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
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import javax.swing.Icon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.spi.search.SearchInfoDefinitionFactory;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileStatusEvent;
import org.openide.filesystems.FileStatusListener;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Sam Harwell
 */
final class PackageRootNode extends AbstractNode implements Runnable, FileStatusListener {
    @StaticResource
    private static final String PACKAGE_BADGE = "org/tvl/goworks/project/ui/resources/packageBadge.gif";

    static final RequestProcessor PKG_VIEW_RP = new RequestProcessor(PackageRootNode.class.getName(),1);

    private final SourceGroup _group;
    private final FileObject _file;
    private final Set<FileObject> _files;
    private FileStatusListener _fileSystemListener;

    private RequestProcessor.Task _task;
    private volatile boolean _iconChange;
    private volatile boolean _nameChange;

    public PackageRootNode(Project project, SourceGroup group) {
        this(project, group, new PackageViewChildren(group));
    }

    private PackageRootNode(Project project, SourceGroup group, Children children) {
        super(children, new ProxyLookup(createLookup(group),
            Lookups.fixed(project, SearchInfoDefinitionFactory.createSearchInfoBySubnodes(children))));
        this._group = group;
        this._file = group.getRootFolder();
        this._files = Collections.singleton(_file);
        try {
            FileSystem fs = _file.getFileSystem();
            this._fileSystemListener = FileUtil.weakFileStatusListener(this, fs);
            fs.addFileStatusListener(_fileSystemListener);
        } catch (FileStateInvalidException e) {
            Exceptions.printStackTrace(Exceptions.attachMessage(e,"Can not get " + _file + " filesystem, ignoring...")); //NOI18N
        }
        setName(group.getName());
        setDisplayName(group.getDisplayName());
    }

    @Override
    public Image getIcon(int type) {
        return computeIcon(false, type);
    }

    @Override
    public Image getOpenedIcon(int type) {
        return computeIcon(true, type);
    }

    @Override
    public String getDisplayName() {
        String s = super.getDisplayName();
        try {
            s = _file.getFileSystem().getStatus().annotateName(s, _files);
        } catch (FileStateInvalidException ex) {
            Exceptions.printStackTrace(ex);
        }

        return s;
    }

    @Override
    public String getHtmlDisplayName() {
        try {
            FileSystem.Status status = _file.getFileSystem().getStatus();
            if (status instanceof FileSystem.HtmlStatus) {
                FileSystem.HtmlStatus htmlStatus = (FileSystem.HtmlStatus)status;
                String result = htmlStatus.annotateNameHtml(super.getDisplayName(), _files);
                if (!super.getDisplayName().equals(result)) {
                    return result;
                }
            }
        } catch (FileStateInvalidException ex) {
            Exceptions.printStackTrace(ex);
        }

        return super.getHtmlDisplayName();
    }

    private Node getDataFolderNodeDelegate() {
        DataFolder df = getLookup().lookup(DataFolder.class);
        try {
            if (df.isValid()) {
                return df.getNodeDelegate();
            }
        } catch (IllegalStateException e) {
            //The data systems API is not thread save,
            //the DataObject may become invalid after isValid call and before
            //getNodeDelegate call, we have to catch the ISE. When the DataObject
            //is valid - other cause rethrow it otherwise return leaf node.
            //todo: The DataObject.getNodedelegate should throw specialized exception type.
            if (df.isValid()) {
                throw e;
            }
        }
        return new AbstractNode(Children.LEAF);
    }

    private Image computeIcon( boolean opened, int type ) {
        Image image;
        Icon icon = _group.getIcon( opened );

        if ( icon == null ) {
            image = opened ? getDataFolderNodeDelegate().getOpenedIcon( type ) :
                             getDataFolderNodeDelegate().getIcon( type );
            image = ImageUtilities.mergeImages(image, ImageUtilities.loadImage(PACKAGE_BADGE), 7, 7);
        }
        else {
            image = ImageUtilities.icon2Image(icon);
        }

        return image;
    }

    private static Lookup createLookup( SourceGroup group ) {
        // XXX Remove DataFolder when paste, find and refresh are reimplemented
        FileObject rootFolder = group.getRootFolder();
        DataFolder dataFolder = DataFolder.findFolder( rootFolder );
        return Lookups.fixed(dataFolder);
    }

    @Override
    public void run() {
        if (_iconChange) {
            fireIconChange();
            fireOpenedIconChange();
            _iconChange = false;
        }

        if (_nameChange) {
            fireDisplayNameChange(null, null);
            _nameChange = false;
        }
    }

    @Override
    public void annotationChanged(FileStatusEvent event) {
        if (_task == null) {
            _task = PKG_VIEW_RP.create(this);
        }

        if ((!_iconChange && event.isIconChange())  || (!_nameChange && event.isNameChange())) {
            if (event.hasChanged(_file)) {
                _iconChange |= event.isIconChange();
                _nameChange |= event.isNameChange();
            }
        }

        _task.schedule(50);  // batch by 50 ms
    }

    // Show reasonable properties of the DataFolder,
    //it shows the sorting names as rw property, the name as ro property and the path to root as ro property
    @NbBundle.Messages({
        "PROP_name=Name",
        "HINT_name=Package Name",
        "PROP_rootpath=Source Root",
        "HINT_rootpath=Source Root"
    })
    @Override public PropertySet[] getPropertySets() {
        final PropertySet[] properties =  getDataFolderNodeDelegate().getPropertySets();
        final PropertySet[] newProperties = Arrays.copyOf(properties, properties.length);
        for (int i=0; i< newProperties.length; i++) {
            if (Sheet.PROPERTIES.equals(newProperties[i].getName())) {
                //Replace the Sheet.PROPERTIES by the new one
                //having the ro name property and ro path property
                newProperties[i] = Sheet.createPropertiesSet();
                ((Sheet.Set)newProperties[i]).put(new PropertySupport.ReadOnly<String>(DataObject.PROP_NAME, String.class,
                        Bundle.PROP_name(), Bundle.HINT_name()) {
                    @Override
                    public String getValue() {
                        return PackageRootNode.this.getDisplayName();
                    }
                });
                ((Sheet.Set)newProperties[i]).put(new PropertySupport.ReadOnly<String>("ROOT_PATH", String.class,    //NOI18N
                        Bundle.PROP_rootpath(), Bundle.HINT_rootpath()) {
                    @Override
                    public String getValue() {
                        return FileUtil.getFileDisplayName(PackageRootNode.this._file);
                    }
                });
            }
        }
        return newProperties;
    }

}
