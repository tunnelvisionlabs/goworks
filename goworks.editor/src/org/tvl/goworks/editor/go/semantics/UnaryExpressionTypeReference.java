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
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
public class UnaryExpressionTypeReference extends CodeElementReference {

    private final CodeElementReference expression;
    private final Token operator;

    public UnaryExpressionTypeReference(CodeElementReference expression, Token operator) {
        this.expression = expression;
        this.operator = operator;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        if (operator.getType() == GoParser.Amp) {
            // address of
            List<CodeElementModel> result = new ArrayList<>();
            result.addAll(expression.resolve(annotatedParseTree, currentPackage, resolvedPackages));
            for (int i = 0; i < result.size(); i++) {
                CodeElementModel codeElementModel = result.get(i);
                if (!(codeElementModel instanceof TypeModelImpl)) {
                    continue;
                }

                TypeModelImpl typeModelImpl = (TypeModelImpl)codeElementModel;
                result.set(i, new TypePointerModelImpl(typeModelImpl));
            }

            return result;
        } else if (operator.getType() == GoParser.Star) {
            // dereference
            List<CodeElementModel> result = new ArrayList<>();
            result.addAll(expression.resolve(annotatedParseTree, currentPackage, resolvedPackages));
            for (int i = 0; i < result.size(); i++) {
                CodeElementModel codeElementModel = result.get(i);
                if (!(codeElementModel instanceof TypePointerModel)) {
                    continue;
                }

                TypePointerModel typePointerModel = (TypePointerModel)codeElementModel;
                result.set(i, typePointerModel.getElementType());
            }

            return result;
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

}
