/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Calendar;
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
import es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;



public class InformeCatalogo implements Job {

	private static Logger log = Logger.getLogger(InformeCatalogo.class);
	
	/**
	 * Ejecución del trabajo InformeCatálogo
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		Long idTarea = null;
		
		/* Añadimos la seguridad al proceso */
		log("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			log.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		
		TareaInformeCatalogoVO datosInforme = (TareaInformeCatalogoVO) parametros.get(0);
		String idioma = (String) parametros.get(1);
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), datosInforme.getMsgDescripcionTrabajo(), usuario)) return;
		} catch (Exception e) {
			log.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}
		
		log("InformeCatálogo: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), datosInforme.getMsgDescripcionTrabajo(), usuario);
		try {	
			SrvPlanificadorService servicio = ServiceLocator.instance().getSrvPlanificadorService();	
			//En caso de que datosInforme.getFechaActualizacion=null introducimois una fecha equivalente al dia 
			//actual + 1 año
			if (datosInforme.getFechaActualizacion()==null){
				Calendar fechaAct = Calendar.getInstance();
				Integer dia = (fechaAct.get(Calendar.DATE));
				Integer mes = (fechaAct.get(Calendar.MONTH));
				Integer annio = (fechaAct.get(Calendar.YEAR))+1;
				fechaAct.set(annio, mes, dia);
				servicio.generarCatalogo(idioma, fechaAct);
			}else{
				servicio.generarCatalogo(idioma, datosInforme.getFechaActualizacion());
	
			}
			
			log("Se han generado el informe del catálogo");
		}
		catch (Exception e) {
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido realizar el informe del catálogo ", e);
			
			log.error("Error " + excepcion);
			/* Se registra que no se ha realizado correctamente */
			log.error("Error generando el informe " + excepcion);			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, datosInforme.getMsgNoInforme());
			/* Se lanza el error */
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
