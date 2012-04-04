/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.DefaultErrorStrategy;
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
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.completion.CaretToken;

/**
 *
 * @author Sam Harwell
 */
public class GoFullContextParserCache extends AbstractParserCache<Token, GoParser> {

    public static final GoFullContextParserCache DEFAULT = new GoFullContextParserCache();

    @Override
    protected GoParser createParser(TokenStream<? extends Token> input) {
        GoParser parser = new GoParserWrapper(input);
        return parser;
    }

    @Override
    public GoParser getParser(TokenStream<? extends Token> input) {
        GoParser result = super.getParser(input);
        result.setBuildParseTree(false);
        result.setErrorHandler(new DefaultErrorStrategy<Token>());
        result.getInterpreter().disable_global_context = false;
        result.getInterpreter().always_try_local_context = false;
        result.getInterpreter().force_global_context = true;
        return result;
    }

    private final class GoParserWrapper extends GoParser {

        public GoParserWrapper(TokenStream<? extends Token> input) {
            super(input);
            _interp = new GoParserATNSimulator(this, _ATN);
        }

    }

    private static final class GoParserATNSimulator extends ParserATNSimulator<Token> {
        private final int QID_DECISION;

        public GoParserATNSimulator(Parser<Token> parser, ATN atn) {
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
            assert !disable_global_context && force_global_context;

            if (decision == QID_DECISION && QID_DECISION >= 0) {
                if (input.LA(1) == GoParser.IDENTIFIER) {
                    if (input.LA(2) == GoParser.Dot) {
                        if (input.LA(3) == GoParser.IDENTIFIER) {
                            return parser.sempred(outerContext, GoParser.RULE_qualifiedIdentifier, 0) ? 1 : 2;
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

        @Override
        public IntervalSet getConflictingAlts(ATNConfigSet configs) {
            IntervalSet result = super.getConflictingAlts(configs);
            if (result != null) {
                // (workaround) make sure the result contains all possible configs or premature resolution could occur
                for (ATNConfig config : configs) {
                    if (!result.contains(config.alt)) {
                        return null;
                    }
                }
            }

            return result;
        }

    }

}
