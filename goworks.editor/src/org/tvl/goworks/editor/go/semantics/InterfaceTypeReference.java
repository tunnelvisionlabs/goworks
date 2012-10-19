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
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeInterfaceModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class InterfaceTypeReference extends CodeElementReference {
    private final FileModelImpl fileModel;

    public InterfaceTypeReference(@NonNull FileModelImpl fileModel) {
        Parameters.notNull("fileModel", fileModel);
        this.fileModel = fileModel;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        TypeInterfaceModelImpl model = new TypeInterfaceModelImpl("?", fileModel, null);
        model.freeze();
        return Collections.singletonList(model);
    }

}
