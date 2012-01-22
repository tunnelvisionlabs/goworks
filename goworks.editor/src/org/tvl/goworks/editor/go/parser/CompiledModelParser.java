/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.shared.parser.SyntaxError;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;

/**
 *
 * @author Sam Harwell
 */
public class CompiledModelParser {
    // -J-Dorg.tvl.goworks.editor.go.parser.CompiledModelParser.level=FINE
    private static final Logger LOGGER = Logger.getLogger(CompiledModelParser.class.getName());

    private final Object lock = new Object();
    private DocumentSnapshot lastSnapshot;
    private CompiledFileModel lastResult;

    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        try {
            if (requestedData.contains(GoParserDataDefinitions.COMPILED_MODEL)) {
                CompiledModel result = parseImpl(taskManager, component, snapshot);
                BaseParserData<CompiledModel> data = new BaseParserData<CompiledModel>(GoParserDataDefinitions.COMPILED_MODEL, snapshot, result);
                results.addResult(data);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An error occurred while parsing.", ex);
            }
        }
    }

    protected CompiledModel parseImpl(@NonNull ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                return new CompiledModel(snapshot, lastResult);
            }
        }

        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Reparsing snapshot {0}", new Object[] { snapshot });
        }

        try {
            ANTLRInputStream input = new ANTLRInputStream(snapshot.getText().toString());
            input.name = snapshot.getVersionedDocument().getFileObject().getNameExt();

            GoLexer lexer = new GoLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GoParser parser = new GoParser(tokens, snapshot);
            parser.setBuildParseTree(true);
            try {
                sourceFileContext context = parser.sourceFile();
                FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
                @SuppressWarnings("unchecked")
                Token[] groupTokens = tokens.getTokens().toArray(new Token[0]);
                return new CompiledModel(snapshot, new CompiledFileModel(parser, context, parser.getSyntaxErrors(), fileObject, groupTokens));
            } catch (RecognitionException ex) {
                if (LOGGER.isLoggable(Level.FINE)) {
                    LOGGER.log(Level.FINE, "A recognition exception occurred while parsing.", ex);
                }
                return null;
            }
        } catch (Exception ex) {
            throw new ExecutionException("An unexpected error occurred.", ex);
        }
    }

}
