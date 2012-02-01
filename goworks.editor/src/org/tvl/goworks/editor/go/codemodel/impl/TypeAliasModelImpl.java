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
import java.util.List;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

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
    public TypeKind getKind() {
        return TypeKind.ALIAS;
    }

    @Override
    public String getSimpleName() {
        return getName();
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
