/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface IndentationData {

    /**
     * Gets the target element to use as an anchor for indenting this element.
     */
    @CheckForNull
    ParseTree<Token> getTarget();
    /**
     * Gets a base position relative to {@link #getTarget} to use as an indentation
     * anchor.
     */
    @NonNull
    IndentationBase getBase();
    /**
     * After the indentation anchor point is calculated from {@link #target} and
     * {@link #base}, the final indentation is found by adding an offset to the
     * anchor.
     */
    int getOffset();

    int getPriority();
}
