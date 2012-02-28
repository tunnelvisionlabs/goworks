/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.FieldModel;
import org.tvl.goworks.editor.go.codemodel.InterfaceModel;
import org.tvl.goworks.editor.go.codemodel.StructModel;
import org.tvl.goworks.editor.go.codemodel.TypeAliasModel;
import org.tvl.goworks.editor.go.codemodel.TypeModel;
import org.tvl.goworks.editor.go.codemodel.TypePointerModel;
import org.tvl.goworks.editor.go.codemodel.TypeReferenceModel;
import org.tvl.goworks.editor.go.codemodel.VarModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypePointerModelImpl;

/**
 *
 * @author Sam Harwell
 */
public final class SemanticAnalyzer {

    public static GoAnnotatedParseTree analyze(VersionedDocument document, ParseTree<Token> parseTree) {
        GoAnnotatedParseTree annotatedParseTree = new GoAnnotatedParseTree(parseTree);
        SemanticAnalyzerListener listener = new SemanticAnalyzerListener(document, annotatedParseTree);
        boolean background = document.getDocument() == null;
        SemanticAnalyzerParseTreeWalker walker = new SemanticAnalyzerParseTreeWalker(background);
        walker.walk(listener, parseTree);
        listener.resolveReferences();
        return annotatedParseTree;
    }

    public static Collection<? extends CodeElementModel> getSelectableMembers(CodeElementModel model) {
        return getSelectableMembers(model, "", true);
    }

    public static Collection<? extends CodeElementModel> getSelectableMembers(CodeElementModel model, String name) {
        return getSelectableMembers(model, name, true);
    }

    public static Collection<? extends CodeElementModel> getSelectableMembers(CodeElementModel model, String name, boolean includeExtendedMembers) {
        if (name == null) {
            name = "";
        }

        if (model instanceof BundledReturnTypeModel) {
            model = ((BundledReturnTypeModel)model).getReturnValues().get(0);
        }

        CodeElementModel source = model;
        if (source instanceof VarModel) {
            source = ((VarModel)source).getVarType();
        }

        if (source instanceof TypeReferenceModel) {
            Collection<? extends CodeElementModel> resolved = ((TypeReferenceModel)source).resolve();
            if (resolved.isEmpty()) {
                return resolved;
            } else if (resolved.size() == 1) {
                return getSelectableMembers(resolved.iterator().next(), name);
            } else {
                List<CodeElementModel> result = new ArrayList<CodeElementModel>();
                for (CodeElementModel i : resolved) {
                    result.addAll(getSelectableMembers(i, name));
                }

                return result;
            }
        }

        boolean usePointer = source instanceof TypeModelImpl;
        if (source instanceof TypePointerModel) {
            usePointer = false;
        } else if (source instanceof InterfaceModel) {
            usePointer = false;
        } else if (source instanceof TypeAliasModel) {
            usePointer = !(((TypeAliasModel)source).getType() instanceof InterfaceModel);
        }

        if (usePointer) {
            assert source instanceof TypeModelImpl;
            // resolve using &x to get all possibilities
            TypeModelImpl typeSource = (TypeModelImpl)source;
            source = new TypePointerModelImpl(typeSource);
        }

        Collection<? extends CodeElementModel> members = name.isEmpty() ? source.getMembers() : source.getMembers(name);
        if (!includeExtendedMembers) {
            return members;
        }

        List<CodeElementModel> allMembers = new ArrayList<CodeElementModel>();
        allMembers.addAll(members);

        if (source instanceof TypePointerModel) {
            source = ((TypePointerModel)source).getElementType();
        }

        if (source instanceof TypeAliasModel) {
            // work with the underlying type for the extended portion
            source = ((TypeAliasModel)source).getType();
        }

        List<CodeElementModel> extendedSources = new ArrayList<CodeElementModel>();
        if (source instanceof TypeReferenceModel) {
            // work with the underlying type for the extended portion
            extendedSources.addAll(((TypeReferenceModel)source).resolve());
            for (int i = 0; i < extendedSources.size(); i++) {
                CodeElementModel extendedSource = extendedSources.get(i);

                if (extendedSource instanceof TypePointerModel) {
                    extendedSource = ((TypePointerModel)extendedSource).getElementType();
                }

                if (extendedSource instanceof TypeAliasModel) {
                    // work with the underlying type for the extended portion
                    extendedSource = ((TypeAliasModel)extendedSource).getType();
                }

                extendedSources.set(i, extendedSource);
            }
        } else {
            extendedSources.add(source);
        }

        for (CodeElementModel extendedSource : extendedSources) {
            if (extendedSource instanceof InterfaceModel) {
                Set<TypeModel> visitedInterfaces = new HashSet<TypeModel>();
                Deque<TypeModel> remainingInterfaces = new ArrayDeque<TypeModel>(((InterfaceModel)extendedSource).getImplementedInterfaces());
                while (!remainingInterfaces.isEmpty()) {
                    TypeModel current = remainingInterfaces.pop();
                    if (current instanceof TypeReferenceModel) {
                        remainingInterfaces.addAll(((TypeReferenceModel)current).resolve());
                        continue;
                    } else if (current instanceof TypeAliasModel) {
                        remainingInterfaces.add(((TypeAliasModel)current).getType());
                        continue;
                    }

                    if (!visitedInterfaces.add(current)) {
                        continue;
                    }

                    if (current instanceof InterfaceModel) {
                        InterfaceModel currentInterface = (InterfaceModel)current;
                        remainingInterfaces.addAll(currentInterface.getImplementedInterfaces());
                    }

                    allMembers.addAll(getSelectableMembers(current, name, false));
                }
            } else if (extendedSource instanceof StructModel) {
                Set<TypeModel> visitedStructures = new HashSet<TypeModel>();
                Deque<TypeModel> remainingStructures = new ArrayDeque<TypeModel>(getAnonymousFieldTypes((StructModel)extendedSource));
                while (!remainingStructures.isEmpty()) {
                    TypeModel current = remainingStructures.pop();
                    if (current instanceof TypeReferenceModel) {
                        remainingStructures.addAll(((TypeReferenceModel)current).resolve());
                        continue;
                    } else if (current instanceof TypeAliasModel) {
                        remainingStructures.add(((TypeAliasModel)current).getType());
                        // Don't continue here or it won't pick up the method set
                        //continue;
                    }

                    if (!visitedStructures.add(current)) {
                        continue;
                    }

                    if (current instanceof StructModel) {
                        StructModel currentInterface = (StructModel)current;
                        remainingStructures.addAll(getAnonymousFieldTypes(currentInterface));
                    }

                    allMembers.addAll(getSelectableMembers(current, name, false));
                }
            }
        }

        return allMembers;
    }

    public static Collection<? extends TypeModel> getAnonymousFieldTypes(StructModel struct) {
        Collection<TypeModel> types = new ArrayList<TypeModel>();
        for (FieldModel field : struct.getFields()) {
            if (field.isAnonymous()) {
                types.add(field.getVarType());
            }
        }

        return types;
    }

    private SemanticAnalyzer() {
    }

}
