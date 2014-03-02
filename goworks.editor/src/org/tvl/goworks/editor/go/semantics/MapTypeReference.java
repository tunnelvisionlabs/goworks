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
import org.tvl.goworks.editor.go.codemodel.impl.TypeMapModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class MapTypeReference extends CodeElementReference {

    private final CodeElementReference keyType;
    private final CodeElementReference valueType;

    public MapTypeReference(CodeElementReference keyType, CodeElementReference valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    public CodeElementReference getKeyType() {
        return keyType;
    }

    public CodeElementReference getValueType() {
        return valueType;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> resolvedKeys = keyType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        Collection<? extends CodeElementModel> resolvedValues = valueType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        List<CodeElementModel> resolved = new ArrayList<>();
        for (CodeElementModel keyModel : resolvedKeys) {
            if (!(keyModel instanceof TypeModelImpl)) {
                continue;
            }

            for (CodeElementModel valueModel : resolvedValues) {
                if (!(valueModel instanceof TypeModelImpl)) {
                    continue;
                }

                resolved.add(new TypeMapModelImpl((TypeModelImpl)keyModel, (TypeModelImpl)valueModel));
            }
        }

        for (CodeElementModel keyModel : resolvedKeys) {
            if (!(keyModel instanceof TypeModelImpl)) {
                resolved.add(keyModel);
            }
        }

        for (CodeElementModel valueModel : resolvedValues) {
            if (!(valueModel instanceof TypeModelImpl)) {
                resolved.add(valueModel);
            }
        }

        return resolved;
    }

}
