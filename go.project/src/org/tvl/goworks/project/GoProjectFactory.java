/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.io.IOException;
import java.util.Enumeration;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=ProjectFactory.class)
public class GoProjectFactory implements ProjectFactory {

    public static final String PROJECT_DIR = "nbgoproject";
    public static final String SRC_DIR = "src";

    //Specifies when a project is a project, i.e.,
    //if the project directory "texts" is present:
    @Override
    public boolean isProject(FileObject projectDirectory) {
        FileObject goprojectDir = projectDirectory.getFileObject(PROJECT_DIR);
        if (goprojectDir != null && goprojectDir.isFolder()) {
            return true;
        }

        FileObject goSrcDir = projectDirectory.getFileObject(SRC_DIR);
        if (goSrcDir != null && goSrcDir.isFolder()) {
            // check for a Go file underneath this folder
            for (Enumeration<? extends FileObject> enumeration = goSrcDir.getChildren(true); enumeration.hasMoreElements(); ) {
                FileObject child = enumeration.nextElement();
                if (!child.isFolder() && child.hasExt("go")) {
                    return true;
                }
            }
        }

        return false;
    }

    //Specifies when the project will be opened, i.e.,
    //if the project exists:
    @Override
    public Project loadProject(FileObject dir, ProjectState state) throws IOException {
        return isProject(dir) ? new GoProject(dir, state) : null;
    }

    @Override
    public void saveProject(final Project project) throws IOException, ClassCastException {
        FileObject projectRoot = project.getProjectDirectory();

        //Force creation of the texts dir if it was deleted:
        ((GoProject) project).getProjectDataFolder(true);
    }

}