/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.Collection;
import java.util.Collections;
import org.tvl.goworks.editor.go.codemodel.ConstModel;

/**
 *
 * @author Sam Harwell
 */
public class ConstModelImpl extends AbstractCodeElementModel implements ConstModel {

    public ConstModelImpl(String name, FileModelImpl file) {
        super(name, file);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

}
