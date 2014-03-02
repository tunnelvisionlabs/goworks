/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.impl.AbstractCodeElementModel;

/**
 *
 * @author Sam Harwell
 */
public class BundledReturnTypeModel extends AbstractCodeElementModel {

    private final List<? extends CodeElementModel> returnValues;

    public BundledReturnTypeModel(Collection<? extends AbstractCodeElementModel> returnValues) {
        super("tuple", returnValues.iterator().next().getFile());
        this.returnValues = new ArrayList<>(returnValues);
    }

    @NonNull
    public List<? extends CodeElementModel> getReturnValues() {
        return returnValues;
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

}
