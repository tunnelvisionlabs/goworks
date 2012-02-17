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

public interface GoParserBaseVisitor<T> {
	T multExprVisit(GoParserBase.multExprContext ctx);

	T channelTypeVisit(GoParserBase.channelTypeContext ctx);

	T mulAssignOpVisit(GoParserBase.mulAssignOpContext ctx);

	T packageNameVisit(GoParserBase.packageNameContext ctx);

	T receiverVisit(GoParserBase.receiverContext ctx);

	T arrayTypeVisit(GoParserBase.arrayTypeContext ctx);

	T expressionListVisit(GoParserBase.expressionListContext ctx);

	T tagVisit(GoParserBase.tagContext ctx);

	T fallthroughStmtVisit(GoParserBase.fallthroughStmtContext ctx);

	T selectorExprVisit(GoParserBase.selectorExprContext ctx);

	T parameterListVisit(GoParserBase.parameterListContext ctx);

	T receiverTypeVisit(GoParserBase.receiverTypeContext ctx);

	T fieldNameVisit(GoParserBase.fieldNameContext ctx);

	T ifStmtVisit(GoParserBase.ifStmtContext ctx);

	T methodNameVisit(GoParserBase.methodNameContext ctx);

	T signatureVisit(GoParserBase.signatureContext ctx);

	T mapTypeVisit(GoParserBase.mapTypeContext ctx);

	T elementVisit(GoParserBase.elementContext ctx);

	T callExprVisit(GoParserBase.callExprContext ctx);

	T typeCaseClauseVisit(GoParserBase.typeCaseClauseContext ctx);

	T exprCaseClauseVisit(GoParserBase.exprCaseClauseContext ctx);

	T typeSwitchGuardVisit(GoParserBase.typeSwitchGuardContext ctx);

	T functionLiteralVisit(GoParserBase.functionLiteralContext ctx);

	T orExprVisit(GoParserBase.orExprContext ctx);

	T recvExprVisit(GoParserBase.recvExprContext ctx);

	T topLevelDeclVisit(GoParserBase.topLevelDeclContext ctx);

	T methodSpecVisit(GoParserBase.methodSpecContext ctx);

	T constSpecVisit(GoParserBase.constSpecContext ctx);

	T compositeLiteralVisit(GoParserBase.compositeLiteralContext ctx);

	T forClauseVisit(GoParserBase.forClauseContext ctx);

	T shortVarDeclVisit(GoParserBase.shortVarDeclContext ctx);

	T gotoStmtVisit(GoParserBase.gotoStmtContext ctx);

	T arrayLengthVisit(GoParserBase.arrayLengthContext ctx);

	T interfaceTypeVisit(GoParserBase.interfaceTypeContext ctx);

	T conversionVisit(GoParserBase.conversionContext ctx);

	T blockVisit(GoParserBase.blockContext ctx);

	T breakStmtVisit(GoParserBase.breakStmtContext ctx);

	T emptyStmtVisit(GoParserBase.emptyStmtContext ctx);

	T functionTypeVisit(GoParserBase.functionTypeContext ctx);

	T baseTypeVisit(GoParserBase.baseTypeContext ctx);

	T operandExprVisit(GoParserBase.operandExprContext ctx);

	T fieldDeclVisit(GoParserBase.fieldDeclContext ctx);

	T exprSwitchStmtVisit(GoParserBase.exprSwitchStmtContext ctx);

	T goStmtVisit(GoParserBase.goStmtContext ctx);

	T parameterDeclVisit(GoParserBase.parameterDeclContext ctx);

	T basicLiteralVisit(GoParserBase.basicLiteralContext ctx);

	T exprSwitchCaseVisit(GoParserBase.exprSwitchCaseContext ctx);

	T typeLiteralVisit(GoParserBase.typeLiteralContext ctx);

	T selectStmtVisit(GoParserBase.selectStmtContext ctx);

	T importSpecVisit(GoParserBase.importSpecContext ctx);

	T typeNameVisit(GoParserBase.typeNameContext ctx);

	T literalTypeVisit(GoParserBase.literalTypeContext ctx);

	T assignmentVisit(GoParserBase.assignmentContext ctx);

	T assignOpVisit(GoParserBase.assignOpContext ctx);

	T recvStmtVisit(GoParserBase.recvStmtContext ctx);

	T typeSpecVisit(GoParserBase.typeSpecContext ctx);

	T packageClauseVisit(GoParserBase.packageClauseContext ctx);

	T builtinCallExprVisit(GoParserBase.builtinCallExprContext ctx);

	T literalValueVisit(GoParserBase.literalValueContext ctx);

	T indexExprVisit(GoParserBase.indexExprContext ctx);

	T varSpecVisit(GoParserBase.varSpecContext ctx);

	T bodyVisit(GoParserBase.bodyContext ctx);

	T commClauseVisit(GoParserBase.commClauseContext ctx);

	T qualifiedIdentifierVisit(GoParserBase.qualifiedIdentifierContext ctx);

	T returnStmtVisit(GoParserBase.returnStmtContext ctx);

	T simpleStmtVisit(GoParserBase.simpleStmtContext ctx);

	T typeAssertionExprVisit(GoParserBase.typeAssertionExprContext ctx);

	T typeVisit(GoParserBase.typeContext ctx);

	T interfaceTypeNameVisit(GoParserBase.interfaceTypeNameContext ctx);

	T continueStmtVisit(GoParserBase.continueStmtContext ctx);

	T valueVisit(GoParserBase.valueContext ctx);

	T methodDeclVisit(GoParserBase.methodDeclContext ctx);

	T labeledStmtVisit(GoParserBase.labeledStmtContext ctx);

	T parametersVisit(GoParserBase.parametersContext ctx);

	T deferStmtVisit(GoParserBase.deferStmtContext ctx);

	T keyVisit(GoParserBase.keyContext ctx);

	T declarationVisit(GoParserBase.declarationContext ctx);

	T commCaseVisit(GoParserBase.commCaseContext ctx);

	T builtinArgsVisit(GoParserBase.builtinArgsContext ctx);

	T conditionVisit(GoParserBase.conditionContext ctx);

	T conversionOrCallExprVisit(GoParserBase.conversionOrCallExprContext ctx);

	T labelVisit(GoParserBase.labelContext ctx);

	T elementTypeVisit(GoParserBase.elementTypeContext ctx);

	T functionDeclVisit(GoParserBase.functionDeclContext ctx);

	T statementVisit(GoParserBase.statementContext ctx);

	T pointerTypeVisit(GoParserBase.pointerTypeContext ctx);

	T addAssignOpVisit(GoParserBase.addAssignOpContext ctx);

	T sourceFileVisit(GoParserBase.sourceFileContext ctx);

	T sliceExprVisit(GoParserBase.sliceExprContext ctx);

	T baseTypeNameVisit(GoParserBase.baseTypeNameContext ctx);

	T methodExprVisit(GoParserBase.methodExprContext ctx);

	T elementIndexVisit(GoParserBase.elementIndexContext ctx);

	T typeListVisit(GoParserBase.typeListContext ctx);

	T incDecStmtVisit(GoParserBase.incDecStmtContext ctx);

	T builtinCallVisit(GoParserBase.builtinCallContext ctx);

	T constDeclVisit(GoParserBase.constDeclContext ctx);

	T resultVisit(GoParserBase.resultContext ctx);

	T andExprVisit(GoParserBase.andExprContext ctx);

	T structTypeVisit(GoParserBase.structTypeContext ctx);

	T varDeclVisit(GoParserBase.varDeclContext ctx);

	T initStmtVisit(GoParserBase.initStmtContext ctx);

	T identifierListVisit(GoParserBase.identifierListContext ctx);

	T sliceTypeVisit(GoParserBase.sliceTypeContext ctx);

	T compareExprVisit(GoParserBase.compareExprContext ctx);

	T importDeclVisit(GoParserBase.importDeclContext ctx);

	T elementListVisit(GoParserBase.elementListContext ctx);

	T keyTypeVisit(GoParserBase.keyTypeContext ctx);

	T importPathVisit(GoParserBase.importPathContext ctx);

	T anonymousFieldVisit(GoParserBase.anonymousFieldContext ctx);

	T addExprVisit(GoParserBase.addExprContext ctx);

	T expressionStmtVisit(GoParserBase.expressionStmtContext ctx);

	T sendStmtVisit(GoParserBase.sendStmtContext ctx);

	T switchStmtVisit(GoParserBase.switchStmtContext ctx);

	T postStmtVisit(GoParserBase.postStmtContext ctx);

	T forStmtVisit(GoParserBase.forStmtContext ctx);

	T typeSwitchCaseVisit(GoParserBase.typeSwitchCaseContext ctx);

	T rangeClauseVisit(GoParserBase.rangeClauseContext ctx);

	T operandVisit(GoParserBase.operandContext ctx);

	T argumentListVisit(GoParserBase.argumentListContext ctx);

	T typeSwitchStmtVisit(GoParserBase.typeSwitchStmtContext ctx);

	T typeDeclVisit(GoParserBase.typeDeclContext ctx);

	T unaryExprVisit(GoParserBase.unaryExprContext ctx);

	T channelVisit(GoParserBase.channelContext ctx);

	T literalVisit(GoParserBase.literalContext ctx);
}