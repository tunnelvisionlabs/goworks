// Generated from GoLexer.g4 by ANTLR 4.0-SNAPSHOT
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

public abstract class AbstractGoLexer extends Lexer {
	public static final int
		INT_LITERAL=1, Break=2, Case=3, Chan=4, Const=5, Continue=6, Default=7, 
		Defer=8, Else=9, Fallthrough=10, For=11, Func=12, Go=13, Goto=14, If=15, 
		Import=16, Interface=17, Map=18, Package=19, Range=20, Return=21, Select=22, 
		Struct=23, Switch=24, Type=25, Var=26, Plus=27, Minus=28, Star=29, Slash=30, 
		Percent=31, Amp=32, Pipe=33, Caret=34, LeftShift=35, RightShift=36, AmpCaret=37, 
		PlusEqual=38, MinusEqual=39, StarEqual=40, SlashEqual=41, PercentEqual=42, 
		AmpEqual=43, PipeEqual=44, CaretEqual=45, LeftShiftEqual=46, RightShiftEqual=47, 
		AmpCaretEqual=48, And=49, Or=50, LeftArrow=51, Inc=52, Dec=53, EqualEqual=54, 
		LessThan=55, GreaterThan=56, Equal=57, Bang=58, BangEqual=59, LessEqual=60, 
		GreaterEqual=61, ColonEqual=62, Ellipsis=63, LeftParen=64, RightParen=65, 
		LeftBrack=66, RightBrack=67, LeftBrace=68, RightBrace=69, Comma=70, Dot=71, 
		Semi=72, Colon=73, IDENTIFIER=74, WS=75, NEWLINE=76, COMMENT=77, ML_COMMENT=78, 
		IMAGINARY_LITERAL=79, FLOAT_LITERAL=80, CharLiteral=81, StringLiteral=82, 
		ANYCHAR=83;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"INT_LITERAL", "'break'", "'case'", "'chan'", "'const'", "'continue'", 
		"'default'", "'defer'", "'else'", "'fallthrough'", "'for'", "'func'", 
		"'go'", "'goto'", "'if'", "'import'", "'interface'", "'map'", "'package'", 
		"'range'", "'return'", "'select'", "'struct'", "'switch'", "'type'", "'var'", 
		"'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", "'<<'", "'>>'", 
		"'&^'", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", "'|='", "'^='", 
		"'<<='", "'>>='", "'&^='", "'&&'", "'||'", "'<-'", "'++'", "'--'", "'=='", 
		"'<'", "'>'", "'='", "'!'", "'!='", "'<='", "'>='", "':='", "'...'", "'('", 
		"')'", "'['", "']'", "'{'", "'}'", "','", "'.'", "';'", "':'", "IDENTIFIER", 
		"WS", "NEWLINE", "COMMENT", "ML_COMMENT", "IMAGINARY_LITERAL", "FLOAT_LITERAL", 
		"CharLiteral", "StringLiteral", "ANYCHAR"
	};
	public static final String[] ruleNames = {
		"Break", "Case", "Chan", "Const", "Continue", "Default", "Defer", "Else", 
		"Fallthrough", "For", "Func", "Go", "Goto", "If", "Import", "Interface", 
		"Map", "Package", "Range", "Return", "Select", "Struct", "Switch", "Type", 
		"Var", "Plus", "Minus", "Star", "Slash", "Percent", "Amp", "Pipe", "Caret", 
		"LeftShift", "RightShift", "AmpCaret", "PlusEqual", "MinusEqual", "StarEqual", 
		"SlashEqual", "PercentEqual", "AmpEqual", "PipeEqual", "CaretEqual", "LeftShiftEqual", 
		"RightShiftEqual", "AmpCaretEqual", "And", "Or", "LeftArrow", "Inc", "Dec", 
		"EqualEqual", "LessThan", "GreaterThan", "Equal", "Bang", "BangEqual", 
		"LessEqual", "GreaterEqual", "ColonEqual", "Ellipsis", "LeftParen", "RightParen", 
		"LeftBrack", "RightBrack", "LeftBrace", "RightBrace", "Comma", "Dot", 
		"Semi", "Colon", "IDENTIFIER", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", 
		"DecimalLiteral", "OctalLiteral", "HexLiteral", "IMAGINARY_LITERAL", "FLOAT_LITERAL", 
		"Decimals", "Exponent", "CharLiteral", "UNICODE_VALUE_NOSQUOTE", "UNICODE_VALUE_NODQUOTE", 
		"BYTE_VALUE", "OCTAL_BYTE_VALUE", "HexByteValue", "LittleUValue", "BigUValue", 
		"EscapedChar", "StringLiteral", "RawStringLiteral", "InterpretedStringLiteral", 
		"NEWLINE_CHAR", "UNICODE_CHAR_NOSQUOTE", "UNICODE_CHAR_NODQUOTE", "UNICODE_CHAR_NOBTICK", 
		"UNICODE_LETTER_CHAR", "UNICODE_DIGIT_CHAR", "LETTER_CHAR", "DECIMAL_DIGIT_CHAR", 
		"OCTAL_DIGIT_CHAR", "HEX_DIGIT_CHAR", "ANYCHAR"
	};


	public AbstractGoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "GoLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public void action(RuleContext<Integer> _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 73 : WS_action(_localctx, actionIndex); break;

		case 74 : NEWLINE_action(_localctx, actionIndex); break;

		case 75 : COMMENT_action(_localctx, actionIndex); break;

		case 76 : ML_COMMENT_action(_localctx, actionIndex); break;

		case 77 : DecimalLiteral_action(_localctx, actionIndex); break;

		case 78 : OctalLiteral_action(_localctx, actionIndex); break;

		case 79 : HexLiteral_action(_localctx, actionIndex); break;
		}
	}
	private void HexLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: _type = INT_LITERAL;  break;
		}
	}
	private void ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: _channel = HIDDEN;  break;
		}
	}
	private void WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}
	private void NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: _channel = HIDDEN;  break;
		}
	}
	private void OctalLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: _type = INT_LITERAL;  break;
		}
	}
	private void DecimalLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: _type = INT_LITERAL;  break;
		}
	}

	public static final String _serializedATN =
		"\3\2S\u02c2\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2"+
		"\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34"+
		"\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2"+
		"%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60"+
		"\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67"+
		"\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7"+
		"B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2"+
		"N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7"+
		"Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2c\7c\2d\7d"+
		"\2e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1"+
		"\1\1\1\1\1\1\2\1\2\1\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4"+
		"\1\4\1\4\1\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1\6\1"+
		"\6\1\6\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b"+
		"\1\b\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\f\1\f\1\f\1"+
		"\f\1\f\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1"+
		"\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1"+
		"\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1\23\1"+
		"\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1"+
		"\25\1\25\1\25\1\25\1\26\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1"+
		"\27\1\27\1\30\1\30\1\30\1\30\1\31\1\31\1\32\1\32\1\33\1\33\1\34\1\34\1"+
		"\35\1\35\1\36\1\36\1\37\1\37\1 \1 \1!\1!\1!\1\"\1\"\1\"\1#\1#\1#\1$\1"+
		"$\1$\1%\1%\1%\1&\1&\1&\1\'\1\'\1\'\1(\1(\1(\1)\1)\1)\1*\1*\1*\1+\1+\1"+
		"+\1,\1,\1,\1,\1-\1-\1-\1-\1.\1.\1.\1.\1/\1/\1/\1\60\1\60\1\60\1\61\1\61"+
		"\1\61\1\62\1\62\1\62\1\63\1\63\1\63\1\64\1\64\1\64\1\65\1\65\1\66\1\66"+
		"\1\67\1\67\18\18\19\19\19\1:\1:\1:\1;\1;\1;\1<\1<\1<\1=\1=\1=\1=\1>\1"+
		">\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1H\1H\1H\5H\u01f0"+
		"\bH\nH\fH\u01f3\tH\1I\4I\u01f6\bI\13I\fI\u01f7\1I\1I\1J\3J\u01fd\bJ\1"+
		"J\1J\1J\1J\1K\1K\1K\1K\5K\u0207\bK\nK\fK\u020a\tK\1K\1K\1L\1L\1L\1L\5"+
		"L\u0212\bL\nL\fL\u0215\tL\1L\1L\1L\1L\1L\1M\1M\5M\u021e\bM\nM\fM\u0221"+
		"\tM\1M\1M\1N\1N\5N\u0227\bN\nN\fN\u022a\tN\1N\1N\1O\1O\1O\4O\u0231\bO"+
		"\13O\fO\u0232\1O\1O\1P\1P\3P\u0239\bP\1P\1P\1Q\1Q\1Q\3Q\u0240\bQ\1Q\3"+
		"Q\u0243\bQ\1Q\1Q\1Q\1Q\1Q\1Q\3Q\u024b\bQ\3Q\u024d\bQ\1R\4R\u0250\bR\13"+
		"R\fR\u0251\1S\1S\3S\u0256\bS\1S\1S\1T\1T\1T\3T\u025d\bT\1T\1T\1U\1U\1"+
		"U\1U\3U\u0265\bU\1V\1V\1V\1V\3V\u026b\bV\1W\1W\3W\u026f\bW\1X\1X\1X\1"+
		"X\1X\1Y\1Y\1Y\1Y\1Y\1Y\1Z\1Z\1Z\1Z\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1[\1[\1"+
		"[\1[\1[\1[\1[\1\\\1\\\1\\\1]\1]\3]\u0295\b]\1^\1^\1^\5^\u029a\b^\n^\f"+
		"^\u029d\t^\1^\1^\1_\1_\1_\5_\u02a4\b_\n_\f_\u02a7\t_\1_\1_\1`\1`\1a\1"+
		"a\1b\1b\1c\1c\1d\1d\1e\1e\1f\1f\3f\u02b9\bf\1g\1g\1h\1h\1i\1i\1j\1j\1"+
		"\u0213\0k\1\0\2\uffff\3\0\3\uffff\5\0\4\uffff\7\0\5\uffff\t\0\6\uffff"+
		"\13\0\7\uffff\r\0\b\uffff\17\0\t\uffff\21\0\n\uffff\23\0\13\uffff\25\0"+
		"\f\uffff\27\0\r\uffff\31\0\16\uffff\33\0\17\uffff\35\0\20\uffff\37\0\21"+
		"\uffff!\0\22\uffff#\0\23\uffff%\0\24\uffff\'\0\25\uffff)\0\26\uffff+\0"+
		"\27\uffff-\0\30\uffff/\0\31\uffff\61\0\32\uffff\63\0\33\uffff\65\0\34"+
		"\uffff\67\0\35\uffff9\0\36\uffff;\0\37\uffff=\0 \uffff?\0!\uffffA\0\""+
		"\uffffC\0#\uffffE\0$\uffffG\0%\uffffI\0&\uffffK\0\'\uffffM\0(\uffffO\0"+
		")\uffffQ\0*\uffffS\0+\uffffU\0,\uffffW\0-\uffffY\0.\uffff[\0/\uffff]\0"+
		"\60\uffff_\0\61\uffffa\0\62\uffffc\0\63\uffffe\0\64\uffffg\0\65\uffff"+
		"i\0\66\uffffk\0\67\uffffm\08\uffffo\09\uffffq\0:\uffffs\0;\uffffu\0<\uffff"+
		"w\0=\uffffy\0>\uffff{\0?\uffff}\0@\uffff\177\0A\uffff\u0081\0B\uffff\u0083"+
		"\0C\uffff\u0085\0D\uffff\u0087\0E\uffff\u0089\0F\uffff\u008b\0G\uffff"+
		"\u008d\0H\uffff\u008f\0I\uffff\u0091\0J\uffff\u0093\0K\0\u0095\0L\1\u0097"+
		"\0M\2\u0099\0N\3\u009b\0\0\4\u009d\0\0\5\u009f\0\0\6\u00a1\0O\uffff\u00a3"+
		"\0P\uffff\u00a5\0\0\uffff\u00a7\0\0\uffff\u00a9\0Q\uffff\u00ab\0\0\uffff"+
		"\u00ad\0\0\uffff\u00af\0\0\uffff\u00b1\0\0\uffff\u00b3\0\0\uffff\u00b5"+
		"\0\0\uffff\u00b7\0\0\uffff\u00b9\0\0\uffff\u00bb\0R\uffff\u00bd\0\0\uffff"+
		"\u00bf\0\0\uffff\u00c1\0\0\uffff\u00c3\0\0\uffff\u00c5\0\0\uffff\u00c7"+
		"\0\0\uffff\u00c9\0\0\uffff\u00cb\0\0\uffff\u00cd\0\0\uffff\u00cf\0\0\uffff"+
		"\u00d1\0\0\uffff\u00d3\0\0\uffff\u00d5\0S\uffff\1\0\f\2\t\t  \2\n\n\r"+
		"\r\2XXxx\2EEee\2++--\t\"\"\'\'\\\\abffnnrrttvv\3\n\n\'\'\\\\\3\n\n\"\""+
		"\\\\\2\n\n``\3AZaz\u0101\u0101\2\609\u0660\u0660\3\609AFaf\u02ca\0\1\1"+
		"\0\0\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0"+
		"\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0\27\1"+
		"\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0\0\0"+
		"\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0"+
		"/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1\0\0"+
		"\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0\0E\1\0\0\0\0"+
		"G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0Q\1\0\0\0\0S\1"+
		"\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1\0\0\0\0_\1\0\0"+
		"\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1\0\0\0\0i\1\0\0\0\0k\1\0\0\0\0"+
		"m\1\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0u\1\0\0\0\0w\1\0\0\0\0y\1"+
		"\0\0\0\0{\1\0\0\0\0}\1\0\0\0\0\177\1\0\0\0\0\u0081\1\0\0\0\0\u0083\1\0"+
		"\0\0\0\u0085\1\0\0\0\0\u0087\1\0\0\0\0\u0089\1\0\0\0\0\u008b\1\0\0\0\0"+
		"\u008d\1\0\0\0\0\u008f\1\0\0\0\0\u0091\1\0\0\0\0\u0093\1\0\0\0\0\u0095"+
		"\1\0\0\0\0\u0097\1\0\0\0\0\u0099\1\0\0\0\0\u009b\1\0\0\0\0\u009d\1\0\0"+
		"\0\0\u009f\1\0\0\0\0\u00a1\1\0\0\0\0\u00a3\1\0\0\0\0\u00a9\1\0\0\0\0\u00bb"+
		"\1\0\0\0\0\u00d5\1\0\0\0\1\u00d7\1\0\0\0\3\u00dd\1\0\0\0\5\u00e2\1\0\0"+
		"\0\7\u00e7\1\0\0\0\t\u00ed\1\0\0\0\13\u00f6\1\0\0\0\r\u00fe\1\0\0\0\17"+
		"\u0104\1\0\0\0\21\u0109\1\0\0\0\23\u0115\1\0\0\0\25\u0119\1\0\0\0\27\u011e"+
		"\1\0\0\0\31\u0121\1\0\0\0\33\u0126\1\0\0\0\35\u0129\1\0\0\0\37\u0130\1"+
		"\0\0\0!\u013a\1\0\0\0#\u013e\1\0\0\0%\u0146\1\0\0\0\'\u014c\1\0\0\0)\u0153"+
		"\1\0\0\0+\u015a\1\0\0\0-\u0161\1\0\0\0/\u0168\1\0\0\0\61\u016d\1\0\0\0"+
		"\63\u0171\1\0\0\0\65\u0173\1\0\0\0\67\u0175\1\0\0\09\u0177\1\0\0\0;\u0179"+
		"\1\0\0\0=\u017b\1\0\0\0?\u017d\1\0\0\0A\u017f\1\0\0\0C\u0181\1\0\0\0E"+
		"\u0184\1\0\0\0G\u0187\1\0\0\0I\u018a\1\0\0\0K\u018d\1\0\0\0M\u0190\1\0"+
		"\0\0O\u0193\1\0\0\0Q\u0196\1\0\0\0S\u0199\1\0\0\0U\u019c\1\0\0\0W\u019f"+
		"\1\0\0\0Y\u01a2\1\0\0\0[\u01a6\1\0\0\0]\u01aa\1\0\0\0_\u01ae\1\0\0\0a"+
		"\u01b1\1\0\0\0c\u01b4\1\0\0\0e\u01b7\1\0\0\0g\u01ba\1\0\0\0i\u01bd\1\0"+
		"\0\0k\u01c0\1\0\0\0m\u01c2\1\0\0\0o\u01c4\1\0\0\0q\u01c6\1\0\0\0s\u01c8"+
		"\1\0\0\0u\u01cb\1\0\0\0w\u01ce\1\0\0\0y\u01d1\1\0\0\0{\u01d4\1\0\0\0}"+
		"\u01d8\1\0\0\0\177\u01da\1\0\0\0\u0081\u01dc\1\0\0\0\u0083\u01de\1\0\0"+
		"\0\u0085\u01e0\1\0\0\0\u0087\u01e2\1\0\0\0\u0089\u01e4\1\0\0\0\u008b\u01e6"+
		"\1\0\0\0\u008d\u01e8\1\0\0\0\u008f\u01ea\1\0\0\0\u0091\u01ec\1\0\0\0\u0093"+
		"\u01f5\1\0\0\0\u0095\u01fc\1\0\0\0\u0097\u0202\1\0\0\0\u0099\u020d\1\0"+
		"\0\0\u009b\u021b\1\0\0\0\u009d\u0224\1\0\0\0\u009f\u022d\1\0\0\0\u00a1"+
		"\u0238\1\0\0\0\u00a3\u024c\1\0\0\0\u00a5\u024f\1\0\0\0\u00a7\u0253\1\0"+
		"\0\0\u00a9\u0259\1\0\0\0\u00ab\u0264\1\0\0\0\u00ad\u026a\1\0\0\0\u00af"+
		"\u026e\1\0\0\0\u00b1\u0270\1\0\0\0\u00b3\u0275\1\0\0\0\u00b5\u027b\1\0"+
		"\0\0\u00b7\u0283\1\0\0\0\u00b9\u028f\1\0\0\0\u00bb\u0294\1\0\0\0\u00bd"+
		"\u0296\1\0\0\0\u00bf\u02a0\1\0\0\0\u00c1\u02aa\1\0\0\0\u00c3\u02ac\1\0"+
		"\0\0\u00c5\u02ae\1\0\0\0\u00c7\u02b0\1\0\0\0\u00c9\u02b2\1\0\0\0\u00cb"+
		"\u02b4\1\0\0\0\u00cd\u02b8\1\0\0\0\u00cf\u02ba\1\0\0\0\u00d1\u02bc\1\0"+
		"\0\0\u00d3\u02be\1\0\0\0\u00d5\u02c0\1\0\0\0\u00d7\u00d8\5b\0\0\u00d8"+
		"\u00d9\5r\0\0\u00d9\u00da\5e\0\0\u00da\u00db\5a\0\0\u00db\u00dc\5k\0\0"+
		"\u00dc\2\1\0\0\0\u00dd\u00de\5c\0\0\u00de\u00df\5a\0\0\u00df\u00e0\5s"+
		"\0\0\u00e0\u00e1\5e\0\0\u00e1\4\1\0\0\0\u00e2\u00e3\5c\0\0\u00e3\u00e4"+
		"\5h\0\0\u00e4\u00e5\5a\0\0\u00e5\u00e6\5n\0\0\u00e6\6\1\0\0\0\u00e7\u00e8"+
		"\5c\0\0\u00e8\u00e9\5o\0\0\u00e9\u00ea\5n\0\0\u00ea\u00eb\5s\0\0\u00eb"+
		"\u00ec\5t\0\0\u00ec\b\1\0\0\0\u00ed\u00ee\5c\0\0\u00ee\u00ef\5o\0\0\u00ef"+
		"\u00f0\5n\0\0\u00f0\u00f1\5t\0\0\u00f1\u00f2\5i\0\0\u00f2\u00f3\5n\0\0"+
		"\u00f3\u00f4\5u\0\0\u00f4\u00f5\5e\0\0\u00f5\n\1\0\0\0\u00f6\u00f7\5d"+
		"\0\0\u00f7\u00f8\5e\0\0\u00f8\u00f9\5f\0\0\u00f9\u00fa\5a\0\0\u00fa\u00fb"+
		"\5u\0\0\u00fb\u00fc\5l\0\0\u00fc\u00fd\5t\0\0\u00fd\f\1\0\0\0\u00fe\u00ff"+
		"\5d\0\0\u00ff\u0100\5e\0\0\u0100\u0101\5f\0\0\u0101\u0102\5e\0\0\u0102"+
		"\u0103\5r\0\0\u0103\16\1\0\0\0\u0104\u0105\5e\0\0\u0105\u0106\5l\0\0\u0106"+
		"\u0107\5s\0\0\u0107\u0108\5e\0\0\u0108\20\1\0\0\0\u0109\u010a\5f\0\0\u010a"+
		"\u010b\5a\0\0\u010b\u010c\5l\0\0\u010c\u010d\5l\0\0\u010d\u010e\5t\0\0"+
		"\u010e\u010f\5h\0\0\u010f\u0110\5r\0\0\u0110\u0111\5o\0\0\u0111\u0112"+
		"\5u\0\0\u0112\u0113\5g\0\0\u0113\u0114\5h\0\0\u0114\22\1\0\0\0\u0115\u0116"+
		"\5f\0\0\u0116\u0117\5o\0\0\u0117\u0118\5r\0\0\u0118\24\1\0\0\0\u0119\u011a"+
		"\5f\0\0\u011a\u011b\5u\0\0\u011b\u011c\5n\0\0\u011c\u011d\5c\0\0\u011d"+
		"\26\1\0\0\0\u011e\u011f\5g\0\0\u011f\u0120\5o\0\0\u0120\30\1\0\0\0\u0121"+
		"\u0122\5g\0\0\u0122\u0123\5o\0\0\u0123\u0124\5t\0\0\u0124\u0125\5o\0\0"+
		"\u0125\32\1\0\0\0\u0126\u0127\5i\0\0\u0127\u0128\5f\0\0\u0128\34\1\0\0"+
		"\0\u0129\u012a\5i\0\0\u012a\u012b\5m\0\0\u012b\u012c\5p\0\0\u012c\u012d"+
		"\5o\0\0\u012d\u012e\5r\0\0\u012e\u012f\5t\0\0\u012f\36\1\0\0\0\u0130\u0131"+
		"\5i\0\0\u0131\u0132\5n\0\0\u0132\u0133\5t\0\0\u0133\u0134\5e\0\0\u0134"+
		"\u0135\5r\0\0\u0135\u0136\5f\0\0\u0136\u0137\5a\0\0\u0137\u0138\5c\0\0"+
		"\u0138\u0139\5e\0\0\u0139 \1\0\0\0\u013a\u013b\5m\0\0\u013b\u013c\5a\0"+
		"\0\u013c\u013d\5p\0\0\u013d\"\1\0\0\0\u013e\u013f\5p\0\0\u013f\u0140\5"+
		"a\0\0\u0140\u0141\5c\0\0\u0141\u0142\5k\0\0\u0142\u0143\5a\0\0\u0143\u0144"+
		"\5g\0\0\u0144\u0145\5e\0\0\u0145$\1\0\0\0\u0146\u0147\5r\0\0\u0147\u0148"+
		"\5a\0\0\u0148\u0149\5n\0\0\u0149\u014a\5g\0\0\u014a\u014b\5e\0\0\u014b"+
		"&\1\0\0\0\u014c\u014d\5r\0\0\u014d\u014e\5e\0\0\u014e\u014f\5t\0\0\u014f"+
		"\u0150\5u\0\0\u0150\u0151\5r\0\0\u0151\u0152\5n\0\0\u0152(\1\0\0\0\u0153"+
		"\u0154\5s\0\0\u0154\u0155\5e\0\0\u0155\u0156\5l\0\0\u0156\u0157\5e\0\0"+
		"\u0157\u0158\5c\0\0\u0158\u0159\5t\0\0\u0159*\1\0\0\0\u015a\u015b\5s\0"+
		"\0\u015b\u015c\5t\0\0\u015c\u015d\5r\0\0\u015d\u015e\5u\0\0\u015e\u015f"+
		"\5c\0\0\u015f\u0160\5t\0\0\u0160,\1\0\0\0\u0161\u0162\5s\0\0\u0162\u0163"+
		"\5w\0\0\u0163\u0164\5i\0\0\u0164\u0165\5t\0\0\u0165\u0166\5c\0\0\u0166"+
		"\u0167\5h\0\0\u0167.\1\0\0\0\u0168\u0169\5t\0\0\u0169\u016a\5y\0\0\u016a"+
		"\u016b\5p\0\0\u016b\u016c\5e\0\0\u016c\60\1\0\0\0\u016d\u016e\5v\0\0\u016e"+
		"\u016f\5a\0\0\u016f\u0170\5r\0\0\u0170\62\1\0\0\0\u0171\u0172\5+\0\0\u0172"+
		"\64\1\0\0\0\u0173\u0174\5-\0\0\u0174\66\1\0\0\0\u0175\u0176\5*\0\0\u0176"+
		"8\1\0\0\0\u0177\u0178\5/\0\0\u0178:\1\0\0\0\u0179\u017a\5%\0\0\u017a<"+
		"\1\0\0\0\u017b\u017c\5&\0\0\u017c>\1\0\0\0\u017d\u017e\5|\0\0\u017e@\1"+
		"\0\0\0\u017f\u0180\5^\0\0\u0180B\1\0\0\0\u0181\u0182\5<\0\0\u0182\u0183"+
		"\5<\0\0\u0183D\1\0\0\0\u0184\u0185\5>\0\0\u0185\u0186\5>\0\0\u0186F\1"+
		"\0\0\0\u0187\u0188\5&\0\0\u0188\u0189\5^\0\0\u0189H\1\0\0\0\u018a\u018b"+
		"\5+\0\0\u018b\u018c\5=\0\0\u018cJ\1\0\0\0\u018d\u018e\5-\0\0\u018e\u018f"+
		"\5=\0\0\u018fL\1\0\0\0\u0190\u0191\5*\0\0\u0191\u0192\5=\0\0\u0192N\1"+
		"\0\0\0\u0193\u0194\5/\0\0\u0194\u0195\5=\0\0\u0195P\1\0\0\0\u0196\u0197"+
		"\5%\0\0\u0197\u0198\5=\0\0\u0198R\1\0\0\0\u0199\u019a\5&\0\0\u019a\u019b"+
		"\5=\0\0\u019bT\1\0\0\0\u019c\u019d\5|\0\0\u019d\u019e\5=\0\0\u019eV\1"+
		"\0\0\0\u019f\u01a0\5^\0\0\u01a0\u01a1\5=\0\0\u01a1X\1\0\0\0\u01a2\u01a3"+
		"\5<\0\0\u01a3\u01a4\5<\0\0\u01a4\u01a5\5=\0\0\u01a5Z\1\0\0\0\u01a6\u01a7"+
		"\5>\0\0\u01a7\u01a8\5>\0\0\u01a8\u01a9\5=\0\0\u01a9\\\1\0\0\0\u01aa\u01ab"+
		"\5&\0\0\u01ab\u01ac\5^\0\0\u01ac\u01ad\5=\0\0\u01ad^\1\0\0\0\u01ae\u01af"+
		"\5&\0\0\u01af\u01b0\5&\0\0\u01b0`\1\0\0\0\u01b1\u01b2\5|\0\0\u01b2\u01b3"+
		"\5|\0\0\u01b3b\1\0\0\0\u01b4\u01b5\5<\0\0\u01b5\u01b6\5-\0\0\u01b6d\1"+
		"\0\0\0\u01b7\u01b8\5+\0\0\u01b8\u01b9\5+\0\0\u01b9f\1\0\0\0\u01ba\u01bb"+
		"\5-\0\0\u01bb\u01bc\5-\0\0\u01bch\1\0\0\0\u01bd\u01be\5=\0\0\u01be\u01bf"+
		"\5=\0\0\u01bfj\1\0\0\0\u01c0\u01c1\5<\0\0\u01c1l\1\0\0\0\u01c2\u01c3\5"+
		">\0\0\u01c3n\1\0\0\0\u01c4\u01c5\5=\0\0\u01c5p\1\0\0\0\u01c6\u01c7\5!"+
		"\0\0\u01c7r\1\0\0\0\u01c8\u01c9\5!\0\0\u01c9\u01ca\5=\0\0\u01cat\1\0\0"+
		"\0\u01cb\u01cc\5<\0\0\u01cc\u01cd\5=\0\0\u01cdv\1\0\0\0\u01ce\u01cf\5"+
		">\0\0\u01cf\u01d0\5=\0\0\u01d0x\1\0\0\0\u01d1\u01d2\5:\0\0\u01d2\u01d3"+
		"\5=\0\0\u01d3z\1\0\0\0\u01d4\u01d5\5.\0\0\u01d5\u01d6\5.\0\0\u01d6\u01d7"+
		"\5.\0\0\u01d7|\1\0\0\0\u01d8\u01d9\5(\0\0\u01d9~\1\0\0\0\u01da\u01db\5"+
		")\0\0\u01db\u0080\1\0\0\0\u01dc\u01dd\5[\0\0\u01dd\u0082\1\0\0\0\u01de"+
		"\u01df\5]\0\0\u01df\u0084\1\0\0\0\u01e0\u01e1\5{\0\0\u01e1\u0086\1\0\0"+
		"\0\u01e2\u01e3\5}\0\0\u01e3\u0088\1\0\0\0\u01e4\u01e5\5,\0\0\u01e5\u008a"+
		"\1\0\0\0\u01e6\u01e7\5.\0\0\u01e7\u008c\1\0\0\0\u01e8\u01e9\5;\0\0\u01e9"+
		"\u008e\1\0\0\0\u01ea\u01eb\5:\0\0\u01eb\u0090\1\0\0\0\u01ec\u01f1\3\u00cd"+
		"f\0\u01ed\u01f0\3\u00cdf\0\u01ee\u01f0\3\u00cbe\0\u01ef\u01ed\1\0\0\0"+
		"\u01ef\u01ee\1\0\0\0\u01f0\u01f3\1\0\0\0\u01f1\u01ef\1\0\0\0\u01f1\u01f2"+
		"\1\0\0\0\u01f2\u0092\1\0\0\0\u01f3\u01f1\1\0\0\0\u01f4\u01f6\7\0\0\0\u01f5"+
		"\u01f4\1\0\0\0\u01f6\u01f7\1\0\0\0\u01f7\u01f5\1\0\0\0\u01f7\u01f8\1\0"+
		"\0\0\u01f8\u01f9\1\0\0\0\u01f9\u01fa\6I\0\0\u01fa\u0094\1\0\0\0\u01fb"+
		"\u01fd\5\r\0\0\u01fc\u01fb\1\0\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01fe\1\0"+
		"\0\0\u01fe\u01ff\5\n\0\0\u01ff\u0200\1\0\0\0\u0200\u0201\6J\1\0\u0201"+
		"\u0096\1\0\0\0\u0202\u0203\5/\0\0\u0203\u0204\5/\0\0\u0204\u0208\1\0\0"+
		"\0\u0205\u0207\b\1\0\0\u0206\u0205\1\0\0\0\u0207\u020a\1\0\0\0\u0208\u0206"+
		"\1\0\0\0\u0208\u0209\1\0\0\0\u0209\u020b\1\0\0\0\u020a\u0208\1\0\0\0\u020b"+
		"\u020c\6K\2\0\u020c\u0098\1\0\0\0\u020d\u020e\5/\0\0\u020e\u020f\5*\0"+
		"\0\u020f\u0213\1\0\0\0\u0210\u0212\t\0\0\0\u0211\u0210\1\0\0\0\u0212\u0215"+
		"\1\0\0\0\u0213\u0214\1\0\0\0\u0213\u0211\1\0\0\0\u0214\u0216\1\0\0\0\u0215"+
		"\u0213\1\0\0\0\u0216\u0217\5*\0\0\u0217\u0218\5/\0\0\u0218\u0219\1\0\0"+
		"\0\u0219\u021a\6L\3\0\u021a\u009a\1\0\0\0\u021b\u021f\2\619\0\u021c\u021e"+
		"\3\u00cfg\0\u021d\u021c\1\0\0\0\u021e\u0221\1\0\0\0\u021f\u021d\1\0\0"+
		"\0\u021f\u0220\1\0\0\0\u0220\u0222\1\0\0\0\u0221\u021f\1\0\0\0\u0222\u0223"+
		"\6M\4\0\u0223\u009c\1\0\0\0\u0224\u0228\5\60\0\0\u0225\u0227\3\u00d1h"+
		"\0\u0226\u0225\1\0\0\0\u0227\u022a\1\0\0\0\u0228\u0226\1\0\0\0\u0228\u0229"+
		"\1\0\0\0\u0229\u022b\1\0\0\0\u022a\u0228\1\0\0\0\u022b\u022c\6N\5\0\u022c"+
		"\u009e\1\0\0\0\u022d\u022e\5\60\0\0\u022e\u0230\7\2\0\0\u022f\u0231\3"+
		"\u00d3i\0\u0230\u022f\1\0\0\0\u0231\u0232\1\0\0\0\u0232\u0230\1\0\0\0"+
		"\u0232\u0233\1\0\0\0\u0233\u0234\1\0\0\0\u0234\u0235\6O\6\0\u0235\u00a0"+
		"\1\0\0\0\u0236\u0239\3\u00a5R\0\u0237\u0239\3\u00a3Q\0\u0238\u0236\1\0"+
		"\0\0\u0238\u0237\1\0\0\0\u0239\u023a\1\0\0\0\u023a\u023b\5i\0\0\u023b"+
		"\u00a2\1\0\0\0\u023c\u023d\3\u00a5R\0\u023d\u023f\5.\0\0\u023e\u0240\3"+
		"\u00a5R\0\u023f\u023e\1\0\0\0\u023f\u0240\1\0\0\0\u0240\u0242\1\0\0\0"+
		"\u0241\u0243\3\u00a7S\0\u0242\u0241\1\0\0\0\u0242\u0243\1\0\0\0\u0243"+
		"\u024d\1\0\0\0\u0244\u0245\3\u00a5R\0\u0245\u0246\3\u00a7S\0\u0246\u024d"+
		"\1\0\0\0\u0247\u0248\5.\0\0\u0248\u024a\3\u00a5R\0\u0249\u024b\3\u00a7"+
		"S\0\u024a\u0249\1\0\0\0\u024a\u024b\1\0\0\0\u024b\u024d\1\0\0\0\u024c"+
		"\u023c\1\0\0\0\u024c\u0244\1\0\0\0\u024c\u0247\1\0\0\0\u024d\u00a4\1\0"+
		"\0\0\u024e\u0250\3\u00cfg\0\u024f\u024e\1\0\0\0\u0250\u0251\1\0\0\0\u0251"+
		"\u024f\1\0\0\0\u0251\u0252\1\0\0\0\u0252\u00a6\1\0\0\0\u0253\u0255\7\3"+
		"\0\0\u0254\u0256\7\4\0\0\u0255\u0254\1\0\0\0\u0255\u0256\1\0\0\0\u0256"+
		"\u0257\1\0\0\0\u0257\u0258\3\u00a5R\0\u0258\u00a8\1\0\0\0\u0259\u025c"+
		"\5\'\0\0\u025a\u025d\3\u00abU\0\u025b\u025d\3\u00afW\0\u025c\u025a\1\0"+
		"\0\0\u025c\u025b\1\0\0\0\u025d\u025e\1\0\0\0\u025e\u025f\5\'\0\0\u025f"+
		"\u00aa\1\0\0\0\u0260\u0265\3\u00c3a\0\u0261\u0265\3\u00b5Z\0\u0262\u0265"+
		"\3\u00b7[\0\u0263\u0265\3\u00b9\\\0\u0264\u0260\1\0\0\0\u0264\u0261\1"+
		"\0\0\0\u0264\u0262\1\0\0\0\u0264\u0263\1\0\0\0\u0265\u00ac\1\0\0\0\u0266"+
		"\u026b\3\u00c5b\0\u0267\u026b\3\u00b5Z\0\u0268\u026b\3\u00b7[\0\u0269"+
		"\u026b\3\u00b9\\\0\u026a\u0266\1\0\0\0\u026a\u0267\1\0\0\0\u026a\u0268"+
		"\1\0\0\0\u026a\u0269\1\0\0\0\u026b\u00ae\1\0\0\0\u026c\u026f\3\u00b1X"+
		"\0\u026d\u026f\3\u00b3Y\0\u026e\u026c\1\0\0\0\u026e\u026d\1\0\0\0\u026f"+
		"\u00b0\1\0\0\0\u0270\u0271\5\\\0\0\u0271\u0272\3\u00d1h\0\u0272\u0273"+
		"\3\u00d1h\0\u0273\u0274\3\u00d1h\0\u0274\u00b2\1\0\0\0\u0275\u0276\5\\"+
		"\0\0\u0276\u0277\5x\0\0\u0277\u0278\1\0\0\0\u0278\u0279\3\u00d3i\0\u0279"+
		"\u027a\3\u00d3i\0\u027a\u00b4\1\0\0\0\u027b\u027c\5\\\0\0\u027c\u027d"+
		"\5u\0\0\u027d\u027e\1\0\0\0\u027e\u027f\3\u00d3i\0\u027f\u0280\3\u00d3"+
		"i\0\u0280\u0281\3\u00d3i\0\u0281\u0282\3\u00d3i\0\u0282\u00b6\1\0\0\0"+
		"\u0283\u0284\5\\\0\0\u0284\u0285\5U\0\0\u0285\u0286\1\0\0\0\u0286\u0287"+
		"\3\u00d3i\0\u0287\u0288\3\u00d3i\0\u0288\u0289\3\u00d3i\0\u0289\u028a"+
		"\3\u00d3i\0\u028a\u028b\3\u00d3i\0\u028b\u028c\3\u00d3i\0\u028c\u028d"+
		"\3\u00d3i\0\u028d\u028e\3\u00d3i\0\u028e\u00b8\1\0\0\0\u028f\u0290\5\\"+
		"\0\0\u0290\u0291\7\5\0\0\u0291\u00ba\1\0\0\0\u0292\u0295\3\u00bd^\0\u0293"+
		"\u0295\3\u00bf_\0\u0294\u0292\1\0\0\0\u0294\u0293\1\0\0\0\u0295\u00bc"+
		"\1\0\0\0\u0296\u029b\5`\0\0\u0297\u029a\3\u00c7c\0\u0298\u029a\3\u00c1"+
		"`\0\u0299\u0297\1\0\0\0\u0299\u0298\1\0\0\0\u029a\u029d\1\0\0\0\u029b"+
		"\u0299\1\0\0\0\u029b\u029c\1\0\0\0\u029c\u029e\1\0\0\0\u029d\u029b\1\0"+
		"\0\0\u029e\u029f\5`\0\0\u029f\u00be\1\0\0\0\u02a0\u02a5\5\"\0\0\u02a1"+
		"\u02a4\3\u00adV\0\u02a2\u02a4\3\u00afW\0\u02a3\u02a1\1\0\0\0\u02a3\u02a2"+
		"\1\0\0\0\u02a4\u02a7\1\0\0\0\u02a5\u02a3\1\0\0\0\u02a5\u02a6\1\0\0\0\u02a6"+
		"\u02a8\1\0\0\0\u02a7\u02a5\1\0\0\0\u02a8\u02a9\5\"\0\0\u02a9\u00c0\1\0"+
		"\0\0\u02aa\u02ab\5\n\0\0\u02ab\u00c2\1\0\0\0\u02ac\u02ad\b\6\0\0\u02ad"+
		"\u00c4\1\0\0\0\u02ae\u02af\b\7\0\0\u02af\u00c6\1\0\0\0\u02b0\u02b1\b\b"+
		"\0\0\u02b1\u00c8\1\0\0\0\u02b2\u02b3\7\t\0\0\u02b3\u00ca\1\0\0\0\u02b4"+
		"\u02b5\7\n\0\0\u02b5\u00cc\1\0\0\0\u02b6\u02b9\3\u00c9d\0\u02b7\u02b9"+
		"\5_\0\0\u02b8\u02b6\1\0\0\0\u02b8\u02b7\1\0\0\0\u02b9\u00ce\1\0\0\0\u02ba"+
		"\u02bb\2\609\0\u02bb\u00d0\1\0\0\0\u02bc\u02bd\2\60\67\0\u02bd\u00d2\1"+
		"\0\0\0\u02be\u02bf\7\13\0\0\u02bf\u00d4\1\0\0\0\u02c0\u02c1\t\0\0\0\u02c1"+
		"\u00d6\1\0\0\0\33\0\u01ef\u01f1\u01f7\u01fc\u0208\u0213\u021f\u0228\u0232"+
		"\u0238\u023f\u0242\u024a\u024c\u0251\u0255\u025c\u0264\u026a\u026e\u0294"+
		"\u0299\u029b\u02a3\u02a5\u02b8";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	}
}