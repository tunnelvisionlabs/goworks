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
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class SliceExpressionTypeReference extends CodeElementReference {

    private final CodeElementReference expression;

    public SliceExpressionTypeReference(CodeElementReference expression) {
        this.expression = expression;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        List<CodeElementModel> resolved = new ArrayList<CodeElementModel>(expression.resolve(annotatedParseTree, currentPackage, resolvedPackages));
        for (int i = 0; i < resolved.size(); i++) {
            CodeElementModel model = resolved.get(i);
            if (!(model instanceof TypeModelImpl)) {
                continue;
            }

            resolved.set(i, new TypeSliceModelImpl((TypeModelImpl)model));
        }

        return resolved;
    }

}
