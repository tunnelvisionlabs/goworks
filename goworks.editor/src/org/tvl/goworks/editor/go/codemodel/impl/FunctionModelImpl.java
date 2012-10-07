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
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tvl.goworks.editor.go.codemodel.CodeElementPositionRegion;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ParameterModel;

/**
 *
 * @author Sam Harwell
 */
public class FunctionModelImpl extends AbstractCodeElementModel implements FunctionModel {

    private final FreezableArrayList<ParameterModelImpl> parameters = new FreezableArrayList<ParameterModelImpl>();
    private final FreezableArrayList<ParameterModelImpl> returnValues = new FreezableArrayList<ParameterModelImpl>();
    private ParameterModelImpl receiverParameter;

    private final OffsetRegion seek;
    private final OffsetRegion span;

    public FunctionModelImpl(String name, FileModelImpl file, TerminalNode<? extends Token> seek, ParserRuleContext<?> span) {
        super(name, file);
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        if (this.seek == null) {
            return super.getSeek();
        }

        return new CodeElementPositionRegionImpl(this, seek);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (this.span == null) {
            return super.getSpan();
        }

        return new CodeElementPositionRegionImpl(this, span);
    }

    @Override
    public boolean isMethod() {
        return receiverParameter != null;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ParameterModelImpl> getParameters() {
        return parameters;
    }

    @Override
    public Collection<ParameterModelImpl> getReturnValues() {
        return returnValues;
    }

    @Override
    public ParameterModelImpl getReceiverParameter() {
        return receiverParameter;
    }

    public void setReceiverParameter(ParameterModelImpl value) {
        ensureModifiable();
        receiverParameter = value;
    }

    @Override
    protected void freezeImpl() {
        parameters.freeze();
        returnValues.freeze();
        super.freezeImpl();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("func");

        ParameterModelImpl receiver = getReceiverParameter();
        if (receiver != null) {
            builder.append(" (").append(receiver).append(")");
        }

        builder.append(" ").append(getName());
        builder.append("(");
        builder.append(Utils.join(getParameters().iterator(), ", "));
        builder.append(")");

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        Collection<? extends ParameterModel> returnValues = getReturnValues();
        if (returnValues.size() == 1 && returnValues.iterator().next().getName().equals("_")) {
            builder.append(" ").append(returnValues.iterator().next().getVarType());
        } else if (!returnValues.isEmpty()) {
            builder.append(" (");
            boolean first = true;
            for (ParameterModel parameter : returnValues) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }

                if (!"_".equals(parameter.getName())) {
                    builder.append(parameter.getName()).append(' ');
                }

                builder.append(parameter.getVarType());
            }
            builder.append(')');
        }

        return builder.toString();
    }

}
