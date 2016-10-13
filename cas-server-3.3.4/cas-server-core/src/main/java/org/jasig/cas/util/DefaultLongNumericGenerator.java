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

import java.util.concurrent.atomic.AtomicLong;

/**
 * The default numeric generator for generating long values. Implementation
 * allows for wrapping (to restart count) if the maximum is reached.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public final class DefaultLongNumericGenerator implements LongNumericGenerator {

    /** The maximum length the string can be. */
    private static final int MAX_STRING_LENGTH = Long.toString(Long.MAX_VALUE)
        .length();

    /** The minimum length the String can be. */
    private static final int MIN_STRING_LENGTH = 1;

    private final AtomicLong count;

    public DefaultLongNumericGenerator() {
        this(0);
        // nothing to do
    }

    public DefaultLongNumericGenerator(final long initialValue) {
        this.count = new AtomicLong(initialValue);
    }

    public long getNextLong() {
        return this.getNextValue();
    }

    public String getNextNumberAsString() {
        return Long.toString(this.getNextValue());
    }

    public int maxLength() {
        return DefaultLongNumericGenerator.MAX_STRING_LENGTH;
    }

    public int minLength() {
        return DefaultLongNumericGenerator.MIN_STRING_LENGTH;
    }

    protected long getNextValue() {
        if (this.count.compareAndSet(Long.MAX_VALUE, 0)) {
            return Long.MAX_VALUE;
        }
        return this.count.getAndIncrement();
    }
}
