/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.nodes.Node;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NodeFactory.Registration(projectType=GoProject.GO_PROJECT_ID, position=300)
public final class GoLibrariesNodeFactory implements NodeFactory {

    public GoLibrariesNodeFactory() {
    }

    @Override
    public NodeList<?> createNodes(Project p) {
        GoProject project = p.getLookup().lookup(GoProject.class);
        assert project != null;
        if (project.isStandardLibrary()) {
            return NodeFactorySupport.fixedNodeList();
        }

        return new LibrariesNodeList(project);
    }

    @NbBundle.Messages({
        "CTL_LibrariesNode=Libraries",
    })
    private static class LibrariesNodeList implements NodeList<String>, PropertyChangeListener {
        private static final String LIBRARIES = "Libraries";

        private final ChangeSupport changeSupport = new ChangeSupport(this);
        private final GoProject _project;

        public LibrariesNodeList(GoProject project) {
            _project = project;
            GoProjectLogicalView logicalView = project.getLookup().lookup(GoProjectLogicalView.class);
            assert logicalView != null;
        }

        @Override
        public List<String> keys() {
            return Collections.singletonList(LIBRARIES);
        }

        @Override
        public void addChangeListener(ChangeListener l) {
            changeSupport.addChangeListener(l);
        }

        @Override
        public void removeChangeListener(ChangeListener l) {
            changeSupport.removeChangeListener(l);
        }

        @Override
        public Node node(String key) {
            if (LIBRARIES.equals(key)) {
                Action[] actions = new Action[] {
                };

                return new LibrariesNode(Bundle.CTL_LibrariesNode(), _project, actions);
            }

            assert false: "No node for key: " + key;
            return null;
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    changeSupport.fireChange();
                }
            });
        }

    }
}
