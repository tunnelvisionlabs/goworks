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
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;

/**
 *
 * @author Sam Harwell
 */
public class ImportDeclarationModelImpl extends AbstractCodeElementModel implements ImportDeclarationModel {
    private final String path;
    private final String alias;
    private final boolean mergeWithLocal;

    public ImportDeclarationModelImpl(@NonNull String path, @NullAllowed String alias, boolean mergeWithLocal, @NonNull FileModelImpl file) {
        super(getAlias(path, alias), file);
        this.path = path;
        this.alias = alias;
        this.mergeWithLocal = mergeWithLocal;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean isMergeWithLocal() {
        return mergeWithLocal;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    private static String getAlias(@NonNull String path, @NullAllowed String alias) {
        Parameters.notNull("path", path);

        if (alias == null || alias.isEmpty()) {
            alias = path.substring(path.lastIndexOf('/') + 1);
            int start = alias.startsWith("\"") ? 1 : 0;
            int end = alias.length() - start - (alias.endsWith("\"") ? 1 : 0);
            alias = alias.substring(start, end);
        }

        return alias;
    }
}
