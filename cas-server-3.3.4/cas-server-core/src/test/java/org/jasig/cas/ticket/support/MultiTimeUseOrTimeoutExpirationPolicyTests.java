/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.support;

import org.jasig.cas.TestUtils;
import org.jasig.cas.ticket.ExpirationPolicy;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.TicketGrantingTicketImpl;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 43197 $ $Date: 2008-02-26 07:51:58 -0500 (Tue, 26 Feb 2008) $
 * @since 3.0
 */
public class MultiTimeUseOrTimeoutExpirationPolicyTests extends TestCase {

    private static final long TIMEOUT = 5000;

    private static final int NUMBER_OF_USES = 5;
    
    private static final int TIMEOUT_BUFFER = 100;

    private ExpirationPolicy expirationPolicy;

    private TicketGrantingTicket ticket;

    protected void setUp() throws Exception {
        this.expirationPolicy = new MultiTimeUseOrTimeoutExpirationPolicy(
            NUMBER_OF_USES, TIMEOUT);

        this.ticket = new TicketGrantingTicketImpl("test", TestUtils
            .getAuthentication(), this.expirationPolicy);

        super.setUp();
    }

    public void testTicketIsNull() {
        assertTrue(this.expirationPolicy.isExpired(null));
    }

    public void testTicketIsNotExpired() {
        assertFalse(this.ticket.isExpired());
    }

    public void testTicketIsExpiredByTime() {
        try {
            Thread.sleep(TIMEOUT + TIMEOUT_BUFFER);
            assertTrue(this.ticket.isExpired());
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }

    public void testTicketIsExpiredByCount() {
        for (int i = 0; i < NUMBER_OF_USES; i++)
            this.ticket.grantServiceTicket("test", TestUtils.getService(), new NeverExpiresExpirationPolicy(), false);

        assertTrue(this.ticket.isExpired());
    }
}
