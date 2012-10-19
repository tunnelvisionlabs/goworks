/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.completion.Anchor;
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
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class ReferenceAnchorsParserTask implements ParserTask {

    private static final Logger LOGGER = Logger.getLogger(ReferenceAnchorsParserTask.class.getName());
    private final Object lock = new Object();


    private ReferenceAnchorsParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results) throws InterruptedException, ExecutionException {
        synchronized (lock) {
            Future<ParserData<List<Anchor>>> futureAnchorPointsResult = taskManager.getData(snapshot, GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<List<Anchor>> anchorPointsResult = futureAnchorPointsResult != null ? futureAnchorPointsResult.get() : null;
            Future<ParserData<FileModel>> futureFileModelResult = taskManager.getData(snapshot, GoParserDataDefinitions.FILE_MODEL, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<FileModel> fileModelResult = futureFileModelResult != null ? futureFileModelResult.get() : null;
            if (anchorPointsResult == null || fileModelResult == null) {
                Future<ParserData<CompiledModel>> futureCompiledModelData = taskManager.getData(snapshot, GoParserDataDefinitions.COMPILED_MODEL);
                ParserData<CompiledModel> compiledModelData = futureCompiledModelData != null ? futureCompiledModelData.get() : null;
                CompiledModel compiledModel = compiledModelData != null ? compiledModelData.getData() : null;
                CompiledFileModel compiledFileModel = compiledModel != null ? compiledModel.getResult() : null;
                ParserRuleContext<Token> parseResult = compiledFileModel != null ? compiledFileModel.getResult() : null;
                Token[] tokens = compiledFileModel != null ? compiledFileModel.getTokens() : null;

                if (anchorPointsResult == null && snapshot.getVersionedDocument().getDocument() != null) {
                    GoParserAnchorListener listener = new GoParserAnchorListener(snapshot);
                    if (parseResult != null) {
                        ParseTreeWalker.DEFAULT.walk(listener, parseResult);
                    }
                    anchorPointsResult = new BaseParserData<List<Anchor>>(context, GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, listener.getAnchors());
                }

                if (fileModelResult == null) {
                    try {
                        CodeModelBuilderListener codeModelBuilderListener = new CodeModelBuilderListener(snapshot, tokens);
                        if (parseResult != null) {
                            CodeModelBuilderListener.PARSE_TREE_WALKER.walk(codeModelBuilderListener, parseResult);
                        }
                        FileModelImpl fileModel = codeModelBuilderListener.getFileModel();
                        if (fileModel != null) {
                            updateCodeModelCache(fileModel);
                        }
                        fileModelResult = new BaseParserData<FileModel>(context, GoParserDataDefinitions.FILE_MODEL, snapshot, fileModel);
                    } catch (RuntimeException ex) {
                        LOGGER.log(Level.WARNING, "An exception occurred while analyzing reference anchors.", ex);
                    } catch (Error ex) {
                        LOGGER.log(Level.WARNING, "An error occurred while analyzing reference anchors.", ex);
                    }
                }
            }

            if (fileModelResult != null) {
                results.addResult(fileModelResult);
            }

            if (anchorPointsResult != null) {
                results.addResult(anchorPointsResult);
            }
        }
    }

    private void updateCodeModelCache(FileModelImpl fileModel) {
        CodeModelCacheImpl codeModelCache = CodeModelCacheImpl.getInstance();
        codeModelCache.updateFile(fileModel);
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS,
                GoParserDataDefinitions.FILE_MODEL);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Reference Anchors", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
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
            return new ReferenceAnchorsParserTask();
        }

    }

}
