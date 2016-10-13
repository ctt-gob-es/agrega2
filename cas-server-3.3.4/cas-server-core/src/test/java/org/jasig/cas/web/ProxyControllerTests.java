/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web;

import org.jasig.cas.AbstractCentralAuthenticationServiceTest;
import org.jasig.cas.TestUtils;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.TicketGrantingTicketImpl;
import org.jasig.cas.ticket.support.NeverExpiresExpirationPolicy;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class ProxyControllerTests extends
    AbstractCentralAuthenticationServiceTest {

    private ProxyController proxyController;

    protected void onSetUp() throws Exception {
        this.proxyController = new ProxyController();
        this.proxyController
            .setCentralAuthenticationService(getCentralAuthenticationService());

        StaticApplicationContext context = new StaticApplicationContext();
        context.refresh();
        ((ApplicationContextAware) this.proxyController)
            .setApplicationContext(context);
    }

    public void testNoParams() throws Exception {
        assertEquals("INVALID_REQUEST", this.proxyController
            .handleRequestInternal(new MockHttpServletRequest(),
                new MockHttpServletResponse()).getModel()
            .get("code"));
    }

    public void testNonExistantPGT() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("pgt", "TestService");
        request.addParameter("targetService", "service");

        assertTrue(this.proxyController.handleRequestInternal(request,
            new MockHttpServletResponse()).getModel().containsKey(
            "code"));
    }

    public void testExistingPGT() throws Exception {
        final TicketGrantingTicket ticket = new TicketGrantingTicketImpl(
            "ticketGrantingTicketId", TestUtils.getAuthentication(),
            new NeverExpiresExpirationPolicy());
        getTicketRegistry().addTicket(ticket);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request
            .addParameter("pgt", ticket.getId());
        request.addParameter(
            "targetService", "service");

        assertTrue(this.proxyController.handleRequestInternal(request,
            new MockHttpServletResponse()).getModel().containsKey(
            "ticket"));
    }
}
