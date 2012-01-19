/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
