/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.services;

import junit.framework.TestCase;


public class UnauthorizedSsoServiceExceptionTests extends TestCase {

    private static final String CODE = "service.not.authorized.sso";
    
    public void testGetCode() {
        UnauthorizedSsoServiceException e = new UnauthorizedSsoServiceException();
        assertEquals(CODE, e.getMessage());
    }

    public void testCodeConstructor() {
        final String MESSAGE = "GG";
        final UnauthorizedSsoServiceException e = new UnauthorizedSsoServiceException(MESSAGE);
        
        assertEquals(MESSAGE, e.getMessage());
    }
    
    public void testThrowableConstructorWithCode() {
        final String MESSAGE = "GG";
        final RuntimeException r = new RuntimeException();
        final UnauthorizedSsoServiceException e = new UnauthorizedSsoServiceException(MESSAGE, r);
        
        assertEquals(MESSAGE, e.getMessage());
        assertEquals(r, e.getCause());
    }
}
