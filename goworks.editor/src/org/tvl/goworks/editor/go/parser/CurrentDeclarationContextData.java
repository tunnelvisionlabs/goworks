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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.tvl.goworks.editor.go.parser.GoParserBase.topLevelDeclContext;

/**
 *
 * @author Sam Harwell
 */
public final class CurrentDeclarationContextData {
    private final DocumentSnapshot snapshot;
    private final topLevelDeclContext context;

    public CurrentDeclarationContextData(DocumentSnapshot snapshot, topLevelDeclContext context) {
        this.snapshot = snapshot;
        this.context = context;
    }

    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public topLevelDeclContext getContext() {
        return context;
    }

    public String getMemberName() {
        if (context == null) {
            return null;
        }

        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
