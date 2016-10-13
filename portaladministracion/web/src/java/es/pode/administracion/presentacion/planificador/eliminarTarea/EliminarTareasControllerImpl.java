// license-header java merge-point
package es.pode.administracion.presentacion.planificador.eliminarTarea;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


/**
 * @see es.pode.administracion.presentacion.planificador.eliminarTarea.EliminarTareasController
 */
public class EliminarTareasControllerImpl extends EliminarTareasController
{
	private static Logger log = Logger.getLogger(EliminarTareasControllerImpl.class);

 
	public void eliminarTareasAdm(ActionMapping mapping, EliminarTareasAdmForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		    	
		TrabajoVO[] trabajos = null;
		Boolean resultado;
		String resultadoString = null;
		String mensaje = null;
		
    	//String listaTrabajo = request.getParameter("listaTrabajo");
		String[] nombresTrabajoArray = form.getListaTrabajoPlana().split("#");
		
		ResourceBundle ficheroRecursos = null;
		
      	try
    	{   Locale locale = null;
      		if(!(LdapUserDetailsUtils.getIdioma() == null))
      		{
      			locale = new Locale(LdapUserDetailsUtils.getIdioma());
      		}else
      		{
      			locale = request.getLocale();
      		}
      		//Locale locale = request.getLocale();
		    ficheroRecursos = this.getFicheroRecursos(locale);
		    
		    trabajos = new TrabajoVO[nombresTrabajoArray.length];
		    for(int i=0; i < nombresTrabajoArray.length;i++)
		    {
		    	trabajos[i] = new TrabajoVO();
		    	trabajos[i].setTrabajo(nombresTrabajoArray[i]);
		    }
		    
	    	resultado = this.getSrvPlanificadorService().eliminarTareasAdm(trabajos);
	    	
	    	if(resultado.booleanValue() == true)
	    	{
	    		
	    		mensaje = ficheroRecursos.getString("eliminarTareasPendientes.tareasEliminadasOk");
	    		resultadoString = ficheroRecursos.getString("tareas.OK.eliminacion");
	    	}
	    	else if(resultado.booleanValue() == false)
	    	{
	    		mensaje = ficheroRecursos.getString("eliminarTareasPendientes.tareasEliminadasError");
	    		resultadoString = ficheroRecursos.getString("tareas.ERROR.eliminacion");
	    	}
	    	
	    	form.setDescripcionBaja(mensaje);
	    	form.setResultado(resultadoString);
      		
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }		
	}
	
	 public void obtenerTareas(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.eliminarTarea.ObtenerTareasForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	 {
		Object[] sTrabajo;
		String[] listadoTrabajo = null;
		StringBuilder listaTrabajoPlana = new StringBuilder();
	  
      	try
    	{   
     		sTrabajo = form.getTrabajosAsArray(); 
     		listadoTrabajo = new String[sTrabajo.length];
     		
      		//Construimos la lista de nombres de las tareas que se van a eliminar
    		for (int i=0; i< sTrabajo.length; i++)
    		{
    			listadoTrabajo[i] = sTrabajo[i].toString();
    			if(i == sTrabajo.length)
    				listaTrabajoPlana.append((String)sTrabajo[i]);
    			else
    				listaTrabajoPlana.append((String)sTrabajo[i] + "#");
    		}
    		
    		
    		log.debug("lista de trabajos a eliminar: " + Arrays.toString(listadoTrabajo));
    		form.setListaTrabajo(listadoTrabajo);
    		form.setListaTrabajoPlana(listaTrabajoPlana.toString());
      		
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }		
	 }
	 
	 
	 private ResourceBundle getFicheroRecursos(Locale locale) 
	 {
		ResourceBundle ficheroRecursos = null;
		log.debug("locale.getLanguage() "+locale.getLanguage());
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
	 }
}