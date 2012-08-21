/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import org.antlr.netbeans.editor.formatting.AbstractFormatOption;
import org.antlr.netbeans.editor.formatting.BooleanFormatOption;
import org.antlr.netbeans.editor.formatting.EnumFormatOption;
import org.antlr.netbeans.editor.formatting.FormatOptions;
import org.antlr.netbeans.editor.formatting.IntFormatOption;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "SAMPLE_Default=",
    "AD_Preview=",
    "AN_Preview=",
})
public class GoFormatOptions extends FormatOptions {

    public static final BooleanFormatOption alignMultilineArrayInitializer = new BooleanFormatOption("alignMultilineArrayInitializer", false);
    public static final BooleanFormatOption alignMultilineAssignment = new BooleanFormatOption("alignMultilineAssignment", false);
    public static final BooleanFormatOption alignMultilineBinaryOperators = new BooleanFormatOption("alignMultilineBinaryOperators", false);
    public static final BooleanFormatOption alignMultilineCallArguments = new BooleanFormatOption("alignMultilineCallArguments", false);
    public static final BooleanFormatOption alignMultilineCompositeLiteral = new BooleanFormatOption("alignMultilineCompositeLiteral", false);
    public static final BooleanFormatOption alignMultilineFor = new BooleanFormatOption("alignMultilineFor", false);
    public static final BooleanFormatOption alignMultilineMethodParameters = new BooleanFormatOption("alignMultilineMethodParameters", false);
    public static final BooleanFormatOption alignMultilineParenthesized = new BooleanFormatOption("alignMultilineParenthesized", false);
    public static final BooleanFormatOption alignMultilineReturnParameters = new BooleanFormatOption("alignMultilineReturnParameters", false);
    public static final BooleanFormatOption alignMultilineTernaryOperators = new BooleanFormatOption("alignMultilineTernaryOperators", false);

    public static final IntFormatOption blankLinesBeforePackage = new IntFormatOption("blankLinesBeforePackage", 0);
    public static final IntFormatOption blankLinesAfterPackage = new IntFormatOption("blankLinesAfterPackage", 1);
    public static final IntFormatOption blankLinesBeforeImports = new IntFormatOption("blankLinesBeforeImports", 1);
    public static final IntFormatOption blankLinesAfterImports = new IntFormatOption("blankLinesAfterImports", 1);
    public static final IntFormatOption blankLinesBeforeType = new IntFormatOption("blankLinesBeforeType", 1);
    public static final IntFormatOption blankLinesAfterType = new IntFormatOption("blankLinesAfterType", 0);
    public static final IntFormatOption blankLinesBeforeVar = new IntFormatOption("blankLinesBeforeVar", 0);
    public static final IntFormatOption blankLinesAfterVar = new IntFormatOption("blankLinesAfterVar", 0);
    public static final IntFormatOption blankLinesBeforeFunction = new IntFormatOption("blankLinesBeforeFunction", 1);
    public static final IntFormatOption blankLinesAfterFunction = new IntFormatOption("blankLinesAfterFunction", 0);

    public static final BooleanFormatOption spaceBeforeWhile = new BooleanFormatOption("spaceBeforeWhile", true);
    public static final BooleanFormatOption spaceBeforeElse = new BooleanFormatOption("spaceBeforeElse", true);

    public static final BooleanFormatOption spaceBeforeParensMethodDeclaration = new BooleanFormatOption("spaceBeforeParensMethodDeclaration", false);
    public static final BooleanFormatOption spaceBeforeParensMethodCall = new BooleanFormatOption("spaceBeforeParensMethodCall", false);
    public static final BooleanFormatOption spaceBeforeParensIf = new BooleanFormatOption("spaceBeforeParensIf", true);
    public static final BooleanFormatOption spaceBeforeParensFor = new BooleanFormatOption("spaceBeforeParensFor", true);
    public static final BooleanFormatOption spaceBeforeParensWhile = new BooleanFormatOption("spaceBeforeParensWhile", true);
    public static final BooleanFormatOption spaceBeforeParensSwitch = new BooleanFormatOption("spaceBeforeParensSwitch", true);

    public static final BooleanFormatOption spaceAroundUnaryOperators = new BooleanFormatOption("spaceAroundUnaryOperators", false);
    public static final BooleanFormatOption spaceAroundBinaryOperators = new BooleanFormatOption("spaceAroundBinaryOperators", true);
    public static final BooleanFormatOption spaceAroundTernaryOperators = new BooleanFormatOption("spaceAroundTernaryOperators", true);
    public static final BooleanFormatOption spaceAroundAssignmentOperators = new BooleanFormatOption("spaceAroundAssignmentOperators", true);

    public static final BooleanFormatOption spaceBeforeBraceTypeDeclaration = new BooleanFormatOption("spaceBeforeBraceTypeDeclaration", true);
    public static final BooleanFormatOption spaceBeforeBraceMethodDeclaration = new BooleanFormatOption("spaceBeforeBraceMethodDeclaration", true);
    public static final BooleanFormatOption spaceBeforeBraceIf = new BooleanFormatOption("spaceBeforeBraceIf", true);
    public static final BooleanFormatOption spaceBeforeBraceElse = new BooleanFormatOption("spaceBeforeBraceElse", true);
    public static final BooleanFormatOption spaceBeforeBraceFor = new BooleanFormatOption("spaceBeforeBraceFor", true);
    public static final BooleanFormatOption spaceBeforeBraceDo = new BooleanFormatOption("spaceBeforeBraceDo", true);
    public static final BooleanFormatOption spaceBeforeBraceWhile = new BooleanFormatOption("spaceBeforeBraceWhile", true);
    public static final BooleanFormatOption spaceBeforeBraceSwitch = new BooleanFormatOption("spaceBeforeBraceSwitch", true);
    public static final BooleanFormatOption spaceBeforeBraceStaticInitializer = new BooleanFormatOption("spaceBeforeBraceStaticInitializer", true);
    public static final BooleanFormatOption spaceBeforeBraceArrayInitializer = new BooleanFormatOption("spaceBeforeBraceArrayInitializer", false);

    public static final BooleanFormatOption spaceInParens = new BooleanFormatOption("spaceInParens", false);
    public static final BooleanFormatOption spaceInParensMethodDeclaration = new BooleanFormatOption("spaceInParensMethodDeclaration", false);
    public static final BooleanFormatOption spaceInParensMethodCall = new BooleanFormatOption("spaceInParensMethodCall", false);
    public static final BooleanFormatOption spaceInParensIf = new BooleanFormatOption("spaceInParensIf", false);
    public static final BooleanFormatOption spaceInParensFor = new BooleanFormatOption("spaceInParensFor", false);
    public static final BooleanFormatOption spaceInParensWhile = new BooleanFormatOption("spaceInParensWhile", false);
    public static final BooleanFormatOption spaceInParensSwitch = new BooleanFormatOption("spaceInParensSwitch", false);
    public static final BooleanFormatOption spaceInParensTypeCast = new BooleanFormatOption("spaceInParensTypeCast", false);
    public static final BooleanFormatOption spaceInParensBraces = new BooleanFormatOption("spaceInParensBraces", false);
    public static final BooleanFormatOption spaceInParensArrayInitBrackets = new BooleanFormatOption("spaceInParensArrayInitBrackets", false);

    public static final BooleanFormatOption spaceBeforeComma = new BooleanFormatOption("spaceBeforeComma", false);
    public static final BooleanFormatOption spaceAfterComma = new BooleanFormatOption("spaceAfterComma", true);
    public static final BooleanFormatOption spaceBeforeSemicolon = new BooleanFormatOption("spaceBeforeSemicolon", false);
    public static final BooleanFormatOption spaceAfterSemicolon = new BooleanFormatOption("spaceAfterSemicolon", true);
    public static final BooleanFormatOption spaceBeforeColon = new BooleanFormatOption("spaceBeforeColon", true);
    public static final BooleanFormatOption spaceAfterColon = new BooleanFormatOption("spaceAfterColon", true);
    public static final BooleanFormatOption spaceAfterTypeCast = new BooleanFormatOption("spaceAfterTypeCast", false);

    public static final BooleanFormatOption enableCommentsFormatting = new BooleanFormatOption("enableCommentsFormatting", true);
    public static final BooleanFormatOption formatBlockComments = new BooleanFormatOption("formatBlockComments", false);
    public static final BooleanFormatOption addLeadingStarInComments = new BooleanFormatOption("addLeadingStarInComments", true);
    public static final BooleanFormatOption wrapTextInComments = new BooleanFormatOption("wrapTextInComments", true);
    public static final BooleanFormatOption wrapOneLineComments = new BooleanFormatOption("wrapOneLineComments", true);
    public static final BooleanFormatOption preserveNewLinesInComments = new BooleanFormatOption("preserveNewLinesInComments", false);

    public static final EnumFormatOption<WrapStyle> wrapArrayInitializer = new EnumFormatOption<WrapStyle>("wrapArrayInitializer", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapAssignmentOperators = new EnumFormatOption<WrapStyle>("wrapAssignmentOperators", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapBinaryOperators = new EnumFormatOption<WrapStyle>("wrapBinaryOperators", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapChainedMethodCalls = new EnumFormatOption<WrapStyle>("wrapChainedMethodCalls", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapDoWhileStatement = new EnumFormatOption<WrapStyle>("wrapDoWhileStatement", WrapStyle.class, WrapStyle.ALWAYS);
    public static final EnumFormatOption<WrapStyle> wrapEnumConstants = new EnumFormatOption<WrapStyle>("wrapEnumConstants", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapFor = new EnumFormatOption<WrapStyle>("wrapFor", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapForStatement = new EnumFormatOption<WrapStyle>("wrapForStatement", WrapStyle.class, WrapStyle.ALWAYS);
    public static final EnumFormatOption<WrapStyle> wrapIfStatement = new EnumFormatOption<WrapStyle>("wrapIfStatement", WrapStyle.class, WrapStyle.ALWAYS);
    public static final EnumFormatOption<WrapStyle> wrapMethodCallArguments = new EnumFormatOption<WrapStyle>("wrapMethodCallArguments", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapMethodParameters = new EnumFormatOption<WrapStyle>("wrapMethodParameters", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapTernaryOperators = new EnumFormatOption<WrapStyle>("wrapTernaryOperators", WrapStyle.class, WrapStyle.NEVER);
    public static final EnumFormatOption<WrapStyle> wrapWhileStatement = new EnumFormatOption<WrapStyle>("wrapWhileStatement", WrapStyle.class, WrapStyle.ALWAYS);

    public static CodeStyleFactory codeStyleFactory;

    private static final Map<String, AbstractFormatOption> knownOptions;

    static {
        knownOptions = new HashMap<String, AbstractFormatOption>();
        for (Field field : GoFormatOptions.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())
                && Modifier.isFinal(field.getModifiers())
                && Modifier.isPublic(field.getModifiers())
                && AbstractFormatOption.class.isAssignableFrom(field.getType()))
            {
                try {
                    AbstractFormatOption value = (AbstractFormatOption)field.get(null);
                    if (value != null) {
                        knownOptions.put(value.getName(), value);
                    }
                } catch (IllegalArgumentException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IllegalAccessException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    public static AbstractFormatOption getOption(String name) {
        return knownOptions.get(name);
    }

    public static interface CodeStyleFactory {
        public GoCodeStyle create(Preferences preferences);
    }

}
