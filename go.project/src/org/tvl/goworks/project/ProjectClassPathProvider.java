/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Supplies classpath information according to project file owner.
 * @author Jesse Glick
 */
@ServiceProvider(service=ClassPathProvider.class, position=100)
public class ProjectClassPathProvider implements ClassPathProvider {

    private static final Logger LOG = Logger.getLogger(ProjectClassPathProvider.class.getName());

    /** Default constructor for lookup. */
    public ProjectClassPathProvider() {}

    @Override
    public ClassPath findClassPath(FileObject file, String type) {
        Project p = FileOwnerQuery.getOwner(file);
        LOG.log(Level.FINE, "findClassPath({0}, {1}) on project {2}", new Object[] {file, type, p});
        if (p != null) {
            ClassPathProvider cpp = p.getLookup().lookup(ClassPathProvider.class);
            if (cpp != null) {
                final ClassPath result = cpp.findClassPath(file, type);
                LOG.log(Level.FINE, "findClassPath({0}, {1}) -> {2} from {3}", new Object[] {file, type, result, cpp});
                return result;
            } else {
                LOG.log(Level.FINE, "cpp.findClassPath({0}, {1}) -> null", new Object[] {file, type});
                return null;
            }
        } else {
            return null;
        }
    }

}
