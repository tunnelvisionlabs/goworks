/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.codemodel.impl.ConstModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FieldModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ImportDeclarationModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.PackageDeclarationModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ParameterModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeAliasModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeArrayModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeChannelModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeFunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeInterfaceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeMapModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeReferenceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeStructModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VarModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VariadicParameterSliceModelImpl;
import org.tvl.goworks.editor.go.completion.GoCompletionQuery;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AndExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AnonymousFieldContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompareExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OrExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SourceFileBodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseListener;
import org.tvl.goworks.project.GoProject;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends GoParserBaseListener {
    // -J-Dorg.tvl.goworks.editor.go.parser.CodeModelBuilderListener.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CodeModelBuilderListener.class.getName());

    public static final ParseTreeWalker PARSE_TREE_WALKER = new ParseTreeWalker() {

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_body, version=0)
        public <Symbol extends Token> void walk(ParseTreeListener<? super Symbol> listener, ParseTree<Symbol> t) {
            if (t instanceof RuleNode) {
                RuleNode<Symbol> ruleNode = (RuleNode<Symbol>)t;
                if (ruleNode.getRuleContext() instanceof BodyContext) {
                    return;
                }
            }

            super.walk(listener, t);
        }

    };

    private final GoProject _project;
    private final DocumentSnapshot _snapshot;
    private final Token[] _tokens;

    private FileModelImpl _fileModel;
    private TypeModelImpl _unknownType;

    /**
     * This is always balanced in {@link #enterStructType} and
     * {@link #exitStructType}.
     */
    private final Deque<TypeStructModelImpl> _structModelStack = new ArrayDeque<TypeStructModelImpl>();
    /**
     * This is always balanced in {@link #enterInterfaceType} and
     * {@link #exitInterfaceType}.
     */
    private final Deque<TypeInterfaceModelImpl> _interfaceModelStack = new ArrayDeque<TypeInterfaceModelImpl>();
    /**
     * This is always balanced in {@link #enterInterfaceType} and
     * {@link #exitInterfaceType}.
     */
    private final Deque<Collection<TypeModelImpl>> _implementedTypesContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    /**
     * This is always balanced in {@link #enterSourceFileBody} and
     * {@link #exitSourceFileBody}.
     */
    private final Deque<Collection<TypeModelImpl>> _typeContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    /**
     * This is always balanced in {@link #enterSourceFileBody} and
     * {@link #exitSourceFileBody}.
     */
    private final Deque<Collection<ConstModelImpl>> _constContainerStack = new ArrayDeque<Collection<ConstModelImpl>>();
    /**
     * This is always balanced in {@link #enterSourceFileBody} and
     * {@link #exitSourceFileBody}.
     */
    private final Deque<Collection<VarModelImpl>> _varContainerStack = new ArrayDeque<Collection<VarModelImpl>>();
    /**
     * This is always balanced in {@link #enterSourceFileBody} and
     * {@link #exitSourceFileBody}, and in {@link #enterInterfaceType} and
     * {@link #exitInterfaceType}.
     */
    private final Deque<Collection<FunctionModelImpl>> _functionContainerStack = new ArrayDeque<Collection<FunctionModelImpl>>();
    /**
     * This is always balanced in the following pairs of methods:
     * <ul>
     * <li>{@link #enterFunctionType} and {@link #exitFunctionType}</li>
     * <li>{@link #enterMethodDecl} and {@link #exitMethodDecl}</li>
     * <li>{@link #enterMethodSpec} and {@link #exitMethodSpec}</li>
     * <li>{@link #enterFunctionDecl} and {@link #exitFunctionDecl}</li>
     * <li>{@link #enterResult} (balanced within this single method)</li>
     * </ul>
     */
    private final Deque<Collection<ParameterModelImpl>> _parameterContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    /**
     * This is always balanced in the following pairs of methods:
     * <ul>
     * <li>{@link #enterFunctionType} and {@link #exitFunctionType}</li>
     * <li>{@link #enterMethodDecl} and {@link #exitMethodDecl}</li>
     * <li>{@link #enterMethodSpec} and {@link #exitMethodSpec}</li>
     * <li>{@link #enterFunctionDecl} and {@link #exitFunctionDecl}</li>
     * </ul>
     */
    private final Deque<FunctionModel> _functionModelStack = new ArrayDeque<FunctionModel>();
    /**
     * This stack is implicitly balanced.
     */
    private final Deque<TypeModelImpl> _typeModelStack = new ArrayDeque<TypeModelImpl>();

    private final Map<ParserRuleContext<Token>, TypeModelImpl> _expressionTypes = new HashMap<ParserRuleContext<Token>, TypeModelImpl>();

    public CodeModelBuilderListener(DocumentSnapshot snapshot, Token[] tokens) {
        Project project = FileOwnerQuery.getOwner(snapshot.getVersionedDocument().getFileObject());
        if (!(project instanceof GoProject)) {
            throw new UnsupportedOperationException("Unsupported project type.");
        }

        this._project = (GoProject)project;
        this._snapshot = snapshot;
        this._tokens = tokens;
    }

    public FileModelImpl getFileModel() {
        return _fileModel;
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=1)
    public void enterSourceFileBody(SourceFileBodyContext ctx) {
        FileObject documentFileObject = _snapshot.getVersionedDocument().getFileObject();
        FileObject packageFolder = documentFileObject != null ? documentFileObject.getParent() : null;
        FileObject sourceRoot = _project != null ? _project.getSourceRoot() : null;

        String packagePath;
        if (sourceRoot != null) {
            packagePath = FileUtil.getRelativePath(sourceRoot, packageFolder);
            assert packagePath != null;
        } else {
            packagePath = packageFolder.getNameExt();
        }

        FileObject fileObject = _snapshot.getVersionedDocument().getFileObject();
        this._fileModel = new FileModelImpl(fileObject, _project, packagePath);
        this._typeContainerStack.push(this._fileModel.getTypes());
        this._constContainerStack.push(this._fileModel.getConstants());
        this._varContainerStack.push(this._fileModel.getVars());
        this._functionContainerStack.push(this._fileModel.getFunctions());
        this._unknownType = new GoCompletionQuery.UnknownTypeModelImpl(_fileModel);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=1)
    public void exitSourceFileBody(SourceFileBodyContext ctx) {
        this._fileModel.freeze();
        this._typeContainerStack.pop();
        this._constContainerStack.pop();
        this._varContainerStack.pop();
        this._functionContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void exitPackageClause(PackageClauseContext ctx) {
        PackageNameContext nameContext = ctx.packageName();
        if (nameContext != null && nameContext.IDENTIFIER() != null) {
            String name = nameContext.IDENTIFIER().getSymbol().getText();
            PackageDeclarationModelImpl model = new PackageDeclarationModelImpl(name, _project, nameContext.IDENTIFIER(), nameContext);
            _fileModel.getPackageDeclarations().add(model);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importPath, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void exitImportSpec(ImportSpecContext ctx) {
        if (ctx.importPath() == null || ctx.importPath().StringLiteral() == null) {
            return;
        }

        String path = ctx.importPath().StringLiteral().getSymbol().getText();
        path = path.substring(1, path.length() - 1);

        String alias;
        if (ctx.dot != null) {
            alias = ".";
        } else if (ctx.packageName() != null && ctx.packageName().IDENTIFIER() != null) {
            alias = ctx.packageName().IDENTIFIER().getSymbol().getText();
        } else {
            alias = GoParser.getPackageName(ctx.importPath().StringLiteral().getSymbol());
        }

        ImportDeclarationModelImpl model = new ImportDeclarationModelImpl(path, alias, ctx.dot != null, _fileModel, ctx);
        _fileModel.getImportDeclarations().add(model);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=2, dependents={Dependents.PARENTS, Dependents.SELF}),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0, dependents=Dependents.SELF),
    })
    public void exitType(TypeContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.typeName(), ctx.typeLiteral(), ctx.type());
        _typeModelStack.push(type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=3, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0, dependents=Dependents.SELF),
    })
    public void exitTypeName(TypeNameContext ctx) {
        QualifiedIdentifierContext qualifiedIdentifierContext = ctx.qualifiedIdentifier();
        PackageNameContext packageNameContext = qualifiedIdentifierContext != null ? qualifiedIdentifierContext.packageName() : null;
        TerminalNode<Token> packageName = packageNameContext != null ? packageNameContext.IDENTIFIER() : null;
        String pkgName = packageName != null ? packageName.getSymbol().getText() : null;
        String typeName;
        if (qualifiedIdentifierContext != null && qualifiedIdentifierContext.IDENTIFIER() != null) {
            typeName = qualifiedIdentifierContext.IDENTIFIER().getSymbol().getText();
        } else {
            typeName = "?";
        }

        _typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, _fileModel));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0, dependents=Dependents.SELF),
    })
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        @NonNull
        TypeModelImpl result = _unknownType;
        if (ctx.getChildCount() > 0) {
            ParseTree<Token> child = ctx.getChild(0);
            if (child instanceof RuleNode) {
                RuleContext<Token> ruleContext = ((RuleNode<Token>)child).getRuleContext();
                switch (ruleContext.getRuleIndex()) {
                case GoParser.RULE_arrayType:
                case GoParser.RULE_structType:
                case GoParser.RULE_pointerType:
                case GoParser.RULE_functionType:
                case GoParser.RULE_interfaceType:
                case GoParser.RULE_sliceType:
                case GoParser.RULE_mapType:
                case GoParser.RULE_channelType:
                    result = popTypeModel((ParserRuleContext<Token>)ruleContext);
                    break;
                    
                default:
                    break;
                }
            }
        }

        _typeModelStack.push(result);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
    })
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
        _typeModelStack.push(popTypeModel(ctx.typeName()));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0, dependents=Dependents.SELF),
    })
    public void exitArrayType(ArrayTypeContext ctx) {
        TypeModelImpl elementType = popTypeModel(ctx.elementType());
        _typeModelStack.push(new TypeArrayModelImpl(elementType));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0, dependents=Dependents.PARENTS)
    public void enterStructType(StructTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _structModelStack.push(new TypeStructModelImpl(typeName, _fileModel, ctx));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0, dependents=Dependents.PARENTS)
    public void exitStructType(StructTypeContext ctx) {
        _typeModelStack.push(_structModelStack.pop());
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0, dependents=Dependents.SELF),
    })
    public void exitPointerType(PointerTypeContext ctx) {
        TypeModelImpl elementType = popTypeModel(ctx.baseType());
        _typeModelStack.push(new TypePointerModelImpl(elementType));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0, dependents=Dependents.PARENTS)
    public void enterFunctionType(FunctionTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _functionModelStack.push(new TypeFunctionModelImpl(typeName, _fileModel, ctx));
        _parameterContainerStack.push(new ArrayList<ParameterModelImpl>());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0, dependents=Dependents.PARENTS)
    public void exitFunctionType(FunctionTypeContext ctx) {
        _parameterContainerStack.pop();
        _typeModelStack.push((TypeFunctionModelImpl)_functionModelStack.pop());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0, dependents=Dependents.PARENTS)
    public void enterInterfaceType(InterfaceTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _interfaceModelStack.push(new TypeInterfaceModelImpl(typeName, _fileModel, ctx));
        _implementedTypesContainerStack.push(_interfaceModelStack.peek().getImplementedInterfaces());
        _functionContainerStack.push(_interfaceModelStack.peek().getInterfaceMethods());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0, dependents=Dependents.PARENTS)
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        _typeModelStack.push(_interfaceModelStack.pop());
        _implementedTypesContainerStack.pop();
        _functionContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0, dependents=Dependents.SELF),
    })
    public void exitSliceType(SliceTypeContext ctx) {
        TypeModelImpl elementType = popTypeModel(ctx.elementType());
        _typeModelStack.push(new TypeSliceModelImpl(elementType));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0, dependents=Dependents.SELF),
    })
    public void exitMapType(MapTypeContext ctx) {
        TypeModelImpl valueType = popTypeModel(ctx.elementType());
        TypeModelImpl keyType = popTypeModel(ctx.keyType());
        _typeModelStack.push(new TypeMapModelImpl(keyType, valueType));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0, dependents=Dependents.SELF),
    })
    public void exitChannelType(ChannelTypeContext ctx) {
        TypeModelImpl elementType = popTypeModel(ctx.elementType());
        ChannelKind channelKind = ChannelKind.SendReceive;
        if (ctx.send != null) {
            channelKind = ChannelKind.Send;
        } else if (ctx.recv != null) {
            channelKind = ChannelKind.Receive;
        }

        _typeModelStack.push(new TypeChannelModelImpl(elementType, channelKind));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitTypeSpec(TypeSpecContext ctx) {
        String name = "?";
        if (ctx.IDENTIFIER() != null) {
            name = ctx.IDENTIFIER().getSymbol().getText();
        }

        TypeModelImpl type = popTypeModel(ctx.type());
        TypeModelImpl model = new TypeAliasModelImpl(name, type, _fileModel, ctx.IDENTIFIER(), ctx);
        _typeContainerStack.peek().add(model);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitConstSpec(ConstSpecContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.type(), null);
        IdentifierListContext idList = ctx.identifierList();
        ExpressionListContext expressionList = ctx.expressionList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : Collections.<TerminalNode<Token>>emptyList();
        List<? extends ExpressionContext> expressions = expressionList != null ? expressionList.expression() : Collections.<ExpressionContext>emptyList();
        for (int i = 0; i < ids.size(); i++) {
            TerminalNode<Token> id = ids.get(i);
            String unevaluatedValue;
            if (expressions.size() > i) {
                unevaluatedValue = expressions.get(i).getText();
            } else {
                unevaluatedValue = null;
            }

            String evaluatedValue = null;

            ConstModelImpl model = new ConstModelImpl(id.getSymbol().getText(), _fileModel, unevaluatedValue, evaluatedValue, type, id, ctx);
            _constContainerStack.peek().add(model);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0, dependents=Dependents.SELF),
    })
    public void exitVarSpec(VarSpecContext ctx) {
        TypeModelImpl explicitType = popTypeModel(ctx.type());
        IdentifierListContext idList = ctx.identifierList();
        ExpressionListContext expressionList = ctx.expressionList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : Collections.<TerminalNode<Token>>emptyList();
        List<? extends ExpressionContext> expressions = expressionList != null ? expressionList.expression() : Collections.<ExpressionContext>emptyList();
        boolean isGlobal = !ids.isEmpty() && _varContainerStack.peek() == _fileModel.getVars();
        for (int i = 0; i < ids.size(); i++) {
            TerminalNode<Token> id = ids.get(i);
            TypeModelImpl varType = _unknownType;
            if (ctx.type() != null) {
                varType = explicitType;
            } else if (i < expressions.size()) {
                varType = getExpressionType(expressions.get(i));
            }

            VarModelImpl model = new VarModelImpl(id.getSymbol().getText(), isGlobal ? VarKind.GLOBAL : VarKind.LOCAL, varType, _fileModel, id, ctx);
            _varContainerStack.peek().add(model);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0, dependents=Dependents.SELF),
    })
    public void enterMethodDecl(MethodDeclContext ctx) {
        TerminalNode<Token> nameNode = ctx.methodName() != null ? ctx.methodName().IDENTIFIER() : null;
        String name = nameNode != null ? nameNode.getSymbol().getText() : createAnonymousTypeName(ctx);
        FunctionModelImpl model = new FunctionModelImpl(name, _fileModel, nameNode, ctx);
        _functionContainerStack.peek().add(model);
        _functionModelStack.push(model);
        _parameterContainerStack.push(model.getParameters());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0, dependents=Dependents.PARENTS)
    public void exitMethodDecl(MethodDeclContext ctx) {
        _functionModelStack.pop();
        _parameterContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0, dependents=Dependents.SELF),
    })
    public void enterMethodSpec(MethodSpecContext ctx) {
        if (ctx.methodName() != null) {
            TerminalNode<Token> nameNode = ctx.methodName().IDENTIFIER();
            FunctionModelImpl model = new FunctionModelImpl(nameNode.getSymbol().getText(), _fileModel, nameNode, ctx);
            _functionContainerStack.peek().add(model);
            _functionModelStack.push(model);
            _parameterContainerStack.push(model.getParameters());
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0, dependents=Dependents.SELF),
    })
    public void exitMethodSpec(MethodSpecContext ctx) {
        if (ctx.methodName() != null) {
            _functionModelStack.pop();
            _parameterContainerStack.pop();
        } else if (ctx.interfaceTypeName() != null) {
            _implementedTypesContainerStack.peek().add(popTypeModel(ctx.interfaceTypeName()));
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0, dependents=Dependents.PARENTS)
    public void exitBaseTypeName(BaseTypeNameContext ctx) {
        String pkgName = null;
        String typeName;
        if (ctx.IDENTIFIER() != null) {
            typeName = ctx.IDENTIFIER().getSymbol().getText();
        } else {
            typeName = "?";
        }

        _typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, _fileModel));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0, dependents=Dependents.SELF),
    })
    public void exitReceiver(ReceiverContext ctx) {
        TerminalNode<? extends Token> nameNode = ctx.IDENTIFIER();
        String name = nameNode != null ? nameNode.getSymbol().getText() : "_";

        if (ctx.baseTypeName() != null) {
            TypeModelImpl type = popTypeModel(ctx.baseTypeName());
            if (ctx.ptr != null) {
                type = new TypePointerModelImpl(type);
            }

            ParameterModelImpl receiver = new ParameterModelImpl(name, VarKind.RECEIVER, type, _fileModel, nameNode, ctx);
            ((FunctionModelImpl)_functionModelStack.peek()).setReceiverParameter(receiver);
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0, dependents=Dependents.PARENTS)
    public void enterFunctionDecl(FunctionDeclContext ctx) {
        TerminalNode<Token> nameNode = ctx.IDENTIFIER();
        String name = nameNode != null ? nameNode.getText() : "?";
        FunctionModelImpl model = new FunctionModelImpl(name, _fileModel, nameNode, ctx);

        if (nameNode != null) {
            _functionContainerStack.peek().add(model);
        }

        _functionModelStack.push(model);
        _parameterContainerStack.push(model.getParameters());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0, dependents=Dependents.PARENTS)
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        _functionModelStack.pop();
        _parameterContainerStack.pop();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0, dependents=Dependents.PARENTS)
    public void enterResult(ResultContext ctx) {
        _parameterContainerStack.pop();
        FunctionModel functionModel = _functionModelStack.peek();
        Collection<ParameterModelImpl> returnValues;
        if (functionModel instanceof FunctionModelImpl) {
            returnValues = ((FunctionModelImpl)functionModel).getReturnValues();
        } else {
            returnValues = ((TypeFunctionModelImpl)functionModel).getReturnValues();
        }

        _parameterContainerStack.push(returnValues);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitResult(ResultContext ctx) {
        _parameterContainerStack.peek().add(new ParameterModelImpl("_", VarKind.RETURN, popTypeModel(ctx.type()), _fileModel, null, ctx));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitParameterDecl(ParameterDeclContext ctx) {
        if (ctx.identifierList() == null && ctx.type() == null) {
            return;
        }

        TypeModelImpl parameterType = popTypeModel(ctx.type());
        if (ctx.ellip != null) {
            parameterType = new VariadicParameterSliceModelImpl(parameterType);
        }

        boolean isReturnParameter = _functionModelStack.peek().getReturnValues() == _parameterContainerStack.peek();
        if (ctx.identifierList() != null) {
            for (TerminalNode<Token> id : ctx.identifierList().IDENTIFIER()) {
                _parameterContainerStack.peek().add(new ParameterModelImpl(id.getSymbol().getText(), isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, _fileModel, id, ctx));
            }
        } else {
            _parameterContainerStack.peek().add(new ParameterModelImpl("_", isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, _fileModel, null, ctx));
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_anonymousField, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0, dependents=Dependents.SELF),
    })
    public void exitFieldDecl(FieldDeclContext ctx) {
        TypeModelImpl fieldType = popTypeModel(ctx.type(), ctx.anonymousField());
        IdentifierListContext idList = ctx.identifierList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : Collections.<TerminalNode<Token>>emptyList();
        if (ctx.anonymousField() != null) {
            AnonymousFieldContext anonymousFieldContext = ctx.anonymousField();
            TypeNameContext typeNameContext = anonymousFieldContext != null ? anonymousFieldContext.typeName() : null;
            QualifiedIdentifierContext qualifiedIdentifierContext = typeNameContext != null ? typeNameContext.qualifiedIdentifier() : null;
            TerminalNode<Token> name = qualifiedIdentifierContext != null ? qualifiedIdentifierContext.IDENTIFIER() : null;
            if (name != null) {
                ids = Collections.singletonList(name);
            }
        }

        for (TerminalNode<Token> id : ids) {
            FieldModelImpl model = new FieldModelImpl(id.getSymbol().getText(), fieldType, ctx.anonymousField() != null, _fileModel, id, ctx);
            _structModelStack.peek().getFields().add(model);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        popTypeModel(ctx.type());
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiverType, version=3, dependents=Dependents.SELF),
    })
    public void exitMethodExpr(MethodExprContext ctx) {
        popTypeModel(ctx.receiverType());
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitConversion(ConversionContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.type());
        putExpressionType(ctx, type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0, dependents=Dependents.SELF),
    })
    public void exitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        putExpressionType(ctx, getExpressionType(ctx.conversion()));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0, dependents=Dependents.SELF),
    })
    public void exitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        putExpressionType(ctx, getExpressionType(ctx.builtinCall()));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=2, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitBuiltinCall(BuiltinCallContext ctx) {
        String functionName = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
        if (functionName == null || functionName.isEmpty()) {
            return;
        }

        BuiltinArgsContext args = ctx.builtinArgs();
        if (args == null) {
            return;
        }

        if ("make".equals(functionName)) {
            putExpressionType(ctx, getExpressionType(args.type()));
        } else if ("new".equals(functionName)) {
            putExpressionType(ctx, new TypePointerModelImpl(getExpressionType(args.type())));
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents={Dependents.PARENTS, Dependents.SELF})
    public void exitUnaryExpr(UnaryExprContext ctx) {
        TypeModelImpl type = getExpressionType(ctx.expression());
        if (ctx.op != null) {
            switch (ctx.op.getType()) {
            case GoLexer.Amp:
                type = new TypePointerModelImpl(type);
                break;

            case GoLexer.Bang:
                type = (TypeModelImpl)IntrinsicTypeModels.BOOL;
                break;

            case GoLexer.Caret:
            case GoLexer.Plus:
            case GoLexer.Minus:
                // type unchanged
                break;

            case GoLexer.Star:
                if (type instanceof TypePointerModelImpl) {
                    type = ((TypePointerModelImpl)type).getElementType();
                } else {
                    LOGGER.log(Level.WARNING, "Unary operator '*' is currently only supported for trivial (non-aliased) pointer element types.");
                    return;
                }

                break;

            default:
                LOGGER.log(Level.WARNING, "Unsupported unary operator: {0}", ctx.op.getText());
                return;
            }
        }

        putExpressionType(ctx, type);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS)
    public void exitCompareExpr(CompareExprContext ctx) {
        putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS)
    public void exitAndExpr(AndExprContext ctx) {
        putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS)
    public void exitOrExpr(OrExprContext ctx) {
        putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=2, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.type());
        putExpressionType(ctx, type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeList, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
    })
    public void exitTypeList(TypeListContext ctx) {
        for (TypeContext type : ctx.type()) {
            popTypeModel(type);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0, dependents=Dependents.SELF),
    })
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.functionType());
        putExpressionType(ctx, type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0, dependents=Dependents.SELF),
    })
    public void exitOperand(OperandContext ctx) {
        if (ctx.literal() != null) {
            putExpressionType(ctx, getExpressionType(ctx.literal()));
        } else if (ctx.qualifiedIdentifier()!= null) {
            putExpressionType(ctx, getExpressionType(ctx.qualifiedIdentifier()));
        } else if (ctx.methodExpr()!= null) {
            putExpressionType(ctx, getExpressionType(ctx.methodExpr()));
        } else if (ctx.expression()!= null) {
            putExpressionType(ctx, getExpressionType(ctx.expression()));
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0, dependents=Dependents.SELF),
    })
    public void exitOperandExpr(OperandExprContext ctx) {
        putExpressionType(ctx, getExpressionType(ctx.operand()));
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0, dependents=Dependents.SELF),
    })
    public void exitCompositeLiteral(CompositeLiteralContext ctx) {
        TypeModelImpl type = popTypeModel(ctx.literalType());
        putExpressionType(ctx, type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0, dependents=Dependents.PARENTS),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0, dependents=Dependents.SELF),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0, dependents=Dependents.SELF),
    })
    public void exitLiteral(LiteralContext ctx) {
        if (ctx.basicLiteral() != null) {
            putExpressionType(ctx, getExpressionType(ctx.basicLiteral()));
        } else if (ctx.compositeLiteral() != null) {
            putExpressionType(ctx, getExpressionType(ctx.compositeLiteral()));
        } else if (ctx.functionLiteral() != null) {
            putExpressionType(ctx, getExpressionType(ctx.functionLiteral()));
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0, dependents=Dependents.PARENTS)
    public void exitBasicLiteral(BasicLiteralContext ctx) {
        if (ctx.INT_LITERAL() != null) {
            putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.INT);
        } else if (ctx.FLOAT_LITERAL() != null) {
            putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.FLOAT64);
        } else if (ctx.IMAGINARY_LITERAL() != null) {
            putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.COMPLEX128);
        } else if (ctx.CharLiteral() != null) {
            putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.RUNE);
        } else if (ctx.StringLiteral() != null) {
            putExpressionType(ctx, (TypeModelImpl)IntrinsicTypeModels.STRING);
        }
    }

    @NonNull
    private TypeModelImpl getExpressionType(@NullAllowed ParserRuleContext<Token> context) {
        if (context == null) {
            return _unknownType;
        }

        TypeModelImpl result = _expressionTypes.get(context);
        if (result == null) {
            return _unknownType;
        }

        return result;
    }

    private void putExpressionType(@NonNull ParserRuleContext<Token> context, @NonNull TypeModelImpl type) {
        if (type == _unknownType) {
            return;
        }

        _expressionTypes.put(context, type);
    }

    @NonNull
    private TypeModelImpl popTypeModel(@NullAllowed ParserRuleContext<Token> context) {
        return popTypeModel(context, _unknownType);
    }

    @NonNull
    private TypeModelImpl popTypeModel(@NullAllowed ParserRuleContext<Token> context, @NullAllowed TypeModelImpl defaultType) {
        if (context != null) {
            return _typeModelStack.pop();
        }

        return defaultType;
    }

    @NonNull
    private TypeModelImpl popTypeModel(ParserRuleContext<?>... contexts) {
        int count = 0;
        for (ParserRuleContext<?> context : contexts) {
            if (context != null) {
                count++;
            }
        }

        switch (count) {
        case 0:
            return _unknownType;

        case 1:
            return _typeModelStack.pop();

        default:
            throw new IllegalStateException();
        }
    }

    private static String createAnonymousTypeName(ParserRuleContext<Token> context) {
        return String.format("$%s_%d", GoParser.ruleNames[context.getRuleIndex()], context.getStart().getStartIndex());
    }
}
