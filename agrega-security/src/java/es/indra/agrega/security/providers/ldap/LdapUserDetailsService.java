/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.providers.ldap;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.ldap.LdapUserSearch;
import org.acegisecurity.providers.ldap.LdapAuthoritiesPopulator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.ldap.LdapUserDetailsImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class LdapUserDetailsService implements UserDetailsService, InitializingBean {

	private LdapAuthoritiesPopulator populator;
	
	private LdapUserSearch ldapUserSearch;
	

	public LdapUserSearch getLdapUserSearch() {
		return ldapUserSearch;
	}

	public void setLdapUserSearch(LdapUserSearch ldapUserSearch) {
		this.ldapUserSearch = ldapUserSearch;
	}

	public LdapAuthoritiesPopulator getPopulator() {
		return populator;
	}

	public void setPopulator(LdapAuthoritiesPopulator populator) {
		this.populator = populator;
	}

	public UserDetails loadUserByUsername(String username)    {

        LdapUserDetailsImpl ldapUser = (LdapUserDetailsImpl)ldapUserSearch.searchForUser(username);
        if(ldapUser==null) //If there is no LdapUserInfo, then there is no UserDetails, just return null.
        {
        	return null;
        }
        
        LdapUserDetailsImpl.Essence user = new LdapUserDetailsImpl.Essence(ldapUser);

        //Get the GrantedAuthorities for this user.
        GrantedAuthority[] authorities = populator.getGrantedAuthorities(ldapUser);

        //Now populate the LdapUserDetailsImpl with the GrantedAuthorities obtained in the previous step.
        user.setAuthorities(authorities);
        
        return user.createUserDetails();
   }

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(populator);
		Assert.notNull(ldapUserSearch);
	}
}