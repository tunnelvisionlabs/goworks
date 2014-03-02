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
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeSliceModelImpl extends TypeWrapperModelImpl implements TypeSliceModel {

    public TypeSliceModelImpl(TypeModelImpl elementType) {
        super("[]" + elementType.getName(), elementType);
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        if (isResolved()) {
            return Collections.singletonList(this);
        }

        List<TypeModelImpl> resolved = new ArrayList<>(getElementType().resolve());
        for (int i = 0; i < resolved.size(); i++) {
            resolved.set(i, new TypeSliceModelImpl(resolved.get(i)));
        }

        return resolved;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.SLICE;
    }

    @Override
    public String getSimpleName() {
        return "[]" + getElementType().getSimpleName();
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeSliceModel)) {
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
