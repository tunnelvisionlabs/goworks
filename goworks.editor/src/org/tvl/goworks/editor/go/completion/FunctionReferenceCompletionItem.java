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
