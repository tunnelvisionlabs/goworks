/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.netbeans.api.annotations.common.NonNull;
import org.tvl.goworks.editor.go.codemodel.TypeKind;
import org.tvl.goworks.editor.go.codemodel.VarKind;

/**
 *
 * @author Sam Harwell
 */
public class GoAnnotatedParseTree extends AnnotatedParseTree {

    public GoAnnotatedParseTree(@NonNull ParseTree<Token> parseTree) {
        super(parseTree);
    }

    @NonNull
    public CodeElementReference getTarget(ParseTree<? extends Token> parseTree) {
        return getTreeDecorator().getProperty(parseTree, GoAnnotations.PROP_ELEMENT_REFERENCE);
    }

    public NodeType getNodeType(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.NODE_TYPE);
    }

    public VarKind getVarType(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.VAR_TYPE);
    }

    public TypeKind getTypeKind(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.TYPE_KIND);
    }

    public boolean isGlobal(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.GLOBAL);
    }

    public boolean isDeclaration(TerminalNode<? extends Token> node) {
        return getNodeType(node).isDeclaration();
    }

    public boolean isResolved(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.RESOLVED)
            || isDeclaration(node)
            || isBuiltin(node);
    }

    public boolean isBuiltin(TerminalNode<? extends Token> node) {
        return getTreeDecorator().getProperty(node, GoAnnotations.BUILTIN);
    }

}
