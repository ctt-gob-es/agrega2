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
 * Default implementation of {@link UniqueTicketIdGenerator}. Implementation
 * utilizes a DefaultLongNumericGeneraor and a DefaultRandomStringGenerator to
 * construct the ticket id.
 * <p>
 * Tickets are of the form [PREFIX]-[SEQUENCE NUMBER]-[RANDOM STRING]-[SUFFIX]
 * </p>
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public final class DefaultUniqueTicketIdGenerator implements
    UniqueTicketIdGenerator {

    /** The numeric generator to generate the static part of the id. */
    private final NumericGenerator numericGenerator;

    /** The RandomStringGenerator to generate the secure random part of the id. */
    private final RandomStringGenerator randomStringGenerator;

    /**
     * Optional suffix to ensure uniqueness across JVMs by specifying unique
     * values.
     */
    private final String suffix;

    /**
     * Creates an instance of DefaultUniqueTicketIdGenerator with default values
     * including a {@link DefaultLongNumericGenerator} with a starting value of
     * 1.
     */
    public DefaultUniqueTicketIdGenerator() {
        this(null);
    }

    /**
     * Creates an instance of DefaultUniqueTicketIdGenerator with a specified
     * maximum length for the random portion.
     * 
     * @param maxLength the maximum length of the random string used to generate
     * the id.
     */
    public DefaultUniqueTicketIdGenerator(final int maxLength) {
        this(maxLength, null);
    }

    /**
     * Creates an instance of DefaultUniqueTicketIdGenerator with default values
     * including a {@link DefaultLongNumericGenerator} with a starting value of
     * 1.
     * 
     * @param suffix the value to append at the end of the unique id to ensure
     * uniqueness across JVMs.
     */
    public DefaultUniqueTicketIdGenerator(final String suffix) {
        this.numericGenerator = new DefaultLongNumericGenerator(1);
        this.randomStringGenerator = new DefaultRandomStringGenerator();

        if (suffix != null) {
            this.suffix = "-" + suffix;
        } else {
            this.suffix = null;
        }
    }

    /**
     * Creates an instance of DefaultUniqueTicketIdGenerator with a specified
     * maximum length for the random portion.
     * 
     * @param maxLength the maximum length of the random string used to generate
     * the id.
     * @param suffix the value to append at the end of the unique id to ensure
     * uniqueness across JVMs.
     */
    public DefaultUniqueTicketIdGenerator(final int maxLength,
        final String suffix) {
        this.numericGenerator = new DefaultLongNumericGenerator(1);
        this.randomStringGenerator = new DefaultRandomStringGenerator(maxLength);

        if (suffix != null) {
            this.suffix = "-" + suffix;
        } else {
            this.suffix = null;
        }
    }

    public String getNewTicketId(final String prefix) {
        final String number = this.numericGenerator.getNextNumberAsString();
        final StringBuilder buffer = new StringBuilder(prefix.length() + 2
            + (this.suffix != null ? this.suffix.length() : 0) + this.randomStringGenerator.getMaxLength()
            + number.length());

        buffer.append(prefix);
        buffer.append("-");
        buffer.append(number);
        buffer.append("-");
        buffer.append(this.randomStringGenerator.getNewString());

        if (this.suffix != null) {
            buffer.append(this.suffix);
        }

        return buffer.toString();
    }
}
