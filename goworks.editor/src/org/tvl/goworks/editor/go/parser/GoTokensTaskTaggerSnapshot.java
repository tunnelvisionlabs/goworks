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
package org.tvl.goworks.editor.go.parser;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.antlr.netbeans.editor.highlighting.TokenSourceWithStateV4;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.works.editor.shared.AbstractTokensTaskTaggerSnapshot;
import org.antlr.works.editor.shared.SimpleLexerState;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
class GoTokensTaskTaggerSnapshot extends AbstractTokensTaskTaggerSnapshot<SimpleLexerState> {
    private final Map<Thread, Reference<GoLexerWrapper>> lexerCache = new WeakHashMap<Thread, Reference<GoLexerWrapper>>();

    public GoTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        super(snapshot);
    }

    protected GoTokensTaskTaggerSnapshot(@NonNull GoTokensTaskTaggerSnapshot reference, @NonNull DocumentSnapshot snapshot) {
        super(reference, snapshot);
    }

    @Override
    protected SimpleLexerState getStartState() {
        return SimpleLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        synchronized (lexerCache) {
            Reference<GoLexerWrapper> ref = lexerCache.get(Thread.currentThread());
            GoLexerWrapper lexer = ref != null ? ref.get() : null;
            if (lexer == null) {
                lexer = new GoLexerWrapper(input);
                lexerCache.put(Thread.currentThread(), new SoftReference<GoLexerWrapper>(lexer));
            } else {
                lexer.setInputStream(input);
            }

            startState.apply(lexer);
            return lexer;
        }
    }

    @Override
    protected GoTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new GoTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class GoLexerWrapper extends GoLexer implements TokenSourceWithStateV4<SimpleLexerState> {
//        private static final Deque<LexerATNSimulator> SharedInterpreters = new ArrayDeque<LexerATNSimulator>();
//
//        private LexerATNSimulator localInterpreter;
//
//        static {
//            for (int i = 0; i < 4; i++) {
//                SharedInterpreters.add(new LexerATNSimulator(_ATN));
//            }
//        }

        public GoLexerWrapper(CharStream input) {
            super(input);
        }

//        @Override
//        public Token nextToken() {
//            try {
//                takeSharedInterpreter();
//                return super.nextToken();
//            } finally {
//                returnSharedInterpreter();
//            }
//        }

        @Override
        public CharStream getCharStream() {
            return getInputStream();
        }

        @Override
        public SimpleLexerState getState() {
            return SimpleLexerState.createSimpleState(this);
        }

//        public void takeSharedInterpreter() {
//            if (localInterpreter != null) {
//                // already have shared interpreter
//                return;
//            }
//
//            synchronized (SharedInterpreters) {
//                if (SharedInterpreters.isEmpty()) {
//                    return;
//                }
//
//                localInterpreter = _interp;
//                _interp = SharedInterpreters.poll();
//            }
//        }
//
//        public void returnSharedInterpreter() {
//            if (localInterpreter == null) {
//                // don't have a shared interpreter
//                return;
//            }
//
//            synchronized (SharedInterpreters) {
//                SharedInterpreters.add(_interp);
//            }
//
//            _interp = localInterpreter;
//            localInterpreter = null;
//        }
    }
}
