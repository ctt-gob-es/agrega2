/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.support;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.authentication.principal.SamlService;
import org.jasig.cas.authentication.principal.WebApplicationService;

/**
 * Retrieve the ticket and artifact based on the SAML 1.1 profile.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42999 $ $Date: 2008-02-05 14:37:28 -0500 (Tue, 05 Feb 2008) $
 * @since 3.1
 */
public final class SamlArgumentExtractor extends AbstractSingleSignOutEnabledArgumentExtractor {

    public WebApplicationService extractServiceInternal(final HttpServletRequest request) {
        return SamlService.createServiceFrom(request, getHttpClientIfSingleSignOutEnabled());
    }
}
