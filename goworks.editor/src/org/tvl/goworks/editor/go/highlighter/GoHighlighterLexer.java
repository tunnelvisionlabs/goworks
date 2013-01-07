/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.tvl.goworks.editor.go.highlighter.generated.AbstractGoHighlighterLexer;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighterLexer extends AbstractGoHighlighterLexer {
    private static final char unicodeDigitPlaceholder = '\u0660';
    private static final char unicodeLetterPlaceholder = '\u0101';

    static {
        IntervalSet digitChars = new IntervalSet();
        IntervalSet letterChars = new IntervalSet();
        for (char c = 0; c < Character.MAX_VALUE; c++) {
            if (Character.isDigit(c)) {
                digitChars.add(c);
            }
            else if (Character.isLetter(c)) {
                letterChars.add(c);
            }
        }

        for (ATNState state : _ATN.states) {
            if (state == null || state.onlyHasEpsilonTransitions()) {
                continue;
            }

            for (int i = 0; i < state.getNumberOfTransitions(); i++) {
                Transition updated = patchTransition(state.transition(i), digitChars, letterChars);
                if (updated != null) {
                    state.setTransition(i, updated);
                }
            }

            if (!state.isOptimized()) {
                continue;
            }

            for (int i = 0; i < state.getNumberOfOptimizedTransitions(); i++) {
                Transition updated = patchTransition(state.getOptimizedTransition(i), digitChars, letterChars);
                if (updated != null) {
                    state.setOptimizedTransition(i, updated);
                }
            }
        }
    }

    private static Transition patchTransition(Transition transition, IntervalSet digitChars, IntervalSet letterChars) {
        switch (transition.getSerializationType()) {
        case Transition.ATOM:
        case Transition.RANGE:
        case Transition.SET:
            break;

        default:
            return null;
        }

        IntervalSet label = transition.label();
        if (label == null) {
            return null;
        }

        IntervalSet updated = null;
        if (label.contains(unicodeDigitPlaceholder)) {
            updated = new IntervalSet(label);
            updated.addAll(digitChars);
            if (updated.size() == digitChars.size()) {
                updated = digitChars;
            }
        }

        if (label.contains(unicodeLetterPlaceholder)) {
            if (updated != null) {
                updated.addAll(label);
            }
            else {
                updated = new IntervalSet(label);
            }

            updated.addAll(letterChars);
            if (updated.size() == letterChars.size()) {
                updated = letterChars;
            }
        }

        if (updated == null) {
            return null;
        }

        return new SetTransition(transition.target, updated);
    }

    public GoHighlighterLexer(CharStream input) {
        super(input);
        _interp = new GoHighlighterATNSimulator(this, _ATN);
    }

    @Override
    public Token emit() {
        switch (_type) {
//        case TOKENS:
//            handleAcceptPositionForKeyword("tokens");
//            setInTokens(true);
//            break;
//
//        case OPTIONS:
//            handleAcceptPositionForKeyword("options");
//            setInOptions(true);
//            break;
//
//        case LABEL:
//            handleAcceptPositionForIdentifier();
//            if (isInOptions()) {
//                _type = ValidGrammarOption;
//            } else if (isInTokens()) {
//                _type = IDENTIFIER;
//            }
//
//            break;
//
//        case RCURLY:
//            setInTokens(false);
//            setInOptions(false);
//            break;

        default:
            break;
        }

        return super.emit();
    }

    private boolean handleAcceptPositionForIdentifier() {
        String tokenText = getText();
        int identifierLength = 0;
        while (identifierLength < tokenText.length() && isIdentifierChar(tokenText.charAt(identifierLength))) {
            identifierLength++;
        }

        if (getInputStream().index() > _tokenStartCharIndex + identifierLength) {
            int offset = identifierLength - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), _tokenStartCharIndex + offset, _tokenStartLine, _tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    private boolean handleAcceptPositionForKeyword(String keyword) {
        if (getInputStream().index() > _tokenStartCharIndex + keyword.length()) {
            int offset = keyword.length() - 1;
            getInterpreter().resetAcceptPosition(getInputStream(), _tokenStartCharIndex + offset, _tokenStartLine, _tokenStartCharPositionInLine + offset);
            return true;
        }

        return false;
    }

    @Override
    public GoHighlighterATNSimulator getInterpreter() {
        return (GoHighlighterATNSimulator)super.getInterpreter();
    }

    private static boolean isIdentifierChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    protected static class GoHighlighterATNSimulator extends LexerATNSimulator {

        public GoHighlighterATNSimulator(Lexer recog, ATN atn) {
            super(recog, atn);
        }

        protected void resetAcceptPosition(CharStream input, int index, int line, int charPositionInLine) {
            input.seek(index);
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            consume(input);
        }

    }

}
