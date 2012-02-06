/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.completion.CodeCompletionParser;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
public class CodeCompletionGoParser extends GoParser implements CodeCompletionParser {

    public CodeCompletionGoParser(TokenStream input) {
        super(input);
        _interp = new CompletionParserATNSimulator(this, _ATN);
    }

    @Override
    public CompletionParserATNSimulator getInterpreter() {
        return (CompletionParserATNSimulator)super.getInterpreter();
    }

}
