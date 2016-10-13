/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.services.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.services.RegisteredService;
import org.jasig.cas.services.ServicesManager;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * MultiActionController to handle the deletion of RegisteredServices as well as
 * displaying them on the Manage Services page.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42776 $ $Date: 2008-01-04 09:15:42 -0500 (Fri, 04 Jan 2008) $
 * @since 3.1
 */
public final class ManageRegisteredServicesMultiActionController extends
    MultiActionController {

    /** View name for the Manage Services View. */
    private static final String VIEW_NAME = "manageServiceView";

    /** Instance of ServicesManager. */
    @NotNull
    private final ServicesManager servicesManager;

    /** Used to ensure services are sorted by name. */
    private final PropertyComparator propertyComparator = new PropertyComparator(
        "name", false, true);

    /**
     * Constructor that takes the required {@link ServicesManager}.
     * 
     * @param servicesManager the Services Manager that manages the
     * RegisteredServices.
     */
    public ManageRegisteredServicesMultiActionController(
        final ServicesManager servicesManager) {
        super();
        this.servicesManager = servicesManager;
    }

    /**
     * Method to delete the RegisteredService by its ID.
     * 
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @return the Model and View to go to after the service is deleted.
     */
    public ModelAndView deleteRegisteredService(
        final HttpServletRequest request, final HttpServletResponse response) {
        final String id = request.getParameter("id");
        final long idAsLong = Long.parseLong(id);

        final ModelAndView modelAndView = new ModelAndView(new RedirectView(
            "/services/manage.html", true), "status", "deleted");


        final RegisteredService r = this.servicesManager.delete(idAsLong);

        modelAndView.addObject("serviceName", r != null
            ? r.getName() : "");

        return modelAndView;
    }

    /**
     * Method to show the RegisteredServices.
     * 
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @return the Model and View to go to after the services are loaded.
     */
    public ModelAndView manage(final HttpServletRequest request,
        final HttpServletResponse response) {
        final Map<String, Object> model = new HashMap<String, Object>();

        final List<RegisteredService> services = new ArrayList<RegisteredService>(
            this.servicesManager.getAllServices());
        PropertyComparator.sort(services, this.propertyComparator
            .getSortDefinition());

        model.put("services", services);
        model.put("pageTitle", VIEW_NAME);

        return new ModelAndView(VIEW_NAME, model);
    }
}