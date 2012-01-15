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
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.shared.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.shared.AbstractSemanticHighlighter;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.blockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener> {
    private static final Set<String> PREDEFINED_TYPES = new HashSet<String>()
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

    private static final Set<String> PREDEFINED_CONSTANTS = new HashSet<String>()
        {{
            add("true");
            add("false");
            add("iota");
            add("nil");
        }};

    private static final Set<String> PREDEFINED_FUNCTIONS = new HashSet<String>()
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

    private SemanticHighlighter(@NonNull StyledDocument document) {
        super(document, GoParserDataDefinitions.REFERENCE_PARSE_TREE);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GoEditorKit.GO_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);

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
    }

    @Override
    protected SemanticAnalyzerListener createListener() {
        return new SemanticAnalyzerListener();
    }

    @Override
    protected void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
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

        private int inResult;
        private int inBlock;

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

        @Override
        public void enterRule(constSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (inBlock > 0) {
                localConstDeclarations.addAll(identifiers);
            } else {
                globalConstDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(varSpecContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (inBlock > 0) {
                localVarDeclarations.addAll(identifiers);
            } else {
                globalVarDeclarations.addAll(identifiers);
            }
        }

        @Override
        public void enterRule(shortVarDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            List<Token> identifiers = idListContext.ids_list;
            if (inBlock > 0) {
                localVarDeclarations.addAll(identifiers);
            } else {
                globalVarDeclarations.addAll(identifiers);
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
        public void enterRule(parameterDeclContext ctx) {
            identifierListContext idListContext = ctx.idList;
            if (idListContext != null) {
                List<Token> identifiers = idListContext.ids_list;
                if (inResult > 0) {
                    returnParameterDeclarations.addAll(identifiers);
                } else {
                    parameterDeclarations.addAll(identifiers);
                }
            }
        }

        @Override
        public void enterRule(typeNameContext ctx) {
            if (ctx.getChild(0) instanceof qualifiedIdentifierContext) {
                qualifiedIdentifierContext idctx = (qualifiedIdentifierContext)ctx.getChild(0);
                if (idctx.pkg == null && idctx.id != null) {
                    if (PREDEFINED_TYPES.contains(idctx.id.getText())) {
                        builtinTypeUses.add(idctx.id);
                    }
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
            inBlock++;
        }

        @Override
        public void exitRule(blockContext ctx) {
            inBlock--;
        }

    }

}
