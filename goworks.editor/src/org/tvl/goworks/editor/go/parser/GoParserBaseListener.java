// Generated from GoParser.g4 by ANTLR 4.0-SNAPSHOT
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
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class GoParserBaseListener implements GoParserListener {
	@Override public void enterMultExpr(AbstractGoParser.MultExprContext ctx) { }
	@Override public void exitMultExpr(AbstractGoParser.MultExprContext ctx) { }

	@Override public void enterChannelType(AbstractGoParser.ChannelTypeContext ctx) { }
	@Override public void exitChannelType(AbstractGoParser.ChannelTypeContext ctx) { }

	@Override public void enterMulAssignOp(AbstractGoParser.MulAssignOpContext ctx) { }
	@Override public void exitMulAssignOp(AbstractGoParser.MulAssignOpContext ctx) { }

	@Override public void enterPackageName(AbstractGoParser.PackageNameContext ctx) { }
	@Override public void exitPackageName(AbstractGoParser.PackageNameContext ctx) { }

	@Override public void enterReceiver(AbstractGoParser.ReceiverContext ctx) { }
	@Override public void exitReceiver(AbstractGoParser.ReceiverContext ctx) { }

	@Override public void enterArrayType(AbstractGoParser.ArrayTypeContext ctx) { }
	@Override public void exitArrayType(AbstractGoParser.ArrayTypeContext ctx) { }

	@Override public void enterExpressionList(AbstractGoParser.ExpressionListContext ctx) { }
	@Override public void exitExpressionList(AbstractGoParser.ExpressionListContext ctx) { }

	@Override public void enterTag(AbstractGoParser.TagContext ctx) { }
	@Override public void exitTag(AbstractGoParser.TagContext ctx) { }

	@Override public void enterElementNameOrIndex(AbstractGoParser.ElementNameOrIndexContext ctx) { }
	@Override public void exitElementNameOrIndex(AbstractGoParser.ElementNameOrIndexContext ctx) { }

	@Override public void enterFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx) { }
	@Override public void exitFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx) { }

	@Override public void enterSelectorExpr(AbstractGoParser.SelectorExprContext ctx) { }
	@Override public void exitSelectorExpr(AbstractGoParser.SelectorExprContext ctx) { }

	@Override public void enterParameterList(AbstractGoParser.ParameterListContext ctx) { }
	@Override public void exitParameterList(AbstractGoParser.ParameterListContext ctx) { }

	@Override public void enterReceiverType(AbstractGoParser.ReceiverTypeContext ctx) { }
	@Override public void exitReceiverType(AbstractGoParser.ReceiverTypeContext ctx) { }

	@Override public void enterIfStmt(AbstractGoParser.IfStmtContext ctx) { }
	@Override public void exitIfStmt(AbstractGoParser.IfStmtContext ctx) { }

	@Override public void enterMethodName(AbstractGoParser.MethodNameContext ctx) { }
	@Override public void exitMethodName(AbstractGoParser.MethodNameContext ctx) { }

	@Override public void enterSignature(AbstractGoParser.SignatureContext ctx) { }
	@Override public void exitSignature(AbstractGoParser.SignatureContext ctx) { }

	@Override public void enterMapType(AbstractGoParser.MapTypeContext ctx) { }
	@Override public void exitMapType(AbstractGoParser.MapTypeContext ctx) { }

	@Override public void enterElement(AbstractGoParser.ElementContext ctx) { }
	@Override public void exitElement(AbstractGoParser.ElementContext ctx) { }

	@Override public void enterCallExpr(AbstractGoParser.CallExprContext ctx) { }
	@Override public void exitCallExpr(AbstractGoParser.CallExprContext ctx) { }

	@Override public void enterTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx) { }
	@Override public void exitTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx) { }

	@Override public void enterExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx) { }
	@Override public void exitExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx) { }

	@Override public void enterTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx) { }
	@Override public void exitTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx) { }

	@Override public void enterFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx) { }
	@Override public void exitFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx) { }

	@Override public void enterOrExpr(AbstractGoParser.OrExprContext ctx) { }
	@Override public void exitOrExpr(AbstractGoParser.OrExprContext ctx) { }

	@Override public void enterRecvExpr(AbstractGoParser.RecvExprContext ctx) { }
	@Override public void exitRecvExpr(AbstractGoParser.RecvExprContext ctx) { }

	@Override public void enterTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx) { }
	@Override public void exitTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx) { }

	@Override public void enterMethodSpec(AbstractGoParser.MethodSpecContext ctx) { }
	@Override public void exitMethodSpec(AbstractGoParser.MethodSpecContext ctx) { }

	@Override public void enterConstSpec(AbstractGoParser.ConstSpecContext ctx) { }
	@Override public void exitConstSpec(AbstractGoParser.ConstSpecContext ctx) { }

	@Override public void enterCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx) { }
	@Override public void exitCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx) { }

	@Override public void enterForClause(AbstractGoParser.ForClauseContext ctx) { }
	@Override public void exitForClause(AbstractGoParser.ForClauseContext ctx) { }

	@Override public void enterShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx) { }
	@Override public void exitShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx) { }

	@Override public void enterGotoStmt(AbstractGoParser.GotoStmtContext ctx) { }
	@Override public void exitGotoStmt(AbstractGoParser.GotoStmtContext ctx) { }

	@Override public void enterArrayLength(AbstractGoParser.ArrayLengthContext ctx) { }
	@Override public void exitArrayLength(AbstractGoParser.ArrayLengthContext ctx) { }

	@Override public void enterInterfaceType(AbstractGoParser.InterfaceTypeContext ctx) { }
	@Override public void exitInterfaceType(AbstractGoParser.InterfaceTypeContext ctx) { }

	@Override public void enterConversion(AbstractGoParser.ConversionContext ctx) { }
	@Override public void exitConversion(AbstractGoParser.ConversionContext ctx) { }

	@Override public void enterBlock(AbstractGoParser.BlockContext ctx) { }
	@Override public void exitBlock(AbstractGoParser.BlockContext ctx) { }

	@Override public void enterBreakStmt(AbstractGoParser.BreakStmtContext ctx) { }
	@Override public void exitBreakStmt(AbstractGoParser.BreakStmtContext ctx) { }

	@Override public void enterEmptyStmt(AbstractGoParser.EmptyStmtContext ctx) { }
	@Override public void exitEmptyStmt(AbstractGoParser.EmptyStmtContext ctx) { }

	@Override public void enterFunctionType(AbstractGoParser.FunctionTypeContext ctx) { }
	@Override public void exitFunctionType(AbstractGoParser.FunctionTypeContext ctx) { }

	@Override public void enterBaseType(AbstractGoParser.BaseTypeContext ctx) { }
	@Override public void exitBaseType(AbstractGoParser.BaseTypeContext ctx) { }

	@Override public void enterOperandExpr(AbstractGoParser.OperandExprContext ctx) { }
	@Override public void exitOperandExpr(AbstractGoParser.OperandExprContext ctx) { }

	@Override public void enterFieldDecl(AbstractGoParser.FieldDeclContext ctx) { }
	@Override public void exitFieldDecl(AbstractGoParser.FieldDeclContext ctx) { }

	@Override public void enterExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx) { }
	@Override public void exitExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx) { }

	@Override public void enterGoStmt(AbstractGoParser.GoStmtContext ctx) { }
	@Override public void exitGoStmt(AbstractGoParser.GoStmtContext ctx) { }

	@Override public void enterParameterDecl(AbstractGoParser.ParameterDeclContext ctx) { }
	@Override public void exitParameterDecl(AbstractGoParser.ParameterDeclContext ctx) { }

	@Override public void enterBasicLiteral(AbstractGoParser.BasicLiteralContext ctx) { }
	@Override public void exitBasicLiteral(AbstractGoParser.BasicLiteralContext ctx) { }

	@Override public void enterExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx) { }
	@Override public void exitExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx) { }

	@Override public void enterTypeLiteral(AbstractGoParser.TypeLiteralContext ctx) { }
	@Override public void exitTypeLiteral(AbstractGoParser.TypeLiteralContext ctx) { }

	@Override public void enterSelectStmt(AbstractGoParser.SelectStmtContext ctx) { }
	@Override public void exitSelectStmt(AbstractGoParser.SelectStmtContext ctx) { }

	@Override public void enterImportSpec(AbstractGoParser.ImportSpecContext ctx) { }
	@Override public void exitImportSpec(AbstractGoParser.ImportSpecContext ctx) { }

	@Override public void enterTypeName(AbstractGoParser.TypeNameContext ctx) { }
	@Override public void exitTypeName(AbstractGoParser.TypeNameContext ctx) { }

	@Override public void enterLiteralType(AbstractGoParser.LiteralTypeContext ctx) { }
	@Override public void exitLiteralType(AbstractGoParser.LiteralTypeContext ctx) { }

	@Override public void enterAssignment(AbstractGoParser.AssignmentContext ctx) { }
	@Override public void exitAssignment(AbstractGoParser.AssignmentContext ctx) { }

	@Override public void enterAssignOp(AbstractGoParser.AssignOpContext ctx) { }
	@Override public void exitAssignOp(AbstractGoParser.AssignOpContext ctx) { }

	@Override public void enterRecvStmt(AbstractGoParser.RecvStmtContext ctx) { }
	@Override public void exitRecvStmt(AbstractGoParser.RecvStmtContext ctx) { }

	@Override public void enterTypeSpec(AbstractGoParser.TypeSpecContext ctx) { }
	@Override public void exitTypeSpec(AbstractGoParser.TypeSpecContext ctx) { }

	@Override public void enterPackageClause(AbstractGoParser.PackageClauseContext ctx) { }
	@Override public void exitPackageClause(AbstractGoParser.PackageClauseContext ctx) { }

	@Override public void enterBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx) { }
	@Override public void exitBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx) { }

	@Override public void enterLiteralValue(AbstractGoParser.LiteralValueContext ctx) { }
	@Override public void exitLiteralValue(AbstractGoParser.LiteralValueContext ctx) { }

	@Override public void enterIndexExpr(AbstractGoParser.IndexExprContext ctx) { }
	@Override public void exitIndexExpr(AbstractGoParser.IndexExprContext ctx) { }

	@Override public void enterVarSpec(AbstractGoParser.VarSpecContext ctx) { }
	@Override public void exitVarSpec(AbstractGoParser.VarSpecContext ctx) { }

	@Override public void enterBody(AbstractGoParser.BodyContext ctx) { }
	@Override public void exitBody(AbstractGoParser.BodyContext ctx) { }

	@Override public void enterCommClause(AbstractGoParser.CommClauseContext ctx) { }
	@Override public void exitCommClause(AbstractGoParser.CommClauseContext ctx) { }

	@Override public void enterQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx) { }
	@Override public void exitQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx) { }

	@Override public void enterReturnStmt(AbstractGoParser.ReturnStmtContext ctx) { }
	@Override public void exitReturnStmt(AbstractGoParser.ReturnStmtContext ctx) { }

	@Override public void enterSimpleStmt(AbstractGoParser.SimpleStmtContext ctx) { }
	@Override public void exitSimpleStmt(AbstractGoParser.SimpleStmtContext ctx) { }

	@Override public void enterTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx) { }
	@Override public void exitTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx) { }

	@Override public void enterType(AbstractGoParser.TypeContext ctx) { }
	@Override public void exitType(AbstractGoParser.TypeContext ctx) { }

	@Override public void enterInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx) { }
	@Override public void exitInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx) { }

	@Override public void enterContinueStmt(AbstractGoParser.ContinueStmtContext ctx) { }
	@Override public void exitContinueStmt(AbstractGoParser.ContinueStmtContext ctx) { }

	@Override public void enterValue(AbstractGoParser.ValueContext ctx) { }
	@Override public void exitValue(AbstractGoParser.ValueContext ctx) { }

	@Override public void enterMethodDecl(AbstractGoParser.MethodDeclContext ctx) { }
	@Override public void exitMethodDecl(AbstractGoParser.MethodDeclContext ctx) { }

	@Override public void enterLabeledStmt(AbstractGoParser.LabeledStmtContext ctx) { }
	@Override public void exitLabeledStmt(AbstractGoParser.LabeledStmtContext ctx) { }

	@Override public void enterParameters(AbstractGoParser.ParametersContext ctx) { }
	@Override public void exitParameters(AbstractGoParser.ParametersContext ctx) { }

	@Override public void enterDeferStmt(AbstractGoParser.DeferStmtContext ctx) { }
	@Override public void exitDeferStmt(AbstractGoParser.DeferStmtContext ctx) { }

	@Override public void enterSourceFileBody(AbstractGoParser.SourceFileBodyContext ctx) { }
	@Override public void exitSourceFileBody(AbstractGoParser.SourceFileBodyContext ctx) { }

	@Override public void enterKey(AbstractGoParser.KeyContext ctx) { }
	@Override public void exitKey(AbstractGoParser.KeyContext ctx) { }

	@Override public void enterDeclaration(AbstractGoParser.DeclarationContext ctx) { }
	@Override public void exitDeclaration(AbstractGoParser.DeclarationContext ctx) { }

	@Override public void enterCommCase(AbstractGoParser.CommCaseContext ctx) { }
	@Override public void exitCommCase(AbstractGoParser.CommCaseContext ctx) { }

	@Override public void enterBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx) { }
	@Override public void exitBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx) { }

	@Override public void enterCondition(AbstractGoParser.ConditionContext ctx) { }
	@Override public void exitCondition(AbstractGoParser.ConditionContext ctx) { }

	@Override public void enterConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx) { }
	@Override public void exitConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx) { }

	@Override public void enterLabel(AbstractGoParser.LabelContext ctx) { }
	@Override public void exitLabel(AbstractGoParser.LabelContext ctx) { }

	@Override public void enterElementType(AbstractGoParser.ElementTypeContext ctx) { }
	@Override public void exitElementType(AbstractGoParser.ElementTypeContext ctx) { }

	@Override public void enterFunctionDecl(AbstractGoParser.FunctionDeclContext ctx) { }
	@Override public void exitFunctionDecl(AbstractGoParser.FunctionDeclContext ctx) { }

	@Override public void enterStatement(AbstractGoParser.StatementContext ctx) { }
	@Override public void exitStatement(AbstractGoParser.StatementContext ctx) { }

	@Override public void enterPointerType(AbstractGoParser.PointerTypeContext ctx) { }
	@Override public void exitPointerType(AbstractGoParser.PointerTypeContext ctx) { }

	@Override public void enterAddAssignOp(AbstractGoParser.AddAssignOpContext ctx) { }
	@Override public void exitAddAssignOp(AbstractGoParser.AddAssignOpContext ctx) { }

	@Override public void enterSourceFile(AbstractGoParser.SourceFileContext ctx) { }
	@Override public void exitSourceFile(AbstractGoParser.SourceFileContext ctx) { }

	@Override public void enterSliceExpr(AbstractGoParser.SliceExprContext ctx) { }
	@Override public void exitSliceExpr(AbstractGoParser.SliceExprContext ctx) { }

	@Override public void enterBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx) { }
	@Override public void exitBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx) { }

	@Override public void enterMethodExpr(AbstractGoParser.MethodExprContext ctx) { }
	@Override public void exitMethodExpr(AbstractGoParser.MethodExprContext ctx) { }

	@Override public void enterTypeList(AbstractGoParser.TypeListContext ctx) { }
	@Override public void exitTypeList(AbstractGoParser.TypeListContext ctx) { }

	@Override public void enterIncDecStmt(AbstractGoParser.IncDecStmtContext ctx) { }
	@Override public void exitIncDecStmt(AbstractGoParser.IncDecStmtContext ctx) { }

	@Override public void enterBuiltinCall(AbstractGoParser.BuiltinCallContext ctx) { }
	@Override public void exitBuiltinCall(AbstractGoParser.BuiltinCallContext ctx) { }

	@Override public void enterConstDecl(AbstractGoParser.ConstDeclContext ctx) { }
	@Override public void exitConstDecl(AbstractGoParser.ConstDeclContext ctx) { }

	@Override public void enterResult(AbstractGoParser.ResultContext ctx) { }
	@Override public void exitResult(AbstractGoParser.ResultContext ctx) { }

	@Override public void enterAndExpr(AbstractGoParser.AndExprContext ctx) { }
	@Override public void exitAndExpr(AbstractGoParser.AndExprContext ctx) { }

	@Override public void enterStructType(AbstractGoParser.StructTypeContext ctx) { }
	@Override public void exitStructType(AbstractGoParser.StructTypeContext ctx) { }

	@Override public void enterVarDecl(AbstractGoParser.VarDeclContext ctx) { }
	@Override public void exitVarDecl(AbstractGoParser.VarDeclContext ctx) { }

	@Override public void enterInitStmt(AbstractGoParser.InitStmtContext ctx) { }
	@Override public void exitInitStmt(AbstractGoParser.InitStmtContext ctx) { }

	@Override public void enterIdentifierList(AbstractGoParser.IdentifierListContext ctx) { }
	@Override public void exitIdentifierList(AbstractGoParser.IdentifierListContext ctx) { }

	@Override public void enterSliceType(AbstractGoParser.SliceTypeContext ctx) { }
	@Override public void exitSliceType(AbstractGoParser.SliceTypeContext ctx) { }

	@Override public void enterCompareExpr(AbstractGoParser.CompareExprContext ctx) { }
	@Override public void exitCompareExpr(AbstractGoParser.CompareExprContext ctx) { }

	@Override public void enterImportDecl(AbstractGoParser.ImportDeclContext ctx) { }
	@Override public void exitImportDecl(AbstractGoParser.ImportDeclContext ctx) { }

	@Override public void enterElementList(AbstractGoParser.ElementListContext ctx) { }
	@Override public void exitElementList(AbstractGoParser.ElementListContext ctx) { }

	@Override public void enterKeyType(AbstractGoParser.KeyTypeContext ctx) { }
	@Override public void exitKeyType(AbstractGoParser.KeyTypeContext ctx) { }

	@Override public void enterImportPath(AbstractGoParser.ImportPathContext ctx) { }
	@Override public void exitImportPath(AbstractGoParser.ImportPathContext ctx) { }

	@Override public void enterAnonymousField(AbstractGoParser.AnonymousFieldContext ctx) { }
	@Override public void exitAnonymousField(AbstractGoParser.AnonymousFieldContext ctx) { }

	@Override public void enterAddExpr(AbstractGoParser.AddExprContext ctx) { }
	@Override public void exitAddExpr(AbstractGoParser.AddExprContext ctx) { }

	@Override public void enterExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx) { }
	@Override public void exitExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx) { }

	@Override public void enterSendStmt(AbstractGoParser.SendStmtContext ctx) { }
	@Override public void exitSendStmt(AbstractGoParser.SendStmtContext ctx) { }

	@Override public void enterSwitchStmt(AbstractGoParser.SwitchStmtContext ctx) { }
	@Override public void exitSwitchStmt(AbstractGoParser.SwitchStmtContext ctx) { }

	@Override public void enterPostStmt(AbstractGoParser.PostStmtContext ctx) { }
	@Override public void exitPostStmt(AbstractGoParser.PostStmtContext ctx) { }

	@Override public void enterForStmt(AbstractGoParser.ForStmtContext ctx) { }
	@Override public void exitForStmt(AbstractGoParser.ForStmtContext ctx) { }

	@Override public void enterTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx) { }
	@Override public void exitTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx) { }

	@Override public void enterRangeClause(AbstractGoParser.RangeClauseContext ctx) { }
	@Override public void exitRangeClause(AbstractGoParser.RangeClauseContext ctx) { }

	@Override public void enterOperand(AbstractGoParser.OperandContext ctx) { }
	@Override public void exitOperand(AbstractGoParser.OperandContext ctx) { }

	@Override public void enterArgumentList(AbstractGoParser.ArgumentListContext ctx) { }
	@Override public void exitArgumentList(AbstractGoParser.ArgumentListContext ctx) { }

	@Override public void enterTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx) { }
	@Override public void exitTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx) { }

	@Override public void enterTypeDecl(AbstractGoParser.TypeDeclContext ctx) { }
	@Override public void exitTypeDecl(AbstractGoParser.TypeDeclContext ctx) { }

	@Override public void enterUnaryExpr(AbstractGoParser.UnaryExprContext ctx) { }
	@Override public void exitUnaryExpr(AbstractGoParser.UnaryExprContext ctx) { }

	@Override public void enterChannel(AbstractGoParser.ChannelContext ctx) { }
	@Override public void exitChannel(AbstractGoParser.ChannelContext ctx) { }

	@Override public void enterLiteral(AbstractGoParser.LiteralContext ctx) { }
	@Override public void exitLiteral(AbstractGoParser.LiteralContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void exitEveryRule(ParserRuleContext<? extends Token> ctx) { }
	@Override public void visitTerminal(TerminalNode<? extends Token> node) { }
	@Override public void visitErrorNode(ErrorNode<? extends Token> node) { }
}