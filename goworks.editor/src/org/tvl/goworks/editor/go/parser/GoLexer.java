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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Sam Harwell
 */
public class GoLexer extends GoLexerBase {
    private boolean insertSemicolonAtEol;
    private Token deferredEol;

    public GoLexer(CharStream input) {
        super(input);
    }

    @Override
    public Token nextToken() {
        if (deferredEol != null) {
            Token result = deferredEol;
            deferredEol = null;
            return result;
        }

        Token result = super.nextToken();
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
        if (type == NEWLINE || type == COMMENT) {
            if (insertSemicolonAtEol) {
                // emit the virtual semicolon token first
                // TODO: text is separately set due to bug in CommonTokenFactory
                semicolonToken = _factory.create(this, Semi, null, DEFAULT_TOKEN_CHANNEL, tokenStartCharIndex, tokenStartCharIndex - 1, tokenStartLine, tokenStartCharPositionInLine);
                ((CommonToken)semicolonToken).setText(";");
                emit(semicolonToken);
            }

            // reset for the next line
            insertSemicolonAtEol = false;
        }

        Token result = super.emit();
        if (semicolonToken != null) {
            deferredEol = result;
            token = semicolonToken; // Lexer.nextToken ignores the return value from emit()
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
            semicolonToken = _factory.create(this, Semi, ";", DEFAULT_TOKEN_CHANNEL, tokenStartCharIndex, tokenStartCharIndex - 1, tokenStartLine, tokenStartCharPositionInLine);
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
