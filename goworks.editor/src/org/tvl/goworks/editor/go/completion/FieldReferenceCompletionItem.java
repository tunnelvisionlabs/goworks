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
public class FieldReferenceCompletionItem extends VarReferenceCompletionItem {

    public FieldReferenceCompletionItem(String varName) {
        super(varName, false);
    }

    public FieldReferenceCompletionItem(VarModel varModel) {
        super(varModel, false);
    }

}
