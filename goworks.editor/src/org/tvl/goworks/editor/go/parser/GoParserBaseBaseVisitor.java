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
import org.antlr.v4.runtime.ParserRuleContext;

public class GoParserBaseBaseVisitor<Result> extends AbstractParseTreeVisitor<Token, Result> implements GoParserBaseVisitor<Token, Result> {
	@Override public Result visitMultExpr(GoParserBase.MultExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitChannelType(GoParserBase.ChannelTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMulAssignOp(GoParserBase.MulAssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPackageName(GoParserBase.PackageNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReceiver(GoParserBase.ReceiverContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArrayType(GoParserBase.ArrayTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExpressionList(GoParserBase.ExpressionListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTag(GoParserBase.TagContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFallthroughStmt(GoParserBase.FallthroughStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSelectorExpr(GoParserBase.SelectorExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameterList(GoParserBase.ParameterListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReceiverType(GoParserBase.ReceiverTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFieldName(GoParserBase.FieldNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIfStmt(GoParserBase.IfStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodName(GoParserBase.MethodNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSignature(GoParserBase.SignatureContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMapType(GoParserBase.MapTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElement(GoParserBase.ElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCallExpr(GoParserBase.CallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprCaseClause(GoParserBase.ExprCaseClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionLiteral(GoParserBase.FunctionLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOrExpr(GoParserBase.OrExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRecvExpr(GoParserBase.RecvExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTopLevelDecl(GoParserBase.TopLevelDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodSpec(GoParserBase.MethodSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConstSpec(GoParserBase.ConstSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCompositeLiteral(GoParserBase.CompositeLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitForClause(GoParserBase.ForClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitShortVarDecl(GoParserBase.ShortVarDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGotoStmt(GoParserBase.GotoStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArrayLength(GoParserBase.ArrayLengthContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInterfaceType(GoParserBase.InterfaceTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConversion(GoParserBase.ConversionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBlock(GoParserBase.BlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBreakStmt(GoParserBase.BreakStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitEmptyStmt(GoParserBase.EmptyStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionType(GoParserBase.FunctionTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBaseType(GoParserBase.BaseTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOperandExpr(GoParserBase.OperandExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFieldDecl(GoParserBase.FieldDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGoStmt(GoParserBase.GoStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameterDecl(GoParserBase.ParameterDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBasicLiteral(GoParserBase.BasicLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeLiteral(GoParserBase.TypeLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSelectStmt(GoParserBase.SelectStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportSpec(GoParserBase.ImportSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeName(GoParserBase.TypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteralType(GoParserBase.LiteralTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAssignment(GoParserBase.AssignmentContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAssignOp(GoParserBase.AssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRecvStmt(GoParserBase.RecvStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSpec(GoParserBase.TypeSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPackageClause(GoParserBase.PackageClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteralValue(GoParserBase.LiteralValueContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIndexExpr(GoParserBase.IndexExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitVarSpec(GoParserBase.VarSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBody(GoParserBase.BodyContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCommClause(GoParserBase.CommClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReturnStmt(GoParserBase.ReturnStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSimpleStmt(GoParserBase.SimpleStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitType(GoParserBase.TypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitContinueStmt(GoParserBase.ContinueStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitValue(GoParserBase.ValueContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodDecl(GoParserBase.MethodDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabeledStmt(GoParserBase.LabeledStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameters(GoParserBase.ParametersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDeferStmt(GoParserBase.DeferStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKey(GoParserBase.KeyContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDeclaration(GoParserBase.DeclarationContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCommCase(GoParserBase.CommCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinArgs(GoParserBase.BuiltinArgsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCondition(GoParserBase.ConditionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabel(GoParserBase.LabelContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementType(GoParserBase.ElementTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionDecl(GoParserBase.FunctionDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitStatement(GoParserBase.StatementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPointerType(GoParserBase.PointerTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAddAssignOp(GoParserBase.AddAssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSourceFile(GoParserBase.SourceFileContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSliceExpr(GoParserBase.SliceExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBaseTypeName(GoParserBase.BaseTypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodExpr(GoParserBase.MethodExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementIndex(GoParserBase.ElementIndexContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeList(GoParserBase.TypeListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIncDecStmt(GoParserBase.IncDecStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinCall(GoParserBase.BuiltinCallContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConstDecl(GoParserBase.ConstDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitResult(GoParserBase.ResultContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAndExpr(GoParserBase.AndExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitStructType(GoParserBase.StructTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitVarDecl(GoParserBase.VarDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInitStmt(GoParserBase.InitStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIdentifierList(GoParserBase.IdentifierListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSliceType(GoParserBase.SliceTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCompareExpr(GoParserBase.CompareExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportDecl(GoParserBase.ImportDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementList(GoParserBase.ElementListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKeyType(GoParserBase.KeyTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportPath(GoParserBase.ImportPathContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAnonymousField(GoParserBase.AnonymousFieldContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAddExpr(GoParserBase.AddExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExpressionStmt(GoParserBase.ExpressionStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSendStmt(GoParserBase.SendStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSwitchStmt(GoParserBase.SwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPostStmt(GoParserBase.PostStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitForStmt(GoParserBase.ForStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRangeClause(GoParserBase.RangeClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOperand(GoParserBase.OperandContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgumentList(GoParserBase.ArgumentListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeDecl(GoParserBase.TypeDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitUnaryExpr(GoParserBase.UnaryExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitChannel(GoParserBase.ChannelContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteral(GoParserBase.LiteralContext ctx) { return visitChildren(ctx); }
}