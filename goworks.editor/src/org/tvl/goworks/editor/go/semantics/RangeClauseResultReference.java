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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicKind;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeArrayModel;
import org.tvl.goworks.editor.go.codemodel.TypeChannelModel;
import org.tvl.goworks.editor.go.codemodel.TypeIntrinsicModel;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public class RangeClauseResultReference extends CodeElementReference {

    private final CodeElementReference exprType;

    public RangeClauseResultReference(@NonNull CodeElementReference exprType) {
        Parameters.notNull("exprType", exprType);
        this.exprType = exprType;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> indexableType = exprType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        if (indexableType.isEmpty()) {
            return indexableType;
        }

        List<CodeElementModel> resolvedTypes = new ArrayList<>();
        for (CodeElementModel model : indexableType) {
            if (model instanceof VarModel) {
                model = ((VarModel)model).getVarType();
            }

            if (model instanceof TypeModel) {
                resolvedTypes.addAll(((TypeModel)model).resolve());
            } else {
                resolvedTypes.add(model);
            }
        }

        Collection<CodeElementModel> elements = new ArrayList<>();
        for (CodeElementModel model : resolvedTypes) {
            if (!(model instanceof TypeModel)) {
                elements.add(model);
                continue;
            }

            while (model instanceof TypeAliasModel) {
                model = ((TypeAliasModel)model).getType();
            }

            if (model instanceof TypeChannelModel) {
                // only has a first value
                elements.add(((TypeChannelModel)model).getElementType());
            } else if (model instanceof TypeMapModel) {
                AbstractCodeElementModel firstValue = (AbstractCodeElementModel)((TypeMapModel)model).getKeyType();
                AbstractCodeElementModel secondValue = (AbstractCodeElementModel)((TypeMapModel)model).getValueType();
                elements.add(new BundledReturnTypeModel(Arrays.asList(firstValue, secondValue)));
            } else if (model instanceof TypeIntrinsicModel) {
                if (((TypeIntrinsicModel)model).getIntrinsicKind() == IntrinsicKind.STRING) {
                    elements.add(new BundledReturnTypeModel(Arrays.asList((AbstractCodeElementModel)IntrinsicTypeModels.INT, (AbstractCodeElementModel)IntrinsicTypeModels.RUNE)));
                }
            } else {
                CodeElementModel unwrappedModel = model;
                if (unwrappedModel instanceof TypePointerModel) {
                    Collection<? extends CodeElementModel> resolvedElement = ((TypePointerModel)unwrappedModel).getElementType().resolve();
                    if (resolvedElement.isEmpty()) {
                        continue;
                    }

                    if (resolvedElement.size() > 0) {
                        LOGGER.log(Level.WARNING, "Pointer element type resolved to multiple targets, using the first.");
                    }

                    unwrappedModel = resolvedElement.iterator().next();
                }

                if (unwrappedModel instanceof TypeArrayModel) {
                    unwrappedModel = ((TypeArrayModel)unwrappedModel).getElementType();
                } else if (unwrappedModel instanceof TypeSliceModel) {
                    unwrappedModel = ((TypeSliceModel)unwrappedModel).getElementType();
                } else {
                    unwrappedModel = null;
                }

                if (unwrappedModel != null) {
                    elements.add(new BundledReturnTypeModel(Arrays.asList((AbstractCodeElementModel)IntrinsicTypeModels.INT, (AbstractCodeElementModel)unwrappedModel)));
                }
            }
        }

        return elements;
    }

}
