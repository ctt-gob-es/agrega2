/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.util;

import org.inspektr.common.ioc.annotation.NotNull;
import org.opensaml.artifact.SAMLArtifact;
import org.opensaml.artifact.SAMLArtifactType0002;
import org.opensaml.artifact.URI;

/**
 * Unique Ticket Id Generator compliant with the SAML 1.1 specification for
 * artifacts. This should also be compliant with the SAML 2 specification.
 * 
 * @author Scott
 * @version $Revision: 42776 $ $Date: 2008-01-04 09:15:42 -0500 (Fri, 04 Jan 2008) $
 * @since 3.0
 */
public final class SamlCompliantUniqueTicketIdGenerator implements
    UniqueTicketIdGenerator {

    /** SAML defines the source id as the server name. */
    @NotNull
    private final String sourceLocation;

    /** Random generator to construct the AssertionHandle. */
    private final RandomStringGenerator randomStringGenerator = new DefaultRandomStringGenerator(
        20);

    public SamlCompliantUniqueTicketIdGenerator(final String sourceId) {
        this.sourceLocation = sourceId;
    }

    /**
     * We ignore prefixes for SAML compliance.
     */
    public String getNewTicketId(final String prefix) {
        final SAMLArtifact samlArtifact = new SAMLArtifactType0002(
            this.randomStringGenerator.getNewStringAsBytes(), new URI(
                this.sourceLocation));

        return samlArtifact.encode();
    }
}
