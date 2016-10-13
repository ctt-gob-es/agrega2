/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.init;

import javax.servlet.ServletContextEvent;

import org.springframework.mock.web.MockServletContext;
import junit.framework.TestCase;

/**
 * Testcase for SafeContextLoaderListener.
 * 
 * @author Andrew Petro
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class SafeContextLoaderListenerTests extends TestCase {

    private MockServletContext servletContext;

    private ServletContextEvent servletContextEvent;

    private SafeContextLoaderListener listener;

    protected void setUp() throws Exception {
        this.listener = new SafeContextLoaderListener();
        this.servletContext = new MockServletContext();
        this.servletContextEvent = new ServletContextEvent(this.servletContext);
    }

    /**
     * Test that SafeContextLoaderListener does not propogate exceptions thrown
     * by its delegate in contextInitialized().
     */
    public void testContextInitialized() {
        /*
         * this testcase relies upon the fact that ContextLoaderListener()
         * throws a FileNotFound exception when invoked in the context of this
         * testcase because it does not find the resource
         * /WEB-INF/applicationContext.xml if our SafeContextLoaderListener
         * instance also throws the exception its delegate threw, this testcase
         * will fail. If it catches the exception, this test method will return
         * without having failed and so indicate success.
         */

        this.listener.contextInitialized(this.servletContextEvent);
    }

    public void testContextDestroy() {
        this.listener.contextDestroyed(this.servletContextEvent);
    }
}
