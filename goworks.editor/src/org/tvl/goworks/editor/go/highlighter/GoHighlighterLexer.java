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
import org.antlr.v4.runtime.atn.LexerATNSimulator;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighterLexer extends AbstractGoHighlighterLexer {

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
            traceSeek(index);
            input.seek(index);
            this.line = line;
            this.charPositionInLine = charPositionInLine;
            consume(input);
        }

    }

}
