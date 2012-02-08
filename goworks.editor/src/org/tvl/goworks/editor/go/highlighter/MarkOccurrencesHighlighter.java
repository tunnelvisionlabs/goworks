/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.highlighter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.TaggedPositionRegion;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.NormalizedSnapshotPositionRegionCollection;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.parsing.ParseTrees;
import org.antlr.works.editor.antlr4.semantics.AbstractSemanticHighlighter;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.FontColorSettings;
import org.netbeans.editor.BaseDocument;
import org.netbeans.lib.editor.util.swing.DocumentUtilities;
import org.netbeans.modules.editor.errorstripe.privatespi.Mark;
import org.netbeans.modules.editor.errorstripe.privatespi.MarkProviderCreator;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.OffsetsBag;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.completion.CompletionParserATNSimulator;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.CurrentDeclarationContextData;
import org.tvl.goworks.editor.go.parser.GoLexerBase;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.semantics.GoAnnotatedParseTree;
import org.tvl.goworks.editor.go.semantics.GoAnnotations;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_ERROR_STRIPE_TOOLTIP=Mark Occurrences"
})
public class MarkOccurrencesHighlighter extends AbstractSemanticHighlighter<CurrentDeclarationContextData> {
    // -J-Dorg.tvl.goworks.editor.go.highlighter.MarkOccurrencesHighlighter.level=FINE
    private static final Logger LOGGER = Logger.getLogger(MarkOccurrencesHighlighter.class.getName());

    public static final Color ERROR_STRIPE_COLOR = new Color(175, 172, 102);

    private final JTextComponent component;
    private final AttributeSet markOccurrencesAttributes;
    private final MarkOccurrencesMarkProviderCreator markProviderCreator;

    private MarkOccurrencesHighlighter(@NonNull JTextComponent component) {
        super((StyledDocument)component.getDocument(), GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT);

        Lookup lookup = MimeLookup.getLookup(MimePath.parse(GoEditorKit.GO_MIME_TYPE));
        FontColorSettings settings = lookup.lookup(FontColorSettings.class);

        this.component = component;
        this.markOccurrencesAttributes = getFontAndColors(settings, "markOccurrences");
        Collection<? extends MarkProviderCreator> markProviderCreators = MimeLookup.getLookup(DocumentUtilities.getMimeType(component)).lookupAll(MarkProviderCreator.class);
        MarkOccurrencesMarkProviderCreator markOccurrencesMarkProviderCreator = null;
        for (MarkProviderCreator creator : markProviderCreators) {
            if (creator instanceof MarkOccurrencesMarkProviderCreator) {
                markOccurrencesMarkProviderCreator = (MarkOccurrencesMarkProviderCreator)creator;
                break;
            }
        }

        this.markProviderCreator = markOccurrencesMarkProviderCreator;
    }

    protected MarkOccurrencesListener createListener(ParserData<? extends CurrentDeclarationContextData> parserData) {
        if (parserData.getContext().getPosition() == null) {
            return null;
        }

        FileModel fileModel = null;
        GoAnnotatedParseTree annotatedParseTree = null;
        try {
            Future<ParserData<FileModel>> futureFileModelData = getTaskManager().getData(parserData.getSnapshot(), GoParserDataDefinitions.FILE_MODEL);
            Future<ParserData<GoAnnotatedParseTree>> futureAnnotatedParseTreeData = getTaskManager().getData(parserData.getSnapshot(), GoParserDataDefinitions.ANNOTATED_PARSE_TREE);

            ParserData<FileModel> annotatedFileModelData = futureFileModelData != null ? futureFileModelData.get() : null;
            fileModel = annotatedFileModelData != null ? annotatedFileModelData.getData() : null;

            ParserData<GoAnnotatedParseTree> annotatedParseTreeData = futureAnnotatedParseTreeData != null ? futureAnnotatedParseTreeData.get() : null;
            annotatedParseTree = annotatedParseTreeData != null ? annotatedParseTreeData.getData() : null;
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }

        if (fileModel == null || annotatedParseTree == null) {
            return null;
        }

        return new MarkOccurrencesListener(fileModel, annotatedParseTree, parserData.getContext().getPosition());
    }

    private final List<SnapshotPosition> markPositions = new ArrayList<SnapshotPosition>();

    @Override
    protected void addHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, Collection<Token> tokens, AttributeSet attributes) {
        for (Token token : tokens) {
            TrackingPositionRegion trackingRegion = sourceSnapshot.createTrackingRegion(OffsetRegion.fromBounds(token.getStartIndex(), token.getStopIndex() + 1), TrackingPositionRegion.Bias.Forward);
            SnapshotPositionRegion region = trackingRegion.getRegion(currentSnapshot);
            container.addHighlight(region.getStart().getOffset(), region.getEnd().getOffset(), attributes);
            markPositions.add(region.getStart());
        }
    }

    protected void updateHighlights(OffsetsBag container, DocumentSnapshot sourceSnapshot, DocumentSnapshot currentSnapshot, MarkOccurrencesListener listener) {
        OffsetsBag updateBag = new OffsetsBag(currentSnapshot.getVersionedDocument().getDocument());
        markPositions.clear();
        addHighlights(updateBag, sourceSnapshot, currentSnapshot, listener.getMarkedOccurrences(), markOccurrencesAttributes);
        container.setHighlights(updateBag);
        Collection<Mark> marks = MarkOccurrencesMarkProvider.createMarks(currentSnapshot.getVersionedDocument(), markPositions, ERROR_STRIPE_COLOR, Bundle.LBL_ERROR_STRIPE_TOOLTIP());
        MarkOccurrencesMarkProvider markProvider = markProviderCreator.createMarkProvider(component);
        markProvider.setOccurrences(marks);
    }

    @Override
    protected Callable<Void> createAnalyzerTask(@NonNull final ParserData<? extends CurrentDeclarationContextData> parserData) {
        return new Callable<Void>() {
            @Override
            public Void call() {
                final MarkOccurrencesListener listener = createListener(parserData);
                if (listener == null) {
                    return null;
                }

                try {
                    ParseTreeWalker.DEFAULT.walk(listener, listener.annotatedParseTree.getParseTree());
                } catch (RuntimeException ex) {
                    Exceptions.printStackTrace(ex);
                    throw ex;
                }

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ((BaseDocument)getDocument()).render(new Runnable() {

                            @Override
                            public void run() {
                                DocumentSnapshot sourceSnapshot = parserData.getSnapshot();
                                DocumentSnapshot currentSnapshot = sourceSnapshot.getVersionedDocument().getCurrentSnapshot();
                                updateHighlights(getContainer(), sourceSnapshot, currentSnapshot, listener);
                            }

                        });
                    }

                });

                return null;
            }
        };
    }

    public static Token getContext(SnapshotPosition position) {
        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        DocumentSnapshot snapshot = position.getSnapshot();
        int offset = position.getOffset();
        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS, EnumSet.of(ParserDataOptions.SYNCHRONOUS));
        Tagger<TokenTag<Token>> tagger;
        try {
            tagger = futureTokensData.get().getData();
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        } catch (ExecutionException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }

        // get the token(s) at the cursor position, with affinity both directions
        OffsetRegion region = OffsetRegion.fromBounds(Math.max(0, offset - 1), Math.min(snapshot.length(), offset + 1));
        Iterable<TaggedPositionRegion<TokenTag<Token>>> tags = tagger.getTags(new NormalizedSnapshotPositionRegionCollection(new SnapshotPositionRegion(snapshot, region)));

        Token token = null;
        for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
            if (taggedRegion.getTag().getToken().getChannel() != Lexer.DEFAULT_TOKEN_CHANNEL) {
                continue;
            }

            Token previousToken = token;
            Token nextToken = taggedRegion.getTag().getToken();
            if (nextToken.getStartIndex() <= offset && nextToken.getStopIndex() + 1 >= offset) {
                if (previousToken != null && previousToken.getStopIndex() + 1 == offset) {
                    // prefer the end of a word token to the beginning of a non-word token
                    if (CompletionParserATNSimulator.WORDLIKE_TOKEN_TYPES.contains(previousToken.getType())) {
                        break;
                    }
                }

                token = nextToken;
            }
        }

        if (token == null) {
            // try again without skipping off-channel tokens
            for (TaggedPositionRegion<TokenTag<Token>> taggedRegion : tags) {
                token = taggedRegion.getTag().getToken();
                if (token.getStartIndex() <= offset && token.getStopIndex() >= offset) {
                    break;
                }
            }
        }

        return token;
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory extends AbstractLayerFactory {

        public LayerFactory() {
            super(MarkOccurrencesHighlighter.class);
        }

        @Override
        protected AbstractSemanticHighlighter<?> createHighlighter(HighlightsLayerFactory.Context context) {
            return new MarkOccurrencesHighlighter(context.getComponent());
        }

        @Override
        protected ZOrder getPosition() {
            return ZOrder.CARET_RACK.forPosition(50);
        }

    }

    public static class MarkOccurrencesListener extends BlankGoParserBaseListener {

        private final FileModel fileModel;
        private final GoAnnotatedParseTree annotatedParseTree;

        private final List<Token> markedOccurrences = new ArrayList<Token>();

        private final Token currentToken;

        @NonNull
        private final Collection<? extends CodeElementModel> referencedElements;

        public MarkOccurrencesListener(FileModel fileModel, GoAnnotatedParseTree annotatedParseTree, SnapshotPosition position) {
            this.fileModel = fileModel;
            this.annotatedParseTree = annotatedParseTree;

            this.currentToken = getContext(position);
            this.referencedElements = currentToken != null ? findReferencedElements(currentToken) : Collections.<CodeElementModel>emptyList();
        }

        @NonNull
        public List<Token> getMarkedOccurrences() {
            return markedOccurrences;
        }

        @Override
        public void visitTerminal(ParserRuleContext<Token> ctx, Token symbol) {
            if (referencedElements.isEmpty()) {
                return;
            }

            if (symbol.getType() != GoParser.IDENTIFIER || !currentToken.getText().equals(symbol.getText())) {
                return;
            }

            Collection<? extends CodeElementModel> currentElements = findReferencedElements(symbol);
            for (CodeElementModel i : referencedElements) {
                for (CodeElementModel j : currentElements) {
                    if (i.equals(j)) {
                        markedOccurrences.add(symbol);
                        return;
                    }
                }
            }
        }

        @NonNull
        private Collection<? extends CodeElementModel> findReferencedElements(Token symbol) {
            Collection<? extends CodeElementModel> models = annotatedParseTree.getTokenDecorator().getProperty(symbol, GoAnnotations.MODELS);
            if (models == null) {
                return Collections.emptyList();
            }

            return models;
        }

    }

}
