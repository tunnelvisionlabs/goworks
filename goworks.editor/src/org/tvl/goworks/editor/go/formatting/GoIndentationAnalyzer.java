/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Dependents;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.parser.GoParser;
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
 *
 *
 * @author Sam Harwell
 */
public class GoIndentationAnalyzer {

    private static class GoIndentationVisitor extends AbstractParseTreeVisitor<Token, IndentationData> implements GoParserVisitor<Token, IndentationData> {

        @NonNull
        private final GoCodeStyle codeStyle;
        @NonNull
        private final TerminalNode<? extends Token> initialNode;
        @NonNull
        private final List<ParseTree<? extends Token>> path = new ArrayList<>();

        public GoIndentationVisitor(@NonNull GoCodeStyle codeStyle, @NonNull TerminalNode<? extends Token> initialNode) {
            this.codeStyle = codeStyle;
            this.initialNode = initialNode;
        }

        private static int getDepth(ParseTree<?> tree) {
            int i = 0;
            while (tree != null) {
                i++;
                tree = tree.getParent();
            }

            return i;
        }

        private static int getPriority(ParseTree<?> tree) {
            return 0;
        }

        private IndentationData indent(IndentationData reference) {
            return new BaseIndentationData(reference, codeStyle.getIndentSize());
        }

        private IndentationData indent(IndentationData reference, int priority) {
            return new BaseIndentationData(reference, codeStyle.getIndentSize(), priority);
        }

        private IndentationData outdent(IndentationData reference) {
            return new BaseIndentationData(reference, -codeStyle.getIndentSize());
        }

        private IndentationData visitParent(ParseTree<? extends Token> tree) {
            if (tree == null) {
                return BaseIndentationData.NO_INDENT;
            }

            path.add(tree);
            return super.visit(tree.getParent());
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_labeledStmt, version = 0)
        })
        public IndentationData visit(ParseTree<? extends Token> tree) {
            if (tree == null) {
                return BaseIndentationData.NO_INDENT;
            }

            // special handling for labeledStmt due to outdent
            if (tree instanceof LabeledStmtContext) {
                return super.visit(tree);
            }

            // always pass left edge to parent
            if (!path.isEmpty() && path.get(path.size() - 1) == tree.getChild(0)) {
                return visitParent(tree);
            }

            return super.visit(tree);
        }

        @Override
        protected IndentationData defaultResult() {
            throw new UnsupportedOperationException("Expected explicit traversal and value computation.");
        }

        @Override
        protected IndentationData aggregateResult(IndentationData aggregate, IndentationData nextResult) {
            throw new UnsupportedOperationException("Expected explicit traversal and value computation.");
        }

        @Override
        protected boolean shouldVisitNextChild(RuleNode<? extends Token> node, IndentationData currentResult) {
            throw new UnsupportedOperationException("Expected explicit traversal and value computation.");
        }

        @Override
        public IndentationData visitChildren(RuleNode<? extends Token> node) {
            throw new UnsupportedOperationException("Expected explicit traversal and value computation.");
        }

        @Override
        public IndentationData visitTerminal(TerminalNode<? extends Token> node) {
            if (node != initialNode) {
                throw new UnsupportedOperationException("Unexpected terminal.");
            }

            return visitParent(node);
        }

        @Override
        public IndentationData visitErrorNode(ErrorNode<? extends Token> node) {
            return BaseIndentationData.UNKNOWN;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMultExpr(MultExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channelType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitChannelType(ChannelTypeContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mulAssignOp, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMulAssignOp(MulAssignOpContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageName, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitPackageName(PackageNameContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiver, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitReceiver(ReceiverContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitArrayType(ArrayTypeContext ctx) {
            // the only break would appear after the opening `[`. indent relative to that.
            return new BaseIndentationData(ctx.getChild(0), IndentationBase.LINE_INDENT, codeStyle.getIndentSize(), getPriority(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionList, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitExpressionList(ExpressionListContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_tag, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTag(TagContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementNameOrIndex, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitElementNameOrIndex(ElementNameOrIndexContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fallthroughStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitFallthroughStmt(FallthroughStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSelectorExpr(SelectorExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterList, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitParameterList(ParameterListContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_receiverType, version = 3, dependents = Dependents.PARENTS)
        })
        public IndentationData visitReceiverType(ReceiverTypeContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_ifStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitIfStmt(IfStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodName, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMethodName(MethodNameContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_signature, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSignature(SignatureContext ctx) {
            return visitParent(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_mapType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMapType(MapTypeContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_element, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitElement(ElementContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCallExpr(CallExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeCaseClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeCaseClause(TypeCaseClauseContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprCaseClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitExprCaseClause(ExprCaseClauseContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchGuard, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeSwitchGuard(TypeSwitchGuardContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionLiteral, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitFunctionLiteral(FunctionLiteralContext ctx) {
            return visitParent(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitOrExpr(OrExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvExpr, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitRecvExpr(RecvExprContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_topLevelDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTopLevelDecl(TopLevelDeclContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodSpec, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMethodSpec(MethodSpecContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constSpec, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitConstSpec(ConstSpecContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_compositeLiteral, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCompositeLiteral(CompositeLiteralContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitForClause(ForClauseContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_shortVarDecl, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitShortVarDecl(ShortVarDeclContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_gotoStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitGotoStmt(GotoStmtContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_arrayLength, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitArrayLength(ArrayLengthContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitInterfaceType(InterfaceTypeContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_conversion, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitConversion(ConversionContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_block, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBlock(BlockContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_breakStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBreakStmt(BreakStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_emptyStmt, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitEmptyStmt(EmptyStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitFunctionType(FunctionTypeContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBaseType(BaseTypeContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitOperandExpr(OperandExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_fieldDecl, version = 0, dependents = Dependents.PARENTS),
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_identifierList, version = 0, dependents = Dependents.SELF),
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_anonymousField, version = 0, dependents = Dependents.SELF),
        })
        public IndentationData visitFieldDecl(FieldDeclContext ctx) {
            if (path.get(path.size() - 1) instanceof IdentifierListContext
                || path.get(path.size() - 1) instanceof AnonymousFieldContext)
            {
                throw new IllegalStateException("Should have been passed to parent.");
            }

            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitExprSwitchStmt(ExprSwitchStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_goStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitGoStmt(GoStmtContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameterDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitParameterDecl(ParameterDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_basicLiteral, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBasicLiteral(BasicLiteralContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_exprSwitchCase, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitExprSwitchCase(ExprSwitchCaseContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeLiteral, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeLiteral(TypeLiteralContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_selectStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSelectStmt(SelectStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importSpec, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitImportSpec(ImportSpecContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeName, version = 3, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeName(TypeNameContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitLiteralType(LiteralTypeContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignment, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAssignment(AssignmentContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_assignOp, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAssignOp(AssignOpContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_recvStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitRecvStmt(RecvStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSpec, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeSpec(TypeSpecContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_packageClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitPackageClause(PackageClauseContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBuiltinCallExpr(BuiltinCallExprContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literalValue, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitLiteralValue(LiteralValueContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitIndexExpr(IndexExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varSpec, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitVarSpec(VarSpecContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_body, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBody(BodyContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCommClause(CommClauseContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_qualifiedIdentifier, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_returnStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitReturnStmt(ReturnStmtContext ctx) {
            return visitParent(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_simpleStmt, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSimpleStmt(SimpleStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeAssertionExpr(TypeAssertionExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_type, version = 2, dependents = Dependents.PARENTS),
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeName, version = 0, dependents = Dependents.SELF),
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeLiteral, version = 0, dependents = Dependents.SELF)
        })
        public IndentationData visitType(TypeContext ctx) {
            if (ctx.typeName() != null || ctx.typeLiteral() != null) {
                throw new IllegalStateException("Should have been passed to parent.");
            }

            // the only break can appear after the opening `(`. indent relative to that
            return new BaseIndentationData(ctx.getChild(0), IndentationBase.LINE_INDENT, codeStyle.getIndentSize(), getPriority(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_interfaceTypeName, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitInterfaceTypeName(InterfaceTypeNameContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_continueStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitContinueStmt(ContinueStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_value, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitValue(ValueContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMethodDecl(MethodDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_labeledStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitLabeledStmt(LabeledStmtContext ctx) {
            if (path.get(path.size() - 1) instanceof LabelContext) {
                return outdent(visitParent(ctx));
            }

            return visitParent(ctx);
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_parameters, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitParameters(ParametersContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_deferStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitDeferStmt(DeferStmtContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFileBody, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSourceFileBody(SourceFileBodyContext ctx) {
            ParseTree<? extends Token> reference = path.isEmpty() ? null : path.get(path.size() - 1);
            if (reference == null) {
                return BaseIndentationData.NO_INDENT;
            }

            ParseTree<Token> tree = null;
            for (int i = 0; i < ctx.getChildCount(); i++) {
                ParseTree<Token> child = ctx.getChild(i);
                if (child instanceof TerminalNode) {
                    continue;
                }

                if (child == reference) {
                    if (tree == null) {
                        return BaseIndentationData.NO_INDENT;
                    }

                    return new BaseIndentationData(tree, IndentationBase.LINE_INDENT, 0, getDepth(ctx));
                }

                tree = child;
            }

            return BaseIndentationData.NO_INDENT;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_key, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitKey(KeyContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_declaration, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitDeclaration(DeclarationContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_commCase, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCommCase(CommCaseContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinArgs, version = 2, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBuiltinArgs(BuiltinArgsContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_condition, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCondition(ConditionContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitConversionOrCallExpr(ConversionOrCallExprContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_label, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitLabel(LabelContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitElementType(ElementTypeContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_functionDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitFunctionDecl(FunctionDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_statement, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitStatement(StatementContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_pointerType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitPointerType(PointerTypeContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_addAssignOp, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAddAssignOp(AddAssignOpContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sourceFile, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSourceFile(SourceFileContext ctx) {
            return BaseIndentationData.NO_INDENT;
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSliceExpr(SliceExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_baseTypeName, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBaseTypeName(BaseTypeNameContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_methodExpr, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitMethodExpr(MethodExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeList, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeList(TypeListContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_incDecStmt, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitIncDecStmt(IncDecStmtContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_builtinCall, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitBuiltinCall(BuiltinCallContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_constDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitConstDecl(ConstDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_result, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitResult(ResultContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAndExpr(AndExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_structType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitStructType(StructTypeContext ctx) {
            /* break could come after `struct`, `{`, or `;`
             *
             * indent `{` to match `struct`
             * indent first `fieldDecl` relative to `{`.
             * indent remaining `fieldDecl` to match the `fieldDecl` before it.
             * indent `}` to match `{`.
             */

            ParseTree<? extends Token> last = path.isEmpty() ? null : path.get(path.size() - 1);
            if (last instanceof FieldDeclContext) {
                FieldDeclContext previous = null;
                for (FieldDeclContext fieldDeclContext : ctx.fieldDecl()) {
                    if (fieldDeclContext == last) {
                        if (previous != null) {
                            return new BaseIndentationData(previous, IndentationBase.LINE_INDENT, 0, getDepth(ctx));
                        }

                        return new BaseIndentationData(ctx.getChild(1), IndentationBase.LINE_INDENT, codeStyle.getIndentSize(), getDepth(ctx));
                    }

                    previous = fieldDeclContext;
                }
            }

            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_varDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitVarDecl(VarDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_initStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitInitStmt(InitStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_identifierList, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitIdentifierList(IdentifierListContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sliceType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSliceType(SliceTypeContext ctx) {
            // the only break would appear after the opening `[`. indent relative to that.
            return new BaseIndentationData(ctx.getChild(0), IndentationBase.LINE_INDENT, codeStyle.getIndentSize(), getPriority(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitCompareExpr(CompareExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitImportDecl(ImportDeclContext ctx) {
            ParseTree<? extends Token> lastNode = path.isEmpty() ? null : path.get(path.size() - 1);
            if (ctx.getChildCount() > 0 && lastNode instanceof TerminalNode) {
                switch (((TerminalNode<? extends Token>)lastNode).getSymbol().getType()) {
                case GoParser.LeftParen:
                case GoParser.RightParen:
                    return visitParent(ctx);

                default:
                    break;
                }
            }

            return indent(visitParent(ctx), getDepth(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_elementList, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitElementList(ElementListContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_keyType, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitKeyType(KeyTypeContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_importPath, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitImportPath(ImportPathContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_anonymousField, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAnonymousField(AnonymousFieldContext ctx) {
            if (ctx.ptr == null) {
                throw new IllegalStateException("Should have been passed to parent.");
            }

            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitAddExpr(AddExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expressionStmt, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitExpressionStmt(ExpressionStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_sendStmt, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSendStmt(SendStmtContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_switchStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitSwitchStmt(SwitchStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_postStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitPostStmt(PostStmtContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_forStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitForStmt(ForStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchCase, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeSwitchCase(TypeSwitchCaseContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_rangeClause, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitRangeClause(RangeClauseContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_operand, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitOperand(OperandContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_argumentList, version = 2, dependents = Dependents.PARENTS)
        })
        public IndentationData visitArgumentList(ArgumentListContext ctx) {
            return indent(visitParent(ctx));
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeSwitchStmt, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeSwitchStmt(TypeSwitchStmtContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_typeDecl, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitTypeDecl(TypeDeclContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_expression, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitUnaryExpr(UnaryExprContext ctx) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_channel, version = 1, dependents = Dependents.PARENTS)
        })
        public IndentationData visitChannel(ChannelContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }

        @Override
        @RuleDependencies({
            @RuleDependency(recognizer = GoParser.class, rule = GoParser.RULE_literal, version = 0, dependents = Dependents.PARENTS)
        })
        public IndentationData visitLiteral(LiteralContext ctx) {
            throw new IllegalStateException("Should have been passed to parent.");
        }
    }
}
