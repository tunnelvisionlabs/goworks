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
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypeReferenceModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeReferenceModelImpl extends TypeModelImpl implements TypeReferenceModel {
    private final String referencedPackageName;
    private final String referencedTypeName;

    public TypeReferenceModelImpl(String referencedPackageName, String referencedTypeName, FileModelImpl fileModel) {
        super(getQualifiedName(referencedPackageName, referencedTypeName), fileModel, null, null);
        this.referencedPackageName = referencedPackageName;
        this.referencedTypeName = referencedTypeName;
    }

    @Override
    public TypeKind getKind() {
        Collection<? extends TypeModelImpl> resolved = resolve();
        if (resolved.isEmpty()) {
            return TypeKind.UNKNOWN;
        }

        return resolved.iterator().next().getKind();
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
    public Collection<? extends TypeModelImpl> resolve() {
        if (referencedPackageName == null || referencedPackageName.isEmpty()) {
            // check for built-in types
            TypeModel intrinsicType = IntrinsicTypeModels.getIntrinsicType(referencedTypeName);
            if (intrinsicType != null) {
                return Collections.singletonList((TypeModelImpl)intrinsicType);
            }
        }

        CodeModelCacheImpl cache = CodeModelCacheImpl.getInstance();
        List<PackageModelImpl> packages = new ArrayList<>();

        FileModelImpl file = getFile();
        if (file != null) {
            for (ImportDeclarationModel importModel : file.getImportDeclarations()) {
                boolean include = false;
                if (referencedPackageName != null && !importModel.isMergeWithLocal() && referencedPackageName.equals(importModel.getName())) {
                    include = true;
                } else if (referencedPackageName == null && importModel.isMergeWithLocal()) {
                    include = true;
                }

                if (include) {
                    packages.addAll(cache.resolvePackages(importModel));
                }
            }
        }

        if (referencedPackageName == null) {
            packages.add(getPackage());
        }

        Collection<TypeModelImpl> resolved = new ArrayList<>();
        for (PackageModelImpl packageModel : packages) {
            resolved.addAll(packageModel.getTypes(referencedTypeName));
        }

        return resolved;
    }

    @Override
    public boolean isResolved() {
        return false;
    }

    @Override
    public Collection<FieldModelImpl> getFields() {
        Collection<? extends TypeModelImpl> resolved = resolve();
        if (resolved == null || resolved.isEmpty()) {
            return Collections.emptyList();
        }

        if (resolved.size() == 1) {
            TypeModelImpl model = resolved.iterator().next();
            return model.getFields();
        } else {
            List<FieldModelImpl> elements = new ArrayList<>();
            for (TypeModelImpl model : resolved) {
                elements.addAll(model.getFields());
            }

            return elements;
        }
    }

    @Override
    public Collection<FunctionModelImpl> getMethods() {
        Collection<? extends TypeModelImpl> resolved = resolve();
        if (resolved == null || resolved.isEmpty()) {
            return Collections.emptyList();
        }

        if (resolved.size() == 1) {
            TypeModelImpl model = resolved.iterator().next();
            return model.getMethods();
        } else {
            List<FunctionModelImpl> elements = new ArrayList<>();
            for (TypeModelImpl model : resolved) {
                elements.addAll(model.getMethods());
            }

            return elements;
        }
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        Collection<? extends TypeModelImpl> resolved = resolve();
        if (resolved == null || resolved.isEmpty()) {
            return Collections.emptyList();
        }

        if (resolved.size() == 1) {
            TypeModelImpl model = resolved.iterator().next();
            return model.getMembers();
        } else {
            List<AbstractCodeElementModel> elements = new ArrayList<>();
            for (TypeModelImpl model : resolved) {
                elements.addAll(model.getMembers());
            }

            return elements;
        }
    }
}
