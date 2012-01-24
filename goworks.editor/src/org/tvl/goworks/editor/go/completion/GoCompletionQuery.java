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
import org.antlr.works.editor.antlr4.completion.AbstractCompletionQuery;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.shared.TaggerTokenSource;
import org.antlr.works.editor.shared.completion.Anchor;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
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
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VarModelImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoLexerBase;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectorExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;
import org.tvl.goworks.editor.go.parser.ParseTreeAnnotations;

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
                        region = OffsetRegion.fromBounds(previous.getSpan().getEndPosition(snapshot).getOffset(), regionEnd);
                    }

                    TaggerTokenSource taggerTokenSource = new TaggerTokenSource(tagger, new SnapshotPositionRegion(snapshot, region));
                    TokenSource tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                    CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                    parser = parserCache.getParser(tokens);
                    try {
                        parser.setBuildParseTree(true);
                        parser.setErrorHandler(new CodeCompletionErrorStrategy());
                        parser.setCheckPackageNames(false);

                        switch (previous.getRule()) {
                        case GoParserBase.RULE_topLevelDecl:
                            parseTrees = getParseTrees(parser);
                            break;

                        default:
                            parseTrees = null;
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
                                                int invokingState = entry.getValue().getFinalContext().invokingState;
                                                int invokingRule = invokingState > 0 ? parser.getATN().states.get(invokingState).ruleIndex : -1;
                                                if (invokingRule == GoParserBase.RULE_methodSpec || invokingRule == GoParserBase.RULE_methodDecl) {
                                                    possibleDeclaration = true;
                                                } else {
                                                    possibleReference = true;
                                                }
                                            }
                                            break;

                                        case GoParserBase.RULE_label:
                                            {
                                                int invokingState = entry.getValue().getFinalContext().invokingState;
                                                if (invokingState > 0 && parser.getATN().states.get(invokingState).ruleIndex == GoParserBase.RULE_labeledStmt) {
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

                                    String text = tokens.toString(selectorTarget.start, selectorTarget.stop);
                                    if (text == null || text.isEmpty()) {
                                        continue;
                                    }

                                    Collection<? extends CodeElementModel> models = targetAnalyzer.resolveTarget(selectorTarget);
                                    assert models != null;

                                    for (CodeElementModel model : models) {
                                        if (model instanceof PackageModel) {
                                            PackageModel packageModel = (PackageModel)model;
                                            if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
                                                continue;
                                            }

                                            for (FunctionModel function : packageModel.getFunctions()) {
                                                intermediateResults.put(function.getName(), new FunctionReferenceCompletionItem(function));
                                            }

                                            for (ConstModel constant : packageModel.getConstants()) {
                                                intermediateResults.put(constant.getName(), new ConstReferenceCompletionItem(constant));
                                            }

                                            for (VarModel var : packageModel.getVars()) {
                                                intermediateResults.put(var.getName(), new VarReferenceCompletionItem(var));
                                            }

                                            for (TypeModel type : packageModel.getTypes()) {
                                                intermediateResults.put(type.getName(), new TypeReferenceCompletionItem(type));
                                            }
                                        } else if (model instanceof TypeModel) {
                                            TypeModel typeModel = (TypeModel)model;
                                            if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
                                                for (FunctionModel method : typeModel.getMethods()) {
                                                    intermediateResults.put(method.getName(), new FunctionReferenceCompletionItem(method));
                                                }
                                                continue;
                                            }

                                            // TODO: any other possibilities?
                                        } else if (model instanceof VarModel) {
                                            VarModel varModel = (VarModel)model;
                                            if (selectorExpressionRoot instanceof GoParserBase.methodExprContext) {
                                                continue;
                                            }

                                            TypeModel varType = varModel.getVarType();
                                            for (FunctionModel method : varType.getMethods()) {
                                                intermediateResults.put(method.getName(), new FunctionReferenceCompletionItem(method));
                                            }

                                            for (VarModel var : varType.getFields()) {
                                                intermediateResults.put(var.getName(), new VarReferenceCompletionItem(var));
                                            }
                                        } else {
                                            LOGGER.log(Level.FINE, "TODO: Unknown model '{0}'.", model.getClass().getSimpleName());
                                        }
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

                                                intermediateResults.put(builtin, new ConstReferenceCompletionItem(builtin));
                                            }

                                            for (PackageModel packageModel : visiblePackages) {
                                                Collection<? extends ConstModel> constants = packageModel.getConstants();
                                                for (ConstModel model : constants) {
                                                    if (intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new ConstReferenceCompletionItem(model));
                                                }

                                                Collection<? extends VarModel> vars = packageModel.getVars();
                                                for (VarModel model : vars) {
                                                    if (intermediateResults.containsKey(model.getName())) {
                                                        continue;
                                                    }

                                                    intermediateResults.put(model.getName(), new VarReferenceCompletionItem(model));
                                                }

                                                Collection<? extends FunctionModel> functions = packageModel.getFunctions();
                                                for (FunctionModel model : functions) {
                                                    if (intermediateResults.containsKey(model.getName())) {
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

                                                    VarModelImpl varModel = new VarModelImpl(name, (TypeModel)varType, (FileModelImpl)getFileModel());
                                                    intermediateResults.put(name, new VarReferenceCompletionItem(varModel));
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
            return ((CodeCompletionGoParser)parser).topLevelDecl();
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

        private ParseTreeAnnotations annotations = new ParseTreeAnnotations();

        private class LocalsAnalyzer {
            private static final String ATTR_CONSTANTS = "constants";
            private static final String ATTR_LOCALS = "locals";
            private static final String ATTR_PARAMETER = "parameter";
            private static final String ATTR_RECEIVER_PARAMETER = "receiver-parameter";
            private static final String ATTR_RETURN_PARAMETER = "return-parameter";

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

            private Map<Token, ParserRuleContext<Token>> getLocals(ParserRuleContext<Token> context, String attr) {
                Map<Token, ParserRuleContext<Token>> result = getLocalsProperty(context, attr);
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

                result = getLocalsProperty(context, attr);
                if (result == null) {
                    LOGGER.log(Level.FINE, "TODO: resolve locals");
                }

                return result != null ? result : Collections.<Token, ParserRuleContext<Token>>emptyMap();
            }

//            public List<Token> getLabels() {
//                return labels;
//            }

            @SuppressWarnings("unchecked")
            private Map<Token, ParserRuleContext<Token>> getLocalsProperty(ParserRuleContext<Token> context, String attr) {
                Object result = annotations.getProperty(context, attr);
                if (result instanceof Map<?,?>) {
                    return (Map<Token, ParserRuleContext<Token>>)result;
                }

                return null;
            }

            private void setLocalsProperty(ParserRuleContext<?> context, String attr, @NonNull Map<Token, ParserRuleContext<Token>> locals) {
                Parameters.notNull("locals", locals);
                annotations.putProperty(context, attr, locals);
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
            private static final String ATTR_TARGET = "target";

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
                            resolved.addAll(cache.getPackages(null, model.getPath()));
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
                    LOGGER.log(Level.FINE, "TODO: unknown typeName syntax.");
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

                        TypePointerModel ptr = new TypePointerModelImpl((TypeModelImpl)model, (FileModelImpl)getFileModel());
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

                        VarModelImpl varModel = new VarModelImpl(name, (TypeModel)varType, (FileModelImpl)getFileModel());
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
        public TypeKind getKind() {
            return TypeKind.UNKNOWN;
        }

        @Override
        public Collection<? extends AbstractCodeElementModel> getMembers() {
            return Collections.emptyList();
        }

    }
}
