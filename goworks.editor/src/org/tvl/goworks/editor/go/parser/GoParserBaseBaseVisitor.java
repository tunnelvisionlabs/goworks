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

public class GoParserBaseBaseVisitor<T> extends ParseTreeVisitor<T> implements GoParserBaseVisitor<T> {
	@Override public T multExprVisit(GoParserBase.multExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T channelTypeVisit(GoParserBase.channelTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T mulAssignOpVisit(GoParserBase.mulAssignOpContext ctx) { visitChildren(ctx); return null; }

	@Override public T packageNameVisit(GoParserBase.packageNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T receiverVisit(GoParserBase.receiverContext ctx) { visitChildren(ctx); return null; }

	@Override public T arrayTypeVisit(GoParserBase.arrayTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T expressionListVisit(GoParserBase.expressionListContext ctx) { visitChildren(ctx); return null; }

	@Override public T tagVisit(GoParserBase.tagContext ctx) { visitChildren(ctx); return null; }

	@Override public T fallthroughStmtVisit(GoParserBase.fallthroughStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T selectorExprVisit(GoParserBase.selectorExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T parameterListVisit(GoParserBase.parameterListContext ctx) { visitChildren(ctx); return null; }

	@Override public T receiverTypeVisit(GoParserBase.receiverTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T fieldNameVisit(GoParserBase.fieldNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T ifStmtVisit(GoParserBase.ifStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T methodNameVisit(GoParserBase.methodNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T signatureVisit(GoParserBase.signatureContext ctx) { visitChildren(ctx); return null; }

	@Override public T mapTypeVisit(GoParserBase.mapTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementVisit(GoParserBase.elementContext ctx) { visitChildren(ctx); return null; }

	@Override public T callExprVisit(GoParserBase.callExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeCaseClauseVisit(GoParserBase.typeCaseClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprCaseClauseVisit(GoParserBase.exprCaseClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeSwitchGuardVisit(GoParserBase.typeSwitchGuardContext ctx) { visitChildren(ctx); return null; }

	@Override public T functionLiteralVisit(GoParserBase.functionLiteralContext ctx) { visitChildren(ctx); return null; }

	@Override public T orExprVisit(GoParserBase.orExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T recvExprVisit(GoParserBase.recvExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T topLevelDeclVisit(GoParserBase.topLevelDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T methodSpecVisit(GoParserBase.methodSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T constSpecVisit(GoParserBase.constSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T compositeLiteralVisit(GoParserBase.compositeLiteralContext ctx) { visitChildren(ctx); return null; }

	@Override public T forClauseVisit(GoParserBase.forClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T shortVarDeclVisit(GoParserBase.shortVarDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T gotoStmtVisit(GoParserBase.gotoStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T arrayLengthVisit(GoParserBase.arrayLengthContext ctx) { visitChildren(ctx); return null; }

	@Override public T interfaceTypeVisit(GoParserBase.interfaceTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T conversionVisit(GoParserBase.conversionContext ctx) { visitChildren(ctx); return null; }

	@Override public T blockVisit(GoParserBase.blockContext ctx) { visitChildren(ctx); return null; }

	@Override public T breakStmtVisit(GoParserBase.breakStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T emptyStmtVisit(GoParserBase.emptyStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T functionTypeVisit(GoParserBase.functionTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T baseTypeVisit(GoParserBase.baseTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T operandExprVisit(GoParserBase.operandExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T fieldDeclVisit(GoParserBase.fieldDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprSwitchStmtVisit(GoParserBase.exprSwitchStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T goStmtVisit(GoParserBase.goStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T parameterDeclVisit(GoParserBase.parameterDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T basicLiteralVisit(GoParserBase.basicLiteralContext ctx) { visitChildren(ctx); return null; }

	@Override public T exprSwitchCaseVisit(GoParserBase.exprSwitchCaseContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeLiteralVisit(GoParserBase.typeLiteralContext ctx) { visitChildren(ctx); return null; }

	@Override public T selectStmtVisit(GoParserBase.selectStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T importSpecVisit(GoParserBase.importSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeNameVisit(GoParserBase.typeNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T literalTypeVisit(GoParserBase.literalTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T assignmentVisit(GoParserBase.assignmentContext ctx) { visitChildren(ctx); return null; }

	@Override public T assignOpVisit(GoParserBase.assignOpContext ctx) { visitChildren(ctx); return null; }

	@Override public T recvStmtVisit(GoParserBase.recvStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeSpecVisit(GoParserBase.typeSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T packageClauseVisit(GoParserBase.packageClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T builtinCallExprVisit(GoParserBase.builtinCallExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T literalValueVisit(GoParserBase.literalValueContext ctx) { visitChildren(ctx); return null; }

	@Override public T indexExprVisit(GoParserBase.indexExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T varSpecVisit(GoParserBase.varSpecContext ctx) { visitChildren(ctx); return null; }

	@Override public T bodyVisit(GoParserBase.bodyContext ctx) { visitChildren(ctx); return null; }

	@Override public T commClauseVisit(GoParserBase.commClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T qualifiedIdentifierVisit(GoParserBase.qualifiedIdentifierContext ctx) { visitChildren(ctx); return null; }

	@Override public T returnStmtVisit(GoParserBase.returnStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T simpleStmtVisit(GoParserBase.simpleStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeAssertionExprVisit(GoParserBase.typeAssertionExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeVisit(GoParserBase.typeContext ctx) { visitChildren(ctx); return null; }

	@Override public T interfaceTypeNameVisit(GoParserBase.interfaceTypeNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T continueStmtVisit(GoParserBase.continueStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T valueVisit(GoParserBase.valueContext ctx) { visitChildren(ctx); return null; }

	@Override public T methodDeclVisit(GoParserBase.methodDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T labeledStmtVisit(GoParserBase.labeledStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T parametersVisit(GoParserBase.parametersContext ctx) { visitChildren(ctx); return null; }

	@Override public T deferStmtVisit(GoParserBase.deferStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T keyVisit(GoParserBase.keyContext ctx) { visitChildren(ctx); return null; }

	@Override public T declarationVisit(GoParserBase.declarationContext ctx) { visitChildren(ctx); return null; }

	@Override public T commCaseVisit(GoParserBase.commCaseContext ctx) { visitChildren(ctx); return null; }

	@Override public T builtinArgsVisit(GoParserBase.builtinArgsContext ctx) { visitChildren(ctx); return null; }

	@Override public T conditionVisit(GoParserBase.conditionContext ctx) { visitChildren(ctx); return null; }

	@Override public T conversionOrCallExprVisit(GoParserBase.conversionOrCallExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T labelVisit(GoParserBase.labelContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementTypeVisit(GoParserBase.elementTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T functionDeclVisit(GoParserBase.functionDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T statementVisit(GoParserBase.statementContext ctx) { visitChildren(ctx); return null; }

	@Override public T pointerTypeVisit(GoParserBase.pointerTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T addAssignOpVisit(GoParserBase.addAssignOpContext ctx) { visitChildren(ctx); return null; }

	@Override public T sourceFileVisit(GoParserBase.sourceFileContext ctx) { visitChildren(ctx); return null; }

	@Override public T sliceExprVisit(GoParserBase.sliceExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T baseTypeNameVisit(GoParserBase.baseTypeNameContext ctx) { visitChildren(ctx); return null; }

	@Override public T methodExprVisit(GoParserBase.methodExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementIndexVisit(GoParserBase.elementIndexContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeListVisit(GoParserBase.typeListContext ctx) { visitChildren(ctx); return null; }

	@Override public T incDecStmtVisit(GoParserBase.incDecStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T builtinCallVisit(GoParserBase.builtinCallContext ctx) { visitChildren(ctx); return null; }

	@Override public T constDeclVisit(GoParserBase.constDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T resultVisit(GoParserBase.resultContext ctx) { visitChildren(ctx); return null; }

	@Override public T andExprVisit(GoParserBase.andExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T structTypeVisit(GoParserBase.structTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T varDeclVisit(GoParserBase.varDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T initStmtVisit(GoParserBase.initStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T identifierListVisit(GoParserBase.identifierListContext ctx) { visitChildren(ctx); return null; }

	@Override public T sliceTypeVisit(GoParserBase.sliceTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T compareExprVisit(GoParserBase.compareExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T importDeclVisit(GoParserBase.importDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T elementListVisit(GoParserBase.elementListContext ctx) { visitChildren(ctx); return null; }

	@Override public T keyTypeVisit(GoParserBase.keyTypeContext ctx) { visitChildren(ctx); return null; }

	@Override public T importPathVisit(GoParserBase.importPathContext ctx) { visitChildren(ctx); return null; }

	@Override public T anonymousFieldVisit(GoParserBase.anonymousFieldContext ctx) { visitChildren(ctx); return null; }

	@Override public T addExprVisit(GoParserBase.addExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T expressionStmtVisit(GoParserBase.expressionStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T sendStmtVisit(GoParserBase.sendStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T switchStmtVisit(GoParserBase.switchStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T postStmtVisit(GoParserBase.postStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T forStmtVisit(GoParserBase.forStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeSwitchCaseVisit(GoParserBase.typeSwitchCaseContext ctx) { visitChildren(ctx); return null; }

	@Override public T rangeClauseVisit(GoParserBase.rangeClauseContext ctx) { visitChildren(ctx); return null; }

	@Override public T operandVisit(GoParserBase.operandContext ctx) { visitChildren(ctx); return null; }

	@Override public T argumentListVisit(GoParserBase.argumentListContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeSwitchStmtVisit(GoParserBase.typeSwitchStmtContext ctx) { visitChildren(ctx); return null; }

	@Override public T typeDeclVisit(GoParserBase.typeDeclContext ctx) { visitChildren(ctx); return null; }

	@Override public T unaryExprVisit(GoParserBase.unaryExprContext ctx) { visitChildren(ctx); return null; }

	@Override public T channelVisit(GoParserBase.channelContext ctx) { visitChildren(ctx); return null; }

	@Override public T literalVisit(GoParserBase.literalContext ctx) { visitChildren(ctx); return null; }
}