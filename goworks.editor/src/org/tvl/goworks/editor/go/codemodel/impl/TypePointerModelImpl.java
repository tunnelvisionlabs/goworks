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
import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;

/**
 *
 * @author Sam Harwell
 */
public class TypePointerModelImpl extends TypeWrapperModelImpl implements TypePointerModel {

    public TypePointerModelImpl(TypeModelImpl elementType) {
        super("*" + elementType.getName(), elementType, elementType.getFile());
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
    public Collection<FieldModelImpl> getFields() {
        return getElementType().getFields();
    }

    @Override
    public Collection<FieldModelImpl> getFields(String name) {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof TypePointerModel)) {
            return false;
        }

        return super.equals(obj);
    }

}
