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

public class PortadaODE implements Job {

	private static Logger log = Logger.getLogger(PortadaODE.class);
	
	
	
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		String horaTrigger, minutosTrigger, segundosTrigger = "";
		GregorianCalendar fechaTriggerCalendar = null;
		Date fechaCreacionTrigger = null;
		String periodicidad = "";
		TrabajoVO trabajo = null;
		TrabajoVO[] trabajoArray = null;
		boolean resultadoEliminacion = false;
		TareaVO tareaCreada = null;
		
		
		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			log.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
				
		log("Portada ODE: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
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
			servicio.portadaODE();
			log("Se ha generado la imagen del ODE de portada");
			
			if(this.modificadoFicheroGeneracionContenidos(periodicidad, horaTrigger, minutosTrigger, segundosTrigger))
			{
				log("Elimino la tarea de generación del ode");
				trabajo = new TrabajoVO();
				trabajo.setGrupoTrabajo(context.getJobDetail().getGroup());
				log("trabajo.getGrupoTrabajo() "+trabajo.getGrupoTrabajo());
				trabajo.setTrabajo(context.getJobDetail().getName());
				log("trabajo.getTrabajo() "+trabajo.getTrabajo());
				trabajo.setUsuario(usuario);
				trabajoArray = new TrabajoVO[1];
				trabajoArray[0] = trabajo;
				resultadoEliminacion = (servicio.eliminarTareas(trabajoArray)).booleanValue();

				if (resultadoEliminacion) {
					log("Creamos la tarea de generación de sitemaps. Será una tarea genérica");
					tareaCreada = new TareaVO();
					tareaCreada.setTrabajo("ODE portada");
					tareaCreada.setGrupoTrabajo("GrupoTrabaODEPortada");
					tareaCreada.setTrigger("TriggerODEPortada");
					tareaCreada.setGrupoTrigger("GrupoTriggerODEPortada");
					tareaCreada.setUsuario("administrador");
					tareaCreada.setPeriodicidad(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.PERIODICIDADODE));
					log("tareaCreada.getPeriodicidad() "+tareaCreada.getPeriodicidad());
    	    	 
					Calendar calInicio = new GregorianCalendar(2008, Calendar.JANUARY, 1);
					calInicio.set(calInicio.HOUR_OF_DAY, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.HORATAREAODE))).intValue());
					calInicio.set(calInicio.MINUTE, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.MINUTOTAREAODE))).intValue());
					calInicio.set(calInicio.SECOND, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.SEGUNDOTAREAODE))).intValue());
					calInicio.set(calInicio.MILLISECOND, 0);
					Date fechaInicio = calInicio.getTime();
					tareaCreada.setFechaInicio(DateManager.dateToCalendar(fechaInicio));
					log("tareaCreada.getFechaInicio() "+tareaCreada.getFechaInicio());
    	    	 
					List parametrosTareaInformesPortada = new ArrayList();
					SrvPlanificadorServiceImpl servicioPlanificador = new SrvPlanificadorServiceImpl();
					servicioPlanificador.handleCrearTarea(tareaCreada, parametrosTareaInformesPortada, PortadaODE.class);
    	    	
				}
			}
			
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido crear el ODE de portada ", e);
			
			log.error("Error " + excepcion);
						
			/* Se lanza el error */
			throw excepcion;
		}
				
	}
	
	private void log (Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}
	
	/*
	 * Comprueba si se han modificado los atributos: periodicidad, hora, minutos y segundos en el fichero generacionContenidos.properties
	 * en el caso de que se hayan modificado habría que volver a generar una tarea con esos nuevos valores
	 */
	private boolean modificadoFicheroGeneracionContenidos(String periodicidad,String horas, String minutos, String segundos)
	{
		log("ModificacionFicheroSitemap");
		boolean resultado = false;
		String periodicidadSitemap, horaSitemap, minutosSitemap, segundosSitemap = "";
		periodicidadSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.PERIODICIDADODE);
		horaSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.HORATAREAODE);
		minutosSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.MINUTOTAREAODE);
		segundosSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.SEGUNDOTAREAODE);
		if((!(periodicidadSitemap.equalsIgnoreCase(periodicidad))) || (!(horaSitemap.equalsIgnoreCase(horas))) || (!(minutosSitemap.equalsIgnoreCase(minutos))) || (!(segundosSitemap.equalsIgnoreCase(segundos))))
		{
			resultado = true;
		}
		log("Se ha modificado el fichero "+resultado);
		return resultado;
	}
}
