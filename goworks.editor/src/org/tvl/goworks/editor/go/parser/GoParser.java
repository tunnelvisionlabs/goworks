/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;

/**
 *
 * @author Sam Harwell
 */
public class GoParser extends AbstractGoParser {
	private final Set<String> packageNames = new HashSet<String>();
    private DocumentSnapshot snapshot;

    public GoParser(TokenStream<? extends Token> input) {
        super(input);
        CharStream charStream = input != null ? input.getTokenSource().getInputStream() : null;
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        }
    }

    @Override
    public void reset() {
        super.reset();

        // must check for null because reset() is called from the Parser constructor, before fields of GoParser are initialized
        if (packageNames != null) {
            packageNames.clear();
        }
    }

    @Override
    public void setInputStream(TokenStream<? extends Token> input) {
        super.setInputStream(input);

        CharStream charStream = input != null ? input.getTokenSource().getInputStream() : null;
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        } else {
            this.snapshot = null;
        }
    }

    @Override
    protected void addPackageName(Token token) {
        String name = getPackageName(token);
        if (name == null || name.isEmpty()) {
            return;
        }

        packageNames.add(name);
    }

    @Override
    protected boolean isPackageName(Token token) {
        return token != null && packageNames.contains(token.getText());
    }

    public Collection<? extends String> getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(Collection<? extends String> names) {
        packageNames.clear();
        packageNames.addAll(names);
    }
}
