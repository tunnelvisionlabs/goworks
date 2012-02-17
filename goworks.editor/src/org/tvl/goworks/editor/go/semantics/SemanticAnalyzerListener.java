/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.tvl.goworks.editor.go.codemodel.VarKind;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTree.TerminalNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.InterfaceModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeArrayModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeChannelModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeMapModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.GoParserBaseBaseListener;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.addAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.addExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.andExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.anonymousFieldContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.argumentListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayLengthContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.assignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.assignmentContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.basicLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.blockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.bodyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.breakStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinArgsContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.callExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.commCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.commClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compareExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compositeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conditionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.continueStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.declarationContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.deferStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementIndexContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.emptyStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fallthroughStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.forClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.forStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.goStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.gotoStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ifStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importPathContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.incDecStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.indexExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.initStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.keyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.keyTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalValueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mulAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.multExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.orExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parametersContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.pointerTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.postStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.rangeClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.recvExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.recvStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.returnStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectorExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sendStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.signatureContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.simpleStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.statementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.structTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.switchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.tagContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.topLevelDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.unaryExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.valueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;

/* TODO for QUALIFIED_EXPR and UNQUALIFIED_LINK
 *   +qualifiedIdentifier
 *   +typeName
 *   operand
 *   type
 *   literalType
 *   interfaceTypeName?
 *   receiverType?
 *   -anonymousField?
 */

/*
 * CODE_TYPE should be valid for the following types
 */

/**
 * Summary of required attributes.
 *
 * Need to specify {@link GoAnnotations#VAR_TYPE} for the following:
 *   - varSpec.idList
 *   - shortVarDecl.idList
 *   - parameterDecl.idList
 *
 * Need to specify {@link GoAnnotations#GLOBAL} for the following:
 *   - constSpec.idList
 *
 * Need to specify {@link GoAnnotations#BUILTIN} for the following:
 *   - function, var, and const references
 *
 * @author Sam Harwell
 */
public class SemanticAnalyzerListener implements GoParserBaseListener {
    private static final Logger LOGGER = Logger.getLogger(SemanticAnalyzerListener.class.getName());

    @NonNull
    private final VersionedDocument document;
    @NonNull
    private final GoAnnotatedParseTree annotatedParseTree;
    @NonNull
    private final ObjectDecorator<Tree> treeDecorator;
    @NonNull
    private final ObjectDecorator<Token> tokenDecorator;

    private final Deque<Map<String, Token>> visibleLocals = new ArrayDeque<Map<String, Token>>();
    private final Deque<Map<String, Token>> visibleConstants = new ArrayDeque<Map<String, Token>>();
    private final Deque<Map<String, Token>> visibleFunctions = new ArrayDeque<Map<String, Token>>();
    private final Deque<Map<String, Token>> visibleTypes = new ArrayDeque<Map<String, Token>>();

    private final List<TerminalNode<Token>> unresolvedIdentifiers = new ArrayList<TerminalNode<Token>>();
    private final List<TerminalNode<Token>> unresolvedQualifiedIdentifiers = new ArrayList<TerminalNode<Token>>();

    // label references are resolved at the end of a function
    private final Deque<Map<String, Token>> visibleLabels = new ArrayDeque<Map<String, Token>>();
    private final Deque<Collection<Token>> unresolvedLabels = new ArrayDeque<Collection<Token>>();

    private final Map<String, List<Token>> importedPackages = new HashMap<String, List<Token>>();

    public SemanticAnalyzerListener(@NonNull VersionedDocument document, @NonNull GoAnnotatedParseTree annotatedParseTree) {
        Parameters.notNull("document", document);
        Parameters.notNull("annotatedParseTree", annotatedParseTree);
        this.document = document;
        this.annotatedParseTree = annotatedParseTree;
        this.treeDecorator = annotatedParseTree.getTreeDecorator();
        this.tokenDecorator = annotatedParseTree.getTokenDecorator();
        pushVarScope();
    }

    public void resolveReferences() {
        CodeModelCacheImpl codeModelCache = CodeModelCacheImpl.getInstance();
        Project project = FileOwnerQuery.getOwner(document.getFileObject());
        String currentPackagePath = getCurrentPackagePath(project, document);
        PackageModel currentPackage = codeModelCache.getUniquePackage(project, currentPackagePath);

        Map<String, Collection<PackageModel>> resolvedPackages = new HashMap<String, Collection<PackageModel>>();
        for (Map.Entry<String, List<Token>> entry : importedPackages.entrySet()) {
            Collection<PackageModel> packages = resolvedPackages.get(entry.getKey());
            if (packages == null) {
                packages = new ArrayList<PackageModel>();
                resolvedPackages.put(entry.getKey(), packages);
            }

            for (Token importToken : entry.getValue()) {
                Collection<? extends CodeElementModel> resolved = tokenDecorator.getProperty(importToken, GoAnnotations.MODELS);
                for (CodeElementModel model : resolved) {
                    if (!(model instanceof PackageModel)) {
                        continue;
                    }

                    packages.add((PackageModel)model);
                }
            }
        }

        for (TerminalNode<Token> node : unresolvedIdentifiers) {
            resolveIdentifier(node, currentPackage, resolvedPackages);
        }

        boolean updatedResolution;
        do {
            updatedResolution = false;
            for (TerminalNode<Token> node : unresolvedQualifiedIdentifiers) {
                try {
                    updatedResolution |= resolveQualifiedIdentifier(node, currentPackage, resolvedPackages);
                } catch (RuntimeException ex) {
                    LOGGER.log(Level.FINE, String.format("An exception occurred while resolving a qualified identifier '%s'", node.getSymbol()), ex);
                } catch (Error ex) {
                    LOGGER.log(Level.FINE, String.format("An exception occurred while resolving a qualified identifier '%s'", node.getSymbol()), ex);
                }
            }
        } while (updatedResolution);
    }

    private static String getCurrentPackagePath(Project project, VersionedDocument document) {
        FileObject documentFileObject = document.getFileObject();
        FileObject packageFolder = documentFileObject.getParent();
        FileObject projectFolder = project != null ? project.getProjectDirectory() : null;

        String packagePath;
        if (projectFolder != null) {
            packagePath = FileUtil.getRelativePath(projectFolder, packageFolder);
        } else {
            packagePath = packageFolder.getNameExt();
        }

        return packagePath;
    }

    private void resolveIdentifier(TerminalNode<Token> node,
                                   PackageModel currentPackage,
                                   Map<String, ? extends Collection<? extends PackageModel>> importedPackages) {
        // check again for a top-level definition in the current file
        Token token = node.getSymbol();
        Token target = getVisibleDeclaration(token);
        if (target != null) {
            boolean resolved = true;
            switch (tokenDecorator.getProperty(target, GoAnnotations.NODE_TYPE)) {
            case CONST_DECL:
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.CONST_REF);
                break;

            case VAR_DECL:
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.VAR_REF);
                break;

            case FUNC_DECL:
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.FUNC_REF);
                break;

            case TYPE_DECL:
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.TYPE_REF);
                break;

            default:
                resolved = false;
                break;
            }

            tokenDecorator.putProperty(token, GoAnnotations.LOCAL_TARGET, target);
            if (resolved) {
                tokenDecorator.putProperty(token, GoAnnotations.RESOLVED, true);
            }

            VarKind varType = tokenDecorator.getProperty(target, GoAnnotations.VAR_TYPE);
            if (varType != VarKind.UNDEFINED) {
                tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, varType);
            }

            TypeKind typeKind = tokenDecorator.getProperty(target, GoAnnotations.TYPE_KIND);
            if (typeKind != TypeKind.UNDEFINED) {
                tokenDecorator.putProperty(token, GoAnnotations.TYPE_KIND, typeKind);
            }

            if (tokenDecorator.getProperty(target, GoAnnotations.GLOBAL)) {
                tokenDecorator.putProperty(token, GoAnnotations.GLOBAL, true);
            }

            return;
        }

        // try to resolve the element in the current package
        if (currentPackage != null) {
            if (resolveIdentifier(token, currentPackage)) {
                return;
            }
        }

        // check packages with alias '.'
        Collection<? extends PackageModel> mergedPackages = importedPackages.get("");
        if (mergedPackages != null) {
            for (PackageModel model : mergedPackages) {
                if (resolveIdentifier(token, model)) {
                    return;
                }
            }
        }
    }

    private boolean resolveIdentifier(Token token, PackageModel packageModel) {
        Collection<? extends CodeElementModel> members = packageModel.getMembers(token.getText());
        for (CodeElementModel model : members) {
            NodeType nodeType = NodeType.UNDEFINED;
            VarKind varType = VarKind.UNDEFINED;
            TypeKind typeKind = TypeKind.UNDEFINED;
            boolean global = true;
            boolean resolved = true;
            if (model instanceof ConstModel) {
                nodeType = NodeType.CONST_REF;
            } else if (model instanceof VarModel) {
                nodeType = NodeType.VAR_REF;
                varType = VarKind.GLOBAL;
            } else if (model instanceof TypeModel) {
                nodeType = NodeType.TYPE_REF;
                typeKind = ((TypeModel)model).getKind();
            } else if (model instanceof FunctionModel) {
                nodeType = NodeType.FUNC_REF;
            } else {
                resolved = false;
            }

            if (nodeType != NodeType.UNDEFINED) {
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, nodeType);
            }

            if (resolved) {
                tokenDecorator.putProperty(token, GoAnnotations.RESOLVED, true);
            }

            if (varType != VarKind.UNDEFINED) {
                tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, varType);
            }

            if (typeKind != TypeKind.UNDEFINED) {
                tokenDecorator.putProperty(token, GoAnnotations.TYPE_KIND, typeKind);
            }

            tokenDecorator.putProperty(token, GoAnnotations.GLOBAL, true);
            tokenDecorator.putProperty(token, GoAnnotations.MODELS, members);
            return true;
        }

        return false;
    }

    private boolean resolveQualifiedIdentifier(TerminalNode<Token> node, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Token token = node.getSymbol();
        if (tokenDecorator.getProperty(token, GoAnnotations.NODE_TYPE) != NodeType.UNDEFINED) {
            // already resolved
            return false;
        }

        ParserRuleContext<Token> qualifier = tokenDecorator.getProperty(token, GoAnnotations.QUALIFIER);
        if (qualifier == null) {
            // don't have the information necessary to resolve
            return false;
        }

        Collection<? extends CodeElementModel> resolvedQualifier = treeDecorator.getProperty(qualifier, GoAnnotations.MODELS);
        if (resolvedQualifier == null) {
            CodeElementReference qualifierCodeClass = treeDecorator.getProperty(qualifier, GoAnnotations.CODE_CLASS);
            if (qualifierCodeClass != null) {
                resolvedQualifier = qualifierCodeClass.resolve(annotatedParseTree, currentPackage, resolvedPackages);
            }
        }

        if (resolvedQualifier == null) {
            CodeElementReference qualifierExprType = treeDecorator.getProperty(qualifier, GoAnnotations.EXPR_TYPE);
            if (qualifierExprType != null) {
                resolvedQualifier = qualifierExprType.resolve(annotatedParseTree, currentPackage, resolvedPackages);
            }
        }

        if (resolvedQualifier == null) {
            NodeType qualifierNodeType = treeDecorator.getProperty(qualifier, GoAnnotations.NODE_TYPE);
            if (qualifierNodeType == NodeType.UNDEFINED) {
                if (treeDecorator.getProperty(qualifier, GoAnnotations.QUALIFIED_EXPR)) {
                    // haven't resolved the qualifier, which is itself a qualified expression
                    return false;
                }

                Token unqualifiedLink = treeDecorator.getProperty(qualifier, GoAnnotations.UNQUALIFIED_LINK);
                if (unqualifiedLink != null) {
                    Map<? extends ObjectProperty<?>, ?> properties = tokenDecorator.getProperties(unqualifiedLink);
                    treeDecorator.putProperties(qualifier, properties);
                    qualifierNodeType = treeDecorator.getProperty(qualifier, GoAnnotations.NODE_TYPE);
                    if (qualifierNodeType == NodeType.UNDEFINED) {
                        treeDecorator.putProperty(qualifier, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
                        qualifierNodeType = NodeType.UNKNOWN;
                    }
                } else {
                    LOGGER.log(Level.WARNING, "Unable to resolve unqualified link from qualifier: {0}", qualifier);
                    qualifierNodeType = NodeType.UNKNOWN;
                }
            }

            assert qualifierNodeType != NodeType.UNDEFINED;

            if (qualifierNodeType == NodeType.UNKNOWN) {
                // can't resolve a dereference if the qualifier couldn't be resolved
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
                treeDecorator.putProperty(node, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
                return true;
            }

            if (qualifierNodeType == NodeType.TYPE_LITERAL) {
                return resolveQualifierType(qualifier, currentPackage, resolvedPackages);
            } else if (qualifierNodeType == NodeType.PACKAGE_REF) {
                assert qualifier instanceof packageNameContext;
                String packageName = ((packageNameContext)qualifier).name.getText();
                resolvedQualifier = resolvedPackages.get(packageName);
                if (resolvedQualifier == null) {
                    resolvedQualifier = Collections.emptyList();
                }
            } else if (qualifierNodeType == NodeType.VAR_REF) {
                // must be referring to something within the current file since it's resolved internally
                Token target = treeDecorator.getProperty(qualifier, GoAnnotations.LOCAL_TARGET);
                assert tokenDecorator.getProperty(target, GoAnnotations.NODE_TYPE) == NodeType.VAR_DECL;
                ParserRuleContext<Token> explicitType = tokenDecorator.getProperty(target, GoAnnotations.EXPLICIT_TYPE);
                if (explicitType != null) {
                    LOGGER.log(Level.WARNING, "Unable to resolve explicit type for qualifier: {0}", qualifier);
                    resolvedQualifier = Collections.emptyList();
                } else {
                    ParserRuleContext<Token> implicitType = tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_TYPE);
                    int implicitIndex = tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_INDEX);
                    LOGGER.log(Level.WARNING, "Unable to resolve implicit type for qualifier: {0}", qualifier);
                    resolvedQualifier = Collections.emptyList();
                }
            } else {
                LOGGER.log(Level.WARNING, "Unable to resolve qualifier: {0}", qualifier);
                resolvedQualifier = Collections.emptyList();
            }
        }

        assert resolvedQualifier != null;
        if (resolvedQualifier == null) {
            LOGGER.log(Level.WARNING, "Should not have a null resolved qualifier at this point.");
            resolvedQualifier = Collections.emptyList();
        }

        String nameText = token.getText();
        List<CodeElementModel> qualifiedModels = new ArrayList<CodeElementModel>();
        for (CodeElementModel model : resolvedQualifier) {
            qualifiedModels.addAll(SemanticAnalyzer.getSelectableMembers(model, nameText));
        }

        if (qualifiedModels.isEmpty()) {
            tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
            treeDecorator.putProperty(node, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
            return true;
        }

        setNodeType(token, qualifiedModels.get(0));
        tokenDecorator.putProperty(token, GoAnnotations.RESOLVED, true);
        tokenDecorator.putProperty(token, GoAnnotations.MODELS, qualifiedModels);
        treeDecorator.putProperties(node, tokenDecorator.getProperties(token));
        return true;
    }

    private void setNodeType(Token token, CodeElementModel model) {
        NodeType nodeType;
        VarKind varType = VarKind.UNDEFINED;
        TypeKind typeKind = TypeKind.UNDEFINED;

        if (model instanceof PackageModel) {
            nodeType = NodeType.PACKAGE_REF;
        } else if (model instanceof TypeModel) {
            nodeType = NodeType.TYPE_REF;
            typeKind = ((TypeModel)model).getKind();
        } else if (model instanceof VarModel) {
            nodeType = NodeType.VAR_REF;
            varType = ((VarModel)model).getVarKind();
        } else if (model instanceof ConstModel) {
            nodeType = NodeType.CONST_REF;
        } else if (model instanceof FunctionModel) {
            nodeType = ((FunctionModel)model).isMethod() ? NodeType.METHOD_REF : NodeType.FUNC_REF;
        } else {
            LOGGER.log(Level.WARNING, "Could not get a NodeType from model type: {0}", model.getClass());
            nodeType = NodeType.UNKNOWN;
        }

        tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, nodeType);
        if (typeKind != TypeKind.UNDEFINED) {
            tokenDecorator.putProperty(token, GoAnnotations.TYPE_KIND, typeKind);
        }
        if (varType != VarKind.UNDEFINED) {
            tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, varType);
        }
    }

    private boolean resolveQualifierType(ParserRuleContext<Token> qualifier,
                                         PackageModel currentPackage,
                                         Map<String, Collection<PackageModel>> resolvedPackages) {

        if (qualifier instanceof arrayTypeContext) {
            arrayTypeContext ctx = (arrayTypeContext)qualifier;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class QualifierTypeResolver extends GoParserBaseBaseListener {

        public boolean resolveType(ParserRuleContext<Token> qualifier) {
            ParseTreeWalker.DEFAULT.walk(this, qualifier);
            return treeDecorator.getProperty(qualifier, GoAnnotations.MODELS) != null;
        }

        @Override
        public void arrayTypeExit(arrayTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elemType, GoAnnotations.MODELS);
            if (elementTypes != null) {
                if (elementTypes.isEmpty()) {
                    treeDecorator.putProperty(ctx, GoAnnotations.MODELS, Collections.<CodeElementModel>emptyList());
                    return;
                }

                List<TypeModelImpl> arrayTypes = new ArrayList<TypeModelImpl>();
                for (CodeElementModel model : elementTypes) {
                    if (!(model instanceof TypeModelImpl)) {
                        continue;
                    }

                    TypeModelImpl elementType = (TypeModelImpl)model;
                    TypeModelImpl arrayType = new TypeArrayModelImpl(elementType);
                    arrayTypes.add(arrayType);
                }

                treeDecorator.putProperty(ctx, GoAnnotations.MODELS, arrayTypes);
            }
        }

        @Override
        public void pointerTypeExit(pointerTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.typ, GoAnnotations.MODELS);
            if (elementTypes != null) {
                if (elementTypes.isEmpty()) {
                    treeDecorator.putProperty(ctx, GoAnnotations.MODELS, Collections.<CodeElementModel>emptyList());
                    return;
                }

                List<TypeModelImpl> pointerTypes = new ArrayList<TypeModelImpl>();
                for (CodeElementModel model : elementTypes) {
                    if (!(model instanceof TypeModelImpl)) {
                        continue;
                    }

                    TypeModelImpl elementType = (TypeModelImpl)model;
                    TypeModelImpl pointerType = new TypePointerModelImpl(elementType);
                    pointerTypes.add(pointerType);
                }

                treeDecorator.putProperty(ctx, GoAnnotations.MODELS, pointerTypes);
            }
        }

        @Override
        public void sliceTypeExit(sliceTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elemType, GoAnnotations.MODELS);
            if (elementTypes != null) {
                if (elementTypes.isEmpty()) {
                    treeDecorator.putProperty(ctx, GoAnnotations.MODELS, Collections.<CodeElementModel>emptyList());
                    return;
                }

                List<TypeModelImpl> sliceTypes = new ArrayList<TypeModelImpl>();
                for (CodeElementModel model : elementTypes) {
                    if (!(model instanceof TypeModelImpl)) {
                        continue;
                    }

                    TypeModelImpl elementType = (TypeModelImpl)model;
                    TypeModelImpl sliceType = new TypeSliceModelImpl(elementType);
                    sliceTypes.add(sliceType);
                }

                treeDecorator.putProperty(ctx, GoAnnotations.MODELS, sliceTypes);
            }
        }

        @Override
        public void mapTypeExit(mapTypeContext ctx) {
            Collection<? extends CodeElementModel> keyTypes = treeDecorator.getProperty(ctx.keyTyp, GoAnnotations.MODELS);
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elemType, GoAnnotations.MODELS);
            if (keyTypes != null && elementTypes != null) {
                if (keyTypes == null || elementTypes.isEmpty()) {
                    treeDecorator.putProperty(ctx, GoAnnotations.MODELS, Collections.<CodeElementModel>emptyList());
                    return;
                }

                List<TypeModelImpl> mapTypes = new ArrayList<TypeModelImpl>();
                for (CodeElementModel keyModel : keyTypes) {
                    if (!(keyModel instanceof TypeModelImpl)) {
                        continue;
                    }

                    for (CodeElementModel model : elementTypes) {
                        if (!(model instanceof TypeModelImpl)) {
                            continue;
                        }

                        TypeModelImpl keyType = (TypeModelImpl)keyModel;
                        TypeModelImpl elementType = (TypeModelImpl)model;
                        TypeModelImpl mapType = new TypeMapModelImpl(keyType, elementType);
                        mapTypes.add(mapType);
                    }
                }

                treeDecorator.putProperty(ctx, GoAnnotations.MODELS, mapTypes);
            }
        }

        @Override
        public void channelTypeExit(channelTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elemType, GoAnnotations.MODELS);
            if (elementTypes != null) {
                if (elementTypes.isEmpty()) {
                    treeDecorator.putProperty(ctx, GoAnnotations.MODELS, Collections.<CodeElementModel>emptyList());
                    return;
                }

                ChannelKind channelKind = ChannelKind.SendReceive;
                if (ctx.send != null) {
                    channelKind = ChannelKind.Send;
                } else if (ctx.recv != null) {
                    channelKind = ChannelKind.Receive;
                }

                List<TypeModelImpl> channelTypes = new ArrayList<TypeModelImpl>();
                for (CodeElementModel model : elementTypes) {
                    if (!(model instanceof TypeModelImpl)) {
                        continue;
                    }

                    TypeModelImpl elementType = (TypeModelImpl)model;
                    TypeModelImpl channelType = new TypeChannelModelImpl(elementType, channelKind);
                    channelTypes.add(channelType);
                }

                treeDecorator.putProperty(ctx, GoAnnotations.MODELS, channelTypes);
            }
        }
    }

    @Override
    public void multExprEnter(multExprContext ctx) {
    }

    @Override
    public void multExprExit(multExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null && ctx.right == null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.right, GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void channelTypeEnter(channelTypeContext ctx) {
    }

    @Override
    public void channelTypeExit(channelTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.elemType != null) {
            ChannelKind kind = ChannelKind.SendReceive;
            if (ctx.send != null) {
                kind = ChannelKind.Send;
            } else if (ctx.recv != null) {
                kind = ChannelKind.Receive;
            }

            codeClass = new ChannelTypeReference(treeDecorator.getProperty(ctx.elemType, GoAnnotations.CODE_CLASS), kind);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.CHANNEL);
    }

    @Override
    public void mulAssignOpEnter(mulAssignOpContext ctx) {
    }

    @Override
    public void mulAssignOpExit(mulAssignOpContext ctx) {
    }

    @Override
    public void packageNameEnter(packageNameContext ctx) {
        int invokingRule = ParseTrees.getInvokingRule(GoParserBase._ATN, ctx);
        NodeType nodeType = invokingRule == GoParser.RULE_packageClause ? NodeType.PACKAGE_DECL : NodeType.PACKAGE_REF;
        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, nodeType);
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, nodeType);
            if (treeDecorator.getProperty(ctx, GoAnnotations.RESOLVED)) {
                tokenDecorator.putProperty(ctx.name, GoAnnotations.RESOLVED, true);
            }

            Token localTarget = treeDecorator.getProperty(ctx, GoAnnotations.LOCAL_TARGET);
            if (localTarget != null) {
                tokenDecorator.putProperty(ctx.name, GoAnnotations.LOCAL_TARGET, localTarget);
            }
        }
    }

    @Override
    public void packageNameExit(packageNameContext ctx) {
        // not qualified!
    }

    @Override
    public void receiverEnter(receiverContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            tokenDecorator.putProperty(ctx.name, GoAnnotations.VAR_TYPE, VarKind.RECEIVER);
            tokenDecorator.putProperty(ctx.name, GoAnnotations.EXPLICIT_TYPE, ctx.typ);
            visibleLocals.peek().put(ctx.name.getText(), ctx.name);
        }

        if (ctx.ptr != null && ctx.typ != null) {
            treeDecorator.putProperty(ctx.typ, GoAnnotations.POINTER_RECEIVER, true);
        }
    }

    @Override
    public void receiverExit(receiverContext ctx) {
    }

    @Override
    public void arrayTypeEnter(arrayTypeContext ctx) {
    }

    @Override
    public void arrayTypeExit(arrayTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.elemType != null) {
            elemClass = treeDecorator.getProperty(ctx.elemType, GoAnnotations.CODE_CLASS);
        }

        CodeElementReference arrayClass = new ArrayTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, arrayClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.ARRAY);
    }

    @Override
    public void expressionListEnter(expressionListContext ctx) {
    }

    @Override
    public void expressionListExit(expressionListContext ctx) {
    }

    @Override
    public void tagEnter(tagContext ctx) {
    }

    @Override
    public void tagExit(tagContext ctx) {
    }

    @Override
    public void fallthroughStmtEnter(fallthroughStmtContext ctx) {
    }

    @Override
    public void fallthroughStmtExit(fallthroughStmtContext ctx) {
    }

    @Override
    public void selectorExprEnter(selectorExprContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.QUALIFIED_EXPR, true);
            if (ctx.e != null) {
                tokenDecorator.putProperty(ctx.name, GoAnnotations.QUALIFIER, ctx.e);
            }
        }
    }

    @Override
    public void selectorExprExit(selectorExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null && ctx.name != null) {
            exprType = new SelectedElementReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE), ctx.name);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        if (ctx.name != null) {
            @SuppressWarnings("unchecked")
            TerminalNode<Token> node = (TerminalNode<Token>)ParseTrees.findTerminalNode(ctx.children, ctx.name);
            unresolvedQualifiedIdentifiers.add(node);
        }
    }

    @Override
    public void parameterListEnter(parameterListContext ctx) {
    }

    @Override
    public void parameterListExit(parameterListContext ctx) {
    }

    @Override
    public void receiverTypeEnter(receiverTypeContext ctx) {
    }

    @Override
    public void receiverTypeExit(receiverTypeContext ctx) {
    }

    @Override
    public void fieldNameEnter(fieldNameContext ctx) {
    }

    @Override
    public void fieldNameExit(fieldNameContext ctx) {
    }

    @Override
    public void ifStmtEnter(ifStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void ifStmtExit(ifStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void methodNameEnter(methodNameContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.METHOD_DECL);
        }
    }

    @Override
    public void methodNameExit(methodNameContext ctx) {
    }

    @Override
    public void signatureEnter(signatureContext ctx) {
    }

    @Override
    public void signatureExit(signatureContext ctx) {
    }

    @Override
    public void mapTypeEnter(mapTypeContext ctx) {
    }

    @Override
    public void mapTypeExit(mapTypeContext ctx) {
        CodeElementReference keyType = CodeElementReference.UNKNOWN;
        if (ctx.keyTyp != null) {
            keyType = treeDecorator.getProperty(ctx.keyTyp, GoAnnotations.CODE_CLASS);
        }

        CodeElementReference valueType = CodeElementReference.UNKNOWN;
        if (ctx.elemType != null) {
            valueType = treeDecorator.getProperty(ctx.elemType, GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new MapTypeReference(keyType, valueType);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.MAP);
    }

    @Override
    public void elementEnter(elementContext ctx) {
    }

    @Override
    public void elementExit(elementContext ctx) {
    }

    @Override
    public void callExprEnter(callExprContext ctx) {
    }

    @Override
    public void callExprExit(callExprContext ctx) {
        CodeElementReference returnType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            boolean builtin = false;
            if (ctx.e.start != null && ctx.e.start == ctx.e.stop) {
                String methodName = ctx.e.start.getText();
                builtin = SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(methodName);
            }

            if (builtin) {
                CodeElementReference typeArgument = CodeElementReference.UNKNOWN;
                argumentListContext args = ctx.args;
                if (args != null) {
                    expressionListContext exprs = args.exprs;
                    if (exprs != null && exprs.expressions != null && !exprs.expressions.isEmpty()) {
                        typeArgument = treeDecorator.getProperty(exprs.expressions.get(0), GoAnnotations.CODE_CLASS);
                        if (typeArgument == null) {
                            typeArgument = treeDecorator.getProperty(exprs.expressions.get(0), GoAnnotations.EXPR_TYPE);
                        }
                    }
                }

                returnType = new BuiltinCallResultReference(ctx.e.start, typeArgument);
            } else {
                returnType = new CallResultReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
            }
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, returnType);
    }

    @Override
    public void typeCaseClauseEnter(typeCaseClauseContext ctx) {
    }

    @Override
    public void typeCaseClauseExit(typeCaseClauseContext ctx) {
    }

    @Override
    public void exprCaseClauseEnter(exprCaseClauseContext ctx) {
    }

    @Override
    public void exprCaseClauseExit(exprCaseClauseContext ctx) {
    }

    @Override
    public void typeSwitchGuardEnter(typeSwitchGuardContext ctx) {
    }

    @Override
    public void typeSwitchGuardExit(typeSwitchGuardContext ctx) {
    }

    @Override
    public void functionLiteralEnter(functionLiteralContext ctx) {
        pushVarScope();
    }

    @Override
    public void functionLiteralExit(functionLiteralContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        popVarScope();
    }

    @Override
    public void orExprEnter(orExprContext ctx) {
    }

    @Override
    public void orExprExit(orExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void recvExprEnter(recvExprContext ctx) {
    }

    @Override
    public void recvExprExit(recvExprContext ctx) {
        assert ctx.getChildCount() <= 1;
        assert ctx.getChildCount() == 0 || ctx.getChild(0) instanceof expressionContext;

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.getChild(0) != null) {
            exprType = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void topLevelDeclEnter(topLevelDeclContext ctx) {
    }

    @Override
    public void topLevelDeclExit(topLevelDeclContext ctx) {
    }

    @Override
    public void methodSpecEnter(methodSpecContext ctx) {
        pushVarScope();
    }

    @Override
    public void methodSpecExit(methodSpecContext ctx) {
        popVarScope();
    }

    @Override
    public void constSpecEnter(constSpecContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.CONST_DECL);
            boolean global = ParseTrees.isInContexts(ctx, false, GoParser.RULE_constSpec, GoParser.RULE_constDecl, GoParser.RULE_declaration, GoParser.RULE_topLevelDecl);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.GLOBAL, global);
        }
    }

    @Override
    public void constSpecExit(constSpecContext ctx) {
    }

    @Override
    public void compositeLiteralEnter(compositeLiteralContext ctx) {
    }

    @Override
    public void compositeLiteralExit(compositeLiteralContext ctx) {
        CodeElementReference exprType;
        if (ctx.litTyp != null) {
            exprType = treeDecorator.getProperty(ctx.litTyp, GoAnnotations.CODE_CLASS);
        } else {
            exprType = CodeElementReference.UNKNOWN;
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
        if (ctx.litVal != null) {
            treeDecorator.putProperty(ctx.litVal, GoAnnotations.EXPR_TYPE, exprType);
        }
    }

    @Override
    public void forClauseEnter(forClauseContext ctx) {
    }

    @Override
    public void forClauseExit(forClauseContext ctx) {
    }

    @Override
    public void shortVarDeclEnter(shortVarDeclContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.LOCAL);
            if (ctx.exprList != null) {
                int varCount = ctx.idList.ids != null ? ctx.idList.ids.size() : 0;
                int exprCount = ctx.exprList.expressions != null ? ctx.exprList.expressions.size() : 0;
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expressions.get(0));
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expressions.get(i));
                        if (varCount > 1) {
                            tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_INDEX, i);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void shortVarDeclExit(shortVarDeclContext ctx) {
    }

    @Override
    public void gotoStmtEnter(gotoStmtContext ctx) {
    }

    @Override
    public void gotoStmtExit(gotoStmtContext ctx) {
    }

    @Override
    public void arrayLengthEnter(arrayLengthContext ctx) {
    }

    @Override
    public void arrayLengthExit(arrayLengthContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expr != null) {
            exprType = treeDecorator.getProperty(ctx.expr, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void interfaceTypeEnter(interfaceTypeContext ctx) {
    }

    @Override
    public void interfaceTypeExit(interfaceTypeContext ctx) {
        CodeElementReference codeClass = new InterfaceTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.INTERFACE);
    }

    @Override
    public void conversionEnter(conversionContext ctx) {
    }

    @Override
    public void conversionExit(conversionContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.t != null) {
            exprType = treeDecorator.getProperty(ctx.t, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void blockEnter(blockContext ctx) {
        pushVarScope();
    }

    @Override
    public void blockExit(blockContext ctx) {
        popVarScope();
    }

    @Override
    public void breakStmtEnter(breakStmtContext ctx) {
    }

    @Override
    public void breakStmtExit(breakStmtContext ctx) {
    }

    @Override
    public void emptyStmtEnter(emptyStmtContext ctx) {
    }

    @Override
    public void emptyStmtExit(emptyStmtContext ctx) {
    }

    @Override
    public void functionTypeEnter(functionTypeContext ctx) {
        if (!ParseTrees.isInContexts(ctx, false, GoParser.RULE_functionType, GoParser.RULE_functionLiteral)) {
            pushVarScope();
        }
    }

    @Override
    public void functionTypeExit(functionTypeContext ctx) {
        CodeElementReference codeClass = new FunctionTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        if (!ParseTrees.isInContexts(ctx, false, GoParser.RULE_functionType, GoParser.RULE_functionLiteral)) {
            popVarScope();
        }

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.FUNCTION);
    }

    @Override
    public void baseTypeEnter(baseTypeContext ctx) {
    }

    @Override
    public void baseTypeExit(baseTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.typ != null) {
            codeClass = treeDecorator.getProperty(ctx.typ, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void fieldDeclEnter(fieldDeclContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.FIELD);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.EXPLICIT_TYPE, ctx.fieldType);
        }
    }

    @Override
    public void fieldDeclExit(fieldDeclContext ctx) {
    }

    @Override
    public void exprSwitchStmtEnter(exprSwitchStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void exprSwitchStmtExit(exprSwitchStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void goStmtEnter(goStmtContext ctx) {
    }

    @Override
    public void goStmtExit(goStmtContext ctx) {
    }

    @Override
    public void parameterDeclEnter(parameterDeclContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            if (ctx.ellip != null) {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.VARIADIC, true);
            }
            treeDecorator.putProperty(ctx.idList, GoAnnotations.EXPLICIT_TYPE, ctx.t);
            if (ParseTrees.isInContexts(ctx, false, GoParser.RULE_parameterDecl, GoParser.RULE_parameterList, GoParser.RULE_parameters, GoParser.RULE_result)) {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.RETURN);
            } else {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.PARAMETER);
            }

            for (Token id : ParseTrees.emptyIfNull(ctx.idList.ids)) {
                visibleLocals.peek().put(id.getText(), id);
            }
        }
    }

    @Override
    public void parameterDeclExit(parameterDeclContext ctx) {
    }

    @Override
    public void basicLiteralEnter(basicLiteralContext ctx) {
    }

    @Override
    public void basicLiteralExit(basicLiteralContext ctx) {
        assert ctx.stop == null || ctx.start == ctx.stop;
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralTypeReference(ctx.start));
    }

    @Override
    public void exprSwitchCaseEnter(exprSwitchCaseContext ctx) {
    }

    @Override
    public void exprSwitchCaseExit(exprSwitchCaseContext ctx) {
    }

    @Override
    public void typeLiteralEnter(typeLiteralContext ctx) {
    }

    @Override
    public void typeLiteralExit(typeLiteralContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.getChildCount() == 1) {
            codeClass = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void selectStmtEnter(selectStmtContext ctx) {
    }

    @Override
    public void selectStmtExit(selectStmtContext ctx) {
    }

    @Override
    public void importSpecEnter(importSpecContext ctx) {
        String name = null;
        String path = null;
        Token target = null;
        if (ctx.path != null && ctx.path.path != null) {
            target = ctx.path.path;
            path = target.getText();
            if (path.startsWith("\"")) {
                path = path.substring(1);
            }
            if (path.endsWith("\"")) {
                path = path.substring(0, path.length() - 1);
            }
        }

        if (ctx.dot != null) {
            name = "";
            target = null;
        } else if (ctx.name != null) {
            if (ctx.name.name != null) {
                target = ctx.name.name;
                name = target.getText();
            }
        } else {
            name = path.substring(path.lastIndexOf('/') + 1);
        }

        if (target != null) {
            Project currentProject = FileOwnerQuery.getOwner(document.getFileObject());
            Collection<? extends PackageModel> packages = CodeModelCacheImpl.getInstance().getPackages(currentProject, path);
            tokenDecorator.putProperty(target, GoAnnotations.MODELS, packages);
            if (!packages.isEmpty()) {
                tokenDecorator.putProperty(target, GoAnnotations.RESOLVED, true);
            }

            List<Token> packageList = importedPackages.get(name);
            if (packageList == null) {
                packageList = new ArrayList<Token>();
                importedPackages.put(name, packageList);
            }

            packageList.add(target);
        }
    }

    @Override
    public void importSpecExit(importSpecContext ctx) {
    }

    @Override
    public void typeNameEnter(typeNameContext ctx) {
    }

    @Override
    public void typeNameExit(typeNameContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.qid != null) {
            codeClass = new QualifiedIdentifierElementReference(ctx.qid);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        if (ctx.qid == null) {
            return;
        }

        if (treeDecorator.getProperty(ctx.qid, GoAnnotations.QUALIFIED_EXPR)) {
            treeDecorator.putProperty(ctx, GoAnnotations.QUALIFIED_EXPR, true);
        } else {
            treeDecorator.putProperty(ctx, GoAnnotations.UNQUALIFIED_LINK, treeDecorator.getProperty(ctx.qid, GoAnnotations.UNQUALIFIED_LINK));
        }
    }

    @Override
    public void literalTypeEnter(literalTypeContext ctx) {
    }

    @Override
    public void literalTypeExit(literalTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.getChildCount() == 1) {
            ParseTree child = ctx.getChild(0);
            assert child instanceof structTypeContext
                || child instanceof arrayTypeContext
                || child instanceof sliceTypeContext
                || child instanceof mapTypeContext
                || child instanceof typeNameContext;
            codeClass = treeDecorator.getProperty(child, GoAnnotations.CODE_CLASS);
        } else if (ctx.getChildCount() > 1) {
            ParseTree firstChild = ctx.getChild(0);
            assert firstChild instanceof TerminalNode<?>
                && ((TerminalNode<?>)firstChild).getSymbol() instanceof Token
                && ((Token)((TerminalNode<?>)firstChild).getSymbol()).getType() == GoParser.LeftBrack;

            ParseTree lastChild = ctx.getChild(ctx.getChildCount() - 1);
            assert lastChild instanceof elementTypeContext;
            codeClass = new ArrayTypeReference(treeDecorator.getProperty(lastChild, GoAnnotations.CODE_CLASS));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void assignmentEnter(assignmentContext ctx) {
    }

    @Override
    public void assignmentExit(assignmentContext ctx) {
    }

    @Override
    public void assignOpEnter(assignOpContext ctx) {
    }

    @Override
    public void assignOpExit(assignOpContext ctx) {
    }

    @Override
    public void recvStmtEnter(recvStmtContext ctx) {
    }

    @Override
    public void recvStmtExit(recvStmtContext ctx) {
    }

    @Override
    public void typeSpecEnter(typeSpecContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.TYPE_DECL);
            visibleTypes.peek().put(ctx.name.getText(), ctx.name);
        }
    }

    @Override
    public void typeSpecExit(typeSpecContext ctx) {
    }

    @Override
    public void packageClauseEnter(packageClauseContext ctx) {
    }

    @Override
    public void packageClauseExit(packageClauseContext ctx) {
    }

    @Override
    public void literalValueEnter(literalValueContext ctx) {
    }

    @Override
    public void literalValueExit(literalValueContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralValueElementReference());
    }

    @Override
    public void indexExprEnter(indexExprContext ctx) {
    }

    @Override
    public void indexExprExit(indexExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null) {
            CodeElementReference arrayType = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            exprType = new ArrayElementTypeReference(arrayType);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void varSpecEnter(varSpecContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            if (ParseTrees.isInContexts(ctx, false, GoParser.RULE_varSpec, GoParser.RULE_varDecl, GoParser.RULE_declaration, GoParser.RULE_topLevelDecl)) {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.GLOBAL);
            } else {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.LOCAL);
            }
            
            if (ctx.varType != null) {
                treeDecorator.putProperty(ctx.idList, GoAnnotations.EXPLICIT_TYPE, ctx.varType);
            } else if (ctx.exprList != null) {
                int varCount = ctx.idList.ids!= null ? ctx.idList.ids.size() : 0;
                int exprCount = ctx.exprList.expressions!= null ? ctx.exprList.expressions.size() : 0;
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expressions.get(0));
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expressions.get(i));
                        tokenDecorator.putProperty(ctx.idList.ids.get(i), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                }
            }
        }
    }

    @Override
    public void varSpecExit(varSpecContext ctx) {
    }

    @Override
    public void expressionEnter(expressionContext ctx) {
    }

    @Override
    public void expressionExit(expressionContext ctx) {
    }

    @Override
    public void operandExprEnter(operandExprContext ctx) {
    }

    @Override
    public void operandExprExit(operandExprContext ctx) {
        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof operandContext) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof operandContext) {
            treeDecorator.putProperties(ctx, treeDecorator.getProperties(ctx.getChild(0)));
        } else {
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
        }
    }

    @Override
    public void builtinCallExprEnter(builtinCallExprContext ctx) {
    }

    @Override
    public void builtinCallExprExit(builtinCallExprContext ctx) {
        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof builtinCallContext) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
    }

    @Override
    public void bodyEnter(bodyContext ctx) {
        pushVarScope();
        visibleLabels.push(new HashMap<String, Token>());
        unresolvedLabels.push(new ArrayList<Token>());
    }

    @Override
    public void bodyExit(bodyContext ctx) {
        for (Token labelReference : unresolvedLabels.peek()) {
            Token target = visibleLabels.peek().get(labelReference.getText());
            if (target == null) {
                continue;
            }

            tokenDecorator.putProperty(labelReference, GoAnnotations.LOCAL_TARGET, target);
            tokenDecorator.putProperty(labelReference, GoAnnotations.RESOLVED, true);
        }

        popVarScope();
        visibleLabels.pop();
        unresolvedLabels.pop();
    }

    @Override
    public void commClauseEnter(commClauseContext ctx) {
    }

    @Override
    public void commClauseExit(commClauseContext ctx) {
    }

    @Override
    public void qualifiedIdentifierEnter(qualifiedIdentifierContext ctx) {
        if (ctx.pkg != null) {
            if (ctx.id != null) {
                tokenDecorator.putProperty(ctx.id, GoAnnotations.QUALIFIED_EXPR, true);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.QUALIFIER, ctx.pkg);
                @SuppressWarnings("unchecked")
                TerminalNode<Token> node = (TerminalNode<Token>)ParseTrees.findTerminalNode(ctx.children, ctx.id);
                unresolvedQualifiedIdentifiers.add(node);
            }

            // check known imports
            if (ctx.pkg.name != null) {
                List<? extends Token> imports = ParseTrees.emptyIfNull(importedPackages.get(ctx.pkg.name.getText()));
                Token bestImport = null;
                boolean resolvedImport = false;
                for (Token importToken : imports) {
                    if (bestImport == null || (!resolvedImport && tokenDecorator.getProperty(importToken, GoAnnotations.RESOLVED))) {
                        bestImport = importToken;
                        resolvedImport = tokenDecorator.getProperty(importToken, GoAnnotations.RESOLVED);
                        break;
                    }
                }

                if (bestImport != null) {
                    treeDecorator.putProperty(ctx.pkg, GoAnnotations.LOCAL_TARGET, bestImport);
                    if (resolvedImport) {
                        treeDecorator.putProperty(ctx.pkg, GoAnnotations.RESOLVED, true);
                    }
                }
            }
        } else if (ctx.id != null) {
            assert ctx.pkg == null;
            Token local = getVisibleLocal(ctx.id);
            if (local != null) {
                tokenDecorator.putProperty(ctx.id, GoAnnotations.NODE_TYPE, NodeType.VAR_REF);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.LOCAL_TARGET, local);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.RESOLVED, true);
                VarKind varType = tokenDecorator.getProperty(local, GoAnnotations.VAR_TYPE);
                if (varType != VarKind.UNDEFINED) {
                    tokenDecorator.putProperty(ctx.id, GoAnnotations.VAR_TYPE, varType);
                }
                return;
            }

            // check built-ins
            if (SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(ctx.id.getText())) {
                tokenDecorator.putProperty(ctx.id, GoAnnotations.NODE_TYPE, NodeType.FUNC_REF);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.BUILTIN, true);
            } else if (SemanticHighlighter.PREDEFINED_TYPES.contains(ctx.id.getText())) {
                tokenDecorator.putProperty(ctx.id, GoAnnotations.NODE_TYPE, NodeType.TYPE_REF);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.TYPE_KIND, TypeKind.INTRINSIC);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.BUILTIN, true);
            } else if (SemanticHighlighter.PREDEFINED_CONSTANTS.contains(ctx.id.getText())) {
                tokenDecorator.putProperty(ctx.id, GoAnnotations.NODE_TYPE, NodeType.CONST_REF);
                tokenDecorator.putProperty(ctx.id, GoAnnotations.BUILTIN, true);
            } else {
                @SuppressWarnings("unchecked")
                TerminalNode<Token> node = (TerminalNode<Token>)ParseTrees.findTerminalNode(ctx.children, ctx.id);
                unresolvedIdentifiers.add(node);
            }
        }
    }

    @Override
    public void qualifiedIdentifierExit(qualifiedIdentifierContext ctx) {
        if (ctx.pkg != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.QUALIFIED_EXPR, true);
        } else if (ctx.id != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.UNQUALIFIED_LINK, ctx.id);
        }
    }

    @Override
    public void returnStmtEnter(returnStmtContext ctx) {
    }

    @Override
    public void returnStmtExit(returnStmtContext ctx) {
    }

    @Override
    public void simpleStmtEnter(simpleStmtContext ctx) {
    }

    @Override
    public void simpleStmtExit(simpleStmtContext ctx) {
    }

    @Override
    public void typeAssertionExprEnter(typeAssertionExprContext ctx) {
    }

    @Override
    public void typeAssertionExprExit(typeAssertionExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.t != null) {
            exprType = treeDecorator.getProperty(ctx.t, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new TypeAssertionResultReference(exprType));
    }

    @Override
    public void typeEnter(typeContext ctx) {
    }

    @Override
    public void typeExit(typeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.name != null) {
            codeClass = treeDecorator.getProperty(ctx.name, GoAnnotations.CODE_CLASS);
        } else if (ctx.lit != null) {
            codeClass = treeDecorator.getProperty(ctx.lit, GoAnnotations.CODE_CLASS);
        } else if (ctx.t != null) {
            codeClass = treeDecorator.getProperty(ctx.t, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void interfaceTypeNameEnter(interfaceTypeNameContext ctx) {
    }

    @Override
    public void interfaceTypeNameExit(interfaceTypeNameContext ctx) {
    }

    @Override
    public void continueStmtEnter(continueStmtContext ctx) {
    }

    @Override
    public void continueStmtExit(continueStmtContext ctx) {
    }

    @Override
    public void valueEnter(valueContext ctx) {
    }

    @Override
    public void valueExit(valueContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expr != null) {
            exprType = treeDecorator.getProperty(ctx.expr, GoAnnotations.EXPR_TYPE);
        } else if (ctx.lit != null) {
            exprType = treeDecorator.getProperty(ctx.lit, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void methodDeclEnter(methodDeclContext ctx) {
        pushVarScope();
    }

    @Override
    public void methodDeclExit(methodDeclContext ctx) {
        popVarScope();
    }

    @Override
    public void labeledStmtEnter(labeledStmtContext ctx) {
    }

    @Override
    public void labeledStmtExit(labeledStmtContext ctx) {
    }

    @Override
    public void parametersEnter(parametersContext ctx) {
    }

    @Override
    public void parametersExit(parametersContext ctx) {
    }

    @Override
    public void deferStmtEnter(deferStmtContext ctx) {
    }

    @Override
    public void deferStmtExit(deferStmtContext ctx) {
    }

    @Override
    public void keyEnter(keyContext ctx) {
    }

    @Override
    public void keyExit(keyContext ctx) {
    }

    @Override
    public void declarationEnter(declarationContext ctx) {
    }

    @Override
    public void declarationExit(declarationContext ctx) {
    }

    @Override
    public void commCaseEnter(commCaseContext ctx) {
    }

    @Override
    public void commCaseExit(commCaseContext ctx) {
    }

    @Override
    public void builtinArgsEnter(builtinArgsContext ctx) {
    }

    @Override
    public void builtinArgsExit(builtinArgsContext ctx) {
    }

    @Override
    public void conditionEnter(conditionContext ctx) {
    }

    @Override
    public void conditionExit(conditionContext ctx) {
        assert ctx.getChildCount() <= 1;
        assert ctx.getChildCount() == 0 || ctx.getChild(0) instanceof expressionContext;

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.getChild(0) != null) {
            exprType = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void conversionOrCallExprEnter(conversionOrCallExprContext ctx) {
    }

    @Override
    public void conversionOrCallExprExit(conversionOrCallExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.conv != null) {
            exprType = new ConversionOrCallResultReference(treeDecorator.getProperty(ctx.conv, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void labelEnter(labelContext ctx) {
        if (ctx.name == null) {
            return;
        }

        boolean definition = ParseTrees.isInContexts(ctx, false, GoParser.RULE_label, GoParser.RULE_labeledStmt);
        if (definition) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.LABEL_DECL);
            visibleLabels.peek().put(ctx.name.getText(), ctx.name);
        } else {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.LABEL_REF);
            unresolvedLabels.peek().add(ctx.name);
        }
    }

    @Override
    public void labelExit(labelContext ctx) {
    }

    @Override
    public void elementTypeEnter(elementTypeContext ctx) {
    }

    @Override
    public void elementTypeExit(elementTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.typ != null) {
            codeClass = treeDecorator.getProperty(ctx.typ, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void functionDeclEnter(functionDeclContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.FUNC_DECL);
            visibleFunctions.peek().put(ctx.name.getText(), ctx.name);
        }

        pushVarScope();
    }

    @Override
    public void functionDeclExit(functionDeclContext ctx) {
        popVarScope();
    }

    @Override
    public void statementEnter(statementContext ctx) {
    }

    @Override
    public void statementExit(statementContext ctx) {
    }

    @Override
    public void pointerTypeEnter(pointerTypeContext ctx) {
    }

    @Override
    public void pointerTypeExit(pointerTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.typ != null) {
            elemClass = treeDecorator.getProperty(ctx.typ, GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new PointerTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.POINTER);
    }

    @Override
    public void addAssignOpEnter(addAssignOpContext ctx) {
    }

    @Override
    public void addAssignOpExit(addAssignOpContext ctx) {
    }

    @Override
    public void sourceFileEnter(sourceFileContext ctx) {
    }

    @Override
    public void sourceFileExit(sourceFileContext ctx) {
    }

    @Override
    public void sliceExprEnter(sliceExprContext ctx) {
    }

    @Override
    public void sliceExprExit(sliceExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = new SliceExpressionTypeReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void baseTypeNameEnter(baseTypeNameContext ctx) {
        if (ctx.name != null) {
            @SuppressWarnings("unchecked")
            TerminalNode<Token> node = (TerminalNode<Token>)ParseTrees.findTerminalNode(ctx.children, ctx.name);
            unresolvedIdentifiers.add(node);
        }
    }

    @Override
    public void baseTypeNameExit(baseTypeNameContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.name != null) {
            codeClass = new ReceiverTypeReference(ctx.name, treeDecorator.getProperty(ctx, GoAnnotations.POINTER_RECEIVER));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void methodExprEnter(methodExprContext ctx) {
    }

    @Override
    public void methodExprExit(methodExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void elementIndexEnter(elementIndexContext ctx) {
    }

    @Override
    public void elementIndexExit(elementIndexContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.index != null) {
            exprType = treeDecorator.getProperty(ctx.index, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void typeListEnter(typeListContext ctx) {
    }

    @Override
    public void typeListExit(typeListContext ctx) {
    }

    @Override
    public void incDecStmtEnter(incDecStmtContext ctx) {
    }

    @Override
    public void incDecStmtExit(incDecStmtContext ctx) {
    }

    @Override
    public void builtinCallEnter(builtinCallContext ctx) {
        if (ctx.name != null) {
            if (SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(ctx.name.getText())) {
                tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.FUNC_REF);
                tokenDecorator.putProperty(ctx.name, GoAnnotations.BUILTIN, true);
            } else {
                @SuppressWarnings("unchecked")
                TerminalNode<Token> node = (TerminalNode<Token>)ParseTrees.findTerminalNode(ctx.children, ctx.name);
                unresolvedIdentifiers.add(node);
            }
        }
    }

    @Override
    public void builtinCallExit(builtinCallContext ctx) {
        CodeElementReference typeArgument = CodeElementReference.UNKNOWN;
        builtinArgsContext args = ctx.args;
        if (args != null) {
            if (args.typeArg != null) {
                typeArgument = treeDecorator.getProperty(args.typeArg, GoAnnotations.CODE_CLASS);
            } else {
                expressionListContext exprList = args.args;
                if (exprList != null) {
                    List<expressionContext> exprs = exprList.expressions;
                    if (exprs != null && !exprs.isEmpty()) {
                        typeArgument = treeDecorator.getProperty(exprs.get(0), GoAnnotations.EXPR_TYPE);
                        if (typeArgument == null) {
                            typeArgument = treeDecorator.getProperty(exprs.get(0), GoAnnotations.CODE_CLASS);
                        }
                    }
                }
            }
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new BuiltinCallResultReference(ctx.name, typeArgument));
    }

    @Override
    public void constDeclEnter(constDeclContext ctx) {
    }

    @Override
    public void constDeclExit(constDeclContext ctx) {
    }

    @Override
    public void resultEnter(resultContext ctx) {
    }

    @Override
    public void resultExit(resultContext ctx) {
    }

    @Override
    public void andExprEnter(andExprContext ctx) {
    }

    @Override
    public void andExprExit(andExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void structTypeEnter(structTypeContext ctx) {
    }

    @Override
    public void structTypeExit(structTypeContext ctx) {
        CodeElementReference codeClass = new StructTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.STRUCT);
    }

    @Override
    public void varDeclEnter(varDeclContext ctx) {
    }

    @Override
    public void varDeclExit(varDeclContext ctx) {
    }

    @Override
    public void initStmtEnter(initStmtContext ctx) {
    }

    @Override
    public void initStmtExit(initStmtContext ctx) {
    }

    @Override
    public void identifierListEnter(identifierListContext ctx) {
        NodeType nodeType = treeDecorator.getProperty(ctx, GoAnnotations.NODE_TYPE);
        VarKind varType = treeDecorator.getProperty(ctx, GoAnnotations.VAR_TYPE);
        boolean variadic = treeDecorator.getProperty(ctx, GoAnnotations.VARIADIC);
        ParserRuleContext<Token> explicitType = treeDecorator.getProperty(ctx, GoAnnotations.EXPLICIT_TYPE);
        boolean global =
            (varType != VarKind.LOCAL && varType != VarKind.RECEIVER && varType != VarKind.PARAMETER && varType != VarKind.RETURN)
            || treeDecorator.getProperty(ctx, GoAnnotations.GLOBAL);

        if (nodeType == NodeType.VAR_DECL) {
            if (varType == VarKind.UNDEFINED) {
                return;
            }
        } else if (nodeType == NodeType.CONST_DECL) {
            // nothing special to do here
        } else {
            return;
        }

        for (Token token : ParseTrees.emptyIfNull(ctx.ids)) {
            if (nodeType == NodeType.VAR_DECL) {
                if (varType != VarKind.FIELD) {
                    visibleLocals.peek().put(token.getText(), token);
                }
            } else {
                assert nodeType == NodeType.CONST_DECL;
                visibleConstants.peek().put(token.getText(), token);
            }

            tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, nodeType);

            if (varType != null) {
                tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, varType);
            }

            if (variadic) {
                tokenDecorator.putProperty(token, GoAnnotations.VARIADIC, variadic);
            }

            if (explicitType != null) {
                tokenDecorator.putProperty(token, GoAnnotations.EXPLICIT_TYPE, explicitType);
            }

            if (global) {
                tokenDecorator.putProperty(token, GoAnnotations.GLOBAL, global);
            }
        }
    }

    @Override
    public void identifierListExit(identifierListContext ctx) {
    }

    @Override
    public void sliceTypeEnter(sliceTypeContext ctx) {
    }

    @Override
    public void sliceTypeExit(sliceTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.elemType != null) {
            elemClass = treeDecorator.getProperty(ctx.elemType, GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new SliceTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.SLICE);
    }

    @Override
    public void compareExprEnter(compareExprContext ctx) {
    }

    @Override
    public void compareExprExit(compareExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void importDeclEnter(importDeclContext ctx) {
    }

    @Override
    public void importDeclExit(importDeclContext ctx) {
    }

    @Override
    public void elementListEnter(elementListContext ctx) {
    }

    @Override
    public void elementListExit(elementListContext ctx) {
    }

    @Override
    public void keyTypeEnter(keyTypeContext ctx) {
    }

    @Override
    public void keyTypeExit(keyTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.t != null) {
            codeClass = treeDecorator.getProperty(ctx.t, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void importPathEnter(importPathContext ctx) {
    }

    @Override
    public void importPathExit(importPathContext ctx) {
    }

    @Override
    public void anonymousFieldEnter(anonymousFieldContext ctx) {
    }

    @Override
    public void anonymousFieldExit(anonymousFieldContext ctx) {
    }

    @Override
    public void addExprEnter(addExprContext ctx) {
    }

    @Override
    public void addExprExit(addExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null && ctx.right == null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.right, GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void expressionStmtEnter(expressionStmtContext ctx) {
    }

    @Override
    public void expressionStmtExit(expressionStmtContext ctx) {
    }

    @Override
    public void sendStmtEnter(sendStmtContext ctx) {
    }

    @Override
    public void sendStmtExit(sendStmtContext ctx) {
    }

    @Override
    public void switchStmtEnter(switchStmtContext ctx) {
    }

    @Override
    public void switchStmtExit(switchStmtContext ctx) {
    }

    @Override
    public void postStmtEnter(postStmtContext ctx) {
    }

    @Override
    public void postStmtExit(postStmtContext ctx) {
    }

    @Override
    public void forStmtEnter(forStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void forStmtExit(forStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void typeSwitchCaseEnter(typeSwitchCaseContext ctx) {
        pushVarScope();
    }

    @Override
    public void typeSwitchCaseExit(typeSwitchCaseContext ctx) {
        popVarScope();
    }

    @Override
    public void rangeClauseEnter(rangeClauseContext ctx) {
        if (ctx.defeq != null) {
            if (ctx.e1 != null && ctx.e1.start == ParseTrees.getStopSymbol(ctx.e1)) {
                Token token = ctx.e1.start;
                visibleLocals.peek().put(token.getText(), token);
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
                tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, VarKind.LOCAL);
                tokenDecorator.putProperty(token, GoAnnotations.IMPLICIT_TYPE, ctx);
                tokenDecorator.putProperty(token, GoAnnotations.IMPLICIT_INDEX, 0);
            }

            if (ctx.e2 != null && ctx.e2.start == ParseTrees.getStopSymbol(ctx.e2)) {
                Token token = ctx.e2.start;
                visibleLocals.peek().put(token.getText(), token);
                tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
                tokenDecorator.putProperty(token, GoAnnotations.VAR_TYPE, VarKind.LOCAL);
                tokenDecorator.putProperty(token, GoAnnotations.IMPLICIT_TYPE, ctx);
                tokenDecorator.putProperty(token, GoAnnotations.IMPLICIT_INDEX, 1);
            }
        }
    }

    @Override
    public void rangeClauseExit(rangeClauseContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = new RangeClauseResultReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void operandEnter(operandContext ctx) {
    }

    @Override
    public void operandExit(operandContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.lit != null) {
            exprType = treeDecorator.getProperty(ctx.lit, GoAnnotations.EXPR_TYPE);
        } else if (ctx.qid != null) {
            exprType = new QualifiedIdentifierElementReference(ctx.qid);
        } else if (ctx.me != null) {
            exprType = treeDecorator.getProperty(ctx.me, GoAnnotations.EXPR_TYPE);
        } else if (ctx.e != null) {
            exprType = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        if (ctx.qid != null) {
            treeDecorator.putProperties(ctx, treeDecorator.getProperties(ctx.qid));
        } else {
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
        }
    }

    @Override
    public void argumentListEnter(argumentListContext ctx) {
    }

    @Override
    public void argumentListExit(argumentListContext ctx) {
    }

    @Override
    public void typeSwitchStmtEnter(typeSwitchStmtContext ctx) {
    }

    @Override
    public void typeSwitchStmtExit(typeSwitchStmtContext ctx) {
    }

    @Override
    public void typeDeclEnter(typeDeclContext ctx) {
    }

    @Override
    public void typeDeclExit(typeDeclContext ctx) {
    }

    @Override
    public void unaryExprEnter(unaryExprContext ctx) {
    }

    @Override
    public void unaryExprExit(unaryExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null) {
            CodeElementReference e = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            exprType = new UnaryExpressionTypeReference(e, ctx.op);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void channelEnter(channelContext ctx) {
    }

    @Override
    public void channelExit(channelContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void literalEnter(literalContext ctx) {
    }

    @Override
    public void literalExit(literalContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.bl != null) {
            exprType = treeDecorator.getProperty(ctx.bl, GoAnnotations.EXPR_TYPE);
        } else if (ctx.cl != null) {
            exprType = treeDecorator.getProperty(ctx.cl, GoAnnotations.EXPR_TYPE);
        } else if (ctx.fl != null) {
            exprType = treeDecorator.getProperty(ctx.fl, GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext<Token> ctx) {
    }

    private static final Set<Class<? extends ParseTree>> EXPR_TYPE_CONTEXTS =
        new HashSet<Class<? extends ParseTree>>() {{
            add(expressionContext.class);
            add(conversionOrCallExprContext.class);
            add(builtinCallContext.class);
            add(selectorExprContext.class);
            add(indexExprContext.class);
            add(sliceExprContext.class);
            add(typeAssertionExprContext.class);
            add(callExprContext.class);
            add(unaryExprContext.class);
            add(multExprContext.class);
            add(addExprContext.class);
            add(compareExprContext.class);
            add(andExprContext.class);
            add(orExprContext.class);
            add(operandContext.class);
            add(literalContext.class);
            add(methodExprContext.class);
            add(basicLiteralContext.class);
            add(compositeLiteralContext.class);
            add(functionLiteralContext.class);
            add(literalValueContext.class);
            add(valueContext.class);
            add(elementIndexContext.class);
            add(conversionContext.class);
            add(arrayLengthContext.class);
            add(channelContext.class);
            add(conditionContext.class);
            add(recvExprContext.class);
            add(rangeClauseContext.class);
        }};

    private static final Set<Class<? extends ParseTree>> CODE_CLASS_CONTEXTS =
        new HashSet<Class<? extends ParseTree>>() {{
            add(typeContext.class);
            add(typeNameContext.class);
            add(typeLiteralContext.class);
            add(arrayTypeContext.class);
            add(structTypeContext.class);
            add(pointerTypeContext.class);
            add(functionTypeContext.class);
            add(interfaceTypeContext.class);
            add(sliceTypeContext.class);
            add(mapTypeContext.class);
            add(channelTypeContext.class);
            add(elementTypeContext.class);
            add(baseTypeContext.class);
            add(keyTypeContext.class);
            add(literalTypeContext.class);
        }};

    @Override
    public void exitEveryRule(ParserRuleContext<Token> ctx) {
        if (EXPR_TYPE_CONTEXTS.contains(ctx.getClass())) {
            if (treeDecorator.getProperty(ctx, GoAnnotations.EXPR_TYPE) == null) {
                LOGGER.log(Level.WARNING, "Expected EXPR_TYPE data for context {0}.", ctx.getClass().getSimpleName());
            }
        }

        if (CODE_CLASS_CONTEXTS.contains(ctx.getClass())) {
            if (treeDecorator.getProperty(ctx, GoAnnotations.CODE_CLASS) == null) {
                LOGGER.log(Level.WARNING, "Expected CODE_TYPE data for context {0}.", ctx.getClass().getSimpleName());
            }
        }
    }

    private void pushVarScope() {
        visibleLocals.push(new HashMap<String, Token>());
        visibleConstants.push(new HashMap<String, Token>());
        visibleFunctions.push(new HashMap<String, Token>());
        visibleTypes.push(new HashMap<String, Token>());
    }

    private void popVarScope() {
        visibleLocals.pop();
        visibleConstants.pop();
        visibleFunctions.pop();
        visibleTypes.pop();
    }

    private Token getVisibleDeclaration(Token reference) {
        Token result = getVisibleLocal(reference);
        result = result != null ? result : getVisibleConstant(reference);
        result = result != null ? result : getVisibleFunction(reference);
        result = result != null ? result : getVisibleType(reference);
        return result;
    }

    private Token getVisibleLocal(Token reference) {
        return getVisibleElement(visibleLocals, reference);
    }

    private Token getVisibleConstant(Token reference) {
        return getVisibleElement(visibleConstants, reference);
    }

    private Token getVisibleFunction(Token reference) {
        return getVisibleElement(visibleFunctions, reference);
    }

    private Token getVisibleType(Token reference) {
        return getVisibleElement(visibleTypes, reference);
    }

    private Token getVisibleElement(Collection<Map<String, Token>> elements, Token reference) {
        for (Map<String, Token> localCollection : elements) {
            Token local = localCollection.get(reference.getText());
            if (local != null) {
                return local;
            }
        }

        return null;
    }

}
