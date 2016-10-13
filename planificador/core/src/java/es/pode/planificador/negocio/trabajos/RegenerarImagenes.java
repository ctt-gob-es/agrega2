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

public class RegenerarImagenes implements Job {
	
	private static Logger log = Logger.getLogger(RegenerarImagenes.class);
	
	/**
	 * Ejecución del trabajo reindexado
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{
		Long idTarea = null;
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);	
//		String descripcion=(String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.TAREA_DESC_REGENERAR_IMAGENES);	
//		String msgNoReindexado=(String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.MSG_NOREINDEXADO);
//		String msgReindexado=(String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.MSG_REINDEXADO);
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
		RegistroTareaEjecutadaVO registro = null;
		
		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			log.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
//		String repositorio = (String) parametros.get(0); 
//		String msgReindexado = (String) parametros.get(1);
//		String msgNoReindexado = (String) parametros.get(2);
		String msgDescTrabajo = (String) parametros.get(0);
		String msgReindexado=CtesPlanificador.MSG_REGENERACION_OK;
		String msgNoReindexado =CtesPlanificador.MSG_REGENERACION_KO;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log("Regenerado: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());

		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
				
		/* Se realiza el reindexado */
		try {
			String odesRegenerados = "";
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			
			log("Se realiza el reindexado: idTarea: " + idTarea + " servicio: " + servicio.toString());		
			odesRegenerados = servicio.regeneracionImagenes(idTarea);
			log("ODEs regenerados: " + odesRegenerados);
			if(odesRegenerados.equals("KO")){
				/* Registramos la hora de finalización de la tarea incorrecta */			
				Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoReindexado);
			}
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar la regeneracion de imagenes ", e);
			
			log.error("Error regenerando imagenes " + excepcion);
			
			/* Registramos la hora de finalización de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoReindexado);
			
			/* Se lanza el error */
			throw excepcion;
		}
		
		/* Si se ha reindexado correctamente */
		try {
			log("Registramos que la regeneracion de imagenes se ha realizado correctamente");
			tarea.setId(idTarea);
			
			registro = new RegistroTareaEjecutadaVO();
			registro.setTarea_ejecutada(tarea);
			registro.setFecha(new GregorianCalendar());
			registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
			registro.setDescripcion(msgReindexado);
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada ", e1);
    		log.error(excepcion);
		}
		
//		/* Registramos la hora de finalización de la tarea */
//		try {	
//			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
//			trabajoEjecutado.setId(idTarea);
//			trabajoEjecutado.setFechaFin(new GregorianCalendar());
//			trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);
//			
//			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
//		}
//		catch (Exception e1) {
//			RegistrarTrabajoException excepcion = 
//					new RegistrarTrabajoException("Error: No se ha podido registrar el fin del trabajo", e1);
//	    	log.error(excepcion);
//		}
	}
	
	private void log (Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}

}
