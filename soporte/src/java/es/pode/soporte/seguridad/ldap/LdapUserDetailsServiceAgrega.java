/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.seguridad.ldap;

import org.acegisecurity.ldap.LdapUserSearch;
import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetailsImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class LdapUserDetailsServiceAgrega implements UserDetailsService, InitializingBean{
 
	private static final Logger logger = Logger.getLogger(LdapAuthenticationProvider.class);
	
	public LdapUserSearch getLdapUserSearch()
    {
        return ldapUserSearch;
    }

    public void setLdapUserSearch(LdapUserSearch ldapUserSearch)
    {
        this.ldapUserSearch = ldapUserSearch;
    }

    public LdapAuthoritiesPopulator getPopulator()
    {
        return populator;
    }

    public void setPopulator(LdapAuthoritiesPopulator populator)
    {
        this.populator = populator;
    }

    /**
     * Este metodo se sobreescribe para devolver el user details personalizado.
     * El nuevo user details va a contener la siguiente informacion adicional:
     * -mail.
     * -idioma búsqueda
     * -idioma. 
     * -nombreCompleto
     * -tipo de empaquetador
     * -login del usuario
     * 
     * @param username string con el nombre del usuario
     * @return ud UserDetails con los detalles del usuario
     * */
    public UserDetails loadUserByUsername(String username) 
    {
    	username = username.toLowerCase();
    	log("Carga del usuario a partir del login " + username);
        LdapUserDetailsImpl ldapUser = (LdapUserDetailsImpl)ldapUserSearch.searchForUser(username);
        
        if(ldapUser == null)
        {
            return null;
        }
		log("Usuario LDAP " + ldapUser);
		UserDetails ud = null;
		try
		{
			es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega.Essence user = new es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega.Essence(ldapUser);
			log("Usuario Essence " + user);        	
			org.acegisecurity.GrantedAuthority authorities[] = populator.getGrantedAuthorities(ldapUser);            
			user.setAuthorities(authorities,user.instance.getUsername(),user.instance.getPassword());
			ud = user.createUserDetails();
			log("Detalles del usuario " + ud);
		}
		 catch(Exception e)
		 {
			logger.error("Se produce una exception al intentar crear el usuario LdapUserDetailsAgrega.Essence "+e); 
			
		 }
		return ud;
    }

    public void afterPropertiesSet()
        throws Exception
    {
        Assert.notNull(populator);
        Assert.notNull(ldapUserSearch);
    }

    private LdapAuthoritiesPopulator populator;
    private LdapUserSearch ldapUserSearch;
    
	private static void log (Object obj)
	{
		if (logger.isDebugEnabled())
			logger.debug(obj);
	}
}
