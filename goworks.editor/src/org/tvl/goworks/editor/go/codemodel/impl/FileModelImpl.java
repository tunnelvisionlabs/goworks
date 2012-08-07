/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.Collection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.tvl.goworks.editor.go.codemodel.CodeElementPositionRegion;
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

    public FileModelImpl(@NonNull String name, @NullAllowed Project project, @NonNull String packagePath) {
        super(name, project, packagePath);
        this.fileObject = null;
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        if (getFileObject() == null) {
            return null;
        }

        OffsetRegion region = new OffsetRegion(0, 0);
        return new CodeElementPositionRegionImpl(this, region);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (getFileObject() == null) {
            return null;
        }

        OffsetRegion region = new OffsetRegion(0, 0);
        return new CodeElementPositionRegionImpl(this, region);
    }

    @Override
    public FileModelImpl getFile() {
        return this;
    }

    @CheckForNull
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
        super.freezeImpl();
    }

}
