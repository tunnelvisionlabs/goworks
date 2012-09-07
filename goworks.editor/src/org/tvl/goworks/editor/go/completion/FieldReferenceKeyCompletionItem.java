/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public class FieldReferenceKeyCompletionItem extends FieldReferenceCompletionItem {
    private String leftText;

    public FieldReferenceKeyCompletionItem(String varName) {
        super(varName);
    }

    public FieldReferenceKeyCompletionItem(VarModel varModel) {
        super(varModel);
    }

    @Override
    public CharSequence getInsertPrefix() {
        return super.getInsertPrefix() + ":";
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(FIELD_COLOR);
            builder.append(getVarName());
            builder.append(COLOR_END);
            builder.append(":");
            leftText = builder.toString();
        }
        return leftText;
    }

}
