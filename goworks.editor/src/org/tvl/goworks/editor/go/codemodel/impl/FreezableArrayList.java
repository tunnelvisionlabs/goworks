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

/**
 *
 * @author sam
 */
public class FreezableArrayList<E> extends ArrayList<E> {

    private boolean frozen;

    public boolean isFrozen() {
        return frozen;
    }

    public void freeze() {
        frozen = true;
    }

    @Override
    public E set(int index, E element) {
        ensureModifiable();
        return super.set(index, element);
    }

    @Override
    public boolean add(E e) {
        ensureModifiable();
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        ensureModifiable();
        super.add(index, element);
    }

    @Override
    public boolean remove(Object o) {
        ensureModifiable();
        return super.remove(o);
    }

    @Override
    public void clear() {
        ensureModifiable();
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ensureModifiable();
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        ensureModifiable();
        return super.addAll(index, c);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        ensureModifiable();
        super.removeRange(fromIndex, toIndex);
    }

    protected void ensureModifiable() {
        if (isFrozen()) {
            throw new IllegalStateException("The collection is frozen.");
        }
    }
}
