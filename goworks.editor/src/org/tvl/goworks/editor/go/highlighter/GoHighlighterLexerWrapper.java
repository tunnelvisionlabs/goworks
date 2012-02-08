/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.util.ArrayDeque;
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
public class GoHighlighterLexerWrapper implements TokenSourceWithStateV4<GoHighlighterLexerState> {
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
    public CharStream getCharStream() {
        return lexer.getInputStream();
    }

    @Override
    public String getSourceName() {
        return "Grammar Highlighter";
    }

    @Override
    public GoHighlighterLexerState getState() {
        if (lexer.modeStack == null) {
            return getCachedState(lexer.mode, null);
        }

        int[] modes = new int[lexer.modeStack.size()];
        int index = 0;
        for (int mode : lexer.modeStack) {
            modes[index++] = mode;
        }

        return getCachedState(lexer.mode, modes);
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
        lexer.mode = state.getMode();
        if (state.getModeStack() != null && state.getModeStack().length > 0) {
            if (lexer.modeStack == null) {
                lexer.modeStack = new ArrayDeque<Integer>();
            } else {
                lexer.modeStack.clear();
            }

            for (int mode : state.getModeStack()) {
                lexer.modeStack.add(mode);
            }
        } else if (lexer.modeStack != null) {
            lexer.modeStack.clear();
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
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        lexer.setTokenFactory(tokenFactory);
    }
}
