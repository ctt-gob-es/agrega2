/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2004 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.validation;

import org.jasig.cas.TestUtils;

import org.jasig.cas.validation.Cas20ProtocolValidationSpecification;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 39552 $ $Date: 2007-01-22 15:35:37 -0500 (Mon, 22 Jan 2007) $
 * @since 3.0
 */
public class Cas20ProtocolValidationSpecificationTests extends TestCase {

    public void testRenewGettersAndSettersFalse() {
        Cas20ProtocolValidationSpecification s = new Cas20ProtocolValidationSpecification();
        s.setRenew(false);
        assertFalse(s.isRenew());
    }

    public void testRenewGettersAndSettersTrue() {
        Cas20ProtocolValidationSpecification s = new Cas20ProtocolValidationSpecification();
        s.setRenew(true);
        assertTrue(s.isRenew());
    }

    public void testRenewAsTrueAsConstructor() {
        assertTrue(new Cas20ProtocolValidationSpecification(true).isRenew());
    }

    public void testRenewAsFalseAsConstructor() {
        assertFalse(new Cas20ProtocolValidationSpecification(false).isRenew());
    }

    public void testSatisfiesSpecOfTrue() {
        assertTrue(new Cas20ProtocolValidationSpecification(true)
            .isSatisfiedBy(TestUtils.getAssertion(true)));
    }

    public void testNotSatisfiesSpecOfTrue() {
        assertFalse(new Cas20ProtocolValidationSpecification(true)
            .isSatisfiedBy(TestUtils.getAssertion(false)));
    }

    public void testSatisfiesSpecOfFalse() {
        assertTrue(new Cas20ProtocolValidationSpecification(false)
            .isSatisfiedBy(TestUtils.getAssertion(true)));
    }

    public void testSatisfiesSpecOfFalse2() {
        assertTrue(new Cas20ProtocolValidationSpecification(false)
            .isSatisfiedBy(TestUtils.getAssertion(false)));
    }
}
