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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
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

	public GoParserBase(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class typeContext extends ParserRuleContext<Token> {
		public typeNameContext name;
		public typeLiteralContext lit;
		public Token lp;
		public typeContext t;
		public Token rp;
		public typeNameContext typeName() {
		    return (typeNameContext)getRuleContext(typeNameContext.class,0);
		}
		public typeLiteralContext typeLiteral() {
		    return (typeLiteralContext)getRuleContext(typeLiteralContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public typeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeVisit(this);
		    else return null;
		}
	}

	public final typeContext type() throws RecognitionException {
		typeContext _localctx = new typeContext(_ctx, 0);
		enterRule(_localctx, RULE_type);
		try {
			setState(232);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(222); ((typeContext)_localctx).name = typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(224); ((typeContext)_localctx).lit = typeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(226); ((typeContext)_localctx).lp = match(LeftParen);
					setState(228); ((typeContext)_localctx).t = type();
					setState(230); ((typeContext)_localctx).rp = match(RightParen);
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
		public qualifiedIdentifierContext qid;
		public qualifiedIdentifierContext qualifiedIdentifier() {
		    return (qualifiedIdentifierContext)getRuleContext(qualifiedIdentifierContext.class,0);
		}
		public typeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeNameVisit(this);
		    else return null;
		}
	}

	public final typeNameContext typeName() throws RecognitionException {
		typeNameContext _localctx = new typeNameContext(_ctx, 2);
		enterRule(_localctx, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); ((typeNameContext)_localctx).qid = qualifiedIdentifier();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public arrayTypeContext arrType;
		public structTypeContext strType;
		public pointerTypeContext ptrType;
		public functionTypeContext fnType;
		public interfaceTypeContext ifaceType;
		public sliceTypeContext slcType;
		public mapTypeContext maptype;
		public channelTypeContext chanType;
		public pointerTypeContext pointerType() {
		    return (pointerTypeContext)getRuleContext(pointerTypeContext.class,0);
		}
		public channelTypeContext channelType() {
		    return (channelTypeContext)getRuleContext(channelTypeContext.class,0);
		}
		public interfaceTypeContext interfaceType() {
		    return (interfaceTypeContext)getRuleContext(interfaceTypeContext.class,0);
		}
		public functionTypeContext functionType() {
		    return (functionTypeContext)getRuleContext(functionTypeContext.class,0);
		}
		public arrayTypeContext arrayType() {
		    return (arrayTypeContext)getRuleContext(arrayTypeContext.class,0);
		}
		public structTypeContext structType() {
		    return (structTypeContext)getRuleContext(structTypeContext.class,0);
		}
		public sliceTypeContext sliceType() {
		    return (sliceTypeContext)getRuleContext(sliceTypeContext.class,0);
		}
		public mapTypeContext mapType() {
		    return (mapTypeContext)getRuleContext(mapTypeContext.class,0);
		}
		public typeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeLiteralEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeLiteralExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeLiteralVisit(this);
		    else return null;
		}
	}

	public final typeLiteralContext typeLiteral() throws RecognitionException {
		typeLiteralContext _localctx = new typeLiteralContext(_ctx, 4);
		enterRule(_localctx, RULE_typeLiteral);
		try {
			setState(252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(236); ((typeLiteralContext)_localctx).arrType = arrayType();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(238); ((typeLiteralContext)_localctx).strType = structType();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(240); ((typeLiteralContext)_localctx).ptrType = pointerType();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(242); ((typeLiteralContext)_localctx).fnType = functionType();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(244); ((typeLiteralContext)_localctx).ifaceType = interfaceType();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(246); ((typeLiteralContext)_localctx).slcType = sliceType();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(248); ((typeLiteralContext)_localctx).maptype = mapType();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(250); ((typeLiteralContext)_localctx).chanType = channelType();
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
		public arrayLengthContext len;
		public elementTypeContext elemType;
		public arrayLengthContext arrayLength() {
		    return (arrayLengthContext)getRuleContext(arrayLengthContext.class,0);
		}
		public elementTypeContext elementType() {
		    return (elementTypeContext)getRuleContext(elementTypeContext.class,0);
		}
		public arrayTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).arrayTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).arrayTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).arrayTypeVisit(this);
		    else return null;
		}
	}

	public final arrayTypeContext arrayType() throws RecognitionException {
		arrayTypeContext _localctx = new arrayTypeContext(_ctx, 6);
		enterRule(_localctx, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254); match(LeftBrack);
			setState(256); ((arrayTypeContext)_localctx).len = arrayLength();
			setState(258); match(RightBrack);
			setState(260); ((arrayTypeContext)_localctx).elemType = elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext expr;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public arrayLengthContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).arrayLengthEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).arrayLengthExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).arrayLengthVisit(this);
		    else return null;
		}
	}

	public final arrayLengthContext arrayLength() throws RecognitionException {
		arrayLengthContext _localctx = new arrayLengthContext(_ctx, 8);
		enterRule(_localctx, RULE_arrayLength);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262); ((arrayLengthContext)_localctx).expr = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeContext typ;
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public elementTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).elementTypeVisit(this);
		    else return null;
		}
	}

	public final elementTypeContext elementType() throws RecognitionException {
		elementTypeContext _localctx = new elementTypeContext(_ctx, 10);
		enterRule(_localctx, RULE_elementType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); ((elementTypeContext)_localctx).typ = type();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public elementTypeContext elemType;
		public elementTypeContext elementType() {
		    return (elementTypeContext)getRuleContext(elementTypeContext.class,0);
		}
		public sliceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sliceTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sliceTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).sliceTypeVisit(this);
		    else return null;
		}
	}

	public final sliceTypeContext sliceType() throws RecognitionException {
		sliceTypeContext _localctx = new sliceTypeContext(_ctx, 12);
		enterRule(_localctx, RULE_sliceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266); match(LeftBrack);
			setState(268); match(RightBrack);
			setState(270); ((sliceTypeContext)_localctx).elemType = elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public fieldDeclContext fieldDecl;
		public List<fieldDeclContext> fields = new ArrayList<fieldDeclContext>();
		public fieldDeclContext fieldDecl(int i) {
		    return (fieldDeclContext)getRuleContext(fieldDeclContext.class,i);
		}
		public List<? extends fieldDeclContext> fieldDecl() {
		    return (List<fieldDeclContext>)getRuleContexts(fieldDeclContext.class);
		}
		public structTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).structTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).structTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).structTypeVisit(this);
		    else return null;
		}
	}

	public final structTypeContext structType() throws RecognitionException {
		structTypeContext _localctx = new structTypeContext(_ctx, 14);
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
					setState(276); ((structTypeContext)_localctx).fieldDecl = fieldDecl();
					((structTypeContext)_localctx).fields.add(((structTypeContext)_localctx).fieldDecl);
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
					setState(285); ((structTypeContext)_localctx).fieldDecl = fieldDecl();
					((structTypeContext)_localctx).fields.add(((structTypeContext)_localctx).fieldDecl);
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

	public static class fieldDeclContext extends ParserRuleContext<Token> {
		public identifierListContext idList;
		public typeContext fieldType;
		public anonymousFieldContext anonField;
		public tagContext fieldTag;
		public tagContext tag() {
		    return (tagContext)getRuleContext(tagContext.class,0);
		}
		public anonymousFieldContext anonymousField() {
		    return (anonymousFieldContext)getRuleContext(anonymousFieldContext.class,0);
		}
		public identifierListContext identifierList() {
		    return (identifierListContext)getRuleContext(identifierListContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public fieldDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fieldDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fieldDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).fieldDeclVisit(this);
		    else return null;
		}
	}

	public final fieldDeclContext fieldDecl() throws RecognitionException {
		fieldDeclContext _localctx = new fieldDeclContext(_ctx, 16);
		enterRule(_localctx, RULE_fieldDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(291); ((fieldDeclContext)_localctx).idList = identifierList();
					setState(293); ((fieldDeclContext)_localctx).fieldType = type();
					}
					break;

				case 2:
					{
					setState(295); ((fieldDeclContext)_localctx).anonField = anonymousField();
					}
					break;
			}
			setState(301);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(299); ((fieldDeclContext)_localctx).fieldTag = tag();
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
		public Token ptr;
		public typeNameContext fieldType;
		public typeNameContext typeName() {
		    return (typeNameContext)getRuleContext(typeNameContext.class,0);
		}
		public anonymousFieldContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).anonymousFieldEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).anonymousFieldExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).anonymousFieldVisit(this);
		    else return null;
		}
	}

	public final anonymousFieldContext anonymousField() throws RecognitionException {
		anonymousFieldContext _localctx = new anonymousFieldContext(_ctx, 18);
		enterRule(_localctx, RULE_anonymousField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(303); ((anonymousFieldContext)_localctx).ptr = match(Star);
					}
					break;
			}
			setState(307); ((anonymousFieldContext)_localctx).fieldType = typeName();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public tagContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).tagEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).tagExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).tagVisit(this);
		    else return null;
		}
	}

	public final tagContext tag() throws RecognitionException {
		tagContext _localctx = new tagContext(_ctx, 20);
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

	public static class pointerTypeContext extends ParserRuleContext<Token> {
		public Token ptr;
		public baseTypeContext typ;
		public baseTypeContext baseType() {
		    return (baseTypeContext)getRuleContext(baseTypeContext.class,0);
		}
		public pointerTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).pointerTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).pointerTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).pointerTypeVisit(this);
		    else return null;
		}
	}

	public final pointerTypeContext pointerType() throws RecognitionException {
		pointerTypeContext _localctx = new pointerTypeContext(_ctx, 22);
		enterRule(_localctx, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311); ((pointerTypeContext)_localctx).ptr = match(Star);
			setState(313); ((pointerTypeContext)_localctx).typ = baseType();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeContext typ;
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public baseTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).baseTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).baseTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).baseTypeVisit(this);
		    else return null;
		}
	}

	public final baseTypeContext baseType() throws RecognitionException {
		baseTypeContext _localctx = new baseTypeContext(_ctx, 24);
		enterRule(_localctx, RULE_baseType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); ((baseTypeContext)_localctx).typ = type();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public signatureContext sig;
		public signatureContext signature() {
		    return (signatureContext)getRuleContext(signatureContext.class,0);
		}
		public functionTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).functionTypeVisit(this);
		    else return null;
		}
	}

	public final functionTypeContext functionType() throws RecognitionException {
		functionTypeContext _localctx = new functionTypeContext(_ctx, 26);
		enterRule(_localctx, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); match(Func);
			setState(319); ((functionTypeContext)_localctx).sig = signature();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public parametersContext params;
		public resultContext res;
		public resultContext result() {
		    return (resultContext)getRuleContext(resultContext.class,0);
		}
		public parametersContext parameters() {
		    return (parametersContext)getRuleContext(parametersContext.class,0);
		}
		public signatureContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).signatureEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).signatureExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).signatureVisit(this);
		    else return null;
		}
	}

	public final signatureContext signature() throws RecognitionException {
		signatureContext _localctx = new signatureContext(_ctx, 28);
		enterRule(_localctx, RULE_signature);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321); ((signatureContext)_localctx).params = parameters();
			setState(325);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(323); ((signatureContext)_localctx).res = result();
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
		public parametersContext params;
		public typeContext t;
		public parametersContext parameters() {
		    return (parametersContext)getRuleContext(parametersContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public resultContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).resultEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).resultExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).resultVisit(this);
		    else return null;
		}
	}

	public final resultContext result() throws RecognitionException {
		resultContext _localctx = new resultContext(_ctx, 30);
		enterRule(_localctx, RULE_result);
		try {
			setState(331);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(327); ((resultContext)_localctx).params = parameters();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(329); ((resultContext)_localctx).t = type();
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
		public parameterListContext params;
		public parameterListContext parameterList() {
		    return (parameterListContext)getRuleContext(parameterListContext.class,0);
		}
		public parametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parametersEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parametersExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).parametersVisit(this);
		    else return null;
		}
	}

	public final parametersContext parameters() throws RecognitionException {
		parametersContext _localctx = new parametersContext(_ctx, 32);
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
					setState(335); ((parametersContext)_localctx).params = parameterList();
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

	public static class parameterListContext extends ParserRuleContext<Token> {
		public parameterDeclContext parameterDecl;
		public List<parameterDeclContext> params = new ArrayList<parameterDeclContext>();
		public List<? extends parameterDeclContext> parameterDecl() {
		    return (List<parameterDeclContext>)getRuleContexts(parameterDeclContext.class);
		}
		public parameterDeclContext parameterDecl(int i) {
		    return (parameterDeclContext)getRuleContext(parameterDeclContext.class,i);
		}
		public parameterListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parameterListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parameterListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).parameterListVisit(this);
		    else return null;
		}
	}

	public final parameterListContext parameterList() throws RecognitionException {
		parameterListContext _localctx = new parameterListContext(_ctx, 34);
		enterRule(_localctx, RULE_parameterList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); ((parameterListContext)_localctx).parameterDecl = parameterDecl();
			((parameterListContext)_localctx).params.add(((parameterListContext)_localctx).parameterDecl);
			setState(353);
			_errHandler.sync(this);
			int _alt350 = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt350!=2 && _alt350!=-1 ) {
				if ( _alt350==1 ) {
					{
					{
					setState(347); match(Comma);
					setState(349); ((parameterListContext)_localctx).parameterDecl = parameterDecl();
					((parameterListContext)_localctx).params.add(((parameterListContext)_localctx).parameterDecl);
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

	public static class parameterDeclContext extends ParserRuleContext<Token> {
		public identifierListContext idList;
		public Token ellip;
		public typeContext t;
		public identifierListContext identifierList() {
		    return (identifierListContext)getRuleContext(identifierListContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public parameterDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parameterDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).parameterDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).parameterDeclVisit(this);
		    else return null;
		}
	}

	public final parameterDeclContext parameterDecl() throws RecognitionException {
		parameterDeclContext _localctx = new parameterDeclContext(_ctx, 36);
		enterRule(_localctx, RULE_parameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(356); ((parameterDeclContext)_localctx).idList = identifierList();
					}
					break;
			}
			setState(362);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(360); ((parameterDeclContext)_localctx).ellip = match(Ellipsis);
					}
					break;
			}
			setState(364); ((parameterDeclContext)_localctx).t = type();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public methodSpecContext methodSpec;
		public List<methodSpecContext> methods = new ArrayList<methodSpecContext>();
		public methodSpecContext methodSpec(int i) {
		    return (methodSpecContext)getRuleContext(methodSpecContext.class,i);
		}
		public List<? extends methodSpecContext> methodSpec() {
		    return (List<methodSpecContext>)getRuleContexts(methodSpecContext.class);
		}
		public interfaceTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).interfaceTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).interfaceTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).interfaceTypeVisit(this);
		    else return null;
		}
	}

	public final interfaceTypeContext interfaceType() throws RecognitionException {
		interfaceTypeContext _localctx = new interfaceTypeContext(_ctx, 38);
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
					setState(370); ((interfaceTypeContext)_localctx).methodSpec = methodSpec();
					((interfaceTypeContext)_localctx).methods.add(((interfaceTypeContext)_localctx).methodSpec);
					setState(378);
					_errHandler.sync(this);
					int _alt394 = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt394!=2 && _alt394!=-1 ) {
						if ( _alt394==1 ) {
							{
							{
							setState(372); match(Semi);
							setState(374); ((interfaceTypeContext)_localctx).methodSpec = methodSpec();
							((interfaceTypeContext)_localctx).methods.add(((interfaceTypeContext)_localctx).methodSpec);
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

	public static class methodSpecContext extends ParserRuleContext<Token> {
		public methodNameContext name;
		public signatureContext sig;
		public interfaceTypeNameContext ifaceName;
		public methodNameContext methodName() {
		    return (methodNameContext)getRuleContext(methodNameContext.class,0);
		}
		public signatureContext signature() {
		    return (signatureContext)getRuleContext(signatureContext.class,0);
		}
		public interfaceTypeNameContext interfaceTypeName() {
		    return (interfaceTypeNameContext)getRuleContext(interfaceTypeNameContext.class,0);
		}
		public methodSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodSpecEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodSpecExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).methodSpecVisit(this);
		    else return null;
		}
	}

	public final methodSpecContext methodSpec() throws RecognitionException {
		methodSpecContext _localctx = new methodSpecContext(_ctx, 40);
		enterRule(_localctx, RULE_methodSpec);
		try {
			setState(395);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(389); ((methodSpecContext)_localctx).name = methodName();
					setState(391); ((methodSpecContext)_localctx).sig = signature();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(393); ((methodSpecContext)_localctx).ifaceName = interfaceTypeName();
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
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public methodNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).methodNameVisit(this);
		    else return null;
		}
	}

	public final methodNameContext methodName() throws RecognitionException {
		methodNameContext _localctx = new methodNameContext(_ctx, 42);
		enterRule(_localctx, RULE_methodName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397); ((methodNameContext)_localctx).name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeNameContext typName;
		public typeNameContext typeName() {
		    return (typeNameContext)getRuleContext(typeNameContext.class,0);
		}
		public interfaceTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).interfaceTypeNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).interfaceTypeNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).interfaceTypeNameVisit(this);
		    else return null;
		}
	}

	public final interfaceTypeNameContext interfaceTypeName() throws RecognitionException {
		interfaceTypeNameContext _localctx = new interfaceTypeNameContext(_ctx, 44);
		enterRule(_localctx, RULE_interfaceTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399); ((interfaceTypeNameContext)_localctx).typName = typeName();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public keyTypeContext keyTyp;
		public elementTypeContext elemType;
		public keyTypeContext keyType() {
		    return (keyTypeContext)getRuleContext(keyTypeContext.class,0);
		}
		public elementTypeContext elementType() {
		    return (elementTypeContext)getRuleContext(elementTypeContext.class,0);
		}
		public mapTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).mapTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).mapTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).mapTypeVisit(this);
		    else return null;
		}
	}

	public final mapTypeContext mapType() throws RecognitionException {
		mapTypeContext _localctx = new mapTypeContext(_ctx, 46);
		enterRule(_localctx, RULE_mapType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401); match(Map);
			setState(403); match(LeftBrack);
			setState(405); ((mapTypeContext)_localctx).keyTyp = keyType();
			setState(407); match(RightBrack);
			setState(409); ((mapTypeContext)_localctx).elemType = elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeContext t;
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public keyTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).keyTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).keyTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).keyTypeVisit(this);
		    else return null;
		}
	}

	public final keyTypeContext keyType() throws RecognitionException {
		keyTypeContext _localctx = new keyTypeContext(_ctx, 48);
		enterRule(_localctx, RULE_keyType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411); ((keyTypeContext)_localctx).t = type();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token send;
		public Token recv;
		public elementTypeContext elemType;
		public elementTypeContext elementType() {
		    return (elementTypeContext)getRuleContext(elementTypeContext.class,0);
		}
		public channelTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).channelTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).channelTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).channelTypeVisit(this);
		    else return null;
		}
	}

	public final channelTypeContext channelType() throws RecognitionException {
		channelTypeContext _localctx = new channelTypeContext(_ctx, 50);
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
							setState(415); ((channelTypeContext)_localctx).send = match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(419); ((channelTypeContext)_localctx).recv = match(LeftArrow);
					setState(421); match(Chan);
					}
					break;
			}
			setState(425); ((channelTypeContext)_localctx).elemType = elementType();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public statementContext statement;
		public List<statementContext> statements = new ArrayList<statementContext>();
		public List<? extends statementContext> statement() {
		    return (List<statementContext>)getRuleContexts(statementContext.class);
		}
		public statementContext statement(int i) {
		    return (statementContext)getRuleContext(statementContext.class,i);
		}
		public blockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).blockEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).blockExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).blockVisit(this);
		    else return null;
		}
	}

	public final blockContext block() throws RecognitionException {
		blockContext _localctx = new blockContext(_ctx, 52);
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
					setState(429); ((blockContext)_localctx).statement = statement();
					((blockContext)_localctx).statements.add(((blockContext)_localctx).statement);
					setState(437);
					_errHandler.sync(this);
					int _alt522 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt522!=2 && _alt522!=-1 ) {
						if ( _alt522==1 ) {
							{
							{
							setState(431); match(Semi);
							setState(433); ((blockContext)_localctx).statement = statement();
							((blockContext)_localctx).statements.add(((blockContext)_localctx).statement);
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

	public static class declarationContext extends ParserRuleContext<Token> {
		public constDeclContext cd;
		public typeDeclContext td;
		public varDeclContext vd;
		public constDeclContext constDecl() {
		    return (constDeclContext)getRuleContext(constDeclContext.class,0);
		}
		public typeDeclContext typeDecl() {
		    return (typeDeclContext)getRuleContext(typeDeclContext.class,0);
		}
		public varDeclContext varDecl() {
		    return (varDeclContext)getRuleContext(varDeclContext.class,0);
		}
		public declarationContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).declarationEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).declarationExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).declarationVisit(this);
		    else return null;
		}
	}

	public final declarationContext declaration() throws RecognitionException {
		declarationContext _localctx = new declarationContext(_ctx, 54);
		enterRule(_localctx, RULE_declaration);
		try {
			setState(454);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(448); ((declarationContext)_localctx).cd = constDecl();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(450); ((declarationContext)_localctx).td = typeDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(452); ((declarationContext)_localctx).vd = varDecl();
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
		public declarationContext decl;
		public functionDeclContext fndecl;
		public methodDeclContext methdecl;
		public declarationContext declaration() {
		    return (declarationContext)getRuleContext(declarationContext.class,0);
		}
		public methodDeclContext methodDecl() {
		    return (methodDeclContext)getRuleContext(methodDeclContext.class,0);
		}
		public functionDeclContext functionDecl() {
		    return (functionDeclContext)getRuleContext(functionDeclContext.class,0);
		}
		public topLevelDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).topLevelDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).topLevelDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).topLevelDeclVisit(this);
		    else return null;
		}
	}

	public final topLevelDeclContext topLevelDecl() throws RecognitionException {
		topLevelDeclContext _localctx = new topLevelDeclContext(_ctx, 56);
		enterRule(_localctx, RULE_topLevelDecl);
		try {
			setState(462);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(456); ((topLevelDeclContext)_localctx).decl = declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(458); ((topLevelDeclContext)_localctx).fndecl = functionDecl();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(460); ((topLevelDeclContext)_localctx).methdecl = methodDecl();
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
		public constSpecContext constSpec;
		public List<constSpecContext> consts = new ArrayList<constSpecContext>();
		public List<? extends constSpecContext> constSpec() {
		    return (List<constSpecContext>)getRuleContexts(constSpecContext.class);
		}
		public constSpecContext constSpec(int i) {
		    return (constSpecContext)getRuleContext(constSpecContext.class,i);
		}
		public constDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).constDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).constDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).constDeclVisit(this);
		    else return null;
		}
	}

	public final constDeclContext constDecl() throws RecognitionException {
		constDeclContext _localctx = new constDeclContext(_ctx, 58);
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
					setState(466); ((constDeclContext)_localctx).constSpec = constSpec();
					((constDeclContext)_localctx).consts.add(((constDeclContext)_localctx).constSpec);
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
							setState(470); ((constDeclContext)_localctx).constSpec = constSpec();
							((constDeclContext)_localctx).consts.add(((constDeclContext)_localctx).constSpec);
							setState(478);
							_errHandler.sync(this);
							int _alt605 = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt605!=2 && _alt605!=-1 ) {
								if ( _alt605==1 ) {
									{
									{
									setState(472); match(Semi);
									setState(474); ((constDeclContext)_localctx).constSpec = constSpec();
									((constDeclContext)_localctx).consts.add(((constDeclContext)_localctx).constSpec);
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

	public static class constSpecContext extends ParserRuleContext<Token> {
		public identifierListContext idList;
		public typeContext explicitType;
		public expressionListContext valueList;
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public identifierListContext identifierList() {
		    return (identifierListContext)getRuleContext(identifierListContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public constSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).constSpecEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).constSpecExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).constSpecVisit(this);
		    else return null;
		}
	}

	public final constSpecContext constSpec() throws RecognitionException {
		constSpecContext _localctx = new constSpecContext(_ctx, 60);
		enterRule(_localctx, RULE_constSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491); ((constSpecContext)_localctx).idList = identifierList();
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
							setState(493); ((constSpecContext)_localctx).explicitType = type();
							}
							break;
					}
					setState(497); match(Equal);
					setState(499); ((constSpecContext)_localctx).valueList = expressionList();
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
		public Token IDENTIFIER;
		public List<Token> ids = new ArrayList<Token>();
		public Token IDENTIFIER(int i) {
		    return getToken(GoParserBase.IDENTIFIER, i);
		}
		public List<? extends Token> IDENTIFIER() { return getTokens(GoParserBase.IDENTIFIER); }
		public identifierListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).identifierListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).identifierListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).identifierListVisit(this);
		    else return null;
		}
	}

	public final identifierListContext identifierList() throws RecognitionException {
		identifierListContext _localctx = new identifierListContext(_ctx, 62);
		enterRule(_localctx, RULE_identifierList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503); ((identifierListContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			((identifierListContext)_localctx).ids.add(((identifierListContext)_localctx).IDENTIFIER);
			setState(511);
			_errHandler.sync(this);
			int _alt657 = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt657!=2 && _alt657!=-1 ) {
				if ( _alt657==1 ) {
					{
					{
					setState(505); match(Comma);
					setState(507); ((identifierListContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					((identifierListContext)_localctx).ids.add(((identifierListContext)_localctx).IDENTIFIER);
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

	public static class expressionListContext extends ParserRuleContext<Token> {
		public expressionContext expression;
		public List<expressionContext> expressions = new ArrayList<expressionContext>();
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public expressionListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).expressionListVisit(this);
		    else return null;
		}
	}

	public final expressionListContext expressionList() throws RecognitionException {
		expressionListContext _localctx = new expressionListContext(_ctx, 64);
		enterRule(_localctx, RULE_expressionList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514); ((expressionListContext)_localctx).expression = expression(0);
			((expressionListContext)_localctx).expressions.add(((expressionListContext)_localctx).expression);
			setState(522);
			_errHandler.sync(this);
			int _alt676 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt676!=2 && _alt676!=-1 ) {
				if ( _alt676==1 ) {
					{
					{
					setState(516); match(Comma);
					setState(518); ((expressionListContext)_localctx).expression = expression(0);
					((expressionListContext)_localctx).expressions.add(((expressionListContext)_localctx).expression);
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

	public static class typeDeclContext extends ParserRuleContext<Token> {
		public typeSpecContext typeSpec;
		public List<typeSpecContext> types = new ArrayList<typeSpecContext>();
		public List<? extends typeSpecContext> typeSpec() {
		    return (List<typeSpecContext>)getRuleContexts(typeSpecContext.class);
		}
		public typeSpecContext typeSpec(int i) {
		    return (typeSpecContext)getRuleContext(typeSpecContext.class,i);
		}
		public typeDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeDeclVisit(this);
		    else return null;
		}
	}

	public final typeDeclContext typeDecl() throws RecognitionException {
		typeDeclContext _localctx = new typeDeclContext(_ctx, 66);
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
					setState(527); ((typeDeclContext)_localctx).typeSpec = typeSpec();
					((typeDeclContext)_localctx).types.add(((typeDeclContext)_localctx).typeSpec);
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
							setState(531); ((typeDeclContext)_localctx).typeSpec = typeSpec();
							((typeDeclContext)_localctx).types.add(((typeDeclContext)_localctx).typeSpec);
							setState(539);
							_errHandler.sync(this);
							int _alt708 = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt708!=2 && _alt708!=-1 ) {
								if ( _alt708==1 ) {
									{
									{
									setState(533); match(Semi);
									setState(535); ((typeDeclContext)_localctx).typeSpec = typeSpec();
									((typeDeclContext)_localctx).types.add(((typeDeclContext)_localctx).typeSpec);
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

	public static class typeSpecContext extends ParserRuleContext<Token> {
		public Token name;
		public typeContext t;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public typeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSpecEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSpecExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeSpecVisit(this);
		    else return null;
		}
	}

	public final typeSpecContext typeSpec() throws RecognitionException {
		typeSpecContext _localctx = new typeSpecContext(_ctx, 68);
		enterRule(_localctx, RULE_typeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552); ((typeSpecContext)_localctx).name = match(IDENTIFIER);
			setState(554); ((typeSpecContext)_localctx).t = type();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public varSpecContext varSpec;
		public List<varSpecContext> vars = new ArrayList<varSpecContext>();
		public varSpecContext varSpec(int i) {
		    return (varSpecContext)getRuleContext(varSpecContext.class,i);
		}
		public List<? extends varSpecContext> varSpec() {
		    return (List<varSpecContext>)getRuleContexts(varSpecContext.class);
		}
		public varDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).varDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).varDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).varDeclVisit(this);
		    else return null;
		}
	}

	public final varDeclContext varDecl() throws RecognitionException {
		varDeclContext _localctx = new varDeclContext(_ctx, 70);
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
					setState(558); ((varDeclContext)_localctx).varSpec = varSpec();
					((varDeclContext)_localctx).vars.add(((varDeclContext)_localctx).varSpec);
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
							setState(562); ((varDeclContext)_localctx).varSpec = varSpec();
							((varDeclContext)_localctx).vars.add(((varDeclContext)_localctx).varSpec);
							setState(570);
							_errHandler.sync(this);
							int _alt763 = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt763!=2 && _alt763!=-1 ) {
								if ( _alt763==1 ) {
									{
									{
									setState(564); match(Semi);
									setState(566); ((varDeclContext)_localctx).varSpec = varSpec();
									((varDeclContext)_localctx).vars.add(((varDeclContext)_localctx).varSpec);
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

	public static class varSpecContext extends ParserRuleContext<Token> {
		public identifierListContext idList;
		public typeContext varType;
		public expressionListContext exprList;
		public List<? extends expressionListContext> expressionList() {
		    return (List<expressionListContext>)getRuleContexts(expressionListContext.class);
		}
		public expressionListContext expressionList(int i) {
		    return (expressionListContext)getRuleContext(expressionListContext.class,i);
		}
		public identifierListContext identifierList() {
		    return (identifierListContext)getRuleContext(identifierListContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public varSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).varSpecEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).varSpecExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).varSpecVisit(this);
		    else return null;
		}
	}

	public final varSpecContext varSpec() throws RecognitionException {
		varSpecContext _localctx = new varSpecContext(_ctx, 72);
		enterRule(_localctx, RULE_varSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583); ((varSpecContext)_localctx).idList = identifierList();
			setState(597);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(585); ((varSpecContext)_localctx).varType = type();
					setState(591);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
						case 1:
							{
							setState(587); match(Equal);
							setState(589); ((varSpecContext)_localctx).exprList = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(593); match(Equal);
					setState(595); ((varSpecContext)_localctx).exprList = expressionList();
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
		public identifierListContext idList;
		public expressionListContext exprList;
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public identifierListContext identifierList() {
		    return (identifierListContext)getRuleContext(identifierListContext.class,0);
		}
		public shortVarDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).shortVarDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).shortVarDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).shortVarDeclVisit(this);
		    else return null;
		}
	}

	public final shortVarDeclContext shortVarDecl() throws RecognitionException {
		shortVarDeclContext _localctx = new shortVarDeclContext(_ctx, 74);
		enterRule(_localctx, RULE_shortVarDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599); ((shortVarDeclContext)_localctx).idList = identifierList();
			setState(601); match(ColonEqual);
			setState(603); ((shortVarDeclContext)_localctx).exprList = expressionList();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token name;
		public signatureContext sig;
		public bodyContext bdy;
		public bodyContext body() {
		    return (bodyContext)getRuleContext(bodyContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public signatureContext signature() {
		    return (signatureContext)getRuleContext(signatureContext.class,0);
		}
		public functionDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).functionDeclVisit(this);
		    else return null;
		}
	}

	public final functionDeclContext functionDecl() throws RecognitionException {
		functionDeclContext _localctx = new functionDeclContext(_ctx, 76);
		enterRule(_localctx, RULE_functionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605); match(Func);
			setState(607); ((functionDeclContext)_localctx).name = match(IDENTIFIER);
			setState(609); ((functionDeclContext)_localctx).sig = signature();
			setState(613);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(611); ((functionDeclContext)_localctx).bdy = body();
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
		public blockContext blk;
		public blockContext block() {
		    return (blockContext)getRuleContext(blockContext.class,0);
		}
		public bodyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).bodyEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).bodyExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).bodyVisit(this);
		    else return null;
		}
	}

	public final bodyContext body() throws RecognitionException {
		bodyContext _localctx = new bodyContext(_ctx, 78);
		enterRule(_localctx, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615); ((bodyContext)_localctx).blk = block();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public receiverContext recv;
		public methodNameContext name;
		public signatureContext sig;
		public bodyContext bdy;
		public bodyContext body() {
		    return (bodyContext)getRuleContext(bodyContext.class,0);
		}
		public receiverContext receiver() {
		    return (receiverContext)getRuleContext(receiverContext.class,0);
		}
		public methodNameContext methodName() {
		    return (methodNameContext)getRuleContext(methodNameContext.class,0);
		}
		public signatureContext signature() {
		    return (signatureContext)getRuleContext(signatureContext.class,0);
		}
		public methodDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).methodDeclVisit(this);
		    else return null;
		}
	}

	public final methodDeclContext methodDecl() throws RecognitionException {
		methodDeclContext _localctx = new methodDeclContext(_ctx, 80);
		enterRule(_localctx, RULE_methodDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(617); match(Func);
			setState(619); ((methodDeclContext)_localctx).recv = receiver();
			setState(621); ((methodDeclContext)_localctx).name = methodName();
			setState(623); ((methodDeclContext)_localctx).sig = signature();
			setState(627);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(625); ((methodDeclContext)_localctx).bdy = body();
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
		public Token name;
		public Token ptr;
		public baseTypeNameContext typ;
		public baseTypeNameContext baseTypeName() {
		    return (baseTypeNameContext)getRuleContext(baseTypeNameContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public receiverContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).receiverEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).receiverExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).receiverVisit(this);
		    else return null;
		}
	}

	public final receiverContext receiver() throws RecognitionException {
		receiverContext _localctx = new receiverContext(_ctx, 82);
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
					setState(631); ((receiverContext)_localctx).name = match(IDENTIFIER);
					}
					break;
			}
			setState(637);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(635); ((receiverContext)_localctx).ptr = match(Star);
					}
					break;
			}
			setState(639); ((receiverContext)_localctx).typ = baseTypeName();
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

	public static class baseTypeNameContext extends ParserRuleContext<Token> {
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public baseTypeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).baseTypeNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).baseTypeNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).baseTypeNameVisit(this);
		    else return null;
		}
	}

	public final baseTypeNameContext baseTypeName() throws RecognitionException {
		baseTypeNameContext _localctx = new baseTypeNameContext(_ctx, 84);
		enterRule(_localctx, RULE_baseTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643); ((baseTypeNameContext)_localctx).name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public literalContext lit;
		public qualifiedIdentifierContext qid;
		public methodExprContext me;
		public expressionContext e;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public qualifiedIdentifierContext qualifiedIdentifier() {
		    return (qualifiedIdentifierContext)getRuleContext(qualifiedIdentifierContext.class,0);
		}
		public methodExprContext methodExpr() {
		    return (methodExprContext)getRuleContext(methodExprContext.class,0);
		}
		public literalContext literal() {
		    return (literalContext)getRuleContext(literalContext.class,0);
		}
		public operandContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).operandEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).operandExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).operandVisit(this);
		    else return null;
		}
	}

	public final operandContext operand() throws RecognitionException {
		operandContext _localctx = new operandContext(_ctx, 86);
		enterRule(_localctx, RULE_operand);
		try {
			setState(657);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(645); ((operandContext)_localctx).lit = literal();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(647); ((operandContext)_localctx).qid = qualifiedIdentifier();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(649); ((operandContext)_localctx).me = methodExpr();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(651); match(LeftParen);
					setState(653); ((operandContext)_localctx).e = expression(0);
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

	public static class literalContext extends ParserRuleContext<Token> {
		public basicLiteralContext bl;
		public compositeLiteralContext cl;
		public functionLiteralContext fl;
		public basicLiteralContext basicLiteral() {
		    return (basicLiteralContext)getRuleContext(basicLiteralContext.class,0);
		}
		public compositeLiteralContext compositeLiteral() {
		    return (compositeLiteralContext)getRuleContext(compositeLiteralContext.class,0);
		}
		public functionLiteralContext functionLiteral() {
		    return (functionLiteralContext)getRuleContext(functionLiteralContext.class,0);
		}
		public literalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).literalVisit(this);
		    else return null;
		}
	}

	public final literalContext literal() throws RecognitionException {
		literalContext _localctx = new literalContext(_ctx, 88);
		enterRule(_localctx, RULE_literal);
		try {
			setState(665);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(659); ((literalContext)_localctx).bl = basicLiteral();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(661); ((literalContext)_localctx).cl = compositeLiteral();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(663); ((literalContext)_localctx).fl = functionLiteral();
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
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public Token IMAGINARY_LITERAL() { return getToken(GoParserBase.IMAGINARY_LITERAL, 0); }
		public Token FLOAT_LITERAL() { return getToken(GoParserBase.FLOAT_LITERAL, 0); }
		public Token INT_LITERAL() { return getToken(GoParserBase.INT_LITERAL, 0); }
		public Token CharLiteral() { return getToken(GoParserBase.CharLiteral, 0); }
		public basicLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).basicLiteralEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).basicLiteralExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).basicLiteralVisit(this);
		    else return null;
		}
	}

	public final basicLiteralContext basicLiteral() throws RecognitionException {
		basicLiteralContext _localctx = new basicLiteralContext(_ctx, 90);
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

	public static class qualifiedIdentifierContext extends ParserRuleContext<Token> {
		public packageNameContext pkg;
		public Token dot;
		public Token id;
		public packageNameContext packageName() {
		    return (packageNameContext)getRuleContext(packageNameContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public qualifiedIdentifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).qualifiedIdentifierEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).qualifiedIdentifierExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).qualifiedIdentifierVisit(this);
		    else return null;
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
					setState(679);
					if (!(isPackageName(_input.LT(1)))) throw new FailedPredicateException(this, "failed predicate: {isPackageName(_input.LT(1))}?");
					setState(681); ((qualifiedIdentifierContext)_localctx).pkg = packageName();
					setState(683); ((qualifiedIdentifierContext)_localctx).dot = match(Dot);
					}
					break;
			}
			setState(687); ((qualifiedIdentifierContext)_localctx).id = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public receiverTypeContext recvType;
		public Token dot;
		public methodNameContext name;
		public receiverTypeContext receiverType() {
		    return (receiverTypeContext)getRuleContext(receiverTypeContext.class,0);
		}
		public methodNameContext methodName() {
		    return (methodNameContext)getRuleContext(methodNameContext.class,0);
		}
		public methodExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).methodExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).methodExprVisit(this);
		    else return null;
		}
	}

	public final methodExprContext methodExpr() throws RecognitionException {
		methodExprContext _localctx = new methodExprContext(_ctx, 94);
		enterRule(_localctx, RULE_methodExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689); ((methodExprContext)_localctx).recvType = receiverType();
			setState(691); ((methodExprContext)_localctx).dot = match(Dot);
			setState(693); ((methodExprContext)_localctx).name = methodName();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeNameContext t;
		public Token ptr;
		public typeNameContext typeName() {
		    return (typeNameContext)getRuleContext(typeNameContext.class,0);
		}
		public receiverTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).receiverTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).receiverTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).receiverTypeVisit(this);
		    else return null;
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
					setState(695); ((receiverTypeContext)_localctx).t = typeName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(697); match(LeftParen);
					setState(699); ((receiverTypeContext)_localctx).ptr = match(Star);
					setState(701); ((receiverTypeContext)_localctx).t = typeName();
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
		public literalTypeContext litTyp;
		public literalValueContext litVal;
		public literalTypeContext literalType() {
		    return (literalTypeContext)getRuleContext(literalTypeContext.class,0);
		}
		public literalValueContext literalValue() {
		    return (literalValueContext)getRuleContext(literalValueContext.class,0);
		}
		public compositeLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).compositeLiteralEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).compositeLiteralExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).compositeLiteralVisit(this);
		    else return null;
		}
	}

	public final compositeLiteralContext compositeLiteral() throws RecognitionException {
		compositeLiteralContext _localctx = new compositeLiteralContext(_ctx, 98);
		enterRule(_localctx, RULE_compositeLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707); ((compositeLiteralContext)_localctx).litTyp = literalType();
			setState(709); ((compositeLiteralContext)_localctx).litVal = literalValue();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeNameContext typeName() {
		    return (typeNameContext)getRuleContext(typeNameContext.class,0);
		}
		public arrayTypeContext arrayType() {
		    return (arrayTypeContext)getRuleContext(arrayTypeContext.class,0);
		}
		public structTypeContext structType() {
		    return (structTypeContext)getRuleContext(structTypeContext.class,0);
		}
		public sliceTypeContext sliceType() {
		    return (sliceTypeContext)getRuleContext(sliceTypeContext.class,0);
		}
		public elementTypeContext elementType() {
		    return (elementTypeContext)getRuleContext(elementTypeContext.class,0);
		}
		public mapTypeContext mapType() {
		    return (mapTypeContext)getRuleContext(mapTypeContext.class,0);
		}
		public literalTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalTypeEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalTypeExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).literalTypeVisit(this);
		    else return null;
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
		public elementListContext elements;
		public elementListContext elementList() {
		    return (elementListContext)getRuleContext(elementListContext.class,0);
		}
		public literalValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalValueEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).literalValueExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).literalValueVisit(this);
		    else return null;
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
					setState(733); ((literalValueContext)_localctx).elements = elementList();
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
		public elementContext element;
		public List<elementContext> elements = new ArrayList<elementContext>();
		public List<? extends elementContext> element() {
		    return (List<elementContext>)getRuleContexts(elementContext.class);
		}
		public elementContext element(int i) {
		    return (elementContext)getRuleContext(elementContext.class,i);
		}
		public elementListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).elementListVisit(this);
		    else return null;
		}
	}

	public final elementListContext elementList() throws RecognitionException {
		elementListContext _localctx = new elementListContext(_ctx, 104);
		enterRule(_localctx, RULE_elementList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743); ((elementListContext)_localctx).element = element();
			((elementListContext)_localctx).elements.add(((elementListContext)_localctx).element);
			setState(751);
			_errHandler.sync(this);
			int _alt1143 = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt1143!=2 && _alt1143!=-1 ) {
				if ( _alt1143==1 ) {
					{
					{
					setState(745); match(Comma);
					setState(747); ((elementListContext)_localctx).element = element();
					((elementListContext)_localctx).elements.add(((elementListContext)_localctx).element);
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

	public static class elementContext extends ParserRuleContext<Token> {
		public keyContext k;
		public valueContext v;
		public valueContext value() {
		    return (valueContext)getRuleContext(valueContext.class,0);
		}
		public keyContext key() {
		    return (keyContext)getRuleContext(keyContext.class,0);
		}
		public elementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).elementVisit(this);
		    else return null;
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
					setState(754); ((elementContext)_localctx).k = key();
					setState(756); match(Colon);
					}
					break;
			}
			setState(760); ((elementContext)_localctx).v = value();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public fieldNameContext field;
		public elementIndexContext index;
		public elementIndexContext elementIndex() {
		    return (elementIndexContext)getRuleContext(elementIndexContext.class,0);
		}
		public fieldNameContext fieldName() {
		    return (fieldNameContext)getRuleContext(fieldNameContext.class,0);
		}
		public keyContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).keyEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).keyExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).keyVisit(this);
		    else return null;
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
					setState(762); ((keyContext)_localctx).field = fieldName();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(764); ((keyContext)_localctx).index = elementIndex();
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
		public Token field;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public fieldNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fieldNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fieldNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).fieldNameVisit(this);
		    else return null;
		}
	}

	public final fieldNameContext fieldName() throws RecognitionException {
		fieldNameContext _localctx = new fieldNameContext(_ctx, 110);
		enterRule(_localctx, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768); ((fieldNameContext)_localctx).field = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext index;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public elementIndexContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementIndexEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).elementIndexExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).elementIndexVisit(this);
		    else return null;
		}
	}

	public final elementIndexContext elementIndex() throws RecognitionException {
		elementIndexContext _localctx = new elementIndexContext(_ctx, 112);
		enterRule(_localctx, RULE_elementIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770); ((elementIndexContext)_localctx).index = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext expr;
		public literalValueContext lit;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public literalValueContext literalValue() {
		    return (literalValueContext)getRuleContext(literalValueContext.class,0);
		}
		public valueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).valueEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).valueExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).valueVisit(this);
		    else return null;
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
					setState(772); ((valueContext)_localctx).expr = expression(0);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(774); ((valueContext)_localctx).lit = literalValue();
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
		public functionTypeContext typ;
		public bodyContext bdy;
		public bodyContext body() {
		    return (bodyContext)getRuleContext(bodyContext.class,0);
		}
		public functionTypeContext functionType() {
		    return (functionTypeContext)getRuleContext(functionTypeContext.class,0);
		}
		public functionLiteralContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionLiteralEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).functionLiteralExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).functionLiteralVisit(this);
		    else return null;
		}
	}

	public final functionLiteralContext functionLiteral() throws RecognitionException {
		functionLiteralContext _localctx = new functionLiteralContext(_ctx, 116);
		enterRule(_localctx, RULE_functionLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(778); ((functionLiteralContext)_localctx).typ = functionType();
			setState(780); ((functionLiteralContext)_localctx).bdy = body();
			}
			_localctx.stop = _input.LT(-1);
		}
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
	 
		public expressionContext() { }
		public void copyFrom(expressionContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionExit(this);
		}
	}
	public static class multExprContext extends expressionContext {
		public expressionContext e;
		public Token op;
		public expressionContext right;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public multExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).multExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).multExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).multExprVisit(this);
		    else return null;
		}
	}
	public static class andExprContext extends expressionContext {
		public expressionContext e;
		public expressionContext right;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public andExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).andExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).andExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).andExprVisit(this);
		    else return null;
		}
	}
	public static class conversionOrCallExprContext extends expressionContext {
		public conversionContext conv;
		public conversionContext conversion() {
		    return (conversionContext)getRuleContext(conversionContext.class,0);
		}
		public conversionOrCallExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conversionOrCallExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conversionOrCallExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).conversionOrCallExprVisit(this);
		    else return null;
		}
	}
	public static class callExprContext extends expressionContext {
		public expressionContext e;
		public Token lp;
		public argumentListContext args;
		public Token rp;
		public argumentListContext argumentList(int i) {
		    return (argumentListContext)getRuleContext(argumentListContext.class,i);
		}
		public List<? extends argumentListContext> argumentList() {
		    return (List<argumentListContext>)getRuleContexts(argumentListContext.class);
		}
		public callExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).callExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).callExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).callExprVisit(this);
		    else return null;
		}
	}
	public static class typeAssertionExprContext extends expressionContext {
		public expressionContext e;
		public Token dot;
		public Token lp;
		public typeContext t;
		public Token rp;
		public typeContext type(int i) {
		    return (typeContext)getRuleContext(typeContext.class,i);
		}
		public List<? extends typeContext> type() {
		    return (List<typeContext>)getRuleContexts(typeContext.class);
		}
		public typeAssertionExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeAssertionExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeAssertionExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeAssertionExprVisit(this);
		    else return null;
		}
	}
	public static class compareExprContext extends expressionContext {
		public expressionContext e;
		public Token op;
		public expressionContext right;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public compareExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).compareExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).compareExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).compareExprVisit(this);
		    else return null;
		}
	}
	public static class orExprContext extends expressionContext {
		public expressionContext e;
		public expressionContext right;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public orExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).orExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).orExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).orExprVisit(this);
		    else return null;
		}
	}
	public static class selectorExprContext extends expressionContext {
		public expressionContext e;
		public Token dot;
		public Token name;
		public Token IDENTIFIER(int i) {
		    return getToken(GoParserBase.IDENTIFIER, i);
		}
		public List<? extends Token> IDENTIFIER() { return getTokens(GoParserBase.IDENTIFIER); }
		public selectorExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).selectorExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).selectorExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).selectorExprVisit(this);
		    else return null;
		}
	}
	public static class sliceExprContext extends expressionContext {
		public expressionContext e;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public sliceExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sliceExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sliceExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).sliceExprVisit(this);
		    else return null;
		}
	}
	public static class unaryExprContext extends expressionContext {
		public Token op;
		public expressionContext e;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public unaryExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).unaryExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).unaryExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).unaryExprVisit(this);
		    else return null;
		}
	}
	public static class operandExprContext extends expressionContext {
		public operandContext operand() {
		    return (operandContext)getRuleContext(operandContext.class,0);
		}
		public operandExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).operandExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).operandExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).operandExprVisit(this);
		    else return null;
		}
	}
	public static class addExprContext extends expressionContext {
		public expressionContext e;
		public Token op;
		public expressionContext right;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public addExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).addExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).addExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).addExprVisit(this);
		    else return null;
		}
	}
	public static class builtinCallExprContext extends expressionContext {
		public builtinCallContext builtinCall() {
		    return (builtinCallContext)getRuleContext(builtinCallContext.class,0);
		}
		public builtinCallExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinCallExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinCallExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).builtinCallExprVisit(this);
		    else return null;
		}
	}
	public static class indexExprContext extends expressionContext {
		public expressionContext e;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public indexExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).indexExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).indexExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).indexExprVisit(this);
		    else return null;
		}
	}

	public final expressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext<Token> _parentctx = _ctx;
		expressionContext _localctx = new expressionContext(_ctx, 118, _p);
		expressionContext _prevctx = _localctx;
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
					_localctx = new unaryExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(796);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
						case 1:
							{
							setState(782); ((unaryExprContext)_localctx).op = match(Plus);
							}
							break;

						case 2:
							{
							setState(784); ((unaryExprContext)_localctx).op = match(Minus);
							}
							break;

						case 3:
							{
							setState(786); ((unaryExprContext)_localctx).op = match(Bang);
							}
							break;

						case 4:
							{
							setState(788); ((unaryExprContext)_localctx).op = match(Caret);
							}
							break;

						case 5:
							{
							setState(790); ((unaryExprContext)_localctx).op = match(Star);
							}
							break;

						case 6:
							{
							setState(792); ((unaryExprContext)_localctx).op = match(Amp);
							}
							break;

						case 7:
							{
							setState(794); ((unaryExprContext)_localctx).op = match(LeftArrow);
							}
							break;
					}
					setState(798); ((unaryExprContext)_localctx).e = expression(6);
					}
					break;

				case 2:
					{
					_localctx = new operandExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(800); operand();
					}
					break;

				case 3:
					{
					_localctx = new conversionOrCallExprContext(_localctx);
					_ctx = _localctx;
					_prevctx = _localctx;
					setState(802); ((conversionOrCallExprContext)_localctx).conv = conversion();
					}
					break;

				case 4:
					{
					_localctx = new builtinCallExprContext(_localctx);
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
							_localctx = new multExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((multExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(808);
							if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
							setState(824);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
								case 1:
									{
									setState(810); ((multExprContext)_localctx).op = match(Star);
									}
									break;

								case 2:
									{
									setState(812); ((multExprContext)_localctx).op = match(Slash);
									}
									break;

								case 3:
									{
									setState(814); ((multExprContext)_localctx).op = match(Percent);
									}
									break;

								case 4:
									{
									setState(816); ((multExprContext)_localctx).op = match(LeftShift);
									}
									break;

								case 5:
									{
									setState(818); ((multExprContext)_localctx).op = match(RightShift);
									}
									break;

								case 6:
									{
									setState(820); ((multExprContext)_localctx).op = match(Amp);
									}
									break;

								case 7:
									{
									setState(822); ((multExprContext)_localctx).op = match(AmpCaret);
									}
									break;
							}
							setState(826); ((multExprContext)_localctx).right = expression(6);
							}
							break;

						case 2:
							{
							_localctx = new addExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((addExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(828);
							if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
							setState(838);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
								case 1:
									{
									setState(830); ((addExprContext)_localctx).op = match(Plus);
									}
									break;

								case 2:
									{
									setState(832); ((addExprContext)_localctx).op = match(Minus);
									}
									break;

								case 3:
									{
									setState(834); ((addExprContext)_localctx).op = match(Pipe);
									}
									break;

								case 4:
									{
									setState(836); ((addExprContext)_localctx).op = match(Caret);
									}
									break;
							}
							setState(840); ((addExprContext)_localctx).right = expression(5);
							}
							break;

						case 3:
							{
							_localctx = new compareExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((compareExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(842);
							if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
							setState(856);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
								case 1:
									{
									setState(844); ((compareExprContext)_localctx).op = match(EqualEqual);
									}
									break;

								case 2:
									{
									setState(846); ((compareExprContext)_localctx).op = match(BangEqual);
									}
									break;

								case 3:
									{
									setState(848); ((compareExprContext)_localctx).op = match(LessThan);
									}
									break;

								case 4:
									{
									setState(850); ((compareExprContext)_localctx).op = match(LessEqual);
									}
									break;

								case 5:
									{
									setState(852); ((compareExprContext)_localctx).op = match(GreaterThan);
									}
									break;

								case 6:
									{
									setState(854); ((compareExprContext)_localctx).op = match(GreaterEqual);
									}
									break;
							}
							setState(858); ((compareExprContext)_localctx).right = expression(4);
							}
							break;

						case 4:
							{
							_localctx = new andExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((andExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(860);
							if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
							setState(862); match(And);
							setState(864); ((andExprContext)_localctx).right = expression(3);
							}
							break;

						case 5:
							{
							_localctx = new orExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((orExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(866);
							if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
							setState(868); match(Or);
							setState(870); ((orExprContext)_localctx).right = expression(2);
							}
							break;

						case 6:
							{
							_localctx = new selectorExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((selectorExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(872);
							if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {11 >= $_p}?");
							setState(874); ((selectorExprContext)_localctx).dot = match(Dot);
							setState(876); ((selectorExprContext)_localctx).name = match(IDENTIFIER);
							}
							break;

						case 7:
							{
							_localctx = new indexExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((indexExprContext)_localctx).e = _prevctx;
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
							_localctx = new sliceExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((sliceExprContext)_localctx).e = _prevctx;
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
							_localctx = new typeAssertionExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((typeAssertionExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(902);
							if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {8 >= $_p}?");
							setState(904); ((typeAssertionExprContext)_localctx).dot = match(Dot);
							setState(906); ((typeAssertionExprContext)_localctx).lp = match(LeftParen);
							setState(908); ((typeAssertionExprContext)_localctx).t = type();
							setState(910); ((typeAssertionExprContext)_localctx).rp = match(RightParen);
							}
							break;

						case 10:
							{
							_localctx = new callExprContext(new expressionContext(_parentctx, _startState, _p));
							_localctx.addChild(_prevctx);
							((callExprContext)_localctx).e = _prevctx;
							pushNewRecursionContext(_localctx, RULE_expression);
							_localctx.start = _prevctx.start;
							setState(912);
							if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {7 >= $_p}?");
							setState(914); ((callExprContext)_localctx).lp = match(LeftParen);
							setState(922);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
								case 1:
									{
									setState(916); ((callExprContext)_localctx).args = argumentList();
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
							setState(924); ((callExprContext)_localctx).rp = match(RightParen);
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

	public static class argumentListContext extends ParserRuleContext<Token> {
		public expressionListContext exprs;
		public Token ellip;
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public argumentListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).argumentListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).argumentListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).argumentListVisit(this);
		    else return null;
		}
	}

	public final argumentListContext argumentList() throws RecognitionException {
		argumentListContext _localctx = new argumentListContext(_ctx, 120);
		enterRule(_localctx, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(931); ((argumentListContext)_localctx).exprs = expressionList();
			setState(935);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(933); ((argumentListContext)_localctx).ellip = match(Ellipsis);
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
		public typeContext t;
		public expressionContext e;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public conversionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conversionEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conversionExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).conversionVisit(this);
		    else return null;
		}
	}

	public final conversionContext conversion() throws RecognitionException {
		conversionContext _localctx = new conversionContext(_ctx, 122);
		enterRule(_localctx, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937); ((conversionContext)_localctx).t = type();
			setState(939); match(LeftParen);
			setState(941); ((conversionContext)_localctx).e = expression(0);
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

	public static class statementContext extends ParserRuleContext<Token> {
		public gotoStmtContext gotoStmt() {
		    return (gotoStmtContext)getRuleContext(gotoStmtContext.class,0);
		}
		public declarationContext declaration() {
		    return (declarationContext)getRuleContext(declarationContext.class,0);
		}
		public forStmtContext forStmt() {
		    return (forStmtContext)getRuleContext(forStmtContext.class,0);
		}
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public returnStmtContext returnStmt() {
		    return (returnStmtContext)getRuleContext(returnStmtContext.class,0);
		}
		public blockContext block() {
		    return (blockContext)getRuleContext(blockContext.class,0);
		}
		public selectStmtContext selectStmt() {
		    return (selectStmtContext)getRuleContext(selectStmtContext.class,0);
		}
		public breakStmtContext breakStmt() {
		    return (breakStmtContext)getRuleContext(breakStmtContext.class,0);
		}
		public fallthroughStmtContext fallthroughStmt() {
		    return (fallthroughStmtContext)getRuleContext(fallthroughStmtContext.class,0);
		}
		public continueStmtContext continueStmt() {
		    return (continueStmtContext)getRuleContext(continueStmtContext.class,0);
		}
		public labeledStmtContext labeledStmt() {
		    return (labeledStmtContext)getRuleContext(labeledStmtContext.class,0);
		}
		public deferStmtContext deferStmt() {
		    return (deferStmtContext)getRuleContext(deferStmtContext.class,0);
		}
		public ifStmtContext ifStmt() {
		    return (ifStmtContext)getRuleContext(ifStmtContext.class,0);
		}
		public goStmtContext goStmt() {
		    return (goStmtContext)getRuleContext(goStmtContext.class,0);
		}
		public switchStmtContext switchStmt() {
		    return (switchStmtContext)getRuleContext(switchStmtContext.class,0);
		}
		public statementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).statementEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).statementExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).statementVisit(this);
		    else return null;
		}
	}

	public final statementContext statement() throws RecognitionException {
		statementContext _localctx = new statementContext(_ctx, 124);
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

	public static class simpleStmtContext extends ParserRuleContext<Token> {
		public shortVarDeclContext shortVarDecl() {
		    return (shortVarDeclContext)getRuleContext(shortVarDeclContext.class,0);
		}
		public assignmentContext assignment() {
		    return (assignmentContext)getRuleContext(assignmentContext.class,0);
		}
		public expressionStmtContext expressionStmt() {
		    return (expressionStmtContext)getRuleContext(expressionStmtContext.class,0);
		}
		public sendStmtContext sendStmt() {
		    return (sendStmtContext)getRuleContext(sendStmtContext.class,0);
		}
		public incDecStmtContext incDecStmt() {
		    return (incDecStmtContext)getRuleContext(incDecStmtContext.class,0);
		}
		public emptyStmtContext emptyStmt() {
		    return (emptyStmtContext)getRuleContext(emptyStmtContext.class,0);
		}
		public simpleStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).simpleStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).simpleStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).simpleStmtVisit(this);
		    else return null;
		}
	}

	public final simpleStmtContext simpleStmt() throws RecognitionException {
		simpleStmtContext _localctx = new simpleStmtContext(_ctx, 126);
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

	public static class emptyStmtContext extends ParserRuleContext<Token> {
		public emptyStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).emptyStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).emptyStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).emptyStmtVisit(this);
		    else return null;
		}
	}

	public final emptyStmtContext emptyStmt() throws RecognitionException {
		emptyStmtContext _localctx = new emptyStmtContext(_ctx, 128);
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
		public labelContext lbl;
		public statementContext stmt;
		public statementContext statement() {
		    return (statementContext)getRuleContext(statementContext.class,0);
		}
		public labelContext label() {
		    return (labelContext)getRuleContext(labelContext.class,0);
		}
		public labeledStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).labeledStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).labeledStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).labeledStmtVisit(this);
		    else return null;
		}
	}

	public final labeledStmtContext labeledStmt() throws RecognitionException {
		labeledStmtContext _localctx = new labeledStmtContext(_ctx, 130);
		enterRule(_localctx, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993); ((labeledStmtContext)_localctx).lbl = label();
			setState(995); match(Colon);
			setState(997); ((labeledStmtContext)_localctx).stmt = statement();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public labelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).labelEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).labelExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).labelVisit(this);
		    else return null;
		}
	}

	public final labelContext label() throws RecognitionException {
		labelContext _localctx = new labelContext(_ctx, 132);
		enterRule(_localctx, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999); ((labelContext)_localctx).name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public expressionStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).expressionStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).expressionStmtVisit(this);
		    else return null;
		}
	}

	public final expressionStmtContext expressionStmt() throws RecognitionException {
		expressionStmtContext _localctx = new expressionStmtContext(_ctx, 134);
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

	public static class sendStmtContext extends ParserRuleContext<Token> {
		public channelContext chan;
		public expressionContext e;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public channelContext channel() {
		    return (channelContext)getRuleContext(channelContext.class,0);
		}
		public sendStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sendStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sendStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).sendStmtVisit(this);
		    else return null;
		}
	}

	public final sendStmtContext sendStmt() throws RecognitionException {
		sendStmtContext _localctx = new sendStmtContext(_ctx, 136);
		enterRule(_localctx, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003); ((sendStmtContext)_localctx).chan = channel();
			setState(1005); match(LeftArrow);
			setState(1007); ((sendStmtContext)_localctx).e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext e;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public channelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).channelEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).channelExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).channelVisit(this);
		    else return null;
		}
	}

	public final channelContext channel() throws RecognitionException {
		channelContext _localctx = new channelContext(_ctx, 138);
		enterRule(_localctx, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009); ((channelContext)_localctx).e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext e;
		public Token op;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public incDecStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).incDecStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).incDecStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).incDecStmtVisit(this);
		    else return null;
		}
	}

	public final incDecStmtContext incDecStmt() throws RecognitionException {
		incDecStmtContext _localctx = new incDecStmtContext(_ctx, 140);
		enterRule(_localctx, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1011); ((incDecStmtContext)_localctx).e = expression(0);
			setState(1013);
			((incDecStmtContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
				((incDecStmtContext)_localctx).op = (Token)_errHandler.recoverInline(this);
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
		public expressionListContext targets;
		public assignOpContext op;
		public expressionListContext values;
		public assignOpContext assignOp() {
		    return (assignOpContext)getRuleContext(assignOpContext.class,0);
		}
		public List<? extends expressionListContext> expressionList() {
		    return (List<expressionListContext>)getRuleContexts(expressionListContext.class);
		}
		public expressionListContext expressionList(int i) {
		    return (expressionListContext)getRuleContext(expressionListContext.class,i);
		}
		public assignmentContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).assignmentEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).assignmentExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).assignmentVisit(this);
		    else return null;
		}
	}

	public final assignmentContext assignment() throws RecognitionException {
		assignmentContext _localctx = new assignmentContext(_ctx, 142);
		enterRule(_localctx, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015); ((assignmentContext)_localctx).targets = expressionList();
			setState(1017); ((assignmentContext)_localctx).op = assignOp();
			setState(1019); ((assignmentContext)_localctx).values = expressionList();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public addAssignOpContext addAssignOp() {
		    return (addAssignOpContext)getRuleContext(addAssignOpContext.class,0);
		}
		public mulAssignOpContext mulAssignOp() {
		    return (mulAssignOpContext)getRuleContext(mulAssignOpContext.class,0);
		}
		public assignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).assignOpEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).assignOpExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).assignOpVisit(this);
		    else return null;
		}
	}

	public final assignOpContext assignOp() throws RecognitionException {
		assignOpContext _localctx = new assignOpContext(_ctx, 144);
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

	public static class addAssignOpContext extends ParserRuleContext<Token> {
		public addAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).addAssignOpEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).addAssignOpExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).addAssignOpVisit(this);
		    else return null;
		}
	}

	public final addAssignOpContext addAssignOp() throws RecognitionException {
		addAssignOpContext _localctx = new addAssignOpContext(_ctx, 146);
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

	public static class mulAssignOpContext extends ParserRuleContext<Token> {
		public mulAssignOpContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).mulAssignOpEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).mulAssignOpExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).mulAssignOpVisit(this);
		    else return null;
		}
	}

	public final mulAssignOpContext mulAssignOp() throws RecognitionException {
		mulAssignOpContext _localctx = new mulAssignOpContext(_ctx, 148);
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

	public static class ifStmtContext extends ParserRuleContext<Token> {
		public blockContext block(int i) {
		    return (blockContext)getRuleContext(blockContext.class,i);
		}
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public List<? extends blockContext> block() {
		    return (List<blockContext>)getRuleContexts(blockContext.class);
		}
		public ifStmtContext ifStmt() {
		    return (ifStmtContext)getRuleContext(ifStmtContext.class,0);
		}
		public ifStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).ifStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).ifStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).ifStmtVisit(this);
		    else return null;
		}
	}

	public final ifStmtContext ifStmt() throws RecognitionException {
		ifStmtContext _localctx = new ifStmtContext(_ctx, 150);
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

	public static class switchStmtContext extends ParserRuleContext<Token> {
		public typeSwitchStmtContext typeSwitchStmt() {
		    return (typeSwitchStmtContext)getRuleContext(typeSwitchStmtContext.class,0);
		}
		public exprSwitchStmtContext exprSwitchStmt() {
		    return (exprSwitchStmtContext)getRuleContext(exprSwitchStmtContext.class,0);
		}
		public switchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).switchStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).switchStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).switchStmtVisit(this);
		    else return null;
		}
	}

	public final switchStmtContext switchStmt() throws RecognitionException {
		switchStmtContext _localctx = new switchStmtContext(_ctx, 152);
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

	public static class exprSwitchStmtContext extends ParserRuleContext<Token> {
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public exprCaseClauseContext exprCaseClause() {
		    return (exprCaseClauseContext)getRuleContext(exprCaseClauseContext.class,0);
		}
		public exprSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprSwitchStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprSwitchStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).exprSwitchStmtVisit(this);
		    else return null;
		}
	}

	public final exprSwitchStmtContext exprSwitchStmt() throws RecognitionException {
		exprSwitchStmtContext _localctx = new exprSwitchStmtContext(_ctx, 154);
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

	public static class exprCaseClauseContext extends ParserRuleContext<Token> {
		public List<? extends statementContext> statement() {
		    return (List<statementContext>)getRuleContexts(statementContext.class);
		}
		public exprSwitchCaseContext exprSwitchCase() {
		    return (exprSwitchCaseContext)getRuleContext(exprSwitchCaseContext.class,0);
		}
		public statementContext statement(int i) {
		    return (statementContext)getRuleContext(statementContext.class,i);
		}
		public exprCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprCaseClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprCaseClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).exprCaseClauseVisit(this);
		    else return null;
		}
	}

	public final exprCaseClauseContext exprCaseClause() throws RecognitionException {
		exprCaseClauseContext _localctx = new exprCaseClauseContext(_ctx, 156);
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

	public static class exprSwitchCaseContext extends ParserRuleContext<Token> {
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public exprSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprSwitchCaseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).exprSwitchCaseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).exprSwitchCaseVisit(this);
		    else return null;
		}
	}

	public final exprSwitchCaseContext exprSwitchCase() throws RecognitionException {
		exprSwitchCaseContext _localctx = new exprSwitchCaseContext(_ctx, 158);
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

	public static class typeSwitchStmtContext extends ParserRuleContext<Token> {
		public typeCaseClauseContext typeCaseClause() {
		    return (typeCaseClauseContext)getRuleContext(typeCaseClauseContext.class,0);
		}
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public typeSwitchGuardContext typeSwitchGuard() {
		    return (typeSwitchGuardContext)getRuleContext(typeSwitchGuardContext.class,0);
		}
		public typeSwitchStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeSwitchStmtVisit(this);
		    else return null;
		}
	}

	public final typeSwitchStmtContext typeSwitchStmt() throws RecognitionException {
		typeSwitchStmtContext _localctx = new typeSwitchStmtContext(_ctx, 160);
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

	public static class typeSwitchGuardContext extends ParserRuleContext<Token> {
		public Token id;
		public Token defeq;
		public expressionContext e;
		public Token dot;
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public typeSwitchGuardContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchGuardEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchGuardExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeSwitchGuardVisit(this);
		    else return null;
		}
	}

	public final typeSwitchGuardContext typeSwitchGuard() throws RecognitionException {
		typeSwitchGuardContext _localctx = new typeSwitchGuardContext(_ctx, 162);
		enterRule(_localctx, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1160);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					{
					setState(1156); ((typeSwitchGuardContext)_localctx).id = match(IDENTIFIER);
					setState(1158); ((typeSwitchGuardContext)_localctx).defeq = match(ColonEqual);
					}
					break;
			}
			setState(1162); ((typeSwitchGuardContext)_localctx).e = expression(0);
			setState(1164); ((typeSwitchGuardContext)_localctx).dot = match(Dot);
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

	public static class typeCaseClauseContext extends ParserRuleContext<Token> {
		public List<? extends statementContext> statement() {
		    return (List<statementContext>)getRuleContexts(statementContext.class);
		}
		public statementContext statement(int i) {
		    return (statementContext)getRuleContext(statementContext.class,i);
		}
		public typeSwitchCaseContext typeSwitchCase() {
		    return (typeSwitchCaseContext)getRuleContext(typeSwitchCaseContext.class,0);
		}
		public typeCaseClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeCaseClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeCaseClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeCaseClauseVisit(this);
		    else return null;
		}
	}

	public final typeCaseClauseContext typeCaseClause() throws RecognitionException {
		typeCaseClauseContext _localctx = new typeCaseClauseContext(_ctx, 164);
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

	public static class typeSwitchCaseContext extends ParserRuleContext<Token> {
		public typeListContext typeList() {
		    return (typeListContext)getRuleContext(typeListContext.class,0);
		}
		public typeSwitchCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchCaseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeSwitchCaseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeSwitchCaseVisit(this);
		    else return null;
		}
	}

	public final typeSwitchCaseContext typeSwitchCase() throws RecognitionException {
		typeSwitchCaseContext _localctx = new typeSwitchCaseContext(_ctx, 166);
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

	public static class typeListContext extends ParserRuleContext<Token> {
		public typeContext type;
		public List<typeContext> types = new ArrayList<typeContext>();
		public typeContext type(int i) {
		    return (typeContext)getRuleContext(typeContext.class,i);
		}
		public List<? extends typeContext> type() {
		    return (List<typeContext>)getRuleContexts(typeContext.class);
		}
		public typeListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeListEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).typeListExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).typeListVisit(this);
		    else return null;
		}
	}

	public final typeListContext typeList() throws RecognitionException {
		typeListContext _localctx = new typeListContext(_ctx, 168);
		enterRule(_localctx, RULE_typeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1201); ((typeListContext)_localctx).type = type();
			((typeListContext)_localctx).types.add(((typeListContext)_localctx).type);
			setState(1209);
			_errHandler.sync(this);
			int _alt2136 = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt2136!=2 && _alt2136!=-1 ) {
				if ( _alt2136==1 ) {
					{
					{
					setState(1203); match(Comma);
					setState(1205); ((typeListContext)_localctx).type = type();
					((typeListContext)_localctx).types.add(((typeListContext)_localctx).type);
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

	public static class forStmtContext extends ParserRuleContext<Token> {
		public conditionContext condition() {
		    return (conditionContext)getRuleContext(conditionContext.class,0);
		}
		public blockContext block() {
		    return (blockContext)getRuleContext(blockContext.class,0);
		}
		public rangeClauseContext rangeClause() {
		    return (rangeClauseContext)getRuleContext(rangeClauseContext.class,0);
		}
		public forClauseContext forClause() {
		    return (forClauseContext)getRuleContext(forClauseContext.class,0);
		}
		public forStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).forStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).forStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).forStmtVisit(this);
		    else return null;
		}
	}

	public final forStmtContext forStmt() throws RecognitionException {
		forStmtContext _localctx = new forStmtContext(_ctx, 170);
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

	public static class conditionContext extends ParserRuleContext<Token> {
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public conditionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conditionEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).conditionExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).conditionVisit(this);
		    else return null;
		}
	}

	public final conditionContext condition() throws RecognitionException {
		conditionContext _localctx = new conditionContext(_ctx, 172);
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

	public static class forClauseContext extends ParserRuleContext<Token> {
		public postStmtContext postStmt() {
		    return (postStmtContext)getRuleContext(postStmtContext.class,0);
		}
		public conditionContext condition() {
		    return (conditionContext)getRuleContext(conditionContext.class,0);
		}
		public initStmtContext initStmt() {
		    return (initStmtContext)getRuleContext(initStmtContext.class,0);
		}
		public forClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).forClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).forClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).forClauseVisit(this);
		    else return null;
		}
	}

	public final forClauseContext forClause() throws RecognitionException {
		forClauseContext _localctx = new forClauseContext(_ctx, 174);
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

	public static class initStmtContext extends ParserRuleContext<Token> {
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public initStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).initStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).initStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).initStmtVisit(this);
		    else return null;
		}
	}

	public final initStmtContext initStmt() throws RecognitionException {
		initStmtContext _localctx = new initStmtContext(_ctx, 176);
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

	public static class postStmtContext extends ParserRuleContext<Token> {
		public simpleStmtContext simpleStmt() {
		    return (simpleStmtContext)getRuleContext(simpleStmtContext.class,0);
		}
		public postStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).postStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).postStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).postStmtVisit(this);
		    else return null;
		}
	}

	public final postStmtContext postStmt() throws RecognitionException {
		postStmtContext _localctx = new postStmtContext(_ctx, 178);
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

	public static class rangeClauseContext extends ParserRuleContext<Token> {
		public expressionContext e1;
		public expressionContext e2;
		public Token eq;
		public Token defeq;
		public expressionContext e;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public rangeClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).rangeClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).rangeClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).rangeClauseVisit(this);
		    else return null;
		}
	}

	public final rangeClauseContext rangeClause() throws RecognitionException {
		rangeClauseContext _localctx = new rangeClauseContext(_ctx, 180);
		enterRule(_localctx, RULE_rangeClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1246); ((rangeClauseContext)_localctx).e1 = expression(0);
			setState(1252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(1248); match(Comma);
					setState(1250); ((rangeClauseContext)_localctx).e2 = expression(0);
					}
					break;
			}
			setState(1258);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(1254); ((rangeClauseContext)_localctx).eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(1256); ((rangeClauseContext)_localctx).defeq = match(ColonEqual);
					}
					break;
			}
			setState(1260); match(Range);
			setState(1262); ((rangeClauseContext)_localctx).e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public goStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).goStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).goStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).goStmtVisit(this);
		    else return null;
		}
	}

	public final goStmtContext goStmt() throws RecognitionException {
		goStmtContext _localctx = new goStmtContext(_ctx, 182);
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

	public static class selectStmtContext extends ParserRuleContext<Token> {
		public commClauseContext commClause() {
		    return (commClauseContext)getRuleContext(commClauseContext.class,0);
		}
		public selectStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).selectStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).selectStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).selectStmtVisit(this);
		    else return null;
		}
	}

	public final selectStmtContext selectStmt() throws RecognitionException {
		selectStmtContext _localctx = new selectStmtContext(_ctx, 184);
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

	public static class commClauseContext extends ParserRuleContext<Token> {
		public List<? extends statementContext> statement() {
		    return (List<statementContext>)getRuleContexts(statementContext.class);
		}
		public statementContext statement(int i) {
		    return (statementContext)getRuleContext(statementContext.class,i);
		}
		public commCaseContext commCase() {
		    return (commCaseContext)getRuleContext(commCaseContext.class,0);
		}
		public commClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).commClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).commClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).commClauseVisit(this);
		    else return null;
		}
	}

	public final commClauseContext commClause() throws RecognitionException {
		commClauseContext _localctx = new commClauseContext(_ctx, 186);
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

	public static class commCaseContext extends ParserRuleContext<Token> {
		public recvStmtContext recvStmt() {
		    return (recvStmtContext)getRuleContext(recvStmtContext.class,0);
		}
		public sendStmtContext sendStmt() {
		    return (sendStmtContext)getRuleContext(sendStmtContext.class,0);
		}
		public commCaseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).commCaseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).commCaseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).commCaseVisit(this);
		    else return null;
		}
	}

	public final commCaseContext commCase() throws RecognitionException {
		commCaseContext _localctx = new commCaseContext(_ctx, 188);
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

	public static class recvStmtContext extends ParserRuleContext<Token> {
		public expressionContext e1;
		public expressionContext e2;
		public Token eq;
		public Token defeq;
		public List<? extends expressionContext> expression() {
		    return (List<expressionContext>)getRuleContexts(expressionContext.class);
		}
		public recvExprContext recvExpr() {
		    return (recvExprContext)getRuleContext(recvExprContext.class,0);
		}
		public expressionContext expression(int i) {
		    return (expressionContext)getRuleContext(expressionContext.class,i);
		}
		public recvStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).recvStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).recvStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).recvStmtVisit(this);
		    else return null;
		}
	}

	public final recvStmtContext recvStmt() throws RecognitionException {
		recvStmtContext _localctx = new recvStmtContext(_ctx, 190);
		enterRule(_localctx, RULE_recvStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1328);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1314); ((recvStmtContext)_localctx).e1 = expression(0);
					setState(1320);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
						case 1:
							{
							setState(1316); match(Comma);
							setState(1318); ((recvStmtContext)_localctx).e2 = expression(0);
							}
							break;
					}
					setState(1326);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
						case 1:
							{
							setState(1322); ((recvStmtContext)_localctx).eq = match(Equal);
							}
							break;

						case 2:
							{
							setState(1324); ((recvStmtContext)_localctx).defeq = match(ColonEqual);
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

	public static class recvExprContext extends ParserRuleContext<Token> {
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public recvExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).recvExprEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).recvExprExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).recvExprVisit(this);
		    else return null;
		}
	}

	public final recvExprContext recvExpr() throws RecognitionException {
		recvExprContext _localctx = new recvExprContext(_ctx, 192);
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

	public static class returnStmtContext extends ParserRuleContext<Token> {
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public returnStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).returnStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).returnStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).returnStmtVisit(this);
		    else return null;
		}
	}

	public final returnStmtContext returnStmt() throws RecognitionException {
		returnStmtContext _localctx = new returnStmtContext(_ctx, 194);
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

	public static class breakStmtContext extends ParserRuleContext<Token> {
		public labelContext label() {
		    return (labelContext)getRuleContext(labelContext.class,0);
		}
		public breakStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).breakStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).breakStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).breakStmtVisit(this);
		    else return null;
		}
	}

	public final breakStmtContext breakStmt() throws RecognitionException {
		breakStmtContext _localctx = new breakStmtContext(_ctx, 196);
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

	public static class continueStmtContext extends ParserRuleContext<Token> {
		public labelContext label() {
		    return (labelContext)getRuleContext(labelContext.class,0);
		}
		public continueStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).continueStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).continueStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).continueStmtVisit(this);
		    else return null;
		}
	}

	public final continueStmtContext continueStmt() throws RecognitionException {
		continueStmtContext _localctx = new continueStmtContext(_ctx, 198);
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

	public static class gotoStmtContext extends ParserRuleContext<Token> {
		public labelContext label() {
		    return (labelContext)getRuleContext(labelContext.class,0);
		}
		public gotoStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).gotoStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).gotoStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).gotoStmtVisit(this);
		    else return null;
		}
	}

	public final gotoStmtContext gotoStmt() throws RecognitionException {
		gotoStmtContext _localctx = new gotoStmtContext(_ctx, 200);
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

	public static class fallthroughStmtContext extends ParserRuleContext<Token> {
		public fallthroughStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fallthroughStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).fallthroughStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).fallthroughStmtVisit(this);
		    else return null;
		}
	}

	public final fallthroughStmtContext fallthroughStmt() throws RecognitionException {
		fallthroughStmtContext _localctx = new fallthroughStmtContext(_ctx, 202);
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

	public static class deferStmtContext extends ParserRuleContext<Token> {
		public expressionContext expression() {
		    return (expressionContext)getRuleContext(expressionContext.class,0);
		}
		public deferStmtContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).deferStmtEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).deferStmtExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).deferStmtVisit(this);
		    else return null;
		}
	}

	public final deferStmtContext deferStmt() throws RecognitionException {
		deferStmtContext _localctx = new deferStmtContext(_ctx, 204);
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

	public static class builtinCallContext extends ParserRuleContext<Token> {
		public Token name;
		public builtinArgsContext args;
		public builtinArgsContext builtinArgs() {
		    return (builtinArgsContext)getRuleContext(builtinArgsContext.class,0);
		}
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public builtinCallContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinCallEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinCallExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).builtinCallVisit(this);
		    else return null;
		}
	}

	public final builtinCallContext builtinCall() throws RecognitionException {
		builtinCallContext _localctx = new builtinCallContext(_ctx, 206);
		enterRule(_localctx, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1362); ((builtinCallContext)_localctx).name = match(IDENTIFIER);
			setState(1364); match(LeftParen);
			setState(1372);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(1366); ((builtinCallContext)_localctx).args = builtinArgs();
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

	public static class builtinArgsContext extends ParserRuleContext<Token> {
		public typeContext typeArg;
		public expressionListContext args;
		public expressionListContext expressionList() {
		    return (expressionListContext)getRuleContext(expressionListContext.class,0);
		}
		public typeContext type() {
		    return (typeContext)getRuleContext(typeContext.class,0);
		}
		public builtinArgsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinArgsEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).builtinArgsExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).builtinArgsVisit(this);
		    else return null;
		}
	}

	public final builtinArgsContext builtinArgs() throws RecognitionException {
		builtinArgsContext _localctx = new builtinArgsContext(_ctx, 208);
		enterRule(_localctx, RULE_builtinArgs);
		try {
			setState(1386);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1376); ((builtinArgsContext)_localctx).typeArg = type();
					setState(1382);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
						case 1:
							{
							setState(1378); match(Comma);
							setState(1380); ((builtinArgsContext)_localctx).args = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1384); ((builtinArgsContext)_localctx).args = expressionList();
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
		public packageClauseContext pkg;
		public importDeclContext importDecl;
		public List<importDeclContext> importDecls = new ArrayList<importDeclContext>();
		public topLevelDeclContext topLevelDecl;
		public List<topLevelDeclContext> decls = new ArrayList<topLevelDeclContext>();
		public importDeclContext importDecl() {
		    return (importDeclContext)getRuleContext(importDeclContext.class,0);
		}
		public topLevelDeclContext topLevelDecl() {
		    return (topLevelDeclContext)getRuleContext(topLevelDeclContext.class,0);
		}
		public packageClauseContext packageClause() {
		    return (packageClauseContext)getRuleContext(packageClauseContext.class,0);
		}
		public sourceFileContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sourceFileEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).sourceFileExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).sourceFileVisit(this);
		    else return null;
		}
	}

	public final sourceFileContext sourceFile() throws RecognitionException {
		sourceFileContext _localctx = new sourceFileContext(_ctx, 210);
		enterRule(_localctx, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1392);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1388); ((sourceFileContext)_localctx).pkg = packageClause();
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
					setState(1394); ((sourceFileContext)_localctx).importDecl = importDecl();
					((sourceFileContext)_localctx).importDecls.add(((sourceFileContext)_localctx).importDecl);
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
					setState(1403); ((sourceFileContext)_localctx).topLevelDecl = topLevelDecl();
					((sourceFileContext)_localctx).decls.add(((sourceFileContext)_localctx).topLevelDecl);
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

	public static class packageClauseContext extends ParserRuleContext<Token> {
		public packageNameContext name;
		public packageNameContext packageName() {
		    return (packageNameContext)getRuleContext(packageNameContext.class,0);
		}
		public packageClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).packageClauseEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).packageClauseExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).packageClauseVisit(this);
		    else return null;
		}
	}

	public final packageClauseContext packageClause() throws RecognitionException {
		packageClauseContext _localctx = new packageClauseContext(_ctx, 212);
		enterRule(_localctx, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1412); match(Package);
			setState(1414); ((packageClauseContext)_localctx).name = packageName();
			addPackageName((((packageClauseContext)_localctx).name!=null?(((packageClauseContext)_localctx).name.start):null));
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token name;
		public Token IDENTIFIER() { return getToken(GoParserBase.IDENTIFIER, 0); }
		public packageNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).packageNameEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).packageNameExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).packageNameVisit(this);
		    else return null;
		}
	}

	public final packageNameContext packageName() throws RecognitionException {
		packageNameContext _localctx = new packageNameContext(_ctx, 214);
		enterRule(_localctx, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1418); ((packageNameContext)_localctx).name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public importSpecContext importSpec;
		public List<importSpecContext> importSpecs = new ArrayList<importSpecContext>();
		public List<? extends importSpecContext> importSpec() {
		    return (List<importSpecContext>)getRuleContexts(importSpecContext.class);
		}
		public importSpecContext importSpec(int i) {
		    return (importSpecContext)getRuleContext(importSpecContext.class,i);
		}
		public importDeclContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importDeclEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importDeclExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).importDeclVisit(this);
		    else return null;
		}
	}

	public final importDeclContext importDecl() throws RecognitionException {
		importDeclContext _localctx = new importDeclContext(_ctx, 216);
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
					setState(1422); ((importDeclContext)_localctx).importSpec = importSpec();
					((importDeclContext)_localctx).importSpecs.add(((importDeclContext)_localctx).importSpec);
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
							setState(1426); ((importDeclContext)_localctx).importSpec = importSpec();
							((importDeclContext)_localctx).importSpecs.add(((importDeclContext)_localctx).importSpec);
							setState(1434);
							_errHandler.sync(this);
							int _alt2551 = getInterpreter().adaptivePredict(_input,120,_ctx);
							while ( _alt2551!=2 && _alt2551!=-1 ) {
								if ( _alt2551==1 ) {
									{
									{
									setState(1428); match(Semi);
									setState(1430); ((importDeclContext)_localctx).importSpec = importSpec();
									((importDeclContext)_localctx).importSpecs.add(((importDeclContext)_localctx).importSpec);
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

	public static class importSpecContext extends ParserRuleContext<Token> {
		public Token dot;
		public importPathContext path;
		public packageNameContext name;
		public packageNameContext packageName() {
		    return (packageNameContext)getRuleContext(packageNameContext.class,0);
		}
		public importPathContext importPath() {
		    return (importPathContext)getRuleContext(importPathContext.class,0);
		}
		public importSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importSpecEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importSpecExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).importSpecVisit(this);
		    else return null;
		}
	}

	public final importSpecContext importSpec() throws RecognitionException {
		importSpecContext _localctx = new importSpecContext(_ctx, 218);
		enterRule(_localctx, RULE_importSpec);
		try {
			setState(1461);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1447); ((importSpecContext)_localctx).dot = match(Dot);
					setState(1449); ((importSpecContext)_localctx).path = importPath();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1451); ((importSpecContext)_localctx).name = packageName();
					setState(1453); ((importSpecContext)_localctx).path = importPath();
					addPackageName((((importSpecContext)_localctx).name!=null?(((importSpecContext)_localctx).name.start):null));
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1457); ((importSpecContext)_localctx).path = importPath();
					addPackageName((((importSpecContext)_localctx).path!=null?(((importSpecContext)_localctx).path.start):null));
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

	public static class importPathContext extends ParserRuleContext<Token> {
		public Token path;
		public Token StringLiteral() { return getToken(GoParserBase.StringLiteral, 0); }
		public importPathContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importPathEnter(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener instanceof GoParserBaseListener ) ((GoParserBaseListener)listener).importPathExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) { 
		    if ( visitor instanceof GoParserBaseVisitor ) return ((GoParserBaseVisitor<T>)visitor).importPathVisit(this);
		    else return null;
		}
	}

	public final importPathContext importPath() throws RecognitionException {
		importPathContext _localctx = new importPathContext(_ctx, 220);
		enterRule(_localctx, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1463); ((importPathContext)_localctx).path = match(StringLiteral);
			}
			_localctx.stop = _input.LT(-1);
		}
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
				case 46 : return qualifiedIdentifier_sempred((qualifiedIdentifierContext)_localctx, predIndex);

				case 59 : return expression_sempred((expressionContext)_localctx, predIndex);
		}
		return true;
	}
	public boolean expression_sempred(expressionContext _localctx, int predIndex) {
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
	public boolean qualifiedIdentifier_sempred(qualifiedIdentifierContext _localctx, int predIndex) {
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