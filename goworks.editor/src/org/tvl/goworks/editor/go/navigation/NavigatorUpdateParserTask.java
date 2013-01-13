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
import org.antlr.netbeans.editor.navigation.AbstractNavigatorUpdateWithContextParserTask;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.CurrentDeclarationContextData;

/**
 * Given a navigator root node, this task refreshes the navigator window itself.
 *
 * @author Sam Harwell
 */
public final class NavigatorUpdateParserTask extends AbstractNavigatorUpdateWithContextParserTask<GoDeclarationsPanel, Description, CurrentDeclarationContextData> {

    private NavigatorUpdateParserTask() {
        super(GoParserDataDefinitions.NAVIGATOR_ROOT, GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT);
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    protected GoDeclarationsPanel getActiveNavigatorPanel() {
        return GoDeclarationsPanel.getInstance();
    }

    @Override
    protected void refresh(ParseContext parseContext, DocumentSnapshot snapshot, GoDeclarationsPanel panel, Description data, CurrentDeclarationContextData context) {
        String selectedMember = context != null ? context.getMemberName() : null;
        panel.getComponent().refresh(data, selectedMember);
    }

    private static final class Definition extends AbstractDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GoParserDataDefinitions.NAVIGATOR_ROOT,
                GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT,
                GoParserDataDefinitions.NAVIGATOR_UI_VISIBLE);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Navigator Update", INPUTS);
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
            return new NavigatorUpdateParserTask();
        }

    }

}
