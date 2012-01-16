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
			setState(289);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(287); fieldDecl();
					}
					break;
			}
			setState(291); match(RightBrace);
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
			setState(299);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(293); _localctx.idList = identifierList();
					setState(295); type();
					}
					break;

				case 2:
					{
					setState(297); anonymousField();
					}
					break;
			}
			setState(303);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(301); tag();
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
			setState(307);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(305); match(Star);
					}
					break;
			}
			setState(309); typeName();
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
			setState(311); match(StringLiteral);
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
			setState(313); match(Star);
			setState(315); baseType();
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
			setState(317); type();
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
			setState(319); match(Func);
			setState(321); signature();
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
			setState(323); parameters();
			setState(327);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(325); result();
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
			setState(333);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(329); parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(331); type();
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
			setState(335); match(LeftParen);
			setState(343);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(337); parameterList();
					setState(341);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
						case 1:
							{
							setState(339); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(345); match(RightParen);
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
			setState(347); parameterDecl();
			setState(355);
			_errHandler.sync(this);
			int _alt269 = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt269!=2 && _alt269!=-1 ) {
			    if ( _alt269==1 ) {
			        {
			        {
			        setState(349); match(Comma);
			        setState(351); parameterDecl();
			        }
			        } 
			    }
				setState(357);
				_errHandler.sync(this);
				_alt269 = getInterpreter().adaptivePredict(_input,11,_ctx);
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
			setState(360);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(358); _localctx.idList = identifierList();
					}
					break;
			}
			setState(364);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(362); match(Ellipsis);
					}
					break;
			}
			setState(366); type();
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
			setState(368); match(Interface);
			setState(370); match(LeftBrace);
			setState(387);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(372); methodSpec();
					setState(380);
					_errHandler.sync(this);
					int _alt305 = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt305!=2 && _alt305!=-1 ) {
					    if ( _alt305==1 ) {
					        {
					        {
					        setState(374); match(Semi);
					        setState(376); methodSpec();
					        }
					        } 
					    }
						setState(382);
						_errHandler.sync(this);
						_alt305 = getInterpreter().adaptivePredict(_input,14,_ctx);
					}
					setState(385);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
						case 1:
							{
							setState(383); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(389); match(RightBrace);
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
			setState(397);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(391); methodName();
					setState(393); signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(395); interfaceTypeName();
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
			setState(399); _localctx.name = match(IDENTIFIER);
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
			setState(401); typeName();
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
			setState(403); match(Map);
			setState(405); match(LeftBrack);
			setState(407); keyType();
			setState(409); match(RightBrack);
			setState(411); elementType();
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
			setState(413); type();
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
			setState(425);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(415); match(Chan);
					setState(419);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
						case 1:
							{
							setState(417); match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(421); match(LeftArrow);
					setState(423); match(Chan);
					}
					break;
			}
			setState(427); elementType();
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
			setState(429); match(LeftBrace);
			setState(446);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(431); statement();
					setState(439);
					_errHandler.sync(this);
					int _alt409 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt409!=2 && _alt409!=-1 ) {
					    if ( _alt409==1 ) {
					        {
					        {
					        setState(433); match(Semi);
					        setState(435); statement();
					        }
					        } 
					    }
						setState(441);
						_errHandler.sync(this);
						_alt409 = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(444);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(442); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(448); match(RightBrace);
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
			setState(456);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(450); constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(452); typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(454); varDecl();
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
			setState(464);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(458); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(460); functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(462); methodDecl();
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
			setState(466); match(Const);
			setState(491);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(468); constSpec();
					}
					break;

				case 2:
					{
					setState(470); match(LeftParen);
					setState(487);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
						case 1:
							{
							setState(472); constSpec();
							setState(480);
							_errHandler.sync(this);
							int _alt474 = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt474!=2 && _alt474!=-1 ) {
							    if ( _alt474==1 ) {
							        {
							        {
							        setState(474); match(Semi);
							        setState(476); constSpec();
							        }
							        } 
							    }
								setState(482);
								_errHandler.sync(this);
								_alt474 = getInterpreter().adaptivePredict(_input,25,_ctx);
							}
							setState(485);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
								case 1:
									{
									setState(483); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(489); match(RightParen);
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
			setState(493); _localctx.idList = identifierList();
			setState(503);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(497);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
						case 1:
							{
							setState(495); _localctx.explicitType = type();
							}
							break;
					}
					setState(499); match(Equal);
					setState(501); _localctx.valueList = expressionList();
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
			setState(505); _localctx.ids = match(IDENTIFIER);
			_localctx.ids_list.add(_localctx.ids);
			setState(513);
			_errHandler.sync(this);
			int _alt526 = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt526!=2 && _alt526!=-1 ) {
			    if ( _alt526==1 ) {
			        {
			        {
			        setState(507); match(Comma);
			        setState(509); _localctx.ids = match(IDENTIFIER);
			        _localctx.ids_list.add(_localctx.ids);
			        }
			        } 
			    }
				setState(515);
				_errHandler.sync(this);
				_alt526 = getInterpreter().adaptivePredict(_input,31,_ctx);
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
			setState(516); _localctx.expressions = expression(0);
			_localctx.expressions_list.add(_localctx.expressions);
			setState(524);
			_errHandler.sync(this);
			int _alt545 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt545!=2 && _alt545!=-1 ) {
			    if ( _alt545==1 ) {
			        {
			        {
			        setState(518); match(Comma);
			        setState(520); _localctx.expressions = expression(0);
			        _localctx.expressions_list.add(_localctx.expressions);
			        }
			        } 
			    }
				setState(526);
				_errHandler.sync(this);
				_alt545 = getInterpreter().adaptivePredict(_input,32,_ctx);
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
			setState(527); match(Type);
			setState(552);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(529); typeSpec();
					}
					break;

				case 2:
					{
					setState(531); match(LeftParen);
					setState(548);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(533); typeSpec();
							setState(541);
							_errHandler.sync(this);
							int _alt571 = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt571!=2 && _alt571!=-1 ) {
							    if ( _alt571==1 ) {
							        {
							        {
							        setState(535); match(Semi);
							        setState(537); typeSpec();
							        }
							        } 
							    }
								setState(543);
								_errHandler.sync(this);
								_alt571 = getInterpreter().adaptivePredict(_input,33,_ctx);
							}
							setState(546);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
								case 1:
									{
									setState(544); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(550); match(RightParen);
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
			setState(554); _localctx.name = match(IDENTIFIER);
			setState(556); type();
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
			setState(558); match(Var);
			setState(583);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(560); varSpec();
					}
					break;

				case 2:
					{
					setState(562); match(LeftParen);
					setState(579);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(564); varSpec();
							setState(572);
							_errHandler.sync(this);
							int _alt618 = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt618!=2 && _alt618!=-1 ) {
							    if ( _alt618==1 ) {
							        {
							        {
							        setState(566); match(Semi);
							        setState(568); varSpec();
							        }
							        } 
							    }
								setState(574);
								_errHandler.sync(this);
								_alt618 = getInterpreter().adaptivePredict(_input,37,_ctx);
							}
							setState(577);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
								case 1:
									{
									setState(575); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(581); match(RightParen);
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
			setState(585); _localctx.idList = identifierList();
			setState(599);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(587); _localctx.varType = type();
					setState(593);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(589); match(Equal);
							setState(591); _localctx.exprList = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(595); match(Equal);
					setState(597); _localctx.exprList = expressionList();
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
			setState(601); _localctx.idList = identifierList();
			setState(603); match(ColonEqual);
			setState(605); _localctx.exprList = expressionList();
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
			setState(607); match(Func);
			setState(609); _localctx.name = match(IDENTIFIER);
			setState(611); _localctx.sig = signature();
			setState(615);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(613); _localctx.bdy = body();
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
			setState(617); block();
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
			setState(619); match(Func);
			setState(621); _localctx.recv = receiver();
			setState(623); _localctx.name = methodName();
			setState(625); _localctx.sig = signature();
			setState(629);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(627); _localctx.bdy = body();
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
			setState(631); match(LeftParen);
			setState(635);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(633); match(IDENTIFIER);
					}
					break;
			}
			setState(639);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(637); match(Star);
					}
					break;
			}
			setState(641); baseTypeName();
			setState(643); match(RightParen);
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
			setState(645); match(IDENTIFIER);
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
			setState(659);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(647); literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(649); qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(651); methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(653); match(LeftParen);
					setState(655); expression(0);
					setState(657); match(RightParen);
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
			setState(667);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(661); basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(663); compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(665); functionLiteral();
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
			setState(679);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(669); match(INT_LITERAL);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(671); match(FLOAT_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(673); match(IMAGINARY_LITERAL);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(675); match(CharLiteral);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(677); match(StringLiteral);
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
			setState(685);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(681); _localctx.pkg = packageName();
					setState(683); match(Dot);
					}
					break;
			}
			setState(687); _localctx.id = match(IDENTIFIER);
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
			setState(689); _localctx.recvType = receiverType();
			setState(691); match(Dot);
			setState(693); _localctx.name = methodName();
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
			setState(705);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(695); typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(697); match(LeftParen);
					setState(699); match(Star);
					setState(701); typeName();
					setState(703); match(RightParen);
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
			setState(707); literalType();
			setState(709); literalValue();
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
			setState(729);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(711); structType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(713); arrayType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(715); match(LeftBrack);
					setState(717); match(Ellipsis);
					setState(719); match(RightBrack);
					setState(721); elementType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(723); sliceType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(725); mapType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(727); typeName();
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
			setState(731); match(LeftBrace);
			setState(739);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(733); elementList();
					setState(737);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
						case 1:
							{
							setState(735); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(741); match(RightBrace);
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
			setState(743); element();
			setState(751);
			_errHandler.sync(this);
			int _alt952 = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt952!=2 && _alt952!=-1 ) {
			    if ( _alt952==1 ) {
			        {
			        {
			        setState(745); match(Comma);
			        setState(747); element();
			        }
			        } 
			    }
				setState(753);
				_errHandler.sync(this);
				_alt952 = getInterpreter().adaptivePredict(_input,55,_ctx);
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
			setState(758);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(754); key();
					setState(756); match(Colon);
					}
					break;
			}
			setState(760); value();
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
			setState(766);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(762); fieldName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(764); elementIndex();
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
			setState(768); match(IDENTIFIER);
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
			setState(770); expression(0);
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
			setState(776);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(772); expression(0);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(774); literalValue();
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
			setState(778); functionType();
			setState(780); body();
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
		    setState(816);
		    //_errHandler.sync(this);
		    switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
		    	case 1:
		    		{
		    		setState(782); match(Plus);
		    		setState(784); expression(12);
		    		}
		    		break;

		    	case 2:
		    		{
		    		setState(786); match(Minus);
		    		setState(788); expression(11);
		    		}
		    		break;

		    	case 3:
		    		{
		    		setState(790); match(Bang);
		    		setState(792); expression(10);
		    		}
		    		break;

		    	case 4:
		    		{
		    		setState(794); match(Caret);
		    		setState(796); expression(9);
		    		}
		    		break;

		    	case 5:
		    		{
		    		setState(798); match(Star);
		    		setState(800); expression(8);
		    		}
		    		break;

		    	case 6:
		    		{
		    		setState(802); match(Amp);
		    		setState(804); expression(7);
		    		}
		    		break;

		    	case 7:
		    		{
		    		setState(806); match(LeftArrow);
		    		setState(808); expression(6);
		    		}
		    		break;

		    	case 8:
		    		{
		    		setState(810); operand();
		    		}
		    		break;

		    	case 9:
		    		{
		    		setState(812); conversion();
		    		}
		    		break;

		    	case 10:
		    		{
		    		setState(814); builtinCall();
		    		}
		    		break;
		    }
		    _ctx.stop = _input.LT(-1);
		    setState(904);
		    _errHandler.sync(this);
		    int _alt238 = getInterpreter().adaptivePredict(_input,65,_ctx);
		    while ( _alt238!=2 && _alt238!=-1 ) {
		        if ( _alt238==1 ) {
		    	    if ( _parseListeners!=null ) triggerExitRuleEvent();
		    	    _prevctx = _localctx;
		            {
		            setState(902);
		            //_errHandler.sync(this);
		            switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
		            	case 1:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(818);
		            		if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
		            		setState(820);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Star || _la==Slash || _la==Percent || _la==Amp || _la==LeftShift || _la==RightShift || _la==AmpCaret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(822); expression(6);
		            		}
		            		break;

		            	case 2:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(824);
		            		if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
		            		setState(826);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Plus || _la==Minus || _la==Pipe || _la==Caret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(828); expression(5);
		            		}
		            		break;

		            	case 3:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(830);
		            		if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
		            		setState(832);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==EqualEqual || _la==LessThan || _la==GreaterThan || _la==BangEqual || _la==LessEqual || _la==GreaterEqual) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(834); expression(4);
		            		}
		            		break;

		            	case 4:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(836);
		            		if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
		            		setState(838); match(And);
		            		setState(840); expression(3);
		            		}
		            		break;

		            	case 5:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(842);
		            		if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
		            		setState(844); match(Or);
		            		setState(846); expression(2);
		            		}
		            		break;

		            	case 6:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(848);
		            		if (!(17 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {17 >= $_p}?");
		            		setState(850); match(Dot);
		            		setState(852); match(IDENTIFIER);
		            		}
		            		break;

		            	case 7:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(854);
		            		if (!(16 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {16 >= $_p}?");
		            		setState(856); match(LeftBrack);
		            		setState(858); expression(0);
		            		setState(860); match(RightBrack);
		            		}
		            		break;

		            	case 8:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(862);
		            		if (!(15 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {15 >= $_p}?");
		            		setState(864); match(LeftBrack);
		            		setState(868);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
		            			case 1:
		            				{
		            				setState(866); expression(0);
		            				}
		            				break;
		            		}
		            		setState(870); match(Colon);
		            		setState(874);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
		            			case 1:
		            				{
		            				setState(872); expression(0);
		            				}
		            				break;
		            		}
		            		setState(876); match(RightBrack);
		            		}
		            		break;

		            	case 9:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(878);
		            		if (!(14 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {14 >= $_p}?");
		            		setState(880); match(Dot);
		            		setState(882); match(LeftParen);
		            		setState(884); type();
		            		setState(886); match(RightParen);
		            		}
		            		break;

		            	case 10:
		            		{
		            		_localctx = new expressionContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		setState(888);
		            		if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {13 >= $_p}?");
		            		setState(890); match(LeftParen);
		            		setState(898);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
		            			case 1:
		            				{
		            				setState(892); argumentList();
		            				setState(896);
		            				//_errHandler.sync(this);
		            				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
		            					case 1:
		            						{
		            						setState(894); match(Comma);
		            						}
		            						break;
		            				}
		            				}
		            				break;
		            		}
		            		setState(900); match(RightParen);
		            		}
		            		break;
		            }
		            } 
		        }
		    	setState(906);
		    	_errHandler.sync(this);
		    	_alt238 = getInterpreter().adaptivePredict(_input,65,_ctx);
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
		    setState(913);
		    //_errHandler.sync(this);
		    switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
		    	case 1:
		    		{
		    		setState(907); operand();
		    		}
		    		break;

		    	case 2:
		    		{
		    		setState(909); conversion();
		    		}
		    		break;

		    	case 3:
		    		{
		    		setState(911); builtinCall();
		    		}
		    		break;
		    }
		    _ctx.stop = _input.LT(-1);
		    setState(971);
		    _errHandler.sync(this);
		    int _alt82 = getInterpreter().adaptivePredict(_input,72,_ctx);
		    while ( _alt82!=2 && _alt82!=-1 ) {
		        if ( _alt82==1 ) {
		    	    if ( _parseListeners!=null ) triggerExitRuleEvent();
		    	    _prevctx = _localctx;
		            {
		            setState(969);
		            //_errHandler.sync(this);
		            switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
		            	case 1:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(915);
		            		if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
		            		setState(917); match(Dot);
		            		setState(919); match(IDENTIFIER);
		            		}
		            		break;

		            	case 2:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(921);
		            		if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
		            		setState(923); match(LeftBrack);
		            		setState(925); expression(0);
		            		setState(927); match(RightBrack);
		            		}
		            		break;

		            	case 3:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(929);
		            		if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
		            		setState(931); match(LeftBrack);
		            		setState(935);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
		            			case 1:
		            				{
		            				setState(933); expression(0);
		            				}
		            				break;
		            		}
		            		setState(937); match(Colon);
		            		setState(941);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
		            			case 1:
		            				{
		            				setState(939); expression(0);
		            				}
		            				break;
		            		}
		            		setState(943); match(RightBrack);
		            		}
		            		break;

		            	case 4:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(945);
		            		if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
		            		setState(947); match(Dot);
		            		setState(949); match(LeftParen);
		            		setState(951); type();
		            		setState(953); match(RightParen);
		            		}
		            		break;

		            	case 5:
		            		{
		            		_localctx = new primaryExprContext(_parentctx, _startState, _p);
		            		_localctx.addChild(_prevctx);
		            		pushNewRecursionContext(_localctx, RULE_primaryExpr);
		            		setState(955);
		            		if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
		            		setState(957); match(LeftParen);
		            		setState(965);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
		            			case 1:
		            				{
		            				setState(959); argumentList();
		            				setState(963);
		            				//_errHandler.sync(this);
		            				switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
		            					case 1:
		            						{
		            						setState(961); match(Comma);
		            						}
		            						break;
		            				}
		            				}
		            				break;
		            		}
		            		setState(967); match(RightParen);
		            		}
		            		break;
		            }
		            } 
		        }
		    	setState(973);
		    	_errHandler.sync(this);
		    	_alt82 = getInterpreter().adaptivePredict(_input,72,_ctx);
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
			setState(974); expressionList();
			setState(978);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					{
					setState(976); match(Ellipsis);
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
			setState(980); type();
			setState(982); match(LeftParen);
			setState(984); expression(0);
			setState(986); match(RightParen);
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
			setState(1018);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(988); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(990); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(992); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(994); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(996); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(998); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1000); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(1002); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(1004); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(1006); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(1008); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(1010); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(1012); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(1014); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(1016); deferStmt();
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
			setState(1032);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1020); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1022); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1024); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1026); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1028); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1030); shortVarDecl();
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
			setState(1036); label();
			setState(1038); match(Colon);
			setState(1040); statement();
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
			setState(1042); match(IDENTIFIER);
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
			setState(1044); expression(0);
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
			setState(1046); channel();
			setState(1048); match(LeftArrow);
			setState(1050); expression(0);
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
			setState(1052); expression(0);
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
			setState(1054); expression(0);
			setState(1056);
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
			setState(1058); expressionList();
			setState(1060); assignOp();
			setState(1062); expressionList();
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
			setState(1070);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1064); addAssignOp();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1066); mulAssignOp();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1068); match(Equal);
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
			setState(1080);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1072); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1074); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1076); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1078); match(CaretEqual);
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
			setState(1096);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1082); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1084); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1086); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1088); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1090); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1092); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1094); match(AmpCaretEqual);
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
			setState(1098); match(If);
			setState(1104);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					{
					setState(1100); simpleStmt();
					setState(1102); match(Semi);
					}
					break;
			}
			setState(1106); expression(0);
			setState(1108); block();
			setState(1118);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(1110); match(Else);
					setState(1116);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
						case 1:
							{
							setState(1112); ifStmt();
							}
							break;

						case 2:
							{
							setState(1114); block();
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
			setState(1124);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1120); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1122); typeSwitchStmt();
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
			setState(1126); match(Switch);
			setState(1132);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(1128); simpleStmt();
					setState(1130); match(Semi);
					}
					break;
			}
			setState(1136);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(1134); expression(0);
					}
					break;
			}
			setState(1138); match(LeftBrace);
			setState(1144);
			_errHandler.sync(this);
			int _alt1671 = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt1671!=2 && _alt1671!=-1 ) {
			    if ( _alt1671==1 ) {
			        {
			        {
			        setState(1140); exprCaseClause();
			        }
			        } 
			    }
				setState(1146);
				_errHandler.sync(this);
				_alt1671 = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(1147); match(RightBrace);
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
			setState(1149); exprSwitchCase();
			setState(1151); match(Colon);
			setState(1168);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(1153); statement();
					setState(1161);
					_errHandler.sync(this);
					int _alt1693 = getInterpreter().adaptivePredict(_input,86,_ctx);
					while ( _alt1693!=2 && _alt1693!=-1 ) {
					    if ( _alt1693==1 ) {
					        {
					        {
					        setState(1155); match(Semi);
					        setState(1157); statement();
					        }
					        } 
					    }
						setState(1163);
						_errHandler.sync(this);
						_alt1693 = getInterpreter().adaptivePredict(_input,86,_ctx);
					}
					setState(1166);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(1164); match(Semi);
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
			setState(1176);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1170); match(Case);
					setState(1172); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1174); match(Default);
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
			setState(1178); match(Switch);
			setState(1184);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
				case 1:
					{
					setState(1180); simpleStmt();
					setState(1182); match(Semi);
					}
					break;
			}
			setState(1186); typeSwitchGuard();
			setState(1188); match(LeftBrace);
			setState(1194);
			_errHandler.sync(this);
			int _alt1734 = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt1734!=2 && _alt1734!=-1 ) {
			    if ( _alt1734==1 ) {
			        {
			        {
			        setState(1190); typeCaseClause();
			        }
			        } 
			    }
				setState(1196);
				_errHandler.sync(this);
				_alt1734 = getInterpreter().adaptivePredict(_input,91,_ctx);
			}
			setState(1197); match(RightBrace);
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
			setState(1203);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(1199); match(IDENTIFIER);
					setState(1201); match(ColonEqual);
					}
					break;
			}
			setState(1205); primaryExpr(0);
			setState(1207); match(Dot);
			setState(1209); match(LeftParen);
			setState(1211); match(Type);
			setState(1213); match(RightParen);
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
			setState(1215); typeSwitchCase();
			setState(1217); match(Colon);
			setState(1234);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1219); statement();
					setState(1227);
					_errHandler.sync(this);
					int _alt1779 = getInterpreter().adaptivePredict(_input,93,_ctx);
					while ( _alt1779!=2 && _alt1779!=-1 ) {
					    if ( _alt1779==1 ) {
					        {
					        {
					        setState(1221); match(Semi);
					        setState(1223); statement();
					        }
					        } 
					    }
						setState(1229);
						_errHandler.sync(this);
						_alt1779 = getInterpreter().adaptivePredict(_input,93,_ctx);
					}
					setState(1232);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
						case 1:
							{
							setState(1230); match(Semi);
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
			setState(1242);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1236); match(Case);
					setState(1238); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1240); match(Default);
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
			setState(1244); type();
			setState(1252);
			_errHandler.sync(this);
			int _alt1813 = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt1813!=2 && _alt1813!=-1 ) {
			    if ( _alt1813==1 ) {
			        {
			        {
			        setState(1246); match(Comma);
			        setState(1248); type();
			        }
			        } 
			    }
				setState(1254);
				_errHandler.sync(this);
				_alt1813 = getInterpreter().adaptivePredict(_input,97,_ctx);
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
			setState(1255); match(For);
			setState(1263);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(1257); condition();
					}
					break;

				case 2:
					{
					setState(1259); forClause();
					}
					break;

				case 3:
					{
					setState(1261); rangeClause();
					}
					break;
			}
			setState(1265); block();
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
			setState(1267); expression(0);
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
			setState(1271);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(1269); initStmt();
					}
					break;
			}
			setState(1273); match(Semi);
			setState(1277);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(1275); condition();
					}
					break;
			}
			setState(1279); match(Semi);
			setState(1283);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(1281); postStmt();
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
			setState(1285); simpleStmt();
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
			setState(1287); simpleStmt();
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
			setState(1289); expression(0);
			setState(1295);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					{
					setState(1291); match(Comma);
					setState(1293); expression(0);
					}
					break;
			}
			setState(1297);
			_input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Equal || _la==ColonEqual) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(1299); match(Range);
			setState(1301); expression(0);
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
			setState(1303); match(Go);
			setState(1305); expression(0);
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
			setState(1307); match(Select);
			setState(1309); match(LeftBrace);
			setState(1315);
			_errHandler.sync(this);
			int _alt1929 = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt1929!=2 && _alt1929!=-1 ) {
			    if ( _alt1929==1 ) {
			        {
			        {
			        setState(1311); commClause();
			        }
			        } 
			    }
				setState(1317);
				_errHandler.sync(this);
				_alt1929 = getInterpreter().adaptivePredict(_input,103,_ctx);
			}
			setState(1318); match(RightBrace);
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
			setState(1320); commCase();
			setState(1322); match(Colon);
			setState(1339);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(1324); statement();
					setState(1332);
					_errHandler.sync(this);
					int _alt1951 = getInterpreter().adaptivePredict(_input,104,_ctx);
					while ( _alt1951!=2 && _alt1951!=-1 ) {
					    if ( _alt1951==1 ) {
					        {
					        {
					        setState(1326); match(Semi);
					        setState(1328); statement();
					        }
					        } 
					    }
						setState(1334);
						_errHandler.sync(this);
						_alt1951 = getInterpreter().adaptivePredict(_input,104,_ctx);
					}
					setState(1337);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
						case 1:
							{
							setState(1335); match(Semi);
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
			setState(1351);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1341); match(Case);
					setState(1347);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(1343); sendStmt();
							}
							break;

						case 2:
							{
							setState(1345); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1349); match(Default);
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
			setState(1363);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
				case 1:
					{
					setState(1353); expression(0);
					setState(1359);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
						case 1:
							{
							setState(1355); match(Comma);
							setState(1357); expression(0);
							}
							break;
					}
					setState(1361);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Equal || _la==ColonEqual) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					break;
			}
			setState(1365); recvExpr();
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
			setState(1367); expression(0);
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
			setState(1369); match(Return);
			setState(1373);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					{
					setState(1371); expressionList();
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
			setState(1375); match(Break);
			setState(1379);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
				case 1:
					{
					setState(1377); label();
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
			setState(1381); match(Continue);
			setState(1385);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
				case 1:
					{
					setState(1383); label();
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
			setState(1387); match(Goto);
			setState(1389); label();
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
			setState(1391); match(Fallthrough);
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
			setState(1393); match(Defer);
			setState(1395); expression(0);
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
			setState(1397); match(IDENTIFIER);
			setState(1399); match(LeftParen);
			setState(1407);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
				case 1:
					{
					setState(1401); builtinArgs();
					setState(1405);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
						case 1:
							{
							setState(1403); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1409); match(RightParen);
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
			setState(1421);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1411); type();
					setState(1417);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
						case 1:
							{
							setState(1413); match(Comma);
							setState(1415); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1419); expressionList();
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
			setState(1423); packageClause();
			setState(1425); match(Semi);
			setState(1433);
			_errHandler.sync(this);
			int _alt2129 = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt2129!=2 && _alt2129!=-1 ) {
			    if ( _alt2129==1 ) {
			        {
			        {
			        setState(1427); importDecl();
			        setState(1429); match(Semi);
			        }
			        } 
			    }
				setState(1435);
				_errHandler.sync(this);
				_alt2129 = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(1442);
			_errHandler.sync(this);
			int _alt2136 = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt2136!=2 && _alt2136!=-1 ) {
			    if ( _alt2136==1 ) {
			        {
			        {
			        setState(1436); topLevelDecl();
			        setState(1438); match(Semi);
			        }
			        } 
			    }
				setState(1444);
				_errHandler.sync(this);
				_alt2136 = getInterpreter().adaptivePredict(_input,119,_ctx);
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
			setState(1445); match(Package);
			setState(1447); packageName();
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
			setState(1449); match(IDENTIFIER);
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
			setState(1451); match(Import);
			setState(1476);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
				case 1:
					{
					setState(1453); importSpec();
					}
					break;

				case 2:
					{
					setState(1455); match(LeftParen);
					setState(1472);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
						case 1:
							{
							setState(1457); importSpec();
							setState(1465);
							_errHandler.sync(this);
							int _alt2179 = getInterpreter().adaptivePredict(_input,120,_ctx);
							while ( _alt2179!=2 && _alt2179!=-1 ) {
							    if ( _alt2179==1 ) {
							        {
							        {
							        setState(1459); match(Semi);
							        setState(1461); importSpec();
							        }
							        } 
							    }
								setState(1467);
								_errHandler.sync(this);
								_alt2179 = getInterpreter().adaptivePredict(_input,120,_ctx);
							}
							setState(1470);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
								case 1:
									{
									setState(1468); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(1474); match(RightParen);
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
			setState(1482);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
				case 1:
					{
					setState(1478); match(Dot);
					}
					break;

				case 2:
					{
					setState(1480); packageName();
					}
					break;
			}
			setState(1484); importPath();
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
			setState(1486); match(StringLiteral);
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
		"\1U\u05d1\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		"\0\3\7\b\7\1\7\1\7\1\b\0\1\b\1\b\1\b\0\3\b\b\b\1\b\0\3\b\b\b\1\t\0\3\t"+
		"\b\t\1\t\1\t\1\n\1\n\1\13\0\1\13\1\13\1\f\1\f\1\r\0\1\r\1\r\1\16\0\1\16"+
		"\0\3\16\b\16\1\17\0\1\17\0\3\17\b\17\1\20\0\1\20\0\1\20\0\3\20\b\20\3"+
		"\20\b\20\1\20\1\20\1\21\0\1\21\0\1\21\0\5\21\b\21\n\21\f\21\u0165\t\21"+
		"\1\22\0\3\22\b\22\1\22\0\3\22\b\22\1\22\1\22\1\23\0\1\23\0\1\23\0\1\23"+
		"\0\1\23\0\5\23\b\23\n\23\f\23\u017e\t\23\1\23\0\3\23\b\23\3\23\b\23\1"+
		"\23\1\23\1\24\0\1\24\1\24\1\24\0\3\24\b\24\1\25\1\25\1\26\1\26\1\27\0"+
		"\1\27\0\1\27\0\1\27\0\1\27\1\27\1\30\1\30\1\31\0\1\31\0\3\31\b\31\1\31"+
		"\0\1\31\0\3\31\b\31\1\31\1\31\1\32\0\1\32\0\1\32\0\1\32\0\5\32\b\32\n"+
		"\32\f\32\u01b9\t\32\1\32\0\3\32\b\32\3\32\b\32\1\32\1\32\1\33\0\1\33\0"+
		"\1\33\0\3\33\b\33\1\34\0\1\34\0\1\34\0\3\34\b\34\1\35\0\1\35\0\1\35\0"+
		"\1\35\0\1\35\0\1\35\0\5\35\b\35\n\35\f\35\u01e2\t\35\1\35\0\3\35\b\35"+
		"\3\35\b\35\1\35\0\3\35\b\35\1\36\0\1\36\0\3\36\b\36\1\36\0\1\36\0\3\36"+
		"\b\36\1\37\0\1\37\0\1\37\0\5\37\b\37\n\37\f\37\u0203\t\37\1 \0\1 \0\1"+
		" \0\5 \b \n \f \u020e\t \1!\0\1!\0\1!\0\1!\0\1!\0\1!\0\5!\b!\n!\f!\u021f"+
		"\t!\1!\0\3!\b!\3!\b!\1!\0\3!\b!\1\"\0\1\"\1\"\1#\0\1#\0\1#\0\1#\0\1#\0"+
		"\1#\0\5#\b#\n#\f#\u023e\t#\1#\0\3#\b#\3#\b#\1#\0\3#\b#\1$\0\1$\0\1$\0"+
		"\1$\0\3$\b$\1$\0\1$\0\3$\b$\1%\0\1%\0\1%\1%\1&\0\1&\0\1&\0\1&\0\3&\b&"+
		"\1\'\1\'\1(\0\1(\0\1(\0\1(\0\1(\0\3(\b(\1)\0\1)\0\3)\b)\1)\0\3)\b)\1)"+
		"\0\1)\1)\1*\1*\1+\0\1+\0\1+\0\1+\0\1+\0\1+\1+\3+\b+\1,\0\1,\0\1,\0\3,"+
		"\b,\1-\0\1-\0\1-\0\1-\0\1-\0\3-\b-\1.\0\1.\1.\3.\b.\1.\1.\1/\0\1/\0\1"+
		"/\1/\1\60\0\1\60\0\1\60\0\1\60\0\1\60\1\60\3\60\b\60\1\61\0\1\61\1\61"+
		"\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\3\62\b"+
		"\62\1\63\0\1\63\0\1\63\0\3\63\b\63\3\63\b\63\1\63\1\63\1\64\0\1\64\0\1"+
		"\64\0\5\64\b\64\n\64\f\64\u02f1\t\64\1\65\0\1\65\1\65\3\65\b\65\1\65\1"+
		"\65\1\66\0\1\66\0\3\66\b\66\1\67\1\67\18\18\19\0\19\0\39\b9\1:\0\1:\1"+
		":\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;"+
		"\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0"+
		"\1;\0\1;\0\3;\b;\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1"+
		";\0\1;\0\1;\0\3;\b;\3;\b;\1;\0\5;\b;\n;\f;\u038a\t;\1<\0\1<\0\1<\0\3<"+
		"\b<\1<\0\1<\0\1<\0\1<\0\1<\0\1<\0\1<\1<\1<\0\1<\0\1<\0\3<\b<\1<\0\1<\0"+
		"\3<\b<\1<\0\1<\0\1<\0\1<\0\1<\0\1<\1<\1<\0\1<\0\1<\0\1<\0\3<\b<\3<\b<"+
		"\1<\0\5<\b<\n<\f<\u03cd\t<\1=\0\1=\0\3=\b=\1>\0\1>\0\1>\0\1>\1>\1?\0\1"+
		"?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\3"+
		"?\b?\1@\0\1@\0\1@\0\1@\0\1@\0\1@\0\3@\b@\1A\1A\1B\0\1B\0\1B\1B\1C\1C\1"+
		"D\1D\1E\0\1E\0\1E\1E\1F\1F\1G\0\1G\1G\1H\0\1H\0\1H\1H\1I\0\1I\0\1I\0\3"+
		"I\bI\1J\0\1J\0\1J\0\1J\0\3J\bJ\1K\0\1K\0\1K\0\1K\0\1K\0\1K\0\1K\0\3K\b"+
		"K\1L\0\1L\0\1L\1L\3L\bL\1L\0\1L\0\1L\0\1L\0\1L\0\3L\bL\3L\bL\1M\0\1M\0"+
		"\3M\bM\1N\0\1N\0\1N\1N\3N\bN\1N\0\3N\bN\1N\0\1N\0\5N\bN\nN\fN\u047a\t"+
		"N\1N\1N\1O\0\1O\0\1O\0\1O\0\1O\0\5O\bO\nO\fO\u048b\tO\1O\0\3O\bO\3O\b"+
		"O\1P\0\1P\0\1P\0\3P\bP\1Q\0\1Q\0\1Q\1Q\3Q\bQ\1Q\0\1Q\0\1Q\0\5Q\bQ\nQ\f"+
		"Q\u04ac\tQ\1Q\1Q\1R\0\1R\0\3R\bR\1R\0\1R\0\1R\0\1R\0\1R\1R\1S\0\1S\0\1"+
		"S\0\1S\0\1S\0\5S\bS\nS\fS\u04cd\tS\1S\0\3S\bS\3S\bS\1T\0\1T\0\1T\0\3T"+
		"\bT\1U\0\1U\0\1U\0\5U\bU\nU\fU\u04e6\tU\1V\0\1V\0\1V\0\1V\0\3V\bV\1V\1"+
		"V\1W\1W\1X\0\3X\bX\1X\0\1X\0\3X\bX\1X\0\1X\0\3X\bX\1Y\1Y\1Z\1Z\1[\0\1"+
		"[\0\1[\0\3[\b[\1[\0\1[\0\1[\1[\1\\\0\1\\\1\\\1]\0\1]\0\1]\0\5]\b]\n]\f"+
		"]\u0525\t]\1]\1]\1^\0\1^\0\1^\0\1^\0\1^\0\5^\b^\n^\f^\u0536\t^\1^\0\3"+
		"^\b^\3^\b^\1_\0\1_\0\1_\0\3_\b_\1_\0\3_\b_\1`\0\1`\0\1`\0\3`\b`\1`\1`"+
		"\3`\b`\1`\1`\1a\1a\1b\0\1b\0\3b\bb\1c\0\1c\0\3c\bc\1d\0\1d\0\3d\bd\1e"+
		"\0\1e\1e\1f\1f\1g\0\1g\1g\1h\0\1h\0\1h\0\1h\0\3h\bh\3h\bh\1h\1h\1i\0\1"+
		"i\0\1i\0\3i\bi\1i\0\3i\bi\1j\0\1j\0\1j\0\1j\1j\5j\bj\nj\fj\u059b\tj\1"+
		"j\0\1j\1j\5j\bj\nj\fj\u05a4\tj\1k\0\1k\1k\1l\1l\1m\0\1m\0\1m\0\1m\0\1"+
		"m\0\1m\0\5m\bm\nm\fm\u05bb\tm\1m\0\3m\bm\3m\bm\1m\0\3m\bm\1n\0\1n\0\3"+
		"n\bn\1n\1n\1o\1o\1op\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e"+
		"\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6"+
		"\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce"+
		"\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\0\6\2\36!$&\2\34\35\""+
		"#\2\679<>\1\65\66\2::??\2::??\u058a\0\u00ea\1\0\0\0\1\u00e8\1\0\0\0\1"+
		"\u010b\1\0\0\0\1\u0128\1\0\0\0\1\u013e\1\0\0\0\1\u014e\1\0\0\0\1\u016f"+
		"\1\0\0\0\1\u019e\1\0\0\0\1\u01f2\1\0\0\0\1\u022d\1\0\0\0\1\u0251\1\0\0"+
		"\0\1\u0376\1\0\0\0\1\u03b9\1\0\0\0\1\u03d6\1\0\0\0\1\u04e4\1\0\0\0\1\u04e3"+
		"\1\0\0\0\1\u0589\1\0\0\0\2\u00ec\1\0\0\0\3\u00eb\1\0\0\0\3\u0136\1\0\0"+
		"\0\3\u0192\1\0\0\0\3\u02c2\1\0\0\0\3\u02bf\1\0\0\0\3\u02da\1\0\0\0\4\u00fe"+
		"\1\0\0\0\5\u00eb\1\0\0\0\6\u0100\1\0\0\0\7\u00ff\1\0\0\0\7\u02da\1\0\0"+
		"\0\b\u0108\1\0\0\0\t\u0104\1\0\0\0\n\u010a\1\0\0\0\13\u0107\1\0\0\0\13"+
		"\u0111\1\0\0\0\13\u019c\1\0\0\0\13\u01ac\1\0\0\0\13\u02da\1\0\0\0\f\u010c"+
		"\1\0\0\0\r\u00ff\1\0\0\0\r\u02da\1\0\0\0\16\u0112\1\0\0\0\17\u00ff\1\0"+
		"\0\0\17\u02da\1\0\0\0\20\u012b\1\0\0\0\21\u0118\1\0\0\0\21\u0122\1\0\0"+
		"\0\22\u0133\1\0\0\0\23\u012c\1\0\0\0\24\u0137\1\0\0\0\25\u0130\1\0\0\0"+
		"\26\u0139\1\0\0\0\27\u00ff\1\0\0\0\30\u013d\1\0\0\0\31\u013c\1\0\0\0\32"+
		"\u013f\1\0\0\0\33\u00ff\1\0\0\0\33\u030c\1\0\0\0\34\u0143\1\0\0\0\35\u0142"+
		"\1\0\0\0\35\u018a\1\0\0\0\35\u0267\1\0\0\0\35\u0275\1\0\0\0\36\u014d\1"+
		"\0\0\0\37\u0148\1\0\0\0 \u014f\1\0\0\0!\u0147\1\0\0\0!\u014e\1\0\0\0\""+
		"\u015b\1\0\0\0#\u0155\1\0\0\0$\u0168\1\0\0\0%\u0163\1\0\0\0%\u0162\1\0"+
		"\0\0&\u0170\1\0\0\0\'\u00ff\1\0\0\0(\u018d\1\0\0\0)\u017c\1\0\0\0)\u017b"+
		"\1\0\0\0*\u018f\1\0\0\0+\u0189\1\0\0\0+\u0271\1\0\0\0+\u02b6\1\0\0\0,"+
		"\u0191\1\0\0\0-\u018e\1\0\0\0.\u0193\1\0\0\0/\u00ff\1\0\0\0/\u02da\1\0"+
		"\0\0\60\u019d\1\0\0\0\61\u0199\1\0\0\0\62\u01a9\1\0\0\0\63\u00ff\1\0\0"+
		"\0\64\u01ad\1\0\0\0\65\u026a\1\0\0\0\65\u03fb\1\0\0\0\65\u045e\1\0\0\0"+
		"\65\u045d\1\0\0\0\65\u04f2\1\0\0\0\66\u01c8\1\0\0\0\67\u01d1\1\0\0\0\67"+
		"\u03fb\1\0\0\08\u01d0\1\0\0\09\u059e\1\0\0\0:\u01d2\1\0\0\0;\u01c9\1\0"+
		"\0\0<\u01ed\1\0\0\0=\u01ec\1\0\0\0=\u01e0\1\0\0\0=\u01df\1\0\0\0>\u01f9"+
		"\1\0\0\0?\u0127\1\0\0\0?\u0169\1\0\0\0?\u01f7\1\0\0\0?\u0257\1\0\0\0?"+
		"\u025b\1\0\0\0@\u0204\1\0\0\0A\u01f8\1\0\0\0A\u0252\1\0\0\0A\u0258\1\0"+
		"\0\0A\u025e\1\0\0\0A\u03d2\1\0\0\0A\u0424\1\0\0\0A\u0427\1\0\0\0A\u0499"+
		"\1\0\0\0A\u055e\1\0\0\0A\u058a\1\0\0\0A\u058e\1\0\0\0B\u020f\1\0\0\0C"+
		"\u01c9\1\0\0\0D\u022a\1\0\0\0E\u0229\1\0\0\0E\u021d\1\0\0\0E\u021c\1\0"+
		"\0\0F\u022e\1\0\0\0G\u01c9\1\0\0\0H\u0249\1\0\0\0I\u0248\1\0\0\0I\u023c"+
		"\1\0\0\0I\u023b\1\0\0\0J\u0259\1\0\0\0K\u0409\1\0\0\0L\u025f\1\0\0\0M"+
		"\u01d1\1\0\0\0N\u0269\1\0\0\0O\u0268\1\0\0\0O\u0276\1\0\0\0O\u030d\1\0"+
		"\0\0P\u026b\1\0\0\0Q\u01d1\1\0\0\0R\u0277\1\0\0\0S\u026f\1\0\0\0T\u0285"+
		"\1\0\0\0U\u0283\1\0\0\0V\u0293\1\0\0\0W\u0331\1\0\0\0W\u0392\1\0\0\0X"+
		"\u029b\1\0\0\0Y\u0294\1\0\0\0Z\u02a7\1\0\0\0[\u029c\1\0\0\0\\\u02ad\1"+
		"\0\0\0]\u00ed\1\0\0\0]\u0294\1\0\0\0^\u02b1\1\0\0\0_\u0294\1\0\0\0`\u02c1"+
		"\1\0\0\0a\u02b3\1\0\0\0b\u02c3\1\0\0\0c\u029c\1\0\0\0d\u02d9\1\0\0\0e"+
		"\u02c5\1\0\0\0f\u02db\1\0\0\0g\u02c6\1\0\0\0g\u0309\1\0\0\0h\u02e7\1\0"+
		"\0\0i\u02e1\1\0\0\0j\u02f6\1\0\0\0k\u02ef\1\0\0\0k\u02ee\1\0\0\0l\u02fe"+
		"\1\0\0\0m\u02f4\1\0\0\0n\u0300\1\0\0\0o\u02ff\1\0\0\0p\u0302\1\0\0\0q"+
		"\u02ff\1\0\0\0r\u0308\1\0\0\0s\u02f9\1\0\0\0t\u030a\1\0\0\0u\u029c\1\0"+
		"\0\0v\u0330\1\0\0\0w\u0109\1\0\0\0w\u020c\1\0\0\0w\u020b\1\0\0\0w\u0291"+
		"\1\0\0\0w\u0303\1\0\0\0w\u0309\1\0\0\0w\u0331\1\0\0\0w\u0331\1\0\0\0w"+
		"\u0331\1\0\0\0w\u0331\1\0\0\0w\u0331\1\0\0\0w\u0331\1\0\0\0w\u0331\1\0"+
		"\0\0w\u0387\1\0\0\0w\u0387\1\0\0\0w\u0387\1\0\0\0w\u0387\1\0\0\0w\u0387"+
		"\1\0\0\0w\u035c\1\0\0\0w\u0365\1\0\0\0w\u036b\1\0\0\0w\u039f\1\0\0\0w"+
		"\u03a8\1\0\0\0w\u03ae\1\0\0\0w\u03da\1\0\0\0w\u0415\1\0\0\0w\u041b\1\0"+
		"\0\0w\u041d\1\0\0\0w\u0420\1\0\0\0w\u0454\1\0\0\0w\u0471\1\0\0\0w\u04f4"+
		"\1\0\0\0w\u050f\1\0\0\0w\u0510\1\0\0\0w\u0516\1\0\0\0w\u051a\1\0\0\0w"+
		"\u054f\1\0\0\0w\u0550\1\0\0\0w\u0558\1\0\0\0w\u0574\1\0\0\0x\u0391\1\0"+
		"\0\0y\u04b7\1\0\0\0z\u03ce\1\0\0\0{\u0380\1\0\0\0{\u03c3\1\0\0\0|\u03d4"+
		"\1\0\0\0}\u0331\1\0\0\0}\u0392\1\0\0\0~\u03fa\1\0\0\0\177\u01b7\1\0\0"+
		"\0\177\u01b6\1\0\0\0\177\u0411\1\0\0\0\177\u0489\1\0\0\0\177\u0488\1\0"+
		"\0\0\177\u04cb\1\0\0\0\177\u04ca\1\0\0\0\177\u0534\1\0\0\0\177\u0533\1"+
		"\0\0\0\u0080\u0408\1\0\0\0\u0081\u03fb\1\0\0\0\u0081\u044e\1\0\0\0\u0081"+
		"\u046a\1\0\0\0\u0081\u049e\1\0\0\0\u0081\u0506\1\0\0\0\u0081\u0508\1\0"+
		"\0\0\u0082\u040a\1\0\0\0\u0083\u0409\1\0\0\0\u0084\u040c\1\0\0\0\u0085"+
		"\u03fb\1\0\0\0\u0086\u0412\1\0\0\0\u0087\u040e\1\0\0\0\u0087\u0564\1\0"+
		"\0\0\u0087\u056a\1\0\0\0\u0087\u056e\1\0\0\0\u0088\u0414\1\0\0\0\u0089"+
		"\u0409\1\0\0\0\u008a\u0416\1\0\0\0\u008b\u0409\1\0\0\0\u008b\u0544\1\0"+
		"\0\0\u008c\u041c\1\0\0\0\u008d\u0418\1\0\0\0\u008e\u041e\1\0\0\0\u008f"+
		"\u0409\1\0\0\0\u0090\u0422\1\0\0\0\u0091\u0409\1\0\0\0\u0092\u042e\1\0"+
		"\0\0\u0093\u0426\1\0\0\0\u0094\u0438\1\0\0\0\u0095\u042f\1\0\0\0\u0096"+
		"\u0448\1\0\0\0\u0097\u042f\1\0\0\0\u0098\u044a\1\0\0\0\u0099\u03fb\1\0"+
		"\0\0\u0099\u045d\1\0\0\0\u009a\u0464\1\0\0\0\u009b\u03fb\1\0\0\0\u009c"+
		"\u0466\1\0\0\0\u009d\u0465\1\0\0\0\u009e\u047d\1\0\0\0\u009f\u0477\1\0"+
		"\0\0\u00a0\u0498\1\0\0\0\u00a1\u047f\1\0\0\0\u00a2\u049a\1\0\0\0\u00a3"+
		"\u0465\1\0\0\0\u00a4\u04b3\1\0\0\0\u00a5\u04a4\1\0\0\0\u00a6\u04bf\1\0"+
		"\0\0\u00a7\u04a9\1\0\0\0\u00a8\u04da\1\0\0\0\u00a9\u04c1\1\0\0\0\u00aa"+
		"\u04dc\1\0\0\0\u00ab\u04db\1\0\0\0\u00ac\u04e7\1\0\0\0\u00ad\u03fb\1\0"+
		"\0\0\u00ae\u04f3\1\0\0\0\u00af\u04f0\1\0\0\0\u00af\u04fe\1\0\0\0\u00b0"+
		"\u04f7\1\0\0\0\u00b1\u04f0\1\0\0\0\u00b2\u0505\1\0\0\0\u00b3\u04f8\1\0"+
		"\0\0\u00b4\u0507\1\0\0\0\u00b5\u0504\1\0\0\0\u00b6\u0509\1\0\0\0\u00b7"+
		"\u04f0\1\0\0\0\u00b8\u0517\1\0\0\0\u00b9\u03fb\1\0\0\0\u00ba\u051b\1\0"+
		"\0\0\u00bb\u03fb\1\0\0\0\u00bc\u0528\1\0\0\0\u00bd\u0522\1\0\0\0\u00be"+
		"\u0547\1\0\0\0\u00bf\u052a\1\0\0\0\u00c0\u0553\1\0\0\0\u00c1\u0544\1\0"+
		"\0\0\u00c2\u0557\1\0\0\0\u00c3\u0556\1\0\0\0\u00c4\u0559\1\0\0\0\u00c5"+
		"\u03fb\1\0\0\0\u00c6\u055f\1\0\0\0\u00c7\u03fb\1\0\0\0\u00c8\u0565\1\0"+
		"\0\0\u00c9\u03fb\1\0\0\0\u00ca\u056b\1\0\0\0\u00cb\u03fb\1\0\0\0\u00cc"+
		"\u056f\1\0\0\0\u00cd\u03fb\1\0\0\0\u00ce\u0571\1\0\0\0\u00cf\u03fb\1\0"+
		"\0\0\u00d0\u0575\1\0\0\0\u00d1\u0331\1\0\0\0\u00d1\u0392\1\0\0\0\u00d2"+
		"\u058d\1\0\0\0\u00d3\u057d\1\0\0\0\u00d4\u058f\1\0\0\0\u00d5\u05d0\5\uffff"+
		"\0\0\u00d6\u05a5\1\0\0\0\u00d7\u0591\1\0\0\0\u00d8\u05a9\1\0\0\0\u00d9"+
		"\u02ab\1\0\0\0\u00d9\u05a8\1\0\0\0\u00d9\u05cb\1\0\0\0\u00da\u05ab\1\0"+
		"\0\0\u00db\u0595\1\0\0\0\u00dc\u05ca\1\0\0\0\u00dd\u05c5\1\0\0\0\u00dd"+
		"\u05b9\1\0\0\0\u00dd\u05b8\1\0\0\0\u00de\u05ce\1\0\0\0\u00df\u05cd\1\0"+
		"\0\0\u00e0\u00eb\3\2\1\0\u00e2\u00eb\3\4\2\0\u00e4\u00e6\5A\0\0\u00e6"+
		"\u00e8\3\0\0\0\u00e8\u00e9\5B\0\0\u00e9\u00eb\1\0\0\0\u00ea\u00e0\1\0"+
		"\0\0\u00ea\u00e2\1\0\0\0\u00ea\u00e4\1\0\0\0\u00eb\1\1\0\0\0\u00ec\u00ed"+
		"\3\\.\0\u00ed\3\1\0\0\0\u00ee\u00ff\3\6\3\0\u00f0\u00ff\3\16\7\0\u00f2"+
		"\u00ff\3\26\13\0\u00f4\u00ff\3\32\r\0\u00f6\u00ff\3&\23\0\u00f8\u00ff"+
		"\3\f\6\0\u00fa\u00ff\3.\27\0\u00fc\u00ff\3\62\31\0\u00fe\u00ee\1\0\0\0"+
		"\u00fe\u00f0\1\0\0\0\u00fe\u00f2\1\0\0\0\u00fe\u00f4\1\0\0\0\u00fe\u00f6"+
		"\1\0\0\0\u00fe\u00f8\1\0\0\0\u00fe\u00fa\1\0\0\0\u00fe\u00fc\1\0\0\0\u00ff"+
		"\5\1\0\0\0\u0100\u0102\5C\0\0\u0102\u0104\3\b\4\0\u0104\u0106\5D\0\0\u0106"+
		"\u0107\3\n\5\0\u0107\7\1\0\0\0\u0108\u0109\3v;\0\u0109\t\1\0\0\0\u010a"+
		"\u010b\3\0\0\0\u010b\13\1\0\0\0\u010c\u010e\5C\0\0\u010e\u0110\5D\0\0"+
		"\u0110\u0111\3\n\5\0\u0111\r\1\0\0\0\u0112\u0114\5\30\0\0\u0114\u011c"+
		"\5E\0\0\u0116\u0118\3\20\b\0\u0118\u0119\5I\0\0\u0119\u011b\1\0\0\0\u011a"+
		"\u0116\1\0\0\0\u011b\u011e\1\0\0\0\u011c\u011a\1\0\0\0\u011c\u011d\1\0"+
		"\0\0\u011d\u0121\1\0\0\0\u011e\u011c\1\0\0\0\u011f\u0122\3\20\b\0\u0121"+
		"\u011f\1\0\0\0\u0121\u0122\1\0\0\0\u0122\u0123\1\0\0\0\u0123\u0124\5F"+
		"\0\0\u0124\17\1\0\0\0\u0125\u0127\3>\37\0\u0127\u0128\3\0\0\0\u0128\u012c"+
		"\1\0\0\0\u0129\u012c\3\22\t\0\u012b\u0125\1\0\0\0\u012b\u0129\1\0\0\0"+
		"\u012c\u012f\1\0\0\0\u012d\u0130\3\24\n\0\u012f\u012d\1\0\0\0\u012f\u0130"+
		"\1\0\0\0\u0130\21\1\0\0\0\u0131\u0134\5\36\0\0\u0133\u0131\1\0\0\0\u0133"+
		"\u0134\1\0\0\0\u0134\u0135\1\0\0\0\u0135\u0136\3\2\1\0\u0136\23\1\0\0"+
		"\0\u0137\u0138\5T\0\0\u0138\25\1\0\0\0\u0139\u013b\5\36\0\0\u013b\u013c"+
		"\3\30\f\0\u013c\27\1\0\0\0\u013d\u013e\3\0\0\0\u013e\31\1\0\0\0\u013f"+
		"\u0141\5\r\0\0\u0141\u0142\3\34\16\0\u0142\33\1\0\0\0\u0143\u0147\3 \20"+
		"\0\u0145\u0148\3\36\17\0\u0147\u0145\1\0\0\0\u0147\u0148\1\0\0\0\u0148"+
		"\35\1\0\0\0\u0149\u014e\3 \20\0\u014b\u014e\3\0\0\0\u014d\u0149\1\0\0"+
		"\0\u014d\u014b\1\0\0\0\u014e\37\1\0\0\0\u014f\u0157\5A\0\0\u0151\u0155"+
		"\3\"\21\0\u0153\u0156\5G\0\0\u0155\u0153\1\0\0\0\u0155\u0156\1\0\0\0\u0156"+
		"\u0158\1\0\0\0\u0157\u0151\1\0\0\0\u0157\u0158\1\0\0\0\u0158\u0159\1\0"+
		"\0\0\u0159\u015a\5B\0\0\u015a!\1\0\0\0\u015b\u0163\3$\22\0\u015d\u015f"+
		"\5G\0\0\u015f\u0162\3$\22\0\u0161\u015d\1\0\0\0\u0162\u0165\1\0\0\0\u0163"+
		"\u0161\1\0\0\0\u0163\u0164\1\0\0\0\u0164#\1\0\0\0\u0165\u0163\1\0\0\0"+
		"\u0166\u0169\3>\37\0\u0168\u0166\1\0\0\0\u0168\u0169\1\0\0\0\u0169\u016c"+
		"\1\0\0\0\u016a\u016d\5@\0\0\u016c\u016a\1\0\0\0\u016c\u016d\1\0\0\0\u016d"+
		"\u016e\1\0\0\0\u016e\u016f\3\0\0\0\u016f%\1\0\0\0\u0170\u0172\5\22\0\0"+
		"\u0172\u0183\5E\0\0\u0174\u017c\3(\24\0\u0176\u0178\5I\0\0\u0178\u017b"+
		"\3(\24\0\u017a\u0176\1\0\0\0\u017b\u017e\1\0\0\0\u017c\u017a\1\0\0\0\u017c"+
		"\u017d\1\0\0\0\u017d\u0181\1\0\0\0\u017e\u017c\1\0\0\0\u017f\u0182\5I"+
		"\0\0\u0181\u017f\1\0\0\0\u0181\u0182\1\0\0\0\u0182\u0184\1\0\0\0\u0183"+
		"\u0174\1\0\0\0\u0183\u0184\1\0\0\0\u0184\u0185\1\0\0\0\u0185\u0186\5F"+
		"\0\0\u0186\'\1\0\0\0\u0187\u0189\3*\25\0\u0189\u018a\3\34\16\0\u018a\u018e"+
		"\1\0\0\0\u018b\u018e\3,\26\0\u018d\u0187\1\0\0\0\u018d\u018b\1\0\0\0\u018e"+
		")\1\0\0\0\u018f\u0190\5K\0\0\u0190+\1\0\0\0\u0191\u0192\3\2\1\0\u0192"+
		"-\1\0\0\0\u0193\u0195\5\23\0\0\u0195\u0197\5C\0\0\u0197\u0199\3\60\30"+
		"\0\u0199\u019b\5D\0\0\u019b\u019c\3\n\5\0\u019c/\1\0\0\0\u019d\u019e\3"+
		"\0\0\0\u019e\61\1\0\0\0\u019f\u01a3\5\5\0\0\u01a1\u01a4\5\64\0\0\u01a3"+
		"\u01a1\1\0\0\0\u01a3\u01a4\1\0\0\0\u01a4\u01aa\1\0\0\0\u01a5\u01a7\5\64"+
		"\0\0\u01a7\u01aa\5\5\0\0\u01a9\u019f\1\0\0\0\u01a9\u01a5\1\0\0\0\u01aa"+
		"\u01ab\1\0\0\0\u01ab\u01ac\3\n\5\0\u01ac\63\1\0\0\0\u01ad\u01be\5E\0\0"+
		"\u01af\u01b7\3~?\0\u01b1\u01b3\5I\0\0\u01b3\u01b6\3~?\0\u01b5\u01b1\1"+
		"\0\0\0\u01b6\u01b9\1\0\0\0\u01b7\u01b5\1\0\0\0\u01b7\u01b8\1\0\0\0\u01b8"+
		"\u01bc\1\0\0\0\u01b9\u01b7\1\0\0\0\u01ba\u01bd\5I\0\0\u01bc\u01ba\1\0"+
		"\0\0\u01bc\u01bd\1\0\0\0\u01bd\u01bf\1\0\0\0\u01be\u01af\1\0\0\0\u01be"+
		"\u01bf\1\0\0\0\u01bf\u01c0\1\0\0\0\u01c0\u01c1\5F\0\0\u01c1\65\1\0\0\0"+
		"\u01c2\u01c9\3:\35\0\u01c4\u01c9\3B!\0\u01c6\u01c9\3F#\0\u01c8\u01c2\1"+
		"\0\0\0\u01c8\u01c4\1\0\0\0\u01c8\u01c6\1\0\0\0\u01c9\67\1\0\0\0\u01ca"+
		"\u01d1\3\66\33\0\u01cc\u01d1\3L&\0\u01ce\u01d1\3P(\0\u01d0\u01ca\1\0\0"+
		"\0\u01d0\u01cc\1\0\0\0\u01d0\u01ce\1\0\0\0\u01d19\1\0\0\0\u01d2\u01eb"+
		"\5\6\0\0\u01d4\u01ec\3<\36\0\u01d6\u01e7\5A\0\0\u01d8\u01e0\3<\36\0\u01da"+
		"\u01dc\5I\0\0\u01dc\u01df\3<\36\0\u01de\u01da\1\0\0\0\u01df\u01e2\1\0"+
		"\0\0\u01e0\u01de\1\0\0\0\u01e0\u01e1\1\0\0\0\u01e1\u01e5\1\0\0\0\u01e2"+
		"\u01e0\1\0\0\0\u01e3\u01e6\5I\0\0\u01e5\u01e3\1\0\0\0\u01e5\u01e6\1\0"+
		"\0\0\u01e6\u01e8\1\0\0\0\u01e7\u01d8\1\0\0\0\u01e7\u01e8\1\0\0\0\u01e8"+
		"\u01e9\1\0\0\0\u01e9\u01ec\5B\0\0\u01eb\u01d4\1\0\0\0\u01eb\u01d6\1\0"+
		"\0\0\u01ec;\1\0\0\0\u01ed\u01f7\3>\37\0\u01ef\u01f2\3\0\0\0\u01f1\u01ef"+
		"\1\0\0\0\u01f1\u01f2\1\0\0\0\u01f2\u01f3\1\0\0\0\u01f3\u01f5\5:\0\0\u01f5"+
		"\u01f8\3@ \0\u01f7\u01f1\1\0\0\0\u01f7\u01f8\1\0\0\0\u01f8=\1\0\0\0\u01f9"+
		"\u0201\5K\0\0\u01fb\u01fd\5G\0\0\u01fd\u0200\5K\0\0\u01ff\u01fb\1\0\0"+
		"\0\u0200\u0203\1\0\0\0\u0201\u01ff\1\0\0\0\u0201\u0202\1\0\0\0\u0202?"+
		"\1\0\0\0\u0203\u0201\1\0\0\0\u0204\u020c\3v;\0\u0206\u0208\5G\0\0\u0208"+
		"\u020b\3v;\0\u020a\u0206\1\0\0\0\u020b\u020e\1\0\0\0\u020c\u020a\1\0\0"+
		"\0\u020c\u020d\1\0\0\0\u020dA\1\0\0\0\u020e\u020c\1\0\0\0\u020f\u0228"+
		"\5\32\0\0\u0211\u0229\3D\"\0\u0213\u0224\5A\0\0\u0215\u021d\3D\"\0\u0217"+
		"\u0219\5I\0\0\u0219\u021c\3D\"\0\u021b\u0217\1\0\0\0\u021c\u021f\1\0\0"+
		"\0\u021d\u021b\1\0\0\0\u021d\u021e\1\0\0\0\u021e\u0222\1\0\0\0\u021f\u021d"+
		"\1\0\0\0\u0220\u0223\5I\0\0\u0222\u0220\1\0\0\0\u0222\u0223\1\0\0\0\u0223"+
		"\u0225\1\0\0\0\u0224\u0215\1\0\0\0\u0224\u0225\1\0\0\0\u0225\u0226\1\0"+
		"\0\0\u0226\u0229\5B\0\0\u0228\u0211\1\0\0\0\u0228\u0213\1\0\0\0\u0229"+
		"C\1\0\0\0\u022a\u022c\5K\0\0\u022c\u022d\3\0\0\0\u022dE\1\0\0\0\u022e"+
		"\u0247\5\33\0\0\u0230\u0248\3H$\0\u0232\u0243\5A\0\0\u0234\u023c\3H$\0"+
		"\u0236\u0238\5I\0\0\u0238\u023b\3H$\0\u023a\u0236\1\0\0\0\u023b\u023e"+
		"\1\0\0\0\u023c\u023a\1\0\0\0\u023c\u023d\1\0\0\0\u023d\u0241\1\0\0\0\u023e"+
		"\u023c\1\0\0\0\u023f\u0242\5I\0\0\u0241\u023f\1\0\0\0\u0241\u0242\1\0"+
		"\0\0\u0242\u0244\1\0\0\0\u0243\u0234\1\0\0\0\u0243\u0244\1\0\0\0\u0244"+
		"\u0245\1\0\0\0\u0245\u0248\5B\0\0\u0247\u0230\1\0\0\0\u0247\u0232\1\0"+
		"\0\0\u0248G\1\0\0\0\u0249\u0257\3>\37\0\u024b\u0251\3\0\0\0\u024d\u024f"+
		"\5:\0\0\u024f\u0252\3@ \0\u0251\u024d\1\0\0\0\u0251\u0252\1\0\0\0\u0252"+
		"\u0258\1\0\0\0\u0253\u0255\5:\0\0\u0255\u0258\3@ \0\u0257\u024b\1\0\0"+
		"\0\u0257\u0253\1\0\0\0\u0258I\1\0\0\0\u0259\u025b\3>\37\0\u025b\u025d"+
		"\5?\0\0\u025d\u025e\3@ \0\u025eK\1\0\0\0\u025f\u0261\5\r\0\0\u0261\u0263"+
		"\5K\0\0\u0263\u0267\3\34\16\0\u0265\u0268\3N\'\0\u0267\u0265\1\0\0\0\u0267"+
		"\u0268\1\0\0\0\u0268M\1\0\0\0\u0269\u026a\3\64\32\0\u026aO\1\0\0\0\u026b"+
		"\u026d\5\r\0\0\u026d\u026f\3R)\0\u026f\u0271\3*\25\0\u0271\u0275\3\34"+
		"\16\0\u0273\u0276\3N\'\0\u0275\u0273\1\0\0\0\u0275\u0276\1\0\0\0\u0276"+
		"Q\1\0\0\0\u0277\u027b\5A\0\0\u0279\u027c\5K\0\0\u027b\u0279\1\0\0\0\u027b"+
		"\u027c\1\0\0\0\u027c\u027f\1\0\0\0\u027d\u0280\5\36\0\0\u027f\u027d\1"+
		"\0\0\0\u027f\u0280\1\0\0\0\u0280\u0281\1\0\0\0\u0281\u0283\3T*\0\u0283"+
		"\u0284\5B\0\0\u0284S\1\0\0\0\u0285\u0286\5K\0\0\u0286U\1\0\0\0\u0287\u0294"+
		"\3X,\0\u0289\u0294\3\\.\0\u028b\u0294\3^/\0\u028d\u028f\5A\0\0\u028f\u0291"+
		"\3v;\0\u0291\u0292\5B\0\0\u0292\u0294\1\0\0\0\u0293\u0287\1\0\0\0\u0293"+
		"\u0289\1\0\0\0\u0293\u028b\1\0\0\0\u0293\u028d\1\0\0\0\u0294W\1\0\0\0"+
		"\u0295\u029c\3Z-\0\u0297\u029c\3b\61\0\u0299\u029c\3t:\0\u029b\u0295\1"+
		"\0\0\0\u029b\u0297\1\0\0\0\u029b\u0299\1\0\0\0\u029cY\1\0\0\0\u029d\u02a8"+
		"\5P\0\0\u029f\u02a8\5R\0\0\u02a1\u02a8\5Q\0\0\u02a3\u02a8\5S\0\0\u02a5"+
		"\u02a8\5T\0\0\u02a7\u029d\1\0\0\0\u02a7\u029f\1\0\0\0\u02a7\u02a1\1\0"+
		"\0\0\u02a7\u02a3\1\0\0\0\u02a7\u02a5\1\0\0\0\u02a8[\1\0\0\0\u02a9\u02ab"+
		"\3\u00d8l\0\u02ab\u02ac\5H\0\0\u02ac\u02ae\1\0\0\0\u02ad\u02a9\1\0\0\0"+
		"\u02ad\u02ae\1\0\0\0\u02ae\u02af\1\0\0\0\u02af\u02b0\5K\0\0\u02b0]\1\0"+
		"\0\0\u02b1\u02b3\3`\60\0\u02b3\u02b5\5H\0\0\u02b5\u02b6\3*\25\0\u02b6"+
		"_\1\0\0\0\u02b7\u02c2\3\2\1\0\u02b9\u02bb\5A\0\0\u02bb\u02bd\5\36\0\0"+
		"\u02bd\u02bf\3\2\1\0\u02bf\u02c0\5B\0\0\u02c0\u02c2\1\0\0\0\u02c1\u02b7"+
		"\1\0\0\0\u02c1\u02b9\1\0\0\0\u02c2a\1\0\0\0\u02c3\u02c5\3d\62\0\u02c5"+
		"\u02c6\3f\63\0\u02c6c\1\0\0\0\u02c7\u02da\3\16\7\0\u02c9\u02da\3\6\3\0"+
		"\u02cb\u02cd\5C\0\0\u02cd\u02cf\5@\0\0\u02cf\u02d1\5D\0\0\u02d1\u02da"+
		"\3\n\5\0\u02d3\u02da\3\f\6\0\u02d5\u02da\3.\27\0\u02d7\u02da\3\2\1\0\u02d9"+
		"\u02c7\1\0\0\0\u02d9\u02c9\1\0\0\0\u02d9\u02cb\1\0\0\0\u02d9\u02d3\1\0"+
		"\0\0\u02d9\u02d5\1\0\0\0\u02d9\u02d7\1\0\0\0\u02dae\1\0\0\0\u02db\u02e3"+
		"\5E\0\0\u02dd\u02e1\3h\64\0\u02df\u02e2\5G\0\0\u02e1\u02df\1\0\0\0\u02e1"+
		"\u02e2\1\0\0\0\u02e2\u02e4\1\0\0\0\u02e3\u02dd\1\0\0\0\u02e3\u02e4\1\0"+
		"\0\0\u02e4\u02e5\1\0\0\0\u02e5\u02e6\5F\0\0\u02e6g\1\0\0\0\u02e7\u02ef"+
		"\3j\65\0\u02e9\u02eb\5G\0\0\u02eb\u02ee\3j\65\0\u02ed\u02e9\1\0\0\0\u02ee"+
		"\u02f1\1\0\0\0\u02ef\u02ed\1\0\0\0\u02ef\u02f0\1\0\0\0\u02f0i\1\0\0\0"+
		"\u02f1\u02ef\1\0\0\0\u02f2\u02f4\3l\66\0\u02f4\u02f5\5J\0\0\u02f5\u02f7"+
		"\1\0\0\0\u02f6\u02f2\1\0\0\0\u02f6\u02f7\1\0\0\0\u02f7\u02f8\1\0\0\0\u02f8"+
		"\u02f9\3r9\0\u02f9k\1\0\0\0\u02fa\u02ff\3n\67\0\u02fc\u02ff\3p8\0\u02fe"+
		"\u02fa\1\0\0\0\u02fe\u02fc\1\0\0\0\u02ffm\1\0\0\0\u0300\u0301\5K\0\0\u0301"+
		"o\1\0\0\0\u0302\u0303\3v;\0\u0303q\1\0\0\0\u0304\u0309\3v;\0\u0306\u0309"+
		"\3f\63\0\u0308\u0304\1\0\0\0\u0308\u0306\1\0\0\0\u0309s\1\0\0\0\u030a"+
		"\u030c\3\32\r\0\u030c\u030d\3N\'\0\u030du\1\0\0\0\u030e\u0310\5\34\0\0"+
		"\u0310\u0331\3v;\0\u0312\u0314\5\35\0\0\u0314\u0331\3v;\0\u0316\u0318"+
		"\5;\0\0\u0318\u0331\3v;\0\u031a\u031c\5#\0\0\u031c\u0331\3v;\0\u031e\u0320"+
		"\5\36\0\0\u0320\u0331\3v;\0\u0322\u0324\5!\0\0\u0324\u0331\3v;\0\u0326"+
		"\u0328\5\64\0\0\u0328\u0331\3v;\0\u032a\u0331\3V+\0\u032c\u0331\3|>\0"+
		"\u032e\u0331\3\u00d0h\0\u0330\u030e\1\0\0\0\u0330\u0312\1\0\0\0\u0330"+
		"\u0316\1\0\0\0\u0330\u031a\1\0\0\0\u0330\u031e\1\0\0\0\u0330\u0322\1\0"+
		"\0\0\u0330\u0326\1\0\0\0\u0330\u032a\1\0\0\0\u0330\u032c\1\0\0\0\u0330"+
		"\u032e\1\0\0\0\u0331\u0388\1\0\0\0\u0332\u0334\4;\0\1\u0334\u0336\7\0"+
		"\0\0\u0336\u0387\3v;\0\u0338\u033a\4;\1\1\u033a\u033c\7\1\0\0\u033c\u0387"+
		"\3v;\0\u033e\u0340\4;\2\1\u0340\u0342\7\2\0\0\u0342\u0387\3v;\0\u0344"+
		"\u0346\4;\3\1\u0346\u0348\5\62\0\0\u0348\u0387\3v;\0\u034a\u034c\4;\4"+
		"\1\u034c\u034e\5\63\0\0\u034e\u0387\3v;\0\u0350\u0352\4;\5\1\u0352\u0354"+
		"\5H\0\0\u0354\u0387\5K\0\0\u0356\u0358\4;\6\1\u0358\u035a\5C\0\0\u035a"+
		"\u035c\3v;\0\u035c\u035d\5D\0\0\u035d\u0387\1\0\0\0\u035e\u0360\4;\7\1"+
		"\u0360\u0364\5C\0\0\u0362\u0365\3v;\0\u0364\u0362\1\0\0\0\u0364\u0365"+
		"\1\0\0\0\u0365\u0366\1\0\0\0\u0366\u036a\5J\0\0\u0368\u036b\3v;\0\u036a"+
		"\u0368\1\0\0\0\u036a\u036b\1\0\0\0\u036b\u036c\1\0\0\0\u036c\u0387\5D"+
		"\0\0\u036e\u0370\4;\b\1\u0370\u0372\5H\0\0\u0372\u0374\5A\0\0\u0374\u0376"+
		"\3\0\0\0\u0376\u0377\5B\0\0\u0377\u0387\1\0\0\0\u0378\u037a\4;\t\1\u037a"+
		"\u0382\5A\0\0\u037c\u0380\3z=\0\u037e\u0381\5G\0\0\u0380\u037e\1\0\0\0"+
		"\u0380\u0381\1\0\0\0\u0381\u0383\1\0\0\0\u0382\u037c\1\0\0\0\u0382\u0383"+
		"\1\0\0\0\u0383\u0384\1\0\0\0\u0384\u0387\5B\0\0\u0386\u0332\1\0\0\0\u0386"+
		"\u0338\1\0\0\0\u0386\u033e\1\0\0\0\u0386\u0344\1\0\0\0\u0386\u034a\1\0"+
		"\0\0\u0386\u0350\1\0\0\0\u0386\u0356\1\0\0\0\u0386\u035e\1\0\0\0\u0386"+
		"\u036e\1\0\0\0\u0386\u0378\1\0\0\0\u0387\u038a\1\0\0\0\u0388\u0386\1\0"+
		"\0\0\u0388\u0389\1\0\0\0\u0389w\1\0\0\0\u038a\u0388\1\0\0\0\u038b\u0392"+
		"\3V+\0\u038d\u0392\3|>\0\u038f\u0392\3\u00d0h\0\u0391\u038b\1\0\0\0\u0391"+
		"\u038d\1\0\0\0\u0391\u038f\1\0\0\0\u0392\u03cb\1\0\0\0\u0393\u0395\4<"+
		"\n\1\u0395\u0397\5H\0\0\u0397\u03ca\5K\0\0\u0399\u039b\4<\13\1\u039b\u039d"+
		"\5C\0\0\u039d\u039f\3v;\0\u039f\u03a0\5D\0\0\u03a0\u03ca\1\0\0\0\u03a1"+
		"\u03a3\4<\f\1\u03a3\u03a7\5C\0\0\u03a5\u03a8\3v;\0\u03a7\u03a5\1\0\0\0"+
		"\u03a7\u03a8\1\0\0\0\u03a8\u03a9\1\0\0\0\u03a9\u03ad\5J\0\0\u03ab\u03ae"+
		"\3v;\0\u03ad\u03ab\1\0\0\0\u03ad\u03ae\1\0\0\0\u03ae\u03af\1\0\0\0\u03af"+
		"\u03ca\5D\0\0\u03b1\u03b3\4<\r\1\u03b3\u03b5\5H\0\0\u03b5\u03b7\5A\0\0"+
		"\u03b7\u03b9\3\0\0\0\u03b9\u03ba\5B\0\0\u03ba\u03ca\1\0\0\0\u03bb\u03bd"+
		"\4<\16\1\u03bd\u03c5\5A\0\0\u03bf\u03c3\3z=\0\u03c1\u03c4\5G\0\0\u03c3"+
		"\u03c1\1\0\0\0\u03c3\u03c4\1\0\0\0\u03c4\u03c6\1\0\0\0\u03c5\u03bf\1\0"+
		"\0\0\u03c5\u03c6\1\0\0\0\u03c6\u03c7\1\0\0\0\u03c7\u03ca\5B\0\0\u03c9"+
		"\u0393\1\0\0\0\u03c9\u0399\1\0\0\0\u03c9\u03a1\1\0\0\0\u03c9\u03b1\1\0"+
		"\0\0\u03c9\u03bb\1\0\0\0\u03ca\u03cd\1\0\0\0\u03cb\u03c9\1\0\0\0\u03cb"+
		"\u03cc\1\0\0\0\u03ccy\1\0\0\0\u03cd\u03cb\1\0\0\0\u03ce\u03d2\3@ \0\u03d0"+
		"\u03d3\5@\0\0\u03d2\u03d0\1\0\0\0\u03d2\u03d3\1\0\0\0\u03d3{\1\0\0\0\u03d4"+
		"\u03d6\3\0\0\0\u03d6\u03d8\5A\0\0\u03d8\u03da\3v;\0\u03da\u03db\5B\0\0"+
		"\u03db}\1\0\0\0\u03dc\u03fb\3\66\33\0\u03de\u03fb\3\u0084B\0\u03e0\u03fb"+
		"\3\u0080@\0\u03e2\u03fb\3\u00b8\\\0\u03e4\u03fb\3\u00c4b\0\u03e6\u03fb"+
		"\3\u00c6c\0\u03e8\u03fb\3\u00c8d\0\u03ea\u03fb\3\u00cae\0\u03ec\u03fb"+
		"\3\u00ccf\0\u03ee\u03fb\3\64\32\0\u03f0\u03fb\3\u0098L\0\u03f2\u03fb\3"+
		"\u009aM\0\u03f4\u03fb\3\u00ba]\0\u03f6\u03fb\3\u00acV\0\u03f8\u03fb\3"+
		"\u00ceg\0\u03fa\u03dc\1\0\0\0\u03fa\u03de\1\0\0\0\u03fa\u03e0\1\0\0\0"+
		"\u03fa\u03e2\1\0\0\0\u03fa\u03e4\1\0\0\0\u03fa\u03e6\1\0\0\0\u03fa\u03e8"+
		"\1\0\0\0\u03fa\u03ea\1\0\0\0\u03fa\u03ec\1\0\0\0\u03fa\u03ee\1\0\0\0\u03fa"+
		"\u03f0\1\0\0\0\u03fa\u03f2\1\0\0\0\u03fa\u03f4\1\0\0\0\u03fa\u03f6\1\0"+
		"\0\0\u03fa\u03f8\1\0\0\0\u03fb\177\1\0\0\0\u03fc\u0409\3\u0082A\0\u03fe"+
		"\u0409\3\u0088D\0\u0400\u0409\3\u008aE\0\u0402\u0409\3\u008eG\0\u0404"+
		"\u0409\3\u0090H\0\u0406\u0409\3J%\0\u0408\u03fc\1\0\0\0\u0408\u03fe\1"+
		"\0\0\0\u0408\u0400\1\0\0\0\u0408\u0402\1\0\0\0\u0408\u0404\1\0\0\0\u0408"+
		"\u0406\1\0\0\0\u0409\u0081\1\0\0\0\u040a\u040b\1\0\0\0\u040b\u0083\1\0"+
		"\0\0\u040c\u040e\3\u0086C\0\u040e\u0410\5J\0\0\u0410\u0411\3~?\0\u0411"+
		"\u0085\1\0\0\0\u0412\u0413\5K\0\0\u0413\u0087\1\0\0\0\u0414\u0415\3v;"+
		"\0\u0415\u0089\1\0\0\0\u0416\u0418\3\u008cF\0\u0418\u041a\5\64\0\0\u041a"+
		"\u041b\3v;\0\u041b\u008b\1\0\0\0\u041c\u041d\3v;\0\u041d\u008d\1\0\0\0"+
		"\u041e\u0420\3v;\0\u0420\u0421\7\3\0\0\u0421\u008f\1\0\0\0\u0422\u0424"+
		"\3@ \0\u0424\u0426\3\u0092I\0\u0426\u0427\3@ \0\u0427\u0091\1\0\0\0\u0428"+
		"\u042f\3\u0094J\0\u042a\u042f\3\u0096K\0\u042c\u042f\5:\0\0\u042e\u0428"+
		"\1\0\0\0\u042e\u042a\1\0\0\0\u042e\u042c\1\0\0\0\u042f\u0093\1\0\0\0\u0430"+
		"\u0439\5\'\0\0\u0432\u0439\5(\0\0\u0434\u0439\5-\0\0\u0436\u0439\5.\0"+
		"\0\u0438\u0430\1\0\0\0\u0438\u0432\1\0\0\0\u0438\u0434\1\0\0\0\u0438\u0436"+
		"\1\0\0\0\u0439\u0095\1\0\0\0\u043a\u0449\5)\0\0\u043c\u0449\5*\0\0\u043e"+
		"\u0449\5.\0\0\u0440\u0449\5/\0\0\u0442\u0449\5\60\0\0\u0444\u0449\5,\0"+
		"\0\u0446\u0449\5\61\0\0\u0448\u043a\1\0\0\0\u0448\u043c\1\0\0\0\u0448"+
		"\u043e\1\0\0\0\u0448\u0440\1\0\0\0\u0448\u0442\1\0\0\0\u0448\u0444\1\0"+
		"\0\0\u0448\u0446\1\0\0\0\u0449\u0097\1\0\0\0\u044a\u0450\5\20\0\0\u044c"+
		"\u044e\3\u0080@\0\u044e\u044f\5I\0\0\u044f\u0451\1\0\0\0\u0450\u044c\1"+
		"\0\0\0\u0450\u0451\1\0\0\0\u0451\u0452\1\0\0\0\u0452\u0454\3v;\0\u0454"+
		"\u045e\3\64\32\0\u0456\u045c\5\n\0\0\u0458\u045d\3\u0098L\0\u045a\u045d"+
		"\3\64\32\0\u045c\u0458\1\0\0\0\u045c\u045a\1\0\0\0\u045d\u045f\1\0\0\0"+
		"\u045e\u0456\1\0\0\0\u045e\u045f\1\0\0\0\u045f\u0099\1\0\0\0\u0460\u0465"+
		"\3\u009cN\0\u0462\u0465\3\u00a2Q\0\u0464\u0460\1\0\0\0\u0464\u0462\1\0"+
		"\0\0\u0465\u009b\1\0\0\0\u0466\u046c\5\31\0\0\u0468\u046a\3\u0080@\0\u046a"+
		"\u046b\5I\0\0\u046b\u046d\1\0\0\0\u046c\u0468\1\0\0\0\u046c\u046d\1\0"+
		"\0\0\u046d\u0470\1\0\0\0\u046e\u0471\3v;\0\u0470\u046e\1\0\0\0\u0470\u0471"+
		"\1\0\0\0\u0471\u0472\1\0\0\0\u0472\u0478\5E\0\0\u0474\u0477\3\u009eO\0"+
		"\u0476\u0474\1\0\0\0\u0477\u047a\1\0\0\0\u0478\u0476\1\0\0\0\u0478\u0479"+
		"\1\0\0\0\u0479\u047b\1\0\0\0\u047a\u0478\1\0\0\0\u047b\u047c\5F\0\0\u047c"+
		"\u009d\1\0\0\0\u047d\u047f\3\u00a0P\0\u047f\u0490\5J\0\0\u0481\u0489\3"+
		"~?\0\u0483\u0485\5I\0\0\u0485\u0488\3~?\0\u0487\u0483\1\0\0\0\u0488\u048b"+
		"\1\0\0\0\u0489\u0487\1\0\0\0\u0489\u048a\1\0\0\0\u048a\u048e\1\0\0\0\u048b"+
		"\u0489\1\0\0\0\u048c\u048f\5I\0\0\u048e\u048c\1\0\0\0\u048e\u048f\1\0"+
		"\0\0\u048f\u0491\1\0\0\0\u0490\u0481\1\0\0\0\u0490\u0491\1\0\0\0\u0491"+
		"\u009f\1\0\0\0\u0492\u0494\5\4\0\0\u0494\u0499\3@ \0\u0496\u0499\5\b\0"+
		"\0\u0498\u0492\1\0\0\0\u0498\u0496\1\0\0\0\u0499\u00a1\1\0\0\0\u049a\u04a0"+
		"\5\31\0\0\u049c\u049e\3\u0080@\0\u049e\u049f\5I\0\0\u049f\u04a1\1\0\0"+
		"\0\u04a0\u049c\1\0\0\0\u04a0\u04a1\1\0\0\0\u04a1\u04a2\1\0\0\0\u04a2\u04a4"+
		"\3\u00a4R\0\u04a4\u04aa\5E\0\0\u04a6\u04a9\3\u00a6S\0\u04a8\u04a6\1\0"+
		"\0\0\u04a9\u04ac\1\0\0\0\u04aa\u04a8\1\0\0\0\u04aa\u04ab\1\0\0\0\u04ab"+
		"\u04ad\1\0\0\0\u04ac\u04aa\1\0\0\0\u04ad\u04ae\5F\0\0\u04ae\u00a3\1\0"+
		"\0\0\u04af\u04b1\5K\0\0\u04b1\u04b4\5?\0\0\u04b3\u04af\1\0\0\0\u04b3\u04b4"+
		"\1\0\0\0\u04b4\u04b5\1\0\0\0\u04b5\u04b7\3x<\0\u04b7\u04b9\5H\0\0\u04b9"+
		"\u04bb\5A\0\0\u04bb\u04bd\5\32\0\0\u04bd\u04be\5B\0\0\u04be\u00a5\1\0"+
		"\0\0\u04bf\u04c1\3\u00a8T\0\u04c1\u04d2\5J\0\0\u04c3\u04cb\3~?\0\u04c5"+
		"\u04c7\5I\0\0\u04c7\u04ca\3~?\0\u04c9\u04c5\1\0\0\0\u04ca\u04cd\1\0\0"+
		"\0\u04cb\u04c9\1\0\0\0\u04cb\u04cc\1\0\0\0\u04cc\u04d0\1\0\0\0\u04cd\u04cb"+
		"\1\0\0\0\u04ce\u04d1\5I\0\0\u04d0\u04ce\1\0\0\0\u04d0\u04d1\1\0\0\0\u04d1"+
		"\u04d3\1\0\0\0\u04d2\u04c3\1\0\0\0\u04d2\u04d3\1\0\0\0\u04d3\u00a7\1\0"+
		"\0\0\u04d4\u04d6\5\4\0\0\u04d6\u04db\3\u00aaU\0\u04d8\u04db\5\b\0\0\u04da"+
		"\u04d4\1\0\0\0\u04da\u04d8\1\0\0\0\u04db\u00a9\1\0\0\0\u04dc\u04e4\3\0"+
		"\0\0\u04de\u04e0\5G\0\0\u04e0\u04e3\3\0\0\0\u04e2\u04de\1\0\0\0\u04e3"+
		"\u04e6\1\0\0\0\u04e4\u04e2\1\0\0\0\u04e4\u04e5\1\0\0\0\u04e5\u00ab\1\0"+
		"\0\0\u04e6\u04e4\1\0\0\0\u04e7\u04ef\5\f\0\0\u04e9\u04f0\3\u00aeW\0\u04eb"+
		"\u04f0\3\u00b0X\0\u04ed\u04f0\3\u00b6[\0\u04ef\u04e9\1\0\0\0\u04ef\u04eb"+
		"\1\0\0\0\u04ef\u04ed\1\0\0\0\u04ef\u04f0\1\0\0\0\u04f0\u04f1\1\0\0\0\u04f1"+
		"\u04f2\3\64\32\0\u04f2\u00ad\1\0\0\0\u04f3\u04f4\3v;\0\u04f4\u00af\1\0"+
		"\0\0\u04f5\u04f8\3\u00b2Y\0\u04f7\u04f5\1\0\0\0\u04f7\u04f8\1\0\0\0\u04f8"+
		"\u04f9\1\0\0\0\u04f9\u04fd\5I\0\0\u04fb\u04fe\3\u00aeW\0\u04fd\u04fb\1"+
		"\0\0\0\u04fd\u04fe\1\0\0\0\u04fe\u04ff\1\0\0\0\u04ff\u0503\5I\0\0\u0501"+
		"\u0504\3\u00b4Z\0\u0503\u0501\1\0\0\0\u0503\u0504\1\0\0\0\u0504\u00b1"+
		"\1\0\0\0\u0505\u0506\3\u0080@\0\u0506\u00b3\1\0\0\0\u0507\u0508\3\u0080"+
		"@\0\u0508\u00b5\1\0\0\0\u0509\u050f\3v;\0\u050b\u050d\5G\0\0\u050d\u0510"+
		"\3v;\0\u050f\u050b\1\0\0\0\u050f\u0510\1\0\0\0\u0510\u0511\1\0\0\0\u0511"+
		"\u0513\7\4\0\0\u0513\u0515\5\25\0\0\u0515\u0516\3v;\0\u0516\u00b7\1\0"+
		"\0\0\u0517\u0519\5\16\0\0\u0519\u051a\3v;\0\u051a\u00b9\1\0\0\0\u051b"+
		"\u051d\5\27\0\0\u051d\u0523\5E\0\0\u051f\u0522\3\u00bc^\0\u0521\u051f"+
		"\1\0\0\0\u0522\u0525\1\0\0\0\u0523\u0521\1\0\0\0\u0523\u0524\1\0\0\0\u0524"+
		"\u0526\1\0\0\0\u0525\u0523\1\0\0\0\u0526\u0527\5F\0\0\u0527\u00bb\1\0"+
		"\0\0\u0528\u052a\3\u00be_\0\u052a\u053b\5J\0\0\u052c\u0534\3~?\0\u052e"+
		"\u0530\5I\0\0\u0530\u0533\3~?\0\u0532\u052e\1\0\0\0\u0533\u0536\1\0\0"+
		"\0\u0534\u0532\1\0\0\0\u0534\u0535\1\0\0\0\u0535\u0539\1\0\0\0\u0536\u0534"+
		"\1\0\0\0\u0537\u053a\5I\0\0\u0539\u0537\1\0\0\0\u0539\u053a\1\0\0\0\u053a"+
		"\u053c\1\0\0\0\u053b\u052c\1\0\0\0\u053b\u053c\1\0\0\0\u053c\u00bd\1\0"+
		"\0\0\u053d\u0543\5\4\0\0\u053f\u0544\3\u008aE\0\u0541\u0544\3\u00c0`\0"+
		"\u0543\u053f\1\0\0\0\u0543\u0541\1\0\0\0\u0544\u0548\1\0\0\0\u0545\u0548"+
		"\5\b\0\0\u0547\u053d\1\0\0\0\u0547\u0545\1\0\0\0\u0548\u00bf\1\0\0\0\u0549"+
		"\u054f\3v;\0\u054b\u054d\5G\0\0\u054d\u0550\3v;\0\u054f\u054b\1\0\0\0"+
		"\u054f\u0550\1\0\0\0\u0550\u0551\1\0\0\0\u0551\u0552\7\5\0\0\u0552\u0554"+
		"\1\0\0\0\u0553\u0549\1\0\0\0\u0553\u0554\1\0\0\0\u0554\u0555\1\0\0\0\u0555"+
		"\u0556\3\u00c2a\0\u0556\u00c1\1\0\0\0\u0557\u0558\3v;\0\u0558\u00c3\1"+
		"\0\0\0\u0559\u055d\5\26\0\0\u055b\u055e\3@ \0\u055d\u055b\1\0\0\0\u055d"+
		"\u055e\1\0\0\0\u055e\u00c5\1\0\0\0\u055f\u0563\5\3\0\0\u0561\u0564\3\u0086"+
		"C\0\u0563\u0561\1\0\0\0\u0563\u0564\1\0\0\0\u0564\u00c7\1\0\0\0\u0565"+
		"\u0569\5\7\0\0\u0567\u056a\3\u0086C\0\u0569\u0567\1\0\0\0\u0569\u056a"+
		"\1\0\0\0\u056a\u00c9\1\0\0\0\u056b\u056d\5\17\0\0\u056d\u056e\3\u0086"+
		"C\0\u056e\u00cb\1\0\0\0\u056f\u0570\5\13\0\0\u0570\u00cd\1\0\0\0\u0571"+
		"\u0573\5\t\0\0\u0573\u0574\3v;\0\u0574\u00cf\1\0\0\0\u0575\u0577\5K\0"+
		"\0\u0577\u057f\5A\0\0\u0579\u057d\3\u00d2i\0\u057b\u057e\5G\0\0\u057d"+
		"\u057b\1\0\0\0\u057d\u057e\1\0\0\0\u057e\u0580\1\0\0\0\u057f\u0579\1\0"+
		"\0\0\u057f\u0580\1\0\0\0\u0580\u0581\1\0\0\0\u0581\u0582\5B\0\0\u0582"+
		"\u00d1\1\0\0\0\u0583\u0589\3\0\0\0\u0585\u0587\5G\0\0\u0587\u058a\3@ "+
		"\0\u0589\u0585\1\0\0\0\u0589\u058a\1\0\0\0\u058a\u058e\1\0\0\0\u058b\u058e"+
		"\3@ \0\u058d\u0583\1\0\0\0\u058d\u058b\1\0\0\0\u058e\u00d3\1\0\0\0\u058f"+
		"\u0591\3\u00d6k\0\u0591\u0599\5I\0\0\u0593\u0595\3\u00dam\0\u0595\u0596"+
		"\5I\0\0\u0596\u0598\1\0\0\0\u0597\u0593\1\0\0\0\u0598\u059b\1\0\0\0\u0599"+
		"\u0597\1\0\0\0\u0599\u059a\1\0\0\0\u059a\u05a2\1\0\0\0\u059b\u0599\1\0"+
		"\0\0\u059c\u059e\38\34\0\u059e\u059f\5I\0\0\u059f\u05a1\1\0\0\0\u05a0"+
		"\u059c\1\0\0\0\u05a1\u05a4\1\0\0\0\u05a2\u05a0\1\0\0\0\u05a2\u05a3\1\0"+
		"\0\0\u05a3\u00d5\1\0\0\0\u05a4\u05a2\1\0\0\0\u05a5\u05a7\5\24\0\0\u05a7"+
		"\u05a8\3\u00d8l\0\u05a8\u00d7\1\0\0\0\u05a9\u05aa\5K\0\0\u05aa\u00d9\1"+
		"\0\0\0\u05ab\u05c4\5\21\0\0\u05ad\u05c5\3\u00dcn\0\u05af\u05c0\5A\0\0"+
		"\u05b1\u05b9\3\u00dcn\0\u05b3\u05b5\5I\0\0\u05b5\u05b8\3\u00dcn\0\u05b7"+
		"\u05b3\1\0\0\0\u05b8\u05bb\1\0\0\0\u05b9\u05b7\1\0\0\0\u05b9\u05ba\1\0"+
		"\0\0\u05ba\u05be\1\0\0\0\u05bb\u05b9\1\0\0\0\u05bc\u05bf\5I\0\0\u05be"+
		"\u05bc\1\0\0\0\u05be\u05bf\1\0\0\0\u05bf\u05c1\1\0\0\0\u05c0\u05b1\1\0"+
		"\0\0\u05c0\u05c1\1\0\0\0\u05c1\u05c2\1\0\0\0\u05c2\u05c5\5B\0\0\u05c4"+
		"\u05ad\1\0\0\0\u05c4\u05af\1\0\0\0\u05c5\u00db\1\0\0\0\u05c6\u05cb\5H"+
		"\0\0\u05c8\u05cb\3\u00d8l\0\u05ca\u05c6\1\0\0\0\u05ca\u05c8\1\0\0\0\u05ca"+
		"\u05cb\1\0\0\0\u05cb\u05cc\1\0\0\0\u05cc\u05cd\3\u00deo\0\u05cd\u00dd"+
		"\1\0\0\0\u05ce\u05cf\5T\0\0\u05cf\u00df\1\0\0\0}\u00ea\1\u00fe\1\u011c"+
		"\1\u0121\1\u012b\1\u012f\1\u0133\1\u0147\1\u014d\1\u0155\1\u0157\1\u0163"+
		"\1\u0168\1\u016c\1\u017c\1\u0181\1\u0183\1\u018d\1\u01a3\1\u01a9\1\u01b7"+
		"\1\u01bc\1\u01be\1\u01c8\1\u01d0\1\u01e0\1\u01e5\1\u01e7\1\u01eb\1\u01f1"+
		"\1\u01f7\1\u0201\1\u020c\1\u021d\1\u0222\1\u0224\1\u0228\1\u023c\1\u0241"+
		"\1\u0243\1\u0247\1\u0251\1\u0257\1\u0267\1\u0275\1\u027b\1\u027f\1\u0293"+
		"\1\u029b\1\u02a7\1\u02ad\1\u02c1\1\u02d9\1\u02e1\1\u02e3\1\u02ef\1\u02f6"+
		"\1\u02fe\1\u0308\1\u0330\1\u0364\1\u036a\1\u0380\1\u0382\1\u0386\1\u0388"+
		"\1\u0391\1\u03a7\1\u03ad\1\u03c3\1\u03c5\1\u03c9\1\u03cb\1\u03d2\1\u03fa"+
		"\1\u0408\1\u042e\1\u0438\1\u0448\1\u0450\1\u045c\1\u045e\1\u0464\1\u046c"+
		"\1\u0470\1\u0478\1\u0489\1\u048e\1\u0490\1\u0498\1\u04a0\1\u04aa\1\u04b3"+
		"\1\u04cb\1\u04d0\1\u04d2\1\u04da\1\u04e4\1\u04ef\1\u04f7\1\u04fd\1\u0503"+
		"\1\u050f\1\u0523\1\u0534\1\u0539\1\u053b\1\u0543\1\u0547\1\u054f\1\u0553"+
		"\1\u055d\1\u0563\1\u0569\1\u057d\1\u057f\1\u0589\1\u058d\1\u0599\1\u05a2"+
		"\1\u05b9\1\u05be\1\u05c0\1\u05c4\1\u05ca\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}