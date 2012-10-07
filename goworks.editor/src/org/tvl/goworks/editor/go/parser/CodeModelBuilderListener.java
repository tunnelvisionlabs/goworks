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
import java.util.List;
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
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MethodSpecContext;
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
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarSpecContext;

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

    private final Project project;
    private final DocumentSnapshot snapshot;
    private final Token[] tokens;

    private FileModelImpl fileModel;

    private final Deque<TypeStructModelImpl> structModelStack = new ArrayDeque<TypeStructModelImpl>();
    private final Deque<TypeInterfaceModelImpl> interfaceModelStack = new ArrayDeque<TypeInterfaceModelImpl>();
    private final Deque<Collection<TypeModelImpl>> implementedTypesContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    private final Deque<Collection<TypeModelImpl>> typeContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    private final Deque<Collection<ConstModelImpl>> constContainerStack = new ArrayDeque<Collection<ConstModelImpl>>();
    private final Deque<Collection<VarModelImpl>> varContainerStack = new ArrayDeque<Collection<VarModelImpl>>();
    private final Deque<Collection<FunctionModelImpl>> functionContainerStack = new ArrayDeque<Collection<FunctionModelImpl>>();
    private final Deque<Collection<ParameterModelImpl>> parameterContainerStack = new ArrayDeque<Collection<ParameterModelImpl>>();
    private final Deque<TypeModelImpl> typeModelStack = new ArrayDeque<TypeModelImpl>();
    private final Deque<FunctionModel> functionModelStack = new ArrayDeque<FunctionModel>();

    public CodeModelBuilderListener(DocumentSnapshot snapshot, Token[] tokens) {
        this.project = FileOwnerQuery.getOwner(snapshot.getVersionedDocument().getFileObject());
        this.snapshot = snapshot;
        this.tokens = tokens;
    }

    public FileModelImpl getFileModel() {
        return fileModel;
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
    public void enterSourceFileBody(SourceFileBodyContext ctx) {
        FileObject documentFileObject = snapshot.getVersionedDocument().getFileObject();
        FileObject packageFolder = documentFileObject != null ? documentFileObject.getParent() : null;
        FileObject projectFolder = project != null ? project.getProjectDirectory() : null;

        String packagePath;
        if (projectFolder != null) {
            packagePath = FileUtil.getRelativePath(projectFolder, packageFolder);
        } else {
            packagePath = packageFolder.getNameExt();
        }

        FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
        this.fileModel = new FileModelImpl(fileObject, project, packagePath);
        this.typeContainerStack.push(this.fileModel.getTypes());
        this.constContainerStack.push(this.fileModel.getConstants());
        this.varContainerStack.push(this.fileModel.getVars());
        this.functionContainerStack.push(this.fileModel.getFunctions());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=0)
    public void exitSourceFileBody(SourceFileBodyContext ctx) {
        this.fileModel.freeze();
        this.typeContainerStack.pop();
        this.constContainerStack.pop();
        this.varContainerStack.pop();
        this.functionContainerStack.pop();
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
            PackageDeclarationModelImpl model = new PackageDeclarationModelImpl(name, project, nameContext.IDENTIFIER(), nameContext);
            fileModel.getPackageDeclarations().add(model);
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importPath, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
    })
    public void exitImportSpec(ImportSpecContext ctx) {
        if (ctx.importPath() == null && ctx.importPath().StringLiteral() == null) {
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

        ImportDeclarationModelImpl model = new ImportDeclarationModelImpl(path, alias, ctx.dot != null, fileModel, ctx);
        fileModel.getImportDeclarations().add(model);
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0)
    public void exitType(TypeContext ctx) {
        // handled by child contexts
        if (typeModelStack.isEmpty()) {
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

        typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, fileModel));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        // handled by child contexts
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceTypeName, version=0)
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
        // handled by child contexts
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0)
    public void exitArrayType(ArrayTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypeArrayModelImpl(elementType));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void enterStructType(StructTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        structModelStack.push(new TypeStructModelImpl(typeName, fileModel, ctx));
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
    public void exitStructType(StructTypeContext ctx) {
        typeModelStack.push(structModelStack.pop());
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0)
    public void exitPointerType(PointerTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypePointerModelImpl(elementType));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
    public void enterFunctionType(FunctionTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        functionModelStack.push(new TypeFunctionModelImpl(typeName, fileModel, ctx));
        parameterContainerStack.push(new ArrayList<ParameterModelImpl>());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
    public void exitFunctionType(FunctionTypeContext ctx) {
        parameterContainerStack.pop();
        typeModelStack.push((TypeFunctionModelImpl)functionModelStack.pop());
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void enterInterfaceType(InterfaceTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        interfaceModelStack.push(new TypeInterfaceModelImpl(typeName, fileModel, ctx));
        implementedTypesContainerStack.push(interfaceModelStack.peek().getImplementedInterfaces());
        functionContainerStack.push(interfaceModelStack.peek().getInterfaceMethods());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        typeModelStack.push(interfaceModelStack.pop());
        implementedTypesContainerStack.pop();
        functionContainerStack.pop();
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0)
    public void exitSliceType(SliceTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypeSliceModelImpl(elementType));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0)
    public void exitMapType(MapTypeContext ctx) {
        TypeModelImpl valueType = typeModelStack.pop();
        TypeModelImpl keyType = typeModelStack.pop();
        typeModelStack.push(new TypeMapModelImpl(keyType, valueType));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0)
    public void exitChannelType(ChannelTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        ChannelKind channelKind = ChannelKind.SendReceive;
        if (ctx.send != null) {
            channelKind = ChannelKind.Send;
        } else if (ctx.recv != null) {
            channelKind = ChannelKind.Receive;
        }

        typeModelStack.push(new TypeChannelModelImpl(elementType, channelKind));
        if (typeModelStack.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0)
    public void exitTypeSpec(TypeSpecContext ctx) {
        String name = "?";
        if (ctx.IDENTIFIER() != null) {
            name = ctx.IDENTIFIER().getSymbol().getText();
        }

        TypeModelImpl type = typeModelStack.pop();
        TypeModelImpl model = new TypeAliasModelImpl(name, type, fileModel, ctx.IDENTIFIER(), ctx);
        typeContainerStack.peek().add(model);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitConstSpec(ConstSpecContext ctx) {
        IdentifierListContext idList = ctx.identifierList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : null;
        if (ids != null) {
            for (TerminalNode<Token> id : ids) {
                ConstModelImpl model = new ConstModelImpl(id.getSymbol().getText(), fileModel, id, ctx);
                constContainerStack.peek().add(model);
            }
        }

        if (ctx.type() != null) {
            typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
    })
    public void exitVarSpec(VarSpecContext ctx) {
        TypeModelImpl varType = ctx.type() != null ? typeModelStack.pop() : new GoCompletionQuery.UnknownTypeModelImpl(fileModel);
        IdentifierListContext idList = ctx.identifierList();
        List<? extends TerminalNode<Token>> ids = idList != null ? idList.IDENTIFIER() : null;
        if (ids != null && !ids.isEmpty()) {
            boolean isGlobal = varContainerStack.peek() == fileModel.getVars();
            for (TerminalNode<Token> id : ids) {
                VarModelImpl model = new VarModelImpl(id.getSymbol().getText(), isGlobal ? VarKind.GLOBAL : VarKind.LOCAL, varType, fileModel, id, ctx);
                varContainerStack.peek().add(model);
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
        FunctionModelImpl model = new FunctionModelImpl(name, fileModel, nameNode, ctx);
        functionContainerStack.peek().add(model);
        functionModelStack.push(model);
        parameterContainerStack.push(model.getParameters());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0)
    public void exitMethodDecl(MethodDeclContext ctx) {
        functionModelStack.pop();
        parameterContainerStack.pop();
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
    })
    public void enterMethodSpec(MethodSpecContext ctx) {
        if (ctx.methodName() != null) {
            TerminalNode<Token> nameNode = ctx.methodName().IDENTIFIER();
            FunctionModelImpl model = new FunctionModelImpl(nameNode.getSymbol().getText(), fileModel, nameNode, ctx);
            functionContainerStack.peek().add(model);
            functionModelStack.push(model);
            parameterContainerStack.push(model.getParameters());
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
            functionModelStack.pop();
            parameterContainerStack.pop();
        } else if (ctx.interfaceTypeName() != null) {
            implementedTypesContainerStack.peek().add(typeModelStack.pop());
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

        typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, fileModel));
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
            TypeModelImpl type = typeModelStack.pop();
            if (ctx.ptr != null) {
                type = new TypePointerModelImpl(type);
            }

            ParameterModelImpl receiver = new ParameterModelImpl(name, VarKind.RECEIVER, type, fileModel, nameNode, ctx);
            ((FunctionModelImpl)functionModelStack.peek()).setReceiverParameter(receiver);
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
    public void enterFunctionDecl(FunctionDeclContext ctx) {
        TerminalNode<Token> nameNode = ctx.IDENTIFIER();
        FunctionModelImpl model = new FunctionModelImpl(nameNode.getSymbol().getText(), fileModel, nameNode, ctx);
        functionContainerStack.peek().add(model);
        functionModelStack.push(model);
        parameterContainerStack.push(model.getParameters());
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0)
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        functionModelStack.pop();
        parameterContainerStack.pop();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0)
    public void enterResult(ResultContext ctx) {
        parameterContainerStack.pop();
        FunctionModel functionModel = functionModelStack.peek();
        Collection<ParameterModelImpl> returnValues;
        if (functionModel instanceof FunctionModelImpl) {
            returnValues = ((FunctionModelImpl)functionModel).getReturnValues();
        } else {
            returnValues = ((TypeFunctionModelImpl)functionModel).getReturnValues();
        }

        parameterContainerStack.push(returnValues);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitResult(ResultContext ctx) {
        if (ctx.type() != null) {
            parameterContainerStack.peek().add(new ParameterModelImpl("_", VarKind.RETURN, typeModelStack.pop(), fileModel, null, ctx));
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

        TypeModelImpl parameterType = ctx.type() != null ? typeModelStack.pop() : new GoCompletionQuery.UnknownTypeModelImpl(fileModel);
        if (ctx.ellip != null) {
            parameterType = new VariadicParameterSliceModelImpl(parameterType);
        }

        boolean isReturnParameter = functionModelStack.peek().getReturnValues() == parameterContainerStack.peek();
        if (ctx.identifierList() != null) {
            for (TerminalNode<Token> id : ctx.identifierList().IDENTIFIER()) {
                parameterContainerStack.peek().add(new ParameterModelImpl(id.getSymbol().getText(), isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, fileModel, id, ctx));
            }
        } else {
            parameterContainerStack.peek().add(new ParameterModelImpl("_", isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, fileModel, null, ctx));
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
            fieldType = typeModelStack.pop();
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
                FieldModelImpl model = new FieldModelImpl(id.getSymbol().getText(), fieldType, ctx.anonymousField() != null, fileModel, id, ctx);
                structModelStack.peek().getFields().add(model);
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
            typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiverType, version=0),
    })
    public void exitMethodExpr(MethodExprContext ctx) {
        if (ctx.receiverType() != null) {
            typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitConversion(ConversionContext ctx) {
        if (ctx.type() != null) {
            typeModelStack.pop();
        }
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
    })
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
        if (ctx.type() != null) {
            typeModelStack.pop();
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
            typeModelStack.pop();
        }
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0)
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        typeModelStack.pop();
    }

    @Override
    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0)
    public void exitCompositeLiteral(CompositeLiteralContext ctx) {
        typeModelStack.pop();
    }

    private static String createAnonymousTypeName(ParserRuleContext<Token> context) {
        return String.format("$%s_%d", GoParser.ruleNames[context.getRuleIndex()], context.getStart().getStartIndex());
    }
}
