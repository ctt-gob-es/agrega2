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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.authentication.principal.WebApplicationService;
import org.jasig.cas.util.HttpClient;

/**
 * Abstract class for handling the enabling and disabling of Single Sign Out features.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1.2
 *
 */
public abstract class AbstractSingleSignOutEnabledArgumentExtractor implements
    ArgumentExtractor {
    
    private final Log log = LogFactory.getLog(getClass());
//    private final Logger log= Logger.getLogger(this.getClass());

    /** Whether single sign out is disabled or not. */
    private boolean disableSingleSignOut = false;
    
    /** Default instance of HttpClient. */
    @NotNull
    private HttpClient httpClient;
    
    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    
    protected HttpClient getHttpClientIfSingleSignOutEnabled() {
        return !this.disableSingleSignOut ? this.httpClient : null; 
    }
    
    public void setDisableSingleSignOut(final boolean disableSingleSignOut) {
        this.disableSingleSignOut = disableSingleSignOut;
    }
    
    public final WebApplicationService extractService(final HttpServletRequest request) {
        final WebApplicationService service = extractServiceInternal(request);
        
        if (service == null) {
            log.debug("Extractor did not generate service.");
        } else {
            log.debug("Extractor generated service for: " + service.getId());
        }
        
        return service;
    }
    
    protected abstract WebApplicationService extractServiceInternal(final HttpServletRequest request);
}
