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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.CustomIndexer;
import org.netbeans.modules.parsing.spi.indexing.CustomIndexerFactory;
import org.netbeans.modules.parsing.spi.indexing.Indexable;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;
import org.openide.util.Lookup;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;

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
                ParseContext parseContext = new ParseContext(versionedDocument);
                taskManager.scheduleData(parseContext, GoParserDataDefinitions.FILE_MODEL);
                if (context.isCancelled()) {
                    break;
                }
            } catch (RuntimeException ex) {
                LOGGER.log(Level.FINE, "An error occurred while indexing a document.", ex);
            }
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=CustomIndexerFactory.class)
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
