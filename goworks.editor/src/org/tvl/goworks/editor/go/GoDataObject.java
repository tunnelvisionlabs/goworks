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
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.tvl.goworks.editor.GoEditorKit;

@NbBundle.Messages({
    "GoResolver=Go Files",
    "CTL_SourceTabCaption=&Source"
})
@MIMEResolver.ExtensionRegistration(
    position=100,
    displayName="#GoResolver",
    extension="go",
    mimeType=GoEditorKit.GO_MIME_TYPE)
public class GoDataObject extends MultiDataObject {

    public GoDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor(GoEditorKit.GO_MIME_TYPE, true);
    }

    @Override
    protected Node createNodeDelegate() {
        return GoDataSupport.createGoNode(getPrimaryFile());
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
        displayName="#CTL_SourceTabCaption",
        iconBase="",
        persistenceType=TopComponent.PERSISTENCE_ONLY_OPENED,
        preferredID="",
        mimeType=GoEditorKit.GO_MIME_TYPE,
        position=2000)
    public static MultiViewEditorElement createMultiViewEditorElement(Lookup context) {
        return new MultiViewEditorElement(context);
    }
}
