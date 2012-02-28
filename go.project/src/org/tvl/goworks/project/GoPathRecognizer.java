/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizer;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=PathRecognizer.class, position=100)
public class GoPathRecognizer extends PathRecognizer {
    private final Set<String> MIME_TYPES = Collections.singleton("text/x-go");
    private final Set<String> SOURCES = Collections.singleton(GoProject.SOURCE);
    private static final Set<String> BINARIES = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(new String[] {
        ClassPath.BOOT,
        ClassPath.COMPILE
    })));

    @Override
    public Set<String> getSourcePathIds() {
        return SOURCES;
    }

    @Override
    public Set<String> getBinaryLibraryPathIds() {
        return BINARIES;
    }

    @Override
    public Set<String> getLibraryPathIds() {
        return null;
    }

    @Override
    public Set<String> getMimeTypes() {
        return MIME_TYPES;
    }

}
