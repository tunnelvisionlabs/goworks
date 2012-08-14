/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.PlusBlockStartState;
import org.antlr.v4.runtime.atn.PlusLoopbackState;
import org.antlr.v4.runtime.atn.StarLoopEntryState;
import org.antlr.v4.runtime.atn.StarLoopbackState;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.antlr.works.editor.antlr4.completion.CaretReachedException;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.completion.CodeCompletionErrorStrategy;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;
import org.antlr.works.editor.antlr4.completion.CodeCompletionTokenSource;
import org.antlr.works.editor.antlr4.completion.MultipleDecisionData;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NotImplementedException;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.completion.CodeCompletionGoParser;
import org.tvl.goworks.editor.go.completion.ParserCache;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;
import org.tvl.goworks.editor.go.semantics.SemanticAnalyzer;

/**
 *
 * @author Sam Harwell
 */
public class GoIndentTask implements IndentTask {
    private static final Logger LOGGER = Logger.getLogger(GoIndentTask.class.getName());

    private final Context context;

    private ParserTaskManager taskManager;
    private DocumentSnapshot snapshot;
    private FileModel fileModel;
    private boolean fileModelDataFailed;

    private CodeStyle codeStyle;
    private CodeCompletionGoParser parser;

    public GoIndentTask(Context context) {
        this.context = context;
    }

    @Override
    public void reindent() throws BadLocationException {
        if (!smartReindent()) {
            fallbackReindent();
        }
    }

    public boolean smartReindent() throws BadLocationException {
        if (!(context.document() instanceof StyledDocument)) {
            return false;
        }

        StyledDocument document = (StyledDocument)context.document();

        taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        if (taskManager == null) {
            return false;
        }

        VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(context.document());
        snapshot = versionedDocument.getCurrentSnapshot();
        List<Anchor> anchors = getDynamicAnchorPoints();
        if (anchors == null) {
            return false;
        }

        SnapshotPosition contextEndPosition = new SnapshotPosition(snapshot, context.endOffset());
        SnapshotPosition endPosition = contextEndPosition.getContainingLine().getEndIncludingLineBreak();
        SnapshotPosition endPositionOnLine = contextEndPosition.getContainingLine().getEnd();

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

            if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= endPosition.getOffset()) {
                previous = anchor;
                if (anchor.getSpan().getEndPosition(snapshot).getOffset() > endPosition.getOffset()) {
                    enclosing = anchor;
                }
            } else {
                next = anchor;
                break;
            }
        }

        if (previous == null) {
            return false;
        }

        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        Tagger<TokenTag<Token>> tagger = null;
        try {
            tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }

        int regionEnd = Math.min(snapshot.length(), endPosition.getOffset() + 1);
        OffsetRegion region;
        if (enclosing != null) {
            region = OffsetRegion.fromBounds(enclosing.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
        } else {
            // at least for now, include the previous span due to the way error handling places bounds on an anchor
            region = OffsetRegion.fromBounds(previous.getSpan().getStartPosition(snapshot).getOffset(), regionEnd);
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Reindent from anchor region: {0}.", region);
        }

        TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(snapshot, region));
        TokenSource<Token> tokenSource = new CodeCompletionTokenSource(endPosition.getOffset(), taggerTokenSource);
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);

        Map<RuleContext<Token>, CaretReachedException> parseTrees;
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
                parseTrees = getParseTrees(parser);
                break;

            default:
                parseTrees = null;
                break;
            }

            if (parseTrees == null) {
                return false;
            }

            NavigableMap<Integer, List<Map.Entry<RuleContext<Token>, CaretReachedException>>> indentLevels =
                new TreeMap<Integer, List<Map.Entry<RuleContext<Token>, CaretReachedException>>>();
            for (Map.Entry<RuleContext<Token>, CaretReachedException> parseTree : parseTrees.entrySet()) {
                ParseTree<Token> firstNodeOnLine = findFirstNodeAfterOffset(parseTree.getKey(), endPositionOnLine.getContainingLine().getStart().getOffset());
                if (firstNodeOnLine == null) {
                    firstNodeOnLine = parseTree.getValue().getFinalContext();
                }

                if (firstNodeOnLine == null) {
                    continue;
                }

                int indentationLevel = getIndent(firstNodeOnLine);

//                TerminalNode<Token> startNode = ParseTrees.getStartNode(parseTree.getKey());
//                //int startLine = new SnapshotPosition(snapshot, startNode.getSymbol().getStartIndex()).getContainingLine();
//                int lineStartOffset = context.lineStartOffset(startNode.getSymbol().getStartIndex());
//                int outerIndent = context.lineIndent(lineStartOffset);

                List<Map.Entry<RuleContext<Token>, CaretReachedException>> indentList =
                    indentLevels.get(indentationLevel);
                if (indentList == null) {
                    indentList = new ArrayList<Map.Entry<RuleContext<Token>, CaretReachedException>>();
                    indentLevels.put(indentationLevel, indentList);
                }

                indentList.add(parseTree);
            }

            int indentLevel = !indentLevels.isEmpty() ? indentLevels.firstKey() : 0;
            if (indentLevels.size() > 1) {
                // TODO: resolve multiple possibilities (appears at least with case statements)
            }

            int startLine = NbDocument.findLineNumber(document, context.startOffset());
            int endLine;
            if (context.endOffset() <= context.startOffset()) {
                endLine = startLine;
            } else {
                endLine = NbDocument.findLineNumber(document, context.endOffset() - 1);
            }

            for (int line = startLine; line <= endLine; line++) {
                int currentOffset = NbDocument.findLineOffset(document, startLine);
                context.modifyIndent(currentOffset, indentLevel);
            }

            return true;
        } finally {
            ParserCache.DEFAULT.putParser(parser);
            parser = null;
        }
    }

    private FileModel getFileModel() {
        if (fileModel == null && !fileModelDataFailed) {
            Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
            try {
                fileModel = futureFileModelData != null ? futureFileModelData.get().getData() : null;
                fileModelDataFailed = fileModel != null;
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

    private List<Anchor> getDynamicAnchorPoints() {
        List<Anchor> anchors;
        Future<ParserData<List<Anchor>>> result =
            taskManager.getData(snapshot, GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        try {
            anchors = result != null ? result.get().getData() : null;
        } catch (InterruptedException ex) {
            anchors = null;
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            anchors = null;
        }

        return anchors;
    }

    private Map<ParseTree<Token>, GoAnnotatedParseTree> analyzeParseTrees(VersionedDocument document, Map<? extends ParseTree<Token>, ? extends CaretReachedException> parseTrees) {
        Map<ParseTree<Token>, GoAnnotatedParseTree> result = new IdentityHashMap<ParseTree<Token>, GoAnnotatedParseTree>();
        for (Map.Entry<? extends ParseTree<Token>, ? extends CaretReachedException> entry : parseTrees.entrySet()) {
            ParseTree<Token> context = entry.getKey();
            GoAnnotatedParseTree annotatedTree = SemanticAnalyzer.analyze(document, context);
            result.put(context, annotatedTree);
        }

        return result;
    }

    protected Map<RuleContext<Token>, CaretReachedException> getParseTrees(CodeCompletionParser parser) {
        List<MultipleDecisionData> potentialAlternatives = new ArrayList<MultipleDecisionData>();
        IntegerList currentPath = new IntegerList();
        Map<RuleContext<Token>, CaretReachedException> results = new IdentityHashMap<RuleContext<Token>, CaretReachedException>();
        // make sure the token stream is initialized before getting the index
        parser.getInputStream().LA(1);
        int initialToken = parser.getInputStream().index();
        while (true) {
            parser.getInputStream().seek(initialToken);
            tryParse(parser, potentialAlternatives, currentPath, results);
            if (!incrementCurrentPath(potentialAlternatives, currentPath)) {
                break;
            }
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            for (Map.Entry<RuleContext<Token>, CaretReachedException> entry : results.entrySet()) {
                LOGGER.log(Level.FINE, entry.getKey().toStringTree((Parser)parser));
            }
        }

        return results;
    }

    protected boolean incrementCurrentPath(List<MultipleDecisionData> potentialAlternatives, IntegerList currentPath) {
        for (int i = currentPath.size() - 1; i >= 0; i--) {
            if (currentPath.get(i) < potentialAlternatives.get(i).alternatives.length - 1) {
                currentPath.set(i, currentPath.get(i) + 1);
                return true;
            }

            potentialAlternatives.remove(i);
            currentPath.removeAt(i);
        }

        return false;
    }

    protected void tryParse(CodeCompletionParser parser, List<MultipleDecisionData> potentialAlternatives, IntegerList currentPath, Map<RuleContext<Token>, CaretReachedException> results) {
        RuleContext<Token> parseTree;
        try {
            parser.getInterpreter().setFixedDecisions(potentialAlternatives, currentPath);
            parseTree = parseImpl(parser);
            results.put(parseTree, null);
        } catch (CaretReachedException ex) {
            if (ex.getTransitions() == null) {
                return;
            }

            for (parseTree = ex.getFinalContext(); parseTree.getParent() != null; parseTree = parseTree.getParent()) {
                // intentionally blank
            }

            if (ex.getCause() instanceof FailedPredicateException) {
                return;
            }

            if (ex.getCause() != null) {
                IntervalSet alts = new IntervalSet();
                for (ATNConfig c : ex.getTransitions().keySet()) {
                    alts.add(c.getAlt());
                }

                MultipleDecisionData decisionData = new MultipleDecisionData();
                decisionData.inputIndex = parser.getInputStream().index();
                decisionData.decision = 0;
                if (ex.getCause() != null) {
                    int stateNumber = ex.getCause().getOffendingState();
                    ATNState state = parser.getATN().states.get(stateNumber);
                    if (state instanceof StarLoopbackState) {
                        assert state.getNumberOfTransitions() == 1 && state.onlyHasEpsilonTransitions();
                        assert state.transition(0).target instanceof StarLoopEntryState;
                        state = state.transition(0).target;
                    } else if (state instanceof PlusBlockStartState && ((PlusBlockStartState)state).decision == -1) {
                        state = ((PlusBlockStartState)state).loopBackState;
                        assert state instanceof PlusLoopbackState;
                    }

                    if (state instanceof DecisionState) {
                        decisionData.decision = ((DecisionState)state).decision;
                        if (decisionData.decision <= 0) {
                            LOGGER.log(Level.FINE, "No decision number found for state {0}.", state.stateNumber);
                        }
                    } else {
                        if (state != null) {
                            LOGGER.log(Level.FINE, "No decision number found for state {0}.", state.stateNumber);
                        } else {
                            LOGGER.log(Level.FINE, "No decision number found for state <null>.");
                        }
                        // continuing is likely to never terminate
                        return;
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

    protected RuleContext<Token> parseImpl(CodeCompletionParser parser) {
        return ((CodeCompletionGoParser)parser).sourceFileBody();
    }

    private void fallbackReindent() throws BadLocationException {
        if (!(context.document() instanceof StyledDocument)) {
            return;
        }

        StyledDocument document = (StyledDocument)context.document();
        int startLine = NbDocument.findLineNumber(document, context.startOffset());

        int endLine;
        if (context.endOffset() <= context.startOffset()) {
            endLine = startLine;
        } else {
            endLine = NbDocument.findLineNumber(document, context.endOffset() - 1);
        }

        int previousIndent;
        if (startLine == 0) {
            previousIndent = 0;
        } else {
            int previousLineOffset = NbDocument.findLineOffset(document, startLine - 1);
            previousIndent = context.lineIndent(previousLineOffset);
        }

        for (int line = startLine; line <= endLine; line++) {
            int currentOffset = NbDocument.findLineOffset(document, startLine);
            int currentIndent = context.lineIndent(currentOffset);
            if (currentIndent == 0 && previousIndent > 0) {
                context.modifyIndent(currentOffset, previousIndent);
            }

            previousIndent = currentIndent;
        }
    }

    @Override
    public ExtraLock indentLock() {
        return null;
    }

    private TerminalNode<Token> findFirstNodeAfterOffset(ParseTree<Token> tree, int offset) {
        TerminalNode<Token> lastNode = ParseTrees.getStopNode(tree);
        if (lastNode == null) {
            return null;
        }

        if (lastNode.getSymbol() instanceof CaretToken) {
            throw new NotImplementedException();
        } else if (lastNode.getSymbol().getStartIndex() < offset) {
            return null;
        }

        if (tree instanceof TerminalNode) {
            return (TerminalNode<Token>)tree;
        }

        for (int i = 0; i < tree.getChildCount(); i++) {
            TerminalNode<Token> node = findFirstNodeAfterOffset(tree.getChild(i), offset);
            if (node != null) {
                return node;
            }
        }

        return null;
    }

    private CodeStyle getCodeStyle() {
        if (codeStyle == null) {
            codeStyle = CodeStyle.getDefault(context.document());
        }

        return codeStyle;
    }

    private int getIndent(ParseTree<Token> node) throws BadLocationException {
//        System.out.println(node.toStringTree(parser));
        int nodeLineStart = -1;

        for (ParseTree<Token> current = node; current != null; current = current.getParent()) {
            if (!(current instanceof RuleNode)) {
                continue;
            }

            ParserRuleContext<Token> ruleContext = (ParserRuleContext<Token>)((RuleNode<Token>)current).getRuleContext();
            if (nodeLineStart == -1) {
                nodeLineStart = context.lineStartOffset(ruleContext.start.getStartIndex());
            }

            switch (ruleContext.getRuleIndex()) {
            case GoParser.RULE_block:
            case GoParser.RULE_literalValue:
            case GoParser.RULE_structType:
            case GoParser.RULE_interfaceType:
            case GoParser.RULE_exprSwitchStmt:
            case GoParser.RULE_typeSwitchStmt:
            case GoParser.RULE_selectStmt:
                TerminalNode<Token> leftBrace = ruleContext.getToken(GoParser.LeftBrace, 0);
                if (leftBrace == null) {
                    continue;
                }

                // get the indent of the line where the block starts
                int blockLineOffset = context.lineStartOffset(leftBrace.getSymbol().getStartIndex());
                int blockIndent = context.lineIndent(blockLineOffset);
                if (nodeLineStart == blockLineOffset) {
                    return blockIndent;
                }

                if (node instanceof TerminalNode) {
                    // no extra indent if the first node on the line is the closing brace of the block
                    if (node == ruleContext.getToken(GoParser.RightBrace, 0)) {
                        return blockIndent;
                    } else {
                        Token symbol = ((TerminalNode<Token>)node).getSymbol();
                        switch (symbol.getType()) {
                        case GoParser.Case:
                        case GoParser.Default:
                            return blockIndent;

                        default:
                                break;
                        }
                    }
                }

                return blockIndent + getCodeStyle().getIndentSize();

            default:
                if (current.getParent() == null) {
                    int outerLineOffset = context.lineStartOffset(ruleContext.start.getStartIndex());
                    int outerIndent = context.lineIndent(outerLineOffset);
                    if (nodeLineStart == outerLineOffset) {
                        return outerIndent;
                    }

                    return outerIndent + getCodeStyle().getIndentSize();
                }

                continue;
            }
        }

        return 0;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=IndentTask.Factory.class)
    public static final class FactoryImpl implements Factory {

        @Override
        public IndentTask createTask(Context context) {
            return new GoIndentTask(context);
        }

    }
}
