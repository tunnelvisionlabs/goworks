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
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class CodeElementReference {
    protected static final Logger LOGGER = Logger.getLogger(CodeElementReference.class.getName());

    public static final CodeElementReference UNKNOWN = new Unknown();
    public static final CodeElementReference MISSING = new Unknown();

    public boolean isDefinition() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public abstract Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages);

    public static class Unknown extends CodeElementReference {

        @Override
        public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
            return Collections.emptyList();
        }

    }
}
