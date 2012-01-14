
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GoParserBaseListener extends ParseTreeListener<Token> {
	void enterRule(GoParserBase.typeContext ctx);
	void exitRule(GoParserBase.typeContext ctx);

	void enterRule(GoParserBase.typeNameContext ctx);
	void exitRule(GoParserBase.typeNameContext ctx);

	void enterRule(GoParserBase.typeLiteralContext ctx);
	void exitRule(GoParserBase.typeLiteralContext ctx);

	void enterRule(GoParserBase.arrayTypeContext ctx);
	void exitRule(GoParserBase.arrayTypeContext ctx);

	void enterRule(GoParserBase.arrayLengthContext ctx);
	void exitRule(GoParserBase.arrayLengthContext ctx);

	void enterRule(GoParserBase.elementTypeContext ctx);
	void exitRule(GoParserBase.elementTypeContext ctx);

	void enterRule(GoParserBase.sliceTypeContext ctx);
	void exitRule(GoParserBase.sliceTypeContext ctx);

	void enterRule(GoParserBase.structTypeContext ctx);
	void exitRule(GoParserBase.structTypeContext ctx);

	void enterRule(GoParserBase.fieldDeclContext ctx);
	void exitRule(GoParserBase.fieldDeclContext ctx);

	void enterRule(GoParserBase.anonymousFieldContext ctx);
	void exitRule(GoParserBase.anonymousFieldContext ctx);

	void enterRule(GoParserBase.tagContext ctx);
	void exitRule(GoParserBase.tagContext ctx);

	void enterRule(GoParserBase.pointerTypeContext ctx);
	void exitRule(GoParserBase.pointerTypeContext ctx);

	void enterRule(GoParserBase.baseTypeContext ctx);
	void exitRule(GoParserBase.baseTypeContext ctx);

	void enterRule(GoParserBase.functionTypeContext ctx);
	void exitRule(GoParserBase.functionTypeContext ctx);

	void enterRule(GoParserBase.signatureContext ctx);
	void exitRule(GoParserBase.signatureContext ctx);

	void enterRule(GoParserBase.resultContext ctx);
	void exitRule(GoParserBase.resultContext ctx);

	void enterRule(GoParserBase.parametersContext ctx);
	void exitRule(GoParserBase.parametersContext ctx);

	void enterRule(GoParserBase.parameterListContext ctx);
	void exitRule(GoParserBase.parameterListContext ctx);

	void enterRule(GoParserBase.parameterDeclContext ctx);
	void exitRule(GoParserBase.parameterDeclContext ctx);

	void enterRule(GoParserBase.interfaceTypeContext ctx);
	void exitRule(GoParserBase.interfaceTypeContext ctx);

	void enterRule(GoParserBase.methodSpecContext ctx);
	void exitRule(GoParserBase.methodSpecContext ctx);

	void enterRule(GoParserBase.methodNameContext ctx);
	void exitRule(GoParserBase.methodNameContext ctx);

	void enterRule(GoParserBase.interfaceTypeNameContext ctx);
	void exitRule(GoParserBase.interfaceTypeNameContext ctx);

	void enterRule(GoParserBase.mapTypeContext ctx);
	void exitRule(GoParserBase.mapTypeContext ctx);

	void enterRule(GoParserBase.keyTypeContext ctx);
	void exitRule(GoParserBase.keyTypeContext ctx);

	void enterRule(GoParserBase.channelTypeContext ctx);
	void exitRule(GoParserBase.channelTypeContext ctx);

	void enterRule(GoParserBase.blockContext ctx);
	void exitRule(GoParserBase.blockContext ctx);

	void enterRule(GoParserBase.declarationContext ctx);
	void exitRule(GoParserBase.declarationContext ctx);

	void enterRule(GoParserBase.topLevelDeclContext ctx);
	void exitRule(GoParserBase.topLevelDeclContext ctx);

	void enterRule(GoParserBase.constDeclContext ctx);
	void exitRule(GoParserBase.constDeclContext ctx);

	void enterRule(GoParserBase.constSpecContext ctx);
	void exitRule(GoParserBase.constSpecContext ctx);

	void enterRule(GoParserBase.identifierListContext ctx);
	void exitRule(GoParserBase.identifierListContext ctx);

	void enterRule(GoParserBase.expressionListContext ctx);
	void exitRule(GoParserBase.expressionListContext ctx);

	void enterRule(GoParserBase.typeDeclContext ctx);
	void exitRule(GoParserBase.typeDeclContext ctx);

	void enterRule(GoParserBase.typeSpecContext ctx);
	void exitRule(GoParserBase.typeSpecContext ctx);

	void enterRule(GoParserBase.varDeclContext ctx);
	void exitRule(GoParserBase.varDeclContext ctx);

	void enterRule(GoParserBase.varSpecContext ctx);
	void exitRule(GoParserBase.varSpecContext ctx);

	void enterRule(GoParserBase.shortVarDeclContext ctx);
	void exitRule(GoParserBase.shortVarDeclContext ctx);

	void enterRule(GoParserBase.functionDeclContext ctx);
	void exitRule(GoParserBase.functionDeclContext ctx);

	void enterRule(GoParserBase.bodyContext ctx);
	void exitRule(GoParserBase.bodyContext ctx);

	void enterRule(GoParserBase.methodDeclContext ctx);
	void exitRule(GoParserBase.methodDeclContext ctx);

	void enterRule(GoParserBase.receiverContext ctx);
	void exitRule(GoParserBase.receiverContext ctx);

	void enterRule(GoParserBase.baseTypeNameContext ctx);
	void exitRule(GoParserBase.baseTypeNameContext ctx);

	void enterRule(GoParserBase.operandContext ctx);
	void exitRule(GoParserBase.operandContext ctx);

	void enterRule(GoParserBase.literalContext ctx);
	void exitRule(GoParserBase.literalContext ctx);

	void enterRule(GoParserBase.basicLiteralContext ctx);
	void exitRule(GoParserBase.basicLiteralContext ctx);

	void enterRule(GoParserBase.qualifiedIdentifierContext ctx);
	void exitRule(GoParserBase.qualifiedIdentifierContext ctx);

	void enterRule(GoParserBase.methodExprContext ctx);
	void exitRule(GoParserBase.methodExprContext ctx);

	void enterRule(GoParserBase.receiverTypeContext ctx);
	void exitRule(GoParserBase.receiverTypeContext ctx);

	void enterRule(GoParserBase.compositeLiteralContext ctx);
	void exitRule(GoParserBase.compositeLiteralContext ctx);

	void enterRule(GoParserBase.literalTypeContext ctx);
	void exitRule(GoParserBase.literalTypeContext ctx);

	void enterRule(GoParserBase.literalValueContext ctx);
	void exitRule(GoParserBase.literalValueContext ctx);

	void enterRule(GoParserBase.elementListContext ctx);
	void exitRule(GoParserBase.elementListContext ctx);

	void enterRule(GoParserBase.elementContext ctx);
	void exitRule(GoParserBase.elementContext ctx);

	void enterRule(GoParserBase.keyContext ctx);
	void exitRule(GoParserBase.keyContext ctx);

	void enterRule(GoParserBase.fieldNameContext ctx);
	void exitRule(GoParserBase.fieldNameContext ctx);

	void enterRule(GoParserBase.elementIndexContext ctx);
	void exitRule(GoParserBase.elementIndexContext ctx);

	void enterRule(GoParserBase.valueContext ctx);
	void exitRule(GoParserBase.valueContext ctx);

	void enterRule(GoParserBase.functionLiteralContext ctx);
	void exitRule(GoParserBase.functionLiteralContext ctx);

	void enterRule(GoParserBase.expressionContext ctx);
	void exitRule(GoParserBase.expressionContext ctx);

	void enterRule(GoParserBase.primaryExprContext ctx);
	void exitRule(GoParserBase.primaryExprContext ctx);

	void enterRule(GoParserBase.argumentListContext ctx);
	void exitRule(GoParserBase.argumentListContext ctx);

	void enterRule(GoParserBase.conversionContext ctx);
	void exitRule(GoParserBase.conversionContext ctx);

	void enterRule(GoParserBase.statementContext ctx);
	void exitRule(GoParserBase.statementContext ctx);

	void enterRule(GoParserBase.simpleStmtContext ctx);
	void exitRule(GoParserBase.simpleStmtContext ctx);

	void enterRule(GoParserBase.emptyStmtContext ctx);
	void exitRule(GoParserBase.emptyStmtContext ctx);

	void enterRule(GoParserBase.labeledStmtContext ctx);
	void exitRule(GoParserBase.labeledStmtContext ctx);

	void enterRule(GoParserBase.labelContext ctx);
	void exitRule(GoParserBase.labelContext ctx);

	void enterRule(GoParserBase.expressionStmtContext ctx);
	void exitRule(GoParserBase.expressionStmtContext ctx);

	void enterRule(GoParserBase.sendStmtContext ctx);
	void exitRule(GoParserBase.sendStmtContext ctx);

	void enterRule(GoParserBase.channelContext ctx);
	void exitRule(GoParserBase.channelContext ctx);

	void enterRule(GoParserBase.incDecStmtContext ctx);
	void exitRule(GoParserBase.incDecStmtContext ctx);

	void enterRule(GoParserBase.assignmentContext ctx);
	void exitRule(GoParserBase.assignmentContext ctx);

	void enterRule(GoParserBase.assignOpContext ctx);
	void exitRule(GoParserBase.assignOpContext ctx);

	void enterRule(GoParserBase.addAssignOpContext ctx);
	void exitRule(GoParserBase.addAssignOpContext ctx);

	void enterRule(GoParserBase.mulAssignOpContext ctx);
	void exitRule(GoParserBase.mulAssignOpContext ctx);

	void enterRule(GoParserBase.ifStmtContext ctx);
	void exitRule(GoParserBase.ifStmtContext ctx);

	void enterRule(GoParserBase.switchStmtContext ctx);
	void exitRule(GoParserBase.switchStmtContext ctx);

	void enterRule(GoParserBase.exprSwitchStmtContext ctx);
	void exitRule(GoParserBase.exprSwitchStmtContext ctx);

	void enterRule(GoParserBase.exprCaseClauseContext ctx);
	void exitRule(GoParserBase.exprCaseClauseContext ctx);

	void enterRule(GoParserBase.exprSwitchCaseContext ctx);
	void exitRule(GoParserBase.exprSwitchCaseContext ctx);

	void enterRule(GoParserBase.typeSwitchStmtContext ctx);
	void exitRule(GoParserBase.typeSwitchStmtContext ctx);

	void enterRule(GoParserBase.typeSwitchGuardContext ctx);
	void exitRule(GoParserBase.typeSwitchGuardContext ctx);

	void enterRule(GoParserBase.typeCaseClauseContext ctx);
	void exitRule(GoParserBase.typeCaseClauseContext ctx);

	void enterRule(GoParserBase.typeSwitchCaseContext ctx);
	void exitRule(GoParserBase.typeSwitchCaseContext ctx);

	void enterRule(GoParserBase.typeListContext ctx);
	void exitRule(GoParserBase.typeListContext ctx);

	void enterRule(GoParserBase.forStmtContext ctx);
	void exitRule(GoParserBase.forStmtContext ctx);

	void enterRule(GoParserBase.conditionContext ctx);
	void exitRule(GoParserBase.conditionContext ctx);

	void enterRule(GoParserBase.forClauseContext ctx);
	void exitRule(GoParserBase.forClauseContext ctx);

	void enterRule(GoParserBase.initStmtContext ctx);
	void exitRule(GoParserBase.initStmtContext ctx);

	void enterRule(GoParserBase.postStmtContext ctx);
	void exitRule(GoParserBase.postStmtContext ctx);

	void enterRule(GoParserBase.rangeClauseContext ctx);
	void exitRule(GoParserBase.rangeClauseContext ctx);

	void enterRule(GoParserBase.goStmtContext ctx);
	void exitRule(GoParserBase.goStmtContext ctx);

	void enterRule(GoParserBase.selectStmtContext ctx);
	void exitRule(GoParserBase.selectStmtContext ctx);

	void enterRule(GoParserBase.commClauseContext ctx);
	void exitRule(GoParserBase.commClauseContext ctx);

	void enterRule(GoParserBase.commCaseContext ctx);
	void exitRule(GoParserBase.commCaseContext ctx);

	void enterRule(GoParserBase.recvStmtContext ctx);
	void exitRule(GoParserBase.recvStmtContext ctx);

	void enterRule(GoParserBase.recvExprContext ctx);
	void exitRule(GoParserBase.recvExprContext ctx);

	void enterRule(GoParserBase.returnStmtContext ctx);
	void exitRule(GoParserBase.returnStmtContext ctx);

	void enterRule(GoParserBase.breakStmtContext ctx);
	void exitRule(GoParserBase.breakStmtContext ctx);

	void enterRule(GoParserBase.continueStmtContext ctx);
	void exitRule(GoParserBase.continueStmtContext ctx);

	void enterRule(GoParserBase.gotoStmtContext ctx);
	void exitRule(GoParserBase.gotoStmtContext ctx);

	void enterRule(GoParserBase.fallthroughStmtContext ctx);
	void exitRule(GoParserBase.fallthroughStmtContext ctx);

	void enterRule(GoParserBase.deferStmtContext ctx);
	void exitRule(GoParserBase.deferStmtContext ctx);

	void enterRule(GoParserBase.builtinCallContext ctx);
	void exitRule(GoParserBase.builtinCallContext ctx);

	void enterRule(GoParserBase.builtinArgsContext ctx);
	void exitRule(GoParserBase.builtinArgsContext ctx);

	void enterRule(GoParserBase.sourceFileContext ctx);
	void exitRule(GoParserBase.sourceFileContext ctx);

	void enterRule(GoParserBase.packageClauseContext ctx);
	void exitRule(GoParserBase.packageClauseContext ctx);

	void enterRule(GoParserBase.packageNameContext ctx);
	void exitRule(GoParserBase.packageNameContext ctx);

	void enterRule(GoParserBase.importDeclContext ctx);
	void exitRule(GoParserBase.importDeclContext ctx);

	void enterRule(GoParserBase.importSpecContext ctx);
	void exitRule(GoParserBase.importSpecContext ctx);

	void enterRule(GoParserBase.importPathContext ctx);
	void exitRule(GoParserBase.importPathContext ctx);
}