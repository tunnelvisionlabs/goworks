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
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
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
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AddAssignOpContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AddExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AndExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AnonymousFieldContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArgumentListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArrayLengthContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AssignOpContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AssignmentContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BlockContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BreakStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ChannelContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CommCaseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CommClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompareExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConditionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ContinueStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.DeclarationContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.DeferStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ElementContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ElementIndexContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ElementListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ElementTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.EmptyStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExprSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExpressionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExpressionStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FallthroughStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FieldNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ForClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ForStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.GoStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.GotoStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IfStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportPathContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IncDecStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IndexExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InitStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.KeyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.KeyTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LabelContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LabeledStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralValueContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MulAssignOpContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MultExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OrExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ParameterListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ParametersContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PostStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.RangeClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ReceiverTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.RecvExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.RecvStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ReturnStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SelectStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SelectorExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SendStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SignatureContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SimpleStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SliceExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SourceFileBodyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SourceFileContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.StatementContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SwitchStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TagContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TopLevelDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ValueContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoParserListener;

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
public class SemanticAnalyzerListener implements GoParserListener {
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
        FileObject packageFolder = documentFileObject != null ? documentFileObject.getParent() : null;
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

    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0)
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
            if (qualifierCodeClass != CodeElementReference.MISSING) {
                resolvedQualifier = qualifierCodeClass.resolve(annotatedParseTree, currentPackage, resolvedPackages);
            }
        }

        if (resolvedQualifier == null) {
            CodeElementReference qualifierExprType = treeDecorator.getProperty(qualifier, GoAnnotations.EXPR_TYPE);
            if (qualifierExprType != CodeElementReference.MISSING) {
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
                String packageName = ((PackageNameContext)qualifier).IDENTIFIER().getSymbol().getText();
                resolvedQualifier = resolvedPackages.get(packageName);
                if (resolvedQualifier == null) {
                    resolvedQualifier = Collections.emptyList();
                }
            } else if (qualifierNodeType == NodeType.VAR_REF) {
                // must be referring to something within the current file since it's resolved internally
                Token target = treeDecorator.getProperty(qualifier, GoAnnotations.LOCAL_TARGET);
                assert target != null && tokenDecorator.getProperty(target, GoAnnotations.NODE_TYPE) == NodeType.VAR_DECL;
                ParserRuleContext<Token> explicitType = target != null ? tokenDecorator.getProperty(target, GoAnnotations.EXPLICIT_TYPE) : null;
                if (explicitType != null) {
                    LOGGER.log(Level.WARNING, "Unable to resolve explicit type for qualifier: {0}", qualifier);
                    resolvedQualifier = Collections.emptyList();
                } else {
                    ParserRuleContext<Token> implicitType = target != null ? tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_TYPE) : null;
                    int implicitIndex = target != null ? tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_INDEX) : -1;
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

    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
    private boolean resolveQualifierType(ParserRuleContext<Token> qualifier,
                                         PackageModel currentPackage,
                                         Map<String, Collection<PackageModel>> resolvedPackages) {

        if (qualifier instanceof ArrayTypeContext) {
            ArrayTypeContext ctx = (ArrayTypeContext)qualifier;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class QualifierTypeResolver extends GoParserBaseListener {

        public boolean resolveType(ParserRuleContext<Token> qualifier) {
            ParseTreeWalker.DEFAULT.walk(this, qualifier);
            return treeDecorator.getProperty(qualifier, GoAnnotations.MODELS) != null;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
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
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
        })
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
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
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
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
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
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterMultExpr(MultExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitMultExpr(MultExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.expression(0) != null && ctx.op != null && ctx.expression(1) != null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.expression(0), GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.expression(1), GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0)
    public void enterChannelType(ChannelTypeContext ctx) {
       
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mulAssignOp, version=0)
    public void enterMulAssignOp(MulAssignOpContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mulAssignOp, version=0)
    public void exitMulAssignOp(MulAssignOpContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0),
    })
    public void enterPackageName(PackageNameContext ctx) {
        int invokingRule = ParseTrees.getInvokingRule(GoParser._ATN, ctx);
        NodeType nodeType = invokingRule == GoParser.RULE_packageClause ? NodeType.PACKAGE_DECL : NodeType.PACKAGE_REF;
        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, nodeType);
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, nodeType);
            if (treeDecorator.getProperty(ctx, GoAnnotations.RESOLVED)) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.RESOLVED, true);
            }

            Token localTarget = treeDecorator.getProperty(ctx, GoAnnotations.LOCAL_TARGET);
            if (localTarget != null) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.LOCAL_TARGET, localTarget);
            }
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0)
    public void exitPackageName(PackageNameContext ctx) {
        // not qualified!
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
    })
    public void enterReceiver(ReceiverContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.VAR_TYPE, VarKind.RECEIVER);
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.EXPLICIT_TYPE, ctx.baseTypeName());
            visibleLocals.peek().put(ctx.IDENTIFIER().getSymbol().getText(), ctx.IDENTIFIER().getSymbol());
        }

        if (ctx.ptr != null && ctx.baseTypeName() != null) {
            treeDecorator.putProperty(ctx.baseTypeName(), GoAnnotations.POINTER_RECEIVER, true);
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0)
    public void exitReceiver(ReceiverContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
    public void enterArrayType(ArrayTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0)
    public void enterExpressionList(ExpressionListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0)
    public void exitExpressionList(ExpressionListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_tag, version=0)
    public void enterTag(TagContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_tag, version=0)
    public void exitTag(TagContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fallthroughStmt, version=0)
    public void enterFallthroughStmt(FallthroughStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fallthroughStmt, version=0)
    public void exitFallthroughStmt(FallthroughStmtContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterSelectorExpr(SelectorExprContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIED_EXPR, true);
            if (ctx.expression() != null) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIER, ctx.expression());
            }
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitSelectorExpr(SelectorExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        TerminalNode<Token> node = ctx.IDENTIFIER();
        if (node != null) {
            assert ctx.expression() != null;
            exprType = new SelectedElementReference(treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE), node.getSymbol());
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        if (node != null) {
            unresolvedQualifiedIdentifiers.add(node);
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0)
    public void enterParameterList(ParameterListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0)
    public void exitParameterList(ParameterListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiverType, version=0)
    public void enterReceiverType(ReceiverTypeContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiverType, version=0)
    public void exitReceiverType(ReceiverTypeContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldName, version=0)
    public void enterFieldName(FieldNameContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldName, version=0)
    public void exitFieldName(FieldNameContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_ifStmt, version=0)
    public void enterIfStmt(IfStmtContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_ifStmt, version=0)
    public void exitIfStmt(IfStmtContext ctx) {
        popVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0)
    public void enterMethodName(MethodNameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.METHOD_DECL);
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0)
    public void exitMethodName(MethodNameContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0)
    public void enterSignature(SignatureContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0)
    public void exitSignature(SignatureContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0)
    public void enterMapType(MapTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_element, version=0)
    public void enterElement(ElementContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_element, version=0)
    public void exitElement(ElementContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterCallExpr(CallExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_argumentList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
    })
    public void exitCallExpr(CallExprContext ctx) {
        CodeElementReference returnType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            boolean builtin = false;
            if (ctx.expression().start != null && ctx.expression().start == ctx.expression().stop) {
                String methodName = ctx.expression().start.getText();
                builtin = SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(methodName);
            }

            if (builtin) {
                CodeElementReference typeArgument = CodeElementReference.UNKNOWN;
                ArgumentListContext args = ctx.argumentList();
                if (args != null) {
                    ExpressionListContext exprs = args.expressionList();
                    if (exprs != null && !exprs.expression().isEmpty()) {
                        typeArgument = treeDecorator.getProperty(exprs.expression(0), GoAnnotations.CODE_CLASS);
                        if (typeArgument == CodeElementReference.MISSING) {
                            typeArgument = treeDecorator.getProperty(exprs.expression(0), GoAnnotations.EXPR_TYPE);
                        }
                    }
                }

                returnType = new BuiltinCallResultReference(ctx.expression().start, typeArgument);
            } else {
                returnType = new CallResultReference(treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE));
            }
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, returnType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeCaseClause, version=0)
    public void enterTypeCaseClause(TypeCaseClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeCaseClause, version=0)
    public void exitTypeCaseClause(TypeCaseClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprCaseClause, version=0)
    public void enterExprCaseClause(ExprCaseClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprCaseClause, version=0)
    public void exitExprCaseClause(ExprCaseClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0)
    public void enterTypeSwitchGuard(TypeSwitchGuardContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0)
    public void exitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0)
    public void enterFunctionLiteral(FunctionLiteralContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0)
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);

        popVarScope();
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterOrExpr(OrExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitOrExpr(OrExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvExpr, version=0)
    public void enterRecvExpr(RecvExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitRecvExpr(RecvExprContext ctx) {
        assert ctx.getChildCount() <= 1;
        assert ctx.getChildCount() == 0 || ctx.expression() != null;

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0)
    public void enterTopLevelDecl(TopLevelDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0)
    public void exitTopLevelDecl(TopLevelDeclContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0)
    public void enterMethodSpec(MethodSpecContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0)
    public void exitMethodSpec(MethodSpecContext ctx) {
        popVarScope();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
    })
    public void enterConstSpec(ConstSpecContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.CONST_DECL);
            boolean global = ParseTrees.isInContexts(ctx, false, GoParser.RULE_constSpec, GoParser.RULE_constDecl, GoParser.RULE_declaration, GoParser.RULE_topLevelDecl);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.GLOBAL, global);
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0)
    public void exitConstSpec(ConstSpecContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0)
    public void enterCompositeLiteral(CompositeLiteralContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forClause, version=0)
    public void enterForClause(ForClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forClause, version=0)
    public void exitForClause(ForClauseContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void enterShortVarDecl(ShortVarDeclContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.LOCAL);
            if (ctx.expressionList() != null) {
                int varCount = ctx.identifierList().IDENTIFIER().size();
                int exprCount = ctx.expressionList().expression().size();
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.expressionList().expression(0));
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.expressionList().expression(i));
                        if (varCount > 1) {
                            tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                        }
                    }
                }
            }
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=0)
    public void exitShortVarDecl(ShortVarDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_gotoStmt, version=0)
    public void enterGotoStmt(GotoStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_gotoStmt, version=0)
    public void exitGotoStmt(GotoStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayLength, version=0)
    public void enterArrayLength(ArrayLengthContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayLength, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitArrayLength(ArrayLengthContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void enterInterfaceType(InterfaceTypeContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        CodeElementReference codeClass = new InterfaceTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.INTERFACE);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0)
    public void enterConversion(ConversionContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitConversion(ConversionContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            exprType = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_block, version=0)
    public void enterBlock(BlockContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_block, version=0)
    public void exitBlock(BlockContext ctx) {
        popVarScope();
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_breakStmt, version=0)
    public void enterBreakStmt(BreakStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_breakStmt, version=0)
    public void exitBreakStmt(BreakStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_emptyStmt, version=0)
    public void enterEmptyStmt(EmptyStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_emptyStmt, version=0)
    public void exitEmptyStmt(EmptyStmtContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
    })
    public void enterFunctionType(FunctionTypeContext ctx) {
        if (!ParseTrees.isInContexts(ctx, false, GoParser.RULE_functionType, GoParser.RULE_functionLiteral)) {
            pushVarScope();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0)
    public void enterBaseType(BaseTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitBaseType(BaseTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void enterFieldDecl(FieldDeclContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.FIELD);
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.EXPLICIT_TYPE, ctx.type());
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0)
    public void exitFieldDecl(FieldDeclContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchStmt, version=0)
    public void enterExprSwitchStmt(ExprSwitchStmtContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchStmt, version=0)
    public void exitExprSwitchStmt(ExprSwitchStmtContext ctx) {
        popVarScope();
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_goStmt, version=0)
    public void enterGoStmt(GoStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_goStmt, version=0)
    public void exitGoStmt(GoStmtContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0)
    public void exitParameterDecl(ParameterDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0)
    public void enterBasicLiteral(BasicLiteralContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0)
    public void exitBasicLiteral(BasicLiteralContext ctx) {
        assert ctx.stop == null || ctx.start == ctx.stop;
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralTypeReference(ctx.start));
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchCase, version=0)
    public void enterExprSwitchCase(ExprSwitchCaseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchCase, version=0)
    public void exitExprSwitchCase(ExprSwitchCaseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
    public void enterTypeLiteral(TypeLiteralContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        assert ctx.getChildCount() <= 1;
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.getChildCount() == 1) {
            codeClass = treeDecorator.getProperty(ctx.getChild(0), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_selectStmt, version=0)
    public void enterSelectStmt(SelectStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_selectStmt, version=0)
    public void exitSelectStmt(SelectStmtContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importPath, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
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
            if (ctx.packageName.IDENTIFIER() != null) {
                target = ctx.packageName.IDENTIFIER().getSymbol();
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importSpec, version=0)
    public void exitImportSpec(ImportSpecContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0)
    public void enterTypeName(TypeNameContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0)
    public void enterLiteralType(LiteralTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_assignment, version=0)
    public void enterAssignment(AssignmentContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_assignment, version=0)
    public void exitAssignment(AssignmentContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_assignOp, version=0)
    public void enterAssignOp(AssignOpContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_assignOp, version=0)
    public void exitAssignOp(AssignOpContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvStmt, version=0)
    public void enterRecvStmt(RecvStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvStmt, version=0)
    public void exitRecvStmt(RecvStmtContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0)
    public void enterTypeSpec(TypeSpecContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.TYPE_DECL);
            visibleTypes.peek().put(ctx.IDENTIFIER().getSymbol().getText(), ctx.IDENTIFIER().getSymbol());
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0)
    public void exitTypeSpec(TypeSpecContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0)
    public void enterPackageClause(PackageClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0)
    public void exitPackageClause(PackageClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0)
    public void enterLiteralValue(LiteralValueContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0)
    public void exitLiteralValue(LiteralValueContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new LiteralValueElementReference());
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterIndexExpr(IndexExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitIndexExpr(IndexExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.expression(0) != null) {
            CodeElementReference arrayType = treeDecorator.getProperty(ctx.expression(0), GoAnnotations.EXPR_TYPE);
            exprType = new ArrayElementTypeReference(arrayType);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
    })
    public void enterVarSpec(VarSpecContext ctx) {
        if (ctx.identifierList() != null) {
            treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.NODE_TYPE, NodeType.VAR_DECL);
            if (ParseTrees.isInContexts(ctx, false, GoParser.RULE_varSpec, GoParser.RULE_varDecl, GoParser.RULE_declaration, GoParser.RULE_topLevelDecl)) {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.GLOBAL);
            } else {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.VAR_TYPE, VarKind.LOCAL);
            }
            
            if (ctx.type() != null) {
                treeDecorator.putProperty(ctx.identifierList(), GoAnnotations.EXPLICIT_TYPE, ctx.type());
            } else if (ctx.expressionList(0) != null) {
                int varCount = ctx.identifierList().IDENTIFIER().size();
                int exprCount = ctx.expressionList(0).expression().size();
                if (varCount > 1 && exprCount == 1) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.expressionList(0).expression(0));
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                } else if (varCount == exprCount) {
                    for (int i = 0; i < varCount; i++) {
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_TYPE, ctx.expressionList(0).expression(i));
                        tokenDecorator.putProperty(ctx.identifierList().IDENTIFIER(i).getSymbol(), GoAnnotations.IMPLICIT_INDEX, i);
                    }
                }
            }
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0)
    public void exitVarSpec(VarSpecContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterOperandExpr(OperandExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
    })
    public void exitOperandExpr(OperandExprContext ctx) {
        assert ctx.getChildCount() == 0 || (ctx.getChildCount() == 1 && ctx.operand() != null);
        if (ctx.operand() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.operand(), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        if (ctx.operand() != null) {
            treeDecorator.putProperties(ctx, treeDecorator.getProperties(ctx.operand()));
        } else {
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParser(null)));
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterBuiltinCallExpr(BuiltinCallExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
    })
    public void exitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        assert ctx.getChildCount() == 0 || (ctx.getChildCount() == 1 && ctx.builtinCall() != null);
        if (ctx.builtinCall() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, treeDecorator.getProperty(ctx.builtinCall(), GoAnnotations.EXPR_TYPE));
        } else {
            LOGGER.log(Level.WARNING, "Unrecognized tree structure.");
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, CodeElementReference.UNKNOWN);
        }

        LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParser(null)));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0)
    public void enterBody(BodyContext ctx) {
        pushVarScope();
        visibleLabels.push(new HashMap<String, Token>());
        unresolvedLabels.push(new ArrayList<Token>());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0)
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commClause, version=0)
    public void enterCommClause(CommClauseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commClause, version=0)
    public void exitCommClause(CommClauseContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void enterQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        if (ctx.packageName() != null) {
            if (ctx.IDENTIFIER() != null) {
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIED_EXPR, true);
                tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.QUALIFIER, ctx.packageName());
                unresolvedQualifiedIdentifiers.add(ctx.IDENTIFIER());
            }

            // check known imports
            if (ctx.packageName().IDENTIFIER() != null) {
                List<? extends Token> imports = ParseTrees.emptyIfNull(importedPackages.get(ctx.packageName().IDENTIFIER().getSymbol().getText()));
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
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void exitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        if (ctx.packageName() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.QUALIFIED_EXPR, true);
        } else if (ctx.IDENTIFIER() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.UNQUALIFIED_LINK, ctx.IDENTIFIER().getSymbol());
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_returnStmt, version=0)
    public void enterReturnStmt(ReturnStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_returnStmt, version=0)
    public void exitReturnStmt(ReturnStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_simpleStmt, version=0)
    public void enterSimpleStmt(SimpleStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_simpleStmt, version=0)
    public void exitSimpleStmt(SimpleStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterTypeAssertionExpr(TypeAssertionExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.type() != null) {
            exprType = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new TypeAssertionResultReference(exprType));
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0)
    public void enterType(TypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0)
    public void enterInterfaceTypeName(InterfaceTypeNameContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0)
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_continueStmt, version=0)
    public void enterContinueStmt(ContinueStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_continueStmt, version=0)
    public void exitContinueStmt(ContinueStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_value, version=0)
    public void enterValue(ValueContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_value, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0),
    })
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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0)
    public void enterMethodDecl(MethodDeclContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0)
    public void exitMethodDecl(MethodDeclContext ctx) {
        popVarScope();
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0)
    public void enterLabeledStmt(LabeledStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0)
    public void exitLabeledStmt(LabeledStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0)
    public void enterParameters(ParametersContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0)
    public void exitParameters(ParametersContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_deferStmt, version=0)
    public void enterDeferStmt(DeferStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_deferStmt, version=0)
    public void exitDeferStmt(DeferStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_key, version=0)
    public void enterKey(KeyContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_key, version=0)
    public void exitKey(KeyContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0)
    public void enterDeclaration(DeclarationContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0)
    public void exitDeclaration(DeclarationContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commCase, version=0)
    public void enterCommCase(CommCaseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commCase, version=0)
    public void exitCommCase(CommCaseContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0)
    public void enterBuiltinArgs(BuiltinArgsContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0)
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_condition, version=0)
    public void enterCondition(ConditionContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_condition, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitCondition(ConditionContext ctx) {
        assert ctx.getChildCount() == 0 || (ctx.getChildCount() == 1 && ctx.expression() != null);

        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterConversionOrCallExpr(ConversionOrCallExprContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
    })
    public void exitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.conversion() != null) {
            exprType = new ConversionOrCallResultReference(treeDecorator.getProperty(ctx.conversion(), GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_label, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_label, version=0)
    public void exitLabel(LabelContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0)
    public void enterElementType(ElementTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitElementType(ElementTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
    public void enterFunctionDecl(FunctionDeclContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            tokenDecorator.putProperty(ctx.IDENTIFIER().getSymbol(), GoAnnotations.NODE_TYPE, NodeType.FUNC_DECL);
            visibleFunctions.peek().put(ctx.IDENTIFIER().getSymbol().getText(), ctx.IDENTIFIER().getSymbol());
        }

        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        popVarScope();
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0)
    public void enterStatement(StatementContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0)
    public void exitStatement(StatementContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0)
    public void enterPointerType(PointerTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_addAssignOp, version=0)
    public void enterAddAssignOp(AddAssignOpContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_addAssignOp, version=0)
    public void exitAddAssignOp(AddAssignOpContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
    public void enterSourceFileBody(SourceFileBodyContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
    public void exitSourceFileBody(SourceFileBodyContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFile, version=1)
    public void enterSourceFile(SourceFileContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFile, version=1)
    public void exitSourceFile(SourceFileContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterSliceExpr(SliceExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitSliceExpr(SliceExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression(0) != null) {
            exprType = new SliceExpressionTypeReference(treeDecorator.getProperty(ctx.expression(0), GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0)
    public void enterBaseTypeName(BaseTypeNameContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            unresolvedIdentifiers.add(ctx.IDENTIFIER());
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0)
    public void exitBaseTypeName(BaseTypeNameContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.IDENTIFIER() != null) {
            codeClass = new ReceiverTypeReference(ctx.IDENTIFIER().getSymbol(), treeDecorator.getProperty(ctx, GoAnnotations.POINTER_RECEIVER));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0)
    public void enterMethodExpr(MethodExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0)
    public void exitMethodExpr(MethodExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        LOGGER.log(Level.WARNING, "Element references not implemented for context {0}.", ctx.getClass().getSimpleName());
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementIndex, version=0)
    public void enterElementIndex(ElementIndexContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementIndex, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitElementIndex(ElementIndexContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeList, version=0)
    public void enterTypeList(TypeListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeList, version=0)
    public void exitTypeList(TypeListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_incDecStmt, version=0)
    public void enterIncDecStmt(IncDecStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_incDecStmt, version=0)
    public void exitIncDecStmt(IncDecStmtContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0)
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
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
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
                        if (typeArgument == CodeElementReference.MISSING) {
                            typeArgument = treeDecorator.getProperty(exprs.get(0), GoAnnotations.CODE_CLASS);
                        }
                    }
                }
            }
        }

        if (ctx.IDENTIFIER() != null) {
            treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, new BuiltinCallResultReference(ctx.IDENTIFIER().getSymbol(), typeArgument));
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constDecl, version=0)
    public void enterConstDecl(ConstDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constDecl, version=0)
    public void exitConstDecl(ConstDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
    public void enterResult(ResultContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
    public void exitResult(ResultContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterAndExpr(AndExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitAndExpr(AndExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void enterStructType(StructTypeContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void exitStructType(StructTypeContext ctx) {
        CodeElementReference codeClass = new StructTypeReference();
        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);

        treeDecorator.putProperty(ctx, GoAnnotations.NODE_TYPE, NodeType.TYPE_LITERAL);
        treeDecorator.putProperty(ctx, GoAnnotations.TYPE_KIND, TypeKind.STRUCT);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varDecl, version=0)
    public void enterVarDecl(VarDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varDecl, version=0)
    public void exitVarDecl(VarDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_initStmt, version=0)
    public void enterInitStmt(InitStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_initStmt, version=0)
    public void exitInitStmt(InitStmtContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0)
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0)
    public void exitIdentifierList(IdentifierListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0)
    public void enterSliceType(SliceTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
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
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterCompareExpr(CompareExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitCompareExpr(CompareExprContext ctx) {
        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, BuiltinTypeReference.BOOL);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importDecl, version=0)
    public void enterImportDecl(ImportDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importDecl, version=0)
    public void exitImportDecl(ImportDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementList, version=0)
    public void enterElementList(ElementListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementList, version=0)
    public void exitElementList(ElementListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0)
    public void enterKeyType(KeyTypeContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitKeyType(KeyTypeContext ctx) {
        CodeElementReference codeClass = CodeElementReference.UNKNOWN;
        if (ctx.type() != null) {
            codeClass = treeDecorator.getProperty(ctx.type(), GoAnnotations.CODE_CLASS);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.CODE_CLASS, codeClass);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importPath, version=0)
    public void enterImportPath(ImportPathContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importPath, version=0)
    public void exitImportPath(ImportPathContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_anonymousField, version=0)
    public void enterAnonymousField(AnonymousFieldContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_anonymousField, version=0)
    public void exitAnonymousField(AnonymousFieldContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterAddExpr(AddExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitAddExpr(AddExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.expression(0) != null && ctx.op != null && ctx.expression(1) != null) {
            CodeElementReference left = treeDecorator.getProperty(ctx.expression(0), GoAnnotations.EXPR_TYPE);
            CodeElementReference right = treeDecorator.getProperty(ctx.expression(1), GoAnnotations.EXPR_TYPE);
            exprType = new BinaryExpressionTypeReference(left, ctx.op, right);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionStmt, version=0)
    public void enterExpressionStmt(ExpressionStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionStmt, version=0)
    public void exitExpressionStmt(ExpressionStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sendStmt, version=0)
    public void enterSendStmt(SendStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sendStmt, version=0)
    public void exitSendStmt(SendStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_switchStmt, version=0)
    public void enterSwitchStmt(SwitchStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_switchStmt, version=0)
    public void exitSwitchStmt(SwitchStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_postStmt, version=0)
    public void enterPostStmt(PostStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_postStmt, version=0)
    public void exitPostStmt(PostStmtContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forStmt, version=0)
    public void enterForStmt(ForStmtContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forStmt, version=0)
    public void exitForStmt(ForStmtContext ctx) {
        popVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchCase, version=0)
    public void enterTypeSwitchCase(TypeSwitchCaseContext ctx) {
        pushVarScope();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchCase, version=0)
    public void exitTypeSwitchCase(TypeSwitchCaseContext ctx) {
        popVarScope();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
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
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitRangeClause(RangeClauseContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.e != null) {
            exprType = new RangeClauseResultReference(treeDecorator.getProperty(ctx.e, GoAnnotations.EXPR_TYPE));
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0)
    public void enterOperand(OperandContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
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
            LOGGER.log(Level.FINER, "Expression resolution links are not supported for context: {0}", ctx.toString(new GoParser(null)));
        }
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_argumentList, version=0)
    public void enterArgumentList(ArgumentListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_argumentList, version=0)
    public void exitArgumentList(ArgumentListContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0)
    public void enterTypeSwitchStmt(TypeSwitchStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0)
    public void exitTypeSwitchStmt(TypeSwitchStmtContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeDecl, version=0)
    public void enterTypeDecl(TypeDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeDecl, version=0)
    public void exitTypeDecl(TypeDeclContext ctx) {
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void enterUnaryExpr(UnaryExprContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitUnaryExpr(UnaryExprContext ctx) {
        CodeElementReference exprType = CodeElementReference.MISSING;
        if (ctx.expression() != null && ctx.op != null) {
            CodeElementReference e = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
            exprType = new UnaryExpressionTypeReference(e, ctx.op);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channel, version=0)
    public void enterChannel(ChannelContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channel, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
    })
    public void exitChannel(ChannelContext ctx) {
        CodeElementReference exprType = CodeElementReference.UNKNOWN;
        if (ctx.expression() != null) {
            exprType = treeDecorator.getProperty(ctx.expression(), GoAnnotations.EXPR_TYPE);
        }

        treeDecorator.putProperty(ctx, GoAnnotations.EXPR_TYPE, exprType);
    }

    @Override
    //@RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0)
    public void enterLiteral(LiteralContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
    })
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
    public void visitTerminal(TerminalNode<? extends Token> node) {
    }

    @Override
    public void visitErrorNode(ErrorNode<? extends Token> node) {
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
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_value, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementIndex, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayLength, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channel, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_condition, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
    })
    @SuppressWarnings("element-type-mismatch")
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
        if (EXPR_TYPE_CONTEXTS.contains(ctx.getClass())) {
            if (treeDecorator.getProperty(ctx, GoAnnotations.EXPR_TYPE) == CodeElementReference.MISSING) {
                LOGGER.log(Level.WARNING, "Expected EXPR_TYPE data for context {0}.", ctx.getClass().getSimpleName());
            }
        }

        if (CODE_CLASS_CONTEXTS.contains(ctx.getClass())) {
            if (treeDecorator.getProperty(ctx, GoAnnotations.CODE_CLASS) == CodeElementReference.MISSING) {
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
