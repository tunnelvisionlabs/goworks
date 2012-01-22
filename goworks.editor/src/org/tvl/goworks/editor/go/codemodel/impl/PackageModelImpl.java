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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.api.project.Project;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;

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
    public PackageModel getPackage() {
        return this;
    }

    @Override
    public Collection<FileModelImpl> getFiles() {
        return files.values();
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
