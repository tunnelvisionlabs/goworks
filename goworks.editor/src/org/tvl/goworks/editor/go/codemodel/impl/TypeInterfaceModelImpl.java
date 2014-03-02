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
import java.util.Collections;
import org.antlr.v4.runtime.ParserRuleContext;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.tvl.goworks.editor.go.codemodel.InterfaceModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeInterfaceModelImpl extends TypeModelImpl implements InterfaceModel {
    private final FreezableArrayList<FunctionModelImpl> interfaceMethods = new FreezableArrayList<>();
    private final FreezableArrayList<TypeModelImpl> implementedInterfaces = new FreezableArrayList<>();

    public TypeInterfaceModelImpl(@NonNull String name, @NonNull FileModelImpl fileModel, @NullAllowed ParserRuleContext<?> span) {
        super(name, fileModel, null, span);
    }

    @Override
    public Collection<? extends TypeModelImpl> resolve() {
        return Collections.singletonList(this);
    }

    @Override
    public boolean isResolved() {
        return true;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.INTERFACE;
    }

    @Override
    public String getSimpleName() {
        if (interfaceMethods.isEmpty() && implementedInterfaces.isEmpty()) {
            return "interface{}";
        }

        return "interface{...}";
    }

    @Override
    public Collection<FunctionModelImpl> getInterfaceMethods() {
        return interfaceMethods;
    }

    @Override
    public Collection<FunctionModelImpl> getInterfaceMethods(String name) {
        return CodeModelCacheImpl.findElementsByName(getInterfaceMethods(), name);
    }

    @Override
    public Collection<TypeModelImpl> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return getInterfaceMethods();
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers(String name) {
        return getInterfaceMethods(name);
    }

    @Override
    protected void freezeImpl() {
        interfaceMethods.freeze();
        implementedInterfaces.freeze();
        super.freezeImpl();
    }

}
