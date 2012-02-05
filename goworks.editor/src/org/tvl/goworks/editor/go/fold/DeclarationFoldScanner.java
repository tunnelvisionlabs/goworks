/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.fold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.netbeans.editor.fold.AbstractFoldScanner;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParserBase.importDeclContext;
import org.tvl.goworks.editor.go.parser.GoParserBase.topLevelDeclContext;

/**
 *
 * @author Sam Harwell
 */
public class DeclarationFoldScanner extends AbstractFoldScanner<CompiledModel> {

    @Override
    protected List<FoldInfo> calculateFolds(ParserData<CompiledModel> result) {
        DocumentSnapshot snapshot = result.getSnapshot();

        final List<FoldInfo> folds = new ArrayList<FoldInfo>();
        CompiledModel model = result.getData();
        CompiledFileModel fileModel = model.getResult();
        FoldListener listener = new FoldListener(snapshot, folds);
        ParseTreeWalker.DEFAULT.walk(listener, fileModel.getResult());
        return folds;
    }

    private static FoldInfo createFold(ParserRuleContext<Token> child, String blockHint, DocumentSnapshot snapshot) {
        Token startToken = child.start;
        Token stopToken = child.stop;

        int startLine = snapshot.findLineNumber(startToken.getStartIndex());
        int stopLine = snapshot.findLineNumber(stopToken.getStopIndex());
        if (startLine >= stopLine) {
            return null;
        }

        SnapshotPositionRegion region = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(startToken.getStartIndex(), stopToken.getStopIndex() + 1));
        FoldInfo fold = new FoldInfo(region, blockHint);
        return fold;
    }

    private static class FoldListener extends BlankGoParserBaseListener {
        private final DocumentSnapshot snapshot;
        private final Collection<FoldInfo> folds;

        public FoldListener(DocumentSnapshot snapshot, Collection<FoldInfo> folds) {
            this.snapshot = snapshot;
            this.folds = folds;
        }

        @Override
        public void enterRule(topLevelDeclContext ctx) {
            FoldInfo foldInfo = createFold(ctx, "...", snapshot);
            if (foldInfo != null) {
                folds.add(foldInfo);
            }
        }

        @Override
        public void enterRule(importDeclContext ctx) {
            FoldInfo foldInfo = createFold(ctx, "...", snapshot);
            if (foldInfo != null) {
                folds.add(foldInfo);
            }
        }

    }
}
