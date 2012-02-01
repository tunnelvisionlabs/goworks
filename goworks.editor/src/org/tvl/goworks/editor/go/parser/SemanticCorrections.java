/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
        this.correctionParser = new TreeCorrectionGoParser(input);
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
