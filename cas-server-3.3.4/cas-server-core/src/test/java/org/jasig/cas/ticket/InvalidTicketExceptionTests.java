/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;

import junit.framework.TestCase;

public class InvalidTicketExceptionTests extends TestCase {

    public void testCodeNoThrowable() {
        TicketException t = new InvalidTicketException();
        assertEquals("INVALID_TICKET", t.getCode());
    }

    public void testCodeWithThrowable() {
        AuthenticationException a = new BadCredentialsAuthenticationException();
        TicketException t = new InvalidTicketException(a);

        assertEquals(a.toString(), t.getCode());
    }
}
