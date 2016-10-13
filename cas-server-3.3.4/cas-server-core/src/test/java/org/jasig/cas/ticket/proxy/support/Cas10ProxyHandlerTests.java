/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket.proxy.support;

import org.jasig.cas.TestUtils;
import org.jasig.cas.ticket.proxy.ProxyHandler;
import org.jasig.cas.ticket.proxy.support.Cas10ProxyHandler;
import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 39552 $ Date$
 * @since 3.0
 */
public class Cas10ProxyHandlerTests extends TestCase {

    private ProxyHandler proxyHandler = new Cas10ProxyHandler();

    public void testNoCredentialsOrProxy() {
        assertNull(this.proxyHandler.handle(null, null));
    }

    public void testCredentialsAndProxy() {
        assertNull(this.proxyHandler.handle(TestUtils
            .getCredentialsWithSameUsernameAndPassword(), "test"));
    }
}
