/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.web.support;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.jasig.cas.util.PrivateKeyFactoryBean;
import org.jasig.cas.util.PublicKeyFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 *
 */
public class GoogleAccountsArgumentExtractorTests extends TestCase {
    
    private GoogleAccountsArgumentExtractor extractor;

    protected void setUp() throws Exception {
        final PublicKeyFactoryBean pubKeyFactoryBean = new PublicKeyFactoryBean();
        final PrivateKeyFactoryBean privKeyFactoryBean = new PrivateKeyFactoryBean();
        
        pubKeyFactoryBean.setAlgorithm("DSA");
        privKeyFactoryBean.setAlgorithm("DSA");
        
        final ClassPathResource pubKeyResource = new ClassPathResource("DSAPublicKey01.key");
        final ClassPathResource privKeyResource = new ClassPathResource("DSAPrivateKey01.key");
        
        pubKeyFactoryBean.setLocation(pubKeyResource);
        privKeyFactoryBean.setLocation(privKeyResource);
        assertTrue(privKeyFactoryBean.getObjectType().equals(PrivateKey.class));
        assertTrue(pubKeyFactoryBean.getObjectType().equals(PublicKey.class));
        pubKeyFactoryBean.afterPropertiesSet();
        privKeyFactoryBean.afterPropertiesSet();
        
        this.extractor = new GoogleAccountsArgumentExtractor();
        this.extractor.setPrivateKey((PrivateKey) privKeyFactoryBean.getObject());
        this.extractor.setPublicKey((PublicKey) pubKeyFactoryBean.getObject());

        super.setUp();
    }
    
    public void testNoService() {
        assertNull(this.extractor.extractService(new MockHttpServletRequest()));
    }
}
