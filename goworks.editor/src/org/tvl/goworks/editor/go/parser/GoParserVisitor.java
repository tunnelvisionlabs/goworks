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

public interface GoParserVisitor<Symbol extends Token, Result> extends ParseTreeVisitor<Symbol, Result> {
	Result visitMultExpr(AbstractGoParser.MultExprContext ctx);

	Result visitChannelType(AbstractGoParser.ChannelTypeContext ctx);

	Result visitMulAssignOp(AbstractGoParser.MulAssignOpContext ctx);

	Result visitPackageName(AbstractGoParser.PackageNameContext ctx);

	Result visitReceiver(AbstractGoParser.ReceiverContext ctx);

	Result visitArrayType(AbstractGoParser.ArrayTypeContext ctx);

	Result visitExpressionList(AbstractGoParser.ExpressionListContext ctx);

	Result visitTag(AbstractGoParser.TagContext ctx);

	Result visitElementNameOrIndex(AbstractGoParser.ElementNameOrIndexContext ctx);

	Result visitFallthroughStmt(AbstractGoParser.FallthroughStmtContext ctx);

	Result visitSelectorExpr(AbstractGoParser.SelectorExprContext ctx);

	Result visitParameterList(AbstractGoParser.ParameterListContext ctx);

	Result visitReceiverType(AbstractGoParser.ReceiverTypeContext ctx);

	Result visitIfStmt(AbstractGoParser.IfStmtContext ctx);

	Result visitMethodName(AbstractGoParser.MethodNameContext ctx);

	Result visitSignature(AbstractGoParser.SignatureContext ctx);

	Result visitMapType(AbstractGoParser.MapTypeContext ctx);

	Result visitElement(AbstractGoParser.ElementContext ctx);

	Result visitCallExpr(AbstractGoParser.CallExprContext ctx);

	Result visitTypeCaseClause(AbstractGoParser.TypeCaseClauseContext ctx);

	Result visitExprCaseClause(AbstractGoParser.ExprCaseClauseContext ctx);

	Result visitTypeSwitchGuard(AbstractGoParser.TypeSwitchGuardContext ctx);

	Result visitFunctionLiteral(AbstractGoParser.FunctionLiteralContext ctx);

	Result visitOrExpr(AbstractGoParser.OrExprContext ctx);

	Result visitRecvExpr(AbstractGoParser.RecvExprContext ctx);

	Result visitTopLevelDecl(AbstractGoParser.TopLevelDeclContext ctx);

	Result visitMethodSpec(AbstractGoParser.MethodSpecContext ctx);

	Result visitConstSpec(AbstractGoParser.ConstSpecContext ctx);

	Result visitCompositeLiteral(AbstractGoParser.CompositeLiteralContext ctx);

	Result visitForClause(AbstractGoParser.ForClauseContext ctx);

	Result visitShortVarDecl(AbstractGoParser.ShortVarDeclContext ctx);

	Result visitGotoStmt(AbstractGoParser.GotoStmtContext ctx);

	Result visitArrayLength(AbstractGoParser.ArrayLengthContext ctx);

	Result visitInterfaceType(AbstractGoParser.InterfaceTypeContext ctx);

	Result visitConversion(AbstractGoParser.ConversionContext ctx);

	Result visitBlock(AbstractGoParser.BlockContext ctx);

	Result visitBreakStmt(AbstractGoParser.BreakStmtContext ctx);

	Result visitEmptyStmt(AbstractGoParser.EmptyStmtContext ctx);

	Result visitFunctionType(AbstractGoParser.FunctionTypeContext ctx);

	Result visitBaseType(AbstractGoParser.BaseTypeContext ctx);

	Result visitOperandExpr(AbstractGoParser.OperandExprContext ctx);

	Result visitFieldDecl(AbstractGoParser.FieldDeclContext ctx);

	Result visitExprSwitchStmt(AbstractGoParser.ExprSwitchStmtContext ctx);

	Result visitGoStmt(AbstractGoParser.GoStmtContext ctx);

	Result visitParameterDecl(AbstractGoParser.ParameterDeclContext ctx);

	Result visitBasicLiteral(AbstractGoParser.BasicLiteralContext ctx);

	Result visitExprSwitchCase(AbstractGoParser.ExprSwitchCaseContext ctx);

	Result visitTypeLiteral(AbstractGoParser.TypeLiteralContext ctx);

	Result visitSelectStmt(AbstractGoParser.SelectStmtContext ctx);

	Result visitImportSpec(AbstractGoParser.ImportSpecContext ctx);

	Result visitTypeName(AbstractGoParser.TypeNameContext ctx);

	Result visitLiteralType(AbstractGoParser.LiteralTypeContext ctx);

	Result visitAssignment(AbstractGoParser.AssignmentContext ctx);

	Result visitAssignOp(AbstractGoParser.AssignOpContext ctx);

	Result visitRecvStmt(AbstractGoParser.RecvStmtContext ctx);

	Result visitTypeSpec(AbstractGoParser.TypeSpecContext ctx);

	Result visitPackageClause(AbstractGoParser.PackageClauseContext ctx);

	Result visitBuiltinCallExpr(AbstractGoParser.BuiltinCallExprContext ctx);

	Result visitLiteralValue(AbstractGoParser.LiteralValueContext ctx);

	Result visitIndexExpr(AbstractGoParser.IndexExprContext ctx);

	Result visitVarSpec(AbstractGoParser.VarSpecContext ctx);

	Result visitBody(AbstractGoParser.BodyContext ctx);

	Result visitCommClause(AbstractGoParser.CommClauseContext ctx);

	Result visitQualifiedIdentifier(AbstractGoParser.QualifiedIdentifierContext ctx);

	Result visitReturnStmt(AbstractGoParser.ReturnStmtContext ctx);

	Result visitSimpleStmt(AbstractGoParser.SimpleStmtContext ctx);

	Result visitTypeAssertionExpr(AbstractGoParser.TypeAssertionExprContext ctx);

	Result visitType(AbstractGoParser.TypeContext ctx);

	Result visitInterfaceTypeName(AbstractGoParser.InterfaceTypeNameContext ctx);

	Result visitContinueStmt(AbstractGoParser.ContinueStmtContext ctx);

	Result visitValue(AbstractGoParser.ValueContext ctx);

	Result visitMethodDecl(AbstractGoParser.MethodDeclContext ctx);

	Result visitLabeledStmt(AbstractGoParser.LabeledStmtContext ctx);

	Result visitParameters(AbstractGoParser.ParametersContext ctx);

	Result visitDeferStmt(AbstractGoParser.DeferStmtContext ctx);

	Result visitSourceFileBody(AbstractGoParser.SourceFileBodyContext ctx);

	Result visitKey(AbstractGoParser.KeyContext ctx);

	Result visitDeclaration(AbstractGoParser.DeclarationContext ctx);

	Result visitCommCase(AbstractGoParser.CommCaseContext ctx);

	Result visitBuiltinArgs(AbstractGoParser.BuiltinArgsContext ctx);

	Result visitCondition(AbstractGoParser.ConditionContext ctx);

	Result visitConversionOrCallExpr(AbstractGoParser.ConversionOrCallExprContext ctx);

	Result visitLabel(AbstractGoParser.LabelContext ctx);

	Result visitElementType(AbstractGoParser.ElementTypeContext ctx);

	Result visitFunctionDecl(AbstractGoParser.FunctionDeclContext ctx);

	Result visitStatement(AbstractGoParser.StatementContext ctx);

	Result visitPointerType(AbstractGoParser.PointerTypeContext ctx);

	Result visitAddAssignOp(AbstractGoParser.AddAssignOpContext ctx);

	Result visitSourceFile(AbstractGoParser.SourceFileContext ctx);

	Result visitSliceExpr(AbstractGoParser.SliceExprContext ctx);

	Result visitBaseTypeName(AbstractGoParser.BaseTypeNameContext ctx);

	Result visitMethodExpr(AbstractGoParser.MethodExprContext ctx);

	Result visitTypeList(AbstractGoParser.TypeListContext ctx);

	Result visitIncDecStmt(AbstractGoParser.IncDecStmtContext ctx);

	Result visitBuiltinCall(AbstractGoParser.BuiltinCallContext ctx);

	Result visitConstDecl(AbstractGoParser.ConstDeclContext ctx);

	Result visitResult(AbstractGoParser.ResultContext ctx);

	Result visitAndExpr(AbstractGoParser.AndExprContext ctx);

	Result visitStructType(AbstractGoParser.StructTypeContext ctx);

	Result visitVarDecl(AbstractGoParser.VarDeclContext ctx);

	Result visitInitStmt(AbstractGoParser.InitStmtContext ctx);

	Result visitIdentifierList(AbstractGoParser.IdentifierListContext ctx);

	Result visitSliceType(AbstractGoParser.SliceTypeContext ctx);

	Result visitCompareExpr(AbstractGoParser.CompareExprContext ctx);

	Result visitImportDecl(AbstractGoParser.ImportDeclContext ctx);

	Result visitElementList(AbstractGoParser.ElementListContext ctx);

	Result visitKeyType(AbstractGoParser.KeyTypeContext ctx);

	Result visitImportPath(AbstractGoParser.ImportPathContext ctx);

	Result visitAnonymousField(AbstractGoParser.AnonymousFieldContext ctx);

	Result visitAddExpr(AbstractGoParser.AddExprContext ctx);

	Result visitExpressionStmt(AbstractGoParser.ExpressionStmtContext ctx);

	Result visitSendStmt(AbstractGoParser.SendStmtContext ctx);

	Result visitSwitchStmt(AbstractGoParser.SwitchStmtContext ctx);

	Result visitPostStmt(AbstractGoParser.PostStmtContext ctx);

	Result visitForStmt(AbstractGoParser.ForStmtContext ctx);

	Result visitTypeSwitchCase(AbstractGoParser.TypeSwitchCaseContext ctx);

	Result visitRangeClause(AbstractGoParser.RangeClauseContext ctx);

	Result visitOperand(AbstractGoParser.OperandContext ctx);

	Result visitArgumentList(AbstractGoParser.ArgumentListContext ctx);

	Result visitTypeSwitchStmt(AbstractGoParser.TypeSwitchStmtContext ctx);

	Result visitTypeDecl(AbstractGoParser.TypeDeclContext ctx);

	Result visitUnaryExpr(AbstractGoParser.UnaryExprContext ctx);

	Result visitChannel(AbstractGoParser.ChannelContext ctx);

	Result visitLiteral(AbstractGoParser.LiteralContext ctx);
}