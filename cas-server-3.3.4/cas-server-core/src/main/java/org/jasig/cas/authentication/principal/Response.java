/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.uportal.org/license.html
 */
package org.jasig.cas.authentication.principal;

import java.net.URLEncoder;
import java.util.Map;

/**
 * Encapsulates a Response to send back for a particular service.
 * 
 * @author Scott Battaglia
 * @author Arnaud Lesueur
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.1
 */
public final class Response {

    public static enum ResponseType {
        POST, REDIRECT
    }

    private final ResponseType responseType;

    private final String url;

    private final Map<String, String> attributes;

    protected Response(ResponseType responseType, final String url,
        final Map<String, String> attributes) {
        this.responseType = responseType;
        this.url = url;
        this.attributes = attributes;
    }

    public static Response getPostResponse(final String url,
        final Map<String, String> attributes) {
        return new Response(ResponseType.POST, url, attributes);
    }

    public static Response getRedirectResponse(final String url,
        final Map<String, String> parameters) {
        final StringBuilder builder = new StringBuilder(
            parameters.size() * 40 + 100);
        boolean isFirst = true;
        
        builder.append(url);
        
        for (final Map.Entry<String, String> entry : parameters.entrySet()) {
            if (entry.getValue() != null) {
                if (isFirst) {
                    builder.append(url.contains("?") ? "&" : "?");
                    isFirst = false;
                } else {
                    builder.append("&");   
                }
                builder.append(entry.getKey());
                builder.append("=");

                try {
                    builder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                } catch (final Exception e) {
                    builder.append(entry.getValue());
                }
            }
        }

        return new Response(ResponseType.REDIRECT, builder.toString(), parameters);
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }

    public String getUrl() {
        return this.url;
    }
}
