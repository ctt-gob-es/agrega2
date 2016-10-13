// license-header java merge-point

package es.pode.planificador.negocio.trabajos;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.ServiceLocator;
import es.pode.planificador.negocio.comun.CtesPlanificador;
import es.pode.planificador.negocio.comun.Planificador;
import es.pode.planificador.negocio.servicios.ParametroInformeCargaVO;
import es.pode.planificador.negocio.servicios.ParametrosCargaVO;
import es.pode.planificador.negocio.servicios.RegistrarTrabajoException;
import es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO;
import es.pode.planificador.negocio.servicios.ResultadoCargaVO;
import es.pode.planificador.negocio.servicios.SrvPlanificadorServiceImpl;
import es.pode.planificador.negocio.servicios.TareaEjecutadaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


/**
 * Trabajo de publicación automática de los ODEs en la plataforma.
 * Si la publicación es correcta se mueve al directorio de ficheros cargados correctamente
 * Si la publicación no se lleva acabo se mueve el fichero al directorio ODEs no publicados
 */

public class CargaODEs implements Job, InterruptableJob
{

	private static Logger logger = Logger.getLogger(CargaODEs.class);
	private boolean interrupcion = false;
	private static final String PUBLICACION_CORRECTA = "0.0";
	private static final String EXCEPCION_NO_CONTROLADA = "20.1";
	public static final String FILE_NAME_PROPERTIES = "/planificador.properties";
	private static String pathDir = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_INFORMES_CARGA);
//	Los odes se mueven una vez se hayan registrado en la BD
	private ArrayList<File> odesMoverOK=new ArrayList<File>();
	private ArrayList<File> odesMoverKO=new ArrayList<File>();
	
	
	/**
	 * Método de ejecución de la tarea.
	 * Publicación de los ODEs en la plataforma de manera automática.
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException
	{       
		Long idTarea = null;
		String nombreTarea="";
		String usuario = (String) context.getJobDetail().getJobDataMap().get(CtesPlanificador.USUARIO);
		boolean ejecucionIncorrecta = false;
		ArrayList<String> resultadoRegistroOdes = new ArrayList<String>();
		
		/* Añadimos la seguridad al proceso */
		if(logger.isDebugEnabled())logger.debug("Usuario que lanza la tarea: " + usuario);
        boolean contextoCargado = Autenticar.cargarContextoSeguridad(usuario);
		
		if(!contextoCargado) {
			logger.error("ERROR: No han cargado los datos en el contexto de seguridad");
			return;			
		}
		
		/*
		 *	Parámetros propios del trabajo:
		 * 		pathODEs, pathODEsCargados, pathODEsNoCargados
		 * 		msgPublicado, msgNoPublicado,msgDescTrabajo, sobrescribir, descripcionTarea, nombreLote y tipoTarea.
		 * 		
		*/		
		ArrayList parametros = (ArrayList)context.getJobDetail().getJobDataMap().get(CtesPlanificador.PARAMETROS);
		String pathODEs = (String) parametros.get(0);
		String pathODEsCargados = (String) parametros.get(1);
		String pathODEsNoCargados = (String) parametros.get(2);
		String msgPublicado = (String) parametros.get(3);
		String msgNoPublicado = (String) parametros.get(4);
		String msgDescTrabajo = (String) parametros.get(5);
//		Se guarda el titulo para utilizarlo en la generacion del nombre del informe
//		Se le quitan los espacios del final
		nombreTarea=msgDescTrabajo.trim();
		String sobrescribir = (String) parametros.get(6);
		String descripcionTarea = (String) parametros.get(7);
		String nombreLote = (String) parametros.get(8);
		String tipoTarea=(String) parametros.get(9);
		int posicionInicial = 0;
		int posicionFinal = 0;
	
		if(logger.isDebugEnabled())logger.debug("CargaODEs: " + context.getJobDetail().getFullName() + " ejecutandose a las " + new Date());

		/* Comprobamos si existen los directorios de trabajo de la carga de ODEs */  
		File fPathODEs = new File(pathODEs); 
		if (!fPathODEs.exists()) {
			logger.error("Error: El directorio donde se deben encontrar los ODEs no existe o no se puede acceder a él " 
					+ fPathODEs.getAbsolutePath());
			JobExecutionException excepcion = new JobExecutionException("Error: No se ha podido registrar. El directorio de los ODEs no existe: " 
					+ fPathODEs.getAbsolutePath());
			throw excepcion;
		}

		File fpathODEsCargados = new File(pathODEsCargados); 
		if (!fpathODEsCargados.exists()) {
			logger.error("Error: El directorio donde se deben mover los ODEs cargados no existe o no se puede acceder a él " 
					+ fPathODEs.getAbsolutePath());
			JobExecutionException excepcion = 
					new JobExecutionException("Error: No se ha podido registrar. No exste el directorio donde se deben moven los ODEs publicados: " 
					+ fpathODEsCargados.getAbsolutePath());
			throw excepcion;
		}

		File fpathODEsNoCargados = new File(pathODEsNoCargados); 
		if (!fpathODEsNoCargados.exists()) {
			logger.error("Error: El directorio donde se deben mover los ODEs no cargados no existe o no se puede acceder a él " 
					+ fpathODEsNoCargados.getAbsolutePath());
			JobExecutionException excepcion = 
				new JobExecutionException("Error: El directorio donde se deben mover los ODEs no cargados no existe o no se puede acceder a él " 
				+ fpathODEsNoCargados.getAbsolutePath());
			throw excepcion;
		}
		
		try {
			if(Planificador.tareaEjecutandose(context.getJobDetail().getGroup(), msgDescTrabajo, usuario)) return;
		} catch (Exception e) {
			logger.error("Error al revisar si la tarea la esta ejecutando algun planificador.",e);
		}

		/* Registramos el inicio del trabajo de carga */		
		
		idTarea = Planificador.registrarInicioTareaCarga(context.getJobDetail().getName(), 
				context.getJobDetail().getGroup(), msgDescTrabajo, usuario,descripcionTarea,nombreLote, pathODEs, tipoTarea,pathODEsCargados,pathODEsNoCargados);
		
		if(logger.isDebugEnabled())logger.debug("Identificador de la tarea: " + idTarea);
		
		/* Recorremos el directorio donde estan los ficheros(ODEs) a publicar */
		
		
		
		if(logger.isDebugEnabled())logger.debug("pathODEs "+pathODEs);
		String[] odes = UtilesFicheros.obtenerOdesDePath(pathODEs,true,true);
		if(logger.isDebugEnabled())logger.debug("Carga de los ODEs de longitud : " + odes.length);
		
		//Paginaremos la llamada al servicio planificador, este último servicio no paginará la llamada a publicador.
		//De esta forma evitamos los posibles errores de socket timeout
		String paginacion=SrvPlanificadorServiceImpl.getPropertyValue("numeroPaginacion"); //Número de odes que se enviarán para su publicación en cada página
		double paginaDouble = Double.parseDouble(paginacion);
		double division = (odes.length / paginaDouble);
		double floor= java.lang.Math.floor(division);
		int paginacionEntera=Integer.parseInt(paginacion);
		int sueloEntero=(int) floor;
		int m=0;
		String[] odesPaginados=new String[paginacionEntera];
//		ArrayList<ResultadoCargaVO> todosOdes=new ArrayList();
		File fileOde = null;
//		Long[] idRegistroTareaCarga = null;
		
		
		//Lo paginamos
		if(logger.isDebugEnabled())logger.debug("Haremos "+sueloEntero+" llamadas de "+paginacion+" odes cada una");
		for(int j=0;j<floor && sueloEntero>0;j++){
			int k=0;
			for(m=((paginacionEntera)*(j));m<(paginacionEntera*(j+1));m++){
				odesPaginados[k]=odes[m];
				k++;
				
			}
			ResultadoCargaVO[] res = ServiceLocator.instance().getSrvPlanificadorService().publicarPIF(odesPaginados, usuario.toString(), msgDescTrabajo, sobrescribir, pathODEs);
			if(logger.isDebugEnabled())logger.debug("Registramos los odes cargados");
			resultadoRegistroOdes.addAll(this.registrarDatosCarga(res,idTarea,msgPublicado,pathODEsCargados,msgNoPublicado,pathODEsNoCargados,posicionInicial,odes,odesMoverOK,odesMoverKO));
			logger.debug("odesMoverOK "+odesMoverOK);
			logger.debug("odesMoverOK.size() "+odesMoverOK.size());
			//Movemos los odesOK
			for(int n=0;n<odesMoverOK.size();n++)
			{
				fileOde = odesMoverOK.get(n);
				try {
					logger.debug("Fichero: " + pathODEsCargados + File.separator + fileOde.getName()); 

					File ficheroCargado = new File(pathODEsCargados + File.separator + fileOde.getName());
				
					if (ficheroCargado.exists()) {
						logger.warn("Ya existe un fichero con ese nombre: " + ficheroCargado.getAbsolutePath() + " en la carpeta de cargados. Se elimina para mover el nuevo fichero");
						ficheroCargado.delete();
					}
				
					boolean mov = fileOde.renameTo(ficheroCargado);													
					if (!mov)
						logger.error("El fichero no se ha podido mover: " + fileOde.getAbsolutePath());					
				}
			
				catch (Exception e2) {
					RegistrarTrabajoException excepcion = 
						new RegistrarTrabajoException("Error: No se ha podido mover el ODE cargado al directorio de ODEs publicados " 
						+ fileOde.getAbsolutePath(), e2);
					logger.error(excepcion);
				}
				fileOde = null;
			}
			logger.debug("odesMoverKO "+odesMoverKO);
			logger.debug("odesMoverKO.size() "+odesMoverKO.size());
			//Movemos los odesKO
			for(int p=0;p<odesMoverKO.size();p++)
			{
				fileOde = odesMoverKO.get(p);
				try {
					logger.debug("Fichero no publicado: " + pathODEsNoCargados + File.separator + fileOde.getName()); 
					
					File ficheroNoCargado = new File(pathODEsNoCargados + File.separator + fileOde.getName());
					
					if (ficheroNoCargado.exists()) {
						logger.warn("Ya existe un fichero con ese nombre: " + ficheroNoCargado.getAbsolutePath() + " en la carpeta de no cargados. Se elimina para mover el nuevo fichero");
						ficheroNoCargado.delete();
					}
						
					boolean mov = fileOde.renameTo(ficheroNoCargado);
					
					if (!mov)
						logger.error("El fichero no se ha podido mover: " + fileOde.getAbsolutePath());					
				}
				catch (Exception e2) {
					RegistrarTrabajoException excepcion = 
							new RegistrarTrabajoException("Error: No se ha podido mover el ODE no publicado " 
							+ fileOde.getAbsolutePath(), e2);
					logger.error(excepcion);
				}
				fileOde = null;
			}
			
			
			posicionInicial = posicionInicial + paginacionEntera;
			
			odesMoverOK=new ArrayList<File>();
			odesMoverKO=new ArrayList<File>();
			
		}
		
		if( ((odes.length)-(sueloEntero*paginacionEntera))>0){
			int resta=(odes.length)-(sueloEntero*paginacionEntera);
			if(logger.isDebugEnabled())logger.debug("Haremos una última llamada con  "+resta+" odes");
			String[] odesFaltan=new String[resta];
			int contador=0;
			posicionInicial = (sueloEntero*paginacionEntera);
			if(logger.isDebugEnabled())logger.debug("posicionInicial "+posicionInicial);
			posicionInicial = sueloEntero*paginacionEntera;
			for(int u=(sueloEntero*paginacionEntera);u<odes.length;u++){
				 odesFaltan[contador]=odes[u];
				 contador++;
			}
		//	idRegistroTareaCarga = this.registrarInicioCarga(odesPaginados,idTarea);
			if(logger.isDebugEnabled())logger.debug("Registramos el inicio de carga de los odes");
			ResultadoCargaVO[] resFaltan = ServiceLocator.instance().getSrvPlanificadorService().publicarPIF(odesFaltan, usuario.toString(), msgDescTrabajo, sobrescribir, pathODEs);
			logger.debug("resFaltan.length "+resFaltan.length);
			resultadoRegistroOdes.addAll(this.registrarDatosCarga(resFaltan,idTarea,msgPublicado,pathODEsCargados,msgNoPublicado,pathODEsNoCargados,posicionInicial,odes,odesMoverOK,odesMoverKO));
			
			//Movemos los odesOK
			logger.debug("odesMoverOK "+odesMoverOK);
			logger.debug("odesMoverOK.size() "+odesMoverOK.size());
			for(int n=0;n<odesMoverOK.size();n++)
			{
				fileOde = odesMoverOK.get(n);
				try {
					logger.debug("Fichero: " + pathODEsCargados + File.separator + fileOde.getName()); 

					File ficheroCargado = new File(pathODEsCargados + File.separator + fileOde.getName());
				
					if (ficheroCargado.exists()) {
						logger.warn("Ya existe un fichero con ese nombre: " + ficheroCargado.getAbsolutePath() + " en la carpeta de cargados. Se elimina para mover el nuevo fichero");
						ficheroCargado.delete();
					}
				
					boolean mov = fileOde.renameTo(ficheroCargado);													
					if (!mov)
						logger.error("El fichero no se ha podido mover: " + fileOde.getAbsolutePath());					
				}
			
				catch (Exception e2) {
					RegistrarTrabajoException excepcion = 
						new RegistrarTrabajoException("Error: No se ha podido mover el ODE cargado al directorio de ODEs publicados " 
						+ fileOde.getAbsolutePath(), e2);
					logger.error(excepcion);
				}
				fileOde = null;
			}
			
			//Movemos los odesKO
			logger.debug("odesMoverKO "+odesMoverKO);
			logger.debug("odesMoverKO.size() "+odesMoverKO.size());
			for(int p=0;p<odesMoverKO.size();p++)
			{
				fileOde = odesMoverKO.get(p);
				try {
					logger.debug("Fichero no publicado: " + pathODEsNoCargados + File.separator + fileOde.getName()); 
					
					File ficheroNoCargado = new File(pathODEsNoCargados + File.separator + fileOde.getName());
					
					if (ficheroNoCargado.exists()) {
						logger.warn("Ya existe un fichero con ese nombre: " + ficheroNoCargado.getAbsolutePath() + " en la carpeta de no cargados. Se elimina para mover el nuevo fichero");
						ficheroNoCargado.delete();
					}
						
					boolean mov = fileOde.renameTo(ficheroNoCargado);
					
					if (!mov)
						logger.error("El fichero no se ha podido mover: " + fileOde.getAbsolutePath());					
				}
				catch (Exception e2) {
					RegistrarTrabajoException excepcion = 
							new RegistrarTrabajoException("Error: No se ha podido mover el ODE no publicado " 
							+ fileOde.getAbsolutePath(), e2);
					logger.error(excepcion);
				}
			}
					
			
			odesMoverOK=new ArrayList<File>();
			odesMoverKO=new ArrayList<File>();
			
		}

	//Llamada al método privado registrarDatosCarga
    //Tratamos el codigo para que solo contenga el primero si tuviera mas de uno
        
			try{
//				Se hace la llamada al servicio de auditoria para crear el informe
				if(logger.isDebugEnabled())logger.debug("Vamos a hacer la llamada a auditoria para que genere el informe birt");
				ParametroInformeCargaVO parametroInformeCargaVO=new ParametroInformeCargaVO();
				parametroInformeCargaVO.setNombreInforme("informeCarga.rptdesign");
				parametroInformeCargaVO.setPathDestinoInforme(pathDir);
				if(logger.isDebugEnabled())logger.debug("El informe de carga lo tiene que dejar en la carpeta "+ parametroInformeCargaVO.getPathDestinoInforme());
				//Reemplazo el espacio en blanco por _
				nombreTarea=nombreTarea.replaceAll(" ", "_");
				
				logger.debug("El nombre del informe será "+ nombreTarea);
				parametroInformeCargaVO.setNombreFicheroInforme(nombreTarea);
				ParametrosCargaVO[] parametroVOs = new ParametrosCargaVO[2];
				parametroVOs[0] =  new ParametrosCargaVO();
				parametroVOs[0].setNombreParametro("RP_idTarea");
				parametroVOs[0].setValorAtributo(idTarea.toString());
				parametroVOs[1] =  new ParametrosCargaVO();
				parametroVOs[1].setNombreParametro("RP_UrlFicha");
				parametroVOs[1].setValorAtributo("http://"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO)+SrvPlanificadorServiceImpl.getPropertyValue("urlFicha"));
				if(logger.isDebugEnabled())logger.debug("El identificador de la tarea es "+parametroVOs[0].getValorAtributo());
				parametroInformeCargaVO.setParametros(parametroVOs);

				
				if (parametroInformeCargaVO.getNombreFicheroInforme()!=null && parametroVOs[0].getValorAtributo()!=null){
					if(logger.isDebugEnabled())logger.debug("Vamos a hacer la llamada al planificador siendo el nombre del fichero y el valor del atributo no nulos");
					ServiceLocator.instance().getSrvPlanificadorService().crearInformeCargaMasiva(parametroInformeCargaVO);
					if(logger.isDebugEnabled())logger.debug("Se ha generado el informe");
				}else{
					logger.error("Para crear el informe hace falta un nombre de tarea y un idTarea");
				}
			}catch (Exception e2) {
				RegistrarTrabajoException excepcion = 
					new RegistrarTrabajoException("Error: No se ha podido generar el informe de carga " + e2.getMessage(), e2);
    		logger.error(excepcion);
		}
		/* Registramos la hora de finalización de la tarea y si ha sido correcta/incorrecta la publicación */
	   
		/*
		 * Comprobamos el resultado de la carga de los odes, si existe uno incorrecto se registra el trabajo como error
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
			trabajoEjecutado.setDescripcionTarea(descripcionTarea);
			trabajoEjecutado.setNombreLote(nombreLote);
			trabajoEjecutado.setPathCarga(pathODEs);
			trabajoEjecutado.setTipoTarea(tipoTarea);
			trabajoEjecutado.setPathCargaKO(pathODEsNoCargados);
			trabajoEjecutado.setPathCargaOK(pathODEsCargados);
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
     * Este código se llama cuando un usuario interrumpe una tarea
     * @throws UnableToInterruptJobException
     */
    public void interrupt() throws UnableToInterruptJobException {
        logger.info("Se para el trabajo por petición del usuario (interrupt)");
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
	
	private ArrayList<String> registrarDatosCarga(ResultadoCargaVO[] resultado,Long idTarea,String msgPublicado,String pathODEsCargados,String msgNoPublicado,String pathODEsNoCargados,int posicionInicial,String[] odes,ArrayList<File> odesMoverOK, ArrayList<File> odesMoverKO)
	{
		String codPublicacion="";
		File fileOde = null;
        RegistroTareaCargaEjecutadaVO[] registros = new RegistroTareaCargaEjecutadaVO[resultado.length];
        ArrayList<String> resultadoRegistro = new ArrayList<String>();
        if(logger.isDebugEnabled())logger.debug("registrarDatosCarga resultado.length "+resultado.length);
        
        for(int i=0;i<resultado.length;i++){
        	
        	try{
        		/* Se comprueba si la tarea ha sido interrumpida */
        		if(logger.isDebugEnabled())logger.debug("Carga del ODE: " + odes[posicionInicial]);
				if(interrupcion) {
					if(logger.isDebugEnabled())logger.debug("Se para la tarea por petición del cliente");
					break;
				}
				
				fileOde = new File(odes[posicionInicial]);
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
                if(logger.isDebugEnabled())logger.debug("PublicarPIF: " + codigoCapado);
                
                /* Preparación del registro del resultado de la publicación */
        		TareaEjecutadaVO tarea = new TareaEjecutadaVO();
            	tarea.setId(idTarea);
            	RegistroTareaCargaEjecutadaVO registro = new RegistroTareaCargaEjecutadaVO();
            	registro.setTarea_carga_ejecutada(tarea);
            	registro.setFecha(new GregorianCalendar());
            	registro.setCodigo(codigoCapado);
            	/* Publicación correcta */
            	if (codigoCapado.equals(PUBLICACION_CORRECTA)) {
                	logger.debug("Registramos que la publicación ha sido correcta. ODE: " + fileOde.getName());
                	registro.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);	
                	
                	registro.setNivelAgregacion(resultado[i].getNivelAgregacion());
                	registro.setNombreZip(resultado[i].getNombreZip());
                	registro.setPathOde(resultado[i].getPathZip());
                	registro.setTitulo(resultado[i].getTituloODE());
                	registro.setId_mec(resultado[i].getIdODE());
                	registro.setIdioma(resultado[i].getIdioma());
                	registro.setRutaTaxonomica(resultado[i].getRutaTaxonomica());
                	registro.setDescripcion(msgPublicado);
                	if(resultado[i].getSobrescrito().equals("s"))
                		registro.setSobrescrito(true);
                	else{
                		registro.setSobrescrito(false);
                	}
                	registro.setFormato(resultado[i].getFormato());
                	registro.setPathOdeDespublicado(false);
                	//TODO Publicacion tiene que devolver los odes similares
                	registro.setOdesSimilares(resultado[i].getOdesSimilares());
                	registros[i]=registro;
                	if(logger.isDebugEnabled())logger.debug("Recogemos los datos de vuelta (registrarDatosCarga), la descripcion ["+registro.getDescripcion()+"] el idioma ["+registro.getIdioma()+ "] el nivelDeAgregacion ["+registro.getNivelAgregacion() +"], la listas de formatos ["+registro.getFormato()+"] y las rutas taxonomicas ["+registro.getRutaTaxonomica() +"] ode: ["+ registro.getId_mec() +"] con titulo: [" + registro.getTitulo() +"] y odes similares ["+ registro.getOdesSimilares()+"]");
               	
    				/* Se mueve el ODE publicado al directorio correspondiente */
                               	
                	odesMoverOK.add(fileOde);
                	
                }
                else { // Publicación incorrecta. El ODE no pasa la validación
                	logger.info("El ODE: " + fileOde.getName() + " no es válido. Código de error: " + codPublicacion);
                	registro.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
                	registro.setNombreZip(resultado[i].getNombreZip());
                	registro.setPathOde(resultado[i].getPathZip());
                	// vamos a recoger el error de publicacion
                	if (codPublicacion!=null&&codPublicacion.indexOf(";") != -1)
                		msgNoPublicado = codPublicacion.substring(codPublicacion.indexOf(";")+1);

                	registro.setCodigo(codigoCapado);
    				//ejecucionIncorrecta = true;
    				if(msgNoPublicado.length()>2000){
    					msgNoPublicado=msgNoPublicado.substring(0, 1999);
    				}
    				registro.setDescripcion(msgNoPublicado);
    				registro.setPathOdeDespublicado(false);
    				registros[i]=registro;
    			
    				odesMoverKO.add(fileOde);
                }
	
    		}catch (Exception e) { // Deberían ser excepciones enviadas del servicio de publicación de ODEs

					String path = "";
					
					if (fileOde != null && fileOde.getAbsolutePath() != null)
						path = fileOde.getAbsolutePath();
					
					logger.error("Error publicando ODE: " + path + " " + e);
					//ejecucionIncorrecta = true;
					
					TareaEjecutadaVO tarea = new TareaEjecutadaVO();            	
					tarea.setId(idTarea);
					RegistroTareaCargaEjecutadaVO registro = new RegistroTareaCargaEjecutadaVO();
					registro.setTarea_carga_ejecutada(tarea);
					registro.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
					registro.setCodigo(EXCEPCION_NO_CONTROLADA);
					registro.setFecha(new GregorianCalendar());
					registros[i]=registro;
			    		
					/* Se mueve el ODE no publicado al directorio correspondiente */
					
					odesMoverKO.add(fileOde);
				}
                	
    		posicionInicial++;  
    		resultadoRegistro.add(registros[i].getEstado());
            }

        if(logger.isDebugEnabled())logger.debug("registrarDatosCarga odesMoverOK"+odesMoverOK);  
        if(logger.isDebugEnabled())logger.debug("registrarDatosCarga odesMoverKO"+odesMoverKO);  
			try {
				// Se registra en la tabla derivada como ha ido la carga de los ODEs
				logger.info("Registramos en la tabla de registros de carga como ha ido la carga de cada ode");
				ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoHijoCarga(registros);
				//Se hace la llamada al servicio de auditoria para crear el informe
				//ServiceLocator.instance().get
				
			}
			catch (Exception e1) {
				RegistrarTrabajoException excepcion = 
						new RegistrarTrabajoException("Error: No se ha podido registrar la tarea derivada " + e1.getMessage(), e1);
	    		logger.error(excepcion);
			}
			return resultadoRegistro;
	}
	
}
