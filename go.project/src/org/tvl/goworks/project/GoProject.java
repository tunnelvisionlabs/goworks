/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.classpath.GlobalPathRegistry;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.ProjectOpenedHook;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Sam Harwell
 */
public class GoProject implements Project {
    // -J-Dorg.tvl.goworks.project.GoProject.level=FINE
    private static final Logger LOGGER = Logger.getLogger(GoProject.class.getName());

    public static final String SOURCE = "go/classpath/source";

    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lkp;

    public GoProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    public FileObject getSourceRoot() {
        return getProjectDirectory().getFileObject("src");
    }

    FileObject getProjectDataFolder(boolean create) {
        FileObject result =
                projectDir.getFileObject(GoProjectFactory.PROJECT_DIR);
        if (result == null && create) {
            try {
                result = projectDir.createFolder(GoProjectFactory.PROJECT_DIR);
            } catch (IOException ioe) {
                LOGGER.log(Level.WARNING, "An exception occurred while getting the project data folder.", ioe);
                Exceptions.printStackTrace(ioe);
            }
        }
        return result;
    }

    //The project type's capabilities are registered in the project's lookup:
    @Override
    public Lookup getLookup() {
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{
                        this,
                        state, //allow outside code to mark the project as needing saving
                        new GoActionProvider(this), //Provides standard actions like Build and Clean
                        new GoDeleteOperation(),
                        new GoCopyOperation(this),
                        new Info(), //Project information implementation
                        new GoProjectLogicalView(this), //Logical view of project implementation
                        new ProjectOpenedHookImpl(),
                        new ClassPathProviderImpl(this),
                    });
        }
        return lkp;
    }

    private final class GoDeleteOperation implements DeleteOperationImplementation {

        @Override
        public void notifyDeleting() throws IOException {
        }

        @Override
        public void notifyDeleted() throws IOException {
        }

        @Override
        public List<FileObject> getMetadataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }

        @Override
        public List<FileObject> getDataFiles() {
            List<FileObject> dataFiles = new ArrayList<FileObject>();
            return dataFiles;
        }
    }

    private final class GoCopyOperation implements CopyOperationImplementation {

        private final GoProject project;
        private final FileObject projectDir;

        public GoCopyOperation(GoProject project) {
            this.project = project;
            this.projectDir = project.getProjectDirectory();
        }

        @Override
        public List<FileObject> getMetadataFiles() {
            return Collections.emptyList();
        }

        @Override
        public List<FileObject> getDataFiles() {
            return Collections.emptyList();
        }

        @Override
        public void notifyCopying() throws IOException {
        }

        @Override
        public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
        }
    }

    private final class Info implements ProjectInformation {

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(
                    "org/tvl/goworks/project/ui/resources/icon1.png"));
        }

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return GoProject.this;
        }
    }

    private final class ProjectOpenedHookImpl extends ProjectOpenedHook {

        @Override
        protected void projectOpened() {
            // register project's classpaths to GlobalPathRegistry
            FileObject sourceRoot = getSourceRoot();
            if (sourceRoot != null && sourceRoot.isFolder()) {
                ClassPath sourceRootClassPath = ClassPath.getClassPath(sourceRoot, SOURCE);
                if (sourceRootClassPath != null) {
                    GlobalPathRegistry.getDefault().register(GoProject.SOURCE, new ClassPath[] { sourceRootClassPath });
                }
            }
        }

        @Override
        protected void projectClosed() {
            FileObject sourceRoot = getSourceRoot();
            if (sourceRoot != null && sourceRoot.isFolder()) {
                ClassPath sourceRootClassPath = ClassPath.getClassPath(sourceRoot, SOURCE);
                if (sourceRootClassPath != null) {
                    GlobalPathRegistry.getDefault().unregister(GoProject.SOURCE, new ClassPath[] { sourceRootClassPath });
                }
            }
        }

    }

}
