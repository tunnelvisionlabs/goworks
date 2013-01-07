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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple3;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.tvl.goworks.editor.go.navigation.GoNode.DeclarationDescription;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BlockContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParametersContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SignatureContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SourceFileContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseListener;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseVisitor;

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

        private final Map<String, Description> _typeDescriptions = new HashMap<String, Description>();
        /**
         * Name -> Parent Node -> Method Node
         */
        private final List<Tuple3<String, ? extends Description, ? extends Description>> _methodDescriptions =
            new ArrayList<Tuple3<String, ? extends Description, ? extends Description>>();

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
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFile, version=1)
        public void exitSourceFile(SourceFileContext ctx) {
            for (Tuple3<String, ? extends Description, ? extends Description> pair : _methodDescriptions) {
                Description typeDescription = _typeDescriptions.get(pair.getItem1());
                if (typeDescription == null) {
                    continue;
                }

                Description parent = pair.getItem2();
                parent.getChildren().remove(pair.getItem3());
                typeDescription.getChildren().add(pair.getItem3());
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public void enterConstSpec(ConstSpecContext ctx) {
            if (!isTopLevel(ctx)) {
                return;
            }

            IdentifierListContext idListContext = ctx.identifierList();
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            String type = ctx.type() != null ? String.format(" : <font color='808080'>%s</font>", HtmlSignatureVisitor.UNCOLORED.visit(ctx.type())) : "";
            for (TerminalNode<Token> identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = identifier.getSymbol().getText() + type;

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.CONSTANT);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(signature);
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
            ExpressionListContext expressionListContext = ctx.expressionList();
            List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
            List<? extends ExpressionContext> expressions = expressionListContext != null ? expressionListContext.expression() : Collections.<ExpressionContext>emptyList();
            String type = ctx.type() != null ? HtmlSignatureVisitor.UNCOLORED.visit(ctx.type()) : "";
            for (int i = 0; i < identifiers.size(); i++) {
                TerminalNode<Token> identifier = identifiers.get(i);
                String varType = type;
                if (varType.isEmpty() && expressions.size() == identifiers.size()) {
                    varType = HtmlSignatureVisitor.UNCOLORED.visit(expressions.get(i));
                }

                if (!varType.isEmpty()) {
                    varType = String.format(" : <font color='808080'>%s</font>", varType);
                }

                Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                String signature = identifier.getSymbol().getText() + varType;

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(signature);
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=1),
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
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_anonymousField, version=0),
        })
        public void enterFieldDecl(FieldDeclContext ctx) {
            if (ctx.getParent() == null || isAnonymousType((StructTypeContext)ctx.getParent())) {
                return;
            }

            IdentifierListContext idListContext = ctx.identifierList();
            if (idListContext != null) {
                List<? extends TerminalNode<Token>> identifiers = idListContext.IDENTIFIER();
                String type = ctx.type() != null ? String.format(" : <font color='808080'>%s</font>", HtmlSignatureVisitor.UNCOLORED.visit(ctx.type())) : "";
                for (TerminalNode<Token> identifier : identifiers) {
                    Interval sourceInterval = new Interval(identifier.getSymbol().getStartIndex(), ParseTrees.getStopSymbol(ctx).getStopIndex());
                    String signature = identifier.getSymbol().getText() + type;

                    GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FIELD);
                    description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(signature);
                    getCurrentParent().getChildren().add(description);
                }
            } else if (ctx.anonymousField() != null) {
                // anonymous field, add to struct node in navigator
                String type = HtmlSignatureVisitor.UNCOLORED.visit(ctx.anonymousField());
                String headerFormat;
                String header = getCurrentParent().getHtmlHeader();
                if (!header.contains(":")) {
                    headerFormat = "%s : <font color='808080'>%s</font>";
                } else {
                    headerFormat = "%s, <font color='808080'>%s</font>";
                }

                header = String.format(headerFormat, header, type);
                getCurrentParent().setHtmlHeader(header);
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
        public void enterInterfaceType(InterfaceTypeContext ctx) {
            if (isAnonymousType(ctx)) {
                return;
            }

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
            if (isAnonymousType(ctx)) {
                return;
            }

            descriptionStack.pop();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
        public void enterStructType(StructTypeContext ctx) {
            if (isAnonymousType(ctx)) {
                return;
            }

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
            if (isAnonymousType(ctx)) {
                return;
            }

            if (isTopLevel(ctx)) {
                String name = descriptionStack.peek().getName();
                if (!_typeDescriptions.containsKey(name)) {
                    _typeDescriptions.put(name, descriptionStack.peek());
                }
            }

            descriptionStack.pop();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0)
        public void enterQualifiedIdentifier(QualifiedIdentifierContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0)
        public void exitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
        public void enterArrayType(ArrayTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
        public void exitArrayType(ArrayTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0)
        public void enterPointerType(PointerTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0)
        public void exitPointerType(PointerTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
        public void enterFunctionType(FunctionTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
        public void exitFunctionType(FunctionTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0)
        public void enterSliceType(SliceTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0)
        public void exitSliceType(SliceTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0)
        public void enterMapType(MapTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0)
        public void exitMapType(MapTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0)
        public void enterChannelType(ChannelTypeContext ctx) {
            handleEnterTypeAlias(ctx);
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0)
        public void exitChannelType(ChannelTypeContext ctx) {
            handleExitTypeAlias(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        })
        public void enterMethodSpec(MethodSpecContext ctx) {
            if (ctx.getParent() == null || isAnonymousType((InterfaceTypeContext)ctx.getParent())) {
                return;
            }

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
                String signature = HtmlSignatureVisitor.COLORED.visit(ctx);

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(signature);
                getCurrentParent().getChildren().add(description);
                description.setChildren(new ArrayList<Description>());
                descriptionStack.push(description);
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        })
        public void exitMethodSpec(MethodSpecContext ctx) {
            if (ctx.getParent() == null || isAnonymousType((InterfaceTypeContext)ctx.getParent())) {
                return;
            }

            if (ctx.interfaceTypeName() != null || ctx.methodName() != null) {
                descriptionStack.pop();
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
        public void enterFunctionDecl(FunctionDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : "?";
            String signature = HtmlSignatureVisitor.COLORED.visit(ctx);

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FUNCTION);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(signature);
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
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
        })
        public void enterMethodDecl(MethodDeclContext ctx) {
            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = ctx.methodName() != null && ctx.methodName().IDENTIFIER() != null ? ctx.methodName().IDENTIFIER().getSymbol().getText() : "?";
            String signature = HtmlSignatureVisitor.COLORED.visit(ctx);

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(signature);
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());

            ReceiverContext receiverContext = ctx.receiver();
            BaseTypeNameContext baseTypeNameContext = receiverContext != null ? receiverContext.baseTypeName() : null;
            if (baseTypeNameContext != null && baseTypeNameContext.IDENTIFIER() != null) {
                String receiverTypeName = baseTypeNameContext.IDENTIFIER().getText();
                _methodDescriptions.add(Tuple.create(receiverTypeName, getCurrentParent(), description));
            }

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

        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=2, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0),
        })
        private boolean isAnonymousType(ParserRuleContext<Token> context) {
            if (!(context instanceof InterfaceTypeContext) && !(context instanceof StructTypeContext)) {
                throw new IllegalArgumentException();
            }

            if (context.getParent() == null) {
                return true;
            }

            TypeLiteralContext typeLiteralContext = (TypeLiteralContext)context.getParent();
            if (typeLiteralContext.getParent() == null) {
                return true;
            }

            TypeContext typeContext = (TypeContext)typeLiteralContext.getParent();
            if (!(typeContext.getParent() instanceof TypeSpecContext)) {
                return true;
            }

            return false;
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0),
        })
        private boolean isTopLevel(ConstSpecContext context) {
            if (ParseTrees.findAncestor(context, BodyContext.class) != null) {
                return false;
            }

            return true;
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0),
        })
        private boolean isTopLevel(StructTypeContext context) {
            if (isAnonymousType(context)) {
                return false;
            }

            if (ParseTrees.findAncestor(context, BodyContext.class) != null) {
                return false;
            }

            return true;
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=2, dependents=Dependents.PARENTS),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0),
        })
        private boolean isTypeAlias(ParserRuleContext<Token> context, boolean topLevelOnly) {
            switch (context.getRuleIndex()) {
            case GoParser.RULE_qualifiedIdentifier:
            case GoParser.RULE_arrayType:
            case GoParser.RULE_pointerType:
            case GoParser.RULE_functionType:
            case GoParser.RULE_sliceType:
            case GoParser.RULE_mapType:
            case GoParser.RULE_channelType:
                break;

            default:
                throw new IllegalArgumentException();
            }

            TypeSpecContext typeSpecContext = null;

            if (context.getParent() instanceof TypeLiteralContext) {
                TypeLiteralContext typeLiteralContext = (TypeLiteralContext)context.getParent();
                if (!(typeLiteralContext.getParent() instanceof TypeContext)) {
                    return false;
                }

                if (!(typeLiteralContext.getParent().getParent() instanceof TypeSpecContext)) {
                    return false;
                }

                typeSpecContext = (TypeSpecContext)typeLiteralContext.getParent().getParent();
            } else if (context.getParent() instanceof TypeNameContext) {
                TypeNameContext typeNameContext = (TypeNameContext)context.getParent();
                if (!(typeNameContext.getParent() instanceof TypeContext)) {
                    return false;
                }

                if (!(typeNameContext.getParent().getParent() instanceof TypeSpecContext)) {
                    return false;
                }

                typeSpecContext = (TypeSpecContext)typeNameContext.getParent().getParent();
            }

            if (typeSpecContext == null) {
                return false;
            }

            if (!topLevelOnly) {
                return true;
            }

            return ParseTrees.findAncestor(typeSpecContext, BodyContext.class) == null;
        }

        private boolean handleEnterTypeAlias(ParserRuleContext<Token> ctx) {
            if (!isTypeAlias(ctx, false)) {
                return false;
            }

            Interval sourceInterval = ParseTrees.getSourceInterval(ctx);
            String name = typeNameStack.isEmpty() ? "?" : typeNameStack.peek();
            String signature = String.format("%s : <font color='808080'>%s</font>", Description.htmlEscape(name), HtmlSignatureVisitor.UNCOLORED.visit(ctx.getParent()));
            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.TYPEDEF);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(signature);
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
            return true;
        }

        private boolean handleExitTypeAlias(ParserRuleContext<Token> context) {
            if (!isTypeAlias(context, false)) {
                return false;
            }

            if (isTypeAlias(context, true)) {
                String name = descriptionStack.peek().getName();
                if (!_typeDescriptions.containsKey(name)) {
                    _typeDescriptions.put(name, descriptionStack.peek());
                }
            }

            descriptionStack.pop();
            return true;
        }
    }

    public static class HtmlSignatureVisitor extends GoParserBaseVisitor<String> {
        public static final HtmlSignatureVisitor COLORED = new HtmlSignatureVisitor(true);
        public static final HtmlSignatureVisitor UNCOLORED = new HtmlSignatureVisitor(false);

        private final boolean _colored;

        public HtmlSignatureVisitor(boolean colored) {
            this._colored = colored;
        }

        @Override
        protected String defaultResult() {
            return "";
        }

        @Override
        protected String aggregateResult(String aggregate, String nextResult) {
            if (aggregate == null || aggregate.isEmpty()) {
                return nextResult;
            } else if (nextResult == null || nextResult.isEmpty()) {
                return aggregate;
            }

            return aggregate + ", " + nextResult;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
        })
        public String visitFunctionDecl(FunctionDeclContext ctx) {
            // name(args) return
            StringBuilder result = new StringBuilder();
            if (ctx.IDENTIFIER() != null) {
                result.append(Description.htmlEscape(ctx.IDENTIFIER().getText()));
            }

            if (ctx.signature() != null) {
                result.append(visit(ctx.signature()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
        })
        public String visitMethodDecl(MethodDeclContext ctx) {
            // name(args) return
            StringBuilder result = new StringBuilder();
            if (ctx.methodName() != null) {
                result.append(Description.htmlEscape(ctx.methodName().getText()));
            }

            if (ctx.signature() != null) {
                result.append(visit(ctx.signature()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
        })
        public String visitMethodSpec(MethodSpecContext ctx) {
            if (ctx.interfaceTypeName() != null) {
                return Description.htmlEscape(ctx.interfaceTypeName().getText());
            }

            StringBuilder result = new StringBuilder();
            if (ctx.methodName() != null) {
                result.append(Description.htmlEscape(ctx.methodName().getText()));
            }

            if (ctx.signature() != null) {
                result.append(visit(ctx.signature()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
        })
        public String visitSignature(SignatureContext ctx) {
            StringBuilder result = new StringBuilder();
            if (ctx.parameters() != null) {
                result.append(visit(ctx.parameters()));
            }

            result.append(' ');
            if (ctx.result() != null) {
                result.append(visit(ctx.result()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0),
        })
        public String visitParameters(ParametersContext ctx) {
            StringBuilder result = new StringBuilder();
            result.append('(');

            if (ctx.parameterList() != null) {
                result.append(visit(ctx.parameterList()));
            }

            result.append(')');
            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0),
        })
        public String visitParameterList(ParameterListContext ctx) {
            // default impl does the right thing
            return super.visitParameterList(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
        })
        public String visitParameterDecl(ParameterDeclContext ctx) {
            StringBuilder result = new StringBuilder();
            if (ctx.identifierList() != null) {
                result.append(visit(ctx.identifierList()));
                result.append(' ');
            }

            if (_colored) {
                result.append("<font color='808080'>");
            }

            if (ctx.ellip != null) {
                result.append("...");
            }

            if (ctx.type() != null) {
                result.append(UNCOLORED.visit(ctx.type()));
            }

            if (_colored) {
                result.append("</font>");
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        })
        public String visitIdentifierList(IdentifierListContext ctx) {
            StringBuilder result = new StringBuilder();
            for (TerminalNode<Token> node : ctx.IDENTIFIER()) {
                if (result.length() > 0) {
                    result.append(", ");
                }

                result.append(Description.htmlEscape(node.getText()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=2, dependents=Dependents.PARENTS),
        })
        public String visitType(TypeContext ctx) {
            // default impl does the right thing
            return super.visitType(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=3, dependents=Dependents.PARENTS),
        })
        public String visitTypeName(TypeNameContext ctx) {
            return Description.htmlEscape(ctx.getText());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
        })
        public String visitTypeLiteral(TypeLiteralContext ctx) {
            // default impl does the right thing
            return super.visitTypeLiteral(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
        })
        public String visitOperandExpr(OperandExprContext ctx) {
            // default impl does the right thing
            return super.visitOperandExpr(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
        })
        public String visitCompositeLiteral(CompositeLiteralContext ctx) {
            if (ctx.literalType() != null) {
                return visit(ctx.literalType());
            }

            return "";
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
        public String visitArrayType(ArrayTypeContext ctx) {
            if (ctx.elementType() == null) {
                return "[...]?";
            }

            return "[...]" + visit(ctx.elementType());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
        })
        public String visitStructType(StructTypeContext ctx) {
            if (ctx.fieldDecl().isEmpty()) {
                return "struct{}";
            }

            return "struct{...}";
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
        })
        public String visitPointerType(PointerTypeContext ctx) {
            if (ctx.baseType() == null) {
                return "*?";
            }

            return "*" + visit(ctx.baseType());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
        })
        public String visitFunctionType(FunctionTypeContext ctx) {
            if (ctx.signature() != null) {
                return "func" + UNCOLORED.visit(ctx.signature());
            } else {
                return "func?";
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
        })
        public String visitInterfaceType(InterfaceTypeContext ctx) {
            if (ctx.methodSpec().isEmpty()) {
                return "interface{}";
            }

            return "interface{?}";
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
        public String visitSliceType(SliceTypeContext ctx) {
            if (ctx.elementType() == null) {
                return "[]?";
            }

            return "[]" + visit(ctx.elementType());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
        public String visitMapType(MapTypeContext ctx) {
            String keyType = ctx.keyType() != null ? visit(ctx.keyType()) : "?";
            String elementType = ctx.elementType() != null ? visit(ctx.elementType()) : "?";
            return String.format("map[%s]%s", keyType, elementType);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
        public String visitChannelType(ChannelTypeContext ctx) {
            StringBuilder result = new StringBuilder();
            if (ctx.recv != null) {
                result.append("&lt;-");
            }

            result.append("chan");
            if (ctx.send != null) {
                result.append("&lt;-");
            }

            result.append(' ');
            if (ctx.elementType() != null) {
                result.append(visit(ctx.elementType()));
            }

            return result.toString();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
        })
        public String visitResult(ResultContext ctx) {
            if (ctx.parameters() != null) {
                return visit(ctx.parameters());
            } else if (ctx.type() != null) {
                if (_colored) {
                    return String.format("<font color='808080'>%s</font>", UNCOLORED.visit(ctx.type()));
                } else {
                    return visit(ctx.type());
                }
            } else {
                return "";
            }
        }

    }
}
