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
public interface InterfaceModel extends TypeModel {

    Collection<? extends FunctionModel> getInterfaceMethods();

    Collection<? extends FunctionModel> getInterfaceMethods(String name);

    Collection<? extends TypeModel> getImplementedInterfaces();

}
