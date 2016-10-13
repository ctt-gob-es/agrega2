/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.util;

/**
 * Interface to return a new sequential number for each call.
 * 
 * @author Scott Battaglia
 * @version $Revision: 44358 $ $Date: 2008-11-10 11:10:16 -0500 (Mon, 10 Nov 2008) $
 * @since 3.0
 */
public interface NumericGenerator {

    /**
     * Method to retrieve the next number as a String.
     * 
     * @return the String representation of the next number in the sequence
     */
    String getNextNumberAsString();

    /**
     * The guaranteed maximum length of a String returned by this generator.
     * 
     * @return the maximum length
     */
    int maxLength();

    /**
     * The guaranteed minimum length of a String returned by this generator.
     * 
     * @return the minimum length.
     */
    int minLength();
}
