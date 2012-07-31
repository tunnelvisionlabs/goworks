/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.BitSet;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfig;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.completion.CaretToken;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractGoParserCache extends AbstractParserCache<Token, GoParser> {

    protected static abstract class AbstractGoParserATNSimulator extends ParserATNSimulator<Token> {

        private static final SemanticContext.Predicate qidPredicate = new SemanticContext.Predicate(GoParser.RULE_qualifiedIdentifier, 0, false);

        private final int QID_DECISION;

        public AbstractGoParserATNSimulator(Parser<Token> parser, ATN atn) {
            super(parser, atn);
            ATNState decisionState = atn.ruleToStartState[GoParser.RULE_qualifiedIdentifier].transition(0).target;
            if (decisionState instanceof DecisionState) {
                QID_DECISION = ((DecisionState)decisionState).decision;
            } else {
                QID_DECISION = -1;
            }
        }

        @Override
        public int adaptivePredict(TokenStream<? extends Token> input, int decision, ParserRuleContext<Token> outerContext) {
            if (decision == QID_DECISION && QID_DECISION >= 0) {
                if (input.LA(1) == GoParser.IDENTIFIER) {
                    if (input.LA(2) == GoParser.Dot) {
                        if (input.LA(3) == GoParser.IDENTIFIER) {
                            return qidPredicate.eval(parser, outerContext) ? 1 : 2;
                        } else {
                            assert input.LA(3) != CaretToken.CARET_TOKEN_TYPE;
                            return 2;
                        }
                    } else {
                        assert input.LA(2) != CaretToken.CARET_TOKEN_TYPE;
                        return 2;
                    }
                }
            }

            return super.adaptivePredict(input, decision, outerContext);
        }

    }

}
