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

import javax.servlet.http.Cookie;

import org.jasig.cas.AbstractCentralAuthenticationServiceTest;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.test.MockRequestContext;

public class SendTicketGrantingTicketActionTests extends AbstractCentralAuthenticationServiceTest {
    private SendTicketGrantingTicketAction action;
    
    private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;
    
    private MockRequestContext context;
    
    protected void onSetUp() throws Exception {
        this.action = new SendTicketGrantingTicketAction();
        
        this.ticketGrantingTicketCookieGenerator = new CookieRetrievingCookieGenerator();
        
        this.ticketGrantingTicketCookieGenerator.setCookieName("TGT");
        
        this.action.setCentralAuthenticationService(getCentralAuthenticationService());
        
        this.action.setTicketGrantingTicketCookieGenerator(this.ticketGrantingTicketCookieGenerator);
       
        this.action.afterPropertiesSet();
        
        this.context = new MockRequestContext();
    }
    
    public void testNoTgtToSet() throws Exception {
        this.context.setExternalContext(new ServletExternalContext(new MockServletContext(), new MockHttpServletRequest(), new MockHttpServletResponse()));
        
        assertEquals("success", this.action.execute(this.context).getId());
    }
    
    public void testTgtToSet() throws Exception {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final String TICKET_VALUE = "test";
        
        WebUtils.putTicketGrantingTicketInRequestScope(this.context, TICKET_VALUE);
        this.context.setExternalContext(new ServletExternalContext(new MockServletContext(), new MockHttpServletRequest(), response));
        
        assertEquals("success", this.action.execute(this.context).getId());
        assertEquals(TICKET_VALUE, response.getCookies()[0].getValue());
    }
    
    public void testTgtToSetRemovingOldTgt() throws Exception {
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final String TICKET_VALUE = "test";
        request.setCookies(new Cookie[] {new Cookie("TGT", "test5")});
        WebUtils.putTicketGrantingTicketInRequestScope(this.context, TICKET_VALUE);
        this.context.setExternalContext(new ServletExternalContext(new MockServletContext(), request, response));
        
        assertEquals("success", this.action.execute(this.context).getId());
        assertEquals(TICKET_VALUE, response.getCookies()[0].getValue());
    }
    
    
    
 
    
    
}
