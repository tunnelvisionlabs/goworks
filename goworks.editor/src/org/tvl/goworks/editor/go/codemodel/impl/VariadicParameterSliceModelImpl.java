/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

/**
 *
 * @author Sam Harwell
 */
public class VariadicParameterSliceModelImpl extends TypeSliceModelImpl {

    public VariadicParameterSliceModelImpl(TypeModelImpl elementType, FileModelImpl fileModel) {
        super(elementType, fileModel);
    }

    @Override
    public String getSimpleName() {
        return "..." + getElementType().getSimpleName();
    }

}
