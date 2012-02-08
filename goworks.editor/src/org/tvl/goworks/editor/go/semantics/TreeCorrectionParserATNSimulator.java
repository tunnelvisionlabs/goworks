/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

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
