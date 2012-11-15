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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
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
import org.tvl.goworks.editor.go.parser.AbstractGoParser.AndExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompareExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExpressionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OrExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SourceFileBodyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarSpecContext;
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

    private final Deque<TypeStructModelImpl> _structModelStack = new ArrayDeque<TypeStructModelImpl>();
    private final Deque<TypeInterfaceModelImpl> _interfaceModelStack = new ArrayDeque<TypeInterfaceModelImpl>();
    private final Deque<Collection<TypeModelImpl>> _implementedTypesContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    private final Deque<Collection<TypeModelImpl>> _typeContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    private final Deque<Collection<ConstModelImpl>> _constContainerStack = new ArrayDeque<Collection<ConstModelImpl>>();
    private final Deque<Collection<VarModelImpl>> _varContainerStack = new ArrayDeque<Collection<VarModelImpl>>();
    private final Deque<Collection<FunctionModelImpl>> _functionContainerStack = new ArrayDeque<Collection<FunctionModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> _parameterContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<TypeModelImpl> _typeModelStack = new ArrayDeque<TypeModelImpl>();
    private final Deque<FunctionModel> _functionModelStack = new ArrayDeque<FunctionModel>();

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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0)
    public void exitType(TypeContext ctx) {
        // handled by child contexts
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void exitTypeName(TypeNameContext ctx) {
        String pkgName = ctx.qualifiedIdentifier().packageName() != null ? ctx.qualifiedIdentifier().packageName().IDENTIFIER().getSymbol().getText() : null;
        String typeName;
        if (ctx.qualifiedIdentifier().IDENTIFIER() != null) {
            typeName = ctx.qualifiedIdentifier().IDENTIFIER().getSymbol().getText();
        } else {
            typeName = "?";
        }

        _typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, _fileModel));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        // handled by child contexts
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0)
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
        // handled by child contexts
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
    public void exitArrayType(ArrayTypeContext ctx) {
        TypeModelImpl elementType = _typeModelStack.pop();
        _typeModelStack.push(new TypeArrayModelImpl(elementType));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void enterStructType(StructTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _structModelStack.push(new TypeStructModelImpl(typeName, _fileModel, ctx));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void exitStructType(StructTypeContext ctx) {
        _typeModelStack.push(_structModelStack.pop());
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0)
    public void exitPointerType(PointerTypeContext ctx) {
        TypeModelImpl elementType = _typeModelStack.pop();
        _typeModelStack.push(new TypePointerModelImpl(elementType));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
    public void enterFunctionType(FunctionTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _functionModelStack.push(new TypeFunctionModelImpl(typeName, _fileModel, ctx));
        _parameterContainerStack.push(new ArrayList<ParameterModelImpl>());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
    public void exitFunctionType(FunctionTypeContext ctx) {
        _parameterContainerStack.pop();
        _typeModelStack.push((TypeFunctionModelImpl)_functionModelStack.pop());
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void enterInterfaceType(InterfaceTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        _interfaceModelStack.push(new TypeInterfaceModelImpl(typeName, _fileModel, ctx));
        _implementedTypesContainerStack.push(_interfaceModelStack.peek().getImplementedInterfaces());
        _functionContainerStack.push(_interfaceModelStack.peek().getInterfaceMethods());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        _typeModelStack.push(_interfaceModelStack.pop());
        _implementedTypesContainerStack.pop();
        _functionContainerStack.pop();
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0)
    public void exitSliceType(SliceTypeContext ctx) {
        TypeModelImpl elementType = _typeModelStack.pop();
        _typeModelStack.push(new TypeSliceModelImpl(elementType));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
    })
    public void exitMapType(MapTypeContext ctx) {
        TypeModelImpl valueType;
        if (ctx.elementType() != null) {
            valueType = _typeModelStack.pop();
        }
        else {
            valueType = _unknownType;
        }

        TypeModelImpl keyType;
        if (ctx.keyType() != null) {
            keyType = _typeModelStack.pop();
        }
        else {
            keyType = _unknownType;
        }

        _typeModelStack.push(new TypeMapModelImpl(keyType, valueType));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0)
    public void exitChannelType(ChannelTypeContext ctx) {
        TypeModelImpl elementType = _typeModelStack.pop();
        ChannelKind channelKind = ChannelKind.SendReceive;
        if (ctx.send != null) {
            channelKind = ChannelKind.Send;
        } else if (ctx.recv != null) {
            channelKind = ChannelKind.Receive;
        }

        _typeModelStack.push(new TypeChannelModelImpl(elementType, channelKind));
        if (_typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitTypeSpec(TypeSpecContext ctx) {
        String name = "?";
        if (ctx.IDENTIFIER() != null) {
            name = ctx.IDENTIFIER().getSymbol().getText();
        }

        TypeModelImpl type;
        if (ctx.type() != null) {
            type = _typeModelStack.pop();
        }
        else {
            type = _unknownType;
        }

        TypeModelImpl model = new TypeAliasModelImpl(name, type, _fileModel, ctx.IDENTIFIER(), ctx);
        _typeContainerStack.peek().add(model);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitConstSpec(ConstSpecContext ctx) {
        TypeModelImpl type = null;
        if (ctx.type() != null) {
            type = _typeModelStack.pop();
        }

        IdentifierListContext idList = ctx.identifierList();
        ExpressionListContext expressionList = ctx.expressionList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : null;
        List<? extends ExpressionContext> expressions = expressionList != null ? expressionList.expression() : null;
        if (ids != null) {
            for (int i = 0; i < ids.size(); i++) {
                TerminalNode<Token> id = ids.get(i);
                String unevaluatedValue;
                if (expressions != null && expressions.size() > i) {
                    unevaluatedValue = expressions.get(i).getText();
                } else {
                    unevaluatedValue = null;
                }

                String evaluatedValue = null;

                ConstModelImpl model = new ConstModelImpl(id.getSymbol().getText(), _fileModel, unevaluatedValue, evaluatedValue, type, id, ctx);
                _constContainerStack.peek().add(model);
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
    })
    public void exitVarSpec(VarSpecContext ctx) {
        TypeModelImpl explicitType = ctx.type() != null ? _typeModelStack.pop() : _unknownType;
        IdentifierListContext idList = ctx.identifierList();
        ExpressionListContext expressionList = ctx.expressionList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : Collections.<TerminalNode<Token>>emptyList();
        List<? extends ExpressionContext> expressions = expressionList != null ? expressionList.expression() : Collections.<ExpressionContext>emptyList();
        if (!ids.isEmpty()) {
            boolean isGlobal = _varContainerStack.peek() == _fileModel.getVars();
            for (int i = 0; i < ids.size(); i++) {
                TerminalNode<Token> id = ids.get(i);
                TypeModelImpl varType = null;
                if (ctx.type() != null) {
                    varType = explicitType;
                } else if (i < expressions.size()) {
                    varType = _expressionTypes.get(expressions.get(i));
                }

                if (varType == null) {
                    varType = _unknownType;
                }

                VarModelImpl model = new VarModelImpl(id.getSymbol().getText(), isGlobal ? VarKind.GLOBAL : VarKind.LOCAL, varType, _fileModel, id, ctx);
                _varContainerStack.peek().add(model);
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0)
    public void exitMethodDecl(MethodDeclContext ctx) {
        _functionModelStack.pop();
        _parameterContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
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
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0),
    })
    public void exitMethodSpec(MethodSpecContext ctx) {
        if (ctx.methodName() != null) {
            _functionModelStack.pop();
            _parameterContainerStack.pop();
        } else if (ctx.interfaceTypeName() != null) {
            _implementedTypesContainerStack.peek().add(_typeModelStack.pop());
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0)
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
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
    })
    public void exitReceiver(ReceiverContext ctx) {
        TerminalNode<? extends Token> nameNode = ctx.IDENTIFIER();
        String name = nameNode != null ? nameNode.getSymbol().getText() : "_";

        if (ctx.baseTypeName() != null) {
            TypeModelImpl type = _typeModelStack.pop();
            if (ctx.ptr != null) {
                type = new TypePointerModelImpl(type);
            }

            ParameterModelImpl receiver = new ParameterModelImpl(name, VarKind.RECEIVER, type, _fileModel, nameNode, ctx);
            ((FunctionModelImpl)_functionModelStack.peek()).setReceiverParameter(receiver);
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
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
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        _functionModelStack.pop();
        _parameterContainerStack.pop();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
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
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitResult(ResultContext ctx) {
        if (ctx.type() != null) {
            _parameterContainerStack.peek().add(new ParameterModelImpl("_", VarKind.RETURN, _typeModelStack.pop(), _fileModel, null, ctx));
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitParameterDecl(ParameterDeclContext ctx) {
        if (ctx.identifierList() == null && ctx.type() == null) {
            return;
        }

        TypeModelImpl parameterType = ctx.type() != null ? _typeModelStack.pop() : new GoCompletionQuery.UnknownTypeModelImpl(_fileModel);
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
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_anonymousField, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
    })
    public void exitFieldDecl(FieldDeclContext ctx) {
        TypeModelImpl fieldType = null;
        if (ctx.type() != null || ctx.anonymousField() != null) {
            fieldType = _typeModelStack.pop();
        }

        IdentifierListContext idList = ctx.identifierList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : null;
        if (ids == null && ctx.anonymousField() != null) {
            TerminalNode<Token> name = ctx.anonymousField().typeName().qualifiedIdentifier().IDENTIFIER();
            if (name != null) {
                ids = Collections.singletonList(name);
            }
        }

        if (ids != null && !ids.isEmpty()) {
            for (TerminalNode<Token> id : ids) {
                FieldModelImpl model = new FieldModelImpl(id.getSymbol().getText(), fieldType, ctx.anonymousField() != null, _fileModel, id, ctx);
                _structModelStack.peek().getFields().add(model);
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        if (ctx.type() != null) {
            _typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiverType, version=0),
    })
    public void exitMethodExpr(MethodExprContext ctx) {
        if (ctx.receiverType() != null) {
            _typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitConversion(ConversionContext ctx) {
        if (ctx.type() != null) {
            TypeModelImpl type = _typeModelStack.pop();
            _expressionTypes.put(ctx, type);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
    })
    public void exitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        if (ctx.conversion() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.conversion());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
    })
    public void exitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        TypeModelImpl type = _expressionTypes.get(ctx.builtinCall());
        if (type != null) {
            _expressionTypes.put(ctx, type);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitBuiltinCall(BuiltinCallContext ctx) {
        String functionName = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
        if (functionName == null || functionName.isEmpty()) {
            return;
        }

        BuiltinArgsContext args = ctx.builtinArgs();
        if (args == null || args.type() == null) {
            return;
        }

        if ("make".equals(functionName)) {
            TypeModelImpl type = _expressionTypes.get(args.type());
            _expressionTypes.put(ctx, type);
        } else if ("new".equals(functionName)) {
            TypeModelImpl type = _expressionTypes.get(args.type());
            _expressionTypes.put(ctx, new TypePointerModelImpl(type));
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitUnaryExpr(UnaryExprContext ctx) {
        TypeModelImpl type = _expressionTypes.get(ctx.expression());
        if (type == null) {
            return;
        }

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

            default:
                LOGGER.log(Level.WARNING, "Unsupported unary operator: {0}", ctx.op.getText());
                return;
            }
        }

        _expressionTypes.put(ctx, type);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitCompareExpr(CompareExprContext ctx) {
        _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitAndExpr(AndExprContext ctx) {
        _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
    public void exitOrExpr(OrExprContext ctx) {
        _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.BOOL);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
        if (ctx.type() != null) {
            TypeModelImpl type = _typeModelStack.pop();
            _expressionTypes.put(ctx.type(), type);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitTypeList(TypeListContext ctx) {
        int typeCount = ctx.type().size();
        for (int i = 0; i < typeCount; i++) {
            _typeModelStack.pop();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0)
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        TypeModelImpl type = _typeModelStack.pop();
        _expressionTypes.put(ctx, type);
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
        if (ctx.literal() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.literal());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        } else if (ctx.qualifiedIdentifier()!= null) {
            TypeModelImpl type = _expressionTypes.get(ctx.qualifiedIdentifier());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        } else if (ctx.methodExpr()!= null) {
            TypeModelImpl type = _expressionTypes.get(ctx.methodExpr());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        } else if (ctx.expression()!= null) {
            TypeModelImpl type = _expressionTypes.get(ctx.expression());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
    })
    public void exitOperandExpr(OperandExprContext ctx) {
        if (ctx.operand() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.operand());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0)
    public void exitCompositeLiteral(CompositeLiteralContext ctx) {
        TypeModelImpl type = _typeModelStack.pop();
        _expressionTypes.put(ctx, type);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
    })
    public void exitLiteral(LiteralContext ctx) {
        if (ctx.basicLiteral() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.basicLiteral());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        } else if (ctx.compositeLiteral() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.compositeLiteral());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        } else if (ctx.functionLiteral() != null) {
            TypeModelImpl type = _expressionTypes.get(ctx.functionLiteral());
            if (type != null) {
                _expressionTypes.put(ctx, type);
            }
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0)
    public void exitBasicLiteral(BasicLiteralContext ctx) {
        if (ctx.INT_LITERAL() != null) {
            _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.INT);
        } else if (ctx.FLOAT_LITERAL() != null) {
            _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.FLOAT64);
        } else if (ctx.IMAGINARY_LITERAL() != null) {
            _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.COMPLEX128);
        } else if (ctx.CharLiteral() != null) {
            _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.RUNE);
        } else if (ctx.StringLiteral() != null) {
            _expressionTypes.put(ctx, (TypeModelImpl)IntrinsicTypeModels.STRING);
        }
    }

    private static String createAnonymousTypeName(ParserRuleContext<Token> context) {
        return String.format("$%s_%d", GoParser.ruleNames[context.getRuleIndex()], context.getStart().getStartIndex());
    }
}
