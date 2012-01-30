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

import java.util.Collection;
import java.util.Collections;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeMapModelImpl extends TypeModelImpl implements TypeMapModel {
    private final TypeModelImpl keyType;
    private final TypeModelImpl valueType;

    public TypeMapModelImpl(String name, TypeModelImpl keyType, TypeModelImpl valueType, FileModelImpl fileModel) {
        super(name, fileModel);
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.MAP;
    }

    @Override
    public String getSimpleName() {
        return String.format("map[%s]%s", getKeyType().getSimpleName(), getValueType().getSimpleName());
    }

    @Override
    public TypeModel getKeyType() {
        return keyType;
    }

    @Override
    public TypeModel getValueType() {
        return valueType;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    protected void freezeImpl() {
        keyType.freeze();
        valueType.freeze();
        super.freezeImpl();
    }

}
