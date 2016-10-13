/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.proxy.support;

import java.net.URL;

import org.jasig.cas.authentication.principal.HttpBasedServiceCredentials;
import org.jasig.cas.util.DefaultUniqueTicketIdGenerator;
import org.jasig.cas.util.HttpClient;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 41982 $ $Date: 2007-05-30 15:35:47 -0400 (Wed, 30 May 2007) $
 * @since 3.0
 */
public class Cas20ProxyHandlerTests extends TestCase {

    private Cas20ProxyHandler handler;

    protected void setUp() throws Exception {
        this.handler = new Cas20ProxyHandler();
        this.handler.setHttpClient(new HttpClient());
        this.handler.setUniqueTicketIdGenerator(new DefaultUniqueTicketIdGenerator());
    }

    public void testValidProxyTicketWithoutQueryString() throws Exception {
        assertNotNull(this.handler.handle(new HttpBasedServiceCredentials(
            new URL("http://www.rutgers.edu/")), "proxyGrantingTicketId"));
    }

    public void testValidProxyTicketWithQueryString() throws Exception {
        assertNotNull(this.handler.handle(new HttpBasedServiceCredentials(
            new URL("http://www.rutgers.edu/?test=test")),
            "proxyGrantingTicketId"));
    }

    public void testNonValidProxyTicket() throws Exception {
        final HttpClient httpClient = new HttpClient();
        httpClient.setAcceptableCodes(new int[] {900});
        this.handler.setHttpClient(httpClient);
        assertNull(this.handler.handle(new HttpBasedServiceCredentials(new URL(
            "http://www.rutgers.edu")), "proxyGrantingTicketId"));
    }
}
