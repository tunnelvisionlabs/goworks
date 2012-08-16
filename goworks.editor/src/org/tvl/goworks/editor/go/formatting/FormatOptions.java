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
import org.netbeans.api.editor.settings.SimpleValueNames;
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
public class FormatOptions {

    public static final BooleanFormatOption expandTabToSpaces = new BooleanFormatOption(SimpleValueNames.EXPAND_TABS, true);
    public static final IntFormatOption tabSize = new IntFormatOption(SimpleValueNames.TAB_SIZE, 4);
    public static final IntFormatOption spacesPerTab = new IntFormatOption(SimpleValueNames.SPACES_PER_TAB, 4);
    public static final IntFormatOption indentSize = new IntFormatOption(SimpleValueNames.INDENT_SHIFT_WIDTH, 4);
    public static final IntFormatOption rightMargin = new IntFormatOption(SimpleValueNames.TEXT_LIMIT_WIDTH, 80);

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

    public static CodeStyleFactory codeStyleFactory;

    private static final Map<String, AbstractFormatOption> knownOptions;

    static {
        knownOptions = new HashMap<String, AbstractFormatOption>();
        for (Field field : FormatOptions.class.getDeclaredFields()) {
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
        public CodeStyle create(Preferences preferences);
    }

}
