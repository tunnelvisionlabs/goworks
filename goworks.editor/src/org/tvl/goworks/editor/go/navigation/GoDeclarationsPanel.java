/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.concurrent.TimeUnit;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoDataObject;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;

@NbBundle.Messages({
    "LBL_declarations=Declarations",
    "HINT_declarations=Declarations",
})
@Registration(mimeType = GoEditorKit.GO_MIME_TYPE, position = 100, displayName = "#LBL_declarations")
public class GoDeclarationsPanel implements NavigatorPanel {

    private static volatile GoDeclarationsPanel INSTANCE;

    private GoDeclarationsPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_declarations();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_declarations();
    }

    @Override
    public GoDeclarationsPanelUI getComponent() {
        return getDeclarationsPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
        scheduleTaskManagerUpdate(context.lookup(DataObject.class));
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
        scheduleTaskManagerUpdate(null);
        getDeclarationsPanelUI().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getDeclarationsPanelUI().getLookup();
    }

    private void scheduleTaskManagerUpdate(DataObject dataObject) {
        if (dataObject != null && !(dataObject instanceof GoDataObject)) {
            return;
        }

        JTextComponent currentComponent = EditorRegistry.lastFocusedComponent();
        if (currentComponent == null) {
            return;
        }

        Document document = currentComponent.getDocument();
        DataObject documentDataObject = document != null ? NbEditorUtilities.getDataObject(document) : null;
        VersionedDocument versionedDocument;
        if (dataObject != null && (documentDataObject == null || !dataObject.equals(documentDataObject))) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(dataObject.getPrimaryFile());
        } else if (document != null) {
            versionedDocument = VersionedDocumentUtilities.getVersionedDocument(document);
        } else {
            return;
        }

        ParseContext context = new ParseContext(ParserTaskScheduler.MANUAL_TASK_SCHEDULER, versionedDocument);
        Lookup.getDefault().lookup(ParserTaskManager.class).scheduleData(context, GoParserDataDefinitions.NAVIGATOR_UI_VISIBLE, 0, TimeUnit.MILLISECONDS);
    }

    private synchronized GoDeclarationsPanelUI getDeclarationsPanelUI() {
        if (this.component == null) {
            this.component = new GoDeclarationsPanelUI();
        }

        return this.component;
    }

    public static GoDeclarationsPanel getInstance() {
        return INSTANCE;
    }

}
