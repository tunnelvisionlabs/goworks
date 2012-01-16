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
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author sam
 */
@ServiceProvider(service=CodeModelCache.class)
public class CodeModelCacheImpl implements CodeModelCache {
    private static CodeModelCacheImpl instance;

    private final Map<Project, CodeModelProjectCache> projectCaches =
        new WeakHashMap<Project, CodeModelProjectCache>();

    @Override
    public Collection<? extends PackageModel> getPackages() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PackageModel getPackage(Project project, String name) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Collection<FileModelImpl> getFiles(PackageModelImpl packageModel) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public CodeModelProjectCache getProjectCache(@NonNull Project project, boolean create) {
        synchronized (projectCaches) {
            CodeModelProjectCache cache = projectCaches.get(project);
            if (cache == null && create) {
                throw new UnsupportedOperationException("Not implemented yet.");
            }

            return cache;
        }
    }

    public void updateFile(@NonNull FileModelImpl fileModel) {
        assert fileModel.isFrozen();
        Project project = fileModel.getProject();
        CodeModelProjectCache projectCache = getProjectCache(project, true);
        projectCache.updateFile(fileModel);
    }

    public static synchronized CodeModelCacheImpl getInstance() {
        if (instance == null) {
            instance = (CodeModelCacheImpl)Lookup.getDefault().lookup(CodeModelCache.class);
        }

        return instance;
    }

}
