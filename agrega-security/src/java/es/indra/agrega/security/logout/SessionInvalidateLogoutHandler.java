/**
 * 
 */
package es.indra.agrega.security.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.ui.logout.LogoutHandler;

/**
 * @author jlhuertas
 *
 */
public class SessionInvalidateLogoutHandler implements LogoutHandler {

	/**
	 * Invalidates the HTTP session
	 */
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		request.getSession().invalidate();
	}

}
