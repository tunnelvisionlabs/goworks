/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Sam Harwell
 */
public final class GoDataSupport {

    public static Node createGoNode(FileObject goFile) {
        try {
            DataObject goDataObject = DataObject.find(goFile);
            return new GoFileNode(goDataObject, true);
        } catch (DataObjectNotFoundException ex) {
            Logger.getLogger(GoDataSupport.class.getName()).log(Level.INFO, null, ex);
            return new AbstractNode(Children.LEAF);
        }
    }

    private GoDataSupport() {
    }

}
