// license-header java merge-point
package es.pode.administracion.presentacion.planificador.listarTareasEnEjecucion;


import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.planificador.comun.Utiles;
import es.pode.planificador.negocio.servicios.TareaVO;

public class TareasEnEjecucionControllerImpl extends TareasEnEjecucionController
{

	private static Logger log = Logger.getLogger(TareasEnEjecucionControllerImpl.class);
	private Properties pSpringProperties = null;
	
    public final void obtenerTareasEnEjecucion(ActionMapping mapping, ObtenerTareasEnEjecucionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    
    	try
    	{
    		Utiles utilidades = new Utiles();
    		
       		
    		TareaVO[] tareas = this.getSrvPlanificadorService().obtenerTareasEnEjecucion();	
    		
    		for (int i=0; i < tareas.length; i++)
    		{
    			
    			/**
    	   		 * Recortamos el nombre de la tarea quitandole lo agregado al nombre original
    	   		 * y el nombre recortado lo metemos en tipoTarea para mostrarlo en la pantalla
    	   		 * de tareas en ejecucion y respetamos el nombre
    	   		 * Lo agregado son dos # seguidas de la fecha en la que se ejecuta la tarea
    	   		 * La fecha se compone de "a�o+mes+dia+hora+minutos+segundos"
    	   		 * */
    			
    			String nombre = tareas[i].getTrabajo();
    			
    	    	int posicion = nombre.indexOf("!!");
    	    	
    	    	if (posicion > 0)
    	    		tareas[i].setTipoTarea(nombre.substring(0, posicion));
    	    	else 
    	    		tareas[i].setTipoTarea(nombre);
    			
    		}
    		
    		((ListarTareasEnEjecucionFormImpl)form).setTareasEnEjecucionAsArray(tareas);
    	}
    	catch (Exception e)
    	{
    		log.error("Error: " + e);
    		throw new ValidatorException("{tareas.error}");
    	}
    }

    /**
     * This dummy variable is used to populate the "tareasEnEjecucion" table.
     * You may delete it when you add you own code in this controller.
     */
    private static final java.util.Collection tareasEnEjecucionDummyList =
        java.util.Arrays.asList( new Object[] {
            new TareasEnEjecucionDummy("job-1"),
            new TareasEnEjecucionDummy("job-2"),
            new TareasEnEjecucionDummy("job-3"),
            new TareasEnEjecucionDummy("job-4"),
            new TareasEnEjecucionDummy("job-5")
        } );

    /**
     * This inner class is used in the dummy implementation in order to get the web application
     * running without any manual programming.
     * You may delete this class when you add you own code in this controller.
     */
    public static final class TareasEnEjecucionDummy implements java.io.Serializable
    {
        private String job = null;

        public TareasEnEjecucionDummy(String job)
        {
            this.job = job;
        }
        
        public void setJob(String job)
        {
            this.job = job;
        }

        public String getJob()
        {
            return this.job;
        }
        
    }
}