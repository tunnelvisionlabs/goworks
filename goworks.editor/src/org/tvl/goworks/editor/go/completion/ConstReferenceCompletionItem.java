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
package org.tvl.goworks.editor.go.completion;

import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.ConstModel;

/**
 *
 * @author Sam Harwell
 */
public class ConstReferenceCompletionItem extends GoCompletionItem {

    private final ConstModel constModel;
    private final String constName;
    private String leftText;

    public ConstReferenceCompletionItem(@NonNull String constName) {
        Parameters.notNull("constName", constName);
        this.constModel = null;
        this.constName = constName;
    }

    public ConstReferenceCompletionItem(@NonNull ConstModel constModel) {
        Parameters.notNull("constModel", constModel);
        this.constModel = constModel;
        this.constName = constModel.getName();
    }

    @Override
    public int getSortPriority() {
        return MEMBER_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return constName;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return constName;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(FIELD_COLOR);
            builder.append(constName);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (constModel != null) {
            String name = constModel.getClass().getSimpleName();
            name = name.substring(name.lastIndexOf('.') + 1);
            return name;
        }

        return "";
    }

}
