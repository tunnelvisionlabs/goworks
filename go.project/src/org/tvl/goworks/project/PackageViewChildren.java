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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.fileinfo.NonRecursiveFolder;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.queries.VisibilityQuery;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ui.support.FileSensitiveActions;
import org.netbeans.spi.search.SearchInfoDefinitionFactory;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.FileSystemAction;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.ChangeableDataFilter;
import org.openide.loaders.DataFilter;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.WeakListeners;
import org.openide.util.datatransfer.ExTransferable;
import org.openide.util.datatransfer.MultiTransferObject;
import org.openide.util.datatransfer.PasteType;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Sam Harwell
 */
final class PackageViewChildren extends Children.Keys<String> implements FileChangeListener, ChangeListener, Runnable {
    // -J-Dorg.tvl.goworks.project.PackageViewChildren.level=FINE
    private static final Logger LOGGER = Logger.getLogger(PackageViewChildren.class.getName());

    private static final String NODE_NOT_CREATED = "NNC"; // NOI18N
    private static final String NODE_NOT_CREATED_EMPTY = "NNC_E"; //NOI18N

    static final String PRIMARY_TYPE = "application";   //NOI18N
    static final String SUBTYPE = "x-go-" + GoProject.GO_PROJECT_ID + "-packagenodednd";    //NOI18N
    static final String MASK = "mask";  //NOI18N

    private static final MessageFormat PACKAGE_FLAVOR = new MessageFormat(PRIMARY_TYPE + "/" + SUBTYPE + "; class=org.tvl.goworks.project.PackageViewChildren$PackageNode; mask={0}"); //NOI18N

    private final java.util.Map<String,Object/*NODE_NOT_CREATED|NODE_NOT_CREATED_EMPTY|PackageNode*/> names2nodes;
    private final FileObject root;
    private final SourceGroup group;
    private FileChangeListener wfcl;    // Weak listener on the system filesystem
    private ChangeListener wvqcl;       // Weak listener on the VisibilityQuery

    /**
     * Creates children based on a single source root.
     * @param root the folder where sources start (must be a package root)
     */
    public PackageViewChildren(SourceGroup group) {

        // Sem mas dat cache a bude to uplne nejrychlejsi na svete
        names2nodes = Collections.synchronizedMap(new TreeMap<String,Object>());
        this.root = group.getRootFolder();
        this.group = group;
    }

    FileObject getRoot() {
        return root; // Used from PackageRootNode
    }

    @Override
    protected Node[] createNodes(String path) {
        FileObject fo = root.getFileObject(path);
        if ( fo != null && fo.isValid() && fo.isFolder()) {
            Object o = names2nodes.get(path);
            PackageNode n;
            DataFolder folder;
            try {
                folder = DataFolder.findFolder(fo);
            } catch (IllegalArgumentException iae) {
                throw new IllegalStateException(
                    MessageFormat.format(
                        "Root: {0}, Path: {1}, Visible: {2}",           //NOI18N
                        FileUtil.getFileDisplayName(root),
                        path,
                        VisibilityQuery.getDefault().isVisible(fo)),
                    iae);
            }
            if (folder.isValid()) {
                if ( o == NODE_NOT_CREATED ) {
                    n = new PackageNode(root, folder, false);
                } else { // NODE_NOT_CREATED_EMPTY, PackageNode
                    n = new PackageNode(root, folder);
                }
                names2nodes.put(path, n);
                return new Node[] {n};
            }
        }
        return new Node[0];
    }

    RequestProcessor.Task task = PackageRootNode.PKG_VIEW_RP.create( this );

    @Override
    protected void addNotify() {
        // System.out.println("ADD NOTIFY" + root + " : " + this );
        super.addNotify();
        task.schedule( 0 );
    }

    @Override
    public Node[] getNodes( boolean optimal ) {
        if ( optimal ) {
            Node[] garbage = super.getNodes( false );
            task.waitFinished();
        }
        return super.getNodes( false );
    }

    @Override
    public Node findChild (String name) {
        while (true) {
            Node n = super.findChild(name);
            if (n != null) {
                // If already there, get it quickly.
                return n;
            }
            // In case a project is made on a large existing source root,
            // which happens to have a file in the root dir (so package node
            // should exist), try to select the root package node soon; no need
            // to wait for whole tree.
            try {
                if (task.waitFinished(5000)) {
                    return super.findChild(name);
                }
                // refreshKeysAsync won't run since we are blocking EQ!
                refreshKeys();
            } catch (InterruptedException x) {
                Exceptions.printStackTrace(x);
            }
        }
    }

    @Override
    public void run() {
        computeKeys();
        refreshKeys();
        try {
            FileSystem fs = root.getFileSystem();
            wfcl = FileUtil.weakFileChangeListener(this, fs);
            fs.addFileChangeListener( wfcl );
        }
        catch ( FileStateInvalidException e ) {
            Exceptions.printStackTrace(e);
        }
        wvqcl = WeakListeners.change( this, VisibilityQuery.getDefault() );
        VisibilityQuery.getDefault().addChangeListener( wvqcl );
    }

    @Override
    protected void removeNotify() {
        // System.out.println("REMOVE NOTIFY" + root + " : " + this );
        VisibilityQuery.getDefault().removeChangeListener( wvqcl );
        try {
            root.getFileSystem().removeFileChangeListener( wfcl );
        }
        catch ( FileStateInvalidException e ) {
            Exceptions.printStackTrace(e);
        }
        setKeys(new String[0]);
        names2nodes.clear();
        super.removeNotify();
    }

    // Private methods ---------------------------------------------------------

    private void refreshKeys() {
        Set<String> keys;
        synchronized (names2nodes) {
            keys = new TreeSet<>(names2nodes.keySet());
        }
        setKeys(keys);
    }

    /* #70097: workaround of a javacore deadlock
     * See related issue: #61027
     */
    private void refreshKeysAsync () {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run () {
                refreshKeys();
            }
         });
    }

    private void computeKeys() {
        // XXX this is not going to perform too well for a huge source root...
        // However we have to go through the whole hierarchy in order to find
        // all packages (Hrebejk)
        names2nodes.clear();
        findNonExcludedPackages( root );
    }

    /**
     * Collect all recursive subfolders, except those which have subfolders
     * but no files.
     */
    private void findNonExcludedPackages( FileObject fo ) {
        PackageView.findNonExcludedPackages(this, null, fo, group, true);
    }


    /** Finds all empty parents of given package and deletes them
     */
    private void cleanEmptyKeys( FileObject fo ) {
        FileObject parent = fo.getParent();

        // Special case for default package
        if ( root.equals( parent ) ) {
            PackageNode n = get( parent );
            // the default package is considered empty if it only contains folders,
            // regardless of the contents of these folders (empty or not)
            if ( n != null && PackageDisplayUtils.isEmpty( root, false ) ) {
                remove( root );
            }
            return;
        }

        while ( FileUtil.isParentOf( root, parent ) ) {
            PackageNode n = get( parent );
            if ( n != null && n.isLeaf() ) {
                // System.out.println("Cleaning " + parent);
                remove( parent );
            }
            parent = parent.getParent();
        }
    }

    // Non private only to be able to have the findNonExcludedPackages impl
    // in on place (PackageView)
    void add(FileObject fo, boolean empty, boolean refreshImmediately) {
        String path = FileUtil.getRelativePath( root, fo );
        assert path != null : "Adding wrong folder " + fo +"(valid="+fo.isValid()+")"+ "under root" + this.root + "(valid="+this.root.isValid()+")";
        if ( get( fo ) == null ) {
            names2nodes.put( path, empty ? NODE_NOT_CREATED_EMPTY : NODE_NOT_CREATED );
            if (refreshImmediately) {
                refreshKeysAsync();
            } else {
                synchronized (this) {
                    if (refreshLazilyTask == null) {
                        refreshLazilyTask = PackageRootNode.PKG_VIEW_RP.post(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (PackageViewChildren.this) {
                                    refreshLazilyTask = null;
                                    refreshKeysAsync();
                                }
                            }
                        }, 2500);
                    }
                }
            }
        }
    }
    private RequestProcessor.Task refreshLazilyTask;

    private void remove( FileObject fo ) {
        String path = FileUtil.getRelativePath( root, fo );
        assert path != null : "Removing wrong folder" + fo;
        names2nodes.remove( path );
    }

    private void removeSubTree (FileObject fo) {
        String path = FileUtil.getRelativePath( root, fo );
        assert path != null : "Removing wrong folder" + fo;
        synchronized (names2nodes) {
            Set<String> keys = names2nodes.keySet();
            keys.remove(path);
            path = path + '/';  //NOI18N
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                if (it.next().startsWith(path)) {
                    it.remove();
                }
            }
        }
    }

    private PackageNode get( FileObject fo ) {
        String path = FileUtil.getRelativePath( root, fo );
        assert path != null : "Asking for wrong folder" + fo;
        Object o = names2nodes.get( path );
        return !isNodeCreated( o ) ? null : (PackageNode)o;
    }

    private boolean contains( FileObject fo ) {
        String path = FileUtil.getRelativePath( root, fo );
        assert path != null : "Asking for wrong folder" + fo;
        Object o = names2nodes.get( path );
        return o != null;
    }

    private boolean exists( FileObject fo ) {
        String path = FileUtil.getRelativePath( root, fo );
        return names2nodes.get( path ) != null;
    }

    private boolean isNodeCreated( Object o ) {
        return o instanceof Node;
    }

    private PackageNode updatePath( String oldPath, String newPath ) {
        assert newPath != null;
        Object o = names2nodes.get( oldPath );
        if ( o == null ) {
            return null;
        }
        names2nodes.remove( oldPath );
        names2nodes.put( newPath, o );
        return !isNodeCreated( o ) ? null : (PackageNode)o;
    }

    // Implementation of FileChangeListener ------------------------------------

    @Override
    public void fileAttributeChanged( FileAttributeEvent fe ) {}

    @Override
    public void fileChanged( FileEvent fe ) {}

    @Override
    public void fileFolderCreated(final FileEvent fe ) {
        FileObject fo = fe.getFile();
        if ( FileUtil.isParentOf( root, fo ) && isVisible( root, fo ) ) {
            if (ProjectManager.mutex().isReadAccess() || ProjectManager.mutex().isWriteAccess()) {
                PackageRootNode.PKG_VIEW_RP.post(new Runnable() {
                    @Override
                    public void run() {
                        fileFolderCreated(fe);
                    }
                });
                return;
            }
            cleanEmptyKeys( fo );
//            add( fo, false);
            findNonExcludedPackages( fo );
            refreshKeys();
        }
    }

    @Override
    public void fileDataCreated( final FileEvent fe ) {
        FileObject fo = fe.getFile();
        if ( FileUtil.isParentOf( root, fo ) && isVisible( root, fo ) ) {
            if (ProjectManager.mutex().isReadAccess() || ProjectManager.mutex().isWriteAccess()) {
                PackageRootNode.PKG_VIEW_RP.post(new Runnable() {
                    @Override
                    public void run() {
                        fileDataCreated(fe);
                    }
                });
                return;
            }

            FileObject parent = fo.getParent();
            if (!parent.isFolder()) {
                throw new IllegalStateException(FileUtil.getFileDisplayName(parent) + " is not a folder!"); //NOI18N
            }
            // XXX consider using group.contains() here
            if ( !VisibilityQuery.getDefault().isVisible( parent ) ) {
                return; // Adding file into ignored directory
            }
            PackageNode n = get( parent );
            if ( n == null && !contains( parent ) ) {
                add(parent, false, true);
                refreshKeysAsync();
            }
            else if ( n != null ) {
                n.updateChildren();
            }
        }
    }

    @Override
    public void fileDeleted( final FileEvent fe ) {
        FileObject fo = fe.getFile();

        // System.out.println("FILE DELETED " + FileUtil.getRelativePath( root, fo ) );

        if ( FileUtil.isParentOf( root, fo ) && isVisible( root, fo ) ) {
            if (ProjectManager.mutex().isReadAccess() || ProjectManager.mutex().isWriteAccess()) {
                PackageRootNode.PKG_VIEW_RP.post(new Runnable() {
                    @Override
                    public void run() {
                        // why don't we jave closures in java? :(
                        fileDeleted(fe);
                    }
                });
                return;
            }


            // System.out.println("IS FOLDER? " + fo + " : " + fo.isFolder() );
                                  /* Hack for MasterFS see #42464 */
            if ( fo.isFolder() || get( fo ) != null ) {
                // System.out.println("REMOVING FODER " + fo );
                removeSubTree( fo );
                // Now add the parent if necessary
                FileObject parent = fo.getParent();
                if (!parent.isFolder()) {
                    throw new IllegalStateException(FileUtil.getFileDisplayName(parent) + " is not a folder!"); //NOI18N
                }
                if ( ( FileUtil.isParentOf( root, parent ) || root.equals( parent ) ) && get( parent ) == null && parent.isValid() ) {
                    // Candidate for adding
                    if ( !toBeRemoved( parent ) ) {
                        // System.out.println("ADDING PARENT " + parent );
                        add(parent, true, true);
                    }
                }
                refreshKeysAsync();
            }
            else {
                FileObject parent = fo.getParent();
                final PackageNode n = get( parent );
                if ( n != null ) {
                    //#61027: workaround to a deadlock when the package is being changed from non-leaf to leaf:
                    boolean leaf = n.isLeaf();
                    DataFolder df = n.getDataFolder();
                    boolean empty = isEmpty(df);

                    if (leaf != empty) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                n.updateChildren();
                            }
                        });
                    } else {
                        n.updateChildren();
                    }
                }
                // If the parent folder only contains folders remove it
                if ( toBeRemoved( parent ) ) {
                    remove( parent );
                    refreshKeysAsync();
                }

            }
        }
        // else {
        //    System.out.println("NOT A PARENT " + fo );
        // }
    }

    /** Returns true if the folder should be removed from the view
     * i.e. it has some unignored children and the children are folders only
     */
    private boolean toBeRemoved( FileObject folder ) {
        boolean ignoredOnly = true;
        boolean foldersOnly = true;
        for (FileObject kid : folder.getChildren()) {
            // XXX consider using group.contains() here
            if (VisibilityQuery.getDefault().isVisible(kid)) {
                ignoredOnly = false;
                if (!kid.isFolder()) {
                    foldersOnly = false;
                    break;
                }
            }
        }
        if ( ignoredOnly ) {
            return false; // It is either empty or it only contains ignored files
                          // thus is leaf and it means package
        }
        else {
            return foldersOnly;
        }
    }

    @Override
    public void fileRenamed( final FileRenameEvent fe ) {
        FileObject fo = fe.getFile();
        if ( FileUtil.isParentOf( root, fo ) && fo.isFolder() ) {
            if (ProjectManager.mutex().isReadAccess() || ProjectManager.mutex().isWriteAccess()) {
                PackageRootNode.PKG_VIEW_RP.post(new Runnable() {
                    @Override
                    public void run() {
                        // why don't we jave closures in java? :(
                        fileRenamed(fe);
                    }
                });
                return;
            }

            String rp = FileUtil.getRelativePath( root, fo.getParent() );
            String oldPath = rp + ( rp.length() == 0 ? "" : "/" ) + fe.getName() + fe.getExt(); // NOI18N

            // XXX consider using group.contains() here
            boolean visible = VisibilityQuery.getDefault().isVisible( fo );
            boolean doUpdate = false;

            // Find all entries which have to be updated
            List<String> needsUpdate = new ArrayList<>();
            synchronized (names2nodes) {
                for (Iterator<String> it = names2nodes.keySet().iterator(); it.hasNext(); ) {
                    String p = it.next();
                    if ( p.startsWith( oldPath ) ) {
                        if ( visible ) {
                            needsUpdate.add( p );
                        } else {
                            it.remove();
                            doUpdate = true;
                        }
                    }
                }
            }

            // If the node does not exists then there might have been update
            // from ignored to non ignored
            if ( get( fo ) == null && visible ) {
                cleanEmptyKeys( fo );
                findNonExcludedPackages( fo );
                doUpdate = true;  // force refresh
            }

            int oldPathLen = oldPath.length();
            String newPath = FileUtil.getRelativePath( root, fo );
            for (String p : needsUpdate) {
                StringBuilder np = new StringBuilder(p);
                np.replace( 0, oldPathLen, newPath );
                PackageNode n = updatePath( p, np.toString() ); // Replace entries in cache
                if ( n != null ) {
                    n.updateDisplayName(); // Update nodes
                }
            }

            if ( needsUpdate.size() > 1 || doUpdate ) {
                // Sorting might change
                refreshKeys();
            }
        }
        /*
        else if ( FileUtil.isParentOf( root, fo ) && fo.isFolder() ) {
            FileObject parent = fo.getParent();
            PackageNode n = get( parent );
            if ( n != null && VisibilityQuery.getDefault().isVisible( parent ) ) {
                n.updateChildren();
            }

        }
        */

    }

    /** Test whether file and all it's parent up to parent paremeter
     * are visible
     */
    private boolean isVisible( FileObject parent, FileObject file ) {

        do {
            // XXX consider using group.contains() here
            if ( !VisibilityQuery.getDefault().isVisible( file ) )  {
                return false;
            }
            file = file.getParent();
        }
        while ( file != null && file != parent );

        return true;
    }


    // Implementation of ChangeListener ------------------------------------

    @Override
    public void stateChanged( ChangeEvent e ) {
        computeKeys();
        refreshKeys();
    }


    /*
    private void debugKeySet() {
        for( Iterator it = names2nodes.keySet().iterator(); it.hasNext(); ) {
            String k = (String)it.next();
            System.out.println( "    " + k + " -> " +  names2nodes.get( k ) );
        }
    }
     */

    private final DataFilter NO_FOLDERS_FILTER = new NoFoldersDataFilter();

    private static boolean isEmpty(DataFolder dataFolder) {
        if ( dataFolder == null ) {
            return true;
        }
        return PackageDisplayUtils.isEmpty( dataFolder.getPrimaryFile() );
    }

    final class PackageNode extends FilterNode {

        private Action actions[];

        private final FileObject root;
        private DataFolder dataFolder;
        private boolean isDefaultPackage;

        public PackageNode( FileObject root, DataFolder dataFolder ) {
            this( root, dataFolder, isEmpty( dataFolder ) );
        }

        public PackageNode( FileObject root, DataFolder dataFolder, boolean empty ) {
            super( dataFolder.getNodeDelegate(),
                   empty ? FilterNode.Children.LEAF : dataFolder.createNodeChildren( NO_FOLDERS_FILTER ),
                   new ProxyLookup(
                        Lookups.singleton(new GoActionProvider((GoProject)FileOwnerQuery.getOwner(root))),
                        Lookups.singleton(FileOwnerQuery.getOwner(root)),
                        Lookups.singleton(new NoFoldersContainer (dataFolder)),
                        dataFolder.getNodeDelegate().getLookup(),
                        Lookups.singleton(SearchInfoDefinitionFactory.createFlatSearchInfo(
                                                  dataFolder.getPrimaryFile()))));
            this.root = root;
            this.dataFolder = dataFolder;
            this.isDefaultPackage = root.equals( dataFolder.getPrimaryFile() );
        }

        FileObject getRoot() {
            return root; // Used from PackageRootNode
        }


        @Override
        public String getName() {
            String relativePath = FileUtil.getRelativePath(root, dataFolder.getPrimaryFile());
            return relativePath == null ?  null : relativePath.replace('/', '.'); // NOI18N
        }

        @NbBundle.Messages({
            "LBL_CompilePackage_Action=Compile Package",
            "LBL_TestPackage_Action=Test Package",
            "LBL_DownloadDependenciesPackage_Action=Download Package Dependencies",
        })
        @Override
        public Action[] getActions( boolean context ) {

            if ( !context ) {
                if ( actions == null ) {
                    // Copy actions and leave out the PropertiesAction and FileSystemAction.
                    Action superActions[] = super.getActions( context );
                    List<Action> actionList = new ArrayList<>(superActions.length);

                    for( int i = 0; i < superActions.length; i++ ) {

                        /*if ( (i <= superActions.length - 2) && superActions[ i ] == null && superActions[i + 1] instanceof PropertiesAction ) {
                            i ++;
                            continue;
                        }
                        else if ( superActions[i] instanceof PropertiesAction ) {
                            continue;
                        }
                        else*/ if ( superActions[i] instanceof FileSystemAction ) {
                            actionList.add (null); // insert separator and new action
                            actionList.add (FileSensitiveActions.fileCommandAction(ActionProvider.COMMAND_COMPILE_SINGLE, Bundle.LBL_CompilePackage_Action(), null));
                            actionList.add (FileSensitiveActions.fileCommandAction(ActionProvider.COMMAND_TEST_SINGLE, Bundle.LBL_TestPackage_Action(), null));
                            actionList.add (FileSensitiveActions.fileCommandAction(GoActionProvider.COMMAND_DOWNLOAD_DEPENDENCIES_SINGLE, Bundle.LBL_DownloadDependenciesPackage_Action(), null));
                        }

                        actionList.add( superActions[i] );
                    }

                    actions = new Action[ actionList.size() ];
                    actionList.toArray( actions );
                }
                return actions;
            }
            else {
                return super.getActions( context );
            }
        }

        @Override
        public boolean canRename() {
            if ( isDefaultPackage ) {
                return false;
            }
            else {
                return true;
            }
        }

        @Override
        public boolean canCut () {
            return !isDefaultPackage;
        }

        /**
         * Copy handling
         */
        @Override
        public Transferable clipboardCopy () throws IOException {
            try {
                return new PackageTransferable (this, DnDConstants.ACTION_COPY);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }

        @Override
        public Transferable clipboardCut () throws IOException {
            try {
                return new PackageTransferable (this, DnDConstants.ACTION_MOVE);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }

        @Override
        public /*@Override*/ Transferable drag () throws IOException {
            try {
                return new PackageTransferable (this, DnDConstants.ACTION_NONE);
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }

        @Override
        public PasteType[] getPasteTypes(Transferable t) {
            if (t.isDataFlavorSupported(ExTransferable.multiFlavor)) {
                try {
                    MultiTransferObject mto = (MultiTransferObject) t.getTransferData (ExTransferable.multiFlavor);
                    boolean hasPackageFlavor = false;
                    for (int i=0; i < mto.getCount(); i++) {
                        DataFlavor[] flavors = mto.getTransferDataFlavors(i);
                        if (isPackageFlavor(flavors)) {
                            hasPackageFlavor = true;
                        }
                    }
                    return hasPackageFlavor ? new PasteType[0] : super.getPasteTypes (t);
                } catch (UnsupportedFlavorException | IOException e) {
                    Exceptions.printStackTrace(e);
                    return new PasteType[0];
                }
            }
            else {
                DataFlavor[] flavors = t.getTransferDataFlavors();
                if (isPackageFlavor(flavors)) {
                    return new PasteType[0];
                }
                else {
                    return super.getPasteTypes(t);
                }
            }
        }

        @Override
        public /*@Override*/ PasteType getDropType (Transferable t, int action, int index) {
            if (t.isDataFlavorSupported(ExTransferable.multiFlavor)) {
                try {
                    MultiTransferObject mto = (MultiTransferObject) t.getTransferData (ExTransferable.multiFlavor);
                    boolean hasPackageFlavor = false;
                    for (int i=0; i < mto.getCount(); i++) {
                        DataFlavor[] flavors = mto.getTransferDataFlavors(i);
                        if (isPackageFlavor(flavors)) {
                            hasPackageFlavor = true;
                        }
                    }
                    return hasPackageFlavor ? null : super.getDropType (t, action, index);
                } catch (UnsupportedFlavorException | IOException e) {
                    Exceptions.printStackTrace(e);
                    return null;
                }
            }
            else {
                DataFlavor[] flavors = t.getTransferDataFlavors();
                if (isPackageFlavor(flavors)) {
                    return null;
                }
                else {
                    return super.getDropType (t, action, index);
                }
            }
        }

        private boolean isPackageFlavor (DataFlavor[] flavors) {
            for (int i=0; i<flavors.length; i++) {
                if (SUBTYPE.equals(flavors[i].getSubType ()) && PRIMARY_TYPE.equals(flavors[i].getPrimaryType ())) {
                    //Disable pasting into package, only paste into root is allowed
                    return true;
                }
            }
            return false;
        }

        @NbBundle.Messages("MSG_InvalidPackageName=Name is not a valid Java package.")
        @Override
        public void setName(String name) {
            if (isDefaultPackage) {
                return;
            }

            String oldName = getName();
            if (oldName.equals(name)) {
                return;
            }

            if (!isValidPackageName (name)) {
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(Bundle.MSG_InvalidPackageName(), NotifyDescriptor.INFORMATION_MESSAGE));
                return;
            }

            name = name.replace('.','/')+'/';           //NOI18N
            oldName = oldName.replace('.','/')+'/';     //NOI18N
            int i;
            for (i=0; i<oldName.length() && i< name.length(); i++) {
                if (oldName.charAt(i) != name.charAt(i)) {
                    break;
                }
            }
            i--;
            int index = oldName.lastIndexOf('/',i);     //NOI18N
            String commonPrefix = index == -1 ? null : oldName.substring(0,index);
            String toCreate = (index+1 == name.length()) ? "" : name.substring(index+1);    //NOI18N
            try {
                FileObject commonFolder = commonPrefix == null ? this.root : this.root.getFileObject(commonPrefix);
                FileObject destination = commonFolder;
                StringTokenizer dtk = new StringTokenizer(toCreate,"/");    //NOI18N
                while (dtk.hasMoreTokens()) {
                    String pathElement = dtk.nextToken();
                    FileObject tmp = destination.getFileObject(pathElement);
                    if (tmp == null) {
                        tmp = destination.createFolder (pathElement);
                    }
                    destination = tmp;
                }
                FileObject source = this.dataFolder.getPrimaryFile();
                DataFolder sourceFolder = DataFolder.findFolder (source);
                DataFolder destinationFolder = DataFolder.findFolder (destination);
                DataObject[] children = sourceFolder.getChildren();
                for (int j=0; j<children.length; j++) {
                    if (children[j].getPrimaryFile().isData()) {
                        children[j].move(destinationFolder);
                    }
                }
                while (!commonFolder.equals(source)) {
                    if (source.getChildren().length==0) {
                        FileObject tmp = source;
                        source = source.getParent();
                        tmp.delete();
                    }
                    else {
                        break;
                    }
                }
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }



        @Override
        public boolean canDestroy() {
            if ( isDefaultPackage ) {
                return false;
            }
            else {
                return true;
            }
        }

        @Override
        public void destroy() throws IOException {
            FileObject parent = dataFolder.getPrimaryFile().getParent();
            // First; delete all files except packages
            DataObject ch[] = dataFolder.getChildren();
            boolean empty = true;
            for( int i = 0; ch != null && i < ch.length; i++ ) {
                if ( !ch[i].getPrimaryFile().isFolder() ) {
                    ch[i].delete();
                }
                else {
                    empty = false;
                }
            }

            // If empty delete itself
            if ( empty ) {
                super.destroy();
            }


            // Second; delete empty super packages
            while( !parent.equals( root ) && parent.getChildren().length == 0  ) {
                FileObject newParent = parent.getParent();
                parent.delete();
                parent = newParent;
            }
        }

        /**
         * Initially overridden to support CVS status labels in package nodes.
         *
         * @return annotated display name
         */
        @Override
        public String getHtmlDisplayName() {
            String name = getDisplayName();
            try {
                FileObject fo = dataFolder.getPrimaryFile();
                Set<FileObject> set = new NonRecursiveFolderSet(fo);
                FileSystem.Status status = fo.getFileSystem().getStatus();
                if (status instanceof FileSystem.HtmlStatus) {
                    name = ((FileSystem.HtmlStatus) status).annotateNameHtml(name, set);
                } else {
                    // #89138: return null if the name starts with '<' and status is not HtmlStatus
                    if (name.startsWith("<")) {
                        name = null;
                    } else {
                        name = status.annotateName(name, set);
                    }
                }
            } catch (FileStateInvalidException e) {
                // no fs, do nothing
            }
            return name;
        }

        @Override
        public String getDisplayName() {
            FileObject folder = dataFolder.getPrimaryFile();
            String path = FileUtil.getRelativePath(root, folder);
            if (path == null) {
                // ???
                return "";
            }
            return PackageDisplayUtils.getDisplayLabel( path.replace('/', '.'));
        }

        @Override
        public String getShortDescription() {
            FileObject folder = dataFolder.getPrimaryFile();
            String path = FileUtil.getRelativePath(root, folder);
            if (path == null) {
                // ???
                return "";
            }
            return PackageDisplayUtils.getToolTip(folder, path.replace('/', '.'));
        }

        @Override
        public Image getIcon (int type) {
            Image img = getMyIcon (type);

            try {
                FileObject fo = dataFolder.getPrimaryFile();
                Set<FileObject> set = new NonRecursiveFolderSet(fo);
                img = fo.getFileSystem ().getStatus ().annotateIcon (img, type, set);
            } catch (FileStateInvalidException e) {
                // no fs, do nothing
            }

            return img;
        }

        @Override
        public Image getOpenedIcon (int type) {
            Image img = getMyOpenedIcon(type);

            try {
                FileObject fo = dataFolder.getPrimaryFile();
                Set<FileObject> set = new NonRecursiveFolderSet(fo);
                img = fo.getFileSystem ().getStatus ().annotateIcon (img, type, set);
            } catch (FileStateInvalidException e) {
                // no fs, do nothing
            }

            return img;
        }


        private Image getMyIcon(int type) {
            FileObject folder = dataFolder.getPrimaryFile();
            String path = FileUtil.getRelativePath(root, folder);
            if (path == null) {
                // ??? - #103711: null cannot be returned because the icon
                // must be annotated; general package icon is returned instead
                return ImageUtilities.loadImage(PackageDisplayUtils.PACKAGE);
            }
            return PackageDisplayUtils.getIcon(folder, isLeaf());
        }

        private Image getMyOpenedIcon(int type) {
            return getMyIcon(type);
        }

        public void update() {
            fireIconChange();
            fireOpenedIconChange();
        }

        public void updateDisplayName() {
            fireNameChange(null, null);
            fireDisplayNameChange(null, null);
            fireShortDescriptionChange(null, null);
        }

        public void updateChildren() {
            boolean leaf = isLeaf();
            DataFolder df = getDataFolder();
            boolean empty = isEmpty( df );
            if ( leaf != empty ) {
                setChildren( empty ? FilterNode.Children.LEAF: df.createNodeChildren( NO_FOLDERS_FILTER ) );
                update();
            }
        }

        @Override
        public Node.PropertySet[] getPropertySets () {
            final Node.PropertySet[] properties = super.getPropertySets ();
            final List<Node.PropertySet> result = new ArrayList<>(properties.length);
            for (Node.PropertySet set : properties) {
                if (set == null) {
                    continue;
                }
                if (Sheet.PROPERTIES.equals(set.getName())) {
                    //Replace the Sheet.PROPERTIES by the new one
                    //having only the name property which does refactoring
                    set = Sheet.createPropertiesSet();
                    ((Sheet.Set)set).put(new PropertySupport.ReadWrite<String>(DataObject.PROP_NAME, String.class, Bundle.PROP_name(), Bundle.HINT_name()) {
                        @Override
                        public String getValue() {
                            return PackageViewChildren.PackageNode.this.getName();
                        }
                        @Override
                        public void setValue(String n) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                            if (!canRename()) {
                                throw new IllegalAccessException();
                            }
                            PackageViewChildren.PackageNode.this.setName(n);
                        }
                        @Override
                        public boolean canWrite() {
                            return PackageViewChildren.PackageNode.this.canRename();
                        }
                    });
                }
                result.add(set);
            }
            return result.toArray(new Node.PropertySet[result.size()]);
        }

        private DataFolder getDataFolder() {
            return getCookie(DataFolder.class);
        }

    }

    static boolean isValidPackageName(String name) {
            if (name.length() == 0) {
                //Fast check of default pkg
                return true;
            }
            StringTokenizer tk = new StringTokenizer(name,".",true); //NOI18N
            boolean delimExpected = false;
            while (tk.hasMoreTokens()) {
                String namePart = tk.nextToken();
                if (!delimExpected) {
                    if (namePart.equals(".")) { //NOI18N
                        return false;
                    }
                    for (int i=0; i< namePart.length(); i++) {
                        char c = namePart.charAt(i);
                        if (i == 0) {
                            if (!Character.isJavaIdentifierStart (c)) {
                                return false;
                            }
                        }
                        else {
                            if (!Character.isJavaIdentifierPart(c)) {
                                return false;
                            }
                        }
                    }
                }
                else {
                    if (!namePart.equals(".")) { //NOI18N
                        return false;
                    }
                }
                delimExpected = !delimExpected;
            }
            return delimExpected;
    }

    private static final class NoFoldersContainer
    implements DataObject.Container, PropertyChangeListener,
               NonRecursiveFolder {
        private DataFolder folder;
        private PropertyChangeSupport prop = new PropertyChangeSupport (this);

        public NoFoldersContainer (DataFolder folder) {
            this.folder = folder;
        }

        @Override
        public FileObject getFolder() {
            return folder.getPrimaryFile();
        }

        @Override
        public DataObject[] getChildren () {
            DataObject[] arr = folder.getChildren ();
            List<DataObject> list = new ArrayList<>(arr.length);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] instanceof DataFolder) continue;

                list.add (arr[i]);
            }
            return list.size() == arr.length ? arr : list.toArray(new DataObject[0]);
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener l) {
            prop.addPropertyChangeListener (l);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener l) {
            prop.removePropertyChangeListener (l);
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (DataObject.Container.PROP_CHILDREN.equals (evt.getPropertyName ())) {
                prop.firePropertyChange (PROP_CHILDREN, null, null);
            }
        }
    }

    final class NoFoldersDataFilter implements ChangeListener, ChangeableDataFilter, DataFilter.FileBased {

        private final ChangeSupport cs = new ChangeSupport(this);

        public NoFoldersDataFilter() {
            VisibilityQuery.getDefault().addChangeListener(WeakListeners.change(this, VisibilityQuery.getDefault()));
        }

        @Override
        public boolean acceptDataObject(DataObject obj) {
            return acceptFileObject(obj.getPrimaryFile());
        }

        @Override
        public void stateChanged( ChangeEvent e) {
            cs.fireChange();
        }

        @Override
        public void addChangeListener( ChangeListener listener ) {
            cs.addChangeListener(listener);
        }

        @Override
        public void removeChangeListener( ChangeListener listener ) {
            cs.removeChangeListener(listener);
        }

        @Override
        public boolean acceptFileObject(FileObject fo) {
            return  fo.isValid() && VisibilityQuery.getDefault().isVisible(fo) && fo.isData() && group.contains(fo);
        }

    }

    static class PackageTransferable extends ExTransferable.Single {

        private PackageNode node;

        public PackageTransferable (PackageNode node, int operation) throws ClassNotFoundException {
            super(new DataFlavor(PACKAGE_FLAVOR.format(new Object[] {operation}), null, PackageNode.class.getClassLoader()));
            this.node = node;
        }

        @Override
        protected Object getData() throws IOException, UnsupportedFlavorException {
            return this.node;
        }
    }

    /**
     * FileObject set that represents package. It means
     * that it's content must not be processed recursively.
     */
    private static class NonRecursiveFolderSet extends HashSet<FileObject> implements NonRecursiveFolder {

        private final FileObject folder;

        /**
         * Creates set with one element, the folder.
         */
        public NonRecursiveFolderSet(FileObject folder) {
            this.folder = folder;
            add(folder);
        }

        @Override
        public FileObject getFolder() {
            return folder;
        }
    }
}
