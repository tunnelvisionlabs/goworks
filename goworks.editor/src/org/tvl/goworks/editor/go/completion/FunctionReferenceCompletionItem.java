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
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.highlighter.SemanticHighlighter;

/**
 *
 * @author Sam Harwell
 */
public class FunctionReferenceCompletionItem extends GoCompletionItem {

    private static ImageIcon FUNCTION_ICON;
    private static ImageIcon FUNCTION_ICON_PROTECTED;
    private static ImageIcon METHOD_ICON;
    private static ImageIcon METHOD_ICON_PROTECTED;

    private final FunctionModel functionModel;
    private final String functionName;
    private String leftText;
    private String rightText;

    public FunctionReferenceCompletionItem(@NonNull String typeName) {
        Parameters.notNull("typeName", typeName);
        this.functionModel = null;
        this.functionName = typeName;
    }

    public FunctionReferenceCompletionItem(@NonNull FunctionModel functionModel) {
        Parameters.notNull("functionModel", functionModel);
        this.functionModel = functionModel;
        this.functionName = functionModel.getName();
    }

    @Override
    public int getSortPriority() {
        return MEMBER_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return functionName;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return functionName;
    }

    @Override
    protected ImageIcon getIcon() {
        ImageIcon icon;
        if (functionModel == null || functionModel.getReceiverParameter() == null) {
            if (Character.isUpperCase(functionName.charAt(0)) || SemanticHighlighter.PREDEFINED_FUNCTIONS.contains(functionName)) {
                if (FUNCTION_ICON == null) {
                    FUNCTION_ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/methods_static.png"));
                }
                icon = FUNCTION_ICON;
            } else {
                if (FUNCTION_ICON_PROTECTED == null) {
                    FUNCTION_ICON_PROTECTED = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/methods_static_protected.png"));
                }
                icon = FUNCTION_ICON_PROTECTED;
            }
        } else {
            if (Character.isUpperCase(functionName.charAt(0))) {
                if (METHOD_ICON == null) {
                    METHOD_ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/methods.png"));
                }
                icon = METHOD_ICON;
            } else {
                if (METHOD_ICON_PROTECTED == null) {
                    METHOD_ICON_PROTECTED = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/methods_protected.png"));
                }
                icon = METHOD_ICON_PROTECTED;
            }
        }

        return icon;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(METHOD_COLOR);
            builder.append(functionName);
            builder.append(COLOR_END);
            if (functionModel != null) {
                builder.append("(");
                boolean first = true;
                for (ParameterModel parameter : functionModel.getParameters()) {
                    if (!first) {
                        builder.append(", ");
                    }

                    appendParameter(builder, parameter);
                    first = false;
                }
                builder.append(")");
            }
            leftText = builder.toString();
        }
        return leftText;
    }

    @Override
    protected String getRightHtmlText() {
        if (rightText == null) {
            if (functionModel != null && !functionModel.getReturnValues().isEmpty()) {
                if (functionModel.getReturnValues().size() == 1) {
                    ParameterModel model = functionModel.getReturnValues().iterator().next();
                    if ("_".equals(model.getName())) {
                        return model.getVarType().getSimpleName();
                    }
                } else {
                    StringBuilder builder = new StringBuilder();
                    builder.append("(");
                    boolean first = true;
                    for (ParameterModel parameter : functionModel.getReturnValues()) {
                        if (!first) {
                            builder.append(", ");
                        }

                        appendParameter(builder, parameter);
                        first = false;
                    }
                    builder.append(")");
                    rightText = builder.toString();
                }
            } else {
                rightText = "";
            }
        }

        return rightText;
    }

    private void appendParameter(StringBuilder builder, ParameterModel parameter) {
        if (!"_".equals(parameter.getName())) {
            builder.append(PARAMETER_NAME_COLOR);
            builder.append(parameter.getName());
            builder.append(COLOR_END);
            builder.append(" ");
        }

        builder.append(PARAMETER_TYPE_COLOR);
        builder.append(parameter.getVarType().getSimpleName());
        builder.append(COLOR_END);
    }

}
