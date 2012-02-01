/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel;

import java.util.Collection;

/**
 *
 * @author Sam Harwell
 */
public interface FileModel extends CodeElementModel {

    // allow multiples to improve ability to recover from syntax errors
    Collection<? extends PackageDeclarationModel> getPackageDeclarations();

    Collection<? extends ImportDeclarationModel> getImportDeclarations();

    Collection<? extends TypeModel> getTypes();

    Collection<? extends TypeModel> getTypes(String name);

    Collection<? extends ConstModel> getConstants();

    Collection<? extends ConstModel> getConstants(String name);

    Collection<? extends VarModel> getVars();

    Collection<? extends VarModel> getVars(String name);

    Collection<? extends FunctionModel> getFunctions();

    Collection<? extends FunctionModel> getFunctions(String name);

}
