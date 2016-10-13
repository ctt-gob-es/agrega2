// license-header java merge-point
package es.pode.administracion.presentacion.planificador.descripcionTarea;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.administracion.presentacion.planificador.comun.Utiles;
import es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTCargaODEsFormImpl;
import es.pode.planificador.negocio.servicios.TareaCargaODEsVO;
import es.pode.planificador.negocio.servicios.TareaDespublicarODEsVO;
import es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO;
import es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO;
import es.pode.planificador.negocio.servicios.TareaInformesNivelAgregacionFederadaVO;
import es.pode.planificador.negocio.servicios.TareaInformesVO;
import es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO;
import es.pode.planificador.negocio.servicios.TareaReindexadoVO;
import es.pode.planificador.negocio.servicios.TareaVO;
import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.validador.ValidadorMEC;


@SuppressWarnings("serial")
public class DescripcionTareaControllerImpl extends DescripcionTareaController
{

	private static Logger log = Logger.getLogger(DescripcionTareaControllerImpl.class);
	private static TimeZone tz = null;
	Utiles utilidades = new Utiles();
	//private Properties pSpringProperties = null;
	//private static String tmpDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO);

	final int BUFFER_SIZE = 100000;

	
	/**
	 * Método que discrimina la tarea a crear
	 * 
	 * Retorna 1: Carga de ODEs 2: Reindexado 3: Eliminar ODEs 4:Infome fecha 5:Informe rango 6:Informe usuario 7:Informe Federado 8:Informe catalogo
	 */
    public final String obtenerTipoTarea(ActionMapping mapping, ObtenerTipoTareaForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {

		String tipoTarea = null;
		String tipoTareaDevolver = null;
		
	    try
	    {	
	    	//obtnemos el tipoTarea
	        TrabajoVO trabajo = new TrabajoVO();
	        
	        trabajo.setTrabajo(form.getTrabajo());
	        trabajo.setGrupoTrabajo(form.getGrupoTrabajo());
	        trabajo.setUsuario(LdapUserDetailsUtils.getUsuario());
	        
	        tipoTarea = this.getSrvPlanificadorService().obtenerTipoTarea(trabajo);
	        if(log.isDebugEnabled())log.debug("el tipo de tarea al recogerlo vale -> " + tipoTarea);

	        //si el tipoTarea es de informes debemos mirar que informe concreto es para redirigirnos a una de las 3 pantallas de informes
	        if(tipoTarea.equalsIgnoreCase("GenerarInforme"))
	        {
		        //obtenemos el tipo de informe
		        TareaInformesVO tarea = new TareaInformesVO();
		        
		        tarea.setTrabajo(form.getTrabajo());
		        tarea.setGrupoTrabajo(form.getGrupoTrabajo());
		        tarea.setTrigger(form.getTrigger());
		        tarea.setGrupoTrigger(form.getGrupoTrigger());
		        
		        TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
		        String informe = tareaInformes.getInforme();
	        
		        if(log.isDebugEnabled())log.debug("estamos dentro de generar informe");
	        	if(informe.equalsIgnoreCase("estadoOdes") || informe.equalsIgnoreCase("operacionesRealizadas") || 
	        			informe.equalsIgnoreCase("nivelAgregacion") || informe.equalsIgnoreCase("coberturaCurricular") || 
	        			informe.equalsIgnoreCase("odesLicencias") || informe.equalsIgnoreCase("usuarios") || 
	        			informe.equalsIgnoreCase("procesosPlanificados"))
		        {
	        		if(log.isDebugEnabled())log.debug("cargo informe con fechas");	
		        	tipoTareaDevolver = "InformeFecha";
		        }
	        	else if(informe.equalsIgnoreCase("terminosBusqueda") ||  informe.equalsIgnoreCase("masValorado") || 
		        			informe.equalsIgnoreCase("masMostrado") || informe.equalsIgnoreCase("masPrevisualizado") || 
		        			informe.equalsIgnoreCase("masVisualizado") || informe.equalsIgnoreCase("masDescargado") || 
		        			informe.equalsIgnoreCase("tamanio"))
		        {
		        	if(log.isDebugEnabled())log.debug("cargo informe con rango");
		        	tipoTareaDevolver = "InformeFechaRango";
		        }
	        	else if(informe.equalsIgnoreCase("odesUsuario"))
		        {
		        	if(log.isDebugEnabled())log.debug("cargo informe con usuario");
		        	tipoTareaDevolver = "InformeFechaUsuario";
		        }
		       
	        }
	        
	        //el tipoTarea es informeFederado
	        else if(tipoTarea.equalsIgnoreCase("GenerarInformeFederado"))
			{
				tipoTareaDevolver = "InformeFederado";
			}
	        //el tipoTarea es Informe Federado nivel Agregación
	        else if(tipoTarea.equalsIgnoreCase("GenerarInformeFederadoNivelAgregacion"))
			{
				tipoTareaDevolver = "InformeFederadoNivelAgregacion";
			}
	        else if(tipoTarea.equalsIgnoreCase("repositorio"))
	        {
	        	tipoTareaDevolver = "InformeCatalogo";
	        }
	        //Si el tipoTarea es Reindexado, carga ODEs o eliminar ODEs el tipo tarea se mantiene
	        else
    			tipoTareaDevolver = tipoTarea;
	        
	        if(log.isDebugEnabled())log.debug("tipoTarea -> " + tipoTarea);
		         
		         
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
	    }
	
	    log.debug("tipoTarea: " + tipoTarea);
		return tipoTareaDevolver;
    }


    public final void obtenerTareaModificarCargaODEs(ActionMapping mapping, ObtenerTareaModificarCargaODEsForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
 	 
    	try
        {       		   		
       		
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaCargaODEsVO tareaCargaODEs = this.getSrvPlanificadorService().obtenerTareaModificarCargaODEs(tarea);
    	
    		tareaCargaODEs.getFechaInicio().setTimeZone(tz);

    		
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaCargaODEs.getPeriodicidad());
    		log.debug("llega a getPeriodicidad "+tareaCargaODEs.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setPathODEs(tareaCargaODEs.getPathODE());
    		log.debug("llega a getPathODE "+tareaCargaODEs.getPathODE());
    		((DescripcionTareaFormImpl) form).setPathODEsCargados(tareaCargaODEs.getPathODEsCargados());
    		log.debug("llega a getPathODEsCargados "+tareaCargaODEs.getPathODEsCargados());
    		((DescripcionTareaFormImpl) form).setPathODEsNoCargados(tareaCargaODEs.getPathODEsNoCargados());
    		log.debug("llega a getPathODEsNoCargados "+tareaCargaODEs.getPathODEsNoCargados());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaCargaODEs.getTipoTarea());
    		log.debug("llega a getTipoTarea "+tareaCargaODEs.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaCargaODEs.getFechaInicio().get(Calendar.YEAR)));
    		log.debug("llega a getFechaInicio "+tareaCargaODEs.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaCargaODEs.getFechaInicio().get(Calendar.MONTH) + 1));
    		log.debug("llega a getFechaInicio "+tareaCargaODEs.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaCargaODEs.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		log.debug("llega a getFechaInicio "+tareaCargaODEs.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaCargaODEs.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		log.debug("llega a getFechaInicio "+tareaCargaODEs.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaCargaODEs.getFechaInicio().get(Calendar.MINUTE)));
    		log.debug("llega a getFechaInicio "+tareaCargaODEs.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMsgPublicado(tareaCargaODEs.getMsgPublicado());
    		log.debug("llega a getMsgPublicado "+tareaCargaODEs.getMsgPublicado());
    		((DescripcionTareaFormImpl) form).setMsgNoPublicado(tareaCargaODEs.getMsgNoPublicado());
    		log.debug("llega a getMsgNoPublicado "+tareaCargaODEs.getMsgNoPublicado());
    		((DescripcionTareaFormImpl) form).setMsgDescCargaODEs(tareaCargaODEs.getMsgDescripcionTrabajo());
    		log.debug("llega a getMsgDescripcionTrabajo "+tareaCargaODEs.getMsgDescripcionTrabajo());
    		((DescripcionTareaFormImpl) form).setSobrescribir(tareaCargaODEs.getSobrescribir());
    		log.debug("llega a sobreescribir "+tareaCargaODEs.getSobrescribir());
    		if (tareaCargaODEs.getNombreLote()!=null){
    			((DescripcionTareaFormImpl) form).setNombreLote(tareaCargaODEs.getNombreLote());
    		}
    		if (tareaCargaODEs.getDescripcionTarea()!=null){
    			((DescripcionTareaFormImpl) form).setDescripcionTarea(tareaCargaODEs.getDescripcionTarea());
    		}
    		
    		
    		
//    		((DescripcionTareaFormImpl) form).setPathODEs("Path ODEs");
//    		((DescripcionTareaFormImpl) form).setDescripcionTarea("descripcion tarea");
//    		((DescripcionTareaFormImpl) form).setNombreLote("nombre lote");
    		

        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
     }


    public final void obtenerTareaReindexado(ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaReindexadoForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaReindexadoVO tareaReindexado = this.getSrvPlanificadorService().obtenerTareaReindexado(tarea);
    		
    		tareaReindexado.getFechaInicio().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaReindexado.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setRepositorio(tareaReindexado.getRepositorioIndices());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaReindexado.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaReindexado.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaReindexado.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaReindexado.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaReindexado.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaReindexado.getFechaInicio().get(Calendar.MINUTE)));
    		((DescripcionTareaFormImpl) form).setMsgReindexado(tareaReindexado.getMsgReindexado());
    		((DescripcionTareaFormImpl) form).setMsgNoReindexado(tareaReindexado.getMsgNoReindexado());
    		((DescripcionTareaFormImpl) form).setMsgDescReindexado(tareaReindexado.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
     }
    
    public void obtenerTareaEliminarOdes(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaEliminarOdesForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaEliminaNoDisponiblesVO tareaEliminarOdes = this.getSrvPlanificadorService().obtenerTareaEliminarrNoDisponibles(tarea);
    		
    		tareaEliminarOdes.getFechaInicio().setTimeZone(tz);
    		tareaEliminarOdes.getFechaDesde().setTimeZone(tz);
    		tareaEliminarOdes.getFechaHasta().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaEliminarOdes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaEliminarOdes.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaEliminarOdes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaEliminarOdes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaEliminarOdes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaEliminarOdes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaEliminarOdes.getFechaInicio().get(Calendar.MINUTE)));
    		((DescripcionTareaFormImpl) form).setAnioDesde(new Integer (tareaEliminarOdes.getFechaDesde().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMesDesde(new Integer (tareaEliminarOdes.getFechaDesde().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDiaDesde(new Integer (tareaEliminarOdes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setAnioHasta(new Integer (tareaEliminarOdes.getFechaHasta().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMesHasta(new Integer (tareaEliminarOdes.getFechaHasta().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDiaHasta(new Integer (tareaEliminarOdes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setMsgEliminado(tareaEliminarOdes.getMsgEliminado());
    		((DescripcionTareaFormImpl) form).setMsgNoEliminado(tareaEliminarOdes.getMsgNoEliminado());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaEliminarOdes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
       
    }
    
    
    /**
     * 
     */
    public void obtenerTareaInformeFecha(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaInformeFechaForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
    		{
    			if(log.isDebugEnabled())log.debug("No es periodica");
    			tareaInformes.getFechaDesde().setTimeZone(tz);
        		tareaInformes.getFechaHasta().setTimeZone(tz);
        		((DescripcionTareaFormImpl) form).setDiaDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
        		((DescripcionTareaFormImpl) form).setDiaHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.YEAR)));
    		}else
    		{
    			if(log.isDebugEnabled())log.debug("es periodica");
    		}
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
    }

    /**
     * 
     */
    public void obtenerTareaInformeFechaRango(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaInformeFechaRangoForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
    		{
    			if(log.isDebugEnabled())log.debug("No es periodica");
    			tareaInformes.getFechaDesde().setTimeZone(tz);
        		tareaInformes.getFechaHasta().setTimeZone(tz);
        		((DescripcionTareaFormImpl) form).setDiaDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
        		((DescripcionTareaFormImpl) form).setDiaHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.YEAR)));
    		}else
    		{
    			if(log.isDebugEnabled())log.debug("es periodica");
    		}
    		
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setRango(tareaInformes.getRango());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }

    /**
     * 
     */
    public void obtenerTareaInformeFechaUsuario(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaInformeFechaUsuarioForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
       		    		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
    		{
    			if(log.isDebugEnabled())log.debug("No es periodica");
    			tareaInformes.getFechaDesde().setTimeZone(tz);
        		tareaInformes.getFechaHasta().setTimeZone(tz);
        		((DescripcionTareaFormImpl) form).setDiaDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioDesde(new Integer (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
        		((DescripcionTareaFormImpl) form).setDiaHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
        		((DescripcionTareaFormImpl) form).setMesHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
        		((DescripcionTareaFormImpl) form).setAnioHasta(new Integer (tareaInformes.getFechaHasta().get(Calendar.YEAR)));    		

    		}else
    		{
    			if(log.isDebugEnabled())log.debug("es periodica");
    		}
    		
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setUsuario(tareaInformes.getUsuarioInforme());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }
    
    
    public void obternerTareaInformeFederado(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.descripcionTarea.ObternerTareaInformeFederadoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }
    
    
    public void obtenerTareaInformeCatalogo(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.descripcionTarea.ObtenerTareaInformeCatalogoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformeCatalogoVO tarea = new TareaInformeCatalogoVO();
    	
    	try
        {    	
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		tarea.setIdioma(((DescripcionTareaFormImpl) form).getRepositorio());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformeCatalogoVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformeCatalogo(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
    		if (tareaInformes.getFechaActualizacion()!=null  ){
    			((DescripcionTareaFormImpl) form).setAnoActualizacion((new Integer (tareaInformes.getFechaActualizacion().get(Calendar.YEAR))));
    			((DescripcionTareaFormImpl) form).setMesActualizacion(new Integer (tareaInformes.getFechaActualizacion().get(Calendar.MONTH) + 1));
    			((DescripcionTareaFormImpl) form).setDiaActualizacion(new Integer (tareaInformes.getFechaActualizacion().get(Calendar.DAY_OF_MONTH)));
    		}
    		
    		((DescripcionTareaFormImpl) form).setRepositorio(tareaInformes.getIdioma());
    		log.debug("Insertamos el idioma en el formulario"+ ((DescripcionTareaFormImpl) form).getRepositorio());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }


	@Override
	public void obtenerRegenerarImagenes(ActionMapping mapping, ObtenerRegenerarImagenesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaRegenerarImagenesVO tareaRegenerar = this.getSrvPlanificadorService().obtenerTareaRegenerarImagenes(tarea);
    		
    		tareaRegenerar.getFechaInicio().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaRegenerar.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaRegenerar.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(new Integer (tareaRegenerar.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(new Integer (tareaRegenerar.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(new Integer (tareaRegenerar.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(new Integer (tareaRegenerar.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(new Integer (tareaRegenerar.getFechaInicio().get(Calendar.MINUTE)));
//    		((DescripcionTareaFormImpl) form).setMsgReindexado(tareaRegenerar.getMsgReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgNoReindexado(tareaRegenerar.getMsgNoReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgDescReindexado(tareaRegenerar.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
		
	}


	@Override
	public void obtenerGenerarSitemaps(ActionMapping mapping, ObtenerGenerarSitemapsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaRegenerarImagenesVO tareaRegenerar = this.getSrvPlanificadorService().obtenerTareaRegenerarImagenes(tarea);
    		
    		tareaRegenerar.getFechaInicio().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaRegenerar.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaRegenerar.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(tareaRegenerar.getFechaInicio().get(Calendar.YEAR));
    		((DescripcionTareaFormImpl) form).setMes(tareaRegenerar.getFechaInicio().get(Calendar.MONTH) + 1);
    		((DescripcionTareaFormImpl) form).setDia(tareaRegenerar.getFechaInicio().get(Calendar.DAY_OF_MONTH));
    		((DescripcionTareaFormImpl) form).setHora(tareaRegenerar.getFechaInicio().get(Calendar.HOUR_OF_DAY));
    		((DescripcionTareaFormImpl) form).setMinutos(tareaRegenerar.getFechaInicio().get(Calendar.MINUTE));
//    		((DescripcionTareaFormImpl) form).setMsgReindexado(tareaRegenerar.getMsgReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgNoReindexado(tareaRegenerar.getMsgNoReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgDescReindexado(tareaRegenerar.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerLanzarRSS(ActionMapping mapping, ObtenerLanzarRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaRegenerarImagenesVO tareaRegenerar = this.getSrvPlanificadorService().obtenerTareaRegenerarImagenes(tarea);
    		
    		tareaRegenerar.getFechaInicio().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaRegenerar.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaRegenerar.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(tareaRegenerar.getFechaInicio().get(Calendar.YEAR));
    		((DescripcionTareaFormImpl) form).setMes(tareaRegenerar.getFechaInicio().get(Calendar.MONTH) + 1);
    		((DescripcionTareaFormImpl) form).setDia(tareaRegenerar.getFechaInicio().get(Calendar.DAY_OF_MONTH));
    		((DescripcionTareaFormImpl) form).setHora(tareaRegenerar.getFechaInicio().get(Calendar.HOUR_OF_DAY));
    		((DescripcionTareaFormImpl) form).setMinutos(tareaRegenerar.getFechaInicio().get(Calendar.MINUTE));
//    		((DescripcionTareaFormImpl) form).setMsgReindexado(tareaRegenerar.getMsgReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgNoReindexado(tareaRegenerar.getMsgNoReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgDescReindexado(tareaRegenerar.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
		
	}

	public void obtenerTareaDespublicar(ActionMapping mapping, ObtenerTareaDespublicarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    
		boolean valida=true;
    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	String pathDespublicar="";
    	try
        {       		   	
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaDespublicarODEsVO despublicarODEsVO = this.getSrvPlanificadorService().obtenerTareaDespublicarODEs(tarea);
    	
    		despublicarODEsVO.getFechaInicio().setTimeZone(tz);

    		
    		String[] identificadores=despublicarODEsVO.getIdentificadores();
    		ArrayList identificadoresValidos = new ArrayList();
    		//Validamos el los identificadores
    		if (identificadores!=null){
	    		ValidadorMEC validadorMEC = new ValidadorMEC();
	    		for (int i=0; i<identificadores.length; i++){  	
	      			valida = validadorMEC.validar(identificadores[i]);
	      			if (valida==true){
	      				identificadoresValidos.add(identificadores[i]);
	      			}
	    		}
	    		String[] ideValidos=(String[])identificadoresValidos.toArray(new String[0]);
	    		
//	    		Creo el fichero temporal y me devuelve el nombre del archivo creado
	    		 pathDespublicar = this.crearTemporal(ideValidos, tarea.getTrabajo());
    		}
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(despublicarODEsVO.getPeriodicidad());
    		log.debug("llega a getPeriodicidad "+despublicarODEsVO.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(despublicarODEsVO.getTipoTarea());
    		log.debug("llega a getTipoTarea "+despublicarODEsVO.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(despublicarODEsVO.getFechaInicio().get(Calendar.YEAR));
    		log.debug("llega a getFechaInicio "+despublicarODEsVO.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMes(despublicarODEsVO.getFechaInicio().get(Calendar.MONTH) + 1);
    		log.debug("llega a getFechaInicio "+despublicarODEsVO.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setDia(despublicarODEsVO.getFechaInicio().get(Calendar.DAY_OF_MONTH));
    		log.debug("llega a getFechaInicio "+despublicarODEsVO.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setHora(despublicarODEsVO.getFechaInicio().get(Calendar.HOUR_OF_DAY));
    		log.debug("llega a getFechaInicio "+despublicarODEsVO.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMinutos(despublicarODEsVO.getFechaInicio().get(Calendar.MINUTE));
    		log.debug("llega a getFechaInicio "+despublicarODEsVO.getFechaInicio());
    		((DescripcionTareaFormImpl) form).setMsgPublicado(despublicarODEsVO.getMsgDespublicado());
    		log.debug("llega a getMsgPublicado "+despublicarODEsVO.getMsgDespublicado());
    		((DescripcionTareaFormImpl) form).setMsgNoPublicado(despublicarODEsVO.getMsgDespublicado());
    		log.debug("llega a getMsgNoPublicado "+despublicarODEsVO.getMsgDespublicado());
    		
    		if (pathDespublicar!=null && pathDespublicar.length()>0){
    			((DescripcionTareaFormImpl) form).setPathDespublicar(pathDespublicar);
    			log.debug("llega a getPathDespublicar "+pathDespublicar);
    		}
    		
//    		((DescripcionTareaFormImpl) form).setPathODEs("Path ODEs");
//    		((DescripcionTareaFormImpl) form).setDescripcionTarea("descripcion tarea");
//    		((DescripcionTareaFormImpl) form).setNombreLote("nombre lote");
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	

		
	}


	public void recuperarDespublicados(ActionMapping mapping, RecuperarDespublicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fichero = form.getNombre();
		DataHandler dataHandler = null;
			try{
				log.info("Se recupera el archivo de despublicados");
				File file = null;

				file = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO) + "/" + fichero);
				
				if (!file.exists())
				{
					log.error("El fichero " + file.getAbsolutePath() + "  no existe, ha sido borrado o movido");
					throw new Exception ("el fichero no existe, ha sido borrado o movido");
				}
				
				FileDataSource fileDS = new FileDataSource(file);
				dataHandler = new DataHandler(fileDS);
			
				if (fichero.endsWith(".pdf")){
					response.setContentType("application/pdf");
				} else if (fichero.endsWith(".html") || fichero.endsWith(".htm")) {
					response.setContentType("application/html");
				} else if (fichero.endsWith(".xls")) {
					response.setContentType("application/xls");
				} else if (fichero.endsWith(".doc")) {
					response.setContentType("application/doc");
				}
				response.setHeader("Content-Disposition", "attachment;filename="+fichero);
				OutputStream out = response.getOutputStream();
				InputStream in = dataHandler.getInputStream();
				if(log.isDebugEnabled())log.debug("recuperando el fichero " + fichero);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count;
				while((count = in.read(buffer, 0, BUFFER_SIZE))!= -1)
				{
					out.write(buffer, 0, count);
				}
				
				out.flush();
				out.close();
			}catch (Exception e){
				log.error("Error al recuperar el fichero");
			}
	}
	
	private void escribirExcel(String[] identificadores, String rutaFichero){
		try 
		
		        { 
				
					int i=0;
		
		            //Se crea el libro Excel 
		            HSSFWorkbook wb = new HSSFWorkbook(); 
		            //Se crea una nueva hoja dentro del libro 
		            HSSFSheet sheet = wb.createSheet("Identificadores"); 
		            for (i=0; i<identificadores.length; i++){
		            //Se crea una fila dentro de la hoja    
		            HSSFRow row = sheet.createRow((short)i); 
		            //Creamos celdas de varios tipos 
		            row.createCell(0).setCellValue(identificadores[i]); 
		            }
		            //Escribimos los resultados a un fichero Excel 
		            FileOutputStream fileOut = new FileOutputStream(rutaFichero); 
		//File temporalExportacion = new File(System.getProperty("java.io.tmpdir") + "/" +nombreExcel);
		            wb.write(fileOut); 
		            fileOut.close(); 
		        } 
		
		        catch(IOException e) 
		
		        { 
		            log.error("Error al escribir el fichero."); 
		        }  
	}
	
	private String crearTemporal(String[] identificadores, String trabajo) throws Exception{
		
		//El nombre del Excel será igual que el del trabajo
		String nombreExcel=trabajo+".xls";

		//tmpDir=tmpDir.replace("/", "\\\\");
		String rutaFichero = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO) + nombreExcel;
		log.info("el fichero se creará en la siguiente ruta: " + rutaFichero);
		File fichero = new File (rutaFichero);
		try {

			  // A partir del objeto File creamos el fichero físicamente
			if (fichero.exists()==false){
				  if (fichero.createNewFile()){
					  this.escribirExcel(identificadores, rutaFichero);
					  log.debug("El fichero se ha creado correctamente");
					  return nombreExcel;
				  }
				log.error("No ha podido ser creado el fichero de despublicaciono");
			}else{
				log.debug("El fichero ya existía");
				return nombreExcel;
			}
			} catch (IOException ioe) {				log.error("No ha podido ser creado el fichero de despublicacion " + ioe);;
			}
		return null;
	}



	public void obtenerGenerarOaiOre(ActionMapping mapping, ObtenerGenerarOaiOreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		TareaVO tareaRegenerar = this.getSrvPlanificadorService().obtenerTareaGenerarOaiOre(tarea);
    		
    		tareaRegenerar.getFechaInicio().setTimeZone(tz);
    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaRegenerar.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaRegenerar.getTipoTarea());    		
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(tareaRegenerar.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(tareaRegenerar.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(tareaRegenerar.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(tareaRegenerar.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(tareaRegenerar.getFechaInicio().get(Calendar.MINUTE)));
//    		((DescripcionTareaFormImpl) form).setMsgReindexado(tareaRegenerar.getMsgReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgNoReindexado(tareaRegenerar.getMsgNoReindexado());
//    		((DescripcionTareaFormImpl) form).setMsgDescReindexado(tareaRegenerar.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obternerTareaInformeFederadoNivelAgregacion(ActionMapping mapping, ObternerTareaInformeFederadoNivelAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaInformesNivelAgregacionFederadaVO tarea = new TareaInformesNivelAgregacionFederadaVO();
    	
    	try {       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesNivelAgregacionFederadaVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformeNivelAgregacion(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((DescripcionTareaFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setFormato(tareaInformes.getFormato());
    		((DescripcionTareaFormImpl) form).setInforme(tareaInformes.getInforme());
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
       		((DescripcionTareaFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
    		((DescripcionTareaFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		((DescripcionTareaFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
    		((DescripcionTareaFormImpl) form).setAnioInicioInforme(Integer.valueOf(tareaInformes.getFechaNivelAgregacion().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMesInicioInforme(Integer.valueOf(tareaInformes.getFechaNivelAgregacion().get(Calendar.MONTH) + 1));
        }catch (Exception e) {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerSubirIndices(ActionMapping mapping,
			ObtenerSubirIndicesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try {       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaVO t = this.getSrvPlanificadorService().obtenerTareaSubirIndices(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		t.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
        }catch (Exception e) {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerActualizarIndicesRemotos(ActionMapping mapping,
			ObtenerActualizarIndicesRemotosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try {       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaVO t = this.getSrvPlanificadorService().obtenerTareaActualizarIndicesRemotos(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		t.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
        }catch (Exception e) {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerTareaGenerarEstadisticasTotales(ActionMapping mapping,
			ObtenerTareaGenerarEstadisticasTotalesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try {       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaVO t = this.getSrvPlanificadorService().obtenerTareaEstadisticasTotales(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		t.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
        }catch (Exception e) {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerTareaGenerarEstadisticasLocales(ActionMapping mapping,
			ObtenerTareaGenerarEstadisticasLocalesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	if (tz == null) 
    		tz = utilidades.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try {       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaVO t = this.getSrvPlanificadorService().obtenerTareaEstadisticasLocales(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		t.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea    		
    		((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
    		((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());
    		((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
    		((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
    		((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
        }catch (Exception e) {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
	}


	@Override
	public void obtenerTareaObtenerMetadatosODESFederados(
			ActionMapping mapping,
			ObtenerTareaObtenerMetadatosODESFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerMetadatosODESFederados(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
			((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}


	@Override
	public void obtenerTareaObtenerODESDespublicadosFederados(
			ActionMapping mapping,
			ObtenerTareaObtenerODESDespublicadosFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerODESDespublicadosFederados(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
			((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}


	@Override
	public void obtenerTareaObtenerMetadatosODESFederadosFaltantes(
			ActionMapping mapping,
			ObtenerTareaObtenerMetadatosODESFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerMetadatosODESFederadosFaltantes(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
			((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}


	@Override
	public void obtenerTareaObtenerODESDespublicadosFederadosFaltantes(
			ActionMapping mapping,
			ObtenerTareaObtenerODESDespublicadosFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((DescripcionTareaFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((DescripcionTareaFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((DescripcionTareaFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((DescripcionTareaFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((DescripcionTareaFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerODESDespublicadosFederadosFaltantes(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((DescripcionTareaFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((DescripcionTareaFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((DescripcionTareaFormImpl) form).setAnio(Integer.valueOf(t.getFechaInicio().get(Calendar.YEAR)));
			((DescripcionTareaFormImpl) form).setMes(Integer.valueOf(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((DescripcionTareaFormImpl) form).setDia(Integer.valueOf(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((DescripcionTareaFormImpl) form).setHora(Integer.valueOf(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((DescripcionTareaFormImpl) form).setMinutos(Integer.valueOf(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}
	
	
	
}
