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

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GoParserBaseListener extends ParseTreeListener<Token> {
	void multExprEnter(GoParserBase.multExprContext ctx);
	void multExprExit(GoParserBase.multExprContext ctx);

	void channelTypeEnter(GoParserBase.channelTypeContext ctx);
	void channelTypeExit(GoParserBase.channelTypeContext ctx);

	void mulAssignOpEnter(GoParserBase.mulAssignOpContext ctx);
	void mulAssignOpExit(GoParserBase.mulAssignOpContext ctx);

	void packageNameEnter(GoParserBase.packageNameContext ctx);
	void packageNameExit(GoParserBase.packageNameContext ctx);

	void receiverEnter(GoParserBase.receiverContext ctx);
	void receiverExit(GoParserBase.receiverContext ctx);

	void arrayTypeEnter(GoParserBase.arrayTypeContext ctx);
	void arrayTypeExit(GoParserBase.arrayTypeContext ctx);

	void expressionListEnter(GoParserBase.expressionListContext ctx);
	void expressionListExit(GoParserBase.expressionListContext ctx);

	void tagEnter(GoParserBase.tagContext ctx);
	void tagExit(GoParserBase.tagContext ctx);

	void fallthroughStmtEnter(GoParserBase.fallthroughStmtContext ctx);
	void fallthroughStmtExit(GoParserBase.fallthroughStmtContext ctx);

	void selectorExprEnter(GoParserBase.selectorExprContext ctx);
	void selectorExprExit(GoParserBase.selectorExprContext ctx);

	void parameterListEnter(GoParserBase.parameterListContext ctx);
	void parameterListExit(GoParserBase.parameterListContext ctx);

	void receiverTypeEnter(GoParserBase.receiverTypeContext ctx);
	void receiverTypeExit(GoParserBase.receiverTypeContext ctx);

	void fieldNameEnter(GoParserBase.fieldNameContext ctx);
	void fieldNameExit(GoParserBase.fieldNameContext ctx);

	void ifStmtEnter(GoParserBase.ifStmtContext ctx);
	void ifStmtExit(GoParserBase.ifStmtContext ctx);

	void methodNameEnter(GoParserBase.methodNameContext ctx);
	void methodNameExit(GoParserBase.methodNameContext ctx);

	void signatureEnter(GoParserBase.signatureContext ctx);
	void signatureExit(GoParserBase.signatureContext ctx);

	void mapTypeEnter(GoParserBase.mapTypeContext ctx);
	void mapTypeExit(GoParserBase.mapTypeContext ctx);

	void elementEnter(GoParserBase.elementContext ctx);
	void elementExit(GoParserBase.elementContext ctx);

	void callExprEnter(GoParserBase.callExprContext ctx);
	void callExprExit(GoParserBase.callExprContext ctx);

	void typeCaseClauseEnter(GoParserBase.typeCaseClauseContext ctx);
	void typeCaseClauseExit(GoParserBase.typeCaseClauseContext ctx);

	void exprCaseClauseEnter(GoParserBase.exprCaseClauseContext ctx);
	void exprCaseClauseExit(GoParserBase.exprCaseClauseContext ctx);

	void typeSwitchGuardEnter(GoParserBase.typeSwitchGuardContext ctx);
	void typeSwitchGuardExit(GoParserBase.typeSwitchGuardContext ctx);

	void functionLiteralEnter(GoParserBase.functionLiteralContext ctx);
	void functionLiteralExit(GoParserBase.functionLiteralContext ctx);

	void orExprEnter(GoParserBase.orExprContext ctx);
	void orExprExit(GoParserBase.orExprContext ctx);

	void recvExprEnter(GoParserBase.recvExprContext ctx);
	void recvExprExit(GoParserBase.recvExprContext ctx);

	void topLevelDeclEnter(GoParserBase.topLevelDeclContext ctx);
	void topLevelDeclExit(GoParserBase.topLevelDeclContext ctx);

	void methodSpecEnter(GoParserBase.methodSpecContext ctx);
	void methodSpecExit(GoParserBase.methodSpecContext ctx);

	void constSpecEnter(GoParserBase.constSpecContext ctx);
	void constSpecExit(GoParserBase.constSpecContext ctx);

	void compositeLiteralEnter(GoParserBase.compositeLiteralContext ctx);
	void compositeLiteralExit(GoParserBase.compositeLiteralContext ctx);

	void forClauseEnter(GoParserBase.forClauseContext ctx);
	void forClauseExit(GoParserBase.forClauseContext ctx);

	void shortVarDeclEnter(GoParserBase.shortVarDeclContext ctx);
	void shortVarDeclExit(GoParserBase.shortVarDeclContext ctx);

	void gotoStmtEnter(GoParserBase.gotoStmtContext ctx);
	void gotoStmtExit(GoParserBase.gotoStmtContext ctx);

	void arrayLengthEnter(GoParserBase.arrayLengthContext ctx);
	void arrayLengthExit(GoParserBase.arrayLengthContext ctx);

	void interfaceTypeEnter(GoParserBase.interfaceTypeContext ctx);
	void interfaceTypeExit(GoParserBase.interfaceTypeContext ctx);

	void conversionEnter(GoParserBase.conversionContext ctx);
	void conversionExit(GoParserBase.conversionContext ctx);

	void blockEnter(GoParserBase.blockContext ctx);
	void blockExit(GoParserBase.blockContext ctx);

	void breakStmtEnter(GoParserBase.breakStmtContext ctx);
	void breakStmtExit(GoParserBase.breakStmtContext ctx);

	void emptyStmtEnter(GoParserBase.emptyStmtContext ctx);
	void emptyStmtExit(GoParserBase.emptyStmtContext ctx);

	void functionTypeEnter(GoParserBase.functionTypeContext ctx);
	void functionTypeExit(GoParserBase.functionTypeContext ctx);

	void baseTypeEnter(GoParserBase.baseTypeContext ctx);
	void baseTypeExit(GoParserBase.baseTypeContext ctx);

	void operandExprEnter(GoParserBase.operandExprContext ctx);
	void operandExprExit(GoParserBase.operandExprContext ctx);

	void fieldDeclEnter(GoParserBase.fieldDeclContext ctx);
	void fieldDeclExit(GoParserBase.fieldDeclContext ctx);

	void exprSwitchStmtEnter(GoParserBase.exprSwitchStmtContext ctx);
	void exprSwitchStmtExit(GoParserBase.exprSwitchStmtContext ctx);

	void goStmtEnter(GoParserBase.goStmtContext ctx);
	void goStmtExit(GoParserBase.goStmtContext ctx);

	void parameterDeclEnter(GoParserBase.parameterDeclContext ctx);
	void parameterDeclExit(GoParserBase.parameterDeclContext ctx);

	void basicLiteralEnter(GoParserBase.basicLiteralContext ctx);
	void basicLiteralExit(GoParserBase.basicLiteralContext ctx);

	void exprSwitchCaseEnter(GoParserBase.exprSwitchCaseContext ctx);
	void exprSwitchCaseExit(GoParserBase.exprSwitchCaseContext ctx);

	void typeLiteralEnter(GoParserBase.typeLiteralContext ctx);
	void typeLiteralExit(GoParserBase.typeLiteralContext ctx);

	void selectStmtEnter(GoParserBase.selectStmtContext ctx);
	void selectStmtExit(GoParserBase.selectStmtContext ctx);

	void importSpecEnter(GoParserBase.importSpecContext ctx);
	void importSpecExit(GoParserBase.importSpecContext ctx);

	void typeNameEnter(GoParserBase.typeNameContext ctx);
	void typeNameExit(GoParserBase.typeNameContext ctx);

	void literalTypeEnter(GoParserBase.literalTypeContext ctx);
	void literalTypeExit(GoParserBase.literalTypeContext ctx);

	void assignmentEnter(GoParserBase.assignmentContext ctx);
	void assignmentExit(GoParserBase.assignmentContext ctx);

	void assignOpEnter(GoParserBase.assignOpContext ctx);
	void assignOpExit(GoParserBase.assignOpContext ctx);

	void recvStmtEnter(GoParserBase.recvStmtContext ctx);
	void recvStmtExit(GoParserBase.recvStmtContext ctx);

	void typeSpecEnter(GoParserBase.typeSpecContext ctx);
	void typeSpecExit(GoParserBase.typeSpecContext ctx);

	void packageClauseEnter(GoParserBase.packageClauseContext ctx);
	void packageClauseExit(GoParserBase.packageClauseContext ctx);

	void builtinCallExprEnter(GoParserBase.builtinCallExprContext ctx);
	void builtinCallExprExit(GoParserBase.builtinCallExprContext ctx);

	void literalValueEnter(GoParserBase.literalValueContext ctx);
	void literalValueExit(GoParserBase.literalValueContext ctx);

	void indexExprEnter(GoParserBase.indexExprContext ctx);
	void indexExprExit(GoParserBase.indexExprContext ctx);

	void varSpecEnter(GoParserBase.varSpecContext ctx);
	void varSpecExit(GoParserBase.varSpecContext ctx);

	void expressionEnter(GoParserBase.expressionContext ctx);
	void expressionExit(GoParserBase.expressionContext ctx);

	void bodyEnter(GoParserBase.bodyContext ctx);
	void bodyExit(GoParserBase.bodyContext ctx);

	void commClauseEnter(GoParserBase.commClauseContext ctx);
	void commClauseExit(GoParserBase.commClauseContext ctx);

	void qualifiedIdentifierEnter(GoParserBase.qualifiedIdentifierContext ctx);
	void qualifiedIdentifierExit(GoParserBase.qualifiedIdentifierContext ctx);

	void returnStmtEnter(GoParserBase.returnStmtContext ctx);
	void returnStmtExit(GoParserBase.returnStmtContext ctx);

	void simpleStmtEnter(GoParserBase.simpleStmtContext ctx);
	void simpleStmtExit(GoParserBase.simpleStmtContext ctx);

	void typeAssertionExprEnter(GoParserBase.typeAssertionExprContext ctx);
	void typeAssertionExprExit(GoParserBase.typeAssertionExprContext ctx);

	void typeEnter(GoParserBase.typeContext ctx);
	void typeExit(GoParserBase.typeContext ctx);

	void interfaceTypeNameEnter(GoParserBase.interfaceTypeNameContext ctx);
	void interfaceTypeNameExit(GoParserBase.interfaceTypeNameContext ctx);

	void continueStmtEnter(GoParserBase.continueStmtContext ctx);
	void continueStmtExit(GoParserBase.continueStmtContext ctx);

	void valueEnter(GoParserBase.valueContext ctx);
	void valueExit(GoParserBase.valueContext ctx);

	void methodDeclEnter(GoParserBase.methodDeclContext ctx);
	void methodDeclExit(GoParserBase.methodDeclContext ctx);

	void labeledStmtEnter(GoParserBase.labeledStmtContext ctx);
	void labeledStmtExit(GoParserBase.labeledStmtContext ctx);

	void parametersEnter(GoParserBase.parametersContext ctx);
	void parametersExit(GoParserBase.parametersContext ctx);

	void deferStmtEnter(GoParserBase.deferStmtContext ctx);
	void deferStmtExit(GoParserBase.deferStmtContext ctx);

	void keyEnter(GoParserBase.keyContext ctx);
	void keyExit(GoParserBase.keyContext ctx);

	void declarationEnter(GoParserBase.declarationContext ctx);
	void declarationExit(GoParserBase.declarationContext ctx);

	void commCaseEnter(GoParserBase.commCaseContext ctx);
	void commCaseExit(GoParserBase.commCaseContext ctx);

	void builtinArgsEnter(GoParserBase.builtinArgsContext ctx);
	void builtinArgsExit(GoParserBase.builtinArgsContext ctx);

	void conditionEnter(GoParserBase.conditionContext ctx);
	void conditionExit(GoParserBase.conditionContext ctx);

	void conversionOrCallExprEnter(GoParserBase.conversionOrCallExprContext ctx);
	void conversionOrCallExprExit(GoParserBase.conversionOrCallExprContext ctx);

	void labelEnter(GoParserBase.labelContext ctx);
	void labelExit(GoParserBase.labelContext ctx);

	void elementTypeEnter(GoParserBase.elementTypeContext ctx);
	void elementTypeExit(GoParserBase.elementTypeContext ctx);

	void functionDeclEnter(GoParserBase.functionDeclContext ctx);
	void functionDeclExit(GoParserBase.functionDeclContext ctx);

	void statementEnter(GoParserBase.statementContext ctx);
	void statementExit(GoParserBase.statementContext ctx);

	void pointerTypeEnter(GoParserBase.pointerTypeContext ctx);
	void pointerTypeExit(GoParserBase.pointerTypeContext ctx);

	void addAssignOpEnter(GoParserBase.addAssignOpContext ctx);
	void addAssignOpExit(GoParserBase.addAssignOpContext ctx);

	void sourceFileEnter(GoParserBase.sourceFileContext ctx);
	void sourceFileExit(GoParserBase.sourceFileContext ctx);

	void sliceExprEnter(GoParserBase.sliceExprContext ctx);
	void sliceExprExit(GoParserBase.sliceExprContext ctx);

	void baseTypeNameEnter(GoParserBase.baseTypeNameContext ctx);
	void baseTypeNameExit(GoParserBase.baseTypeNameContext ctx);

	void methodExprEnter(GoParserBase.methodExprContext ctx);
	void methodExprExit(GoParserBase.methodExprContext ctx);

	void elementIndexEnter(GoParserBase.elementIndexContext ctx);
	void elementIndexExit(GoParserBase.elementIndexContext ctx);

	void typeListEnter(GoParserBase.typeListContext ctx);
	void typeListExit(GoParserBase.typeListContext ctx);

	void incDecStmtEnter(GoParserBase.incDecStmtContext ctx);
	void incDecStmtExit(GoParserBase.incDecStmtContext ctx);

	void builtinCallEnter(GoParserBase.builtinCallContext ctx);
	void builtinCallExit(GoParserBase.builtinCallContext ctx);

	void constDeclEnter(GoParserBase.constDeclContext ctx);
	void constDeclExit(GoParserBase.constDeclContext ctx);

	void resultEnter(GoParserBase.resultContext ctx);
	void resultExit(GoParserBase.resultContext ctx);

	void andExprEnter(GoParserBase.andExprContext ctx);
	void andExprExit(GoParserBase.andExprContext ctx);

	void structTypeEnter(GoParserBase.structTypeContext ctx);
	void structTypeExit(GoParserBase.structTypeContext ctx);

	void varDeclEnter(GoParserBase.varDeclContext ctx);
	void varDeclExit(GoParserBase.varDeclContext ctx);

	void initStmtEnter(GoParserBase.initStmtContext ctx);
	void initStmtExit(GoParserBase.initStmtContext ctx);

	void identifierListEnter(GoParserBase.identifierListContext ctx);
	void identifierListExit(GoParserBase.identifierListContext ctx);

	void sliceTypeEnter(GoParserBase.sliceTypeContext ctx);
	void sliceTypeExit(GoParserBase.sliceTypeContext ctx);

	void compareExprEnter(GoParserBase.compareExprContext ctx);
	void compareExprExit(GoParserBase.compareExprContext ctx);

	void importDeclEnter(GoParserBase.importDeclContext ctx);
	void importDeclExit(GoParserBase.importDeclContext ctx);

	void elementListEnter(GoParserBase.elementListContext ctx);
	void elementListExit(GoParserBase.elementListContext ctx);

	void keyTypeEnter(GoParserBase.keyTypeContext ctx);
	void keyTypeExit(GoParserBase.keyTypeContext ctx);

	void importPathEnter(GoParserBase.importPathContext ctx);
	void importPathExit(GoParserBase.importPathContext ctx);

	void anonymousFieldEnter(GoParserBase.anonymousFieldContext ctx);
	void anonymousFieldExit(GoParserBase.anonymousFieldContext ctx);

	void addExprEnter(GoParserBase.addExprContext ctx);
	void addExprExit(GoParserBase.addExprContext ctx);

	void expressionStmtEnter(GoParserBase.expressionStmtContext ctx);
	void expressionStmtExit(GoParserBase.expressionStmtContext ctx);

	void sendStmtEnter(GoParserBase.sendStmtContext ctx);
	void sendStmtExit(GoParserBase.sendStmtContext ctx);

	void switchStmtEnter(GoParserBase.switchStmtContext ctx);
	void switchStmtExit(GoParserBase.switchStmtContext ctx);

	void postStmtEnter(GoParserBase.postStmtContext ctx);
	void postStmtExit(GoParserBase.postStmtContext ctx);

	void forStmtEnter(GoParserBase.forStmtContext ctx);
	void forStmtExit(GoParserBase.forStmtContext ctx);

	void typeSwitchCaseEnter(GoParserBase.typeSwitchCaseContext ctx);
	void typeSwitchCaseExit(GoParserBase.typeSwitchCaseContext ctx);

	void rangeClauseEnter(GoParserBase.rangeClauseContext ctx);
	void rangeClauseExit(GoParserBase.rangeClauseContext ctx);

	void operandEnter(GoParserBase.operandContext ctx);
	void operandExit(GoParserBase.operandContext ctx);

	void argumentListEnter(GoParserBase.argumentListContext ctx);
	void argumentListExit(GoParserBase.argumentListContext ctx);

	void typeSwitchStmtEnter(GoParserBase.typeSwitchStmtContext ctx);
	void typeSwitchStmtExit(GoParserBase.typeSwitchStmtContext ctx);

	void typeDeclEnter(GoParserBase.typeDeclContext ctx);
	void typeDeclExit(GoParserBase.typeDeclContext ctx);

	void unaryExprEnter(GoParserBase.unaryExprContext ctx);
	void unaryExprExit(GoParserBase.unaryExprContext ctx);

	void channelEnter(GoParserBase.channelContext ctx);
	void channelExit(GoParserBase.channelContext ctx);

	void literalEnter(GoParserBase.literalContext ctx);
	void literalExit(GoParserBase.literalContext ctx);
}