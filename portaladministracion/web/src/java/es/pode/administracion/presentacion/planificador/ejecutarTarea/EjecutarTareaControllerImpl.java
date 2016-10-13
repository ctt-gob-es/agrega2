// license-header java merge-point
package es.pode.administracion.presentacion.planificador.ejecutarTarea;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.TareaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


public class EjecutarTareaControllerImpl extends EjecutarTareaController
{
	private static Logger log = Logger.getLogger(EjecutarTareaControllerImpl.class);

    public final void lanzarTarea(ActionMapping mapping, LanzarTareaForm form, HttpServletRequest request, 
    		HttpServletResponse response) throws Exception
    {
    	TareaVO tarea = new TareaVO();
    	
       	try
    	{	
      		tarea.setTrabajo(form.getTrabajo());
       		tarea.setGrupoTrabajo(form.getGrupoTrabajo());
       		tarea.setTrigger(form.getTrigger());
       		tarea.setGrupoTrigger(form.getGrupoTrigger());
       		
	        // TODO: Insertamos un usuario temporal Eliminar cuando este el usuario en la sesion 
    		//Principal usuario = request.getUserPrincipal();
       		tarea.setUsuario(LdapUserDetailsUtils.getUsuario());

       		this.getSrvPlanificadorService().lanzarTarea(tarea);	
    	}
    	catch (Exception e)
    	{
    		log.error("Error: " + e);
    		saveErrorMessage(request, "tareas.error");
    	}    	
    }
}