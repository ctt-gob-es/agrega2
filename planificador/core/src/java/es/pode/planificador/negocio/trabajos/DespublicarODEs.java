// license-header java merge-point

package es.pode.planificador.negocio.trabajos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.RegistrarTrabajoException;
import es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO;
import es.pode.planificador.negocio.servicios.ResultadoDespublicacionVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorServiceImpl;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;


/**
 * Trabajo de publicaci�n autom�tica de los ODEs en la plataforma.
 * Si la publicaci�n es correcta se mueve al directorio de ficheros cargados correctamente
 * Si la publicaci�n no se lleva acabo se mueve el fichero al directorio ODEs no publicados
 */

public class DespublicarODEs implements Job, InterruptableJob
{
	
	private static Logger logger = Logger.getLogger(DespublicarODEs.class);
	private boolean interrupcion = false;
	private static final String DESPUBLICACION_CORRECTA = "0.0";
	private static final String EXCEPCION_NO_CONTROLADA = "20.1";
	public static final String FILE_NAME_PROPERTIES = "/planificador.properties";

	private static Properties props = null;

	
	
	/**
	 * M�todo de ejecuci�n de la tarea.
	 * Publicaci�n de los ODEs en la plataforma de manera autom�tica.
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{       
		Long idTarea = null;
//		String nombreTarea="";
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		boolean ejecucionIncorrecta = false;
		ArrayList<String> resultadoRegistroOdes = new ArrayList<String>();

		
		/* A�adimos la seguridad al proceso */
		if(logger.isDebugEnabled())logger.debug("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			logger.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		
		/*
		 *	Par�metros propios del trabajo:
		 * 		pathExcel,msgDespublicado, msgNoDespublicado,msgDescTrabajo.
		 * 		
		*/		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
//		String[] identificadores=(String[])parametros.get(0);
		String[] identificadores = (String[]) parametros.get(0);
		String msgDespublicado = (String) parametros.get(1);
		String msgNoDespublicado = (String) parametros.get(2);
		String msgDescTrabajo = (String) parametros.get(3);
		Boolean desdePortal = (Boolean) parametros.get(4);
		Long idTareaCarga = (Long) parametros.get(5);
//		Se guarda el titulo para utilizarlo en la generacion del nombre del informe
//		Se le quitan los espacios del final
//		nombreTarea=msgDescTrabajo.trim();

		int posicionInicial = 0;
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			logger.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}

	
		if(logger.isDebugEnabled())logger.debug("DespublicarODEs: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());
		//Ya tenemos leidos los identificadores que queremos despublicar, no es necesario verificar esto.


		/* Registramos el inicio del trabajo de carga */	

		
		idTarea = Planificador.registrarInicioTarea(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario);
		
		if(logger.isDebugEnabled())logger.debug("Identificador de la tarea: " + idTarea);
		
		/* Recorremos el directorio donde estan los ficheros(ODEs) a despublicar */
		
		
		/*
		ValidadorMEC validador=new 	ValidadorMEC();
		ArrayList<String> identificadoresValidos=new ArrayList();
		ArrayList<String> identificadoresNoValidos=new ArrayList();
		for (int i=0;i<identificadores.length;i++){

			boolean vuelta = validador.validar(identificadores[i]);
			if(vuelta){
				identificadoresValidos.add(identificadores[i]);
			}else{
				log.error("El identificador "+identificadores[i]+" no es valido");
				identificadoresNoValidos.add(identificadores[i]);
			}
		}
		
		if(logger.isDebugEnabled())logger.debug("La longitud de los identificadores validos para despublicar es : " + identificadoresValidos.size());
		*/
		//Paginaremos la llamada al servicio planificador, este �ltimo servicio no paginar� la llamada a publicador.
		//De esta forma evitamos los posibles errores de socket timeout
		String paginacion=SrvPlanificadorServiceImpl.getPropertyValue("numeroPaginacion"); //N�mero de identificadores que se enviar�n para su despublicaci�n en cada p�gina
		double paginaDouble = Double.parseDouble(paginacion);
		double division = (identificadores.length / paginaDouble);
		double floor= java.lang.Math.floor(division);
		int paginacionEntera=Integer.parseInt(paginacion);
		int sueloEntero=(int) floor;
		int m=0;
		String[] identificadoresPaginados=new String[paginacionEntera];

		
		
		//Lo paginamos
		try{
			if(logger.isDebugEnabled())logger.debug("Haremos "+sueloEntero+" llamadas de "+paginacion+" odes cada una");
			for(int j=0;j<floor && sueloEntero>0;j++){
				int k=0;
				for(m=((paginacionEntera)*(j));m<(paginacionEntera*(j+1));m++){
					identificadoresPaginados[k]=identificadores[m];
					k++;
					
				}
				ResultadoDespublicacionVO[] res = ServiceLocator.instance().getSrvPlanificadorService().despublicarPIF(identificadoresPaginados, usuario.toString());
				if(logger.isDebugEnabled())logger.debug("ODEs despublicados "+res.length);
				resultadoRegistroOdes.addAll(this.registrarDatosDespublicacion(res,idTarea,msgDespublicado,msgNoDespublicado,posicionInicial,desdePortal,idTareaCarga));
	
				posicionInicial = posicionInicial + paginacionEntera;
	
			}
			
			if( ((identificadores.length)-(sueloEntero*paginacionEntera))>0){
				int resta=(identificadores.length)-(sueloEntero*paginacionEntera);
				if(logger.isDebugEnabled())logger.debug("Haremos una �ltima llamada con  "+resta+" identificadores");
				String[] identificadoresFaltan=new String[resta];
				int contador=0;
				posicionInicial = (sueloEntero*paginacionEntera);
				if(logger.isDebugEnabled())logger.debug("posicionInicial "+posicionInicial);
				posicionInicial = sueloEntero*paginacionEntera;
				for(int u=(sueloEntero*paginacionEntera);u<identificadores.length;u++){
					 identificadoresFaltan[contador]=identificadores[u];
					 contador++;
				}
				if(logger.isDebugEnabled())logger.debug("Registramos el inicio de despublicacion de los odes");
				ResultadoDespublicacionVO[] resFaltan = ServiceLocator.instance().getSrvPlanificadorService().despublicarPIF(identificadoresFaltan, usuario.toString());
				logger.debug("Odes despublicados "+Arrays.toString(resFaltan));
				resultadoRegistroOdes.addAll(this.registrarDatosDespublicacion(resFaltan,idTarea,msgDespublicado,msgNoDespublicado,posicionInicial,desdePortal,idTareaCarga));
				
			}
		}catch (Exception e) {
			JobExecutionException excepcion = 
				new JobExecutionException("Error: No se ha podido realizar la despublicacion ", e);
		
			logger.error("Error despublicacion " + excepcion);
		
			/* Registramos la hora de finalizaci�n de la tarea incorrecta */			
			Planificador.registrarTareaIncorrecta(idTarea, ConstantesAgrega.TRABAJO_ERRONEO, msgNoDespublicado);
		
			/* Se lanza el error */
			throw excepcion;
		}/* Registramos la hora de finalizaci�n de la tarea y si ha sido correcta/incorrecta la despublicaci�n */
	   
		/*
		 * Comprobamos el resultado de la despublicacion de los odes, si existe uno incorrecto se registra el trabajo como error
		 * Actualizamos la variable ejecucionIncorrecta
		 */
			if(resultadoRegistroOdes.contains(ConstantesAgrega.TRABAJO_ERRONEO))
			{
				if(logger.isDebugEnabled())logger.debug("existe algun ode erroneo");
				ejecucionIncorrecta = true;
			}
		try {
			
			TareaEjecutadaVO trabajoEjecutado = new TareaEjecutadaVO();
			trabajoEjecutado.setId(idTarea);
			trabajoEjecutado.setFechaFin(new GregorianCalendar());
			if(logger.isDebugEnabled())logger.debug("ejecucionIncorrecta vale "+ejecucionIncorrecta);
			if (interrupcion)
				trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_INTERRUMPIDO);
			else if (ejecucionIncorrecta)
				trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
			else{
				trabajoEjecutado.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
			}
			logger.info("Hemos registrado en la tabla de tarea ejecutada en estado "+ trabajoEjecutado.getEstado());
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoFechaFin(trabajoEjecutado);
			
    		
			
		}
		catch (Exception e1) {
			RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido registrar el fin del trabajo", e1);
	    	logger.error(excepcion);
		}
	}	
	
	 /**
     * Este c�digo se llama cuando un usuario interrumpe una tarea
     * @throws UnableToInterruptJobException
     */
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("Se para el trabajo por petici�n del usuario (interrupt)");
        interrupcion = true;
    }

//	private String getPropertyValue(String sKey) {
//		String sReturn = "";
//		try {
//			if (props == null) {
//				InputStream fIsSpringProperties = SrvPlanificadorServiceBase.class
//						.getResourceAsStream(FILE_NAME_PROPERTIES);
//				props = new java.util.Properties();
//				props.load(fIsSpringProperties);
//			}
//			sReturn = props.getProperty(sKey);
//			if(logger.isDebugEnabled())logger.debug("propiedad obtenida: " + sReturn.toString());
//		} catch (IOException e) {
//			log.warn("Excepcion intentando obtener propiedad [" + sKey
//					+ "] del fichero de propiedades del planificador[" + e.getCause() + "]");
//		}
//		// devolvemos la propiedad
//		return sReturn;
//	}
	
	private ArrayList<String> registrarDatosDespublicacion(ResultadoDespublicacionVO[] resultado,Long idTarea,String msgDespublicado,String msgNoDespublicado,int posicionInicial, Boolean desdePortal,Long idTareaCarga)
	{
		String codPublicacion="";
//		File fileOde = null;
        RegistroTareaEjecutadaVO[] registros = new RegistroTareaEjecutadaVO[resultado.length];
        ArrayList<String> resultadoRegistro = new ArrayList<String>();
        if(logger.isDebugEnabled())logger.debug("registrarDatosDespublicacion resultado.length "+resultado.length);
        Collection<String> idesActualizar=new ArrayList<String>();
        for(int i=0;i<resultado.length;i++){
        	
        	try{
        		/* Se comprueba si la tarea ha sido interrumpida */
//        		if(logger.isDebugEnabled())logger.debug("Despublicacion del ODE: " + identificadores[posicionInicial]);
				if(interrupcion) {
					if(logger.isDebugEnabled())logger.debug("Se para la tarea por petici�n del cliente");
					break;
				}
				
/*
            	codPublicacion=resultado[i].getIdResultado();
            	if(resultado[i].getDescripcion() != null && !resultado[i].getDescripcion().equals("")){
            		codPublicacion+= ";"+ resultado[i].getDescripcion();  // concatenamos al codigo de error la descripcion si existe.
            	}
            	String codigoCapado = null;
    			
                if(codPublicacion == null || codPublicacion.equals(""))
                	codigoCapado = "20.1";
                else {
                	int posicion = codPublicacion.indexOf(";");
    				if(posicion == -1)
    					codigoCapado = codPublicacion;
    				else
    					codigoCapado = codPublicacion.substring(0,posicion);
                }
                if(logger.isDebugEnabled())logger.debug("DespublicarPIF: " + codigoCapado);
       */         
                /* Preparaci�n del registro del resultado de la despublicaci�n */
        		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
            	tarea.setId(idTarea);
            	RegistroTareaEjecutadaVO registro = new RegistroTareaEjecutadaVO();
            	registro.setTarea_ejecutada(tarea);
            	registro.setFecha(new GregorianCalendar());
            	registro.setCodigo(resultado[i].getIdResultado()); //Este valor ser� 0.0 u otro c�digo de error
            	//TODO Asociar una fecha new GregorianCalendar
            	/* Publicaci�n correcta */
            	//Vamos a recoger los que se han despublicado correctamente si venimos de portal, para luego actualizar la tebla de registros de carga ejecutada
            	if (resultado[i].getIdResultado().equals(DESPUBLICACION_CORRECTA)) {
                	logger.debug("Registramos que la despublicaci�n ha sido correcta. ODE: " + resultado[i].getIdODE());
                	registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
                	registro.setDescripcion(msgDespublicado + ". " + resultado[i].getIdODE());
                	registros[i]=registro;
                	if(desdePortal){
                		idesActualizar.add(resultado[i].getIdODE());
                	}
 
                	
                }
                else { // Despublicaci�n incorrecta. 
                	logger.info("El ODE: " + resultado[i].getIdODE() + " no es v�lido. C�digo de error: " + codPublicacion);
                	registro.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
                	registro.setDescripcion(resultado[i].getDescripcion() + ". " + resultado[i].getIdODE());
                	registros[i]=registro;
    			
                }
	
    		}catch (Exception e) { // Deber�an ser excepciones enviadas del servicio de despublicaci�n de ODEs

//					String path = "";
					
//					if (fileOde != null || fileOde.getAbsolutePath() != null)
//						path = fileOde.getAbsolutePath();
//					
//					log.error("Error publicando ODE: " + path + " " + e);
					//ejecucionIncorrecta = true;
					
					TareaEjecutadaVO tarea = new TareaEjecutadaVO();            	
					tarea.setId(idTarea);
					RegistroTareaEjecutadaVO registro = new RegistroTareaEjecutadaVO();
					registro.setTarea_ejecutada(tarea);
					registro.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
					registro.setCodigo(EXCEPCION_NO_CONTROLADA);
					registro.setFecha(new GregorianCalendar());
					registros[i]=registro;
			    		
				}
                	
    		posicionInicial++;  
    		resultadoRegistro.add(registros[i].getEstado());
            }

			try {
				// Se registra en la tabla derivada como ha ido la carga de los ODEs
				logger.info("Registramos en la tabla de registros de carga como ha ido la carga de cada ode");
				ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijos(registros);
				//Se hace la llamada al servicio de auditoria para crear el informe
				//ServiceLocator.instance().get
				if(desdePortal && idesActualizar!=null && idesActualizar.size()>0){
					String[] identif=new String[idesActualizar.size()];
					Iterator<String> iter = idesActualizar.iterator();
					int k = 0;
					while (iter.hasNext())
					{
						String g = (iter.next());
						identif[k]=g;
						k++;
					}
					//Vamos a actualizar la tabla registro_tarea_carga_ejecutada--> Insertamos 1 en path_ode_despublicado de los odes despublicados.
					ServiceLocator.instance().getSrvRegistroPlanificadorService().actualizarTrabajoTareaCarga(identif, idTareaCarga);
				}
				
			}
			catch (Exception e1) {
				RegistrarTrabajoException excepcion = 
						new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada " + e1.getMessage(), e1);
	    		logger.error(excepcion);
			}
			return resultadoRegistro;
	}
	
}
