/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor;

import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;
import org.antlr.netbeans.editor.commenting.BlockCommentFormat;
import org.antlr.netbeans.editor.commenting.ExtendedCommentAction;
import org.antlr.netbeans.editor.commenting.ExtendedUncommentAction;
import org.antlr.netbeans.editor.commenting.LineCommentFormat;
import org.antlr.netbeans.editor.commenting.StandardCommenter;
import org.netbeans.api.editor.EditorActionRegistration;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.editor.BaseAction;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.BaseKit;
import org.netbeans.editor.Utilities;
import org.netbeans.editor.ext.ExtKit;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.go.navigation.GoToSupport;

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
            new GoGoToDeclarationAction(),
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

    @NbBundle.Messages({
        "generate-goto-popup=Go To",
        "goto-source=Go to Source",
        "goto_source_open_source_not_formatted=Go to Source",
    })
    @EditorActionRegistration(name = generateGoToPopupAction, mimeType = GO_MIME_TYPE)
    public static class GoGenerateGoToPopupAction extends NbGenerateGoToPopupAction {

        @Override
        public void actionPerformed(ActionEvent evt, JTextComponent target) {
        }

        private void addAcceleretors(Action a, JMenuItem item, JTextComponent target){
            // Try to get the accelerator
            Keymap km = target.getKeymap();
            if (km != null) {

                KeyStroke[] keys = km.getKeyStrokesForAction(a);
                if (keys != null && keys.length > 0) {
                    item.setAccelerator(keys[0]);
                }else if (a!=null){
                    KeyStroke ks = (KeyStroke)a.getValue(Action.ACCELERATOR_KEY);
                    if (ks!=null) {
                        item.setAccelerator(ks);
                    }
                }
            }
        }

        private void addAction(JTextComponent target, JMenu menu, Action a){
            if (a != null) {
                String actionName = (String) a.getValue(Action.NAME);
                JMenuItem item = null;
                if (a instanceof BaseAction) {
                    item = ((BaseAction)a).getPopupMenuItem(target);
                }
                if (item == null) {
                    // gets trimmed text that doesn' contain "go to"
                    String itemText = (String)a.getValue(ExtKit.TRIMMED_TEXT);
                    if (itemText == null){
                        itemText = getItemText(target, actionName, a);
                    }
                    if (itemText != null) {
                        item = new JMenuItem(itemText);
                        Mnemonics.setLocalizedText(item, itemText);
                        item.addActionListener(a);
                        addAcceleretors(a, item, target);
                        item.setEnabled(a.isEnabled());
                        Object helpID = a.getValue ("helpID"); // NOI18N
                        if (helpID != null && (helpID instanceof String))
                            item.putClientProperty ("HelpID", helpID); // NOI18N
                    }else{
                        if (ExtKit.gotoSourceAction.equals(actionName)){
                            item = new JMenuItem(Bundle.goto_source_open_source_not_formatted());
                            addAcceleretors(a, item, target);
                            item.setEnabled(false);
                        }
                    }
                }

                if (item != null) {
                    menu.add(item);
                }

            }
        }

        protected void addAction(JTextComponent target, JMenu menu, String actionName) {
            BaseKit kit = Utilities.getKit(target);
            if (kit == null) return;
            Action a = kit.getActionByName(actionName);
            if (a!=null){
                addAction(target, menu, a);
            } else { // action-name is null, add the separator
                menu.addSeparator();
            }
        }

        protected String getItemText(JTextComponent target, String actionName, Action a) {
            String itemText;
            if (a instanceof BaseAction) {
                itemText = ((BaseAction)a).getPopupMenuText(target);
            } else {
                itemText = actionName;
            }
            return itemText;
        }

        @Override
        public JMenuItem getPopupMenuItem(final JTextComponent target) {
            String menuText = Bundle.generate_goto_popup();
            JMenu jm = new JMenu(menuText);
            //addAction(target, jm, ExtKit.gotoSourceAction);
            addAction(target, jm, ExtKit.gotoDeclarationAction);
            //addAction(target, jm, gotoSuperImplementationAction);
            //addAction(target, jm, ExtKit.gotoAction);
            return jm;
        }

    }

    /**
     * @deprecated Not actually deprecated...
     */
    @Deprecated
    public static class GoGoToDeclarationAction extends GotoDeclarationAction {

        public GoGoToDeclarationAction() {
        }

        @Override
        public boolean gotoDeclaration(JTextComponent target) {
            GoToSupport.goTo(target, target.getCaretPosition(), false);
            return true;
        }
    }

}
