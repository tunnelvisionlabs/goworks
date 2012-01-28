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
package org.tvl.goworks.editor.go.highlighter;

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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.blockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.callExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.forStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ifStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.rangeClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.switchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;
import org.tvl.goworks.editor.go.parser.ParseTreeAnnotations;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener> {
    // -J-Dorg.tvl.goworks.editor.go.highlighter.SemanticHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(SemanticHighlighter.class.getName());

    public static final Set<String> PREDEFINED_TYPES = new HashSet<String>()
        {{
            add("bool");
            add("byte");
            add("complex64");
            add("complex128");
            add("error");
            add("float32");
            add("float64");
            add("int");
            add("int8");
            add("int16");
            add("int32");
            add("int64");
            add("rune");
            add("string");
            add("uint");
            add("uint8");
            add("uint16");
            add("uint32");
            add("uint64");
            add("uintptr");
        }};

    public static final Set<String> PREDEFINED_CONSTANTS = new HashSet<String>()
        {{
            add("true");
            add("false");
            add("iota");
            add("nil");
        }};

    public static final Set<String> PREDEFINED_FUNCTIONS = new HashSet<String>()
        {{
            add("append");
            add("cap");
            add("close");
            add("complex");
            add("copy");
            add("delete");
            add("imag");
            add("len");
            add("make");
            add("new");
            add("panic");
            add("print");
            add("println");
            add("real");
            add("recover");
        }};

    private final AttributeSet packageDeclarationAttributes;
    private final AttributeSet packageUseAttributes;
    private final AttributeSet globalConstDeclarationAttributes;
    private final AttributeSet globalConstUseAttributes;
    private final AttributeSet localConstDeclarationAttributes;
    private final AttributeSet localConstUseAttributes;
    private final AttributeSet funcDeclarationAttributes;
    private final AttributeSet funcUseAttributes;
    private final AttributeSet methodDeclarationAttributes;
    private final AttributeSet methodUseAttributes;
    private final AttributeSet typeDeclarationAttributes;
    private final AttributeSet typeUseAttributes;
    private final AttributeSet builtinTypeUseAttributes;
    private final AttributeSet builtinConstUseAttributes;
    private final AttributeSet builtinFunctionUseAttributes;
    private final AttributeSet parameterDeclarationAttributes;
    private final AttributeSet parameterUseAttributes;
    private final AttributeSet returnParameterDeclarationAttributes;
    private final AttributeSet returnParameterUseAttributes;
    private final AttributeSet varUseAttributes;
    private final AttributeSet globalVarDeclarationAttributes;
    private final AttributeSet globalVarUseAttributes;
    private final AttributeSet fieldVarDeclarationAttributes;
    private final AttributeSet fieldVarUseAttributes;
    private final AttributeSet localVarDeclarationAttributes;
    private final AttributeSet localVarUseAttributes;
    private final AttributeSet labelDeclarationAttributes;
    private final AttributeSet labelUseAttributes;
    private final AttributeSet unresolvedIdentifierAttributes;

    private SemanticHighlighter(@NonNull StyledDocument document) {
        super(document, GoParserDataDefinitions.REFERENCE_PARSE_TREE);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GoEditorKit.GO_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);

        this.packageDeclarationAttributes = getFontAndColors(settings, "packageDeclaration");
        this.packageUseAttributes = getFontAndColors(settings, "packageUse");
        this.globalConstDeclarationAttributes = getFontAndColors(settings, "globalConstDeclaration");
        this.globalConstUseAttributes = getFontAndColors(settings, "globalConstUse");
        this.localConstDeclarationAttributes = getFontAndColors(settings, "localConstDeclaration");
        this.localConstUseAttributes = getFontAndColors(settings, "localConstUse");
        this.funcDeclarationAttributes = getFontAndColors(settings, "funcDeclaration");
        this.funcUseAttributes = getFontAndColors(settings, "funcUse");
        this.methodDeclarationAttributes = getFontAndColors(settings, "methodDeclaration");
        this.methodUseAttributes = getFontAndColors(settings, "methodUse");
        this.typeDeclarationAttributes = getFontAndColors(settings, "typeDeclaration");
        this.typeUseAttributes = getFontAndColors(settings, "typeUse");
        this.builtinTypeUseAttributes = getFontAndColors(settings, "builtinTypeUse");
        this.builtinConstUseAttributes = getFontAndColors(settings, "builtinConstUse");
        this.builtinFunctionUseAttributes = getFontAndColors(settings, "builtinFunctionUse");
        this.parameterDeclarationAttributes = getFontAndColors(settings, "parameterDeclaration");
        this.parameterUseAttributes = getFontAndColors(settings, "parameterUse");
        this.returnParameterDeclarationAttributes = getFontAndColors(settings, "returnParameterDeclaration");
        this.returnParameterUseAttributes = getFontAndColors(settings, "returnParameterUse");
        this.varUseAttributes = getFontAndColors(settings, "varUse");
        this.globalVarDeclarationAttributes = getFontAndColors(settings, "globalVarDeclaration");
        this.globalVarUseAttributes = getFontAndColors(settings, "globalVarUse");
        this.fieldVarDeclarationAttributes = getFontAndColors(settings, "fieldVarDeclaration");
        this.fieldVarUseAttributes = getFontAndColors(settings, "fieldVarUse");
        this.localVarDeclarationAttributes = getFontAndColors(settings, "localVarDeclaration");
        this.localVarUseAttributes = getFontAndColors(settings, "localVarUse");
        this.labelDeclarationAttributes = getFontAndColors(settings, "labelDeclaration");
        this.labelUseAttributes = getFontAndColors(settings, "labelUse");
        this.unresolvedIdentifierAttributes = getFontAndColors(settings, "unresolvedIdentifier");
    }

    @Override
    protected final SemanticAnalyzerListener createListener() {
        throw new UnsupportedOperationException();
    }

    protected SemanticAnalyzerListener createListener(ParserData<? extends ParserRuleContext<Token>> parserData) {
        FileModel fileModel = null;
        try {
            Future<ParserData<FileModel>> futureFileModelData = getTaskManager().getData(parserData.getSnapshot(), GoParserDataDefinitions.FILE_MODEL);
            fileModel = futureFileModelData.get().getData();
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return new SemanticAnalyzerListener(fileModel);
    }

    @Override
    protected Callable<Void> createAnalyzerTask(final ParserData<? extends ParserRuleContext<Token>> parserData) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                final SemanticAnalyzerListener listener = createListener(parserData);

                try {
                    ParseTreeWalker.DEFAULT.walk(listener, parserData.getData());
                } catch (RuntimeException ex) {
                    Exceptions.printStackTrace(ex);
                    throw ex;
                }

                ((BaseDocument)getDocument()).render(new Runnable() {
                    @Override
                    public void run() {
                        getContainer().clear();
                        DocumentSnapshot sourceSnapshot = parserData.getSnapshot();
                        DocumentSnapshot currentSnapshot = sourceSnapshot.getVersionedDocument().getCurrentSnapshot();
                        updateHighlights(getContainer(), sourceSnapshot, currentSnapshot, listener);
                    }
                });

                return null;
            }
        };
    }

    private final Set<Token> resolvedTokens = new HashSet<Token>();

    @Override
    protected void addHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        if (LOGGER.isLoggable(Level.FINE)) {
            resolvedTokens.addAll(tokens);
        }

        super.addHighlights(container, sourceSnapshot, currentSnapshot, tokens, attributes);
    }

    @Override
    protected void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        synchronized (resolvedTokens) {
            resolvedTokens.clear();

            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getPackageDeclarations(), packageDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getPackageUses(), packageUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getGlobalConstDeclarations(), globalConstDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getGlobalConstUses(), globalConstUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLocalConstDeclarations(), localConstDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLocalConstUses(), localConstUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getFuncDeclarations(), funcDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getFuncUses(), funcUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getMethodDeclarations(), methodDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getMethodUses(), methodUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getTypeDeclarations(), typeDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getTypeUses(), typeUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getBuiltinTypeUses(), builtinTypeUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getBuiltinConstUses(), builtinConstUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getBuiltinFunctionUses(), builtinFunctionUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getParameterUses(), parameterUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getReturnParameterDeclarations(), returnParameterDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getReturnParameterUses(), returnParameterUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getVarUses(), varUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getGlobalVarDeclarations(), globalVarDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getGlobalVarUses(), globalVarUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getFieldVarDeclarations(), fieldVarDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getFieldVarUses(), fieldVarUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLocalVarDeclarations(), localVarDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLocalVarUses(), localVarUseAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLabelDeclarations(), labelDeclarationAttributes);
            addHighlights(container, sourceSnapshot, currentSnapshot, listener.getLabelUses(), labelUseAttributes);

            if (LOGGER.isLoggable(Level.FINE)) {
                Collection<Token> unresolved = listener.getUnresolvedIdentifiers();
                unresolved.removeAll(resolvedTokens);
                addHighlights(container, sourceSnapshot, currentSnapshot, unresolved, unresolvedIdentifierAttributes);
            }
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory extends AbstractLayerFactory {

        public LayerFactory() {
            super(SemanticHighlighter.class);
        }

        @Override
        protected AbstractSemanticHighlighter<?> createHighlighter(Context context) {
            return new SemanticHighlighter((StyledDocument)context.getDocument());
        }

    }

    public static class SemanticAnalyzerListener extends BlankGoParserBaseListener {
        private static final String ATTR_LITERAL = "literal";
        private static final String ATTR_DECLARATION = "declaration";

        private final FileModel fileModel;

        private final ParseTreeAnnotations annotations = new ParseTreeAnnotations();

        private final List<Token> packageDeclarations = new ArrayList<Token>();
        private final List<Token> packageUses = new ArrayList<Token>();
        private final List<Token> globalConstDeclarations = new ArrayList<Token>();
        private final List<Token> globalConstUses = new ArrayList<Token>();
        private final List<Token> localConstDeclarations = new ArrayList<Token>();
        private final List<Token> localConstUses = new ArrayList<Token>();
        private final List<Token> funcDeclarations = new ArrayList<Token>();
        private final List<Token> funcUses = new ArrayList<Token>();
        private final List<Token> methodDeclarations = new ArrayList<Token>();
        private final List<Token> methodUses = new ArrayList<Token>();
        private final List<Token> typeDeclarations = new ArrayList<Token>();
        private final List<Token> typeUses = new ArrayList<Token>();
        private final List<Token> builtinTypeUses = new ArrayList<Token>();
        private final List<Token> builtinConstUses = new ArrayList<Token>();
        private final List<Token> builtinFunctionUses = new ArrayList<Token>();
        private final List<Token> parameterDeclarations = new ArrayList<Token>();
        private final List<Token> parameterUses = new ArrayList<Token>();
        private final List<Token> returnParameterDeclarations = new ArrayList<Token>();
        private final List<Token> returnParameterUses = new ArrayList<Token>();
        private final List<Token> varUses = new ArrayList<Token>();
        private final List<Token> globalVarDeclarations = new ArrayList<Token>();
        private final List<Token> globalVarUses = new ArrayList<Token>();
        private final List<Token> fieldVarDeclarations = new ArrayList<Token>();
        private final List<Token> fieldVarUses = new ArrayList<Token>();
        private final List<Token> localVarDeclarations = new ArrayList<Token>();
        private final List<Token> localVarUses = new ArrayList<Token>();
        private final List<Token> labelDeclarations = new ArrayList<Token>();
        private final List<Token> labelUses = new ArrayList<Token>();
        private final Set<Token> unresolvedIdentifiers = new HashSet<Token>();

        // temporary resolution of package references
        private final Set<String> visiblePackages = new HashSet<String>();

        private final Deque<Set<String>> visibleParameters = new ArrayDeque<Set<String>>();
        private final Deque<Set<String>> visibleReturnValues = new ArrayDeque<Set<String>>();

        private final Deque<Set<String>> visibleLocals = new ArrayDeque<Set<String>>();
        private final Deque<Set<String>> visibleLabels = new ArrayDeque<Set<String>>();

        private final Map<String, PackageModel> importedPackages = new HashMap<String, PackageModel>();
        private final HashSet<String> currentPackageTypes = new HashSet<String>();

        private final HashSet<String> knownVisibleTypes = new HashSet<String>();
        private final HashSet<String> knownInvisibleTypes = new HashSet<String>();

        private int inResult;
        private int blockLevel;

        public SemanticAnalyzerListener(FileModel fileModel) {
            this.fileModel = fileModel;
        }

        public List<Token> getPackageDeclarations() {
            return packageDeclarations;
        }

        public List<Token> getPackageUses() {
            return packageUses;
        }

        public List<Token> getGlobalConstDeclarations() {
            return globalConstDeclarations;
        }

        public List<Token> getGlobalConstUses() {
            return globalConstUses;
        }

        public List<Token> getLocalConstDeclarations() {
            return localConstDeclarations;
        }

        public List<Token> getLocalConstUses() {
            return localConstUses;
        }

        public List<Token> getFuncDeclarations() {
            return funcDeclarations;
        }

        public List<Token> getFuncUses() {
            return funcUses;
        }

        public List<Token> getMethodDeclarations() {
            return methodDeclarations;
        }

        public List<Token> getMethodUses() {
            return methodUses;
        }

        public List<Token> getTypeDeclarations() {
            return typeDeclarations;
        }

        public List<Token> getTypeUses() {
            return typeUses;
        }

        public List<Token> getBuiltinTypeUses() {
            return builtinTypeUses;
        }

        public List<Token> getBuiltinConstUses() {
            return builtinConstUses;
        }

        public List<Token> getBuiltinFunctionUses() {
            return builtinFunctionUses;
        }

        public List<Token> getParameterDeclarations() {
            return parameterDeclarations;
        }

        public List<Token> getParameterUses() {
            return parameterUses;
        }

        public List<Token> getReturnParameterDeclarations() {
            return returnParameterDeclarations;
        }

        public List<Token> getReturnParameterUses() {
            return returnParameterUses;
        }

        public List<Token> getVarUses() {
            return varUses;
        }

        public List<Token> getGlobalVarDeclarations() {
            return globalVarDeclarations;
        }

        public List<Token> getGlobalVarUses() {
            return globalVarUses;
        }

        public List<Token> getFieldVarDeclarations() {
            return fieldVarDeclarations;
        }

        public List<Token> getFieldVarUses() {
            return fieldVarUses;
        }

        public List<Token> getLocalVarDeclarations() {
            return localVarDeclarations;
        }

        public List<Token> getLocalVarUses() {
            return localVarUses;
        }

        public List<Token> getLabelDeclarations() {
            return labelDeclarations;
        }

        public List<Token> getLabelUses() {
            return labelUses;
        }

        public Collection<Token> getUnresolvedIdentifiers() {
            return unresolvedIdentifiers;
        }

        @Override
        public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
            if (symbol.getType() == GoParserBase.IDENTIFIER) {
                unresolvedIdentifiers.add(symbol);
            }
        }

        @Override
        public void enterRule(constSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (blockLevel > 0) {
                localConstDeclarations.addAll(identifiers);
            } else {
                globalConstDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(varSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (blockLevel > 0) {
                localVarDeclarations.addAll(identifiers);
                for (Token token : identifiers) {
                    visibleLocals.peek().add(token.getText());
                }
            } else {
                globalVarDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(shortVarDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (blockLevel > 0) {
                localVarDeclarations.addAll(identifiers);
                for (Token token : identifiers) {
                    visibleLocals.peek().add(token.getText());
                }
            } else {
                globalVarDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(rangeClauseContext ctx) {
            if (ctx.defeq != null) {
                if (ctx.e1 != null && ctx.e1.start == ctx.e1.stop) {
                    localVarDeclarations.add(ctx.e1.start);
                    visibleLocals.peek().add(ctx.e1.start.getText());
                }

                if (ctx.e2 != null && ctx.e2.start == ctx.e2.stop) {
                    localVarDeclarations.add(ctx.e2.start);
                    visibleLocals.peek().add(ctx.e2.start.getText());
                }
            }
        }

        @Override
        public void enterRule(fieldDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            if (idListContext != null) {
                List<Token> identifiers = idListContext.ids_list;
                fieldVarDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(functionLiteralContext ctx) {
            if (ctx.typ != null) {
                annotations.putProperty(ctx.typ, ATTR_LITERAL, Boolean.TRUE);
            }

            pushFunctionScope();
        }

        @Override
        public void exitRule(functionLiteralContext ctx) {
            popFunctionScope();
        }

        @Override
        public void enterRule(functionTypeContext ctx) {
            if (annotations.getProperty(ctx, ATTR_LITERAL) != Boolean.TRUE) {
                pushFunctionScope();
            }
        }

        @Override
        public void exitRule(functionTypeContext ctx) {
            if (annotations.getProperty(ctx, ATTR_LITERAL) != Boolean.TRUE) {
                popFunctionScope();
            }
        }

        @Override
        public void enterRule(functionDeclContext ctx) {
            if (ctx.name != null) {
                funcDeclarations.add(ctx.name);
            }

            pushFunctionScope();
        }

        @Override
        public void exitRule(functionDeclContext ctx) {
            popFunctionScope();
        }

        @Override
        public void enterRule(methodDeclContext ctx) {
            if (ctx.name != null && ctx.name.name != null) {
                methodDeclarations.add(ctx.name.name);
            }

            pushFunctionScope();
        }

        @Override
        public void exitRule(methodDeclContext ctx) {
            popFunctionScope();
        }

        @Override
        public void enterRule(receiverContext ctx) {
            if (ctx.name != null) {
                parameterDeclarations.add(ctx.name);
                visibleParameters.peek().add(ctx.name.getText());
            }
        }

        @Override
        public void enterRule(parameterDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            if (idListContext != null) {
                List<Token> identifiers = idListContext.ids_list;
                if (inResult > 0) {
                    returnParameterDeclarations.addAll(identifiers);
                    for (Token identifier : identifiers) {
                        visibleReturnValues.peek().add(identifier.getText());
                    }
                } else {
                    parameterDeclarations.addAll(identifiers);
                    for (Token identifier : identifiers) {
                        visibleParameters.peek().add(identifier.getText());
                    }
                }
            }
        }

        @Override
        public void enterRule(methodSpecContext ctx) {
            if (ctx.name != null && ctx.name.name != null) {
                methodDeclarations.add(ctx.name.name);
            }

            pushFunctionScope();
        }

        @Override
        public void exitRule(methodSpecContext ctx) {
            popFunctionScope();
        }

        @Override
        public void enterRule(labeledStmtContext ctx) {
            if (ctx.lbl != null) {
                annotations.putProperty(ctx.lbl, ATTR_DECLARATION, Boolean.TRUE);
            }
        }

        @Override
        public void enterRule(labelContext ctx) {
            if (annotations.getProperty(ctx, ATTR_DECLARATION) == Boolean.TRUE) {
                labelDeclarations.add(ctx.name);
            } else if (isVisibleLabel(ctx.name)) {
                labelUses.add(ctx.name);
            }
        }

        @Override
        public void enterRule(typeNameContext ctx) {
            if (ctx.qid != null) {
                if (ctx.qid.pkg == null && ctx.qid.id != null) {
                    if (PREDEFINED_TYPES.contains(ctx.qid.id.getText())) {
                        builtinTypeUses.add(ctx.qid.id);
                        return;
                    }
                }

                if (isVisibleType(ctx.qid)) {
                    typeUses.add(ctx.qid.id);
                }
            }
        }

        @Override
        public void enterRule(baseTypeNameContext ctx) {
            if (isVisibleType(null, ctx.name)) {
                typeUses.add(ctx.name);
            }
        }

        @Override
        public void enterRule(operandContext ctx) {
            if (ctx.qid != null) {
                qualifiedIdentifierContext idctx = ctx.qid;
                if (idctx.pkg == null) {
                    Collection<Token> collection = getContainerForVarReference(idctx.id);
                    if (collection != null) {
                        collection.add(idctx.id);
                    }
                }
            }
            super.enterRule(ctx);
        }

        @Override
        public void enterRule(packageClauseContext ctx) {
            if (ctx.name != null) {
                annotations.putProperty(ctx.name, ATTR_DECLARATION, Boolean.TRUE);
            }
        }

        @Override
        public void enterRule(packageNameContext ctx) {
            if (ctx.name != null) {
                if (annotations.getProperty(ctx, ATTR_DECLARATION) == Boolean.TRUE) {
                    packageDeclarations.add(ctx.name);
                } else if (visiblePackages.contains(ctx.name.getText())) {
                    packageUses.add(ctx.name);
                }
            }
        }

        @Override
        public void enterRule(importSpecContext ctx) {
            if (ctx.name != null) {
                annotations.putProperty(ctx.name, ATTR_DECLARATION, Boolean.TRUE);
                visiblePackages.add(ctx.name.name.getText());
            } else if (ctx.dot == null) {
                String path = ctx.path.path.getText();
                String pkg = path.substring(path.lastIndexOf('/') + 1);
                if (pkg.startsWith("\"")) {
                    pkg = pkg.substring(1);
                }
                if (pkg.endsWith("\"")) {
                    pkg = pkg.substring(0, pkg.length() - 1);
                }
                if (!pkg.isEmpty()) {
                    visiblePackages.add(pkg);
                }
            }
        }

        @Override
        public void enterRule(conversionOrCallExprContext ctx) {
            if (ctx.conv == null) {
                return;
            }

            boolean singleIdentifierType = ctx.conv.t.start == ctx.conv.t.stop
                && ctx.conv.t.start.getType() == GoParserBase.IDENTIFIER;

            if (singleIdentifierType) {
                Token typeToken = ctx.conv.t.start;
                if (PREDEFINED_FUNCTIONS.contains(typeToken.getText())) {
                    builtinFunctionUses.add(typeToken);
                } else if (PREDEFINED_TYPES.contains(typeToken.getText())) {
                    builtinTypeUses.add(typeToken);
                }
            }
        }

        @Override
        public void enterRule(builtinCallContext ctx) {
            if (ctx.name != null && PREDEFINED_FUNCTIONS.contains(ctx.name.getText())) {
                builtinFunctionUses.add(ctx.name);
            }
        }

        @Override
        public void enterRule(callExprContext ctx) {
            if (ctx.e == null) {
                return;
            }

            boolean singleIdentifierTarget = ctx.e.start == ctx.e.stop
                && ctx.e.start.getType() == GoParserBase.IDENTIFIER;

            if (singleIdentifierTarget) {
                Token targetToken = ctx.e.start;
                if (PREDEFINED_FUNCTIONS.contains(targetToken.getText())) {
                    builtinFunctionUses.add(targetToken);
                } else if (PREDEFINED_TYPES.contains(targetToken.getText())) {
                    builtinTypeUses.add(targetToken);
                }
            }
        }

        @Override
        public void enterRule(typeSpecContext ctx) {
            if (ctx.name != null) {
                typeDeclarations.add(ctx.name);
            }
        }

        @Override
        public void enterRule(resultContext ctx) {
            inResult++;
        }

        @Override
        public void exitRule(resultContext ctx) {
            inResult--;
        }

        @Override
        public void enterRule(blockContext ctx) {
            blockLevel++;
            pushBlockScope();
        }

        @Override
        public void exitRule(blockContext ctx) {
            blockLevel--;
            popBlockScope();
        }

        @Override
        public void enterRule(ifStmtContext ctx) {
            blockLevel++;
            pushBlockScope();
        }

        @Override
        public void exitRule(ifStmtContext ctx) {
            blockLevel--;
            popBlockScope();
        }

        @Override
        public void enterRule(switchStmtContext ctx) {
            blockLevel++;
            pushBlockScope();
        }

        @Override
        public void exitRule(switchStmtContext ctx) {
            blockLevel--;
            popBlockScope();
        }

        @Override
        public void enterRule(typeSwitchStmtContext ctx) {
            blockLevel++;
            pushBlockScope();
        }

        @Override
        public void exitRule(typeSwitchStmtContext ctx) {
            blockLevel--;
            popBlockScope();
        }

        @Override
        public void enterRule(forStmtContext ctx) {
            blockLevel++;
            pushBlockScope();
        }

        @Override
        public void exitRule(forStmtContext ctx) {
            blockLevel--;
            popBlockScope();
        }

        private void pushFunctionScope() {
            visibleParameters.push(new HashSet<String>());
            visibleReturnValues.push(new HashSet<String>());
        }

        private void popFunctionScope() {
            visibleParameters.pop();
            visibleReturnValues.pop();
        }

        private void pushBlockScope() {
            visibleLocals.push(new HashSet<String>());
            visibleLabels.push(new HashSet<String>());
        }

        private void popBlockScope() {
            visibleLocals.pop();
            visibleLabels.pop();
        }

        private boolean isVisibleLabel(Token token) {
            String text = token.getText();
            for (Set<String> labels : visibleLabels) {
                if (labels.contains(text)) {
                    return true;
                }
            }

            return false;
        }

        private Collection<Token> getContainerForVarReference(Token token) {
            if (token == null) {
                return null;
            }

            String text = token.getText();

            if (PREDEFINED_CONSTANTS.contains(text)) {
                return builtinConstUses;
            }

            for (Set<String> locals : visibleLocals) {
                if (locals.contains(text)) {
                    return localVarUses;
                }
            }

            for (Set<String> values : visibleReturnValues) {
                if (values.contains(text)) {
                    return returnParameterUses;
                }
            }

            for (Set<String> values : visibleParameters) {
                if (values.contains(text)) {
                    return parameterUses;
                }
            }

            return null;
        }

        private boolean isVisibleType(qualifiedIdentifierContext qid) {
            return isVisibleType(qid.pkg, qid.id);
        }

        private boolean isVisibleType(packageNameContext pkg, Token id) {
            // first check for a type in the current package
            boolean hasPackage = false;
            String unqualifiedName = id.getText();
            String qualifiedName;
            if (pkg != null) {
                hasPackage = true;
                qualifiedName = pkg.name.getText() + "." + unqualifiedName;
            } else {
                qualifiedName = unqualifiedName;
            }

            // first check the cache
            if (knownVisibleTypes.contains(qualifiedName)) {
                return true;
            } else if (knownInvisibleTypes.contains(qualifiedName)) {
                return false;
            }

            String packagePrefix = hasPackage ? pkg.name.getText() + "." : "";

            // get the packages corresponding to the type
            Collection<? extends PackageModel> packages = getPackages(hasPackage ? qualifiedName : "");
            for (PackageModel packageModel : packages) {
                Collection<? extends TypeModel> types = packageModel.getTypes(unqualifiedName);
                for (TypeModel typeModel : types) {
                    knownVisibleTypes.add(packagePrefix + typeModel.getName());
                    if (typeModel.getName().equals(unqualifiedName)) {
                        return true;
                    }
                }
            }

            knownInvisibleTypes.add(qualifiedName);
            return false;
        }

        private Collection<PackageModel> getPackages(String packageName) {
            if (fileModel == null) {
                return Collections.emptyList();
            }

            CodeModelCacheImpl cache = CodeModelCacheImpl.getInstance();
            Collection<? extends ImportDeclarationModel> imports = fileModel.getImportDeclarations();
            List<PackageModel> resolvedImports = new ArrayList<PackageModel>();
            for (ImportDeclarationModel importDeclaration : imports) {
                boolean matches;
                if (packageName.isEmpty()) {
                    matches = importDeclaration.isMergeWithLocal();
                } else {
                    matches = packageName.equals(importDeclaration.getName());
                }

                if (!matches) {
                    continue;
                }

                resolvedImports.addAll(cache.resolvePackages(importDeclaration));
            }

            if (packageName.isEmpty()) {
                PackageModel currentPackage = fileModel.getPackage();
                if (currentPackage != null) {
                    resolvedImports.add(currentPackage);
                }
            }

            return resolvedImports;
        }

    }

}
