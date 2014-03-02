/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeArrayModel;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public class ArrayElementTypeReference extends CodeElementReference {

    private final CodeElementReference arrayType;

    public ArrayElementTypeReference(CodeElementReference arrayType) {
        this.arrayType = arrayType;
    }
    
    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> indexableType = arrayType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        if (indexableType.isEmpty()) {
            return indexableType;
        }

        List<CodeElementModel> resolvedTypes = new ArrayList<>();
        for (CodeElementModel model : indexableType) {
            if (model instanceof VarModel) {
                model = ((VarModel)model).getVarType();
            }

            if (model instanceof TypeModel) {
                resolvedTypes.addAll(((TypeModel)model).resolve());
            } else {
                resolvedTypes.add(model);
            }
        }

        Collection<CodeElementModel> elements = new ArrayList<>();
        for (CodeElementModel arrayModel : resolvedTypes) {
            if (!(arrayModel instanceof TypeModel)) {
                elements.add(arrayModel);
                continue;
            }

            while (arrayModel instanceof TypeAliasModel) {
                arrayModel = ((TypeAliasModel)arrayModel).getType();
            }

            if (arrayModel instanceof TypePointerModel && ((TypePointerModel)arrayModel).getElementType() instanceof TypeArrayModel) {
                arrayModel = ((TypePointerModel)arrayModel).getElementType();
            }

            if (arrayModel instanceof TypeArrayModel) {
                elements.add(((TypeArrayModel)arrayModel).getElementType());
            } else if (arrayModel instanceof TypeSliceModel) {
                elements.add(((TypeSliceModel)arrayModel).getElementType());
            } else if (arrayModel instanceof TypeMapModel) {
                elements.add(((TypeMapModel)arrayModel).getValueType());
            }
        }

        return elements;
    }

}
