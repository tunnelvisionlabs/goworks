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
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.ImageUtilities;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;

/**
 *
 * @author Sam Harwell
 */
public class ConstReferenceCompletionItem extends GoCompletionItem {

    private static ImageIcon ICON;
    private static ImageIcon ICON_PROTECTED;
    private static ImageIcon ICON_LOCAL;

    private final ConstModel constModel;
    private final String constName;
    private final TypeModel typeModel;
    private final boolean localScope;
    private String leftText;

    public ConstReferenceCompletionItem(@NonNull String constName, @NullAllowed TypeModel typeModel, boolean localScope) {
        Parameters.notNull("constName", constName);
        this.constModel = null;
        this.constName = constName;
        this.typeModel = typeModel;
        this.localScope = localScope;
    }

    public ConstReferenceCompletionItem(@NonNull ConstModel constModel, boolean localScope) {
        Parameters.notNull("constModel", constModel);
        this.constModel = constModel;
        this.constName = constModel.getName();
        this.typeModel = constModel.getConstType();
        this.localScope = localScope;
    }

    @Override
    public ConstModel getCodeElementModel() {
        return constModel;
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
    public String getToolTipText() {
        if (localScope) {
            return "(Local constant) " + constModel;
        } else if (constModel != null) {
            return constModel.toString();
        }

        return "";
    }

    @Override
    protected ImageIcon getIcon() {
        ImageIcon icon;
        if (localScope) {
            if (ICON_LOCAL == null) {
                ICON_LOCAL = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/const_protected.png"));
            }
            icon = ICON_LOCAL;
        } else if (Character.isUpperCase(constName.charAt(0)) || SemanticHighlighter.PREDEFINED_CONSTANTS.contains(constName)) {
            if (ICON == null) {
                ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/const_static_public.png"));
            }
            icon = ICON;
        } else {
            if (ICON_PROTECTED == null) {
                ICON_PROTECTED = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/const_static_protected.png"));
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
            builder.append(constName);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (constModel != null) {
            if (constModel.isTyped()) {
                return constModel.getConstType().getName();
            }

            String unevaluated = constModel.getUnevaluatedValue();
            String evaluated = constModel.getEvaluatedValue();

            StringBuilder builder = new StringBuilder(unevaluated.length() + 3 + evaluated.length());

            builder.append(unevaluated);
            if (!unevaluated.isEmpty() && !evaluated.isEmpty()) {
                builder.append(' ');
            }

            if (!evaluated.isEmpty()) {
                builder.append('(').append(evaluated).append(')');
            }

            return builder.toString();
        }

        if (typeModel != null) {
            return typeModel.getName();
        }

        return "";
    }

}
