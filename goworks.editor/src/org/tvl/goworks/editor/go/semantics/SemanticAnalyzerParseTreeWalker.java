/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tvl.goworks.editor.go.parser.GoParserBase.bodyContext;

/**
 *
 * @author Sam Harwell
 */
public class SemanticAnalyzerParseTreeWalker extends ParseTreeWalker {

    private final boolean backgroundAnalysis;

    public SemanticAnalyzerParseTreeWalker(boolean backgroundAnalysis) {
        this.backgroundAnalysis = backgroundAnalysis;
    }

    @Override
    public <Symbol> void walk(ParseTreeListener<Symbol> listener, ParseTree t) {
        if (backgroundAnalysis && t instanceof bodyContext) {
            return;
        }

        super.walk(listener, t);
    }

}
