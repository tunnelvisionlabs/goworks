/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser;
import org.tvl.goworks.editor.go.parser.generated.GoParserBaseVisitor;

/**
 *
 * @author Sam Harwell
 */
public class GoParser extends AbstractGoParser {
	private final Set<String> packageNames = new HashSet<>();
    private DocumentSnapshot snapshot;

    public GoParser(TokenStream<? extends Token> input) {
        super(input);
        CharStream charStream = input != null ? input.getTokenSource().getInputStream() : null;
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        }
    }

    @Override
    public void reset() {
        super.reset();

        // must check for null because reset() is called from the Parser constructor, before fields of GoParser are initialized
        if (packageNames != null) {
            packageNames.clear();
        }
    }

    @Override
    public void setInputStream(TokenStream<? extends Token> input) {
        super.setInputStream(input);

        CharStream charStream = input != null ? input.getTokenSource().getInputStream() : null;
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        } else {
            this.snapshot = null;
        }
    }

    @Override
    protected void addPackageName(Token token) {
        String name = getPackageName(token);
        if (name == null || name.isEmpty()) {
            return;
        }

        packageNames.add(name);
    }

    @Override
    protected boolean isPackageName(Token token) {
        return token != null && packageNames.contains(token.getText());
    }

    public Collection<? extends String> getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(Collection<? extends String> names) {
        packageNames.clear();
        packageNames.addAll(names);
    }

    @RuleDependency(recognizer=GoParser.class, rule=RULE_operand, version=0)
    @Override
    protected boolean isLiteralAllowed(Token nextToken, OperandContext context) {
        if (nextToken.getType() != IDENTIFIER) {
            return true;
        }

        for (ParserRuleContext<Token> current = context; current != null; current = current.getParent()) {
            Boolean allowed = CompositeLiteralAllowedVisitor.INSTANCE.visit(current);
            if (allowed == null) {
                continue;
            }

            return allowed;
        }

        return true;
    }

    @Override
    protected boolean isBuiltInMethodName(Token token) {
        return token != null && SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(token.getText());
    }

    protected static final class CompositeLiteralAllowedVisitor extends GoParserBaseVisitor<Boolean> {
        protected static final CompositeLiteralAllowedVisitor INSTANCE = new CompositeLiteralAllowedVisitor();

        @RuleDependency(recognizer=GoParser.class, rule=RULE_expression, version=1)
        @Override
        public Boolean visitChildren(RuleNode<? extends Token> node) {
            RuleContext<? extends Token> ruleContext = node.getRuleContext();
            if (ruleContext instanceof ExpressionContext) {
                return visitExpression((ExpressionContext)ruleContext);
            }

            return super.visitChildren(node);
        }

        @Override
        protected Boolean defaultResult() {
            return null;
        }

        @Override
        protected boolean shouldVisitNextChild(RuleNode<? extends Token> node, Boolean currentResult) {
            return false;
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GoParser.class, rule=RULE_expression, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_exprSwitchStmt, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_arrayLength, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_elementNameOrIndex, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_channel, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_incDecStmt, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_ifStmt, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_condition, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_rangeClause, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_recvStmt, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_recvExpr, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_deferStmt, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_expressionList, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_operand, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_value, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_conversion, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_expressionStmt, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_sendStmt, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_simpleStmt, version=1),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_typeSwitchGuard, version=0),
            @RuleDependency(recognizer=GoParser.class, rule=RULE_goStmt, version=0),
        })
        private Boolean visitExpression(ExpressionContext ctx) {
            ParserRuleContext<Token> parent = ctx.getParent();
            if (parent == null) {
                return null;
            }

            switch (parent.getRuleIndex()) {
            case RULE_exprSwitchStmt:
            case RULE_arrayLength:
            case RULE_channel:
            case RULE_incDecStmt:
            case RULE_ifStmt:
            case RULE_condition:
            case RULE_rangeClause:
            case RULE_recvStmt:
            case RULE_recvExpr:
            case RULE_deferStmt:
                return false;

            case RULE_elementNameOrIndex:
            case RULE_expressionList:
            case RULE_operand:
            case RULE_value:
            case RULE_conversion:
            case RULE_expressionStmt:
            case RULE_sendStmt:
            case RULE_typeSwitchGuard:
            case RULE_goStmt:
                return true;

            // this one is needed due to explicit left factoring of expression
            // into simpleStmt, and this is called before the parse tree is
            // restored to the intended shape
            case RULE_simpleStmt:
                return true;

            case RULE_expression:
                if (parent instanceof SelectorExprContext
                    || parent instanceof IndexExprContext
                    || parent instanceof SliceExprContext
                    || parent instanceof TypeAssertionExprContext) {
                    return true;
                } else if (parent instanceof UnaryExprContext) {
                    UnaryExprContext unaryParent = (UnaryExprContext)parent;
                    if (unaryParent.op != null) {
                        switch (unaryParent.op.getType()) {
                        case Amp:
                            // address of, but still has to be in a valid context
                            return null;

                        default:
                            return false;
                        }
                    }

                    // not building parse trees, assume was a valid op
                    return true;
                } else if (parent instanceof CompareExprContext) {
                    CompareExprContext compareParent = (CompareExprContext)parent;
                    if (compareParent.op != null) {
                        switch (compareParent.op.getType()) {
                        case EqualEqual:
                        case BangEqual:
                            // equality comparison is allowed, but still has to be in a valid context
                            return null;

                        default:
                            return false;
                        }
                    }

                    // not building parse trees, assume was a valid op
                    return true;
                }

                return false;
            }

            return null;
        }
    }
}
