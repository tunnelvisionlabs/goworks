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
		RULE_argumentList = 60, RULE_conversion = 61, RULE_statement = 62, RULE_simpleStmt = 63, 
		RULE_emptyStmt = 64, RULE_labeledStmt = 65, RULE_label = 66, RULE_expressionStmt = 67, 
		RULE_sendStmt = 68, RULE_channel = 69, RULE_incDecStmt = 70, RULE_assignment = 71, 
		RULE_assignOp = 72, RULE_addAssignOp = 73, RULE_mulAssignOp = 74, RULE_ifStmt = 75, 
		RULE_switchStmt = 76, RULE_exprSwitchStmt = 77, RULE_exprCaseClause = 78, 
		RULE_exprSwitchCase = 79, RULE_typeSwitchStmt = 80, RULE_typeSwitchGuard = 81, 
		RULE_typeCaseClause = 82, RULE_typeSwitchCase = 83, RULE_typeList = 84, 
		RULE_forStmt = 85, RULE_condition = 86, RULE_forClause = 87, RULE_initStmt = 88, 
		RULE_postStmt = 89, RULE_rangeClause = 90, RULE_goStmt = 91, RULE_selectStmt = 92, 
		RULE_commClause = 93, RULE_commCase = 94, RULE_recvStmt = 95, RULE_recvExpr = 96, 
		RULE_returnStmt = 97, RULE_breakStmt = 98, RULE_continueStmt = 99, RULE_gotoStmt = 100, 
		RULE_fallthroughStmt = 101, RULE_deferStmt = 102, RULE_builtinCall = 103, 
		RULE_builtinArgs = 104, RULE_sourceFileBody = 105, RULE_sourceFile = 106, 
		RULE_packageClause = 107, RULE_packageName = 108, RULE_importDecl = 109, 
		RULE_importSpec = 110, RULE_importPath = 111;
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
		"elementIndex", "value", "functionLiteral", "expression", "argumentList", 
		"conversion", "statement", "simpleStmt", "emptyStmt", "labeledStmt", "label", 
		"expressionStmt", "sendStmt", "channel", "incDecStmt", "assignment", "assignOp", 
		"addAssignOp", "mulAssignOp", "ifStmt", "switchStmt", "exprSwitchStmt", 
		"exprCaseClause", "exprSwitchCase", "typeSwitchStmt", "typeSwitchGuard", 
		"typeCaseClause", "typeSwitchCase", "typeList", "forStmt", "condition", 
		"forClause", "initStmt", "postStmt", "rangeClause", "goStmt", "selectStmt", 
		"commClause", "commCase", "recvStmt", "recvExpr", "returnStmt", "breakStmt", 
		"continueStmt", "gotoStmt", "fallthroughStmt", "deferStmt", "builtinCall", 
		"builtinArgs", "sourceFileBody", "sourceFile", "packageClause", "packageName", 
		"importDecl", "importSpec", "importPath"
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

	public AbstractGoParser(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
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
		enterRule(_localctx, 0, RULE_type);
		try {
			setState(230);
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
				setState(225); typeLiteral();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(226); match(LeftParen);
				setState(227); type();
				setState(228); match(RightParen);
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
		enterRule(_localctx, 2, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); qualifiedIdentifier();
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
		enterRule(_localctx, 4, RULE_typeLiteral);
		try {
			setState(242);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(234); arrayType();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(235); structType();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236); pointerType();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(237); functionType();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(238); interfaceType();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(239); sliceType();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(240); mapType();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(241); channelType();
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
		enterRule(_localctx, 6, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); match(LeftBrack);
			setState(245); arrayLength();
			setState(246); match(RightBrack);
			setState(247); elementType();
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
		enterRule(_localctx, 8, RULE_arrayLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); expression(0);
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
		enterRule(_localctx, 10, RULE_elementType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251); type();
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
		enterRule(_localctx, 12, RULE_sliceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); match(LeftBrack);
			setState(254); match(RightBrack);
			setState(255); elementType();
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
		enterRule(_localctx, 14, RULE_structType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(257); match(Struct);
			setState(258); match(LeftBrace);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(259); fieldDecl();
					setState(260); match(Semi);
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(268);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(267); fieldDecl();
				}
				break;
			}
			setState(270); match(RightBrace);
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
		enterRule(_localctx, 16, RULE_fieldDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(272); identifierList();
				setState(273); type();
				}
				break;

			case 2:
				{
				setState(275); anonymousField();
				}
				break;
			}
			setState(279);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(278); tag();
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
		enterRule(_localctx, 18, RULE_anonymousField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(281); _localctx.ptr = match(Star);
				}
				break;
			}
			setState(284); typeName();
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
		enterRule(_localctx, 20, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286); match(StringLiteral);
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
		enterRule(_localctx, 22, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288); _localctx.ptr = match(Star);
			setState(289); baseType();
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
		enterRule(_localctx, 24, RULE_baseType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291); type();
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
		enterRule(_localctx, 26, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293); match(Func);
			setState(294); signature();
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
		enterRule(_localctx, 28, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296); parameters();
			setState(298);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(297); result();
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
		enterRule(_localctx, 30, RULE_result);
		try {
			setState(302);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300); parameters();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(301); type();
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
		enterRule(_localctx, 32, RULE_parameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); match(LeftParen);
			setState(309);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(305); parameterList();
				setState(307);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(306); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(311); match(RightParen);
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
		enterRule(_localctx, 34, RULE_parameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(313); parameterDecl();
			setState(318);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(314); match(Comma);
					setState(315); parameterDecl();
					}
					} 
				}
				setState(320);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		enterRule(_localctx, 36, RULE_parameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(321); identifierList();
				}
				break;
			}
			setState(325);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(324); _localctx.ellip = match(Ellipsis);
				}
				break;
			}
			setState(327); type();
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
		enterRule(_localctx, 38, RULE_interfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329); match(Interface);
			setState(330); match(LeftBrace);
			setState(342);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(331); methodSpec();
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(332); match(Semi);
						setState(333); methodSpec();
						}
						} 
					}
					setState(338);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
				}
				setState(340);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(339); match(Semi);
					}
					break;
				}
				}
				break;
			}
			setState(344); match(RightBrace);
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
		enterRule(_localctx, 40, RULE_methodSpec);
		try {
			setState(350);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(346); methodName();
				setState(347); signature();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(349); interfaceTypeName();
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
		enterRule(_localctx, 42, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352); match(IDENTIFIER);
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
		enterRule(_localctx, 44, RULE_interfaceTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354); typeName();
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
		enterRule(_localctx, 46, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356); match(Map);
			setState(357); match(LeftBrack);
			setState(358); keyType();
			setState(359); match(RightBrack);
			setState(360); elementType();
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
		enterRule(_localctx, 48, RULE_keyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362); type();
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
		enterRule(_localctx, 50, RULE_channelType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(364); match(Chan);
				setState(366);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(365); _localctx.send = match(LeftArrow);
					}
					break;
				}
				}
				break;

			case 2:
				{
				setState(368); _localctx.recv = match(LeftArrow);
				setState(369); match(Chan);
				}
				break;
			}
			setState(372); elementType();
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
		enterRule(_localctx, 52, RULE_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(374); match(LeftBrace);
			setState(386);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(375); statement();
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(376); match(Semi);
						setState(377); statement();
						}
						} 
					}
					setState(382);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(384);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(383); match(Semi);
					}
					break;
				}
				}
				break;
			}
			setState(388); match(RightBrace);
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
		enterRule(_localctx, 54, RULE_declaration);
		try {
			setState(393);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(390); constDecl();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(391); typeDecl();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(392); varDecl();
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
		enterRule(_localctx, 56, RULE_topLevelDecl);
		try {
			setState(398);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395); declaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396); functionDecl();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(397); methodDecl();
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
		enterRule(_localctx, 58, RULE_constDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(400); match(Const);
			setState(417);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(401); constSpec();
				}
				break;

			case 2:
				{
				setState(402); match(LeftParen);
				setState(414);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(403); constSpec();
					setState(408);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(404); match(Semi);
							setState(405); constSpec();
							}
							} 
						}
						setState(410);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
					}
					setState(412);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						setState(411); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(416); match(RightParen);
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
		enterRule(_localctx, 60, RULE_constSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419); identifierList();
			setState(425);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(421);
				switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
				case 1:
					{
					setState(420); type();
					}
					break;
				}
				setState(423); match(Equal);
				setState(424); expressionList();
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
		enterRule(_localctx, 62, RULE_identifierList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(427); match(IDENTIFIER);
			setState(432);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(428); match(Comma);
					setState(429); match(IDENTIFIER);
					}
					} 
				}
				setState(434);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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

	@RuleVersion(0)
	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expressionList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(435); expression(0);
			setState(440);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(436); match(Comma);
					setState(437); expression(0);
					}
					} 
				}
				setState(442);
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
		enterRule(_localctx, 66, RULE_typeDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(443); match(Type);
			setState(460);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(444); typeSpec();
				}
				break;

			case 2:
				{
				setState(445); match(LeftParen);
				setState(457);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(446); typeSpec();
					setState(451);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(447); match(Semi);
							setState(448); typeSpec();
							}
							} 
						}
						setState(453);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					setState(455);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						setState(454); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(459); match(RightParen);
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
		enterRule(_localctx, 68, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462); match(IDENTIFIER);
			setState(463); type();
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
		enterRule(_localctx, 70, RULE_varDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(465); match(Var);
			setState(482);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(466); varSpec();
				}
				break;

			case 2:
				{
				setState(467); match(LeftParen);
				setState(479);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(468); varSpec();
					setState(473);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(469); match(Semi);
							setState(470); varSpec();
							}
							} 
						}
						setState(475);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
					}
					setState(477);
					switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
					case 1:
						{
						setState(476); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(481); match(RightParen);
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
		enterRule(_localctx, 72, RULE_varSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484); identifierList();
			setState(492);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(485); type();
				setState(488);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(486); match(Equal);
					setState(487); expressionList();
					}
					break;
				}
				}
				break;

			case 2:
				{
				setState(490); match(Equal);
				setState(491); expressionList();
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
		enterRule(_localctx, 74, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494); identifierList();
			setState(495); match(ColonEqual);
			setState(496); expressionList();
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
		enterRule(_localctx, 76, RULE_functionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498); match(Func);
			setState(499); match(IDENTIFIER);
			setState(500); signature();
			setState(502);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(501); body();
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
		enterRule(_localctx, 78, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504); block();
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
		enterRule(_localctx, 80, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506); match(Func);
			setState(507); receiver();
			setState(508); methodName();
			setState(509); signature();
			setState(511);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(510); body();
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
		enterRule(_localctx, 82, RULE_receiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513); match(LeftParen);
			setState(515);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(514); match(IDENTIFIER);
				}
				break;
			}
			setState(518);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(517); _localctx.ptr = match(Star);
				}
				break;
			}
			setState(520); baseTypeName();
			setState(521); match(RightParen);
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
		enterRule(_localctx, 84, RULE_baseTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523); match(IDENTIFIER);
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
		enterRule(_localctx, 86, RULE_operand);
		try {
			setState(532);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(525); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(526); qualifiedIdentifier();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(527); methodExpr();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(528); match(LeftParen);
				setState(529); expression(0);
				setState(530); match(RightParen);
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
		enterRule(_localctx, 88, RULE_literal);
		try {
			setState(537);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(534); basicLiteral();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(535); compositeLiteral();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(536); functionLiteral();
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
		enterRule(_localctx, 90, RULE_basicLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			_la = _input.LA(1);
			if ( !(_la==INT_LITERAL || _la==IMAGINARY_LITERAL || _la==FLOAT_LITERAL || _la==CharLiteral || _la==StringLiteral) ) {
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
		enterRule(_localctx, 92, RULE_qualifiedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(541);
				if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "isPackageName(_input.LT(1))");
				setState(542); packageName();
				setState(543); _localctx.dot = match(Dot);
				}
				break;
			}
			setState(547); match(IDENTIFIER);
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
		enterRule(_localctx, 94, RULE_methodExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549); receiverType();
			setState(550); _localctx.dot = match(Dot);
			setState(551); methodName();
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
		enterRule(_localctx, 96, RULE_receiverType);
		try {
			setState(559);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(553); typeName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(554); match(LeftParen);
				setState(555); _localctx.ptr = match(Star);
				setState(556); typeName();
				setState(557); match(RightParen);
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
		enterRule(_localctx, 98, RULE_compositeLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561); literalType();
			setState(562); literalValue();
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
		enterRule(_localctx, 100, RULE_literalType);
		try {
			setState(573);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(564); structType();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(565); arrayType();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(566); match(LeftBrack);
				setState(567); match(Ellipsis);
				setState(568); match(RightBrack);
				setState(569); elementType();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(570); sliceType();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(571); mapType();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(572); typeName();
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
		enterRule(_localctx, 102, RULE_literalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575); match(LeftBrace);
			setState(580);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(576); elementList();
				setState(578);
				switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					{
					setState(577); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(582); match(RightBrace);
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
		enterRule(_localctx, 104, RULE_elementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(584); element();
			setState(589);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(585); match(Comma);
					setState(586); element();
					}
					} 
				}
				setState(591);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
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
		enterRule(_localctx, 106, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(592); key();
				setState(593); match(Colon);
				}
				break;
			}
			setState(597); value();
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
		public ElementIndexContext elementIndex() {
			return getRuleContext(ElementIndexContext.class,0);
		}
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
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

	@RuleVersion(0)
	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_key);
		try {
			setState(601);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(599); fieldName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(600); elementIndex();
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

	public static class FieldNameContext extends ParserRuleContext<Token> {
		public TerminalNode<Token> IDENTIFIER() { return getToken(AbstractGoParser.IDENTIFIER, 0); }
		public FieldNameContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitFieldName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitFieldName(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603); match(IDENTIFIER);
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

	public static class ElementIndexContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ElementIndexContext(ParserRuleContext<Token> parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementIndex; }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).enterElementIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserListener ) ((GoParserListener)listener).exitElementIndex(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserVisitor<?, ?> ) return ((GoParserVisitor<? super Token, ? extends Result>)visitor).visitElementIndex(this);
			else return null;
		}
	}

	@RuleVersion(0)
	public final ElementIndexContext elementIndex() throws RecognitionException {
		ElementIndexContext _localctx = new ElementIndexContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_elementIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605); expression(0);
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
		enterRule(_localctx, 114, RULE_value);
		try {
			setState(609);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(607); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(608); literalValue();
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
		enterRule(_localctx, 116, RULE_functionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611); functionType();
			setState(612); body();
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
		public int _p;
		public ExpressionContext(ParserRuleContext<Token> parent, int invokingState) { super(parent, invokingState); }
		public ExpressionContext(ParserRuleContext<Token> parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
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
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState, _p);
		ExpressionContext _prevctx = _localctx;
		int _startState = 118;
		enterRecursionRule(_localctx, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(620);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(615);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus || _la==Star || _la==Amp || _la==Caret || _la==LeftArrow || _la==Bang) ) {
					((UnaryExprContext)_localctx).op = _errHandler.recoverInline(this);
				}
				consume();
				setState(616); expression(7);
				}
				break;

			case 2:
				{
				_localctx = new ConversionOrCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(617); conversion();
				}
				break;

			case 3:
				{
				_localctx = new BuiltinCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(618); builtinCall();
				}
				break;

			case 4:
				{
				_localctx = new OperandExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(619); operand();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(672);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(670);
					switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(622);
						if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
						setState(623);
						((MultExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Star || _la==Slash || _la==Percent || _la==Amp || _la==LeftShift || _la==RightShift || _la==AmpCaret) ) {
							((MultExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(624); expression(7);
						}
						break;

					case 2:
						{
						_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(625);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(626);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus || _la==Pipe || _la==Caret) ) {
							((AddExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(627); expression(6);
						}
						break;

					case 3:
						{
						_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(628);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(629);
						((CompareExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EqualEqual || _la==LessThan || _la==GreaterThan || _la==BangEqual || _la==LessEqual || _la==GreaterEqual) ) {
							((CompareExprContext)_localctx).op = _errHandler.recoverInline(this);
						}
						consume();
						setState(630); expression(5);
						}
						break;

					case 4:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(631);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(632); match(And);
						setState(633); expression(4);
						}
						break;

					case 5:
						{
						_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(634);
						if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
						setState(635); match(Or);
						setState(636); expression(3);
						}
						break;

					case 6:
						{
						_localctx = new SelectorExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(637);
						if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
						setState(638); ((SelectorExprContext)_localctx).dot = match(Dot);
						setState(639); match(IDENTIFIER);
						}
						break;

					case 7:
						{
						_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(640);
						if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
						setState(641); match(LeftBrack);
						setState(642); expression(0);
						setState(643); match(RightBrack);
						}
						break;

					case 8:
						{
						_localctx = new SliceExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(645);
						if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
						setState(646); match(LeftBrack);
						setState(648);
						switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(647); ((SliceExprContext)_localctx).from = expression(0);
							}
							break;
						}
						setState(650); match(Colon);
						setState(652);
						switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
						case 1:
							{
							setState(651); ((SliceExprContext)_localctx).to = expression(0);
							}
							break;
						}
						setState(654); match(RightBrack);
						}
						break;

					case 9:
						{
						_localctx = new TypeAssertionExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(655);
						if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
						setState(656); ((TypeAssertionExprContext)_localctx).dot = match(Dot);
						setState(657); ((TypeAssertionExprContext)_localctx).lp = match(LeftParen);
						setState(658); type();
						setState(659); ((TypeAssertionExprContext)_localctx).rp = match(RightParen);
						}
						break;

					case 10:
						{
						_localctx = new CallExprContext(new ExpressionContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(661);
						if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
						setState(662); ((CallExprContext)_localctx).lp = match(LeftParen);
						setState(667);
						switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
						case 1:
							{
							setState(663); argumentList();
							setState(665);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
							case 1:
								{
								setState(664); match(Comma);
								}
								break;
							}
							}
							break;
						}
						setState(669); ((CallExprContext)_localctx).rp = match(RightParen);
						}
						break;
					}
					} 
				}
				setState(674);
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
		enterRule(_localctx, 120, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(675); expressionList();
			setState(677);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(676); _localctx.ellip = match(Ellipsis);
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
		enterRule(_localctx, 122, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(679); type();
			setState(680); match(LeftParen);
			setState(681); expression(0);
			setState(682); match(RightParen);
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
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
		enterRule(_localctx, 124, RULE_statement);
		try {
			setState(699);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(684); declaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(685); labeledStmt();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(686); simpleStmt();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(687); goStmt();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(688); returnStmt();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(689); breakStmt();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(690); continueStmt();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(691); gotoStmt();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(692); fallthroughStmt();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(693); block();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(694); ifStmt();
				}
				break;

			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(695); switchStmt();
				}
				break;

			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(696); selectStmt();
				}
				break;

			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(697); forStmt();
				}
				break;

			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(698); deferStmt();
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

	@RuleVersion(0)
	public final SimpleStmtContext simpleStmt() throws RecognitionException {
		SimpleStmtContext _localctx = new SimpleStmtContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_simpleStmt);
		try {
			setState(707);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(701); emptyStmt();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(702); expressionStmt();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(703); sendStmt();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(704); incDecStmt();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(705); assignment();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(706); shortVarDecl();
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
		enterRule(_localctx, 128, RULE_emptyStmt);
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
		enterRule(_localctx, 130, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711); label();
			setState(712); match(Colon);
			setState(713); statement();
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
		enterRule(_localctx, 132, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715); match(IDENTIFIER);
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

	@RuleVersion(0)
	public final ExpressionStmtContext expressionStmt() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717); expression(0);
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

	@RuleVersion(0)
	public final SendStmtContext sendStmt() throws RecognitionException {
		SendStmtContext _localctx = new SendStmtContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719); channel();
			setState(720); match(LeftArrow);
			setState(721); expression(0);
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

	@RuleVersion(0)
	public final ChannelContext channel() throws RecognitionException {
		ChannelContext _localctx = new ChannelContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(723); expression(0);
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

	@RuleVersion(0)
	public final IncDecStmtContext incDecStmt() throws RecognitionException {
		IncDecStmtContext _localctx = new IncDecStmtContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(725); expression(0);
			setState(726);
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
		public ExpressionListContext targets;
		public ExpressionListContext values;
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

	@RuleVersion(0)
	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728); _localctx.targets = expressionList();
			setState(729); assignOp();
			setState(730); _localctx.values = expressionList();
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
		enterRule(_localctx, 144, RULE_assignOp);
		try {
			setState(735);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(732); addAssignOp();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(733); mulAssignOp();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(734); match(Equal);
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
		enterRule(_localctx, 146, RULE_addAssignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(737);
			_la = _input.LA(1);
			if ( !(_la==PlusEqual || _la==MinusEqual || _la==PipeEqual || _la==CaretEqual) ) {
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
		enterRule(_localctx, 148, RULE_mulAssignOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(739);
			_la = _input.LA(1);
			if ( !(_la==StarEqual || _la==SlashEqual || _la==PercentEqual || _la==AmpEqual || _la==CaretEqual || _la==LeftShiftEqual || _la==RightShiftEqual || _la==AmpCaretEqual) ) {
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
		enterRule(_localctx, 150, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(741); match(If);
			setState(745);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(742); simpleStmt();
				setState(743); match(Semi);
				}
				break;
			}
			setState(747); expression(0);
			setState(748); block();
			setState(754);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(749); match(Else);
				setState(752);
				switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(750); ifStmt();
					}
					break;

				case 2:
					{
					setState(751); block();
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
		enterRule(_localctx, 152, RULE_switchStmt);
		try {
			setState(758);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(756); exprSwitchStmt();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(757); typeSwitchStmt();
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
		enterRule(_localctx, 154, RULE_exprSwitchStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(760); match(Switch);
			setState(764);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(761); simpleStmt();
				setState(762); match(Semi);
				}
				break;
			}
			setState(767);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(766); expression(0);
				}
				break;
			}
			setState(769); match(LeftBrace);
			setState(773);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(770); exprCaseClause();
					}
					} 
				}
				setState(775);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			}
			setState(776); match(RightBrace);
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
		enterRule(_localctx, 156, RULE_exprCaseClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(778); exprSwitchCase();
			setState(779); match(Colon);
			setState(791);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(780); statement();
				setState(785);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(781); match(Semi);
						setState(782); statement();
						}
						} 
					}
					setState(787);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
				}
				setState(789);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(788); match(Semi);
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
		enterRule(_localctx, 158, RULE_exprSwitchCase);
		try {
			setState(796);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(793); match(Case);
				setState(794); expressionList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(795); match(Default);
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
		enterRule(_localctx, 160, RULE_typeSwitchStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(798); match(Switch);
			setState(802);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(799); simpleStmt();
				setState(800); match(Semi);
				}
				break;
			}
			setState(804); typeSwitchGuard();
			setState(805); match(LeftBrace);
			setState(809);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(806); typeCaseClause();
					}
					} 
				}
				setState(811);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
			}
			setState(812); match(RightBrace);
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
		enterRule(_localctx, 162, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(816);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(814); match(IDENTIFIER);
				setState(815); _localctx.defeq = match(ColonEqual);
				}
				break;
			}
			setState(818); expression(0);
			setState(819); _localctx.dot = match(Dot);
			setState(820); match(LeftParen);
			setState(821); match(Type);
			setState(822); match(RightParen);
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
		enterRule(_localctx, 164, RULE_typeCaseClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(824); typeSwitchCase();
			setState(825); match(Colon);
			setState(837);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				{
				setState(826); statement();
				setState(831);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(827); match(Semi);
						setState(828); statement();
						}
						} 
					}
					setState(833);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				}
				setState(835);
				switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(834); match(Semi);
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
		enterRule(_localctx, 166, RULE_typeSwitchCase);
		try {
			setState(842);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(839); match(Case);
				setState(840); typeList();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(841); match(Default);
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
		enterRule(_localctx, 168, RULE_typeList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(844); type();
			setState(849);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(845); match(Comma);
					setState(846); type();
					}
					} 
				}
				setState(851);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
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
		enterRule(_localctx, 170, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(852); match(For);
			setState(856);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				{
				setState(853); condition();
				}
				break;

			case 2:
				{
				setState(854); forClause();
				}
				break;

			case 3:
				{
				setState(855); rangeClause();
				}
				break;
			}
			setState(858); block();
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
		enterRule(_localctx, 172, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(860); expression(0);
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
		enterRule(_localctx, 174, RULE_forClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(863);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(862); initStmt();
				}
				break;
			}
			setState(865); match(Semi);
			setState(867);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(866); condition();
				}
				break;
			}
			setState(869); match(Semi);
			setState(871);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(870); postStmt();
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
		enterRule(_localctx, 176, RULE_initStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(873); simpleStmt();
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
		enterRule(_localctx, 178, RULE_postStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(875); simpleStmt();
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
		enterRule(_localctx, 180, RULE_rangeClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(877); _localctx.e1 = expression(0);
			setState(880);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(878); match(Comma);
				setState(879); _localctx.e2 = expression(0);
				}
				break;
			}
			setState(884);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				{
				setState(882); _localctx.eq = match(Equal);
				}
				break;

			case 2:
				{
				setState(883); _localctx.defeq = match(ColonEqual);
				}
				break;
			}
			setState(886); match(Range);
			setState(887); _localctx.e = expression(0);
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
		enterRule(_localctx, 182, RULE_goStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(889); match(Go);
			setState(890); expression(0);
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
		enterRule(_localctx, 184, RULE_selectStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(892); match(Select);
			setState(893); match(LeftBrace);
			setState(897);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(894); commClause();
					}
					} 
				}
				setState(899);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			}
			setState(900); match(RightBrace);
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
		enterRule(_localctx, 186, RULE_commClause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(902); commCase();
			setState(903); match(Colon);
			setState(915);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				{
				setState(904); statement();
				setState(909);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(905); match(Semi);
						setState(906); statement();
						}
						} 
					}
					setState(911);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				}
				setState(913);
				switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(912); match(Semi);
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
		enterRule(_localctx, 188, RULE_commCase);
		try {
			setState(923);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(917); match(Case);
				setState(920);
				switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(918); sendStmt();
					}
					break;

				case 2:
					{
					setState(919); recvStmt();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(922); match(Default);
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
		enterRule(_localctx, 190, RULE_recvStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(934);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				{
				setState(925); _localctx.e1 = expression(0);
				setState(928);
				switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(926); match(Comma);
					setState(927); _localctx.e2 = expression(0);
					}
					break;
				}
				setState(932);
				switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(930); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(931); _localctx.defeq = match(ColonEqual);
					}
					break;
				}
				}
				break;
			}
			setState(936); recvExpr();
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
		enterRule(_localctx, 192, RULE_recvExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(938); expression(0);
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
		enterRule(_localctx, 194, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(940); match(Return);
			setState(942);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				{
				setState(941); expressionList();
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
		enterRule(_localctx, 196, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(944); match(Break);
			setState(946);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(945); label();
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
		enterRule(_localctx, 198, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(948); match(Continue);
			setState(950);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(949); label();
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
		enterRule(_localctx, 200, RULE_gotoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(952); match(Goto);
			setState(953); label();
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
		enterRule(_localctx, 202, RULE_fallthroughStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(955); match(Fallthrough);
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
		enterRule(_localctx, 204, RULE_deferStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(957); match(Defer);
			setState(958); expression(0);
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
		enterRule(_localctx, 206, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960); match(IDENTIFIER);
			setState(961); match(LeftParen);
			setState(966);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(962); builtinArgs();
				setState(964);
				switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(963); match(Comma);
					}
					break;
				}
				}
				break;
			}
			setState(968); match(RightParen);
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
		enterRule(_localctx, 208, RULE_builtinArgs);
		try {
			setState(976);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(970); type();
				setState(973);
				switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					{
					setState(971); match(Comma);
					setState(972); expressionList();
					}
					break;
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(975); expressionList();
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
		enterRule(_localctx, 210, RULE_sourceFileBody);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(981);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				{
				setState(978); packageClause();
				setState(979); match(Semi);
				}
				break;
			}
			setState(988);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(983); importDecl();
					setState(984); match(Semi);
					}
					} 
				}
				setState(990);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,111,_ctx);
			}
			setState(996);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(991); topLevelDecl();
					setState(992); match(Semi);
					}
					} 
				}
				setState(998);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
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
		enterRule(_localctx, 212, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999); sourceFileBody();
			setState(1000); match(EOF);
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
		enterRule(_localctx, 214, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002); match(Package);
			setState(1003); _localctx.packageName = packageName();
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
		enterRule(_localctx, 216, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1006); match(IDENTIFIER);
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
		enterRule(_localctx, 218, RULE_importDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1008); match(Import);
			setState(1025);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				{
				setState(1009); importSpec();
				}
				break;

			case 2:
				{
				setState(1010); match(LeftParen);
				setState(1022);
				switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
				case 1:
					{
					setState(1011); importSpec();
					setState(1016);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(1012); match(Semi);
							setState(1013); importSpec();
							}
							} 
						}
						setState(1018);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
					}
					setState(1020);
					switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
					case 1:
						{
						setState(1019); match(Semi);
						}
						break;
					}
					}
					break;
				}
				setState(1024); match(RightParen);
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
		enterRule(_localctx, 220, RULE_importSpec);
		try {
			setState(1036);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1027); _localctx.dot = match(Dot);
				setState(1028); importPath();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1029); _localctx.packageName = packageName();
				setState(1030); importPath();
				addPackageName((_localctx.packageName!=null?(_localctx.packageName.start):null));
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1033); _localctx.importPath = importPath();
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
		enterRule(_localctx, 222, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1038); match(StringLiteral);
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
		case 46: return qualifiedIdentifier_sempred((QualifiedIdentifierContext)_localctx, predIndex);

		case 59: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	public boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 6 >= _localctx._p;

		case 2: return 5 >= _localctx._p;

		case 3: return 4 >= _localctx._p;

		case 4: return 3 >= _localctx._p;

		case 5: return 2 >= _localctx._p;

		case 6: return 12 >= _localctx._p;

		case 7: return 11 >= _localctx._p;

		case 8: return 10 >= _localctx._p;

		case 9: return 9 >= _localctx._p;

		case 10: return 8 >= _localctx._p;
		}
		return true;
	}
	public boolean qualifiedIdentifier_sempred(QualifiedIdentifierContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return isPackageName(_input.LT(1));
		}
		return true;
	}

	public static final String _serializedATN =
		"\1S\u0411\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		"\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\2o\7o\1\0\1\0\1\0"+
		"\1\0\1\0\1\0\3\0\u00e7\b\0\1\1\1\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\3\2"+
		"\u00f3\b\2\1\3\1\3\1\3\1\3\1\3\1\4\1\4\1\5\1\5\1\6\1\6\1\6\1\6\1\7\1\7"+
		"\1\7\1\7\1\7\5\7\u0107\b\7\n\7\f\7\u010a\t\7\1\7\3\7\u010d\b\7\1\7\1\7"+
		"\1\b\1\b\1\b\1\b\3\b\u0115\b\b\1\b\3\b\u0118\b\b\1\t\3\t\u011b\b\t\1\t"+
		"\1\t\1\n\1\n\1\13\1\13\1\13\1\f\1\f\1\r\1\r\1\r\1\16\1\16\3\16\u012b\b"+
		"\16\1\17\1\17\3\17\u012f\b\17\1\20\1\20\1\20\3\20\u0134\b\20\3\20\u0136"+
		"\b\20\1\20\1\20\1\21\1\21\1\21\5\21\u013d\b\21\n\21\f\21\u0140\t\21\1"+
		"\22\3\22\u0143\b\22\1\22\3\22\u0146\b\22\1\22\1\22\1\23\1\23\1\23\1\23"+
		"\1\23\5\23\u014f\b\23\n\23\f\23\u0152\t\23\1\23\3\23\u0155\b\23\3\23\u0157"+
		"\b\23\1\23\1\23\1\24\1\24\1\24\1\24\3\24\u015f\b\24\1\25\1\25\1\26\1\26"+
		"\1\27\1\27\1\27\1\27\1\27\1\27\1\30\1\30\1\31\1\31\3\31\u016f\b\31\1\31"+
		"\1\31\3\31\u0173\b\31\1\31\1\31\1\32\1\32\1\32\1\32\5\32\u017b\b\32\n"+
		"\32\f\32\u017e\t\32\1\32\3\32\u0181\b\32\3\32\u0183\b\32\1\32\1\32\1\33"+
		"\1\33\1\33\3\33\u018a\b\33\1\34\1\34\1\34\3\34\u018f\b\34\1\35\1\35\1"+
		"\35\1\35\1\35\1\35\5\35\u0197\b\35\n\35\f\35\u019a\t\35\1\35\3\35\u019d"+
		"\b\35\3\35\u019f\b\35\1\35\3\35\u01a2\b\35\1\36\1\36\3\36\u01a6\b\36\1"+
		"\36\1\36\3\36\u01aa\b\36\1\37\1\37\1\37\5\37\u01af\b\37\n\37\f\37\u01b2"+
		"\t\37\1 \1 \1 \5 \u01b7\b \n \f \u01ba\t \1!\1!\1!\1!\1!\1!\5!\u01c2\b"+
		"!\n!\f!\u01c5\t!\1!\3!\u01c8\b!\3!\u01ca\b!\1!\3!\u01cd\b!\1\"\1\"\1\""+
		"\1#\1#\1#\1#\1#\1#\5#\u01d8\b#\n#\f#\u01db\t#\1#\3#\u01de\b#\3#\u01e0"+
		"\b#\1#\3#\u01e3\b#\1$\1$\1$\1$\3$\u01e9\b$\1$\1$\3$\u01ed\b$\1%\1%\1%"+
		"\1%\1&\1&\1&\1&\3&\u01f7\b&\1\'\1\'\1(\1(\1(\1(\1(\3(\u0200\b(\1)\1)\3"+
		")\u0204\b)\1)\3)\u0207\b)\1)\1)\1)\1*\1*\1+\1+\1+\1+\1+\1+\1+\3+\u0215"+
		"\b+\1,\1,\1,\3,\u021a\b,\1-\1-\1.\1.\1.\1.\3.\u0222\b.\1.\1.\1/\1/\1/"+
		"\1/\1\60\1\60\1\60\1\60\1\60\1\60\3\60\u0230\b\60\1\61\1\61\1\61\1\62"+
		"\1\62\1\62\1\62\1\62\1\62\1\62\1\62\1\62\3\62\u023e\b\62\1\63\1\63\1\63"+
		"\3\63\u0243\b\63\3\63\u0245\b\63\1\63\1\63\1\64\1\64\1\64\5\64\u024c\b"+
		"\64\n\64\f\64\u024f\t\64\1\65\1\65\1\65\3\65\u0254\b\65\1\65\1\65\1\66"+
		"\1\66\3\66\u025a\b\66\1\67\1\67\18\18\19\19\39\u0262\b9\1:\1:\1:\1;\1"+
		";\1;\1;\1;\1;\3;\u026d\b;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1"+
		";\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\3;\u0289\b;\1;\1;\3;\u028d\b;\1;\1"+
		";\1;\1;\1;\1;\1;\1;\1;\1;\1;\3;\u029a\b;\3;\u029c\b;\1;\5;\u029f\b;\n"+
		";\f;\u02a2\t;\1<\1<\3<\u02a6\b<\1=\1=\1=\1=\1=\1>\1>\1>\1>\1>\1>\1>\1"+
		">\1>\1>\1>\1>\1>\1>\1>\3>\u02bc\b>\1?\1?\1?\1?\1?\1?\3?\u02c4\b?\1@\1"+
		"@\1A\1A\1A\1A\1B\1B\1C\1C\1D\1D\1D\1D\1E\1E\1F\1F\1F\1G\1G\1G\1G\1H\1"+
		"H\1H\3H\u02e0\bH\1I\1I\1J\1J\1K\1K\1K\1K\3K\u02ea\bK\1K\1K\1K\1K\1K\3"+
		"K\u02f1\bK\3K\u02f3\bK\1L\1L\3L\u02f7\bL\1M\1M\1M\1M\3M\u02fd\bM\1M\3"+
		"M\u0300\bM\1M\1M\5M\u0304\bM\nM\fM\u0307\tM\1M\1M\1N\1N\1N\1N\1N\5N\u0310"+
		"\bN\nN\fN\u0313\tN\1N\3N\u0316\bN\3N\u0318\bN\1O\1O\1O\3O\u031d\bO\1P"+
		"\1P\1P\1P\3P\u0323\bP\1P\1P\1P\5P\u0328\bP\nP\fP\u032b\tP\1P\1P\1Q\1Q"+
		"\3Q\u0331\bQ\1Q\1Q\1Q\1Q\1Q\1Q\1R\1R\1R\1R\1R\5R\u033e\bR\nR\fR\u0341"+
		"\tR\1R\3R\u0344\bR\3R\u0346\bR\1S\1S\1S\3S\u034b\bS\1T\1T\1T\5T\u0350"+
		"\bT\nT\fT\u0353\tT\1U\1U\1U\1U\3U\u0359\bU\1U\1U\1V\1V\1W\3W\u0360\bW"+
		"\1W\1W\3W\u0364\bW\1W\1W\3W\u0368\bW\1X\1X\1Y\1Y\1Z\1Z\1Z\3Z\u0371\bZ"+
		"\1Z\1Z\3Z\u0375\bZ\1Z\1Z\1Z\1[\1[\1[\1\\\1\\\1\\\5\\\u0380\b\\\n\\\f\\"+
		"\u0383\t\\\1\\\1\\\1]\1]\1]\1]\1]\5]\u038c\b]\n]\f]\u038f\t]\1]\3]\u0392"+
		"\b]\3]\u0394\b]\1^\1^\1^\3^\u0399\b^\1^\3^\u039c\b^\1_\1_\1_\3_\u03a1"+
		"\b_\1_\1_\3_\u03a5\b_\3_\u03a7\b_\1_\1_\1`\1`\1a\1a\3a\u03af\ba\1b\1b"+
		"\3b\u03b3\bb\1c\1c\3c\u03b7\bc\1d\1d\1d\1e\1e\1f\1f\1f\1g\1g\1g\1g\3g"+
		"\u03c5\bg\3g\u03c7\bg\1g\1g\1h\1h\1h\3h\u03ce\bh\1h\3h\u03d1\bh\1i\1i"+
		"\1i\3i\u03d6\bi\1i\1i\1i\5i\u03db\bi\ni\fi\u03de\ti\1i\1i\1i\5i\u03e3"+
		"\bi\ni\fi\u03e6\ti\1j\1j\1j\1k\1k\1k\1k\1l\1l\1m\1m\1m\1m\1m\1m\5m\u03f7"+
		"\bm\nm\fm\u03fa\tm\1m\3m\u03fd\bm\3m\u03ff\bm\1m\3m\u0402\bm\1n\1n\1n"+
		"\1n\1n\1n\1n\1n\1n\3n\u040d\bn\1o\1o\1op\0\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|"+
		"~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096"+
		"\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae"+
		"\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6"+
		"\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de"+
		"\0\b\2\1\1OR\5\33\35  \"\"\63\63::\2\35 #%\2\33\34!\"\2\668;=\1\64\65"+
		"\2&\',-\2(+-\60\u0445\0\u00e6\1\0\0\0\2\u00e8\1\0\0\0\4\u00f2\1\0\0\0"+
		"\6\u00f4\1\0\0\0\b\u00f9\1\0\0\0\n\u00fb\1\0\0\0\f\u00fd\1\0\0\0\16\u0101"+
		"\1\0\0\0\20\u0114\1\0\0\0\22\u011a\1\0\0\0\24\u011e\1\0\0\0\26\u0120\1"+
		"\0\0\0\30\u0123\1\0\0\0\32\u0125\1\0\0\0\34\u0128\1\0\0\0\36\u012e\1\0"+
		"\0\0 \u0130\1\0\0\0\"\u0139\1\0\0\0$\u0142\1\0\0\0&\u0149\1\0\0\0(\u015e"+
		"\1\0\0\0*\u0160\1\0\0\0,\u0162\1\0\0\0.\u0164\1\0\0\0\60\u016a\1\0\0\0"+
		"\62\u0172\1\0\0\0\64\u0176\1\0\0\0\66\u0189\1\0\0\08\u018e\1\0\0\0:\u0190"+
		"\1\0\0\0<\u01a3\1\0\0\0>\u01ab\1\0\0\0@\u01b3\1\0\0\0B\u01bb\1\0\0\0D"+
		"\u01ce\1\0\0\0F\u01d1\1\0\0\0H\u01e4\1\0\0\0J\u01ee\1\0\0\0L\u01f2\1\0"+
		"\0\0N\u01f8\1\0\0\0P\u01fa\1\0\0\0R\u0201\1\0\0\0T\u020b\1\0\0\0V\u0214"+
		"\1\0\0\0X\u0219\1\0\0\0Z\u021b\1\0\0\0\\\u0221\1\0\0\0^\u0225\1\0\0\0"+
		"`\u022f\1\0\0\0b\u0231\1\0\0\0d\u023d\1\0\0\0f\u023f\1\0\0\0h\u0248\1"+
		"\0\0\0j\u0253\1\0\0\0l\u0259\1\0\0\0n\u025b\1\0\0\0p\u025d\1\0\0\0r\u0261"+
		"\1\0\0\0t\u0263\1\0\0\0v\u026c\1\0\0\0x\u02a3\1\0\0\0z\u02a7\1\0\0\0|"+
		"\u02bb\1\0\0\0~\u02c3\1\0\0\0\u0080\u02c5\1\0\0\0\u0082\u02c7\1\0\0\0"+
		"\u0084\u02cb\1\0\0\0\u0086\u02cd\1\0\0\0\u0088\u02cf\1\0\0\0\u008a\u02d3"+
		"\1\0\0\0\u008c\u02d5\1\0\0\0\u008e\u02d8\1\0\0\0\u0090\u02df\1\0\0\0\u0092"+
		"\u02e1\1\0\0\0\u0094\u02e3\1\0\0\0\u0096\u02e5\1\0\0\0\u0098\u02f6\1\0"+
		"\0\0\u009a\u02f8\1\0\0\0\u009c\u030a\1\0\0\0\u009e\u031c\1\0\0\0\u00a0"+
		"\u031e\1\0\0\0\u00a2\u0330\1\0\0\0\u00a4\u0338\1\0\0\0\u00a6\u034a\1\0"+
		"\0\0\u00a8\u034c\1\0\0\0\u00aa\u0354\1\0\0\0\u00ac\u035c\1\0\0\0\u00ae"+
		"\u035f\1\0\0\0\u00b0\u0369\1\0\0\0\u00b2\u036b\1\0\0\0\u00b4\u036d\1\0"+
		"\0\0\u00b6\u0379\1\0\0\0\u00b8\u037c\1\0\0\0\u00ba\u0386\1\0\0\0\u00bc"+
		"\u039b\1\0\0\0\u00be\u03a6\1\0\0\0\u00c0\u03aa\1\0\0\0\u00c2\u03ac\1\0"+
		"\0\0\u00c4\u03b0\1\0\0\0\u00c6\u03b4\1\0\0\0\u00c8\u03b8\1\0\0\0\u00ca"+
		"\u03bb\1\0\0\0\u00cc\u03bd\1\0\0\0\u00ce\u03c0\1\0\0\0\u00d0\u03d0\1\0"+
		"\0\0\u00d2\u03d5\1\0\0\0\u00d4\u03e7\1\0\0\0\u00d6\u03ea\1\0\0\0\u00d8"+
		"\u03ee\1\0\0\0\u00da\u03f0\1\0\0\0\u00dc\u040c\1\0\0\0\u00de\u040e\1\0"+
		"\0\0\u00e0\u00e7\3\2\1\0\u00e1\u00e7\3\4\2\0\u00e2\u00e3\5@\0\0\u00e3"+
		"\u00e4\3\0\0\0\u00e4\u00e5\5A\0\0\u00e5\u00e7\1\0\0\0\u00e6\u00e0\1\0"+
		"\0\0\u00e6\u00e1\1\0\0\0\u00e6\u00e2\1\0\0\0\u00e7\1\1\0\0\0\u00e8\u00e9"+
		"\3\\.\0\u00e9\3\1\0\0\0\u00ea\u00f3\3\6\3\0\u00eb\u00f3\3\16\7\0\u00ec"+
		"\u00f3\3\26\13\0\u00ed\u00f3\3\32\r\0\u00ee\u00f3\3&\23\0\u00ef\u00f3"+
		"\3\f\6\0\u00f0\u00f3\3.\27\0\u00f1\u00f3\3\62\31\0\u00f2\u00ea\1\0\0\0"+
		"\u00f2\u00eb\1\0\0\0\u00f2\u00ec\1\0\0\0\u00f2\u00ed\1\0\0\0\u00f2\u00ee"+
		"\1\0\0\0\u00f2\u00ef\1\0\0\0\u00f2\u00f0\1\0\0\0\u00f2\u00f1\1\0\0\0\u00f3"+
		"\5\1\0\0\0\u00f4\u00f5\5B\0\0\u00f5\u00f6\3\b\4\0\u00f6\u00f7\5C\0\0\u00f7"+
		"\u00f8\3\n\5\0\u00f8\7\1\0\0\0\u00f9\u00fa\3v;\0\u00fa\t\1\0\0\0\u00fb"+
		"\u00fc\3\0\0\0\u00fc\13\1\0\0\0\u00fd\u00fe\5B\0\0\u00fe\u00ff\5C\0\0"+
		"\u00ff\u0100\3\n\5\0\u0100\r\1\0\0\0\u0101\u0102\5\27\0\0\u0102\u0108"+
		"\5D\0\0\u0103\u0104\3\20\b\0\u0104\u0105\5H\0\0\u0105\u0107\1\0\0\0\u0106"+
		"\u0103\1\0\0\0\u0107\u010a\1\0\0\0\u0108\u0106\1\0\0\0\u0108\u0109\1\0"+
		"\0\0\u0109\u010c\1\0\0\0\u010a\u0108\1\0\0\0\u010b\u010d\3\20\b\0\u010c"+
		"\u010b\1\0\0\0\u010c\u010d\1\0\0\0\u010d\u010e\1\0\0\0\u010e\u010f\5E"+
		"\0\0\u010f\17\1\0\0\0\u0110\u0111\3>\37\0\u0111\u0112\3\0\0\0\u0112\u0115"+
		"\1\0\0\0\u0113\u0115\3\22\t\0\u0114\u0110\1\0\0\0\u0114\u0113\1\0\0\0"+
		"\u0115\u0117\1\0\0\0\u0116\u0118\3\24\n\0\u0117\u0116\1\0\0\0\u0117\u0118"+
		"\1\0\0\0\u0118\21\1\0\0\0\u0119\u011b\5\35\0\0\u011a\u0119\1\0\0\0\u011a"+
		"\u011b\1\0\0\0\u011b\u011c\1\0\0\0\u011c\u011d\3\2\1\0\u011d\23\1\0\0"+
		"\0\u011e\u011f\5R\0\0\u011f\25\1\0\0\0\u0120\u0121\5\35\0\0\u0121\u0122"+
		"\3\30\f\0\u0122\27\1\0\0\0\u0123\u0124\3\0\0\0\u0124\31\1\0\0\0\u0125"+
		"\u0126\5\f\0\0\u0126\u0127\3\34\16\0\u0127\33\1\0\0\0\u0128\u012a\3 \20"+
		"\0\u0129\u012b\3\36\17\0\u012a\u0129\1\0\0\0\u012a\u012b\1\0\0\0\u012b"+
		"\35\1\0\0\0\u012c\u012f\3 \20\0\u012d\u012f\3\0\0\0\u012e\u012c\1\0\0"+
		"\0\u012e\u012d\1\0\0\0\u012f\37\1\0\0\0\u0130\u0135\5@\0\0\u0131\u0133"+
		"\3\"\21\0\u0132\u0134\5F\0\0\u0133\u0132\1\0\0\0\u0133\u0134\1\0\0\0\u0134"+
		"\u0136\1\0\0\0\u0135\u0131\1\0\0\0\u0135\u0136\1\0\0\0\u0136\u0137\1\0"+
		"\0\0\u0137\u0138\5A\0\0\u0138!\1\0\0\0\u0139\u013e\3$\22\0\u013a\u013b"+
		"\5F\0\0\u013b\u013d\3$\22\0\u013c\u013a\1\0\0\0\u013d\u0140\1\0\0\0\u013e"+
		"\u013c\1\0\0\0\u013e\u013f\1\0\0\0\u013f#\1\0\0\0\u0140\u013e\1\0\0\0"+
		"\u0141\u0143\3>\37\0\u0142\u0141\1\0\0\0\u0142\u0143\1\0\0\0\u0143\u0145"+
		"\1\0\0\0\u0144\u0146\5?\0\0\u0145\u0144\1\0\0\0\u0145\u0146\1\0\0\0\u0146"+
		"\u0147\1\0\0\0\u0147\u0148\3\0\0\0\u0148%\1\0\0\0\u0149\u014a\5\21\0\0"+
		"\u014a\u0156\5D\0\0\u014b\u0150\3(\24\0\u014c\u014d\5H\0\0\u014d\u014f"+
		"\3(\24\0\u014e\u014c\1\0\0\0\u014f\u0152\1\0\0\0\u0150\u014e\1\0\0\0\u0150"+
		"\u0151\1\0\0\0\u0151\u0154\1\0\0\0\u0152\u0150\1\0\0\0\u0153\u0155\5H"+
		"\0\0\u0154\u0153\1\0\0\0\u0154\u0155\1\0\0\0\u0155\u0157\1\0\0\0\u0156"+
		"\u014b\1\0\0\0\u0156\u0157\1\0\0\0\u0157\u0158\1\0\0\0\u0158\u0159\5E"+
		"\0\0\u0159\'\1\0\0\0\u015a\u015b\3*\25\0\u015b\u015c\3\34\16\0\u015c\u015f"+
		"\1\0\0\0\u015d\u015f\3,\26\0\u015e\u015a\1\0\0\0\u015e\u015d\1\0\0\0\u015f"+
		")\1\0\0\0\u0160\u0161\5J\0\0\u0161+\1\0\0\0\u0162\u0163\3\2\1\0\u0163"+
		"-\1\0\0\0\u0164\u0165\5\22\0\0\u0165\u0166\5B\0\0\u0166\u0167\3\60\30"+
		"\0\u0167\u0168\5C\0\0\u0168\u0169\3\n\5\0\u0169/\1\0\0\0\u016a\u016b\3"+
		"\0\0\0\u016b\61\1\0\0\0\u016c\u016e\5\4\0\0\u016d\u016f\5\63\0\0\u016e"+
		"\u016d\1\0\0\0\u016e\u016f\1\0\0\0\u016f\u0173\1\0\0\0\u0170\u0171\5\63"+
		"\0\0\u0171\u0173\5\4\0\0\u0172\u016c\1\0\0\0\u0172\u0170\1\0\0\0\u0173"+
		"\u0174\1\0\0\0\u0174\u0175\3\n\5\0\u0175\63\1\0\0\0\u0176\u0182\5D\0\0"+
		"\u0177\u017c\3|>\0\u0178\u0179\5H\0\0\u0179\u017b\3|>\0\u017a\u0178\1"+
		"\0\0\0\u017b\u017e\1\0\0\0\u017c\u017a\1\0\0\0\u017c\u017d\1\0\0\0\u017d"+
		"\u0180\1\0\0\0\u017e\u017c\1\0\0\0\u017f\u0181\5H\0\0\u0180\u017f\1\0"+
		"\0\0\u0180\u0181\1\0\0\0\u0181\u0183\1\0\0\0\u0182\u0177\1\0\0\0\u0182"+
		"\u0183\1\0\0\0\u0183\u0184\1\0\0\0\u0184\u0185\5E\0\0\u0185\65\1\0\0\0"+
		"\u0186\u018a\3:\35\0\u0187\u018a\3B!\0\u0188\u018a\3F#\0\u0189\u0186\1"+
		"\0\0\0\u0189\u0187\1\0\0\0\u0189\u0188\1\0\0\0\u018a\67\1\0\0\0\u018b"+
		"\u018f\3\66\33\0\u018c\u018f\3L&\0\u018d\u018f\3P(\0\u018e\u018b\1\0\0"+
		"\0\u018e\u018c\1\0\0\0\u018e\u018d\1\0\0\0\u018f9\1\0\0\0\u0190\u01a1"+
		"\5\5\0\0\u0191\u01a2\3<\36\0\u0192\u019e\5@\0\0\u0193\u0198\3<\36\0\u0194"+
		"\u0195\5H\0\0\u0195\u0197\3<\36\0\u0196\u0194\1\0\0\0\u0197\u019a\1\0"+
		"\0\0\u0198\u0196\1\0\0\0\u0198\u0199\1\0\0\0\u0199\u019c\1\0\0\0\u019a"+
		"\u0198\1\0\0\0\u019b\u019d\5H\0\0\u019c\u019b\1\0\0\0\u019c\u019d\1\0"+
		"\0\0\u019d\u019f\1\0\0\0\u019e\u0193\1\0\0\0\u019e\u019f\1\0\0\0\u019f"+
		"\u01a0\1\0\0\0\u01a0\u01a2\5A\0\0\u01a1\u0191\1\0\0\0\u01a1\u0192\1\0"+
		"\0\0\u01a2;\1\0\0\0\u01a3\u01a9\3>\37\0\u01a4\u01a6\3\0\0\0\u01a5\u01a4"+
		"\1\0\0\0\u01a5\u01a6\1\0\0\0\u01a6\u01a7\1\0\0\0\u01a7\u01a8\59\0\0\u01a8"+
		"\u01aa\3@ \0\u01a9\u01a5\1\0\0\0\u01a9\u01aa\1\0\0\0\u01aa=\1\0\0\0\u01ab"+
		"\u01b0\5J\0\0\u01ac\u01ad\5F\0\0\u01ad\u01af\5J\0\0\u01ae\u01ac\1\0\0"+
		"\0\u01af\u01b2\1\0\0\0\u01b0\u01ae\1\0\0\0\u01b0\u01b1\1\0\0\0\u01b1?"+
		"\1\0\0\0\u01b2\u01b0\1\0\0\0\u01b3\u01b8\3v;\0\u01b4\u01b5\5F\0\0\u01b5"+
		"\u01b7\3v;\0\u01b6\u01b4\1\0\0\0\u01b7\u01ba\1\0\0\0\u01b8\u01b6\1\0\0"+
		"\0\u01b8\u01b9\1\0\0\0\u01b9A\1\0\0\0\u01ba\u01b8\1\0\0\0\u01bb\u01cc"+
		"\5\31\0\0\u01bc\u01cd\3D\"\0\u01bd\u01c9\5@\0\0\u01be\u01c3\3D\"\0\u01bf"+
		"\u01c0\5H\0\0\u01c0\u01c2\3D\"\0\u01c1\u01bf\1\0\0\0\u01c2\u01c5\1\0\0"+
		"\0\u01c3\u01c1\1\0\0\0\u01c3\u01c4\1\0\0\0\u01c4\u01c7\1\0\0\0\u01c5\u01c3"+
		"\1\0\0\0\u01c6\u01c8\5H\0\0\u01c7\u01c6\1\0\0\0\u01c7\u01c8\1\0\0\0\u01c8"+
		"\u01ca\1\0\0\0\u01c9\u01be\1\0\0\0\u01c9\u01ca\1\0\0\0\u01ca\u01cb\1\0"+
		"\0\0\u01cb\u01cd\5A\0\0\u01cc\u01bc\1\0\0\0\u01cc\u01bd\1\0\0\0\u01cd"+
		"C\1\0\0\0\u01ce\u01cf\5J\0\0\u01cf\u01d0\3\0\0\0\u01d0E\1\0\0\0\u01d1"+
		"\u01e2\5\32\0\0\u01d2\u01e3\3H$\0\u01d3\u01df\5@\0\0\u01d4\u01d9\3H$\0"+
		"\u01d5\u01d6\5H\0\0\u01d6\u01d8\3H$\0\u01d7\u01d5\1\0\0\0\u01d8\u01db"+
		"\1\0\0\0\u01d9\u01d7\1\0\0\0\u01d9\u01da\1\0\0\0\u01da\u01dd\1\0\0\0\u01db"+
		"\u01d9\1\0\0\0\u01dc\u01de\5H\0\0\u01dd\u01dc\1\0\0\0\u01dd\u01de\1\0"+
		"\0\0\u01de\u01e0\1\0\0\0\u01df\u01d4\1\0\0\0\u01df\u01e0\1\0\0\0\u01e0"+
		"\u01e1\1\0\0\0\u01e1\u01e3\5A\0\0\u01e2\u01d2\1\0\0\0\u01e2\u01d3\1\0"+
		"\0\0\u01e3G\1\0\0\0\u01e4\u01ec\3>\37\0\u01e5\u01e8\3\0\0\0\u01e6\u01e7"+
		"\59\0\0\u01e7\u01e9\3@ \0\u01e8\u01e6\1\0\0\0\u01e8\u01e9\1\0\0\0\u01e9"+
		"\u01ed\1\0\0\0\u01ea\u01eb\59\0\0\u01eb\u01ed\3@ \0\u01ec\u01e5\1\0\0"+
		"\0\u01ec\u01ea\1\0\0\0\u01edI\1\0\0\0\u01ee\u01ef\3>\37\0\u01ef\u01f0"+
		"\5>\0\0\u01f0\u01f1\3@ \0\u01f1K\1\0\0\0\u01f2\u01f3\5\f\0\0\u01f3\u01f4"+
		"\5J\0\0\u01f4\u01f6\3\34\16\0\u01f5\u01f7\3N\'\0\u01f6\u01f5\1\0\0\0\u01f6"+
		"\u01f7\1\0\0\0\u01f7M\1\0\0\0\u01f8\u01f9\3\64\32\0\u01f9O\1\0\0\0\u01fa"+
		"\u01fb\5\f\0\0\u01fb\u01fc\3R)\0\u01fc\u01fd\3*\25\0\u01fd\u01ff\3\34"+
		"\16\0\u01fe\u0200\3N\'\0\u01ff\u01fe\1\0\0\0\u01ff\u0200\1\0\0\0\u0200"+
		"Q\1\0\0\0\u0201\u0203\5@\0\0\u0202\u0204\5J\0\0\u0203\u0202\1\0\0\0\u0203"+
		"\u0204\1\0\0\0\u0204\u0206\1\0\0\0\u0205\u0207\5\35\0\0\u0206\u0205\1"+
		"\0\0\0\u0206\u0207\1\0\0\0\u0207\u0208\1\0\0\0\u0208\u0209\3T*\0\u0209"+
		"\u020a\5A\0\0\u020aS\1\0\0\0\u020b\u020c\5J\0\0\u020cU\1\0\0\0\u020d\u0215"+
		"\3X,\0\u020e\u0215\3\\.\0\u020f\u0215\3^/\0\u0210\u0211\5@\0\0\u0211\u0212"+
		"\3v;\0\u0212\u0213\5A\0\0\u0213\u0215\1\0\0\0\u0214\u020d\1\0\0\0\u0214"+
		"\u020e\1\0\0\0\u0214\u020f\1\0\0\0\u0214\u0210\1\0\0\0\u0215W\1\0\0\0"+
		"\u0216\u021a\3Z-\0\u0217\u021a\3b\61\0\u0218\u021a\3t:\0\u0219\u0216\1"+
		"\0\0\0\u0219\u0217\1\0\0\0\u0219\u0218\1\0\0\0\u021aY\1\0\0\0\u021b\u021c"+
		"\7\0\0\0\u021c[\1\0\0\0\u021d\u021e\4.\0\0\u021e\u021f\3\u00d8l\0\u021f"+
		"\u0220\5G\0\0\u0220\u0222\1\0\0\0\u0221\u021d\1\0\0\0\u0221\u0222\1\0"+
		"\0\0\u0222\u0223\1\0\0\0\u0223\u0224\5J\0\0\u0224]\1\0\0\0\u0225\u0226"+
		"\3`\60\0\u0226\u0227\5G\0\0\u0227\u0228\3*\25\0\u0228_\1\0\0\0\u0229\u0230"+
		"\3\2\1\0\u022a\u022b\5@\0\0\u022b\u022c\5\35\0\0\u022c\u022d\3\2\1\0\u022d"+
		"\u022e\5A\0\0\u022e\u0230\1\0\0\0\u022f\u0229\1\0\0\0\u022f\u022a\1\0"+
		"\0\0\u0230a\1\0\0\0\u0231\u0232\3d\62\0\u0232\u0233\3f\63\0\u0233c\1\0"+
		"\0\0\u0234\u023e\3\16\7\0\u0235\u023e\3\6\3\0\u0236\u0237\5B\0\0\u0237"+
		"\u0238\5?\0\0\u0238\u0239\5C\0\0\u0239\u023e\3\n\5\0\u023a\u023e\3\f\6"+
		"\0\u023b\u023e\3.\27\0\u023c\u023e\3\2\1\0\u023d\u0234\1\0\0\0\u023d\u0235"+
		"\1\0\0\0\u023d\u0236\1\0\0\0\u023d\u023a\1\0\0\0\u023d\u023b\1\0\0\0\u023d"+
		"\u023c\1\0\0\0\u023ee\1\0\0\0\u023f\u0244\5D\0\0\u0240\u0242\3h\64\0\u0241"+
		"\u0243\5F\0\0\u0242\u0241\1\0\0\0\u0242\u0243\1\0\0\0\u0243\u0245\1\0"+
		"\0\0\u0244\u0240\1\0\0\0\u0244\u0245\1\0\0\0\u0245\u0246\1\0\0\0\u0246"+
		"\u0247\5E\0\0\u0247g\1\0\0\0\u0248\u024d\3j\65\0\u0249\u024a\5F\0\0\u024a"+
		"\u024c\3j\65\0\u024b\u0249\1\0\0\0\u024c\u024f\1\0\0\0\u024d\u024b\1\0"+
		"\0\0\u024d\u024e\1\0\0\0\u024ei\1\0\0\0\u024f\u024d\1\0\0\0\u0250\u0251"+
		"\3l\66\0\u0251\u0252\5I\0\0\u0252\u0254\1\0\0\0\u0253\u0250\1\0\0\0\u0253"+
		"\u0254\1\0\0\0\u0254\u0255\1\0\0\0\u0255\u0256\3r9\0\u0256k\1\0\0\0\u0257"+
		"\u025a\3n\67\0\u0258\u025a\3p8\0\u0259\u0257\1\0\0\0\u0259\u0258\1\0\0"+
		"\0\u025am\1\0\0\0\u025b\u025c\5J\0\0\u025co\1\0\0\0\u025d\u025e\3v;\0"+
		"\u025eq\1\0\0\0\u025f\u0262\3v;\0\u0260\u0262\3f\63\0\u0261\u025f\1\0"+
		"\0\0\u0261\u0260\1\0\0\0\u0262s\1\0\0\0\u0263\u0264\3\32\r\0\u0264\u0265"+
		"\3N\'\0\u0265u\1\0\0\0\u0266\u0267\6;\uffff\0\u0267\u0268\7\1\0\0\u0268"+
		"\u026d\3v;\0\u0269\u026d\3z=\0\u026a\u026d\3\u00ceg\0\u026b\u026d\3V+"+
		"\0\u026c\u0266\1\0\0\0\u026c\u0269\1\0\0\0\u026c\u026a\1\0\0\0\u026c\u026b"+
		"\1\0\0\0\u026d\u02a0\1\0\0\0\u026e\u026f\4;\1\1\u026f\u0270\7\2\0\0\u0270"+
		"\u029f\3v;\0\u0271\u0272\4;\2\1\u0272\u0273\7\3\0\0\u0273\u029f\3v;\0"+
		"\u0274\u0275\4;\3\1\u0275\u0276\7\4\0\0\u0276\u029f\3v;\0\u0277\u0278"+
		"\4;\4\1\u0278\u0279\5\61\0\0\u0279\u029f\3v;\0\u027a\u027b\4;\5\1\u027b"+
		"\u027c\5\62\0\0\u027c\u029f\3v;\0\u027d\u027e\4;\6\1\u027e\u027f\5G\0"+
		"\0\u027f\u029f\5J\0\0\u0280\u0281\4;\7\1\u0281\u0282\5B\0\0\u0282\u0283"+
		"\3v;\0\u0283\u0284\5C\0\0\u0284\u029f\1\0\0\0\u0285\u0286\4;\b\1\u0286"+
		"\u0288\5B\0\0\u0287\u0289\3v;\0\u0288\u0287\1\0\0\0\u0288\u0289\1\0\0"+
		"\0\u0289\u028a\1\0\0\0\u028a\u028c\5I\0\0\u028b\u028d\3v;\0\u028c\u028b"+
		"\1\0\0\0\u028c\u028d\1\0\0\0\u028d\u028e\1\0\0\0\u028e\u029f\5C\0\0\u028f"+
		"\u0290\4;\t\1\u0290\u0291\5G\0\0\u0291\u0292\5@\0\0\u0292\u0293\3\0\0"+
		"\0\u0293\u0294\5A\0\0\u0294\u029f\1\0\0\0\u0295\u0296\4;\n\1\u0296\u029b"+
		"\5@\0\0\u0297\u0299\3x<\0\u0298\u029a\5F\0\0\u0299\u0298\1\0\0\0\u0299"+
		"\u029a\1\0\0\0\u029a\u029c\1\0\0\0\u029b\u0297\1\0\0\0\u029b\u029c\1\0"+
		"\0\0\u029c\u029d\1\0\0\0\u029d\u029f\5A\0\0\u029e\u026e\1\0\0\0\u029e"+
		"\u0271\1\0\0\0\u029e\u0274\1\0\0\0\u029e\u0277\1\0\0\0\u029e\u027a\1\0"+
		"\0\0\u029e\u027d\1\0\0\0\u029e\u0280\1\0\0\0\u029e\u0285\1\0\0\0\u029e"+
		"\u028f\1\0\0\0\u029e\u0295\1\0\0\0\u029f\u02a2\1\0\0\0\u02a0\u029e\1\0"+
		"\0\0\u02a0\u02a1\1\0\0\0\u02a1w\1\0\0\0\u02a2\u02a0\1\0\0\0\u02a3\u02a5"+
		"\3@ \0\u02a4\u02a6\5?\0\0\u02a5\u02a4\1\0\0\0\u02a5\u02a6\1\0\0\0\u02a6"+
		"y\1\0\0\0\u02a7\u02a8\3\0\0\0\u02a8\u02a9\5@\0\0\u02a9\u02aa\3v;\0\u02aa"+
		"\u02ab\5A\0\0\u02ab{\1\0\0\0\u02ac\u02bc\3\66\33\0\u02ad\u02bc\3\u0082"+
		"A\0\u02ae\u02bc\3~?\0\u02af\u02bc\3\u00b6[\0\u02b0\u02bc\3\u00c2a\0\u02b1"+
		"\u02bc\3\u00c4b\0\u02b2\u02bc\3\u00c6c\0\u02b3\u02bc\3\u00c8d\0\u02b4"+
		"\u02bc\3\u00cae\0\u02b5\u02bc\3\64\32\0\u02b6\u02bc\3\u0096K\0\u02b7\u02bc"+
		"\3\u0098L\0\u02b8\u02bc\3\u00b8\\\0\u02b9\u02bc\3\u00aaU\0\u02ba\u02bc"+
		"\3\u00ccf\0\u02bb\u02ac\1\0\0\0\u02bb\u02ad\1\0\0\0\u02bb\u02ae\1\0\0"+
		"\0\u02bb\u02af\1\0\0\0\u02bb\u02b0\1\0\0\0\u02bb\u02b1\1\0\0\0\u02bb\u02b2"+
		"\1\0\0\0\u02bb\u02b3\1\0\0\0\u02bb\u02b4\1\0\0\0\u02bb\u02b5\1\0\0\0\u02bb"+
		"\u02b6\1\0\0\0\u02bb\u02b7\1\0\0\0\u02bb\u02b8\1\0\0\0\u02bb\u02b9\1\0"+
		"\0\0\u02bb\u02ba\1\0\0\0\u02bc}\1\0\0\0\u02bd\u02c4\3\u0080@\0\u02be\u02c4"+
		"\3\u0086C\0\u02bf\u02c4\3\u0088D\0\u02c0\u02c4\3\u008cF\0\u02c1\u02c4"+
		"\3\u008eG\0\u02c2\u02c4\3J%\0\u02c3\u02bd\1\0\0\0\u02c3\u02be\1\0\0\0"+
		"\u02c3\u02bf\1\0\0\0\u02c3\u02c0\1\0\0\0\u02c3\u02c1\1\0\0\0\u02c3\u02c2"+
		"\1\0\0\0\u02c4\177\1\0\0\0\u02c5\u02c6\1\0\0\0\u02c6\u0081\1\0\0\0\u02c7"+
		"\u02c8\3\u0084B\0\u02c8\u02c9\5I\0\0\u02c9\u02ca\3|>\0\u02ca\u0083\1\0"+
		"\0\0\u02cb\u02cc\5J\0\0\u02cc\u0085\1\0\0\0\u02cd\u02ce\3v;\0\u02ce\u0087"+
		"\1\0\0\0\u02cf\u02d0\3\u008aE\0\u02d0\u02d1\5\63\0\0\u02d1\u02d2\3v;\0"+
		"\u02d2\u0089\1\0\0\0\u02d3\u02d4\3v;\0\u02d4\u008b\1\0\0\0\u02d5\u02d6"+
		"\3v;\0\u02d6\u02d7\7\5\0\0\u02d7\u008d\1\0\0\0\u02d8\u02d9\3@ \0\u02d9"+
		"\u02da\3\u0090H\0\u02da\u02db\3@ \0\u02db\u008f\1\0\0\0\u02dc\u02e0\3"+
		"\u0092I\0\u02dd\u02e0\3\u0094J\0\u02de\u02e0\59\0\0\u02df\u02dc\1\0\0"+
		"\0\u02df\u02dd\1\0\0\0\u02df\u02de\1\0\0\0\u02e0\u0091\1\0\0\0\u02e1\u02e2"+
		"\7\6\0\0\u02e2\u0093\1\0\0\0\u02e3\u02e4\7\7\0\0\u02e4\u0095\1\0\0\0\u02e5"+
		"\u02e9\5\17\0\0\u02e6\u02e7\3~?\0\u02e7\u02e8\5H\0\0\u02e8\u02ea\1\0\0"+
		"\0\u02e9\u02e6\1\0\0\0\u02e9\u02ea\1\0\0\0\u02ea\u02eb\1\0\0\0\u02eb\u02ec"+
		"\3v;\0\u02ec\u02f2\3\64\32\0\u02ed\u02f0\5\t\0\0\u02ee\u02f1\3\u0096K"+
		"\0\u02ef\u02f1\3\64\32\0\u02f0\u02ee\1\0\0\0\u02f0\u02ef\1\0\0\0\u02f1"+
		"\u02f3\1\0\0\0\u02f2\u02ed\1\0\0\0\u02f2\u02f3\1\0\0\0\u02f3\u0097\1\0"+
		"\0\0\u02f4\u02f7\3\u009aM\0\u02f5\u02f7\3\u00a0P\0\u02f6\u02f4\1\0\0\0"+
		"\u02f6\u02f5\1\0\0\0\u02f7\u0099\1\0\0\0\u02f8\u02fc\5\30\0\0\u02f9\u02fa"+
		"\3~?\0\u02fa\u02fb\5H\0\0\u02fb\u02fd\1\0\0\0\u02fc\u02f9\1\0\0\0\u02fc"+
		"\u02fd\1\0\0\0\u02fd\u02ff\1\0\0\0\u02fe\u0300\3v;\0\u02ff\u02fe\1\0\0"+
		"\0\u02ff\u0300\1\0\0\0\u0300\u0301\1\0\0\0\u0301\u0305\5D\0\0\u0302\u0304"+
		"\3\u009cN\0\u0303\u0302\1\0\0\0\u0304\u0307\1\0\0\0\u0305\u0303\1\0\0"+
		"\0\u0305\u0306\1\0\0\0\u0306\u0308\1\0\0\0\u0307\u0305\1\0\0\0\u0308\u0309"+
		"\5E\0\0\u0309\u009b\1\0\0\0\u030a\u030b\3\u009eO\0\u030b\u0317\5I\0\0"+
		"\u030c\u0311\3|>\0\u030d\u030e\5H\0\0\u030e\u0310\3|>\0\u030f\u030d\1"+
		"\0\0\0\u0310\u0313\1\0\0\0\u0311\u030f\1\0\0\0\u0311\u0312\1\0\0\0\u0312"+
		"\u0315\1\0\0\0\u0313\u0311\1\0\0\0\u0314\u0316\5H\0\0\u0315\u0314\1\0"+
		"\0\0\u0315\u0316\1\0\0\0\u0316\u0318\1\0\0\0\u0317\u030c\1\0\0\0\u0317"+
		"\u0318\1\0\0\0\u0318\u009d\1\0\0\0\u0319\u031a\5\3\0\0\u031a\u031d\3@"+
		" \0\u031b\u031d\5\7\0\0\u031c\u0319\1\0\0\0\u031c\u031b\1\0\0\0\u031d"+
		"\u009f\1\0\0\0\u031e\u0322\5\30\0\0\u031f\u0320\3~?\0\u0320\u0321\5H\0"+
		"\0\u0321\u0323\1\0\0\0\u0322\u031f\1\0\0\0\u0322\u0323\1\0\0\0\u0323\u0324"+
		"\1\0\0\0\u0324\u0325\3\u00a2Q\0\u0325\u0329\5D\0\0\u0326\u0328\3\u00a4"+
		"R\0\u0327\u0326\1\0\0\0\u0328\u032b\1\0\0\0\u0329\u0327\1\0\0\0\u0329"+
		"\u032a\1\0\0\0\u032a\u032c\1\0\0\0\u032b\u0329\1\0\0\0\u032c\u032d\5E"+
		"\0\0\u032d\u00a1\1\0\0\0\u032e\u032f\5J\0\0\u032f\u0331\5>\0\0\u0330\u032e"+
		"\1\0\0\0\u0330\u0331\1\0\0\0\u0331\u0332\1\0\0\0\u0332\u0333\3v;\0\u0333"+
		"\u0334\5G\0\0\u0334\u0335\5@\0\0\u0335\u0336\5\31\0\0\u0336\u0337\5A\0"+
		"\0\u0337\u00a3\1\0\0\0\u0338\u0339\3\u00a6S\0\u0339\u0345\5I\0\0\u033a"+
		"\u033f\3|>\0\u033b\u033c\5H\0\0\u033c\u033e\3|>\0\u033d\u033b\1\0\0\0"+
		"\u033e\u0341\1\0\0\0\u033f\u033d\1\0\0\0\u033f\u0340\1\0\0\0\u0340\u0343"+
		"\1\0\0\0\u0341\u033f\1\0\0\0\u0342\u0344\5H\0\0\u0343\u0342\1\0\0\0\u0343"+
		"\u0344\1\0\0\0\u0344\u0346\1\0\0\0\u0345\u033a\1\0\0\0\u0345\u0346\1\0"+
		"\0\0\u0346\u00a5\1\0\0\0\u0347\u0348\5\3\0\0\u0348\u034b\3\u00a8T\0\u0349"+
		"\u034b\5\7\0\0\u034a\u0347\1\0\0\0\u034a\u0349\1\0\0\0\u034b\u00a7\1\0"+
		"\0\0\u034c\u0351\3\0\0\0\u034d\u034e\5F\0\0\u034e\u0350\3\0\0\0\u034f"+
		"\u034d\1\0\0\0\u0350\u0353\1\0\0\0\u0351\u034f\1\0\0\0\u0351\u0352\1\0"+
		"\0\0\u0352\u00a9\1\0\0\0\u0353\u0351\1\0\0\0\u0354\u0358\5\13\0\0\u0355"+
		"\u0359\3\u00acV\0\u0356\u0359\3\u00aeW\0\u0357\u0359\3\u00b4Z\0\u0358"+
		"\u0355\1\0\0\0\u0358\u0356\1\0\0\0\u0358\u0357\1\0\0\0\u0358\u0359\1\0"+
		"\0\0\u0359\u035a\1\0\0\0\u035a\u035b\3\64\32\0\u035b\u00ab\1\0\0\0\u035c"+
		"\u035d\3v;\0\u035d\u00ad\1\0\0\0\u035e\u0360\3\u00b0X\0\u035f\u035e\1"+
		"\0\0\0\u035f\u0360\1\0\0\0\u0360\u0361\1\0\0\0\u0361\u0363\5H\0\0\u0362"+
		"\u0364\3\u00acV\0\u0363\u0362\1\0\0\0\u0363\u0364\1\0\0\0\u0364\u0365"+
		"\1\0\0\0\u0365\u0367\5H\0\0\u0366\u0368\3\u00b2Y\0\u0367\u0366\1\0\0\0"+
		"\u0367\u0368\1\0\0\0\u0368\u00af\1\0\0\0\u0369\u036a\3~?\0\u036a\u00b1"+
		"\1\0\0\0\u036b\u036c\3~?\0\u036c\u00b3\1\0\0\0\u036d\u0370\3v;\0\u036e"+
		"\u036f\5F\0\0\u036f\u0371\3v;\0\u0370\u036e\1\0\0\0\u0370\u0371\1\0\0"+
		"\0\u0371\u0374\1\0\0\0\u0372\u0375\59\0\0\u0373\u0375\5>\0\0\u0374\u0372"+
		"\1\0\0\0\u0374\u0373\1\0\0\0\u0375\u0376\1\0\0\0\u0376\u0377\5\24\0\0"+
		"\u0377\u0378\3v;\0\u0378\u00b5\1\0\0\0\u0379\u037a\5\r\0\0\u037a\u037b"+
		"\3v;\0\u037b\u00b7\1\0\0\0\u037c\u037d\5\26\0\0\u037d\u0381\5D\0\0\u037e"+
		"\u0380\3\u00ba]\0\u037f\u037e\1\0\0\0\u0380\u0383\1\0\0\0\u0381\u037f"+
		"\1\0\0\0\u0381\u0382\1\0\0\0\u0382\u0384\1\0\0\0\u0383\u0381\1\0\0\0\u0384"+
		"\u0385\5E\0\0\u0385\u00b9\1\0\0\0\u0386\u0387\3\u00bc^\0\u0387\u0393\5"+
		"I\0\0\u0388\u038d\3|>\0\u0389\u038a\5H\0\0\u038a\u038c\3|>\0\u038b\u0389"+
		"\1\0\0\0\u038c\u038f\1\0\0\0\u038d\u038b\1\0\0\0\u038d\u038e\1\0\0\0\u038e"+
		"\u0391\1\0\0\0\u038f\u038d\1\0\0\0\u0390\u0392\5H\0\0\u0391\u0390\1\0"+
		"\0\0\u0391\u0392\1\0\0\0\u0392\u0394\1\0\0\0\u0393\u0388\1\0\0\0\u0393"+
		"\u0394\1\0\0\0\u0394\u00bb\1\0\0\0\u0395\u0398\5\3\0\0\u0396\u0399\3\u0088"+
		"D\0\u0397\u0399\3\u00be_\0\u0398\u0396\1\0\0\0\u0398\u0397\1\0\0\0\u0399"+
		"\u039c\1\0\0\0\u039a\u039c\5\7\0\0\u039b\u0395\1\0\0\0\u039b\u039a\1\0"+
		"\0\0\u039c\u00bd\1\0\0\0\u039d\u03a0\3v;\0\u039e\u039f\5F\0\0\u039f\u03a1"+
		"\3v;\0\u03a0\u039e\1\0\0\0\u03a0\u03a1\1\0\0\0\u03a1\u03a4\1\0\0\0\u03a2"+
		"\u03a5\59\0\0\u03a3\u03a5\5>\0\0\u03a4\u03a2\1\0\0\0\u03a4\u03a3\1\0\0"+
		"\0\u03a5\u03a7\1\0\0\0\u03a6\u039d\1\0\0\0\u03a6\u03a7\1\0\0\0\u03a7\u03a8"+
		"\1\0\0\0\u03a8\u03a9\3\u00c0`\0\u03a9\u00bf\1\0\0\0\u03aa\u03ab\3v;\0"+
		"\u03ab\u00c1\1\0\0\0\u03ac\u03ae\5\25\0\0\u03ad\u03af\3@ \0\u03ae\u03ad"+
		"\1\0\0\0\u03ae\u03af\1\0\0\0\u03af\u00c3\1\0\0\0\u03b0\u03b2\5\2\0\0\u03b1"+
		"\u03b3\3\u0084B\0\u03b2\u03b1\1\0\0\0\u03b2\u03b3\1\0\0\0\u03b3\u00c5"+
		"\1\0\0\0\u03b4\u03b6\5\6\0\0\u03b5\u03b7\3\u0084B\0\u03b6\u03b5\1\0\0"+
		"\0\u03b6\u03b7\1\0\0\0\u03b7\u00c7\1\0\0\0\u03b8\u03b9\5\16\0\0\u03b9"+
		"\u03ba\3\u0084B\0\u03ba\u00c9\1\0\0\0\u03bb\u03bc\5\n\0\0\u03bc\u00cb"+
		"\1\0\0\0\u03bd\u03be\5\b\0\0\u03be\u03bf\3v;\0\u03bf\u00cd\1\0\0\0\u03c0"+
		"\u03c1\5J\0\0\u03c1\u03c6\5@\0\0\u03c2\u03c4\3\u00d0h\0\u03c3\u03c5\5"+
		"F\0\0\u03c4\u03c3\1\0\0\0\u03c4\u03c5\1\0\0\0\u03c5\u03c7\1\0\0\0\u03c6"+
		"\u03c2\1\0\0\0\u03c6\u03c7\1\0\0\0\u03c7\u03c8\1\0\0\0\u03c8\u03c9\5A"+
		"\0\0\u03c9\u00cf\1\0\0\0\u03ca\u03cd\3\0\0\0\u03cb\u03cc\5F\0\0\u03cc"+
		"\u03ce\3@ \0\u03cd\u03cb\1\0\0\0\u03cd\u03ce\1\0\0\0\u03ce\u03d1\1\0\0"+
		"\0\u03cf\u03d1\3@ \0\u03d0\u03ca\1\0\0\0\u03d0\u03cf\1\0\0\0\u03d1\u00d1"+
		"\1\0\0\0\u03d2\u03d3\3\u00d6k\0\u03d3\u03d4\5H\0\0\u03d4\u03d6\1\0\0\0"+
		"\u03d5\u03d2\1\0\0\0\u03d5\u03d6\1\0\0\0\u03d6\u03dc\1\0\0\0\u03d7\u03d8"+
		"\3\u00dam\0\u03d8\u03d9\5H\0\0\u03d9\u03db\1\0\0\0\u03da\u03d7\1\0\0\0"+
		"\u03db\u03de\1\0\0\0\u03dc\u03da\1\0\0\0\u03dc\u03dd\1\0\0\0\u03dd\u03e4"+
		"\1\0\0\0\u03de\u03dc\1\0\0\0\u03df\u03e0\38\34\0\u03e0\u03e1\5H\0\0\u03e1"+
		"\u03e3\1\0\0\0\u03e2\u03df\1\0\0\0\u03e3\u03e6\1\0\0\0\u03e4\u03e2\1\0"+
		"\0\0\u03e4\u03e5\1\0\0\0\u03e5\u00d3\1\0\0\0\u03e6\u03e4\1\0\0\0\u03e7"+
		"\u03e8\3\u00d2i\0\u03e8\u03e9\5\uffff\0\0\u03e9\u00d5\1\0\0\0\u03ea\u03eb"+
		"\5\23\0\0\u03eb\u03ec\3\u00d8l\0\u03ec\u03ed\6k\uffff\0\u03ed\u00d7\1"+
		"\0\0\0\u03ee\u03ef\5J\0\0\u03ef\u00d9\1\0\0\0\u03f0\u0401\5\20\0\0\u03f1"+
		"\u0402\3\u00dcn\0\u03f2\u03fe\5@\0\0\u03f3\u03f8\3\u00dcn\0\u03f4\u03f5"+
		"\5H\0\0\u03f5\u03f7\3\u00dcn\0\u03f6\u03f4\1\0\0\0\u03f7\u03fa\1\0\0\0"+
		"\u03f8\u03f6\1\0\0\0\u03f8\u03f9\1\0\0\0\u03f9\u03fc\1\0\0\0\u03fa\u03f8"+
		"\1\0\0\0\u03fb\u03fd\5H\0\0\u03fc\u03fb\1\0\0\0\u03fc\u03fd\1\0\0\0\u03fd"+
		"\u03ff\1\0\0\0\u03fe\u03f3\1\0\0\0\u03fe\u03ff\1\0\0\0\u03ff\u0400\1\0"+
		"\0\0\u0400\u0402\5A\0\0\u0401\u03f1\1\0\0\0\u0401\u03f2\1\0\0\0\u0402"+
		"\u00db\1\0\0\0\u0403\u0404\5G\0\0\u0404\u040d\3\u00deo\0\u0405\u0406\3"+
		"\u00d8l\0\u0406\u0407\3\u00deo\0\u0407\u0408\6n\uffff\0\u0408\u040d\1"+
		"\0\0\0\u0409\u040a\3\u00deo\0\u040a\u040b\6n\uffff\0\u040b\u040d\1\0\0"+
		"\0\u040c\u0403\1\0\0\0\u040c\u0405\1\0\0\0\u040c\u0409\1\0\0\0\u040d\u00dd"+
		"\1\0\0\0\u040e\u040f\5R\0\0\u040f\u00df\1\0\0\0v\u00e6\u00f2\u0108\u010c"+
		"\u0114\u0117\u011a\u012a\u012e\u0133\u0135\u013e\u0142\u0145\u0150\u0154"+
		"\u0156\u015e\u016e\u0172\u017c\u0180\u0182\u0189\u018e\u0198\u019c\u019e"+
		"\u01a1\u01a5\u01a9\u01b0\u01b8\u01c3\u01c7\u01c9\u01cc\u01d9\u01dd\u01df"+
		"\u01e2\u01e8\u01ec\u01f6\u01ff\u0203\u0206\u0214\u0219\u0221\u022f\u023d"+
		"\u0242\u0244\u024d\u0253\u0259\u0261\u026c\u0288\u028c\u0299\u029b\u029e"+
		"\u02a0\u02a5\u02bb\u02c3\u02df\u02e9\u02f0\u02f2\u02f6\u02fc\u02ff\u0305"+
		"\u0311\u0315\u0317\u031c\u0322\u0329\u0330\u033f\u0343\u0345\u034a\u0351"+
		"\u0358\u035f\u0363\u0367\u0370\u0374\u0381\u038d\u0391\u0393\u0398\u039b"+
		"\u03a0\u03a4\u03a6\u03ae\u03b2\u03b6\u03c4\u03c6\u03cd\u03d0\u03d5\u03dc"+
		"\u03e4\u03f8\u03fc\u03fe\u0401\u040c";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	}
}