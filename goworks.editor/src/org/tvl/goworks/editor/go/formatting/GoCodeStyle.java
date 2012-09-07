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
import org.antlr.netbeans.editor.formatting.CodeStyle;
import org.netbeans.modules.editor.indent.spi.CodeStylePreferences;
import org.openide.filesystems.FileObject;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
public final class GoCodeStyle extends CodeStyle {

    static {
        GoFormatOptions.codeStyleFactory = new Factory();
    }

    private GoCodeStyle(Preferences preferences) {
        super(preferences);
    }

    public synchronized static GoCodeStyle getDefault(Document document) {
        Preferences preferences = CodeStylePreferences.get(document, GoEditorKit.GO_MIME_TYPE).getPreferences();
        return new GoCodeStyle(preferences);
    }

    public synchronized static GoCodeStyle getDefault(FileObject file) {
        Preferences preferences = CodeStylePreferences.get(file, GoEditorKit.GO_MIME_TYPE).getPreferences();
        return new GoCodeStyle(preferences);
    }

    public boolean isAlignMultilineArrayInitializer() {
        return GoFormatOptions.alignMultilineArrayInitializer.getValue(getPreferences());
    }

    public boolean isAlignMultilineAssignment() {
        return GoFormatOptions.alignMultilineAssignment.getValue(getPreferences());
    }

    public boolean isAlignMultilineBinaryOperators() {
        return GoFormatOptions.alignMultilineBinaryOperators.getValue(getPreferences());
    }

    public boolean isAlignMultilineCallArguments() {
        return GoFormatOptions.alignMultilineCallArguments.getValue(getPreferences());
    }

    public boolean isAlignMultilineCompositeLiteral() {
        return GoFormatOptions.alignMultilineCompositeLiteral.getValue(getPreferences());
    }

    public boolean isAlignMultilineFor() {
        return GoFormatOptions.alignMultilineFor.getValue(getPreferences());
    }

    public boolean isAlignMultilineMethodParameters() {
        return GoFormatOptions.alignMultilineMethodParameters.getValue(getPreferences());
    }

    public boolean isAlignMultilineParenthesized() {
        return GoFormatOptions.alignMultilineParenthesized.getValue(getPreferences());
    }

    public boolean isAlignMultilineReturnParameters() {
        return GoFormatOptions.alignMultilineReturnParameters.getValue(getPreferences());
    }

    public boolean isAlignMultilineTernaryOperators() {
        return GoFormatOptions.alignMultilineTernaryOperators.getValue(getPreferences());
    }

    public int getBlankLinesBeforePackage() {
        return GoFormatOptions.blankLinesBeforePackage.getValue(getPreferences());
    }

    public int getBlankLinesAfterPackage() {
        return GoFormatOptions.blankLinesAfterPackage.getValue(getPreferences());
    }

    public int getBlankLinesBeforeImports() {
        return GoFormatOptions.blankLinesBeforeImports.getValue(getPreferences());
    }

    public int getBlankLinesAfterImports() {
        return GoFormatOptions.blankLinesAfterImports.getValue(getPreferences());
    }

    public int getBlankLinesBeforeType() {
        return GoFormatOptions.blankLinesBeforeType.getValue(getPreferences());
    }

    public int getBlankLinesAfterType() {
        return GoFormatOptions.blankLinesAfterType.getValue(getPreferences());
    }

    public int getBlankLinesBeforeVar() {
        return GoFormatOptions.blankLinesBeforeVar.getValue(getPreferences());
    }

    public int getBlankLinesAfterVar() {
        return GoFormatOptions.blankLinesAfterVar.getValue(getPreferences());
    }

    public int getBlankLinesBeforeFunction() {
        return GoFormatOptions.blankLinesBeforeFunction.getValue(getPreferences());
    }

    public int getBlankLinesAfterFunction() {
        return GoFormatOptions.blankLinesAfterFunction.getValue(getPreferences());
    }

    public boolean isSpaceBeforeWhile() {
        return GoFormatOptions.spaceBeforeWhile.getValue(getPreferences());
    }

    public boolean isSpaceBeforeElse() {
        return GoFormatOptions.spaceBeforeElse.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensMethodDeclaration() {
        return GoFormatOptions.spaceBeforeParensMethodDeclaration.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensMethodCall() {
        return GoFormatOptions.spaceBeforeParensMethodCall.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensIf() {
        return GoFormatOptions.spaceBeforeParensIf.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensFor() {
        return GoFormatOptions.spaceBeforeParensFor.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensWhile() {
        return GoFormatOptions.spaceBeforeParensWhile.getValue(getPreferences());
    }

    public boolean isSpaceBeforeParensSwitch() {
        return GoFormatOptions.spaceBeforeParensSwitch.getValue(getPreferences());
    }

    public boolean isSpaceAroundUnaryOperators() {
        return GoFormatOptions.spaceAroundUnaryOperators.getValue(getPreferences());
    }

    public boolean isSpaceAroundBinaryOperators() {
        return GoFormatOptions.spaceAroundBinaryOperators.getValue(getPreferences());
    }

    public boolean isSpaceAroundTernaryOperators() {
        return GoFormatOptions.spaceAroundTernaryOperators.getValue(getPreferences());
    }

    public boolean isSpaceAroundAssignmentOperators() {
        return GoFormatOptions.spaceAroundAssignmentOperators.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceTypeDeclaration() {
        return GoFormatOptions.spaceBeforeBraceTypeDeclaration.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceMethodDeclaration() {
        return GoFormatOptions.spaceBeforeBraceMethodDeclaration.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceIf() {
        return GoFormatOptions.spaceBeforeBraceIf.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceElse() {
        return GoFormatOptions.spaceBeforeBraceElse.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceFor() {
        return GoFormatOptions.spaceBeforeBraceFor.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceDo() {
        return GoFormatOptions.spaceBeforeBraceDo.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceWhile() {
        return GoFormatOptions.spaceBeforeBraceWhile.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceSwitch() {
        return GoFormatOptions.spaceBeforeBraceSwitch.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceStaticInitializer() {
        return GoFormatOptions.spaceBeforeBraceStaticInitializer.getValue(getPreferences());
    }

    public boolean isSpaceBeforeBraceArrayInitializer() {
        return GoFormatOptions.spaceBeforeBraceArrayInitializer.getValue(getPreferences());
    }

    public boolean isSpaceInParens() {
        return GoFormatOptions.spaceInParens.getValue(getPreferences());
    }

    public boolean isSpaceInParensMethodDeclaration() {
        return GoFormatOptions.spaceInParensMethodDeclaration.getValue(getPreferences());
    }

    public boolean isSpaceInParensMethodCall() {
        return GoFormatOptions.spaceInParensMethodCall.getValue(getPreferences());
    }

    public boolean isSpaceInParensIf() {
        return GoFormatOptions.spaceInParensIf.getValue(getPreferences());
    }

    public boolean isSpaceInParensFor() {
        return GoFormatOptions.spaceInParensFor.getValue(getPreferences());
    }

    public boolean isSpaceInParensWhile() {
        return GoFormatOptions.spaceInParensWhile.getValue(getPreferences());
    }

    public boolean isSpaceInParensSwitch() {
        return GoFormatOptions.spaceInParensSwitch.getValue(getPreferences());
    }

    public boolean isSpaceInParensTypeCast() {
        return GoFormatOptions.spaceInParensTypeCast.getValue(getPreferences());
    }

    public boolean isSpaceInParensBraces() {
        return GoFormatOptions.spaceInParensBraces.getValue(getPreferences());
    }

    public boolean isSpaceInParensArrayInitBrackets() {
        return GoFormatOptions.spaceInParensArrayInitBrackets.getValue(getPreferences());
    }

    public boolean isSpaceBeforeComma() {
        return GoFormatOptions.spaceBeforeComma.getValue(getPreferences());
    }

    public boolean isSpaceAfterComma() {
        return GoFormatOptions.spaceAfterComma.getValue(getPreferences());
    }

    public boolean isSpaceBeforeSemicolon() {
        return GoFormatOptions.spaceBeforeSemicolon.getValue(getPreferences());
    }

    public boolean isSpaceAfterSemicolon() {
        return GoFormatOptions.spaceAfterSemicolon.getValue(getPreferences());
    }

    public boolean isSpaceBeforeColon() {
        return GoFormatOptions.spaceBeforeColon.getValue(getPreferences());
    }

    public boolean isSpaceAfterColon() {
        return GoFormatOptions.spaceAfterColon.getValue(getPreferences());
    }

    public boolean isSpaceAfterTypeCast() {
        return GoFormatOptions.spaceAfterTypeCast.getValue(getPreferences());
    }

    public boolean isEnableCommentsFormatting() {
        return GoFormatOptions.enableCommentsFormatting.getValue(getPreferences());
    }

    public boolean isFormatBlockComments() {
        return GoFormatOptions.formatBlockComments.getValue(getPreferences());
    }

    public boolean isAddLeadingStarInComments() {
        return GoFormatOptions.addLeadingStarInComments.getValue(getPreferences());
    }

    public boolean isWrapTextInComments() {
        return GoFormatOptions.wrapTextInComments.getValue(getPreferences());
    }

    public boolean isWrapOneLineComments() {
        return GoFormatOptions.wrapOneLineComments.getValue(getPreferences());
    }

    public boolean isPreserveNewLinesInComments() {
        return GoFormatOptions.preserveNewLinesInComments.getValue(getPreferences());
    }

    public WrapStyle getWrapArrayInitializer() {
        return GoFormatOptions.wrapArrayInitializer.getValue(getPreferences());
    }

    public WrapStyle getWrapAssignmentOperators() {
        return GoFormatOptions.wrapAssignmentOperators.getValue(getPreferences());
    }

    public WrapStyle getWrapBinaryOperators() {
        return GoFormatOptions.wrapBinaryOperators.getValue(getPreferences());
    }

    public WrapStyle getWrapChainedMethodCalls() {
        return GoFormatOptions.wrapChainedMethodCalls.getValue(getPreferences());
    }

    public WrapStyle getWrapDoWhileStatement() {
        return GoFormatOptions.wrapDoWhileStatement.getValue(getPreferences());
    }

    public WrapStyle getWrapEnumConstants() {
        return GoFormatOptions.wrapEnumConstants.getValue(getPreferences());
    }

    public WrapStyle getWrapFor() {
        return GoFormatOptions.wrapFor.getValue(getPreferences());
    }

    public WrapStyle getWrapForStatement() {
        return GoFormatOptions.wrapForStatement.getValue(getPreferences());
    }

    public WrapStyle getWrapIfStatement() {
        return GoFormatOptions.wrapIfStatement.getValue(getPreferences());
    }

    public WrapStyle getWrapMethodCallArguments() {
        return GoFormatOptions.wrapMethodCallArguments.getValue(getPreferences());
    }

    public WrapStyle getWrapMethodParameters() {
        return GoFormatOptions.wrapMethodParameters.getValue(getPreferences());
    }

    public WrapStyle getWrapTernaryOperators() {
        return GoFormatOptions.wrapTernaryOperators.getValue(getPreferences());
    }

    public WrapStyle getWrapWhileStatement() {
        return GoFormatOptions.wrapWhileStatement.getValue(getPreferences());
    }

    public static final class Factory implements GoFormatOptions.CodeStyleFactory {
        @Override
        public GoCodeStyle create(Preferences preferences) {
            return new GoCodeStyle(preferences);
        }
    }

}
