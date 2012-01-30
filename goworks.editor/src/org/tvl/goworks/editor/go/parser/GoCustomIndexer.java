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
