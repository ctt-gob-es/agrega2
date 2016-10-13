/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.util;

/**
 * Interface to return a random String.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42223 $ $Date: 2007-07-10 11:41:31 -0400 (Tue, 10 Jul 2007) $
 * @since 3.0
 */
public interface RandomStringGenerator {

    /**
     * @return the minimum length as an int guaranteed by this generator.
     */
    int getMinLength();

    /**
     * @return the maximum length as an int guaranteed by this generator.
     */
    int getMaxLength();

    /**
     * @return the new random string
     */
    String getNewString();
    
    byte[] getNewStringAsBytes();
}
