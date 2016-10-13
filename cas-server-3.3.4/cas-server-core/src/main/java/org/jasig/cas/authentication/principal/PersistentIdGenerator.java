/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.authentication.principal;

/**
 * Generates a unique consistant Id based on the principal, a service, and some
 * algorithm.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2007/04/20 19:39:31 $
 * @since 3.1
 */
public interface PersistentIdGenerator {

    /**
     * Generates a PersistentId based on some algorithm plus the principal and
     * service.
     * 
     * @param principal the principal to generate the id for.
     * @param service the service to generate the id for.
     * @return the generated persistent id.
     */
    String generate(Principal principal, Service service);
}
