/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web.view;

import java.util.HashMap;

import org.jasig.cas.web.view.Cas10ResponseViewTests.MockWriterHttpMockHttpServletResponse;
import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 *
 */
public class Saml10FailureResponseViewTests extends TestCase {

    private Saml10FailureResponseView view = new Saml10FailureResponseView();
    
    public void testResponse() throws Exception {
        final MockHttpServletRequest request =  new MockHttpServletRequest();
        final MockWriterHttpMockHttpServletResponse response = new MockWriterHttpMockHttpServletResponse();
        request.addParameter("TARGET", "service");
        
        this.view.renderMergedOutputModel(new HashMap(), request, response);
        
        assertTrue(response.getWrittenValue().contains("Status"));
    }
    
}
