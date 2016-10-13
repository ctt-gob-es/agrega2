/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point

package es.pode.planificador.negocio.servicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.xml.sax.InputSource;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.ServiceLocator;
import es.pode.auditoria.negocio.servicios.ParametroCrearInformeVO;
import es.pode.auditoria.negocio.servicios.ParametroInformeGenericoVO;
import es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio;
import es.pode.buscar.negocio.administrar.servicio.CcaaVO;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.buscar.servicios.SrvCacheService;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService;
import es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO;
import es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest;
import es.pode.oaipmh.negocio.servicios.ResultadoRecordVO;
import es.pode.parseadorXML.DcDao;
import es.pode.parseadorXML.LomESDao;
import es.pode.parseadorXML.OaiPmhDao;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.oai_dc.Dc;
import es.pode.parseadorXML.oai_dc.DcContributor;
import es.pode.parseadorXML.oai_dc.DcCoverage;
import es.pode.parseadorXML.oai_dc.DcCreator;
import es.pode.parseadorXML.oai_dc.DcDate;
import es.pode.parseadorXML.oai_dc.DcDescription;
import es.pode.parseadorXML.oai_dc.DcFormat;
import es.pode.parseadorXML.oai_dc.DcIdentifier;
import es.pode.parseadorXML.oai_dc.DcLanguage;
import es.pode.parseadorXML.oai_dc.DcPublisher;
import es.pode.parseadorXML.oai_dc.DcRelation;
import es.pode.parseadorXML.oai_dc.DcRights;
import es.pode.parseadorXML.oai_dc.DcSource;
import es.pode.parseadorXML.oai_dc.DcSubject;
import es.pode.parseadorXML.oai_dc.DcTitle;
import es.pode.parseadorXML.oai_dc.DcType;
import es.pode.parseadorXML.oai_dc.Oai_dcTypeItem;
import es.pode.parseadorXML.oai_pmh.OAIPMH;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.OAIPMHAgrega;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.RecordAgrega;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.dominio.CargasEjecutadasPorIdCriteria;
import es.pode.planificador.negocio.dominio.CargasEjecutadasPorNombreZipCriteria;
import es.pode.planificador.negocio.dominio.IdentificadoresCarpetasCriteria;
import es.pode.planificador.negocio.dominio.IdentificadoresTareaCarpetaCriteria;
import es.pode.planificador.negocio.dominio.IdentificadoresTareaCriteria;
import es.pode.planificador.negocio.dominio.OdesPorIdCargaCriteria;
import es.pode.planificador.negocio.dominio.RegistroCargaTrabajoCriteria;
import es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada;
import es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDao;
import es.pode.planificador.negocio.dominio.RegistroTareaEjecutada;
import es.pode.planificador.negocio.dominio.RegistroTareaEjecutadaDao;
import es.pode.planificador.negocio.dominio.RegistroTareaEjecutadaImpl;
import es.pode.planificador.negocio.dominio.TareaEjecutada;
import es.pode.planificador.negocio.dominio.TareaEjecutadaDao;
import es.pode.planificador.negocio.dominio.TareaTrabajoFFinCriteria;
import es.pode.planificador.negocio.dominio.TareasCargaOdesEjecutadosCriteria;
import es.pode.planificador.negocio.dominio.TareasEjecutadasPorNombreLoteCriteria;
import es.pode.planificador.negocio.dominio.TrabajosActivosCriteria;
import es.pode.planificador.negocio.dominio.TrabajosNoCorrectosCriteria;
import es.pode.planificador.negocio.trabajos.ActualizarIndices;
import es.pode.planificador.negocio.trabajos.CargaODEs;
import es.pode.planificador.negocio.trabajos.DespublicarODEs;
import es.pode.planificador.negocio.trabajos.EliminarNoDisponibles;
import es.pode.planificador.negocio.trabajos.GenerarEstadisticasNodo;
import es.pode.planificador.negocio.trabajos.GenerarEstadisticasPublicas;
import es.pode.planificador.negocio.trabajos.GenerarInforme;
import es.pode.planificador.negocio.trabajos.GenerarInformeFederado;
import es.pode.planificador.negocio.trabajos.GenerarInformeFederadoNivelAgregacion;
import es.pode.planificador.negocio.trabajos.GenerarOaiOre;
import es.pode.planificador.negocio.trabajos.GenerarSitemaps;
import es.pode.planificador.negocio.trabajos.InformeCatalogo;
import es.pode.planificador.negocio.trabajos.LanzarRSS;
import es.pode.planificador.negocio.trabajos.ObtenerMetadatosODESFederados;
import es.pode.planificador.negocio.trabajos.ObtenerMetadatosODESFederadosFaltantes;
import es.pode.planificador.negocio.trabajos.ObtenerODESDespublicadosFederados;
import es.pode.planificador.negocio.trabajos.ObtenerODESDespublicadosFederadosFaltantes;
import es.pode.planificador.negocio.trabajos.RegenerarImagenes;
import es.pode.planificador.negocio.trabajos.Reindexado;
import es.pode.planificador.negocio.trabajos.SubirIndices;
import es.pode.planificador.negocio.trabajos.TareaModificacion;
import es.pode.publicacion.negocio.servicios.EliminarNoDisponiblesVO;
import es.pode.publicacion.negocio.servicios.EliminarResultadoVO;
import es.pode.publicacion.negocio.servicios.RegeneracionIndiceVO;
import es.pode.publicacion.negocio.servicios.ReindexarODEResultadoVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionCargaVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.SrvPublicacionService;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.url.Proxy;
import es.pode.soporte.utiles.date.DateManager;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

/**
 * Servicios asociados a la planificación de las tareas
 * 
 * 	Trigger: Es la programación de la tarea.
 *  Trabajo: Es la tarea planificada para ejecutarse.
 *  
 *  Una tarea engloba la programación (trigger) y el trabajo asociado.
 */

public class SrvPlanificadorServiceImpl extends es.pode.planificador.negocio.servicios.SrvPlanificadorServiceBase
{

	private static final String OK = "OK";
	private static Logger log = Logger.getLogger(SrvPlanificadorServiceImpl.class);
	private static final String FILE_NAME_PROPERTIES = "/planificador.properties";
	private static Properties props = null;

	/**
	 * Eliminación de las tareas elegidas. 
	 * @param tareas Un array de trabajos.
	 * @return retorno Indica si todo ha ido bien(true) o si ha habido algun error(false).
	 * @throws java.lang.Exception 
	 */
    protected Boolean handleEliminarTareas(TrabajoVO[] tareas)
        throws java.lang.Exception
    {
//    	Boolean retorno = false;
    	boolean todasBorradas;
    	
    	try {
    		Scheduler agenda = Planificador.getAgenda();
    		boolean borrada = false;
			todasBorradas = true;
					
	    	for (int i = 0; i < tareas.length; i++) {    			
	    		if(logger.isDebugEnabled())logger.debug("Tarea a borrar: " + tareas[i].getTrabajo());
	    		
				if (tareas[i].getTrabajo() == null || tareas[i].getGrupoTrabajo() == null) {
					todasBorradas = false;
					log.error("Nombre o grupo de la tarea/trabajo a nulo");
				}
				else {
					agenda.unscheduleJob(tareas[i].getTrabajo(), tareas[i].getGrupoTrabajo());
					borrada = agenda.deleteJob(tareas[i].getTrabajo(), tareas[i].getGrupoTrabajo());
				}
					
	    		if (!borrada) { 
	    			todasBorradas = false;
	    			if(logger.isDebugEnabled())logger.debug("No se ha podido eliminar la tarea " + tareas[i].getTrabajo() + "  " 
	    					+ tareas[i].getGrupoTrabajo());
	    		}
	    		else
	    			if(logger.isDebugEnabled())logger.debug("Eliminada la tarea " + tareas[i].getTrabajo() + "  " + tareas[i].getGrupoTrabajo());
	    	}
	    	
//	    	if (todasBorradas)
//	    		retorno = Boolean.TRUE;
		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido eliminar la tarea/s " , e);
    		throw e;
    	}
    	
        return todasBorradas;	
    }

    /**
     * Obtención de los trabajos pendientes de ejecutar del grupo por defecto
     * El planificador permite programar tareas propias del sistema que 
     * puede que no sea interesante mostrarlas en la pantalla de administración de tareas 
     * @return tareasPendientes Un array con las tareas pendientes
     * @throws Exception
     */
    
    protected TareaVO[] handleObtenerTareasPendientes() 
    	throws java.lang.Exception
    {
    	TareaVO[] tareasPendientes = null;
    	TareaVO tarea = null;
    	List<TareaVO> listaTareas = new ArrayList<TareaVO>();
    	
    	try {
    		Scheduler agenda = Planificador.getAgenda();
	    			
			/* Recuperar triggers y del grupo por defecto */	        
	        String[] triggerNombres = agenda.getTriggerNames(CtesPlanificador.GRUPO_TRIGGER);
	            
	        for (int j = 0; j < triggerNombres.length; j++) {
	            if(logger.isDebugEnabled())logger.debug("Tareas pendientes: " + triggerNombres[j]);
	            Trigger trigerRecup = agenda.getTrigger(triggerNombres[j], CtesPlanificador.GRUPO_TRIGGER);
	            	            	
            	tarea = new TareaVO();
            	tarea.setTrabajo(trigerRecup.getJobName());
            	tarea.setGrupoTrabajo(trigerRecup.getJobGroup());
            	tarea.setTrigger(trigerRecup.getName());
            	tarea.setGrupoTrigger(trigerRecup.getGroup());
            	tarea.setFechaInicio(DateManager.dateToCalendar(trigerRecup.getStartTime()));	            		            	
            	
            	if (trigerRecup instanceof CronTrigger) { 
            		if(logger.isDebugEnabled())logger.debug("CronTrigger");
            		tarea.setCron(((CronTrigger)trigerRecup).getCronExpression());
            		String periodicidad = getPeriodicidadFromCron(((CronTrigger)trigerRecup).getCronExpression());
            		tarea.setPeriodicidad(periodicidad);
            	}
            	else {
            		if(logger.isDebugEnabled())logger.debug("Trigger");
            		tarea.setPeriodicidad(ConstantesAgrega.NO_PERIODICA);
            	}
            		
                listaTareas.add(tarea);
            }
	        
	        tareasPendientes = listaTareas.toArray(new TareaVO[0]);
		}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
   
        return tareasPendientes;
    }

    /**
     * Obtención de los trabajos en ejecución
     * @return tareasEnEjecucion Un array de tareas con las tareas que se estan ejecutando
     * @throws Exception
     */
    protected TareaVO[] handleObtenerTareasEnEjecucion() 
    	throws Exception
    {
    	TareaVO[] tareasEnEjecucion = null;
    	
    	/* Rellenar tareas en ejecucion de la base de datos */
    	try {
    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
    		TareaTrabajoFFinCriteria criterios = new TareaTrabajoFFinCriteria();
    		
    		criterios.setFechaFin(null);
    		   		
    		List list = tareaEjecutadaDao.buscarTareaByTrabajoAndFFin(
    				TareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO, criterios);
    		
    		tareasEnEjecucion = new TareaVO[list.size()];
    		
    		for (int i = 0; i < list.size(); i++) {
    			tareasEnEjecucion[i] = new TareaVO();
    			tareasEnEjecucion[i].setTrabajo(((TareaEjecutadaVO)list.get(i)).getTrabajo());
    			tareasEnEjecucion[i].setGrupoTrabajo(((TareaEjecutadaVO)list.get(i)).getGrupoTrabajo());
    		}
    	}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
    	
        return tareasEnEjecucion;
    }

    /**
     * Obtención de los trabajos ejecutados
     * @return TareaEjecutadaVO[] Un array con las tareas ejecutadas
     * @throws Exception
     */

    protected TareaEjecutadaVO[] handleObtenerTrabajosEjecutados() 
    	throws Exception
    {
    	TareaEjecutadaVO[] res = null;
    	
    	try {
    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();    		
    		TrabajosActivosCriteria criterios = new TrabajosActivosCriteria();
    		criterios.setFechaBaja(null);
    		   		
    		List listaTareaEjecutadas = tareaEjecutadaDao.buscarTrabajosActivos(
    				TareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO, criterios);
    		res = (TareaEjecutadaVO[])listaTareaEjecutadas.toArray(new TareaEjecutadaVO[0]);    		    		
		}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
    	
        return res;
    }

    
    /**
     * Eliminar un trabajo ejecutado junto con el informe asociado.
     * Eliminamos un registro de la entity de tareasejecutas partiendo del id que nos pasan por parametro
     * @param idTrabajo Array con los identificadores de los trabajos
     * @return retorno Indica si todo ha ido bien(true) o si ha habido algún error(false)
     * @throws Exception
     */


    protected Boolean handleEliminarTrabajoEjecutado(Long[] idTrabajo) 
    	throws Exception
    {
    	Boolean retorno = false;
    	
    	try {
    		for (int i = 0; i < idTrabajo.length; i++) {   
    			if(logger.isDebugEnabled())logger.debug("Trabajo a eliminar: " + idTrabajo[i]);
    			
    			TareaEjecutada entity = this.getTareaEjecutadaDao().load(idTrabajo[i]);
    			Collection registroTareaEjecutada = entity.getRegistroTareaEjecutadas();
    			Collection registroTareaCargaEjecutada = entity.getRegistroTareaCargaEjecutadas();
    			
    			//Compruebo si el trabajo tiene registros en la tabla REGISTRO_TAREA_CARGA_EJECUTADA, en ese caso no eliminamos la tarea, la ponemos una fecha de baja
    			
    			if(!(registroTareaCargaEjecutada == null) && registroTareaCargaEjecutada.size()>0)
    			{
    				logger.debug("Intentamos eliminar una tarea de carga masiva");
    				entity.setFechaBaja(new GregorianCalendar());
    				this.getTareaEjecutadaDao().update(entity);
    				logger.debug("actualizamos la tarea ejecutada");
    			}else
    			{
    				if (registroTareaEjecutada != null && registroTareaEjecutada.size()>0){
        				Iterator iterator = registroTareaEjecutada.iterator();
    	    			while (iterator.hasNext()){
    	    				RegistroTareaEjecutadaImpl registroTareaEjecutadaImpl =  (RegistroTareaEjecutadaImpl) iterator.next();
    	    				this.getRegistroTareaEjecutadaDao().remove(registroTareaEjecutadaImpl);
    	    			}
    	    		}
    				entity.setFechaBaja(new GregorianCalendar());
        			this.getTareaEjecutadaDao().remove(entity);
        			logger.debug("Eliminamos una tarea que no es carga masiva");
    			}
    			    			
    		}
    		
    		retorno = Boolean.TRUE;
    	}
    	catch (Exception e) {
    		log.error("Error: No se ha podido eliminar el trabajo ejecutado " , e);
    		throw e;
    	}
    	
        return retorno;
    }

    /**
     * Obtención del informe asociado a la ejecución de un trabajo
     * Recuperamos un informe sacando la fecha de la descripcion de un trabajo segun el id que le pasamos
     * @param idTarea El id de la tarea
     * @return RegistroTareaEjecutadaVO[] Arrays con los registros de la tarea ejecutada que se pasa como parámetro
     * @throws Exception
     */

    protected RegistroTareaEjecutadaVO[] handleObtenerInformeTrabajo(Long idTarea) 
    	throws java.lang.Exception
    {
    	RegistroTareaEjecutadaVO[] informe = null;
    	
    	try {
    		Collection informeCol = this.getTareaEjecutadaDao().load(idTarea).getRegistroTareaEjecutadas();
    		if(logger.isDebugEnabled())logger.debug("Tareas ejecutadas: " + informeCol);
    		Iterator it = informeCol.iterator();
    		informe = new RegistroTareaEjecutadaVO[informeCol.size()]; 
    		int i=0;
    		
    		while (it.hasNext()) {
    			RegistroTareaEjecutada reg = (RegistroTareaEjecutada)it.next();
    			informe[i] = new RegistroTareaEjecutadaVO();
    			informe[i].setDescripcion(reg.getDescripcion());
    			informe[i].setFecha(reg.getFecha());
    			informe[i].setEstado(reg.getEstado());
    			informe[i].setCodigo(reg.getCodigo());
    			i++;	
    		}
		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido obtener el informe " , e);
    		throw e;
    	}
    	
        return informe;
    }

    /**
     * Obtenemos una tarea que queremos modificar en la carga de ODEs
     * @param tarea Tarea a modificar
     * @return tareaModificar La tarea modificada
     * @throws Exception
     */
    protected TareaCargaODEsVO handleObtenerTareaModificarCargaODEs(TareaVO tarea)
    	throws java.lang.Exception
    {
    	TareaCargaODEsVO tareaModificar = null;
    	
    	try {
    		if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    		
    		Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaCargaODEsVO();
			
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			tareaModificar.setPathODE((String) parametros.get(0));
			tareaModificar.setPathODEsCargados((String) parametros.get(1));
			tareaModificar.setPathODEsNoCargados((String) parametros.get(2));
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setMsgPublicado((String) parametros.get(3));
			tareaModificar.setMsgNoPublicado((String) parametros.get(4));
			tareaModificar.setMsgDescripcionTrabajo((String) parametros.get(5));
			tareaModificar.setSobrescribir((String) parametros.get(6));
			if(parametros.size()>7){
				logger.debug("La carga es una tarea nueva, que contiene todos los parámetros "+parametros.size());
				tareaModificar.setDescripcionTarea((String)parametros.get(7));
				if(parametros.size()>8){
					tareaModificar.setNombreLote((String)parametros.get(8));
					if(parametros.size()>9){
						logger.debug("La carga es una tarea nueva, que contiene hasta el parametro tipo de tarea");
						tareaModificar.setTipoTarea((String)parametros.get(9));
					}
				}
			}
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido modificar la tarea " , e);
    		throw e;
    	}

        return tareaModificar;
    }

    /** 
     * Ejecutar manualmente un trabajo
     * Se lanza una tarea con un determinado trigger que pasamos por parametro.
     * @param tarea 
     * @return retorno Indica si todo ha ido bien(true) o si ha habido algun error(false)
     * @throws Exception
     */
    protected Boolean handleLanzarTarea(TareaVO tarea) 
    	throws java.lang.Exception
    {
    	if(logger.isDebugEnabled())logger.debug("Lanzar tarea " + tarea.getTrabajo() + " " + tarea.getGrupoTrabajo() + " trigger: "    
    			+ tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    	Boolean retorno = false;
 
    	//Vamos a comprobar si se esta ejecutando ya en el sistema una tarea de reindexado, carga masiva o eliminación odes
    	
    	
		try {    	
			
		
			
			Scheduler agenda = Planificador.getAgenda();
			
			//////NUEVO ////////
			//Obtenemos las tareas que estan en ejecución llamamos al método del planificador obtenerTareasEnEjecucion()
			TareaVO[] tareasArray = this.obtenerTareasEnEjecucion();
			
			//comprobamos si existe alguna tarea en ejecucion
			boolean existeTareasIndice = false;
			if(!(tareasArray == null) && (tareasArray.length > 0))
			{
				if(logger.isDebugEnabled())logger.debug("Existe alguna tarea en ejecucion");
				//Comprobamos si alguna de esas tareas es del tipo CargaOdes o Reindexado
				
				for(int j=0;j<tareasArray.length;j++)
				{
					TareaVO tareaEjecucion = tareasArray[j];
					String nombre = tareaEjecucion.getTrabajo();
	    		   	int posicion = nombre.indexOf("!!");
	    	       	if (posicion > 0)
	    	       		//tareaEjecucion.setTipoTarea(nombre.substring(0, posicion));
	    	       		nombre = nombre.substring(0, posicion);
	    	    	//else 
	    	    		//tareaEjecucion.setTipoTarea(nombre);
	    	    		
	    	       	String grupoTrabajo = tareaEjecucion.getGrupoTrabajo();
					JobDetail jobTareaEjecucion = agenda.getJobDetail(nombre, grupoTrabajo);
					if(logger.isDebugEnabled())logger.debug("jobTareaEjecucion.getJobClass().getName() "+jobTareaEjecucion.getJobClass().getName());
					if(((jobTareaEjecucion.getJobClass().getName()).equalsIgnoreCase("es.pode.planificador.negocio.trabajos.CargaODEs"))||((jobTareaEjecucion.getJobClass().getName()).equalsIgnoreCase("es.pode.planificador.negocio.trabajos.Reindexado")))
					{
						existeTareasIndice = true;
					}
				}
				
			}else
			{
				if(logger.isDebugEnabled())logger.debug("**************************");
				List lista = agenda.getCurrentlyExecutingJobs();
				Iterator iter = lista.iterator();
				while(iter.hasNext())
				{
					JobExecutionContext trabajoJob = (JobExecutionContext)iter.next();
					if(logger.isDebugEnabled())logger.debug("Clase del trabajo en ejecucion"+trabajoJob.getJobDetail().getJobClass().getName());
				}
			}
			if(logger.isDebugEnabled())logger.debug("existeTareasIndice "+existeTareasIndice);
			
			
			//Nuevo
			
			
			
			String trabajoNuevo = tarea.getTrabajo() + CtesPlanificador.SEPARACION_TAREA
					+ Planificador.getFechaAAMMDDHHMMSS();
			
			String nombreTrigger = CtesPlanificador.TRIGGER + Planificador.getFechaAAMMDDHHMMSS();
					
			Trigger triggerN = new SimpleTrigger(nombreTrigger,CtesPlanificador.GRUPO_TRIGGER,new Date(System.currentTimeMillis()+ 1L*1000));
					
			//Trigger triggerNuevo = new SimpleTrigger(nombreTrigger, tarea.getGrupoTrigger(),
				//	new Date(System.currentTimeMillis()+ 3L*1000));
			
			JobDetail jobTarea = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			JobDetail jobTareaNueva = new JobDetail();			
			jobTareaNueva.setName(trabajoNuevo);
			jobTareaNueva.setGroup(jobTarea.getGroup());
			jobTareaNueva.setJobClass(jobTarea.getJobClass());
			jobTareaNueva.setJobDataMap(jobTarea.getJobDataMap());
					
			
			//Comprobamos si el tipo de tarea que se quiere ejecutar manualmente es una carga de odes o un reindexado
			//en ese caso se comprueba si existe ya ejecutándose otra tarea de carga masiva o de reindexado en ese caso
			//no se dejaría lanzar manualmente esa tarea
			
			
			
			if((((jobTareaNueva.getJobClass()).getName()).equalsIgnoreCase("es.pode.planificador.negocio.trabajos.CargaODEs"))||(((jobTareaNueva.getJobClass()).getName()).equalsIgnoreCase("es.pode.planificador.negocio.trabajos.Reindexado")))
			{
				if(logger.isDebugEnabled())logger.debug("Se intenta reindexar o carga odes");
				if(existeTareasIndice)
				{
					if(logger.isDebugEnabled())logger.debug("No se lanzaria la tarea");
					existeTareasIndice = false;
					//Registramos la tarea como erronea con el error de que no se pueden lanzar dos tareas
					//del mismo tipo a la vez  
					
					/* Registrar el fallo de la tarea */
			    	try {
			    		es.pode.planificador.negocio.servicios.TareaEjecutadaVO tareaEjecutadaVO = new TareaEjecutadaVO();
			    		if(logger.isDebugEnabled())logger.debug("jobTareaNueva.getName() "+jobTareaNueva.getName());
			    		int posicion = jobTareaNueva.getName().indexOf("!!");
		    	       	if (posicion > 0)
		    	       		//tareaEjecucion.setTipoTarea(nombre.substring(0, posicion));
		    	       		tareaEjecutadaVO.setDescripcion(jobTareaNueva.getName().substring(0, posicion));
		    	       	else
		    	       		
			    		tareaEjecutadaVO.setDescripcion(jobTareaNueva.getName());
			    		tareaEjecutadaVO.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
			    		Calendar cal = Calendar.getInstance();
			            cal.setTimeInMillis(System.currentTimeMillis());
			    		tareaEjecutadaVO.setFechaInicio(cal);
			    		tareaEjecutadaVO.setFechaFin(cal);
			    		tareaEjecutadaVO.setTrabajo(jobTareaNueva.getName());
			    		tareaEjecutadaVO.setGrupoTrabajo(jobTareaNueva.getGroup());
			    		if(logger.isDebugEnabled())logger.debug("tareaEjecutadaVO.getGrupoTrabajo() "+tareaEjecutadaVO.getGrupoTrabajo());
			    		tareaEjecutadaVO.setUsuario("administrador");
			    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
			    		TareaEjecutada tareaEjecutadaEntity = tareaEjecutadaDao.create(tareaEjecutadaDao.fromTareaEjecutadaVO(tareaEjecutadaVO));
			    		
			    		//Escribimos un mensaje de error en la tabla REGISTRO_TAREA_EJECUTADA
			    		
			    		try {
			    			if(logger.isDebugEnabled())logger.debug("Registramos que la tarea no se ha podido lanzar en la tabla Registro_tarea_ejecutada");
			    					
			    			RegistroTareaEjecutadaVO registro = new RegistroTareaEjecutadaVO();
			    			registro.setTarea_ejecutada(this.getTareaEjecutadaDao().toTareaEjecutadaVO(tareaEjecutadaEntity));
			    			if(logger.isDebugEnabled())logger.debug("registro.getTarea_ejecutada() "+registro.getTarea_ejecutada());
			    			registro.setFecha(new GregorianCalendar());
			    			registro.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);	
			    			registro.setDescripcion(ConstantesAgrega.TRABAJO_INCOMPATIBLE);
			                ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
			    			if(logger.isDebugEnabled())logger.debug("Se registra el resultado de la tarea");
			    		}
			    		catch (Exception e1) {
			    			RegistrarTrabajoException excepcion = 
			    					new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada de la generación de informe ", e1);
			        		log.error(excepcion);
			    		}
			    		
			    	}
			    	catch (Exception e) {
			    		log.error("Error al registrar la parada manual de la tarea " , e);
			    	}

					retorno = Boolean.FALSE;
					
				}else
				{
					if(logger.isDebugEnabled())logger.debug("Se lanza la tarea");
//					Date ft = agenda.scheduleJob(jobTareaNueva, triggerNuevo);
					
					/*Date ft = */agenda.scheduleJob(jobTareaNueva,triggerN);
					Thread.sleep(6L * 1000L); 
		            if(logger.isDebugEnabled())logger.debug("Despues del sleep");

		            agenda.resumeAll();
		            this.reiniciarPlanificador();
		           Thread.sleep(1L * 1000L);
		            
					if(logger.isDebugEnabled())logger.debug("Se ha lanzado manualmente " + trabajoNuevo + " " + tarea.getGrupoTrabajo() + nombreTrigger);
					
					retorno = Boolean.TRUE;
				}
				
				
			}else
			{
				if(logger.isDebugEnabled())logger.debug("Se lanza la tarea igualmente pq no es de reindexado ni de carga de odes");
//				Date ft = agenda.scheduleJob(jobTareaNueva, triggerNuevo);
				
				/*Date ft = */agenda.scheduleJob(jobTareaNueva,triggerN);
				Thread.sleep(6L * 1000L); 
	            if(logger.isDebugEnabled())logger.debug("Despues del sleep");

	            agenda.resumeAll();
	            this.reiniciarPlanificador();
	            Thread.sleep(1L * 1000L);
	            
				if(logger.isDebugEnabled())logger.debug("Se ha lanzado manualmente " + trabajoNuevo + " " + tarea.getGrupoTrabajo() + nombreTrigger);
				
				retorno = Boolean.TRUE;
				
			}
		}
		catch (Exception e) {
    		log.error("No se ha podido lanzar el trabajo manualmente " , e);
    		throw e;
		}		
		
        return retorno;
    }
       
    /**
     * Parada de un trabajo en ejecución
     * Se interrupe la ejecucion de una tarea
     * @param trabajo
     * @return boo Indica si todos ha ido bien(true) o si ha habido algun error(false)
     * @throws Exception
     */
    protected Boolean handlePararTarea(TrabajoVO trabajo)
        throws java.lang.Exception
    {
    	if(logger.isDebugEnabled())logger.debug("Parar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo());
    	boolean boo = false;
    	
    	try {
    		Scheduler agenda = Planificador.getAgenda();		
    		/* No funciona correctamente el método interrupt del Scheduler 
    		 * debido a que el método getCurrentlyExecutingJobs() no siempre devuelve bien los trabajos ejecutandose */
    		boo = agenda.interrupt(trabajo.getTrabajo(), trabajo.getGrupoTrabajo());
    		if(logger.isDebugEnabled())logger.debug("Trabajo interrumpido: " + boo);
		}	
    	catch (Exception e) {
    		log.error("No se ha podido parar el trabajo manualmente " , e);
    		throw e;
    	}		
    	
    	/* Registrar la parada de la tarea */
    	try {
    		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
    		TareaTrabajoFFinCriteria criterios = new TareaTrabajoFFinCriteria();
    		criterios.setTrabajo(trabajo.getTrabajo());
    		criterios.setGrupoTrabajo(trabajo.getGrupoTrabajo());    		
    		List list = tareaEjecutadaDao.buscarTareaByTrabajoAndFFin(
    				TareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO, criterios);
    		
    		if (list == null || list.size() == 0) 
    			return false;
			if (list.size() > 1)
				log.warn("El trabajo " + trabajo.getTrabajo() + " del grupo" + trabajo.getGrupoTrabajo() 
						+ " tiene procesos sin finalizar");
			
			TareaEjecutadaVO tarea = (TareaEjecutadaVO)list.get(0);
			tarea.setFechaFin(new GregorianCalendar());
			tarea.setEstado(ConstantesAgrega.TRABAJO_INTERRUMPIDO);
			this.getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(tarea);
    	}
    	catch (Exception e) {
    		log.error("Error al registrar la parada manual de la tarea " , e);
    	}
    	
        return boo;
    }
    
    /**
     * Inicia el planificador si no está iniciado
     * @return boo Indica si el planificador se inicia o estaba iniciado(true) o si hay algún error(false)
     * @throws Exception  
     */
   
    protected Boolean handleIniciarPlanificador() 
    	throws java.lang.Exception
    {
    	boolean boo;
    	
    	try {
    		if (!Planificador.getAgenda().isStarted())
    			Planificador.getAgenda().start();
    		
    		boo = true;
    	}
    	catch (Exception e) {
    		log.error("No se ha podido iniciar el planificador " , e);
    		throw e;
    	}
    	
    	return boo;
    }
   
    /**
     * Para el planificador si está iniciado
     * @return 	boo Indica si el planificador se para o estaba parado(true) 
     * o si hay algún error(false)
     * @throws Exception  
     */
    protected Boolean handlePararPlanificador()
    	throws java.lang.Exception
    {
    	boolean boo;
    	
    	try {
    		if (Planificador.getAgenda().isStarted())
    			Planificador.getAgenda().shutdown();
    		
    		boo = true;
    	}
    	catch (Exception e) {
    		log.error("No se ha podido parar el planificador " , e);
    		throw e;
    	}
    	
    	return boo;
    }
    
    /**
     * Reinicia el planificador
     * @return 	boo Indica si el planificador se para o estaba parado(true) o si hay algún error(false)
     * @throws Exception  
     */
    protected Boolean handleReiniciarPlanificador()
    	throws java.lang.Exception
    {
    	boolean boo;
    	
    	try {
    		if (Planificador.getAgenda().isStarted()) 
    			this.pararPlanificador();
    		
    		this.iniciarPlanificador();
    		
    		boo = true;
    	}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
    	
    	return boo;
    }
    
    /**
     * Muestra en la situación que se encuentra el planificador
     * @return boo Indica si el planificador está arrancado(true)o si el planificador está parado(false)
     * @throws Exception
     */
    protected Boolean handleEstaIniciado()
    	throws java.lang.Exception
    {
    	boolean boo;
    	
    	try {
    		if (Planificador.getAgenda().isStarted())
    			boo = true;
    		else	
    			boo = false;
    	}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
    	
    	return boo;
    }
    
    /**
     * Creación de una tarea de carga de ODEs
     * @param datosTarea Datos de la tarea
     * @return tareaCreada Los datos de la tarea creada
     * @throws Exception
     *	
     */
    protected TareaCargaODEsVO handleCrearTareaCargaODEs(TareaCargaODEsVO datosTarea)
    	throws java.lang.Exception
    {  
    	TareaCargaODEsVO tareaCreada = new TareaCargaODEsVO();
    	TareaVO tareaRetornada = null;
    	TareaVO tareaGenerica = new TareaVO();
    	List<Serializable> parametrosTarea = new ArrayList<Serializable>();
    	String msgPublicado = CtesPlanificador.MSG_PUBLICADO;
    	String msgNoPublicado = CtesPlanificador.MSG_NOPUBLICADO;
    	 
    	try {
    		tareaGenerica.setTrabajo(datosTarea.getTrabajo());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTrabajo() "+tareaGenerica.getTrabajo());
    		tareaGenerica.setTrigger(datosTarea.getTrigger());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTrigger() "+tareaGenerica.getTrigger());
    		tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getFechaInicio() "+tareaGenerica.getFechaInicio());
    		tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getPeriodicidad() "+tareaGenerica.getPeriodicidad());
    		tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getGrupoTrabajo() "+tareaGenerica.getGrupoTrabajo());
    		tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getGrupoTrigger() "+tareaGenerica.getGrupoTrigger());
    		tareaGenerica.setUsuario(datosTarea.getUsuario());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getUsuario() "+tareaGenerica.getUsuario());
    		tareaGenerica.setTipoTarea(datosTarea.getTipoTarea());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTipoTarea() "+tareaGenerica.getTipoTarea());
    		
    		if(logger.isDebugEnabled())logger.debug("Tarea generica: " + tareaGenerica);
    		
    		/* Mensajes internacionalizados que se muestran al incluir ODEs en la plataforma */
    		if (null != datosTarea.getMsgPublicado())
    			msgPublicado = datosTarea.getMsgPublicado();

    		if (null != datosTarea.getMsgNoPublicado())
    			msgNoPublicado = datosTarea.getMsgNoPublicado();

    		//Validamos la longitud de la descripcion y el nombre del lote
    		String lote=datosTarea.getNombreLote();
    		if(lote.length()>255){
    			if(logger.isDebugEnabled())logger.debug("El nombre de lote es demasiado largo. " );
    			tareaCreada.setError("tareas.LoteLarga");
    			return tareaCreada;
    		}
    		String desc=datosTarea.getDescripcionTarea();
    		if(desc.length()>255){
    			if(logger.isDebugEnabled())logger.debug("La descripción de la tarea es demasido larga. " );
    			tareaCreada.setError("tareas.DescripcionLarga");
    			return tareaCreada;
    		}
    		/* Validamos que existan los directorios de carga de ODEs */
    		File fODEs = new File(datosTarea.getPathODE()); 
    		if (!fODEs.exists()) {
    			if(logger.isDebugEnabled())logger.debug("El directorio de carga de ODEs no existe. " + fODEs.getAbsolutePath());
    			tareaCreada.setError("tareas.NoExisteDirectorioODEs");
    			return tareaCreada;
    		}
    		fODEs = new File(datosTarea.getPathODEsCargados()); 
    		if (!fODEs.exists()) {
    			if(logger.isDebugEnabled())logger.debug("El directorio donde mover los ODEs cargados no existe. " + fODEs.getAbsolutePath());
    			tareaCreada.setError("tareas.NoExisteDirectorioODEsCargados");
    			return tareaCreada;
    		}
    		fODEs = new File(datosTarea.getPathODEsNoCargados()); 
    		if (!fODEs.exists()) {
    			if(logger.isDebugEnabled())logger.debug("El directorio donde mover los ODEs no cargados no existe. " + fODEs.getAbsolutePath());
    			tareaCreada.setError("tareas.NoExisteDirectorioODEsNoCargados");
    			return tareaCreada;
    		}
    		
    		/* Parámetros propios de la tarea */
    		parametrosTarea.add(datosTarea.getPathODE());
    		parametrosTarea.add(datosTarea.getPathODEsCargados());
    		parametrosTarea.add(datosTarea.getPathODEsNoCargados());
    		parametrosTarea.add(msgPublicado);
    		parametrosTarea.add(msgNoPublicado);
    		parametrosTarea.add(datosTarea.getTrabajo());
    		parametrosTarea.add(datosTarea.getSobrescribir());
    		//Nuevos paramtros añadidos
    		
    	
    		parametrosTarea.add(datosTarea.getDescripcionTarea());
    		parametrosTarea.add(datosTarea.getNombreLote());
    		parametrosTarea.add(datosTarea.getTipoTarea());
    		
    		tareaRetornada = handleCrearTarea(tareaGenerica, parametrosTarea, CargaODEs.class);
    		
    		if(logger.isDebugEnabled())logger.debug("Parámetros de la tarea: " + parametrosTarea);
    		
    		/* Cargamos los datos de la tarea creada en el objeto */
    		tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
    		tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
    		tareaCreada.setTrigger(tareaRetornada.getTrigger());
    		tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrabajo());
    		tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
    		tareaCreada.setCron(tareaRetornada.getCron());
    		tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
    		tareaCreada.setPathODE(datosTarea.getPathODE());			
    		tareaCreada.setPathODEsCargados(datosTarea.getPathODEsCargados());
    		tareaCreada.setPathODEsNoCargados(datosTarea.getPathODEsNoCargados());
    		tareaCreada.setTipoTarea(datosTarea.getTipoTarea());
    		tareaCreada.setSobrescribir(datosTarea.getSobrescribir());
    		//Nuevos parametros
    		tareaCreada.setDescripcionTarea(datosTarea.getDescripcionTarea());
    		tareaCreada.setNombreLote(datosTarea.getNombreLote());
    		
			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada);
		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea " , e);
    		throw e;
    	}
    	
        return tareaCreada;
    }
    
    /**
     * Modificación de los datos de una tarea de carga de ODEs
     * @param datosTarea Los datos que se quieren modificar en la tarea
     * @param modificarTarea El identificativo de la tarea a modificar
     * @return 	tarea Indica si se ha podido modificar(true) o si no se ha podido modificar(false)
     * @throws Exception
     * 
     * Se opta por eliminar el trabajo y crear uno nuevo ya que se permite modificar datos que pertenecen 
     * a la identificación de la tarea y que se almacenan en el contexto
     */
    protected TareaCargaODEsVO handleModificarTareaCargaODEs(TareaCargaODEsVO datosTarea, TrabajoVO modificarTarea) 
    	throws java.lang.Exception
    {
    	/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + modificarTarea.getTrabajo() + " " + modificarTarea.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
    	Boolean boo = false;
    	TareaCargaODEsVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = modificarTarea;
    		
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		if (boo) {
    			if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.toString());
    			tarea = handleCrearTareaCargaODEs(datosTarea);
    		}
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea de carga de ODEs " , e);
    		throw e;
    	}		
	
    	return tarea;	  
    }
    
    /** 
     * Construcción de la cadena de programación de la tarea (cron) a partir de la periodicidad y la fecha de inicio
     *  @param fechaInicio Fecha de inicio de la tarea
     *  @param periodicidad Periodicidad de la tarea
     *  @return cron La expresión de la programación de la tarea en formato cron  
     * */
    protected String getCronFromPeriodicidad(Date fechaInicio, String periodicidad) 
    {
    	String cron = null;
    	Calendar fecha = new GregorianCalendar();
    	    	
    	fecha.setTime(fechaInicio);
    	String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
    	String minutos = Integer.toString(fecha.get(Calendar.MINUTE));
    	
    	if (ConstantesAgrega.DIARIA.equals(periodicidad)) {
    		if(logger.isDebugEnabled())logger.debug("Periodicidad diaria");
    		cron = "0 " + minutos + " " + hora + " * * ?";
    	}
    	else if (ConstantesAgrega.SEMANAL.equals(periodicidad)) {
    		if(logger.isDebugEnabled())logger.debug("Periodicidad semanal");
    		String diaSemana = Integer.toString(fecha.get(Calendar.DAY_OF_WEEK));
    		cron = "0 " + minutos + " " + hora + " ? * " + diaSemana;
    	}
    	else if (ConstantesAgrega.MENSUAL.equals(periodicidad)) {
    		if(logger.isDebugEnabled())logger.debug("Periodicidad mensual");
    		String diaMes = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
    		cron = "0 " + minutos + " " + hora + " " + diaMes + " * ?"; 
    	}
    	else if (ConstantesAgrega.ANUAL.equals(periodicidad)) {
    		if(logger.isDebugEnabled())logger.debug("Periodicidad anual");
    		String diaMes = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
    		String mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
    		
    		cron = "0 " + minutos + " " + hora + " " + diaMes + " " + mes + " ? ";
    	}
    	else if (ConstantesAgrega.HORARIA.equals(periodicidad)) {
    		if(logger.isDebugEnabled())logger.debug("Periodicidad cada hora");
    		cron = "0 " + minutos + " * * * ?"; 
    	}
    	else {
    		log.error("Periodicidad no existente");
    		return null;
    	}
    	
    	return cron;    	    	
    }
    
    
    /** 

     * Construcción de la periodicidad a partir de la cadena de programación de la tarea (cron)
     *  @param cron Cadena de programacion de tarea en formati cron
     *  @return devolver Constante que indica 
     *                  N: No periodica
     *            D: Diaria
     *            S: Semanal
     *            M: Mensual
     *            A: Anual
     *            H: Horaria
     *    
     * */

    protected String getPeriodicidadFromCron(String cron) 

    {

      String [] partes=cron.split(" ");
      String devolver = null;


      if(partes[5].equals("?") && partes[4].equals("*") && partes[3].equals("*"))
            devolver =  ConstantesAgrega.DIARIA;
      else if(!partes[5].equals("?") && partes[4].equals("*") && partes[3].equals("?"))
            devolver =  ConstantesAgrega.SEMANAL;
      else if(partes[5].equals("?") && partes[4].equals("*") && !partes[3].equals("?") && !partes[3].equals("*"))
            devolver =  ConstantesAgrega.MENSUAL;
      else if(partes[5].equals("?") && !partes[4].equals("?") && !partes[4].equals("*") && !partes[3].equals("?") && !partes[3].equals("*"))
            devolver = ConstantesAgrega.ANUAL;
      else if(partes[2].equals("*"))
            devolver = ConstantesAgrega.HORARIA;
      else {
            log.error("Parseado Cron incorrecto");
            devolver = ConstantesAgrega.PERIODICA;
      }

      return devolver;
    }

    
    /**
     * Creación de una tarea genérica
     * @param tarea Datos comunes de la tarea 
     * @param parametrosTarea Parámetros propios de cada tarea
     * @param claseTrabajo Clase que implementa el trabajo a realizar
     * @return tareaCreada Un objeto TareaVO con los datos de la tarea creada 
     * @throws Exception
     *	
     */
       
    public TareaVO handleCrearTarea(TareaVO tarea, List<Serializable> parametrosTarea, Class claseTrabajo)
    	throws java.lang.Exception 
    {    

    	TareaVO tareaCreada = new TareaVO();
    	Trigger trigger = null;
    	String programacion = null;
    	String trabajo = null;
    	String grupoTrabajo = null;
    	String nombreTrigger = null;
    	String grupoTrigger = null;
    	  	
    	try {
    		grupoTrabajo = CtesPlanificador.GRUPO_JOBS;
    		
    		/* Asignación de nombre al trabajo y al triger/disparador */
    		if (tarea.getTrabajo() == null || tarea.getTrabajo().equals(""))    		
    			trabajo = CtesPlanificador.JOB + Planificador.getFechaAAMMDDHHMMSS();
    		else
    			trabajo = tarea.getTrabajo();
    	
    		if (tarea.getGrupoTrabajo() == null || tarea.getGrupoTrabajo().equals(""))    		
    			grupoTrabajo = CtesPlanificador.GRUPO_JOBS;
    		else
    			grupoTrabajo = tarea.getGrupoTrabajo();

    		if (tarea.getTrigger() == null || tarea.getTrigger().equals(""))    		
    			nombreTrigger = CtesPlanificador.TRIGGER + Planificador.getFechaAAMMDDHHMMSS();
    		else
    			nombreTrigger = tarea.getTrigger();
    		
    		if (tarea.getGrupoTrigger() == null || tarea.getGrupoTrigger().equals(""))    		
    			grupoTrigger = CtesPlanificador.GRUPO_TRIGGER;
    		else
    			grupoTrigger = tarea.getGrupoTrigger();

    		if(logger.isDebugEnabled())logger.debug("Tarea a crear: nombre " + trabajo + " grupo trabajo " + grupoTrabajo + " trigger " 
    				+ nombreTrigger + " grupo trigger " + grupoTrigger);
    		
    		TrabajoVO trab = new TrabajoVO();
    		trab.setTrabajo(trabajo);
    		trab.setGrupoTrabajo(grupoTrabajo);
    		
    		if(this.handleExisteTrabajo(trab)){
    			CrearTareaException excepcion = new CrearTareaException("Error: Ya existe una tarea con ese nombre");
        		log.error(excepcion);
        		throw excepcion;
    		}
    		
    		Scheduler agenda = Planificador.getAgenda();
    		if(logger.isDebugEnabled())logger.debug("La agenda: " + agenda.toString());
    			
    		/* Creación de la tarea/job */
    		JobDetail jobTarea = new JobDetail(trabajo, grupoTrabajo, claseTrabajo);
			
    		/* Situamos en el contexto los parámetros que se necesitarán en el trabajo */
    		jobTarea.getJobDataMap().put(CtesPlanificador.PERIODICIDAD, tarea.getPeriodicidad());
    		jobTarea.getJobDataMap().put(CtesPlanificador.PARAMETROS, parametrosTarea);
    		jobTarea.getJobDataMap().put(CtesPlanificador.USUARIO, tarea.getUsuario());
    		
    		if(logger.isDebugEnabled())logger.debug("Parámetros de la tarea en el contexto: " + jobTarea.toString());
    			
    		if (ConstantesAgrega.NO_PERIODICA.equals(tarea.getPeriodicidad())) {
    			if(logger.isDebugEnabled())logger.debug("Tarea carga de ODEs. No periódica: " + trabajo);
    		    	        	    
    			/* Creación de un trigger simple */
    			trigger = new SimpleTrigger(nombreTrigger, grupoTrigger, tarea.getFechaInicio().getTime());
    	       
    			/* Asociamos Trigger/Job */
    			Date ft = agenda.scheduleJob(jobTarea, trigger);    
    			
    			if(logger.isDebugEnabled())logger.debug(jobTarea.getFullName() + " se ha añadido a la agenda para dispararse a las: " + ft);						
    		}
    		else {
    			if(logger.isDebugEnabled())logger.debug("Tarea carga de ODEs. Periódica: " + trabajo);
    		    
    			/* Si es periódica crear la cadena del Cron con la fecha ini y la periodicidad */
    			programacion = getCronFromPeriodicidad(tarea.getFechaInicio().getTime(), tarea.getPeriodicidad());
    	    
    			trigger = new CronTrigger(nombreTrigger, grupoTrigger, trabajo, 
    					grupoTrabajo, tarea.getFechaInicio().getTime(), 
    					new GregorianCalendar(2200, Calendar.DECEMBER, 31).getTime(), programacion);
    	    		    	
    			/* Se añade la tarea/trabajo */ 
    			agenda.addJob(jobTarea, true);
    	    	
    			/* Se programa el disparador */ 
    			Date ft = agenda.scheduleJob(trigger);   	 
    	    	
    			if(logger.isDebugEnabled())logger.debug(jobTarea.getFullName() + " se ha añadido a la agenda para dispararse a las: " + ft
    						+ " y repetirse en base a la expresión: "+ ((CronTrigger)trigger).getCronExpression());
    		}    
    			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(trigger.getJobName());
			tareaCreada.setGrupoTrabajo(trigger.getJobGroup());
			tareaCreada.setTrigger(trigger.getName());
			tareaCreada.setGrupoTrigger(trigger.getGroup());
			tareaCreada.setPeriodicidad(tarea.getPeriodicidad());
			tareaCreada.setCron(programacion);
			tareaCreada.setFechaInicio(DateManager.dateToCalendar(trigger.getStartTime()));
			tareaCreada.setTipoTarea(tarea.getTipoTarea());
			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());

		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea " , e);
    		throw e;    	
    	}
    	
        return tareaCreada;
    }
    
    /**
     * Obtención de todos los trabajos pendientes de ejecutar
     * @return tareasPendientes Un array con las tareas
     * @throws Exception
     */
    protected TareaVO[] handleObtenerTareasPendientesTodas()
        throws java.lang.Exception
    {
    	TareaVO[] tareasPendientes = null;
    	TareaVO tarea = null;
    	List<TareaVO> listaTareas = new ArrayList<TareaVO>();
    	
    	try {
    		Scheduler agenda = Planificador.getAgenda();
	    		
			/* Recuperar trigger y grupos de triggers */
			String[] triggersGrupos = agenda.getTriggerGroupNames();
	        
	        for (int i = 0; i < triggersGrupos.length; i++) {            
	        	String[] triggerNombres = agenda.getTriggerNames(triggersGrupos[i]);
	            
	            for (int j = 0; j < triggerNombres.length; j++) {
	            	if(logger.isDebugEnabled())logger.debug("Tareas pendientes: " + triggerNombres[j]);
	            	Trigger trigerRecup = agenda.getTrigger(triggerNombres[j], triggersGrupos[i]);
	            	            	
	            	tarea = new TareaVO();
	            	tarea.setTrabajo(trigerRecup.getJobName());
	            	tarea.setGrupoTrabajo(trigerRecup.getJobGroup());
	            	tarea.setTrigger(trigerRecup.getName());
	            	tarea.setGrupoTrigger(trigerRecup.getGroup());
	            	tarea.setFechaInicio(DateManager.dateToCalendar(trigerRecup.getStartTime()));	            		            	
	            	
	            	if (trigerRecup instanceof CronTrigger) 
	            		tarea.setCron(((CronTrigger)trigerRecup).getCronExpression());
	            	       	
	                 listaTareas.add(tarea);
	            }
	        }
	        if(logger.isDebugEnabled())logger.debug("Lista de tareas: " + listaTareas.toString());
	        tareasPendientes = listaTareas.toArray(new TareaVO[0]);
		}
    	catch (Exception e) {
    		log.error("Error: " , e);
    		throw e;
    	}
   
        return tareasPendientes;
    }   
    
    /**
     * Obtiene el informe de los sub-trabajos que son erroneos de un trabajo concreto
     * @param id El id indica el trabajo que se va a consultar
     * @return array El registro de tareas ejecutadas
     * @throws Exception
     * 
     */
	protected RegistroTareaEjecutadaVO[] handleObtenerInformeTrabajoErroneos(Long id) 
		throws Exception 
	{
		RegistroTareaEjecutadaDao registroDao = this.getRegistroTareaEjecutadaDao();
		if(logger.isDebugEnabled())logger.debug("Dao: " + registroDao.toString());
		
		TrabajosNoCorrectosCriteria criterios = new TrabajosNoCorrectosCriteria();
		if(logger.isDebugEnabled())logger.debug("Criterios: " + criterios.toString());
		criterios.setIdTrabajo(this.getTareaEjecutadaDao().load(id));
		criterios.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
		List list = registroDao.buscarTrabajosNoCorrectos(
				RegistroTareaEjecutadaDao.TRANSFORM_REGISTROTAREAEJECUTADAVO, criterios);
	 	
		if (list == null || list.size() == 0) 
            return null;
		RegistroTareaEjecutadaVO[] array = null;
		array = (RegistroTareaEjecutadaVO[])list.toArray(new RegistroTareaEjecutadaVO[0]);
		return array;	
	}

	
	/**
	 * Obtiene un trabajo ejecutado con el id de una tarea en concreto
	 * @param id El id de una tarea en concreto
	 * @return tarea La tarea con los datos correspodientes al trabajo con el id pasado por parametro
	 * @throws Exception
	 */
	protected TareaEjecutadaVO handleObtenerTrabajoEjecutado(Long id) 
		throws Exception 
	{
		TareaEjecutadaVO tarea= new TareaEjecutadaVO();
		
		try {
			tarea.setId(id);
			tarea.setGrupoTrabajo(this.getTareaEjecutadaDao().load(id).getGrupoTrabajo());
			tarea.setFechaFin(this.getTareaEjecutadaDao().load(id).getFechaFin());
			tarea.setFechaInicio(this.getTareaEjecutadaDao().load(id).getFechaInicio()); 
    		tarea.setDescripcion(this.getTareaEjecutadaDao().load(id).getDescripcion());
    		tarea.setTrabajo(this.getTareaEjecutadaDao().load(id).getTrabajo());
    		tarea.setTipoTarea(this.getTareaEjecutadaDao().load(id).getTipoTarea());
		}
		catch (Exception e) {
			log.error("Error " , e);
    		throw e;
		}
		return tarea;
	}

	
	/**
	 * Crea la una tarea del tipo reindexado
	 * @param datosTarea Los datos correspodientes a la tarea reindexada
	 * @return tareaCreada Una tarea del tipo reindexada
	 * @throws Exception
	 */
	protected TareaReindexadoVO handleCrearTareaReindexado(TareaReindexadoVO datosTarea) 
		throws Exception 
	{
		TareaReindexadoVO tareaCreada = new TareaReindexadoVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
	   	String msgReindexado = CtesPlanificador.MSG_REINDEXADO;
    	String msgNoReindexado = CtesPlanificador.MSG_NOREINDEXADO;
    	
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al reindexar los ODEs de la plataforma */
    		if (null != datosTarea.getMsgReindexado())
    			msgReindexado = datosTarea.getMsgReindexado();

    		if (null != datosTarea.getMsgNoReindexado())
    			msgNoReindexado = datosTarea.getMsgNoReindexado();
    		    	
			/* Parámetros propios de la tarea */
			parametrosTarea.add(datosTarea.getRepositorioIndices());
    		parametrosTarea.add(msgReindexado);
    		parametrosTarea.add(msgNoReindexado);
    		parametrosTarea.add(datosTarea.getTrabajo());
			
			tareaRetornada = handleCrearTarea( tareaGenerica, parametrosTarea, Reindexado.class);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			tareaCreada.setRepositorioIndices(datosTarea.getRepositorioIndices());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	
	/**
	 * Modificación de los datos de una tarea de Reindexado
	 * @param datosTarea Datos de la tarea de reindexado
	 * @param trabajo Tarea que hay que modificar con los datos de reindexado
	 * @return tarea Tarea modificada de reindexado
	 * @throws Exception
	 */
	protected TareaReindexadoVO handleModificarTareaReindexado(TareaReindexadoVO datosTarea, TrabajoVO trabajo)
			throws Exception 
	{
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
			+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
		
		Boolean boo = false;
		TareaReindexadoVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
			
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo) 
				tarea = handleCrearTareaReindexado(datosTarea);    		
	
		}	
		catch (Exception e) {
			log.error("No se ha podido modificar la reindexacion " , e);
			throw e;
		}		
	
		return tarea;	  
	}
	
	
	/**
	 * Obtiene una tarea Reindexada a partir de una tarea
	 * @param tarea Una tarea que identifica que tarea reindexada debemos coger
	 * @return tareaModificar Una tarea reindexada rellena que corresponde a la tarea pasada por parametro
	 * @throws Exception
	 */
	protected TareaReindexadoVO handleObtenerTareaReindexado(TareaVO tarea)
    		throws java.lang.Exception
    {
		TareaReindexadoVO tareaModificar = null;
	
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
		
			Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaReindexadoVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);			
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			tareaModificar.setRepositorioIndices((String) parametros.get(0));
			tareaModificar.setMsgReindexado((String) parametros.get(1));
			tareaModificar.setMsgNoReindexado((String) parametros.get(2));
			tareaModificar.setMsgDescripcionTrabajo((String) parametros.get(3));
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
		}
		catch (Exception e) {
			log.error("Error: No se ha podido modificar la tarea " , e);
			throw e;
		}

		return tareaModificar;
    }

	
	/**
	 * Metodo que se encarga de crear una tarea de eliminacion de ODEs en estado no disponible.
	 * @param datosTarea Parametros de la tarea de eliminacion de ODEs no publicados.
	 * @return TareaEliminaNoDisponiblesVO Una tarea del tipo eliminar no disponible
	 * @throws Exception
	 */
	protected TareaEliminaNoDisponiblesVO handleCrearTareaEliminarNoDisponibles(TareaEliminaNoDisponiblesVO datosTarea) throws Exception {
		TareaEliminaNoDisponiblesVO tareaCreada = new TareaEliminaNoDisponiblesVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
	   	String msgEliminaciones = CtesPlanificador.MSG_ELIMINACIONODES_OK;
    	String msgNoEliminaciones = CtesPlanificador.MSG_ELIMINACIONODES_KO;
    			
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al eliminar los ODEs de la plataforma */
    		if (null != datosTarea.getMsgEliminado())
    			msgEliminaciones = datosTarea.getMsgEliminado();

    		if (null != datosTarea.getMsgNoEliminado())
    			msgNoEliminaciones = datosTarea.getMsgNoEliminado();

			/* Parámetros propios de la tarea */
			parametrosTarea.add(datosTarea.getFechaDesde());
			parametrosTarea.add(datosTarea.getFechaHasta());
    		parametrosTarea.add(msgEliminaciones);
    		parametrosTarea.add(msgNoEliminaciones);
    		parametrosTarea.add(datosTarea.getTrabajo());
			
			tareaRetornada = handleCrearTarea( tareaGenerica, parametrosTarea, EliminarNoDisponibles.class);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	/**
	 * Este metodo modifica una tarea de eliminacion de ODEs no disponibles.
	 * @param datosTarea Nuevos datos de la tarea de eliminar ODEs no disponibles.
	 * @param trabajo Tarea que hay que modificar con los datos de reindexado
	 * @return TareaEliminaNoDisponiblesVO Tarea del tipo eliminar no disponible
	 * @throws Exception
	 */
	protected TareaEliminaNoDisponiblesVO handleModificarTareaEliminarNoDisponibles(TareaEliminaNoDisponiblesVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario());
			
			Boolean boo = false;
			TareaEliminaNoDisponiblesVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo) 
					tarea = handleCrearTareaEliminarNoDisponibles(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la tarea eliminar ODEs " , e);
				throw e;
			}		
		
			return tarea;	
	}

	/**
	 * Este metodo devuelve la tarea de eliminacion de ODEs no disponibles que coincida con la tarea.
	 * @param tarea Tarea que se quiere obtener.
	 * @return TareaEliminaNoDisponiblesVO Una tarea de eliminacion rellena que corresponde a la tarea pasada por parametro
	 * @throws Exception
	 */
	protected TareaEliminaNoDisponiblesVO handleObtenerTareaEliminarrNoDisponibles(TareaVO tarea) throws Exception {
		TareaEliminaNoDisponiblesVO tareaModificar = null;
		
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
		
			Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaEliminaNoDisponiblesVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			if(logger.isDebugEnabled())logger.debug("Parametros " + parametros.toString());
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));			
			tareaModificar.setFechaDesde((GregorianCalendar)parametros.get(0));
			tareaModificar.setFechaHasta((GregorianCalendar)parametros.get(1));
			tareaModificar.setMsgEliminado((String) parametros.get(2));
			tareaModificar.setMsgNoEliminado((String) parametros.get(3));
			tareaModificar.setMsgDescripcionTrabajo((String) parametros.get(4));
			
		}
		catch (Exception e) {
			log.error("Error: No se ha podido obtener la tarea de elimina ODEs " , e);
			throw e;
		}

		return tareaModificar;
	}

	/** 
	 *  Metodo fachada para la eliminacion de los ODEs en estado no disponible que hayan pasado a dicho 
	 *  estado en un periodo concreto de tiempo.
	 * @param fechaInicio Periodo de inicio desde el que se esta interesado en borrar ODEs no disponibles.
	 * @param fechaFin Periodo de fin desde el que se esta interesado en borrar ODEs no disponibles.
	 * @param idTarea 
	 * 	@return String Devuelve lo mismo que la correspondiente llamada al servicio de publicacion.
	 * @throws Exception
	 * */
	protected String handleEliminarNoDisponibles(Calendar fechaInicio, Calendar fechaFin, Long idTarea) throws Exception {
		
		EliminarNoDisponiblesVO paramEliminar = new EliminarNoDisponiblesVO();
		paramEliminar.setFechaFin(fechaFin);
		paramEliminar.setFechaInicio(fechaInicio);
		paramEliminar.setIdUsuarios(new String[]{});
		paramEliminar.setIdTarea(idTarea);
		
		try {
			EliminarResultadoVO[] resultados = this.getSrvPublicacionService().eliminarNoDisponibles(paramEliminar);
			if (resultados!= null && resultados.length >0)
			{
				log.debug("ODEs afectados por la elmininacion["+resultados.length+"]");
				return ""+resultados.length;
			}
		} 
		catch (Exception e) {
			log.error("Error: No se ha podido realizar el eliminado de ODEs " , e);
			throw e;
		}
		return "0";
	}	
	
	/** 
	 *  Método que llama al servicio externo de publicación
	 * 	@param odes Array de rutas relativas al pahtCarga de donde se encuentran los Objeto Digital Educativos 
	 * 	@param idUsuario Identificador del usuario
	 * 	@param nombreCarga Nombre de la carga masiva
	 * 	@param sobrescribir Determina si el ODE se debe sobrescribir o no (s/n)
	 * 	@param pathCarga Path donde se encuentran todos odes a cargar
	 * 	@return resultadoCarga Devuelve el array de resultados
	 * @throws Exception
	 * */
	protected ResultadoCargaVO[] handlePublicarPIF(String[] odes, String idUsuario, String nombreCarga, String sobrescribir, String pathCarga) 
		throws Exception 
	{
		
		ResultadoOperacionCargaVO[] resultado = this.getSrvPublicacionService().publicarPifCarga(odes,idUsuario, sobrescribir, nombreCarga, pathCarga);
		
		ResultadoCargaVO[] resultadoCarga=new ResultadoCargaVO[resultado.length];
		for(int i=0;i<resultado.length;i++){
			ResultadoCargaVO resVO = (es.pode.planificador.negocio.servicios.ResultadoCargaVO)this.getBeanMapper().map(resultado[i],
					es.pode.planificador.negocio.servicios.ResultadoCargaVO.class, "DEF_MAPPING_RESULTADOOPERACIONCARGAVO_RESULTADOCARGAVO");
			resultadoCarga[i]=resVO;
		}

		return resultadoCarga;
	}
	
	/** 
	 * Recuperación del tipo de la tarea
	 * @param trabajo  
	 * @return tipoTarea La clase que ejecuta la tarea
	 * @throws Exception
	 * 
	 */
	protected String handleObtenerTipoTarea(TrabajoVO trabajo) 
		throws Exception 
	{
    	String tipoTarea = null;
    	JobDetail trabajoDetalle = null;
    	
    	try {    		
    		Scheduler agenda = Planificador.getAgenda();    		
    		trabajoDetalle = agenda.getJobDetail(trabajo.getTrabajo(), trabajo.getGrupoTrabajo());    		
    		tipoTarea = Planificador.ultimoCampo(trabajoDetalle.getJobClass().toString());
        		
			if(logger.isDebugEnabled())logger.debug("Tipo tarea: " + tipoTarea);
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido obtener el tipo de la tarea" , e);
    		throw e;
    	}

        return tipoTarea;	
	}
	
	
	/**
	 * Reindexado de una tarea
	 * @param repositorio Indice de repositorio (coincide con el locale)
	 * @param idTarea Identificador de la tarea
	 * @return objReindexados  
	 * @throws Exception
	 */
	protected Long handleReindexado(String repositorio, Long idTarea) 
		throws Exception 
	{
		Long objReindexados = null;
		ReindexarODEResultadoVO[] resultadosReindexado = null;
		try {
		
			SrvPublicacionService servicio = this.getSrvPublicacionService();
			RegeneracionIndiceVO paramRegenera = new RegeneracionIndiceVO();
			paramRegenera.setIdIdiomas(new String[]{repositorio});
			paramRegenera.setIdTarea(idTarea);
			resultadosReindexado = servicio.regeneraIndiceIdioma(paramRegenera);
			objReindexados = resultadosReindexado!= null?resultadosReindexado.length: 0L;
		}
		catch (Exception e) {
			log.error("Error: No se ha podido realizar el reindexado " , e);
			throw e;
		}
		
		return objReindexados;
	}

	/**
	 * Elimina los trabajos que se pasan por parametro
	 * Sólo se permite eliminar tareas del grupo por defecto de la administración
	 * @param trabajos Array de trabajos a eliminar
	 * @return devolver Indica si todo ha ido bien(true) o si ha habido algun error(false)
	 * @throws Exception
	 */
	protected Boolean handleEliminarTareasAdm(TrabajoVO[] trabajos) 
		throws Exception 
	{
		for (int i = 0; i < trabajos.length; i++) {    			
			trabajos[i].setGrupoTrabajo(CtesPlanificador.GRUPO_JOBS);	
    	}
		
		Boolean devolver = handleEliminarTareas(trabajos);
		return devolver;
	}

	/** 
	 * Se comprueba si ya existe un trabajo con ese nombre o grupo
     * @param trabajo Trabajo que contiene el nombre del trabajo y el grupo ha comparar
     * @return 	devolver Indica si exite(true) o si no existe(false)
     * @throws Exception
     */
	
	protected Boolean handleExisteTrabajo(TrabajoVO trabajo) throws Exception 
	{
		Scheduler agenda = Planificador.getAgenda();
		Boolean devolver = Boolean.FALSE;
		// Recuperar trabajos y grupos de trabajo 
		String[] trabajosGrupos = agenda.getJobGroupNames();
		String grupoTrabajo = null;
		
		// Si el grupo de trabajo viene a nulo asignamos el grupo por defecto
   		if (trabajo.getGrupoTrabajo() == null || trabajo.getGrupoTrabajo().equals(""))    		
			grupoTrabajo = CtesPlanificador.GRUPO_JOBS;
		else
			grupoTrabajo = trabajo.getGrupoTrabajo();
		
		for (int i = 0; i < trabajosGrupos.length; i++)  {     
			//Comparo todos los grupos y si se llaman igual comparo los trabajo
			//devuelvo false si no encuentro las dos condicciones y true si las encuentro
			if(grupoTrabajo.equals(trabajosGrupos[i])) {	
				String[] trabajos = agenda.getJobNames(trabajosGrupos[i]);
		
				for (int j = 0; j < trabajos.length; j++) {
					if (trabajo.getTrabajo().equalsIgnoreCase(trabajos[j])) {
						if(logger.isDebugEnabled())logger.debug("Existe un trabajo con ese nombre " + trabajo.getTrabajo());
						devolver =  Boolean.TRUE;
						return devolver;
					}
				}
			}
		}
		
		return devolver;
	}



	/**
	 * Crea una tarea del tipo informes
	 * @param datosTarea Nuevos datos de la tarea generación de informes
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesVO handleCrearTareaInformes(TareaInformesVO datosTarea) throws Exception {
		
		TareaInformesVO tareaCreada = new TareaInformesVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
		
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al reindexar los ODEs de la plataforma */
    		if (null == datosTarea.getMsgInforme() || datosTarea.getMsgInforme().equals(""))
    			datosTarea.setMsgInforme(CtesPlanificador.MSG_INFORMES_OK);

    		if (null == datosTarea.getMsgNoInforme() || datosTarea.getMsgNoInforme().equals(""))
    			datosTarea.setMsgNoInforme(CtesPlanificador.MSG_INFORMES_KO);

    		if (null == datosTarea.getMsgDescripcionTrabajo() || datosTarea.getMsgDescripcionTrabajo().equals(""))
    			datosTarea.setMsgDescripcionTrabajo(datosTarea.getTrabajo());

			/* Parámetros propios de la tarea */
			parametrosTarea.add(datosTarea);
    		    		
			tareaRetornada = handleCrearTarea (tareaGenerica, parametrosTarea, GenerarInforme.class);
			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			//tareaCreada. setRepositorioIndices(datosTarea.getRepositorioIndices());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	/**
	 * Crea una tarea del tipo informes Federada
	 * @param datosTarea Nuevos datos de la tarea generación de informes federada
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesVO handleCrearTareaInformesFederado(TareaInformesVO datosTarea) throws Exception {
		
		TareaInformesVO tareaCreada = new TareaInformesVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
		
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al reindexar los ODEs de la plataforma */
    		if (null == datosTarea.getMsgInforme() || datosTarea.getMsgInforme().equals(""))
    			datosTarea.setMsgInforme(CtesPlanificador.MSG_INFORMES_OK);

    		if (null == datosTarea.getMsgNoInforme() || datosTarea.getMsgNoInforme().equals(""))
    			datosTarea.setMsgNoInforme(CtesPlanificador.MSG_INFORMES_KO);

    		if (null == datosTarea.getMsgDescripcionTrabajo() || datosTarea.getMsgDescripcionTrabajo().equals(""))
    			datosTarea.setMsgDescripcionTrabajo(datosTarea.getTrabajo());

			/* Parámetros propios de la tarea */
			parametrosTarea.add(datosTarea);
    		    		
			tareaRetornada = handleCrearTarea (tareaGenerica, parametrosTarea, GenerarInformeFederado.class);
			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			//tareaCreada. setRepositorioIndices(datosTarea.getRepositorioIndices());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	/**
	 * Este metodo modifica una tarea de generación de informes
	 * @param datosTarea Nuevos datos de la tarea generación de informes
	 * @param trabajo Tarea que hay que modificar
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesVO handleModificarTareaInformes(TareaInformesVO datosTarea, TrabajoVO trabajo) throws Exception 
	{
    	/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.getTrabajo() + " " + datosTarea.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario());
    	Boolean boo = false;
    	TareaInformesVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = trabajo;
    					
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		if (boo.booleanValue()) {
    			if(logger.isDebugEnabled())logger.debug("Crear tarea " + datosTarea.toString());
    			tarea = handleCrearTareaInformes(datosTarea);
    		}
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea " , e);
    		throw e;
    	}		
	
    	return tarea;	  
	}
	
	/**
	 * Este metodo modifica una tarea de generación de informes federados
	 * @param datosTarea Nuevos datos de la tarea generación de informes
	 * @param trabajo Tarea que hay que modificar
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesVO handleModificarTareaInformesFederado(TareaInformesVO datosTarea, TrabajoVO trabajo) throws Exception 
	{
    	/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.getTrabajo() + " " + datosTarea.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario());
    	Boolean boo = false;
    	TareaInformesVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = trabajo;
    					
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		if (boo.booleanValue()) {
    			if(logger.isDebugEnabled())logger.debug("Crear tarea " + datosTarea.toString());
    			tarea = handleCrearTareaInformesFederado(datosTarea);
    		}
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea " , e);
    		throw e;
    	}		
	
    	return tarea;	  
	}
	

	/**
	 * Crea una tarea del tipo informes de catalogo
	 * @param datosTarea Nuevos datos de la tarea generación de informes federada
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformeCatalogoVO handleCrearTareaInformesCatalogo(TareaInformeCatalogoVO datosTarea) throws Exception {
		
		TareaInformeCatalogoVO tareaCreada = new TareaInformeCatalogoVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
		
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al reindexar los ODEs de la plataforma */
    		if (null == datosTarea.getMsgInforme() || datosTarea.getMsgInforme().equals(""))
    			datosTarea.setMsgInforme(CtesPlanificador.MSG_INFORMES_OK);

    		if (null == datosTarea.getMsgNoInforme() || datosTarea.getMsgNoInforme().equals(""))
    			datosTarea.setMsgNoInforme(CtesPlanificador.MSG_INFORMES_KO);

    		if (null == datosTarea.getMsgDescripcionTrabajo() || datosTarea.getMsgDescripcionTrabajo().equals(""))
    			datosTarea.setMsgDescripcionTrabajo(datosTarea.getTrabajo());

			/* Parámetros propios de la tarea */
    		parametrosTarea.add(datosTarea);
    		parametrosTarea.add(datosTarea.getIdioma());
			parametrosTarea.add((datosTarea.getFechaActualizacion()));
    		
    		if(logger.isDebugEnabled())logger.debug("Insertamos los parametros de la tarea, en la posicion 0 los datos de tarea"+ datosTarea+" y en la posicion 1 el idioma "+ datosTarea.getIdioma());
			tareaRetornada = handleCrearTarea (tareaGenerica, parametrosTarea, InformeCatalogo.class);
			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			tareaCreada.setIdioma(datosTarea.getIdioma());
			tareaCreada.setFechaActualizacion(datosTarea.getFechaActualizacion());
			
			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString()+" con idioma "+datosTarea.getIdioma());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}
	
	
	/**
	 * Este metodo modifica una tarea de generación de informes de catalogo
	 * @param datosTarea Nuevos datos de la tarea generación de informes
	 * @param trabajo Tarea que hay que modificar
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformeCatalogoVO handleModificarTareaInformesCatalogo(TareaInformeCatalogoVO datosTarea, TrabajoVO trabajo) throws Exception 
	{
    	/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.getTrabajo() + " " + datosTarea.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario());
    	Boolean boo = false;
    	TareaInformeCatalogoVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = trabajo;
    					
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		if (boo.booleanValue()) {
    			if(logger.isDebugEnabled())logger.debug("Crear tarea " + datosTarea.toString());
    			tarea = handleCrearTareaInformesCatalogo(datosTarea);
    		}
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea " , e);
    		throw e;
    	}		
	
    	return tarea;	  
	}
	
	
	
	/**
	 * Este metodo obtiene una tarea del tipo de informes
	 * @param tarea nombre de la tarea que se recuperara los datos
	 * @return TareaInformesVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */
	protected TareaInformesVO handleObtenerTareaInformes(TareaInformesVO tarea) throws Exception 
	{
		TareaInformesVO tareaModificar = null;
    	
    	try {
    		if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    		
    		Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaInformesVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			
			TareaInformesVO datosInforme = (TareaInformesVO) parametros.get(0);
	
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			tareaModificar.setFechaDesde(datosInforme.getFechaDesde());
			tareaModificar.setFechaHasta(datosInforme.getFechaHasta());
			tareaModificar.setFormato(datosInforme.getFormato());
			tareaModificar.setInforme(datosInforme.getInforme());
			tareaModificar.setRango(datosInforme.getRango());
			tareaModificar.setUsuarioInforme(datosInforme.getUsuarioInforme());
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido modificar la tarea de informes " , e);
    		throw e;
    	}

        return tareaModificar;
	}

	
	/**
	 * Crea la generacion de un informe
	 * @param datosInforme Datos que necesita el informe para lanzarse
	 * @param idTarea Identificador de la tarea para registrar como ha ido la generacion de la misma
	 * @throws Exception
	 */
	protected void handleGenerarInforme(TareaInformesVO datosInforme, Long idTarea) throws Exception {
		
		try {			
			
			/* Conversión de TareaInformesVO a ParametroCrearInformeVO */
			ParametroCrearInformeVO parametros = new ParametroCrearInformeVO();
			tareaInformesVO2ParametroCrearInformeVO(datosInforme, parametros);
			
			this.getSrvAuditoriaServicio().crearGuardarInforme(parametros);
		}
		catch (Exception e) {
			log.error("Error: al llamar al generar informe: " , e);
		}
	}
	
	/**
	 * Crea la generacion de un informe Federado
	 * @param datosInforme Datos que necesita el informe Federado para lanzarse
	 * @param idTarea Identificador de la tarea para registrar como ha ido la generacion de la misma
	 * @throws Exception
	 */
	protected void handleGenerarInformeFederado(TareaInformesVO datosInforme, Long idTarea) throws Exception {
		
		try {			
			if(logger.isDebugEnabled())logger.debug("dentro de generarInformeFederado del planificador");
			/* Conversión de TareaInformesVO a ParametroCrearInformeVO */
			ParametroCrearInformeVO parametros = new ParametroCrearInformeVO();
			tareaInformesVO2ParametroCrearInformeVOFederado(datosInforme, parametros);
						
			this.getSrvAuditoriaServicio().crearGuardarInformeFederado(parametros);

		}
		catch (Exception e) {
			log.error("Error: al llamar al generar informe: " , e);
		}
	}
	
	
	
	/** 
	 * Se transforma el objeto TareaInformesVO a CrearInformeVO
     * @param datosInforme Objeto TareaInformesVO
     * @param parametros Objeto ParametroCrearInformeVO
     */
	
	private void tareaInformesVO2ParametroCrearInformeVO(TareaInformesVO datosInforme, ParametroCrearInformeVO parametros)
	{
		parametros.setUsuario(datosInforme.getUsuarioInforme());
		parametros.setNombreInforme(datosInforme.getInforme());
		parametros.setRango(datosInforme.getRango());
		parametros.setFormato(datosInforme.getFormato());
		
		String dia = null;
		String mes = null;
		String anio = null;
		Calendar fecha = new GregorianCalendar();
		fecha.setTime(datosInforme.getFechaDesde().getTime());
		
		if (fecha.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
		else
			dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
		
		if (fecha.get(Calendar.MONTH) + 1 < 10)
			mes = "0" + Integer.toString(fecha.get(Calendar.MONTH) + 1);
		else
			mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
		
		anio = Integer.toString(fecha.get(Calendar.YEAR));
						
		parametros.setDiaDesde(dia);
		parametros.setMesDesde(mes);
		parametros.setAnioDesde(anio);
		
		if(logger.isDebugEnabled())logger.debug("Desde- dia: " + dia + " mes: " + mes + " anio: " + anio);
		
		fecha.setTime(datosInforme.getFechaHasta().getTime());
		
		if (fecha.get(Calendar.DAY_OF_MONTH) < 10)
			dia = "0" + Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
		else
			dia = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
		
		if (fecha.get(Calendar.MONTH) + 1 < 10)
			mes = "0" + Integer.toString(fecha.get(Calendar.MONTH) + 1);
		else
			mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
		
		anio = Integer.toString(fecha.get(Calendar.YEAR));
		
		parametros.setDiaHasta(dia);
		parametros.setMesHasta(mes);
		parametros.setAnioHasta(anio);
		
		if(logger.isDebugEnabled())logger.debug("Hasta- dia: " + dia + " mes: " + mes + " anio: " + anio);
	}

	/** 
	 * Se transforma el objeto TareaInformesVO a CrearInformeVO
     * @param datosInforme Objeto TareaInformesVO
     * @param parametros Objeto ParametroCrearInformeVO
     */
	
	private void tareaInformesVO2ParametroCrearInformeVOFederado(TareaInformesVO datosInforme, ParametroCrearInformeVO parametros)
	{
		parametros.setUsuario(datosInforme.getUsuarioInforme());
		parametros.setNombreInforme(datosInforme.getInforme());
		parametros.setRango(datosInforme.getRango());
		parametros.setFormato(datosInforme.getFormato());
	}
	
	/**
	 * Registra en Quartz la ejecucion de la tarea de modificacion descrita en
	 * datosTarea. Se genera un nombre para identificarlo en el quartz a partir
	 * del identificador proporcionado por el modificador.
	 * @param datosTarea
	 * @return TareaModificacionVO
	 * @throws Exception
	 * @see es.pode.planificador.negocio.servicios.SrvPlanificadorServiceBase#handleCrearTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO)
	 */
	protected TareaModificacionVO handleCrearTareaModificacion(TareaModificacionVO datosTarea) throws Exception {
		
		if(logger.isDebugEnabled())logger.debug("PLANIFICADOR handleCrearTareaModificacion");
		TareaModificacionVO result = null;
		String nombre = ConstantesAgrega.NOMBRE_MODIFICADOR + datosTarea.getIdModificacion();
		String grupo = ConstantesAgrega.GRUPO_MODIFICADOR;
		ArrayList<Serializable> parametros = new ArrayList<Serializable>();
		parametros.add(datosTarea.getIdModificacion());
		parametros.add(datosTarea.getUsuario());
		TareaVO tarea = new TareaVO();
		tarea.setGrupoTrabajo(ConstantesAgrega.GRUPO_TRABAJO_MODIFICADOR);
		tarea.setTrabajo(nombre);
		tarea.setFechaInicio(datosTarea.getFechaInicio());
		tarea.setPeriodicidad(CtesPlanificador.NO_PERIODICA);
		tarea.setUsuario(datosTarea.getUsuario());
		tarea.setTrigger(ConstantesAgrega.TRIGGER_MODIFICADOR+datosTarea.getIdModificacion());
		tarea.setGrupoTrigger(ConstantesAgrega.GRUPO_TRIGGER_MODIFICADOR);
		if(logger.isDebugEnabled())logger.debug("Tarea.getGrupoTrabajo() "+tarea.getGrupoTrabajo()+" tarea.getGrupoTrigger() "+tarea.getGrupoTrigger()+" tarea.getTrabajo() "+tarea.getTrabajo()+" tarea.getTrigger() "+tarea.getTrigger());
		
		try {
			TareaVO retorno = handleCrearTarea(tarea, parametros, TareaModificacion.class);
			
			result = new TareaModificacionVO();
			result.setFechaInicio(retorno.getFechaInicio());
			result.setTrabajo(retorno.getTrabajo());
			result.setIdModificacion(datosTarea.getIdModificacion());
			result.setUsuario(retorno.getUsuario());
			Logger.getLogger(this.getClass()).info("Tarea " + result.getTrabajo() + " registrada en el planificador");
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("Error en la creacion de la tarea de modificacion " + datosTarea.getIdModificacion() + ": " + e.getMessage());
			if(logger.isDebugEnabled())logger.debug(e);
			throw new CrearTareaException("No se ha podido crear la tarea de modificacion " + nombre,e);
		}
		
		return result;
	}

	/**
	 * Elimina la tarea programada y la vuelve a crear.
	 * @param datosTarea
	 * @return TareaModificacionVO
	 * @throws Exception
	 * @see es.pode.planificador.negocio.servicios.SrvPlanificadorServiceBase#handleModificarTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO)
	 */
	protected TareaModificacionVO handleModificarTareaModificacion(TareaModificacionVO datosTarea) throws Exception {

		Boolean result = Boolean.FALSE;
		TareaModificacionVO resultVO = null;
		try {
			
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			TrabajoVO trabajo = new TrabajoVO();
			trabajo.setGrupoTrabajo(ConstantesAgrega.GRUPO_TRABAJO_MODIFICADOR);
			trabajo.setTrabajo(datosTarea.getTrabajo());
			trabajo.setUsuario(datosTarea.getUsuario());
			datosJob[0] = trabajo;
			/* Modificación de una tarea */
	    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
	    			+ " usuario: " + trabajo.getUsuario());
	    	
					
			result = handleEliminarTareas(datosJob);
			if(result.booleanValue()) {
				if(logger.isDebugEnabled())logger.debug("Tarea " + trabajo +" eliminada. La creo de nuevo");
				resultVO = handleCrearTareaModificacion(datosTarea);
			}
		} catch (Exception e) {
			String msg = "Error durante la modificacion de la tarea " + datosTarea.getTrabajo() + ": " + e.getMessage();
			Logger.getLogger(this.getClass()).error(msg);
			if(logger.isDebugEnabled())logger.debug(e);
			throw new ModificarTareaException(msg,e);
		}
		return resultVO;
	}

	/**
	 * Creación de los informes de portada
	 * @throws Exception
	 */
	protected void handleInformesPortada() throws Exception {
			
		this.getSrvAuditoriaServicio().crearInformesPortada();
	}
	
	/**
	 * Creación de la imágen aleatoria para sites externos
	 * @throws Exception
	 */
	protected void handlePortadaODE() throws Exception {
			
		this.getSrvGeneracionDinamicaService().generaODEDiario();
	}

	/**
	 * Creación de los informes de portada
	 * @param idioma en el que se crearan los informes
	 * @param fechaActualizacion representa la fecha a partir de la cual lo ODEs se marcan como si fueran nuevos en el informe
	 * @throws Exception
	 */
	protected void handleGenerarCatalogo(String idioma, Calendar fechaActualizacion) throws Exception {
		if(logger.isDebugEnabled())logger.debug("handleGenerarCatalogo");	
			this.getSrvAuditoriaServicio().crearInformeRepositorio(idioma, fechaActualizacion);
	}

	/**
	 * Este metodo obtiene una tarea del tipo de informes
	 * @param tarea nombre de la tarea que se recuperara los datos
	 * @return TareaInformesVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */
	protected TareaInformeCatalogoVO handleObtenerTareaInformeCatalogo(TareaInformeCatalogoVO tarea) throws Exception 
	{
		TareaInformeCatalogoVO tareaModificar = null;
    	
    	try {
    		if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    		
    		Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaInformeCatalogoVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			
			TareaInformeCatalogoVO datosInforme = (TareaInformeCatalogoVO) parametros.get(0);
			String idioma=(String)parametros.get(1);
			if(logger.isDebugEnabled())logger.debug("Recogemos el idioma de los parametros "+ idioma);
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			tareaModificar.setFechaDesde(datosInforme.getFechaDesde());
			tareaModificar.setFechaHasta(datosInforme.getFechaHasta());
			tareaModificar.setFormato(datosInforme.getFormato());
			tareaModificar.setInforme(datosInforme.getInforme());
			tareaModificar.setRango(datosInforme.getRango());
			tareaModificar.setUsuarioInforme(datosInforme.getUsuarioInforme());
			tareaModificar.setIdioma(idioma);
			tareaModificar.setFechaActualizacion(datosInforme.getFechaActualizacion());
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido modificar la tarea de informes " , e);
    		throw e;
    	}

        return tareaModificar;
	}
	/**
	 * Este metodo obtiene una tarea del tipo de generarImagenes
	 * @param datosTarea nombre de la tarea que se recuperara los datos
	 * @return TareaRegenerarImagenesVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */

	@Override
	protected TareaRegenerarImagenesVO handleCrearTareaRegenerarImagemes(TareaRegenerarImagenesVO datosTarea) throws Exception {
		TareaRegenerarImagenesVO tareaCreada = new TareaRegenerarImagenesVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
    	
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
    		    	
			/* Parámetros propios de la tarea */

    		parametrosTarea.add(datosTarea.getTrabajo());


			
			tareaRetornada = handleCrearTarea( tareaGenerica, parametrosTarea, RegenerarImagenes.class);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}
	/**
	 * Regeneracion de imagenes de una tarea
	 * @param idTarea Identificador de la tarea
	 * @return objReindexados  
	 * @throws Exception
	 */
	
	protected String  handleRegeneracionImagenes(Long idTarea) throws Exception {
		String texto = "";
		//ReindexarODEResultadoVO[] resultadosReindexado = null;
		try {
			SrvPublicacionService servicio = this.getSrvPublicacionService();

			Boolean vuelta = servicio.regenerarImagenes( idTarea);
			if(vuelta.booleanValue()==true){
				texto=OK;
			}else{
				texto="KO";
			}
		}
		catch (Exception e) {
			log.error("Error: No se ha podido realizar el reindexado " , e);
			throw e;
		}
		
		return texto;
	}
	
	/**
	 * Modifica los datos de una tarea de regeneración de imágenes previamente registrada.
	 * @param datosTarea Nuevos datos para la tarea de regeneración de imagenes que se desea actualizar.
	 * @param trabajo Datos del trabajo que identifican la tarea en Quartz.
	 * @return TareaRegenerarImagenesVO
	 * @throws Exception
	 */
	
	protected TareaRegenerarImagenesVO handleModificarTareaRegenerarImagenes(TareaRegenerarImagenesVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaRegenerarImagenesVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo) 
					tarea = handleCrearTareaRegenerarImagemes(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la regeneracion de imagenes " , e);
				throw e;
			}		
		
			return tarea;	
	}
	
	/**
	 * Recupera los datos de una tarea de regeneración de imágenes previamente
	 * registrada.
	 * 
	 * @param tarea
	 *            Value Object con alguno de los datos genéricos de la tarea que
	 *            se desea recuperar.
	 * @return Value Object con los datos especificos de la tarea de
	 *         regeneración de imágenes recuperados del planificador.
	 * @throws Exception
	 */
	
	protected TareaRegenerarImagenesVO handleObtenerTareaRegenerarImagenes(TareaVO tarea) throws Exception {
		TareaRegenerarImagenesVO tareaModificar = null;
		
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
		
			Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaRegenerarImagenesVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			//ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);			
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
		}
		catch (Exception e) {
			log.error("Error: No se ha podido modificar la tarea " , e);
			throw e;
		}

		return tareaModificar;
	}


	/** 
	 *  Método para la obtención del informe de carga masiva del trabajo que se le pasa
	 * @param trabajo El trabajo del que se quiere crear el informe
	 * 	@return informeCargaVO VO que contiene la descripción de cómo ha ido la obtención del informe
	 * @throws Exception
	 * */
	protected InformeCargaVO handleObtenerInformeCarga(String trabajo)
			throws Exception {
				return null;
	}

	/** 
	 *  Método para listar los informes de carga masiva
	 * 	@return InformeODEsCargadosVO Array de VO que contiene la información para la generación del informe.
	 * @throws Exception
	 * */
	protected InformeODEsCargadosVO[] handleListarInformesCarga() throws Exception {
		String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_CARGA);
		log.info("Recuperando el contenido del informe que queremos visualizar");
		File fpathDir = new File(pathDir);
		InformeODEsCargadosVO[] listaInformesCarga = null;
		try{
			if (!fpathDir.exists()) {
				log.error("Error: El directorio " + pathDir + " no existe");
			} else {
	
				// Recorremos el directorio donde estan los informes
				File[] arrayList = fpathDir.listFiles();
				List<InformeODEsCargadosVO> ficheros = new ArrayList<InformeODEsCargadosVO>();
				File ficheroTemp = null;
	
				// Por cada fichero encontrado creamos el VO con su nombre y la fecha
				if(arrayList!=null){
					for (int i = 0; i < arrayList.length; i++) {
						ficheroTemp = arrayList[i];
						InformeODEsCargadosVO informeODEsCargadosVO = new InformeODEsCargadosVO();
						informeODEsCargadosVO.setNombreFichero(ficheroTemp.getName());
						
						long timestamp = ficheroTemp.lastModified();
						Date fecha = new Date(timestamp);
						GregorianCalendar calendar = new GregorianCalendar();
						calendar.setTime(fecha);
						informeODEsCargadosVO.setFechaModificacion(calendar);
						ficheros.add(informeODEsCargadosVO);
					}
				}
				listaInformesCarga = ficheros.toArray(new InformeODEsCargadosVO[0]);
			}
		} catch (Exception e){
			log.error("se ha producido una excepción al intentar listar los informes: ", e);
			throw e;
		}
		return listaInformesCarga;
	}

	/** 
	 *  Método para obtener el informe de carga masiva
	 * @param nombreInforme 
	 * 	@return DataHandler Contiene la información para la generación del informe.
	 * @throws Exception
	 * */
	protected DataHandler handleRecuperarInformeCarga(String nombreInforme) throws Exception {
		String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_CARGA);
		log.info("Recuperando los informes generados al hacer la carga masiva");
		File file = null;
		String ruta = pathDir;
		if (!pathDir.endsWith(File.separator)) {
			ruta = ruta + File.separator;
		}
		file = new File(ruta + nombreInforme);

		if (!file.exists()) {
			log.error("El fichero " + file.getAbsolutePath()
					+ "  ha sido borrado o movido");
			throw new Exception("el fichero ha sido borrado o movido");
		}

		FileDataSource fileDS = new FileDataSource(file);
		DataHandler dataHandler = new DataHandler(fileDS);

		return dataHandler;

	}

	/** 
	 *  Método para la eliminación de los informes de carga masiva
	 * @param ficheros lista de informes que se quieren borrar.
	 * 	@return informeCargaVO VO que contiene la descripción de cómo ha ido la eliminación de los informes.
	 * @throws Exception
	 * */
	protected ValidaBajaInformeCargaVO handleEliminarInformesCarga(
			String[] ficheros) throws Exception {
		String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_CARGA);
		List<String> ficherosBorrados = new ArrayList<String>();
		ValidaBajaInformeCargaVO informeCargaVO = new ValidaBajaInformeCargaVO();
		File fichero = null;
		String ruta = pathDir; 
		if (!pathDir.endsWith(File.separator)){
			ruta = ruta + File.separator;
		}
			
		try{
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					fichero = new File(ruta + ficheros[i]);
					if (fichero.delete()){
						ficherosBorrados.add(ficheros[i]);
					} else {
						log.error("no se ha podido eliminar el siguiente informe: " + ficheros[i]);
					}
				}
				informeCargaVO.setDescripcionBaja("informesBorrados.OK");
			} else {
				informeCargaVO.setDescripcionBaja("informesBorrados.FALLO");
			}
		} catch (Exception e){
			log.error("se ha producido una excepción al borrar los informes :" +e);
			informeCargaVO.setDescripcionBaja("informesBorrados.FALLO");
		}
		informeCargaVO.setInformesBorrados(ficherosBorrados.toArray(new String[0]));
		return informeCargaVO;

	}
	
	/**Este metodo recoge los properties
	 * 
	 * @param sKey
	 * 			La clave de la propiedad
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public static String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = SrvPlanificadorServiceBase.class
						.getResourceAsStream(FILE_NAME_PROPERTIES);
				/* Forma mala de hacerlo
				props = new java.util.Properties();
				//Es mala porque entre estas dos líneas props!=null
				// pero todavía no tiene valores útiles y fallaría la llamada
				props.load(fIsSpringProperties);
				*/
				
				//Forma buena
				Properties prop = new java.util.Properties();
				prop.load(fIsSpringProperties);
				props=prop;
			}
			sReturn = props.getProperty(sKey);
			if (logger.isDebugEnabled())
				logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.warn("Excepcion intentando obtener propiedad [" + sKey
					+ "] del fichero de propiedades del planificador[" + e.getCause() + "]");
		}
		// devolvemos la propiedad
		return sReturn;
	}
	
   
    /**
     * Obtención del informe asociado a la ejecución de un trabajo de carga masiva
     * Recuperamos un informe sacando la fecha de la descripcion de un trabajo segun el id que le pasamos
     * @param idTrabajo El id de la tarea
     * @return informe Las filas de las entitys que corresponden con el id de tarea ejecutada que se ha 
     * pasado como parametro
     * @throws Exception
     */
	@Override
	protected RegistroTareaCargaEjecutadaVO[] handleObtenerInformeTrabajoCargaMasiva(
			Long idTrabajo) throws Exception {
		RegistroTareaCargaEjecutadaVO[] informe = null;
    	
    	try {
    		Collection informeCol = this.getTareaEjecutadaDao().load(idTrabajo).getRegistroTareaCargaEjecutadas();
    		if(logger.isDebugEnabled())logger.debug("Tareas de carga ejecutadas: " + informeCol);
    		Iterator it = informeCol.iterator();
    		informe = new RegistroTareaCargaEjecutadaVO[informeCol.size()]; 
    		int i=0;
    		
    		while (it.hasNext()) {
    			RegistroTareaCargaEjecutada reg = (RegistroTareaCargaEjecutada)it.next();
    			informe[i] = new RegistroTareaCargaEjecutadaVO();
    			informe[i].setDescripcion(reg.getDescripcion());
    			informe[i].setFecha(reg.getFecha());
    			informe[i].setEstado(reg.getEstado());
    			informe[i].setCodigo(reg.getCodigo());
    			informe[i].setPathOdeDespublicado(reg.getPathOdeDespublicado());
    			i++;	
    		}
		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido obtener el informe de carga: ",e);
    		throw e;
    	}
    	
        return informe;
	}

	/**
	 * Obtencion del informe de carga masiva
	 * @param parametro ParametroInformeCargaVO donde iran datos relativos a la tarea
	 * @throws Exception
	 */
	protected void handleCrearInformeCargaMasiva(ParametroInformeCargaVO parametro) throws Exception {
		try{
			
			//Mapear el nuevo VO a ParametroInformeGenericoVO
			if(logger.isDebugEnabled())logger.debug("Entramos en CrearInformeCargaMasiva para hacer la llamada a auditoria");
			SrvAuditoriaServicio auditoria = this.getSrvAuditoriaServicio();
			//Se realiza la llamada al servicio de auditoria para generar el informe
			ParametroInformeGenericoVO res = (es.pode.auditoria.negocio.servicios.ParametroInformeGenericoVO)this.getBeanMapper().map(parametro,
					es.pode.planificador.negocio.servicios.ParametroInformeCargaVO.class, "DEF_MAPPING_PARAMETROINFORMECARGAVO_PARAMETROINFORMEGENERICOVO");
			auditoria.crearAlmacenarInforme(res);
			if(logger.isDebugEnabled())logger.debug("Ya sea hecho la llamada");
		}catch (Exception e){
			log.error("Error: No se ha podido obtener el informe de carga masiva: ",e);
    		throw e;
		}
		
		
		
	}

	/**
	 * Este metodo obtiene una tarea del tipo de generar Sitemaps
	 * @param datosTarea nombre de la tarea que se recuperara los datos
	 * @return TareaGenerarSitemapsVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */
	protected TareaGenerarSitemapsVO handleCrearTareaGenerarSitemaps(
			TareaGenerarSitemapsVO datosTarea) throws Exception {
		TareaGenerarSitemapsVO tareaCreada = new TareaGenerarSitemapsVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
    	
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
    		    	
			/* Parámetros propios de la tarea */

    		parametrosTarea.add(datosTarea.getTrabajo());


			
			tareaRetornada = handleCrearTarea( tareaGenerica, parametrosTarea, GenerarSitemaps.class);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. ", e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	/**
	 * Este metodo obtiene una tarea del tipo de lanzar RSS
	 * @param datosTarea nombre de la tarea que se recuperara los datos
	 * @return TareaLanzarRSSVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */
	protected TareaLanzarRSSVO handleCrearTareaLanzarRSS(
			TareaLanzarRSSVO datosTarea) throws Exception {
		
		TareaLanzarRSSVO tareaCreada = new TareaLanzarRSSVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
    	
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Parámetros propios de la tarea */
    		parametrosTarea.add(datosTarea.getTrabajo());

			tareaRetornada = handleCrearTarea( tareaGenerica, parametrosTarea, LanzarRSS.class);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. ", e);
    		throw e;			
		}
		return tareaCreada;
	}
	
//	/**
//	 * Creación de los ficheros sitemap.xml
//	 * @throws Exception
//	 */
//	protected void handleGenerarSitemaps() throws Exception {
//		if(logger.isDebugEnabled())logger.debug("GENERAR SITEMAPS");
//		this.getSrvSitemapService().generarSitemapXml();
//	
//	}
	/**
	 * Generación de Sitemaps de una tarea
	 * @param idTarea Identificador de la tarea
	 * @return objGenerados 
	 * @throws Exception
	 */
	protected String handleGenerarSitemaps(Long idTarea) throws Exception {
		if(logger.isDebugEnabled())logger.debug("GENERAR SITEMAPS");
		String texto = "KO";
		try{
			/*
			Boolean vuelta=this.getSrvSitemapService().generarSitemapXml(idTarea);
			if(vuelta.booleanValue()==true){
				texto=OK;
			}else{
				texto="KO";
			}
			*/
			this.getSrvSitemapService().generarSitemapXml();
		}catch (Exception e) {
			log.error("Error: No se ha podido realizar la generacion de sitemaps ", e);
			return "KO";
		}

		return "OK";
		//return texto;
	}

	
//	/**
//	 * Creación de los RSS
//	 * @throws Exception
//	 */
//	protected void handleLanzarRSS() throws Exception {
//			
//		this.getSrvAgregadorRssService().crearXML();
//	}
	/**
	 * Lanzamiento de RSS de una tarea
	 * @param idTarea Identificador de la tarea
	 * @return String
	 * @throws Exception
	 */
	protected String handleLanzarRSS(Long idTarea) throws Exception {
		if(logger.isDebugEnabled())logger.debug("LANZAR RSS");
		String texto = "KO";
		try{
			this.getSrvAgregadorRssService().crearXML(idTarea);
			texto=OK;
		} catch (Exception e) {
			log.error("Error: No se ha podido realizar el reindexado ", e);
			return "KO";
		}
		return texto;
	}

	/**
	 * Modifica los datos de una tarea de generación de Sitemaps previamente registrada.
	 * @param datosTarea Nuevos datos para la tarea de generación de Sitemaps que se desea actualizar.
	 * @param trabajo Datos del trabajo que identifican la tarea en Quartz.
	 * @return TareaGenerarSitemapsVO con los datos de la tarea
	 * @throws Exception
	 */
	protected TareaGenerarSitemapsVO handleModificarTareaGenerarSitemaps(
			TareaGenerarSitemapsVO datosTarea, TrabajoVO trabajo)
			throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaGenerarSitemapsVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo) 
					tarea = handleCrearTareaGenerarSitemaps(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la generacion de sitemaps ", e);
				throw e;
			}		
		
			return tarea;	
	}

	/**
	 * Modifica los datos de una tarea de lanzar RSS previamente registrada.
	 * @param datosTarea Nuevos datos para la tarea de lanzar RSS que se desea actualizar.
	 * @param trabajo Datos del trabajo que identifican la tarea en Quartz.
	 * @return TareaLanzarRSSVO con los datos de la tarea
	 * @throws Exception
	 */
	protected TareaLanzarRSSVO handleModificarTareaLanzarRSS(
			TareaLanzarRSSVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaLanzarRSSVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo) 
					tarea = handleCrearTareaLanzarRSS(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar lanzamiento de RSS " , e);
				throw e;
			}		
		
			return tarea;	
	}

	/**
	 * Recupera los datos de una tarea de generación de Sitemaps previamente
	 * registrada.
	 * 
	 * @param tarea
	 *            Value Object con alguno de los datos genéricos de la tarea que
	 *            se desea recuperar.
	 * @return TareaGenerarSitemapsVO Value Object con los datos especificos de la tarea de
	 *         generación de Sitemaps recuperados del planificador.
	 * @throws Exception
	 */
	protected TareaGenerarSitemapsVO handleObtenerTareaGenerarSitemaps(
			TareaVO tarea) throws Exception {
		TareaGenerarSitemapsVO tareaModificar = null;
		
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
		
			Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaGenerarSitemapsVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			//ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);			
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
		}
		catch (Exception e) {
			log.error("Error: No se ha podido modificar la tarea " , e);
			throw e;
		}

		return tareaModificar;
	}

	/**
	 * Recupera los datos de una tarea de lanzamiento de RSS-s previamente
	 * registrada.
	 * 
	 * @param tarea
	 *            Value Object con alguno de los datos genéricos de la tarea que
	 *            se desea recuperar.
	 * @return TareaLanzarRSSVO Value Object con los datos especificos de la tarea de
	 *         lanzamiento de RSS-s recuperados del planificador.
	 * @throws Exception
	 */
	protected TareaLanzarRSSVO handleObtenerTareaLanzarRSS(TareaVO tarea)
			throws Exception {
		TareaLanzarRSSVO tareaModificar = null;
		
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
		
			Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaLanzarRSSVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			//ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);			
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
		}
		catch (Exception e) {
			log.error("Error: No se ha podido modificar la tarea " , e);
			throw e;
		}

		return tareaModificar;
	}

	/**
	 * Recupera las tareas de carga de odes que se han ejecutado
	 * 
	 
	 * @return TareaEjecutadaExplotacionVO[] Value Object con los datos especificos de la tarea de
	 *         carga de odes del planificador.
	 * @throws Exception
	 */
	protected TareaEjecutadaExplotacionVO[] handleObtenerTareasCargaEjecutadas()
			throws Exception {
		List vuelta =null;
		TareaEjecutadaExplotacionVO[] explotaciones=null;
		
		try{
			TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
			TareasCargaOdesEjecutadosCriteria criterio=new TareasCargaOdesEjecutadosCriteria();
			criterio.setTipoTarea(SrvPlanificadorServiceImpl.getPropertyValue("cargaODES"));
		//	criterio.setFechaBaja(null);
			vuelta = tareaEjecutadaDao.buscarTareasCargaOdesEjecutados(tareaEjecutadaDao.TRANSFORM_TAREAEJECUTADAVO,criterio);
			logger.info("Recuperamos "+vuelta.size()+" tareas de carga ejecutadas");
		}catch (Exception e) {
			logger.error("Error recogiendo las tareas ejecutadas ",e);
		}
		try{
			explotaciones=new TareaEjecutadaExplotacionVO[vuelta.size()];
			
			for(int i=0;i<vuelta.size();i++){
				TareaEjecutadaExplotacionVO explotacion=new TareaEjecutadaExplotacionVO();
				TareaEjecutadaVO tarea=(TareaEjecutadaVO)vuelta.get(i);
				
				logger.info("Recogemos la tarea de identificador "+tarea.getId()+" y nombre "+tarea.getDescripcion());
				String nombreTarea=tarea.getDescripcion();
				String desc=tarea.getDescripcionTarea();
				Calendar fechaFin = tarea.getFechaFin();
				String nombreLote=tarea.getNombreLote();
				String pathCarga=tarea.getPathCarga();
				Long idTarea = tarea.getId();
				
				
				
//				Long listaRegistros=ServiceLocator.instance().getSrvRegistroPlanificadorService().obtenerRegistrosConRutas(idTarea);
				//TODO Llamar al metodo registroTareaCarga.obtenerIdentificadorOdesTarea para obtener la lista de odes cargados en esa tarea correctamente (estado OK) y que no hayan sido despublicados.
				//Si la lista es mayor que cero explotacion.setDespublicado=true, es decir, existen odes sin despublicar.
				IdentificadoresTareaCriteria criteria=new IdentificadoresTareaCriteria();
//				Collection informeCol = this.getTareaEjecutadaDao().load(idTarea).getRegistroTareaCargaEjecutadas();
//				Iterator it = informeCol.iterator();
				criteria.setPathOdeDespublicado(Boolean.FALSE);
				criteria.setIdTarea(idTarea);
				criteria.setEstado(OK);
				
//				listaCargas =this.getRegistroTareaCargaEjecutadaDao().buscarRegistroTareaPorNombreZip(this.getRegistroTareaCargaEjecutadaDao().TRANSFORM_REGISTROTAREACARGAEJECUTADAVO,criteria);
//				todasCargas=(RegistroTareaCargaEjecutadaVO[])(listaCargas.toArray(new RegistroTareaCargaEjecutadaVO[listaCargas.size()]));
//				
				
				
				
				RegistroTareaCargaEjecutadaDao registroTareaCargaII=this.getRegistroTareaCargaEjecutadaDao();
				List lista = registroTareaCargaII.obtenerIdentificadorOdesTarea(this.getRegistroTareaCargaEjecutadaDao().TRANSFORM_REGISTROTAREACARGAEJECUTADAVO,criteria);
				if (lista.size()>0){
					logger.info("Existen ODEs sin depublicar");
					explotacion.setDespublicado(Boolean.TRUE);
				}else{
					logger.info("No existen ODEs sin depublicar");
					explotacion.setDespublicado(Boolean.FALSE);
				}
				
				Long listaRegistros=null;
				try{
					RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
					logger.info("Hacemos la llamada con el criteria para el identificador "+idTarea);
					listaRegistros=registroTareaCarga.buscarRegistrosConRuta(idTarea);
					logger.info("Recogemos una lista con "+listaRegistros+" carpetas");
				}catch (Exception e) {
					logger.error("Error al recuperar los registros ",e);
				}
				
				logger.info("Tiene "+listaRegistros+" odes la tarea");

				explotacion.setId(idTarea);
				explotacion.setDescripcion(nombreTarea);
				explotacion.setDescripcionTarea(desc);
				explotacion.setFechaFin(fechaFin);
				explotacion.setNombreLote(nombreLote);
				explotacion.setPathCarga(pathCarga);
				if(listaRegistros!=null&&listaRegistros>0){
					logger.info("Exiten carpetas");
					explotacion.setCarpeta(true);	
				}else{
					logger.info("No tenemos carpetas, todos cuelgan del path ode");
					explotacion.setCarpeta(false);
				}
				explotaciones[i]=explotacion;	
			}
		}catch (Exception ei) {
			logger.error("Error poblando el VO de vuelta",ei);
		}
		
		
		return explotaciones;
	}
	
	/**
     * Obtiene los registros de los odes que siguen publicados y en grupos de pathOdes
     * @param idTarea Identificador de la tarea
     * @return InformacionCargaVO[] Devuelve el VO relleno de los datos correspondientes al registro agrupado por lo pahtOde distintos
     * @throws Exception
     */
	protected InformacionCargaVO[] handleObtenerCarpetasDeRegistro(Long idTarea)
			throws Exception {
		InformacionCargaVO[] informacion=null;
		List listaIdentificadores = null;
		List listaIdentificadoresCarpeta = null;
		IdentificadoresTareaCarpetaCriteria carpetaCriteria=new IdentificadoresTareaCarpetaCriteria();

		try{
			RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
			logger.info("Hacemos la llamada para el identificador "+idTarea);
			List listaCarpetas=registroTareaCarga.buscarCarpetasRegistro(idTarea);
			logger.info("Recogemos "+listaCarpetas.size()+" carpetas distintas");
			if(listaCarpetas.size()>0){
				Iterator i=listaCarpetas.iterator();
				int k=0;
				informacion=new InformacionCargaVO[listaCarpetas.size()];
				ArrayList<InformacionCargaVO> listaInformaciones=new ArrayList<InformacionCargaVO>();
				ArrayList<String> listaIdent=new ArrayList<String>();
				while(i.hasNext()){
					String pathOde =(String) i.next();
					if(pathOde==null || (pathOde!=null && pathOde.equals(""))){
						IdentificadoresCarpetasCriteria criteria=new IdentificadoresCarpetasCriteria();
						criteria.setIdTarea(idTarea);
						criteria.setPathOde(pathOde);
						criteria.setEstado(OK);
//						criteria.setPathOdeDespublicado(Boolean.FALSE);
						logger.info("Obtenemos la lista de identificadores que son parte de la carpeta "+pathOde);
						listaIdentificadores =registroTareaCarga.obtenerIdentificadoresOdes(RegistroTareaCargaEjecutadaDao.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, criteria);
						if(logger.isDebugEnabled()) logger.debug("Obtenemos "+listaIdentificadores.size()+" odes para la carpeta "+pathOde);
						for(int j=0;j<listaIdentificadores.size();j++){
								listaIdent.add(((RegistroTareaCargaEjecutadaVO)listaIdentificadores.get(j)).getId_mec());
						}
	
					}else{
						InformacionCargaVO infor=new InformacionCargaVO();
						infor.setPathOde(pathOde);
						IdentificadoresCarpetasCriteria criteria=new IdentificadoresCarpetasCriteria();
						criteria.setIdTarea(idTarea);
						criteria.setPathOde(pathOde);
						criteria.setEstado(OK);
//						criteria.setPathOdeDespublicado(Boolean.FALSE);
						logger.info("Obtenemos la lista de identificadores que son parte de la carpeta "+pathOde);
						listaIdentificadores =registroTareaCarga.obtenerIdentificadoresOdes(RegistroTareaCargaEjecutadaDao.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, criteria);
						
						//Obtenemos la lista de ODEs despublicados 
						
//						Collection informeCol = this.getTareaEjecutadaDao().load(idTarea).getRegistroTareaCargaEjecutadas();
//						Iterator it = informeCol.iterator();
						carpetaCriteria.setPathOde(pathOde);
						carpetaCriteria.setPathOdeDespublicado(Boolean.FALSE);
						carpetaCriteria.setIdTarea(idTarea);
						carpetaCriteria.setEstado(OK);
						logger.info("Obtenemos la lista de ODEs de la carpeta: "+pathOde);
						listaIdentificadoresCarpeta =registroTareaCarga.obtenerIdentificadorOdesTareaCarpeta(RegistroTareaCargaEjecutadaDao.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, carpetaCriteria);

						
						if(logger.isDebugEnabled()) logger.debug("Obtenemos "+listaIdentificadores.size()+" odes para la carpeta "+pathOde);
						String[] lista=new String[listaIdentificadoresCarpeta.size()];
						for(int j=0;j<listaIdentificadoresCarpeta.size();j++){
							lista[j]=((RegistroTareaCargaEjecutadaVO)listaIdentificadoresCarpeta.get(j)).getId_mec();
						}
						if (listaIdentificadoresCarpeta.size()>0){
							infor.setDespublicado(true);
						}else{
							infor.setDespublicado(false);
						}
						infor.setNumOdes(Long.valueOf(listaIdentificadores.size()));
						infor.setIdentificadores(lista);
						listaInformaciones.add(infor);
						k++;
					}
					
				}if(listaIdent.size()>0){//Tenemos carpetas nulas y/o ""
					logger.info("Recogemos los identificadores que no son parte de ninguna carpeta  (null o '') y los metemos en un VO");
					InformacionCargaVO infor=new InformacionCargaVO();
					carpetaCriteria =new IdentificadoresTareaCarpetaCriteria();
					carpetaCriteria.setPathOdeDespublicado(false);
					carpetaCriteria.setIdTarea(idTarea);
					carpetaCriteria.setEstado(OK);
					carpetaCriteria.setPathOde("");
					listaIdentificadoresCarpeta =registroTareaCarga.obtenerIdentificadorOdesTareaCarpeta(registroTareaCarga.TRANSFORM_REGISTROTAREACARGAEJECUTADAVO, carpetaCriteria);
					logger.info("ListaIndentificadoresCarpeta: " + listaIdentificadoresCarpeta);
					if (listaIdentificadoresCarpeta.size()>0){
						infor.setDespublicado(true);
					}else{
						infor.setDespublicado(false);
					}
					infor.setPathOde(SrvPlanificadorServiceImpl.getPropertyValue("sinCarpeta"));
					infor.setNumOdes(Long.valueOf(listaIdent.size()));
					String[] lista=new String[listaIdentificadoresCarpeta.size()];
					for(int j=0;j<listaIdentificadoresCarpeta.size();j++){
						lista[j]=((RegistroTareaCargaEjecutadaVO)listaIdentificadoresCarpeta.get(j)).getId_mec();
					}
					infor.setIdentificadores(lista);
					listaInformaciones.add(infor);
				}
				informacion=listaInformaciones.toArray(new InformacionCargaVO[0]);
			}else{
				informacion=new InformacionCargaVO[0];
			}
			
			logger.info("Recogemos los valores "+Arrays.toString(informacion));
		}catch(Exception e){
			logger.error("Error al recuperar los datos de las carpetas de la tarea",e);
		}
		return informacion;
	}

	/**
	 * Elimina las tareas de carga de odes que que correspondan al identificador
	 * @param idTarea
	 * 					String[] Identificador(es) de tareas que se quieran eliminar
	 
	 * @return Boolean Devuelve true si la eliminación ha ido bien, false en otro caso
	 * @throws Exception
	 */
	protected Boolean handleEliminarTareasCargaEjecutada(String[] idTarea)
			throws Exception {
		//Tenemos que eliminar las entradas en base de datos tareas_ejecutadas y también en tareas_carga_ejecutadas
		Boolean borrado=Boolean.TRUE;
		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
		for(int i=0;i<idTarea.length;i++){
			TareaEjecutada tarea=null;
			try{
				tarea = tareaEjecutadaDao.load(Long.valueOf(idTarea[i]));	
			}catch(Exception e){
				logger.error("Error al intentan recuperar las entradas de las tareas en tareaEjecutada");
				borrado=Boolean.FALSE;
			}
			try{
				RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
				OdesPorIdCargaCriteria criteria=new OdesPorIdCargaCriteria();
				criteria.setIdCarga(Long.valueOf(idTarea[i]));
//				RegistroCargaTrabajoCriteria criteria=new RegistroCargaTrabajoCriteria();
//				criteria.setIdTrabajo(tarea);
//				//Se deben tomar encuetna todas las opciones:
//				criteria.setEstado("OK");
//				criteria.setPathOdeDespublicado(Boolean.TRUE);//Todo OK
//				
//				criteria.setEstado("OK");
//				criteria.setPathOdeDespublicado(Boolean.FALSE);
//				
//				
//				criteria.setEstado("ERROR");
//				criteria.setPathOdeDespublicado(Boolean.TRUE);
//				
//				criteria.setEstado("ERROR");
//				criteria.setPathOdeDespublicado(Boolean.FALSE);//Todo KO
				
				List registros = registroTareaCarga.obtenerTrabajoCargaPorId(criteria);
				logger.info("Obtenemos los registros relacionados con esa tarea");
				registroTareaCarga.remove(registros);
				if(logger.isDebugEnabled())logger.debug("Hemos borrado los registros");
//				for(int j=0;j<registros.size();j++){
//					registroTareaCarga.remove((RegistroTareaCargaEjecutada)registros.get(j));
//				}
			}catch(Exception e){
				logger.error("Error al intentan borrar las entradas de las tareas en RegistroTareaCargaEjecutada");
				borrado=Boolean.FALSE;
			}try{
				tareaEjecutadaDao.remove(tarea);
				if(logger.isDebugEnabled())logger.debug("Hemos borrado la tarea");
			}catch(Exception e){
				logger.error("Error al intentar borrar las entradas de las tareas en tareaEjecutada");
				borrado=Boolean.FALSE;
			}
			logger.info("Hemos borrado la entrada "+idTarea[i]);
		}
		return borrado;
	}

	/**
	 * Metodo para consultar las tareas de carga que coincidan con los identificadores del array de Long. 
	 * @param identificadores un array de Long
	 * @return TareaEjecutadaVO[]
	 * 				Un array de TareaEjecutadaVO
	 * @throws Exception
	 */
	protected TareaEjecutadaVO[] handleConsultarTareaEjecutadasCarga(
			Long[] identificadores) throws Exception {
		TareaEjecutadaVO[] todasCargas=null;
		try{
			CargasEjecutadasPorIdCriteria criterio=new CargasEjecutadasPorIdCriteria();
			criterio.setIds(identificadores);
			List listaCargas;//=new ArrayList();
			listaCargas =this.getTareaEjecutadaDao().consultarCargasEjecutadas(this.getTareaEjecutadaDao().TRANSFORM_TAREAEJECUTADAVO, criterio);
			todasCargas=(TareaEjecutadaVO[])(listaCargas.toArray(new TareaEjecutadaVO[listaCargas.size()]));
			if(logger.isDebugEnabled())logger.debug("Obtenemos todos las cargas de longitud "+todasCargas.length);
		}catch(Exception e){
			logger.error("Error al hacer la consulta para las tareas ["+ Arrays.toString(identificadores)+"]");
			throw new Exception("Error al hacer la consulta para las tareas ["+ Arrays.toString(identificadores)+"]");
		}
		return todasCargas;
	}

	/**
	 * Metodo para buscar las tareas de carga que coincidan con el texto de entrada en el nombre lote. 
	 * @param nombre String texto que debe ser similar al nombre del lote de la tarea
	 * @return TareaEjecutadaVO[]
	 * 				Un array de TareaEjecutadaVO
	 * @throws Exception
	 */
	protected TareaEjecutadaVO[] handleBuscarPorLote(String nombre)
			throws Exception {
		TareaEjecutadaVO[] todasCargas=null;
		try{
			TareasEjecutadasPorNombreLoteCriteria criteria=new TareasEjecutadasPorNombreLoteCriteria();
			nombre=this.concatenarBusqueda(nombre);
			criteria.setNombreLote(nombre);
			criteria.setTipoTarea(SrvPlanificadorServiceImpl.getPropertyValue("cargaODES"));

			List listaCargas=new ArrayList();
			listaCargas =this.getTareaEjecutadaDao().buscarTareasPorNombreLote(this.getTareaEjecutadaDao().TRANSFORM_TAREAEJECUTADAVO, criteria);
			todasCargas=(TareaEjecutadaVO[])(listaCargas.toArray(new TareaEjecutadaVO[listaCargas.size()]));
			if(logger.isDebugEnabled())logger.debug("Obtenemos todas las cargas de longitud "+todasCargas.length+" que su nombre de lote se asemeja a "+nombre);
		}catch(Exception e){
			logger.error("Error al hacer la consulta para las tareas que tenga el nombre de lote semejante a["+ nombre+"]");
			throw new Exception("Error al hacer la consulta para las tareas que tenga el nombre de lote semejante a["+ nombre+"]");
		}
		return todasCargas;
	}

	/**
	 * Metodo para buscar los odes publicados mediante alguna carga masiva que coincidan con el texto de entrada en el nombre zip. 
	 * @param nombre String texto que debe ser similar al nombre del zip del ode
	 * @return RegistroTareaCargaEjecutadaVO[]
	 * 				Un array de RegistroTareaCargaEjecutadaVO
	 * @throws Exception
	 */
	protected RegistroTareaCargaEjecutadaVO[] handleBuscarPorZip(String nombre)
			throws Exception {
		RegistroTareaCargaEjecutadaVO[] todasCargas=null;
		try{
			CargasEjecutadasPorNombreZipCriteria criteria=new CargasEjecutadasPorNombreZipCriteria();
			criteria.setEstado(OK);
			criteria.setPathOdeDespublicado(Boolean.FALSE);
			nombre=this.concatenarBusqueda(nombre);
			criteria.setNombreZip(nombre);
			List listaCargas=new ArrayList();
			listaCargas =this.getRegistroTareaCargaEjecutadaDao().buscarRegistroTareaPorNombreZip(this.getRegistroTareaCargaEjecutadaDao().TRANSFORM_REGISTROTAREACARGAEJECUTADAVO,criteria);
			todasCargas=(RegistroTareaCargaEjecutadaVO[])(listaCargas.toArray(new RegistroTareaCargaEjecutadaVO[listaCargas.size()]));
			if(logger.isDebugEnabled())logger.debug("Obtenemos todos los odes de longitud "+todasCargas.length+" que su nombre de zip se asemeja a "+nombre);
		}catch(Exception e){
			logger.error("Error al hacer la consulta para los odes que tenga el nombre de zip semejante a["+ nombre+"] ",e);
			throw new Exception("Error al hacer la consulta para los odes que tenga el nombre de zip semejante a["+ nombre+"]");
		}
		return todasCargas;
	}

	private String concatenarBusqueda (String texto) throws Exception{
		try {
			logger.debug("titulo" + texto);
			StringBuffer busqueda = new StringBuffer("%");
			StringTokenizer token = new StringTokenizer(texto, " ");
			while ((token.hasMoreElements())){
				busqueda.append(token.nextElement().toString()).append("%");
			}
			logger.info("La busqueda se realizará por los siguientes criterios: " + busqueda.toString());
			return busqueda.toString();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/**
	 * A partir de un identificador de carga masiva, devuelve un array con los
	 * identificadores de los ODEs que se han cargado correctamente.
	 * 
	 * @param idTarea
	 *            Identificador de la tarea.
	 * @return Array con los identificadores MEC de los ODEs publicados por la
	 *         carga masiva.
	 * @see es.pode.planificador.negocio.servicios.SrvPlanificadorServiceBase#handleObtenerODEsPublicadosEnCarga(java.lang.Long)
	 */

	protected OdeCargaVO[] handleObtenerODEsPublicadosEnCarga(Long idTarea)
			throws Exception {
		
		//para almacenar el array de salida
		List<OdeCargaVO> idMec = new ArrayList<OdeCargaVO>();
		TareaEjecutada tarea=null;
		TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();
		try{
			tarea = tareaEjecutadaDao.load(idTarea);	
		}catch(Exception e){
			logger.error("Error al intentar recuperar las entradas de las tareas en tareaEjecutada", e);
		}
		try{
			
			RegistroTareaCargaEjecutadaDao registroTareaCarga=this.getRegistroTareaCargaEjecutadaDao();
			RegistroCargaTrabajoCriteria criteria=new RegistroCargaTrabajoCriteria();
			criteria.setIdTrabajo(tarea);
			criteria.setEstado(OK);
			criteria.setPathOdeDespublicado(Boolean.FALSE);
			List resultset = registroTareaCarga.obtenerTrabajosCargaMasiva(criteria);
			logger.debug("Recibimos la tarea con id: " + idTarea);
			logger.info("El número de registros obtenidos es de: "+ resultset.size());
			
			for (Iterator iter = resultset.iterator(); iter.hasNext();) {
				RegistroTareaCargaEjecutada registro = (RegistroTareaCargaEjecutada) iter.next();
				OdeCargaVO id = new OdeCargaVO();
				id.setId(registro.getId_mec());
				id.setTitulo(registro.getTitulo());
				idMec.add(id);
			}
			
//			Iterator<RegistroTareaCargaEjecutada> i = resultset.iterator();
//			
//			for (int j = 0; j < resultset.size(); j++){
//				OdeCargaVO id = new OdeCargaVO();
//				//vamos insertando los identificadores MEC de los ODEs publicados dentro del array de salida
//				id.setId(i.next().getId_mec()) ;
//				id.setTitulo(i.next().getTitulo());
//				idMec[j]=id;
//			}
			
		}catch(Exception e){
			logger.error("Error al intentar recuperar los registros", e);
		
		}
		return idMec.toArray(new OdeCargaVO[idMec.size()]);
	}

	 /**
     * Creación de una tarea de despublicacion de ODEs
     * @param tarea Datos de la tarea
     * @return tareaCreada Los datos de la tarea creada
     * @throws Exception
     *	
     */
	protected TareaDespublicarODEsVO handleCrearTareaDespublicarODEs(
			TareaDespublicarODEsVO tarea) throws Exception {
    	log.info("Entra en el servicio de planificador, método CrearTareaDespublicarODEs");
		TareaDespublicarODEsVO tareaCreada = new TareaDespublicarODEsVO();
    	TareaVO tareaRetornada = null;
    	TareaVO tareaGenerica = new TareaVO();
    	List<Serializable> parametrosTarea = new ArrayList<Serializable>();
    	String msgDespublicado = CtesPlanificador.MSG_DESPUBLICADO;
    	String msgNoDespublicado = CtesPlanificador.MSG_NODESPUBLICADO;
    	 
    	try {
    		tareaGenerica.setTrabajo(tarea.getTrabajo());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTrabajo() "+tareaGenerica.getTrabajo());
    		tareaGenerica.setTrigger(tarea.getTrigger());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTrigger() "+tareaGenerica.getTrigger());
    		tareaGenerica.setFechaInicio(tarea.getFechaInicio());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getFechaInicio() "+tareaGenerica.getFechaInicio());
    		tareaGenerica.setPeriodicidad(tarea.getPeriodicidad());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getPeriodicidad() "+tareaGenerica.getPeriodicidad());
    		tareaGenerica.setGrupoTrabajo(tarea.getGrupoTrabajo());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getGrupoTrabajo() "+tareaGenerica.getGrupoTrabajo());
    		tareaGenerica.setGrupoTrigger(tarea.getGrupoTrigger());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getGrupoTrigger() "+tareaGenerica.getGrupoTrigger());
    		tareaGenerica.setUsuario(tarea.getUsuario());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getUsuario() "+tareaGenerica.getUsuario());
    		tareaGenerica.setTipoTarea(tarea.getTipoTarea());
    		if(logger.isDebugEnabled())logger.debug("tareaGenerica.getTipoTarea() "+tareaGenerica.getTipoTarea());
    		
    		if(logger.isDebugEnabled())logger.debug("Tarea generica: " + tareaGenerica);
    		
    		/* Mensajes internacionalizados que se muestran al incluir ODEs en la plataforma */
    		if (null != tarea.getMsgDespublicado())
    			msgDespublicado = tarea.getMsgDespublicado();

    		if (null != tarea.getMsgNoDespublicado())
    			msgNoDespublicado = tarea.getMsgNoDespublicado();

    		
    		/* Validamos que existan el directorios de despublicar ODEs */
//    		File fODEs = new File(tarea.getPathExcel()); 
//    		if (!fODEs.exists()) {
//    			if(logger.isDebugEnabled())logger.debug("El directorio de despublicacion de ODEs no existe. " + fODEs.getAbsolutePath());
//    			tareaCreada.setError("tareas.NoExisteDirectorioExcel");
//    			return tareaCreada;
//    		}
    		//Vamos a obtener el nombre del fichero
    		
//    		String ruta=tarea.getPathExcel();
//    		String[] splitRuta=ruta.split("/");
    		/* Parámetros propios de la tarea */
    		//Vamos a insertar los identificadores obtenidos desde el excel
//    		String[] identificadores=tarea.getIdentificadores();
    		
//    		parametrosTarea.add(splitRuta[splitRuta.length-1]);
//    		parametrosTarea.add(identificadores);
    		parametrosTarea.add(tarea.getIdentificadores());
    		parametrosTarea.add(msgDespublicado);
    		parametrosTarea.add(msgNoDespublicado);
    		parametrosTarea.add(tarea.getTrabajo());
    		parametrosTarea.add(tarea.getDesdePortal());
    		parametrosTarea.add(tarea.getIdTareaCarga());
    		tareaRetornada = handleCrearTarea(tareaGenerica, parametrosTarea, DespublicarODEs.class);
    		
    		if(logger.isDebugEnabled())logger.debug("Parámetros de la tarea: " + parametrosTarea);
    		
    		/* Cargamos los datos de la tarea creada en el objeto */
    		tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
    		tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
    		tareaCreada.setTrigger(tareaRetornada.getTrigger());
    		tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrabajo());
    		tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
    		tareaCreada.setCron(tareaRetornada.getCron());
    		tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
    		tareaCreada.setIdentificadores(tarea.getIdentificadores());			
    		tareaCreada.setTipoTarea(tarea.getTipoTarea());
    		
			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada);
		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea " , e);
    		throw e;
    	}
    	
        return tareaCreada;
	}

	 /**
     * Modificación de los datos de una tarea de despublicacion de ODEs
     * @param datosTarea Los datos que se quieren modificar en la tarea
     * @param trabajo El identificativo de la tarea a modificar
     * @return 	TareaDespublicarODEsVO Devuelve la tarea modificada
     * @throws Exception
     * 
     * Se opta por eliminar el trabajo y crear uno nuevo ya que se permite modificar datos que pertenecen 
     * a la identificación de la tarea y que se almacenan en el contexto
     */
	protected TareaDespublicarODEsVO handleModificarTareaDespublicarODEs(
			TareaDespublicarODEsVO datosTarea, TrabajoVO trabajo)
			throws Exception {
		/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
    	Boolean boo = false;
    	TareaDespublicarODEsVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = trabajo;
    		
    		
    		
    		/* Validamos que existan los directorios de carga de ODEs */
//    		File fODEs = new File(datosTarea.getPathExcel()); 
//    		if (!fODEs.exists()) {
//    			if(logger.isDebugEnabled())logger.debug("El directorio del archivo excel no existe. " + fODEs.getAbsolutePath());
//    			tarea.setError("tareas.NoExisteDirectorioODEs");
//    			return tarea;
//    		}
    		if (datosTarea.getIdentificadores()==null){
	    		Scheduler agenda = Planificador.getAgenda();
	//    		
	    		JobDetail trabajo2 = agenda.getJobDetail(trabajo.getTrabajo(), trabajo.getGrupoTrabajo());
	    		ArrayList parametros = (ArrayList)trabajo2.getJobDataMap().get(CtesPlanificador.PARAMETROS);
	    		String[] identificadores =  (String[]) parametros.get(0);
	    		datosTarea.setIdentificadores(identificadores);
    		}
//    		String nombreViejo=(String)parametros.get(1);
    		
    		
    		
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		
    		if (boo.booleanValue()) {
    			if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.toString());
    			tarea = handleCrearTareaDespublicarODEs(datosTarea);
    		}
    		//Eliminar el temporal en un privado y llamar desde el servicio publico y desde aqui
//    		this.eliminarTemporal(nombreViejo);
    		
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea de carga de ODEs " , e);
    		throw e;
    	}		
	
    	return tarea;	  
	}

	/**
     * Obtenemos una tarea que queremos modificar en la despublicacion de ODEs
     * @param tarea Tarea a modificar
     * @return TareaDespublicarODEsVO La tarea modificada
     * @throws Exception
     */
	protected TareaDespublicarODEsVO handleObtenerTareaDespublicarODEs(
			TareaVO tarea) throws Exception {
		TareaDespublicarODEsVO tareaModificar = null;
    	
    	try {
    		if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    		
    		Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaDespublicarODEsVO();
			
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			tareaModificar.setIdentificadores((String[]) parametros.get(0));
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setMsgDespublicado((String) parametros.get(1));
			tareaModificar.setMsgNoDespublicado((String) parametros.get(2));
			tareaModificar.setMsgDescripcion((String) parametros.get(3));
			//Vamos a crear un temporal para poder leer los identificadores al pulsar el link del nombre del fichero
			//Vamos a insertar los identificadores obtenidos desde el excel
//    		escribirIdentificadoresEnExcel((String[])parametros.get(0), (String)parametros.get(1));
			
			if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tareaModificar.toString());
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido modificar la tarea " , e);
    		throw e;
    	}

        return tareaModificar;
	}


	/** 
	 *  Método que llama al servicio externo de publicación
	 * 	@param identificadores Array de identificadores de los Objeto Digital Educativos que se quieren despublicar 
	 * 	@param idUsuario Identificador del usuario
	 * 	@return ResultadoDespublicacionVO Devuelve el array de resultados
	 * @throws Exception
	 * */
	protected ResultadoDespublicacionVO[] handleDespublicarPIF(
			String[] identificadores, String idUsuario) throws Exception {
		//TODO Una cosa parecida, cuando hagan el método de despublicacion
		//Sacamos trazas con los identificadores que le pasamos a publicacion
		
		ResultadoOperacionVO[] resultado = this.getSrvPublicacionService().noDisponibles(identificadores,idUsuario, "Despublicación masiva");
		//Recorrer el array res e irlo mapeando al VO ResultadoDespublicacionVO
		//Modificar el VO ResultadoDespublicacionVO para incluir la fecha, así cuando se vaya recorriendo el resultado de la despublicación tenemos la fecha en la que se despublicó.
		//Mapeamos el idResultado con el 
		//He modificado el custom-dozer-mapping para incluir el mapeo DEF_MAPPING_RESULTADOOPERACIONVO_RESULTADODESPUBLICACIONVO
		//El campo idResultado vendrá el código (0.0) y en el campo descripcion vendrá la descripción, creo q si es ok la despublicacion no aparece nada.
		//El campo fecha lo vamos a dejar vacío se asignará valor en la clase DespublicarODES.java
		//Estos valores se tendrán en cuenta en la clase DespublicarODES.java
		 
		
		
		ResultadoDespublicacionVO[] resultadoDespublicacion=new ResultadoDespublicacionVO[resultado.length];
		for(int i=0;i<resultado.length;i++){
			ResultadoDespublicacionVO resVO = (es.pode.planificador.negocio.servicios.ResultadoDespublicacionVO)this.getBeanMapper().map(resultado[i],
					es.pode.planificador.negocio.servicios.ResultadoDespublicacionVO.class, "DEF_MAPPING_RESULTADOOPERACIONVO_RESULTADODESPUBLICACIONVO");
			resultadoDespublicacion[i]=resVO;
		}

		return resultadoDespublicacion;

	}

	/** 
	 *  Método para la eliminación del excel temporal que se crea al modificar o visualizar una tarea de tipo despublicar
	 * 	@param nombreExcel El nombre del excel que queremos eliminar
	 * @throws Exception
	 * */
	protected void handleEliminarTemporalesDespublicar(String nombreExcel)
			throws Exception {
		try{
			this.eliminarTemporal(nombreExcel);
		}catch (Exception e) {
			log.error("Error: No se ha podido eliminar el temporal " + nombreExcel,e);
    		throw e;
		}
		 
	}

	private void eliminarTemporal(String nombreExcel) throws Exception{
		
		 try 
        { 
            File temporalExportacion = new File(System.getProperty("java.io.tmpdir") + "/" +nombreExcel);	
            if(temporalExportacion.exists()){          	
    			UtilesFicheros.eliminar(temporalExportacion);
            }
            

        } 

        catch(Exception e) 

        { 
       	 logger.error("Error al eliminar el fichero." +e); 
            throw e;
        } 

	}
	
	/**
	 * Metodo que permite realizar tareas de inicializacion como eliminar
	 * ficheros temporales creados por este modulo 
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception 
	{
			this.limpiarTemporales();
	}
    


	/**
	 * Metodo privado que elimina ficheros temporales.
	 * 
	 * @throws Exception
	 */
	private void limpiarTemporales()
	throws Exception
	{
		try{
			log.info("Limpieza de temporales");
			// por compatibilidad hacia atras.. elimino todos los temporales que pudieran existir de 
			// versiones anteriores de la aplicación.
			File carpetaTemporal = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO));
			String nombreCarpetaTemp = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO);
			String[] listaTemporal= carpetaTemporal.list();
			for (int i = 0; listaTemporal!=null && i < listaTemporal.length; i++) 
			{
				File carpetaUsuario= new File(nombreCarpetaTemp+listaTemporal[i]);
				UtilesFicheros.eliminar(carpetaUsuario);
			}
		}catch(Exception e){
			log.error("Error limpiando los ficheros temporales de despublicacion");
		}
	
	}

	
	/**
	 * Crea la una tarea del tipo generar OaiOre
	 * @param datosTarea Los datos correspodientes a la tarea creada
	 * @return tareaCreada Una tarea del tipo generar OaiOre
	 * @throws Exception
	 */
	protected TareaVO handleCrearTareaGenerarOaiOre(TareaVO datosTarea) 
			throws Exception {
		return crearTareaGenerica(datosTarea, GenerarOaiOre.class);
	}

	
	/**
	 * Lanzamiento de RSS de una tarea
	 * @param idTarea Identificador de la tarea
	 * @return String
	 * @throws Exception
	 */
	protected String handleLanzarOaiOre(Long idTarea) throws Exception {
		if(logger.isDebugEnabled())logger.debug("LANZAR OAI ORE");
		String texto = "KO";
		try{
			log.info("Lanzar Generar OaiOre");
			getSrvOaiOre().generarOreAtom(0);
			texto=OK;
		}
		catch (Exception e) {
			log.error("Error: No se ha podido realizar la tarea de generar OaiOre " , e);
			return "KO";
		}
		
		return texto;
	}

	/**
	 * Modificación de los datos de una tarea de tipo generar OaiOre
	 * @param datosTarea Datos de la tarea de tipo generar OaiOre
	 * @param trabajo Tarea que hay que modificar con los datos de la tarea tipo generar OaiOre
	 * @return tarea Tarea modificada de generar OaiOre
	 * @throws Exception
	 */
	protected TareaVO handleModificarTareaGenerarOaiOre(TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo.booleanValue()) 
					tarea = handleCrearTareaGenerarOaiOre(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la reindexacion " , e);
				throw e;
			}		
		
			return tarea;	  
	}

	/**
	 * Obtiene una tarea Reindexada a partir de una tarea
	 * @param tarea Una tarea que identifica que tarea generar OaiOre debemos coger
	 * @return tareaModificar Una tarea generar OaiOre rellena que corresponde a la tarea pasada por parametro
	 * @throws Exception
	 */
	protected TareaVO handleObtenerTareaGenerarOaiOre(TareaVO tarea) 
			throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	/**
	 * @param registrosCarga
	 * @throws Exception
	 */
	protected void handleActualizarRegistrosCarga(RegistroTareaCargaEjecutadaVO[] registrosCarga) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param idTarea long
	 * @return RegistroTareaCargaEjecutadaVO 
	 * @throws Exception
	 */
	protected RegistroTareaCargaEjecutadaVO handleObtenerRegistrosCarga(Long idTarea) throws Exception
	{
		//
		return null;
	}

	/**
	 * Crea una tarea del tipo informes Federada Nivel Agregacion
	 * @param datosTarea Nuevos datos de la tarea generación de informes federada
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesNivelAgregacionFederadaVO handleCrearTareaInformesFederadoNivelAgregacion(TareaInformesNivelAgregacionFederadaVO datosTarea) throws Exception {
		
		TareaInformesNivelAgregacionFederadaVO tareaCreada = new TareaInformesNivelAgregacionFederadaVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
		
		try {
			tareaGenerica.setTrabajo(datosTarea.getTrabajo());
			tareaGenerica.setTrigger(datosTarea.getTrigger());
			tareaGenerica.setFechaInicio(datosTarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(datosTarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(datosTarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(datosTarea.getGrupoTrigger());
			tareaGenerica.setUsuario(datosTarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Mensajes internacionalizados que se muestran al reindexar los ODEs de la plataforma */
    		if (null == datosTarea.getMsgInforme() || datosTarea.getMsgInforme().equals(""))
    			datosTarea.setMsgInforme(CtesPlanificador.MSG_INFORMES_OK);

    		if (null == datosTarea.getMsgNoInforme() || datosTarea.getMsgNoInforme().equals(""))
    			datosTarea.setMsgNoInforme(CtesPlanificador.MSG_INFORMES_KO);

    		if (null == datosTarea.getMsgDescripcionTrabajo() || datosTarea.getMsgDescripcionTrabajo().equals(""))
    			datosTarea.setMsgDescripcionTrabajo(datosTarea.getTrabajo());

			/* Parámetros propios de la tarea */
			parametrosTarea.add(datosTarea);
    		    		
			tareaRetornada = handleCrearTarea (tareaGenerica, parametrosTarea, GenerarInformeFederadoNivelAgregacion.class);
			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			//tareaCreada. setRepositorioIndices(datosTarea.getRepositorioIndices());

			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		}
		catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
			
		return tareaCreada;
	}

	/**
	 * Crea la generacion de un informe Federado Nivel de Agregacion
	 * @param datosInforme Datos que necesita el informe Federado para lanzarse
	 * @param idTarea Identificador de la tarea para registrar como ha ido la generacion de la misma
	 * @throws Exception
	 */
	protected void handleGenerarInformeFederadoNivelAgregacion(TareaInformesNivelAgregacionFederadaVO datosInforme, Long idTarea) throws Exception {

		
		try {			
			if(logger.isDebugEnabled())logger.debug("dentro de generarInformeFederadoNivelAgregacion del planificador");
			/* Conversión de TareaInformesVO a ParametroCrearInformeVO */
			ParametroCrearInformeVO parametros = new ParametroCrearInformeVO();
			tareaInformesNivelAgregacionVO2ParametroCrearInformeVOFederado(datosInforme, parametros);
						
			this.getSrvAuditoriaServicio().crearGuardarInformeFederado(parametros);

		}
		catch (Exception e) {
			log.error("Error: al llamar al generar informe: " , e);
		}
	
		
	}

	/**
	 * Este metodo modifica una tarea de generación de informes federados de nivel de agregacion
	 * @param datosTarea Nuevos datos de la tarea generación de informes
	 * @param trabajo Tarea que hay que modificar
	 * @return TareaInformesVO Tarea con los datos nuevos
	 * @throws Exception
	 */
	protected TareaInformesNivelAgregacionFederadaVO handleModificarTareaInformesFederadoNivelAgregacion(TareaInformesNivelAgregacionFederadaVO datosTarea, TrabajoVO trabajo) throws Exception {

    	/* Modificación de una tarea */
    	if(logger.isDebugEnabled())logger.debug("Modificar tarea " + datosTarea.getTrabajo() + " " + datosTarea.getGrupoTrabajo() 
    			+ " usuario: " + datosTarea.getUsuario());
    	Boolean boo = false;
    	TareaInformesNivelAgregacionFederadaVO tarea = null;	
    	
    	try {
    		TrabajoVO[] datosJob = new TrabajoVO[1]; 
    		datosJob[0] = trabajo;
    					
    		/* Eliminamos la tarea */
    		boo = handleEliminarTareas(datosJob);
    		
    		if (boo.booleanValue()) {
    			if(logger.isDebugEnabled())logger.debug("Crear tarea " + datosTarea.toString());
    			tarea = handleCrearTareaInformesFederadoNivelAgregacion(datosTarea);
    		}
    	}	
    	catch (Exception e) {
    		log.error("No se ha podido modificar la tarea " , e);
    		throw e;
    	}		
	
    	return tarea;	  
	
	}
	
	/** 
	 * Se transforma el objeto TareaInformesVO a CrearInformeVO
     * @param datosInforme Objeto TareaInformesVO
     * @param parametros Objeto ParametroCrearInformeVO
     */
	
	private void tareaInformesNivelAgregacionVO2ParametroCrearInformeVOFederado(TareaInformesNivelAgregacionFederadaVO datosInforme, ParametroCrearInformeVO parametros)
	{
		parametros.setUsuario(datosInforme.getUsuarioInforme());
		parametros.setNombreInforme(datosInforme.getInforme());
		parametros.setRango(datosInforme.getRango());
		parametros.setFormato(datosInforme.getFormato());
		parametros.setFechaNivelAgregacion(datosInforme.getFechaNivelAgregacion());
	}

	/**
	 * Este metodo obtiene una tarea del tipo de informes de nivel de agregacion
	 * @param tarea nombre de la tarea que se recuperara los datos
	 * @return TareaInformesVO Tarea rellena con los datos especificos correspondientes
	 * @throws Exception
	 */
	protected TareaInformesNivelAgregacionFederadaVO handleObtenerTareaInformeNivelAgregacion(TareaInformesNivelAgregacionFederadaVO tarea) throws Exception {

		TareaInformesNivelAgregacionFederadaVO tareaModificar = null;
    	
    	try {
    		if(logger.isDebugEnabled())logger.debug("Tarea a modificar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
    		
    		Scheduler agenda = Planificador.getAgenda();
    		
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigerModif = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigerModif: " + trigerModif.toString());
			tareaModificar = new TareaInformesNivelAgregacionFederadaVO();
			
			tareaModificar.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			ArrayList parametros = (ArrayList)trabajo.getJobDataMap().get(CtesPlanificador.PARAMETROS);
			
			TareaInformesNivelAgregacionFederadaVO datosInforme = (TareaInformesNivelAgregacionFederadaVO) parametros.get(0);
	
			tareaModificar.setTrabajo(trabajo.getName());			
			tareaModificar.setGrupoTrabajo(trabajo.getGroup());
			tareaModificar.setTrigger(trigerModif.getName());
			tareaModificar.setGrupoTrigger(trigerModif.getGroup());
			tareaModificar.setFechaInicio(DateManager.dateToCalendar(trigerModif.getStartTime()));	
			tareaModificar.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			tareaModificar.setFechaDesde(datosInforme.getFechaDesde());
			tareaModificar.setFechaHasta(datosInforme.getFechaHasta());
			tareaModificar.setFormato(datosInforme.getFormato());
			tareaModificar.setFechaNivelAgregacion(datosInforme.getFechaNivelAgregacion());
			tareaModificar.setInforme(datosInforme.getInforme());
			tareaModificar.setRango(datosInforme.getRango());
			tareaModificar.setUsuarioInforme(datosInforme.getUsuarioInforme());
   		}
    	catch (Exception e) {
    		log.error("Error: No se ha podido modificar la tarea de informes " , e);
    		throw e;
    	}

        return tareaModificar;
	
	}

	/**
	 * Metodo para buscar las sugerencias de tareas de carga que coincidan con el texto de entrada en el nombre lote. 
	 * @param nombre String texto que debe ser similar al nombre del lote de la tarea
	 * @return TareaEjecutadaVO[]
	 * 				Un array de TareaEjecutadaVO
	 * @throws Exception
	 */
	protected TareaEjecutadaVO[] handleBuscarPorLoteSugerencias(String nombre) throws Exception {

		TareaEjecutadaVO[] todasCargas=null;
		try{
			TareasEjecutadasPorNombreLoteCriteria criteria=new TareasEjecutadasPorNombreLoteCriteria();
			nombre=this.concatenarBusquedaSugerencias(nombre);
			criteria.setNombreLote(nombre);
			criteria.setTipoTarea(SrvPlanificadorServiceImpl.getPropertyValue("cargaODES"));

			List listaCargas=new ArrayList();
			listaCargas =this.getTareaEjecutadaDao().buscarTareasPorNombreLote(this.getTareaEjecutadaDao().TRANSFORM_TAREAEJECUTADAVO, criteria);
			todasCargas=(TareaEjecutadaVO[])(listaCargas.toArray(new TareaEjecutadaVO[listaCargas.size()]));
			if(logger.isDebugEnabled())logger.debug("Obtenemos todas las cargas de longitud "+todasCargas.length+" que su nombre de lote se asemeja a "+nombre);
		}catch(Exception e){
			logger.error("Error al hacer la consulta para las tareas que tenga el nombre de lote semejante a["+ nombre+"]");
			throw new Exception("Error al hacer la consulta para las tareas que tenga el nombre de lote semejante a["+ nombre+"]");
		}
		return todasCargas;
	}
	
	private String concatenarBusquedaSugerencias (String texto) throws Exception{
		try {
			logger.debug("titulo" + texto);
			StringBuilder busqueda = new StringBuilder();
			busqueda.append(texto).append("%");
			logger.info("La busqueda se realizará por los siguientes criterios: " + busqueda.toString());
			return busqueda.toString();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	protected Boolean handleSubirIndices(Long idTarea) throws Exception {
		if(logger.isDebugEnabled())logger.debug("SUBIR INDICES");
		Boolean resultado=false;
		try{
			log.info("Lanzar Subir Indices");
			resultado=getSrvNodoService().subirIndices();
		}
		catch (Exception e) {
			log.error("Error: No se ha podido realizar la tarea Subir Indices " , e);
			return resultado;
		}
		
		return resultado;
	}

	
	@Override
	protected Boolean handleActualizarIndicesRemotos(Long idTarea)
			throws Exception {
		if(logger.isDebugEnabled())logger.debug("ACTUALIZAR INDICES");
		Boolean resultado=false;
		try{
			log.info("Lanzar Actualizar Indices");
			resultado=getSrvNodoService().actualizarIndices();
		}
		catch (Exception e) {
			log.error("Error: No se ha podido realizar la tarea Subir Indices " , e);
			return resultado;
		}
		
		return resultado;
	}

	
	@Override
	protected TareaVO handleCrearTareaSubirIndices(TareaVO datosTarea)
			throws Exception {
		return crearTareaGenerica(datosTarea, SubirIndices.class);
	}

	
	@Override
	protected TareaVO handleCrearTareaActualizarIndicesRemotos(
			TareaVO datosTarea) throws Exception {
		return crearTareaGenerica(datosTarea, ActualizarIndices.class);
	}
	

	@Override
	protected TareaVO handleObtenerTareaSubirIndices(TareaVO tarea)
			throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	@Override
	protected TareaVO handleObtenerTareaActualizarIndicesRemotos(TareaVO tarea)
			throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	@Override
	protected TareaVO handleCrearTareaEstadisticasLocales(TareaVO datosTarea)
			throws Exception {
		return crearTareaGenerica(datosTarea, GenerarEstadisticasNodo.class);
	}

	
	@Override
	protected TareaVO handleCrearTareaEstadisticasTotales(TareaVO datosTarea)
			throws Exception {
		return crearTareaGenerica(datosTarea, GenerarEstadisticasPublicas.class);
	}
	
	
	protected Boolean handleEstadisticasLocales(Long idTarea) throws Exception {
		logger.debug("Estadisticas Locales begins");
		Boolean resultado=false;
		try{
			resultado=getSrvNodoService().crearEstadisticasLocales();
		}
		catch (Exception e) {
			logger.error("Error: No se ha podido realizar la tarea Estadisticas Locales " , e);
			return resultado;
		}
		logger.debug("Estadisticas Locales ends");
		return resultado;
	}
	
	
	protected TareaVO handleObtenerTareaEstadisticasLocales(TareaVO tarea)
	throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}
	

	protected Boolean handleEstadisticasTotales(Long idTarea) throws Exception {
		logger.info("Estadisticas Totales begins");
		Boolean resultado=false;
		try{
			resultado=getSrvNodoService().crearEstadisticasTotales();
		}
		catch (Exception e) {
			logger.error("Error: No se ha podido realizar la tarea Estadisticas totales " , e);
			return resultado;
		}
		logger.debug("Estadisticas Totales ends");
		return resultado;
	}

	
	protected TareaVO handleObtenerTareaEstadisticasTotales(TareaVO tarea)
	throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}
	

	@Override
	protected TareaVO handleModificarTareaSubirIndices(TareaVO datosTarea,
			TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo.booleanValue()) 
					tarea = handleCrearTareaSubirIndices(datosTarea);    			
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la tarea de subir índices " , e);
				throw e;
			}		
		
			return tarea;	  		
	}

	@Override
	protected TareaVO handleModificarTareaDescargarIndicesRemotos(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo.booleanValue()) 
					tarea = handleCrearTareaActualizarIndicesRemotos(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la tarea de actualizar índices remotos" , e);
				throw e;
			}		
		
			return tarea;	  
	}

	@Override
	protected TareaVO handleModificarTareaGenerarEstadisticasLocales(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
			Boolean boo = false;
			TareaVO tarea = null;		
			
			try {
				TrabajoVO[] datosJob = new TrabajoVO[1]; 
				datosJob[0] = trabajo;
				
				/* Eliminamos la tarea */
				if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
				boo = handleEliminarTareas(datosJob);
				
				if (boo.booleanValue()) 
					tarea = handleCrearTareaEstadisticasLocales(datosTarea);    		
		
			}	
			catch (Exception e) {
				log.error("No se ha podido modificar la tarea de estadísticas locales " , e);
				throw e;
			}		
		
			return tarea;	  
	}

	@Override
	protected TareaVO handleModificarTareaGenerarEstadisticasTotales(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
				+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
			
		Boolean boo = false;
		TareaVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
			
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo.booleanValue()) 
				tarea = handleCrearTareaEstadisticasTotales(datosTarea);    		
	
		} catch (Exception e) {
			log.error("No se ha podido modificar la tarea de estadísticas totales " , e);
			throw e;
		}		
		return tarea;	  
	}

	
	/**
	 * Este metodo se encargar de volcar la info de la DB a los indices de compass. 
	 * La info que vuelca es toda la referente a los ODEs publicados. Una vez volcada 
	 * la informacion resetea las caches de busqueda. 
	 * Este metodo se ha creado para que en entornos de alta disponibilidad, cada 
	 * instancia de JBoss pueda tener su copia individual de los indices, y que cada 
	 * ciertto tiempo las copias puedan sincronizarse entre si a traves de los datos 
	 * guardados en el DB.
	 */
	@Override
	protected Boolean handleSincronizarInfoDB2indices() throws Exception {
		if(!this.getSrvIndexadorService().sincronizarIndiceCompass()){
			log.error("Fallo el volcado de la informacion de los ODEs publicados de la DB a los indices");
			return false;
		}
		if(!this.getSrvCacheService().borrarHitCache()){
			log.error("Fallo la operacion de vaciado de la cache");
			return false;
		}
		// 23042013 
		// Solicitamos al resto de nodos del cluster que actualicen sus taxonomias y tesauros
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("Vamos a llamar al resto de nodos para sincronizar índices");
			
			boolean bHayNodos=true;
			SrvIndexadorService srvIndexadorRemoto;
			int nNodo=0;
			while (bHayNodos) {
				srvIndexadorRemoto = this.getSrvIndexadorClusterService(nNodo);
				if (srvIndexadorRemoto!=null)
				{
					srvIndexadorRemoto.sincronizarIndiceCompass();
				}
				else
				{
					bHayNodos=false;
				}
				nNodo++;
			}
		
		}catch (Exception e) {				
			logger.error("Error al llamar al otro nodo para recargar índices :",e);
		}
		// Solicitamos al otro nodo del cluster que borre su cache de búsqueda
		try
		{
			
			if (logger.isDebugEnabled())
				logger.debug("Vamos a llamar al resto de nodos para borrar cache");
			
			boolean bHayNodos=true;
			SrvCacheService srvCacheRemoto;
			
			int nNodo=0;
			while (bHayNodos) {
				srvCacheRemoto = this.getSrvCacheClusterService(nNodo);
				if (srvCacheRemoto!=null)
				{
					srvCacheRemoto.borrarHitCache();
				}
				else
				{
					bHayNodos=false;
				}
				nNodo++;
			}
						
		}catch (Exception e) {				
			logger.error("Error al llamar al otro nodo para recargar índices :",e);
		}
		return true;
	}
	
		protected final es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService getSrvIndexadorClusterService(int nNodo)
        throws java.lang.Exception
    {
        String srvIndexadorServiceFile="importedServices.properties";	    
          java.io.InputStream srvIndexadorServiceInputStream=SrvPlanificadorServiceBase.class.getClassLoader().getResourceAsStream(srvIndexadorServiceFile);
          java.util.Properties srvIndexadorServiceProperties = new java.util.Properties();
          srvIndexadorServiceProperties.load(srvIndexadorServiceInputStream);
          String srvIndexadorServiceEndPointAddress="";
          srvIndexadorServiceEndPointAddress=(String) srvIndexadorServiceProperties.get("srvIndexadorServiceClusterPort" +nNodo);
		  logger.debug("srvIndexadorClusterServiceEndPointAddress del fichero --> " + srvIndexadorServiceEndPointAddress);
		  if (srvIndexadorServiceEndPointAddress==null)
		      	return null;
		  es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceService srvIndexadorService = new es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceServiceLocator();                                                                                                                                                                                                                                                    
        if (srvIndexadorServiceEndPointAddress.length()>0) 
				  ((es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceServiceLocator)srvIndexadorService).setSrvIndexadorServiceEndpointAddress(srvIndexadorServiceEndPointAddress);
    	    es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService port = srvIndexadorService.getSrvIndexadorService();	    
          return port;
    }


	protected final es.pode.buscar.negocio.buscar.servicios.SrvCacheService getSrvCacheClusterService(int nNodo)
	        throws java.lang.Exception
    {
        String srvCacheServiceFile="importedServices.properties";	    
        java.io.InputStream srvCacheServiceInputStream=SrvPlanificadorServiceBase.class.getClassLoader().getResourceAsStream(srvCacheServiceFile);
        java.util.Properties srvCacheServiceProperties = new java.util.Properties();
        srvCacheServiceProperties.load(srvCacheServiceInputStream);
        String srvCacheServiceEndPointAddress="";
        srvCacheServiceEndPointAddress=(String) srvCacheServiceProperties.get("srvCacheServiceClusterPort" +nNodo);
		logger.debug("srvCacheServiceClusterEndPointAddress del fichero --> " + srvCacheServiceEndPointAddress);
		if (srvCacheServiceEndPointAddress==null)
	      	return null;
		es.pode.buscar.negocio.buscar.servicios.SrvCacheServiceService srvCacheService = new es.pode.buscar.negocio.buscar.servicios.SrvCacheServiceServiceLocator();                                                                                                                                                                                                                                                    
        if (srvCacheServiceEndPointAddress.length()>0) 
				  ((es.pode.buscar.negocio.buscar.servicios.SrvCacheServiceServiceLocator)srvCacheService).setSrvCacheServiceEndpointAddress(srvCacheServiceEndPointAddress);
    	   es.pode.buscar.negocio.buscar.servicios.SrvCacheService port = srvCacheService.getSrvCacheService();	    
        return port;
    }

	
	@Override
	protected Boolean handleEnviarCorreoIncidencia(Long idTarea) throws Exception {

		boolean envioCorrecto = false;
			
		if(log.isDebugEnabled())log.debug("Se va a enviar el correo de alarma por tarea planificada ejecutada con error :" + idTarea);
		
		try {
			
			// Obtenemos los datos de la tarea sobre la que se quiere alertar
			TareaEjecutadaDao tareaEjecutadaDao = this.getTareaEjecutadaDao();			
			TareaEjecutada tarea = tareaEjecutadaDao.load(idTarea);
			
			//Obtenemos las direcciones de administradores del nodo 
			String listaCorreoAdminNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CORREO_INCIDENCIA_COMUNICACION_NODO);
			
			//Obtenemos las direcciones de administradores del nodo 
			String listaCorreoAdminMECD = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CORREO_INCIDENCIA_COMUNICACION_NODO_MECD);
			
			// Si tenemos el correo del administrador del nodo enviamos el correo
			if (listaCorreoAdminNodo!=null && !listaCorreoAdminNodo.equals(""))
			{			
				if(log.isDebugEnabled())log.debug("Se va a enviar el correo de alarma por tarea planificada " + tarea.getDescripcion()+" ejecutada con error a :" + listaCorreoAdminNodo + " con copia a :"  +listaCorreoAdminMECD);
			
				ResultadoEnvioCorreoVO resulCorreo = this.getSrvCorreo().enviarCorreoIncidencia(listaCorreoAdminNodo, listaCorreoAdminMECD, tarea.getDescripcion());
				if (resulCorreo!=null && resulCorreo.getResultado().equals("OK"))
					envioCorrecto=true;
			}else
				log.error("No se ha podido enviar el correo de tarea planificada erronea por que no está configurado correctamente la dirección del administrador");
			
		} catch (Exception e) {
			log.error("No se ha podido enviar el correo de alarma :" , e);
		}
		
		return envioCorrecto;
	}

	
	private TareaVO crearTareaGenerica (TareaVO tarea, Class clase) throws Exception {
		
		TareaVO tareaCreada = new TareaVO();
		TareaVO tareaRetornada = null;
		TareaVO tareaGenerica = new TareaVO();
		List<Serializable> parametrosTarea = new ArrayList<Serializable>();
	       	
		try {
			tareaGenerica.setTrabajo(tarea.getTrabajo());
			tareaGenerica.setTrigger(tarea.getTrigger());
			tareaGenerica.setFechaInicio(tarea.getFechaInicio());
			tareaGenerica.setPeriodicidad(tarea.getPeriodicidad());
			tareaGenerica.setGrupoTrabajo(tarea.getGrupoTrabajo());
			tareaGenerica.setGrupoTrigger(tarea.getGrupoTrigger());
			tareaGenerica.setUsuario(tarea.getUsuario());
			
			if(logger.isDebugEnabled())logger.debug("Tarea genérica: " + tareaGenerica.toString());
			
			/* Parámetros propios de la tarea */
    		parametrosTarea.add(tarea.getTrabajo());
			
			tareaRetornada = handleCrearTarea(tareaGenerica, parametrosTarea, clase);
			if(logger.isDebugEnabled())logger.debug("Parámetros tarea: " + parametrosTarea.toString());
			
			/* Cargamos los datos de la tarea creada en el objeto */
			tareaCreada.setTrabajo(tareaRetornada.getTrabajo());
			tareaCreada.setGrupoTrabajo(tareaRetornada.getGrupoTrabajo()); 
			tareaCreada.setTrigger(tareaRetornada.getTrigger());
			tareaCreada.setGrupoTrigger(tareaRetornada.getGrupoTrigger());
			tareaCreada.setFechaInicio(tareaRetornada.getFechaInicio());		
			tareaCreada.setCron(tareaRetornada.getCron());
			tareaCreada.setPeriodicidad(tareaRetornada.getPeriodicidad());
			if(logger.isDebugEnabled())logger.debug("Tarea creada: " + tareaCreada.toString());
		
		} catch (Exception e) {
    		log.error("Error: No se ha podido crear la tarea. " , e);
    		throw e;			
		}
		return tareaCreada;
	}
	
	
	private TareaVO obtenerDatosTareaGenerica(TareaVO tarea)
			throws Exception {
		
		TareaVO t = null;
		
		try {
			if(logger.isDebugEnabled())logger.debug("Tarea a consultar: " + tarea.getTrigger() + " " + tarea.getGrupoTrigger());
			Scheduler agenda = Planificador.getAgenda();
    		JobDetail trabajo = agenda.getJobDetail(tarea.getTrabajo(), tarea.getGrupoTrabajo());
			Trigger trigger = agenda.getTrigger(tarea.getTrigger(), tarea.getGrupoTrigger());
			
			if(logger.isDebugEnabled())logger.debug("trigger: " + trigger.toString());
			t = new TareaVO();
			t.setPeriodicidad((String)trabajo.getJobDataMap().get(CtesPlanificador.PERIODICIDAD));
			t.setTrabajo(trabajo.getName());			
			t.setGrupoTrabajo(trabajo.getGroup());
			t.setTrigger(trigger.getName());
			t.setGrupoTrigger(trigger.getGroup());
			t.setFechaInicio(DateManager.dateToCalendar(trigger.getStartTime()));	
			t.setTipoTarea(Planificador.ultimoCampo(trabajo.getJobClass().toString()));
			if(logger.isDebugEnabled())logger.debug("Tarea solicitada: " + t.toString());
			
		} catch (Exception e) {
			log.error("Error: No se ha podido encontrar los datos de la tarea " , e);
			throw e;
		}
		return t;
	}
	
	
	@Override
	protected TareaVO handleCrearTareaObtenerMetadatosODESFederados(
			TareaVO tarea) throws Exception {
		return crearTareaGenerica(tarea, ObtenerMetadatosODESFederados.class);
	}

	
	@Override
	protected TareaVO handleCrearTareaObtenerODESDespublicadosFederados(
			TareaVO tarea) throws Exception {
		return crearTareaGenerica(tarea, ObtenerODESDespublicadosFederados.class);
	}

	
	@Override
	protected TareaVO handleModificarTareaObtenerMetadatosODESFederados(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
		+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
		
		Boolean boo = false;
		TareaVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
				
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo.booleanValue()) 
				tarea = handleCrearTareaObtenerMetadatosODESFederados(datosTarea);  
		
		} catch (Exception e) {
			log.error("No se ha podido modificar la tarea de ObtenerMetadatosODESFederados " , e);
			throw e;
		}		
		return tarea;
	}

	
	@Override
	protected TareaVO handleModificarTareaObtenerODESDespublicadosFederados(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
		+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
		
		Boolean boo = false;
		TareaVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
				
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo.booleanValue()) 
				tarea = handleCrearTareaObtenerODESDespublicadosFederados(datosTarea);  
		
		} catch (Exception e) {
			log.error("No se ha podido modificar la tarea de ObtenerODESDespublicadosFederados " , e);
			throw e;
		}		
		return tarea;
	}

	
	@Override
	protected TareaVO handleObtenerTareaObtenerMetadatosODESFederados(
			TareaVO tarea) throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	
	@Override
	protected TareaVO handleObtenerTareaObtenerODESDespublicadosFederados(
			TareaVO tarea) throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}
	

	@Override
	protected TareaVO handleCrearTareaObtenerMetadatosODESFederadosFaltantes(
			TareaVO tarea) throws Exception {
		return crearTareaGenerica(tarea, ObtenerMetadatosODESFederadosFaltantes.class);
	}

	
	@Override
	protected TareaVO handleCrearTareaObtenerODESDespublicadosFederadosFaltantes(
			TareaVO tarea) throws Exception {
		return crearTareaGenerica(tarea, ObtenerODESDespublicadosFederadosFaltantes.class);
	}

	
	@Override
	protected TareaVO handleModificarTareaObtenerMetadatosODESFederadosFaltantes(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
		+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
		
		Boolean boo = false;
		TareaVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
				
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo.booleanValue()) 
				tarea = handleCrearTareaObtenerMetadatosODESFederadosFaltantes(datosTarea);  
		
		} catch (Exception e) {
			log.error("No se ha podido modificar la tarea de ObtenerMetadatosODESFederadosFaltantes " , e);
			throw e;
		}		
		return tarea;
	}

	
	@Override
	protected TareaVO handleModificarTareaObtenerODESDespublicadosFederadosFaltantes(
			TareaVO datosTarea, TrabajoVO trabajo) throws Exception {
		
		if(logger.isDebugEnabled())logger.debug("Modificar tarea " + trabajo.getTrabajo() + " " + trabajo.getGrupoTrabajo() 
		+ " usuario: " + datosTarea.getUsuario()/*.toString()*/);
		
		Boolean boo = false;
		TareaVO tarea = null;		
		
		try {
			TrabajoVO[] datosJob = new TrabajoVO[1]; 
			datosJob[0] = trabajo;
				
			/* Eliminamos la tarea */
			if(logger.isDebugEnabled())logger.debug("Se elimina la tarea " + Arrays.toString(datosJob));
			boo = handleEliminarTareas(datosJob);
			
			if (boo.booleanValue()) 
				tarea = handleCrearTareaObtenerODESDespublicadosFederadosFaltantes(datosTarea);  
		
		} catch (Exception e) {
			log.error("No se ha podido modificar la tarea de ObtenerODESDespublicadosFederadosFaltantes " , e);
			throw e;
		}		
		return tarea;
	}

	
	@Override
	protected TareaVO handleObtenerTareaObtenerMetadatosODESFederadosFaltantes(
			TareaVO tarea) throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	
	@Override
	protected TareaVO handleObtenerTareaObtenerODESDespublicadosFederadosFaltantes(
			TareaVO tarea) throws Exception {
		return obtenerDatosTareaGenerica(tarea);
	}

	
    protected final es.pode.oaipmh.negocio.servicios.SrvOaiPmhService getSrvOaiPmhServiceNode(String UrlWSNodo)
	        throws java.lang.Exception {
    	
		String srvOaiPmhServiceEndPointAddress=UrlWSNodo+"/oaipmh/services/SrvOaiPmhService";
		logger.debug("srvOaiPmhServiceEndPointAddress del fichero --> " + srvOaiPmhServiceEndPointAddress);
		es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceService srvOaiPmhService = new es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceServiceLocator();                                                                                                                                                                                                                                                    
		if (srvOaiPmhServiceEndPointAddress.length()>0) 
			((es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceServiceLocator)srvOaiPmhService).setSrvOaiPmhServiceEndpointAddress(srvOaiPmhServiceEndPointAddress);
		es.pode.oaipmh.negocio.servicios.SrvOaiPmhService port = srvOaiPmhService.getSrvOaiPmhService();	    
		return port;
	}
    
    
	private Boolean enviarCorreoIncidenciaUnificacion(ArrayList<String> listaNodosConError, String tipoIncidencia) throws Exception {

		boolean envioCorrecto = false;
		
		String envioCorreoAlarmaActivo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ACTIVO_ENVIO_CORREOS_INCIDENCIA_COMUNICACION);
		
		if (envioCorreoAlarmaActivo==null || !envioCorreoAlarmaActivo.equals("true")) {
			log.debug("No se envia correo para informar de errores en la tarea de unificacion");
			return true;
		}
				
		try {
			if(listaNodosConError==null || listaNodosConError.size()<1) {
				log.error("La lista de nodos con error esta vacia por lo que no se enviara ningun correo");
				return false;
			}
			
			HashMap<String ,String> idNodos_emails = new HashMap<String ,String>();			
			idNodos_emails.put("es_andalucia_20090324",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_AN);
			idNodos_emails.put("es_aragon_20080930",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_AR);
			idNodos_emails.put("es_baleares_200907131205",		AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_IB);
			idNodos_emails.put("es_canarias_20090114",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_IC);
			idNodos_emails.put("es_cantabria_20081215",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_CB);
			idNodos_emails.put("es_castillayleon_201002241811",	AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_CM);
			idNodos_emails.put("es_clm_20091103121523455",		AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_CL);
			idNodos_emails.put("es_cnice_20080623",				AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_CT);
			idNodos_emails.put("es_euskadi_20100423",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_EU);
			idNodos_emails.put("es_extremadura_20111222",		AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_EX);
			idNodos_emails.put("es_larioja_20081107",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_LR);
			idNodos_emails.put("es_murcia_20080422121523455",	AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_MU);
			idNodos_emails.put("es_na_90348943",				AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_NA);
			idNodos_emails.put("es_valencia_201101241416",		AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_CV);
			idNodos_emails.put("es_{nodo}_20080923",			AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_MA);
			idNodos_emails.put("galicia20091006",				AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_GA);
			//idNodos_emails.put("",				AgregaProperties.CORREOINCIDENCIACOMUNICACIONNODO_REDES);

			//Obtenemos las direcciones de administradores del nodo 
			String listaCorreoAdminMECD = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.CORREO_INCIDENCIA_COMUNICACION_NODO_MECD);
			String listaCorreoAdminNodo = "";
			
			for(int i=0; i<listaNodosConError.size(); i++) {
				
				listaCorreoAdminNodo = AgregaPropertiesImpl.getInstance().getProperty(idNodos_emails.get(listaNodosConError.get(i)));
	
				// Si tenemos el correo del administrador del nodo enviamos el correo
				if (listaCorreoAdminNodo!=null && !listaCorreoAdminNodo.equals(""))
				{	
					ResultadoEnvioCorreoVO resulCorreo = this.getSrvCorreo().enviarCorreoIncidencia(listaCorreoAdminNodo, listaCorreoAdminMECD, tipoIncidencia);
					if (resulCorreo!=null && resulCorreo.getResultado().equals("OK")) {
						log.debug("el correo se envio correctamente");
						envioCorrecto=true;
					} else
						log.debug("el correo NO se envio correctamente");
				} else
					log.error("No se ha podido enviar el correo de tarea por que no está configurado correctamente la dirección del administrador");
			}
			
		} catch (Exception e) {
			log.error("No se ha podido enviar el correo de alarma :" , e);
			return false;
		}
		return envioCorrecto;
	}
	
    
    /*
     * Devuelve la lista de todos los nodos incluido el local
     */
    private ArrayList<NodoVO> listaTodosLosNodos() throws Exception {
		//Añadimos el nodo local al resto de nodos
		NodoVO nodoLocal = new NodoVO();
		nodoLocal.setNodo("localhost");
		nodoLocal.setIdNodo(this.getSrvPropiedadService().get(AgregaProperties.SERVER_ID));
		/* Esto lo coge de DB y no funciona correctamente ya que siempre usa default_host
		 * en vez de adecuarlo a la instancia en la que este
		nodoLocal.setUrl(this.getSrvPropiedadService().get(AgregaProperties.HOST));
		nodoLocal.setUrlWS(this.getSrvPropiedadService().get(AgregaProperties.HOST));
		*/
		String url = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		String subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		if(subdominio!=null && !subdominio.isEmpty()) url = url + subdominio;
		nodoLocal.setUrl(url);
		nodoLocal.setUrlWS(url);
 		NodoVO[] otrosNodos = this.getSrvNodoService().listarNodos();
 		ArrayList<NodoVO> nodos = new ArrayList<NodoVO>(Arrays.asList(otrosNodos));
 		nodos.add(nodoLocal);
 		return nodos;
    }
    
    
    /*
     * Devuelve null si hubo error
     */
    private ArrayList<Object> hacerPeticionOAIPMHLomes(String fechaInicio, String fechaFin, String urlWsNodo, String idNodo)
			throws Exception {

    	ArrayList<Object> resultado = new ArrayList<Object>();
		String idResumptionToken = "";
		Boolean fin = false;
		String url = "";
		
		try {
			
			while (!fin) {

				if(idResumptionToken==null || idResumptionToken.equals(""))
					url = urlWsNodo+"/oaipmh/OaiPmhRequest/OaiPmhRequest.do?verb=ListRecords&metadataPrefix=lomes&from="+fechaInicio+"&until="+fechaFin;
				else
					url = urlWsNodo+"/oaipmh/OaiPmhRequest/OaiPmhRequest.do?verb=ListRecords&metadataPrefix=lomes&from="+fechaInicio+"&until="+fechaFin+"&resumptionToken="+idResumptionToken;
				
				logger.debug("URL a la que se atacara para recibir metadatos de ODEs publicados del nodo "+idNodo+": "+url);
				
				BufferedInputStream bufferedInput = new BufferedInputStream(Proxy.getInputStream(new URL(url))); 
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		        byte [] array = new byte[1000];
		        int leidos = bufferedInput.read(array);
		        
		        while (leidos > 0) {
		            baos.write(array,0,leidos);
		            leidos=bufferedInput.read(array);
		        }
		        bufferedInput.close();
				
		        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		        //ByteArrayInputStream bais = new ByteArrayInputStream(baos.toString(Charset.forName("ISO-8859-1")));
		        //ByteArrayInputStream bais = new ByteArrayInputStream(baos.toString("ISO-8859-1").getBytes());
		        BufferedInputStream bis = new BufferedInputStream(bais);
		        InputSource isrc = null;
		        isrc = new InputSource(bis);
	
				Unmarshaller unmarshaller = new Unmarshaller(OAIPMH.class);
				unmarshaller.setValidation(false);
				OAIPMH oai = (OAIPMH) unmarshaller.unmarshal(isrc);
				OAIPMHAgrega oaiAgrega = new OAIPMHAgrega(oai);
						
				if(oaiAgrega.getError()!=null && oaiAgrega.getError().length>0) {
					for(int i=0; i<oaiAgrega.getError().length; i++) {
						String codigoError = oaiAgrega.getError(i).getCode().toString();
						String msgError = oaiAgrega.getError(i).getContent();
						logger.warn("Error devuelto en la respuesta OAIPMH (Cod. "+codigoError+"): "+msgError);
					}
					return new ArrayList<Object>();
				}
				
				for (int i = 0; i < oaiAgrega.getListRecords().getRecords().length; i++) {
					RecordAgrega rec = oaiAgrega.getListRecords().getRecords()[i];
					resultado.add(rec);
				}
				
				if(oaiAgrega.getListRecords().getResumptionToken()==null)
					fin=true;
				else
					idResumptionToken = oaiAgrega.getListRecords().getResumptionToken().getContent();

				if(idResumptionToken==null || idResumptionToken.equals("")) 
					fin=true;
			}
			
		} catch (Exception e) {
			logger.error("Error al hacer peticion http OAIPMH de los metadatos Lomes de los ODEs publicados en el nodo "+idNodo,e);
			return null;
		}

		return resultado;
    }
    
    
    /*
     * Devuelve true si la linea especificada existe en el fichero
     */
    private Boolean existeLineaEnFichero (String file, String linea) throws Exception {
    	Scanner scanner = new Scanner(new File(file));
		Boolean encontrado = false;
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.contains(linea)) {
				encontrado = true;
				break;
			}
		}
		scanner.close();
		return encontrado;
    }
    
	
    /*
     * Obtiene los metadatos de todos los ODEs publicados en cada nodo en el dia anterior a su ejecicion.
     * Los metadatos son guardados en ficheros independientes en carpetaspara las diferentes comunidades
     */
	@Override
	protected Boolean handleObtenerMetadatosODESFederados(Long idTarea)
			throws Exception {
		
		int contNodos = 0;
		List<NodoVO> nodos = null;
		ArrayList<String> listaNodosConError = new ArrayList<String>();
		String rutaLocal = "uploads"+File.separator+"metadatosLomesOdesFederados";
		String errorLogFile = rutaLocal+File.separator+"ObtenerMetadatosODESFederados_ERROR.log";
		String newLine = System.getProperty("line.separator");

		//Dia de hoy
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String fechaHoy = df.format(new Date());
		String carpetaFecha = fechaHoy.substring(0,4)+fechaHoy.substring(5,7)+fechaHoy.substring(8,10);
		
		//Pedimos los datos del dia de hoy y crearemos carpeta con la fecha
        Calendar inicioDiaActual = Calendar.getInstance();
        inicioDiaActual.add(Calendar.DATE, -1);
        inicioDiaActual.set(Calendar.AM_PM, 0);
        inicioDiaActual.set(Calendar.HOUR, 0);
        inicioDiaActual.set(Calendar.MINUTE, 0);
        inicioDiaActual.set(Calendar.SECOND, 0);
        String fechaInicio = df.format(inicioDiaActual.getTime());
        
        Calendar finDiaActual = Calendar.getInstance();
        finDiaActual.add(Calendar.DATE,- 1);
        finDiaActual.set(Calendar.AM_PM, 1);
        finDiaActual.set(Calendar.HOUR, 11);
        finDiaActual.set(Calendar.MINUTE, 59);
        finDiaActual.set(Calendar.SECOND, 59);
        String fechaFin = df.format(finDiaActual.getTime());
        		
    	try {
    		//Creamos el registro de errores si no existe
    		File path = new File(rutaLocal);
			if(!path.exists()) path.mkdir();
    		File errorLog = new File(errorLogFile);
    		if(!errorLog.exists()) errorLog.createNewFile();
    		
   	 		nodos = listaTodosLosNodos();
   	 		
	   	 	for(contNodos=0; contNodos<nodos.size(); contNodos++) {
	   	 		
	   	 		String urlWsNodo="http://"+nodos.get(contNodos).getUrl().replaceAll("\\\\", "");
	   	 		String idNodo=nodos.get(contNodos).getIdNodo();
	   	 	   	 		
	   	 		logger.debug("idNodo: " + nodos.get(contNodos).getIdNodo());
	   	 		logger.debug("urlWsNodo: " + urlWsNodo);
	    		
	    		ArrayList<Object> o = hacerPeticionOAIPMHLomes(fechaInicio, fechaFin, urlWsNodo, idNodo);
	    		if (o==null) {		        		
	    			//Resgistro error del nodo en el fichero log en caso de que no este ya registrado
	    			String error=fechaHoy + " " + idNodo;					
					if (!existeLineaEnFichero(errorLogFile, error)) {
		    			FileWriter f = new FileWriter(errorLogFile, true);
			    		f.write(error + newLine);
			    		f.close();
					}
		    		listaNodosConError.add(idNodo);
					
	    		} else {
	        		logger.debug("Recibidos "+o.size()+" ODEs del nodo "+idNodo);
	    			
	    			//Guardaremos los datos obtenidos en el HD
	    			String pathAlmacenMetadatos = rutaLocal+File.separator+carpetaFecha+File.separator+idNodo;
					File storePath = new File(pathAlmacenMetadatos);
	        		//NO Vaciamos el directorio previamente
					//if(storePath.exists()) UtilesFicheros.eliminar(storePath);
					if(!storePath.exists()) storePath.mkdirs();
	        		logger.debug("Los metadatos se guardaran en "+storePath.getAbsolutePath());
	        		
	        		for(int n=0; n<o.size(); n++) {
						RecordAgrega rec = (RecordAgrega)o.get(n);
						String[] tmp = rec.getHeader().getIdentifier().split(":");
						String idOde = tmp[tmp.length-1];
						if(idOde==null || idOde.contentEquals("")) {
					    	Random generator = new Random();
					    	//Random number between 0 and 1000
					    	int randomNumber=generator.nextInt(1000);
					    	String number = Integer.toString(randomNumber); 
					    	idOde=new GregorianCalendar().getTimeInMillis()+"."+number;
						}
						String nombreXml = pathAlmacenMetadatos+File.separator+idOde+".xml";
		    			FileWriter xml = new FileWriter(nombreXml);
		    			xml.write(rec.getMetadata().toString().replaceFirst("UTF-8", "ISO-8859-1"));
		    			xml.close();
					}
	    		}
	   	 	}

	   	 	//Enviamos correo informando de que nodos han fallado
	   	 	if(listaNodosConError!=null && listaNodosConError.size()>0)
	   	 		enviarCorreoIncidenciaUnificacion(listaNodosConError, "INCIDENCIA_METADATOS_ODES_FEDERADOS");
	   	 	
    	} catch(Exception e) {
    		if (nodos!=null) {
	    		//Registramos como respuestas erroneas los nodos que todavia faltaban por recorrer
    			FileWriter f = new FileWriter(errorLogFile, true);
	    		for(int i=contNodos; i<nodos.size(); i++) 
	    			f.write(fechaHoy + " " + nodos.get(i).getIdNodo() + newLine);
	    		f.close();
    		} else {
        		logger.error("ERROR GRAVE: No se han podido registrar los nodos que no han contestados en el log "+errorLogFile);
    		}
    		logger.error("Error al recuperar y salvar metadatos - "+e);
    		return false;
    	}
    	return true;
	}

	
    protected final es.pode.publicacion.negocio.servicios.SrvPublicacionService getSrvPublicacionServiceNode(String UrlWSNodo)
	        throws java.lang.Exception {
    	
		String srvPublicacionServiceEndPointAddress=UrlWSNodo+"/publicacion-1/services/SrvPublicacionService";
		logger.debug("srvPublicacionServiceEndPointAddress del fichero --> " + srvPublicacionServiceEndPointAddress);
		es.pode.publicacion.negocio.servicios.SrvPublicacionServiceService srvPublicacionService = new es.pode.publicacion.negocio.servicios.SrvPublicacionServiceServiceLocator();                                                                                                                                                                                                                                                    
		if (srvPublicacionServiceEndPointAddress.length()>0) 
			((es.pode.publicacion.negocio.servicios.SrvPublicacionServiceServiceLocator)srvPublicacionService).setSrvPublicacionServiceEndpointAddress(srvPublicacionServiceEndPointAddress);
		es.pode.publicacion.negocio.servicios.SrvPublicacionService port = srvPublicacionService.getSrvPublicacionService();	    
		return port;
	}
    
    
    /*
     * Devuelve false si hubo un error
     */
    private Boolean registraDespublicadosDeNodo(String fechaInicio, String fechaFin, String urlWsNodo, String idNodo, Boolean guardaConFechaManana)
			throws Exception {
    	
    	TransicionVO [] t = null;
	 	Boolean bien = true;
	 		
 		try {
 			t = this.getSrvPublicacionServiceNode(urlWsNodo).obtenODEsDespublicadosPorFecha(fechaInicio, fechaFin);
 		} catch (Exception e) {
    		logger.error("Error al obtener lista de despublicados del nodo "+idNodo+ " - "+e);      		
    		return false;
 		}
 		if (t==null) {
			logger.error("Error inesperado al recuperar despublicados del nodo con id "+ idNodo);     		
			return false;
 		}
 		
		logger.debug("Recibidos "+t.length+" ODEs del nodo "+idNodo);
		
 		try {
 			if(guardaConFechaManana){				            
 				Calendar inicioDiaManana = Calendar.getInstance();
	            inicioDiaManana.add(Calendar.DATE,+ 1);
	            inicioDiaManana.set(Calendar.AM_PM, 0);
	            inicioDiaManana.set(Calendar.HOUR, 0);
	            inicioDiaManana.set(Calendar.MINUTE, 0);
	            inicioDiaManana.set(Calendar.SECOND, 0);
	            
	            for(int i=0; i<t.length; i++)
	            	t[i].setFecha(inicioDiaManana);
 			}
 			
 			bien = this.getSrvPublicacionService().insertarOdesFederadosDespublicados(t, idNodo);
 		} catch (Exception e) {
    		logger.error("Error al registrar despublicados del nodo con id "+ idNodo +" en la DB - " + e);     		
			return false;  		   	 			
 		}
 		if(!bien) {
    		logger.error("No se pudo registrar despublicados del nodo con id "+ idNodo +" en la DB.");
     		return false;  	
 		}
	 	return true;
    }
    
	
	/*
	 * Obtiene los ODEs despublicados en cada nodo en el dia anterior a su ejecucion
	 */
	@Override
	protected Boolean handleObtenerODESDespublicadosFederados(Long idTarea)
			throws Exception {

		String rutaLocal = "uploads";
		String errorLogFile = rutaLocal+File.separator+"ObtenerODESDespublicadosFederados_ERROR.log";
		String newLine = System.getProperty("line.separator");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String fechaHoy = df.format(new Date());
		
        Calendar inicioDiaActual = Calendar.getInstance();
        inicioDiaActual.add(Calendar.DATE, -1);
        inicioDiaActual.set(Calendar.AM_PM, 0);
        inicioDiaActual.set(Calendar.HOUR, 0);
        inicioDiaActual.set(Calendar.MINUTE, 0);
        inicioDiaActual.set(Calendar.SECOND, 0);
        String fechaInicio = df.format(inicioDiaActual.getTime());
        
        Calendar finDiaActual = Calendar.getInstance();
        finDiaActual.add(Calendar.DATE,- 1);
        finDiaActual.set(Calendar.AM_PM, 1);
        finDiaActual.set(Calendar.HOUR, 11);
        finDiaActual.set(Calendar.MINUTE, 59);
        finDiaActual.set(Calendar.SECOND, 59);
        String fechaFin = df.format(finDiaActual.getTime());
        
        int contNodos=0;
        List<NodoVO> nodos = null;
		ArrayList<String> listaNodosConError = new ArrayList<String>();
    	
        try {
    		//Creamos el registro de errores si no existe
    		File path = new File(rutaLocal);
			if(!path.exists()) path.mkdir();
    		File errorLog = new File(errorLogFile);
    		if(!errorLog.exists()) errorLog.createNewFile();
    		
   	 		nodos = listaTodosLosNodos();
	 		
	 		//Recorremos todos los nodos
	   	 	for(contNodos=0; contNodos<nodos.size(); contNodos++) {
	
	   	 		String urlWsNodo="http://"+nodos.get(contNodos).getUrl().replaceAll("\\\\", "");
	   	 		String idNodo=nodos.get(contNodos).getIdNodo();
	   	 	   	 		
	   	 		logger.debug("idNodo: " + nodos.get(contNodos).getIdNodo());
	   	 		logger.debug("urlWsNodo: " + urlWsNodo);
	   	 		
	   	 		if(!registraDespublicadosDeNodo(fechaInicio, fechaFin, urlWsNodo, idNodo, false)) {
	   	 			//Resgistro error del nodo en el fichero log en caso de que no este ya registrado
		   	 		String error=fechaHoy + " " + idNodo;
					if (!existeLineaEnFichero(errorLogFile, error)) {
		    			FileWriter f = new FileWriter(errorLogFile, true);
			    		f.write(error + newLine);
			    		f.close();
					}
		    		listaNodosConError.add(idNodo);
	   	 		}
	   	 	}

	   	 	//Enviamos correo informando de que nodos han fallado
	   	 	// TODO Descomentar cuando se actualicen versiones en los nodos
	   	 	// 20140310 Se comenta el envio de correos temporalmente hasta que los nodos tengan la versión 1.4.4 o posterior
//	   	 	if(listaNodosConError!=null && listaNodosConError.size()>0)
//	   	 		enviarCorreoIncidenciaUnificacion(listaNodosConError, "INCIDENCIA_DESPUBLICADOS_FEDERADOS");
	   	 	
    	} catch (Exception e) {
    		if (nodos!=null) {
	    		//Registramos como respuestas erroneas los nodos que todavia faltaban por recorrer
    			FileWriter f = new FileWriter(errorLogFile, true);
	    		for(int i=contNodos; i<nodos.size(); i++) 
	    			f.write(fechaHoy + " " + nodos.get(i).getIdNodo() + newLine);
	    		f.close();
    		} else {
        		logger.error("ERROR GRAVE: No se han podido registrar los nodos que no han contestado en el log "+errorLogFile);
    		}
    		logger.error("Error al obtener despublicados - "+e);
    		return false;
    	}
		return true;
	}
	

	@Override
	protected Boolean handleObtenerMetadatosODESFederadosFaltantes(Long idTarea)
			throws Exception {
		
		String rutaLocal = "uploads"+File.separator+"metadatosLomesOdesFederados";
		String errorLogFile = rutaLocal+File.separator+"ObtenerMetadatosODESFederados_ERROR.log";
		String errorLogFileNew = rutaLocal+File.separator+"ObtenerMetadatosODESFederados_ERROR.newlog";
		String newLine = System.getProperty("line.separator");
		
		try {
			
			//Si no existe fichero de errores de una ejecucion previa de la ObtenerMetadatosODESFederados
			//salimos inmediatamente
			File errorLog = new File(errorLogFile);
			if(!errorLog.exists()) {
		    	logger.warn("No se ha encontrado el fichero de errores de la tarea ObtenerMetadatosODESFederados ("+errorLogFile+"), por lo que se supone que no hubo errores en su ejecucion.");
				return true;
			}
			
			//Esto nos servira para saber a que url debemos atacar
	 		ArrayList<NodoVO> nodos = listaTodosLosNodos();

			//Variables para el registro de errores
			File path = new File(rutaLocal);
			if(!path.exists()) path.mkdir();
			File errorLogNew = new File(errorLogFileNew);

			//Leemos linea a linea el fichero que contendra pares fecha(formato yyyy-MM-dd)/idNodo
			BufferedReader br = new BufferedReader(new FileReader(errorLogFile));
			String line;
			Integer contLine = 0;
			ArrayList<String> listaNodosConError = new ArrayList<String>();
			
			while((line = br.readLine()) != null) {
			
				contLine++;
			    String[] s = line.split(" ");
			    
			    if(s.length!=2) {
			    	logger.error("Error de formato en el fichero "+errorLogFile+": no se encontro el par fecha-idNodo en la linea "+contLine+". Esta línea no se procesara.");
			    
			    } else {
			    	String fecha=s[0];
			    	String idNodo=s[1];
			    	String urlWsNodo="";
			    	String nodo="";
			    	
			    	for(int i=0; i<nodos.size(); i++) 
			    		if(nodos.get(i).getIdNodo().equalsIgnoreCase(idNodo)) {
			    			urlWsNodo="http://"+nodos.get(i).getUrl().replaceAll("\\\\", "");
			    			break;
			    		}
			    			
			    	if(urlWsNodo.isEmpty()) {
				    	logger.error("No se ha encontrado nodo que coincida con el id "+idNodo+" especificado en la linea "+contLine+" del fichero "+errorLogFile+". Esta línea no se procesara.");
			    	
			    	} else {
	
			    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
			            Calendar inicioDiaActual = Calendar.getInstance();
			            inicioDiaActual.setTime(df.parse(fecha));
			            inicioDiaActual.add(Calendar.DATE,- 1);
			            inicioDiaActual.set(Calendar.AM_PM, 0);
			            inicioDiaActual.set(Calendar.HOUR, 0);
			            inicioDiaActual.set(Calendar.MINUTE, 0);
			            inicioDiaActual.set(Calendar.SECOND, 0);
			            String fechaInicio = df.format(inicioDiaActual.getTime());
			            
			            Calendar finDiaActual = Calendar.getInstance();
			            finDiaActual.setTime(df.parse(fecha));
			            finDiaActual.add(Calendar.DATE,- 1);
			            finDiaActual.set(Calendar.AM_PM, 1);
			            finDiaActual.set(Calendar.HOUR, 11);
			            finDiaActual.set(Calendar.MINUTE, 59);
			            finDiaActual.set(Calendar.SECOND, 59);
			            String fechaFin = df.format(finDiaActual.getTime());
			    		
			    		logger.debug("Se va a intentar obtener ODEs federados del nodo "+idNodo+" con fecha "+fecha);
			    		
			    		ArrayList<Object> o = hacerPeticionOAIPMHLomes(fechaInicio, fechaFin, urlWsNodo, idNodo);
			    		if (o==null) {		        		
			        		//Creamos el registro de errores si no existe
			        		if(!errorLogNew.exists()) errorLogNew.createNewFile();
			        		
			    			//Resgistro error del nodo en el nuevo fichero log 
			    			FileWriter f = new FileWriter(errorLogFileNew, true);
				    		f.write(fecha + " " + idNodo + newLine);
				    		f.close();
				    		listaNodosConError.add(idNodo);
				    		
			    		} else {	    			
			        		logger.debug("Recibidos "+o.size()+" ODEs del nodo "+idNodo);
			        		
			    			//Guardaremos los datos obtenidos en el HD
			    			//String carpetaFecha = fecha.substring(0,4)+fecha.substring(5,7)+fecha.substring(8,10);
				            Calendar inicioDiaManana = Calendar.getInstance();
				            inicioDiaManana.add(Calendar.DATE,+ 1);
				            inicioDiaManana.set(Calendar.AM_PM, 0);
				            inicioDiaManana.set(Calendar.HOUR, 0);
				            inicioDiaManana.set(Calendar.MINUTE, 0);
				            inicioDiaManana.set(Calendar.SECOND, 0);
				            String fechaManana = df.format(inicioDiaManana.getTime());
			        		String carpetaFecha = fechaManana.substring(0,4)+fechaManana.substring(5,7)+fechaManana.substring(8,10);
			        		
			    			String pathAlmacenMetadatos = rutaLocal+File.separator+carpetaFecha+File.separator+idNodo;
							File storePath = new File(pathAlmacenMetadatos);
							
			        		//NO Vaciamos el directorio previamente
							//if(storePath.exists()) UtilesFicheros.eliminar(storePath);
							if(!storePath.exists()) storePath.mkdirs();
			        		logger.debug("Los metadatos se guardaran en "+storePath.getAbsolutePath());	
	
			        		for(int n=0; n<o.size(); n++) {
								RecordAgrega rec = (RecordAgrega)o.get(n);
								String[] tmp = rec.getHeader().getIdentifier().split(":");
								String idOde = tmp[tmp.length-1];
								if(idOde==null || idOde.contentEquals("")) {
							    	Random generator = new Random();
							    	//Random number between 0 and 1000
							    	int randomNumber=generator.nextInt(1000);
							    	String number = Integer.toString(randomNumber); 
							    	idOde=new GregorianCalendar().getTimeInMillis()+"."+number;
								}
								String nombreXml = pathAlmacenMetadatos+File.separator+idOde+".xml";
				    			FileWriter xml = new FileWriter(nombreXml);
				    			xml.write(rec.getMetadata().toString().replaceFirst("UTF-8", "ISO-8859-1"));
				    			xml.close();
							}
			    		}
			    	}
			    }
			}
			br.close();
			errorLog.delete();
			
			//Si se ha creado un nuevo fichero de errores se sustituye el antiguo por el nuevo
			if(errorLogNew.exists()) {
				errorLogNew.renameTo(errorLog);
	    		logger.debug("Se ha creado un nuevo log con los nodos que no han devuelto los metadatos de sus ODEs");
			} 
	
			//Enviamos correo informando de que nodos han fallado
			if(listaNodosConError!=null && listaNodosConError.size()>0)
				enviarCorreoIncidenciaUnificacion(listaNodosConError, "INCIDENCIA_METADATOS_ODES_FEDERADOS");
			
		} catch (Exception e) {
			logger.error("Se produjo una excepcion - "+e);
			return false;
		}
		return true;
	}

	
	@Override
	protected Boolean handleObtenerODESDespublicadosFederadosFaltantes(
			Long idTarea) throws Exception {
		
		String rutaLocal = "uploads";
		String errorLogFile = rutaLocal+File.separator+"ObtenerODESDespublicadosFederados_ERROR.log";
		String errorLogFileNew = rutaLocal+File.separator+"ObtenerODESDespublicadosFederados_ERROR.newlog";
		String newLine = System.getProperty("line.separator");
		
		try{
			//Si no existe fichero de errores de una ejecucion previa de la ObtenerODESDespublicadosFederados
			//salimos inmediatamente
			File errorLog = new File(errorLogFile);
			if(!errorLog.exists()) {
		    	logger.warn("No se ha encontrado el fichero de errores de la tarea ObtenerMetadatosODESFederados ("+errorLogFile+"), por lo que se supone que no hubo errores en su ejecucion.");
				return true;
			}
			
			//Esto nos servira para saber a que url debemos atacar
	 		ArrayList<NodoVO> nodos = listaTodosLosNodos();

			//Variables para el registro de errores
			File path = new File(rutaLocal);
			if(!path.exists()) path.mkdir();
			File errorLogNew = new File(errorLogFileNew);

			//Leemos linea a linea el fichero que contendra pares fecha(formato yyyy-MM-dd)/idNodo
			BufferedReader br = new BufferedReader(new FileReader(errorLogFile));
			String line;
			Integer contLine = 0;
			ArrayList<String> listaNodosConError = new ArrayList<String>();
			
			while((line = br.readLine()) != null) {
			
				contLine++;
			    String[] s = line.split(" ");
			    
			    if(s.length!=2) {
			    	logger.error("Error de formato en el fichero "+errorLogFile+": no se encontro el par fecha-idNodo en la linea "+contLine+". Esta línea no se procesara.");
			    
			    } else {
			    	String fecha=s[0];
			    	String idNodo=s[1];
			    	String urlWsNodo="";
			    	String nodo="";
			    	
			    	for(int i=0; i<nodos.size(); i++) 
			    		if(nodos.get(i).getIdNodo().equalsIgnoreCase(idNodo)) {
			    			urlWsNodo="http://"+nodos.get(i).getUrl().replaceAll("\\\\", "");
			    			break;
			    		}
			    			
			    	if(urlWsNodo.isEmpty()) {
				    	logger.error("No se ha encontrado nodo que coincida con el id "+idNodo+" especificado en la linea "+contLine+" del fichero "+errorLogFile+". Esta línea no se procesara.");
			    	
			    	} else {
	
			    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
			            Calendar inicioDiaActual = Calendar.getInstance();
			            inicioDiaActual.setTime(df.parse(fecha));
			            inicioDiaActual.add(Calendar.DATE,- 1);
			            inicioDiaActual.set(Calendar.AM_PM, 0);
			            inicioDiaActual.set(Calendar.HOUR, 0);
			            inicioDiaActual.set(Calendar.MINUTE, 0);
			            inicioDiaActual.set(Calendar.SECOND, 0);
			            String fechaInicio = df.format(inicioDiaActual.getTime());
			            
			            Calendar finDiaActual = Calendar.getInstance();
			            finDiaActual.setTime(df.parse(fecha));
			            finDiaActual.add(Calendar.DATE,- 1);
			            finDiaActual.set(Calendar.AM_PM, 1);
			            finDiaActual.set(Calendar.HOUR, 11);
			            finDiaActual.set(Calendar.MINUTE, 59);
			            finDiaActual.set(Calendar.SECOND, 59);
			            String fechaFin = df.format(inicioDiaActual.getTime());
			    		
			    		logger.debug("Se va a intentar obtener ODEs despublicados federados del nodo "+idNodo+" con fecha "+fecha);
			    				    			   	 		
			   	 		if(!registraDespublicadosDeNodo(fechaInicio, fechaFin, urlWsNodo, idNodo, true)) {
			        		//Creamos el registro de errores si no existe
			        		if(!errorLogNew.exists()) errorLogNew.createNewFile();
			        		
			    			//Resgistro error del nodo en el nuevo fichero log 
			    			FileWriter f = new FileWriter(errorLogFileNew, true);
				    		f.write(fecha + " " + idNodo + newLine);
				    		f.close();
				    		// Si no se ha identificado ya como error se incluye
				    		if (!listaNodosConError.contains(idNodo))
				    			listaNodosConError.add(idNodo);
			   	 		}
			   	 	}		
			    }
			}
			br.close();
			errorLog.delete();
			
			//Si se ha creado un nuevo fichero de errores se sustituye el antiguo por el nuevo
			if(errorLogNew.exists()) {
				errorLogNew.renameTo(errorLog);
	    		logger.debug("Se ha creado un nuevo log con los nodos que no han devuelto los ODEs despublicados");
			}
	
	   	 	// TODO Descomentar cuando se actualicen versiones en los nodos
	   	 	// 20140410 Se comenta el envio de correos temporalmente hasta que los nodos tengan la versión 1.4.4 o posterior
			//Enviamos correo informando de que nodos han fallado
			//if(listaNodosConError!=null && listaNodosConError.size()>0)
				//enviarCorreoIncidenciaUnificacion(listaNodosConError, "INCIDENCIA_DESPUBLICADOS_FEDERADOS");
		
		} catch (Exception e) {
			logger.error("Se produjo una excepcion - "+e);
			return false;
		}
		return true;
	}
		
}