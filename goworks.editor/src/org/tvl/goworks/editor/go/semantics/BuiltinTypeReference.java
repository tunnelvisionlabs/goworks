/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.Collection;
import java.util.Map;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;

/**
 *
 * @author Sam Harwell
 */
public class BuiltinTypeReference extends CodeElementReference {

    public static final BuiltinTypeReference BOOL = new BuiltinTypeReference("bool");
    public static final BuiltinTypeReference BYTE = new BuiltinTypeReference("byte");
    public static final BuiltinTypeReference COMPLEX64 = new BuiltinTypeReference("complex64");
    public static final BuiltinTypeReference COMPLEX128 = new BuiltinTypeReference("complex128");
    public static final BuiltinTypeReference ERROR = new BuiltinTypeReference("error");
    public static final BuiltinTypeReference FLOAT32 = new BuiltinTypeReference("float32");
    public static final BuiltinTypeReference FLOAT64 = new BuiltinTypeReference("float64");
    public static final BuiltinTypeReference INT = new BuiltinTypeReference("int");
    public static final BuiltinTypeReference INT8 = new BuiltinTypeReference("int8");
    public static final BuiltinTypeReference INT16 = new BuiltinTypeReference("int16");
    public static final BuiltinTypeReference INT32 = new BuiltinTypeReference("int32");
    public static final BuiltinTypeReference INT64 = new BuiltinTypeReference("int64");
    public static final BuiltinTypeReference RUNE = new BuiltinTypeReference("rune");
    public static final BuiltinTypeReference STRING = new BuiltinTypeReference("string");
    public static final BuiltinTypeReference UINT = new BuiltinTypeReference("uint");
    public static final BuiltinTypeReference UINT8 = new BuiltinTypeReference("uint8");
    public static final BuiltinTypeReference UINT16 = new BuiltinTypeReference("uint16");
    public static final BuiltinTypeReference UINT32 = new BuiltinTypeReference("uint32");
    public static final BuiltinTypeReference UINT64 = new BuiltinTypeReference("uint64");
    public static final BuiltinTypeReference UINTPTR = new BuiltinTypeReference("uintptr");

    private final String name;

    private BuiltinTypeReference(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
