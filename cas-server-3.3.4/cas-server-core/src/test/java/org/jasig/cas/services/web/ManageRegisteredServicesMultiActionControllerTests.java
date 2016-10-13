/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.services.web;

import java.util.Collection;

import org.jasig.cas.services.DefaultServicesManagerImpl;
import org.jasig.cas.services.InMemoryServiceRegistryDaoImpl;
import org.jasig.cas.services.RegisteredServiceImpl;
import org.jasig.cas.services.ServicesManager;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 *
 */
public class ManageRegisteredServicesMultiActionControllerTests extends
    TestCase {
    
    private ManageRegisteredServicesMultiActionController controller;
    
    private ServicesManager servicesManager;

    protected void setUp() throws Exception {
        this.servicesManager = new DefaultServicesManagerImpl(new InMemoryServiceRegistryDaoImpl());
        this.controller = new ManageRegisteredServicesMultiActionController(this.servicesManager);
    }
    
    public void testDeleteService() {
        final RegisteredServiceImpl r = new RegisteredServiceImpl();
        r.setId(1200);
        r.setName("name");
        r.setServiceId("serviceId");
        r.setEvaluationOrder(1);
        
        this.servicesManager.save(r);
        
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("id", "1200");
        
        final ModelAndView modelAndView = this.controller.deleteRegisteredService(request, new MockHttpServletResponse());
        
        assertNotNull(modelAndView);
        assertNull(this.servicesManager.findServiceBy(1200));
        assertEquals("deleted", modelAndView.getModel().get("status"));
        assertEquals("name", modelAndView.getModelMap().get("serviceName"));
    }
    
    public void testDeleteServiceNoService() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("id", "1200");
        
        final ModelAndView modelAndView = this.controller.deleteRegisteredService(request, new MockHttpServletResponse());
        
        assertNotNull(modelAndView);
        assertNull(this.servicesManager.findServiceBy(1200));
        assertEquals("deleted", modelAndView.getModel().get("status"));
        assertEquals("", modelAndView.getModelMap().get("serviceName"));
    }
    
    public void testManage() {
        final RegisteredServiceImpl r = new RegisteredServiceImpl();
        r.setId(1200);
        r.setName("name");
        r.setServiceId("test");
        r.setEvaluationOrder(2);
        
        this.servicesManager.save(r);
        
        final ModelAndView modelAndView = this.controller.manage(new MockHttpServletRequest(), new MockHttpServletResponse());
        
        assertNotNull(modelAndView);
        assertEquals("manageServiceView", modelAndView.getViewName());
        
        final Collection c = (Collection) modelAndView.getModel().get("services");
        assertTrue(c.contains(r));
    }
}
