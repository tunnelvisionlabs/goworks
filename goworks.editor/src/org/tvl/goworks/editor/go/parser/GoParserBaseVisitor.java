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

public interface GoParserBaseVisitor<Symbol extends Token, Result> extends ParseTreeVisitor<Symbol, Result> {
	Result visitMultExpr(GoParserBase.MultExprContext ctx);

	Result visitChannelType(GoParserBase.ChannelTypeContext ctx);

	Result visitMulAssignOp(GoParserBase.MulAssignOpContext ctx);

	Result visitPackageName(GoParserBase.PackageNameContext ctx);

	Result visitReceiver(GoParserBase.ReceiverContext ctx);

	Result visitArrayType(GoParserBase.ArrayTypeContext ctx);

	Result visitExpressionList(GoParserBase.ExpressionListContext ctx);

	Result visitTag(GoParserBase.TagContext ctx);

	Result visitFallthroughStmt(GoParserBase.FallthroughStmtContext ctx);

	Result visitSelectorExpr(GoParserBase.SelectorExprContext ctx);

	Result visitParameterList(GoParserBase.ParameterListContext ctx);

	Result visitReceiverType(GoParserBase.ReceiverTypeContext ctx);

	Result visitFieldName(GoParserBase.FieldNameContext ctx);

	Result visitIfStmt(GoParserBase.IfStmtContext ctx);

	Result visitMethodName(GoParserBase.MethodNameContext ctx);

	Result visitSignature(GoParserBase.SignatureContext ctx);

	Result visitMapType(GoParserBase.MapTypeContext ctx);

	Result visitElement(GoParserBase.ElementContext ctx);

	Result visitCallExpr(GoParserBase.CallExprContext ctx);

	Result visitTypeCaseClause(GoParserBase.TypeCaseClauseContext ctx);

	Result visitExprCaseClause(GoParserBase.ExprCaseClauseContext ctx);

	Result visitTypeSwitchGuard(GoParserBase.TypeSwitchGuardContext ctx);

	Result visitFunctionLiteral(GoParserBase.FunctionLiteralContext ctx);

	Result visitOrExpr(GoParserBase.OrExprContext ctx);

	Result visitRecvExpr(GoParserBase.RecvExprContext ctx);

	Result visitTopLevelDecl(GoParserBase.TopLevelDeclContext ctx);

	Result visitMethodSpec(GoParserBase.MethodSpecContext ctx);

	Result visitConstSpec(GoParserBase.ConstSpecContext ctx);

	Result visitCompositeLiteral(GoParserBase.CompositeLiteralContext ctx);

	Result visitForClause(GoParserBase.ForClauseContext ctx);

	Result visitShortVarDecl(GoParserBase.ShortVarDeclContext ctx);

	Result visitGotoStmt(GoParserBase.GotoStmtContext ctx);

	Result visitArrayLength(GoParserBase.ArrayLengthContext ctx);

	Result visitInterfaceType(GoParserBase.InterfaceTypeContext ctx);

	Result visitConversion(GoParserBase.ConversionContext ctx);

	Result visitBlock(GoParserBase.BlockContext ctx);

	Result visitBreakStmt(GoParserBase.BreakStmtContext ctx);

	Result visitEmptyStmt(GoParserBase.EmptyStmtContext ctx);

	Result visitFunctionType(GoParserBase.FunctionTypeContext ctx);

	Result visitBaseType(GoParserBase.BaseTypeContext ctx);

	Result visitOperandExpr(GoParserBase.OperandExprContext ctx);

	Result visitFieldDecl(GoParserBase.FieldDeclContext ctx);

	Result visitExprSwitchStmt(GoParserBase.ExprSwitchStmtContext ctx);

	Result visitGoStmt(GoParserBase.GoStmtContext ctx);

	Result visitParameterDecl(GoParserBase.ParameterDeclContext ctx);

	Result visitBasicLiteral(GoParserBase.BasicLiteralContext ctx);

	Result visitExprSwitchCase(GoParserBase.ExprSwitchCaseContext ctx);

	Result visitTypeLiteral(GoParserBase.TypeLiteralContext ctx);

	Result visitSelectStmt(GoParserBase.SelectStmtContext ctx);

	Result visitImportSpec(GoParserBase.ImportSpecContext ctx);

	Result visitTypeName(GoParserBase.TypeNameContext ctx);

	Result visitLiteralType(GoParserBase.LiteralTypeContext ctx);

	Result visitAssignment(GoParserBase.AssignmentContext ctx);

	Result visitAssignOp(GoParserBase.AssignOpContext ctx);

	Result visitRecvStmt(GoParserBase.RecvStmtContext ctx);

	Result visitTypeSpec(GoParserBase.TypeSpecContext ctx);

	Result visitPackageClause(GoParserBase.PackageClauseContext ctx);

	Result visitBuiltinCallExpr(GoParserBase.BuiltinCallExprContext ctx);

	Result visitLiteralValue(GoParserBase.LiteralValueContext ctx);

	Result visitIndexExpr(GoParserBase.IndexExprContext ctx);

	Result visitVarSpec(GoParserBase.VarSpecContext ctx);

	Result visitBody(GoParserBase.BodyContext ctx);

	Result visitCommClause(GoParserBase.CommClauseContext ctx);

	Result visitQualifiedIdentifier(GoParserBase.QualifiedIdentifierContext ctx);

	Result visitReturnStmt(GoParserBase.ReturnStmtContext ctx);

	Result visitSimpleStmt(GoParserBase.SimpleStmtContext ctx);

	Result visitTypeAssertionExpr(GoParserBase.TypeAssertionExprContext ctx);

	Result visitType(GoParserBase.TypeContext ctx);

	Result visitInterfaceTypeName(GoParserBase.InterfaceTypeNameContext ctx);

	Result visitContinueStmt(GoParserBase.ContinueStmtContext ctx);

	Result visitValue(GoParserBase.ValueContext ctx);

	Result visitMethodDecl(GoParserBase.MethodDeclContext ctx);

	Result visitLabeledStmt(GoParserBase.LabeledStmtContext ctx);

	Result visitParameters(GoParserBase.ParametersContext ctx);

	Result visitDeferStmt(GoParserBase.DeferStmtContext ctx);

	Result visitKey(GoParserBase.KeyContext ctx);

	Result visitDeclaration(GoParserBase.DeclarationContext ctx);

	Result visitCommCase(GoParserBase.CommCaseContext ctx);

	Result visitBuiltinArgs(GoParserBase.BuiltinArgsContext ctx);

	Result visitCondition(GoParserBase.ConditionContext ctx);

	Result visitConversionOrCallExpr(GoParserBase.ConversionOrCallExprContext ctx);

	Result visitLabel(GoParserBase.LabelContext ctx);

	Result visitElementType(GoParserBase.ElementTypeContext ctx);

	Result visitFunctionDecl(GoParserBase.FunctionDeclContext ctx);

	Result visitStatement(GoParserBase.StatementContext ctx);

	Result visitPointerType(GoParserBase.PointerTypeContext ctx);

	Result visitAddAssignOp(GoParserBase.AddAssignOpContext ctx);

	Result visitSourceFile(GoParserBase.SourceFileContext ctx);

	Result visitSliceExpr(GoParserBase.SliceExprContext ctx);

	Result visitBaseTypeName(GoParserBase.BaseTypeNameContext ctx);

	Result visitMethodExpr(GoParserBase.MethodExprContext ctx);

	Result visitElementIndex(GoParserBase.ElementIndexContext ctx);

	Result visitTypeList(GoParserBase.TypeListContext ctx);

	Result visitIncDecStmt(GoParserBase.IncDecStmtContext ctx);

	Result visitBuiltinCall(GoParserBase.BuiltinCallContext ctx);

	Result visitConstDecl(GoParserBase.ConstDeclContext ctx);

	Result visitResult(GoParserBase.ResultContext ctx);

	Result visitAndExpr(GoParserBase.AndExprContext ctx);

	Result visitStructType(GoParserBase.StructTypeContext ctx);

	Result visitVarDecl(GoParserBase.VarDeclContext ctx);

	Result visitInitStmt(GoParserBase.InitStmtContext ctx);

	Result visitIdentifierList(GoParserBase.IdentifierListContext ctx);

	Result visitSliceType(GoParserBase.SliceTypeContext ctx);

	Result visitCompareExpr(GoParserBase.CompareExprContext ctx);

	Result visitImportDecl(GoParserBase.ImportDeclContext ctx);

	Result visitElementList(GoParserBase.ElementListContext ctx);

	Result visitKeyType(GoParserBase.KeyTypeContext ctx);

	Result visitImportPath(GoParserBase.ImportPathContext ctx);

	Result visitAnonymousField(GoParserBase.AnonymousFieldContext ctx);

	Result visitAddExpr(GoParserBase.AddExprContext ctx);

	Result visitExpressionStmt(GoParserBase.ExpressionStmtContext ctx);

	Result visitSendStmt(GoParserBase.SendStmtContext ctx);

	Result visitSwitchStmt(GoParserBase.SwitchStmtContext ctx);

	Result visitPostStmt(GoParserBase.PostStmtContext ctx);

	Result visitForStmt(GoParserBase.ForStmtContext ctx);

	Result visitTypeSwitchCase(GoParserBase.TypeSwitchCaseContext ctx);

	Result visitRangeClause(GoParserBase.RangeClauseContext ctx);

	Result visitOperand(GoParserBase.OperandContext ctx);

	Result visitArgumentList(GoParserBase.ArgumentListContext ctx);

	Result visitTypeSwitchStmt(GoParserBase.TypeSwitchStmtContext ctx);

	Result visitTypeDecl(GoParserBase.TypeDeclContext ctx);

	Result visitUnaryExpr(GoParserBase.UnaryExprContext ctx);

	Result visitChannel(GoParserBase.ChannelContext ctx);

	Result visitLiteral(GoParserBase.LiteralContext ctx);
}