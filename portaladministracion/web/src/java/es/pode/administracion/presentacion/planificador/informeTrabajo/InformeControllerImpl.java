/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.administracion.presentacion.planificador.informeTrabajo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.planificador.comun.Utiles;
import es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

/**
 * @see es.pode.administracion.presentacion.planificador.informeTrabajo.InformeController
 */
public class InformeControllerImpl extends InformeController {

    private static Logger log = Logger.getLogger(InformeControllerImpl.class);

    ResourceBundle ficheroRecursos = null;

    private Properties pSpringProperties = null;

    Utiles utilidades = new Utiles();

    public final void obtenerInformeTrabajo(ActionMapping mapping, ObtenerInformeTrabajoForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	
		try {
		    Long informe = null;
		    Locale local=new Locale(LdapUserDetailsUtils.getIdioma());
		    ficheroRecursos = ResourceBundle.getBundle("application-resources", local);
	
		    if (form instanceof InformeFormImpl)
			informe = ((InformeFormImpl) form).getId();
		    else
			informe = ((MostrarDescripcionVolverFormImpl) form).getId();
		    RegistroTareaEjecutadaDate[] registroTareasDate = null;
		    //Vamos a calcular para el caso del informe de carga masiva
		    if(log.isDebugEnabled())log.debug("obtenemos el trabajo ejecutado con identificador "+informe);
		    TareaEjecutadaVO tarea = this.getSrvPlanificadorService().obtenerTrabajoEjecutado(informe);
		    if(tarea.getTipoTarea() !=null && !tarea.getTipoTarea().equals("")){
		    	if(log.isDebugEnabled())log.debug("El trabajo es de tipo carga de odes");
		    	RegistroTareaCargaEjecutadaVO[] informeTrabajoCargaMasiva = this.getSrvPlanificadorService().obtenerInformeTrabajoCargaMasiva(informe);
		    	if(log.isDebugEnabled())log.debug("Hay "+informeTrabajoCargaMasiva.length+" registros en la base de datos");
		    	if(informeTrabajoCargaMasiva != null && informeTrabajoCargaMasiva.length>0){
		    		registroTareasDate=cambiarFormatoRegistroTareasCargaMasiva(informeTrabajoCargaMasiva, informe);
		    		form.setInformeTrabajoAsArray(registroTareasDate);
		    	}
		    }
		    else{
		    	RegistroTareaEjecutadaVO[] informeTrabajo = this.getSrvPlanificadorService().obtenerInformeTrabajo(informe);
		    	 if (informeTrabajo != null && informeTrabajo.length>0) {
		    		 registroTareasDate = cambiarFormatoRegistroTareas(informeTrabajo, informe);
		    		 form.setInformeTrabajoAsArray(registroTareasDate);
		    	 }else{//Para el caso de que ejecutemos una tarea vieja que no etnga guardado el tipo de tarea CargaODEs
		    		 RegistroTareaCargaEjecutadaVO[] informeTrabajoCargaMasiva = this.getSrvPlanificadorService().obtenerInformeTrabajoCargaMasiva(informe);
				    	if(log.isDebugEnabled())log.debug("Hay "+informeTrabajoCargaMasiva.length+" registros en la base de datos para la tarea antigua que estamos ejecutando");
				    	if(informeTrabajoCargaMasiva != null && informeTrabajoCargaMasiva.length>0){
				    		registroTareasDate=cambiarFormatoRegistroTareasCargaMasiva(informeTrabajoCargaMasiva, informe);
				    		form.setInformeTrabajoAsArray(registroTareasDate);
				    	}
		    	 }
		    }
		    if(log.isDebugEnabled())log.debug("informeTrabajo.getDescripcion()");

		    /* Cabecera del informe */
		    TareaEjecutadaVO cabecera = this.getSrvPlanificadorService().obtenerTrabajoEjecutado(informe);
	
		    /**
	                 * Recortamos el nombre de la tarea quitandole lo agregado al
	                 * nombre original Lo agregado son dos # seguidas de la fecha en
	                 * la que se ejecuta la tarea La fecha se compone de
	                 * "año+mes+dia+hora+minutos+segundos"
	                 */
		    int posicion = cabecera.getTrabajo().indexOf("!!");
	
		    if (posicion > 0)
			form.setTrabajo(cabecera.getTrabajo().substring(0, posicion));
		    else
			form.setTrabajo(cabecera.getTrabajo());
		    
	
		    form.setDescripcion(cabecera.getDescripcion());
		    if(log.isDebugEnabled())log.debug("cabecera.getDescripcion()" + cabecera.getDescripcion());
		    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
		    
		    
		    if(log.isDebugEnabled())log.debug("cabecera.getFechaInicio()" + cabecera.getFechaInicio());
		    String fechaInicio = formato.format(cabecera.getFechaInicio().getTime()); 
		    form.setFechaInicio(fechaInicio);
		    //log("fecha inicio despues del set" + form.getFechaInicio());
	
		    if (cabecera.getFechaFin() != null)
			form.setFechaFin(formato.format(cabecera.getFechaFin().getTime()));
	
		    
		} catch (Exception e) {
		    log.error("Error: " + e);
		    throw new ValidatorException("{tareas.error}");
		}
    }

    /**
         * Copiamos todas las tareas que vienen en el arrays de tareas recibidas
         * en un nuevo array de tareas cambiandole la fecha a tipo Date en vez
         * de Calendar
         */
    private RegistroTareaEjecutadaDate[] cambiarFormatoRegistroTareas(RegistroTareaEjecutadaVO[] informeTrabajo,
	    Long idTarea) {
    	
		RegistroTareaEjecutadaDate[] registroTareasDate = new RegistroTareaEjecutadaDate[informeTrabajo.length];
	
		for (int i = 0; i < informeTrabajo.length; i++) {
			String descripcion=informeTrabajo[i].getDescripcion();
			String codigo=informeTrabajo[i].getCodigo();
			Date fecha=informeTrabajo[i].getFecha().getTime();
			String estado=informeTrabajo[i].getEstado();
		    registroTareasDate[i] = cambiarFormatoRegistroTarea(descripcion,codigo,fecha,estado, idTarea);
		    if(log.isDebugEnabled())log.debug("informeTrabajo.getDecripcion "+ registroTareasDate[i].getDescripcion());
		}
	
		return registroTareasDate;
    }
    
    /**
     * Copiamos todas las tareas que vienen en el arrays de tareas de carga masiva recibidas
     * en un nuevo array de tareas cambiandole la fecha a tipo Date en vez
     * de Calendar
     */
    private RegistroTareaEjecutadaDate[] cambiarFormatoRegistroTareasCargaMasiva(RegistroTareaCargaEjecutadaVO[] informeTrabajo,
    Long idTarea) {
	
		RegistroTareaEjecutadaDate[] registroTareasDate = new RegistroTareaEjecutadaDate[informeTrabajo.length];
	
		for (int i = 0; i < informeTrabajo.length; i++) {
			String descripcion=informeTrabajo[i].getDescripcion();
			String codigo=informeTrabajo[i].getCodigo();
			Date fecha=informeTrabajo[i].getFecha().getTime();
			String estado=informeTrabajo[i].getEstado();
		    registroTareasDate[i] = cambiarFormatoRegistroTarea(descripcion,codigo,fecha,estado, idTarea);
		    if(log.isDebugEnabled())log.debug("informeTrabajo.getDecripcion "+ registroTareasDate[i].getDescripcion());
		}
	
		return registroTareasDate;
}

    /**
         * Copiamos todos los campos de la tarea recibida y los metemos en la
         * nueva tarea cambiando la fecha a Date
         */
    private RegistroTareaEjecutadaDate cambiarFormatoRegistroTarea(String descripcion, String codigo, Date fecha, String estado, Long idTarea) {
		RegistroTareaEjecutadaDate registroTareaDate = new RegistroTareaEjecutadaDate();
	
//		String descripcion = registroTarea.getDescripcion();
	
		/*
	         * Sustituimos los tabuladores, saltos de linea y simbolos @ y > por
	         * espacio
	         */
		descripcion = descripcion.replace('\r', ' ');
		descripcion = descripcion.replace('\n', ' ');
		descripcion = descripcion.replace('\t', ' ');
		descripcion = descripcion.replace('>', '-');
		descripcion = descripcion.replace('@', '-');
		descripcion = descripcion.replace("\"", "");

		
	
		/* Limitamos el tamaño de la descripción del error */
		if (descripcion.length() > 300)
		    descripcion = descripcion.substring(0, 300);
	
		// Se comprueba el valor del campo codigo para concatenarlo a la
	        // descripcion
	
		if (codigo != null) {
		    Locale locale = new Locale(LdapUserDetailsUtils.getIdioma());
		    ResourceBundle ficheroRecursos = this.getFicherRecursos(locale);
		    
		    //Controlamos que el codigo no este en el fichero de internacionalizacion
		    try
		    {
		    	codigo = ficheroRecursos.getString(codigo);
		    	log.debug("El codigo de la tarea ejecutada es " + codigo);		    	
		    }
		    catch(Exception e)
		    {
		    	codigo = " ";
		    	log.debug("El codigo de la tarea ejecutada no esta internacionalizado");
		    }
		    registroTareaDate.setDescripcion(descripcion + ". " + codigo);
		} else {
		    log.debug("El codigo de la tarea ejecutada es null");
		    registroTareaDate.setDescripcion(descripcion);
		}
		registroTareaDate.setFecha(fecha);
		// registroTareaDate.setId(registroTarea.getId());
		registroTareaDate.setId(idTarea);
	
		if (estado != null) {
		    if (estado.equals("OK"))
			registroTareaDate.setEstado(ficheroRecursos.getString("tareas.OK"));
		    else
			registroTareaDate.setEstado(ficheroRecursos.getString("tareas.ERROR"));
		} else
		    registroTareaDate.setEstado(" ");
	
		return registroTareaDate;
    }
    

    public void cargaIdiomas(ActionMapping mapping,
	    es.pode.administracion.presentacion.planificador.informeTrabajo.CargaIdiomasForm form,
	    HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    
    }

    /**
         * This dummy variable is used to populate the "informeTrabajo" table.
         * You may delete it when you add you own code in this controller.
         */
    private static final java.util.Collection informeTrabajoDummyList = java.util.Arrays.asList(new Object[] {
	    new InformeTrabajoDummy("descripcion-1"), new InformeTrabajoDummy("descripcion-2"),
	    new InformeTrabajoDummy("descripcion-3"), new InformeTrabajoDummy("descripcion-4"),
	    new InformeTrabajoDummy("descripcion-5") });

    /**
         * This inner class is used in the dummy implementation in order to get
         * the web application running without any manual programming. You may
         * delete this class when you add you own code in this controller.
         */
    public static final class InformeTrabajoDummy implements java.io.Serializable {
	private String descripcion = null;

	public InformeTrabajoDummy(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public String getDescripcion() {
	    return this.descripcion;
	}
    }

    public static class RegistroTareaEjecutadaDate {
	private java.lang.String descripcion;

	private java.util.Date fecha;

	private java.lang.Long id;

	private java.lang.String estado;

	private es.pode.planificador.negocio.servicios.TareaEjecutadaVO tarea_ejecutada;

	public RegistroTareaEjecutadaDate() {
	}

	/**
         * Gets the descripcion value for this RegistroTareaEjecutadaVO.
         * 
         * @return descripcion
         */
	public java.lang.String getDescripcion() {
	    return descripcion;
	}

	/**
         * Sets the descripcion value for this RegistroTareaEjecutadaVO.
         * 
         * @param descripcion
         */
	public void setDescripcion(java.lang.String descripcion) {
	    this.descripcion = descripcion;
	}

	/**
         * Gets the fecha value for this RegistroTareaEjecutadaVO.
         * 
         * @return fecha
         */
	public java.util.Date getFecha() {
	    return fecha;
	}

	/**
         * Sets the fecha value for this RegistroTareaEjecutadaVO.
         * 
         * @param fecha
         */
	public void setFecha(java.util.Date fecha) {
	    this.fecha = fecha;
	}

	/**
         * Gets the id value for this RegistroTareaEjecutadaVO.
         * 
         * @return id
         */
	public java.lang.Long getId() {
	    return id;
	}

	/**
         * Sets the id value for this RegistroTareaEjecutadaVO.
         * 
         * @param id
         */
	public void setId(java.lang.Long id) {
	    this.id = id;
	}

	/**
         * Gets the estado value for this RegistroTareaEjecutadaVO.
         * 
         * @return estado
         */
	public java.lang.String getEstado() {
	    return estado;
	}

	/**
         * Sets the estado value for this RegistroTareaEjecutadaVO.
         * 
         * @param estado
         */
	public void setEstado(java.lang.String estado) {
	    this.estado = estado;
	}

	/**
         * Gets the tarea_ejecutada value for this RegistroTareaEjecutadaVO.
         * 
         * @return tarea_ejecutada
         */
	public es.pode.planificador.negocio.servicios.TareaEjecutadaVO getTarea_ejecutada() {
	    return tarea_ejecutada;
	}

	/**
         * Sets the tarea_ejecutada value for this RegistroTareaEjecutadaVO.
         * 
         * @param tarea_ejecutada
         */
	public void setTarea_ejecutada(es.pode.planificador.negocio.servicios.TareaEjecutadaVO tarea_ejecutada) {
	    this.tarea_ejecutada = tarea_ejecutada;
	}

    }

    private ResourceBundle getFicherRecursos(Locale locale) {
	ResourceBundle ficheroRecursos = null;
	ficheroRecursos = ResourceBundle.getBundle("application-resources", locale);
	return ficheroRecursos;
    }
    
}
