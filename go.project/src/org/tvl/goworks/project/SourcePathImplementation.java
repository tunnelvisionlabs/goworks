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
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.java.classpath.ClassPathImplementation;
import org.netbeans.spi.java.classpath.PathResourceImplementation;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Sam Harwell
 */
final class SourcePathImplementation implements ClassPathImplementation {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final GoProject project;
    private final List<PathResourceImplementation> resources;
    private final FileObject sourceRoot;

    public SourcePathImplementation(GoProject project, FileObject sourceRoot) {
        assert project != null;
        assert sourceRoot != null;

        this.project = project;
        this.sourceRoot = sourceRoot;
        this.resources = this.getPath();
    }

    @Override
    public List<PathResourceImplementation> getResources() {
        assert resources != null;
        return resources;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private void invalidate() {
        support.firePropertyChange(PROP_RESOURCES, null, null);
    }

    private List<PathResourceImplementation> getPath() {
        List<PathResourceImplementation> result = new ArrayList<>();
        result.add(ClassPathSupport.createResource(this.sourceRoot.toURL()));
        return Collections.unmodifiableList(result);
    }

}
