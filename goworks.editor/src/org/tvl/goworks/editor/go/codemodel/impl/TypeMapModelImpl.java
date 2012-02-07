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
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeMapModelImpl extends TypeModelImpl implements TypeMapModel {
    private final TypeModelImpl keyType;
    private final TypeModelImpl valueType;

    public TypeMapModelImpl(TypeModelImpl keyType, TypeModelImpl valueType) {
        super("map[" + keyType.getName() + "]" + valueType.getName(), keyType.getFile());
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.MAP;
    }

    @Override
    public String getSimpleName() {
        return String.format("map[%s]%s", getKeyType().getSimpleName(), getValueType().getSimpleName());
    }

    @Override
    public TypeModel getKeyType() {
        return keyType;
    }

    @Override
    public TypeModel getValueType() {
        return valueType;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    protected void freezeImpl() {
        keyType.freeze();
        valueType.freeze();
        super.freezeImpl();
    }

}
