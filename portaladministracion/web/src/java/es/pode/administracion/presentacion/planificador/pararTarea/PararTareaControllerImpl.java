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