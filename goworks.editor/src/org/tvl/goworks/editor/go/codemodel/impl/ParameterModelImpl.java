/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.VarKind;

/**
 *
 * @author Sam Harwell
 */
public class ParameterModelImpl extends VarModelImpl implements ParameterModel {

    public ParameterModelImpl(String name, VarKind kind, TypeModel varType, FileModelImpl file) {
        super(name, kind, varType, file);
    }

}
