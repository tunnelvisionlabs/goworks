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
import org.antlr.netbeans.editor.navigation.Description;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.ImageUtilities;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public class VarReferenceCompletionItem extends GoCompletionItem {

    private static ImageIcon ICON;
    private static ImageIcon ICON_PROTECTED;
    private static ImageIcon ICON_LOCAL;

    private final VarModel varModel;
    private final String varName;
    private final boolean localScope;
    private String leftText;

    public VarReferenceCompletionItem(@NonNull String varName, boolean localScope) {
        Parameters.notNull("varName", varName);
        this.varModel = null;
        this.varName = varName;
        this.localScope = localScope;
    }

    public VarReferenceCompletionItem(@NonNull VarModel varModel, boolean localScope) {
        Parameters.notNull("varModel", varModel);
        this.varModel = varModel;
        this.varName = varModel.getName();
        this.localScope = localScope;
    }

    @Override
    public VarModel getCodeElementModel() {
        return varModel;
    }

    public String getVarName() {
        return varName;
    }

    @Override
    public int getSortPriority() {
        return MEMBER_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return varName;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return varName;
    }

    @Override
    public String getToolTipText() {
        if (varModel != null) {
            switch (varModel.getVarKind()) {
            case PARAMETER:
                return "(Parameter) " + varModel;

            case LOCAL:
                return "(Local variable) " + varModel;

            case RECEIVER:
                return "(Receiver) " + varModel;

            case RETURN:
                return "(Return variable) " + varModel;

            default:
                return varModel.toString();
            }
        }

        return varName;
    }

    @Override
    protected ImageIcon getIcon() {
        ImageIcon icon;
        if (localScope) {
            if (ICON_LOCAL == null) {
                ICON_LOCAL = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/fields.png"));
            }
            icon = ICON_LOCAL;
        } else if (Character.isUpperCase(varName.charAt(0))) {
            if (ICON == null) {
                ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/field_static_16.png"));
            }
            icon = ICON;
        } else {
            if (ICON_PROTECTED == null) {
                ICON_PROTECTED = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/field_static_protected_16.png"));
            }
            icon = ICON_PROTECTED;
        }

        return icon;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(FIELD_COLOR);
            builder.append(varName);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (varModel != null) {
            String name = Description.htmlEscape(varModel.getVarType().getSimpleName());
            return "    " + name;
            //String name = varModel.getClass().getSimpleName();
            //name = name.substring(name.lastIndexOf('.') + 1);
            //return name;
        }

        return "";
    }

}
