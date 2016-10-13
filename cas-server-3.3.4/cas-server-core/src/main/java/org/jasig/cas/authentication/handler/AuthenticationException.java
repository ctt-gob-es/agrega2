/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.authentication.handler;

/**
 * The most generic type of authentication exception that one can catch if not
 * sure what specific implementation will be thrown. Top of the tree of all
 * other AuthenticationExceptions.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public abstract class AuthenticationException extends Exception {

    /** Serializable ID. */
    private static final long serialVersionUID = 3906648604830611762L;

    /** The code to return for resolving to a message description. */
    private String code;

    /**
     * Constructor that takes a code description of the error. These codes
     * normally have a corresponding entries in the messages file for the
     * internationalization of error messages.
     * 
     * @param code The short unique identifier for this error.
     */
    public AuthenticationException(final String code) {
        this.code = code;
    }

    /**
     * Constructor that takes a code description of the error and the chained
     * exception. These codes normally have a corresponding entries in the
     * messages file for the internationalization of error messages.
     * 
     * @param code The short unique identifier for this error.
     * @param throwable The chained exception for this AuthenticationException
     */
    public AuthenticationException(final String code, final Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    /**
     * Method to return the unique identifier for this error type.
     * 
     * @return the String identifier for this error type.
     */
    public final String getCode() {
        return this.code;
    }

    public final String toString() {
        return getCode();
    }
}
