/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import org.netbeans.api.editor.mimelookup.MimeRegistration;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=LexerDebuggerEditorKit.LEXER_DEBUGGER_MIME_TYPE, service=EditorKit.class)
public class LexerDebuggerEditorKit extends AbstractGrammarDebuggerEditorKit {
    private static final Logger LOG = Logger.getLogger(LexerDebuggerEditorKit.class.getName());

    public static final String LEXER_DEBUGGER_MIME_TYPE = "text/x-antlr3-ldbg";

    @Override
    public String getContentType() {
        return LEXER_DEBUGGER_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        Action[] actions = {
        };

        actions = TextAction.augmentList(superActions, actions);
        return actions;
    }
}
