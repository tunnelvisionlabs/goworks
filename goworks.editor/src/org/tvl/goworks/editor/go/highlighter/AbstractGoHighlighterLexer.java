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
		RawStringLiteral=82, InterpretedStringLiteral=83, ANYCHAR=84, END_ML_COMMENT=85;
	public static final int BlockComment = 1;
	public static final int RawLiteralMode = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "BlockComment", "RawLiteralMode"
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
		"RawStringLiteral", "InterpretedStringLiteral", "ANYCHAR", "'*/'"
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
		"FLOAT_LITERAL", "Decimals", "Exponent", "CharLiteral", "UNICODE_VALUE_NOSQUOTE", 
		"UNICODE_VALUE_NODQUOTE", "BYTE_VALUE", "OCTAL_BYTE_VALUE", "HexByteValue", 
		"LittleUValue", "BigUValue", "EscapedChar", "RawStringLiteral", "InterpretedStringLiteral", 
		"NEWLINE_CHAR", "UNICODE_CHAR_NOSQUOTE", "UNICODE_CHAR_NODQUOTE", "UNICODE_CHAR_NOBTICK", 
		"UNICODE_LETTER_CHAR", "UNICODE_DIGIT_CHAR", "LETTER_CHAR", "DECIMAL_DIGIT_CHAR", 
		"OCTAL_DIGIT_CHAR", "HEX_DIGIT_CHAR", "ANYCHAR", "BlockComment_NEWLINE", 
		"CONTINUE_ML_COMMENT", "END_ML_COMMENT", "ML_COMMENT_STAR", "BlockComment_ANYCHAR", 
		"RawLiteralNewline", "ContinueRawLiteral", "EndRawLiteral", "RawLiteral_ANYCHAR"
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

			case 86 : UNICODE_VALUE_NOSQUOTE_action(_localctx, actionIndex); break;

			case 87 : UNICODE_VALUE_NODQUOTE_action(_localctx, actionIndex); break;

			case 88 : BYTE_VALUE_action(_localctx, actionIndex); break;

			case 89 : OCTAL_BYTE_VALUE_action(_localctx, actionIndex); break;

			case 90 : HexByteValue_action(_localctx, actionIndex); break;

			case 91 : LittleUValue_action(_localctx, actionIndex); break;

			case 92 : BigUValue_action(_localctx, actionIndex); break;

			case 93 : EscapedChar_action(_localctx, actionIndex); break;

			case 94 : RawStringLiteral_action(_localctx, actionIndex); break;

			case 95 : InterpretedStringLiteral_action(_localctx, actionIndex); break;

			case 96 : NEWLINE_CHAR_action(_localctx, actionIndex); break;

			case 97 : UNICODE_CHAR_NOSQUOTE_action(_localctx, actionIndex); break;

			case 98 : UNICODE_CHAR_NODQUOTE_action(_localctx, actionIndex); break;

			case 99 : UNICODE_CHAR_NOBTICK_action(_localctx, actionIndex); break;

			case 100 : UNICODE_LETTER_CHAR_action(_localctx, actionIndex); break;

			case 101 : UNICODE_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 102 : LETTER_CHAR_action(_localctx, actionIndex); break;

			case 103 : DECIMAL_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 104 : OCTAL_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 105 : HEX_DIGIT_CHAR_action(_localctx, actionIndex); break;

			case 106 : ANYCHAR_action(_localctx, actionIndex); break;

			case 107 : BlockComment_NEWLINE_action(_localctx, actionIndex); break;

			case 108 : CONTINUE_ML_COMMENT_action(_localctx, actionIndex); break;

			case 109 : END_ML_COMMENT_action(_localctx, actionIndex); break;

			case 110 : ML_COMMENT_STAR_action(_localctx, actionIndex); break;

			case 111 : BlockComment_ANYCHAR_action(_localctx, actionIndex); break;

			case 112 : RawLiteralNewline_action(_localctx, actionIndex); break;

			case 113 : ContinueRawLiteral_action(_localctx, actionIndex); break;

			case 114 : EndRawLiteral_action(_localctx, actionIndex); break;

			case 115 : RawLiteral_ANYCHAR_action(_localctx, actionIndex); break;
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
			case 5 : _type = ML_COMMENT;  break;
		}
	}
	public void COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RawLiteral_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 10 : _type = ANYCHAR;  break;
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
	public void UNICODE_VALUE_NODQUOTE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_VALUE_NOSQUOTE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Inc_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CONTINUE_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : _type = ML_COMMENT;  break;
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
			case 1 : pushMode(RawLiteralMode);  break;
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
			case 2 : _type = NEWLINE;  break;
		}
	}
	public void UNICODE_CHAR_NODQUOTE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void END_ML_COMMENT_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 4 : _type = ML_COMMENT; popMode();  break;
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
			case 9 : _type = RawStringLiteral; popMode();  break;
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
		}
	}
	public void ContinueRawLiteral_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 8 : _type = RawStringLiteral;  break;
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
	public void BlockComment_ANYCHAR_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 6 : _type = ANYCHAR;  break;
		}
	}
	public void NEWLINE_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RawLiteralNewline_action(RuleContext<Integer> _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 7 : _type = NEWLINE;  break;
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
		"\2U\u02e8\6\uffff\6\uffff\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4"+
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
		"\2n\7n\2o\7o\2p\7p\2q\7q\2r\7r\2s\7s\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1"+
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
		">\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1H\1H\1H\5H\u0204"+
		"\bH\nH\fH\u0207\tH\1I\4I\u020a\bI\13I\fI\u020b\1J\3J\u020f\bJ\1J\1J\1"+
		"K\1K\1K\1K\5K\u0217\bK\nK\fK\u021a\tK\1L\1L\1L\1L\1L\1M\1M\1M\3M\u0224"+
		"\bM\1N\1N\5N\u0228\bN\nN\fN\u022b\tN\1O\1O\5O\u022f\bO\nO\fO\u0232\tO"+
		"\1P\1P\1P\4P\u0237\bP\13P\fP\u0238\1Q\1Q\3Q\u023d\bQ\1Q\1Q\1R\1R\1R\3"+
		"R\u0244\bR\1R\3R\u0247\bR\1R\1R\1R\1R\1R\1R\3R\u024f\bR\3R\u0251\bR\1"+
		"S\4S\u0254\bS\13S\fS\u0255\1T\1T\3T\u025a\bT\1T\1T\1U\1U\1U\3U\u0261\b"+
		"U\1U\1U\1V\1V\1V\1V\3V\u0269\bV\1W\1W\1W\1W\3W\u026f\bW\1X\1X\3X\u0273"+
		"\bX\1Y\1Y\1Y\1Y\1Y\1Z\1Z\1Z\1Z\1Z\1Z\1[\1[\1[\1[\1[\1[\1[\1[\1\\\1\\\1"+
		"\\\1\\\1\\\1\\\1\\\1\\\1\\\1\\\1\\\1\\\1]\1]\1]\1^\1^\1^\1^\1_\1_\1_\5"+
		"_\u029e\b_\n_\f_\u02a1\t_\1_\1_\1`\1`\1a\1a\1b\1b\1c\1c\1d\1d\1e\1e\1"+
		"f\1f\3f\u02b3\bf\1g\1g\1h\1h\1i\1i\1j\1j\1k\1k\1k\1k\1l\4l\u02c2\bl\13"+
		"l\fl\u02c3\1l\1l\1m\1m\1m\1m\1m\1n\1n\1n\1n\1o\1o\1o\1o\1p\1p\1p\1p\1"+
		"q\1q\4q\u02db\bq\13q\fq\u02dc\1q\1q\1r\1r\1r\1r\1s\1s\1s\1st\3\1\uffff"+
		"\5\2\uffff\7\3\uffff\t\4\uffff\13\5\uffff\r\6\uffff\17\7\uffff\21\b\uffff"+
		"\23\t\uffff\25\n\uffff\27\13\uffff\31\f\uffff\33\r\uffff\35\16\uffff\37"+
		"\17\uffff!\20\uffff#\21\uffff%\22\uffff\'\23\uffff)\24\uffff+\25\uffff"+
		"-\26\uffff/\27\uffff\61\30\uffff\63\31\uffff\65\32\uffff\67\33\uffff9"+
		"\34\uffff;\35\uffff=\36\uffff?\37\uffffA \uffffC!\uffffE\"\uffffG#\uffff"+
		"I$\uffffK%\uffffM&\uffffO\'\uffffQ(\uffffS)\uffffU*\uffffW+\uffffY,\uffff"+
		"[-\uffff].\uffff_/\uffffa\60\uffffc\61\uffffe\62\uffffg\63\uffffi\64\uffff"+
		"k\65\uffffm\66\uffffo\67\uffffq8\uffffs9\uffffu:\uffffw;\uffffy<\uffff"+
		"{=\uffff}>\uffff\177?\uffff\u0081@\uffff\u0083A\uffff\u0085B\uffff\u0087"+
		"C\uffff\u0089D\uffff\u008bE\uffff\u008dF\uffff\u008fG\uffff\u0091H\uffff"+
		"\u0093I\uffff\u0095J\uffff\u0097K\uffff\u0099L\uffff\u009bM\0\u009dN\uffff"+
		"\u009f\0\uffff\u00a1\0\uffff\u00a3\0\uffff\u00a5O\uffff\u00a7P\uffff\u00a9"+
		"\0\uffff\u00ab\0\uffff\u00adQ\uffff\u00af\0\uffff\u00b1\0\uffff\u00b3"+
		"\0\uffff\u00b5\0\uffff\u00b7\0\uffff\u00b9\0\uffff\u00bb\0\uffff\u00bd"+
		"\0\uffff\u00bfR\1\u00c1S\uffff\u00c3\0\uffff\u00c5\0\uffff\u00c7\0\uffff"+
		"\u00c9\0\uffff\u00cb\0\uffff\u00cd\0\uffff\u00cf\0\uffff\u00d1\0\uffff"+
		"\u00d3\0\uffff\u00d5\0\uffff\u00d7T\uffff\u00d9\0\2\u00db\0\3\u00ddU\4"+
		"\u00df\0\5\u00e1\0\6\u00e3\0\7\u00e5\0\b\u00e7\0\t\u00e9\0\n\3\0\1\2\r"+
		"\2\t\t  \2\n\n\r\r\2XXxx\2EEee\2++--\t\"\"\'\'\\\\abffnnrrttvv\3\n\n\'"+
		"\'\\\\\3\n\n\"\"\\\\\2\n\n``\3AZaz\u0101\u0101\2\609\u0660\u0660\3\60"+
		"9AFaf\3\n\n\r\r**\u02ee\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0"+
		"\0\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25"+
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
		"\1\0\0\0\0\u00a5\1\0\0\0\0\u00a7\1\0\0\0\0\u00ad\1\0\0\0\0\u00bf\1\0\0"+
		"\0\0\u00c1\1\0\0\0\0\u00d7\1\0\0\0\1\u00d9\1\0\0\0\1\u00db\1\0\0\0\1\u00dd"+
		"\1\0\0\0\1\u00df\1\0\0\0\1\u00e1\1\0\0\0\2\u00e3\1\0\0\0\2\u00e5\1\0\0"+
		"\0\2\u00e7\1\0\0\0\2\u00e9\1\0\0\0\3\u00eb\1\0\0\0\5\u00f1\1\0\0\0\7\u00f6"+
		"\1\0\0\0\t\u00fb\1\0\0\0\13\u0101\1\0\0\0\r\u010a\1\0\0\0\17\u0112\1\0"+
		"\0\0\21\u0118\1\0\0\0\23\u011d\1\0\0\0\25\u0129\1\0\0\0\27\u012d\1\0\0"+
		"\0\31\u0132\1\0\0\0\33\u0135\1\0\0\0\35\u013a\1\0\0\0\37\u013d\1\0\0\0"+
		"!\u0144\1\0\0\0#\u014e\1\0\0\0%\u0152\1\0\0\0\'\u015a\1\0\0\0)\u0160\1"+
		"\0\0\0+\u0167\1\0\0\0-\u016e\1\0\0\0/\u0175\1\0\0\0\61\u017c\1\0\0\0\63"+
		"\u0181\1\0\0\0\65\u0185\1\0\0\0\67\u0187\1\0\0\09\u0189\1\0\0\0;\u018b"+
		"\1\0\0\0=\u018d\1\0\0\0?\u018f\1\0\0\0A\u0191\1\0\0\0C\u0193\1\0\0\0E"+
		"\u0195\1\0\0\0G\u0198\1\0\0\0I\u019b\1\0\0\0K\u019e\1\0\0\0M\u01a1\1\0"+
		"\0\0O\u01a4\1\0\0\0Q\u01a7\1\0\0\0S\u01aa\1\0\0\0U\u01ad\1\0\0\0W\u01b0"+
		"\1\0\0\0Y\u01b3\1\0\0\0[\u01b6\1\0\0\0]\u01ba\1\0\0\0_\u01be\1\0\0\0a"+
		"\u01c2\1\0\0\0c\u01c5\1\0\0\0e\u01c8\1\0\0\0g\u01cb\1\0\0\0i\u01ce\1\0"+
		"\0\0k\u01d1\1\0\0\0m\u01d4\1\0\0\0o\u01d6\1\0\0\0q\u01d8\1\0\0\0s\u01da"+
		"\1\0\0\0u\u01dc\1\0\0\0w\u01df\1\0\0\0y\u01e2\1\0\0\0{\u01e5\1\0\0\0}"+
		"\u01e8\1\0\0\0\177\u01ec\1\0\0\0\u0081\u01ee\1\0\0\0\u0083\u01f0\1\0\0"+
		"\0\u0085\u01f2\1\0\0\0\u0087\u01f4\1\0\0\0\u0089\u01f6\1\0\0\0\u008b\u01f8"+
		"\1\0\0\0\u008d\u01fa\1\0\0\0\u008f\u01fc\1\0\0\0\u0091\u01fe\1\0\0\0\u0093"+
		"\u0200\1\0\0\0\u0095\u0209\1\0\0\0\u0097\u020e\1\0\0\0\u0099\u0212\1\0"+
		"\0\0\u009b\u021b\1\0\0\0\u009d\u0223\1\0\0\0\u009f\u0225\1\0\0\0\u00a1"+
		"\u022c\1\0\0\0\u00a3\u0233\1\0\0\0\u00a5\u023c\1\0\0\0\u00a7\u0250\1\0"+
		"\0\0\u00a9\u0253\1\0\0\0\u00ab\u0257\1\0\0\0\u00ad\u025d\1\0\0\0\u00af"+
		"\u0268\1\0\0\0\u00b1\u026e\1\0\0\0\u00b3\u0272\1\0\0\0\u00b5\u0274\1\0"+
		"\0\0\u00b7\u0279\1\0\0\0\u00b9\u027f\1\0\0\0\u00bb\u0287\1\0\0\0\u00bd"+
		"\u0293\1\0\0\0\u00bf\u0296\1\0\0\0\u00c1\u029a\1\0\0\0\u00c3\u02a4\1\0"+
		"\0\0\u00c5\u02a6\1\0\0\0\u00c7\u02a8\1\0\0\0\u00c9\u02aa\1\0\0\0\u00cb"+
		"\u02ac\1\0\0\0\u00cd\u02ae\1\0\0\0\u00cf\u02b2\1\0\0\0\u00d1\u02b4\1\0"+
		"\0\0\u00d3\u02b6\1\0\0\0\u00d5\u02b8\1\0\0\0\u00d7\u02ba\1\0\0\0\u00d9"+
		"\u02bc\1\0\0\0\u00db\u02c1\1\0\0\0\u00dd\u02c7\1\0\0\0\u00df\u02cc\1\0"+
		"\0\0\u00e1\u02d0\1\0\0\0\u00e3\u02d4\1\0\0\0\u00e5\u02da\1\0\0\0\u00e7"+
		"\u02e0\1\0\0\0\u00e9\u02e4\1\0\0\0\u00eb\u00ec\5b\0\0\u00ec\u00ed\5r\0"+
		"\0\u00ed\u00ee\5e\0\0\u00ee\u00ef\5a\0\0\u00ef\u00f0\5k\0\0\u00f0\4\1"+
		"\0\0\0\u00f1\u00f2\5c\0\0\u00f2\u00f3\5a\0\0\u00f3\u00f4\5s\0\0\u00f4"+
		"\u00f5\5e\0\0\u00f5\6\1\0\0\0\u00f6\u00f7\5c\0\0\u00f7\u00f8\5h\0\0\u00f8"+
		"\u00f9\5a\0\0\u00f9\u00fa\5n\0\0\u00fa\b\1\0\0\0\u00fb\u00fc\5c\0\0\u00fc"+
		"\u00fd\5o\0\0\u00fd\u00fe\5n\0\0\u00fe\u00ff\5s\0\0\u00ff\u0100\5t\0\0"+
		"\u0100\n\1\0\0\0\u0101\u0102\5c\0\0\u0102\u0103\5o\0\0\u0103\u0104\5n"+
		"\0\0\u0104\u0105\5t\0\0\u0105\u0106\5i\0\0\u0106\u0107\5n\0\0\u0107\u0108"+
		"\5u\0\0\u0108\u0109\5e\0\0\u0109\f\1\0\0\0\u010a\u010b\5d\0\0\u010b\u010c"+
		"\5e\0\0\u010c\u010d\5f\0\0\u010d\u010e\5a\0\0\u010e\u010f\5u\0\0\u010f"+
		"\u0110\5l\0\0\u0110\u0111\5t\0\0\u0111\16\1\0\0\0\u0112\u0113\5d\0\0\u0113"+
		"\u0114\5e\0\0\u0114\u0115\5f\0\0\u0115\u0116\5e\0\0\u0116\u0117\5r\0\0"+
		"\u0117\20\1\0\0\0\u0118\u0119\5e\0\0\u0119\u011a\5l\0\0\u011a\u011b\5"+
		"s\0\0\u011b\u011c\5e\0\0\u011c\22\1\0\0\0\u011d\u011e\5f\0\0\u011e\u011f"+
		"\5a\0\0\u011f\u0120\5l\0\0\u0120\u0121\5l\0\0\u0121\u0122\5t\0\0\u0122"+
		"\u0123\5h\0\0\u0123\u0124\5r\0\0\u0124\u0125\5o\0\0\u0125\u0126\5u\0\0"+
		"\u0126\u0127\5g\0\0\u0127\u0128\5h\0\0\u0128\24\1\0\0\0\u0129\u012a\5"+
		"f\0\0\u012a\u012b\5o\0\0\u012b\u012c\5r\0\0\u012c\26\1\0\0\0\u012d\u012e"+
		"\5f\0\0\u012e\u012f\5u\0\0\u012f\u0130\5n\0\0\u0130\u0131\5c\0\0\u0131"+
		"\30\1\0\0\0\u0132\u0133\5g\0\0\u0133\u0134\5o\0\0\u0134\32\1\0\0\0\u0135"+
		"\u0136\5g\0\0\u0136\u0137\5o\0\0\u0137\u0138\5t\0\0\u0138\u0139\5o\0\0"+
		"\u0139\34\1\0\0\0\u013a\u013b\5i\0\0\u013b\u013c\5f\0\0\u013c\36\1\0\0"+
		"\0\u013d\u013e\5i\0\0\u013e\u013f\5m\0\0\u013f\u0140\5p\0\0\u0140\u0141"+
		"\5o\0\0\u0141\u0142\5r\0\0\u0142\u0143\5t\0\0\u0143 \1\0\0\0\u0144\u0145"+
		"\5i\0\0\u0145\u0146\5n\0\0\u0146\u0147\5t\0\0\u0147\u0148\5e\0\0\u0148"+
		"\u0149\5r\0\0\u0149\u014a\5f\0\0\u014a\u014b\5a\0\0\u014b\u014c\5c\0\0"+
		"\u014c\u014d\5e\0\0\u014d\"\1\0\0\0\u014e\u014f\5m\0\0\u014f\u0150\5a"+
		"\0\0\u0150\u0151\5p\0\0\u0151$\1\0\0\0\u0152\u0153\5p\0\0\u0153\u0154"+
		"\5a\0\0\u0154\u0155\5c\0\0\u0155\u0156\5k\0\0\u0156\u0157\5a\0\0\u0157"+
		"\u0158\5g\0\0\u0158\u0159\5e\0\0\u0159&\1\0\0\0\u015a\u015b\5r\0\0\u015b"+
		"\u015c\5a\0\0\u015c\u015d\5n\0\0\u015d\u015e\5g\0\0\u015e\u015f\5e\0\0"+
		"\u015f(\1\0\0\0\u0160\u0161\5r\0\0\u0161\u0162\5e\0\0\u0162\u0163\5t\0"+
		"\0\u0163\u0164\5u\0\0\u0164\u0165\5r\0\0\u0165\u0166\5n\0\0\u0166*\1\0"+
		"\0\0\u0167\u0168\5s\0\0\u0168\u0169\5e\0\0\u0169\u016a\5l\0\0\u016a\u016b"+
		"\5e\0\0\u016b\u016c\5c\0\0\u016c\u016d\5t\0\0\u016d,\1\0\0\0\u016e\u016f"+
		"\5s\0\0\u016f\u0170\5t\0\0\u0170\u0171\5r\0\0\u0171\u0172\5u\0\0\u0172"+
		"\u0173\5c\0\0\u0173\u0174\5t\0\0\u0174.\1\0\0\0\u0175\u0176\5s\0\0\u0176"+
		"\u0177\5w\0\0\u0177\u0178\5i\0\0\u0178\u0179\5t\0\0\u0179\u017a\5c\0\0"+
		"\u017a\u017b\5h\0\0\u017b\60\1\0\0\0\u017c\u017d\5t\0\0\u017d\u017e\5"+
		"y\0\0\u017e\u017f\5p\0\0\u017f\u0180\5e\0\0\u0180\62\1\0\0\0\u0181\u0182"+
		"\5v\0\0\u0182\u0183\5a\0\0\u0183\u0184\5r\0\0\u0184\64\1\0\0\0\u0185\u0186"+
		"\5+\0\0\u0186\66\1\0\0\0\u0187\u0188\5-\0\0\u01888\1\0\0\0\u0189\u018a"+
		"\5*\0\0\u018a:\1\0\0\0\u018b\u018c\5/\0\0\u018c<\1\0\0\0\u018d\u018e\5"+
		"%\0\0\u018e>\1\0\0\0\u018f\u0190\5&\0\0\u0190@\1\0\0\0\u0191\u0192\5|"+
		"\0\0\u0192B\1\0\0\0\u0193\u0194\5^\0\0\u0194D\1\0\0\0\u0195\u0196\5<\0"+
		"\0\u0196\u0197\5<\0\0\u0197F\1\0\0\0\u0198\u0199\5>\0\0\u0199\u019a\5"+
		">\0\0\u019aH\1\0\0\0\u019b\u019c\5&\0\0\u019c\u019d\5^\0\0\u019dJ\1\0"+
		"\0\0\u019e\u019f\5+\0\0\u019f\u01a0\5=\0\0\u01a0L\1\0\0\0\u01a1\u01a2"+
		"\5-\0\0\u01a2\u01a3\5=\0\0\u01a3N\1\0\0\0\u01a4\u01a5\5*\0\0\u01a5\u01a6"+
		"\5=\0\0\u01a6P\1\0\0\0\u01a7\u01a8\5/\0\0\u01a8\u01a9\5=\0\0\u01a9R\1"+
		"\0\0\0\u01aa\u01ab\5%\0\0\u01ab\u01ac\5=\0\0\u01acT\1\0\0\0\u01ad\u01ae"+
		"\5&\0\0\u01ae\u01af\5=\0\0\u01afV\1\0\0\0\u01b0\u01b1\5|\0\0\u01b1\u01b2"+
		"\5=\0\0\u01b2X\1\0\0\0\u01b3\u01b4\5^\0\0\u01b4\u01b5\5=\0\0\u01b5Z\1"+
		"\0\0\0\u01b6\u01b7\5<\0\0\u01b7\u01b8\5<\0\0\u01b8\u01b9\5=\0\0\u01b9"+
		"\\\1\0\0\0\u01ba\u01bb\5>\0\0\u01bb\u01bc\5>\0\0\u01bc\u01bd\5=\0\0\u01bd"+
		"^\1\0\0\0\u01be\u01bf\5&\0\0\u01bf\u01c0\5^\0\0\u01c0\u01c1\5=\0\0\u01c1"+
		"`\1\0\0\0\u01c2\u01c3\5&\0\0\u01c3\u01c4\5&\0\0\u01c4b\1\0\0\0\u01c5\u01c6"+
		"\5|\0\0\u01c6\u01c7\5|\0\0\u01c7d\1\0\0\0\u01c8\u01c9\5<\0\0\u01c9\u01ca"+
		"\5-\0\0\u01caf\1\0\0\0\u01cb\u01cc\5+\0\0\u01cc\u01cd\5+\0\0\u01cdh\1"+
		"\0\0\0\u01ce\u01cf\5-\0\0\u01cf\u01d0\5-\0\0\u01d0j\1\0\0\0\u01d1\u01d2"+
		"\5=\0\0\u01d2\u01d3\5=\0\0\u01d3l\1\0\0\0\u01d4\u01d5\5<\0\0\u01d5n\1"+
		"\0\0\0\u01d6\u01d7\5>\0\0\u01d7p\1\0\0\0\u01d8\u01d9\5=\0\0\u01d9r\1\0"+
		"\0\0\u01da\u01db\5!\0\0\u01dbt\1\0\0\0\u01dc\u01dd\5!\0\0\u01dd\u01de"+
		"\5=\0\0\u01dev\1\0\0\0\u01df\u01e0\5<\0\0\u01e0\u01e1\5=\0\0\u01e1x\1"+
		"\0\0\0\u01e2\u01e3\5>\0\0\u01e3\u01e4\5=\0\0\u01e4z\1\0\0\0\u01e5\u01e6"+
		"\5:\0\0\u01e6\u01e7\5=\0\0\u01e7|\1\0\0\0\u01e8\u01e9\5.\0\0\u01e9\u01ea"+
		"\5.\0\0\u01ea\u01eb\5.\0\0\u01eb~\1\0\0\0\u01ec\u01ed\5(\0\0\u01ed\u0080"+
		"\1\0\0\0\u01ee\u01ef\5)\0\0\u01ef\u0082\1\0\0\0\u01f0\u01f1\5[\0\0\u01f1"+
		"\u0084\1\0\0\0\u01f2\u01f3\5]\0\0\u01f3\u0086\1\0\0\0\u01f4\u01f5\5{\0"+
		"\0\u01f5\u0088\1\0\0\0\u01f6\u01f7\5}\0\0\u01f7\u008a\1\0\0\0\u01f8\u01f9"+
		"\5,\0\0\u01f9\u008c\1\0\0\0\u01fa\u01fb\5.\0\0\u01fb\u008e\1\0\0\0\u01fc"+
		"\u01fd\5;\0\0\u01fd\u0090\1\0\0\0\u01fe\u01ff\5:\0\0\u01ff\u0092\1\0\0"+
		"\0\u0200\u0205\3\u00cff\0\u0201\u0204\3\u00cff\0\u0202\u0204\3\u00cde"+
		"\0\u0203\u0201\1\0\0\0\u0203\u0202\1\0\0\0\u0204\u0207\1\0\0\0\u0205\u0203"+
		"\1\0\0\0\u0205\u0206\1\0\0\0\u0206\u0094\1\0\0\0\u0207\u0205\1\0\0\0\u0208"+
		"\u020a\7\0\0\0\u0209\u0208\1\0\0\0\u020a\u020b\1\0\0\0\u020b\u0209\1\0"+
		"\0\0\u020b\u020c\1\0\0\0\u020c\u0096\1\0\0\0\u020d\u020f\5\r\0\0\u020e"+
		"\u020d\1\0\0\0\u020e\u020f\1\0\0\0\u020f\u0210\1\0\0\0\u0210\u0211\5\n"+
		"\0\0\u0211\u0098\1\0\0\0\u0212\u0213\5/\0\0\u0213\u0214\5/\0\0\u0214\u0218"+
		"\1\0\0\0\u0215\u0217\b\1\0\0\u0216\u0215\1\0\0\0\u0217\u021a\1\0\0\0\u0218"+
		"\u0216\1\0\0\0\u0218\u0219\1\0\0\0\u0219\u009a\1\0\0\0\u021a\u0218\1\0"+
		"\0\0\u021b\u021c\5/\0\0\u021c\u021d\5*\0\0\u021d\u021e\1\0\0\0\u021e\u021f"+
		"\6L\0\0\u021f\u009c\1\0\0\0\u0220\u0224\3\u009fN\0\u0221\u0224\3\u00a1"+
		"O\0\u0222\u0224\3\u00a3P\0\u0223\u0220\1\0\0\0\u0223\u0221\1\0\0\0\u0223"+
		"\u0222\1\0\0\0\u0224\u009e\1\0\0\0\u0225\u0229\2\619\0\u0226\u0228\3\u00d1"+
		"g\0\u0227\u0226\1\0\0\0\u0228\u022b\1\0\0\0\u0229\u0227\1\0\0\0\u0229"+
		"\u022a\1\0\0\0\u022a\u00a0\1\0\0\0\u022b\u0229\1\0\0\0\u022c\u0230\5\60"+
		"\0\0\u022d\u022f\3\u00d3h\0\u022e\u022d\1\0\0\0\u022f\u0232\1\0\0\0\u0230"+
		"\u022e\1\0\0\0\u0230\u0231\1\0\0\0\u0231\u00a2\1\0\0\0\u0232\u0230\1\0"+
		"\0\0\u0233\u0234\5\60\0\0\u0234\u0236\7\2\0\0\u0235\u0237\3\u00d5i\0\u0236"+
		"\u0235\1\0\0\0\u0237\u0238\1\0\0\0\u0238\u0236\1\0\0\0\u0238\u0239\1\0"+
		"\0\0\u0239\u00a4\1\0\0\0\u023a\u023d\3\u00a9S\0\u023b\u023d\3\u00a7R\0"+
		"\u023c\u023a\1\0\0\0\u023c\u023b\1\0\0\0\u023d\u023e\1\0\0\0\u023e\u023f"+
		"\5i\0\0\u023f\u00a6\1\0\0\0\u0240\u0241\3\u00a9S\0\u0241\u0243\5.\0\0"+
		"\u0242\u0244\3\u00a9S\0\u0243\u0242\1\0\0\0\u0243\u0244\1\0\0\0\u0244"+
		"\u0246\1\0\0\0\u0245\u0247\3\u00abT\0\u0246\u0245\1\0\0\0\u0246\u0247"+
		"\1\0\0\0\u0247\u0251\1\0\0\0\u0248\u0249\3\u00a9S\0\u0249\u024a\3\u00ab"+
		"T\0\u024a\u0251\1\0\0\0\u024b\u024c\5.\0\0\u024c\u024e\3\u00a9S\0\u024d"+
		"\u024f\3\u00abT\0\u024e\u024d\1\0\0\0\u024e\u024f\1\0\0\0\u024f\u0251"+
		"\1\0\0\0\u0250\u0240\1\0\0\0\u0250\u0248\1\0\0\0\u0250\u024b\1\0\0\0\u0251"+
		"\u00a8\1\0\0\0\u0252\u0254\3\u00d1g\0\u0253\u0252\1\0\0\0\u0254\u0255"+
		"\1\0\0\0\u0255\u0253\1\0\0\0\u0255\u0256\1\0\0\0\u0256\u00aa\1\0\0\0\u0257"+
		"\u0259\7\3\0\0\u0258\u025a\7\4\0\0\u0259\u0258\1\0\0\0\u0259\u025a\1\0"+
		"\0\0\u025a\u025b\1\0\0\0\u025b\u025c\3\u00a9S\0\u025c\u00ac\1\0\0\0\u025d"+
		"\u0260\5\'\0\0\u025e\u0261\3\u00afV\0\u025f\u0261\3\u00b3X\0\u0260\u025e"+
		"\1\0\0\0\u0260\u025f\1\0\0\0\u0261\u0262\1\0\0\0\u0262\u0263\5\'\0\0\u0263"+
		"\u00ae\1\0\0\0\u0264\u0269\3\u00c5a\0\u0265\u0269\3\u00b9[\0\u0266\u0269"+
		"\3\u00bb\\\0\u0267\u0269\3\u00bd]\0\u0268\u0264\1\0\0\0\u0268\u0265\1"+
		"\0\0\0\u0268\u0266\1\0\0\0\u0268\u0267\1\0\0\0\u0269\u00b0\1\0\0\0\u026a"+
		"\u026f\3\u00c7b\0\u026b\u026f\3\u00b9[\0\u026c\u026f\3\u00bb\\\0\u026d"+
		"\u026f\3\u00bd]\0\u026e\u026a\1\0\0\0\u026e\u026b\1\0\0\0\u026e\u026c"+
		"\1\0\0\0\u026e\u026d\1\0\0\0\u026f\u00b2\1\0\0\0\u0270\u0273\3\u00b5Y"+
		"\0\u0271\u0273\3\u00b7Z\0\u0272\u0270\1\0\0\0\u0272\u0271\1\0\0\0\u0273"+
		"\u00b4\1\0\0\0\u0274\u0275\5\\\0\0\u0275\u0276\3\u00d3h\0\u0276\u0277"+
		"\3\u00d3h\0\u0277\u0278\3\u00d3h\0\u0278\u00b6\1\0\0\0\u0279\u027a\5\\"+
		"\0\0\u027a\u027b\5x\0\0\u027b\u027c\1\0\0\0\u027c\u027d\3\u00d5i\0\u027d"+
		"\u027e\3\u00d5i\0\u027e\u00b8\1\0\0\0\u027f\u0280\5\\\0\0\u0280\u0281"+
		"\5u\0\0\u0281\u0282\1\0\0\0\u0282\u0283\3\u00d5i\0\u0283\u0284\3\u00d5"+
		"i\0\u0284\u0285\3\u00d5i\0\u0285\u0286\3\u00d5i\0\u0286\u00ba\1\0\0\0"+
		"\u0287\u0288\5\\\0\0\u0288\u0289\5U\0\0\u0289\u028a\1\0\0\0\u028a\u028b"+
		"\3\u00d5i\0\u028b\u028c\3\u00d5i\0\u028c\u028d\3\u00d5i\0\u028d\u028e"+
		"\3\u00d5i\0\u028e\u028f\3\u00d5i\0\u028f\u0290\3\u00d5i\0\u0290\u0291"+
		"\3\u00d5i\0\u0291\u0292\3\u00d5i\0\u0292\u00bc\1\0\0\0\u0293\u0294\5\\"+
		"\0\0\u0294\u0295\7\5\0\0\u0295\u00be\1\0\0\0\u0296\u0297\5`\0\0\u0297"+
		"\u0298\1\0\0\0\u0298\u0299\6^\1\0\u0299\u00c0\1\0\0\0\u029a\u029f\5\""+
		"\0\0\u029b\u029e\3\u00b1W\0\u029c\u029e\3\u00b3X\0\u029d\u029b\1\0\0\0"+
		"\u029d\u029c\1\0\0\0\u029e\u02a1\1\0\0\0\u029f\u029d\1\0\0\0\u029f\u02a0"+
		"\1\0\0\0\u02a0\u02a2\1\0\0\0\u02a1\u029f\1\0\0\0\u02a2\u02a3\5\"\0\0\u02a3"+
		"\u00c2\1\0\0\0\u02a4\u02a5\5\n\0\0\u02a5\u00c4\1\0\0\0\u02a6\u02a7\b\6"+
		"\0\0\u02a7\u00c6\1\0\0\0\u02a8\u02a9\b\7\0\0\u02a9\u00c8\1\0\0\0\u02aa"+
		"\u02ab\b\b\0\0\u02ab\u00ca\1\0\0\0\u02ac\u02ad\7\t\0\0\u02ad\u00cc\1\0"+
		"\0\0\u02ae\u02af\7\n\0\0\u02af\u00ce\1\0\0\0\u02b0\u02b3\3\u00cbd\0\u02b1"+
		"\u02b3\5_\0\0\u02b2\u02b0\1\0\0\0\u02b2\u02b1\1\0\0\0\u02b3\u00d0\1\0"+
		"\0\0\u02b4\u02b5\2\609\0\u02b5\u00d2\1\0\0\0\u02b6\u02b7\2\60\67\0\u02b7"+
		"\u00d4\1\0\0\0\u02b8\u02b9\7\13\0\0\u02b9\u00d6\1\0\0\0\u02ba\u02bb\t"+
		"\0\0\0\u02bb\u00d8\1\0\0\0\u02bc\u02bd\3\u0097J\0\u02bd\u02be\1\0\0\0"+
		"\u02be\u02bf\6k\2\0\u02bf\u00da\1\0\0\0\u02c0\u02c2\b\f\0\0\u02c1\u02c0"+
		"\1\0\0\0\u02c2\u02c3\1\0\0\0\u02c3\u02c1\1\0\0\0\u02c3\u02c4\1\0\0\0\u02c4"+
		"\u02c5\1\0\0\0\u02c5\u02c6\6l\3\0\u02c6\u00dc\1\0\0\0\u02c7\u02c8\5*\0"+
		"\0\u02c8\u02c9\5/\0\0\u02c9\u02ca\1\0\0\0\u02ca\u02cb\6m\4\0\u02cb\u00de"+
		"\1\0\0\0\u02cc\u02cd\5*\0\0\u02cd\u02ce\1\0\0\0\u02ce\u02cf\6n\5\0\u02cf"+
		"\u00e0\1\0\0\0\u02d0\u02d1\t\0\0\0\u02d1\u02d2\1\0\0\0\u02d2\u02d3\6o"+
		"\6\0\u02d3\u00e2\1\0\0\0\u02d4\u02d5\3\u0097J\0\u02d5\u02d6\1\0\0\0\u02d6"+
		"\u02d7\6p\7\0\u02d7\u00e4\1\0\0\0\u02d8\u02db\3\u00c9c\0\u02d9\u02db\3"+
		"\u00c3`\0\u02da\u02d8\1\0\0\0\u02da\u02d9\1\0\0\0\u02db\u02dc\1\0\0\0"+
		"\u02dc\u02da\1\0\0\0\u02dc\u02dd\1\0\0\0\u02dd\u02de\1\0\0\0\u02de\u02df"+
		"\6q\b\0\u02df\u00e6\1\0\0\0\u02e0\u02e1\5`\0\0\u02e1\u02e2\1\0\0\0\u02e2"+
		"\u02e3\6r\t\0\u02e3\u00e8\1\0\0\0\u02e4\u02e5\t\0\0\0\u02e5\u02e6\1\0"+
		"\0\0\u02e6\u02e7\6s\n\0\u02e7\u00ea\1\0\0\0\35\0\0\1\0\2\0\u0203\0\u0205"+
		"\0\u020b\0\u020e\0\u0218\0\u0223\0\u0229\0\u0230\0\u0238\0\u023c\0\u0243"+
		"\0\u0246\0\u024e\0\u0250\0\u0255\0\u0259\0\u0260\0\u0268\0\u026e\0\u0272"+
		"\0\u029d\0\u029f\0\u02b2\0\u02c3\0\u02da\0\u02dc\0";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}