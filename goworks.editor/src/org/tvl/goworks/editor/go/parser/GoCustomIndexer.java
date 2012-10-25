/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.parsing.SyntaxErrorConvertor;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.impl.TaskSchedulers;
import org.antlr.v4.runtime.misc.Tuple;
import org.antlr.v4.runtime.misc.Tuple2;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.CustomIndexer;
import org.netbeans.modules.parsing.spi.indexing.CustomIndexerFactory;
import org.netbeans.modules.parsing.spi.indexing.ErrorsCache;
import org.netbeans.modules.parsing.spi.indexing.Indexable;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizerRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.project.GoProject;

/**
 *
 * @author Sam Harwell
 */
public class GoCustomIndexer extends CustomIndexer {
    private static final Logger LOGGER = Logger.getLogger(GoCustomIndexer.class.getName());

    private static final String INDEX_NAME = "golang";
    private static final int INDEX_VERSION = 1;

    public GoCustomIndexer() {
    }

    @Override
    protected void index(Iterable<? extends Indexable> files, Context context) {
        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        Deque<Tuple2<Indexable, Future<ParserData<FileModel>>>> futures = new ArrayDeque<Tuple2<Indexable, Future<ParserData<FileModel>>>>();
        for (Indexable file : files) {
            try {
                if (!GoEditorKit.GO_MIME_TYPE.equals(file.getMimeType())) {
                    continue;
                }

                FileObject fileObject = URLMapper.findFileObject(file.getURL());
                if (fileObject == null) {
                    continue;
                }

                VersionedDocument versionedDocument = VersionedDocumentUtilities.getVersionedDocument(fileObject);
                ParseContext parseContext = new ParseContext(TaskSchedulers.getScheduler(ParserTaskScheduler.MANUAL_TASK_SCHEDULER), versionedDocument);
                Future<ParserData<FileModel>> future = taskManager.scheduleData(parseContext, GoParserDataDefinitions.FILE_MODEL);
                futures.add(Tuple.create(file, future));
                if (context.isCancelled()) {
                    break;
                }
            } catch (RuntimeException ex) {
                LOGGER.log(Level.WARNING, "An error occurred while indexing a document.", ex);
            }
        }

        while (!futures.isEmpty()) {
            Tuple2<Indexable, Future<ParserData<FileModel>>> pair = futures.poll();
            Future<ParserData<FileModel>> future = pair.getItem2();
            if (future == null) {
                ErrorsCache.setErrors(context.getRootURI(), pair.getItem1(), Collections.<SyntaxError>emptyList(), SyntaxErrorConvertor.INSTANCE);
                continue;
            }

            if (context.isCancelled()) {
                future.cancel(true);
                continue;
            }

            ParserData<FileModel> parserData = null;
            try {
                parserData = future.get(0, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred while indexing a document.", ex);
            } catch (ExecutionException ex) {
                LOGGER.log(Level.WARNING, "An exception occurred while indexing a document.", ex);
            } catch (TimeoutException ex) {
                futures.add(pair);
                continue;
            }

            Iterable<? extends SyntaxError> errors = null;
            if (parserData != null) {
                Future<ParserData<CompiledModel>> futureCompiledModelData = taskManager.getData(parserData.getSnapshot(), null, GoParserDataDefinitions.COMPILED_MODEL);

                ParserData<CompiledModel> compiledModelData = null;
                try {
                    compiledModelData = futureCompiledModelData != null ? futureCompiledModelData.get() : null;
                } catch (InterruptedException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while indexing a document.", ex);
                } catch (ExecutionException ex) {
                    LOGGER.log(Level.WARNING, "An exception occurred while indexing a document.", ex);
                }

                CompiledModel model = compiledModelData != null ? compiledModelData.getData() : null;
                if (model != null) {
                    errors = model.getResult().getSyntaxErrors();
                }
            }

            if (errors == null) {
                errors = Collections.emptyList();
            }

            ErrorsCache.setErrors(context.getRootURI(), pair.getItem1(), errors, SyntaxErrorConvertor.INSTANCE);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=CustomIndexerFactory.class)
    @PathRecognizerRegistration(sourcePathIds={ GoProject.SOURCE }, binaryLibraryPathIds={}, libraryPathIds={ GoProject.PLATFORM }, mimeTypes={ GoEditorKit.GO_MIME_TYPE })
    public static class Factory extends CustomIndexerFactory {

        public Factory() {
        }

        @Override
        public boolean scanStarted(Context context) {
            return false;
        }

        @Override
        public void scanFinished(Context context) {
        }

        @Override
        public CustomIndexer createIndexer() {
            return new GoCustomIndexer();
        }

        @Override
        public boolean supportsEmbeddedIndexers() {
            return true;
        }

        @Override
        public void filesDeleted(Iterable<? extends Indexable> deleted, Context context) {
            LOGGER.log(Level.FINE, "filesDeleted({0})", deleted);
        }

        @Override
        public void rootsRemoved(Iterable<? extends URL> removedRoots) {
            LOGGER.log(Level.FINE, "roots removed: {0}", removedRoots);
        }

        @Override
        public void filesDirty(Iterable<? extends Indexable> dirty, Context context) {
            LOGGER.log(Level.FINE, "filesDirty({0})", dirty);
        }

        @Override
        public String getIndexerName() {
            return INDEX_NAME;
        }

        @Override
        public int getIndexVersion() {
            return INDEX_VERSION;
        }

    }

}
