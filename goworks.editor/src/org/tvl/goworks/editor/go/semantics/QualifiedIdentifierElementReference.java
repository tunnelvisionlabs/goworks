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
import java.util.logging.Level;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;

/**
 *
 * @author Sam Harwell
 */
public class QualifiedIdentifierElementReference extends CodeElementReference {

    private final qualifiedIdentifierContext context;

    public QualifiedIdentifierElementReference(qualifiedIdentifierContext context) {
        this.context = context;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        if (context.pkg != null) {
            if (context.id == null) {
                return Collections.emptyList();
            }

            String memberName = context.id.getText();
            Collection<PackageModel> packages = resolvedPackages.get(context.pkg.name.getText());
            if (packages == null || packages.isEmpty()) {
                return Collections.emptyList();
            }

            List<CodeElementModel> results = new ArrayList<CodeElementModel>();
            for (PackageModel model : packages) {
                results.addAll(model.getMembers(memberName));
            }

            return results;
        } else if (context.id != null) {
            Collection<? extends CodeElementModel> result = annotatedParseTree.getTokenDecorator().getProperty(context.id, GoAnnotations.MODELS);
            if (result != null) {
                return result;
            }

            Token decl = annotatedParseTree.getTokenDecorator().getProperty(context.id, GoAnnotations.LOCAL_TARGET);
            if (decl != null) {
                result = annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.MODELS);
                if (result != null) {
                    return result;
                }
            }

            if (decl == null || annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.NODE_TYPE) == NodeType.TYPE_DECL) {
                result = currentPackage.getMembers(context.id.getText());
                Collection<PackageModel> mergePackages = resolvedPackages.get("");
                if (mergePackages != null && !mergePackages.isEmpty()) {
                    List<CodeElementModel> combinedResults = new ArrayList<CodeElementModel>(result);
                    for (PackageModel otherPackage : mergePackages) {
                        combinedResults.addAll(otherPackage.getMembers(context.id.getText()));
                    }

                    result = combinedResults;
                }

                if (decl == null || !result.isEmpty()) {
                    return result;
                }
            }

            NodeType nodeType = annotatedParseTree.getNodeType(context.id);
            switch (nodeType) {
            case VAR_REF:
            {
                CodeElementReference varType = annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.EXPR_TYPE);
                if (varType == null) {
                    ParseTree explicitType = annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.EXPLICIT_TYPE);
                    if (explicitType != null) {
                        varType = annotatedParseTree.getTreeDecorator().getProperty(explicitType, GoAnnotations.CODE_CLASS);
                        if (annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.VARIADIC)) {
                            varType = new VariadicParameterTypeReference(varType);
                        }
                    } else {
                        ParseTree implicitType = annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.IMPLICIT_TYPE);
                        if (implicitType == null) {
                            LOGGER.log(Level.FINE, "Unable to find an explicit or implicit type for the tree.");
                            return Collections.emptyList();
                        }

                        varType = annotatedParseTree.getTreeDecorator().getProperty(implicitType, GoAnnotations.EXPR_TYPE);
                    }

                    if (varType == null) {
                        LOGGER.log(Level.FINE, String.format("Unable to resolve the type of var declared with token: '%s'.", decl.toString()));
                        return Collections.emptyList();
                    }

                    annotatedParseTree.getTokenDecorator().putProperty(decl, GoAnnotations.EXPR_TYPE, varType);
                }

                Collection<? extends CodeElementModel> resolved = varType.resolve(annotatedParseTree, currentPackage, resolvedPackages);

                int implicitIndex = annotatedParseTree.getTokenDecorator().getProperty(decl, GoAnnotations.IMPLICIT_INDEX);
                if (implicitIndex >= 0) {
                    Collection<CodeElementModel> unwrapped = new ArrayList<CodeElementModel>();
                    for (CodeElementModel model : resolved) {
                        if (model instanceof BundledReturnTypeModel) {
                            BundledReturnTypeModel bundled = (BundledReturnTypeModel)model;
                            if (bundled.getReturnValues().size() > implicitIndex) {
                                unwrapped.add(bundled.getReturnValues().get(implicitIndex));
                            }
                        } else if (implicitIndex == 0) {
                            unwrapped.add(model);
                        }
                    }

                    resolved = unwrapped;
                }

                return resolved;
            }

            case FUNC_REF:
            {
                ArrayList<CodeElementModel> resolved = new ArrayList<CodeElementModel>();
                resolved.addAll(currentPackage.getFunctions(context.id.getText()));
                Collection<? extends PackageModel> mergedImports = resolvedPackages.get("");
                if (mergedImports != null) {
                    for (PackageModel model : mergedImports) {
                        resolved.addAll(model.getFunctions(context.id.getText()));
                    }
                }

                for (int i = resolved.size() - 1; i >= 0; i--) {
                    CodeElementModel element = resolved.get(i);
                    if (element instanceof FunctionModel && ((FunctionModel)element).isMethod()) {
                        resolved.remove(i);
                    }
                }

                return resolved;
            }

            case TYPE_REF:
                throw new UnsupportedOperationException("Not supported yet.");

            default:
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        return Collections.emptyList();
    }

}
