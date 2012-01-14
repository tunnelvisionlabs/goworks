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
package org.tvl.goworks.editor.go.fold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.shared.fold.AbstractFoldScanner;
import org.tvl.goworks.editor.go.parser.BlankGoParserBaseListener;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
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

    }
}
