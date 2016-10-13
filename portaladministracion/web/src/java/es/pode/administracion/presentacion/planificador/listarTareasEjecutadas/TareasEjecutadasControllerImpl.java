// license-header java merge-point
package es.pode.administracion.presentacion.planificador.listarTareasEjecutadas;


import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;


public class TareasEjecutadasControllerImpl extends TareasEjecutadasController
{
	private static Logger log = Logger.getLogger(TareasEjecutadasControllerImpl.class);
	ResourceBundle ficheroRecursos = null;
	
	/**
	 * metodo que obtiene los trabajos ejecutados que se han seleccionado para ser eliminados
	 */
	public final void obtenerIdsTrabajos(org.apache.struts.action.ActionMapping mapping, es.pode.administracion.presentacion.planificador.listarTareasEjecutadas.ObtenerIdsTrabajosForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		try {
			
			List lista = ((ListarEliminarFormImpl)form).getIdRowSelection();
			
		    if (lista == null) 
		    {
		    	throw new ValidatorException("{tareas.errorEliminar}");
		    }
			form.setIds(lista);
		    
		} 
		catch (ValidatorException validator) 
		{
		    log.error("validator eception : " + validator);
		    throw validator;
		} 
		catch (Exception e) 
		{
		    log.error("Se produce una excepcion ", e);
		    throw new ValidatorException("{tareas.error}");
		}
	}
	
	
	/**
	 * metodo que obtiene los trabajos que ya estan ejecutados
	 */
    public final void obtenerTrabajosEjecutados(ActionMapping mapping, ObtenerTrabajosEjecutadosForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	try 
    	{
    		Locale local=new Locale(LdapUserDetailsUtils.getIdioma());
    		ficheroRecursos = ResourceBundle.getBundle("application-resources", local);
       		
    		TareaEjecutadaVO[] tareas = this.getSrvPlanificadorService().obtenerTrabajosEjecutados();
    		
    		TareaEjecutadaDate[] tareasDate = null;
    		
    		if (tareas != null) {
    			tareasDate = cambiarFormatoTareas(tareas);
    			tareasDate = ordenaDescendente(tareasDate);
    			((ListarTareasEjecutadasFormImpl)form).setTareasEjecutadasAsArray(tareasDate);
    		}
    		    		
    	}
    	catch (Exception e)
    	{
    		log.error("Error: " + e);
    		throw new ValidatorException("{tareas.error}");
    	}
    }
    
    
    /** Copiamos todas las tareas que vienene en el arrays de tareas recibidas en un nuevo array 
     * array de tareas cambiandole la fecha a tipo Date en vez de Calendar
     * */
    private TareaEjecutadaDate[] cambiarFormatoTareas(TareaEjecutadaVO[] tareas) 
    {

    	TareaEjecutadaDate[] tareasDate = new TareaEjecutadaDate[tareas.length];
    	
		for(int i=0;i<tareas.length;i++)
		{
			tareasDate[i]=cambiarFormatoTarea(tareas[i]);
		}

		return tareasDate;
    }
    
    
    /** Copiamos todos los campos de la tarea recibida y los metemos en la nueva tarea cambiando 
     * la fecha a Date
     * */
    private TareaEjecutadaDate cambiarFormatoTarea(TareaEjecutadaVO tarea) 
    {

    	TareaEjecutadaDate tareaDate = new TareaEjecutadaDate();
    	
    	tareaDate.setDescripcion(tarea.getDescripcion());
    	if (tarea.getFechaFin() != null)
    			tareaDate.setFechaFin(tarea.getFechaFin().getTime());
    	if (tarea.getFechaInicio() != null)
    		tareaDate.setFechaInicio(tarea.getFechaInicio().getTime());
    	tareaDate.setGrupoTrabajo(tarea.getGrupoTrabajo());
    	tareaDate.setId(tarea.getId());
    	
    	if (tarea.getEstado() != null)
    	{    		
	    	if (tarea.getEstado().equals("OK")){
	    		
	    		tareaDate.setEstado(ficheroRecursos.getString("tareas.OK"));
	    	}
	    	else if (tarea.getEstado().equals("ERROR")){
	    		tareaDate.setEstado(ficheroRecursos.getString("tareas.ERROR"));
	    	}
	    	else if(tarea.getEstado().equals("INTERRUMPIDO")){
	    		tareaDate.setEstado(ficheroRecursos.getString("tareas.INTERRUMPIDO"));
	    	}
    	}
    	else 
    		tareaDate.setEstado(" ");
    	
    	/**
   		 * Recortamos el nombre de la tarea quitandole lo agregado al nombre original
   		 * Lo agregado son dos # seguidas de la fecha en la que se ejecuta la tarea
   		 * La fecha se compone de "año+mes+dia+hora+minutos+segundos"
   		 * */
    	int posicion = tarea.getTrabajo().indexOf("!!");
    	
    	if (posicion > 0)
    		tareaDate.setTrabajo(tarea.getTrabajo().substring(0, posicion));
    	else 
    		tareaDate.setTrabajo(tarea.getTrabajo());
    	
    	//tareaDate.setTrabajo(tarea.getTrabajo());
    	tareaDate.setUsuario(tarea.getUsuario());
    	
		return tareaDate;
    }

    public static class TareaEjecutadaDate  {
    	
        /* Descripcion de una tarea ejecutada (trabajo) */
        private java.lang.String descripcion;

        private java.lang.String trabajo;

        private java.lang.String grupoTrabajo;

        private java.util.Date fechaInicio;

        private java.lang.Long id;

        private java.util.Date fechaFin;

        private java.lang.String usuario;

        private java.lang.String estado;

        public TareaEjecutadaDate() {
        }

        /**
         * Gets the descripcion value for this TareaEjecutadaVO.
         * 
         * @return descripcion   * Descripcion de una tarea ejecutada (trabajo)
         */
        public java.lang.String getDescripcion() {
            return descripcion;
        }


        /**
         * Sets the descripcion value for this TareaEjecutadaVO.
         * 
         * @param descripcion   * Descripcion de una tarea ejecutada (trabajo)
         */
        public void setDescripcion(java.lang.String descripcion) {
            this.descripcion = descripcion;
        }


        /**
         * Gets the trabajo value for this TareaEjecutadaVO.
         * 
         * @return trabajo
         */
        public java.lang.String getTrabajo() {
            return trabajo;
        }


        /**
         * Sets the trabajo value for this TareaEjecutadaVO.
         * 
         * @param trabajo
         */
        public void setTrabajo(java.lang.String trabajo) {
            this.trabajo = trabajo;
        }


        /**
         * Gets the grupoTrabajo value for this TareaEjecutadaVO.
         * 
         * @return grupoTrabajo
         */
        public java.lang.String getGrupoTrabajo() {
            return grupoTrabajo;
        }

        
        /**
         * Sets the grupoTrabajo value for this TareaEjecutadaVO.
         * 
         * @param grupoTrabajo
         */
        public void setGrupoTrabajo(java.lang.String grupoTrabajo) {
            this.grupoTrabajo = grupoTrabajo;
        }

        public java.lang.String getEstado() {
            return estado;
        }

        public void setEstado(java.lang.String estado) {
            this.estado = estado;
        }
        
        /**
         * Gets the fechaInicio value for this TareaEjecutadaVO.
         * 
         * @return fechaInicio
         */
        public java.util.Date getFechaInicio() {
            return fechaInicio;
        }


        /**
         * Sets the fechaInicio value for this TareaEjecutadaVO.
         * 
         * @param fechaInicio
         */
        public void setFechaInicio(java.util.Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }


        /**
         * Gets the id value for this TareaEjecutadaVO.
         * 
         * @return id
         */
        public java.lang.Long getId() {
            return id;
        }


        /**
         * Sets the id value for this TareaEjecutadaVO.
         * 
         * @param id
         */
        public void setId(java.lang.Long id) {
            this.id = id;
        }


        /**
         * Gets the fechaFin value for this TareaEjecutadaVO.
         * 
         * @return fechaFin
         */
        public java.util.Date getFechaFin() {
            return fechaFin;
        }


        /**
         * Sets the fechaFin value for this TareaEjecutadaVO.
         * 
         * @param fechaFin
         */
        public void setFechaFin(java.util.Date fechaFin) {
            this.fechaFin = fechaFin;
        }


        /**
         * Gets the usuario value for this TareaEjecutadaVO.
         * 
         * @return usuario
         */
        public java.lang.String getUsuario() {
            return usuario;
        }


        /**
         * Sets the usuario value for this TareaEjecutadaVO.
         * 
         * @param usuario
         */
        public void setUsuario(java.lang.String usuario) {
            this.usuario = usuario;
        }
    }
    /**
	 * Obtiene una array de Value Objets ordenados 
	 * @param  array Array de Value Objects que se quiere ordenar
	 * @return TareaDate[] Array con los Value Object ordenados
	 * @throws Exception
	 */
    //TODO Ver si es mejor usar un Comparator
	private TareaEjecutadaDate[] ordenaDescendente(TareaEjecutadaDate[] array)
	{
		TareaEjecutadaDate tmp;
		int i, j, pos_max;
		int N = array.length;
		for (i = 0; i < N - 1; i++)
		{
			//Mayor elemento del vector
			pos_max = i;
			for (j = i + 1; j < N; j++)
			{
				if (array[j].getFechaInicio().after(array[pos_max].getFechaInicio()))
					pos_max = j;
			}
			//coloca el maximo en la posicion i
			tmp = array[i];
			array[i] = array[pos_max];
			array[pos_max] = tmp;
		}
		
		return array;
	}
}