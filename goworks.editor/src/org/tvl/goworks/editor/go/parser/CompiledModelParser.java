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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.antlr4.classification.TaggerTokenSource;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.SourceFileContext;

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
    private Throwable lastException;

    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        try {
            if (requestedData.contains(GoParserDataDefinitions.COMPILED_MODEL)) {
                CompiledModel result = parseImpl(taskManager, context, snapshot);
                BaseParserData<CompiledModel> data = new BaseParserData<CompiledModel>(context, GoParserDataDefinitions.COMPILED_MODEL, snapshot, result);
                results.addResult(data);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "An error occurred while parsing.", ex);
            }
        }
    }

    protected CompiledModel parseImpl(@NonNull ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot)
        throws InterruptedException, ExecutionException {

        Parameters.notNull("snapshot", snapshot);

        synchronized (lock) {
            if (snapshot.equals(lastSnapshot)) {
                if (lastException != null) {
                    throw new ExecutionException("An unexpected error occurred.", lastException);
                }

                return new CompiledModel(snapshot, lastResult);
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, "Reparsing snapshot {0}", snapshot);
            }

            try {
                Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS);
                Tagger<TokenTag<Token>> tagger = futureTokensData != null ? futureTokensData.get().getData() : null;
                TaggerTokenSource<Token> tokenSource = new TaggerTokenSource<Token>(tagger, snapshot);
                CommonTokenStream tokenStream = new CommonTokenStream(tokenSource);
                GoParser parser = GoParserCache.DEFAULT.getParser(tokenStream);
                boolean fullContext = false;
                try {
                    SourceFileContext sourceFileContext;
                    try {
                        parser.setBuildParseTree(true);
                        parser.setErrorHandler(new BailErrorStrategy<Token>());
                        sourceFileContext = parser.sourceFile();
                    } catch (RuntimeException ex) {
                        GoParserCache.DEFAULT.putParser(parser);
                        fullContext = true;
                        if (ex.getClass() == RuntimeException.class && ex.getCause() instanceof RecognitionException) {
                            // retry with default error handler
                            tokenStream.reset();
                            parser = GoFullContextParserCache.DEFAULT.getParser(tokenStream);
                            parser.setBuildParseTree(true);
                            sourceFileContext = parser.sourceFile();
                        } else {
                            throw ex;
                        }
                    }

                    FileObject fileObject = snapshot.getVersionedDocument().getFileObject();
                    Token[] groupTokens = tokenStream.getTokens().toArray(new Token[0]);
                    lastSnapshot = snapshot;
                    lastResult = new CompiledFileModel(sourceFileContext, parser.getSyntaxErrors(), fileObject, groupTokens);
                    lastException = null;
                    return new CompiledModel(snapshot, lastResult);
                } catch (RecognitionException ex) {
                    if (LOGGER.isLoggable(Level.FINE)) {
                        LOGGER.log(Level.FINE, "A recognition exception occurred while parsing.", ex);
                    }

                    lastSnapshot = snapshot;
                    lastResult = null;
                    lastException = null;
                    return null;
                } finally {
                    if (!fullContext) {
                        GoParserCache.DEFAULT.putParser(parser);
                    } else {
                        GoFullContextParserCache.DEFAULT.putParser(parser);
                    }
                }
            } catch (Exception ex) {
                lastSnapshot = snapshot;
                lastResult = null;
                lastException = ex;
                throw new ExecutionException("An unexpected error occurred.", ex);
            }
        }
    }

}
