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
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class GoLexer extends AbstractGoLexer {
    private boolean insertSemicolonAtEol;
    private Token deferredEol;

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
                // TODO: text is separately set due to bug in CommonTokenFactory
                semicolonToken = _factory.create(this, Semi, null, DEFAULT_TOKEN_CHANNEL, _tokenStartCharIndex, _tokenStartCharIndex - 1, _tokenStartLine, _tokenStartCharPositionInLine);
                ((CommonToken)semicolonToken).setText(";");
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
    public Token anEOF() {
        Token semicolonToken = null;
        if (insertSemicolonAtEol) {
            // emit the virtual semicolon token first
            // TODO: text is separately set due to bug in CommonTokenFactory
            semicolonToken = _factory.create(this, Semi, ";", DEFAULT_TOKEN_CHANNEL, _tokenStartCharIndex, _tokenStartCharIndex - 1, _tokenStartLine, _tokenStartCharPositionInLine);
            ((CommonToken)semicolonToken).setText(";");
            emit(semicolonToken);
            // don't want to emit it twice
            insertSemicolonAtEol = false;
        }

        Token result = super.anEOF();
        if (semicolonToken != null) {
            deferredEol = result;
            return semicolonToken;
        }

        return result;
    }

}
