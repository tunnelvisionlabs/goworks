/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.project;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;

/**
 *
 * @author Sam Harwell
 */
public interface ProjectBinaryResolver {

    /**
     * Returns a list of names of <em>executable</em> binaries resulting from
     * building {@code project}.
     * <p/>
     * If the resolver does not support the given {@code project}, return
     * {@code null}.
     *
     * @param project the project
     * @return the executable binaries produced by the given {@code project}
     */
    @CheckForNull
    String[] findProjectBinaries(@NonNull Project project);
}
