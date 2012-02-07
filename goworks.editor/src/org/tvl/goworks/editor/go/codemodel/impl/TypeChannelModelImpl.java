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
import org.tvl.goworks.editor.go.codemodel.ChannelKind;
import org.tvl.goworks.editor.go.codemodel.TypeChannelModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class TypeChannelModelImpl extends TypeWrapperModelImpl implements TypeChannelModel {

    private final ChannelKind channelKind;

    public TypeChannelModelImpl(TypeModelImpl elementType, ChannelKind channelKind) {
        super("chan " + elementType.getName(), elementType);
        this.channelKind = channelKind;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.CHANNEL;
    }

    @Override
    public ChannelKind getChannelKind() {
        return channelKind;
    }

    @Override
    public String getSimpleName() {
        return "chan " + getElementType().getSimpleName();
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeChannelModel)) {
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
