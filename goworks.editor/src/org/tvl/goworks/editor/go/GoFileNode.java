/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "TP_NeedsCompileBadge=Needs to be compiled.",
    "TP_ExecutableBadge=Contains main class.",
})
public final class GoFileNode extends DataNode implements ChangeListener {

    private static final String EXECUTABLE_BADGE_URL = "org/tvl/goworks/editor/go/resources/executable-badge.png";
    private static final String NEEDS_COMPILE_BADGE_URL = "org/tvl/goworks/editor/go/resources/needs-compile.png";

    private static final String GO_ICON_BASE = "org/tvl/goworks/editor/go/resources/class.png"; // NOI18N
    private static final String CLASS_ICON_BASE = "org/tvl/goworks/editor/go/resources/clazz.gif"; // NOI18N

    private static final Image NEEDS_COMPILE;
    private static final Image IS_EXECUTABLE_CLASS;

    private static final Logger LOGGER = Logger.getLogger(GoFileNode.class.getName());

    @NonNull
    private final AtomicBoolean isCompiled = new AtomicBoolean(true);
    @NonNull
    private final AtomicBoolean isExecutable = new AtomicBoolean(false);

    static {
        URL needsCompileIconURL = GoFileNode.class.getClassLoader().getResource(NEEDS_COMPILE_BADGE_URL);
        String needsCompileTP = "<img src=\"" + needsCompileIconURL + "\">&nbsp;" + Bundle.TP_NeedsCompileBadge();
        NEEDS_COMPILE = ImageUtilities.assignToolTipToImage(ImageUtilities.loadImage(NEEDS_COMPILE_BADGE_URL), needsCompileTP); // NOI18N
        URL executableIconURL = GoFileNode.class.getClassLoader().getResource(EXECUTABLE_BADGE_URL);
        String executableTP = "<img src=\"" + executableIconURL + "\">&nbsp;" + Bundle.TP_ExecutableBadge();
        IS_EXECUTABLE_CLASS = ImageUtilities.assignToolTipToImage(ImageUtilities.loadImage(EXECUTABLE_BADGE_URL), executableTP); // NOI18N
    }

    public GoFileNode(DataObject obj, boolean isGoSource) {
        super(obj, Children.LEAF);
        this.setIconBaseWithExtension(isGoSource ? GO_ICON_BASE : CLASS_ICON_BASE);
    }

    @Override
    public void setName(String name) {
        RenameHandler handler = getRenameHandler();
        if (handler == null) {
            super.setName(name);
        } else {
            try {
                handler.handleRename(GoFileNode.this, name);
            } catch (IllegalArgumentException ex) {
                super.setName(name);
            }
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();

        //if there is any rename handler installed
        //push under our own property
        if (getRenameHandler() != null)
            sheet.get(Sheet.PROPERTIES).put(createNameProperty());

        return sheet;
    }

    private Node.Property<String> createNameProperty () {
        Node.Property<String> p = new PropertySupport.ReadWrite<String> (
                DataObject.PROP_NAME,
                String.class,
                NbBundle.getMessage (DataObject.class, "PROP_name"),
                NbBundle.getMessage (DataObject.class, "HINT_name")
                )
        {
            @Override
            public String getValue () {
                return GoFileNode.this.getName();
            }
            @Override
            public Object getValue(String key) {
                if ("suppressCustomEditor".equals (key)) { //NOI18N
                    return Boolean.TRUE;
                } else {
                    return super.getValue (key);
                }
            }
            @Override
            public void setValue(String val) throws IllegalAccessException,
                    IllegalArgumentException, InvocationTargetException {
                if (!canWrite())
                    throw new IllegalAccessException();
                GoFileNode.this.setName(val);
            }
            @Override
            public boolean canWrite() {
                return GoFileNode.this.canRename();
            }

        };

        return p;
    }

    private static synchronized RenameHandler getRenameHandler() {
        Collection<? extends RenameHandler> handlers = Lookup.getDefault().lookupAll(RenameHandler.class);
        if (handlers.isEmpty()) {
            return null;
        }

        if (handlers.size() > 1) {
            LOGGER.log(Level.WARNING, "Multiple instances of RenameHandler found in Lookup; only using first one: {0}", handlers); // NOI18N
        }

        return handlers.iterator().next();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Image getIcon(int type) {
        Image i = super.getIcon(type);

        return enhanceIcon(i);
    }

    @Override
    public Image getOpenedIcon(int type) {
        Image i = super.getOpenedIcon(type);

        return enhanceIcon(i);
    }

    private Image enhanceIcon(Image i) {
        if (!isCompiled.get()) {
            i = ImageUtilities.mergeImages(i, NEEDS_COMPILE, 16, 0);
        }

        if (isExecutable.get()) {
            i = ImageUtilities.mergeImages(i, IS_EXECUTABLE_CLASS, 10, 6);
        }

        return i;
    }

}
