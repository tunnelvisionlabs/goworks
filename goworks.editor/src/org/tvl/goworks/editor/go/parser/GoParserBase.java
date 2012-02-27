// $ANTLR ANTLRVersion> GoParserBase.java generatedTimestamp>
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

public class GoParserBase extends Parser<Token> {
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
		"'break'", "'case'", "'chan'", "'const'", "'continue'", "'default'", "'defer'", 
		"'else'", "'fallthrough'", "'for'", "'func'", "'go'", "'goto'", "'if'", 
		"'import'", "'interface'", "'map'", "'package'", "'range'", "'return'", 
		"'select'", "'struct'", "'switch'", "'type'", "'var'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'&'", "'|'", "'^'", "'<<'", "'>>'", "'&^'", "'+='", "'-='", 
		"'*='", "'/='", "'%='", "'&='", "'|='", "'^='", "'<<='", "'>>='", "'&^='", 
		"'&&'", "'||'", "'<-'", "'++'", "'--'", "'=='", "'<'", "'>'", "'='", "'!'", 
		"'!='", "'<='", "'>='", "':='", "'...'", "'('", "')'", "'['", "']'", "'{'", 
		"'}'", "','", "'.'", "';'", "':'", "IDENTIFIER", "WS", "NEWLINE", "COMMENT", 
		"ML_COMMENT", "INT_LITERAL", "IMAGINARY_LITERAL", "FLOAT_LITERAL", "CharLiteral", 
		"StringLiteral", "ANYCHAR"
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
	public String getGrammarFileName() { return "GoParserBase.g4"; }

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

	public GoParserBase(TokenStream<? extends Token> input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class TypeContext extends ParserRuleContext<Token> {
		public TypeNameContext name;
		public TypeLiteralContext lit;
		public Token lp;
		public TypeContext t;
		public Token rp;
		public TypeNameContext typeName() {
		    return getRuleContext(TypeNameContext.class,0);
		}
		public TypeLiteralContext typeLiteral() {
		    return getRuleContext(TypeLiteralContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitType(this);
			else return null;
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, 0);
		enterRule(_localctx, RULE_type);
		try {
			setState(232);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(222); _localctx.name = typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(224); _localctx.lit = typeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(226); _localctx.lp = match(LeftParen);
					setState(228); _localctx.t = type();
					setState(230); _localctx.rp = match(RightParen);
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

	public static class TypeNameContext extends ParserRuleContext<Token> {
		public QualifiedIdentifierContext qid;
		public QualifiedIdentifierContext qualifiedIdentifier() {
		    return getRuleContext(QualifiedIdentifierContext.class,0);
		}
		public TypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeName(this);
			else return null;
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, 2);
		enterRule(_localctx, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); _localctx.qid = qualifiedIdentifier();
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

	public static class TypeLiteralContext extends ParserRuleContext<Token> {
		public ArrayTypeContext arrType;
		public StructTypeContext strType;
		public PointerTypeContext ptrType;
		public FunctionTypeContext fnType;
		public InterfaceTypeContext ifaceType;
		public SliceTypeContext slcType;
		public MapTypeContext maptype;
		public ChannelTypeContext chanType;
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
		public TypeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeLiteral(this);
			else return null;
		}
	}

	public final TypeLiteralContext typeLiteral() throws RecognitionException {
		TypeLiteralContext _localctx = new TypeLiteralContext(_ctx, 4);
		enterRule(_localctx, RULE_typeLiteral);
		try {
			setState(252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(236); _localctx.arrType = arrayType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(238); _localctx.strType = structType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(240); _localctx.ptrType = pointerType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(242); _localctx.fnType = functionType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(244); _localctx.ifaceType = interfaceType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(246); _localctx.slcType = sliceType();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(248); _localctx.maptype = mapType();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(250); _localctx.chanType = channelType();
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

	public static class ArrayTypeContext extends ParserRuleContext<Token> {
		public ArrayLengthContext len;
		public ElementTypeContext elemType;
		public ArrayLengthContext arrayLength() {
		    return getRuleContext(ArrayLengthContext.class,0);
		}
		public ElementTypeContext elementType() {
		    return getRuleContext(ElementTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitArrayType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitArrayType(this);
			else return null;
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, 6);
		enterRule(_localctx, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254); match(LeftBrack);
			setState(256); _localctx.len = arrayLength();
			setState(258); match(RightBrack);
			setState(260); _localctx.elemType = elementType();
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

	public static class ArrayLengthContext extends ParserRuleContext<Token> {
		public ExpressionContext expr;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayLengthContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterArrayLength(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitArrayLength(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitArrayLength(this);
			else return null;
		}
	}

	public final ArrayLengthContext arrayLength() throws RecognitionException {
		ArrayLengthContext _localctx = new ArrayLengthContext(_ctx, 8);
		enterRule(_localctx, RULE_arrayLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); _localctx.expr = expression(0);
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

	public static class ElementTypeContext extends ParserRuleContext<Token> {
		public TypeContext typ;
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public ElementTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterElementType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitElementType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitElementType(this);
			else return null;
		}
	}

	public final ElementTypeContext elementType() throws RecognitionException {
		ElementTypeContext _localctx = new ElementTypeContext(_ctx, 10);
		enterRule(_localctx, RULE_elementType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); _localctx.typ = type();
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

	public static class SliceTypeContext extends ParserRuleContext<Token> {
		public ElementTypeContext elemType;
		public ElementTypeContext elementType() {
		    return getRuleContext(ElementTypeContext.class,0);
		}
		public SliceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSliceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSliceType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSliceType(this);
			else return null;
		}
	}

	public final SliceTypeContext sliceType() throws RecognitionException {
		SliceTypeContext _localctx = new SliceTypeContext(_ctx, 12);
		enterRule(_localctx, RULE_sliceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); match(LeftBrack);
			setState(268); match(RightBrack);
			setState(270); _localctx.elemType = elementType();
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

	public static class StructTypeContext extends ParserRuleContext<Token> {
		public FieldDeclContext fieldDecl;
		public List<FieldDeclContext> fields = new ArrayList<FieldDeclContext>();
		public FieldDeclContext fieldDecl(int i) {
		    return getRuleContext(FieldDeclContext.class,i);
		}
		public List<? extends FieldDeclContext> fieldDecl() {
		    return getRuleContexts(FieldDeclContext.class);
		}
		public StructTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterStructType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitStructType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitStructType(this);
			else return null;
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, 14);
		enterRule(_localctx, RULE_structType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(Struct);
			setState(274); match(LeftBrace);
			setState(282);
			_errHandler.sync(this);
			int _alt187 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt187!=2 && _alt187!=-1 ) {
				if ( _alt187==1 ) {
					{
					{
					setState(276); _localctx.fieldDecl = fieldDecl();
					_localctx.fields.add(_localctx.fieldDecl);
					setState(278); match(Semi);
					}
					} 
				}
				setState(284);
				_errHandler.sync(this);
				_alt187 = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(287);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(285); _localctx.fieldDecl = fieldDecl();
					_localctx.fields.add(_localctx.fieldDecl);
					}
					break;
			}
			setState(289); match(RightBrace);
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

	public static class FieldDeclContext extends ParserRuleContext<Token> {
		public IdentifierListContext idList;
		public TypeContext fieldType;
		public AnonymousFieldContext anonField;
		public TagContext fieldTag;
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
		public FieldDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFieldDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFieldDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFieldDecl(this);
			else return null;
		}
	}

	public final FieldDeclContext fieldDecl() throws RecognitionException {
		FieldDeclContext _localctx = new FieldDeclContext(_ctx, 16);
		enterRule(_localctx, RULE_fieldDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(291); _localctx.idList = identifierList();
					setState(293); _localctx.fieldType = type();
					}
					break;

				case 2:
					{
					setState(295); _localctx.anonField = anonymousField();
					}
					break;
			}
			setState(301);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(299); _localctx.fieldTag = tag();
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

	public static class AnonymousFieldContext extends ParserRuleContext<Token> {
		public Token ptr;
		public TypeNameContext fieldType;
		public TypeNameContext typeName() {
		    return getRuleContext(TypeNameContext.class,0);
		}
		public AnonymousFieldContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAnonymousField(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAnonymousField(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAnonymousField(this);
			else return null;
		}
	}

	public final AnonymousFieldContext anonymousField() throws RecognitionException {
		AnonymousFieldContext _localctx = new AnonymousFieldContext(_ctx, 18);
		enterRule(_localctx, RULE_anonymousField);
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
			setState(307); _localctx.fieldType = typeName();
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

	public static class TagContext extends ParserRuleContext<Token> {
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public TagContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTag(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTag(this);
			else return null;
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, 20);
		enterRule(_localctx, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(StringLiteral);
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

	public static class PointerTypeContext extends ParserRuleContext<Token> {
		public Token ptr;
		public BaseTypeContext typ;
		public BaseTypeContext baseType() {
		    return getRuleContext(BaseTypeContext.class,0);
		}
		public PointerTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterPointerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitPointerType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitPointerType(this);
			else return null;
		}
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, 22);
		enterRule(_localctx, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311); _localctx.ptr = match(Star);
			setState(313); _localctx.typ = baseType();
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

	public static class BaseTypeContext extends ParserRuleContext<Token> {
		public TypeContext typ;
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public BaseTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBaseType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBaseType(this);
			else return null;
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, 24);
		enterRule(_localctx, RULE_baseType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); _localctx.typ = type();
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

	public static class FunctionTypeContext extends ParserRuleContext<Token> {
		public SignatureContext sig;
		public SignatureContext signature() {
		    return getRuleContext(SignatureContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFunctionType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFunctionType(this);
			else return null;
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, 26);
		enterRule(_localctx, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(Func);
			setState(319); _localctx.sig = signature();
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

	public static class SignatureContext extends ParserRuleContext<Token> {
		public ParametersContext params;
		public ResultContext res;
		public ResultContext result() {
		    return getRuleContext(ResultContext.class,0);
		}
		public ParametersContext parameters() {
		    return getRuleContext(ParametersContext.class,0);
		}
		public SignatureContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSignature(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSignature(this);
			else return null;
		}
	}

	public final SignatureContext signature() throws RecognitionException {
		SignatureContext _localctx = new SignatureContext(_ctx, 28);
		enterRule(_localctx, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321); _localctx.params = parameters();
			setState(325);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(323); _localctx.res = result();
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

	public static class ResultContext extends ParserRuleContext<Token> {
		public ParametersContext params;
		public TypeContext t;
		public ParametersContext parameters() {
		    return getRuleContext(ParametersContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public ResultContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterResult(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitResult(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitResult(this);
			else return null;
		}
	}

	public final ResultContext result() throws RecognitionException {
		ResultContext _localctx = new ResultContext(_ctx, 30);
		enterRule(_localctx, RULE_result);
		try {
			setState(331);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(327); _localctx.params = parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(329); _localctx.t = type();
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

	public static class ParametersContext extends ParserRuleContext<Token> {
		public ParameterListContext params;
		public ParameterListContext parameterList() {
		    return getRuleContext(ParameterListContext.class,0);
		}
		public ParametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitParameters(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitParameters(this);
			else return null;
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, 32);
		enterRule(_localctx, RULE_parameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333); match(LeftParen);
			setState(341);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(335); _localctx.params = parameterList();
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

	public static class ParameterListContext extends ParserRuleContext<Token> {
		public ParameterDeclContext parameterDecl;
		public List<ParameterDeclContext> params = new ArrayList<ParameterDeclContext>();
		public List<? extends ParameterDeclContext> parameterDecl() {
		    return getRuleContexts(ParameterDeclContext.class);
		}
		public ParameterDeclContext parameterDecl(int i) {
		    return getRuleContext(ParameterDeclContext.class,i);
		}
		public ParameterListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitParameterList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitParameterList(this);
			else return null;
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, 34);
		enterRule(_localctx, RULE_parameterList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); _localctx.parameterDecl = parameterDecl();
			_localctx.params.add(_localctx.parameterDecl);
			setState(353);
			_errHandler.sync(this);
			int _alt350 = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt350!=2 && _alt350!=-1 ) {
				if ( _alt350==1 ) {
					{
					{
					setState(347); match(Comma);
					setState(349); _localctx.parameterDecl = parameterDecl();
					_localctx.params.add(_localctx.parameterDecl);
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt350 = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public static class ParameterDeclContext extends ParserRuleContext<Token> {
		public IdentifierListContext idList;
		public Token ellip;
		public TypeContext t;
		public IdentifierListContext identifierList() {
		    return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public ParameterDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterParameterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitParameterDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitParameterDecl(this);
			else return null;
		}
	}

	public final ParameterDeclContext parameterDecl() throws RecognitionException {
		ParameterDeclContext _localctx = new ParameterDeclContext(_ctx, 36);
		enterRule(_localctx, RULE_parameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(356); _localctx.idList = identifierList();
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
			setState(364); _localctx.t = type();
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

	public static class InterfaceTypeContext extends ParserRuleContext<Token> {
		public MethodSpecContext methodSpec;
		public List<MethodSpecContext> methods = new ArrayList<MethodSpecContext>();
		public MethodSpecContext methodSpec(int i) {
		    return getRuleContext(MethodSpecContext.class,i);
		}
		public List<? extends MethodSpecContext> methodSpec() {
		    return getRuleContexts(MethodSpecContext.class);
		}
		public InterfaceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitInterfaceType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitInterfaceType(this);
			else return null;
		}
	}

	public final InterfaceTypeContext interfaceType() throws RecognitionException {
		InterfaceTypeContext _localctx = new InterfaceTypeContext(_ctx, 38);
		enterRule(_localctx, RULE_interfaceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366); match(Interface);
			setState(368); match(LeftBrace);
			setState(385);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(370); _localctx.methodSpec = methodSpec();
					_localctx.methods.add(_localctx.methodSpec);
					setState(378);
					_errHandler.sync(this);
					int _alt394 = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt394!=2 && _alt394!=-1 ) {
						if ( _alt394==1 ) {
							{
							{
							setState(372); match(Semi);
							setState(374); _localctx.methodSpec = methodSpec();
							_localctx.methods.add(_localctx.methodSpec);
							}
							} 
						}
						setState(380);
						_errHandler.sync(this);
						_alt394 = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	public static class MethodSpecContext extends ParserRuleContext<Token> {
		public MethodNameContext name;
		public SignatureContext sig;
		public InterfaceTypeNameContext ifaceName;
		public MethodNameContext methodName() {
		    return getRuleContext(MethodNameContext.class,0);
		}
		public SignatureContext signature() {
		    return getRuleContext(SignatureContext.class,0);
		}
		public InterfaceTypeNameContext interfaceTypeName() {
		    return getRuleContext(InterfaceTypeNameContext.class,0);
		}
		public MethodSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMethodSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMethodSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMethodSpec(this);
			else return null;
		}
	}

	public final MethodSpecContext methodSpec() throws RecognitionException {
		MethodSpecContext _localctx = new MethodSpecContext(_ctx, 40);
		enterRule(_localctx, RULE_methodSpec);
		try {
			setState(395);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(389); _localctx.name = methodName();
					setState(391); _localctx.sig = signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(393); _localctx.ifaceName = interfaceTypeName();
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

	public static class MethodNameContext extends ParserRuleContext<Token> {
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public MethodNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMethodName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMethodName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMethodName(this);
			else return null;
		}
	}

	public final MethodNameContext methodName() throws RecognitionException {
		MethodNameContext _localctx = new MethodNameContext(_ctx, 42);
		enterRule(_localctx, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397); _localctx.name = match(IDENTIFIER);
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

	public static class InterfaceTypeNameContext extends ParserRuleContext<Token> {
		public TypeNameContext typName;
		public TypeNameContext typeName() {
		    return getRuleContext(TypeNameContext.class,0);
		}
		public InterfaceTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterInterfaceTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitInterfaceTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitInterfaceTypeName(this);
			else return null;
		}
	}

	public final InterfaceTypeNameContext interfaceTypeName() throws RecognitionException {
		InterfaceTypeNameContext _localctx = new InterfaceTypeNameContext(_ctx, 44);
		enterRule(_localctx, RULE_interfaceTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399); _localctx.typName = typeName();
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

	public static class MapTypeContext extends ParserRuleContext<Token> {
		public KeyTypeContext keyTyp;
		public ElementTypeContext elemType;
		public KeyTypeContext keyType() {
		    return getRuleContext(KeyTypeContext.class,0);
		}
		public ElementTypeContext elementType() {
		    return getRuleContext(ElementTypeContext.class,0);
		}
		public MapTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMapType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMapType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMapType(this);
			else return null;
		}
	}

	public final MapTypeContext mapType() throws RecognitionException {
		MapTypeContext _localctx = new MapTypeContext(_ctx, 46);
		enterRule(_localctx, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401); match(Map);
			setState(403); match(LeftBrack);
			setState(405); _localctx.keyTyp = keyType();
			setState(407); match(RightBrack);
			setState(409); _localctx.elemType = elementType();
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

	public static class KeyTypeContext extends ParserRuleContext<Token> {
		public TypeContext t;
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public KeyTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterKeyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitKeyType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitKeyType(this);
			else return null;
		}
	}

	public final KeyTypeContext keyType() throws RecognitionException {
		KeyTypeContext _localctx = new KeyTypeContext(_ctx, 48);
		enterRule(_localctx, RULE_keyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411); _localctx.t = type();
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

	public static class ChannelTypeContext extends ParserRuleContext<Token> {
		public Token send;
		public Token recv;
		public ElementTypeContext elemType;
		public ElementTypeContext elementType() {
		    return getRuleContext(ElementTypeContext.class,0);
		}
		public ChannelTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterChannelType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitChannelType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitChannelType(this);
			else return null;
		}
	}

	public final ChannelTypeContext channelType() throws RecognitionException {
		ChannelTypeContext _localctx = new ChannelTypeContext(_ctx, 50);
		enterRule(_localctx, RULE_channelType);
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
			setState(425); _localctx.elemType = elementType();
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

	public static class BlockContext extends ParserRuleContext<Token> {
		public StatementContext statement;
		public List<StatementContext> statements = new ArrayList<StatementContext>();
		public List<? extends StatementContext> statement() {
		    return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
		    return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBlock(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBlock(this);
			else return null;
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, 52);
		enterRule(_localctx, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427); match(LeftBrace);
			setState(444);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(429); _localctx.statement = statement();
					_localctx.statements.add(_localctx.statement);
					setState(437);
					_errHandler.sync(this);
					int _alt522 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt522!=2 && _alt522!=-1 ) {
						if ( _alt522==1 ) {
							{
							{
							setState(431); match(Semi);
							setState(433); _localctx.statement = statement();
							_localctx.statements.add(_localctx.statement);
							}
							} 
						}
						setState(439);
						_errHandler.sync(this);
						_alt522 = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	public static class DeclarationContext extends ParserRuleContext<Token> {
		public ConstDeclContext cd;
		public TypeDeclContext td;
		public VarDeclContext vd;
		public ConstDeclContext constDecl() {
		    return getRuleContext(ConstDeclContext.class,0);
		}
		public TypeDeclContext typeDecl() {
		    return getRuleContext(TypeDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
		    return getRuleContext(VarDeclContext.class,0);
		}
		public DeclarationContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitDeclaration(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitDeclaration(this);
			else return null;
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, 54);
		enterRule(_localctx, RULE_declaration);
		try {
			setState(454);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(448); _localctx.cd = constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(450); _localctx.td = typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(452); _localctx.vd = varDecl();
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

	public static class TopLevelDeclContext extends ParserRuleContext<Token> {
		public DeclarationContext decl;
		public FunctionDeclContext fndecl;
		public MethodDeclContext methdecl;
		public DeclarationContext declaration() {
		    return getRuleContext(DeclarationContext.class,0);
		}
		public MethodDeclContext methodDecl() {
		    return getRuleContext(MethodDeclContext.class,0);
		}
		public FunctionDeclContext functionDecl() {
		    return getRuleContext(FunctionDeclContext.class,0);
		}
		public TopLevelDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTopLevelDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTopLevelDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTopLevelDecl(this);
			else return null;
		}
	}

	public final TopLevelDeclContext topLevelDecl() throws RecognitionException {
		TopLevelDeclContext _localctx = new TopLevelDeclContext(_ctx, 56);
		enterRule(_localctx, RULE_topLevelDecl);
		try {
			setState(462);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(456); _localctx.decl = declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(458); _localctx.fndecl = functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(460); _localctx.methdecl = methodDecl();
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

	public static class ConstDeclContext extends ParserRuleContext<Token> {
		public ConstSpecContext constSpec;
		public List<ConstSpecContext> consts = new ArrayList<ConstSpecContext>();
		public List<? extends ConstSpecContext> constSpec() {
		    return getRuleContexts(ConstSpecContext.class);
		}
		public ConstSpecContext constSpec(int i) {
		    return getRuleContext(ConstSpecContext.class,i);
		}
		public ConstDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitConstDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitConstDecl(this);
			else return null;
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, 58);
		enterRule(_localctx, RULE_constDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464); match(Const);
			setState(489);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(466); _localctx.constSpec = constSpec();
					_localctx.consts.add(_localctx.constSpec);
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
							setState(470); _localctx.constSpec = constSpec();
							_localctx.consts.add(_localctx.constSpec);
							setState(478);
							_errHandler.sync(this);
							int _alt605 = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt605!=2 && _alt605!=-1 ) {
								if ( _alt605==1 ) {
									{
									{
									setState(472); match(Semi);
									setState(474); _localctx.constSpec = constSpec();
									_localctx.consts.add(_localctx.constSpec);
									}
									} 
								}
								setState(480);
								_errHandler.sync(this);
								_alt605 = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class ConstSpecContext extends ParserRuleContext<Token> {
		public IdentifierListContext idList;
		public TypeContext explicitType;
		public ExpressionListContext valueList;
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public IdentifierListContext identifierList() {
		    return getRuleContext(IdentifierListContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public ConstSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterConstSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitConstSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitConstSpec(this);
			else return null;
		}
	}

	public final ConstSpecContext constSpec() throws RecognitionException {
		ConstSpecContext _localctx = new ConstSpecContext(_ctx, 60);
		enterRule(_localctx, RULE_constSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491); _localctx.idList = identifierList();
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
							setState(493); _localctx.explicitType = type();
							}
							break;
					}
					setState(497); match(Equal);
					setState(499); _localctx.valueList = expressionList();
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

	public static class IdentifierListContext extends ParserRuleContext<Token> {
		public Token IDENTIFIER;
		public List<Token> ids = new ArrayList<Token>();
		public Token IDENTIFIER(int i) {
		    return getToken(GoParserBase.IDENTIFIER, i);
		}
		public List<? extends Token> IDENTIFIER() { return getTokens(GoParserBase.IDENTIFIER); }
		public IdentifierListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitIdentifierList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitIdentifierList(this);
			else return null;
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, 62);
		enterRule(_localctx, RULE_identifierList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503); _localctx.IDENTIFIER = match(IDENTIFIER);
			_localctx.ids.add(_localctx.IDENTIFIER);
			setState(511);
			_errHandler.sync(this);
			int _alt657 = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt657!=2 && _alt657!=-1 ) {
				if ( _alt657==1 ) {
					{
					{
					setState(505); match(Comma);
					setState(507); _localctx.IDENTIFIER = match(IDENTIFIER);
					_localctx.ids.add(_localctx.IDENTIFIER);
					}
					} 
				}
				setState(513);
				_errHandler.sync(this);
				_alt657 = getInterpreter().adaptivePredict(_input,31,_ctx);
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

	public static class ExpressionListContext extends ParserRuleContext<Token> {
		public ExpressionContext expression;
		public List<ExpressionContext> expressions = new ArrayList<ExpressionContext>();
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitExpressionList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitExpressionList(this);
			else return null;
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, 64);
		enterRule(_localctx, RULE_expressionList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); _localctx.expression = expression(0);
			_localctx.expressions.add(_localctx.expression);
			setState(522);
			_errHandler.sync(this);
			int _alt676 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt676!=2 && _alt676!=-1 ) {
				if ( _alt676==1 ) {
					{
					{
					setState(516); match(Comma);
					setState(518); _localctx.expression = expression(0);
					_localctx.expressions.add(_localctx.expression);
					}
					} 
				}
				setState(524);
				_errHandler.sync(this);
				_alt676 = getInterpreter().adaptivePredict(_input,32,_ctx);
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

	public static class TypeDeclContext extends ParserRuleContext<Token> {
		public TypeSpecContext typeSpec;
		public List<TypeSpecContext> types = new ArrayList<TypeSpecContext>();
		public List<? extends TypeSpecContext> typeSpec() {
		    return getRuleContexts(TypeSpecContext.class);
		}
		public TypeSpecContext typeSpec(int i) {
		    return getRuleContext(TypeSpecContext.class,i);
		}
		public TypeDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeDecl(this);
			else return null;
		}
	}

	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, 66);
		enterRule(_localctx, RULE_typeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525); match(Type);
			setState(550);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(527); _localctx.typeSpec = typeSpec();
					_localctx.types.add(_localctx.typeSpec);
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
							setState(531); _localctx.typeSpec = typeSpec();
							_localctx.types.add(_localctx.typeSpec);
							setState(539);
							_errHandler.sync(this);
							int _alt708 = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt708!=2 && _alt708!=-1 ) {
								if ( _alt708==1 ) {
									{
									{
									setState(533); match(Semi);
									setState(535); _localctx.typeSpec = typeSpec();
									_localctx.types.add(_localctx.typeSpec);
									}
									} 
								}
								setState(541);
								_errHandler.sync(this);
								_alt708 = getInterpreter().adaptivePredict(_input,33,_ctx);
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

	public static class TypeSpecContext extends ParserRuleContext<Token> {
		public Token name;
		public TypeContext t;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public TypeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeSpec(this);
			else return null;
		}
	}

	public final TypeSpecContext typeSpec() throws RecognitionException {
		TypeSpecContext _localctx = new TypeSpecContext(_ctx, 68);
		enterRule(_localctx, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552); _localctx.name = match(IDENTIFIER);
			setState(554); _localctx.t = type();
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

	public static class VarDeclContext extends ParserRuleContext<Token> {
		public VarSpecContext varSpec;
		public List<VarSpecContext> vars = new ArrayList<VarSpecContext>();
		public VarSpecContext varSpec(int i) {
		    return getRuleContext(VarSpecContext.class,i);
		}
		public List<? extends VarSpecContext> varSpec() {
		    return getRuleContexts(VarSpecContext.class);
		}
		public VarDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitVarDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitVarDecl(this);
			else return null;
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, 70);
		enterRule(_localctx, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556); match(Var);
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(558); _localctx.varSpec = varSpec();
					_localctx.vars.add(_localctx.varSpec);
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
							setState(562); _localctx.varSpec = varSpec();
							_localctx.vars.add(_localctx.varSpec);
							setState(570);
							_errHandler.sync(this);
							int _alt763 = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt763!=2 && _alt763!=-1 ) {
								if ( _alt763==1 ) {
									{
									{
									setState(564); match(Semi);
									setState(566); _localctx.varSpec = varSpec();
									_localctx.vars.add(_localctx.varSpec);
									}
									} 
								}
								setState(572);
								_errHandler.sync(this);
								_alt763 = getInterpreter().adaptivePredict(_input,37,_ctx);
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

	public static class VarSpecContext extends ParserRuleContext<Token> {
		public IdentifierListContext idList;
		public TypeContext varType;
		public ExpressionListContext exprList;
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
		public VarSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterVarSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitVarSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitVarSpec(this);
			else return null;
		}
	}

	public final VarSpecContext varSpec() throws RecognitionException {
		VarSpecContext _localctx = new VarSpecContext(_ctx, 72);
		enterRule(_localctx, RULE_varSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583); _localctx.idList = identifierList();
			setState(597);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(585); _localctx.varType = type();
					setState(591);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(587); match(Equal);
							setState(589); _localctx.exprList = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(593); match(Equal);
					setState(595); _localctx.exprList = expressionList();
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

	public static class ShortVarDeclContext extends ParserRuleContext<Token> {
		public IdentifierListContext idList;
		public ExpressionListContext exprList;
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public IdentifierListContext identifierList() {
		    return getRuleContext(IdentifierListContext.class,0);
		}
		public ShortVarDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterShortVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitShortVarDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitShortVarDecl(this);
			else return null;
		}
	}

	public final ShortVarDeclContext shortVarDecl() throws RecognitionException {
		ShortVarDeclContext _localctx = new ShortVarDeclContext(_ctx, 74);
		enterRule(_localctx, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599); _localctx.idList = identifierList();
			setState(601); match(ColonEqual);
			setState(603); _localctx.exprList = expressionList();
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

	public static class FunctionDeclContext extends ParserRuleContext<Token> {
		public Token name;
		public SignatureContext sig;
		public BodyContext bdy;
		public BodyContext body() {
		    return getRuleContext(BodyContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public SignatureContext signature() {
		    return getRuleContext(SignatureContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFunctionDecl(this);
			else return null;
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, 76);
		enterRule(_localctx, RULE_functionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605); match(Func);
			setState(607); _localctx.name = match(IDENTIFIER);
			setState(609); _localctx.sig = signature();
			setState(613);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(611); _localctx.bdy = body();
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

	public static class BodyContext extends ParserRuleContext<Token> {
		public BlockContext blk;
		public BlockContext block() {
		    return getRuleContext(BlockContext.class,0);
		}
		public BodyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBody(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBody(this);
			else return null;
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, 78);
		enterRule(_localctx, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615); _localctx.blk = block();
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

	public static class MethodDeclContext extends ParserRuleContext<Token> {
		public ReceiverContext recv;
		public MethodNameContext name;
		public SignatureContext sig;
		public BodyContext bdy;
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
		public MethodDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMethodDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMethodDecl(this);
			else return null;
		}
	}

	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, 80);
		enterRule(_localctx, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(617); match(Func);
			setState(619); _localctx.recv = receiver();
			setState(621); _localctx.name = methodName();
			setState(623); _localctx.sig = signature();
			setState(627);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(625); _localctx.bdy = body();
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

	public static class ReceiverContext extends ParserRuleContext<Token> {
		public Token name;
		public Token ptr;
		public BaseTypeNameContext typ;
		public BaseTypeNameContext baseTypeName() {
		    return getRuleContext(BaseTypeNameContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public ReceiverContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterReceiver(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitReceiver(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitReceiver(this);
			else return null;
		}
	}

	public final ReceiverContext receiver() throws RecognitionException {
		ReceiverContext _localctx = new ReceiverContext(_ctx, 82);
		enterRule(_localctx, RULE_receiver);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(629); match(LeftParen);
			setState(633);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(631); _localctx.name = match(IDENTIFIER);
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
			setState(639); _localctx.typ = baseTypeName();
			setState(641); match(RightParen);
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

	public static class BaseTypeNameContext extends ParserRuleContext<Token> {
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public BaseTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBaseTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBaseTypeName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBaseTypeName(this);
			else return null;
		}
	}

	public final BaseTypeNameContext baseTypeName() throws RecognitionException {
		BaseTypeNameContext _localctx = new BaseTypeNameContext(_ctx, 84);
		enterRule(_localctx, RULE_baseTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643); _localctx.name = match(IDENTIFIER);
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

	public static class OperandContext extends ParserRuleContext<Token> {
		public LiteralContext lit;
		public QualifiedIdentifierContext qid;
		public MethodExprContext me;
		public ExpressionContext e;
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
		public OperandContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitOperand(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitOperand(this);
			else return null;
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, 86);
		enterRule(_localctx, RULE_operand);
		try {
			setState(657);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(645); _localctx.lit = literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(647); _localctx.qid = qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(649); _localctx.me = methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(651); match(LeftParen);
					setState(653); _localctx.e = expression(0);
					setState(655); match(RightParen);
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

	public static class LiteralContext extends ParserRuleContext<Token> {
		public BasicLiteralContext bl;
		public CompositeLiteralContext cl;
		public FunctionLiteralContext fl;
		public BasicLiteralContext basicLiteral() {
		    return getRuleContext(BasicLiteralContext.class,0);
		}
		public CompositeLiteralContext compositeLiteral() {
		    return getRuleContext(CompositeLiteralContext.class,0);
		}
		public FunctionLiteralContext functionLiteral() {
		    return getRuleContext(FunctionLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitLiteral(this);
			else return null;
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, 88);
		enterRule(_localctx, RULE_literal);
		try {
			setState(665);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(659); _localctx.bl = basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(661); _localctx.cl = compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(663); _localctx.fl = functionLiteral();
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

	public static class BasicLiteralContext extends ParserRuleContext<Token> {
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public Token IMAGINARY_LITERAL() { return getToken(GoParserBase.IMAGINARY_LITERAL, 0); }
		public Token FLOAT_LITERAL() { return getToken(GoParserBase.FLOAT_LITERAL, 0); }
		public Token INT_LITERAL() { return getToken(GoParserBase.INT_LITERAL, 0); }
		public Token CharLiteral() { return getToken(GoParserBase.CharLiteral, 0); }
		public BasicLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBasicLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBasicLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBasicLiteral(this);
			else return null;
		}
	}

	public final BasicLiteralContext basicLiteral() throws RecognitionException {
		BasicLiteralContext _localctx = new BasicLiteralContext(_ctx, 90);
		enterRule(_localctx, RULE_basicLiteral);
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

	public static class QualifiedIdentifierContext extends ParserRuleContext<Token> {
		public PackageNameContext pkg;
		public Token dot;
		public Token id;
		public PackageNameContext packageName() {
		    return getRuleContext(PackageNameContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public QualifiedIdentifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterQualifiedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitQualifiedIdentifier(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitQualifiedIdentifier(this);
			else return null;
		}
	}

	public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
		QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, 92);
		enterRule(_localctx, RULE_qualifiedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(685);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
				case 1:
					{
					setState(679);
					if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "failed predicate: {isPackageName(_input.LT(1))}?");
					setState(681); _localctx.pkg = packageName();
					setState(683); _localctx.dot = match(Dot);
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

	public static class MethodExprContext extends ParserRuleContext<Token> {
		public ReceiverTypeContext recvType;
		public Token dot;
		public MethodNameContext name;
		public ReceiverTypeContext receiverType() {
		    return getRuleContext(ReceiverTypeContext.class,0);
		}
		public MethodNameContext methodName() {
		    return getRuleContext(MethodNameContext.class,0);
		}
		public MethodExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMethodExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMethodExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMethodExpr(this);
			else return null;
		}
	}

	public final MethodExprContext methodExpr() throws RecognitionException {
		MethodExprContext _localctx = new MethodExprContext(_ctx, 94);
		enterRule(_localctx, RULE_methodExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689); _localctx.recvType = receiverType();
			setState(691); _localctx.dot = match(Dot);
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

	public static class ReceiverTypeContext extends ParserRuleContext<Token> {
		public TypeNameContext t;
		public Token ptr;
		public TypeNameContext typeName() {
		    return getRuleContext(TypeNameContext.class,0);
		}
		public ReceiverTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterReceiverType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitReceiverType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitReceiverType(this);
			else return null;
		}
	}

	public final ReceiverTypeContext receiverType() throws RecognitionException {
		ReceiverTypeContext _localctx = new ReceiverTypeContext(_ctx, 96);
		enterRule(_localctx, RULE_receiverType);
		try {
			setState(705);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(695); _localctx.t = typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(697); match(LeftParen);
					setState(699); _localctx.ptr = match(Star);
					setState(701); _localctx.t = typeName();
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

	public static class CompositeLiteralContext extends ParserRuleContext<Token> {
		public LiteralTypeContext litTyp;
		public LiteralValueContext litVal;
		public LiteralTypeContext literalType() {
		    return getRuleContext(LiteralTypeContext.class,0);
		}
		public LiteralValueContext literalValue() {
		    return getRuleContext(LiteralValueContext.class,0);
		}
		public CompositeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCompositeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCompositeLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCompositeLiteral(this);
			else return null;
		}
	}

	public final CompositeLiteralContext compositeLiteral() throws RecognitionException {
		CompositeLiteralContext _localctx = new CompositeLiteralContext(_ctx, 98);
		enterRule(_localctx, RULE_compositeLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707); _localctx.litTyp = literalType();
			setState(709); _localctx.litVal = literalValue();
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
		public LiteralTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterLiteralType(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitLiteralType(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitLiteralType(this);
			else return null;
		}
	}

	public final LiteralTypeContext literalType() throws RecognitionException {
		LiteralTypeContext _localctx = new LiteralTypeContext(_ctx, 100);
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

	public static class LiteralValueContext extends ParserRuleContext<Token> {
		public ElementListContext elements;
		public ElementListContext elementList() {
		    return getRuleContext(ElementListContext.class,0);
		}
		public LiteralValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterLiteralValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitLiteralValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitLiteralValue(this);
			else return null;
		}
	}

	public final LiteralValueContext literalValue() throws RecognitionException {
		LiteralValueContext _localctx = new LiteralValueContext(_ctx, 102);
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
					setState(733); _localctx.elements = elementList();
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

	public static class ElementListContext extends ParserRuleContext<Token> {
		public ElementContext element;
		public List<ElementContext> elements = new ArrayList<ElementContext>();
		public List<? extends ElementContext> element() {
		    return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
		    return getRuleContext(ElementContext.class,i);
		}
		public ElementListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitElementList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitElementList(this);
			else return null;
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, 104);
		enterRule(_localctx, RULE_elementList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743); _localctx.element = element();
			_localctx.elements.add(_localctx.element);
			setState(751);
			_errHandler.sync(this);
			int _alt1143 = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt1143!=2 && _alt1143!=-1 ) {
				if ( _alt1143==1 ) {
					{
					{
					setState(745); match(Comma);
					setState(747); _localctx.element = element();
					_localctx.elements.add(_localctx.element);
					}
					} 
				}
				setState(753);
				_errHandler.sync(this);
				_alt1143 = getInterpreter().adaptivePredict(_input,55,_ctx);
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

	public static class ElementContext extends ParserRuleContext<Token> {
		public KeyContext k;
		public ValueContext v;
		public ValueContext value() {
		    return getRuleContext(ValueContext.class,0);
		}
		public KeyContext key() {
		    return getRuleContext(KeyContext.class,0);
		}
		public ElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitElement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitElement(this);
			else return null;
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, 106);
		enterRule(_localctx, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(758);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					{
					setState(754); _localctx.k = key();
					setState(756); match(Colon);
					}
					break;
			}
			setState(760); _localctx.v = value();
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

	public static class KeyContext extends ParserRuleContext<Token> {
		public FieldNameContext field;
		public ElementIndexContext index;
		public ElementIndexContext elementIndex() {
		    return getRuleContext(ElementIndexContext.class,0);
		}
		public FieldNameContext fieldName() {
		    return getRuleContext(FieldNameContext.class,0);
		}
		public KeyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitKey(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitKey(this);
			else return null;
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, 108);
		enterRule(_localctx, RULE_key);
		try {
			setState(766);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(762); _localctx.field = fieldName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(764); _localctx.index = elementIndex();
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

	public static class FieldNameContext extends ParserRuleContext<Token> {
		public Token field;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public FieldNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFieldName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFieldName(this);
			else return null;
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, 110);
		enterRule(_localctx, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768); _localctx.field = match(IDENTIFIER);
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

	public static class ElementIndexContext extends ParserRuleContext<Token> {
		public ExpressionContext index;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ElementIndexContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterElementIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitElementIndex(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitElementIndex(this);
			else return null;
		}
	}

	public final ElementIndexContext elementIndex() throws RecognitionException {
		ElementIndexContext _localctx = new ElementIndexContext(_ctx, 112);
		enterRule(_localctx, RULE_elementIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770); _localctx.index = expression(0);
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

	public static class ValueContext extends ParserRuleContext<Token> {
		public ExpressionContext expr;
		public LiteralValueContext lit;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public LiteralValueContext literalValue() {
		    return getRuleContext(LiteralValueContext.class,0);
		}
		public ValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitValue(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitValue(this);
			else return null;
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, 114);
		enterRule(_localctx, RULE_value);
		try {
			setState(776);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(772); _localctx.expr = expression(0);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(774); _localctx.lit = literalValue();
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

	public static class FunctionLiteralContext extends ParserRuleContext<Token> {
		public FunctionTypeContext typ;
		public BodyContext bdy;
		public BodyContext body() {
		    return getRuleContext(BodyContext.class,0);
		}
		public FunctionTypeContext functionType() {
		    return getRuleContext(FunctionTypeContext.class,0);
		}
		public FunctionLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFunctionLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFunctionLiteral(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFunctionLiteral(this);
			else return null;
		}
	}

	public final FunctionLiteralContext functionLiteral() throws RecognitionException {
		FunctionLiteralContext _localctx = new FunctionLiteralContext(_ctx, 116);
		enterRule(_localctx, RULE_functionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(778); _localctx.typ = functionType();
			setState(780); _localctx.bdy = body();
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

	public static class ExpressionContext extends ParserRuleContext<Token> {
		public int _p;
		public ExpressionContext(ParserRuleContext<Token> parent, int state) { super(parent, state); }
		public ExpressionContext(ParserRuleContext<Token> parent, int state, int _p) {
			super(parent, state);
			this._p = _p;
		}
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}
	}
	public static class MultExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token op;
		public ExpressionContext right;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public MultExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMultExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMultExpr(this);
			else return null;
		}
	}
	public static class AndExprContext extends ExpressionContext {
		public ExpressionContext e;
		public ExpressionContext right;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAndExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAndExpr(this);
			else return null;
		}
	}
	public static class ConversionOrCallExprContext extends ExpressionContext {
		public ConversionContext conv;
		public ConversionContext conversion() {
		    return getRuleContext(ConversionContext.class,0);
		}
		public ConversionOrCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterConversionOrCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitConversionOrCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitConversionOrCallExpr(this);
			else return null;
		}
	}
	public static class CallExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token lp;
		public ArgumentListContext args;
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
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCallExpr(this);
			else return null;
		}
	}
	public static class TypeAssertionExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token dot;
		public Token lp;
		public TypeContext t;
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
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeAssertionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeAssertionExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeAssertionExpr(this);
			else return null;
		}
	}
	public static class CompareExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token op;
		public ExpressionContext right;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public CompareExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCompareExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCompareExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCompareExpr(this);
			else return null;
		}
	}
	public static class OrExprContext extends ExpressionContext {
		public ExpressionContext e;
		public ExpressionContext right;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public OrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitOrExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitOrExpr(this);
			else return null;
		}
	}
	public static class SelectorExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token dot;
		public Token name;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public SelectorExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSelectorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSelectorExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSelectorExpr(this);
			else return null;
		}
	}
	public static class SliceExprContext extends ExpressionContext {
		public ExpressionContext e;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public SliceExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSliceExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSliceExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSliceExpr(this);
			else return null;
		}
	}
	public static class UnaryExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext e;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitUnaryExpr(this);
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
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterOperandExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitOperandExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitOperandExpr(this);
			else return null;
		}
	}
	public static class AddExprContext extends ExpressionContext {
		public ExpressionContext e;
		public Token op;
		public ExpressionContext right;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public AddExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAddExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAddExpr(this);
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
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBuiltinCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBuiltinCallExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBuiltinCallExpr(this);
			else return null;
		}
	}
	public static class IndexExprContext extends ExpressionContext {
		public ExpressionContext e;
		public List<? extends ExpressionContext> expression() {
		    return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
		    return getRuleContext(ExpressionContext.class,i);
		}
		public IndexExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitIndexExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitIndexExpr(this);
			else return null;
		}
	}

	public final ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext<Token> _parentctx = _ctx;
		ExpressionContext _localctx = new ExpressionContext(_ctx, 118, _p);
		ExpressionContext _prevctx = _localctx;
		int _startState = 118;
		pushNewRecursionContext(_localctx, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					_localctx = new UnaryExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(796);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(782); ((UnaryExprContext)_localctx).op = match(Plus);
							}
							break;

						case 2:
							{
							setState(784); ((UnaryExprContext)_localctx).op = match(Minus);
							}
							break;

						case 3:
							{
							setState(786); ((UnaryExprContext)_localctx).op = match(Bang);
							}
							break;

						case 4:
							{
							setState(788); ((UnaryExprContext)_localctx).op = match(Caret);
							}
							break;

						case 5:
							{
							setState(790); ((UnaryExprContext)_localctx).op = match(Star);
							}
							break;

						case 6:
							{
							setState(792); ((UnaryExprContext)_localctx).op = match(Amp);
							}
							break;

						case 7:
							{
							setState(794); ((UnaryExprContext)_localctx).op = match(LeftArrow);
							}
							break;
					}
					setState(798); ((UnaryExprContext)_localctx).e = expression(6);
					}
					break;

				case 2:
					{
					_localctx = new OperandExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(800); operand();
					}
					break;

				case 3:
					{
					_localctx = new ConversionOrCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(802); ((ConversionOrCallExprContext)_localctx).conv = conversion();
					}
					break;

				case 4:
					{
					_localctx = new BuiltinCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(804); builtinCall();
					}
					break;
			}
			_ctx.stop = _input.LT(-1);
			setState(928);
			_errHandler.sync(this);
			int _alt302 = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt302!=2 && _alt302!=-1 ) {
				if ( _alt302==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					_prevctx.stop = _input.LT(-1);
					{
					setState(926);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
						case 1:
							{
							_localctx = new MultExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((MultExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(808);
							if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
							setState(824);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
								case 1:
									{
									setState(810); ((MultExprContext)_localctx).op = match(Star);
									}
									break;

								case 2:
									{
									setState(812); ((MultExprContext)_localctx).op = match(Slash);
									}
									break;

								case 3:
									{
									setState(814); ((MultExprContext)_localctx).op = match(Percent);
									}
									break;

								case 4:
									{
									setState(816); ((MultExprContext)_localctx).op = match(LeftShift);
									}
									break;

								case 5:
									{
									setState(818); ((MultExprContext)_localctx).op = match(RightShift);
									}
									break;

								case 6:
									{
									setState(820); ((MultExprContext)_localctx).op = match(Amp);
									}
									break;

								case 7:
									{
									setState(822); ((MultExprContext)_localctx).op = match(AmpCaret);
									}
									break;
							}
							setState(826); ((MultExprContext)_localctx).right = expression(6);
							}
							break;

						case 2:
							{
							_localctx = new AddExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((AddExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(828);
							if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
							setState(838);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
								case 1:
									{
									setState(830); ((AddExprContext)_localctx).op = match(Plus);
									}
									break;

								case 2:
									{
									setState(832); ((AddExprContext)_localctx).op = match(Minus);
									}
									break;

								case 3:
									{
									setState(834); ((AddExprContext)_localctx).op = match(Pipe);
									}
									break;

								case 4:
									{
									setState(836); ((AddExprContext)_localctx).op = match(Caret);
									}
									break;
							}
							setState(840); ((AddExprContext)_localctx).right = expression(5);
							}
							break;

						case 3:
							{
							_localctx = new CompareExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((CompareExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(842);
							if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
							setState(856);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
								case 1:
									{
									setState(844); ((CompareExprContext)_localctx).op = match(EqualEqual);
									}
									break;

								case 2:
									{
									setState(846); ((CompareExprContext)_localctx).op = match(BangEqual);
									}
									break;

								case 3:
									{
									setState(848); ((CompareExprContext)_localctx).op = match(LessThan);
									}
									break;

								case 4:
									{
									setState(850); ((CompareExprContext)_localctx).op = match(LessEqual);
									}
									break;

								case 5:
									{
									setState(852); ((CompareExprContext)_localctx).op = match(GreaterThan);
									}
									break;

								case 6:
									{
									setState(854); ((CompareExprContext)_localctx).op = match(GreaterEqual);
									}
									break;
							}
							setState(858); ((CompareExprContext)_localctx).right = expression(4);
							}
							break;

						case 4:
							{
							_localctx = new AndExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((AndExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(860);
							if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
							setState(862); match(And);
							setState(864); ((AndExprContext)_localctx).right = expression(3);
							}
							break;

						case 5:
							{
							_localctx = new OrExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((OrExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(866);
							if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
							setState(868); match(Or);
							setState(870); ((OrExprContext)_localctx).right = expression(2);
							}
							break;

						case 6:
							{
							_localctx = new SelectorExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((SelectorExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(872);
							if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {11 >= $_p}?");
							setState(874); ((SelectorExprContext)_localctx).dot = match(Dot);
							setState(876); ((SelectorExprContext)_localctx).name = match(IDENTIFIER);
							}
							break;

						case 7:
							{
							_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((IndexExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(878);
							if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {10 >= $_p}?");
							setState(880); match(LeftBrack);
							setState(882); expression(0);
							setState(884); match(RightBrack);
							}
							break;

						case 8:
							{
							_localctx = new SliceExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((SliceExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(886);
							if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {9 >= $_p}?");
							setState(888); match(LeftBrack);
							setState(892);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
								case 1:
									{
									setState(890); expression(0);
									}
									break;
							}
							setState(894); match(Colon);
							setState(898);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
								case 1:
									{
									setState(896); expression(0);
									}
									break;
							}
							setState(900); match(RightBrack);
							}
							break;

						case 9:
							{
							_localctx = new TypeAssertionExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((TypeAssertionExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(902);
							if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {8 >= $_p}?");
							setState(904); ((TypeAssertionExprContext)_localctx).dot = match(Dot);
							setState(906); ((TypeAssertionExprContext)_localctx).lp = match(LeftParen);
							setState(908); ((TypeAssertionExprContext)_localctx).t = type();
							setState(910); ((TypeAssertionExprContext)_localctx).rp = match(RightParen);
							}
							break;

						case 10:
							{
							_localctx = new CallExprContext(new ExpressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((CallExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(912);
							if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {7 >= $_p}?");
							setState(914); ((CallExprContext)_localctx).lp = match(LeftParen);
							setState(922);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
								case 1:
									{
									setState(916); ((CallExprContext)_localctx).args = argumentList();
									setState(920);
									//_errHandler.sync(this);
									switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
										case 1:
											{
											setState(918); match(Comma);
											}
											break;
									}
									}
									break;
							}
							setState(924); ((CallExprContext)_localctx).rp = match(RightParen);
							}
							break;
					}
					} 
				}
				setState(930);
				_errHandler.sync(this);
				_alt302 = getInterpreter().adaptivePredict(_input,69,_ctx);
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

	public static class ArgumentListContext extends ParserRuleContext<Token> {
		public ExpressionListContext exprs;
		public Token ellip;
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitArgumentList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitArgumentList(this);
			else return null;
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, 120);
		enterRule(_localctx, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(931); _localctx.exprs = expressionList();
			setState(935);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(933); _localctx.ellip = match(Ellipsis);
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

	public static class ConversionContext extends ParserRuleContext<Token> {
		public TypeContext t;
		public ExpressionContext e;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public ConversionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterConversion(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitConversion(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitConversion(this);
			else return null;
		}
	}

	public final ConversionContext conversion() throws RecognitionException {
		ConversionContext _localctx = new ConversionContext(_ctx, 122);
		enterRule(_localctx, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937); _localctx.t = type();
			setState(939); match(LeftParen);
			setState(941); _localctx.e = expression(0);
			setState(943); match(RightParen);
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
		public StatementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitStatement(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitStatement(this);
			else return null;
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, 124);
		enterRule(_localctx, RULE_statement);
		try {
			setState(975);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(945); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(947); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(949); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(951); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(953); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(955); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(957); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(959); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(961); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(963); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(965); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(967); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(969); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(971); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(973); deferStmt();
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
		public SimpleStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSimpleStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSimpleStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSimpleStmt(this);
			else return null;
		}
	}

	public final SimpleStmtContext simpleStmt() throws RecognitionException {
		SimpleStmtContext _localctx = new SimpleStmtContext(_ctx, 126);
		enterRule(_localctx, RULE_simpleStmt);
		try {
			setState(989);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(977); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(979); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(981); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(983); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(985); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(987); shortVarDecl();
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

	public static class EmptyStmtContext extends ParserRuleContext<Token> {
		public EmptyStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitEmptyStmt(this);
			else return null;
		}
	}

	public final EmptyStmtContext emptyStmt() throws RecognitionException {
		EmptyStmtContext _localctx = new EmptyStmtContext(_ctx, 128);
		enterRule(_localctx, RULE_emptyStmt);
		try {
			enterOuterAlt(_localctx, 1);
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

	public static class LabeledStmtContext extends ParserRuleContext<Token> {
		public LabelContext lbl;
		public StatementContext stmt;
		public StatementContext statement() {
		    return getRuleContext(StatementContext.class,0);
		}
		public LabelContext label() {
		    return getRuleContext(LabelContext.class,0);
		}
		public LabeledStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterLabeledStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitLabeledStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitLabeledStmt(this);
			else return null;
		}
	}

	public final LabeledStmtContext labeledStmt() throws RecognitionException {
		LabeledStmtContext _localctx = new LabeledStmtContext(_ctx, 130);
		enterRule(_localctx, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993); _localctx.lbl = label();
			setState(995); match(Colon);
			setState(997); _localctx.stmt = statement();
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

	public static class LabelContext extends ParserRuleContext<Token> {
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public LabelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitLabel(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitLabel(this);
			else return null;
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, 132);
		enterRule(_localctx, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999); _localctx.name = match(IDENTIFIER);
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

	public static class ExpressionStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterExpressionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitExpressionStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitExpressionStmt(this);
			else return null;
		}
	}

	public final ExpressionStmtContext expressionStmt() throws RecognitionException {
		ExpressionStmtContext _localctx = new ExpressionStmtContext(_ctx, 134);
		enterRule(_localctx, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1001); expression(0);
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

	public static class SendStmtContext extends ParserRuleContext<Token> {
		public ChannelContext chan;
		public ExpressionContext e;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ChannelContext channel() {
		    return getRuleContext(ChannelContext.class,0);
		}
		public SendStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSendStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSendStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSendStmt(this);
			else return null;
		}
	}

	public final SendStmtContext sendStmt() throws RecognitionException {
		SendStmtContext _localctx = new SendStmtContext(_ctx, 136);
		enterRule(_localctx, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003); _localctx.chan = channel();
			setState(1005); match(LeftArrow);
			setState(1007); _localctx.e = expression(0);
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

	public static class ChannelContext extends ParserRuleContext<Token> {
		public ExpressionContext e;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ChannelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterChannel(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitChannel(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitChannel(this);
			else return null;
		}
	}

	public final ChannelContext channel() throws RecognitionException {
		ChannelContext _localctx = new ChannelContext(_ctx, 138);
		enterRule(_localctx, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009); _localctx.e = expression(0);
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

	public static class IncDecStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext e;
		public Token op;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public IncDecStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterIncDecStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitIncDecStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitIncDecStmt(this);
			else return null;
		}
	}

	public final IncDecStmtContext incDecStmt() throws RecognitionException {
		IncDecStmtContext _localctx = new IncDecStmtContext(_ctx, 140);
		enterRule(_localctx, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1011); _localctx.e = expression(0);
			setState(1013);
			_localctx.op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
				_localctx.op = _errHandler.recoverInline(this);
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

	public static class AssignmentContext extends ParserRuleContext<Token> {
		public ExpressionListContext targets;
		public AssignOpContext op;
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
		public AssignmentContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAssignment(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAssignment(this);
			else return null;
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, 142);
		enterRule(_localctx, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015); _localctx.targets = expressionList();
			setState(1017); _localctx.op = assignOp();
			setState(1019); _localctx.values = expressionList();
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

	public static class AssignOpContext extends ParserRuleContext<Token> {
		public AddAssignOpContext addAssignOp() {
		    return getRuleContext(AddAssignOpContext.class,0);
		}
		public MulAssignOpContext mulAssignOp() {
		    return getRuleContext(MulAssignOpContext.class,0);
		}
		public AssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAssignOp(this);
			else return null;
		}
	}

	public final AssignOpContext assignOp() throws RecognitionException {
		AssignOpContext _localctx = new AssignOpContext(_ctx, 144);
		enterRule(_localctx, RULE_assignOp);
		try {
			setState(1027);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1021); addAssignOp();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1023); mulAssignOp();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1025); match(Equal);
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

	public static class AddAssignOpContext extends ParserRuleContext<Token> {
		public AddAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterAddAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitAddAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitAddAssignOp(this);
			else return null;
		}
	}

	public final AddAssignOpContext addAssignOp() throws RecognitionException {
		AddAssignOpContext _localctx = new AddAssignOpContext(_ctx, 146);
		enterRule(_localctx, RULE_addAssignOp);
		try {
			setState(1037);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1029); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1031); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1033); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1035); match(CaretEqual);
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

	public static class MulAssignOpContext extends ParserRuleContext<Token> {
		public MulAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterMulAssignOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitMulAssignOp(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitMulAssignOp(this);
			else return null;
		}
	}

	public final MulAssignOpContext mulAssignOp() throws RecognitionException {
		MulAssignOpContext _localctx = new MulAssignOpContext(_ctx, 148);
		enterRule(_localctx, RULE_mulAssignOp);
		try {
			setState(1053);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1039); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1041); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1043); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(1045); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(1047); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1049); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1051); match(AmpCaretEqual);
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
		public IfStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitIfStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitIfStmt(this);
			else return null;
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, 150);
		enterRule(_localctx, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1055); match(If);
			setState(1061);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(1057); simpleStmt();
					setState(1059); match(Semi);
					}
					break;
			}
			setState(1063); expression(0);
			setState(1065); block();
			setState(1075);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
				case 1:
					{
					setState(1067); match(Else);
					setState(1073);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(1069); ifStmt();
							}
							break;

						case 2:
							{
							setState(1071); block();
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

	public static class SwitchStmtContext extends ParserRuleContext<Token> {
		public TypeSwitchStmtContext typeSwitchStmt() {
		    return getRuleContext(TypeSwitchStmtContext.class,0);
		}
		public ExprSwitchStmtContext exprSwitchStmt() {
		    return getRuleContext(ExprSwitchStmtContext.class,0);
		}
		public SwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSwitchStmt(this);
			else return null;
		}
	}

	public final SwitchStmtContext switchStmt() throws RecognitionException {
		SwitchStmtContext _localctx = new SwitchStmtContext(_ctx, 152);
		enterRule(_localctx, RULE_switchStmt);
		try {
			setState(1081);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1077); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1079); typeSwitchStmt();
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
		public ExprSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterExprSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitExprSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitExprSwitchStmt(this);
			else return null;
		}
	}

	public final ExprSwitchStmtContext exprSwitchStmt() throws RecognitionException {
		ExprSwitchStmtContext _localctx = new ExprSwitchStmtContext(_ctx, 154);
		enterRule(_localctx, RULE_exprSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1083); match(Switch);
			setState(1089);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(1085); simpleStmt();
					setState(1087); match(Semi);
					}
					break;
			}
			setState(1093);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(1091); expression(0);
					}
					break;
			}
			setState(1095); match(LeftBrace);
			setState(1101);
			_errHandler.sync(this);
			int _alt1980 = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt1980!=2 && _alt1980!=-1 ) {
				if ( _alt1980==1 ) {
					{
					{
					setState(1097); exprCaseClause();
					}
					} 
				}
				setState(1103);
				_errHandler.sync(this);
				_alt1980 = getInterpreter().adaptivePredict(_input,82,_ctx);
			}
			setState(1104); match(RightBrace);
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
		public ExprCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterExprCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitExprCaseClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitExprCaseClause(this);
			else return null;
		}
	}

	public final ExprCaseClauseContext exprCaseClause() throws RecognitionException {
		ExprCaseClauseContext _localctx = new ExprCaseClauseContext(_ctx, 156);
		enterRule(_localctx, RULE_exprCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1106); exprSwitchCase();
			setState(1108); match(Colon);
			setState(1125);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(1110); statement();
					setState(1118);
					_errHandler.sync(this);
					int _alt2002 = getInterpreter().adaptivePredict(_input,83,_ctx);
					while ( _alt2002!=2 && _alt2002!=-1 ) {
						if ( _alt2002==1 ) {
							{
							{
							setState(1112); match(Semi);
							setState(1114); statement();
							}
							} 
						}
						setState(1120);
						_errHandler.sync(this);
						_alt2002 = getInterpreter().adaptivePredict(_input,83,_ctx);
					}
					setState(1123);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
						case 1:
							{
							setState(1121); match(Semi);
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

	public static class ExprSwitchCaseContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public ExprSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterExprSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitExprSwitchCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitExprSwitchCase(this);
			else return null;
		}
	}

	public final ExprSwitchCaseContext exprSwitchCase() throws RecognitionException {
		ExprSwitchCaseContext _localctx = new ExprSwitchCaseContext(_ctx, 158);
		enterRule(_localctx, RULE_exprSwitchCase);
		try {
			setState(1133);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1127); match(Case);
					setState(1129); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1131); match(Default);
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
		public TypeSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeSwitchStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchStmt(this);
			else return null;
		}
	}

	public final TypeSwitchStmtContext typeSwitchStmt() throws RecognitionException {
		TypeSwitchStmtContext _localctx = new TypeSwitchStmtContext(_ctx, 160);
		enterRule(_localctx, RULE_typeSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1135); match(Switch);
			setState(1141);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(1137); simpleStmt();
					setState(1139); match(Semi);
					}
					break;
			}
			setState(1143); typeSwitchGuard();
			setState(1145); match(LeftBrace);
			setState(1151);
			_errHandler.sync(this);
			int _alt2043 = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt2043!=2 && _alt2043!=-1 ) {
				if ( _alt2043==1 ) {
					{
					{
					setState(1147); typeCaseClause();
					}
					} 
				}
				setState(1153);
				_errHandler.sync(this);
				_alt2043 = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(1154); match(RightBrace);
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

	public static class TypeSwitchGuardContext extends ParserRuleContext<Token> {
		public Token id;
		public Token defeq;
		public ExpressionContext e;
		public Token dot;
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public TypeSwitchGuardContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeSwitchGuard(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeSwitchGuard(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchGuard(this);
			else return null;
		}
	}

	public final TypeSwitchGuardContext typeSwitchGuard() throws RecognitionException {
		TypeSwitchGuardContext _localctx = new TypeSwitchGuardContext(_ctx, 162);
		enterRule(_localctx, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1160);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					{
					setState(1156); _localctx.id = match(IDENTIFIER);
					setState(1158); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(1162); _localctx.e = expression(0);
			setState(1164); _localctx.dot = match(Dot);
			setState(1166); match(LeftParen);
			setState(1168); match(Type);
			setState(1170); match(RightParen);
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
		public TypeCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeCaseClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeCaseClause(this);
			else return null;
		}
	}

	public final TypeCaseClauseContext typeCaseClause() throws RecognitionException {
		TypeCaseClauseContext _localctx = new TypeCaseClauseContext(_ctx, 164);
		enterRule(_localctx, RULE_typeCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1172); typeSwitchCase();
			setState(1174); match(Colon);
			setState(1191);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(1176); statement();
					setState(1184);
					_errHandler.sync(this);
					int _alt2098 = getInterpreter().adaptivePredict(_input,90,_ctx);
					while ( _alt2098!=2 && _alt2098!=-1 ) {
						if ( _alt2098==1 ) {
							{
							{
							setState(1178); match(Semi);
							setState(1180); statement();
							}
							} 
						}
						setState(1186);
						_errHandler.sync(this);
						_alt2098 = getInterpreter().adaptivePredict(_input,90,_ctx);
					}
					setState(1189);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
						case 1:
							{
							setState(1187); match(Semi);
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

	public static class TypeSwitchCaseContext extends ParserRuleContext<Token> {
		public TypeListContext typeList() {
		    return getRuleContext(TypeListContext.class,0);
		}
		public TypeSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeSwitchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeSwitchCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeSwitchCase(this);
			else return null;
		}
	}

	public final TypeSwitchCaseContext typeSwitchCase() throws RecognitionException {
		TypeSwitchCaseContext _localctx = new TypeSwitchCaseContext(_ctx, 166);
		enterRule(_localctx, RULE_typeSwitchCase);
		try {
			setState(1199);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1193); match(Case);
					setState(1195); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1197); match(Default);
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

	public static class TypeListContext extends ParserRuleContext<Token> {
		public TypeContext type;
		public List<TypeContext> types = new ArrayList<TypeContext>();
		public TypeContext type(int i) {
		    return getRuleContext(TypeContext.class,i);
		}
		public List<? extends TypeContext> type() {
		    return getRuleContexts(TypeContext.class);
		}
		public TypeListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitTypeList(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitTypeList(this);
			else return null;
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, 168);
		enterRule(_localctx, RULE_typeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1201); _localctx.type = type();
			_localctx.types.add(_localctx.type);
			setState(1209);
			_errHandler.sync(this);
			int _alt2136 = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt2136!=2 && _alt2136!=-1 ) {
				if ( _alt2136==1 ) {
					{
					{
					setState(1203); match(Comma);
					setState(1205); _localctx.type = type();
					_localctx.types.add(_localctx.type);
					}
					} 
				}
				setState(1211);
				_errHandler.sync(this);
				_alt2136 = getInterpreter().adaptivePredict(_input,94,_ctx);
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
		public ForStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitForStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitForStmt(this);
			else return null;
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, 170);
		enterRule(_localctx, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1212); match(For);
			setState(1220);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1214); condition();
					}
					break;

				case 2:
					{
					setState(1216); forClause();
					}
					break;

				case 3:
					{
					setState(1218); rangeClause();
					}
					break;
			}
			setState(1222); block();
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

	public static class ConditionContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCondition(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCondition(this);
			else return null;
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, 172);
		enterRule(_localctx, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1224); expression(0);
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
		public ForClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterForClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitForClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitForClause(this);
			else return null;
		}
	}

	public final ForClauseContext forClause() throws RecognitionException {
		ForClauseContext _localctx = new ForClauseContext(_ctx, 174);
		enterRule(_localctx, RULE_forClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1228);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(1226); initStmt();
					}
					break;
			}
			setState(1230); match(Semi);
			setState(1234);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
				case 1:
					{
					setState(1232); condition();
					}
					break;
			}
			setState(1236); match(Semi);
			setState(1240);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(1238); postStmt();
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

	public static class InitStmtContext extends ParserRuleContext<Token> {
		public SimpleStmtContext simpleStmt() {
		    return getRuleContext(SimpleStmtContext.class,0);
		}
		public InitStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterInitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitInitStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitInitStmt(this);
			else return null;
		}
	}

	public final InitStmtContext initStmt() throws RecognitionException {
		InitStmtContext _localctx = new InitStmtContext(_ctx, 176);
		enterRule(_localctx, RULE_initStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1242); simpleStmt();
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

	public static class PostStmtContext extends ParserRuleContext<Token> {
		public SimpleStmtContext simpleStmt() {
		    return getRuleContext(SimpleStmtContext.class,0);
		}
		public PostStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterPostStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitPostStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitPostStmt(this);
			else return null;
		}
	}

	public final PostStmtContext postStmt() throws RecognitionException {
		PostStmtContext _localctx = new PostStmtContext(_ctx, 178);
		enterRule(_localctx, RULE_postStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1244); simpleStmt();
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
		public RangeClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterRangeClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitRangeClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitRangeClause(this);
			else return null;
		}
	}

	public final RangeClauseContext rangeClause() throws RecognitionException {
		RangeClauseContext _localctx = new RangeClauseContext(_ctx, 180);
		enterRule(_localctx, RULE_rangeClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1246); _localctx.e1 = expression(0);
			setState(1252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(1248); match(Comma);
					setState(1250); _localctx.e2 = expression(0);
					}
					break;
			}
			setState(1258);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(1254); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(1256); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(1260); match(Range);
			setState(1262); _localctx.e = expression(0);
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

	public static class GoStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public GoStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterGoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitGoStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitGoStmt(this);
			else return null;
		}
	}

	public final GoStmtContext goStmt() throws RecognitionException {
		GoStmtContext _localctx = new GoStmtContext(_ctx, 182);
		enterRule(_localctx, RULE_goStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1264); match(Go);
			setState(1266); expression(0);
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

	public static class SelectStmtContext extends ParserRuleContext<Token> {
		public CommClauseContext commClause() {
		    return getRuleContext(CommClauseContext.class,0);
		}
		public SelectStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSelectStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSelectStmt(this);
			else return null;
		}
	}

	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, 184);
		enterRule(_localctx, RULE_selectStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1268); match(Select);
			setState(1270); match(LeftBrace);
			setState(1276);
			_errHandler.sync(this);
			int _alt2262 = getInterpreter().adaptivePredict(_input,101,_ctx);
			while ( _alt2262!=2 && _alt2262!=-1 ) {
				if ( _alt2262==1 ) {
					{
					{
					setState(1272); commClause();
					}
					} 
				}
				setState(1278);
				_errHandler.sync(this);
				_alt2262 = getInterpreter().adaptivePredict(_input,101,_ctx);
			}
			setState(1279); match(RightBrace);
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
		public CommClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCommClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCommClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCommClause(this);
			else return null;
		}
	}

	public final CommClauseContext commClause() throws RecognitionException {
		CommClauseContext _localctx = new CommClauseContext(_ctx, 186);
		enterRule(_localctx, RULE_commClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1281); commCase();
			setState(1283); match(Colon);
			setState(1300);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1285); statement();
					setState(1293);
					_errHandler.sync(this);
					int _alt2284 = getInterpreter().adaptivePredict(_input,102,_ctx);
					while ( _alt2284!=2 && _alt2284!=-1 ) {
						if ( _alt2284==1 ) {
							{
							{
							setState(1287); match(Semi);
							setState(1289); statement();
							}
							} 
						}
						setState(1295);
						_errHandler.sync(this);
						_alt2284 = getInterpreter().adaptivePredict(_input,102,_ctx);
					}
					setState(1298);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
						case 1:
							{
							setState(1296); match(Semi);
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

	public static class CommCaseContext extends ParserRuleContext<Token> {
		public RecvStmtContext recvStmt() {
		    return getRuleContext(RecvStmtContext.class,0);
		}
		public SendStmtContext sendStmt() {
		    return getRuleContext(SendStmtContext.class,0);
		}
		public CommCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterCommCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitCommCase(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitCommCase(this);
			else return null;
		}
	}

	public final CommCaseContext commCase() throws RecognitionException {
		CommCaseContext _localctx = new CommCaseContext(_ctx, 188);
		enterRule(_localctx, RULE_commCase);
		try {
			setState(1312);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1302); match(Case);
					setState(1308);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
						case 1:
							{
							setState(1304); sendStmt();
							}
							break;

						case 2:
							{
							setState(1306); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1310); match(Default);
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
		public RecvStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterRecvStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitRecvStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitRecvStmt(this);
			else return null;
		}
	}

	public final RecvStmtContext recvStmt() throws RecognitionException {
		RecvStmtContext _localctx = new RecvStmtContext(_ctx, 190);
		enterRule(_localctx, RULE_recvStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1328);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1314); _localctx.e1 = expression(0);
					setState(1320);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(1316); match(Comma);
							setState(1318); _localctx.e2 = expression(0);
							}
							break;
					}
					setState(1326);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
						case 1:
							{
							setState(1322); _localctx.eq = match(Equal);
							}
							break;

						case 2:
							{
							setState(1324); _localctx.defeq = match(ColonEqual);
							}
							break;
					}
					}
					break;
			}
			setState(1330); recvExpr();
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

	public static class RecvExprContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public RecvExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterRecvExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitRecvExpr(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitRecvExpr(this);
			else return null;
		}
	}

	public final RecvExprContext recvExpr() throws RecognitionException {
		RecvExprContext _localctx = new RecvExprContext(_ctx, 192);
		enterRule(_localctx, RULE_recvExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1332); expression(0);
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

	public static class ReturnStmtContext extends ParserRuleContext<Token> {
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitReturnStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitReturnStmt(this);
			else return null;
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, 194);
		enterRule(_localctx, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1334); match(Return);
			setState(1338);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
				case 1:
					{
					setState(1336); expressionList();
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

	public static class BreakStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
		    return getRuleContext(LabelContext.class,0);
		}
		public BreakStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBreakStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBreakStmt(this);
			else return null;
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, 196);
		enterRule(_localctx, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1340); match(Break);
			setState(1344);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					{
					setState(1342); label();
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

	public static class ContinueStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
		    return getRuleContext(LabelContext.class,0);
		}
		public ContinueStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitContinueStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitContinueStmt(this);
			else return null;
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, 198);
		enterRule(_localctx, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1346); match(Continue);
			setState(1350);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
				case 1:
					{
					setState(1348); label();
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

	public static class GotoStmtContext extends ParserRuleContext<Token> {
		public LabelContext label() {
		    return getRuleContext(LabelContext.class,0);
		}
		public GotoStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterGotoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitGotoStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitGotoStmt(this);
			else return null;
		}
	}

	public final GotoStmtContext gotoStmt() throws RecognitionException {
		GotoStmtContext _localctx = new GotoStmtContext(_ctx, 200);
		enterRule(_localctx, RULE_gotoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1352); match(Goto);
			setState(1354); label();
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

	public static class FallthroughStmtContext extends ParserRuleContext<Token> {
		public FallthroughStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterFallthroughStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitFallthroughStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitFallthroughStmt(this);
			else return null;
		}
	}

	public final FallthroughStmtContext fallthroughStmt() throws RecognitionException {
		FallthroughStmtContext _localctx = new FallthroughStmtContext(_ctx, 202);
		enterRule(_localctx, RULE_fallthroughStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1356); match(Fallthrough);
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

	public static class DeferStmtContext extends ParserRuleContext<Token> {
		public ExpressionContext expression() {
		    return getRuleContext(ExpressionContext.class,0);
		}
		public DeferStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterDeferStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitDeferStmt(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitDeferStmt(this);
			else return null;
		}
	}

	public final DeferStmtContext deferStmt() throws RecognitionException {
		DeferStmtContext _localctx = new DeferStmtContext(_ctx, 204);
		enterRule(_localctx, RULE_deferStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1358); match(Defer);
			setState(1360); expression(0);
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

	public static class BuiltinCallContext extends ParserRuleContext<Token> {
		public Token name;
		public BuiltinArgsContext args;
		public BuiltinArgsContext builtinArgs() {
		    return getRuleContext(BuiltinArgsContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public BuiltinCallContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBuiltinCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBuiltinCall(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBuiltinCall(this);
			else return null;
		}
	}

	public final BuiltinCallContext builtinCall() throws RecognitionException {
		BuiltinCallContext _localctx = new BuiltinCallContext(_ctx, 206);
		enterRule(_localctx, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1362); _localctx.name = match(IDENTIFIER);
			setState(1364); match(LeftParen);
			setState(1372);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1366); _localctx.args = builtinArgs();
					setState(1370);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
						case 1:
							{
							setState(1368); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1374); match(RightParen);
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

	public static class BuiltinArgsContext extends ParserRuleContext<Token> {
		public TypeContext typeArg;
		public ExpressionListContext args;
		public ExpressionListContext expressionList() {
		    return getRuleContext(ExpressionListContext.class,0);
		}
		public TypeContext type() {
		    return getRuleContext(TypeContext.class,0);
		}
		public BuiltinArgsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterBuiltinArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitBuiltinArgs(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitBuiltinArgs(this);
			else return null;
		}
	}

	public final BuiltinArgsContext builtinArgs() throws RecognitionException {
		BuiltinArgsContext _localctx = new BuiltinArgsContext(_ctx, 208);
		enterRule(_localctx, RULE_builtinArgs);
		try {
			setState(1386);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1376); _localctx.typeArg = type();
					setState(1382);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
						case 1:
							{
							setState(1378); match(Comma);
							setState(1380); _localctx.args = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1384); _localctx.args = expressionList();
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

	public static class SourceFileContext extends ParserRuleContext<Token> {
		public PackageClauseContext pkg;
		public ImportDeclContext importDecl;
		public List<ImportDeclContext> importDecls = new ArrayList<ImportDeclContext>();
		public TopLevelDeclContext topLevelDecl;
		public List<TopLevelDeclContext> decls = new ArrayList<TopLevelDeclContext>();
		public ImportDeclContext importDecl() {
		    return getRuleContext(ImportDeclContext.class,0);
		}
		public TopLevelDeclContext topLevelDecl() {
		    return getRuleContext(TopLevelDeclContext.class,0);
		}
		public PackageClauseContext packageClause() {
		    return getRuleContext(PackageClauseContext.class,0);
		}
		public SourceFileContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterSourceFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitSourceFile(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitSourceFile(this);
			else return null;
		}
	}

	public final SourceFileContext sourceFile() throws RecognitionException {
		SourceFileContext _localctx = new SourceFileContext(_ctx, 210);
		enterRule(_localctx, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1392);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1388); _localctx.pkg = packageClause();
					setState(1390); match(Semi);
					}
					break;
			}
			setState(1400);
			_errHandler.sync(this);
			int _alt2487 = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt2487!=2 && _alt2487!=-1 ) {
				if ( _alt2487==1 ) {
					{
					{
					setState(1394); _localctx.importDecl = importDecl();
					_localctx.importDecls.add(_localctx.importDecl);
					setState(1396); match(Semi);
					}
					} 
				}
				setState(1402);
				_errHandler.sync(this);
				_alt2487 = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(1409);
			_errHandler.sync(this);
			int _alt2496 = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt2496!=2 && _alt2496!=-1 ) {
				if ( _alt2496==1 ) {
					{
					{
					setState(1403); _localctx.topLevelDecl = topLevelDecl();
					_localctx.decls.add(_localctx.topLevelDecl);
					setState(1405); match(Semi);
					}
					} 
				}
				setState(1411);
				_errHandler.sync(this);
				_alt2496 = getInterpreter().adaptivePredict(_input,119,_ctx);
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

	public static class PackageClauseContext extends ParserRuleContext<Token> {
		public PackageNameContext name;
		public PackageNameContext packageName() {
		    return getRuleContext(PackageNameContext.class,0);
		}
		public PackageClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterPackageClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitPackageClause(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitPackageClause(this);
			else return null;
		}
	}

	public final PackageClauseContext packageClause() throws RecognitionException {
		PackageClauseContext _localctx = new PackageClauseContext(_ctx, 212);
		enterRule(_localctx, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1412); match(Package);
			setState(1414); _localctx.name = packageName();
			addPackageName((_localctx.name!=null?(_localctx.name.start):null));
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

	public static class PackageNameContext extends ParserRuleContext<Token> {
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public PackageNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitPackageName(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitPackageName(this);
			else return null;
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, 214);
		enterRule(_localctx, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1418); _localctx.name = match(IDENTIFIER);
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

	public static class ImportDeclContext extends ParserRuleContext<Token> {
		public ImportSpecContext importSpec;
		public List<ImportSpecContext> importSpecs = new ArrayList<ImportSpecContext>();
		public List<? extends ImportSpecContext> importSpec() {
		    return getRuleContexts(ImportSpecContext.class);
		}
		public ImportSpecContext importSpec(int i) {
		    return getRuleContext(ImportSpecContext.class,i);
		}
		public ImportDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitImportDecl(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitImportDecl(this);
			else return null;
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, 216);
		enterRule(_localctx, RULE_importDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1420); match(Import);
			setState(1445);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
				case 1:
					{
					setState(1422); _localctx.importSpec = importSpec();
					_localctx.importSpecs.add(_localctx.importSpec);
					}
					break;

				case 2:
					{
					setState(1424); match(LeftParen);
					setState(1441);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
						case 1:
							{
							setState(1426); _localctx.importSpec = importSpec();
							_localctx.importSpecs.add(_localctx.importSpec);
							setState(1434);
							_errHandler.sync(this);
							int _alt2551 = getInterpreter().adaptivePredict(_input,120,_ctx);
							while ( _alt2551!=2 && _alt2551!=-1 ) {
								if ( _alt2551==1 ) {
									{
									{
									setState(1428); match(Semi);
									setState(1430); _localctx.importSpec = importSpec();
									_localctx.importSpecs.add(_localctx.importSpec);
									}
									} 
								}
								setState(1436);
								_errHandler.sync(this);
								_alt2551 = getInterpreter().adaptivePredict(_input,120,_ctx);
							}
							setState(1439);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
								case 1:
									{
									setState(1437); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(1443); match(RightParen);
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

	public static class ImportSpecContext extends ParserRuleContext<Token> {
		public Token dot;
		public ImportPathContext path;
		public PackageNameContext name;
		public PackageNameContext packageName() {
		    return getRuleContext(PackageNameContext.class,0);
		}
		public ImportPathContext importPath() {
		    return getRuleContext(ImportPathContext.class,0);
		}
		public ImportSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterImportSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitImportSpec(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitImportSpec(this);
			else return null;
		}
	}

	public final ImportSpecContext importSpec() throws RecognitionException {
		ImportSpecContext _localctx = new ImportSpecContext(_ctx, 218);
		enterRule(_localctx, RULE_importSpec);
		try {
			setState(1461);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1447); _localctx.dot = match(Dot);
					setState(1449); _localctx.path = importPath();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1451); _localctx.name = packageName();
					setState(1453); _localctx.path = importPath();
					addPackageName((_localctx.name!=null?(_localctx.name.start):null));
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1457); _localctx.path = importPath();
					addPackageName((_localctx.path!=null?(_localctx.path.start):null));
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

	public static class ImportPathContext extends ParserRuleContext<Token> {
		public Token path;
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public ImportPathContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).enterImportPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener<? super Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exitImportPath(this);
		}
		@Override
		public <Result> Result accept(ParseTreeVisitor<? super Token, ? extends Result> visitor) {
			if ( visitor instanceof GoParserBaseVisitor<?, ?> ) return ((GoParserBaseVisitor<? super Token, ? extends Result>)visitor).visitImportPath(this);
			else return null;
		}
	}

	public final ImportPathContext importPath() throws RecognitionException {
		ImportPathContext _localctx = new ImportPathContext(_ctx, 220);
		enterRule(_localctx, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1463); _localctx.path = match(StringLiteral);
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
		"\1U\u05ba\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		":\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\3;\b;\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0"+
		"\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1;\0"+
		"\1;\0\3;\b;\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\1;\1;\0\1;\0\1"+
		";\0\1;\0\3;\b;\3;\b;\1;\0\5;\b;\n;\f;\u03a2\t;\1<\0\1<\0\3<\b<\1=\0\1"+
		"=\0\1=\0\1=\1=\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0"+
		"\1>\0\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\1?\0\1?\0\1?\0\1?\0\3?\b?\1@\1@\1"+
		"A\0\1A\0\1A\1A\1B\1B\1C\1C\1D\0\1D\0\1D\1D\1E\1E\1F\0\1F\1F\1G\0\1G\0"+
		"\1G\1G\1H\0\1H\0\1H\0\3H\bH\1I\0\1I\0\1I\0\1I\0\3I\bI\1J\0\1J\0\1J\0\1"+
		"J\0\1J\0\1J\0\1J\0\3J\bJ\1K\0\1K\0\1K\1K\3K\bK\1K\0\1K\0\1K\0\1K\0\1K"+
		"\0\3K\bK\3K\bK\1L\0\1L\0\3L\bL\1M\0\1M\0\1M\1M\3M\bM\1M\0\3M\bM\1M\0\1"+
		"M\0\5M\bM\nM\fM\u044f\tM\1M\1M\1N\0\1N\0\1N\0\1N\0\1N\0\5N\bN\nN\fN\u0460"+
		"\tN\1N\0\3N\bN\3N\bN\1O\0\1O\0\1O\0\3O\bO\1P\0\1P\0\1P\1P\3P\bP\1P\0\1"+
		"P\0\1P\0\5P\bP\nP\fP\u0481\tP\1P\1P\1Q\0\1Q\0\3Q\bQ\1Q\0\1Q\0\1Q\0\1Q"+
		"\0\1Q\1Q\1R\0\1R\0\1R\0\1R\0\1R\0\5R\bR\nR\fR\u04a2\tR\1R\0\3R\bR\3R\b"+
		"R\1S\0\1S\0\1S\0\3S\bS\1T\0\1T\0\1T\0\5T\bT\nT\fT\u04bb\tT\1U\0\1U\0\1"+
		"U\0\1U\0\3U\bU\1U\1U\1V\1V\1W\0\3W\bW\1W\0\1W\0\3W\bW\1W\0\1W\0\3W\bW"+
		"\1X\1X\1Y\1Y\1Z\0\1Z\0\1Z\0\3Z\bZ\1Z\0\1Z\0\3Z\bZ\1Z\0\1Z\1Z\1[\0\1[\1"+
		"[\1\\\0\1\\\0\1\\\0\5\\\b\\\n\\\f\\\u04fe\t\\\1\\\1\\\1]\0\1]\0\1]\0\1"+
		"]\0\1]\0\5]\b]\n]\f]\u050f\t]\1]\0\3]\b]\3]\b]\1^\0\1^\0\1^\0\3^\b^\1"+
		"^\0\3^\b^\1_\0\1_\0\1_\0\3_\b_\1_\0\1_\0\3_\b_\3_\b_\1_\1_\1`\1`\1a\0"+
		"\1a\0\3a\ba\1b\0\1b\0\3b\bb\1c\0\1c\0\3c\bc\1d\0\1d\1d\1e\1e\1f\0\1f\1"+
		"f\1g\0\1g\0\1g\0\1g\0\3g\bg\3g\bg\1g\1g\1h\0\1h\0\1h\0\3h\bh\1h\0\3h\b"+
		"h\1i\0\1i\1i\3i\bi\1i\0\1i\1i\5i\bi\ni\fi\u057a\ti\1i\0\1i\1i\5i\bi\n"+
		"i\fi\u0583\ti\1j\0\1j\0\1j\1j\1k\1k\1l\0\1l\0\1l\0\1l\0\1l\0\1l\0\5l\b"+
		"l\nl\fl\u059c\tl\1l\0\3l\bl\3l\bl\1l\0\3l\bl\1m\0\1m\0\1m\0\1m\0\1m\1"+
		"m\1m\0\1m\1m\3m\bm\1n\1n\1no\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a"+
		"\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2"+
		"\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca"+
		"\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\0\1\1\65\66\u0577"+
		"\0\u00e8\1\0\0\0\1\u00e6\1\0\0\0\1\u0109\1\0\0\0\1\u0126\1\0\0\0\1\u013c"+
		"\1\0\0\0\1\u014c\1\0\0\0\1\u016d\1\0\0\0\1\u019c\1\0\0\0\1\u01f0\1\0\0"+
		"\0\1\u022b\1\0\0\0\1\u024f\1\0\0\0\1\u038e\1\0\0\0\1\u03ab\1\0\0\0\1\u04b9"+
		"\1\0\0\0\1\u04b8\1\0\0\0\1\u0566\1\0\0\0\2\u00ea\1\0\0\0\3\u00e9\1\0\0"+
		"\0\3\u0134\1\0\0\0\3\u0190\1\0\0\0\3\u02c2\1\0\0\0\3\u02bf\1\0\0\0\3\u02da"+
		"\1\0\0\0\4\u00fc\1\0\0\0\5\u00e9\1\0\0\0\6\u00fe\1\0\0\0\7\u00fd\1\0\0"+
		"\0\7\u02da\1\0\0\0\b\u0106\1\0\0\0\t\u0102\1\0\0\0\n\u0108\1\0\0\0\13"+
		"\u0105\1\0\0\0\13\u010f\1\0\0\0\13\u019a\1\0\0\0\13\u01aa\1\0\0\0\13\u02da"+
		"\1\0\0\0\f\u010a\1\0\0\0\r\u00fd\1\0\0\0\r\u02da\1\0\0\0\16\u0110\1\0"+
		"\0\0\17\u00fd\1\0\0\0\17\u02da\1\0\0\0\20\u0129\1\0\0\0\21\u0116\1\0\0"+
		"\0\21\u0120\1\0\0\0\22\u0131\1\0\0\0\23\u012a\1\0\0\0\24\u0135\1\0\0\0"+
		"\25\u012e\1\0\0\0\26\u0137\1\0\0\0\27\u00fd\1\0\0\0\30\u013b\1\0\0\0\31"+
		"\u013a\1\0\0\0\32\u013d\1\0\0\0\33\u00fd\1\0\0\0\33\u030c\1\0\0\0\34\u0141"+
		"\1\0\0\0\35\u0140\1\0\0\0\35\u0188\1\0\0\0\35\u0265\1\0\0\0\35\u0273\1"+
		"\0\0\0\36\u014b\1\0\0\0\37\u0146\1\0\0\0 \u014d\1\0\0\0!\u0145\1\0\0\0"+
		"!\u014c\1\0\0\0\"\u0159\1\0\0\0#\u0153\1\0\0\0$\u0166\1\0\0\0%\u0161\1"+
		"\0\0\0%\u0160\1\0\0\0&\u016e\1\0\0\0\'\u00fd\1\0\0\0(\u018b\1\0\0\0)\u017a"+
		"\1\0\0\0)\u0179\1\0\0\0*\u018d\1\0\0\0+\u0187\1\0\0\0+\u026f\1\0\0\0+"+
		"\u02b6\1\0\0\0,\u018f\1\0\0\0-\u018c\1\0\0\0.\u0191\1\0\0\0/\u00fd\1\0"+
		"\0\0/\u02da\1\0\0\0\60\u019b\1\0\0\0\61\u0197\1\0\0\0\62\u01a7\1\0\0\0"+
		"\63\u00fd\1\0\0\0\64\u01ab\1\0\0\0\65\u0268\1\0\0\0\65\u03d0\1\0\0\0\65"+
		"\u0433\1\0\0\0\65\u0432\1\0\0\0\65\u04c7\1\0\0\0\66\u01c6\1\0\0\0\67\u01cf"+
		"\1\0\0\0\67\u03d0\1\0\0\08\u01ce\1\0\0\09\u057d\1\0\0\0:\u01d0\1\0\0\0"+
		";\u01c7\1\0\0\0<\u01eb\1\0\0\0=\u01ea\1\0\0\0=\u01de\1\0\0\0=\u01dd\1"+
		"\0\0\0>\u01f7\1\0\0\0?\u0125\1\0\0\0?\u0167\1\0\0\0?\u01f5\1\0\0\0?\u0255"+
		"\1\0\0\0?\u0259\1\0\0\0@\u0202\1\0\0\0A\u01f6\1\0\0\0A\u0250\1\0\0\0A"+
		"\u0256\1\0\0\0A\u025c\1\0\0\0A\u03a7\1\0\0\0A\u03f9\1\0\0\0A\u03fc\1\0"+
		"\0\0A\u046e\1\0\0\0A\u053b\1\0\0\0A\u0567\1\0\0\0A\u056b\1\0\0\0B\u020d"+
		"\1\0\0\0C\u01c7\1\0\0\0D\u0228\1\0\0\0E\u0227\1\0\0\0E\u021b\1\0\0\0E"+
		"\u021a\1\0\0\0F\u022c\1\0\0\0G\u01c7\1\0\0\0H\u0247\1\0\0\0I\u0246\1\0"+
		"\0\0I\u023a\1\0\0\0I\u0239\1\0\0\0J\u0257\1\0\0\0K\u03de\1\0\0\0L\u025d"+
		"\1\0\0\0M\u01cf\1\0\0\0N\u0267\1\0\0\0O\u0266\1\0\0\0O\u0274\1\0\0\0O"+
		"\u030d\1\0\0\0P\u0269\1\0\0\0Q\u01cf\1\0\0\0R\u0275\1\0\0\0S\u026d\1\0"+
		"\0\0T\u0283\1\0\0\0U\u0281\1\0\0\0V\u0291\1\0\0\0W\u0327\1\0\0\0X\u0299"+
		"\1\0\0\0Y\u0292\1\0\0\0Z\u02a5\1\0\0\0[\u029a\1\0\0\0\\\u02ad\1\0\0\0"+
		"]\u00eb\1\0\0\0]\u0292\1\0\0\0^\u02b1\1\0\0\0_\u0292\1\0\0\0`\u02c1\1"+
		"\0\0\0a\u02b3\1\0\0\0b\u02c3\1\0\0\0c\u029a\1\0\0\0d\u02d9\1\0\0\0e\u02c5"+
		"\1\0\0\0f\u02db\1\0\0\0g\u02c6\1\0\0\0g\u0309\1\0\0\0h\u02e7\1\0\0\0i"+
		"\u02e1\1\0\0\0j\u02f6\1\0\0\0k\u02ef\1\0\0\0k\u02ee\1\0\0\0l\u02fe\1\0"+
		"\0\0m\u02f4\1\0\0\0n\u0300\1\0\0\0o\u02ff\1\0\0\0p\u0302\1\0\0\0q\u02ff"+
		"\1\0\0\0r\u0308\1\0\0\0s\u02f9\1\0\0\0t\u030a\1\0\0\0u\u029a\1\0\0\0v"+
		"\u0326\1\0\0\0w\u0107\1\0\0\0w\u020a\1\0\0\0w\u0209\1\0\0\0w\u028f\1\0"+
		"\0\0w\u0303\1\0\0\0w\u0309\1\0\0\0w\u0327\1\0\0\0w\u039f\1\0\0\0w\u039f"+
		"\1\0\0\0w\u039f\1\0\0\0w\u039f\1\0\0\0w\u039f\1\0\0\0w\u0374\1\0\0\0w"+
		"\u037d\1\0\0\0w\u0383\1\0\0\0w\u03af\1\0\0\0w\u03ea\1\0\0\0w\u03f0\1\0"+
		"\0\0w\u03f2\1\0\0\0w\u03f5\1\0\0\0w\u0429\1\0\0\0w\u0446\1\0\0\0w\u048c"+
		"\1\0\0\0w\u04c9\1\0\0\0w\u04e4\1\0\0\0w\u04e5\1\0\0\0w\u04ef\1\0\0\0w"+
		"\u04f3\1\0\0\0w\u0528\1\0\0\0w\u0529\1\0\0\0w\u0535\1\0\0\0w\u0551\1\0"+
		"\0\0x\u03a3\1\0\0\0y\u0398\1\0\0\0z\u03a9\1\0\0\0{\u0327\1\0\0\0|\u03cf"+
		"\1\0\0\0}\u01b5\1\0\0\0}\u01b4\1\0\0\0}\u03e6\1\0\0\0}\u045e\1\0\0\0}"+
		"\u045d\1\0\0\0}\u04a0\1\0\0\0}\u049f\1\0\0\0}\u050d\1\0\0\0}\u050c\1\0"+
		"\0\0~\u03dd\1\0\0\0\177\u03d0\1\0\0\0\177\u0423\1\0\0\0\177\u043f\1\0"+
		"\0\0\177\u0473\1\0\0\0\177\u04db\1\0\0\0\177\u04dd\1\0\0\0\u0080\u03df"+
		"\1\0\0\0\u0081\u03de\1\0\0\0\u0082\u03e1\1\0\0\0\u0083\u03d0\1\0\0\0\u0084"+
		"\u03e7\1\0\0\0\u0085\u03e3\1\0\0\0\u0085\u0541\1\0\0\0\u0085\u0547\1\0"+
		"\0\0\u0085\u054b\1\0\0\0\u0086\u03e9\1\0\0\0\u0087\u03de\1\0\0\0\u0088"+
		"\u03eb\1\0\0\0\u0089\u03de\1\0\0\0\u0089\u051d\1\0\0\0\u008a\u03f1\1\0"+
		"\0\0\u008b\u03ed\1\0\0\0\u008c\u03f3\1\0\0\0\u008d\u03de\1\0\0\0\u008e"+
		"\u03f7\1\0\0\0\u008f\u03de\1\0\0\0\u0090\u0403\1\0\0\0\u0091\u03fb\1\0"+
		"\0\0\u0092\u040d\1\0\0\0\u0093\u0404\1\0\0\0\u0094\u041d\1\0\0\0\u0095"+
		"\u0404\1\0\0\0\u0096\u041f\1\0\0\0\u0097\u03d0\1\0\0\0\u0097\u0432\1\0"+
		"\0\0\u0098\u0439\1\0\0\0\u0099\u03d0\1\0\0\0\u009a\u043b\1\0\0\0\u009b"+
		"\u043a\1\0\0\0\u009c\u0452\1\0\0\0\u009d\u044c\1\0\0\0\u009e\u046d\1\0"+
		"\0\0\u009f\u0454\1\0\0\0\u00a0\u046f\1\0\0\0\u00a1\u043a\1\0\0\0\u00a2"+
		"\u0488\1\0\0\0\u00a3\u0479\1\0\0\0\u00a4\u0494\1\0\0\0\u00a5\u047e\1\0"+
		"\0\0\u00a6\u04af\1\0\0\0\u00a7\u0496\1\0\0\0\u00a8\u04b1\1\0\0\0\u00a9"+
		"\u04b0\1\0\0\0\u00aa\u04bc\1\0\0\0\u00ab\u03d0\1\0\0\0\u00ac\u04c8\1\0"+
		"\0\0\u00ad\u04c5\1\0\0\0\u00ad\u04d3\1\0\0\0\u00ae\u04cc\1\0\0\0\u00af"+
		"\u04c5\1\0\0\0\u00b0\u04da\1\0\0\0\u00b1\u04cd\1\0\0\0\u00b2\u04dc\1\0"+
		"\0\0\u00b3\u04d9\1\0\0\0\u00b4\u04de\1\0\0\0\u00b5\u04c5\1\0\0\0\u00b6"+
		"\u04f0\1\0\0\0\u00b7\u03d0\1\0\0\0\u00b8\u04f4\1\0\0\0\u00b9\u03d0\1\0"+
		"\0\0\u00ba\u0501\1\0\0\0\u00bb\u04fb\1\0\0\0\u00bc\u0520\1\0\0\0\u00bd"+
		"\u0503\1\0\0\0\u00be\u0530\1\0\0\0\u00bf\u051d\1\0\0\0\u00c0\u0534\1\0"+
		"\0\0\u00c1\u0533\1\0\0\0\u00c2\u0536\1\0\0\0\u00c3\u03d0\1\0\0\0\u00c4"+
		"\u053c\1\0\0\0\u00c5\u03d0\1\0\0\0\u00c6\u0542\1\0\0\0\u00c7\u03d0\1\0"+
		"\0\0\u00c8\u0548\1\0\0\0\u00c9\u03d0\1\0\0\0\u00ca\u054c\1\0\0\0\u00cb"+
		"\u03d0\1\0\0\0\u00cc\u054e\1\0\0\0\u00cd\u03d0\1\0\0\0\u00ce\u0552\1\0"+
		"\0\0\u00cf\u0327\1\0\0\0\u00d0\u056a\1\0\0\0\u00d1\u055a\1\0\0\0\u00d2"+
		"\u0570\1\0\0\0\u00d3\u05b9\5\uffff\0\0\u00d4\u0584\1\0\0\0\u00d5\u056e"+
		"\1\0\0\0\u00d6\u058a\1\0\0\0\u00d7\u02ab\1\0\0\0\u00d7\u0588\1\0\0\0\u00d7"+
		"\u05ad\1\0\0\0\u00d8\u058c\1\0\0\0\u00d9\u0574\1\0\0\0\u00da\u05b5\1\0"+
		"\0\0\u00db\u05a6\1\0\0\0\u00db\u059a\1\0\0\0\u00db\u0599\1\0\0\0\u00dc"+
		"\u05b7\1\0\0\0\u00dd\u05b6\1\0\0\0\u00dd\u05af\1\0\0\0\u00dd\u05b3\1\0"+
		"\0\0\u00de\u00e9\3\2\1\0\u00e0\u00e9\3\4\2\0\u00e2\u00e4\5A\0\0\u00e4"+
		"\u00e6\3\0\0\0\u00e6\u00e7\5B\0\0\u00e7\u00e9\1\0\0\0\u00e8\u00de\1\0"+
		"\0\0\u00e8\u00e0\1\0\0\0\u00e8\u00e2\1\0\0\0\u00e9\1\1\0\0\0\u00ea\u00eb"+
		"\3\\.\0\u00eb\3\1\0\0\0\u00ec\u00fd\3\6\3\0\u00ee\u00fd\3\16\7\0\u00f0"+
		"\u00fd\3\26\13\0\u00f2\u00fd\3\32\r\0\u00f4\u00fd\3&\23\0\u00f6\u00fd"+
		"\3\f\6\0\u00f8\u00fd\3.\27\0\u00fa\u00fd\3\62\31\0\u00fc\u00ec\1\0\0\0"+
		"\u00fc\u00ee\1\0\0\0\u00fc\u00f0\1\0\0\0\u00fc\u00f2\1\0\0\0\u00fc\u00f4"+
		"\1\0\0\0\u00fc\u00f6\1\0\0\0\u00fc\u00f8\1\0\0\0\u00fc\u00fa\1\0\0\0\u00fd"+
		"\5\1\0\0\0\u00fe\u0100\5C\0\0\u0100\u0102\3\b\4\0\u0102\u0104\5D\0\0\u0104"+
		"\u0105\3\n\5\0\u0105\7\1\0\0\0\u0106\u0107\3v;\0\u0107\t\1\0\0\0\u0108"+
		"\u0109\3\0\0\0\u0109\13\1\0\0\0\u010a\u010c\5C\0\0\u010c\u010e\5D\0\0"+
		"\u010e\u010f\3\n\5\0\u010f\r\1\0\0\0\u0110\u0112\5\30\0\0\u0112\u011a"+
		"\5E\0\0\u0114\u0116\3\20\b\0\u0116\u0117\5I\0\0\u0117\u0119\1\0\0\0\u0118"+
		"\u0114\1\0\0\0\u0119\u011c\1\0\0\0\u011a\u0118\1\0\0\0\u011a\u011b\1\0"+
		"\0\0\u011b\u011f\1\0\0\0\u011c\u011a\1\0\0\0\u011d\u0120\3\20\b\0\u011f"+
		"\u011d\1\0\0\0\u011f\u0120\1\0\0\0\u0120\u0121\1\0\0\0\u0121\u0122\5F"+
		"\0\0\u0122\17\1\0\0\0\u0123\u0125\3>\37\0\u0125\u0126\3\0\0\0\u0126\u012a"+
		"\1\0\0\0\u0127\u012a\3\22\t\0\u0129\u0123\1\0\0\0\u0129\u0127\1\0\0\0"+
		"\u012a\u012d\1\0\0\0\u012b\u012e\3\24\n\0\u012d\u012b\1\0\0\0\u012d\u012e"+
		"\1\0\0\0\u012e\21\1\0\0\0\u012f\u0132\5\36\0\0\u0131\u012f\1\0\0\0\u0131"+
		"\u0132\1\0\0\0\u0132\u0133\1\0\0\0\u0133\u0134\3\2\1\0\u0134\23\1\0\0"+
		"\0\u0135\u0136\5T\0\0\u0136\25\1\0\0\0\u0137\u0139\5\36\0\0\u0139\u013a"+
		"\3\30\f\0\u013a\27\1\0\0\0\u013b\u013c\3\0\0\0\u013c\31\1\0\0\0\u013d"+
		"\u013f\5\r\0\0\u013f\u0140\3\34\16\0\u0140\33\1\0\0\0\u0141\u0145\3 \20"+
		"\0\u0143\u0146\3\36\17\0\u0145\u0143\1\0\0\0\u0145\u0146\1\0\0\0\u0146"+
		"\35\1\0\0\0\u0147\u014c\3 \20\0\u0149\u014c\3\0\0\0\u014b\u0147\1\0\0"+
		"\0\u014b\u0149\1\0\0\0\u014c\37\1\0\0\0\u014d\u0155\5A\0\0\u014f\u0153"+
		"\3\"\21\0\u0151\u0154\5G\0\0\u0153\u0151\1\0\0\0\u0153\u0154\1\0\0\0\u0154"+
		"\u0156\1\0\0\0\u0155\u014f\1\0\0\0\u0155\u0156\1\0\0\0\u0156\u0157\1\0"+
		"\0\0\u0157\u0158\5B\0\0\u0158!\1\0\0\0\u0159\u0161\3$\22\0\u015b\u015d"+
		"\5G\0\0\u015d\u0160\3$\22\0\u015f\u015b\1\0\0\0\u0160\u0163\1\0\0\0\u0161"+
		"\u015f\1\0\0\0\u0161\u0162\1\0\0\0\u0162#\1\0\0\0\u0163\u0161\1\0\0\0"+
		"\u0164\u0167\3>\37\0\u0166\u0164\1\0\0\0\u0166\u0167\1\0\0\0\u0167\u016a"+
		"\1\0\0\0\u0168\u016b\5@\0\0\u016a\u0168\1\0\0\0\u016a\u016b\1\0\0\0\u016b"+
		"\u016c\1\0\0\0\u016c\u016d\3\0\0\0\u016d%\1\0\0\0\u016e\u0170\5\22\0\0"+
		"\u0170\u0181\5E\0\0\u0172\u017a\3(\24\0\u0174\u0176\5I\0\0\u0176\u0179"+
		"\3(\24\0\u0178\u0174\1\0\0\0\u0179\u017c\1\0\0\0\u017a\u0178\1\0\0\0\u017a"+
		"\u017b\1\0\0\0\u017b\u017f\1\0\0\0\u017c\u017a\1\0\0\0\u017d\u0180\5I"+
		"\0\0\u017f\u017d\1\0\0\0\u017f\u0180\1\0\0\0\u0180\u0182\1\0\0\0\u0181"+
		"\u0172\1\0\0\0\u0181\u0182\1\0\0\0\u0182\u0183\1\0\0\0\u0183\u0184\5F"+
		"\0\0\u0184\'\1\0\0\0\u0185\u0187\3*\25\0\u0187\u0188\3\34\16\0\u0188\u018c"+
		"\1\0\0\0\u0189\u018c\3,\26\0\u018b\u0185\1\0\0\0\u018b\u0189\1\0\0\0\u018c"+
		")\1\0\0\0\u018d\u018e\5K\0\0\u018e+\1\0\0\0\u018f\u0190\3\2\1\0\u0190"+
		"-\1\0\0\0\u0191\u0193\5\23\0\0\u0193\u0195\5C\0\0\u0195\u0197\3\60\30"+
		"\0\u0197\u0199\5D\0\0\u0199\u019a\3\n\5\0\u019a/\1\0\0\0\u019b\u019c\3"+
		"\0\0\0\u019c\61\1\0\0\0\u019d\u01a1\5\5\0\0\u019f\u01a2\5\64\0\0\u01a1"+
		"\u019f\1\0\0\0\u01a1\u01a2\1\0\0\0\u01a2\u01a8\1\0\0\0\u01a3\u01a5\5\64"+
		"\0\0\u01a5\u01a8\5\5\0\0\u01a7\u019d\1\0\0\0\u01a7\u01a3\1\0\0\0\u01a8"+
		"\u01a9\1\0\0\0\u01a9\u01aa\3\n\5\0\u01aa\63\1\0\0\0\u01ab\u01bc\5E\0\0"+
		"\u01ad\u01b5\3|>\0\u01af\u01b1\5I\0\0\u01b1\u01b4\3|>\0\u01b3\u01af\1"+
		"\0\0\0\u01b4\u01b7\1\0\0\0\u01b5\u01b3\1\0\0\0\u01b5\u01b6\1\0\0\0\u01b6"+
		"\u01ba\1\0\0\0\u01b7\u01b5\1\0\0\0\u01b8\u01bb\5I\0\0\u01ba\u01b8\1\0"+
		"\0\0\u01ba\u01bb\1\0\0\0\u01bb\u01bd\1\0\0\0\u01bc\u01ad\1\0\0\0\u01bc"+
		"\u01bd\1\0\0\0\u01bd\u01be\1\0\0\0\u01be\u01bf\5F\0\0\u01bf\65\1\0\0\0"+
		"\u01c0\u01c7\3:\35\0\u01c2\u01c7\3B!\0\u01c4\u01c7\3F#\0\u01c6\u01c0\1"+
		"\0\0\0\u01c6\u01c2\1\0\0\0\u01c6\u01c4\1\0\0\0\u01c7\67\1\0\0\0\u01c8"+
		"\u01cf\3\66\33\0\u01ca\u01cf\3L&\0\u01cc\u01cf\3P(\0\u01ce\u01c8\1\0\0"+
		"\0\u01ce\u01ca\1\0\0\0\u01ce\u01cc\1\0\0\0\u01cf9\1\0\0\0\u01d0\u01e9"+
		"\5\6\0\0\u01d2\u01ea\3<\36\0\u01d4\u01e5\5A\0\0\u01d6\u01de\3<\36\0\u01d8"+
		"\u01da\5I\0\0\u01da\u01dd\3<\36\0\u01dc\u01d8\1\0\0\0\u01dd\u01e0\1\0"+
		"\0\0\u01de\u01dc\1\0\0\0\u01de\u01df\1\0\0\0\u01df\u01e3\1\0\0\0\u01e0"+
		"\u01de\1\0\0\0\u01e1\u01e4\5I\0\0\u01e3\u01e1\1\0\0\0\u01e3\u01e4\1\0"+
		"\0\0\u01e4\u01e6\1\0\0\0\u01e5\u01d6\1\0\0\0\u01e5\u01e6\1\0\0\0\u01e6"+
		"\u01e7\1\0\0\0\u01e7\u01ea\5B\0\0\u01e9\u01d2\1\0\0\0\u01e9\u01d4\1\0"+
		"\0\0\u01ea;\1\0\0\0\u01eb\u01f5\3>\37\0\u01ed\u01f0\3\0\0\0\u01ef\u01ed"+
		"\1\0\0\0\u01ef\u01f0\1\0\0\0\u01f0\u01f1\1\0\0\0\u01f1\u01f3\5:\0\0\u01f3"+
		"\u01f6\3@ \0\u01f5\u01ef\1\0\0\0\u01f5\u01f6\1\0\0\0\u01f6=\1\0\0\0\u01f7"+
		"\u01ff\5K\0\0\u01f9\u01fb\5G\0\0\u01fb\u01fe\5K\0\0\u01fd\u01f9\1\0\0"+
		"\0\u01fe\u0201\1\0\0\0\u01ff\u01fd\1\0\0\0\u01ff\u0200\1\0\0\0\u0200?"+
		"\1\0\0\0\u0201\u01ff\1\0\0\0\u0202\u020a\3v;\0\u0204\u0206\5G\0\0\u0206"+
		"\u0209\3v;\0\u0208\u0204\1\0\0\0\u0209\u020c\1\0\0\0\u020a\u0208\1\0\0"+
		"\0\u020a\u020b\1\0\0\0\u020bA\1\0\0\0\u020c\u020a\1\0\0\0\u020d\u0226"+
		"\5\32\0\0\u020f\u0227\3D\"\0\u0211\u0222\5A\0\0\u0213\u021b\3D\"\0\u0215"+
		"\u0217\5I\0\0\u0217\u021a\3D\"\0\u0219\u0215\1\0\0\0\u021a\u021d\1\0\0"+
		"\0\u021b\u0219\1\0\0\0\u021b\u021c\1\0\0\0\u021c\u0220\1\0\0\0\u021d\u021b"+
		"\1\0\0\0\u021e\u0221\5I\0\0\u0220\u021e\1\0\0\0\u0220\u0221\1\0\0\0\u0221"+
		"\u0223\1\0\0\0\u0222\u0213\1\0\0\0\u0222\u0223\1\0\0\0\u0223\u0224\1\0"+
		"\0\0\u0224\u0227\5B\0\0\u0226\u020f\1\0\0\0\u0226\u0211\1\0\0\0\u0227"+
		"C\1\0\0\0\u0228\u022a\5K\0\0\u022a\u022b\3\0\0\0\u022bE\1\0\0\0\u022c"+
		"\u0245\5\33\0\0\u022e\u0246\3H$\0\u0230\u0241\5A\0\0\u0232\u023a\3H$\0"+
		"\u0234\u0236\5I\0\0\u0236\u0239\3H$\0\u0238\u0234\1\0\0\0\u0239\u023c"+
		"\1\0\0\0\u023a\u0238\1\0\0\0\u023a\u023b\1\0\0\0\u023b\u023f\1\0\0\0\u023c"+
		"\u023a\1\0\0\0\u023d\u0240\5I\0\0\u023f\u023d\1\0\0\0\u023f\u0240\1\0"+
		"\0\0\u0240\u0242\1\0\0\0\u0241\u0232\1\0\0\0\u0241\u0242\1\0\0\0\u0242"+
		"\u0243\1\0\0\0\u0243\u0246\5B\0\0\u0245\u022e\1\0\0\0\u0245\u0230\1\0"+
		"\0\0\u0246G\1\0\0\0\u0247\u0255\3>\37\0\u0249\u024f\3\0\0\0\u024b\u024d"+
		"\5:\0\0\u024d\u0250\3@ \0\u024f\u024b\1\0\0\0\u024f\u0250\1\0\0\0\u0250"+
		"\u0256\1\0\0\0\u0251\u0253\5:\0\0\u0253\u0256\3@ \0\u0255\u0249\1\0\0"+
		"\0\u0255\u0251\1\0\0\0\u0256I\1\0\0\0\u0257\u0259\3>\37\0\u0259\u025b"+
		"\5?\0\0\u025b\u025c\3@ \0\u025cK\1\0\0\0\u025d\u025f\5\r\0\0\u025f\u0261"+
		"\5K\0\0\u0261\u0265\3\34\16\0\u0263\u0266\3N\'\0\u0265\u0263\1\0\0\0\u0265"+
		"\u0266\1\0\0\0\u0266M\1\0\0\0\u0267\u0268\3\64\32\0\u0268O\1\0\0\0\u0269"+
		"\u026b\5\r\0\0\u026b\u026d\3R)\0\u026d\u026f\3*\25\0\u026f\u0273\3\34"+
		"\16\0\u0271\u0274\3N\'\0\u0273\u0271\1\0\0\0\u0273\u0274\1\0\0\0\u0274"+
		"Q\1\0\0\0\u0275\u0279\5A\0\0\u0277\u027a\5K\0\0\u0279\u0277\1\0\0\0\u0279"+
		"\u027a\1\0\0\0\u027a\u027d\1\0\0\0\u027b\u027e\5\36\0\0\u027d\u027b\1"+
		"\0\0\0\u027d\u027e\1\0\0\0\u027e\u027f\1\0\0\0\u027f\u0281\3T*\0\u0281"+
		"\u0282\5B\0\0\u0282S\1\0\0\0\u0283\u0284\5K\0\0\u0284U\1\0\0\0\u0285\u0292"+
		"\3X,\0\u0287\u0292\3\\.\0\u0289\u0292\3^/\0\u028b\u028d\5A\0\0\u028d\u028f"+
		"\3v;\0\u028f\u0290\5B\0\0\u0290\u0292\1\0\0\0\u0291\u0285\1\0\0\0\u0291"+
		"\u0287\1\0\0\0\u0291\u0289\1\0\0\0\u0291\u028b\1\0\0\0\u0292W\1\0\0\0"+
		"\u0293\u029a\3Z-\0\u0295\u029a\3b\61\0\u0297\u029a\3t:\0\u0299\u0293\1"+
		"\0\0\0\u0299\u0295\1\0\0\0\u0299\u0297\1\0\0\0\u029aY\1\0\0\0\u029b\u02a6"+
		"\5P\0\0\u029d\u02a6\5R\0\0\u029f\u02a6\5Q\0\0\u02a1\u02a6\5S\0\0\u02a3"+
		"\u02a6\5T\0\0\u02a5\u029b\1\0\0\0\u02a5\u029d\1\0\0\0\u02a5\u029f\1\0"+
		"\0\0\u02a5\u02a1\1\0\0\0\u02a5\u02a3\1\0\0\0\u02a6[\1\0\0\0\u02a7\u02a9"+
		"\4.\0\0\u02a9\u02ab\3\u00d6k\0\u02ab\u02ac\5H\0\0\u02ac\u02ae\1\0\0\0"+
		"\u02ad\u02a7\1\0\0\0\u02ad\u02ae\1\0\0\0\u02ae\u02af\1\0\0\0\u02af\u02b0"+
		"\5K\0\0\u02b0]\1\0\0\0\u02b1\u02b3\3`\60\0\u02b3\u02b5\5H\0\0\u02b5\u02b6"+
		"\3*\25\0\u02b6_\1\0\0\0\u02b7\u02c2\3\2\1\0\u02b9\u02bb\5A\0\0\u02bb\u02bd"+
		"\5\36\0\0\u02bd\u02bf\3\2\1\0\u02bf\u02c0\5B\0\0\u02c0\u02c2\1\0\0\0\u02c1"+
		"\u02b7\1\0\0\0\u02c1\u02b9\1\0\0\0\u02c2a\1\0\0\0\u02c3\u02c5\3d\62\0"+
		"\u02c5\u02c6\3f\63\0\u02c6c\1\0\0\0\u02c7\u02da\3\16\7\0\u02c9\u02da\3"+
		"\6\3\0\u02cb\u02cd\5C\0\0\u02cd\u02cf\5@\0\0\u02cf\u02d1\5D\0\0\u02d1"+
		"\u02da\3\n\5\0\u02d3\u02da\3\f\6\0\u02d5\u02da\3.\27\0\u02d7\u02da\3\2"+
		"\1\0\u02d9\u02c7\1\0\0\0\u02d9\u02c9\1\0\0\0\u02d9\u02cb\1\0\0\0\u02d9"+
		"\u02d3\1\0\0\0\u02d9\u02d5\1\0\0\0\u02d9\u02d7\1\0\0\0\u02dae\1\0\0\0"+
		"\u02db\u02e3\5E\0\0\u02dd\u02e1\3h\64\0\u02df\u02e2\5G\0\0\u02e1\u02df"+
		"\1\0\0\0\u02e1\u02e2\1\0\0\0\u02e2\u02e4\1\0\0\0\u02e3\u02dd\1\0\0\0\u02e3"+
		"\u02e4\1\0\0\0\u02e4\u02e5\1\0\0\0\u02e5\u02e6\5F\0\0\u02e6g\1\0\0\0\u02e7"+
		"\u02ef\3j\65\0\u02e9\u02eb\5G\0\0\u02eb\u02ee\3j\65\0\u02ed\u02e9\1\0"+
		"\0\0\u02ee\u02f1\1\0\0\0\u02ef\u02ed\1\0\0\0\u02ef\u02f0\1\0\0\0\u02f0"+
		"i\1\0\0\0\u02f1\u02ef\1\0\0\0\u02f2\u02f4\3l\66\0\u02f4\u02f5\5J\0\0\u02f5"+
		"\u02f7\1\0\0\0\u02f6\u02f2\1\0\0\0\u02f6\u02f7\1\0\0\0\u02f7\u02f8\1\0"+
		"\0\0\u02f8\u02f9\3r9\0\u02f9k\1\0\0\0\u02fa\u02ff\3n\67\0\u02fc\u02ff"+
		"\3p8\0\u02fe\u02fa\1\0\0\0\u02fe\u02fc\1\0\0\0\u02ffm\1\0\0\0\u0300\u0301"+
		"\5K\0\0\u0301o\1\0\0\0\u0302\u0303\3v;\0\u0303q\1\0\0\0\u0304\u0309\3"+
		"v;\0\u0306\u0309\3f\63\0\u0308\u0304\1\0\0\0\u0308\u0306\1\0\0\0\u0309"+
		"s\1\0\0\0\u030a\u030c\3\32\r\0\u030c\u030d\3N\'\0\u030du\1\0\0\0\u030e"+
		"\u031d\5\34\0\0\u0310\u031d\5\35\0\0\u0312\u031d\5;\0\0\u0314\u031d\5"+
		"#\0\0\u0316\u031d\5\36\0\0\u0318\u031d\5!\0\0\u031a\u031d\5\64\0\0\u031c"+
		"\u030e\1\0\0\0\u031c\u0310\1\0\0\0\u031c\u0312\1\0\0\0\u031c\u0314\1\0"+
		"\0\0\u031c\u0316\1\0\0\0\u031c\u0318\1\0\0\0\u031c\u031a\1\0\0\0\u031d"+
		"\u031e\1\0\0\0\u031e\u0327\3v;\0\u0320\u0327\3V+\0\u0322\u0327\3z=\0\u0324"+
		"\u0327\3\u00ceg\0\u0326\u031c\1\0\0\0\u0326\u0320\1\0\0\0\u0326\u0322"+
		"\1\0\0\0\u0326\u0324\1\0\0\0\u0327\u03a0\1\0\0\0\u0328\u0338\4;\1\1\u032a"+
		"\u0339\5\36\0\0\u032c\u0339\5\37\0\0\u032e\u0339\5 \0\0\u0330\u0339\5"+
		"$\0\0\u0332\u0339\5%\0\0\u0334\u0339\5!\0\0\u0336\u0339\5&\0\0\u0338\u032a"+
		"\1\0\0\0\u0338\u032c\1\0\0\0\u0338\u032e\1\0\0\0\u0338\u0330\1\0\0\0\u0338"+
		"\u0332\1\0\0\0\u0338\u0334\1\0\0\0\u0338\u0336\1\0\0\0\u0339\u033a\1\0"+
		"\0\0\u033a\u039f\3v;\0\u033c\u0346\4;\2\1\u033e\u0347\5\34\0\0\u0340\u0347"+
		"\5\35\0\0\u0342\u0347\5\"\0\0\u0344\u0347\5#\0\0\u0346\u033e\1\0\0\0\u0346"+
		"\u0340\1\0\0\0\u0346\u0342\1\0\0\0\u0346\u0344\1\0\0\0\u0347\u0348\1\0"+
		"\0\0\u0348\u039f\3v;\0\u034a\u0358\4;\3\1\u034c\u0359\5\67\0\0\u034e\u0359"+
		"\5<\0\0\u0350\u0359\58\0\0\u0352\u0359\5=\0\0\u0354\u0359\59\0\0\u0356"+
		"\u0359\5>\0\0\u0358\u034c\1\0\0\0\u0358\u034e\1\0\0\0\u0358\u0350\1\0"+
		"\0\0\u0358\u0352\1\0\0\0\u0358\u0354\1\0\0\0\u0358\u0356\1\0\0\0\u0359"+
		"\u035a\1\0\0\0\u035a\u039f\3v;\0\u035c\u035e\4;\4\1\u035e\u0360\5\62\0"+
		"\0\u0360\u039f\3v;\0\u0362\u0364\4;\5\1\u0364\u0366\5\63\0\0\u0366\u039f"+
		"\3v;\0\u0368\u036a\4;\6\1\u036a\u036c\5H\0\0\u036c\u039f\5K\0\0\u036e"+
		"\u0370\4;\7\1\u0370\u0372\5C\0\0\u0372\u0374\3v;\0\u0374\u0375\5D\0\0"+
		"\u0375\u039f\1\0\0\0\u0376\u0378\4;\b\1\u0378\u037c\5C\0\0\u037a\u037d"+
		"\3v;\0\u037c\u037a\1\0\0\0\u037c\u037d\1\0\0\0\u037d\u037e\1\0\0\0\u037e"+
		"\u0382\5J\0\0\u0380\u0383\3v;\0\u0382\u0380\1\0\0\0\u0382\u0383\1\0\0"+
		"\0\u0383\u0384\1\0\0\0\u0384\u039f\5D\0\0\u0386\u0388\4;\t\1\u0388\u038a"+
		"\5H\0\0\u038a\u038c\5A\0\0\u038c\u038e\3\0\0\0\u038e\u038f\5B\0\0\u038f"+
		"\u039f\1\0\0\0\u0390\u0392\4;\n\1\u0392\u039a\5A\0\0\u0394\u0398\3x<\0"+
		"\u0396\u0399\5G\0\0\u0398\u0396\1\0\0\0\u0398\u0399\1\0\0\0\u0399\u039b"+
		"\1\0\0\0\u039a\u0394\1\0\0\0\u039a\u039b\1\0\0\0\u039b\u039c\1\0\0\0\u039c"+
		"\u039f\5B\0\0\u039e\u0328\1\0\0\0\u039e\u033c\1\0\0\0\u039e\u034a\1\0"+
		"\0\0\u039e\u035c\1\0\0\0\u039e\u0362\1\0\0\0\u039e\u0368\1\0\0\0\u039e"+
		"\u036e\1\0\0\0\u039e\u0376\1\0\0\0\u039e\u0386\1\0\0\0\u039e\u0390\1\0"+
		"\0\0\u039f\u03a2\1\0\0\0\u03a0\u039e\1\0\0\0\u03a0\u03a1\1\0\0\0\u03a1"+
		"w\1\0\0\0\u03a2\u03a0\1\0\0\0\u03a3\u03a7\3@ \0\u03a5\u03a8\5@\0\0\u03a7"+
		"\u03a5\1\0\0\0\u03a7\u03a8\1\0\0\0\u03a8y\1\0\0\0\u03a9\u03ab\3\0\0\0"+
		"\u03ab\u03ad\5A\0\0\u03ad\u03af\3v;\0\u03af\u03b0\5B\0\0\u03b0{\1\0\0"+
		"\0\u03b1\u03d0\3\66\33\0\u03b3\u03d0\3\u0082A\0\u03b5\u03d0\3~?\0\u03b7"+
		"\u03d0\3\u00b6[\0\u03b9\u03d0\3\u00c2a\0\u03bb\u03d0\3\u00c4b\0\u03bd"+
		"\u03d0\3\u00c6c\0\u03bf\u03d0\3\u00c8d\0\u03c1\u03d0\3\u00cae\0\u03c3"+
		"\u03d0\3\64\32\0\u03c5\u03d0\3\u0096K\0\u03c7\u03d0\3\u0098L\0\u03c9\u03d0"+
		"\3\u00b8\\\0\u03cb\u03d0\3\u00aaU\0\u03cd\u03d0\3\u00ccf\0\u03cf\u03b1"+
		"\1\0\0\0\u03cf\u03b3\1\0\0\0\u03cf\u03b5\1\0\0\0\u03cf\u03b7\1\0\0\0\u03cf"+
		"\u03b9\1\0\0\0\u03cf\u03bb\1\0\0\0\u03cf\u03bd\1\0\0\0\u03cf\u03bf\1\0"+
		"\0\0\u03cf\u03c1\1\0\0\0\u03cf\u03c3\1\0\0\0\u03cf\u03c5\1\0\0\0\u03cf"+
		"\u03c7\1\0\0\0\u03cf\u03c9\1\0\0\0\u03cf\u03cb\1\0\0\0\u03cf\u03cd\1\0"+
		"\0\0\u03d0}\1\0\0\0\u03d1\u03de\3\u0080@\0\u03d3\u03de\3\u0086C\0\u03d5"+
		"\u03de\3\u0088D\0\u03d7\u03de\3\u008cF\0\u03d9\u03de\3\u008eG\0\u03db"+
		"\u03de\3J%\0\u03dd\u03d1\1\0\0\0\u03dd\u03d3\1\0\0\0\u03dd\u03d5\1\0\0"+
		"\0\u03dd\u03d7\1\0\0\0\u03dd\u03d9\1\0\0\0\u03dd\u03db\1\0\0\0\u03de\177"+
		"\1\0\0\0\u03df\u03e0\1\0\0\0\u03e0\u0081\1\0\0\0\u03e1\u03e3\3\u0084B"+
		"\0\u03e3\u03e5\5J\0\0\u03e5\u03e6\3|>\0\u03e6\u0083\1\0\0\0\u03e7\u03e8"+
		"\5K\0\0\u03e8\u0085\1\0\0\0\u03e9\u03ea\3v;\0\u03ea\u0087\1\0\0\0\u03eb"+
		"\u03ed\3\u008aE\0\u03ed\u03ef\5\64\0\0\u03ef\u03f0\3v;\0\u03f0\u0089\1"+
		"\0\0\0\u03f1\u03f2\3v;\0\u03f2\u008b\1\0\0\0\u03f3\u03f5\3v;\0\u03f5\u03f6"+
		"\7\0\0\0\u03f6\u008d\1\0\0\0\u03f7\u03f9\3@ \0\u03f9\u03fb\3\u0090H\0"+
		"\u03fb\u03fc\3@ \0\u03fc\u008f\1\0\0\0\u03fd\u0404\3\u0092I\0\u03ff\u0404"+
		"\3\u0094J\0\u0401\u0404\5:\0\0\u0403\u03fd\1\0\0\0\u0403\u03ff\1\0\0\0"+
		"\u0403\u0401\1\0\0\0\u0404\u0091\1\0\0\0\u0405\u040e\5\'\0\0\u0407\u040e"+
		"\5(\0\0\u0409\u040e\5-\0\0\u040b\u040e\5.\0\0\u040d\u0405\1\0\0\0\u040d"+
		"\u0407\1\0\0\0\u040d\u0409\1\0\0\0\u040d\u040b\1\0\0\0\u040e\u0093\1\0"+
		"\0\0\u040f\u041e\5)\0\0\u0411\u041e\5*\0\0\u0413\u041e\5.\0\0\u0415\u041e"+
		"\5/\0\0\u0417\u041e\5\60\0\0\u0419\u041e\5,\0\0\u041b\u041e\5\61\0\0\u041d"+
		"\u040f\1\0\0\0\u041d\u0411\1\0\0\0\u041d\u0413\1\0\0\0\u041d\u0415\1\0"+
		"\0\0\u041d\u0417\1\0\0\0\u041d\u0419\1\0\0\0\u041d\u041b\1\0\0\0\u041e"+
		"\u0095\1\0\0\0\u041f\u0425\5\20\0\0\u0421\u0423\3~?\0\u0423\u0424\5I\0"+
		"\0\u0424\u0426\1\0\0\0\u0425\u0421\1\0\0\0\u0425\u0426\1\0\0\0\u0426\u0427"+
		"\1\0\0\0\u0427\u0429\3v;\0\u0429\u0433\3\64\32\0\u042b\u0431\5\n\0\0\u042d"+
		"\u0432\3\u0096K\0\u042f\u0432\3\64\32\0\u0431\u042d\1\0\0\0\u0431\u042f"+
		"\1\0\0\0\u0432\u0434\1\0\0\0\u0433\u042b\1\0\0\0\u0433\u0434\1\0\0\0\u0434"+
		"\u0097\1\0\0\0\u0435\u043a\3\u009aM\0\u0437\u043a\3\u00a0P\0\u0439\u0435"+
		"\1\0\0\0\u0439\u0437\1\0\0\0\u043a\u0099\1\0\0\0\u043b\u0441\5\31\0\0"+
		"\u043d\u043f\3~?\0\u043f\u0440\5I\0\0\u0440\u0442\1\0\0\0\u0441\u043d"+
		"\1\0\0\0\u0441\u0442\1\0\0\0\u0442\u0445\1\0\0\0\u0443\u0446\3v;\0\u0445"+
		"\u0443\1\0\0\0\u0445\u0446\1\0\0\0\u0446\u0447\1\0\0\0\u0447\u044d\5E"+
		"\0\0\u0449\u044c\3\u009cN\0\u044b\u0449\1\0\0\0\u044c\u044f\1\0\0\0\u044d"+
		"\u044b\1\0\0\0\u044d\u044e\1\0\0\0\u044e\u0450\1\0\0\0\u044f\u044d\1\0"+
		"\0\0\u0450\u0451\5F\0\0\u0451\u009b\1\0\0\0\u0452\u0454\3\u009eO\0\u0454"+
		"\u0465\5J\0\0\u0456\u045e\3|>\0\u0458\u045a\5I\0\0\u045a\u045d\3|>\0\u045c"+
		"\u0458\1\0\0\0\u045d\u0460\1\0\0\0\u045e\u045c\1\0\0\0\u045e\u045f\1\0"+
		"\0\0\u045f\u0463\1\0\0\0\u0460\u045e\1\0\0\0\u0461\u0464\5I\0\0\u0463"+
		"\u0461\1\0\0\0\u0463\u0464\1\0\0\0\u0464\u0466\1\0\0\0\u0465\u0456\1\0"+
		"\0\0\u0465\u0466\1\0\0\0\u0466\u009d\1\0\0\0\u0467\u0469\5\4\0\0\u0469"+
		"\u046e\3@ \0\u046b\u046e\5\b\0\0\u046d\u0467\1\0\0\0\u046d\u046b\1\0\0"+
		"\0\u046e\u009f\1\0\0\0\u046f\u0475\5\31\0\0\u0471\u0473\3~?\0\u0473\u0474"+
		"\5I\0\0\u0474\u0476\1\0\0\0\u0475\u0471\1\0\0\0\u0475\u0476\1\0\0\0\u0476"+
		"\u0477\1\0\0\0\u0477\u0479\3\u00a2Q\0\u0479\u047f\5E\0\0\u047b\u047e\3"+
		"\u00a4R\0\u047d\u047b\1\0\0\0\u047e\u0481\1\0\0\0\u047f\u047d\1\0\0\0"+
		"\u047f\u0480\1\0\0\0\u0480\u0482\1\0\0\0\u0481\u047f\1\0\0\0\u0482\u0483"+
		"\5F\0\0\u0483\u00a1\1\0\0\0\u0484\u0486\5K\0\0\u0486\u0489\5?\0\0\u0488"+
		"\u0484\1\0\0\0\u0488\u0489\1\0\0\0\u0489\u048a\1\0\0\0\u048a\u048c\3v"+
		";\0\u048c\u048e\5H\0\0\u048e\u0490\5A\0\0\u0490\u0492\5\32\0\0\u0492\u0493"+
		"\5B\0\0\u0493\u00a3\1\0\0\0\u0494\u0496\3\u00a6S\0\u0496\u04a7\5J\0\0"+
		"\u0498\u04a0\3|>\0\u049a\u049c\5I\0\0\u049c\u049f\3|>\0\u049e\u049a\1"+
		"\0\0\0\u049f\u04a2\1\0\0\0\u04a0\u049e\1\0\0\0\u04a0\u04a1\1\0\0\0\u04a1"+
		"\u04a5\1\0\0\0\u04a2\u04a0\1\0\0\0\u04a3\u04a6\5I\0\0\u04a5\u04a3\1\0"+
		"\0\0\u04a5\u04a6\1\0\0\0\u04a6\u04a8\1\0\0\0\u04a7\u0498\1\0\0\0\u04a7"+
		"\u04a8\1\0\0\0\u04a8\u00a5\1\0\0\0\u04a9\u04ab\5\4\0\0\u04ab\u04b0\3\u00a8"+
		"T\0\u04ad\u04b0\5\b\0\0\u04af\u04a9\1\0\0\0\u04af\u04ad\1\0\0\0\u04b0"+
		"\u00a7\1\0\0\0\u04b1\u04b9\3\0\0\0\u04b3\u04b5\5G\0\0\u04b5\u04b8\3\0"+
		"\0\0\u04b7\u04b3\1\0\0\0\u04b8\u04bb\1\0\0\0\u04b9\u04b7\1\0\0\0\u04b9"+
		"\u04ba\1\0\0\0\u04ba\u00a9\1\0\0\0\u04bb\u04b9\1\0\0\0\u04bc\u04c4\5\f"+
		"\0\0\u04be\u04c5\3\u00acV\0\u04c0\u04c5\3\u00aeW\0\u04c2\u04c5\3\u00b4"+
		"Z\0\u04c4\u04be\1\0\0\0\u04c4\u04c0\1\0\0\0\u04c4\u04c2\1\0\0\0\u04c4"+
		"\u04c5\1\0\0\0\u04c5\u04c6\1\0\0\0\u04c6\u04c7\3\64\32\0\u04c7\u00ab\1"+
		"\0\0\0\u04c8\u04c9\3v;\0\u04c9\u00ad\1\0\0\0\u04ca\u04cd\3\u00b0X\0\u04cc"+
		"\u04ca\1\0\0\0\u04cc\u04cd\1\0\0\0\u04cd\u04ce\1\0\0\0\u04ce\u04d2\5I"+
		"\0\0\u04d0\u04d3\3\u00acV\0\u04d2\u04d0\1\0\0\0\u04d2\u04d3\1\0\0\0\u04d3"+
		"\u04d4\1\0\0\0\u04d4\u04d8\5I\0\0\u04d6\u04d9\3\u00b2Y\0\u04d8\u04d6\1"+
		"\0\0\0\u04d8\u04d9\1\0\0\0\u04d9\u00af\1\0\0\0\u04da\u04db\3~?\0\u04db"+
		"\u00b1\1\0\0\0\u04dc\u04dd\3~?\0\u04dd\u00b3\1\0\0\0\u04de\u04e4\3v;\0"+
		"\u04e0\u04e2\5G\0\0\u04e2\u04e5\3v;\0\u04e4\u04e0\1\0\0\0\u04e4\u04e5"+
		"\1\0\0\0\u04e5\u04ea\1\0\0\0\u04e6\u04eb\5:\0\0\u04e8\u04eb\5?\0\0\u04ea"+
		"\u04e6\1\0\0\0\u04ea\u04e8\1\0\0\0\u04eb\u04ec\1\0\0\0\u04ec\u04ee\5\25"+
		"\0\0\u04ee\u04ef\3v;\0\u04ef\u00b5\1\0\0\0\u04f0\u04f2\5\16\0\0\u04f2"+
		"\u04f3\3v;\0\u04f3\u00b7\1\0\0\0\u04f4\u04f6\5\27\0\0\u04f6\u04fc\5E\0"+
		"\0\u04f8\u04fb\3\u00ba]\0\u04fa\u04f8\1\0\0\0\u04fb\u04fe\1\0\0\0\u04fc"+
		"\u04fa\1\0\0\0\u04fc\u04fd\1\0\0\0\u04fd\u04ff\1\0\0\0\u04fe\u04fc\1\0"+
		"\0\0\u04ff\u0500\5F\0\0\u0500\u00b9\1\0\0\0\u0501\u0503\3\u00bc^\0\u0503"+
		"\u0514\5J\0\0\u0505\u050d\3|>\0\u0507\u0509\5I\0\0\u0509\u050c\3|>\0\u050b"+
		"\u0507\1\0\0\0\u050c\u050f\1\0\0\0\u050d\u050b\1\0\0\0\u050d\u050e\1\0"+
		"\0\0\u050e\u0512\1\0\0\0\u050f\u050d\1\0\0\0\u0510\u0513\5I\0\0\u0512"+
		"\u0510\1\0\0\0\u0512\u0513\1\0\0\0\u0513\u0515\1\0\0\0\u0514\u0505\1\0"+
		"\0\0\u0514\u0515\1\0\0\0\u0515\u00bb\1\0\0\0\u0516\u051c\5\4\0\0\u0518"+
		"\u051d\3\u0088D\0\u051a\u051d\3\u00be_\0\u051c\u0518\1\0\0\0\u051c\u051a"+
		"\1\0\0\0\u051d\u0521\1\0\0\0\u051e\u0521\5\b\0\0\u0520\u0516\1\0\0\0\u0520"+
		"\u051e\1\0\0\0\u0521\u00bd\1\0\0\0\u0522\u0528\3v;\0\u0524\u0526\5G\0"+
		"\0\u0526\u0529\3v;\0\u0528\u0524\1\0\0\0\u0528\u0529\1\0\0\0\u0529\u052e"+
		"\1\0\0\0\u052a\u052f\5:\0\0\u052c\u052f\5?\0\0\u052e\u052a\1\0\0\0\u052e"+
		"\u052c\1\0\0\0\u052f\u0531\1\0\0\0\u0530\u0522\1\0\0\0\u0530\u0531\1\0"+
		"\0\0\u0531\u0532\1\0\0\0\u0532\u0533\3\u00c0`\0\u0533\u00bf\1\0\0\0\u0534"+
		"\u0535\3v;\0\u0535\u00c1\1\0\0\0\u0536\u053a\5\26\0\0\u0538\u053b\3@ "+
		"\0\u053a\u0538\1\0\0\0\u053a\u053b\1\0\0\0\u053b\u00c3\1\0\0\0\u053c\u0540"+
		"\5\3\0\0\u053e\u0541\3\u0084B\0\u0540\u053e\1\0\0\0\u0540\u0541\1\0\0"+
		"\0\u0541\u00c5\1\0\0\0\u0542\u0546\5\7\0\0\u0544\u0547\3\u0084B\0\u0546"+
		"\u0544\1\0\0\0\u0546\u0547\1\0\0\0\u0547\u00c7\1\0\0\0\u0548\u054a\5\17"+
		"\0\0\u054a\u054b\3\u0084B\0\u054b\u00c9\1\0\0\0\u054c\u054d\5\13\0\0\u054d"+
		"\u00cb\1\0\0\0\u054e\u0550\5\t\0\0\u0550\u0551\3v;\0\u0551\u00cd\1\0\0"+
		"\0\u0552\u0554\5K\0\0\u0554\u055c\5A\0\0\u0556\u055a\3\u00d0h\0\u0558"+
		"\u055b\5G\0\0\u055a\u0558\1\0\0\0\u055a\u055b\1\0\0\0\u055b\u055d\1\0"+
		"\0\0\u055c\u0556\1\0\0\0\u055c\u055d\1\0\0\0\u055d\u055e\1\0\0\0\u055e"+
		"\u055f\5B\0\0\u055f\u00cf\1\0\0\0\u0560\u0566\3\0\0\0\u0562\u0564\5G\0"+
		"\0\u0564\u0567\3@ \0\u0566\u0562\1\0\0\0\u0566\u0567\1\0\0\0\u0567\u056b"+
		"\1\0\0\0\u0568\u056b\3@ \0\u056a\u0560\1\0\0\0\u056a\u0568\1\0\0\0\u056b"+
		"\u00d1\1\0\0\0\u056c\u056e\3\u00d4j\0\u056e\u056f\5I\0\0\u056f\u0571\1"+
		"\0\0\0\u0570\u056c\1\0\0\0\u0570\u0571\1\0\0\0\u0571\u0578\1\0\0\0\u0572"+
		"\u0574\3\u00d8l\0\u0574\u0575\5I\0\0\u0575\u0577\1\0\0\0\u0576\u0572\1"+
		"\0\0\0\u0577\u057a\1\0\0\0\u0578\u0576\1\0\0\0\u0578\u0579\1\0\0\0\u0579"+
		"\u0581\1\0\0\0\u057a\u0578\1\0\0\0\u057b\u057d\38\34\0\u057d\u057e\5I"+
		"\0\0\u057e\u0580\1\0\0\0\u057f\u057b\1\0\0\0\u0580\u0583\1\0\0\0\u0581"+
		"\u057f\1\0\0\0\u0581\u0582\1\0\0\0\u0582\u00d3\1\0\0\0\u0583\u0581\1\0"+
		"\0\0\u0584\u0586\5\24\0\0\u0586\u0588\3\u00d6k\0\u0588\u0589\6j\uffff"+
		"\0\u0589\u00d5\1\0\0\0\u058a\u058b\5K\0\0\u058b\u00d7\1\0\0\0\u058c\u05a5"+
		"\5\21\0\0\u058e\u05a6\3\u00dam\0\u0590\u05a1\5A\0\0\u0592\u059a\3\u00da"+
		"m\0\u0594\u0596\5I\0\0\u0596\u0599\3\u00dam\0\u0598\u0594\1\0\0\0\u0599"+
		"\u059c\1\0\0\0\u059a\u0598\1\0\0\0\u059a\u059b\1\0\0\0\u059b\u059f\1\0"+
		"\0\0\u059c\u059a\1\0\0\0\u059d\u05a0\5I\0\0\u059f\u059d\1\0\0\0\u059f"+
		"\u05a0\1\0\0\0\u05a0\u05a2\1\0\0\0\u05a1\u0592\1\0\0\0\u05a1\u05a2\1\0"+
		"\0\0\u05a2\u05a3\1\0\0\0\u05a3\u05a6\5B\0\0\u05a5\u058e\1\0\0\0\u05a5"+
		"\u0590\1\0\0\0\u05a6\u00d9\1\0\0\0\u05a7\u05a9\5H\0\0\u05a9\u05b6\3\u00dc"+
		"n\0\u05ab\u05ad\3\u00d6k\0\u05ad\u05af\3\u00dcn\0\u05af\u05b0\6m\uffff"+
		"\0\u05b0\u05b6\1\0\0\0\u05b1\u05b3\3\u00dcn\0\u05b3\u05b4\6m\uffff\0\u05b4"+
		"\u05b6\1\0\0\0\u05b5\u05a7\1\0\0\0\u05b5\u05ab\1\0\0\0\u05b5\u05b1\1\0"+
		"\0\0\u05b6\u00db\1\0\0\0\u05b7\u05b8\5T\0\0\u05b8\u00dd\1\0\0\0}\u00e8"+
		"\1\u00fc\1\u011a\1\u011f\1\u0129\1\u012d\1\u0131\1\u0145\1\u014b\1\u0153"+
		"\1\u0155\1\u0161\1\u0166\1\u016a\1\u017a\1\u017f\1\u0181\1\u018b\1\u01a1"+
		"\1\u01a7\1\u01b5\1\u01ba\1\u01bc\1\u01c6\1\u01ce\1\u01de\1\u01e3\1\u01e5"+
		"\1\u01e9\1\u01ef\1\u01f5\1\u01ff\1\u020a\1\u021b\1\u0220\1\u0222\1\u0226"+
		"\1\u023a\1\u023f\1\u0241\1\u0245\1\u024f\1\u0255\1\u0265\1\u0273\1\u0279"+
		"\1\u027d\1\u0291\1\u0299\1\u02a5\1\u02ad\1\u02c1\1\u02d9\1\u02e1\1\u02e3"+
		"\1\u02ef\1\u02f6\1\u02fe\1\u0308\1\u031c\1\u0326\1\u0338\1\u0346\1\u0358"+
		"\1\u037c\1\u0382\1\u0398\1\u039a\1\u039e\1\u03a0\1\u03a7\1\u03cf\1\u03dd"+
		"\1\u0403\1\u040d\1\u041d\1\u0425\1\u0431\1\u0433\1\u0439\1\u0441\1\u0445"+
		"\1\u044d\1\u045e\1\u0463\1\u0465\1\u046d\1\u0475\1\u047f\1\u0488\1\u04a0"+
		"\1\u04a5\1\u04a7\1\u04af\1\u04b9\1\u04c4\1\u04cc\1\u04d2\1\u04d8\1\u04e4"+
		"\1\u04ea\1\u04fc\1\u050d\1\u0512\1\u0514\1\u051c\1\u0520\1\u0528\1\u052e"+
		"\1\u0530\1\u053a\1\u0540\1\u0546\1\u055a\1\u055c\1\u0566\1\u056a\1\u0570"+
		"\1\u0578\1\u0581\1\u059a\1\u059f\1\u05a1\1\u05a5\1\u05b5\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}