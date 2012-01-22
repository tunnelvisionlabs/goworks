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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.navigation.Description;
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
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.NotSetTransition;
import org.antlr.v4.runtime.atn.PredictionContext;
import org.antlr.v4.runtime.atn.StarLoopbackState;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.atn.WildcardTransition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;
import org.antlr.works.editor.shared.TaggerTokenSource;
import org.antlr.works.editor.shared.completion.Anchor;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.completion.CompletionDocumentation;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.GoLexerBase;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectorExprContext;
import org.tvl.goworks.editor.go.parser.ParseTreeAnnotations;

/**
 *
 * @author Sam Harwell
 */
public final class GoCompletionQuery extends AsyncCompletionQuery {
    // -J-Dorg.tvl.goworks.editor.go.completion.GoCompletionQuery.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoCompletionQuery.class.getName());

    private static final int NO_ADDITIONAL_ITEMS = 0;
    private static final int ADDITIONAL_IMPORTED_ITEMS = 1;
    private static final int ADDITIONAL_MEMBER_ITEMS = 2;

    /** ^[\\$A-Za-z_][A-Za-z0-9_]*$ */
    /*package*/ static final Pattern WORD_PATTERN = Pattern.compile("^[\\$A-Za-z_][A-Za-z0-9_]*$");

    private static final String EMPTY = "";

    private final int queryType;
    private final boolean hasTask;
    private final boolean extend;
    private int caretOffset;

    private JTextComponent component;
    private CompletionToolTip toolTip;

    private List<CompletionItem> results;
    private boolean possibleDeclaration;
    private boolean possibleReference;
    private boolean possibleKeyword;

    private CompletionDocumentation documentation;
    private String filterPrefix;
    private byte hasAdditionalItems;
    private TrackingPositionRegion applicableTo;
    private int toolTipOffset;

    /*package*/ GoCompletionQuery(int queryType, int caretOffset, boolean hasTask, boolean extend) {
        this.queryType = queryType;
        this.caretOffset = caretOffset;
        this.hasTask = hasTask;
        this.extend = extend;
    }

    public TrackingPositionRegion getApplicableTo() {
        return applicableTo;
    }

    public boolean isExtend() {
        return extend;
    }

    public boolean isExplicitQuery() {
        return (queryType & GoCompletionProvider.AUTO_QUERY_TYPE) == 0;
    }

    @Override
    protected void preQueryUpdate(JTextComponent component) {
        if (applicableTo != null) {
            int newCaretOffset = component.getSelectionStart();
            Document document = component.getDocument();
            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
            DocumentSnapshot textSnapshot = textBuffer.getCurrentSnapshot();
            SnapshotPositionRegion span = applicableTo.getRegion(textSnapshot);
            if (span.contains(newCaretOffset)) {
                String text = span.getText();
                if (text.isEmpty() || WORD_PATTERN.matcher(text).matches()) {
                    return;
                }
            }

            Completion.get().hideCompletion();
        }
    }

    @Override
    protected void prepareQuery(JTextComponent component) {
        this.component = component;
        if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
            this.toolTip = new CompletionToolTip(component);
        }
    }

    private ParserTaskManager getParserTaskManager() {
        return Lookup.getDefault().lookup(ParserTaskManager.class);
    }

    @Override
    @NbBundle.Messages({
        "scanning_in_progress=Scanning in progress..."
    })
    protected void query(CompletionResultSet resultSet, Document doc, int caretOffset) {
        try {
            this.caretOffset = caretOffset;
            if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE || GoCompletionProvider.isGoContext(component, caretOffset, true)) {
                results = null;
                documentation = null;
                if (toolTip != null) {
                    toolTip.clearData();
                }

                applicableTo = null;
                if ((queryType & CompletionProvider.DOCUMENTATION_QUERY_TYPE) == CompletionProvider.DOCUMENTATION_QUERY_TYPE) {
                    throw new UnsupportedOperationException("Not implemented yet.");
                }

//                    VersionedDocument buffer = VersionedDocumentUtilities.getVersionedDocument(doc);
//                    if (buffer != null) {
//                        final DocumentSnapshot snapshot = buffer.getCurrentSnapshot();
//                        final ParserDataDefinition<?> definition = null;
//                        final EnumSet<ParserDataOptions> options = EnumSet.noneOf(ParserDataOptions.class);
//                        Callable<Void> task = getTask();
//                        Future<Void> data = getParserTaskManager().scheduleHighPriority(task);
//                    }

                Future<Void> value = getParserTaskManager().scheduleHighPriority(getTask((BaseDocument)doc));
                if (value != null) {
                    if (!value.isDone()) {
                        component.putClientProperty("completion-active", Boolean.FALSE);
                        resultSet.setWaitText(Bundle.scanning_in_progress());
                        value.get();
                    }

                    if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
                        if (results != null) {
                            resultSet.addAllItems(results);
                        }

                        resultSet.setHasAdditionalItems(hasAdditionalItems != NO_ADDITIONAL_ITEMS);
                        if (hasAdditionalItems == ADDITIONAL_IMPORTED_ITEMS) {
                            resultSet.setHasAdditionalItemsText(Bundle.GCP_imported_items());
                        } else if (hasAdditionalItems == ADDITIONAL_MEMBER_ITEMS) {
                            resultSet.setHasAdditionalItemsText(Bundle.GCP_instance_members());
                        }
                    } else if ((queryType & CompletionProvider.TOOLTIP_QUERY_TYPE) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
                        if (toolTip != null && toolTip.hasData()) {
                            resultSet.setToolTip(toolTip);
                        }
                    } else if ((queryType & CompletionProvider.DOCUMENTATION_QUERY_TYPE) == CompletionProvider.DOCUMENTATION_QUERY_TYPE) {
                        throw new UnsupportedOperationException("Not implemented yet.");
                    }

                    if (applicableTo != null) {
                        VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(doc);
                        resultSet.setAnchorOffset(applicableTo.getStartPosition(textBuffer.getCurrentSnapshot()).getOffset());
                    }
                }
            }
        } catch (Exception ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An exception occurred while processing a completion query.", ex);
            }
        } finally {
            resultSet.finish();
        }
    }

    @Override
    protected boolean canFilter(JTextComponent component) {
        filterPrefix = null;
        int newOffset = component.getSelectionStart();
        if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
            if (applicableTo != null) {
                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
                SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
                int caretPosition = component.getCaretPosition();
                // can't use SnapshotPositionRegion.contains because we need to use an inclusive check at the end of the span
                if (applicableSpan.getStart().getOffset() <= caretPosition && applicableSpan.getEnd().getOffset() >= caretPosition) {
                    OffsetRegion filterSpan = OffsetRegion.fromBounds(applicableSpan.getStart().getOffset(), component.getCaretPosition());
                    filterPrefix = snapshot.subSequence(filterSpan.getStart(), filterSpan.getEnd()).toString();
                    if (!filterPrefix.isEmpty() && !WORD_PATTERN.matcher(filterPrefix).matches()) {
                        filterPrefix = null;
                    }
                }

                return true;
            }
        } else if ((queryType & CompletionProvider.RESERVED_QUERY_MASK) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
            try {
                if (newOffset == caretOffset) {
                    filterPrefix = EMPTY;
                } else if (newOffset - caretOffset > 0) {
                    filterPrefix = component.getDocument().getText(caretOffset, newOffset - caretOffset);
                } else if (newOffset - caretOffset < 0) {
                    filterPrefix = newOffset > toolTipOffset ? component.getDocument().getText(newOffset, caretOffset - newOffset) : null;
                }
            } catch (BadLocationException e) {
            }

            return (filterPrefix != null && filterPrefix.indexOf(',') == -1 && filterPrefix.indexOf('(') == -1 && filterPrefix.indexOf(')') == -1);
        }

        return false;
    }

    @Override
    protected void filter(CompletionResultSet resultSet) {
        try {
            if ((queryType & CompletionProvider.COMPLETION_QUERY_TYPE) != 0) {
                if (results != null) {
                    if (filterPrefix != null) {
                        Collection<? extends CompletionItem> filtered = getFilteredData(results, filterPrefix);
                        resultSet.addAllItems(filtered);
                        if (possibleDeclaration && !isExplicitQuery() && getApplicableTo() != null) {
                            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();
                            SnapshotPositionRegion applicableSpan = getApplicableTo().getRegion(snapshot);
                            if (applicableSpan.getLength() > 0) {
                                resultSet.addItem(new DeclarationCompletionItem(component.getDocument(), getApplicableTo()));
                            }
                        }

                        resultSet.setHasAdditionalItems(hasAdditionalItems > 0);
                    } else {
                        Completion.get().hideDocumentation();
                        Completion.get().hideCompletion();
                    }
                }
            } else if ((queryType & CompletionProvider.RESERVED_QUERY_MASK) == CompletionProvider.TOOLTIP_QUERY_TYPE) {
                resultSet.setToolTip(toolTip != null ? toolTip : null);
            }

            if (applicableTo != null) {
                VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(component.getDocument());
                resultSet.setAnchorOffset(applicableTo.getStartPosition(textBuffer.getCurrentSnapshot()).getOffset());
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        resultSet.finish();
    }

    private Task getTask(BaseDocument document) {
        VersionedDocument buffer = VersionedDocumentUtilities.getVersionedDocument(document);
        DocumentSnapshot snapshot = buffer.getCurrentSnapshot();
        return new Task(document, snapshot);
    }

    /*package*/ static boolean isIdentifierPart(String typedText) {
        for (int i = 0; i < typedText.length(); i++) {
            if (!Character.isJavaIdentifierPart(typedText.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private Collection<? extends CompletionItem> getFilteredData(List<CompletionItem> data, String prefix) {
        if (prefix.length() == 0) {
            return data;
        }

        Pattern prefixBoundaryPattern = GoCompletionController.getPrefixBoundaryPattern(prefix, false);
        String lowercasePrefix = prefix.toLowerCase(Locale.getDefault());
        List<CompletionItem> result = new ArrayList<CompletionItem>();
        for (CompletionItem item : data) {
            String insertPrefix = item.getInsertPrefix().toString();
            if (insertPrefix.toLowerCase(Locale.getDefault()).contains(lowercasePrefix)) {
                result.add(item);
            } else if (prefixBoundaryPattern != null && prefixBoundaryPattern.matcher(insertPrefix).matches()) {
                result.add(item);
            }
        }

        return result;
    }

    private class Task implements Callable<Void> {
        private final BaseDocument document;
        private final DocumentSnapshot snapshot;
        private final ParserTaskManager taskManager;

        private FileModel fileModel;
        private boolean fileModelDataFailed = false;
        private TargetAnalyzer targetAnalyzer = new TargetAnalyzer();

        private final IntervalSet BREAK_SCOPES = new IntervalSet() {{
            add(GoParserBase.RULE_forStmt);
            add(GoParserBase.RULE_switchStmt);
            add(GoParserBase.RULE_selectStmt);
        }};

        private final IntervalSet CONTINUE_SCOPES = new IntervalSet() {{
            add(GoParserBase.RULE_forStmt);
        }};

        public Task(BaseDocument document, DocumentSnapshot snapshot) {
            Parameters.notNull("document", document);
            Parameters.notNull("snapshot", snapshot);

            this.document = document;
            this.snapshot = snapshot;
            this.taskManager = getParserTaskManager();

            if (taskManager == null) {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public Void call() {
            try {
                runImpl();
            } catch (RuntimeException ex) {
                Exceptions.printStackTrace(ex);
                throw ex;
            } catch (Error ex) {
                Exceptions.printStackTrace(ex);
                throw ex;
            }

            return null;
        }

        private void runImpl() {
            results = new ArrayList<CompletionItem>();
            possibleDeclaration = true;
            possibleReference = true;
            possibleKeyword = true;

            // Add context items (labels, etc). Use anchor points to optimize information gathering.

//            VersionedDocument textBuffer = VersionedDocumentUtilities.getVersionedDocument(document);
//            DocumentSnapshot snapshot = textBuffer.getCurrentSnapshot();

//            boolean possibleInAction;
//            boolean definiteInAction;
            Map<RuleContext, CaretReachedException> parseTrees = null;
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

                    if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= caretOffset) {
                        previous = anchor;
                        if (anchor.getSpan().getEndPosition(snapshot).getOffset() > caretOffset) {
                            enclosing = anchor;
                        }
                    } else {
                        next = anchor;
                        break;
                    }
                }

                if (previous != null) {
                    Future<ParserData<Tagger<TokenTag>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                    Tagger<TokenTag> tagger = null;
                    try {
                        tagger = futureTokensData.get().getData();
                    } catch (InterruptedException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                    int regionEnd = Math.min(snapshot.length(), caretOffset + 1);
                    OffsetRegion region;
                    if (enclosing != null) {
                        region = OffsetRegion.fromBounds(enclosing.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
                    } else {
                        region = OffsetRegion.fromBounds(previous.getSpan().getEndPosition(snapshot).getOffset(), regionEnd);
                    }

                    TaggerTokenSource taggerTokenSource = new TaggerTokenSource(tagger, new SnapshotPositionRegion(snapshot, region));
                    TokenSource tokenSource = new CodeCompletionTokenSource(caretOffset, taggerTokenSource);
                    CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                    CodeCompletionGoParser parser = new CodeCompletionGoParser(tokens, snapshot);
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

                    //boolean hasActionConfig = false;
                    //boolean hasNonActionConfig = false;
                    //final boolean hasRewriteConfig = false;
                    //boolean hasNonRewriteConfig = false;

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
                            IdentityHashMap<PredictionContext, PredictionContext> visited = new IdentityHashMap<PredictionContext, PredictionContext>();
                            Deque<PredictionContext> workList = new ArrayDeque<PredictionContext>();
                            Deque<Integer> stateWorkList = new ArrayDeque<Integer>();
                            for (ATNConfig c : transitions.keySet()) {
                                //boolean currentActionConfig = false;
                                //final boolean currentRewriteConfig = false;

                                visited.clear();
                                workList.clear();
                                stateWorkList.clear();
                                workList.add(c.context);
                                stateWorkList.add(c.state.stateNumber);
                                while (!workList.isEmpty()) {
                                    PredictionContext context = workList.poll();
                                    int state = stateWorkList.poll();
                                    if (visited.put(context, context) != null) {
                                        continue;
                                    }

                                    for (int i = 0; i < context.invokingStates.length; i++) {
                                        workList.add(context.parents[i]);
                                        stateWorkList.add(context.invokingStates[i]);
                                    }

                                    int ruleIndex = parser.getATN().states.get(state).ruleIndex;
                                    //if (ruleIndex == GrammarParser.RULE_actionBlock) {
                                    //    currentActionConfig = true;
                                    //}
                                    //
                                    //if (currentActionConfig && currentRewriteConfig) {
                                    //    break;
                                    //}
                                }

                                //hasActionConfig |= currentActionConfig;
                                //hasNonActionConfig |= !currentActionConfig;
                                //hasNonRewriteConfig |= !currentRewriteConfig;

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

                                //if (hasActionConfig && hasNonActionConfig && hasRewriteConfig && hasNonRewriteConfig) {
                                //    break declarationOrReferenceLoop;
                                //}
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
                        IntervalSet remainingKeywords = new IntervalSet();
                        for (int type : KeywordCompletionItem.KEYWORD_TYPES) {
                            remainingKeywords.add(type);
                        }

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

                                    //ActionExpressionAnalyzer expressionAnalyzer = new ActionExpressionAnalyzer(fileModel, finalContext);
                                    //ParseTreeWalker.DEFAULT.walk(expressionAnalyzer, expressionRoot);
                                    //for (AttributeModel member : expressionAnalyzer.getMembers()) {
                                    //    CompletionItem item = new MemberCompletionItem(member);
                                    //    intermediateResults.put(item.getInsertPrefix().toString(), item);
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
                                boolean inCompositeLiteral = isInContext(parser, finalContext, IntervalSet.of(GoParserBase.RULE_compositeLiteral));
                                if (finalContext instanceof GoParserBase.fieldNameContext) {
                                    assert inCompositeLiteral;
                                    // first resolve the type of the containing compositeLiteral
                                    // then, if the literalValue is nested, resolve the type of the innermost literalValue
                                    LOGGER.log(Level.FINE, "TODO: Resolve type of compositeLiteral");
                                } else if (finalContext instanceof GoParserBase.qualifiedIdentifierContext) {
                                    GoParserBase.qualifiedIdentifierContext context = (GoParserBase.qualifiedIdentifierContext)finalContext;
                                    if (context.pkg != null) {
                                        continue;
                                    }

                                    if (inCompositeLiteral) {
                                        LOGGER.log(Level.FINE, "TODO: resolve enclosing compositeLiteral for further analysis");
                                    } else {
                                        if (finalContext.getParent() instanceof GoParserBase.typeNameContext) {
                                            LOGGER.log(Level.FINE, "TODO: resolve visible type");
                                            continue;
                                        }

                                        assert finalContext.getParent() instanceof GoParserBase.operandContext;
                                        LOGGER.log(Level.FINE, "TODO: resolve unqualified const, var, or func name");
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
                                } else if (finalContext instanceof GoParserBase.builtinCallContext) {
                                    // this is easy - just add the names of built in methods
                                    for (String builtin : SemanticHighlighter.PREDEFINED_FUNCTIONS) {
                                        if (intermediateResults.containsKey(builtin)) {
                                            continue;
                                        }

                                        intermediateResults.put(builtin, new FunctionReferenceCompletionItem(builtin));
                                    }
                                } else if (finalContext instanceof GoParserBase.methodNameContext) {
                                    // this is a declaration not a reference
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

                        /*
                         * GLOBAL SCOPE ANALYSIS
                         */
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
                        }

                        /*
                         * LABEL ANALYSIS
                         */
                        for (Map.Entry<RuleContext, CaretReachedException> entry : parseTrees.entrySet()) {
                            ParseTree parseTree = entry.getKey();
                            RuleContext finalContext = entry.getValue() != null ? entry.getValue().getFinalContext() : null;
                            if (true) {
                                continue;
                            }

                            GoCompletionProvider.incompleteCompletionSupport();
                            //LabelAnalyzer labelAnalyzer = new LabelAnalyzer(finalContext);
                            //ParseTreeWalker.DEFAULT.walk(labelAnalyzer, parseTree);
                            //
                            //possibleInAction = labelAnalyzer.isInAction() || hasActionConfig;
                            //definiteInAction = labelAnalyzer.isInAction() || (hasActionConfig && !hasNonActionConfig);
                            //possibleKeyword |= !definiteInAction;
                            //possibleDeclaration &= !definiteInAction;
                            //possibleReference &= !definiteInAction;
                            //
                            //if (grammarType == GrammarParser.COMBINED) {
                            //    Token enclosingRule = labelAnalyzer.getEnclosingRuleName();
                            //    if (enclosingRule != null) {
                            //        if (enclosingRule.getType() == GrammarParser.RULE_REF) {
                            //            grammarType = GrammarParser.PARSER;
                            //        } else {
                            //            grammarType = GrammarParser.LEXER;
                            //        }
                            //    }
                            //}
                            //
                            //if (!inExpression && possibleInAction) {
                            //    if (!definiteInAction && labelAnalyzer.getEnclosingRuleName() != null) {
                            //        CompletionItem item = new EnclosingRuleCompletionItem(labelAnalyzer.getEnclosingRuleName().getText());
                            //        intermediateResults.put(item.getInsertPrefix().toString(), item);
                            //    }
                            //
                            //    for (Token label : labelAnalyzer.getLabels()) {
                            //        CompletionItem item = new RewriteReferenceCompletionItem(label.getText(), true);
                            //        intermediateResults.put(item.getInsertPrefix().toString(), item);
                            //    }
                            //
                            //    if (possibleInAction && !inExpression) {
                            //        for (Token implicit : labelAnalyzer.getUnlabeledElements()) {
                            //            // only add implicit tokens here. all implicit rule references will be added separately
                            //            if (Character.isUpperCase(implicit.getText().charAt(0))) {
                            //                CompletionItem item = new ActionReferenceCompletionItem(implicit.getText(), false);
                            //                intermediateResults.put(item.getInsertPrefix().toString(), item);
                            //            }
                            //        }
                            //
                            //        if (grammarType != GrammarParser.LEXER) {
                            //            // Add rules from the grammar
                            //            if (rules == null) {
                            //                rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot);
                            //            }
                            //
                            //            for (Description rule : rules) {
                            //                if (Character.isLowerCase(rule.getName().charAt(0))) {
                            //                    results.add(new ActionReferenceCompletionItem(rule.getName(), false));
                            //                }
                            //            }
                            //        }
                            //
                            //        switch (grammarType) {
                            //        case GrammarParser.LEXER:
                            //            intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                            //            intermediateResults.put("$type", new KeywordCompletionItem("$type"));
                            //            intermediateResults.put("$line", new KeywordCompletionItem("$line"));
                            //            intermediateResults.put("$index", new KeywordCompletionItem("$index"));
                            //            intermediateResults.put("$pos", new KeywordCompletionItem("$pos"));
                            //            intermediateResults.put("$channel", new KeywordCompletionItem("$channel"));
                            //            intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                            //            intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                            //            intermediateResults.put("$int", new KeywordCompletionItem("$int"));
                            //            break;
                            //
                            //        case GrammarParser.PARSER:
                            //            intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                            //            intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                            //            intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                            //            intermediateResults.put("$ctx", new KeywordCompletionItem("$ctx"));
                            //            break;
                            //
                            //        default:
                            //            // if we're unsure about the type, include all possibilities to make sure we're covered
                            //            intermediateResults.put("$text", new KeywordCompletionItem("$text"));
                            //            intermediateResults.put("$type", new KeywordCompletionItem("$type"));
                            //            intermediateResults.put("$line", new KeywordCompletionItem("$line"));
                            //            intermediateResults.put("$index", new KeywordCompletionItem("$index"));
                            //            intermediateResults.put("$pos", new KeywordCompletionItem("$pos"));
                            //            intermediateResults.put("$channel", new KeywordCompletionItem("$channel"));
                            //            intermediateResults.put("$start", new KeywordCompletionItem("$start"));
                            //            intermediateResults.put("$stop", new KeywordCompletionItem("$stop"));
                            //            intermediateResults.put("$int", new KeywordCompletionItem("$int"));
                            //            intermediateResults.put("$ctx", new KeywordCompletionItem("$ctx"));
                            //            break;
                            //        }
                            //    }
                            //}
                        }

                        results.addAll(intermediateResults.values());
                    }
                }
            }

            if (parseTrees == null && possibleKeyword) {
                // Add keywords
                results.addAll(KeywordCompletionItem.KEYWORD_ITEMS);
            }

            if (possibleReference) {
//                GoCompletionProvider.incompleteCompletionSupport();
                //boolean tokenReferencesOnly = grammarType == GrammarParser.LEXER;
                //
                //// Add rules from the grammar
                //if (rules == null) {
                //    rules = GrammarCompletionProvider.getRulesFromGrammar(taskManager, snapshot);
                //}
                //
                //for (Description rule : rules) {
                //    if (!tokenReferencesOnly || Character.isUpperCase(rule.getName().charAt(0))) {
                //        results.add(new GrammarRuleCompletionItem(rule));
                //    }
                //}
            }

            OffsetRegion applicableToSpan;
            if (caretToken != null && caretToken.getOriginalToken() != null && caretToken.getOriginalToken().getChannel() == Token.DEFAULT_CHANNEL) {
                applicableToSpan = OffsetRegion.fromBounds(caretToken.getStartIndex(), caretToken.getStopIndex() + 1);
            } else {
                SnapshotPositionRegion identifier = DocumentTextUtilities.getIdentifierBlock(new SnapshotPosition(snapshot, caretOffset));
                if (identifier != null) {
                    applicableToSpan = identifier.getRegion();
                } else {
                    applicableToSpan = OffsetRegion.fromBounds(caretOffset, caretOffset);
                }
            }

            if (!isExtend() && applicableToSpan.contains(caretOffset)) {
                applicableToSpan = OffsetRegion.fromBounds(applicableToSpan.getStart(), caretOffset);
            }

            if (!applicableToSpan.isEmpty()) {
                // make sure this is a word
                String applicableText = snapshot.subSequence(applicableToSpan.getStart(), applicableToSpan.getEnd()).toString();
                if (!WORD_PATTERN.matcher(applicableText).matches()) {
                    applicableToSpan = OffsetRegion.fromBounds(caretOffset, caretOffset);
                }
            }

            applicableTo = snapshot.createTrackingRegion(applicableToSpan, TrackingPositionRegion.Bias.Inclusive);
        }

        private FileModel getFileModel() {
            if (fileModel == null && !fileModelDataFailed) {
                Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
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

        private boolean isInContext(Parser parser, RuleContext context, IntervalSet values) {
            return isInContext(parser, context, values, true);
        }

        private boolean isInContext(Parser parser, RuleContext context, IntervalSet values, boolean checkTop) {
            if (context instanceof ParserRuleContext<?>) {
                if (values.contains(((ParserRuleContext<?>)context).ruleIndex)) {
                    return true;
                }
            }

            if (context.isEmpty()) {
                return false;
            }

            if (values.contains(parser.getATN().states.get(context.invokingState).ruleIndex)) {
                return true;
            }

            return isInContext(parser, context.parent, values, false);
        }

        private Map<RuleContext, CaretReachedException> getParseTrees(CodeCompletionGoParser parser) {
            List<MultipleDecisionData> potentialAlternatives = new ArrayList<MultipleDecisionData>();
            List<Integer> currentPath = new ArrayList<Integer>();
            Map<RuleContext, CaretReachedException> results = new IdentityHashMap<RuleContext, CaretReachedException>();
            while (true) {
                tryParse(parser, potentialAlternatives, currentPath, results);
                if (!incrementCurrentPath(potentialAlternatives, currentPath)) {
                    break;
                }
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                for (Map.Entry<RuleContext, CaretReachedException> entry : results.entrySet()) {
                    LOGGER.log(Level.FINE, entry.getKey().toStringTree(parser));
                }
            }

            return results;
        }

        private boolean incrementCurrentPath(List<MultipleDecisionData> potentialAlternatives, List<Integer> currentPath) {
            for (int i = currentPath.size() - 1; i >= 0; i--) {
                if (currentPath.get(i) < potentialAlternatives.get(i).alternatives.length - 1) {
                    currentPath.set(i, currentPath.get(i) + 1);
                    return true;
                }

                potentialAlternatives.remove(i);
                currentPath.remove(i);
            }

            return false;
        }

        private void tryParse(CodeCompletionGoParser parser, List<MultipleDecisionData> potentialAlternatives, List<Integer> currentPath, Map<RuleContext, CaretReachedException> results) {
            RuleContext parseTree;
            try {
                parser.getTokenStream().seek(0);
                parser.getInterpreter().setFixedDecisions(potentialAlternatives, currentPath);
                parseTree = parser.topLevelDecl();
                results.put(parseTree, null);
            } catch (CaretReachedException ex) {
                if (ex.getTransitions() == null) {
                    return;
                }

                for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = (RuleContext)parseTree.getParent()) {
                    // intentionally blank
                }

                if (ex.getCause() instanceof FailedPredicateException) {
                    return;
                }

                if (ex.getCause() != null) {
                    IntervalSet alts = new IntervalSet();
                    for (ATNConfig c : ex.getTransitions().keySet()) {
                        alts.add(c.alt);
                    }

                    MultipleDecisionData decisionData = new MultipleDecisionData();
                    decisionData.inputIndex = parser.getInputStream().index();
                    decisionData.decision = 0;
                    if (ex.getCause() != null) {
                        ATNState state = parser.getATN().states.get(((ParserRuleContext<?>)ex.getCause().getCtx()).s);
                        if (state instanceof DecisionState) {
                            decisionData.decision = ((DecisionState)state).decision;
                        }
                    }
                    decisionData.alternatives = alts.toArray();
                    potentialAlternatives.add(decisionData);
                    currentPath.add(-1);
                }
                else {
                    results.put(parseTree, ex);
                }
            } catch (RecognitionException ex) {
                // not a viable path
            }
        }

        private ParseTreeAnnotations annotations = new ParseTreeAnnotations();

        //private class LabelAnalyzer extends BlankGoParserBaseListener {
        //    private static final String ATTR_LABEL_DEF = "label-def";
        //
        //    public boolean isLabelDefinition(GoParserBase.labelContext context) {
        //
        //    }
        //}

        private class TargetAnalyzer extends BlankGoParserBaseListener {
            private static final String ATTR_TARGET = "target";

            public Collection<? extends CodeElementModel> resolveTarget(ParserRuleContext<Token> selector) {
                Collection<? extends CodeElementModel> result = getTargetProperty(selector);
                if (result != null) {
                    return result;
                }

                LOGGER.log(Level.FINE, "TODO: resolve selector target");
                ParseTreeWalker.DEFAULT.walk(this, selector);
                result = getTargetProperty(selector);
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
            public void exitRule(selectorExprContext ctx) {
                Collection<? extends CodeElementModel> target = resolveTarget(ctx.e);
                if (target == null) {
                    annotations.putProperty(ctx, ATTR_TARGET, null);
                    return;
                }

                super.exitRule(ctx);
            }

            @SuppressWarnings("unchecked")
            private Collection<? extends CodeElementModel> getTargetProperty(ParserRuleContext<Token> context) {
                Object result = annotations.getProperty(context, ATTR_TARGET);
                if (result instanceof Collection<?>) {
                    return (Collection<? extends CodeElementModel>)result;
                }

                return null;
            }

            private void setTargetProperty(ParserRuleContext<?> context, Collection<? extends CodeElementModel> model) {
                annotations.putProperty(context, ATTR_TARGET, model);
            }
        }
    }
}
