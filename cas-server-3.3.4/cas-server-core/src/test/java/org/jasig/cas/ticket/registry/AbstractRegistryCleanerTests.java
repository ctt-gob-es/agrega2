/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.registry;

import org.jasig.cas.TestUtils;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.TicketGrantingTicketImpl;
import org.jasig.cas.ticket.support.NeverExpiresExpirationPolicy;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 39552 $ $Date: 2007-01-22 15:35:37 -0500 (Mon, 22 Jan 2007) $
 * @since 3.0
 */
public abstract class AbstractRegistryCleanerTests extends TestCase {

    private RegistryCleaner registryCleaner;

    private TicketRegistry ticketRegistry;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.ticketRegistry = this.getNewTicketRegistry();
        this.registryCleaner = this.getNewRegistryCleaner(this.ticketRegistry);
    }

    public abstract RegistryCleaner getNewRegistryCleaner(
        TicketRegistry newTicketRegistry);

    public abstract TicketRegistry getNewTicketRegistry();

    public void testCleanEmptyTicketRegistry() {
        this.registryCleaner.clean();
        assertTrue(this.ticketRegistry.getTickets().isEmpty());
    }

    public void testCleanRegistryOfExpiredTicketsAllExpired() {
        populateRegistryWithExpiredTickets();
        this.registryCleaner.clean();
        assertTrue(this.ticketRegistry.getTickets().isEmpty());
    }

    public void testCleanRegistryOneNonExpired() {
        populateRegistryWithExpiredTickets();
        TicketGrantingTicket ticket = new TicketGrantingTicketImpl(
            "testNoExpire", TestUtils.getAuthentication(),
            new NeverExpiresExpirationPolicy());
        this.ticketRegistry.addTicket(ticket);

        this.registryCleaner.clean();

        assertEquals(this.ticketRegistry.getTickets().size(), 1);
    }

    private void populateRegistryWithExpiredTickets() {
        for (int i = 0; i < 10; i++) {
            TicketGrantingTicket ticket = new TicketGrantingTicketImpl("test"
                + i, TestUtils.getAuthentication(),
                new NeverExpiresExpirationPolicy());
            ticket.expire();
            this.ticketRegistry.addTicket(ticket);
        }
    }
}
