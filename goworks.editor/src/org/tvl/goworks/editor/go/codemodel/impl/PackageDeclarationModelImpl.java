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
import org.tvl.goworks.editor.go.codemodel.PackageDeclarationModel;
import org.tvl.goworks.project.GoProject;

/**
 *
 * @author Sam Harwell
 */
public class PackageDeclarationModelImpl extends AbstractCodeElementModel implements PackageDeclarationModel {

    private final OffsetRegion seek;
    private final OffsetRegion span;

    public PackageDeclarationModelImpl(String name, GoProject project, TerminalNode<? extends Token> seek, ParserRuleContext<?> span) {
        super(name, project, name);
        this.seek = getOffsetRegion(seek);
        this.span = getOffsetRegion(span);
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

}
