/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
public interface ConstModel extends CodeElementModel {

    /**
     * Get the type of this constant. If the constant is not typed, this returns
     * {@code null}.
     *
     * @return The type of this constant. If the constant is not typed, this
     * returns {@code null}.
     */
    @CheckForNull
    TypeModel getConstType();

    /**
     *
     * @return {@code true} if this constant is typed, otherwise {@code false}.
     */
    boolean isTyped();

    @NonNull
    String getUnevaluatedValue();

    @NonNull
    String getEvaluatedValue();

}
