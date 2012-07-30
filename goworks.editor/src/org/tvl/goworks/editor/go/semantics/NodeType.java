/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

/**
 *
 * @author Sam Harwell
 */
public enum NodeType {

    PACKAGE_DECL(true),
    PACKAGE_REF(false),
    CONST_DECL(true),
    CONST_REF(false),
    VAR_DECL(true),
    VAR_REF(false),
    FUNC_DECL(true),
    FUNC_REF(false),
    METHOD_DECL(true),
    METHOD_REF(false),
    TYPE_DECL(true),
    TYPE_REF(false),
    LABEL_DECL(true),
    LABEL_REF(false),

    TYPE_LITERAL(false),

    UNKNOWN(false),
    UNDEFINED(false),

    ;

    private final boolean declaration;

    private NodeType(boolean declaration) {
        this.declaration = declaration;
    }

    public boolean isDeclaration() {
        return declaration;
    }

}
