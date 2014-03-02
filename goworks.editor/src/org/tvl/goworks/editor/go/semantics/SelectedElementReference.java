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
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public class SelectedElementReference extends CodeElementReference {

    private final CodeElementReference expression;
    private final Token name;

    public SelectedElementReference(CodeElementReference expression, Token name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> resolvedExpression = expression.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        if (resolvedExpression.isEmpty()) {
            return resolvedExpression;
        }

        String nameText = name.getText();
        List<CodeElementModel> results = new ArrayList<>();
        for (CodeElementModel model : resolvedExpression) {
            results.addAll(SemanticAnalyzer.getSelectableMembers(model, nameText));
        }

        return results;
    }

}
