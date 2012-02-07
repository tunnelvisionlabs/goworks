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
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.codemodel.TypeArrayModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeArrayModelImpl extends TypeWrapperModelImpl implements TypeArrayModel {

    public TypeArrayModelImpl(@NonNull TypeModelImpl elementType) {
        super("[...]" + elementType.getName(), elementType);
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.ARRAY;
    }

    @Override
    public String getSimpleName() {
        return "[...]" + getElementType().getSimpleName();
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeArrayModel)) {
            return false;
        }

        return super.equals(obj);
    }

}
