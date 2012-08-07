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
import org.antlr.works.editor.antlr4.parsing.DescriptiveErrorListener;

/**
 *
 * @author Sam Harwell
 */
public class GoFullContextParserCache extends AbstractGoParserCache {

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

        result.removeErrorListeners();
        result.addErrorListener(DescriptiveErrorListener.INSTANCE);

        return result;
    }

    private final class GoParserWrapper extends GoParser {

        public GoParserWrapper(TokenStream<? extends Token> input) {
            super(input);
            _interp = new GoParserATNSimulator(this, _ATN);
        }

    }

    private static final class GoParserATNSimulator extends AbstractGoParserATNSimulator {
        public GoParserATNSimulator(Parser<Token> parser, ATN atn) {
            super(parser, atn);
        }

        @Override
        public int adaptivePredict(TokenStream<? extends Token> input, int decision, ParserRuleContext<Token> outerContext) {
            assert !disable_global_context && force_global_context;
            return super.adaptivePredict(input, decision, outerContext);
        }
    }

}
