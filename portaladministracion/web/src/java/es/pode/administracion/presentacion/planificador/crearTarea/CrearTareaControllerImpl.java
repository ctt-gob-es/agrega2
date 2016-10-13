// license-header java merge-point
package es.pode.administracion.presentacion.planificador.crearTarea;

import java.io.IOException;
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
import es.pode.soporte.validador.ValidadorMEC;

public class CrearTareaControllerImpl extends CrearTareaController {
	private static Logger log = Logger.getLogger(CrearTareaControllerImpl.class);

	private static TimeZone tz = null;

//	Utiles utilidades = new Utiles();


	/**
	 * Método que discrimina la tarea a crear
	 * 
	 * Retorna 1: Carga de ODEs 2: Reindexado 3: Eliminar ODEs 4:Infome fecha 5:Informe rango 6:Informe usuario 7:Informe Federado 8:Informe catalogo
	 * 
	 * @param mapping
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public final String crearTarea(ActionMapping mapping, CrearTareaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		if(log.isDebugEnabled())log.debug("CREAR TAREA");
	
		TrabajoVO trabajo = new TrabajoVO();
		trabajo.setTrabajo(form.getTrabajo());

		Boolean existe = this.getSrvPlanificadorService().existeTrabajo(trabajo);

		/* Comprobamos si la tarea ya existe */
		try 
		{
			if (existe)
				throw new ValidatorException("{tareas.tareaExiste}");	
		} 
		catch (ValidatorException e2) 
		{
			saveErrorMessage(request, "tareas.tareaExiste");
			return "Error";
		}

		//La tarea no puede ser superior al 12/12/2099
		Integer anioInt = Integer.parseInt(form.getAnio());
		String periodicidad = form.getPeriodicidad();
		if (anioInt>2099 && "N".equals(periodicidad)==false){
			log.error("El año de inicio de la tarea no puede ser superior a 2099");
			throw new ValidatorException("{tareas.error.fechaSuperior}") ;
		}
		
		/* Comprobamos si el nombre de tarea tiene caracteres raros */
		try 
		{
			if (!Utiles.validacionCaracter(form.getTrabajo())) {
				log.error("Nombre de tarea incorrecto.");
				throw new ValidatorException("{tareas.nombreIncorrecto}");
			}
		} 
		catch (ValidatorException e2) {
			saveErrorMessage(request, "tareas.nombreIncorrecto");
			return "Error";
		}
		
		String dia = form.getDia();
		String mes = form.getMes();
		String anio = form.getAnio();
		String hora = form.getHora();
		String minutos= form.getMinutos();
		
		if (dia.equalsIgnoreCase("") || mes.equalsIgnoreCase("") || anio.equalsIgnoreCase("") || 
				hora.equalsIgnoreCase("") || minutos.equalsIgnoreCase(""))
		{
			log.error("Error al introducir la fecha.");
			throw new ValidatorException("{tareas.errors.dateHora.required}") ;
		}
		//}catch(Exception e)
		//{
			//throw new ValidatorException("{tareas.errors.fecha.incorrecta}");
		//}
		try
		{
		 	new Integer (dia).intValue();
		 	new Integer(mes).intValue();
		 	new Integer(anio).intValue();
		 	
		}catch(Exception e)
		{
			log.error("Alguno de los campos de la fecha no son números");
			throw new ValidatorException("{tareas.fechaIncorrecta}");
		}
		
		try
		{
		 	new Integer(hora).intValue();
		  	new Integer(minutos).intValue();
		 		 	
		}catch(Exception e)
		{
			log.error("Alguno de los campos de la hora no son números");
			throw new ValidatorException("{tareas.horaIncorrecta}");
		}
		
		try 
		{
			if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS");
			Date fechaActual = new Date();

			Date fechaIn = null;
			
			
			if(log.isDebugEnabled())log.debug("antes de validar la hora");
			boolean horaValida = Utiles.validacionHoraHHMM(hora,minutos);
			
			if(log.isDebugEnabled())log.debug("horaValida es "+horaValida);
			boolean fechaValida = Utiles.validacionFechaDDMMAAAAHHMM(dia, mes, anio, "yyyyMMdd");
			
			if(log.isDebugEnabled())log.debug("fechaValida es "+fechaValida);
			if (!fechaValida && !horaValida)
				throw new ValidatorException("{tareas.fechaYHoraIncorrectas}");

			if (!horaValida)
				throw new ValidatorException("{tareas.horaIncorrecta}");

			if (!fechaValida)
				throw new ValidatorException("{tareas.fechaIncorrecta}");
			
			if ((new Integer(mes).intValue() < 10)&& (mes.length() == 1))
                 mes = "0" + mes;
			
            if ((new Integer(dia).intValue() < 10)&&(dia.length() == 1))
                 dia = "0" + dia;
            
            if ((new Integer(hora).intValue() < 10)&& (hora.length() == 1))
                hora = "0" + hora;
            
           if ((new Integer(minutos).intValue() < 10)&& (minutos.length() == 1))
               minutos = "0" + minutos;

           log.debug("dia "+dia+" mes "+mes+" anio "+anio+" hora "+hora+" minutos "+minutos);
           SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
           fechaIn = format.parse(dia + mes + anio + hora + minutos + "59");
           if(fechaActual.getTime() > fechaIn.getTime())
           {
        	   log.error("La fecha introducida es anterior a la actual");
        	   throw new ValidatorException("{tareas.fechaAnteriorActual}");
           }

		}  catch(java.text.ParseException pe)
        {
        	log.error("Error al introducir la fecha "+pe);
			throw new ValidatorException("{tareas.errors.fecha.incorrecta}");
        }
		catch (ValidatorException e2) 
		{
			log.error("e2 "+e2);
			throw e2;
		
		}
		
		
		//seleccionamos la 2ª pantalla a la que se debe ir dependiendo del tipo de tarea seleccionada

		String tipoTarea = null;
		String tipoTareaDevolver = null;
		try{
			
			if(log.isDebugEnabled())log.debug("obtenerTipoInforme");
	        tipoTarea = form.getTipoTarea();

	        //Si el tipoTarea es Reindexado, carga ODEs o eliminar ODEs el tipo tarea se mantiene
	        if(tipoTarea.equalsIgnoreCase("estadoOdes") || tipoTarea.equalsIgnoreCase("operacionesRealizadas") || 
	        		tipoTarea.equalsIgnoreCase("nivelAgregacion") || tipoTarea.equalsIgnoreCase("coberturaCurricular") || 
	        		tipoTarea.equalsIgnoreCase("odesLicencias") || tipoTarea.equalsIgnoreCase("usuarios") || 
	        		tipoTarea.equalsIgnoreCase("procesosPlanificados"))
	        {	
	        	if(log.isDebugEnabled())log.debug("cargo informe con fechas");	
	        	tipoTareaDevolver = "InformeFecha";
	        	
	        }
	        else if(tipoTarea.equalsIgnoreCase("terminosBusqueda") ||  tipoTarea.equalsIgnoreCase("masValorado") || 
	        			tipoTarea.equalsIgnoreCase("masMostrado") || tipoTarea.equalsIgnoreCase("masPrevisualizado") || 
	        			tipoTarea.equalsIgnoreCase("masVisualizado") || tipoTarea.equalsIgnoreCase("masDescargado") || 
	        			tipoTarea.equalsIgnoreCase("tamanio"))
	        {
	        	if(log.isDebugEnabled())log.debug("cargo informe con rango");
	        	tipoTareaDevolver = "InformeFechaRango";
	        }
	        else if(tipoTarea.equalsIgnoreCase("odesUsuario"))
	        {
	        	
	        	if(log.isDebugEnabled())log.debug("cargo informe con usuario");
	        	tipoTareaDevolver = "InformeFechaUsuario";
	        	
	        }
	        else if(tipoTarea.indexOf("Federada") != -1)
	        {
	        	if (tipoTarea.equalsIgnoreCase("odesIdiomaFederada") 
		        		|| tipoTarea.equalsIgnoreCase("odesPublicadosFederada")
		        		|| tipoTarea.equalsIgnoreCase("coberturaCurricularFederada")){
		        	tipoTareaDevolver= "InformeFederado";
		        }else{
		        	tipoTareaDevolver = "InformeFederadoNivelAgregacion";
		        }
	        }
			
	        else if(tipoTarea.equalsIgnoreCase("repositorio"))
	        {
	        	tipoTareaDevolver = "InformeCatalogo";
	        }
	        else if(tipoTarea.equalsIgnoreCase("DespublicarODEs"))
	        {
	        	tipoTareaDevolver = "DespublicarODEs";
	        }
	        else if (tipoTarea.equalsIgnoreCase("GenerarOaiOre")){
	        	tipoTareaDevolver= "GenerarOaiOre";
	        }
	        else if (tipoTarea.equalsIgnoreCase("ActualizarIndicesRemotos")){
	        	tipoTareaDevolver= "ActualizarIndicesRemotos";
	        }
	        else if (tipoTarea.equalsIgnoreCase("SubirIndices")){
	        	tipoTareaDevolver= "SubirIndices";
	        }
	        
	        else
	        	tipoTareaDevolver = tipoTarea;
	        
	        if(log.isDebugEnabled())log.debug("tipoTarea -> " + tipoTarea);
		
		
		}
		catch (Exception e) 
		{
			saveErrorMessage(request, "tareas.error");
			return "Error";
		}
		
		
		if(log.isDebugEnabled())log.debug("devuelvo el tipo de tarea -> " + tipoTareaDevolver);
		return tipoTareaDevolver;
	}
	
	
	/**
	 * Crea una tarea del tipo Carga ODEs el que carga los ODEs especificados en un directorio
	 * determinado que le indicamos
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario que contiene los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public final void crearTareaCargaODEs(ActionMapping mapping,
			CrearTareaCargaODEsForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		TareaCargaODEsVO tareaRecuperada = null;
		
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.parseInt(form.getAnio()), Integer.parseInt(form
					.getMes()) - 1, Integer.parseInt(form.getDia()), Integer.parseInt(form
					.getHora()), Integer.parseInt(form.getMinutos()));
			
			TareaCargaODEsVO tarea = new TareaCargaODEsVO();
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
			tarea.setPathODE(form.getPathODEs());
			tarea.setPathODEsCargados(form.getPathODEsCarg());
			tarea.setPathODEsNoCargados(form.getPathODEsNoCarg());
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setMsgPublicado(form.getMsgPublicado());
			tarea.setMsgNoPublicado(form.getMsgNoPublicado());
			tarea.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
			tarea.setDescripcionTarea(form.getDescripcionTarea());
			tarea.setNombreLote(form.getNombreLote());
			
			log.info("El VO que mando tiene getDescripcionTarea: " + tarea.getDescripcionTarea());
			log.info("El VO que mando tiene getNombreLote: " + tarea.getNombreLote());
			
			if (form.getSobrescribir()!=null && form.getSobrescribir().equals("on"))
				tarea.setSobrescribir("s");
			else 
				tarea.setSobrescribir("n");
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			tareaRecuperada = this.getSrvPlanificadorService()
			.crearTareaCargaODEs(tarea);
			
			log.info("El VO que recibo tiene getDescripcionTarea:" + tareaRecuperada.getDescripcionTarea());
			log.info("El VO que recibo tiene getNombreLote:" + tareaRecuperada.getNombreLote());
			
			
			if (tareaRecuperada.getError() != null)
				throw new ValidatorException("{" + tareaRecuperada.getError()
						+ "}");
			form.setTareaModificada(tareaRecuperada.getTrabajo());
			
			
		} catch (ValidatorException e2) {
			throw new ValidatorException("{" + tareaRecuperada!=null?tareaRecuperada.getError():"null" + "}");
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}
	

	/**
	 * Crea una tarea del tipo Reindexado la que reindexa los ODEs de un idioma
	 * determinado que le indicamos
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario que contiene los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public final void crearTareaReindexado(ActionMapping mapping,
			CrearTareaReindexadoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (tz == null)
			tz = Utiles.asignarZonaHoraria();

		try {
			Calendar cal = Calendar.getInstance(tz);
			//TODO Este horror hay que cambiarlo!
			cal = new GregorianCalendar(Integer.parseInt(form.getAnio()), Integer.parseInt(form
					.getMes())- 1, Integer.parseInt(form.getDia()),Integer.parseInt(form
					.getHora()),Integer.parseInt(form.getMinutos()));

			TareaReindexadoVO tarea = new TareaReindexadoVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
			tarea.setRepositorioIndices(form.getRepositorioIndices());
			tarea.setMsgReindexado(form.getMsgReindexado());
			tarea.setMsgNoReindexado(form.getMsgNoReindexado());
			tarea.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());

			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());

			TareaReindexadoVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaReindexado(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}

	/**
	 * Elimina los ODEs que estan despublicados entre dos fechas
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario del tipo CrearTareasEliminarNoDisponibles
	 * @param request
	 *            Request con los datos que se manden a traves de ella
	 * @param response
	 *            Response con los datos que contenga
	 * @throws Exception
	 */
	public final void crearTareasEliminarNoDisponibles(ActionMapping mapping,
			CrearTareasEliminarNoDisponiblesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();
		String diaDesde = form.getDiaDesde();
	 	String mesDesde = form.getMesDesde();
	 	String anioDesde = form.getAnioDesde();
	 	String diaHasta = form.getDiaHasta();
	 	String mesHasta = form.getMesHasta();
	 	String anioHasta = form.getAnioHasta();

		try {
			
			//Comprobamos que todos los campos de la fecha de inicio están rellenos
    		if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
    				throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
    		
    		//Comprobamos que todos los campos de la fecha fin están rellenos
    		if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
    				throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
    		
    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas tiene caracteres no numericos");
    		try
    		{
    		 	new Integer(diaDesde).intValue();
    		 	new Integer(mesDesde).intValue();
    		 	new Integer(anioDesde).intValue();
    		 	
    		}catch(Exception e)
    		{
    			log.error("Alguno de los campos de la fecha desde no son números");
    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
    		}
    		try
    		{
    		 	new Integer(diaHasta).intValue();
    		 	new Integer(mesHasta).intValue();
    		 	new Integer(anioHasta).intValue();
    		 	
    		}catch(Exception e)
    		{
    			log.error("Alguno de los campos de la fecha hasta no son números");
    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
    		}
    		
			// Comprobamos si las fechas introducidas son correctas
			boolean fechaValidaDesde = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde, "yyyyMMdd");

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

			Calendar cal = Calendar.getInstance(tz);
			//TODO Otra vez...
			cal = new GregorianCalendar(new Integer(anio).intValue(), 
					new Integer(mes).intValue() - 1, new Integer(dia).intValue(), 
					new Integer(hora).intValue(), new Integer(minutos).intValue());

			Calendar calDesde = Calendar.getInstance(tz);
			calDesde = new GregorianCalendar(new Integer(anioDesde).intValue(), new Integer(mesDesde).intValue() - 1, 
					new Integer(diaDesde).intValue(),0,1);

			Calendar calHasta = Calendar.getInstance(tz);
			calHasta = new GregorianCalendar(new Integer(anioHasta).intValue(), new Integer(mesHasta).intValue() - 1, 
					new Integer(diaHasta).intValue(),23,59);

			TareaEliminaNoDisponiblesVO tareaEliminada = new TareaEliminaNoDisponiblesVO();
			tareaEliminada.setFechaInicio(cal);
			tareaEliminada.setFechaDesde(calDesde);
			tareaEliminada.setFechaHasta(calHasta);
			tareaEliminada.setTrabajo(form.getTrabajo());
			tareaEliminada.setTrigger(form.getTrigger());
			tareaEliminada.setTipoTarea(form.getTipoTarea());
			tareaEliminada.setPeriodicidad(form.getPeriodicidad());

			tareaEliminada.setUsuario(LdapUserDetailsUtils.getUsuario());

			TareaEliminaNoDisponiblesVO tareaRecuperada =  this.getSrvPlanificadorService().crearTareaEliminarNoDisponibles(tareaEliminada);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
			
		} catch (ValidatorException e2) {
			throw e2;
		} catch (Exception e) {
			log.info("Exception --> " + e);
			throw new ValidatorException("{tareas.error}");
		}

	}
	
	/**
	 * Recoge los valores de la tarea y del informe y llama al metodo que crea una tarea(siempre no periodica) de informe fecha
	 * @param mapping
	 * @param form
	 *            Formulario en el que se guardan los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void crearTareaInformesFecha(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.CrearTareaInformesFechaForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception 
	{
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();
		
		try{
		
		
			//convertimos los datos de fecha de inicio en un calendar
			Calendar fechaInicio = Calendar.getInstance(tz);
			fechaInicio = new GregorianCalendar(new Integer(anio).intValue(), new Integer(mes).intValue() - 1, 
				new Integer(dia).intValue(), new Integer(hora).intValue(), new Integer(minutos).intValue());
					
			TareaInformesVO tareaInformeVO = new TareaInformesVO();
	    	
			//Recogemos el usuario para mandarlo en el TareaInformesVO
			String usuario = LdapUserDetailsUtils.getUsuario();
			
			if(log.isDebugEnabled())log.debug("rellenamos los datos del VO desde el formulario");
	    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
	    	tareaInformeVO.setTrabajo(form.getTrabajo());
	    	tareaInformeVO.setTrigger(form.getTrigger());
	    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
	    	tareaInformeVO.setInforme(form.getTipoTarea());
	    	tareaInformeVO.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
	    	tareaInformeVO.setMsgInforme(form.getMsgInforme());
	    	tareaInformeVO.setMsgNoInforme(form.getMsgNoInforme());
	    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
	    	tareaInformeVO.setFormato(form.getFormato());
	    	tareaInformeVO.setFechaInicio(fechaInicio);
	    	tareaInformeVO.setUsuario(usuario);
    		
			if((form.getPeriodicidad()).equalsIgnoreCase("N"))
			{
				String diaDesde = form.getDiaDesde();
			 	String mesDesde = form.getMesDesde();
			 	String anioDesde = form.getAnioDesde();
			 	String diaHasta = form.getDiaHasta();
			 	String mesHasta = form.getMesHasta();
			 	String anioHasta = form.getAnioHasta();
			
	    		//Comprobamos si estan vacías las dos fechas(Desde y Hasta), si es asi,  asignaríamos fechas de un rango amplio
			 	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde y Hasta estan vacias");
	    		if(anioDesde.equalsIgnoreCase("") && mesDesde.equalsIgnoreCase("") && diaDesde.equalsIgnoreCase("") && 
	    				anioHasta.equalsIgnoreCase("") && mesHasta.equalsIgnoreCase("") && diaHasta.equalsIgnoreCase(""))
	    		{
	    			if(log.isDebugEnabled())log.debug("El rango de fechas esta vacio asignamos unas por defecto");
	    			form.setAnioDesde("2007");
	    			form.setMesDesde("1");
	    			form.setDiaDesde("1");
	    			form.setAnioHasta("2100");
	    			form.setMesHasta("1");
	    			form.setDiaHasta("1");
	    		}
				else
	    		{
	    		
					//Comprobamos que todos los campos de la fecha desde  están rellenos
					if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
					//Comprobamos que todos los campos de la fecha hasta  están rellenos
					if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		}
				
		    	//Comprobamos que la fecha Desde es numerica
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas Desde y Hasta tienen caracteres no numericos");
	    		try
	    		{
	    		 	new Integer(diaDesde).intValue();
	    		 	new Integer(mesDesde).intValue();
	    		 	new Integer(anioDesde).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		
				//Comprobamos que la fecha Hasta es numerica
				try
	    		{
	    		 	new Integer(diaHasta).intValue();
	    		 	new Integer(mesHasta).intValue();
	    		 	new Integer(anioHasta).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
				//Comprobamos si las fechas son correctas
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es correcta");
		    	boolean fechaDesdeValida = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde, "yyyyMMdd");
		
		    	if (!fechaDesdeValida)
		    		throw new ValidatorException("{informes.crearInformes.fechaDesdeIncorrecta}");
		    	
		    	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Hasta es correcta");
		    	boolean fechaHastaValida = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta, "yyyyMMdd");
		    	
				if (!fechaHastaValida)
					throw new ValidatorException("{informes.crearInformes.fechaHastaIncorrecta}");
		    	
		       	//Comprobamos que la fecha Hasta es mayor o igual que la fecha Desde
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es menor que la fecha Hasta");
				boolean comparacionFecha = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde,anioHasta, mesHasta, diaHasta);
				
				if (!comparacionFecha)
					throw new ValidatorException("{informes.crearInformes.fechasOrdenIncorrectas}");
				
				if(log.isDebugEnabled())log.debug("Comprobamos que los campos dia y mes (de Desde y Hasta) tengan dos digitos");
				
				//Comprobamos si el dia y la hora tienen dos dígitos
				
				if(diaDesde.length() == 1)
					diaDesde = "0" + diaDesde;
					
				if(mesDesde.length() == 1)
					mesDesde = "0" + mesDesde;
				
				if(diaHasta.length() == 1)
					diaHasta = "0" + diaHasta;
				
				if( mesHasta.length() == 1)
					 mesHasta = "0" + mesHasta;
				
				
				//convertimos los datos de fechas Desde y Hasta en un calendar
				
				Calendar fechaDesde = Calendar.getInstance(tz);
				fechaDesde = new GregorianCalendar(new Integer(anioDesde).intValue(), new Integer(mesDesde).intValue() - 1, 
						new Integer(diaDesde).intValue(),00, 00);

				Calendar fechaHasta = Calendar.getInstance(tz);
				fechaHasta = new GregorianCalendar(new Integer(anioHasta).intValue(),
						new Integer(mesHasta).intValue() - 1, new Integer(form.getDiaHasta())
								.intValue(),23, 59);
				
		    	tareaInformeVO.setFechaDesde(fechaDesde);
		    	tareaInformeVO.setFechaHasta(fechaHasta);
		    
			}
			else
				if(log.isDebugEnabled())log.debug("La tarea es periodica y no se pasa fechaDesde y fechaHasta. El servicio lo rellena");
				
			if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeVO");
	    	
	    	//Llamamos al servicio
			if(log.isDebugEnabled())log.debug("LLamamos al servicio");
	    	TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformes(tareaInformeVO);
	    	form.setTareaModificada(tareaRecuperada.getTrabajo());
	 		
	    }
    	catch(ValidatorException e2)
    	{
    		log.error("Se ha producido la siguiente excepcion" + e2);
    		throw e2;
    	}
    	catch(Exception e)
    	{
    		if(log.isDebugEnabled())log.debug("se ha producido una excepcion en cargarInformeFechas" + e);
    		throw new ValidatorException("{errors.crearInformes.general}");
    	}
    	
	}

	
	
	/**
	 * Recoge los valores de la tarea y del informe y llama al metodo que crea una tarea(siempre no periodica) de informe de rango
	 * @param mapping
	 * @param form
	 *            Formulario en el que se guardan los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void crearTareaInformesFechasRango(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.CrearTareaInformesFechasRangoForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();
		
		try{
		
		
			//convertimos los datos de fecha de inicio en un calendar
			Calendar fechaInicio = Calendar.getInstance(tz);
			fechaInicio = new GregorianCalendar(new Integer(anio).intValue(), new Integer(mes).intValue() - 1, 
				new Integer(dia).intValue(), new Integer(hora).intValue(), new Integer(minutos).intValue());
					
			TareaInformesVO tareaInformeVO = new TareaInformesVO();
	    	
			//Recogemos el usuario para mandarlo en el TareaInformesVO
			String usuario = LdapUserDetailsUtils.getUsuario();
			
	    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
	    	tareaInformeVO.setTrabajo(form.getTrabajo());
	    	tareaInformeVO.setTrigger(form.getTrigger());
	    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
	    	tareaInformeVO.setInforme(form.getTipoTarea());
	    	tareaInformeVO.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
	    	tareaInformeVO.setMsgInforme(form.getMsgInforme());
	    	tareaInformeVO.setMsgNoInforme(form.getMsgNoInforme());
	    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
	    	tareaInformeVO.setFormato(form.getFormato());
	    	tareaInformeVO.setFechaInicio(fechaInicio);
			tareaInformeVO.setRango(form.getRango());
	    	tareaInformeVO.setUsuario(usuario);
    		
			if((form.getPeriodicidad()).equalsIgnoreCase("N"))
			{
				
				String diaDesde = form.getDiaDesde();
			 	String mesDesde = form.getMesDesde();
			 	String anioDesde = form.getAnioDesde();
			 	String diaHasta = form.getDiaHasta();
			 	String mesHasta = form.getMesHasta();
			 	String anioHasta = form.getAnioHasta();
			
	    		//Comprobamos si estan vacías las dos fechas(Desde y Hasta), si es asi,  asignaríamos fechas de un rango amplio
			 	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde y Hasta estan vacias");
	    		if(anioDesde.equalsIgnoreCase("") && mesDesde.equalsIgnoreCase("") && diaDesde.equalsIgnoreCase("") && 
	    				anioHasta.equalsIgnoreCase("") && mesHasta.equalsIgnoreCase("") && diaHasta.equalsIgnoreCase(""))
	    		{
	    			if(log.isDebugEnabled())log.debug("El rango de fechas esta vacio asignamos unas por defecto");
	    			form.setAnioDesde("2007");
	    			form.setMesDesde("1");
	    			form.setDiaDesde("1");
	    			form.setAnioHasta("2100");
	    			form.setMesHasta("1");
	    			form.setDiaHasta("1");
	    		}
				else
	    		{
	    		
					//Comprobamos que todos los campos de la fecha desde  están rellenos
					if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
					//Comprobamos que todos los campos de la fecha hasta  están rellenos
					if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		}
				
		    	//Comprobamos que la fecha Desde es numerica
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas Desde y Hasta tienen caracteres no numericos");
	    		try
	    		{
	    		 	new Integer(diaDesde).intValue();
	    		 	new Integer(mesDesde).intValue();
	    		 	new Integer(anioDesde).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		
				//Comprobamos que la fecha Hasta es numerica
				try
	    		{
	    		 	new Integer(diaHasta).intValue();
	    		 	new Integer(mesHasta).intValue();
	    		 	new Integer(anioHasta).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
				//Comprobamos si las fechas son correctas
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es correcta");
		    	boolean fechaDesdeValida = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde, "yyyyMMdd");
		
		    	if (!fechaDesdeValida)
		    		throw new ValidatorException("{informes.crearInformes.fechaDesdeIncorrecta}");
		    	
		    	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Hasta es correcta");
		    	boolean fechaHastaValida = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta, "yyyyMMdd");
		    	
				if (!fechaHastaValida)
					throw new ValidatorException("{informes.crearInformes.fechaHastaIncorrecta}");
		    	
		       	//Comprobamos que la fecha Hasta es mayor o igual que la fecha Desde
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es menor que la fecha Hasta");
				boolean comparacionFecha = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde,anioHasta, mesHasta, diaHasta);
				
				if (!comparacionFecha)
					throw new ValidatorException("{informes.crearInformes.fechasOrdenIncorrectas}");
				
				if(log.isDebugEnabled())log.debug("Comprobamos que los campos dia y mes (de Desde y Hasta) tengan dos digitos");
				
				//Comprobamos si el dia y la hora tienen dos dígitos
				
				if(diaDesde.length() == 1)
					diaDesde = "0" + diaDesde;
					
				if(mesDesde.length() == 1)
					mesDesde = "0" + mesDesde;
				
				if(diaHasta.length() == 1)
					diaHasta = "0" + diaHasta;
				
				if( mesHasta.length() == 1)
					 mesHasta = "0" + mesHasta;
				
				
				//convertimos los datos de fechas Desde y Hasta en un calendar
				
				Calendar fechaDesde = Calendar.getInstance(tz);
				fechaDesde = new GregorianCalendar(new Integer(anioDesde).intValue(), new Integer(mesDesde).intValue() - 1, 
						new Integer(diaDesde).intValue(),00, 00);

				Calendar fechaHasta = Calendar.getInstance(tz);
				fechaHasta = new GregorianCalendar(new Integer(anioHasta).intValue(),
						new Integer(mesHasta).intValue() - 1, new Integer(form.getDiaHasta())
								.intValue(),23, 59);
				
		    	tareaInformeVO.setFechaDesde(fechaDesde);
		    	tareaInformeVO.setFechaHasta(fechaHasta);
		    
			}
			else
				if(log.isDebugEnabled())log.debug("La tarea es periodica y no se pasa fechaDesde y fechaHasta. El servicio lo rellena");
				
			if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeVO");
	    	
	    	//Llamamos al servicio
			if(log.isDebugEnabled())log.debug("LLamamos al servicio");
	    	TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformes(tareaInformeVO);
	    	form.setTareaModificada(tareaRecuperada.getTrabajo());
	 		
	    }
    	catch(ValidatorException e2)
    	{
    		log.error("Se ha producido la siguiente excepcion" + e2);
    		throw e2;
    	}
    	catch(Exception e)
    	{
    		if(log.isDebugEnabled())log.debug("se ha producido una excepcion en cargarInformeFechas" + e);
    		throw new ValidatorException("{errors.crearInformes.general}");
    	}
	}
	
	/**
	 * Recoge los valores de la tarea y del informe y llama al metodo que crea una tarea(siempre no periodica) de informe de usuario
	 * @param mapping
	 * @param form
	 *            Formulario en el que se guardan los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void crearTareaInformesFechaUsuario(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.CrearTareaInformesFechaUsuarioForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();
	 	
		
		try
		{
		
			//convertimos los datos de fecha de inicio en un calendar
			Calendar fechaInicio = Calendar.getInstance(tz);
			fechaInicio = new GregorianCalendar(new Integer(anio).intValue(), new Integer(mes).intValue() - 1, 
				new Integer(dia).intValue(), new Integer(hora).intValue(), new Integer(minutos).intValue());
					
			TareaInformesVO tareaInformeVO = new TareaInformesVO();
	    	
			//Recogemos el usuario para mandarlo en el TareaInformesVO
			String usuario = LdapUserDetailsUtils.getUsuario();
			
			//Comprobamos que el usuario no este vacio
			
			if((form.getUsuarios() == null) || (form.getUsuarios() == "") || (form.getUsuarios().equalsIgnoreCase("#")))
			{
				log.error("Se debe seleccionar al menos un usuario");
				throw new ValidatorException("{informes.crearInformes.usuarioVacio}");
			}
			
			
	    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
	    	tareaInformeVO.setTrabajo(form.getTrabajo());
	    	tareaInformeVO.setTrigger(form.getTrigger());
	    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
	    	tareaInformeVO.setInforme(form.getTipoTarea());
	    	tareaInformeVO.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
	    	tareaInformeVO.setMsgInforme(form.getMsgInforme());
	    	tareaInformeVO.setMsgNoInforme(form.getMsgNoInforme());
	    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
	    	tareaInformeVO.setFormato(form.getFormato());
	    	tareaInformeVO.setFechaInicio(fechaInicio);
			tareaInformeVO.setUsuarioInforme(form.getUsuarios());
	    	tareaInformeVO.setUsuario(usuario);
    		
			if((form.getPeriodicidad()).equalsIgnoreCase("N"))
			{
				String diaDesde = form.getDiaDesde();
			 	String mesDesde = form.getMesDesde();
			 	String anioDesde = form.getAnioDesde();
			 	String diaHasta = form.getDiaHasta();
			 	String mesHasta = form.getMesHasta();
			 	String anioHasta = form.getAnioHasta();
			
	    		//Comprobamos si estan vacías las dos fechas(Desde y Hasta), si es asi,  asignaríamos fechas de un rango amplio
			 	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde y Hasta estan vacias");
	    		if(anioDesde.equalsIgnoreCase("") && mesDesde.equalsIgnoreCase("") && diaDesde.equalsIgnoreCase("") && 
	    				anioHasta.equalsIgnoreCase("") && mesHasta.equalsIgnoreCase("") && diaHasta.equalsIgnoreCase(""))
	    		{
	    			if(log.isDebugEnabled())log.debug("El rango de fechas esta vacio asignamos unas por defecto");
	    			form.setAnioDesde("2007");
	    			form.setMesDesde("1");
	    			form.setDiaDesde("1");
	    			form.setAnioHasta("2100");
	    			form.setMesHasta("1");
	    			form.setDiaHasta("1");
	    		}
				else
	    		{
	    		
					//Comprobamos que todos los campos de la fecha desde  están rellenos
					if(anioDesde.equalsIgnoreCase("") || mesDesde.equalsIgnoreCase("") || diaDesde.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaDesdeCampoVacio}");
	    		
					//Comprobamos que todos los campos de la fecha hasta  están rellenos
					if(anioHasta.equalsIgnoreCase("") || mesHasta.equalsIgnoreCase("") || diaHasta.equalsIgnoreCase(""))
						throw new ValidatorException("{informes.crearInformes.fechaHastaCampoVacio}");
	    		
	    		}
				
		    	//Comprobamos que la fecha Desde es numerica
	    		if(log.isDebugEnabled())log.debug("comprobamos si las fechas Desde y Hasta tienen caracteres no numericos");
	    		try
	    		{
	    		 	new Integer(diaDesde).intValue();
	    		 	new Integer(mesDesde).intValue();
	    		 	new Integer(anioDesde).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha desde no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaDesde}");
	    		}
	    		
				//Comprobamos que la fecha Hasta es numerica
				try
	    		{
	    		 	new Integer(diaHasta).intValue();
	    		 	new Integer(mesHasta).intValue();
	    		 	new Integer(anioHasta).intValue();
	    		 	
	    		}
				catch(Exception e)
	    		{
	    			log.error("Alguno de los campos de la fecha hasta no son números");
	    			throw new ValidatorException("{tareas.eliminarOdes.fechaHasta}");
	    		}
	    		
				//Comprobamos si las fechas son correctas
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es correcta");
		    	boolean fechaDesdeValida = Utiles.validacionFechaDDMMAAAAHHMM(diaDesde, mesDesde, anioDesde, "yyyyMMdd");
		
		    	if (!fechaDesdeValida)
		    		throw new ValidatorException("{informes.crearInformes.fechaDesdeIncorrecta}");
		    	
		    	if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Hasta es correcta");
		    	boolean fechaHastaValida = Utiles.validacionFechaDDMMAAAAHHMM(diaHasta, mesHasta, anioHasta, "yyyyMMdd");
		    	
				if (!fechaHastaValida)
					throw new ValidatorException("{informes.crearInformes.fechaHastaIncorrecta}");
		    	
		       	//Comprobamos que la fecha Hasta es mayor o igual que la fecha Desde
				if(log.isDebugEnabled())log.debug("Se comprueba si la fecha Desde es menor que la fecha Hasta");
				boolean comparacionFecha = Utiles.comparacionFechas(anioDesde, mesDesde, diaDesde,anioHasta, mesHasta, diaHasta);
				
				if (!comparacionFecha)
					throw new ValidatorException("{informes.crearInformes.fechasOrdenIncorrectas}");
				
				if(log.isDebugEnabled())log.debug("Comprobamos que los campos dia y mes (de Desde y Hasta) tengan dos digitos");
				
				//Comprobamos si el dia y la hora tienen dos dígitos
				
				if(diaDesde.length() == 1)
					diaDesde = "0" + diaDesde;
					
				if(mesDesde.length() == 1)
					mesDesde = "0" + mesDesde;
				
				if(diaHasta.length() == 1)
					diaHasta = "0" + diaHasta;
				
				if( mesHasta.length() == 1)
					 mesHasta = "0" + mesHasta;
				
				
				//convertimos los datos de fechas Desde y Hasta en un calendar
				
				Calendar fechaDesde = Calendar.getInstance(tz);
				fechaDesde = new GregorianCalendar(new Integer(anioDesde).intValue(), new Integer(mesDesde).intValue() - 1, 
						new Integer(diaDesde).intValue(),00, 00);

				Calendar fechaHasta = Calendar.getInstance(tz);
				fechaHasta = new GregorianCalendar(new Integer(anioHasta).intValue(),
						new Integer(mesHasta).intValue() - 1, new Integer(form.getDiaHasta())
								.intValue(),23, 59);
				
		    	tareaInformeVO.setFechaDesde(fechaDesde);
		    	tareaInformeVO.setFechaHasta(fechaHasta);
		    
			}
			else
				if(log.isDebugEnabled())log.debug("La tarea es periodica y no se pasa fechaDesde y fechaHasta. El servicio lo rellena");
				
			if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeVO");
	    	
	    	//Llamamos al servicio
			if(log.isDebugEnabled())log.debug("LLamamos al servicio");
	    	TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformes(tareaInformeVO);
	    	form.setTareaModificada(tareaRecuperada.getTrabajo());
	 		
	    }
    	catch(ValidatorException e2)
    	{
    		log.error("Se ha producido la siguiente excepcion" + e2);
    		throw e2;
    	}
    	catch(Exception e)
    	{
    		if(log.isDebugEnabled())log.debug("se ha producido una excepcion en cargarInformeFechas" + e);
    		throw new ValidatorException("{errors.crearInformes.general}");
    	}
	}
	
	
	
	
	/**
	 * Recoge los valores de la tarea y del informe y llama al metodo que crea una tarea de INFORME FEDERADO que no sea Informe Federado por Nivel Agregación
	 * @param mapping
	 * @param form Formulario en el que se guardan los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void crearTareaInformeFederado(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.CrearTareaInformeFederadoForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception 
	{
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();
	 	
	 	

		
		try{
		
		
			//convertimos los datos de fecha de inicio en un calendar
			Calendar fechaInicio = Calendar.getInstance(tz);
			fechaInicio = new GregorianCalendar(new Integer(anio).intValue(), new Integer(mes).intValue() - 1, 
				new Integer(dia).intValue(), new Integer(hora).intValue(), new Integer(minutos).intValue());
			

					
			TareaInformesVO tareaInformeVO = new TareaInformesVO();
	    	
			//Recogemos el usuario para mandarlo en el TareaInformesVO
			String usuario = LdapUserDetailsUtils.getUsuario();
			
			if(log.isDebugEnabled())log.debug("rellenamos los datos del VO desde el formulario");
	    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
	    	tareaInformeVO.setTrabajo(form.getTrabajo());
	    	tareaInformeVO.setTrigger(form.getTrigger());
	    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
	    	tareaInformeVO.setInforme(form.getTipoTarea());
	    	tareaInformeVO.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
	    	tareaInformeVO.setMsgInforme(form.getMsgInforme());
	    	tareaInformeVO.setMsgNoInforme(form.getMsgNoInforme());
	    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
	    	tareaInformeVO.setFormato(form.getFormato());
	    	tareaInformeVO.setFechaInicio(fechaInicio);
	    	tareaInformeVO.setUsuario(usuario);
    		
			//La fechaDesde y fechaHasta es rellenada en la generacion del informe
	    	
	    	if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeVO");
	    	
	    	//Llamamos al servicio
	    	if(log.isDebugEnabled())log.debug("LLamamos al servicio");
	    	TareaInformesVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformesFederado(tareaInformeVO);
	    	form.setTareaModificada(tareaRecuperada.getTrabajo());
	 		
	    }
    	catch(ValidatorException e2)
    	{
    		log.error("Se ha producido la siguiente excepcion" + e2);
    		throw e2;
    	}
    	catch(Exception e)
    	{
    		if(log.isDebugEnabled())log.debug("se ha producido una excepcion en cargarInformeFechas" + e);
    		throw new ValidatorException("{errors.crearInformes.general}");
    	}
    	
	}
	
	public void crearTareaInformeCatalogo(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.crearTarea.CrearTareaInformeCatalogoForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();
		
		String dia = form.getDia();
	 	String mes = form.getMes();
	 	String anio = form.getAnio();
	 	String hora = form.getHora();
	 	String minutos = form.getMinutos();

	 	String diaActualizacion=null;
	 	String mesActualizacion=null;
	 	String anoActualizacion=null;
	 	
	 	//Recogemos la fecha que introduce el usuario si quiere distinguir los ODES nuevos
//	 	if (form.getDiaActualizacion()!=null && form.getDiaActualizacion().length()>0){
	 		diaActualizacion = form.getDiaActualizacion();
//	 	}
//	 	if (form.getMesActualizacion()!=null && form.getMesActualizacion().length()>0){
	 		mesActualizacion = form.getMesActualizacion();
//	 	}
//	 	if (form.getAnoActualizacion()!=null && form.getAnoActualizacion().length()>0){
	 		anoActualizacion = form.getAnoActualizacion();
//	 	}
	 	//Validamos que la fecha introducida sea correcta 
	 	try
			{
	 		//dgonzalezd: Comento este bloque porque no le veo la gracia.
//	 		if (diaActualizacion==null || diaActualizacion.length()==0 || 
//					mesActualizacion==null || mesActualizacion.length()==0 ||
//					anoActualizacion==null || anoActualizacion.length()==0){
//				if (diaActualizacion.length()>0 ||	mesActualizacion.length()>0 ||	anoActualizacion.length()>0){
//					new Integer (diaActualizacion).intValue();
//					new Integer(mesActualizacion).intValue();
//					new Integer(anoActualizacion).intValue();
//				}
//			}
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
				log.error("Alguno de los campos de la fecha no son números:",e);
				diaActualizacion=null;
				mesActualizacion=null;
				anoActualizacion=null;
				//throw new ValidatorException("{tareas.fechaIncorrecta}");
			}
	
			try 
			{
				if(log.isDebugEnabled())log.debug("VALIDAMOS LAS FECHAS");
				
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
	 	

	 	Calendar fechaActualizacion = null;
	 	
	 	
	 	
	 	
		try{
		
		
			//convertimos los datos de fecha de inicio en un calendar
			Calendar fechaInicio = Calendar.getInstance(tz);
			fechaInicio = new GregorianCalendar(new Integer(anio).intValue(), new Integer(mes).intValue() - 1, 
				new Integer(dia).intValue(), new Integer(hora).intValue(), new Integer(minutos).intValue());
			

	//			convertimos los datos de la fecha de actualizacion  en un calendar
			if (diaActualizacion!=null && diaActualizacion.length()>0 && mesActualizacion!=null && mesActualizacion.length()>0 && anoActualizacion!=null && anoActualizacion.length()>0){
				fechaActualizacion = Calendar.getInstance(tz);
				fechaActualizacion = new GregorianCalendar(
		        		new Integer (anoActualizacion).intValue(), 
		        		new Integer (mesActualizacion).intValue() -1,
		        		new Integer (diaActualizacion).intValue(),10,10);
			}
			
			log.info("Generamos el tarea InformeCatalogoVO");
			TareaInformeCatalogoVO tareaInformeVO = new TareaInformeCatalogoVO();
	    	
			//Recogemos el usuario para mandarlo en el TareaInformesVO
			String usuario = LdapUserDetailsUtils.getUsuario();
			
			if(log.isDebugEnabled())log.debug("rellenamos los datos del VO desde el formulario");
	    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
	    	tareaInformeVO.setTrabajo(form.getTrabajo());
	    	//tareaInformeVO.setTrigger(form.getTrigger());
	    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
	    	tareaInformeVO.setInforme(form.getInforme());
	    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
	    	tareaInformeVO.setFechaInicio(fechaInicio);
	    	tareaInformeVO.setUsuario(usuario);
	    	tareaInformeVO.setIdioma(form.getIdioma());
	    	log.debug("fechaActualizacion es "+fechaActualizacion);
	    	if (fechaActualizacion!=null){
	    		tareaInformeVO.setFechaActualizacion(fechaActualizacion);
	    	}
    		
			//La fechaDesde y fechaHasta no hace falta para este informe
	    	
	    	if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeCatalogoVO con idioma "+ form.getIdioma());
	    	
	    	//Llamamos al servicio
	    	if(log.isDebugEnabled())log.debug("Llamamos al servicio");
	    	TareaInformeCatalogoVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformesCatalogo(tareaInformeVO);
	    	form.setTareaModificada(tareaRecuperada.getTrabajo());
	 		
	    }
    	catch(ValidatorException e2)
    	{
    		log.error("Se ha producido la siguiente excepcion" + e2);
    		throw e2;
    	}
    	catch(Exception e)
    	{
    		log.error("se ha producido una excepcion en cargarInformeCatalogo" + e);
    		throw new ValidatorException("{errors.crearInformes.general}");
    	}
	}
	
	
	/**
	 * Carga la fecha actual en los datos de fecha y los pasa por le formulario
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario en el que se guardan los datos de la fecha(dia,
	 *            mes, año)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public final void cargarFechaActual(ActionMapping mapping,
			CargarFechaActualForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (tz == null)
			tz = Utiles.asignarZonaHoraria();

		Calendar fechaHoy = Calendar.getInstance(tz);
		form.setDia(Integer.toString(fechaHoy.get(Calendar.DAY_OF_MONTH)));
		form.setMes(Integer.toString(fechaHoy.get(Calendar.MONTH) + 1));
		form.setAnio(Integer.toString(fechaHoy.get(Calendar.YEAR)));

		
	}
	
	/**
	 * Obtiene los usuarios que hay disponilbes en la base de datos
	 * 
	 * @param mapping
	 * @param form Formulario en el que se guardan los datos 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void obtenerUsuarios(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.ObtenerUsuariosForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		//String idiomaSelected = LdapUserDetailsUtils.getIdiomaBusqueda();
		//LocalizacionIdiomaVO[] localizadorIdioma = this.getSrvBuscadorService().obtenerIdiomasLocalizados(idiomaSelected);
		
		List<UsuarioVO> usuariosList = Arrays.asList(this.getSrvAdminUsuariosService().listarTodosUsuarios());
		if(log.isDebugEnabled())log.debug("se recogen los usuarios de la aplicacion, estos son: " + usuariosList);
		// Rellena el combo de usuarios
		form.setUsuariosBackingList(usuariosList, "usuario", "usuario");
	}
	
	
	
	/**
	 * Obtiene los idiomas disponibles para reindexar, obtenemos los idiomas del agrega.properties
	 * 
	 * @param mapping
	 * @param form Formulario en el que se guardan los datos 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void cargarIdiomaReindexado(org.apache.struts.action.ActionMapping mapping, 
			es.pode.administracion.presentacion.planificador.crearTarea.CargarIdiomaReindexadoForm form, 
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		I18n i18n = I18n.getInstance();
    	
    	//Recogemos el idioma por defecto para mostrar en ese idioma la lista desplegable de idiomas
    	Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	
    	
    	//recogemos un array de objetos con la lista de idiomas
    	es.pode.soporte.i18n.LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	//LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	
    	//sacamos los idiomas del array de objetos y lo asignamos al combo
    	form.setIdiomaBackingList(Arrays.asList(localizacionArray), "idLocalizacion", "nombre");
		
	}


	/**
	 * Obtiene los idiomas disponibles para generar el informe del catalogo, obtenemos los idiomas del agrega.properties
	 * 
	 * @param mapping
	 * @param form Formulario en el que se guardan los datos 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void cargarIdiomaCatalogo(ActionMapping mapping, CargarIdiomaCatalogoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		I18n i18n = I18n.getInstance();
    	
    	//Recogemos el idioma por defecto para mostrar en ese idioma la lista desplegable de idiomas
    	Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
    	
    	
    	//recogemos un array de objetos con la lista de idiomas
    	es.pode.soporte.i18n.LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	//LocalizacionIdiomaVO[] localizacionArray = i18n.obtenerIdiomasBuscablesLocalizados(locale.getLanguage());
    	
    	//sacamos los idiomas del array de objetos y lo asignamos al combo
    	form.setIdiomaBackingList(Arrays.asList(localizacionArray), "idLocalizacion", "nombre");
		
	}

	/**
	 * Crea una tarea del tipo RegenerarImagenes que reindexa los ODEs de todo el repositorio
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario que contiene los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	@Override
	public void crearTareaRegenerarImagenes(ActionMapping mapping, CrearTareaRegenerarImagenesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();

		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(new Integer(form.getAnio()).intValue(), new Integer(form
					.getMes()).intValue() - 1, new Integer(form.getDia()).intValue(), new Integer(form
					.getHora()).intValue(), new Integer(form.getMinutos()).intValue());

			TareaRegenerarImagenesVO tarea = new TareaRegenerarImagenesVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
			// Que se hace con esto?
//			tarea.setMsgReindexado(form.getMsgReindexado());
//			tarea.setMsgNoReindexado(form.getMsgNoReindexado());
//			tarea.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());

			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());

			TareaRegenerarImagenesVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaRegenerarImagemes(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
		
	}


/**	 * Crea una tarea del tipo Generar Sitenaps 	 
 * 	 
 * @param mapping	 
 * @param form	 
 *            Formulario que contiene los datos de la tarea	 
 * @param request	 
 * @param response	 
 * @throws Exception	 
 */	
	public void crearTareaGenerarSitemaps(ActionMapping mapping, CrearTareaGenerarSitemapsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();

		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(new Integer(form.getAnio()).intValue(), new Integer(form
					.getMes()).intValue() - 1, new Integer(form.getDia()).intValue(), new Integer(form
					.getHora()).intValue(), new Integer(form.getMinutos()).intValue());

			TareaGenerarSitemapsVO tarea = new TareaGenerarSitemapsVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
			// Que se hace con esto?
//			tarea.setMsgReindexado(form.getMsgReindexado());
//			tarea.setMsgNoReindexado(form.getMsgNoReindexado());
//			tarea.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());

			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			

			TareaGenerarSitemapsVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaGenerarSitemaps(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
		
	}

	/**
	 * Crea una tarea del tipo Generar RSS 
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario que contiene los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	public void crearTareaLanzarRSS(ActionMapping mapping, CrearTareaLanzarRSSForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (tz == null)
			tz = Utiles.asignarZonaHoraria();

		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(new Integer(form.getAnio()).intValue(), new Integer(form
					.getMes()).intValue() - 1, new Integer(form.getDia()).intValue(), new Integer(form
					.getHora()).intValue(), new Integer(form.getMinutos()).intValue());

			TareaLanzarRSSVO tarea = new TareaLanzarRSSVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
			// Que se hace con esto?
//			tarea.setMsgReindexado(form.getMsgReindexado());
//			tarea.setMsgNoReindexado(form.getMsgNoReindexado());
//			tarea.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());

			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());

			TareaLanzarRSSVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaLanzarRSS(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
		
	}


	/**
	 * Crea una tarea del tipo Despublicar
	 * 
	 * @param mapping
	 * @param form
	 *            Formulario que contiene los datos de la tarea
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void crearTareaDespublicar(ActionMapping mapping, CrearTareaDespublicarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (tz == null)
		tz = Utiles.asignarZonaHoraria();
	
	TareaDespublicarODEsVO tareaRecuperada = null;
	
	try {
		
		boolean valida=true;
		Calendar cal = Calendar.getInstance(tz);
		cal = new GregorianCalendar(new Integer(form.getAnio()).intValue(), new Integer(form
				.getMes()).intValue() - 1, new Integer(form.getDia()).intValue(), new Integer(form
				.getHora()).intValue(), new Integer(form.getMinutos()).intValue());
		  

		
		TareaDespublicarODEsVO tarea = new TareaDespublicarODEsVO();
		tarea.setFechaInicio(cal);
		tarea.setPeriodicidad(form.getPeriodicidad());
		tarea.setTrabajo(form.getTrabajo());
		tarea.setTrigger(form.getTrigger());
		tarea.setTipoTarea(form.getTipoTarea());
		tarea.setDesdePortal(Boolean.FALSE);
		
		
		
		FormFile archivo = form.getArchivo();
		String file = archivo.getFileName();
		//Es obligatorio añadir el fichero
		if (file==null || file.length()==0){
			log.error("Se debe añadir un archivo Excel para la despublicación");
			throw new ValidatorException("{crear.tarea.existeFichero}");
		}
//		Validamos que el fichero sea Excel, extensión xls
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
			String[] ideValidos=identificadoresValidos.toArray(new String[0]);
			tarea.setIdentificadores(ideValidos);
		}
		if (identificadoresValidos.size()==0){
			log.error("Todos los identificadores del archivo no son  válidos");
			throw new ValidatorException("{crear.tarea.identficadoresNoValidos}");
		}
		
        log.info("Identificadores obtenidos");
		
//        fichero=this.leeFichero((File) archivo);
		
		tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		
		tareaRecuperada = this.getSrvPlanificadorService()
		.crearTareaDespublicarODEs(tarea);
		

		
		
		if (tareaRecuperada.getError() != null)
			throw new ValidatorException("{" + tareaRecuperada.getError()
					+ "}");
		form.setTareaModificada(tareaRecuperada.getTrabajo());
		
		
	} catch (ValidatorException e2) 
	{
		log.error("e2 "+e2);
		throw e2;
	} catch(Exception e)
	{
		log.error("Se ha producido el siguiente error al crear la tarea de despublicacion "+e);
		throw new ValidatorException("{tareas.error}");
	}
}
	
private String[] obtenerIdentificadoresDesdeExcel(InputStream inputStream) throws Exception,IOException{
		
		String[] identificadores=null;
		ArrayList<String> listaIdentificadores=new ArrayList<String>();
		POIFSFileSystem fs = null;
        try 

        { 
        	try{
        		//Se abre el fichero Excel 
        		fs =  new POIFSFileSystem(inputStream);
        	}catch (Exception e){
        		 log.error("Error al abrir el fichero."+e); 
        		 throw new ValidatorException ("{tareas.excelNoValido}");
        	}
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
		                HSSFCell cell = row.getCell(0);//getCell((short)0); 
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
        catch(IOException ex) 
        { 
            log.error("Error al leer el fichero."+ex); 
            throw ex;
        } 
        if(log.isDebugEnabled())log.debug("Ejemplo Finalizado. La lista es "+listaIdentificadores); 
        identificadores=listaIdentificadores.toArray(new String[0]);
        return identificadores;

	}


/**	 * Crea una tarea del tipo Generar OaiOre 	 
 * 	 
 * @param mapping	 
 * @param form	 
 *            Formulario que contiene los datos de la tarea	 
 * @param request	 
 * @param response	 
 * @throws Exception	 
 */	
public void crearTareaGenerarOaiOre(ActionMapping mapping, CrearTareaGenerarOaiOreForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	
	if (tz == null) {
		synchronized (response) {
			tz = Utiles.asignarZonaHoraria();
		}
	}

	try {
		Calendar cal = Calendar.getInstance(tz);
		cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
				.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
				.getHora()),Integer.valueOf(form.getMinutos()));

		TareaVO tarea = new TareaVO();
		tarea.setTrabajo(form.getTrabajo());
		tarea.setTrigger(form.getTrigger());
		tarea.setTipoTarea(form.getTipoTarea());
		tarea.setFechaInicio(cal);
		tarea.setPeriodicidad(form.getPeriodicidad());

		tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		

		TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaGenerarOaiOre(tarea);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
	} catch (Exception e) {
		log.error("Error: " + e);
		throw new ValidatorException("{tareas.error}");
	}
	
}


/**	 * Crea una tarea del tipo Generar Informe Federado Nivel Agregacion 	 
 * 	 
 * @param mapping	 
 * @param form	 
 *            Formulario que contiene los datos de la tarea	 
 * @param request	 
 * @param response	 
 * @throws Exception	 
 */	
public void crearTareaInformeFederadoNivelAgregacion(ActionMapping mapping, CrearTareaInformeFederadoNivelAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	if (tz == null)
		tz = Utiles.asignarZonaHoraria();
	
	String dia = form.getDia();
 	String mes = form.getMes();
 	String anio = form.getAnio();
 	String hora = form.getHora();
 	String minutos = form.getMinutos();
 	
 	String mesNivelAgregacion = form.getMesInicioInforme();
 	String anioNivelAgregacion = form.getAnioInicioInforme();
 	
 	//Primero validames las fechas
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

	
	try{
	
	
		//convertimos los datos de fecha de inicio en un calendar
		Calendar fechaInicio = Calendar.getInstance(tz);
		fechaInicio = new GregorianCalendar(Integer.valueOf(anio),Integer.valueOf(mes)- 1, 
			Integer.valueOf(dia),Integer.valueOf(hora),Integer.valueOf(minutos));
		
//		convertimos los datos de fecha de de nivel de agregacion en un calendar
		Calendar fechaNivelAgregacion = Calendar.getInstance(tz);
		fechaNivelAgregacion = new GregorianCalendar(Integer.valueOf(anioNivelAgregacion),Integer.valueOf(mesNivelAgregacion)- 1, 
			1,12,12);
				
		TareaInformesNivelAgregacionFederadaVO tareaInformeVO = new TareaInformesNivelAgregacionFederadaVO();
    	
		//Recogemos el usuario para mandarlo en el TareaInformesVO
		String usuario = LdapUserDetailsUtils.getUsuario();
		
		if(log.isDebugEnabled())log.debug("rellenamos los datos del VO desde el formulario");
    	//recogemos los valores del formulario y los metemos en el VO para lanzar el servicio
    	tareaInformeVO.setTrabajo(form.getTrabajo());
    	tareaInformeVO.setTrigger(form.getTrigger());
    	tareaInformeVO.setTipoTarea(form.getTipoTarea());
    	tareaInformeVO.setInforme(form.getTipoTarea());
    	tareaInformeVO.setMsgDescripcionTrabajo(form.getMsgDescTrabajo());
    	tareaInformeVO.setMsgInforme(form.getMsgInforme());
    	tareaInformeVO.setMsgNoInforme(form.getMsgNoInforme());
    	tareaInformeVO.setPeriodicidad(form.getPeriodicidad());
    	tareaInformeVO.setFormato(form.getFormato());
    	tareaInformeVO.setFechaInicio(fechaInicio);
    	tareaInformeVO.setFechaNivelAgregacion(fechaNivelAgregacion);
    	tareaInformeVO.setUsuario(usuario);
		
		//La fechaDesde y fechaHasta es rellenada en la generacion del informe
    	
    	if(log.isDebugEnabled())log.debug("Despues de rellenar el TareaInformeVO");
    	
    	//Llamamos al servicio
    	if(log.isDebugEnabled())log.debug("LLamamos al servicio");
    	TareaInformesNivelAgregacionFederadaVO tareaRecuperada = this.getSrvPlanificadorService().crearTareaInformesFederadoNivelAgregacion(tareaInformeVO);
    	form.setTareaModificada(tareaRecuperada.getTrabajo());
 		
    }
	catch(ValidatorException e2)
	{
		log.error("Se ha producido la siguiente excepcion" + e2);
		throw e2;
	}
	catch(Exception e)
	{
		if(log.isDebugEnabled())log.debug("se ha producido una excepcion en cargarInformeFechas" + e);
		throw new ValidatorException("{errors.crearInformes.general}");
	}
	
}


@Override
public void crearTareaSubirIndices(ActionMapping mapping,
		CrearTareaSubirIndicesForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {	
	if (tz == null) {
		synchronized (response) {
			tz = Utiles.asignarZonaHoraria();
		}
	}

	try {
		Calendar cal = Calendar.getInstance(tz);
		cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
				.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
				.getHora()),Integer.valueOf(form.getMinutos()));

		TareaVO tarea = new TareaVO();
		tarea.setTrabajo(form.getTrabajo());
		tarea.setTrigger(form.getTrigger());
		tarea.setTipoTarea(form.getTipoTarea());
		tarea.setFechaInicio(cal);
		tarea.setPeriodicidad(form.getPeriodicidad());

		tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		

		TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaSubirIndices(tarea);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
	} catch (Exception e) {
		log.error("Error: " + e);
		throw new ValidatorException("{tareas.error}");
	}
	
}


@Override
public void crearTareaActualizarIndicesRemotos(ActionMapping mapping,
		CrearTareaActualizarIndicesRemotosForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {	
	if (tz == null) {
		synchronized (response) {
			tz = Utiles.asignarZonaHoraria();
		}
	}

	try {
		Calendar cal = Calendar.getInstance(tz);
		cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
				.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
				.getHora()),Integer.valueOf(form.getMinutos()));

		TareaVO tarea = new TareaVO();
		tarea.setTrabajo(form.getTrabajo());
		tarea.setTrigger(form.getTrigger());
		tarea.setTipoTarea(form.getTipoTarea());
		tarea.setFechaInicio(cal);
		tarea.setPeriodicidad(form.getPeriodicidad());

		tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
		

		TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaActualizarIndicesRemotos(tarea);
		form.setTareaModificada(tareaRecuperada.getTrabajo());
	} catch (Exception e) {
		log.error("Error: " + e);
		throw new ValidatorException("{tareas.error}");
	}
	
}

	@Override
	public void crearTareaEstadisticasLocales(ActionMapping mapping,
			CrearTareaEstadisticasLocalesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaEstadisticasLocales(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
		
	}

	@Override
	public void crearTareaEstadisticasTotales(ActionMapping mapping,
			CrearTareaEstadisticasTotalesForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaEstadisticasTotales(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}


	@Override
	public void crearTareaObtenerMetadatosODESFederados(ActionMapping mapping,
			CrearTareaObtenerMetadatosODESFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaObtenerMetadatosODESFederados(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}


	@Override
	public void crearTareaObtenerODESDespublicadosFederados(
			ActionMapping mapping,
			CrearTareaObtenerODESDespublicadosFederadosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaObtenerODESDespublicadosFederados(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}


	@Override
	public void crearTareaObtenerMetadatosODESFederadosFaltantes(
			ActionMapping mapping,
			CrearTareaObtenerMetadatosODESFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaObtenerMetadatosODESFederadosFaltantes(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}		
	}


	@Override
	public void crearTareaObtenerODESDespublicadosFederadosFaltantes(
			ActionMapping mapping,
			CrearTareaObtenerODESDespublicadosFederadosFaltantesForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (tz == null) {
			synchronized (response) {
				tz = Utiles.asignarZonaHoraria();
			}
		}
	
		try {
			Calendar cal = Calendar.getInstance(tz);
			cal = new GregorianCalendar(Integer.valueOf(form.getAnio()), Integer.valueOf(form
					.getMes()) - 1, Integer.valueOf(form.getDia()),Integer.valueOf(form
					.getHora()),Integer.valueOf(form.getMinutos()));
	
			TareaVO tarea = new TareaVO();
			tarea.setTrabajo(form.getTrabajo());
			tarea.setTrigger(form.getTrigger());
			tarea.setTipoTarea(form.getTipoTarea());
			tarea.setFechaInicio(cal);
			tarea.setPeriodicidad(form.getPeriodicidad());
	
			tarea.setUsuario(LdapUserDetailsUtils.getUsuario());
			
			TareaVO tareaRecuperada=this.getSrvPlanificadorService().crearTareaObtenerODESDespublicadosFederadosFaltantes(tarea);
			form.setTareaModificada(tareaRecuperada.getTrabajo());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw new ValidatorException("{tareas.error}");
		}
	}
	
	
}
