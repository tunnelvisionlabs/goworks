/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

/**
 *
 * @author Sam Harwell
 */
public enum IndentationBase {

    /**
     * Indent relative to the start of the line.
     */
    LINE_START,
    /**
     * Indent relative to end of the indentation of the line.
     */
    LINE_INDENT,
    /**
     * Indent relative to the target element's actual start position.
     */
    ELEMENT_START,
    /**
     * Indent relative to the target element's actual end position.
     */
    ELEMENT_END,
}
