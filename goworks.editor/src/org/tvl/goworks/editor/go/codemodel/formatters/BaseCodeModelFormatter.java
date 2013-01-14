/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.formatters;

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
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeReferenceModel;
import org.tvl.goworks.editor.go.codemodel.TypeSliceModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public class BaseCodeModelFormatter extends AbstractCodeModelFormatter {

    @Override
    protected String formatParameterModel(ParameterModel model) {
        return model.getName();
    }

    @Override
    protected String formatFieldModel(FieldModel model) {
        return model.getName();
    }

    @Override
    protected String formatFunctionModel(FunctionModel model) {
        return model.getName();
    }

    @Override
    protected String formatFileModel(FileModel model) {
        return model.getName();
    }

    @Override
    protected String formatConstModel(ConstModel model) {
        return model.getName();
    }

    @Override
    protected String formatInterfaceModel(InterfaceModel model) {
        return model.getName();
    }

    @Override
    protected String formatStructModel(StructModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeAliasModel(TypeAliasModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeFunctionModel(TypeFunctionModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeIntrinsicModel(TypeIntrinsicModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeMapModel(TypeMapModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeReferenceModel(TypeReferenceModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeArrayModel(TypeArrayModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeChannelModel(TypeChannelModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypePointerModel(TypePointerModel model) {
        return model.getName();
    }

    @Override
    protected String formatTypeSliceModel(TypeSliceModel model) {
        return model.getName();
    }

    @Override
    protected String formatVarModel(VarModel model) {
        return model.getName();
    }

    @Override
    protected String formatImportDeclarationModel(ImportDeclarationModel model) {
        return model.getName();
    }

    @Override
    protected String formatPackageDeclarationModel(PackageDeclarationModel model) {
        return model.getName();
    }

    @Override
    protected String formatPackageModel(PackageModel model) {
        return model.getName();
    }
}
