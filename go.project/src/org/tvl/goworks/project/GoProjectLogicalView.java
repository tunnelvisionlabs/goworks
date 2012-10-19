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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.ProjectSensitiveActions;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_BuildAction_Name=Build",
    "LBL_CleanBuildAction_Name=Clean and Build",
    "LBL_CleanAction_Name=Clean",
    "LBL_InstallAction_Name=Install",
    "LBL_RunAction_Name=Run",
    "LBL_DebugAction_Name=Debug",
    "LBL_ProfileAction_Name=Profile",
    "LBL_TestAction_Name=Test",
    "LBL_SourcePackages=Source Packages",
})
public class GoProjectLogicalView implements LogicalViewProvider {
    // -J-Dorg.tvl.goworks.project.GoProjectLogicalView.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoProjectLogicalView.class.getName());

    @StaticResource
    private static final String PROJECT_ICON = "org/tvl/goworks/project/ui/resources/icon1.png";

    private final GoProject project;

    public GoProjectLogicalView(GoProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        try {
            //Get the Text directory, creating if deleted
            FileObject root = project.getProjectDirectory();

            //Get the DataObject that represents it
            DataFolder rootDataObject = DataFolder.findFolder(root);

            //Get its default node-we'll wrap our node around it to change the
            //display name, icon, etc
            Node realRootFolderNode = rootDataObject.getNodeDelegate();

            //This FilterNode will be our project node
            return new TextNode(realRootFolderNode, project);

        } catch (DataObjectNotFoundException donfe) {
            LOGGER.log(Level.WARNING, "An exception occurred while opening a project.", donfe);
            //Fallback-the directory couldn't be created -
            //read-only filesystem or something evil happened
            return new AbstractNode(Children.LEAF);
        }
    }

    /** This is the node you actually see in the project tab for the project */
    private static final class TextNode extends AbstractNode {

        final GoProject project;

        public TextNode(Node node, GoProject project) throws DataObjectNotFoundException {
            super(NodeFactorySupport.createCompositeChildren(project, "Projects/" + GoProject.GO_PROJECT_ID + "/Nodes"),
                Lookups.singleton(project));
            this.project = project;
        }

        @Override
        public Action[] getActions(boolean arg0) {
            Action[] nodeActions = {
                CommonProjectActions.newFileAction(),
                null,
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_BUILD, Bundle.LBL_BuildAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_REBUILD, Bundle.LBL_CleanBuildAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_CLEAN, Bundle.LBL_CleanAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(GoActionProvider.COMMAND_INSTALL, Bundle.LBL_InstallAction_Name(), null),
                null,
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_RUN, Bundle.LBL_RunAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_DEBUG, Bundle.LBL_DebugAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_PROFILE, Bundle.LBL_ProfileAction_Name(), null),
                ProjectSensitiveActions.projectCommandAction(ActionProvider.COMMAND_TEST, Bundle.LBL_TestAction_Name(), null),
                null,
                CommonProjectActions.copyProjectAction(),
                CommonProjectActions.deleteProjectAction(),
                CommonProjectActions.setAsMainProjectAction(),
                CommonProjectActions.closeProjectAction(),
            };
            return nodeActions;
        }

        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(PROJECT_ICON);
        }

        @Override
        public Image getOpenedIcon(int type) {
            return ImageUtilities.loadImage(PROJECT_ICON);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }

    }

    @Override
    public Node findPath(Node root, Object target) {
        //leave unimplemented for now
        return null;
    }

}
