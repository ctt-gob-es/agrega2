// license-header java merge-point
package es.pode.administracion.presentacion.planificador.modificarTarea;


import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.administracion.presentacion.planificador.comun.Utiles;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.planificador.negocio.servicios.TareaCargaODEsVO;
import es.pode.planificador.negocio.servicios.TareaDespublicarODEsVO;
import es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO;
import es.pode.planificador.negocio.servicios.TareaGenerarSitemapsVO;
import es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO;
import es.pode.planificador.negocio.servicios.TareaInformesNivelAgregacionFederadaVO;
import es.pode.planificador.negocio.servicios.TareaInformesVO;
import es.pode.planificador.negocio.servicios.TareaLanzarRSSVO;
import es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO;
import es.pode.planificador.negocio.servicios.TareaReindexadoVO;
import es.pode.planificador.negocio.servicios.TareaVO;
import es.pode.planificador.negocio.servicios.TrabajoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.validador.ValidadorMEC;


public class ObtenerTareaControllerImpl extends ObtenerTareaController
{
	private static Logger log = Logger.getLogger(ObtenerTareaControllerImpl.class);
	private static TimeZone tz = null;
	
	/**
     * Metodo que optiene cual es la tarea que se va a modificar y bifurcara a ese tipo concreto
     */
    public final java.lang.String obtenerTipoTarea(ActionMapping mapping, ObtenerTipoTareaForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String tipoTarea = null;
		String tipoTareaDevolver = null;
		
	    try
	    {	
	    	//obtenemos el tipoTarea
	        TrabajoVO trabajo = new TrabajoVO();
	        
	        trabajo.setTrabajo(form.getTrabajo());
	        trabajo.setGrupoTrabajo(form.getGrupoTrabajo());
	        trabajo.setUsuario(LdapUserDetailsUtils.getUsuario());
	        
	        tipoTarea = this.getSrvPlanificadorService().obtenerTipoTarea(trabajo); 
	        
	        
	        //si la tarea es Generar informes comprobamos de que informe se trata y si no se devuleve la tarea que venga(cargaOdes, reindexado o eliminarODes)
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
	        
		        //si el tipoTarea es de informes debemos mirar que informe concreto es para redirigirnos a una de las 3 pantallas de informes
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
	        
	        //si el tipo de informe es federado
	        else if(tipoTarea.equalsIgnoreCase("GenerarInformeFederado"))
	        {
        			tipoTareaDevolver = "InformeFederado";
	        }
	        //si el tipo de informe es federado nivel agregacion
	        else if(tipoTarea.equalsIgnoreCase("GenerarInformeFederadoNivelAgregacion"))
	        {
        			tipoTareaDevolver = "InformeFederadoNivelAgregacion";
	        }
	        
	        //si el tipo es informe de catalogo
	        else if(tipoTarea.equalsIgnoreCase("InformeCatalogo"))
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
    
    
    
    
    /**
     * ******************************			METODOS DE OBTENCION DE LA TAREA			****************************************
     */
    
    
    
    
    /**
     * Metodo que obtiene los datos de la tarea de carga de odes que se selecciona
     */
    /* (non-Javadoc)
     * @see es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaController#obtenerTareaModificarCargaODEs(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaModificarCargaODEsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerTareaModificarCargaODEs(ActionMapping mapping, ObtenerTareaModificarCargaODEsForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
 	 
    	try
        {       		  
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaCargaODEsVO tareaCargaODEs = this.getSrvPlanificadorService().obtenerTareaModificarCargaODEs(tarea);
    	
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaCargaODEs.getFechaInicio().setTimeZone(tz);

       		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaCargaODEs.getPeriodicidad());
    		esteForm.setPathODEs(tareaCargaODEs.getPathODE());
    		esteForm.setPathODEsCarg(tareaCargaODEs.getPathODEsCargados());
    		esteForm.setPathODEsNoCarg(tareaCargaODEs.getPathODEsNoCargados());
    		esteForm.setTipoTarea(tareaCargaODEs.getTipoTarea());
    		
    		esteForm.setAnio(Integer.toString (tareaCargaODEs.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaCargaODEs.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaCargaODEs.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaCargaODEs.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString(tareaCargaODEs.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgPublicado(tareaCargaODEs.getMsgPublicado());
    		esteForm.setMsgNoPublicado(tareaCargaODEs.getMsgNoPublicado());
    		esteForm.setMsgDescCargaODEs(tareaCargaODEs.getMsgDescripcionTrabajo());
    		esteForm.setSobrescribir(tareaCargaODEs.getSobrescribir());
    		esteForm.setNombreLote(tareaCargaODEs.getNombreLote());
    		esteForm.setDescripcionTarea(tareaCargaODEs.getDescripcionTarea());

        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
    }

    
    /**
     * Metodo que obtiene los datos de la tarea de reindexado que se selecciona
     */
    public final void obtenerTareaReindexado(ActionMapping mapping, ObtenerTareaReindexadoForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaReindexadoVO tareaReindexado = this.getSrvPlanificadorService().obtenerTareaReindexado(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaReindexado.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaReindexado.getPeriodicidad());
    		esteForm.setRepositorio(tareaReindexado.getRepositorioIndices());
    		esteForm.setTipoTarea(tareaReindexado.getTipoTarea());    		
    		esteForm.setAnio(Integer.toString(tareaReindexado.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaReindexado.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaReindexado.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaReindexado.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString (tareaReindexado.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgReindexado(tareaReindexado.getMsgReindexado());
    		esteForm.setMsgNoReindexado(tareaReindexado.getMsgNoReindexado());
    		esteForm.setMsgDescReindexado(tareaReindexado.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
    }
    
    
    
    /**
     * Metodo que obtiene los datos de la tarea de eliminar odes que se selecciona
     */
    public void obtenerTareaNoDisponible(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaNoDisponibleForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
	
    
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaEliminaNoDisponiblesVO tareaEliminarODEs = getSrvPlanificadorService().obtenerTareaEliminarrNoDisponibles(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaEliminarODEs.getFechaInicio().setTimeZone(tz);
    		tareaEliminarODEs.getFechaDesde().setTimeZone(tz);
    		tareaEliminarODEs.getFechaHasta().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaEliminarODEs.getPeriodicidad());
    		esteForm.setTipoTarea(tareaEliminarODEs.getTipoTarea());
    		esteForm.setAnio(Integer.toString(tareaEliminarODEs.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaEliminarODEs.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaEliminarODEs.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaEliminarODEs.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString (tareaEliminarODEs.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setDiaDesde(Integer.toString(tareaEliminarODEs.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setMesDesde(Integer.toString (tareaEliminarODEs.getFechaDesde().get(Calendar.MONTH) + 1));
    		esteForm.setAnioDesde(Integer.toString (tareaEliminarODEs.getFechaDesde().get(Calendar.YEAR)));
    		esteForm.setDiaHasta(Integer.toString (tareaEliminarODEs.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setMesHasta(Integer.toString (tareaEliminarODEs.getFechaHasta().get(Calendar.MONTH) + 1));
    		esteForm.setAnioHasta(Integer.toString (tareaEliminarODEs.getFechaHasta().get(Calendar.YEAR)));
    		esteForm.setMsgEliminado(tareaEliminarODEs.getMsgEliminado());
    		esteForm.setMsgNoEliminado(tareaEliminarODEs.getMsgNoEliminado());
    		esteForm.setMsgDescTrabajo(tareaEliminarODEs.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
	}
    
    
    /**
     * Metodo que obtiene los datos de la tarea de informeFecha que se selecciona
     */
    public void obtenerTareaInformeFecha(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaInformeFechaForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
			
			//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
			if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "No es periodica");
				
				//asignamos a la fecha Desde y Hasta la franja horaria correspondiente
				tareaInformes.getFechaDesde().setTimeZone(tz);
				tareaInformes.getFechaHasta().setTimeZone(tz);
				
				esteForm.setDiaDesde(Integer.toString(tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesDesde(Integer.toString(tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
				esteForm.setAnioDesde(Integer.toString (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
				esteForm.setDiaHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
				esteForm.setAnioHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.YEAR)));
			}
			else
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "Es periodica");
			}
    		
    		//asignamos a la fecha inicio la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
			
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaInformes.getPeriodicidad());
    		esteForm.setTipoTarea(tareaInformes.getInforme());
    		esteForm.setFormato(tareaInformes.getFormato());
    		esteForm.setInforme(tareaInformes.getInforme());
    		esteForm.setAnio(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgInforme(tareaInformes.getMsgInforme());
    		esteForm.setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		esteForm.setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
    }

    
    /**
     * Metodo que obtiene los datos de la tarea de informeFechaRango que se selecciona
     */
    public void obtenerTareaInformeFechaRango(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaInformeFechaRangoForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
			
    		//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
			if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "No es periodica");
				
				//asignamos a la fecha Desde y Hasta la franja horaria correspondiente
				tareaInformes.getFechaDesde().setTimeZone(tz);
				tareaInformes.getFechaHasta().setTimeZone(tz);
				
				esteForm.setDiaDesde(Integer.toString(tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesDesde(Integer.toString (tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
				esteForm.setAnioDesde(Integer.toString (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
				esteForm.setDiaHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
				esteForm.setAnioHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.YEAR)));
			}
			else
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "Es periodica");
			}
			
			//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaInformes.getPeriodicidad());
    		esteForm.setTipoTarea(tareaInformes.getInforme());
    		esteForm.setFormato(tareaInformes.getFormato());
    		esteForm.setInforme(tareaInformes.getInforme());
    		esteForm.setRango(tareaInformes.getRango());
    		esteForm.setAnio(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgReindexado(tareaInformes.getMsgInforme());
    		esteForm.setMsgNoReindexado(tareaInformes.getMsgNoInforme());
    		esteForm.setMsgDescReindexado(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
        
    }

    
    /**
     * Metodo que obtiene los datos de la tarea de informeFechaUsuario que se selecciona
     */
    public void obtenerTareaInformeFechaUsuario(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaInformeFechaUsuarioForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {

    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
			
			//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
			if(tareaInformes.getPeriodicidad().equalsIgnoreCase("N"))
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "No es periodica");
				
				//asignamos a la fecha Desde y Hasta la franja horaria correspondiente
				tareaInformes.getFechaDesde().setTimeZone(tz);
				tareaInformes.getFechaHasta().setTimeZone(tz);
				
				esteForm.setDiaDesde(Integer.toString(tareaInformes.getFechaDesde().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesDesde(Integer.toString (tareaInformes.getFechaDesde().get(Calendar.MONTH) + 1));
				esteForm.setAnioDesde(Integer.toString (tareaInformes.getFechaDesde().get(Calendar.YEAR)));
				esteForm.setDiaHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.DAY_OF_MONTH)));
				esteForm.setMesHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.MONTH) + 1));
				esteForm.setAnioHasta(Integer.toString (tareaInformes.getFechaHasta().get(Calendar.YEAR)));
			}
			else
			{
				if(log.isDebugEnabled())log.debug(tareaInformes + "Es periodica");
			}
    		
			//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
			
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaInformes.getPeriodicidad());
    		esteForm.setTipoTarea(tareaInformes.getInforme());
    		esteForm.setFormato(tareaInformes.getFormato());
    		esteForm.setInforme(tareaInformes.getInforme());
    		esteForm.setUsuario(tareaInformes.getUsuarioInforme());
    		esteForm.setAnio(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgInforme(tareaInformes.getMsgInforme());
    		esteForm.setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		esteForm.setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
        
    }

    /**
     * Metodo que obtiene los datos de la tarea de informe federado que hay almacenados actualmente
     */
    public void obtenerTareaInformeFederado(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaInformeFederadoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaInformesVO tarea = new TareaInformesVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformesVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformes(tarea);
			
			//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
			
    		//Las fechas desde y hasta se las asignamos en el servicio
    		
			//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
			
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaInformes.getPeriodicidad());
    		esteForm.setTipoTarea(tareaInformes.getInforme());
    		esteForm.setFormato(tareaInformes.getFormato());
    		esteForm.setInforme(tareaInformes.getInforme());
    		esteForm.setAnio(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString (tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgInforme(tareaInformes.getMsgInforme());
    		esteForm.setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		esteForm.setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
            }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }
    
    /**
     * Metodo que obtiene los datos que estan registrados de la tarea de informe catalogo
     */
    public void obtenerTareaInformeCatalogo(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.modificarTarea.ObtenerTareaInformeCatalogoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaInformeCatalogoVO tarea = new TareaInformeCatalogoVO();
    	
    	try
        {    	
    		ObtenerTCargaODEsFormImpl esteForm=(ObtenerTCargaODEsFormImpl) form;
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
    		tarea.setTrabajo(esteForm.getTrabajo());
    		tarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		tarea.setTrigger(esteForm.getTrigger());
    		tarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		tarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaInformeCatalogoVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformeCatalogo(tarea);
			
			//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
			
    		//Las fechas desde y hasta se las asignamos en el servicio
    		
			//asignamos a las fechas la franja horaria correspondiente
    		tareaInformes.getFechaInicio().setTimeZone(tz);
			
    		//rellenamos los datos especificos de este tipo de tarea
    		esteForm.setPeriodicidad(tareaInformes.getPeriodicidad());
    		esteForm.setTipoTarea(tareaInformes.getInforme());
    		esteForm.setFormato(tareaInformes.getFormato());
    		esteForm.setInforme(tareaInformes.getInforme());
    		esteForm.setAnio(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.YEAR)));
    		esteForm.setMes(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
    		esteForm.setDia(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
    		esteForm.setHora(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
    		esteForm.setMinutos(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
    		esteForm.setMsgInforme(tareaInformes.getMsgInforme());
    		esteForm.setMsgNoInforme(tareaInformes.getMsgNoInforme());
    		esteForm.setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
    		esteForm.setRepositorio(tareaInformes.getIdioma());
    		if (tareaInformes.getFechaActualizacion()!=null){
    			esteForm.setAnoActualizacion(Integer.toString(tareaInformes.getFechaActualizacion().get(Calendar.YEAR)));
    			esteForm.setMesActualizacion(Integer.toString(tareaInformes.getFechaActualizacion().get(Calendar.MONTH) + 1));
    			esteForm.setDiaActualizacion(Integer.toString(tareaInformes.getFechaActualizacion().get(Calendar.DAY_OF_MONTH)));
    		}
    		log.debug("Cogemos el idioma desde el formulario"+ tareaInformes.getIdioma());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }
    }
    
    
    /**
     * ******************************			METODOS DE VALIDACION			****************************************
     */
    
    
    
    /**
     * metodo que valida los campos de la tarea carga de odes(pantalla comun de la tareas)
     */
	public void validarFormularioCargaODEs(ActionMapping mapping, ValidarFormularioCargaODEsForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{

		FormularioIContinuarFormImpl formCarga = (FormularioIContinuarFormImpl) form;
		
		String dia = formCarga.getDia();
	 	String mes = formCarga.getMes();
	 	String anio = formCarga.getAnio();
	 	String hora = formCarga.getHora();
	 	String minutos = formCarga.getMinutos();
		
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.valueOf(formCarga.getAnio());
		String periodicidad = formCarga.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
	}

	
	 /**
     * metodo que valida los campos de la tarea reindexado(pantalla comun de la tareas)
     */
	public void validarFormularioReindexado(ActionMapping mapping, ValidarFormularioReindexadoForm form,  
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
	
		I18n i18n = I18n.getInstance();
    	
    	//Recogemos el idioma por defecto para mostrar en ese idioma la lista desplegable de idiomas
    	Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	
    	
    	//recogemos un array de objetos con la lista de idiomas
    	es.pode.soporte.i18n.LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	
    	//sacamos los idiomas del array de objetos y lo asignamos al combo
    	form.setRepositorioBackingList(Arrays.asList(localizacionArray), "idLocalizacion", "nombre");
		
		FormularioReindexadoIContinuarFormImpl formCarga = (FormularioReindexadoIContinuarFormImpl) form;
		
		
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formCarga.getAnio());
		String periodicidad = formCarga.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formCarga.getDia();
	 	String mes = formCarga.getMes();
	 	String anio = formCarga.getAnio();
	 	String hora = formCarga.getHora();
	 	String minutos = formCarga.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de reindexado");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
	}
	
	/**
     * metodo que valida los campos de la tarea eliminar odes
     */
	public void validarFormularioNoDisponibles(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ValidarFormularioNoDisponiblesForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    		{

		FormularioNoDisponiblesIContinuarFormImpl formCarga = (FormularioNoDisponiblesIContinuarFormImpl) form;
		//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formCarga.getAnio());
		String periodicidad = formCarga.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		String dia = formCarga.getDia();
		String mes = formCarga.getMes();
		String anio = formCarga.getAnio();
		String hora = formCarga.getHora();
		String minutos = formCarga.getMinutos();

		if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de eliminar odes");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
    }
	
	
	/**
     * metodo que valida los campos de la tarea informeFecha(pantalla comun de la tareas)
     */
    public void validarFormularioFecha(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ValidarFormularioFechaForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    		{

    	FormularioInformeFechaIContinuarFormImpl formCarga = (FormularioInformeFechaIContinuarFormImpl) form;
    	//	 	La tarea no puede ser superior al 12/12/2099
    	Integer anioInt = Integer.parseInt(formCarga.getAnio());
    	String periodicidad = formCarga.getPeriodicidad();
    	if (anioInt>2099 && "N".equals(periodicidad)==false){
    		log.error("El año de inicio de la tarea no puede ser superior a 2099");
    		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
    	}
    	String dia = formCarga.getDia();
    	String mes = formCarga.getMes();
    	String anio = formCarga.getAnio();
    	String hora = formCarga.getHora();
    	String minutos = formCarga.getMinutos();

    	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de informe con fechas");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
    }

    
    /**
     * metodo que valida los campos de la tarea informeFechaRango(pantalla comun de la tareas)
     */
    public void validarInformeFechaRango(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ValidarInformeFechaRangoForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception		
    		{

    	FormularioInformesFechaRangoIContinuarFormImpl formCarga = (FormularioInformesFechaRangoIContinuarFormImpl) form;
    	//	 	La tarea no puede ser superior al 12/12/2099
    	Integer anioInt = Integer.parseInt(formCarga.getAnio());
    	String periodicidad = formCarga.getPeriodicidad();
    	if (anioInt>2099 && "N".equals(periodicidad)==false){
    		log.error("El año de inicio de la tarea no puede ser superior a 2099");
    		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
    	}
    	String dia = formCarga.getDia();
    	String mes = formCarga.getMes();
    	String anio = formCarga.getAnio();
    	String hora = formCarga.getHora();
    	String minutos = formCarga.getMinutos();

    	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de fechas rango");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
    }

    
    /**
     * metodo que valida los campos de la tarea informeFechaUsuario(pantalla comun de la tareas)
     */
    public void validarInformeFechaUsuario(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ValidarInformeFechaUsuarioForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
		//cargamos el combo de usuarios
		List<UsuarioVO> usuariosList = Arrays.asList(this.getSrvAdminUsuariosService().listarTodosUsuarios());
		if(log.isDebugEnabled())log.debug("se recogen los usuarios de la aplicacion, estos son: " + usuariosList);
		form.setUsuarioBackingList(usuariosList, "usuario", "usuario");
		
		FormularioInformeFechaUsuarioIContinuarFormImpl formCarga = (FormularioInformeFechaUsuarioIContinuarFormImpl) form;
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formCarga.getAnio());
		String periodicidad = formCarga.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		String dia = formCarga.getDia();
	 	String mes = formCarga.getMes();
	 	String anio = formCarga.getAnio();
	 	String hora = formCarga.getHora();
	 	String minutos = formCarga.getMinutos();
		
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de informe usuarios");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}			
    }
	
    
    /**
     * Metodo que valida los campos introducidos en la modificacion del la tarea de informe de catalogo
     */
    public void validarInformeFederado(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.modificarTarea.ValidarInformeFederadoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	FormularioInformeFederadoIContinuarFormImpl formCarga = (FormularioInformeFederadoIContinuarFormImpl) form;
    	//	 	La tarea no puede ser superior al 12/12/2099
    	Integer anioInt = Integer.parseInt(formCarga.getAnio());
    	String periodicidad = formCarga.getPeriodicidad();
    	if (anioInt>2099 && "N".equals(periodicidad)==false){
    		log.error("El año de inicio de la tarea no puede ser superior a 2099");
    		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
    	}
    	String dia = formCarga.getDia();
    	String mes = formCarga.getMes();
    	String anio = formCarga.getAnio();
    	String hora = formCarga.getHora();
    	String minutos = formCarga.getMinutos();

    	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de fechas rango");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
    }
	
    
    public void validarInformeCatalogo(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.modificarTarea.ValidarInformeCatalogoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	I18n i18n = I18n.getInstance();

    	//Recogemos el idioma por defecto para mostrar en ese idioma la lista desplegable de idiomas
    	Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);


    	//recogemos un array de objetos con la lista de idiomas
    	es.pode.soporte.i18n.LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());

    	//sacamos los idiomas del array de objetos y lo asignamos al combo
    	form.setRepositorioBackingList(Arrays.asList(localizacionArray), "idLocalizacion", "nombre");
    	FormularioInformeCatalogoIContinuarFormImpl formCarga = (FormularioInformeCatalogoIContinuarFormImpl) form;
    	//	 	La tarea no puede ser superior al 12/12/2099
    	Integer anioInt = Integer.parseInt(formCarga.getAnio());
    	String periodicidad = formCarga.getPeriodicidad();
    	if (anioInt>2099 && "N".equals(periodicidad)==false){
    		log.error("El año de inicio de la tarea no puede ser superior a 2099");
    		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
    	}
    	String dia = formCarga.getDia();
    	String mes = formCarga.getMes();
    	String anio = formCarga.getAnio();
    	String hora = formCarga.getHora();
    	String minutos = formCarga.getMinutos();


    	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de informe catalogo");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
    }
	
	 /**
     * ******************************			METODOS DE MODIFICACION DE LA TAREA			****************************************
     */
	
	
	

    /**
     * Metodo que guarda los cambios que se han echo en la tarea carga de odes
     */
    public final void modificarTareaCargaODEs(ActionMapping mapping, ModificarTareaCargaODEsForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaCargaODEsVO datosTarea = new TareaCargaODEsVO(); // Datos a modificar
		
		TareaCargaODEsVO tareaRecuperada = null;//comprobar si el servicio manda la validacion ok o no
				
    	try
        {    			
    		FormularioIIAceptarFormImpl esteForm=(FormularioIIAceptarFormImpl) form;
    		trabajo.setTrabajo(esteForm.getTrabajo());
    		trabajo.setGrupoTrabajo(esteForm.getGrupoTrabajo());	
    		datosTarea.setTrabajo(esteForm.getTrabajo());
    		datosTarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		datosTarea.setTrigger(esteForm.getTrigger());
    		datosTarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		datosTarea.setPathODE(esteForm.getPathODEs());
    		datosTarea.setPathODEsCargados(esteForm.getPathODEsCarg());
    		datosTarea.setPathODEsNoCargados(esteForm.getPathODEsNoCarg());
    		datosTarea.setPeriodicidad(esteForm.getPeriodicidad());
    		datosTarea.setMsgPublicado(esteForm.getMsgPublicado());
    		datosTarea.setMsgNoPublicado(esteForm.getMsgNoPublicado());
    		datosTarea.setMsgDescripcionTrabajo(esteForm.getMsgDescCargaODEs());
			if (esteForm.getSobrescribir()!=null &&esteForm.getSobrescribir().equals("on"))
				datosTarea.setSobrescribir("s");
			else 
				datosTarea.setSobrescribir("n");
    		
    		
	        Calendar cal = Calendar.getInstance(tz);    		
	        cal = new GregorianCalendar(
	        		Integer.parseInt(esteForm.getAnio()), 
	        		Integer.parseInt(esteForm.getMes()) -1,
	        		Integer.parseInt(esteForm.getDia()),
	        		Integer.parseInt(esteForm.getHora()), 
	        		Integer.parseInt(esteForm.getMinutos()));
	        	        
	        datosTarea.setFechaInicio(cal);
	        datosTarea.setNombreLote(((FormularioIIAceptarFormImpl) form).getNombreLote());
    		datosTarea.setDescripcionTarea(((FormularioIIAceptarFormImpl) form).getDescripcionTarea());
    		datosTarea.setTipoTarea(((FormularioIIAceptarFormImpl) form).getTipoTarea());
    		    		
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		tareaRecuperada = this.getSrvPlanificadorService().modificarTareaCargaODEs(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
    		if (tareaRecuperada.getError() != null)
				throw new ValidatorException("{" + tareaRecuperada.getError()
						+ "}");
        }
    	catch (ValidatorException e2) {
			throw new ValidatorException("{" + tareaRecuperada.getError() + "}");
    	}
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }		
    }

    
    /**
     * Metodo que guarda los cambios que se han echo en la tarea reindexado
     */
    public final void modificarTareaReindexado(ActionMapping mapping, ModificarTareaReindexadoForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaReindexadoVO datosTarea = new TareaReindexadoVO(); // Datos a modificar
		
    	try
        {    				
    		FormularioReindexadoIIAceptarFormImpl esteForm=(FormularioReindexadoIIAceptarFormImpl) form;
    		trabajo.setTrabajo(esteForm.getTrabajo());
    		trabajo.setGrupoTrabajo(esteForm.getGrupoTrabajo());	
    		datosTarea.setTrabajo(esteForm.getTrabajo());
    		datosTarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		datosTarea.setTrigger(esteForm.getTrigger());
    		datosTarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		datosTarea.setRepositorioIndices(esteForm.getRepositorio());
    		datosTarea.setPeriodicidad(esteForm.getPeriodicidad());
    		
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		Integer.parseInt(esteForm.getAnio()),
	        		Integer.parseInt(esteForm.getMes())-1,
	        		Integer.parseInt(esteForm.getDia()),
	        		Integer.parseInt(esteForm.getHora()),
	        		Integer.parseInt(esteForm.getMinutos()));
	        	        
	        datosTarea.setFechaInicio(cal);
    		datosTarea.setMsgReindexado(esteForm.getMsgReindexado());
    		datosTarea.setMsgNoReindexado(esteForm.getMsgNoReindexado());
    		datosTarea.setMsgDescripcionTrabajo(esteForm.getMsgDescReindexado());
	        
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaReindexadoVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaReindexado(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }		
    }
    

	
    /**
     * Metodo que guarda los cambios que se han echo en la tarea eliminar odes
     */
    public void modificarTareaNoDisponibles(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ModificarTareaNoDisponiblesForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	FormularioNoDisponiblesIIAceptarFormImpl esteForm=(FormularioNoDisponiblesIIAceptarFormImpl)form;
    	String dia = esteForm.getDia();
    	String mes = esteForm.getMes();
    	String anio = esteForm.getAnio();
    	String hora = esteForm.getHora();
    	String minutos = esteForm.getMinutos();
    	String anioDesde = esteForm.getAnioDesde();
    	String mesDesde = esteForm.getMesDesde();
    	String diaDesde = esteForm.getDiaDesde();
    	String anioHasta = esteForm.getAnioHasta();
    	String mesHasta = esteForm.getMesHasta();
    	String diaHasta = esteForm.getDiaHasta();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaEliminaNoDisponiblesVO datosTarea = new TareaEliminaNoDisponiblesVO(); // Datos a modificar
				
    	try
        {    				
    		//Comprobamos que todos los campos de la fecha de inicio están rellenos
    		if( anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
    			
    			throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
    		
    		//Comprobamos que todos los campos de la fecha fin están rellenos
    		if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
    			
    			throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
    		
    		//Comprobamos que los campos de las fechas son numeros
    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas tiene caracteres no numericos");
    		try
    		{
    			new Integer (anioDesde).intValue(); 
				new Integer (mesDesde).intValue(); 
				new Integer (diaDesde).intValue();
    		 	
    		 	
    		}catch(Exception e)
    		{
    			log.error("Alguno de los campos de la fecha desde no son números");
    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
    		}
    		try
    		{
    			new Integer (anioHasta).intValue(); 
				new Integer (mesHasta).intValue(); 
				new Integer (diaHasta).intValue();
    		 	
    		}catch(Exception e)
    		{
    			log.error("Alguno de los campos de la fecha hasta no son números");
    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
    		}
    		
    		
			// Comprobamos si las fechas introducidas son correctas
			boolean fechaValidaDesde = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde,"yyyyMMdd");

			boolean fechaValidaHasta = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta,"yyyyMMdd");

			boolean comparacionFechaDesdeHasta = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde, anioHasta, mesHasta, diaHasta);

			if (!fechaValidaHasta && !fechaValidaDesde)
				throw new ValidatorException(
						"{tareas.fechaDesdeHastaIncorrecta}");

			if (!fechaValidaDesde)
				throw new ValidatorException("{tareas.fechaDesdeIncorrecta}");

			if (!fechaValidaHasta)
				throw new ValidatorException("{tareas.fechaHastaIncorrecta}");

			if (!comparacionFechaDesdeHasta)
				throw new ValidatorException("{tareas.fechaDesdeMasQueHasta}");

			try
    		{
			
    		trabajo.setTrabajo(esteForm.getTrabajo());
    		trabajo.setGrupoTrabajo(esteForm.getGrupoTrabajo());	
    		datosTarea.setTrabajo(esteForm.getTrabajo());
    		datosTarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		datosTarea.setTrigger(esteForm.getTrigger());
    		datosTarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		datosTarea.setPeriodicidad(esteForm.getPeriodicidad());
    		datosTarea.setMsgEliminado(esteForm.getMsgEliminado());
    		datosTarea.setMsgNoEliminado(esteForm.getMsgNoEliminado());
    		datosTarea.setMsgDescripcionTrabajo(esteForm.getMsgDescTrabajo());
			
    		
	        Calendar cal = Calendar.getInstance(tz);    		
	        cal = new GregorianCalendar(
	        		Integer.parseInt(anio), 
	        		Integer.parseInt(mes)- 1, 
	        		Integer.parseInt(dia),
	        		Integer.parseInt(hora), 
	        		Integer.parseInt(minutos));
	        
	        Calendar calFechaDesde = Calendar.getInstance(tz);
	        calFechaDesde = new GregorianCalendar(
	        		Integer.parseInt(anioDesde), 
	        		Integer.parseInt(mesDesde)-1,
	        		Integer.parseInt(diaDesde),0,1);
	        		
	        Calendar calFechaHasta = Calendar.getInstance(tz);
	        calFechaHasta = new GregorianCalendar(
	        		Integer.parseInt(anioHasta), 
	        		Integer.parseInt(mesHasta)-1,
	        		Integer.parseInt(diaHasta),23,59);
	        
	        
	        datosTarea.setFechaInicio(cal);
	        datosTarea.setFechaDesde(calFechaDesde);
    		datosTarea.setFechaHasta(calFechaHasta);
    		
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    			TareaEliminaNoDisponiblesVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaEliminarNoDisponibles(datosTarea, trabajo);
    			form.setTareaModificada(tareaRecuperada.getTrabajo());
    		}catch(Exception e)
    		{
    			log.error("Error: " + e);
            	throw new ValidatorException("{tareas.error}");
    		}
        }catch (ValidatorException ve)
        {
        	log.error("error "+ve);
        	throw ve;
        }
    	catch (Exception e)
        {
        	log.error("Error: " + e);
        	//throw new ValidatorException("{tareas.error}");
        }		
    }
    
    
    /**
     * Metodo que guarda los cambios que se han echo en la tarea informeFecha
     */
    public void modificarTareaInformeFecha(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ModificarTareaInformeFechaForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaInformesVO datosTarea = new TareaInformesVO(); // Datos a modificar
		
		FormularioInformeFechaIIAceptarFormImpl esteForm=(FormularioInformeFechaIIAceptarFormImpl)form;
		
		String dia = esteForm.getDia();
    	String mes = esteForm.getMes();
    	String anio = esteForm.getAnio();
    	String hora = esteForm.getHora();
    	String minutos = esteForm.getMinutos();
    	
		
    	try
        {    	

			if(esteForm.getPeriodicidad().equalsIgnoreCase("N"))
			{
				String anioDesde = esteForm.getAnioDesde();
		    	String mesDesde = esteForm.getMesDesde();
		    	String diaDesde = esteForm.getDiaDesde();
		    	String anioHasta = esteForm.getAnioHasta();
		    	String mesHasta = esteForm.getMesHasta();
		    	String diaHasta = esteForm.getDiaHasta();
			
	    		//Comprobamos que todos los campos de la fecha desde están rellenos
	    		if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
	    		//Comprobamos que todos los campos de la fecha hasta están rellenos
	    		if( anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		//Comprobamos que los campos de las fechas son numeros
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas tiene caracteres no numericos");
	    		try
	    		{
	    			new Integer (anioDesde).intValue(); 
					new Integer (mesDesde).intValue();
					new Integer (diaDesde).intValue();
	    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		try
	    		{
	    			new Integer (anioHasta).intValue(); 
					new Integer (mesHasta).intValue();
					new Integer (diaHasta).intValue();
	    			    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
	    		
				// Comprobamos si las fechas introducidas son correctas
				boolean fechaValidaDesde = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde , mesDesde, anioDesde, "yyyyMMdd");

				boolean fechaValidaHasta = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta, "yyyyMMdd");

				boolean comparacionFechaDesdeHasta = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde, anioHasta, mesHasta, diaHasta);

				if (!fechaValidaHasta && !fechaValidaDesde)
					throw new ValidatorException(
							"{tareas.fechaDesdeHastaIncorrecta}");

				if (!fechaValidaDesde)
					throw new ValidatorException("{tareas.fechaDesdeIncorrecta}");

				if (!fechaValidaHasta)
					throw new ValidatorException("{tareas.fechaHastaIncorrecta}");

				if (!comparacionFechaDesdeHasta)
					throw new ValidatorException("{tareas.fechaDesdeMasQueHasta}");
					
				Calendar calFechaDesde = Calendar.getInstance(tz);
				calFechaDesde = new GregorianCalendar(
	        		Integer.parseInt(anioDesde), 
	        		Integer.parseInt(mesDesde)-1,
	        		Integer.parseInt(diaDesde),0,1);
	        		
				Calendar calFechaHasta = Calendar.getInstance(tz);
				calFechaHasta = new GregorianCalendar(
	        		Integer.parseInt(anioHasta), 
	        		Integer.parseInt(mesHasta)-1,
	        		Integer.parseInt(diaHasta),23,59);
					
				datosTarea.setFechaDesde(calFechaDesde);
				datosTarea.setFechaHasta(calFechaHasta);
						
			}
			else
			{
				if(log.isDebugEnabled())log.debug("es periodica");
			}
				
			try
    		{
			
    		trabajo.setTrabajo(esteForm.getTrabajo());
    		trabajo.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		
    		datosTarea.setTrabajo(esteForm.getTrabajo());
    		datosTarea.setGrupoTrabajo(esteForm.getGrupoTrabajo());
    		datosTarea.setTrigger(esteForm.getTrigger());
    		datosTarea.setGrupoTrigger(esteForm.getGrupoTrigger());
    		datosTarea.setPeriodicidad(esteForm.getPeriodicidad());
    		datosTarea.setFormato(esteForm.getFormato());
    		datosTarea.setInforme(esteForm.getInforme());
    		datosTarea.setMsgInforme(esteForm.getMsgInforme());
    		datosTarea.setMsgNoInforme(esteForm.getMsgNoInforme());
    		datosTarea.setMsgDescripcionTrabajo(esteForm.getMsgDescTrabajo());
			
    		
	        Calendar cal = Calendar.getInstance(tz);    		
	        cal = new GregorianCalendar(
	        		Integer.parseInt(anio), 
	        		Integer.parseInt(mes)-1,
	        		Integer.parseInt(dia),
	        		Integer.parseInt(hora), 
	        		Integer.parseInt(minutos));
	        
	        datosTarea.setFechaInicio(cal);
    		
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaInformes(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
    		
    		
    		}catch(Exception e)
    		{
    			log.error("Error: " + e);
            	throw new ValidatorException("{tareas.error}");
    		}
    		
        }
    	catch (ValidatorException ve)
        {
        	log.error("error "+ve);
        	throw ve;
        }
    	
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	//throw new ValidatorException("{tareas.error}");
        }
        		
    }

    //TODO Seguir con limpieza
    /**
     * Metodo que guarda los cambios que se han echo en la tarea informeFechaRango
     */
    public void modificarTareaInformeFechaRango(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ModificarTareaInformeFechaRangoForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	String dia = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getDia();
    	String mes = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getMes();
    	String anio = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getAnio();
    	String hora = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getHora();
    	String minutos = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getMinutos();
    	
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaInformesVO datosTarea = new TareaInformesVO(); // Datos a modificar
				
    	try
        {  

			if(((FormularioInformeFechaRangoIIAceptarFormImpl)form).getPeriodicidad().equalsIgnoreCase("N"))
    		{
				String anioDesde = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getAnioDesde();
		    	String mesDesde = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getMesDesde();
		    	String diaDesde = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getDiaDesde();
		    	String anioHasta = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getAnioHasta();
		    	String mesHasta = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getMesHasta();
		    	String diaHasta = ((FormularioInformeFechaRangoIIAceptarFormImpl)form).getDiaHasta();
			
	    		//Comprobamos que todos los campos de la fecha de inicio están rellenos
	    		if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
	    		//Comprobamos que todos los campos de la fecha fin están rellenos
	    		if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		
	    		//Comprobamos que los campos de las fechas son numeros
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas tiene caracteres no numericos");
	    		try
	    		{
	    			new Integer(anioDesde).intValue();
	    			new Integer(mesDesde).intValue(); 
	    			new Integer(diaDesde).intValue();
	    			
	    			    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		try
	    		{
	    			new Integer(anioHasta).intValue();
	    			new Integer(mesHasta).intValue(); 
	    			new Integer(diaHasta).intValue();
	    			    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
	    		
				// Comprobamos si las fechas introducidas son correctas
				boolean fechaValidaDesde = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde,"yyyyMMdd");

				boolean fechaValidaHasta = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta,"yyyyMMdd");

				boolean comparacionFechaDesdeHasta = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde, anioHasta, mesHasta, diaHasta);

				if (!fechaValidaHasta && !fechaValidaDesde)
					throw new ValidatorException(
							"{tareas.fechaDesdeHastaIncorrecta}");

				if (!fechaValidaDesde)
					throw new ValidatorException("{tareas.fechaDesdeIncorrecta}");

				if (!fechaValidaHasta)
					throw new ValidatorException("{tareas.fechaHastaIncorrecta}");

				if (!comparacionFechaDesdeHasta)
					throw new ValidatorException("{tareas.fechaDesdeMasQueHasta}");
				
				Calendar calFechaDesde = Calendar.getInstance(tz);
				calFechaDesde = new GregorianCalendar(
	        		new Integer (anioDesde).intValue(), 
	        		new Integer (mesDesde).intValue() -1,
	        		new Integer (diaDesde).intValue(),0,1);
	        		
				Calendar calFechaHasta = Calendar.getInstance(tz);
				calFechaHasta = new GregorianCalendar(
	        		new Integer (anioHasta).intValue(), 
	        		new Integer (mesHasta).intValue() -1,
	        		new Integer (diaHasta).intValue(),23,59);
				
				datosTarea.setFechaDesde(calFechaDesde);
				datosTarea.setFechaHasta(calFechaHasta);

				}
				else
				{
					if(log.isDebugEnabled())log.debug("es periodica");
				}
				
			try
			{
			
    		trabajo.setTrabajo(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getTrabajo());
    		trabajo.setGrupoTrabajo(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getGrupoTrabajo());
    		
    		datosTarea.setTrabajo(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getTrabajo());
    		datosTarea.setGrupoTrabajo(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getGrupoTrabajo());
    		datosTarea.setTrigger(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getTrigger());
    		datosTarea.setGrupoTrigger(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getGrupoTrigger());
    		datosTarea.setPeriodicidad(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getPeriodicidad());
    		datosTarea.setFormato(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getFormato());
    		datosTarea.setRango(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getRango());
    		datosTarea.setInforme(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getInforme());
    		datosTarea.setMsgInforme(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getMsgInforme());
    		datosTarea.setMsgNoInforme(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getMsgNoInforme());
    		datosTarea.setMsgDescripcionTrabajo(((FormularioInformeFechaRangoIIAceptarFormImpl) form).getMsgDescTrabajo());
			
    		
	        Calendar cal = Calendar.getInstance(tz);    		
	        cal = new GregorianCalendar(
	        		new Integer (anio).intValue(), 
	        		new Integer (mes).intValue() -1,
	        		new Integer (dia).intValue() ,
	        		new Integer (hora).intValue(), 
	        		new Integer (minutos).intValue());
	        
	        datosTarea.setFechaInicio(cal);
    		
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    	
    		TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaInformes(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());

    		}catch(Exception e)
    		{
    			log.error("Error: " + e);
            	throw new ValidatorException("{tareas.error}");
    		}
        }
    	catch (ValidatorException ve)
        {
        	log.error("error "+ve);
        	throw ve;
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	//throw new ValidatorException("{tareas.error}");
        }
    }

    
    /**
     * Metodo que guarda los cambios que se han echo en la tarea informeFechaUsuario
     */
    public void modificarTareaInformeFechaUsuario(org.apache.struts.action.ActionMapping mapping, 
    		es.pode.administracion.presentacion.planificador.modificarTarea.ModificarTareaInformeFechaUsuarioForm form, 
    		javax.servlet.http.HttpServletRequest request, 
    		javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaInformesVO datosTarea = new TareaInformesVO(); // Datos a modificar
				
		String dia = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getDia();
    	String mes = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getMes();
    	String anio = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getAnio();
    	String hora = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getHora();
    	String minutos = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getMinutos();
    	
		
    	try
        {    				
		
			if(((FormularioInformeFechaRangoIIAceptarFormImpl)form).getPeriodicidad().equalsIgnoreCase("N"))
			{
				String anioDesde = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getAnioDesde();
		    	String mesDesde = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getMesDesde();
		    	String diaDesde = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getDiaDesde();
		    	String anioHasta = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getAnioHasta();
		    	String mesHasta = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getMesHasta();
		    	String diaHasta = ((FormularioInformeFechaUsuarioIIAceptarFormImpl)form).getDiaHasta();
			
	    		//Comprobamos que todos los campos de la fecha de inicio están rellenos
	    		if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
	    		//Comprobamos que todos los campos de la fecha fin están rellenos
	    		if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
	    			
	    			throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		
	    		//Comprobamos que los campos de las fechas son numeros
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas tiene caracteres no numericos");
	    		try
	    		{
	    			new Integer(anioDesde).intValue(); 
	    			new Integer(mesDesde).intValue(); 
	    			new Integer(diaDesde).intValue();
	    		
	    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		try
	    		{
	    			new Integer(anioHasta).intValue(); 
	    			new Integer(mesHasta).intValue(); 
	    			new Integer(diaHasta).intValue();
	    			
	    		 	    		 	
	    		}catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
	    		
				// Comprobamos si las fechas introducidas son correctas
				boolean fechaValidaDesde = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde,"yyyyMMdd");

				boolean fechaValidaHasta = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta,"yyyyMMdd");

				boolean comparacionFechaDesdeHasta = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde, anioHasta, mesHasta, diaHasta);

				if (!fechaValidaHasta && !fechaValidaDesde)
					throw new ValidatorException(
							"{tareas.fechaDesdeHastaIncorrecta}");

				if (!fechaValidaDesde)
					throw new ValidatorException("{tareas.fechaDesdeIncorrecta}");

				if (!fechaValidaHasta)
					throw new ValidatorException("{tareas.fechaHastaIncorrecta}");

				if (!comparacionFechaDesdeHasta)
					throw new ValidatorException("{tareas.fechaDesdeMasQueHasta}");
				
				Calendar calFechaDesde = Calendar.getInstance(tz);
				calFechaDesde = new GregorianCalendar(
	        		new Integer (anioDesde).intValue(), 
	        		new Integer (mesDesde).intValue() -1,
	        		new Integer (diaDesde).intValue(),0,1);
	        		
				Calendar calFechaHasta = Calendar.getInstance(tz);
				calFechaHasta = new GregorianCalendar(
	        		new Integer (anioHasta).intValue(), 
	        		new Integer (mesHasta).intValue() -1,
	        		new Integer (diaHasta).intValue(),23,59);
				
				datosTarea.setFechaDesde(calFechaDesde);
				datosTarea.setFechaHasta(calFechaHasta);
				
				}
				else
				{
					if(log.isDebugEnabled())log.debug("es periodica");
				}

			try
			{
			
    		trabajo.setTrabajo(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getTrabajo());
    		trabajo.setGrupoTrabajo(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getGrupoTrabajo());
    		
    		datosTarea.setTrabajo(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getTrabajo());
    		datosTarea.setGrupoTrabajo(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getGrupoTrabajo());
    		datosTarea.setTrigger(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getTrigger());
    		datosTarea.setGrupoTrigger(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getGrupoTrigger());
    		datosTarea.setPeriodicidad(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getPeriodicidad());
    		datosTarea.setFormato(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getFormato());
    		datosTarea.setUsuarioInforme(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getUsuario());
    		datosTarea.setInforme(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getInforme());
    		datosTarea.setMsgInforme(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getMsgInforme());
    		datosTarea.setMsgNoInforme(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getMsgNoInforme());
    		datosTarea.setMsgDescripcionTrabajo(((FormularioInformeFechaUsuarioIIAceptarFormImpl) form).getMsgDescTrabajo());
			
    		
	        Calendar cal = Calendar.getInstance(tz);    		
	        cal = new GregorianCalendar(
	        		new Integer (anio).intValue(), 
	        		new Integer (mes).intValue() -1,
	        		new Integer (dia).intValue() ,
	        		new Integer (hora).intValue(), 
	        		new Integer (minutos).intValue());
	        
	        
	        datosTarea.setFechaInicio(cal);
    		
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaInformes(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());

    		
			}catch(Exception e)
			{
				log.error("se produce un error "+e);
				throw new ValidatorException("{tareas.error}");
			}
        }
    	catch (ValidatorException ve)
        {
        	log.error("error "+ve);
        	throw ve;
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	//throw new ValidatorException("{tareas.error}");
        }
    }
    
    /**
     * Metodo que modifica la tarea de informes Federados con los nuevos datos
     */
    public void modificarTareaInformeFederado(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.modificarTarea.ModificarTareaInformeFederadoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
    {
    	if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	String dia = ((FormularioInformeFederadoIIAceptarFormImpl)form).getDia();
    	String mes = ((FormularioInformeFederadoIIAceptarFormImpl)form).getMes();
    	String anio = ((FormularioInformeFederadoIIAceptarFormImpl)form).getAnio();
    	String hora = ((FormularioInformeFederadoIIAceptarFormImpl)form).getHora();
    	String minutos = ((FormularioInformeFederadoIIAceptarFormImpl)form).getMinutos();
    	
    	
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaInformesVO datosTarea = new TareaInformesVO(); // Datos a modificar
			

				
		try
		{
		
		trabajo.setTrabajo(((FormularioInformeFederadoIIAceptarFormImpl) form).getTrabajo());
		trabajo.setGrupoTrabajo(((FormularioInformeFederadoIIAceptarFormImpl) form).getGrupoTrabajo());
		
		datosTarea.setTrabajo(((FormularioInformeFederadoIIAceptarFormImpl) form).getTrabajo());
		datosTarea.setGrupoTrabajo(((FormularioInformeFederadoIIAceptarFormImpl) form).getGrupoTrabajo());
		datosTarea.setTrigger(((FormularioInformeFederadoIIAceptarFormImpl) form).getTrigger());
		datosTarea.setGrupoTrigger(((FormularioInformeFederadoIIAceptarFormImpl) form).getGrupoTrigger());
		datosTarea.setPeriodicidad(((FormularioInformeFederadoIIAceptarFormImpl) form).getPeriodicidad());
		datosTarea.setFormato(((FormularioInformeFederadoIIAceptarFormImpl) form).getFormato());
		datosTarea.setInforme(((FormularioInformeFederadoIIAceptarFormImpl) form).getInforme());
		datosTarea.setMsgInforme(((FormularioInformeFederadoIIAceptarFormImpl) form).getMsgInforme());
		datosTarea.setMsgNoInforme(((FormularioInformeFederadoIIAceptarFormImpl) form).getMsgNoInforme());
		datosTarea.setMsgDescripcionTrabajo(((FormularioInformeFederadoIIAceptarFormImpl) form).getMsgDescTrabajo());
		
		
        Calendar cal = Calendar.getInstance(tz);    		
        cal = new GregorianCalendar(
        		new Integer (anio).intValue(), 
        		new Integer (mes).intValue() -1,
        		new Integer (dia).intValue() ,
        		new Integer (hora).intValue(), 
        		new Integer (minutos).intValue());
        
        datosTarea.setFechaInicio(cal);
        
        
		
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
	
		TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaInformesFederado(datosTarea, trabajo);
		form.setTareaModificada(tareaRecuperada.getTrabajo());

		}catch(Exception e)
		{
			log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
		}
        
        
    }




    /**
     * Metodo que guarda los cambios que se han hecho en la tarea del informe catalogo
     */
	public void modificarTareaInformeCatalogo(ActionMapping mapping, ModificarTareaInformeCatalogoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaInformeCatalogoVO datosTarea = new TareaInformeCatalogoVO(); // Datos a modificar
		
		String anoActualizacion = ((FormularioInformeCatalogoIIAceptarFormImpl)form).getAnoActualizacion();
		String mesActualizacion = ((FormularioInformeCatalogoIIAceptarFormImpl)form).getMesActualizacion();
		String diaActualizacion = ((FormularioInformeCatalogoIIAceptarFormImpl)form).getDiaActualizacion();
		try{
	//		Validamos que la fecha introducida sea correcta 
			try
			{
				if (diaActualizacion==null || diaActualizacion.length()==0 || 
						mesActualizacion==null || mesActualizacion.length()==0 ||
						anoActualizacion==null || anoActualizacion.length()==0){
					if (diaActualizacion.length()>0 ||	mesActualizacion.length()>0 ||	anoActualizacion.length()>0){
						new Integer (diaActualizacion).intValue();
						new Integer(mesActualizacion).intValue();
						new Integer(anoActualizacion).intValue();
					}
				}
			 	if (diaActualizacion!=null && diaActualizacion.length()>0){
			 		new Integer (diaActualizacion).intValue();
			 	}
			 	if (mesActualizacion!=null && mesActualizacion.length()>0){
			 		new Integer(mesActualizacion).intValue();
			 	}
			 	if (anoActualizacion!=null && anoActualizacion.length()>0){
			 		new Integer(anoActualizacion).intValue();
			 	}
			 	
			}catch(Exception e)
			{
				log.error("Alguno de los campos de la fecha no son números");
				throw new ValidatorException("{tareas.fechaIncorrecta}");
			}
	
			try 
			{
				if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS");
				SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
				format.setLenient(false);
				
				
				if (diaActualizacion!=null && diaActualizacion.length()>0 && mesActualizacion!=null && mesActualizacion.length()>0 && anoActualizacion!=null && anoActualizacion.length()>0){
					boolean fechaValida = Utiles.validacionFechaDDMMAAAAHHMM(diaActualizacion, mesActualizacion, anoActualizacion, "yyyyMMdd");
					
					if(log.isDebugEnabled())log.debug("fechaValida es "+fechaValida);
					
					if (!fechaValida)
						throw new ValidatorException("{tareas.fechaIncorrecta}");
					
					if ((new Integer(mesActualizacion).intValue() < 10)&& (mesActualizacion.length() == 1))
						mesActualizacion = "0" + mesActualizacion;
					
			         if ((new Integer(diaActualizacion).intValue() < 10)&&(diaActualizacion.length() == 1))
			        	 diaActualizacion = "0" + diaActualizacion;
		         
		
		        log.debug("dia "+diaActualizacion+" mes "+mesActualizacion+" anio "+anoActualizacion);
	
				}	
			}  catch (ValidatorException e2) 
			{
				log.error("e2 "+e2);
				throw e2;
			
			}
	
	    	try
	        {    				
	    		trabajo.setTrabajo(((FormularioInformeCatalogoIIAceptarFormImpl) form).getTrabajo());
	    		trabajo.setGrupoTrabajo(((FormularioInformeCatalogoIIAceptarFormImpl) form).getGrupoTrabajo());	
	    		datosTarea.setTrabajo(((FormularioInformeCatalogoIIAceptarFormImpl) form).getTrabajo());
	    		datosTarea.setGrupoTrabajo(((FormularioInformeCatalogoIIAceptarFormImpl) form).getGrupoTrabajo());
	    		datosTarea.setTrigger(((FormularioInformeCatalogoIIAceptarFormImpl) form).getTrigger());
	    		datosTarea.setGrupoTrigger(((FormularioInformeCatalogoIIAceptarFormImpl) form).getGrupoTrigger());
	    		datosTarea.setPeriodicidad(((FormularioInformeCatalogoIIAceptarFormImpl) form).getPeriodicidad());
	    		datosTarea.setIdioma(((FormularioInformeCatalogoIIAceptarFormImpl) form).getRepositorio());
	    		log.debug("Insertamos en datosTarea el idioma "+datosTarea.getIdioma());
		        Calendar cal = Calendar.getInstance(tz);
		        cal = new GregorianCalendar(
		        		new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getAnio()).intValue(),
		        		new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getMes()).intValue() -1,
		        		new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getDia()).intValue(),
		        		new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getHora()).intValue(),
		        		new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getMinutos()).intValue());
		        	        
		        datosTarea.setFechaInicio(cal);
	    		datosTarea.setMsgDescripcionTrabajo(((FormularioInformeCatalogoIIAceptarFormImpl) form).getMsgDescTrabajo());
	    		datosTarea.setMsgNoInforme(((FormularioInformeCatalogoIIAceptarFormImpl) form).getMsgNoInforme());
	    		datosTarea.setMsgInforme(((FormularioInformeCatalogoIIAceptarFormImpl) form).getMsgInforme());
		        datosTarea.setFormato(((FormularioInformeCatalogoIIAceptarFormImpl) form).getFormato());
		        datosTarea.setInforme(((FormularioInformeCatalogoIIAceptarFormImpl) form).getInforme());
	    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
	    		datosTarea.setIdioma(((FormularioInformeCatalogoIIAceptarFormImpl) form).getRepositorio());
	    		
	    		Calendar calActualizacion=null;
	    		if ((((FormularioInformeCatalogoIIAceptarFormImpl)form).getAnoActualizacion())!=null && (((FormularioInformeCatalogoIIAceptarFormImpl)form).getAnoActualizacion()).length()>0
	    				&& (((FormularioInformeCatalogoIIAceptarFormImpl)form).getMesActualizacion())!=null && (((FormularioInformeCatalogoIIAceptarFormImpl)form).getMesActualizacion()).length()>0
	    				&& (((FormularioInformeCatalogoIIAceptarFormImpl)form).getDiaActualizacion())!=null && (((FormularioInformeCatalogoIIAceptarFormImpl)form).getDiaActualizacion()).length()>0){
	    			calActualizacion = Calendar.getInstance(tz);
	    			calActualizacion = new GregorianCalendar(
	    					new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getAnoActualizacion()).intValue(),
	    					new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getMesActualizacion()).intValue() -1,
	    					new Integer(((FormularioInformeCatalogoIIAceptarFormImpl)form).getDiaActualizacion()).intValue(),10,10);
	    		}
	    		
	 	        		
	    		 
	    		datosTarea.setFechaActualizacion(calActualizacion);
	    		log.debug("Idioma del formulario "+datosTarea.getIdioma());
	    		TareaInformeCatalogoVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaInformesCatalogo(datosTarea, trabajo);
	    		log.debug("La tarea recuperada tiene el idioma: "+ tareaRecuperada.getIdioma());
	    		form.setTareaModificada(tareaRecuperada.getTrabajo());
	        }
	        catch (Exception e)
	        {
	        	log.error("Error: " + e);
	        	throw new ValidatorException("{tareas.error}");
	        }	
		}
        catch (ValidatorException ve)
        {
        	log.error("error "+ve);
        	throw ve;
        }
    	
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	//throw new ValidatorException("{tareas.error}");
        }
		
	}




	@Override
	public void obtenerRegenerarImagenes(ActionMapping mapping, ObtenerRegenerarImagenesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaRegenerarImagenesVO tareaRegenerar = this.getSrvPlanificadorService().obtenerTareaRegenerarImagenes(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaRegenerar.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(tareaRegenerar.getPeriodicidad());
    		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(tareaRegenerar.getTipoTarea());    		
    		((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(tareaRegenerar.getFechaInicio().get(Calendar.YEAR)));
    		((ObtenerTCargaODEsFormImpl) form).setMes((Integer.toString(tareaRegenerar.getFechaInicio().get(Calendar.MONTH) + 1)));
    		((ObtenerTCargaODEsFormImpl) form).setDia((Integer.toString(tareaRegenerar.getFechaInicio().get(Calendar.DAY_OF_MONTH))));
    		((ObtenerTCargaODEsFormImpl) form).setHora((Integer.toString(tareaRegenerar.getFechaInicio().get(Calendar.HOUR_OF_DAY))));
    		((ObtenerTCargaODEsFormImpl) form).setMinutos((Integer.toString(tareaRegenerar.getFechaInicio().get(Calendar.MINUTE))));
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}

	@Override
	public void validarRegenerarImagenes(ActionMapping mapping, ValidarRegenerarImagenesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	FormularioRegenerarImagenesAceptarFormImpl formRegenerar = (FormularioRegenerarImagenesAceptarFormImpl) form;
		//Primero validamos
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formRegenerar.getAnio());
		String periodicidad = formRegenerar.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formRegenerar.getDia();
	 	String mes = formRegenerar.getMes();
	 	String anio = formRegenerar.getAnio();
	 	String hora = formRegenerar.getHora();
	 	String minutos = formRegenerar.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}			
		
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	log.debug("Entramos en la modificacion de la tarea");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaRegenerarImagenesVO datosTarea = new TareaRegenerarImagenesVO(); // Datos a modificar
		
    	try
        {    				
    		trabajo.setTrabajo((formRegenerar).getTrabajo());
    		trabajo.setGrupoTrabajo((formRegenerar).getGrupoTrabajo());	
    		datosTarea.setTrabajo((formRegenerar).getTrabajo());
    		datosTarea.setGrupoTrabajo((formRegenerar).getGrupoTrabajo());
    		datosTarea.setTrigger((formRegenerar).getTrigger());
    		datosTarea.setGrupoTrigger((formRegenerar).getGrupoTrigger());
    		datosTarea.setPeriodicidad((formRegenerar).getPeriodicidad());
    		
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formRegenerar).getAnio()).intValue(),
	        		new Integer((formRegenerar).getMes()).intValue() -1,
	        		new Integer((formRegenerar).getDia()).intValue(),
	        		new Integer((formRegenerar).getHora()).intValue(),
	        		new Integer((formRegenerar).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
//    		datosTarea.setMsgReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgReindexado());
//    		datosTarea.setMsgNoReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgNoReindexado());
//    		datosTarea.setMsgDescripcionTrabajo(((FormularioReindexadoIIAceptarFormImpl) form).getMsgDescReindexado());
//	        
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaRegenerarImagenesVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaRegenerarImagenes(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}




	/**
     * Metodo que obtiene los datos de la tarea de generarSitemaps que se selecciona
     */
	public void obtenerGenerarSitemaps(ActionMapping mapping, ObtenerGenerarSitemapsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaGenerarSitemapsVO tareaSitemaps = this.getSrvPlanificadorService().obtenerTareaGenerarSitemaps(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaSitemaps.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(tareaSitemaps.getPeriodicidad());
    		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(tareaSitemaps.getTipoTarea());    		
    		((ObtenerTCargaODEsFormImpl) form).setAnio((Integer.toString(tareaSitemaps.getFechaInicio().get(Calendar.YEAR))));
    		((ObtenerTCargaODEsFormImpl) form).setMes((Integer.toString(tareaSitemaps.getFechaInicio().get(Calendar.MONTH) + 1)));
    		((ObtenerTCargaODEsFormImpl) form).setDia((Integer.toString(tareaSitemaps.getFechaInicio().get(Calendar.DAY_OF_MONTH))));
    		((ObtenerTCargaODEsFormImpl) form).setHora((Integer.toString(tareaSitemaps.getFechaInicio().get(Calendar.HOUR_OF_DAY))));
    		((ObtenerTCargaODEsFormImpl) form).setMinutos((Integer.toString(tareaSitemaps.getFechaInicio().get(Calendar.MINUTE))));
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}




    /**
     * Metodo que obtiene los datos de la tarea de generarRSS que se selecciona
     */
	public void obtenerLanzarRSS(ActionMapping mapping, ObtenerLanzarRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaLanzarRSSVO tareaRSS = this.getSrvPlanificadorService().obtenerTareaLanzarRSS(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		tareaRSS.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(tareaRSS.getPeriodicidad());
    		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(tareaRSS.getTipoTarea());    		
    		((ObtenerTCargaODEsFormImpl) form).setAnio((Integer.toString(tareaRSS.getFechaInicio().get(Calendar.YEAR))));
    		((ObtenerTCargaODEsFormImpl) form).setMes((Integer.toString(tareaRSS.getFechaInicio().get(Calendar.MONTH) + 1)));
    		((ObtenerTCargaODEsFormImpl) form).setDia((Integer.toString(tareaRSS.getFechaInicio().get(Calendar.DAY_OF_MONTH))));
    		((ObtenerTCargaODEsFormImpl) form).setHora((Integer.toString(tareaRSS.getFechaInicio().get(Calendar.HOUR_OF_DAY))));
    		((ObtenerTCargaODEsFormImpl) form).setMinutos((Integer.toString(tareaRSS.getFechaInicio().get(Calendar.MINUTE))));
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}




	/**
	 * Valida el formulario cuando se genera una tarea del tipo generarSitemaps
	 */
	public void validarGenerarSitemaps(ActionMapping mapping, ValidarGenerarSitemapsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormularioGenerarSitemapsAceptarFormImpl formGenerarSitemaps = (FormularioGenerarSitemapsAceptarFormImpl) form;
		//Primero validamos
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formGenerarSitemaps.getAnio());
		String periodicidad = formGenerarSitemaps.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formGenerarSitemaps.getDia();
	 	String mes = formGenerarSitemaps.getMes();
	 	String anio = formGenerarSitemaps.getAnio();
	 	String hora = formGenerarSitemaps.getHora();
	 	String minutos = formGenerarSitemaps.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}			
		
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	log.debug("Entramos en la modificacion de la tarea");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaGenerarSitemapsVO datosTarea = new TareaGenerarSitemapsVO(); // Datos a modificar
		
    	try
        {    				
    		trabajo.setTrabajo((formGenerarSitemaps).getTrabajo());
    		trabajo.setGrupoTrabajo((formGenerarSitemaps).getGrupoTrabajo());	
    		datosTarea.setTrabajo((formGenerarSitemaps).getTrabajo());
    		datosTarea.setGrupoTrabajo((formGenerarSitemaps).getGrupoTrabajo());
    		datosTarea.setTrigger((formGenerarSitemaps).getTrigger());
    		datosTarea.setGrupoTrigger((formGenerarSitemaps).getGrupoTrigger());
    		datosTarea.setPeriodicidad((formGenerarSitemaps).getPeriodicidad());
    		
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formGenerarSitemaps).getAnio()).intValue(),
	        		new Integer((formGenerarSitemaps).getMes()).intValue() -1,
	        		new Integer((formGenerarSitemaps).getDia()).intValue(),
	        		new Integer((formGenerarSitemaps).getHora()).intValue(),
	        		new Integer((formGenerarSitemaps).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaGenerarSitemapsVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaGenerarSitemaps(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}




	/**
	 * Valida el formulario cuando se genera una tarea del tipo generarRSS
	 */
	public void validarLanzarRSS(ActionMapping mapping, ValidarLanzarRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormularioLanzarRSSAceptarFormImpl formGenerarRSS = (FormularioLanzarRSSAceptarFormImpl) form;
		//Primero validamos
//	 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formGenerarRSS.getAnio());
		String periodicidad = formGenerarRSS.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formGenerarRSS.getDia();
	 	String mes = formGenerarRSS.getMes();
	 	String anio = formGenerarRSS.getAnio();
	 	String hora = formGenerarRSS.getHora();
	 	String minutos = formGenerarRSS.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}	
		
		
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	log.debug("Entramos en la modificacion de la tarea");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaLanzarRSSVO datosTarea = new TareaLanzarRSSVO(); // Datos a modificar
		
    	try
        {    				
    		trabajo.setTrabajo((formGenerarRSS).getTrabajo());
    		trabajo.setGrupoTrabajo((formGenerarRSS).getGrupoTrabajo());	
    		datosTarea.setTrabajo((formGenerarRSS).getTrabajo());
    		datosTarea.setGrupoTrabajo((formGenerarRSS).getGrupoTrabajo());
    		datosTarea.setTrigger((formGenerarRSS).getTrigger());
    		datosTarea.setGrupoTrigger((formGenerarRSS).getGrupoTrigger());
    		datosTarea.setPeriodicidad((formGenerarRSS).getPeriodicidad());
    		
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formGenerarRSS).getAnio()).intValue(),
	        		new Integer((formGenerarRSS).getMes()).intValue() -1,
	        		new Integer((formGenerarRSS).getDia()).intValue(),
	        		new Integer((formGenerarRSS).getHora()).intValue(),
	        		new Integer((formGenerarRSS).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
    		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
    		
    		TareaLanzarRSSVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaLanzarRSS(datosTarea, trabajo);
    		form.setTareaModificada(tareaRecuperada.getTrabajo());
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
	}

	public void obtenerTareaModificarDespublicados(ActionMapping mapping, ObtenerTareaModificarDespublicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null) 
    		tz = Utiles.asignarZonaHoraria();
    	
    	TareaVO tarea = new TareaVO();
    	
    	try
        {    	
       		
    		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
    		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
    		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
    		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
    		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
    		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
    		
    		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
    		TareaDespublicarODEsVO despublicarODEsVO = this.getSrvPlanificadorService().obtenerTareaDespublicarODEs(tarea);
    		
    		//asignamos a las fechas la franja horaria correspondiente
    		despublicarODEsVO.getFechaInicio().setTimeZone(tz);
    		
    		//rellenamos los datos especificos de este tipo de tarea
    		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(despublicarODEsVO.getPeriodicidad());
    		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(despublicarODEsVO.getTipoTarea());    		
    		((ObtenerTCargaODEsFormImpl) form).setAnio((Integer.toString(despublicarODEsVO.getFechaInicio().get(Calendar.YEAR))));
    		((ObtenerTCargaODEsFormImpl) form).setMes((Integer.toString(despublicarODEsVO.getFechaInicio().get(Calendar.MONTH) + 1)));
    		((ObtenerTCargaODEsFormImpl) form).setDia((Integer.toString(despublicarODEsVO.getFechaInicio().get(Calendar.DAY_OF_MONTH))));
    		((ObtenerTCargaODEsFormImpl) form).setHora((Integer.toString(despublicarODEsVO.getFechaInicio().get(Calendar.HOUR_OF_DAY))));
    		((ObtenerTCargaODEsFormImpl) form).setMinutos((Integer.toString(despublicarODEsVO.getFechaInicio().get(Calendar.MINUTE))));
        }
        catch (Exception e)
        {
        	log.error("Error: " + e);
        	throw new ValidatorException("{tareas.error}");
        }	
		
	}


	/**
	 * Valida el formulario cuando se genera una tarea del tipo despublicar
	 */
	public void validarDespublicados(ActionMapping mapping, ValidarDespublicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormularioDespublicadosAceptarFormImpl aceptarFormImpl = (FormularioDespublicadosAceptarFormImpl) form;
		
		//Primero validamos
	//	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(aceptarFormImpl.getAnio());
		String periodicidad = aceptarFormImpl.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		String dia = aceptarFormImpl.getDia();
	 	String mes = aceptarFormImpl.getMes();
	 	String anio = aceptarFormImpl.getAnio();
	 	String hora = aceptarFormImpl.getHora();
	 	String minutos = aceptarFormImpl.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de despublicar");

		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}		
	}
	
	
private String[] obtenerIdentificadoresDesdeExcel(InputStream inputStream) throws Exception{
		
		String[] identificadores=null;
		ArrayList<String> listaIdentificadores=new ArrayList<String>();
        try 

        { 
            //Se abre el fichero Excel 
            POIFSFileSystem fs =  new POIFSFileSystem(inputStream); 
            //Se obtiene el libro Excel 
            HSSFWorkbook wb = new HSSFWorkbook(fs); 
            //Se obtiene la primera hoja 
            HSSFSheet sheet = wb.getSheetAt(0); 
            //Se obtiene la primera fila de la hoja  y los identificadores que obtenermos los insertamos en un array
            Iterator iter = sheet.iterator();
            Boolean vacio=Boolean.FALSE;
           
				int k = 0;
				while (iter.hasNext() && !vacio)
				{
					HSSFRow row = sheet.getRow(k); 
					if(row!=null){
		                HSSFCell cell = row.getCell(0); 
		                if(cell!=null){
		                	if(log.isDebugEnabled())log.debug("String: " + cell.getStringCellValue()+" lo que le insertamos a la lista"); 
		                	listaIdentificadores.add(cell.getStringCellValue());
		                	
		                }else{
		                	vacio=Boolean.TRUE;
		                }
					}else{
						vacio=Boolean.TRUE;
					}
					
					k++;
				}
        } 
        catch(Exception ex) 
        { 
            log.error("Error al leer el fichero."+ex);
            throw new ValidatorException("{tareas.error}");
        } 
        if(log.isDebugEnabled())log.debug("Ejemplo Finalizado. La lista es "+listaIdentificadores); 
        identificadores=listaIdentificadores.toArray(new String[0]);
        return identificadores;

	}
    

	private void eliminarTemporal(String nombreExcel) throws Exception {

		try {
			File temporalExportacion = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_DESPUBLICADO) + nombreExcel);
			if (temporalExportacion.exists()) {
				UtilesFicheros.eliminar(temporalExportacion);
			}
		}

		catch (Exception e) {
			log.error("Error al eliminar el fichero." + e);
			throw e;
		}

	}

@Override
public void modificarTareaDespublicados(ActionMapping mapping, ModificarTareaDespublicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	boolean valida=true;
	FormularioDespublicadosIIAceptarFormImpl aceptarIIFormImpl = (FormularioDespublicadosIIAceptarFormImpl) form;
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	log.debug("Entramos en la modificacion de la tarea");
	TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
	TareaDespublicarODEsVO despublicarODEsVO = new TareaDespublicarODEsVO(); // Datos a modificar
	String[] ideValidos=null;
	try
    {    			
		FormFile archivo = ((FormularioDespublicadosIIAceptarFormImpl) form).getArchivo();
		String file = archivo.getFileName();
		//Validamos que el fichero sea Excel, extensión xls
		if (file!=null && file.length()>0){
    		if (!file.endsWith(".xls")){
    			log.error("El archivo debe de ser de tipo Excel");
    			throw new ValidatorException("{crear.tarea.ficheroNoValido}");
    		}
    		//Obtenemos los identificadores del Excel
    		log.info("Obtenemos los identificadores");
    		String[] identificadores=this.obtenerIdentificadoresDesdeExcel(archivo.getInputStream());
    		ArrayList<String> identificadoresValidos = new ArrayList<String>();
    		//Validamos el los identificadores
    		if (identificadores!=null){
	    		ValidadorMEC validadorMEC = new ValidadorMEC();
	    		for (int i=0; i<identificadores.length; i++){  	
	      			valida = validadorMEC.validar(identificadores[i]);
	      			if (valida==true){
	      				identificadoresValidos.add(identificadores[i]);
	      			}
	    		}
    		}
    		if (identificadoresValidos.size()==0){
    			log.error("Todos los identificadores del archivo no son  válidos");
    			throw new ValidatorException("{crear.tarea.identficadoresNoValidos}");
    		}
    		ideValidos=identificadoresValidos.toArray(new String[0]);
		}
		
//		TareaVO tarea = new TareaVO();
////		cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
//		tarea.setTrabajo(( form).getTrabajo());
//		tarea.setGrupoTrabajo(( form).getGrupoTrabajo());
//		tarea.setTrigger(( form).getTrigger());
//		tarea.setGrupoTrigger(( form).getGrupoTrigger());
//		tarea.setPeriodicidad(( form).getPeriodicidad());
//		
//		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
//		TareaDespublicarODEsVO obtenerDespublicados = this.getSrvPlanificadorService().obtenerTareaDespublicarODEs(tarea);
		
		trabajo.setTrabajo((aceptarIIFormImpl).getTrabajo());
		trabajo.setGrupoTrabajo((aceptarIIFormImpl).getGrupoTrabajo());	
		despublicarODEsVO.setTrabajo((aceptarIIFormImpl).getTrabajo());
		despublicarODEsVO.setGrupoTrabajo((aceptarIIFormImpl).getGrupoTrabajo());
		despublicarODEsVO.setTrigger((aceptarIIFormImpl).getTrigger());
		despublicarODEsVO.setGrupoTrigger((aceptarIIFormImpl).getGrupoTrigger());
		despublicarODEsVO.setPeriodicidad((aceptarIIFormImpl).getPeriodicidad());
		despublicarODEsVO.setDesdePortal(Boolean.FALSE);
//		TareaVO tarea = new TareaVO();
//////	cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
//		tarea.setTrabajo(despublicarODEsVO.getTrabajo());
//		tarea.setGrupoTrabajo(despublicarODEsVO.getGrupoTrabajo());
//		tarea.setTrigger(despublicarODEsVO.getTrigger());
//		tarea.setGrupoTrigger(despublicarODEsVO.getGrupoTrigger());
//		tarea.setPeriodicidad(despublicarODEsVO.getPeriodicidad());
////	
////	//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
//		TareaDespublicarODEsVO obtenerDespublicados = this.getSrvPlanificadorService().obtenerTareaDespublicarODEs(tarea);
		
		if (file!=null && file.length()>0){
			despublicarODEsVO.setIdentificadores(ideValidos);
		}
		
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		Integer.parseInt((aceptarIIFormImpl).getAnio()),
        		Integer.parseInt((aceptarIIFormImpl).getMes()) -1,
        		Integer.parseInt((aceptarIIFormImpl).getDia()),
        		Integer.parseInt((aceptarIIFormImpl).getHora()),
        		Integer.parseInt((aceptarIIFormImpl).getMinutos()));
        	        
        despublicarODEsVO.setFechaInicio(cal);
//		datosTarea.setMsgReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgReindexado());
//		datosTarea.setMsgNoReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgNoReindexado());
//		datosTarea.setMsgDescripcionTrabajo(((FormularioReindexadoIIAceptarFormImpl) form).getMsgDescReindexado());
//        
        despublicarODEsVO.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaDespublicarODEsVO despublicarODEsVO2 =this.getSrvPlanificadorService().modificarTareaDespublicarODEs(despublicarODEsVO, trabajo);
		if (despublicarODEsVO2.getIdentificadores()!=null){
			this.eliminarTemporal(despublicarODEsVO2.getTrabajo() +".xls");
		}
		form.setTareaModificada(despublicarODEsVO2.getTrabajo());
    }
	catch (ValidatorException e2) 
	{
		log.error("e2 "+e2);
		throw e2;
}
}




/**
 * Metodo que obtiene los datos de la tarea de generarOaiOre que se selecciona
 */
public void obtenerGenerarOaiOre(ActionMapping mapping, ObtenerGenerarOaiOreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	TareaVO tarea = new TareaVO();
	
	try
    {    	
   		
		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
		
		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
		TareaVO tareaOaiOre = this.getSrvPlanificadorService().obtenerTareaGenerarOaiOre(tarea);
		
		//asignamos a las fechas la franja horaria correspondiente
		tareaOaiOre.getFechaInicio().setTimeZone(tz);
		
		//rellenamos los datos especificos de este tipo de tarea
		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(tareaOaiOre.getPeriodicidad());
		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(tareaOaiOre.getTipoTarea());    		
		((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(tareaOaiOre.getFechaInicio().get(Calendar.YEAR)));
		((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(tareaOaiOre.getFechaInicio().get(Calendar.MONTH) + 1));
		((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(tareaOaiOre.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
		((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(tareaOaiOre.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
		((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(tareaOaiOre.getFechaInicio().get(Calendar.MINUTE)));
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
}




/**
 * Valida el formulario cuando se genera una tarea del tipo generarOaiOre
 */
public void validarGenerarOaiOre(ActionMapping mapping, ValidarGenerarOaiOreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	FormularioGenerarOaiOreAceptarFormImpl formGenerarOaiOre = (FormularioGenerarOaiOreAceptarFormImpl) form;
	//Primero validamos
// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(formGenerarOaiOre.getAnio());
	String periodicidad = form.getPeriodicidad();
	if (anioInt>2099 && !"N".equals(periodicidad)){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	
	String dia = formGenerarOaiOre.getDia();
 	String mes = formGenerarOaiOre.getMes();
 	String anio = formGenerarOaiOre.getAnio();
 	String hora = formGenerarOaiOre.getHora();
 	String minutos = formGenerarOaiOre.getMinutos();
 	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");
	
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}
		
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	try
    {
		log.debug("Entramos en la modificacion de la tarea");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaVO datosTarea = new TareaVO(); // Datos a modificar
		
		trabajo.setTrabajo((formGenerarOaiOre).getTrabajo());
		trabajo.setGrupoTrabajo((formGenerarOaiOre).getGrupoTrabajo());	
		datosTarea.setTrabajo((formGenerarOaiOre).getTrabajo());
		datosTarea.setGrupoTrabajo((formGenerarOaiOre).getGrupoTrabajo());
		datosTarea.setTrigger((formGenerarOaiOre).getTrigger());
		datosTarea.setGrupoTrigger((formGenerarOaiOre).getGrupoTrigger());
		datosTarea.setPeriodicidad((formGenerarOaiOre).getPeriodicidad());
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		new Integer((formGenerarOaiOre).getAnio()).intValue(),
        		new Integer((formGenerarOaiOre).getMes()).intValue() -1,
        		new Integer((formGenerarOaiOre).getDia()).intValue(),
        		new Integer((formGenerarOaiOre).getHora()).intValue(),
        		new Integer((formGenerarOaiOre).getMinutos()).intValue());
        	        
        datosTarea.setFechaInicio(cal);
//		datosTarea.setMsgReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgReindexado());
//		datosTarea.setMsgNoReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgNoReindexado());
//		datosTarea.setMsgDescripcionTrabajo(((FormularioReindexadoIIAceptarFormImpl) form).getMsgDescReindexado());
//        
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaGenerarOaiOre(datosTarea, trabajo);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
}




/**
 * Metodo que modifica la tarea de informes Federados de Nivel de Agregacion con los nuevos datos
 */
public void modificarTareaInformeFederadoNivelAgregacion(ActionMapping mapping, ModificarTareaInformeFederadoNivelAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	String dia = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getDia();
	String mes = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getMes();
	String anio = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getAnio();
	String hora = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getHora();
	String minutos = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getMinutos();
	
	String mesNivelAgregacion = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getMesInicioInforme();
	String anioNivelAgregacion = ((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl)form).getAnioInicioInforme();

	
	TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
	TareaInformesNivelAgregacionFederadaVO datosTarea = new TareaInformesNivelAgregacionFederadaVO(); // Datos a modificar
		
//	Primero validames las fechas
 	try{
		if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS DE NIVEL DE AGREGACION");
		
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		format.setLenient(false);
		
		
		Logger.getLogger(this.getClass()).debug("validamos la fecha");
		boolean fechaValida = Utiles.validacionFechaDDMMAAAAHHMM("25", mesNivelAgregacion, anioNivelAgregacion, "yyyyMMdd");
		
		
		if(log.isDebugEnabled())log.debug("fechaValida es "+fechaValida);
		
		
		if (!fechaValida)
			throw new ValidatorException("{tareas.fechaIncorrecta}");
		
		if ((new Integer(mesNivelAgregacion).intValue() < 10)&& (mesNivelAgregacion.length() == 1))
			mesNivelAgregacion = "0" + mesNivelAgregacion;
			
		
		   
		
		
		log.debug("dia "+ "25" +" mes "+mesNivelAgregacion+" anio "+anioNivelAgregacion);
		
		

}catch (ValidatorException e2) 
	{
		log.error("e2 "+e2);
		throw e2;
}
			
	try
	{
	
	trabajo.setTrabajo(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getTrabajo());
	trabajo.setGrupoTrabajo(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getGrupoTrabajo());
	
	datosTarea.setTrabajo(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getTrabajo());
	datosTarea.setGrupoTrabajo(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getGrupoTrabajo());
	datosTarea.setTrigger(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getTrigger());
	datosTarea.setGrupoTrigger(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getGrupoTrigger());
	datosTarea.setPeriodicidad(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getPeriodicidad());
	datosTarea.setFormato(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getFormato());
	datosTarea.setInforme(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getInforme());
	datosTarea.setMsgInforme(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getMsgInforme());
	datosTarea.setMsgNoInforme(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getMsgNoInforme());
	datosTarea.setMsgDescripcionTrabajo(((FormularioInformeFederadoNivelAgregacionIIAceptarFormImpl) form).getMsgDescTrabajo());
	
	
    Calendar cal = Calendar.getInstance(tz);    		
    cal = new GregorianCalendar(
    		new Integer (anio).intValue(), 
    		new Integer (mes).intValue() -1,
    		new Integer (dia).intValue() ,
    		new Integer (hora).intValue(), 
    		new Integer (minutos).intValue());
    
    datosTarea.setFechaInicio(cal);
    //Tranformo a calendar la fecha de nivel de agregación
    Calendar calNivelAgregacion = Calendar.getInstance(tz);    		
    calNivelAgregacion = new GregorianCalendar(
    		new Integer (anioNivelAgregacion).intValue(), 
    		new Integer (mesNivelAgregacion).intValue() -1,
    		1 ,
    		12,
    		12);
    
    datosTarea.setFechaNivelAgregacion(calNivelAgregacion);
	
	datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());

	TareaInformesNivelAgregacionFederadaVO tareaRecuperada = this.getSrvPlanificadorService().modificarTareaInformesFederadoNivelAgregacion(datosTarea, trabajo);
	form.setTareaModificada(tareaRecuperada.getTrabajo());

	}catch(Exception e)
	{
		log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
	}

}

/**
 * Metodo que obtiene los datos de la tarea de informe federado de nivel de agregacion que hay almacenados actualmente
 */
public void obtenerTareaInformeFederadoNivelAgregacion(ActionMapping mapping, ObtenerTareaInformeFederadoNivelAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	TareaInformesNivelAgregacionFederadaVO tarea = new TareaInformesNivelAgregacionFederadaVO();
	
	try
    {    	
   		
		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea
		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
		
		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
		TareaInformesNivelAgregacionFederadaVO tareaInformes = this.getSrvPlanificadorService().obtenerTareaInformeNivelAgregacion(tarea);
		
		//Si tiene periodicidad no se obtienen las fechas Desde y Hasta
		
		//Las fechas desde y hasta se las asignamos en el servicio
		
		//asignamos a las fechas la franja horaria correspondiente
		tareaInformes.getFechaInicio().setTimeZone(tz);
		
		//rellenamos los datos especificos de este tipo de tarea
		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(tareaInformes.getPeriodicidad());
		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(tareaInformes.getInforme());
		((ObtenerTCargaODEsFormImpl) form).setFormato(tareaInformes.getFormato());
		((ObtenerTCargaODEsFormImpl) form).setInforme(tareaInformes.getInforme());
		((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.YEAR)));
		((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.MONTH) + 1));
		((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
		((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
		((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(tareaInformes.getFechaInicio().get(Calendar.MINUTE)));
		((ObtenerTCargaODEsFormImpl) form).setMsgInforme(tareaInformes.getMsgInforme());
		((ObtenerTCargaODEsFormImpl) form).setMsgNoInforme(tareaInformes.getMsgNoInforme());
		((ObtenerTCargaODEsFormImpl) form).setMsgDescTrabajo(tareaInformes.getMsgDescripcionTrabajo());
		((ObtenerTCargaODEsFormImpl) form).setAnioInicioInforme(Integer.toString(tareaInformes.getFechaNivelAgregacion().get(Calendar.YEAR)));
		((ObtenerTCargaODEsFormImpl) form).setMesInicioInforme(Integer.toString(tareaInformes.getFechaNivelAgregacion().get(Calendar.MONTH) + 1));
		if (((ObtenerTCargaODEsFormImpl) form).getMesInicioInforme().length()==1){
			((ObtenerTCargaODEsFormImpl) form).setMesInicioInforme("0"+(tareaInformes.getFechaNivelAgregacion().get(Calendar.MONTH) + 1));
		}
        }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }

}




/**
 * Metodo que valida los campos introducidos en la modificacion del la tarea de informe de catalogo
 */
public void validarInformeFederadoNivelAgregacion(ActionMapping mapping, ValidarInformeFederadoNivelAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	FormularioInformeFederadoNivelAgregacionIContinuarFormImpl formCarga = (FormularioInformeFederadoNivelAgregacionIContinuarFormImpl) form;
// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(formCarga.getAnio());
	String periodicidad = formCarga.getPeriodicidad();
	if (anioInt>2099 && "N".equals(periodicidad)==false){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	String dia = formCarga.getDia();
 	String mes = formCarga.getMes();
 	String anio = formCarga.getAnio();
 	String hora = formCarga.getHora();
 	String minutos = formCarga.getMinutos();
	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de fechas rango");
		
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}

 	String mesNivelAgregacion = formCarga.getMesInicioInforme();
 	String anioNivelAgregacion = formCarga.getAnioInicioInforme();
 	
	try{
		if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS DE NIVEL DE AGREGACION");
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		format.setLenient(false);
		
		Logger.getLogger(this.getClass()).debug("validamos la fecha");
		boolean fechaValida = Utiles.validacionFechaDDMMAAAAHHMM("25", mesNivelAgregacion, anioNivelAgregacion, "yyyyMMdd");
		
		
		if(log.isDebugEnabled())log.debug("fechaValida es "+fechaValida);
		
		
		if (!fechaValida)
			throw new ValidatorException("{tareas.fechaIncorrecta}");
		
		if ((new Integer(mesNivelAgregacion).intValue() < 10)&& (mesNivelAgregacion.length() == 1))
			mesNivelAgregacion = "0" + mesNivelAgregacion;
	
		log.debug("dia "+ "25" +" mes "+mesNivelAgregacion+" anio "+anioNivelAgregacion);
	

	}catch (ValidatorException e2) 
	{
		log.error("e2 "+e2);
		throw e2;
	}
	
}




@Override
public void obtenerSubirIndices(ActionMapping mapping,
		ObtenerSubirIndicesForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	TareaVO tarea = new TareaVO();
	
	try
    {    	
		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
		
		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
		//TareaVO tareaOaiOre = this.getSrvPlanificadorService().obtenerTareaGenerarOaiOre(tarea);
		TareaVO subirIndices = this.getSrvPlanificadorService().obtenerTareaSubirIndices(tarea);
		
		//asignamos a las fechas la franja horaria correspondiente
		subirIndices.getFechaInicio().setTimeZone(tz);
		
		//rellenamos los datos especificos de este tipo de tarea
		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(subirIndices.getPeriodicidad());
		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(subirIndices.getTipoTarea());    		
		((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(subirIndices.getFechaInicio().get(Calendar.YEAR)));
		((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(subirIndices.getFechaInicio().get(Calendar.MONTH) + 1));
		((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(subirIndices.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
		((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(subirIndices.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
		((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(subirIndices.getFechaInicio().get(Calendar.MINUTE)));
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
}




@Override
public void obtenerActualizarIndicesRemotos(ActionMapping mapping,
		ObtenerActualizarIndicesRemotosForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	TareaVO tarea = new TareaVO();
	
	try
    {    	
		//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
		tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
		tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
		tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
		tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
		tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
		
		//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
		//TareaVO tareaOaiOre = this.getSrvPlanificadorService().obtenerTareaGenerarOaiOre(tarea);
		TareaVO actualizarIndicesRemotos = this.getSrvPlanificadorService().obtenerTareaActualizarIndicesRemotos(tarea);
		
		//asignamos a las fechas la franja horaria correspondiente
		actualizarIndicesRemotos.getFechaInicio().setTimeZone(tz);
		
		//rellenamos los datos especificos de este tipo de tarea
		((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(actualizarIndicesRemotos.getPeriodicidad());
		((ObtenerTCargaODEsFormImpl) form).setTipoTarea(actualizarIndicesRemotos.getTipoTarea());    		
		((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(actualizarIndicesRemotos.getFechaInicio().get(Calendar.YEAR)));
		((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(actualizarIndicesRemotos.getFechaInicio().get(Calendar.MONTH) + 1));
		((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(actualizarIndicesRemotos.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
		((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(actualizarIndicesRemotos.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
		((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(actualizarIndicesRemotos.getFechaInicio().get(Calendar.MINUTE)));
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
	
}




@Override
public void validarSubirIndices(ActionMapping mapping,
		ValidarSubirIndicesForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(form.getAnio());
	String periodicidad = form.getPeriodicidad();
	if (anioInt>2099 && !"N".equals(periodicidad)){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	
	String dia = form.getDia();
 	String mes = form.getMes();
 	String anio = form.getAnio();
 	String hora = form.getHora();
 	String minutos = form.getMinutos();
 	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");
		
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}
	
	
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	log.debug("Entramos en la modificacion de la tarea");
	TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
	TareaVO datosTarea = new TareaVO(); // Datos a modificar
	
	try
    {    				
		trabajo.setTrabajo((form).getTrabajo());
		trabajo.setGrupoTrabajo((form).getGrupoTrabajo());	
		datosTarea.setTrabajo((form).getTrabajo());
		datosTarea.setGrupoTrabajo((form).getGrupoTrabajo());
		datosTarea.setTrigger((form).getTrigger());
		datosTarea.setGrupoTrigger((form).getGrupoTrigger());
		datosTarea.setPeriodicidad((form).getPeriodicidad());
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		Integer.parseInt((form).getAnio()),
        		new Integer((form).getMes()).intValue() -1,
        		new Integer((form).getDia()).intValue(),
        		new Integer((form).getHora()).intValue(),
        		new Integer((form).getMinutos()).intValue());
        	        
        datosTarea.setFechaInicio(cal);
//		datosTarea.setMsgReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgReindexado());
//		datosTarea.setMsgNoReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgNoReindexado());
//		datosTarea.setMsgDescripcionTrabajo(((FormularioReindexadoIIAceptarFormImpl) form).getMsgDescReindexado());
//        
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaSubirIndices(datosTarea, trabajo);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
}




@Override
public void validarActualizarIndicesRemotos(ActionMapping mapping,
		ValidarActualizarIndicesRemotosForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(form.getAnio());
	String periodicidad = form.getPeriodicidad();
	if (anioInt>2099 && !"N".equals(periodicidad)){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	
	String dia = form.getDia();
 	String mes = form.getMes();
 	String anio = form.getAnio();
 	String hora = form.getHora();
 	String minutos = form.getMinutos();
 	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de regenerarImagenes");
		
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}
		
	
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	log.debug("Entramos en la modificacion de la tarea");
	TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
	TareaVO datosTarea = new TareaVO(); // Datos a modificar
	
	try
    {    				
		trabajo.setTrabajo((form).getTrabajo());
		trabajo.setGrupoTrabajo((form).getGrupoTrabajo());	
		datosTarea.setTrabajo((form).getTrabajo());
		datosTarea.setGrupoTrabajo((form).getGrupoTrabajo());
		datosTarea.setTrigger((form).getTrigger());
		datosTarea.setGrupoTrigger((form).getGrupoTrigger());
		datosTarea.setPeriodicidad((form).getPeriodicidad());
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		Integer.parseInt((form).getAnio()),
        		new Integer((form).getMes()).intValue() -1,
        		new Integer((form).getDia()).intValue(),
        		new Integer((form).getHora()).intValue(),
        		new Integer((form).getMinutos()).intValue());
        	        
        datosTarea.setFechaInicio(cal);
//		datosTarea.setMsgReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgReindexado());
//		datosTarea.setMsgNoReindexado(((FormularioReindexadoIIAceptarFormImpl) form).getMsgNoReindexado());
//		datosTarea.setMsgDescripcionTrabajo(((FormularioReindexadoIIAceptarFormImpl) form).getMsgDescReindexado());
//        
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaDescargarIndicesRemotos(datosTarea, trabajo);

		form.setTareaModificada(tareaRecuperada.getTrabajo());
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	
}
   
//








private void validateDate(String dia, String mes, String anio, String hora,
		String minutos) throws ValidatorException {

	if (dia.equalsIgnoreCase("") || mes.equalsIgnoreCase("") || anio.equalsIgnoreCase("") || hora.equalsIgnoreCase("") || minutos.equalsIgnoreCase(""))
	{
		log.error("Error al introducir la fecha: dia="+dia+", mes="+mes+" año="+anio+" hora="+hora+" minutos"+minutos);
		throw new ValidatorException("{tareas.errors.dateHora.required}") ;
	}
	
	try	{
		new Integer (dia).intValue();
		new Integer(mes).intValue();
		new Integer(anio).intValue();
	 	
	} catch(Exception e) {
		log.error("Alguno de los campos de la fecha desde no son números: ",e);
		throw new ValidatorException("{tareas.fechaIncorrecta}");
	}

	try {
		Integer.parseInt(hora); 
	} catch (Exception e){
		log.error("Alguno de los campos de la hora no son números: hora="+hora);
		throw new ValidatorException("{tareas.horaIncorrecta}");
	}
	
	try {
		Integer.parseInt(minutos);
	} catch (Exception e){
		log.error("Alguno de los campos de la hora no son números: minutos="+minutos);
		throw new ValidatorException("{tareas.horaIncorrecta}");
	}

	try{
		if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS");
		Date fechaActual = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		format.setLenient(false);
		Date fechaIn = null;
		
		Logger.getLogger(this.getClass()).debug("validamos la fecha");
		boolean fechaValida = Utiles.validacionFechaDDMMAAAAHHMM(dia, mes, anio, "yyyyMMdd");
		
		Logger.getLogger(this.getClass()).debug("validamos la hora");
		boolean horaValida = Utiles.validacionHoraHHMM(hora, minutos);
		
		if(log.isDebugEnabled())log.debug("fechaValida es "+fechaValida);
		
		if (!fechaValida && !horaValida) {
			throw new ValidatorException("{tareas.fechaYHoraIncorrectas}");
		}
		
		if (!horaValida) {
			throw new ValidatorException("{tareas.horaIncorrecta}");
		}
		
		if (!fechaValida) {
			throw new ValidatorException("{tareas.fechaIncorrecta}");
		}
		
		if ((Integer.parseInt(mes) < 10)&& (mes.length() == 1))
		    mes = "0" + mes;
			
		if ((Integer.parseInt(dia) < 10)&&(dia.length() == 1))
		   dia = "0" + dia;
		   
		if ((Integer.parseInt(hora) < 10)&& (hora.length() == 1))
		   hora = "0" + hora;
		   
		if ((Integer.parseInt(minutos) < 10)&& (minutos.length() == 1))
		   minutos = "0" + minutos;
		
		log.debug("dia "+dia+" mes "+mes+" anio "+anio+" hora "+hora+" minutos "+minutos);
		fechaIn = format.parse(dia + mes + anio + hora + minutos + "59");
		
		if(fechaActual.getTime() > fechaIn.getTime()) {
			log.error("La fecha introducida es anterior a la actual");
			throw new ValidatorException("{tareas.fechaAnteriorActual}");
		}

	} catch(java.text.ParseException pe) {
		log.error("Error al introducir la fecha "+pe);
		throw new ValidatorException("{tareas.errors.fecha.incorrecta}");
		
	} catch (ValidatorException e2) {
		log.error("e2 "+e2);
		throw e2;
	}
}




@Override
public void validarGenerarEstadisticasLocales(ActionMapping mapping,
		ValidarGenerarEstadisticasLocalesForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {

	FormularioTareaGenerarEstadisticasLocalesAceptarFormImpl formEstadisticasLocales = (FormularioTareaGenerarEstadisticasLocalesAceptarFormImpl) form;
	//Primero validamos
// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(formEstadisticasLocales.getAnio());
	String periodicidad = form.getPeriodicidad();
	if (anioInt>2099 && !"N".equals(periodicidad)){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	
	String dia = formEstadisticasLocales.getDia();
 	String mes = formEstadisticasLocales.getMes();
 	String anio = formEstadisticasLocales.getAnio();
 	String hora = formEstadisticasLocales.getHora();
 	String minutos = formEstadisticasLocales.getMinutos();
 	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de modificar estadísticas locales");
	
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}
		
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	try
    {
		log.debug("Entramos en la modificacion de la tarea estadísticas locales");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaVO datosTarea = new TareaVO(); // Datos a modificar
		
		trabajo.setTrabajo((formEstadisticasLocales).getTrabajo());
		trabajo.setGrupoTrabajo((formEstadisticasLocales).getGrupoTrabajo());	
		datosTarea.setTrabajo((formEstadisticasLocales).getTrabajo());
		datosTarea.setGrupoTrabajo((formEstadisticasLocales).getGrupoTrabajo());
		datosTarea.setTrigger((formEstadisticasLocales).getTrigger());
		datosTarea.setGrupoTrigger((formEstadisticasLocales).getGrupoTrigger());
		datosTarea.setPeriodicidad((formEstadisticasLocales).getPeriodicidad());
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		new Integer((formEstadisticasLocales).getAnio()).intValue(),
        		new Integer((formEstadisticasLocales).getMes()).intValue() -1,
        		new Integer((formEstadisticasLocales).getDia()).intValue(),
        		new Integer((formEstadisticasLocales).getHora()).intValue(),
        		new Integer((formEstadisticasLocales).getMinutos()).intValue());
        	        
        datosTarea.setFechaInicio(cal);
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaGenerarEstadisticasLocales(datosTarea, trabajo);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
    }
    catch (Exception e)
    {
    	log.error("Error: " + e);
    	throw new ValidatorException("{tareas.error}");
    }	

}




@Override
public void validarGenerarEstadisticasTotales(ActionMapping mapping,
		ValidarGenerarEstadisticasTotalesForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {


	FormularioTareaGenerarEstadisticasTotalesAceptarFormImpl formEstadisticasTotales = (FormularioTareaGenerarEstadisticasTotalesAceptarFormImpl) form;
	//Primero validamos
// 	La tarea no puede ser superior al 12/12/2099
	Integer anioInt = Integer.parseInt(formEstadisticasTotales.getAnio());
	String periodicidad = form.getPeriodicidad();
	if (anioInt>2099 && !"N".equals(periodicidad)){
		log.error("El año de inicio de la tarea no puede ser superior a 2099");
		throw new ValidatorException("{tareas.error.fechaSuperior}") ;
	}
	
	String dia = formEstadisticasTotales.getDia();
 	String mes = formEstadisticasTotales.getMes();
 	String anio = formEstadisticasTotales.getAnio();
 	String hora = formEstadisticasTotales.getHora();
 	String minutos = formEstadisticasTotales.getMinutos();
 	
 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea de modificar estadísticas totales");
	
	try {
		validateDate(dia, mes, anio, hora, minutos);
	} catch (Exception e) {
		log.error("No se pudo validar la fecha/hora: ",e);
	}
		
	if (tz == null) 
		tz = Utiles.asignarZonaHoraria();
	
	try
    {
		log.debug("Entramos en la modificacion de la tarea estadísticas totales");
		TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
		TareaVO datosTarea = new TareaVO(); // Datos a modificar
		
		trabajo.setTrabajo((formEstadisticasTotales).getTrabajo());
		trabajo.setGrupoTrabajo((formEstadisticasTotales).getGrupoTrabajo());	
		datosTarea.setTrabajo((formEstadisticasTotales).getTrabajo());
		datosTarea.setGrupoTrabajo((formEstadisticasTotales).getGrupoTrabajo());
		datosTarea.setTrigger((formEstadisticasTotales).getTrigger());
		datosTarea.setGrupoTrigger((formEstadisticasTotales).getGrupoTrigger());
		datosTarea.setPeriodicidad((formEstadisticasTotales).getPeriodicidad());
		
        Calendar cal = Calendar.getInstance(tz);
        cal = new GregorianCalendar(
        		new Integer((formEstadisticasTotales).getAnio()).intValue(),
        		new Integer((formEstadisticasTotales).getMes()).intValue() -1,
        		new Integer((formEstadisticasTotales).getDia()).intValue(),
        		new Integer((formEstadisticasTotales).getHora()).intValue(),
        		new Integer((formEstadisticasTotales).getMinutos()).intValue());
        	        
        datosTarea.setFechaInicio(cal);
		datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaGenerarEstadisticasTotales(datosTarea, trabajo);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
    }
    catch (Exception e)
    {
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
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaEstadisticasLocales(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
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
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
		
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaEstadisticasTotales(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }	
	}




	@Override
	public void obtenerTareaObtenerMetadatosODESFederados(ActionMapping mapping,
			ObtenerTareaObtenerMetadatosODESFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		TareaVO tarea = new TareaVO();
	
		try
	    {    	
			//cargamos la tarea con lo valores esenciales para recuperar los demas valores de esta misma tarea 
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerMetadatosODESFederados(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}




	@Override
	public void validarObtenerMetadatosODESFederados(ActionMapping mapping,
			ValidarObtenerMetadatosODESFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormularioObtenerMetadatosOdesFederadosAceptarFormImpl formObtenerMetadatos = (FormularioObtenerMetadatosOdesFederadosAceptarFormImpl) form;
		//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formObtenerMetadatos.getAnio());
		String periodicidad = form.getPeriodicidad();
		if (anioInt>2099 && !"N".equals(periodicidad)){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formObtenerMetadatos.getDia();
	 	String mes = formObtenerMetadatos.getMes();
	 	String anio = formObtenerMetadatos.getAnio();
	 	String hora = formObtenerMetadatos.getHora();
	 	String minutos = formObtenerMetadatos.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea ObtenerMetadatosODESFederados");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}
			
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		try
	    {
			log.debug("Entramos en la modificacion de la tarea ObtenerMetadatosODESFederados");
			TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
			TareaVO datosTarea = new TareaVO(); // Datos a modificar
			
			trabajo.setTrabajo((formObtenerMetadatos).getTrabajo());
			trabajo.setGrupoTrabajo((formObtenerMetadatos).getGrupoTrabajo());	
			datosTarea.setTrabajo((formObtenerMetadatos).getTrabajo());
			datosTarea.setGrupoTrabajo((formObtenerMetadatos).getGrupoTrabajo());
			datosTarea.setTrigger((formObtenerMetadatos).getTrigger());
			datosTarea.setGrupoTrigger((formObtenerMetadatos).getGrupoTrigger());
			datosTarea.setPeriodicidad((formObtenerMetadatos).getPeriodicidad());
			
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formObtenerMetadatos).getAnio()).intValue(),
	        		new Integer((formObtenerMetadatos).getMes()).intValue() -1,
	        		new Integer((formObtenerMetadatos).getDia()).intValue(),
	        		new Integer((formObtenerMetadatos).getHora()).intValue(),
	        		new Integer((formObtenerMetadatos).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
			datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaObtenerMetadatosODESFederados(datosTarea, trabajo);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
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
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerODESDespublicadosFederados(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}




	@Override
	public void validarObtenerODESDespublicadosFederados(ActionMapping mapping,
			ValidarObtenerODESDespublicadosFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormularioObtenerOdesDespublicadosFederadosAceptarFormImpl formObtenerODESDespublicados = (FormularioObtenerOdesDespublicadosFederadosAceptarFormImpl) form;
		//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formObtenerODESDespublicados.getAnio());
		String periodicidad = form.getPeriodicidad();
		if (anioInt>2099 && !"N".equals(periodicidad)){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formObtenerODESDespublicados.getDia();
	 	String mes = formObtenerODESDespublicados.getMes();
	 	String anio = formObtenerODESDespublicados.getAnio();
	 	String hora = formObtenerODESDespublicados.getHora();
	 	String minutos = formObtenerODESDespublicados.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea ObtenerODESDespublicadosFederados");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}
			
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		try
	    {
			log.debug("Entramos en la modificacion de la tarea ObtenerODESDespublicadosFederados");
			TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
			TareaVO datosTarea = new TareaVO(); // Datos a modificar
			
			trabajo.setTrabajo((formObtenerODESDespublicados).getTrabajo());
			trabajo.setGrupoTrabajo((formObtenerODESDespublicados).getGrupoTrabajo());	
			datosTarea.setTrabajo((formObtenerODESDespublicados).getTrabajo());
			datosTarea.setGrupoTrabajo((formObtenerODESDespublicados).getGrupoTrabajo());
			datosTarea.setTrigger((formObtenerODESDespublicados).getTrigger());
			datosTarea.setGrupoTrigger((formObtenerODESDespublicados).getGrupoTrigger());
			datosTarea.setPeriodicidad((formObtenerODESDespublicados).getPeriodicidad());
			
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formObtenerODESDespublicados).getAnio()).intValue(),
	        		new Integer((formObtenerODESDespublicados).getMes()).intValue() -1,
	        		new Integer((formObtenerODESDespublicados).getDia()).intValue(),
	        		new Integer((formObtenerODESDespublicados).getHora()).intValue(),
	        		new Integer((formObtenerODESDespublicados).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
			datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaObtenerODESDespublicadosFederados(datosTarea, trabajo);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
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
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerMetadatosODESFederadosFaltantes(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
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
			tarea.setTrabajo(((ObtenerTCargaODEsFormImpl) form).getTrabajo());
			tarea.setGrupoTrabajo(((ObtenerTCargaODEsFormImpl) form).getGrupoTrabajo());
			tarea.setTrigger(((ObtenerTCargaODEsFormImpl) form).getTrigger());
			tarea.setGrupoTrigger(((ObtenerTCargaODEsFormImpl) form).getGrupoTrigger());
			tarea.setPeriodicidad(((ObtenerTCargaODEsFormImpl) form).getPeriodicidad());
			
			//llamamos al servicio para que carge los valores en la tarea del tipo correspondiente
			TareaVO t = this.getSrvPlanificadorService().obtenerTareaObtenerODESDespublicadosFederadosFaltantes(tarea);
			
			//asignamos a las fechas la franja horaria correspondiente
			t.getFechaInicio().setTimeZone(tz);
			
			//rellenamos los datos especificos de este tipo de tarea
			((ObtenerTCargaODEsFormImpl) form).setPeriodicidad(t.getPeriodicidad());
			((ObtenerTCargaODEsFormImpl) form).setTipoTarea(t.getTipoTarea());    		
			((ObtenerTCargaODEsFormImpl) form).setAnio(Integer.toString(t.getFechaInicio().get(Calendar.YEAR)));
			((ObtenerTCargaODEsFormImpl) form).setMes(Integer.toString(t.getFechaInicio().get(Calendar.MONTH) + 1));
			((ObtenerTCargaODEsFormImpl) form).setDia(Integer.toString(t.getFechaInicio().get(Calendar.DAY_OF_MONTH)));
			((ObtenerTCargaODEsFormImpl) form).setHora(Integer.toString(t.getFechaInicio().get(Calendar.HOUR_OF_DAY)));
			((ObtenerTCargaODEsFormImpl) form).setMinutos(Integer.toString(t.getFechaInicio().get(Calendar.MINUTE)));
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }
	}




	@Override
	public void validarObtenerMetadatosODESFederadosFaltantes(
			ActionMapping mapping,
			ValidarObtenerMetadatosODESFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormularioObtenerMetadatosOdesFederadosFaltantesAceptarFormImpl formObtenerMetadatosODESFederadosFaltantes = (FormularioObtenerMetadatosOdesFederadosFaltantesAceptarFormImpl) form;
		//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formObtenerMetadatosODESFederadosFaltantes.getAnio());
		String periodicidad = form.getPeriodicidad();
		if (anioInt>2099 && !"N".equals(periodicidad)){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formObtenerMetadatosODESFederadosFaltantes.getDia();
	 	String mes = formObtenerMetadatosODESFederadosFaltantes.getMes();
	 	String anio = formObtenerMetadatosODESFederadosFaltantes.getAnio();
	 	String hora = formObtenerMetadatosODESFederadosFaltantes.getHora();
	 	String minutos = formObtenerMetadatosODESFederadosFaltantes.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea ObtenerMetadatosODESFederadosFaltantes");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}
			
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		try
	    {
			log.debug("Entramos en la modificacion de la tarea ObtenerMetadatosODESFederadosFaltantes");
			TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
			TareaVO datosTarea = new TareaVO(); // Datos a modificar
			
			trabajo.setTrabajo((formObtenerMetadatosODESFederadosFaltantes).getTrabajo());
			trabajo.setGrupoTrabajo((formObtenerMetadatosODESFederadosFaltantes).getGrupoTrabajo());	
			datosTarea.setTrabajo((formObtenerMetadatosODESFederadosFaltantes).getTrabajo());
			datosTarea.setGrupoTrabajo((formObtenerMetadatosODESFederadosFaltantes).getGrupoTrabajo());
			datosTarea.setTrigger((formObtenerMetadatosODESFederadosFaltantes).getTrigger());
			datosTarea.setGrupoTrigger((formObtenerMetadatosODESFederadosFaltantes).getGrupoTrigger());
			datosTarea.setPeriodicidad((formObtenerMetadatosODESFederadosFaltantes).getPeriodicidad());
			
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formObtenerMetadatosODESFederadosFaltantes).getAnio()).intValue(),
	        		new Integer((formObtenerMetadatosODESFederadosFaltantes).getMes()).intValue() -1,
	        		new Integer((formObtenerMetadatosODESFederadosFaltantes).getDia()).intValue(),
	        		new Integer((formObtenerMetadatosODESFederadosFaltantes).getHora()).intValue(),
	        		new Integer((formObtenerMetadatosODESFederadosFaltantes).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
			datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaObtenerMetadatosODESFederadosFaltantes(datosTarea, trabajo);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }	
	}




	@Override
	public void validarObtenerODESDespublicadosFederadosFaltantes(
			ActionMapping mapping,
			ValidarObtenerODESDespublicadosFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FormularioObtenerOdesDespublicadosFederadosFaltantesAceptarFormImpl formObtenerODESDespublicadosFederadosFaltantes = (FormularioObtenerOdesDespublicadosFederadosFaltantesAceptarFormImpl) form;
		//Primero validamos
	// 	La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(formObtenerODESDespublicadosFederadosFaltantes.getAnio());
		String periodicidad = form.getPeriodicidad();
		if (anioInt>2099 && !"N".equals(periodicidad)){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		String dia = formObtenerODESDespublicadosFederadosFaltantes.getDia();
	 	String mes = formObtenerODESDespublicadosFederadosFaltantes.getMes();
	 	String anio = formObtenerODESDespublicadosFederadosFaltantes.getAnio();
	 	String hora = formObtenerODESDespublicadosFederadosFaltantes.getHora();
	 	String minutos = formObtenerODESDespublicadosFederadosFaltantes.getMinutos();
	 	
	 	if(log.isDebugEnabled())log.debug("Validamos el formulario de la tarea ObtenerODESDespublicadosFederadosFaltantes");
		
		try {
			validateDate(dia, mes, anio, hora, minutos);
		} catch (Exception e) {
			log.error("No se pudo validar la fecha/hora: ",e);
		}
			
		if (tz == null) 
			tz = Utiles.asignarZonaHoraria();
		
		try
	    {
			log.debug("Entramos en la modificacion de la tarea ObtenerODESDespublicadosFederadosFaltantes");
			TrabajoVO trabajo = new TrabajoVO(); // Trabajo a modificar
			TareaVO datosTarea = new TareaVO(); // Datos a modificar
			
			trabajo.setTrabajo((formObtenerODESDespublicadosFederadosFaltantes).getTrabajo());
			trabajo.setGrupoTrabajo((formObtenerODESDespublicadosFederadosFaltantes).getGrupoTrabajo());	
			datosTarea.setTrabajo((formObtenerODESDespublicadosFederadosFaltantes).getTrabajo());
			datosTarea.setGrupoTrabajo((formObtenerODESDespublicadosFederadosFaltantes).getGrupoTrabajo());
			datosTarea.setTrigger((formObtenerODESDespublicadosFederadosFaltantes).getTrigger());
			datosTarea.setGrupoTrigger((formObtenerODESDespublicadosFederadosFaltantes).getGrupoTrigger());
			datosTarea.setPeriodicidad((formObtenerODESDespublicadosFederadosFaltantes).getPeriodicidad());
			
	        Calendar cal = Calendar.getInstance(tz);
	        cal = new GregorianCalendar(
	        		new Integer((formObtenerODESDespublicadosFederadosFaltantes).getAnio()).intValue(),
	        		new Integer((formObtenerODESDespublicadosFederadosFaltantes).getMes()).intValue() -1,
	        		new Integer((formObtenerODESDespublicadosFederadosFaltantes).getDia()).intValue(),
	        		new Integer((formObtenerODESDespublicadosFederadosFaltantes).getHora()).intValue(),
	        		new Integer((formObtenerODESDespublicadosFederadosFaltantes).getMinutos()).intValue());
	        	        
	        datosTarea.setFechaInicio(cal);
			datosTarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada =this.getSrvPlanificadorService().modificarTareaObtenerODESDespublicadosFederadosFaltantes(datosTarea, trabajo);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
	    }
	    catch (Exception e)
	    {
	    	log.error("Error: " + e);
	    	throw new ValidatorException("{tareas.error}");
	    }	
	}

    
}