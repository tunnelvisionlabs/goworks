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
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.QualifiedIdentifierContext;

/**
 *
 * @author Sam Harwell
 */
public class QualifiedIdentifierElementReference extends CodeElementReference {

    private final QualifiedIdentifierContext context;

    public QualifiedIdentifierElementReference(QualifiedIdentifierContext context) {
        this.context = context;
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0, dependents=Dependents.SELF),
    })
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        if (context.packageName() != null) {
            if (context.IDENTIFIER() == null) {
                return Collections.emptyList();
            }

            String memberName = context.IDENTIFIER().getSymbol().getText();
            Collection<PackageModel> packages = resolvedPackages.get(context.packageName().IDENTIFIER().getSymbol().getText());
            if (packages == null || packages.isEmpty()) {
                return Collections.emptyList();
            }

            List<CodeElementModel> results = new ArrayList<CodeElementModel>();
            for (PackageModel model : packages) {
                results.addAll(model.getMembers(memberName));
            }

            return results;
        } else if (context.IDENTIFIER() != null) {
            return new UnqualifiedIdentifierElementReference(context.IDENTIFIER()).resolve(annotatedParseTree, currentPackage, resolvedPackages);
        }

        return Collections.emptyList();
    }
}
