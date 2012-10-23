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
import org.netbeans.modules.gsf.testrunner.api.TestRunnerNodeFactory;
import org.netbeans.modules.gsf.testrunner.api.Testcase;
import org.netbeans.modules.gsf.testrunner.api.TestsuiteNode;
import org.openide.nodes.Node;

/**
 *
 * @author Sam Harwell
 */
public class GoTestRunnerNodeFactory extends TestRunnerNodeFactory {
    @Override
    public Node createTestMethodNode(Testcase testcase, Project project) {
        return new GoTestMethodNode(testcase, project);
    }

    @Override
    public Node createCallstackFrameNode(String frameInfo, String dispayName) {
        return new GoCallstackFrameNode(frameInfo, dispayName);
    }

    @Override
    public TestsuiteNode createTestSuiteNode(String suiteName, boolean filtered) {
        return new GoTestsuiteNode(suiteName, filtered);
    }
}
