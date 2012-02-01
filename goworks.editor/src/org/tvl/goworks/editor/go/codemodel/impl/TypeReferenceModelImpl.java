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
import org.tvl.goworks.editor.go.codemodel.TypeReferenceModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeReferenceModelImpl extends TypeModelImpl implements TypeReferenceModel {
    private final String referencedPackageName;
    private final String referencedTypeName;

    public TypeReferenceModelImpl(String referencedPackageName, String referencedTypeName, FileModelImpl fileModel) {
        super(getQualifiedName(referencedPackageName, referencedTypeName), fileModel);
        this.referencedPackageName = referencedPackageName;
        this.referencedTypeName = referencedTypeName;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.UNKNOWN;
    }

    @Override
    public String getSimpleName() {
        return getName();
    }

    private static String getQualifiedName(String packageName, String typeName) {
        if (packageName == null || packageName.isEmpty()) {
            return typeName;
        }

        return packageName + "." + typeName;
    }

    @Override
    public TypeModelImpl resolve() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        TypeModelImpl resolved = resolve();
        if (resolved != null) {
            return resolved.getMembers();
        }

        return Collections.emptyList();
    }
}
