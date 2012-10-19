/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
@TemplateRegistrations({
    @TemplateRegistration(folder = "Code", position = 100, content = "resources/main.go.template", scriptEngine = "freemarker", displayName = "#main.go", description = "resources/main.html", category = "go-main"),
    @TemplateRegistration(folder = "Code", position = 200, content = "resources/source.go.template", scriptEngine = "freemarker", displayName = "#source.go", description = "resources/source.html", category = "go-code"),
    @TemplateRegistration(folder = "Code", position = 300, content = "resources/source_test.go.template", scriptEngine = "freemarker", displayName = "#source_test.go", description = "resources/source_test.html", category = "go-test"),
    @TemplateRegistration(folder = "Code", position = 400, content = "resources/empty.go.template", scriptEngine = "freemarker", displayName = "#empty.go", description = "resources/empty.html", category = {"go-code", "go-code-basic"}),
})
@NbBundle.Messages({
    "main.go=Go Main File",
    "source.go=Go Source File",
    "source_test.go=Go Test File",
    "empty.go=Empty Go File",
    "Templates/Code=Go",
})
package org.tvl.goworks.editor.go;

import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.templates.TemplateRegistrations;
import org.openide.util.NbBundle;
