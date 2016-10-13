/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.cas.adaptors;
/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.AuthenticationHandler;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

/**
 * <p>
 * Replica de org.acegisecurity.adapters.cas3.CasAuthenticationHandler de Acegi
 * Security adaptada a Spring Security para delegar la autenticacion contra un
 * LDAP en las clases de Spring.
 * </p>
 * 
 * @author fjespino
 * 
 * @see AuthenticationHandler
 * @see AuthenticationManager
 */
public final class CasAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
   //~ Instance fields ================================================================================================

   private AuthenticationManager authenticationManager;
   private Log log = LogFactory.getLog(this.getClass());
//   private Logger log= Logger.getLogger(this.getClass());

   //~ Methods ========================================================================================================

   protected void afterPropertiesSetInternal() throws Exception {
       Assert.notNull(this.authenticationManager, "authenticationManager cannot be null.");
   }

   @Override
   protected boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials)
       throws AuthenticationException {
       final Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
               credentials.getPassword());

       if (log.isDebugEnabled()) {
           log.debug("Attempting to authenticate for user: " + credentials.getUsername());
       }

       try {
           this.authenticationManager.authenticate(authenticationRequest);
       } catch (final org.springframework.security.AuthenticationException e) {
           if (log.isDebugEnabled()) {
               log.debug("Authentication request for " + credentials.getUsername() + "failed: " + e.toString());
           }

           return false;
       }

       if (log.isDebugEnabled()) {
           log.debug("Authentication request for " + credentials.getUsername() + " successful.");
       }

       return true;
   }

   /**
    * Method to set the Acegi <code>AuthenticationManager</code> to delegate to.
    *
    * @param authenticationManager the Acegi AuthenticationManager that knows how to authenticate users.
    */
   public void setAuthenticationManager(final AuthenticationManager authenticationManager) {
       this.authenticationManager = authenticationManager;
   }
}
