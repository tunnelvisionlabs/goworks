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
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeAliasModelImpl extends TypeModelImpl implements TypeAliasModel {

    private final TypeModelImpl type;

    public TypeAliasModelImpl(String name, TypeModelImpl type, FileModelImpl fileModel, TerminalNode seek, ParserRuleContext span) {
        super(name, fileModel, seek, span);
        this.type = type;
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        if (isResolved()) {
            return Collections.singletonList(this);
        }

        List<TypeModelImpl> resolved = new ArrayList<>(getType().resolve());
        for (int i = 0; i < resolved.size(); i++) {
            resolved.set(i, new TypeAliasModelImpl(getName(), resolved.get(i), getFile(), null, null));
        }

        return resolved;
    }

    @Override
    public boolean isResolved() {
        return type.isResolved();
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
    public Collection<FieldModelImpl> getFields() {
        return getType().getFields();
    }

    @Override
    public Collection<FunctionModelImpl> getMethods() {
        TypeModelImpl target = getType();
        if (target instanceof TypeInterfaceModelImpl) {
            return ((TypeInterfaceModelImpl)target).getInterfaceMethods();
        }

        PackageModelImpl packageModel = getPackage();
        assert packageModel != null;

        List<FunctionModelImpl> methods = new ArrayList<>();
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

}
