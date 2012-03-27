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

public class GoParserBaseVisitor<Result> extends AbstractParseTreeVisitor<Token, Result> implements GoParserVisitor<Token, Result> {
	@Override public Result visitMultExpr(AbstractGoParser.MultExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitChannelType(AbstractGoParser.ChannelTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMulAssignOp(AbstractGoParser.MulAssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPackageName(AbstractGoParser.PackageNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReceiver(AbstractGoParser.ReceiverContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArrayType(AbstractGoParser.ArrayTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExpressionList(AbstractGoParser.ExpressionListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTag(AbstractGoParser.TagContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSelectorExpr(AbstractGoParser.SelectorExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameterList(AbstractGoParser.ParameterListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReceiverType(AbstractGoParser.ReceiverTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFieldName(AbstractGoParser.FieldNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIfStmt(AbstractGoParser.IfStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodName(AbstractGoParser.MethodNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSignature(AbstractGoParser.SignatureContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMapType(AbstractGoParser.MapTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElement(AbstractGoParser.ElementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCallExpr(AbstractGoParser.CallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOrExpr(AbstractGoParser.OrExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRecvExpr(AbstractGoParser.RecvExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodSpec(AbstractGoParser.MethodSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConstSpec(AbstractGoParser.ConstSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitForClause(AbstractGoParser.ForClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGotoStmt(AbstractGoParser.GotoStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArrayLength(AbstractGoParser.ArrayLengthContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInterfaceType(AbstractGoParser.InterfaceTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConversion(AbstractGoParser.ConversionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBlock(AbstractGoParser.BlockContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBreakStmt(AbstractGoParser.BreakStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitEmptyStmt(AbstractGoParser.EmptyStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionType(AbstractGoParser.FunctionTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBaseType(AbstractGoParser.BaseTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOperandExpr(AbstractGoParser.OperandExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFieldDecl(AbstractGoParser.FieldDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitGoStmt(AbstractGoParser.GoStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameterDecl(AbstractGoParser.ParameterDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBasicLiteral(AbstractGoParser.BasicLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeLiteral(AbstractGoParser.TypeLiteralContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSelectStmt(AbstractGoParser.SelectStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportSpec(AbstractGoParser.ImportSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeName(AbstractGoParser.TypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteralType(AbstractGoParser.LiteralTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAssignment(AbstractGoParser.AssignmentContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAssignOp(AbstractGoParser.AssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRecvStmt(AbstractGoParser.RecvStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSpec(AbstractGoParser.TypeSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPackageClause(AbstractGoParser.PackageClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteralValue(AbstractGoParser.LiteralValueContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIndexExpr(AbstractGoParser.IndexExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitVarSpec(AbstractGoParser.VarSpecContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBody(AbstractGoParser.BodyContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCommClause(AbstractGoParser.CommClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx) { return visitChildren(ctx); }

	@Override public Result visitReturnStmt(AbstractGoParser.ReturnStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSimpleStmt(AbstractGoParser.SimpleStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitType(AbstractGoParser.TypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitContinueStmt(AbstractGoParser.ContinueStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitValue(AbstractGoParser.ValueContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodDecl(AbstractGoParser.MethodDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabeledStmt(AbstractGoParser.LabeledStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitParameters(AbstractGoParser.ParametersContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDeferStmt(AbstractGoParser.DeferStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKey(AbstractGoParser.KeyContext ctx) { return visitChildren(ctx); }

	@Override public Result visitDeclaration(AbstractGoParser.DeclarationContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCommCase(AbstractGoParser.CommCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCondition(AbstractGoParser.ConditionContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLabel(AbstractGoParser.LabelContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementType(AbstractGoParser.ElementTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitFunctionDecl(AbstractGoParser.FunctionDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitStatement(AbstractGoParser.StatementContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPointerType(AbstractGoParser.PointerTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAddAssignOp(AbstractGoParser.AddAssignOpContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSourceFile(AbstractGoParser.SourceFileContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSliceExpr(AbstractGoParser.SliceExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx) { return visitChildren(ctx); }

	@Override public Result visitMethodExpr(AbstractGoParser.MethodExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementIndex(AbstractGoParser.ElementIndexContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeList(AbstractGoParser.TypeListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIncDecStmt(AbstractGoParser.IncDecStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitBuiltinCall(AbstractGoParser.BuiltinCallContext ctx) { return visitChildren(ctx); }

	@Override public Result visitConstDecl(AbstractGoParser.ConstDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitResult(AbstractGoParser.ResultContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAndExpr(AbstractGoParser.AndExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitStructType(AbstractGoParser.StructTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitVarDecl(AbstractGoParser.VarDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitInitStmt(AbstractGoParser.InitStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitIdentifierList(AbstractGoParser.IdentifierListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSliceType(AbstractGoParser.SliceTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitCompareExpr(AbstractGoParser.CompareExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportDecl(AbstractGoParser.ImportDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitElementList(AbstractGoParser.ElementListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitKeyType(AbstractGoParser.KeyTypeContext ctx) { return visitChildren(ctx); }

	@Override public Result visitImportPath(AbstractGoParser.ImportPathContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAnonymousField(AbstractGoParser.AnonymousFieldContext ctx) { return visitChildren(ctx); }

	@Override public Result visitAddExpr(AbstractGoParser.AddExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSendStmt(AbstractGoParser.SendStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitSwitchStmt(AbstractGoParser.SwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitPostStmt(AbstractGoParser.PostStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitForStmt(AbstractGoParser.ForStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitRangeClause(AbstractGoParser.RangeClauseContext ctx) { return visitChildren(ctx); }

	@Override public Result visitOperand(AbstractGoParser.OperandContext ctx) { return visitChildren(ctx); }

	@Override public Result visitArgumentList(AbstractGoParser.ArgumentListContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx) { return visitChildren(ctx); }

	@Override public Result visitTypeDecl(AbstractGoParser.TypeDeclContext ctx) { return visitChildren(ctx); }

	@Override public Result visitUnaryExpr(AbstractGoParser.UnaryExprContext ctx) { return visitChildren(ctx); }

	@Override public Result visitChannel(AbstractGoParser.ChannelContext ctx) { return visitChildren(ctx); }

	@Override public Result visitLiteral(AbstractGoParser.LiteralContext ctx) { return visitChildren(ctx); }
}