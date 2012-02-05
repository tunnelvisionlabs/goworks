/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.parser.GoParserBase.addAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.addExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.andExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.anonymousFieldContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.argumentListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayLengthContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.arrayTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.assignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.assignmentContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.baseTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.basicLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.blockContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.bodyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.breakStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinArgsContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.callExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.channelTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.commCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.commClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compareExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.compositeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conditionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.constSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.continueStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.declarationContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.deferStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementIndexContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.elementTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.emptyStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.exprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.expressionStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fallthroughStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.fieldNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.forClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.forStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.functionTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.goStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.gotoStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.identifierListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ifStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importPathContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.importSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.incDecStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.indexExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.initStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.interfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.keyContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.keyTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labelContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.labeledStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.literalValueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mapTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.methodSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.mulAssignOpContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.multExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.operandContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.orExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parameterListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.parametersContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.pointerTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.postStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.qualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.rangeClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.receiverTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.recvExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.recvStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.resultContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.returnStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.selectorExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sendStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.shortVarDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.signatureContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.simpleStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sliceTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.statementContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.structTypeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.switchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.tagContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.topLevelDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeListContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeLiteralContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeNameContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.typeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.unaryExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.valueContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.varSpecContext;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class SemanticAnalyzerListener implements GoParserBaseListener {

    @Override
    public void enterRule(multExprContext ctx) {
    }

    @Override
    public void exitRule(multExprContext ctx) {
    }

    @Override
    public void enterRule(channelTypeContext ctx) {
    }

    @Override
    public void exitRule(channelTypeContext ctx) {
    }

    @Override
    public void enterRule(mulAssignOpContext ctx) {
    }

    @Override
    public void exitRule(mulAssignOpContext ctx) {
    }

    @Override
    public void enterRule(packageNameContext ctx) {
    }

    @Override
    public void exitRule(packageNameContext ctx) {
    }

    @Override
    public void enterRule(receiverContext ctx) {
    }

    @Override
    public void exitRule(receiverContext ctx) {
    }

    @Override
    public void enterRule(arrayTypeContext ctx) {
    }

    @Override
    public void exitRule(arrayTypeContext ctx) {
    }

    @Override
    public void enterRule(expressionListContext ctx) {
    }

    @Override
    public void exitRule(expressionListContext ctx) {
    }

    @Override
    public void enterRule(tagContext ctx) {
    }

    @Override
    public void exitRule(tagContext ctx) {
    }

    @Override
    public void enterRule(fallthroughStmtContext ctx) {
    }

    @Override
    public void exitRule(fallthroughStmtContext ctx) {
    }

    @Override
    public void enterRule(selectorExprContext ctx) {
    }

    @Override
    public void exitRule(selectorExprContext ctx) {
    }

    @Override
    public void enterRule(parameterListContext ctx) {
    }

    @Override
    public void exitRule(parameterListContext ctx) {
    }

    @Override
    public void enterRule(receiverTypeContext ctx) {
    }

    @Override
    public void exitRule(receiverTypeContext ctx) {
    }

    @Override
    public void enterRule(fieldNameContext ctx) {
    }

    @Override
    public void exitRule(fieldNameContext ctx) {
    }

    @Override
    public void enterRule(ifStmtContext ctx) {
    }

    @Override
    public void exitRule(ifStmtContext ctx) {
    }

    @Override
    public void enterRule(methodNameContext ctx) {
    }

    @Override
    public void exitRule(methodNameContext ctx) {
    }

    @Override
    public void enterRule(signatureContext ctx) {
    }

    @Override
    public void exitRule(signatureContext ctx) {
    }

    @Override
    public void enterRule(mapTypeContext ctx) {
    }

    @Override
    public void exitRule(mapTypeContext ctx) {
    }

    @Override
    public void enterRule(elementContext ctx) {
    }

    @Override
    public void exitRule(elementContext ctx) {
    }

    @Override
    public void enterRule(callExprContext ctx) {
    }

    @Override
    public void exitRule(callExprContext ctx) {
    }

    @Override
    public void enterRule(typeCaseClauseContext ctx) {
    }

    @Override
    public void exitRule(typeCaseClauseContext ctx) {
    }

    @Override
    public void enterRule(exprCaseClauseContext ctx) {
    }

    @Override
    public void exitRule(exprCaseClauseContext ctx) {
    }

    @Override
    public void enterRule(typeSwitchGuardContext ctx) {
    }

    @Override
    public void exitRule(typeSwitchGuardContext ctx) {
    }

    @Override
    public void enterRule(functionLiteralContext ctx) {
    }

    @Override
    public void exitRule(functionLiteralContext ctx) {
    }

    @Override
    public void enterRule(orExprContext ctx) {
    }

    @Override
    public void exitRule(orExprContext ctx) {
    }

    @Override
    public void enterRule(recvExprContext ctx) {
    }

    @Override
    public void exitRule(recvExprContext ctx) {
    }

    @Override
    public void enterRule(topLevelDeclContext ctx) {
    }

    @Override
    public void exitRule(topLevelDeclContext ctx) {
    }

    @Override
    public void enterRule(methodSpecContext ctx) {
    }

    @Override
    public void exitRule(methodSpecContext ctx) {
    }

    @Override
    public void enterRule(constSpecContext ctx) {
    }

    @Override
    public void exitRule(constSpecContext ctx) {
    }

    @Override
    public void enterRule(compositeLiteralContext ctx) {
    }

    @Override
    public void exitRule(compositeLiteralContext ctx) {
    }

    @Override
    public void enterRule(forClauseContext ctx) {
    }

    @Override
    public void exitRule(forClauseContext ctx) {
    }

    @Override
    public void enterRule(shortVarDeclContext ctx) {
    }

    @Override
    public void exitRule(shortVarDeclContext ctx) {
    }

    @Override
    public void enterRule(gotoStmtContext ctx) {
    }

    @Override
    public void exitRule(gotoStmtContext ctx) {
    }

    @Override
    public void enterRule(arrayLengthContext ctx) {
    }

    @Override
    public void exitRule(arrayLengthContext ctx) {
    }

    @Override
    public void enterRule(interfaceTypeContext ctx) {
    }

    @Override
    public void exitRule(interfaceTypeContext ctx) {
    }

    @Override
    public void enterRule(conversionContext ctx) {
    }

    @Override
    public void exitRule(conversionContext ctx) {
    }

    @Override
    public void enterRule(blockContext ctx) {
    }

    @Override
    public void exitRule(blockContext ctx) {
    }

    @Override
    public void enterRule(breakStmtContext ctx) {
    }

    @Override
    public void exitRule(breakStmtContext ctx) {
    }

    @Override
    public void enterRule(emptyStmtContext ctx) {
    }

    @Override
    public void exitRule(emptyStmtContext ctx) {
    }

    @Override
    public void enterRule(functionTypeContext ctx) {
    }

    @Override
    public void exitRule(functionTypeContext ctx) {
    }

    @Override
    public void enterRule(baseTypeContext ctx) {
    }

    @Override
    public void exitRule(baseTypeContext ctx) {
    }

    @Override
    public void enterRule(fieldDeclContext ctx) {
    }

    @Override
    public void exitRule(fieldDeclContext ctx) {
    }

    @Override
    public void enterRule(exprSwitchStmtContext ctx) {
    }

    @Override
    public void exitRule(exprSwitchStmtContext ctx) {
    }

    @Override
    public void enterRule(goStmtContext ctx) {
    }

    @Override
    public void exitRule(goStmtContext ctx) {
    }

    @Override
    public void enterRule(parameterDeclContext ctx) {
    }

    @Override
    public void exitRule(parameterDeclContext ctx) {
    }

    @Override
    public void enterRule(basicLiteralContext ctx) {
    }

    @Override
    public void exitRule(basicLiteralContext ctx) {
    }

    @Override
    public void enterRule(exprSwitchCaseContext ctx) {
    }

    @Override
    public void exitRule(exprSwitchCaseContext ctx) {
    }

    @Override
    public void enterRule(typeLiteralContext ctx) {
    }

    @Override
    public void exitRule(typeLiteralContext ctx) {
    }

    @Override
    public void enterRule(selectStmtContext ctx) {
    }

    @Override
    public void exitRule(selectStmtContext ctx) {
    }

    @Override
    public void enterRule(importSpecContext ctx) {
    }

    @Override
    public void exitRule(importSpecContext ctx) {
    }

    @Override
    public void enterRule(typeNameContext ctx) {
    }

    @Override
    public void exitRule(typeNameContext ctx) {
    }

    @Override
    public void enterRule(literalTypeContext ctx) {
    }

    @Override
    public void exitRule(literalTypeContext ctx) {
    }

    @Override
    public void enterRule(assignmentContext ctx) {
    }

    @Override
    public void exitRule(assignmentContext ctx) {
    }

    @Override
    public void enterRule(assignOpContext ctx) {
    }

    @Override
    public void exitRule(assignOpContext ctx) {
    }

    @Override
    public void enterRule(recvStmtContext ctx) {
    }

    @Override
    public void exitRule(recvStmtContext ctx) {
    }

    @Override
    public void enterRule(typeSpecContext ctx) {
    }

    @Override
    public void exitRule(typeSpecContext ctx) {
    }

    @Override
    public void enterRule(packageClauseContext ctx) {
    }

    @Override
    public void exitRule(packageClauseContext ctx) {
    }

    @Override
    public void enterRule(literalValueContext ctx) {
    }

    @Override
    public void exitRule(literalValueContext ctx) {
    }

    @Override
    public void enterRule(indexExprContext ctx) {
    }

    @Override
    public void exitRule(indexExprContext ctx) {
    }

    @Override
    public void enterRule(varSpecContext ctx) {
    }

    @Override
    public void exitRule(varSpecContext ctx) {
    }

    @Override
    public void enterRule(expressionContext ctx) {
    }

    @Override
    public void exitRule(expressionContext ctx) {
    }

    @Override
    public void enterRule(bodyContext ctx) {
    }

    @Override
    public void exitRule(bodyContext ctx) {
    }

    @Override
    public void enterRule(commClauseContext ctx) {
    }

    @Override
    public void exitRule(commClauseContext ctx) {
    }

    @Override
    public void enterRule(qualifiedIdentifierContext ctx) {
    }

    @Override
    public void exitRule(qualifiedIdentifierContext ctx) {
    }

    @Override
    public void enterRule(returnStmtContext ctx) {
    }

    @Override
    public void exitRule(returnStmtContext ctx) {
    }

    @Override
    public void enterRule(simpleStmtContext ctx) {
    }

    @Override
    public void exitRule(simpleStmtContext ctx) {
    }

    @Override
    public void enterRule(typeAssertionExprContext ctx) {
    }

    @Override
    public void exitRule(typeAssertionExprContext ctx) {
    }

    @Override
    public void enterRule(typeContext ctx) {
    }

    @Override
    public void exitRule(typeContext ctx) {
    }

    @Override
    public void enterRule(interfaceTypeNameContext ctx) {
    }

    @Override
    public void exitRule(interfaceTypeNameContext ctx) {
    }

    @Override
    public void enterRule(continueStmtContext ctx) {
    }

    @Override
    public void exitRule(continueStmtContext ctx) {
    }

    @Override
    public void enterRule(valueContext ctx) {
    }

    @Override
    public void exitRule(valueContext ctx) {
    }

    @Override
    public void enterRule(methodDeclContext ctx) {
    }

    @Override
    public void exitRule(methodDeclContext ctx) {
    }

    @Override
    public void enterRule(labeledStmtContext ctx) {
    }

    @Override
    public void exitRule(labeledStmtContext ctx) {
    }

    @Override
    public void enterRule(parametersContext ctx) {
    }

    @Override
    public void exitRule(parametersContext ctx) {
    }

    @Override
    public void enterRule(deferStmtContext ctx) {
    }

    @Override
    public void exitRule(deferStmtContext ctx) {
    }

    @Override
    public void enterRule(keyContext ctx) {
    }

    @Override
    public void exitRule(keyContext ctx) {
    }

    @Override
    public void enterRule(declarationContext ctx) {
    }

    @Override
    public void exitRule(declarationContext ctx) {
    }

    @Override
    public void enterRule(commCaseContext ctx) {
    }

    @Override
    public void exitRule(commCaseContext ctx) {
    }

    @Override
    public void enterRule(builtinArgsContext ctx) {
    }

    @Override
    public void exitRule(builtinArgsContext ctx) {
    }

    @Override
    public void enterRule(conditionContext ctx) {
    }

    @Override
    public void exitRule(conditionContext ctx) {
    }

    @Override
    public void enterRule(conversionOrCallExprContext ctx) {
    }

    @Override
    public void exitRule(conversionOrCallExprContext ctx) {
    }

    @Override
    public void enterRule(labelContext ctx) {
    }

    @Override
    public void exitRule(labelContext ctx) {
    }

    @Override
    public void enterRule(elementTypeContext ctx) {
    }

    @Override
    public void exitRule(elementTypeContext ctx) {
    }

    @Override
    public void enterRule(functionDeclContext ctx) {
    }

    @Override
    public void exitRule(functionDeclContext ctx) {
    }

    @Override
    public void enterRule(statementContext ctx) {
    }

    @Override
    public void exitRule(statementContext ctx) {
    }

    @Override
    public void enterRule(pointerTypeContext ctx) {
    }

    @Override
    public void exitRule(pointerTypeContext ctx) {
    }

    @Override
    public void enterRule(addAssignOpContext ctx) {
    }

    @Override
    public void exitRule(addAssignOpContext ctx) {
    }

    @Override
    public void enterRule(sourceFileContext ctx) {
    }

    @Override
    public void exitRule(sourceFileContext ctx) {
    }

    @Override
    public void enterRule(sliceExprContext ctx) {
    }

    @Override
    public void exitRule(sliceExprContext ctx) {
    }

    @Override
    public void enterRule(baseTypeNameContext ctx) {
    }

    @Override
    public void exitRule(baseTypeNameContext ctx) {
    }

    @Override
    public void enterRule(methodExprContext ctx) {
    }

    @Override
    public void exitRule(methodExprContext ctx) {
    }

    @Override
    public void enterRule(elementIndexContext ctx) {
    }

    @Override
    public void exitRule(elementIndexContext ctx) {
    }

    @Override
    public void enterRule(typeListContext ctx) {
    }

    @Override
    public void exitRule(typeListContext ctx) {
    }

    @Override
    public void enterRule(incDecStmtContext ctx) {
    }

    @Override
    public void exitRule(incDecStmtContext ctx) {
    }

    @Override
    public void enterRule(builtinCallContext ctx) {
    }

    @Override
    public void exitRule(builtinCallContext ctx) {
    }

    @Override
    public void enterRule(constDeclContext ctx) {
    }

    @Override
    public void exitRule(constDeclContext ctx) {
    }

    @Override
    public void enterRule(resultContext ctx) {
    }

    @Override
    public void exitRule(resultContext ctx) {
    }

    @Override
    public void enterRule(andExprContext ctx) {
    }

    @Override
    public void exitRule(andExprContext ctx) {
    }

    @Override
    public void enterRule(structTypeContext ctx) {
    }

    @Override
    public void exitRule(structTypeContext ctx) {
    }

    @Override
    public void enterRule(varDeclContext ctx) {
    }

    @Override
    public void exitRule(varDeclContext ctx) {
    }

    @Override
    public void enterRule(initStmtContext ctx) {
    }

    @Override
    public void exitRule(initStmtContext ctx) {
    }

    @Override
    public void enterRule(identifierListContext ctx) {
    }

    @Override
    public void exitRule(identifierListContext ctx) {
    }

    @Override
    public void enterRule(sliceTypeContext ctx) {
    }

    @Override
    public void exitRule(sliceTypeContext ctx) {
    }

    @Override
    public void enterRule(compareExprContext ctx) {
    }

    @Override
    public void exitRule(compareExprContext ctx) {
    }

    @Override
    public void enterRule(importDeclContext ctx) {
    }

    @Override
    public void exitRule(importDeclContext ctx) {
    }

    @Override
    public void enterRule(elementListContext ctx) {
    }

    @Override
    public void exitRule(elementListContext ctx) {
    }

    @Override
    public void enterRule(keyTypeContext ctx) {
    }

    @Override
    public void exitRule(keyTypeContext ctx) {
    }

    @Override
    public void enterRule(importPathContext ctx) {
    }

    @Override
    public void exitRule(importPathContext ctx) {
    }

    @Override
    public void enterRule(anonymousFieldContext ctx) {
    }

    @Override
    public void exitRule(anonymousFieldContext ctx) {
    }

    @Override
    public void enterRule(addExprContext ctx) {
    }

    @Override
    public void exitRule(addExprContext ctx) {
    }

    @Override
    public void enterRule(expressionStmtContext ctx) {
    }

    @Override
    public void exitRule(expressionStmtContext ctx) {
    }

    @Override
    public void enterRule(sendStmtContext ctx) {
    }

    @Override
    public void exitRule(sendStmtContext ctx) {
    }

    @Override
    public void enterRule(switchStmtContext ctx) {
    }

    @Override
    public void exitRule(switchStmtContext ctx) {
    }

    @Override
    public void enterRule(postStmtContext ctx) {
    }

    @Override
    public void exitRule(postStmtContext ctx) {
    }

    @Override
    public void enterRule(forStmtContext ctx) {
    }

    @Override
    public void exitRule(forStmtContext ctx) {
    }

    @Override
    public void enterRule(typeSwitchCaseContext ctx) {
    }

    @Override
    public void exitRule(typeSwitchCaseContext ctx) {
    }

    @Override
    public void enterRule(rangeClauseContext ctx) {
    }

    @Override
    public void exitRule(rangeClauseContext ctx) {
    }

    @Override
    public void enterRule(operandContext ctx) {
    }

    @Override
    public void exitRule(operandContext ctx) {
    }

    @Override
    public void enterRule(argumentListContext ctx) {
    }

    @Override
    public void exitRule(argumentListContext ctx) {
    }

    @Override
    public void enterRule(typeSwitchStmtContext ctx) {
    }

    @Override
    public void exitRule(typeSwitchStmtContext ctx) {
    }

    @Override
    public void enterRule(typeDeclContext ctx) {
    }

    @Override
    public void exitRule(typeDeclContext ctx) {
    }

    @Override
    public void enterRule(unaryExprContext ctx) {
    }

    @Override
    public void exitRule(unaryExprContext ctx) {
    }

    @Override
    public void enterRule(channelContext ctx) {
    }

    @Override
    public void exitRule(channelContext ctx) {
    }

    @Override
    public void enterRule(literalContext ctx) {
    }

    @Override
    public void exitRule(literalContext ctx) {
    }

    @Override
    public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext<Token> ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext<Token> ctx) {
    }

}
