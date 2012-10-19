/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

/**
 *
 * @author Sam Harwell
 */
public enum ParserConfiguration {

    /**
     * Tail call optimization without SLL strength preservation, global context
     * prediction disabled.
     */
    FASTEST,

    /**
     * Tail call optimization with SLL strength preservation, global context
     * prediction disabled.
     */
    SLL,

    /**
     * Tail call optimization without SLL strength preservation, global context
     * prediction enabled for SLL conflicts at {@literal k>1}.
     */
    HYBRID,

    /**
     * Tail call optimization preserving SLL strength, global context prediction
     * enabled for SLL conflicts at {@literal k>1}.
     */
    HYBRID_SLL,

    /**
     * Tail call optimization without SLL strength preservation, global context
     * prediction enabled for all SLL conflicts unless predicate evaluation
     * reduces to a single result.
     */
    PRECISE
}
