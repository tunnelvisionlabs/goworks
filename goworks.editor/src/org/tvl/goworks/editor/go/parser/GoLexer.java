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

    static {
        int unicodeDigitRule;
        for (unicodeDigitRule = 0; unicodeDigitRule < ruleNames.length; unicodeDigitRule++) {
            if ("UNICODE_DIGIT_CHAR".equals(ruleNames[unicodeDigitRule])) {
                break;
            }
        }

        int unicodeLetterRule;
        for (unicodeLetterRule = 0; unicodeLetterRule < ruleNames.length; unicodeLetterRule++) {
            if ("UNICODE_LETTER_CHAR".equals(ruleNames[unicodeLetterRule])) {
                break;
            }
        }

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

        if (unicodeDigitRule < ruleNames.length) {
            ATNState sourceState = _ATN.ruleToStartState[unicodeDigitRule].transition(0).target;
            assert sourceState.getNumberOfTransitions() == 1 && sourceState.transition(0) instanceof RangeTransition;
            Transition existingDigitTransition = sourceState.transition(0);
            SetTransition digitTransition = new SetTransition(existingDigitTransition.target, digitChars);
            sourceState.setTransition(0, digitTransition);
        }

        if (unicodeLetterRule < ruleNames.length) {
            ATNState sourceState = _ATN.ruleToStartState[unicodeLetterRule].transition(0).target;
            assert sourceState.getNumberOfTransitions() == 1 && sourceState.transition(0) instanceof SetTransition;
            Transition existingLetterTransition = sourceState.transition(0);
            SetTransition letterTransition = new SetTransition(existingLetterTransition.target, letterChars);
            sourceState.setTransition(0, letterTransition);
        }
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
