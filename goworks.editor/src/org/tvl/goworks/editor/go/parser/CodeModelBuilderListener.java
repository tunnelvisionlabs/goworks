/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.netbeans.api.project.Project;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ImportDeclarationModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.PackageDeclarationModelImpl;
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
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinArgsContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compositeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.pointerTypeContext;
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
    private final Project project;
    private final DocumentSnapshot snapshot;
    private final TokenStream tokenStream;

    private String packageName;
    private FileModelImpl fileModel;

    public CodeModelBuilderListener(DocumentSnapshot snapshot, TokenStream tokenStream) {
        this.project = null;
        this.snapshot = snapshot;
        this.tokenStream = tokenStream;
    }

    public FileModelImpl getFileModel() {
        return fileModel;
    }

    @Override
    public void enterRule(sourceFileContext ctx) {
        if (ctx.pkg != null && ctx.pkg.name != null && ctx.pkg.name.name != null) {
            packageName = ctx.pkg.name.name.getText();
        } else {
            packageName = snapshot.getVersionedDocument().getFileObject().getParent().getName();
        }

        String name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        this.fileModel = new FileModelImpl(name, project, packageName);
        this.typeContainerStack.push(this.fileModel.getTypes());
    }

    @Override
    public void exitRule(sourceFileContext ctx) {
        this.fileModel.freeze();
        this.typeContainerStack.pop();
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
        path = path.substring(1, path.length() - 2);

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

    private final Deque<TypeStructModelImpl> structModelStack = new ArrayDeque<TypeStructModelImpl>();
    private final Deque<TypeInterfaceModelImpl> interfaceModelStack = new ArrayDeque<TypeInterfaceModelImpl>();
    private final Deque<Collection<TypeModelImpl>> typeContainerStack = new ArrayDeque<Collection<TypeModelImpl>>();
    private final Deque<TypeModelImpl> typeModelStack = new ArrayDeque<TypeModelImpl>();

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
    public void exitRule(arrayTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypeArrayModelImpl(typeName, elementType, fileModel));
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
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypePointerModelImpl(typeName, elementType, fileModel));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(functionTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypeFunctionModelImpl(typeName, fileModel));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void enterRule(interfaceTypeContext ctx) {
        String typeName = createAnonymousTypeName(ctx);
        interfaceModelStack.push(new TypeInterfaceModelImpl(typeName, fileModel));
    }

    @Override
    public void exitRule(interfaceTypeContext ctx) {
        typeModelStack.push(interfaceModelStack.pop());
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(sliceTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypeSliceModelImpl(typeName, elementType, fileModel));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(mapTypeContext ctx) {
        TypeModelImpl valueType = typeModelStack.pop();
        TypeModelImpl keyType = typeModelStack.pop();
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypeMapModelImpl(typeName, keyType, valueType, fileModel));
        assert !typeModelStack.isEmpty();
    }

    @Override
    public void exitRule(channelTypeContext ctx) {
        TypeModelImpl elementType = typeModelStack.pop();
        String typeName = createAnonymousTypeName(ctx);
        typeModelStack.push(new TypeChannelModelImpl(typeName, elementType, fileModel));
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
        if (ctx.explicitType != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(varSpecContext ctx) {
        if (ctx.varType != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(methodDeclContext ctx) {
    }

    @Override
    public void exitRule(functionDeclContext ctx) {
    }

    @Override
    public void exitRule(fieldDeclContext ctx) {
        if (ctx.fieldType != null || ctx.anonField != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(parameterDeclContext ctx) {
        if (ctx.t != null) {
            typeModelStack.pop();
        }
    }

    @Override
    public void exitRule(resultContext ctx) {
        if (ctx.t != null) {
            typeModelStack.pop();
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
