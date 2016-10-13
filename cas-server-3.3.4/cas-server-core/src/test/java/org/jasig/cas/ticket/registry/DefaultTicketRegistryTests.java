/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.registry;

/**
 * Test case to test the DefaultTicketRegistry based on test cases to test all
 * Ticket Registries.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42030 $ $Date: 2007-06-04 12:13:32 -0400 (Mon, 04 Jun 2007) $
 * @since 3.0
 */
public class DefaultTicketRegistryTests extends AbstractTicketRegistryTests {

    public TicketRegistry getNewTicketRegistry() throws Exception {
        return new DefaultTicketRegistry();
    }
    
    public void testOtherConstructor() {
        assertNotNull(new DefaultTicketRegistry(10, 10F, 5));
    }
}
