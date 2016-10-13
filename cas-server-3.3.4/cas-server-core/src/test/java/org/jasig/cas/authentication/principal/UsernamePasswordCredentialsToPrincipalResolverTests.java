/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.authentication.principal;

import org.jasig.cas.TestUtils;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public final class UsernamePasswordCredentialsToPrincipalResolverTests extends
    TestCase {

    private CredentialsToPrincipalResolver resolver = new UsernamePasswordCredentialsToPrincipalResolver();

    public void testValidSupportsCredentials() {
        assertTrue(this.resolver.supports(TestUtils
            .getCredentialsWithSameUsernameAndPassword()));
    }

    public void testNullSupportsCredentials() {
        assertFalse(this.resolver.supports(null));
    }

    public void testInvalidSupportsCredentials() {
        assertFalse(this.resolver.supports(TestUtils
            .getHttpBasedServiceCredentials()));
    }

    public void testValidCredentials() {
        Principal p = this.resolver.resolvePrincipal(TestUtils
            .getCredentialsWithSameUsernameAndPassword());

        assertEquals(p.getId(), TestUtils
            .getCredentialsWithSameUsernameAndPassword().getUsername());
    }
}