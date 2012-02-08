/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.tvl.goworks.editor.go.codemodel.TypeModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class TypeModelImpl extends AbstractCodeElementModel implements TypeModel {

    public TypeModelImpl(String name, FileModelImpl fileModel) {
        super(name, fileModel);
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        return Collections.singletonList(this);
    }

    public abstract boolean isResolved();

    @Override
    public Collection<FieldModelImpl> getFields() {
        return Collections.emptyList();
    }

    @Override
    public Collection<FieldModelImpl> getFields(String name) {
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
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        members.addAll(getFields());
        members.addAll(getMethods());
        return members;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(String name) {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        members.addAll(getFields(name));
        members.addAll(getMethods(name));
        return members;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeModel)) {
            return false;
        }

        TypeModel other = (TypeModel)obj;
        return getKind().equals(other.getKind())
            && getPackage().equals(other.getPackage())
            && getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 7 * hashCode + getKind().hashCode();
        PackageModelImpl packageModel = getPackage();
        hashCode = 7 * hashCode + (packageModel != null ? packageModel.hashCode() : 0);
        hashCode = 7 * hashCode + getName().hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return getName();
    }

}
