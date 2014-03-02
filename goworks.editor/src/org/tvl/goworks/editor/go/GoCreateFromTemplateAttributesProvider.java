/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go;

import java.util.HashMap;
import java.util.Map;
import org.openide.loaders.CreateFromTemplateAttributesProvider;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sam Harwell
 */
@ServiceProvider(service=CreateFromTemplateAttributesProvider.class)
public class GoCreateFromTemplateAttributesProvider implements CreateFromTemplateAttributesProvider {

    @Override
    public Map<String, ?> attributesFor(DataObject template, DataFolder target, String name) {
        if (!(template instanceof GoDataObject)) {
            return null;
        }

        Map<String, String> attributes = new HashMap<>();
        attributes.put("package", target.getName());
        return attributes;
    }

}
