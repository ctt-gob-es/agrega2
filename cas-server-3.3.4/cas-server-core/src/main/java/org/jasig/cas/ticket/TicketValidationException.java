/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.ticket;

import org.jasig.cas.authentication.principal.Service;

/**
 * Exception to alert that there was an error validating the ticket.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42230 $ $Date: 2007-07-18 09:20:06 -0400 (Wed, 18 Jul 2007) $
 * @since 3.0
 */
public class TicketValidationException extends TicketException {

    /** Unique Serial ID. */
    private static final long serialVersionUID = 3257004341537093175L;

    /** The code description. */
    private static final String CODE = "INVALID_SERVICE";
    
    private final Service service;

    /**
     * Constructs a TicketValidationException with the default exception code
     * and the original exception that was thrown.
     * 
     * @param throwable the chained exception
     */
    public TicketValidationException(final Service service) {
        super(CODE);
        this.service = service;
    }
    
    public Service getOriginalService() {
        return this.service;
    }

}
