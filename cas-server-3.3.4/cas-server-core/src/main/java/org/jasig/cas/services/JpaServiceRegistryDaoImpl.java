/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.services;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * Implementation of the ServiceRegistryDao based on JPA.
 * 
 * @author Scott Battaglia
 * @version $Revision: 43407 $ $Date: 2008-03-25 09:54:08 -0400 (Tue, 25 Mar 2008) $
 * @since 3.1
 */
public final class JpaServiceRegistryDaoImpl extends JpaDaoSupport implements
    ServiceRegistryDao {

    public boolean delete(final RegisteredService registeredService) {
        getJpaTemplate().remove(
            getJpaTemplate().contains(registeredService) ? registeredService
                : getJpaTemplate().merge(registeredService));
        return true;
    }

    public List<RegisteredService> load() {
        return getJpaTemplate().find("select r from RegisteredServiceImpl r");
    }

    public RegisteredService save(final RegisteredService registeredService) {
        final boolean isNew = registeredService.getId() == -1;

        final RegisteredService r = getJpaTemplate().merge(registeredService);
        
        if (!isNew) {
            getJpaTemplate().persist(r);
        }
        
        return r;
    }

    public RegisteredService findServiceById(final long id) {
        return getJpaTemplate().find(RegisteredServiceImpl.class, id);
    }
}