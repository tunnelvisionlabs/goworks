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
import java.util.List;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeAliasModelImpl extends TypeModelImpl implements TypeAliasModel {
    private final TypeModelImpl type;

    public TypeAliasModelImpl(String name, TypeModelImpl type, FileModelImpl fileModel) {
        super(name, fileModel);
        this.type = type;
    }

    @Override
    public TypeModelImpl getType() {
        return type;
    }

    @Override
    public Collection<VarModelImpl> getFields() {
        return getType().getFields();
    }

    @Override
    public Collection<FunctionModelImpl> getMethods() {
        PackageModelImpl packageModel = getPackage();
        assert packageModel != null;

        List<FunctionModelImpl> methods = new ArrayList<FunctionModelImpl>();
        for (FunctionModelImpl function : packageModel.getFunctions()) {
            ParameterModelImpl receiver = function.getReceiverParameter();
            if (receiver == null) {
                continue;
            }
            
            if (receiver.getVarType().equals(this)) {
                methods.add(function);
            }
        }

        return methods;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        List<AbstractCodeElementModel> members = new ArrayList<AbstractCodeElementModel>();
        members.addAll(getFields());
        members.addAll(getMethods());
        return members;
    }

}
