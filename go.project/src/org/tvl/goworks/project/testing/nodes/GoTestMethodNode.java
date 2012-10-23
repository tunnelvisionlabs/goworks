/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project.testing.nodes;

import org.netbeans.api.project.Project;
import org.netbeans.modules.gsf.testrunner.api.TestMethodNode;
import org.netbeans.modules.gsf.testrunner.api.Testcase;

/**
 *
 * @author Sam Harwell
 */
public class GoTestMethodNode extends TestMethodNode {

    public GoTestMethodNode(Testcase testcase, Project project) {
        super(testcase, project);
    }

    public Testcase getTestcase() {
        return testcase;
    }

}
