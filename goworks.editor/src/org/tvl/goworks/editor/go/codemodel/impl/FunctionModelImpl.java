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
import org.tvl.goworks.editor.go.codemodel.FunctionModel;

/**
 *
 * @author Sam Harwell
 */
public class FunctionModelImpl extends AbstractCodeElementModel implements FunctionModel {

    private final FreezableArrayList<ParameterModelImpl> parameters = new FreezableArrayList<ParameterModelImpl>();
    private final FreezableArrayList<ParameterModelImpl> returnValues = new FreezableArrayList<ParameterModelImpl>();
    private ParameterModelImpl receiverParameter;

    public FunctionModelImpl(String name, FileModelImpl file) {
        super(name, file);
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

}
