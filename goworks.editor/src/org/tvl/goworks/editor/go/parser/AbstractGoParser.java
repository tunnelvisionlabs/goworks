// $ANTLR ANTLRVersion> AbstractGoParser.java generatedTimestamp>
/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public abstract class AbstractGoParser extends Parser<Token> {
	public static final int
		Switch=24, Pipe=33, PipeEqual=44, LessThan=55, GreaterEqual=61, Goto=14, 
		Go=13, LessEqual=60, AmpEqual=43, Fallthrough=10, BangEqual=59, Case=3, 
		GreaterThan=56, Func=12, Percent=31, LeftArrow=51, IMAGINARY_LITERAL=79, 
		Default=7, ML_COMMENT=78, StarEqual=40, Map=18, CharLiteral=81, IDENTIFIER=74, 
		Amp=32, Chan=4, Ellipsis=63, Interface=17, ANYCHAR=83, Select=22, AmpCaretEqual=48, 
		Or=50, COMMENT=77, Return=21, Struct=23, If=15, Caret=34, PercentEqual=42, 
		RightShiftEqual=47, And=49, Import=16, INT_LITERAL=1, Type=25, PlusEqual=38, 
		Continue=6, MinusEqual=39, ColonEqual=62, LeftShiftEqual=46, Colon=73, 
		LeftShift=35, EqualEqual=54, Const=5, Equal=57, Dec=53, Package=19, For=11, 
		RightShift=36, StringLiteral=82, FLOAT_LITERAL=80, CaretEqual=45, Range=20, 
		Plus=27, Bang=58, Minus=28, WS=75, Semi=72, NEWLINE=76, Break=2, Inc=52, 
		LeftBrace=68, SlashEqual=41, Dot=71, LeftBrack=66, RightBrack=67, LeftParen=64, 
		Defer=8, Star=29, RightParen=65, AmpCaret=37, Else=9, Comma=70, Var=26, 
		Slash=30, RightBrace=69;
	public static final String[] tokenNames = {
		"<INVALID>", "INT_LITERAL", "'break'", "'case'", "'chan'", "'const'", 
		"'continue'", "'default'", "'defer'", "'else'", "'fallthrough'", "'for'", 
		"'func'", "'go'", "'goto'", "'if'", "'import'", "'interface'", "'map'", 
		"'package'", "'range'", "'return'", "'select'", "'struct'", "'switch'", 
		"'type'", "'var'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", 
		"'<<'", "'>>'", "'&^'", "'+='", "'-='", "'*='", "'/='", "'%='", "'&='", 
		"'|='", "'^='", "'<<='", "'>>='", "'&^='", "'&&'", "'||'", "'<-'", "'++'", 
		"'--'", "'=='", "'<'", "'>'", "'='", "'!'", "'!='", "'<='", "'>='", "':='", 
		"'...'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "'.'", "';'", 
		"':'", "IDENTIFIER", "WS", "NEWLINE", "COMMENT", "ML_COMMENT", "IMAGINARY_LITERAL", 
		"FLOAT_LITERAL", "CharLiteral", "StringLiteral", "ANYCHAR"
	};
	public static final int
		RULE_channel$lf$expression = 0, RULE_sendStmt$lf$expression = 1, RULE_incDecStmt$lf$expression = 2, 
		RULE_expressionList$lf$expression = 3, RULE_assignment$lf$expression = 4, 
		RULE_expressionStmt$lf$expression = 5, RULE_type = 6, RULE_typeName = 7, 
		RULE_typeLiteral = 8, RULE_arrayType = 9, RULE_arrayLength = 10, RULE_elementType = 11, 
		RULE_sliceType = 12, RULE_structType = 13, RULE_fieldDecl = 14, RULE_anonymousField = 15, 
		RULE_tag = 16, RULE_pointerType = 17, RULE_baseType = 18, RULE_functionType = 19, 
		RULE_signature = 20, RULE_result = 21, RULE_parameters = 22, RULE_parameterList = 23, 
		RULE_parameterDecl = 24, RULE_interfaceType = 25, RULE_methodSpec = 26, 
		RULE_methodName = 27, RULE_interfaceTypeName = 28, RULE_mapType = 29, 
		RULE_keyType = 30, RULE_channelType = 31, RULE_block = 32, RULE_declaration = 33, 
		RULE_topLevelDecl = 34, RULE_constDecl = 35, RULE_constSpec = 36, RULE_identifierList = 37, 
		RULE_expressionList = 38, RULE_typeDecl = 39, RULE_typeSpec = 40, RULE_varDecl = 41, 
		RULE_varSpec = 42, RULE_shortVarDecl = 43, RULE_functionDecl = 44, RULE_body = 45, 
		RULE_methodDecl = 46, RULE_receiver = 47, RULE_baseTypeName = 48, RULE_operand = 49, 
		RULE_literal = 50, RULE_basicLiteral = 51, RULE_qualifiedIdentifier = 52, 
		RULE_methodExpr = 53, RULE_receiverType = 54, RULE_compositeLiteral = 55, 
		RULE_literalType = 56, RULE_literalValue = 57, RULE_elementList = 58, 
		RULE_element = 59, RULE_key = 60, RULE_elementNameOrIndex = 61, RULE_value = 62, 
		RULE_functionLiteral = 63, RULE_expression = 64, RULE_argumentList = 65, 
		RULE_conversion = 66, RULE_statement = 67, RULE_simpleStmt = 68, RULE_emptyStmt = 69, 
		RULE_labeledStmt = 70, RULE_label = 71, RULE_expressionStmt = 72, RULE_sendStmt = 73, 
		RULE_channel = 74, RULE_incDecStmt = 75, RULE_assignment = 76, RULE_assignOp = 77, 
		RULE_addAssignOp = 78, RULE_mulAssignOp = 79, RULE_ifStmt = 80, RULE_switchStmt = 81, 
		RULE_exprSwitchStmt = 82, RULE_exprCaseClause = 83, RULE_exprSwitchCase = 84, 
		RULE_typeSwitchStmt = 85, RULE_typeSwitchGuard = 86, RULE_typeCaseClause = 87, 
		RULE_typeSwitchCase = 88, RULE_typeList = 89, RULE_forStmt = 90, RULE_condition = 91, 
		RULE_forClause = 92, RULE_initStmt = 93, RULE_postStmt = 94, RULE_rangeClause = 95, 
		RULE_goStmt = 96, RULE_selectStmt = 97, RULE_commClause = 98, RULE_commCase = 99, 
		RULE_recvStmt = 100, RULE_recvExpr = 101, RULE_returnStmt = 102, RULE_breakStmt = 103, 
		RULE_continueStmt = 104, RULE_gotoStmt = 105, RULE_fallthroughStmt = 106, 
		RULE_deferStmt = 107, RULE_builtinCall = 108, RULE_builtinArgs = 109, 
		RULE_sourceFileBody = 110, RULE_sourceFile = 111, RULE_packageClause = 112, 
		RULE_packageName = 113, RULE_importDecl = 114, RULE_importSpec = 115, 
		RULE_importPath = 116;
	public static final String[] ruleNames = {
		"channel$lf$expression", "sendStmt$lf$expression", "incDecStmt$lf$expression", 
		"expressionList$lf$expression", "assignment$lf$expression", "expressionStmt$lf$expression", 
		"type", "typeName", "typeLiteral", "arrayType", "arrayLength", "elementType", 
		"sliceType", "structType", "fieldDecl", "anonymousField", "tag", "pointerType", 
		"baseType", "functionType", "signature", "result", "parameters", "parameterList", 
		"parameterDecl", "interfaceType", "methodSpec", "methodName", "interfaceTypeName", 
		"mapType", "keyType", "channelType", "block", "declaration", "topLevelDecl", 
		"constDecl", "constSpec", "identifierList", "expressionList", "typeDecl", 
		"typeSpec", "varDecl", "varSpec", "shortVarDecl", "functionDecl", "body", 
		"methodDecl", "receiver", "baseTypeName", "operand", "literal", "basicLiteral", 
		"qualifiedIdentifier", "methodExpr", "receiverType", "compositeLiteral", 
		"literalType", "literalValue", "elementList", "element", "key", "elementNameOrIndex", 
		"value", "functionLiteral", "expression", "argumentList", "conversion", 
		"statement", "simpleStmt", "emptyStmt", "labeledStmt", "label", "expressionStmt", 
		"sendStmt", "channel", "incDecStmt", "assignment", "assignOp", "addAssignOp", 
		"mulAssignOp", "ifStmt", "switchStmt", "exprSwitchStmt", "exprCaseClause", 
		"exprSwitchCase", "typeSwitchStmt", "typeSwitchGuard", "typeCaseClause", 
		"typeSwitchCase", "typeList", "forStmt", "condition", "forClause", "initStmt", 
		"postStmt", "rangeClause", "goStmt", "selectStmt", "commClause", "commCase", 
		"recvStmt", "recvExpr", "returnStmt", "breakStmt", "continueStmt", "gotoStmt", 
		"fallthroughStmt", "deferStmt", "builtinCall", "builtinArgs", "sourceFileBody", 
		"sourceFile", "packageClause", "packageName", "importDecl", "importSpec", 
		"importPath"
	};

	@Override
	public String getGrammarFileName() { return "GoParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }


	private final Set<String> packageNames = new HashSet<String>();
	private boolean checkPackageNames = true;

	public static String getPackageName(Token token) {
	    if (token == null) {
	        return null;
	    }

	    String name = token.getText();
	    if (name != null && token.getType() == StringLiteral) {
	        name = name.substring(name.lastIndexOf('/') + 1);
	        if (name.startsWith("\"")) {
	            name = name.substring(1);
	        }

	        if (name.endsWith("\"")) {
	            name = name.substring(0, name.length() - 1);
	        }
	    }

	    return name;
	}

	protected void addPackageName(Token token) {
	    String name = getPackageName(token);
	    if (name == null || name.isEmpty()) {
	        return;
	    }

	    packageNames.add(name);
	}

	protected boolean isPackageName(Token token) {
	    if (!checkPackageNames) {
	        return true;
	    }

	    return token != null && packageNames.contains(token.getText());
	}

	public boolean isCheckPackageNames() {
	    return checkPackageNames;
	}

	public void setCheckPackageNames(boolean checkPackageNames) {
	    this.checkPackageNames = checkPackageNames;
	}

	protected abstract boolean isLiteralAllowed(Token nextToken, OperandContext context);

	public AbstractGoParser(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	private final ChannelContext channel$lf$expression() throws RecognitionException {
		ChannelContext _localctx = new ChannelContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 0, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	private final SendStmtContext sendStmt$lf$expression() throws RecognitionException {
		SendStmtContext _localctx = new SendStmtContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 2, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); channel$lf$expression();
			setState(237); match(LeftArrow);
			setState(238); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	private final IncDecStmtContext incDecStmt$lf$expression() throws RecognitionException {
		IncDecStmtContext _localctx = new IncDecStmtContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 4, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_localctx.op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
				_localctx.op = _errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	private final ExpressionListContext expressionList$lf$expression() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 6, RULE_expressionList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(242); match(Comma);
					setState(243); expression(0);
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	private final AssignmentContext assignment$lf$expression() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 8, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); expressionList$lf$expression();
			setState(250); assignOp();
			setState(251); expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	private final ExpressionStmtContext expressionStmt$lf$expression() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, getState());
		enterLeftFactoredRule(_localctx, 10, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext<Token> {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TypeLiteralContext typeLiteral() {
			return getRuleContext(TypeLiteralContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(261);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(255); typeName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(256); typeLiteral();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(257); match(LeftParen);
				setState(258); type();
				setState(259); match(RightParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext<Token> {
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TypeNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263); qualifiedIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeLiteralContext extends ParserRuleContext<Token> {
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public ChannelTypeContext channelType() {
			return getRuleContext(ChannelTypeContext.class,0);
		}
		public InterfaceTypeContext interfaceType() {
			return getRuleContext(InterfaceTypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public SliceTypeContext sliceType() {
			return getRuleContext(SliceTypeContext.class,0);
		}
		public MapTypeContext mapType() {
			return getRuleContext(MapTypeContext.class,0);
		}
		public TypeLiteralContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeLiteral; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeLiteral(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeLiteralContext typeLiteral() throws RecognitionException {
		TypeLiteralContext _localctx = new TypeLiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeLiteral);
		try {
			setState(273);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(265); arrayType();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(266); structType();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267); pointerType();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(268); functionType();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(269); interfaceType();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(270); sliceType();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(271); mapType();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(272); channelType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext<Token> {
		public ArrayLengthContext arrayLength() {
			return getRuleContext(ArrayLengthContext.class,0);
		}
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitArrayType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitArrayType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275); match(LeftBrack);
			setState(276); arrayLength();
			setState(277); match(RightBrack);
			setState(278); elementType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLengthContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayLengthContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLength; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterArrayLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitArrayLength(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitArrayLength(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArrayLengthContext arrayLength() throws RecognitionException {
		ArrayLengthContext _localctx = new ArrayLengthContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementTypeContext extends ParserRuleContext<Token> {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ElementTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterElementType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitElementType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitElementType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementTypeContext elementType() throws RecognitionException {
		ElementTypeContext _localctx = new ElementTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_elementType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282); type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SliceTypeContext extends ParserRuleContext<Token> {
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public SliceTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliceType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSliceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSliceType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSliceType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final SliceTypeContext sliceType() throws RecognitionException {
		SliceTypeContext _localctx = new SliceTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sliceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284); match(LeftBrack);
			setState(285); match(RightBrack);
			setState(286); elementType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructTypeContext extends ParserRuleContext<Token> {
		public FieldDeclContext fieldDecl(int i) {
			return getRuleContext(FieldDeclContext.class,i);
		}
		public List<? extends FieldDeclContext> fieldDecl() {
			return getRuleContexts(FieldDeclContext.class);
		}
		public StructTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterStructType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitStructType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitStructType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_structType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288); match(Struct);
			setState(289); match(LeftBrace);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(290); fieldDecl();
					setState(291); match(Semi);
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(299);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(298); fieldDecl();
				}
				break;
			}
			setState(301); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclContext extends ParserRuleContext<Token> {
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public AnonymousFieldContext anonymousField() {
			return getRuleContext(AnonymousFieldContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FieldDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFieldDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFieldDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFieldDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FieldDeclContext fieldDecl() throws RecognitionException {
		FieldDeclContext _localctx = new FieldDeclContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fieldDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(303); identifierList();
				setState(304); type();
				}
				break;

			case 2:
				{
				setState(306); anonymousField();
				}
				break;
			}
			setState(310);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(309); tag();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnonymousFieldContext extends ParserRuleContext<Token> {
		public Token ptr;
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public AnonymousFieldContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousField; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAnonymousField(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAnonymousField(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAnonymousField(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AnonymousFieldContext anonymousField() throws RecognitionException {
		AnonymousFieldContext _localctx = new AnonymousFieldContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_anonymousField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(312); _localctx.ptr = match(Star);
				}
				break;
			}
			setState(315); typeName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> StringLiteral() { return getToken(AbstractGoParser.StringLiteral, 0); }
		public TagContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTag(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTag(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerTypeContext extends ParserRuleContext<Token> {
		public Token ptr;
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PointerTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterPointerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitPointerType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitPointerType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319); _localctx.ptr = match(Star);
			setState(320); baseType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext<Token> {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BaseTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBaseType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBaseType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_baseType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322); type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext<Token> {
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFunctionType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFunctionType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324); match(Func);
			setState(325); signature();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignatureContext extends ParserRuleContext<Token> {
		public ResultContext result() {
			return getRuleContext(ResultContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public SignatureContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signature; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSignature(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSignature(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327); parameters();
			setState(329);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(328); result();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResultContext extends ParserRuleContext<Token> {
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ResultContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterResult(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitResult(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitResult(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ResultContext result() throws RecognitionException {
		ResultContext _localctx = new ResultContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_result);
		try {
			setState(333);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331); parameters();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(332); type();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext<Token> {
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ParametersContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitParameters(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitParameters(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_parameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335); match(LeftParen);
			setState(340);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(336); parameterList();
				setState(338);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(337); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(342); match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext<Token> {
		public List<? extends ParameterDeclContext> parameterDecl() {
			return getRuleContexts(ParameterDeclContext.class);
		}
		public ParameterDeclContext parameterDecl(int i) {
			return getRuleContext(ParameterDeclContext.class,i);
		}
		public ParameterListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitParameterList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitParameterList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_parameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(344); parameterDecl();
			setState(349);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(345); match(Comma);
					setState(346); parameterDecl();
					}
					} 
				}
				setState(351);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterDeclContext extends ParserRuleContext<Token> {
		public Token ellip;
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterParameterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitParameterDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitParameterDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ParameterDeclContext parameterDecl() throws RecognitionException {
		ParameterDeclContext _localctx = new ParameterDeclContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_parameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(352); identifierList();
				}
				break;
			}
			setState(356);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(355); _localctx.ellip = match(Ellipsis);
				}
				break;
			}
			setState(358); type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceTypeContext extends ParserRuleContext<Token> {
		public MethodSpecContext methodSpec(int i) {
			return getRuleContext(MethodSpecContext.class,i);
		}
		public List<? extends MethodSpecContext> methodSpec() {
			return getRuleContexts(MethodSpecContext.class);
		}
		public InterfaceTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitInterfaceType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitInterfaceType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final InterfaceTypeContext interfaceType() throws RecognitionException {
		InterfaceTypeContext _localctx = new InterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_interfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360); match(Interface);
			setState(361); match(LeftBrace);
			setState(373);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(362); methodSpec();
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(363); match(Semi);
						setState(364); methodSpec();
						}
						} 
					}
					setState(369);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				setState(371);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(370); match(Semi);
					}
					break;
				}
				}
				break;
			}
			setState(375); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodSpecContext extends ParserRuleContext<Token> {
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public InterfaceTypeNameContext interfaceTypeName() {
			return getRuleContext(InterfaceTypeNameContext.class,0);
		}
		public MethodSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSpec; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMethodSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMethodSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMethodSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MethodSpecContext methodSpec() throws RecognitionException {
		MethodSpecContext _localctx = new MethodSpecContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_methodSpec);
		try {
			setState(381);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(377); methodName();
				setState(378); signature();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(380); interfaceTypeName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodNameContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public MethodNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMethodName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMethodName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMethodName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceTypeNameContext extends ParserRuleContext<Token> {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public InterfaceTypeNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceTypeName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterInterfaceTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitInterfaceTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitInterfaceTypeName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final InterfaceTypeNameContext interfaceTypeName() throws RecognitionException {
		InterfaceTypeNameContext _localctx = new InterfaceTypeNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_interfaceTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385); typeName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapTypeContext extends ParserRuleContext<Token> {
		public KeyTypeContext keyType() {
			return getRuleContext(KeyTypeContext.class,0);
		}
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public MapTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMapType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMapType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMapType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MapTypeContext mapType() throws RecognitionException {
		MapTypeContext _localctx = new MapTypeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); match(Map);
			setState(388); match(LeftBrack);
			setState(389); keyType();
			setState(390); match(RightBrack);
			setState(391); elementType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyTypeContext extends ParserRuleContext<Token> {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public KeyTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterKeyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitKeyType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitKeyType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final KeyTypeContext keyType() throws RecognitionException {
		KeyTypeContext _localctx = new KeyTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_keyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393); type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChannelTypeContext extends ParserRuleContext<Token> {
		public Token send;
		public Token recv;
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public ChannelTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_channelType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterChannelType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitChannelType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitChannelType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ChannelTypeContext channelType() throws RecognitionException {
		ChannelTypeContext _localctx = new ChannelTypeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_channelType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(395); match(Chan);
				setState(397);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(396); _localctx.send = match(LeftArrow);
					}
					break;
				}
				}
				break;

			case 2:
				{
				setState(399); _localctx.recv = match(LeftArrow);
				setState(400); match(Chan);
				}
				break;
			}
			setState(403); elementType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext<Token> {
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBlock(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(405); match(LeftBrace);
			setState(417);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(406); statement();
				setState(411);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(407); match(Semi);
						setState(408); statement();
						}
						} 
					}
					setState(413);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(415);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(414); match(Semi);
					}
					break;
				}
				}
				break;
			}
			setState(419); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext<Token> {
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public TypeDeclContext typeDecl() {
			return getRuleContext(TypeDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public DeclarationContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitDeclaration(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitDeclaration(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_declaration);
		try {
			setState(424);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421); constDecl();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422); typeDecl();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(423); varDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TopLevelDeclContext extends ParserRuleContext<Token> {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public MethodDeclContext methodDecl() {
			return getRuleContext(MethodDeclContext.class,0);
		}
		public FunctionDeclContext functionDecl() {
			return getRuleContext(FunctionDeclContext.class,0);
		}
		public TopLevelDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topLevelDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTopLevelDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTopLevelDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTopLevelDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TopLevelDeclContext topLevelDecl() throws RecognitionException {
		TopLevelDeclContext _localctx = new TopLevelDeclContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_topLevelDecl);
		try {
			setState(429);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(426); declaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(427); functionDecl();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(428); methodDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclContext extends ParserRuleContext<Token> {
		public List<? extends ConstSpecContext> constSpec() {
			return getRuleContexts(ConstSpecContext.class);
		}
		public ConstSpecContext constSpec(int i) {
			return getRuleContext(ConstSpecContext.class,i);
		}
		public ConstDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitConstDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitConstDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_constDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(431); match(Const);
			setState(448);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(432); constSpec();
				}
				break;

			case 2:
				{
				setState(433); match(LeftParen);
				setState(445);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(434); constSpec();
					setState(439);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(435); match(Semi);
							setState(436); constSpec();
							}
							} 
						}
						setState(441);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(443);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						setState(442); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(447); match(RightParen);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstSpecContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ConstSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constSpec; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterConstSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitConstSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitConstSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ConstSpecContext constSpec() throws RecognitionException {
		ConstSpecContext _localctx = new ConstSpecContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_constSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450); identifierList();
			setState(456);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(452);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(451); type();
					}
					break;
				}
				setState(454); match(Equal);
				setState(455); expressionList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierListContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER(int i) {
			return getToken(AbstractGoParser.IDENTIFIER, i);
		}
		public List<? extends TerminalNode<Token>> IDENTIFIER() { return getTokens(AbstractGoParser.IDENTIFIER); }
		public IdentifierListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitIdentifierList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitIdentifierList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_identifierList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(458); match(IDENTIFIER);
			setState(463);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(459); match(Comma);
					setState(460); match(IDENTIFIER);
					}
					} 
				}
				setState(465);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext<Token> {
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitExpressionList(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_expressionList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(466); expression(0);
			setState(471);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(467); match(Comma);
					setState(468); expression(0);
					}
					} 
				}
				setState(473);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclContext extends ParserRuleContext<Token> {
		public List<? extends TypeSpecContext> typeSpec() {
			return getRuleContexts(TypeSpecContext.class);
		}
		public TypeSpecContext typeSpec(int i) {
			return getRuleContext(TypeSpecContext.class,i);
		}
		public TypeDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_typeDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(474); match(Type);
			setState(491);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(475); typeSpec();
				}
				break;

			case 2:
				{
				setState(476); match(LeftParen);
				setState(488);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(477); typeSpec();
					setState(482);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(478); match(Semi);
							setState(479); typeSpec();
							}
							} 
						}
						setState(484);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
					}
					setState(486);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						setState(485); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(490); match(RightParen);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpec; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeSpecContext typeSpec() throws RecognitionException {
		TypeSpecContext _localctx = new TypeSpecContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493); match(IDENTIFIER);
			setState(494); type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext<Token> {
		public VarSpecContext varSpec(int i) {
			return getRuleContext(VarSpecContext.class,i);
		}
		public List<? extends VarSpecContext> varSpec() {
			return getRuleContexts(VarSpecContext.class);
		}
		public VarDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitVarDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitVarDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_varDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(496); match(Var);
			setState(513);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(497); varSpec();
				}
				break;

			case 2:
				{
				setState(498); match(LeftParen);
				setState(510);
				switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(499); varSpec();
					setState(504);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(500); match(Semi);
							setState(501); varSpec();
							}
							} 
						}
						setState(506);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
					}
					setState(508);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
					case 1:
						{
						setState(507); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(512); match(RightParen);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarSpecContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varSpec; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterVarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitVarSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitVarSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final VarSpecContext varSpec() throws RecognitionException {
		VarSpecContext _localctx = new VarSpecContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_varSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515); identifierList();
			setState(523);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(516); type();
				setState(519);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(517); match(Equal);
					setState(518); expressionList();
					}
					break;
				}
				}
				break;

			case 2:
				{
				setState(521); match(Equal);
				setState(522); expressionList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShortVarDeclContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public ShortVarDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortVarDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterShortVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitShortVarDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitShortVarDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ShortVarDeclContext shortVarDecl() throws RecognitionException {
		ShortVarDeclContext _localctx = new ShortVarDeclContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525); identifierList();
			setState(526); match(ColonEqual);
			setState(527); expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclContext extends ParserRuleContext<Token> {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFunctionDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_functionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529); match(Func);
			setState(530); match(IDENTIFIER);
			setState(531); signature();
			setState(533);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(532); body();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext<Token> {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BodyContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBody(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBody(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclContext extends ParserRuleContext<Token> {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ReceiverContext receiver() {
			return getRuleContext(ReceiverContext.class,0);
		}
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public SignatureContext signature() {
			return getRuleContext(SignatureContext.class,0);
		}
		public MethodDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMethodDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMethodDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(537); match(Func);
			setState(538); receiver();
			setState(539); methodName();
			setState(540); signature();
			setState(542);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(541); body();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReceiverContext extends ParserRuleContext<Token> {
		public Token ptr;
		public BaseTypeNameContext baseTypeName() {
			return getRuleContext(BaseTypeNameContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public ReceiverContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiver; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitReceiver(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitReceiver(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ReceiverContext receiver() throws RecognitionException {
		ReceiverContext _localctx = new ReceiverContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_receiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544); match(LeftParen);
			setState(546);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(545); match(IDENTIFIER);
				}
				break;
			}
			setState(549);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(548); _localctx.ptr = match(Star);
				}
				break;
			}
			setState(551); baseTypeName();
			setState(552); match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeNameContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public BaseTypeNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseTypeName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBaseTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBaseTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBaseTypeName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BaseTypeNameContext baseTypeName() throws RecognitionException {
		BaseTypeNameContext _localctx = new BaseTypeNameContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_baseTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QualifiedIdentifierContext qualifiedIdentifier() {
			return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public MethodExprContext methodExpr() {
			return getRuleContext(MethodExprContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public OperandContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitOperand(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitOperand(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_operand);
		try {
			setState(564);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(556);
				if (!(isLiteralAllowed(_input.LT(1), _localctx))) throw new FailedPredicateException(this, "isLiteralAllowed(_input.LT(1), $ctx)");
				setState(557); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(558); qualifiedIdentifier();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(559); methodExpr();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(560); match(LeftParen);
				setState(561); expression(0);
				setState(562); match(RightParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext<Token> {
		public BasicLiteralContext basicLiteral() {
			return getRuleContext(BasicLiteralContext.class,0);
		}
		public CompositeLiteralContext compositeLiteral() {
			return getRuleContext(CompositeLiteralContext.class,0);
		}
		public FunctionLiteralContext functionLiteral() {
			return getRuleContext(FunctionLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitLiteral(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_literal);
		try {
			setState(569);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(566); basicLiteral();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(567); compositeLiteral();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(568); functionLiteral();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasicLiteralContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> StringLiteral() { return getToken(AbstractGoParser.StringLiteral, 0); }
		public TerminalNode<Token> IMAGINARY_LITERAL() { return getToken(AbstractGoParser.IMAGINARY_LITERAL, 0); }
		public TerminalNode<Token> FLOAT_LITERAL() { return getToken(AbstractGoParser.FLOAT_LITERAL, 0); }
		public TerminalNode<Token> INT_LITERAL() { return getToken(AbstractGoParser.INT_LITERAL, 0); }
		public TerminalNode<Token> CharLiteral() { return getToken(AbstractGoParser.CharLiteral, 0); }
		public BasicLiteralContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicLiteral; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBasicLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBasicLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBasicLiteral(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BasicLiteralContext basicLiteral() throws RecognitionException {
		BasicLiteralContext _localctx = new BasicLiteralContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_basicLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			_la = _input.LA(1);
			if ( !(_la==INT_LITERAL || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (IMAGINARY_LITERAL - 79)) | (1L << (FLOAT_LITERAL - 79)) | (1L << (CharLiteral - 79)) | (1L << (StringLiteral - 79)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedIdentifierContext extends ParserRuleContext<Token> {
		public Token dot;
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public QualifiedIdentifierContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterQualifiedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitQualifiedIdentifier(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitQualifiedIdentifier(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_qualifiedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(577);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(573);
				if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "isPackageName(_input.LT(1))");
				setState(574); packageName();
				setState(575); _localctx.dot = match(Dot);
				}
				break;
			}
			setState(579); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodExprContext extends ParserRuleContext<Token> {
		public Token dot;
		public ReceiverTypeContext receiverType() {
			return getRuleContext(ReceiverTypeContext.class,0);
		}
		public MethodNameContext methodName() {
			return getRuleContext(MethodNameContext.class,0);
		}
		public MethodExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodExpr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMethodExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMethodExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMethodExpr(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MethodExprContext methodExpr() throws RecognitionException {
		MethodExprContext _localctx = new MethodExprContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_methodExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581); receiverType();
			setState(582); _localctx.dot = match(Dot);
			setState(583); methodName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReceiverTypeContext extends ParserRuleContext<Token> {
		public Token ptr;
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ReceiverTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_receiverType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterReceiverType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitReceiverType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitReceiverType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ReceiverTypeContext receiverType() throws RecognitionException {
		ReceiverTypeContext _localctx = new ReceiverTypeContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_receiverType);
		try {
			setState(591);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(585); typeName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(586); match(LeftParen);
				setState(587); _localctx.ptr = match(Star);
				setState(588); typeName();
				setState(589); match(RightParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompositeLiteralContext extends ParserRuleContext<Token> {
		public LiteralTypeContext literalType() {
			return getRuleContext(LiteralTypeContext.class,0);
		}
		public LiteralValueContext literalValue() {
			return getRuleContext(LiteralValueContext.class,0);
		}
		public CompositeLiteralContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compositeLiteral; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCompositeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCompositeLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCompositeLiteral(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final CompositeLiteralContext compositeLiteral() throws RecognitionException {
		CompositeLiteralContext _localctx = new CompositeLiteralContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_compositeLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(593); literalType();
			setState(594); literalValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralTypeContext extends ParserRuleContext<Token> {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public SliceTypeContext sliceType() {
			return getRuleContext(SliceTypeContext.class,0);
		}
		public ElementTypeContext elementType() {
			return getRuleContext(ElementTypeContext.class,0);
		}
		public MapTypeContext mapType() {
			return getRuleContext(MapTypeContext.class,0);
		}
		public LiteralTypeContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalType; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterLiteralType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitLiteralType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitLiteralType(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LiteralTypeContext literalType() throws RecognitionException {
		LiteralTypeContext _localctx = new LiteralTypeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_literalType);
		try {
			setState(605);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(596); structType();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(597); arrayType();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(598); match(LeftBrack);
				setState(599); match(Ellipsis);
				setState(600); match(RightBrack);
				setState(601); elementType();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(602); sliceType();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(603); mapType();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(604); typeName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralValueContext extends ParserRuleContext<Token> {
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public LiteralValueContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalValue; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterLiteralValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitLiteralValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitLiteralValue(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LiteralValueContext literalValue() throws RecognitionException {
		LiteralValueContext _localctx = new LiteralValueContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_literalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607); match(LeftBrace);
			setState(612);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(608); elementList();
				setState(610);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(609); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(614); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementListContext extends ParserRuleContext<Token> {
		public List<? extends ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ElementListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitElementList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitElementList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_elementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(616); element();
			setState(621);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(617); match(Comma);
					setState(618); element();
					}
					} 
				}
				setState(623);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext<Token> {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public KeyContext key() {
			return getRuleContext(KeyContext.class,0);
		}
		public ElementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitElement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(627);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(624); key();
				setState(625); match(Colon);
				}
				break;
			}
			setState(629); value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyContext extends ParserRuleContext<Token> {
		public ElementNameOrIndexContext elementNameOrIndex() {
			return getRuleContext(ElementNameOrIndexContext.class,0);
		}
		public KeyContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitKey(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitKey(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631); elementNameOrIndex();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementNameOrIndexContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ElementNameOrIndexContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementNameOrIndex; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterElementNameOrIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitElementNameOrIndex(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitElementNameOrIndex(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementNameOrIndexContext elementNameOrIndex() throws RecognitionException {
		ElementNameOrIndexContext _localctx = new ElementNameOrIndexContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_elementNameOrIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(633); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LiteralValueContext literalValue() {
			return getRuleContext(LiteralValueContext.class,0);
		}
		public ValueContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitValue(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_value);
		try {
			setState(637);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(635); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(636); literalValue();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionLiteralContext extends ParserRuleContext<Token> {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public FunctionLiteralContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionLiteral; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFunctionLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFunctionLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFunctionLiteral(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FunctionLiteralContext functionLiteral() throws RecognitionException {
		FunctionLiteralContext _localctx = new FunctionLiteralContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_functionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639); functionType();
			setState(640); body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext<Token> {
		public ExpressionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultExprContext extends ExpressionContext {
		public Token op;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMultExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMultExpr(this);
			else return null;
		}
	}
	public static class AndExprContext extends ExpressionContext {
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAndExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAndExpr(this);
			else return null;
		}
	}
	public static class ConversionOrCallExprContext extends ExpressionContext {
		public ConversionContext conversion() {
			return getRuleContext(ConversionContext.class,0);
		}
		public ConversionOrCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterConversionOrCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitConversionOrCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitConversionOrCallExpr(this);
			else return null;
		}
	}
	public static class CallExprContext extends ExpressionContext {
		public Token lp;
		public Token rp;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public CallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCallExpr(this);
			else return null;
		}
	}
	public static class TypeAssertionExprContext extends ExpressionContext {
		public Token dot;
		public Token lp;
		public Token rp;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeAssertionExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeAssertionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeAssertionExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeAssertionExpr(this);
			else return null;
		}
	}
	public static class CompareExprContext extends ExpressionContext {
		public Token op;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CompareExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCompareExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCompareExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCompareExpr(this);
			else return null;
		}
	}
	public static class OrExprContext extends ExpressionContext {
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitOrExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitOrExpr(this);
			else return null;
		}
	}
	public static class SelectorExprContext extends ExpressionContext {
		public Token dot;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public SelectorExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSelectorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSelectorExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSelectorExpr(this);
			else return null;
		}
	}
	public static class SliceExprContext extends ExpressionContext {
		public ExpressionContext from;
		public ExpressionContext to;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SliceExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSliceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSliceExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSliceExpr(this);
			else return null;
		}
	}
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitUnaryExpr(this);
			else return null;
		}
	}
	public static class OperandExprContext extends ExpressionContext {
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public OperandExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterOperandExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitOperandExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitOperandExpr(this);
			else return null;
		}
	}
	public static class AddExprContext extends ExpressionContext {
		public Token op;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAddExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAddExpr(this);
			else return null;
		}
	}
	public static class BuiltinCallExprContext extends ExpressionContext {
		public BuiltinCallContext builtinCall() {
			return getRuleContext(BuiltinCallContext.class,0);
		}
		public BuiltinCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBuiltinCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBuiltinCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBuiltinCallExpr(this);
			else return null;
		}
	}
	public static class IndexExprContext extends ExpressionContext {
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IndexExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitIndexExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitIndexExpr(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext<Token> _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 128;
		enterRecursionRule(_localctx, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(648);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(643);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Star) | (1L << Amp) | (1L << Caret) | (1L << LeftArrow) | (1L << Bang))) != 0)) ) {
					((UnaryExprContext)_localctx).op = _errHandler.recoverInline(this);
				}
				consume();
				setState(644); expression(7);
				}
				break;

			case 2:
				{
				_localctx = new ConversionOrCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(645); conversion();
				}
				break;

			case 3:
				{
				_localctx = new BuiltinCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(646); builtinCall();
				}
				break;

			case 4:
				{
				_localctx = new OperandExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(647); operand();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(700);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(698);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(650);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(651);
						((MultExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Slash) | (1L << Percent) | (1L << Amp) | (1L << LeftShift) | (1L << RightShift) | (1L << AmpCaret))) != 0)) ) {
							((MultExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(652); expression(7);
						}
						break;

					case 2:
						{
						_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(653);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(654);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Plus) | (1L << Minus) | (1L << Pipe) | (1L << Caret))) != 0)) ) {
							((AddExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(655); expression(6);
						}
						break;

					case 3:
						{
						_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(656);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(657);
						((CompareExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EqualEqual) | (1L << LessThan) | (1L << GreaterThan) | (1L << BangEqual) | (1L << LessEqual) | (1L << GreaterEqual))) != 0)) ) {
							((CompareExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(658); expression(5);
						}
						break;

					case 4:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(659);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(660); match(And);
						setState(661); expression(4);
						}
						break;

					case 5:
						{
						_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(662);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(663); match(Or);
						setState(664); expression(3);
						}
						break;

					case 6:
						{
						_localctx = new SelectorExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(665);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(666); ((SelectorExprContext)_localctx).dot = match(Dot);
						setState(667); match(IDENTIFIER);
						}
						break;

					case 7:
						{
						_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(668);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(669); match(LeftBrack);
						setState(670); expression(0);
						setState(671); match(RightBrack);
						}
						break;

					case 8:
						{
						_localctx = new SliceExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(673);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(674); match(LeftBrack);
						setState(676);
						switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(675); ((SliceExprContext)_localctx).from = expression(0);
							}
							break;
						}
						setState(678); match(Colon);
						setState(680);
						switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
						case 1:
							{
							setState(679); ((SliceExprContext)_localctx).to = expression(0);
							}
							break;
						}
						setState(682); match(RightBrack);
						}
						break;

					case 9:
						{
						_localctx = new TypeAssertionExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(683);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(684); ((TypeAssertionExprContext)_localctx).dot = match(Dot);
						setState(685); ((TypeAssertionExprContext)_localctx).lp = match(LeftParen);
						setState(686); type();
						setState(687); ((TypeAssertionExprContext)_localctx).rp = match(RightParen);
						}
						break;

					case 10:
						{
						_localctx = new CallExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(689);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(690); ((CallExprContext)_localctx).lp = match(LeftParen);
						setState(695);
						switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
						case 1:
							{
							setState(691); argumentList();
							setState(693);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
							case 1:
								{
								setState(692); match(Comma);
								}
								break;
							}
							}
							break;
						}
						setState(697); ((CallExprContext)_localctx).rp = match(RightParen);
						}
						break;
					}
					} 
				}
				setState(702);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext<Token> {
		public Token ellip;
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitArgumentList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitArgumentList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(703); expressionList();
			setState(705);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(704); _localctx.ellip = match(Ellipsis);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConversionContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ConversionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conversion; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterConversion(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitConversion(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitConversion(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ConversionContext conversion() throws RecognitionException {
		ConversionContext _localctx = new ConversionContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707); type();
			setState(708); match(LeftParen);
			setState(709); expression(0);
			setState(710); match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext<Token> {
		public GotoStmtContext gotoStmt() {
			return getRuleContext(GotoStmtContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public FallthroughStmtContext fallthroughStmt() {
			return getRuleContext(FallthroughStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public LabeledStmtContext labeledStmt() {
			return getRuleContext(LabeledStmtContext.class,0);
		}
		public DeferStmtContext deferStmt() {
			return getRuleContext(DeferStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public GoStmtContext goStmt() {
			return getRuleContext(GoStmtContext.class,0);
		}
		public SwitchStmtContext switchStmt() {
			return getRuleContext(SwitchStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitStatement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitStatement(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_statement);
		try {
			setState(727);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(712); declaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(713); labeledStmt();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(714); block();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(715); goStmt();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(716); returnStmt();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(717); breakStmt();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(718); continueStmt();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(719); gotoStmt();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(720); fallthroughStmt();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(721); ifStmt();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(722); switchStmt();
				}
				break;

			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(723); selectStmt();
				}
				break;

			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(724); forStmt();
				}
				break;

			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(725); deferStmt();
				}
				break;

			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(726); simpleStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleStmtContext extends ParserRuleContext<Token> {
		public ShortVarDeclContext shortVarDecl() {
			return getRuleContext(ShortVarDeclContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExpressionStmtContext expressionStmt() {
			return getRuleContext(ExpressionStmtContext.class,0);
		}
		public SendStmtContext sendStmt() {
			return getRuleContext(SendStmtContext.class,0);
		}
		public IncDecStmtContext incDecStmt() {
			return getRuleContext(IncDecStmtContext.class,0);
		}
		public EmptyStmtContext emptyStmt() {
			return getRuleContext(EmptyStmtContext.class,0);
		}
		public SimpleStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSimpleStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSimpleStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSimpleStmt(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final SimpleStmtContext simpleStmt() throws RecognitionException {
		SimpleStmtContext _localctx = new SimpleStmtContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_simpleStmt);
		try {
			setState(738);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(729); shortVarDecl();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(730); expression(0);
				setState(735);
				switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(731); sendStmt$lf$expression();
					}
					break;

				case 2:
					{
					setState(732); incDecStmt$lf$expression();
					}
					break;

				case 3:
					{
					setState(733); assignment$lf$expression();
					}
					break;

				case 4:
					{
					setState(734); expressionStmt$lf$expression();
					}
					break;
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(737); emptyStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStmtContext extends ParserRuleContext<Token> {
		public EmptyStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitEmptyStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final EmptyStmtContext emptyStmt() throws RecognitionException {
		EmptyStmtContext _localctx = new EmptyStmtContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_emptyStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabeledStmtContext extends ParserRuleContext<Token> {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public LabeledStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterLabeledStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitLabeledStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitLabeledStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LabeledStmtContext labeledStmt() throws RecognitionException {
		LabeledStmtContext _localctx = new LabeledStmtContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742); label();
			setState(743); match(Colon);
			setState(744); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public LabelContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitLabel(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitLabel(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(746); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterExpressionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitExpressionStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitExpressionStmt(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final ExpressionStmtContext expressionStmt() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(748); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SendStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChannelContext channel() {
			return getRuleContext(ChannelContext.class,0);
		}
		public SendStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sendStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSendStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSendStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSendStmt(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final SendStmtContext sendStmt() throws RecognitionException {
		SendStmtContext _localctx = new SendStmtContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(750); channel();
			setState(751); match(LeftArrow);
			setState(752); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChannelContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChannelContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_channel; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterChannel(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitChannel(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitChannel(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final ChannelContext channel() throws RecognitionException {
		ChannelContext _localctx = new ChannelContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(754); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncDecStmtContext extends ParserRuleContext<Token> {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IncDecStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_incDecStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterIncDecStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitIncDecStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitIncDecStmt(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final IncDecStmtContext incDecStmt() throws RecognitionException {
		IncDecStmtContext _localctx = new IncDecStmtContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756); expression(0);
			setState(757);
			_localctx.op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
				_localctx.op = _errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext<Token> {
		public AssignOpContext assignOp() {
			return getRuleContext(AssignOpContext.class,0);
		}
		public List<? extends ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public AssignmentContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAssignment(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAssignment(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(759); expressionList();
			setState(760); assignOp();
			setState(761); expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignOpContext extends ParserRuleContext<Token> {
		public AddAssignOpContext addAssignOp() {
			return getRuleContext(AddAssignOpContext.class,0);
		}
		public MulAssignOpContext mulAssignOp() {
			return getRuleContext(MulAssignOpContext.class,0);
		}
		public AssignOpContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignOp; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAssignOp(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AssignOpContext assignOp() throws RecognitionException {
		AssignOpContext _localctx = new AssignOpContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_assignOp);
		try {
			setState(766);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(763); addAssignOp();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(764); mulAssignOp();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(765); match(Equal);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddAssignOpContext extends ParserRuleContext<Token> {
		public AddAssignOpContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addAssignOp; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterAddAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitAddAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitAddAssignOp(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final AddAssignOpContext addAssignOp() throws RecognitionException {
		AddAssignOpContext _localctx = new AddAssignOpContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_addAssignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PlusEqual) | (1L << MinusEqual) | (1L << PipeEqual) | (1L << CaretEqual))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulAssignOpContext extends ParserRuleContext<Token> {
		public MulAssignOpContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulAssignOp; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterMulAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitMulAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitMulAssignOp(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final MulAssignOpContext mulAssignOp() throws RecognitionException {
		MulAssignOpContext _localctx = new MulAssignOpContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_mulAssignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StarEqual) | (1L << SlashEqual) | (1L << PercentEqual) | (1L << AmpEqual) | (1L << CaretEqual) | (1L << LeftShiftEqual) | (1L << RightShiftEqual) | (1L << AmpCaretEqual))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext<Token> {
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public List<? extends BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public IfStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitIfStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitIfStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772); match(If);
			setState(776);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(773); simpleStmt();
				setState(774); match(Semi);
				}
				break;
			}
			setState(778); expression(0);
			setState(779); block();
			setState(785);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(780); match(Else);
				setState(783);
				switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					{
					setState(781); ifStmt();
					}
					break;

				case 2:
					{
					setState(782); block();
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchStmtContext extends ParserRuleContext<Token> {
		public TypeSwitchStmtContext typeSwitchStmt() {
			return getRuleContext(TypeSwitchStmtContext.class,0);
		}
		public ExprSwitchStmtContext exprSwitchStmt() {
			return getRuleContext(ExprSwitchStmtContext.class,0);
		}
		public SwitchStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSwitchStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final SwitchStmtContext switchStmt() throws RecognitionException {
		SwitchStmtContext _localctx = new SwitchStmtContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_switchStmt);
		try {
			setState(789);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(787); exprSwitchStmt();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(788); typeSwitchStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSwitchStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprCaseClauseContext exprCaseClause(int i) {
			return getRuleContext(ExprCaseClauseContext.class,i);
		}
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public List<? extends ExprCaseClauseContext> exprCaseClause() {
			return getRuleContexts(ExprCaseClauseContext.class);
		}
		public ExprSwitchStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSwitchStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterExprSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitExprSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitExprSwitchStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExprSwitchStmtContext exprSwitchStmt() throws RecognitionException {
		ExprSwitchStmtContext _localctx = new ExprSwitchStmtContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_exprSwitchStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(791); match(Switch);
			setState(795);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(792); simpleStmt();
				setState(793); match(Semi);
				}
				break;
			}
			setState(798);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				{
				setState(797); expression(0);
				}
				break;
			}
			setState(800); match(LeftBrace);
			setState(804);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(801); exprCaseClause();
					}
					} 
				}
				setState(806);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			}
			setState(807); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprCaseClauseContext extends ParserRuleContext<Token> {
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public ExprSwitchCaseContext exprSwitchCase() {
			return getRuleContext(ExprSwitchCaseContext.class,0);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ExprCaseClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCaseClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterExprCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitExprCaseClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitExprCaseClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExprCaseClauseContext exprCaseClause() throws RecognitionException {
		ExprCaseClauseContext _localctx = new ExprCaseClauseContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_exprCaseClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(809); exprSwitchCase();
			setState(810); match(Colon);
			setState(822);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(811); statement();
				setState(816);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(812); match(Semi);
						setState(813); statement();
						}
						} 
					}
					setState(818);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				}
				setState(820);
				switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(819); match(Semi);
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSwitchCaseContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExprSwitchCaseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSwitchCase; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterExprSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitExprSwitchCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitExprSwitchCase(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ExprSwitchCaseContext exprSwitchCase() throws RecognitionException {
		ExprSwitchCaseContext _localctx = new ExprSwitchCaseContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_exprSwitchCase);
		try {
			setState(827);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(824); match(Case);
				setState(825); expressionList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(826); match(Default);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSwitchStmtContext extends ParserRuleContext<Token> {
		public TypeCaseClauseContext typeCaseClause(int i) {
			return getRuleContext(TypeCaseClauseContext.class,i);
		}
		public List<? extends TypeCaseClauseContext> typeCaseClause() {
			return getRuleContexts(TypeCaseClauseContext.class);
		}
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public TypeSwitchGuardContext typeSwitchGuard() {
			return getRuleContext(TypeSwitchGuardContext.class,0);
		}
		public TypeSwitchStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSwitchStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeSwitchStmtContext typeSwitchStmt() throws RecognitionException {
		TypeSwitchStmtContext _localctx = new TypeSwitchStmtContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_typeSwitchStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(829); match(Switch);
			setState(833);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(830); simpleStmt();
				setState(831); match(Semi);
				}
				break;
			}
			setState(835); typeSwitchGuard();
			setState(836); match(LeftBrace);
			setState(840);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(837); typeCaseClause();
					}
					} 
				}
				setState(842);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(843); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSwitchGuardContext extends ParserRuleContext<Token> {
		public Token defeq;
		public Token dot;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public TypeSwitchGuardContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSwitchGuard; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeSwitchGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeSwitchGuard(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchGuard(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeSwitchGuardContext typeSwitchGuard() throws RecognitionException {
		TypeSwitchGuardContext _localctx = new TypeSwitchGuardContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				{
				setState(845); match(IDENTIFIER);
				setState(846); _localctx.defeq = match(ColonEqual);
				}
				break;
			}
			setState(849); expression(0);
			setState(850); _localctx.dot = match(Dot);
			setState(851); match(LeftParen);
			setState(852); match(Type);
			setState(853); match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeCaseClauseContext extends ParserRuleContext<Token> {
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TypeSwitchCaseContext typeSwitchCase() {
			return getRuleContext(TypeSwitchCaseContext.class,0);
		}
		public TypeCaseClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeCaseClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeCaseClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeCaseClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeCaseClauseContext typeCaseClause() throws RecognitionException {
		TypeCaseClauseContext _localctx = new TypeCaseClauseContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_typeCaseClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(855); typeSwitchCase();
			setState(856); match(Colon);
			setState(868);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				{
				setState(857); statement();
				setState(862);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(858); match(Semi);
						setState(859); statement();
						}
						} 
					}
					setState(864);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				}
				setState(866);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(865); match(Semi);
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSwitchCaseContext extends ParserRuleContext<Token> {
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TypeSwitchCaseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSwitchCase; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeSwitchCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchCase(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeSwitchCaseContext typeSwitchCase() throws RecognitionException {
		TypeSwitchCaseContext _localctx = new TypeSwitchCaseContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_typeSwitchCase);
		try {
			setState(873);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(870); match(Case);
				setState(871); typeList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(872); match(Default);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext<Token> {
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<? extends TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeListContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitTypeList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitTypeList(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_typeList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(875); type();
			setState(880);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(876); match(Comma);
					setState(877); type();
					}
					} 
				}
				setState(882);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStmtContext extends ParserRuleContext<Token> {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public RangeClauseContext rangeClause() {
			return getRuleContext(RangeClauseContext.class,0);
		}
		public ForClauseContext forClause() {
			return getRuleContext(ForClauseContext.class,0);
		}
		public ForStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitForStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitForStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(883); match(For);
			setState(887);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(884); condition();
				}
				break;

			case 2:
				{
				setState(885); forClause();
				}
				break;

			case 3:
				{
				setState(886); rangeClause();
				}
				break;
			}
			setState(889); block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCondition(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCondition(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(891); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForClauseContext extends ParserRuleContext<Token> {
		public PostStmtContext postStmt() {
			return getRuleContext(PostStmtContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InitStmtContext initStmt() {
			return getRuleContext(InitStmtContext.class,0);
		}
		public ForClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterForClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitForClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitForClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_forClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(894);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(893); initStmt();
				}
				break;
			}
			setState(896); match(Semi);
			setState(898);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(897); condition();
				}
				break;
			}
			setState(900); match(Semi);
			setState(902);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(901); postStmt();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitStmtContext extends ParserRuleContext<Token> {
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public InitStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterInitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitInitStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitInitStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final InitStmtContext initStmt() throws RecognitionException {
		InitStmtContext _localctx = new InitStmtContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_initStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(904); simpleStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostStmtContext extends ParserRuleContext<Token> {
		public SimpleStmtContext simpleStmt() {
			return getRuleContext(SimpleStmtContext.class,0);
		}
		public PostStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterPostStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitPostStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitPostStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final PostStmtContext postStmt() throws RecognitionException {
		PostStmtContext _localctx = new PostStmtContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_postStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(906); simpleStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeClauseContext extends ParserRuleContext<Token> {
		public ExpressionContext e1;
		public ExpressionContext e2;
		public Token eq;
		public Token defeq;
		public ExpressionContext e;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterRangeClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitRangeClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitRangeClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RangeClauseContext rangeClause() throws RecognitionException {
		RangeClauseContext _localctx = new RangeClauseContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_rangeClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(908); _localctx.e1 = expression(0);
			setState(911);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				{
				setState(909); match(Comma);
				setState(910); _localctx.e2 = expression(0);
				}
				break;
			}
			setState(915);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				{
				setState(913); _localctx.eq = match(Equal);
				}
				break;

			case 2:
				{
				setState(914); _localctx.defeq = match(ColonEqual);
				}
				break;
			}
			setState(917); match(Range);
			setState(918); _localctx.e = expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GoStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterGoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitGoStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitGoStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final GoStmtContext goStmt() throws RecognitionException {
		GoStmtContext _localctx = new GoStmtContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_goStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(920); match(Go);
			setState(921); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectStmtContext extends ParserRuleContext<Token> {
		public CommClauseContext commClause(int i) {
			return getRuleContext(CommClauseContext.class,i);
		}
		public List<? extends CommClauseContext> commClause() {
			return getRuleContexts(CommClauseContext.class);
		}
		public SelectStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSelectStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSelectStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_selectStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(923); match(Select);
			setState(924); match(LeftBrace);
			setState(928);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(925); commClause();
					}
					} 
				}
				setState(930);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			}
			setState(931); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommClauseContext extends ParserRuleContext<Token> {
		public List<? extends StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public CommCaseContext commCase() {
			return getRuleContext(CommCaseContext.class,0);
		}
		public CommClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCommClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCommClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCommClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final CommClauseContext commClause() throws RecognitionException {
		CommClauseContext _localctx = new CommClauseContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_commClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(933); commCase();
			setState(934); match(Colon);
			setState(946);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				{
				setState(935); statement();
				setState(940);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(936); match(Semi);
						setState(937); statement();
						}
						} 
					}
					setState(942);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				}
				setState(944);
				switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(943); match(Semi);
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommCaseContext extends ParserRuleContext<Token> {
		public RecvStmtContext recvStmt() {
			return getRuleContext(RecvStmtContext.class,0);
		}
		public SendStmtContext sendStmt() {
			return getRuleContext(SendStmtContext.class,0);
		}
		public CommCaseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commCase; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterCommCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitCommCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitCommCase(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final CommCaseContext commCase() throws RecognitionException {
		CommCaseContext _localctx = new CommCaseContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_commCase);
		try {
			setState(954);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(948); match(Case);
				setState(951);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(949); sendStmt();
					}
					break;

				case 2:
					{
					setState(950); recvStmt();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(953); match(Default);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecvStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext e1;
		public ExpressionContext e2;
		public Token eq;
		public Token defeq;
		public List<? extends ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public RecvExprContext recvExpr() {
			return getRuleContext(RecvExprContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RecvStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recvStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterRecvStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitRecvStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitRecvStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RecvStmtContext recvStmt() throws RecognitionException {
		RecvStmtContext _localctx = new RecvStmtContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_recvStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(965);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				setState(956); _localctx.e1 = expression(0);
				setState(959);
				switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(957); match(Comma);
					setState(958); _localctx.e2 = expression(0);
					}
					break;
				}
				setState(963);
				switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					{
					setState(961); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(962); _localctx.defeq = match(ColonEqual);
					}
					break;
				}
				}
				break;
			}
			setState(967); recvExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecvExprContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RecvExprContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recvExpr; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterRecvExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitRecvExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitRecvExpr(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final RecvExprContext recvExpr() throws RecognitionException {
		RecvExprContext _localctx = new RecvExprContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_recvExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(969); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitReturnStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitReturnStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(971); match(Return);
			setState(973);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(972); expressionList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public BreakStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBreakStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBreakStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(975); match(Break);
			setState(977);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(976); label();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public ContinueStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitContinueStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitContinueStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(979); match(Continue);
			setState(981);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				{
				setState(980); label();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GotoStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public GotoStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gotoStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterGotoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitGotoStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitGotoStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final GotoStmtContext gotoStmt() throws RecognitionException {
		GotoStmtContext _localctx = new GotoStmtContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_gotoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(983); match(Goto);
			setState(984); label();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FallthroughStmtContext extends ParserRuleContext<Token> {
		public FallthroughStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fallthroughStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFallthroughStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFallthroughStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFallthroughStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FallthroughStmtContext fallthroughStmt() throws RecognitionException {
		FallthroughStmtContext _localctx = new FallthroughStmtContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_fallthroughStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(986); match(Fallthrough);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeferStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeferStmtContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deferStmt; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterDeferStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitDeferStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitDeferStmt(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final DeferStmtContext deferStmt() throws RecognitionException {
		DeferStmtContext _localctx = new DeferStmtContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_deferStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(988); match(Defer);
			setState(989); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltinCallContext extends ParserRuleContext<Token> {
		public BuiltinArgsContext builtinArgs() {
			return getRuleContext(BuiltinArgsContext.class,0);
		}
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public BuiltinCallContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtinCall; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBuiltinCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBuiltinCall(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBuiltinCall(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BuiltinCallContext builtinCall() throws RecognitionException {
		BuiltinCallContext _localctx = new BuiltinCallContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(991); match(IDENTIFIER);
			setState(992); match(LeftParen);
			setState(997);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(993); builtinArgs();
				setState(995);
				switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
				case 1:
					{
					setState(994); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(999); match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltinArgsContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BuiltinArgsContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtinArgs; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterBuiltinArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitBuiltinArgs(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitBuiltinArgs(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final BuiltinArgsContext builtinArgs() throws RecognitionException {
		BuiltinArgsContext _localctx = new BuiltinArgsContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_builtinArgs);
		try {
			setState(1007);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1001); type();
				setState(1004);
				switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1002); match(Comma);
					setState(1003); expressionList();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1006); expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceFileBodyContext extends ParserRuleContext<Token> {
		public List<? extends ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public List<? extends TopLevelDeclContext> topLevelDecl() {
			return getRuleContexts(TopLevelDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public PackageClauseContext packageClause() {
			return getRuleContext(PackageClauseContext.class,0);
		}
		public TopLevelDeclContext topLevelDecl(int i) {
			return getRuleContext(TopLevelDeclContext.class,i);
		}
		public SourceFileBodyContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceFileBody; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSourceFileBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSourceFileBody(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSourceFileBody(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final SourceFileBodyContext sourceFileBody() throws RecognitionException {
		SourceFileBodyContext _localctx = new SourceFileBodyContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_sourceFileBody);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1012);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				{
				setState(1009); packageClause();
				setState(1010); match(Semi);
				}
				break;
			}
			setState(1019);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1014); importDecl();
					setState(1015); match(Semi);
					}
					} 
				}
				setState(1021);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			}
			setState(1027);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1022); topLevelDecl();
					setState(1023); match(Semi);
					}
					} 
				}
				setState(1029);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceFileContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> EOF() { return getToken(AbstractGoParser.EOF, 0); }
		public SourceFileBodyContext sourceFileBody() {
			return getRuleContext(SourceFileBodyContext.class,0);
		}
		public SourceFileContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceFile; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterSourceFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitSourceFile(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitSourceFile(this);
			else return null;
		}
	}

	@RuleVersion(1)
	public final SourceFileContext sourceFile() throws RecognitionException {
		SourceFileContext _localctx = new SourceFileContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1030); sourceFileBody();
			setState(1031); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageClauseContext extends ParserRuleContext<Token> {
		public PackageNameContext packageName;
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public PackageClauseContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageClause; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterPackageClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitPackageClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitPackageClause(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final PackageClauseContext packageClause() throws RecognitionException {
		PackageClauseContext _localctx = new PackageClauseContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1033); match(Package);
			setState(1034); _localctx.packageName = packageName();
			addPackageName((_localctx.packageName!=null?(_localctx.packageName.start):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageNameContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public PackageNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitPackageName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitPackageName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1037); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclContext extends ParserRuleContext<Token> {
		public List<? extends ImportSpecContext> importSpec() {
			return getRuleContexts(ImportSpecContext.class);
		}
		public ImportSpecContext importSpec(int i) {
			return getRuleContext(ImportSpecContext.class,i);
		}
		public ImportDeclContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitImportDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitImportDecl(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_importDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1039); match(Import);
			setState(1056);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
			case 1:
				{
				setState(1040); importSpec();
				}
				break;

			case 2:
				{
				setState(1041); match(LeftParen);
				setState(1053);
				switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					{
					setState(1042); importSpec();
					setState(1047);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(1043); match(Semi);
							setState(1044); importSpec();
							}
							} 
						}
						setState(1049);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
					}
					setState(1051);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
					case 1:
						{
						setState(1050); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(1055); match(RightParen);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportSpecContext extends ParserRuleContext<Token> {
		public Token dot;
		public PackageNameContext packageName;
		public ImportPathContext importPath;
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public ImportPathContext importPath() {
			return getRuleContext(ImportPathContext.class,0);
		}
		public ImportSpecContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSpec; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterImportSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitImportSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitImportSpec(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ImportSpecContext importSpec() throws RecognitionException {
		ImportSpecContext _localctx = new ImportSpecContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_importSpec);
		try {
			setState(1067);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1058); _localctx.dot = match(Dot);
				setState(1059); importPath();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1060); _localctx.packageName = packageName();
				setState(1061); importPath();
				addPackageName((_localctx.packageName!=null?(_localctx.packageName.start):null));
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1064); _localctx.importPath = importPath();
				addPackageName((_localctx.importPath!=null?(_localctx.importPath.start):null));
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportPathContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> StringLiteral() { return getToken(AbstractGoParser.StringLiteral, 0); }
		public ImportPathContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importPath; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterImportPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitImportPath(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitImportPath(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ImportPathContext importPath() throws RecognitionException {
		ImportPathContext _localctx = new ImportPathContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069); match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext<Token> _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 49: return operand_sempred((OperandContext)_localctx, predIndex);

		case 52: return qualifiedIdentifier_sempred((QualifiedIdentifierContext)_localctx, predIndex);

		case 64: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 6);

		case 3: return precpred(_ctx, 5);

		case 4: return precpred(_ctx, 4);

		case 5: return precpred(_ctx, 3);

		case 6: return precpred(_ctx, 2);

		case 7: return precpred(_ctx, 12);

		case 8: return precpred(_ctx, 11);

		case 9: return precpred(_ctx, 10);

		case 10: return precpred(_ctx, 9);

		case 11: return precpred(_ctx, 8);
		}
		return true;
	}
	private boolean qualifiedIdentifier_sempred(QualifiedIdentifierContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return isPackageName(_input.LT(1));
		}
		return true;
	}
	private boolean operand_sempred(OperandContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return isLiteralAllowed(_input.LT(1), _localctx);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\1S\u0430\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2"+
		"\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\2G\7G\2H\7H\2I\7I\2J\7J\2K\7K\2L\7L\2M\7M\2N\7N\2O\7"+
		"O\2P\7P\2Q\7Q\2R\7R\2S\7S\2T\7T\2U\7U\2V\7V\2W\7W\2X\7X\2Y\7Y\2Z\7Z\2"+
		"[\7[\2\\\7\\\2]\7]\2^\7^\2_\7_\2`\7`\2a\7a\2b\7b\2c\7c\2d\7d\2e\7e\2f"+
		"\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\2o\7o\2p\7p\2q\7q"+
		"\2r\7r\2s\7s\2t\7t\1\0\1\0\1\1\1\1\1\1\1\1\1\2\1\2\1\3\1\3\5\3\u00f5\b"+
		"\3\n\3\f\3\u00f8\t\3\1\4\1\4\1\4\1\4\1\5\1\5\1\6\1\6\1\6\1\6\1\6\1\6\3"+
		"\6\u0106\b\6\1\7\1\7\1\b\1\b\1\b\1\b\1\b\1\b\1\b\1\b\3\b\u0112\b\b\1\t"+
		"\1\t\1\t\1\t\1\t\1\n\1\n\1\13\1\13\1\f\1\f\1\f\1\f\1\r\1\r\1\r\1\r\1\r"+
		"\5\r\u0126\b\r\n\r\f\r\u0129\t\r\1\r\3\r\u012c\b\r\1\r\1\r\1\16\1\16\1"+
		"\16\1\16\3\16\u0134\b\16\1\16\3\16\u0137\b\16\1\17\3\17\u013a\b\17\1\17"+
		"\1\17\1\20\1\20\1\21\1\21\1\21\1\22\1\22\1\23\1\23\1\23\1\24\1\24\3\24"+
		"\u014a\b\24\1\25\1\25\3\25\u014e\b\25\1\26\1\26\1\26\3\26\u0153\b\26\3"+
		"\26\u0155\b\26\1\26\1\26\1\27\1\27\1\27\5\27\u015c\b\27\n\27\f\27\u015f"+
		"\t\27\1\30\3\30\u0162\b\30\1\30\3\30\u0165\b\30\1\30\1\30\1\31\1\31\1"+
		"\31\1\31\1\31\5\31\u016e\b\31\n\31\f\31\u0171\t\31\1\31\3\31\u0174\b\31"+
		"\3\31\u0176\b\31\1\31\1\31\1\32\1\32\1\32\1\32\3\32\u017e\b\32\1\33\1"+
		"\33\1\34\1\34\1\35\1\35\1\35\1\35\1\35\1\35\1\36\1\36\1\37\1\37\3\37\u018e"+
		"\b\37\1\37\1\37\3\37\u0192\b\37\1\37\1\37\1 \1 \1 \1 \5 \u019a\b \n \f"+
		" \u019d\t \1 \3 \u01a0\b \3 \u01a2\b \1 \1 \1!\1!\1!\3!\u01a9\b!\1\"\1"+
		"\"\1\"\3\"\u01ae\b\"\1#\1#\1#\1#\1#\1#\5#\u01b6\b#\n#\f#\u01b9\t#\1#\3"+
		"#\u01bc\b#\3#\u01be\b#\1#\3#\u01c1\b#\1$\1$\3$\u01c5\b$\1$\1$\3$\u01c9"+
		"\b$\1%\1%\1%\5%\u01ce\b%\n%\f%\u01d1\t%\1&\1&\1&\5&\u01d6\b&\n&\f&\u01d9"+
		"\t&\1\'\1\'\1\'\1\'\1\'\1\'\5\'\u01e1\b\'\n\'\f\'\u01e4\t\'\1\'\3\'\u01e7"+
		"\b\'\3\'\u01e9\b\'\1\'\3\'\u01ec\b\'\1(\1(\1(\1)\1)\1)\1)\1)\1)\5)\u01f7"+
		"\b)\n)\f)\u01fa\t)\1)\3)\u01fd\b)\3)\u01ff\b)\1)\3)\u0202\b)\1*\1*\1*"+
		"\1*\3*\u0208\b*\1*\1*\3*\u020c\b*\1+\1+\1+\1+\1,\1,\1,\1,\3,\u0216\b,"+
		"\1-\1-\1.\1.\1.\1.\1.\3.\u021f\b.\1/\1/\3/\u0223\b/\1/\3/\u0226\b/\1/"+
		"\1/\1/\1\60\1\60\1\61\1\61\1\61\1\61\1\61\1\61\1\61\1\61\3\61\u0235\b"+
		"\61\1\62\1\62\1\62\3\62\u023a\b\62\1\63\1\63\1\64\1\64\1\64\1\64\3\64"+
		"\u0242\b\64\1\64\1\64\1\65\1\65\1\65\1\65\1\66\1\66\1\66\1\66\1\66\1\66"+
		"\3\66\u0250\b\66\1\67\1\67\1\67\18\18\18\18\18\18\18\18\18\38\u025e\b"+
		"8\19\19\19\39\u0263\b9\39\u0265\b9\19\19\1:\1:\1:\5:\u026c\b:\n:\f:\u026f"+
		"\t:\1;\1;\1;\3;\u0274\b;\1;\1;\1<\1<\1=\1=\1>\1>\3>\u027e\b>\1?\1?\1?"+
		"\1@\1@\1@\1@\1@\1@\3@\u0289\b@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@"+
		"\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\3@\u02a5\b@\1@\1@\3@\u02a9\b@"+
		"\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\1@\3@\u02b6\b@\3@\u02b8\b@\1@\5@\u02bb"+
		"\b@\n@\f@\u02be\t@\1A\1A\3A\u02c2\bA\1B\1B\1B\1B\1B\1C\1C\1C\1C\1C\1C"+
		"\1C\1C\1C\1C\1C\1C\1C\1C\1C\3C\u02d8\bC\1D\1D\1D\1D\1D\1D\3D\u02e0\bD"+
		"\1D\3D\u02e3\bD\1E\1E\1F\1F\1F\1F\1G\1G\1H\1H\1I\1I\1I\1I\1J\1J\1K\1K"+
		"\1K\1L\1L\1L\1L\1M\1M\1M\3M\u02ff\bM\1N\1N\1O\1O\1P\1P\1P\1P\3P\u0309"+
		"\bP\1P\1P\1P\1P\1P\3P\u0310\bP\3P\u0312\bP\1Q\1Q\3Q\u0316\bQ\1R\1R\1R"+
		"\1R\3R\u031c\bR\1R\3R\u031f\bR\1R\1R\5R\u0323\bR\nR\fR\u0326\tR\1R\1R"+
		"\1S\1S\1S\1S\1S\5S\u032f\bS\nS\fS\u0332\tS\1S\3S\u0335\bS\3S\u0337\bS"+
		"\1T\1T\1T\3T\u033c\bT\1U\1U\1U\1U\3U\u0342\bU\1U\1U\1U\5U\u0347\bU\nU"+
		"\fU\u034a\tU\1U\1U\1V\1V\3V\u0350\bV\1V\1V\1V\1V\1V\1V\1W\1W\1W\1W\1W"+
		"\5W\u035d\bW\nW\fW\u0360\tW\1W\3W\u0363\bW\3W\u0365\bW\1X\1X\1X\3X\u036a"+
		"\bX\1Y\1Y\1Y\5Y\u036f\bY\nY\fY\u0372\tY\1Z\1Z\1Z\1Z\3Z\u0378\bZ\1Z\1Z"+
		"\1[\1[\1\\\3\\\u037f\b\\\1\\\1\\\3\\\u0383\b\\\1\\\1\\\3\\\u0387\b\\\1"+
		"]\1]\1^\1^\1_\1_\1_\3_\u0390\b_\1_\1_\3_\u0394\b_\1_\1_\1_\1`\1`\1`\1"+
		"a\1a\1a\5a\u039f\ba\na\fa\u03a2\ta\1a\1a\1b\1b\1b\1b\1b\5b\u03ab\bb\n"+
		"b\fb\u03ae\tb\1b\3b\u03b1\bb\3b\u03b3\bb\1c\1c\1c\3c\u03b8\bc\1c\3c\u03bb"+
		"\bc\1d\1d\1d\3d\u03c0\bd\1d\1d\3d\u03c4\bd\3d\u03c6\bd\1d\1d\1e\1e\1f"+
		"\1f\3f\u03ce\bf\1g\1g\3g\u03d2\bg\1h\1h\3h\u03d6\bh\1i\1i\1i\1j\1j\1k"+
		"\1k\1k\1l\1l\1l\1l\3l\u03e4\bl\3l\u03e6\bl\1l\1l\1m\1m\1m\3m\u03ed\bm"+
		"\1m\3m\u03f0\bm\1n\1n\1n\3n\u03f5\bn\1n\1n\1n\5n\u03fa\bn\nn\fn\u03fd"+
		"\tn\1n\1n\1n\5n\u0402\bn\nn\fn\u0405\tn\1o\1o\1o\1p\1p\1p\1p\1q\1q\1r"+
		"\1r\1r\1r\1r\1r\5r\u0416\br\nr\fr\u0419\tr\1r\3r\u041c\br\3r\u041e\br"+
		"\1r\3r\u0421\br\1s\1s\1s\1s\1s\1s\1s\1s\1s\3s\u042c\bs\1t\1t\1t\0\1\u0080"+
		"u\0\1\2\1\4\1\6\1\b\1\n\1\f\0\16\0\20\0\22\0\24\0\26\0\30\0\32\0\34\0"+
		"\36\0 \0\"\0$\0&\0(\0*\0,\0.\0\60\0\62\0\64\0\66\08\0:\0<\0>\0@\0B\0D"+
		"\0F\0H\0J\0L\0N\0P\0R\0T\0V\0X\0Z\0\\\0^\0`\0b\0d\0f\0h\0j\0l\0n\0p\0"+
		"r\0t\0v\0x\0z\0|\0~\0\u0080\0\u0082\0\u0084\0\u0086\0\u0088\0\u008a\0"+
		"\u008c\0\u008e\0\u0090\0\u0092\0\u0094\0\u0096\0\u0098\0\u009a\0\u009c"+
		"\0\u009e\0\u00a0\0\u00a2\0\u00a4\0\u00a6\0\u00a8\0\u00aa\0\u00ac\0\u00ae"+
		"\0\u00b0\0\u00b2\0\u00b4\0\u00b6\0\u00b8\0\u00ba\0\u00bc\0\u00be\0\u00c0"+
		"\0\u00c2\0\u00c4\0\u00c6\0\u00c8\0\u00ca\0\u00cc\0\u00ce\0\u00d0\0\u00d2"+
		"\0\u00d4\0\u00d6\0\u00d8\0\u00da\0\u00dc\0\u00de\0\u00e0\0\u00e2\0\u00e4"+
		"\0\u00e6\0\u00e8\0\0\t\1\64\65\2\1\1OR\5\33\35  \"\"\63\63::\2\35 #%\2"+
		"\33\34!\"\2\668;=\1\64\65\2&\',-\2(+-\60\u045f\0\u00ea\1\0\0\0\2\u00ec"+
		"\1\0\0\0\4\u00f0\1\0\0\0\6\u00f6\1\0\0\0\b\u00f9\1\0\0\0\n\u00fd\1\0\0"+
		"\0\f\u0105\1\0\0\0\16\u0107\1\0\0\0\20\u0111\1\0\0\0\22\u0113\1\0\0\0"+
		"\24\u0118\1\0\0\0\26\u011a\1\0\0\0\30\u011c\1\0\0\0\32\u0120\1\0\0\0\34"+
		"\u0133\1\0\0\0\36\u0139\1\0\0\0 \u013d\1\0\0\0\"\u013f\1\0\0\0$\u0142"+
		"\1\0\0\0&\u0144\1\0\0\0(\u0147\1\0\0\0*\u014d\1\0\0\0,\u014f\1\0\0\0."+
		"\u0158\1\0\0\0\60\u0161\1\0\0\0\62\u0168\1\0\0\0\64\u017d\1\0\0\0\66\u017f"+
		"\1\0\0\08\u0181\1\0\0\0:\u0183\1\0\0\0<\u0189\1\0\0\0>\u0191\1\0\0\0@"+
		"\u0195\1\0\0\0B\u01a8\1\0\0\0D\u01ad\1\0\0\0F\u01af\1\0\0\0H\u01c2\1\0"+
		"\0\0J\u01ca\1\0\0\0L\u01d2\1\0\0\0N\u01da\1\0\0\0P\u01ed\1\0\0\0R\u01f0"+
		"\1\0\0\0T\u0203\1\0\0\0V\u020d\1\0\0\0X\u0211\1\0\0\0Z\u0217\1\0\0\0\\"+
		"\u0219\1\0\0\0^\u0220\1\0\0\0`\u022a\1\0\0\0b\u0234\1\0\0\0d\u0239\1\0"+
		"\0\0f\u023b\1\0\0\0h\u0241\1\0\0\0j\u0245\1\0\0\0l\u024f\1\0\0\0n\u0251"+
		"\1\0\0\0p\u025d\1\0\0\0r\u025f\1\0\0\0t\u0268\1\0\0\0v\u0273\1\0\0\0x"+
		"\u0277\1\0\0\0z\u0279\1\0\0\0|\u027d\1\0\0\0~\u027f\1\0\0\0\u0080\u0288"+
		"\1\0\0\0\u0082\u02bf\1\0\0\0\u0084\u02c3\1\0\0\0\u0086\u02d7\1\0\0\0\u0088"+
		"\u02e2\1\0\0\0\u008a\u02e4\1\0\0\0\u008c\u02e6\1\0\0\0\u008e\u02ea\1\0"+
		"\0\0\u0090\u02ec\1\0\0\0\u0092\u02ee\1\0\0\0\u0094\u02f2\1\0\0\0\u0096"+
		"\u02f4\1\0\0\0\u0098\u02f7\1\0\0\0\u009a\u02fe\1\0\0\0\u009c\u0300\1\0"+
		"\0\0\u009e\u0302\1\0\0\0\u00a0\u0304\1\0\0\0\u00a2\u0315\1\0\0\0\u00a4"+
		"\u0317\1\0\0\0\u00a6\u0329\1\0\0\0\u00a8\u033b\1\0\0\0\u00aa\u033d\1\0"+
		"\0\0\u00ac\u034f\1\0\0\0\u00ae\u0357\1\0\0\0\u00b0\u0369\1\0\0\0\u00b2"+
		"\u036b\1\0\0\0\u00b4\u0373\1\0\0\0\u00b6\u037b\1\0\0\0\u00b8\u037e\1\0"+
		"\0\0\u00ba\u0388\1\0\0\0\u00bc\u038a\1\0\0\0\u00be\u038c\1\0\0\0\u00c0"+
		"\u0398\1\0\0\0\u00c2\u039b\1\0\0\0\u00c4\u03a5\1\0\0\0\u00c6\u03ba\1\0"+
		"\0\0\u00c8\u03c5\1\0\0\0\u00ca\u03c9\1\0\0\0\u00cc\u03cb\1\0\0\0\u00ce"+
		"\u03cf\1\0\0\0\u00d0\u03d3\1\0\0\0\u00d2\u03d7\1\0\0\0\u00d4\u03da\1\0"+
		"\0\0\u00d6\u03dc\1\0\0\0\u00d8\u03df\1\0\0\0\u00da\u03ef\1\0\0\0\u00dc"+
		"\u03f4\1\0\0\0\u00de\u0406\1\0\0\0\u00e0\u0409\1\0\0\0\u00e2\u040d\1\0"+
		"\0\0\u00e4\u040f\1\0\0\0\u00e6\u042b\1\0\0\0\u00e8\u042d\1\0\0\0\u00ea"+
		"\u00eb\1\0\0\0\u00eb\1\1\0\0\0\u00ec\u00ed\3\0\0\0\u00ed\u00ee\5\63\0"+
		"\0\u00ee\u00ef\3\u0080@\0\u00ef\3\1\0\0\0\u00f0\u00f1\7\0\0\0\u00f1\5"+
		"\1\0\0\0\u00f2\u00f3\5F\0\0\u00f3\u00f5\3\u0080@\0\u00f4\u00f2\1\0\0\0"+
		"\u00f5\u00f8\1\0\0\0\u00f6\u00f4\1\0\0\0\u00f6\u00f7\1\0\0\0\u00f7\7\1"+
		"\0\0\0\u00f8\u00f6\1\0\0\0\u00f9\u00fa\3\6\3\0\u00fa\u00fb\3\u009aM\0"+
		"\u00fb\u00fc\3L&\0\u00fc\t\1\0\0\0\u00fd\u00fe\1\0\0\0\u00fe\13\1\0\0"+
		"\0\u00ff\u0106\3\16\7\0\u0100\u0106\3\20\b\0\u0101\u0102\5@\0\0\u0102"+
		"\u0103\3\f\6\0\u0103\u0104\5A\0\0\u0104\u0106\1\0\0\0\u0105\u00ff\1\0"+
		"\0\0\u0105\u0100\1\0\0\0\u0105\u0101\1\0\0\0\u0106\r\1\0\0\0\u0107\u0108"+
		"\3h\64\0\u0108\17\1\0\0\0\u0109\u0112\3\22\t\0\u010a\u0112\3\32\r\0\u010b"+
		"\u0112\3\"\21\0\u010c\u0112\3&\23\0\u010d\u0112\3\62\31\0\u010e\u0112"+
		"\3\30\f\0\u010f\u0112\3:\35\0\u0110\u0112\3>\37\0\u0111\u0109\1\0\0\0"+
		"\u0111\u010a\1\0\0\0\u0111\u010b\1\0\0\0\u0111\u010c\1\0\0\0\u0111\u010d"+
		"\1\0\0\0\u0111\u010e\1\0\0\0\u0111\u010f\1\0\0\0\u0111\u0110\1\0\0\0\u0112"+
		"\21\1\0\0\0\u0113\u0114\5B\0\0\u0114\u0115\3\24\n\0\u0115\u0116\5C\0\0"+
		"\u0116\u0117\3\26\13\0\u0117\23\1\0\0\0\u0118\u0119\3\u0080@\0\u0119\25"+
		"\1\0\0\0\u011a\u011b\3\f\6\0\u011b\27\1\0\0\0\u011c\u011d\5B\0\0\u011d"+
		"\u011e\5C\0\0\u011e\u011f\3\26\13\0\u011f\31\1\0\0\0\u0120\u0121\5\27"+
		"\0\0\u0121\u0127\5D\0\0\u0122\u0123\3\34\16\0\u0123\u0124\5H\0\0\u0124"+
		"\u0126\1\0\0\0\u0125\u0122\1\0\0\0\u0126\u0129\1\0\0\0\u0127\u0125\1\0"+
		"\0\0\u0127\u0128\1\0\0\0\u0128\u012b\1\0\0\0\u0129\u0127\1\0\0\0\u012a"+
		"\u012c\3\34\16\0\u012b\u012a\1\0\0\0\u012b\u012c\1\0\0\0\u012c\u012d\1"+
		"\0\0\0\u012d\u012e\5E\0\0\u012e\33\1\0\0\0\u012f\u0130\3J%\0\u0130\u0131"+
		"\3\f\6\0\u0131\u0134\1\0\0\0\u0132\u0134\3\36\17\0\u0133\u012f\1\0\0\0"+
		"\u0133\u0132\1\0\0\0\u0134\u0136\1\0\0\0\u0135\u0137\3 \20\0\u0136\u0135"+
		"\1\0\0\0\u0136\u0137\1\0\0\0\u0137\35\1\0\0\0\u0138\u013a\5\35\0\0\u0139"+
		"\u0138\1\0\0\0\u0139\u013a\1\0\0\0\u013a\u013b\1\0\0\0\u013b\u013c\3\16"+
		"\7\0\u013c\37\1\0\0\0\u013d\u013e\5R\0\0\u013e!\1\0\0\0\u013f\u0140\5"+
		"\35\0\0\u0140\u0141\3$\22\0\u0141#\1\0\0\0\u0142\u0143\3\f\6\0\u0143%"+
		"\1\0\0\0\u0144\u0145\5\f\0\0\u0145\u0146\3(\24\0\u0146\'\1\0\0\0\u0147"+
		"\u0149\3,\26\0\u0148\u014a\3*\25\0\u0149\u0148\1\0\0\0\u0149\u014a\1\0"+
		"\0\0\u014a)\1\0\0\0\u014b\u014e\3,\26\0\u014c\u014e\3\f\6\0\u014d\u014b"+
		"\1\0\0\0\u014d\u014c\1\0\0\0\u014e+\1\0\0\0\u014f\u0154\5@\0\0\u0150\u0152"+
		"\3.\27\0\u0151\u0153\5F\0\0\u0152\u0151\1\0\0\0\u0152\u0153\1\0\0\0\u0153"+
		"\u0155\1\0\0\0\u0154\u0150\1\0\0\0\u0154\u0155\1\0\0\0\u0155\u0156\1\0"+
		"\0\0\u0156\u0157\5A\0\0\u0157-\1\0\0\0\u0158\u015d\3\60\30\0\u0159\u015a"+
		"\5F\0\0\u015a\u015c\3\60\30\0\u015b\u0159\1\0\0\0\u015c\u015f\1\0\0\0"+
		"\u015d\u015b\1\0\0\0\u015d\u015e\1\0\0\0\u015e/\1\0\0\0\u015f\u015d\1"+
		"\0\0\0\u0160\u0162\3J%\0\u0161\u0160\1\0\0\0\u0161\u0162\1\0\0\0\u0162"+
		"\u0164\1\0\0\0\u0163\u0165\5?\0\0\u0164\u0163\1\0\0\0\u0164\u0165\1\0"+
		"\0\0\u0165\u0166\1\0\0\0\u0166\u0167\3\f\6\0\u0167\61\1\0\0\0\u0168\u0169"+
		"\5\21\0\0\u0169\u0175\5D\0\0\u016a\u016f\3\64\32\0\u016b\u016c\5H\0\0"+
		"\u016c\u016e\3\64\32\0\u016d\u016b\1\0\0\0\u016e\u0171\1\0\0\0\u016f\u016d"+
		"\1\0\0\0\u016f\u0170\1\0\0\0\u0170\u0173\1\0\0\0\u0171\u016f\1\0\0\0\u0172"+
		"\u0174\5H\0\0\u0173\u0172\1\0\0\0\u0173\u0174\1\0\0\0\u0174\u0176\1\0"+
		"\0\0\u0175\u016a\1\0\0\0\u0175\u0176\1\0\0\0\u0176\u0177\1\0\0\0\u0177"+
		"\u0178\5E\0\0\u0178\63\1\0\0\0\u0179\u017a\3\66\33\0\u017a\u017b\3(\24"+
		"\0\u017b\u017e\1\0\0\0\u017c\u017e\38\34\0\u017d\u0179\1\0\0\0\u017d\u017c"+
		"\1\0\0\0\u017e\65\1\0\0\0\u017f\u0180\5J\0\0\u0180\67\1\0\0\0\u0181\u0182"+
		"\3\16\7\0\u01829\1\0\0\0\u0183\u0184\5\22\0\0\u0184\u0185\5B\0\0\u0185"+
		"\u0186\3<\36\0\u0186\u0187\5C\0\0\u0187\u0188\3\26\13\0\u0188;\1\0\0\0"+
		"\u0189\u018a\3\f\6\0\u018a=\1\0\0\0\u018b\u018d\5\4\0\0\u018c\u018e\5"+
		"\63\0\0\u018d\u018c\1\0\0\0\u018d\u018e\1\0\0\0\u018e\u0192\1\0\0\0\u018f"+
		"\u0190\5\63\0\0\u0190\u0192\5\4\0\0\u0191\u018b\1\0\0\0\u0191\u018f\1"+
		"\0\0\0\u0192\u0193\1\0\0\0\u0193\u0194\3\26\13\0\u0194?\1\0\0\0\u0195"+
		"\u01a1\5D\0\0\u0196\u019b\3\u0086C\0\u0197\u0198\5H\0\0\u0198\u019a\3"+
		"\u0086C\0\u0199\u0197\1\0\0\0\u019a\u019d\1\0\0\0\u019b\u0199\1\0\0\0"+
		"\u019b\u019c\1\0\0\0\u019c\u019f\1\0\0\0\u019d\u019b\1\0\0\0\u019e\u01a0"+
		"\5H\0\0\u019f\u019e\1\0\0\0\u019f\u01a0\1\0\0\0\u01a0\u01a2\1\0\0\0\u01a1"+
		"\u0196\1\0\0\0\u01a1\u01a2\1\0\0\0\u01a2\u01a3\1\0\0\0\u01a3\u01a4\5E"+
		"\0\0\u01a4A\1\0\0\0\u01a5\u01a9\3F#\0\u01a6\u01a9\3N\'\0\u01a7\u01a9\3"+
		"R)\0\u01a8\u01a5\1\0\0\0\u01a8\u01a6\1\0\0\0\u01a8\u01a7\1\0\0\0\u01a9"+
		"C\1\0\0\0\u01aa\u01ae\3B!\0\u01ab\u01ae\3X,\0\u01ac\u01ae\3\\.\0\u01ad"+
		"\u01aa\1\0\0\0\u01ad\u01ab\1\0\0\0\u01ad\u01ac\1\0\0\0\u01aeE\1\0\0\0"+
		"\u01af\u01c0\5\5\0\0\u01b0\u01c1\3H$\0\u01b1\u01bd\5@\0\0\u01b2\u01b7"+
		"\3H$\0\u01b3\u01b4\5H\0\0\u01b4\u01b6\3H$\0\u01b5\u01b3\1\0\0\0\u01b6"+
		"\u01b9\1\0\0\0\u01b7\u01b5\1\0\0\0\u01b7\u01b8\1\0\0\0\u01b8\u01bb\1\0"+
		"\0\0\u01b9\u01b7\1\0\0\0\u01ba\u01bc\5H\0\0\u01bb\u01ba\1\0\0\0\u01bb"+
		"\u01bc\1\0\0\0\u01bc\u01be\1\0\0\0\u01bd\u01b2\1\0\0\0\u01bd\u01be\1\0"+
		"\0\0\u01be\u01bf\1\0\0\0\u01bf\u01c1\5A\0\0\u01c0\u01b0\1\0\0\0\u01c0"+
		"\u01b1\1\0\0\0\u01c1G\1\0\0\0\u01c2\u01c8\3J%\0\u01c3\u01c5\3\f\6\0\u01c4"+
		"\u01c3\1\0\0\0\u01c4\u01c5\1\0\0\0\u01c5\u01c6\1\0\0\0\u01c6\u01c7\59"+
		"\0\0\u01c7\u01c9\3L&\0\u01c8\u01c4\1\0\0\0\u01c8\u01c9\1\0\0\0\u01c9I"+
		"\1\0\0\0\u01ca\u01cf\5J\0\0\u01cb\u01cc\5F\0\0\u01cc\u01ce\5J\0\0\u01cd"+
		"\u01cb\1\0\0\0\u01ce\u01d1\1\0\0\0\u01cf\u01cd\1\0\0\0\u01cf\u01d0\1\0"+
		"\0\0\u01d0K\1\0\0\0\u01d1\u01cf\1\0\0\0\u01d2\u01d7\3\u0080@\0\u01d3\u01d4"+
		"\5F\0\0\u01d4\u01d6\3\u0080@\0\u01d5\u01d3\1\0\0\0\u01d6\u01d9\1\0\0\0"+
		"\u01d7\u01d5\1\0\0\0\u01d7\u01d8\1\0\0\0\u01d8M\1\0\0\0\u01d9\u01d7\1"+
		"\0\0\0\u01da\u01eb\5\31\0\0\u01db\u01ec\3P(\0\u01dc\u01e8\5@\0\0\u01dd"+
		"\u01e2\3P(\0\u01de\u01df\5H\0\0\u01df\u01e1\3P(\0\u01e0\u01de\1\0\0\0"+
		"\u01e1\u01e4\1\0\0\0\u01e2\u01e0\1\0\0\0\u01e2\u01e3\1\0\0\0\u01e3\u01e6"+
		"\1\0\0\0\u01e4\u01e2\1\0\0\0\u01e5\u01e7\5H\0\0\u01e6\u01e5\1\0\0\0\u01e6"+
		"\u01e7\1\0\0\0\u01e7\u01e9\1\0\0\0\u01e8\u01dd\1\0\0\0\u01e8\u01e9\1\0"+
		"\0\0\u01e9\u01ea\1\0\0\0\u01ea\u01ec\5A\0\0\u01eb\u01db\1\0\0\0\u01eb"+
		"\u01dc\1\0\0\0\u01ecO\1\0\0\0\u01ed\u01ee\5J\0\0\u01ee\u01ef\3\f\6\0\u01ef"+
		"Q\1\0\0\0\u01f0\u0201\5\32\0\0\u01f1\u0202\3T*\0\u01f2\u01fe\5@\0\0\u01f3"+
		"\u01f8\3T*\0\u01f4\u01f5\5H\0\0\u01f5\u01f7\3T*\0\u01f6\u01f4\1\0\0\0"+
		"\u01f7\u01fa\1\0\0\0\u01f8\u01f6\1\0\0\0\u01f8\u01f9\1\0\0\0\u01f9\u01fc"+
		"\1\0\0\0\u01fa\u01f8\1\0\0\0\u01fb\u01fd\5H\0\0\u01fc\u01fb\1\0\0\0\u01fc"+
		"\u01fd\1\0\0\0\u01fd\u01ff\1\0\0\0\u01fe\u01f3\1\0\0\0\u01fe\u01ff\1\0"+
		"\0\0\u01ff\u0200\1\0\0\0\u0200\u0202\5A\0\0\u0201\u01f1\1\0\0\0\u0201"+
		"\u01f2\1\0\0\0\u0202S\1\0\0\0\u0203\u020b\3J%\0\u0204\u0207\3\f\6\0\u0205"+
		"\u0206\59\0\0\u0206\u0208\3L&\0\u0207\u0205\1\0\0\0\u0207\u0208\1\0\0"+
		"\0\u0208\u020c\1\0\0\0\u0209\u020a\59\0\0\u020a\u020c\3L&\0\u020b\u0204"+
		"\1\0\0\0\u020b\u0209\1\0\0\0\u020cU\1\0\0\0\u020d\u020e\3J%\0\u020e\u020f"+
		"\5>\0\0\u020f\u0210\3L&\0\u0210W\1\0\0\0\u0211\u0212\5\f\0\0\u0212\u0213"+
		"\5J\0\0\u0213\u0215\3(\24\0\u0214\u0216\3Z-\0\u0215\u0214\1\0\0\0\u0215"+
		"\u0216\1\0\0\0\u0216Y\1\0\0\0\u0217\u0218\3@ \0\u0218[\1\0\0\0\u0219\u021a"+
		"\5\f\0\0\u021a\u021b\3^/\0\u021b\u021c\3\66\33\0\u021c\u021e\3(\24\0\u021d"+
		"\u021f\3Z-\0\u021e\u021d\1\0\0\0\u021e\u021f\1\0\0\0\u021f]\1\0\0\0\u0220"+
		"\u0222\5@\0\0\u0221\u0223\5J\0\0\u0222\u0221\1\0\0\0\u0222\u0223\1\0\0"+
		"\0\u0223\u0225\1\0\0\0\u0224\u0226\5\35\0\0\u0225\u0224\1\0\0\0\u0225"+
		"\u0226\1\0\0\0\u0226\u0227\1\0\0\0\u0227\u0228\3`\60\0\u0228\u0229\5A"+
		"\0\0\u0229_\1\0\0\0\u022a\u022b\5J\0\0\u022ba\1\0\0\0\u022c\u022d\4\61"+
		"\0\1\u022d\u0235\3d\62\0\u022e\u0235\3h\64\0\u022f\u0235\3j\65\0\u0230"+
		"\u0231\5@\0\0\u0231\u0232\3\u0080@\0\u0232\u0233\5A\0\0\u0233\u0235\1"+
		"\0\0\0\u0234\u022c\1\0\0\0\u0234\u022e\1\0\0\0\u0234\u022f\1\0\0\0\u0234"+
		"\u0230\1\0\0\0\u0235c\1\0\0\0\u0236\u023a\3f\63\0\u0237\u023a\3n\67\0"+
		"\u0238\u023a\3~?\0\u0239\u0236\1\0\0\0\u0239\u0237\1\0\0\0\u0239\u0238"+
		"\1\0\0\0\u023ae\1\0\0\0\u023b\u023c\7\1\0\0\u023cg\1\0\0\0\u023d\u023e"+
		"\4\64\1\0\u023e\u023f\3\u00e2q\0\u023f\u0240\5G\0\0\u0240\u0242\1\0\0"+
		"\0\u0241\u023d\1\0\0\0\u0241\u0242\1\0\0\0\u0242\u0243\1\0\0\0\u0243\u0244"+
		"\5J\0\0\u0244i\1\0\0\0\u0245\u0246\3l\66\0\u0246\u0247\5G\0\0\u0247\u0248"+
		"\3\66\33\0\u0248k\1\0\0\0\u0249\u0250\3\16\7\0\u024a\u024b\5@\0\0\u024b"+
		"\u024c\5\35\0\0\u024c\u024d\3\16\7\0\u024d\u024e\5A\0\0\u024e\u0250\1"+
		"\0\0\0\u024f\u0249\1\0\0\0\u024f\u024a\1\0\0\0\u0250m\1\0\0\0\u0251\u0252"+
		"\3p8\0\u0252\u0253\3r9\0\u0253o\1\0\0\0\u0254\u025e\3\32\r\0\u0255\u025e"+
		"\3\22\t\0\u0256\u0257\5B\0\0\u0257\u0258\5?\0\0\u0258\u0259\5C\0\0\u0259"+
		"\u025e\3\26\13\0\u025a\u025e\3\30\f\0\u025b\u025e\3:\35\0\u025c\u025e"+
		"\3\16\7\0\u025d\u0254\1\0\0\0\u025d\u0255\1\0\0\0\u025d\u0256\1\0\0\0"+
		"\u025d\u025a\1\0\0\0\u025d\u025b\1\0\0\0\u025d\u025c\1\0\0\0\u025eq\1"+
		"\0\0\0\u025f\u0264\5D\0\0\u0260\u0262\3t:\0\u0261\u0263\5F\0\0\u0262\u0261"+
		"\1\0\0\0\u0262\u0263\1\0\0\0\u0263\u0265\1\0\0\0\u0264\u0260\1\0\0\0\u0264"+
		"\u0265\1\0\0\0\u0265\u0266\1\0\0\0\u0266\u0267\5E\0\0\u0267s\1\0\0\0\u0268"+
		"\u026d\3v;\0\u0269\u026a\5F\0\0\u026a\u026c\3v;\0\u026b\u0269\1\0\0\0"+
		"\u026c\u026f\1\0\0\0\u026d\u026b\1\0\0\0\u026d\u026e\1\0\0\0\u026eu\1"+
		"\0\0\0\u026f\u026d\1\0\0\0\u0270\u0271\3x<\0\u0271\u0272\5I\0\0\u0272"+
		"\u0274\1\0\0\0\u0273\u0270\1\0\0\0\u0273\u0274\1\0\0\0\u0274\u0275\1\0"+
		"\0\0\u0275\u0276\3|>\0\u0276w\1\0\0\0\u0277\u0278\3z=\0\u0278y\1\0\0\0"+
		"\u0279\u027a\3\u0080@\0\u027a{\1\0\0\0\u027b\u027e\3\u0080@\0\u027c\u027e"+
		"\3r9\0\u027d\u027b\1\0\0\0\u027d\u027c\1\0\0\0\u027e}\1\0\0\0\u027f\u0280"+
		"\3&\23\0\u0280\u0281\3Z-\0\u0281\177\1\0\0\0\u0282\u0283\6@\uffff\0\u0283"+
		"\u0284\7\2\0\0\u0284\u0289\3\u0080@\7\u0285\u0289\3\u0084B\0\u0286\u0289"+
		"\3\u00d8l\0\u0287\u0289\3b\61\0\u0288\u0282\1\0\0\0\u0288\u0285\1\0\0"+
		"\0\u0288\u0286\1\0\0\0\u0288\u0287\1\0\0\0\u0289\u02bc\1\0\0\0\u028a\u028b"+
		"\n\6\0\0\u028b\u028c\7\3\0\0\u028c\u02bb\3\u0080@\7\u028d\u028e\n\5\0"+
		"\0\u028e\u028f\7\4\0\0\u028f\u02bb\3\u0080@\6\u0290\u0291\n\4\0\0\u0291"+
		"\u0292\7\5\0\0\u0292\u02bb\3\u0080@\5\u0293\u0294\n\3\0\0\u0294\u0295"+
		"\5\61\0\0\u0295\u02bb\3\u0080@\4\u0296\u0297\n\2\0\0\u0297\u0298\5\62"+
		"\0\0\u0298\u02bb\3\u0080@\3\u0299\u029a\n\f\0\0\u029a\u029b\5G\0\0\u029b"+
		"\u02bb\5J\0\0\u029c\u029d\n\13\0\0\u029d\u029e\5B\0\0\u029e\u029f\3\u0080"+
		"@\0\u029f\u02a0\5C\0\0\u02a0\u02bb\1\0\0\0\u02a1\u02a2\n\n\0\0\u02a2\u02a4"+
		"\5B\0\0\u02a3\u02a5\3\u0080@\0\u02a4\u02a3\1\0\0\0\u02a4\u02a5\1\0\0\0"+
		"\u02a5\u02a6\1\0\0\0\u02a6\u02a8\5I\0\0\u02a7\u02a9\3\u0080@\0\u02a8\u02a7"+
		"\1\0\0\0\u02a8\u02a9\1\0\0\0\u02a9\u02aa\1\0\0\0\u02aa\u02bb\5C\0\0\u02ab"+
		"\u02ac\n\t\0\0\u02ac\u02ad\5G\0\0\u02ad\u02ae\5@\0\0\u02ae\u02af\3\f\6"+
		"\0\u02af\u02b0\5A\0\0\u02b0\u02bb\1\0\0\0\u02b1\u02b2\n\b\0\0\u02b2\u02b7"+
		"\5@\0\0\u02b3\u02b5\3\u0082A\0\u02b4\u02b6\5F\0\0\u02b5\u02b4\1\0\0\0"+
		"\u02b5\u02b6\1\0\0\0\u02b6\u02b8\1\0\0\0\u02b7\u02b3\1\0\0\0\u02b7\u02b8"+
		"\1\0\0\0\u02b8\u02b9\1\0\0\0\u02b9\u02bb\5A\0\0\u02ba\u028a\1\0\0\0\u02ba"+
		"\u028d\1\0\0\0\u02ba\u0290\1\0\0\0\u02ba\u0293\1\0\0\0\u02ba\u0296\1\0"+
		"\0\0\u02ba\u0299\1\0\0\0\u02ba\u029c\1\0\0\0\u02ba\u02a1\1\0\0\0\u02ba"+
		"\u02ab\1\0\0\0\u02ba\u02b1\1\0\0\0\u02bb\u02be\1\0\0\0\u02bc\u02ba\1\0"+
		"\0\0\u02bc\u02bd\1\0\0\0\u02bd\u0081\1\0\0\0\u02be\u02bc\1\0\0\0\u02bf"+
		"\u02c1\3L&\0\u02c0\u02c2\5?\0\0\u02c1\u02c0\1\0\0\0\u02c1\u02c2\1\0\0"+
		"\0\u02c2\u0083\1\0\0\0\u02c3\u02c4\3\f\6\0\u02c4\u02c5\5@\0\0\u02c5\u02c6"+
		"\3\u0080@\0\u02c6\u02c7\5A\0\0\u02c7\u0085\1\0\0\0\u02c8\u02d8\3B!\0\u02c9"+
		"\u02d8\3\u008cF\0\u02ca\u02d8\3@ \0\u02cb\u02d8\3\u00c0`\0\u02cc\u02d8"+
		"\3\u00ccf\0\u02cd\u02d8\3\u00ceg\0\u02ce\u02d8\3\u00d0h\0\u02cf\u02d8"+
		"\3\u00d2i\0\u02d0\u02d8\3\u00d4j\0\u02d1\u02d8\3\u00a0P\0\u02d2\u02d8"+
		"\3\u00a2Q\0\u02d3\u02d8\3\u00c2a\0\u02d4\u02d8\3\u00b4Z\0\u02d5\u02d8"+
		"\3\u00d6k\0\u02d6\u02d8\3\u0088D\0\u02d7\u02c8\1\0\0\0\u02d7\u02c9\1\0"+
		"\0\0\u02d7\u02ca\1\0\0\0\u02d7\u02cb\1\0\0\0\u02d7\u02cc\1\0\0\0\u02d7"+
		"\u02cd\1\0\0\0\u02d7\u02ce\1\0\0\0\u02d7\u02cf\1\0\0\0\u02d7\u02d0\1\0"+
		"\0\0\u02d7\u02d1\1\0\0\0\u02d7\u02d2\1\0\0\0\u02d7\u02d3\1\0\0\0\u02d7"+
		"\u02d4\1\0\0\0\u02d7\u02d5\1\0\0\0\u02d7\u02d6\1\0\0\0\u02d8\u0087\1\0"+
		"\0\0\u02d9\u02e3\3V+\0\u02da\u02df\3\u0080@\0\u02db\u02e0\3\2\1\0\u02dc"+
		"\u02e0\3\4\2\0\u02dd\u02e0\3\b\4\0\u02de\u02e0\3\n\5\0\u02df\u02db\1\0"+
		"\0\0\u02df\u02dc\1\0\0\0\u02df\u02dd\1\0\0\0\u02df\u02de\1\0\0\0\u02e0"+
		"\u02e3\1\0\0\0\u02e1\u02e3\3\u008aE\0\u02e2\u02d9\1\0\0\0\u02e2\u02da"+
		"\1\0\0\0\u02e2\u02e1\1\0\0\0\u02e3\u0089\1\0\0\0\u02e4\u02e5\1\0\0\0\u02e5"+
		"\u008b\1\0\0\0\u02e6\u02e7\3\u008eG\0\u02e7\u02e8\5I\0\0\u02e8\u02e9\3"+
		"\u0086C\0\u02e9\u008d\1\0\0\0\u02ea\u02eb\5J\0\0\u02eb\u008f\1\0\0\0\u02ec"+
		"\u02ed\3\u0080@\0\u02ed\u0091\1\0\0\0\u02ee\u02ef\3\u0094J\0\u02ef\u02f0"+
		"\5\63\0\0\u02f0\u02f1\3\u0080@\0\u02f1\u0093\1\0\0\0\u02f2\u02f3\3\u0080"+
		"@\0\u02f3\u0095\1\0\0\0\u02f4\u02f5\3\u0080@\0\u02f5\u02f6\7\6\0\0\u02f6"+
		"\u0097\1\0\0\0\u02f7\u02f8\3L&\0\u02f8\u02f9\3\u009aM\0\u02f9\u02fa\3"+
		"L&\0\u02fa\u0099\1\0\0\0\u02fb\u02ff\3\u009cN\0\u02fc\u02ff\3\u009eO\0"+
		"\u02fd\u02ff\59\0\0\u02fe\u02fb\1\0\0\0\u02fe\u02fc\1\0\0\0\u02fe\u02fd"+
		"\1\0\0\0\u02ff\u009b\1\0\0\0\u0300\u0301\7\7\0\0\u0301\u009d\1\0\0\0\u0302"+
		"\u0303\7\b\0\0\u0303\u009f\1\0\0\0\u0304\u0308\5\17\0\0\u0305\u0306\3"+
		"\u0088D\0\u0306\u0307\5H\0\0\u0307\u0309\1\0\0\0\u0308\u0305\1\0\0\0\u0308"+
		"\u0309\1\0\0\0\u0309\u030a\1\0\0\0\u030a\u030b\3\u0080@\0\u030b\u0311"+
		"\3@ \0\u030c\u030f\5\t\0\0\u030d\u0310\3\u00a0P\0\u030e\u0310\3@ \0\u030f"+
		"\u030d\1\0\0\0\u030f\u030e\1\0\0\0\u0310\u0312\1\0\0\0\u0311\u030c\1\0"+
		"\0\0\u0311\u0312\1\0\0\0\u0312\u00a1\1\0\0\0\u0313\u0316\3\u00a4R\0\u0314"+
		"\u0316\3\u00aaU\0\u0315\u0313\1\0\0\0\u0315\u0314\1\0\0\0\u0316\u00a3"+
		"\1\0\0\0\u0317\u031b\5\30\0\0\u0318\u0319\3\u0088D\0\u0319\u031a\5H\0"+
		"\0\u031a\u031c\1\0\0\0\u031b\u0318\1\0\0\0\u031b\u031c\1\0\0\0\u031c\u031e"+
		"\1\0\0\0\u031d\u031f\3\u0080@\0\u031e\u031d\1\0\0\0\u031e\u031f\1\0\0"+
		"\0\u031f\u0320\1\0\0\0\u0320\u0324\5D\0\0\u0321\u0323\3\u00a6S\0\u0322"+
		"\u0321\1\0\0\0\u0323\u0326\1\0\0\0\u0324\u0322\1\0\0\0\u0324\u0325\1\0"+
		"\0\0\u0325\u0327\1\0\0\0\u0326\u0324\1\0\0\0\u0327\u0328\5E\0\0\u0328"+
		"\u00a5\1\0\0\0\u0329\u032a\3\u00a8T\0\u032a\u0336\5I\0\0\u032b\u0330\3"+
		"\u0086C\0\u032c\u032d\5H\0\0\u032d\u032f\3\u0086C\0\u032e\u032c\1\0\0"+
		"\0\u032f\u0332\1\0\0\0\u0330\u032e\1\0\0\0\u0330\u0331\1\0\0\0\u0331\u0334"+
		"\1\0\0\0\u0332\u0330\1\0\0\0\u0333\u0335\5H\0\0\u0334\u0333\1\0\0\0\u0334"+
		"\u0335\1\0\0\0\u0335\u0337\1\0\0\0\u0336\u032b\1\0\0\0\u0336\u0337\1\0"+
		"\0\0\u0337\u00a7\1\0\0\0\u0338\u0339\5\3\0\0\u0339\u033c\3L&\0\u033a\u033c"+
		"\5\7\0\0\u033b\u0338\1\0\0\0\u033b\u033a\1\0\0\0\u033c\u00a9\1\0\0\0\u033d"+
		"\u0341\5\30\0\0\u033e\u033f\3\u0088D\0\u033f\u0340\5H\0\0\u0340\u0342"+
		"\1\0\0\0\u0341\u033e\1\0\0\0\u0341\u0342\1\0\0\0\u0342\u0343\1\0\0\0\u0343"+
		"\u0344\3\u00acV\0\u0344\u0348\5D\0\0\u0345\u0347\3\u00aeW\0\u0346\u0345"+
		"\1\0\0\0\u0347\u034a\1\0\0\0\u0348\u0346\1\0\0\0\u0348\u0349\1\0\0\0\u0349"+
		"\u034b\1\0\0\0\u034a\u0348\1\0\0\0\u034b\u034c\5E\0\0\u034c\u00ab\1\0"+
		"\0\0\u034d\u034e\5J\0\0\u034e\u0350\5>\0\0\u034f\u034d\1\0\0\0\u034f\u0350"+
		"\1\0\0\0\u0350\u0351\1\0\0\0\u0351\u0352\3\u0080@\0\u0352\u0353\5G\0\0"+
		"\u0353\u0354\5@\0\0\u0354\u0355\5\31\0\0\u0355\u0356\5A\0\0\u0356\u00ad"+
		"\1\0\0\0\u0357\u0358\3\u00b0X\0\u0358\u0364\5I\0\0\u0359\u035e\3\u0086"+
		"C\0\u035a\u035b\5H\0\0\u035b\u035d\3\u0086C\0\u035c\u035a\1\0\0\0\u035d"+
		"\u0360\1\0\0\0\u035e\u035c\1\0\0\0\u035e\u035f\1\0\0\0\u035f\u0362\1\0"+
		"\0\0\u0360\u035e\1\0\0\0\u0361\u0363\5H\0\0\u0362\u0361\1\0\0\0\u0362"+
		"\u0363\1\0\0\0\u0363\u0365\1\0\0\0\u0364\u0359\1\0\0\0\u0364\u0365\1\0"+
		"\0\0\u0365\u00af\1\0\0\0\u0366\u0367\5\3\0\0\u0367\u036a\3\u00b2Y\0\u0368"+
		"\u036a\5\7\0\0\u0369\u0366\1\0\0\0\u0369\u0368\1\0\0\0\u036a\u00b1\1\0"+
		"\0\0\u036b\u0370\3\f\6\0\u036c\u036d\5F\0\0\u036d\u036f\3\f\6\0\u036e"+
		"\u036c\1\0\0\0\u036f\u0372\1\0\0\0\u0370\u036e\1\0\0\0\u0370\u0371\1\0"+
		"\0\0\u0371\u00b3\1\0\0\0\u0372\u0370\1\0\0\0\u0373\u0377\5\13\0\0\u0374"+
		"\u0378\3\u00b6[\0\u0375\u0378\3\u00b8\\\0\u0376\u0378\3\u00be_\0\u0377"+
		"\u0374\1\0\0\0\u0377\u0375\1\0\0\0\u0377\u0376\1\0\0\0\u0377\u0378\1\0"+
		"\0\0\u0378\u0379\1\0\0\0\u0379\u037a\3@ \0\u037a\u00b5\1\0\0\0\u037b\u037c"+
		"\3\u0080@\0\u037c\u00b7\1\0\0\0\u037d\u037f\3\u00ba]\0\u037e\u037d\1\0"+
		"\0\0\u037e\u037f\1\0\0\0\u037f\u0380\1\0\0\0\u0380\u0382\5H\0\0\u0381"+
		"\u0383\3\u00b6[\0\u0382\u0381\1\0\0\0\u0382\u0383\1\0\0\0\u0383\u0384"+
		"\1\0\0\0\u0384\u0386\5H\0\0\u0385\u0387\3\u00bc^\0\u0386\u0385\1\0\0\0"+
		"\u0386\u0387\1\0\0\0\u0387\u00b9\1\0\0\0\u0388\u0389\3\u0088D\0\u0389"+
		"\u00bb\1\0\0\0\u038a\u038b\3\u0088D\0\u038b\u00bd\1\0\0\0\u038c\u038f"+
		"\3\u0080@\0\u038d\u038e\5F\0\0\u038e\u0390\3\u0080@\0\u038f\u038d\1\0"+
		"\0\0\u038f\u0390\1\0\0\0\u0390\u0393\1\0\0\0\u0391\u0394\59\0\0\u0392"+
		"\u0394\5>\0\0\u0393\u0391\1\0\0\0\u0393\u0392\1\0\0\0\u0394\u0395\1\0"+
		"\0\0\u0395\u0396\5\24\0\0\u0396\u0397\3\u0080@\0\u0397\u00bf\1\0\0\0\u0398"+
		"\u0399\5\r\0\0\u0399\u039a\3\u0080@\0\u039a\u00c1\1\0\0\0\u039b\u039c"+
		"\5\26\0\0\u039c\u03a0\5D\0\0\u039d\u039f\3\u00c4b\0\u039e\u039d\1\0\0"+
		"\0\u039f\u03a2\1\0\0\0\u03a0\u039e\1\0\0\0\u03a0\u03a1\1\0\0\0\u03a1\u03a3"+
		"\1\0\0\0\u03a2\u03a0\1\0\0\0\u03a3\u03a4\5E\0\0\u03a4\u00c3\1\0\0\0\u03a5"+
		"\u03a6\3\u00c6c\0\u03a6\u03b2\5I\0\0\u03a7\u03ac\3\u0086C\0\u03a8\u03a9"+
		"\5H\0\0\u03a9\u03ab\3\u0086C\0\u03aa\u03a8\1\0\0\0\u03ab\u03ae\1\0\0\0"+
		"\u03ac\u03aa\1\0\0\0\u03ac\u03ad\1\0\0\0\u03ad\u03b0\1\0\0\0\u03ae\u03ac"+
		"\1\0\0\0\u03af\u03b1\5H\0\0\u03b0\u03af\1\0\0\0\u03b0\u03b1\1\0\0\0\u03b1"+
		"\u03b3\1\0\0\0\u03b2\u03a7\1\0\0\0\u03b2\u03b3\1\0\0\0\u03b3\u00c5\1\0"+
		"\0\0\u03b4\u03b7\5\3\0\0\u03b5\u03b8\3\u0092I\0\u03b6\u03b8\3\u00c8d\0"+
		"\u03b7\u03b5\1\0\0\0\u03b7\u03b6\1\0\0\0\u03b8\u03bb\1\0\0\0\u03b9\u03bb"+
		"\5\7\0\0\u03ba\u03b4\1\0\0\0\u03ba\u03b9\1\0\0\0\u03bb\u00c7\1\0\0\0\u03bc"+
		"\u03bf\3\u0080@\0\u03bd\u03be\5F\0\0\u03be\u03c0\3\u0080@\0\u03bf\u03bd"+
		"\1\0\0\0\u03bf\u03c0\1\0\0\0\u03c0\u03c3\1\0\0\0\u03c1\u03c4\59\0\0\u03c2"+
		"\u03c4\5>\0\0\u03c3\u03c1\1\0\0\0\u03c3\u03c2\1\0\0\0\u03c4\u03c6\1\0"+
		"\0\0\u03c5\u03bc\1\0\0\0\u03c5\u03c6\1\0\0\0\u03c6\u03c7\1\0\0\0\u03c7"+
		"\u03c8\3\u00cae\0\u03c8\u00c9\1\0\0\0\u03c9\u03ca\3\u0080@\0\u03ca\u00cb"+
		"\1\0\0\0\u03cb\u03cd\5\25\0\0\u03cc\u03ce\3L&\0\u03cd\u03cc\1\0\0\0\u03cd"+
		"\u03ce\1\0\0\0\u03ce\u00cd\1\0\0\0\u03cf\u03d1\5\2\0\0\u03d0\u03d2\3\u008e"+
		"G\0\u03d1\u03d0\1\0\0\0\u03d1\u03d2\1\0\0\0\u03d2\u00cf\1\0\0\0\u03d3"+
		"\u03d5\5\6\0\0\u03d4\u03d6\3\u008eG\0\u03d5\u03d4\1\0\0\0\u03d5\u03d6"+
		"\1\0\0\0\u03d6\u00d1\1\0\0\0\u03d7\u03d8\5\16\0\0\u03d8\u03d9\3\u008e"+
		"G\0\u03d9\u00d3\1\0\0\0\u03da\u03db\5\n\0\0\u03db\u00d5\1\0\0\0\u03dc"+
		"\u03dd\5\b\0\0\u03dd\u03de\3\u0080@\0\u03de\u00d7\1\0\0\0\u03df\u03e0"+
		"\5J\0\0\u03e0\u03e5\5@\0\0\u03e1\u03e3\3\u00dam\0\u03e2\u03e4\5F\0\0\u03e3"+
		"\u03e2\1\0\0\0\u03e3\u03e4\1\0\0\0\u03e4\u03e6\1\0\0\0\u03e5\u03e1\1\0"+
		"\0\0\u03e5\u03e6\1\0\0\0\u03e6\u03e7\1\0\0\0\u03e7\u03e8\5A\0\0\u03e8"+
		"\u00d9\1\0\0\0\u03e9\u03ec\3\f\6\0\u03ea\u03eb\5F\0\0\u03eb\u03ed\3L&"+
		"\0\u03ec\u03ea\1\0\0\0\u03ec\u03ed\1\0\0\0\u03ed\u03f0\1\0\0\0\u03ee\u03f0"+
		"\3L&\0\u03ef\u03e9\1\0\0\0\u03ef\u03ee\1\0\0\0\u03f0\u00db\1\0\0\0\u03f1"+
		"\u03f2\3\u00e0p\0\u03f2\u03f3\5H\0\0\u03f3\u03f5\1\0\0\0\u03f4\u03f1\1"+
		"\0\0\0\u03f4\u03f5\1\0\0\0\u03f5\u03fb\1\0\0\0\u03f6\u03f7\3\u00e4r\0"+
		"\u03f7\u03f8\5H\0\0\u03f8\u03fa\1\0\0\0\u03f9\u03f6\1\0\0\0\u03fa\u03fd"+
		"\1\0\0\0\u03fb\u03f9\1\0\0\0\u03fb\u03fc\1\0\0\0\u03fc\u0403\1\0\0\0\u03fd"+
		"\u03fb\1\0\0\0\u03fe\u03ff\3D\"\0\u03ff\u0400\5H\0\0\u0400\u0402\1\0\0"+
		"\0\u0401\u03fe\1\0\0\0\u0402\u0405\1\0\0\0\u0403\u0401\1\0\0\0\u0403\u0404"+
		"\1\0\0\0\u0404\u00dd\1\0\0\0\u0405\u0403\1\0\0\0\u0406\u0407\3\u00dcn"+
		"\0\u0407\u0408\5\uffff\0\0\u0408\u00df\1\0\0\0\u0409\u040a\5\23\0\0\u040a"+
		"\u040b\3\u00e2q\0\u040b\u040c\6p\uffff\0\u040c\u00e1\1\0\0\0\u040d\u040e"+
		"\5J\0\0\u040e\u00e3\1\0\0\0\u040f\u0420\5\20\0\0\u0410\u0421\3\u00e6s"+
		"\0\u0411\u041d\5@\0\0\u0412\u0417\3\u00e6s\0\u0413\u0414\5H\0\0\u0414"+
		"\u0416\3\u00e6s\0\u0415\u0413\1\0\0\0\u0416\u0419\1\0\0\0\u0417\u0415"+
		"\1\0\0\0\u0417\u0418\1\0\0\0\u0418\u041b\1\0\0\0\u0419\u0417\1\0\0\0\u041a"+
		"\u041c\5H\0\0\u041b\u041a\1\0\0\0\u041b\u041c\1\0\0\0\u041c\u041e\1\0"+
		"\0\0\u041d\u0412\1\0\0\0\u041d\u041e\1\0\0\0\u041e\u041f\1\0\0\0\u041f"+
		"\u0421\5A\0\0\u0420\u0410\1\0\0\0\u0420\u0411\1\0\0\0\u0421\u00e5\1\0"+
		"\0\0\u0422\u0423\5G\0\0\u0423\u042c\3\u00e8t\0\u0424\u0425\3\u00e2q\0"+
		"\u0425\u0426\3\u00e8t\0\u0426\u0427\6s\uffff\0\u0427\u042c\1\0\0\0\u0428"+
		"\u0429\3\u00e8t\0\u0429\u042a\6s\uffff\0\u042a\u042c\1\0\0\0\u042b\u0422"+
		"\1\0\0\0\u042b\u0424\1\0\0\0\u042b\u0428\1\0\0\0\u042c\u00e7\1\0\0\0\u042d"+
		"\u042e\5R\0\0\u042e\u00e9\1\0\0\0w\u00f6\u0105\u0111\u0127\u012b\u0133"+
		"\u0136\u0139\u0149\u014d\u0152\u0154\u015d\u0161\u0164\u016f\u0173\u0175"+
		"\u017d\u018d\u0191\u019b\u019f\u01a1\u01a8\u01ad\u01b7\u01bb\u01bd\u01c0"+
		"\u01c4\u01c8\u01cf\u01d7\u01e2\u01e6\u01e8\u01eb\u01f8\u01fc\u01fe\u0201"+
		"\u0207\u020b\u0215\u021e\u0222\u0225\u0234\u0239\u0241\u024f\u025d\u0262"+
		"\u0264\u026d\u0273\u027d\u0288\u02a4\u02a8\u02b5\u02b7\u02ba\u02bc\u02c1"+
		"\u02d7\u02df\u02e2\u02fe\u0308\u030f\u0311\u0315\u031b\u031e\u0324\u0330"+
		"\u0334\u0336\u033b\u0341\u0348\u034f\u035e\u0362\u0364\u0369\u0370\u0377"+
		"\u037e\u0382\u0386\u038f\u0393\u03a0\u03ac\u03b0\u03b2\u03b7\u03ba\u03bf"+
		"\u03c3\u03c5\u03cd\u03d1\u03d5\u03e3\u03e5\u03ec\u03ef\u03f4\u03fb\u0403"+
		"\u0417\u041b\u041d\u0420\u042b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	}
}