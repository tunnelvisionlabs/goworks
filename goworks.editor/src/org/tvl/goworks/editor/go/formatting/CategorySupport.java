/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.awt.Rectangle;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.netbeans.modules.options.editor.spi.PreviewProvider;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class CategorySupport implements PreviewProvider, PreferencesCustomizer {

    public static final String OPTION_ID = "org.tvl.goworks.editor.go.formatting.FormatOptions.ID";

    private final String previewText;

    private final String id;
    private final JPanel panel;

    private final Preferences preferences;

    private JEditorPane previewPane;

    protected CategorySupport(Preferences preferences, String id, JPanel panel, String previewText) {
        this.preferences = preferences;
        this.id = id;
        this.panel = panel;
        this.previewText = previewText != null ? previewText : Bundle.SAMPLE_Default();
    }

    protected void notifyChanged() {
        refreshPreview();
    }

    // PreviewProvider methods -----------------------------------------------------

    @Override
    public JComponent getPreviewComponent() {
        if (previewPane == null) {
            previewPane = new JEditorPane();
            previewPane.getAccessibleContext().setAccessibleName(Bundle.AN_Preview());
            previewPane.getAccessibleContext().setAccessibleDescription(Bundle.AD_Preview());
            previewPane.putClientProperty("HighlightsLayerIncludes", "^org\\.netbeans\\.modules\\.editor\\.lib2\\.highlighting\\.SyntaxHighlighting$"); //NOI18N
            previewPane.setEditorKit(CloneableEditorSupport.getEditorKit(GoEditorKit.GO_MIME_TYPE));
            previewPane.setEditable(false);
        }
        return previewPane;
    }

    @Override
    public void refreshPreview() {
        JEditorPane jep = (JEditorPane) getPreviewComponent();

        try {
            int rm = FormatOptions.rightMargin.getValue(preferences);
            jep.putClientProperty("TextLimitLine", rm); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TEXT_LINE_WRAP, ""); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TAB_SIZE, ""); //NOI18N
            jep.getDocument().putProperty(SimpleValueNames.TEXT_LIMIT_WIDTH, ""); //NOI18N
        }
        catch( NumberFormatException e ) {
            // Ignore it
        }

        try {
            // make sure the CodeStyle class is initialized
            Class.forName(CodeStyle.class.getName(), true, CodeStyle.class.getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            // ignore
        }

        CodeStyle codeStyle = FormatOptions.codeStyleFactory.create(preferences);
        jep.setIgnoreRepaint(true);
        String formattedPreviewText = GoReformatTask.reformat(previewText, codeStyle);
        jep.setText(formattedPreviewText);
        jep.setIgnoreRepaint(false);
        jep.scrollRectToVisible(new Rectangle(0,0,10,10) );
        jep.repaint(100);
    }

    // PreferencesCustomizer implementation --------------------------------

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public String getDisplayName() {
        return panel.getName();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

    // PreferencesCustomizer.Factory implementation ------------------------

    public static final class Factory implements PreferencesCustomizer.Factory {

        private final String id;
        private final Class<? extends JPanel> panelClass;
        private final String previewText;

        public Factory(String id, Class<? extends JPanel> panelClass, String previewText) {
            this.id = id;
            this.panelClass = panelClass;
            this.previewText = previewText;
        }

        @Override
        public PreferencesCustomizer create(Preferences preferences) {
            try {
                CategorySupport categorySupport = new CategorySupport(preferences, id, panelClass.newInstance(), previewText);
                return categorySupport;
            } catch (InstantiationException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }
    }

}
