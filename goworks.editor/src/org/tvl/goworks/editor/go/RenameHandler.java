/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 * This handler is used by {@link GoNode#setName} method. {@link GoNode#setName} uses
 * {@link Lookup#getDefault} to lookup for instances of {@link RenameHandler}. If there is one
 * instance found, it's {@link RenameHandler#handleRename} method is called to handle rename
 * request. More than one instance of {@link RenameHandler} is not allowed.
 *
 * @author Jan Becicka
 */
public interface RenameHandler {
    /**
     * @param node on this node rename was requested
     * @param newName new name of node
     * @throws IllegalArgumentException thrown if rename cannot be performed
     */
    void handleRename(Node node, String newName) throws IllegalArgumentException;
}
