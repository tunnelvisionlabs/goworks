/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.antlr.works.editor.antlr4.completion.AbstractParserCache;

/**
 *
 * @author Sam Harwell
 */
public class ParserCache extends AbstractParserCache<Token, CodeCompletionGoParser> {

    @Override
    protected CodeCompletionGoParser createParser(TokenStream<? extends Token> input) {
        CharStream charStream = input.getTokenSource().getInputStream();
        if (!(charStream instanceof DocumentSnapshotCharStream)) {
            throw new UnsupportedOperationException();
        }

        return new CodeCompletionGoParser(input);
    }

}
