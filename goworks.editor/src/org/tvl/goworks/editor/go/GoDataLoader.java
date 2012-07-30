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
public class GoDataLoader extends UniFileLoader {

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
        return "Loaders/" + GoEditorKit.GO_MIME_TYPE + "/Actions/";
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
