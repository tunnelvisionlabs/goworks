/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.parser.GoParserBase.builtinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.conversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.packageNameContext;

/**
 *
 * @author Sam Harwell
 */
public class SemanticCorrections {
    @NonNull
    private final ParserRuleContext<?> parseTree;
    @NonNull
    private final TreeCorrectionGoParser correctionParser;
    @NonNull
    private final TreeCorrectionParserATNSimulator correctionSimulator;

    @NonNull
    private final List<ParserRuleContext<?>> invalidContexts = new ArrayList<ParserRuleContext<?>>();

    public SemanticCorrections(@NonNull ParserRuleContext<?> parseTree, @NonNull TokenStream input, @NonNull DocumentSnapshot snapshot) {
        this.parseTree = parseTree;
        this.correctionParser = new TreeCorrectionGoParser(input, snapshot);
        this.correctionSimulator = correctionParser.getInterpreter();
    }

    public void notConversion(conversionOrCallExprContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_conversion);
    }

    public void notBuiltin(builtinCallContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_builtinCall);
    }

    public void notPackageName(packageNameContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_packageName);
    }

    public void apply() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
