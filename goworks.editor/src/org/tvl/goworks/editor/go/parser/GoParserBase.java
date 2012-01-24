// $ANTLR ANTLRVersion> GoParserBase.java generatedTimestamp>
/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
		public typeNameContext name;;
		public typeLiteralContext lit;;
		public Token lp;;
		public typeContext t;;
		public Token rp;;
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

	public static class typeNameContext extends ParserRuleContext<Token> {
		public qualifiedIdentifierContext qid;;
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

	public static class typeLiteralContext extends ParserRuleContext<Token> {
		public arrayTypeContext arrType;;
		public structTypeContext strType;;
		public pointerTypeContext ptrType;;
		public functionTypeContext fnType;;
		public interfaceTypeContext ifaceType;;
		public sliceTypeContext slcType;;
		public mapTypeContext maptype;;
		public channelTypeContext chanType;;
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

	public static class arrayTypeContext extends ParserRuleContext<Token> {
		public arrayLengthContext len;;
		public elementTypeContext elemType;;
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

	public static class arrayLengthContext extends ParserRuleContext<Token> {
		public expressionContext expr;;
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

	public static class elementTypeContext extends ParserRuleContext<Token> {
		public typeContext typ;;
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

	public static class sliceTypeContext extends ParserRuleContext<Token> {
		public elementTypeContext elemType;;
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

	public static class structTypeContext extends ParserRuleContext<Token> {
		public fieldDeclContext fields;;
		public List<fieldDeclContext> fields_list = new ArrayList<fieldDeclContext>();;
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
			setState(272); match(Struct);
			setState(274); match(LeftBrace);
			setState(282);
			_errHandler.sync(this);
			int _alt187 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt187!=2 && _alt187!=-1 ) {
			    if ( _alt187==1 ) {
			        {
			        {
			        setState(276); _localctx.fields = fieldDecl();
			        _localctx.fields_list.add(_localctx.fields);
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
					setState(285); _localctx.fields = fieldDecl();
					_localctx.fields_list.add(_localctx.fields);
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
		public identifierListContext idList;;
		public typeContext fieldType;;
		public anonymousFieldContext anonField;;
		public tagContext fieldTag;;
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

	public static class anonymousFieldContext extends ParserRuleContext<Token> {
		public Token ptr;;
		public typeNameContext fieldType;;
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
		public Token ptr;;
		public baseTypeContext typ;;
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

	public static class baseTypeContext extends ParserRuleContext<Token> {
		public typeContext typ;;
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

	public static class functionTypeContext extends ParserRuleContext<Token> {
		public signatureContext sig;;
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

	public static class signatureContext extends ParserRuleContext<Token> {
		public parametersContext params;;
		public resultContext res;;
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

	public static class resultContext extends ParserRuleContext<Token> {
		public parametersContext params;;
		public typeContext t;;
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

	public static class parametersContext extends ParserRuleContext<Token> {
		public parameterListContext params;;
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

	public static class parameterListContext extends ParserRuleContext<Token> {
		public parameterDeclContext params;;
		public List<parameterDeclContext> params_list = new ArrayList<parameterDeclContext>();;
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
			setState(345); _localctx.params = parameterDecl();
			_localctx.params_list.add(_localctx.params);
			setState(353);
			_errHandler.sync(this);
			int _alt350 = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt350!=2 && _alt350!=-1 ) {
			    if ( _alt350==1 ) {
			        {
			        {
			        setState(347); match(Comma);
			        setState(349); _localctx.params = parameterDecl();
			        _localctx.params_list.add(_localctx.params);
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
		public identifierListContext idList;;
		public Token ellip;;
		public typeContext t;;
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

	public static class interfaceTypeContext extends ParserRuleContext<Token> {
		public methodSpecContext methods;;
		public List<methodSpecContext> methods_list = new ArrayList<methodSpecContext>();;
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
			setState(366); match(Interface);
			setState(368); match(LeftBrace);
			setState(385);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(370); _localctx.methods = methodSpec();
					_localctx.methods_list.add(_localctx.methods);
					setState(378);
					_errHandler.sync(this);
					int _alt394 = getInterpreter().adaptivePredict(_input,14,_ctx);
					while ( _alt394!=2 && _alt394!=-1 ) {
					    if ( _alt394==1 ) {
					        {
					        {
					        setState(372); match(Semi);
					        setState(374); _localctx.methods = methodSpec();
					        _localctx.methods_list.add(_localctx.methods);
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
		public methodNameContext name;;
		public signatureContext sig;;
		public interfaceTypeNameContext ifaceName;;
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

	public static class interfaceTypeNameContext extends ParserRuleContext<Token> {
		public typeNameContext typName;;
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

	public static class mapTypeContext extends ParserRuleContext<Token> {
		public keyTypeContext keyTyp;;
		public elementTypeContext elemType;;
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

	public static class keyTypeContext extends ParserRuleContext<Token> {
		public typeContext t;;
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

	public static class channelTypeContext extends ParserRuleContext<Token> {
		public elementTypeContext elemType;;
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
							setState(415); match(LeftArrow);
							}
							break;
					}
					}
					break;

				case 2:
					{
					setState(419); match(LeftArrow);
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

	public static class blockContext extends ParserRuleContext<Token> {
		public statementContext statements;;
		public List<statementContext> statements_list = new ArrayList<statementContext>();;
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
			setState(427); match(LeftBrace);
			setState(444);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(429); _localctx.statements = statement();
					_localctx.statements_list.add(_localctx.statements);
					setState(437);
					_errHandler.sync(this);
					int _alt518 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt518!=2 && _alt518!=-1 ) {
					    if ( _alt518==1 ) {
					        {
					        {
					        setState(431); match(Semi);
					        setState(433); _localctx.statements = statement();
					        _localctx.statements_list.add(_localctx.statements);
					        }
					        } 
					    }
						setState(439);
						_errHandler.sync(this);
						_alt518 = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		public constDeclContext cd;;
		public typeDeclContext td;;
		public varDeclContext vd;;
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

	public static class topLevelDeclContext extends ParserRuleContext<Token> {
		public declarationContext decl;;
		public functionDeclContext fndecl;;
		public methodDeclContext methdecl;;
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

	public static class constDeclContext extends ParserRuleContext<Token> {
		public constSpecContext consts;;
		public List<constSpecContext> consts_list = new ArrayList<constSpecContext>();;
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
			setState(464); match(Const);
			setState(489);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(466); _localctx.consts = constSpec();
					_localctx.consts_list.add(_localctx.consts);
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
							setState(470); _localctx.consts = constSpec();
							_localctx.consts_list.add(_localctx.consts);
							setState(478);
							_errHandler.sync(this);
							int _alt601 = getInterpreter().adaptivePredict(_input,25,_ctx);
							while ( _alt601!=2 && _alt601!=-1 ) {
							    if ( _alt601==1 ) {
							        {
							        {
							        setState(472); match(Semi);
							        setState(474); _localctx.consts = constSpec();
							        _localctx.consts_list.add(_localctx.consts);
							        }
							        } 
							    }
								setState(480);
								_errHandler.sync(this);
								_alt601 = getInterpreter().adaptivePredict(_input,25,_ctx);
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
			setState(503); _localctx.ids = match(IDENTIFIER);
			_localctx.ids_list.add(_localctx.ids);
			setState(511);
			_errHandler.sync(this);
			int _alt653 = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt653!=2 && _alt653!=-1 ) {
			    if ( _alt653==1 ) {
			        {
			        {
			        setState(505); match(Comma);
			        setState(507); _localctx.ids = match(IDENTIFIER);
			        _localctx.ids_list.add(_localctx.ids);
			        }
			        } 
			    }
				setState(513);
				_errHandler.sync(this);
				_alt653 = getInterpreter().adaptivePredict(_input,31,_ctx);
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
			setState(514); _localctx.expressions = expression(0);
			_localctx.expressions_list.add(_localctx.expressions);
			setState(522);
			_errHandler.sync(this);
			int _alt672 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt672!=2 && _alt672!=-1 ) {
			    if ( _alt672==1 ) {
			        {
			        {
			        setState(516); match(Comma);
			        setState(518); _localctx.expressions = expression(0);
			        _localctx.expressions_list.add(_localctx.expressions);
			        }
			        } 
			    }
				setState(524);
				_errHandler.sync(this);
				_alt672 = getInterpreter().adaptivePredict(_input,32,_ctx);
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
		public typeSpecContext types;;
		public List<typeSpecContext> types_list = new ArrayList<typeSpecContext>();;
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
			setState(525); match(Type);
			setState(550);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(527); _localctx.types = typeSpec();
					_localctx.types_list.add(_localctx.types);
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
							setState(531); _localctx.types = typeSpec();
							_localctx.types_list.add(_localctx.types);
							setState(539);
							_errHandler.sync(this);
							int _alt704 = getInterpreter().adaptivePredict(_input,33,_ctx);
							while ( _alt704!=2 && _alt704!=-1 ) {
							    if ( _alt704==1 ) {
							        {
							        {
							        setState(533); match(Semi);
							        setState(535); _localctx.types = typeSpec();
							        _localctx.types_list.add(_localctx.types);
							        }
							        } 
							    }
								setState(541);
								_errHandler.sync(this);
								_alt704 = getInterpreter().adaptivePredict(_input,33,_ctx);
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
		public Token name;;
		public typeContext t;;
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

	public static class varDeclContext extends ParserRuleContext<Token> {
		public varSpecContext vars;;
		public List<varSpecContext> vars_list = new ArrayList<varSpecContext>();;
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
			setState(556); match(Var);
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					{
					setState(558); _localctx.vars = varSpec();
					_localctx.vars_list.add(_localctx.vars);
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
							setState(562); _localctx.vars = varSpec();
							_localctx.vars_list.add(_localctx.vars);
							setState(570);
							_errHandler.sync(this);
							int _alt759 = getInterpreter().adaptivePredict(_input,37,_ctx);
							while ( _alt759!=2 && _alt759!=-1 ) {
							    if ( _alt759==1 ) {
							        {
							        {
							        setState(564); match(Semi);
							        setState(566); _localctx.vars = varSpec();
							        _localctx.vars_list.add(_localctx.vars);
							        }
							        } 
							    }
								setState(572);
								_errHandler.sync(this);
								_alt759 = getInterpreter().adaptivePredict(_input,37,_ctx);
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

	public static class bodyContext extends ParserRuleContext<Token> {
		public blockContext blk;;
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

	public static class receiverContext extends ParserRuleContext<Token> {
		public Token name;;
		public Token ptr;;
		public baseTypeNameContext typ;;
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

	public static class baseTypeNameContext extends ParserRuleContext<Token> {
		public Token name;;
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

	public static class operandContext extends ParserRuleContext<Token> {
		public literalContext lit;;
		public qualifiedIdentifierContext qid;;
		public methodExprContext me;;
		public expressionContext e;;
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
			_localctx.stop = _input.LT(-1);
		}
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
		public packageNameContext pkg;;
		public Token dot;;
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

	public static class methodExprContext extends ParserRuleContext<Token> {
		public receiverTypeContext recvType;;
		public Token dot;;
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

	public static class receiverTypeContext extends ParserRuleContext<Token> {
		public typeNameContext t;;
		public Token ptr;;
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

	public static class compositeLiteralContext extends ParserRuleContext<Token> {
		public literalTypeContext litTyp;;
		public literalValueContext litVal;;
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
		public elementListContext elements;;
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

	public static class elementListContext extends ParserRuleContext<Token> {
		public elementContext elements;;
		public List<elementContext> elements_list = new ArrayList<elementContext>();;
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
			setState(743); _localctx.elements = element();
			_localctx.elements_list.add(_localctx.elements);
			setState(751);
			_errHandler.sync(this);
			int _alt1133 = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt1133!=2 && _alt1133!=-1 ) {
			    if ( _alt1133==1 ) {
			        {
			        {
			        setState(745); match(Comma);
			        setState(747); _localctx.elements = element();
			        _localctx.elements_list.add(_localctx.elements);
			        }
			        } 
			    }
				setState(753);
				_errHandler.sync(this);
				_alt1133 = getInterpreter().adaptivePredict(_input,55,_ctx);
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
		public keyContext k;;
		public valueContext v;;
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

	public static class keyContext extends ParserRuleContext<Token> {
		public fieldNameContext field;;
		public elementIndexContext index;;
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

	public static class fieldNameContext extends ParserRuleContext<Token> {
		public Token field;;
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

	public static class elementIndexContext extends ParserRuleContext<Token> {
		public expressionContext index;;
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

	public static class valueContext extends ParserRuleContext<Token> {
		public expressionContext expr;;
		public literalValueContext lit;;
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

	public static class functionLiteralContext extends ParserRuleContext<Token> {
		public functionTypeContext typ;;
		public bodyContext bdy;;
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

	public static class expressionContext extends ParserRuleContext<Token> {
		public int _p;
		public expressionContext e;;
		public conversionContext conv;;
		public expressionContext right;;
		public typeContext t;;
		public argumentListContext args;;
		public Token dot;;
		public Token name;;
		public Token lp;;
		public Token rp;;
		public expressionContext(ParserRuleContext<Token> parent, int state) { super(parent, state); }
		public expressionContext(ParserRuleContext<Token> parent, int state, int _p) {
			super(parent, state);
			this._p = _p;
		}
	 
		public expressionContext() { }
		public void copyFrom(expressionContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
			this.e = ctx.e;
			this.conv = ctx.conv;
			this.right = ctx.right;
			this.t = ctx.t;
			this.args = ctx.args;
			this.dot = ctx.dot;
			this.name = ctx.name;
			this.lp = ctx.lp;
			this.rp = ctx.rp;
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
	public static class multExprContext extends expressionContext {
		public multExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class andExprContext extends expressionContext {
		public andExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class selectorExprContext extends expressionContext {
		public selectorExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class sliceExprContext extends expressionContext {
		public sliceExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class unaryExprContext extends expressionContext {
		public unaryExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class conversionOrCallExprContext extends expressionContext {
		public conversionOrCallExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class addExprContext extends expressionContext {
		public addExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class callExprContext extends expressionContext {
		public callExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class typeAssertionExprContext extends expressionContext {
		public typeAssertionExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class indexExprContext extends expressionContext {
		public indexExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class compareExprContext extends expressionContext {
		public compareExprContext(expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GoParserBaseListener)listener).exitRule(this);
		}
	}
	public static class orExprContext extends expressionContext {
		public orExprContext(expressionContext ctx) { copyFrom(ctx); }
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
		    setState(792);
		    //_errHandler.sync(this);
		    switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
		    	case 1:
		    		{
		    		_localctx = new unaryExprContext(_localctx);
		    		_ctx = _localctx;
		    		_prevctx = _localctx;
		    		setState(782);
		    		_input.LT(1);
		    		_la = _input.LA(1);
		    		if ( !(_la==Plus || _la==Minus || _la==Star || _la==Amp || _la==Caret || _la==LeftArrow || _la==Bang) ) {
		    		_errHandler.recoverInline(this);
		    		}
		    		consume();
		    		setState(784); _localctx.e = expression(6);
		    		}
		    		break;

		    	case 2:
		    		{
		    		setState(786); operand();
		    		}
		    		break;

		    	case 3:
		    		{
		    		_localctx = new conversionOrCallExprContext(_localctx);
		    		_ctx = _localctx;
		    		_prevctx = _localctx;
		    		setState(788); _localctx.conv = conversion();
		    		}
		    		break;

		    	case 4:
		    		{
		    		setState(790); builtinCall();
		    		}
		    		break;
		    }
		    _ctx.stop = _input.LT(-1);
		    setState(880);
		    _errHandler.sync(this);
		    int _alt254 = getInterpreter().adaptivePredict(_input,65,_ctx);
		    while ( _alt254!=2 && _alt254!=-1 ) {
		        if ( _alt254==1 ) {
		    	    if ( _parseListeners!=null ) triggerExitRuleEvent();
		    	    _prevctx = _localctx;
		    	    _prevctx.stop = _input.LT(-1);
		            {
		            setState(878);
		            //_errHandler.sync(this);
		            switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
		            	case 1:
		            		{
		            		_localctx = new multExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(794);
		            		if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {5 >= $_p}?");
		            		setState(796);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Star || _la==Slash || _la==Percent || _la==Amp || _la==LeftShift || _la==RightShift || _la==AmpCaret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(798); _localctx.right = expression(6);
		            		}
		            		break;

		            	case 2:
		            		{
		            		_localctx = new addExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(800);
		            		if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {4 >= $_p}?");
		            		setState(802);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==Plus || _la==Minus || _la==Pipe || _la==Caret) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(804); _localctx.right = expression(5);
		            		}
		            		break;

		            	case 3:
		            		{
		            		_localctx = new compareExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(806);
		            		if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {3 >= $_p}?");
		            		setState(808);
		            		_input.LT(1);
		            		_la = _input.LA(1);
		            		if ( !(_la==EqualEqual || _la==LessThan || _la==GreaterThan || _la==BangEqual || _la==LessEqual || _la==GreaterEqual) ) {
		            		_errHandler.recoverInline(this);
		            		}
		            		consume();
		            		setState(810); _localctx.right = expression(4);
		            		}
		            		break;

		            	case 4:
		            		{
		            		_localctx = new andExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(812);
		            		if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {2 >= $_p}?");
		            		setState(814); match(And);
		            		setState(816); _localctx.right = expression(3);
		            		}
		            		break;

		            	case 5:
		            		{
		            		_localctx = new orExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(818);
		            		if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {1 >= $_p}?");
		            		setState(820); match(Or);
		            		setState(822); _localctx.right = expression(2);
		            		}
		            		break;

		            	case 6:
		            		{
		            		_localctx = new selectorExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(824);
		            		if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {11 >= $_p}?");
		            		setState(826); _localctx.dot = match(Dot);
		            		setState(828); _localctx.name = match(IDENTIFIER);
		            		}
		            		break;

		            	case 7:
		            		{
		            		_localctx = new indexExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(830);
		            		if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {10 >= $_p}?");
		            		setState(832); match(LeftBrack);
		            		setState(834); expression(0);
		            		setState(836); match(RightBrack);
		            		}
		            		break;

		            	case 8:
		            		{
		            		_localctx = new sliceExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(838);
		            		if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {9 >= $_p}?");
		            		setState(840); match(LeftBrack);
		            		setState(844);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
		            			case 1:
		            				{
		            				setState(842); expression(0);
		            				}
		            				break;
		            		}
		            		setState(846); match(Colon);
		            		setState(850);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
		            			case 1:
		            				{
		            				setState(848); expression(0);
		            				}
		            				break;
		            		}
		            		setState(852); match(RightBrack);
		            		}
		            		break;

		            	case 9:
		            		{
		            		_localctx = new typeAssertionExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(854);
		            		if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {8 >= $_p}?");
		            		setState(856); _localctx.dot = match(Dot);
		            		setState(858); _localctx.lp = match(LeftParen);
		            		setState(860); _localctx.t = type();
		            		setState(862); _localctx.rp = match(RightParen);
		            		}
		            		break;

		            	case 10:
		            		{
		            		_localctx = new callExprContext(new expressionContext(_parentctx, _startState, _p));
		            		_localctx.addChild(_prevctx);
		            		_localctx.e = _prevctx;
		            		pushNewRecursionContext(_localctx, RULE_expression);
		            		_localctx.start = _prevctx.start;
		            		setState(864);
		            		if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "failed predicate: {7 >= $_p}?");
		            		setState(866); _localctx.lp = match(LeftParen);
		            		setState(874);
		            		//_errHandler.sync(this);
		            		switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
		            			case 1:
		            				{
		            				setState(868); _localctx.args = argumentList();
		            				setState(872);
		            				//_errHandler.sync(this);
		            				switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
		            					case 1:
		            						{
		            						setState(870); match(Comma);
		            						}
		            						break;
		            				}
		            				}
		            				break;
		            		}
		            		setState(876); _localctx.rp = match(RightParen);
		            		}
		            		break;
		            }
		            } 
		        }
		    	setState(882);
		    	_errHandler.sync(this);
		    	_alt254 = getInterpreter().adaptivePredict(_input,65,_ctx);
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
		public expressionListContext exprs;;
		public Token ellip;;
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
		argumentListContext _localctx = new argumentListContext(_ctx, 120);
		enterRule(_localctx, RULE_argumentList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(883); _localctx.exprs = expressionList();
			setState(887);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(885); _localctx.ellip = match(Ellipsis);
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
		public typeContext t;;
		public expressionContext e;;
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
		conversionContext _localctx = new conversionContext(_ctx, 122);
		enterRule(_localctx, RULE_conversion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(889); _localctx.t = type();
			setState(891); match(LeftParen);
			setState(893); _localctx.e = expression(0);
			setState(895); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		statementContext _localctx = new statementContext(_ctx, 124);
		enterRule(_localctx, RULE_statement);
		try {
			setState(927);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(897); declaration();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(899); labeledStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(901); simpleStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(903); goStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(905); returnStmt();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(907); breakStmt();
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(909); continueStmt();
					}
					break;

				case 8:
					enterOuterAlt(_localctx, 8);
					{
					setState(911); gotoStmt();
					}
					break;

				case 9:
					enterOuterAlt(_localctx, 9);
					{
					setState(913); fallthroughStmt();
					}
					break;

				case 10:
					enterOuterAlt(_localctx, 10);
					{
					setState(915); block();
					}
					break;

				case 11:
					enterOuterAlt(_localctx, 11);
					{
					setState(917); ifStmt();
					}
					break;

				case 12:
					enterOuterAlt(_localctx, 12);
					{
					setState(919); switchStmt();
					}
					break;

				case 13:
					enterOuterAlt(_localctx, 13);
					{
					setState(921); selectStmt();
					}
					break;

				case 14:
					enterOuterAlt(_localctx, 14);
					{
					setState(923); forStmt();
					}
					break;

				case 15:
					enterOuterAlt(_localctx, 15);
					{
					setState(925); deferStmt();
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
		simpleStmtContext _localctx = new simpleStmtContext(_ctx, 126);
		enterRule(_localctx, RULE_simpleStmt);
		try {
			setState(941);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(929); emptyStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(931); expressionStmt();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(933); sendStmt();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(935); incDecStmt();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(937); assignment();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(939); shortVarDecl();
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
		public labelContext lbl;;
		public statementContext stmt;;
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
		labeledStmtContext _localctx = new labeledStmtContext(_ctx, 130);
		enterRule(_localctx, RULE_labeledStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(945); _localctx.lbl = label();
			setState(947); match(Colon);
			setState(949); _localctx.stmt = statement();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token name;;
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
		labelContext _localctx = new labelContext(_ctx, 132);
		enterRule(_localctx, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(951); _localctx.name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		expressionStmtContext _localctx = new expressionStmtContext(_ctx, 134);
		enterRule(_localctx, RULE_expressionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(953); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public channelContext chan;;
		public expressionContext e;;
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
		sendStmtContext _localctx = new sendStmtContext(_ctx, 136);
		enterRule(_localctx, RULE_sendStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(955); _localctx.chan = channel();
			setState(957); match(LeftArrow);
			setState(959); _localctx.e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext e;;
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
		channelContext _localctx = new channelContext(_ctx, 138);
		enterRule(_localctx, RULE_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(961); _localctx.e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext e;;
		public Token op;;
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
		incDecStmtContext _localctx = new incDecStmtContext(_ctx, 140);
		enterRule(_localctx, RULE_incDecStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(963); _localctx.e = expression(0);
			setState(965);
			_localctx.op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Inc || _la==Dec) ) {
				_localctx.op = (Token)_errHandler.recoverInline(this);
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
		public expressionListContext targets;;
		public assignOpContext op;;
		public expressionListContext values;;
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
		assignmentContext _localctx = new assignmentContext(_ctx, 142);
		enterRule(_localctx, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(967); _localctx.targets = expressionList();
			setState(969); _localctx.op = assignOp();
			setState(971); _localctx.values = expressionList();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		assignOpContext _localctx = new assignOpContext(_ctx, 144);
		enterRule(_localctx, RULE_assignOp);
		try {
			setState(979);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(973); addAssignOp();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(975); mulAssignOp();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(977); match(Equal);
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
		addAssignOpContext _localctx = new addAssignOpContext(_ctx, 146);
		enterRule(_localctx, RULE_addAssignOp);
		try {
			setState(989);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(981); match(PlusEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(983); match(MinusEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(985); match(PipeEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(987); match(CaretEqual);
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
		mulAssignOpContext _localctx = new mulAssignOpContext(_ctx, 148);
		enterRule(_localctx, RULE_mulAssignOp);
		try {
			setState(1005);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(991); match(StarEqual);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(993); match(SlashEqual);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(995); match(CaretEqual);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(997); match(LeftShiftEqual);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(999); match(RightShiftEqual);
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(1001); match(AmpEqual);
					}
					break;

				case 7:
					enterOuterAlt(_localctx, 7);
					{
					setState(1003); match(AmpCaretEqual);
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
		ifStmtContext _localctx = new ifStmtContext(_ctx, 150);
		enterRule(_localctx, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1007); match(If);
			setState(1013);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					{
					setState(1009); simpleStmt();
					setState(1011); match(Semi);
					}
					break;
			}
			setState(1015); expression(0);
			setState(1017); block();
			setState(1027);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
				case 1:
					{
					setState(1019); match(Else);
					setState(1025);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
						case 1:
							{
							setState(1021); ifStmt();
							}
							break;

						case 2:
							{
							setState(1023); block();
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
		switchStmtContext _localctx = new switchStmtContext(_ctx, 152);
		enterRule(_localctx, RULE_switchStmt);
		try {
			setState(1033);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1029); exprSwitchStmt();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1031); typeSwitchStmt();
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
		exprSwitchStmtContext _localctx = new exprSwitchStmtContext(_ctx, 154);
		enterRule(_localctx, RULE_exprSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1035); match(Switch);
			setState(1041);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
				case 1:
					{
					setState(1037); simpleStmt();
					setState(1039); match(Semi);
					}
					break;
			}
			setState(1045);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(1043); expression(0);
					}
					break;
			}
			setState(1047); match(LeftBrace);
			setState(1053);
			_errHandler.sync(this);
			int _alt1914 = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt1914!=2 && _alt1914!=-1 ) {
			    if ( _alt1914==1 ) {
			        {
			        {
			        setState(1049); exprCaseClause();
			        }
			        } 
			    }
				setState(1055);
				_errHandler.sync(this);
				_alt1914 = getInterpreter().adaptivePredict(_input,78,_ctx);
			}
			setState(1056); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		exprCaseClauseContext _localctx = new exprCaseClauseContext(_ctx, 156);
		enterRule(_localctx, RULE_exprCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1058); exprSwitchCase();
			setState(1060); match(Colon);
			setState(1077);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(1062); statement();
					setState(1070);
					_errHandler.sync(this);
					int _alt1936 = getInterpreter().adaptivePredict(_input,79,_ctx);
					while ( _alt1936!=2 && _alt1936!=-1 ) {
					    if ( _alt1936==1 ) {
					        {
					        {
					        setState(1064); match(Semi);
					        setState(1066); statement();
					        }
					        } 
					    }
						setState(1072);
						_errHandler.sync(this);
						_alt1936 = getInterpreter().adaptivePredict(_input,79,_ctx);
					}
					setState(1075);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
						case 1:
							{
							setState(1073); match(Semi);
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
		exprSwitchCaseContext _localctx = new exprSwitchCaseContext(_ctx, 158);
		enterRule(_localctx, RULE_exprSwitchCase);
		try {
			setState(1085);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1079); match(Case);
					setState(1081); expressionList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1083); match(Default);
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
		typeSwitchStmtContext _localctx = new typeSwitchStmtContext(_ctx, 160);
		enterRule(_localctx, RULE_typeSwitchStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1087); match(Switch);
			setState(1093);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(1089); simpleStmt();
					setState(1091); match(Semi);
					}
					break;
			}
			setState(1095); typeSwitchGuard();
			setState(1097); match(LeftBrace);
			setState(1103);
			_errHandler.sync(this);
			int _alt1977 = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt1977!=2 && _alt1977!=-1 ) {
			    if ( _alt1977==1 ) {
			        {
			        {
			        setState(1099); typeCaseClause();
			        }
			        } 
			    }
				setState(1105);
				_errHandler.sync(this);
				_alt1977 = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(1106); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token id;;
		public expressionContext e;;
		public Token dot;;
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
		typeSwitchGuardContext _localctx = new typeSwitchGuardContext(_ctx, 162);
		enterRule(_localctx, RULE_typeSwitchGuard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1112);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(1108); _localctx.id = match(IDENTIFIER);
					setState(1110); match(ColonEqual);
					}
					break;
			}
			setState(1114); _localctx.e = expression(0);
			setState(1116); _localctx.dot = match(Dot);
			setState(1118); match(LeftParen);
			setState(1120); match(Type);
			setState(1122); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		typeCaseClauseContext _localctx = new typeCaseClauseContext(_ctx, 164);
		enterRule(_localctx, RULE_typeCaseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1124); typeSwitchCase();
			setState(1126); match(Colon);
			setState(1143);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(1128); statement();
					setState(1136);
					_errHandler.sync(this);
					int _alt2030 = getInterpreter().adaptivePredict(_input,86,_ctx);
					while ( _alt2030!=2 && _alt2030!=-1 ) {
					    if ( _alt2030==1 ) {
					        {
					        {
					        setState(1130); match(Semi);
					        setState(1132); statement();
					        }
					        } 
					    }
						setState(1138);
						_errHandler.sync(this);
						_alt2030 = getInterpreter().adaptivePredict(_input,86,_ctx);
					}
					setState(1141);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(1139); match(Semi);
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
		typeSwitchCaseContext _localctx = new typeSwitchCaseContext(_ctx, 166);
		enterRule(_localctx, RULE_typeSwitchCase);
		try {
			setState(1151);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1145); match(Case);
					setState(1147); typeList();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1149); match(Default);
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
		public typeContext types;;
		public List<typeContext> types_list = new ArrayList<typeContext>();;
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
		typeListContext _localctx = new typeListContext(_ctx, 168);
		enterRule(_localctx, RULE_typeList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1153); _localctx.types = type();
			_localctx.types_list.add(_localctx.types);
			setState(1161);
			_errHandler.sync(this);
			int _alt2068 = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt2068!=2 && _alt2068!=-1 ) {
			    if ( _alt2068==1 ) {
			        {
			        {
			        setState(1155); match(Comma);
			        setState(1157); _localctx.types = type();
			        _localctx.types_list.add(_localctx.types);
			        }
			        } 
			    }
				setState(1163);
				_errHandler.sync(this);
				_alt2068 = getInterpreter().adaptivePredict(_input,90,_ctx);
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
		forStmtContext _localctx = new forStmtContext(_ctx, 170);
		enterRule(_localctx, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1164); match(For);
			setState(1172);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					{
					setState(1166); condition();
					}
					break;

				case 2:
					{
					setState(1168); forClause();
					}
					break;

				case 3:
					{
					setState(1170); rangeClause();
					}
					break;
			}
			setState(1174); block();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		conditionContext _localctx = new conditionContext(_ctx, 172);
		enterRule(_localctx, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1176); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		forClauseContext _localctx = new forClauseContext(_ctx, 174);
		enterRule(_localctx, RULE_forClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1180);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
				case 1:
					{
					setState(1178); initStmt();
					}
					break;
			}
			setState(1182); match(Semi);
			setState(1186);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
				case 1:
					{
					setState(1184); condition();
					}
					break;
			}
			setState(1188); match(Semi);
			setState(1192);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(1190); postStmt();
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
		initStmtContext _localctx = new initStmtContext(_ctx, 176);
		enterRule(_localctx, RULE_initStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1194); simpleStmt();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		postStmtContext _localctx = new postStmtContext(_ctx, 178);
		enterRule(_localctx, RULE_postStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1196); simpleStmt();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public expressionContext e1;;
		public expressionContext e2;;
		public Token eq;;
		public Token defeq;;
		public expressionContext e;;
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
		rangeClauseContext _localctx = new rangeClauseContext(_ctx, 180);
		enterRule(_localctx, RULE_rangeClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1198); _localctx.e1 = expression(0);
			setState(1204);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1200); match(Comma);
					setState(1202); _localctx.e2 = expression(0);
					}
					break;
			}
			setState(1210);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(1206); _localctx.eq = match(Equal);
					}
					break;

				case 2:
					{
					setState(1208); _localctx.defeq = match(ColonEqual);
					}
					break;
			}
			setState(1212); match(Range);
			setState(1214); _localctx.e = expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		goStmtContext _localctx = new goStmtContext(_ctx, 182);
		enterRule(_localctx, RULE_goStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1216); match(Go);
			setState(1218); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		selectStmtContext _localctx = new selectStmtContext(_ctx, 184);
		enterRule(_localctx, RULE_selectStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1220); match(Select);
			setState(1222); match(LeftBrace);
			setState(1228);
			_errHandler.sync(this);
			int _alt2194 = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt2194!=2 && _alt2194!=-1 ) {
			    if ( _alt2194==1 ) {
			        {
			        {
			        setState(1224); commClause();
			        }
			        } 
			    }
				setState(1230);
				_errHandler.sync(this);
				_alt2194 = getInterpreter().adaptivePredict(_input,97,_ctx);
			}
			setState(1231); match(RightBrace);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		commClauseContext _localctx = new commClauseContext(_ctx, 186);
		enterRule(_localctx, RULE_commClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1233); commCase();
			setState(1235); match(Colon);
			setState(1252);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(1237); statement();
					setState(1245);
					_errHandler.sync(this);
					int _alt2216 = getInterpreter().adaptivePredict(_input,98,_ctx);
					while ( _alt2216!=2 && _alt2216!=-1 ) {
					    if ( _alt2216==1 ) {
					        {
					        {
					        setState(1239); match(Semi);
					        setState(1241); statement();
					        }
					        } 
					    }
						setState(1247);
						_errHandler.sync(this);
						_alt2216 = getInterpreter().adaptivePredict(_input,98,_ctx);
					}
					setState(1250);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
						case 1:
							{
							setState(1248); match(Semi);
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
		commCaseContext _localctx = new commCaseContext(_ctx, 188);
		enterRule(_localctx, RULE_commCase);
		try {
			setState(1264);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1254); match(Case);
					setState(1260);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
						case 1:
							{
							setState(1256); sendStmt();
							}
							break;

						case 2:
							{
							setState(1258); recvStmt();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1262); match(Default);
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
		recvStmtContext _localctx = new recvStmtContext(_ctx, 190);
		enterRule(_localctx, RULE_recvStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1276);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(1266); expression(0);
					setState(1272);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
						case 1:
							{
							setState(1268); match(Comma);
							setState(1270); expression(0);
							}
							break;
					}
					setState(1274);
					_input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==Equal || _la==ColonEqual) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					break;
			}
			setState(1278); recvExpr();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		recvExprContext _localctx = new recvExprContext(_ctx, 192);
		enterRule(_localctx, RULE_recvExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1280); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		returnStmtContext _localctx = new returnStmtContext(_ctx, 194);
		enterRule(_localctx, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1282); match(Return);
			setState(1286);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(1284); expressionList();
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
		breakStmtContext _localctx = new breakStmtContext(_ctx, 196);
		enterRule(_localctx, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1288); match(Break);
			setState(1292);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(1290); label();
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
		continueStmtContext _localctx = new continueStmtContext(_ctx, 198);
		enterRule(_localctx, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1294); match(Continue);
			setState(1298);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
				case 1:
					{
					setState(1296); label();
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
		gotoStmtContext _localctx = new gotoStmtContext(_ctx, 200);
		enterRule(_localctx, RULE_gotoStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1300); match(Goto);
			setState(1302); label();
			}
			_localctx.stop = _input.LT(-1);
		}
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
		fallthroughStmtContext _localctx = new fallthroughStmtContext(_ctx, 202);
		enterRule(_localctx, RULE_fallthroughStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1304); match(Fallthrough);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		deferStmtContext _localctx = new deferStmtContext(_ctx, 204);
		enterRule(_localctx, RULE_deferStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1306); match(Defer);
			setState(1308); expression(0);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public Token name;;
		public builtinArgsContext args;;
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
		builtinCallContext _localctx = new builtinCallContext(_ctx, 206);
		enterRule(_localctx, RULE_builtinCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1310); _localctx.name = match(IDENTIFIER);
			setState(1312); match(LeftParen);
			setState(1320);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
				case 1:
					{
					setState(1314); _localctx.args = builtinArgs();
					setState(1318);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
						case 1:
							{
							setState(1316); match(Comma);
							}
							break;
					}
					}
					break;
			}
			setState(1322); match(RightParen);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public typeContext typeArg;;
		public expressionListContext args;;
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
		builtinArgsContext _localctx = new builtinArgsContext(_ctx, 208);
		enterRule(_localctx, RULE_builtinArgs);
		try {
			setState(1334);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1324); _localctx.typeArg = type();
					setState(1330);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
						case 1:
							{
							setState(1326); match(Comma);
							setState(1328); _localctx.args = expressionList();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1332); _localctx.args = expressionList();
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
		public packageClauseContext pkg;;
		public importDeclContext importDecls;;
		public List<importDeclContext> importDecls_list = new ArrayList<importDeclContext>();;
		public topLevelDeclContext decls;;
		public List<topLevelDeclContext> decls_list = new ArrayList<topLevelDeclContext>();;
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
		sourceFileContext _localctx = new sourceFileContext(_ctx, 210);
		enterRule(_localctx, RULE_sourceFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1336); _localctx.pkg = packageClause();
			setState(1338); match(Semi);
			setState(1346);
			_errHandler.sync(this);
			int _alt2408 = getInterpreter().adaptivePredict(_input,112,_ctx);
			while ( _alt2408!=2 && _alt2408!=-1 ) {
			    if ( _alt2408==1 ) {
			        {
			        {
			        setState(1340); _localctx.importDecls = importDecl();
			        _localctx.importDecls_list.add(_localctx.importDecls);
			        setState(1342); match(Semi);
			        }
			        } 
			    }
				setState(1348);
				_errHandler.sync(this);
				_alt2408 = getInterpreter().adaptivePredict(_input,112,_ctx);
			}
			setState(1355);
			_errHandler.sync(this);
			int _alt2417 = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt2417!=2 && _alt2417!=-1 ) {
			    if ( _alt2417==1 ) {
			        {
			        {
			        setState(1349); _localctx.decls = topLevelDecl();
			        _localctx.decls_list.add(_localctx.decls);
			        setState(1351); match(Semi);
			        }
			        } 
			    }
				setState(1357);
				_errHandler.sync(this);
				_alt2417 = getInterpreter().adaptivePredict(_input,113,_ctx);
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
		public packageNameContext name;;
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
		packageClauseContext _localctx = new packageClauseContext(_ctx, 212);
		enterRule(_localctx, RULE_packageClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1358); match(Package);
			setState(1360); _localctx.name = packageName();
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

	public static class packageNameContext extends ParserRuleContext<Token> {
		public Token name;;
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
		packageNameContext _localctx = new packageNameContext(_ctx, 214);
		enterRule(_localctx, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1364); _localctx.name = match(IDENTIFIER);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		public importSpecContext importSpecs;;
		public List<importSpecContext> importSpecs_list = new ArrayList<importSpecContext>();;
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
		importDeclContext _localctx = new importDeclContext(_ctx, 216);
		enterRule(_localctx, RULE_importDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1366); match(Import);
			setState(1391);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1368); _localctx.importSpecs = importSpec();
					_localctx.importSpecs_list.add(_localctx.importSpecs);
					}
					break;

				case 2:
					{
					setState(1370); match(LeftParen);
					setState(1387);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
						case 1:
							{
							setState(1372); _localctx.importSpecs = importSpec();
							_localctx.importSpecs_list.add(_localctx.importSpecs);
							setState(1380);
							_errHandler.sync(this);
							int _alt2472 = getInterpreter().adaptivePredict(_input,114,_ctx);
							while ( _alt2472!=2 && _alt2472!=-1 ) {
							    if ( _alt2472==1 ) {
							        {
							        {
							        setState(1374); match(Semi);
							        setState(1376); _localctx.importSpecs = importSpec();
							        _localctx.importSpecs_list.add(_localctx.importSpecs);
							        }
							        } 
							    }
								setState(1382);
								_errHandler.sync(this);
								_alt2472 = getInterpreter().adaptivePredict(_input,114,_ctx);
							}
							setState(1385);
							//_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
								case 1:
									{
									setState(1383); match(Semi);
									}
									break;
							}
							}
							break;
					}
					setState(1389); match(RightParen);
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
		public Token dot;;
		public importPathContext path;;
		public packageNameContext name;;
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
		importSpecContext _localctx = new importSpecContext(_ctx, 218);
		enterRule(_localctx, RULE_importSpec);
		try {
			setState(1407);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1393); _localctx.dot = match(Dot);
					setState(1395); _localctx.path = importPath();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1397); _localctx.name = packageName();
					setState(1399); _localctx.path = importPath();
					addPackageName((_localctx.name!=null?(_localctx.name.start):null));
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1403); _localctx.path = importPath();
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

	public static class importPathContext extends ParserRuleContext<Token> {
		public Token path;;
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
		importPathContext _localctx = new importPathContext(_ctx, 220);
		enterRule(_localctx, RULE_importPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1409); _localctx.path = match(StringLiteral);
			}
			_localctx.stop = _input.LT(-1);
		}
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
		"\1U\u0584\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
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
		":\1;\0\1;\0\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1"+
		";\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1;\0\1"+
		";\1;\1;\0\1;\0\1;\0\3;\b;\1;\0\1;\0\3;\b;\1;\0\1;\0\1;\0\1;\0\1;\0\1;"+
		"\1;\1;\0\1;\0\1;\0\1;\0\3;\b;\3;\b;\1;\0\5;\b;\n;\f;\u0372\t;\1<\0\1<"+
		"\0\3<\b<\1=\0\1=\0\1=\0\1=\1=\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0"+
		"\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\1?\0\1?\0\1?\0\1?"+
		"\0\3?\b?\1@\1@\1A\0\1A\0\1A\1A\1B\1B\1C\1C\1D\0\1D\0\1D\1D\1E\1E\1F\0"+
		"\1F\1F\1G\0\1G\0\1G\1G\1H\0\1H\0\1H\0\3H\bH\1I\0\1I\0\1I\0\1I\0\3I\bI"+
		"\1J\0\1J\0\1J\0\1J\0\1J\0\1J\0\1J\0\3J\bJ\1K\0\1K\0\1K\1K\3K\bK\1K\0\1"+
		"K\0\1K\0\1K\0\1K\0\3K\bK\3K\bK\1L\0\1L\0\3L\bL\1M\0\1M\0\1M\1M\3M\bM\1"+
		"M\0\3M\bM\1M\0\1M\0\5M\bM\nM\fM\u041f\tM\1M\1M\1N\0\1N\0\1N\0\1N\0\1N"+
		"\0\5N\bN\nN\fN\u0430\tN\1N\0\3N\bN\3N\bN\1O\0\1O\0\1O\0\3O\bO\1P\0\1P"+
		"\0\1P\1P\3P\bP\1P\0\1P\0\1P\0\5P\bP\nP\fP\u0451\tP\1P\1P\1Q\0\1Q\0\3Q"+
		"\bQ\1Q\0\1Q\0\1Q\0\1Q\0\1Q\1Q\1R\0\1R\0\1R\0\1R\0\1R\0\5R\bR\nR\fR\u0472"+
		"\tR\1R\0\3R\bR\3R\bR\1S\0\1S\0\1S\0\3S\bS\1T\0\1T\0\1T\0\5T\bT\nT\fT\u048b"+
		"\tT\1U\0\1U\0\1U\0\1U\0\3U\bU\1U\1U\1V\1V\1W\0\3W\bW\1W\0\1W\0\3W\bW\1"+
		"W\0\1W\0\3W\bW\1X\1X\1Y\1Y\1Z\0\1Z\0\1Z\0\3Z\bZ\1Z\0\1Z\0\3Z\bZ\1Z\0\1"+
		"Z\1Z\1[\0\1[\1[\1\\\0\1\\\0\1\\\0\5\\\b\\\n\\\f\\\u04ce\t\\\1\\\1\\\1"+
		"]\0\1]\0\1]\0\1]\0\1]\0\5]\b]\n]\f]\u04df\t]\1]\0\3]\b]\3]\b]\1^\0\1^"+
		"\0\1^\0\3^\b^\1^\0\3^\b^\1_\0\1_\0\1_\0\3_\b_\1_\1_\3_\b_\1_\1_\1`\1`"+
		"\1a\0\1a\0\3a\ba\1b\0\1b\0\3b\bb\1c\0\1c\0\3c\bc\1d\0\1d\1d\1e\1e\1f\0"+
		"\1f\1f\1g\0\1g\0\1g\0\1g\0\3g\bg\3g\bg\1g\1g\1h\0\1h\0\1h\0\3h\bh\1h\0"+
		"\3h\bh\1i\0\1i\0\1i\0\1i\1i\5i\bi\ni\fi\u0544\ti\1i\0\1i\1i\5i\bi\ni\f"+
		"i\u054d\ti\1j\0\1j\0\1j\1j\1k\1k\1l\0\1l\0\1l\0\1l\0\1l\0\1l\0\5l\bl\n"+
		"l\fl\u0566\tl\1l\0\3l\bl\3l\bl\1l\0\3l\bl\1m\0\1m\0\1m\0\1m\0\1m\1m\1"+
		"m\0\1m\1m\3m\bm\1n\1n\1no\0\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084"+
		"\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c"+
		"\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4"+
		"\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc"+
		"\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\0\6\5\34\36!!##\64\64"+
		";;\2\36!$&\2\34\35\"#\2\679<>\1\65\66\2::??\u0540\0\u00e8\1\0\0\0\1\u00e6"+
		"\1\0\0\0\1\u0109\1\0\0\0\1\u0126\1\0\0\0\1\u013c\1\0\0\0\1\u014c\1\0\0"+
		"\0\1\u016d\1\0\0\0\1\u019c\1\0\0\0\1\u01f0\1\0\0\0\1\u022b\1\0\0\0\1\u024f"+
		"\1\0\0\0\1\u035e\1\0\0\0\1\u037b\1\0\0\0\1\u0489\1\0\0\0\1\u0488\1\0\0"+
		"\0\1\u0532\1\0\0\0\2\u00ea\1\0\0\0\3\u00e9\1\0\0\0\3\u0134\1\0\0\0\3\u0190"+
		"\1\0\0\0\3\u02c2\1\0\0\0\3\u02bf\1\0\0\0\3\u02da\1\0\0\0\4\u00fc\1\0\0"+
		"\0\5\u00e9\1\0\0\0\6\u00fe\1\0\0\0\7\u00fd\1\0\0\0\7\u02da\1\0\0\0\b\u0106"+
		"\1\0\0\0\t\u0102\1\0\0\0\n\u0108\1\0\0\0\13\u0105\1\0\0\0\13\u010f\1\0"+
		"\0\0\13\u019a\1\0\0\0\13\u01aa\1\0\0\0\13\u02da\1\0\0\0\f\u010a\1\0\0"+
		"\0\r\u00fd\1\0\0\0\r\u02da\1\0\0\0\16\u0110\1\0\0\0\17\u00fd\1\0\0\0\17"+
		"\u02da\1\0\0\0\20\u0129\1\0\0\0\21\u0116\1\0\0\0\21\u0120\1\0\0\0\22\u0131"+
		"\1\0\0\0\23\u012a\1\0\0\0\24\u0135\1\0\0\0\25\u012e\1\0\0\0\26\u0137\1"+
		"\0\0\0\27\u00fd\1\0\0\0\30\u013b\1\0\0\0\31\u013a\1\0\0\0\32\u013d\1\0"+
		"\0\0\33\u00fd\1\0\0\0\33\u030c\1\0\0\0\34\u0141\1\0\0\0\35\u0140\1\0\0"+
		"\0\35\u0188\1\0\0\0\35\u0265\1\0\0\0\35\u0273\1\0\0\0\36\u014b\1\0\0\0"+
		"\37\u0146\1\0\0\0 \u014d\1\0\0\0!\u0145\1\0\0\0!\u014c\1\0\0\0\"\u0159"+
		"\1\0\0\0#\u0153\1\0\0\0$\u0166\1\0\0\0%\u0161\1\0\0\0%\u0160\1\0\0\0&"+
		"\u016e\1\0\0\0\'\u00fd\1\0\0\0(\u018b\1\0\0\0)\u017a\1\0\0\0)\u0179\1"+
		"\0\0\0*\u018d\1\0\0\0+\u0187\1\0\0\0+\u026f\1\0\0\0+\u02b6\1\0\0\0,\u018f"+
		"\1\0\0\0-\u018c\1\0\0\0.\u0191\1\0\0\0/\u00fd\1\0\0\0/\u02da\1\0\0\0\60"+
		"\u019b\1\0\0\0\61\u0197\1\0\0\0\62\u01a7\1\0\0\0\63\u00fd\1\0\0\0\64\u01ab"+
		"\1\0\0\0\65\u0268\1\0\0\0\65\u03a0\1\0\0\0\65\u0403\1\0\0\0\65\u0402\1"+
		"\0\0\0\65\u0497\1\0\0\0\66\u01c6\1\0\0\0\67\u01cf\1\0\0\0\67\u03a0\1\0"+
		"\0\08\u01ce\1\0\0\09\u0547\1\0\0\0:\u01d0\1\0\0\0;\u01c7\1\0\0\0<\u01eb"+
		"\1\0\0\0=\u01ea\1\0\0\0=\u01de\1\0\0\0=\u01dd\1\0\0\0>\u01f7\1\0\0\0?"+
		"\u0125\1\0\0\0?\u0167\1\0\0\0?\u01f5\1\0\0\0?\u0255\1\0\0\0?\u0259\1\0"+
		"\0\0@\u0202\1\0\0\0A\u01f6\1\0\0\0A\u0250\1\0\0\0A\u0256\1\0\0\0A\u025c"+
		"\1\0\0\0A\u0377\1\0\0\0A\u03c9\1\0\0\0A\u03cc\1\0\0\0A\u043e\1\0\0\0A"+
		"\u0507\1\0\0\0A\u0533\1\0\0\0A\u0537\1\0\0\0B\u020d\1\0\0\0C\u01c7\1\0"+
		"\0\0D\u0228\1\0\0\0E\u0227\1\0\0\0E\u021b\1\0\0\0E\u021a\1\0\0\0F\u022c"+
		"\1\0\0\0G\u01c7\1\0\0\0H\u0247\1\0\0\0I\u0246\1\0\0\0I\u023a\1\0\0\0I"+
		"\u0239\1\0\0\0J\u0257\1\0\0\0K\u03ae\1\0\0\0L\u025d\1\0\0\0M\u01cf\1\0"+
		"\0\0N\u0267\1\0\0\0O\u0266\1\0\0\0O\u0274\1\0\0\0O\u030d\1\0\0\0P\u0269"+
		"\1\0\0\0Q\u01cf\1\0\0\0R\u0275\1\0\0\0S\u026d\1\0\0\0T\u0283\1\0\0\0U"+
		"\u0281\1\0\0\0V\u0291\1\0\0\0W\u0319\1\0\0\0X\u0299\1\0\0\0Y\u0292\1\0"+
		"\0\0Z\u02a5\1\0\0\0[\u029a\1\0\0\0\\\u02ad\1\0\0\0]\u00eb\1\0\0\0]\u0292"+
		"\1\0\0\0^\u02b1\1\0\0\0_\u0292\1\0\0\0`\u02c1\1\0\0\0a\u02b3\1\0\0\0b"+
		"\u02c3\1\0\0\0c\u029a\1\0\0\0d\u02d9\1\0\0\0e\u02c5\1\0\0\0f\u02db\1\0"+
		"\0\0g\u02c6\1\0\0\0g\u0309\1\0\0\0h\u02e7\1\0\0\0i\u02e1\1\0\0\0j\u02f6"+
		"\1\0\0\0k\u02ef\1\0\0\0k\u02ee\1\0\0\0l\u02fe\1\0\0\0m\u02f4\1\0\0\0n"+
		"\u0300\1\0\0\0o\u02ff\1\0\0\0p\u0302\1\0\0\0q\u02ff\1\0\0\0r\u0308\1\0"+
		"\0\0s\u02f9\1\0\0\0t\u030a\1\0\0\0u\u029a\1\0\0\0v\u0318\1\0\0\0w\u0107"+
		"\1\0\0\0w\u020a\1\0\0\0w\u0209\1\0\0\0w\u028f\1\0\0\0w\u0303\1\0\0\0w"+
		"\u0309\1\0\0\0w\u0319\1\0\0\0w\u036f\1\0\0\0w\u036f\1\0\0\0w\u036f\1\0"+
		"\0\0w\u036f\1\0\0\0w\u036f\1\0\0\0w\u0344\1\0\0\0w\u034d\1\0\0\0w\u0353"+
		"\1\0\0\0w\u037f\1\0\0\0w\u03ba\1\0\0\0w\u03c0\1\0\0\0w\u03c2\1\0\0\0w"+
		"\u03c5\1\0\0\0w\u03f9\1\0\0\0w\u0416\1\0\0\0w\u045c\1\0\0\0w\u0499\1\0"+
		"\0\0w\u04b4\1\0\0\0w\u04b5\1\0\0\0w\u04bf\1\0\0\0w\u04c3\1\0\0\0w\u04f8"+
		"\1\0\0\0w\u04f9\1\0\0\0w\u0501\1\0\0\0w\u051d\1\0\0\0x\u0373\1\0\0\0y"+
		"\u0368\1\0\0\0z\u0379\1\0\0\0{\u0319\1\0\0\0|\u039f\1\0\0\0}\u01b5\1\0"+
		"\0\0}\u01b4\1\0\0\0}\u03b6\1\0\0\0}\u042e\1\0\0\0}\u042d\1\0\0\0}\u0470"+
		"\1\0\0\0}\u046f\1\0\0\0}\u04dd\1\0\0\0}\u04dc\1\0\0\0~\u03ad\1\0\0\0\177"+
		"\u03a0\1\0\0\0\177\u03f3\1\0\0\0\177\u040f\1\0\0\0\177\u0443\1\0\0\0\177"+
		"\u04ab\1\0\0\0\177\u04ad\1\0\0\0\u0080\u03af\1\0\0\0\u0081\u03ae\1\0\0"+
		"\0\u0082\u03b1\1\0\0\0\u0083\u03a0\1\0\0\0\u0084\u03b7\1\0\0\0\u0085\u03b3"+
		"\1\0\0\0\u0085\u050d\1\0\0\0\u0085\u0513\1\0\0\0\u0085\u0517\1\0\0\0\u0086"+
		"\u03b9\1\0\0\0\u0087\u03ae\1\0\0\0\u0088\u03bb\1\0\0\0\u0089\u03ae\1\0"+
		"\0\0\u0089\u04ed\1\0\0\0\u008a\u03c1\1\0\0\0\u008b\u03bd\1\0\0\0\u008c"+
		"\u03c3\1\0\0\0\u008d\u03ae\1\0\0\0\u008e\u03c7\1\0\0\0\u008f\u03ae\1\0"+
		"\0\0\u0090\u03d3\1\0\0\0\u0091\u03cb\1\0\0\0\u0092\u03dd\1\0\0\0\u0093"+
		"\u03d4\1\0\0\0\u0094\u03ed\1\0\0\0\u0095\u03d4\1\0\0\0\u0096\u03ef\1\0"+
		"\0\0\u0097\u03a0\1\0\0\0\u0097\u0402\1\0\0\0\u0098\u0409\1\0\0\0\u0099"+
		"\u03a0\1\0\0\0\u009a\u040b\1\0\0\0\u009b\u040a\1\0\0\0\u009c\u0422\1\0"+
		"\0\0\u009d\u041c\1\0\0\0\u009e\u043d\1\0\0\0\u009f\u0424\1\0\0\0\u00a0"+
		"\u043f\1\0\0\0\u00a1\u040a\1\0\0\0\u00a2\u0458\1\0\0\0\u00a3\u0449\1\0"+
		"\0\0\u00a4\u0464\1\0\0\0\u00a5\u044e\1\0\0\0\u00a6\u047f\1\0\0\0\u00a7"+
		"\u0466\1\0\0\0\u00a8\u0481\1\0\0\0\u00a9\u0480\1\0\0\0\u00aa\u048c\1\0"+
		"\0\0\u00ab\u03a0\1\0\0\0\u00ac\u0498\1\0\0\0\u00ad\u0495\1\0\0\0\u00ad"+
		"\u04a3\1\0\0\0\u00ae\u049c\1\0\0\0\u00af\u0495\1\0\0\0\u00b0\u04aa\1\0"+
		"\0\0\u00b1\u049d\1\0\0\0\u00b2\u04ac\1\0\0\0\u00b3\u04a9\1\0\0\0\u00b4"+
		"\u04ae\1\0\0\0\u00b5\u0495\1\0\0\0\u00b6\u04c0\1\0\0\0\u00b7\u03a0\1\0"+
		"\0\0\u00b8\u04c4\1\0\0\0\u00b9\u03a0\1\0\0\0\u00ba\u04d1\1\0\0\0\u00bb"+
		"\u04cb\1\0\0\0\u00bc\u04f0\1\0\0\0\u00bd\u04d3\1\0\0\0\u00be\u04fc\1\0"+
		"\0\0\u00bf\u04ed\1\0\0\0\u00c0\u0500\1\0\0\0\u00c1\u04ff\1\0\0\0\u00c2"+
		"\u0502\1\0\0\0\u00c3\u03a0\1\0\0\0\u00c4\u0508\1\0\0\0\u00c5\u03a0\1\0"+
		"\0\0\u00c6\u050e\1\0\0\0\u00c7\u03a0\1\0\0\0\u00c8\u0514\1\0\0\0\u00c9"+
		"\u03a0\1\0\0\0\u00ca\u0518\1\0\0\0\u00cb\u03a0\1\0\0\0\u00cc\u051a\1\0"+
		"\0\0\u00cd\u03a0\1\0\0\0\u00ce\u051e\1\0\0\0\u00cf\u0319\1\0\0\0\u00d0"+
		"\u0536\1\0\0\0\u00d1\u0526\1\0\0\0\u00d2\u0538\1\0\0\0\u00d3\u0583\5\uffff"+
		"\0\0\u00d4\u054e\1\0\0\0\u00d5\u053a\1\0\0\0\u00d6\u0554\1\0\0\0\u00d7"+
		"\u02ab\1\0\0\0\u00d7\u0552\1\0\0\0\u00d7\u0577\1\0\0\0\u00d8\u0556\1\0"+
		"\0\0\u00d9\u053e\1\0\0\0\u00da\u057f\1\0\0\0\u00db\u0570\1\0\0\0\u00db"+
		"\u0564\1\0\0\0\u00db\u0563\1\0\0\0\u00dc\u0581\1\0\0\0\u00dd\u0580\1\0"+
		"\0\0\u00dd\u0579\1\0\0\0\u00dd\u057d\1\0\0\0\u00de\u00e9\3\2\1\0\u00e0"+
		"\u00e9\3\4\2\0\u00e2\u00e4\5A\0\0\u00e4\u00e6\3\0\0\0\u00e6\u00e7\5B\0"+
		"\0\u00e7\u00e9\1\0\0\0\u00e8\u00de\1\0\0\0\u00e8\u00e0\1\0\0\0\u00e8\u00e2"+
		"\1\0\0\0\u00e9\1\1\0\0\0\u00ea\u00eb\3\\.\0\u00eb\3\1\0\0\0\u00ec\u00fd"+
		"\3\6\3\0\u00ee\u00fd\3\16\7\0\u00f0\u00fd\3\26\13\0\u00f2\u00fd\3\32\r"+
		"\0\u00f4\u00fd\3&\23\0\u00f6\u00fd\3\f\6\0\u00f8\u00fd\3.\27\0\u00fa\u00fd"+
		"\3\62\31\0\u00fc\u00ec\1\0\0\0\u00fc\u00ee\1\0\0\0\u00fc\u00f0\1\0\0\0"+
		"\u00fc\u00f2\1\0\0\0\u00fc\u00f4\1\0\0\0\u00fc\u00f6\1\0\0\0\u00fc\u00f8"+
		"\1\0\0\0\u00fc\u00fa\1\0\0\0\u00fd\5\1\0\0\0\u00fe\u0100\5C\0\0\u0100"+
		"\u0102\3\b\4\0\u0102\u0104\5D\0\0\u0104\u0105\3\n\5\0\u0105\7\1\0\0\0"+
		"\u0106\u0107\3v;\0\u0107\t\1\0\0\0\u0108\u0109\3\0\0\0\u0109\13\1\0\0"+
		"\0\u010a\u010c\5C\0\0\u010c\u010e\5D\0\0\u010e\u010f\3\n\5\0\u010f\r\1"+
		"\0\0\0\u0110\u0112\5\30\0\0\u0112\u011a\5E\0\0\u0114\u0116\3\20\b\0\u0116"+
		"\u0117\5I\0\0\u0117\u0119\1\0\0\0\u0118\u0114\1\0\0\0\u0119\u011c\1\0"+
		"\0\0\u011a\u0118\1\0\0\0\u011a\u011b\1\0\0\0\u011b\u011f\1\0\0\0\u011c"+
		"\u011a\1\0\0\0\u011d\u0120\3\20\b\0\u011f\u011d\1\0\0\0\u011f\u0120\1"+
		"\0\0\0\u0120\u0121\1\0\0\0\u0121\u0122\5F\0\0\u0122\17\1\0\0\0\u0123\u0125"+
		"\3>\37\0\u0125\u0126\3\0\0\0\u0126\u012a\1\0\0\0\u0127\u012a\3\22\t\0"+
		"\u0129\u0123\1\0\0\0\u0129\u0127\1\0\0\0\u012a\u012d\1\0\0\0\u012b\u012e"+
		"\3\24\n\0\u012d\u012b\1\0\0\0\u012d\u012e\1\0\0\0\u012e\21\1\0\0\0\u012f"+
		"\u0132\5\36\0\0\u0131\u012f\1\0\0\0\u0131\u0132\1\0\0\0\u0132\u0133\1"+
		"\0\0\0\u0133\u0134\3\2\1\0\u0134\23\1\0\0\0\u0135\u0136\5T\0\0\u0136\25"+
		"\1\0\0\0\u0137\u0139\5\36\0\0\u0139\u013a\3\30\f\0\u013a\27\1\0\0\0\u013b"+
		"\u013c\3\0\0\0\u013c\31\1\0\0\0\u013d\u013f\5\r\0\0\u013f\u0140\3\34\16"+
		"\0\u0140\33\1\0\0\0\u0141\u0145\3 \20\0\u0143\u0146\3\36\17\0\u0145\u0143"+
		"\1\0\0\0\u0145\u0146\1\0\0\0\u0146\35\1\0\0\0\u0147\u014c\3 \20\0\u0149"+
		"\u014c\3\0\0\0\u014b\u0147\1\0\0\0\u014b\u0149\1\0\0\0\u014c\37\1\0\0"+
		"\0\u014d\u0155\5A\0\0\u014f\u0153\3\"\21\0\u0151\u0154\5G\0\0\u0153\u0151"+
		"\1\0\0\0\u0153\u0154\1\0\0\0\u0154\u0156\1\0\0\0\u0155\u014f\1\0\0\0\u0155"+
		"\u0156\1\0\0\0\u0156\u0157\1\0\0\0\u0157\u0158\5B\0\0\u0158!\1\0\0\0\u0159"+
		"\u0161\3$\22\0\u015b\u015d\5G\0\0\u015d\u0160\3$\22\0\u015f\u015b\1\0"+
		"\0\0\u0160\u0163\1\0\0\0\u0161\u015f\1\0\0\0\u0161\u0162\1\0\0\0\u0162"+
		"#\1\0\0\0\u0163\u0161\1\0\0\0\u0164\u0167\3>\37\0\u0166\u0164\1\0\0\0"+
		"\u0166\u0167\1\0\0\0\u0167\u016a\1\0\0\0\u0168\u016b\5@\0\0\u016a\u0168"+
		"\1\0\0\0\u016a\u016b\1\0\0\0\u016b\u016c\1\0\0\0\u016c\u016d\3\0\0\0\u016d"+
		"%\1\0\0\0\u016e\u0170\5\22\0\0\u0170\u0181\5E\0\0\u0172\u017a\3(\24\0"+
		"\u0174\u0176\5I\0\0\u0176\u0179\3(\24\0\u0178\u0174\1\0\0\0\u0179\u017c"+
		"\1\0\0\0\u017a\u0178\1\0\0\0\u017a\u017b\1\0\0\0\u017b\u017f\1\0\0\0\u017c"+
		"\u017a\1\0\0\0\u017d\u0180\5I\0\0\u017f\u017d\1\0\0\0\u017f\u0180\1\0"+
		"\0\0\u0180\u0182\1\0\0\0\u0181\u0172\1\0\0\0\u0181\u0182\1\0\0\0\u0182"+
		"\u0183\1\0\0\0\u0183\u0184\5F\0\0\u0184\'\1\0\0\0\u0185\u0187\3*\25\0"+
		"\u0187\u0188\3\34\16\0\u0188\u018c\1\0\0\0\u0189\u018c\3,\26\0\u018b\u0185"+
		"\1\0\0\0\u018b\u0189\1\0\0\0\u018c)\1\0\0\0\u018d\u018e\5K\0\0\u018e+"+
		"\1\0\0\0\u018f\u0190\3\2\1\0\u0190-\1\0\0\0\u0191\u0193\5\23\0\0\u0193"+
		"\u0195\5C\0\0\u0195\u0197\3\60\30\0\u0197\u0199\5D\0\0\u0199\u019a\3\n"+
		"\5\0\u019a/\1\0\0\0\u019b\u019c\3\0\0\0\u019c\61\1\0\0\0\u019d\u01a1\5"+
		"\5\0\0\u019f\u01a2\5\64\0\0\u01a1\u019f\1\0\0\0\u01a1\u01a2\1\0\0\0\u01a2"+
		"\u01a8\1\0\0\0\u01a3\u01a5\5\64\0\0\u01a5\u01a8\5\5\0\0\u01a7\u019d\1"+
		"\0\0\0\u01a7\u01a3\1\0\0\0\u01a8\u01a9\1\0\0\0\u01a9\u01aa\3\n\5\0\u01aa"+
		"\63\1\0\0\0\u01ab\u01bc\5E\0\0\u01ad\u01b5\3|>\0\u01af\u01b1\5I\0\0\u01b1"+
		"\u01b4\3|>\0\u01b3\u01af\1\0\0\0\u01b4\u01b7\1\0\0\0\u01b5\u01b3\1\0\0"+
		"\0\u01b5\u01b6\1\0\0\0\u01b6\u01ba\1\0\0\0\u01b7\u01b5\1\0\0\0\u01b8\u01bb"+
		"\5I\0\0\u01ba\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb\u01bd\1\0\0\0\u01bc"+
		"\u01ad\1\0\0\0\u01bc\u01bd\1\0\0\0\u01bd\u01be\1\0\0\0\u01be\u01bf\5F"+
		"\0\0\u01bf\65\1\0\0\0\u01c0\u01c7\3:\35\0\u01c2\u01c7\3B!\0\u01c4\u01c7"+
		"\3F#\0\u01c6\u01c0\1\0\0\0\u01c6\u01c2\1\0\0\0\u01c6\u01c4\1\0\0\0\u01c7"+
		"\67\1\0\0\0\u01c8\u01cf\3\66\33\0\u01ca\u01cf\3L&\0\u01cc\u01cf\3P(\0"+
		"\u01ce\u01c8\1\0\0\0\u01ce\u01ca\1\0\0\0\u01ce\u01cc\1\0\0\0\u01cf9\1"+
		"\0\0\0\u01d0\u01e9\5\6\0\0\u01d2\u01ea\3<\36\0\u01d4\u01e5\5A\0\0\u01d6"+
		"\u01de\3<\36\0\u01d8\u01da\5I\0\0\u01da\u01dd\3<\36\0\u01dc\u01d8\1\0"+
		"\0\0\u01dd\u01e0\1\0\0\0\u01de\u01dc\1\0\0\0\u01de\u01df\1\0\0\0\u01df"+
		"\u01e3\1\0\0\0\u01e0\u01de\1\0\0\0\u01e1\u01e4\5I\0\0\u01e3\u01e1\1\0"+
		"\0\0\u01e3\u01e4\1\0\0\0\u01e4\u01e6\1\0\0\0\u01e5\u01d6\1\0\0\0\u01e5"+
		"\u01e6\1\0\0\0\u01e6\u01e7\1\0\0\0\u01e7\u01ea\5B\0\0\u01e9\u01d2\1\0"+
		"\0\0\u01e9\u01d4\1\0\0\0\u01ea;\1\0\0\0\u01eb\u01f5\3>\37\0\u01ed\u01f0"+
		"\3\0\0\0\u01ef\u01ed\1\0\0\0\u01ef\u01f0\1\0\0\0\u01f0\u01f1\1\0\0\0\u01f1"+
		"\u01f3\5:\0\0\u01f3\u01f6\3@ \0\u01f5\u01ef\1\0\0\0\u01f5\u01f6\1\0\0"+
		"\0\u01f6=\1\0\0\0\u01f7\u01ff\5K\0\0\u01f9\u01fb\5G\0\0\u01fb\u01fe\5"+
		"K\0\0\u01fd\u01f9\1\0\0\0\u01fe\u0201\1\0\0\0\u01ff\u01fd\1\0\0\0\u01ff"+
		"\u0200\1\0\0\0\u0200?\1\0\0\0\u0201\u01ff\1\0\0\0\u0202\u020a\3v;\0\u0204"+
		"\u0206\5G\0\0\u0206\u0209\3v;\0\u0208\u0204\1\0\0\0\u0209\u020c\1\0\0"+
		"\0\u020a\u0208\1\0\0\0\u020a\u020b\1\0\0\0\u020bA\1\0\0\0\u020c\u020a"+
		"\1\0\0\0\u020d\u0226\5\32\0\0\u020f\u0227\3D\"\0\u0211\u0222\5A\0\0\u0213"+
		"\u021b\3D\"\0\u0215\u0217\5I\0\0\u0217\u021a\3D\"\0\u0219\u0215\1\0\0"+
		"\0\u021a\u021d\1\0\0\0\u021b\u0219\1\0\0\0\u021b\u021c\1\0\0\0\u021c\u0220"+
		"\1\0\0\0\u021d\u021b\1\0\0\0\u021e\u0221\5I\0\0\u0220\u021e\1\0\0\0\u0220"+
		"\u0221\1\0\0\0\u0221\u0223\1\0\0\0\u0222\u0213\1\0\0\0\u0222\u0223\1\0"+
		"\0\0\u0223\u0224\1\0\0\0\u0224\u0227\5B\0\0\u0226\u020f\1\0\0\0\u0226"+
		"\u0211\1\0\0\0\u0227C\1\0\0\0\u0228\u022a\5K\0\0\u022a\u022b\3\0\0\0\u022b"+
		"E\1\0\0\0\u022c\u0245\5\33\0\0\u022e\u0246\3H$\0\u0230\u0241\5A\0\0\u0232"+
		"\u023a\3H$\0\u0234\u0236\5I\0\0\u0236\u0239\3H$\0\u0238\u0234\1\0\0\0"+
		"\u0239\u023c\1\0\0\0\u023a\u0238\1\0\0\0\u023a\u023b\1\0\0\0\u023b\u023f"+
		"\1\0\0\0\u023c\u023a\1\0\0\0\u023d\u0240\5I\0\0\u023f\u023d\1\0\0\0\u023f"+
		"\u0240\1\0\0\0\u0240\u0242\1\0\0\0\u0241\u0232\1\0\0\0\u0241\u0242\1\0"+
		"\0\0\u0242\u0243\1\0\0\0\u0243\u0246\5B\0\0\u0245\u022e\1\0\0\0\u0245"+
		"\u0230\1\0\0\0\u0246G\1\0\0\0\u0247\u0255\3>\37\0\u0249\u024f\3\0\0\0"+
		"\u024b\u024d\5:\0\0\u024d\u0250\3@ \0\u024f\u024b\1\0\0\0\u024f\u0250"+
		"\1\0\0\0\u0250\u0256\1\0\0\0\u0251\u0253\5:\0\0\u0253\u0256\3@ \0\u0255"+
		"\u0249\1\0\0\0\u0255\u0251\1\0\0\0\u0256I\1\0\0\0\u0257\u0259\3>\37\0"+
		"\u0259\u025b\5?\0\0\u025b\u025c\3@ \0\u025cK\1\0\0\0\u025d\u025f\5\r\0"+
		"\0\u025f\u0261\5K\0\0\u0261\u0265\3\34\16\0\u0263\u0266\3N\'\0\u0265\u0263"+
		"\1\0\0\0\u0265\u0266\1\0\0\0\u0266M\1\0\0\0\u0267\u0268\3\64\32\0\u0268"+
		"O\1\0\0\0\u0269\u026b\5\r\0\0\u026b\u026d\3R)\0\u026d\u026f\3*\25\0\u026f"+
		"\u0273\3\34\16\0\u0271\u0274\3N\'\0\u0273\u0271\1\0\0\0\u0273\u0274\1"+
		"\0\0\0\u0274Q\1\0\0\0\u0275\u0279\5A\0\0\u0277\u027a\5K\0\0\u0279\u0277"+
		"\1\0\0\0\u0279\u027a\1\0\0\0\u027a\u027d\1\0\0\0\u027b\u027e\5\36\0\0"+
		"\u027d\u027b\1\0\0\0\u027d\u027e\1\0\0\0\u027e\u027f\1\0\0\0\u027f\u0281"+
		"\3T*\0\u0281\u0282\5B\0\0\u0282S\1\0\0\0\u0283\u0284\5K\0\0\u0284U\1\0"+
		"\0\0\u0285\u0292\3X,\0\u0287\u0292\3\\.\0\u0289\u0292\3^/\0\u028b\u028d"+
		"\5A\0\0\u028d\u028f\3v;\0\u028f\u0290\5B\0\0\u0290\u0292\1\0\0\0\u0291"+
		"\u0285\1\0\0\0\u0291\u0287\1\0\0\0\u0291\u0289\1\0\0\0\u0291\u028b\1\0"+
		"\0\0\u0292W\1\0\0\0\u0293\u029a\3Z-\0\u0295\u029a\3b\61\0\u0297\u029a"+
		"\3t:\0\u0299\u0293\1\0\0\0\u0299\u0295\1\0\0\0\u0299\u0297\1\0\0\0\u029a"+
		"Y\1\0\0\0\u029b\u02a6\5P\0\0\u029d\u02a6\5R\0\0\u029f\u02a6\5Q\0\0\u02a1"+
		"\u02a6\5S\0\0\u02a3\u02a6\5T\0\0\u02a5\u029b\1\0\0\0\u02a5\u029d\1\0\0"+
		"\0\u02a5\u029f\1\0\0\0\u02a5\u02a1\1\0\0\0\u02a5\u02a3\1\0\0\0\u02a6["+
		"\1\0\0\0\u02a7\u02a9\4.\0\0\u02a9\u02ab\3\u00d6k\0\u02ab\u02ac\5H\0\0"+
		"\u02ac\u02ae\1\0\0\0\u02ad\u02a7\1\0\0\0\u02ad\u02ae\1\0\0\0\u02ae\u02af"+
		"\1\0\0\0\u02af\u02b0\5K\0\0\u02b0]\1\0\0\0\u02b1\u02b3\3`\60\0\u02b3\u02b5"+
		"\5H\0\0\u02b5\u02b6\3*\25\0\u02b6_\1\0\0\0\u02b7\u02c2\3\2\1\0\u02b9\u02bb"+
		"\5A\0\0\u02bb\u02bd\5\36\0\0\u02bd\u02bf\3\2\1\0\u02bf\u02c0\5B\0\0\u02c0"+
		"\u02c2\1\0\0\0\u02c1\u02b7\1\0\0\0\u02c1\u02b9\1\0\0\0\u02c2a\1\0\0\0"+
		"\u02c3\u02c5\3d\62\0\u02c5\u02c6\3f\63\0\u02c6c\1\0\0\0\u02c7\u02da\3"+
		"\16\7\0\u02c9\u02da\3\6\3\0\u02cb\u02cd\5C\0\0\u02cd\u02cf\5@\0\0\u02cf"+
		"\u02d1\5D\0\0\u02d1\u02da\3\n\5\0\u02d3\u02da\3\f\6\0\u02d5\u02da\3.\27"+
		"\0\u02d7\u02da\3\2\1\0\u02d9\u02c7\1\0\0\0\u02d9\u02c9\1\0\0\0\u02d9\u02cb"+
		"\1\0\0\0\u02d9\u02d3\1\0\0\0\u02d9\u02d5\1\0\0\0\u02d9\u02d7\1\0\0\0\u02da"+
		"e\1\0\0\0\u02db\u02e3\5E\0\0\u02dd\u02e1\3h\64\0\u02df\u02e2\5G\0\0\u02e1"+
		"\u02df\1\0\0\0\u02e1\u02e2\1\0\0\0\u02e2\u02e4\1\0\0\0\u02e3\u02dd\1\0"+
		"\0\0\u02e3\u02e4\1\0\0\0\u02e4\u02e5\1\0\0\0\u02e5\u02e6\5F\0\0\u02e6"+
		"g\1\0\0\0\u02e7\u02ef\3j\65\0\u02e9\u02eb\5G\0\0\u02eb\u02ee\3j\65\0\u02ed"+
		"\u02e9\1\0\0\0\u02ee\u02f1\1\0\0\0\u02ef\u02ed\1\0\0\0\u02ef\u02f0\1\0"+
		"\0\0\u02f0i\1\0\0\0\u02f1\u02ef\1\0\0\0\u02f2\u02f4\3l\66\0\u02f4\u02f5"+
		"\5J\0\0\u02f5\u02f7\1\0\0\0\u02f6\u02f2\1\0\0\0\u02f6\u02f7\1\0\0\0\u02f7"+
		"\u02f8\1\0\0\0\u02f8\u02f9\3r9\0\u02f9k\1\0\0\0\u02fa\u02ff\3n\67\0\u02fc"+
		"\u02ff\3p8\0\u02fe\u02fa\1\0\0\0\u02fe\u02fc\1\0\0\0\u02ffm\1\0\0\0\u0300"+
		"\u0301\5K\0\0\u0301o\1\0\0\0\u0302\u0303\3v;\0\u0303q\1\0\0\0\u0304\u0309"+
		"\3v;\0\u0306\u0309\3f\63\0\u0308\u0304\1\0\0\0\u0308\u0306\1\0\0\0\u0309"+
		"s\1\0\0\0\u030a\u030c\3\32\r\0\u030c\u030d\3N\'\0\u030du\1\0\0\0\u030e"+
		"\u0310\7\0\0\0\u0310\u0319\3v;\0\u0312\u0319\3V+\0\u0314\u0319\3z=\0\u0316"+
		"\u0319\3\u00ceg\0\u0318\u030e\1\0\0\0\u0318\u0312\1\0\0\0\u0318\u0314"+
		"\1\0\0\0\u0318\u0316\1\0\0\0\u0319\u0370\1\0\0\0\u031a\u031c\4;\1\1\u031c"+
		"\u031e\7\1\0\0\u031e\u036f\3v;\0\u0320\u0322\4;\2\1\u0322\u0324\7\2\0"+
		"\0\u0324\u036f\3v;\0\u0326\u0328\4;\3\1\u0328\u032a\7\3\0\0\u032a\u036f"+
		"\3v;\0\u032c\u032e\4;\4\1\u032e\u0330\5\62\0\0\u0330\u036f\3v;\0\u0332"+
		"\u0334\4;\5\1\u0334\u0336\5\63\0\0\u0336\u036f\3v;\0\u0338\u033a\4;\6"+
		"\1\u033a\u033c\5H\0\0\u033c\u036f\5K\0\0\u033e\u0340\4;\7\1\u0340\u0342"+
		"\5C\0\0\u0342\u0344\3v;\0\u0344\u0345\5D\0\0\u0345\u036f\1\0\0\0\u0346"+
		"\u0348\4;\b\1\u0348\u034c\5C\0\0\u034a\u034d\3v;\0\u034c\u034a\1\0\0\0"+
		"\u034c\u034d\1\0\0\0\u034d\u034e\1\0\0\0\u034e\u0352\5J\0\0\u0350\u0353"+
		"\3v;\0\u0352\u0350\1\0\0\0\u0352\u0353\1\0\0\0\u0353\u0354\1\0\0\0\u0354"+
		"\u036f\5D\0\0\u0356\u0358\4;\t\1\u0358\u035a\5H\0\0\u035a\u035c\5A\0\0"+
		"\u035c\u035e\3\0\0\0\u035e\u035f\5B\0\0\u035f\u036f\1\0\0\0\u0360\u0362"+
		"\4;\n\1\u0362\u036a\5A\0\0\u0364\u0368\3x<\0\u0366\u0369\5G\0\0\u0368"+
		"\u0366\1\0\0\0\u0368\u0369\1\0\0\0\u0369\u036b\1\0\0\0\u036a\u0364\1\0"+
		"\0\0\u036a\u036b\1\0\0\0\u036b\u036c\1\0\0\0\u036c\u036f\5B\0\0\u036e"+
		"\u031a\1\0\0\0\u036e\u0320\1\0\0\0\u036e\u0326\1\0\0\0\u036e\u032c\1\0"+
		"\0\0\u036e\u0332\1\0\0\0\u036e\u0338\1\0\0\0\u036e\u033e\1\0\0\0\u036e"+
		"\u0346\1\0\0\0\u036e\u0356\1\0\0\0\u036e\u0360\1\0\0\0\u036f\u0372\1\0"+
		"\0\0\u0370\u036e\1\0\0\0\u0370\u0371\1\0\0\0\u0371w\1\0\0\0\u0372\u0370"+
		"\1\0\0\0\u0373\u0377\3@ \0\u0375\u0378\5@\0\0\u0377\u0375\1\0\0\0\u0377"+
		"\u0378\1\0\0\0\u0378y\1\0\0\0\u0379\u037b\3\0\0\0\u037b\u037d\5A\0\0\u037d"+
		"\u037f\3v;\0\u037f\u0380\5B\0\0\u0380{\1\0\0\0\u0381\u03a0\3\66\33\0\u0383"+
		"\u03a0\3\u0082A\0\u0385\u03a0\3~?\0\u0387\u03a0\3\u00b6[\0\u0389\u03a0"+
		"\3\u00c2a\0\u038b\u03a0\3\u00c4b\0\u038d\u03a0\3\u00c6c\0\u038f\u03a0"+
		"\3\u00c8d\0\u0391\u03a0\3\u00cae\0\u0393\u03a0\3\64\32\0\u0395\u03a0\3"+
		"\u0096K\0\u0397\u03a0\3\u0098L\0\u0399\u03a0\3\u00b8\\\0\u039b\u03a0\3"+
		"\u00aaU\0\u039d\u03a0\3\u00ccf\0\u039f\u0381\1\0\0\0\u039f\u0383\1\0\0"+
		"\0\u039f\u0385\1\0\0\0\u039f\u0387\1\0\0\0\u039f\u0389\1\0\0\0\u039f\u038b"+
		"\1\0\0\0\u039f\u038d\1\0\0\0\u039f\u038f\1\0\0\0\u039f\u0391\1\0\0\0\u039f"+
		"\u0393\1\0\0\0\u039f\u0395\1\0\0\0\u039f\u0397\1\0\0\0\u039f\u0399\1\0"+
		"\0\0\u039f\u039b\1\0\0\0\u039f\u039d\1\0\0\0\u03a0}\1\0\0\0\u03a1\u03ae"+
		"\3\u0080@\0\u03a3\u03ae\3\u0086C\0\u03a5\u03ae\3\u0088D\0\u03a7\u03ae"+
		"\3\u008cF\0\u03a9\u03ae\3\u008eG\0\u03ab\u03ae\3J%\0\u03ad\u03a1\1\0\0"+
		"\0\u03ad\u03a3\1\0\0\0\u03ad\u03a5\1\0\0\0\u03ad\u03a7\1\0\0\0\u03ad\u03a9"+
		"\1\0\0\0\u03ad\u03ab\1\0\0\0\u03ae\177\1\0\0\0\u03af\u03b0\1\0\0\0\u03b0"+
		"\u0081\1\0\0\0\u03b1\u03b3\3\u0084B\0\u03b3\u03b5\5J\0\0\u03b5\u03b6\3"+
		"|>\0\u03b6\u0083\1\0\0\0\u03b7\u03b8\5K\0\0\u03b8\u0085\1\0\0\0\u03b9"+
		"\u03ba\3v;\0\u03ba\u0087\1\0\0\0\u03bb\u03bd\3\u008aE\0\u03bd\u03bf\5"+
		"\64\0\0\u03bf\u03c0\3v;\0\u03c0\u0089\1\0\0\0\u03c1\u03c2\3v;\0\u03c2"+
		"\u008b\1\0\0\0\u03c3\u03c5\3v;\0\u03c5\u03c6\7\4\0\0\u03c6\u008d\1\0\0"+
		"\0\u03c7\u03c9\3@ \0\u03c9\u03cb\3\u0090H\0\u03cb\u03cc\3@ \0\u03cc\u008f"+
		"\1\0\0\0\u03cd\u03d4\3\u0092I\0\u03cf\u03d4\3\u0094J\0\u03d1\u03d4\5:"+
		"\0\0\u03d3\u03cd\1\0\0\0\u03d3\u03cf\1\0\0\0\u03d3\u03d1\1\0\0\0\u03d4"+
		"\u0091\1\0\0\0\u03d5\u03de\5\'\0\0\u03d7\u03de\5(\0\0\u03d9\u03de\5-\0"+
		"\0\u03db\u03de\5.\0\0\u03dd\u03d5\1\0\0\0\u03dd\u03d7\1\0\0\0\u03dd\u03d9"+
		"\1\0\0\0\u03dd\u03db\1\0\0\0\u03de\u0093\1\0\0\0\u03df\u03ee\5)\0\0\u03e1"+
		"\u03ee\5*\0\0\u03e3\u03ee\5.\0\0\u03e5\u03ee\5/\0\0\u03e7\u03ee\5\60\0"+
		"\0\u03e9\u03ee\5,\0\0\u03eb\u03ee\5\61\0\0\u03ed\u03df\1\0\0\0\u03ed\u03e1"+
		"\1\0\0\0\u03ed\u03e3\1\0\0\0\u03ed\u03e5\1\0\0\0\u03ed\u03e7\1\0\0\0\u03ed"+
		"\u03e9\1\0\0\0\u03ed\u03eb\1\0\0\0\u03ee\u0095\1\0\0\0\u03ef\u03f5\5\20"+
		"\0\0\u03f1\u03f3\3~?\0\u03f3\u03f4\5I\0\0\u03f4\u03f6\1\0\0\0\u03f5\u03f1"+
		"\1\0\0\0\u03f5\u03f6\1\0\0\0\u03f6\u03f7\1\0\0\0\u03f7\u03f9\3v;\0\u03f9"+
		"\u0403\3\64\32\0\u03fb\u0401\5\n\0\0\u03fd\u0402\3\u0096K\0\u03ff\u0402"+
		"\3\64\32\0\u0401\u03fd\1\0\0\0\u0401\u03ff\1\0\0\0\u0402\u0404\1\0\0\0"+
		"\u0403\u03fb\1\0\0\0\u0403\u0404\1\0\0\0\u0404\u0097\1\0\0\0\u0405\u040a"+
		"\3\u009aM\0\u0407\u040a\3\u00a0P\0\u0409\u0405\1\0\0\0\u0409\u0407\1\0"+
		"\0\0\u040a\u0099\1\0\0\0\u040b\u0411\5\31\0\0\u040d\u040f\3~?\0\u040f"+
		"\u0410\5I\0\0\u0410\u0412\1\0\0\0\u0411\u040d\1\0\0\0\u0411\u0412\1\0"+
		"\0\0\u0412\u0415\1\0\0\0\u0413\u0416\3v;\0\u0415\u0413\1\0\0\0\u0415\u0416"+
		"\1\0\0\0\u0416\u0417\1\0\0\0\u0417\u041d\5E\0\0\u0419\u041c\3\u009cN\0"+
		"\u041b\u0419\1\0\0\0\u041c\u041f\1\0\0\0\u041d\u041b\1\0\0\0\u041d\u041e"+
		"\1\0\0\0\u041e\u0420\1\0\0\0\u041f\u041d\1\0\0\0\u0420\u0421\5F\0\0\u0421"+
		"\u009b\1\0\0\0\u0422\u0424\3\u009eO\0\u0424\u0435\5J\0\0\u0426\u042e\3"+
		"|>\0\u0428\u042a\5I\0\0\u042a\u042d\3|>\0\u042c\u0428\1\0\0\0\u042d\u0430"+
		"\1\0\0\0\u042e\u042c\1\0\0\0\u042e\u042f\1\0\0\0\u042f\u0433\1\0\0\0\u0430"+
		"\u042e\1\0\0\0\u0431\u0434\5I\0\0\u0433\u0431\1\0\0\0\u0433\u0434\1\0"+
		"\0\0\u0434\u0436\1\0\0\0\u0435\u0426\1\0\0\0\u0435\u0436\1\0\0\0\u0436"+
		"\u009d\1\0\0\0\u0437\u0439\5\4\0\0\u0439\u043e\3@ \0\u043b\u043e\5\b\0"+
		"\0\u043d\u0437\1\0\0\0\u043d\u043b\1\0\0\0\u043e\u009f\1\0\0\0\u043f\u0445"+
		"\5\31\0\0\u0441\u0443\3~?\0\u0443\u0444\5I\0\0\u0444\u0446\1\0\0\0\u0445"+
		"\u0441\1\0\0\0\u0445\u0446\1\0\0\0\u0446\u0447\1\0\0\0\u0447\u0449\3\u00a2"+
		"Q\0\u0449\u044f\5E\0\0\u044b\u044e\3\u00a4R\0\u044d\u044b\1\0\0\0\u044e"+
		"\u0451\1\0\0\0\u044f\u044d\1\0\0\0\u044f\u0450\1\0\0\0\u0450\u0452\1\0"+
		"\0\0\u0451\u044f\1\0\0\0\u0452\u0453\5F\0\0\u0453\u00a1\1\0\0\0\u0454"+
		"\u0456\5K\0\0\u0456\u0459\5?\0\0\u0458\u0454\1\0\0\0\u0458\u0459\1\0\0"+
		"\0\u0459\u045a\1\0\0\0\u045a\u045c\3v;\0\u045c\u045e\5H\0\0\u045e\u0460"+
		"\5A\0\0\u0460\u0462\5\32\0\0\u0462\u0463\5B\0\0\u0463\u00a3\1\0\0\0\u0464"+
		"\u0466\3\u00a6S\0\u0466\u0477\5J\0\0\u0468\u0470\3|>\0\u046a\u046c\5I"+
		"\0\0\u046c\u046f\3|>\0\u046e\u046a\1\0\0\0\u046f\u0472\1\0\0\0\u0470\u046e"+
		"\1\0\0\0\u0470\u0471\1\0\0\0\u0471\u0475\1\0\0\0\u0472\u0470\1\0\0\0\u0473"+
		"\u0476\5I\0\0\u0475\u0473\1\0\0\0\u0475\u0476\1\0\0\0\u0476\u0478\1\0"+
		"\0\0\u0477\u0468\1\0\0\0\u0477\u0478\1\0\0\0\u0478\u00a5\1\0\0\0\u0479"+
		"\u047b\5\4\0\0\u047b\u0480\3\u00a8T\0\u047d\u0480\5\b\0\0\u047f\u0479"+
		"\1\0\0\0\u047f\u047d\1\0\0\0\u0480\u00a7\1\0\0\0\u0481\u0489\3\0\0\0\u0483"+
		"\u0485\5G\0\0\u0485\u0488\3\0\0\0\u0487\u0483\1\0\0\0\u0488\u048b\1\0"+
		"\0\0\u0489\u0487\1\0\0\0\u0489\u048a\1\0\0\0\u048a\u00a9\1\0\0\0\u048b"+
		"\u0489\1\0\0\0\u048c\u0494\5\f\0\0\u048e\u0495\3\u00acV\0\u0490\u0495"+
		"\3\u00aeW\0\u0492\u0495\3\u00b4Z\0\u0494\u048e\1\0\0\0\u0494\u0490\1\0"+
		"\0\0\u0494\u0492\1\0\0\0\u0494\u0495\1\0\0\0\u0495\u0496\1\0\0\0\u0496"+
		"\u0497\3\64\32\0\u0497\u00ab\1\0\0\0\u0498\u0499\3v;\0\u0499\u00ad\1\0"+
		"\0\0\u049a\u049d\3\u00b0X\0\u049c\u049a\1\0\0\0\u049c\u049d\1\0\0\0\u049d"+
		"\u049e\1\0\0\0\u049e\u04a2\5I\0\0\u04a0\u04a3\3\u00acV\0\u04a2\u04a0\1"+
		"\0\0\0\u04a2\u04a3\1\0\0\0\u04a3\u04a4\1\0\0\0\u04a4\u04a8\5I\0\0\u04a6"+
		"\u04a9\3\u00b2Y\0\u04a8\u04a6\1\0\0\0\u04a8\u04a9\1\0\0\0\u04a9\u00af"+
		"\1\0\0\0\u04aa\u04ab\3~?\0\u04ab\u00b1\1\0\0\0\u04ac\u04ad\3~?\0\u04ad"+
		"\u00b3\1\0\0\0\u04ae\u04b4\3v;\0\u04b0\u04b2\5G\0\0\u04b2\u04b5\3v;\0"+
		"\u04b4\u04b0\1\0\0\0\u04b4\u04b5\1\0\0\0\u04b5\u04ba\1\0\0\0\u04b6\u04bb"+
		"\5:\0\0\u04b8\u04bb\5?\0\0\u04ba\u04b6\1\0\0\0\u04ba\u04b8\1\0\0\0\u04bb"+
		"\u04bc\1\0\0\0\u04bc\u04be\5\25\0\0\u04be\u04bf\3v;\0\u04bf\u00b5\1\0"+
		"\0\0\u04c0\u04c2\5\16\0\0\u04c2\u04c3\3v;\0\u04c3\u00b7\1\0\0\0\u04c4"+
		"\u04c6\5\27\0\0\u04c6\u04cc\5E\0\0\u04c8\u04cb\3\u00ba]\0\u04ca\u04c8"+
		"\1\0\0\0\u04cb\u04ce\1\0\0\0\u04cc\u04ca\1\0\0\0\u04cc\u04cd\1\0\0\0\u04cd"+
		"\u04cf\1\0\0\0\u04ce\u04cc\1\0\0\0\u04cf\u04d0\5F\0\0\u04d0\u00b9\1\0"+
		"\0\0\u04d1\u04d3\3\u00bc^\0\u04d3\u04e4\5J\0\0\u04d5\u04dd\3|>\0\u04d7"+
		"\u04d9\5I\0\0\u04d9\u04dc\3|>\0\u04db\u04d7\1\0\0\0\u04dc\u04df\1\0\0"+
		"\0\u04dd\u04db\1\0\0\0\u04dd\u04de\1\0\0\0\u04de\u04e2\1\0\0\0\u04df\u04dd"+
		"\1\0\0\0\u04e0\u04e3\5I\0\0\u04e2\u04e0\1\0\0\0\u04e2\u04e3\1\0\0\0\u04e3"+
		"\u04e5\1\0\0\0\u04e4\u04d5\1\0\0\0\u04e4\u04e5\1\0\0\0\u04e5\u00bb\1\0"+
		"\0\0\u04e6\u04ec\5\4\0\0\u04e8\u04ed\3\u0088D\0\u04ea\u04ed\3\u00be_\0"+
		"\u04ec\u04e8\1\0\0\0\u04ec\u04ea\1\0\0\0\u04ed\u04f1\1\0\0\0\u04ee\u04f1"+
		"\5\b\0\0\u04f0\u04e6\1\0\0\0\u04f0\u04ee\1\0\0\0\u04f1\u00bd\1\0\0\0\u04f2"+
		"\u04f8\3v;\0\u04f4\u04f6\5G\0\0\u04f6\u04f9\3v;\0\u04f8\u04f4\1\0\0\0"+
		"\u04f8\u04f9\1\0\0\0\u04f9\u04fa\1\0\0\0\u04fa\u04fb\7\5\0\0\u04fb\u04fd"+
		"\1\0\0\0\u04fc\u04f2\1\0\0\0\u04fc\u04fd\1\0\0\0\u04fd\u04fe\1\0\0\0\u04fe"+
		"\u04ff\3\u00c0`\0\u04ff\u00bf\1\0\0\0\u0500\u0501\3v;\0\u0501\u00c1\1"+
		"\0\0\0\u0502\u0506\5\26\0\0\u0504\u0507\3@ \0\u0506\u0504\1\0\0\0\u0506"+
		"\u0507\1\0\0\0\u0507\u00c3\1\0\0\0\u0508\u050c\5\3\0\0\u050a\u050d\3\u0084"+
		"B\0\u050c\u050a\1\0\0\0\u050c\u050d\1\0\0\0\u050d\u00c5\1\0\0\0\u050e"+
		"\u0512\5\7\0\0\u0510\u0513\3\u0084B\0\u0512\u0510\1\0\0\0\u0512\u0513"+
		"\1\0\0\0\u0513\u00c7\1\0\0\0\u0514\u0516\5\17\0\0\u0516\u0517\3\u0084"+
		"B\0\u0517\u00c9\1\0\0\0\u0518\u0519\5\13\0\0\u0519\u00cb\1\0\0\0\u051a"+
		"\u051c\5\t\0\0\u051c\u051d\3v;\0\u051d\u00cd\1\0\0\0\u051e\u0520\5K\0"+
		"\0\u0520\u0528\5A\0\0\u0522\u0526\3\u00d0h\0\u0524\u0527\5G\0\0\u0526"+
		"\u0524\1\0\0\0\u0526\u0527\1\0\0\0\u0527\u0529\1\0\0\0\u0528\u0522\1\0"+
		"\0\0\u0528\u0529\1\0\0\0\u0529\u052a\1\0\0\0\u052a\u052b\5B\0\0\u052b"+
		"\u00cf\1\0\0\0\u052c\u0532\3\0\0\0\u052e\u0530\5G\0\0\u0530\u0533\3@ "+
		"\0\u0532\u052e\1\0\0\0\u0532\u0533\1\0\0\0\u0533\u0537\1\0\0\0\u0534\u0537"+
		"\3@ \0\u0536\u052c\1\0\0\0\u0536\u0534\1\0\0\0\u0537\u00d1\1\0\0\0\u0538"+
		"\u053a\3\u00d4j\0\u053a\u0542\5I\0\0\u053c\u053e\3\u00d8l\0\u053e\u053f"+
		"\5I\0\0\u053f\u0541\1\0\0\0\u0540\u053c\1\0\0\0\u0541\u0544\1\0\0\0\u0542"+
		"\u0540\1\0\0\0\u0542\u0543\1\0\0\0\u0543\u054b\1\0\0\0\u0544\u0542\1\0"+
		"\0\0\u0545\u0547\38\34\0\u0547\u0548\5I\0\0\u0548\u054a\1\0\0\0\u0549"+
		"\u0545\1\0\0\0\u054a\u054d\1\0\0\0\u054b\u0549\1\0\0\0\u054b\u054c\1\0"+
		"\0\0\u054c\u00d3\1\0\0\0\u054d\u054b\1\0\0\0\u054e\u0550\5\24\0\0\u0550"+
		"\u0552\3\u00d6k\0\u0552\u0553\6j\uffff\0\u0553\u00d5\1\0\0\0\u0554\u0555"+
		"\5K\0\0\u0555\u00d7\1\0\0\0\u0556\u056f\5\21\0\0\u0558\u0570\3\u00dam"+
		"\0\u055a\u056b\5A\0\0\u055c\u0564\3\u00dam\0\u055e\u0560\5I\0\0\u0560"+
		"\u0563\3\u00dam\0\u0562\u055e\1\0\0\0\u0563\u0566\1\0\0\0\u0564\u0562"+
		"\1\0\0\0\u0564\u0565\1\0\0\0\u0565\u0569\1\0\0\0\u0566\u0564\1\0\0\0\u0567"+
		"\u056a\5I\0\0\u0569\u0567\1\0\0\0\u0569\u056a\1\0\0\0\u056a\u056c\1\0"+
		"\0\0\u056b\u055c\1\0\0\0\u056b\u056c\1\0\0\0\u056c\u056d\1\0\0\0\u056d"+
		"\u0570\5B\0\0\u056f\u0558\1\0\0\0\u056f\u055a\1\0\0\0\u0570\u00d9\1\0"+
		"\0\0\u0571\u0573\5H\0\0\u0573\u0580\3\u00dcn\0\u0575\u0577\3\u00d6k\0"+
		"\u0577\u0579\3\u00dcn\0\u0579\u057a\6m\uffff\0\u057a\u0580\1\0\0\0\u057b"+
		"\u057d\3\u00dcn\0\u057d\u057e\6m\uffff\0\u057e\u0580\1\0\0\0\u057f\u0571"+
		"\1\0\0\0\u057f\u0575\1\0\0\0\u057f\u057b\1\0\0\0\u0580\u00db\1\0\0\0\u0581"+
		"\u0582\5T\0\0\u0582\u00dd\1\0\0\0w\u00e8\1\u00fc\1\u011a\1\u011f\1\u0129"+
		"\1\u012d\1\u0131\1\u0145\1\u014b\1\u0153\1\u0155\1\u0161\1\u0166\1\u016a"+
		"\1\u017a\1\u017f\1\u0181\1\u018b\1\u01a1\1\u01a7\1\u01b5\1\u01ba\1\u01bc"+
		"\1\u01c6\1\u01ce\1\u01de\1\u01e3\1\u01e5\1\u01e9\1\u01ef\1\u01f5\1\u01ff"+
		"\1\u020a\1\u021b\1\u0220\1\u0222\1\u0226\1\u023a\1\u023f\1\u0241\1\u0245"+
		"\1\u024f\1\u0255\1\u0265\1\u0273\1\u0279\1\u027d\1\u0291\1\u0299\1\u02a5"+
		"\1\u02ad\1\u02c1\1\u02d9\1\u02e1\1\u02e3\1\u02ef\1\u02f6\1\u02fe\1\u0308"+
		"\1\u0318\1\u034c\1\u0352\1\u0368\1\u036a\1\u036e\1\u0370\1\u0377\1\u039f"+
		"\1\u03ad\1\u03d3\1\u03dd\1\u03ed\1\u03f5\1\u0401\1\u0403\1\u0409\1\u0411"+
		"\1\u0415\1\u041d\1\u042e\1\u0433\1\u0435\1\u043d\1\u0445\1\u044f\1\u0458"+
		"\1\u0470\1\u0475\1\u0477\1\u047f\1\u0489\1\u0494\1\u049c\1\u04a2\1\u04a8"+
		"\1\u04b4\1\u04ba\1\u04cc\1\u04dd\1\u04e2\1\u04e4\1\u04ec\1\u04f0\1\u04f8"+
		"\1\u04fc\1\u0506\1\u050c\1\u0512\1\u0526\1\u0528\1\u0532\1\u0536\1\u0542"+
		"\1\u054b\1\u0564\1\u0569\1\u056b\1\u056f\1\u057f\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}