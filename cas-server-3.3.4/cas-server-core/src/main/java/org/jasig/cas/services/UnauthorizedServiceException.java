/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.services;

/**
 * Exception that is thrown when an Unauthorized Service attempts to use CAS.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public class UnauthorizedServiceException extends RuntimeException {

    /** The Unique ID for serialization. */
    private static final long serialVersionUID = 3905807495715960369L;
    
    /** The code description. */
    private static final String CODE = "service.not.authorized";

    public UnauthorizedServiceException() {
        this(CODE);
    }
    
    
    /**
     * Constructs an UnauthorizedServiceException with a custom message and the
     * root cause of this exception.
     * 
     * @param message an explanatory message.
     * @param cause the root cause of the exception.
     */
    public UnauthorizedServiceException(final String message,
        final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an exception with a custom message.
     * 
     * @param message an explanatory message.
     */
    public UnauthorizedServiceException(final String message) {
        super(message);
    }
}
