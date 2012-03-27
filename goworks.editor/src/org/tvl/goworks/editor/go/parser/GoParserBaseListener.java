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
	void enterMultExpr(GoParserBase.MultExprContext ctx);
	void exitMultExpr(GoParserBase.MultExprContext ctx);

	void enterChannelType(GoParserBase.ChannelTypeContext ctx);
	void exitChannelType(GoParserBase.ChannelTypeContext ctx);

	void enterMulAssignOp(GoParserBase.MulAssignOpContext ctx);
	void exitMulAssignOp(GoParserBase.MulAssignOpContext ctx);

	void enterPackageName(GoParserBase.PackageNameContext ctx);
	void exitPackageName(GoParserBase.PackageNameContext ctx);

	void enterReceiver(GoParserBase.ReceiverContext ctx);
	void exitReceiver(GoParserBase.ReceiverContext ctx);

	void enterArrayType(GoParserBase.ArrayTypeContext ctx);
	void exitArrayType(GoParserBase.ArrayTypeContext ctx);

	void enterExpressionList(GoParserBase.ExpressionListContext ctx);
	void exitExpressionList(GoParserBase.ExpressionListContext ctx);

	void enterTag(GoParserBase.TagContext ctx);
	void exitTag(GoParserBase.TagContext ctx);

	void enterFallthroughStmt(GoParserBase.FallthroughStmtContext ctx);
	void exitFallthroughStmt(GoParserBase.FallthroughStmtContext ctx);

	void enterSelectorExpr(GoParserBase.SelectorExprContext ctx);
	void exitSelectorExpr(GoParserBase.SelectorExprContext ctx);

	void enterParameterList(GoParserBase.ParameterListContext ctx);
	void exitParameterList(GoParserBase.ParameterListContext ctx);

	void enterReceiverType(GoParserBase.ReceiverTypeContext ctx);
	void exitReceiverType(GoParserBase.ReceiverTypeContext ctx);

	void enterFieldName(GoParserBase.FieldNameContext ctx);
	void exitFieldName(GoParserBase.FieldNameContext ctx);

	void enterIfStmt(GoParserBase.IfStmtContext ctx);
	void exitIfStmt(GoParserBase.IfStmtContext ctx);

	void enterMethodName(GoParserBase.MethodNameContext ctx);
	void exitMethodName(GoParserBase.MethodNameContext ctx);

	void enterSignature(GoParserBase.SignatureContext ctx);
	void exitSignature(GoParserBase.SignatureContext ctx);

	void enterMapType(GoParserBase.MapTypeContext ctx);
	void exitMapType(GoParserBase.MapTypeContext ctx);

	void enterElement(GoParserBase.ElementContext ctx);
	void exitElement(GoParserBase.ElementContext ctx);

	void enterCallExpr(GoParserBase.CallExprContext ctx);
	void exitCallExpr(GoParserBase.CallExprContext ctx);

	void enterTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx);
	void exitTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx);

	void enterExprCaseClause(GoParserBase.ExprCaseClauseContext ctx);
	void exitExprCaseClause(GoParserBase.ExprCaseClauseContext ctx);

	void enterTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx);
	void exitTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx);

	void enterFunctionLiteral(GoParserBase.FunctionLiteralContext ctx);
	void exitFunctionLiteral(GoParserBase.FunctionLiteralContext ctx);

	void enterOrExpr(GoParserBase.OrExprContext ctx);
	void exitOrExpr(GoParserBase.OrExprContext ctx);

	void enterRecvExpr(GoParserBase.RecvExprContext ctx);
	void exitRecvExpr(GoParserBase.RecvExprContext ctx);

	void enterTopLevelDecl(GoParserBase.TopLevelDeclContext ctx);
	void exitTopLevelDecl(GoParserBase.TopLevelDeclContext ctx);

	void enterMethodSpec(GoParserBase.MethodSpecContext ctx);
	void exitMethodSpec(GoParserBase.MethodSpecContext ctx);

	void enterConstSpec(GoParserBase.ConstSpecContext ctx);
	void exitConstSpec(GoParserBase.ConstSpecContext ctx);

	void enterCompositeLiteral(GoParserBase.CompositeLiteralContext ctx);
	void exitCompositeLiteral(GoParserBase.CompositeLiteralContext ctx);

	void enterForClause(GoParserBase.ForClauseContext ctx);
	void exitForClause(GoParserBase.ForClauseContext ctx);

	void enterShortVarDecl(GoParserBase.ShortVarDeclContext ctx);
	void exitShortVarDecl(GoParserBase.ShortVarDeclContext ctx);

	void enterGotoStmt(GoParserBase.GotoStmtContext ctx);
	void exitGotoStmt(GoParserBase.GotoStmtContext ctx);

	void enterArrayLength(GoParserBase.ArrayLengthContext ctx);
	void exitArrayLength(GoParserBase.ArrayLengthContext ctx);

	void enterInterfaceType(GoParserBase.InterfaceTypeContext ctx);
	void exitInterfaceType(GoParserBase.InterfaceTypeContext ctx);

	void enterConversion(GoParserBase.ConversionContext ctx);
	void exitConversion(GoParserBase.ConversionContext ctx);

	void enterBlock(GoParserBase.BlockContext ctx);
	void exitBlock(GoParserBase.BlockContext ctx);

	void enterBreakStmt(GoParserBase.BreakStmtContext ctx);
	void exitBreakStmt(GoParserBase.BreakStmtContext ctx);

	void enterEmptyStmt(GoParserBase.EmptyStmtContext ctx);
	void exitEmptyStmt(GoParserBase.EmptyStmtContext ctx);

	void enterFunctionType(GoParserBase.FunctionTypeContext ctx);
	void exitFunctionType(GoParserBase.FunctionTypeContext ctx);

	void enterBaseType(GoParserBase.BaseTypeContext ctx);
	void exitBaseType(GoParserBase.BaseTypeContext ctx);

	void enterOperandExpr(GoParserBase.OperandExprContext ctx);
	void exitOperandExpr(GoParserBase.OperandExprContext ctx);

	void enterFieldDecl(GoParserBase.FieldDeclContext ctx);
	void exitFieldDecl(GoParserBase.FieldDeclContext ctx);

	void enterExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx);
	void exitExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx);

	void enterGoStmt(GoParserBase.GoStmtContext ctx);
	void exitGoStmt(GoParserBase.GoStmtContext ctx);

	void enterParameterDecl(GoParserBase.ParameterDeclContext ctx);
	void exitParameterDecl(GoParserBase.ParameterDeclContext ctx);

	void enterBasicLiteral(GoParserBase.BasicLiteralContext ctx);
	void exitBasicLiteral(GoParserBase.BasicLiteralContext ctx);

	void enterExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx);
	void exitExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx);

	void enterTypeLiteral(GoParserBase.TypeLiteralContext ctx);
	void exitTypeLiteral(GoParserBase.TypeLiteralContext ctx);

	void enterSelectStmt(GoParserBase.SelectStmtContext ctx);
	void exitSelectStmt(GoParserBase.SelectStmtContext ctx);

	void enterImportSpec(GoParserBase.ImportSpecContext ctx);
	void exitImportSpec(GoParserBase.ImportSpecContext ctx);

	void enterTypeName(GoParserBase.TypeNameContext ctx);
	void exitTypeName(GoParserBase.TypeNameContext ctx);

	void enterLiteralType(GoParserBase.LiteralTypeContext ctx);
	void exitLiteralType(GoParserBase.LiteralTypeContext ctx);

	void enterAssignment(GoParserBase.AssignmentContext ctx);
	void exitAssignment(GoParserBase.AssignmentContext ctx);

	void enterAssignOp(GoParserBase.AssignOpContext ctx);
	void exitAssignOp(GoParserBase.AssignOpContext ctx);

	void enterRecvStmt(GoParserBase.RecvStmtContext ctx);
	void exitRecvStmt(GoParserBase.RecvStmtContext ctx);

	void enterTypeSpec(GoParserBase.TypeSpecContext ctx);
	void exitTypeSpec(GoParserBase.TypeSpecContext ctx);

	void enterPackageClause(GoParserBase.PackageClauseContext ctx);
	void exitPackageClause(GoParserBase.PackageClauseContext ctx);

	void enterBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx);
	void exitBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx);

	void enterLiteralValue(GoParserBase.LiteralValueContext ctx);
	void exitLiteralValue(GoParserBase.LiteralValueContext ctx);

	void enterIndexExpr(GoParserBase.IndexExprContext ctx);
	void exitIndexExpr(GoParserBase.IndexExprContext ctx);

	void enterVarSpec(GoParserBase.VarSpecContext ctx);
	void exitVarSpec(GoParserBase.VarSpecContext ctx);

	void enterBody(GoParserBase.BodyContext ctx);
	void exitBody(GoParserBase.BodyContext ctx);

	void enterCommClause(GoParserBase.CommClauseContext ctx);
	void exitCommClause(GoParserBase.CommClauseContext ctx);

	void enterQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx);
	void exitQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx);

	void enterReturnStmt(GoParserBase.ReturnStmtContext ctx);
	void exitReturnStmt(GoParserBase.ReturnStmtContext ctx);

	void enterSimpleStmt(GoParserBase.SimpleStmtContext ctx);
	void exitSimpleStmt(GoParserBase.SimpleStmtContext ctx);

	void enterTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx);
	void exitTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx);

	void enterType(GoParserBase.TypeContext ctx);
	void exitType(GoParserBase.TypeContext ctx);

	void enterInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx);
	void exitInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx);

	void enterContinueStmt(GoParserBase.ContinueStmtContext ctx);
	void exitContinueStmt(GoParserBase.ContinueStmtContext ctx);

	void enterValue(GoParserBase.ValueContext ctx);
	void exitValue(GoParserBase.ValueContext ctx);

	void enterMethodDecl(GoParserBase.MethodDeclContext ctx);
	void exitMethodDecl(GoParserBase.MethodDeclContext ctx);

	void enterLabeledStmt(GoParserBase.LabeledStmtContext ctx);
	void exitLabeledStmt(GoParserBase.LabeledStmtContext ctx);

	void enterParameters(GoParserBase.ParametersContext ctx);
	void exitParameters(GoParserBase.ParametersContext ctx);

	void enterDeferStmt(GoParserBase.DeferStmtContext ctx);
	void exitDeferStmt(GoParserBase.DeferStmtContext ctx);

	void enterKey(GoParserBase.KeyContext ctx);
	void exitKey(GoParserBase.KeyContext ctx);

	void enterDeclaration(GoParserBase.DeclarationContext ctx);
	void exitDeclaration(GoParserBase.DeclarationContext ctx);

	void enterCommCase(GoParserBase.CommCaseContext ctx);
	void exitCommCase(GoParserBase.CommCaseContext ctx);

	void enterBuiltinArgs(GoParserBase.BuiltinArgsContext ctx);
	void exitBuiltinArgs(GoParserBase.BuiltinArgsContext ctx);

	void enterCondition(GoParserBase.ConditionContext ctx);
	void exitCondition(GoParserBase.ConditionContext ctx);

	void enterConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx);
	void exitConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx);

	void enterLabel(GoParserBase.LabelContext ctx);
	void exitLabel(GoParserBase.LabelContext ctx);

	void enterElementType(GoParserBase.ElementTypeContext ctx);
	void exitElementType(GoParserBase.ElementTypeContext ctx);

	void enterFunctionDecl(GoParserBase.FunctionDeclContext ctx);
	void exitFunctionDecl(GoParserBase.FunctionDeclContext ctx);

	void enterStatement(GoParserBase.StatementContext ctx);
	void exitStatement(GoParserBase.StatementContext ctx);

	void enterPointerType(GoParserBase.PointerTypeContext ctx);
	void exitPointerType(GoParserBase.PointerTypeContext ctx);

	void enterAddAssignOp(GoParserBase.AddAssignOpContext ctx);
	void exitAddAssignOp(GoParserBase.AddAssignOpContext ctx);

	void enterSourceFile(GoParserBase.SourceFileContext ctx);
	void exitSourceFile(GoParserBase.SourceFileContext ctx);

	void enterSliceExpr(GoParserBase.SliceExprContext ctx);
	void exitSliceExpr(GoParserBase.SliceExprContext ctx);

	void enterBaseTypeName(GoParserBase.BaseTypeNameContext ctx);
	void exitBaseTypeName(GoParserBase.BaseTypeNameContext ctx);

	void enterMethodExpr(GoParserBase.MethodExprContext ctx);
	void exitMethodExpr(GoParserBase.MethodExprContext ctx);

	void enterElementIndex(GoParserBase.ElementIndexContext ctx);
	void exitElementIndex(GoParserBase.ElementIndexContext ctx);

	void enterTypeList(GoParserBase.TypeListContext ctx);
	void exitTypeList(GoParserBase.TypeListContext ctx);

	void enterIncDecStmt(GoParserBase.IncDecStmtContext ctx);
	void exitIncDecStmt(GoParserBase.IncDecStmtContext ctx);

	void enterBuiltinCall(GoParserBase.BuiltinCallContext ctx);
	void exitBuiltinCall(GoParserBase.BuiltinCallContext ctx);

	void enterConstDecl(GoParserBase.ConstDeclContext ctx);
	void exitConstDecl(GoParserBase.ConstDeclContext ctx);

	void enterResult(GoParserBase.ResultContext ctx);
	void exitResult(GoParserBase.ResultContext ctx);

	void enterAndExpr(GoParserBase.AndExprContext ctx);
	void exitAndExpr(GoParserBase.AndExprContext ctx);

	void enterStructType(GoParserBase.StructTypeContext ctx);
	void exitStructType(GoParserBase.StructTypeContext ctx);

	void enterVarDecl(GoParserBase.VarDeclContext ctx);
	void exitVarDecl(GoParserBase.VarDeclContext ctx);

	void enterInitStmt(GoParserBase.InitStmtContext ctx);
	void exitInitStmt(GoParserBase.InitStmtContext ctx);

	void enterIdentifierList(GoParserBase.IdentifierListContext ctx);
	void exitIdentifierList(GoParserBase.IdentifierListContext ctx);

	void enterSliceType(GoParserBase.SliceTypeContext ctx);
	void exitSliceType(GoParserBase.SliceTypeContext ctx);

	void enterCompareExpr(GoParserBase.CompareExprContext ctx);
	void exitCompareExpr(GoParserBase.CompareExprContext ctx);

	void enterImportDecl(GoParserBase.ImportDeclContext ctx);
	void exitImportDecl(GoParserBase.ImportDeclContext ctx);

	void enterElementList(GoParserBase.ElementListContext ctx);
	void exitElementList(GoParserBase.ElementListContext ctx);

	void enterKeyType(GoParserBase.KeyTypeContext ctx);
	void exitKeyType(GoParserBase.KeyTypeContext ctx);

	void enterImportPath(GoParserBase.ImportPathContext ctx);
	void exitImportPath(GoParserBase.ImportPathContext ctx);

	void enterAnonymousField(GoParserBase.AnonymousFieldContext ctx);
	void exitAnonymousField(GoParserBase.AnonymousFieldContext ctx);

	void enterAddExpr(GoParserBase.AddExprContext ctx);
	void exitAddExpr(GoParserBase.AddExprContext ctx);

	void enterExpressionStmt(GoParserBase.ExpressionStmtContext ctx);
	void exitExpressionStmt(GoParserBase.ExpressionStmtContext ctx);

	void enterSendStmt(GoParserBase.SendStmtContext ctx);
	void exitSendStmt(GoParserBase.SendStmtContext ctx);

	void enterSwitchStmt(GoParserBase.SwitchStmtContext ctx);
	void exitSwitchStmt(GoParserBase.SwitchStmtContext ctx);

	void enterPostStmt(GoParserBase.PostStmtContext ctx);
	void exitPostStmt(GoParserBase.PostStmtContext ctx);

	void enterForStmt(GoParserBase.ForStmtContext ctx);
	void exitForStmt(GoParserBase.ForStmtContext ctx);

	void enterTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx);
	void exitTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx);

	void enterRangeClause(GoParserBase.RangeClauseContext ctx);
	void exitRangeClause(GoParserBase.RangeClauseContext ctx);

	void enterOperand(GoParserBase.OperandContext ctx);
	void exitOperand(GoParserBase.OperandContext ctx);

	void enterArgumentList(GoParserBase.ArgumentListContext ctx);
	void exitArgumentList(GoParserBase.ArgumentListContext ctx);

	void enterTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx);
	void exitTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx);

	void enterTypeDecl(GoParserBase.TypeDeclContext ctx);
	void exitTypeDecl(GoParserBase.TypeDeclContext ctx);

	void enterUnaryExpr(GoParserBase.UnaryExprContext ctx);
	void exitUnaryExpr(GoParserBase.UnaryExprContext ctx);

	void enterChannel(GoParserBase.ChannelContext ctx);
	void exitChannel(GoParserBase.ChannelContext ctx);

	void enterLiteral(GoParserBase.LiteralContext ctx);
	void exitLiteral(GoParserBase.LiteralContext ctx);
}