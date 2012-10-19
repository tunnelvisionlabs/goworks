/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.ExtensionList;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiDataObject.Entry;
import org.openide.loaders.UniFileLoader;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "GoLoader_Name=Go Source Object"
})
@DataObject.Registration(
    displayName="#GoLoader_Name",
    mimeType=GoEditorKit.GO_MIME_TYPE,
    position=99999)
@ActionReferences({
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 100, separatorAfter = 200),
    @ActionReference(id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 300),
    @ActionReference(id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 400, separatorAfter = 500),
    @ActionReference(id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 600),
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 700, separatorAfter = 800),
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 900, separatorAfter = 1000),
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 1100, separatorAfter = 1200),
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 1300),
    @ActionReference(id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"), path = GoDataLoader.ACTIONS_CONTEXT, position = 1400),
})
public class GoDataLoader extends UniFileLoader {
    public static final String ACTIONS_CONTEXT = "Loaders/" + GoEditorKit.GO_MIME_TYPE + "/Actions";

    public GoDataLoader() {
        super("org.tvl.goworks.editor.go.GoDataObject");
    }

    @Override
    protected void initialize() {
        super.initialize();
        ExtensionList extensions = new ExtensionList();
        extensions.addExtension(".go");
        extensions.addMimeType(GoEditorKit.GO_MIME_TYPE);
        setExtensions(extensions);
    }

    @Override
    protected String actionsContext() {
        return ACTIONS_CONTEXT;
    }

    @Override
    protected String defaultDisplayName() {
        return Bundle.GoLoader_Name();
    }

    @Override
    protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
        if (getExtensions().isRegistered(primaryFile)) {
            return new GoDataObject(primaryFile, this);
        }

        return null;
    }

    @Override
    protected FileObject findPrimaryFile(FileObject fo) {
        // never recognize folders
        if (fo.isFolder()) {
            return null;
        }

        return super.findPrimaryFile(fo);
    }

    @Override
    protected Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
        return super.createPrimaryEntry(obj, primaryFile);
    }

}
