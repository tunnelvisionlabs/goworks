/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.util.EnumMap;
import java.util.Map;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.spi.java.classpath.ClassPathFactory;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

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
    private final boolean isStandardLibrary;
    private final FileObject sourceRoot;

    // GuardedBy(cache)
    private final Map<ClassPathCache, ClassPath> cache = new EnumMap<ClassPathCache, ClassPath>(ClassPathCache.class);

    public ClassPathProviderImpl(GoProject project) {
        assert project != null;
        this.project = project;
        this.isStandardLibrary = project.isStandardLibrary();
        this.sourceRoot = project.getSourceRoot();
        assert sourceRoot != null;
    }

    private ClassPath getSourcePath(FileObject file) {
        ClassPath cp = null;
        synchronized (cache) {
            cp = cache.get(ClassPathCache.SOURCE);
            if (cp == null) {
                cp = ClassPathFactory.createClassPath(new SourcePathImplementation(project, sourceRoot));
                cache.put(ClassPathCache.SOURCE, cp);
            }
        }
        return cp;
    }

    private ClassPath getPlatformPath(FileObject file) {
        ClassPath cp = null;
        synchronized (cache) {
            cp = cache.get(ClassPathCache.PLATFORM);
            if (cp == null) {
                cp = ClassPathFactory.createClassPath(new SourcePathImplementation(project, sourceRoot));
                cache.put(ClassPathCache.PLATFORM, cp);
            }
        }
        return cp;
    }

    @Override
    public ClassPath findClassPath(FileObject file, String type) {
        if (GoProject.SOURCE.equals(type)) {
            if (isStandardLibrary || FileUtil.getRelativePath(sourceRoot, file) == null) {
                return null;
            }

            return getSourcePath(file);
        } else if (GoProject.PLATFORM.equals(type)) {
            if (!isStandardLibrary || FileUtil.getRelativePath(sourceRoot, file) == null) {
                return null;
            }

            return getPlatformPath(file);
        }

        return null;
    }

}
