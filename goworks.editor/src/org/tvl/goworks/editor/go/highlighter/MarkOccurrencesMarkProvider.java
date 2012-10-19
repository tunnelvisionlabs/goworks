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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPosition;
import org.antlr.netbeans.editor.text.TrackingPosition;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.modules.editor.errorstripe.privatespi.Mark;
import org.netbeans.modules.editor.errorstripe.privatespi.MarkProvider;
import org.netbeans.modules.editor.errorstripe.privatespi.Status;
import org.openide.text.NbDocument;

/**
 *
 * @author Sam Harwell
 */
public class MarkOccurrencesMarkProvider extends MarkProvider {
    // -J-Dorg.tvl.goworks.editor.go.highlighter.MarkOccurrencesMarkProvider.level=FINE
    private static final Logger LOGGER = Logger.getLogger(MarkOccurrencesMarkProvider.class.getName());

    private final Object lock = new Object();

    @NonNull
    private List<Mark> occurrences;

    public MarkOccurrencesMarkProvider() {
        this.occurrences = Collections.emptyList();
    }

    @Override
    public List<Mark> getMarks() {
        synchronized (lock) {
            return occurrences;
        }
    }

    public void setOccurrences(Collection<Mark> marks) {
        List<Mark> old;
        List<Mark> nue;

        synchronized (lock) {
            old = occurrences;
            occurrences = new ArrayList<Mark>(marks);
            nue = occurrences;
        }

        //#85919: fire outside the lock:
        firePropertyChange(PROP_MARKS, old, nue);
    }

    public static Collection<Mark> createMarks(final VersionedDocument document, final Collection<SnapshotPosition> bag, final Color color, final String tooltip) {
        final Document doc = document.getDocument();
        if (doc == null) {
            return Collections.emptyList();
        }

        final List<Mark> result = new LinkedList<Mark>();
        doc.render(new Runnable() {
            @Override
            public void run() {
                DocumentSnapshot currentSnapshot = document.getCurrentSnapshot();
                for (SnapshotPosition position : bag) {
                    try {
                        SnapshotPosition translatedPosition = position;
                        if (!position.getSnapshot().equals(currentSnapshot)) {
                            TrackingPosition trackingPosition = position.getSnapshot().createTrackingPosition(position.getOffset(), TrackingPosition.Bias.Forward);
                            translatedPosition = trackingPosition.getPosition(currentSnapshot);
                        }

                        result.add(new MarkImpl(doc, doc.createPosition(translatedPosition.getOffset()), color, tooltip));
                    } catch (BadLocationException ex) {
                        LOGGER.log(Level.WARNING, "An exception occurred while marking occurrences.", ex);
                    }
                }
            }
        });

        return result;
    }

    private static class MarkImpl implements Mark {

        private Document doc;
        private Position startOffset;
        private Color color;
        private String tooltip;

        public MarkImpl(Document doc, Position startOffset, Color color, String tooltip) {
            this.doc = doc;
            this.startOffset = startOffset;
            this.color = color;
            this.tooltip = tooltip;
        }

        @Override
        public int getType() {
            return TYPE_ERROR_LIKE;
        }

        @Override
        public Status getStatus() {
            return Status.STATUS_OK;
        }

        @Override
        public int getPriority() {
            return PRIORITY_DEFAULT;
        }

        @Override
        public Color getEnhancedColor() {
            return color;
        }

        @Override
        public int[] getAssignedLines() {
            int line = NbDocument.findLineNumber((StyledDocument) doc, startOffset.getOffset());

            return new int[] {line, line};
        }

        @Override
        public String getShortDescription() {
            return tooltip;
        }

    }

}
