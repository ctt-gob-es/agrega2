/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.planificador.pararTarea;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.planificador.pararTarea.PararTareaController
 */
public class PararTareaControllerImpl extends PararTareaController
{
	private static Logger log = Logger.getLogger(PararTareaControllerImpl.class);
	
    public final void pararTrabajo(ActionMapping mapping, PararTrabajoForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	TrabajoVO trabajo = new TrabajoVO(); 
    	
    	try 
    	{
    		trabajo.setTrabajo(form.getTrabajo());
    		trabajo.setGrupoTrabajo(form.getGrupoTrabajo());
    		
	        // TODO: Insertamos un usuario temporal Eliminar cuando este el usuario en la sesion 
    		//Principal usuario = request.getUserPrincipal();
    		//trabajo.setUsuario(usuario (tiene que ser Long); 
    		trabajo.setUsuario(LdapUserDetailsUtils.getUsuario());

    		this.getSrvPlanificadorService().pararTarea(trabajo);
    	}
    	catch (Exception e)
    	{
    		log.error("Error: " + e);
    		saveErrorMessage(request, "tareas.error");
    	}    	
    }
}