/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.formatters;

import org.tvl.goworks.editor.go.codemodel.CodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public class SimpleCodeModelFormatter implements CodeModelFormatter {

    @Override
    public String format(CodeElementModel model) {
        return model.getName();
    }
}
