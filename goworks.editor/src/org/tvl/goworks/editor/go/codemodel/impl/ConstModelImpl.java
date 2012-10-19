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
import org.tvl.goworks.editor.go.codemodel.ConstModel;

/**
 *
 * @author Sam Harwell
 */
public class ConstModelImpl extends AbstractCodeElementModel implements ConstModel {

    private final OffsetRegion seek;
    private final OffsetRegion span;

    private final String _unevaluatedValue;
    private final String _evaluatedValue;
    private final TypeModelImpl _type;

    public ConstModelImpl(String name, FileModelImpl file, String unevaluatedValue, String evaluatedValue, TypeModelImpl type, TerminalNode<? extends Token> seek, ParserRuleContext<?> span) {
        super(name, file);
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
        this._unevaluatedValue = unevaluatedValue;
        this._evaluatedValue = evaluatedValue;
        this._type = type;
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
    public TypeModelImpl getConstType() {
        return _type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTyped() {
        return _type != null;
    }

    @Override
    public String getUnevaluatedValue() {
        return _unevaluatedValue != null ? _unevaluatedValue : "";
    }

    @Override
    public String getEvaluatedValue() {
        return _evaluatedValue != null ? _evaluatedValue : "";
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("const ");
        builder.append(getName());
        if (isTyped()) {
            builder.append(' ').append(getConstType().getName());
        }

        String unevaluated = getUnevaluatedValue();
        String evaluated = getEvaluatedValue();
        if (!unevaluated.isEmpty() || !evaluated.isEmpty()) {
            builder.append(" =");
        }

        if (!unevaluated.isEmpty()) {
            builder.append(' ').append(unevaluated);
        }

        if (!evaluated.isEmpty()) {
            builder.append(" (").append(evaluated).append(')');
        }

        return builder.toString();
    }

}
