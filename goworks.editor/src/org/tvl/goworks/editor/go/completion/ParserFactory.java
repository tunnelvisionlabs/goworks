/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import java.util.Collections;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public class ParserFactory {
    public static final ParserFactory DEFAULT = new ParserFactory();

    @NonNull
    protected CodeCompletionGoParser createParser(@NonNull TokenStream<? extends Token> input) {
        CharStream charStream = input.getTokenSource().getInputStream();
        if (!(charStream instanceof DocumentSnapshotCharStream)) {
            throw new UnsupportedOperationException();
        }

        CodeCompletionGoParser parser = new CodeCompletionGoParser(input);
        return parser;
    }

    @NonNull
    public CodeCompletionGoParser getParser(@NonNull TokenStream<? extends Token> input) {
        CodeCompletionGoParser parser = createParser(input);

        parser.removeErrorListeners();
        parser.setBuildParseTree(false);
        parser.setErrorHandler(new DefaultErrorStrategy<Token>());
        parser.getInterpreter().disable_global_context = false;
        parser.getInterpreter().force_global_context = false;
        parser.getInterpreter().always_try_local_context = true;

        parser.setCheckPackageNames(false);
        parser.setPackageNames(Collections.<String>emptyList());

        return parser;
    }

}
