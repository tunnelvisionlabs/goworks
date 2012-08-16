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

    public boolean isAlignMultilineArrayInitializer() {
        return FormatOptions.alignMultilineArrayInitializer.getValue(_preferences);
    }

    public boolean isAlignMultilineAssignment() {
        return FormatOptions.alignMultilineAssignment.getValue(_preferences);
    }

    public boolean isAlignMultilineBinaryOperators() {
        return FormatOptions.alignMultilineBinaryOperators.getValue(_preferences);
    }

    public boolean isAlignMultilineCallArguments() {
        return FormatOptions.alignMultilineCallArguments.getValue(_preferences);
    }

    public boolean isAlignMultilineCompositeLiteral() {
        return FormatOptions.alignMultilineCompositeLiteral.getValue(_preferences);
    }

    public boolean isAlignMultilineFor() {
        return FormatOptions.alignMultilineFor.getValue(_preferences);
    }

    public boolean isAlignMultilineMethodParameters() {
        return FormatOptions.alignMultilineMethodParameters.getValue(_preferences);
    }

    public boolean isAlignMultilineParenthesized() {
        return FormatOptions.alignMultilineParenthesized.getValue(_preferences);
    }

    public boolean isAlignMultilineReturnParameters() {
        return FormatOptions.alignMultilineReturnParameters.getValue(_preferences);
    }

    public boolean isAlignMultilineTernaryOperators() {
        return FormatOptions.alignMultilineTernaryOperators.getValue(_preferences);
    }

    public int getBlankLinesBeforePackage() {
        return FormatOptions.blankLinesBeforePackage.getValue(_preferences);
    }

    public int getBlankLinesAfterPackage() {
        return FormatOptions.blankLinesAfterPackage.getValue(_preferences);
    }

    public int getBlankLinesBeforeImports() {
        return FormatOptions.blankLinesBeforeImports.getValue(_preferences);
    }

    public int getBlankLinesAfterImports() {
        return FormatOptions.blankLinesAfterImports.getValue(_preferences);
    }

    public int getBlankLinesBeforeType() {
        return FormatOptions.blankLinesBeforeType.getValue(_preferences);
    }

    public int getBlankLinesAfterType() {
        return FormatOptions.blankLinesAfterType.getValue(_preferences);
    }

    public int getBlankLinesBeforeVar() {
        return FormatOptions.blankLinesBeforeVar.getValue(_preferences);
    }

    public int getBlankLinesAfterVar() {
        return FormatOptions.blankLinesAfterVar.getValue(_preferences);
    }

    public int getBlankLinesBeforeFunction() {
        return FormatOptions.blankLinesBeforeFunction.getValue(_preferences);
    }

    public int getBlankLinesAfterFunction() {
        return FormatOptions.blankLinesAfterFunction.getValue(_preferences);
    }

    public boolean isSpaceBeforeWhile() {
        return FormatOptions.spaceBeforeWhile.getValue(_preferences);
    }

    public boolean isSpaceBeforeElse() {
        return FormatOptions.spaceBeforeElse.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensMethodDeclaration() {
        return FormatOptions.spaceBeforeParensMethodDeclaration.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensMethodCall() {
        return FormatOptions.spaceBeforeParensMethodCall.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensIf() {
        return FormatOptions.spaceBeforeParensIf.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensFor() {
        return FormatOptions.spaceBeforeParensFor.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensWhile() {
        return FormatOptions.spaceBeforeParensWhile.getValue(_preferences);
    }

    public boolean isSpaceBeforeParensSwitch() {
        return FormatOptions.spaceBeforeParensSwitch.getValue(_preferences);
    }

    public boolean isSpaceAroundUnaryOperators() {
        return FormatOptions.spaceAroundUnaryOperators.getValue(_preferences);
    }

    public boolean isSpaceAroundBinaryOperators() {
        return FormatOptions.spaceAroundBinaryOperators.getValue(_preferences);
    }

    public boolean isSpaceAroundTernaryOperators() {
        return FormatOptions.spaceAroundTernaryOperators.getValue(_preferences);
    }

    public boolean isSpaceAroundAssignmentOperators() {
        return FormatOptions.spaceAroundAssignmentOperators.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceTypeDeclaration() {
        return FormatOptions.spaceBeforeBraceTypeDeclaration.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceMethodDeclaration() {
        return FormatOptions.spaceBeforeBraceMethodDeclaration.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceIf() {
        return FormatOptions.spaceBeforeBraceIf.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceElse() {
        return FormatOptions.spaceBeforeBraceElse.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceFor() {
        return FormatOptions.spaceBeforeBraceFor.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceDo() {
        return FormatOptions.spaceBeforeBraceDo.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceWhile() {
        return FormatOptions.spaceBeforeBraceWhile.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceSwitch() {
        return FormatOptions.spaceBeforeBraceSwitch.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceStaticInitializer() {
        return FormatOptions.spaceBeforeBraceStaticInitializer.getValue(_preferences);
    }

    public boolean isSpaceBeforeBraceArrayInitializer() {
        return FormatOptions.spaceBeforeBraceArrayInitializer.getValue(_preferences);
    }

    public boolean isSpaceInParens() {
        return FormatOptions.spaceInParens.getValue(_preferences);
    }

    public boolean isSpaceInParensMethodDeclaration() {
        return FormatOptions.spaceInParensMethodDeclaration.getValue(_preferences);
    }

    public boolean isSpaceInParensMethodCall() {
        return FormatOptions.spaceInParensMethodCall.getValue(_preferences);
    }

    public boolean isSpaceInParensIf() {
        return FormatOptions.spaceInParensIf.getValue(_preferences);
    }

    public boolean isSpaceInParensFor() {
        return FormatOptions.spaceInParensFor.getValue(_preferences);
    }

    public boolean isSpaceInParensWhile() {
        return FormatOptions.spaceInParensWhile.getValue(_preferences);
    }

    public boolean isSpaceInParensSwitch() {
        return FormatOptions.spaceInParensSwitch.getValue(_preferences);
    }

    public boolean isSpaceInParensTypeCast() {
        return FormatOptions.spaceInParensTypeCast.getValue(_preferences);
    }

    public boolean isSpaceInParensBraces() {
        return FormatOptions.spaceInParensBraces.getValue(_preferences);
    }

    public boolean isSpaceInParensArrayInitBrackets() {
        return FormatOptions.spaceInParensArrayInitBrackets.getValue(_preferences);
    }

    public boolean isSpaceBeforeComma() {
        return FormatOptions.spaceBeforeComma.getValue(_preferences);
    }

    public boolean isSpaceAfterComma() {
        return FormatOptions.spaceAfterComma.getValue(_preferences);
    }

    public boolean isSpaceBeforeSemicolon() {
        return FormatOptions.spaceBeforeSemicolon.getValue(_preferences);
    }

    public boolean isSpaceAfterSemicolon() {
        return FormatOptions.spaceAfterSemicolon.getValue(_preferences);
    }

    public boolean isSpaceBeforeColon() {
        return FormatOptions.spaceBeforeColon.getValue(_preferences);
    }

    public boolean isSpaceAfterColon() {
        return FormatOptions.spaceAfterColon.getValue(_preferences);
    }

    public boolean isSpaceAfterTypeCast() {
        return FormatOptions.spaceAfterTypeCast.getValue(_preferences);
    }

    public static final class Factory implements FormatOptions.CodeStyleFactory {
        @Override
        public CodeStyle create(Preferences preferences) {
            return new CodeStyle(preferences);
        }
    }

}
