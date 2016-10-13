/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;

public class ObtenerODESDespublicadosFederadosFaltantes extends AbstractJob {
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	
	private static Logger log = Logger.getLogger(ObtenerODESDespublicadosFederados.class);
	
	
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
		
		log.debug("ObtenerODESDespublicadosFederadosFaltantes: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		
		SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
		Boolean resultado=servicio.obtenerODESDespublicadosFederadosFaltantes(idTarea);

		log.debug("Registramos el estado final de la tarea ObtenerODESDespublicadosFederadosFaltantes");
		String estado = ConstantesAgrega.TRABAJO_CORRECTO;
		if(!resultado) {
			estado = ConstantesAgrega.TRABAJO_ERRONEO;
			Planificador.registrarTareaIncorrecta(idTarea, estado, msgNoGenerarEstadisticas);
			registraEstadoFinal(idTarea, tarea, msgNoGenerarEstadisticas, estado);		
		} else {
			registraEstadoFinal(idTarea, tarea, msgGenerarEstadisticas, estado);		
		}
		registraHoraFin(idTarea, estado);
	}

}
