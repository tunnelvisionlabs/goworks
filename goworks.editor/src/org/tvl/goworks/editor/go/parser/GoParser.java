/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.antlr.works.editor.antlr4.parsing.AntlrSyntaxErrorV4;
import org.netbeans.spi.editor.hints.Severity;

/**
 *
 * @author Sam Harwell
 */
public class GoParser extends AbstractGoParser {
    private final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();
	private final Set<String> packageNames = new HashSet<String>();
    private DocumentSnapshot snapshot;

    public GoParser(TokenStream<? extends Token> input) {
        super(input);
        CharStream charStream = input != null ? input.getTokenSource().getInputStream() : null;
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        }
        this.addErrorListener(new ErrorListener());
    }

    public List<SyntaxError> getSyntaxErrors() {
        return new ArrayList<SyntaxError>(syntaxErrors);
    }

    @Override
    public void reset() {
        super.reset();
        if (packageNames != null) {
            packageNames.clear();
        }
        if (syntaxErrors != null) {
            syntaxErrors.clear();
        }
    }

    @Override
    public void setTokenStream(TokenStream<? extends Token> input) {
        super.setTokenStream(input);

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

    protected class ErrorListener implements ANTLRErrorListener<Token> {

        @Override
        public <T extends Token> void syntaxError(Recognizer<T, ?> recognizer, T offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            if (snapshot != null) {
                syntaxErrors.add(new AntlrSyntaxErrorV4(snapshot, offendingSymbol, e, msg, Severity.ERROR));
            }
        }

    }
}
