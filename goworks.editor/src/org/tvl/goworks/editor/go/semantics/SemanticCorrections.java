/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.parser.GoParserBase;
import org.tvl.goworks.editor.go.parser.GoParserBase.BuiltinCallContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.ConversionOrCallExprContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.PackageNameContext;

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

    public SemanticCorrections(@NonNull ParserRuleContext<?> parseTree, @NonNull TokenStream<? extends Token> input, @NonNull DocumentSnapshot snapshot) {
        this.parseTree = parseTree;
        this.correctionParser = new TreeCorrectionGoParser(input);
        this.correctionSimulator = correctionParser.getInterpreter();
    }

    public void notConversion(ConversionOrCallExprContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_conversion);
    }

    public void notBuiltin(BuiltinCallContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_builtinCall);
    }

    public void notPackageName(PackageNameContext context) {
        correctionSimulator.suppressRule(context.start.getTokenIndex(), GoParserBase.RULE_packageName);
    }

    public void apply() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
