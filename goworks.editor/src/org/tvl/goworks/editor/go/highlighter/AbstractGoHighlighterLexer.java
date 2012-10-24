// $ANTLR ANTLRVersion> AbstractGoHighlighterLexer.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

public abstract class AbstractGoHighlighterLexer extends Lexer {
	public static final int
		Break=1, Case=2, Chan=3, Const=4, Continue=5, Default=6, Defer=7, Else=8, 
		Fallthrough=9, For=10, Func=11, Go=12, Goto=13, If=14, Import=15, Interface=16, 
		Map=17, Package=18, Range=19, Return=20, Select=21, Struct=22, Switch=23, 
		Type=24, Var=25, Plus=26, Minus=27, Star=28, Slash=29, Percent=30, Amp=31, 
		Pipe=32, Caret=33, LeftShift=34, RightShift=35, AmpCaret=36, PlusEqual=37, 
		MinusEqual=38, StarEqual=39, SlashEqual=40, PercentEqual=41, AmpEqual=42, 
		PipeEqual=43, CaretEqual=44, LeftShiftEqual=45, RightShiftEqual=46, AmpCaretEqual=47, 
		And=48, Or=49, LeftArrow=50, Inc=51, Dec=52, EqualEqual=53, LessThan=54, 
		GreaterThan=55, Equal=56, Bang=57, BangEqual=58, LessEqual=59, GreaterEqual=60, 
		ColonEqual=61, Ellipsis=62, LeftParen=63, RightParen=64, LeftBrack=65, 
		RightBrack=66, LeftBrace=67, RightBrace=68, Comma=69, Dot=70, Semi=71, 
		Colon=72, IDENTIFIER=73, WS=74, NEWLINE=75, COMMENT=76, ML_COMMENT=77, 
		INT_LITERAL=78, IMAGINARY_LITERAL=79, FLOAT_LITERAL=80, CharLiteral=81, 
		RawStringLiteral=82, InterpretedStringLiteral=83, ANYCHAR=84, StringLiteralEscape=85, 
		CharLiteralEscape=86, END_ML_COMMENT=87;
	public static final int BlockComment = 1;
	public static final int RawLiteralMode = 2;
	public static final int StringLiteralMode = 3;
	public static final int CharLiteralMode = 4;
	public static String[] modeNames = {
		"DEFAULT_MODE", "BlockComment", "RawLiteralMode", "StringLiteralMode", 
		"CharLiteralMode"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'break'", "'case'", "'chan'", "'const'", "'continue'", "'default'", "'defer'", 
		"'else'", "'fallthrough'", "'for'", "'func'", "'go'", "'goto'", "'if'", 
		"'import'", "'interface'", "'map'", "'package'", "'range'", "'return'", 
		"'select'", "'struct'", "'switch'", "'type'", "'var'", "'+'", "'-'", "Star", 
		"'/'", "'%'", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'&^'", "'+='", "'-='", 
		"'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'&^='", 
		"'&&'", "'||'", "'<-'", "'++'", "'--'", "'=='", "'<'", "'>'", "'='", "'!'", 
		"'!='", "'<='", "'>='", "':='", "'...'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "','", "'.'", "';'", "':'", "IDENTIFIER", "WS", "NEWLINE", "COMMENT", 
		"'/*'", "INT_LITERAL", "IMAGINARY_LITERAL", "FLOAT_LITERAL", "CharLiteral", 
		"RawStringLiteral", "InterpretedStringLiteral", "ANYCHAR", "StringLiteralEscape", 
		"CharLiteralEscape", "'*/'"
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
		"FLOAT_LITERAL", "Decimals", "Exponent", "CharLiteral", "UNICODE_VALUE_ESCAPE", 
		"BYTE_VALUE", "OCTAL_BYTE_VALUE", "HexByteValue", "LittleUValue", "BigUValue", 
		"EscapedChar", "RawStringLiteral", "InterpretedStringLiteral", "NEWLINE_CHAR", 
		"UNICODE_CHAR_NOSQUOTE", "UNICODE_CHAR_NODQUOTE", "UNICODE_CHAR_NOBTICK", 
		"UNICODE_LETTER_CHAR", "UNICODE_DIGIT_CHAR", "LETTER_CHAR", "DECIMAL_DIGIT_CHAR", 
		"OCTAL_DIGIT_CHAR", "HEX_DIGIT_CHAR", "ANYCHAR", "BlockComment_NEWLINE", 
		"CONTINUE_ML_COMMENT", "END_ML_COMMENT", "ML_COMMENT_STAR", "BlockComment_ANYCHAR", 
		"RawLiteralNewline", "ContinueRawLiteral", "EndRawLiteral", "RawLiteral_ANYCHAR", 
		"ContinueStringLiteral", "StringLiteralEscape", "EndStringLiteral", "StringLiteral_Unterminated", 
		"StringLiteral_ANYCHAR", "ContinueCharLiteral", "CharLiteralEscape", "EndCharLiteral", 
		"CharLiteral_Unterminated", "CharLiteral_ANYCHAR"
	};


	public AbstractGoHighlighterLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	@Override
	public String getGrammarFileName() { return "GoHighlighterLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }


	@Override
	public void action(RuleContext<Integer> _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 0 : Break_action(_localctx, actionIndex); break;

			case 1 : Case_action(_localctx, actionIndex); break;

			case 2 : Chan_action(_localctx, actionIndex); break;

			case 3 : Const_action(_localctx, actionIndex); break;

			case 4 : Continue_action(_localctx, actionIndex); break;

			case 5 : Default_action(_localctx, actionIndex); break;

			case 6 : Defer_action(_localctx, actionIndex); break;

			case 7 : Else_action(_localctx, actionIndex); break;

			case 8 : Fallthrough_action(_localctx, actionIndex); break;

			case 9 : For_action(_localctx, actionIndex); break;

			case 10 : Func_action(_localctx, actionIndex); break;

			case 11 : Go_action(_localctx, actionIndex); break;

			case 12 : Goto_action(_localctx, actionIndex); break;

			case 13 : If_action(_localctx, actionIndex); break;

			case 14 : Import_action(_localctx, actionIndex); break;

			case 15 : Interface_action(_localctx, actionIndex); break;

			case 16 : Map_action(_localctx, actionIndex); break;

			case 17 : Package_action(_localctx, actionIndex); break;

			case 18 : Range_action(_localctx, actionIndex); break;

			case 19 : Return_action(_localctx, actionIndex); break;

			case 20 : Select_action(_localctx, actionIndex); break;

			case 21 : Struct_action(_localctx, actionIndex); break;

			case 22 : Switch_action(_localctx, actionIndex); break;

			case 23 : Type_action(_localctx, actionIndex); break;

			case 24 : Var_action(_localctx, actionIndex); break;

			case 25 : Plus_action(_localctx, actionIndex); break;

			case 26 : Minus_action(_localctx, actionIndex); break;

			case 27 : Star_action(_localctx, actionIndex); break;

			case 28 : Slash_action(_localctx, actionIndex); break;

			case 29 : Percent_action(_localctx, actionIndex); break;

			case 30 : Amp_action(_localctx, actionIndex); break;

			case 31 : Pipe_action(_localctx, actionIndex); break;

			case 32 : Caret_action(_localctx, actionIndex); break;

			case 33 : LeftShift_action(_localctx, actionIndex); break;

			case 34 : RightShift_action(_localctx, actionIndex); break;

			case 35 : AmpCaret_action(_localctx, actionIndex); break;

			case 36 : PlusEqual_action(_localctx, actionIndex); break;

			case 37 : MinusEqual_action(_localctx, actionIndex); break;

			case 38 : StarEqual_action(_localctx, actionIndex); break;

			case 39 : SlashEqual_action(_localctx, actionIndex); break;

			case 40 : PercentEqual_action(_localctx, actionIndex); break;

			case 41 : AmpEqual_action(_localctx, actionIndex); break;

			case 42 : PipeEqual_action(_localctx, actionIndex); break;

			case 43 : CaretEqual_action(_localctx, actionIndex); break;

			case 44 : LeftShiftEqual_action(_localctx, actionIndex); break;

			case 45 : RightShiftEqual_action(_localctx, actionIndex); break;

			case 46 : AmpCaretEqual_action(_localctx, actionIndex); break;

			case 47 : And_action(_localctx, actionIndex); break;

			case 48 : Or_action(_localctx, actionIndex); break;

			case 49 : LeftArrow_action(_localctx, actionIndex); break;

			case 50 : Inc_action(_localctx, actionIndex); break;

			case 51 : Dec_action(_localctx, actionIndex); break;

			case 52 : EqualEqual_action(_localctx, actionIndex); break;

			case 53 : LessThan_action(_localctx, actionIndex); break;

			case 54 : GreaterThan_action(_localctx, actionIndex); break;

			case 55 : Equal_action(_localctx, actionIndex); break;

			case 56 : Bang_action(_localctx, actionIndex); break;

			case 57 : BangEqual_action(_localctx, actionIndex); break;

			case 58 : LessEqual_action(_localctx, actionIndex); break;

			case 59 : GreaterEqual_action(_localctx, actionIndex); break;

			case 60 : ColonEqual_action(_localctx, actionIndex); break;

			case 61 : Ellipsis_action(_localctx, actionIndex); break;

			case 62 : LeftParen_action(_localctx, actionIndex); break;

			case 63 : RightParen_action(_localctx, actionIndex); break;

			case 64 : LeftBrack_action(_localctx, actionIndex); break;

			case 65 : RightBrack_action(_localctx, actionIndex); break;

			case 66 : LeftBrace_action(_localctx, actionIndex); break;

			case 67 : RightBrace_action(_localctx, actionIndex); break;

			case 68 : Comma_action(_localctx, actionIndex); break;

			case 69 : Dot_action(_localctx, actionIndex); break;

			case 70 : Semi_action(_localctx, actionIndex); break;

			case 71 : Colon_action(_localctx, actionIndex); break;

			case 72 : IDENTIFIER_action(_localctx, actionIndex); break;

			case 73 : WS_action(_localctx, actionIndex); break;

			case 74 : NEWLINE_action(_localctx, actionIndex); break;

			case 75 : COMMENT_action(_localctx, actionIndex); break;

			case 76 : ML_COMMENT_action(_localctx, actionIndex); break;

			case 77 : INT_LITERAL_action(_localctx, actionIndex); break;

			case 78 : DecimalLiteral_action(_localctx, actionIndex); break;

			case 79 : OctalLiteral_action(_localctx, actionIndex); break;

			case 80 : HexLiteral_action(_localctx, actionIndex); break;

			case 81 : IMAGINARY_LITERAL_action(_localctx, actionIndex); break;

			case 82 : FLOAT_LITERAL_action(_localctx, actionIndex); break;

			case 83 : Decimals_action(_localctx, actionIndex); break;

			case 84 : Exponent_action(_localctx, actionIndex); break;

			case 85 : CharLiteral_action(_localctx, actionIndex); break;

			case 86 : UNICODE_VALUE_ESCAPE_action(_localctx, actionIndex); break;

			case 87 : BYTE_VALUE_action(_localctx, actionIndex); break;

			case 88 : OCTAL_BYTE_VALUE_action(_localctx, actionIndex); break;

			case 89 : HexByteValue_action(_localctx, actionIndex); break;

			case 90 : LittleUValue_action(_localctx, actionIndex); break;

			case 91 : BigUValue_action(_localctx, actionIndex); break;

			case 92 : EscapedChar_action(_localctx, actionIndex); break;

			case 93 : RawStringLiteral_action(_localctx, actionIndex); break;

			case 94 : InterpretedStringLiteral_action(_localctx, actionIndex); break;

			case 95 : NEWLINE_CHAR_action(_localctx, actionIndex); break;

			case 96 : UNICODE_CHAR_NOSQUOTE_action(_localctx, actionIndex); break;

			case 97 : UNICODE_CHAR_NODQUOTE_action(_localctx, actionIndex); break;

			case 98 : UNICODE_CHAR_NOBTICK_action(_localctx, actionIndex); break;

			case 99 : UNICODE_LETTER_CHAR_action(_localctx, actionIndex); break;

			case 100 : UNICODE_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 101 : LETTER_CHAR_action(_localctx, actionIndex); break;

			case 102 : DECIMAL_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 103 : OCTAL_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 104 : HEX_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 105 : ANYCHAR_action(_localctx, actionIndex); break;

			case 106 : BlockComment_NEWLINE_action(_localctx, actionIndex); break;

			case 107 : CONTINUE_ML_COMMENT_action(_localctx, actionIndex); break;

			case 108 : END_ML_COMMENT_action(_localctx, actionIndex); break;

			case 109 : ML_COMMENT_STAR_action(_localctx, actionIndex); break;

			case 110 : BlockComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 111 : RawLiteralNewline_action(_localctx, actionIndex); break;

			case 112 : ContinueRawLiteral_action(_localctx, actionIndex); break;

			case 113 : EndRawLiteral_action(_localctx, actionIndex); break;

			case 114 : RawLiteral_ANYCHAR_action(_localctx, actionIndex); break;

			case 115 : ContinueStringLiteral_action(_localctx, actionIndex); break;

			case 116 : StringLiteralEscape_action(_localctx, actionIndex); break;

			case 117 : EndStringLiteral_action(_localctx, actionIndex); break;

			case 118 : StringLiteral_Unterminated_action(_localctx, actionIndex); break;

			case 119 : StringLiteral_ANYCHAR_action(_localctx, actionIndex); break;

			case 120 : ContinueCharLiteral_action(_localctx, actionIndex); break;

			case 121 : CharLiteralEscape_action(_localctx, actionIndex); break;

			case 122 : EndCharLiteral_action(_localctx, actionIndex); break;

			case 123 : CharLiteral_Unterminated_action(_localctx, actionIndex); break;

			case 124 : CharLiteral_ANYCHAR_action(_localctx, actionIndex); break;
		}
	}
	public void Switch_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_CHAR_NOBTICK_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PipeEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Pipe_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LessThan_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Goto_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OctalLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EndCharLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 18 : popMode(); _type = CharLiteral;  break;
		}
	}
	public void EndStringLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 14 : _type = InterpretedStringLiteral; popMode();  break;
		}
	}
	public void StringLiteral_Unterminated_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 15 : _type = NEWLINE; popMode();  break;
		}
	}
	public void Fallthrough_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BangEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Percent_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftArrow_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Decimals_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Default_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMAGINARY_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NEWLINE_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CharLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : pushMode(CharLiteralMode);  break;
		}
	}
	public void Chan_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DECIMAL_DIGIT_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ML_COMMENT_STAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : _type = ML_COMMENT;  break;
		}
	}
	public void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ContinueStringLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 13 : _type = InterpretedStringLiteral;  break;
		}
	}
	public void RawLiteral_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 12 : _type = ANYCHAR;  break;
		}
	}
	public void INT_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void MinusEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ColonEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_CHAR_NOSQUOTE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftShiftEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Colon_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Const_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Equal_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Dec_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightShift_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CaretEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Range_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Minus_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Semi_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WS_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Break_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Inc_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CONTINUE_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 5 : _type = ML_COMMENT;  break;
		}
	}
	public void LeftBrace_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SlashEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftBrack_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftParen_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Star_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Defer_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpCaret_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightParen_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RawStringLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : pushMode(RawLiteralMode);  break;
		}
	}
	public void Else_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Var_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Slash_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BlockComment_NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : _type = NEWLINE;  break;
		}
	}
	public void UNICODE_CHAR_NODQUOTE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void END_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : _type = ML_COMMENT; popMode();  break;
		}
	}
	public void GreaterEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigUValue_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LETTER_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Go_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EndRawLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 11 : _type = RawStringLiteral; popMode();  break;
		}
	}
	public void CharLiteralEscape_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EscapedChar_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Exponent_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LessEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HEX_DIGIT_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Case_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Func_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GreaterThan_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CharLiteral_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 20 : _type = ANYCHAR;  break;
		}
	}
	public void ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : pushMode(BlockComment);  break;
		}
	}
	public void StarEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Map_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IDENTIFIER_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Amp_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Ellipsis_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Interface_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpCaretEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Select_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HexByteValue_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_LETTER_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Or_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Return_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Struct_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Caret_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void If_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightShiftEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PercentEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BYTE_VALUE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void And_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void StringLiteral_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 16 : _type = ANYCHAR;  break;
		}
	}
	public void HexLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Import_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PlusEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Type_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Continue_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_DIGIT_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void InterpretedStringLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : pushMode(StringLiteralMode);  break;
		}
	}
	public void ContinueRawLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : _type = RawStringLiteral;  break;
		}
	}
	public void StringLiteralEscape_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftShift_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EqualEqual_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DecimalLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void For_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Package_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ContinueCharLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 17 : _type = CharLiteral;  break;
		}
	}
	public void FLOAT_LITERAL_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OCTAL_BYTE_VALUE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Plus_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Bang_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_VALUE_ESCAPE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BlockComment_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : _type = ANYCHAR;  break;
		}
	}
	public void NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RawLiteralNewline_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 9 : _type = NEWLINE;  break;
		}
	}
	public void Dot_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OCTAL_DIGIT_CHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CharLiteral_Unterminated_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 19 : _type = NEWLINE; popMode();  break;
		}
	}
	public void RightBrack_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LittleUValue_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Comma_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightBrace_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}

	public static final String _serializedATN =
		"\2W\u0314\6\uffff\6\uffff\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2"+
		"\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2"+
		"\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22"+
		"\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31"+
		"\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7"+
		" \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7"+
		"+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64"+
		"\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7"+
		"=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2"+
		"I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7"+
		"T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7Y\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_"+
		"\2`\7`\2a\7a\2b\7b\2c\7c\2d\7d\2e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k"+
		"\7k\2l\7l\2m\7m\2n\7n\2o\7o\2p\7p\2q\7q\2r\7r\2s\7s\2t\7t\2u\7u\2v\7v"+
		"\2w\7w\2x\7x\2y\7y\2z\7z\2{\7{\2|\7|\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1"+
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
		">\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1H\1H\1H\5H\u0218"+
		"\bH\nH\fH\u021b\tH\1I\4I\u021e\bI\13I\fI\u021f\1J\3J\u0223\bJ\1J\1J\1"+
		"K\1K\1K\1K\5K\u022b\bK\nK\fK\u022e\tK\1L\1L\1L\1L\1L\1M\1M\1M\3M\u0238"+
		"\bM\1N\1N\5N\u023c\bN\nN\fN\u023f\tN\1O\1O\5O\u0243\bO\nO\fO\u0246\tO"+
		"\1P\1P\1P\4P\u024b\bP\13P\fP\u024c\1Q\1Q\3Q\u0251\bQ\1Q\1Q\1R\1R\1R\3"+
		"R\u0258\bR\1R\3R\u025b\bR\1R\1R\1R\1R\1R\1R\3R\u0263\bR\3R\u0265\bR\1"+
		"S\4S\u0268\bS\13S\fS\u0269\1T\1T\3T\u026e\bT\1T\1T\1U\1U\1U\1U\1V\1V\1"+
		"V\3V\u0279\bV\1W\1W\3W\u027d\bW\1X\1X\1X\1X\1X\1Y\1Y\1Y\1Y\1Y\1Y\1Z\1"+
		"Z\1Z\1Z\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1[\1[\1[\1[\1[\1[\1[\1\\\1\\\1\\\1"+
		"]\1]\1]\1]\1^\1^\1^\1^\1_\1_\1`\1`\1a\1a\1b\1b\1c\1c\1d\1d\1e\1e\3e\u02b7"+
		"\be\1f\1f\1g\1g\1h\1h\1i\1i\1j\1j\1j\1j\1k\4k\u02c6\bk\13k\fk\u02c7\1"+
		"k\1k\1l\1l\1l\1l\1l\1m\1m\1m\1m\1n\1n\1n\1n\1o\1o\1o\1o\1p\1p\4p\u02df"+
		"\bp\13p\fp\u02e0\1p\1p\1q\1q\1q\1q\1r\1r\1r\1r\1s\1s\1s\1s\1t\1t\3t\u02f3"+
		"\bt\1u\1u\1u\1u\1v\1v\1v\1v\1w\1w\1w\1w\1x\1x\1x\1x\1y\1y\3y\u0307\by"+
		"\1z\1z\1z\1z\1{\1{\1{\1{\1|\1|\1|\1|}\5\1\uffff\7\2\uffff\t\3\uffff\13"+
		"\4\uffff\r\5\uffff\17\6\uffff\21\7\uffff\23\b\uffff\25\t\uffff\27\n\uffff"+
		"\31\13\uffff\33\f\uffff\35\r\uffff\37\16\uffff!\17\uffff#\20\uffff%\21"+
		"\uffff\'\22\uffff)\23\uffff+\24\uffff-\25\uffff/\26\uffff\61\27\uffff"+
		"\63\30\uffff\65\31\uffff\67\32\uffff9\33\uffff;\34\uffff=\35\uffff?\36"+
		"\uffffA\37\uffffC \uffffE!\uffffG\"\uffffI#\uffffK$\uffffM%\uffffO&\uffff"+
		"Q\'\uffffS(\uffffU)\uffffW*\uffffY+\uffff[,\uffff]-\uffff_.\uffffa/\uffff"+
		"c\60\uffffe\61\uffffg\62\uffffi\63\uffffk\64\uffffm\65\uffffo\66\uffff"+
		"q\67\uffffs8\uffffu9\uffffw:\uffffy;\uffff{<\uffff}=\uffff\177>\uffff"+
		"\u0081?\uffff\u0083@\uffff\u0085A\uffff\u0087B\uffff\u0089C\uffff\u008b"+
		"D\uffff\u008dE\uffff\u008fF\uffff\u0091G\uffff\u0093H\uffff\u0095I\uffff"+
		"\u0097J\uffff\u0099K\uffff\u009bL\uffff\u009dM\0\u009fN\uffff\u00a1\0"+
		"\uffff\u00a3\0\uffff\u00a5\0\uffff\u00a7O\uffff\u00a9P\uffff\u00ab\0\uffff"+
		"\u00ad\0\uffff\u00afQ\1\u00b1\0\uffff\u00b3\0\uffff\u00b5\0\uffff\u00b7"+
		"\0\uffff\u00b9\0\uffff\u00bb\0\uffff\u00bd\0\uffff\u00bfR\2\u00c1S\3\u00c3"+
		"\0\uffff\u00c5\0\uffff\u00c7\0\uffff\u00c9\0\uffff\u00cb\0\uffff\u00cd"+
		"\0\uffff\u00cf\0\uffff\u00d1\0\uffff\u00d3\0\uffff\u00d5\0\uffff\u00d7"+
		"T\uffff\u00d9\0\4\u00db\0\5\u00ddW\6\u00df\0\7\u00e1\0\b\u00e3\0\t\u00e5"+
		"\0\n\u00e7\0\13\u00e9\0\f\u00eb\0\r\u00edU\uffff\u00ef\0\16\u00f1\0\17"+
		"\u00f3\0\20\u00f5\0\21\u00f7V\uffff\u00f9\0\22\u00fb\0\23\u00fd\0\24\5"+
		"\0\1\2\3\4\r\2\t\t  \2\n\n\r\r\2XXxx\2EEee\2++--\t\"\"\'\'\\\\abffnnr"+
		"rttvv\3\n\n\'\'\\\\\3\n\n\"\"\\\\\2\n\n``\3AZaz\u0101\u0101\2\609\u0660"+
		"\u0660\3\609AFaf\3\n\n\r\r**\u0314\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0"+
		"\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25"+
		"\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0"+
		"\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0"+
		"\0\0-\1\0\0\0\0/\1\0\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1"+
		"\0\0\0\09\1\0\0\0\0;\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0"+
		"\0\0E\1\0\0\0\0G\1\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0"+
		"Q\1\0\0\0\0S\1\0\0\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1"+
		"\0\0\0\0_\1\0\0\0\0a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1\0\0\0\0i\1\0\0"+
		"\0\0k\1\0\0\0\0m\1\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0u\1\0\0\0\0"+
		"w\1\0\0\0\0y\1\0\0\0\0{\1\0\0\0\0}\1\0\0\0\0\177\1\0\0\0\0\u0081\1\0\0"+
		"\0\0\u0083\1\0\0\0\0\u0085\1\0\0\0\0\u0087\1\0\0\0\0\u0089\1\0\0\0\0\u008b"+
		"\1\0\0\0\0\u008d\1\0\0\0\0\u008f\1\0\0\0\0\u0091\1\0\0\0\0\u0093\1\0\0"+
		"\0\0\u0095\1\0\0\0\0\u0097\1\0\0\0\0\u0099\1\0\0\0\0\u009b\1\0\0\0\0\u009d"+
		"\1\0\0\0\0\u009f\1\0\0\0\0\u00a7\1\0\0\0\0\u00a9\1\0\0\0\0\u00af\1\0\0"+
		"\0\0\u00bf\1\0\0\0\0\u00c1\1\0\0\0\0\u00d7\1\0\0\0\1\u00d9\1\0\0\0\1\u00db"+
		"\1\0\0\0\1\u00dd\1\0\0\0\1\u00df\1\0\0\0\1\u00e1\1\0\0\0\2\u00e3\1\0\0"+
		"\0\2\u00e5\1\0\0\0\2\u00e7\1\0\0\0\2\u00e9\1\0\0\0\3\u00eb\1\0\0\0\3\u00ed"+
		"\1\0\0\0\3\u00ef\1\0\0\0\3\u00f1\1\0\0\0\3\u00f3\1\0\0\0\4\u00f5\1\0\0"+
		"\0\4\u00f7\1\0\0\0\4\u00f9\1\0\0\0\4\u00fb\1\0\0\0\4\u00fd\1\0\0\0\5\u00ff"+
		"\1\0\0\0\7\u0105\1\0\0\0\t\u010a\1\0\0\0\13\u010f\1\0\0\0\r\u0115\1\0"+
		"\0\0\17\u011e\1\0\0\0\21\u0126\1\0\0\0\23\u012c\1\0\0\0\25\u0131\1\0\0"+
		"\0\27\u013d\1\0\0\0\31\u0141\1\0\0\0\33\u0146\1\0\0\0\35\u0149\1\0\0\0"+
		"\37\u014e\1\0\0\0!\u0151\1\0\0\0#\u0158\1\0\0\0%\u0162\1\0\0\0\'\u0166"+
		"\1\0\0\0)\u016e\1\0\0\0+\u0174\1\0\0\0-\u017b\1\0\0\0/\u0182\1\0\0\0\61"+
		"\u0189\1\0\0\0\63\u0190\1\0\0\0\65\u0195\1\0\0\0\67\u0199\1\0\0\09\u019b"+
		"\1\0\0\0;\u019d\1\0\0\0=\u019f\1\0\0\0?\u01a1\1\0\0\0A\u01a3\1\0\0\0C"+
		"\u01a5\1\0\0\0E\u01a7\1\0\0\0G\u01a9\1\0\0\0I\u01ac\1\0\0\0K\u01af\1\0"+
		"\0\0M\u01b2\1\0\0\0O\u01b5\1\0\0\0Q\u01b8\1\0\0\0S\u01bb\1\0\0\0U\u01be"+
		"\1\0\0\0W\u01c1\1\0\0\0Y\u01c4\1\0\0\0[\u01c7\1\0\0\0]\u01ca\1\0\0\0_"+
		"\u01ce\1\0\0\0a\u01d2\1\0\0\0c\u01d6\1\0\0\0e\u01d9\1\0\0\0g\u01dc\1\0"+
		"\0\0i\u01df\1\0\0\0k\u01e2\1\0\0\0m\u01e5\1\0\0\0o\u01e8\1\0\0\0q\u01ea"+
		"\1\0\0\0s\u01ec\1\0\0\0u\u01ee\1\0\0\0w\u01f0\1\0\0\0y\u01f3\1\0\0\0{"+
		"\u01f6\1\0\0\0}\u01f9\1\0\0\0\177\u01fc\1\0\0\0\u0081\u0200\1\0\0\0\u0083"+
		"\u0202\1\0\0\0\u0085\u0204\1\0\0\0\u0087\u0206\1\0\0\0\u0089\u0208\1\0"+
		"\0\0\u008b\u020a\1\0\0\0\u008d\u020c\1\0\0\0\u008f\u020e\1\0\0\0\u0091"+
		"\u0210\1\0\0\0\u0093\u0212\1\0\0\0\u0095\u0214\1\0\0\0\u0097\u021d\1\0"+
		"\0\0\u0099\u0222\1\0\0\0\u009b\u0226\1\0\0\0\u009d\u022f\1\0\0\0\u009f"+
		"\u0237\1\0\0\0\u00a1\u0239\1\0\0\0\u00a3\u0240\1\0\0\0\u00a5\u0247\1\0"+
		"\0\0\u00a7\u0250\1\0\0\0\u00a9\u0264\1\0\0\0\u00ab\u0267\1\0\0\0\u00ad"+
		"\u026b\1\0\0\0\u00af\u0271\1\0\0\0\u00b1\u0278\1\0\0\0\u00b3\u027c\1\0"+
		"\0\0\u00b5\u027e\1\0\0\0\u00b7\u0283\1\0\0\0\u00b9\u0289\1\0\0\0\u00bb"+
		"\u0291\1\0\0\0\u00bd\u029d\1\0\0\0\u00bf\u02a0\1\0\0\0\u00c1\u02a4\1\0"+
		"\0\0\u00c3\u02a8\1\0\0\0\u00c5\u02aa\1\0\0\0\u00c7\u02ac\1\0\0\0\u00c9"+
		"\u02ae\1\0\0\0\u00cb\u02b0\1\0\0\0\u00cd\u02b2\1\0\0\0\u00cf\u02b6\1\0"+
		"\0\0\u00d1\u02b8\1\0\0\0\u00d3\u02ba\1\0\0\0\u00d5\u02bc\1\0\0\0\u00d7"+
		"\u02be\1\0\0\0\u00d9\u02c0\1\0\0\0\u00db\u02c5\1\0\0\0\u00dd\u02cb\1\0"+
		"\0\0\u00df\u02d0\1\0\0\0\u00e1\u02d4\1\0\0\0\u00e3\u02d8\1\0\0\0\u00e5"+
		"\u02de\1\0\0\0\u00e7\u02e4\1\0\0\0\u00e9\u02e8\1\0\0\0\u00eb\u02ec\1\0"+
		"\0\0\u00ed\u02f2\1\0\0\0\u00ef\u02f4\1\0\0\0\u00f1\u02f8\1\0\0\0\u00f3"+
		"\u02fc\1\0\0\0\u00f5\u0300\1\0\0\0\u00f7\u0306\1\0\0\0\u00f9\u0308\1\0"+
		"\0\0\u00fb\u030c\1\0\0\0\u00fd\u0310\1\0\0\0\u00ff\u0100\5b\0\0\u0100"+
		"\u0101\5r\0\0\u0101\u0102\5e\0\0\u0102\u0103\5a\0\0\u0103\u0104\5k\0\0"+
		"\u0104\6\1\0\0\0\u0105\u0106\5c\0\0\u0106\u0107\5a\0\0\u0107\u0108\5s"+
		"\0\0\u0108\u0109\5e\0\0\u0109\b\1\0\0\0\u010a\u010b\5c\0\0\u010b\u010c"+
		"\5h\0\0\u010c\u010d\5a\0\0\u010d\u010e\5n\0\0\u010e\n\1\0\0\0\u010f\u0110"+
		"\5c\0\0\u0110\u0111\5o\0\0\u0111\u0112\5n\0\0\u0112\u0113\5s\0\0\u0113"+
		"\u0114\5t\0\0\u0114\f\1\0\0\0\u0115\u0116\5c\0\0\u0116\u0117\5o\0\0\u0117"+
		"\u0118\5n\0\0\u0118\u0119\5t\0\0\u0119\u011a\5i\0\0\u011a\u011b\5n\0\0"+
		"\u011b\u011c\5u\0\0\u011c\u011d\5e\0\0\u011d\16\1\0\0\0\u011e\u011f\5"+
		"d\0\0\u011f\u0120\5e\0\0\u0120\u0121\5f\0\0\u0121\u0122\5a\0\0\u0122\u0123"+
		"\5u\0\0\u0123\u0124\5l\0\0\u0124\u0125\5t\0\0\u0125\20\1\0\0\0\u0126\u0127"+
		"\5d\0\0\u0127\u0128\5e\0\0\u0128\u0129\5f\0\0\u0129\u012a\5e\0\0\u012a"+
		"\u012b\5r\0\0\u012b\22\1\0\0\0\u012c\u012d\5e\0\0\u012d\u012e\5l\0\0\u012e"+
		"\u012f\5s\0\0\u012f\u0130\5e\0\0\u0130\24\1\0\0\0\u0131\u0132\5f\0\0\u0132"+
		"\u0133\5a\0\0\u0133\u0134\5l\0\0\u0134\u0135\5l\0\0\u0135\u0136\5t\0\0"+
		"\u0136\u0137\5h\0\0\u0137\u0138\5r\0\0\u0138\u0139\5o\0\0\u0139\u013a"+
		"\5u\0\0\u013a\u013b\5g\0\0\u013b\u013c\5h\0\0\u013c\26\1\0\0\0\u013d\u013e"+
		"\5f\0\0\u013e\u013f\5o\0\0\u013f\u0140\5r\0\0\u0140\30\1\0\0\0\u0141\u0142"+
		"\5f\0\0\u0142\u0143\5u\0\0\u0143\u0144\5n\0\0\u0144\u0145\5c\0\0\u0145"+
		"\32\1\0\0\0\u0146\u0147\5g\0\0\u0147\u0148\5o\0\0\u0148\34\1\0\0\0\u0149"+
		"\u014a\5g\0\0\u014a\u014b\5o\0\0\u014b\u014c\5t\0\0\u014c\u014d\5o\0\0"+
		"\u014d\36\1\0\0\0\u014e\u014f\5i\0\0\u014f\u0150\5f\0\0\u0150 \1\0\0\0"+
		"\u0151\u0152\5i\0\0\u0152\u0153\5m\0\0\u0153\u0154\5p\0\0\u0154\u0155"+
		"\5o\0\0\u0155\u0156\5r\0\0\u0156\u0157\5t\0\0\u0157\"\1\0\0\0\u0158\u0159"+
		"\5i\0\0\u0159\u015a\5n\0\0\u015a\u015b\5t\0\0\u015b\u015c\5e\0\0\u015c"+
		"\u015d\5r\0\0\u015d\u015e\5f\0\0\u015e\u015f\5a\0\0\u015f\u0160\5c\0\0"+
		"\u0160\u0161\5e\0\0\u0161$\1\0\0\0\u0162\u0163\5m\0\0\u0163\u0164\5a\0"+
		"\0\u0164\u0165\5p\0\0\u0165&\1\0\0\0\u0166\u0167\5p\0\0\u0167\u0168\5"+
		"a\0\0\u0168\u0169\5c\0\0\u0169\u016a\5k\0\0\u016a\u016b\5a\0\0\u016b\u016c"+
		"\5g\0\0\u016c\u016d\5e\0\0\u016d(\1\0\0\0\u016e\u016f\5r\0\0\u016f\u0170"+
		"\5a\0\0\u0170\u0171\5n\0\0\u0171\u0172\5g\0\0\u0172\u0173\5e\0\0\u0173"+
		"*\1\0\0\0\u0174\u0175\5r\0\0\u0175\u0176\5e\0\0\u0176\u0177\5t\0\0\u0177"+
		"\u0178\5u\0\0\u0178\u0179\5r\0\0\u0179\u017a\5n\0\0\u017a,\1\0\0\0\u017b"+
		"\u017c\5s\0\0\u017c\u017d\5e\0\0\u017d\u017e\5l\0\0\u017e\u017f\5e\0\0"+
		"\u017f\u0180\5c\0\0\u0180\u0181\5t\0\0\u0181.\1\0\0\0\u0182\u0183\5s\0"+
		"\0\u0183\u0184\5t\0\0\u0184\u0185\5r\0\0\u0185\u0186\5u\0\0\u0186\u0187"+
		"\5c\0\0\u0187\u0188\5t\0\0\u0188\60\1\0\0\0\u0189\u018a\5s\0\0\u018a\u018b"+
		"\5w\0\0\u018b\u018c\5i\0\0\u018c\u018d\5t\0\0\u018d\u018e\5c\0\0\u018e"+
		"\u018f\5h\0\0\u018f\62\1\0\0\0\u0190\u0191\5t\0\0\u0191\u0192\5y\0\0\u0192"+
		"\u0193\5p\0\0\u0193\u0194\5e\0\0\u0194\64\1\0\0\0\u0195\u0196\5v\0\0\u0196"+
		"\u0197\5a\0\0\u0197\u0198\5r\0\0\u0198\66\1\0\0\0\u0199\u019a\5+\0\0\u019a"+
		"8\1\0\0\0\u019b\u019c\5-\0\0\u019c:\1\0\0\0\u019d\u019e\5*\0\0\u019e<"+
		"\1\0\0\0\u019f\u01a0\5/\0\0\u01a0>\1\0\0\0\u01a1\u01a2\5%\0\0\u01a2@\1"+
		"\0\0\0\u01a3\u01a4\5&\0\0\u01a4B\1\0\0\0\u01a5\u01a6\5|\0\0\u01a6D\1\0"+
		"\0\0\u01a7\u01a8\5^\0\0\u01a8F\1\0\0\0\u01a9\u01aa\5<\0\0\u01aa\u01ab"+
		"\5<\0\0\u01abH\1\0\0\0\u01ac\u01ad\5>\0\0\u01ad\u01ae\5>\0\0\u01aeJ\1"+
		"\0\0\0\u01af\u01b0\5&\0\0\u01b0\u01b1\5^\0\0\u01b1L\1\0\0\0\u01b2\u01b3"+
		"\5+\0\0\u01b3\u01b4\5=\0\0\u01b4N\1\0\0\0\u01b5\u01b6\5-\0\0\u01b6\u01b7"+
		"\5=\0\0\u01b7P\1\0\0\0\u01b8\u01b9\5*\0\0\u01b9\u01ba\5=\0\0\u01baR\1"+
		"\0\0\0\u01bb\u01bc\5/\0\0\u01bc\u01bd\5=\0\0\u01bdT\1\0\0\0\u01be\u01bf"+
		"\5%\0\0\u01bf\u01c0\5=\0\0\u01c0V\1\0\0\0\u01c1\u01c2\5&\0\0\u01c2\u01c3"+
		"\5=\0\0\u01c3X\1\0\0\0\u01c4\u01c5\5|\0\0\u01c5\u01c6\5=\0\0\u01c6Z\1"+
		"\0\0\0\u01c7\u01c8\5^\0\0\u01c8\u01c9\5=\0\0\u01c9\\\1\0\0\0\u01ca\u01cb"+
		"\5<\0\0\u01cb\u01cc\5<\0\0\u01cc\u01cd\5=\0\0\u01cd^\1\0\0\0\u01ce\u01cf"+
		"\5>\0\0\u01cf\u01d0\5>\0\0\u01d0\u01d1\5=\0\0\u01d1`\1\0\0\0\u01d2\u01d3"+
		"\5&\0\0\u01d3\u01d4\5^\0\0\u01d4\u01d5\5=\0\0\u01d5b\1\0\0\0\u01d6\u01d7"+
		"\5&\0\0\u01d7\u01d8\5&\0\0\u01d8d\1\0\0\0\u01d9\u01da\5|\0\0\u01da\u01db"+
		"\5|\0\0\u01dbf\1\0\0\0\u01dc\u01dd\5<\0\0\u01dd\u01de\5-\0\0\u01deh\1"+
		"\0\0\0\u01df\u01e0\5+\0\0\u01e0\u01e1\5+\0\0\u01e1j\1\0\0\0\u01e2\u01e3"+
		"\5-\0\0\u01e3\u01e4\5-\0\0\u01e4l\1\0\0\0\u01e5\u01e6\5=\0\0\u01e6\u01e7"+
		"\5=\0\0\u01e7n\1\0\0\0\u01e8\u01e9\5<\0\0\u01e9p\1\0\0\0\u01ea\u01eb\5"+
		">\0\0\u01ebr\1\0\0\0\u01ec\u01ed\5=\0\0\u01edt\1\0\0\0\u01ee\u01ef\5!"+
		"\0\0\u01efv\1\0\0\0\u01f0\u01f1\5!\0\0\u01f1\u01f2\5=\0\0\u01f2x\1\0\0"+
		"\0\u01f3\u01f4\5<\0\0\u01f4\u01f5\5=\0\0\u01f5z\1\0\0\0\u01f6\u01f7\5"+
		">\0\0\u01f7\u01f8\5=\0\0\u01f8|\1\0\0\0\u01f9\u01fa\5:\0\0\u01fa\u01fb"+
		"\5=\0\0\u01fb~\1\0\0\0\u01fc\u01fd\5.\0\0\u01fd\u01fe\5.\0\0\u01fe\u01ff"+
		"\5.\0\0\u01ff\u0080\1\0\0\0\u0200\u0201\5(\0\0\u0201\u0082\1\0\0\0\u0202"+
		"\u0203\5)\0\0\u0203\u0084\1\0\0\0\u0204\u0205\5[\0\0\u0205\u0086\1\0\0"+
		"\0\u0206\u0207\5]\0\0\u0207\u0088\1\0\0\0\u0208\u0209\5{\0\0\u0209\u008a"+
		"\1\0\0\0\u020a\u020b\5}\0\0\u020b\u008c\1\0\0\0\u020c\u020d\5,\0\0\u020d"+
		"\u008e\1\0\0\0\u020e\u020f\5.\0\0\u020f\u0090\1\0\0\0\u0210\u0211\5;\0"+
		"\0\u0211\u0092\1\0\0\0\u0212\u0213\5:\0\0\u0213\u0094\1\0\0\0\u0214\u0219"+
		"\3\u00cfe\0\u0215\u0218\3\u00cfe\0\u0216\u0218\3\u00cdd\0\u0217\u0215"+
		"\1\0\0\0\u0217\u0216\1\0\0\0\u0218\u021b\1\0\0\0\u0219\u0217\1\0\0\0\u0219"+
		"\u021a\1\0\0\0\u021a\u0096\1\0\0\0\u021b\u0219\1\0\0\0\u021c\u021e\7\0"+
		"\0\0\u021d\u021c\1\0\0\0\u021e\u021f\1\0\0\0\u021f\u021d\1\0\0\0\u021f"+
		"\u0220\1\0\0\0\u0220\u0098\1\0\0\0\u0221\u0223\5\r\0\0\u0222\u0221\1\0"+
		"\0\0\u0222\u0223\1\0\0\0\u0223\u0224\1\0\0\0\u0224\u0225\5\n\0\0\u0225"+
		"\u009a\1\0\0\0\u0226\u0227\5/\0\0\u0227\u0228\5/\0\0\u0228\u022c\1\0\0"+
		"\0\u0229\u022b\b\1\0\0\u022a\u0229\1\0\0\0\u022b\u022e\1\0\0\0\u022c\u022a"+
		"\1\0\0\0\u022c\u022d\1\0\0\0\u022d\u009c\1\0\0\0\u022e\u022c\1\0\0\0\u022f"+
		"\u0230\5/\0\0\u0230\u0231\5*\0\0\u0231\u0232\1\0\0\0\u0232\u0233\6L\0"+
		"\0\u0233\u009e\1\0\0\0\u0234\u0238\3\u00a1N\0\u0235\u0238\3\u00a3O\0\u0236"+
		"\u0238\3\u00a5P\0\u0237\u0234\1\0\0\0\u0237\u0235\1\0\0\0\u0237\u0236"+
		"\1\0\0\0\u0238\u00a0\1\0\0\0\u0239\u023d\2\619\0\u023a\u023c\3\u00d1f"+
		"\0\u023b\u023a\1\0\0\0\u023c\u023f\1\0\0\0\u023d\u023b\1\0\0\0\u023d\u023e"+
		"\1\0\0\0\u023e\u00a2\1\0\0\0\u023f\u023d\1\0\0\0\u0240\u0244\5\60\0\0"+
		"\u0241\u0243\3\u00d3g\0\u0242\u0241\1\0\0\0\u0243\u0246\1\0\0\0\u0244"+
		"\u0242\1\0\0\0\u0244\u0245\1\0\0\0\u0245\u00a4\1\0\0\0\u0246\u0244\1\0"+
		"\0\0\u0247\u0248\5\60\0\0\u0248\u024a\7\2\0\0\u0249\u024b\3\u00d5h\0\u024a"+
		"\u0249\1\0\0\0\u024b\u024c\1\0\0\0\u024c\u024a\1\0\0\0\u024c\u024d\1\0"+
		"\0\0\u024d\u00a6\1\0\0\0\u024e\u0251\3\u00abS\0\u024f\u0251\3\u00a9R\0"+
		"\u0250\u024e\1\0\0\0\u0250\u024f\1\0\0\0\u0251\u0252\1\0\0\0\u0252\u0253"+
		"\5i\0\0\u0253\u00a8\1\0\0\0\u0254\u0255\3\u00abS\0\u0255\u0257\5.\0\0"+
		"\u0256\u0258\3\u00abS\0\u0257\u0256\1\0\0\0\u0257\u0258\1\0\0\0\u0258"+
		"\u025a\1\0\0\0\u0259\u025b\3\u00adT\0\u025a\u0259\1\0\0\0\u025a\u025b"+
		"\1\0\0\0\u025b\u0265\1\0\0\0\u025c\u025d\3\u00abS\0\u025d\u025e\3\u00ad"+
		"T\0\u025e\u0265\1\0\0\0\u025f\u0260\5.\0\0\u0260\u0262\3\u00abS\0\u0261"+
		"\u0263\3\u00adT\0\u0262\u0261\1\0\0\0\u0262\u0263\1\0\0\0\u0263\u0265"+
		"\1\0\0\0\u0264\u0254\1\0\0\0\u0264\u025c\1\0\0\0\u0264\u025f\1\0\0\0\u0265"+
		"\u00aa\1\0\0\0\u0266\u0268\3\u00d1f\0\u0267\u0266\1\0\0\0\u0268\u0269"+
		"\1\0\0\0\u0269\u0267\1\0\0\0\u0269\u026a\1\0\0\0\u026a\u00ac\1\0\0\0\u026b"+
		"\u026d\7\3\0\0\u026c\u026e\7\4\0\0\u026d\u026c\1\0\0\0\u026d\u026e\1\0"+
		"\0\0\u026e\u026f\1\0\0\0\u026f\u0270\3\u00abS\0\u0270\u00ae\1\0\0\0\u0271"+
		"\u0272\5\'\0\0\u0272\u0273\1\0\0\0\u0273\u0274\6U\1\0\u0274\u00b0\1\0"+
		"\0\0\u0275\u0279\3\u00b9Z\0\u0276\u0279\3\u00bb[\0\u0277\u0279\3\u00bd"+
		"\\\0\u0278\u0275\1\0\0\0\u0278\u0276\1\0\0\0\u0278\u0277\1\0\0\0\u0279"+
		"\u00b2\1\0\0\0\u027a\u027d\3\u00b5X\0\u027b\u027d\3\u00b7Y\0\u027c\u027a"+
		"\1\0\0\0\u027c\u027b\1\0\0\0\u027d\u00b4\1\0\0\0\u027e\u027f\5\\\0\0\u027f"+
		"\u0280\3\u00d3g\0\u0280\u0281\3\u00d3g\0\u0281\u0282\3\u00d3g\0\u0282"+
		"\u00b6\1\0\0\0\u0283\u0284\5\\\0\0\u0284\u0285\5x\0\0\u0285\u0286\1\0"+
		"\0\0\u0286\u0287\3\u00d5h\0\u0287\u0288\3\u00d5h\0\u0288\u00b8\1\0\0\0"+
		"\u0289\u028a\5\\\0\0\u028a\u028b\5u\0\0\u028b\u028c\1\0\0\0\u028c\u028d"+
		"\3\u00d5h\0\u028d\u028e\3\u00d5h\0\u028e\u028f\3\u00d5h\0\u028f\u0290"+
		"\3\u00d5h\0\u0290\u00ba\1\0\0\0\u0291\u0292\5\\\0\0\u0292\u0293\5U\0\0"+
		"\u0293\u0294\1\0\0\0\u0294\u0295\3\u00d5h\0\u0295\u0296\3\u00d5h\0\u0296"+
		"\u0297\3\u00d5h\0\u0297\u0298\3\u00d5h\0\u0298\u0299\3\u00d5h\0\u0299"+
		"\u029a\3\u00d5h\0\u029a\u029b\3\u00d5h\0\u029b\u029c\3\u00d5h\0\u029c"+
		"\u00bc\1\0\0\0\u029d\u029e\5\\\0\0\u029e\u029f\7\5\0\0\u029f\u00be\1\0"+
		"\0\0\u02a0\u02a1\5`\0\0\u02a1\u02a2\1\0\0\0\u02a2\u02a3\6]\2\0\u02a3\u00c0"+
		"\1\0\0\0\u02a4\u02a5\5\"\0\0\u02a5\u02a6\1\0\0\0\u02a6\u02a7\6^\3\0\u02a7"+
		"\u00c2\1\0\0\0\u02a8\u02a9\5\n\0\0\u02a9\u00c4\1\0\0\0\u02aa\u02ab\b\6"+
		"\0\0\u02ab\u00c6\1\0\0\0\u02ac\u02ad\b\7\0\0\u02ad\u00c8\1\0\0\0\u02ae"+
		"\u02af\b\b\0\0\u02af\u00ca\1\0\0\0\u02b0\u02b1\7\t\0\0\u02b1\u00cc\1\0"+
		"\0\0\u02b2\u02b3\7\n\0\0\u02b3\u00ce\1\0\0\0\u02b4\u02b7\3\u00cbc\0\u02b5"+
		"\u02b7\5_\0\0\u02b6\u02b4\1\0\0\0\u02b6\u02b5\1\0\0\0\u02b7\u00d0\1\0"+
		"\0\0\u02b8\u02b9\2\609\0\u02b9\u00d2\1\0\0\0\u02ba\u02bb\2\60\67\0\u02bb"+
		"\u00d4\1\0\0\0\u02bc\u02bd\7\13\0\0\u02bd\u00d6\1\0\0\0\u02be\u02bf\t"+
		"\0\0\0\u02bf\u00d8\1\0\0\0\u02c0\u02c1\3\u0099J\0\u02c1\u02c2\1\0\0\0"+
		"\u02c2\u02c3\6j\4\0\u02c3\u00da\1\0\0\0\u02c4\u02c6\b\f\0\0\u02c5\u02c4"+
		"\1\0\0\0\u02c6\u02c7\1\0\0\0\u02c7\u02c5\1\0\0\0\u02c7\u02c8\1\0\0\0\u02c8"+
		"\u02c9\1\0\0\0\u02c9\u02ca\6k\5\0\u02ca\u00dc\1\0\0\0\u02cb\u02cc\5*\0"+
		"\0\u02cc\u02cd\5/\0\0\u02cd\u02ce\1\0\0\0\u02ce\u02cf\6l\6\0\u02cf\u00de"+
		"\1\0\0\0\u02d0\u02d1\5*\0\0\u02d1\u02d2\1\0\0\0\u02d2\u02d3\6m\7\0\u02d3"+
		"\u00e0\1\0\0\0\u02d4\u02d5\t\0\0\0\u02d5\u02d6\1\0\0\0\u02d6\u02d7\6n"+
		"\b\0\u02d7\u00e2\1\0\0\0\u02d8\u02d9\3\u0099J\0\u02d9\u02da\1\0\0\0\u02da"+
		"\u02db\6o\t\0\u02db\u00e4\1\0\0\0\u02dc\u02df\3\u00c9b\0\u02dd\u02df\3"+
		"\u00c3_\0\u02de\u02dc\1\0\0\0\u02de\u02dd\1\0\0\0\u02df\u02e0\1\0\0\0"+
		"\u02e0\u02de\1\0\0\0\u02e0\u02e1\1\0\0\0\u02e1\u02e2\1\0\0\0\u02e2\u02e3"+
		"\6p\n\0\u02e3\u00e6\1\0\0\0\u02e4\u02e5\5`\0\0\u02e5\u02e6\1\0\0\0\u02e6"+
		"\u02e7\6q\13\0\u02e7\u00e8\1\0\0\0\u02e8\u02e9\t\0\0\0\u02e9\u02ea\1\0"+
		"\0\0\u02ea\u02eb\6r\f\0\u02eb\u00ea\1\0\0\0\u02ec\u02ed\3\u00c7a\0\u02ed"+
		"\u02ee\1\0\0\0\u02ee\u02ef\6s\r\0\u02ef\u00ec\1\0\0\0\u02f0\u02f3\3\u00b1"+
		"V\0\u02f1\u02f3\3\u00b3W\0\u02f2\u02f0\1\0\0\0\u02f2\u02f1\1\0\0\0\u02f3"+
		"\u00ee\1\0\0\0\u02f4\u02f5\5\"\0\0\u02f5\u02f6\1\0\0\0\u02f6\u02f7\6u"+
		"\16\0\u02f7\u00f0\1\0\0\0\u02f8\u02f9\3\u0099J\0\u02f9\u02fa\1\0\0\0\u02fa"+
		"\u02fb\6v\17\0\u02fb\u00f2\1\0\0\0\u02fc\u02fd\t\0\0\0\u02fd\u02fe\1\0"+
		"\0\0\u02fe\u02ff\6w\20\0\u02ff\u00f4\1\0\0\0\u0300\u0301\3\u00c5`\0\u0301"+
		"\u0302\1\0\0\0\u0302\u0303\6x\21\0\u0303\u00f6\1\0\0\0\u0304\u0307\3\u00b1"+
		"V\0\u0305\u0307\3\u00b3W\0\u0306\u0304\1\0\0\0\u0306\u0305\1\0\0\0\u0307"+
		"\u00f8\1\0\0\0\u0308\u0309\5\'\0\0\u0309\u030a\1\0\0\0\u030a\u030b\6z"+
		"\22\0\u030b\u00fa\1\0\0\0\u030c\u030d\3\u0099J\0\u030d\u030e\1\0\0\0\u030e"+
		"\u030f\6{\23\0\u030f\u00fc\1\0\0\0\u0310\u0311\t\0\0\0\u0311\u0312\1\0"+
		"\0\0\u0312\u0313\6|\24\0\u0313\u00fe\1\0\0\0\35\0\1\2\3\4\u0217\u0219"+
		"\u021f\u0222\u022c\u0237\u023d\u0244\u024c\u0250\u0257\u025a\u0262\u0264"+
		"\u0269\u026d\u0278\u027c\u02b6\u02c7\u02de\u02e0\u02f2\u0306";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}