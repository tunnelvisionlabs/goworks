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
import org.antlr.v4.runtime.Token;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
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
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinArgsContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compositeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.pointerTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.structTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;

/**
 *
 * @author Sam Harwell
 */
public class CodeModelBuilderListener extends BlankGoParserBaseListener {
    // -J-Dorg.tvl.goworks.editor.go.parser.CodeModelBuilderListener.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CodeModelBuilderListener.class.getName());

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
    public void enterRule(sourceFileContext ctx) {
        FileObject packageFolder = snapshot.getVersionedDocument().getFileObject().getParent();
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
    public void exitRule(sourceFileContext ctx) {
        this.fileModel.freeze();
        this.typeContainerStack.pop();
        this.constContainerStack.pop();
        this.varContainerStack.pop();
        this.functionContainerStack.pop();
    }

    @Override
    public void exitRule(packageClauseContext ctx) {
        packageNameContext nameContext = ctx.name;
        if (nameContext != null && nameContext.name != null) {
            String name = nameContext.name.getText();
            PackageDeclarationModelImpl model = new PackageDeclarationModelImpl(name, project);
            fileModel.getPackageDeclarations().add(model);
        }
    }

    @Override
    public void exitRule(importSpecContext ctx) {
        if (ctx.path == null && ctx.path.path == null) {
            return;
        }

        String path = ctx.path.path.getText();
        path = path.substring(1, path.length() - 1);

        String alias;
        if (ctx.dot != null) {
            alias = ".";
        } else if (ctx.name != null && ctx.name.name != null) {
            alias = ctx.name.name.getText();
        } else {
            alias = GoParserBase.getPackageName(ctx.path.path);
        }

        ImportDeclarationModelImpl model = new ImportDeclarationModelImpl(path, alias, ctx.dot != null, fileModel);
        fileModel.getImportDeclarations().add(model);
    }

    @Override
    public void exitRule(typeContext ctx) {
        // handled by child contexts
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(typeNameContext ctx) {
        String pkgName = ctx.qid.pkg != null ? ctx.qid.pkg.name.getText() : null;
        String typeName = ctx.qid.id.getText();
        typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, fileModel));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(typeLiteralContext ctx) {
        // handled by child contexts
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(interfaceTypeNameContext ctx) {
        // handled by child contexts
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(arrayTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypeArrayModelImpl(elementType));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void enterRule(structTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        structModelStack.push(new TypeStructModelImpl(typeName, fileModel));
    }

    @Override
    public void exitRule(structTypeContext ctx) {
        typeModelStack.push(structModelStack.pop());
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(pointerTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypePointerModelImpl(elementType));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void enterRule(functionTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        functionModelStack.push(new TypeFunctionModelImpl(typeName, fileModel));
        parameterContainerStack.push(new ArrayList<ParameterModelImpl>());
    }

    @Override
    public void exitRule(functionTypeContext ctx) {
        parameterContainerStack.pop();
        typeModelStack.push((TypeFunctionModelImpl)functionModelStack.pop());
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void enterRule(interfaceTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        interfaceModelStack.push(new TypeInterfaceModelImpl(typeName, fileModel));
        implementedTypesContainerStack.push(interfaceModelStack.peek().getImplementedInterfaces());
        functionContainerStack.push(interfaceModelStack.peek().getInterfaceMethods());
    }

    @Override
    public void exitRule(interfaceTypeContext ctx) {
        typeModelStack.push(interfaceModelStack.pop());
        implementedTypesContainerStack.pop();
        functionContainerStack.pop();
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(sliceTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypeSliceModelImpl(elementType));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(mapTypeContext ctx) {
        TypeModelImpl valueType = typeModelStack.pop();
        TypeModelImpl keyType = typeModelStack.pop();
        typeModelStack.push(new TypeMapModelImpl(keyType, valueType));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(channelTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        typeModelStack.push(new TypeChannelModelImpl(elementType));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(typeSpecContext ctx) {
        String name = "?";
        if (ctx.name != null) {
            name = ctx.name.getText();
        }

        TypeModelImpl type = typeModelStack.pop();
        TypeModelImpl model = new TypeAliasModelImpl(ctx.name.getText(), type, fileModel);
        typeContainerStack.peek().add(model);
    }

    @Override
    public void exitRule(constSpecContext ctx) {
        identifierListContext idList = ctx.idList;
        List<Token> ids = idList != null ? idList.ids_list : null;
        if (ids != null && !ids.isEmpty()) {
            for (Token id : ids) {
                ConstModelImpl model = new ConstModelImpl(id.getText(), fileModel);
                constContainerStack.peek().add(model);
            }
        }

        if (ctx.explicitType != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(varSpecContext ctx) {
        TypeModelImpl varType = ctx.varType != null ? typeModelStack.pop() : new GoCompletionQuery.UnknownTypeModelImpl(fileModel);
        identifierListContext idList = ctx.idList;
        List<Token> ids = idList != null ? idList.ids_list : null;
        if (ids != null && !ids.isEmpty()) {
            boolean isGlobal = varContainerStack.peek() == fileModel.getVars();
            for (Token id : ids) {
                VarModelImpl model = new VarModelImpl(id.getText(), isGlobal ? VarKind.GLOBAL : VarKind.LOCAL, varType, fileModel);
                varContainerStack.peek().add(model);
            }
        }
    }

    @Override
    public void enterRule(methodDeclContext ctx) {
        String name = ctx.name != null && ctx.name.name != null ? ctx.name.name.getText() : createAnonymousTypeName(ctx);
        FunctionModelImpl model = new FunctionModelImpl(name, fileModel);
        functionContainerStack.peek().add(model);
        functionModelStack.push(model);
        parameterContainerStack.push(model.getParameters());
    }

    @Override
    public void exitRule(methodDeclContext ctx) {
        functionModelStack.pop();
        parameterContainerStack.pop();
    }

    @Override
    public void enterRule(methodSpecContext ctx) {
        if (ctx.name != null) {
            FunctionModelImpl model = new FunctionModelImpl(ctx.name.name.getText(), fileModel);
            functionContainerStack.peek().add(model);
            functionModelStack.push(model);
            parameterContainerStack.push(model.getParameters());
        }
    }

    @Override
    public void exitRule(methodSpecContext ctx) {
        if (ctx.name != null) {
            functionModelStack.pop();
            parameterContainerStack.pop();
        } else if (ctx.ifaceName != null) {
            implementedTypesContainerStack.peek().add(typeModelStack.pop());
        }
    }

    @Override
    public void exitRule(baseTypeNameContext ctx) {
        String pkgName = null;
        String typeName = ctx.name.getText();
        typeModelStack.push(new TypeReferenceModelImpl(pkgName, typeName, fileModel));
    }

    @Override
    public void exitRule(receiverContext ctx) {
        String name = "_";
        if (ctx.name != null) {
            name = ctx.name.getText();
        }

        if (ctx.typ != null) {
            TypeModelImpl type = typeModelStack.pop();
            if (ctx.ptr != null) {
                type = new TypePointerModelImpl(type);
            }

            ParameterModelImpl receiver = new ParameterModelImpl(name, VarKind.RECEIVER, type, fileModel);
            ((FunctionModelImpl)functionModelStack.peek()).setReceiverParameter(receiver);
        }
    }

    @Override
    public void enterRule(functionDeclContext ctx) {
        FunctionModelImpl model = new FunctionModelImpl(ctx.name.getText(), fileModel);
        functionContainerStack.peek().add(model);
        functionModelStack.push(model);
        parameterContainerStack.push(model.getParameters());
    }

    @Override
    public void exitRule(functionDeclContext ctx) {
        functionModelStack.pop();
        parameterContainerStack.pop();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void enterRule(resultContext ctx) {
        parameterContainerStack.pop();
        parameterContainerStack.push((Collection<ParameterModelImpl>)functionModelStack.peek().getReturnValues());
    }

    @Override
    public void exitRule(resultContext ctx) {
        if (ctx.t != null) {
            parameterContainerStack.peek().add(new ParameterModelImpl("_", VarKind.RETURN, typeModelStack.pop(), fileModel));
        }
    }

    @Override
    public void exitRule(parameterDeclContext ctx) {
        if (ctx.idList == null && ctx.t == null) {
            return;
        }

        TypeModelImpl parameterType = ctx.t != null ? typeModelStack.pop() : new GoCompletionQuery.UnknownTypeModelImpl(fileModel);
        if (ctx.ellip != null) {
            parameterType = new VariadicParameterSliceModelImpl(parameterType);
        }

        boolean isReturnParameter = functionModelStack.peek().getReturnValues() == parameterContainerStack.peek();
        if (ctx.idList != null && ctx.idList.ids_list != null) {
            for (Token id : ctx.idList.ids_list) {
                parameterContainerStack.peek().add(new ParameterModelImpl(id.getText(), isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, fileModel));
            }
        } else {
            parameterContainerStack.peek().add(new ParameterModelImpl("_", isReturnParameter ? VarKind.RETURN : VarKind.PARAMETER, parameterType, fileModel));
        }
    }

    @Override
    public void exitRule(fieldDeclContext ctx) {
        TypeModelImpl fieldType = null;
        if (ctx.fieldType != null || ctx.anonField != null) {
            fieldType = typeModelStack.pop();
        }

        identifierListContext idList = ctx.idList;
        List<Token> ids = idList != null ? idList.ids_list : null;
        if (ids == null && ctx.anonField != null) {
            Token name = ctx.anonField.fieldType.qid.id;
            if (name != null) {
                ids = Collections.singletonList(name);
            }
        }

        if (ids != null && !ids.isEmpty()) {
            for (Token id : ids) {
                FieldModelImpl model = new FieldModelImpl(id.getText(), fieldType, ctx.anonField != null, fileModel);
                structModelStack.peek().getFields().add(model);
            }
        }
    }

    @Override
    public void exitRule(typeAssertionExprContext ctx) {
        if (ctx.t != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(methodExprContext ctx) {
        if (ctx.recvType != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(conversionContext ctx) {
        if (ctx.t != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(builtinArgsContext ctx) {
        if (ctx.typeArg != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(typeListContext ctx) {
        int typeCount = ctx.types_list != null ? ctx.types_list.size() : 0;
        for (int i = 0; i < typeCount; i++) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(functionLiteralContext ctx) {
        typeModelStack.pop();
    }

    @Override
    public void exitRule(compositeLiteralContext ctx) {
        typeModelStack.pop();
    }

    private static String createAnonymousTypeName(ParserRuleContext<Token> context) {
        return String.format("$%s_%d", GoParserBase.ruleNames[context.ruleIndex], context.getStart().getStartIndex());
    }
}
