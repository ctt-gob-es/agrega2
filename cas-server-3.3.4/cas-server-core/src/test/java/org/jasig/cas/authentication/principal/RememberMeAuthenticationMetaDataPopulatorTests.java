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

import org.jasig.cas.TestUtils;
import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.MutableAuthentication;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.2.1
 *
 */
public class RememberMeAuthenticationMetaDataPopulatorTests extends TestCase {
    
    private RememberMeAuthenticationMetaDataPopulator p  = new RememberMeAuthenticationMetaDataPopulator();

    public void testWithTrueRememberMeCredentials() {
        final Authentication auth = new MutableAuthentication(TestUtils.getPrincipal());
        final RememberMeUsernamePasswordCredentials c = new RememberMeUsernamePasswordCredentials();
        c.setRememberMe(true);
        
        final Authentication auth2 = this.p.populateAttributes(auth, c);
        
        assertEquals(Boolean.TRUE, auth2.getAttributes().get(RememberMeCredentials.AUTHENTICATION_ATTRIBUTE_REMEMBER_ME));
    }
    
    public void testWithFalseRememberMeCredentials() {
        final Authentication auth = new MutableAuthentication(TestUtils.getPrincipal());
        final RememberMeUsernamePasswordCredentials c = new RememberMeUsernamePasswordCredentials();
        c.setRememberMe(false);
        
        final Authentication auth2 = this.p.populateAttributes(auth, c);
        
        assertNull(auth2.getAttributes().get(RememberMeCredentials.AUTHENTICATION_ATTRIBUTE_REMEMBER_ME));
    }

    
    public void testWithoutRememberMeCredentials() {
        final Authentication auth = new MutableAuthentication(TestUtils.getPrincipal());        
        final Authentication auth2 = this.p.populateAttributes(auth, TestUtils.getCredentialsWithSameUsernameAndPassword());
        
        assertNull(auth2.getAttributes().get(RememberMeCredentials.AUTHENTICATION_ATTRIBUTE_REMEMBER_ME));
    }
    

}
