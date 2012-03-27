/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.util.Arrays;
import org.antlr.netbeans.editor.highlighting.LineStateInfo;
import org.antlr.v4.runtime.Lexer;

/**
 *
 * @author Sam Harwell
 */
public class GoHighlighterLexerState implements LineStateInfo<GoHighlighterLexerState> {
    public static final GoHighlighterLexerState INITIAL = new GoHighlighterLexerState();
    public static final GoHighlighterLexerState DIRTY = new GoHighlighterLexerState();
    public static final GoHighlighterLexerState MULTILINE = new GoHighlighterLexerState();

    private final int mode;
    private final int[] modeStack;

    private GoHighlighterLexerState() {
        this(Lexer.DEFAULT_MODE, null);
    }

    public GoHighlighterLexerState(int mode, int[] modeStack) {
        this.mode = mode;
        this.modeStack = modeStack;
    }

    public int getMode() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return mode;
    }

    public int[] getModeStack() {
        if (getIsDirty() || getIsMultiLineToken()) {
            throw new UnsupportedOperationException();
        }

        return modeStack;
    }

    @Override
    public boolean getIsDirty() {
        return this == DIRTY;
    }

    @Override
    public boolean getIsMultiLineToken() {
        return this == MULTILINE;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GoHighlighterLexerState)) {
            return false;
        }

        return this.equals((GoHighlighterLexerState)obj);
    }

    public boolean equals(GoHighlighterLexerState other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        return this.getIsDirty() == other.getIsDirty()
            && this.getIsMultiLineToken() == other.getIsMultiLineToken()
            && this.getMode() == other.getMode()
            && Arrays.equals(this.getModeStack(), other.getModeStack());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.mode;
        hash = 31 * hash + Arrays.hashCode(this.modeStack);
        return hash;
    }

    @Override
    public GoHighlighterLexerState createDirtyState() {
        return DIRTY;
    }

    @Override
    public GoHighlighterLexerState createMultiLineState() {
        return MULTILINE;
    }
}
