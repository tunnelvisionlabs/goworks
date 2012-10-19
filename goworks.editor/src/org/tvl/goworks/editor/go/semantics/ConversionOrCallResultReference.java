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
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;
import org.tvl.goworks.editor.go.codemodel.impl.FunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeFunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VarModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class ConversionOrCallResultReference extends CodeElementReference {

    private final CodeElementReference typeOrMethod;

    public ConversionOrCallResultReference(CodeElementReference typeOrMethod) {
        this.typeOrMethod = typeOrMethod;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> methods = typeOrMethod.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        List<CodeElementModel> result = new ArrayList<CodeElementModel>();
        for (CodeElementModel model : methods) {
            if (model instanceof VarModelImpl) {
                model = ((VarModelImpl)model).getVarType();
            }

            if (model instanceof FunctionModel) {
                Collection<? extends AbstractCodeElementModel> returnValues;
                if (model instanceof FunctionModelImpl) {
                    returnValues = ((FunctionModelImpl)model).getReturnValues();
                } else if (model instanceof TypeFunctionModelImpl) {
                    returnValues = ((TypeFunctionModelImpl)model).getReturnValues();
                } else {
                    continue;
                }

                if (returnValues.isEmpty()) {
                    continue;
                }

                if (returnValues.size() == 1) {
                    result.addAll(returnValues);
                } else {
                    result.add(new BundledReturnTypeModel(returnValues));
                }
            } else if (model instanceof TypeModelImpl) {
                // this was actually a type cast
                Collection<AbstractCodeElementModel> resultValues = new ArrayList<AbstractCodeElementModel>();
                resultValues.add((AbstractCodeElementModel)model);
                resultValues.add((AbstractCodeElementModel)BuiltinTypeReference.BOOL.resolve());
                result.add(new BundledReturnTypeModel(resultValues));
            }
        }

        return result;
    }

}
