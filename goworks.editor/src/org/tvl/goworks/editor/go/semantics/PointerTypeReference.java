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
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class PointerTypeReference extends CodeElementReference {

    private final CodeElementReference elementType;

    public PointerTypeReference(CodeElementReference elementType) {
        this.elementType = elementType;
    }

    public CodeElementReference getElementType() {
        return elementType;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> elementTypes = elementType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        if (elementTypes.isEmpty()) {
            return elementTypes;
        }

        List<CodeElementModel> pointerTypes = new ArrayList<CodeElementModel>();
        for (CodeElementModel model : elementTypes) {
            if (!(model instanceof TypeModelImpl)) {
                continue;
            }

            TypeModelImpl typeModel = (TypeModelImpl)model;
            pointerTypes.add(new TypePointerModelImpl(typeModel));
        }

        return pointerTypes;
    }

}
