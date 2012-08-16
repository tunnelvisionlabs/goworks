/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "LBL_WrapStyle_ALWAYS=Always",
    "LBL_WrapStyle_IF_LONG=If Long",
    "LBL_WrapStyle_NEVER=Never",
})
public enum WrapStyle {
    ALWAYS,
    IF_LONG,
    NEVER,
}
