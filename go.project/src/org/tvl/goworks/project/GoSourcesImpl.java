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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.api.queries.SharabilityQuery;
import org.netbeans.spi.project.support.GenericSources;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.ChangeSupport;
import org.openide.util.Mutex;

/**
 *
 * @author Sam Harwell
 */
final class GoSourcesImpl implements Sources, ChangeListener {

    /**
     * Go package root sources type.
     * @see org.netbeans.api.project.Sources
     */
    public static final String SOURCES_TYPE_GO = "go"; // NOI18N

    private final GoProject _project;
    private final ChangeSupport _changeSupport = new ChangeSupport(this);

    private boolean _dirty;
    private final Map<String,SourceGroup[]> _cachedGroups = new ConcurrentHashMap<String,SourceGroup[]>();
    private long _eventId;
    private final FireAction _fireTask = new FireAction();

    public GoSourcesImpl(GoProject project) {
        this._project = project;
    }

    @Override
    public SourceGroup[] getSourceGroups(final String type) {
        final SourceGroup[] cachedGroups = _cachedGroups.get(type);
        if (cachedGroups != null) {
            return cachedGroups;
        }

        return ProjectManager.mutex().readAccess(new Mutex.Action<SourceGroup[]>() {
            @Override
            public SourceGroup[] run() {
                SourceGroup[] groups;
                if (type.equals(GoSourcesImpl.SOURCES_TYPE_GO)) {
                    final FileObject sourcesDirectory = _project.getSourceRoot();
                    if (sourcesDirectory != null) {
                        SourceGroup group = new SourceGroup() {
                            final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

                            @Override
                            public FileObject getRootFolder() {
                                return sourcesDirectory;
                            }

                            @Override
                            public String getName() {
                                return sourcesDirectory.getName();
                            }

                            @Override
                            public String getDisplayName() {
                                return sourcesDirectory.getName();
                            }

                            @Override
                            public Icon getIcon(boolean opened) {
                                return null;
//                                return opened ? icon : openedIcon;
                            }

                            @Override
                            public boolean contains(FileObject file) {
                                if (file == sourcesDirectory) {
                                    return true;
                                }

                                String path = FileUtil.getRelativePath(sourcesDirectory, file);
                                if (path == null) {
                                    return false;
                                }

                                if (file.isFolder()) {
                                    path += "/"; // NOI18N
                                }

                                Project p = _project;
                                if (file.isFolder() && file != p.getProjectDirectory() && ProjectManager.getDefault().isProject(file)) {
                                    // #67450: avoid actually loading the nested project.
                                    return false;
                                }

                                // XXX disabled for typed source roots; difficult to make fast (#97215)
                                Project owner = FileOwnerQuery.getOwner(file);
                                if (owner != null && owner != p) {
                                    return false;
                                }

                                if (SharabilityQuery.getSharability(file) == SharabilityQuery.Sharability.NOT_SHARABLE) {
                                    return false;
                                } // else MIXED, UNKNOWN, or SHARABLE

                                return true;
                            }

                            @Override
                            public void addPropertyChangeListener(PropertyChangeListener listener) {
                                pcs.addPropertyChangeListener(listener);
                            }

                            @Override
                            public void removePropertyChangeListener(PropertyChangeListener listener) {
                                pcs.removePropertyChangeListener(listener);
                            }
                        };

                        groups = new SourceGroup[] { group };

                    } else {
                        groups = new SourceGroup[0];
                    }
                } else if (type.equals(Sources.TYPE_GENERIC)) {
                    groups = new SourceGroup[] {
                        GenericSources.group(_project, _project.getProjectDirectory(), "go-generic", "Go", null, null)
                    };
                } else {
                    groups = new SourceGroup[0];
                }

                long myEventId;
                synchronized (GoSourcesImpl.this) {
                    if (_dirty) {
                        _dirty = false;
                    }

                    myEventId = ++_eventId;
                }

                synchronized (GoSourcesImpl.this) {
                    if (myEventId == _eventId) {
                        GoSourcesImpl.this._cachedGroups.put(type, groups);
                    }
                }

                return groups;
            }
        });
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        _changeSupport.addChangeListener(listener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
        _changeSupport.removeChangeListener(listener);
    }

    private void fireChange() {
        synchronized (this) {
            _cachedGroups.clear();   //threading: CHM.clear is not atomic, the getSourceGroup may return staled data which is not a problem in this case.
            _dirty = true;
        }

        ProjectManager.mutex().postReadRequest(_fireTask.activate());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.fireChange();
    }

    private class FireAction implements Runnable {

        private AtomicBoolean fire = new AtomicBoolean();

        @Override
        public void run() {
            if (fire.getAndSet(false)) {
                _changeSupport.fireChange();
            }
        }

        FireAction activate() {
            this.fire.set(true);
            return this;
        }
    }
}
