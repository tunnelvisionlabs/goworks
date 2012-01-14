/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;

/**
 *
 * @author Sam Harwell
 */
public class GoDeclarationsScanner {

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
            Exceptions.printStackTrace(ex);
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
        private final GoNode.DeclarationDescription rootDescription;

        public DeclarationsScannerListener(DocumentSnapshot snapshot, DeclarationDescription rootDescription) {
            this.snapshot = snapshot;
            this.rootDescription = rootDescription;
        }

        @Override
        public void enterRule(constSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            for (Token identifier : identifiers) {
                Interval sourceInterval = new Interval(identifier.getStartIndex(), ctx.stop.getStopIndex());
                String signature = String.format("%s", identifier.getText());

                GoNode.DeclarationDescription description = new GoNode.DeclarationDescription(signature);
                description.setOffset(snapshot, rootDescription.getFileObject(), sourceInterval.a);
                description.setHtmlHeader(String.format("%s", signature));
                rootDescription.getChildren().add(description);
            }
        }

    }
}
