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
import org.tvl.goworks.editor.go.codemodel.IntrinsicKind;
import org.tvl.goworks.editor.go.codemodel.TypeIntrinsicModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeIntrinsicModelImpl extends TypeModelImpl implements TypeIntrinsicModel {

    private final IntrinsicKind kind;

    public TypeIntrinsicModelImpl(IntrinsicKind kind) {
        super(kind.toString().toLowerCase(), IntrinsicFileModelImpl.INSTANCE, null, null);
        this.kind = kind;
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        return Collections.singletonList(this);
    }

    @Override
    public boolean isResolved() {
        return true;
    }

    @Override
    public String getSimpleName() {
        return getName();
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.INTRINSIC;
    }

    @Override
    public IntrinsicKind getIntrinsicKind() {
        return kind;
    }

}
