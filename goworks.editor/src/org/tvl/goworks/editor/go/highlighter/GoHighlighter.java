/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.openide.text.NbDocument;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighter extends ANTLRHighlighterBaseV4<GoHighlighterLexerState> {
    public static final String DOCUMENT_PROPERTY = "go-highlighter";

    private final AttributeSet identifierAttributes;
    private final AttributeSet keywordAttributes;
    private final AttributeSet commentAttributes;
    private final AttributeSet stringLiteralAttributes;
    private final AttributeSet numberLiteralAttributes;
//    private final AttributeSet symbolDefinitionAttributes;
//    private final AttributeSet symbolReferenceAttributes;
//    private final AttributeSet parserRuleAttributes;
//    private final AttributeSet lexerRuleAttributes;
//    private final AttributeSet astOperatorAttributes;
//    private final AttributeSet directiveAttributes;
//    private final AttributeSet validOptionAttributes;
//    private final AttributeSet invalidOptionAttributes;
//    private final AttributeSet actionLiteralAttributes;
//    private final AttributeSet actionCommentAttributes;
//    private final AttributeSet actionStringLiteralAttributes;
//    private final AttributeSet actionSymbolReferenceAttributes;

    private GoHighlighterLexerWrapper lexerWrapper;

    @SuppressWarnings("LeakingThisInConstructor")
    public GoHighlighter(final StyledDocument document) {
        super(document);

        document.putProperty(DOCUMENT_PROPERTY, this);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GoEditorKit.GO_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        identifierAttributes = getFontAndColors(settings, "identifier");
        keywordAttributes = getFontAndColors(settings, "keyword");
        commentAttributes = getFontAndColors(settings, "comment");
        stringLiteralAttributes = getFontAndColors(settings, "stringliteral");
        numberLiteralAttributes = getFontAndColors(settings, "number");
//        symbolDefinitionAttributes = getFontAndColors(settings, "definition");
//        symbolReferenceAttributes = getFontAndColors(settings, "reference");
//        parserRuleAttributes = getFontAndColors(settings, "parserrule");
//        lexerRuleAttributes = getFontAndColors(settings, "lexerrule");
//        astOperatorAttributes = getFontAndColors(settings, "astoperator");
//        directiveAttributes = getFontAndColors(settings, "directive");
//        validOptionAttributes = getFontAndColors(settings, "validoption");
//        invalidOptionAttributes = getFontAndColors(settings, "invalidoption");
//        actionLiteralAttributes = getFontAndColors(settings, "actionliteral");
//        actionCommentAttributes = getFontAndColors(settings, "actioncomment");
//        actionStringLiteralAttributes = getFontAndColors(settings, "actionstringliteral");
//        actionSymbolReferenceAttributes = getFontAndColors(settings, "actionreference");
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        return attributes;
    }

    @Override
    protected CharStream createInputStream(OffsetRegion span) throws BadLocationException {
        return super.createInputStream(span);
    }

    @Override
    protected GoHighlighterLexerState getStartState() {
        return GoHighlighterLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<GoHighlighterLexerState> createLexer(CharStream input, GoHighlighterLexerState startState) {
        if (lexerWrapper == null) {
            lexerWrapper = new GoHighlighterLexerWrapper(input, startState);
        } else {
            lexerWrapper.setState(input, startState);
        }

        return lexerWrapper;
    }

    @Override
    protected AttributeSet highlightToken(Token token) {
        switch (token.getType()) {
        // common keywords
        case GoHighlighterLexer.Break:
        case GoHighlighterLexer.Case:
        case GoHighlighterLexer.Chan:
        case GoHighlighterLexer.Const:
        case GoHighlighterLexer.Continue:
        case GoHighlighterLexer.Default:
        case GoHighlighterLexer.Defer:
        case GoHighlighterLexer.Else:
        case GoHighlighterLexer.Fallthrough:
        case GoHighlighterLexer.For:
        case GoHighlighterLexer.Func:
        case GoHighlighterLexer.Go:
        case GoHighlighterLexer.Goto:
        case GoHighlighterLexer.If:
        case GoHighlighterLexer.Import:
        case GoHighlighterLexer.Interface:
        case GoHighlighterLexer.Map:
        case GoHighlighterLexer.Package:
        case GoHighlighterLexer.Range:
        case GoHighlighterLexer.Return:
        case GoHighlighterLexer.Select:
        case GoHighlighterLexer.Struct:
        case GoHighlighterLexer.Switch:
        case GoHighlighterLexer.Type:
        case GoHighlighterLexer.Var:
            return keywordAttributes;

        case GoHighlighterLexer.INT_LITERAL:
        case GoHighlighterLexer.FLOAT_LITERAL:
            return numberLiteralAttributes;

        case GoHighlighterLexer.CharLiteral:
        case GoHighlighterLexer.InterpretedStringLiteral:
        case GoHighlighterLexer.RawStringLiteral:
            return stringLiteralAttributes;

        case GoHighlighterLexer.COMMENT:
        case GoHighlighterLexer.ML_COMMENT:
            return commentAttributes;

        case GoHighlighterLexer.IDENTIFIER:
            return identifierAttributes;

//        case GoHighlighterLexer.LEXER:
//        case GoHighlighterLexer.PARSER:
//        case GoHighlighterLexer.CATCH:
//        case GoHighlighterLexer.FINALLY:
//        case GoHighlighterLexer.GRAMMAR:
//        case GoHighlighterLexer.PRIVATE:
//        case GoHighlighterLexer.PROTECTED:
//        case GoHighlighterLexer.PUBLIC:
//        case GoHighlighterLexer.RETURNS  :
//        case GoHighlighterLexer.THROWS:
//        case GoHighlighterLexer.IMPORT:
//        case GoHighlighterLexer.FRAGMENT :
//        case GoHighlighterLexer.TOKENS:
//        case GoHighlighterLexer.OPTIONS:
//            return keywordAttributes;
//
//        // v4 only keywords
//        case GoHighlighterLexer.MODE:
//        case GoHighlighterLexer.LOCALS:
//            return !legacyMode ? keywordAttributes : getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());
//
//        // v3 only keywords
//        case GoHighlighterLexer.TREE:
//        case GoHighlighterLexer.SCOPE:
//            return legacyMode ? keywordAttributes : getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());
//
//        case GoHighlighterLexer.IDENTIFIER:
//            return getIdentifierAttributes(lexerWrapper.getLexer(), token.getText());
//
//        case GoHighlighterLexer.LABEL:
//            return symbolDefinitionAttributes;
//
//        case GoHighlighterLexer.COMMENT:
//        case GoHighlighterLexer.ML_COMMENT:
//            return commentAttributes;
//
//        case GoHighlighterLexer.CHAR_LITERAL:
//        case GoHighlighterLexer.STRING_LITERAL:
////        case GrammarHighlighterLexer.DOUBLE_ANGLE_STRING_LITERAL:
//            return stringLiteralAttributes;
//
//        case GoHighlighterLexer.DIRECTIVE:
//            return directiveAttributes;
//
//        case GoHighlighterLexer.REFERENCE:
//            return symbolReferenceAttributes;
//
//        case GoHighlighterLexer.BANG:
//        case GoHighlighterLexer.REWRITE:
//        case GoHighlighterLexer.ROOT:
//            return astOperatorAttributes;
//
//        case GoHighlighterLexer.INT:
//            return numberLiteralAttributes;
//
//        case GoHighlighterLexer.Action_COMMENT:
//        case GoHighlighterLexer.Action_ML_COMMENT:
//            return actionCommentAttributes;
//
//        case GoHighlighterLexer.ArgAction_TEXT:
//        case GoHighlighterLexer.Action_TEXT:
//            return actionLiteralAttributes;
//
//        case GoHighlighterLexer.ArgAction_CHAR_LITERAL:
//        case GoHighlighterLexer.Action_CHAR_LITERAL:
//        case GoHighlighterLexer.ArgAction_STRING_LITERAL:
//        case GoHighlighterLexer.Action_STRING_LITERAL:
//            return actionStringLiteralAttributes;
//
//        case GoHighlighterLexer.ArgAction_REFERENCE:
//        case GoHighlighterLexer.Action_REFERENCE:
//            return actionSymbolReferenceAttributes;
//
//        case GoHighlighterLexer.ValidGrammarOption:
//            return validOptionAttributes;
//
//        case GoHighlighterLexer.InvalidGrammarOption:
//            return invalidOptionAttributes;

        default:
            return null;
        }
    }

}
