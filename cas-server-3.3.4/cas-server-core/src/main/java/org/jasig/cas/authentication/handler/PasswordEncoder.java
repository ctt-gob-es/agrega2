/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.authentication.handler;

/**
 * Interface to provide a standard way to translate a plaintext password into a
 * different representation of that password so that the password may be
 * compared with the stored encrypted password without having to decode the
 * encrypted password.
 * <p>
 * PasswordEncoders are useful because often the stored passwords are encoded
 * with a one way hash function which makes them almost impossible to decode.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 * <p>
 * This is a published and supported CAS Server 3 API.
 * </p>
 */
public interface PasswordEncoder {

    /**
     * Method that actually performs the transformation of the plaintext
     * password into the encrypted password.
     * 
     * @param password the password to translate
     * @return the transformed version of the password
     */
    String encode(String password);
}
