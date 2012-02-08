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
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
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
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoLexerBase;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.callExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.pointerTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.rangeClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.recvStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectorExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.structTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;
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

    private static final ParserCache parserCache = new ParserCache();

    private boolean possibleReference;

    /*package*/ GoCompletionQuery(GoCompletionProvider completionProvider, int queryType, int caretOffset, boolean hasTask, boolean extend) {
        super(completionProvider, queryType, caretOffset, hasTask, extend);
    }

    public static boolean isInContext(Parser parser, RuleContext context, IntervalSet values) {
        return isInContext(parser, context, values, true);
    }

    public static boolean isInContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
        return getTopContext(parser, context, values, checkTop) != null;
    }

    public static boolean isInContext(ParserRuleContext<?> context, IntervalSet values) {
        return isInContext(context, values, true);
    }

    public static boolean isInContext(ParserRuleContext<?> context, IntervalSet values, boolean checkTop) {
        return getTopContext(context, values, checkTop) != null;
    }

    public static RuleContext getTopContext(Parser parser, RuleContext context, IntervalSet values) {
        return getTopContext(parser, context, values, true);
    }

    public static RuleContext getTopContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
        if (checkTop && context instanceof ParserRuleContext<?>) {
            if (values.contains(((ParserRuleContext<?>)context).ruleIndex)) {
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
        if (checkTop && values.contains(context.ruleIndex)) {
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
    protected boolean isQueryContext(CompletionResultSet resultSet, Document doc, int caretOffset) {
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

    private static final ObjectProperty<Map<Token, ParserRuleContext<Token>>> ATTR_CONSTANTS = new ObjectProperty<Map<Token, ParserRuleContext<Token>>>("constants");
    private static final ObjectProperty<Map<Token, ParserRuleContext<Token>>> ATTR_LOCALS = new ObjectProperty<Map<Token, ParserRuleContext<Token>>>("locals");
    private static final ObjectProperty<Map<Token, ParserRuleContext<Token>>> ATTR_PARAMETER = new ObjectProperty<Map<Token, ParserRuleContext<Token>>>("parameter");
    private static final ObjectProperty<Map<Token, ParserRuleContext<Token>>> ATTR_RECEIVER_PARAMETER = new ObjectProperty<Map<Token, ParserRuleContext<Token>>>("receiver-parameter");
    private static final ObjectProperty<Map<Token, ParserRuleContext<Token>>> ATTR_RETURN_PARAMETER = new ObjectProperty<Map<Token, ParserRuleContext<Token>>>("return-parameter");

    private static final ObjectProperty<Collection<? extends CodeElementModel>> ATTR_TARGET = new ObjectProperty<Collection<? extends CodeElementModel>>("target");

    private class TaskImpl extends Task {
        private final DocumentSnapshot snapshot;
        private final ParserTaskManager taskManager;

        private FileModel fileModel;
        private boolean fileModelDataFailed = false;

        private CodeCompletionGoParser parser;
        private LocalsAnalyzer localsAnalyzer = new LocalsAnalyzer();
        private TargetAnalyzer targetAnalyzer = new TargetAnalyzer();

        private final IntervalSet BREAK_SCOPES = new IntervalSet() {{
            add(GoParserBase.RULE_forStmt);
            add(GoParserBase.RULE_switchStmt);
            add(GoParserBase.RULE_selectStmt);
        }};

        private final IntervalSet CONTINUE_SCOPES = IntervalSet.of(GoParserBase.RULE_forStmt);

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
        public void runImpl(BaseDocument document) {
            results = new ArrayList<CompletionItem>();
            possibleDeclaration = true;
            possibleReference = true;

            // Add context items (labels, etc). Use anchor points to optimize information gathering.

            Map<RuleContext, CaretReachedException> parseTrees;
            Map<RuleContext, GoAnnotatedParseTree> annotatedParseTrees;
            CaretToken caretToken = null;

            List<Anchor> anchors;
            Future<ParserData<List<Anchor>>> result =
                taskManager.getData(snapshot, GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
            try {
                anchors = result.get().getData();
            } catch (InterruptedException ex) {
                anchors = null;
            } catch (ExecutionException ex) {
                Exceptions.printStackTrace(ex);
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
                    if (anchor.getRule() != GoParserBase.RULE_topLevelDecl) {
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
                        tagger = futureTokensData.get().getData();
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
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

                    TaggerTokenSource taggerTokenSource = new TaggerTokenSource(tagger, new SnapshotPositionRegion(snapshot, region));
                    TokenSource tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                    CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                    parser = parserCache.getParser(tokens);
                    try {
                        parser.setBuildParseTree(true);
                        parser.setErrorHandler(new CodeCompletionErrorStrategy());
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

                            parser.setPackageNames(packageNames);
                        }

                        switch (previous.getRule()) {
                        case GoParserBase.RULE_topLevelDecl:
                            parseTrees = getParseTrees(parser);
                            annotatedParseTrees = analyzeParseTrees(snapshot.getVersionedDocument(), parseTrees);
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
                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                CaretReachedException ex = entry.getValue();
                                if (ex == null || ex.getTransitions() == null) {
                                    continue;
                                }

                                if (ex.getCaretToken() != null) {
                                    caretToken = ex.getCaretToken();
                                }

                                Map<ATNConfig, List<Transition>> transitions = entry.getValue().getTransitions();
                                for (ATNConfig c : transitions.keySet()) {
                                    for (Transition t : transitions.get(c)) {
                                        if (!t.label().contains(GoParserBase.IDENTIFIER)) {
                                            continue;
                                        }

                                        int ruleIndex = t.target.ruleIndex;
                                        switch (ruleIndex) {
                                        case GoParserBase.RULE_methodName:
                                            {
                                                int invokingRule = ParseTrees.getInvokingRule(parser.getATN(), entry.getValue().getFinalContext());
                                                if (invokingRule == GoParserBase.RULE_methodSpec || invokingRule == GoParserBase.RULE_methodDecl) {
                                                    possibleDeclaration = true;
                                                } else {
                                                    possibleReference = true;
                                                }
                                            }
                                            break;

                                        case GoParserBase.RULE_label:
                                            {
                                                int invokingRule = ParseTrees.getInvokingRule(parser.getATN(), entry.getValue().getFinalContext());
                                                if (invokingRule == GoParserBase.RULE_labeledStmt) {
                                                    possibleDeclaration = true;
                                                } else {
                                                    possibleReference = true;
                                                }
                                            }
                                            break;

                                        case GoParserBase.RULE_packageName:
                                            if (isInContext(parser, entry.getValue().getFinalContext(), IntervalSet.of(GoParserBase.RULE_packageClause))) {
                                                possibleDeclaration = true;
                                            } else {
                                                possibleReference = true;
                                            }

                                            break;

                                        case GoParserBase.RULE_baseTypeName:
                                        case GoParserBase.RULE_builtinCall: // only happens for builtin method name
                                        case GoParserBase.RULE_expression:  // only happens for selector
                                        case GoParserBase.RULE_fieldName:
                                        case GoParserBase.RULE_qualifiedIdentifier:
                                            possibleReference = true;
                                            break;

                                        case GoParserBase.RULE_identifierList:
                                        case GoParserBase.RULE_functionDecl:
                                        case GoParserBase.RULE_receiver:
                                        case GoParserBase.RULE_typeSpec:
                                        case GoParserBase.RULE_typeSwitchGuard:
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

                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
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

                                        if (!canContinue && label.contains(GoLexerBase.Continue)) {
                                            canContinue = isInContext(parser, caretReachedException.getFinalContext(), CONTINUE_SCOPES);
                                            if (canContinue) {
                                                canBreak = true;
                                            }
                                        }

                                        if (!canBreak && label.contains(GoLexerBase.Break)) {
                                            canBreak = isInContext(parser, caretReachedException.getFinalContext(), BREAK_SCOPES);
                                        }

                                        if (!canDefaultOrCase && label.contains(GoLexerBase.Default)) {
                                            if (caretReachedException.getFinalContext() instanceof ParserRuleContext<?>) {
                                                int currentRule = ((ParserRuleContext<?>)caretReachedException.getFinalContext()).ruleIndex;
                                                canDefaultOrCase =
                                                    currentRule == GoParserBase.RULE_typeSwitchCase
                                                    || currentRule == GoParserBase.RULE_exprSwitchCase
                                                    || currentRule == GoParserBase.RULE_commCase;
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
                                allowedKeywords.remove(GoLexerBase.Continue);
                            }

                            if (!canBreak) {
                                allowedKeywords.remove(GoLexerBase.Break);
                            }

                            if (!canDefaultOrCase) {
                                allowedKeywords.remove(GoLexerBase.Case);
                                allowedKeywords.remove(GoLexerBase.Default);
                            }

                            for (int keyword : allowedKeywords.toArray()) {
                                KeywordCompletionItem item = new KeywordCompletionItem(GoLexerBase.tokenNames[keyword]);
                                intermediateResults.put(item.getInsertPrefix().toString(), item);
                            }

                            /*
                            * EXPRESSION ANALYSIS
                            */

                            boolean addedPackages = false;
                            boolean addedTypes = false;
                            boolean addedVars = false;
                            boolean addedFunctions = false;

                            for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                                RuleContext finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
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
                                if (!transition.label().contains(GoParserBase.IDENTIFIER)) {
                                    continue;
                                }

                                // if selectorExpressionRoot is not null, then we are on the right hand side of a '.'
                                ParseTree selectorExpressionRoot = null;
                                ParserRuleContext<Token> selectorTarget = null;
                                boolean isTypeSwitchGuard = false;

                                List<Tree> pathToRoot = new ArrayList<Tree>(Trees.getAncestors(finalContext));
                                pathToRoot.add(finalContext);
                                Collections.reverse(pathToRoot);
                                for (Tree tree : pathToRoot) {
                                    if (tree instanceof GoParserBase.selectorExprContext) {
                                        GoParserBase.selectorExprContext context = (GoParserBase.selectorExprContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = (ParseTree)tree;
                                            selectorTarget = context.e;
                                            break;
                                        }
                                    } else if (tree instanceof GoParserBase.typeSwitchGuardContext) {
                                        GoParserBase.typeSwitchGuardContext context = (GoParserBase.typeSwitchGuardContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = (ParseTree)tree;
                                            selectorTarget = context.e;
                                            break;
                                        }
                                    } else if (tree instanceof GoParserBase.typeAssertionExprContext) {
                                        GoParserBase.typeAssertionExprContext context = (GoParserBase.typeAssertionExprContext)tree;
                                        if (context.dot != null && context.lp == null) {
                                            selectorExpressionRoot = (ParseTree)tree;
                                            selectorTarget = context.e;
                                            break;
                                        }
                                    } else if (tree instanceof GoParserBase.methodExprContext) {
                                        GoParserBase.methodExprContext context = (GoParserBase.methodExprContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = (ParseTree)tree;
                                            selectorTarget = context.recvType;
                                            break;
                                        }
                                    } else if (tree instanceof GoParserBase.qualifiedIdentifierContext) {
                                        GoParserBase.qualifiedIdentifierContext context = (GoParserBase.qualifiedIdentifierContext)tree;
                                        if (context.dot != null) {
                                            selectorExpressionRoot = (ParseTree)tree;
                                            selectorTarget = context.pkg;
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
                                        String text = tokens.toString(selectorTarget.start, selectorTarget.stop);
                                        if (text != null && !text.isEmpty()) {
                                            intermediateResults.put(text, new KeywordCompletionItem(text));
                                        }
                                    }

                                    GoAnnotatedParseTree annotatedParseTree = annotatedParseTrees.get(entry.getKey());
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
                                        //    if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
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
                                        //    if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
                                        //        for (FunctionModel method : typeModel.getMethods()) {
                                        //            intermediateResults.put(method.getName(), new FunctionReferenceCompletionItem(method));
                                        //        }
                                        //        continue;
                                        //    }
                                        //
                                        //    // TODO: any other possibilities?
                                        //} else if (model instanceof VarModel) {
                                        //    VarModel varModel = (VarModel)model;
                                        //    if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
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
                                } else if (finalContext instanceof GoParserBase.packageNameContext) {
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
                                    RuleContext compositeLiteralRuleContext = getTopContext(parser, finalContext, IntervalSet.of(GoParserBase.RULE_compositeLiteral));
                                    GoParserBase.compositeLiteralContext compositeLiteralContext = (GoParserBase.compositeLiteralContext)compositeLiteralRuleContext;
                                    if (finalContext instanceof GoParserBase.fieldNameContext) {
                                        assert compositeLiteralContext != null && compositeLiteralContext.litTyp != null;

                                        List<GoParserBase.literalValueContext> literalValueContexts = new ArrayList<GoParserBase.literalValueContext>();
                                        for (RuleContext context = finalContext; context != null; context = context.parent) {
                                            if (context instanceof GoParserBase.literalValueContext) {
                                                literalValueContexts.add((GoParserBase.literalValueContext)context);
                                            } else if (context instanceof GoParserBase.compositeLiteralContext) {
                                                // stop at the containing compositeLiteral
                                                break;
                                            }
                                        }

                                        // first resolve the type of the containing compositeLiteral
                                        Collection<? extends CodeElementModel> models = targetAnalyzer.resolveTarget(compositeLiteralContext.litTyp);
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
                                    } else if (finalContext instanceof GoParserBase.qualifiedIdentifierContext) {
                                        GoParserBase.qualifiedIdentifierContext context = (GoParserBase.qualifiedIdentifierContext)finalContext;
                                        if (context.pkg != null) {
                                            continue;
                                        }

                                        if (compositeLiteralContext != null && compositeLiteralContext.litVal != null) {
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

                                        if (finalContext.getParent() instanceof GoParserBase.typeNameContext) {
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
                                            assert finalContext.getParent() instanceof GoParserBase.operandContext;
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
                                            Map<Token, ParserRuleContext<Token>> vars = Collections.emptyMap();
                                            Map<Token, ParserRuleContext<Token>> constants = Collections.emptyMap();

                                            @SuppressWarnings("unchecked")
                                            ParserRuleContext<Token> functionContext = (ParserRuleContext<Token>)getTopContext(parser, finalContext, new IntervalSet() {{ add(GoParserBase.RULE_functionDecl); add(GoParserBase.RULE_methodDecl); }});
                                            if (functionContext != null) {
                                                vars = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                                                vars.putAll(localsAnalyzer.getReceiverParameters(functionContext));
                                                vars.putAll(localsAnalyzer.getParameters(functionContext));
                                                vars.putAll(localsAnalyzer.getReturnParameters(functionContext));
                                                vars.putAll(localsAnalyzer.getLocals(functionContext));
                                                constants.putAll(localsAnalyzer.getConstants(functionContext));
                                            }

                                            for (Map.Entry<Token, ParserRuleContext<Token>> varEntry : vars.entrySet()) {
                                                String name = varEntry.getKey().getText();
                                                if (intermediateResults.containsKey(name)) {
                                                    continue;
                                                }

                                                Collection<? extends CodeElementModel> varTypes = targetAnalyzer.resolveTarget(varEntry.getValue());
                                                if (varTypes.isEmpty()) {
                                                    varTypes = Collections.singleton(new UnknownTypeModelImpl((FileModelImpl)getFileModel()));
                                                }

                                                for (CodeElementModel varType : varTypes) {
                                                    if (!(varType instanceof TypeModel)) {
                                                        continue;
                                                    }

                                                    // TODO: use the proper var kind
                                                    VarModelImpl varModel = new VarModelImpl(name, VarKind.LOCAL, (TypeModel)varType, (FileModelImpl)getFileModel());
                                                    intermediateResults.put(name, new VarReferenceCompletionItem(varModel, true));
                                                    break;
                                                }
                                            }

                                            LOGGER.log(Level.FINE, "TODO: constants");
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
                                    } else if (finalContext instanceof GoParserBase.baseTypeNameContext) {
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
                                    } else if (finalContext instanceof GoParserBase.labelContext) {
                                        if (finalContext.getParent() instanceof GoParserBase.labeledStmtContext) {
                                            continue;
                                        }

                                        final List<Token> labels = new ArrayList<Token>();
                                        ParseTreeListener<Token> listener = new BlankGoParserBaseListener() {

                                            @Override
                                            public void enterRule(labeledStmtContext ctx) {
                                                if (ctx.lbl != null && ctx.lbl.name != null) {
                                                    labels.add(ctx.lbl.name);
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
                                    } else if (finalContext instanceof GoParserBase.builtinCallContext) {
                                        // this is easy - just add the names of built in methods
                                        for (String builtin : SemanticHighlighter.PREDEFINED_FUNCTIONS) {
                                            if (intermediateResults.containsKey(builtin)) {
                                                continue;
                                            }

                                            intermediateResults.put(builtin, new FunctionReferenceCompletionItem(builtin));
                                        }
                                    } else if (finalContext instanceof GoParserBase.methodNameContext) {
                                        // This block only handles non-dotted identifiers, which means this can't be a methodExpr
                                        // and is therefore always a declaration (never a reference).
                                        continue;
                                    } else if (finalContext instanceof GoParserBase.receiverContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParserBase.functionDeclContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParserBase.typeSpecContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParserBase.identifierListContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    } else if (finalContext instanceof GoParserBase.typeSwitchGuardContext) {
                                        // this is a declaration not a reference
                                        continue;
                                    }
                                }
                            }

                            results.addAll(intermediateResults.values());
                        }
                    } finally {
                        parserCache.putParser(parser);
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
        }

        @Override
        protected RuleContext parseImpl(CodeCompletionParser parser) {
            return ((CodeCompletionGoParser)parser).sourceFile();
        }

        private FileModel getFileModel() {
            if (fileModel == null && !fileModelDataFailed) {
                Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
                try {
                    fileModel = futureFileModelData.get().getData();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                    fileModelDataFailed = true;
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                    fileModelDataFailed = true;
                }
            }

            return fileModel;
        }

        private ObjectDecorator<Tree> annotations = new ObjectDecorator<Tree>();

        private class LocalsAnalyzer {

//            private final List<Token> labels = new ArrayList<Token>();

            public Map<Token, ParserRuleContext<Token>> getLocals(@NonNull ParserRuleContext<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_LOCALS);
            }

            public Map<Token, ParserRuleContext<Token>> getConstants(@NonNull ParserRuleContext<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_CONSTANTS);
            }

            public Map<Token, ParserRuleContext<Token>> getReceiverParameters(@NonNull ParserRuleContext<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RECEIVER_PARAMETER);
            }

            public Map<Token, ParserRuleContext<Token>> getParameters(@NonNull ParserRuleContext<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_PARAMETER);
            }

            public Map<Token, ParserRuleContext<Token>> getReturnParameters(@NonNull ParserRuleContext<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RETURN_PARAMETER);
            }

            private Map<Token, ParserRuleContext<Token>> getLocals(ParserRuleContext<Token> context, ObjectProperty<Map<Token, ParserRuleContext<Token>>> property) {
                Map<Token, ParserRuleContext<Token>> result = getLocalsProperty(context, property);
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

                return result != null ? result : Collections.<Token, ParserRuleContext<Token>>emptyMap();
            }

//            public List<Token> getLabels() {
//                return labels;
//            }

            @SuppressWarnings("unchecked")
            private Map<Token, ParserRuleContext<Token>> getLocalsProperty(ParserRuleContext<Token> context, ObjectProperty<Map<Token, ParserRuleContext<Token>>> property) {
                Map<Token, ParserRuleContext<Token>> result = annotations.getProperty(context, property);
                if (result != null) {
                    return result;
                }

                return null;
            }

            private void setLocalsProperty(ParserRuleContext<?> context, ObjectProperty<Map<Token, ParserRuleContext<Token>>> property, @NonNull Map<Token, ParserRuleContext<Token>> locals) {
                Parameters.notNull("locals", locals);
                annotations.putProperty(context, property, locals);
            }

            private class Listener extends BlankGoParserBaseListener {
                private final Map<Token, ParserRuleContext<Token>> locals = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                private final Map<Token, ParserRuleContext<Token>> receiverParameters = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                private final Map<Token, ParserRuleContext<Token>> parameters = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                private final Map<Token, ParserRuleContext<Token>> returnParameters = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                private final Map<Token, ParserRuleContext<Token>> constants = new IdentityHashMap<Token, ParserRuleContext<Token>>();

                public Map<Token, ParserRuleContext<Token>> getLocals() {
                    return locals;
                }

                public Map<Token, ParserRuleContext<Token>> getConstants() {
                    return constants;
                }

                public Map<Token, ParserRuleContext<Token>> getReceiverParameters() {
                    return receiverParameters;
                }

                public Map<Token, ParserRuleContext<Token>> getParameters() {
                    return parameters;
                }

                public Map<Token, ParserRuleContext<Token>> getReturnParameters() {
                    return returnParameters;
                }

                @Override
                public void enterRule(varSpecContext ctx) {
                    addVars(locals, ctx.idList, ctx.varType, ctx.exprList);
                }

                @Override
                public void enterRule(shortVarDeclContext ctx) {
                    addVars(locals, ctx.idList, null, ctx.exprList);
                }

                @Override
                public void enterRule(rangeClauseContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.put(ctx.e1.start, null);
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.put(ctx.e2.start, null);
                        }
                    }
                }

                @Override
                public void enterRule(typeSwitchGuardContext ctx) {
                    if (ctx.id != null) {
                        locals.put(ctx.id, null);
                    }
                }

                @Override
                public void enterRule(recvStmtContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.put(ctx.e1.start, null);
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.put(ctx.e2.start, null);
                        }
                    }
                }

                @Override
                public void enterRule(receiverContext ctx) {
                    if (ctx.name != null) {
                        receiverParameters.put(ctx.name, ctx.typ);
                    }
                }

                @Override
                public void enterRule(parameterDeclContext ctx) {
                    if (ctx.idList != null) {
                        GoParserBase.parametersContext parametersContext = (GoParserBase.parametersContext)getTopContext(parser, ctx, IntervalSet.of(GoParserBase.RULE_parameters));
                        Map<Token, ParserRuleContext<Token>> map = parametersContext.parent instanceof GoParserBase.resultContext ? returnParameters : parameters;
                        addVars(map, ctx.idList, ctx.t, null);
                    }
                }

                @Override
                public void enterRule(constSpecContext ctx) {
                    addVars(constants, ctx.idList, ctx.explicitType, ctx.valueList);
                }

                private void addVars(@NonNull Map<Token, ParserRuleContext<Token>> map,
                                     @NullAllowed GoParserBase.identifierListContext idList,
                                     @NullAllowed GoParserBase.typeContext explicitType,
                                     @NullAllowed GoParserBase.expressionListContext exprList) {

                    if (idList == null || idList.ids_list == null) {
                        return;
                    }

                    List<GoParserBase.expressionContext> expressions = exprList != null ? exprList.expressions_list : null;
                    for (int i = 0; i < idList.ids_list.size(); i++) {
                        Token name = idList.ids_list.get(i);
                        ParserRuleContext<Token> type = explicitType;
                        if (type == null && expressions != null && i < expressions.size()) {
                            type = expressions.get(i);
                        }

                        map.put(name, type);
                    }
                }
            }
        }

        private class TargetAnalyzer extends BlankGoParserBaseListener {

            @NonNull
            public Collection<? extends CodeElementModel> resolveTarget(@NullAllowed ParserRuleContext<Token> context) {
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
            public void enterRule(packageNameContext ctx) {
                FileModel fileModel = getFileModel();
                if (ctx.name == null || fileModel == null) {
                    return;
                }

                if (ctx.getParent() instanceof GoParserBase.packageClauseContext) {
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
            public void enterRule(expressionContext ctx) {
                if (ctx.getChildCount() == 1 && (ctx.getChild(0) instanceof GoParserBase.operandContext)) {
                    annotations.putProperty(ctx, ATTR_TARGET, resolveTarget((GoParserBase.operandContext)ctx.getChild(0)));
                    return;
                }

                LOGGER.log(Level.FINE, "TODO: handle other expressions.");
            }

            @Override
            public void enterRule(selectorExprContext ctx) {
                if (ctx.name == null) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                String name = ctx.name.getText();
                if (name == null || name.isEmpty()) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                Collection<? extends CodeElementModel> targets = resolveTarget(ctx.e);
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
            public void enterRule(typeContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.name != null) {
                    result = resolveTarget(ctx.name);
                } else if (ctx.lit != null) {
                    result = resolveTarget(ctx.lit);
                } else if (ctx.t != null) {
                    result = resolveTarget(ctx.t);
                } else {
                    LOGGER.log(Level.FINE, "Unknown type syntax.");
                    result = null;
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(typeLiteralContext ctx) {
                if (ctx.getChildCount() != 1) {
                    LOGGER.log(Level.FINE, "Unknown typeLiteral syntax.");
                    return;
                }

                @SuppressWarnings("unchecked")
                Collection<? extends CodeElementModel> result = resolveTarget((ParserRuleContext<Token>)ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(arrayTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elemType != null) {
                    result.addAll(resolveTarget(ctx.elemType));
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
            public void enterRule(structTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            public void enterRule(pointerTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.typ != null) {
                    result.addAll(resolveTarget(ctx.typ));
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
            public void enterRule(baseTypeContext ctx) {
                if (ctx.getChildCount() != 1) {
                    LOGGER.log(Level.FINE, "Unknown baseType syntax.");
                    return;
                }

                @SuppressWarnings("unchecked")
                Collection<? extends CodeElementModel> result = resolveTarget(ctx.typ);
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(functionTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            public void enterRule(interfaceTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            public void enterRule(sliceTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elemType != null) {
                    result.addAll(resolveTarget(ctx.elemType));
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
            public void enterRule(mapTypeContext ctx) {
                LOGGER.log(Level.FINE, "Target resolution for context {0} is not implemented.", ctx.getClass());
            }

            @Override
            public void enterRule(channelTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elemType != null) {
                    result.addAll(resolveTarget(ctx.elemType));
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
            public void enterRule(literalTypeContext ctx) {
                if (ctx.getChildCount() != 1) {
                    LOGGER.log(Level.FINE, "TODO: resolve implicit array creation.");
                    return;
                }

                @SuppressWarnings("unchecked")
                Collection<? extends CodeElementModel> result = resolveTarget((ParserRuleContext<Token>)ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(typeNameContext ctx) {
                if (ctx.qid == null) {
                    LOGGER.log(Level.FINE, "Unknown typeName syntax.");
                    return;
                }

                @SuppressWarnings("unchecked")
                Collection<? extends CodeElementModel> result = resolveTarget((ParserRuleContext<Token>)ctx.getChild(0));
                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(baseTypeNameContext ctx) {
                // must be a type in the current package
                PackageModel currentPackage = getFileModel().getPackage();
                Collection<? extends TypeModel> types = currentPackage.getTypes(ctx.name.getText());

                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                receiverContext receiverContext = (receiverContext)ctx.parent;
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
            public void enterRule(operandContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.lit != null) {
                    result = resolveTarget(ctx.lit);
                } else if (ctx.qid != null) {
                    result = resolveTarget(ctx.qid);
                } else if (ctx.me != null) {
                    result = resolveTarget(ctx.me);
                } else if (ctx.e != null) {
                    result = resolveTarget(ctx.e);
                } else {
                    LOGGER.log(Level.FINE, "TODO: unknown typeName syntax.");
                    result = null;
                }

                annotations.putProperty(ctx, ATTR_TARGET, result);
            }

            @Override
            public void enterRule(callExprContext ctx) {
                if (ctx.e == null) {
                    return;
                }

                Collection<? extends CodeElementModel> methodResults = resolveTarget(ctx.e);
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
            public void enterRule(qualifiedIdentifierContext ctx) {
                Map<Token, ParserRuleContext<Token>> vars = Collections.emptyMap();
                List<CodeElementModel> contextModels = new ArrayList<CodeElementModel>();
                List<ImportDeclarationModel> possibleImports = new ArrayList<ImportDeclarationModel>();
                if (ctx.pkg == null) {
                    contextModels.add(getFileModel().getPackage());
                    for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                        if (importDeclarationModel.isMergeWithLocal()) {
                            possibleImports.add(importDeclarationModel);
                        }
                    }

                    @SuppressWarnings("unchecked")
                    ParserRuleContext<Token> functionContext = (ParserRuleContext<Token>)getTopContext(parser, ctx, new IntervalSet() {{ add(GoParserBase.RULE_functionDecl); add(GoParserBase.RULE_methodDecl); }});
                    if (functionContext != null) {
                        vars = new IdentityHashMap<Token, ParserRuleContext<Token>>();
                        vars.putAll(localsAnalyzer.getReceiverParameters(functionContext));
                        vars.putAll(localsAnalyzer.getParameters(functionContext));
                        vars.putAll(localsAnalyzer.getReturnParameters(functionContext));
                        vars.putAll(localsAnalyzer.getConstants(functionContext));
                        vars.putAll(localsAnalyzer.getLocals(functionContext));
                    }
                } else {
                    String pkgName = ctx.pkg.name.getText();
                    for (ImportDeclarationModel importDeclarationModel : getFileModel().getImportDeclarations()) {
                        if (!importDeclarationModel.isMergeWithLocal() && pkgName.equals(importDeclarationModel.getName())) {
                            possibleImports.add(importDeclarationModel);
                        }
                    }
                }

                for (ImportDeclarationModel importDeclarationModel : possibleImports) {
                    Collection<? extends PackageModel> resolved = CodeModelCacheImpl.getInstance().resolvePackages(importDeclarationModel);
                    if (resolved != null) {
                        contextModels.addAll(resolved);
                    }
                }

                String name = ctx.id.getText();
                Set<CodeElementModel> members = new HashSet<CodeElementModel>();
                for (CodeElementModel model : contextModels) {
                    members.addAll(model.getMembers(name));
                }

                for (Map.Entry<Token, ParserRuleContext<Token>> entry : vars.entrySet()) {
                    if (!name.equals(entry.getKey().getText())) {
                        continue;
                    }

                    Collection<? extends CodeElementModel> varTypes = resolveTarget(entry.getValue());
                    for (CodeElementModel varType : varTypes) {
                        if (!(varType instanceof TypeModel)) {
                            continue;
                        }

                        // TODO: use proper var kind
                        VarModelImpl varModel = new VarModelImpl(name, VarKind.LOCAL, (TypeModel)varType, (FileModelImpl)getFileModel());
                        members.add(varModel);
                    }
                }

                annotations.putProperty(ctx, ATTR_TARGET, members);
            }

            @SuppressWarnings("unchecked")
            private Collection<? extends CodeElementModel> getTargetProperty(ParserRuleContext<Token> context) {
                Object result = annotations.getProperty(context, ATTR_TARGET);
                if (result instanceof Collection<?>) {
                    return (Collection<? extends CodeElementModel>)result;
                }

                return null;
            }

            private void setTargetProperty(ParserRuleContext<?> context, @NonNull Collection<? extends CodeElementModel> models) {
                Parameters.notNull("models", models);

                annotations.putProperty(context, ATTR_TARGET, models);
            }
        }

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
                if (qualifierCodeClass != null) {
                    resolvedQualifier = qualifierCodeClass.resolve(annotatedParseTree, currentPackage, resolvedPackages);
                }
            }

            if (resolvedQualifier == null) {
                CodeElementReference qualifierExprType = treeDecorator.getProperty(qualifier, GoAnnotations.EXPR_TYPE);
                if (qualifierExprType != null) {
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
                        LOGGER.log(Level.WARNING, "Unable to resolve unqualified link from qualifier: {0}", qualifier);
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
                    assert qualifier instanceof packageNameContext;
                    String packageName = ((packageNameContext)qualifier).name.getText();
                    resolvedQualifier = resolvedPackages.get(packageName);
                    if (resolvedQualifier == null) {
                        resolvedQualifier = Collections.emptyList();
                    }
                } else if (qualifierNodeType == NodeType.VAR_REF) {
                    // must be referring to something within the current file since it's resolved internally
                    Token target = treeDecorator.getProperty(qualifier, GoAnnotations.LOCAL_TARGET);
                    assert tokenDecorator.getProperty(target, GoAnnotations.NODE_TYPE) == NodeType.VAR_DECL;
                    ParserRuleContext<Token> explicitType = tokenDecorator.getProperty(target, GoAnnotations.EXPLICIT_TYPE);
                    if (explicitType != null) {
                        LOGGER.log(Level.WARNING, "Unable to resolve explicit type for qualifier: {0}", qualifier);
                        resolvedQualifier = Collections.emptyList();
                    } else {
                        ParserRuleContext<Token> implicitType = tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_TYPE);
                        int implicitIndex = tokenDecorator.getProperty(target, GoAnnotations.IMPLICIT_INDEX);
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

        private Map<RuleContext, GoAnnotatedParseTree> analyzeParseTrees(VersionedDocument document, Map<? extends RuleContext, ? extends CaretReachedException> parseTrees) {
            Map<RuleContext, GoAnnotatedParseTree> result = new IdentityHashMap<RuleContext, GoAnnotatedParseTree>();
            for (Map.Entry<? extends RuleContext, ? extends CaretReachedException> entry : parseTrees.entrySet()) {
                @SuppressWarnings("unchecked")
                ParserRuleContext<Token> context = (ParserRuleContext<Token>)entry.getKey();
                GoAnnotatedParseTree annotatedTree = SemanticAnalyzer.analyze(document, context);
                result.put(context, annotatedTree);
            }

            return result;
        }
    }

    private static class SingleElementParseTreeWalker extends ParseTreeWalker {
        public static final SingleElementParseTreeWalker SINGLE_ELEMENT_WALKER = new SingleElementParseTreeWalker();

        @Override
        public <Symbol> void walk(@NonNull ParseTreeListener<Symbol> listener, @NonNull ParseTree t) {
            Parameters.notNull("listener", listener);
            Parameters.notNull("t", t);

            if (t instanceof ParseTree.RuleNode) {
                ParseTree.RuleNode r = (ParseTree.RuleNode)t;
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
            super(name, fileModel);
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
