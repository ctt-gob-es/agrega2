/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.gestorFlujo.presentacion.objetosPublicados.odeGrupos;

import org.apache.log4j.Logger;
import org.displaytag.decorator.TableDecorator;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class ChequearGruposUsuario extends TableDecorator 
{  
	private Logger logger = Logger.getLogger(ChequearGruposUsuario.class);
	
	public String getCheck() throws Exception 
	{  
		Object obj = this.getCurrentRowObject(); 
		GrupoUsuarioCheckVO grupoUsuario = ((GrupoUsuarioCheckVO) obj);
		logger.debug("Chequeando el grupo [ "+ grupoUsuario.getNombre()+"] para el usuario ["+ LdapUserDetailsUtils.getUsuario()+"]");
		String estaChequeado = "";
		//si el grupo ya tiene el ode seleccionado, no permitimos su deselección.
		if (grupoUsuario.getCheck().booleanValue()) 
			estaChequeado = "checked disabled";
		logger.debug("El grupo: " +grupoUsuario.getNombre()+ "esta chequeado: "+ estaChequeado);
	   	return " <input type='checkbox' name='nombreRowSelectionAsArray' value='" + grupoUsuario.getNombre() + "' "+estaChequeado+" title=''/> ";
		
	}  
}  