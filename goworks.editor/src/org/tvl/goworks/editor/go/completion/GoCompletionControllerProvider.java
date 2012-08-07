/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionController;
import org.netbeans.spi.editor.completion.CompletionControllerProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=CompletionControllerProvider.class)
public class GoCompletionControllerProvider implements CompletionControllerProvider {

    @Override
    public CompletionController createController(JTextComponent component, List<? extends CompletionTask> tasks, List<Integer> queryTypes) {
        return new GoCompletionController(component, tasks, queryTypes);
    }

}
