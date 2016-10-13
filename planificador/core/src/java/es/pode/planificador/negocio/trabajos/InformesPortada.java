package es.pode.planificador.negocio.trabajos;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.soporte.seguridad.encriptacion.Autenticar;

public class InformesPortada implements Job {

	private static Logger logger = Logger.getLogger(InformesPortada.class);
	
	/**
	 * Ejecución del trabajo lanzar RSS
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		
		/* Añadimos la seguridad al proceso */
		if(logger.isDebugEnabled())logger.debug("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			logger.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}

		if(logger.isDebugEnabled())logger.debug("Informes portada: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		try {			
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();					
			servicio.informesPortada();
			if(logger.isDebugEnabled())logger.debug("Se han generado los informes de portada");
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar los Informes de portada ", e);
			
			logger.error("Error " + excepcion);
						
			/* Se lanza el error */
			throw excepcion;
		}				
	}

}
