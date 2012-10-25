/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.awt.Image;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.netbeans.editor.navigation.NavigatorNode;
import org.antlr.netbeans.editor.navigation.NavigatorPanelUI;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.util.ImageUtilities;

public class GoNode extends NavigatorNode {

    @StaticResource
    private static final String CONST_STATIC_PUBLIC = "org/tvl/goworks/editor/go/resources/const_static_public.png";
    @StaticResource
    private static final String CONST_STATIC_PROTECTED = "org/tvl/goworks/editor/go/resources/const_static_protected.png";
    @StaticResource
    private static final String CONST_PUBLIC = "org/tvl/goworks/editor/go/resources/const_public.png";
    @StaticResource
    private static final String CONST_PROTECTED = "org/tvl/goworks/editor/go/resources/const_protected.png";
    @StaticResource
    private static final String FIELD_STATIC_PUBLIC = "org/tvl/goworks/editor/go/resources/field_static_16.png";
    @StaticResource
    private static final String FIELD_STATIC_PROTECTED = "org/tvl/goworks/editor/go/resources/field_static_protected_16.png";
    @StaticResource
    private static final String FIELD_PUBLIC = "org/tvl/goworks/editor/go/resources/fields.png";
    @StaticResource
    private static final String FIELD_PROTECTED = "org/tvl/goworks/editor/go/resources/field_protected_16.png";
    @StaticResource
    private static final String METHOD_STATIC_PUBLIC = "org/tvl/goworks/editor/go/resources/methods_static.png";
    @StaticResource
    private static final String METHOD_STATIC_PROTECTED = "org/tvl/goworks/editor/go/resources/methods_static_protected.png";
    @StaticResource
    private static final String METHOD_PUBLIC = "org/tvl/goworks/editor/go/resources/methods.png";
    @StaticResource
    private static final String METHOD_PROTECTED = "org/tvl/goworks/editor/go/resources/methods_protected.png";
    @StaticResource
    private static final String STRUCT = "org/tvl/goworks/editor/go/resources/struct_16.png";
    @StaticResource
    private static final String INTERFACE = "org/tvl/goworks/editor/go/resources/interface.png";
    @StaticResource
    private static final String TYPEDEF = "org/tvl/goworks/editor/go/resources/typedef_16.png";

    public GoNode(NavigatorPanelUI ui, DeclarationDescription description) {
        super(ui, description, DeclarationNodeFactory.INSTANCE);
    }

    @Override
    public DeclarationDescription getDescription() {
        return (DeclarationDescription)super.getDescription();
    }

    @Override
    public Image getIcon(int type) {
        String imageName;
        switch (getDescription().getKind()) {
        case CONSTANT:
            if (getDescription().isExported()) {
                imageName = CONST_STATIC_PUBLIC;
            } else {
                imageName = CONST_STATIC_PROTECTED;
            }
            break;

        case VARIABLE:
            if (getDescription().isExported()) {
                imageName = FIELD_STATIC_PUBLIC;
            } else {
                imageName = FIELD_STATIC_PROTECTED;
            }
            break;

        case FIELD:
            if (getDescription().isExported()) {
                imageName = FIELD_PUBLIC;
            } else {
                imageName = FIELD_PROTECTED;
            }
            break;

        case FUNCTION:
            if (getDescription().isExported()) {
                imageName = METHOD_STATIC_PUBLIC;
            } else {
                imageName = METHOD_STATIC_PROTECTED;
            }
            break;

        case METHOD:
            if (getDescription().isExported()) {
                imageName = METHOD_PUBLIC;
            } else {
                imageName = METHOD_PROTECTED;
            }
            break;

        case STRUCT:
            imageName = STRUCT;
            break;

        case INTERFACE:
            imageName = INTERFACE;
            break;

        case TYPEDEF:
            imageName = TYPEDEF;
            break;

        case UNKNOWN:
        default:
            // TODO: use error image
            imageName = STRUCT;
            break;
        }

        return ImageUtilities.loadImage(imageName);
    }

    public static class DeclarationDescription extends Description {
        private final DeclarationKind kind;
        private final String sortText;

        public DeclarationDescription() {
            this.kind = DeclarationKind.UNKNOWN;
            this.sortText = null;
        }

        public DeclarationDescription(String name, DeclarationKind kind) {
            super(name);
            this.kind = kind;
            this.sortText = getSortOrder(kind) + "_" + name.toLowerCase();
        }

        @Override
        public String getSortText() {
            if (sortText != null) {
                return sortText;
            }

            return super.getSortText();
        }

        public DeclarationKind getKind() {
            return kind;
        }

        public boolean isExported() {
            return !getName().isEmpty() && Character.isUpperCase(getName().charAt(0));
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DeclarationDescription)) {
                return false;
            }

            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        private static int getSortOrder(DeclarationKind kind) {
            // functions sort before vars, but fields sort before methods
            switch (kind) {
            case CONSTANT:
                return 0;

            case FUNCTION:
            case FIELD:
                return 1;

            case METHOD:
            case VARIABLE:
                return 2;

            case STRUCT:
            case INTERFACE:
            case TYPEDEF:
                return 3;

            case UNKNOWN:
            default:
                return 4;
            }
        }
    }

    protected static class DeclarationNodeFactory implements Factory {
        public static final DeclarationNodeFactory INSTANCE = new DeclarationNodeFactory();

        @Override
        public NavigatorNode createNode(NavigatorPanelUI ui, Description key) {
            if (!(key instanceof DeclarationDescription)) {
                throw new UnsupportedOperationException();
            }

            return new GoNode(ui, (DeclarationDescription)key);
        }

    }
}
