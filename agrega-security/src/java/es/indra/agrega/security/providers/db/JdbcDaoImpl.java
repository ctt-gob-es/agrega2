/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.security.providers.db;

import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;

public class JdbcDaoImpl extends org.acegisecurity.userdetails.jdbc.JdbcDaoImpl {

	private String defaultRole; 
	
	private boolean convertToUpperCase = true;
	
	/**
     * Allows subclasses to add their own granted authorities to the list to be returned in the
     * <code>User</code>.
     *
     * @param username the username, for use by finder methods
     * @param authorities the current granted authorities, as populated from the <code>authoritiesByUsername</code>
     *        mapping
     */
    protected void addCustomAuthorities(String username, List authorities) {
    	if (defaultRole != null){
    		String role = defaultRole;
    		//añadir el prefijo si no lo tiene ya y si está especificado
    		if (!defaultRole.startsWith(this.getRolePrefix())){
    			role = this.getRolePrefix() + role; 
    		}
    		
    		//convertir a mayúsculas si se ha especificado así
    		if (this.isConvertToUpperCase()){
    			role = role.toUpperCase();
    		}
    		authorities.add(new GrantedAuthorityImpl(role));
    	}
    	
    }

    
    public UserDetails loadUserByUsername(String username) {
    	UserDetails user = super.loadUserByUsername(username);
    	if (this.isConvertToUpperCase()){
        	GrantedAuthority[] roles = user.getAuthorities();
        	for (int i = 0; i < roles.length; i++){
        		roles[i] = new GrantedAuthorityImpl(roles[i].getAuthority().toUpperCase());
        	}
    	}
    	return user;
    }
    
	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	public boolean isConvertToUpperCase() {
		return convertToUpperCase;
	}

	public void setConvertToUpperCase(boolean convertToUpperCase) {
		this.convertToUpperCase = convertToUpperCase;
	}
	
	
}
