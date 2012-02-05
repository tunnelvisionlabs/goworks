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
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public class ChannelTypeReference extends CodeElementReference {

    private final CodeElementReference elementType;
    private final ChannelKind kind;

    public ChannelTypeReference(CodeElementReference elementType, ChannelKind kind) {
        this.elementType = elementType;
        this.kind = kind;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
