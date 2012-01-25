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
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.tvl.goworks.editor.go.codemodel.FileModel;

/**
 *
 * @author Sam Harwell
 */
public class FileModelImpl extends AbstractCodeElementModel implements FileModel {
    private final FileObject fileObject;
    private final FreezableArrayList<PackageDeclarationModelImpl> packageDeclarations = new FreezableArrayList<PackageDeclarationModelImpl>();
    private final FreezableArrayList<ImportDeclarationModelImpl> importDeclarations = new FreezableArrayList<ImportDeclarationModelImpl>();
    private final FreezableArrayList<TypeModelImpl> types = new FreezableArrayList<TypeModelImpl>();
    private final FreezableArrayList<ConstModelImpl> constants = new FreezableArrayList<ConstModelImpl>();
    private final FreezableArrayList<VarModelImpl> vars = new FreezableArrayList<VarModelImpl>();
    private final FreezableArrayList<FunctionModelImpl> functions = new FreezableArrayList<FunctionModelImpl>();
    @SuppressWarnings("unchecked")
    private final ProxyCollection<AbstractCodeElementModel> codeElements = new ProxyCollection<AbstractCodeElementModel>(packageDeclarations, importDeclarations, types, constants, vars, functions);

    public FileModelImpl(@NonNull FileObject fileObject, @NullAllowed Project project, @NonNull String packagePath) {
        super(fileObject.getNameExt(), project, packagePath);
        this.fileObject = fileObject;
    }

    @Override
    public FileModelImpl getFile() {
        return this;
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    @Override
    public Collection<AbstractCodeElementModel> getMembers() {
        return codeElements;
    }

    @Override
    public Collection<PackageDeclarationModelImpl> getPackageDeclarations() {
        return packageDeclarations;
    }

    @Override
    public Collection<ImportDeclarationModelImpl> getImportDeclarations() {
        return importDeclarations;
    }

    @Override
    public Collection<TypeModelImpl> getTypes() {
        return types;
    }

    @Override
    public Collection<TypeModelImpl> getTypes(String name) {
        return CodeModelCacheImpl.findElementsByName(getTypes(), name);
    }

    @Override
    public Collection<ConstModelImpl> getConstants() {
        return constants;
    }

    @Override
    public Collection<ConstModelImpl> getConstants(String name) {
        return CodeModelCacheImpl.findElementsByName(getConstants(), name);
    }

    @Override
    public Collection<VarModelImpl> getVars() {
        return vars;
    }

    @Override
    public Collection<VarModelImpl> getVars(String name) {
        return CodeModelCacheImpl.findElementsByName(getVars(), name);
    }

    @Override
    public Collection<FunctionModelImpl> getFunctions() {
        return functions;
    }

    @Override
    public Collection<FunctionModelImpl> getFunctions(String name) {
        return CodeModelCacheImpl.findElementsByName(getFunctions(), name);
    }

    @Override
    protected void freezeImpl() {
        packageDeclarations.freeze();
        importDeclarations.freeze();
        types.freeze();
        constants.freeze();
        vars.freeze();
        functions.freeze();
    }

}
