/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.agrega.soporte.sitemapProperties.SitemapProperties;
import es.agrega.soporte.sitemapProperties.SitemapPropertiesImpl;
import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.RegistrarTrabajoException;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.TareaVO;
import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;


public class GenerarSitemaps implements Job {

	private static Logger logger = Logger.getLogger(InformesPortada.class);
	
	/**
	 * Ejecución del trabajo Generar ficheros sitemaps
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Long idTarea = null;
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
		RegistroTareaEjecutadaVO registro = null;
		String horaTrigger, minutosTrigger, segundosTrigger = "";
		GregorianCalendar fechaTriggerCalendar = null;
		Date fechaCreacionTrigger = null;
		String periodicidad = "";
		TrabajoVO trabajo = null;
		TrabajoVO[] trabajoArray = null;
		boolean resultadoEliminacion = false;
		TareaVO tareaCreada = null;
		
		/* Añadimos la seguridad al proceso */
		if(logger.isDebugEnabled())logger.debug("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			logger.warn("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		
		//Cogeremos la fecha de StartTime para comprobar si coincide con los valores que aparecen en el fichero sitemap.xml
		// en el caso de que no coincidan modificaré la tarea de generación de los ficheros sitemap 
		
		/**periodicidad = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.PERIODICIDAD);
		if(logger.isDebugEnabled())logger.debug("periodicidad "+periodicidad);
		if(logger.isDebugEnabled())logger.debug("context.getTrigger().getStartTime() "+context.getTrigger().getStartTime());
		fechaCreacionTrigger =  context.getTrigger().getStartTime();
		fechaTriggerCalendar = new GregorianCalendar();
		fechaTriggerCalendar.setTime(fechaCreacionTrigger);
		//Obtenemos la hora, minutos y segundos en los que se lanzará la tarea
		horaTrigger = new Integer(fechaTriggerCalendar.get(Calendar.HOUR_OF_DAY)).toString();
		if(logger.isDebugEnabled())logger.debug("horaTrigger "+horaTrigger);
		minutosTrigger = new Integer(fechaTriggerCalendar.get(Calendar.MINUTE)).toString();
		if(logger.isDebugEnabled())logger.debug("minutosTrigger "+minutosTrigger);
		segundosTrigger = new Integer(fechaTriggerCalendar.get(Calendar.SECOND)).toString();
		if(logger.isDebugEnabled())logger.debug("segundosTrigger "+segundosTrigger);**/
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		String msgGenerar=CtesPlanificador.MSG_GENERACION_SITEMAPS_OK;
		String msgNoGenerar =CtesPlanificador.MSG_GENERACION_SITEMAPS_KO;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			logger.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		if(logger.isDebugEnabled())logger.debug("Generando: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		
		try {
			String generados = "";
			if(logger.isDebugEnabled())logger.debug("Invocamos la tarea de generacion de sitemaps");
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			generados=servicio.generarSitemaps(idTarea);
			if(logger.isDebugEnabled())logger.debug("Se han generado los ficheros sitemaps");
			//comprobaré si se ha modificado el fichero sitemap.xml,en ese caso
			//eliminaré la tarea de generación de sitemaps y la volveré a crear con los nuevos atributos
			
			if(generados.equals("KO")){
				/* Registramos la hora de finalización de la tarea incorrecta */			
				Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoGenerar);
			}
			/**if(this.modificadoFicheroSitemap(periodicidad, horaTrigger, minutosTrigger, segundosTrigger))
			{
				if(logger.isDebugEnabled())logger.debug("Elimino la tarea de generación de informes");
				trabajo = new TrabajoVO();
				trabajo.setGrupoTrabajo(context.getJobDetail().getGroup());
				if(logger.isDebugEnabled())logger.debug("trabajo.getGrupoTrabajo() "+trabajo.getGrupoTrabajo());
				trabajo.setTrabajo(context.getJobDetail().getName());
				if(logger.isDebugEnabled())logger.debug("trabajo.getTrabajo() "+trabajo.getTrabajo());
				trabajo.setUsuario(usuario);
				trabajoArray = new TrabajoVO[1];
				trabajoArray[0] = trabajo;
				resultadoEliminacion = (servicio.eliminarTareas(trabajoArray)).booleanValue();

				if (resultadoEliminacion) {
					if(logger.isDebugEnabled())logger.debug("Creamos la tarea de generación de sitemaps. Será una tarea genérica");
					tareaCreada = new TareaVO();
					tareaCreada.setTrabajo("Sitemaps");
					tareaCreada.setGrupoTrabajo("GrupoTrabajoSitemaps");
					tareaCreada.setTrigger("TriggerSitemaps");
					tareaCreada.setGrupoTrigger("GrupoTriggerSitemaps");
					tareaCreada.setUsuario("administrador");
					tareaCreada.setPeriodicidad(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.PERIODICIDADTAREA));
					if(logger.isDebugEnabled())logger.debug("tareaCreada.getPeriodicidad() "+tareaCreada.getPeriodicidad());
    	    	 
					Calendar calInicio = new GregorianCalendar(2008, Calendar.JANUARY, 1);
					calInicio.set(calInicio.HOUR_OF_DAY, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.HORATAREA))).intValue());
					calInicio.set(calInicio.MINUTE, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.MINUTOTAREA))).intValue());
					calInicio.set(calInicio.SECOND, (new Integer(SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.SEGUNDOTAREA))).intValue());
					calInicio.set(calInicio.MILLISECOND, 0);
					Date fechaInicio = calInicio.getTime();
					tareaCreada.setFechaInicio(DateManager.dateToCalendar(fechaInicio));
					if(logger.isDebugEnabled())logger.debug("tareaCreada.getFechaInicio() "+tareaCreada.getFechaInicio());
    	    	 
					List parametrosTareaInformesPortada = new ArrayList();
					SrvPlanificadorServiceImpl servicioPlanificador = new SrvPlanificadorServiceImpl();
					servicioPlanificador.handleCrearTarea(tareaCreada, parametrosTareaInformesPortada, GenerarSitemaps.class);
    	    	
				}
			}**/
		}
		catch (Exception e) {
			logger.error("Se produce la siguiente excepcion "+e);
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido generar los ficheros sitemaps ", e);
			
			logger.error("Error " + excepcion);
			
			/* Registramos la hora de finalización de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoGenerar);
			/* Se lanza el error */
			throw excepcion;
		}	
		/* Si se ha generado correctamente */
		try {
			if(logger.isDebugEnabled())logger.debug("Registramos que la generacion de sitemaps se ha realizado correctamente");
			tarea.setId(idTarea);
			
			registro = new RegistroTareaEjecutadaVO();
			registro.setTarea_ejecutada(tarea);
			registro.setFecha(new GregorianCalendar());
			registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
			registro.setDescripcion(msgGenerar);
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada ", e1);
    		logger.error(excepcion);
		}
		
		/* Registramos la hora de finalización de la tarea */
		try {	
			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
			trabajoEjecutado.setId(idTarea);
			trabajoEjecutado.setFechaFin(new GregorianCalendar());
			trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);
			
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar el fin del trabajo", e1);
	    	logger.error(excepcion);
		}
	}

	
	/*
	 * Comprueba si se han modificado los atributos: periodicidad, hora, minutos y segundos en el fichero sitemap.properties
	 * en el caso de que se hayan modificado habría que volver a generar una tarea con esos nuevos valores
	 */
	private boolean modificadoFicheroSitemap(String periodicidad,String horas, String minutos, String segundos)
	{
		if(logger.isDebugEnabled())logger.debug("ModificacionFicheroSitemap");
		boolean resultado = false;
		String periodicidadSitemap, horaSitemap, minutosSitemap, segundosSitemap = "";
		periodicidadSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.PERIODICIDADTAREA);
		horaSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.HORATAREA);
		minutosSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.MINUTOTAREA);
		segundosSitemap = SitemapPropertiesImpl.getInstance().getProperty(SitemapProperties.SEGUNDOTAREA);
		if((!(periodicidadSitemap.equalsIgnoreCase(periodicidad))) || (!(horaSitemap.equalsIgnoreCase(horas))) || (!(minutosSitemap.equalsIgnoreCase(minutos))) || (!(segundosSitemap.equalsIgnoreCase(segundos))))
		{
			resultado = true;
		}
		if(logger.isDebugEnabled())logger.debug("Se ha modificado el fichero "+resultado);
		return resultado;
	}
}
