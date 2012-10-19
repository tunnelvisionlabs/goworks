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
public interface TypeModel extends CodeElementModel {

    @NonNull
    Collection<? extends TypeModel> resolve();

    @NonNull
    String getSimpleName();

    @NonNull
    TypeKind getKind();

    @NonNull
    Collection<? extends FieldModel> getFields();

    @NonNull
    Collection<? extends FieldModel> getFields(String name);

    @NonNull
    Collection<? extends FunctionModel> getMethods();

    @NonNull
    Collection<? extends FunctionModel> getMethods(String name);

}
