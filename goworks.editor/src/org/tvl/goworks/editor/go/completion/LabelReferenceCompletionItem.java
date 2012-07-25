/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class LabelReferenceCompletionItem extends GoCompletionItem {

    private final String labelName;
    private String leftText;

    public LabelReferenceCompletionItem(@NonNull String labelName) {
        Parameters.notNull("labelName", labelName);
        this.labelName = labelName;
    }

    @Override
    public int getSortPriority() {
        return LABEL_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return labelName;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return labelName;
    }

    @Override
    public String getToolTipText() {
        return "label " + labelName;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(LABEL_COLOR);
            builder.append(labelName);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        return "Label";
    }

}
