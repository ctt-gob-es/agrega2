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

public class AbstractJob implements Job {

	private static Logger log = Logger.getLogger(AbstractJob.class);

	protected void registraEstadoFinal(Long idTarea, TareaEjecutadaVO tarea,
			String msgGenerarOiaOre, String estado) {
				RegistroTareaEjecutadaVO registro;
				try {
					tarea.setId(idTarea);
					
					registro = new RegistroTareaEjecutadaVO();
					registro.setTarea_ejecutada(tarea);
					registro.setFecha(new GregorianCalendar());
					registro.setEstado(estado);	
					registro.setDescripcion(msgGenerarOiaOre);
					ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
				}
				catch (Exception e1) {
					RegistrarTrabajoException excepcion = 
							new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada ", e1);
					log.error(excepcion);
				}
			}

	protected void registraHoraFin(Long idTarea, String estado) {
		/* Registramos la hora de finalización de la tarea */
		try {	
			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
			trabajoEjecutado.setId(idTarea);
			trabajoEjecutado.setFechaFin(new GregorianCalendar());
			trabajoEjecutado.setEstado(estado);
			
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar el fin del trabajo", e1);
	    	log.error(excepcion);
		}
	}

	protected void log(Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}

	@Override
	//TODO Mejorar
	public void execute(JobExecutionContext context) throws JobExecutionException {
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
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		String msgOK=ConstantesAgrega.TRABAJO_CORRECTO;
		String msgKO=ConstantesAgrega.TRABAJO_ERRONEO;
		
		log("AbstractJob: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
				
		/* Se realiza el reindexado */
		try {
			String odesReindexados;
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			
			log("Se realiza  GenerarOaiOre: idTarea: " + idTarea + " servicio: " + servicio.toString());		
			odesReindexados = servicio.LanzarOaiOre(idTarea);
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar GenerarOaiOre ", e);
			
			log.error("Error al GenerarOaiOre " + excepcion);
			
			/* Registramos la hora de finalización de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgKO);
			
			/* Se lanza el error */
			throw excepcion;
		}
		
		String estado = ConstantesAgrega.TRABAJO_CORRECTO;
		log("Registramos que la tarea se ha realizado correctamente");
		
		registraEstadoFinal(idTarea, tarea, msgOK, estado);
		
		registraHoraFin(idTarea, estado);
	}

}