package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.RegistrarTrabajoException;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;

public class LanzarRSS implements Job {

	private static Logger log = Logger.getLogger(LanzarRSS.class);
	
	/**
	 * Ejecución del trabajo lanzar RSS
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		Long idTarea = null;		
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
		RegistroTareaEjecutadaVO registro = null;
		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			log.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		
		/* No registramos la ejecución de la tarea 
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		//String msgRSS = (String) parametros.get(0);
		String msgNoRSS = (String) parametros.get(1);
		String msgDescTrabajo = (String) parametros.get(2);
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		*/
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		String msgLanzar=CtesPlanificador.MSG_LANZAR_RSS_OK;
		String msgNoLanzar =CtesPlanificador.MSG_LANZAR_RSS_KO;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log("LanzarRSS: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		try {
			String lanzar = "";
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();			
			log("Se realiza la creación del RSS: idTarea: " + idTarea + " servicio: " + servicio.toString());		
			lanzar=servicio.lanzarRSS(idTarea);
			log("Se ha lanzado el RSS "+lanzar);
			if(lanzar.equals("KO")){
				/* Registramos la hora de finalización de la tarea incorrecta */			
				Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoLanzar);
			}
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar el RSS ", e);
			
			log.error("Error lanzandoRSS " + excepcion);
			
			/* No se registra la hora de finalización de la tarea incorrecta 			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoRSS);
			*/
			/* Registramos la hora de finalización de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoLanzar);
			
			/* Se lanza el error */
			throw excepcion;
		}
				
		/* No se registra la hora de finalización de la tarea 
		try {	
			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
			trabajoEjecutado.setId(idTarea);
			trabajoEjecutado.setFechaFin(new Date());
			trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);
			
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
		}
		catch (Exception e1) {
	    	log.error(e1);
		}
		*/
		
		/* Si se ha lanzado correctamente */
		try {
			log("Registramos que el lanzamiento de RSS-s se ha realizado correctamente");
			tarea.setId(idTarea);
			
			registro = new RegistroTareaEjecutadaVO();
			registro.setTarea_ejecutada(tarea);
			registro.setFecha(new GregorianCalendar());
			registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
			registro.setDescripcion(msgLanzar);
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada ", e1);
    		log.error(excepcion);
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
	    	log.error(excepcion);
		}
	}
	
	private void log (Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}
}
