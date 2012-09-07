/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.util.prefs.Preferences;
import org.antlr.netbeans.editor.formatting.CategorySupport.PreviewFormatter;

/**
 *
 * @author Sam Harwell
 */
public class GoPreviewFormatter implements PreviewFormatter {
    public static final GoPreviewFormatter INSTANCE = new GoPreviewFormatter();

    public GoPreviewFormatter() {
    }

    @Override
    public String reformat(String text, Preferences preferences) {
        return GoReformatTask.reformat(text, GoFormatOptions.codeStyleFactory.create(preferences));
    }

}
