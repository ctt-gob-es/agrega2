package es.pode.soporte.seguridad.ldap;

import java.util.Arrays;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

/**
 * 
 * Esta clase supone una modificacion de LdapAuthenticationProvider de acegi, para que se construya 
 * el user details personalizado de Agrega
 * 
 * */
public class LdapAuthenticationProviderAgrega extends AbstractUserDetailsAuthenticationProvider {

    //~ Static fields/initializers =====================================================================================

    private static final Logger logger = Logger.getLogger(LdapAuthenticationProvider.class);

    //~ Instance fields ================================================================================================

    private LdapAuthenticator authenticator;
    private LdapAuthoritiesPopulator authoritiesPopulator;

    //~ Constructors ===================================================================================================

    public LdapAuthenticationProviderAgrega(LdapAuthenticator authenticator, LdapAuthoritiesPopulator authoritiesPopulator) {
        log("authenticator: " + authenticator);
        log("authoritiesPopulator: " + authoritiesPopulator);

        this.authenticator = authenticator;
        this.authoritiesPopulator = authoritiesPopulator;
    }

    //~ Methods ========================================================================================================

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
    	log("additionalAuthenticationChecks UserDetails: " + userDetails + " UsernamePasswordAuthenticationToken: " + authentication);
        if (!userDetails.getPassword().equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), userDetails);
        }
    }

    /**
     * Creates the final <tt>UserDetails</tt> object that will be returned by the provider once the user has
     * been authenticated.<p>The <tt>LdapAuthoritiesPopulator</tt> will be used to create the granted
     * authorites for the user.</p>
     *  <p>Can be overridden to customize the creation of the final UserDetails instance. The default will
     * merge any additional authorities retrieved from the populator with the propertis of original <tt>ldapUser</tt>
     * object and set the values of the username and password.</p>
     *
     * @param ldapUser The intermediate LdapUserDetails instance returned by the authenticator.
     * @param username the username submitted to the provider
     * @param password the password submitted to the provider
     *
     * @return The UserDetails for the successfully authenticated user.
     */
    protected UserDetails createUserDetails(LdapUserDetails ldapUser, String username, String password) {
    	
    	username = username.toLowerCase();
    	es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega.Essence user = new es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega.Essence(ldapUser);
    	log("Creción del Essence del user " + user);
        org.acegisecurity.GrantedAuthority authorities[] = authoritiesPopulator.getGrantedAuthorities(ldapUser);
        log("Autorizaciones " + Arrays.toString(authorities));
        user.setUsername(username);
        user.setPassword(password);        
        user.setAuthorities(authorities,user.instance.getUsername(),user.instance.getPassword());
        UserDetails ud = user.createUserDetails();
        log("UserDetail creado " + ud);
        return ud;   	

    }

    protected LdapAuthoritiesPopulator getAuthoritiesPoulator() {
    	log("getAuthoritiesPoulator: " + authoritiesPopulator);
        return authoritiesPopulator;
    }

    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        if (!StringUtils.hasLength(username)) {
            throw new BadCredentialsException(messages.getMessage("LdapAuthenticationProvider.emptyUsername",
                    "Empty Username"));
        }

        log("Recuperando el usuario " + username);
        
        String password = (String) authentication.getCredentials();        
 
        if (password.length() == 0) {
            	log("Rechazo de clave vacia para el usuario " + username);
            throw new BadCredentialsException(messages.getMessage("LdapAuthenticationProvider.emptyPassword",
                    "Empty Password"));
        }

        try {
            LdapUserDetails ldapUser = authenticator.authenticate(username, password);
            log("Usuario LDAP " + ldapUser);
            return createUserDetails(ldapUser, username, password);
        } 
        catch (DataAccessException ldapAccessFailure) {
            throw new AuthenticationServiceException(ldapAccessFailure.getMessage(), ldapAccessFailure);
        }
    }
    
	private static void log (Object obj)
	{
		if (logger.isDebugEnabled())
			logger.debug(obj);
	}
}
