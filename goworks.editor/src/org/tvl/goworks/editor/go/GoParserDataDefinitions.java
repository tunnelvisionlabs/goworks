/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.CurrentDeclarationContextData;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;

/**
 *
 * @author Sam Harwell
 */
public class GoParserDataDefinitions {
    private static final Logger LOGGER = Logger.getLogger(GoParserDataDefinitions.class.getName());

    public static final ParserDataDefinition<CompiledModel> COMPILED_MODEL = new CompiledModelDataDefinition();
    public static final ParserDataDefinition<GoAnnotatedParseTree> ANNOTATED_PARSE_TREE = new AnnotatedParseTreeDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> REFERENCE_ANCHOR_POINTS = new ReferenceAnchorPointsDataDefinition();

    public static final ParserDataDefinition<List<Anchor>> DYNAMIC_ANCHOR_POINTS = new DynamicAnchorPointsDataDefinition();
    public static final ParserDataDefinition<Tagger<TokenTag<Token>>> LEXER_TOKENS = new LexerTokensDataDefinition();
    public static final ParserDataDefinition<CurrentDeclarationContextData> CURRENT_DECLARATION_CONTEXT = new CurrentDeclarationContextDataDefinition();
    public static final ParserDataDefinition<FileModel> FILE_MODEL = new FileModelDataDefinition();

    public static final ParserDataDefinition<Description> NAVIGATOR_ROOT = new NavigatorRootDataDefinition();

    private GoParserDataDefinitions() {
    }

    public static <T> T tryGetData(ParserTaskManager taskManager, DocumentSnapshot snapshot, ParserDataDefinition<T> definition, Collection<ParserDataOptions> options) {
        Future<ParserData<T>> futureData = taskManager.getData(snapshot, definition, options);
        if (futureData == null) {
            return null;
        }

        try {
            ParserData<T> parserData = futureData.get();
            if (parserData == null) {
                return null;
            }

            if (parserData != null) {
                return parserData.getData();
            }
        } catch (InterruptedException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, String.format("An exception occurred while parsing '%s' data.", definition.getName()), ex);
            }
        } catch (ExecutionException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                LOGGER.log(Level.FINE, String.format("An exception occurred while parsing '%s' data.", definition.getName()), ex);
            }
        }

        return null;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CompiledModel> getCompiledModelDataDefinition() {
        return COMPILED_MODEL;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<GoAnnotatedParseTree> getAnnotatedParseTreeDataDefinition() {
        return ANNOTATED_PARSE_TREE;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getReferenceAnchorPointsDataDefinition() {
        return REFERENCE_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<List<Anchor>> getDynamicAnchorPointsDataDefinition() {
        return DYNAMIC_ANCHOR_POINTS;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Tagger<TokenTag<Token>>> getLexerTokensDataDefinition() {
        return LEXER_TOKENS;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<CurrentDeclarationContextData> getCurrentDeclarationContextDataDefinition() {
        return CURRENT_DECLARATION_CONTEXT;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<FileModel> getFileModelDataDefinition() {
        return FILE_MODEL;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserDataDefinition.class)
    public static ParserDataDefinition<Description> getNavigatorRootDataDefinition() {
        return NAVIGATOR_ROOT;
    }

    private static final class CompiledModelDataDefinition extends ParserDataDefinition<CompiledModel> {

        public CompiledModelDataDefinition() {
            super("Go Compiled Model", CompiledModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class AnnotatedParseTreeDataDefinition extends ParserDataDefinition<GoAnnotatedParseTree> {

        public AnnotatedParseTreeDataDefinition() {
            super("Go Annotated Parse Tree", GoAnnotatedParseTree.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class ReferenceAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public ReferenceAnchorPointsDataDefinition() {
            super("Go Reference Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class DynamicAnchorPointsDataDefinition extends ParserDataDefinition<List<Anchor>> {

        @SuppressWarnings("unchecked")
        public DynamicAnchorPointsDataDefinition() {
            super("Go Dynamic Anchor Points", (Class<List<Anchor>>)(Object)List.class, false, true, ParserTaskScheduler.EDITOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class LexerTokensDataDefinition extends ParserDataDefinition<Tagger<TokenTag<Token>>> {

        @SuppressWarnings("unchecked")
        public LexerTokensDataDefinition() {
            super("Go Lexer Tokens", (Class<Tagger<TokenTag<Token>>>)(Object)Tagger.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class CurrentDeclarationContextDataDefinition extends ParserDataDefinition<CurrentDeclarationContextData> {

        public CurrentDeclarationContextDataDefinition() {
            super("Go Current Rule Context", CurrentDeclarationContextData.class, true, true, ParserTaskScheduler.CURSOR_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class FileModelDataDefinition extends ParserDataDefinition<FileModel> {

        public FileModelDataDefinition() {
            super("Go File Model", FileModel.class, false, true, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }

    }

    private static final class NavigatorRootDataDefinition extends ParserDataDefinition<Description> {

        public NavigatorRootDataDefinition() {
            super("Go Navigator Root", Description.class, false, true, ParserTaskScheduler.MANUAL_TASK_SCHEDULER);
        }

    }
}
