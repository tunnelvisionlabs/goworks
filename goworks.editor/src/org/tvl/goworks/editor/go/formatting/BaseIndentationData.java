/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import org.antlr.v4.runtime.tree.ParseTree;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class BaseIndentationData implements IndentationData {
    public static final BaseIndentationData NO_INDENT = new BaseIndentationData(0, 0);
    public static final BaseIndentationData UNKNOWN = new BaseIndentationData(0, -1);

    private final ParseTree target;
    private final IndentationBase base;
    private final int offset;
    private final int priority;

    public BaseIndentationData(int offset, int priority) {
        this(null, IndentationBase.LINE_START, offset, priority);
        if (offset < 0) {
            throw new IllegalArgumentException("Unexpected negative offset relative to the start of the line.");
        }
    }

    public BaseIndentationData(@NullAllowed ParseTree target, @NonNull IndentationBase base, int offset, int priority) {
        Parameters.notNull("base", base);
        if (target == null && base != IndentationBase.LINE_START) {
            throw new IllegalArgumentException("target must be provided for base " + base);
        }

        this.target = target;
        this.base = base;
        this.offset = offset;
        this.priority = priority;
    }

    public BaseIndentationData(@NonNull IndentationData reference, int relativeOffset) {
        Parameters.notNull("reference", reference);
        this.target = reference.getTarget();
        this.base = reference.getBase();
        this.offset = reference.getOffset() + relativeOffset;
        this.priority = reference.getPriority();
    }

    public BaseIndentationData(@NonNull IndentationData reference, int relativeOffset, int priority) {
        Parameters.notNull("reference", reference);
        this.target = reference.getTarget();
        this.base = reference.getBase();
        this.offset = reference.getOffset() + relativeOffset;
        this.priority = priority;
    }

    @Override
    public ParseTree getTarget() {
        return target;
    }

    @Override
    public IndentationBase getBase() {
        return base;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
