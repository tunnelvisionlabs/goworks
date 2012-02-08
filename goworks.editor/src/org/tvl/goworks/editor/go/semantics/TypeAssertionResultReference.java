/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;
import org.tvl.goworks.editor.go.completion.GoCompletionQuery.UnknownTypeModelImpl;

/**
 *
 * @author sam
 */
public class TypeAssertionResultReference extends CodeElementReference {

    private final CodeElementReference type;

    public TypeAssertionResultReference(CodeElementReference type) {
        this.type = type;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> result = type.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        if (result.isEmpty()) {
            return result;
        }

        List<CodeElementModel> bundled = new ArrayList<CodeElementModel>();
        for (CodeElementModel model : result) {
            bundled.add(new BundledReturnTypeModel(Arrays.asList((AbstractCodeElementModel)model, new UnknownTypeModelImpl(((AbstractCodeElementModel)model).getFile()))));
        }

        return bundled;
    }

}
