/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project.testing.nodes;

import org.netbeans.modules.gsf.testrunner.api.CallstackFrameNode;

/**
 *
 * @author Sam Harwell
 */
public class GoCallstackFrameNode extends CallstackFrameNode {

    public GoCallstackFrameNode(String frameInfo, String dispayName) {
        super(frameInfo, dispayName);
    }

    public GoCallstackFrameNode(String frameInfo) {
        super(frameInfo);
    }

}
