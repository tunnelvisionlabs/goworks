/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.tvl.goworks.editor.go.navigation.GoNode.DeclarationDescription;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BlockContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SourceFileContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class GoDeclarationsScanner {
    // -J-Dorg.tvl.goworks.editor.go.navigation.GoDeclarationsScanner.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoDeclarationsScanner.class.getName());

    public Description scan(CompiledModel model) {
        GoDeclarationsPanel panel = GoDeclarationsPanel.getInstance();
        GoDeclarationsPanelUI ui = panel != null ? panel.getComponent() : null;
        if (ui == null) {
            return null;
        }

        GoNode.DeclarationDescription rootDescription = scan(ui, model);
        return rootDescription;
    }

    public GoNode.DeclarationDescription scan(NavigatorPanelUI ui, CompiledModel model) {
        try {
            // don't update if there were errors and a result is already displayed
            /*if (!result.getParser().getSyntaxErrors().isEmpty() && !ui.isShowingWaitNode()) {
                return;
            }*/

            GoNode.DeclarationDescription rootDescription = new GoNode.DeclarationDescription();
            rootDescription.setChildren(new ArrayList<Description>());
            rootDescription.setFileObject(model.getSnapshot().getVersionedDocument().getFileObject());

//            for (CompiledFileModel importedParseResult : model.getImportedGroupResults()) {
//                processParseResult(null, importedParseResult, ui, rootDescription);
//            }

            processParseResult(model.getSnapshot(), model.getResult(), ui, rootDescription);
            return rootDescription;
        } catch (RuntimeException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while scanning for declarations.", ex);
            return null;
        }
    }

    private void processParseResult(DocumentSnapshot snapshot,
                                    CompiledFileModel result,
                                    NavigatorPanelUI ui,
                                    GoNode.DeclarationDescription rootDescription) {

        SourceFileContext parseResult = result.getResult();
        if (parseResult != null) {
            DeclarationsScannerListener listener = new DeclarationsScannerListener(snapshot, rootDescription);
            ParseTreeWalker.DEFAULT.walk(listener, parseResult);
        }
    }

    private static class DeclarationsScannerListener extends GoParserBaseListener {
        private final DocumentSnapshot snapshot;
        private final Deque<DeclarationDescription> descriptionStack = new ArrayDeque<DeclarationDescription>();
        private final Deque<String> typeNameStack = new ArrayDeque<String>();

        private int resultLevel;
        private int blockLevel;

        public DeclarationsScannerListener(DocumentSnapshot snapshot, DeclarationDescription rootDescription) {
            this.snapshot = snapshot;
            this.descriptionStack.push(rootDescription);
        }

        public DeclarationDescription getCurrentParent() {
            return descriptionStack.peek();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public void enterConstSpec(ConstSpecContext ctx) {
            IdentifierListContext idListContext = ctx.identifierList();
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.CONSTANT);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public void enterVarSpec(VarSpecContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            IdentifierListContext idListContext = ctx.identifierList();
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public void enterShortVarDecl(ShortVarDeclContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            IdentifierListContext idListContext = ctx.identifierList();
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public void enterFieldDecl(FieldDeclContext ctx) {
            IdentifierListContext idListContext = ctx.identifierList();
            if (idListContext != null) {
                List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
                for (TerminalNode<Token> identifier : identifiers) {
                    Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                    String signature = String.format("%s", identifier.getSymbol().getText());

                    GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FIELD);
                    description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                    getCurrentParent().getChildren().add(description);
                }
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
        public void enterInterfaceType(InterfaceTypeContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String signature = typeNameStack.isEmpty() ? "?interface?" : typeNameStack.peek();

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.INTERFACE);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
        public void exitInterfaceType(InterfaceTypeContext ctx) {
            descriptionStack.pop();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
        public void enterStructType(StructTypeContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String signature = typeNameStack.isEmpty() ? "?struct?" : typeNameStack.peek();

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.STRUCT);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
        public void exitStructType(StructTypeContext ctx) {
            descriptionStack.pop();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        })
        public void enterMethodSpec(MethodSpecContext ctx) {
            if (ctx.interfaceTypeName() != null) {
                InterfaceTypeNameContext interfaceTypeNameContext = ctx.interfaceTypeName();
                Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
                String name = interfaceTypeNameContext.typeName() != null ? interfaceTypeNameContext.typeName().getText() : "?";
                String signature = name;

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.INTERFACE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                getCurrentParent().getChildren().add(description);
                description.setChildren(new ArrayList<Description>());
                descriptionStack.push(description);
            } else if (ctx.methodName() != null) {
                MethodNameContext methodNameContext = ctx.methodName();
                Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
                String name = methodNameContext.IDENTIFIER() != null ? methodNameContext.IDENTIFIER().getText() : "?";
                String signature = name;

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
                getCurrentParent().getChildren().add(description);
                description.setChildren(new ArrayList<Description>());
                descriptionStack.push(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        })
        public void exitMethodSpec(MethodSpecContext ctx) {
            if (ctx.interfaceTypeName() != null || ctx.methodName() != null) {
                descriptionStack.pop();
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
        public void enterFunctionDecl(FunctionDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "?";
            String signature = name;

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FUNCTION);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
        public void exitFunctionDecl(FunctionDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        })
        public void enterMethodDecl(MethodDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = ctx.methodName() != null && ctx.methodName().IDENTIFIER() != null ? ctx.methodName().IDENTIFIER().getSymbol().getText() : "?";
            String signature = String.format("%s", name);

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", Description.htmlEscape(signature)));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0)
        public void exitMethodDecl(MethodDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0)
        public void enterTypeSpec(TypeSpecContext ctx) {
            if (ctx.IDENTIFIER() != null) {
                typeNameStack.push(ctx.IDENTIFIER().getSymbol().getText());
            } else {
                typeNameStack.push("?");
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0)
        public void exitTypeSpec(TypeSpecContext ctx) {
            typeNameStack.pop();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
        public void enterResult(ResultContext ctx) {
            resultLevel++;
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
        public void exitResult(ResultContext ctx) {
            resultLevel--;
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_block, version=0)
        public void enterBlock(BlockContext ctx) {
            blockLevel++;
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_block, version=0)
        public void exitBlock(BlockContext ctx) {
            blockLevel--;
        }

    }
}
