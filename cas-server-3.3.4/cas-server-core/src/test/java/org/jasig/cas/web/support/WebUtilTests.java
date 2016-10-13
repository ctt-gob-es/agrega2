/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.support;

import java.util.Arrays;

import org.jasig.cas.authentication.principal.Service;
import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 42071 $ $Date: 2007-06-13 10:47:30 -0400 (Wed, 13 Jun 2007) $
 * @since 3.1
 *
 */
public class WebUtilTests extends TestCase {

    public void testFindService() {
        final SamlArgumentExtractor openIdArgumentExtractor = new SamlArgumentExtractor();
        final CasArgumentExtractor casArgumentExtractor = new CasArgumentExtractor();
        final ArgumentExtractor[] argumentExtractors = new ArgumentExtractor[] {
            openIdArgumentExtractor, casArgumentExtractor};
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("service", "test");

        final Service service = WebUtils.getService(Arrays
            .asList(argumentExtractors), request);

        assertEquals("test", service.getId());
    }
    
    public void testFoundNoService() {
        final SamlArgumentExtractor openIdArgumentExtractor = new SamlArgumentExtractor();
        final ArgumentExtractor[] argumentExtractors = new ArgumentExtractor[] {
            openIdArgumentExtractor};
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("service", "test");

        final Service service = WebUtils.getService(Arrays
            .asList(argumentExtractors), request);

        assertNull(service);
    }
    /*
     * public void testStripJsessionWithoutQueryStringParameters() {
     * assertEquals("test", WebUtils.stripJsessionFromUrl("test"));
     * assertEquals("http://www.cnn.com",
     * WebUtils.stripJsessionFromUrl("http://www.cnn.com;jsession=fsfsadfsdfsafsd")); }
     * public void testStripJsessionWithQueryStringParameters() {
     * assertEquals("test", WebUtils.stripJsessionFromUrl("test"));
     * assertEquals("http://localhost:8080/WebModule2/jsplevel0.jsp?action=test",
     * WebUtils.stripJsessionFromUrl("http://localhost:8080/WebModule2/jsplevel0.jsp;jsessionid=CC80B7CC9D62689578A99DB90B187A62?action=test")); }
     * public void testStripJsessionWithQueryStringParametersBeforeJsession() {
     * assertEquals("test", WebUtils.stripJsessionFromUrl("test"));
     * assertEquals("http://localhost:8080/WebModule2/jsplevel0.jsp?action=test",
     * WebUtils.stripJsessionFromUrl("http://localhost:8080/WebModule2/jsplevel0.jsp?action=test;jsessionid=CC80B7CC9D62689578A99DB90B187A62")); }
     */
}
