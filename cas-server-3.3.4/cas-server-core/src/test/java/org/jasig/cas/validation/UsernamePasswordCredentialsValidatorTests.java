/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.validation;

import org.jasig.cas.TestUtils;
import org.jasig.cas.authentication.principal.HttpBasedServiceCredentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.jasig.cas.validation.UsernamePasswordCredentialsValidator;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

import junit.framework.TestCase;

/**
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class UsernamePasswordCredentialsValidatorTests extends TestCase {

    private Validator validator = new UsernamePasswordCredentialsValidator();

    public void testSupportsUsernamePasswordCredentials() {
        assertTrue(this.validator.supports(UsernamePasswordCredentials.class));
    }

    public void testNotSupportsBasicHttpServiceCredentials() {
        assertFalse(this.validator.supports(HttpBasedServiceCredentials.class));
    }

    public void testValidationPasses() {
        final UsernamePasswordCredentials c = TestUtils
            .getCredentialsWithSameUsernameAndPassword();
        final BindException b = new BindException(c, "credentials");
        this.validator.validate(c, b);
        assertFalse(b.hasErrors());
    }

    public void testValidationFailsPasswordNull() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            "test", null), 1);
    }

    public void testValidationFailsPasswordBlank() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            "test", ""), 1);
    }

    public void testValidationFailsUsernameNull() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            null, "hello"), 1);
    }

    public void testValidationFailsUsernameBlank() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            "", "hello"), 1);
    }

    public void testValidationFailsUsernameAndPasswordBlank() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            "", ""), 2);
    }

    public void testValidationFailsUsernameAndPasswordNull() {
        commonTests(TestUtils.getCredentialsWithDifferentUsernameAndPassword(
            null, null), 2);
    }

    private void commonTests(final UsernamePasswordCredentials c,
        int errorCountExpected) {
        final BindException b = new BindException(c, "credentials");
        this.validator.validate(c, b);
        assertTrue(b.hasErrors());
        assertEquals(b.getErrorCount(), errorCountExpected);
    }
}
