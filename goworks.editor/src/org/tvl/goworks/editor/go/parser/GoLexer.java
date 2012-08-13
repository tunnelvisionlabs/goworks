/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.RangeTransition;
import org.antlr.v4.runtime.atn.SetTransition;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author Sam Harwell
 */
public class GoLexer extends AbstractGoLexer {
    private boolean insertSemicolonAtEol;
    private Token deferredEol;

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

    public GoLexer(CharStream input) {
        super(input);
    }

    @Override
    public void reset() {
        insertSemicolonAtEol = false;
        deferredEol = null;
        super.reset();
    }

    @Override
    public Token nextToken() {
        Token result;
        if (deferredEol != null) {
            result = deferredEol;
            deferredEol = null;
        } else {
            result = super.nextToken();
        }

        switch (result.getType()) {
        case IDENTIFIER:
        case INT_LITERAL:
        case FLOAT_LITERAL:
        case IMAGINARY_LITERAL:
        case CharLiteral:
        case StringLiteral:
        case Break:
        case Continue:
        case Fallthrough:
        case Return:
        case Inc:
        case Dec:
        case RightParen:
        case RightBrack:
        case RightBrace:
            insertSemicolonAtEol = true;
            break;

        default:
            if (result.getChannel() == Token.DEFAULT_CHANNEL) {
                insertSemicolonAtEol = false;
            }
            break;
        }

        return result;
    }

    @Override
    public Token emit() {
        Token semicolonToken = null;
        if (_type == NEWLINE || _type == COMMENT) {
            if (insertSemicolonAtEol) {
                // emit the virtual semicolon token first
                semicolonToken = _factory.create(this, Semi, ";", DEFAULT_TOKEN_CHANNEL, _tokenStartCharIndex, _tokenStartCharIndex - 1, _tokenStartLine, _tokenStartCharPositionInLine);
                emit(semicolonToken);
            }

            // reset for the next line
            insertSemicolonAtEol = false;
        }

        Token result = super.emit();
        if (semicolonToken != null) {
            deferredEol = result;
            _token = semicolonToken; // Lexer.nextToken ignores the return value from emit()
            return semicolonToken;
        }

        return result;
    }

    @Override
    public Token emitEOF() {
        Token semicolonToken = null;
        if (insertSemicolonAtEol) {
            // emit the virtual semicolon token first
            semicolonToken = _factory.create(this, Semi, ";", DEFAULT_TOKEN_CHANNEL, _tokenStartCharIndex, _tokenStartCharIndex - 1, _tokenStartLine, _tokenStartCharPositionInLine);
            emit(semicolonToken);
            // don't want to emit it twice
            insertSemicolonAtEol = false;
        }

        Token result = super.emitEOF();
        if (semicolonToken != null) {
            deferredEol = result;
            return semicolonToken;
        }

        return result;
    }

}
