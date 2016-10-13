package es.pode.planificador.negocio.comun;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.TareaVO;


//TODO Mover métodos a AbstractJob
public class Planificador {

	private static Logger log = Logger.getLogger(Planificador.class);
	
	/** 
     *  Método para recuperar la agenda del planificador 
     * */
    public static Scheduler getAgenda() throws Exception
    {   	
		SchedulerFactory sFac = new StdSchedulerFactory(CtesPlanificador.QUARTZ_PROPERTIES);
		Scheduler laAgenda = sFac.getScheduler();
		
		return laAgenda;
    }   
    
    /** Función para recuperar la fecha actual en el formato AAAAMMDDHHMMSS 
     *  Retorna: un String con la fecha en este formato AAAAMMDDHHMMSS
     * */
    public static String getFechaAAAAMMDDHHMMSS() {

    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	return format.format(new Date());
    }

    /** Función para recuperar la fecha actual en el formato AAAAMMDDHHMMSSM 
     *  Retorna: un String con la fecha en este formato AAAAMMDDHHMMSSM
     * */
    public static String getFechaAAAAMMDDHHMMSSMIL() {
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssS");
    	return format.format(new Date());
    }
    
    /** Función para recuperar la fecha actual en el formato AAMMDDHHMMSS 
     *  Retorna: un String con la fecha en este formato AAMMDDHHMMSS

     * */
    public static String getFechaAAMMDDHHMMSS() {
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
    	return format.format(new Date());
    	
    }
    
    /**
     *  Partimos una cadena por el punto (.) y devolvemos el ultimo trozo
     *  Útil para recuperar el nombre de una clase 
     * @param cadena a partir
     * @return último trozo
     */
    public static String ultimoCampo(String cadena) {
    	
    	String[] cadenas = cadena.split("\\.");
    	return cadenas[cadenas.length-1];
    }
    
    /**
     *  Se registra el inicio de una tarea 
     * @param trabajo Nombre de la tarea a ejecutar
     * @param grupoTrabajo Grupo de la tarea a ejecutar
     * @param descripcion Descripción de la tarea
     * @param usuario Usuario que ha creado la tarea    
     * @return idTarea El identificador de la tarea registrada
     */
	public static Long registrarInicioTarea (String trabajo, String grupoTrabajo, String descripcion, String usuario) 
	{  
		Long idTarea = null;
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("Trabajo registro: " + trabajo);
				log.debug("GrupoTrabajo registro" + grupoTrabajo);
				log.debug("Descripcion registro: " + descripcion);
				log.debug("Usuario registro: " + usuario);				
			}
		
			TareaEjecutadaVO tarea = new TareaEjecutadaVO();
			tarea.setDescripcion(descripcion);
			tarea.setTrabajo(trabajo);
			tarea.setGrupoTrabajo(grupoTrabajo);
			tarea.setFechaInicio(new GregorianCalendar());
			tarea.setUsuario(usuario);
		
			idTarea = ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajo(tarea);
			
			if (log.isDebugEnabled())
				log.debug("idTarea: " + idTarea);
		}
		catch (Exception e) {
			log.error("No se ha podido registrar el inicio de la tarea " + e);
		}
		
		return idTarea;
	}
	
    /**
     *  Se registra que la tarea ha sido incorrecta 
     * @param idTarea El identificador de la tarea registradaidTarea El identificador de la tarea registrada
     * @param estado Estado a registrar
     * @param descripcion Descripción de la tarea
     */
	public static void registrarTareaIncorrecta(Long idTarea, String estado, String descripcion) 
	{	
		RegistroTareaEjecutadaVO registro = null;
		TareaEjecutadaVO tarea = null;		
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("idTarea registro: " + idTarea);
				log.debug("Estado registro" + estado);
				log.debug("Descripcion registro: " + descripcion);			
			}
			
			tarea = new TareaEjecutadaVO();
			tarea.setId(idTarea);
			
			registro = new RegistroTareaEjecutadaVO();
			registro.setTarea_ejecutada(tarea);
			registro.setFecha(new GregorianCalendar());
			registro.setEstado(estado);	
			registro.setDescripcion(descripcion);
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijo(registro);
		}
		catch (Exception e1) {
			log.error("Error: No se ha podido registrar la tarea derivada ");
		}
		
		/* Registramos la hora de finalización de la tarea incorrecta */
		try {
			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
			trabajoEjecutado.setId(idTarea);
			trabajoEjecutado.setFechaFin(new GregorianCalendar());
			trabajoEjecutado.setEstado(descripcion);
			
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
		}
		catch (Exception e1) {
			log.error("Error: No se ha podido registrar el fin del trabajo");
		}
	}	
	
	public static Date fechaDia(Date fch, int dias) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTimeInMillis(fch.getTime());
	     cal.add(Calendar.DATE, dias);
	     
	     return new Date(cal.getTimeInMillis());
	}
	
	public static Date fechaSemanaDesde(Date fch, int sem) {
		int dia;
	    Calendar cal = new GregorianCalendar();
	    cal.setTimeInMillis(fch.getTime());
	    cal.add(Calendar.DATE, sem * 7);
	     
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) // Si es domingo
			dia = 7;
		else
			dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
	     
		cal.add(Calendar.DATE, - dia + 1);
	     
	    return new Date(cal.getTimeInMillis());
	}
	
	public static Date fechaSemanaHasta(Date fch, int sem) {
		int dia;
	    Calendar cal = new GregorianCalendar();
	    cal.setTimeInMillis(fch.getTime());	     
	    cal.add(Calendar.DATE, sem * 7);
	     
	    if (cal.get(Calendar.DAY_OF_WEEK) == 1) // Si es domingo
	    	dia = 7;
	    else
	    	dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    
	     cal.add(Calendar.DATE, 7 - dia);	     
	     
	     return new Date(cal.getTimeInMillis());
	}
	
	public static Date fechaMesDesde(Date fch, int meses) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTimeInMillis(fch.getTime());
	     cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + meses, 1);
	     
	     return new Date(cal.getTimeInMillis());
	}

	public static Date fechaMesHasta(Date fch, int meses) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTimeInMillis(fch.getTime());	     
	     cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + meses, 1);
	     cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));	     

	     return new Date(cal.getTimeInMillis());
	}
	
	public static Date fechaAnualDesde(Date fch, int anios) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTimeInMillis(fch.getTime());
	     cal.set(cal.get(Calendar.YEAR) + anios, Calendar.JANUARY, 1);
	     return new Date(cal.getTimeInMillis());
	}
	
	public static Date fechaAnualHasta(Date fch, int anios) {
	     Calendar cal = new GregorianCalendar();
	     cal.setTimeInMillis(fch.getTime());
	     cal.set(cal.get(Calendar.YEAR) + anios, Calendar.DECEMBER, 31);
	     return new Date(cal.getTimeInMillis());
	}
	
	 /**
     *  Se registra el inicio de una tarea de carga masiva
     * @param trabajo Nombre de la tarea a ejecutar
     * @param grupoTrabajo Grupo de la tarea a ejecutar
     * @param descripcion Descripción de la tarea
     * @param usuario Usuario que ha creado la tarea 
     * @param descripcionTarea Descripción de la tarea a ejecutar
     * @param nombreLote Nombre del lote de carga
     * @param pathCarga El path de la carga
     * @param tipoTarea El tipo de tarea que se va a ejecutar   
     * @param pathCargaOK El path donde se dejan los odes que se han publicado bien
     * @param pathCargaKO El path donde se dejan los odes que no han podido ser publicados
     * @return idTarea El identificador de la tarea registrada
     */
	public static Long registrarInicioTareaCarga (String trabajo, String grupoTrabajo, String descripcion, String usuario,String descripcionTarea, String nombreLote,String pathCarga, String tipoTarea,String pathCargaOK, String pathCargaKO) 
	{  
		Long idTarea = null;
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("Trabajo registro: " + trabajo);
				log.debug("GrupoTrabajo registro" + grupoTrabajo);
				log.debug("Descripcion registro: " + descripcion);
				log.debug("DescripcionTarea registro: " + descripcionTarea);		
				log.debug("NombreLote registro: " + nombreLote);		
				log.debug("PathCarga registro: " + pathCarga);		
				log.debug("TipoTarea registro: " + tipoTarea);		
				log.debug("Usuario registro: " + usuario);		
			}
		
			TareaEjecutadaVO tarea = new TareaEjecutadaVO();
			tarea.setDescripcion(descripcion);
			tarea.setTrabajo(trabajo);
			tarea.setGrupoTrabajo(grupoTrabajo);
			tarea.setFechaInicio(new GregorianCalendar());
			tarea.setUsuario(usuario);
			tarea.setDescripcionTarea(descripcionTarea);
			tarea.setNombreLote(nombreLote);
			tarea.setPathCarga(pathCarga);
			tarea.setTipoTarea(tipoTarea);
			tarea.setPathCargaKO(pathCargaKO);
			tarea.setPathCargaOK(pathCargaOK);
		
			idTarea = ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajo(tarea);
			
			if (log.isDebugEnabled())
				log.debug("idTarea: " + idTarea);
		}
		catch (Exception e) {
			log.error("No se ha podido registrar el inicio de la tarea " + e);
		}
		
		return idTarea;
	}
	
	
    /**
     * Metodo que comprueba si la tarea que recibe como argumento esta ejecutandose o no.
     * Este metodo se incluyo para proporcionar alta disponibilidad. En alta disponibilidad
     * hay varios JBoss operando sobre las tablas de quartz y por lo tanto puede haber varios
     * planificadores intentando ejecutar una misma tarea a la vez o una tarea que ya se esta 
     * ejecutando. Este metodo se usa para evitar que se lance una tarea que ya se esta ejecutando.
     * 
     * Para esta tarea no podemos usar las primitivas de quartz ya que al tener diferentes instancias
     * de quartz usando las mismas tablas, intentaran ejecutar a la vez las mismas tareas. Es por eso 
     * que antes de revisar la lista de tareas en ejecucion se introduce una pausa que DEBE SER
     * DIFERENTE PARA CADA INSTANCIA DESPLEGADA DEL PLANIFICADOR. De esta forma cuando se intenten ejecutar 
     * a la vez dos tareas, como cada planificador esperara un tiempo diferente, se dara cuenta de 
     * que otro quartz esta ejecutando la tarea.
     * 
     * En alta disponibilidad podemos tener varias instancias de JBoss
     * - en distintas maquinas: en este caso lo normal es que el primer quartz que llegue consuma la tarea 
     *   y los demas no intentan ejecutar nada.
     * - en la misma maquina: en este caso pueden lanzarse los metodos execute a la vez.
     * 
     * @return false si la tarea que recibe como parametro no esta ejecutandose y true en caso contrario.
     */    
    public static Boolean tareaEjecutandose (String grupoTrabajo, String descripcion, String usuario) throws Exception {
    	String homeJboss;
    	String timeoutPlanificador="";
    	
		//https://community.jboss.org/wiki/JBossProperties
		homeJboss = System.getProperty("jboss.server.home.dir");
    	
		try {
    		timeoutPlanificador = System.getProperty("planificador.timeout");
		} catch (Exception e) {
			log.error("No se pudo recuperar el parametro 'planificador.timeout'. Por favor asegurese que en el arranque de JBoss o en el de la JVM se especifica su valor con el flag '-Dplanificador.timeout'.",e);
		}
    	
    	int timeout=0;
		try {
			timeout = Integer.valueOf(timeoutPlanificador);
		} catch (Exception e) {
			log.error("El valor dado al timeout del planificador no es numerico. Debe ser un numero entero positivo.",e);
		}
		if(timeout<0) {
			log.error("El valor dado al timeout del planificador es menor que 0. Debe ser un numero entero positivo.");
			throw new Exception("El valor dado al timeout del planificador es menor que 0. Debe ser un numero entero positivo.");
		}

		log.debug("El planificador del JBoss '"+ homeJboss +"' intenta ejecutar una tarea. Datos de la tarea:\n" +
				"- Trabajo: " + descripcion + "\n" +
				"- Usuario: " + usuario + "\n" +
				"- Grupo trabajo: " + grupoTrabajo);		
				
		//Recorremos los trabajos que se estan ejecutando para ver si alguno de ellos es el que desea lanzarse
        Thread.sleep(timeout);		
		TareaVO[] tareas = ServiceLocator.instance().getSrvPlanificadorService().obtenerTareasEnEjecucion();
		
		for (int i=0; i<tareas.length; i++) {
			
	   		//  Recortamos el nombre de la tarea quitandole lo agregado al nombre original
	   		//  ya que esa es la descripcion de la tarea.
	   		//  Lo agregado son dos ! seguidas de la fecha en la que se ejecuta la tarea
	   		//  La fecha se compone de "año+mes+dia+hora+minutos+segundos"  
	   		// Lo que en la interfaz web llama nombre, se guarda en la DB como descripcion.
	   		 
			String nombre = tareas[i].getTrabajo();
	    	int posicion = nombre.indexOf("!!");
	    	String descripcionTarea = "";
	    	if (posicion > 0)
	    		descripcionTarea = nombre.substring(0, posicion);
	    	else 
	    		descripcionTarea = nombre;
	    	
	    	if(descripcionTarea.contentEquals(descripcion) &&
	    			tareas[i].getGrupoTrabajo().contentEquals(grupoTrabajo) &&
	    			tareas[i].getUsuario().contentEquals(usuario)) {

	    		log.debug("El planificador del JBoss '"+ homeJboss +"' intenta ejecutar una tarea que ya esta en ejecucion. Datos de la tarea:\n" +
	    				"- Trabajo: " + descripcion + "\n" +
	    				"- Usuario: " + usuario + "\n" +
	    				"- Grupo trabajo: " + grupoTrabajo);		
	    		return true;			
	    	}
		}
/*
		//Recorremos los trabajos que se han ejecutado para ver si alguno de ellos es el que desea lanzarse
		TareaEjecutadaVO[] tareasEjecutadas = ServiceLocator.instance().getSrvPlanificadorService().obtenerTrabajosEjecutados();
		
		for (int i=0; i<tareasEjecutadas.length; i++) {
			String nombre = tareasEjecutadas[i].getTrabajo();
	    	int posicion = nombre.indexOf("!!");
	    	String descripcionTarea = "";
	    	if (posicion > 0)
	    		descripcionTarea = nombre.substring(0, posicion);
	    	else 
	    		descripcionTarea = nombre;

	    	if(descripcionTarea.contentEquals(descripcion) &&
	    			tareasEjecutadas[i].getGrupoTrabajo().contentEquals(grupoTrabajo) &&
	    			tareasEjecutadas[i].getUsuario().contentEquals(usuario)) {

	    		log.debug("El planificador del JBoss '"+ homeJboss +"' intenta ejecutar una tarea que ya se ha ejecutado. Datos de la tarea:\n" +
	    				"- Trabajo: " + descripcion + "\n" +
	    				"- Usuario: " + usuario + "\n" +
	    				"- Grupo trabajo: " + grupoTrabajo);		
	    		return true;			
	    	}
		}
*/		
		log.debug("El planificador del JBoss '"+ homeJboss +"' ejecutara una nueva tarea. Datos de la tarea:\n" +
				"- Trabajo: " + descripcion + "\n" +
				"- Usuario: " + usuario + "\n" +
				"- Grupo trabajo: " + grupoTrabajo);		
		return false;
    }
    
    
}
