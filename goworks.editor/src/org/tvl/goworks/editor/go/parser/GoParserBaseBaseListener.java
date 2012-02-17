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


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class GoParserBaseBaseListener implements GoParserBaseListener {
	@Override public void multExprEnter(GoParserBase.multExprContext ctx) { }
	@Override public void multExprExit(GoParserBase.multExprContext ctx) { }

	@Override public void channelTypeEnter(GoParserBase.channelTypeContext ctx) { }
	@Override public void channelTypeExit(GoParserBase.channelTypeContext ctx) { }

	@Override public void mulAssignOpEnter(GoParserBase.mulAssignOpContext ctx) { }
	@Override public void mulAssignOpExit(GoParserBase.mulAssignOpContext ctx) { }

	@Override public void packageNameEnter(GoParserBase.packageNameContext ctx) { }
	@Override public void packageNameExit(GoParserBase.packageNameContext ctx) { }

	@Override public void receiverEnter(GoParserBase.receiverContext ctx) { }
	@Override public void receiverExit(GoParserBase.receiverContext ctx) { }

	@Override public void arrayTypeEnter(GoParserBase.arrayTypeContext ctx) { }
	@Override public void arrayTypeExit(GoParserBase.arrayTypeContext ctx) { }

	@Override public void expressionListEnter(GoParserBase.expressionListContext ctx) { }
	@Override public void expressionListExit(GoParserBase.expressionListContext ctx) { }

	@Override public void tagEnter(GoParserBase.tagContext ctx) { }
	@Override public void tagExit(GoParserBase.tagContext ctx) { }

	@Override public void fallthroughStmtEnter(GoParserBase.fallthroughStmtContext ctx) { }
	@Override public void fallthroughStmtExit(GoParserBase.fallthroughStmtContext ctx) { }

	@Override public void selectorExprEnter(GoParserBase.selectorExprContext ctx) { }
	@Override public void selectorExprExit(GoParserBase.selectorExprContext ctx) { }

	@Override public void parameterListEnter(GoParserBase.parameterListContext ctx) { }
	@Override public void parameterListExit(GoParserBase.parameterListContext ctx) { }

	@Override public void receiverTypeEnter(GoParserBase.receiverTypeContext ctx) { }
	@Override public void receiverTypeExit(GoParserBase.receiverTypeContext ctx) { }

	@Override public void fieldNameEnter(GoParserBase.fieldNameContext ctx) { }
	@Override public void fieldNameExit(GoParserBase.fieldNameContext ctx) { }

	@Override public void ifStmtEnter(GoParserBase.ifStmtContext ctx) { }
	@Override public void ifStmtExit(GoParserBase.ifStmtContext ctx) { }

	@Override public void methodNameEnter(GoParserBase.methodNameContext ctx) { }
	@Override public void methodNameExit(GoParserBase.methodNameContext ctx) { }

	@Override public void signatureEnter(GoParserBase.signatureContext ctx) { }
	@Override public void signatureExit(GoParserBase.signatureContext ctx) { }

	@Override public void mapTypeEnter(GoParserBase.mapTypeContext ctx) { }
	@Override public void mapTypeExit(GoParserBase.mapTypeContext ctx) { }

	@Override public void elementEnter(GoParserBase.elementContext ctx) { }
	@Override public void elementExit(GoParserBase.elementContext ctx) { }

	@Override public void callExprEnter(GoParserBase.callExprContext ctx) { }
	@Override public void callExprExit(GoParserBase.callExprContext ctx) { }

	@Override public void typeCaseClauseEnter(GoParserBase.typeCaseClauseContext ctx) { }
	@Override public void typeCaseClauseExit(GoParserBase.typeCaseClauseContext ctx) { }

	@Override public void exprCaseClauseEnter(GoParserBase.exprCaseClauseContext ctx) { }
	@Override public void exprCaseClauseExit(GoParserBase.exprCaseClauseContext ctx) { }

	@Override public void typeSwitchGuardEnter(GoParserBase.typeSwitchGuardContext ctx) { }
	@Override public void typeSwitchGuardExit(GoParserBase.typeSwitchGuardContext ctx) { }

	@Override public void functionLiteralEnter(GoParserBase.functionLiteralContext ctx) { }
	@Override public void functionLiteralExit(GoParserBase.functionLiteralContext ctx) { }

	@Override public void orExprEnter(GoParserBase.orExprContext ctx) { }
	@Override public void orExprExit(GoParserBase.orExprContext ctx) { }

	@Override public void recvExprEnter(GoParserBase.recvExprContext ctx) { }
	@Override public void recvExprExit(GoParserBase.recvExprContext ctx) { }

	@Override public void topLevelDeclEnter(GoParserBase.topLevelDeclContext ctx) { }
	@Override public void topLevelDeclExit(GoParserBase.topLevelDeclContext ctx) { }

	@Override public void methodSpecEnter(GoParserBase.methodSpecContext ctx) { }
	@Override public void methodSpecExit(GoParserBase.methodSpecContext ctx) { }

	@Override public void constSpecEnter(GoParserBase.constSpecContext ctx) { }
	@Override public void constSpecExit(GoParserBase.constSpecContext ctx) { }

	@Override public void compositeLiteralEnter(GoParserBase.compositeLiteralContext ctx) { }
	@Override public void compositeLiteralExit(GoParserBase.compositeLiteralContext ctx) { }

	@Override public void forClauseEnter(GoParserBase.forClauseContext ctx) { }
	@Override public void forClauseExit(GoParserBase.forClauseContext ctx) { }

	@Override public void shortVarDeclEnter(GoParserBase.shortVarDeclContext ctx) { }
	@Override public void shortVarDeclExit(GoParserBase.shortVarDeclContext ctx) { }

	@Override public void gotoStmtEnter(GoParserBase.gotoStmtContext ctx) { }
	@Override public void gotoStmtExit(GoParserBase.gotoStmtContext ctx) { }

	@Override public void arrayLengthEnter(GoParserBase.arrayLengthContext ctx) { }
	@Override public void arrayLengthExit(GoParserBase.arrayLengthContext ctx) { }

	@Override public void interfaceTypeEnter(GoParserBase.interfaceTypeContext ctx) { }
	@Override public void interfaceTypeExit(GoParserBase.interfaceTypeContext ctx) { }

	@Override public void conversionEnter(GoParserBase.conversionContext ctx) { }
	@Override public void conversionExit(GoParserBase.conversionContext ctx) { }

	@Override public void blockEnter(GoParserBase.blockContext ctx) { }
	@Override public void blockExit(GoParserBase.blockContext ctx) { }

	@Override public void breakStmtEnter(GoParserBase.breakStmtContext ctx) { }
	@Override public void breakStmtExit(GoParserBase.breakStmtContext ctx) { }

	@Override public void emptyStmtEnter(GoParserBase.emptyStmtContext ctx) { }
	@Override public void emptyStmtExit(GoParserBase.emptyStmtContext ctx) { }

	@Override public void functionTypeEnter(GoParserBase.functionTypeContext ctx) { }
	@Override public void functionTypeExit(GoParserBase.functionTypeContext ctx) { }

	@Override public void baseTypeEnter(GoParserBase.baseTypeContext ctx) { }
	@Override public void baseTypeExit(GoParserBase.baseTypeContext ctx) { }

	@Override public void operandExprEnter(GoParserBase.operandExprContext ctx) { }
	@Override public void operandExprExit(GoParserBase.operandExprContext ctx) { }

	@Override public void fieldDeclEnter(GoParserBase.fieldDeclContext ctx) { }
	@Override public void fieldDeclExit(GoParserBase.fieldDeclContext ctx) { }

	@Override public void exprSwitchStmtEnter(GoParserBase.exprSwitchStmtContext ctx) { }
	@Override public void exprSwitchStmtExit(GoParserBase.exprSwitchStmtContext ctx) { }

	@Override public void goStmtEnter(GoParserBase.goStmtContext ctx) { }
	@Override public void goStmtExit(GoParserBase.goStmtContext ctx) { }

	@Override public void parameterDeclEnter(GoParserBase.parameterDeclContext ctx) { }
	@Override public void parameterDeclExit(GoParserBase.parameterDeclContext ctx) { }

	@Override public void basicLiteralEnter(GoParserBase.basicLiteralContext ctx) { }
	@Override public void basicLiteralExit(GoParserBase.basicLiteralContext ctx) { }

	@Override public void exprSwitchCaseEnter(GoParserBase.exprSwitchCaseContext ctx) { }
	@Override public void exprSwitchCaseExit(GoParserBase.exprSwitchCaseContext ctx) { }

	@Override public void typeLiteralEnter(GoParserBase.typeLiteralContext ctx) { }
	@Override public void typeLiteralExit(GoParserBase.typeLiteralContext ctx) { }

	@Override public void selectStmtEnter(GoParserBase.selectStmtContext ctx) { }
	@Override public void selectStmtExit(GoParserBase.selectStmtContext ctx) { }

	@Override public void importSpecEnter(GoParserBase.importSpecContext ctx) { }
	@Override public void importSpecExit(GoParserBase.importSpecContext ctx) { }

	@Override public void typeNameEnter(GoParserBase.typeNameContext ctx) { }
	@Override public void typeNameExit(GoParserBase.typeNameContext ctx) { }

	@Override public void literalTypeEnter(GoParserBase.literalTypeContext ctx) { }
	@Override public void literalTypeExit(GoParserBase.literalTypeContext ctx) { }

	@Override public void assignmentEnter(GoParserBase.assignmentContext ctx) { }
	@Override public void assignmentExit(GoParserBase.assignmentContext ctx) { }

	@Override public void assignOpEnter(GoParserBase.assignOpContext ctx) { }
	@Override public void assignOpExit(GoParserBase.assignOpContext ctx) { }

	@Override public void recvStmtEnter(GoParserBase.recvStmtContext ctx) { }
	@Override public void recvStmtExit(GoParserBase.recvStmtContext ctx) { }

	@Override public void typeSpecEnter(GoParserBase.typeSpecContext ctx) { }
	@Override public void typeSpecExit(GoParserBase.typeSpecContext ctx) { }

	@Override public void packageClauseEnter(GoParserBase.packageClauseContext ctx) { }
	@Override public void packageClauseExit(GoParserBase.packageClauseContext ctx) { }

	@Override public void builtinCallExprEnter(GoParserBase.builtinCallExprContext ctx) { }
	@Override public void builtinCallExprExit(GoParserBase.builtinCallExprContext ctx) { }

	@Override public void literalValueEnter(GoParserBase.literalValueContext ctx) { }
	@Override public void literalValueExit(GoParserBase.literalValueContext ctx) { }

	@Override public void indexExprEnter(GoParserBase.indexExprContext ctx) { }
	@Override public void indexExprExit(GoParserBase.indexExprContext ctx) { }

	@Override public void varSpecEnter(GoParserBase.varSpecContext ctx) { }
	@Override public void varSpecExit(GoParserBase.varSpecContext ctx) { }

	@Override public void expressionEnter(GoParserBase.expressionContext ctx) { }
	@Override public void expressionExit(GoParserBase.expressionContext ctx) { }

	@Override public void bodyEnter(GoParserBase.bodyContext ctx) { }
	@Override public void bodyExit(GoParserBase.bodyContext ctx) { }

	@Override public void commClauseEnter(GoParserBase.commClauseContext ctx) { }
	@Override public void commClauseExit(GoParserBase.commClauseContext ctx) { }

	@Override public void qualifiedIdentifierEnter(GoParserBase.qualifiedIdentifierContext ctx) { }
	@Override public void qualifiedIdentifierExit(GoParserBase.qualifiedIdentifierContext ctx) { }

	@Override public void returnStmtEnter(GoParserBase.returnStmtContext ctx) { }
	@Override public void returnStmtExit(GoParserBase.returnStmtContext ctx) { }

	@Override public void simpleStmtEnter(GoParserBase.simpleStmtContext ctx) { }
	@Override public void simpleStmtExit(GoParserBase.simpleStmtContext ctx) { }

	@Override public void typeAssertionExprEnter(GoParserBase.typeAssertionExprContext ctx) { }
	@Override public void typeAssertionExprExit(GoParserBase.typeAssertionExprContext ctx) { }

	@Override public void typeEnter(GoParserBase.typeContext ctx) { }
	@Override public void typeExit(GoParserBase.typeContext ctx) { }

	@Override public void interfaceTypeNameEnter(GoParserBase.interfaceTypeNameContext ctx) { }
	@Override public void interfaceTypeNameExit(GoParserBase.interfaceTypeNameContext ctx) { }

	@Override public void continueStmtEnter(GoParserBase.continueStmtContext ctx) { }
	@Override public void continueStmtExit(GoParserBase.continueStmtContext ctx) { }

	@Override public void valueEnter(GoParserBase.valueContext ctx) { }
	@Override public void valueExit(GoParserBase.valueContext ctx) { }

	@Override public void methodDeclEnter(GoParserBase.methodDeclContext ctx) { }
	@Override public void methodDeclExit(GoParserBase.methodDeclContext ctx) { }

	@Override public void labeledStmtEnter(GoParserBase.labeledStmtContext ctx) { }
	@Override public void labeledStmtExit(GoParserBase.labeledStmtContext ctx) { }

	@Override public void parametersEnter(GoParserBase.parametersContext ctx) { }
	@Override public void parametersExit(GoParserBase.parametersContext ctx) { }

	@Override public void deferStmtEnter(GoParserBase.deferStmtContext ctx) { }
	@Override public void deferStmtExit(GoParserBase.deferStmtContext ctx) { }

	@Override public void keyEnter(GoParserBase.keyContext ctx) { }
	@Override public void keyExit(GoParserBase.keyContext ctx) { }

	@Override public void declarationEnter(GoParserBase.declarationContext ctx) { }
	@Override public void declarationExit(GoParserBase.declarationContext ctx) { }

	@Override public void commCaseEnter(GoParserBase.commCaseContext ctx) { }
	@Override public void commCaseExit(GoParserBase.commCaseContext ctx) { }

	@Override public void builtinArgsEnter(GoParserBase.builtinArgsContext ctx) { }
	@Override public void builtinArgsExit(GoParserBase.builtinArgsContext ctx) { }

	@Override public void conditionEnter(GoParserBase.conditionContext ctx) { }
	@Override public void conditionExit(GoParserBase.conditionContext ctx) { }

	@Override public void conversionOrCallExprEnter(GoParserBase.conversionOrCallExprContext ctx) { }
	@Override public void conversionOrCallExprExit(GoParserBase.conversionOrCallExprContext ctx) { }

	@Override public void labelEnter(GoParserBase.labelContext ctx) { }
	@Override public void labelExit(GoParserBase.labelContext ctx) { }

	@Override public void elementTypeEnter(GoParserBase.elementTypeContext ctx) { }
	@Override public void elementTypeExit(GoParserBase.elementTypeContext ctx) { }

	@Override public void functionDeclEnter(GoParserBase.functionDeclContext ctx) { }
	@Override public void functionDeclExit(GoParserBase.functionDeclContext ctx) { }

	@Override public void statementEnter(GoParserBase.statementContext ctx) { }
	@Override public void statementExit(GoParserBase.statementContext ctx) { }

	@Override public void pointerTypeEnter(GoParserBase.pointerTypeContext ctx) { }
	@Override public void pointerTypeExit(GoParserBase.pointerTypeContext ctx) { }

	@Override public void addAssignOpEnter(GoParserBase.addAssignOpContext ctx) { }
	@Override public void addAssignOpExit(GoParserBase.addAssignOpContext ctx) { }

	@Override public void sourceFileEnter(GoParserBase.sourceFileContext ctx) { }
	@Override public void sourceFileExit(GoParserBase.sourceFileContext ctx) { }

	@Override public void sliceExprEnter(GoParserBase.sliceExprContext ctx) { }
	@Override public void sliceExprExit(GoParserBase.sliceExprContext ctx) { }

	@Override public void baseTypeNameEnter(GoParserBase.baseTypeNameContext ctx) { }
	@Override public void baseTypeNameExit(GoParserBase.baseTypeNameContext ctx) { }

	@Override public void methodExprEnter(GoParserBase.methodExprContext ctx) { }
	@Override public void methodExprExit(GoParserBase.methodExprContext ctx) { }

	@Override public void elementIndexEnter(GoParserBase.elementIndexContext ctx) { }
	@Override public void elementIndexExit(GoParserBase.elementIndexContext ctx) { }

	@Override public void typeListEnter(GoParserBase.typeListContext ctx) { }
	@Override public void typeListExit(GoParserBase.typeListContext ctx) { }

	@Override public void incDecStmtEnter(GoParserBase.incDecStmtContext ctx) { }
	@Override public void incDecStmtExit(GoParserBase.incDecStmtContext ctx) { }

	@Override public void builtinCallEnter(GoParserBase.builtinCallContext ctx) { }
	@Override public void builtinCallExit(GoParserBase.builtinCallContext ctx) { }

	@Override public void constDeclEnter(GoParserBase.constDeclContext ctx) { }
	@Override public void constDeclExit(GoParserBase.constDeclContext ctx) { }

	@Override public void resultEnter(GoParserBase.resultContext ctx) { }
	@Override public void resultExit(GoParserBase.resultContext ctx) { }

	@Override public void andExprEnter(GoParserBase.andExprContext ctx) { }
	@Override public void andExprExit(GoParserBase.andExprContext ctx) { }

	@Override public void structTypeEnter(GoParserBase.structTypeContext ctx) { }
	@Override public void structTypeExit(GoParserBase.structTypeContext ctx) { }

	@Override public void varDeclEnter(GoParserBase.varDeclContext ctx) { }
	@Override public void varDeclExit(GoParserBase.varDeclContext ctx) { }

	@Override public void initStmtEnter(GoParserBase.initStmtContext ctx) { }
	@Override public void initStmtExit(GoParserBase.initStmtContext ctx) { }

	@Override public void identifierListEnter(GoParserBase.identifierListContext ctx) { }
	@Override public void identifierListExit(GoParserBase.identifierListContext ctx) { }

	@Override public void sliceTypeEnter(GoParserBase.sliceTypeContext ctx) { }
	@Override public void sliceTypeExit(GoParserBase.sliceTypeContext ctx) { }

	@Override public void compareExprEnter(GoParserBase.compareExprContext ctx) { }
	@Override public void compareExprExit(GoParserBase.compareExprContext ctx) { }

	@Override public void importDeclEnter(GoParserBase.importDeclContext ctx) { }
	@Override public void importDeclExit(GoParserBase.importDeclContext ctx) { }

	@Override public void elementListEnter(GoParserBase.elementListContext ctx) { }
	@Override public void elementListExit(GoParserBase.elementListContext ctx) { }

	@Override public void keyTypeEnter(GoParserBase.keyTypeContext ctx) { }
	@Override public void keyTypeExit(GoParserBase.keyTypeContext ctx) { }

	@Override public void importPathEnter(GoParserBase.importPathContext ctx) { }
	@Override public void importPathExit(GoParserBase.importPathContext ctx) { }

	@Override public void anonymousFieldEnter(GoParserBase.anonymousFieldContext ctx) { }
	@Override public void anonymousFieldExit(GoParserBase.anonymousFieldContext ctx) { }

	@Override public void addExprEnter(GoParserBase.addExprContext ctx) { }
	@Override public void addExprExit(GoParserBase.addExprContext ctx) { }

	@Override public void expressionStmtEnter(GoParserBase.expressionStmtContext ctx) { }
	@Override public void expressionStmtExit(GoParserBase.expressionStmtContext ctx) { }

	@Override public void sendStmtEnter(GoParserBase.sendStmtContext ctx) { }
	@Override public void sendStmtExit(GoParserBase.sendStmtContext ctx) { }

	@Override public void switchStmtEnter(GoParserBase.switchStmtContext ctx) { }
	@Override public void switchStmtExit(GoParserBase.switchStmtContext ctx) { }

	@Override public void postStmtEnter(GoParserBase.postStmtContext ctx) { }
	@Override public void postStmtExit(GoParserBase.postStmtContext ctx) { }

	@Override public void forStmtEnter(GoParserBase.forStmtContext ctx) { }
	@Override public void forStmtExit(GoParserBase.forStmtContext ctx) { }

	@Override public void typeSwitchCaseEnter(GoParserBase.typeSwitchCaseContext ctx) { }
	@Override public void typeSwitchCaseExit(GoParserBase.typeSwitchCaseContext ctx) { }

	@Override public void rangeClauseEnter(GoParserBase.rangeClauseContext ctx) { }
	@Override public void rangeClauseExit(GoParserBase.rangeClauseContext ctx) { }

	@Override public void operandEnter(GoParserBase.operandContext ctx) { }
	@Override public void operandExit(GoParserBase.operandContext ctx) { }

	@Override public void argumentListEnter(GoParserBase.argumentListContext ctx) { }
	@Override public void argumentListExit(GoParserBase.argumentListContext ctx) { }

	@Override public void typeSwitchStmtEnter(GoParserBase.typeSwitchStmtContext ctx) { }
	@Override public void typeSwitchStmtExit(GoParserBase.typeSwitchStmtContext ctx) { }

	@Override public void typeDeclEnter(GoParserBase.typeDeclContext ctx) { }
	@Override public void typeDeclExit(GoParserBase.typeDeclContext ctx) { }

	@Override public void unaryExprEnter(GoParserBase.unaryExprContext ctx) { }
	@Override public void unaryExprExit(GoParserBase.unaryExprContext ctx) { }

	@Override public void channelEnter(GoParserBase.channelContext ctx) { }
	@Override public void channelExit(GoParserBase.channelContext ctx) { }

	@Override public void literalEnter(GoParserBase.literalContext ctx) { }
	@Override public void literalExit(GoParserBase.literalContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<Token> ctx) { }
	@Override public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) { }
}