// $ANTLR ANTLRVersion> GoParserBase.java generatedTimestamp>

package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GoParserBase extends Parser {
	public static final int
		Switch=25, Pipe=34, PipeEqual=45, LessThan=56, GreaterEqual=62, Goto=15, 
		Go=14, LessEqual=61, AmpEqual=44, Fallthrough=11, BangEqual=60, Case=4, 
		GreaterThan=57, Func=13, Percent=32, LeftArrow=52, IMAGINARY_LITERAL=81, 
		Default=8, ML_COMMENT=79, StarEqual=41, Map=19, CharLiteral=83, IDENTIFIER=75, 
		Amp=33, Chan=5, Ellipsis=64, Interface=18, ANYCHAR=85, Select=23, AmpCaretEqual=49, 
		Or=51, COMMENT=78, Return=22, Struct=24, If=16, Caret=35, PercentEqual=43, 
		RightShiftEqual=48, And=50, INT_LITERAL=80, Import=17, Type=26, PlusEqual=39, 
		Continue=7, MinusEqual=40, ColonEqual=63, LeftShiftEqual=47, Colon=74, 
		LeftShift=36, EqualEqual=55, Const=6, Equal=58, Dec=54, Package=20, For=12, 
		RightShift=37, StringLiteral=84, FLOAT_LITERAL=82, CaretEqual=46, Range=21, 
		Plus=28, Bang=59, Minus=29, WS=76, Semi=73, NEWLINE=77, Break=3, Inc=53, 
		LeftBrace=69, SlashEqual=42, Dot=72, LeftBrack=67, RightBrack=68, LeftParen=65, 
		Defer=9, Star=30, RightParen=66, AmpCaret=38, Else=10, Comma=71, Var=27, 
		Slash=31, RightBrace=70;
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
	public static final int
		RULE_type = 0, RULE_typeName = 1, RULE_typeLiteral = 2, RULE_arrayType = 3, 
		RULE_arrayLength = 4, RULE_elementType = 5, RULE_sliceType = 6, RULE_structType = 7, 
		RULE_fieldDecl = 8, RULE_anonymousField = 9, RULE_tag = 10, RULE_pointerType = 11, 
		RULE_baseType = 12, RULE_functionType = 13, RULE_signature = 14, RULE_result = 15, 
		RULE_parameters = 16, RULE_parameterList = 17, RULE_parameterDecl = 18, 
		RULE_interfaceType = 19, RULE_methodSpec = 20, RULE_methodName = 21, RULE_interfaceTypeName = 22, 
		RULE_mapType = 23, RULE_keyType = 24, RULE_channelType = 25, RULE_block = 26, 
		RULE_declaration = 27, RULE_topLevelDecl = 28, RULE_constDecl = 29, RULE_constSpec = 30, 
		RULE_identifierList = 31, RULE_expressionList = 32, RULE_typeDecl = 33, 
		RULE_typeSpec = 34, RULE_varDecl = 35, RULE_varSpec = 36, RULE_shortVarDecl = 37, 
		RULE_functionDecl = 38, RULE_body = 39, RULE_methodDecl = 40, RULE_receiver = 41, 
		RULE_baseTypeName = 42, RULE_operand = 43, RULE_literal = 44, RULE_basicLiteral = 45, 
		RULE_qualifiedIdentifier = 46, RULE_methodExpr = 47, RULE_receiverType = 48, 
		RULE_compositeLiteral = 49, RULE_literalType = 50, RULE_literalValue = 51, 
		RULE_elementList = 52, RULE_element = 53, RULE_key = 54, RULE_fieldName = 55, 
		RULE_elementIndex = 56, RULE_value = 57, RULE_functionLiteral = 58, RULE_expression = 59, 
		RULE_primaryExpr = 60, RULE_argumentList = 61, RULE_conversion = 62, RULE_statement = 63, 
		RULE_simpleStmt = 64, RULE_emptyStmt = 65, RULE_labeledStmt = 66, RULE_label = 67, 
		RULE_expressionStmt = 68, RULE_sendStmt = 69, RULE_channel = 70, RULE_incDecStmt = 71, 
		RULE_assignment = 72, RULE_assignOp = 73, RULE_addAssignOp = 74, RULE_mulAssignOp = 75, 
		RULE_ifStmt = 76, RULE_switchStmt = 77, RULE_exprSwitchStmt = 78, RULE_exprCaseClause = 79, 
		RULE_exprSwitchCase = 80, RULE_typeSwitchStmt = 81, RULE_typeSwitchGuard = 82, 
		RULE_typeCaseClause = 83, RULE_typeSwitchCase = 84, RULE_typeList = 85, 
		RULE_forStmt = 86, RULE_condition = 87, RULE_forClause = 88, RULE_initStmt = 89, 
		RULE_postStmt = 90, RULE_rangeClause = 91, RULE_goStmt = 92, RULE_selectStmt = 93, 
		RULE_commClause = 94, RULE_commCase = 95, RULE_recvStmt = 96, RULE_recvExpr = 97, 
		RULE_returnStmt = 98, RULE_breakStmt = 99, RULE_continueStmt = 100, RULE_gotoStmt = 101, 
		RULE_fallthroughStmt = 102, RULE_deferStmt = 103, RULE_builtinCall = 104, 
		RULE_builtinArgs = 105, RULE_sourceFile = 106, RULE_packageClause = 107, 
		RULE_packageName = 108, RULE_importDecl = 109, RULE_importSpec = 110, 
		RULE_importPath = 111;
	public static final String[] ruleNames = {
		"type", "typeName", "typeLiteral", "arrayType", "arrayLength", "elementType", 
		"sliceType", "structType", "fieldDecl", "anonymousField", "tag", "pointerType", 
		"baseType", "functionType", "signature", "result", "parameters", "parameterList", 
		"parameterDecl", "interfaceType", "methodSpec", "methodName", "interfaceTypeName", 
		"mapType", "keyType", "channelType", "block", "declaration", "topLevelDecl", 
		"constDecl", "constSpec", "identifierList", "expressionList", "typeDecl", 
		"typeSpec", "varDecl", "varSpec", "shortVarDecl", "functionDecl", "body", 
		"methodDecl", "receiver", "baseTypeName", "operand", "literal", "basicLiteral", 
		"qualifiedIdentifier", "methodExpr", "receiverType", "compositeLiteral", 
		"literalType", "literalValue", "elementList", "element", "key", "fieldName", 
		"elementIndex", "value", "functionLiteral", "expression", "primaryExpr", 
		"argumentList", "conversion", "statement", "simpleStmt", "emptyStmt", 
		"labeledStmt", "label", "expressionStmt", "sendStmt", "channel", "incDecStmt", 
		"assignment", "assignOp", "addAssignOp", "mulAssignOp", "ifStmt", "switchStmt", 
		"exprSwitchStmt", "exprCaseClause", "exprSwitchCase", "typeSwitchStmt", 
		"typeSwitchGuard", "typeCaseClause", "typeSwitchCase", "typeList", "forStmt", 
		"condition", "forClause", "initStmt", "postStmt", "rangeClause", "goStmt", 
		"selectStmt", "commClause", "commCase", "recvStmt", "recvExpr", "returnStmt", 
		"breakStmt", "continueStmt", "gotoStmt", "fallthroughStmt", "deferStmt", 
		"builtinCall", "builtinArgs", "sourceFile", "packageClause", "packageName", 
		"importDecl", "importSpec", "importPath"
	};
	public GoParserBase(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class typeContext extends ParserRuleContext<Token> {
		public typeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeContext type() throws RecognitionException {
		typeContext _localctx = new typeContext(_ctx, 0);
		enterRule(_localctx, RULE_type);
		try {
			setState(234);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(224); typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(226); typeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(228); match(LeftParen);
					setState(230); type();
					setState(232); match(RightParen);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeNameContext extends ParserRuleContext<Token> {
		public typeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeNameContext typeName() throws RecognitionException {
		typeNameContext _localctx = new typeNameContext(_ctx, 2);
		enterRule(_localctx, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); qualifiedIdentifier();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeLiteralContext extends ParserRuleContext<Token> {
		public typeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeLiteralContext typeLiteral() throws RecognitionException {
		typeLiteralContext _localctx = new typeLiteralContext(_ctx, 4);
		enterRule(_localctx, RULE_typeLiteral);
		try {
			setState(254);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(238); arrayType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(240); structType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(242); pointerType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(244); functionType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(246); interfaceType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(248); sliceType();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(250); mapType();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(252); channelType();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class arrayTypeContext extends ParserRuleContext<Token> {
		public arrayTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final arrayTypeContext arrayType() throws RecognitionException {
		arrayTypeContext _localctx = new arrayTypeContext(_ctx, 6);
		enterRule(_localctx, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(LeftBrack);
			setState(258); arrayLength();
			setState(260); match(RightBrack);
			setState(262); elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class arrayLengthContext extends ParserRuleContext<Token> {
		public arrayLengthContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final arrayLengthContext arrayLength() throws RecognitionException {
		arrayLengthContext _localctx = new arrayLengthContext(_ctx, 8);
		enterRule(_localctx, RULE_arrayLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementTypeContext extends ParserRuleContext<Token> {
		public elementTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final elementTypeContext elementType() throws RecognitionException {
		elementTypeContext _localctx = new elementTypeContext(_ctx, 10);
		enterRule(_localctx, RULE_elementType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); type();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class sliceTypeContext extends ParserRuleContext<Token> {
		public sliceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final sliceTypeContext sliceType() throws RecognitionException {
		sliceTypeContext _localctx = new sliceTypeContext(_ctx, 12);
		enterRule(_localctx, RULE_sliceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268); match(LeftBrack);
			setState(270); match(RightBrack);
			setState(272); elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class structTypeContext extends ParserRuleContext<Token> {
		public structTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final structTypeContext structType() throws RecognitionException {
		structTypeContext _localctx = new structTypeContext(_ctx, 14);
		enterRule(_localctx, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274); match(Struct);
			setState(276); match(LeftBrace);
			setState(284);
			_errHandler.sync(this);
			int _alt140 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt140!=2 && _alt140!=-1 ) {
			    if ( _alt140==1 ) {
			        {
			        {
			        setState(278); fieldDecl();
			        setState(280); match(Semi);
			        }
			        } 
			    }
				setState(286);
				_errHandler.sync(this);
				_alt140 = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(287); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class fieldDeclContext extends ParserRuleContext<Token> {
		public identifierListContext idList;;
		public fieldDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final fieldDeclContext fieldDecl() throws RecognitionException {
		fieldDeclContext _localctx = new fieldDeclContext(_ctx, 16);
		enterRule(_localctx, RULE_fieldDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(289); _localctx.idList = identifierList();
					setState(291); type();
					}
					break;

				case 2:
					{
					setState(293); anonymousField();
					}
					break;
			}
			setState(299);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(297); tag();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class anonymousFieldContext extends ParserRuleContext<Token> {
		public anonymousFieldContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final anonymousFieldContext anonymousField() throws RecognitionException {
		anonymousFieldContext _localctx = new anonymousFieldContext(_ctx, 18);
		enterRule(_localctx, RULE_anonymousField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(301); match(Star);
					}
					break;
			}
			setState(305); typeName();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class tagContext extends ParserRuleContext<Token> {
		public tagContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final tagContext tag() throws RecognitionException {
		tagContext _localctx = new tagContext(_ctx, 20);
		enterRule(_localctx, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307); match(StringLiteral);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class pointerTypeContext extends ParserRuleContext<Token> {
		public pointerTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final pointerTypeContext pointerType() throws RecognitionException {
		pointerTypeContext _localctx = new pointerTypeContext(_ctx, 22);
		enterRule(_localctx, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(Star);
			setState(311); baseType();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class baseTypeContext extends ParserRuleContext<Token> {
		public baseTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final baseTypeContext baseType() throws RecognitionException {
		baseTypeContext _localctx = new baseTypeContext(_ctx, 24);
		enterRule(_localctx, RULE_baseType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313); type();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class functionTypeContext extends ParserRuleContext<Token> {
		public functionTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final functionTypeContext functionType() throws RecognitionException {
		functionTypeContext _localctx = new functionTypeContext(_ctx, 26);
		enterRule(_localctx, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); match(Func);
			setState(317); signature();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class signatureContext extends ParserRuleContext<Token> {
		public signatureContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final signatureContext signature() throws RecognitionException {
		signatureContext _localctx = new signatureContext(_ctx, 28);
		enterRule(_localctx, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319); parameters();
			setState(323);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(321); result();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class resultContext extends ParserRuleContext<Token> {
		public resultContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final resultContext result() throws RecognitionException {
		resultContext _localctx = new resultContext(_ctx, 30);
		enterRule(_localctx, RULE_result);
		try {
			setState(329);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(325); parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(327); type();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class parametersContext extends ParserRuleContext<Token> {
		public parametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final parametersContext parameters() throws RecognitionException {
		parametersContext _localctx = new parametersContext(_ctx, 32);
		enterRule(_localctx, RULE_parameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(LeftParen);
			setState(339);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(333); parameterList();
					setState(337);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
						case 1:
							{
							setState(335); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(341); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class parameterListContext extends ParserRuleContext<Token> {
		public parameterListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final parameterListContext parameterList() throws RecognitionException {
		parameterListContext _localctx = new parameterListContext(_ctx, 34);
		enterRule(_localctx, RULE_parameterList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343); parameterDecl();
			setState(351);
			_errHandler.sync(this);
			int _alt266 = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt266!=2 && _alt266!=-1 ) {
			    if ( _alt266==1 ) {
			        {
			        {
			        setState(345); match(Comma);
			        setState(347); parameterDecl();
			        }
			        } 
			    }
				setState(353);
				_errHandler.sync(this);
				_alt266 = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class parameterDeclContext extends ParserRuleContext<Token> {
		public identifierListContext idList;;
		public parameterDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final parameterDeclContext parameterDecl() throws RecognitionException {
		parameterDeclContext _localctx = new parameterDeclContext(_ctx, 36);
		enterRule(_localctx, RULE_parameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(354); _localctx.idList = identifierList();
					}
					break;
			}
			setState(360);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(358); match(Ellipsis);
					}
					break;
			}
			setState(362); type();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class interfaceTypeContext extends ParserRuleContext<Token> {
		public interfaceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final interfaceTypeContext interfaceType() throws RecognitionException {
		interfaceTypeContext _localctx = new interfaceTypeContext(_ctx, 38);
		enterRule(_localctx, RULE_interfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364); match(Interface);
			setState(366); match(LeftBrace);
			setState(374);
			_errHandler.sync(this);
			int _alt299 = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt299!=2 && _alt299!=-1 ) {
			    if ( _alt299==1 ) {
			        {
			        {
			        setState(368); methodSpec();
			        setState(370); match(Semi);
			        }
			        } 
			    }
				setState(376);
				_errHandler.sync(this);
				_alt299 = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(377); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class methodSpecContext extends ParserRuleContext<Token> {
		public methodSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final methodSpecContext methodSpec() throws RecognitionException {
		methodSpecContext _localctx = new methodSpecContext(_ctx, 40);
		enterRule(_localctx, RULE_methodSpec);
		try {
			setState(385);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(379); methodName();
					setState(381); signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(383); interfaceTypeName();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class methodNameContext extends ParserRuleContext<Token> {
		public Token name;;
		public methodNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final methodNameContext methodName() throws RecognitionException {
		methodNameContext _localctx = new methodNameContext(_ctx, 42);
		enterRule(_localctx, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); _localctx.name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class interfaceTypeNameContext extends ParserRuleContext<Token> {
		public interfaceTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final interfaceTypeNameContext interfaceTypeName() throws RecognitionException {
		interfaceTypeNameContext _localctx = new interfaceTypeNameContext(_ctx, 44);
		enterRule(_localctx, RULE_interfaceTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389); typeName();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class mapTypeContext extends ParserRuleContext<Token> {
		public mapTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final mapTypeContext mapType() throws RecognitionException {
		mapTypeContext _localctx = new mapTypeContext(_ctx, 46);
		enterRule(_localctx, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391); match(Map);
			setState(393); match(LeftBrack);
			setState(395); keyType();
			setState(397); match(RightBrack);
			setState(399); elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class keyTypeContext extends ParserRuleContext<Token> {
		public keyTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final keyTypeContext keyType() throws RecognitionException {
		keyTypeContext _localctx = new keyTypeContext(_ctx, 48);
		enterRule(_localctx, RULE_keyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401); type();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class channelTypeContext extends ParserRuleContext<Token> {
		public channelTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final channelTypeContext channelType() throws RecognitionException {
		channelTypeContext _localctx = new channelTypeContext(_ctx, 50);
		enterRule(_localctx, RULE_channelType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(403); match(Chan);
					setState(407);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
						case 1:
							{
							setState(405); match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(409); match(LeftArrow);
					setState(411); match(Chan);
					}
					break;
			}
			setState(415); elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class blockContext extends ParserRuleContext<Token> {
		public blockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final blockContext block() throws RecognitionException {
		blockContext _localctx = new blockContext(_ctx, 52);
		enterRule(_localctx, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417); match(LeftBrace);
			setState(425);
			_errHandler.sync(this);
			int _alt395 = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt395!=2 && _alt395!=-1 ) {
			    if ( _alt395==1 ) {
			        {
			        {
			        setState(419); statement();
			        setState(421); match(Semi);
			        }
			        } 
			    }
				setState(427);
				_errHandler.sync(this);
				_alt395 = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(428); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class declarationContext extends ParserRuleContext<Token> {
		public declarationContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final declarationContext declaration() throws RecognitionException {
		declarationContext _localctx = new declarationContext(_ctx, 54);
		enterRule(_localctx, RULE_declaration);
		try {
			setState(436);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(430); constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(432); typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(434); varDecl();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class topLevelDeclContext extends ParserRuleContext<Token> {
		public topLevelDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final topLevelDeclContext topLevelDecl() throws RecognitionException {
		topLevelDeclContext _localctx = new topLevelDeclContext(_ctx, 56);
		enterRule(_localctx, RULE_topLevelDecl);
		try {
			setState(444);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(438); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(440); functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(442); methodDecl();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class constDeclContext extends ParserRuleContext<Token> {
		public constDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final constDeclContext constDecl() throws RecognitionException {
		constDeclContext _localctx = new constDeclContext(_ctx, 58);
		enterRule(_localctx, RULE_constDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446); match(Const);
			setState(463);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(448); constSpec();
					}
					break;

				case 2:
					{
					setState(450); match(LeftParen);
					setState(458);
					_errHandler.sync(this);
					int _alt452 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt452!=2 && _alt452!=-1 ) {
					    if ( _alt452==1 ) {
					        {
					        {
					        setState(452); constSpec();
					        setState(454); match(Semi);
					        }
					        } 
					    }
						setState(460);
						_errHandler.sync(this);
						_alt452 = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(461); match(RightParen);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class constSpecContext extends ParserRuleContext<Token> {
		public identifierListContext idList;;
		public typeContext explicitType;;
		public expressionListContext valueList;;
		public constSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final constSpecContext constSpec() throws RecognitionException {
		constSpecContext _localctx = new constSpecContext(_ctx, 60);
		enterRule(_localctx, RULE_constSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465); _localctx.idList = identifierList();
			setState(475);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(469);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
						case 1:
							{
							setState(467); _localctx.explicitType = type();
							}
							break;
					}
					setState(471); match(Equal);
					setState(473); _localctx.valueList = expressionList();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class identifierListContext extends ParserRuleContext<Token> {
		public Token ids;;
		public List<Token> ids_list = new ArrayList<Token>();;
		public identifierListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final identifierListContext identifierList() throws RecognitionException {
		identifierListContext _localctx = new identifierListContext(_ctx, 62);
		enterRule(_localctx, RULE_identifierList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477); _localctx.ids = match(IDENTIFIER);
			_localctx.ids_list.add(_localctx.ids);
			setState(485);
			_errHandler.sync(this);
			int _alt499 = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt499!=2 && _alt499!=-1 ) {
			    if ( _alt499==1 ) {
			        {
			        {
			        setState(479); match(Comma);
			        setState(481); _localctx.ids = match(IDENTIFIER);
			        _localctx.ids_list.add(_localctx.ids);
			        }
			        } 
			    }
				setState(487);
				_errHandler.sync(this);
				_alt499 = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class expressionListContext extends ParserRuleContext<Token> {
		public expressionContext expressions;;
		public List<expressionContext> expressions_list = new ArrayList<expressionContext>();;
		public expressionListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final expressionListContext expressionList() throws RecognitionException {
		expressionListContext _localctx = new expressionListContext(_ctx, 64);
		enterRule(_localctx, RULE_expressionList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488); _localctx.expressions = expression(0);
			_localctx.expressions_list.add(_localctx.expressions);
			setState(496);
			_errHandler.sync(this);
			int _alt518 = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt518!=2 && _alt518!=-1 ) {
			    if ( _alt518==1 ) {
			        {
			        {
			        setState(490); match(Comma);
			        setState(492); _localctx.expressions = expression(0);
			        _localctx.expressions_list.add(_localctx.expressions);
			        }
			        } 
			    }
				setState(498);
				_errHandler.sync(this);
				_alt518 = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeDeclContext extends ParserRuleContext<Token> {
		public typeDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeDeclContext typeDecl() throws RecognitionException {
		typeDeclContext _localctx = new typeDeclContext(_ctx, 66);
		enterRule(_localctx, RULE_typeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499); match(Type);
			setState(516);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(501); typeSpec();
					}
					break;

				case 2:
					{
					setState(503); match(LeftParen);
					setState(511);
					_errHandler.sync(this);
					int _alt541 = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt541!=2 && _alt541!=-1 ) {
					    if ( _alt541==1 ) {
					        {
					        {
					        setState(505); typeSpec();
					        setState(507); match(Semi);
					        }
					        } 
					    }
						setState(513);
						_errHandler.sync(this);
						_alt541 = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(514); match(RightParen);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeSpecContext extends ParserRuleContext<Token> {
		public Token name;;
		public typeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeSpecContext typeSpec() throws RecognitionException {
		typeSpecContext _localctx = new typeSpecContext(_ctx, 68);
		enterRule(_localctx, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518); _localctx.name = match(IDENTIFIER);
			setState(520); type();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class varDeclContext extends ParserRuleContext<Token> {
		public varDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final varDeclContext varDecl() throws RecognitionException {
		varDeclContext _localctx = new varDeclContext(_ctx, 70);
		enterRule(_localctx, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522); match(Var);
			setState(539);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(524); varSpec();
					}
					break;

				case 2:
					{
					setState(526); match(LeftParen);
					setState(534);
					_errHandler.sync(this);
					int _alt580 = getInterpreter().adaptivePredict(_input,28,_ctx);
					while ( _alt580!=2 && _alt580!=-1 ) {
					    if ( _alt580==1 ) {
					        {
					        {
					        setState(528); varSpec();
					        setState(530); match(Semi);
					        }
					        } 
					    }
						setState(536);
						_errHandler.sync(this);
						_alt580 = getInterpreter().adaptivePredict(_input,28,_ctx);
					}
					setState(537); match(RightParen);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class varSpecContext extends ParserRuleContext<Token> {
		public identifierListContext idList;;
		public typeContext varType;;
		public expressionListContext exprList;;
		public varSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final varSpecContext varSpec() throws RecognitionException {
		varSpecContext _localctx = new varSpecContext(_ctx, 72);
		enterRule(_localctx, RULE_varSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541); _localctx.idList = identifierList();
			setState(555);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(543); _localctx.varType = type();
					setState(549);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
						case 1:
							{
							setState(545); match(Equal);
							setState(547); _localctx.exprList = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(551); match(Equal);
					setState(553); _localctx.exprList = expressionList();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class shortVarDeclContext extends ParserRuleContext<Token> {
		public identifierListContext idList;;
		public expressionListContext exprList;;
		public shortVarDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final shortVarDeclContext shortVarDecl() throws RecognitionException {
		shortVarDeclContext _localctx = new shortVarDeclContext(_ctx, 74);
		enterRule(_localctx, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557); _localctx.idList = identifierList();
			setState(559); match(ColonEqual);
			setState(561); _localctx.exprList = expressionList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class functionDeclContext extends ParserRuleContext<Token> {
		public Token name;;
		public signatureContext sig;;
		public bodyContext bdy;;
		public functionDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final functionDeclContext functionDecl() throws RecognitionException {
		functionDeclContext _localctx = new functionDeclContext(_ctx, 76);
		enterRule(_localctx, RULE_functionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563); match(Func);
			setState(565); _localctx.name = match(IDENTIFIER);
			setState(567); _localctx.sig = signature();
			setState(571);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(569); _localctx.bdy = body();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class bodyContext extends ParserRuleContext<Token> {
		public bodyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final bodyContext body() throws RecognitionException {
		bodyContext _localctx = new bodyContext(_ctx, 78);
		enterRule(_localctx, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573); block();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class methodDeclContext extends ParserRuleContext<Token> {
		public receiverContext recv;;
		public methodNameContext name;;
		public signatureContext sig;;
		public bodyContext bdy;;
		public methodDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final methodDeclContext methodDecl() throws RecognitionException {
		methodDeclContext _localctx = new methodDeclContext(_ctx, 80);
		enterRule(_localctx, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575); match(Func);
			setState(577); _localctx.recv = receiver();
			setState(579); _localctx.name = methodName();
			setState(581); _localctx.sig = signature();
			setState(585);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(583); _localctx.bdy = body();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class receiverContext extends ParserRuleContext<Token> {
		public receiverContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final receiverContext receiver() throws RecognitionException {
		receiverContext _localctx = new receiverContext(_ctx, 82);
		enterRule(_localctx, RULE_receiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587); match(LeftParen);
			setState(591);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(589); match(IDENTIFIER);
					}
					break;
			}
			setState(595);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(593); match(Star);
					}
					break;
			}
			setState(597); baseTypeName();
			setState(599); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class baseTypeNameContext extends ParserRuleContext<Token> {
		public baseTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final baseTypeNameContext baseTypeName() throws RecognitionException {
		baseTypeNameContext _localctx = new baseTypeNameContext(_ctx, 84);
		enterRule(_localctx, RULE_baseTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601); match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class operandContext extends ParserRuleContext<Token> {
		public operandContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final operandContext operand() throws RecognitionException {
		operandContext _localctx = new operandContext(_ctx, 86);
		enterRule(_localctx, RULE_operand);
		try {
			setState(615);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(603); literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(605); qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(607); methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(609); match(LeftParen);
					setState(611); expression(0);
					setState(613); match(RightParen);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class literalContext extends ParserRuleContext<Token> {
		public literalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final literalContext literal() throws RecognitionException {
		literalContext _localctx = new literalContext(_ctx, 88);
		enterRule(_localctx, RULE_literal);
		try {
			setState(623);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(617); basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(619); compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(621); functionLiteral();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class basicLiteralContext extends ParserRuleContext<Token> {
		public basicLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final basicLiteralContext basicLiteral() throws RecognitionException {
		basicLiteralContext _localctx = new basicLiteralContext(_ctx, 90);
		enterRule(_localctx, RULE_basicLiteral);
		try {
			setState(635);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(625); match(INT_LITERAL);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(627); match(FLOAT_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(629); match(IMAGINARY_LITERAL);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(631); match(CharLiteral);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(633); match(StringLiteral);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class qualifiedIdentifierContext extends ParserRuleContext<Token> {
		public packageNameContext pkg;;
		public Token id;;
		public qualifiedIdentifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final qualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		qualifiedIdentifierContext _localctx = new qualifiedIdentifierContext(_ctx, 92);
		enterRule(_localctx, RULE_qualifiedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(641);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(637); _localctx.pkg = packageName();
					setState(639); match(Dot);
					}
					break;
			}
			setState(643); _localctx.id = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class methodExprContext extends ParserRuleContext<Token> {
		public receiverTypeContext recvType;;
		public methodNameContext name;;
		public methodExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final methodExprContext methodExpr() throws RecognitionException {
		methodExprContext _localctx = new methodExprContext(_ctx, 94);
		enterRule(_localctx, RULE_methodExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645); _localctx.recvType = receiverType();
			setState(647); match(Dot);
			setState(649); _localctx.name = methodName();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class receiverTypeContext extends ParserRuleContext<Token> {
		public receiverTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final receiverTypeContext receiverType() throws RecognitionException {
		receiverTypeContext _localctx = new receiverTypeContext(_ctx, 96);
		enterRule(_localctx, RULE_receiverType);
		try {
			setState(661);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(651); typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(653); match(LeftParen);
					setState(655); match(Star);
					setState(657); typeName();
					setState(659); match(RightParen);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class compositeLiteralContext extends ParserRuleContext<Token> {
		public compositeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final compositeLiteralContext compositeLiteral() throws RecognitionException {
		compositeLiteralContext _localctx = new compositeLiteralContext(_ctx, 98);
		enterRule(_localctx, RULE_compositeLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(663); literalType();
			setState(665); literalValue();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class literalTypeContext extends ParserRuleContext<Token> {
		public literalTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final literalTypeContext literalType() throws RecognitionException {
		literalTypeContext _localctx = new literalTypeContext(_ctx, 100);
		enterRule(_localctx, RULE_literalType);
		try {
			setState(685);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(667); structType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(669); arrayType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(671); match(LeftBrack);
					setState(673); match(Ellipsis);
					setState(675); match(RightBrack);
					setState(677); elementType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(679); sliceType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(681); mapType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(683); typeName();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class literalValueContext extends ParserRuleContext<Token> {
		public literalValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final literalValueContext literalValue() throws RecognitionException {
		literalValueContext _localctx = new literalValueContext(_ctx, 102);
		enterRule(_localctx, RULE_literalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687); match(LeftBrace);
			setState(695);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(689); elementList();
					setState(693);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
						case 1:
							{
							setState(691); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(697); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementListContext extends ParserRuleContext<Token> {
		public elementListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final elementListContext elementList() throws RecognitionException {
		elementListContext _localctx = new elementListContext(_ctx, 104);
		enterRule(_localctx, RULE_elementList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(699); element();
			setState(707);
			_errHandler.sync(this);
			int _alt909 = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt909!=2 && _alt909!=-1 ) {
			    if ( _alt909==1 ) {
			        {
			        {
			        setState(701); match(Comma);
			        setState(703); element();
			        }
			        } 
			    }
				setState(709);
				_errHandler.sync(this);
				_alt909 = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementContext extends ParserRuleContext<Token> {
		public elementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final elementContext element() throws RecognitionException {
		elementContext _localctx = new elementContext(_ctx, 106);
		enterRule(_localctx, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(714);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(710); key();
					setState(712); match(Colon);
					}
					break;
			}
			setState(716); value();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class keyContext extends ParserRuleContext<Token> {
		public keyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final keyContext key() throws RecognitionException {
		keyContext _localctx = new keyContext(_ctx, 108);
		enterRule(_localctx, RULE_key);
		try {
			setState(722);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(718); fieldName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(720); elementIndex();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class fieldNameContext extends ParserRuleContext<Token> {
		public fieldNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final fieldNameContext fieldName() throws RecognitionException {
		fieldNameContext _localctx = new fieldNameContext(_ctx, 110);
		enterRule(_localctx, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724); match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementIndexContext extends ParserRuleContext<Token> {
		public elementIndexContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final elementIndexContext elementIndex() throws RecognitionException {
		elementIndexContext _localctx = new elementIndexContext(_ctx, 112);
		enterRule(_localctx, RULE_elementIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(726); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class valueContext extends ParserRuleContext<Token> {
		public valueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final valueContext value() throws RecognitionException {
		valueContext _localctx = new valueContext(_ctx, 114);
		enterRule(_localctx, RULE_value);
		try {
			setState(732);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(728); expression(0);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(730); literalValue();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class functionLiteralContext extends ParserRuleContext<Token> {
		public functionLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final functionLiteralContext functionLiteral() throws RecognitionException {
		functionLiteralContext _localctx = new functionLiteralContext(_ctx, 116);
		enterRule(_localctx, RULE_functionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734); functionType();
			setState(736); body();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class expressionContext extends ParserRuleContext<Token> {
		public int _p;
		public expressionContext(ParserRuleContext<Token> parent, int state) { super(parent, state); }
		public expressionContext(ParserRuleContext<Token> parent, int state, int _p) {
			super(parent, state);
			this._p = _p;
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final expressionContext expression(int _p) throws RecognitionException {
	    ParserRuleContext<Token> _parentctx = _ctx;
		expressionContext _localctx = new expressionContext(_ctx, 118, _p);
		expressionContext _prevctx = _localctx;
		int _startState = 118;
	    pushNewRecursionContext(_localctx, RULE_expression);
		int _la;
		try {
		    enterOuterAlt(_localctx, 1);
		    {
		    setState(772);
		    //_errHandler.sync(this);
		    switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
		    	case 1:
		    		{
		    		setState(738); match(Plus);
		    		setState(740); expression(12);
		    		}
		    		break;

		    	case 2:
		    		{
		    		setState(742); match(Minus);
		    		setState(744); expression(11);
		    		}
		    		break;

		    	case 3:
		    		{
		    		setState(746); match(Bang);
		    		setState(748); expression(10);
		    		}
		    		break;

		    	case 4:
		    		{
		    		setState(750); match(Caret);
		    		setState(752); expression(9);
		    		}
		    		break;

		    	case 5:
		    		{
		    		setState(754); match(Star);
		    		setState(756); expression(8);
		    		}
		    		break;

		    	case 6:
		    		{
		    		setState(758); match(Amp);
		    		setState(760); expression(7);
		    		}
		    		break;

		    	case 7:
		    		{
		    		setState(762); match(LeftArrow);
		    		setState(764); expression(6);
		    		}
		    		break;

		    	case 8:
		    		{
		    		setState(766); operand();
		    		}
		    		break;

		    	case 9:
		    		{
		    		setState(768); conversion();
		    		}
		    		break;

		    	case 10:
		    		{
		    		setState(770); builtinCall();
		    		}
		    		break;
		    }
		    _ctx.stop = _input.LT(-1);
		    setState(860);
		    _errHandler.sync(this);
		    int _alt238 = getInterpreter().adaptivePredict(_input,54,_ctx);
		    while ( _alt238!=2 && _alt238!=-1 ) {
		        if ( _alt238==1 ) {
		    	    if ( _parseListeners!=null ) triggerExitRuleEvent();
		    	    _prevctx = _localctx;
		            {
		            setState(858);
		            //_errHandler.sync(this);
		            switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
		            	case 1:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(774);
		            		if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
		            		setState(776);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Star || _la==Slash || _la==Percent || _la==Amp || _la==LeftShift || _la==RightShift || _la==AmpCaret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(778); expression(6);
		            		}
		            		break;

		            	case 2:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(780);
		            		if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
		            		setState(782);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Plus || _la==Minus || _la==Pipe || _la==Caret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(784); expression(5);
		            		}
		            		break;

		            	case 3:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(786);
		            		if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
		            		setState(788);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==EqualEqual || _la==LessThan || _la==GreaterThan || _la==BangEqual || _la==LessEqual || _la==GreaterEqual) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(790); expression(4);
		            		}
		            		break;

		            	case 4:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(792);
		            		if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
		            		setState(794); match(And);
		            		setState(796); expression(3);
		            		}
		            		break;

		            	case 5:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(798);
		            		if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
		            		setState(800); match(Or);
		            		setState(802); expression(2);
		            		}
		            		break;

		            	case 6:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(804);
		            		if (!(17 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {17 >= $_p}?");
		            		setState(806); match(Dot);
		            		setState(808); match(IDENTIFIER);
		            		}
		            		break;

		            	case 7:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(810);
		            		if (!(16 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {16 >= $_p}?");
		            		setState(812); match(LeftBrack);
		            		setState(814); expression(0);
		            		setState(816); match(RightBrack);
		            		}
		            		break;

		            	case 8:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(818);
		            		if (!(15 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {15 >= $_p}?");
		            		setState(820); match(LeftBrack);
		            		setState(824);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
		            			case 1:
		            				{
		            				setState(822); expression(0);
		            				}
		            				break;
		            		}
		            		setState(826); match(Colon);
		            		setState(830);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
		            			case 1:
		            				{
		            				setState(828); expression(0);
		            				}
		            				break;
		            		}
		            		setState(832); match(RightBrack);
		            		}
		            		break;

		            	case 9:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(834);
		            		if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {14 >= $_p}?");
		            		setState(836); match(Dot);
		            		setState(838); match(LeftParen);
		            		setState(840); type();
		            		setState(842); match(RightParen);
		            		}
		            		break;

		            	case 10:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(844);
		            		if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {13 >= $_p}?");
		            		setState(846); match(LeftParen);
		            		setState(854);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
		            			case 1:
		            				{
		            				setState(848); argumentList();
		            				setState(852);
		            				//_errHandler.sync(this);
		            				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
		            					case 1:
		            						{
		            						setState(850); match(Comma);
		            						}
		            						break;
		            				}
		            				}
		            				break;
		            		}
		            		setState(856); match(RightParen);
		            		}
		            		break;
		            }
		            } 
		        }
		    	setState(862);
		    	_errHandler.sync(this);
		    	_alt238 = getInterpreter().adaptivePredict(_input,54,_ctx);
		    }
		    }
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class primaryExprContext extends ParserRuleContext<Token> {
		public int _p;
		public primaryExprContext(ParserRuleContext<Token> parent, int state) { super(parent, state); }
		public primaryExprContext(ParserRuleContext<Token> parent, int state, int _p) {
			super(parent, state);
			this._p = _p;
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final primaryExprContext primaryExpr(int _p) throws RecognitionException {
	    ParserRuleContext<Token> _parentctx = _ctx;
		primaryExprContext _localctx = new primaryExprContext(_ctx, 120, _p);
		primaryExprContext _prevctx = _localctx;
		int _startState = 120;
	    pushNewRecursionContext(_localctx, RULE_primaryExpr);
		try {
		    enterOuterAlt(_localctx, 1);
		    {
		    setState(869);
		    //_errHandler.sync(this);
		    switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
		    	case 1:
		    		{
		    		setState(863); operand();
		    		}
		    		break;

		    	case 2:
		    		{
		    		setState(865); conversion();
		    		}
		    		break;

		    	case 3:
		    		{
		    		setState(867); builtinCall();
		    		}
		    		break;
		    }
		    _ctx.stop = _input.LT(-1);
		    setState(927);
		    _errHandler.sync(this);
		    int _alt82 = getInterpreter().adaptivePredict(_input,61,_ctx);
		    while ( _alt82!=2 && _alt82!=-1 ) {
		        if ( _alt82==1 ) {
		    	    if ( _parseListeners!=null ) triggerExitRuleEvent();
		    	    _prevctx = _localctx;
		            {
		            setState(925);
		            //_errHandler.sync(this);
		            switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
		            	case 1:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(871);
		            		if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
		            		setState(873); match(Dot);
		            		setState(875); match(IDENTIFIER);
		            		}
		            		break;

		            	case 2:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(877);
		            		if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
		            		setState(879); match(LeftBrack);
		            		setState(881); expression(0);
		            		setState(883); match(RightBrack);
		            		}
		            		break;

		            	case 3:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(885);
		            		if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
		            		setState(887); match(LeftBrack);
		            		setState(891);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
		            			case 1:
		            				{
		            				setState(889); expression(0);
		            				}
		            				break;
		            		}
		            		setState(893); match(Colon);
		            		setState(897);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
		            			case 1:
		            				{
		            				setState(895); expression(0);
		            				}
		            				break;
		            		}
		            		setState(899); match(RightBrack);
		            		}
		            		break;

		            	case 4:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(901);
		            		if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
		            		setState(903); match(Dot);
		            		setState(905); match(LeftParen);
		            		setState(907); type();
		            		setState(909); match(RightParen);
		            		}
		            		break;

		            	case 5:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(911);
		            		if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
		            		setState(913); match(LeftParen);
		            		setState(921);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
		            			case 1:
		            				{
		            				setState(915); argumentList();
		            				setState(919);
		            				//_errHandler.sync(this);
		            				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
		            					case 1:
		            						{
		            						setState(917); match(Comma);
		            						}
		            						break;
		            				}
		            				}
		            				break;
		            		}
		            		setState(923); match(RightParen);
		            		}
		            		break;
		            }
		            } 
		        }
		    	setState(929);
		    	_errHandler.sync(this);
		    	_alt82 = getInterpreter().adaptivePredict(_input,61,_ctx);
		    }
		    }
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class argumentListContext extends ParserRuleContext<Token> {
		public argumentListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final argumentListContext argumentList() throws RecognitionException {
		argumentListContext _localctx = new argumentListContext(_ctx, 122);
		enterRule(_localctx, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(930); expressionList();
			setState(934);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(932); match(Ellipsis);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class conversionContext extends ParserRuleContext<Token> {
		public conversionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final conversionContext conversion() throws RecognitionException {
		conversionContext _localctx = new conversionContext(_ctx, 124);
		enterRule(_localctx, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(936); type();
			setState(938); match(LeftParen);
			setState(940); expression(0);
			setState(942); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class statementContext extends ParserRuleContext<Token> {
		public statementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final statementContext statement() throws RecognitionException {
		statementContext _localctx = new statementContext(_ctx, 126);
		enterRule(_localctx, RULE_statement);
		try {
			setState(974);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(944); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(946); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(948); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(950); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(952); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(954); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(956); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(958); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(960); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(962); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(964); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(966); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(968); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(970); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(972); deferStmt();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class simpleStmtContext extends ParserRuleContext<Token> {
		public simpleStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final simpleStmtContext simpleStmt() throws RecognitionException {
		simpleStmtContext _localctx = new simpleStmtContext(_ctx, 128);
		enterRule(_localctx, RULE_simpleStmt);
		try {
			setState(988);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(976); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(978); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(980); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(982); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(984); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(986); shortVarDecl();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class emptyStmtContext extends ParserRuleContext<Token> {
		public emptyStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final emptyStmtContext emptyStmt() throws RecognitionException {
		emptyStmtContext _localctx = new emptyStmtContext(_ctx, 130);
		enterRule(_localctx, RULE_emptyStmt);
		try {
			{
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class labeledStmtContext extends ParserRuleContext<Token> {
		public labeledStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final labeledStmtContext labeledStmt() throws RecognitionException {
		labeledStmtContext _localctx = new labeledStmtContext(_ctx, 132);
		enterRule(_localctx, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(992); label();
			setState(994); match(Colon);
			setState(996); statement();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class labelContext extends ParserRuleContext<Token> {
		public labelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final labelContext label() throws RecognitionException {
		labelContext _localctx = new labelContext(_ctx, 134);
		enterRule(_localctx, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998); match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class expressionStmtContext extends ParserRuleContext<Token> {
		public expressionStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final expressionStmtContext expressionStmt() throws RecognitionException {
		expressionStmtContext _localctx = new expressionStmtContext(_ctx, 136);
		enterRule(_localctx, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1000); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class sendStmtContext extends ParserRuleContext<Token> {
		public sendStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final sendStmtContext sendStmt() throws RecognitionException {
		sendStmtContext _localctx = new sendStmtContext(_ctx, 138);
		enterRule(_localctx, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002); channel();
			setState(1004); match(LeftArrow);
			setState(1006); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class channelContext extends ParserRuleContext<Token> {
		public channelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final channelContext channel() throws RecognitionException {
		channelContext _localctx = new channelContext(_ctx, 140);
		enterRule(_localctx, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1008); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class incDecStmtContext extends ParserRuleContext<Token> {
		public incDecStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final incDecStmtContext incDecStmt() throws RecognitionException {
		incDecStmtContext _localctx = new incDecStmtContext(_ctx, 142);
		enterRule(_localctx, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1010); expression(0);
			setState(1012);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class assignmentContext extends ParserRuleContext<Token> {
		public assignmentContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final assignmentContext assignment() throws RecognitionException {
		assignmentContext _localctx = new assignmentContext(_ctx, 144);
		enterRule(_localctx, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1014); expressionList();
			setState(1016); assignOp();
			setState(1018); expressionList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class assignOpContext extends ParserRuleContext<Token> {
		public assignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final assignOpContext assignOp() throws RecognitionException {
		assignOpContext _localctx = new assignOpContext(_ctx, 146);
		enterRule(_localctx, RULE_assignOp);
		try {
			setState(1026);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1020); addAssignOp();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1022); mulAssignOp();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1024); match(Equal);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class addAssignOpContext extends ParserRuleContext<Token> {
		public addAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final addAssignOpContext addAssignOp() throws RecognitionException {
		addAssignOpContext _localctx = new addAssignOpContext(_ctx, 148);
		enterRule(_localctx, RULE_addAssignOp);
		try {
			setState(1036);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1028); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1030); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1032); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1034); match(CaretEqual);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class mulAssignOpContext extends ParserRuleContext<Token> {
		public mulAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final mulAssignOpContext mulAssignOp() throws RecognitionException {
		mulAssignOpContext _localctx = new mulAssignOpContext(_ctx, 150);
		enterRule(_localctx, RULE_mulAssignOp);
		try {
			setState(1052);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1038); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1040); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1042); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1044); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1046); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1048); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1050); match(AmpCaretEqual);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ifStmtContext extends ParserRuleContext<Token> {
		public ifStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final ifStmtContext ifStmt() throws RecognitionException {
		ifStmtContext _localctx = new ifStmtContext(_ctx, 152);
		enterRule(_localctx, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1054); match(If);
			setState(1060);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					{
					setState(1056); simpleStmt();
					setState(1058); match(Semi);
					}
					break;
			}
			setState(1062); expression(0);
			setState(1064); block();
			setState(1074);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(1066); match(Else);
					setState(1072);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
						case 1:
							{
							setState(1068); ifStmt();
							}
							break;

						case 2:
							{
							setState(1070); block();
							}
							break;
					}
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class switchStmtContext extends ParserRuleContext<Token> {
		public switchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final switchStmtContext switchStmt() throws RecognitionException {
		switchStmtContext _localctx = new switchStmtContext(_ctx, 154);
		enterRule(_localctx, RULE_switchStmt);
		try {
			setState(1080);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1076); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1078); typeSwitchStmt();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class exprSwitchStmtContext extends ParserRuleContext<Token> {
		public exprSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final exprSwitchStmtContext exprSwitchStmt() throws RecognitionException {
		exprSwitchStmtContext _localctx = new exprSwitchStmtContext(_ctx, 156);
		enterRule(_localctx, RULE_exprSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082); match(Switch);
			setState(1088);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(1084); simpleStmt();
					setState(1086); match(Semi);
					}
					break;
			}
			setState(1092);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					{
					setState(1090); expression(0);
					}
					break;
			}
			setState(1094); match(LeftBrace);
			setState(1100);
			_errHandler.sync(this);
			int _alt1628 = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt1628!=2 && _alt1628!=-1 ) {
			    if ( _alt1628==1 ) {
			        {
			        {
			        setState(1096); exprCaseClause();
			        }
			        } 
			    }
				setState(1102);
				_errHandler.sync(this);
				_alt1628 = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			setState(1103); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class exprCaseClauseContext extends ParserRuleContext<Token> {
		public exprCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final exprCaseClauseContext exprCaseClause() throws RecognitionException {
		exprCaseClauseContext _localctx = new exprCaseClauseContext(_ctx, 158);
		enterRule(_localctx, RULE_exprCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105); exprSwitchCase();
			setState(1107); match(Colon);
			setState(1115);
			_errHandler.sync(this);
			int _alt1647 = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt1647!=2 && _alt1647!=-1 ) {
			    if ( _alt1647==1 ) {
			        {
			        {
			        setState(1109); statement();
			        setState(1111); match(Semi);
			        }
			        } 
			    }
				setState(1117);
				_errHandler.sync(this);
				_alt1647 = getInterpreter().adaptivePredict(_input,75,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class exprSwitchCaseContext extends ParserRuleContext<Token> {
		public exprSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final exprSwitchCaseContext exprSwitchCase() throws RecognitionException {
		exprSwitchCaseContext _localctx = new exprSwitchCaseContext(_ctx, 160);
		enterRule(_localctx, RULE_exprSwitchCase);
		try {
			setState(1124);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1118); match(Case);
					setState(1120); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1122); match(Default);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeSwitchStmtContext extends ParserRuleContext<Token> {
		public typeSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeSwitchStmtContext typeSwitchStmt() throws RecognitionException {
		typeSwitchStmtContext _localctx = new typeSwitchStmtContext(_ctx, 162);
		enterRule(_localctx, RULE_typeSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1126); match(Switch);
			setState(1132);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(1128); simpleStmt();
					setState(1130); match(Semi);
					}
					break;
			}
			setState(1134); typeSwitchGuard();
			setState(1136); match(LeftBrace);
			setState(1142);
			_errHandler.sync(this);
			int _alt1683 = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt1683!=2 && _alt1683!=-1 ) {
			    if ( _alt1683==1 ) {
			        {
			        {
			        setState(1138); typeCaseClause();
			        }
			        } 
			    }
				setState(1144);
				_errHandler.sync(this);
				_alt1683 = getInterpreter().adaptivePredict(_input,78,_ctx);
			}
			setState(1145); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeSwitchGuardContext extends ParserRuleContext<Token> {
		public typeSwitchGuardContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeSwitchGuardContext typeSwitchGuard() throws RecognitionException {
		typeSwitchGuardContext _localctx = new typeSwitchGuardContext(_ctx, 164);
		enterRule(_localctx, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1151);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					{
					setState(1147); match(IDENTIFIER);
					setState(1149); match(ColonEqual);
					}
					break;
			}
			setState(1153); primaryExpr(0);
			setState(1155); match(Dot);
			setState(1157); match(LeftParen);
			setState(1159); match(Type);
			setState(1161); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeCaseClauseContext extends ParserRuleContext<Token> {
		public typeCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeCaseClauseContext typeCaseClause() throws RecognitionException {
		typeCaseClauseContext _localctx = new typeCaseClauseContext(_ctx, 166);
		enterRule(_localctx, RULE_typeCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1163); typeSwitchCase();
			setState(1165); match(Colon);
			setState(1173);
			_errHandler.sync(this);
			int _alt1725 = getInterpreter().adaptivePredict(_input,80,_ctx);
			while ( _alt1725!=2 && _alt1725!=-1 ) {
			    if ( _alt1725==1 ) {
			        {
			        {
			        setState(1167); statement();
			        setState(1169); match(Semi);
			        }
			        } 
			    }
				setState(1175);
				_errHandler.sync(this);
				_alt1725 = getInterpreter().adaptivePredict(_input,80,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeSwitchCaseContext extends ParserRuleContext<Token> {
		public typeSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeSwitchCaseContext typeSwitchCase() throws RecognitionException {
		typeSwitchCaseContext _localctx = new typeSwitchCaseContext(_ctx, 168);
		enterRule(_localctx, RULE_typeSwitchCase);
		try {
			setState(1182);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1176); match(Case);
					setState(1178); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1180); match(Default);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class typeListContext extends ParserRuleContext<Token> {
		public typeListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final typeListContext typeList() throws RecognitionException {
		typeListContext _localctx = new typeListContext(_ctx, 170);
		enterRule(_localctx, RULE_typeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1184); type();
			setState(1192);
			_errHandler.sync(this);
			int _alt1754 = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt1754!=2 && _alt1754!=-1 ) {
			    if ( _alt1754==1 ) {
			        {
			        {
			        setState(1186); match(Comma);
			        setState(1188); type();
			        }
			        } 
			    }
				setState(1194);
				_errHandler.sync(this);
				_alt1754 = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class forStmtContext extends ParserRuleContext<Token> {
		public forStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final forStmtContext forStmt() throws RecognitionException {
		forStmtContext _localctx = new forStmtContext(_ctx, 172);
		enterRule(_localctx, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1195); match(For);
			setState(1203);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(1197); condition();
					}
					break;

				case 2:
					{
					setState(1199); forClause();
					}
					break;

				case 3:
					{
					setState(1201); rangeClause();
					}
					break;
			}
			setState(1205); block();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class conditionContext extends ParserRuleContext<Token> {
		public conditionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final conditionContext condition() throws RecognitionException {
		conditionContext _localctx = new conditionContext(_ctx, 174);
		enterRule(_localctx, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1207); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class forClauseContext extends ParserRuleContext<Token> {
		public forClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final forClauseContext forClause() throws RecognitionException {
		forClauseContext _localctx = new forClauseContext(_ctx, 176);
		enterRule(_localctx, RULE_forClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1211);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(1209); initStmt();
					}
					break;
			}
			setState(1213); match(Semi);
			setState(1217);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(1215); condition();
					}
					break;
			}
			setState(1219); match(Semi);
			setState(1223);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					{
					setState(1221); postStmt();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class initStmtContext extends ParserRuleContext<Token> {
		public initStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final initStmtContext initStmt() throws RecognitionException {
		initStmtContext _localctx = new initStmtContext(_ctx, 178);
		enterRule(_localctx, RULE_initStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1225); simpleStmt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class postStmtContext extends ParserRuleContext<Token> {
		public postStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final postStmtContext postStmt() throws RecognitionException {
		postStmtContext _localctx = new postStmtContext(_ctx, 180);
		enterRule(_localctx, RULE_postStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1227); simpleStmt();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rangeClauseContext extends ParserRuleContext<Token> {
		public rangeClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final rangeClauseContext rangeClause() throws RecognitionException {
		rangeClauseContext _localctx = new rangeClauseContext(_ctx, 182);
		enterRule(_localctx, RULE_rangeClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1229); expression(0);
			setState(1235);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(1231); match(Comma);
					setState(1233); expression(0);
					}
					break;
			}
			setState(1237);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Equal || _la==ColonEqual) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(1239); match(Range);
			setState(1241); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class goStmtContext extends ParserRuleContext<Token> {
		public goStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final goStmtContext goStmt() throws RecognitionException {
		goStmtContext _localctx = new goStmtContext(_ctx, 184);
		enterRule(_localctx, RULE_goStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1243); match(Go);
			setState(1245); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class selectStmtContext extends ParserRuleContext<Token> {
		public selectStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final selectStmtContext selectStmt() throws RecognitionException {
		selectStmtContext _localctx = new selectStmtContext(_ctx, 186);
		enterRule(_localctx, RULE_selectStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1247); match(Select);
			setState(1249); match(LeftBrace);
			setState(1255);
			_errHandler.sync(this);
			int _alt1870 = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt1870!=2 && _alt1870!=-1 ) {
			    if ( _alt1870==1 ) {
			        {
			        {
			        setState(1251); commClause();
			        }
			        } 
			    }
				setState(1257);
				_errHandler.sync(this);
				_alt1870 = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(1258); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class commClauseContext extends ParserRuleContext<Token> {
		public commClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final commClauseContext commClause() throws RecognitionException {
		commClauseContext _localctx = new commClauseContext(_ctx, 188);
		enterRule(_localctx, RULE_commClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1260); commCase();
			setState(1262); match(Colon);
			setState(1270);
			_errHandler.sync(this);
			int _alt1889 = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt1889!=2 && _alt1889!=-1 ) {
			    if ( _alt1889==1 ) {
			        {
			        {
			        setState(1264); statement();
			        setState(1266); match(Semi);
			        }
			        } 
			    }
				setState(1272);
				_errHandler.sync(this);
				_alt1889 = getInterpreter().adaptivePredict(_input,89,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class commCaseContext extends ParserRuleContext<Token> {
		public commCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final commCaseContext commCase() throws RecognitionException {
		commCaseContext _localctx = new commCaseContext(_ctx, 190);
		enterRule(_localctx, RULE_commCase);
		try {
			setState(1283);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1273); match(Case);
					setState(1279);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
						case 1:
							{
							setState(1275); sendStmt();
							}
							break;

						case 2:
							{
							setState(1277); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1281); match(Default);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class recvStmtContext extends ParserRuleContext<Token> {
		public recvStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final recvStmtContext recvStmt() throws RecognitionException {
		recvStmtContext _localctx = new recvStmtContext(_ctx, 192);
		enterRule(_localctx, RULE_recvStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1295);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(1285); expression(0);
					setState(1291);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
						case 1:
							{
							setState(1287); match(Comma);
							setState(1289); expression(0);
							}
							break;
					}
					setState(1293);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Equal || _la==ColonEqual) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					break;
			}
			setState(1297); recvExpr();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class recvExprContext extends ParserRuleContext<Token> {
		public recvExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final recvExprContext recvExpr() throws RecognitionException {
		recvExprContext _localctx = new recvExprContext(_ctx, 194);
		enterRule(_localctx, RULE_recvExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1299); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class returnStmtContext extends ParserRuleContext<Token> {
		public returnStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final returnStmtContext returnStmt() throws RecognitionException {
		returnStmtContext _localctx = new returnStmtContext(_ctx, 196);
		enterRule(_localctx, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1301); match(Return);
			setState(1305);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(1303); expressionList();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class breakStmtContext extends ParserRuleContext<Token> {
		public breakStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final breakStmtContext breakStmt() throws RecognitionException {
		breakStmtContext _localctx = new breakStmtContext(_ctx, 198);
		enterRule(_localctx, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1307); match(Break);
			setState(1311);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1309); label();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class continueStmtContext extends ParserRuleContext<Token> {
		public continueStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final continueStmtContext continueStmt() throws RecognitionException {
		continueStmtContext _localctx = new continueStmtContext(_ctx, 200);
		enterRule(_localctx, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1313); match(Continue);
			setState(1317);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(1315); label();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class gotoStmtContext extends ParserRuleContext<Token> {
		public gotoStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final gotoStmtContext gotoStmt() throws RecognitionException {
		gotoStmtContext _localctx = new gotoStmtContext(_ctx, 202);
		enterRule(_localctx, RULE_gotoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1319); match(Goto);
			setState(1321); label();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class fallthroughStmtContext extends ParserRuleContext<Token> {
		public fallthroughStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final fallthroughStmtContext fallthroughStmt() throws RecognitionException {
		fallthroughStmtContext _localctx = new fallthroughStmtContext(_ctx, 204);
		enterRule(_localctx, RULE_fallthroughStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1323); match(Fallthrough);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class deferStmtContext extends ParserRuleContext<Token> {
		public deferStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final deferStmtContext deferStmt() throws RecognitionException {
		deferStmtContext _localctx = new deferStmtContext(_ctx, 206);
		enterRule(_localctx, RULE_deferStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1325); match(Defer);
			setState(1327); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class builtinCallContext extends ParserRuleContext<Token> {
		public builtinCallContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final builtinCallContext builtinCall() throws RecognitionException {
		builtinCallContext _localctx = new builtinCallContext(_ctx, 208);
		enterRule(_localctx, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1329); match(IDENTIFIER);
			setState(1331); match(LeftParen);
			setState(1339);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(1333); builtinArgs();
					setState(1337);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
						case 1:
							{
							setState(1335); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1341); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class builtinArgsContext extends ParserRuleContext<Token> {
		public builtinArgsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final builtinArgsContext builtinArgs() throws RecognitionException {
		builtinArgsContext _localctx = new builtinArgsContext(_ctx, 210);
		enterRule(_localctx, RULE_builtinArgs);
		try {
			setState(1353);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1343); type();
					setState(1349);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
						case 1:
							{
							setState(1345); match(Comma);
							setState(1347); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1351); expressionList();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class sourceFileContext extends ParserRuleContext<Token> {
		public sourceFileContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final sourceFileContext sourceFile() throws RecognitionException {
		sourceFileContext _localctx = new sourceFileContext(_ctx, 212);
		enterRule(_localctx, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1355); packageClause();
			setState(1357); match(Semi);
			setState(1365);
			_errHandler.sync(this);
			int _alt2062 = getInterpreter().adaptivePredict(_input,101,_ctx);
			while ( _alt2062!=2 && _alt2062!=-1 ) {
			    if ( _alt2062==1 ) {
			        {
			        {
			        setState(1359); importDecl();
			        setState(1361); match(Semi);
			        }
			        } 
			    }
				setState(1367);
				_errHandler.sync(this);
				_alt2062 = getInterpreter().adaptivePredict(_input,101,_ctx);
			}
			setState(1374);
			_errHandler.sync(this);
			int _alt2069 = getInterpreter().adaptivePredict(_input,102,_ctx);
			while ( _alt2069!=2 && _alt2069!=-1 ) {
			    if ( _alt2069==1 ) {
			        {
			        {
			        setState(1368); topLevelDecl();
			        setState(1370); match(Semi);
			        }
			        } 
			    }
				setState(1376);
				_errHandler.sync(this);
				_alt2069 = getInterpreter().adaptivePredict(_input,102,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class packageClauseContext extends ParserRuleContext<Token> {
		public packageClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final packageClauseContext packageClause() throws RecognitionException {
		packageClauseContext _localctx = new packageClauseContext(_ctx, 214);
		enterRule(_localctx, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1377); match(Package);
			setState(1379); packageName();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class packageNameContext extends ParserRuleContext<Token> {
		public packageNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final packageNameContext packageName() throws RecognitionException {
		packageNameContext _localctx = new packageNameContext(_ctx, 216);
		enterRule(_localctx, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1381); match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class importDeclContext extends ParserRuleContext<Token> {
		public importDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final importDeclContext importDecl() throws RecognitionException {
		importDeclContext _localctx = new importDeclContext(_ctx, 218);
		enterRule(_localctx, RULE_importDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1383); match(Import);
			setState(1400);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1385); importSpec();
					}
					break;

				case 2:
					{
					setState(1387); match(LeftParen);
					setState(1395);
					_errHandler.sync(this);
					int _alt2109 = getInterpreter().adaptivePredict(_input,103,_ctx);
					while ( _alt2109!=2 && _alt2109!=-1 ) {
					    if ( _alt2109==1 ) {
					        {
					        {
					        setState(1389); importSpec();
					        setState(1391); match(Semi);
					        }
					        } 
					    }
						setState(1397);
						_errHandler.sync(this);
						_alt2109 = getInterpreter().adaptivePredict(_input,103,_ctx);
					}
					setState(1398); match(RightParen);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class importSpecContext extends ParserRuleContext<Token> {
		public importSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final importSpecContext importSpec() throws RecognitionException {
		importSpecContext _localctx = new importSpecContext(_ctx, 220);
		enterRule(_localctx, RULE_importSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1406);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(1402); match(Dot);
					}
					break;

				case 2:
					{
					setState(1404); packageName();
					}
					break;
			}
			setState(1408); importPath();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class importPathContext extends ParserRuleContext<Token> {
		public importPathContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}

	public final importPathContext importPath() throws RecognitionException {
		importPathContext _localctx = new importPathContext(_ctx, 222);
		enterRule(_localctx, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1410); match(StringLiteral);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@Override
	public String[] getTokenNames() { return tokenNames; }
	@Override
	public String[] getRuleNames() { return ruleNames; }
	@Override
	public ATN getATN() { return _ATN; }

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch ( ruleIndex ) {
				case 59 : return expression_sempred((expressionContext)_localctx, predIndex);

				case 60 : return primaryExpr_sempred((primaryExprContext)_localctx, predIndex);
		}
		return true;
	}
	public boolean expression_sempred(expressionContext _localctx, int predIndex) {
		switch ( predIndex ) {
			case 0 : return 5 >= _localctx._p;

			case 1 : return 4 >= _localctx._p;

			case 2 : return 3 >= _localctx._p;

			case 3 : return 2 >= _localctx._p;

			case 4 : return 1 >= _localctx._p;

			case 5 : return 17 >= _localctx._p;

			case 6 : return 16 >= _localctx._p;

			case 7 : return 15 >= _localctx._p;

			case 8 : return 14 >= _localctx._p;

			case 9 : return 13 >= _localctx._p;
		}
		return true;
	}
	public boolean primaryExpr_sempred(primaryExprContext _localctx, int predIndex) {
		switch ( predIndex ) {
			case 10 : return 5 >= _localctx._p;

			case 11 : return 4 >= _localctx._p;

			case 12 : return 3 >= _localctx._p;

			case 13 : return 2 >= _localctx._p;

			case 14 : return 1 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\1U\u0585\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7"+
		"O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7Y\2Z\7Z\2"+
		"[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2c\7c\2d\7d\2e\7e\2f"+
		"\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\2o\7o\1\0\0\1\0\0"+
		"\1\0\0\1\0\0\1\0\1\0\3\0\b\0\1\1\1\1\1\2\0\1\2\0\1\2\0\1\2\0\1\2\0\1\2"+
		"\0\1\2\0\1\2\0\3\2\b\2\1\3\0\1\3\0\1\3\0\1\3\1\3\1\4\1\4\1\5\1\5\1\6\0"+
		"\1\6\0\1\6\1\6\1\7\0\1\7\0\1\7\0\1\7\1\7\5\7\b\7\n\7\f\7\u011e\t\7\1\7"+
		"\1\7\1\b\0\1\b\1\b\1\b\0\3\b\b\b\1\b\0\3\b\b\b\1\t\0\3\t\b\t\1\t\1\t\1"+
		"\n\1\n\1\13\0\1\13\1\13\1\f\1\f\1\r\0\1\r\1\r\1\16\0\1\16\0\3\16\b\16"+
		"\1\17\0\1\17\0\3\17\b\17\1\20\0\1\20\0\1\20\0\3\20\b\20\3\20\b\20\1\20"+
		"\1\20\1\21\0\1\21\0\1\21\0\5\21\b\21\n\21\f\21\u0161\t\21\1\22\0\3\22"+
		"\b\22\1\22\0\3\22\b\22\1\22\1\22\1\23\0\1\23\0\1\23\0\1\23\1\23\5\23\b"+
		"\23\n\23\f\23\u0178\t\23\1\23\1\23\1\24\0\1\24\1\24\1\24\0\3\24\b\24\1"+
		"\25\1\25\1\26\1\26\1\27\0\1\27\0\1\27\0\1\27\0\1\27\1\27\1\30\1\30\1\31"+
		"\0\1\31\0\3\31\b\31\1\31\0\1\31\0\3\31\b\31\1\31\1\31\1\32\0\1\32\0\1"+
		"\32\1\32\5\32\b\32\n\32\f\32\u01ab\t\32\1\32\1\32\1\33\0\1\33\0\1\33\0"+
		"\3\33\b\33\1\34\0\1\34\0\1\34\0\3\34\b\34\1\35\0\1\35\0\1\35\0\1\35\0"+
		"\1\35\1\35\5\35\b\35\n\35\f\35\u01cc\t\35\1\35\0\3\35\b\35\1\36\0\1\36"+
		"\0\3\36\b\36\1\36\0\1\36\0\3\36\b\36\1\37\0\1\37\0\1\37\0\5\37\b\37\n"+
		"\37\f\37\u01e7\t\37\1 \0\1 \0\1 \0\5 \b \n \f \u01f2\t \1!\0\1!\0\1!\0"+
		"\1!\0\1!\1!\5!\b!\n!\f!\u0201\t!\1!\0\3!\b!\1\"\0\1\"\1\"\1#\0\1#\0\1"+
		"#\0\1#\0\1#\1#\5#\b#\n#\f#\u0218\t#\1#\0\3#\b#\1$\0\1$\0\1$\0\1$\0\3$"+
		"\b$\1$\0\1$\0\3$\b$\1%\0\1%\0\1%\1%\1&\0\1&\0\1&\0\1&\0\3&\b&\1\'\1\'"+
		"\1(\0\1(\0\1(\0\1(\0\1(\0\3(\b(\1)\0\1)\0\3)\b)\1)\0\3)\b)\1)\0\1)\1)"+
		"\1*\1*\1+\0\1+\0\1+\0\1+\0\1+\0\1+\1+\3+\b+\1,\0\1,\0\1,\0\3,\b,\1-\0"+
		"\1-\0\1-\0\1-\0\1-\0\3-\b-\1.\0\1.\1.\3.\b.\1.\1.\1/\0\1/\0\1/\1/\1\60"+
		"\0\1\60\0\1\60\0\1\60\0\1\60\1\60\3\60\b\60\1\61\0\1\61\1\61\1\62\0\1"+
		"\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\3\62\b\62\1\63"+
		"\0\1\63\0\1\63\0\3\63\b\63\3\63\b\63\1\63\1\63\1\64\0\1\64\0\1\64\0\5"+
		"\64\b\64\n\64\f\64\u02c5\t\64\1\65\0\1\65\1\65\3\65\b\65\1\65\1\65\1\66"+
		"\0\1\66\0\3\66\b\66\1\67\1\67\18\18\19\0\19\0\39\b9\1:\0\1:\1:\1;\0\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1"+
		";\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0"+
		"\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1;\0\1;"+
		"\0\3;\b;\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1;\0\1;\0"+
		"\1;\0\3;\b;\3;\b;\1;\0\5;\b;\n;\f;\u035e\t;\1<\0\1<\0\1<\0\3<\b<\1<\0"+
		"\1<\0\1<\0\1<\0\1<\0\1<\0\1<\1<\1<\0\1<\0\1<\0\3<\b<\1<\0\1<\0\3<\b<\1"+
		"<\0\1<\0\1<\0\1<\0\1<\0\1<\1<\1<\0\1<\0\1<\0\1<\0\3<\b<\3<\b<\1<\0\5<"+
		"\b<\n<\f<\u03a1\t<\1=\0\1=\0\3=\b=\1>\0\1>\0\1>\0\1>\1>\1?\0\1?\0\1?\0"+
		"\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1@"+
		"\0\1@\0\1@\0\1@\0\1@\0\1@\0\3@\b@\1A\1A\1B\0\1B\0\1B\1B\1C\1C\1D\1D\1"+
		"E\0\1E\0\1E\1E\1F\1F\1G\0\1G\1G\1H\0\1H\0\1H\1H\1I\0\1I\0\1I\0\3I\bI\1"+
		"J\0\1J\0\1J\0\1J\0\3J\bJ\1K\0\1K\0\1K\0\1K\0\1K\0\1K\0\1K\0\3K\bK\1L\0"+
		"\1L\0\1L\1L\3L\bL\1L\0\1L\0\1L\0\1L\0\1L\0\3L\bL\3L\bL\1M\0\1M\0\3M\b"+
		"M\1N\0\1N\0\1N\1N\3N\bN\1N\0\3N\bN\1N\0\1N\0\5N\bN\nN\fN\u044e\tN\1N\1"+
		"N\1O\0\1O\0\1O\0\1O\1O\5O\bO\nO\fO\u045d\tO\1P\0\1P\0\1P\0\3P\bP\1Q\0"+
		"\1Q\0\1Q\1Q\3Q\bQ\1Q\0\1Q\0\1Q\0\5Q\bQ\nQ\fQ\u0478\tQ\1Q\1Q\1R\0\1R\0"+
		"\3R\bR\1R\0\1R\0\1R\0\1R\0\1R\1R\1S\0\1S\0\1S\0\1S\1S\5S\bS\nS\fS\u0497"+
		"\tS\1T\0\1T\0\1T\0\3T\bT\1U\0\1U\0\1U\0\5U\bU\nU\fU\u04aa\tU\1V\0\1V\0"+
		"\1V\0\1V\0\3V\bV\1V\1V\1W\1W\1X\0\3X\bX\1X\0\1X\0\3X\bX\1X\0\1X\0\3X\b"+
		"X\1Y\1Y\1Z\1Z\1[\0\1[\0\1[\0\3[\b[\1[\0\1[\0\1[\1[\1\\\0\1\\\1\\\1]\0"+
		"\1]\0\1]\0\5]\b]\n]\f]\u04e9\t]\1]\1]\1^\0\1^\0\1^\0\1^\1^\5^\b^\n^\f"+
		"^\u04f8\t^\1_\0\1_\0\1_\0\3_\b_\1_\0\3_\b_\1`\0\1`\0\1`\0\3`\b`\1`\1`"+
		"\3`\b`\1`\1`\1a\1a\1b\0\1b\0\3b\bb\1c\0\1c\0\3c\bc\1d\0\1d\0\3d\bd\1e"+
		"\0\1e\1e\1f\1f\1g\0\1g\1g\1h\0\1h\0\1h\0\1h\0\3h\bh\3h\bh\1h\1h\1i\0\1"+
		"i\0\1i\0\3i\bi\1i\0\3i\bi\1j\0\1j\0\1j\0\1j\1j\5j\bj\nj\fj\u0557\tj\1"+
		"j\0\1j\1j\5j\bj\nj\fj\u0560\tj\1k\0\1k\1k\1l\1l\1m\0\1m\0\1m\0\1m\0\1"+
		"m\1m\5m\bm\nm\fm\u0575\tm\1m\0\3m\bm\1n\0\1n\0\3n\bn\1n\1n\1o\1o\1op\0"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFH"+
		"JLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4"+
		"\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc"+
		"\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4"+
		"\u00d6\u00d8\u00da\u00dc\u00de\0\6\2\36!$&\2\34\35\"#\2\679<>\1\65\66"+
		"\2::??\2::??\u053d\0\u00ea\1\0\0\0\1\u00e8\1\0\0\0\1\u010b\1\0\0\0\1\u0124"+
		"\1\0\0\0\1\u013a\1\0\0\0\1\u014a\1\0\0\0\1\u016b\1\0\0\0\1\u0192\1\0\0"+
		"\0\1\u01d6\1\0\0\0\1\u0209\1\0\0\0\1\u0225\1\0\0\0\1\u034a\1\0\0\0\1\u038d"+
		"\1\0\0\0\1\u03aa\1\0\0\0\1\u04a8\1\0\0\0\1\u04a7\1\0\0\0\1\u0545\1\0\0"+
		"\0\2\u00ec\1\0\0\0\3\u00eb\1\0\0\0\3\u0132\1\0\0\0\3\u0186\1\0\0\0\3\u0296"+
		"\1\0\0\0\3\u0293\1\0\0\0\3\u02ae\1\0\0\0\4\u00fe\1\0\0\0\5\u00eb\1\0\0"+
		"\0\6\u0100\1\0\0\0\7\u00ff\1\0\0\0\7\u02ae\1\0\0\0\b\u0108\1\0\0\0\t\u0104"+
		"\1\0\0\0\n\u010a\1\0\0\0\13\u0107\1\0\0\0\13\u0111\1\0\0\0\13\u0190\1"+
		"\0\0\0\13\u01a0\1\0\0\0\13\u02ae\1\0\0\0\f\u010c\1\0\0\0\r\u00ff\1\0\0"+
		"\0\r\u02ae\1\0\0\0\16\u0112\1\0\0\0\17\u00ff\1\0\0\0\17\u02ae\1\0\0\0"+
		"\20\u0127\1\0\0\0\21\u0118\1\0\0\0\22\u012f\1\0\0\0\23\u0128\1\0\0\0\24"+
		"\u0133\1\0\0\0\25\u012c\1\0\0\0\26\u0135\1\0\0\0\27\u00ff\1\0\0\0\30\u0139"+
		"\1\0\0\0\31\u0138\1\0\0\0\32\u013b\1\0\0\0\33\u00ff\1\0\0\0\33\u02e0\1"+
		"\0\0\0\34\u013f\1\0\0\0\35\u013e\1\0\0\0\35\u017e\1\0\0\0\35\u023b\1\0"+
		"\0\0\35\u0249\1\0\0\0\36\u0149\1\0\0\0\37\u0144\1\0\0\0 \u014b\1\0\0\0"+
		"!\u0143\1\0\0\0!\u014a\1\0\0\0\"\u0157\1\0\0\0#\u0151\1\0\0\0$\u0164\1"+
		"\0\0\0%\u015f\1\0\0\0%\u015e\1\0\0\0&\u016c\1\0\0\0\'\u00ff\1\0\0\0(\u0181"+
		"\1\0\0\0)\u0172\1\0\0\0*\u0183\1\0\0\0+\u017d\1\0\0\0+\u0245\1\0\0\0+"+
		"\u028a\1\0\0\0,\u0185\1\0\0\0-\u0182\1\0\0\0.\u0187\1\0\0\0/\u00ff\1\0"+
		"\0\0/\u02ae\1\0\0\0\60\u0191\1\0\0\0\61\u018d\1\0\0\0\62\u019d\1\0\0\0"+
		"\63\u00ff\1\0\0\0\64\u01a1\1\0\0\0\65\u023e\1\0\0\0\65\u03cf\1\0\0\0\65"+
		"\u0432\1\0\0\0\65\u0431\1\0\0\0\65\u04b6\1\0\0\0\66\u01b4\1\0\0\0\67\u01bd"+
		"\1\0\0\0\67\u03cf\1\0\0\08\u01bc\1\0\0\09\u055a\1\0\0\0:\u01be\1\0\0\0"+
		";\u01b5\1\0\0\0<\u01d1\1\0\0\0=\u01d0\1\0\0\0=\u01c6\1\0\0\0>\u01dd\1"+
		"\0\0\0?\u0123\1\0\0\0?\u0165\1\0\0\0?\u01db\1\0\0\0?\u022b\1\0\0\0?\u022f"+
		"\1\0\0\0@\u01e8\1\0\0\0A\u01dc\1\0\0\0A\u0226\1\0\0\0A\u022c\1\0\0\0A"+
		"\u0232\1\0\0\0A\u03a6\1\0\0\0A\u03f8\1\0\0\0A\u03fb\1\0\0\0A\u0465\1\0"+
		"\0\0A\u051a\1\0\0\0A\u0546\1\0\0\0A\u054a\1\0\0\0B\u01f3\1\0\0\0C\u01b5"+
		"\1\0\0\0D\u0206\1\0\0\0E\u0205\1\0\0\0E\u01fb\1\0\0\0F\u020a\1\0\0\0G"+
		"\u01b5\1\0\0\0H\u021d\1\0\0\0I\u021c\1\0\0\0I\u0212\1\0\0\0J\u022d\1\0"+
		"\0\0K\u03dd\1\0\0\0L\u0233\1\0\0\0M\u01bd\1\0\0\0N\u023d\1\0\0\0O\u023c"+
		"\1\0\0\0O\u024a\1\0\0\0O\u02e1\1\0\0\0P\u023f\1\0\0\0Q\u01bd\1\0\0\0R"+
		"\u024b\1\0\0\0S\u0243\1\0\0\0T\u0259\1\0\0\0U\u0257\1\0\0\0V\u0267\1\0"+
		"\0\0W\u0305\1\0\0\0W\u0366\1\0\0\0X\u026f\1\0\0\0Y\u0268\1\0\0\0Z\u027b"+
		"\1\0\0\0[\u0270\1\0\0\0\\\u0281\1\0\0\0]\u00ed\1\0\0\0]\u0268\1\0\0\0"+
		"^\u0285\1\0\0\0_\u0268\1\0\0\0`\u0295\1\0\0\0a\u0287\1\0\0\0b\u0297\1"+
		"\0\0\0c\u0270\1\0\0\0d\u02ad\1\0\0\0e\u0299\1\0\0\0f\u02af\1\0\0\0g\u029a"+
		"\1\0\0\0g\u02dd\1\0\0\0h\u02bb\1\0\0\0i\u02b5\1\0\0\0j\u02ca\1\0\0\0k"+
		"\u02c3\1\0\0\0k\u02c2\1\0\0\0l\u02d2\1\0\0\0m\u02c8\1\0\0\0n\u02d4\1\0"+
		"\0\0o\u02d3\1\0\0\0p\u02d6\1\0\0\0q\u02d3\1\0\0\0r\u02dc\1\0\0\0s\u02cd"+
		"\1\0\0\0t\u02de\1\0\0\0u\u0270\1\0\0\0v\u0304\1\0\0\0w\u0109\1\0\0\0w"+
		"\u01f0\1\0\0\0w\u01ef\1\0\0\0w\u0265\1\0\0\0w\u02d7\1\0\0\0w\u02dd\1\0"+
		"\0\0w\u0305\1\0\0\0w\u0305\1\0\0\0w\u0305\1\0\0\0w\u0305\1\0\0\0w\u0305"+
		"\1\0\0\0w\u0305\1\0\0\0w\u0305\1\0\0\0w\u035b\1\0\0\0w\u035b\1\0\0\0w"+
		"\u035b\1\0\0\0w\u035b\1\0\0\0w\u035b\1\0\0\0w\u0330\1\0\0\0w\u0339\1\0"+
		"\0\0w\u033f\1\0\0\0w\u0373\1\0\0\0w\u037c\1\0\0\0w\u0382\1\0\0\0w\u03ae"+
		"\1\0\0\0w\u03e9\1\0\0\0w\u03ef\1\0\0\0w\u03f1\1\0\0\0w\u03f4\1\0\0\0w"+
		"\u0428\1\0\0\0w\u0445\1\0\0\0w\u04b8\1\0\0\0w\u04d3\1\0\0\0w\u04d4\1\0"+
		"\0\0w\u04da\1\0\0\0w\u04de\1\0\0\0w\u050b\1\0\0\0w\u050c\1\0\0\0w\u0514"+
		"\1\0\0\0w\u0530\1\0\0\0x\u0365\1\0\0\0y\u0483\1\0\0\0z\u03a2\1\0\0\0{"+
		"\u0354\1\0\0\0{\u0397\1\0\0\0|\u03a8\1\0\0\0}\u0305\1\0\0\0}\u0366\1\0"+
		"\0\0~\u03ce\1\0\0\0\177\u01a5\1\0\0\0\177\u03e5\1\0\0\0\177\u0457\1\0"+
		"\0\0\177\u0491\1\0\0\0\177\u04f2\1\0\0\0\u0080\u03dc\1\0\0\0\u0081\u03cf"+
		"\1\0\0\0\u0081\u0422\1\0\0\0\u0081\u043e\1\0\0\0\u0081\u046a\1\0\0\0\u0081"+
		"\u04ca\1\0\0\0\u0081\u04cc\1\0\0\0\u0082\u03de\1\0\0\0\u0083\u03dd\1\0"+
		"\0\0\u0084\u03e0\1\0\0\0\u0085\u03cf\1\0\0\0\u0086\u03e6\1\0\0\0\u0087"+
		"\u03e2\1\0\0\0\u0087\u0520\1\0\0\0\u0087\u0526\1\0\0\0\u0087\u052a\1\0"+
		"\0\0\u0088\u03e8\1\0\0\0\u0089\u03dd\1\0\0\0\u008a\u03ea\1\0\0\0\u008b"+
		"\u03dd\1\0\0\0\u008b\u0500\1\0\0\0\u008c\u03f0\1\0\0\0\u008d\u03ec\1\0"+
		"\0\0\u008e\u03f2\1\0\0\0\u008f\u03dd\1\0\0\0\u0090\u03f6\1\0\0\0\u0091"+
		"\u03dd\1\0\0\0\u0092\u0402\1\0\0\0\u0093\u03fa\1\0\0\0\u0094\u040c\1\0"+
		"\0\0\u0095\u0403\1\0\0\0\u0096\u041c\1\0\0\0\u0097\u0403\1\0\0\0\u0098"+
		"\u041e\1\0\0\0\u0099\u03cf\1\0\0\0\u0099\u0431\1\0\0\0\u009a\u0438\1\0"+
		"\0\0\u009b\u03cf\1\0\0\0\u009c\u043a\1\0\0\0\u009d\u0439\1\0\0\0\u009e"+
		"\u0451\1\0\0\0\u009f\u044b\1\0\0\0\u00a0\u0464\1\0\0\0\u00a1\u0453\1\0"+
		"\0\0\u00a2\u0466\1\0\0\0\u00a3\u0439\1\0\0\0\u00a4\u047f\1\0\0\0\u00a5"+
		"\u0470\1\0\0\0\u00a6\u048b\1\0\0\0\u00a7\u0475\1\0\0\0\u00a8\u049e\1\0"+
		"\0\0\u00a9\u048d\1\0\0\0\u00aa\u04a0\1\0\0\0\u00ab\u049f\1\0\0\0\u00ac"+
		"\u04ab\1\0\0\0\u00ad\u03cf\1\0\0\0\u00ae\u04b7\1\0\0\0\u00af\u04b4\1\0"+
		"\0\0\u00af\u04c2\1\0\0\0\u00b0\u04bb\1\0\0\0\u00b1\u04b4\1\0\0\0\u00b2"+
		"\u04c9\1\0\0\0\u00b3\u04bc\1\0\0\0\u00b4\u04cb\1\0\0\0\u00b5\u04c8\1\0"+
		"\0\0\u00b6\u04cd\1\0\0\0\u00b7\u04b4\1\0\0\0\u00b8\u04db\1\0\0\0\u00b9"+
		"\u03cf\1\0\0\0\u00ba\u04df\1\0\0\0\u00bb\u03cf\1\0\0\0\u00bc\u04ec\1\0"+
		"\0\0\u00bd\u04e6\1\0\0\0\u00be\u0503\1\0\0\0\u00bf\u04ee\1\0\0\0\u00c0"+
		"\u050f\1\0\0\0\u00c1\u0500\1\0\0\0\u00c2\u0513\1\0\0\0\u00c3\u0512\1\0"+
		"\0\0\u00c4\u0515\1\0\0\0\u00c5\u03cf\1\0\0\0\u00c6\u051b\1\0\0\0\u00c7"+
		"\u03cf\1\0\0\0\u00c8\u0521\1\0\0\0\u00c9\u03cf\1\0\0\0\u00ca\u0527\1\0"+
		"\0\0\u00cb\u03cf\1\0\0\0\u00cc\u052b\1\0\0\0\u00cd\u03cf\1\0\0\0\u00ce"+
		"\u052d\1\0\0\0\u00cf\u03cf\1\0\0\0\u00d0\u0531\1\0\0\0\u00d1\u0305\1\0"+
		"\0\0\u00d1\u0366\1\0\0\0\u00d2\u0549\1\0\0\0\u00d3\u0539\1\0\0\0\u00d4"+
		"\u054b\1\0\0\0\u00d5\u0584\5\uffff\0\0\u00d6\u0561\1\0\0\0\u00d7\u054d"+
		"\1\0\0\0\u00d8\u0565\1\0\0\0\u00d9\u027f\1\0\0\0\u00d9\u0564\1\0\0\0\u00d9"+
		"\u057f\1\0\0\0\u00da\u0567\1\0\0\0\u00db\u0551\1\0\0\0\u00dc\u057e\1\0"+
		"\0\0\u00dd\u0579\1\0\0\0\u00dd\u056f\1\0\0\0\u00de\u0582\1\0\0\0\u00df"+
		"\u0581\1\0\0\0\u00e0\u00eb\3\2\1\0\u00e2\u00eb\3\4\2\0\u00e4\u00e6\5A"+
		"\0\0\u00e6\u00e8\3\0\0\0\u00e8\u00e9\5B\0\0\u00e9\u00eb\1\0\0\0\u00ea"+
		"\u00e0\1\0\0\0\u00ea\u00e2\1\0\0\0\u00ea\u00e4\1\0\0\0\u00eb\1\1\0\0\0"+
		"\u00ec\u00ed\3\\.\0\u00ed\3\1\0\0\0\u00ee\u00ff\3\6\3\0\u00f0\u00ff\3"+
		"\16\7\0\u00f2\u00ff\3\26\13\0\u00f4\u00ff\3\32\r\0\u00f6\u00ff\3&\23\0"+
		"\u00f8\u00ff\3\f\6\0\u00fa\u00ff\3.\27\0\u00fc\u00ff\3\62\31\0\u00fe\u00ee"+
		"\1\0\0\0\u00fe\u00f0\1\0\0\0\u00fe\u00f2\1\0\0\0\u00fe\u00f4\1\0\0\0\u00fe"+
		"\u00f6\1\0\0\0\u00fe\u00f8\1\0\0\0\u00fe\u00fa\1\0\0\0\u00fe\u00fc\1\0"+
		"\0\0\u00ff\5\1\0\0\0\u0100\u0102\5C\0\0\u0102\u0104\3\b\4\0\u0104\u0106"+
		"\5D\0\0\u0106\u0107\3\n\5\0\u0107\7\1\0\0\0\u0108\u0109\3v;\0\u0109\t"+
		"\1\0\0\0\u010a\u010b\3\0\0\0\u010b\13\1\0\0\0\u010c\u010e\5C\0\0\u010e"+
		"\u0110\5D\0\0\u0110\u0111\3\n\5\0\u0111\r\1\0\0\0\u0112\u0114\5\30\0\0"+
		"\u0114\u011c\5E\0\0\u0116\u0118\3\20\b\0\u0118\u0119\5I\0\0\u0119\u011b"+
		"\1\0\0\0\u011a\u0116\1\0\0\0\u011b\u011e\1\0\0\0\u011c\u011a\1\0\0\0\u011c"+
		"\u011d\1\0\0\0\u011d\u011f\1\0\0\0\u011e\u011c\1\0\0\0\u011f\u0120\5F"+
		"\0\0\u0120\17\1\0\0\0\u0121\u0123\3>\37\0\u0123\u0124\3\0\0\0\u0124\u0128"+
		"\1\0\0\0\u0125\u0128\3\22\t\0\u0127\u0121\1\0\0\0\u0127\u0125\1\0\0\0"+
		"\u0128\u012b\1\0\0\0\u0129\u012c\3\24\n\0\u012b\u0129\1\0\0\0\u012b\u012c"+
		"\1\0\0\0\u012c\21\1\0\0\0\u012d\u0130\5\36\0\0\u012f\u012d\1\0\0\0\u012f"+
		"\u0130\1\0\0\0\u0130\u0131\1\0\0\0\u0131\u0132\3\2\1\0\u0132\23\1\0\0"+
		"\0\u0133\u0134\5T\0\0\u0134\25\1\0\0\0\u0135\u0137\5\36\0\0\u0137\u0138"+
		"\3\30\f\0\u0138\27\1\0\0\0\u0139\u013a\3\0\0\0\u013a\31\1\0\0\0\u013b"+
		"\u013d\5\r\0\0\u013d\u013e\3\34\16\0\u013e\33\1\0\0\0\u013f\u0143\3 \20"+
		"\0\u0141\u0144\3\36\17\0\u0143\u0141\1\0\0\0\u0143\u0144\1\0\0\0\u0144"+
		"\35\1\0\0\0\u0145\u014a\3 \20\0\u0147\u014a\3\0\0\0\u0149\u0145\1\0\0"+
		"\0\u0149\u0147\1\0\0\0\u014a\37\1\0\0\0\u014b\u0153\5A\0\0\u014d\u0151"+
		"\3\"\21\0\u014f\u0152\5G\0\0\u0151\u014f\1\0\0\0\u0151\u0152\1\0\0\0\u0152"+
		"\u0154\1\0\0\0\u0153\u014d\1\0\0\0\u0153\u0154\1\0\0\0\u0154\u0155\1\0"+
		"\0\0\u0155\u0156\5B\0\0\u0156!\1\0\0\0\u0157\u015f\3$\22\0\u0159\u015b"+
		"\5G\0\0\u015b\u015e\3$\22\0\u015d\u0159\1\0\0\0\u015e\u0161\1\0\0\0\u015f"+
		"\u015d\1\0\0\0\u015f\u0160\1\0\0\0\u0160#\1\0\0\0\u0161\u015f\1\0\0\0"+
		"\u0162\u0165\3>\37\0\u0164\u0162\1\0\0\0\u0164\u0165\1\0\0\0\u0165\u0168"+
		"\1\0\0\0\u0166\u0169\5@\0\0\u0168\u0166\1\0\0\0\u0168\u0169\1\0\0\0\u0169"+
		"\u016a\1\0\0\0\u016a\u016b\3\0\0\0\u016b%\1\0\0\0\u016c\u016e\5\22\0\0"+
		"\u016e\u0176\5E\0\0\u0170\u0172\3(\24\0\u0172\u0173\5I\0\0\u0173\u0175"+
		"\1\0\0\0\u0174\u0170\1\0\0\0\u0175\u0178\1\0\0\0\u0176\u0174\1\0\0\0\u0176"+
		"\u0177\1\0\0\0\u0177\u0179\1\0\0\0\u0178\u0176\1\0\0\0\u0179\u017a\5F"+
		"\0\0\u017a\'\1\0\0\0\u017b\u017d\3*\25\0\u017d\u017e\3\34\16\0\u017e\u0182"+
		"\1\0\0\0\u017f\u0182\3,\26\0\u0181\u017b\1\0\0\0\u0181\u017f\1\0\0\0\u0182"+
		")\1\0\0\0\u0183\u0184\5K\0\0\u0184+\1\0\0\0\u0185\u0186\3\2\1\0\u0186"+
		"-\1\0\0\0\u0187\u0189\5\23\0\0\u0189\u018b\5C\0\0\u018b\u018d\3\60\30"+
		"\0\u018d\u018f\5D\0\0\u018f\u0190\3\n\5\0\u0190/\1\0\0\0\u0191\u0192\3"+
		"\0\0\0\u0192\61\1\0\0\0\u0193\u0197\5\5\0\0\u0195\u0198\5\64\0\0\u0197"+
		"\u0195\1\0\0\0\u0197\u0198\1\0\0\0\u0198\u019e\1\0\0\0\u0199\u019b\5\64"+
		"\0\0\u019b\u019e\5\5\0\0\u019d\u0193\1\0\0\0\u019d\u0199\1\0\0\0\u019e"+
		"\u019f\1\0\0\0\u019f\u01a0\3\n\5\0\u01a0\63\1\0\0\0\u01a1\u01a9\5E\0\0"+
		"\u01a3\u01a5\3~?\0\u01a5\u01a6\5I\0\0\u01a6\u01a8\1\0\0\0\u01a7\u01a3"+
		"\1\0\0\0\u01a8\u01ab\1\0\0\0\u01a9\u01a7\1\0\0\0\u01a9\u01aa\1\0\0\0\u01aa"+
		"\u01ac\1\0\0\0\u01ab\u01a9\1\0\0\0\u01ac\u01ad\5F\0\0\u01ad\65\1\0\0\0"+
		"\u01ae\u01b5\3:\35\0\u01b0\u01b5\3B!\0\u01b2\u01b5\3F#\0\u01b4\u01ae\1"+
		"\0\0\0\u01b4\u01b0\1\0\0\0\u01b4\u01b2\1\0\0\0\u01b5\67\1\0\0\0\u01b6"+
		"\u01bd\3\66\33\0\u01b8\u01bd\3L&\0\u01ba\u01bd\3P(\0\u01bc\u01b6\1\0\0"+
		"\0\u01bc\u01b8\1\0\0\0\u01bc\u01ba\1\0\0\0\u01bd9\1\0\0\0\u01be\u01cf"+
		"\5\6\0\0\u01c0\u01d0\3<\36\0\u01c2\u01ca\5A\0\0\u01c4\u01c6\3<\36\0\u01c6"+
		"\u01c7\5I\0\0\u01c7\u01c9\1\0\0\0\u01c8\u01c4\1\0\0\0\u01c9\u01cc\1\0"+
		"\0\0\u01ca\u01c8\1\0\0\0\u01ca\u01cb\1\0\0\0\u01cb\u01cd\1\0\0\0\u01cc"+
		"\u01ca\1\0\0\0\u01cd\u01d0\5B\0\0\u01cf\u01c0\1\0\0\0\u01cf\u01c2\1\0"+
		"\0\0\u01d0;\1\0\0\0\u01d1\u01db\3>\37\0\u01d3\u01d6\3\0\0\0\u01d5\u01d3"+
		"\1\0\0\0\u01d5\u01d6\1\0\0\0\u01d6\u01d7\1\0\0\0\u01d7\u01d9\5:\0\0\u01d9"+
		"\u01dc\3@ \0\u01db\u01d5\1\0\0\0\u01db\u01dc\1\0\0\0\u01dc=\1\0\0\0\u01dd"+
		"\u01e5\5K\0\0\u01df\u01e1\5G\0\0\u01e1\u01e4\5K\0\0\u01e3\u01df\1\0\0"+
		"\0\u01e4\u01e7\1\0\0\0\u01e5\u01e3\1\0\0\0\u01e5\u01e6\1\0\0\0\u01e6?"+
		"\1\0\0\0\u01e7\u01e5\1\0\0\0\u01e8\u01f0\3v;\0\u01ea\u01ec\5G\0\0\u01ec"+
		"\u01ef\3v;\0\u01ee\u01ea\1\0\0\0\u01ef\u01f2\1\0\0\0\u01f0\u01ee\1\0\0"+
		"\0\u01f0\u01f1\1\0\0\0\u01f1A\1\0\0\0\u01f2\u01f0\1\0\0\0\u01f3\u0204"+
		"\5\32\0\0\u01f5\u0205\3D\"\0\u01f7\u01ff\5A\0\0\u01f9\u01fb\3D\"\0\u01fb"+
		"\u01fc\5I\0\0\u01fc\u01fe\1\0\0\0\u01fd\u01f9\1\0\0\0\u01fe\u0201\1\0"+
		"\0\0\u01ff\u01fd\1\0\0\0\u01ff\u0200\1\0\0\0\u0200\u0202\1\0\0\0\u0201"+
		"\u01ff\1\0\0\0\u0202\u0205\5B\0\0\u0204\u01f5\1\0\0\0\u0204\u01f7\1\0"+
		"\0\0\u0205C\1\0\0\0\u0206\u0208\5K\0\0\u0208\u0209\3\0\0\0\u0209E\1\0"+
		"\0\0\u020a\u021b\5\33\0\0\u020c\u021c\3H$\0\u020e\u0216\5A\0\0\u0210\u0212"+
		"\3H$\0\u0212\u0213\5I\0\0\u0213\u0215\1\0\0\0\u0214\u0210\1\0\0\0\u0215"+
		"\u0218\1\0\0\0\u0216\u0214\1\0\0\0\u0216\u0217\1\0\0\0\u0217\u0219\1\0"+
		"\0\0\u0218\u0216\1\0\0\0\u0219\u021c\5B\0\0\u021b\u020c\1\0\0\0\u021b"+
		"\u020e\1\0\0\0\u021cG\1\0\0\0\u021d\u022b\3>\37\0\u021f\u0225\3\0\0\0"+
		"\u0221\u0223\5:\0\0\u0223\u0226\3@ \0\u0225\u0221\1\0\0\0\u0225\u0226"+
		"\1\0\0\0\u0226\u022c\1\0\0\0\u0227\u0229\5:\0\0\u0229\u022c\3@ \0\u022b"+
		"\u021f\1\0\0\0\u022b\u0227\1\0\0\0\u022cI\1\0\0\0\u022d\u022f\3>\37\0"+
		"\u022f\u0231\5?\0\0\u0231\u0232\3@ \0\u0232K\1\0\0\0\u0233\u0235\5\r\0"+
		"\0\u0235\u0237\5K\0\0\u0237\u023b\3\34\16\0\u0239\u023c\3N\'\0\u023b\u0239"+
		"\1\0\0\0\u023b\u023c\1\0\0\0\u023cM\1\0\0\0\u023d\u023e\3\64\32\0\u023e"+
		"O\1\0\0\0\u023f\u0241\5\r\0\0\u0241\u0243\3R)\0\u0243\u0245\3*\25\0\u0245"+
		"\u0249\3\34\16\0\u0247\u024a\3N\'\0\u0249\u0247\1\0\0\0\u0249\u024a\1"+
		"\0\0\0\u024aQ\1\0\0\0\u024b\u024f\5A\0\0\u024d\u0250\5K\0\0\u024f\u024d"+
		"\1\0\0\0\u024f\u0250\1\0\0\0\u0250\u0253\1\0\0\0\u0251\u0254\5\36\0\0"+
		"\u0253\u0251\1\0\0\0\u0253\u0254\1\0\0\0\u0254\u0255\1\0\0\0\u0255\u0257"+
		"\3T*\0\u0257\u0258\5B\0\0\u0258S\1\0\0\0\u0259\u025a\5K\0\0\u025aU\1\0"+
		"\0\0\u025b\u0268\3X,\0\u025d\u0268\3\\.\0\u025f\u0268\3^/\0\u0261\u0263"+
		"\5A\0\0\u0263\u0265\3v;\0\u0265\u0266\5B\0\0\u0266\u0268\1\0\0\0\u0267"+
		"\u025b\1\0\0\0\u0267\u025d\1\0\0\0\u0267\u025f\1\0\0\0\u0267\u0261\1\0"+
		"\0\0\u0268W\1\0\0\0\u0269\u0270\3Z-\0\u026b\u0270\3b\61\0\u026d\u0270"+
		"\3t:\0\u026f\u0269\1\0\0\0\u026f\u026b\1\0\0\0\u026f\u026d\1\0\0\0\u0270"+
		"Y\1\0\0\0\u0271\u027c\5P\0\0\u0273\u027c\5R\0\0\u0275\u027c\5Q\0\0\u0277"+
		"\u027c\5S\0\0\u0279\u027c\5T\0\0\u027b\u0271\1\0\0\0\u027b\u0273\1\0\0"+
		"\0\u027b\u0275\1\0\0\0\u027b\u0277\1\0\0\0\u027b\u0279\1\0\0\0\u027c["+
		"\1\0\0\0\u027d\u027f\3\u00d8l\0\u027f\u0280\5H\0\0\u0280\u0282\1\0\0\0"+
		"\u0281\u027d\1\0\0\0\u0281\u0282\1\0\0\0\u0282\u0283\1\0\0\0\u0283\u0284"+
		"\5K\0\0\u0284]\1\0\0\0\u0285\u0287\3`\60\0\u0287\u0289\5H\0\0\u0289\u028a"+
		"\3*\25\0\u028a_\1\0\0\0\u028b\u0296\3\2\1\0\u028d\u028f\5A\0\0\u028f\u0291"+
		"\5\36\0\0\u0291\u0293\3\2\1\0\u0293\u0294\5B\0\0\u0294\u0296\1\0\0\0\u0295"+
		"\u028b\1\0\0\0\u0295\u028d\1\0\0\0\u0296a\1\0\0\0\u0297\u0299\3d\62\0"+
		"\u0299\u029a\3f\63\0\u029ac\1\0\0\0\u029b\u02ae\3\16\7\0\u029d\u02ae\3"+
		"\6\3\0\u029f\u02a1\5C\0\0\u02a1\u02a3\5@\0\0\u02a3\u02a5\5D\0\0\u02a5"+
		"\u02ae\3\n\5\0\u02a7\u02ae\3\f\6\0\u02a9\u02ae\3.\27\0\u02ab\u02ae\3\2"+
		"\1\0\u02ad\u029b\1\0\0\0\u02ad\u029d\1\0\0\0\u02ad\u029f\1\0\0\0\u02ad"+
		"\u02a7\1\0\0\0\u02ad\u02a9\1\0\0\0\u02ad\u02ab\1\0\0\0\u02aee\1\0\0\0"+
		"\u02af\u02b7\5E\0\0\u02b1\u02b5\3h\64\0\u02b3\u02b6\5G\0\0\u02b5\u02b3"+
		"\1\0\0\0\u02b5\u02b6\1\0\0\0\u02b6\u02b8\1\0\0\0\u02b7\u02b1\1\0\0\0\u02b7"+
		"\u02b8\1\0\0\0\u02b8\u02b9\1\0\0\0\u02b9\u02ba\5F\0\0\u02bag\1\0\0\0\u02bb"+
		"\u02c3\3j\65\0\u02bd\u02bf\5G\0\0\u02bf\u02c2\3j\65\0\u02c1\u02bd\1\0"+
		"\0\0\u02c2\u02c5\1\0\0\0\u02c3\u02c1\1\0\0\0\u02c3\u02c4\1\0\0\0\u02c4"+
		"i\1\0\0\0\u02c5\u02c3\1\0\0\0\u02c6\u02c8\3l\66\0\u02c8\u02c9\5J\0\0\u02c9"+
		"\u02cb\1\0\0\0\u02ca\u02c6\1\0\0\0\u02ca\u02cb\1\0\0\0\u02cb\u02cc\1\0"+
		"\0\0\u02cc\u02cd\3r9\0\u02cdk\1\0\0\0\u02ce\u02d3\3n\67\0\u02d0\u02d3"+
		"\3p8\0\u02d2\u02ce\1\0\0\0\u02d2\u02d0\1\0\0\0\u02d3m\1\0\0\0\u02d4\u02d5"+
		"\5K\0\0\u02d5o\1\0\0\0\u02d6\u02d7\3v;\0\u02d7q\1\0\0\0\u02d8\u02dd\3"+
		"v;\0\u02da\u02dd\3f\63\0\u02dc\u02d8\1\0\0\0\u02dc\u02da\1\0\0\0\u02dd"+
		"s\1\0\0\0\u02de\u02e0\3\32\r\0\u02e0\u02e1\3N\'\0\u02e1u\1\0\0\0\u02e2"+
		"\u02e4\5\34\0\0\u02e4\u0305\3v;\0\u02e6\u02e8\5\35\0\0\u02e8\u0305\3v"+
		";\0\u02ea\u02ec\5;\0\0\u02ec\u0305\3v;\0\u02ee\u02f0\5#\0\0\u02f0\u0305"+
		"\3v;\0\u02f2\u02f4\5\36\0\0\u02f4\u0305\3v;\0\u02f6\u02f8\5!\0\0\u02f8"+
		"\u0305\3v;\0\u02fa\u02fc\5\64\0\0\u02fc\u0305\3v;\0\u02fe\u0305\3V+\0"+
		"\u0300\u0305\3|>\0\u0302\u0305\3\u00d0h\0\u0304\u02e2\1\0\0\0\u0304\u02e6"+
		"\1\0\0\0\u0304\u02ea\1\0\0\0\u0304\u02ee\1\0\0\0\u0304\u02f2\1\0\0\0\u0304"+
		"\u02f6\1\0\0\0\u0304\u02fa\1\0\0\0\u0304\u02fe\1\0\0\0\u0304\u0300\1\0"+
		"\0\0\u0304\u0302\1\0\0\0\u0305\u035c\1\0\0\0\u0306\u0308\4;\0\1\u0308"+
		"\u030a\7\0\0\0\u030a\u035b\3v;\0\u030c\u030e\4;\1\1\u030e\u0310\7\1\0"+
		"\0\u0310\u035b\3v;\0\u0312\u0314\4;\2\1\u0314\u0316\7\2\0\0\u0316\u035b"+
		"\3v;\0\u0318\u031a\4;\3\1\u031a\u031c\5\62\0\0\u031c\u035b\3v;\0\u031e"+
		"\u0320\4;\4\1\u0320\u0322\5\63\0\0\u0322\u035b\3v;\0\u0324\u0326\4;\5"+
		"\1\u0326\u0328\5H\0\0\u0328\u035b\5K\0\0\u032a\u032c\4;\6\1\u032c\u032e"+
		"\5C\0\0\u032e\u0330\3v;\0\u0330\u0331\5D\0\0\u0331\u035b\1\0\0\0\u0332"+
		"\u0334\4;\7\1\u0334\u0338\5C\0\0\u0336\u0339\3v;\0\u0338\u0336\1\0\0\0"+
		"\u0338\u0339\1\0\0\0\u0339\u033a\1\0\0\0\u033a\u033e\5J\0\0\u033c\u033f"+
		"\3v;\0\u033e\u033c\1\0\0\0\u033e\u033f\1\0\0\0\u033f\u0340\1\0\0\0\u0340"+
		"\u035b\5D\0\0\u0342\u0344\4;\b\1\u0344\u0346\5H\0\0\u0346\u0348\5A\0\0"+
		"\u0348\u034a\3\0\0\0\u034a\u034b\5B\0\0\u034b\u035b\1\0\0\0\u034c\u034e"+
		"\4;\t\1\u034e\u0356\5A\0\0\u0350\u0354\3z=\0\u0352\u0355\5G\0\0\u0354"+
		"\u0352\1\0\0\0\u0354\u0355\1\0\0\0\u0355\u0357\1\0\0\0\u0356\u0350\1\0"+
		"\0\0\u0356\u0357\1\0\0\0\u0357\u0358\1\0\0\0\u0358\u035b\5B\0\0\u035a"+
		"\u0306\1\0\0\0\u035a\u030c\1\0\0\0\u035a\u0312\1\0\0\0\u035a\u0318\1\0"+
		"\0\0\u035a\u031e\1\0\0\0\u035a\u0324\1\0\0\0\u035a\u032a\1\0\0\0\u035a"+
		"\u0332\1\0\0\0\u035a\u0342\1\0\0\0\u035a\u034c\1\0\0\0\u035b\u035e\1\0"+
		"\0\0\u035c\u035a\1\0\0\0\u035c\u035d\1\0\0\0\u035dw\1\0\0\0\u035e\u035c"+
		"\1\0\0\0\u035f\u0366\3V+\0\u0361\u0366\3|>\0\u0363\u0366\3\u00d0h\0\u0365"+
		"\u035f\1\0\0\0\u0365\u0361\1\0\0\0\u0365\u0363\1\0\0\0\u0366\u039f\1\0"+
		"\0\0\u0367\u0369\4<\n\1\u0369\u036b\5H\0\0\u036b\u039e\5K\0\0\u036d\u036f"+
		"\4<\13\1\u036f\u0371\5C\0\0\u0371\u0373\3v;\0\u0373\u0374\5D\0\0\u0374"+
		"\u039e\1\0\0\0\u0375\u0377\4<\f\1\u0377\u037b\5C\0\0\u0379\u037c\3v;\0"+
		"\u037b\u0379\1\0\0\0\u037b\u037c\1\0\0\0\u037c\u037d\1\0\0\0\u037d\u0381"+
		"\5J\0\0\u037f\u0382\3v;\0\u0381\u037f\1\0\0\0\u0381\u0382\1\0\0\0\u0382"+
		"\u0383\1\0\0\0\u0383\u039e\5D\0\0\u0385\u0387\4<\r\1\u0387\u0389\5H\0"+
		"\0\u0389\u038b\5A\0\0\u038b\u038d\3\0\0\0\u038d\u038e\5B\0\0\u038e\u039e"+
		"\1\0\0\0\u038f\u0391\4<\16\1\u0391\u0399\5A\0\0\u0393\u0397\3z=\0\u0395"+
		"\u0398\5G\0\0\u0397\u0395\1\0\0\0\u0397\u0398\1\0\0\0\u0398\u039a\1\0"+
		"\0\0\u0399\u0393\1\0\0\0\u0399\u039a\1\0\0\0\u039a\u039b\1\0\0\0\u039b"+
		"\u039e\5B\0\0\u039d\u0367\1\0\0\0\u039d\u036d\1\0\0\0\u039d\u0375\1\0"+
		"\0\0\u039d\u0385\1\0\0\0\u039d\u038f\1\0\0\0\u039e\u03a1\1\0\0\0\u039f"+
		"\u039d\1\0\0\0\u039f\u03a0\1\0\0\0\u03a0y\1\0\0\0\u03a1\u039f\1\0\0\0"+
		"\u03a2\u03a6\3@ \0\u03a4\u03a7\5@\0\0\u03a6\u03a4\1\0\0\0\u03a6\u03a7"+
		"\1\0\0\0\u03a7{\1\0\0\0\u03a8\u03aa\3\0\0\0\u03aa\u03ac\5A\0\0\u03ac\u03ae"+
		"\3v;\0\u03ae\u03af\5B\0\0\u03af}\1\0\0\0\u03b0\u03cf\3\66\33\0\u03b2\u03cf"+
		"\3\u0084B\0\u03b4\u03cf\3\u0080@\0\u03b6\u03cf\3\u00b8\\\0\u03b8\u03cf"+
		"\3\u00c4b\0\u03ba\u03cf\3\u00c6c\0\u03bc\u03cf\3\u00c8d\0\u03be\u03cf"+
		"\3\u00cae\0\u03c0\u03cf\3\u00ccf\0\u03c2\u03cf\3\64\32\0\u03c4\u03cf\3"+
		"\u0098L\0\u03c6\u03cf\3\u009aM\0\u03c8\u03cf\3\u00ba]\0\u03ca\u03cf\3"+
		"\u00acV\0\u03cc\u03cf\3\u00ceg\0\u03ce\u03b0\1\0\0\0\u03ce\u03b2\1\0\0"+
		"\0\u03ce\u03b4\1\0\0\0\u03ce\u03b6\1\0\0\0\u03ce\u03b8\1\0\0\0\u03ce\u03ba"+
		"\1\0\0\0\u03ce\u03bc\1\0\0\0\u03ce\u03be\1\0\0\0\u03ce\u03c0\1\0\0\0\u03ce"+
		"\u03c2\1\0\0\0\u03ce\u03c4\1\0\0\0\u03ce\u03c6\1\0\0\0\u03ce\u03c8\1\0"+
		"\0\0\u03ce\u03ca\1\0\0\0\u03ce\u03cc\1\0\0\0\u03cf\177\1\0\0\0\u03d0\u03dd"+
		"\3\u0082A\0\u03d2\u03dd\3\u0088D\0\u03d4\u03dd\3\u008aE\0\u03d6\u03dd"+
		"\3\u008eG\0\u03d8\u03dd\3\u0090H\0\u03da\u03dd\3J%\0\u03dc\u03d0\1\0\0"+
		"\0\u03dc\u03d2\1\0\0\0\u03dc\u03d4\1\0\0\0\u03dc\u03d6\1\0\0\0\u03dc\u03d8"+
		"\1\0\0\0\u03dc\u03da\1\0\0\0\u03dd\u0081\1\0\0\0\u03de\u03df\1\0\0\0\u03df"+
		"\u0083\1\0\0\0\u03e0\u03e2\3\u0086C\0\u03e2\u03e4\5J\0\0\u03e4\u03e5\3"+
		"~?\0\u03e5\u0085\1\0\0\0\u03e6\u03e7\5K\0\0\u03e7\u0087\1\0\0\0\u03e8"+
		"\u03e9\3v;\0\u03e9\u0089\1\0\0\0\u03ea\u03ec\3\u008cF\0\u03ec\u03ee\5"+
		"\64\0\0\u03ee\u03ef\3v;\0\u03ef\u008b\1\0\0\0\u03f0\u03f1\3v;\0\u03f1"+
		"\u008d\1\0\0\0\u03f2\u03f4\3v;\0\u03f4\u03f5\7\3\0\0\u03f5\u008f\1\0\0"+
		"\0\u03f6\u03f8\3@ \0\u03f8\u03fa\3\u0092I\0\u03fa\u03fb\3@ \0\u03fb\u0091"+
		"\1\0\0\0\u03fc\u0403\3\u0094J\0\u03fe\u0403\3\u0096K\0\u0400\u0403\5:"+
		"\0\0\u0402\u03fc\1\0\0\0\u0402\u03fe\1\0\0\0\u0402\u0400\1\0\0\0\u0403"+
		"\u0093\1\0\0\0\u0404\u040d\5\'\0\0\u0406\u040d\5(\0\0\u0408\u040d\5-\0"+
		"\0\u040a\u040d\5.\0\0\u040c\u0404\1\0\0\0\u040c\u0406\1\0\0\0\u040c\u0408"+
		"\1\0\0\0\u040c\u040a\1\0\0\0\u040d\u0095\1\0\0\0\u040e\u041d\5)\0\0\u0410"+
		"\u041d\5*\0\0\u0412\u041d\5.\0\0\u0414\u041d\5/\0\0\u0416\u041d\5\60\0"+
		"\0\u0418\u041d\5,\0\0\u041a\u041d\5\61\0\0\u041c\u040e\1\0\0\0\u041c\u0410"+
		"\1\0\0\0\u041c\u0412\1\0\0\0\u041c\u0414\1\0\0\0\u041c\u0416\1\0\0\0\u041c"+
		"\u0418\1\0\0\0\u041c\u041a\1\0\0\0\u041d\u0097\1\0\0\0\u041e\u0424\5\20"+
		"\0\0\u0420\u0422\3\u0080@\0\u0422\u0423\5I\0\0\u0423\u0425\1\0\0\0\u0424"+
		"\u0420\1\0\0\0\u0424\u0425\1\0\0\0\u0425\u0426\1\0\0\0\u0426\u0428\3v"+
		";\0\u0428\u0432\3\64\32\0\u042a\u0430\5\n\0\0\u042c\u0431\3\u0098L\0\u042e"+
		"\u0431\3\64\32\0\u0430\u042c\1\0\0\0\u0430\u042e\1\0\0\0\u0431\u0433\1"+
		"\0\0\0\u0432\u042a\1\0\0\0\u0432\u0433\1\0\0\0\u0433\u0099\1\0\0\0\u0434"+
		"\u0439\3\u009cN\0\u0436\u0439\3\u00a2Q\0\u0438\u0434\1\0\0\0\u0438\u0436"+
		"\1\0\0\0\u0439\u009b\1\0\0\0\u043a\u0440\5\31\0\0\u043c\u043e\3\u0080"+
		"@\0\u043e\u043f\5I\0\0\u043f\u0441\1\0\0\0\u0440\u043c\1\0\0\0\u0440\u0441"+
		"\1\0\0\0\u0441\u0444\1\0\0\0\u0442\u0445\3v;\0\u0444\u0442\1\0\0\0\u0444"+
		"\u0445\1\0\0\0\u0445\u0446\1\0\0\0\u0446\u044c\5E\0\0\u0448\u044b\3\u009e"+
		"O\0\u044a\u0448\1\0\0\0\u044b\u044e\1\0\0\0\u044c\u044a\1\0\0\0\u044c"+
		"\u044d\1\0\0\0\u044d\u044f\1\0\0\0\u044e\u044c\1\0\0\0\u044f\u0450\5F"+
		"\0\0\u0450\u009d\1\0\0\0\u0451\u0453\3\u00a0P\0\u0453\u045b\5J\0\0\u0455"+
		"\u0457\3~?\0\u0457\u0458\5I\0\0\u0458\u045a\1\0\0\0\u0459\u0455\1\0\0"+
		"\0\u045a\u045d\1\0\0\0\u045b\u0459\1\0\0\0\u045b\u045c\1\0\0\0\u045c\u009f"+
		"\1\0\0\0\u045d\u045b\1\0\0\0\u045e\u0460\5\4\0\0\u0460\u0465\3@ \0\u0462"+
		"\u0465\5\b\0\0\u0464\u045e\1\0\0\0\u0464\u0462\1\0\0\0\u0465\u00a1\1\0"+
		"\0\0\u0466\u046c\5\31\0\0\u0468\u046a\3\u0080@\0\u046a\u046b\5I\0\0\u046b"+
		"\u046d\1\0\0\0\u046c\u0468\1\0\0\0\u046c\u046d\1\0\0\0\u046d\u046e\1\0"+
		"\0\0\u046e\u0470\3\u00a4R\0\u0470\u0476\5E\0\0\u0472\u0475\3\u00a6S\0"+
		"\u0474\u0472\1\0\0\0\u0475\u0478\1\0\0\0\u0476\u0474\1\0\0\0\u0476\u0477"+
		"\1\0\0\0\u0477\u0479\1\0\0\0\u0478\u0476\1\0\0\0\u0479\u047a\5F\0\0\u047a"+
		"\u00a3\1\0\0\0\u047b\u047d\5K\0\0\u047d\u0480\5?\0\0\u047f\u047b\1\0\0"+
		"\0\u047f\u0480\1\0\0\0\u0480\u0481\1\0\0\0\u0481\u0483\3x<\0\u0483\u0485"+
		"\5H\0\0\u0485\u0487\5A\0\0\u0487\u0489\5\32\0\0\u0489\u048a\5B\0\0\u048a"+
		"\u00a5\1\0\0\0\u048b\u048d\3\u00a8T\0\u048d\u0495\5J\0\0\u048f\u0491\3"+
		"~?\0\u0491\u0492\5I\0\0\u0492\u0494\1\0\0\0\u0493\u048f\1\0\0\0\u0494"+
		"\u0497\1\0\0\0\u0495\u0493\1\0\0\0\u0495\u0496\1\0\0\0\u0496\u00a7\1\0"+
		"\0\0\u0497\u0495\1\0\0\0\u0498\u049a\5\4\0\0\u049a\u049f\3\u00aaU\0\u049c"+
		"\u049f\5\b\0\0\u049e\u0498\1\0\0\0\u049e\u049c\1\0\0\0\u049f\u00a9\1\0"+
		"\0\0\u04a0\u04a8\3\0\0\0\u04a2\u04a4\5G\0\0\u04a4\u04a7\3\0\0\0\u04a6"+
		"\u04a2\1\0\0\0\u04a7\u04aa\1\0\0\0\u04a8\u04a6\1\0\0\0\u04a8\u04a9\1\0"+
		"\0\0\u04a9\u00ab\1\0\0\0\u04aa\u04a8\1\0\0\0\u04ab\u04b3\5\f\0\0\u04ad"+
		"\u04b4\3\u00aeW\0\u04af\u04b4\3\u00b0X\0\u04b1\u04b4\3\u00b6[\0\u04b3"+
		"\u04ad\1\0\0\0\u04b3\u04af\1\0\0\0\u04b3\u04b1\1\0\0\0\u04b3\u04b4\1\0"+
		"\0\0\u04b4\u04b5\1\0\0\0\u04b5\u04b6\3\64\32\0\u04b6\u00ad\1\0\0\0\u04b7"+
		"\u04b8\3v;\0\u04b8\u00af\1\0\0\0\u04b9\u04bc\3\u00b2Y\0\u04bb\u04b9\1"+
		"\0\0\0\u04bb\u04bc\1\0\0\0\u04bc\u04bd\1\0\0\0\u04bd\u04c1\5I\0\0\u04bf"+
		"\u04c2\3\u00aeW\0\u04c1\u04bf\1\0\0\0\u04c1\u04c2\1\0\0\0\u04c2\u04c3"+
		"\1\0\0\0\u04c3\u04c7\5I\0\0\u04c5\u04c8\3\u00b4Z\0\u04c7\u04c5\1\0\0\0"+
		"\u04c7\u04c8\1\0\0\0\u04c8\u00b1\1\0\0\0\u04c9\u04ca\3\u0080@\0\u04ca"+
		"\u00b3\1\0\0\0\u04cb\u04cc\3\u0080@\0\u04cc\u00b5\1\0\0\0\u04cd\u04d3"+
		"\3v;\0\u04cf\u04d1\5G\0\0\u04d1\u04d4\3v;\0\u04d3\u04cf\1\0\0\0\u04d3"+
		"\u04d4\1\0\0\0\u04d4\u04d5\1\0\0\0\u04d5\u04d7\7\4\0\0\u04d7\u04d9\5\25"+
		"\0\0\u04d9\u04da\3v;\0\u04da\u00b7\1\0\0\0\u04db\u04dd\5\16\0\0\u04dd"+
		"\u04de\3v;\0\u04de\u00b9\1\0\0\0\u04df\u04e1\5\27\0\0\u04e1\u04e7\5E\0"+
		"\0\u04e3\u04e6\3\u00bc^\0\u04e5\u04e3\1\0\0\0\u04e6\u04e9\1\0\0\0\u04e7"+
		"\u04e5\1\0\0\0\u04e7\u04e8\1\0\0\0\u04e8\u04ea\1\0\0\0\u04e9\u04e7\1\0"+
		"\0\0\u04ea\u04eb\5F\0\0\u04eb\u00bb\1\0\0\0\u04ec\u04ee\3\u00be_\0\u04ee"+
		"\u04f6\5J\0\0\u04f0\u04f2\3~?\0\u04f2\u04f3\5I\0\0\u04f3\u04f5\1\0\0\0"+
		"\u04f4\u04f0\1\0\0\0\u04f5\u04f8\1\0\0\0\u04f6\u04f4\1\0\0\0\u04f6\u04f7"+
		"\1\0\0\0\u04f7\u00bd\1\0\0\0\u04f8\u04f6\1\0\0\0\u04f9\u04ff\5\4\0\0\u04fb"+
		"\u0500\3\u008aE\0\u04fd\u0500\3\u00c0`\0\u04ff\u04fb\1\0\0\0\u04ff\u04fd"+
		"\1\0\0\0\u0500\u0504\1\0\0\0\u0501\u0504\5\b\0\0\u0503\u04f9\1\0\0\0\u0503"+
		"\u0501\1\0\0\0\u0504\u00bf\1\0\0\0\u0505\u050b\3v;\0\u0507\u0509\5G\0"+
		"\0\u0509\u050c\3v;\0\u050b\u0507\1\0\0\0\u050b\u050c\1\0\0\0\u050c\u050d"+
		"\1\0\0\0\u050d\u050e\7\5\0\0\u050e\u0510\1\0\0\0\u050f\u0505\1\0\0\0\u050f"+
		"\u0510\1\0\0\0\u0510\u0511\1\0\0\0\u0511\u0512\3\u00c2a\0\u0512\u00c1"+
		"\1\0\0\0\u0513\u0514\3v;\0\u0514\u00c3\1\0\0\0\u0515\u0519\5\26\0\0\u0517"+
		"\u051a\3@ \0\u0519\u0517\1\0\0\0\u0519\u051a\1\0\0\0\u051a\u00c5\1\0\0"+
		"\0\u051b\u051f\5\3\0\0\u051d\u0520\3\u0086C\0\u051f\u051d\1\0\0\0\u051f"+
		"\u0520\1\0\0\0\u0520\u00c7\1\0\0\0\u0521\u0525\5\7\0\0\u0523\u0526\3\u0086"+
		"C\0\u0525\u0523\1\0\0\0\u0525\u0526\1\0\0\0\u0526\u00c9\1\0\0\0\u0527"+
		"\u0529\5\17\0\0\u0529\u052a\3\u0086C\0\u052a\u00cb\1\0\0\0\u052b\u052c"+
		"\5\13\0\0\u052c\u00cd\1\0\0\0\u052d\u052f\5\t\0\0\u052f\u0530\3v;\0\u0530"+
		"\u00cf\1\0\0\0\u0531\u0533\5K\0\0\u0533\u053b\5A\0\0\u0535\u0539\3\u00d2"+
		"i\0\u0537\u053a\5G\0\0\u0539\u0537\1\0\0\0\u0539\u053a\1\0\0\0\u053a\u053c"+
		"\1\0\0\0\u053b\u0535\1\0\0\0\u053b\u053c\1\0\0\0\u053c\u053d\1\0\0\0\u053d"+
		"\u053e\5B\0\0\u053e\u00d1\1\0\0\0\u053f\u0545\3\0\0\0\u0541\u0543\5G\0"+
		"\0\u0543\u0546\3@ \0\u0545\u0541\1\0\0\0\u0545\u0546\1\0\0\0\u0546\u054a"+
		"\1\0\0\0\u0547\u054a\3@ \0\u0549\u053f\1\0\0\0\u0549\u0547\1\0\0\0\u054a"+
		"\u00d3\1\0\0\0\u054b\u054d\3\u00d6k\0\u054d\u0555\5I\0\0\u054f\u0551\3"+
		"\u00dam\0\u0551\u0552\5I\0\0\u0552\u0554\1\0\0\0\u0553\u054f\1\0\0\0\u0554"+
		"\u0557\1\0\0\0\u0555\u0553\1\0\0\0\u0555\u0556\1\0\0\0\u0556\u055e\1\0"+
		"\0\0\u0557\u0555\1\0\0\0\u0558\u055a\38\34\0\u055a\u055b\5I\0\0\u055b"+
		"\u055d\1\0\0\0\u055c\u0558\1\0\0\0\u055d\u0560\1\0\0\0\u055e\u055c\1\0"+
		"\0\0\u055e\u055f\1\0\0\0\u055f\u00d5\1\0\0\0\u0560\u055e\1\0\0\0\u0561"+
		"\u0563\5\24\0\0\u0563\u0564\3\u00d8l\0\u0564\u00d7\1\0\0\0\u0565\u0566"+
		"\5K\0\0\u0566\u00d9\1\0\0\0\u0567\u0578\5\21\0\0\u0569\u0579\3\u00dcn"+
		"\0\u056b\u0573\5A\0\0\u056d\u056f\3\u00dcn\0\u056f\u0570\5I\0\0\u0570"+
		"\u0572\1\0\0\0\u0571\u056d\1\0\0\0\u0572\u0575\1\0\0\0\u0573\u0571\1\0"+
		"\0\0\u0573\u0574\1\0\0\0\u0574\u0576\1\0\0\0\u0575\u0573\1\0\0\0\u0576"+
		"\u0579\5B\0\0\u0578\u0569\1\0\0\0\u0578\u056b\1\0\0\0\u0579\u00db\1\0"+
		"\0\0\u057a\u057f\5H\0\0\u057c\u057f\3\u00d8l\0\u057e\u057a\1\0\0\0\u057e"+
		"\u057c\1\0\0\0\u057e\u057f\1\0\0\0\u057f\u0580\1\0\0\0\u0580\u0581\3\u00de"+
		"o\0\u0581\u00dd\1\0\0\0\u0582\u0583\5T\0\0\u0583\u00df\1\0\0\0j\u00ea"+
		"\1\u00fe\1\u011c\1\u0127\1\u012b\1\u012f\1\u0143\1\u0149\1\u0151\1\u0153"+
		"\1\u015f\1\u0164\1\u0168\1\u0176\1\u0181\1\u0197\1\u019d\1\u01a9\1\u01b4"+
		"\1\u01bc\1\u01ca\1\u01cf\1\u01d5\1\u01db\1\u01e5\1\u01f0\1\u01ff\1\u0204"+
		"\1\u0216\1\u021b\1\u0225\1\u022b\1\u023b\1\u0249\1\u024f\1\u0253\1\u0267"+
		"\1\u026f\1\u027b\1\u0281\1\u0295\1\u02ad\1\u02b5\1\u02b7\1\u02c3\1\u02ca"+
		"\1\u02d2\1\u02dc\1\u0304\1\u0338\1\u033e\1\u0354\1\u0356\1\u035a\1\u035c"+
		"\1\u0365\1\u037b\1\u0381\1\u0397\1\u0399\1\u039d\1\u039f\1\u03a6\1\u03ce"+
		"\1\u03dc\1\u0402\1\u040c\1\u041c\1\u0424\1\u0430\1\u0432\1\u0438\1\u0440"+
		"\1\u0444\1\u044c\1\u045b\1\u0464\1\u046c\1\u0476\1\u047f\1\u0495\1\u049e"+
		"\1\u04a8\1\u04b3\1\u04bb\1\u04c1\1\u04c7\1\u04d3\1\u04e7\1\u04f6\1\u04ff"+
		"\1\u0503\1\u050b\1\u050f\1\u0519\1\u051f\1\u0525\1\u0539\1\u053b\1\u0545"+
		"\1\u0549\1\u0555\1\u055e\1\u0573\1\u0578\1\u057e\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}