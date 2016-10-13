/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.authentication;

import java.util.Date;
import java.util.HashMap;

import org.jasig.cas.authentication.principal.Principal;

/**
 * Mutable implementation of Authentication interface.
 * <p>
 * Instanciators of the MutableAuthentication class must take care that the map
 * they provide is serializable (i.e. HashMap).
 * 
 * @author Scott Battaglia
 * @version $Revision: 43397 $ $Date: 2008-03-19 11:12:43 -0400 (Wed, 19 Mar 2008) $
 * @since 3.0.3
 */
public final class MutableAuthentication extends AbstractAuthentication {

    /** Unique Id for serialization. */
    private static final long serialVersionUID = -4415875344376642246L;

    /** The date/time this authentication object became valid. */
    private final Date authenticatedDate;

    public MutableAuthentication(final Principal principal) {
        this(principal, new Date());
    }
    
    public MutableAuthentication(final Principal principal, final Date date) {
        super(principal, new HashMap<String, Object>());
        this.authenticatedDate = date;
    }

    public Date getAuthenticatedDate() {
        return this.authenticatedDate;
    }
}
