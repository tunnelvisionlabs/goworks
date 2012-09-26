/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.works.editor.antlr4.completion.CompletionQueryResult;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.completion.GoCompletionItem;
import org.tvl.goworks.editor.go.completion.GoCompletionProvider;

/**
 *
 * @author Sam Harwell
 */
public final class GoToSupport {
    // -J-Dorg.tvl.goworks.editor.go.navigation.GoToSupport.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoToSupport.class.getName());

    private GoToSupport() {
    }

    public static JTextComponent goTo(@NonNull JTextComponent component, int offset, boolean goToSource) {
        Parameters.notNull("component", component);

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

        Future<CompletionQueryResult> futureQuery = provider.executeQuery(CompletionProvider.TOOLTIP_QUERY_TYPE, component, offset, true);
        if (futureQuery == null) {
            return null;
        }

        CompletionQueryResult result;
        try {
            result = futureQuery.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while attempting to navigate to an element.", ex);
            return null;
        } catch (ExecutionException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while attempting to navigate to an element.", ex);
            return null;
        } catch (TimeoutException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while attempting to navigate to an element.", ex);
            return null;
        }

        List<? extends CompletionItem> results = result.getResults();
        if (results == null) {
            return null;
        }

        for (CompletionItem completionItem : results) {
            if (!(completionItem instanceof GoCompletionItem)) {
                continue;
            }
            
            GoCompletionItem item = (GoCompletionItem)completionItem;
            CodeElementModel model = item.getCodeElementModel();
            if (model == null) {
                continue;
            }

            JTextComponent target = model.navigateTo();
            if (target != null) {
                return target;
            }
        }

        return null;
    }

}
