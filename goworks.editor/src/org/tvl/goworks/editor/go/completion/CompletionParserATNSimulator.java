/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.antlr4.completion.AbstractCompletionParserATNSimulator;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
public class CompletionParserATNSimulator extends AbstractCompletionParserATNSimulator {
    private static final SemanticContext qidPredicate = new SemanticContext.Predicate(GoParser.RULE_qualifiedIdentifier, 0, false);

    private final int QID_DECISION;

    public CompletionParserATNSimulator(@NonNull Parser<Token> parser, ATN atn) {
        super(parser, atn);
        disable_global_context = true;
        ATNState decisionState = atn.ruleToStartState[GoParser.RULE_qualifiedIdentifier].transition(0).target;
        if (decisionState instanceof DecisionState) {
            QID_DECISION = ((DecisionState)decisionState).decision;
        } else {
            QID_DECISION = -1;
        }
    }

    public static final IntervalSet WORDLIKE_TOKEN_TYPES =
        new IntervalSet() {{
            // keywords
            addAll(KeywordCompletionItem.KEYWORD_TYPES);
            // identifiers
            add(GoParser.IDENTIFIER);
        }};

    @Override
    protected IntervalSet getWordlikeTokenTypes() {
        return WORDLIKE_TOKEN_TYPES;
    }

    @Override
    public int adaptivePredict(TokenStream<? extends Token> input, int decision, ParserRuleContext<Token> outerContext) {
        if (decision == QID_DECISION && QID_DECISION >= 0) {
            if (input.LA(1) == GoParser.IDENTIFIER) {
                if (input.LA(2) == GoParser.Dot) {
                    if (input.LA(3) == GoParser.IDENTIFIER) {
                        return qidPredicate.eval(parser, outerContext) ? 1 : 2;
                    } else if (input.LA(3) != CaretToken.CARET_TOKEN_TYPE) {
                        return 2;
                    }
                } else if (input.LA(2) != CaretToken.CARET_TOKEN_TYPE) {
                    return 2;
                }
            }
        }

        return super.adaptivePredict(input, decision, outerContext);
    }

}
