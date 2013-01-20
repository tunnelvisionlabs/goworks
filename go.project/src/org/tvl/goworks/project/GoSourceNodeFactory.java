/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.filesystems.FileObject;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ChangeSupport;
import org.openide.util.lookup.Lookups;
import org.openide.xml.XMLUtil;

/**
 *
 * @author Sam Harwell
 */
@NodeFactory.Registration(projectType=GoProject.GO_PROJECT_ID, position=100)
public final class GoSourceNodeFactory implements NodeFactory {
    // -J-Dorg.tvl.goworks.project.GoSourceNodeFactory.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoSourceNodeFactory.class.getName());

    public GoSourceNodeFactory() {
    }

    @Override
    public NodeList<?> createNodes(Project p) {
        Project project = p.getLookup().lookup(Project.class);
        assert project != null;
        return new SourcesNodeList(project);
    }

    private static class SourcesNodeList implements NodeList<SourceGroupKey>, ChangeListener {

        private final Project project;
        private final ChangeSupport changeSupport = new ChangeSupport(this);

        public SourcesNodeList(Project proj) {
            project = proj;
        }

        @Override
        public List<SourceGroupKey> keys() {
            if (this.project.getProjectDirectory() == null || !this.project.getProjectDirectory().isValid()) {
                return Collections.<SourceGroupKey>emptyList();
            }
            List<SourceGroupKey> result =  new ArrayList<SourceGroupKey>();
            final SourceGroup[] groups = getSources().getSourceGroups(GoSourcesImpl.SOURCES_TYPE_GO);
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE,
                        "Go source groups: {0}",  //NOI18N
                        Arrays.toString(groups));
            }
            for (SourceGroup group : groups) {
                result.add(new SourceGroupKey(group, true));
            }
            return result;
        }

        @Override
        public void addChangeListener(ChangeListener l) {
            synchronized (changeSupport) {
                changeSupport.addChangeListener(l);
            }
        }

        @Override
        public void removeChangeListener(ChangeListener l) {
            synchronized (changeSupport) {
                changeSupport.removeChangeListener(l);
            }
        }

        @Override
        public Node node(SourceGroupKey key) {
            return new PackageViewFilterNode(key, project);
        }

        @Override
        public void addNotify() {
            getSources().addChangeListener(this);
        }

        @Override
        public void removeNotify() {
            getSources().removeChangeListener(this);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            // setKeys(getKeys());
            // The caller holds ProjectManager.mutex() read lock
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    changeSupport.fireChange();
                }
            });
        }

        private Sources getSources() {
            return ProjectUtils.getSources(project);
        }

    }

    private static class SourceGroupKey {

        public final SourceGroup group;
        public final FileObject fileObject;
        public final boolean trueSource;
        
        SourceGroupKey(SourceGroup group, boolean trueSource) {
            this.group = group;
            this.fileObject = group.getRootFolder();
            this.trueSource = trueSource;
        }
        
        @Override
        public int hashCode() {
            int hash = 5;
            String disp = this.group.getDisplayName();
            hash = 79 * hash + (fileObject != null ? fileObject.hashCode() : 0);
            hash = 79 * hash + (disp != null ? disp.hashCode() : 0);
            return hash;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof SourceGroupKey)) {
                return false;
            } else {
                SourceGroupKey otherKey = (SourceGroupKey) obj;
                
                if (fileObject != otherKey.fileObject && (fileObject == null || !fileObject.equals(otherKey.fileObject))) {
                    return false;
                }
                String thisDisplayName = this.group.getDisplayName();
                String otherDisplayName = otherKey.group.getDisplayName();
                boolean oneNull = thisDisplayName == null;
                boolean twoNull = otherDisplayName == null;
                if (oneNull != twoNull || !thisDisplayName.equals(otherDisplayName)) {
                    return false;
                }
                return true;
            }
        }

    }

    private static class PackageViewFilterNode extends FilterNode {

        private final String nodeName;
        private final Project project;
        private final boolean trueSource;

        public PackageViewFilterNode(SourceGroupKey sourceGroupKey, Project project) {
            super(PackageView.createPackageView(sourceGroupKey.group), null, Lookups.singleton(project));
            this.project = project;
            this.nodeName = "Sources";
            trueSource = sourceGroupKey.trueSource;
        }

        public @Override Action[] getActions(boolean context) {
            List<Action> actions = new ArrayList<Action>(Arrays.asList(super.getActions(context)));
            if (!trueSource) {
                // Just take out "New File..." as this would be misleading.
                Iterator<Action> scan = actions.iterator();
                while (scan.hasNext()) {
                    Action a = scan.next();
                    if (a != null && a.getClass().getName().equals("org.netbeans.modules.project.ui.actions.NewFile$WithSubMenu")) { // NOI18N
                        scan.remove();
                    }
                }
            }

            return actions.toArray(new Action[actions.size()]);
        }

        public @Override String getHtmlDisplayName() {
            if (trueSource) {
                return super.getHtmlDisplayName();
            }

            String htmlName = getOriginal().getHtmlDisplayName();
            if (htmlName == null) {
                try {
                    htmlName = XMLUtil.toElementContent(super.getDisplayName());
                } catch (CharConversionException x) {
                    return null; // never mind
                }
            }

            return "<font color='!controlShadow'>" + htmlName + "</font>"; // NOI18N
        }

    }

}
