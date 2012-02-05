/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.tvl.goworks.editor.go.codemodel.VarKind;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.codemodel.TypeKind;

/**
 *
 * @author Sam Harwell
 */
public class GoAnnotatedParseTree extends AnnotatedParseTree {

    public GoAnnotatedParseTree(@NonNull ParserRuleContext<Token> parseTree) {
        super(parseTree);
    }

    @NonNull
    public CodeElementReference getTarget(ParserRuleContext<Token> parseTree) {
        return getTreeDecorator().getProperty(parseTree, GoAnnotations.PROP_ELEMENT_REFERENCE);
    }

    public NodeType getNodeType(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.NODE_TYPE);
    }

    public VarKind getVarType(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.VAR_TYPE);
    }

    public TypeKind getTypeKind(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.TYPE_KIND);
    }

    public boolean isGlobal(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.GLOBAL);
    }

    public boolean isDeclaration(Token symbol) {
        return getNodeType(symbol).isDeclaration();
    }

    public boolean isResolved(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.RESOLVED)
            || isDeclaration(symbol)
            || isBuiltin(symbol);
    }

    public boolean isBuiltin(Token symbol) {
        return getTokenDecorator().getProperty(symbol, GoAnnotations.BUILTIN);
    }

}
