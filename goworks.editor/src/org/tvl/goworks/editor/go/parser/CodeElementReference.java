/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.tvl.goworks.editor.go.codemodel.CodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public class CodeElementReference {
    public static final CodeElementReference MISSING = new CodeElementReference();

    public boolean isDefinition() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public CodeElementModel resolve() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
