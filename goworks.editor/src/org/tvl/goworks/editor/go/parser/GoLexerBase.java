// $ANTLR ANTLRVersion> GoLexerBase.java generatedTimestamp>
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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GoLexerBase extends Lexer {
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
		StringLiteral=84, ANYCHAR=85;
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"break", "case", "chan", "const", "continue", "default", "defer", "else", 
		"fallthrough", "for", "func", "go", "goto", "if", "import", "interface", 
		"map", "package", "range", "return", "select", "struct", "switch", "type", 
		"var", "+", "-", "*", "/", "%", "&", "|", "^", "<<", ">>", "&^", "+=", 
		"-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "&^=", "&&", "||", 
		"<-", "++", "--", "==", "<", ">", "=", "!", "!=", "<=", ">=", ":=", "...", 
		"(", ")", "[", "]", "{", "}", ",", ".", ";", ":", "IDENTIFIER", "WS", 
		"NEWLINE", "COMMENT", "ML_COMMENT", "INT_LITERAL", "IMAGINARY_LITERAL", 
		"FLOAT_LITERAL", "CharLiteral", "StringLiteral", "ANYCHAR"
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
		"LittleUValue", "BigUValue", "EscapedChar", "StringLiteral", "RawStringLiteral", 
		"InterpretedStringLiteral", "NEWLINE_CHAR", "UNICODE_CHAR_NOSQUOTE", "UNICODE_CHAR_NODQUOTE", 
		"UNICODE_CHAR_NOBTICK", "UNICODE_LETTER_CHAR", "UNICODE_DIGIT_CHAR", "LETTER_CHAR", 
		"DECIMAL_DIGIT_CHAR", "OCTAL_DIGIT_CHAR", "HEX_DIGIT_CHAR", "ANYCHAR"
	};


	protected int getMultilineCommentType() {
	    return modeStack.peek()==DEFAULT_MODE ? ML_COMMENT : ML_COMMENT;
	}


	public GoLexerBase(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN);
	}

	public String getGrammarFileName() { return "GoLexerBase.java"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }


	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch ( ruleIndex ) {
			case 0 : Break_action((RuleContext)_localctx, actionIndex); break;

			case 1 : Case_action((RuleContext)_localctx, actionIndex); break;

			case 2 : Chan_action((RuleContext)_localctx, actionIndex); break;

			case 3 : Const_action((RuleContext)_localctx, actionIndex); break;

			case 4 : Continue_action((RuleContext)_localctx, actionIndex); break;

			case 5 : Default_action((RuleContext)_localctx, actionIndex); break;

			case 6 : Defer_action((RuleContext)_localctx, actionIndex); break;

			case 7 : Else_action((RuleContext)_localctx, actionIndex); break;

			case 8 : Fallthrough_action((RuleContext)_localctx, actionIndex); break;

			case 9 : For_action((RuleContext)_localctx, actionIndex); break;

			case 10 : Func_action((RuleContext)_localctx, actionIndex); break;

			case 11 : Go_action((RuleContext)_localctx, actionIndex); break;

			case 12 : Goto_action((RuleContext)_localctx, actionIndex); break;

			case 13 : If_action((RuleContext)_localctx, actionIndex); break;

			case 14 : Import_action((RuleContext)_localctx, actionIndex); break;

			case 15 : Interface_action((RuleContext)_localctx, actionIndex); break;

			case 16 : Map_action((RuleContext)_localctx, actionIndex); break;

			case 17 : Package_action((RuleContext)_localctx, actionIndex); break;

			case 18 : Range_action((RuleContext)_localctx, actionIndex); break;

			case 19 : Return_action((RuleContext)_localctx, actionIndex); break;

			case 20 : Select_action((RuleContext)_localctx, actionIndex); break;

			case 21 : Struct_action((RuleContext)_localctx, actionIndex); break;

			case 22 : Switch_action((RuleContext)_localctx, actionIndex); break;

			case 23 : Type_action((RuleContext)_localctx, actionIndex); break;

			case 24 : Var_action((RuleContext)_localctx, actionIndex); break;

			case 25 : Plus_action((RuleContext)_localctx, actionIndex); break;

			case 26 : Minus_action((RuleContext)_localctx, actionIndex); break;

			case 27 : Star_action((RuleContext)_localctx, actionIndex); break;

			case 28 : Slash_action((RuleContext)_localctx, actionIndex); break;

			case 29 : Percent_action((RuleContext)_localctx, actionIndex); break;

			case 30 : Amp_action((RuleContext)_localctx, actionIndex); break;

			case 31 : Pipe_action((RuleContext)_localctx, actionIndex); break;

			case 32 : Caret_action((RuleContext)_localctx, actionIndex); break;

			case 33 : LeftShift_action((RuleContext)_localctx, actionIndex); break;

			case 34 : RightShift_action((RuleContext)_localctx, actionIndex); break;

			case 35 : AmpCaret_action((RuleContext)_localctx, actionIndex); break;

			case 36 : PlusEqual_action((RuleContext)_localctx, actionIndex); break;

			case 37 : MinusEqual_action((RuleContext)_localctx, actionIndex); break;

			case 38 : StarEqual_action((RuleContext)_localctx, actionIndex); break;

			case 39 : SlashEqual_action((RuleContext)_localctx, actionIndex); break;

			case 40 : PercentEqual_action((RuleContext)_localctx, actionIndex); break;

			case 41 : AmpEqual_action((RuleContext)_localctx, actionIndex); break;

			case 42 : PipeEqual_action((RuleContext)_localctx, actionIndex); break;

			case 43 : CaretEqual_action((RuleContext)_localctx, actionIndex); break;

			case 44 : LeftShiftEqual_action((RuleContext)_localctx, actionIndex); break;

			case 45 : RightShiftEqual_action((RuleContext)_localctx, actionIndex); break;

			case 46 : AmpCaretEqual_action((RuleContext)_localctx, actionIndex); break;

			case 47 : And_action((RuleContext)_localctx, actionIndex); break;

			case 48 : Or_action((RuleContext)_localctx, actionIndex); break;

			case 49 : LeftArrow_action((RuleContext)_localctx, actionIndex); break;

			case 50 : Inc_action((RuleContext)_localctx, actionIndex); break;

			case 51 : Dec_action((RuleContext)_localctx, actionIndex); break;

			case 52 : EqualEqual_action((RuleContext)_localctx, actionIndex); break;

			case 53 : LessThan_action((RuleContext)_localctx, actionIndex); break;

			case 54 : GreaterThan_action((RuleContext)_localctx, actionIndex); break;

			case 55 : Equal_action((RuleContext)_localctx, actionIndex); break;

			case 56 : Bang_action((RuleContext)_localctx, actionIndex); break;

			case 57 : BangEqual_action((RuleContext)_localctx, actionIndex); break;

			case 58 : LessEqual_action((RuleContext)_localctx, actionIndex); break;

			case 59 : GreaterEqual_action((RuleContext)_localctx, actionIndex); break;

			case 60 : ColonEqual_action((RuleContext)_localctx, actionIndex); break;

			case 61 : Ellipsis_action((RuleContext)_localctx, actionIndex); break;

			case 62 : LeftParen_action((RuleContext)_localctx, actionIndex); break;

			case 63 : RightParen_action((RuleContext)_localctx, actionIndex); break;

			case 64 : LeftBrack_action((RuleContext)_localctx, actionIndex); break;

			case 65 : RightBrack_action((RuleContext)_localctx, actionIndex); break;

			case 66 : LeftBrace_action((RuleContext)_localctx, actionIndex); break;

			case 67 : RightBrace_action((RuleContext)_localctx, actionIndex); break;

			case 68 : Comma_action((RuleContext)_localctx, actionIndex); break;

			case 69 : Dot_action((RuleContext)_localctx, actionIndex); break;

			case 70 : Semi_action((RuleContext)_localctx, actionIndex); break;

			case 71 : Colon_action((RuleContext)_localctx, actionIndex); break;

			case 72 : IDENTIFIER_action((RuleContext)_localctx, actionIndex); break;

			case 73 : WS_action((RuleContext)_localctx, actionIndex); break;

			case 74 : NEWLINE_action((RuleContext)_localctx, actionIndex); break;

			case 75 : COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 76 : ML_COMMENT_action((RuleContext)_localctx, actionIndex); break;

			case 77 : INT_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 78 : DecimalLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 79 : OctalLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 80 : HexLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 81 : IMAGINARY_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 82 : FLOAT_LITERAL_action((RuleContext)_localctx, actionIndex); break;

			case 83 : Decimals_action((RuleContext)_localctx, actionIndex); break;

			case 84 : Exponent_action((RuleContext)_localctx, actionIndex); break;

			case 85 : CharLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 86 : UNICODE_VALUE_NOSQUOTE_action((RuleContext)_localctx, actionIndex); break;

			case 87 : UNICODE_VALUE_NODQUOTE_action((RuleContext)_localctx, actionIndex); break;

			case 88 : BYTE_VALUE_action((RuleContext)_localctx, actionIndex); break;

			case 89 : OCTAL_BYTE_VALUE_action((RuleContext)_localctx, actionIndex); break;

			case 90 : HexByteValue_action((RuleContext)_localctx, actionIndex); break;

			case 91 : LittleUValue_action((RuleContext)_localctx, actionIndex); break;

			case 92 : BigUValue_action((RuleContext)_localctx, actionIndex); break;

			case 93 : EscapedChar_action((RuleContext)_localctx, actionIndex); break;

			case 94 : StringLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 95 : RawStringLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 96 : InterpretedStringLiteral_action((RuleContext)_localctx, actionIndex); break;

			case 97 : NEWLINE_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 98 : UNICODE_CHAR_NOSQUOTE_action((RuleContext)_localctx, actionIndex); break;

			case 99 : UNICODE_CHAR_NODQUOTE_action((RuleContext)_localctx, actionIndex); break;

			case 100 : UNICODE_CHAR_NOBTICK_action((RuleContext)_localctx, actionIndex); break;

			case 101 : UNICODE_LETTER_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 102 : UNICODE_DIGIT_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 103 : LETTER_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 104 : DECIMAL_DIGIT_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 105 : OCTAL_DIGIT_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 106 : HEX_DIGIT_CHAR_action((RuleContext)_localctx, actionIndex); break;

			case 107 : ANYCHAR_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	public void Switch_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_CHAR_NOBTICK_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PipeEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Pipe_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LessThan_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Goto_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OctalLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Fallthrough_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BangEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Percent_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftArrow_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Decimals_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Default_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IMAGINARY_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NEWLINE_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CharLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Chan_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DECIMAL_DIGIT_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ANYCHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 2 : channel =  HIDDEN; break;
		}
	}
	public void INT_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void MinusEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ColonEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_CHAR_NOSQUOTE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftShiftEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Colon_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Const_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Equal_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Dec_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightShift_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void CaretEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Range_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Minus_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Semi_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void WS_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 0 : channel =  HIDDEN; break;
		}
	}
	public void Break_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_VALUE_NODQUOTE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_VALUE_NOSQUOTE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Inc_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftBrace_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void SlashEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftBrack_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftParen_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Star_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Defer_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpCaret_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightParen_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RawStringLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Else_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Var_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Slash_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_CHAR_NODQUOTE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GreaterEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BigUValue_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LETTER_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Go_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EscapedChar_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Exponent_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LessEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HEX_DIGIT_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Case_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Func_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void GreaterThan_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void ML_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 3 : channel =  HIDDEN; break;
		}
	}
	public void StarEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Map_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void IDENTIFIER_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Amp_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Ellipsis_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Interface_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void AmpCaretEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Select_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HexByteValue_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_LETTER_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Or_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Return_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Struct_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Caret_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void If_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightShiftEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PercentEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void BYTE_VALUE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void And_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void HexLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Import_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void PlusEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Type_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Continue_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void UNICODE_DIGIT_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void InterpretedStringLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LeftShift_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void EqualEqual_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void DecimalLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void For_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Package_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void FLOAT_LITERAL_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OCTAL_BYTE_VALUE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void StringLiteral_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Plus_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Bang_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
			case 1 : channel =  HIDDEN; break;
		}
	}
	public void Dot_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void OCTAL_DIGIT_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightBrack_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void LittleUValue_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void Comma_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}
	public void RightBrace_action(RuleContext _localctx, int actionIndex) {
		switch ( actionIndex ) {
		}
	}

	public static final String _serializedATN =
		"\2U\u0311\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6"+
		"\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7"+
		"\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7"+
		"\25\2\26\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7"+
		"\34\2\35\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7"+
		"%\2&\7&\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7"+
		"\60\2\61\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7"+
		"\67\28\78\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B"+
		"\2C\7C\2D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N"+
		"\7N\2O\7O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7Y"+
		"\2Z\7Z\2[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2c\7c\2d\7d\2"+
		"e\7e\2f\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\1\0\1\0\1\0\1\0\1\0\1\0\1\1\1"+
		"\1\1\1\1\1\1\1\1\2\1\2\1\2\1\2\1\2\1\3\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\4"+
		"\1\4\1\4\1\4\1\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1\6\1\6\1"+
		"\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b"+
		"\1\b\1\b\1\t\1\t\1\t\1\t\1\n\1\n\1\n\1\n\1\n\1\13\1\13\1\13\1\f\1\f\1"+
		"\f\1\f\1\f\1\r\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1"+
		"\17\1\17\1\17\1\17\1\17\1\17\1\17\1\17\1\20\1\20\1\20\1\20\1\21\1\21\1"+
		"\21\1\21\1\21\1\21\1\21\1\21\1\22\1\22\1\22\1\22\1\22\1\22\1\23\1\23\1"+
		"\23\1\23\1\23\1\23\1\23\1\24\1\24\1\24\1\24\1\24\1\24\1\24\1\25\1\25\1"+
		"\25\1\25\1\25\1\25\1\25\1\26\1\26\1\26\1\26\1\26\1\26\1\26\1\27\1\27\1"+
		"\27\1\27\1\27\1\30\1\30\1\30\1\30\1\31\1\31\1\32\1\32\1\33\1\33\1\34\1"+
		"\34\1\35\1\35\1\36\1\36\1\37\1\37\1 \1 \1!\1!\1!\1\"\1\"\1\"\1#\1#\1#"+
		"\1$\1$\1$\1%\1%\1%\1&\1&\1&\1\'\1\'\1\'\1(\1(\1(\1)\1)\1)\1*\1*\1*\1+"+
		"\1+\1+\1,\1,\1,\1,\1-\1-\1-\1-\1.\1.\1.\1.\1/\1/\1/\1\60\1\60\1\60\1\61"+
		"\1\61\1\61\1\62\1\62\1\62\1\63\1\63\1\63\1\64\1\64\1\64\1\65\1\65\1\66"+
		"\1\66\1\67\1\67\18\18\19\19\19\1:\1:\1:\1;\1;\1;\1<\1<\1<\1=\1=\1=\1="+
		"\1>\1>\1?\1?\1@\1@\1A\1A\1B\1B\1C\1C\1D\1D\1E\1E\1F\1F\1G\1G\1H\0\1H\0"+
		"\1H\0\5H\bH\nH\fH\u01f8\tH\1I\0\4I\bI\13I\fI\u01fd\1I\1I\1J\0\3J\bJ\1"+
		"J\0\1J\1J\1K\1K\1K\1K\0\5K\bK\nK\fK\u0212\tK\1K\1K\1L\1L\1L\1L\0\5L\b"+
		"L\nL\fL\u021e\tL\1L\1L\1L\1L\1L\1M\0\1M\0\1M\0\3M\bM\1N\0\1N\0\5N\bN\n"+
		"N\fN\u0234\tN\1O\0\1O\0\5O\bO\nO\fO\u023d\tO\1P\0\1P\0\1P\0\4P\bP\13P"+
		"\fP\u0246\1Q\0\1Q\0\3Q\bQ\1Q\1Q\1R\0\1R\0\1R\0\3R\bR\1R\0\3R\bR\1R\0\1"+
		"R\1R\1R\0\1R\0\1R\0\3R\bR\3R\bR\1S\0\4S\bS\13S\fS\u026e\1T\0\1T\0\3T\b"+
		"T\1T\1T\1U\0\1U\0\1U\0\3U\bU\1U\1U\1V\0\1V\0\1V\0\1V\0\3V\bV\1W\0\1W\0"+
		"\1W\0\1W\0\3W\bW\1X\0\1X\0\3X\bX\1Y\0\1Y\0\1Y\0\1Y\1Y\1Z\1Z\1Z\1Z\0\1"+
		"Z\1Z\1[\1[\1[\1[\0\1[\0\1[\0\1[\1[\1\\\1\\\1\\\1\\\0\1\\\0\1\\\0\1\\\0"+
		"\1\\\0\1\\\0\1\\\0\1\\\1\\\1]\0\1]\1]\1^\0\1^\0\3^\b^\1_\0\1_\0\1_\0\5"+
		"_\b_\n_\f_\u02dd\t_\1_\1_\1`\0\1`\0\1`\0\5`\b`\n`\f`\u02ea\t`\1`\1`\1"+
		"a\1a\1b\1b\1c\1c\1d\1d\1e\0\1e\0\3e\be\1f\1f\1g\0\1g\0\3g\bg\1h\1h\1i"+
		"\1i\1j\0\1j\0\1j\0\3j\bj\1k\1kl\1\3\uffff\3\4\uffff\5\5\uffff\7\6\uffff"+
		"\t\7\uffff\13\b\uffff\r\t\uffff\17\n\uffff\21\13\uffff\23\f\uffff\25\r"+
		"\uffff\27\16\uffff\31\17\uffff\33\20\uffff\35\21\uffff\37\22\uffff!\23"+
		"\uffff#\24\uffff%\25\uffff\'\26\uffff)\27\uffff+\30\uffff-\31\uffff/\32"+
		"\uffff\61\33\uffff\63\34\uffff\65\35\uffff\67\36\uffff9\37\uffff; \uffff"+
		"=!\uffff?\"\uffffA#\uffffC$\uffffE%\uffffG&\uffffI\'\uffffK(\uffffM)\uffff"+
		"O*\uffffQ+\uffffS,\uffffU-\uffffW.\uffffY/\uffff[\60\uffff]\61\uffff_"+
		"\62\uffffa\63\uffffc\64\uffffe\65\uffffg\66\uffffi\67\uffffk8\uffffm9"+
		"\uffffo:\uffffq;\uffffs<\uffffu=\uffffw>\uffffy?\uffff{@\uffff}A\uffff"+
		"\177B\uffff\u0081C\uffff\u0083D\uffff\u0085E\uffff\u0087F\uffff\u0089"+
		"G\uffff\u008bH\uffff\u008dI\uffff\u008fJ\uffff\u0091K\uffff\u0093L\0\u0095"+
		"M\1\u0097N\2\u0099O\3\u009bP\uffff\u009d\0\uffff\u009f\0\uffff\u00a1\0"+
		"\uffff\u00a3Q\uffff\u00a5R\uffff\u00a7\0\uffff\u00a9\0\uffff\u00abS\uffff"+
		"\u00ad\0\uffff\u00af\0\uffff\u00b1\0\uffff\u00b3\0\uffff\u00b5\0\uffff"+
		"\u00b7\0\uffff\u00b9\0\uffff\u00bb\0\uffff\u00bdT\uffff\u00bf\0\uffff"+
		"\u00c1\0\uffff\u00c3\0\uffff\u00c5\0\uffff\u00c7\0\uffff\u00c9\0\uffff"+
		"\u00cb\0\uffff\u00cd\0\uffff\u00cf\0\uffff\u00d1\0\uffff\u00d3\0\uffff"+
		"\u00d5\0\uffff\u00d7U\uffff\1\0\t\2\t\t  \2\n\n\r\r\2XXxx\2EEee\2++--"+
		"\t\"\"\'\'\\\\abffnnrrttvv\2\n\n\'\'\2\n\n\"\"\2\n\n``\u02d1\0\1\1\0\0"+
		"\0\0\3\1\0\0\0\0\5\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0\r\1"+
		"\0\0\0\0\17\1\0\0\0\0\21\1\0\0\0\0\23\1\0\0\0\0\25\1\0\0\0\0\27\1\0\0"+
		"\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1"+
		"\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0\0-\1\0\0\0\0/\1\0"+
		"\0\0\0\61\1\0\0\0\0\63\1\0\0\0\0\65\1\0\0\0\0\67\1\0\0\0\09\1\0\0\0\0"+
		";\1\0\0\0\0=\1\0\0\0\0?\1\0\0\0\0A\1\0\0\0\0C\1\0\0\0\0E\1\0\0\0\0G\1"+
		"\0\0\0\0I\1\0\0\0\0K\1\0\0\0\0M\1\0\0\0\0O\1\0\0\0\0Q\1\0\0\0\0S\1\0\0"+
		"\0\0U\1\0\0\0\0W\1\0\0\0\0Y\1\0\0\0\0[\1\0\0\0\0]\1\0\0\0\0_\1\0\0\0\0"+
		"a\1\0\0\0\0c\1\0\0\0\0e\1\0\0\0\0g\1\0\0\0\0i\1\0\0\0\0k\1\0\0\0\0m\1"+
		"\0\0\0\0o\1\0\0\0\0q\1\0\0\0\0s\1\0\0\0\0u\1\0\0\0\0w\1\0\0\0\0y\1\0\0"+
		"\0\0{\1\0\0\0\0}\1\0\0\0\0\177\1\0\0\0\0\u0081\1\0\0\0\0\u0083\1\0\0\0"+
		"\0\u0085\1\0\0\0\0\u0087\1\0\0\0\0\u0089\1\0\0\0\0\u008b\1\0\0\0\0\u008d"+
		"\1\0\0\0\0\u008f\1\0\0\0\0\u0091\1\0\0\0\0\u0093\1\0\0\0\0\u0095\1\0\0"+
		"\0\0\u0097\1\0\0\0\0\u0099\1\0\0\0\0\u009b\1\0\0\0\0\u00a3\1\0\0\0\0\u00a5"+
		"\1\0\0\0\0\u00ab\1\0\0\0\0\u00bd\1\0\0\0\0\u00d7\1\0\0\0\1\u00d9\1\0\0"+
		"\0\3\u00df\1\0\0\0\5\u00e4\1\0\0\0\7\u00e9\1\0\0\0\t\u00ef\1\0\0\0\13"+
		"\u00f8\1\0\0\0\r\u0100\1\0\0\0\17\u0106\1\0\0\0\21\u010b\1\0\0\0\23\u0117"+
		"\1\0\0\0\25\u011b\1\0\0\0\27\u0120\1\0\0\0\31\u0123\1\0\0\0\33\u0128\1"+
		"\0\0\0\35\u012b\1\0\0\0\37\u0132\1\0\0\0!\u013c\1\0\0\0#\u0140\1\0\0\0"+
		"%\u0148\1\0\0\0\'\u014e\1\0\0\0)\u0155\1\0\0\0+\u015c\1\0\0\0-\u0163\1"+
		"\0\0\0/\u016a\1\0\0\0\61\u016f\1\0\0\0\63\u0173\1\0\0\0\65\u0175\1\0\0"+
		"\0\67\u0177\1\0\0\09\u0179\1\0\0\0;\u017b\1\0\0\0=\u017d\1\0\0\0?\u017f"+
		"\1\0\0\0A\u0181\1\0\0\0C\u0183\1\0\0\0E\u0186\1\0\0\0G\u0189\1\0\0\0I"+
		"\u018c\1\0\0\0K\u018f\1\0\0\0M\u0192\1\0\0\0O\u0195\1\0\0\0Q\u0198\1\0"+
		"\0\0S\u019b\1\0\0\0U\u019e\1\0\0\0W\u01a1\1\0\0\0Y\u01a4\1\0\0\0[\u01a8"+
		"\1\0\0\0]\u01ac\1\0\0\0_\u01b0\1\0\0\0a\u01b3\1\0\0\0c\u01b6\1\0\0\0e"+
		"\u01b9\1\0\0\0g\u01bc\1\0\0\0i\u01bf\1\0\0\0k\u01c2\1\0\0\0m\u01c4\1\0"+
		"\0\0o\u01c6\1\0\0\0q\u01c8\1\0\0\0s\u01ca\1\0\0\0u\u01cd\1\0\0\0w\u01d0"+
		"\1\0\0\0y\u01d3\1\0\0\0{\u01d6\1\0\0\0}\u01da\1\0\0\0\177\u01dc\1\0\0"+
		"\0\u0081\u01de\1\0\0\0\u0083\u01e0\1\0\0\0\u0085\u01e2\1\0\0\0\u0087\u01e4"+
		"\1\0\0\0\u0089\u01e6\1\0\0\0\u008b\u01e8\1\0\0\0\u008d\u01ea\1\0\0\0\u008f"+
		"\u01ec\1\0\0\0\u0091\u01ee\1\0\0\0\u0093\u01fb\1\0\0\0\u0095\u0203\1\0"+
		"\0\0\u0097\u0209\1\0\0\0\u0099\u0215\1\0\0\0\u009b\u022a\1\0\0\0\u009d"+
		"\u022c\1\0\0\0\u009f\u0235\1\0\0\0\u00a1\u023e\1\0\0\0\u00a3\u024c\1\0"+
		"\0\0\u00a5\u0268\1\0\0\0\u00a7\u026c\1\0\0\0\u00a9\u0270\1\0\0\0\u00ab"+
		"\u0278\1\0\0\0\u00ad\u028a\1\0\0\0\u00af\u0294\1\0\0\0\u00b1\u029a\1\0"+
		"\0\0\u00b3\u029c\1\0\0\0\u00b5\u02a4\1\0\0\0\u00b7\u02ab\1\0\0\0\u00b9"+
		"\u02b6\1\0\0\0\u00bb\u02c9\1\0\0\0\u00bd\u02d1\1\0\0\0\u00bf\u02d3\1\0"+
		"\0\0\u00c1\u02e0\1\0\0\0\u00c3\u02ed\1\0\0\0\u00c5\u02ef\1\0\0\0\u00c7"+
		"\u02f1\1\0\0\0\u00c9\u02f3\1\0\0\0\u00cb\u02f9\1\0\0\0\u00cd\u02fb\1\0"+
		"\0\0\u00cf\u0301\1\0\0\0\u00d1\u0303\1\0\0\0\u00d3\u0305\1\0\0\0\u00d5"+
		"\u030d\1\0\0\0\u00d7\u030f\1\0\0\0\u00d9\u00da\5b\0\0\u00da\u00db\5r\0"+
		"\0\u00db\u00dc\5e\0\0\u00dc\u00dd\5a\0\0\u00dd\u00de\5k\0\0\u00de\2\1"+
		"\0\0\0\u00df\u00e0\5c\0\0\u00e0\u00e1\5a\0\0\u00e1\u00e2\5s\0\0\u00e2"+
		"\u00e3\5e\0\0\u00e3\4\1\0\0\0\u00e4\u00e5\5c\0\0\u00e5\u00e6\5h\0\0\u00e6"+
		"\u00e7\5a\0\0\u00e7\u00e8\5n\0\0\u00e8\6\1\0\0\0\u00e9\u00ea\5c\0\0\u00ea"+
		"\u00eb\5o\0\0\u00eb\u00ec\5n\0\0\u00ec\u00ed\5s\0\0\u00ed\u00ee\5t\0\0"+
		"\u00ee\b\1\0\0\0\u00ef\u00f0\5c\0\0\u00f0\u00f1\5o\0\0\u00f1\u00f2\5n"+
		"\0\0\u00f2\u00f3\5t\0\0\u00f3\u00f4\5i\0\0\u00f4\u00f5\5n\0\0\u00f5\u00f6"+
		"\5u\0\0\u00f6\u00f7\5e\0\0\u00f7\n\1\0\0\0\u00f8\u00f9\5d\0\0\u00f9\u00fa"+
		"\5e\0\0\u00fa\u00fb\5f\0\0\u00fb\u00fc\5a\0\0\u00fc\u00fd\5u\0\0\u00fd"+
		"\u00fe\5l\0\0\u00fe\u00ff\5t\0\0\u00ff\f\1\0\0\0\u0100\u0101\5d\0\0\u0101"+
		"\u0102\5e\0\0\u0102\u0103\5f\0\0\u0103\u0104\5e\0\0\u0104\u0105\5r\0\0"+
		"\u0105\16\1\0\0\0\u0106\u0107\5e\0\0\u0107\u0108\5l\0\0\u0108\u0109\5"+
		"s\0\0\u0109\u010a\5e\0\0\u010a\20\1\0\0\0\u010b\u010c\5f\0\0\u010c\u010d"+
		"\5a\0\0\u010d\u010e\5l\0\0\u010e\u010f\5l\0\0\u010f\u0110\5t\0\0\u0110"+
		"\u0111\5h\0\0\u0111\u0112\5r\0\0\u0112\u0113\5o\0\0\u0113\u0114\5u\0\0"+
		"\u0114\u0115\5g\0\0\u0115\u0116\5h\0\0\u0116\22\1\0\0\0\u0117\u0118\5"+
		"f\0\0\u0118\u0119\5o\0\0\u0119\u011a\5r\0\0\u011a\24\1\0\0\0\u011b\u011c"+
		"\5f\0\0\u011c\u011d\5u\0\0\u011d\u011e\5n\0\0\u011e\u011f\5c\0\0\u011f"+
		"\26\1\0\0\0\u0120\u0121\5g\0\0\u0121\u0122\5o\0\0\u0122\30\1\0\0\0\u0123"+
		"\u0124\5g\0\0\u0124\u0125\5o\0\0\u0125\u0126\5t\0\0\u0126\u0127\5o\0\0"+
		"\u0127\32\1\0\0\0\u0128\u0129\5i\0\0\u0129\u012a\5f\0\0\u012a\34\1\0\0"+
		"\0\u012b\u012c\5i\0\0\u012c\u012d\5m\0\0\u012d\u012e\5p\0\0\u012e\u012f"+
		"\5o\0\0\u012f\u0130\5r\0\0\u0130\u0131\5t\0\0\u0131\36\1\0\0\0\u0132\u0133"+
		"\5i\0\0\u0133\u0134\5n\0\0\u0134\u0135\5t\0\0\u0135\u0136\5e\0\0\u0136"+
		"\u0137\5r\0\0\u0137\u0138\5f\0\0\u0138\u0139\5a\0\0\u0139\u013a\5c\0\0"+
		"\u013a\u013b\5e\0\0\u013b \1\0\0\0\u013c\u013d\5m\0\0\u013d\u013e\5a\0"+
		"\0\u013e\u013f\5p\0\0\u013f\"\1\0\0\0\u0140\u0141\5p\0\0\u0141\u0142\5"+
		"a\0\0\u0142\u0143\5c\0\0\u0143\u0144\5k\0\0\u0144\u0145\5a\0\0\u0145\u0146"+
		"\5g\0\0\u0146\u0147\5e\0\0\u0147$\1\0\0\0\u0148\u0149\5r\0\0\u0149\u014a"+
		"\5a\0\0\u014a\u014b\5n\0\0\u014b\u014c\5g\0\0\u014c\u014d\5e\0\0\u014d"+
		"&\1\0\0\0\u014e\u014f\5r\0\0\u014f\u0150\5e\0\0\u0150\u0151\5t\0\0\u0151"+
		"\u0152\5u\0\0\u0152\u0153\5r\0\0\u0153\u0154\5n\0\0\u0154(\1\0\0\0\u0155"+
		"\u0156\5s\0\0\u0156\u0157\5e\0\0\u0157\u0158\5l\0\0\u0158\u0159\5e\0\0"+
		"\u0159\u015a\5c\0\0\u015a\u015b\5t\0\0\u015b*\1\0\0\0\u015c\u015d\5s\0"+
		"\0\u015d\u015e\5t\0\0\u015e\u015f\5r\0\0\u015f\u0160\5u\0\0\u0160\u0161"+
		"\5c\0\0\u0161\u0162\5t\0\0\u0162,\1\0\0\0\u0163\u0164\5s\0\0\u0164\u0165"+
		"\5w\0\0\u0165\u0166\5i\0\0\u0166\u0167\5t\0\0\u0167\u0168\5c\0\0\u0168"+
		"\u0169\5h\0\0\u0169.\1\0\0\0\u016a\u016b\5t\0\0\u016b\u016c\5y\0\0\u016c"+
		"\u016d\5p\0\0\u016d\u016e\5e\0\0\u016e\60\1\0\0\0\u016f\u0170\5v\0\0\u0170"+
		"\u0171\5a\0\0\u0171\u0172\5r\0\0\u0172\62\1\0\0\0\u0173\u0174\5+\0\0\u0174"+
		"\64\1\0\0\0\u0175\u0176\5-\0\0\u0176\66\1\0\0\0\u0177\u0178\5*\0\0\u0178"+
		"8\1\0\0\0\u0179\u017a\5/\0\0\u017a:\1\0\0\0\u017b\u017c\5%\0\0\u017c<"+
		"\1\0\0\0\u017d\u017e\5&\0\0\u017e>\1\0\0\0\u017f\u0180\5|\0\0\u0180@\1"+
		"\0\0\0\u0181\u0182\5^\0\0\u0182B\1\0\0\0\u0183\u0184\5<\0\0\u0184\u0185"+
		"\5<\0\0\u0185D\1\0\0\0\u0186\u0187\5>\0\0\u0187\u0188\5>\0\0\u0188F\1"+
		"\0\0\0\u0189\u018a\5&\0\0\u018a\u018b\5^\0\0\u018bH\1\0\0\0\u018c\u018d"+
		"\5+\0\0\u018d\u018e\5=\0\0\u018eJ\1\0\0\0\u018f\u0190\5-\0\0\u0190\u0191"+
		"\5=\0\0\u0191L\1\0\0\0\u0192\u0193\5*\0\0\u0193\u0194\5=\0\0\u0194N\1"+
		"\0\0\0\u0195\u0196\5/\0\0\u0196\u0197\5=\0\0\u0197P\1\0\0\0\u0198\u0199"+
		"\5%\0\0\u0199\u019a\5=\0\0\u019aR\1\0\0\0\u019b\u019c\5&\0\0\u019c\u019d"+
		"\5=\0\0\u019dT\1\0\0\0\u019e\u019f\5|\0\0\u019f\u01a0\5=\0\0\u01a0V\1"+
		"\0\0\0\u01a1\u01a2\5^\0\0\u01a2\u01a3\5=\0\0\u01a3X\1\0\0\0\u01a4\u01a5"+
		"\5<\0\0\u01a5\u01a6\5<\0\0\u01a6\u01a7\5=\0\0\u01a7Z\1\0\0\0\u01a8\u01a9"+
		"\5>\0\0\u01a9\u01aa\5>\0\0\u01aa\u01ab\5=\0\0\u01ab\\\1\0\0\0\u01ac\u01ad"+
		"\5&\0\0\u01ad\u01ae\5^\0\0\u01ae\u01af\5=\0\0\u01af^\1\0\0\0\u01b0\u01b1"+
		"\5&\0\0\u01b1\u01b2\5&\0\0\u01b2`\1\0\0\0\u01b3\u01b4\5|\0\0\u01b4\u01b5"+
		"\5|\0\0\u01b5b\1\0\0\0\u01b6\u01b7\5<\0\0\u01b7\u01b8\5-\0\0\u01b8d\1"+
		"\0\0\0\u01b9\u01ba\5+\0\0\u01ba\u01bb\5+\0\0\u01bbf\1\0\0\0\u01bc\u01bd"+
		"\5-\0\0\u01bd\u01be\5-\0\0\u01beh\1\0\0\0\u01bf\u01c0\5=\0\0\u01c0\u01c1"+
		"\5=\0\0\u01c1j\1\0\0\0\u01c2\u01c3\5<\0\0\u01c3l\1\0\0\0\u01c4\u01c5\5"+
		">\0\0\u01c5n\1\0\0\0\u01c6\u01c7\5=\0\0\u01c7p\1\0\0\0\u01c8\u01c9\5!"+
		"\0\0\u01c9r\1\0\0\0\u01ca\u01cb\5!\0\0\u01cb\u01cc\5=\0\0\u01cct\1\0\0"+
		"\0\u01cd\u01ce\5<\0\0\u01ce\u01cf\5=\0\0\u01cfv\1\0\0\0\u01d0\u01d1\5"+
		">\0\0\u01d1\u01d2\5=\0\0\u01d2x\1\0\0\0\u01d3\u01d4\5:\0\0\u01d4\u01d5"+
		"\5=\0\0\u01d5z\1\0\0\0\u01d6\u01d7\5.\0\0\u01d7\u01d8\5.\0\0\u01d8\u01d9"+
		"\5.\0\0\u01d9|\1\0\0\0\u01da\u01db\5(\0\0\u01db~\1\0\0\0\u01dc\u01dd\5"+
		")\0\0\u01dd\u0080\1\0\0\0\u01de\u01df\5[\0\0\u01df\u0082\1\0\0\0\u01e0"+
		"\u01e1\5]\0\0\u01e1\u0084\1\0\0\0\u01e2\u01e3\5{\0\0\u01e3\u0086\1\0\0"+
		"\0\u01e4\u01e5\5}\0\0\u01e5\u0088\1\0\0\0\u01e6\u01e7\5,\0\0\u01e7\u008a"+
		"\1\0\0\0\u01e8\u01e9\5.\0\0\u01e9\u008c\1\0\0\0\u01ea\u01eb\5;\0\0\u01eb"+
		"\u008e\1\0\0\0\u01ec\u01ed\5:\0\0\u01ed\u0090\1\0\0\0\u01ee\u01f6\3\u00cf"+
		"g\0\u01f0\u01f5\3\u00cfg\0\u01f2\u01f5\3\u00cdf\0\u01f4\u01f0\1\0\0\0"+
		"\u01f4\u01f2\1\0\0\0\u01f5\u01f8\1\0\0\0\u01f6\u01f4\1\0\0\0\u01f6\u01f7"+
		"\1\0\0\0\u01f7\u0092\1\0\0\0\u01f8\u01f6\1\0\0\0\u01f9\u01fc\7\0\0\0\u01fb"+
		"\u01f9\1\0\0\0\u01fc\u01fd\1\0\0\0\u01fd\u01fb\1\0\0\0\u01fd\u01fe\1\0"+
		"\0\0\u01fe\u01ff\1\0\0\0\u01ff\u0200\6I\0\0\u0200\u0094\1\0\0\0\u0201"+
		"\u0204\5\r\0\0\u0203\u0201\1\0\0\0\u0203\u0204\1\0\0\0\u0204\u0205\1\0"+
		"\0\0\u0205\u0207\5\n\0\0\u0207\u0208\6J\1\0\u0208\u0096\1\0\0\0\u0209"+
		"\u020a\5/\0\0\u020a\u020b\5/\0\0\u020b\u0210\1\0\0\0\u020c\u020f\b\1\0"+
		"\0\u020e\u020c\1\0\0\0\u020f\u0212\1\0\0\0\u0210\u020e\1\0\0\0\u0210\u0211"+
		"\1\0\0\0\u0211\u0213\1\0\0\0\u0212\u0210\1\0\0\0\u0213\u0214\6K\2\0\u0214"+
		"\u0098\1\0\0\0\u0215\u0216\5/\0\0\u0216\u0217\5*\0\0\u0217\u021c\1\0\0"+
		"\0\u0218\u021b\t\0\0\0\u021a\u0218\1\0\0\0\u021b\u021e\1\0\0\0\u021c\u021d"+
		"\1\0\0\0\u021c\u021a\1\0\0\0\u021d\u021f\1\0\0\0\u021e\u021c\1\0\0\0\u021f"+
		"\u0220\5*\0\0\u0220\u0221\5/\0\0\u0221\u0222\1\0\0\0\u0222\u0223\6L\3"+
		"\0\u0223\u009a\1\0\0\0\u0224\u022b\3\u009dN\0\u0226\u022b\3\u009fO\0\u0228"+
		"\u022b\3\u00a1P\0\u022a\u0224\1\0\0\0\u022a\u0226\1\0\0\0\u022a\u0228"+
		"\1\0\0\0\u022b\u009c\1\0\0\0\u022c\u0232\2\619\0\u022e\u0231\3\u00d1h"+
		"\0\u0230\u022e\1\0\0\0\u0231\u0234\1\0\0\0\u0232\u0230\1\0\0\0\u0232\u0233"+
		"\1\0\0\0\u0233\u009e\1\0\0\0\u0234\u0232\1\0\0\0\u0235\u023b\5\60\0\0"+
		"\u0237\u023a\3\u00d3i\0\u0239\u0237\1\0\0\0\u023a\u023d\1\0\0\0\u023b"+
		"\u0239\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u00a0\1\0\0\0\u023d\u023b\1\0"+
		"\0\0\u023e\u0240\5\60\0\0\u0240\u0244\7\2\0\0\u0242\u0245\3\u00d5j\0\u0244"+
		"\u0242\1\0\0\0\u0245\u0246\1\0\0\0\u0246\u0244\1\0\0\0\u0246\u0247\1\0"+
		"\0\0\u0247\u00a2\1\0\0\0\u0248\u024d\3\u00a7S\0\u024a\u024d\3\u00a5R\0"+
		"\u024c\u0248\1\0\0\0\u024c\u024a\1\0\0\0\u024d\u024e\1\0\0\0\u024e\u024f"+
		"\5i\0\0\u024f\u00a4\1\0\0\0\u0250\u0252\3\u00a7S\0\u0252\u0256\5.\0\0"+
		"\u0254\u0257\3\u00a7S\0\u0256\u0254\1\0\0\0\u0256\u0257\1\0\0\0\u0257"+
		"\u025a\1\0\0\0\u0258\u025b\3\u00a9T\0\u025a\u0258\1\0\0\0\u025a\u025b"+
		"\1\0\0\0\u025b\u0269\1\0\0\0\u025c\u025e\3\u00a7S\0\u025e\u025f\3\u00a9"+
		"T\0\u025f\u0269\1\0\0\0\u0260\u0262\5.\0\0\u0262\u0266\3\u00a7S\0\u0264"+
		"\u0267\3\u00a9T\0\u0266\u0264\1\0\0\0\u0266\u0267\1\0\0\0\u0267\u0269"+
		"\1\0\0\0\u0268\u0250\1\0\0\0\u0268\u025c\1\0\0\0\u0268\u0260\1\0\0\0\u0269"+
		"\u00a6\1\0\0\0\u026a\u026d\3\u00d1h\0\u026c\u026a\1\0\0\0\u026d\u026e"+
		"\1\0\0\0\u026e\u026c\1\0\0\0\u026e\u026f\1\0\0\0\u026f\u00a8\1\0\0\0\u0270"+
		"\u0274\7\3\0\0\u0272\u0275\7\4\0\0\u0274\u0272\1\0\0\0\u0274\u0275\1\0"+
		"\0\0\u0275\u0276\1\0\0\0\u0276\u0277\3\u00a7S\0\u0277\u00aa\1\0\0\0\u0278"+
		"\u027e\5\'\0\0\u027a\u027f\3\u00adV\0\u027c\u027f\3\u00b1X\0\u027e\u027a"+
		"\1\0\0\0\u027e\u027c\1\0\0\0\u027f\u0280\1\0\0\0\u0280\u0281\5\'\0\0\u0281"+
		"\u00ac\1\0\0\0\u0282\u028b\3\u00c5b\0\u0284\u028b\3\u00b7[\0\u0286\u028b"+
		"\3\u00b9\\\0\u0288\u028b\3\u00bb]\0\u028a\u0282\1\0\0\0\u028a\u0284\1"+
		"\0\0\0\u028a\u0286\1\0\0\0\u028a\u0288\1\0\0\0\u028b\u00ae\1\0\0\0\u028c"+
		"\u0295\3\u00c7c\0\u028e\u0295\3\u00b7[\0\u0290\u0295\3\u00b9\\\0\u0292"+
		"\u0295\3\u00bb]\0\u0294\u028c\1\0\0\0\u0294\u028e\1\0\0\0\u0294\u0290"+
		"\1\0\0\0\u0294\u0292\1\0\0\0\u0295\u00b0\1\0\0\0\u0296\u029b\3\u00b3Y"+
		"\0\u0298\u029b\3\u00b5Z\0\u029a\u0296\1\0\0\0\u029a\u0298\1\0\0\0\u029b"+
		"\u00b2\1\0\0\0\u029c\u029e\5\\\0\0\u029e\u02a0\3\u00d3i\0\u02a0\u02a2"+
		"\3\u00d3i\0\u02a2\u02a3\3\u00d3i\0\u02a3\u00b4\1\0\0\0\u02a4\u02a5\5\\"+
		"\0\0\u02a5\u02a6\5x\0\0\u02a6\u02a7\1\0\0\0\u02a7\u02a9\3\u00d5j\0\u02a9"+
		"\u02aa\3\u00d5j\0\u02aa\u00b6\1\0\0\0\u02ab\u02ac\5\\\0\0\u02ac\u02ad"+
		"\5u\0\0\u02ad\u02ae\1\0\0\0\u02ae\u02b0\3\u00d5j\0\u02b0\u02b2\3\u00d5"+
		"j\0\u02b2\u02b4\3\u00d5j\0\u02b4\u02b5\3\u00d5j\0\u02b5\u00b8\1\0\0\0"+
		"\u02b6\u02b7\5\\\0\0\u02b7\u02b8\5U\0\0\u02b8\u02b9\1\0\0\0\u02b9\u02bb"+
		"\3\u00d5j\0\u02bb\u02bd\3\u00d5j\0\u02bd\u02bf\3\u00d5j\0\u02bf\u02c1"+
		"\3\u00d5j\0\u02c1\u02c3\3\u00d5j\0\u02c3\u02c5\3\u00d5j\0\u02c5\u02c7"+
		"\3\u00d5j\0\u02c7\u02c8\3\u00d5j\0\u02c8\u00ba\1\0\0\0\u02c9\u02cb\5\\"+
		"\0\0\u02cb\u02cc\7\5\0\0\u02cc\u00bc\1\0\0\0\u02cd\u02d2\3\u00bf_\0\u02cf"+
		"\u02d2\3\u00c1`\0\u02d1\u02cd\1\0\0\0\u02d1\u02cf\1\0\0\0\u02d2\u00be"+
		"\1\0\0\0\u02d3\u02db\5`\0\0\u02d5\u02da\3\u00c9d\0\u02d7\u02da\3\u00c3"+
		"a\0\u02d9\u02d5\1\0\0\0\u02d9\u02d7\1\0\0\0\u02da\u02dd\1\0\0\0\u02db"+
		"\u02d9\1\0\0\0\u02db\u02dc\1\0\0\0\u02dc\u02de\1\0\0\0\u02dd\u02db\1\0"+
		"\0\0\u02de\u02df\5`\0\0\u02df\u00c0\1\0\0\0\u02e0\u02e8\5\"\0\0\u02e2"+
		"\u02e7\3\u00afW\0\u02e4\u02e7\3\u00b1X\0\u02e6\u02e2\1\0\0\0\u02e6\u02e4"+
		"\1\0\0\0\u02e7\u02ea\1\0\0\0\u02e8\u02e6\1\0\0\0\u02e8\u02e9\1\0\0\0\u02e9"+
		"\u02eb\1\0\0\0\u02ea\u02e8\1\0\0\0\u02eb\u02ec\5\"\0\0\u02ec\u00c2\1\0"+
		"\0\0\u02ed\u02ee\5\n\0\0\u02ee\u00c4\1\0\0\0\u02ef\u02f0\b\6\0\0\u02f0"+
		"\u00c6\1\0\0\0\u02f1\u02f2\b\7\0\0\u02f2\u00c8\1\0\0\0\u02f3\u02f4\b\b"+
		"\0\0\u02f4\u00ca\1\0\0\0\u02f5\u02fa\2az\0\u02f7\u02fa\2AZ\0\u02f9\u02f5"+
		"\1\0\0\0\u02f9\u02f7\1\0\0\0\u02fa\u00cc\1\0\0\0\u02fb\u02fc\2\609\0\u02fc"+
		"\u00ce\1\0\0\0\u02fd\u0302\3\u00cbe\0\u02ff\u0302\5_\0\0\u0301\u02fd\1"+
		"\0\0\0\u0301\u02ff\1\0\0\0\u0302\u00d0\1\0\0\0\u0303\u0304\2\609\0\u0304"+
		"\u00d2\1\0\0\0\u0305\u0306\2\60\67\0\u0306\u00d4\1\0\0\0\u0307\u030e\2"+
		"\609\0\u0309\u030e\2af\0\u030b\u030e\2AF\0\u030d\u0307\1\0\0\0\u030d\u0309"+
		"\1\0\0\0\u030d\u030b\1\0\0\0\u030e\u00d6\1\0\0\0\u030f\u0310\t\0\0\0\u0310"+
		"\u00d8\1\0\0\0\36\0\1\u01f4\1\u01f6\1\u01fd\1\u0203\1\u0210\1\u021c\0"+
		"\u022a\1\u0232\1\u023b\1\u0246\1\u024c\1\u0256\1\u025a\1\u0266\1\u0268"+
		"\1\u026e\1\u0274\1\u027e\1\u028a\1\u0294\1\u029a\1\u02d1\1\u02d9\1\u02db"+
		"\1\u02e6\1\u02e8\1\u02f9\1\u0301\1\u030d\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}