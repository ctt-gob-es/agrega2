package es.pode.modificador.negocio.cambios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.modificador.ServiceLocator;
import es.pode.modificador.negocio.cambios.configuracion.ModificadorProperties;
import es.pode.modificador.negocio.servicio.ModificacionVO;
import es.pode.modificador.negocio.servicio.ResultadoModificacionVO;
import es.pode.modificador.negocio.servicio.SrvTareasModificacionServiceImpl;
import es.pode.modificador.negocio.utilidades.EstadosTarea;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.validador.TipoOde;
import es.pode.soporte.zip.ZipDao;



public class TareaCambios {
	

	
	private Logger logger = Logger.getLogger(TareaCambios.class);
	
	private Cambio[] cambios = null;
	
	private ODE[] odes = null;
	
	private ZipDao zipDao=null; 
	private SCORM2004Dao scormDao=null;
	private static final String MANIFEST_NAME="imsmanifest.xml";
	private ModificadorProperties props = null;
	private Appender apender = null;
	
	private boolean destructiva = false;
	private boolean soloValidacion = false;
	
	private static final String VACIA ="";
	private static final String pathInforme ="uploads/modificador/informes";
	private static final String REPORT_EXT=".html";
	
	
	/**
	 * Visibilidad package para evitar que se instancie directamente desde el
	 * servicio. Debe ser instanciada por la FactoriaCambios
	 * 
	 * @param cambios
	 * @param odes
	 */
	TareaCambios(Cambio[] cambios, ODE[] odes) {
		this.setCambios(cambios);
		this.setOdes(odes);
	}
	
	
	/**
	 * @see ejecutarTarea(true)
	 * 
	 * @return El retorno es un array con el resultado de la modificacion de
	 *         cada ODE.
	 */
	public ResultadoModificacionVO[] ejecutarTarea() {
		return ejecutarTarea(false);
	}


	protected final es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService getPropiedadService()
			throws java.lang.Exception {

		String srvPublicacionServiceFile = "importedServices.properties";
		java.io.InputStream srvPublicacionServiceInputStream = SrvTareasModificacionServiceImpl.class
				.getClassLoader()
				.getResourceAsStream(srvPublicacionServiceFile);
		logger.debug("srvPropiedadServiceInputStream = " + srvPublicacionServiceInputStream );
		java.util.Properties srvPublicacionServiceProperties = new java.util.Properties();
		srvPublicacionServiceProperties.load(srvPublicacionServiceInputStream);
		String srvPublicacionServiceEndPointAddress = "";
		srvPublicacionServiceEndPointAddress = (String) srvPublicacionServiceProperties
				.get("srvPropiedadServicePort");
		System.out
				.println("srvPropiedadServiceEndPointAddress del fichero --> "
						+ srvPublicacionServiceEndPointAddress);
		es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadServiceService srvPropiedadService = new es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadServiceServiceLocator();
		logger.debug("srvPropiedadServiceEndPointAddress = " + srvPublicacionServiceEndPointAddress );
		if (srvPublicacionServiceEndPointAddress.length() > 0)
			((es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadServiceServiceLocator) srvPropiedadService)
					.setSrvPropiedadServiceEndpointAddress(srvPublicacionServiceEndPointAddress);
		es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService port = srvPropiedadService
				.getSrvPropiedadService();
		logger.debug("retorno : srvPropiedadService = " + srvPropiedadService );
		return port;

	}
	
	
	private String obtenerId(LomAgrega lomAgrega) throws ParseadorException, Exception {
		
		SrvPropiedadService prop = getPropiedadService();
		String catalogoMec=prop.getValorPropiedad(AgregaProperties.CATALOGO_MEC);
		if(catalogoMec==null || catalogoMec.isEmpty()) {
			return lomAgrega.getGeneralAgrega().getPrimerIdentificador();
		}
		es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega gen=lomAgrega.getGeneralAgrega();

		//recorremos la estructura de Identificadores
		for (int i=0; i<gen.getCountIdentifier(); i++) {

			String catalogo = gen.getCatalogo(i)!=null?gen.getCatalogo(i):"";
			String id = gen.getEntry(i)!=null?gen.getEntry(i):"";
			//Devolvemos el id asignado al catalogo mec
			if (catalogo.equals(catalogoMec) && !id.isEmpty()) 
				return id;
		}
		//Si no se encontro un id asociado al catalogo mec, devolvemos el primero que exista
		return lomAgrega.getGeneralAgrega().getPrimerIdentificador();
	}
	
	
	/**
	 * <p>
	 * Metodo para iniciar la tarea de modificacion. Recorre el array de odes y
	 * por cada ODE ejecuta el array de cambios solicitados.
	 * </p>
	 * <p>
	 * Previamente a la ejecucion de los cambios, se debe:
	 * </p>
	 * 
	 * <ul>
	 * <li>Preparar el fichero de traza para el ode a modificar</li>
	 * <li>generar un backup del ODE</li>
	 * <li>copiar el ODE original a la localizacion de taller, donde se aplican
	 * los cambios</li>
	 * </ul>
	 * <p>
	 * Al finalizar las modificaciones, se debe validar el manifest resultante,
	 * generar el fichero de traza correspondiente y eliminar la localizacion de
	 * taller.
	 * </p>
	 * 
	 * @param simulacion
	 *            Determina si es una ejecucion real o una simulacion para
	 *            validar la tarea. La simulacion no aplica los cambios sobre el
	 *            ODE original.
	 * @return El retorno es un array con el resultado de la modificacion de
	 *         cada ODE.
	 * @throws AlmacenamientoException
	 * @throws ParseadorException
	 */
	public ResultadoModificacionVO[] ejecutarTarea(boolean simulacion)  
	{
	
		Manifest manifestImportado = null;
		EstadosTarea estado=null;
		/*
		 * Si existe el array de odes y al menos tiene un elemento
		 * Si hay al menos un cambio
		 */
		ResultadoModificacionVO[] resultModificacion;
		if(odes!=null && odes.length>0 && cambios!=null && cambios.length>0)
		{
			resultModificacion= new ResultadoModificacionVO[odes.length];
			for(int i=0; i<odes.length;i++)
			{
				//Relleno los datos del ODE para el registro de la tarea
				resultModificacion[i] = new ResultadoModificacionVO();
				resultModificacion[i].setIdOde(odes[i].getPathOriginal());
				resultModificacion[i].setPathTraza(odes[i].getTraza());
				resultModificacion[i].setPathOriginal(odes[i].getPathOriginal());
				resultModificacion[i].setPathBackup(odes[i].getBackup()!=null?odes[i].getBackup():VACIA);
				resultModificacion[i].setPublicado(Boolean.valueOf(odes[i].isPublicado()));
				try
				{
					prepararTarea(odes[i],simulacion);
				}
				catch (Exception e) 
				{
					logger.error("No se ha podido preparar la tarea del ode "+odes[i].getPathOriginal(),e);
					if(logger.isDebugEnabled()) logger.debug(e);
					resultModificacion[i].setStatus(EstadosTarea.ERROR);
					resultModificacion[i].setMsgError(CodigosMensajesError.ERROR_PREPARACION_ODE);
					finalizarTarea(odes[i], false, simulacion, null);
					continue;
				}
				String rutaManifest=null;
//				if(odes[i].getPathTaller().charAt(odes[i].getPathTaller().length()-1)=='/')
				if(odes[i].getPathTaller().endsWith("/"))
				{
					rutaManifest=odes[i].getPathTaller() + MANIFEST_NAME;
				}
				else
				{
					rutaManifest=odes[i].getPathTaller() + "/" + MANIFEST_NAME;
				}
				
				//parseo el manifest
				File rutaXml = new File(rutaManifest);
				try {
						manifestImportado = this.scormDao.parsearODELazy(rutaXml);
						ManifestAgrega manAgrega = new ManifestAgrega(manifestImportado);
						LomAgrega lomAgrega= new LomAgrega(manAgrega.obtenerLom(manifestImportado.getIdentifier(), null));
						// Si he consegido el manifest, recupero el identificador del ODE para registrarlo
						String id=obtenerId(lomAgrega);
						resultModificacion[i].setIdOde(id);
						//Título en idioma de navegación 
						
						String idioma = null;
						if(LdapUserDetailsUtils.estaAutenticado()) {
							idioma = LdapUserDetailsUtils.getIdioma()!=null?LdapUserDetailsUtils.getIdioma():"es";
						}
						
						resultModificacion[i].setTitulo(lomAgrega.getGeneralAgrega().getTitulo(idioma));
					
				} catch (Exception e) {
					logger.error("No se ha podido parsear el ode "+odes[i].getPathOriginal(), e);
					if(logger.isDebugEnabled()) logger.debug(e);
					resultModificacion[i].setStatus(EstadosTarea.ERROR);
					resultModificacion[i].setMsgError(CodigosMensajesError.ERROR_PARSEO_ODE);
					finalizarTarea(odes[i], false, simulacion, null);
					continue;
				}
				
				// Compruebo que el ODE que va a ser modificado no ha sido
				// modificado previamente por una tarea que todavia puede
				// restaurar el backup de ese ODE.
				if(checkearOde(resultModificacion[i].getPathOriginal())) {
					logger.error("El ode " + odes[i].getPathOriginal() + " ha sido modificado por una tarea anterior. Los cambios no se pueden aplicar hasta que se elimine dicha tarea o se restaure la copia de seguridad.");
					resultModificacion[i].setStatus(EstadosTarea.ERROR);
					resultModificacion[i].setMsgError(CodigosMensajesError.ODE_MODIFICADO_POR_OTRA_TAREA);
					finalizarTarea(odes[i], false, simulacion, null);
					continue;
				}
				
				logger.info("Se ha parseado el ode "+odes[i].getPathOriginal());
				boolean resultadoCambios = true;
				for(int j=0; j<cambios.length;j++)
				{
					logger.info("Cambio número " + j );
					// Ejecuto el cambio individual. Si no se ejecuta con exito, pongo el resultado global a false.
					logger.debug("Ruta para este cambio: "+new File(odes[i].getPathTaller()).getAbsolutePath());
					if (!(cambios[j] instanceof InformeGeneral)) {
						if (!cambios[j].ejecutar(manifestImportado, apender,new File(odes[i].getPathTaller()).getAbsolutePath()))
							resultadoCambios = false;
					} else {
						String pathInformeEste=pathInforme+"/"+new Date().getTime()+"/"+i+"/";
						
						File filePath = new File(pathInformeEste);
						if(!filePath.exists()) {
							logger.debug("No existe "+pathInformeEste+", lo creamos");
							filePath.mkdirs();
						}
						
						String identificador="";
						try {
							identificador = new LomAgrega(new ManifestAgrega(
									manifestImportado).obtenerLom(
									manifestImportado.getIdentifier(), null))
									.getGeneralAgrega()
									.getPrimerIdentificador();
						} catch (Exception e) {
							logger.error("No se pudo generar path para informe.",e);
						}
						resultModificacion[i].setPathInforme(pathInformeEste+identificador+REPORT_EXT);
						if (!cambios[j].ejecutar(manifestImportado, apender, pathInformeEste+identificador+REPORT_EXT))
							resultadoCambios = false;
					}
				}
				
				boolean resultadoValidacion;
				
				if (isDestructiva()) {
					try {
						TipoOde tipoOde = new TipoOde();
						tipoOde.obtenerTipoOde(rutaManifest);
						if( ConstantesAgrega.SCORM_04.equals(tipoOde.getTipo()) )
						{
							this.scormDao.escribirODE(manifestImportado, rutaXml);
							
						}else
						{
							logger.error("el ODE que está modificando no es SCORM 2004.");
							resultModificacion[i].setStatus(EstadosTarea.ERROR);
							resultModificacion[i].setMsgError(CodigosMensajesError.ERROR_ESCRITURA_ODE);
							finalizarTarea(odes[i], false, simulacion, null);
							continue;
						}
						
					} catch (Exception e) {
						logger.error("No se ha podido escribir el ode "
								+ odes[i].getPathOriginal());
						if (logger.isDebugEnabled())
							logger.debug(e);
						resultModificacion[i].setStatus(EstadosTarea.ERROR);
						resultModificacion[i]
								.setMsgError(CodigosMensajesError.ERROR_ESCRITURA_ODE);
						finalizarTarea(odes[i], false, simulacion, null);
						continue;
					}
					/*
					 * Ejecuto una validacion para comprobar que los cambios han
					 * generado un ODE valido
					 */
					ValidacionManifest validacion = new ValidacionManifest();
					resultadoValidacion = validacion
							.ejecutar(null, apender, new File(odes[i]
									.getPathTaller()).getAbsolutePath());
				} else {
					resultadoValidacion=true;
					logger.debug("Como no es destructiva, no escribimos cambios ni validamos.");
				}
				
				/*
				 * Finalizo la tarea en funcion del resultado y de si es simulada
				 */
				boolean resultadoFinalizar = finalizarTarea(odes[i], resultadoValidacion, simulacion, resultModificacion[i].getIdOde());
				/*
				 * ESTADO DE LA MODIFICACION:
				 * ERROR - resultadoValidacion = false || resultadoFinalizar = false
				 * WARNING - resultadoValidacion = true && resultadoFinalizar = true && resultadoCambios = false
				 * FINALIZADO - resultadoValidacion = true && resultadoFinalizar = true && resultadoCambios = true
				 */
				if(resultadoValidacion && resultadoFinalizar) {
					if(resultadoCambios) {
						estado = EstadosTarea.FINALIZADA;
					} else {
						estado = EstadosTarea.WARNING;
						resultModificacion[i].setMsgError(CodigosMensajesError.WARNING);
					}
				} else {
					estado = EstadosTarea.ERROR;
					resultModificacion[i].setMsgError((resultadoValidacion?CodigosMensajesError.ERROR_FINALIZACION_ODE:CodigosMensajesError.ERROR_VALIDACION_ODE));
				}
				
				resultModificacion[i].setStatus(estado);
			}
			
			//Si una tarea tenía informeGeneral, lo generamos
//			for(int j=0; j<cambios.length;j++)
//			{
//				if(cambios[j] instanceof InformeGeneral) {
//					//Path en el que se genera informe
//					String pathInforme ="uploads/modificador/informes";
//					
//					/* OJO, cada resultado de modificación está ligado a un ODE, salvo que exista 
//					 * una tarea que implemente esta interfaz, en cuyo caso habrá un resultado más
//					 * por cada modificación de este tipo, que es global.
//					 */
//					ResultadoModificacionVO[] auxiliar = new ResultadoModificacionVO[resultModificacion.length+1];
//					for (int k = 0; k < resultModificacion.length; k++) {
//						auxiliar[k]=resultModificacion[k];
//					}
//					
//					//Esta línea es el equivalente de los anteriores para Java 6
////					resultModificacion=Arrays.copyOf(resultModificacion, resultModificacion.length+1);
//					
//					resultModificacion[resultModificacion.length-1]=new ResultadoModificacionVO();
//					resultModificacion[resultModificacion.length-1].setPathInforme(pathInforme);
//					try {
//						resultModificacion[resultModificacion.length-1].setTraza(leeTraza(pathInforme));
//					} catch (Exception e) {
//						logger.debug("No se ha podido leer la traza del Informe General");
//					}
//					if(!((InformeGeneral)cambios[j]).generar(odes, apender, pathInforme)) {
//						resultModificacion[resultModificacion.length-1].setStatus(EstadosTarea.ERROR);
//						//Concatenamos mensajes
//						ResultadoModificacionVO resultado= resultModificacion[resultModificacion.length-1];
//						StringBuffer msgOriginal = new StringBuffer();
//						if(resultado!=null&&resultado.getMsgError()!=null&&!resultado.getMsgError().equals(VACIA)) {
//							msgOriginal.append(resultado.getMsgError());
//							msgOriginal.append(",");
//						}
//						msgOriginal.append(CodigosMensajesError.ERROR_GENERACION_INFORME);
//						resultModificacion[resultModificacion.length-1].setMsgError(msgOriginal.toString());
//					}
//				}
//			}
			
			
		} else {
			resultModificacion=new ResultadoModificacionVO[]{};
		}
		return resultModificacion;
	}
	
	/**
	 * Lee el contenido del fichero de trazas
	 * @param path Ruta al fichero de trazas
	 * @return Texto del fichero de trazas
	 * @throws Exception Encapsula IOException original
	 */
	private String leeTraza(String path) throws Exception{
		String salida = VACIA;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = in.readLine()) != null) {
				buffer.append(temp).append("\n");
			}
			in.close();
			salida = buffer.toString();
		} catch (IOException e) {
			logger.error("No se encontró traza en "+path+" debido a "+e);
			throw new Exception(e);
		}
		return salida;
	}
	
	private boolean checkearOde(String pathOriginal) {
		boolean result = false;
		ModificacionVO[] modificaciones = ServiceLocator.instance().getSrvHerramientaModificacion().chequearResultadosODE(pathOriginal);
		if(modificaciones!=null && modificaciones.length>0) {
			result = true;
		}
		return result;
	}
	
	private boolean finalizarTarea(ODE ode, boolean resultadoValidacion, boolean simulacion, String identificador) {
		boolean result = false;
		if(resultadoValidacion && !simulacion &&isDestructiva()) {
			logger.info("Aplicando los cambios a la localización original");
			try {
				boolean borrado=true;
				if(ode.isComprimido()) {
					borrado=new File(ode.getPathOriginal()).delete();
					if(!borrado) {
						logger.error("No se pudo borrar el zip original en "+ode.getPathOriginal());
					} else {
						logger.debug("Comprimo "+ode.getPathOriginal()+" en "+ode.getPathTaller());
						getZipDao().comprimir(ode.getPathOriginal(), ode.getPathTaller());
					}
				} else {
					File origen = new File(ode.getPathTaller());
					File destino = new File(ode.getPathOriginal());
					logger.debug("Copio "+ode.getPathTaller()+" en "+ode.getPathOriginal());
					UtilesFicheros.copiar(origen, destino);
				}
				result = borrado;
			} catch (Exception e) {
				logger.error("No se han aplicado los cambios con exito. Restaurando copia de seguridad");
				if(logger.isDebugEnabled()) logger.debug(e);
				restaurar(ode);
			}
		} else {
			if (StringUtils.isNotEmpty(ode.getBackup())) {
				logger.info("Eliminando copia de seguridad");
				borrarLocalizacion(ode.getBackup());
			}
			if(simulacion && resultadoValidacion) result=true;
			if(!isDestructiva() && resultadoValidacion) result=true;
			
		}
		
		//Borraremos del taller únicamente si se trata de una tarea no destructiva
		if(!isDestructiva())
		{
			logger.info("Eliminando los ficheros temporales");
			borrarLocalizacion(ode.getPathTaller());	
		}
		
		apender.close();
		logger.removeAppender(apender);
		return result;
	}
	
	private void restaurar(ODE ode) {
		try {
			if(ode.isComprimido()) {
				if(logger.isDebugEnabled()) logger.debug("Copiando backup de " + ode.getBackup() + " a " + ode.getPathOriginal());
				File origen = new File(ode.getBackup());
				File destino = new File(ode.getPathOriginal());
				UtilesFicheros.copiar(origen, destino);
			} else {
				if(logger.isDebugEnabled()) logger.debug("Descomprimiendo backup " + ode.getBackup() + " en " + ode.getPathOriginal());
				getZipDao().descomprimir(ode.getBackup(), ode.getPathOriginal());
			}
		} catch (Exception e) {
			logger.fatal("No se ha podido restaurar la copia de seguridad");
			if(logger.isDebugEnabled()) logger.debug(e);
		}
	}

	private void borrarLocalizacion(String path) {
		File taller = new File(path);
		try {
			UtilesFicheros.eliminar(taller);
		} catch (Exception e) {
			logger.debug("No se ha podido eliminar la localizaci&oacute;n " + path, e);
		}
	}
	

	
	//Este método configura la información de los logger
	private Appender configurarAppender(String file) throws Exception
	{
		PatternLayout layout = new PatternLayout("%d{dd/MM/yyyy HH:mm:ss} - %p - %m%n");
		FileAppender app = new FileAppender(layout,file);
		app.setThreshold(Level.INFO);
		app.setAppend(true);
		app.setName("informe");
		
		return app;
	}
	
	/**
	 *  <p>Prepara el ODE para ser modificado. Esto incluye las siguientes tareas:</p>
	 *  <ul>
	 *  <li>Copiar el ODE original a la localizacion de taller. Si el ODE esta comprimido, debera descomprimirse</li>
	 *  <li>Generar un backup del ODE original (copiar el original cuando esta comprimido</p>
	 *  </ul>
	 * 
	 * @param ode
	 * @throws Exception 
	 */
	private void prepararTarea(ODE ode,boolean simulacion) throws Exception 
	{
		//		Creo la carpeta Taller
		File taller = new File(ode.getPathTaller()); 
		if (taller.mkdirs()){
			if(logger.isDebugEnabled()) logger.debug("La carpeta taller fue creada");
        }
        else 
        {
        	if(logger.isDebugEnabled()) logger.debug("La carpeta taller no fue creada");
        } 
		apender=configurarAppender(ode.getTraza());
		logger.addAppender(apender);
		//creo la carpeta de bakup y del pathOriginal
		File backup=null;
		if(ode.getBackup()!=null&&!ode.getBackup().equals(VACIA)){
			backup = new File(ode.getBackup()); 
		}
		File pathOriginal=new File(ode.getPathOriginal());
		if(!simulacion) {
	        if (backup!=null&&backup.getParentFile().mkdirs())
	        {
	            if(logger.isDebugEnabled()) logger.debug(" La carpeta backup fue creada");
	        }
	        else 
	        {
	        	if(logger.isDebugEnabled()) logger.debug("La carpeta backup no fue creada");
	        } 
	        /*
	         * Si el ode está comprimido lo copio en el 
	         * backup si no lo comprimo en el backup
	         */
	        String nombreZip = ode.getBackup();
			if (backup!=null&&nombreZip!=null) {
				if (ode.isComprimido()) {
					UtilesFicheros.copiar(pathOriginal, backup);
				} else {
					zipDao.comprimir(nombreZip, ode.getPathOriginal());
				}
			}
			logger.info("Se ha creado copia de seguridad de ode "+ode.getPathOriginal());
		}
		
		
		/*
		 * Copio el ode original en la carpeta taller
		 * si está comprimido lo descomprimo y si no
		 * lo copio directamente
		 */
		
		if(ode.isComprimido())
		{
			zipDao.descomprimir(ode.getPathOriginal(), ode.getPathTaller());
		}
		else
		{
			UtilesFicheros.copiar(pathOriginal, taller);
		}
		
		
	}
	

	/**
	 * @return the cambios
	 */
	public Cambio[] getCambios() {
		return cambios;
	}

	/**
	 * @param cambios the cambios to set
	 */
	public void setCambios(Cambio[] cambios) {
		this.cambios = cambios;
	}

	/**
	 * @return the odes
	 */
	public ODE[] getOdes() {
		return odes;
	}

	/**
	 * @param odes the odes to set
	 */
	public void setOdes(ODE[] odes) {
		this.odes = odes;
	}

	/**
	 * @return the props
	 */
	public ModificadorProperties getProps() {
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(ModificadorProperties props) {
		this.props = props;
	}

	/**
	 * @return the scormDao
	 */
	public SCORM2004Dao getScormDao() {
		return scormDao;
	}

	/**
	 * @param scormDao the scormDao to set
	 */
	public void setScormDao(SCORM2004Dao scormDao) {
		this.scormDao = scormDao;
	}

	/**
	 * @return the zipDao
	 */
	public ZipDao getZipDao() {
		return zipDao;
	}

	/**
	 * @param zipDao the zipDao to set
	 */
	public void setZipDao(ZipDao zipDao) {
		this.zipDao = zipDao;
	}

	public boolean isDestructiva() {
		return destructiva;
	}


	public void setDestructiva(boolean destructiva) {
		this.destructiva = destructiva;
	}


	/**
	 * @return the soloValidacion
	 */
	public boolean isSoloValidacion() {
		return soloValidacion;
	}


	/**
	 * @param soloValidacion the soloValidacion to set
	 */
	public void setSoloValidacion(boolean soloValidacion) {
		this.soloValidacion = soloValidacion;
	}

}
