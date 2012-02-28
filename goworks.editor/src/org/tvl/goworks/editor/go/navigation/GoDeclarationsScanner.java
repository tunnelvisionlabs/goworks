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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.openide.util.Exceptions;
import org.tvl.goworks.editor.go.navigation.GoNode.DeclarationDescription;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParserBase.BlockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ResultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.StructTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.VarSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBaseBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class GoDeclarationsScanner {
    private static final Logger LOGGER = Logger.getLogger(GoDeclarationsScanner.class.getName());

    public Description scan(CompiledModel model) {
        GoDeclarationsPanelUI ui = GoDeclarationsPanel.findDeclarationsPanelUI();
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
            if (LOGGER.isLoggable(Level.FINE)) {
                Exceptions.printStackTrace(ex);
            }
            return null;
        }
    }

    private void processParseResult(DocumentSnapshot snapshot,
                                    CompiledFileModel result,
                                    NavigatorPanelUI ui,
                                    GoNode.DeclarationDescription rootDescription) {

        SourceFileContext parseResult = result.getResult();

        if (result != null) {
            DeclarationsScannerListener listener = new DeclarationsScannerListener(snapshot, rootDescription);
            ParseTreeWalker.DEFAULT.walk(listener, parseResult);
        }
    }

    private static class DeclarationsScannerListener extends GoParserBaseBaseListener {
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
        public void enterConstSpec(ConstSpecContext ctx) {
            IdentifierListContext idListContext = ctx.idList;
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.CONSTANT);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterVarSpec(VarSpecContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            IdentifierListContext idListContext = ctx.idList;
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterShortVarDecl(ShortVarDeclContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            IdentifierListContext idListContext = ctx.idList;
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = String.format("%s", identifier.getSymbol().getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterFieldDecl(FieldDeclContext ctx) {
            IdentifierListContext idListContext = ctx.identifierList();
            if (idListContext != null) {
                List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
                for (TerminalNode<Token> identifier : identifiers) {
                    Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                    String signature = String.format("%s", identifier.getSymbol().getText());

                    GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FIELD);
                    description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(String.format("%s", signature));
                    getCurrentParent().getChildren().add(description);
                }
            }
        }

        @Override
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
        public void exitStructType(StructTypeContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterFunctionDecl(FunctionDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String signature = String.format("%s", ctx.name.getText());

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FUNCTION);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        public void exitFunctionDecl(FunctionDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterMethodDecl(MethodDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = ctx.methodName() != null && ctx.methodName().IDENTIFIER() != null ? ctx.methodName().IDENTIFIER().getSymbol().getText() : "?";
            String signature = String.format("%s", name);

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        public void exitMethodDecl(MethodDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterTypeSpec(TypeSpecContext ctx) {
            typeNameStack.push(ctx.IDENTIFIER().getSymbol().getText());
        }

        @Override
        public void exitTypeSpec(TypeSpecContext ctx) {
            typeNameStack.pop();
        }

        @Override
        public void enterResult(ResultContext ctx) {
            resultLevel++;
        }

        @Override
        public void exitResult(ResultContext ctx) {
            resultLevel--;
        }

        @Override
        public void enterBlock(BlockContext ctx) {
            blockLevel++;
        }

        @Override
        public void exitBlock(BlockContext ctx) {
            blockLevel--;
        }

    }
}
