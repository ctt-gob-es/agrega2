/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.flow;

import java.util.Arrays;

import org.jasig.cas.web.support.ArgumentExtractor;
import org.jasig.cas.web.support.CasArgumentExtractor;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.test.MockRequestContext;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 42067 $ $Date: 2007-06-12 15:55:40 -0400 (Tue, 12 Jun 2007) $
 * @since 3.0.5
 *
 */
public class InitialFlowSetupActionTests extends TestCase {
    private final InitialFlowSetupAction action = new InitialFlowSetupAction();
    
    private CookieRetrievingCookieGenerator warnCookieGenerator;
    
    private CookieRetrievingCookieGenerator tgtCookieGenerator;

    protected void setUp() throws Exception {
        this.warnCookieGenerator = new CookieRetrievingCookieGenerator();
        this.tgtCookieGenerator = new CookieRetrievingCookieGenerator();
        this.action.setTicketGrantingTicketCookieGenerator(this.tgtCookieGenerator);
        this.action.setWarnCookieGenerator(this.warnCookieGenerator);
        final ArgumentExtractor[] argExtractors = new ArgumentExtractor[] {new CasArgumentExtractor()};
        this.action.setArgumentExtractors(Arrays.asList(argExtractors));
        this.action.afterPropertiesSet();
        this.action.afterPropertiesSet();
    }
    
    public void testSettingContextPath() throws Exception {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final String CONST_CONTEXT_PATH = "/test";
        request.setContextPath(CONST_CONTEXT_PATH);
        final MockRequestContext context = new MockRequestContext();
        context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, new MockHttpServletResponse()));
        
        this.action.doExecute(context);
        
        assertEquals(CONST_CONTEXT_PATH, this.warnCookieGenerator.getCookiePath());
        assertEquals(CONST_CONTEXT_PATH, this.tgtCookieGenerator.getCookiePath());
    }
    
    public void testResettingContexPath() throws Exception {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final String CONST_CONTEXT_PATH = "/test";
        final String CONST_CONTEXT_PATH_2 = "/test1";
        request.setContextPath(CONST_CONTEXT_PATH);
        final MockRequestContext context = new MockRequestContext();
        context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, new MockHttpServletResponse()));
        
        this.action.doExecute(context);
        
        assertEquals(CONST_CONTEXT_PATH, this.warnCookieGenerator.getCookiePath());
        assertEquals(CONST_CONTEXT_PATH, this.tgtCookieGenerator.getCookiePath());
        
        request.setContextPath(CONST_CONTEXT_PATH_2);
        this.action.doExecute(context);
        
        assertNotSame(CONST_CONTEXT_PATH_2, this.warnCookieGenerator.getCookiePath());
        assertNotSame(CONST_CONTEXT_PATH_2, this.tgtCookieGenerator.getCookiePath());
        assertEquals(CONST_CONTEXT_PATH, this.warnCookieGenerator.getCookiePath());
        assertEquals(CONST_CONTEXT_PATH, this.tgtCookieGenerator.getCookiePath());
    }
    
    public void testNoServiceFound() throws Exception {
        final MockRequestContext context = new MockRequestContext();
        context.setExternalContext(new ServletExternalContext(new MockServletContext(), new MockHttpServletRequest(), new MockHttpServletResponse()));
        
        final Event event = this.action.execute(context);

        assertNull(WebUtils.getService(context));
        
        assertEquals("success", event.getId());
    }
    
    public void testServiceFound() throws Exception {
        final MockRequestContext context = new MockRequestContext();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("service", "test");
        context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, new MockHttpServletResponse()));
        
        final Event event = this.action.execute(context);

        assertEquals("test", WebUtils.getService(context).getId());
        assertEquals("success", event.getId());
    }
}
