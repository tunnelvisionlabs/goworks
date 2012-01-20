/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.highlighter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;

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
