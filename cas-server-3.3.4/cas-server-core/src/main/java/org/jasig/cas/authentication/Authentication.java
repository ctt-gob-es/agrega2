/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.authentication;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.jasig.cas.authentication.principal.Principal;

/**
 * <p>
 * The Authentication object represents a successful authentication request. It
 * contains the principal that the authentication request was made for as well
 * as the additional meta information such as the authenticated date and a map
 * of attributes.
 * </p>
 * <p>
 * An Authentication object must be serializable to permit persistance and
 * clustering.
 * </p>
 * <p>
 * Implementing classes must take care to ensure that the Map returned by
 * getAttributes is serializable by using a Serializable map such as HashMap.
 * </p>
 * 
 * @author Dmitriy Kopylenko
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface Authentication extends Serializable {

    /**
     * Method to obtain the Principal.
     * 
     * @return a Principal implementation
     */
    Principal getPrincipal();

    /**
     * Method to retrieve the timestamp of when this Authentication object was
     * created.
     * 
     * @return the date/time the authentication occurred.
     */
    Date getAuthenticatedDate();

    /**
     * Attributes of the authentication (not the Principal).
     * 
     * @return the map of attributes.
     */
    Map<String, Object> getAttributes();
}