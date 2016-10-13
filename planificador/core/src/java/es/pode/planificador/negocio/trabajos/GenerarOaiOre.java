/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point

package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorService;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;


/**
 * Trabajo de reindexado de índices
 */
public class GenerarOaiOre extends AbstractJob
{	
	/**
	 * Ejecución del trabajo reindexado
	 */
	private static Logger log = Logger.getLogger(GenerarOaiOre.class);
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
		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String msgDescTrabajo = (String) parametros.get(0);
		String msgGenerarOiaOre=CtesPlanificador.MSG_GENERAR_OAI_ORE_OK;
		String msgNoGenerarOiaOre =CtesPlanificador.MSG_GENERAR_OAI_ORE_KO;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log("GenerarOaiOre: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
				
		/* Se realiza el reindexado */
		try {
			String odesReindexados = null;
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();
			
			log("Se realiza  GenerarOaiOre: idTarea: " + idTarea + " servicio: " + servicio.toString());		
			odesReindexados = servicio.LanzarOaiOre(idTarea);
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar GenerarOaiOre ", e);
			
			log.error("Error al GenerarOaiOre " + excepcion);
			
			/* Registramos la hora de finalización de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoGenerarOiaOre);
			
			/* Se lanza el error */
			throw excepcion;
		}
		
		String estado = ConstantesAgrega.TRABAJO_CORRECTO;
		log("Registramos que la tarea GenerarOaiOre se ha realizado correctamente");
		
		registraEstadoFinal(idTarea, tarea, msgGenerarOiaOre, estado);
		
		registraHoraFin(idTarea, estado);
	}
}
