/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.remoting.server;

import org.jasig.cas.AbstractCentralAuthenticationServiceTest;
import org.jasig.cas.TestUtils;
import org.jasig.cas.ticket.TicketException;
import org.jasig.cas.validation.UsernamePasswordCredentialsValidator;
import org.springframework.validation.Validator;

/**
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class RemoteCentralAuthenticationServiceTests extends
    AbstractCentralAuthenticationServiceTest {

    private RemoteCentralAuthenticationService remoteCentralAuthenticationService;

    protected void onSetUp() throws Exception {
        this.remoteCentralAuthenticationService = new RemoteCentralAuthenticationService();

        this.remoteCentralAuthenticationService
            .setCentralAuthenticationService(getCentralAuthenticationService());

        Validator[] validators = new Validator[1];
        validators[0] = new UsernamePasswordCredentialsValidator();

        this.remoteCentralAuthenticationService.setValidators(validators);
    }

    public void testInvalidCredentials() throws TicketException {
        try {
            this.remoteCentralAuthenticationService
                .createTicketGrantingTicket(TestUtils
                    .getCredentialsWithDifferentUsernameAndPassword(null, null));
            fail("IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    public void testValidCredentials() throws TicketException {
        this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
    }

    public void testDontUseValidatorsToCheckValidCredentials() {
        try {
            this.remoteCentralAuthenticationService.setValidators(null);
            this.remoteCentralAuthenticationService
                .createTicketGrantingTicket(TestUtils
                    .getCredentialsWithDifferentUsernameAndPassword());
            fail("TicketException expected.");
        } catch (TicketException e) {
            return;
        }
    }

    public void testDestroyTicketGrantingTicket() {
        this.remoteCentralAuthenticationService
            .destroyTicketGrantingTicket("test");
    }

    public void testGrantServiceTicketWithValidTicketGrantingTicket()
        throws TicketException {
        final String ticketId = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        this.remoteCentralAuthenticationService.grantServiceTicket(ticketId,
            TestUtils.getService());
    }

    public void testGrantServiceTicketWithValidCredentials()
        throws TicketException {
        final String ticketGrantingTicketId = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        this.remoteCentralAuthenticationService.grantServiceTicket(
            ticketGrantingTicketId, TestUtils.getService(), TestUtils
                .getCredentialsWithSameUsernameAndPassword());
    }

    public void testGrantServiceTicketWithNullCredentials()
        throws TicketException {
        final String ticketGrantingTicketId = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        this.remoteCentralAuthenticationService.grantServiceTicket(
            ticketGrantingTicketId, TestUtils.getService(), null);
    }

    public void testGrantServiceTicketWithEmptyCredentials()
        throws TicketException {
        final String ticketGrantingTicketId = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        try {
            this.remoteCentralAuthenticationService.grantServiceTicket(
                ticketGrantingTicketId, TestUtils.getService(), TestUtils
                    .getCredentialsWithDifferentUsernameAndPassword("", ""));
            fail("IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    public void testValidateServiceTicketWithValidService()
        throws TicketException {
        final String ticketGrantingTicket = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        final String serviceTicket = this.remoteCentralAuthenticationService
            .grantServiceTicket(ticketGrantingTicket, TestUtils.getService());

        this.remoteCentralAuthenticationService.validateServiceTicket(
            serviceTicket, TestUtils.getService());
    }

    public void testDelegateTicketGrantingTicketWithValidCredentials()
        throws TicketException {
        final String ticketGrantingTicket = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        final String serviceTicket = this.remoteCentralAuthenticationService
            .grantServiceTicket(ticketGrantingTicket, TestUtils.getService());
        this.remoteCentralAuthenticationService.delegateTicketGrantingTicket(
            serviceTicket, TestUtils.getHttpBasedServiceCredentials());
    }

    public void testDelegateTicketGrantingTicketWithInvalidCredentials()
        throws TicketException {
        final String ticketGrantingTicket = this.remoteCentralAuthenticationService
            .createTicketGrantingTicket(TestUtils
                .getCredentialsWithSameUsernameAndPassword());
        final String serviceTicket = this.remoteCentralAuthenticationService
            .grantServiceTicket(ticketGrantingTicket, TestUtils.getService());
        try {
            this.remoteCentralAuthenticationService
                .delegateTicketGrantingTicket(serviceTicket, TestUtils
                    .getCredentialsWithDifferentUsernameAndPassword("", ""));
            fail("IllegalArgumentException expected.");
        } catch (IllegalArgumentException e) {
            return;
        }

    }
}
