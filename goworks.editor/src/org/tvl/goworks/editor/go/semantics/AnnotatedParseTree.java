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
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.GoParserBaseBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class AnnotatedParseTree {

    private final ObjectDecorator<Tree> treeAnnotations = new ObjectDecorator<Tree>(new IdentityHashMap<Tree, Map<ObjectProperty<?>, Object>>());
    private final ObjectDecorator<Token> tokenAnnotations = new ObjectDecorator<Token>();
    private ParserRuleContext<Token> parseTree;

    public AnnotatedParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        Parameters.notNull("parseTree", parseTree);

        this.parseTree = parseTree;
    }

    @NonNull
    public ParserRuleContext<Token> getParseTree() {
        return parseTree;
    }

    public final ObjectDecorator<Tree> getTreeDecorator() {
        return treeAnnotations;
    }

    public final ObjectDecorator<Token> getTokenDecorator() {
        return tokenAnnotations;
    }

    public final void setParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        setParseTree(parseTree, true);
    }

    public void setParseTree(@NonNull ParserRuleContext<Token> parseTree, boolean compactAnnotations) {
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

        GoParserBaseBaseListener listener = new GoParserBaseBaseListener() {

            @Override
            public void enterEveryRule(ParserRuleContext<Token> ctx) {
                map.put(ctx, ctx);
            }

            @Override
            public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
                map.put(ctx, ctx);
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
