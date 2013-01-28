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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
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
import org.tvl.goworks.editor.go.parser.generated.GoParserListener;

/**
 * This template can be used to create a new complete listener implementation
 * for {@link GoParser} with the proper {@link RuleDependency} annotations
 * already in place.
 * <p/>
 * This class should be copied to create new listener implementations. It cannot
 * be extended or instantiated directly.
 *
 * @author Sam Harwell
 */
abstract class GoFullListenerTemplate implements GoParserListener {

    private GoFullListenerTemplate() {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterMultExpr(MultExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitMultExpr(MultExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channelType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterChannelType(ChannelTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channelType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitChannelType(ChannelTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mulAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMulAssignOp(MulAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mulAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMulAssignOp(MulAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageName, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterPackageName(PackageNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageName, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitPackageName(PackageNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiver, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterReceiver(ReceiverContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiver, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitReceiver(ReceiverContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterArrayType(ArrayTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitArrayType(ArrayTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionList, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterExpressionList(ExpressionListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionList, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitExpressionList(ExpressionListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_tag, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTag(TagContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_tag, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTag(TagContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementNameOrIndex, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterElementNameOrIndex(ElementNameOrIndexContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementNameOrIndex, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitElementNameOrIndex(ElementNameOrIndexContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fallthroughStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterFallthroughStmt(FallthroughStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fallthroughStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitFallthroughStmt(FallthroughStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSelectorExpr(SelectorExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSelectorExpr(SelectorExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterList, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterParameterList(ParameterListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterList, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitParameterList(ParameterListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiverType, version = 3, dependents = Dependents.PARENTS)
    })
    public void enterReceiverType(ReceiverTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiverType, version = 3, dependents = Dependents.PARENTS)
    })
    public void exitReceiverType(ReceiverTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_ifStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterIfStmt(IfStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_ifStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitIfStmt(IfStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodName, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMethodName(MethodNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodName, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMethodName(MethodNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_signature, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterSignature(SignatureContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_signature, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitSignature(SignatureContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mapType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMapType(MapTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mapType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMapType(MapTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_element, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterElement(ElementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_element, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitElement(ElementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterCallExpr(CallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitCallExpr(CallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeCaseClause(TypeCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeCaseClause(TypeCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterExprCaseClause(ExprCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprCaseClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitExprCaseClause(ExprCaseClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchGuard, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeSwitchGuard(TypeSwitchGuardContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchGuard, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterFunctionLiteral(FunctionLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitFunctionLiteral(FunctionLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterOrExpr(OrExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitOrExpr(OrExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterRecvExpr(RecvExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitRecvExpr(RecvExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_topLevelDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTopLevelDecl(TopLevelDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_topLevelDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTopLevelDecl(TopLevelDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMethodSpec(MethodSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMethodSpec(MethodSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterConstSpec(ConstSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitConstSpec(ConstSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_compositeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterCompositeLiteral(CompositeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_compositeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitCompositeLiteral(CompositeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterForClause(ForClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitForClause(ForClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_shortVarDecl, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterShortVarDecl(ShortVarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_shortVarDecl, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitShortVarDecl(ShortVarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_gotoStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterGotoStmt(GotoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_gotoStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitGotoStmt(GotoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayLength, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterArrayLength(ArrayLengthContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayLength, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitArrayLength(ArrayLengthContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterInterfaceType(InterfaceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitInterfaceType(InterfaceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_conversion, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterConversion(ConversionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_conversion, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitConversion(ConversionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_block, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBlock(BlockContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_block, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBlock(BlockContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_breakStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBreakStmt(BreakStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_breakStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBreakStmt(BreakStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_emptyStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterEmptyStmt(EmptyStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_emptyStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitEmptyStmt(EmptyStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterFunctionType(FunctionTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitFunctionType(FunctionTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBaseType(BaseTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBaseType(BaseTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterOperandExpr(OperandExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitOperandExpr(OperandExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fieldDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterFieldDecl(FieldDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fieldDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitFieldDecl(FieldDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterExprSwitchStmt(ExprSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitExprSwitchStmt(ExprSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_goStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterGoStmt(GoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_goStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitGoStmt(GoStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterParameterDecl(ParameterDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitParameterDecl(ParameterDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_basicLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBasicLiteral(BasicLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_basicLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBasicLiteral(BasicLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterExprSwitchCase(ExprSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitExprSwitchCase(ExprSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeLiteral(TypeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeLiteral, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeLiteral(TypeLiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_selectStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterSelectStmt(SelectStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_selectStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitSelectStmt(SelectStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterImportSpec(ImportSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitImportSpec(ImportSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeName, version = 3, dependents = Dependents.PARENTS)
    })
    public void enterTypeName(TypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeName, version = 3, dependents = Dependents.PARENTS)
    })
    public void exitTypeName(TypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterLiteralType(LiteralTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitLiteralType(LiteralTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignment, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterAssignment(AssignmentContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignment, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitAssignment(AssignmentContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignOp, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterAssignOp(AssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignOp, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitAssignOp(AssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterRecvStmt(RecvStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitRecvStmt(RecvStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeSpec(TypeSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeSpec(TypeSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterPackageClause(PackageClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitPackageClause(PackageClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterBuiltinCallExpr(BuiltinCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitBuiltinCallExpr(BuiltinCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalValue, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterLiteralValue(LiteralValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalValue, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitLiteralValue(LiteralValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterIndexExpr(IndexExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitIndexExpr(IndexExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterVarSpec(VarSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varSpec, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitVarSpec(VarSpecContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_body, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBody(BodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_body, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBody(BodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterCommClause(CommClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitCommClause(CommClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_qualifiedIdentifier, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_qualifiedIdentifier, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_returnStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterReturnStmt(ReturnStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_returnStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitReturnStmt(ReturnStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_simpleStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSimpleStmt(SimpleStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_simpleStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSimpleStmt(SimpleStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterTypeAssertionExpr(TypeAssertionExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitTypeAssertionExpr(TypeAssertionExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_type, version = 2, dependents = Dependents.PARENTS)
    })
    public void enterType(TypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_type, version = 2, dependents = Dependents.PARENTS)
    })
    public void exitType(TypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterInterfaceTypeName(InterfaceTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitInterfaceTypeName(InterfaceTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_continueStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterContinueStmt(ContinueStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_continueStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitContinueStmt(ContinueStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_value, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterValue(ValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_value, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitValue(ValueContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMethodDecl(MethodDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMethodDecl(MethodDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_labeledStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterLabeledStmt(LabeledStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_labeledStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitLabeledStmt(LabeledStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameters, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterParameters(ParametersContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameters, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitParameters(ParametersContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_deferStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterDeferStmt(DeferStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_deferStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitDeferStmt(DeferStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFileBody, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSourceFileBody(SourceFileBodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFileBody, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSourceFileBody(SourceFileBodyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_key, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterKey(KeyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_key, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitKey(KeyContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_declaration, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterDeclaration(DeclarationContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_declaration, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitDeclaration(DeclarationContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterCommCase(CommCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitCommCase(CommCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinArgs, version = 2, dependents = Dependents.PARENTS)
    })
    public void enterBuiltinArgs(BuiltinArgsContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinArgs, version = 2, dependents = Dependents.PARENTS)
    })
    public void exitBuiltinArgs(BuiltinArgsContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_condition, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterCondition(ConditionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_condition, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitCondition(ConditionContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_label, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterLabel(LabelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_label, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitLabel(LabelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterElementType(ElementTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitElementType(ElementTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterFunctionDecl(FunctionDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitFunctionDecl(FunctionDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_statement, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterStatement(StatementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_statement, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitStatement(StatementContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_pointerType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterPointerType(PointerTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_pointerType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitPointerType(PointerTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_addAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterAddAssignOp(AddAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_addAssignOp, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitAddAssignOp(AddAssignOpContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFile, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSourceFile(SourceFileContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFile, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSourceFile(SourceFileContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSliceExpr(SliceExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSliceExpr(SliceExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBaseTypeName(BaseTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseTypeName, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBaseTypeName(BaseTypeNameContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterMethodExpr(MethodExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodExpr, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitMethodExpr(MethodExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeList, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeList(TypeListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeList, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeList(TypeListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_incDecStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterIncDecStmt(IncDecStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_incDecStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitIncDecStmt(IncDecStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinCall, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterBuiltinCall(BuiltinCallContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinCall, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitBuiltinCall(BuiltinCallContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterConstDecl(ConstDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitConstDecl(ConstDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_result, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterResult(ResultContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_result, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitResult(ResultContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterAndExpr(AndExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitAndExpr(AndExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_structType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterStructType(StructTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_structType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitStructType(StructTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterVarDecl(VarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitVarDecl(VarDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_initStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterInitStmt(InitStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_initStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitInitStmt(InitStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_identifierList, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterIdentifierList(IdentifierListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_identifierList, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitIdentifierList(IdentifierListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sliceType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterSliceType(SliceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sliceType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitSliceType(SliceTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterCompareExpr(CompareExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitCompareExpr(CompareExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterImportDecl(ImportDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitImportDecl(ImportDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementList, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterElementList(ElementListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementList, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitElementList(ElementListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_keyType, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterKeyType(KeyTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_keyType, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitKeyType(KeyTypeContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importPath, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterImportPath(ImportPathContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importPath, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitImportPath(ImportPathContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_anonymousField, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterAnonymousField(AnonymousFieldContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_anonymousField, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitAnonymousField(AnonymousFieldContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterAddExpr(AddExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitAddExpr(AddExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterExpressionStmt(ExpressionStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitExpressionStmt(ExpressionStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sendStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterSendStmt(SendStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sendStmt, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitSendStmt(SendStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_switchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterSwitchStmt(SwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_switchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitSwitchStmt(SwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_postStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterPostStmt(PostStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_postStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitPostStmt(PostStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterForStmt(ForStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitForStmt(ForStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeSwitchCase(TypeSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchCase, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeSwitchCase(TypeSwitchCaseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_rangeClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterRangeClause(RangeClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_rangeClause, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitRangeClause(RangeClauseContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_operand, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterOperand(OperandContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_operand, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitOperand(OperandContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_argumentList, version = 2, dependents = Dependents.PARENTS)
    })
    public void enterArgumentList(ArgumentListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_argumentList, version = 2, dependents = Dependents.PARENTS)
    })
    public void exitArgumentList(ArgumentListContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeSwitchStmt(TypeSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchStmt, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeSwitchStmt(TypeSwitchStmtContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterTypeDecl(TypeDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeDecl, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitTypeDecl(TypeDeclContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterUnaryExpr(UnaryExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitUnaryExpr(UnaryExprContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channel, version = 1, dependents = Dependents.PARENTS)
    })
    public void enterChannel(ChannelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channel, version = 1, dependents = Dependents.PARENTS)
    })
    public void exitChannel(ChannelContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literal, version = 0, dependents = Dependents.PARENTS)
    })
    public void enterLiteral(LiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literal, version = 0, dependents = Dependents.PARENTS)
    })
    public void exitLiteral(LiteralContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitTerminal(TerminalNode<? extends Token> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visitErrorNode(ErrorNode<? extends Token> node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
