/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.validation;

/**
 * Base validation specification for the CAS protocol. This specification checks
 * for the presence of renew=true and if requested, succeeds only if ticket
 * validation is occurring from a new login.
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public abstract class AbstractCasProtocolValidationSpecification implements
    ValidationSpecification {

    /** The default value for the renew attribute is false. */
    private static final boolean DEFAULT_RENEW = false;

    /** Denotes whether we should always authenticate or not. */
    private boolean renew;

    public AbstractCasProtocolValidationSpecification() {
        this.renew = DEFAULT_RENEW;
    }

    public AbstractCasProtocolValidationSpecification(final boolean renew) {
        this.renew = renew;
    }

    /**
     * Method to set the renew requirement.
     * 
     * @param renew The renew value we want.
     */
    public final void setRenew(final boolean renew) {
        this.renew = renew;
    }

    /**
     * Method to determine if we require renew to be true.
     * 
     * @return true if renew is required, false otherwise.
     */
    public final boolean isRenew() {
        return this.renew;
    }

    public final boolean isSatisfiedBy(final Assertion assertion) {
        return isSatisfiedByInternal(assertion)
            && ((!this.renew) || (assertion.isFromNewLogin() && this.renew));
    }

    /**
     * Template method to allow for additional checks by subclassed methods
     * without needing to call super.isSatisfiedBy(...).
     */
    protected abstract boolean isSatisfiedByInternal(final Assertion assertion);
}
