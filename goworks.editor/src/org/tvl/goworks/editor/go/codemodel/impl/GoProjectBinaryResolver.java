/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.project.Project;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.project.GoProject;
import org.tvl.goworks.project.ProjectBinaryResolver;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(service = ProjectBinaryResolver.class, mimeType = GoEditorKit.GO_MIME_TYPE)
public class GoProjectBinaryResolver implements ProjectBinaryResolver {

    @Override
    public String[] findProjectBinaries(Project project) {
        if (!(project instanceof GoProject)) {
            return null;
        }

        CodeModelProjectCache cache = CodeModelCacheImpl.getInstance().getProjectCache((GoProject)project, false);
        if (cache == null) {
            return new String[0];
        }

        // looking for "main" package containing "func main()"
        Set<String> binaries = new HashSet<>();
        for (PackageModelImpl packageModel : cache.getPackages()) {
            Collection<FunctionModelImpl> functions = packageModel.getFunctions("main");
            if (functions.isEmpty()) {
                continue;
            }

            for (FunctionModelImpl functionModel : functions) {
                if (functionModel.isMethod()) {
                    continue;
                }

                if (!functionModel.getParameters().isEmpty() || !functionModel.getReturnValues().isEmpty()) {
                    continue;
                }

                boolean mainPackage = "main".equals(packageModel.getName());
                if (!mainPackage) {
                    FileModelImpl fileModel = functionModel.getFile();
                    if (fileModel == null) {
                        continue;
                    }

                    Collection<PackageDeclarationModelImpl> packageDeclarations = fileModel.getPackageDeclarations();
                    if (packageDeclarations.isEmpty()) {
                        continue;
                    }

                    mainPackage = true;
                    for (PackageDeclarationModelImpl packageDeclaration : packageDeclarations) {
                        mainPackage &= "main".equals(packageDeclaration.getName());
                    }
                }

                if (mainPackage) {
                    binaries.add(packageModel.getName());
                }
            }
        }

        return binaries.toArray(new String[binaries.size()]);
    }
}
