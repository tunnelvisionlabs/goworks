/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.quicksearch;

import java.util.HashSet;
import java.util.Set;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelProjectCache;
import org.tvl.goworks.project.GoProject;

public class GoToTypeSearchProvider implements SearchProvider {

    @Override
    public void evaluate(SearchRequest request, SearchResponse response) {
        Set<GoProject> projects = new HashSet<GoProject>();

        for (Project project : OpenProjects.getDefault().getOpenProjects()) {
            if (project instanceof GoProject) {
                projects.add((GoProject)project);
            }
        }

        while (true) {
            boolean updated = false;
            for (GoProject project : projects.toArray(new GoProject[projects.size()])) {
                updated |= projects.addAll(project.getLibraryProjects());
            }

            if (!updated) {
                break;
            }
        }

        for (GoProject project : projects) {
            CodeModelProjectCache projectCache = CodeModelCacheImpl.getInstance().getProjectCache(project, false);
            if (projectCache == null) {
                continue;
            }

            for (PackageModel packageModel : projectCache.getPackages()) {
                for (TypeModel typeModel : packageModel.getTypes()) {
                    if (!examineModel(request, response, typeModel)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean examineModel(SearchRequest request, SearchResponse response, TypeModel model) {
        if (!model.getName().toLowerCase().contains(request.getText().toLowerCase())) {
            return true;
        }

        String displayName = QuickSearchFormatter.INSTANCE.format(model);
        if (displayName == null || displayName.isEmpty()) {
            return true;
        }

        return response.addResult(new Handler(model), displayName);
    }

    private static final class Handler implements Runnable {
        private final CodeElementModel model;

        public Handler(CodeElementModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            model.navigateTo();
        }

    }
}
