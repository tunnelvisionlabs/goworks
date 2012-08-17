/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
public class TreeCorrectionGoParser extends GoParser {

    public TreeCorrectionGoParser(TokenStream<? extends Token> input) {
        super(input);
    }

    @Override
    public TreeCorrectionParserATNSimulator getInterpreter() {
        return (TreeCorrectionParserATNSimulator)super.getInterpreter();
    }

}
