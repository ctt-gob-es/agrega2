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
 * @author dgonzalezd
 *
 */
public class ActualizarIndices extends AbstractJob {
	
	private static Logger log = Logger.getLogger(ActualizarIndices.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		Long idTarea = null;
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);	
		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		//TODO Preparar mejores mensajes
		String msgActualizarIndices=ConstantesAgrega.TRABAJO_CORRECTO;
		String msgNoActualizarIndices =ConstantesAgrega.TRABAJO_ERRONEO;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log.debug("ActualizarIndices: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		
		SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
		ResultadoTareaVO resultado=servicio.actualizarIndicesRemotos(idTarea);
		
		log.debug("Registramos los estados intermedios y el estado final de la tarea SubirIndices");
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
				Planificador.registrarTareaIncorrecta(idTarea, estado, msgNoActualizarIndices);
				registraEstadoFinal(idTarea, tarea, msgNoActualizarIndices, estado);		
			} else {						
				registraEstadoFinal(idTarea, tarea, msgActualizarIndices, estado);		
			}
		}else
		{
			estado = ConstantesAgrega.TRABAJO_ERRONEO;
			Planificador.registrarTareaIncorrecta(idTarea, estado, msgNoActualizarIndices);
			registraEstadoFinal(idTarea, tarea, msgNoActualizarIndices, estado);		
		}
		
		registraHoraFin(idTarea, estado);
		
	}
}
