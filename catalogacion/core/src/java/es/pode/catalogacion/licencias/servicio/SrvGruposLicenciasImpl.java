// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.catalogacion.licencias.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.Unmarshaller;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencia;
import es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.LicenciaAdicional;
import es.pode.catalogacion.licencias.dominio.compatibilidadLicencias.Licencias;
import es.pode.catalogacion.licencias.dominio.gruposLicencias.CodigoLicencia;
import es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupo;
import es.pode.catalogacion.licencias.dominio.gruposLicencias.Grupos;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;



/**
 * @see es.pode.catalogacion.licencias.servicio.SrvGruposLicencias
 */

public class SrvGruposLicenciasImpl
    extends es.pode.catalogacion.licencias.servicio.SrvGruposLicenciasBase
{
	
	private static org.apache.log4j.Logger log = Logger.getLogger(SrvGruposLicenciasImpl.class);
	private static Grupos grupos=null;
	private static Licencias licencias=null;
	private static AgregaProperties properties=null;
	private static String rutaLocalGrupos="";
	private static String rutaLocalCompatibilidad="";
	private static String identificadorVocabulario="6.2";
	private static String idiomaTerminos="en";
	private final static String MANIFEST_NAME = "imsmanifest.xml";
	private final static String MANIFEST_NAME_BACKUP = "imsmanifest.xml.backup";
	private static String NO_PERMITE_DERIVADA="3";
	
	
	
	private static void parseaGruposXML() {
		if(properties==null) {
			properties = AgregaPropertiesImpl.getInstance();
			rutaLocalGrupos = properties.getProperty(AgregaProperties.URL_LICENCIAS)+"/gruposLicencias.xml";
			rutaLocalCompatibilidad = properties.getProperty(AgregaProperties.URL_LICENCIAS)+"/compatibilidadLicencias.xml";
		}
    	
		//Parseamos XML
		Unmarshaller unmarshallerGrupos = new Unmarshaller(Grupos.class);
//		Unmarshaller unmarshallerLicencias = new Unmarshaller(Licencias.class);
		unmarshallerGrupos.setValidation(false);
		
		File fichero = new File(rutaLocalGrupos);
		FileInputStream fis=null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(fichero);
			isr = new InputStreamReader(fis);
			grupos=(Grupos)unmarshallerGrupos.unmarshal(isr);
//			licencias=(Licencias)unmarshallerLicencias.unmarshal(isr);
		} catch (Exception e) {
			log.error("Error en parseo a XML de grupos de licencias: ",e);
		} finally {
			try {
				if(fis!=null) fis.close();
				if(isr!=null) isr.close();
			} catch (IOException e) {
				log.error("Error en parseo a XML de grupos de licencias: ",e);
			}
		}
	}
	
	
	private static void parseaCompatibilidadXML() {
		if(properties==null) {
			properties = AgregaPropertiesImpl.getInstance();
			rutaLocalGrupos = properties.getProperty(AgregaProperties.URL_LICENCIAS)+"/gruposLicencias.xml";
			rutaLocalCompatibilidad = properties.getProperty(AgregaProperties.URL_LICENCIAS)+"/compatibilidadLicencias.xml";
		}
    	
		//Parseamos XML
		Unmarshaller unmarshallerLicencias = new Unmarshaller(Licencias.class);
		unmarshallerLicencias.setValidation(false);
		
		File fichero = new File(rutaLocalCompatibilidad);
		FileInputStream fis=null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(fichero);
			isr = new InputStreamReader(fis);
			licencias=(Licencias)unmarshallerLicencias.unmarshal(isr);
		} catch (Exception e) {
			log.error("Error en parseo a XML de compatibilidad de licencias: ",e);
		} finally {
			try {
				if(fis!=null) fis.close();
				if(isr!=null) isr.close();
			} catch (IOException e) {
				log.error("Error en parseo a XML de compatibilidad de licencias: ",e);
			}
		}
	}


	/**
	 * Metodo que obtiene los grupos a los que pertenece un codigo de licencia.
	 *
	 * @param   codigosLicencias 
	 * 			codigo de una licencia 			
	 * @return Vector con los datos de cada grupo a los que pertenece la licencia.
	 * 			Cada posicion del vector es un grupo.
	 */
    protected GruposLicenciasVO[] handleObtieneGrupoLicencias(java.lang.String codigosLicencias)
        throws java.lang.Exception
    {
    	if(grupos==null) {
    		parseaGruposXML();
    	}
		Grupo[] listaGrupo=grupos.getGrupo();
		ArrayList<GruposLicenciasVO> gruposLicencias = new ArrayList<GruposLicenciasVO>();
		
		for (int i = 0; i < listaGrupo.length; i++) {
			Grupo grupo = listaGrupo[i];
			CodigoLicencia[] codigoLicencias=grupo.getCodigoLicencia();
			for (int j = 0; j < codigoLicencias.length; j++) {
				String licencia = codigoLicencias[j].getContent();
				if(codigosLicencias.equals(licencia)) {
					GruposLicenciasVO grupoLicencias = new GruposLicenciasVO();
					grupoLicencias.setGrupoLicencias(grupo.getNivel());
					grupoLicencias.setOrdenLimitacion(Integer.parseInt(codigoLicencias[j].getOrden()));
					gruposLicencias.add(grupoLicencias);
				}
			}
		}
		
		return gruposLicencias.toArray(new GruposLicenciasVO[gruposLicencias.size()]);
    }

    
	/**
	 * Metodo que obtiene la licencia resultante de mezlcar dos licencias.
	 *
	 * @param   licenciaOriginal 
	 * 			licencia a combinar 			
	 * @param   licenciaAdicional 
	 * 			licencia a combinar 
	 * @return String con la licencia resultante.
	 */
	@Override
	protected String handleObtieneLicenciaResultante(String licenciaOriginal, String licenciaAdicional)
			throws Exception {
		logger.debug("Comprobamos "+licenciaOriginal+" y "+licenciaAdicional);
		if(licencias==null) {
    		parseaCompatibilidadXML();
    	}
		Licencia[] listaLicencias=licencias.getLicencia();
		//Buscamos licenciaOriginal
		for (int i = 0; i < listaLicencias.length; i++) {
			Licencia licencia = listaLicencias[i];
			if(licencia.getCodigo().equals(licenciaOriginal)) {
			//Buscamos licenciaAdicional
				LicenciaAdicional[] listaLicenciasAdicionales=licencia.getLicenciaAdicional();
				for (int j = 0; j < listaLicenciasAdicionales.length; j++) {
					LicenciaAdicional licenciaAdicional2 = listaLicenciasAdicionales[j];
					if(licenciaAdicional2.getCodigo().equals(licenciaAdicional)) {
						logger.debug(licenciaOriginal+" y "+licenciaAdicional+" dan "+licenciaAdicional2.getContent());
						return licenciaAdicional2.getContent();
					}
				}
				logger.debug("No se ha encontrado licencia resultante.");
				return "";
			}
		}
		logger.debug("No se ha encontrado licencia entre las que son combinables.");
		return "";
	}

	
	/**
	 * Metodo que obtiene los grupos a los que pertenece la licencia de un ODE.
	 *
	 * @param   identificador 
	 * 			Identificador del ODE 			
	 * @param   rutaManifest 
	 * 			Es el path donde se encuentra el fichero manifest del ODE (el MANIFEST_NAME)
	 * @return Objeto VO en el que se indica si la licencia es valida y en caso de no serlo
	 * 			indica ademas que licencia transitorio produce incompatibilidades
	 */
	@Override
	protected GruposLicenciasVO[] handleObtieneGrupoLicenciasFichero (String rutaManifest, String identificador)
			throws Exception {
		//Primero obtenemos licencia por el catalogador avanzado
		
		try {
			File extraeSubmanifest = new File(rutaManifest);
			Manifest imsmanifest = this.getScormDao().parsearODELazy(extraeSubmanifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			Lom lom = manAgrega.obtenerLom(identificador, null);
			LomAgrega lomAgrega = new LomAgrega(lom);

			if (lomAgrega != null
					&& lomAgrega.getRightsAgrega() != null
					&& lomAgrega.getRightsAgrega().getDerechosDeAutor() != null) {
				String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
				//Ahora obtenemos código
				String codigoLicencia = getIdTermino(licencia,
						identificadorVocabulario, idiomaTerminos);
				//Y finalmente, obtenemos los grupos de licencias
				logger.debug("Devolvemos codigoLicencia "+codigoLicencia);
				return handleObtieneGrupoLicencias(codigoLicencia);
			}
		} catch (Exception e) {
			logger.debug("Error al parsear, luego suponemos que no hay problemas de licencias: ",e);
		}
		return new GruposLicenciasVO[0];
	}
	

	
    /*
     * Devuelve el ID del temino a traducir
     */
    private String getIdTermino(String termino, String identificadorVocabulario, String idiomaTerminos){

		TerminoYPadreVO terminoTraducido=null;
		if(termino!=null && !termino.trim().equals("")) {
			try{
				TerminoYPadreVO terminoVO = new TerminoYPadreVO(identificadorVocabulario, termino, idiomaTerminos,"");
				TerminoYPadreVO[] terminoArray = getSrvVocabulariosControladosService().obtenerIdTermino(new TerminoYPadreVO[]{terminoVO});
				if(terminoArray!=null && terminoArray.length>0) terminoTraducido=terminoArray[0];
			}catch (RemoteException e) {
	    		logger.error("SrvGruposLicenciasImpl - getIdTermino ERROR: Error en la invocacion al servicio de vocabularios controlados obteniendo el identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",e);
			} catch (Exception ex) {
				logger.error("SrvGruposLicenciasImpl - getIdTermino ERROR: Error en la obtencion de identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",ex);
			}
			if (terminoTraducido != null && terminoTraducido.getIdTermino() != null) {
				logger.debug("Termino "+termino+" traducido es "+terminoTraducido.getIdTermino());
				return terminoTraducido.getIdTermino();
			}
		}
		return "";
    }
    

	/**
	 * Metodo que indica si un ODE permite obras derivadas.
	 *
	 * @param   identificador 
	 * 			Identificador del ODE 
	 * @param   rutaManifest 
	 * 			Es el path donde se encuentra el fichero manifest del ODE (el MANIFEST_NAME)
	 * @return boolean
	 * 			true si permite obra derivada y false en caso contrario.
	 */
	@Override
	protected boolean handlePermiteObraDerivada(String rutaManifest, String identificador) throws Exception {
		logger.debug("Recibimos ruta "+rutaManifest);
		if (new File(rutaManifest).exists()) {
			GruposLicenciasVO[] grupos = handleObtieneGrupoLicenciasFichero(rutaManifest,identificador);
			if (grupos.length > 0) {
				for (int i = 0; i < grupos.length; i++) {
					GruposLicenciasVO gruposLicenciasVO = grupos[i];
					if (gruposLicenciasVO.getGrupoLicencias().equals(
							NO_PERMITE_DERIVADA)) {
						logger.debug("El grupo de licencias al que pertenece no permite Obra Derivada");
						return false;
					}
				}
			}
		} else {
			logger.debug("No existe "+rutaManifest+", luego no hay limitación de licencias.");
		}
		logger.debug("No se han encontrado limitaciones de Obra Derivada");
		return true;
	}

	
	/**
	 * Metodo que valida la licencia de un ODE.
	 * @param   pathManifest 
	 * 			Es el path donde se encuentra el fichero manifest del ODE (el MANIFEST_NAME)
	 * 			Tambien se le puede pasar un fichero manifest concreto.
	 * @return Objeto VO en el que se indica si la licencia es valida y en caso de no serlo
	 * 			indica ademas que licencia transitorio produce incompatibilidades
	 */
	@Override
	protected ValidarLicenciasVO handleValidarLicencias(String pathManifest)
			throws Exception {

		//Codigo util para devolver un error traducido
    	Properties prop = getCodigosErrorPropertiesFile();
		
		ValidarLicenciasVO validarLicencias = new ValidarLicenciasVO();
		validarLicencias.setResultado(false);
		
		//Cargamos ManifestAgrega
		//Primero miramos si lo que nos dan es un fichero o una carpeta
		//En caso de ser un fichero supondremos que es un manifest
		//En otro caso sera el path donde buscaremos el manifest
		File manifiestoFile = new File(pathManifest);
		
		//Revisamos si es un fichero
		if (manifiestoFile==null || !manifiestoFile.exists() || !manifiestoFile.isFile()) {
			logger.debug("El path '"+manifiestoFile.getAbsolutePath()+"' no existe o no es un fichero. Seguimos buscando el manifest...");		
			
			//No nos han dado un fichero asi que suponemos que bajo el path dado estara el manifest
			manifiestoFile = new File(pathManifest, MANIFEST_NAME);
			if (manifiestoFile==null || !manifiestoFile.exists()) {
				//logger.error("Se ha invocado al servicio de validacion de licencias sin un manifest valido. " + 
				//			   "El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");						
				//Devolvemos mensaje indicandi que no hay manifest
				//validarLicencias.setLicenciaAdicional(prop.getProperty("0.1"));
				//validarLicencias.setIdentificadorElemento("");
				//return validarLicencias;
				throw new Exception("Se ha invocado al servicio de validacion de licencias sin un manifest valido. " +
						"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");
			}
		}	

		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifiestoFile);
		ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
		String licenciaOriginal="";
		String licenciaTransitoria="";
		String licenciaAdicional="";
		String licenciaResultante="";
		String primerIdentificador="";
		
		//Recorremos Lomes buscando licencias
		Collection<Lom> lomes=manifAgrega.recuperarLomes();
		for (Iterator iterator = lomes.iterator(); iterator.hasNext();) {
			//Por cada licencia encontrada, vemos si hay licencia posible
			Lom lom = (Lom) iterator.next();
			
			LomAgrega lomAgrega = new LomAgrega(lom);
			primerIdentificador = lomAgrega.getGeneralAgrega().getPrimerIdentificador();

			if (lomAgrega != null
					&& lomAgrega.getRightsAgrega() != null
					&& lomAgrega.getRightsAgrega().getDerechosDeAutor() != null) {
				String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
		
				//Ahora obtenemos código
				String codigoLicencia = getIdTermino(licencia, identificadorVocabulario, idiomaTerminos);

				if(licenciaOriginal.equals("")) {
					licenciaOriginal=codigoLicencia;
					licenciaTransitoria=codigoLicencia;
				} else {
					licenciaAdicional=codigoLicencia;
				}
				if (!licenciaTransitoria.equals("")&&!licenciaAdicional.equals("")) {
					//Llamada a handleObtieneLicenciaResultante
					
					licenciaResultante = handleObtieneLicenciaResultante(licenciaOriginal, licenciaAdicional);

					if(!licenciaResultante.equals("")) {
						licenciaTransitoria=licenciaResultante;
					} else {						
						logger.debug("Problemas con licencia transitoria "+licenciaTransitoria+" y licenciaAdicional "+licenciaAdicional);
						//La última licencia dada es la que da problemas
						TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService().crearTraduccionAIngles(new String[]{licenciaAdicional});
						validarLicencias.setLicenciaAdicional(prop.getProperty("0.0")+" "+licenciaTraducida[0].getNomTermino());
						validarLicencias.setIdentificadorElemento(primerIdentificador);				
						return validarLicencias;
					}
				}
			}
		}
		//Al final, si licencia resultante es igual a la global, ok
		//caso contrario, KO	
		if (licenciaOriginal.equals(licenciaTransitoria)) {
			logger.debug("Licencias OK");
			validarLicencias.setIdentificadorElemento("Las licencias del ODE son compatibles.");
		} else {						
			logger.debug("Problemas con licencia transitoria "+licenciaTransitoria+" y licenciaAdicional "+licenciaAdicional);
			//La última licencia dada es la que da problemas
			TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService().crearTraduccionAIngles(new String[]{licenciaAdicional});
			validarLicencias.setLicenciaAdicional(prop.getProperty("0.0")+" "+licenciaTraducida[0].getNomTermino());	
			validarLicencias.setIdentificadorElemento(primerIdentificador);
		}
		validarLicencias.setResultado(licenciaOriginal.equals(licenciaTransitoria));	
		return validarLicencias;
	}

	
	@Override
	protected ValidarLicenciasVO handleCambiaLicenciaCompatible(
			String pathManifest, String idNuevoElemento, String usuario, String idioma) throws Exception {
		
		ValidarLicenciasVO validarLicencias = new ValidarLicenciasVO();
		validarLicencias.setResultado(false);
		//Cargamos ManifestAgrega
		//BORRAR
		logger.debug("Cargamos ManifestAgrega de "+pathManifest);
		File manifiestoFile = new File(pathManifest);
		Manifest imsmanifest = this.getScormDao().parsearODELazy(manifiestoFile);
		ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
		//Cargamos elemento nuevo
//		File nuevoElementoFile = new File(pathNuevoElemento, MANIFEST_NAME);
//		Manifest imsmanifestNuevo = this.getScormDao().parsearODEEager(nuevoElementoFile);
//		ManifestAgrega manifAgregaNuevo = new ManifestAgrega(imsmanifestNuevo);
		//Cogemos primera licencia de cada uno
		String codigoLicencia =""; 
		String codigoLicenciaNuevo="";
		LomAgrega lomAgrega=null;
		
		Collection<Lom> lomes=manifAgrega.recuperarLomes();
		for (Iterator iterator = lomes.iterator(); iterator.hasNext();) {
		
			lomAgrega=new LomAgrega(lomes.iterator().next());
			
			if (lomAgrega != null
					&& lomAgrega.getRightsAgrega() != null
					&& lomAgrega.getRightsAgrega().getDerechosDeAutor() != null) {
				String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
				//Ahora obtenemos código
				if(codigoLicencia.equals("")) {
					codigoLicencia = getIdTermino(licencia,
							identificadorVocabulario, idiomaTerminos);
					//BORRAR
					logger.debug("Licencia original es "+codigoLicencia);
				} else {
					if(lomAgrega.getGeneralAgrega().getPrimerIdentificador().equals(idNuevoElemento)) {
						codigoLicenciaNuevo=getIdTermino(licencia, identificadorVocabulario, idiomaTerminos);
						//BORRAR
						logger.debug("Licencia de nuevo elemento es "+codigoLicenciaNuevo);
					}
				}
			}
		}
		
//		Collection<Lom> lomesNuevo=manifAgregaNuevo.recuperarLomes();
//		LomAgrega lomAgregaNuevo=new LomAgrega(lomesNuevo.iterator().next());
//		
//		if (lomAgregaNuevo != null
//				&& lomAgregaNuevo.getRightsAgrega() != null
//				&& lomAgregaNuevo.getRightsAgrega().getDerechosDeAutor() != null) {
//			String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
//			//Ahora obtenemos código
//			codigoLicenciaNuevo = getIdTermino(licencia,
//					identificadorVocabulario, idiomaTerminos);
//		}
		
		//Vemos si hay licencia compatible
		String licenciaNueva=handleObtieneLicenciaResultante(codigoLicencia, codigoLicenciaNuevo);
		//BORRAR
		logger.debug("Licencia resultante es "+licenciaNueva);
		if(!licenciaNueva.equals("")) {
//			if (lomAgrega!=null) {
//				TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService().crearTraduccionAIngles(new String[]{licenciaNueva});
//				//Asignamos licencia
//				LomAvanzadoVO lomAvanzado = getSrvCatalogacionAvanzadaService()
//						.obtenerLomAvanzado(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), usuario,idioma);
//				lomAvanzado.getDerechos().getDerechosDeAutor().setValor(licenciaTraducida[0].getNomTermino());
//				getSrvCatalogacionAvanzadaService().generarMetadatos(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), usuario, lomAvanzado, idioma);
//				logger.debug("Hemos cambiado licencia resultante.");
//				validarLicencias.setResultado(true);
//			}
		} else {
			TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService().crearTraduccionAIngles(new String[]{codigoLicenciaNuevo});
			validarLicencias.setLicenciaAdicional(licenciaTraducida[0].getNomTermino());
			validarLicencias.setIdentificadorElemento(idNuevoElemento);
		}
		return validarLicencias;
	}

	
	/**
	 * Metodo que cambia la licencia de un ODE por la licencia resultante de la 
	 * combinacion de; la licencia de un nuevo alemento que va a ser añadido al ODE, 
	 * y la propia licencia del ODE.
	 *		
	 * @param   pathManifest 
	 * 			Es el path donde se encuentra el fichero manifest del ODE (el MANIFEST_NAME)
	 * @param   pathNuevoElemento 
	 * 			Es el path donde se encuentra el fichero manifest (el MANIFEST_NAME) del nuevo elemento a añadir en el ODE 
	 * @param   usuario 
	 * 			parametro sin uso.
	 * @param   idioma 
	 * 			parametro sin uso.
	 * @return Vector con los datos de cada grupo a los que pertenece la licencia.
	 * 			Cada posicion del vector es un grupo.
	 */
	@Override
	protected ValidarLicenciasVO handleCambiaLicenciaCompatibleRuta(
			String pathManifest, String pathNuevoElemento, String usuario,
			String idioma) throws Exception {
	
		ValidarLicenciasVO validarLicencias = new ValidarLicenciasVO();
		validarLicencias.setResultado(false);
		validarLicencias.setNecesitaCambio(true);
		
		//Cargamos ManifestAgrega
		pathManifest=idToPath(pathManifest);
		if(!pathManifest.startsWith("uploads")) {
			pathManifest="uploads"+pathManifest;
		}

		File manifiestoFile = new File(pathManifest,MANIFEST_NAME);
		if (manifiestoFile==null || !manifiestoFile.exists()) {
			//logger.error("Se ha invocado al servicio de validacion de cambio a una lincecia compatible sin un manifest valido." +
			//		"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");		
			//validarLicencias.setNecesitaCambio(false);
			//validarLicencias.setResultado(true);
			//return validarLicencias;
			throw new Exception("Se ha invocado al servicio de validacion de cambio a una lincecia compatible sin un manifest valido. " +
							"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");
		}
		
		logger.debug("Cargamos ManifestAgrega de "+pathManifest);
		Manifest imsmanifest = this.getScormDao().parsearODELazy(manifiestoFile);
		ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
		
		//Cargamos elemento nuevo
		pathNuevoElemento = idToPath(pathNuevoElemento);
		
		if(!pathNuevoElemento.startsWith("uploads")&&!pathNuevoElemento.contains("uploads")) {
			pathNuevoElemento="uploads"+pathNuevoElemento;
		}

		logger.debug("Cargamos ManifestAgrega de "+pathNuevoElemento);
		File nuevoElementoFile = new File(pathNuevoElemento, MANIFEST_NAME);
		if (nuevoElementoFile==null || !nuevoElementoFile.exists()) {
			//logger.error("Se ha invocado al servicio de validacion de cambio a una lincecia compatible sin un nuevo elemento valido." +
			//		"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");		
			//validarLicencias.setNecesitaCambio(false);
			//validarLicencias.setResultado(true);
			//return validarLicencias;
			throw new Exception("Se ha invocado al servicio de validacion de cambio a una lincecia compatible sin un nuevo elemento valido. " +
					"El fichero '"+nuevoElementoFile.getAbsolutePath()+"' no existe");
		}		
	
		Manifest imsmanifestNuevo = this.getScormDao().parsearODELazy(nuevoElementoFile);
		
		//BORRAR
		logger.debug("Parseado "+pathNuevoElemento);
		ManifestAgrega manifAgregaNuevo = null;
		try {
			manifAgregaNuevo = new ManifestAgrega(imsmanifestNuevo);
		} catch (Exception e){
			logger.debug("error al obtener el manifest del fichero '"+pathNuevoElemento+"'", e);
			validarLicencias.setNecesitaCambio(false);
			validarLicencias.setResultado(true);
			return validarLicencias;		
		}
		//Cogemos primera licencia de cada uno
		String codigoLicencia=""; 
		String codigoLicenciaNuevo="";
		
//		Collection<Lom> lomes=manifAgrega.recuperarLomes();
//		for (Iterator iterator = lomes.iterator(); iterator.hasNext();) {
//		
//			lomAgrega=new LomAgrega(lomes.iterator().next());
//			
//			if (lomAgrega != null
//					&& lomAgrega.getRightsAgrega() != null
//					&& lomAgrega.getRightsAgrega().getDerechosDeAutor() != null) {
//				String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
//				//Ahora obtenemos código
//				if(codigoLicencia.equals("")) {
//				codigoLicencia = getIdTermino(licencia,
//						identificadorVocabulario, idiomaTerminos);
//				//BORRAR
//				logger.debug("Licencia original es "+codigoLicencia);
//				} else {
//					if(lomAgrega.getGeneralAgrega().getPrimerIdentificador().equals(idNuevoElemento)) {
//						codigoLicenciaNuevo=getIdTermino(licencia, identificadorVocabulario, idiomaTerminos);
//						//BORRAR
//						logger.debug("Licencia de nuevo elemento es "+codigoLicenciaNuevo);
//					}
//				}
//			}
//		}
		
		Collection<Lom> lomes = null;
		LomAgrega lomAgrega=null;
		try {
			lomes=manifAgrega.recuperarLomes();
			lomAgrega=new LomAgrega(lomes.iterator().next());
		} catch (Exception e) {
			logger.debug("No se encontro catalogacion LOM-ES del manifest '"+pathManifest+"'", e);
			validarLicencias.setNecesitaCambio(false);
			validarLicencias.setResultado(true);
			return validarLicencias;			
		}
		if (lomAgrega != null
				&& lomAgrega.getRightsAgrega() != null
				&& lomAgrega.getRightsAgrega().getDerechosDeAutor() != null) {
			String licencia = lomAgrega.getRightsAgrega().getDerechosDeAutor();
			//Ahora obtenemos código
			codigoLicencia = getIdTermino(licencia, identificadorVocabulario, idiomaTerminos);
		}
		
		Collection<Lom> lomesNuevo=null;
		LomAgrega lomAgregaNuevo = null;
		try {
			lomesNuevo=manifAgregaNuevo.recuperarLomes();
			lomAgregaNuevo=new LomAgrega(lomesNuevo.iterator().next());
		} catch (Exception e) {
			logger.debug("No se encontro catalogacion LOM-ES de ODE '"+pathNuevoElemento+"'", e);
			validarLicencias.setNecesitaCambio(false);
			validarLicencias.setResultado(true);
			return validarLicencias;			
		}
		if (lomAgregaNuevo != null
				&& lomAgregaNuevo.getRightsAgrega() != null
				&& lomAgregaNuevo.getRightsAgrega().getDerechosDeAutor() != null) {
			String licencia = lomAgregaNuevo.getRightsAgrega().getDerechosDeAutor();
			//Ahora obtenemos código
			codigoLicenciaNuevo = getIdTermino(licencia, identificadorVocabulario, idiomaTerminos);
		}

		if (codigoLicencia.equals("") || codigoLicenciaNuevo.equals("")) {
			logger.debug("No se encontro una de las licencias");
			validarLicencias.setNecesitaCambio(false);
			validarLicencias.setResultado(true);
			return validarLicencias;
		}
		
		String licenciaNueva = handleObtieneLicenciaResultante(codigoLicencia, codigoLicenciaNuevo);
		if (licenciaNueva.equals("")) {
			TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService()
						.crearTraduccionAIngles(new String[] { codigoLicenciaNuevo });
			validarLicencias.setLicenciaAdicional(licenciaTraducida[0].getNomTermino());
			validarLicencias.setIdentificadorElemento(lomAgregaNuevo.getGeneralAgrega().getPrimerIdentificador());
			return validarLicencias;
		}

		if (licenciaNueva.equals(codigoLicencia)) {
			validarLicencias.setNecesitaCambio(false);
			validarLicencias.setResultado(true);
			
		} else if (lomAgrega != null) {
			TerminoYPadreVO[] licenciaTraducida = getSrvVocabulariosControladosService()
					.crearTraduccionAIngles(new String[] { licenciaNueva });
			//Asignamos licencia

			//ESTO GUARDABA LICENCIA EN DISCO
			lomAgrega.getRightsAgrega().setDerechosDeAutor(licenciaTraducida[0].getNomTermino());
			Lom lom = lomAgrega.getLom();
			manifAgrega.setLom(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), null, lom);
			Manifest manifest = manifAgrega.getManifest();

			//BORRAR
			logger.debug("Escribimos manifest en "+ manifiestoFile.getPath());
			getScormDao().escribirODE(manifest, manifiestoFile);

			//				getSrvGestorManifestService().cambiarLicencia(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), licenciaTraducida[0].getNomTermino());

			//				LomAvanzadoVO lomAvanzado = getSrvCatalogacionAvanzadaService()
			//						.obtenerLomAvanzado(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), usuario,idioma);
			//				lomAvanzado.getDerechos().getDerechosDeAutor().setValor(licenciaTraducida[0].getNomTermino());
			//				getSrvCatalogacionAvanzadaService().generarMetadatos(lomAgrega.getGeneralAgrega().getPrimerIdentificador(), usuario, lomAvanzado, idioma);
			logger.debug("Hemos cambiado licencia resultante.");
			validarLicencias.setResultado(true);
			validarLicencias.setLicenciaAdicional(licenciaTraducida[0].getNomTermino());
		}
		return validarLicencias;
	}


	private String idToPath(String pathNuevoElemento) throws RemoteException,
			Exception {
		if(!pathNuevoElemento.contains("/")) {
			//Esto es un id!
			LocalizadorVO localizador=getSrvLocalizadorService().consultaLocalizador(pathNuevoElemento);
			logger.debug("Elemento "+pathNuevoElemento+" esta en "+localizador.getPath());
			pathNuevoElemento=localizador.getPath();
		}
		return pathNuevoElemento;
	}
	
	
	/**
	 * Metodo que dado una licencia A y un manifest predice si al cambiar la licencia del manifesto por la licencia A el manifest valida o no.
	 *
	 * @param   licencia 
	 * 			licencia nueva a asignar en el manifest.
	 * @param   pathManifest 
	 * 			Es el path donde se encuentra el fichero manifest del ODE (el MANIFEST_NAME)
	 * 			Tambien se le puede pasar un fichero manifest concreto.
	 * @return Objeto VO en el que se indica si la licencia es valida y en caso de no serlo
	 * 			indica ademas que licencia transitorio produce incompatibilidades
	 * 
	 * @see es.pode.catalogacion.licencias.servicio.SrvGruposLicenciasBase#handlePredecirCompatibilidad(java.lang.String, java.lang.String)
	 */
	@Override
	protected ValidarLicenciasVO handlePredecirCompatibilidad(
			String pathManifest, String licencia) throws Exception {

		//Codigo util para devolver un error traducido
    	Properties prop = getCodigosErrorPropertiesFile();
    	
		ValidarLicenciasVO validacionLic = new ValidarLicenciasVO();
		validacionLic.setResultado(false);
		
		//Creamos copia del manifest (un backup)
		File manifiestoFile = new File(pathManifest,MANIFEST_NAME);
		if (manifiestoFile==null || !manifiestoFile.exists()) {
			//logger.error("Se ha invocado al servicio de validacion de licencias sin un manifest valido." +
			//		"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");					
			//Devolvemos mensaje indicando que no hay manifest
			//validacionLic.setLicenciaAdicional(prop.getProperty("0.1"));
			//validacionLic.setIdentificadorElemento("");
			//return validacionLic;
			throw new Exception ("Se ha invocado al servicio de validacion de licencias sin un manifest valido." +
					"El fichero '"+manifiestoFile.getAbsolutePath()+"' no existe");
		}
		
		File manifiestoFileBackup = new File(pathManifest,MANIFEST_NAME_BACKUP);
		
		//TODO -> este codigo todavia no funciona pero es una mejora muy recomendable de acabar
		//////////////////////////////////////////////////////////////////////////////////////////
		//Si el backup (la copia del manifest) existia previamente quiere decir que otro usuario 
		//esta validando el mismo ODE con (seguramente) otra licencia. En este caso creo un backup 
		//con un sufijo aleatorio hasta que encuentre un fichero que no exista y este vacio
		//////////////////////////////////////////////////////////////////////////////////////////
/*		FileInputStream fis = new FileInputStream(manifiestoFileBackup);
		while (manifiestoFileBackup!=null && manifiestoFileBackup.exists() && fis!=null && fis.read()!=-1) {
			//Fichero no vacio
			logger.debug("LUIS ERROR Generamos otro manifest temporal porque el fichero '"+manifiestoFileBackup.getAbsolutePath()+"' ya existe.");
			fis.close();
		    Random randomGenerator = new Random();
			manifiestoFileBackup = new File(pathManifest,MANIFEST_NAME_BACKUP+randomGenerator.nextInt(1000));
			fis = new FileInputStream(manifiestoFileBackup);
		}
		fis.close();
*/
		logger.debug("Usamos como manifest temporal el fichero '"+manifiestoFileBackup.getAbsolutePath()+"'");
		UtilesFicheros.copiar(manifiestoFile, manifiestoFileBackup);
		
		//Extraemos lomes de la copia
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifiestoFileBackup);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		String identifiadorManifest = manAgrega.getManifest().getIdentifier();
		Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
		LomAgrega lomAgrega = new LomAgrega(lom);

		//Modificamos y vemos si valida
		lomAgrega.getRightsAgrega().setDerechosDeAutor(licencia);
		try {
			this.getScormDao().escribirODE(imsmanifest, manifiestoFileBackup);
		} catch (Exception e) {
			UtilesFicheros.eliminar(manifiestoFileBackup);
			throw new Exception("No se ha podido guardar el ode", e);
		}
		validacionLic = handleValidarLicencias(manifiestoFileBackup.getAbsolutePath());

		//Eliminamos copia del manifest	
		UtilesFicheros.eliminar(manifiestoFileBackup);
		
		return validacionLic;
	}


	//Codigo util para devolver un error traducido
	protected Properties getCodigosErrorPropertiesFile() throws Exception {
		
		InputStream is = null;
    	Properties prop = new Properties();
    	//Necesitamos el idioma para saber en que idioma tenemos que hacer las traducciones
    	String idioma="es";
    	
    	if (LdapUserDetailsUtils.estaAutenticado()) { 
    		idioma = LdapUserDetailsUtils.getIdioma();
    		if(idioma==null ||idioma.equals(""))
    			idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	} else {
    		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}
		String nombreFich="/erroresValidacion_"+idioma+".properties";
		is = this.getClass().getResourceAsStream(nombreFich);
		prop.load(is);
		return prop;
	}
	
}