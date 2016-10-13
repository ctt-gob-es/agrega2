/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.registry.support;

import org.jasig.cas.ticket.registry.AbstractRegistryCleanerTests;
import org.jasig.cas.ticket.registry.DefaultTicketRegistry;
import org.jasig.cas.ticket.registry.RegistryCleaner;
import org.jasig.cas.ticket.registry.TicketRegistry;

/**
 * @author Scott Battaglia
 * @version $Revision: 39690 $ $Date: 2007-04-07 14:35:03 -0400 (Sat, 07 Apr 2007) $
 * @since 3.0
 */
public class DefaultTicketRegistryCleanerTests extends
    AbstractRegistryCleanerTests {

    public RegistryCleaner getNewRegistryCleaner(
        final TicketRegistry ticketRegistry) {
        DefaultTicketRegistryCleaner cleaner = new DefaultTicketRegistryCleaner();
        cleaner.setTicketRegistry(ticketRegistry);

        return cleaner;
    }

    public TicketRegistry getNewTicketRegistry() {
        return new DefaultTicketRegistry();
    }
}
