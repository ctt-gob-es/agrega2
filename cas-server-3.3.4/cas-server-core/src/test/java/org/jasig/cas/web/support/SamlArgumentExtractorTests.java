/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web.support;

import org.jasig.cas.authentication.principal.Service;
import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 *
 */
public class SamlArgumentExtractorTests extends TestCase {

    private SamlArgumentExtractor extractor;

    protected void setUp() throws Exception {
        this.extractor = new SamlArgumentExtractor();
        super.setUp();
    }
    
    public void testObtainService() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("TARGET", "test");
        final Service service = this.extractor.extractService(request);
        assertEquals("test", service.getId());
    }
    
    public void testServiceDoesNotExist() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        assertNull(this.extractor.extractService(request));
    }  
}
