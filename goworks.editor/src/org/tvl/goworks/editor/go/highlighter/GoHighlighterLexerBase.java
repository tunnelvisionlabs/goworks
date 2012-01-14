// $ANTLR ANTLRVersion> GoHighlighterLexerBase.java generatedTimestamp>

package org.tvl.goworks.editor.go.highlighter;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GoHighlighterLexerBase extends Lexer {
	public static final int
		Break=3, Case=4, Chan=5, Const=6, Continue=7, Default=8, Defer=9, Else=10, 
		Fallthrough=11, For=12, Func=13, Go=14, Goto=15, If=16, Import=17, Interface=18, 
		Map=19, Package=20, Range=21, Return=22, Select=23, Struct=24, Switch=25, 
		Type=26, Var=27, Plus=28, Minus=29, Star=30, Slash=31, Percent=32, Amp=33, 
		Pipe=34, Caret=35, LeftShift=36, RightShift=37, AmpCaret=38, PlusEqual=39, 
		MinusEqual=40, StarEqual=41, SlashEqual=42, PercentEqual=43, AmpEqual=44, 
		PipeEqual=45, CaretEqual=46, LeftShiftEqual=47, RightShiftEqual=48, AmpCaretEqual=49, 
		And=50, Or=51, LeftArrow=52, Inc=53, Dec=54, EqualEqual=55, LessThan=56, 
		GreaterThan=57, Equal=58, Bang=59, BangEqual=60, LessEqual=61, GreaterEqual=62, 
		ColonEqual=63, Ellipsis=64, LeftParen=65, RightParen=66, LeftBrack=67, 
		RightBrack=68, LeftBrace=69, RightBrace=70, Comma=71, Dot=72, Semi=73, 
		Colon=74, IDENTIFIER=75, WS=76, NEWLINE=77, COMMENT=78, ML_COMMENT=79, 
		INT_LITERAL=80, IMAGINARY_LITERAL=81, FLOAT_LITERAL=82, CharLiteral=83, 
		RawStringLiteral=84, InterpretedStringLiteral=85, ANYCHAR=86, BlockComment_NEWLINE=87, 
		CONTINUE_ML_COMMENT=88, END_ML_COMMENT=89, ML_COMMENT_STAR=90, BlockComment_ANYCHAR=91, 
		RawLiteralNewline=92, ContinueRawLiteral=93, EndRawLiteral=94, RawLiteral_ANYCHAR=95;
	// Lexer modes
	public static final int BlockComment = 1;
	public static final int RawLiteralMode = 2;

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"break", "case", "chan", "const", "continue", "default", "defer", "else", 
		"fallthrough", "for", "func", "go", "goto", "if", "import", "interface", 
		"map", "package", "range", "return", "select", "struct", "switch", "type", 
		"var", "+", "-", "Star", "/", "%", "&", "|", "^", "<<", ">>", "&^", "+=", 
		"-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "&^=", "&&", "||", 
		"<-", "++", "--", "==", "<", ">", "=", "!", "!=", "<=", ">=", ":=", "...", 
		"(", ")", "[", "]", "{", "}", ",", ".", ";", ":", "IDENTIFIER", "WS", 
		"NEWLINE", "COMMENT", "/*", "INT_LITERAL", "IMAGINARY_LITERAL", "FLOAT_LITERAL", 
		"CharLiteral", "RawStringLiteral", "InterpretedStringLiteral", "ANYCHAR", 
		"BlockComment_NEWLINE", "CONTINUE_ML_COMMENT", "*/", "*", "BlockComment_ANYCHAR", 
		"RawLiteralNewline", "ContinueRawLiteral", "`", "RawLiteral_ANYCHAR"
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
		"INT_LITERAL", "DecimalLiteral", "OctalLiteral", "HexLiteral", "IMAGINARY_LITERAL", 
		"FLOAT_LITERAL", "Decimals", "Exponent", "CharLiteral", "UNICODE_VALUE", 
		"BYTE_VALUE", "OCTAL_BYTE_VALUE", "HexByteValue", "LittleUValue", "BigUValue", 
		"EscapedChar", "RawStringLiteral", "InterpretedStringLiteral", "NEWLINE_CHAR", 
		"UNICODE_CHAR", "UNICODE_LETTER_CHAR", "UNICODE_DIGIT_CHAR", "LETTER_CHAR", 
		"DECIMAL_DIGIT_CHAR", "OCTAL_DIGIT_CHAR", "HEX_DIGIT_CHAR", "ANYCHAR", 
		"BlockComment_NEWLINE", "CONTINUE_ML_COMMENT", "END_ML_COMMENT", "ML_COMMENT_STAR", 
		"BlockComment_ANYCHAR", "RawLiteralNewline", "ContinueRawLiteral", "EndRawLiteral", 
		"RawLiteral_ANYCHAR"
	};


	protected int getMultilineCommentType() {
	    return modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : ML_COMMENT;
	}


	public GoHighlighterLexerBase(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "GoHighlighterLexerBase.java"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 76 : ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 93 : RawStringLiteral_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 104 : BlockComment_NEWLINE_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 105 : CONTINUE_ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 106 : END_ML_COMMENT_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 107 : ML_COMMENT_STAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 108 : BlockComment_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 109 : RawLiteralNewline_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 110 : ContinueRawLiteral_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 111 : EndRawLiteral_action((ParserRuleContext<?>)_localctx, actionIndex); break;

			case 112 : RawLiteral_ANYCHAR_action((ParserRuleContext<?>)_localctx, actionIndex); break;
		}
	}
	public void ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(BlockComment); break;
		}
	}
	public void ContinueRawLiteral_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : type =  RawStringLiteral; break;
		}
	}
	public void END_ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : type =  getMultilineCommentType(); popMode(); break;
		}
	}
	public void BlockComment_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : type =  ANYCHAR; break;
		}
	}
	public void ML_COMMENT_STAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : type =  getMultilineCommentType(); break;
		}
	}
	public void RawStringLiteral_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : pushMode(RawLiteralMode); break;
		}
	}
	public void EndRawLiteral_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : type =  RawStringLiteral; popMode(); break;
		}
	}
	public void RawLiteralNewline_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : type =  NEWLINE; break;
		}
	}
	public void CONTINUE_ML_COMMENT_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : type =  getMultilineCommentType(); break;
		}
	}
	public void BlockComment_NEWLINE_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : type =  NEWLINE; break;
		}
	}
	public void RawLiteral_ANYCHAR_action(ParserRuleContext<?> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : type =  ANYCHAR; break;
		}
	}

	public static final String _serializedATN =
		"\2_\u0317\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4"+
		"\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f"+
		"\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2"+
		"\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2"+
		"\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2"+
		"#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2."+
		"\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65"+
		"\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2"+
		"@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7"+
		"K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2"+
		"W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b"+
		"\7b\2c\7c\2d\7d\2e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m"+
		"\2n\7n\2o\7o\2p\7p\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\1\2\1\2"+
		"\1\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1\4\1"+
		"\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1\6\1\6\1\6\1\7\1\7\1\7"+
		"\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\t\1\t\1\t\1"+
		"\t\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\f\1\f\1\f\1\f\1\f\1\r\1\r\1\r"+
		"\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\1\17\1\17\1\17\1\17"+
		"\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\21\1\21\1\21\1\21\1\21\1\21\1\21"+
		"\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\1\23\1\23"+
		"\1\24\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1\25\1\25\1\25\1\25\1\25"+
		"\1\26\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\30\1\30"+
		"\1\30\1\30\1\31\1\31\1\32\1\32\1\33\1\33\1\34\1\34\1\35\1\35\1\36\1\36"+
		"\1\37\1\37\1 \1 \1!\1!\1!\1\"\1\"\1\"\1#\1#\1#\1$\1$\1$\1%\1%\1%\1&\1"+
		"&\1&\1\'\1\'\1\'\1(\1(\1(\1)\1)\1)\1*\1*\1*\1+\1+\1+\1,\1,\1,\1,\1-\1"+
		"-\1-\1-\1.\1.\1.\1.\1/\1/\1/\1\60\1\60\1\60\1\61\1\61\1\61\1\62\1\62\1"+
		"\62\1\63\1\63\1\63\1\64\1\64\1\64\1\65\1\65\1\66\1\66\1\67\1\67\18\18"+
		"\19\19\19\1:\1:\1:\1;\1;\1;\1<\1<\1<\1=\1=\1=\1=\1>\1>\1?\1?\1@\1@\1A"+
		"\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1H\0\1H\0\1H\0\5H\bH\nH\fH\u0204"+
		"\tH\1I\0\4I\bI\13I\fI\u0209\1J\0\3J\bJ\1J\1J\1K\1K\1K\1K\0\5K\bK\nK\f"+
		"K\u021a\tK\1L\1L\1L\1L\1M\0\1M\0\1M\0\3M\bM\1N\0\1N\0\5N\bN\nN\fN\u022f"+
		"\tN\1O\0\1O\0\5O\bO\nO\fO\u0238\tO\1P\0\1P\0\1P\0\4P\bP\13P\fP\u0241\1"+
		"Q\0\1Q\0\3Q\bQ\1Q\1Q\1R\0\1R\0\1R\0\3R\bR\1R\0\3R\bR\1R\0\1R\1R\1R\0\1"+
		"R\0\1R\0\3R\bR\3R\bR\1S\0\4S\bS\13S\fS\u0269\1T\0\1T\0\3T\bT\1T\1T\1U"+
		"\0\1U\0\1U\0\3U\bU\1U\1U\1V\0\1V\0\1V\0\1V\0\3V\bV\1W\0\1W\0\3W\bW\1X"+
		"\0\1X\0\1X\0\1X\1X\1Y\1Y\1Y\1Y\0\1Y\1Y\1Z\1Z\1Z\1Z\0\1Z\0\1Z\0\1Z\1Z\1"+
		"[\1[\1[\1[\0\1[\0\1[\0\1[\0\1[\0\1[\0\1[\0\1[\1[\1\\\0\1\\\1\\\1]\0\1"+
		"]\1^\0\1^\0\1^\0\5^\b^\n^\f^\u02cb\t^\1^\1^\1_\1_\1`\1`\1a\0\1a\0\3a\b"+
		"a\1b\1b\1c\0\1c\0\3c\bc\1d\1d\1e\1e\1f\0\1f\0\1f\0\3f\bf\1g\1g\1h\0\1"+
		"h\1i\0\4i\bi\13i\fi\u02f5\1i\1j\1j\1j\1j\1k\0\1k\1l\0\1l\1m\0\1m\1n\0"+
		"\1n\0\5n\bn\nn\fn\u030d\tn\1n\0\1n\1o\0\1o\1p\0\1pq\3\3\uffff\5\4\uffff"+
		"\7\5\uffff\t\6\uffff\13\7\uffff\r\b\uffff\17\t\uffff\21\n\uffff\23\13"+
		"\uffff\25\f\uffff\27\r\uffff\31\16\uffff\33\17\uffff\35\20\uffff\37\21"+
		"\uffff!\22\uffff#\23\uffff%\24\uffff\'\25\uffff)\26\uffff+\27\uffff-\30"+
		"\uffff/\31\uffff\61\32\uffff\63\33\uffff\65\34\uffff\67\35\uffff9\36\uffff"+
		";\37\uffff= \uffff?!\uffffA\"\uffffC#\uffffE$\uffffG%\uffffI&\uffffK\'"+
		"\uffffM(\uffffO)\uffffQ*\uffffS+\uffffU,\uffffW-\uffffY.\uffff[/\uffff"+
		"]\60\uffff_\61\uffffa\62\uffffc\63\uffffe\64\uffffg\65\uffffi\66\uffff"+
		"k\67\uffffm8\uffffo9\uffffq:\uffffs;\uffffu<\uffffw=\uffffy>\uffff{?\uffff"+
		"}@\uffff\177A\uffff\u0081B\uffff\u0083C\uffff\u0085D\uffff\u0087E\uffff"+
		"\u0089F\uffff\u008bG\uffff\u008dH\uffff\u008fI\uffff\u0091J\uffff\u0093"+
		"K\uffff\u0095L\uffff\u0097M\uffff\u0099N\uffff\u009bO\0\u009dP\uffff\u009f"+
		"\0\uffff\u00a1\0\uffff\u00a3\0\uffff\u00a5Q\uffff\u00a7R\uffff\u00a9\0"+
		"\uffff\u00ab\0\uffff\u00adS\uffff\u00af\0\uffff\u00b1\0\uffff\u00b3\0"+
		"\uffff\u00b5\0\uffff\u00b7\0\uffff\u00b9\0\uffff\u00bb\0\uffff\u00bdT"+
		"\1\u00bfU\uffff\u00c1\0\uffff\u00c3\0\uffff\u00c5\0\uffff\u00c7\0\uffff"+
		"\u00c9\0\uffff\u00cb\0\uffff\u00cd\0\uffff\u00cf\0\uffff\u00d1V\uffff"+
		"\u00d3W\2\u00d5X\3\u00d7Y\4\u00d9Z\5\u00db[\6\u00dd\\\7\u00df]\b\u00e1"+
		"^\t\u00e3_\n\3\0\1\2\b\2\t\t  \2\n\n\r\r\2XXxx\2EEee\2++--\t\"\"\'\'\\"+
		"\\abffnnrrttvv\1\n\n\3\n\n\r\r**\u02d6\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0"+
		"\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0"+
		"\23\1\0\0\0\0\25\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1"+
		"\0\0\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1"+
		"\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65"+
		"\1\0\0\0\0\67\1\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1"+
		"\0\0\0\0C\1\0\0\0\0E\1\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0"+
		"\0\0O\1\0\0\0\0Q\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0"+
		"[\1\0\0\0\0]\1\0\0\0\0_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1"+
		"\0\0\0\0i\1\0\0\0\0k\1\0\0\0\0m\1\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0"+
		"\0\0u\1\0\0\0\0w\1\0\0\0\0y\1\0\0\0\0{\1\0\0\0\0}\1\0\0\0\0\177\1\0\0"+
		"\0\0\u0081\1\0\0\0\0\u0083\1\0\0\0\0\u0085\1\0\0\0\0\u0087\1\0\0\0\0\u0089"+
		"\1\0\0\0\0\u008b\1\0\0\0\0\u008d\1\0\0\0\0\u008f\1\0\0\0\0\u0091\1\0\0"+
		"\0\0\u0093\1\0\0\0\0\u0095\1\0\0\0\0\u0097\1\0\0\0\0\u0099\1\0\0\0\0\u009b"+
		"\1\0\0\0\0\u009d\1\0\0\0\0\u00a5\1\0\0\0\0\u00a7\1\0\0\0\0\u00ad\1\0\0"+
		"\0\0\u00bd\1\0\0\0\0\u00bf\1\0\0\0\0\u00d1\1\0\0\0\1\u00d3\1\0\0\0\1\u00d5"+
		"\1\0\0\0\1\u00d7\1\0\0\0\1\u00d9\1\0\0\0\1\u00db\1\0\0\0\2\u00dd\1\0\0"+
		"\0\2\u00df\1\0\0\0\2\u00e1\1\0\0\0\2\u00e3\1\0\0\0\3\u00e5\1\0\0\0\5\u00eb"+
		"\1\0\0\0\7\u00f0\1\0\0\0\t\u00f5\1\0\0\0\13\u00fb\1\0\0\0\r\u0104\1\0"+
		"\0\0\17\u010c\1\0\0\0\21\u0112\1\0\0\0\23\u0117\1\0\0\0\25\u0123\1\0\0"+
		"\0\27\u0127\1\0\0\0\31\u012c\1\0\0\0\33\u012f\1\0\0\0\35\u0134\1\0\0\0"+
		"\37\u0137\1\0\0\0!\u013e\1\0\0\0#\u0148\1\0\0\0%\u014c\1\0\0\0\'\u0154"+
		"\1\0\0\0)\u015a\1\0\0\0+\u0161\1\0\0\0-\u0168\1\0\0\0/\u016f\1\0\0\0\61"+
		"\u0176\1\0\0\0\63\u017b\1\0\0\0\65\u017f\1\0\0\0\67\u0181\1\0\0\09\u0183"+
		"\1\0\0\0;\u0185\1\0\0\0=\u0187\1\0\0\0?\u0189\1\0\0\0A\u018b\1\0\0\0C"+
		"\u018d\1\0\0\0E\u018f\1\0\0\0G\u0192\1\0\0\0I\u0195\1\0\0\0K\u0198\1\0"+
		"\0\0M\u019b\1\0\0\0O\u019e\1\0\0\0Q\u01a1\1\0\0\0S\u01a4\1\0\0\0U\u01a7"+
		"\1\0\0\0W\u01aa\1\0\0\0Y\u01ad\1\0\0\0[\u01b0\1\0\0\0]\u01b4\1\0\0\0_"+
		"\u01b8\1\0\0\0a\u01bc\1\0\0\0c\u01bf\1\0\0\0e\u01c2\1\0\0\0g\u01c5\1\0"+
		"\0\0i\u01c8\1\0\0\0k\u01cb\1\0\0\0m\u01ce\1\0\0\0o\u01d0\1\0\0\0q\u01d2"+
		"\1\0\0\0s\u01d4\1\0\0\0u\u01d6\1\0\0\0w\u01d9\1\0\0\0y\u01dc\1\0\0\0{"+
		"\u01df\1\0\0\0}\u01e2\1\0\0\0\177\u01e6\1\0\0\0\u0081\u01e8\1\0\0\0\u0083"+
		"\u01ea\1\0\0\0\u0085\u01ec\1\0\0\0\u0087\u01ee\1\0\0\0\u0089\u01f0\1\0"+
		"\0\0\u008b\u01f2\1\0\0\0\u008d\u01f4\1\0\0\0\u008f\u01f6\1\0\0\0\u0091"+
		"\u01f8\1\0\0\0\u0093\u01fa\1\0\0\0\u0095\u0207\1\0\0\0\u0097\u020d\1\0"+
		"\0\0\u0099\u0211\1\0\0\0\u009b\u021b\1\0\0\0\u009d\u0225\1\0\0\0\u009f"+
		"\u0227\1\0\0\0\u00a1\u0230\1\0\0\0\u00a3\u0239\1\0\0\0\u00a5\u0247\1\0"+
		"\0\0\u00a7\u0263\1\0\0\0\u00a9\u0267\1\0\0\0\u00ab\u026b\1\0\0\0\u00ad"+
		"\u0273\1\0\0\0\u00af\u0285\1\0\0\0\u00b1\u028b\1\0\0\0\u00b3\u028d\1\0"+
		"\0\0\u00b5\u0295\1\0\0\0\u00b7\u029c\1\0\0\0\u00b9\u02a7\1\0\0\0\u00bb"+
		"\u02ba\1\0\0\0\u00bd\u02be\1\0\0\0\u00bf\u02c1\1\0\0\0\u00c1\u02ce\1\0"+
		"\0\0\u00c3\u02d0\1\0\0\0\u00c5\u02d6\1\0\0\0\u00c7\u02d8\1\0\0\0\u00c9"+
		"\u02de\1\0\0\0\u00cb\u02e0\1\0\0\0\u00cd\u02e2\1\0\0\0\u00cf\u02ea\1\0"+
		"\0\0\u00d1\u02ec\1\0\0\0\u00d3\u02ee\1\0\0\0\u00d5\u02f3\1\0\0\0\u00d7"+
		"\u02f8\1\0\0\0\u00d9\u02fc\1\0\0\0\u00db\u02ff\1\0\0\0\u00dd\u0302\1\0"+
		"\0\0\u00df\u030b\1\0\0\0\u00e1\u0311\1\0\0\0\u00e3\u0314\1\0\0\0\u00e5"+
		"\u00e6\5b\0\0\u00e6\u00e7\5r\0\0\u00e7\u00e8\5e\0\0\u00e8\u00e9\5a\0\0"+
		"\u00e9\u00ea\5k\0\0\u00ea\4\1\0\0\0\u00eb\u00ec\5c\0\0\u00ec\u00ed\5a"+
		"\0\0\u00ed\u00ee\5s\0\0\u00ee\u00ef\5e\0\0\u00ef\6\1\0\0\0\u00f0\u00f1"+
		"\5c\0\0\u00f1\u00f2\5h\0\0\u00f2\u00f3\5a\0\0\u00f3\u00f4\5n\0\0\u00f4"+
		"\b\1\0\0\0\u00f5\u00f6\5c\0\0\u00f6\u00f7\5o\0\0\u00f7\u00f8\5n\0\0\u00f8"+
		"\u00f9\5s\0\0\u00f9\u00fa\5t\0\0\u00fa\n\1\0\0\0\u00fb\u00fc\5c\0\0\u00fc"+
		"\u00fd\5o\0\0\u00fd\u00fe\5n\0\0\u00fe\u00ff\5t\0\0\u00ff\u0100\5i\0\0"+
		"\u0100\u0101\5n\0\0\u0101\u0102\5u\0\0\u0102\u0103\5e\0\0\u0103\f\1\0"+
		"\0\0\u0104\u0105\5d\0\0\u0105\u0106\5e\0\0\u0106\u0107\5f\0\0\u0107\u0108"+
		"\5a\0\0\u0108\u0109\5u\0\0\u0109\u010a\5l\0\0\u010a\u010b\5t\0\0\u010b"+
		"\16\1\0\0\0\u010c\u010d\5d\0\0\u010d\u010e\5e\0\0\u010e\u010f\5f\0\0\u010f"+
		"\u0110\5e\0\0\u0110\u0111\5r\0\0\u0111\20\1\0\0\0\u0112\u0113\5e\0\0\u0113"+
		"\u0114\5l\0\0\u0114\u0115\5s\0\0\u0115\u0116\5e\0\0\u0116\22\1\0\0\0\u0117"+
		"\u0118\5f\0\0\u0118\u0119\5a\0\0\u0119\u011a\5l\0\0\u011a\u011b\5l\0\0"+
		"\u011b\u011c\5t\0\0\u011c\u011d\5h\0\0\u011d\u011e\5r\0\0\u011e\u011f"+
		"\5o\0\0\u011f\u0120\5u\0\0\u0120\u0121\5g\0\0\u0121\u0122\5h\0\0\u0122"+
		"\24\1\0\0\0\u0123\u0124\5f\0\0\u0124\u0125\5o\0\0\u0125\u0126\5r\0\0\u0126"+
		"\26\1\0\0\0\u0127\u0128\5f\0\0\u0128\u0129\5u\0\0\u0129\u012a\5n\0\0\u012a"+
		"\u012b\5c\0\0\u012b\30\1\0\0\0\u012c\u012d\5g\0\0\u012d\u012e\5o\0\0\u012e"+
		"\32\1\0\0\0\u012f\u0130\5g\0\0\u0130\u0131\5o\0\0\u0131\u0132\5t\0\0\u0132"+
		"\u0133\5o\0\0\u0133\34\1\0\0\0\u0134\u0135\5i\0\0\u0135\u0136\5f\0\0\u0136"+
		"\36\1\0\0\0\u0137\u0138\5i\0\0\u0138\u0139\5m\0\0\u0139\u013a\5p\0\0\u013a"+
		"\u013b\5o\0\0\u013b\u013c\5r\0\0\u013c\u013d\5t\0\0\u013d \1\0\0\0\u013e"+
		"\u013f\5i\0\0\u013f\u0140\5n\0\0\u0140\u0141\5t\0\0\u0141\u0142\5e\0\0"+
		"\u0142\u0143\5r\0\0\u0143\u0144\5f\0\0\u0144\u0145\5a\0\0\u0145\u0146"+
		"\5c\0\0\u0146\u0147\5e\0\0\u0147\"\1\0\0\0\u0148\u0149\5m\0\0\u0149\u014a"+
		"\5a\0\0\u014a\u014b\5p\0\0\u014b$\1\0\0\0\u014c\u014d\5p\0\0\u014d\u014e"+
		"\5a\0\0\u014e\u014f\5c\0\0\u014f\u0150\5k\0\0\u0150\u0151\5a\0\0\u0151"+
		"\u0152\5g\0\0\u0152\u0153\5e\0\0\u0153&\1\0\0\0\u0154\u0155\5r\0\0\u0155"+
		"\u0156\5a\0\0\u0156\u0157\5n\0\0\u0157\u0158\5g\0\0\u0158\u0159\5e\0\0"+
		"\u0159(\1\0\0\0\u015a\u015b\5r\0\0\u015b\u015c\5e\0\0\u015c\u015d\5t\0"+
		"\0\u015d\u015e\5u\0\0\u015e\u015f\5r\0\0\u015f\u0160\5n\0\0\u0160*\1\0"+
		"\0\0\u0161\u0162\5s\0\0\u0162\u0163\5e\0\0\u0163\u0164\5l\0\0\u0164\u0165"+
		"\5e\0\0\u0165\u0166\5c\0\0\u0166\u0167\5t\0\0\u0167,\1\0\0\0\u0168\u0169"+
		"\5s\0\0\u0169\u016a\5t\0\0\u016a\u016b\5r\0\0\u016b\u016c\5u\0\0\u016c"+
		"\u016d\5c\0\0\u016d\u016e\5t\0\0\u016e.\1\0\0\0\u016f\u0170\5s\0\0\u0170"+
		"\u0171\5w\0\0\u0171\u0172\5i\0\0\u0172\u0173\5t\0\0\u0173\u0174\5c\0\0"+
		"\u0174\u0175\5h\0\0\u0175\60\1\0\0\0\u0176\u0177\5t\0\0\u0177\u0178\5"+
		"y\0\0\u0178\u0179\5p\0\0\u0179\u017a\5e\0\0\u017a\62\1\0\0\0\u017b\u017c"+
		"\5v\0\0\u017c\u017d\5a\0\0\u017d\u017e\5r\0\0\u017e\64\1\0\0\0\u017f\u0180"+
		"\5+\0\0\u0180\66\1\0\0\0\u0181\u0182\5-\0\0\u01828\1\0\0\0\u0183\u0184"+
		"\5*\0\0\u0184:\1\0\0\0\u0185\u0186\5/\0\0\u0186<\1\0\0\0\u0187\u0188\5"+
		"%\0\0\u0188>\1\0\0\0\u0189\u018a\5&\0\0\u018a@\1\0\0\0\u018b\u018c\5|"+
		"\0\0\u018cB\1\0\0\0\u018d\u018e\5^\0\0\u018eD\1\0\0\0\u018f\u0190\5<\0"+
		"\0\u0190\u0191\5<\0\0\u0191F\1\0\0\0\u0192\u0193\5>\0\0\u0193\u0194\5"+
		">\0\0\u0194H\1\0\0\0\u0195\u0196\5&\0\0\u0196\u0197\5^\0\0\u0197J\1\0"+
		"\0\0\u0198\u0199\5+\0\0\u0199\u019a\5=\0\0\u019aL\1\0\0\0\u019b\u019c"+
		"\5-\0\0\u019c\u019d\5=\0\0\u019dN\1\0\0\0\u019e\u019f\5*\0\0\u019f\u01a0"+
		"\5=\0\0\u01a0P\1\0\0\0\u01a1\u01a2\5/\0\0\u01a2\u01a3\5=\0\0\u01a3R\1"+
		"\0\0\0\u01a4\u01a5\5%\0\0\u01a5\u01a6\5=\0\0\u01a6T\1\0\0\0\u01a7\u01a8"+
		"\5&\0\0\u01a8\u01a9\5=\0\0\u01a9V\1\0\0\0\u01aa\u01ab\5|\0\0\u01ab\u01ac"+
		"\5=\0\0\u01acX\1\0\0\0\u01ad\u01ae\5^\0\0\u01ae\u01af\5=\0\0\u01afZ\1"+
		"\0\0\0\u01b0\u01b1\5<\0\0\u01b1\u01b2\5<\0\0\u01b2\u01b3\5=\0\0\u01b3"+
		"\\\1\0\0\0\u01b4\u01b5\5>\0\0\u01b5\u01b6\5>\0\0\u01b6\u01b7\5=\0\0\u01b7"+
		"^\1\0\0\0\u01b8\u01b9\5&\0\0\u01b9\u01ba\5^\0\0\u01ba\u01bb\5=\0\0\u01bb"+
		"`\1\0\0\0\u01bc\u01bd\5&\0\0\u01bd\u01be\5&\0\0\u01beb\1\0\0\0\u01bf\u01c0"+
		"\5|\0\0\u01c0\u01c1\5|\0\0\u01c1d\1\0\0\0\u01c2\u01c3\5<\0\0\u01c3\u01c4"+
		"\5-\0\0\u01c4f\1\0\0\0\u01c5\u01c6\5+\0\0\u01c6\u01c7\5+\0\0\u01c7h\1"+
		"\0\0\0\u01c8\u01c9\5-\0\0\u01c9\u01ca\5-\0\0\u01caj\1\0\0\0\u01cb\u01cc"+
		"\5=\0\0\u01cc\u01cd\5=\0\0\u01cdl\1\0\0\0\u01ce\u01cf\5<\0\0\u01cfn\1"+
		"\0\0\0\u01d0\u01d1\5>\0\0\u01d1p\1\0\0\0\u01d2\u01d3\5=\0\0\u01d3r\1\0"+
		"\0\0\u01d4\u01d5\5!\0\0\u01d5t\1\0\0\0\u01d6\u01d7\5!\0\0\u01d7\u01d8"+
		"\5=\0\0\u01d8v\1\0\0\0\u01d9\u01da\5<\0\0\u01da\u01db\5=\0\0\u01dbx\1"+
		"\0\0\0\u01dc\u01dd\5>\0\0\u01dd\u01de\5=\0\0\u01dez\1\0\0\0\u01df\u01e0"+
		"\5:\0\0\u01e0\u01e1\5=\0\0\u01e1|\1\0\0\0\u01e2\u01e3\5.\0\0\u01e3\u01e4"+
		"\5.\0\0\u01e4\u01e5\5.\0\0\u01e5~\1\0\0\0\u01e6\u01e7\5(\0\0\u01e7\u0080"+
		"\1\0\0\0\u01e8\u01e9\5)\0\0\u01e9\u0082\1\0\0\0\u01ea\u01eb\5[\0\0\u01eb"+
		"\u0084\1\0\0\0\u01ec\u01ed\5]\0\0\u01ed\u0086\1\0\0\0\u01ee\u01ef\5{\0"+
		"\0\u01ef\u0088\1\0\0\0\u01f0\u01f1\5}\0\0\u01f1\u008a\1\0\0\0\u01f2\u01f3"+
		"\5,\0\0\u01f3\u008c\1\0\0\0\u01f4\u01f5\5.\0\0\u01f5\u008e\1\0\0\0\u01f6"+
		"\u01f7\5;\0\0\u01f7\u0090\1\0\0\0\u01f8\u01f9\5:\0\0\u01f9\u0092\1\0\0"+
		"\0\u01fa\u0202\3\u00c9c\0\u01fc\u0201\3\u00c9c\0\u01fe\u0201\3\u00c7b"+
		"\0\u0200\u01fc\1\0\0\0\u0200\u01fe\1\0\0\0\u0201\u0204\1\0\0\0\u0202\u0200"+
		"\1\0\0\0\u0202\u0203\1\0\0\0\u0203\u0094\1\0\0\0\u0204\u0202\1\0\0\0\u0205"+
		"\u0208\7\0\0\0\u0207\u0205\1\0\0\0\u0208\u0209\1\0\0\0\u0209\u0207\1\0"+
		"\0\0\u0209\u020a\1\0\0\0\u020a\u0096\1\0\0\0\u020b\u020e\5\r\0\0\u020d"+
		"\u020b\1\0\0\0\u020d\u020e\1\0\0\0\u020e\u020f\1\0\0\0\u020f\u0210\5\n"+
		"\0\0\u0210\u0098\1\0\0\0\u0211\u0212\5/\0\0\u0212\u0213\5/\0\0\u0213\u0218"+
		"\1\0\0\0\u0214\u0217\b\1\0\0\u0216\u0214\1\0\0\0\u0217\u021a\1\0\0\0\u0218"+
		"\u0216\1\0\0\0\u0218\u0219\1\0\0\0\u0219\u009a\1\0\0\0\u021a\u0218\1\0"+
		"\0\0\u021b\u021c\5/\0\0\u021c\u021d\5*\0\0\u021d\u021e\1\0\0\0\u021e\u009c"+
		"\1\0\0\0\u021f\u0226\3\u009fN\0\u0221\u0226\3\u00a1O\0\u0223\u0226\3\u00a3"+
		"P\0\u0225\u021f\1\0\0\0\u0225\u0221\1\0\0\0\u0225\u0223\1\0\0\0\u0226"+
		"\u009e\1\0\0\0\u0227\u022d\2\619\0\u0229\u022c\3\u00cbd\0\u022b\u0229"+
		"\1\0\0\0\u022c\u022f\1\0\0\0\u022d\u022b\1\0\0\0\u022d\u022e\1\0\0\0\u022e"+
		"\u00a0\1\0\0\0\u022f\u022d\1\0\0\0\u0230\u0236\5\60\0\0\u0232\u0235\3"+
		"\u00cde\0\u0234\u0232\1\0\0\0\u0235\u0238\1\0\0\0\u0236\u0234\1\0\0\0"+
		"\u0236\u0237\1\0\0\0\u0237\u00a2\1\0\0\0\u0238\u0236\1\0\0\0\u0239\u023b"+
		"\5\60\0\0\u023b\u023f\7\2\0\0\u023d\u0240\3\u00cff\0\u023f\u023d\1\0\0"+
		"\0\u0240\u0241\1\0\0\0\u0241\u023f\1\0\0\0\u0241\u0242\1\0\0\0\u0242\u00a4"+
		"\1\0\0\0\u0243\u0248\3\u00a9S\0\u0245\u0248\3\u00a7R\0\u0247\u0243\1\0"+
		"\0\0\u0247\u0245\1\0\0\0\u0248\u0249\1\0\0\0\u0249\u024a\5i\0\0\u024a"+
		"\u00a6\1\0\0\0\u024b\u024d\3\u00a9S\0\u024d\u0251\5.\0\0\u024f\u0252\3"+
		"\u00a9S\0\u0251\u024f\1\0\0\0\u0251\u0252\1\0\0\0\u0252\u0255\1\0\0\0"+
		"\u0253\u0256\3\u00abT\0\u0255\u0253\1\0\0\0\u0255\u0256\1\0\0\0\u0256"+
		"\u0264\1\0\0\0\u0257\u0259\3\u00a9S\0\u0259\u025a\3\u00abT\0\u025a\u0264"+
		"\1\0\0\0\u025b\u025d\5.\0\0\u025d\u0261\3\u00a9S\0\u025f\u0262\3\u00ab"+
		"T\0\u0261\u025f\1\0\0\0\u0261\u0262\1\0\0\0\u0262\u0264\1\0\0\0\u0263"+
		"\u024b\1\0\0\0\u0263\u0257\1\0\0\0\u0263\u025b\1\0\0\0\u0264\u00a8\1\0"+
		"\0\0\u0265\u0268\3\u00cbd\0\u0267\u0265\1\0\0\0\u0268\u0269\1\0\0\0\u0269"+
		"\u0267\1\0\0\0\u0269\u026a\1\0\0\0\u026a\u00aa\1\0\0\0\u026b\u026f\7\3"+
		"\0\0\u026d\u0270\7\4\0\0\u026f\u026d\1\0\0\0\u026f\u0270\1\0\0\0\u0270"+
		"\u0271\1\0\0\0\u0271\u0272\3\u00a9S\0\u0272\u00ac\1\0\0\0\u0273\u0279"+
		"\5\'\0\0\u0275\u027a\3\u00afV\0\u0277\u027a\3\u00b1W\0\u0279\u0275\1\0"+
		"\0\0\u0279\u0277\1\0\0\0\u027a\u027b\1\0\0\0\u027b\u027c\5\'\0\0\u027c"+
		"\u00ae\1\0\0\0\u027d\u0286\3\u00c3`\0\u027f\u0286\3\u00b7Z\0\u0281\u0286"+
		"\3\u00b9[\0\u0283\u0286\3\u00bb\\\0\u0285\u027d\1\0\0\0\u0285\u027f\1"+
		"\0\0\0\u0285\u0281\1\0\0\0\u0285\u0283\1\0\0\0\u0286\u00b0\1\0\0\0\u0287"+
		"\u028c\3\u00b3X\0\u0289\u028c\3\u00b5Y\0\u028b\u0287\1\0\0\0\u028b\u0289"+
		"\1\0\0\0\u028c\u00b2\1\0\0\0\u028d\u028f\5\\\0\0\u028f\u0291\3\u00cde"+
		"\0\u0291\u0293\3\u00cde\0\u0293\u0294\3\u00cde\0\u0294\u00b4\1\0\0\0\u0295"+
		"\u0296\5\\\0\0\u0296\u0297\5x\0\0\u0297\u0298\1\0\0\0\u0298\u029a\3\u00cf"+
		"f\0\u029a\u029b\3\u00cff\0\u029b\u00b6\1\0\0\0\u029c\u029d\5\\\0\0\u029d"+
		"\u029e\5u\0\0\u029e\u029f\1\0\0\0\u029f\u02a1\3\u00cff\0\u02a1\u02a3\3"+
		"\u00cff\0\u02a3\u02a5\3\u00cff\0\u02a5\u02a6\3\u00cff\0\u02a6\u00b8\1"+
		"\0\0\0\u02a7\u02a8\5\\\0\0\u02a8\u02a9\5U\0\0\u02a9\u02aa\1\0\0\0\u02aa"+
		"\u02ac\3\u00cff\0\u02ac\u02ae\3\u00cff\0\u02ae\u02b0\3\u00cff\0\u02b0"+
		"\u02b2\3\u00cff\0\u02b2\u02b4\3\u00cff\0\u02b4\u02b6\3\u00cff\0\u02b6"+
		"\u02b8\3\u00cff\0\u02b8\u02b9\3\u00cff\0\u02b9\u00ba\1\0\0\0\u02ba\u02bc"+
		"\5\\\0\0\u02bc\u02bd\7\5\0\0\u02bd\u00bc\1\0\0\0\u02be\u02c0\5`\0\0\u02c0"+
		"\u00be\1\0\0\0\u02c1\u02c9\5\"\0\0\u02c3\u02c8\3\u00afV\0\u02c5\u02c8"+
		"\3\u00b1W\0\u02c7\u02c3\1\0\0\0\u02c7\u02c5\1\0\0\0\u02c8\u02cb\1\0\0"+
		"\0\u02c9\u02c7\1\0\0\0\u02c9\u02ca\1\0\0\0\u02ca\u02cc\1\0\0\0\u02cb\u02c9"+
		"\1\0\0\0\u02cc\u02cd\5\"\0\0\u02cd\u00c0\1\0\0\0\u02ce\u02cf\5\n\0\0\u02cf"+
		"\u00c2\1\0\0\0\u02d0\u02d1\b\6\0\0\u02d1\u00c4\1\0\0\0\u02d2\u02d7\2a"+
		"z\0\u02d4\u02d7\2AZ\0\u02d6\u02d2\1\0\0\0\u02d6\u02d4\1\0\0\0\u02d7\u00c6"+
		"\1\0\0\0\u02d8\u02d9\2\609\0\u02d9\u00c8\1\0\0\0\u02da\u02df\3\u00c5a"+
		"\0\u02dc\u02df\5_\0\0\u02de\u02da\1\0\0\0\u02de\u02dc\1\0\0\0\u02df\u00ca"+
		"\1\0\0\0\u02e0\u02e1\2\609\0\u02e1\u00cc\1\0\0\0\u02e2\u02e3\2\60\67\0"+
		"\u02e3\u00ce\1\0\0\0\u02e4\u02eb\2\609\0\u02e6\u02eb\2af\0\u02e8\u02eb"+
		"\2AF\0\u02ea\u02e4\1\0\0\0\u02ea\u02e6\1\0\0\0\u02ea\u02e8\1\0\0\0\u02eb"+
		"\u00d0\1\0\0\0\u02ec\u02ed\t\0\0\0\u02ed\u00d2\1\0\0\0\u02ee\u02f0\3\u0097"+
		"J\0\u02f0\u00d4\1\0\0\0\u02f1\u02f4\b\7\0\0\u02f3\u02f1\1\0\0\0\u02f4"+
		"\u02f5\1\0\0\0\u02f5\u02f3\1\0\0\0\u02f5\u02f6\1\0\0\0\u02f6\u02f7\1\0"+
		"\0\0\u02f7\u00d6\1\0\0\0\u02f8\u02f9\5*\0\0\u02f9\u02fa\5/\0\0\u02fa\u02fb"+
		"\1\0\0\0\u02fb\u00d8\1\0\0\0\u02fc\u02fe\5*\0\0\u02fe\u00da\1\0\0\0\u02ff"+
		"\u0301\t\0\0\0\u0301\u00dc\1\0\0\0\u0302\u0304\3\u0097J\0\u0304\u00de"+
		"\1\0\0\0\u0305\u030a\3\u00c3`\0\u0307\u030a\3\u00c1_\0\u0309\u0305\1\0"+
		"\0\0\u0309\u0307\1\0\0\0\u030a\u030d\1\0\0\0\u030b\u0309\1\0\0\0\u030b"+
		"\u030c\1\0\0\0\u030c\u030e\1\0\0\0\u030d\u030b\1\0\0\0\u030e\u0310\5`"+
		"\0\0\u0310\u00e0\1\0\0\0\u0311\u0313\5`\0\0\u0313\u00e2\1\0\0\0\u0314"+
		"\u0316\t\0\0\0\u0316\u00e4\1\0\0\0\36\0\1\1\1\2\1\u0200\1\u0202\1\u0209"+
		"\1\u020d\1\u0218\1\u0225\1\u022d\1\u0236\1\u0241\1\u0247\1\u0251\1\u0255"+
		"\1\u0261\1\u0263\1\u0269\1\u026f\1\u0279\1\u0285\1\u028b\1\u02c7\1\u02c9"+
		"\1\u02d6\1\u02de\1\u02ea\1\u02f5\1\u0309\1\u030b\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}