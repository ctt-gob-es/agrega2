/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package org.jasig.cas.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.validation.Assertion;

/**
 * Custom View to Return the CAS 1.0 Protocol Response. Implemented as a view
 * class rather than a JSP (like CAS 2.0 spec) because of the requirement of the
 * line feeds to be "\n".
 * 
 * @author Scott Battaglia
 * @version $Revision: 42053 $ $Date: 2007-06-10 09:17:55 -0400 (Sun, 10 Jun 2007) $
 * @since 3.0
 */
public final class Cas10ResponseView extends AbstractCasView {

    /**
     * Indicate whether this view will be generating the success response or
     * not.
     */
    private boolean successResponse;

    protected void renderMergedOutputModel(final Map model,
        final HttpServletRequest request, final HttpServletResponse response)
        throws Exception {
        final Assertion assertion = getAssertionFrom(model);

        if (this.successResponse) {
            response.getWriter().print(
                "yes\n"
                    + assertion.getChainedAuthentications().get(0).getPrincipal()
                        .getId() + "\n");
        } else {
            response.getWriter().print("no\n\n");
        }
    }

    public void setSuccessResponse(final boolean successResponse) {
        this.successResponse = successResponse;
    }
}
