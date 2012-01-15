/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
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
            return null;
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
