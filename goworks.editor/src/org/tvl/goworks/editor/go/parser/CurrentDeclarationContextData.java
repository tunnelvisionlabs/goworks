/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.RuleDependency;
import org.tvl.goworks.editor.go.parser.generated.AbstractGoParser.TopLevelDeclContext;

/**
 *
 * @author Sam Harwell
 */
public final class CurrentDeclarationContextData {
    private final DocumentSnapshot snapshot;
    private final TopLevelDeclContext context;

    public CurrentDeclarationContextData(DocumentSnapshot snapshot, TopLevelDeclContext context) {
        this.snapshot = snapshot;
        this.context = context;
    }

    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public TopLevelDeclContext getContext() {
        return context;
    }

    @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0)
    public String getMemberName() {
        if (context == null) {
            return null;
        }

        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
