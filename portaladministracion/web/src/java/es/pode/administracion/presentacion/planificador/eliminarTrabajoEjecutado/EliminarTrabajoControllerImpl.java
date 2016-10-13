/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.planificador.eliminarTrabajoEjecutado;



import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;




public class EliminarTrabajoControllerImpl extends EliminarTrabajoController
{
	private static Logger log = Logger.getLogger(EliminarTrabajoControllerImpl.class);

	
	/**
	 * metodo que obtiene los trabajos que se han seleccionado para eliminar
	 */
	public final void obtenerTrabajos(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.eliminarTrabajoEjecutado.ObtenerTrabajosForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		String[] arrayIds;
	    StringBuilder listaIds = new StringBuilder();
	    String nombreServidor = "";
	    String[] arrayNombreServidor;
	    
      	try
    	{   
      		
     		arrayIds = (String[])form.getIdsAsArray(); 
     		String[] listaNombres = new String[arrayIds.length];
     		
      		//Construimos el array de nombres
    		for (int i=0; i< arrayIds.length; i++)
    		{
    			nombreServidor = this.getSrvPlanificadorService().obtenerTrabajoEjecutado(new Long(arrayIds[i])).getTrabajo();
    			arrayNombreServidor = nombreServidor.split("!!");
    			
    			listaNombres[i] = arrayNombreServidor[0];
    			log.debug("trabajo a eliminar: " + listaNombres[i]);
    			listaIds.append(arrayIds[i] + "#");
    			
    		}
    		
    		form.setListaIds(listaIds.toString());
    		form.setListaNombres(listaNombres);
      		
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }		
	}
	
	
	/**
	 * metodo que elimina los trabajos seleccionados
	 */
    public final void eliminarTrabajoEjecutado(ActionMapping mapping, EliminarTrabajoEjecutadoForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	Long[] trabajosIDs = null;
		Boolean resultado;
		String resultadoString = null;
		String mensaje = null;
		
		
		
    	String listaID = form.getListaIds();
    	if(log.isDebugEnabled())log.debug("los nombres de tareas que se quieren eliminar son " + listaID);
    	
		String[] cadenaIds = listaID.split("#");
		trabajosIDs = new Long[cadenaIds.length];
		
		for (int i = 0; i < cadenaIds.length; i++) 
		{
			trabajosIDs[i] = Long.parseLong(cadenaIds[i]);
		}
		
		
		ResourceBundle ficheroRecursos = null;
		
      	try
    	{   
      		Locale locale = null;
      		if(!(LdapUserDetailsUtils.getIdioma() == null))
      		{
      			locale = new Locale(LdapUserDetailsUtils.getIdioma());
      		}else
      		{
      			locale = request.getLocale();
      		}
      		
		    ficheroRecursos = this.getFicheroRecursos(locale);
		    
		    
	    	resultado = this.getSrvPlanificadorService().eliminarTrabajoEjecutado(trabajosIDs);
	    	
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
    

    
    private ResourceBundle getFicheroRecursos(Locale locale) 
	 {
		ResourceBundle ficheroRecursos = null;
		ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
		return ficheroRecursos;
	 }
    
}