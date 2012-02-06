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
import java.util.List;
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
public class GoParser extends GoParserBase {
    private final List<SyntaxError> syntaxErrors = new ArrayList<SyntaxError>();
    private DocumentSnapshot snapshot;

    public GoParser(TokenStream input) {
        super(input);
        CharStream charStream = input.getTokenSource().getInputStream();
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
        if (syntaxErrors != null) {
            syntaxErrors.clear();
        }
    }

    @Override
    public void setTokenStream(TokenStream input) {
        super.setTokenStream(input);

        CharStream charStream = input.getTokenSource().getInputStream();
        if (charStream instanceof DocumentSnapshotCharStream) {
            DocumentSnapshotCharStream documentSnapshotCharStream = (DocumentSnapshotCharStream)charStream;
            this.snapshot = documentSnapshotCharStream.getSnapshot();
        } else {
            this.snapshot = null;
        }
    }

    protected class ErrorListener implements ANTLRErrorListener<Token> {

        @Override
        public void error(Recognizer<Token, ?> recognizer, Token offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            if (snapshot != null) {
                syntaxErrors.add(new AntlrSyntaxErrorV4(snapshot, offendingSymbol, e, msg, Severity.ERROR));
            }
        }

    }
}
