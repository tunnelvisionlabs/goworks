/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public class PackageModelImpl extends AbstractCodeElementModel implements PackageModel {
    private final Map<String, FileModelImpl> files = new HashMap<String, FileModelImpl>();

    public PackageModelImpl(String name, Project project, String path) {
        super(name, project, path);
    }

    @Override
    public PackageModelImpl getPackage() {
        return this;
    }

    @Override
    public Collection<FileModelImpl> getFiles() {
        return files.values();
    }

    @Override
    public Collection<AbstractCodeElementModel> getMembers() {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        for (FileModelImpl file : getFiles()) {
            members.addAll(file.getMembers());
        }

        return members;
    }

    @Override
    public Collection<AbstractCodeElementModel> getMembers(@NonNull String name) {
        Parameters.notNull("name", name);

        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        for (FileModelImpl file : getFiles()) {
            members.addAll(file.getMembers(name));
        }

        return members;
    }

    @Override
    public Collection<TypeModelImpl> getTypes() {
        List<TypeModelImpl> types = new ArrayList<TypeModelImpl>();
        for (FileModelImpl file : getFiles()) {
            types.addAll(file.getTypes());
        }

        return types;
    }

    @Override
    public Collection<TypeModelImpl> getTypes(String name) {
        List<TypeModelImpl> types = new ArrayList<TypeModelImpl>();
        for (FileModelImpl file : getFiles()) {
            types.addAll(file.getTypes(name));
        }

        return types;
    }

    public void updateFile(FileModelImpl fileModel) {
        files.put(fileModel.getName(), fileModel);
    }

    @Override
    public Collection<FunctionModelImpl> getFunctions() {
        List<FunctionModelImpl> functions = new ArrayList<FunctionModelImpl>();
        for (FileModelImpl file : getFiles()) {
            functions.addAll(file.getFunctions());
        }

        return functions;
    }

    @Override
    public Collection<FunctionModelImpl> getFunctions(String name) {
        List<FunctionModelImpl> functions = new ArrayList<FunctionModelImpl>();
        for (FileModelImpl file : getFiles()) {
            functions.addAll(file.getFunctions(name));
        }

        return functions;
    }

    @Override
    public Collection<ConstModelImpl> getConstants() {
        List<ConstModelImpl> constants = new ArrayList<ConstModelImpl>();
        for (FileModelImpl file : getFiles()) {
            constants.addAll(file.getConstants());
        }

        return constants;
    }

    @Override
    public Collection<ConstModelImpl> getConstants(String name) {
        List<ConstModelImpl> constants = new ArrayList<ConstModelImpl>();
        for (FileModelImpl file : getFiles()) {
            constants.addAll(file.getConstants(name));
        }

        return constants;
    }

    @Override
    public Collection<VarModelImpl> getVars() {
        List<VarModelImpl> vars = new ArrayList<VarModelImpl>();
        for (FileModelImpl file : getFiles()) {
            vars.addAll(file.getVars());
        }

        return vars;
    }

    @Override
    public Collection<VarModelImpl> getVars(String name) {
        List<VarModelImpl> vars = new ArrayList<VarModelImpl>();
        for (FileModelImpl file : getFiles()) {
            vars.addAll(file.getVars(name));
        }

        return vars;
    }

}
