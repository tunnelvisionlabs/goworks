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
import org.antlr.v4.runtime.tree.ParseTree;

public class GoParserBaseBaseListener implements GoParserBaseListener {
	@Override public void enterMultExpr(GoParserBase.MultExprContext ctx) { }
	@Override public void exitMultExpr(GoParserBase.MultExprContext ctx) { }

	@Override public void enterChannelType(GoParserBase.ChannelTypeContext ctx) { }
	@Override public void exitChannelType(GoParserBase.ChannelTypeContext ctx) { }

	@Override public void enterMulAssignOp(GoParserBase.MulAssignOpContext ctx) { }
	@Override public void exitMulAssignOp(GoParserBase.MulAssignOpContext ctx) { }

	@Override public void enterPackageName(GoParserBase.PackageNameContext ctx) { }
	@Override public void exitPackageName(GoParserBase.PackageNameContext ctx) { }

	@Override public void enterReceiver(GoParserBase.ReceiverContext ctx) { }
	@Override public void exitReceiver(GoParserBase.ReceiverContext ctx) { }

	@Override public void enterArrayType(GoParserBase.ArrayTypeContext ctx) { }
	@Override public void exitArrayType(GoParserBase.ArrayTypeContext ctx) { }

	@Override public void enterExpressionList(GoParserBase.ExpressionListContext ctx) { }
	@Override public void exitExpressionList(GoParserBase.ExpressionListContext ctx) { }

	@Override public void enterTag(GoParserBase.TagContext ctx) { }
	@Override public void exitTag(GoParserBase.TagContext ctx) { }

	@Override public void enterFallthroughStmt(GoParserBase.FallthroughStmtContext ctx) { }
	@Override public void exitFallthroughStmt(GoParserBase.FallthroughStmtContext ctx) { }

	@Override public void enterSelectorExpr(GoParserBase.SelectorExprContext ctx) { }
	@Override public void exitSelectorExpr(GoParserBase.SelectorExprContext ctx) { }

	@Override public void enterParameterList(GoParserBase.ParameterListContext ctx) { }
	@Override public void exitParameterList(GoParserBase.ParameterListContext ctx) { }

	@Override public void enterReceiverType(GoParserBase.ReceiverTypeContext ctx) { }
	@Override public void exitReceiverType(GoParserBase.ReceiverTypeContext ctx) { }

	@Override public void enterFieldName(GoParserBase.FieldNameContext ctx) { }
	@Override public void exitFieldName(GoParserBase.FieldNameContext ctx) { }

	@Override public void enterIfStmt(GoParserBase.IfStmtContext ctx) { }
	@Override public void exitIfStmt(GoParserBase.IfStmtContext ctx) { }

	@Override public void enterMethodName(GoParserBase.MethodNameContext ctx) { }
	@Override public void exitMethodName(GoParserBase.MethodNameContext ctx) { }

	@Override public void enterSignature(GoParserBase.SignatureContext ctx) { }
	@Override public void exitSignature(GoParserBase.SignatureContext ctx) { }

	@Override public void enterMapType(GoParserBase.MapTypeContext ctx) { }
	@Override public void exitMapType(GoParserBase.MapTypeContext ctx) { }

	@Override public void enterElement(GoParserBase.ElementContext ctx) { }
	@Override public void exitElement(GoParserBase.ElementContext ctx) { }

	@Override public void enterCallExpr(GoParserBase.CallExprContext ctx) { }
	@Override public void exitCallExpr(GoParserBase.CallExprContext ctx) { }

	@Override public void enterTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx) { }
	@Override public void exitTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx) { }

	@Override public void enterExprCaseClause(GoParserBase.ExprCaseClauseContext ctx) { }
	@Override public void exitExprCaseClause(GoParserBase.ExprCaseClauseContext ctx) { }

	@Override public void enterTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx) { }
	@Override public void exitTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx) { }

	@Override public void enterFunctionLiteral(GoParserBase.FunctionLiteralContext ctx) { }
	@Override public void exitFunctionLiteral(GoParserBase.FunctionLiteralContext ctx) { }

	@Override public void enterOrExpr(GoParserBase.OrExprContext ctx) { }
	@Override public void exitOrExpr(GoParserBase.OrExprContext ctx) { }

	@Override public void enterRecvExpr(GoParserBase.RecvExprContext ctx) { }
	@Override public void exitRecvExpr(GoParserBase.RecvExprContext ctx) { }

	@Override public void enterTopLevelDecl(GoParserBase.TopLevelDeclContext ctx) { }
	@Override public void exitTopLevelDecl(GoParserBase.TopLevelDeclContext ctx) { }

	@Override public void enterMethodSpec(GoParserBase.MethodSpecContext ctx) { }
	@Override public void exitMethodSpec(GoParserBase.MethodSpecContext ctx) { }

	@Override public void enterConstSpec(GoParserBase.ConstSpecContext ctx) { }
	@Override public void exitConstSpec(GoParserBase.ConstSpecContext ctx) { }

	@Override public void enterCompositeLiteral(GoParserBase.CompositeLiteralContext ctx) { }
	@Override public void exitCompositeLiteral(GoParserBase.CompositeLiteralContext ctx) { }

	@Override public void enterForClause(GoParserBase.ForClauseContext ctx) { }
	@Override public void exitForClause(GoParserBase.ForClauseContext ctx) { }

	@Override public void enterShortVarDecl(GoParserBase.ShortVarDeclContext ctx) { }
	@Override public void exitShortVarDecl(GoParserBase.ShortVarDeclContext ctx) { }

	@Override public void enterGotoStmt(GoParserBase.GotoStmtContext ctx) { }
	@Override public void exitGotoStmt(GoParserBase.GotoStmtContext ctx) { }

	@Override public void enterArrayLength(GoParserBase.ArrayLengthContext ctx) { }
	@Override public void exitArrayLength(GoParserBase.ArrayLengthContext ctx) { }

	@Override public void enterInterfaceType(GoParserBase.InterfaceTypeContext ctx) { }
	@Override public void exitInterfaceType(GoParserBase.InterfaceTypeContext ctx) { }

	@Override public void enterConversion(GoParserBase.ConversionContext ctx) { }
	@Override public void exitConversion(GoParserBase.ConversionContext ctx) { }

	@Override public void enterBlock(GoParserBase.BlockContext ctx) { }
	@Override public void exitBlock(GoParserBase.BlockContext ctx) { }

	@Override public void enterBreakStmt(GoParserBase.BreakStmtContext ctx) { }
	@Override public void exitBreakStmt(GoParserBase.BreakStmtContext ctx) { }

	@Override public void enterEmptyStmt(GoParserBase.EmptyStmtContext ctx) { }
	@Override public void exitEmptyStmt(GoParserBase.EmptyStmtContext ctx) { }

	@Override public void enterFunctionType(GoParserBase.FunctionTypeContext ctx) { }
	@Override public void exitFunctionType(GoParserBase.FunctionTypeContext ctx) { }

	@Override public void enterBaseType(GoParserBase.BaseTypeContext ctx) { }
	@Override public void exitBaseType(GoParserBase.BaseTypeContext ctx) { }

	@Override public void enterOperandExpr(GoParserBase.OperandExprContext ctx) { }
	@Override public void exitOperandExpr(GoParserBase.OperandExprContext ctx) { }

	@Override public void enterFieldDecl(GoParserBase.FieldDeclContext ctx) { }
	@Override public void exitFieldDecl(GoParserBase.FieldDeclContext ctx) { }

	@Override public void enterExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx) { }
	@Override public void exitExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx) { }

	@Override public void enterGoStmt(GoParserBase.GoStmtContext ctx) { }
	@Override public void exitGoStmt(GoParserBase.GoStmtContext ctx) { }

	@Override public void enterParameterDecl(GoParserBase.ParameterDeclContext ctx) { }
	@Override public void exitParameterDecl(GoParserBase.ParameterDeclContext ctx) { }

	@Override public void enterBasicLiteral(GoParserBase.BasicLiteralContext ctx) { }
	@Override public void exitBasicLiteral(GoParserBase.BasicLiteralContext ctx) { }

	@Override public void enterExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx) { }
	@Override public void exitExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx) { }

	@Override public void enterTypeLiteral(GoParserBase.TypeLiteralContext ctx) { }
	@Override public void exitTypeLiteral(GoParserBase.TypeLiteralContext ctx) { }

	@Override public void enterSelectStmt(GoParserBase.SelectStmtContext ctx) { }
	@Override public void exitSelectStmt(GoParserBase.SelectStmtContext ctx) { }

	@Override public void enterImportSpec(GoParserBase.ImportSpecContext ctx) { }
	@Override public void exitImportSpec(GoParserBase.ImportSpecContext ctx) { }

	@Override public void enterTypeName(GoParserBase.TypeNameContext ctx) { }
	@Override public void exitTypeName(GoParserBase.TypeNameContext ctx) { }

	@Override public void enterLiteralType(GoParserBase.LiteralTypeContext ctx) { }
	@Override public void exitLiteralType(GoParserBase.LiteralTypeContext ctx) { }

	@Override public void enterAssignment(GoParserBase.AssignmentContext ctx) { }
	@Override public void exitAssignment(GoParserBase.AssignmentContext ctx) { }

	@Override public void enterAssignOp(GoParserBase.AssignOpContext ctx) { }
	@Override public void exitAssignOp(GoParserBase.AssignOpContext ctx) { }

	@Override public void enterRecvStmt(GoParserBase.RecvStmtContext ctx) { }
	@Override public void exitRecvStmt(GoParserBase.RecvStmtContext ctx) { }

	@Override public void enterTypeSpec(GoParserBase.TypeSpecContext ctx) { }
	@Override public void exitTypeSpec(GoParserBase.TypeSpecContext ctx) { }

	@Override public void enterPackageClause(GoParserBase.PackageClauseContext ctx) { }
	@Override public void exitPackageClause(GoParserBase.PackageClauseContext ctx) { }

	@Override public void enterBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx) { }
	@Override public void exitBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx) { }

	@Override public void enterLiteralValue(GoParserBase.LiteralValueContext ctx) { }
	@Override public void exitLiteralValue(GoParserBase.LiteralValueContext ctx) { }

	@Override public void enterIndexExpr(GoParserBase.IndexExprContext ctx) { }
	@Override public void exitIndexExpr(GoParserBase.IndexExprContext ctx) { }

	@Override public void enterVarSpec(GoParserBase.VarSpecContext ctx) { }
	@Override public void exitVarSpec(GoParserBase.VarSpecContext ctx) { }

	@Override public void enterBody(GoParserBase.BodyContext ctx) { }
	@Override public void exitBody(GoParserBase.BodyContext ctx) { }

	@Override public void enterCommClause(GoParserBase.CommClauseContext ctx) { }
	@Override public void exitCommClause(GoParserBase.CommClauseContext ctx) { }

	@Override public void enterQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx) { }
	@Override public void exitQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx) { }

	@Override public void enterReturnStmt(GoParserBase.ReturnStmtContext ctx) { }
	@Override public void exitReturnStmt(GoParserBase.ReturnStmtContext ctx) { }

	@Override public void enterSimpleStmt(GoParserBase.SimpleStmtContext ctx) { }
	@Override public void exitSimpleStmt(GoParserBase.SimpleStmtContext ctx) { }

	@Override public void enterTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx) { }
	@Override public void exitTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx) { }

	@Override public void enterType(GoParserBase.TypeContext ctx) { }
	@Override public void exitType(GoParserBase.TypeContext ctx) { }

	@Override public void enterInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx) { }
	@Override public void exitInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx) { }

	@Override public void enterContinueStmt(GoParserBase.ContinueStmtContext ctx) { }
	@Override public void exitContinueStmt(GoParserBase.ContinueStmtContext ctx) { }

	@Override public void enterValue(GoParserBase.ValueContext ctx) { }
	@Override public void exitValue(GoParserBase.ValueContext ctx) { }

	@Override public void enterMethodDecl(GoParserBase.MethodDeclContext ctx) { }
	@Override public void exitMethodDecl(GoParserBase.MethodDeclContext ctx) { }

	@Override public void enterLabeledStmt(GoParserBase.LabeledStmtContext ctx) { }
	@Override public void exitLabeledStmt(GoParserBase.LabeledStmtContext ctx) { }

	@Override public void enterParameters(GoParserBase.ParametersContext ctx) { }
	@Override public void exitParameters(GoParserBase.ParametersContext ctx) { }

	@Override public void enterDeferStmt(GoParserBase.DeferStmtContext ctx) { }
	@Override public void exitDeferStmt(GoParserBase.DeferStmtContext ctx) { }

	@Override public void enterKey(GoParserBase.KeyContext ctx) { }
	@Override public void exitKey(GoParserBase.KeyContext ctx) { }

	@Override public void enterDeclaration(GoParserBase.DeclarationContext ctx) { }
	@Override public void exitDeclaration(GoParserBase.DeclarationContext ctx) { }

	@Override public void enterCommCase(GoParserBase.CommCaseContext ctx) { }
	@Override public void exitCommCase(GoParserBase.CommCaseContext ctx) { }

	@Override public void enterBuiltinArgs(GoParserBase.BuiltinArgsContext ctx) { }
	@Override public void exitBuiltinArgs(GoParserBase.BuiltinArgsContext ctx) { }

	@Override public void enterCondition(GoParserBase.ConditionContext ctx) { }
	@Override public void exitCondition(GoParserBase.ConditionContext ctx) { }

	@Override public void enterConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx) { }
	@Override public void exitConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx) { }

	@Override public void enterLabel(GoParserBase.LabelContext ctx) { }
	@Override public void exitLabel(GoParserBase.LabelContext ctx) { }

	@Override public void enterElementType(GoParserBase.ElementTypeContext ctx) { }
	@Override public void exitElementType(GoParserBase.ElementTypeContext ctx) { }

	@Override public void enterFunctionDecl(GoParserBase.FunctionDeclContext ctx) { }
	@Override public void exitFunctionDecl(GoParserBase.FunctionDeclContext ctx) { }

	@Override public void enterStatement(GoParserBase.StatementContext ctx) { }
	@Override public void exitStatement(GoParserBase.StatementContext ctx) { }

	@Override public void enterPointerType(GoParserBase.PointerTypeContext ctx) { }
	@Override public void exitPointerType(GoParserBase.PointerTypeContext ctx) { }

	@Override public void enterAddAssignOp(GoParserBase.AddAssignOpContext ctx) { }
	@Override public void exitAddAssignOp(GoParserBase.AddAssignOpContext ctx) { }

	@Override public void enterSourceFile(GoParserBase.SourceFileContext ctx) { }
	@Override public void exitSourceFile(GoParserBase.SourceFileContext ctx) { }

	@Override public void enterSliceExpr(GoParserBase.SliceExprContext ctx) { }
	@Override public void exitSliceExpr(GoParserBase.SliceExprContext ctx) { }

	@Override public void enterBaseTypeName(GoParserBase.BaseTypeNameContext ctx) { }
	@Override public void exitBaseTypeName(GoParserBase.BaseTypeNameContext ctx) { }

	@Override public void enterMethodExpr(GoParserBase.MethodExprContext ctx) { }
	@Override public void exitMethodExpr(GoParserBase.MethodExprContext ctx) { }

	@Override public void enterElementIndex(GoParserBase.ElementIndexContext ctx) { }
	@Override public void exitElementIndex(GoParserBase.ElementIndexContext ctx) { }

	@Override public void enterTypeList(GoParserBase.TypeListContext ctx) { }
	@Override public void exitTypeList(GoParserBase.TypeListContext ctx) { }

	@Override public void enterIncDecStmt(GoParserBase.IncDecStmtContext ctx) { }
	@Override public void exitIncDecStmt(GoParserBase.IncDecStmtContext ctx) { }

	@Override public void enterBuiltinCall(GoParserBase.BuiltinCallContext ctx) { }
	@Override public void exitBuiltinCall(GoParserBase.BuiltinCallContext ctx) { }

	@Override public void enterConstDecl(GoParserBase.ConstDeclContext ctx) { }
	@Override public void exitConstDecl(GoParserBase.ConstDeclContext ctx) { }

	@Override public void enterResult(GoParserBase.ResultContext ctx) { }
	@Override public void exitResult(GoParserBase.ResultContext ctx) { }

	@Override public void enterAndExpr(GoParserBase.AndExprContext ctx) { }
	@Override public void exitAndExpr(GoParserBase.AndExprContext ctx) { }

	@Override public void enterStructType(GoParserBase.StructTypeContext ctx) { }
	@Override public void exitStructType(GoParserBase.StructTypeContext ctx) { }

	@Override public void enterVarDecl(GoParserBase.VarDeclContext ctx) { }
	@Override public void exitVarDecl(GoParserBase.VarDeclContext ctx) { }

	@Override public void enterInitStmt(GoParserBase.InitStmtContext ctx) { }
	@Override public void exitInitStmt(GoParserBase.InitStmtContext ctx) { }

	@Override public void enterIdentifierList(GoParserBase.IdentifierListContext ctx) { }
	@Override public void exitIdentifierList(GoParserBase.IdentifierListContext ctx) { }

	@Override public void enterSliceType(GoParserBase.SliceTypeContext ctx) { }
	@Override public void exitSliceType(GoParserBase.SliceTypeContext ctx) { }

	@Override public void enterCompareExpr(GoParserBase.CompareExprContext ctx) { }
	@Override public void exitCompareExpr(GoParserBase.CompareExprContext ctx) { }

	@Override public void enterImportDecl(GoParserBase.ImportDeclContext ctx) { }
	@Override public void exitImportDecl(GoParserBase.ImportDeclContext ctx) { }

	@Override public void enterElementList(GoParserBase.ElementListContext ctx) { }
	@Override public void exitElementList(GoParserBase.ElementListContext ctx) { }

	@Override public void enterKeyType(GoParserBase.KeyTypeContext ctx) { }
	@Override public void exitKeyType(GoParserBase.KeyTypeContext ctx) { }

	@Override public void enterImportPath(GoParserBase.ImportPathContext ctx) { }
	@Override public void exitImportPath(GoParserBase.ImportPathContext ctx) { }

	@Override public void enterAnonymousField(GoParserBase.AnonymousFieldContext ctx) { }
	@Override public void exitAnonymousField(GoParserBase.AnonymousFieldContext ctx) { }

	@Override public void enterAddExpr(GoParserBase.AddExprContext ctx) { }
	@Override public void exitAddExpr(GoParserBase.AddExprContext ctx) { }

	@Override public void enterExpressionStmt(GoParserBase.ExpressionStmtContext ctx) { }
	@Override public void exitExpressionStmt(GoParserBase.ExpressionStmtContext ctx) { }

	@Override public void enterSendStmt(GoParserBase.SendStmtContext ctx) { }
	@Override public void exitSendStmt(GoParserBase.SendStmtContext ctx) { }

	@Override public void enterSwitchStmt(GoParserBase.SwitchStmtContext ctx) { }
	@Override public void exitSwitchStmt(GoParserBase.SwitchStmtContext ctx) { }

	@Override public void enterPostStmt(GoParserBase.PostStmtContext ctx) { }
	@Override public void exitPostStmt(GoParserBase.PostStmtContext ctx) { }

	@Override public void enterForStmt(GoParserBase.ForStmtContext ctx) { }
	@Override public void exitForStmt(GoParserBase.ForStmtContext ctx) { }

	@Override public void enterTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx) { }
	@Override public void exitTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx) { }

	@Override public void enterRangeClause(GoParserBase.RangeClauseContext ctx) { }
	@Override public void exitRangeClause(GoParserBase.RangeClauseContext ctx) { }

	@Override public void enterOperand(GoParserBase.OperandContext ctx) { }
	@Override public void exitOperand(GoParserBase.OperandContext ctx) { }

	@Override public void enterArgumentList(GoParserBase.ArgumentListContext ctx) { }
	@Override public void exitArgumentList(GoParserBase.ArgumentListContext ctx) { }

	@Override public void enterTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx) { }
	@Override public void exitTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx) { }

	@Override public void enterTypeDecl(GoParserBase.TypeDeclContext ctx) { }
	@Override public void exitTypeDecl(GoParserBase.TypeDeclContext ctx) { }

	@Override public void enterUnaryExpr(GoParserBase.UnaryExprContext ctx) { }
	@Override public void exitUnaryExpr(GoParserBase.UnaryExprContext ctx) { }

	@Override public void enterChannel(GoParserBase.ChannelContext ctx) { }
	@Override public void exitChannel(GoParserBase.ChannelContext ctx) { }

	@Override public void enterLiteral(GoParserBase.LiteralContext ctx) { }
	@Override public void exitLiteral(GoParserBase.LiteralContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void visitTerminal(ParseTree.TerminalNode<? extends Token> node) { }
	@Override public void visitErrorNode(ParseTree.ErrorNode<? extends Token> node) { }
}