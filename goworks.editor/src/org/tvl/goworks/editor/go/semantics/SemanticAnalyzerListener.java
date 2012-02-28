/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

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
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeArrayModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeChannelModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeMapModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.AddAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.AddExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.AndExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.AnonymousFieldContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ArgumentListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ArrayLengthContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.AssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.AssignmentContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BaseTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BlockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BodyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BreakStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.CallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ChannelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.CommCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.CommClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.CompareExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConditionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConstDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ContinueStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConversionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.DeclarationContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.DeferStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ElementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ElementIndexContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ElementListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ElementTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.EmptyStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExprSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExpressionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ExpressionStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FallthroughStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FieldNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ForClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ForStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.GoStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.GotoStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.IfStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ImportDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ImportPathContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.IncDecStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.IndexExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.InitStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.KeyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.KeyTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.LabelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.LabeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.LiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.LiteralTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.LiteralValueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MethodExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MethodNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MulAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.MultExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.OperandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.OperandExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.OrExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.PackageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ParameterListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ParametersContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.PostStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.RangeClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ReceiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ReceiverTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.RecvExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.RecvStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ResultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ReturnStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SelectStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SelectorExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SendStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SignatureContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SimpleStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SliceExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.StatementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.StructTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.SwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TagContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TopLevelDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.TypeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ValueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.VarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.VarSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBaseBaseListener;
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
                assert qualifier instanceof PackageNameContext;
                String packageName = ((PackageNameContext)qualifier).name.getText();
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

        if (qualifier instanceof ArrayTypeContext) {
            ArrayTypeContext ctx = (ArrayTypeContext)qualifier;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class QualifierTypeResolver extends GoParserBaseBaseListener {

        public boolean resolveType(ParserRuleContext<Token> qualifier) {
            ParseTreeWalker.DEFAULT.walk(this, qualifier);
            return treeDecorator.getProperty(qualifier, GoAnnotations.MODELS) != null;
        }

        @Override
        public void exitArrayType(ArrayTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.MODELS);
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
        public void exitPointerType(PointerTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.baseType(), GoAnnotations.MODELS);
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
        public void exitSliceType(SliceTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.MODELS);
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
        public void exitMapType(MapTypeContext ctx) {
            Collection<? extends CodeElementModel> keyTypes = treeDecorator.getProperty(ctx.keyType(), GoAnnotations.MODELS);
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.MODELS);
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
        public void exitChannelType(ChannelTypeContext ctx) {
            Collection<? extends CodeElementModel> elementTypes = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.MODELS);
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
    public void enterMultExpr(MultExprContext ctx) {
    }

    @Override
    public void exitMultExpr(MultExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null && ctx.right == null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.right, GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterChannelType(ChannelTypeContext ctx) {
    }

    @Override
    public void exitChannelType(ChannelTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.elementType() != null) {
            ChannelKind kind = ChannelKind.SendReceive;
            if (ctx.send != null) {
                kind = ChannelKind.Send;
            } else if (ctx.recv != null) {
                kind = ChannelKind.Receive;
            }

            codeClass = new ChannelTypeReference(treeDecorator.getProperty(ctx.elementType(), GoAnnotations.CODE_CLASS), kind);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.CHANNEL);
    }

    @Override
    public void enterMulAssignOp(MulAssignOpContext ctx) {
    }

    @Override
    public void exitMulAssignOp(MulAssignOpContext ctx) {
    }

    @Override
    public void enterPackageName(PackageNameContext ctx) {
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
    public void exitPackageName(PackageNameContext ctx) {
        // not qualified!
    }

    @Override
    public void enterReceiver(ReceiverContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            tokenDecorator.putProperty(ctx.name, GoAnnotations.VAR_TYPE, VarKind.RECEIVER);
            tokenDecorator.putProperty(ctx.name, GoAnnotations.EXPLICIT_TYPE, ctx.baseTypeName());
            visibleLocals.peek().put(ctx.name.getText(), ctx.name);
        }

        if (ctx.ptr != null && ctx.baseTypeName() != null) {
            treeDecorator.putProperty(ctx.baseTypeName(), GoAnnotations.POINTER_RECEIVER, true);
        }
    }

    @Override
    public void exitReceiver(ReceiverContext ctx) {
    }

    @Override
    public void enterArrayType(ArrayTypeContext ctx) {
    }

    @Override
    public void exitArrayType(ArrayTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.elementType() != null) {
            elemClass = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.CODE_CLASS);
        }

        CodeElementReference arrayClass = new ArrayTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, arrayClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.ARRAY);
    }

    @Override
    public void enterExpressionList(ExpressionListContext ctx) {
    }

    @Override
    public void exitExpressionList(ExpressionListContext ctx) {
    }

    @Override
    public void enterTag(TagContext ctx) {
    }

    @Override
    public void exitTag(TagContext ctx) {
    }

    @Override
    public void enterFallthroughStmt(FallthroughStmtContext ctx) {
    }

    @Override
    public void exitFallthroughStmt(FallthroughStmtContext ctx) {
    }

    @Override
    public void enterSelectorExpr(SelectorExprContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.QUALIFIED_EXPR, true);
            if (ctx.e != null) {
                tokenDecorator.putProperty(ctx.name, GoAnnotations.QUALIFIER, ctx.e);
            }
        }
    }

    @Override
    public void exitSelectorExpr(SelectorExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        TerminalNode<Token> node = ctx.IDENTIFIER();
        if (node != null) {
            assert ctx.e != null;
            exprType = new SelectedElementReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE), node.getSymbol());
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        if (ctx.name != null) {
            unresolvedQualifiedIdentifiers.add(node);
        }
    }

    @Override
    public void enterParameterList(ParameterListContext ctx) {
    }

    @Override
    public void exitParameterList(ParameterListContext ctx) {
    }

    @Override
    public void enterReceiverType(ReceiverTypeContext ctx) {
    }

    @Override
    public void exitReceiverType(ReceiverTypeContext ctx) {
    }

    @Override
    public void enterFieldName(FieldNameContext ctx) {
    }

    @Override
    public void exitFieldName(FieldNameContext ctx) {
    }

    @Override
    public void enterIfStmt(IfStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitIfStmt(IfStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void enterMethodName(MethodNameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.METHOD_DECL);
        }
    }

    @Override
    public void exitMethodName(MethodNameContext ctx) {
    }

    @Override
    public void enterSignature(SignatureContext ctx) {
    }

    @Override
    public void exitSignature(SignatureContext ctx) {
    }

    @Override
    public void enterMapType(MapTypeContext ctx) {
    }

    @Override
    public void exitMapType(MapTypeContext ctx) {
        CodeElementReference keyType = CodeElementReference.UNKNOWN;
        if (ctx.keyType() != null) {
            keyType = treeDecorator.getProperty(ctx.keyType(), GoAnnotations.CODE_CLASS);
        }

        CodeElementReference valueType = CodeElementReference.UNKNOWN;
        if (ctx.elementType() != null) {
            valueType = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new MapTypeReference(keyType, valueType);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.MAP);
    }

    @Override
    public void enterElement(ElementContext ctx) {
    }

    @Override
    public void exitElement(ElementContext ctx) {
    }

    @Override
    public void enterCallExpr(CallExprContext ctx) {
    }

    @Override
    public void exitCallExpr(CallExprContext ctx) {
        CodeElementReference returnType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            boolean builtin = false;
            if (ctx.e.start != null && ctx.e.start == ctx.e.stop) {
                String methodName = ctx.e.start.getText();
                builtin = SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(methodName);
            }

            if (builtin) {
                CodeElementReference typeArgument = CodeElementReference.UNKNOWN;
                ArgumentListContext args = ctx.args;
                if (args != null) {
                    ExpressionListContext exprs = args.expressionList();
                    if (exprs != null && !exprs.expression().isEmpty()) {
                        typeArgument = treeDecorator.getProperty(exprs.expression(0), GoAnnotations.CODE_CLASS);
                        if (typeArgument == null) {
                            typeArgument = treeDecorator.getProperty(exprs.expression(0), GoAnnotations.EXPR_TYPE);
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
    public void enterTypeCaseClause(TypeCaseClauseContext ctx) {
    }

    @Override
    public void exitTypeCaseClause(TypeCaseClauseContext ctx) {
    }

    @Override
    public void enterExprCaseClause(ExprCaseClauseContext ctx) {
    }

    @Override
    public void exitExprCaseClause(ExprCaseClauseContext ctx) {
    }

    @Override
    public void enterTypeSwitchGuard(TypeSwitchGuardContext ctx) {
    }

    @Override
    public void exitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
    }

    @Override
    public void enterFunctionLiteral(FunctionLiteralContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        popVarScope();
    }

    @Override
    public void enterOrExpr(OrExprContext ctx) {
    }

    @Override
    public void exitOrExpr(OrExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void enterRecvExpr(RecvExprContext ctx) {
    }

    @Override
    public void exitRecvExpr(RecvExprContext ctx) {
        assert ctx.getChildCount() <= 1;
        assert ctx.getChildCount() == 0 || ctx.getChild(0) instanceof ExpressionContext;

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.getChild(0) != null) {
            exprType = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterTopLevelDecl(TopLevelDeclContext ctx) {
    }

    @Override
    public void exitTopLevelDecl(TopLevelDeclContext ctx) {
    }

    @Override
    public void enterMethodSpec(MethodSpecContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitMethodSpec(MethodSpecContext ctx) {
        popVarScope();
    }

    @Override
    public void enterConstSpec(ConstSpecContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.CONST_DECL);
            boolean global = ParseTrees.isInContexts(ctx, false, GoParser.RULE_constSpec, GoParser.RULE_constDecl, GoParser.RULE_declaration, GoParser.RULE_topLevelDecl);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.GLOBAL, global);
        }
    }

    @Override
    public void exitConstSpec(ConstSpecContext ctx) {
    }

    @Override
    public void enterCompositeLiteral(CompositeLiteralContext ctx) {
    }

    @Override
    public void exitCompositeLiteral(CompositeLiteralContext ctx) {
        CodeElementReference exprType;
        if (ctx.literalType() != null) {
            exprType = treeDecorator.getProperty(ctx.literalType(), GoAnnotations.CODE_CLASS);
        } else {
            exprType = CodeElementReference.UNKNOWN;
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
        if (ctx.literalValue() != null) {
            treeDecorator.putProperty(ctx.literalValue(), GoAnnotations.EXPR_TYPE, exprType);
        }
    }

    @Override
    public void enterForClause(ForClauseContext ctx) {
    }

    @Override
    public void exitForClause(ForClauseContext ctx) {
    }

    @Override
    public void enterShortVarDecl(ShortVarDeclContext ctx) {
        if (ctx.idList != null) {
            treeDecorator.putProperty(ctx.idList, GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.idList, GoAnnotations.VAR_TYPE, VarKind.LOCAL);
            if (ctx.exprList != null) {
                int varCount = ctx.idList.IDENTIFIER().size();
                int exprCount = ctx.exprList.expression().size();
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expression(0));
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expression(i));
                        if (varCount > 1) {
                            tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void exitShortVarDecl(ShortVarDeclContext ctx) {
    }

    @Override
    public void enterGotoStmt(GotoStmtContext ctx) {
    }

    @Override
    public void exitGotoStmt(GotoStmtContext ctx) {
    }

    @Override
    public void enterArrayLength(ArrayLengthContext ctx) {
    }

    @Override
    public void exitArrayLength(ArrayLengthContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterInterfaceType(InterfaceTypeContext ctx) {
    }

    @Override
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        CodeElementReference codeClass = new InterfaceTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.INTERFACE);
    }

    @Override
    public void enterConversion(ConversionContext ctx) {
    }

    @Override
    public void exitConversion(ConversionContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            exprType = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterBlock(BlockContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitBlock(BlockContext ctx) {
        popVarScope();
    }

    @Override
    public void enterBreakStmt(BreakStmtContext ctx) {
    }

    @Override
    public void exitBreakStmt(BreakStmtContext ctx) {
    }

    @Override
    public void enterEmptyStmt(EmptyStmtContext ctx) {
    }

    @Override
    public void exitEmptyStmt(EmptyStmtContext ctx) {
    }

    @Override
    public void enterFunctionType(FunctionTypeContext ctx) {
        if (!ParseTrees.isInContexts(ctx, false, GoParser.RULE_functionType, GoParser.RULE_functionLiteral)) {
            pushVarScope();
        }
    }

    @Override
    public void exitFunctionType(FunctionTypeContext ctx) {
        CodeElementReference codeClass = new FunctionTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        if (!ParseTrees.isInContexts(ctx, false, GoParser.RULE_functionType, GoParser.RULE_functionLiteral)) {
            popVarScope();
        }

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.FUNCTION);
    }

    @Override
    public void enterBaseType(BaseTypeContext ctx) {
    }

    @Override
    public void exitBaseType(BaseTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterFieldDecl(FieldDeclContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.FIELD);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.EXPLICIT_TYPE, ctx.type());
        }
    }

    @Override
    public void exitFieldDecl(FieldDeclContext ctx) {
    }

    @Override
    public void enterExprSwitchStmt(ExprSwitchStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitExprSwitchStmt(ExprSwitchStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void enterGoStmt(GoStmtContext ctx) {
    }

    @Override
    public void exitGoStmt(GoStmtContext ctx) {
    }

    @Override
    public void enterParameterDecl(ParameterDeclContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            if (ctx.ellip != null) {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VARIADIC, true);
            }

            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.EXPLICIT_TYPE, ctx.type());
            if (ParseTrees.isInContexts(ctx, false, GoParser.RULE_parameterDecl, GoParser.RULE_parameterList, GoParser.RULE_parameters, GoParser.RULE_result)) {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.RETURN);
            } else {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.PARAMETER);
            }

            for (TerminalNode<Token> id : ctx.identifierList().IDENTIFIER()) {
                visibleLocals.peek().put(id.getSymbol().getText(), id.getSymbol());
            }
        }
    }

    @Override
    public void exitParameterDecl(ParameterDeclContext ctx) {
    }

    @Override
    public void enterBasicLiteral(BasicLiteralContext ctx) {
    }

    @Override
    public void exitBasicLiteral(BasicLiteralContext ctx) {
        assert ctx.stop == null || ctx.start == ctx.stop;
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralTypeReference(ctx.start));
    }

    @Override
    public void enterExprSwitchCase(ExprSwitchCaseContext ctx) {
    }

    @Override
    public void exitExprSwitchCase(ExprSwitchCaseContext ctx) {
    }

    @Override
    public void enterTypeLiteral(TypeLiteralContext ctx) {
    }

    @Override
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.getChildCount() == 1) {
            codeClass = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterSelectStmt(SelectStmtContext ctx) {
    }

    @Override
    public void exitSelectStmt(SelectStmtContext ctx) {
    }

    @Override
    public void enterImportSpec(ImportSpecContext ctx) {
        String name = null;
        String path = null;
        Token target = null;
        if (ctx.importPath() != null && ctx.importPath().StringLiteral() != null) {
            target = ctx.importPath().StringLiteral().getSymbol();
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
        } else if (ctx.packageName != null) {
            if (ctx.packageName.name != null) {
                target = ctx.packageName.name;
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
    public void exitImportSpec(ImportSpecContext ctx) {
    }

    @Override
    public void enterTypeName(TypeNameContext ctx) {
    }

    @Override
    public void exitTypeName(TypeNameContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.qualifiedIdentifier() != null) {
            codeClass = new QualifiedIdentifierElementReference(ctx.qualifiedIdentifier());
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        if (ctx.qualifiedIdentifier() == null) {
            return;
        }

        if (treeDecorator.getProperty(ctx.qualifiedIdentifier(), GoAnnotations.QUALIFIED_EXPR)) {
            treeDecorator.putProperty(ctx, GoAnnotations.QUALIFIED_EXPR, true);
        } else {
            treeDecorator.putProperty(ctx, GoAnnotations.UNQUALIFIED_LINK, treeDecorator.getProperty(ctx.qualifiedIdentifier(), GoAnnotations.UNQUALIFIED_LINK));
        }
    }

    @Override
    public void enterLiteralType(LiteralTypeContext ctx) {
    }

    @Override
    public void exitLiteralType(LiteralTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.getChildCount() == 1) {
            ParseTree<Token> child = ctx.getChild(0);
            assert child instanceof StructTypeContext
                || child instanceof ArrayTypeContext
                || child instanceof SliceTypeContext
                || child instanceof MapTypeContext
                || child instanceof TypeNameContext;
            codeClass = treeDecorator.getProperty(child, GoAnnotations.CODE_CLASS);
        } else if (ctx.getChildCount() > 1) {
            ParseTree<Token> firstChild = ctx.getChild(0);
            assert firstChild instanceof TerminalNode<?>
                && ((TerminalNode<?>)firstChild).getSymbol() instanceof Token
                && ((Token)((TerminalNode<?>)firstChild).getSymbol()).getType() == GoParser.LeftBrack;

            ParseTree<Token> lastChild = ctx.getChild(ctx.getChildCount() - 1);
            assert lastChild instanceof ElementTypeContext;
            codeClass = new ArrayTypeReference(treeDecorator.getProperty(lastChild, GoAnnotations.CODE_CLASS));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterAssignment(AssignmentContext ctx) {
    }

    @Override
    public void exitAssignment(AssignmentContext ctx) {
    }

    @Override
    public void enterAssignOp(AssignOpContext ctx) {
    }

    @Override
    public void exitAssignOp(AssignOpContext ctx) {
    }

    @Override
    public void enterRecvStmt(RecvStmtContext ctx) {
    }

    @Override
    public void exitRecvStmt(RecvStmtContext ctx) {
    }

    @Override
    public void enterTypeSpec(TypeSpecContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.TYPE_DECL);
            visibleTypes.peek().put(ctx.IDENTIFIER().getSymbol().getText(), ctx.IDENTIFIER().getSymbol());
        }
    }

    @Override
    public void exitTypeSpec(TypeSpecContext ctx) {
    }

    @Override
    public void enterPackageClause(PackageClauseContext ctx) {
    }

    @Override
    public void exitPackageClause(PackageClauseContext ctx) {
    }

    @Override
    public void enterLiteralValue(LiteralValueContext ctx) {
    }

    @Override
    public void exitLiteralValue(LiteralValueContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralValueElementReference());
    }

    @Override
    public void enterIndexExpr(IndexExprContext ctx) {
    }

    @Override
    public void exitIndexExpr(IndexExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null) {
            CodeElementReference arrayType = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            exprType = new ArrayElementTypeReference(arrayType);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterVarSpec(VarSpecContext ctx) {
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
                int varCount = ctx.idList.IDENTIFIER().size();
                int exprCount = ctx.exprList.expression().size();
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expression(0));
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.exprList.expression(i));
                        tokenDecorator.putProperty(ctx.idList.IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                }
            }
        }
    }

    @Override
    public void exitVarSpec(VarSpecContext ctx) {
    }

    @Override
    public void enterOperandExpr(OperandExprContext ctx) {
    }

    @Override
    public void exitOperandExpr(OperandExprContext ctx) {
        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof OperandContext) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof OperandContext) {
            treeDecorator.putProperties(ctx, treeDecorator.getProperties(ctx.getChild(0)));
        } else {
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
        }
    }

    @Override
    public void enterBuiltinCallExpr(BuiltinCallExprContext ctx) {
    }

    @Override
    public void exitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        if (ctx.getChildCount() == 1 && ctx.getChild(0) instanceof BuiltinCallContext) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
    }

    @Override
    public void enterBody(BodyContext ctx) {
        pushVarScope();
        visibleLabels.push(new HashMap<String, Token>());
        unresolvedLabels.push(new ArrayList<Token>());
    }

    @Override
    public void exitBody(BodyContext ctx) {
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
    public void enterCommClause(CommClauseContext ctx) {
    }

    @Override
    public void exitCommClause(CommClauseContext ctx) {
    }

    @Override
    public void enterQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        if (ctx.packageName() != null) {
            if (ctx.IDENTIFIER() != null) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIED_EXPR, true);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIER, ctx.packageName());
                unresolvedQualifiedIdentifiers.add(ctx.IDENTIFIER());
            }

            // check known imports
            if (ctx.packageName().name != null) {
                List<? extends Token> imports = ParseTrees.emptyIfNull(importedPackages.get(ctx.packageName().name.getText()));
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
                    treeDecorator.putProperty(ctx.packageName(), GoAnnotations.LOCAL_TARGET, bestImport);
                    if (resolvedImport) {
                        treeDecorator.putProperty(ctx.packageName(), GoAnnotations.RESOLVED, true);
                    }
                }
            }
        } else if (ctx.IDENTIFIER() != null) {
            assert ctx.packageName() == null;
            Token local = getVisibleLocal(ctx.IDENTIFIER().getSymbol());
            if (local != null) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.VAR_REF);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.LOCAL_TARGET, local);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.RESOLVED, true);
                VarKind varType = tokenDecorator.getProperty(local, GoAnnotations.VAR_TYPE);
                if (varType != VarKind.UNDEFINED) {
                    tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.VAR_TYPE, varType);
                }
                return;
            }

            // check built-ins
            if (SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(ctx.IDENTIFIER().getSymbol().getText())) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.FUNC_REF);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.BUILTIN, true);
            } else if (SemanticHighlighter.PREDEFINED_TYPES.contains(ctx.IDENTIFIER().getSymbol().getText())) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.TYPE_REF);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.TYPE_KIND, TypeKind.INTRINSIC);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.BUILTIN, true);
            } else if (SemanticHighlighter.PREDEFINED_CONSTANTS.contains(ctx.IDENTIFIER().getSymbol().getText())) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.CONST_REF);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.BUILTIN, true);
            } else {
                unresolvedIdentifiers.add(ctx.IDENTIFIER());
            }
        }
    }

    @Override
    public void exitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        if (ctx.packageName() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.QUALIFIED_EXPR, true);
        } else if (ctx.IDENTIFIER() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.UNQUALIFIED_LINK, ctx.IDENTIFIER().getSymbol());
        }
    }

    @Override
    public void enterReturnStmt(ReturnStmtContext ctx) {
    }

    @Override
    public void exitReturnStmt(ReturnStmtContext ctx) {
    }

    @Override
    public void enterSimpleStmt(SimpleStmtContext ctx) {
    }

    @Override
    public void exitSimpleStmt(SimpleStmtContext ctx) {
    }

    @Override
    public void enterTypeAssertionExpr(TypeAssertionExprContext ctx) {
    }

    @Override
    public void exitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.t != null) {
            exprType = treeDecorator.getProperty(ctx.t, GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new TypeAssertionResultReference(exprType));
    }

    @Override
    public void enterType(TypeContext ctx) {
    }

    @Override
    public void exitType(TypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.typeName() != null) {
            codeClass = treeDecorator.getProperty(ctx.typeName(), GoAnnotations.CODE_CLASS);
        } else if (ctx.typeLiteral() != null) {
            codeClass = treeDecorator.getProperty(ctx.typeLiteral(), GoAnnotations.CODE_CLASS);
        } else if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterInterfaceTypeName(InterfaceTypeNameContext ctx) {
    }

    @Override
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
    }

    @Override
    public void enterContinueStmt(ContinueStmtContext ctx) {
    }

    @Override
    public void exitContinueStmt(ContinueStmtContext ctx) {
    }

    @Override
    public void enterValue(ValueContext ctx) {
    }

    @Override
    public void exitValue(ValueContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        } else if (ctx.literalValue() != null) {
            exprType = treeDecorator.getProperty(ctx.literalValue(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterMethodDecl(MethodDeclContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitMethodDecl(MethodDeclContext ctx) {
        popVarScope();
    }

    @Override
    public void enterLabeledStmt(LabeledStmtContext ctx) {
    }

    @Override
    public void exitLabeledStmt(LabeledStmtContext ctx) {
    }

    @Override
    public void enterParameters(ParametersContext ctx) {
    }

    @Override
    public void exitParameters(ParametersContext ctx) {
    }

    @Override
    public void enterDeferStmt(DeferStmtContext ctx) {
    }

    @Override
    public void exitDeferStmt(DeferStmtContext ctx) {
    }

    @Override
    public void enterKey(KeyContext ctx) {
    }

    @Override
    public void exitKey(KeyContext ctx) {
    }

    @Override
    public void enterDeclaration(DeclarationContext ctx) {
    }

    @Override
    public void exitDeclaration(DeclarationContext ctx) {
    }

    @Override
    public void enterCommCase(CommCaseContext ctx) {
    }

    @Override
    public void exitCommCase(CommCaseContext ctx) {
    }

    @Override
    public void enterBuiltinArgs(BuiltinArgsContext ctx) {
    }

    @Override
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
    }

    @Override
    public void enterCondition(ConditionContext ctx) {
    }

    @Override
    public void exitCondition(ConditionContext ctx) {
        assert ctx.getChildCount() <= 1;
        assert ctx.getChildCount() == 0 || ctx.getChild(0) instanceof ExpressionContext;

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.getChild(0) != null) {
            exprType = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterConversionOrCallExpr(ConversionOrCallExprContext ctx) {
    }

    @Override
    public void exitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.conv != null) {
            exprType = new ConversionOrCallResultReference(treeDecorator.getProperty(ctx.conv, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterLabel(LabelContext ctx) {
        if (ctx.IDENTIFIER() == null) {
            return;
        }

        boolean definition = ParseTrees.isInContexts(ctx, false, GoParser.RULE_label, GoParser.RULE_labeledStmt);
        if (definition) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.LABEL_DECL);
            visibleLabels.peek().put(ctx.IDENTIFIER().getSymbol().getText(), ctx.IDENTIFIER().getSymbol());
        } else {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.LABEL_REF);
            unresolvedLabels.peek().add(ctx.IDENTIFIER().getSymbol());
        }
    }

    @Override
    public void exitLabel(LabelContext ctx) {
    }

    @Override
    public void enterElementType(ElementTypeContext ctx) {
    }

    @Override
    public void exitElementType(ElementTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterFunctionDecl(FunctionDeclContext ctx) {
        if (ctx.name != null) {
            tokenDecorator.putProperty(ctx.name, GoAnnotations.NODE_TYPE, NodeType.FUNC_DECL);
            visibleFunctions.peek().put(ctx.name.getText(), ctx.name);
        }

        pushVarScope();
    }

    @Override
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        popVarScope();
    }

    @Override
    public void enterStatement(StatementContext ctx) {
    }

    @Override
    public void exitStatement(StatementContext ctx) {
    }

    @Override
    public void enterPointerType(PointerTypeContext ctx) {
    }

    @Override
    public void exitPointerType(PointerTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.baseType() != null) {
            elemClass = treeDecorator.getProperty(ctx.baseType(), GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new PointerTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.POINTER);
    }

    @Override
    public void enterAddAssignOp(AddAssignOpContext ctx) {
    }

    @Override
    public void exitAddAssignOp(AddAssignOpContext ctx) {
    }

    @Override
    public void enterSourceFile(SourceFileContext ctx) {
    }

    @Override
    public void exitSourceFile(SourceFileContext ctx) {
    }

    @Override
    public void enterSliceExpr(SliceExprContext ctx) {
    }

    @Override
    public void exitSliceExpr(SliceExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = new SliceExpressionTypeReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterBaseTypeName(BaseTypeNameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            unresolvedIdentifiers.add(ctx.IDENTIFIER());
        }
    }

    @Override
    public void exitBaseTypeName(BaseTypeNameContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.IDENTIFIER() != null) {
            codeClass = new ReceiverTypeReference(ctx.IDENTIFIER().getSymbol(), treeDecorator.getProperty(ctx, GoAnnotations.POINTER_RECEIVER));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterMethodExpr(MethodExprContext ctx) {
    }

    @Override
    public void exitMethodExpr(MethodExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterElementIndex(ElementIndexContext ctx) {
    }

    @Override
    public void exitElementIndex(ElementIndexContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterTypeList(TypeListContext ctx) {
    }

    @Override
    public void exitTypeList(TypeListContext ctx) {
    }

    @Override
    public void enterIncDecStmt(IncDecStmtContext ctx) {
    }

    @Override
    public void exitIncDecStmt(IncDecStmtContext ctx) {
    }

    @Override
    public void enterBuiltinCall(BuiltinCallContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            if (SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(ctx.IDENTIFIER().getSymbol().getText())) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.FUNC_REF);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.BUILTIN, true);
            } else {
                unresolvedIdentifiers.add(ctx.IDENTIFIER());
            }
        }
    }

    @Override
    public void exitBuiltinCall(BuiltinCallContext ctx) {
        CodeElementReference typeArgument = CodeElementReference.UNKNOWN;
        BuiltinArgsContext args = ctx.builtinArgs();
        if (args != null) {
            if (args.type() != null) {
                typeArgument = treeDecorator.getProperty(args.type(), GoAnnotations.CODE_CLASS);
            } else {
                ExpressionListContext exprList = args.expressionList();
                if (exprList != null) {
                    List<? extends ExpressionContext> exprs = exprList.expression();
                    if (exprs != null && !exprs.isEmpty()) {
                        typeArgument = treeDecorator.getProperty(exprs.get(0), GoAnnotations.EXPR_TYPE);
                        if (typeArgument == null) {
                            typeArgument = treeDecorator.getProperty(exprs.get(0), GoAnnotations.CODE_CLASS);
                        }
                    }
                }
            }
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new BuiltinCallResultReference(ctx.IDENTIFIER().getSymbol(), typeArgument));
    }

    @Override
    public void enterConstDecl(ConstDeclContext ctx) {
    }

    @Override
    public void exitConstDecl(ConstDeclContext ctx) {
    }

    @Override
    public void enterResult(ResultContext ctx) {
    }

    @Override
    public void exitResult(ResultContext ctx) {
    }

    @Override
    public void enterAndExpr(AndExprContext ctx) {
    }

    @Override
    public void exitAndExpr(AndExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void enterStructType(StructTypeContext ctx) {
    }

    @Override
    public void exitStructType(StructTypeContext ctx) {
        CodeElementReference codeClass = new StructTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.STRUCT);
    }

    @Override
    public void enterVarDecl(VarDeclContext ctx) {
    }

    @Override
    public void exitVarDecl(VarDeclContext ctx) {
    }

    @Override
    public void enterInitStmt(InitStmtContext ctx) {
    }

    @Override
    public void exitInitStmt(InitStmtContext ctx) {
    }

    @Override
    public void enterIdentifierList(IdentifierListContext ctx) {
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

        for (TerminalNode<Token> terminalNode : ctx.IDENTIFIER()) {
            Token token = terminalNode.getSymbol();
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
    public void exitIdentifierList(IdentifierListContext ctx) {
    }

    @Override
    public void enterSliceType(SliceTypeContext ctx) {
    }

    @Override
    public void exitSliceType(SliceTypeContext ctx) {
        CodeElementReference elemClass = CodeElementReference.UNKNOWN;
        if (ctx.elementType() != null) {
            elemClass = treeDecorator.getProperty(ctx.elementType(), GoAnnotations.CODE_CLASS);
        }

        CodeElementReference codeClass = new SliceTypeReference(elemClass);
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.SLICE);
    }

    @Override
    public void enterCompareExpr(CompareExprContext ctx) {
    }

    @Override
    public void exitCompareExpr(CompareExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    public void enterImportDecl(ImportDeclContext ctx) {
    }

    @Override
    public void exitImportDecl(ImportDeclContext ctx) {
    }

    @Override
    public void enterElementList(ElementListContext ctx) {
    }

    @Override
    public void exitElementList(ElementListContext ctx) {
    }

    @Override
    public void enterKeyType(KeyTypeContext ctx) {
    }

    @Override
    public void exitKeyType(KeyTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    public void enterImportPath(ImportPathContext ctx) {
    }

    @Override
    public void exitImportPath(ImportPathContext ctx) {
    }

    @Override
    public void enterAnonymousField(AnonymousFieldContext ctx) {
    }

    @Override
    public void exitAnonymousField(AnonymousFieldContext ctx) {
    }

    @Override
    public void enterAddExpr(AddExprContext ctx) {
    }

    @Override
    public void exitAddExpr(AddExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null && ctx.right == null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.right, GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterExpressionStmt(ExpressionStmtContext ctx) {
    }

    @Override
    public void exitExpressionStmt(ExpressionStmtContext ctx) {
    }

    @Override
    public void enterSendStmt(SendStmtContext ctx) {
    }

    @Override
    public void exitSendStmt(SendStmtContext ctx) {
    }

    @Override
    public void enterSwitchStmt(SwitchStmtContext ctx) {
    }

    @Override
    public void exitSwitchStmt(SwitchStmtContext ctx) {
    }

    @Override
    public void enterPostStmt(PostStmtContext ctx) {
    }

    @Override
    public void exitPostStmt(PostStmtContext ctx) {
    }

    @Override
    public void enterForStmt(ForStmtContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitForStmt(ForStmtContext ctx) {
        popVarScope();
    }

    @Override
    public void enterTypeSwitchCase(TypeSwitchCaseContext ctx) {
        pushVarScope();
    }

    @Override
    public void exitTypeSwitchCase(TypeSwitchCaseContext ctx) {
        popVarScope();
    }

    @Override
    public void enterRangeClause(RangeClauseContext ctx) {
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
    public void exitRangeClause(RangeClauseContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = new RangeClauseResultReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterOperand(OperandContext ctx) {
    }

    @Override
    public void exitOperand(OperandContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.literal() != null) {
            exprType = treeDecorator.getProperty(ctx.literal(), GoAnnotations.EXPR_TYPE);
        } else if (ctx.qualifiedIdentifier() != null) {
            exprType = new QualifiedIdentifierElementReference(ctx.qualifiedIdentifier());
        } else if (ctx.methodExpr() != null) {
            exprType = treeDecorator.getProperty(ctx.methodExpr(), GoAnnotations.EXPR_TYPE);
        } else if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        if (ctx.qualifiedIdentifier() != null) {
            treeDecorator.putProperties(ctx, treeDecorator.getProperties(ctx.qualifiedIdentifier()));
        } else {
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParserBase(null)));
        }
    }

    @Override
    public void enterArgumentList(ArgumentListContext ctx) {
    }

    @Override
    public void exitArgumentList(ArgumentListContext ctx) {
    }

    @Override
    public void enterTypeSwitchStmt(TypeSwitchStmtContext ctx) {
    }

    @Override
    public void exitTypeSwitchStmt(TypeSwitchStmtContext ctx) {
    }

    @Override
    public void enterTypeDecl(TypeDeclContext ctx) {
    }

    @Override
    public void exitTypeDecl(TypeDeclContext ctx) {
    }

    @Override
    public void enterUnaryExpr(UnaryExprContext ctx) {
    }

    @Override
    public void exitUnaryExpr(UnaryExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.e != null && ctx.op != null) {
            CodeElementReference e = treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE);
            exprType = new UnaryExpressionTypeReference(e, ctx.op);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterChannel(ChannelContext ctx) {
    }

    @Override
    public void exitChannel(ChannelContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void enterLiteral(LiteralContext ctx) {
    }

    @Override
    public void exitLiteral(LiteralContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.basicLiteral() != null) {
            exprType = treeDecorator.getProperty(ctx.basicLiteral(), GoAnnotations.EXPR_TYPE);
        } else if (ctx.compositeLiteral() != null) {
            exprType = treeDecorator.getProperty(ctx.compositeLiteral(), GoAnnotations.EXPR_TYPE);
        } else if (ctx.functionLiteral() != null) {
            exprType = treeDecorator.getProperty(ctx.functionLiteral(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    public void visitTerminal(ParseTree.TerminalNode<? extends Token> node) {
    }

    @Override
    public void visitErrorNode(ParseTree.ErrorNode<? extends Token> node) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
    }

    private static final Set<Class<? extends ParserRuleContext<?>>> EXPR_TYPE_CONTEXTS =
        new HashSet<Class<? extends ParserRuleContext<?>>>() {{
            add(ExpressionContext.class);
            add(ConversionOrCallExprContext.class);
            add(BuiltinCallContext.class);
            add(SelectorExprContext.class);
            add(IndexExprContext.class);
            add(SliceExprContext.class);
            add(TypeAssertionExprContext.class);
            add(CallExprContext.class);
            add(UnaryExprContext.class);
            add(MultExprContext.class);
            add(AddExprContext.class);
            add(CompareExprContext.class);
            add(AndExprContext.class);
            add(OrExprContext.class);
            add(OperandContext.class);
            add(LiteralContext.class);
            add(MethodExprContext.class);
            add(BasicLiteralContext.class);
            add(CompositeLiteralContext.class);
            add(FunctionLiteralContext.class);
            add(LiteralValueContext.class);
            add(ValueContext.class);
            add(ElementIndexContext.class);
            add(ConversionContext.class);
            add(ArrayLengthContext.class);
            add(ChannelContext.class);
            add(ConditionContext.class);
            add(RecvExprContext.class);
            add(RangeClauseContext.class);
        }};

    private static final Set<Class<? extends ParserRuleContext<?>>> CODE_CLASS_CONTEXTS =
        new HashSet<Class<? extends ParserRuleContext<?>>>() {{
            add(TypeContext.class);
            add(TypeNameContext.class);
            add(TypeLiteralContext.class);
            add(ArrayTypeContext.class);
            add(StructTypeContext.class);
            add(PointerTypeContext.class);
            add(FunctionTypeContext.class);
            add(InterfaceTypeContext.class);
            add(SliceTypeContext.class);
            add(MapTypeContext.class);
            add(ChannelTypeContext.class);
            add(ElementTypeContext.class);
            add(BaseTypeContext.class);
            add(KeyTypeContext.class);
            add(LiteralTypeContext.class);
        }};

    @Override
    @SuppressWarnings("element-type-mismatch")
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
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
