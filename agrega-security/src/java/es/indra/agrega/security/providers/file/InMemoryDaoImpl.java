/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.providers.file;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * 
 * @author jlhuertas
 *
 */
public class InMemoryDaoImpl extends org.acegisecurity.userdetails.memory.InMemoryDaoImpl {

	private String defaultRole;
	private String rolePrefix = "";
	private boolean convertToUpperCase = true;

    /**
     * Locates the user based on the username. In the actual implementation, the search may possibly be case
     * insensitive, or case insensitive depending on how the implementaion instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that is of a different case than what was
     * actually requested..
     *
     * @param username the username presented to the {@link DaoAuthenticationProvider}
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     * @throws DataAccessException if user could not be found for a repository-specific reason
     */
    public UserDetails loadUserByUsername(String username) 
    		throws UsernameNotFoundException, DataAccessException {
    	UserDetails user = super.loadUserByUsername(username);
    	GrantedAuthority[] roles = user.getAuthorities();
    	
 		//añadir rol por defecto (si hay que hacerlo)
    	if (defaultRole != null){
    		int numRoles = roles.length;
    		//crear un nuevo array con una posición más
    		GrantedAuthority[] newRoles = new GrantedAuthority[numRoles + 1];
    		for (int i = 0; i < numRoles; i++) {
				newRoles[i] = roles[i];
			}
    		//añadir el rol por defecto en la última posición.
    		newRoles[numRoles] = new GrantedAuthorityImpl(defaultRole);
    		roles = newRoles;
    	}
    	
    	//añadir el prefijo a los roles y convertir a mayúsculas si es necesario.
    	for (int i = 0; i < roles.length; i++) {
    		if (!roles[i].getAuthority().startsWith(rolePrefix)){
    			roles[i] = new GrantedAuthorityImpl(rolePrefix + roles[i].getAuthority());
    		}
    		
    		if (this.isConvertToUpperCase()){
    			roles[i] = new GrantedAuthorityImpl(roles[i].getAuthority().toUpperCase());
    		}
    	}

    	//retornar el usuario con los nuevos roles    	
    	return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, roles);
    }
    
	public boolean isConvertToUpperCase() {
		return convertToUpperCase;
	}
	public void setConvertToUpperCase(boolean convertToUppercase) {
		this.convertToUpperCase = convertToUppercase;
	}
	public String getDefaultRole() {
		return defaultRole;
	}
	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}
	public String getRolePrefix() {
		return rolePrefix;
	}
	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}
}
