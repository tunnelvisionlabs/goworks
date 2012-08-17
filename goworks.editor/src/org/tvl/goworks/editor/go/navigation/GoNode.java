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
import org.openide.util.ImageUtilities;

public class GoNode extends NavigatorNode {

    public GoNode(NavigatorPanelUI ui, DeclarationDescription description) {
        super(ui, description, DeclarationNodeFactory.INSTANCE);
    }

    @Override
    public DeclarationDescription getDescription() {
        return (DeclarationDescription)super.getDescription();
    }

    @Override
    public Image getIcon(int type) {
        String resourceLocation = "org/tvl/goworks/editor/go/resources/";
        String imageName;
        switch (getDescription().getKind()) {
        case CONSTANT:
            imageName = "global_constant.png";
            break;

        case VARIABLE:
            imageName = "global_variable.png";
            break;

        case FIELD:
            imageName = "fields.png";
            break;

        case FUNCTION:
            imageName = "global_function.png";
            break;

        case METHOD:
            if (getDescription().isExported()) {
                imageName = "methods.png";
            } else {
                imageName = "methods_protected.png";
            }
            break;

        case STRUCT:
            imageName = "struct_16.png";
            break;

        case UNKNOWN:
        default:
            // TODO: use error image
            imageName = "struct_16.png";
            break;
        }

        return ImageUtilities.loadImage(resourceLocation + imageName);
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
            switch (kind) {
            case CONSTANT:
                return 0;

            case FUNCTION:
            case METHOD:
                return 1;

            case FIELD:
            case VARIABLE:
                return 2;

            case STRUCT:
            case INTERFACE:
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
