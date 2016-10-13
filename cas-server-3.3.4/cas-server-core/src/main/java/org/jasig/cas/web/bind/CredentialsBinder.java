/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.bind;

import javax.servlet.http.HttpServletRequest;
import org.jasig.cas.authentication.principal.Credentials;

/**
 * Interface for a class that can bind items stored in the request to a
 * particular credentials implementation. This allows for binding beyond the
 * basic JavaBean/Request parameter binding that is handled by Spring
 * automatically. Implementations are free to pass part or all of the
 * HttpServletRequest to the Credentials.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface CredentialsBinder {

    /**
     * Method to allow manually binding attributes from the request object to
     * properties of the credentials. Useful when there is no mapping of
     * attribute to property for the usual Spring binding to handle.
     * 
     * @param request The HttpServletRequest from which we wish to bind
     * credentials to
     * @param credentials The credentials we will be doing custom binding to.
     */
    void bind(HttpServletRequest request, Credentials credentials);

    /**
     * Method to determine if a CredentialsBinder supports a specific class or
     * not.
     * 
     * @param clazz The class to determine is supported or not
     * @return true if this class is supported by the CredentialsBinder, false
     * otherwise.
     */
    boolean supports(Class<?> clazz);
}
