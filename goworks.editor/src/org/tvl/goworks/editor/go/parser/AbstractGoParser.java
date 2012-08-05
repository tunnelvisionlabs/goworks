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
		Switch=23, Pipe=32, PipeEqual=43, LessThan=54, GreaterEqual=60, Goto=13, 
		Go=12, LessEqual=59, AmpEqual=42, Fallthrough=9, BangEqual=58, Case=2, 
		GreaterThan=55, Func=11, Percent=30, LeftArrow=50, IMAGINARY_LITERAL=79, 
		Default=6, ML_COMMENT=77, StarEqual=39, Map=17, CharLiteral=81, IDENTIFIER=73, 
		Amp=31, Chan=3, Ellipsis=62, Interface=16, ANYCHAR=83, Select=21, AmpCaretEqual=47, 
		Or=49, COMMENT=76, Return=20, Struct=22, If=14, Caret=33, PercentEqual=41, 
		RightShiftEqual=46, And=48, INT_LITERAL=78, Import=15, Type=24, PlusEqual=37, 
		Continue=5, MinusEqual=38, ColonEqual=61, LeftShiftEqual=45, Colon=72, 
		LeftShift=34, EqualEqual=53, Const=4, Equal=56, Dec=52, Package=18, For=10, 
		RightShift=35, StringLiteral=82, FLOAT_LITERAL=80, CaretEqual=44, Range=19, 
		Plus=26, Bang=57, Minus=27, WS=74, Semi=71, NEWLINE=75, Break=1, Inc=51, 
		LeftBrace=67, SlashEqual=40, Dot=70, LeftBrack=65, RightBrack=66, LeftParen=63, 
		Defer=7, Star=28, RightParen=64, AmpCaret=36, Else=8, Comma=69, Var=25, 
		Slash=29, RightBrace=68;
	public static final String[] tokenNames = {
        "<INVALID>", "'break'", "'case'", "'chan'", "'const'", "'continue'", 
        "'default'", "'defer'", "'else'", "'fallthrough'", "'for'", "'func'", 
        "'go'", "'goto'", "'if'", "'import'", "'interface'", "'map'", "'package'", 
        "'range'", "'return'", "'select'", "'struct'", "'switch'", "'type'", 
        "'var'", "'+'", "'-'", "'*'", "'/'", "'%'", "'&'", "'|'", "'^'", 
        "'<<'", "'>>'", "'&^'", "'+='", "'-='", "'*='", "'/='", "'%='", 
        "'&='", "'|='", "'^='", "'<<='", "'>>='", "'&^='", "'&&'", "'||'", 
        "'<-'", "'++'", "'--'", "'=='", "'<'", "'>'", "'='", "'!'", "'!='", 
        "'<='", "'>='", "':='", "'...'", "'('", "')'", "'['", "']'", "'{'", 
        "'}'", "','", "'.'", "';'", "':'", "IDENTIFIER", "WS", "NEWLINE", 
        "COMMENT", "ML_COMMENT", "INT_LITERAL", "IMAGINARY_LITERAL", "FLOAT_LITERAL", 
        "CharLiteral", "StringLiteral", "ANYCHAR"
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
		RULE_builtinArgs = 104, RULE_sourceFile = 105, RULE_packageClause = 106, 
		RULE_packageName = 107, RULE_importDecl = 108, RULE_importSpec = 109, 
		RULE_importPath = 110;
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
		"builtinArgs", "sourceFile", "packageClause", "packageName", "importDecl", 
		"importSpec", "importPath"
	};

	@Override
	public String getGrammarFileName() { return "GoParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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
			setState(228);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(222); typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(223); typeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(224); match(LeftParen);
					setState(225); type();
					setState(226); match(RightParen);
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
			setState(230); qualifiedIdentifier();
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
			setState(240);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(232); arrayType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(233); structType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(234); pointerType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(235); functionType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(236); interfaceType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(237); sliceType();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(238); mapType();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(239); channelType();
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
			setState(242); match(LeftBrack);
			setState(243); arrayLength();
			setState(244); match(RightBrack);
			setState(245); elementType();
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
			setState(247); expression(0);
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
			setState(249); type();
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
			setState(251); match(LeftBrack);
			setState(252); match(RightBrack);
			setState(253); elementType();
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
			setState(255); match(Struct);
			setState(256); match(LeftBrace);
			setState(262);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(257); fieldDecl();
					setState(258); match(Semi);
					}
					} 
				}
				setState(264);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(266);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(265); fieldDecl();
					}
					break;
			}
			setState(268); match(RightBrace);
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
			setState(274);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(270); identifierList();
					setState(271); type();
					}
					break;

				case 2:
					{
					setState(273); anonymousField();
					}
					break;
			}
			setState(277);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(276); tag();
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
			setState(280);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(279); _localctx.ptr = match(Star);
					}
					break;
			}
			setState(282); typeName();
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
			setState(284); match(StringLiteral);
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
			setState(286); _localctx.ptr = match(Star);
			setState(287); baseType();
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
			setState(289); type();
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
			setState(291); match(Func);
			setState(292); signature();
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
			setState(294); parameters();
			setState(296);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(295); result();
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
			setState(300);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(298); parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(299); type();
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
			setState(302); match(LeftParen);
			setState(307);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(303); parameterList();
					setState(305);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
						case 1:
							{
							setState(304); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(309); match(RightParen);
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
			setState(311); parameterDecl();
			setState(316);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(312); match(Comma);
					setState(313); parameterDecl();
					}
					} 
				}
				setState(318);
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
			setState(320);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(319); identifierList();
					}
					break;
			}
			setState(323);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(322); _localctx.ellip = match(Ellipsis);
					}
					break;
			}
			setState(325); type();
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
			setState(327); match(Interface);
			setState(328); match(LeftBrace);
			setState(340);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(329); methodSpec();
					setState(334);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(330); match(Semi);
							setState(331); methodSpec();
							}
							} 
						}
						setState(336);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
					}
					setState(338);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
						case 1:
							{
							setState(337); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(342); match(RightBrace);
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
			setState(348);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(344); methodName();
					setState(345); signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(347); interfaceTypeName();
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
			setState(350); match(IDENTIFIER);
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
			setState(352); typeName();
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
			setState(354); match(Map);
			setState(355); match(LeftBrack);
			setState(356); keyType();
			setState(357); match(RightBrack);
			setState(358); elementType();
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
			setState(360); type();
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
			setState(368);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(362); match(Chan);
					setState(364);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
						case 1:
							{
							setState(363); _localctx.send = match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(366); _localctx.recv = match(LeftArrow);
					setState(367); match(Chan);
					}
					break;
			}
			setState(370); elementType();
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
			setState(372); match(LeftBrace);
			setState(384);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(373); statement();
					setState(378);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(374); match(Semi);
							setState(375); statement();
							}
							} 
						}
						setState(380);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(382);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(381); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(386); match(RightBrace);
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
			setState(391);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(388); constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(389); typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(390); varDecl();
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
			setState(396);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(393); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(394); functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(395); methodDecl();
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
			setState(398); match(Const);
			setState(415);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(399); constSpec();
					}
					break;

				case 2:
					{
					setState(400); match(LeftParen);
					setState(412);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
						case 1:
							{
							setState(401); constSpec();
							setState(406);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(402); match(Semi);
									setState(403); constSpec();
									}
									} 
								}
								setState(408);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
							}
							setState(410);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
								case 1:
									{
									setState(409); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(414); match(RightParen);
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
			setState(417); identifierList();
			setState(423);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(419);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
						case 1:
							{
							setState(418); type();
							}
							break;
					}
					setState(421); match(Equal);
					setState(422); expressionList();
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
			setState(425); match(IDENTIFIER);
			setState(430);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(426); match(Comma);
					setState(427); match(IDENTIFIER);
					}
					} 
				}
				setState(432);
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
			setState(433); expression(0);
			setState(438);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(434); match(Comma);
					setState(435); expression(0);
					}
					} 
				}
				setState(440);
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
			setState(441); match(Type);
			setState(458);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(442); typeSpec();
					}
					break;

				case 2:
					{
					setState(443); match(LeftParen);
					setState(455);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(444); typeSpec();
							setState(449);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(445); match(Semi);
									setState(446); typeSpec();
									}
									} 
								}
								setState(451);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
							}
							setState(453);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
								case 1:
									{
									setState(452); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(457); match(RightParen);
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
			setState(460); match(IDENTIFIER);
			setState(461); type();
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
			setState(463); match(Var);
			setState(480);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(464); varSpec();
					}
					break;

				case 2:
					{
					setState(465); match(LeftParen);
					setState(477);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(466); varSpec();
							setState(471);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(467); match(Semi);
									setState(468); varSpec();
									}
									} 
								}
								setState(473);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
							}
							setState(475);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
								case 1:
									{
									setState(474); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(479); match(RightParen);
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
		public List<? extends ExpressionListContext> expressionList() {
		    return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
		    return getRuleContext(ExpressionListContext.class,i);
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
			setState(482); identifierList();
			setState(490);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(483); type();
					setState(486);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(484); match(Equal);
							setState(485); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(488); match(Equal);
					setState(489); expressionList();
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
			setState(492); identifierList();
			setState(493); match(ColonEqual);
			setState(494); expressionList();
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
			setState(496); match(Func);
			setState(497); match(IDENTIFIER);
			setState(498); signature();
			setState(500);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(499); body();
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
			setState(502); block();
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
			setState(504); match(Func);
			setState(505); receiver();
			setState(506); methodName();
			setState(507); signature();
			setState(509);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(508); body();
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
			setState(511); match(LeftParen);
			setState(513);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(512); match(IDENTIFIER);
					}
					break;
			}
			setState(516);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(515); _localctx.ptr = match(Star);
					}
					break;
			}
			setState(518); baseTypeName();
			setState(519); match(RightParen);
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
			setState(521); match(IDENTIFIER);
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
			setState(530);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(523); literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(524); qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(525); methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(526); match(LeftParen);
					setState(527); expression(0);
					setState(528); match(RightParen);
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
			setState(535);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(532); basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(533); compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(534); functionLiteral();
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
		try {
			setState(542);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(537); match(INT_LITERAL);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(538); match(FLOAT_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(539); match(IMAGINARY_LITERAL);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(540); match(CharLiteral);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(541); match(StringLiteral);
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
			setState(548);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(544);
					if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "isPackageName(_input.LT(1))");
					setState(545); packageName();
					setState(546); _localctx.dot = match(Dot);
					}
					break;
			}
			setState(550); match(IDENTIFIER);
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
			setState(552); receiverType();
			setState(553); _localctx.dot = match(Dot);
			setState(554); methodName();
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
			setState(562);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(556); typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(557); match(LeftParen);
					setState(558); _localctx.ptr = match(Star);
					setState(559); typeName();
					setState(560); match(RightParen);
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
			setState(564); literalType();
			setState(565); literalValue();
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
			setState(576);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(567); structType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(568); arrayType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(569); match(LeftBrack);
					setState(570); match(Ellipsis);
					setState(571); match(RightBrack);
					setState(572); elementType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(573); sliceType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(574); mapType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(575); typeName();
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
			setState(578); match(LeftBrace);
			setState(583);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
				case 1:
					{
					setState(579); elementList();
					setState(581);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
						case 1:
							{
							setState(580); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(585); match(RightBrace);
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
			setState(587); element();
			setState(592);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(588); match(Comma);
					setState(589); element();
					}
					} 
				}
				setState(594);
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
		enterRule(_localctx, 106, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(598);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(595); key();
					setState(596); match(Colon);
					}
					break;
			}
			setState(600); value();
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
			setState(604);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(602); fieldName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(603); elementIndex();
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
			setState(606); match(IDENTIFIER);
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
			setState(608); expression(0);
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
			setState(612);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(610); expression(0);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(611); literalValue();
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
			setState(614); functionType();
			setState(615); body();
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
		pushNewRecursionContext(_localctx, 118, RULE_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					_localctx = new UnaryExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(625);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(618); ((UnaryExprContext)_localctx).op = match(Plus);
							}
							break;

						case 2:
							{
							setState(619); ((UnaryExprContext)_localctx).op = match(Minus);
							}
							break;

						case 3:
							{
							setState(620); ((UnaryExprContext)_localctx).op = match(Bang);
							}
							break;

						case 4:
							{
							setState(621); ((UnaryExprContext)_localctx).op = match(Caret);
							}
							break;

						case 5:
							{
							setState(622); ((UnaryExprContext)_localctx).op = match(Star);
							}
							break;

						case 6:
							{
							setState(623); ((UnaryExprContext)_localctx).op = match(Amp);
							}
							break;

						case 7:
							{
							setState(624); ((UnaryExprContext)_localctx).op = match(LeftArrow);
							}
							break;
					}
					setState(627); expression(6);
					}
					break;

				case 2:
					{
					_localctx = new OperandExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(628); operand();
					}
					break;

				case 3:
					{
					_localctx = new ConversionOrCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(629); conversion();
					}
					break;

				case 4:
					{
					_localctx = new BuiltinCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(630); builtinCall();
					}
					break;
			}
			_ctx.stop = _input.LT(-1);
			setState(703);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					_prevctx.stop = _input.LT(-1);
					{
					setState(701);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
						case 1:
							{
							_localctx = new MultExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(633);
							if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
							setState(641);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
								case 1:
									{
									setState(634); ((MultExprContext)_localctx).op = match(Star);
									}
									break;

								case 2:
									{
									setState(635); ((MultExprContext)_localctx).op = match(Slash);
									}
									break;

								case 3:
									{
									setState(636); ((MultExprContext)_localctx).op = match(Percent);
									}
									break;

								case 4:
									{
									setState(637); ((MultExprContext)_localctx).op = match(LeftShift);
									}
									break;

								case 5:
									{
									setState(638); ((MultExprContext)_localctx).op = match(RightShift);
									}
									break;

								case 6:
									{
									setState(639); ((MultExprContext)_localctx).op = match(Amp);
									}
									break;

								case 7:
									{
									setState(640); ((MultExprContext)_localctx).op = match(AmpCaret);
									}
									break;
							}
							setState(643); expression(6);
							}
							break;

						case 2:
							{
							_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(644);
							if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
							setState(649);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
								case 1:
									{
									setState(645); ((AddExprContext)_localctx).op = match(Plus);
									}
									break;

								case 2:
									{
									setState(646); ((AddExprContext)_localctx).op = match(Minus);
									}
									break;

								case 3:
									{
									setState(647); ((AddExprContext)_localctx).op = match(Pipe);
									}
									break;

								case 4:
									{
									setState(648); ((AddExprContext)_localctx).op = match(Caret);
									}
									break;
							}
							setState(651); expression(5);
							}
							break;

						case 3:
							{
							_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(652);
							if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
							setState(659);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
								case 1:
									{
									setState(653); ((CompareExprContext)_localctx).op = match(EqualEqual);
									}
									break;

								case 2:
									{
									setState(654); ((CompareExprContext)_localctx).op = match(BangEqual);
									}
									break;

								case 3:
									{
									setState(655); ((CompareExprContext)_localctx).op = match(LessThan);
									}
									break;

								case 4:
									{
									setState(656); ((CompareExprContext)_localctx).op = match(LessEqual);
									}
									break;

								case 5:
									{
									setState(657); ((CompareExprContext)_localctx).op = match(GreaterThan);
									}
									break;

								case 6:
									{
									setState(658); ((CompareExprContext)_localctx).op = match(GreaterEqual);
									}
									break;
							}
							setState(661); expression(4);
							}
							break;

						case 4:
							{
							_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(662);
							if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
							setState(663); match(And);
							setState(664); expression(3);
							}
							break;

						case 5:
							{
							_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(665);
							if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "1 >= $_p");
							setState(666); match(Or);
							setState(667); expression(2);
							}
							break;

						case 6:
							{
							_localctx = new SelectorExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(668);
							if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
							setState(669); ((SelectorExprContext)_localctx).dot = match(Dot);
							setState(670); match(IDENTIFIER);
							}
							break;

						case 7:
							{
							_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(671);
							if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
							setState(672); match(LeftBrack);
							setState(673); expression(0);
							setState(674); match(RightBrack);
							}
							break;

						case 8:
							{
							_localctx = new SliceExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(676);
							if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
							setState(677); match(LeftBrack);
							setState(679);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
								case 1:
									{
									setState(678); ((SliceExprContext)_localctx).from = expression(0);
									}
									break;
							}
							setState(681); match(Colon);
							setState(683);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
								case 1:
									{
									setState(682); ((SliceExprContext)_localctx).to = expression(0);
									}
									break;
							}
							setState(685); match(RightBrack);
							}
							break;

						case 9:
							{
							_localctx = new TypeAssertionExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(686);
							if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
							setState(687); ((TypeAssertionExprContext)_localctx).dot = match(Dot);
							setState(688); ((TypeAssertionExprContext)_localctx).lp = match(LeftParen);
							setState(689); type();
							setState(690); ((TypeAssertionExprContext)_localctx).rp = match(RightParen);
							}
							break;

						case 10:
							{
							_localctx = new CallExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(692);
							if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
							setState(693); ((CallExprContext)_localctx).lp = match(LeftParen);
							setState(698);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
								case 1:
									{
									setState(694); argumentList();
									setState(696);
									//_errHandler.sync(this);
									switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
										case 1:
											{
											setState(695); match(Comma);
											}
											break;
									}
									}
									break;
							}
							setState(700); ((CallExprContext)_localctx).rp = match(RightParen);
							}
							break;
					}
					} 
				}
				setState(705);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx, _parentState);
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
			setState(706); expressionList();
			setState(708);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(707); _localctx.ellip = match(Ellipsis);
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
			setState(710); type();
			setState(711); match(LeftParen);
			setState(712); expression(0);
			setState(713); match(RightParen);
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
			setState(730);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(715); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(716); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(717); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(718); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(719); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(720); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(721); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(722); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(723); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(724); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(725); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(726); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(727); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(728); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(729); deferStmt();
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
			setState(738);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(732); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(733); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(734); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(735); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(736); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(737); shortVarDecl();
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
		enterRule(_localctx, 132, RULE_label);
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

	@RuleVersion(0)
	public final ExpressionStmtContext expressionStmt() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_expressionStmt);
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

	@RuleVersion(0)
	public final SendStmtContext sendStmt() throws RecognitionException {
		SendStmtContext _localctx = new SendStmtContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_sendStmt);
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

	@RuleVersion(0)
	public final ChannelContext channel() throws RecognitionException {
		ChannelContext _localctx = new ChannelContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_channel);
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

	@RuleVersion(0)
	public final IncDecStmtContext incDecStmt() throws RecognitionException {
		IncDecStmtContext _localctx = new IncDecStmtContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_incDecStmt);
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
			setState(759); _localctx.targets = expressionList();
			setState(760); assignOp();
			setState(761); _localctx.values = expressionList();
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
			setState(766);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
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
		enterRule(_localctx, 146, RULE_addAssignOp);
		try {
			setState(772);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(768); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(769); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(770); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(771); match(CaretEqual);
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
		try {
			setState(782);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(774); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(775); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(776); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(777); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(778); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(779); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(780); match(AmpCaretEqual);
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(781); match(PercentEqual);
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
			setState(784); match(If);
			setState(788);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(785); simpleStmt();
					setState(786); match(Semi);
					}
					break;
			}
			setState(790); expression(0);
			setState(791); block();
			setState(797);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(792); match(Else);
					setState(795);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(793); ifStmt();
							}
							break;

						case 2:
							{
							setState(794); block();
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
			setState(801);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(799); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(800); typeSwitchStmt();
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
		public SimpleStmtContext simpleStmt() {
		    return getRuleContext(SimpleStmtContext.class,0);
		}
		public ExprCaseClauseContext exprCaseClause() {
		    return getRuleContext(ExprCaseClauseContext.class,0);
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
			setState(803); match(Switch);
			setState(807);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(804); simpleStmt();
					setState(805); match(Semi);
					}
					break;
			}
			setState(810);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(809); expression(0);
					}
					break;
			}
			setState(812); match(LeftBrace);
			setState(816);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(813); exprCaseClause();
					}
					} 
				}
				setState(818);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(819); match(RightBrace);
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
			setState(821); exprSwitchCase();
			setState(822); match(Colon);
			setState(834);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(823); statement();
					setState(828);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(824); match(Semi);
							setState(825); statement();
							}
							} 
						}
						setState(830);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
					}
					setState(832);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
						case 1:
							{
							setState(831); match(Semi);
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
			setState(839);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(836); match(Case);
					setState(837); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(838); match(Default);
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
		public TypeCaseClauseContext typeCaseClause() {
		    return getRuleContext(TypeCaseClauseContext.class,0);
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
			setState(841); match(Switch);
			setState(845);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(842); simpleStmt();
					setState(843); match(Semi);
					}
					break;
			}
			setState(847); typeSwitchGuard();
			setState(848); match(LeftBrace);
			setState(852);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(849); typeCaseClause();
					}
					} 
				}
				setState(854);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(855); match(RightBrace);
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
			setState(859);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					{
					setState(857); match(IDENTIFIER);
					setState(858); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(861); expression(0);
			setState(862); _localctx.dot = match(Dot);
			setState(863); match(LeftParen);
			setState(864); match(Type);
			setState(865); match(RightParen);
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
			setState(867); typeSwitchCase();
			setState(868); match(Colon);
			setState(880);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(869); statement();
					setState(874);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(870); match(Semi);
							setState(871); statement();
							}
							} 
						}
						setState(876);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					}
					setState(878);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
						case 1:
							{
							setState(877); match(Semi);
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
			setState(885);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(882); match(Case);
					setState(883); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(884); match(Default);
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
			setState(887); type();
			setState(892);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(888); match(Comma);
					setState(889); type();
					}
					} 
				}
				setState(894);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
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
			setState(895); match(For);
			setState(899);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(896); condition();
					}
					break;

				case 2:
					{
					setState(897); forClause();
					}
					break;

				case 3:
					{
					setState(898); rangeClause();
					}
					break;
			}
			setState(901); block();
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
			setState(903); expression(0);
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
			setState(906);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(905); initStmt();
					}
					break;
			}
			setState(908); match(Semi);
			setState(910);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(909); condition();
					}
					break;
			}
			setState(912); match(Semi);
			setState(914);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(913); postStmt();
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
			setState(916); simpleStmt();
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
			setState(918); simpleStmt();
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
			setState(920); _localctx.e1 = expression(0);
			setState(923);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(921); match(Comma);
					setState(922); _localctx.e2 = expression(0);
					}
					break;
			}
			setState(927);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(925); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(926); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(929); match(Range);
			setState(930); _localctx.e = expression(0);
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
			setState(932); match(Go);
			setState(933); expression(0);
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
		public CommClauseContext commClause() {
		    return getRuleContext(CommClauseContext.class,0);
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
			setState(935); match(Select);
			setState(936); match(LeftBrace);
			setState(940);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(937); commClause();
					}
					} 
				}
				setState(942);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			}
			setState(943); match(RightBrace);
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
			setState(945); commCase();
			setState(946); match(Colon);
			setState(958);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(947); statement();
					setState(952);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(948); match(Semi);
							setState(949); statement();
							}
							} 
						}
						setState(954);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					}
					setState(956);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
						case 1:
							{
							setState(955); match(Semi);
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
			setState(966);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(960); match(Case);
					setState(963);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
						case 1:
							{
							setState(961); sendStmt();
							}
							break;

						case 2:
							{
							setState(962); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(965); match(Default);
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
			setState(977);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(968); _localctx.e1 = expression(0);
					setState(971);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(969); match(Comma);
							setState(970); _localctx.e2 = expression(0);
							}
							break;
					}
					setState(975);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
						case 1:
							{
							setState(973); _localctx.eq = match(Equal);
							}
							break;

						case 2:
							{
							setState(974); _localctx.defeq = match(ColonEqual);
							}
							break;
					}
					}
					break;
			}
			setState(979); recvExpr();
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
			setState(981); expression(0);
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
			setState(983); match(Return);
			setState(985);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
				case 1:
					{
					setState(984); expressionList();
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
			setState(987); match(Break);
			setState(989);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					{
					setState(988); label();
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
			setState(991); match(Continue);
			setState(993);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
				case 1:
					{
					setState(992); label();
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
			setState(995); match(Goto);
			setState(996); label();
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
			setState(998); match(Fallthrough);
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
			setState(1000); match(Defer);
			setState(1001); expression(0);
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
			setState(1003); match(IDENTIFIER);
			setState(1004); match(LeftParen);
			setState(1009);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1005); builtinArgs();
					setState(1007);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
						case 1:
							{
							setState(1006); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1011); match(RightParen);
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
			setState(1019);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1013); type();
					setState(1016);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
						case 1:
							{
							setState(1014); match(Comma);
							setState(1015); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1018); expressionList();
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

	public static class SourceFileContext extends ParserRuleContext<Token> {
		public ImportDeclContext importDecl() {
		    return getRuleContext(ImportDeclContext.class,0);
		}
		public TopLevelDeclContext topLevelDecl() {
		    return getRuleContext(TopLevelDeclContext.class,0);
		}
		public PackageClauseContext packageClause() {
		    return getRuleContext(PackageClauseContext.class,0);
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

	@RuleVersion(0)
	public final SourceFileContext sourceFile() throws RecognitionException {
		SourceFileContext _localctx = new SourceFileContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_sourceFile);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1024);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1021); packageClause();
					setState(1022); match(Semi);
					}
					break;
			}
			setState(1031);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1026); importDecl();
					setState(1027); match(Semi);
					}
					} 
				}
				setState(1033);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(1039);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1034); topLevelDecl();
					setState(1035); match(Semi);
					}
					} 
				}
				setState(1041);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
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
		enterRule(_localctx, 212, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1042); match(Package);
			setState(1043); _localctx.packageName = packageName();
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
		enterRule(_localctx, 214, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1046); match(IDENTIFIER);
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
		enterRule(_localctx, 216, RULE_importDecl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1048); match(Import);
			setState(1065);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
				case 1:
					{
					setState(1049); importSpec();
					}
					break;

				case 2:
					{
					setState(1050); match(LeftParen);
					setState(1062);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
						case 1:
							{
							setState(1051); importSpec();
							setState(1056);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(1052); match(Semi);
									setState(1053); importSpec();
									}
									} 
								}
								setState(1058);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
							}
							setState(1060);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
								case 1:
									{
									setState(1059); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(1064); match(RightParen);
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
		enterRule(_localctx, 218, RULE_importSpec);
		try {
			setState(1076);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1067); _localctx.dot = match(Dot);
					setState(1068); importPath();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1069); _localctx.packageName = packageName();
					setState(1070); importPath();
					addPackageName((_localctx.packageName!=null?(_localctx.packageName.start):null));
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1073); _localctx.importPath = importPath();
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
		enterRule(_localctx, 220, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1078); match(StringLiteral);
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
		switch ( ruleIndex ) {
				case 46 : return qualifiedIdentifier_sempred((QualifiedIdentifierContext)_localctx, predIndex);

				case 59 : return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	public boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch ( predIndex ) {
			case 1 : return 5 >= _localctx._p;

			case 2 : return 4 >= _localctx._p;

			case 3 : return 3 >= _localctx._p;

			case 4 : return 2 >= _localctx._p;

			case 5 : return 1 >= _localctx._p;

			case 6 : return 11 >= _localctx._p;

			case 7 : return 10 >= _localctx._p;

			case 8 : return 9 >= _localctx._p;

			case 9 : return 8 >= _localctx._p;

			case 10 : return 7 >= _localctx._p;
		}
		return true;
	}
	public boolean qualifiedIdentifier_sempred(QualifiedIdentifierContext _localctx, int predIndex) {
		switch ( predIndex ) {
			case 0 : return isPackageName(_input.LT(1));
		}
		return true;
	}

	public static final String _serializedATN =
		"\1S\u0439\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		"\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\1\0\1\0\1\0\1\0\1"+
		"\0\1\0\3\0\b\0\1\1\1\1\1\2\1\2\1\2\1\2\1\2\1\2\1\2\1\2\3\2\b\2\1\3\1\3"+
		"\1\3\1\3\1\3\1\4\1\4\1\5\1\5\1\6\1\6\1\6\1\6\1\7\1\7\1\7\1\7\1\7\5\7\b"+
		"\7\n\7\f\7\u0108\t\7\1\7\3\7\b\7\1\7\1\7\1\b\1\b\1\b\1\b\3\b\b\b\1\b\3"+
		"\b\b\b\1\t\3\t\b\t\1\t\1\t\1\n\1\n\1\13\1\13\1\13\1\f\1\f\1\r\1\r\1\r"+
		"\1\16\1\16\3\16\b\16\1\17\1\17\3\17\b\17\1\20\1\20\1\20\3\20\b\20\3\20"+
		"\b\20\1\20\1\20\1\21\1\21\1\21\5\21\b\21\n\21\f\21\u013e\t\21\1\22\3\22"+
		"\b\22\1\22\3\22\b\22\1\22\1\22\1\23\1\23\1\23\1\23\1\23\5\23\b\23\n\23"+
		"\f\23\u0150\t\23\1\23\3\23\b\23\3\23\b\23\1\23\1\23\1\24\1\24\1\24\1\24"+
		"\3\24\b\24\1\25\1\25\1\26\1\26\1\27\1\27\1\27\1\27\1\27\1\27\1\30\1\30"+
		"\1\31\1\31\3\31\b\31\1\31\1\31\3\31\b\31\1\31\1\31\1\32\1\32\1\32\1\32"+
		"\5\32\b\32\n\32\f\32\u017c\t\32\1\32\3\32\b\32\3\32\b\32\1\32\1\32\1\33"+
		"\1\33\1\33\3\33\b\33\1\34\1\34\1\34\3\34\b\34\1\35\1\35\1\35\1\35\1\35"+
		"\1\35\5\35\b\35\n\35\f\35\u0198\t\35\1\35\3\35\b\35\3\35\b\35\1\35\3\35"+
		"\b\35\1\36\1\36\3\36\b\36\1\36\1\36\3\36\b\36\1\37\1\37\1\37\5\37\b\37"+
		"\n\37\f\37\u01b0\t\37\1 \1 \1 \5 \b \n \f \u01b8\t \1!\1!\1!\1!\1!\1!"+
		"\5!\b!\n!\f!\u01c3\t!\1!\3!\b!\3!\b!\1!\3!\b!\1\"\1\"\1\"\1#\1#\1#\1#"+
		"\1#\1#\5#\b#\n#\f#\u01d9\t#\1#\3#\b#\3#\b#\1#\3#\b#\1$\1$\1$\1$\3$\b$"+
		"\1$\1$\3$\b$\1%\1%\1%\1%\1&\1&\1&\1&\3&\b&\1\'\1\'\1(\1(\1(\1(\1(\3(\b"+
		"(\1)\1)\3)\b)\1)\3)\b)\1)\1)\1)\1*\1*\1+\1+\1+\1+\1+\1+\1+\3+\b+\1,\1"+
		",\1,\3,\b,\1-\1-\1-\1-\1-\3-\b-\1.\1.\1.\1.\3.\b.\1.\1.\1/\1/\1/\1/\1"+
		"\60\1\60\1\60\1\60\1\60\1\60\3\60\b\60\1\61\1\61\1\61\1\62\1\62\1\62\1"+
		"\62\1\62\1\62\1\62\1\62\1\62\3\62\b\62\1\63\1\63\1\63\3\63\b\63\3\63\b"+
		"\63\1\63\1\63\1\64\1\64\1\64\5\64\b\64\n\64\f\64\u0252\t\64\1\65\1\65"+
		"\1\65\3\65\b\65\1\65\1\65\1\66\1\66\3\66\b\66\1\67\1\67\18\18\19\19\3"+
		"9\b9\1:\1:\1:\1;\1;\1;\1;\1;\1;\1;\1;\3;\b;\1;\1;\1;\1;\3;\b;\1;\1;\1"+
		";\1;\1;\1;\1;\1;\3;\b;\1;\1;\1;\1;\1;\1;\3;\b;\1;\1;\1;\1;\1;\1;\1;\1"+
		";\3;\b;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\3;\b;\1"+
		";\1;\3;\b;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\1;\3;\b;\3;\b;\1;\5;\b;\n;\f"+
		";\u02c1\t;\1<\1<\3<\b<\1=\1=\1=\1=\1=\1>\1>\1>\1>\1>\1>\1>\1>\1>\1>\1"+
		">\1>\1>\1>\1>\3>\b>\1?\1?\1?\1?\1?\1?\3?\b?\1@\1@\1A\1A\1A\1A\1B\1B\1"+
		"C\1C\1D\1D\1D\1D\1E\1E\1F\1F\1F\1G\1G\1G\1G\1H\1H\1H\3H\bH\1I\1I\1I\1"+
		"I\3I\bI\1J\1J\1J\1J\1J\1J\1J\1J\3J\bJ\1K\1K\1K\1K\3K\bK\1K\1K\1K\1K\1"+
		"K\3K\bK\3K\bK\1L\1L\3L\bL\1M\1M\1M\1M\3M\bM\1M\3M\bM\1M\1M\5M\bM\nM\f"+
		"M\u0332\tM\1M\1M\1N\1N\1N\1N\1N\5N\bN\nN\fN\u033e\tN\1N\3N\bN\3N\bN\1"+
		"O\1O\1O\3O\bO\1P\1P\1P\1P\3P\bP\1P\1P\1P\5P\bP\nP\fP\u0356\tP\1P\1P\1"+
		"Q\1Q\3Q\bQ\1Q\1Q\1Q\1Q\1Q\1Q\1R\1R\1R\1R\1R\5R\bR\nR\fR\u036c\tR\1R\3"+
		"R\bR\3R\bR\1S\1S\1S\3S\bS\1T\1T\1T\5T\bT\nT\fT\u037e\tT\1U\1U\1U\1U\3"+
		"U\bU\1U\1U\1V\1V\1W\3W\bW\1W\1W\3W\bW\1W\1W\3W\bW\1X\1X\1Y\1Y\1Z\1Z\1"+
		"Z\3Z\bZ\1Z\1Z\3Z\bZ\1Z\1Z\1Z\1[\1[\1[\1\\\1\\\1\\\5\\\b\\\n\\\f\\\u03ae"+
		"\t\\\1\\\1\\\1]\1]\1]\1]\1]\5]\b]\n]\f]\u03ba\t]\1]\3]\b]\3]\b]\1^\1^"+
		"\1^\3^\b^\1^\3^\b^\1_\1_\1_\3_\b_\1_\1_\3_\b_\3_\b_\1_\1_\1`\1`\1a\1a"+
		"\3a\ba\1b\1b\3b\bb\1c\1c\3c\bc\1d\1d\1d\1e\1e\1f\1f\1f\1g\1g\1g\1g\3g"+
		"\bg\3g\bg\1g\1g\1h\1h\1h\3h\bh\1h\3h\bh\1i\1i\1i\3i\bi\1i\1i\1i\5i\bi"+
		"\ni\fi\u0409\ti\1i\1i\1i\5i\bi\ni\fi\u0411\ti\1j\1j\1j\1j\1k\1k\1l\1l"+
		"\1l\1l\1l\1l\5l\bl\nl\fl\u0422\tl\1l\3l\bl\3l\bl\1l\3l\bl\1m\1m\1m\1m"+
		"\1m\1m\1m\1m\1m\3m\bm\1n\1n\1no\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a"+
		"\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2"+
		"\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca"+
		"\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\0\1\1\63\64\u057a"+
		"\0\u00e4\1\0\0\0\1\u00e2\1\0\0\0\1\u00fa\1\0\0\0\1\u0110\1\0\0\0\1\u0122"+
		"\1\0\0\0\1\u012d\1\0\0\0\1\u0146\1\0\0\0\1\u0169\1\0\0\0\1\u01a4\1\0\0"+
		"\0\1\u01ce\1\0\0\0\1\u01e6\1\0\0\0\1\u02b2\1\0\0\0\1\u02c7\1\0\0\0\1\u037c"+
		"\1\0\0\0\1\u037b\1\0\0\0\1\u03f8\1\0\0\0\2\u00e6\1\0\0\0\3\u00e5\1\0\0"+
		"\0\3\u011b\1\0\0\0\3\u0161\1\0\0\0\3\u0233\1\0\0\0\3\u0230\1\0\0\0\3\u0241"+
		"\1\0\0\0\4\u00f0\1\0\0\0\5\u00e5\1\0\0\0\6\u00f2\1\0\0\0\7\u00f1\1\0\0"+
		"\0\7\u0241\1\0\0\0\b\u00f7\1\0\0\0\t\u00f4\1\0\0\0\n\u00f9\1\0\0\0\13"+
		"\u00f6\1\0\0\0\13\u00fe\1\0\0\0\13\u0167\1\0\0\0\13\u0173\1\0\0\0\13\u0241"+
		"\1\0\0\0\f\u00fb\1\0\0\0\r\u00f1\1\0\0\0\r\u0241\1\0\0\0\16\u00ff\1\0"+
		"\0\0\17\u00f1\1\0\0\0\17\u0241\1\0\0\0\20\u0112\1\0\0\0\21\u0102\1\0\0"+
		"\0\21\u010b\1\0\0\0\22\u0118\1\0\0\0\23\u0113\1\0\0\0\24\u011c\1\0\0\0"+
		"\25\u0116\1\0\0\0\26\u011e\1\0\0\0\27\u00f1\1\0\0\0\30\u0121\1\0\0\0\31"+
		"\u0120\1\0\0\0\32\u0123\1\0\0\0\33\u00f1\1\0\0\0\33\u0267\1\0\0\0\34\u0126"+
		"\1\0\0\0\35\u0125\1\0\0\0\35\u015a\1\0\0\0\35\u01f4\1\0\0\0\35\u01fd\1"+
		"\0\0\0\36\u012c\1\0\0\0\37\u0129\1\0\0\0 \u012e\1\0\0\0!\u0128\1\0\0\0"+
		"!\u012d\1\0\0\0\"\u0137\1\0\0\0#\u0131\1\0\0\0$\u0140\1\0\0\0%\u013c\1"+
		"\0\0\0%\u013b\1\0\0\0&\u0147\1\0\0\0\'\u00f1\1\0\0\0(\u015c\1\0\0\0)\u014e"+
		"\1\0\0\0)\u014d\1\0\0\0*\u015e\1\0\0\0+\u0159\1\0\0\0+\u01fb\1\0\0\0+"+
		"\u022b\1\0\0\0,\u0160\1\0\0\0-\u015d\1\0\0\0.\u0162\1\0\0\0/\u00f1\1\0"+
		"\0\0/\u0241\1\0\0\0\60\u0168\1\0\0\0\61\u0165\1\0\0\0\62\u0170\1\0\0\0"+
		"\63\u00f1\1\0\0\0\64\u0174\1\0\0\0\65\u01f7\1\0\0\0\65\u02db\1\0\0\0\65"+
		"\u031d\1\0\0\0\65\u031c\1\0\0\0\65\u0386\1\0\0\0\66\u0187\1\0\0\0\67\u018d"+
		"\1\0\0\0\67\u02db\1\0\0\08\u018c\1\0\0\09\u040b\1\0\0\0:\u018e\1\0\0\0"+
		";\u0188\1\0\0\0<\u01a1\1\0\0\0=\u01a0\1\0\0\0=\u0196\1\0\0\0=\u0195\1"+
		"\0\0\0>\u01a9\1\0\0\0?\u010f\1\0\0\0?\u0141\1\0\0\0?\u01a7\1\0\0\0?\u01ea"+
		"\1\0\0\0?\u01ed\1\0\0\0@\u01b1\1\0\0\0A\u01a8\1\0\0\0A\u01e7\1\0\0\0A"+
		"\u01eb\1\0\0\0A\u01ef\1\0\0\0A\u02c4\1\0\0\0A\u02f8\1\0\0\0A\u02fa\1\0"+
		"\0\0A\u0348\1\0\0\0A\u03da\1\0\0\0A\u03f9\1\0\0\0A\u03fc\1\0\0\0B\u01b9"+
		"\1\0\0\0C\u0188\1\0\0\0D\u01cc\1\0\0\0E\u01cb\1\0\0\0E\u01c1\1\0\0\0E"+
		"\u01c0\1\0\0\0F\u01cf\1\0\0\0G\u0188\1\0\0\0H\u01e2\1\0\0\0I\u01e1\1\0"+
		"\0\0I\u01d7\1\0\0\0I\u01d6\1\0\0\0J\u01ec\1\0\0\0K\u02e3\1\0\0\0L\u01f0"+
		"\1\0\0\0M\u018d\1\0\0\0N\u01f6\1\0\0\0O\u01f5\1\0\0\0O\u01fe\1\0\0\0O"+
		"\u0268\1\0\0\0P\u01f8\1\0\0\0Q\u018d\1\0\0\0R\u01ff\1\0\0\0S\u01fa\1\0"+
		"\0\0T\u0209\1\0\0\0U\u0207\1\0\0\0V\u0212\1\0\0\0W\u0278\1\0\0\0X\u0217"+
		"\1\0\0\0Y\u0213\1\0\0\0Z\u021e\1\0\0\0[\u0218\1\0\0\0\\\u0224\1\0\0\0"+
		"]\u00e7\1\0\0\0]\u0213\1\0\0\0^\u0228\1\0\0\0_\u0213\1\0\0\0`\u0232\1"+
		"\0\0\0a\u0229\1\0\0\0b\u0234\1\0\0\0c\u0218\1\0\0\0d\u0240\1\0\0\0e\u0235"+
		"\1\0\0\0f\u0242\1\0\0\0g\u0236\1\0\0\0g\u0265\1\0\0\0h\u024b\1\0\0\0i"+
		"\u0245\1\0\0\0j\u0256\1\0\0\0k\u0250\1\0\0\0k\u024f\1\0\0\0l\u025c\1\0"+
		"\0\0m\u0254\1\0\0\0n\u025e\1\0\0\0o\u025d\1\0\0\0p\u0260\1\0\0\0q\u025d"+
		"\1\0\0\0r\u0264\1\0\0\0s\u0259\1\0\0\0t\u0266\1\0\0\0u\u0218\1\0\0\0v"+
		"\u0277\1\0\0\0w\u00f8\1\0\0\0w\u01b6\1\0\0\0w\u01b5\1\0\0\0w\u0210\1\0"+
		"\0\0w\u0261\1\0\0\0w\u0265\1\0\0\0w\u0278\1\0\0\0w\u02be\1\0\0\0w\u02be"+
		"\1\0\0\0w\u02be\1\0\0\0w\u02be\1\0\0\0w\u02be\1\0\0\0w\u02a2\1\0\0\0w"+
		"\u02a8\1\0\0\0w\u02ac\1\0\0\0w\u02c9\1\0\0\0w\u02ed\1\0\0\0w\u02f1\1\0"+
		"\0\0w\u02f3\1\0\0\0w\u02f5\1\0\0\0w\u0317\1\0\0\0w\u032b\1\0\0\0w\u035e"+
		"\1\0\0\0w\u0388\1\0\0\0w\u039b\1\0\0\0w\u039c\1\0\0\0w\u03a3\1\0\0\0w"+
		"\u03a6\1\0\0\0w\u03cb\1\0\0\0w\u03cc\1\0\0\0w\u03d6\1\0\0\0w\u03ea\1\0"+
		"\0\0x\u02c2\1\0\0\0y\u02b8\1\0\0\0z\u02c6\1\0\0\0{\u0278\1\0\0\0|\u02da"+
		"\1\0\0\0}\u017a\1\0\0\0}\u0179\1\0\0\0}\u02e9\1\0\0\0}\u033c\1\0\0\0}"+
		"\u033b\1\0\0\0}\u036a\1\0\0\0}\u0369\1\0\0\0}\u03b8\1\0\0\0}\u03b7\1\0"+
		"\0\0~\u02e2\1\0\0\0\177\u02db\1\0\0\0\177\u0312\1\0\0\0\177\u0325\1\0"+
		"\0\0\177\u034b\1\0\0\0\177\u0395\1\0\0\0\177\u0397\1\0\0\0\u0080\u02e4"+
		"\1\0\0\0\u0081\u02e3\1\0\0\0\u0082\u02e6\1\0\0\0\u0083\u02db\1\0\0\0\u0084"+
		"\u02ea\1\0\0\0\u0085\u02e7\1\0\0\0\u0085\u03de\1\0\0\0\u0085\u03e2\1\0"+
		"\0\0\u0085\u03e5\1\0\0\0\u0086\u02ec\1\0\0\0\u0087\u02e3\1\0\0\0\u0088"+
		"\u02ee\1\0\0\0\u0089\u02e3\1\0\0\0\u0089\u03c4\1\0\0\0\u008a\u02f2\1\0"+
		"\0\0\u008b\u02ef\1\0\0\0\u008c\u02f4\1\0\0\0\u008d\u02e3\1\0\0\0\u008e"+
		"\u02f7\1\0\0\0\u008f\u02e3\1\0\0\0\u0090\u02fe\1\0\0\0\u0091\u02f9\1\0"+
		"\0\0\u0092\u0304\1\0\0\0\u0093\u02ff\1\0\0\0\u0094\u030e\1\0\0\0\u0095"+
		"\u02ff\1\0\0\0\u0096\u0310\1\0\0\0\u0097\u02db\1\0\0\0\u0097\u031c\1\0"+
		"\0\0\u0098\u0321\1\0\0\0\u0099\u02db\1\0\0\0\u009a\u0323\1\0\0\0\u009b"+
		"\u0322\1\0\0\0\u009c\u0335\1\0\0\0\u009d\u032f\1\0\0\0\u009e\u0347\1\0"+
		"\0\0\u009f\u0336\1\0\0\0\u00a0\u0349\1\0\0\0\u00a1\u0322\1\0\0\0\u00a2"+
		"\u035b\1\0\0\0\u00a3\u0350\1\0\0\0\u00a4\u0363\1\0\0\0\u00a5\u0353\1\0"+
		"\0\0\u00a6\u0375\1\0\0\0\u00a7\u0364\1\0\0\0\u00a8\u0377\1\0\0\0\u00a9"+
		"\u0376\1\0\0\0\u00aa\u037f\1\0\0\0\u00ab\u02db\1\0\0\0\u00ac\u0387\1\0"+
		"\0\0\u00ad\u0384\1\0\0\0\u00ad\u038f\1\0\0\0\u00ae\u038a\1\0\0\0\u00af"+
		"\u0384\1\0\0\0\u00b0\u0394\1\0\0\0\u00b1\u038b\1\0\0\0\u00b2\u0396\1\0"+
		"\0\0\u00b3\u0393\1\0\0\0\u00b4\u0398\1\0\0\0\u00b5\u0384\1\0\0\0\u00b6"+
		"\u03a4\1\0\0\0\u00b7\u02db\1\0\0\0\u00b8\u03a7\1\0\0\0\u00b9\u02db\1\0"+
		"\0\0\u00ba\u03b1\1\0\0\0\u00bb\u03ab\1\0\0\0\u00bc\u03c6\1\0\0\0\u00bd"+
		"\u03b2\1\0\0\0\u00be\u03d1\1\0\0\0\u00bf\u03c4\1\0\0\0\u00c0\u03d5\1\0"+
		"\0\0\u00c1\u03d4\1\0\0\0\u00c2\u03d7\1\0\0\0\u00c3\u02db\1\0\0\0\u00c4"+
		"\u03db\1\0\0\0\u00c5\u02db\1\0\0\0\u00c6\u03df\1\0\0\0\u00c7\u02db\1\0"+
		"\0\0\u00c8\u03e3\1\0\0\0\u00c9\u02db\1\0\0\0\u00ca\u03e6\1\0\0\0\u00cb"+
		"\u02db\1\0\0\0\u00cc\u03e8\1\0\0\0\u00cd\u02db\1\0\0\0\u00ce\u03eb\1\0"+
		"\0\0\u00cf\u0278\1\0\0\0\u00d0\u03fb\1\0\0\0\u00d1\u03ef\1\0\0\0\u00d2"+
		"\u0400\1\0\0\0\u00d3\u0438\5\uffff\0\0\u00d4\u0412\1\0\0\0\u00d5\u03fe"+
		"\1\0\0\0\u00d6\u0416\1\0\0\0\u00d7\u0222\1\0\0\0\u00d7\u0414\1\0\0\0\u00d7"+
		"\u042e\1\0\0\0\u00d8\u0418\1\0\0\0\u00d9\u0403\1\0\0\0\u00da\u0434\1\0"+
		"\0\0\u00db\u042a\1\0\0\0\u00db\u0420\1\0\0\0\u00db\u041f\1\0\0\0\u00dc"+
		"\u0436\1\0\0\0\u00dd\u0435\1\0\0\0\u00dd\u042f\1\0\0\0\u00dd\u0432\1\0"+
		"\0\0\u00de\u00e5\3\2\1\0\u00df\u00e5\3\4\2\0\u00e0\u00e1\5?\0\0\u00e1"+
		"\u00e2\3\0\0\0\u00e2\u00e3\5@\0\0\u00e3\u00e5\1\0\0\0\u00e4\u00de\1\0"+
		"\0\0\u00e4\u00df\1\0\0\0\u00e4\u00e0\1\0\0\0\u00e5\1\1\0\0\0\u00e6\u00e7"+
		"\3\\.\0\u00e7\3\1\0\0\0\u00e8\u00f1\3\6\3\0\u00e9\u00f1\3\16\7\0\u00ea"+
		"\u00f1\3\26\13\0\u00eb\u00f1\3\32\r\0\u00ec\u00f1\3&\23\0\u00ed\u00f1"+
		"\3\f\6\0\u00ee\u00f1\3.\27\0\u00ef\u00f1\3\62\31\0\u00f0\u00e8\1\0\0\0"+
		"\u00f0\u00e9\1\0\0\0\u00f0\u00ea\1\0\0\0\u00f0\u00eb\1\0\0\0\u00f0\u00ec"+
		"\1\0\0\0\u00f0\u00ed\1\0\0\0\u00f0\u00ee\1\0\0\0\u00f0\u00ef\1\0\0\0\u00f1"+
		"\5\1\0\0\0\u00f2\u00f3\5A\0\0\u00f3\u00f4\3\b\4\0\u00f4\u00f5\5B\0\0\u00f5"+
		"\u00f6\3\n\5\0\u00f6\7\1\0\0\0\u00f7\u00f8\3v;\0\u00f8\t\1\0\0\0\u00f9"+
		"\u00fa\3\0\0\0\u00fa\13\1\0\0\0\u00fb\u00fc\5A\0\0\u00fc\u00fd\5B\0\0"+
		"\u00fd\u00fe\3\n\5\0\u00fe\r\1\0\0\0\u00ff\u0100\5\26\0\0\u0100\u0106"+
		"\5C\0\0\u0101\u0102\3\20\b\0\u0102\u0103\5G\0\0\u0103\u0105\1\0\0\0\u0104"+
		"\u0101\1\0\0\0\u0105\u0108\1\0\0\0\u0106\u0104\1\0\0\0\u0106\u0107\1\0"+
		"\0\0\u0107\u010a\1\0\0\0\u0108\u0106\1\0\0\0\u0109\u010b\3\20\b\0\u010a"+
		"\u0109\1\0\0\0\u010a\u010b\1\0\0\0\u010b\u010c\1\0\0\0\u010c\u010d\5D"+
		"\0\0\u010d\17\1\0\0\0\u010e\u010f\3>\37\0\u010f\u0110\3\0\0\0\u0110\u0113"+
		"\1\0\0\0\u0111\u0113\3\22\t\0\u0112\u010e\1\0\0\0\u0112\u0111\1\0\0\0"+
		"\u0113\u0115\1\0\0\0\u0114\u0116\3\24\n\0\u0115\u0114\1\0\0\0\u0115\u0116"+
		"\1\0\0\0\u0116\21\1\0\0\0\u0117\u0119\5\34\0\0\u0118\u0117\1\0\0\0\u0118"+
		"\u0119\1\0\0\0\u0119\u011a\1\0\0\0\u011a\u011b\3\2\1\0\u011b\23\1\0\0"+
		"\0\u011c\u011d\5R\0\0\u011d\25\1\0\0\0\u011e\u011f\5\34\0\0\u011f\u0120"+
		"\3\30\f\0\u0120\27\1\0\0\0\u0121\u0122\3\0\0\0\u0122\31\1\0\0\0\u0123"+
		"\u0124\5\13\0\0\u0124\u0125\3\34\16\0\u0125\33\1\0\0\0\u0126\u0128\3 "+
		"\20\0\u0127\u0129\3\36\17\0\u0128\u0127\1\0\0\0\u0128\u0129\1\0\0\0\u0129"+
		"\35\1\0\0\0\u012a\u012d\3 \20\0\u012b\u012d\3\0\0\0\u012c\u012a\1\0\0"+
		"\0\u012c\u012b\1\0\0\0\u012d\37\1\0\0\0\u012e\u0133\5?\0\0\u012f\u0131"+
		"\3\"\21\0\u0130\u0132\5E\0\0\u0131\u0130\1\0\0\0\u0131\u0132\1\0\0\0\u0132"+
		"\u0134\1\0\0\0\u0133\u012f\1\0\0\0\u0133\u0134\1\0\0\0\u0134\u0135\1\0"+
		"\0\0\u0135\u0136\5@\0\0\u0136!\1\0\0\0\u0137\u013c\3$\22\0\u0138\u0139"+
		"\5E\0\0\u0139\u013b\3$\22\0\u013a\u0138\1\0\0\0\u013b\u013e\1\0\0\0\u013c"+
		"\u013a\1\0\0\0\u013c\u013d\1\0\0\0\u013d#\1\0\0\0\u013e\u013c\1\0\0\0"+
		"\u013f\u0141\3>\37\0\u0140\u013f\1\0\0\0\u0140\u0141\1\0\0\0\u0141\u0143"+
		"\1\0\0\0\u0142\u0144\5>\0\0\u0143\u0142\1\0\0\0\u0143\u0144\1\0\0\0\u0144"+
		"\u0145\1\0\0\0\u0145\u0146\3\0\0\0\u0146%\1\0\0\0\u0147\u0148\5\20\0\0"+
		"\u0148\u0154\5C\0\0\u0149\u014e\3(\24\0\u014a\u014b\5G\0\0\u014b\u014d"+
		"\3(\24\0\u014c\u014a\1\0\0\0\u014d\u0150\1\0\0\0\u014e\u014c\1\0\0\0\u014e"+
		"\u014f\1\0\0\0\u014f\u0152\1\0\0\0\u0150\u014e\1\0\0\0\u0151\u0153\5G"+
		"\0\0\u0152\u0151\1\0\0\0\u0152\u0153\1\0\0\0\u0153\u0155\1\0\0\0\u0154"+
		"\u0149\1\0\0\0\u0154\u0155\1\0\0\0\u0155\u0156\1\0\0\0\u0156\u0157\5D"+
		"\0\0\u0157\'\1\0\0\0\u0158\u0159\3*\25\0\u0159\u015a\3\34\16\0\u015a\u015d"+
		"\1\0\0\0\u015b\u015d\3,\26\0\u015c\u0158\1\0\0\0\u015c\u015b\1\0\0\0\u015d"+
		")\1\0\0\0\u015e\u015f\5I\0\0\u015f+\1\0\0\0\u0160\u0161\3\2\1\0\u0161"+
		"-\1\0\0\0\u0162\u0163\5\21\0\0\u0163\u0164\5A\0\0\u0164\u0165\3\60\30"+
		"\0\u0165\u0166\5B\0\0\u0166\u0167\3\n\5\0\u0167/\1\0\0\0\u0168\u0169\3"+
		"\0\0\0\u0169\61\1\0\0\0\u016a\u016c\5\3\0\0\u016b\u016d\5\62\0\0\u016c"+
		"\u016b\1\0\0\0\u016c\u016d\1\0\0\0\u016d\u0171\1\0\0\0\u016e\u016f\5\62"+
		"\0\0\u016f\u0171\5\3\0\0\u0170\u016a\1\0\0\0\u0170\u016e\1\0\0\0\u0171"+
		"\u0172\1\0\0\0\u0172\u0173\3\n\5\0\u0173\63\1\0\0\0\u0174\u0180\5C\0\0"+
		"\u0175\u017a\3|>\0\u0176\u0177\5G\0\0\u0177\u0179\3|>\0\u0178\u0176\1"+
		"\0\0\0\u0179\u017c\1\0\0\0\u017a\u0178\1\0\0\0\u017a\u017b\1\0\0\0\u017b"+
		"\u017e\1\0\0\0\u017c\u017a\1\0\0\0\u017d\u017f\5G\0\0\u017e\u017d\1\0"+
		"\0\0\u017e\u017f\1\0\0\0\u017f\u0181\1\0\0\0\u0180\u0175\1\0\0\0\u0180"+
		"\u0181\1\0\0\0\u0181\u0182\1\0\0\0\u0182\u0183\5D\0\0\u0183\65\1\0\0\0"+
		"\u0184\u0188\3:\35\0\u0185\u0188\3B!\0\u0186\u0188\3F#\0\u0187\u0184\1"+
		"\0\0\0\u0187\u0185\1\0\0\0\u0187\u0186\1\0\0\0\u0188\67\1\0\0\0\u0189"+
		"\u018d\3\66\33\0\u018a\u018d\3L&\0\u018b\u018d\3P(\0\u018c\u0189\1\0\0"+
		"\0\u018c\u018a\1\0\0\0\u018c\u018b\1\0\0\0\u018d9\1\0\0\0\u018e\u019f"+
		"\5\4\0\0\u018f\u01a0\3<\36\0\u0190\u019c\5?\0\0\u0191\u0196\3<\36\0\u0192"+
		"\u0193\5G\0\0\u0193\u0195\3<\36\0\u0194\u0192\1\0\0\0\u0195\u0198\1\0"+
		"\0\0\u0196\u0194\1\0\0\0\u0196\u0197\1\0\0\0\u0197\u019a\1\0\0\0\u0198"+
		"\u0196\1\0\0\0\u0199\u019b\5G\0\0\u019a\u0199\1\0\0\0\u019a\u019b\1\0"+
		"\0\0\u019b\u019d\1\0\0\0\u019c\u0191\1\0\0\0\u019c\u019d\1\0\0\0\u019d"+
		"\u019e\1\0\0\0\u019e\u01a0\5@\0\0\u019f\u018f\1\0\0\0\u019f\u0190\1\0"+
		"\0\0\u01a0;\1\0\0\0\u01a1\u01a7\3>\37\0\u01a2\u01a4\3\0\0\0\u01a3\u01a2"+
		"\1\0\0\0\u01a3\u01a4\1\0\0\0\u01a4\u01a5\1\0\0\0\u01a5\u01a6\58\0\0\u01a6"+
		"\u01a8\3@ \0\u01a7\u01a3\1\0\0\0\u01a7\u01a8\1\0\0\0\u01a8=\1\0\0\0\u01a9"+
		"\u01ae\5I\0\0\u01aa\u01ab\5E\0\0\u01ab\u01ad\5I\0\0\u01ac\u01aa\1\0\0"+
		"\0\u01ad\u01b0\1\0\0\0\u01ae\u01ac\1\0\0\0\u01ae\u01af\1\0\0\0\u01af?"+
		"\1\0\0\0\u01b0\u01ae\1\0\0\0\u01b1\u01b6\3v;\0\u01b2\u01b3\5E\0\0\u01b3"+
		"\u01b5\3v;\0\u01b4\u01b2\1\0\0\0\u01b5\u01b8\1\0\0\0\u01b6\u01b4\1\0\0"+
		"\0\u01b6\u01b7\1\0\0\0\u01b7A\1\0\0\0\u01b8\u01b6\1\0\0\0\u01b9\u01ca"+
		"\5\30\0\0\u01ba\u01cb\3D\"\0\u01bb\u01c7\5?\0\0\u01bc\u01c1\3D\"\0\u01bd"+
		"\u01be\5G\0\0\u01be\u01c0\3D\"\0\u01bf\u01bd\1\0\0\0\u01c0\u01c3\1\0\0"+
		"\0\u01c1\u01bf\1\0\0\0\u01c1\u01c2\1\0\0\0\u01c2\u01c5\1\0\0\0\u01c3\u01c1"+
		"\1\0\0\0\u01c4\u01c6\5G\0\0\u01c5\u01c4\1\0\0\0\u01c5\u01c6\1\0\0\0\u01c6"+
		"\u01c8\1\0\0\0\u01c7\u01bc\1\0\0\0\u01c7\u01c8\1\0\0\0\u01c8\u01c9\1\0"+
		"\0\0\u01c9\u01cb\5@\0\0\u01ca\u01ba\1\0\0\0\u01ca\u01bb\1\0\0\0\u01cb"+
		"C\1\0\0\0\u01cc\u01cd\5I\0\0\u01cd\u01ce\3\0\0\0\u01ceE\1\0\0\0\u01cf"+
		"\u01e0\5\31\0\0\u01d0\u01e1\3H$\0\u01d1\u01dd\5?\0\0\u01d2\u01d7\3H$\0"+
		"\u01d3\u01d4\5G\0\0\u01d4\u01d6\3H$\0\u01d5\u01d3\1\0\0\0\u01d6\u01d9"+
		"\1\0\0\0\u01d7\u01d5\1\0\0\0\u01d7\u01d8\1\0\0\0\u01d8\u01db\1\0\0\0\u01d9"+
		"\u01d7\1\0\0\0\u01da\u01dc\5G\0\0\u01db\u01da\1\0\0\0\u01db\u01dc\1\0"+
		"\0\0\u01dc\u01de\1\0\0\0\u01dd\u01d2\1\0\0\0\u01dd\u01de\1\0\0\0\u01de"+
		"\u01df\1\0\0\0\u01df\u01e1\5@\0\0\u01e0\u01d0\1\0\0\0\u01e0\u01d1\1\0"+
		"\0\0\u01e1G\1\0\0\0\u01e2\u01ea\3>\37\0\u01e3\u01e6\3\0\0\0\u01e4\u01e5"+
		"\58\0\0\u01e5\u01e7\3@ \0\u01e6\u01e4\1\0\0\0\u01e6\u01e7\1\0\0\0\u01e7"+
		"\u01eb\1\0\0\0\u01e8\u01e9\58\0\0\u01e9\u01eb\3@ \0\u01ea\u01e3\1\0\0"+
		"\0\u01ea\u01e8\1\0\0\0\u01ebI\1\0\0\0\u01ec\u01ed\3>\37\0\u01ed\u01ee"+
		"\5=\0\0\u01ee\u01ef\3@ \0\u01efK\1\0\0\0\u01f0\u01f1\5\13\0\0\u01f1\u01f2"+
		"\5I\0\0\u01f2\u01f4\3\34\16\0\u01f3\u01f5\3N\'\0\u01f4\u01f3\1\0\0\0\u01f4"+
		"\u01f5\1\0\0\0\u01f5M\1\0\0\0\u01f6\u01f7\3\64\32\0\u01f7O\1\0\0\0\u01f8"+
		"\u01f9\5\13\0\0\u01f9\u01fa\3R)\0\u01fa\u01fb\3*\25\0\u01fb\u01fd\3\34"+
		"\16\0\u01fc\u01fe\3N\'\0\u01fd\u01fc\1\0\0\0\u01fd\u01fe\1\0\0\0\u01fe"+
		"Q\1\0\0\0\u01ff\u0201\5?\0\0\u0200\u0202\5I\0\0\u0201\u0200\1\0\0\0\u0201"+
		"\u0202\1\0\0\0\u0202\u0204\1\0\0\0\u0203\u0205\5\34\0\0\u0204\u0203\1"+
		"\0\0\0\u0204\u0205\1\0\0\0\u0205\u0206\1\0\0\0\u0206\u0207\3T*\0\u0207"+
		"\u0208\5@\0\0\u0208S\1\0\0\0\u0209\u020a\5I\0\0\u020aU\1\0\0\0\u020b\u0213"+
		"\3X,\0\u020c\u0213\3\\.\0\u020d\u0213\3^/\0\u020e\u020f\5?\0\0\u020f\u0210"+
		"\3v;\0\u0210\u0211\5@\0\0\u0211\u0213\1\0\0\0\u0212\u020b\1\0\0\0\u0212"+
		"\u020c\1\0\0\0\u0212\u020d\1\0\0\0\u0212\u020e\1\0\0\0\u0213W\1\0\0\0"+
		"\u0214\u0218\3Z-\0\u0215\u0218\3b\61\0\u0216\u0218\3t:\0\u0217\u0214\1"+
		"\0\0\0\u0217\u0215\1\0\0\0\u0217\u0216\1\0\0\0\u0218Y\1\0\0\0\u0219\u021f"+
		"\5N\0\0\u021a\u021f\5P\0\0\u021b\u021f\5O\0\0\u021c\u021f\5Q\0\0\u021d"+
		"\u021f\5R\0\0\u021e\u0219\1\0\0\0\u021e\u021a\1\0\0\0\u021e\u021b\1\0"+
		"\0\0\u021e\u021c\1\0\0\0\u021e\u021d\1\0\0\0\u021f[\1\0\0\0\u0220\u0221"+
		"\4.\0\0\u0221\u0222\3\u00d6k\0\u0222\u0223\5F\0\0\u0223\u0225\1\0\0\0"+
		"\u0224\u0220\1\0\0\0\u0224\u0225\1\0\0\0\u0225\u0226\1\0\0\0\u0226\u0227"+
		"\5I\0\0\u0227]\1\0\0\0\u0228\u0229\3`\60\0\u0229\u022a\5F\0\0\u022a\u022b"+
		"\3*\25\0\u022b_\1\0\0\0\u022c\u0233\3\2\1\0\u022d\u022e\5?\0\0\u022e\u022f"+
		"\5\34\0\0\u022f\u0230\3\2\1\0\u0230\u0231\5@\0\0\u0231\u0233\1\0\0\0\u0232"+
		"\u022c\1\0\0\0\u0232\u022d\1\0\0\0\u0233a\1\0\0\0\u0234\u0235\3d\62\0"+
		"\u0235\u0236\3f\63\0\u0236c\1\0\0\0\u0237\u0241\3\16\7\0\u0238\u0241\3"+
		"\6\3\0\u0239\u023a\5A\0\0\u023a\u023b\5>\0\0\u023b\u023c\5B\0\0\u023c"+
		"\u0241\3\n\5\0\u023d\u0241\3\f\6\0\u023e\u0241\3.\27\0\u023f\u0241\3\2"+
		"\1\0\u0240\u0237\1\0\0\0\u0240\u0238\1\0\0\0\u0240\u0239\1\0\0\0\u0240"+
		"\u023d\1\0\0\0\u0240\u023e\1\0\0\0\u0240\u023f\1\0\0\0\u0241e\1\0\0\0"+
		"\u0242\u0247\5C\0\0\u0243\u0245\3h\64\0\u0244\u0246\5E\0\0\u0245\u0244"+
		"\1\0\0\0\u0245\u0246\1\0\0\0\u0246\u0248\1\0\0\0\u0247\u0243\1\0\0\0\u0247"+
		"\u0248\1\0\0\0\u0248\u0249\1\0\0\0\u0249\u024a\5D\0\0\u024ag\1\0\0\0\u024b"+
		"\u0250\3j\65\0\u024c\u024d\5E\0\0\u024d\u024f\3j\65\0\u024e\u024c\1\0"+
		"\0\0\u024f\u0252\1\0\0\0\u0250\u024e\1\0\0\0\u0250\u0251\1\0\0\0\u0251"+
		"i\1\0\0\0\u0252\u0250\1\0\0\0\u0253\u0254\3l\66\0\u0254\u0255\5H\0\0\u0255"+
		"\u0257\1\0\0\0\u0256\u0253\1\0\0\0\u0256\u0257\1\0\0\0\u0257\u0258\1\0"+
		"\0\0\u0258\u0259\3r9\0\u0259k\1\0\0\0\u025a\u025d\3n\67\0\u025b\u025d"+
		"\3p8\0\u025c\u025a\1\0\0\0\u025c\u025b\1\0\0\0\u025dm\1\0\0\0\u025e\u025f"+
		"\5I\0\0\u025fo\1\0\0\0\u0260\u0261\3v;\0\u0261q\1\0\0\0\u0262\u0265\3"+
		"v;\0\u0263\u0265\3f\63\0\u0264\u0262\1\0\0\0\u0264\u0263\1\0\0\0\u0265"+
		"s\1\0\0\0\u0266\u0267\3\32\r\0\u0267\u0268\3N\'\0\u0268u\1\0\0\0\u0269"+
		"\u0271\6;\uffff\0\u026a\u0272\5\32\0\0\u026b\u0272\5\33\0\0\u026c\u0272"+
		"\59\0\0\u026d\u0272\5!\0\0\u026e\u0272\5\34\0\0\u026f\u0272\5\37\0\0\u0270"+
		"\u0272\5\62\0\0\u0271\u026a\1\0\0\0\u0271\u026b\1\0\0\0\u0271\u026c\1"+
		"\0\0\0\u0271\u026d\1\0\0\0\u0271\u026e\1\0\0\0\u0271\u026f\1\0\0\0\u0271"+
		"\u0270\1\0\0\0\u0272\u0273\1\0\0\0\u0273\u0278\3v;\0\u0274\u0278\3V+\0"+
		"\u0275\u0278\3z=\0\u0276\u0278\3\u00ceg\0\u0277\u0269\1\0\0\0\u0277\u0274"+
		"\1\0\0\0\u0277\u0275\1\0\0\0\u0277\u0276\1\0\0\0\u0278\u02bf\1\0\0\0\u0279"+
		"\u0281\4;\1\1\u027a\u0282\5\34\0\0\u027b\u0282\5\35\0\0\u027c\u0282\5"+
		"\36\0\0\u027d\u0282\5\"\0\0\u027e\u0282\5#\0\0\u027f\u0282\5\37\0\0\u0280"+
		"\u0282\5$\0\0\u0281\u027a\1\0\0\0\u0281\u027b\1\0\0\0\u0281\u027c\1\0"+
		"\0\0\u0281\u027d\1\0\0\0\u0281\u027e\1\0\0\0\u0281\u027f\1\0\0\0\u0281"+
		"\u0280\1\0\0\0\u0282\u0283\1\0\0\0\u0283\u02be\3v;\0\u0284\u0289\4;\2"+
		"\1\u0285\u028a\5\32\0\0\u0286\u028a\5\33\0\0\u0287\u028a\5 \0\0\u0288"+
		"\u028a\5!\0\0\u0289\u0285\1\0\0\0\u0289\u0286\1\0\0\0\u0289\u0287\1\0"+
		"\0\0\u0289\u0288\1\0\0\0\u028a\u028b\1\0\0\0\u028b\u02be\3v;\0\u028c\u0293"+
		"\4;\3\1\u028d\u0294\5\65\0\0\u028e\u0294\5:\0\0\u028f\u0294\5\66\0\0\u0290"+
		"\u0294\5;\0\0\u0291\u0294\5\67\0\0\u0292\u0294\5<\0\0\u0293\u028d\1\0"+
		"\0\0\u0293\u028e\1\0\0\0\u0293\u028f\1\0\0\0\u0293\u0290\1\0\0\0\u0293"+
		"\u0291\1\0\0\0\u0293\u0292\1\0\0\0\u0294\u0295\1\0\0\0\u0295\u02be\3v"+
		";\0\u0296\u0297\4;\4\1\u0297\u0298\5\60\0\0\u0298\u02be\3v;\0\u0299\u029a"+
		"\4;\5\1\u029a\u029b\5\61\0\0\u029b\u02be\3v;\0\u029c\u029d\4;\6\1\u029d"+
		"\u029e\5F\0\0\u029e\u02be\5I\0\0\u029f\u02a0\4;\7\1\u02a0\u02a1\5A\0\0"+
		"\u02a1\u02a2\3v;\0\u02a2\u02a3\5B\0\0\u02a3\u02be\1\0\0\0\u02a4\u02a5"+
		"\4;\b\1\u02a5\u02a7\5A\0\0\u02a6\u02a8\3v;\0\u02a7\u02a6\1\0\0\0\u02a7"+
		"\u02a8\1\0\0\0\u02a8\u02a9\1\0\0\0\u02a9\u02ab\5H\0\0\u02aa\u02ac\3v;"+
		"\0\u02ab\u02aa\1\0\0\0\u02ab\u02ac\1\0\0\0\u02ac\u02ad\1\0\0\0\u02ad\u02be"+
		"\5B\0\0\u02ae\u02af\4;\t\1\u02af\u02b0\5F\0\0\u02b0\u02b1\5?\0\0\u02b1"+
		"\u02b2\3\0\0\0\u02b2\u02b3\5@\0\0\u02b3\u02be\1\0\0\0\u02b4\u02b5\4;\n"+
		"\1\u02b5\u02ba\5?\0\0\u02b6\u02b8\3x<\0\u02b7\u02b9\5E\0\0\u02b8\u02b7"+
		"\1\0\0\0\u02b8\u02b9\1\0\0\0\u02b9\u02bb\1\0\0\0\u02ba\u02b6\1\0\0\0\u02ba"+
		"\u02bb\1\0\0\0\u02bb\u02bc\1\0\0\0\u02bc\u02be\5@\0\0\u02bd\u0279\1\0"+
		"\0\0\u02bd\u0284\1\0\0\0\u02bd\u028c\1\0\0\0\u02bd\u0296\1\0\0\0\u02bd"+
		"\u0299\1\0\0\0\u02bd\u029c\1\0\0\0\u02bd\u029f\1\0\0\0\u02bd\u02a4\1\0"+
		"\0\0\u02bd\u02ae\1\0\0\0\u02bd\u02b4\1\0\0\0\u02be\u02c1\1\0\0\0\u02bf"+
		"\u02bd\1\0\0\0\u02bf\u02c0\1\0\0\0\u02c0w\1\0\0\0\u02c1\u02bf\1\0\0\0"+
		"\u02c2\u02c4\3@ \0\u02c3\u02c5\5>\0\0\u02c4\u02c3\1\0\0\0\u02c4\u02c5"+
		"\1\0\0\0\u02c5y\1\0\0\0\u02c6\u02c7\3\0\0\0\u02c7\u02c8\5?\0\0\u02c8\u02c9"+
		"\3v;\0\u02c9\u02ca\5@\0\0\u02ca{\1\0\0\0\u02cb\u02db\3\66\33\0\u02cc\u02db"+
		"\3\u0082A\0\u02cd\u02db\3~?\0\u02ce\u02db\3\u00b6[\0\u02cf\u02db\3\u00c2"+
		"a\0\u02d0\u02db\3\u00c4b\0\u02d1\u02db\3\u00c6c\0\u02d2\u02db\3\u00c8"+
		"d\0\u02d3\u02db\3\u00cae\0\u02d4\u02db\3\64\32\0\u02d5\u02db\3\u0096K"+
		"\0\u02d6\u02db\3\u0098L\0\u02d7\u02db\3\u00b8\\\0\u02d8\u02db\3\u00aa"+
		"U\0\u02d9\u02db\3\u00ccf\0\u02da\u02cb\1\0\0\0\u02da\u02cc\1\0\0\0\u02da"+
		"\u02cd\1\0\0\0\u02da\u02ce\1\0\0\0\u02da\u02cf\1\0\0\0\u02da\u02d0\1\0"+
		"\0\0\u02da\u02d1\1\0\0\0\u02da\u02d2\1\0\0\0\u02da\u02d3\1\0\0\0\u02da"+
		"\u02d4\1\0\0\0\u02da\u02d5\1\0\0\0\u02da\u02d6\1\0\0\0\u02da\u02d7\1\0"+
		"\0\0\u02da\u02d8\1\0\0\0\u02da\u02d9\1\0\0\0\u02db}\1\0\0\0\u02dc\u02e3"+
		"\3\u0080@\0\u02dd\u02e3\3\u0086C\0\u02de\u02e3\3\u0088D\0\u02df\u02e3"+
		"\3\u008cF\0\u02e0\u02e3\3\u008eG\0\u02e1\u02e3\3J%\0\u02e2\u02dc\1\0\0"+
		"\0\u02e2\u02dd\1\0\0\0\u02e2\u02de\1\0\0\0\u02e2\u02df\1\0\0\0\u02e2\u02e0"+
		"\1\0\0\0\u02e2\u02e1\1\0\0\0\u02e3\177\1\0\0\0\u02e4\u02e5\1\0\0\0\u02e5"+
		"\u0081\1\0\0\0\u02e6\u02e7\3\u0084B\0\u02e7\u02e8\5H\0\0\u02e8\u02e9\3"+
		"|>\0\u02e9\u0083\1\0\0\0\u02ea\u02eb\5I\0\0\u02eb\u0085\1\0\0\0\u02ec"+
		"\u02ed\3v;\0\u02ed\u0087\1\0\0\0\u02ee\u02ef\3\u008aE\0\u02ef\u02f0\5"+
		"\62\0\0\u02f0\u02f1\3v;\0\u02f1\u0089\1\0\0\0\u02f2\u02f3\3v;\0\u02f3"+
		"\u008b\1\0\0\0\u02f4\u02f5\3v;\0\u02f5\u02f6\7\0\0\0\u02f6\u008d\1\0\0"+
		"\0\u02f7\u02f8\3@ \0\u02f8\u02f9\3\u0090H\0\u02f9\u02fa\3@ \0\u02fa\u008f"+
		"\1\0\0\0\u02fb\u02ff\3\u0092I\0\u02fc\u02ff\3\u0094J\0\u02fd\u02ff\58"+
		"\0\0\u02fe\u02fb\1\0\0\0\u02fe\u02fc\1\0\0\0\u02fe\u02fd\1\0\0\0\u02ff"+
		"\u0091\1\0\0\0\u0300\u0305\5%\0\0\u0301\u0305\5&\0\0\u0302\u0305\5+\0"+
		"\0\u0303\u0305\5,\0\0\u0304\u0300\1\0\0\0\u0304\u0301\1\0\0\0\u0304\u0302"+
		"\1\0\0\0\u0304\u0303\1\0\0\0\u0305\u0093\1\0\0\0\u0306\u030f\5\'\0\0\u0307"+
		"\u030f\5(\0\0\u0308\u030f\5,\0\0\u0309\u030f\5-\0\0\u030a\u030f\5.\0\0"+
		"\u030b\u030f\5*\0\0\u030c\u030f\5/\0\0\u030d\u030f\5)\0\0\u030e\u0306"+
		"\1\0\0\0\u030e\u0307\1\0\0\0\u030e\u0308\1\0\0\0\u030e\u0309\1\0\0\0\u030e"+
		"\u030a\1\0\0\0\u030e\u030b\1\0\0\0\u030e\u030c\1\0\0\0\u030e\u030d\1\0"+
		"\0\0\u030f\u0095\1\0\0\0\u0310\u0314\5\16\0\0\u0311\u0312\3~?\0\u0312"+
		"\u0313\5G\0\0\u0313\u0315\1\0\0\0\u0314\u0311\1\0\0\0\u0314\u0315\1\0"+
		"\0\0\u0315\u0316\1\0\0\0\u0316\u0317\3v;\0\u0317\u031d\3\64\32\0\u0318"+
		"\u031b\5\b\0\0\u0319\u031c\3\u0096K\0\u031a\u031c\3\64\32\0\u031b\u0319"+
		"\1\0\0\0\u031b\u031a\1\0\0\0\u031c\u031e\1\0\0\0\u031d\u0318\1\0\0\0\u031d"+
		"\u031e\1\0\0\0\u031e\u0097\1\0\0\0\u031f\u0322\3\u009aM\0\u0320\u0322"+
		"\3\u00a0P\0\u0321\u031f\1\0\0\0\u0321\u0320\1\0\0\0\u0322\u0099\1\0\0"+
		"\0\u0323\u0327\5\27\0\0\u0324\u0325\3~?\0\u0325\u0326\5G\0\0\u0326\u0328"+
		"\1\0\0\0\u0327\u0324\1\0\0\0\u0327\u0328\1\0\0\0\u0328\u032a\1\0\0\0\u0329"+
		"\u032b\3v;\0\u032a\u0329\1\0\0\0\u032a\u032b\1\0\0\0\u032b\u032c\1\0\0"+
		"\0\u032c\u0330\5C\0\0\u032d\u032f\3\u009cN\0\u032e\u032d\1\0\0\0\u032f"+
		"\u0332\1\0\0\0\u0330\u032e\1\0\0\0\u0330\u0331\1\0\0\0\u0331\u0333\1\0"+
		"\0\0\u0332\u0330\1\0\0\0\u0333\u0334\5D\0\0\u0334\u009b\1\0\0\0\u0335"+
		"\u0336\3\u009eO\0\u0336\u0342\5H\0\0\u0337\u033c\3|>\0\u0338\u0339\5G"+
		"\0\0\u0339\u033b\3|>\0\u033a\u0338\1\0\0\0\u033b\u033e\1\0\0\0\u033c\u033a"+
		"\1\0\0\0\u033c\u033d\1\0\0\0\u033d\u0340\1\0\0\0\u033e\u033c\1\0\0\0\u033f"+
		"\u0341\5G\0\0\u0340\u033f\1\0\0\0\u0340\u0341\1\0\0\0\u0341\u0343\1\0"+
		"\0\0\u0342\u0337\1\0\0\0\u0342\u0343\1\0\0\0\u0343\u009d\1\0\0\0\u0344"+
		"\u0345\5\2\0\0\u0345\u0348\3@ \0\u0346\u0348\5\6\0\0\u0347\u0344\1\0\0"+
		"\0\u0347\u0346\1\0\0\0\u0348\u009f\1\0\0\0\u0349\u034d\5\27\0\0\u034a"+
		"\u034b\3~?\0\u034b\u034c\5G\0\0\u034c\u034e\1\0\0\0\u034d\u034a\1\0\0"+
		"\0\u034d\u034e\1\0\0\0\u034e\u034f\1\0\0\0\u034f\u0350\3\u00a2Q\0\u0350"+
		"\u0354\5C\0\0\u0351\u0353\3\u00a4R\0\u0352\u0351\1\0\0\0\u0353\u0356\1"+
		"\0\0\0\u0354\u0352\1\0\0\0\u0354\u0355\1\0\0\0\u0355\u0357\1\0\0\0\u0356"+
		"\u0354\1\0\0\0\u0357\u0358\5D\0\0\u0358\u00a1\1\0\0\0\u0359\u035a\5I\0"+
		"\0\u035a\u035c\5=\0\0\u035b\u0359\1\0\0\0\u035b\u035c\1\0\0\0\u035c\u035d"+
		"\1\0\0\0\u035d\u035e\3v;\0\u035e\u035f\5F\0\0\u035f\u0360\5?\0\0\u0360"+
		"\u0361\5\30\0\0\u0361\u0362\5@\0\0\u0362\u00a3\1\0\0\0\u0363\u0364\3\u00a6"+
		"S\0\u0364\u0370\5H\0\0\u0365\u036a\3|>\0\u0366\u0367\5G\0\0\u0367\u0369"+
		"\3|>\0\u0368\u0366\1\0\0\0\u0369\u036c\1\0\0\0\u036a\u0368\1\0\0\0\u036a"+
		"\u036b\1\0\0\0\u036b\u036e\1\0\0\0\u036c\u036a\1\0\0\0\u036d\u036f\5G"+
		"\0\0\u036e\u036d\1\0\0\0\u036e\u036f\1\0\0\0\u036f\u0371\1\0\0\0\u0370"+
		"\u0365\1\0\0\0\u0370\u0371\1\0\0\0\u0371\u00a5\1\0\0\0\u0372\u0373\5\2"+
		"\0\0\u0373\u0376\3\u00a8T\0\u0374\u0376\5\6\0\0\u0375\u0372\1\0\0\0\u0375"+
		"\u0374\1\0\0\0\u0376\u00a7\1\0\0\0\u0377\u037c\3\0\0\0\u0378\u0379\5E"+
		"\0\0\u0379\u037b\3\0\0\0\u037a\u0378\1\0\0\0\u037b\u037e\1\0\0\0\u037c"+
		"\u037a\1\0\0\0\u037c\u037d\1\0\0\0\u037d\u00a9\1\0\0\0\u037e\u037c\1\0"+
		"\0\0\u037f\u0383\5\n\0\0\u0380\u0384\3\u00acV\0\u0381\u0384\3\u00aeW\0"+
		"\u0382\u0384\3\u00b4Z\0\u0383\u0380\1\0\0\0\u0383\u0381\1\0\0\0\u0383"+
		"\u0382\1\0\0\0\u0383\u0384\1\0\0\0\u0384\u0385\1\0\0\0\u0385\u0386\3\64"+
		"\32\0\u0386\u00ab\1\0\0\0\u0387\u0388\3v;\0\u0388\u00ad\1\0\0\0\u0389"+
		"\u038b\3\u00b0X\0\u038a\u0389\1\0\0\0\u038a\u038b\1\0\0\0\u038b\u038c"+
		"\1\0\0\0\u038c\u038e\5G\0\0\u038d\u038f\3\u00acV\0\u038e\u038d\1\0\0\0"+
		"\u038e\u038f\1\0\0\0\u038f\u0390\1\0\0\0\u0390\u0392\5G\0\0\u0391\u0393"+
		"\3\u00b2Y\0\u0392\u0391\1\0\0\0\u0392\u0393\1\0\0\0\u0393\u00af\1\0\0"+
		"\0\u0394\u0395\3~?\0\u0395\u00b1\1\0\0\0\u0396\u0397\3~?\0\u0397\u00b3"+
		"\1\0\0\0\u0398\u039b\3v;\0\u0399\u039a\5E\0\0\u039a\u039c\3v;\0\u039b"+
		"\u0399\1\0\0\0\u039b\u039c\1\0\0\0\u039c\u039f\1\0\0\0\u039d\u03a0\58"+
		"\0\0\u039e\u03a0\5=\0\0\u039f\u039d\1\0\0\0\u039f\u039e\1\0\0\0\u03a0"+
		"\u03a1\1\0\0\0\u03a1\u03a2\5\23\0\0\u03a2\u03a3\3v;\0\u03a3\u00b5\1\0"+
		"\0\0\u03a4\u03a5\5\f\0\0\u03a5\u03a6\3v;\0\u03a6\u00b7\1\0\0\0\u03a7\u03a8"+
		"\5\25\0\0\u03a8\u03ac\5C\0\0\u03a9\u03ab\3\u00ba]\0\u03aa\u03a9\1\0\0"+
		"\0\u03ab\u03ae\1\0\0\0\u03ac\u03aa\1\0\0\0\u03ac\u03ad\1\0\0\0\u03ad\u03af"+
		"\1\0\0\0\u03ae\u03ac\1\0\0\0\u03af\u03b0\5D\0\0\u03b0\u00b9\1\0\0\0\u03b1"+
		"\u03b2\3\u00bc^\0\u03b2\u03be\5H\0\0\u03b3\u03b8\3|>\0\u03b4\u03b5\5G"+
		"\0\0\u03b5\u03b7\3|>\0\u03b6\u03b4\1\0\0\0\u03b7\u03ba\1\0\0\0\u03b8\u03b6"+
		"\1\0\0\0\u03b8\u03b9\1\0\0\0\u03b9\u03bc\1\0\0\0\u03ba\u03b8\1\0\0\0\u03bb"+
		"\u03bd\5G\0\0\u03bc\u03bb\1\0\0\0\u03bc\u03bd\1\0\0\0\u03bd\u03bf\1\0"+
		"\0\0\u03be\u03b3\1\0\0\0\u03be\u03bf\1\0\0\0\u03bf\u00bb\1\0\0\0\u03c0"+
		"\u03c3\5\2\0\0\u03c1\u03c4\3\u0088D\0\u03c2\u03c4\3\u00be_\0\u03c3\u03c1"+
		"\1\0\0\0\u03c3\u03c2\1\0\0\0\u03c4\u03c7\1\0\0\0\u03c5\u03c7\5\6\0\0\u03c6"+
		"\u03c0\1\0\0\0\u03c6\u03c5\1\0\0\0\u03c7\u00bd\1\0\0\0\u03c8\u03cb\3v"+
		";\0\u03c9\u03ca\5E\0\0\u03ca\u03cc\3v;\0\u03cb\u03c9\1\0\0\0\u03cb\u03cc"+
		"\1\0\0\0\u03cc\u03cf\1\0\0\0\u03cd\u03d0\58\0\0\u03ce\u03d0\5=\0\0\u03cf"+
		"\u03cd\1\0\0\0\u03cf\u03ce\1\0\0\0\u03d0\u03d2\1\0\0\0\u03d1\u03c8\1\0"+
		"\0\0\u03d1\u03d2\1\0\0\0\u03d2\u03d3\1\0\0\0\u03d3\u03d4\3\u00c0`\0\u03d4"+
		"\u00bf\1\0\0\0\u03d5\u03d6\3v;\0\u03d6\u00c1\1\0\0\0\u03d7\u03d9\5\24"+
		"\0\0\u03d8\u03da\3@ \0\u03d9\u03d8\1\0\0\0\u03d9\u03da\1\0\0\0\u03da\u00c3"+
		"\1\0\0\0\u03db\u03dd\5\1\0\0\u03dc\u03de\3\u0084B\0\u03dd\u03dc\1\0\0"+
		"\0\u03dd\u03de\1\0\0\0\u03de\u00c5\1\0\0\0\u03df\u03e1\5\5\0\0\u03e0\u03e2"+
		"\3\u0084B\0\u03e1\u03e0\1\0\0\0\u03e1\u03e2\1\0\0\0\u03e2\u00c7\1\0\0"+
		"\0\u03e3\u03e4\5\r\0\0\u03e4\u03e5\3\u0084B\0\u03e5\u00c9\1\0\0\0\u03e6"+
		"\u03e7\5\t\0\0\u03e7\u00cb\1\0\0\0\u03e8\u03e9\5\7\0\0\u03e9\u03ea\3v"+
		";\0\u03ea\u00cd\1\0\0\0\u03eb\u03ec\5I\0\0\u03ec\u03f1\5?\0\0\u03ed\u03ef"+
		"\3\u00d0h\0\u03ee\u03f0\5E\0\0\u03ef\u03ee\1\0\0\0\u03ef\u03f0\1\0\0\0"+
		"\u03f0\u03f2\1\0\0\0\u03f1\u03ed\1\0\0\0\u03f1\u03f2\1\0\0\0\u03f2\u03f3"+
		"\1\0\0\0\u03f3\u03f4\5@\0\0\u03f4\u00cf\1\0\0\0\u03f5\u03f8\3\0\0\0\u03f6"+
		"\u03f7\5E\0\0\u03f7\u03f9\3@ \0\u03f8\u03f6\1\0\0\0\u03f8\u03f9\1\0\0"+
		"\0\u03f9\u03fc\1\0\0\0\u03fa\u03fc\3@ \0\u03fb\u03f5\1\0\0\0\u03fb\u03fa"+
		"\1\0\0\0\u03fc\u00d1\1\0\0\0\u03fd\u03fe\3\u00d4j\0\u03fe\u03ff\5G\0\0"+
		"\u03ff\u0401\1\0\0\0\u0400\u03fd\1\0\0\0\u0400\u0401\1\0\0\0\u0401\u0407"+
		"\1\0\0\0\u0402\u0403\3\u00d8l\0\u0403\u0404\5G\0\0\u0404\u0406\1\0\0\0"+
		"\u0405\u0402\1\0\0\0\u0406\u0409\1\0\0\0\u0407\u0405\1\0\0\0\u0407\u0408"+
		"\1\0\0\0\u0408\u040f\1\0\0\0\u0409\u0407\1\0\0\0\u040a\u040b\38\34\0\u040b"+
		"\u040c\5G\0\0\u040c\u040e\1\0\0\0\u040d\u040a\1\0\0\0\u040e\u0411\1\0"+
		"\0\0\u040f\u040d\1\0\0\0\u040f\u0410\1\0\0\0\u0410\u00d3\1\0\0\0\u0411"+
		"\u040f\1\0\0\0\u0412\u0413\5\22\0\0\u0413\u0414\3\u00d6k\0\u0414\u0415"+
		"\6j\uffff\0\u0415\u00d5\1\0\0\0\u0416\u0417\5I\0\0\u0417\u00d7\1\0\0\0"+
		"\u0418\u0429\5\17\0\0\u0419\u042a\3\u00dam\0\u041a\u0426\5?\0\0\u041b"+
		"\u0420\3\u00dam\0\u041c\u041d\5G\0\0\u041d\u041f\3\u00dam\0\u041e\u041c"+
		"\1\0\0\0\u041f\u0422\1\0\0\0\u0420\u041e\1\0\0\0\u0420\u0421\1\0\0\0\u0421"+
		"\u0424\1\0\0\0\u0422\u0420\1\0\0\0\u0423\u0425\5G\0\0\u0424\u0423\1\0"+
		"\0\0\u0424\u0425\1\0\0\0\u0425\u0427\1\0\0\0\u0426\u041b\1\0\0\0\u0426"+
		"\u0427\1\0\0\0\u0427\u0428\1\0\0\0\u0428\u042a\5@\0\0\u0429\u0419\1\0"+
		"\0\0\u0429\u041a\1\0\0\0\u042a\u00d9\1\0\0\0\u042b\u042c\5F\0\0\u042c"+
		"\u0435\3\u00dcn\0\u042d\u042e\3\u00d6k\0\u042e\u042f\3\u00dcn\0\u042f"+
		"\u0430\6m\uffff\0\u0430\u0435\1\0\0\0\u0431\u0432\3\u00dcn\0\u0432\u0433"+
		"\6m\uffff\0\u0433\u0435\1\0\0\0\u0434\u042b\1\0\0\0\u0434\u042d\1\0\0"+
		"\0\u0434\u0431\1\0\0\0\u0435\u00db\1\0\0\0\u0436\u0437\5R\0\0\u0437\u00dd"+
		"\1\0\0\0}\u00e4\1\u00f0\1\u0106\1\u010a\1\u0112\1\u0115\1\u0118\1\u0128"+
		"\1\u012c\1\u0131\1\u0133\1\u013c\1\u0140\1\u0143\1\u014e\1\u0152\1\u0154"+
		"\1\u015c\1\u016c\1\u0170\1\u017a\1\u017e\1\u0180\1\u0187\1\u018c\1\u0196"+
		"\1\u019a\1\u019c\1\u019f\1\u01a3\1\u01a7\1\u01ae\1\u01b6\1\u01c1\1\u01c5"+
		"\1\u01c7\1\u01ca\1\u01d7\1\u01db\1\u01dd\1\u01e0\1\u01e6\1\u01ea\1\u01f4"+
		"\1\u01fd\1\u0201\1\u0204\1\u0212\1\u0217\1\u021e\1\u0224\1\u0232\1\u0240"+
		"\1\u0245\1\u0247\1\u0250\1\u0256\1\u025c\1\u0264\1\u0271\1\u0277\1\u0281"+
		"\1\u0289\1\u0293\1\u02a7\1\u02ab\1\u02b8\1\u02ba\1\u02bd\1\u02bf\1\u02c4"+
		"\1\u02da\1\u02e2\1\u02fe\1\u0304\1\u030e\1\u0314\1\u031b\1\u031d\1\u0321"+
		"\1\u0327\1\u032a\1\u0330\1\u033c\1\u0340\1\u0342\1\u0347\1\u034d\1\u0354"+
		"\1\u035b\1\u036a\1\u036e\1\u0370\1\u0375\1\u037c\1\u0383\1\u038a\1\u038e"+
		"\1\u0392\1\u039b\1\u039f\1\u03ac\1\u03b8\1\u03bc\1\u03be\1\u03c3\1\u03c6"+
		"\1\u03cb\1\u03cf\1\u03d1\1\u03d9\1\u03dd\1\u03e1\1\u03ef\1\u03f1\1\u03f8"+
		"\1\u03fb\1\u0400\1\u0407\1\u040f\1\u0420\1\u0424\1\u0426\1\u0429\1\u0434"+
		"\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}