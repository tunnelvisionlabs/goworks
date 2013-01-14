/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.formatters;

import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.ConstModel;
import org.tvl.goworks.editor.go.codemodel.FieldModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.FunctionModel;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.InterfaceModel;
import org.tvl.goworks.editor.go.codemodel.PackageDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.ParameterModel;
import org.tvl.goworks.editor.go.codemodel.StructModel;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeArrayModel;
import org.tvl.goworks.editor.go.codemodel.TypeChannelModel;
import org.tvl.goworks.editor.go.codemodel.TypeFunctionModel;
import org.tvl.goworks.editor.go.codemodel.TypeIntrinsicModel;
import org.tvl.goworks.editor.go.codemodel.TypeMapModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeReferenceModel;
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class AbstractCodeModelFormatter implements CodeModelFormatter {

    @Override
    public String format(CodeElementModel model) {
        if (model instanceof FileModel) {
            return formatFileModel((FileModel)model);
        } else if (model instanceof PackageModel) {
            return formatPackageModel((PackageModel)model);
        } else if (model instanceof ImportDeclarationModel) {
            return formatImportDeclarationModel((ImportDeclarationModel)model);
        } else if (model instanceof PackageDeclarationModel) {
            return formatPackageDeclarationModel((PackageDeclarationModel)model);
        } else if (model instanceof TypeModel) {
            if (model instanceof InterfaceModel) {
                return formatInterfaceModel((InterfaceModel)model);
            } else if (model instanceof StructModel) {
                return formatStructModel((StructModel)model);
            } else if (model instanceof TypeAliasModel) {
                return formatTypeAliasModel((TypeAliasModel)model);
            } else if (model instanceof TypeFunctionModel) {
                return formatTypeFunctionModel((TypeFunctionModel)model);
            } else if (model instanceof TypeIntrinsicModel) {
                return formatTypeIntrinsicModel((TypeIntrinsicModel)model);
            } else if (model instanceof TypeMapModel) {
                return formatTypeMapModel((TypeMapModel)model);
            } else if (model instanceof TypeReferenceModel) {
                return formatTypeReferenceModel((TypeReferenceModel)model);
            } else if (model instanceof TypeArrayModel) {
                return formatTypeArrayModel((TypeArrayModel)model);
            } else if (model instanceof TypeChannelModel) {
                return formatTypeChannelModel((TypeChannelModel)model);
            } else if (model instanceof TypePointerModel) {
                return formatTypePointerModel((TypePointerModel)model);
            } else if (model instanceof TypeSliceModel) {
                return formatTypeSliceModel((TypeSliceModel)model);
            } else {
                throw new UnsupportedOperationException("Unrecognized CodeElementModel implementation");
            }
        } else if (model instanceof FunctionModel) {
            return formatFunctionModel((FunctionModel)model);
        } else if (model instanceof VarModel) {
            if (model instanceof FieldModel) {
                return formatFieldModel((FieldModel)model);
            } else if (model instanceof ParameterModel) {
                return formatParameterModel((ParameterModel)model);
            } else {
                return formatVarModel((VarModel)model);
            }
        } else if (model instanceof ConstModel) {
            return formatConstModel((ConstModel)model);
        } else {
            throw new UnsupportedOperationException("Unrecognized CodeElementModel implementation");
        }
    }

    protected abstract String formatConstModel(ConstModel model);

    protected abstract String formatFieldModel(FieldModel model);

    protected abstract String formatFileModel(FileModel model);

    protected abstract String formatFunctionModel(FunctionModel model);

    protected abstract String formatImportDeclarationModel(ImportDeclarationModel model);

    protected abstract String formatInterfaceModel(InterfaceModel model);

    protected abstract String formatPackageDeclarationModel(PackageDeclarationModel model);

    protected abstract String formatPackageModel(PackageModel model);

    protected abstract String formatParameterModel(ParameterModel model);

    protected abstract String formatStructModel(StructModel model);

    protected abstract String formatTypeAliasModel(TypeAliasModel model);

    protected abstract String formatTypeArrayModel(TypeArrayModel model);

    protected abstract String formatTypeChannelModel(TypeChannelModel model);

    protected abstract String formatTypeFunctionModel(TypeFunctionModel model);

    protected abstract String formatTypeIntrinsicModel(TypeIntrinsicModel model);

    protected abstract String formatTypeMapModel(TypeMapModel model);

    protected abstract String formatTypePointerModel(TypePointerModel model);

    protected abstract String formatTypeReferenceModel(TypeReferenceModel model);

    protected abstract String formatTypeSliceModel(TypeSliceModel model);

    protected abstract String formatVarModel(VarModel model);
}
