/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.Collection;
import java.util.Map;
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public class BinaryExpressionTypeReference extends CodeElementReference {

    private final CodeElementReference left;
    private final Token operator;
    private final CodeElementReference right;

    public BinaryExpressionTypeReference(CodeElementReference left, Token operator, CodeElementReference right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
