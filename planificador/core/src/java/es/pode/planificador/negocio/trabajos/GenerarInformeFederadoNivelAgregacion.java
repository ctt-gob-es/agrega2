// license-header java merge-point

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
import es.pode.planificador.negocio.servicios.TareaInformesNivelAgregacionFederadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;


public class GenerarInformeFederadoNivelAgregacion implements Job {
	
	private static Logger log = Logger.getLogger(GenerarInformeFederadoNivelAgregacion.class);
	
	/**
	 * Ejecución del trabajo de generación de informes
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException 
	{		
		log("Entramos en el trabajo de informes federados");
		Long idTarea = null;
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		TareaInformesNivelAgregacionFederadaVO datosInforme = (TareaInformesNivelAgregacionFederadaVO) parametros.get(0);
		
		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
		//boolean seguridad = Autenticar.anadirSeguridad(usuario);
		 boolean seguridad = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!seguridad) {
			log.error("ERROR: No se lanza el proceso. La seguridad no está confirmada");
			return;			
		}
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), datosInforme.getMsgDescripcionTrabajo(), usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log("Generación de informe federado: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
				
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), datosInforme.getMsgDescripcionTrabajo(), usuario);
		
		/* Se genera el informe */
		try {
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			
			log("Se genera el informe federado: idTarea: " + idTarea + " servicio: " + servicio.toString());		
			servicio.generarInformeFederadoNivelAgregacion(datosInforme, idTarea);
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido generar el informe ", e);
			
			/* Se registra que no se ha realizado correctamente */
			log.error("Error generando el informe " + excepcion);			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, datosInforme.getMsgNoInforme());
			
			throw excepcion;
		}
		
		// TODO: Temporal ya que la fecha de finalización la deberá guardar la tarea ejecutada	
		TareaEjecutadaVO trabajoEjecutado = null;
		
		try {
			trabajoEjecutado = new TareaEjecutadaVO();
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
		
		try {
			log("Registramos que el informe federado se ha realizado correctamente");
					
			RegistroTareaEjecutadaVO registro = new RegistroTareaEjecutadaVO();
			registro.setTarea_ejecutada(trabajoEjecutado);
			registro.setFecha(new GregorianCalendar());
			registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
            registro.setDescripcion(datosInforme.getMsgInforme());
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada de la generación de informe ", e1);
    		log.error(excepcion);
		}
	}
	
	
	
	private void log (Object obj) {
		if (log.isDebugEnabled())
			log.debug(obj);
	}
}
