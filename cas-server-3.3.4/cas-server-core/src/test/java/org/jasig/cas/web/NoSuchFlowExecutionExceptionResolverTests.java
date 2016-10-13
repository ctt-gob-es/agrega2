/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.webflow.execution.repository.FlowExecutionKey;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class NoSuchFlowExecutionExceptionResolverTests extends TestCase {

    private NoSuchFlowExecutionExceptionResolver resolver;

    protected void setUp() throws Exception {
        this.resolver = new NoSuchFlowExecutionExceptionResolver();
    }

    public void testNullPointerException() {
        assertNull(this.resolver.resolveException(new MockHttpServletRequest(),
            new MockHttpServletResponse(), null, new NullPointerException()));
    }

    public void testNoSuchFlowExecutionException() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("test");
        ModelAndView model = (this.resolver.resolveException(request,
            new MockHttpServletResponse(), null,
            new NoSuchFlowExecutionException(new FlowExecutionKey(){
            
                private static final long serialVersionUID = 1443616250214416520L;

                public String toString() {
                    return "test";
                }
            
            }, new RuntimeException())));

        assertEquals(request.getRequestURI(), ((RedirectView) model.getView())
            .getUrl());
    }
    
    public void testNoSuchFlowExecutionExeptionWithQueryString() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("test");
        request.setQueryString("test=test");
        ModelAndView model = (this.resolver.resolveException(request,
            new MockHttpServletResponse(), null,
            new NoSuchFlowExecutionException(new FlowExecutionKey(){
                
                private static final long serialVersionUID = -4750073902540974152L;

                public String toString() {
                    return "test";
                }
            
            }, new RuntimeException())));

        assertEquals(request.getRequestURI() + "?" + request.getQueryString(), ((RedirectView) model.getView())
            .getUrl());
    }

}
