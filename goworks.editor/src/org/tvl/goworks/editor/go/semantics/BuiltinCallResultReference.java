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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class BuiltinCallResultReference extends CodeElementReference {

    private final Token name;
    private final CodeElementReference typeArgument;

    public BuiltinCallResultReference(Token name, CodeElementReference typeArgument) {
        this.name = name;
        this.typeArgument = typeArgument;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        String function = name.getText();
        if (function.equals("len") || function.equals("cap") || function.equals("copy")) {
            return BuiltinTypeReference.INT.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        } else if (function.equals("real") || function.equals("imag")) {
            // TODO: handle 32 vs 64 bit issues
            return BuiltinTypeReference.FLOAT32.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        } else if (function.equals("complex")) {
            // TODO: handle 32 vs 64 bit issues
            return BuiltinTypeReference.COMPLEX64.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        } else if (function.equals("new")) {

            Collection<? extends CodeElementModel> types = typeArgument.resolve(annotatedParseTree, currentPackage, resolvedPackages);
            List<CodeElementModel> pointerTypes = new ArrayList<CodeElementModel>();
            for (CodeElementModel model : types) {
                if (!(model instanceof TypeModelImpl)) {
                    continue;
                }

                TypeModelImpl typeModel = (TypeModelImpl)model;
                pointerTypes.add(new TypePointerModelImpl(typeModel));
            }

            return pointerTypes;

        } else if (function.equals("make")) {

            return typeArgument.resolve(annotatedParseTree, currentPackage, resolvedPackages);

        } else if (function.equals("append")) {

            Collection<? extends CodeElementModel> argumentType = typeArgument.resolve(annotatedParseTree, currentPackage, resolvedPackages);
            throw new UnsupportedOperationException("Not supported yet.");

        } else if (function.equals("panic") || function.equals("recover")) {
            return Collections.emptyList();
        } else if (function.equals("print") || function.equals("println")) {
            return Collections.emptyList();
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

}
