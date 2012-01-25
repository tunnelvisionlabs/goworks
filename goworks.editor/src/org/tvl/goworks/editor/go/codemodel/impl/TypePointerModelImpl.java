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
import java.util.Collections;
import java.util.List;
import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;

/**
 *
 * @author Sam Harwell
 */
public class TypePointerModelImpl extends TypeWrapperModelImpl implements TypePointerModel {

    public TypePointerModelImpl(TypeModelImpl elementType, FileModelImpl fileModel) {
        super("*" + elementType.getName(), elementType, fileModel);
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.POINTER;
    }

    @Override
    public String getSimpleName() {
        return "*" + getElementType().getSimpleName();
    }

    @Override
    public PackageModelImpl getPackage() {
        return getElementType().getPackage();
    }

    @Override
    public Collection<VarModelImpl> getFields() {
        return getElementType().getFields();
    }

    @Override
    public Collection<VarModelImpl> getFields(String name) {
        return getElementType().getFields(name);
    }

    @Override
    public Collection<FunctionModelImpl> getMethods() {
        List<FunctionModelImpl> functions = new ArrayList<FunctionModelImpl>();
        Collection<? extends FunctionModelImpl> packageFunctions = getPackage().getFunctions();
        for (FunctionModelImpl function : packageFunctions) {
            ParameterModel receiver = function.getReceiverParameter();
            if (receiver == null) {
                continue;
            }

            TypeModel receiverType = receiver.getVarType();
            if (this.equals(receiverType) || this.getElementType().equals(receiverType)) {
                functions.add(function);
            }
        }

        return Collections.unmodifiableList(functions);
    }

    @Override
    public Collection<FunctionModelImpl> getMethods(String name) {
        List<FunctionModelImpl> functions = new ArrayList<FunctionModelImpl>();
        Collection<? extends FunctionModelImpl> packageFunctions = getPackage().getFunctions(name);
        for (FunctionModelImpl function : packageFunctions) {
            ParameterModel receiver = function.getReceiverParameter();
            if (receiver == null) {
                continue;
            }

            TypeModel receiverType = receiver.getVarType();
            if (this.equals(receiverType) || this.getElementType().equals(receiverType)) {
                functions.add(function);
            }
        }

        return Collections.unmodifiableList(functions);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        members.addAll(getFields());
        members.addAll(getMethods());
        return Collections.unmodifiableList(members);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(String name) {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        members.addAll(getFields(name));
        members.addAll(getMethods(name));
        return Collections.unmodifiableList(members);
    }

}
