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
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface InterfaceModel extends TypeModel {

    @NonNull
    Collection<? extends FunctionModel> getInterfaceMethods();

    @NonNull
    Collection<? extends FunctionModel> getInterfaceMethods(String name);

    // extends TypeModel since it may contain aliases/references which resolve to interfaces
    @NonNull
    Collection<? extends TypeModel> getImplementedInterfaces();

}
