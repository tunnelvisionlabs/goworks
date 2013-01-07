/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import com.tvl.spi.editor.completion.CompletionItem;
import com.tvl.spi.editor.completion.CompletionProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import org.antlr.v4.runtime.Dependents;
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
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple3;
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
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.editor.BaseDocument;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeElementPositionRegion;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.IntrinsicTypeModels;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeArrayModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;
import org.tvl.goworks.editor.go.codemodel.TypeWrapperModel;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ConstModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ParameterModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeArrayModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeChannelModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeFunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeInterfaceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeMapModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeWrapperModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.VarModelImpl;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.GoLexer;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AddExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AndExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BlockContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CommClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.DeclarationContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementNameOrIndexContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ForClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ForStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IfStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IndexExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InitStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.KeyTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LabelContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LabeledStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MultExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OrExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParametersContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PostStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.RangeClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.RecvStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SelectStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SelectorExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SignatureContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SimpleStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SourceFileBodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StatementContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TopLevelDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseListener;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseVisitor;
import org.tvl.goworks.editor.go.semantics.BundledReturnTypeModel;
import org.tvl.goworks.editor.go.semantics.CodeElementReference;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;
import org.tvl.goworks.editor.go.semantics.GoAnnotations;
import org.tvl.goworks.editor.go.semantics.NodeType;
import org.tvl.goworks.editor.go.semantics.SemanticAnalyzer;
import org.tvl.goworks.project.GoProject;

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

    @CheckForNull
    public static <Symbol> ParseTree<Symbol> getTopContext(@NonNull ParseTree<Symbol> context, @NonNull IntervalSet values) {
        return getTopContext(context, values, true);
    }

    @CheckForNull
    public static <Symbol> ParseTree<Symbol> getTopContext(@NonNull ParseTree<Symbol> context, @NonNull IntervalSet values, boolean checkTop) {
        if (checkTop && context instanceof RuleNode && values.contains(((RuleNode<?>)context).getRuleContext().getRuleIndex())) {
            return context;
        }

        ParseTree<Symbol> parent = context.getParent();
        if (parent == null) {
            return null;
        }

        return getTopContext(parent, values, true);
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

    private static final ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> ATTR_CONSTANTS = new ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>>("constants");
    private static final ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> ATTR_LOCALS = new ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>>("locals");
    private static final ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> ATTR_PARAMETER = new ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>>("parameter");
    private static final ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> ATTR_RECEIVER_PARAMETER = new ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>>("receiver-parameter");
    private static final ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> ATTR_RETURN_PARAMETER = new ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>>("return-parameter");

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

        private FileModelImpl fileModel;
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
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
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
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementNameOrIndex, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalValue, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=3, dependents=Dependents.PARENTS),
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

                Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
                Tagger<TokenTag<Token>> tagger = null;
                try {
                    tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while getting tokens.", ex);
                } catch (ExecutionException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while getting tokens.", ex);
                }

                int regionStart;
                if (enclosing != null) {
                    regionStart = enclosing.getSpan().getStartPosition(snapshot).getOffset();
                } else if (previous != null) {
                    regionStart = previous.getSpan().getStartPosition(snapshot).getOffset();
                } else {
                    regionStart = 0;
                }

                int regionEnd = Math.min(snapshot.length(), getCaretOffset() + 1);
                OffsetRegion region = OffsetRegion.fromBounds(regionStart, regionEnd);
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE, "Code completion from {0}: {1}.", new Object[] { enclosing != null || previous != null ? "anchor region" : "top of File", region });
                }

                TaggerTokenSource<Token> taggerTokenSource = new TaggerTokenSource<Token>(tagger, new SnapshotPositionRegion(snapshot, region));
                TokenSource<Token> tokenSource = new CodeCompletionTokenSource(getCaretOffset(), taggerTokenSource);
                CommonTokenStream tokens = new CommonTokenStream(tokenSource);

                parser = ParserFactory.DEFAULT.getParser(tokens);
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

                    int anchorRule;
                    if (enclosing != null) {
                        anchorRule = enclosing.getRule();
                    } else if (previous != null) {
                        anchorRule = previous.getRule();
                    } else {
                        anchorRule = GoParser.RULE_topLevelDecl;
                    }

                    switch (anchorRule) {
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
                                    GoProject mainProject = getFileModel().getPackage().getProject();
                                    for (GoProject library : mainProject.getLibraryProjects()) {
                                        packages.addAll(CodeModelCacheImpl.getInstance().getPackages(library, importDeclarationModel.getPath()));
                                    }
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
                                if (finalContext instanceof GoParser.QualifiedIdentifierContext) {
                                    GoParser.QualifiedIdentifierContext context = (GoParser.QualifiedIdentifierContext)finalContext;
                                    if (context.packageName() != null) {
                                        continue;
                                    }

                                    if (context.getParent() instanceof OperandContext
                                        && context.getParent().getParent() instanceof ExpressionContext
                                        && context.getParent().getParent().getParent() instanceof ElementNameOrIndexContext)
                                    {
                                        // could be a field name
                                        assert compositeLiteralContext != null && compositeLiteralContext.literalType() != null;

                                        List<GoParser.LiteralValueContext> literalValueContexts = new ArrayList<GoParser.LiteralValueContext>();
                                        for (RuleContext<Token> context2 = finalContext; context2 != null; context2 = context2.parent) {
                                            if (context2 instanceof GoParser.LiteralValueContext) {
                                                literalValueContexts.add((GoParser.LiteralValueContext)context2);
                                            } else if (context2 instanceof GoParser.CompositeLiteralContext) {
                                                // stop at the containing compositeLiteral
                                                break;
                                            }
                                        }

                                        // first resolve the type of the containing compositeLiteral
                                        Collection<? extends CodeElementModel> models = targetAnalyzer.visit(compositeLiteralContext.literalType());
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

                                            TypeModel typeModel = null;
                                            if (builtin.equals("true") || builtin.equals("false")) {
                                                typeModel = IntrinsicTypeModels.BOOL;
                                            }

                                            intermediateResults.put(builtin, new ConstReferenceCompletionItem(builtin, typeModel, false));
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
                                        ParseTree<Token> functionContext = getTopContext(parser, finalContext, new IntervalSet() {{ add(GoParser.RULE_functionDecl); add(GoParser.RULE_methodDecl); }});
                                        if (functionContext != null) {
                                            addVars(VarKind.RECEIVER, finalContext, localsAnalyzer.getReceiverParameters(functionContext), intermediateResults);
                                            addVars(VarKind.PARAMETER, finalContext, localsAnalyzer.getParameters(functionContext), intermediateResults);
                                            addVars(VarKind.RETURN, finalContext, localsAnalyzer.getReturnParameters(functionContext), intermediateResults);
                                            addVars(VarKind.LOCAL, finalContext, localsAnalyzer.getLocals(functionContext), intermediateResults);
                                            addVars(VarKind.LOCAL, finalContext, localsAnalyzer.getConstants(functionContext), intermediateResults);
                                        }
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

                        final boolean sourceTestSource = getFileModel().getName().toLowerCase().endsWith("_test.go");

                        for (Iterator<Map.Entry<String, CompletionItem>> it = intermediateResults.entrySet().iterator(); it.hasNext(); ) {
                            Map.Entry<String, CompletionItem> entry = it.next();
                            if (entry.getValue() instanceof GoCompletionItem) {
                                CodeElementModel codeElementModel = ((GoCompletionItem)entry.getValue()).getCodeElementModel();
                                if (codeElementModel == null || codeElementModel.getPackage() == null) {
                                    continue;
                                }

                                final boolean inCurrentPackage = codeElementModel.getPackage().equals(getFileModel().getPackage());
                                if (!inCurrentPackage && !Character.isUpperCase(codeElementModel.getName().charAt(0))) {
                                    // item is not visible to other packages
                                    it.remove();
                                    continue;
                                }

                                if (!(codeElementModel instanceof AbstractCodeElementModel)) {
                                    continue;
                                }

                                FileModelImpl codeElementFile = ((AbstractCodeElementModel)codeElementModel).getFile();
                                if (codeElementFile == null) {
                                    continue;
                                }

                                final boolean targetTestSource = codeElementFile.getName().toLowerCase().endsWith("_test.go");
                                if (targetTestSource && (!sourceTestSource || !inCurrentPackage)) {
                                    // item is defined in a test source that isn't visible
                                    it.remove();
                                    continue;
                                }
                            }
                        }

                        results.addAll(intermediateResults.values());
                    }
                } finally {
                    parser = null;
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

        private void addVars(VarKind varKind, ParseTree<? extends Token> finalContext, Collection<? extends Tuple3<? extends TerminalNode<? extends Token>, ? extends ParserRuleContext<Token>, Integer>> vars, Map<String, ? super GoCompletionItem> intermediateResults) {
            for (Tuple3<? extends TerminalNode<? extends Token>, ? extends ParserRuleContext<Token>, Integer> varEntry : vars) {
                String name = varEntry.getItem1().getText();

                // make sure the item is visible in the final context
                ParseTree<?> scopeContext = ScopeContextVisitor.INSTANCE.visit(varEntry.getItem1());
                if (!ParseTrees.isAncestorOf(scopeContext, finalContext)) {
                    continue;
                }

                int index = varEntry.getItem3();
                if (index < 0) {
                    index = -index - 1;
                }

                Collection<? extends CodeElementModel> varTypes = targetAnalyzer.visit(varEntry.getItem2());
                if (varTypes != null && index >= 0) {
                    ArrayList<CodeElementModel> unbundledTypes = new ArrayList<CodeElementModel>();
                    for (CodeElementModel varType : varTypes) {
                        if (varType instanceof BundledReturnTypeModel) {
                            List<? extends CodeElementModel> returnValues = ((BundledReturnTypeModel)varType).getReturnValues();
                            if (returnValues.size() > index) {
                                unbundledTypes.add(((BundledReturnTypeModel)varType).getReturnValues().get(index));
                            }
                        } else if (index == 0) {
                            unbundledTypes.add(varType);
                        }
                    }

                    varTypes = unbundledTypes;
                }

                if (varTypes == null || varTypes.isEmpty()) {
                    Object existing = intermediateResults.get(name);
                    if (existing instanceof VarReferenceCompletionItem) {
                        VarModel existingModel = ((VarReferenceCompletionItem)existing).getCodeElementModel();
                        CodeElementPositionRegion seek = existingModel.getSeek();
                        if (seek != null && seek.getFileObject().equals(getFileModel().getFileObject())) {
                            if (seek.getOffsetRegion().getStart() == varEntry.getItem1().getSymbol().getStartIndex()) {
                                continue;
                            }
                        }
                    }

                    varTypes = Collections.singleton(new UnknownTypeModelImpl(getFileModel()));
                }

                if (varEntry.getItem3() < 0) {
                    ConstModelImpl constModel = new ConstModelImpl(name, getFileModel(), null, null, null, varEntry.getItem1(), varEntry.getItem2());
                    intermediateResults.put(name, new ConstReferenceCompletionItem(constModel, true));
                } else {
                    for (CodeElementModel model : varTypes) {
                        TypeModelImpl typeModel;
                        if (model instanceof TypeModelImpl) {
                            typeModel = (TypeModelImpl)model;
                        } else if (model instanceof VarModelImpl) {
                            // this could be an implicit reference to a field or the return value of a function
                            VarModelImpl varModel = (VarModelImpl)model;
                            typeModel = varModel.getVarType();
                        } else if (model instanceof ConstModel) {
                            intermediateResults.put(name, new ConstReferenceCompletionItem((ConstModel)model, true));
                            continue;
                        } else {
                            LOGGER.log(Level.WARNING, "Unsupported code model: {0}", model.getClass());
                            continue;
                        }

                        VarModelImpl varModel = new VarModelImpl(name, varKind, typeModel, getFileModel(), varEntry.getItem1(), varEntry.getItem2());
                        intermediateResults.put(name, new VarReferenceCompletionItem(varModel, true));
                        break;
                    }
                }
            }
        }

        private FileModelImpl getFileModel() {
            if (fileModel == null && !fileModelDataFailed) {
                Future<ParserData<FileModel>> futureFileModelData = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
                try {
                    fileModel = futureFileModelData != null ? (FileModelImpl)futureFileModelData.get().getData() : null;
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

        private final ObjectDecorator<ParseTree<Token>> annotations = new ObjectDecorator<ParseTree<Token>>();

        private class LocalsAnalyzer {

//            private final List<Token> labels = new ArrayList<Token>();

            public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getLocals(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_LOCALS);
            }

            public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getConstants(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_CONSTANTS);
            }

            public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getReceiverParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RECEIVER_PARAMETER);
            }

            public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_PARAMETER);
            }

            public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getReturnParameters(@NonNull ParseTree<Token> context) {
                Parameters.notNull("context", context);
                return getLocals(context, ATTR_RETURN_PARAMETER);
            }

            private Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getLocals(ParseTree<Token> context, ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> property) {
                Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> result = getLocalsProperty(context, property);
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

                if (result == null) {
                    return Collections.emptyList();
                }

                return result;
            }

//            public List<Token> getLabels() {
//                return labels;
//            }

            private Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getLocalsProperty(ParseTree<Token> context, ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> property) {
                Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> result = annotations.getProperty(context, property);
                if (result != null) {
                    return result;
                }

                return null;
            }

            private void setLocalsProperty(ParseTree<Token> context, ObjectProperty<Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>> property, @NonNull Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> locals) {
                Parameters.notNull("locals", locals);
                annotations.putProperty(context, property, locals);
            }

            private class Listener extends GoParserBaseListener {
                private final Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> locals = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();
                private final Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> receiverParameters = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();
                private final Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> parameters = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();
                private final Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> returnParameters = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();
                private final Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> constants = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();

                public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getLocals() {
                    return locals;
                }

                public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getConstants() {
                    return constants;
                }

                public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getReceiverParameters() {
                    return receiverParameters;
                }

                public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getParameters() {
                    return parameters;
                }

                public Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> getReturnParameters() {
                    return returnParameters;
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                })
                public void enterVarSpec(VarSpecContext ctx) {
                    addVars(locals, ctx.identifierList(), ctx.type(), ctx.expressionList());
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=1),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1),
                })
                public void enterShortVarDecl(ShortVarDeclContext ctx) {
                    addVars(locals, ctx.identifierList(), null, ctx.expressionList());
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                })
                public void enterRangeClause(RangeClauseContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.add(Tuple.create(getStartNode(ctx.e1), (ParserRuleContext<Token>)ctx, 0));
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.add(Tuple.create(getStartNode(ctx.e2), (ParserRuleContext<Token>)ctx, 1));
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
                        locals.add(Tuple.create(ctx.IDENTIFIER(), (ParserRuleContext<Token>)ctx, 0));
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_recvStmt, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                })
                public void enterRecvStmt(RecvStmtContext ctx) {
                    if (ctx.defeq != null) {
                        if (ctx.e1 != null && ctx.e1.start != null) {
                            locals.add(Tuple.create(getStartNode(ctx.e1), (ParserRuleContext<Token>)ctx.recvExpr(), 0));
                        }

                        if (ctx.e2 != null && ctx.e2.start != null) {
                            locals.add(Tuple.create(getStartNode(ctx.e2), (ParserRuleContext<Token>)ctx.recvExpr(), 1));
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
                        receiverParameters.add(Tuple.create(ctx.IDENTIFIER(), (ParserRuleContext<Token>)ctx.baseTypeName(), 0));
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
                        Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> vars = parametersContext.parent instanceof GoParser.ResultContext ? returnParameters : parameters;
                        addVars(vars, ctx.identifierList(), ctx.type(), null);
                    }
                }

                @Override
                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1),
                })
                public void enterConstSpec(ConstSpecContext ctx) {
                    addVars(constants, ctx.identifierList(), ctx.type(), ctx.expressionList());
                }

                @RuleDependencies({
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expressionList, version=1),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
                })
                private void addVars(@NonNull Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> vars,
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
                        int index = type != null ? 0 : i;
                        if (type == null && expressions != null) {
                            if (i < expressions.size()) {
                                type = expressions.get(i);
                                index = 0;
                            } else if (expressions.size() == 1) {
                                type = expressions.get(0);
                            }
                        }

                        if (type == null) {
                            LOGGER.log(Level.FINE, "Couldn't resolve expression type.");
                            continue;
                        }

                        if (idList.getParent() instanceof ConstSpecContext) {
                            index = -index - 1;
                        }

                        vars.add(Tuple.create(name, type, index));
                    }
                }
            }
        }

        private class TargetAnalyzer extends GoParserBaseVisitor<Collection<? extends CodeElementModel>> {

            @Override
            public Collection<? extends CodeElementModel> visitChildren(RuleNode<? extends Token> node) {
                LOGGER.log(Level.WARNING, "Context {0} is not supported by this visitor.", node.getClass());
                return Collections.emptyList();
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0),
            })
            public Collection<? extends CodeElementModel> visitPackageName(PackageNameContext ctx) {
                FileModel fileModel = getFileModel();
                if (ctx.IDENTIFIER() == null || fileModel == null) {
                    return Collections.emptyList();
                }

                if (ctx.getParent() instanceof GoParser.PackageClauseContext) {
                    Collection<? extends CodeElementModel> resolved = Collections.singletonList(fileModel.getPackage());
                    setTargetProperty(ctx, resolved);
                    return resolved;
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
                    return resolved;
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitUnaryExpr(UnaryExprContext ctx) {
                if (ctx.op == null) {
                    return Collections.emptyList();
                }

                switch (ctx.op.getType()) {
                case GoParser.Star:
                    // dereference value
                    Collection<? extends CodeElementModel> pointerTypes = visit(ctx.expression());
                    Collection<CodeElementModel> result = new ArrayList<CodeElementModel>();
                    for (CodeElementModel model : pointerTypes) {
                        for (TypeModelImpl typeModel : resolveType(model, true, false)) {
                            if (!(typeModel instanceof TypePointerModel)) {
                                LOGGER.log(Level.WARNING, "Cannot dereference expression of type {0}.", typeModel);
                                continue;
                            }

                            result.add(((TypePointerModel)typeModel).getElementType());
                        }
                    }

                    setTargetProperty(ctx, result);
                    return result;

                case GoParser.Amp:
                    // dereference value
                    Collection<? extends CodeElementModel> types = visit(ctx.expression());
                    result = new ArrayList<CodeElementModel>();
                    for (CodeElementModel model : types) {
                        TypeModel typeModel;
                        if (model instanceof TypeModel) {
                            typeModel = (TypeModel)model;
                        } else if (model instanceof VarModel) {
                            typeModel = ((VarModel)model).getVarType();
                        } else {
                            LOGGER.log(Level.WARNING, "Cannot deference expression model of {0}.", model.getClass());
                            continue;
                        }

                        if (!(typeModel instanceof TypeModelImpl)) {
                            continue;
                        }

                        result.add(new TypePointerModelImpl((TypeModelImpl)typeModel));
                    }

                    setTargetProperty(ctx, result);
                    return result;

                default:
                    LOGGER.log(Level.WARNING, "Unary operator {0} is not supported by this visitor.", ctx.op.getText());
                    return Collections.emptyList();
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitMultExpr(MultExprContext ctx) {
                if (ctx.op == null) {
                    return Collections.emptyList();
                }

                switch (ctx.op.getType()) {
                case GoLexer.LeftShift:
                case GoLexer.RightShift:
                    return visit(ctx.expression(0));

                case GoLexer.Star:
                case GoLexer.Slash:
                case GoLexer.Percent:
                case GoLexer.Amp:
                case GoLexer.AmpCaret:
                    // this may not be absolutely precise, but for now it's close enough to make the UI work
                    return visit(ctx.expression(0));

                default:
                    LOGGER.log(Level.WARNING, "Multiply operator {0} is not supported by this visitor.", ctx.op.getText());
                    return Collections.emptyList();
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitAddExpr(AddExprContext ctx) {
                if (ctx.op == null) {
                    return Collections.emptyList();
                }

                switch (ctx.op.getType()) {
                case GoLexer.Plus:
                case GoLexer.Minus:
                case GoLexer.Pipe:
                case GoLexer.Caret:
                    // this may not be absolutely precise, but for now it's close enough to make the UI work
                    return visit(ctx.expression(0));

                default:
                    LOGGER.log(Level.WARNING, "Add operator {0} is not supported by this visitor.", ctx.op.getText());
                    return Collections.emptyList();
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitIndexExpr(IndexExprContext ctx) {
                List<? extends ExpressionContext> expressions = ctx.expression();
                if (expressions.isEmpty()) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> target = visit(expressions.get(0));
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : target) {
                    for (TypeModelImpl type : resolveType(model, true, true)) {
                        if (type instanceof TypeArrayModel || type instanceof TypeSliceModel) {
                            result.add(((TypeWrapperModel)type).getElementType());
                        } else if (type instanceof TypeMapModel) {
                            result.add(new BundledReturnTypeModel(Arrays.asList((TypeModelImpl)((TypeMapModel)type).getValueType(), (TypeModelImpl)IntrinsicTypeModels.BOOL)));
                        } else if (IntrinsicTypeModels.STRING.equals(type)) {
                            result.add(IntrinsicTypeModels.BYTE);
                        } else {
                            LOGGER.log(Level.WARNING, "Cannot resolve index expression for source model {0}.", type.getClass());
                        }
                    }
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitSliceExpr(SliceExprContext ctx) {
                List<? extends ExpressionContext> expressions = ctx.expression();
                if (expressions.isEmpty()) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> target = visit(expressions.get(0));
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : target) {
                    for (TypeModelImpl type : resolveType(model, true, true)) {
                        if (type instanceof TypeArrayModel) {
                            result.add(new TypeSliceModelImpl(((TypeArrayModelImpl)type).getElementType()));
                        } else if (type instanceof TypeSliceModel || IntrinsicTypeModels.STRING.equals(type)) {
                            result.add(type);
                        } else {
                            LOGGER.log(Level.WARNING, "Unsupported type in slice expression: {0}", model.getClass());
                        }
                    }
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
            })
            public Collection<? extends CodeElementModel> visitOperandExpr(OperandExprContext ctx) {
                OperandContext operandContext = ctx.operand();
                if (operandContext != null) {
                    Collection<? extends CodeElementModel> resolved = visit(operandContext);
                    setTargetProperty(ctx, resolved);
                    return resolved;
                } else {
                    LOGGER.log(Level.WARNING, "TODO: handle other expressions.");
                    return Collections.emptyList();
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
            })
            public Collection<? extends CodeElementModel> visitBuiltinCallExpr(BuiltinCallExprContext ctx) {
                BuiltinCallContext builtinCallContext = ctx.builtinCall();
                if (builtinCallContext == null) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> result = visit(builtinCallContext);
                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinArgs, version=2, dependents=Dependents.SELF),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitBuiltinCall(BuiltinCallContext ctx) {
                TerminalNode<Token> methodName = ctx.IDENTIFIER();
                if (methodName == null) {
                    return Collections.emptyList();
                }

                String name = methodName.getText();
                if (name == null || name.isEmpty()) {
                    return Collections.emptyList();
                }

                BuiltinArgsContext args = ctx.builtinArgs();
                TypeContext type = args != null ? args.type() : null;
                if ("append".equals(name) || "make".equals(name)) {
                    if (type == null) {
                        return Collections.emptyList();
                    }

                    return visit(type);
                } else if ("cap".equals(name) || "copy".equals(name) || "len".equals(name)) {
                    return Collections.singletonList(IntrinsicTypeModels.INT);
                } else if ("close".equals(name) || "delete".equals(name) || "panic".equals(name) || "print".equals(name) || "println".equals(name)) {
                    // no return value
                    return Collections.emptyList();
                } else if ("complex".equals(name)) {
                    LOGGER.log(Level.FINE, "TODO: separate complex64 and complex128 types.");
                    return Collections.singletonList(IntrinsicTypeModels.COMPLEX128);
                } else if ("imag".equals(name) || "real".equals(name)) {
                    LOGGER.log(Level.FINE, "TODO: separate float32 and float64 types.");
                    return Collections.singletonList(IntrinsicTypeModels.FLOAT64);
                } else if ("new".equals(name)) {
                    if (type == null) {
                        return Collections.emptyList();
                    }

                    Collection<? extends CodeElementModel> result = visit(type);
                    if (result.isEmpty()) {
                        return result;
                    }

                    List<CodeElementModel> pointers = new ArrayList<CodeElementModel>();
                    for (CodeElementModel model : result) {
                        if (model instanceof TypeModelImpl) {
                            pointers.add(new TypePointerModelImpl((TypeModelImpl)model));
                        }
                    }

                    setTargetProperty(ctx, pointers);
                    return pointers;
                } else if ("recover".equals(name)) {
                    TypeInterfaceModelImpl result = new TypeInterfaceModelImpl("_", fileModel, ctx);
                    result.freeze();
                    return Collections.singletonList(result);
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitSelectorExpr(SelectorExprContext ctx) {
                if (ctx.IDENTIFIER() == null) {
                    setTargetProperty(ctx, Collections.<CodeElementModel>emptyList());
                    return Collections.emptyList();
                }

                String name = ctx.IDENTIFIER().getSymbol().getText();
                if (name == null || name.isEmpty()) {
                    setTargetProperty(ctx, Collections.<CodeElementModel>emptyList());
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> targets = visit(ctx.expression());

                List<CodeElementModel> members = new ArrayList<CodeElementModel>();
                for (CodeElementModel target : targets) {
                    members.addAll(SemanticAnalyzer.getSelectableMembers(target, name));
                }

                setTargetProperty(ctx, members);
                return members;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=2, dependents=Dependents.PARENTS),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0),
            })
            public Collection<? extends CodeElementModel> visitType(TypeContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.typeName() != null) {
                    result = visit(ctx.typeName());
                } else if (ctx.typeLiteral() != null) {
                    result = visit(ctx.typeLiteral());
                } else if (ctx.type() != null) {
                    result = visit(ctx.type());
                } else {
                    LOGGER.log(Level.WARNING, "Unknown type syntax.");
                    result = Collections.emptyList();
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeLiteral, version=0)
            public Collection<? extends CodeElementModel> visitTypeLiteral(TypeLiteralContext ctx) {
                assert ctx.getChildCount() <= 1 : "Unknown typeLiteral syntax.";
                Collection<? extends CodeElementModel> result = visit(ctx.getChild(0));
                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitArrayType(ArrayTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(visit(ctx.elementType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypeArrayModelImpl((TypeModelImpl)result.get(i)));
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitKeyType(KeyTypeContext ctx) {
                if (ctx.type() == null) {
                    return Collections.emptyList();
                }

                return visit(ctx.type());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitElementType(ElementTypeContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.type() != null) {
                    result = visit(ctx.type());
                } else {
                    result = Collections.emptyList();
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0)
            public Collection<? extends CodeElementModel> visitStructType(StructTypeContext ctx) {
                LOGGER.log(Level.WARNING, "Target resolution for context {0} is not implemented.", ctx.getClass());
                return Collections.emptyList();
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_pointerType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0),
            })
            public Collection<? extends CodeElementModel> visitPointerType(PointerTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.baseType() != null) {
                    result.addAll(visit(ctx.baseType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypePointerModelImpl((TypeModelImpl)result.get(i)));
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseType, version=0)
            public Collection<? extends CodeElementModel> visitBaseType(BaseTypeContext ctx) {
                if (ctx.getChildCount() != 1) {
                    LOGGER.log(Level.WARNING, "Unknown baseType syntax.");
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> result = visit(ctx.type());
                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitFunctionType(FunctionTypeContext ctx) {
                TypeFunctionModelImpl functionType = new TypeFunctionModelImpl("func", fileModel, ctx);

                List<ParameterModelImpl> parameters = functionType.getParameters();
                List<ParameterModelImpl> returnValues = functionType.getReturnValues();

                SignatureContext signatureContext = ctx.signature();
                if (signatureContext == null) {
                    return Collections.emptyList();
                }

                ParametersContext parametersContext = signatureContext.parameters();
                handleParameters(parametersContext, VarKind.PARAMETER, parameters);

                ResultContext resultContext = signatureContext.result();
                if (resultContext != null) {
                    parametersContext = resultContext.parameters();
                    if (parametersContext != null) {
                        handleParameters(parametersContext, VarKind.RETURN, returnValues);
                    } else {
                        TypeContext typeContext = resultContext.type();
                        if (typeContext != null) {
                            Collection<? extends CodeElementModel> resolved = visit(typeContext);

                            TypeModelImpl returnType;
                            if (resolved.size() == 1) {
                                returnType = (TypeModelImpl)resolved.iterator().next();
                            } else {
                                returnType = new UnknownTypeModelImpl(fileModel);
                            }

                            returnValues.add(new ParameterModelImpl("_", VarKind.RETURN, returnType, fileModel, ParseTrees.getStartNode(typeContext), typeContext));
                        }
                    }
                }

                functionType.freeze();
                return Collections.singletonList(functionType);
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
            })
            public Collection<? extends CodeElementModel> visitRangeClause(RangeClauseContext ctx) {
                if (ctx.e == null) {
                    return Collections.emptyList();
                }

                // this will be a bundled return type
                Collection<? extends CodeElementModel> expressionModel = visit(ctx.e);
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : expressionModel) {
                    for (TypeModelImpl type : resolveType(model, true, true)) {
                        if (type instanceof TypeArrayModelImpl || type instanceof TypeSliceModelImpl) {
                            TypeWrapperModelImpl wrapperModel = (TypeWrapperModelImpl)type;
                            result.add(new BundledReturnTypeModel(Arrays.asList((TypeModelImpl)IntrinsicTypeModels.INT, wrapperModel.getElementType())));
                        } else if (type instanceof TypeMapModelImpl) {
                            TypeMapModelImpl mapModel = (TypeMapModelImpl)type;
                            result.add(new BundledReturnTypeModel(Arrays.asList(mapModel.getKeyType(), mapModel.getValueType())));
                        } else if (IntrinsicTypeModels.STRING.equals(type)) {
                            result.add(new BundledReturnTypeModel(Arrays.asList((TypeModelImpl)IntrinsicTypeModels.INT, (TypeModelImpl)IntrinsicTypeModels.RUNE)));
                        } else if (type instanceof TypeChannelModelImpl) {
                            result.add(((TypeChannelModelImpl)type).getElementType());
                        } else {
                            LOGGER.log(Level.WARNING, "Unsupported type model {0} in range clause.", type.getClass());
                        }
                    }
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeCaseClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchCase, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeList, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
                if (ctx.expression() == null) {
                    return Collections.emptyList();
                }

                TypeSwitchStmtContext typeSwitchStmtContext = (TypeSwitchStmtContext)ctx.getParent();
                List<? extends TypeCaseClauseContext> typeCaseClauseContexts = typeSwitchStmtContext.typeCaseClause();
                if (typeCaseClauseContexts.isEmpty()) {
                    return visit(ctx.expression());
                }

                TypeCaseClauseContext lastContext = typeCaseClauseContexts.get(typeCaseClauseContexts.size() - 1);
                TypeSwitchCaseContext typeSwitchCaseContext = lastContext.typeSwitchCase();
                if (typeSwitchCaseContext == null) {
                    return visit(ctx.expression());
                }

                TypeListContext typeListContext = typeSwitchCaseContext.typeList();
                if (typeListContext == null || typeListContext.type().size() != 1) {
                    return visit(ctx.expression());
                }

                return visit(typeListContext.type(0));
            }

            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
            })
            private void handleParameters(ParametersContext parametersContext, VarKind kind, List<ParameterModelImpl> parameters) {
                ParameterListContext parameterListContext = parametersContext.parameterList();
                if (parameterListContext == null) {
                    return;
                }

                List<? extends ParameterDeclContext> parameterDeclContexts = parameterListContext.parameterDecl();
                for (ParameterDeclContext parameterDeclContext : parameterDeclContexts) {
                    TypeContext typeContext = parameterDeclContext.type();
                    Collection<? extends CodeElementModel> resolved = typeContext != null ? visit(typeContext) : Collections.<CodeElementModel>emptyList();

                    TypeModelImpl returnType;
                    if (resolved.size() == 1) {
                        returnType = (TypeModelImpl)resolved.iterator().next();
                    } else {
                        returnType = new UnknownTypeModelImpl(fileModel);
                    }

                    IdentifierListContext identifierListContext = parameterDeclContext.identifierList();
                    if (identifierListContext == null) {
                        continue;
                    }

                    for (TerminalNode<Token> identifier : identifierListContext.IDENTIFIER()) {
                        String name = identifier.getText();
                        if (name == null || name.isEmpty()) {
                            name = "_";
                        }

                        ParameterModelImpl parameter = new ParameterModelImpl(name, kind, returnType, fileModel, identifier, parameterDeclContext);
                        parameters.add(parameter);
                    }
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            })
            public Collection<? extends CodeElementModel> visitInterfaceType(InterfaceTypeContext ctx) {
                if (!ctx.methodSpec().isEmpty()) {
                    LOGGER.log(Level.WARNING, "Target resolution for non-empty anonymous interfaces is not implemented.");
                }

                return Collections.singletonList(new TypeInterfaceModelImpl("_", fileModel, ctx));
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitSliceType(SliceTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(visit(ctx.elementType()));
                }

                for (int i = result.size() - 1; i >= 0; i--) {
                    if (!(result.get(i) instanceof TypeModelImpl)) {
                        result.remove(i);
                        continue;
                    }

                    result.set(i, new TypeSliceModelImpl((TypeModelImpl)result.get(i)));
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_keyType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitMapType(MapTypeContext ctx) {
                Collection<? extends CodeElementModel> keyTypes = null;
                Collection<? extends CodeElementModel> valueTypes = null;
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();

                if (ctx.keyType() != null) {
                    keyTypes = visit(ctx.keyType());
                }

                if (ctx.elementType() != null) {
                    valueTypes = visit(ctx.elementType());
                }

                if (keyTypes == null || keyTypes.isEmpty()) {
                    keyTypes = Collections.singletonList(new UnknownTypeModelImpl(fileModel));
                }

                if (valueTypes == null || valueTypes.isEmpty()) {
                    valueTypes = Collections.singletonList(new UnknownTypeModelImpl(fileModel));
                }

                for (CodeElementModel keyTypeModel : keyTypes) {
                    for (TypeModelImpl keyType : resolveType(keyTypeModel, false, false)) {
                        for (CodeElementModel valueTypeModel : valueTypes) {
                            for (TypeModelImpl valueType : resolveType(valueTypeModel, false, false)) {
                                result.add(new TypeMapModelImpl(keyType, valueType));
                            }
                        }
                    }
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_channelType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitChannelType(ChannelTypeContext ctx) {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                if (ctx.elementType() != null) {
                    result.addAll(visit(ctx.elementType()));
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

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitLiteralType(LiteralTypeContext ctx) {
                if (ctx.elementType() != null) {
                    LOGGER.log(Level.WARNING, "TODO: resolve implicit array creation.");
                    return Collections.emptyList();
                }

                assert ctx.getChildCount() <= 1 : "Unknown literalType syntax.";
                Collection<? extends CodeElementModel> result = visit(ctx.getChild(0));
                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=3, dependents=Dependents.PARENTS)
            public Collection<? extends CodeElementModel> visitTypeName(TypeNameContext ctx) {
                assert ctx.getChildCount() <= 1 : "Unknown typeName syntax.";
                Collection<? extends CodeElementModel> result = visit(ctx.getChild(0));
                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
            })
            public Collection<? extends CodeElementModel> visitBaseTypeName(BaseTypeNameContext ctx) {
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

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
            })
            public Collection<? extends CodeElementModel> visitOperand(OperandContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.literal() != null) {
                    result = visit(ctx.literal());
                } else if (ctx.qualifiedIdentifier() != null) {
                    result = visit(ctx.qualifiedIdentifier());
                } else if (ctx.methodExpr() != null) {
                    result = visit(ctx.methodExpr());
                } else if (ctx.expression() != null) {
                    result = visit(ctx.expression());
                } else {
                    LOGGER.log(Level.WARNING, "TODO: unknown typeName syntax.");
                    result = Collections.emptyList();
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
            })
            public Collection<? extends CodeElementModel> visitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
                if (ctx.conversion() == null) {
                    return Collections.emptyList();
                }

                return visit(ctx.conversion());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_conversion, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitConversion(ConversionContext ctx) {
                if (ctx.type() == null) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> methodResults = visit(ctx.type());
                List<CodeElementModel> results = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : methodResults) {
                    if (model instanceof TypeModel) {
                        results.add(model);
                    } else if (model instanceof FunctionModel) {
                        // some calls look like conversions
                        Collection<? extends AbstractCodeElementModel> returnValues;
                        if (model instanceof FunctionModelImpl) {
                            returnValues = ((FunctionModelImpl)model).getReturnValues();
                        } else if (model instanceof TypeFunctionModelImpl) {
                            returnValues = ((TypeFunctionModelImpl)model).getReturnValues();
                        } else {
                            LOGGER.log(Level.WARNING, "Unsupported {0} implementation: {1}.", new Object[] { FunctionModel.class.getSimpleName(), model.getClass().getSimpleName() } );
                            continue;
                        }

                        if (returnValues.size() > 1) {
                            returnValues = Collections.singletonList(new BundledReturnTypeModel(returnValues));
                        }

                        results.addAll(returnValues);
                    }
                }

                setTargetProperty(ctx, results);
                return results;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitAndExpr(AndExprContext ctx) {
                return Collections.singletonList(IntrinsicTypeModels.BOOL);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitOrExpr(OrExprContext ctx) {
                return Collections.singletonList(IntrinsicTypeModels.BOOL);
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
            public Collection<? extends CodeElementModel> visitCallExpr(CallExprContext ctx) {
                if (ctx.expression() == null) {
                    return Collections.emptyList();
                }

                Collection<? extends CodeElementModel> methodResults = visit(ctx.expression());
                List<CodeElementModel> results = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : methodResults) {
                    Collection<? extends CodeElementModel> resolvedModels;
                    if (model instanceof VarModel) {
                        resolvedModels = resolveType(model, true, false);
                    } else {
                        resolvedModels = Collections.singletonList(model);
                    }

                    for (CodeElementModel resolvedModel : resolvedModels) {
                        if (resolvedModel instanceof FunctionModel) {
                            Collection<? extends AbstractCodeElementModel> returnValues;
                            if (resolvedModel instanceof FunctionModelImpl) {
                                returnValues = ((FunctionModelImpl)resolvedModel).getReturnValues();
                            } else if (resolvedModel instanceof TypeFunctionModelImpl) {
                                returnValues = ((TypeFunctionModelImpl)resolvedModel).getReturnValues();
                            } else {
                                LOGGER.log(Level.WARNING, "Unsupported {0} implementation: {1}.", new Object[] { FunctionModel.class.getSimpleName(), resolvedModel.getClass().getSimpleName() } );
                                continue;
                            }

                            if (returnValues.size() > 1) {
                                returnValues = Collections.singletonList(new BundledReturnTypeModel(returnValues));
                            }

                            results.addAll(returnValues);
                        } else if (model instanceof TypeModel) {
                            // conversion operations look like calls
                            results.add(resolvedModel);
                        } else {
                            LOGGER.log(Level.WARNING, "Unsupported target for call expression: {0}", resolvedModel.getClass());
                        }
                    }
                }

                setTargetProperty(ctx, results);
                return results;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
            })
            public Collection<? extends CodeElementModel> visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
                // first check for the "semi-special" literals
                if (ctx.packageName() == null && ctx.IDENTIFIER() != null) {
                    TerminalNode<Token> identifier = ctx.IDENTIFIER();
                    if ("true".equals(identifier.getText()) || "false".equals(identifier.getText())) {
                        return Collections.singletonList(IntrinsicTypeModels.BOOL);
                    } else if ("iota".equals(identifier.getText())) {
                        return Collections.singletonList(IntrinsicTypeModels.INT);
                    }
                }

                Collection<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>> vars = Collections.emptyList();
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
                        vars = new ArrayList<Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer>>();
                        vars.addAll(localsAnalyzer.getReceiverParameters(functionContext));
                        vars.addAll(localsAnalyzer.getParameters(functionContext));
                        vars.addAll(localsAnalyzer.getReturnParameters(functionContext));
                        vars.addAll(localsAnalyzer.getConstants(functionContext));
                        vars.addAll(localsAnalyzer.getLocals(functionContext));
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
                    for (Tuple3<TerminalNode<Token>, ParserRuleContext<Token>, Integer> entry : vars) {
                        if (ParseTrees.isAncestorOf(entry.getItem2(), nameNode)) {
                            // prevent stack overflow from redeclaration of a variable
                            continue;
                        }

                        if (!name.equals(entry.getItem1().getText())) {
                            continue;
                        }

                        Collection<? extends CodeElementModel> varTypes = visit(entry.getItem2());
                        ArrayList<CodeElementModel> unbundledTypes = new ArrayList<CodeElementModel>();
                        for (CodeElementModel varType : varTypes) {
                            if (varType instanceof BundledReturnTypeModel) {
                                List<? extends CodeElementModel> returnValues = ((BundledReturnTypeModel)varType).getReturnValues();
                                if (returnValues.size() > entry.getItem3()) {
                                    unbundledTypes.add(((BundledReturnTypeModel)varType).getReturnValues().get(entry.getItem3()));
                                }
                            } else if (entry.getItem3() == 0) {
                                unbundledTypes.add(varType);
                            }
                        }

                        varTypes = unbundledTypes;
                        if (varTypes.isEmpty()) {
                            varTypes = Collections.singleton(new UnknownTypeModelImpl(getFileModel()));
                        }

                        for (CodeElementModel unresolvedVarType : varTypes) {
                            for (TypeModelImpl varType : resolveType(unresolvedVarType, false, false)) {
                                // TODO: use proper var kind
                                VarModelImpl varModel = new VarModelImpl(name, VarKind.LOCAL, varType, getFileModel(), entry.getItem1(), entry.getItem2());
                                members.add(varModel);
                            }
                        }
                    }

                    if (members.isEmpty()) {
                        for (CodeElementModel model : contextModels) {
                            members.addAll(model.getMembers(name));
                        }
                    }
                }

                setTargetProperty(ctx, members);
                return members;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_type, version=0, dependents=Dependents.SELF),
            })
            public Collection<? extends CodeElementModel> visitTypeAssertionExpr(TypeAssertionExprContext ctx) {
                Collection<? extends CodeElementModel> types = visit(ctx.type());
                if (types.isEmpty()) {
                    return types;
                }

                List<CodeElementModel> assertionBundles = new ArrayList<CodeElementModel>();
                for (CodeElementModel model : types) {
                    if (model instanceof AbstractCodeElementModel) {
                        assertionBundles.add(new BundledReturnTypeModel(Arrays.asList((AbstractCodeElementModel)model, (AbstractCodeElementModel)IntrinsicTypeModels.BOOL)));
                    } else {
                        assertionBundles.add(model);
                    }
                }

                setTargetProperty(ctx, assertionBundles);
                return assertionBundles;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literal, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0),
            })
            public Collection<? extends CodeElementModel> visitLiteral(LiteralContext ctx) {
                Collection<? extends CodeElementModel> result;
                if (ctx.basicLiteral() != null) {
                    result = visit(ctx.basicLiteral());
                } else if (ctx.compositeLiteral() != null) {
                    result = visit(ctx.compositeLiteral());
                } else if (ctx.functionLiteral() != null) {
                    result = visit(ctx.functionLiteral());
                } else {
                    result = Collections.emptyList();
                }

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_basicLiteral, version=0)
            public Collection<? extends CodeElementModel> visitBasicLiteral(BasicLiteralContext ctx) {
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

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_compositeLiteral, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_literalType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_arrayType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sliceType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_mapType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeName, version=0, dependents=Dependents.SELF),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_elementType, version=0),
            })
            public Collection<? extends CodeElementModel> visitCompositeLiteral(CompositeLiteralContext ctx) {
                Collection<? extends CodeElementModel> result;
                LiteralTypeContext literalType = ctx.literalType();
                if (literalType != null) {
                    if (literalType.structType() != null) {
                        result = visit(literalType.structType());
                    } else if (literalType.arrayType() != null) {
                        result = visit(literalType.arrayType());
                    } else if (literalType.sliceType() != null) {
                        result = visit(literalType.sliceType());
                    } else if (literalType.mapType() != null) {
                        result = visit(literalType.mapType());
                    } else if (literalType.typeName() != null) {
                        result = visit(literalType.typeName());
                    } else if (literalType.elementType() != null) {
                        List<CodeElementModel> arrayElementType = new ArrayList<CodeElementModel>();
                        arrayElementType.addAll(visit(literalType.elementType()));
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

                setTargetProperty(ctx, result);
                return result;
            }

            @Override
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionLiteral, version=0)
            public Collection<? extends CodeElementModel> visitFunctionLiteral(FunctionLiteralContext ctx) {
                return Collections.emptyList();
            }

            @NonNull
            private Collection<? extends TypeModelImpl> resolveType(@NonNull CodeElementModel model, boolean lookThroughAliases, boolean dereferenceArrayPointers) {
                TypeModelImpl type;
                if (model instanceof TypeModelImpl) {
                    type = (TypeModelImpl)model;
                } else if (model instanceof VarModelImpl) {
                    type = ((VarModelImpl)model).getVarType();
                } else {
                    LOGGER.log(Level.WARNING, "Cannot resolve target type from model {0}.", model.getClass());
                    return Collections.emptyList();
                }

                Collection<? extends TypeModelImpl> resolved = type.resolve();
                if (lookThroughAliases) {
                    List<TypeModelImpl> result = new ArrayList<TypeModelImpl>();
                    for (TypeModelImpl typeModelImpl : resolved) {
                        if (typeModelImpl instanceof TypeAliasModel) {
                            result.addAll(resolveType(((TypeAliasModel)typeModelImpl).getType(), lookThroughAliases, dereferenceArrayPointers));
                        } else {
                            result.add(typeModelImpl);
                        }
                    }

                    resolved = result;
                }

                if (dereferenceArrayPointers) {
                    List<TypeModelImpl> result = new ArrayList<TypeModelImpl>();
                    for (TypeModelImpl typeModelImpl : resolved) {
                        if (typeModelImpl instanceof TypePointerModel) {
                            Collection<? extends TypeModelImpl> dereferences = resolveType(((TypePointerModel)typeModelImpl).getElementType(), lookThroughAliases, false);
                            for (TypeModelImpl dereference : dereferences) {
                                if (dereference instanceof TypeArrayModel) {
                                    result.add(dereference);
                                }
                            }
                        } else {
                            result.add(typeModelImpl);
                        }
                    }

                    resolved = result;
                }

                return resolved;
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

                    TerminalNode<Token> unqualifiedLink = treeDecorator.getProperty(qualifier, GoAnnotations.UNQUALIFIED_LINK);
                    if (unqualifiedLink != null) {
                        qualifierNodeType = treeDecorator.getProperty(qualifier, GoAnnotations.NODE_TYPE);
                        if (qualifierNodeType == NodeType.UNDEFINED) {
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
                    TerminalNode<Token> target = treeDecorator.getProperty(qualifier, GoAnnotations.LOCAL_TARGET);
                    assert target != null && treeDecorator.getProperty(target, GoAnnotations.NODE_TYPE) == NodeType.VAR_DECL;
                    ParserRuleContext<Token> explicitType = treeDecorator.getProperty(qualifier, GoAnnotations.EXPLICIT_TYPE);
                    if (explicitType == null && target != null) {
                        explicitType = treeDecorator.getProperty(target, GoAnnotations.EXPLICIT_TYPE);
                    }
                    if (explicitType != null) {
                        LOGGER.log(Level.WARNING, "Unable to resolve explicit type for qualifier: {0}", qualifier);
                        resolvedQualifier = Collections.emptyList();
                    } else {
                        ParserRuleContext<Token> implicitType = target != null ? treeDecorator.getProperty(target, GoAnnotations.IMPLICIT_TYPE) : null;
                        int implicitIndex = target != null ? treeDecorator.getProperty(target, GoAnnotations.IMPLICIT_INDEX) : -1;
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

    private static class ScopeContextVisitor extends GoParserBaseVisitor<ParseTree<Token>> {
        public static ScopeContextVisitor INSTANCE = new ScopeContextVisitor();
        private static ScopeContextVisitorHelper HELPER_INSTANCE = new ScopeContextVisitorHelper();

        protected ScopeContextVisitor() {
        }

        @Override
        public ParseTree<Token> visitTerminal(TerminalNode<? extends Token> node) {
            return visit(node.getParent());
        }

        @Override
        protected ParseTree<Token> defaultResult() {
            throw new UnsupportedOperationException();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_baseTypeName, version=0)
        public ParseTree<Token> visitBaseTypeName(BaseTypeNameContext ctx) {
            // this is not a declaration
            return null;
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_builtinCall, version=0)
        public ParseTree<Token> visitBuiltinCall(BuiltinCallContext ctx) {
            // this is not a declaration
            return null;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
        })
        public ParseTree<Token> visitFunctionDecl(FunctionDeclContext ctx) {
            return HELPER_INSTANCE.visit((TopLevelDeclContext)ctx.getParent());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_identifierList, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_constDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_fieldDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_structType, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_varDecl, version=0),
        })
        public ParseTree<Token> visitIdentifierList(IdentifierListContext ctx) {
            switch (ctx.getParent().getRuleIndex()) {
            case GoParser.RULE_constSpec:
                ConstDeclContext constDeclContext = (ConstDeclContext)((ConstSpecContext)ctx.getParent()).getParent();
                return HELPER_INSTANCE.visit((DeclarationContext)constDeclContext.getParent());

            case GoParser.RULE_fieldDecl:
                return (StructTypeContext)((FieldDeclContext)ctx.getParent()).getParent();

            case GoParser.RULE_parameterDecl:
                return HELPER_INSTANCE.visit((ParameterDeclContext)ctx.getParent());

            case GoParser.RULE_shortVarDecl:
                return HELPER_INSTANCE.visit((ShortVarDeclContext)ctx.getParent());

            case GoParser.RULE_varSpec:
                VarSpecContext varSpecContext = (VarSpecContext)ctx.getParent();
                VarDeclContext varDeclContext = (VarDeclContext)varSpecContext.getParent();
                return HELPER_INSTANCE.visit((DeclarationContext)varDeclContext.getParent());

            default:
                throw new UnsupportedOperationException();
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_label, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0),
        })
        public ParseTree<Token> visitLabel(LabelContext ctx) {
            if (ctx.getParent().getRuleIndex() != GoParser.RULE_labeledStmt) {
                // not a declaration
                return null;
            }

            return HELPER_INSTANCE.visit((LabeledStmtContext)ctx.getParent());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodExpr, version=0),
        })
        public ParseTree<Token> visitMethodName(MethodNameContext ctx) {
            switch (ctx.getParent().getRuleIndex()) {
            case GoParser.RULE_methodDecl:
                return HELPER_INSTANCE.visit((MethodDeclContext)ctx.getParent());

            case GoParser.RULE_methodSpec:
                return HELPER_INSTANCE.visit((MethodSpecContext)ctx.getParent());

            case GoParser.RULE_methodExpr:
                // this isn't a declaration
                return null;

            default:
                throw new UnsupportedOperationException();
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageName, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importDecl, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_packageClause, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
        })
        public ParseTree<Token> visitPackageName(PackageNameContext ctx) {
            switch (ctx.getParent().getRuleIndex()) {
            case GoParser.RULE_importSpec:
                ImportSpecContext importSpecContext = (ImportSpecContext)ctx.getParent();
                ImportDeclContext importDeclContext = (ImportDeclContext)importSpecContext.getParent();
                return (SourceFileBodyContext)importDeclContext.getParent();

            case GoParser.RULE_packageClause:
                return (SourceFileBodyContext)((PackageClauseContext)ctx.getParent()).getParent();

            case GoParser.RULE_qualifiedIdentifier:
                // not a declaration
                return null;

            default:
                throw new UnsupportedOperationException();
            }
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_qualifiedIdentifier, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_operand, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_rangeClause, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forStmt, version=0),
        })
        public ParseTree<Token> visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
            if (ctx.getParent().getRuleIndex() != GoParser.RULE_operand) {
                // not a declaration
                return null;
            }

            OperandContext operandContext = (OperandContext)ctx.getParent();
            OperandExprContext operandExprContext = (OperandExprContext)operandContext.getParent();
            if (operandExprContext.getParent().getRuleIndex() != GoParser.RULE_rangeClause) {
                // not a declaration
                return null;
            }

            RangeClauseContext rangeClauseContext = (RangeClauseContext)operandExprContext.getParent();
            // just check eq... both operators missing is an ambig situation
            if (rangeClauseContext.eq != null) {
                // not a declaration
                return null;
            }

            if (operandExprContext != rangeClauseContext.e1 && operandExprContext != rangeClauseContext.e2) {
                // not a declaration
                return null;
            }

            return (ForStmtContext)rangeClauseContext.getParent();
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_receiver, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
        })
        public ParseTree<Token> visitReceiver(ReceiverContext ctx) {
            return (MethodDeclContext)ctx.getParent();
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_expression, version=1)
        public ParseTree<Token> visitSelectorExpr(SelectorExprContext ctx) {
            // this isn't a declaration
            return null;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSpec, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeDecl, version=0),
        })
        public ParseTree<Token> visitTypeSpec(TypeSpecContext ctx) {
            return HELPER_INSTANCE.visit((TypeDeclContext)ctx.getParent());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchGuard, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0),
        })
        public ParseTree<Token> visitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
            return (TypeSwitchStmtContext)ctx.getParent();
        }

        private static class ScopeContextVisitorHelper extends ScopeContextVisitor {

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_sourceFileBody, version=1),
            })
            public ParseTree<Token> visitTopLevelDecl(TopLevelDeclContext ctx) {
                return (SourceFileBodyContext)ctx.getParent();
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
            })
            public ParseTree<Token> visitMethodDecl(MethodDeclContext ctx) {
                return visit((TopLevelDeclContext)ctx.getParent());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_interfaceType, version=0),
            })
            public ParseTree<Token> visitMethodSpec(MethodSpecContext ctx) {
                return (InterfaceTypeContext)ctx.getParent();
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0),
            })
            public ParseTree<Token> visitTypeDecl(TypeDeclContext ctx) {
                return visit((DeclarationContext)ctx.getParent());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_declaration, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0),
            })
            public ParseTree<Token> visitDeclaration(DeclarationContext ctx) {
                switch (ctx.getParent().getRuleIndex()) {
                case GoParser.RULE_topLevelDecl:
                    return visit((TopLevelDeclContext)ctx.getParent());

                case GoParser.RULE_statement:
                    return visit((StatementContext)ctx.getParent());

                default:
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_shortVarDecl, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_simpleStmt, version=1),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_ifStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_initStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_forStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_postStmt, version=0),
            })
            public ParseTree<Token> visitShortVarDecl(ShortVarDeclContext ctx) {
                SimpleStmtContext simpleStmtContext = (SimpleStmtContext)ctx.getParent();
                switch (simpleStmtContext.getParent().getRuleIndex()) {
                case GoParser.RULE_statement:
                    return HELPER_INSTANCE.visit((StatementContext)simpleStmtContext.getParent());

                case GoParser.RULE_ifStmt:
                    return (IfStmtContext)simpleStmtContext.getParent();

                case GoParser.RULE_exprSwitchStmt:
                    return (ExprSwitchStmtContext)simpleStmtContext.getParent();

                case GoParser.RULE_typeSwitchStmt:
                    return (TypeSwitchStmtContext)simpleStmtContext.getParent();

                case GoParser.RULE_initStmt:
                    InitStmtContext initStmtContext = (InitStmtContext)simpleStmtContext.getParent();
                    ForClauseContext forClauseContext = (ForClauseContext)initStmtContext.getParent();
                    return (ForStmtContext)forClauseContext.getParent();

                case GoParser.RULE_postStmt:
                    PostStmtContext postStmtContext = (PostStmtContext)simpleStmtContext.getParent();
                    forClauseContext = (ForClauseContext)postStmtContext.getParent();
                    return (ForStmtContext)forClauseContext.getParent();

                default:
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameterList, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_parameters, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_signature, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_result, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionType, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodSpec, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_functionDecl, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_methodDecl, version=0),
            })
            public ParseTree<Token> visitParameterDecl(ParameterDeclContext ctx) {
                ParameterListContext parameterListContext = (ParameterListContext)ctx.getParent();
                ParametersContext parametersContext = (ParametersContext)parameterListContext.getParent();

                SignatureContext signatureContext;
                if (parametersContext.getParent().getRuleIndex() == GoParser.RULE_signature) {
                    signatureContext = (SignatureContext)parametersContext.getParent();
                } else {
                    ResultContext resultContext = (ResultContext)parametersContext.getParent();
                    signatureContext = (SignatureContext)resultContext.getParent();
                }

                switch (signatureContext.getParent().getRuleIndex()) {
                case GoParser.RULE_functionType:
                    return (FunctionTypeContext)signatureContext.getParent();

                case GoParser.RULE_methodSpec:
                    return (MethodSpecContext)signatureContext.getParent();

                case GoParser.RULE_functionDecl:
                    return (FunctionDeclContext)signatureContext.getParent();

                case GoParser.RULE_methodDecl:
                    return (MethodDeclContext)signatureContext.getParent();

                default:
                    throw new UnsupportedOperationException();
                }
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0),
            })
            public ParseTree<Token> visitLabeledStmt(LabeledStmtContext ctx) {
                return visit((StatementContext)ctx.getParent());
            }

            @Override
            @RuleDependencies({
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_statement, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_block, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_labeledStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_commClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_selectStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprCaseClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_exprSwitchStmt, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeCaseClause, version=0),
                @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_typeSwitchStmt, version=0),
            })
            public ParseTree<Token> visitStatement(StatementContext ctx) {
                switch (ctx.getParent().getRuleIndex()) {
                case GoParser.RULE_block:
                    return (BlockContext)ctx.getParent();

                case GoParser.RULE_labeledStmt:
                    return visit((LabeledStmtContext)ctx.getParent());

                case GoParser.RULE_commClause:
                    return (SelectStmtContext)((CommClauseContext)ctx.getParent()).getParent();

                case GoParser.RULE_exprCaseClause:
                    return (ExprSwitchStmtContext)((ExprCaseClauseContext)ctx.getParent()).getParent();

                case GoParser.RULE_typeCaseClause:
                    return (TypeSwitchStmtContext)((TypeCaseClauseContext)ctx.getParent()).getParent();

                default:
                    throw new UnsupportedOperationException();
                }
            }

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
