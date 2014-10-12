/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.Arrays;
import java.util.Collection;
import org.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateParserTask;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.works.editor.antlr4.navigation.ParseTreeNode;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.GoParser;

/**
 *
 * @author Sam Harwell
 */
public final class ParseTreeNavigatorUpdateParserTask extends AbstractNavigatorUpdateParserTask<GoParseTreeNavigatorPanel, CompiledModel> {

    private ParseTreeNavigatorUpdateParserTask() {
        super(GoParserDataDefinitions.COMPILED_MODEL);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected GoParseTreeNavigatorPanel getActiveNavigatorPanel() {
        return GoParseTreeNavigatorPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, GoParseTreeNavigatorPanel panel, CompiledModel data) {
        panel.setCurrentFile(data.getResult().getFileObject());
        panel.setParseTree(new ParseTreeNode(data.getResult().getResult(), Arrays.asList(GoParser.ruleNames)));
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GoParserDataDefinitions.COMPILED_MODEL,
                GoParserDataDefinitions.PARSE_TREE_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Parse Tree Navigator Update", INPUTS);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new ParseTreeNavigatorUpdateParserTask();
        }

    }

}
