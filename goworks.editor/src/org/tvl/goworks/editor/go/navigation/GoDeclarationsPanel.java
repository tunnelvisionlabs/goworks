/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import org.antlr.netbeans.editor.navigation.AbstractNavigatorPanel;
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
public class GoDeclarationsPanel extends AbstractNavigatorPanel<GoDeclarationsPanelUI> {

    private static volatile GoDeclarationsPanel INSTANCE;

    public GoDeclarationsPanel() {
        super(GoEditorKit.GO_MIME_TYPE, GoParserDataDefinitions.NAVIGATOR_UI_VISIBLE);
    }

    @Override
    public String getDisplayName() {
        return Bundle.LBL_declarations();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_declarations();
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
        getComponent().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getComponent().getLookup();
    }

    @Override
    protected void scheduleTaskManagerUpdate(DataObject dataObject) {
        if (dataObject != null && !(dataObject instanceof GoDataObject)) {
            return;
        }

        super.scheduleTaskManagerUpdate(dataObject);
    }

    @Override
    protected GoDeclarationsPanelUI createPanelUI() {
        return new GoDeclarationsPanelUI();
    }

    public static GoDeclarationsPanel getInstance() {
        return INSTANCE;
    }

}
