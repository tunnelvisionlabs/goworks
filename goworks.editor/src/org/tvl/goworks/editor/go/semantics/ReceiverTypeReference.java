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
import java.util.logging.Level;
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class ReceiverTypeReference extends CodeElementReference {

    private final Token name;
    private final boolean pointer;

    public ReceiverTypeReference(Token name, boolean pointer) {
        this.name = name;
        this.pointer = pointer;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Token localTarget = annotatedParseTree.getTokenDecorator().getProperty(name, GoAnnotations.LOCAL_TARGET);
        if (localTarget != null) {
            LOGGER.log(Level.WARNING, "Unable to resolve receiver type from the current file. Attempting cache lookup.");
        }

        Collection<? extends CodeElementModel> types = currentPackage.getTypes(name.getText());
        if (!pointer) {
            return types;
        }

        List<CodeElementModel> pointerTypes = new ArrayList<CodeElementModel>();
        for (CodeElementModel model : types) {
            if (!(model instanceof TypeModelImpl)) {
                continue;
            }

            TypeModelImpl typeModel = (TypeModelImpl)model;
            pointerTypes.add(new TypePointerModelImpl(typeModel));
        }

        return pointerTypes;
    }

}
