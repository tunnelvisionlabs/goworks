/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AddAssignOpContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AddExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AndExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AnonymousFieldContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArgumentListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArrayLengthContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ArrayTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AssignOpContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.AssignmentContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BaseTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BasicLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BlockContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BreakStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinArgsContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.BuiltinCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ChannelContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ChannelTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CommCaseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CommClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompareExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.CompositeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConditionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConstSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ContinueStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.DeclarationContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.DeferStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementNameOrIndexContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ElementTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.EmptyStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExprCaseClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExprSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExprSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ExpressionStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FallthroughStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FieldDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ForClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ForStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.FunctionTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.GoStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.GotoStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IdentifierListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IfStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportPathContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ImportSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IncDecStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.IndexExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InitStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.InterfaceTypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.KeyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.KeyTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LabelContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LabeledStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.LiteralValueContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MapTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MethodSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MulAssignOpContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.MultExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OperandExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.OrExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PackageNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParameterListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ParametersContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PointerTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.PostStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.QualifiedIdentifierContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.RangeClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReceiverContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReceiverTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.RecvExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.RecvStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ResultContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ReturnStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SelectStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SelectorExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SendStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ShortVarDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SignatureContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SimpleStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SliceTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SourceFileBodyContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SourceFileContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StatementContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.StructTypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.SwitchStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TagContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TopLevelDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeAssertionExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeCaseClauseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeListContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeLiteralContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeNameContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSpecContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchCaseContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchGuardContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TypeSwitchStmtContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.UnaryExprContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.ValueContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarDeclContext;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.VarSpecContext;
import org.tvl.goworks.editor.go.parser.generated.GoParserVisitor;

/**
 * This template can be used to create a new complete visitor implementation for
 * {@link GoParser} with the proper {@link RuleDependency} annotations already
 * in place.
 * <p/>
 * This class should be copied to create new visitor implementations. It cannot
 * be extended or instantiated directly.
 *
 * @author Sam Harwell
 */
abstract class GoFullVisitorTemplate extends AbstractParseTreeVisitor<Token, Void> implements GoParserVisitor<Token, Void> {

    private GoFullVisitorTemplate() {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitMultExpr(MultExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channelType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitChannelType(ChannelTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mulAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMulAssignOp(MulAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageName, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitPackageName(PackageNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiver, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitReceiver(ReceiverContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitArrayType(ArrayTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionList, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitExpressionList(ExpressionListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_tag, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTag(TagContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementNameOrIndex, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitElementNameOrIndex(ElementNameOrIndexContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fallthroughStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitFallthroughStmt(FallthroughStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSelectorExpr(SelectorExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterList, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitParameterList(ParameterListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiverType, version = 3, dependents = Dependents.PARENTS)
    })
    public Void visitReceiverType(ReceiverTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_ifStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitIfStmt(IfStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodName, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMethodName(MethodNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_signature, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitSignature(SignatureContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mapType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMapType(MapTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_element, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitElement(ElementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitCallExpr(CallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeCaseClause(TypeCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitExprCaseClause(ExprCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchGuard, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitFunctionLiteral(FunctionLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitOrExpr(OrExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitRecvExpr(RecvExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_topLevelDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTopLevelDecl(TopLevelDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMethodSpec(MethodSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitConstSpec(ConstSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_compositeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitCompositeLiteral(CompositeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitForClause(ForClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_shortVarDecl, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitShortVarDecl(ShortVarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_gotoStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitGotoStmt(GotoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayLength, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitArrayLength(ArrayLengthContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitInterfaceType(InterfaceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_conversion, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitConversion(ConversionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_block, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBlock(BlockContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_breakStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBreakStmt(BreakStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_emptyStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitEmptyStmt(EmptyStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitFunctionType(FunctionTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBaseType(BaseTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitOperandExpr(OperandExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fieldDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitFieldDecl(FieldDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitExprSwitchStmt(ExprSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_goStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitGoStmt(GoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitParameterDecl(ParameterDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_basicLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBasicLiteral(BasicLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitExprSwitchCase(ExprSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeLiteral(TypeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_selectStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitSelectStmt(SelectStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitImportSpec(ImportSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeName, version = 3, dependents = Dependents.PARENTS)
    })
    public Void visitTypeName(TypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitLiteralType(LiteralTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignment, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitAssignment(AssignmentContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignOp, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitAssignOp(AssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitRecvStmt(RecvStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeSpec(TypeSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitPackageClause(PackageClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalValue, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitLiteralValue(LiteralValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitIndexExpr(IndexExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitVarSpec(VarSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_body, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBody(BodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitCommClause(CommClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_qualifiedIdentifier, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_returnStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitReturnStmt(ReturnStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_simpleStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSimpleStmt(SimpleStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_type, version = 2, dependents = Dependents.PARENTS)
    })
    public Void visitType(TypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitInterfaceTypeName(InterfaceTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_continueStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitContinueStmt(ContinueStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_value, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitValue(ValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMethodDecl(MethodDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_labeledStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitLabeledStmt(LabeledStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameters, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitParameters(ParametersContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_deferStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitDeferStmt(DeferStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFileBody, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSourceFileBody(SourceFileBodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_key, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitKey(KeyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_declaration, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitDeclaration(DeclarationContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commCase, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitCommCase(CommCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinArgs, version = 2, dependents = Dependents.PARENTS)
    })
    public Void visitBuiltinArgs(BuiltinArgsContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_condition, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitCondition(ConditionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_label, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitLabel(LabelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitElementType(ElementTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitFunctionDecl(FunctionDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_statement, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitStatement(StatementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_pointerType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitPointerType(PointerTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_addAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitAddAssignOp(AddAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFile, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSourceFile(SourceFileContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSliceExpr(SliceExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBaseTypeName(BaseTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitMethodExpr(MethodExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeList, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeList(TypeListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_incDecStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitIncDecStmt(IncDecStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinCall, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitBuiltinCall(BuiltinCallContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitConstDecl(ConstDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_result, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitResult(ResultContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitAndExpr(AndExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_structType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitStructType(StructTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitVarDecl(VarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_initStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitInitStmt(InitStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_identifierList, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitIdentifierList(IdentifierListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sliceType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitSliceType(SliceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitCompareExpr(CompareExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitImportDecl(ImportDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementList, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitElementList(ElementListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_keyType, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitKeyType(KeyTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importPath, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitImportPath(ImportPathContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_anonymousField, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitAnonymousField(AnonymousFieldContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitAddExpr(AddExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitExpressionStmt(ExpressionStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sendStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitSendStmt(SendStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_switchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitSwitchStmt(SwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_postStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitPostStmt(PostStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitForStmt(ForStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeSwitchCase(TypeSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_rangeClause, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitRangeClause(RangeClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_operand, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitOperand(OperandContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_argumentList, version = 2, dependents = Dependents.PARENTS)
    })
    public Void visitArgumentList(ArgumentListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeSwitchStmt(TypeSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitTypeDecl(TypeDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitUnaryExpr(UnaryExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channel, version = 1, dependents = Dependents.PARENTS)
    })
    public Void visitChannel(ChannelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literal, version = 0, dependents = Dependents.PARENTS)
    })
    public Void visitLiteral(LiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
