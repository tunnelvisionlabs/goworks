/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
