/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.quicksearch;

import java.util.Collection;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FieldModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.codemodel.TypeFunctionModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.formatters.BaseCodeModelFormatter;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class QuickSearchFormatter extends BaseCodeModelFormatter {

    public static final QuickSearchFormatter INSTANCE = new TopLevelQuickSearchFormatter();

    private static final TopLevelElementQuickSearchFormatter TOP_LEVEL_ELEMENT = new TopLevelElementQuickSearchFormatter();
    private static final ParameterFormatter PARAM = new ParameterFormatter();

    private QuickSearchFormatter() {
    }

    protected void formatParameters(StringBuilder builder, Collection<? extends ParameterModel> parameters) {
        builder.append('(');
        boolean first = true;
        for (ParameterModel parameter : parameters) {
            if (!first) {
                builder.append(", ");
            }

            first = false;
            builder.append(PARAM.format(parameter));
        }

        builder.append(')');
    }

    private static class TopLevelQuickSearchFormatter extends QuickSearchFormatter {

        @Override
        public String format(CodeElementModel model) {
            StringBuilder builder = new StringBuilder();
            builder.append(TOP_LEVEL_ELEMENT.format(model));
            if (model instanceof AbstractCodeElementModel) {
                AbstractCodeElementModel abstractModel = (AbstractCodeElementModel)model;
                FileModel fileModel = abstractModel.getFile();
                if (fileModel != null && !fileModel.equals(model)) {
                    builder.append(" in ");
                    builder.append(fileModel.getName());
                }
            }

            return builder.toString();
        }
    }

    private static class TopLevelElementQuickSearchFormatter extends QuickSearchFormatter {

        @Override
        protected String formatFunctionModel(FunctionModel model) {
            StringBuilder builder = new StringBuilder(model.getName());
            formatParameters(builder, model.getParameters());
            return builder.toString();
        }

        @Override
        protected String formatFieldModel(FieldModel model) {
            return format(model.getVarType()) + ' ' + model.getName();
        }

        @Override
        protected String formatVarModel(VarModel model) {
            return format(model.getVarType()) + ' ' + model.getName();
        }

        @Override
        protected String formatParameterModel(ParameterModel model) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected String formatTypeFunctionModel(TypeFunctionModel model) {
            throw new UnsupportedOperationException();
        }
    }

    private static class ParameterFormatter extends QuickSearchFormatter {

        @Override
        protected String formatTypeFunctionModel(TypeFunctionModel model) {
            StringBuilder builder = new StringBuilder("func ");
            formatParameters(builder, model.getParameters());
            if (!model.getReturnValues().isEmpty()) {
                builder.append(' ');
                formatParameters(builder, model.getReturnValues());
            }

            return builder.toString();
        }

        @Override
        protected String formatParameterModel(ParameterModel model) {
            return format(model.getVarType());
        }

        @Override
        protected String formatConstModel(ConstModel model) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected String formatFileModel(FileModel model) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected String formatFunctionModel(FunctionModel model) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected String formatFieldModel(FieldModel model) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected String formatVarModel(VarModel model) {
            throw new UnsupportedOperationException();
        }
    }
}
