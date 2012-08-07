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

public interface GoParserListener extends ParseTreeListener<Token> {
	void enterMultExpr(AbstractGoParser.MultExprContext ctx);
	void exitMultExpr(AbstractGoParser.MultExprContext ctx);

	void enterChannelType(AbstractGoParser.ChannelTypeContext ctx);
	void exitChannelType(AbstractGoParser.ChannelTypeContext ctx);

	void enterMulAssignOp(AbstractGoParser.MulAssignOpContext ctx);
	void exitMulAssignOp(AbstractGoParser.MulAssignOpContext ctx);

	void enterPackageName(AbstractGoParser.PackageNameContext ctx);
	void exitPackageName(AbstractGoParser.PackageNameContext ctx);

	void enterReceiver(AbstractGoParser.ReceiverContext ctx);
	void exitReceiver(AbstractGoParser.ReceiverContext ctx);

	void enterArrayType(AbstractGoParser.ArrayTypeContext ctx);
	void exitArrayType(AbstractGoParser.ArrayTypeContext ctx);

	void enterExpressionList(AbstractGoParser.ExpressionListContext ctx);
	void exitExpressionList(AbstractGoParser.ExpressionListContext ctx);

	void enterTag(AbstractGoParser.TagContext ctx);
	void exitTag(AbstractGoParser.TagContext ctx);

	void enterFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx);
	void exitFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx);

	void enterSelectorExpr(AbstractGoParser.SelectorExprContext ctx);
	void exitSelectorExpr(AbstractGoParser.SelectorExprContext ctx);

	void enterParameterList(AbstractGoParser.ParameterListContext ctx);
	void exitParameterList(AbstractGoParser.ParameterListContext ctx);

	void enterReceiverType(AbstractGoParser.ReceiverTypeContext ctx);
	void exitReceiverType(AbstractGoParser.ReceiverTypeContext ctx);

	void enterFieldName(AbstractGoParser.FieldNameContext ctx);
	void exitFieldName(AbstractGoParser.FieldNameContext ctx);

	void enterIfStmt(AbstractGoParser.IfStmtContext ctx);
	void exitIfStmt(AbstractGoParser.IfStmtContext ctx);

	void enterMethodName(AbstractGoParser.MethodNameContext ctx);
	void exitMethodName(AbstractGoParser.MethodNameContext ctx);

	void enterSignature(AbstractGoParser.SignatureContext ctx);
	void exitSignature(AbstractGoParser.SignatureContext ctx);

	void enterMapType(AbstractGoParser.MapTypeContext ctx);
	void exitMapType(AbstractGoParser.MapTypeContext ctx);

	void enterElement(AbstractGoParser.ElementContext ctx);
	void exitElement(AbstractGoParser.ElementContext ctx);

	void enterCallExpr(AbstractGoParser.CallExprContext ctx);
	void exitCallExpr(AbstractGoParser.CallExprContext ctx);

	void enterTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx);
	void exitTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx);

	void enterExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx);
	void exitExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx);

	void enterTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx);
	void exitTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx);

	void enterFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx);
	void exitFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx);

	void enterOrExpr(AbstractGoParser.OrExprContext ctx);
	void exitOrExpr(AbstractGoParser.OrExprContext ctx);

	void enterRecvExpr(AbstractGoParser.RecvExprContext ctx);
	void exitRecvExpr(AbstractGoParser.RecvExprContext ctx);

	void enterTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx);
	void exitTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx);

	void enterMethodSpec(AbstractGoParser.MethodSpecContext ctx);
	void exitMethodSpec(AbstractGoParser.MethodSpecContext ctx);

	void enterConstSpec(AbstractGoParser.ConstSpecContext ctx);
	void exitConstSpec(AbstractGoParser.ConstSpecContext ctx);

	void enterCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx);
	void exitCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx);

	void enterForClause(AbstractGoParser.ForClauseContext ctx);
	void exitForClause(AbstractGoParser.ForClauseContext ctx);

	void enterShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx);
	void exitShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx);

	void enterGotoStmt(AbstractGoParser.GotoStmtContext ctx);
	void exitGotoStmt(AbstractGoParser.GotoStmtContext ctx);

	void enterArrayLength(AbstractGoParser.ArrayLengthContext ctx);
	void exitArrayLength(AbstractGoParser.ArrayLengthContext ctx);

	void enterInterfaceType(AbstractGoParser.InterfaceTypeContext ctx);
	void exitInterfaceType(AbstractGoParser.InterfaceTypeContext ctx);

	void enterConversion(AbstractGoParser.ConversionContext ctx);
	void exitConversion(AbstractGoParser.ConversionContext ctx);

	void enterBlock(AbstractGoParser.BlockContext ctx);
	void exitBlock(AbstractGoParser.BlockContext ctx);

	void enterBreakStmt(AbstractGoParser.BreakStmtContext ctx);
	void exitBreakStmt(AbstractGoParser.BreakStmtContext ctx);

	void enterEmptyStmt(AbstractGoParser.EmptyStmtContext ctx);
	void exitEmptyStmt(AbstractGoParser.EmptyStmtContext ctx);

	void enterFunctionType(AbstractGoParser.FunctionTypeContext ctx);
	void exitFunctionType(AbstractGoParser.FunctionTypeContext ctx);

	void enterBaseType(AbstractGoParser.BaseTypeContext ctx);
	void exitBaseType(AbstractGoParser.BaseTypeContext ctx);

	void enterOperandExpr(AbstractGoParser.OperandExprContext ctx);
	void exitOperandExpr(AbstractGoParser.OperandExprContext ctx);

	void enterFieldDecl(AbstractGoParser.FieldDeclContext ctx);
	void exitFieldDecl(AbstractGoParser.FieldDeclContext ctx);

	void enterExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx);
	void exitExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx);

	void enterGoStmt(AbstractGoParser.GoStmtContext ctx);
	void exitGoStmt(AbstractGoParser.GoStmtContext ctx);

	void enterParameterDecl(AbstractGoParser.ParameterDeclContext ctx);
	void exitParameterDecl(AbstractGoParser.ParameterDeclContext ctx);

	void enterBasicLiteral(AbstractGoParser.BasicLiteralContext ctx);
	void exitBasicLiteral(AbstractGoParser.BasicLiteralContext ctx);

	void enterExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx);
	void exitExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx);

	void enterTypeLiteral(AbstractGoParser.TypeLiteralContext ctx);
	void exitTypeLiteral(AbstractGoParser.TypeLiteralContext ctx);

	void enterSelectStmt(AbstractGoParser.SelectStmtContext ctx);
	void exitSelectStmt(AbstractGoParser.SelectStmtContext ctx);

	void enterImportSpec(AbstractGoParser.ImportSpecContext ctx);
	void exitImportSpec(AbstractGoParser.ImportSpecContext ctx);

	void enterTypeName(AbstractGoParser.TypeNameContext ctx);
	void exitTypeName(AbstractGoParser.TypeNameContext ctx);

	void enterLiteralType(AbstractGoParser.LiteralTypeContext ctx);
	void exitLiteralType(AbstractGoParser.LiteralTypeContext ctx);

	void enterAssignment(AbstractGoParser.AssignmentContext ctx);
	void exitAssignment(AbstractGoParser.AssignmentContext ctx);

	void enterAssignOp(AbstractGoParser.AssignOpContext ctx);
	void exitAssignOp(AbstractGoParser.AssignOpContext ctx);

	void enterRecvStmt(AbstractGoParser.RecvStmtContext ctx);
	void exitRecvStmt(AbstractGoParser.RecvStmtContext ctx);

	void enterTypeSpec(AbstractGoParser.TypeSpecContext ctx);
	void exitTypeSpec(AbstractGoParser.TypeSpecContext ctx);

	void enterPackageClause(AbstractGoParser.PackageClauseContext ctx);
	void exitPackageClause(AbstractGoParser.PackageClauseContext ctx);

	void enterBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx);
	void exitBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx);

	void enterLiteralValue(AbstractGoParser.LiteralValueContext ctx);
	void exitLiteralValue(AbstractGoParser.LiteralValueContext ctx);

	void enterIndexExpr(AbstractGoParser.IndexExprContext ctx);
	void exitIndexExpr(AbstractGoParser.IndexExprContext ctx);

	void enterVarSpec(AbstractGoParser.VarSpecContext ctx);
	void exitVarSpec(AbstractGoParser.VarSpecContext ctx);

	void enterBody(AbstractGoParser.BodyContext ctx);
	void exitBody(AbstractGoParser.BodyContext ctx);

	void enterCommClause(AbstractGoParser.CommClauseContext ctx);
	void exitCommClause(AbstractGoParser.CommClauseContext ctx);

	void enterQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx);
	void exitQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx);

	void enterReturnStmt(AbstractGoParser.ReturnStmtContext ctx);
	void exitReturnStmt(AbstractGoParser.ReturnStmtContext ctx);

	void enterSimpleStmt(AbstractGoParser.SimpleStmtContext ctx);
	void exitSimpleStmt(AbstractGoParser.SimpleStmtContext ctx);

	void enterTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx);
	void exitTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx);

	void enterType(AbstractGoParser.TypeContext ctx);
	void exitType(AbstractGoParser.TypeContext ctx);

	void enterInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx);
	void exitInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx);

	void enterContinueStmt(AbstractGoParser.ContinueStmtContext ctx);
	void exitContinueStmt(AbstractGoParser.ContinueStmtContext ctx);

	void enterValue(AbstractGoParser.ValueContext ctx);
	void exitValue(AbstractGoParser.ValueContext ctx);

	void enterMethodDecl(AbstractGoParser.MethodDeclContext ctx);
	void exitMethodDecl(AbstractGoParser.MethodDeclContext ctx);

	void enterLabeledStmt(AbstractGoParser.LabeledStmtContext ctx);
	void exitLabeledStmt(AbstractGoParser.LabeledStmtContext ctx);

	void enterParameters(AbstractGoParser.ParametersContext ctx);
	void exitParameters(AbstractGoParser.ParametersContext ctx);

	void enterDeferStmt(AbstractGoParser.DeferStmtContext ctx);
	void exitDeferStmt(AbstractGoParser.DeferStmtContext ctx);

	void enterSourceFileBody(AbstractGoParser.SourceFileBodyContext ctx);
	void exitSourceFileBody(AbstractGoParser.SourceFileBodyContext ctx);

	void enterKey(AbstractGoParser.KeyContext ctx);
	void exitKey(AbstractGoParser.KeyContext ctx);

	void enterDeclaration(AbstractGoParser.DeclarationContext ctx);
	void exitDeclaration(AbstractGoParser.DeclarationContext ctx);

	void enterCommCase(AbstractGoParser.CommCaseContext ctx);
	void exitCommCase(AbstractGoParser.CommCaseContext ctx);

	void enterBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx);
	void exitBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx);

	void enterCondition(AbstractGoParser.ConditionContext ctx);
	void exitCondition(AbstractGoParser.ConditionContext ctx);

	void enterConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx);
	void exitConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx);

	void enterLabel(AbstractGoParser.LabelContext ctx);
	void exitLabel(AbstractGoParser.LabelContext ctx);

	void enterElementType(AbstractGoParser.ElementTypeContext ctx);
	void exitElementType(AbstractGoParser.ElementTypeContext ctx);

	void enterFunctionDecl(AbstractGoParser.FunctionDeclContext ctx);
	void exitFunctionDecl(AbstractGoParser.FunctionDeclContext ctx);

	void enterStatement(AbstractGoParser.StatementContext ctx);
	void exitStatement(AbstractGoParser.StatementContext ctx);

	void enterPointerType(AbstractGoParser.PointerTypeContext ctx);
	void exitPointerType(AbstractGoParser.PointerTypeContext ctx);

	void enterAddAssignOp(AbstractGoParser.AddAssignOpContext ctx);
	void exitAddAssignOp(AbstractGoParser.AddAssignOpContext ctx);

	void enterSourceFile(AbstractGoParser.SourceFileContext ctx);
	void exitSourceFile(AbstractGoParser.SourceFileContext ctx);

	void enterSliceExpr(AbstractGoParser.SliceExprContext ctx);
	void exitSliceExpr(AbstractGoParser.SliceExprContext ctx);

	void enterBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx);
	void exitBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx);

	void enterMethodExpr(AbstractGoParser.MethodExprContext ctx);
	void exitMethodExpr(AbstractGoParser.MethodExprContext ctx);

	void enterElementIndex(AbstractGoParser.ElementIndexContext ctx);
	void exitElementIndex(AbstractGoParser.ElementIndexContext ctx);

	void enterTypeList(AbstractGoParser.TypeListContext ctx);
	void exitTypeList(AbstractGoParser.TypeListContext ctx);

	void enterIncDecStmt(AbstractGoParser.IncDecStmtContext ctx);
	void exitIncDecStmt(AbstractGoParser.IncDecStmtContext ctx);

	void enterBuiltinCall(AbstractGoParser.BuiltinCallContext ctx);
	void exitBuiltinCall(AbstractGoParser.BuiltinCallContext ctx);

	void enterConstDecl(AbstractGoParser.ConstDeclContext ctx);
	void exitConstDecl(AbstractGoParser.ConstDeclContext ctx);

	void enterResult(AbstractGoParser.ResultContext ctx);
	void exitResult(AbstractGoParser.ResultContext ctx);

	void enterAndExpr(AbstractGoParser.AndExprContext ctx);
	void exitAndExpr(AbstractGoParser.AndExprContext ctx);

	void enterStructType(AbstractGoParser.StructTypeContext ctx);
	void exitStructType(AbstractGoParser.StructTypeContext ctx);

	void enterVarDecl(AbstractGoParser.VarDeclContext ctx);
	void exitVarDecl(AbstractGoParser.VarDeclContext ctx);

	void enterInitStmt(AbstractGoParser.InitStmtContext ctx);
	void exitInitStmt(AbstractGoParser.InitStmtContext ctx);

	void enterIdentifierList(AbstractGoParser.IdentifierListContext ctx);
	void exitIdentifierList(AbstractGoParser.IdentifierListContext ctx);

	void enterSliceType(AbstractGoParser.SliceTypeContext ctx);
	void exitSliceType(AbstractGoParser.SliceTypeContext ctx);

	void enterCompareExpr(AbstractGoParser.CompareExprContext ctx);
	void exitCompareExpr(AbstractGoParser.CompareExprContext ctx);

	void enterImportDecl(AbstractGoParser.ImportDeclContext ctx);
	void exitImportDecl(AbstractGoParser.ImportDeclContext ctx);

	void enterElementList(AbstractGoParser.ElementListContext ctx);
	void exitElementList(AbstractGoParser.ElementListContext ctx);

	void enterKeyType(AbstractGoParser.KeyTypeContext ctx);
	void exitKeyType(AbstractGoParser.KeyTypeContext ctx);

	void enterImportPath(AbstractGoParser.ImportPathContext ctx);
	void exitImportPath(AbstractGoParser.ImportPathContext ctx);

	void enterAnonymousField(AbstractGoParser.AnonymousFieldContext ctx);
	void exitAnonymousField(AbstractGoParser.AnonymousFieldContext ctx);

	void enterAddExpr(AbstractGoParser.AddExprContext ctx);
	void exitAddExpr(AbstractGoParser.AddExprContext ctx);

	void enterExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx);
	void exitExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx);

	void enterSendStmt(AbstractGoParser.SendStmtContext ctx);
	void exitSendStmt(AbstractGoParser.SendStmtContext ctx);

	void enterSwitchStmt(AbstractGoParser.SwitchStmtContext ctx);
	void exitSwitchStmt(AbstractGoParser.SwitchStmtContext ctx);

	void enterPostStmt(AbstractGoParser.PostStmtContext ctx);
	void exitPostStmt(AbstractGoParser.PostStmtContext ctx);

	void enterForStmt(AbstractGoParser.ForStmtContext ctx);
	void exitForStmt(AbstractGoParser.ForStmtContext ctx);

	void enterTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx);
	void exitTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx);

	void enterRangeClause(AbstractGoParser.RangeClauseContext ctx);
	void exitRangeClause(AbstractGoParser.RangeClauseContext ctx);

	void enterOperand(AbstractGoParser.OperandContext ctx);
	void exitOperand(AbstractGoParser.OperandContext ctx);

	void enterArgumentList(AbstractGoParser.ArgumentListContext ctx);
	void exitArgumentList(AbstractGoParser.ArgumentListContext ctx);

	void enterTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx);
	void exitTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx);

	void enterTypeDecl(AbstractGoParser.TypeDeclContext ctx);
	void exitTypeDecl(AbstractGoParser.TypeDeclContext ctx);

	void enterUnaryExpr(AbstractGoParser.UnaryExprContext ctx);
	void exitUnaryExpr(AbstractGoParser.UnaryExprContext ctx);

	void enterChannel(AbstractGoParser.ChannelContext ctx);
	void exitChannel(AbstractGoParser.ChannelContext ctx);

	void enterLiteral(AbstractGoParser.LiteralContext ctx);
	void exitLiteral(AbstractGoParser.LiteralContext ctx);
}