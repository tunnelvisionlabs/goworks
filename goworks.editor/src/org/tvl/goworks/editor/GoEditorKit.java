/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor;

import java.util.prefs.Preferences;
import javax.swing.Action;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import org.antlr.netbeans.editor.commenting.BlockCommentFormat;
import org.antlr.netbeans.editor.commenting.ExtendedCommentAction;
import org.antlr.netbeans.editor.commenting.ExtendedUncommentAction;
import org.antlr.netbeans.editor.commenting.LineCommentFormat;
import org.antlr.netbeans.editor.commenting.StandardCommenter;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.NbEditorKit;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=EditorKit.class)
public class GoEditorKit extends NbEditorKit {

    public static final String GO_MIME_TYPE = "text/x-go";

    private static final LineCommentFormat LINE_COMMENT_FORMAT = new LineCommentFormat("//");
    private static final BlockCommentFormat BLOCK_COMMENT_FORMAT = new BlockCommentFormat("/*", "*/");

    @Override
    protected void initDocument(BaseDocument doc) {
        super.initDocument(doc);

        Preferences preferences = MimeLookup.getLookup(GO_MIME_TYPE).lookup(Preferences.class);
        if (preferences != null) {
            preferences.putInt(SimpleValueNames.COMPLETION_AUTO_POPUP_DELAY, 0);
        }
    }

    @Override
    public String getContentType() {
        return GO_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        StandardCommenter commenter = new StandardCommenter(LINE_COMMENT_FORMAT, BLOCK_COMMENT_FORMAT);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedCommentAction commentAction = new ExtendedCommentAction(commenter);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedUncommentAction uncommentAction = new ExtendedUncommentAction(commenter);

        Action[] actions = {
        };

        actions = TextAction.augmentList(superActions, actions);
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] instanceof CommentAction) {
                actions[i] = commentAction;
            } else if (actions[i] instanceof UncommentAction) {
                actions[i] = uncommentAction;
            }
        }

        return actions;
    }

}
