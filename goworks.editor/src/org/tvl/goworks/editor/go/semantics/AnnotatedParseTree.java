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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class AnnotatedParseTree {

    private final ObjectDecorator<Tree> annotations = new ObjectDecorator<Tree>();
    private ParserRuleContext<Token> parseTree;

    public AnnotatedParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        Parameters.notNull("parseTree", parseTree);

        this.parseTree = parseTree;
    }

    @NonNull
    public ParserRuleContext<Token> getParseTree() {
        return parseTree;
    }

    protected final ObjectDecorator<Tree> getAnnotations() {
        return annotations;
    }

    public final void setParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        setParseTree(parseTree, true);
    }

    public void setParseTree(@NonNull ParserRuleContext<Token> parseTree, boolean compactAnnotations) {
        Parameters.notNull("parseTree", parseTree);

        if (this.parseTree != parseTree && compactAnnotations) {
            annotations.clear();
            compactAnnotations = false;
        }

        this.parseTree = parseTree;
        if (compactAnnotations) {
            compactAnnotations();
        }
    }

    public void compactAnnotations() {
        final Map<Tree, Tree> map = new IdentityHashMap<Tree, Tree>();

        BlankGoParserBaseListener listener = new BlankGoParserBaseListener() {

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
        for (Map.Entry<? extends Tree, ?> entry : annotations.getProperties().entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                extras.put(entry.getKey(), entry.getKey());
            }
        }

        for (Tree tree : extras.keySet()) {
            annotations.removeProperties(tree);
        }
    }

}
