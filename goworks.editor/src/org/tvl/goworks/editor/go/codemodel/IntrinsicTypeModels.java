/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.tvl.goworks.editor.go.codemodel.impl.FunctionIntrinsicModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FunctionModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.IntrinsicFileModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.ParameterModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeIntrinsicModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class IntrinsicTypeModels {

    public static final TypeIntrinsicModel BOOL = new TypeIntrinsicModelImpl(IntrinsicKind.BOOL);
    public static final TypeIntrinsicModel BYTE = new TypeIntrinsicModelImpl(IntrinsicKind.BYTE);
    public static final TypeIntrinsicModel COMPLEX64 = new TypeIntrinsicModelImpl(IntrinsicKind.COMPLEX64);
    public static final TypeIntrinsicModel COMPLEX128 = new TypeIntrinsicModelImpl(IntrinsicKind.COMPLEX128);
    public static final TypeIntrinsicModel FLOAT32 = new TypeIntrinsicModelImpl(IntrinsicKind.FLOAT32);
    public static final TypeIntrinsicModel FLOAT64 = new TypeIntrinsicModelImpl(IntrinsicKind.FLOAT64);
    public static final TypeIntrinsicModel INT = new TypeIntrinsicModelImpl(IntrinsicKind.INT);
    public static final TypeIntrinsicModel INT8 = new TypeIntrinsicModelImpl(IntrinsicKind.INT8);
    public static final TypeIntrinsicModel INT16 = new TypeIntrinsicModelImpl(IntrinsicKind.INT16);
    public static final TypeIntrinsicModel INT32 = new TypeIntrinsicModelImpl(IntrinsicKind.INT32);
    public static final TypeIntrinsicModel INT64 = new TypeIntrinsicModelImpl(IntrinsicKind.INT64);
    public static final TypeIntrinsicModel RUNE = new TypeIntrinsicModelImpl(IntrinsicKind.RUNE);
    public static final TypeIntrinsicModel STRING = new TypeIntrinsicModelImpl(IntrinsicKind.STRING);
    public static final TypeIntrinsicModel UINT = new TypeIntrinsicModelImpl(IntrinsicKind.UINT);
    public static final TypeIntrinsicModel UINT8 = new TypeIntrinsicModelImpl(IntrinsicKind.UINT8);
    public static final TypeIntrinsicModel UINT16 = new TypeIntrinsicModelImpl(IntrinsicKind.UINT16);
    public static final TypeIntrinsicModel UINT32 = new TypeIntrinsicModelImpl(IntrinsicKind.UINT32);
    public static final TypeIntrinsicModel UINT64 = new TypeIntrinsicModelImpl(IntrinsicKind.UINT64);
    public static final TypeIntrinsicModel UINTPTR = new TypeIntrinsicModelImpl(IntrinsicKind.UINTPTR);
    public static final TypeIntrinsicModel ERROR = new TypeIntrinsicModelImpl(IntrinsicKind.ERROR) {
        FunctionModelImpl errorFunction = new FunctionIntrinsicModelImpl("Error") {
            {
                getReturnValues().add(new ParameterModelImpl("_", VarKind.RETURN, (TypeIntrinsicModelImpl)STRING, IntrinsicFileModelImpl.INSTANCE, null, null));
                freeze();
            }
        };

        @Override
        public Collection<FunctionModelImpl> getMethods() {
            return Collections.singletonList(errorFunction);
        }
    };

    private static final Map<String, TypeIntrinsicModel> MODELS = new HashMap<String, TypeIntrinsicModel>()
        {{
            put(BOOL.getName().toLowerCase(), BOOL);
            put(BYTE.getName().toLowerCase(), BYTE);
            put(COMPLEX64.getName().toLowerCase(), COMPLEX64);
            put(COMPLEX128.getName().toLowerCase(), COMPLEX128);
            put(ERROR.getName().toLowerCase(), ERROR);
            put(FLOAT32.getName().toLowerCase(), FLOAT32);
            put(FLOAT64.getName().toLowerCase(), FLOAT64);
            put(INT.getName().toLowerCase(), INT);
            put(INT8.getName().toLowerCase(), INT8);
            put(INT16.getName().toLowerCase(), INT16);
            put(INT32.getName().toLowerCase(), INT32);
            put(INT64.getName().toLowerCase(), INT64);
            put(RUNE.getName().toLowerCase(), RUNE);
            put(STRING.getName().toLowerCase(), STRING);
            put(UINT.getName().toLowerCase(), UINT);
            put(UINT8.getName().toLowerCase(), UINT8);
            put(UINT16.getName().toLowerCase(), UINT16);
            put(UINT32.getName().toLowerCase(), UINT32);
            put(UINT64.getName().toLowerCase(), UINT64);
            put(UINTPTR.getName().toLowerCase(), UINTPTR);
        }};

    public static TypeModel getIntrinsicType(String name) {
        return MODELS.get(name);
    }

}
