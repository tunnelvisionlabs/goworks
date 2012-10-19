/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.IdentityHashMap;
import java.util.Map;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.netbeans.semantics.ObjectProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class AnnotatedParseTree {

    private final ObjectDecorator<Tree> treeAnnotations = new ObjectDecorator<Tree>(new IdentityHashMap<Tree, Map<ObjectProperty<?>, Object>>());
    private ParseTree<Token> parseTree;

    public AnnotatedParseTree(@NonNull ParseTree<Token> parseTree) {
        Parameters.notNull("parseTree", parseTree);

        this.parseTree = parseTree;
    }

    @NonNull
    public ParseTree<Token> getParseTree() {
        return parseTree;
    }

    public final ObjectDecorator<Tree> getTreeDecorator() {
        return treeAnnotations;
    }

    public final void setParseTree(@NonNull ParseTree<Token> parseTree) {
        setParseTree(parseTree, true);
    }

    public void setParseTree(@NonNull ParseTree<Token> parseTree, boolean compactAnnotations) {
        Parameters.notNull("parseTree", parseTree);

        if (this.parseTree != parseTree && compactAnnotations) {
            treeAnnotations.clear();
            compactAnnotations = false;
        }

        this.parseTree = parseTree;
        if (compactAnnotations) {
            compactAnnotations();
        }
    }

    public void compactAnnotations() {
        final Map<Tree, Tree> map = new IdentityHashMap<Tree, Tree>();

        ParseTreeListener<Token> listener = new ParseTreeListener<Token>() {

            @Override
            public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
                map.put(ctx, ctx);
            }

            @Override
            public void visitTerminal(TerminalNode<? extends Token> node) {
                map.put(node, node);
            }

            @Override
            public void visitErrorNode(ErrorNode<? extends Token> node) {
                map.put(node, node);
            }

            @Override
            public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
            }

        };

        final Map<Tree, Tree> extras = new IdentityHashMap<Tree, Tree>();
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        for (Map.Entry<? extends Tree, ?> entry : treeAnnotations.getProperties().entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                extras.put(entry.getKey(), entry.getKey());
            }
        }

        for (Tree tree : extras.keySet()) {
            treeAnnotations.removeProperties(tree);
        }
    }

}
