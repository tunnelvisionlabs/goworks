/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighterLexerWrapper implements TokenSourceWithStateV4<Token, GoHighlighterLexerState> {
    private static final Map<GoHighlighterLexerState, GoHighlighterLexerState> sharedStates =
        new HashMap<GoHighlighterLexerState, GoHighlighterLexerState>();

    private final GoHighlighterLexer lexer;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GoHighlighterLexerWrapper(CharStream input, GoHighlighterLexerState state) {
        this.lexer = new GoHighlighterLexer(input);
        setState(input, state);
    }

    public GoHighlighterLexer getLexer() {
        return lexer;
    }

    @Override
    public String getSourceName() {
        return "Grammar Highlighter";
    }

    @Override
    public GoHighlighterLexerState getCurrentState() {
        if (lexer._modeStack == null) {
            return getCachedState(lexer._mode, null);
        }

        return getCachedState(lexer._mode, lexer._modeStack.toArray());
    }

    @Override
    public void close() {
        // this lexer uses the normal shared ATN so there's need to do something special here
    }

    private static GoHighlighterLexerState getCachedState(int mode, int[] modeStack) {
        GoHighlighterLexerState state = new GoHighlighterLexerState(mode, modeStack);

        synchronized (sharedStates) {
            GoHighlighterLexerState cached = sharedStates.get(state);
            if (cached != null) {
                return cached;
            }

            sharedStates.put(state, state);
            return state;
        }
    }

    public void setState(CharStream input, GoHighlighterLexerState state) {
        lexer.setInputStream(input);
        lexer._mode = state.getMode();

        lexer._modeStack.clear();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            lexer._modeStack.addAll(state.getModeStack());
        }
    }

    @Override
    public Token nextToken() {
        Token token;
        do {
            token = nextTokenCore();
        } while (token == null || token.getType() == GoHighlighterLexer.NEWLINE);

        return token;
    }

    private Token nextTokenCore() {
        return lexer.nextToken();
    }

    @Override
    public int getLine() {
        return lexer.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return lexer.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return lexer.getInputStream();
    }

    @Override
    public TokenFactory<? extends Token> getTokenFactory() {
        return lexer.getTokenFactory();
    }

    @Override
    public void setTokenFactory(TokenFactory<? extends Token> tokenFactory) {
        lexer.setTokenFactory(tokenFactory);
    }
}
