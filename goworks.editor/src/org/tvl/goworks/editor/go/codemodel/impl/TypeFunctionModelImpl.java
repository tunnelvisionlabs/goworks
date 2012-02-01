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
import java.util.List;
import org.tvl.goworks.editor.go.codemodel.TypeFunctionModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeFunctionModelImpl extends TypeModelImpl implements TypeFunctionModel {

    private final FreezableArrayList<ParameterModelImpl> parameters = new FreezableArrayList<ParameterModelImpl>();
    private final FreezableArrayList<ParameterModelImpl> returnValues = new FreezableArrayList<ParameterModelImpl>();

    private String simpleName;

    public TypeFunctionModelImpl(String name, FileModelImpl fileModel) {
        super(name, fileModel);
    }

    @Override
    public boolean isMethod() {
        return false;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.FUNCTION;
    }

    @Override
    public String getSimpleName() {
        if (simpleName == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("func(");
            boolean first = true;
            for (ParameterModelImpl parameter : getParameters()) {
                if (!first) {
                    builder.append(", ");
                }

                appendParameter(builder, parameter);
                first = false;
            }
            builder.append(")");

            if (!getReturnValues().isEmpty()) {
                if (getReturnValues().size() == 1) {
                    ParameterModelImpl model = getReturnValues().get(0);
                    if ("_".equals(model.getName())) {
                        builder.append(" ");
                        builder.append(model.getVarType().getSimpleName());
                    }
                } else {
                    builder.append(" (");
                    first = true;
                    for (ParameterModelImpl parameter : getReturnValues()) {
                        if (!first) {
                            builder.append(", ");
                        }

                        appendParameter(builder, parameter);
                        first = false;
                    }
                    builder.append(")");
                }
            }

            simpleName = builder.toString();
        }

        return simpleName;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public List<ParameterModelImpl> getParameters() {
        return parameters;
    }

    @Override
    public List<ParameterModelImpl> getReturnValues() {
        return returnValues;
    }

    @Override
    public ParameterModelImpl getReceiverParameter() {
        return null;
    }

    @Override
    protected void freezeImpl() {
        parameters.freeze();
        returnValues.freeze();
        super.freezeImpl();
    }

    private void appendParameter(StringBuilder builder, ParameterModelImpl parameter) {
        if (!"_".equals(parameter.getName())) {
            builder.append(parameter.getName());
            builder.append(" ");
        }

        builder.append(parameter.getVarType().getSimpleName());
    }

}
