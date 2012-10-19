/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.DecisionState;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;

/**
 *
 * @author Sam Harwell
 */
public class GoParserCache extends AbstractParserCache<Token, GoParser> {
    public static final GoParserCache DEFAULT = new GoParserCache();

    @Override
    protected GoParser createParser(TokenStream<? extends Token> input) {
        GoParser parser = new GoParserWrapper(input);
        return parser;
    }

    @Override
    public GoParser getParser(TokenStream<? extends Token> input) {
        GoParser result = super.getParser(input);
        configureParser(result, ParserConfiguration.PRECISE);
        return result;
    }

    public GoParser getParser(TokenStream<? extends Token> input, ParserConfiguration configuration) {
        GoParser result = super.getParser(input);
        configureParser(result, configuration);
        return result;
    }

    private static void configureParser(Parser<? extends Token> parser, ParserConfiguration configuration) {
        ParserATNSimulator<?> interpreter = parser.getInterpreter();

        // common configuration
        interpreter.force_global_context = false;
        interpreter.always_try_local_context = true;
        interpreter.optimize_hidden_conflicted_configs = true;
        interpreter.optimize_tail_calls = true;
        parser.setBuildParseTree(true);
        parser.removeErrorListeners();

        switch (configuration) {
        case FASTEST:
            interpreter.disable_global_context = true;
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = true;
            parser.setErrorHandler(new BailErrorStrategy<Token>());
            break;

        case SLL:
            throw new UnsupportedOperationException("The tail_call_preserves_sll flag cannot change within a single ATN instance.");
            //interpreter.disable_global_context = true;
            //interpreter.tail_call_preserves_sll = true;
            //interpreter.treat_sllk1_conflict_as_ambiguity = true;
            //parser.setErrorHandler(new BailErrorStrategy<Token>());
            //break;

        case HYBRID:
            interpreter.disable_global_context = false;
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = true;
            parser.setErrorHandler(new BailErrorStrategy<Token>());
            break;

        case HYBRID_SLL:
            throw new UnsupportedOperationException("The tail_call_preserves_sll flag cannot change within a single ATN instance.");
            //interpreter.disable_global_context = false;
            //interpreter.tail_call_preserves_sll = true;
            //interpreter.treat_sllk1_conflict_as_ambiguity = true;
            //parser.setErrorHandler(new BailErrorStrategy<Token>());
            //break;

        case PRECISE:
            interpreter.disable_global_context = false;
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = false;
            parser.setErrorHandler(new DefaultErrorStrategy<Token>());
            parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
            break;

        default:
            throw new IllegalArgumentException("Invalid configuration.");
        }
    }

    private final class GoParserWrapper extends GoParser {

        public GoParserWrapper(TokenStream<? extends Token> input) {
            super(input);
            _interp = new GoParserATNSimulator(this, _ATN);
        }

    }

    protected static class GoParserATNSimulator extends ParserATNSimulator<Token> {

        private static final SemanticContext.Predicate qidPredicate = new SemanticContext.Predicate(GoParser.RULE_qualifiedIdentifier, 0, false);

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
