/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Sam Harwell
 */
public final class LibrariesNode extends AbstractNode {
    @StaticResource
    private static final String ICON_BADGE_IMAGE = "org/tvl/goworks/project/ui/resources/libraries-badge.png";
    private static final Image ICON_BADGE = ImageUtilities.loadImage(ICON_BADGE_IMAGE);
    public static final RequestProcessor rp = new RequestProcessor();
    private static Icon folderIconCache;
    private static Icon openedFolderIconCache;

    private final String _displayName;
    private final Action[] _actions;

    public LibrariesNode(String displayName, GoProject project, Action[] actions) {
        super(new LibrariesChildren(project), Lookups.singleton(project));
        _displayName = displayName;
        _actions = actions;
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public String getName() {
        return this.getDisplayName();
    }

    @Override
    public Image getIcon( int type ) {
        return computeIcon( false, type );
    }

    @Override
    public Image getOpenedIcon( int type ) {
        return computeIcon( true, type );
    }

    @Override
    public Action[] getActions(boolean context) {
        return _actions;
    }

    @Override
    public boolean canCopy() {
        return false;
    }

    /**
     * Returns Icon of folder on active platform
     * @param opened should the icon represent opened folder
     * @return the folder icon
     */
    static synchronized Icon getFolderIcon (boolean opened) {
        if (openedFolderIconCache == null) {
            Node n = DataFolder.findFolder(FileUtil.getConfigRoot()).getNodeDelegate();
            openedFolderIconCache = new ImageIcon(n.getOpenedIcon(BeanInfo.ICON_COLOR_16x16));
            folderIconCache = new ImageIcon(n.getIcon(BeanInfo.ICON_COLOR_16x16));
        }

        if (opened) {
            return openedFolderIconCache;
        }
        else {
            return folderIconCache;
        }
    }

    private Image computeIcon( boolean opened, int type ) {
        Icon icon = getFolderIcon(opened);
        Image image = ((ImageIcon)icon).getImage();
        image = ImageUtilities.mergeImages(image, ICON_BADGE, 7, 7 );
        return image;
    }

    public static final class Key {
        public static final int TYPE_PLATFORM = 0;

        public final int getType() {
            return TYPE_PLATFORM;
        }
    }

    private static class LibrariesChildren extends Children.Keys<Key> implements PropertyChangeListener {
        private final GoProject _project;

        public LibrariesChildren(GoProject project) {
            _project = project;
        }

        @Override
        protected Node[] createNodes(Key key) {
            Node[] result = null;
            switch (key.getType()) {
            case Key.TYPE_PLATFORM:
                String goroot = System.getenv("GOROOT");
                if (goroot == null) {
                    return new Node[0];
                }

                File gorootFile = new File(goroot);
                if (!gorootFile.isDirectory()) {
                    return new Node[0];
                }

                FileObject sourceRoot = FileUtil.toFileObject(gorootFile);
                result = new Node[] { PlatformNode.create(_project, sourceRoot) };
                break;

            default:
                break;
            }

            if (result == null) {
                assert false : "Unknown key type";
                result = new Node[0];
            }

            return result;
        }

        @Override
        protected void addNotify() {
            setKeys(getKeys());
        }

        @Override
        protected void removeNotify() {
            setKeys(Collections.<Key>emptySet());
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
        }

        private List<Key> getKeys() {
            return Collections.singletonList(new Key());
        }
    }

}
