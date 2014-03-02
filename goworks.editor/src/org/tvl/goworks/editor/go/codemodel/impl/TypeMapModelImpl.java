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
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeMapModelImpl extends TypeModelImpl implements TypeMapModel {
    private final TypeModelImpl keyType;
    private final TypeModelImpl valueType;

    public TypeMapModelImpl(TypeModelImpl keyType, TypeModelImpl valueType) {
        super("map[" + keyType.getName() + "]" + valueType.getName(), keyType.getFile(), null, null);
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        if (isResolved()) {
            return Collections.singletonList(this);
        }

        Collection<? extends TypeModelImpl> resolvedKey = getKeyType().resolve();
        Collection<? extends TypeModelImpl> resolvedValue = getValueType().resolve();
        List<TypeModelImpl> resolved = new ArrayList<>();
        for (TypeModelImpl keyModel : resolvedKey) {
            for (TypeModelImpl valueModel : resolvedValue) {
                resolved.add(new TypeMapModelImpl(keyModel, valueModel));
            }
        }

        return resolved;
    }

    @Override
    public boolean isResolved() {
        return keyType.isResolved() && valueType.isResolved();
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
    public TypeModelImpl getKeyType() {
        return keyType;
    }

    @Override
    public TypeModelImpl getValueType() {
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
