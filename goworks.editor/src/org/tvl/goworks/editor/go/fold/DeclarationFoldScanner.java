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
import java.util.Collections;
import java.util.List;
import org.antlr.netbeans.editor.fold.AbstractFoldScanner.FoldInfo;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.antlr4.fold.AbstractAntlrFoldScanner;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.ImportDeclContext;
import org.tvl.goworks.editor.go.parser.AbstractGoParser.TopLevelDeclContext;
import org.tvl.goworks.editor.go.parser.CompiledFileModel;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParser;
import org.tvl.goworks.editor.go.parser.GoParserBaseListener;

/**
 *
 * @author Sam Harwell
 */
public class DeclarationFoldScanner extends AbstractAntlrFoldScanner<CompiledModel> {

    @Override
    protected List<FoldInfo> calculateFolds(ParserData<CompiledModel> result) {
        DocumentSnapshot snapshot = result.getSnapshot();

        final List<FoldInfo> folds = new ArrayList<FoldInfo>();
        CompiledModel model = result.getData();
        if (model == null) {
            return Collections.emptyList();
        }

        CompiledFileModel fileModel = model.getResult();
        FoldListener listener = new FoldListener(snapshot, folds);
        ParseTreeWalker.DEFAULT.walk(listener, fileModel.getResult());
        return folds;
    }

    private class FoldListener extends GoParserBaseListener {
        private final DocumentSnapshot snapshot;
        private final Collection<FoldInfo> folds;

        public FoldListener(DocumentSnapshot snapshot, Collection<FoldInfo> folds) {
            this.snapshot = snapshot;
            this.folds = folds;
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_topLevelDecl, version=0)
        public void enterTopLevelDecl(TopLevelDeclContext ctx) {
            FoldInfo foldInfo = createFold(ctx, "...", snapshot);
            if (foldInfo != null) {
                folds.add(foldInfo);
            }
        }

        @Override
        @RuleDependency(recognizer=GoParser.class, rule=GoParser.RULE_importDecl, version=0)
        public void enterImportDecl(ImportDeclContext ctx) {
            FoldInfo foldInfo = createFold(ctx, "...", snapshot);
            if (foldInfo != null) {
                folds.add(foldInfo);
            }
        }

    }
}
