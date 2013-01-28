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
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;

/**
 *
 * @author Sam Harwell
 */
public class UnqualifiedIdentifierElementReference extends CodeElementReference {

    @NonNull
    private final TerminalNode<Token> identifier;

    public UnqualifiedIdentifierElementReference(@NonNull TerminalNode<Token> identifier) {
        this.identifier = identifier;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        if (identifier != null) {
            TypeModel intrinsicType = IntrinsicTypeModels.getIntrinsicType(identifier.getText());
            if (intrinsicType != null) {
                return Collections.singletonList(intrinsicType);
            }

            Collection<? extends CodeElementModel> result = annotatedParseTree.getTreeDecorator().getProperty(identifier, GoAnnotations.MODELS);
            if (result != null) {
                return result;
            }

            TerminalNode<? extends Token> decl;
            if (annotatedParseTree.getTreeDecorator().getProperty(identifier, GoAnnotations.EXPLICIT_TYPE) != null) {
                decl = identifier;
            } else {
                decl = annotatedParseTree.getTreeDecorator().getProperty(identifier, GoAnnotations.LOCAL_TARGET);
            }

            if (decl != null) {
                result = annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.MODELS);
                if (result != null) {
                    return result;
                }
            }

            if (decl == null || annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.NODE_TYPE) == NodeType.TYPE_DECL) {
                result = currentPackage.getMembers(identifier.getSymbol().getText());
                Collection<PackageModel> mergePackages = resolvedPackages.get("");
                if (mergePackages != null && !mergePackages.isEmpty()) {
                    List<CodeElementModel> combinedResults = new ArrayList<CodeElementModel>(result);
                    for (PackageModel otherPackage : mergePackages) {
                        combinedResults.addAll(otherPackage.getMembers(identifier.getSymbol().getText()));
                    }

                    result = combinedResults;
                }

                if (decl == null || !result.isEmpty()) {
                    return result;
                }
            }

            NodeType nodeType = annotatedParseTree.getNodeType(identifier);
            switch (nodeType) {
            case VAR_REF:
            {
                CodeElementReference varType = annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.EXPR_TYPE);
                if (varType == CodeElementReference.MISSING) {
                    ParseTree<Token> explicitType = annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.EXPLICIT_TYPE);
                    if (explicitType != null) {
                        varType = annotatedParseTree.getTreeDecorator().getProperty(explicitType, GoAnnotations.CODE_CLASS);
                        if (annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.VARIADIC)) {
                            varType = new VariadicParameterTypeReference(varType);
                        }
                    } else {
                        ParseTree<Token> implicitType = annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.IMPLICIT_TYPE);
                        if (implicitType == null) {
                            LOGGER.log(Level.FINE, "Unable to find an explicit or implicit type for the tree.");
                            return Collections.emptyList();
                        }

                        varType = annotatedParseTree.getTreeDecorator().getProperty(implicitType, GoAnnotations.EXPR_TYPE);
                    }

                    if (varType == CodeElementReference.MISSING) {
                        LOGGER.log(Level.FINE, String.format("Unable to resolve the type of var declared with token: '%s'.", decl.toString()));
                        return Collections.emptyList();
                    }

                    annotatedParseTree.getTreeDecorator().putProperty(decl, GoAnnotations.EXPR_TYPE, varType);
                }

                Collection<? extends CodeElementModel> resolved = varType.resolve(annotatedParseTree, currentPackage, resolvedPackages);

                int implicitIndex = annotatedParseTree.getTreeDecorator().getProperty(decl, GoAnnotations.IMPLICIT_INDEX);
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
                resolved.addAll(currentPackage.getFunctions(identifier.getSymbol().getText()));
                Collection<? extends PackageModel> mergedImports = resolvedPackages.get("");
                if (mergedImports != null) {
                    for (PackageModel model : mergedImports) {
                        resolved.addAll(model.getFunctions(identifier.getSymbol().getText()));
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
