/**
 * 
 */
package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.pode.ServiceLocator;
import es.pode.buscar.negocio.administrar.servicio.ResultadoSubtareaVO;
import es.pode.buscar.negocio.administrar.servicio.ResultadoTareaVO;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;

/**
 * @author ejfente
 *
 */
public class GenerarEstadisticasNodo extends AbstractJob {

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	
	private static Logger log = Logger.getLogger(GenerarEstadisticasNodo.class);
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Long idTarea = null;
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);	
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		//TODO Preparar mejores mensajes
		String msgGenerarEstadisticas=ConstantesAgrega.TRABAJO_CORRECTO;
		String msgNoGenerarEstadisticas =ConstantesAgrega.TRABAJO_ERRONEO;
		
		log.debug("GenerarEstadisticas: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		
		SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
		ResultadoTareaVO resultado=servicio.EstadisticasLocales(idTarea);
	
		log.debug("Registramos el estado final de la tarea Generar estadisticas locales");
		String estado = ConstantesAgrega.TRABAJO_CORRECTO;
		
		if (resultado!=null) {					
			// Registramos los resultados de las subtareas
			for (int i = 0; i < resultado.getResultadosSubtareas().length; i++) {
				ResultadoSubtareaVO resSubt = resultado.getResultadosSubtareas()[i];			
				registraEstadoFinal(idTarea, tarea, resSubt.getSubtarea(),resSubt.getResultadoSubtarea());
			}
			// Registramos estado final de la tarea
			if(!resultado.getResultadoGlobal().equals("OK")) {
				estado = ConstantesAgrega.TRABAJO_ERRONEO;
				Planificador.registrarTareaIncorrecta(idTarea, estado, msgNoGenerarEstadisticas);
				registraEstadoFinal(idTarea, tarea, msgNoGenerarEstadisticas, estado);		
			} else {						
				registraEstadoFinal(idTarea, tarea, msgGenerarEstadisticas, estado);		
			}
		}else
		{
			estado = ConstantesAgrega.TRABAJO_ERRONEO;
			Planificador.registrarTareaIncorrecta(idTarea, estado, msgNoGenerarEstadisticas);
			registraEstadoFinal(idTarea, tarea, msgNoGenerarEstadisticas, estado);		
		}
		
		registraHoraFin(idTarea, estado);		
	}
}
