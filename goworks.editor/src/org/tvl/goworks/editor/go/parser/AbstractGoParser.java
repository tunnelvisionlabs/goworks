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
			setState(232);
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
					setState(224); typeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(226); match(LeftParen);
					setState(228); type();
					setState(230); match(RightParen);
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
			setState(234); qualifiedIdentifier();
			}
		}
		catch (RecognitionException re) {
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
			setState(252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(236); arrayType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(238); structType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(240); pointerType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(242); functionType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(244); interfaceType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(246); sliceType();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(248); mapType();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(250); channelType();
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
			setState(254); match(LeftBrack);
			setState(256); arrayLength();
			setState(258); match(RightBrack);
			setState(260); elementType();
			}
		}
		catch (RecognitionException re) {
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
			setState(262); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(264); type();
			}
		}
		catch (RecognitionException re) {
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
			setState(266); match(LeftBrack);
			setState(268); match(RightBrack);
			setState(270); elementType();
			}
		}
		catch (RecognitionException re) {
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
			setState(272); match(Struct);
			setState(274); match(LeftBrace);
			setState(282);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(276); fieldDecl();
					setState(278); match(Semi);
					}
					} 
				}
				setState(284);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(287);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(285); fieldDecl();
					}
					break;
			}
			setState(289); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(297);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(291); identifierList();
					setState(293); type();
					}
					break;

				case 2:
					{
					setState(295); anonymousField();
					}
					break;
			}
			setState(301);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(299); tag();
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
			setState(305);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(303); _localctx.ptr = match(Star);
					}
					break;
			}
			setState(307); typeName();
			}
		}
		catch (RecognitionException re) {
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
			setState(309); match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
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
			setState(311); _localctx.ptr = match(Star);
			setState(313); baseType();
			}
		}
		catch (RecognitionException re) {
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
			setState(315); type();
			}
		}
		catch (RecognitionException re) {
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
			setState(317); match(Func);
			setState(319); signature();
			}
		}
		catch (RecognitionException re) {
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
			setState(321); parameters();
			setState(325);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(323); result();
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
			setState(331);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(327); parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(329); type();
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
			setState(333); match(LeftParen);
			setState(341);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(335); parameterList();
					setState(339);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
						case 1:
							{
							setState(337); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(343); match(RightParen);
			}
		}
		catch (RecognitionException re) {
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
			setState(345); parameterDecl();
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(347); match(Comma);
					setState(349); parameterDecl();
					}
					} 
				}
				setState(355);
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
			setState(358);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(356); identifierList();
					}
					break;
			}
			setState(362);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(360); _localctx.ellip = match(Ellipsis);
					}
					break;
			}
			setState(364); type();
			}
		}
		catch (RecognitionException re) {
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
			setState(366); match(Interface);
			setState(368); match(LeftBrace);
			setState(385);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(370); methodSpec();
					setState(378);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(372); match(Semi);
							setState(374); methodSpec();
							}
							} 
						}
						setState(380);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
					}
					setState(383);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
						case 1:
							{
							setState(381); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(387); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(395);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(389); methodName();
					setState(391); signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(393); interfaceTypeName();
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
			setState(397); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(399); typeName();
			}
		}
		catch (RecognitionException re) {
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
			setState(401); match(Map);
			setState(403); match(LeftBrack);
			setState(405); keyType();
			setState(407); match(RightBrack);
			setState(409); elementType();
			}
		}
		catch (RecognitionException re) {
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
			setState(411); type();
			}
		}
		catch (RecognitionException re) {
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
			setState(423);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(413); match(Chan);
					setState(417);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
						case 1:
							{
							setState(415); _localctx.send = match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(419); _localctx.recv = match(LeftArrow);
					setState(421); match(Chan);
					}
					break;
			}
			setState(425); elementType();
			}
		}
		catch (RecognitionException re) {
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
			setState(427); match(LeftBrace);
			setState(444);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(429); statement();
					setState(437);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(431); match(Semi);
							setState(433); statement();
							}
							} 
						}
						setState(439);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(442);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
						case 1:
							{
							setState(440); match(Semi);
							}
							break;
					}
					}
					break;
			}
			setState(446); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(454);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(448); constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(450); typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(452); varDecl();
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
			setState(462);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(456); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(458); functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(460); methodDecl();
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
			setState(464); match(Const);
			setState(489);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(466); constSpec();
					}
					break;

				case 2:
					{
					setState(468); match(LeftParen);
					setState(485);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
						case 1:
							{
							setState(470); constSpec();
							setState(478);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(472); match(Semi);
									setState(474); constSpec();
									}
									} 
								}
								setState(480);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
							}
							setState(483);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
								case 1:
									{
									setState(481); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(487); match(RightParen);
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
			setState(491); identifierList();
			setState(501);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(495);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
						case 1:
							{
							setState(493); type();
							}
							break;
					}
					setState(497); match(Equal);
					setState(499); expressionList();
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
			setState(503); match(IDENTIFIER);
			setState(511);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(505); match(Comma);
					setState(507); match(IDENTIFIER);
					}
					} 
				}
				setState(513);
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
			setState(514); expression(0);
			setState(522);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(516); match(Comma);
					setState(518); expression(0);
					}
					} 
				}
				setState(524);
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
			setState(525); match(Type);
			setState(550);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(527); typeSpec();
					}
					break;

				case 2:
					{
					setState(529); match(LeftParen);
					setState(546);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(531); typeSpec();
							setState(539);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(533); match(Semi);
									setState(535); typeSpec();
									}
									} 
								}
								setState(541);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
							}
							setState(544);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
								case 1:
									{
									setState(542); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(548); match(RightParen);
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
			setState(552); match(IDENTIFIER);
			setState(554); type();
			}
		}
		catch (RecognitionException re) {
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
			setState(556); match(Var);
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(558); varSpec();
					}
					break;

				case 2:
					{
					setState(560); match(LeftParen);
					setState(577);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
						case 1:
							{
							setState(562); varSpec();
							setState(570);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(564); match(Semi);
									setState(566); varSpec();
									}
									} 
								}
								setState(572);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
							}
							setState(575);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
								case 1:
									{
									setState(573); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(579); match(RightParen);
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
			setState(583); identifierList();
			setState(597);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(585); type();
					setState(591);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(587); match(Equal);
							setState(589); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(593); match(Equal);
					setState(595); expressionList();
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
			setState(599); identifierList();
			setState(601); match(ColonEqual);
			setState(603); expressionList();
			}
		}
		catch (RecognitionException re) {
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
			setState(605); match(Func);
			setState(607); match(IDENTIFIER);
			setState(609); signature();
			setState(613);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(611); body();
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
			setState(615); block();
			}
		}
		catch (RecognitionException re) {
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
			setState(617); match(Func);
			setState(619); receiver();
			setState(621); methodName();
			setState(623); signature();
			setState(627);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(625); body();
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
			setState(629); match(LeftParen);
			setState(633);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(631); match(IDENTIFIER);
					}
					break;
			}
			setState(637);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(635); _localctx.ptr = match(Star);
					}
					break;
			}
			setState(639); baseTypeName();
			setState(641); match(RightParen);
			}
		}
		catch (RecognitionException re) {
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
			setState(643); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(657);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(645); literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(647); qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(649); methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(651); match(LeftParen);
					setState(653); expression(0);
					setState(655); match(RightParen);
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
			setState(665);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(659); basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(661); compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(663); functionLiteral();
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
			setState(677);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(667); match(INT_LITERAL);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(669); match(FLOAT_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(671); match(IMAGINARY_LITERAL);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(673); match(CharLiteral);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(675); match(StringLiteral);
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
			setState(685);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(679);
					if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "isPackageName(_input.LT(1))");
					setState(681); packageName();
					setState(683); _localctx.dot = match(Dot);
					}
					break;
			}
			setState(687); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(689); receiverType();
			setState(691); _localctx.dot = match(Dot);
			setState(693); methodName();
			}
		}
		catch (RecognitionException re) {
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
					setState(699); _localctx.ptr = match(Star);
					setState(701); typeName();
					setState(703); match(RightParen);
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
			setState(707); literalType();
			setState(709); literalValue();
			}
		}
		catch (RecognitionException re) {
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
		}
		catch (RecognitionException re) {
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
		}
		catch (RecognitionException re) {
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
			setState(743); element();
			setState(751);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(745); match(Comma);
					setState(747); element();
					}
					} 
				}
				setState(753);
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
		}
		catch (RecognitionException re) {
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
		}
		catch (RecognitionException re) {
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
			setState(768); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(770); expression(0);
			}
		}
		catch (RecognitionException re) {
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
		}
		catch (RecognitionException re) {
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
			setState(778); functionType();
			setState(780); body();
			}
		}
		catch (RecognitionException re) {
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
			setState(808);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					_localctx = new UnaryExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;

					setState(798);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(784); ((UnaryExprContext)_localctx).op = match(Plus);
							}
							break;

						case 2:
							{
							setState(786); ((UnaryExprContext)_localctx).op = match(Minus);
							}
							break;

						case 3:
							{
							setState(788); ((UnaryExprContext)_localctx).op = match(Bang);
							}
							break;

						case 4:
							{
							setState(790); ((UnaryExprContext)_localctx).op = match(Caret);
							}
							break;

						case 5:
							{
							setState(792); ((UnaryExprContext)_localctx).op = match(Star);
							}
							break;

						case 6:
							{
							setState(794); ((UnaryExprContext)_localctx).op = match(Amp);
							}
							break;

						case 7:
							{
							setState(796); ((UnaryExprContext)_localctx).op = match(LeftArrow);
							}
							break;
					}
					setState(800); expression(6);
					}
					break;

				case 2:
					{
					_localctx = new OperandExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(802); operand();
					}
					break;

				case 3:
					{
					_localctx = new ConversionOrCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(804); conversion();
					}
					break;

				case 4:
					{
					_localctx = new BuiltinCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(806); builtinCall();
					}
					break;
			}
			_ctx.stop = _input.LT(-1);
			setState(930);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					_prevctx.stop = _input.LT(-1);
					{
					setState(928);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
						case 1:
							{
							_localctx = new MultExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(810);
							if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
							setState(826);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
								case 1:
									{
									setState(812); ((MultExprContext)_localctx).op = match(Star);
									}
									break;

								case 2:
									{
									setState(814); ((MultExprContext)_localctx).op = match(Slash);
									}
									break;

								case 3:
									{
									setState(816); ((MultExprContext)_localctx).op = match(Percent);
									}
									break;

								case 4:
									{
									setState(818); ((MultExprContext)_localctx).op = match(LeftShift);
									}
									break;

								case 5:
									{
									setState(820); ((MultExprContext)_localctx).op = match(RightShift);
									}
									break;

								case 6:
									{
									setState(822); ((MultExprContext)_localctx).op = match(Amp);
									}
									break;

								case 7:
									{
									setState(824); ((MultExprContext)_localctx).op = match(AmpCaret);
									}
									break;
							}
							setState(828); expression(6);
							}
							break;

						case 2:
							{
							_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(830);
							if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
							setState(840);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
								case 1:
									{
									setState(832); ((AddExprContext)_localctx).op = match(Plus);
									}
									break;

								case 2:
									{
									setState(834); ((AddExprContext)_localctx).op = match(Minus);
									}
									break;

								case 3:
									{
									setState(836); ((AddExprContext)_localctx).op = match(Pipe);
									}
									break;

								case 4:
									{
									setState(838); ((AddExprContext)_localctx).op = match(Caret);
									}
									break;
							}
							setState(842); expression(5);
							}
							break;

						case 3:
							{
							_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(844);
							if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
							setState(858);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
								case 1:
									{
									setState(846); ((CompareExprContext)_localctx).op = match(EqualEqual);
									}
									break;

								case 2:
									{
									setState(848); ((CompareExprContext)_localctx).op = match(BangEqual);
									}
									break;

								case 3:
									{
									setState(850); ((CompareExprContext)_localctx).op = match(LessThan);
									}
									break;

								case 4:
									{
									setState(852); ((CompareExprContext)_localctx).op = match(LessEqual);
									}
									break;

								case 5:
									{
									setState(854); ((CompareExprContext)_localctx).op = match(GreaterThan);
									}
									break;

								case 6:
									{
									setState(856); ((CompareExprContext)_localctx).op = match(GreaterEqual);
									}
									break;
							}
							setState(860); expression(4);
							}
							break;

						case 4:
							{
							_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(862);
							if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
							setState(864); match(And);
							setState(866); expression(3);
							}
							break;

						case 5:
							{
							_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(868);
							if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "1 >= $_p");
							setState(870); match(Or);
							setState(872); expression(2);
							}
							break;

						case 6:
							{
							_localctx = new SelectorExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(874);
							if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
							setState(876); ((SelectorExprContext)_localctx).dot = match(Dot);
							setState(878); match(IDENTIFIER);
							}
							break;

						case 7:
							{
							_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(880);
							if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
							setState(882); match(LeftBrack);
							setState(884); expression(0);
							setState(886); match(RightBrack);
							}
							break;

						case 8:
							{
							_localctx = new SliceExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(888);
							if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
							setState(890); match(LeftBrack);
							setState(894);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
								case 1:
									{
									setState(892); ((SliceExprContext)_localctx).from = expression(0);
									}
									break;
							}
							setState(896); match(Colon);
							setState(900);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
								case 1:
									{
									setState(898); ((SliceExprContext)_localctx).to = expression(0);
									}
									break;
							}
							setState(902); match(RightBrack);
							}
							break;

						case 9:
							{
							_localctx = new TypeAssertionExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(904);
							if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
							setState(906); ((TypeAssertionExprContext)_localctx).dot = match(Dot);
							setState(908); ((TypeAssertionExprContext)_localctx).lp = match(LeftParen);
							setState(910); type();
							setState(912); ((TypeAssertionExprContext)_localctx).rp = match(RightParen);
							}
							break;

						case 10:
							{
							_localctx = new CallExprContext(new ExpressionContext(_parentctx, _parentState, _p));
							_localctx.addChild(_prevctx);
							pushNewRecursionContext(_localctx, _startState, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(914);
							if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
							setState(916); ((CallExprContext)_localctx).lp = match(LeftParen);
							setState(924);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
								case 1:
									{
									setState(918); argumentList();
									setState(922);
									//_errHandler.sync(this);
									switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
										case 1:
											{
											setState(920); match(Comma);
											}
											break;
									}
									}
									break;
							}
							setState(926); ((CallExprContext)_localctx).rp = match(RightParen);
							}
							break;
					}
					} 
				}
				setState(932);
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
			setState(933); expressionList();
			setState(937);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(935); _localctx.ellip = match(Ellipsis);
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
			setState(939); type();
			setState(941); match(LeftParen);
			setState(943); expression(0);
			setState(945); match(RightParen);
			}
		}
		catch (RecognitionException re) {
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
			setState(977);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(947); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(949); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(951); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(953); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(955); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(957); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(959); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(961); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(963); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(965); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(967); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(969); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(971); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(973); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(975); deferStmt();
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
			setState(991);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(979); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(981); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(983); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(985); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(987); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(989); shortVarDecl();
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
			setState(995); label();
			setState(997); match(Colon);
			setState(999); statement();
			}
		}
		catch (RecognitionException re) {
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
			setState(1001); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(1003); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1005); channel();
			setState(1007); match(LeftArrow);
			setState(1009); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1011); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1013); expression(0);
			setState(1015);
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
			setState(1017); _localctx.targets = expressionList();
			setState(1019); assignOp();
			setState(1021); _localctx.values = expressionList();
			}
		}
		catch (RecognitionException re) {
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
			setState(1029);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1023); addAssignOp();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1025); mulAssignOp();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1027); match(Equal);
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
			setState(1039);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1031); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1033); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1035); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1037); match(CaretEqual);
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
			setState(1055);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1041); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1043); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1045); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1047); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1049); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1051); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1053); match(AmpCaretEqual);
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
			setState(1057); match(If);
			setState(1063);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(1059); simpleStmt();
					setState(1061); match(Semi);
					}
					break;
			}
			setState(1065); expression(0);
			setState(1067); block();
			setState(1077);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(1069); match(Else);
					setState(1075);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(1071); ifStmt();
							}
							break;

						case 2:
							{
							setState(1073); block();
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
			setState(1083);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1079); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1081); typeSwitchStmt();
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
			setState(1085); match(Switch);
			setState(1091);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(1087); simpleStmt();
					setState(1089); match(Semi);
					}
					break;
			}
			setState(1095);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(1093); expression(0);
					}
					break;
			}
			setState(1097); match(LeftBrace);
			setState(1103);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1099); exprCaseClause();
					}
					} 
				}
				setState(1105);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(1106); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(1108); exprSwitchCase();
			setState(1110); match(Colon);
			setState(1127);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(1112); statement();
					setState(1120);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(1114); match(Semi);
							setState(1116); statement();
							}
							} 
						}
						setState(1122);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
					}
					setState(1125);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
						case 1:
							{
							setState(1123); match(Semi);
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
			setState(1135);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1129); match(Case);
					setState(1131); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1133); match(Default);
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
			setState(1137); match(Switch);
			setState(1143);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(1139); simpleStmt();
					setState(1141); match(Semi);
					}
					break;
			}
			setState(1145); typeSwitchGuard();
			setState(1147); match(LeftBrace);
			setState(1153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1149); typeCaseClause();
					}
					} 
				}
				setState(1155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(1156); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(1162);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					{
					setState(1158); match(IDENTIFIER);
					setState(1160); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(1164); expression(0);
			setState(1166); _localctx.dot = match(Dot);
			setState(1168); match(LeftParen);
			setState(1170); match(Type);
			setState(1172); match(RightParen);
			}
		}
		catch (RecognitionException re) {
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
			setState(1174); typeSwitchCase();
			setState(1176); match(Colon);
			setState(1193);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(1178); statement();
					setState(1186);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(1180); match(Semi);
							setState(1182); statement();
							}
							} 
						}
						setState(1188);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					}
					setState(1191);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
						case 1:
							{
							setState(1189); match(Semi);
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
			setState(1201);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1195); match(Case);
					setState(1197); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1199); match(Default);
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
			setState(1203); type();
			setState(1211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1205); match(Comma);
					setState(1207); type();
					}
					} 
				}
				setState(1213);
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
			setState(1214); match(For);
			setState(1222);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1216); condition();
					}
					break;

				case 2:
					{
					setState(1218); forClause();
					}
					break;

				case 3:
					{
					setState(1220); rangeClause();
					}
					break;
			}
			setState(1224); block();
			}
		}
		catch (RecognitionException re) {
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
			setState(1226); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1230);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(1228); initStmt();
					}
					break;
			}
			setState(1232); match(Semi);
			setState(1236);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(1234); condition();
					}
					break;
			}
			setState(1238); match(Semi);
			setState(1242);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(1240); postStmt();
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
			setState(1244); simpleStmt();
			}
		}
		catch (RecognitionException re) {
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
			setState(1246); simpleStmt();
			}
		}
		catch (RecognitionException re) {
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
			setState(1248); _localctx.e1 = expression(0);
			setState(1254);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(1250); match(Comma);
					setState(1252); _localctx.e2 = expression(0);
					}
					break;
			}
			setState(1260);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(1256); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(1258); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(1262); match(Range);
			setState(1264); _localctx.e = expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1266); match(Go);
			setState(1268); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1270); match(Select);
			setState(1272); match(LeftBrace);
			setState(1278);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1274); commClause();
					}
					} 
				}
				setState(1280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
			}
			setState(1281); match(RightBrace);
			}
		}
		catch (RecognitionException re) {
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
			setState(1283); commCase();
			setState(1285); match(Colon);
			setState(1302);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1287); statement();
					setState(1295);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					while ( _alt!=2 && _alt!=-1 ) {
						if ( _alt==1 ) {
							{
							{
							setState(1289); match(Semi);
							setState(1291); statement();
							}
							} 
						}
						setState(1297);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
					}
					setState(1300);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
						case 1:
							{
							setState(1298); match(Semi);
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
			setState(1314);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1304); match(Case);
					setState(1310);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
						case 1:
							{
							setState(1306); sendStmt();
							}
							break;

						case 2:
							{
							setState(1308); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1312); match(Default);
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
			setState(1330);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1316); _localctx.e1 = expression(0);
					setState(1322);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(1318); match(Comma);
							setState(1320); _localctx.e2 = expression(0);
							}
							break;
					}
					setState(1328);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
						case 1:
							{
							setState(1324); _localctx.eq = match(Equal);
							}
							break;

						case 2:
							{
							setState(1326); _localctx.defeq = match(ColonEqual);
							}
							break;
					}
					}
					break;
			}
			setState(1332); recvExpr();
			}
		}
		catch (RecognitionException re) {
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
			setState(1334); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1336); match(Return);
			setState(1340);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
				case 1:
					{
					setState(1338); expressionList();
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
			setState(1342); match(Break);
			setState(1346);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					{
					setState(1344); label();
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
			setState(1348); match(Continue);
			setState(1352);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
				case 1:
					{
					setState(1350); label();
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
			setState(1354); match(Goto);
			setState(1356); label();
			}
		}
		catch (RecognitionException re) {
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
			setState(1358); match(Fallthrough);
			}
		}
		catch (RecognitionException re) {
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
			setState(1360); match(Defer);
			setState(1362); expression(0);
			}
		}
		catch (RecognitionException re) {
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
			setState(1364); match(IDENTIFIER);
			setState(1366); match(LeftParen);
			setState(1374);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1368); builtinArgs();
					setState(1372);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
						case 1:
							{
							setState(1370); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1376); match(RightParen);
			}
		}
		catch (RecognitionException re) {
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
			setState(1388);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1378); type();
					setState(1384);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
						case 1:
							{
							setState(1380); match(Comma);
							setState(1382); expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1386); expressionList();
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
			setState(1394);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1390); packageClause();
					setState(1392); match(Semi);
					}
					break;
			}
			setState(1402);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1396); importDecl();
					setState(1398); match(Semi);
					}
					} 
				}
				setState(1404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(1411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(1405); topLevelDecl();
					setState(1407); match(Semi);
					}
					} 
				}
				setState(1413);
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
			setState(1414); match(Package);
			setState(1416); _localctx.packageName = packageName();
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
			setState(1420); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
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
			setState(1422); match(Import);
			setState(1447);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
				case 1:
					{
					setState(1424); importSpec();
					}
					break;

				case 2:
					{
					setState(1426); match(LeftParen);
					setState(1443);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
						case 1:
							{
							setState(1428); importSpec();
							setState(1436);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
							while ( _alt!=2 && _alt!=-1 ) {
								if ( _alt==1 ) {
									{
									{
									setState(1430); match(Semi);
									setState(1432); importSpec();
									}
									} 
								}
								setState(1438);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
							}
							setState(1441);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
								case 1:
									{
									setState(1439); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(1445); match(RightParen);
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
			setState(1463);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1449); _localctx.dot = match(Dot);
					setState(1451); importPath();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1453); _localctx.packageName = packageName();
					setState(1455); importPath();
					addPackageName((_localctx.packageName!=null?(_localctx.packageName.start):null));
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1459); _localctx.importPath = importPath();
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
			setState(1465); match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
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
		"\1S\u05bc\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		"\7f\2g\7g\2h\7h\2i\7i\2j\7j\2k\7k\2l\7l\2m\7m\2n\7n\1\0\0\1\0\0\1\0\0"+
		"\1\0\0\1\0\1\0\3\0\b\0\1\1\1\1\1\2\0\1\2\0\1\2\0\1\2\0\1\2\0\1\2\0\1\2"+
		"\0\1\2\0\3\2\b\2\1\3\0\1\3\0\1\3\0\1\3\1\3\1\4\1\4\1\5\1\5\1\6\0\1\6\0"+
		"\1\6\1\6\1\7\0\1\7\0\1\7\0\1\7\1\7\5\7\b\7\n\7\f\7\u011c\t\7\1\7\0\3\7"+
		"\b\7\1\7\1\7\1\b\0\1\b\1\b\1\b\0\3\b\b\b\1\b\0\3\b\b\b\1\t\0\3\t\b\t\1"+
		"\t\1\t\1\n\1\n\1\13\0\1\13\1\13\1\f\1\f\1\r\0\1\r\1\r\1\16\0\1\16\0\3"+
		"\16\b\16\1\17\0\1\17\0\3\17\b\17\1\20\0\1\20\0\1\20\0\3\20\b\20\3\20\b"+
		"\20\1\20\1\20\1\21\0\1\21\0\1\21\0\5\21\b\21\n\21\f\21\u0163\t\21\1\22"+
		"\0\3\22\b\22\1\22\0\3\22\b\22\1\22\1\22\1\23\0\1\23\0\1\23\0\1\23\0\1"+
		"\23\0\5\23\b\23\n\23\f\23\u017c\t\23\1\23\0\3\23\b\23\3\23\b\23\1\23\1"+
		"\23\1\24\0\1\24\1\24\1\24\0\3\24\b\24\1\25\1\25\1\26\1\26\1\27\0\1\27"+
		"\0\1\27\0\1\27\0\1\27\1\27\1\30\1\30\1\31\0\1\31\0\3\31\b\31\1\31\0\1"+
		"\31\0\3\31\b\31\1\31\1\31\1\32\0\1\32\0\1\32\0\1\32\0\5\32\b\32\n\32\f"+
		"\32\u01b7\t\32\1\32\0\3\32\b\32\3\32\b\32\1\32\1\32\1\33\0\1\33\0\1\33"+
		"\0\3\33\b\33\1\34\0\1\34\0\1\34\0\3\34\b\34\1\35\0\1\35\0\1\35\0\1\35"+
		"\0\1\35\0\1\35\0\5\35\b\35\n\35\f\35\u01e0\t\35\1\35\0\3\35\b\35\3\35"+
		"\b\35\1\35\0\3\35\b\35\1\36\0\1\36\0\3\36\b\36\1\36\0\1\36\0\3\36\b\36"+
		"\1\37\0\1\37\0\1\37\0\5\37\b\37\n\37\f\37\u0201\t\37\1 \0\1 \0\1 \0\5"+
		" \b \n \f \u020c\t \1!\0\1!\0\1!\0\1!\0\1!\0\1!\0\5!\b!\n!\f!\u021d\t"+
		"!\1!\0\3!\b!\3!\b!\1!\0\3!\b!\1\"\0\1\"\1\"\1#\0\1#\0\1#\0\1#\0\1#\0\1"+
		"#\0\5#\b#\n#\f#\u023c\t#\1#\0\3#\b#\3#\b#\1#\0\3#\b#\1$\0\1$\0\1$\0\1"+
		"$\0\3$\b$\1$\0\1$\0\3$\b$\1%\0\1%\0\1%\1%\1&\0\1&\0\1&\0\1&\0\3&\b&\1"+
		"\'\1\'\1(\0\1(\0\1(\0\1(\0\1(\0\3(\b(\1)\0\1)\0\3)\b)\1)\0\3)\b)\1)\0"+
		"\1)\1)\1*\1*\1+\0\1+\0\1+\0\1+\0\1+\0\1+\1+\3+\b+\1,\0\1,\0\1,\0\3,\b"+
		",\1-\0\1-\0\1-\0\1-\0\1-\0\3-\b-\1.\0\1.\0\1.\1.\3.\b.\1.\1.\1/\0\1/\0"+
		"\1/\1/\1\60\0\1\60\0\1\60\0\1\60\0\1\60\1\60\3\60\b\60\1\61\0\1\61\1\61"+
		"\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\1\62\0\3\62\b"+
		"\62\1\63\0\1\63\0\1\63\0\3\63\b\63\3\63\b\63\1\63\1\63\1\64\0\1\64\0\1"+
		"\64\0\5\64\b\64\n\64\f\64\u02f1\t\64\1\65\0\1\65\1\65\3\65\b\65\1\65\1"+
		"\65\1\66\0\1\66\0\3\66\b\66\1\67\1\67\18\18\19\0\19\0\39\b9\1:\0\1:\1"+
		":\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\3"+
		";\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0"+
		"\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0"+
		"\1;\0\1;\0\3;\b;\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1"+
		";\0\1;\0\1;\0\3;\b;\3;\b;\1;\0\5;\b;\n;\f;\u03a4\t;\1<\0\1<\0\3<\b<\1"+
		"=\0\1=\0\1=\0\1=\1=\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0"+
		"\1>\0\1>\0\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1"+
		"@\1@\1A\0\1A\0\1A\1A\1B\1B\1C\1C\1D\0\1D\0\1D\1D\1E\1E\1F\0\1F\1F\1G\0"+
		"\1G\0\1G\1G\1H\0\1H\0\1H\0\3H\bH\1I\0\1I\0\1I\0\1I\0\3I\bI\1J\0\1J\0\1"+
		"J\0\1J\0\1J\0\1J\0\1J\0\3J\bJ\1K\0\1K\0\1K\1K\3K\bK\1K\0\1K\0\1K\0\1K"+
		"\0\1K\0\3K\bK\3K\bK\1L\0\1L\0\3L\bL\1M\0\1M\0\1M\1M\3M\bM\1M\0\3M\bM\1"+
		"M\0\1M\0\5M\bM\nM\fM\u0451\tM\1M\1M\1N\0\1N\0\1N\0\1N\0\1N\0\5N\bN\nN"+
		"\fN\u0462\tN\1N\0\3N\bN\3N\bN\1O\0\1O\0\1O\0\3O\bO\1P\0\1P\0\1P\1P\3P"+
		"\bP\1P\0\1P\0\1P\0\5P\bP\nP\fP\u0483\tP\1P\1P\1Q\0\1Q\0\3Q\bQ\1Q\0\1Q"+
		"\0\1Q\0\1Q\0\1Q\1Q\1R\0\1R\0\1R\0\1R\0\1R\0\5R\bR\nR\fR\u04a4\tR\1R\0"+
		"\3R\bR\3R\bR\1S\0\1S\0\1S\0\3S\bS\1T\0\1T\0\1T\0\5T\bT\nT\fT\u04bd\tT"+
		"\1U\0\1U\0\1U\0\1U\0\3U\bU\1U\1U\1V\1V\1W\0\3W\bW\1W\0\1W\0\3W\bW\1W\0"+
		"\1W\0\3W\bW\1X\1X\1Y\1Y\1Z\0\1Z\0\1Z\0\3Z\bZ\1Z\0\1Z\0\3Z\bZ\1Z\0\1Z\1"+
		"Z\1[\0\1[\1[\1\\\0\1\\\0\1\\\0\5\\\b\\\n\\\f\\\u0500\t\\\1\\\1\\\1]\0"+
		"\1]\0\1]\0\1]\0\1]\0\5]\b]\n]\f]\u0511\t]\1]\0\3]\b]\3]\b]\1^\0\1^\0\1"+
		"^\0\3^\b^\1^\0\3^\b^\1_\0\1_\0\1_\0\3_\b_\1_\0\1_\0\3_\b_\3_\b_\1_\1_"+
		"\1`\1`\1a\0\1a\0\3a\ba\1b\0\1b\0\3b\bb\1c\0\1c\0\3c\bc\1d\0\1d\1d\1e\1"+
		"e\1f\0\1f\1f\1g\0\1g\0\1g\0\1g\0\3g\bg\3g\bg\1g\1g\1h\0\1h\0\1h\0\3h\b"+
		"h\1h\0\3h\bh\1i\0\1i\1i\3i\bi\1i\0\1i\1i\5i\bi\ni\fi\u057c\ti\1i\0\1i"+
		"\1i\5i\bi\ni\fi\u0585\ti\1j\0\1j\0\1j\1j\1k\1k\1l\0\1l\0\1l\0\1l\0\1l"+
		"\0\1l\0\5l\bl\nl\fl\u059e\tl\1l\0\3l\bl\3l\bl\1l\0\3l\bl\1m\0\1m\0\1m"+
		"\0\1m\0\1m\1m\1m\0\1m\1m\3m\bm\1n\1n\1no\0\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|"+
		"~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096"+
		"\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae"+
		"\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6"+
		"\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\0\1"+
		"\1\63\64\u0578\0\u00e8\1\0\0\0\1\u00e6\1\0\0\0\1\u0109\1\0\0\0\1\u0126"+
		"\1\0\0\0\1\u013c\1\0\0\0\1\u014c\1\0\0\0\1\u016d\1\0\0\0\1\u019c\1\0\0"+
		"\0\1\u01f0\1\0\0\0\1\u022b\1\0\0\0\1\u024f\1\0\0\0\1\u0390\1\0\0\0\1\u03ad"+
		"\1\0\0\0\1\u04bb\1\0\0\0\1\u04ba\1\0\0\0\1\u0568\1\0\0\0\2\u00ea\1\0\0"+
		"\0\3\u00e9\1\0\0\0\3\u0134\1\0\0\0\3\u0190\1\0\0\0\3\u02c2\1\0\0\0\3\u02bf"+
		"\1\0\0\0\3\u02da\1\0\0\0\4\u00fc\1\0\0\0\5\u00e9\1\0\0\0\6\u00fe\1\0\0"+
		"\0\7\u00fd\1\0\0\0\7\u02da\1\0\0\0\b\u0106\1\0\0\0\t\u0102\1\0\0\0\n\u0108"+
		"\1\0\0\0\13\u0105\1\0\0\0\13\u010f\1\0\0\0\13\u019a\1\0\0\0\13\u01aa\1"+
		"\0\0\0\13\u02da\1\0\0\0\f\u010a\1\0\0\0\r\u00fd\1\0\0\0\r\u02da\1\0\0"+
		"\0\16\u0110\1\0\0\0\17\u00fd\1\0\0\0\17\u02da\1\0\0\0\20\u0129\1\0\0\0"+
		"\21\u0116\1\0\0\0\21\u0120\1\0\0\0\22\u0131\1\0\0\0\23\u012a\1\0\0\0\24"+
		"\u0135\1\0\0\0\25\u012e\1\0\0\0\26\u0137\1\0\0\0\27\u00fd\1\0\0\0\30\u013b"+
		"\1\0\0\0\31\u013a\1\0\0\0\32\u013d\1\0\0\0\33\u00fd\1\0\0\0\33\u030c\1"+
		"\0\0\0\34\u0141\1\0\0\0\35\u0140\1\0\0\0\35\u0188\1\0\0\0\35\u0265\1\0"+
		"\0\0\35\u0273\1\0\0\0\36\u014b\1\0\0\0\37\u0146\1\0\0\0 \u014d\1\0\0\0"+
		"!\u0145\1\0\0\0!\u014c\1\0\0\0\"\u0159\1\0\0\0#\u0153\1\0\0\0$\u0166\1"+
		"\0\0\0%\u0161\1\0\0\0%\u0160\1\0\0\0&\u016e\1\0\0\0\'\u00fd\1\0\0\0(\u018b"+
		"\1\0\0\0)\u017a\1\0\0\0)\u0179\1\0\0\0*\u018d\1\0\0\0+\u0187\1\0\0\0+"+
		"\u026f\1\0\0\0+\u02b6\1\0\0\0,\u018f\1\0\0\0-\u018c\1\0\0\0.\u0191\1\0"+
		"\0\0/\u00fd\1\0\0\0/\u02da\1\0\0\0\60\u019b\1\0\0\0\61\u0197\1\0\0\0\62"+
		"\u01a7\1\0\0\0\63\u00fd\1\0\0\0\64\u01ab\1\0\0\0\65\u0268\1\0\0\0\65\u03d2"+
		"\1\0\0\0\65\u0435\1\0\0\0\65\u0434\1\0\0\0\65\u04c9\1\0\0\0\66\u01c6\1"+
		"\0\0\0\67\u01cf\1\0\0\0\67\u03d2\1\0\0\08\u01ce\1\0\0\09\u057f\1\0\0\0"+
		":\u01d0\1\0\0\0;\u01c7\1\0\0\0<\u01eb\1\0\0\0=\u01ea\1\0\0\0=\u01de\1"+
		"\0\0\0=\u01dd\1\0\0\0>\u01f7\1\0\0\0?\u0125\1\0\0\0?\u0167\1\0\0\0?\u01f5"+
		"\1\0\0\0?\u0255\1\0\0\0?\u0259\1\0\0\0@\u0202\1\0\0\0A\u01f6\1\0\0\0A"+
		"\u0250\1\0\0\0A\u0256\1\0\0\0A\u025c\1\0\0\0A\u03a9\1\0\0\0A\u03fb\1\0"+
		"\0\0A\u03fe\1\0\0\0A\u0470\1\0\0\0A\u053d\1\0\0\0A\u0569\1\0\0\0A\u056d"+
		"\1\0\0\0B\u020d\1\0\0\0C\u01c7\1\0\0\0D\u0228\1\0\0\0E\u0227\1\0\0\0E"+
		"\u021b\1\0\0\0E\u021a\1\0\0\0F\u022c\1\0\0\0G\u01c7\1\0\0\0H\u0247\1\0"+
		"\0\0I\u0246\1\0\0\0I\u023a\1\0\0\0I\u0239\1\0\0\0J\u0257\1\0\0\0K\u03e0"+
		"\1\0\0\0L\u025d\1\0\0\0M\u01cf\1\0\0\0N\u0267\1\0\0\0O\u0266\1\0\0\0O"+
		"\u0274\1\0\0\0O\u030d\1\0\0\0P\u0269\1\0\0\0Q\u01cf\1\0\0\0R\u0275\1\0"+
		"\0\0S\u026d\1\0\0\0T\u0283\1\0\0\0U\u0281\1\0\0\0V\u0291\1\0\0\0W\u0329"+
		"\1\0\0\0X\u0299\1\0\0\0Y\u0292\1\0\0\0Z\u02a5\1\0\0\0[\u029a\1\0\0\0\\"+
		"\u02ad\1\0\0\0]\u00eb\1\0\0\0]\u0292\1\0\0\0^\u02b1\1\0\0\0_\u0292\1\0"+
		"\0\0`\u02c1\1\0\0\0a\u02b3\1\0\0\0b\u02c3\1\0\0\0c\u029a\1\0\0\0d\u02d9"+
		"\1\0\0\0e\u02c5\1\0\0\0f\u02db\1\0\0\0g\u02c6\1\0\0\0g\u0309\1\0\0\0h"+
		"\u02e7\1\0\0\0i\u02e1\1\0\0\0j\u02f6\1\0\0\0k\u02ef\1\0\0\0k\u02ee\1\0"+
		"\0\0l\u02fe\1\0\0\0m\u02f4\1\0\0\0n\u0300\1\0\0\0o\u02ff\1\0\0\0p\u0302"+
		"\1\0\0\0q\u02ff\1\0\0\0r\u0308\1\0\0\0s\u02f9\1\0\0\0t\u030a\1\0\0\0u"+
		"\u029a\1\0\0\0v\u0328\1\0\0\0w\u0107\1\0\0\0w\u020a\1\0\0\0w\u0209\1\0"+
		"\0\0w\u028f\1\0\0\0w\u0303\1\0\0\0w\u0309\1\0\0\0w\u0329\1\0\0\0w\u03a1"+
		"\1\0\0\0w\u03a1\1\0\0\0w\u03a1\1\0\0\0w\u03a1\1\0\0\0w\u03a1\1\0\0\0w"+
		"\u0376\1\0\0\0w\u037f\1\0\0\0w\u0385\1\0\0\0w\u03b1\1\0\0\0w\u03ec\1\0"+
		"\0\0w\u03f2\1\0\0\0w\u03f4\1\0\0\0w\u03f7\1\0\0\0w\u042b\1\0\0\0w\u0448"+
		"\1\0\0\0w\u048e\1\0\0\0w\u04cb\1\0\0\0w\u04e6\1\0\0\0w\u04e7\1\0\0\0w"+
		"\u04f1\1\0\0\0w\u04f5\1\0\0\0w\u052a\1\0\0\0w\u052b\1\0\0\0w\u0537\1\0"+
		"\0\0w\u0553\1\0\0\0x\u03a5\1\0\0\0y\u039a\1\0\0\0z\u03ab\1\0\0\0{\u0329"+
		"\1\0\0\0|\u03d1\1\0\0\0}\u01b5\1\0\0\0}\u01b4\1\0\0\0}\u03e8\1\0\0\0}"+
		"\u0460\1\0\0\0}\u045f\1\0\0\0}\u04a2\1\0\0\0}\u04a1\1\0\0\0}\u050f\1\0"+
		"\0\0}\u050e\1\0\0\0~\u03df\1\0\0\0\177\u03d2\1\0\0\0\177\u0425\1\0\0\0"+
		"\177\u0441\1\0\0\0\177\u0475\1\0\0\0\177\u04dd\1\0\0\0\177\u04df\1\0\0"+
		"\0\u0080\u03e1\1\0\0\0\u0081\u03e0\1\0\0\0\u0082\u03e3\1\0\0\0\u0083\u03d2"+
		"\1\0\0\0\u0084\u03e9\1\0\0\0\u0085\u03e5\1\0\0\0\u0085\u0543\1\0\0\0\u0085"+
		"\u0549\1\0\0\0\u0085\u054d\1\0\0\0\u0086\u03eb\1\0\0\0\u0087\u03e0\1\0"+
		"\0\0\u0088\u03ed\1\0\0\0\u0089\u03e0\1\0\0\0\u0089\u051f\1\0\0\0\u008a"+
		"\u03f3\1\0\0\0\u008b\u03ef\1\0\0\0\u008c\u03f5\1\0\0\0\u008d\u03e0\1\0"+
		"\0\0\u008e\u03f9\1\0\0\0\u008f\u03e0\1\0\0\0\u0090\u0405\1\0\0\0\u0091"+
		"\u03fd\1\0\0\0\u0092\u040f\1\0\0\0\u0093\u0406\1\0\0\0\u0094\u041f\1\0"+
		"\0\0\u0095\u0406\1\0\0\0\u0096\u0421\1\0\0\0\u0097\u03d2\1\0\0\0\u0097"+
		"\u0434\1\0\0\0\u0098\u043b\1\0\0\0\u0099\u03d2\1\0\0\0\u009a\u043d\1\0"+
		"\0\0\u009b\u043c\1\0\0\0\u009c\u0454\1\0\0\0\u009d\u044e\1\0\0\0\u009e"+
		"\u046f\1\0\0\0\u009f\u0456\1\0\0\0\u00a0\u0471\1\0\0\0\u00a1\u043c\1\0"+
		"\0\0\u00a2\u048a\1\0\0\0\u00a3\u047b\1\0\0\0\u00a4\u0496\1\0\0\0\u00a5"+
		"\u0480\1\0\0\0\u00a6\u04b1\1\0\0\0\u00a7\u0498\1\0\0\0\u00a8\u04b3\1\0"+
		"\0\0\u00a9\u04b2\1\0\0\0\u00aa\u04be\1\0\0\0\u00ab\u03d2\1\0\0\0\u00ac"+
		"\u04ca\1\0\0\0\u00ad\u04c7\1\0\0\0\u00ad\u04d5\1\0\0\0\u00ae\u04ce\1\0"+
		"\0\0\u00af\u04c7\1\0\0\0\u00b0\u04dc\1\0\0\0\u00b1\u04cf\1\0\0\0\u00b2"+
		"\u04de\1\0\0\0\u00b3\u04db\1\0\0\0\u00b4\u04e0\1\0\0\0\u00b5\u04c7\1\0"+
		"\0\0\u00b6\u04f2\1\0\0\0\u00b7\u03d2\1\0\0\0\u00b8\u04f6\1\0\0\0\u00b9"+
		"\u03d2\1\0\0\0\u00ba\u0503\1\0\0\0\u00bb\u04fd\1\0\0\0\u00bc\u0522\1\0"+
		"\0\0\u00bd\u0505\1\0\0\0\u00be\u0532\1\0\0\0\u00bf\u051f\1\0\0\0\u00c0"+
		"\u0536\1\0\0\0\u00c1\u0535\1\0\0\0\u00c2\u0538\1\0\0\0\u00c3\u03d2\1\0"+
		"\0\0\u00c4\u053e\1\0\0\0\u00c5\u03d2\1\0\0\0\u00c6\u0544\1\0\0\0\u00c7"+
		"\u03d2\1\0\0\0\u00c8\u054a\1\0\0\0\u00c9\u03d2\1\0\0\0\u00ca\u054e\1\0"+
		"\0\0\u00cb\u03d2\1\0\0\0\u00cc\u0550\1\0\0\0\u00cd\u03d2\1\0\0\0\u00ce"+
		"\u0554\1\0\0\0\u00cf\u0329\1\0\0\0\u00d0\u056c\1\0\0\0\u00d1\u055c\1\0"+
		"\0\0\u00d2\u0572\1\0\0\0\u00d3\u05bb\5\uffff\0\0\u00d4\u0586\1\0\0\0\u00d5"+
		"\u0570\1\0\0\0\u00d6\u058c\1\0\0\0\u00d7\u02ab\1\0\0\0\u00d7\u058a\1\0"+
		"\0\0\u00d7\u05af\1\0\0\0\u00d8\u058e\1\0\0\0\u00d9\u0576\1\0\0\0\u00da"+
		"\u05b7\1\0\0\0\u00db\u05a8\1\0\0\0\u00db\u059c\1\0\0\0\u00db\u059b\1\0"+
		"\0\0\u00dc\u05b9\1\0\0\0\u00dd\u05b8\1\0\0\0\u00dd\u05b1\1\0\0\0\u00dd"+
		"\u05b5\1\0\0\0\u00de\u00e9\3\2\1\0\u00e0\u00e9\3\4\2\0\u00e2\u00e4\5?"+
		"\0\0\u00e4\u00e6\3\0\0\0\u00e6\u00e7\5@\0\0\u00e7\u00e9\1\0\0\0\u00e8"+
		"\u00de\1\0\0\0\u00e8\u00e0\1\0\0\0\u00e8\u00e2\1\0\0\0\u00e9\1\1\0\0\0"+
		"\u00ea\u00eb\3\\.\0\u00eb\3\1\0\0\0\u00ec\u00fd\3\6\3\0\u00ee\u00fd\3"+
		"\16\7\0\u00f0\u00fd\3\26\13\0\u00f2\u00fd\3\32\r\0\u00f4\u00fd\3&\23\0"+
		"\u00f6\u00fd\3\f\6\0\u00f8\u00fd\3.\27\0\u00fa\u00fd\3\62\31\0\u00fc\u00ec"+
		"\1\0\0\0\u00fc\u00ee\1\0\0\0\u00fc\u00f0\1\0\0\0\u00fc\u00f2\1\0\0\0\u00fc"+
		"\u00f4\1\0\0\0\u00fc\u00f6\1\0\0\0\u00fc\u00f8\1\0\0\0\u00fc\u00fa\1\0"+
		"\0\0\u00fd\5\1\0\0\0\u00fe\u0100\5A\0\0\u0100\u0102\3\b\4\0\u0102\u0104"+
		"\5B\0\0\u0104\u0105\3\n\5\0\u0105\7\1\0\0\0\u0106\u0107\3v;\0\u0107\t"+
		"\1\0\0\0\u0108\u0109\3\0\0\0\u0109\13\1\0\0\0\u010a\u010c\5A\0\0\u010c"+
		"\u010e\5B\0\0\u010e\u010f\3\n\5\0\u010f\r\1\0\0\0\u0110\u0112\5\26\0\0"+
		"\u0112\u011a\5C\0\0\u0114\u0116\3\20\b\0\u0116\u0117\5G\0\0\u0117\u0119"+
		"\1\0\0\0\u0118\u0114\1\0\0\0\u0119\u011c\1\0\0\0\u011a\u0118\1\0\0\0\u011a"+
		"\u011b\1\0\0\0\u011b\u011f\1\0\0\0\u011c\u011a\1\0\0\0\u011d\u0120\3\20"+
		"\b\0\u011f\u011d\1\0\0\0\u011f\u0120\1\0\0\0\u0120\u0121\1\0\0\0\u0121"+
		"\u0122\5D\0\0\u0122\17\1\0\0\0\u0123\u0125\3>\37\0\u0125\u0126\3\0\0\0"+
		"\u0126\u012a\1\0\0\0\u0127\u012a\3\22\t\0\u0129\u0123\1\0\0\0\u0129\u0127"+
		"\1\0\0\0\u012a\u012d\1\0\0\0\u012b\u012e\3\24\n\0\u012d\u012b\1\0\0\0"+
		"\u012d\u012e\1\0\0\0\u012e\21\1\0\0\0\u012f\u0132\5\34\0\0\u0131\u012f"+
		"\1\0\0\0\u0131\u0132\1\0\0\0\u0132\u0133\1\0\0\0\u0133\u0134\3\2\1\0\u0134"+
		"\23\1\0\0\0\u0135\u0136\5R\0\0\u0136\25\1\0\0\0\u0137\u0139\5\34\0\0\u0139"+
		"\u013a\3\30\f\0\u013a\27\1\0\0\0\u013b\u013c\3\0\0\0\u013c\31\1\0\0\0"+
		"\u013d\u013f\5\13\0\0\u013f\u0140\3\34\16\0\u0140\33\1\0\0\0\u0141\u0145"+
		"\3 \20\0\u0143\u0146\3\36\17\0\u0145\u0143\1\0\0\0\u0145\u0146\1\0\0\0"+
		"\u0146\35\1\0\0\0\u0147\u014c\3 \20\0\u0149\u014c\3\0\0\0\u014b\u0147"+
		"\1\0\0\0\u014b\u0149\1\0\0\0\u014c\37\1\0\0\0\u014d\u0155\5?\0\0\u014f"+
		"\u0153\3\"\21\0\u0151\u0154\5E\0\0\u0153\u0151\1\0\0\0\u0153\u0154\1\0"+
		"\0\0\u0154\u0156\1\0\0\0\u0155\u014f\1\0\0\0\u0155\u0156\1\0\0\0\u0156"+
		"\u0157\1\0\0\0\u0157\u0158\5@\0\0\u0158!\1\0\0\0\u0159\u0161\3$\22\0\u015b"+
		"\u015d\5E\0\0\u015d\u0160\3$\22\0\u015f\u015b\1\0\0\0\u0160\u0163\1\0"+
		"\0\0\u0161\u015f\1\0\0\0\u0161\u0162\1\0\0\0\u0162#\1\0\0\0\u0163\u0161"+
		"\1\0\0\0\u0164\u0167\3>\37\0\u0166\u0164\1\0\0\0\u0166\u0167\1\0\0\0\u0167"+
		"\u016a\1\0\0\0\u0168\u016b\5>\0\0\u016a\u0168\1\0\0\0\u016a\u016b\1\0"+
		"\0\0\u016b\u016c\1\0\0\0\u016c\u016d\3\0\0\0\u016d%\1\0\0\0\u016e\u0170"+
		"\5\20\0\0\u0170\u0181\5C\0\0\u0172\u017a\3(\24\0\u0174\u0176\5G\0\0\u0176"+
		"\u0179\3(\24\0\u0178\u0174\1\0\0\0\u0179\u017c\1\0\0\0\u017a\u0178\1\0"+
		"\0\0\u017a\u017b\1\0\0\0\u017b\u017f\1\0\0\0\u017c\u017a\1\0\0\0\u017d"+
		"\u0180\5G\0\0\u017f\u017d\1\0\0\0\u017f\u0180\1\0\0\0\u0180\u0182\1\0"+
		"\0\0\u0181\u0172\1\0\0\0\u0181\u0182\1\0\0\0\u0182\u0183\1\0\0\0\u0183"+
		"\u0184\5D\0\0\u0184\'\1\0\0\0\u0185\u0187\3*\25\0\u0187\u0188\3\34\16"+
		"\0\u0188\u018c\1\0\0\0\u0189\u018c\3,\26\0\u018b\u0185\1\0\0\0\u018b\u0189"+
		"\1\0\0\0\u018c)\1\0\0\0\u018d\u018e\5I\0\0\u018e+\1\0\0\0\u018f\u0190"+
		"\3\2\1\0\u0190-\1\0\0\0\u0191\u0193\5\21\0\0\u0193\u0195\5A\0\0\u0195"+
		"\u0197\3\60\30\0\u0197\u0199\5B\0\0\u0199\u019a\3\n\5\0\u019a/\1\0\0\0"+
		"\u019b\u019c\3\0\0\0\u019c\61\1\0\0\0\u019d\u01a1\5\3\0\0\u019f\u01a2"+
		"\5\62\0\0\u01a1\u019f\1\0\0\0\u01a1\u01a2\1\0\0\0\u01a2\u01a8\1\0\0\0"+
		"\u01a3\u01a5\5\62\0\0\u01a5\u01a8\5\3\0\0\u01a7\u019d\1\0\0\0\u01a7\u01a3"+
		"\1\0\0\0\u01a8\u01a9\1\0\0\0\u01a9\u01aa\3\n\5\0\u01aa\63\1\0\0\0\u01ab"+
		"\u01bc\5C\0\0\u01ad\u01b5\3|>\0\u01af\u01b1\5G\0\0\u01b1\u01b4\3|>\0\u01b3"+
		"\u01af\1\0\0\0\u01b4\u01b7\1\0\0\0\u01b5\u01b3\1\0\0\0\u01b5\u01b6\1\0"+
		"\0\0\u01b6\u01ba\1\0\0\0\u01b7\u01b5\1\0\0\0\u01b8\u01bb\5G\0\0\u01ba"+
		"\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb\u01bd\1\0\0\0\u01bc\u01ad\1\0"+
		"\0\0\u01bc\u01bd\1\0\0\0\u01bd\u01be\1\0\0\0\u01be\u01bf\5D\0\0\u01bf"+
		"\65\1\0\0\0\u01c0\u01c7\3:\35\0\u01c2\u01c7\3B!\0\u01c4\u01c7\3F#\0\u01c6"+
		"\u01c0\1\0\0\0\u01c6\u01c2\1\0\0\0\u01c6\u01c4\1\0\0\0\u01c7\67\1\0\0"+
		"\0\u01c8\u01cf\3\66\33\0\u01ca\u01cf\3L&\0\u01cc\u01cf\3P(\0\u01ce\u01c8"+
		"\1\0\0\0\u01ce\u01ca\1\0\0\0\u01ce\u01cc\1\0\0\0\u01cf9\1\0\0\0\u01d0"+
		"\u01e9\5\4\0\0\u01d2\u01ea\3<\36\0\u01d4\u01e5\5?\0\0\u01d6\u01de\3<\36"+
		"\0\u01d8\u01da\5G\0\0\u01da\u01dd\3<\36\0\u01dc\u01d8\1\0\0\0\u01dd\u01e0"+
		"\1\0\0\0\u01de\u01dc\1\0\0\0\u01de\u01df\1\0\0\0\u01df\u01e3\1\0\0\0\u01e0"+
		"\u01de\1\0\0\0\u01e1\u01e4\5G\0\0\u01e3\u01e1\1\0\0\0\u01e3\u01e4\1\0"+
		"\0\0\u01e4\u01e6\1\0\0\0\u01e5\u01d6\1\0\0\0\u01e5\u01e6\1\0\0\0\u01e6"+
		"\u01e7\1\0\0\0\u01e7\u01ea\5@\0\0\u01e9\u01d2\1\0\0\0\u01e9\u01d4\1\0"+
		"\0\0\u01ea;\1\0\0\0\u01eb\u01f5\3>\37\0\u01ed\u01f0\3\0\0\0\u01ef\u01ed"+
		"\1\0\0\0\u01ef\u01f0\1\0\0\0\u01f0\u01f1\1\0\0\0\u01f1\u01f3\58\0\0\u01f3"+
		"\u01f6\3@ \0\u01f5\u01ef\1\0\0\0\u01f5\u01f6\1\0\0\0\u01f6=\1\0\0\0\u01f7"+
		"\u01ff\5I\0\0\u01f9\u01fb\5E\0\0\u01fb\u01fe\5I\0\0\u01fd\u01f9\1\0\0"+
		"\0\u01fe\u0201\1\0\0\0\u01ff\u01fd\1\0\0\0\u01ff\u0200\1\0\0\0\u0200?"+
		"\1\0\0\0\u0201\u01ff\1\0\0\0\u0202\u020a\3v;\0\u0204\u0206\5E\0\0\u0206"+
		"\u0209\3v;\0\u0208\u0204\1\0\0\0\u0209\u020c\1\0\0\0\u020a\u0208\1\0\0"+
		"\0\u020a\u020b\1\0\0\0\u020bA\1\0\0\0\u020c\u020a\1\0\0\0\u020d\u0226"+
		"\5\30\0\0\u020f\u0227\3D\"\0\u0211\u0222\5?\0\0\u0213\u021b\3D\"\0\u0215"+
		"\u0217\5G\0\0\u0217\u021a\3D\"\0\u0219\u0215\1\0\0\0\u021a\u021d\1\0\0"+
		"\0\u021b\u0219\1\0\0\0\u021b\u021c\1\0\0\0\u021c\u0220\1\0\0\0\u021d\u021b"+
		"\1\0\0\0\u021e\u0221\5G\0\0\u0220\u021e\1\0\0\0\u0220\u0221\1\0\0\0\u0221"+
		"\u0223\1\0\0\0\u0222\u0213\1\0\0\0\u0222\u0223\1\0\0\0\u0223\u0224\1\0"+
		"\0\0\u0224\u0227\5@\0\0\u0226\u020f\1\0\0\0\u0226\u0211\1\0\0\0\u0227"+
		"C\1\0\0\0\u0228\u022a\5I\0\0\u022a\u022b\3\0\0\0\u022bE\1\0\0\0\u022c"+
		"\u0245\5\31\0\0\u022e\u0246\3H$\0\u0230\u0241\5?\0\0\u0232\u023a\3H$\0"+
		"\u0234\u0236\5G\0\0\u0236\u0239\3H$\0\u0238\u0234\1\0\0\0\u0239\u023c"+
		"\1\0\0\0\u023a\u0238\1\0\0\0\u023a\u023b\1\0\0\0\u023b\u023f\1\0\0\0\u023c"+
		"\u023a\1\0\0\0\u023d\u0240\5G\0\0\u023f\u023d\1\0\0\0\u023f\u0240\1\0"+
		"\0\0\u0240\u0242\1\0\0\0\u0241\u0232\1\0\0\0\u0241\u0242\1\0\0\0\u0242"+
		"\u0243\1\0\0\0\u0243\u0246\5@\0\0\u0245\u022e\1\0\0\0\u0245\u0230\1\0"+
		"\0\0\u0246G\1\0\0\0\u0247\u0255\3>\37\0\u0249\u024f\3\0\0\0\u024b\u024d"+
		"\58\0\0\u024d\u0250\3@ \0\u024f\u024b\1\0\0\0\u024f\u0250\1\0\0\0\u0250"+
		"\u0256\1\0\0\0\u0251\u0253\58\0\0\u0253\u0256\3@ \0\u0255\u0249\1\0\0"+
		"\0\u0255\u0251\1\0\0\0\u0256I\1\0\0\0\u0257\u0259\3>\37\0\u0259\u025b"+
		"\5=\0\0\u025b\u025c\3@ \0\u025cK\1\0\0\0\u025d\u025f\5\13\0\0\u025f\u0261"+
		"\5I\0\0\u0261\u0265\3\34\16\0\u0263\u0266\3N\'\0\u0265\u0263\1\0\0\0\u0265"+
		"\u0266\1\0\0\0\u0266M\1\0\0\0\u0267\u0268\3\64\32\0\u0268O\1\0\0\0\u0269"+
		"\u026b\5\13\0\0\u026b\u026d\3R)\0\u026d\u026f\3*\25\0\u026f\u0273\3\34"+
		"\16\0\u0271\u0274\3N\'\0\u0273\u0271\1\0\0\0\u0273\u0274\1\0\0\0\u0274"+
		"Q\1\0\0\0\u0275\u0279\5?\0\0\u0277\u027a\5I\0\0\u0279\u0277\1\0\0\0\u0279"+
		"\u027a\1\0\0\0\u027a\u027d\1\0\0\0\u027b\u027e\5\34\0\0\u027d\u027b\1"+
		"\0\0\0\u027d\u027e\1\0\0\0\u027e\u027f\1\0\0\0\u027f\u0281\3T*\0\u0281"+
		"\u0282\5@\0\0\u0282S\1\0\0\0\u0283\u0284\5I\0\0\u0284U\1\0\0\0\u0285\u0292"+
		"\3X,\0\u0287\u0292\3\\.\0\u0289\u0292\3^/\0\u028b\u028d\5?\0\0\u028d\u028f"+
		"\3v;\0\u028f\u0290\5@\0\0\u0290\u0292\1\0\0\0\u0291\u0285\1\0\0\0\u0291"+
		"\u0287\1\0\0\0\u0291\u0289\1\0\0\0\u0291\u028b\1\0\0\0\u0292W\1\0\0\0"+
		"\u0293\u029a\3Z-\0\u0295\u029a\3b\61\0\u0297\u029a\3t:\0\u0299\u0293\1"+
		"\0\0\0\u0299\u0295\1\0\0\0\u0299\u0297\1\0\0\0\u029aY\1\0\0\0\u029b\u02a6"+
		"\5N\0\0\u029d\u02a6\5P\0\0\u029f\u02a6\5O\0\0\u02a1\u02a6\5Q\0\0\u02a3"+
		"\u02a6\5R\0\0\u02a5\u029b\1\0\0\0\u02a5\u029d\1\0\0\0\u02a5\u029f\1\0"+
		"\0\0\u02a5\u02a1\1\0\0\0\u02a5\u02a3\1\0\0\0\u02a6[\1\0\0\0\u02a7\u02a9"+
		"\4.\0\0\u02a9\u02ab\3\u00d6k\0\u02ab\u02ac\5F\0\0\u02ac\u02ae\1\0\0\0"+
		"\u02ad\u02a7\1\0\0\0\u02ad\u02ae\1\0\0\0\u02ae\u02af\1\0\0\0\u02af\u02b0"+
		"\5I\0\0\u02b0]\1\0\0\0\u02b1\u02b3\3`\60\0\u02b3\u02b5\5F\0\0\u02b5\u02b6"+
		"\3*\25\0\u02b6_\1\0\0\0\u02b7\u02c2\3\2\1\0\u02b9\u02bb\5?\0\0\u02bb\u02bd"+
		"\5\34\0\0\u02bd\u02bf\3\2\1\0\u02bf\u02c0\5@\0\0\u02c0\u02c2\1\0\0\0\u02c1"+
		"\u02b7\1\0\0\0\u02c1\u02b9\1\0\0\0\u02c2a\1\0\0\0\u02c3\u02c5\3d\62\0"+
		"\u02c5\u02c6\3f\63\0\u02c6c\1\0\0\0\u02c7\u02da\3\16\7\0\u02c9\u02da\3"+
		"\6\3\0\u02cb\u02cd\5A\0\0\u02cd\u02cf\5>\0\0\u02cf\u02d1\5B\0\0\u02d1"+
		"\u02da\3\n\5\0\u02d3\u02da\3\f\6\0\u02d5\u02da\3.\27\0\u02d7\u02da\3\2"+
		"\1\0\u02d9\u02c7\1\0\0\0\u02d9\u02c9\1\0\0\0\u02d9\u02cb\1\0\0\0\u02d9"+
		"\u02d3\1\0\0\0\u02d9\u02d5\1\0\0\0\u02d9\u02d7\1\0\0\0\u02dae\1\0\0\0"+
		"\u02db\u02e3\5C\0\0\u02dd\u02e1\3h\64\0\u02df\u02e2\5E\0\0\u02e1\u02df"+
		"\1\0\0\0\u02e1\u02e2\1\0\0\0\u02e2\u02e4\1\0\0\0\u02e3\u02dd\1\0\0\0\u02e3"+
		"\u02e4\1\0\0\0\u02e4\u02e5\1\0\0\0\u02e5\u02e6\5D\0\0\u02e6g\1\0\0\0\u02e7"+
		"\u02ef\3j\65\0\u02e9\u02eb\5E\0\0\u02eb\u02ee\3j\65\0\u02ed\u02e9\1\0"+
		"\0\0\u02ee\u02f1\1\0\0\0\u02ef\u02ed\1\0\0\0\u02ef\u02f0\1\0\0\0\u02f0"+
		"i\1\0\0\0\u02f1\u02ef\1\0\0\0\u02f2\u02f4\3l\66\0\u02f4\u02f5\5H\0\0\u02f5"+
		"\u02f7\1\0\0\0\u02f6\u02f2\1\0\0\0\u02f6\u02f7\1\0\0\0\u02f7\u02f8\1\0"+
		"\0\0\u02f8\u02f9\3r9\0\u02f9k\1\0\0\0\u02fa\u02ff\3n\67\0\u02fc\u02ff"+
		"\3p8\0\u02fe\u02fa\1\0\0\0\u02fe\u02fc\1\0\0\0\u02ffm\1\0\0\0\u0300\u0301"+
		"\5I\0\0\u0301o\1\0\0\0\u0302\u0303\3v;\0\u0303q\1\0\0\0\u0304\u0309\3"+
		"v;\0\u0306\u0309\3f\63\0\u0308\u0304\1\0\0\0\u0308\u0306\1\0\0\0\u0309"+
		"s\1\0\0\0\u030a\u030c\3\32\r\0\u030c\u030d\3N\'\0\u030du\1\0\0\0\u030e"+
		"\u031e\6;\uffff\0\u0310\u031f\5\32\0\0\u0312\u031f\5\33\0\0\u0314\u031f"+
		"\59\0\0\u0316\u031f\5!\0\0\u0318\u031f\5\34\0\0\u031a\u031f\5\37\0\0\u031c"+
		"\u031f\5\62\0\0\u031e\u0310\1\0\0\0\u031e\u0312\1\0\0\0\u031e\u0314\1"+
		"\0\0\0\u031e\u0316\1\0\0\0\u031e\u0318\1\0\0\0\u031e\u031a\1\0\0\0\u031e"+
		"\u031c\1\0\0\0\u031f\u0320\1\0\0\0\u0320\u0329\3v;\0\u0322\u0329\3V+\0"+
		"\u0324\u0329\3z=\0\u0326\u0329\3\u00ceg\0\u0328\u030e\1\0\0\0\u0328\u0322"+
		"\1\0\0\0\u0328\u0324\1\0\0\0\u0328\u0326\1\0\0\0\u0329\u03a2\1\0\0\0\u032a"+
		"\u033a\4;\1\1\u032c\u033b\5\34\0\0\u032e\u033b\5\35\0\0\u0330\u033b\5"+
		"\36\0\0\u0332\u033b\5\"\0\0\u0334\u033b\5#\0\0\u0336\u033b\5\37\0\0\u0338"+
		"\u033b\5$\0\0\u033a\u032c\1\0\0\0\u033a\u032e\1\0\0\0\u033a\u0330\1\0"+
		"\0\0\u033a\u0332\1\0\0\0\u033a\u0334\1\0\0\0\u033a\u0336\1\0\0\0\u033a"+
		"\u0338\1\0\0\0\u033b\u033c\1\0\0\0\u033c\u03a1\3v;\0\u033e\u0348\4;\2"+
		"\1\u0340\u0349\5\32\0\0\u0342\u0349\5\33\0\0\u0344\u0349\5 \0\0\u0346"+
		"\u0349\5!\0\0\u0348\u0340\1\0\0\0\u0348\u0342\1\0\0\0\u0348\u0344\1\0"+
		"\0\0\u0348\u0346\1\0\0\0\u0349\u034a\1\0\0\0\u034a\u03a1\3v;\0\u034c\u035a"+
		"\4;\3\1\u034e\u035b\5\65\0\0\u0350\u035b\5:\0\0\u0352\u035b\5\66\0\0\u0354"+
		"\u035b\5;\0\0\u0356\u035b\5\67\0\0\u0358\u035b\5<\0\0\u035a\u034e\1\0"+
		"\0\0\u035a\u0350\1\0\0\0\u035a\u0352\1\0\0\0\u035a\u0354\1\0\0\0\u035a"+
		"\u0356\1\0\0\0\u035a\u0358\1\0\0\0\u035b\u035c\1\0\0\0\u035c\u03a1\3v"+
		";\0\u035e\u0360\4;\4\1\u0360\u0362\5\60\0\0\u0362\u03a1\3v;\0\u0364\u0366"+
		"\4;\5\1\u0366\u0368\5\61\0\0\u0368\u03a1\3v;\0\u036a\u036c\4;\6\1\u036c"+
		"\u036e\5F\0\0\u036e\u03a1\5I\0\0\u0370\u0372\4;\7\1\u0372\u0374\5A\0\0"+
		"\u0374\u0376\3v;\0\u0376\u0377\5B\0\0\u0377\u03a1\1\0\0\0\u0378\u037a"+
		"\4;\b\1\u037a\u037e\5A\0\0\u037c\u037f\3v;\0\u037e\u037c\1\0\0\0\u037e"+
		"\u037f\1\0\0\0\u037f\u0380\1\0\0\0\u0380\u0384\5H\0\0\u0382\u0385\3v;"+
		"\0\u0384\u0382\1\0\0\0\u0384\u0385\1\0\0\0\u0385\u0386\1\0\0\0\u0386\u03a1"+
		"\5B\0\0\u0388\u038a\4;\t\1\u038a\u038c\5F\0\0\u038c\u038e\5?\0\0\u038e"+
		"\u0390\3\0\0\0\u0390\u0391\5@\0\0\u0391\u03a1\1\0\0\0\u0392\u0394\4;\n"+
		"\1\u0394\u039c\5?\0\0\u0396\u039a\3x<\0\u0398\u039b\5E\0\0\u039a\u0398"+
		"\1\0\0\0\u039a\u039b\1\0\0\0\u039b\u039d\1\0\0\0\u039c\u0396\1\0\0\0\u039c"+
		"\u039d\1\0\0\0\u039d\u039e\1\0\0\0\u039e\u03a1\5@\0\0\u03a0\u032a\1\0"+
		"\0\0\u03a0\u033e\1\0\0\0\u03a0\u034c\1\0\0\0\u03a0\u035e\1\0\0\0\u03a0"+
		"\u0364\1\0\0\0\u03a0\u036a\1\0\0\0\u03a0\u0370\1\0\0\0\u03a0\u0378\1\0"+
		"\0\0\u03a0\u0388\1\0\0\0\u03a0\u0392\1\0\0\0\u03a1\u03a4\1\0\0\0\u03a2"+
		"\u03a0\1\0\0\0\u03a2\u03a3\1\0\0\0\u03a3w\1\0\0\0\u03a4\u03a2\1\0\0\0"+
		"\u03a5\u03a9\3@ \0\u03a7\u03aa\5>\0\0\u03a9\u03a7\1\0\0\0\u03a9\u03aa"+
		"\1\0\0\0\u03aay\1\0\0\0\u03ab\u03ad\3\0\0\0\u03ad\u03af\5?\0\0\u03af\u03b1"+
		"\3v;\0\u03b1\u03b2\5@\0\0\u03b2{\1\0\0\0\u03b3\u03d2\3\66\33\0\u03b5\u03d2"+
		"\3\u0082A\0\u03b7\u03d2\3~?\0\u03b9\u03d2\3\u00b6[\0\u03bb\u03d2\3\u00c2"+
		"a\0\u03bd\u03d2\3\u00c4b\0\u03bf\u03d2\3\u00c6c\0\u03c1\u03d2\3\u00c8"+
		"d\0\u03c3\u03d2\3\u00cae\0\u03c5\u03d2\3\64\32\0\u03c7\u03d2\3\u0096K"+
		"\0\u03c9\u03d2\3\u0098L\0\u03cb\u03d2\3\u00b8\\\0\u03cd\u03d2\3\u00aa"+
		"U\0\u03cf\u03d2\3\u00ccf\0\u03d1\u03b3\1\0\0\0\u03d1\u03b5\1\0\0\0\u03d1"+
		"\u03b7\1\0\0\0\u03d1\u03b9\1\0\0\0\u03d1\u03bb\1\0\0\0\u03d1\u03bd\1\0"+
		"\0\0\u03d1\u03bf\1\0\0\0\u03d1\u03c1\1\0\0\0\u03d1\u03c3\1\0\0\0\u03d1"+
		"\u03c5\1\0\0\0\u03d1\u03c7\1\0\0\0\u03d1\u03c9\1\0\0\0\u03d1\u03cb\1\0"+
		"\0\0\u03d1\u03cd\1\0\0\0\u03d1\u03cf\1\0\0\0\u03d2}\1\0\0\0\u03d3\u03e0"+
		"\3\u0080@\0\u03d5\u03e0\3\u0086C\0\u03d7\u03e0\3\u0088D\0\u03d9\u03e0"+
		"\3\u008cF\0\u03db\u03e0\3\u008eG\0\u03dd\u03e0\3J%\0\u03df\u03d3\1\0\0"+
		"\0\u03df\u03d5\1\0\0\0\u03df\u03d7\1\0\0\0\u03df\u03d9\1\0\0\0\u03df\u03db"+
		"\1\0\0\0\u03df\u03dd\1\0\0\0\u03e0\177\1\0\0\0\u03e1\u03e2\1\0\0\0\u03e2"+
		"\u0081\1\0\0\0\u03e3\u03e5\3\u0084B\0\u03e5\u03e7\5H\0\0\u03e7\u03e8\3"+
		"|>\0\u03e8\u0083\1\0\0\0\u03e9\u03ea\5I\0\0\u03ea\u0085\1\0\0\0\u03eb"+
		"\u03ec\3v;\0\u03ec\u0087\1\0\0\0\u03ed\u03ef\3\u008aE\0\u03ef\u03f1\5"+
		"\62\0\0\u03f1\u03f2\3v;\0\u03f2\u0089\1\0\0\0\u03f3\u03f4\3v;\0\u03f4"+
		"\u008b\1\0\0\0\u03f5\u03f7\3v;\0\u03f7\u03f8\7\0\0\0\u03f8\u008d\1\0\0"+
		"\0\u03f9\u03fb\3@ \0\u03fb\u03fd\3\u0090H\0\u03fd\u03fe\3@ \0\u03fe\u008f"+
		"\1\0\0\0\u03ff\u0406\3\u0092I\0\u0401\u0406\3\u0094J\0\u0403\u0406\58"+
		"\0\0\u0405\u03ff\1\0\0\0\u0405\u0401\1\0\0\0\u0405\u0403\1\0\0\0\u0406"+
		"\u0091\1\0\0\0\u0407\u0410\5%\0\0\u0409\u0410\5&\0\0\u040b\u0410\5+\0"+
		"\0\u040d\u0410\5,\0\0\u040f\u0407\1\0\0\0\u040f\u0409\1\0\0\0\u040f\u040b"+
		"\1\0\0\0\u040f\u040d\1\0\0\0\u0410\u0093\1\0\0\0\u0411\u0420\5\'\0\0\u0413"+
		"\u0420\5(\0\0\u0415\u0420\5,\0\0\u0417\u0420\5-\0\0\u0419\u0420\5.\0\0"+
		"\u041b\u0420\5*\0\0\u041d\u0420\5/\0\0\u041f\u0411\1\0\0\0\u041f\u0413"+
		"\1\0\0\0\u041f\u0415\1\0\0\0\u041f\u0417\1\0\0\0\u041f\u0419\1\0\0\0\u041f"+
		"\u041b\1\0\0\0\u041f\u041d\1\0\0\0\u0420\u0095\1\0\0\0\u0421\u0427\5\16"+
		"\0\0\u0423\u0425\3~?\0\u0425\u0426\5G\0\0\u0426\u0428\1\0\0\0\u0427\u0423"+
		"\1\0\0\0\u0427\u0428\1\0\0\0\u0428\u0429\1\0\0\0\u0429\u042b\3v;\0\u042b"+
		"\u0435\3\64\32\0\u042d\u0433\5\b\0\0\u042f\u0434\3\u0096K\0\u0431\u0434"+
		"\3\64\32\0\u0433\u042f\1\0\0\0\u0433\u0431\1\0\0\0\u0434\u0436\1\0\0\0"+
		"\u0435\u042d\1\0\0\0\u0435\u0436\1\0\0\0\u0436\u0097\1\0\0\0\u0437\u043c"+
		"\3\u009aM\0\u0439\u043c\3\u00a0P\0\u043b\u0437\1\0\0\0\u043b\u0439\1\0"+
		"\0\0\u043c\u0099\1\0\0\0\u043d\u0443\5\27\0\0\u043f\u0441\3~?\0\u0441"+
		"\u0442\5G\0\0\u0442\u0444\1\0\0\0\u0443\u043f\1\0\0\0\u0443\u0444\1\0"+
		"\0\0\u0444\u0447\1\0\0\0\u0445\u0448\3v;\0\u0447\u0445\1\0\0\0\u0447\u0448"+
		"\1\0\0\0\u0448\u0449\1\0\0\0\u0449\u044f\5C\0\0\u044b\u044e\3\u009cN\0"+
		"\u044d\u044b\1\0\0\0\u044e\u0451\1\0\0\0\u044f\u044d\1\0\0\0\u044f\u0450"+
		"\1\0\0\0\u0450\u0452\1\0\0\0\u0451\u044f\1\0\0\0\u0452\u0453\5D\0\0\u0453"+
		"\u009b\1\0\0\0\u0454\u0456\3\u009eO\0\u0456\u0467\5H\0\0\u0458\u0460\3"+
		"|>\0\u045a\u045c\5G\0\0\u045c\u045f\3|>\0\u045e\u045a\1\0\0\0\u045f\u0462"+
		"\1\0\0\0\u0460\u045e\1\0\0\0\u0460\u0461\1\0\0\0\u0461\u0465\1\0\0\0\u0462"+
		"\u0460\1\0\0\0\u0463\u0466\5G\0\0\u0465\u0463\1\0\0\0\u0465\u0466\1\0"+
		"\0\0\u0466\u0468\1\0\0\0\u0467\u0458\1\0\0\0\u0467\u0468\1\0\0\0\u0468"+
		"\u009d\1\0\0\0\u0469\u046b\5\2\0\0\u046b\u0470\3@ \0\u046d\u0470\5\6\0"+
		"\0\u046f\u0469\1\0\0\0\u046f\u046d\1\0\0\0\u0470\u009f\1\0\0\0\u0471\u0477"+
		"\5\27\0\0\u0473\u0475\3~?\0\u0475\u0476\5G\0\0\u0476\u0478\1\0\0\0\u0477"+
		"\u0473\1\0\0\0\u0477\u0478\1\0\0\0\u0478\u0479\1\0\0\0\u0479\u047b\3\u00a2"+
		"Q\0\u047b\u0481\5C\0\0\u047d\u0480\3\u00a4R\0\u047f\u047d\1\0\0\0\u0480"+
		"\u0483\1\0\0\0\u0481\u047f\1\0\0\0\u0481\u0482\1\0\0\0\u0482\u0484\1\0"+
		"\0\0\u0483\u0481\1\0\0\0\u0484\u0485\5D\0\0\u0485\u00a1\1\0\0\0\u0486"+
		"\u0488\5I\0\0\u0488\u048b\5=\0\0\u048a\u0486\1\0\0\0\u048a\u048b\1\0\0"+
		"\0\u048b\u048c\1\0\0\0\u048c\u048e\3v;\0\u048e\u0490\5F\0\0\u0490\u0492"+
		"\5?\0\0\u0492\u0494\5\30\0\0\u0494\u0495\5@\0\0\u0495\u00a3\1\0\0\0\u0496"+
		"\u0498\3\u00a6S\0\u0498\u04a9\5H\0\0\u049a\u04a2\3|>\0\u049c\u049e\5G"+
		"\0\0\u049e\u04a1\3|>\0\u04a0\u049c\1\0\0\0\u04a1\u04a4\1\0\0\0\u04a2\u04a0"+
		"\1\0\0\0\u04a2\u04a3\1\0\0\0\u04a3\u04a7\1\0\0\0\u04a4\u04a2\1\0\0\0\u04a5"+
		"\u04a8\5G\0\0\u04a7\u04a5\1\0\0\0\u04a7\u04a8\1\0\0\0\u04a8\u04aa\1\0"+
		"\0\0\u04a9\u049a\1\0\0\0\u04a9\u04aa\1\0\0\0\u04aa\u00a5\1\0\0\0\u04ab"+
		"\u04ad\5\2\0\0\u04ad\u04b2\3\u00a8T\0\u04af\u04b2\5\6\0\0\u04b1\u04ab"+
		"\1\0\0\0\u04b1\u04af\1\0\0\0\u04b2\u00a7\1\0\0\0\u04b3\u04bb\3\0\0\0\u04b5"+
		"\u04b7\5E\0\0\u04b7\u04ba\3\0\0\0\u04b9\u04b5\1\0\0\0\u04ba\u04bd\1\0"+
		"\0\0\u04bb\u04b9\1\0\0\0\u04bb\u04bc\1\0\0\0\u04bc\u00a9\1\0\0\0\u04bd"+
		"\u04bb\1\0\0\0\u04be\u04c6\5\n\0\0\u04c0\u04c7\3\u00acV\0\u04c2\u04c7"+
		"\3\u00aeW\0\u04c4\u04c7\3\u00b4Z\0\u04c6\u04c0\1\0\0\0\u04c6\u04c2\1\0"+
		"\0\0\u04c6\u04c4\1\0\0\0\u04c6\u04c7\1\0\0\0\u04c7\u04c8\1\0\0\0\u04c8"+
		"\u04c9\3\64\32\0\u04c9\u00ab\1\0\0\0\u04ca\u04cb\3v;\0\u04cb\u00ad\1\0"+
		"\0\0\u04cc\u04cf\3\u00b0X\0\u04ce\u04cc\1\0\0\0\u04ce\u04cf\1\0\0\0\u04cf"+
		"\u04d0\1\0\0\0\u04d0\u04d4\5G\0\0\u04d2\u04d5\3\u00acV\0\u04d4\u04d2\1"+
		"\0\0\0\u04d4\u04d5\1\0\0\0\u04d5\u04d6\1\0\0\0\u04d6\u04da\5G\0\0\u04d8"+
		"\u04db\3\u00b2Y\0\u04da\u04d8\1\0\0\0\u04da\u04db\1\0\0\0\u04db\u00af"+
		"\1\0\0\0\u04dc\u04dd\3~?\0\u04dd\u00b1\1\0\0\0\u04de\u04df\3~?\0\u04df"+
		"\u00b3\1\0\0\0\u04e0\u04e6\3v;\0\u04e2\u04e4\5E\0\0\u04e4\u04e7\3v;\0"+
		"\u04e6\u04e2\1\0\0\0\u04e6\u04e7\1\0\0\0\u04e7\u04ec\1\0\0\0\u04e8\u04ed"+
		"\58\0\0\u04ea\u04ed\5=\0\0\u04ec\u04e8\1\0\0\0\u04ec\u04ea\1\0\0\0\u04ed"+
		"\u04ee\1\0\0\0\u04ee\u04f0\5\23\0\0\u04f0\u04f1\3v;\0\u04f1\u00b5\1\0"+
		"\0\0\u04f2\u04f4\5\f\0\0\u04f4\u04f5\3v;\0\u04f5\u00b7\1\0\0\0\u04f6\u04f8"+
		"\5\25\0\0\u04f8\u04fe\5C\0\0\u04fa\u04fd\3\u00ba]\0\u04fc\u04fa\1\0\0"+
		"\0\u04fd\u0500\1\0\0\0\u04fe\u04fc\1\0\0\0\u04fe\u04ff\1\0\0\0\u04ff\u0501"+
		"\1\0\0\0\u0500\u04fe\1\0\0\0\u0501\u0502\5D\0\0\u0502\u00b9\1\0\0\0\u0503"+
		"\u0505\3\u00bc^\0\u0505\u0516\5H\0\0\u0507\u050f\3|>\0\u0509\u050b\5G"+
		"\0\0\u050b\u050e\3|>\0\u050d\u0509\1\0\0\0\u050e\u0511\1\0\0\0\u050f\u050d"+
		"\1\0\0\0\u050f\u0510\1\0\0\0\u0510\u0514\1\0\0\0\u0511\u050f\1\0\0\0\u0512"+
		"\u0515\5G\0\0\u0514\u0512\1\0\0\0\u0514\u0515\1\0\0\0\u0515\u0517\1\0"+
		"\0\0\u0516\u0507\1\0\0\0\u0516\u0517\1\0\0\0\u0517\u00bb\1\0\0\0\u0518"+
		"\u051e\5\2\0\0\u051a\u051f\3\u0088D\0\u051c\u051f\3\u00be_\0\u051e\u051a"+
		"\1\0\0\0\u051e\u051c\1\0\0\0\u051f\u0523\1\0\0\0\u0520\u0523\5\6\0\0\u0522"+
		"\u0518\1\0\0\0\u0522\u0520\1\0\0\0\u0523\u00bd\1\0\0\0\u0524\u052a\3v"+
		";\0\u0526\u0528\5E\0\0\u0528\u052b\3v;\0\u052a\u0526\1\0\0\0\u052a\u052b"+
		"\1\0\0\0\u052b\u0530\1\0\0\0\u052c\u0531\58\0\0\u052e\u0531\5=\0\0\u0530"+
		"\u052c\1\0\0\0\u0530\u052e\1\0\0\0\u0531\u0533\1\0\0\0\u0532\u0524\1\0"+
		"\0\0\u0532\u0533\1\0\0\0\u0533\u0534\1\0\0\0\u0534\u0535\3\u00c0`\0\u0535"+
		"\u00bf\1\0\0\0\u0536\u0537\3v;\0\u0537\u00c1\1\0\0\0\u0538\u053c\5\24"+
		"\0\0\u053a\u053d\3@ \0\u053c\u053a\1\0\0\0\u053c\u053d\1\0\0\0\u053d\u00c3"+
		"\1\0\0\0\u053e\u0542\5\1\0\0\u0540\u0543\3\u0084B\0\u0542\u0540\1\0\0"+
		"\0\u0542\u0543\1\0\0\0\u0543\u00c5\1\0\0\0\u0544\u0548\5\5\0\0\u0546\u0549"+
		"\3\u0084B\0\u0548\u0546\1\0\0\0\u0548\u0549\1\0\0\0\u0549\u00c7\1\0\0"+
		"\0\u054a\u054c\5\r\0\0\u054c\u054d\3\u0084B\0\u054d\u00c9\1\0\0\0\u054e"+
		"\u054f\5\t\0\0\u054f\u00cb\1\0\0\0\u0550\u0552\5\7\0\0\u0552\u0553\3v"+
		";\0\u0553\u00cd\1\0\0\0\u0554\u0556\5I\0\0\u0556\u055e\5?\0\0\u0558\u055c"+
		"\3\u00d0h\0\u055a\u055d\5E\0\0\u055c\u055a\1\0\0\0\u055c\u055d\1\0\0\0"+
		"\u055d\u055f\1\0\0\0\u055e\u0558\1\0\0\0\u055e\u055f\1\0\0\0\u055f\u0560"+
		"\1\0\0\0\u0560\u0561\5@\0\0\u0561\u00cf\1\0\0\0\u0562\u0568\3\0\0\0\u0564"+
		"\u0566\5E\0\0\u0566\u0569\3@ \0\u0568\u0564\1\0\0\0\u0568\u0569\1\0\0"+
		"\0\u0569\u056d\1\0\0\0\u056a\u056d\3@ \0\u056c\u0562\1\0\0\0\u056c\u056a"+
		"\1\0\0\0\u056d\u00d1\1\0\0\0\u056e\u0570\3\u00d4j\0\u0570\u0571\5G\0\0"+
		"\u0571\u0573\1\0\0\0\u0572\u056e\1\0\0\0\u0572\u0573\1\0\0\0\u0573\u057a"+
		"\1\0\0\0\u0574\u0576\3\u00d8l\0\u0576\u0577\5G\0\0\u0577\u0579\1\0\0\0"+
		"\u0578\u0574\1\0\0\0\u0579\u057c\1\0\0\0\u057a\u0578\1\0\0\0\u057a\u057b"+
		"\1\0\0\0\u057b\u0583\1\0\0\0\u057c\u057a\1\0\0\0\u057d\u057f\38\34\0\u057f"+
		"\u0580\5G\0\0\u0580\u0582\1\0\0\0\u0581\u057d\1\0\0\0\u0582\u0585\1\0"+
		"\0\0\u0583\u0581\1\0\0\0\u0583\u0584\1\0\0\0\u0584\u00d3\1\0\0\0\u0585"+
		"\u0583\1\0\0\0\u0586\u0588\5\22\0\0\u0588\u058a\3\u00d6k\0\u058a\u058b"+
		"\6j\uffff\0\u058b\u00d5\1\0\0\0\u058c\u058d\5I\0\0\u058d\u00d7\1\0\0\0"+
		"\u058e\u05a7\5\17\0\0\u0590\u05a8\3\u00dam\0\u0592\u05a3\5?\0\0\u0594"+
		"\u059c\3\u00dam\0\u0596\u0598\5G\0\0\u0598\u059b\3\u00dam\0\u059a\u0596"+
		"\1\0\0\0\u059b\u059e\1\0\0\0\u059c\u059a\1\0\0\0\u059c\u059d\1\0\0\0\u059d"+
		"\u05a1\1\0\0\0\u059e\u059c\1\0\0\0\u059f\u05a2\5G\0\0\u05a1\u059f\1\0"+
		"\0\0\u05a1\u05a2\1\0\0\0\u05a2\u05a4\1\0\0\0\u05a3\u0594\1\0\0\0\u05a3"+
		"\u05a4\1\0\0\0\u05a4\u05a5\1\0\0\0\u05a5\u05a8\5@\0\0\u05a7\u0590\1\0"+
		"\0\0\u05a7\u0592\1\0\0\0\u05a8\u00d9\1\0\0\0\u05a9\u05ab\5F\0\0\u05ab"+
		"\u05b8\3\u00dcn\0\u05ad\u05af\3\u00d6k\0\u05af\u05b1\3\u00dcn\0\u05b1"+
		"\u05b2\6m\uffff\0\u05b2\u05b8\1\0\0\0\u05b3\u05b5\3\u00dcn\0\u05b5\u05b6"+
		"\6m\uffff\0\u05b6\u05b8\1\0\0\0\u05b7\u05a9\1\0\0\0\u05b7\u05ad\1\0\0"+
		"\0\u05b7\u05b3\1\0\0\0\u05b8\u00db\1\0\0\0\u05b9\u05ba\5R\0\0\u05ba\u00dd"+
		"\1\0\0\0}\u00e8\1\u00fc\1\u011a\1\u011f\1\u0129\1\u012d\1\u0131\1\u0145"+
		"\1\u014b\1\u0153\1\u0155\1\u0161\1\u0166\1\u016a\1\u017a\1\u017f\1\u0181"+
		"\1\u018b\1\u01a1\1\u01a7\1\u01b5\1\u01ba\1\u01bc\1\u01c6\1\u01ce\1\u01de"+
		"\1\u01e3\1\u01e5\1\u01e9\1\u01ef\1\u01f5\1\u01ff\1\u020a\1\u021b\1\u0220"+
		"\1\u0222\1\u0226\1\u023a\1\u023f\1\u0241\1\u0245\1\u024f\1\u0255\1\u0265"+
		"\1\u0273\1\u0279\1\u027d\1\u0291\1\u0299\1\u02a5\1\u02ad\1\u02c1\1\u02d9"+
		"\1\u02e1\1\u02e3\1\u02ef\1\u02f6\1\u02fe\1\u0308\1\u031e\1\u0328\1\u033a"+
		"\1\u0348\1\u035a\1\u037e\1\u0384\1\u039a\1\u039c\1\u03a0\1\u03a2\1\u03a9"+
		"\1\u03d1\1\u03df\1\u0405\1\u040f\1\u041f\1\u0427\1\u0433\1\u0435\1\u043b"+
		"\1\u0443\1\u0447\1\u044f\1\u0460\1\u0465\1\u0467\1\u046f\1\u0477\1\u0481"+
		"\1\u048a\1\u04a2\1\u04a7\1\u04a9\1\u04b1\1\u04bb\1\u04c6\1\u04ce\1\u04d4"+
		"\1\u04da\1\u04e6\1\u04ec\1\u04fe\1\u050f\1\u0514\1\u0516\1\u051e\1\u0522"+
		"\1\u052a\1\u0530\1\u0532\1\u053c\1\u0542\1\u0548\1\u055c\1\u055e\1\u0568"+
		"\1\u056c\1\u0572\1\u057a\1\u0583\1\u059c\1\u05a1\1\u05a3\1\u05a7\1\u05b7"+
		"\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}