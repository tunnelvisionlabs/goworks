/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.Collection;
import java.util.Collections;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypeWrapperModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class TypeModelImpl extends AbstractCodeElementModel implements TypeModel {

    public TypeModelImpl(String name, FileModelImpl fileModel) {
        super(name, fileModel);
    }

    @Override
    public Collection<VarModelImpl> getFields() {
        return Collections.emptyList();
    }

    @Override
    public Collection<VarModelImpl> getFields(String name) {
        return CodeModelCacheImpl.findElementsByName(getFields(), name);
    }

    @Override
    public Collection<FunctionModelImpl> getMethods() {
        return Collections.emptyList();
    }

    @Override
    public Collection<FunctionModelImpl> getMethods(String name) {
        return CodeModelCacheImpl.findElementsByName(getMethods(), name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeWrapperModel)) {
            return false;
        }

        TypeWrapperModel other = (TypeWrapperModel)obj;
        return getKind().equals(other.getKind())
            && getPackage().equals(other.getPackage())
            && getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 7 * hashCode + getKind().hashCode();
        hashCode = 7 * hashCode + getPackage().hashCode();
        hashCode = 7 * hashCode + getName().hashCode();
        return hashCode;
    }

}
