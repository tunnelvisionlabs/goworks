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


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class BlankGoParserBaseListener implements GoParserBaseListener {
	@Override public void enterRule(GoParserBase.multExprContext ctx) { }
	@Override public void exitRule(GoParserBase.multExprContext ctx) { }

	@Override public void enterRule(GoParserBase.channelTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.channelTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.mulAssignOpContext ctx) { }
	@Override public void exitRule(GoParserBase.mulAssignOpContext ctx) { }

	@Override public void enterRule(GoParserBase.packageNameContext ctx) { }
	@Override public void exitRule(GoParserBase.packageNameContext ctx) { }

	@Override public void enterRule(GoParserBase.receiverContext ctx) { }
	@Override public void exitRule(GoParserBase.receiverContext ctx) { }

	@Override public void enterRule(GoParserBase.arrayTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.arrayTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.expressionListContext ctx) { }
	@Override public void exitRule(GoParserBase.expressionListContext ctx) { }

	@Override public void enterRule(GoParserBase.tagContext ctx) { }
	@Override public void exitRule(GoParserBase.tagContext ctx) { }

	@Override public void enterRule(GoParserBase.fallthroughStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.fallthroughStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.selectorExprContext ctx) { }
	@Override public void exitRule(GoParserBase.selectorExprContext ctx) { }

	@Override public void enterRule(GoParserBase.parameterListContext ctx) { }
	@Override public void exitRule(GoParserBase.parameterListContext ctx) { }

	@Override public void enterRule(GoParserBase.receiverTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.receiverTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.fieldNameContext ctx) { }
	@Override public void exitRule(GoParserBase.fieldNameContext ctx) { }

	@Override public void enterRule(GoParserBase.ifStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.ifStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.methodNameContext ctx) { }
	@Override public void exitRule(GoParserBase.methodNameContext ctx) { }

	@Override public void enterRule(GoParserBase.signatureContext ctx) { }
	@Override public void exitRule(GoParserBase.signatureContext ctx) { }

	@Override public void enterRule(GoParserBase.mapTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.mapTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.elementContext ctx) { }
	@Override public void exitRule(GoParserBase.elementContext ctx) { }

	@Override public void enterRule(GoParserBase.callExprContext ctx) { }
	@Override public void exitRule(GoParserBase.callExprContext ctx) { }

	@Override public void enterRule(GoParserBase.typeCaseClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.typeCaseClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.exprCaseClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.exprCaseClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.typeSwitchGuardContext ctx) { }
	@Override public void exitRule(GoParserBase.typeSwitchGuardContext ctx) { }

	@Override public void enterRule(GoParserBase.functionLiteralContext ctx) { }
	@Override public void exitRule(GoParserBase.functionLiteralContext ctx) { }

	@Override public void enterRule(GoParserBase.orExprContext ctx) { }
	@Override public void exitRule(GoParserBase.orExprContext ctx) { }

	@Override public void enterRule(GoParserBase.recvExprContext ctx) { }
	@Override public void exitRule(GoParserBase.recvExprContext ctx) { }

	@Override public void enterRule(GoParserBase.topLevelDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.topLevelDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.methodSpecContext ctx) { }
	@Override public void exitRule(GoParserBase.methodSpecContext ctx) { }

	@Override public void enterRule(GoParserBase.constSpecContext ctx) { }
	@Override public void exitRule(GoParserBase.constSpecContext ctx) { }

	@Override public void enterRule(GoParserBase.compositeLiteralContext ctx) { }
	@Override public void exitRule(GoParserBase.compositeLiteralContext ctx) { }

	@Override public void enterRule(GoParserBase.forClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.forClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.shortVarDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.shortVarDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.gotoStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.gotoStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.arrayLengthContext ctx) { }
	@Override public void exitRule(GoParserBase.arrayLengthContext ctx) { }

	@Override public void enterRule(GoParserBase.interfaceTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.interfaceTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.conversionContext ctx) { }
	@Override public void exitRule(GoParserBase.conversionContext ctx) { }

	@Override public void enterRule(GoParserBase.blockContext ctx) { }
	@Override public void exitRule(GoParserBase.blockContext ctx) { }

	@Override public void enterRule(GoParserBase.breakStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.breakStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.emptyStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.emptyStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.functionTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.functionTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.baseTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.baseTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.fieldDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.fieldDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.exprSwitchStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.exprSwitchStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.goStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.goStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.parameterDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.parameterDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.basicLiteralContext ctx) { }
	@Override public void exitRule(GoParserBase.basicLiteralContext ctx) { }

	@Override public void enterRule(GoParserBase.exprSwitchCaseContext ctx) { }
	@Override public void exitRule(GoParserBase.exprSwitchCaseContext ctx) { }

	@Override public void enterRule(GoParserBase.typeLiteralContext ctx) { }
	@Override public void exitRule(GoParserBase.typeLiteralContext ctx) { }

	@Override public void enterRule(GoParserBase.selectStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.selectStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.importSpecContext ctx) { }
	@Override public void exitRule(GoParserBase.importSpecContext ctx) { }

	@Override public void enterRule(GoParserBase.typeNameContext ctx) { }
	@Override public void exitRule(GoParserBase.typeNameContext ctx) { }

	@Override public void enterRule(GoParserBase.literalTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.literalTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.assignmentContext ctx) { }
	@Override public void exitRule(GoParserBase.assignmentContext ctx) { }

	@Override public void enterRule(GoParserBase.assignOpContext ctx) { }
	@Override public void exitRule(GoParserBase.assignOpContext ctx) { }

	@Override public void enterRule(GoParserBase.recvStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.recvStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.typeSpecContext ctx) { }
	@Override public void exitRule(GoParserBase.typeSpecContext ctx) { }

	@Override public void enterRule(GoParserBase.packageClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.packageClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.literalValueContext ctx) { }
	@Override public void exitRule(GoParserBase.literalValueContext ctx) { }

	@Override public void enterRule(GoParserBase.indexExprContext ctx) { }
	@Override public void exitRule(GoParserBase.indexExprContext ctx) { }

	@Override public void enterRule(GoParserBase.varSpecContext ctx) { }
	@Override public void exitRule(GoParserBase.varSpecContext ctx) { }

	@Override public void enterRule(GoParserBase.expressionContext ctx) { }
	@Override public void exitRule(GoParserBase.expressionContext ctx) { }

	@Override public void enterRule(GoParserBase.bodyContext ctx) { }
	@Override public void exitRule(GoParserBase.bodyContext ctx) { }

	@Override public void enterRule(GoParserBase.commClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.commClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.qualifiedIdentifierContext ctx) { }
	@Override public void exitRule(GoParserBase.qualifiedIdentifierContext ctx) { }

	@Override public void enterRule(GoParserBase.returnStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.returnStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.simpleStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.simpleStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.typeAssertionExprContext ctx) { }
	@Override public void exitRule(GoParserBase.typeAssertionExprContext ctx) { }

	@Override public void enterRule(GoParserBase.typeContext ctx) { }
	@Override public void exitRule(GoParserBase.typeContext ctx) { }

	@Override public void enterRule(GoParserBase.interfaceTypeNameContext ctx) { }
	@Override public void exitRule(GoParserBase.interfaceTypeNameContext ctx) { }

	@Override public void enterRule(GoParserBase.continueStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.continueStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.valueContext ctx) { }
	@Override public void exitRule(GoParserBase.valueContext ctx) { }

	@Override public void enterRule(GoParserBase.methodDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.methodDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.labeledStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.labeledStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.parametersContext ctx) { }
	@Override public void exitRule(GoParserBase.parametersContext ctx) { }

	@Override public void enterRule(GoParserBase.deferStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.deferStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.keyContext ctx) { }
	@Override public void exitRule(GoParserBase.keyContext ctx) { }

	@Override public void enterRule(GoParserBase.declarationContext ctx) { }
	@Override public void exitRule(GoParserBase.declarationContext ctx) { }

	@Override public void enterRule(GoParserBase.commCaseContext ctx) { }
	@Override public void exitRule(GoParserBase.commCaseContext ctx) { }

	@Override public void enterRule(GoParserBase.builtinArgsContext ctx) { }
	@Override public void exitRule(GoParserBase.builtinArgsContext ctx) { }

	@Override public void enterRule(GoParserBase.conditionContext ctx) { }
	@Override public void exitRule(GoParserBase.conditionContext ctx) { }

	@Override public void enterRule(GoParserBase.conversionOrCallExprContext ctx) { }
	@Override public void exitRule(GoParserBase.conversionOrCallExprContext ctx) { }

	@Override public void enterRule(GoParserBase.labelContext ctx) { }
	@Override public void exitRule(GoParserBase.labelContext ctx) { }

	@Override public void enterRule(GoParserBase.elementTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.elementTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.functionDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.functionDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.statementContext ctx) { }
	@Override public void exitRule(GoParserBase.statementContext ctx) { }

	@Override public void enterRule(GoParserBase.pointerTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.pointerTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.addAssignOpContext ctx) { }
	@Override public void exitRule(GoParserBase.addAssignOpContext ctx) { }

	@Override public void enterRule(GoParserBase.sourceFileContext ctx) { }
	@Override public void exitRule(GoParserBase.sourceFileContext ctx) { }

	@Override public void enterRule(GoParserBase.sliceExprContext ctx) { }
	@Override public void exitRule(GoParserBase.sliceExprContext ctx) { }

	@Override public void enterRule(GoParserBase.baseTypeNameContext ctx) { }
	@Override public void exitRule(GoParserBase.baseTypeNameContext ctx) { }

	@Override public void enterRule(GoParserBase.methodExprContext ctx) { }
	@Override public void exitRule(GoParserBase.methodExprContext ctx) { }

	@Override public void enterRule(GoParserBase.elementIndexContext ctx) { }
	@Override public void exitRule(GoParserBase.elementIndexContext ctx) { }

	@Override public void enterRule(GoParserBase.typeListContext ctx) { }
	@Override public void exitRule(GoParserBase.typeListContext ctx) { }

	@Override public void enterRule(GoParserBase.incDecStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.incDecStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.builtinCallContext ctx) { }
	@Override public void exitRule(GoParserBase.builtinCallContext ctx) { }

	@Override public void enterRule(GoParserBase.constDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.constDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.resultContext ctx) { }
	@Override public void exitRule(GoParserBase.resultContext ctx) { }

	@Override public void enterRule(GoParserBase.andExprContext ctx) { }
	@Override public void exitRule(GoParserBase.andExprContext ctx) { }

	@Override public void enterRule(GoParserBase.structTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.structTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.varDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.varDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.initStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.initStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.identifierListContext ctx) { }
	@Override public void exitRule(GoParserBase.identifierListContext ctx) { }

	@Override public void enterRule(GoParserBase.sliceTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.sliceTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.compareExprContext ctx) { }
	@Override public void exitRule(GoParserBase.compareExprContext ctx) { }

	@Override public void enterRule(GoParserBase.importDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.importDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.elementListContext ctx) { }
	@Override public void exitRule(GoParserBase.elementListContext ctx) { }

	@Override public void enterRule(GoParserBase.keyTypeContext ctx) { }
	@Override public void exitRule(GoParserBase.keyTypeContext ctx) { }

	@Override public void enterRule(GoParserBase.importPathContext ctx) { }
	@Override public void exitRule(GoParserBase.importPathContext ctx) { }

	@Override public void enterRule(GoParserBase.anonymousFieldContext ctx) { }
	@Override public void exitRule(GoParserBase.anonymousFieldContext ctx) { }

	@Override public void enterRule(GoParserBase.addExprContext ctx) { }
	@Override public void exitRule(GoParserBase.addExprContext ctx) { }

	@Override public void enterRule(GoParserBase.expressionStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.expressionStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.sendStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.sendStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.switchStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.switchStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.postStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.postStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.forStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.forStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.typeSwitchCaseContext ctx) { }
	@Override public void exitRule(GoParserBase.typeSwitchCaseContext ctx) { }

	@Override public void enterRule(GoParserBase.rangeClauseContext ctx) { }
	@Override public void exitRule(GoParserBase.rangeClauseContext ctx) { }

	@Override public void enterRule(GoParserBase.operandContext ctx) { }
	@Override public void exitRule(GoParserBase.operandContext ctx) { }

	@Override public void enterRule(GoParserBase.argumentListContext ctx) { }
	@Override public void exitRule(GoParserBase.argumentListContext ctx) { }

	@Override public void enterRule(GoParserBase.typeSwitchStmtContext ctx) { }
	@Override public void exitRule(GoParserBase.typeSwitchStmtContext ctx) { }

	@Override public void enterRule(GoParserBase.typeDeclContext ctx) { }
	@Override public void exitRule(GoParserBase.typeDeclContext ctx) { }

	@Override public void enterRule(GoParserBase.unaryExprContext ctx) { }
	@Override public void exitRule(GoParserBase.unaryExprContext ctx) { }

	@Override public void enterRule(GoParserBase.channelContext ctx) { }
	@Override public void exitRule(GoParserBase.channelContext ctx) { }

	@Override public void enterRule(GoParserBase.literalContext ctx) { }
	@Override public void exitRule(GoParserBase.literalContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}