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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.SymbolStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.atn.ATNState;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.RuleTransition;
import org.antlr.v4.runtime.atn.SimulatorState;
import org.antlr.v4.runtime.atn.Transition;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.dfa.DFAState;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author Sam Harwell
 */
public class TreeCorrectionParserATNSimulator extends ParserATNSimulator<Token> {
    private final Map<Integer, IntervalSet> suppressedRules = new HashMap<Integer, IntervalSet>();
    private int startIndex;

    public TreeCorrectionParserATNSimulator(Parser parser, ATN atn) {
        super(parser, atn);
    }

    public void suppressRule(int inputIndex, int ruleIndex) {
        IntervalSet set = suppressedRules.get(inputIndex);
        if (set != null) {
            set.add(ruleIndex);
            return;
        }

        suppressedRules.put(inputIndex, IntervalSet.of(ruleIndex));
    }

    @Override
    public int adaptivePredict(SymbolStream<Token> input, int decision, ParserRuleContext<?> outerContext) {
        boolean ll1opt = optimize_ll1;
        try {
            startIndex = input.index();
            if (getSuppressedSet(startIndex).isNil()) {
                optimize_ll1 = false;
            }

            return super.adaptivePredict(input, decision, outerContext);
        } finally {
            optimize_ll1 = ll1opt;
        }
    }

    @Override
    public SimulatorState getStartState(DFA dfa, SymbolStream<?> input, ParserRuleContext<?> outerContext, boolean useContext) {
        // force execATN for special decisions
        if (getSuppressedSet(startIndex).isNil()) {
            return null;
        }

        return super.getStartState(dfa, input, outerContext, useContext);
    }

    @Override
    protected void addDFAEdge(DFAState p, int t, DFAState q) {
        if (!getSuppressedSet(startIndex).isNil()) {
            return;
        }

        super.addDFAEdge(p, t, q);
    }

    @Override
    protected DFAState addDFAEdge(DFA dfa, ATNConfigSet p, int t, List<Integer> contextTransitions, ATNConfigSet q) {
        if (!getSuppressedSet(startIndex).isNil()) {
            DFAState from = addDFAState(dfa, p);
            DFAState to = addDFAState(dfa, q);
            return to;
        }

        return super.addDFAEdge(dfa, p, t, contextTransitions, q);
    }

    @Override
    public ATNState getReachableTarget(Transition trans, int ttype) {
        if (trans instanceof RuleTransition) {
            IntervalSet suppressed = getSuppressedSet(startIndex);
            if (suppressed.contains(((RuleTransition)trans).ruleIndex)) {
                return null;
            }
        }

        return super.getReachableTarget(trans, ttype);
    }

    private IntervalSet getSuppressedSet(int startIndex) {
        IntervalSet suppressedSet = suppressedRules.get(startIndex);
        if (suppressedSet == null) {
            return IntervalSet.EMPTY_SET;
        }

        return suppressedSet;
    }
}
