/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import com.tvl.spi.editor.completion.CompletionProvider;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkProviderExt;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkType;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.cookies.EditorCookie;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.completion.GoCompletionProvider;
import org.tvl.goworks.editor.go.highlighter.GoHighlighter.TooltipResolver;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=HyperlinkProviderExt.class)
public class GoHyperlinkProvider implements HyperlinkProviderExt {
    // -J-Dorg.tvl.goworks.editor.go.navigation.GoHyperlinkProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoHyperlinkProvider.class.getName());

    @Override
    public Set<HyperlinkType> getSupportedHyperlinkTypes() {
        return EnumSet.of(HyperlinkType.GO_TO_DECLARATION, HyperlinkType.ALT_HYPERLINK);
    }

    @Override
    public boolean isHyperlinkPoint(Document doc, int offset, HyperlinkType type) {
        return getHyperlinkSpan(doc, offset, type) != null;
    }

    @Override
    public int[] getHyperlinkSpan(Document doc, int offset, HyperlinkType type) {
        final Lookup lookup = MimeLookup.getLookup(MimePath.get(GoEditorKit.GO_MIME_TYPE));
        Collection<? extends CompletionProvider> providers = lookup.lookupAll(CompletionProvider.class);
        GoCompletionProvider provider = null;
        for (CompletionProvider current : providers) {
            if (!(current instanceof GoCompletionProvider)) {
                continue;
            }

            provider = (GoCompletionProvider)current;
        }

        if (provider == null) {
            return null;
        }

        Token token = provider.getContext(doc, offset);
        if (token == null) {
            return null;
        }

        switch (token.getType()) {
        case GoParser.IDENTIFIER:
            break;

        default:
            return null;
        }

        return new int[] { token.getStartIndex(), token.getStopIndex() + 1 };
    }

    @Override
    public void performClickAction(Document doc, int offset, HyperlinkType type) {
        JTextComponent component = getComponent(doc);
        if (component == null) {
            return;
        }

        switch (type) {
        case GO_TO_DECLARATION:
            GoToSupport.goTo(component, offset, false);
            break;

//        case ALT_HYPERLINK:
//            JTextComponent focused = EditorRegistry.focusedComponent();
//            if (focused != null && focused.getDocument() == doc) {
//                focused.setCaretPosition(offset);
//                GoToImplementation.goToImplementation(focused);
//            }
//            break;
        }
    }

    @Override
    public String getTooltipText(Document doc, int offset, HyperlinkType type) {
        JTextComponent component = getComponent(doc);
        if (component == null) {
            return "";
        }

        TooltipResolver tooltipResolver = new TooltipResolver();
        return tooltipResolver.getValue(component, doc, EditorStyleConstants.Tooltip, offset, offset);
    }

    private static JTextComponent getComponent(Document doc) {
        DataObject dataObject = NbEditorUtilities.getDataObject(doc);
        if (dataObject == null) {
            return null;
        }

        final EditorCookie editorCookie = dataObject.getLookup().lookup(EditorCookie.class);
        if (editorCookie == null) {
            return null;
        }

        JEditorPane[] panes;
        if (SwingUtilities.isEventDispatchThread()) {
            panes = editorCookie.getOpenedPanes();
        } else {
            final JEditorPane[][] panesArray = new JEditorPane[1][];

            try {
                SwingUtilities.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        panesArray[0] = editorCookie.getOpenedPanes();
                    }
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred while locating a document.", ex);
                return null;
            }

            panes = panesArray[0];
        }

        if (panes == null || panes.length == 0) {
            return null;
        }

        return panes[0];
    }
}
