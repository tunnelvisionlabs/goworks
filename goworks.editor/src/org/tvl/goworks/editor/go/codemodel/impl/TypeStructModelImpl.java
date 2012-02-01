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
import org.tvl.goworks.editor.go.codemodel.StructModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeStructModelImpl extends TypeModelImpl implements StructModel {
    private final FreezableArrayList<VarModelImpl> fields = new FreezableArrayList<VarModelImpl>();

    public TypeStructModelImpl(String name, FileModelImpl fileModel) {
        super(name, fileModel);
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.STRUCT;
    }

    @Override
    public String getSimpleName() {
        return "struct{...}";
    }

    @Override
    public Collection<VarModelImpl> getFields() {
        return fields;
    }

    @Override
    public Collection<VarModelImpl> getFields(String name) {
        return CodeModelCacheImpl.findElementsByName(getFields(), name);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    protected void freezeImpl() {
        fields.freeze();
        super.freezeImpl();
    }

}
