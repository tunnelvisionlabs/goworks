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
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class LiteralTypeReference extends CodeElementReference {

    private final Token literalToken;

    public LiteralTypeReference(Token literalToken) {
        this.literalToken = literalToken;
    }

    public Token getLiteralToken() {
        return literalToken;
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
