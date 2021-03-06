/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.DocumentParserTaskProvider;
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
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.CompiledModel;

/**
 * This task computes the root node for the navigator.
 *
 * @author Sam Harwell
 */
public final class GoDeclarationsScannerParserTask implements ParserTask {
    private final GoDeclarationsScanner declarationsScanner = new GoDeclarationsScanner();
    private final Object lock = new Object();

    private GoDeclarationsScannerParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        boolean explicitRequest = ParserTaskScheduler.MANUAL_TASK_SCHEDULER.isAssignableFrom(context.getSchedulerClass());
        if (!explicitRequest && snapshot.getVersionedDocument().getDocument() == null) {
            // no update for background parsed document unless specifically requested
            return;
        }

        if (requestedData.contains(GoParserDataDefinitions.NAVIGATOR_ROOT)) {
            synchronized (lock) {
                Future<ParserData<Description>> futureData = taskManager.getData(snapshot, GoParserDataDefinitions.NAVIGATOR_ROOT, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
                ParserData<Description> data = futureData != null ? futureData.get() : null;
                if (data != null) {
                    results.addResult(data);
                    return;
                }

                EnumSet<ParserDataOptions> options;
                if (explicitRequest) {
                    options = EnumSet.of(ParserDataOptions.SYNCHRONOUS);
                } else {
                    options = EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS);
                }

                Future<ParserData<CompiledModel>> futureParserData = taskManager.getData(snapshot, context.getComponent(), GoParserDataDefinitions.COMPILED_MODEL, options);
                ParserData<CompiledModel> parserData = futureParserData != null ? futureParserData.get() : null;
                if (parserData == null && !explicitRequest) {
                    return;
                }

                CompiledModel model = parserData != null ? parserData.getData() : null;
                Description description = null;
                if (model != null) {
                    GoDeclarationsScanner scanner = getScanner(model);
                    description = scanner.scan(model);
                }

                data = new BaseParserData<>(context, GoParserDataDefinitions.NAVIGATOR_ROOT, snapshot, description);
                results.addResult(data);
            }
        }
    }

    private GoDeclarationsScanner getScanner(CompiledModel model) {
        return declarationsScanner;
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.NAVIGATOR_ROOT);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Declarations Scanner", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new GoDeclarationsScannerParserTask();
        }

    }

}
