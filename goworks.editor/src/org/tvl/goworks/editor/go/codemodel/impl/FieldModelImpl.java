/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tvl.goworks.editor.go.codemodel.FieldModel;
import org.tvl.goworks.editor.go.codemodel.VarKind;

/**
 *
 * @author Sam Harwell
 */
public class FieldModelImpl extends VarModelImpl implements FieldModel {

    private final boolean anonymous;

    public FieldModelImpl(String name, TypeModelImpl varType, boolean anonymous, FileModelImpl file, TerminalNode<? extends Token> seek, ParserRuleContext<?> span) {
        super(name, VarKind.FIELD, varType, file, seek, span);
        this.anonymous = anonymous;
    }

    @Override
    public boolean isAnonymous() {
        return anonymous;
    }

}
