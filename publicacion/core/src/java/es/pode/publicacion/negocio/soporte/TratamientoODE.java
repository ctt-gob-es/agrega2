/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.soporte;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService;
import es.pode.indexador.negocio.servicios.indexado.IdODEVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.castor.LifeCycle;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.IdentificadorAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LifeCycleAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.publicacion.negocio.servicios.OdeVO;
import es.pode.publicacion.negocio.servicios.ScormDao;
import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceImpl;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

public class TratamientoODE {
	private static Logger logger = Logger.getLogger(TratamientoODE.class);
	
	private final static String URL_FICHA 		= "url.recurso.ficha";
	private final static String HTTP_PROTOCOL	= "http://";
	private final static String MANIFEST_NAME = "imsmanifest.xml";
	private final static String LICENCIA_NAME = "licencia.txt";
	
	protected final static String FILE_SEPARATOR = "/";
	public static final String FILE_NAME_PROPERTIES = "/publicacion.properties";

	/**
	 * 
	 * método auxiliar que tiene la funcionalidad rellenar los VO para indexar,
	 * se apoya en otros métodos
	 * 
	 * @throws Exception
	 */
	public static es.pode.indexador.negocio.servicios.indexado.IdODEVO rellenaIdOdeVO(ManifestAgrega manifest,
			String path_ode, String mec, String valoracion, Float tamanio, SrvNodoService nodo, 
			SrvEstructurasEducativasService estructuras, SrvTaxonomiaService taxonomia, 
			SrvPublicacionServiceImpl publicacion) throws Exception {
		return rellenaIdOdeVO(manifest, path_ode, mec, valoracion, tamanio, nodo, estructuras, taxonomia, publicacion, null);
	}
	
	/**
	 * 
	 * método auxiliar que tiene la funcionalidad rellenar los VO para indexar,
	 * se apoya en otros métodos
	 * 
	 * @throws Exception
	 */
	public static es.pode.indexador.negocio.servicios.indexado.IdODEVO rellenaIdOdeVO(ManifestAgrega manifest,
			String path_ode, String mec, String valoracion, Float tamanio, SrvNodoService nodo, 
			SrvEstructurasEducativasService estructuras, SrvTaxonomiaService taxonomia, 
			SrvPublicacionServiceImpl publicacion, SrvBuscadorService buscador) throws Exception {
		return rellenaIdOdeVO(manifest, path_ode, mec, valoracion, tamanio, nodo, estructuras, taxonomia, publicacion, null, null);
	}
	
	/**
	 * 
	 * método auxiliar que tiene la funcionalidad rellenar los VO para indexar
	 * 
	 * @param imagen Imagen que debe tener el ODE. Esto es util para aquellos ODEs que no tienen el fichero de vista previa
	 * en la raiz del ODE pero sin embargo si tienen imagenes asociadas en la galeria de imagenes. Esto ocurre con ODEs antiguos 
	 * que se crearon con una version de agrega anterior a la inclusion del fichero de vista previa en la raiz del ODE. Si imagen es null
	 * o vacio se usara el fichero de vista previa de la raiz del ODE, en otro caso se usara la imagen dada. 
	 * Esta solucion surgio como bugfix para el problema de que cuando se valoraba un ODE antiguo se perdia su imagen ya que
	 * en el proceso de reindexado no se encontraba el fichero de vista previa en la raiz del ODE y se le asignaba la de por defecto. 
	 * 
	 * @throws Exception
	 */
	public static es.pode.indexador.negocio.servicios.indexado.IdODEVO rellenaIdOdeVO(ManifestAgrega manifest,
			String path_ode, String mec, String valoracion, Float tamanio, SrvNodoService nodo, 
			SrvEstructurasEducativasService estructuras, SrvTaxonomiaService taxonomia, 
			SrvPublicacionServiceImpl publicacion, SrvBuscadorService buscador, String imagen) throws Exception {
		
		if (logger.isDebugEnabled())
			logger.debug("Vamos a rellenar los VOs para enviárselos luego al indexador con manifest["
					+ manifest.toString() + "], path_ode[" + path_ode + "] mec[" + mec + "] y valoracion[" + valoracion+ "]");

		ArrayList listLomes = new ArrayList(manifest.recuperarLomes());
		es.pode.indexador.negocio.servicios.indexado.IdODEVO idOdeVo = new IdODEVO();
		// Ponemos la secuencia a false por defecto
		idOdeVo.setSecuencia(false);
		if (logger.isDebugEnabled())
			logger.debug("ODE con mec[" + mec + "] tiene[" + (listLomes == null ? 0 : listLomes.size()) + "] lomes.");
		
		if (listLomes != null && listLomes.size() > 0) {
			try {
				idOdeVo.setCatalogacionPrimaria(new es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO());
				if(logger.isDebugEnabled())logger.debug("Rellenamos los VOs con los campos primarios");
				//Obtenemos los nodos 
				logger.debug("SecurityContextHolder.getContext() "+SecurityContextHolder.getContext());
				if(SecurityContextHolder.getContext().getAuthentication() == null)
				{
					logger.debug("El contexto no tiene autenticacion");
				//	Autenticar.cargarContextoSeguridad("administrador");
				}else{
					logger.debug("El contexto tiene autenticacion");
					logger.debug("SecurityContextHolder.getContext().getAuthentication() "+SecurityContextHolder.getContext().getAuthentication());
					logger.debug("getPrincipal() "+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
					logger.debug("getCredentials() "+SecurityContextHolder.getContext().getAuthentication().getCredentials());
					logger.debug("getName() "+SecurityContextHolder.getContext().getAuthentication().getName());
					logger.debug("getDetails() "+SecurityContextHolder.getContext().getAuthentication().getDetails());
					logger.debug("isAuthenticated() "+SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
//					logger.debug("-Creamos de nuevo el contexto de seguridad");
//					Autenticar.cargarContextoSeguridad(SecurityContextHolder.getContext().getAuthentication().getName());
				}
			
				NodoVO[] nodos = nodo.listarNodos();
				if(logger.isDebugEnabled())logger.debug("nodos <"+Arrays.toString(nodos)+">");
			
				//Obtenemos el nodo local y lo añadimos
				String[] nodosReturn = new String[((nodos!=null)?nodos.length+1:1)];
				nodosReturn[0] = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
				for (int i = 1; i < nodosReturn.length; i++) {
					nodosReturn[i] = nodos[i-1].getIdNodo();
				}
				logger.debug("nodosReturn[0] <"+nodosReturn[0]+">");
				idOdeVo = TratamientoIndexacion.RellenarCamposPrincipales(idOdeVo, (Lom) listLomes.get(0), estructuras, nodosReturn, taxonomia);
				
				// Eliminamos el LOM-ES principal de la lista para tratar solo los LOM-ES secundarios a la hora 
				// de indexar la informacion secundaria.
				listLomes.remove(0);
				if (listLomes.size() > 0) {
					logger.debug("Rellenamos los VOs con los campos secundarios");
					idOdeVo = TratamientoIndexacion.RellenarCamposSecundarios(idOdeVo, (Lom[]) listLomes.toArray(new Lom[listLomes.size()]));
				}
				// C/S SECUENCIA
				idOdeVo.setSecuencia(manifest.tieneSecuencias());
				if (logger.isDebugEnabled())logger.debug("Rellenamos IdODEVO con c/s secuencia[" + manifest.tieneSecuencias() + "]");
			
			} catch (Exception e) {
				logger.error("Fallo al rellenaIdOdeVO con con manifest[" + manifest.toString() + "], path_ode["
						+ path_ode + "] mec[" + mec + "] y valoracion[" + valoracion + "] - ", e);
				throw new Exception("Fallo al rellenaIdOdeVO con con manifest[" + manifest.toString() + "], path_ode["
						+ path_ode + "] mec[" + mec + "] y valoracion[" + valoracion + "]", e);
			}
		} else {
			if (logger.isDebugEnabled()) logger.debug("El ODE con mec[" + mec + "] no tiene lomes. Se mapean datos de indexacion minimos.");
		}
		// LOCALIZACION
		idOdeVo.setLocalizadorODE(path_ode);
		if (logger.isDebugEnabled())logger.debug("Rellenamos IdODEVO con localizador[" + path_ode + "]");
		// MEC
		idOdeVo.setIdODE(mec);
		if (logger.isDebugEnabled()) logger.debug("Rellenamos IdODEVO con mec[" + mec + "]");
		// VALORACION
		idOdeVo.setValoracion(Float.valueOf(valoracion.trim()));
		if (logger.isDebugEnabled())logger.debug("Rellenamos IdODEVO con valoracion[" + valoracion + "]");
		// FICHERO DE LA IMAGEN
		if (imagen==null||imagen.isEmpty()) {
			OdeVO ode = TratamientoImagenes.pathGenerate(manifest, mec, path_ode);
			String imgFile = publicacion.imagePathGenerate(manifest, mec, ode, path_ode);
			idOdeVo.setImgFile(imgFile);
		} else {
			idOdeVo.setImgFile(imagen);
		}
		if (logger.isDebugEnabled())logger.debug("Rellenamos IdODEVO con la imagen[" + idOdeVo.getImgFile() + "]");

		// TAMAÑO DEL ODE
		if (logger.isDebugEnabled())logger.debug("Rellenamos IdODEVO con el tamanio[" + tamanio + "]");
		idOdeVo.setTamanio(tamanio);

		return idOdeVo;
	}
	
	
	/**
	 * Introduce en el manifest la contribucion del publicador que se le pasa.
	 * 
	 * @param imsmanifest
	 * @param idUsuario
	 * @throws ParseadorException
	 */
	public static void introducePublicadorManifest(String localizador, String idUsuario, Manifest imsmanifest, ScormDao scorm)
			throws Exception {
		try {

			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			Lom lom = manAgrega.obtenerLom(imsmanifest.getIdentifier(), null);
			if (lom != null) {
			//	if(logger.isDebugEnabled())	logger.debug("Manipulando lom");
				LomAgrega lomAgrega = new LomAgrega(lom);
				LifeCycleAgrega cicloVida = lomAgrega.getLifeCycleAgrega();
				if (cicloVida == null)
					cicloVida = new LifeCycleAgrega(new LifeCycle());

				// Generamos la entidad VCARD con la entidad
				String entidad = "BEGIN:VCARD VERSION:3.0 FN: " + idUsuario + " EMAIL;TYPE=INTERNET:  ORG: Plataforma Agrega";
				// Generamos la fecha de hoy con formato YYYYMMDD
				java.util.Date date = new java.util.Date();
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
				String cadenaFecha = formato.format(date);
				String[] cadenas=cadenaFecha.split(" ");
				String fecha=cadenas[0].concat("T").concat(cadenas[1]).concat("Z");
				// Hacemos el set
				cicloVida.addContribucionPublicacion(entidad, fecha);
				lom.setLifeCycle(cicloVida.getLifeCycle());
				if(logger.isDebugEnabled()) logger.debug("introduciendo Lom en Manifest: entidad: " + entidad + " fecha: "+ cadenaFecha);
				manAgrega.setLom(imsmanifest.getIdentifier(), null, lom);
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())	logger.debug("Lom modificado en objeto Manifest");
			}
			if(logger.isDebugEnabled())	logger.debug("Escribiendo Manifest modificado: localizador" + localizador );
			File prueba = new File(localizador, MANIFEST_NAME);
			scorm.escribirODE(imsmanifest, prueba);
			if(logger.isDebugEnabled())	logger.debug("Manifest modificado con exito. Manifest.id = "+ imsmanifest.getIdentifier());
		} catch (Exception e) {
			logger.error("Error durante la modificacion del manifest a publicar. No se ha podido introducir la contribucion del publicador["
									+ idUsuario + "]localizador[" + localizador + "] - ", e);
		}
	}
	
	/**
	 * Introduce en el manifest la contribucion del publicador que se le pasa.
	 * 
	 * @param imsmanifest
	 * @param idUsuario
	 * @throws ParseadorException
	 */
	public static void introduceAutorWebSemanticaManifest(String localizador, String[] correosUsuarios, Manifest imsmanifest, ScormDao scorm)
			throws Exception {
		try {

			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			Lom lom = manAgrega.obtenerLom(imsmanifest.getIdentifier(), null);
			if (lom != null) {
			//	if(logger.isDebugEnabled())	logger.debug("Manipulando lom");
				LomAgrega lomAgrega = new LomAgrega(lom);
				LifeCycleAgrega cicloVida = lomAgrega.getLifeCycleAgrega();
				if (cicloVida == null)
					cicloVida = new LifeCycleAgrega(new LifeCycle());

				for (int i = 0; i < correosUsuarios.length; i++) {
					// Generamos la entidad VCARD con la entidad
					String entidad = "BEGIN:VCARD VERSION:3.0 FN: " + correosUsuarios[i] + " EMAIL;TYPE=INTERNET:"+ correosUsuarios[i]  +" ORG: Plataforma Agrega-WebSemantica";
					// Generamos la fecha de hoy con formato YYYYMMDD
					java.util.Date date = new java.util.Date();
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
					String cadenaFecha = formato.format(date);
					String[] cadenas=cadenaFecha.split(" ");
					String fecha=cadenas[0].concat("T").concat(cadenas[1]).concat("Z");
					// Hacemos el set
					cicloVida.addContribucionCreacion(entidad, fecha);
					if(logger.isDebugEnabled()) logger.debug("introduciendo Lom en Manifest: entidad: " + entidad + " fecha: "+ cadenaFecha);					
				}
				lom.setLifeCycle(cicloVida.getLifeCycle());

				manAgrega.setLom(imsmanifest.getIdentifier(), null, lom);
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())	logger.debug("Lom modificado en objeto Manifest");
			}
			if(logger.isDebugEnabled())	logger.debug("Escribiendo Manifest modificado: localizador" + localizador );
			File prueba = new File(localizador, MANIFEST_NAME);
			scorm.escribirODE(imsmanifest, prueba);
			if(logger.isDebugEnabled())	logger.debug("Manifest modificado con exito. Manifest.id = "+ imsmanifest.getIdentifier());
		} catch (Exception e) {
			logger.error("Error durante la modificacion del manifest. No se ha podido introducir la contribucion del autor["
									+ correosUsuarios + "]localizador[" + localizador + "] - ", e);
		}
	}	
	/**
	 * Modificar la localización en el fichero manifest para introducir la url de la ficha 
	 * @param localizador
	 *            Localizador del ODE
	 * @param idUsuario
	 * 			  Id del usuario
	 * @param imsmanifest 
	 * 			  Fichero manifest
	 * @throws Exception
	 */
	public static void introduceLocalizacionWEB(String localizador, String idUsuario, Manifest imsmanifest, ScormDao scorm, String catMec)
		throws Exception {
		try {

			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			Lom lom = manAgrega.obtenerLom(imsmanifest.getIdentifier(), null);
			LomAgrega lomAg = new LomAgrega(lom);
			if (lomAg != null) {
			//	if(logger.isDebugEnabled())	logger.debug("introduceLocalizacionWEB:Manipulando lom");
				// sacamos el idioma en el que se va a indexar el ODE para poder construir la URL de la WEB
				MetaMetadataAgrega metaMeta = lomAg.getMetaMetadataAgrega();
				//Sera idiomaAv, valida que no sea nulo
				String idioma = metaMeta.getIdiomaAv();
				TechnicalAgrega tecnical = lomAg.getTechnicalAgrega();
				ArrayList localizacionesOrigen = tecnical.getLocalizacionAv();
				ArrayList<String> localizacionesDestino = new ArrayList<String>();
				// la localizacion esta compuesta por una url con el host, el recurso del servlet que monta la ficha, el id del ode y el idioma
				//Recogemos el mec del ode.........
				
				ArrayList arrayIdentificadores=lomAg.getGeneralAgrega().getIdentificadoresAv();
				boolean identificadorMec=false;
				String identificadorEncontrado="";
				for(int i=0;i<arrayIdentificadores.size() && !identificadorMec;i++){
					IdentificadorAgrega identificador = (IdentificadorAgrega)arrayIdentificadores.get(i);
					if(identificador.getCatalogo().equals(catMec)){
						String entrada = identificador.getEntrada();
						//miramos que el entry sea valido debe ser de la forma
						 //es-ex_20061017_2_1234567 siendo -ex opcional
						 Pattern mask=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
						 Matcher matcher = mask.matcher(entrada);
						 
						 //o de la forma es_ex_2006101722_1300009 siendo _ex opcional
						 Pattern mask2=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)([0-9]{1})([0-9]{1})\\_([0-9]{7}$)");
						 Matcher matcher2 = mask2.matcher(entrada);
						 if(matcher.matches() || matcher2.matches()){
							 identificadorEncontrado=entrada;
							 identificadorMec=true;
						 }
			 		}
			 	}
			 	if(!identificadorMec){
			 		logger.error("El ode no tiene identificador mec");
			 		identificadorEncontrado="";
			 	}
				String mec = identificadorEncontrado;
				String miNuevaLocalizacion= "";
				if(!(LdapUserDetailsUtils.getSubdominio() == "")&&!(LdapUserDetailsUtils.getSubdominio() == " ")&&!(LdapUserDetailsUtils.getSubdominio() == null))
				{
					miNuevaLocalizacion = HTTP_PROTOCOL +AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+ LdapUserDetailsUtils.getSubdominio()+ FILE_SEPARATOR+
								SrvPublicacionServiceImpl.getPropertyValue(URL_FICHA)+"/"+idioma+"/"+mec;
				}else
				{
					miNuevaLocalizacion = HTTP_PROTOCOL +AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)+ 
												SrvPublicacionServiceImpl.getPropertyValue(URL_FICHA)+"/"+idioma+"/"+mec;
				}
				if(logger.isDebugEnabled())logger.debug("La nueva localizacion que le insertamos es <"+miNuevaLocalizacion+">");
				localizacionesDestino.add(miNuevaLocalizacion);
				localizacionesDestino.addAll(localizacionesOrigen);
				tecnical.setLocalizadorAv(localizacionesDestino);
				lomAg.setTechnicalAgrega(tecnical);
				// reconstruimos el objeto lom
				manAgrega.setLom(imsmanifest.getIdentifier(), null, lomAg.getLom());
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())	logger.debug("introduceLocalizacionWEB:Escribiendo Manifest modificado: localizador <" + localizador +">");
				File prueba = new File(localizador, MANIFEST_NAME);
				scorm.escribirODE(imsmanifest, prueba);
			}
		} catch (Exception e) {
			logger.error(
					"introduceLocalizacionWEB: Error durante la modificacion del manifest a publicar. No se ha podido introducir la contribucion del publicador["
					+ idUsuario + "]localizador[" + localizador + "] - ", e);
		}
	}
	
	
	public static void insercionLicenciaOde(LocalizadorVO localizadorVO, Lom lom, SrvVocabulariosControladosService vocabulario) throws Exception, ParseadorException {
		LomAgrega la = new LomAgrega(lom);
		
		// Obtiene la licencia en ingles
		String derechosAutor = la.getRightsAgrega().getDerechosDeAutor();
		if(logger.isDebugEnabled()) logger.debug("Licencia del ODE: <" + derechosAutor+">");
		// consulto con fuentes taxonomicas
		try {
			
			TerminoYPadreVO typ= new TerminoYPadreVO();
			typ.setIdiomaTermino("en");
			typ.setNomTermino(derechosAutor);
			typ.setIdTermino("");
			// Vocabulario de Rights -> Derechos de Autor = 6.2
			typ.setIdVocabulario("6.2");
			
			//if(logger.isDebugEnabled()) logger.debug("Llamando a fuentes taxonomicas");
			TerminoYPadreVO[] typArray = vocabulario.obtenerIdTermino(new TerminoYPadreVO[]{typ});
			if(logger.isDebugEnabled()) logger.debug("Retorno de fuentes taxonomicas: " + Arrays.toString(typArray) + " con longitud " + (typArray!=null?typArray.length:"null"));
			if (typArray == null){
				logger.error("Error: fuentestaxonomicas ha devuelto null: no se ha podido recuperar la licencia");
			}else{
				String identificadorLicencia = typArray[0].getIdTermino();
				if(logger.isDebugEnabled()) logger.debug("Identificador de la licencia = " + identificadorLicencia);
				String urlLicencias=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LICENCIAS);
				if(logger.isDebugEnabled()) logger.debug("URL licencias (sin identificador)" + urlLicencias);
				String urlEntero=urlLicencias+ FILE_SEPARATOR+identificadorLicencia+ FILE_SEPARATOR+ LICENCIA_NAME;
				if(logger.isDebugEnabled()) logger.debug("URL licencias (completo)" + urlEntero);
				File localizarLicencia=new File(urlEntero);
				File licenciaEnODE = new File(localizadorVO.getPath(),LICENCIA_NAME);
				if(licenciaEnODE.exists()) {
					if(logger.isDebugEnabled()) logger.debug("Ya hay un licencia.txt en " + localizadorVO.getPath() + "; lo sobreescribo");
					UtilesFicheros.eliminar(licenciaEnODE);
					if(licenciaEnODE.exists()) logger.error("No se ha podido borrar " + licenciaEnODE);
				}
				if(localizarLicencia.exists())  {
					UtilesFicheros.copiar(localizarLicencia, new File(localizadorVO.getPath()));
					if(logger.isDebugEnabled()) logger.debug("Licencia copiada con exito");
					
				} else {
					logger.error("La licencia <" + urlEntero + "> no existe en <" + urlLicencias+">");
				}
			} 
		} catch (Exception e) {
			logger.error("Error en la recuperación de licencias del VDEX - ",e);
		}
	}

	
	/**
	 * Parsea el manifest almacenado en <em>localizador</em> y comprueba que
	 * Manifest.getIdentifier devuelve el MEC. Si no es asi, significa que es la
	 * primera vez que indexamos este ODE (publicacion). Hay que cambiar el
	 * viejo UUID por el MEC e introducir el MEC en el campo LOM-ES del
	 * manifiesto.
	 * 
	 * @param mec
	 *            Identificativo MEC del ODE
	 * @param localizador
	 *            Localizador del ODE
	 */
	public static void cambiaUUIDxMEC(String mec, String localizador, Manifest manifest, String catalogo, ScormDao scorm) {
		if (logger.isDebugEnabled())
			logger.debug("Entrando en cambiaUUIDxMEC con mec <" + mec + "> y localizador <" + localizador+">");
		try {

			if (logger.isDebugEnabled()) logger.debug("MEC = <" + mec + ">; Identifier = <" + manifest.getIdentifier()+">");
			// Cambiar el mec si no es igual
			if (!mec.equals(manifest.getIdentifier())) {
				if(logger.isDebugEnabled())
				logger.debug("Cambiando el Manifest.getIdentifier");
				manifest.setIdentifier(mec);
			}

			if(logger.isDebugEnabled())logger.debug("Buscando LOM-ES principal del manifiesto");
			ManifestAgrega manAgrega = new ManifestAgrega(manifest);
			// Lom lom = manAgrega.obtenerLom(mec, null);
			Lom lom = manAgrega.obtenerLom(manifest.getIdentifier(), null);
			if (lom != null) {
				if(logger.isDebugEnabled()) logger.debug("Manipulando lom");
				LomAgrega lomAgrega = new LomAgrega(lom);

				// hay que comprobar que es distinto el agrega del mec, si es
				// así lo recubrimos.
				if (lomAgrega.getGeneralAgrega() != null
						&& !lomAgrega.getGeneralAgrega().getPrimerIdentificador().equals(mec)) {
					lomAgrega.getGeneralAgrega().setIdentificadorPrimeraPosicion(catalogo, mec);

					// Campo 3.1 - Identificador del LOM-ES con sufijo meta
					lomAgrega.getMetaMetadataAgrega().setIdentificadorMEC(catalogo, mec + "-meta");
					if(logger.isDebugEnabled())	logger.debug("MEC introducido en el LOM-ES del manifiesto");
					lom.setMetaMetadata(lomAgrega.getMetaMetadataAgrega().getMetaMetadata());
					if(logger.isDebugEnabled())	logger.debug("Re-introduciendo Lom en Manifest");
					// manAgrega.setLom(mec, null, lom);
					manAgrega.setLom(manifest.getIdentifier(), null, lom);
					manifest = manAgrega.getManifest();
					if(logger.isDebugEnabled())	logger.debug("Lom modificado en objeto Manifest");
				} else {
					logger.warn("El Lom del manifest <" + mec + "> no tiene objeto general o el primer identificador que contiene es igual al mec");
				}
			}
			if(logger.isDebugEnabled())	logger.debug("Escribiendo Manifest modificado");
			File prueba = new File(localizador, MANIFEST_NAME);
			scorm.escribirODE(manifest, prueba);

			if (logger.isDebugEnabled()) logger.debug("Manifest modificado con exito. Manifest.id = <" + manifest.getIdentifier()+">");

		} catch (Exception e) {
			logger.error("Error durante la modificacion del manifest publicado. No se ha podido introducir el MEC. - ", e);
		}
	}
	
	
	/**
	 * Copiamos los esquemas XSD a la carpeta del ODE
	 * @throws Exception
	 */

	public static void copiarEsquemas(String rutaDestino) throws Exception {
		File ficheroNuevo = new File(rutaDestino);
		File ficheroViejo = new File(SrvPublicacionServiceImpl.getPropertyValue("carpeta.schema"));

		if (ficheroViejo.exists()) {
			if(logger.isDebugEnabled())	logger.debug("Se va a copiar el esquema");
			UtilesFicheros.copiar(ficheroViejo, ficheroNuevo);
		} else {
			logger.warn("El fichero origen no existe");
			throw new Exception("El fichero origen " + ficheroViejo.getName() + " no existe en la ruta "
					+ ficheroViejo.getPath());
		}

	}
	
	
	/**
	 * Borramos los esquemas XSD sobrantes de la carpeta del ODE
	 * @throws Exception
	 */
	public static void borrarEsquemas(String rutaDestino, String tipoOde) throws Exception {
		//Si se ha transformado de Scorm12 a Scorm 2004 --> borramos los esquemas de Scorm12
		//Si se ha transformado de Imscp a Scorm 2004 --> borramos los esquemas de imscp
		
		File ficheroNuevo = new File(rutaDestino);
		String esquemasABorrar="";
		if ((tipoOde!=null) && (tipoOde.equals(ConstantesAgrega.SCORM_12))) {
			esquemasABorrar= SrvPublicacionServiceImpl.getPropertyValue("carpeta.schema12");				
		} else if ((tipoOde!=null) && (tipoOde.equals(ConstantesAgrega.IMS_CP))) {
			esquemasABorrar=SrvPublicacionServiceImpl.getPropertyValue("carpeta.schema_imscp");
			//si estamos en imscp no borramos
		}
		//logger.info("ASC - ESQUEMASABORRAR " + esquemasABorrar);
		File ficheroViejo = new File(esquemasABorrar);
		
		if ((ficheroViejo.exists()) && (ficheroNuevo.exists()) ){
			if ((ficheroViejo.isDirectory()) && (ficheroNuevo.isDirectory())) {
				File[] arrFileOld= ficheroViejo.listFiles(); //lista de ficheros que hay que borrar
				
				File[] arrFileNew = ficheroNuevo.listFiles(); //lista de ficheros nuevos
				if ((arrFileOld!=null) && (arrFileOld.length>0)) {
					for (int j=0;j<arrFileOld.length; j++) {
						String unFileOld = arrFileOld[j].toString(); //uploads/schemasScorm12/imscp_rootv1p1p2.xsd		
						// 20131104 Si se importa un ODE en SCORM1.2 debemos dejar en el ODE el esquema imsmd_rootv1p2p1.xsd porque
						// si volviesen a descargar ese ODE y no tiene ese esquema, no validaría el ODE correctamente
						if(!unFileOld.contains(SrvPublicacionServiceImpl.getPropertyValue("schema.imsmd_rootv1p2p1")))
						{
							//borrar este logger despues...
							//pero antes uploads/schemasScorm12/imscp_rootv1p1p2.xsd lo cortamos y nos quedams con el fichero
							String[]rutasOld= unFileOld.split("\\/");
							String rutasOld_aux="";
							if ((rutasOld!=null) && (rutasOld.length>0)) {
								rutasOld_aux = rutasOld[rutasOld.length-1];
								unFileOld = rutasOld_aux;
							}
							//buscamos este fichero en el directorio nuevo
							if ((arrFileNew!=null) && (arrFileNew.length>0)) {
								for (int i=0;i<arrFileNew.length; i++) {
									String unFileNew= arrFileNew[i].toString();
									//si no son vacios y coinciden lo borramos
									if ((!unFileOld.equals("")) && (!unFileNew.equals("")) && (unFileNew.endsWith(unFileOld))) {
										arrFileNew[i].delete();
										//QUITAR DESPUES!!!!!!!!
										if (logger.isDebugEnabled())logger.debug("BORRANDO EL FICHERO <" + unFileNew+">");
									}
								}
							}
						}
					}
				}
			 }
		}

	}

	/**
	 * Obtiene el código MD5 asociado al ODE que se va a publicar.
	 * 
	 * @param manifest
	 *            Manifiesto del ODE donde se indican los recursos que contiene.
	 * @return Devuelve la cadena de caracteres del código MD5 asociado al ODE.            
	 */
	public static String obtenerMD5FromODE(Manifest manifest){
		
		try {
			ManifestAgrega manAgrega = new ManifestAgrega(manifest);
			// obtenemos los recursos del ODE
			// primero las url's, ordenadas
			String[] urls = manAgrega.obtenerURLsODE(true);
			// después los ficheros, ordenados
			String[] ficheros = manAgrega.obtenerFicherosODE(true);
			// ahora generamos el string del MD5 a partir de esta info.
			String md5Ficheros = UtilesFicheros.obtenerMD5Files(manifest.getManifestBasePath(), ficheros);
			String md5URL = UtilesFicheros.obtenerMD5SumFromString(urls);
			// concatenamos los dos string y calculo el MD5
			return UtilesFicheros.obtenerMD5SumFromString(md5URL+md5Ficheros);
		} catch (Exception e) {
			logger.error("Error generando el codigo MD5 del manifiesto ["+(manifest!= null?manifest.getManifestBasePath():"")+"]. Devolvemos vacio.");
			if(logger.isDebugEnabled())logger.debug(e);
			return "";
		}
	}

}

