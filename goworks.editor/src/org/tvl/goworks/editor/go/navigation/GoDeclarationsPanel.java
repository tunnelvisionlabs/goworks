/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import org.netbeans.spi.navigator.NavigatorPanel;
import org.netbeans.spi.navigator.NavigatorPanel.Registration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.GoEditorKit;

@NbBundle.Messages({
    "LBL_templates=Templates",
    "HINT_templates=Templates",
    "LBL_declarations=Declarations"
})
@Registration(mimeType = GoEditorKit.GO_MIME_TYPE, position = 100, displayName = "#LBL_declarations")
public class GoDeclarationsPanel implements NavigatorPanel {

    private static volatile GoDeclarationsPanel INSTANCE;

    private GoDeclarationsPanelUI component;

    @Override
    public String getDisplayName() {
        return Bundle.LBL_templates();
    }

    @Override
    public String getDisplayHint() {
        return Bundle.HINT_templates();
    }

    @Override
    public GoDeclarationsPanelUI getComponent() {
        return getDeclarationsPanelUI();
    }

    @Override
    public void panelActivated(Lookup context) {
        INSTANCE = this;
    }

    @Override
    public void panelDeactivated() {
        INSTANCE = null;
        getDeclarationsPanelUI().showWaitNode();
    }

    @Override
    public Lookup getLookup() {
        return getDeclarationsPanelUI().getLookup();
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
