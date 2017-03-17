/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Lookup;
import org.openide.util.Parameters;
import org.openide.util.lookup.ServiceProvider;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.CodeModelCache;
import org.tvl.goworks.editor.go.codemodel.ImportDeclarationModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.project.GoProject;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=CodeModelCache.class)
public class CodeModelCacheImpl implements CodeModelCache {
    private static CodeModelCacheImpl instance;

    private CodeModelProjectCache defaultProjectCache;
    private final Map<GoProject, CodeModelProjectCache> projectCaches =
        new WeakHashMap<>();

    @Override
    @NonNull
    public Collection<? extends PackageModel> getPackages(GoProject project) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return Collections.emptyList();
        }

        Collection<PackageModelImpl> packages = cache.getPackages();
        if (packages instanceof List) {
            return Collections.unmodifiableList((List<PackageModelImpl>)packages);
        }

        return Collections.unmodifiableCollection(cache.getPackages());
    }

    @NonNull
    @Override
    public Collection<? extends PackageModelImpl> getPackages(GoProject project, String path) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return Collections.emptyList();
        }

        PackageModelImpl unique = cache.getUniquePackage(path);
        if (unique == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(unique);
    }

    @CheckForNull
    public PackageModelImpl getUniquePackage(GoProject project, String path) {
        CodeModelProjectCache cache = getProjectCache(project, false);
        if (cache == null) {
            return null;
        }

        return cache.getUniquePackage(path);
    }

    @NonNull
    public Collection<? extends PackageModelImpl> resolvePackages(ImportDeclarationModel importModel) {
        return resolvePackages(importModel.getPackage().getProject(), importModel.getPackage().getPackagePath(), importModel.getPath());
    }

    @NonNull
    public Collection<? extends PackageModelImpl> resolvePackages(GoProject project, String packagePath, String path) {
        Collection<? extends PackageModelImpl> userPackages = resolveUserPackages(project, packagePath, path);
        if (!userPackages.isEmpty()) {
            return userPackages;
        }

        return resolveLibraryPackages(project, path);
    }

    @NonNull
    private Collection<? extends PackageModelImpl> resolveUserPackages(GoProject project, String packagePath, String path) {
        CodeModelProjectCache projectCache = getProjectCache(project, false);
        if (projectCache == null) {
            return Collections.emptyList();
        }

        // Check vendor folders headed to the root
        for (String vendorBase = packagePath; !vendorBase.isEmpty(); vendorBase = vendorBase.substring(0, vendorBase.lastIndexOf('/', vendorBase.length() - 2) + 1)) {
            String vendorPackage =
                vendorBase + (vendorBase.endsWith("/") ? "" : "/") + "vendor/" + path;
            PackageModelImpl vendor = projectCache.getUniquePackage(vendorPackage);
            if (vendor != null) {
                return Collections.singletonList(vendor);
            }
        }

        PackageModelImpl unique = projectCache.getUniquePackage(path);
        if (unique == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(unique);
    }

    @NonNull
    private Collection<? extends PackageModelImpl> resolveLibraryPackages(GoProject project, String path) {
        if (project.isStandardLibrary()) {
            return Collections.emptyList();
        }

        List<PackageModelImpl> result = new ArrayList<>();
        for (GoProject libraryProject : project.getLibraryProjects()) {
            result.addAll(getPackages(libraryProject, path));
        }

        return result;
    }

    @CheckForNull
    public CodeModelProjectCache getProjectCache(@NullAllowed GoProject project, boolean create) {
        synchronized (projectCaches) {
            CodeModelProjectCache cache = project != null ? projectCaches.get(project) : defaultProjectCache;
            if (cache == null && create) {
                cache = new CodeModelProjectCache(project);
                if (project == null) {
                    defaultProjectCache = cache;
                } else {
                    projectCaches.put(project, cache);
                }
            }

            return cache;
        }
    }

    @NonNull
    public final CodeModelProjectCache getOrCreateProjectCache(@NullAllowed GoProject project) {
        CodeModelProjectCache cache = getProjectCache(project, true);
        if (cache == null) {
            throw new IllegalStateException("getProjectCache should not return null when 'create' is true.");
        }

        return cache;
    }

    public void updateFile(@NonNull FileModelImpl fileModel) {
        assert fileModel.isFrozen();
        GoProject project = fileModel.getProject();
        CodeModelProjectCache projectCache = getOrCreateProjectCache(project);
        projectCache.updateFile(fileModel);
    }

    public static synchronized CodeModelCacheImpl getInstance() {
        if (instance == null) {
            instance = (CodeModelCacheImpl)Lookup.getDefault().lookup(CodeModelCache.class);
        }

        return instance;
    }

    public static <T extends CodeElementModel> Collection<T> findElementsByName(Collection<? extends T> elements, @NonNull String name) {
        Parameters.notNull("name", name);

        List<T> result = new ArrayList<>();
        for (T element : elements) {
            if (name.equals(element.getName())) {
                result.add(element);
            }
        }

        return result;
    }

}
