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
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCodeElementModel implements CodeElementModel {
    @NonNull
    private final String name;
    @NullAllowed
    private final Project project;
    @NonNull
    private final String packagePath;
    @NullAllowed
    private final FileModelImpl file;

    private boolean frozen;

    public AbstractCodeElementModel(@NonNull String name, @NullAllowed Project project, @NonNull String packagePath) {
        this(name, project, packagePath, null);
    }

    public AbstractCodeElementModel(@NonNull String name, @NonNull FileModelImpl file) {
        this(name, file.getProject(), file.getPackagePath(), file);
    }

    private AbstractCodeElementModel(@NonNull String name, @NullAllowed Project project, @NonNull String packagePath, @NullAllowed FileModelImpl file) {
        Parameters.notNull("name", name);
        Parameters.notNull("packagePath", packagePath);

        this.name = name;
        this.project = project;
        this.packagePath = packagePath;
        this.file = file;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public final void freeze() {
        if (isFrozen()) {
            return;
        }

        freezeImpl();
        frozen = true;
    }

    @Override
    public PackageModelImpl getPackage() {
        return getCodeModelCache().getUniquePackage(project, packagePath);
    }

    @Override
    public String getName() {
        return name;
    }

    @CheckForNull
    public FileModelImpl getFile() {
        return file;
    }

    @Override
    public abstract Collection<? extends AbstractCodeElementModel> getMembers();

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(String name) {
        return CodeModelCacheImpl.findElementsByName(getMembers(), name);
    }

    public Project getProject() {
        return project;
    }

    public String getPackagePath() {
        return packagePath;
    }

    protected CodeModelCacheImpl getCodeModelCache() {
        return CodeModelCacheImpl.getInstance();
    }

    protected void freezeImpl() {
    }

    protected void ensureModifiable() {
        if (isFrozen()) {
            throw new IllegalStateException("The object is frozen.");
        }
    }
}
