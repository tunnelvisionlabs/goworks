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
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.works.editor.antlr4.classification.DocumentSnapshotCharStream;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.GoParserBase.TopLevelDeclContext;

/**
 *
 * @author Sam Harwell
 */
public class CurrentMemberContextParserTask implements ParserTask {

    private CurrentMemberContextParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    @RuleDependency(recognizer=GoParserBase.class, rule=GoParserBase.RULE_topLevelDecl, version=0)
    public void parse(ParserTaskManager taskManager, ParseContext parseContext, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT)) {
            CurrentDeclarationContextData data = null;
            if (parseContext.getPosition() != null) {
                int caretOffset = parseContext.getPosition().getOffset();

                Future<ParserData<List<Anchor>>> result =
                    taskManager.getData(snapshot, GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));

                ParserData<List<Anchor>> anchorsData = result.get();
                List<Anchor> anchors = anchorsData.getData();

                TopLevelDeclContext context = null;

                if (anchors != null) {
                    Anchor enclosing = null;

                    /*
                    * parse the current rule
                    */
                    for (Anchor anchor : anchors) {
                        if (anchor.getSpan().getStartPosition(snapshot).getOffset() <= caretOffset && anchor.getSpan().getEndPosition(snapshot).getOffset() > caretOffset) {
                            enclosing = anchor;
                        } else if (anchor.getSpan().getStartPosition(snapshot).getOffset() > caretOffset) {
                            break;
                        }
                    }

                    if (enclosing != null) {
                        CharStream input = new DocumentSnapshotCharStream(snapshot);
                        input.seek(enclosing.getSpan().getStartPosition(snapshot).getOffset());
                        GoLexer lexer = new GoLexer(input);
                        CommonTokenStream tokens = new TaskTokenStream(lexer);
                        GoParser parser = GoFullContextParserCache.DEFAULT.getParser(tokens);
                        try {
                            parser.setBuildParseTree(true);
                            context = parser.topLevelDecl();
                        } finally {
                            GoFullContextParserCache.DEFAULT.putParser(parser);
                        }
                    }
                }

                data = new CurrentDeclarationContextData(snapshot, context);
            }

            results.addResult(new BaseParserData<CurrentDeclarationContextData>(parseContext, GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT, snapshot, data));
        }
    }

    private class TaskTokenStream extends CommonTokenStream {

        public TaskTokenStream(TokenSource<? extends Token> tokenSource) {
            super(tokenSource);
        }

        @Override
        public int LA(int i) {
//            if (cancelled) {
//                throw new CancellationException();
//            }

            return super.LA(i);
        }

    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Current Member Context", INPUTS, OUTPUTS, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new CurrentMemberContextParserTask();
        }

    }
}
