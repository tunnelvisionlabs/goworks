/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.queries.SharabilityQuery;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Sam Harwell
 */
class PlatformNode extends AbstractNode implements ChangeListener {

    @StaticResource
    private static final String PLATFORM_ICON = "org/tvl/goworks/project/ui/resources/platform.gif";

    public PlatformNode(FileObject root) {
        super(new PlatformChildren(root), Lookups.fixed());
        setIconBaseWithExtension(PLATFORM_ICON);
    }

    @Override
    public String getDisplayName() {
        return "Go 1.0.3";
    }

    @Override
    public String getName() {
        return getDisplayName();
    }

    @Override
    public boolean canCopy() {
        return false;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[0];
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.fireNameChange(null,null);
        this.fireDisplayNameChange(null,null);
    }

    public static PlatformNode create(FileObject root) {
        return new PlatformNode(root);
    }

    private static class PlatformChildren extends Children.Keys<SourceGroup> {
        private final FileObject root;

        public PlatformChildren(FileObject root) {
            this.root = root;
        }

        @Override
        protected void addNotify() {
            this.setKeys(this.getKeys());
        }

        @Override
        protected void removeNotify() {
            this.setKeys(Collections.<SourceGroup>emptySet());
        }

        @Override
        protected Node[] createNodes(SourceGroup key) {
            return new Node[] { PackageFilterNode.forPackage(PackageView.createPackageView(key), key) };
        }

        private List<SourceGroup> getKeys() {
            if (root == null || !root.isFolder()) {
                return Collections.emptyList();
            }

            List<SourceGroup> result = new ArrayList<SourceGroup>();
            final FileObject sourceRoot = root.getFileObject("src/pkg");
            if (sourceRoot != null && sourceRoot.isFolder()) {
                result.add(new SourceGroup() {
                    final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

                    @Override
                    public FileObject getRootFolder() {
                        return sourceRoot;
                    }

                    @Override
                    public String getName() {
                        return "Standard Library";
                    }

                    @Override
                    public String getDisplayName() {
                        return "Standard Library";
                    }

                    @Override
                    public Icon getIcon(boolean opened) {
                        return null;
                    }

                    @Override
                    public boolean contains(FileObject file) {
                        if (file == sourceRoot) {
                            return true;
                        }
                        String path = FileUtil.getRelativePath(sourceRoot, file);
                        if (path == null) {
                            return false;
                        }
                        if (file.isFolder()) {
                            path += "/"; // NOI18N
                        }
//                                if (!computeIncludeExcludePatterns().matches(path, true)) {
//                                    return false;
//                                }
//                        Project p = _project;
//                        if (file.isFolder() && file != p.getProjectDirectory() && ProjectManager.getDefault().isProject(file)) {
//                            // #67450: avoid actually loading the nested project.
//                            return false;
//                        }
                        if (true /*!(SourceRoot.this instanceof TypedSourceRoot)*/) {
                            // XXX disabled for typed source roots; difficult to make fast (#97215)
//                            Project owner = FileOwnerQuery.getOwner(file);
//                            if (owner != null && owner != p) {
//                                return false;
//                            }
                            if (SharabilityQuery.getSharability(file) == SharabilityQuery.Sharability.NOT_SHARABLE) {
                                return false;
                            } // else MIXED, UNKNOWN, or SHARABLE
                        }
                        return true;
                    }

                    @Override
                    public void addPropertyChangeListener(PropertyChangeListener listener) {
                        pcs.addPropertyChangeListener(listener);
                    }

                    @Override
                    public void removePropertyChangeListener(PropertyChangeListener listener) {
                        pcs.removePropertyChangeListener(listener);
                    }

                });
            }

            return result;
        }

    }

    private static class PackageFilterNode extends FilterNode {

        public PackageFilterNode(Node original, SourceGroup group) {
            super(original, new PackageViewChildren(group)/*new PackageFilterChildren(original, null)*/);
        }

        static FilterNode forPackage(Node original, SourceGroup group) {
            return new PackageFilterNode(original, group);
        }

        @Override
        public Action[] getActions(boolean context) {
            return new Action[0];
        }

    }

    private static class PackageFilterChildren extends FilterNode.Children {
        
        private final FileObject root;

        public PackageFilterChildren(Node original, FileObject root) {
            super(original);
            this.root = root;
        }

        @Override
        protected Node[] createNodes(Node key) {
            return new Node[0];
        }
    }
}
