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