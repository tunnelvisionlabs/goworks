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

import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.ImageUtilities;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.TypeModel;

/**
 *
 * @author Sam Harwell
 */
public class TypeReferenceCompletionItem extends GoCompletionItem {

    private final TypeModel typeModel;
    private final String typeName;
    private String leftText;

    public TypeReferenceCompletionItem(@NonNull String typeName) {
        Parameters.notNull("typeName", typeName);
        this.typeModel = null;
        this.typeName = typeName;
    }

    public TypeReferenceCompletionItem(@NonNull TypeModel typeModel) {
        Parameters.notNull("typeModel", typeModel);
        this.typeModel = typeModel;
        this.typeName = typeModel.getName();
    }

    @Override
    protected ImageIcon getIcon() {
        String resourceLocation = "org/tvl/goworks/editor/go/resources/";
        String imageName;

        TypeKind kind = typeModel != null ? typeModel.getKind() : TypeKind.UNKNOWN;
        if (typeModel instanceof TypeAliasModel) {
            kind = ((TypeAliasModel)typeModel).getType().getKind();
        }

        switch (kind) {
        case ALIAS:
            imageName = "typedef_16.png";
            break;

        case STRUCT:
            imageName = "struct_16.png";
            break;

        case INTERFACE:
            imageName = "interface.png";
            break;

        case ARRAY:
        case CHANNEL:
        case FUNCTION:
        case INTRINSIC:
        case MAP:
        case POINTER:
        case SLICE:
        case UNKNOWN:
        default:
            imageName = "typedef_16.png";
            break;
        }

        return new ImageIcon(ImageUtilities.loadImage(resourceLocation + imageName));
    }

    @Override
    public int getSortPriority() {
        return TYPE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return typeName;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return typeName;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(TYPE_COLOR);
            builder.append(typeName);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
//        if (typeModel != null) {
//            String name = typeModel.getClass().getSimpleName();
//            name = name.substring(name.lastIndexOf('.') + 1);
//            return name;
//        }

        return "";
    }

}
