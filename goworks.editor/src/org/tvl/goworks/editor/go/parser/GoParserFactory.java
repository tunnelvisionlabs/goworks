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
import org.antlr.v4.runtime.atn.PredicateTransition;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.works.editor.antlr4.completion.CaretToken;
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class GoParserFactory {
    public static final GoParserFactory DEFAULT = new GoParserFactory();

    @NonNull
    protected GoParser createParser(@NonNull TokenStream<? extends Token> input) {
        GoParser parser = new GoParserWrapper(input);
        return parser;
    }

    @NonNull
    public final GoParser getParser(@NonNull TokenStream<? extends Token> input) {
        return getParser(input, ParserConfiguration.PRECISE);
    }

    @NonNull
    public GoParser getParser(@NonNull TokenStream<? extends Token> input, @NonNull ParserConfiguration configuration) {
        GoParser result = createParser(input);
        configureParser(result, configuration);
        return result;
    }

    protected void configureParser(@NonNull Parser<? extends Token> parser, @NonNull ParserConfiguration configuration) {
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
            interpreter.setPredictionMode(PredictionMode.SLL);
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = true;
            parser.setErrorHandler(new BailErrorStrategy<>());
            break;

        case SLL:
            throw new UnsupportedOperationException("The tail_call_preserves_sll flag cannot change within a single ATN instance.");
            //interpreter.setPredictionMode(PredictionMode.SLL);
            //interpreter.tail_call_preserves_sll = true;
            //interpreter.treat_sllk1_conflict_as_ambiguity = true;
            //parser.setErrorHandler(new BailErrorStrategy<Token>());
            //break;

        case HYBRID:
            interpreter.setPredictionMode(PredictionMode.LL);
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = true;
            parser.setErrorHandler(new BailErrorStrategy<>());
            break;

        case HYBRID_SLL:
            throw new UnsupportedOperationException("The tail_call_preserves_sll flag cannot change within a single ATN instance.");
            //interpreter.setPredictionMode(PredictionMode.LL);
            //interpreter.tail_call_preserves_sll = true;
            //interpreter.treat_sllk1_conflict_as_ambiguity = true;
            //parser.setErrorHandler(new BailErrorStrategy<Token>());
            //break;

        case PRECISE:
            interpreter.setPredictionMode(PredictionMode.LL);
            interpreter.tail_call_preserves_sll = false;
            interpreter.treat_sllk1_conflict_as_ambiguity = false;
            parser.setErrorHandler(new DefaultErrorStrategy<>());
            parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
            break;

        default:
            throw new IllegalArgumentException("Invalid configuration.");
        }
    }

    public static int getQidDecision(@NonNull ATN atn) {
        ATNState decisionState = atn.ruleToStartState[GoParser.RULE_qualifiedIdentifier].transition(0).target;
        if (decisionState instanceof DecisionState) {
            return ((DecisionState)decisionState).decision;
        } else {
            return -1;
        }
    }

    public static SemanticContext.Predicate getQidPredicate(@NonNull ATN atn) {
        int predicateIndex = -1;
        for (ATNState state : atn.states) {
            if (state.ruleIndex != GoParser.RULE_qualifiedIdentifier) {
                continue;
            }

            for (int i = 0; i < state.getNumberOfOptimizedTransitions(); i++) {
                Transition transition = state.getOptimizedTransition(i);
                if (transition instanceof PredicateTransition) {
                    predicateIndex = ((PredicateTransition)transition).predIndex;
                }
            }
        }

        assert predicateIndex >= 0 : "Couldn't find the QID predicate transition.";
        return new SemanticContext.Predicate(GoParser.RULE_qualifiedIdentifier, predicateIndex, false);
    }

    private static final class GoParserWrapper extends GoParser {

        public GoParserWrapper(TokenStream<? extends Token> input) {
            super(input);
            _interp = new GoParserATNSimulator(this, _ATN);
        }

    }

    protected static class GoParserATNSimulator extends ParserATNSimulator<Token> {

        private final SemanticContext.Predicate qidPredicate;

        private final int QID_DECISION;

        public GoParserATNSimulator(Parser<Token> parser, ATN atn) {
            super(parser, atn);
            QID_DECISION = getQidDecision(atn);
            qidPredicate = getQidPredicate(atn);
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
