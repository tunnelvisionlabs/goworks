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
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.openide.util.Exceptions;
import org.tvl.goworks.editor.go.navigation.GoNode.DeclarationDescription;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParserBase.blockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.structTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;

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

        sourceFileContext parseResult = result.getResult();

        if (result != null) {
            DeclarationsScannerListener listener = new DeclarationsScannerListener(snapshot, rootDescription);
            ParseTreeWalker.DEFAULT.walk(listener, parseResult);
        }
    }

    private static class DeclarationsScannerListener extends BlankGoParserBaseListener {
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
        public void enterRule(constSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            for (Token identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getStartIndex(), ctx.stop.getStopIndex());
                String signature = String.format("%s", identifier.getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.CONSTANT);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterRule(varSpecContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            for (Token identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getStartIndex(), ctx.stop.getStopIndex());
                String signature = String.format("%s", identifier.getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterRule(shortVarDeclContext ctx) {
            // no locals in navigator
            if (blockLevel > 0) {
                return;
            }

            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            for (Token identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getStartIndex(), ctx.stop.getStopIndex());
                String signature = String.format("%s", identifier.getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.VARIABLE);
                description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                getCurrentParent().getChildren().add(description);
            }
        }

        @Override
        public void enterRule(fieldDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            if (idListContext != null) {
                List<Token> identifiers = idListContext.ids_list;
                for (Token identifier : identifiers) {
                    Interval sourceInterval = new Interval(identifier.getStartIndex(), ctx.stop.getStopIndex());
                    String signature = String.format("%s", identifier.getText());

                    GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FIELD);
                    description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
                    description.setHtmlHeader(String.format("%s", signature));
                    getCurrentParent().getChildren().add(description);
                }
            }
        }

        @Override
        public void enterRule(structTypeContext ctx) {
            Interval sourceInterval = new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex());
            String signature = typeNameStack.isEmpty() ? "?struct?" : typeNameStack.peek();

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.STRUCT);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        public void exitRule(structTypeContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterRule(functionDeclContext ctx) {
            Interval sourceInterval = new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex());
            String signature = String.format("%s", ctx.name.getText());

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.FUNCTION);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        public void exitRule(functionDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterRule(methodDeclContext ctx) {
            Interval sourceInterval = new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex());
            String signature = String.format("%s", ctx.name.name.getText());

            GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature, DeclarationKind.METHOD);
            description.setOffset(snapshot, getCurrentParent().getFileObject(), sourceInterval.a);
            description.setHtmlHeader(String.format("%s", signature));
            getCurrentParent().getChildren().add(description);
            description.setChildren(new ArrayList<Description>());
            descriptionStack.push(description);
        }

        @Override
        public void exitRule(methodDeclContext ctx) {
            descriptionStack.pop();
        }

        @Override
        public void enterRule(typeSpecContext ctx) {
            typeNameStack.push(ctx.name.getText());
        }

        @Override
        public void exitRule(typeSpecContext ctx) {
            typeNameStack.pop();
        }

        @Override
        public void enterRule(resultContext ctx) {
            resultLevel++;
        }

        @Override
        public void exitRule(resultContext ctx) {
            resultLevel--;
        }

        @Override
        public void enterRule(blockContext ctx) {
            blockLevel++;
        }

        @Override
        public void exitRule(blockContext ctx) {
            blockLevel--;
        }

    }
}
