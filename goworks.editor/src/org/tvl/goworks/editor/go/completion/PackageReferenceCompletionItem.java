/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.completion;

import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.ImageUtilities;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class PackageReferenceCompletionItem extends GoCompletionItem {

    private static ImageIcon ICON;
    private final String packageNameOrAlias;
    private String leftText;

    public PackageReferenceCompletionItem(@NonNull String packageNameOrAlias) {
        Parameters.notNull("packageNameOrAlias", packageNameOrAlias);
        this.packageNameOrAlias = packageNameOrAlias;
    }

    @Override
    public int getSortPriority() {
        return PACKAGE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return packageNameOrAlias;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return packageNameOrAlias;
    }

    @Override
    protected ImageIcon getIcon() {
        if (ICON == null) {
            ICON = new ImageIcon(ImageUtilities.loadImage("org/tvl/goworks/editor/go/resources/package.png"));
        }

        return ICON;
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(PACKAGE_COLOR);
            builder.append(packageNameOrAlias);
            builder.append(COLOR_END);
            leftText = builder.toString();
        }
        return leftText;
    }

}
