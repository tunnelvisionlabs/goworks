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
package org.tvl.goworks.editor.go.completion;

import java.awt.event.KeyEvent;
import java.util.Collection;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.antlr4.completion.BaseCompletionController;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.openide.util.Lookup;

/**
 *
 * @author Sam Harwell
 */
public class GoCompletionController extends BaseCompletionController {
    private final GoCompletionProvider provider;

    public GoCompletionController(JTextComponent component, CompletionTask task, int queryType) {
        super(component, task, queryType);
        Lookup lookup = MimeLookup.getLookup(DocumentUtilities.getMimeType(component));
        if (lookup != null) {
            Collection<? extends CompletionProvider> providers = lookup.lookupAll(CompletionProvider.class);
            GoCompletionProvider goCompletionProvider = null;
            for (CompletionProvider completionProvider : providers) {
                if (completionProvider instanceof GoCompletionProvider) {
                    goCompletionProvider = (GoCompletionProvider)completionProvider;
                    break;
                }
            }
            this.provider = goCompletionProvider;
        } else {
            this.provider = null;
        }
    }

    @Override
    public void processKeyEvent(KeyEvent evt, CompletionItem bestMatch, boolean isSelected) {
        if (provider != null) {
            if (isSelected && evt.getID() == KeyEvent.KEY_PRESSED && provider.getCompletionSelectors().indexOf(evt.getKeyChar()) >= 0) {
                evt.consume();
                defaultAction(bestMatch, isSelected);
                return;
            }
        }

        super.processKeyEvent(evt, bestMatch, isSelected);
    }

}
