/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.netbeans.api.annotations.common.NonNull;
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
public class CategorySupport implements ActionListener, PreviewProvider, PreferencesCustomizer {

    public static final String OPTION_ID = "org.tvl.goworks.editor.go.formatting.FormatOptions.ID";

    private static final int LOAD = 0;
    private static final int STORE = 1;
    private static final int ADD_LISTENERS = 2;

    private final String previewText;

    private final String id;
    private final JPanel panel;

    private final Preferences preferences;

    private final List<JComponent> components = new LinkedList<JComponent>();
    private JEditorPane previewPane;

    protected CategorySupport(Preferences preferences, String id, JPanel panel, String previewText) {
        this.preferences = preferences;
        this.id = id;
        this.panel = panel;
        this.previewText = previewText != null ? previewText : Bundle.SAMPLE_Default();

        // Scan the panel for its components
        scan(panel, components);

        // Load and hook up all the components
        loadFrom(preferences);
        addListeners();
    }

    protected void addListeners() {
        scan(ADD_LISTENERS, null);
    }

    protected void loadFrom(Preferences preferences) {
        scan(LOAD, preferences);
    }

    protected void storeTo(Preferences p) {
        scan(STORE, p);
    }

    protected void notifyChanged() {
        storeTo(preferences);
        refreshPreview();
    }

    // ActionListener implementation ---------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        notifyChanged();
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

    // Private methods -----------------------------------------------------

    private void performOperation(int operation, JComponent jc, AbstractFormatOption option, Preferences p) {
        switch(operation) {
        case LOAD:
            loadData(jc, option, p);
            break;

        case STORE:
            storeData(jc, option, p);
            break;

        case ADD_LISTENERS:
            addListener(jc);
            break;
        }
    }

    private void scan(int what, Preferences p ) {
        for (JComponent jc : components) {
            Object o = jc.getClientProperty(OPTION_ID);
            if (o instanceof String) {
                performOperation(what, jc, FormatOptions.getOption((String)o), p);
            } else if (o instanceof String[]) {
                for(String oid : (String[])o) {
                    performOperation(what, jc, FormatOptions.getOption(oid), p);
                }
            } else if (o instanceof AbstractFormatOption) {
                performOperation(what, jc, (AbstractFormatOption)o, p);
            } else if (o instanceof AbstractFormatOption[]) {
                for(AbstractFormatOption oid : (AbstractFormatOption[])o) {
                    performOperation(what, jc, oid, p);
                }
            }
        }
    }

    private void scan(Container container, List<JComponent> components) {
        for (Component c : container.getComponents()) {
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                Object o = jc.getClientProperty(OPTION_ID);
                if (o instanceof String || o instanceof String[] || o instanceof AbstractFormatOption || o instanceof AbstractFormatOption[]) {
                    components.add(jc);
                }
            }

            if (c instanceof Container) {
                scan((Container)c, components);
            }
        }
    }

    /** Very smart method which tries to set the values in the components correctly
     */
    private void loadData( JComponent jc, AbstractFormatOption optionID, Preferences node ) {
        if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            toggle.setSelected( ((BooleanFormatOption)optionID).getValue(node) );
        }
    }

    private void storeData( JComponent jc, @NonNull AbstractFormatOption option, Preferences node ) {
        if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            if (!option.equals(FormatOptions.expandTabToSpaces) && ((BooleanFormatOption)option).getDefaultValue() == toggle.isSelected())
                node.remove(option.getName());
            else
                node.putBoolean(option.getName(), toggle.isSelected());
        }
    }

    private void addListener( JComponent jc ) {
        if ( jc instanceof JToggleButton ) {
            JToggleButton toggle = (JToggleButton)jc;
            toggle.addActionListener(this);
        }
    }

}
