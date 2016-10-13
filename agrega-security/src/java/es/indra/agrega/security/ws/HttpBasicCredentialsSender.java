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
