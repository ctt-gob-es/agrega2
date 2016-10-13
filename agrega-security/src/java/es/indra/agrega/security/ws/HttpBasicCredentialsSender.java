/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.ws;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

public class HttpBasicCredentialsSender  extends BasicHandler {

	public void invoke(MessageContext msgContext) throws AxisFault {
		try {
			Authentication currentAuth= SecurityContextHolder.getContext().getAuthentication();
			
			if (currentAuth != null){
				String username = "";
				String password = "";
				Object principal = currentAuth.getPrincipal();

				if ( principal instanceof UserDetails) {
					username = ((UserDetails) principal).getUsername();
					password = ((UserDetails) principal).getPassword();
				} else {
					username = principal.toString();
					password = currentAuth.getCredentials().toString();
				}
				
				msgContext.setUsername(username);
				msgContext.setPassword(password);
			}
		} catch (Exception e) {
			throw AxisFault.makeFault(e);
		}
	}
}
