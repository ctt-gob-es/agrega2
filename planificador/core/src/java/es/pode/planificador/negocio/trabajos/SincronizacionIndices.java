package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.agrega.soporte.sitemapProperties.SitemapProperties;
import es.agrega.soporte.sitemapProperties.SitemapPropertiesImpl;
import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.SrvPlanificadorServiceImpl;
import es.pode.planificador.negocio.servicios.TareaVO;
import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.utiles.date.DateManager;

public class SincronizacionIndices implements Job {

	private static Logger log = Logger.getLogger(SincronizacionIndices.class);
	
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		String horaTrigger, minutosTrigger, segundosTrigger = "";
		GregorianCalendar fechaTriggerCalendar = null;
		Date fechaCreacionTrigger = null;
		String periodicidad = "";

		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			log.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
				
		log("Sincronización índices: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());

		//Cogeremos la periodicidad de StartTime para comprobar si coincide con los valores que aparecen en el fichero generacionContenidos.properties
		// en el caso de que no coincidan modificaré la tarea de generación del ode de la portada 
		
		periodicidad = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.PERIODICIDAD);
		log("periodicidad "+periodicidad);
		log("context.getTrigger().getStartTime() "+context.getTrigger().getStartTime());
		fechaCreacionTrigger =  context.getTrigger().getStartTime();
		fechaTriggerCalendar = new GregorianCalendar();
		fechaTriggerCalendar.setTime(fechaCreacionTrigger);
		//Obtenemos la hora, minutos y segundos en los que se lanzará la tarea
		horaTrigger = new Integer(fechaTriggerCalendar.get(Calendar.HOUR_OF_DAY)).toString();
		log("horaTrigger "+horaTrigger);
		minutosTrigger = new Integer(fechaTriggerCalendar.get(Calendar.MINUTE)).toString();
		log("minutosTrigger "+minutosTrigger);
		segundosTrigger = new Integer(fechaTriggerCalendar.get(Calendar.SECOND)).toString();
		log("segundosTrigger "+segundosTrigger);
		
		try {	
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			servicio.sincronizarInfoDB2indices();

			log("Se han sincronizado los índices");						
			
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se han podido sincronizar los índices", e);
			
			log.error("Error " + excepcion);
						
			/* Se lanza el error */
			throw excepcion;
		}
				
	}
	
    
	private void log (Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}		
}
