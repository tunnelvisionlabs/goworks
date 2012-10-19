/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import java.awt.Image;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.queries.VisibilityQuery;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 * Provides display name and icon utilities for
 * {@link PackageViewChildren.PackageNode} and {@link PackageListView.PackageItem}.
 * @author Jesse Glick
 */
@NbBundle.Messages({
    "LBL_DefaultPackage=<default package>",
    "# {0} - full package name",
    "LBL_package=Go Source Package ({0})",
})
public final class PackageDisplayUtils {

    private PackageDisplayUtils() {}

    @StaticResource
    public static final String PACKAGE = "org/tvl/goworks/project/ui/resources/package.gif";

    @StaticResource
    private static final String PACKAGE_EMPTY = "org/tvl/goworks/project/ui/resources/packageEmpty.gif";

    /**
     * Find the proper display label for a package.
     * @param pkg the actual folder
     * @param pkgname the dot-separated package name (<code>""</code> for default package)
     * @return an appropriate display label for it
     */
    public static String getDisplayLabel(String pkgname) {
        return computePackageName(pkgname);
    }

    /**
     * Find the proper tool tip for a package.
     * May have more info than the display label.
     * @param pkg the actual folder
     * @param pkgname the dot-separated package name (<code>""</code> for default package)
     * @return an appropriate display label for it
     */
    public static String getToolTip(FileObject pkg, String pkgname) {
        String pkglabel = computePackageName(pkgname);
        return Bundle.LBL_package(pkglabel);
    }

    /**
     * Get package name.
     * Handles default package specially.
     */
    private static String computePackageName(String pkgname) {
        if (pkgname.length() == 0) {
            return Bundle.LBL_DefaultPackage(); // NOI18N
        } else {
            return pkgname;
        }
    }

    /** Performance optimization if the the isEmpty status is already known.
     *
     */
    public static Image getIcon(FileObject pkg, boolean empty) {
        if ( empty ) {
            return ImageUtilities.loadImage(PACKAGE_EMPTY);
        } else {
            return ImageUtilities.loadImage(PACKAGE);
        }
    }

    /**
     * Check whether a package is empty (devoid of files except for subpackages).
     */
    public static boolean isEmpty( FileObject fo ) {
        return isEmpty (fo, true );
    }

    /**
     * Check whether a package is empty (devoid of files except for subpackages).
     * @param recurse specifies whether to check if subpackages are empty too.
     */
    public static boolean isEmpty( FileObject fo, boolean recurse ) {
        FileObject[] kids = fo.getChildren();
        for( int i = 0; i < kids.length; i++ ) {
            // XXX consider using group.contains() here
            if ( !kids[i].isFolder() && VisibilityQuery.getDefault().isVisible( kids[i] ) ) {
                return false;
            }
            else if (recurse && VisibilityQuery.getDefault().isVisible( kids[i] ) && !isEmpty(kids[i])) {
                    return false;
            }
        }
        return true;
    }

}
