/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Tuple2;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.semantics.AbstractParseTreeSemanticHighlighter;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseListener;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;
import org.tvl.goworks.editor.go.semantics.NodeType;

/**
 *
 * @author Sam Harwell
 */
public class SemanticHighlighter extends AbstractParseTreeSemanticHighlighter<SemanticHighlighter.SemanticAnalyzerListener, GoAnnotatedParseTree> {
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
    private final AttributeSet structDeclarationAttributes;
    private final AttributeSet structUseAttributes;
    private final AttributeSet interfaceDeclarationAttributes;
    private final AttributeSet interfaceUseAttributes;
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
        super(document, GoParserDataDefinitions.ANNOTATED_PARSE_TREE);

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
        this.structDeclarationAttributes = getFontAndColors(settings, "structDeclaration");
        this.structUseAttributes = getFontAndColors(settings, "structUse");
        this.interfaceDeclarationAttributes = getFontAndColors(settings, "interfaceDeclaration");
        this.interfaceUseAttributes = getFontAndColors(settings, "interfaceUse");
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
    protected SemanticAnalyzerListener createListener(ParserData<? extends GoAnnotatedParseTree> parserData) {
        FileModel fileModel = null;
        GoAnnotatedParseTree annotatedParseTree = null;
        try {
            Future<ParserData<FileModel>> futureFileModelData = getTaskManager().getData(parserData.getSnapshot(), GoParserDataDefinitions.FILE_MODEL);
            ParserData<FileModel> fileModelData = futureFileModelData != null ? futureFileModelData.get() : null;
            fileModel = fileModelData != null ? fileModelData.getData() : null;
            annotatedParseTree = parserData != null ? parserData.getData() : null;
        } catch (InterruptedException | ExecutionException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while getting the file model.", ex);
        }

        if (fileModel == null || annotatedParseTree == null) {
            return null;
        }

        return new SemanticAnalyzerListener(fileModel, annotatedParseTree);
    }

    @Override
    protected ParseTree getParseTree(ParserData<? extends GoAnnotatedParseTree> parserData) {
        GoAnnotatedParseTree annotatedParseTree = parserData != null ? parserData.getData() : null;
        return annotatedParseTree != null ? annotatedParseTree.getParseTree() : null;
    }

    private final Set<Token> resolvedTokens = new HashSet<>();

    @Override
    protected void addHighlights(List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        if (LOGGER.isLoggable(Level.FINE)) {
            resolvedTokens.addAll(tokens);
        }

        super.addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, tokens, attributes);
    }

    @Override
    protected void updateHighlights(OffsetsBag targetContainer, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, SemanticAnalyzerListener listener) {
        List<Tuple2<OffsetRegion, AttributeSet>> intermediateContainer = new ArrayList<>();
        resolvedTokens.clear();

        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getPackageDeclarations(), packageDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getPackageUses(), packageUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getGlobalConstDeclarations(), globalConstDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getGlobalConstUses(), globalConstUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLocalConstDeclarations(), localConstDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLocalConstUses(), localConstUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getFuncDeclarations(), funcDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getFuncUses(), funcUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getMethodDeclarations(), methodDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getMethodUses(), methodUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getTypeDeclarations(), typeDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getTypeUses(), typeUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getStructDeclarations(), structDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getStructUses(), structUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getInterfaceDeclarations(), interfaceDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getInterfaceUses(), interfaceUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getBuiltinTypeUses(), builtinTypeUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getBuiltinConstUses(), builtinConstUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getBuiltinFunctionUses(), builtinFunctionUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getParameterDeclarations(), parameterDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getParameterUses(), parameterUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getReturnParameterDeclarations(), returnParameterDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getReturnParameterUses(), returnParameterUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getVarUses(), varUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getGlobalVarDeclarations(), globalVarDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getGlobalVarUses(), globalVarUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getFieldVarDeclarations(), fieldVarDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getFieldVarUses(), fieldVarUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLocalVarDeclarations(), localVarDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLocalVarUses(), localVarUseAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLabelDeclarations(), labelDeclarationAttributes);
        addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, listener.getLabelUses(), labelUseAttributes);

        if (LOGGER.isLoggable(Level.FINE)) {
            Collection<Token> unresolved = listener.getUnresolvedIdentifiers();
            unresolved.removeAll(resolvedTokens);
            addHighlights(intermediateContainer, sourceSnapshot, currentSnapshot, unresolved, unresolvedIdentifierAttributes);
        }

        OffsetsBag container = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        fillHighlights(container, intermediateContainer);
        targetContainer.setHighlights(container);
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

    public static class SemanticAnalyzerListener extends GoParserBaseListener {

        private final FileModel fileModel;
        private final GoAnnotatedParseTree annotatedTree;

        private final List<Token> packageDeclarations = new ArrayList<>();
        private final List<Token> packageUses = new ArrayList<>();
        private final List<Token> globalConstDeclarations = new ArrayList<>();
        private final List<Token> globalConstUses = new ArrayList<>();
        private final List<Token> localConstDeclarations = new ArrayList<>();
        private final List<Token> localConstUses = new ArrayList<>();
        private final List<Token> funcDeclarations = new ArrayList<>();
        private final List<Token> funcUses = new ArrayList<>();
        private final List<Token> methodDeclarations = new ArrayList<>();
        private final List<Token> methodUses = new ArrayList<>();
        private final List<Token> typeDeclarations = new ArrayList<>();
        private final List<Token> typeUses = new ArrayList<>();
        private final List<Token> structDeclarations = new ArrayList<>();
        private final List<Token> structUses = new ArrayList<>();
        private final List<Token> interfaceDeclarations = new ArrayList<>();
        private final List<Token> interfaceUses = new ArrayList<>();
        private final List<Token> builtinTypeUses = new ArrayList<>();
        private final List<Token> builtinConstUses = new ArrayList<>();
        private final List<Token> builtinFunctionUses = new ArrayList<>();
        private final List<Token> parameterDeclarations = new ArrayList<>();
        private final List<Token> parameterUses = new ArrayList<>();
        private final List<Token> returnParameterDeclarations = new ArrayList<>();
        private final List<Token> returnParameterUses = new ArrayList<>();
        private final List<Token> varUses = new ArrayList<>();
        private final List<Token> globalVarDeclarations = new ArrayList<>();
        private final List<Token> globalVarUses = new ArrayList<>();
        private final List<Token> fieldVarDeclarations = new ArrayList<>();
        private final List<Token> fieldVarUses = new ArrayList<>();
        private final List<Token> localVarDeclarations = new ArrayList<>();
        private final List<Token> localVarUses = new ArrayList<>();
        private final List<Token> labelDeclarations = new ArrayList<>();
        private final List<Token> labelUses = new ArrayList<>();

        private final Set<Token> unresolvedIdentifiers = new HashSet<>();

        public SemanticAnalyzerListener(@NonNull FileModel fileModel, @NonNull GoAnnotatedParseTree annotatedTree) {
            Parameters.notNull("fileModel", fileModel);
            Parameters.notNull("annotatedTree", annotatedTree);
            this.fileModel = fileModel;
            this.annotatedTree = annotatedTree;
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

        public List<Token> getStructDeclarations() {
            return structDeclarations;
        }

        public List<Token> getStructUses() {
            return structUses;
        }

        public List<Token> getInterfaceDeclarations() {
            return interfaceDeclarations;
        }

        public List<Token> getInterfaceUses() {
            return interfaceUses;
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
        public void visitTerminal(TerminalNode node) {
            Token symbol = node.getSymbol();
            if (symbol.getType() != GoParser.IDENTIFIER) {
                return;
            }

            boolean isDeclaration = annotatedTree.isDeclaration(node);
            boolean resolved = annotatedTree.isResolved(node);
            if (!resolved) {
                unresolvedIdentifiers.add(symbol);
                return;
            }

            NodeType nodeType = annotatedTree.getNodeType(node);
            switch (nodeType) {
            case PACKAGE_DECL:
                packageDeclarations.add(symbol);
                break;

            case PACKAGE_REF:
                packageUses.add(symbol);
                break;

            case CONST_DECL:
                if (annotatedTree.isGlobal(node)) {
                    this.globalConstDeclarations.add(symbol);
                } else {
                    this.localConstDeclarations.add(symbol);
                }
                break;

            case CONST_REF:
                if (annotatedTree.isGlobal(node)) {
                    this.globalConstUses.add(symbol);
                } else {
                    this.localConstUses.add(symbol);
                }
                break;

            case VAR_DECL:
            case VAR_REF:
                VarKind varType = annotatedTree.getVarType(node);
                Collection<Token> varCollection;
                switch (varType) {
                case GLOBAL:
                    varCollection = isDeclaration ? globalVarDeclarations : globalVarUses;
                    break;

                case FIELD:
                    varCollection = isDeclaration ? fieldVarDeclarations : fieldVarUses;
                    break;

                case LOCAL:
                    varCollection = isDeclaration ? localVarDeclarations : localVarUses;
                    break;

                case RECEIVER:
                case PARAMETER:
                    varCollection = isDeclaration ? parameterDeclarations : parameterUses;
                    break;

                case RETURN:
                    varCollection = isDeclaration ? returnParameterDeclarations : returnParameterUses;
                    break;

                default:
                    varCollection = null;
                    break;
                }
                
                if (varCollection != null) {
                    varCollection.add(symbol);
                }

                break;

            case FUNC_DECL:
                this.funcDeclarations.add(symbol);
                break;

            case FUNC_REF:
                if (annotatedTree.isBuiltin(node)) {
                    this.builtinFunctionUses.add(symbol);
                } else {
                    this.funcUses.add(symbol);
                }
                break;

            case METHOD_DECL:
                this.methodDeclarations.add(symbol);
                break;

            case METHOD_REF:
                this.methodUses.add(symbol);
                break;

            case TYPE_DECL:
            case TYPE_REF:
                TypeKind kind = annotatedTree.getTypeKind(node);
                isDeclaration = annotatedTree.isDeclaration(node);
                Collection<Token> typeCollection;
                switch (kind) {
                case INTERFACE:
                    typeCollection = isDeclaration ? interfaceDeclarations : interfaceUses;
                    break;

                case STRUCT:
                    typeCollection = isDeclaration ? structDeclarations : structUses;
                    break;

                case INTRINSIC:
                    typeCollection = builtinTypeUses;
                    break;

                case ALIAS:
                case ARRAY:
                case CHANNEL:
                case FUNCTION:
                case MAP:
                case POINTER:
                case SLICE:
                case UNKNOWN:
                case UNDEFINED:
                default:
                    typeCollection = isDeclaration ? typeDeclarations : typeUses;
                    break;
                }

                if (typeCollection != null) {
                    typeCollection.add(symbol);
                }

                break;

            case LABEL_DECL:
                this.labelDeclarations.add(symbol);
                break;

            case LABEL_REF:
                this.labelUses.add(symbol);
                break;

            case UNKNOWN:
            case UNDEFINED:
            default:
                if (symbol.getType() == GoParser.IDENTIFIER) {
                    unresolvedIdentifiers.add(symbol);
                }
                break;
            }
        }

    }

}
