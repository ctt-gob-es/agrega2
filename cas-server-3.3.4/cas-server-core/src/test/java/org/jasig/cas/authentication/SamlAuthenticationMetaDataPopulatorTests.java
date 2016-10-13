/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.authentication;

import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.TestUtils;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.opensaml.SAMLAuthenticationStatement;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 *
 */
public class SamlAuthenticationMetaDataPopulatorTests extends TestCase {

    private SamlAuthenticationMetaDataPopulator populator;

    protected void setUp() throws Exception {
        this.populator = new SamlAuthenticationMetaDataPopulator();
        super.setUp();
    }
    
    public void testAuthenticationTypeFound() {
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
        final MutableAuthentication ma = new MutableAuthentication(TestUtils.getPrincipal());
        
        final Authentication m2 = this.populator.populateAttributes(ma, credentials);
        
        assertEquals(m2.getAttributes().get("samlAuthenticationStatementAuthMethod"), SAMLAuthenticationStatement.AuthenticationMethod_Password);
    }
    
    public void testAuthenticationTypeNotFound() {
        final CustomCredentials credentials = new CustomCredentials();
        final MutableAuthentication ma = new MutableAuthentication(TestUtils.getPrincipal());
        
        final Authentication m2 = this.populator.populateAttributes(ma, credentials);
        
        assertNull(m2.getAttributes().get("samlAuthenticationStatementAuthMethod"));
    }
    
    public void testAuthenticationTypeFoundCustom() {
        final CustomCredentials credentials = new CustomCredentials();
        
        final Map<String, String> added = new HashMap<String, String>();
        added.put(CustomCredentials.class.getName(), "FF");
        
        this.populator.setUserDefinedMappings(added);
        
        final MutableAuthentication ma = new MutableAuthentication(TestUtils.getPrincipal());
        
        final Authentication m2 = this.populator.populateAttributes(ma, credentials);
        
        assertEquals("FF", m2.getAttributes().get("samlAuthenticationStatementAuthMethod"));
    }
    
    protected class CustomCredentials implements Credentials {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = -3387599342233073148L;
        // nothing to do
    }
}
