/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.util.prefs.Preferences;
import javax.swing.text.Document;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;
import org.openide.filesystems.FileObject;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public final class CodeStyle {

    private final Preferences _preferences;

    static {
        FormatOptions.codeStyleFactory = new Factory();
    }

    private CodeStyle(Preferences preferences) {
        this._preferences = preferences;
    }

    public synchronized static CodeStyle getDefault(Document document) {
        Preferences preferences = CodeStylePreferences.get(document, GoEditorKit.GO_MIME_TYPE).getPreferences();
        return new CodeStyle(preferences);
    }

    public synchronized static CodeStyle getDefault(FileObject file) {
        Preferences preferences = CodeStylePreferences.get(file, GoEditorKit.GO_MIME_TYPE).getPreferences();
        return new CodeStyle(preferences);
    }

    public boolean expandTabToSpaces() {
        return FormatOptions.expandTabToSpaces.getValue(_preferences);
    }

    public int getTabSize() {
        return FormatOptions.tabSize.getValue(_preferences);
    }

    public int getSpacesPerTab() {
        return FormatOptions.spacesPerTab.getValue(_preferences);
    }

    public int getIndentSize() {
        int indentLevel = FormatOptions.indentSize.getValue(_preferences);
        if (indentLevel <= 0) {
            if (expandTabToSpaces()) {
                indentLevel = FormatOptions.spacesPerTab.getValue(_preferences);
            } else {
                indentLevel = getTabSize();
            }
        }

        return indentLevel;
    }

    public int getRightMargin() {
        return FormatOptions.rightMargin.getValue(_preferences);
    }

    public static final class Factory implements FormatOptions.CodeStyleFactory {
        @Override
        public CodeStyle create(Preferences preferences) {
            return new CodeStyle(preferences);
        }
    }

}
