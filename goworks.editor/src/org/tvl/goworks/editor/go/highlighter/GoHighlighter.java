/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.highlighting.ANTLRHighlighterBaseV4;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.settings.AttributesUtilities;
import org.netbeans.api.editor.settings.EditorStyleConstants;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.spi.editor.highlighting.HighlightAttributeValue;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighter extends ANTLRHighlighterBaseV4<GoHighlighterLexerState> {
    public static final String DOCUMENT_PROPERTY = "go-highlighter";
    private static final AttributeSet TOOLTIP =
        AttributesUtilities.createImmutable(EditorStyleConstants.Tooltip, new TooltipResolver());

    private final AttributeSet identifierAttributes;
    private final AttributeSet keywordAttributes;
    private final AttributeSet commentAttributes;
    private final AttributeSet stringLiteralAttributes;
    private final AttributeSet numberLiteralAttributes;

    private GoHighlighterLexerWrapper lexerWrapper;

    @SuppressWarnings("LeakingThisInConstructor")
    public GoHighlighter(final StyledDocument document) {
        super(document);

        document.putProperty(DOCUMENT_PROPERTY, this);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GoEditorKit.GO_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);
        identifierAttributes = getFontAndColors(settings, "identifier", true);
        keywordAttributes = getFontAndColors(settings, "keyword");
        commentAttributes = getFontAndColors(settings, "comment");
        stringLiteralAttributes = getFontAndColors(settings, "stringliteral");
        numberLiteralAttributes = getFontAndColors(settings, "number");
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category) {
        return getFontAndColors(settings, category, false);
    }

    private static AttributeSet getFontAndColors(FontColorSettings settings, String category, boolean tooltip) {
        AttributeSet attributes = settings.getTokenFontColors(category);
        if (tooltip) {
            attributes = AttributesUtilities.createComposite(attributes, TOOLTIP);
        }

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
    protected TokenSourceWithStateV4<Token, GoHighlighterLexerState> createLexer(CharStream input, GoHighlighterLexerState startState) {
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

        default:
            return null;
        }
    }

    private static final class TooltipResolver implements HighlightAttributeValue<String> {

        @Override
        public String getValue(JTextComponent component, Document document, Object attributeKey, int startOffset, int endOffset) {
            return "";
        }

    }
}
