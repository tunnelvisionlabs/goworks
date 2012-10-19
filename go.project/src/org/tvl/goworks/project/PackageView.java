/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.awt.EventQueue;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.queries.VisibilityQuery;
import org.netbeans.spi.project.ui.PathFinder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;
import org.openide.util.WeakListeners;

/**
 *
 * @author Sam Harwell
 */
public class PackageView {
    // -J-Dorg.tvl.goworks.project.PackageView.level=FINE
    private static final Logger LOGGER = Logger.getLogger(PackageView.class.getName());

    private PackageView() {
    }

    public static Node createPackageView(SourceGroup group) {
        return new RootNode(group);
    }

    /**
     * Finds the node representing given object, if any.
     * The current implementation works only for {@link org.openide.filesystems.FileObject}s
     * and {@link org.openide.loaders.DataObject}s.
     * @param rootNode a node some descendant of which should contain the object
     * @param object object to find
     * @return a node representing the given object, or null if no such node was found
     */
    public static Node findPath(Node rootNode, Object object) {

        final PathFinder pf = rootNode.getLookup().lookup(PathFinder.class);
        if ( pf != null ) {
            return pf.findPath( rootNode, object );
        }
        return null;
    }

    /** Fills given collection with flattened packages under given folder
     *@param target The collection to be filled
     *@param fo The folder to be scanned
     * @param group the group to scan
     * @param createPackageItems if false the collection will be filled with file objects; if
     *       true PackageItems will be created.
     * @param showProgress whether to show a progress handle or not
     */
    @NbBundle.Messages({"# {0} - root folder", "PackageView.find_packages_progress=Finding packages in {0}"})
    static void findNonExcludedPackages(PackageViewChildren children, Collection<PackageItem> target, FileObject fo, SourceGroup group, boolean showProgress) {
        if (showProgress) {
            ProgressHandle progress = ProgressHandleFactory.createHandle(Bundle.PackageView_find_packages_progress(FileUtil.getFileDisplayName(fo)));
            progress.start(1000);
            findNonExcludedPackages(children, target, fo, group, progress, 0, 1000);
            progress.finish();
        } else {
            findNonExcludedPackages(children, target, fo, group, null, 0, 0);
        }
    }

    private static void findNonExcludedPackages(PackageViewChildren children, Collection<PackageItem> target, FileObject fo, SourceGroup group, ProgressHandle progress, int start, int end) {
        if (!fo.isValid() || fo.isVirtual()) {
            return;
        }
        if (!fo.isFolder()) {
            throw new IllegalArgumentException("Package view only accepts folders, given: " + FileUtil.getFileDisplayName(fo)); // NOI18N
        }

        if (progress != null) {
            String path = FileUtil.getRelativePath(children.getRoot(), fo);
            if (path == null) {
                if (!fo.isValid() || !children.getRoot().isValid()) {
                    return;
                } else {
                    throw new IllegalArgumentException(
                        MessageFormat.format(
                            "{0} in {1}", //NOI18N
                            FileUtil.getFileDisplayName(fo),
                            FileUtil.getFileDisplayName(children.getRoot())));
                }
            }
            progress.progress(path.replace('/', '.'), start);
        }
        if ( !VisibilityQuery.getDefault().isVisible( fo ) ) {
            return; // Don't show hidden packages
        }

        boolean hasSubfolders = false;
        boolean hasFiles = false;
        List<FileObject> folders = new ArrayList<FileObject>();
        for (FileObject kid : fo.getChildren()) {
            // XXX could use PackageDisplayUtils.isSignificant here
            if (kid.isValid() && VisibilityQuery.getDefault().isVisible(kid) && group.contains(kid)) {
                if (kid.isFolder()) {
                    folders.add(kid);
                    hasSubfolders = true;
                }
                else {
                    hasFiles = true;
                }
            }
        }
        if (hasFiles || !hasSubfolders) {
            if (target != null) {
                target.add( new PackageItem(group, fo, !hasFiles ) );
            }
            else {
                if (fo.isValid()) {
                    children.add(fo, !hasFiles, false);
                }
            }
        }
        if (!folders.isEmpty()) {
            int diff = (end - start) / folders.size();
            int c = 0;
            for (FileObject kid : folders) {
                // Do this after adding the parent, so we get a pre-order traversal.
                // Also see PackageViewChildren.findChild: prefer to get root first.
                findNonExcludedPackages(children, target, kid, group, progress, start + c * diff, start + (c + 1) * diff);
                c++;
            }
        }
    }

    private static final class RootNode extends FilterNode implements PropertyChangeListener {
        private final SourceGroup _sourceGroup;

        private RootNode(SourceGroup sourceGroup) {
            super(getOriginalNode(sourceGroup));
            _sourceGroup = sourceGroup;
            sourceGroup.addPropertyChangeListener(WeakListeners.propertyChange(this, sourceGroup));
        }

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            String prop = event.getPropertyName();
            if (SourceGroup.PROP_CONTAINERSHIP.equals(prop)) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        changeOriginal(getOriginalNode(_sourceGroup), true);
                    }

                });
            }
        }

        private static Node getOriginalNode(SourceGroup group) {
            FileObject root = group.getRootFolder();
            if (root == null || !root.isValid()) {
                return new AbstractNode(Children.LEAF);
            }

            if (!VisibilityQuery.getDefault().isVisible(root)) {
                LOGGER.log(
                    Level.WARNING,
                    "Ignoring source group: {0} with non visible root: {1}",    //NOI18N
                    new Object[]{
                        group,
                        FileUtil.getFileDisplayName(root)
                });
                return new AbstractNode (Children.LEAF);
            }

            return new PackageRootNode(group);
        }
    }

    /**
     * Model item representing one package.
     */
    static final class PackageItem implements Comparable<PackageItem> {

        private static Map<Image,Icon> image2icon = new IdentityHashMap<Image,Icon>();

        private final boolean empty;
        private final FileObject pkg;
        private final String pkgname;
        private Icon icon;

        public PackageItem(SourceGroup group, FileObject pkg, boolean empty) {
            this.pkg = pkg;
            this.empty = empty;
            String path = FileUtil.getRelativePath(group.getRootFolder(), pkg);
            assert path != null : "No " + pkg + " in " + group;
            pkgname = path.replace('/', '.');
        }

        @Override
        public String toString() {
            return pkgname;
        }

        public String getLabel() {
            return PackageDisplayUtils.getDisplayLabel(pkgname);
        }

        public Icon getIcon() {
            if ( icon == null ) {
                Image image = PackageDisplayUtils.getIcon(pkg, empty);
                icon = image2icon.get(image);
                if ( icon == null ) {
                    icon = new ImageIcon( image );
                    image2icon.put( image, icon );
                }
            }
            return icon;
        }

        @Override
        public int compareTo(PackageItem p) {
            return pkgname.compareTo(p.pkgname);
        }

    }

}
