/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentTextUtilities;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeArrayModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeChannelModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VarModelImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CallExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ElementTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LabeledStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.LiteralTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.RangeClauseContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.RecvStmtContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SelectorExprContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TypeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.GoLexer;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;
import org.tvl.goworks.editor.go.semantics.CodeElementReference;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;
import org.tvl.goworks.editor.go.semantics.GoAnnotations;
import org.tvl.goworks.editor.go.semantics.NodeType;
import org.tvl.goworks.editor.go.semantics.SemanticAnalyzer;

/**
 *
 * @author Sam Harwell
 */
public final class GoCompletionQuery extends AbstractCompletionQuery {
    // -J-Dorg.tvl.goworks.editor.go.completion.GoCompletionQuery.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoCompletionQuery.class.getName());

    private boolean possibleReference;

    /*package*/ GoCompletionQuery(GoCompletionProvider completionProvider, int queryType, int caretOffset, boolean hasTask, boolean extend) {
        super(completionProvider, queryType, caretOffset, hasTask, extend);
    }

    public static <T extends Token> boolean isInContext(Parser<T> parser, RuleContext<T> context, IntervalSet values) {
        return isInContext(parser, context, values, true);
    }

    public static <T extends Token> boolean isInContext(Parser<T> parser, RuleContext<T> context, IntervalSet values, boolean checkTop) {
        return getTopContext(parser, context, values, checkTop) != null;
    }

    public static boolean isInContext(ParserRuleContext<?> context, IntervalSet values) {
        return isInContext(context, values, true);
    }

    public static boolean isInContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        return getTopContext(context, values, checkTop) != null;
    }

    public static <T extends Token> RuleContext<T> getTopContext(Parser<T> parser, RuleContext<T> context, IntervalSet values) {
        return getTopContext(parser, context, values, true);
    }

    public static <T extends Token> RuleContext<T> getTopContext(Parser<T> parser, RuleContext<T> context, IntervalSet values, boolean checkTop) {
        if (checkTop && context instanceof ParserRuleContext<?>) {
            if (values.contains(context.getRuleIndex())) {
                return context;
            }
        }

        if (context.isEmpty()) {
            return null;
        }

        if (values.contains(parser.getATN().states.get(context.invokingState).ruleIndex)) {
            return context.parent;
        }

        return getTopContext(parser, context.parent, values, false);
    }

    public static ParserRuleContext<?> getTopContext(ParserRuleContext<?> context, IntervalSet values) {
        return getTopContext(context, values, true);
    }

    public static ParserRuleContext<?> getTopContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        if (checkTop && values.contains(context.getRuleIndex())) {
            return context;
        }

        if (context.isEmpty()) {
            return null;
        }

        return getTopContext((ParserRuleContext<?>)context.parent, values, true);
    }

    @Override
    public GoCompletionProvider getCompletionProvider() {
        return (GoCompletionProvider)super.getCompletionProvider();
    }

    @Override
    protected boolean isQueryContext(Document doc, int caretOffset) {
        return getCompletionProvider().isGoContext(getComponent(), caretOffset, true);
    }

    @Override
    protected Task getTask(BaseDocument document) {
        VersionedDocument buffer = VersionedDocumentUtilities.getVersionedDocument(document);
        DocumentSnapshot snapshot = buffer.getCurrentSnapshot();
        return new TaskImpl(document, snapshot);
    }

    /*package*/ static boolean isIdentifierPart(String typedText) {
        for (int i = 0; i < typedText.length(); i++) {
            if (!Character.isJavaIdentifierPart(typedText.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected CompletionItem createDeclarationCompletionItem(Document document, TrackingPositionRegion applicableTo) {
        return new DeclarationCompletionItem(document, applicableTo);
    }

    private static final ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> ATTR_CONSTANTS = new ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>>("constants");
    private static final ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> ATTR_LOCALS = new ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>>("locals");
    private static final ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> ATTR_PARAMETER = new ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>>("parameter");
    private static final ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> ATTR_RECEIVER_PARAMETER = new ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>>("receiver-parameter");
    private static final ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> ATTR_RETURN_PARAMETER = new ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>>("return-parameter");

    private static final ObjectProperty<Collection<? extends CodeElementModel>> ATTR_TARGET = new ObjectProperty<Collection<? extends CodeElementModel>>("target");

    @RuleDependencies({
        // these are dependencies from the BREAK_SCOPES and CONTINUE_SCOPES fields
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forStmt, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_switchStmt, version=0),
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_selectStmt, version=0),
    })
    private class TaskImpl extends Task {
        private final DocumentSnapshot snapshot;
        private final ParserTaskManager taskManager;

        private FileModel fileModel;
        private boolean fileModelDataFailed = false;

        private CodeCompletionGoParser parser;
        private LocalsAnalyzer localsAnalyzer = new LocalsAnalyzer();
        private TargetAnalyzer targetAnalyzer = new TargetAnalyzer();

        private final IntervalSet BREAK_SCOPES = new IntervalSet() {{
            add(GoParser.RULE_forStmt);
            add(GoParser.RULE_switchStmt);
            add(GoParser.RULE_selectStmt);
        }};

        private final IntervalSet CONTINUE_SCOPES = IntervalSet.of(GoParser.RULE_forStmt);

        public TaskImpl(BaseDocument document, DocumentSnapshot snapshot) {
            super(document);
            Parameters.notNull("snapshot", snapshot);

            this.snapshot = snapshot;
            this.taskManager = getParserTaskManager();

            if (taskManager == null) {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_label, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchCase, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchCase, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commCase, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0),
        })
        public void runImpl(BaseDocument document) {
            results = new ArrayList<CompletionItem>();
            possibleDeclaration = true;
            possibleReference = true;

            // Add context items (labels, etc). Use anchor points to optimize information gathering.

            Map<RuleContext<Token>, CaretReachedException> parseTrees;
            Map<ParseTree<Token>, GoAnnotatedParseTree> annotatedParseTrees;
            CaretToken caretToken = null;

            List<Anchor> anchors;
            Future<ParserData<List<Anchor>>> result =
                taskManager.getData(snapshot, GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
            try {
                anchors = result != null ? result.get().getData() : null;
            } catch (InterruptedException ex) {
                anchors = null;
            } catch (ExecutionException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred while getting anchor points.", ex);
                anchors = null;
            }

            if (anchors != null) {
                Anchor enclosing = null;
                Anchor previous = null;
                Anchor next = null;

                /*
                 * parse the current rule
                 */
                for (Anchor anchor : anchors) {
                    // TODO: support more anchors
                    if (anchor.getRule() != GoParser.RULE_topLevelDecl) {
                        continue;
                    }

                    if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= getCaretOffset()) {
                        previous = anchor;
                        if (anchor.getSpan().getEndPosition(snapshot).getOffset() > getCaretOffset()) {
                            enclosing = anchor;
                        }
                    } else {
                        next = anchor;
                        break;
                    }
                }

                if (previous != null) {
                    Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                    Tagger<TokenTag<Token>> tagger = null;
                    try {
                        tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.WARNING, "An exception occurred while getting tokens.", ex);
                    } catch (ExecutionException ex) {
                        LOGGER.log(Level.WARNING, "An exception occurred while getting tokens.", ex);
                    }

                    int regionEnd = Math.min(snapshot.length(), getCaretOffset() + 1);
                    OffsetRegion region;
                    if (enclosing != null) {
                        region = OffsetRegion.fromBounds(enclosing.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
                    } else {
                        // at least for now, include the previous span due to the way error handling places bounds on an anchor
                        region = OffsetRegion.fromBounds(previous.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
                    }

                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE, "Code completion from anchor region: {0}.", region);
                    }

                    TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(snapshot, region));
                    TokenSource<Token> tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                    CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                    parser = ParserCache.DEFAULT.getParser(tokens);
                    try {
                        parser.setBuildParseTree(true);
                        parser.setErrorHandler(new CodeCompletionErrorStrategy<Token>());
                        @SuppressWarnings("LocalVariableHidesMemberVariable")
                        FileModel fileModel = getFileModel();
                        if (fileModel != null) {
                            Set<String> packageNames = new HashSet<String>();
                            for (ImportDeclarationModel model : fileModel.getImportDeclarations()) {
                                String name = model.getName();
                                if (!name.isEmpty() && !name.equals(".")) {
                                    packageNames.add(name);
                                }
                            }

                            parser.setCheckPackageNames(true);
                            parser.setPackageNames(packageNames);
                        }

                        switch (previous.getRule()) {
                        case GoParser.RULE_topLevelDecl:
                            parseTrees = GoForestParser.INSTANCE.getParseTrees(parser);
                            annotatedParseTrees = new HashMap<ParseTree<Token>, GoAnnotatedParseTree>();
                            break;

                        default:
                            parseTrees = null;
                            annotatedParseTrees = null;
                            break;
                        }

                        if (parseTrees != null) {
                            possibleDeclaration = false;
                            possibleReference = false;

                            declarationOrReferenceLoop:
                            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                                CaretReachedException ex = entry.getValue();
                                if (ex == null || ex.getTransitions() == null) {
                                    continue;
                                }

                                if (ex.getCaretToken() != null) {
                                    caretToken = ex.getCaretToken();
                                }

                                Map<ATNConfig, List<Transition>> transitions = entry.getValue().getTransitions();
                                for (Map.Entry<ATNConfig, List<Transition>> transitionEntry : transitions.entrySet()) {
                                    for (Transition t : transitionEntry.getValue()) {
                                        IntervalSet label = t.label();
                                        if (label == null || !label.contains(GoParser.IDENTIFIER)) {
                                            continue;
                                        }

                                        int ruleIndex = t.target.ruleIndex;
                                        switch (ruleIndex) {
                                        case GoParser.RULE_methodName:
                                            {
                                                int invokingRule = ParseTrees.getInvokingRule(parser.getATN(), entry.getValue().getFinalContext());
                                                if (invokingRule == GoParser.RULE_methodSpec || invokingRule == GoParser.RULE_methodDecl) {
                                                    possibleDeclaration = true;
                                                } else {
                                                    possibleReference = true;
                                                }
                                            }
                                            break;

                                        case GoParser.RULE_label:
                                            {
                                                int invokingRule = ParseTrees.getInvokingRule(parser.getATN(), entry.getValue().getFinalContext());
                                                if (invokingRule == GoParser.RULE_labeledStmt) {
                                                    possibleDeclaration = true;
                                                } else {
                                                    possibleReference = true;
                                                }
                                            }
                                            break;

                                        case GoParser.RULE_packageName:
                                            if (isInContext(parser, entry.getValue().getFinalContext(), IntervalSet.of(GoParser.RULE_packageClause))) {
                                                possibleDeclaration = true;
                                            } else {
                                                possibleReference = true;
                                            }

                                            break;

                                        case GoParser.RULE_baseTypeName:
                                        case GoParser.RULE_builtinCall: // only happens for builtin method name
                                        case GoParser.RULE_expression:  // only happens for selector
                                        case GoParser.RULE_fieldName:
                                        case GoParser.RULE_qualifiedIdentifier:
                                            possibleReference = true;
                                            break;

                                        case GoParser.RULE_identifierList:
                                        case GoParser.RULE_functionDecl:
                                        case GoParser.RULE_receiver:
                                        case GoParser.RULE_typeSpec:
                                        case GoParser.RULE_typeSwitchGuard:
                                            possibleDeclaration = true;
                                            break;

                                        default:
                                            break;
                                        }

                                        if (possibleDeclaration && possibleReference) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        Map<String, CompletionItem> intermediateResults = new HashMap<String, CompletionItem>();
                        if (parseTrees != null) {
                            /*
                            * KEYWORD ANALYSIS
                            */
                            boolean canContinue = false;
                            boolean canBreak = false;
                            boolean canDefaultOrCase = false;

                            IntervalSet allowedKeywords = new IntervalSet();
                            IntervalSet remainingKeywords = new IntervalSet(KeywordCompletionItem.KEYWORD_TYPES);

                            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                                CaretReachedException caretReachedException = entry.getValue();
                                if (caretReachedException == null || caretReachedException.getTransitions() == null) {
                                    continue;
                                }

                                Map<ATNConfig, List<Transition>> transitions = caretReachedException.getTransitions();
                                for (List<Transition> transitionList : transitions.values()) {
                                    for (Transition transition : transitionList) {
                                        if (transition.isEpsilon() || transition instanceof WildcardTransition || transition instanceof NotSetTransition) {
                                            continue;
                                        }

                                        IntervalSet label = transition.label();
                                        if (label == null) {
                                            continue;
                                        }

                                        if (!canContinue && label.contains(GoLexer.Continue)) {
                                            canContinue = isInContext(parser, caretReachedException.getFinalContext(), CONTINUE_SCOPES);
                                            if (canContinue) {
                                                canBreak = true;
                                            }
                                        }

                                        if (!canBreak && label.contains(GoLexer.Break)) {
                                            canBreak = isInContext(parser, caretReachedException.getFinalContext(), BREAK_SCOPES);
                                        }

                                        if (!canDefaultOrCase && label.contains(GoLexer.Default)) {
                                            if (caretReachedException.getFinalContext() instanceof ParserRuleContext<?>) {
                                                int currentRule = caretReachedException.getFinalContext().getRuleIndex();
                                                canDefaultOrCase =
                                                    currentRule == GoParser.RULE_typeSwitchCase
                                                    || currentRule == GoParser.RULE_exprSwitchCase
                                                    || currentRule == GoParser.RULE_commCase;
                                            } else {
                                                canDefaultOrCase = true;
                                            }
                                        }

                                        for (int keyword : remainingKeywords.toArray()) {
                                            if (label.contains(keyword)) {
                                                remainingKeywords.remove(keyword);
                                                allowedKeywords.add(keyword);
                                            }
                                        }
                                    }
                                }
                            }

                            if (!canContinue) {
                                allowedKeywords.remove(GoLexer.Continue);
                            }

                            if (!canBreak) {
                                allowedKeywords.remove(GoLexer.Break);
                            }

                            if (!canDefaultOrCase) {
                                allowedKeywords.remove(GoLexer.Case);
                                allowedKeywords.remove(GoLexer.Default);
                            }

                            for (int keyword : allowedKeywords.toArray()) {
                                KeywordCompletionItem item = new KeywordCompletionItem(GoLexer.tokenNames[keyword].substring(1, GoLexer.tokenNames[keyword].length() - 1));
                                intermediateResults.put(item.getInsertPrefix().toString(), item);
                            }

                            /*
                            * EXPRESSION ANALYSIS
                            */

                            boolean addedPackages = false;
                            boolean addedTypes = false;
                            boolean addedVars = false;
                            boolean addedFunctions = false;

                            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : parseTrees.entrySet()) {
                                RuleContext<Token> finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                                if (finalContext == null) {
                                    continue;
                                }

                                Map<ATNConfig, List<Transition>> transitions = entry.getValue().getTransitions();
                                assert transitions != null;
                                assert transitions.size() == 1;
                                List<Transition> singleTransitionList = transitions.values().iterator().next();
                                assert singleTransitionList.size() == 1;
                                Transition transition = singleTransitionList.get(0);
                                // only interested in identifiers
                                IntervalSet label = transition.label();
                                if (label == null || !label.contains(GoParser.IDENTIFIER)) {
                                    continue;
                                }

                                // if selectorExpressionRoot is not null, then we are on the right hand side of a '.'
                                ParseTree<Token> selectorExpressionRoot = null;
                                ParserRuleContext<Token> selectorTarget = null;
                                boolean isTypeSwitchGuard = false;

                                List<ParseTree<Token>> pathToRoot = new ArrayList<ParseTree<Token>>(ParseTrees.getAncestors(finalContext));
                                pathToRoot.add(finalContext);
                                Collections.reverse(pathToRoot);
                                for (ParseTree<Token> tree : pathToRoot) {
                                    if (tree instanceof GoParser.SelectorExprContext) {
                                        GoParser.SelectorExprContext context = (GoParser.SelectorExprContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = tree;
                                            selectorTarget = context.expression();
                                            break;
                                        }
                                    } else if (tree instanceof GoParser.TypeSwitchGuardContext) {
                                        GoParser.TypeSwitchGuardContext context = (GoParser.TypeSwitchGuardContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = tree;
                                            selectorTarget = context.expression();
                                            break;
                                        }
                                    } else if (tree instanceof GoParser.TypeAssertionExprContext) {
                                        GoParser.TypeAssertionExprContext context = (GoParser.TypeAssertionExprContext)tree;
                                        if (context.dot != null && context.lp == null) {
                                            selectorExpressionRoot = tree;
                                            selectorTarget = context.expression();
                                            break;
                                        }
                                    } else if (tree instanceof GoParser.MethodExprContext) {
                                        GoParser.MethodExprContext context = (GoParser.MethodExprContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = tree;
                                            selectorTarget = context.receiverType();
                                            break;
                                        }
                                    } else if (tree instanceof GoParser.QualifiedIdentifierContext) {
                                        GoParser.QualifiedIdentifierContext context = (GoParser.QualifiedIdentifierContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = tree;
                                            selectorTarget = context.packageName();
                                            break;
                                        }
                                    }
                                }

                                if (selectorExpressionRoot != null) {
                                    /*
                                    * SELECTOR EXPRESSION
                                    */
                                    if (isTypeSwitchGuard) {
                                        intermediateResults.put("(type)", new KeywordCompletionItem("(type)"));
                                        continue;
                                    }

                                    if (getFileModel() == null) {
                                        continue;
                                    }

                                    final boolean INCLUDE_EXPRESSION = false;
                                    if (INCLUDE_EXPRESSION) {
                                        String text = tokens.getText(selectorTarget.start, selectorTarget.stop);
                                        if (text != null && !text.isEmpty()) {
                                            intermediateResults.put(text, new KeywordCompletionItem(text));
                                        }
                                    }

                                    if (annotatedParseTrees == null) {
                                        continue;
                                    }

                                    GoAnnotatedParseTree annotatedParseTree = getAnnotatedParseTree(snapshot.getVersionedDocument(), entry.getKey(), annotatedParseTrees);
                                    PackageModel currentPackage = getFileModel().getPackage();
                                    Map<String, Collection<PackageModel>> resolvedPackages = new HashMap<String, Collection<PackageModel>>();
                                    for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                                        Collection<PackageModel> packages = resolvedPackages.get(importDeclarationModel.getName());
                                        if (packages == null) {
                                            packages = new ArrayList<PackageModel>();
                                            resolvedPackages.put(importDeclarationModel.getName(), packages);
                                        }

                                        packages.addAll(CodeModelCacheImpl.getInstance().getPackages(getFileModel().getPackage().getProject(), importDeclarationModel.getPath()));
                                    }

                                    Collection<? extends CodeElementModel> models = resolveSelectorTarget(selectorTarget, annotatedParseTree, currentPackage, resolvedPackages);
                                    assert models != null;
                                    for (CodeElementModel model : models) {
                                        Collection<? extends CodeElementModel> selected = SemanticAnalyzer.getSelectableMembers(model);
                                        for (CodeElementModel selectedModel : selected) {
                                            if (selectedModel instanceof FunctionModel) {
                                                FunctionModel function = (FunctionModel)selectedModel;
                                                intermediateResults.put(function.getName(), new FunctionReferenceCompletionItem(function));
                                            } else if (selectedModel instanceof ConstModel) {
                                                ConstModel function = (ConstModel)selectedModel;
                                                intermediateResults.put(function.getName(), new ConstReferenceCompletionItem(function, false));
                                            } else if (selectedModel instanceof VarModel) {
                                                VarModel function = (VarModel)selectedModel;
                                                intermediateResults.put(function.getName(), new VarReferenceCompletionItem(function, false));
                                            } else if (selectedModel instanceof TypeModel) {
                                                TypeModel function = (TypeModel)selectedModel;
                                                intermediateResults.put(function.getName(), new TypeReferenceCompletionItem(function));
                                            } else {
                                                LOGGER.log(Level.FINE, "TODO: Unknown model '{0}'.", model.getClass().getSimpleName());
                                            }
                                        }

                                        //if (model instanceof PackageModel) {
                                        //    PackageModel packageModel = (PackageModel)model;
                                        //    if (selectorExpressionRoot instanceof GoParser.methodExprContext) {
                                        //        continue;
                                        //    }
                                        //
                                        //    for (FunctionModel function : packageModel.getFunctions()) {
                                        //        intermediateResults.put(function.getName(), new FunctionReferenceCompletionItem(function));
                                        //    }
                                        //
                                        //    for (ConstModel constant : packageModel.getConstants()) {
                                        //        intermediateResults.put(constant.getName(), new ConstReferenceCompletionItem(constant, false));
                                        //    }
                                        //
                                        //    for (VarModel var : packageModel.getVars()) {
                                        //        intermediateResults.put(var.getName(), new VarReferenceCompletionItem(var, false));
                                        //    }
                                        //
                                        //    for (TypeModel type : packageModel.getTypes()) {
                                        //        intermediateResults.put(type.getName(), new TypeReferenceCompletionItem(type));
                                        //    }
                                        //} else if (model instanceof TypeModel) {
                                        //    TypeModel typeModel = (TypeModel)model;
                                        //    if (selectorExpressionRoot instanceof GoParser.methodExprContext) {
                                        //        for (FunctionModel method : typeModel.getMethods()) {
                                        //            intermediateResults.put(method.getName(), new FunctionReferenceCompletionItem(method));
                                        //        }
                                        //        continue;
                                        //    }
                                        //
                                        //    // TODO: any other possibilities?
                                        //} else if (model instanceof VarModel) {
                                        //    VarModel varModel = (VarModel)model;
                                        //    if (selectorExpressionRoot instanceof GoParser.methodExprContext) {
                                        //        continue;
                                        //    }
                                        //
                                        //    TypeModel varType = varModel.getVarType();
                                        //    for (FunctionModel method : varType.getMethods()) {
                                        //        intermediateResults.put(method.getName(), new FunctionReferenceCompletionItem(method));
                                        //    }
                                        //
                                        //    for (VarModel var : varType.getFields()) {
                                        //        intermediateResults.put(var.getName(), new VarReferenceCompletionItem(var, false));
                                        //    }
                                        //} else {
                                        //    LOGGER.log(Level.FINE, "TODO: Unknown model '{0}'.", model.getClass().getSimpleName());
                                        //}
                                    }
                                } else if (finalContext instanceof GoParser.PackageNameContext) {
                                    /* AVAILABLE PACKAGES
                                    */
                                    if (getFileModel() == null || addedPackages) {
                                        continue;
                                    }

                                    addedPackages = true;
                                    Collection<? extends ImportDeclarationModel> imports = getFileModel().getImportDeclarations();
                                    for (ImportDeclarationModel model : imports) {
                                        if (intermediateResults.containsKey(model.getName())) {
                                            continue;
                                        }

                                        intermediateResults.put(model.getName(), new PackageReferenceCompletionItem(model.getName()));
                                    }
                                } else {
                                    /*
                                    * UNQUALIFIED EXPRESSION. Identifier could reference any visible:
                                    *  - type
                                    *  - var
                                    *  - function
                                    *  - method
                                    *
                                    * Visible means located in any of the following:
                                    *  - the current package
                                    *  - any packages imported with alias '.'
                                    *  - built-in elements
                                    */
                                    RuleContext<Token> compositeLiteralRuleContext = getTopContext(parser, finalContext, IntervalSet.of(GoParser.RULE_compositeLiteral));
                                    GoParser.CompositeLiteralContext compositeLiteralContext = (GoParser.CompositeLiteralContext)compositeLiteralRuleContext;
                                    if (finalContext instanceof GoParser.FieldNameContext) {
                                        assert compositeLiteralContext != null && compositeLiteralContext.literalType() != null;

                                        List<GoParser.LiteralValueContext> literalValueContexts = new ArrayList<GoParser.LiteralValueContext>();
                                        for (RuleContext<Token> context = finalContext; context != null; context = context.parent) {
                                            if (context instanceof GoParser.LiteralValueContext) {
                                                literalValueContexts.add((GoParser.LiteralValueContext)context);
                                            } else if (context instanceof GoParser.CompositeLiteralContext) {
                                                // stop at the containing compositeLiteral
                                                break;
                                            }
                                        }

                                        // first resolve the type of the containing compositeLiteral
                                        Collection<? extends CodeElementModel> models = targetAnalyzer.resolveTarget(compositeLiteralContext.literalType());
                                        if (literalValueContexts.size() > 1) {
                                            LOGGER.log(Level.FINE, "TODO: resolve nested values - need type of the innermost value.");
                                            models = Collections.emptyList();
                                        }

                                        if (!models.isEmpty()) {
                                            for (CodeElementModel model : models) {
                                                if (!(model instanceof TypeModel)) {
                                                    continue;
                                                }

                                                TypeModel typeModel = (TypeModel)model;
                                                for (VarModel field : typeModel.getFields()) {
                                                    String key = field.getName() + ":";
                                                    if (intermediateResults.containsKey(key)) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(key, new FieldReferenceKeyCompletionItem(field));
                                                }
                                            }
                                        }
                                    } else if (finalContext instanceof GoParser.QualifiedIdentifierContext) {
                                        GoParser.QualifiedIdentifierContext context = (GoParser.QualifiedIdentifierContext)finalContext;
                                        if (context.packageName() != null) {
                                            continue;
                                        }

                                        if (compositeLiteralContext != null && compositeLiteralContext.literalValue() != null) {
                                            LOGGER.log(Level.FINE, "TODO: is there any other work to do for this case?");
                                        }

                                        // add items from the current package and imported "mergeWithLocal" packages
                                        List<PackageModel> visiblePackages = new ArrayList<PackageModel>();
                                        visiblePackages.add(getFileModel().getPackage());
                                        for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                                            if (importDeclarationModel.isMergeWithLocal()) {
                                                Collection<? extends PackageModel> resolved = CodeModelCacheImpl.getInstance().resolvePackages(importDeclarationModel);
                                                if (resolved != null) {
                                                    visiblePackages.addAll(resolved);
                                                }
                                            }
                                        }

                                        if (finalContext.getParent() instanceof GoParser.TypeNameContext) {
                                            for (String builtin : SemanticHighlighter.PREDEFINED_TYPES) {
                                                if (intermediateResults.containsKey(builtin)) {
                                                    continue;
                                                }

                                                intermediateResults.put(builtin, new TypeReferenceCompletionItem(builtin));
                                            }

                                            for (PackageModel packageModel : visiblePackages) {
                                                Collection<? extends TypeModel> types = packageModel.getTypes();
                                                for (TypeModel model : types) {
                                                    if (intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new TypeReferenceCompletionItem(model));
                                                }
                                            }
                                        } else {
                                            assert finalContext.getParent() instanceof GoParser.OperandContext;
                                            // add builtin items
                                            for (String builtin : SemanticHighlighter.PREDEFINED_CONSTANTS) {
                                                if (intermediateResults.containsKey(builtin)) {
                                                    continue;
                                                }

                                                intermediateResults.put(builtin, new ConstReferenceCompletionItem(builtin, false));
                                            }

                                            for (PackageModel packageModel : visiblePackages) {
                                                Collection<? extends ConstModel> constants = packageModel.getConstants();
                                                for (ConstModel model : constants) {
                                                    if (intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new ConstReferenceCompletionItem(model, false));
                                                }

                                                Collection<? extends VarModel> vars = packageModel.getVars();
                                                for (VarModel model : vars) {
                                                    if (intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new VarReferenceCompletionItem(model, false));
                                                }

                                                Collection<? extends FunctionModel> functions = packageModel.getFunctions();
                                                for (FunctionModel model : functions) {
                                                    if (model.getReceiverParameter() != null || intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new FunctionReferenceCompletionItem(model));
                                                }
                                            }

                                            LOGGER.log(Level.FINE, "TODO: proper block scope for vars");
                                            Map<TerminalNode<Token>, ParserRuleContext<Token>> constants = Collections.emptyMap();

                                            ParseTree<Token> functionContext = getTopContext(parser, finalContext, new IntervalSet() {{ add(GoParser.RULE_functionDecl); add(GoParser.RULE_methodDecl); }});
                                            if (functionContext != null) {
                                                addVars(VarKind.RECEIVER, localsAnalyzer.getReceiverParameters(functionContext), intermediateResults);
                                                addVars(VarKind.PARAMETER, localsAnalyzer.getParameters(functionContext), intermediateResults);
                                                addVars(VarKind.RETURN, localsAnalyzer.getReturnParameters(functionContext), intermediateResults);
                                                addVars(VarKind.LOCAL, localsAnalyzer.getLocals(functionContext), intermediateResults);

                                                constants.putAll(localsAnalyzer.getConstants(functionContext));
                                            }

                                            LOGGER.log(Level.FINE, "TODO: local constants");
    //                                        for (Map.Entry<Token, ParserRuleContext<Token>> constEntry : constants.entrySet()) {
    //                                            String name = constEntry.getKey().getText();
    //                                            if (intermediateResults.containsKey(name)) {
    //                                                continue;
    //                                            }
    //
    //                                            Collection<? extends CodeElementModel> varTypes = targetAnalyzer.resolveTarget(constEntry.getValue());
    //                                            for (CodeElementModel varType : varTypes) {
    //                                                if (!(varType instanceof TypeModel)) {
    //                                                    continue;
    //                                                }
    //
    //                                                VarModelImpl varModel = new VarModelImpl(name, (TypeModel)varType, (FileModelImpl)getFileModel());
    //                                                intermediateResults.put(name, new VarReferenceCompletionItem(varModel));
    //                                                break;
    //                                            }
    //                                        }
                                        }
                                    } else if (finalContext instanceof GoParser.BaseTypeNameContext) {
                                        // this is the name of a type in the current package
                                        if (getFileModel() == null) {
                                            continue;
                                        }

                                        PackageModel packageModel = getFileModel().getPackage();
                                        Collection<? extends TypeModel> types = packageModel.getTypes();
                                        for (TypeModel typeModel : types) {
                                            if (intermediateResults.containsKey(typeModel.getName())) {
                                                continue;
                                            }

                                            intermediateResults.put(typeModel.getName(), new TypeReferenceCompletionItem(typeModel));
                                        }
                                    } else if (finalContext instanceof GoParser.LabelContext) {
                                        if (finalContext.getParent() instanceof GoParser.LabeledStmtContext) {
                                            continue;
                                        }

                                        final List<Token> labels = new ArrayList<Token>();
                                        ParseTreeListener<Token> listener = new GoParserBaseListener() {

                                            @Override
                                            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0)
                                            public void enterLabeledStmt(LabeledStmtContext ctx) {
                                                if (ctx.label() != null && ctx.label().IDENTIFIER() != null) {
                                                    labels.add(ctx.label().IDENTIFIER().getSymbol());
                                                }
                                            }

                                        };

                                        ParseTreeWalker.DEFAULT.walk(listener, entry.getKey());
                                        for (Token token : labels) {
                                            if (intermediateResults.containsKey(token.getText())) {
                                                continue;
                                            }

                                            intermediateResults.put(token.getText(), new LabelReferenceCompletionItem(token.getText()));
                                        }

                                        LOGGER.log(Level.FINE, "TODO: Also include labels for the current function as obtained from the FunctionModel.");
                                    } else if (finalContext instanceof GoParser.BuiltinCallContext) {
                                        // this is easy - just add the names of built in methods
                                        for (String builtin : SemanticHighlighter.PREDEFINED_FUNCTIONS) {
                                            if (intermediateResults.containsKey(builtin)) {
                                                continue;
                                            }

                                            intermediateResults.put(builtin, new FunctionReferenceCompletionItem(builtin));
                                        }
                                    } else if (finalContext instanceof GoParser.MethodNameContext) {
                                        // This block only handles non-dotted identifiers, which means this can't be a methodExpr
                                        // and is therefore always a declaration (never a reference).
                                        continue;
                                    } else if (finalContext instanceof GoParser.ReceiverContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParser.FunctionDeclContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParser.TypeSpecContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParser.IdentifierListContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParser.TypeSwitchGuardContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    }
                                }
                            }

                            results.addAll(intermediateResults.values());
                        }
                    } finally {
                        ParserCache.DEFAULT.putParser(parser);
                        parser = null;
                    }
                }
            }

            OffsetRegion applicableToSpan;
            if (caretToken != null && caretToken.getOriginalToken() != null && caretToken.getOriginalToken().getChannel() == Token.DEFAULT_CHANNEL) {
                applicableToSpan = OffsetRegion.fromBounds(caretToken.getStartIndex(), caretToken.getStopIndex() + 1);
            } else {
                SnapshotPositionRegion identifier = DocumentTextUtilities.getIdentifierBlock(new SnapshotPosition(snapshot, getCaretOffset()));
                if (identifier != null) {
                    applicableToSpan = identifier.getRegion();
                } else {
                    applicableToSpan = OffsetRegion.fromBounds(getCaretOffset(), getCaretOffset());
                }
            }

            if (!isExtend() && applicableToSpan.contains(getCaretOffset())) {
                applicableToSpan = OffsetRegion.fromBounds(applicableToSpan.getStart(), getCaretOffset());
            }

            if (!applicableToSpan.isEmpty()) {
                // make sure this is a word
                String applicableText = snapshot.subSequence(applicableToSpan.getStart(), applicableToSpan.getEnd()).toString();
                if (!WORD_PATTERN.matcher(applicableText).matches()) {
                    applicableToSpan = OffsetRegion.fromBounds(getCaretOffset(), getCaretOffset());
                }
            }

            applicableTo = snapshot.createTrackingRegion(applicableToSpan, TrackingPositionRegion.Bias.Inclusive);
            if ((getQueryType() & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
                String enteredText = applicableTo.getText(snapshot);
                List<CompletionItem> updated = new ArrayList<CompletionItem>();
                for (CompletionItem item : results) {
                    if (item.getInsertPrefix().equals(enteredText)) {
                        updated.add(item);
                    }
                }

                results.clear();
                results.addAll(updated);
                if (updated.size() > 0) {
                    if (updated.get(0) instanceof GoCompletionItem) {
                        getToolTip().setTipText(((GoCompletionItem)updated.get(0)).getToolTipText());
                    } else {
                        getToolTip().setTipText(updated.get(0).getInsertPrefix().toString());
                    }
                }
            }
        }

        private void addVars(VarKind varKind, Map<? extends TerminalNode<? extends Token>, ? extends ParserRuleContext<Token>> vars, Map<String, ? super VarReferenceCompletionItem> intermediateResults) {
            for (Map.Entry<? extends TerminalNode<? extends Token>, ? extends ParserRuleContext<Token>> varEntry : vars.entrySet()) {
                String name = varEntry.getKey().getText();
                if (intermediateResults.containsKey(name)) {
                    continue;
                }

                Collection<? extends CodeElementModel> varTypes = targetAnalyzer.resolveTarget(varEntry.getValue());
                if (varTypes.isEmpty()) {
                    varTypes = Collections.singleton(new UnknownTypeModelImpl((FileModelImpl)getFileModel()));
                }

                for (CodeElementModel model : varTypes) {
                    TypeModelImpl typeModel;
                    if (model instanceof TypeModelImpl) {
                        typeModel = (TypeModelImpl)model;
                    } else if (model instanceof VarModelImpl) {
                        // this could be an implicit reference to the return value of a function
                        VarModelImpl varModel = (VarModelImpl)model;
                        if (varModel.getVarKind() != VarKind.RETURN) {
                            continue;
                        }

                        typeModel = varModel.getVarType();
                    } else {
                        continue;
                    }

                    VarModelImpl varModel = new VarModelImpl(name, varKind, typeModel, (FileModelImpl)getFileModel(), varEntry.getKey(), varEntry.getValue());
                    intermediateResults.put(name, new VarReferenceCompletionItem(varModel, true));
                    break;
                }
            }
        }

        private FileModel getFileModel() {
            if (fileModel == null && !fileModelDataFailed) {
                Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
                try {
                    fileModel = futureFileModelData != null ? futureFileModelData.get().getData() : null;
                    fileModelDataFailed = fileModel != null;
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while getting the file model.", ex);
                    fileModelDataFailed = true;
                } catch (ExecutionException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while getting the file model.", ex);
                    fileModelDataFailed = true;
                }
            }

            return fileModel;
        }

        private ObjectDecorator<ParseTree<Token>> annotations = new ObjectDecorator<ParseTree<Token>>();

        private class LocalsAnalyzer {

//            private final List<Token> labels = new ArrayList<Token>();

            public Map<TerminalNode<Token>, ParserRuleContext<Token>> getLocals(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_LOCALS);
            }

            public Map<TerminalNode<Token>, ParserRuleContext<Token>> getConstants(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_CONSTANTS);
            }

            public Map<TerminalNode<Token>, ParserRuleContext<Token>> getReceiverParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RECEIVER_PARAMETER);
            }

            public Map<TerminalNode<Token>, ParserRuleContext<Token>> getParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_PARAMETER);
            }

            public Map<TerminalNode<Token>, ParserRuleContext<Token>> getReturnParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RETURN_PARAMETER);
            }

            private Map<TerminalNode<Token>, ParserRuleContext<Token>> getLocals(ParseTree<Token> context, ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> property) {
                Map<TerminalNode<Token>, ParserRuleContext<Token>> result = getLocalsProperty(context, property);
                if (result != null) {
                    return result;
                }

                Listener listener = new Listener();
                ParseTreeWalker.DEFAULT.walk(listener, context);
                setLocalsProperty(context, ATTR_LOCALS, listener.getLocals());
                setLocalsProperty(context, ATTR_CONSTANTS, listener.getConstants());
                setLocalsProperty(context, ATTR_PARAMETER, listener.getParameters());
                setLocalsProperty(context, ATTR_RECEIVER_PARAMETER, listener.getReceiverParameters());
                setLocalsProperty(context, ATTR_RETURN_PARAMETER, listener.getReturnParameters());

                result = getLocalsProperty(context, property);
                if (result == null) {
                    LOGGER.log(Level.FINE, "TODO: resolve locals");
                }

                return result != null ? result : Collections.<TerminalNode<Token>, ParserRuleContext<Token>>emptyMap();
            }

//            public List<Token> getLabels() {
//                return labels;
//            }

            private Map<TerminalNode<Token>, ParserRuleContext<Token>> getLocalsProperty(ParseTree<Token> context, ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> property) {
                Map<TerminalNode<Token>, ParserRuleContext<Token>> result = annotations.getProperty(context, property);
                if (result != null) {
                    return result;
                }

                return null;
            }

            private void setLocalsProperty(ParseTree<Token> context, ObjectProperty<Map<TerminalNode<Token>, ParserRuleContext<Token>>> property, @NonNull Map<TerminalNode<Token>, ParserRuleContext<Token>> locals) {
                Parameters.notNull("locals", locals);
                annotations.putProperty(context, property, locals);
            }

            private class Listener extends GoParserBaseListener {
                private final Map<TerminalNode<Token>, ParserRuleContext<Token>> locals = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();
                private final Map<TerminalNode<Token>, ParserRuleContext<Token>> receiverParameters = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();
                private final Map<TerminalNode<Token>, ParserRuleContext<Token>> parameters = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();
                private final Map<TerminalNode<Token>, ParserRuleContext<Token>> returnParameters = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();
                private final Map<TerminalNode<Token>, ParserRuleContext<Token>> constants = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();

                public Map<TerminalNode<Token>, ParserRuleContext<Token>> getLocals() {
                    return locals;
                }

                public Map<TerminalNode<Token>, ParserRuleContext<Token>> getConstants() {
                    return constants;
                }

                public Map<TerminalNode<Token>, ParserRuleContext<Token>> getReceiverParameters() {
                    return receiverParameters;
                }

                public Map<TerminalNode<Token>, ParserRuleContext<Token>> getParameters() {
                    return parameters;
                }

                public Map<TerminalNode<Token>, ParserRuleContext<Token>> getReturnParameters() {
                    return returnParameters;
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
                })
                public void enterVarSpec(VarSpecContext ctx) {
                    addVars(locals, ctx.identifierList(), ctx.type(), ctx.expressionList(0));
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
                })
                public void enterShortVarDecl(ShortVarDeclContext ctx) {
                    addVars(locals, ctx.identifierList(), null, ctx.expressionList());
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
                })
                public void enterRangeClause(RangeClauseContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.put(getStartNode(ctx.e1), null);
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.put(getStartNode(ctx.e2), null);
                        }
                    }
                }

                private TerminalNode<Token> getStartNode(ParseTree<Token> context) {
                    if (context instanceof TerminalNode<?>) {
                        return (TerminalNode<Token>)context;
                    }

                    for (int i = 0; i < context.getChildCount(); i++) {
                        TerminalNode<Token> node = getStartNode(context.getChild(i));
                        if (node != null) {
                            return node;
                        }
                    }

                    return null;
                }

                @Override
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0)
                public void enterTypeSwitchGuard(TypeSwitchGuardContext ctx) {
                    if (ctx.IDENTIFIER() != null) {
                        locals.put(ctx.IDENTIFIER(), null);
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvStmt, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
                })
                public void enterRecvStmt(RecvStmtContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.put(getStartNode(ctx.e1), null);
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.put(getStartNode(ctx.e2), null);
                        }
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
                })
                public void enterReceiver(ReceiverContext ctx) {
                    if (ctx.IDENTIFIER() != null) {
                        receiverParameters.put(ctx.IDENTIFIER(), ctx.baseTypeName());
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
                })
                public void enterParameterDecl(ParameterDeclContext ctx) {
                    if (ctx.identifierList() != null) {
                        GoParser.ParametersContext parametersContext = (GoParser.ParametersContext)getTopContext(parser, ctx, IntervalSet.of(GoParser.RULE_parameters));
                        Map<TerminalNode<Token>, ParserRuleContext<Token>> map = parametersContext.parent instanceof GoParser.ResultContext ? returnParameters : parameters;
                        addVars(map, ctx.identifierList(), ctx.type(), null);
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
                })
                public void enterConstSpec(ConstSpecContext ctx) {
                    addVars(constants, ctx.identifierList(), ctx.type(), ctx.expressionList());
                }

                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
                })
                private void addVars(@NonNull Map<TerminalNode<Token>, ParserRuleContext<Token>> map,
                                     @NullAllowed GoParser.IdentifierListContext idList,
                                     @NullAllowed GoParser.TypeContext explicitType,
                                     @NullAllowed GoParser.ExpressionListContext exprList) {

                    if (idList == null || idList.IDENTIFIER() == null) {
                        return;
                    }

                    List<? extends GoParser.ExpressionContext> expressions = exprList != null ? exprList.expression() : null;
                    for (int i = 0; i < idList.IDENTIFIER().size(); i++) {
                        TerminalNode<Token> name = idList.IDENTIFIER(i);
                        ParserRuleContext<Token> type = explicitType;
                        if (type == null && expressions != null && i < expressions.size()) {
                            type = expressions.get(i);
                        }

                        map.put(name, type);
                    }
                }
            }
        }

        private class TargetAnalyzer extends GoParserBaseListener {

            @NonNull
            public Collection<? extends CodeElementModel> resolveTarget(@NullAllowed ParseTree<Token> context) {
                if (context == null) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> result = getTargetProperty(context);
                if (result != null) {
                    return result;
                }

                SingleElementParseTreeWalker.SINGLE_ELEMENT_WALKER.walk(this, context);
                result = getTargetProperty(context);
                if (result == null) {
                    LOGGER.log(Level.FINE, "TODO: resolve selector target");
                }

                return result != null ? result : Collections.<CodeElementModel>emptyList();
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0),
            })
            public void enterPackageName(PackageNameContext ctx) {
                FileModel fileModel = getFileModel();
                if (ctx.IDENTIFIER() == null || fileModel == null) {
                    return;
                }

                if (ctx.getParent() instanceof GoParser.PackageClauseContext) {
                    setTargetProperty(ctx, Collections.singletonList(fileModel.getPackage()));
                } else {
                    // look up the package by import
                    CodeModelCache cache = CodeModelCacheImpl.getInstance();
                    Collection<CodeElementModel> resolved = new ArrayList<CodeElementModel>();
                    Collection<? extends ImportDeclarationModel> imports = fileModel.getImportDeclarations();
                    for (ImportDeclarationModel model : imports) {
                        if (model.getName().equals(ctx.start.getText())) {
                            resolved.addAll(cache.getPackages(fileModel.getPackage().getProject(), model.getPath()));
                        }
                    }

                    setTargetProperty(ctx, resolved);
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
            })
            public void enterOperandExpr(OperandExprContext ctx) {
                OperandContext operandContext = ctx.operand();
                if (operandContext != null) {
                    annotations.putProperty(ctx, ATTR_TARGET, resolveTarget(operandContext));
                } else {
                    LOGGER.log(Level.FINE, "TODO: handle other expressions.");
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
            public void enterBuiltinCallExpr(BuiltinCallExprContext ctx) {
                LOGGER.log(Level.FINE, "TODO: handle other expressions.");
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
            public void enterSelectorExpr(SelectorExprContext ctx) {
                if (ctx.IDENTIFIER() == null) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                String name = ctx.IDENTIFIER().getSymbol().getText();
                if (name == null || name.isEmpty()) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                Collection<? extends CodeElementModel> targets = resolveTarget(ctx.expression());
                if (targets == null) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                List<CodeElementModel> members = new ArrayList<CodeElementModel>();
                for (CodeElementModel target : targets) {
                    members.addAll(target.getMembers(name));
                }

                annotations.putProperty(ctx, ATTR_TARGET, members);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
            })
            public void enterType(TypeContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.typeName() != null) {
                    result = resolveTarget(ctx.typeName());
                } else if (ctx.typeLiteral() != null) {
                    result = resolveTarget(ctx.typeLiteral());
                } else if (ctx.type() != null) {
                    result = resolveTarget(ctx.type());
                } else {
                    LOGGER.log(Level.FINE, "Unknown type syntax.");
                    result = null;
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
            public void enterTypeLiteral(TypeLiteralContext ctx) {
                assert ctx.getChildCount() <= 1 : "Unknown typeLiteral syntax.";
                Collection<? extends CodeElementModel> result = resolveTarget(ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public void enterArrayType(ArrayTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(resolveTarget(ctx.elementType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypeArrayModelImpl((TypeModelImpl)result.get(i)));
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0),
            })
            public void enterElementType(ElementTypeContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.type() != null) {
                    result = resolveTarget(ctx.type());
                } else {
                    result = Collections.emptyList();
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
            public void enterStructType(StructTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
            })
            public void enterPointerType(PointerTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.baseType() != null) {
                    result.addAll(resolveTarget(ctx.baseType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypePointerModelImpl((TypeModelImpl)result.get(i)));
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0)
            public void enterBaseType(BaseTypeContext ctx) {
                if (ctx.getChildCount() != 1) {
                    LOGGER.log(Level.FINE, "Unknown baseType syntax.");
                    return;
                }

                Collection<? extends CodeElementModel> result = resolveTarget(ctx.type());
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0)
            public void enterFunctionType(FunctionTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0)
            public void enterInterfaceType(InterfaceTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public void enterSliceType(SliceTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(resolveTarget(ctx.elementType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypeSliceModelImpl((TypeModelImpl)result.get(i)));
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0)
            public void enterMapType(MapTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public void enterChannelType(ChannelTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(resolveTarget(ctx.elementType()));
                }

                ChannelKind channelKind = ChannelKind.SendReceive;
                if (ctx.send != null) {
                    channelKind = ChannelKind.Send;
                } else if (ctx.recv != null) {
                    channelKind = ChannelKind.Receive;
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypeChannelModelImpl((TypeModelImpl)result.get(i), channelKind));
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public void enterLiteralType(LiteralTypeContext ctx) {
                if (ctx.elementType() != null) {
                    LOGGER.log(Level.FINE, "TODO: resolve implicit array creation.");
                    return;
                }

                assert ctx.getChildCount() <= 1 : "Unknown literalType syntax.";
                Collection<? extends CodeElementModel> result = resolveTarget(ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0)
            public void enterTypeName(TypeNameContext ctx) {
                assert ctx.getChildCount() <= 1 : "Unknown typeName syntax.";
                Collection<? extends CodeElementModel> result = resolveTarget(ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
            })
            public void enterBaseTypeName(BaseTypeNameContext ctx) {
                // must be a type in the current package
                PackageModel currentPackage = getFileModel().getPackage();
                Collection<? extends TypeModel> types = currentPackage.getTypes(ctx.IDENTIFIER().getSymbol().getText());

                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                ReceiverContext receiverContext = (ReceiverContext)ctx.parent;
                boolean isptr = receiverContext.ptr != null;
                if (isptr) {
                    for (TypeModel model : types) {
                        assert model instanceof TypeModelImpl;
                        if (!(model instanceof TypeModelImpl)) {
                            continue;
                        }

                        TypePointerModel ptr = new TypePointerModelImpl((TypeModelImpl)model);
                        result.add(ptr);
                    }
                } else {
                    result.addAll(types);
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0),
            })
            public void enterOperand(OperandContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.literal() != null) {
                    result = resolveTarget(ctx.literal());
                } else if (ctx.qualifiedIdentifier() != null) {
                    result = resolveTarget(ctx.qualifiedIdentifier());
                } else if (ctx.methodExpr() != null) {
                    result = resolveTarget(ctx.methodExpr());
                } else if (ctx.expression() != null) {
                    result = resolveTarget(ctx.expression());
                } else {
                    LOGGER.log(Level.FINE, "TODO: unknown typeName syntax.");
                    result = null;
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=0)
            public void enterCallExpr(CallExprContext ctx) {
                if (ctx.expression() == null) {
                    return;
                }

                Collection<? extends CodeElementModel> methodResults = resolveTarget(ctx.expression());
                List<CodeElementModel> results = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : methodResults) {
                    if (!(model instanceof FunctionModel)) {
                        continue;
                    }

                    results.addAll(((FunctionModel)model).getReturnValues());
                }

                annotations.putProperty(ctx, ATTR_TARGET, results);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
            })
            public void enterQualifiedIdentifier(QualifiedIdentifierContext ctx) {
                Map<TerminalNode<Token>, ParserRuleContext<Token>> vars = Collections.emptyMap();
                List<CodeElementModel> contextModels = new ArrayList<CodeElementModel>();
                List<ImportDeclarationModel> possibleImports = new ArrayList<ImportDeclarationModel>();
                if (ctx.packageName() == null) {
                    contextModels.add(getFileModel().getPackage());
                    for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                        if (importDeclarationModel.isMergeWithLocal()) {
                            possibleImports.add(importDeclarationModel);
                        }
                    }

                    ParseTree<Token> functionContext = getTopContext(parser, ctx, new IntervalSet() {{ add(GoParser.RULE_functionDecl); add(GoParser.RULE_methodDecl); }});
                    if (functionContext != null) {
                        vars = new IdentityHashMap<TerminalNode<Token>, ParserRuleContext<Token>>();
                        vars.putAll(localsAnalyzer.getReceiverParameters(functionContext));
                        vars.putAll(localsAnalyzer.getParameters(functionContext));
                        vars.putAll(localsAnalyzer.getReturnParameters(functionContext));
                        vars.putAll(localsAnalyzer.getConstants(functionContext));
                        vars.putAll(localsAnalyzer.getLocals(functionContext));
                    }
                } else {
                    String pkgName = ctx.packageName().IDENTIFIER().getSymbol().getText();
                    for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                        if (!importDeclarationModel.isMergeWithLocal() && pkgName.equals(importDeclarationModel.getName())) {
                            possibleImports.add(importDeclarationModel);
                        }
                    }
                }

                PackageModel builtinPackage = CodeModelCacheImpl.getInstance().getUniquePackage(getFileModel().getPackage().getProject(), "builtin");
                if (builtinPackage != null) {
                    contextModels.add(builtinPackage);
                }

                for (ImportDeclarationModel importDeclarationModel : possibleImports) {
                    Collection<? extends PackageModel> resolved = CodeModelCacheImpl.getInstance().resolvePackages(importDeclarationModel);
                    if (resolved != null) {
                        contextModels.addAll(resolved);
                    }
                }

                Set<CodeElementModel> members = new HashSet<CodeElementModel>();
                TerminalNode<Token> nameNode = ctx.IDENTIFIER();
                if (nameNode != null) {
                    String name = nameNode.getSymbol().getText();
                    for (CodeElementModel model : contextModels) {
                        members.addAll(model.getMembers(name));
                    }

                    for (Map.Entry<TerminalNode<Token>, ParserRuleContext<Token>> entry : vars.entrySet()) {
                        if (!name.equals(entry.getKey().getText())) {
                            continue;
                        }

                        Collection<? extends CodeElementModel> varTypes = resolveTarget(entry.getValue());
                        for (CodeElementModel varType : varTypes) {
                            if (!(varType instanceof TypeModel)) {
                                continue;
                            }

                            // TODO: use proper var kind
                            VarModelImpl varModel = new VarModelImpl(name, VarKind.LOCAL, (TypeModelImpl)varType, (FileModelImpl)getFileModel(), entry.getKey(), entry.getValue());
                            members.add(varModel);
                        }
                    }
                }

                annotations.putProperty(ctx, ATTR_TARGET, members);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
            })
            public void enterLiteral(LiteralContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.basicLiteral() != null) {
                    result = resolveTarget(ctx.basicLiteral());
                } else if (ctx.compositeLiteral() != null) {
                    result = resolveTarget(ctx.compositeLiteral());
                } else if (ctx.functionLiteral() != null) {
                    result = resolveTarget(ctx.functionLiteral());
                } else {
                    result = Collections.emptyList();
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0)
            public void enterBasicLiteral(BasicLiteralContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.INT_LITERAL() != null) {
                    result = Collections.singletonList(IntrinsicTypeModels.INT);
                } else if (ctx.FLOAT_LITERAL() != null) {
                    result = Collections.singletonList(IntrinsicTypeModels.FLOAT32);
                } else if (ctx.IMAGINARY_LITERAL() != null) {
                    result = Collections.singletonList(IntrinsicTypeModels.COMPLEX64);
                } else if (ctx.CharLiteral() != null) {
                    result = Collections.singletonList(IntrinsicTypeModels.INT32);
                } else if (ctx.StringLiteral() != null) {
                    result = Collections.singletonList(IntrinsicTypeModels.STRING);
                } else {
                    result = Collections.emptyList();
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public void enterCompositeLiteral(CompositeLiteralContext ctx) {
                Collection<? extends CodeElementModel> result;
                LiteralTypeContext literalType = ctx.literalType();
                if (literalType != null) {
                    if (literalType.structType() != null) {
                        result = resolveTarget(literalType.structType());
                    } else if (literalType.arrayType() != null) {
                        result = resolveTarget(literalType.arrayType());
                    } else if (literalType.sliceType() != null) {
                        result = resolveTarget(literalType.sliceType());
                    } else if (literalType.mapType() != null) {
                        result = resolveTarget(literalType.mapType());
                    } else if (literalType.typeName() != null) {
                        result = resolveTarget(literalType.typeName());
                    } else if (literalType.elementType() != null) {
                        List<CodeElementModel> arrayElementType = new ArrayList<CodeElementModel>();
                        arrayElementType.addAll(resolveTarget(literalType.elementType()));
                        for (int i = 0; i < arrayElementType.size(); i++) {
                            CodeElementModel elementType = arrayElementType.get(i);
                            if (elementType instanceof TypeModelImpl) {
                                // eventually array types will have their length embedded
                                arrayElementType.set(i, new TypeArrayModelImpl((TypeModelImpl)elementType));
                            }
                        }

                        result = arrayElementType;
                    } else {
                        result = Collections.emptyList();
                    }
                } else {
                    result = Collections.emptyList();
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterFunctionLiteral(FunctionLiteralContext ctx) {
            }

            private Collection<? extends CodeElementModel> getTargetProperty(ParseTree<Token> context) {
                return annotations.getProperty(context, ATTR_TARGET);
            }

            private void setTargetProperty(ParseTree<Token> context, @NonNull Collection<? extends CodeElementModel> models) {
                Parameters.notNull("models", models);

                annotations.putProperty(context, ATTR_TARGET, models);
            }
        }

        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0)
        private Collection<? extends CodeElementModel> resolveSelectorTarget(ParserRuleContext<Token> qualifier, GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
            if (qualifier == null) {
                // don't have the information necessary to resolve
                return Collections.emptyList();
            }

            ObjectDecorator<Tree> treeDecorator = annotatedParseTree.getTreeDecorator();
            ObjectDecorator<Token> tokenDecorator = annotatedParseTree.getTokenDecorator();
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
                        return Collections.emptyList();
                    }

                    Token unqualifiedLink = treeDecorator.getProperty(qualifier, GoAnnotations.UNQUALIFIED_LINK);
                    if (unqualifiedLink != null) {
                        Map<? extends ObjectProperty<?>, ?> properties = tokenDecorator.getProperties(unqualifiedLink);
//                        treeDecorator.putProperties(qualifier, properties);
                        qualifierNodeType = treeDecorator.getProperty(qualifier, GoAnnotations.NODE_TYPE);
                        if (qualifierNodeType == NodeType.UNDEFINED) {
//                            treeDecorator.putProperty(qualifier, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
                            qualifierNodeType = NodeType.UNKNOWN;
                        }
                    } else {
                        if (LOGGER.isLoggable(Level.WARNING)) {
                            LOGGER.log(Level.WARNING, "Unable to resolve unqualified link from qualifier: {0}", qualifier.toString(Arrays.asList(GoParser.ruleNames)));
                        }

                        qualifierNodeType = NodeType.UNKNOWN;
                    }
                }

                assert qualifierNodeType != NodeType.UNDEFINED;

                if (qualifierNodeType == NodeType.UNKNOWN) {
                    // can't resolve a dereference if the qualifier couldn't be resolved
//                    tokenDecorator.putProperty(token, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
//                    treeDecorator.putProperty(node, GoAnnotations.NODE_TYPE, NodeType.UNKNOWN);
                    return Collections.emptyList();
                }

                if (qualifierNodeType == NodeType.TYPE_LITERAL) {
                    throw new UnsupportedOperationException("Not yet implemented");
                    //return resolveQualifierType(qualifier, currentPackage, resolvedPackages);
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

            return resolvedQualifier;
            //List<CodeElementModel> qualifiedModels = new ArrayList<CodeElementModel>();
            //for (CodeElementModel model : resolvedQualifier) {
            //    qualifiedModels.addAll(SemanticAnalyzer.getSelectableMembers(model));
            //}
            //
            //return qualifiedModels;
        }

        private GoAnnotatedParseTree getAnnotatedParseTree(VersionedDocument document, ParseTree<Token> parseTree, Map<ParseTree<Token>, GoAnnotatedParseTree> annotatedParseTrees) {
            GoAnnotatedParseTree annotatedParseTree = annotatedParseTrees.get(parseTree);
            if (annotatedParseTree == null) {
                annotatedParseTree = SemanticAnalyzer.analyze(document, parseTree);
                annotatedParseTrees.put(parseTree, annotatedParseTree);
            }

            return annotatedParseTree;
        }
    }

    private static class SingleElementParseTreeWalker extends ParseTreeWalker {
        public static final SingleElementParseTreeWalker SINGLE_ELEMENT_WALKER = new SingleElementParseTreeWalker();

        @Override
        public <Symbol extends Token> void walk(@NonNull ParseTreeListener<? super Symbol> listener, @NonNull ParseTree<Symbol> t) {
            Parameters.notNull("listener", listener);
            Parameters.notNull("t", t);

            if (t instanceof RuleNode) {
                RuleNode<Symbol> r = (RuleNode<Symbol>)t;
                enterRule(listener, r);
                exitRule(listener, r);
                return;
            }

            super.walk(listener, t);
        }

    }

    public static class UnknownTypeModelImpl extends TypeModelImpl {

        public UnknownTypeModelImpl(FileModelImpl fileModel) {
            this("?", fileModel);
        }

        public UnknownTypeModelImpl(String name, FileModelImpl fileModel) {
            super(name, fileModel, null, null);
        }

        @Override
        public Collection<? extends TypeModelImpl> resolve() {
            return Collections.singletonList(this);
        }

        @Override
        public boolean isResolved() {
            return true;
        }

        @Override
        public TypeKind getKind() {
            return TypeKind.UNKNOWN;
        }

        @Override
        public String getSimpleName() {
            return "?";
        }

        @Override
        public Collection<? extends AbstractCodeElementModel> getMembers() {
            return Collections.emptyList();
        }

    }
}
