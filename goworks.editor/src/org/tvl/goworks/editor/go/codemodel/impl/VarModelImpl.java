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
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.tvl.goworks.editor.go.codemodel.CodeElementPositionRegion;
import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.tvl.goworks.editor.go.codemodel.VarModel;

/**
 *
 * @author Sam Harwell
 */
public class VarModelImpl extends AbstractCodeElementModel implements VarModel {
    private final VarKind kind;
    private final TypeModelImpl varType;

    private final OffsetRegion seek;
    private final OffsetRegion span;

    public VarModelImpl(String name, VarKind kind, TypeModelImpl varType, FileModelImpl file, TerminalNode<? extends Token> seek, ParserRuleContext<?> span) {
        super(name, file);
        this.kind = kind;
        this.varType = varType;
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
    }

    @Override
    public VarKind getVarKind() {
        return kind;
    }

    @Override
    public TypeModelImpl getVarType() {
        return varType;
    }

    @Override
    public CodeElementPositionRegion getSeek() {
        if (this.seek == null) {
            return super.getSeek();
        }

        return new CodeElementPositionRegionImpl(this, seek);
    }

    @Override
    public CodeElementPositionRegion getSpan() {
        if (this.span == null) {
            return super.getSpan();
        }

        return new CodeElementPositionRegionImpl(this, span);
    }

    @Override
    public Collection<? extends AbstractCodeElementModel> getMembers() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return String.format("%s %s", getName(), getVarType());
    }

}
