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

import java.util.EnumMap;
import java.util.Map;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.spi.java.classpath.ClassPathFactory;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
/**
 * Defines the various (BOOT, SOURCE, TEST) class paths for a PHP project.
 */
public final class ClassPathProviderImpl implements ClassPathProvider/*, GoSourcePathImplementation*/ {

    /**
     * Constants for different cached classpaths.
     */
    private static enum ClassPathCache {
        PLATFORM,
        SOURCE,
        TEST
    }

    private final GoProject project;
    private final FileObject projectDirectory;

    // GuardedBy(cache)
    private final Map<ClassPathCache, ClassPath> cache = new EnumMap<ClassPathCache, ClassPath>(ClassPathCache.class);

    public ClassPathProviderImpl(GoProject project) {
        assert project != null;
        this.project = project;
        this.projectDirectory = project.getProjectDirectory();
        assert projectDirectory != null;
    }

    private ClassPath getSourcePath(FileObject file) {
        ClassPath cp = null;
        synchronized (cache) {
            cp = cache.get(ClassPathCache.SOURCE);
            if (cp == null) {
                cp = ClassPathFactory.createClassPath(new SourcePathImplementation(project, projectDirectory));
                cache.put(ClassPathCache.SOURCE, cp);
            }
        }
        return cp;
    }

    @Override
    public ClassPath findClassPath(FileObject file, String type) {
        if (GoProject.SOURCE.equals(type)) {
            return getSourcePath(file);
        }

        return null;
    }

}
