/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
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
    public TypeModel getCodeElementModel() {
        return typeModel;
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
