/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.herramientaOffline.negocio.soporte;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import es.agrega.soporte.xslt.TransformadorSaxonImpl;
import es.pode.dri.negocio.servicios.DRI.SrvDRIService;
import es.pode.dri.negocio.servicios.DRI.SrvDRIServiceServiceLocator;
import es.pode.empaquetador.negocio.servicio.AnalizaArchivoVO;
import es.pode.entregar.negocio.servicios.PaquetePifDriVO;
import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.herramientaOffline.negocio.dominio.Ode;
import es.pode.herramientaOffline.negocio.dominio.OdeDao;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.parseadorXML.castor.Item;
import es.pode.parseadorXML.castor.Location;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.Metadata;
import es.pode.parseadorXML.castor.Organization;
import es.pode.parseadorXML.castor.Resource;
import es.pode.parseadorXML.lomes.lomesAgrega.AccesoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.AnnotationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ContribucionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.DuracionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EducationalAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.FechaAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LifeCycleAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RequisitoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RightsAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.validador.negocio.servicio.SrvValidadorService;
import es.pode.validador.negocio.servicio.ValidaVO;



/**
 * @see es.pode.herramientaOffline.negocio.soporte.DescomprimeYvalida
 */

public class DescomprimeYvalidaImpl
    extends es.pode.herramientaOffline.negocio.soporte.DescomprimeYvalidaBase
{

	static Logger logger = Logger.getLogger(DescomprimeYvalidaImpl.class);
	
	private static HashMap propsI18n = null;
	public static final String FILE_NAME_I18N = "hoffline";
	private final String MANIFEST_NAME = "imsmanifest.xml";
	
	public static final String FILE_NAME_OFFLINE = "application-resources";
	
	//private final String MANIFEST_NAME = "imsmanifest.xml";

	public final static String SIN_ERRORES = "0.0";

	public final static String FALLO_ESCRITURA_DISCO_YA_EXISTE = "10.1";

	public final static String MEC_YA_EXISTE = "10.2";

	public final static String ERROR_INDEXACION = "10.3";

	public final static String FICHERO_ORIGINAL_ODE_NO_EXISTE = "10.4";

	public final static String IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA = "10.5";

	public final static String ODE_YA_CREADO_EN_PLATAFORMA = "10.6";

	public final static String TRANSICION_ESTADO_INVALIDA = "10.7";

	public final static String FICHERO_YA_EXISTE = "10.8";

	public final static String EXCEPCION_INESPERADA = "10.9";

	public final static String CONSULTA_VALORACION_ERRONEA = "10.10";

	public final static String ERROR_PARSEO_MANIFEST = "10.11";

	public final static String ERROR_MAPEO_INDEXADO = "10.12";

	public final static String ERROR_FORMATO_ZIP = "10.13";

	public final static String ERROR_DESCOMPRIMIENDO_FICHERO_ZIP = "10.14";

	public final static String ERROR_COPIANDO_ESQUEMAS = "10.15";

	public final static String ERROR_DE_VALIDACION = "10.16";
	
	private final String usuario = "usuario";
	
	private final String HTTP = "http://";
	
	private final String SPLITTER = ";";
	
	private final String DRI = "dri-1/services/SrvDRIService";
	
	private final String BARRA = "/";
	
	/* 
	 * descomprime el datahandler que se le envía, crea un localzador, y valida el ode. Si todo ha ido correctamente 
	 * devuelve un id ode en el primer argumento y un null en el segundo. Si no devuelve un null en el primer argumento
	 * y los errores en el segundo.
	 */

	protected DescompriveYvalidaVO handleImportarOde(DataHandler archivo,String titulo)
			throws Exception {
		return importarOde(archivo, titulo, null);
	}
	
	private DescompriveYvalidaVO importarOde(DataHandler archivo, String titulo, String idOde) {

		idOde = idOde!=null&&!idOde.equals("")?idOde:es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String
				.valueOf(System.currentTimeMillis()));
		String xsl="";
		try {
		// creacion
		this.creacion(idOde, titulo);
		LocalizadorVO loc = this.getSrvLocalizadorService().consultaLocalizador(idOde);
		Locale locale=new Locale(LdapUserDetailsUtils.getIdioma());
		ResourceBundle i18n = ResourceBundle.getBundle(FILE_NAME_OFFLINE,locale);
		logger.debug("Creando PIF utilizando identificador[" + idOde
				+ "] y titulo[" + titulo + "]");
		
		// Creo file temporal para escribir fichero a importar
		File ficheroZip=java.io.File.createTempFile("Temp", titulo);
		String pathtemp =ficheroZip.getPath();
		ficheroZip.deleteOnExit();
		FileOutputStream fos = new FileOutputStream(ficheroZip);
		archivo.writeTo(fos);
		fos.close();
		// Preparo ruta del nuevo ODE
		String pathtempDestino = loc.getPath();
		File destinoTemporal = new File(pathtempDestino);
		destinoTemporal.mkdirs();
//		 La comprobacion se delega en el
		// empaquetador para hacer un 'importar inteligente'. El atributo
		// 'titulo' es el nombre del fichero proporcionado por la plicación
		// cliente
		AnalizaArchivoVO resultadoAnalisis = null;
		String tipoFichero =null;
		try {
			resultadoAnalisis = this.getSrvFachadaAgregarService().analizarArchivo(
					pathtemp); 
			tipoFichero = resultadoAnalisis.getTipoArchivo();
			if (logger.isDebugEnabled())
				logger.debug("El fichero importado <" + titulo + "> es de tipo <"
						+ tipoFichero+">");
			// Si el tipo de fichero es comprimido, lo descomprimimos en la
			// localización definitiva
			if (ConstantesAgrega.FICHERO.equals(tipoFichero)) {
				UtilesFicheros.copiar(ficheroZip, new File(destinoTemporal,titulo));
			} else if(ConstantesAgrega.ODE_NO_VALIDO.equals(tipoFichero)) {
				logger.warn("El archivo <" + titulo + "> es un ODE no valido. Se rechaza su importacion");
				eliminarOdes(new String[]{idOde});
				return new DescompriveYvalidaVO(idOde,resultadoAnalisis.getMensajes(),false);
			}
			else {
				getZipDao().descomprimir(ficheroZip.getPath(), pathtempDestino);
			}
		} catch (Exception e) {
			// borramos todo lo que hayamos hecho hasta ahora
			this.eliminarOdes(new String[]{idOde});
			logger.error("Error analizando fichero [" + pathtemp
					+ "], con nombre[" + titulo + "]", e);
			return new DescompriveYvalidaVO(idOde,new String[]{getPropertyValueI18n(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP)},false);
		}
		/*
		 * En funcion del tipo de archivo hay que generar un imsmanifest.xml que recubra el contenido.
		 */
		String idioma = LdapUserDetailsUtils.getIdioma();
		if(ConstantesAgrega.FICHERO.equals(tipoFichero)) {
			this.getSrvFachadaAgregarService().generarManifest(loc.getIdentificador(), new String[]{titulo}, titulo, idioma);
		} else if(ConstantesAgrega.ARCHIVO.equals(tipoFichero)) {
			Collection<File> ficheros = FileUtils.listFiles(destinoTemporal, null, true);
			if(ficheros!=null ) {
				java.io.File[] ficherosArray = new java.io.File[ficheros.size()];
				if(logger.isDebugEnabled()) logger.debug("Se han encontrado <" + ficheros.size() + ">ficheros dentro del zip <" + titulo+">");
				ficherosArray = ficheros.toArray(ficherosArray);
				String[] rutas = new String[ficheros.size()];
				String rutaAReemplazar = destinoTemporal.getPath().replaceAll("\\\\", "/");
				if(logger.isDebugEnabled()) logger.debug("Ruta a reemplazar con / : <" + rutaAReemplazar+">");
				for(int i=0;i<ficherosArray.length;i++) {
					
					String ruta =  ficherosArray[i].getPath().replaceAll("\\\\", "/");
					ruta = ruta.replaceAll(rutaAReemplazar, "");
					if(ruta.startsWith("/")) ruta = ruta.substring(1);
					if(logger.isDebugEnabled()) logger.debug("Ruta " + ficherosArray[i].getPath() + " cambiada a " +ruta);
					rutas[i]=ruta;
				}
				getSrvFachadaAgregarService().generarManifest(loc.getIdentificador(), rutas, rutas[0], idioma);
			} else {
				logger.warn("Se ha intentado importar un ZIP vacio");
			}
		} else if(ConstantesAgrega.RCP.equals(tipoFichero)) {
			this.getSrvFachadaAgregarService().generarManifestRCP(loc.getIdentificador(), idioma);
		} // PARA ConstantesAgrega.CA no hace falta hacer nada
		
		//	copiar los esquemas a la localizacion del ODE
		String tipo="";
		try {
//			if(logger.isDebugEnabled())logger.debug("LEA - JUSTO ANTES DE VER TIPO ODE!!");
			//aqui debemos chequear q tipo de ode es y hacer la conversion y copiar los esquemas correspondientes
			//SACAMOS EL TIPO DE ODE Q SE ESTA IMPORTANDO: SCORM 1.2, IMS CP O SCORM 2004
			es.pode.soporte.validador.TipoOde tipoOde = new es.pode.soporte.validador.TipoOde();
			tipoOde.obtenerTipoOde(loc.getPath() + "/" +MANIFEST_NAME);
			tipo = tipoOde.getTipo();
			if(logger.isDebugEnabled())logger.debug("LEA - EL TIPO DE ODE ES <" + tipo+">, se tratara de hacer la CONVERSION");
			//Hacemos la transformación a scorm 2004	
			if (tipo !=null) {
				String oriTrans="";
				String destTrans="";
				//if(logger.isDebugEnabled())logger.debug("LEA-Entramos en la transformacion");
				TransformadorSaxonImpl transformador = new TransformadorSaxonImpl();					
				if (tipo.equals(ConstantesAgrega.SCORM_12)) {
					xsl=Utilidades.getPropertyValue("xslt_scorm_12");
					if(logger.isDebugEnabled())logger.debug("LEA - EL ODE ES SCORM 12");
					
				}else if (tipo.equals(ConstantesAgrega.IMS_CP)) {
					xsl=Utilidades.getPropertyValue("xslt_ims_cp");
					if(logger.isDebugEnabled())logger.debug("LEA - EL ODE ES IMSCP");
				}
				//Si xsl es "" es que no es ni scorm_12 ni ims_cp sino scorm2004
				if (!xsl.equals("")) {
					if(logger.isDebugEnabled())logger.debug("LEA - EL VALOR DE XSL ES: <" +  xsl+ ">");
					oriTrans = loc.getPath() +"/" +MANIFEST_NAME;
					destTrans = loc.getPath() + "/imsmanifestTransformado.xml";
					transformador.transformar(xsl, oriTrans, destTrans);
					// Falta renombrar el imsmanifestTransformado a imsmanifest pq asi está mal!!
					File fichOri = new File(oriTrans);
					/*
					 * Dependiendo del SO, no se puede renombrar un fichero a otro que ya existe.
					 * Borro el original antes de renombrar
					 */
					boolean deleteOri = fichOri.delete();
					if(!deleteOri) logger.error("No se ha podido borrar " + fichOri);
					File fichDest = new File(destTrans);
					boolean renombrado=fichDest.renameTo(fichOri);
					if (renombrado)
						logger.debug("LEA - SI SE HA RENOMBRADO!!");
					else
						logger.debug("LEA - NOOOO SE HA RENOMBRADO!!!");
					
					logger.info("LEA - HEMOS TRANSFORMADO DE <" + oriTrans + "> a <" + destTrans+">");
			}
				//Una vez transformado el manifest a 2004, copiamos los esquemas
			this.copiarEsquemas(pathtempDestino);
			logger.debug("LEA - HEMOS COPIADOS LOS ESQUEMAS");
		}
		} catch (Exception e1) {
			logger.error("No se pudieron copiar los esquemas al importar un pif con el error: ", e1);
			// borramos todo lo que hayamos hecho hasta ahora
			this.eliminarOdes(new String[]{idOde});
			return new DescompriveYvalidaVO(idOde, new String[]{getPropertyValueI18n(ERROR_COPIANDO_ESQUEMAS)},false);
		}
//		 validador
		SrvValidadorService validadorService = this.getSrvValidadorService();
		// reaalizamos una validacion ligera en lugar de carga ode.
		ValidaVO valid = validadorService.obtenervalidacionLigera(loc.getPath(), "CA");
		if(logger.isDebugEnabled())logger.debug("Validando el ODE a importar[" + loc.getPath() + "] : Valido["
				+ valid.getEsValidoManifest().booleanValue() + "]");

		// la hora de testear cuidado con el validador
		if (valid.getEsValidoManifest().booleanValue()) {
			// Extraemos el manifest del ODE.
			File manifestFile = new File(loc.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(manifestFile);
			if(logger.isDebugEnabled())
			logger.debug("Validado el ODE [" + loc.getPath() + "]!!");
			
			String resultadoVocab = this.comprobarVocabulariosYFechas(imsmanifest);
			
//////////	Metemos la información de validación ligera ///////////////////
			if ((valid.getResultadoValidacion()!=null) && (!valid.getResultadoValidacion().equals(""))) {
				resultadoVocab = new StringBuffer(resultadoVocab).append(valid.getResultadoValidacion()).toString();
				if(logger.isDebugEnabled())logger.debug("ASC- SACAMOS EL VALOR DE RESULTADO VALIDACION " + valid.getResultadoValidacion().toString());
			}
			//Metemos la información sobre el tipo de transformación que se ha realizado
			if (!xsl.equals("") && (!tipo.equals(""))) {
				if(logger.isDebugEnabled())logger.debug("se ha transformado de <" + tipo + "> a SCORM2004");
				resultadoVocab = new StringBuffer(resultadoVocab).append(i18n.getString("publicacion.origenTransformacion"))
					.append(tipo).append(" ").append(i18n.getString("publicacion.transforma2004")).toString();
				//if(logger.isDebugEnabled())logger.debug("SACAMOS EL VALOR DE RESULTADO de resultadoVocab " + resultadoVocab);					
			}
//			 Por ultimo, en caso de que todo haya ido bien, tenemos que
			// recubrir el MEC y el identificador del
			// manifest que tenga este ODE con el identificador UUID que
			// utilizamos en la plataforma, ya que este
			// ODE esta en estado CREADO => esta en el taller. Para ello
			// vamos autilizar el mismo metodo que ya
			// existe y hace todo esto con el identificador MEC en el
			// momento de publicar, pero le vamos a pasar
			// el identificador de taller (el UUID)

//			cambiaUUIDxMEC(idOde, loc.getPath(), imsmanifest, AgregaPropertiesImpl.getInstance()
//					.getProperty(AgregaProperties.CATALOGO_AGREGA)); // utilizamos
//																		// el
//																		// catalogo
//																		// agrega

			//***********************
			//Si se llamara al metodo cambiaUUIDxMEC no habria quitar esta lineas porque ya se escribe el manifest en dicho método
//			if(logger.isDebugEnabled())
//				logger.debug("Escribiendo Manifest modificado");
				File file = new File(loc.getPath(), MANIFEST_NAME);
				this.getScormDao().escribirODE(imsmanifest, file);

				if (logger.isDebugEnabled())
					logger.debug("Manifest modificado con exito. Manifest.id = " + imsmanifest.getIdentifier());
			//*************************
			
			// manifest en la posicion exacta
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();

			Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
			if (lom != null) {
				LomAgrega lomAgrega = new LomAgrega(lom);
				idioma=lomAgrega.getMetaMetadataAgrega().getIdioma();
				titulo = lomAgrega.getGeneralAgrega().getTitulo(idioma);
				// Modificamos la tabla ODE para incorporar el titulo del LOM
				this.modificarOde(idOde, titulo);
			} else {
				logger.warn("El Lom del manifest <" + idOde + "> no tiene objeto general, el nombre del fichero sera el titulo del ODE");
			}
			
			if(resultadoVocab.equals(""))
				return new DescompriveYvalidaVO(idOde, new String[]{},true);
			else
				return new DescompriveYvalidaVO(idOde, resultadoVocab.split(SPLITTER),true);			

		}// si no es válido rollback
		else {
			// borrar la info
			// borramos todo lo que hayamos hecho hasta ahora
			this.eliminarOdes(new String[]{idOde});

			// Lanzamos una excepción para que haga el rollback de las bbdd
			// Ahora no lanzamos la excepcion pq no hay nada que deshacer
			// String errorValidacion="";
			// if (valid.getErrores() != null && valid.getErrores().length
			// >0)
			// {
			// errorValidacion = valid.getResultadoValidacion();
			// // errorValidacion = ""+
			// getPropertyValueI18n(LINEA_ERROR)+"["+valid.getErrores()[0].getLinea()+"]
			// "+
			// //
			// getPropertyValueI18n(COLUMNA_ERROR)+"["+valid.getErrores()[0].getColumna()+"]
			// "+
			// //
			// getPropertyValueI18n(TEXTO_ERROR)+"["+valid.getErrores()[0].getMensaje()+"]";
			// }
			logger.warn("Atencion: no se ha validado el ODE [" + loc.getPath()
					+ "] correctamente. No se ha creado el Pif, error: <" + valid.getResultadoValidacion() + ">");
			return new DescompriveYvalidaVO(idOde, valid.getResultadoValidacion().split(";"),false);
		}
		}catch(Exception e) {
			logger.error("Fallo creando PIF con fichero[" + titulo + "] )", e);
			// borramos todo lo que hayamos hecho hasta ahora
			this.eliminarOdes(new String[]{idOde});
//			throw new Exception(e);
			return null;
		}
	
	}
	
	/*
	 * Copiamos los esquemas XSD a la carpeta del ODE
	 */

	private void copiarEsquemas(String rutaDestino) throws Exception {
		File ficheroNuevo = new File(rutaDestino);
		File ficheroViejo = new File(Utilidades.getPropertyValue("carpeta.schema"));

		if (ficheroViejo.exists()) {
			UtilesFicheros.copiar(ficheroViejo, ficheroNuevo);
		} else {
			logger.warn("El fichero origen no existe");
			throw new Exception("El fichero origen " + ficheroViejo.getName() + " no existe en la ruta "
					+ ficheroViejo.getPath());
		}

	}
		
		
	/** obtener el property internacionalizado* */
		private static String getPropertyValueI18n(String sKey) {
			String idiomaUser = null;
			String retorno = null;
			try {
				idiomaUser = LdapUserDetailsUtils.getIdioma();
				retorno = getPropertyValueI18n(sKey, idiomaUser);
			} catch (Exception e) {
				logger.warn("Excepcion intentando obtener propiedad [" + sKey + "] del fichero de propiedades i18n ["
						+ idiomaUser + "] del publicador[" + e.getCause() + "]", e);
			}
			return retorno;
		}

		/** obtener el property internacionalizado* */
		private static String getPropertyValueI18n(String sKey, String idioma) {
			String idiomaUser = null;
			ResourceBundle fichero = null;
			String retorno = null;
			try {
				idiomaUser = idioma;
				// No tengo mapa con los bundles de los idiomas o no tengo cargado
				// el del idioma, lo cargo e inicializo
				if (propsI18n == null || (fichero = (ResourceBundle) propsI18n.get(idiomaUser)) == null) {
					if (propsI18n == null) // inicializo la variable
						propsI18n = new HashMap();
					if ((fichero = (ResourceBundle) propsI18n.get(idiomaUser)) == null) // no
																						// hay
																						// fichero
																						// de
																						// bundle
																						// para
																						// este
																						// idioma,
																						// lo
																						// cargo
						addBundleFile(idiomaUser);
				}
				if ((fichero = (ResourceBundle) propsI18n.get(idiomaUser)) == null)
					throw new Exception("Error intentando acceder al fichero de propiedades[i18n del publicador con " +
							"idioma[" + idiomaUser
							+ "]. El fichero no existe.");
				retorno = fichero.getString(sKey);
				if (logger.isDebugEnabled())
					logger.debug("propiedad internacionalizada obtenida[" + sKey + "]->[" + retorno + "]");
			} catch (Exception e) {
				logger.warn("Excepcion intentando obtener propiedad [" + sKey + "] del fichero de propiedades i18n["
						+ idiomaUser + "] del publicador[" + e.getCause() + "]", e);
			}
			// devolvemos la propiedad
			return retorno;
		}
		
		
		/*
		 * Añade un bundle para ese idioma a la coleccion de bundles y lo devuelve
		 */
		private static ResourceBundle addBundleFile(String idioma) {
			// Chequeamos que exista el fichero de bundle para el idioma dado.
			// Si no existe el fichero de properties para el idioma, no lo añadimos
			ResourceBundle fichero = null;
			try {
//				fichero = ResourceBundle.getBundle(DescomprimeYvalidaImpl.FILE_NAME_I18N, new Locale(idioma));
				fichero = ResourceBundle.getBundle(DescomprimeYvalidaImpl.FILE_NAME_OFFLINE, new Locale(idioma));
			} catch (RuntimeException e) {
				// No existe un resource bundle para un idioma dado
				logger.warn("Excepcion intentando buscar el fichero de bundle para el idioma [" + idioma + "]["
						+ e.getCause() + "]");
				return null;
			}
			if (fichero == null)
				return fichero;
			propsI18n.put(idioma, fichero);
			return fichero;
		}

		@Override
		protected void handleEliminaLocalizador(String idOde) throws Exception {
			//if(logger.isDebugEnabled()) logger.debug("Borrando el localizador " + idOde);
			this.getSrvLocalizadorService().eliminarLocalizador(idOde);
			if(logger.isDebugEnabled()) logger.debug("Localizador " + idOde + " eliminado");
			
		}

		@Override
		protected DescargaVO handleDescargar(String idOde,String tipoOde) throws Exception {
			// TODO Adaptar a cambio pedido en C27 para que se lance warning de
			// validacion en vez de error.
		//	logger.info("Generando descarga de " + idOde + " con formato " + tipoOde);
			DescargaVO result = null;
			try {
				TipoPifVO tipoPifVO = new TipoPifVO();
				tipoPifVO.setIdODE(idOde);
				tipoPifVO.setTipoPif(tipoOde);
				PaquetePifVO paquete = this.getSrvEntregarService().generarPaquetePIFTipoPIF(tipoPifVO);
				result = new DescargaVO();
				result.setIdOde(idOde);
				result.setUrl(paquete.getHref());
				result.setValido(paquete.getResultadoValidacion().getEsValidoManifest());
				String resultadoValidacion = paquete.getResultadoValidacion().getResultadoValidacion();
				// Parsea los mensajes del validador (si los hubiera)
				String[] mensajes = resultadoValidacion==null? new String[]{} : resultadoValidacion.split(";");
				result.setMensajes(mensajes);
			} catch (Exception e) {
				logger.error("Error inesperado al generar la descarga de <" + idOde+"> - ",e);
				throw new Exception("Error inesperado al descargar " + idOde,e);
			}
			
			return result;
		}

		@Override
		protected void handleEliminarOdes(String[] identificadores) throws Exception {

			for(int i=0;i<identificadores.length;i++) {
				try {
					this.getSrvLocalizadorService().eliminarLocalizador(identificadores[i]);
					Ode ode = getOdeDao().obtenerOde(identificadores[i]);
					if(ode!=null) getOdeDao().remove(ode);
					logger.info("Ode <" + identificadores[i] + "> eliminado");
				} catch (Exception e) {
					logger.error("Error al borrar Ode " + identificadores[i],e);
				}
			}
		}

		@Override
		protected OdeVO[] handleListarOdes() throws Exception {

			Collection odes = this.getOdeDao().loadAll(OdeDao.TRANSFORM_ODEVO);
			
			return odes == null ? new OdeVO[]{} : (OdeVO[])odes.toArray(new OdeVO[odes.size()]);
		}

		@Override
		protected void handleModificarOde(String idOde, String titulo) throws Exception {
			Ode ode = this.getOdeDao().obtenerOde(idOde);
			if(ode == null) {
				logger.warn("Se ha intentado modificar un Ode no registrado: <" + idOde+">");
				throw new Exception("No existe el ODE " + idOde);
			} else {
				ode.setTitulo(titulo);
				getOdeDao().update(ode);
			}
			
		}

		@Override
		protected DescompriveYvalidaVO handleValidarOde(String idOde,Validaciones tipoValidacion) throws Exception {
			logger.info("Validando ODE <" + idOde + " con tipo de validacion  <" + tipoValidacion.toString()+">");
			DescompriveYvalidaVO result = null;
			try {
				LocalizadorVO loc = getSrvLocalizadorService().consultaLocalizador(idOde);
				ValidaVO valVO = null;
				if(Validaciones.LIGERA.equals(tipoValidacion)) {
					valVO =getSrvValidadorService().obtenervalidacionLigera(loc.getPath(),"CA");
				} else if(Validaciones.BINARIA.equals(tipoValidacion)) {
					valVO =getSrvValidadorService().obtenerValidacionBin(loc.getPath());
				} else {
					// Completa
					valVO =getSrvValidadorService().obtenerValidacion(loc.getPath());
				}
				if(valVO!=null) {
					result = new DescompriveYvalidaVO();
					result.setIdOde(idOde);
					result.setMensajes(valVO.getResultadoValidacion()==null?new String[]{}:valVO.getResultadoValidacion().split(";"));
					result.setValido(valVO.getEsValidoManifest());
				}
			} catch (Exception e) {
				logger.error("Error inesperado durante la validacion del ODE - ",e);
			}
			return result;
		}

		@Override
		protected void handleCreacion(String idOde, String titulo) throws Exception {
			Ode ode = null;
			try {
				ode = (Ode)getOdeDao().create(OdeDao.TRANSFORM_NONE, idOde, titulo);
			} catch (Exception e) {
				logger.error("Error en la creacion del ODE - ",e);
				throw new Exception("Error en la creacion del ODE",e);
			}
			try {
				this.getSrvLocalizadorService().crearLocalizadorNoPublicado(LdapUserDetailsUtils.getLogin(), idOde);
			} catch (Exception e) {
				getOdeDao().remove(ode);
				logger.error("Error creando el localizador de <" + idOde+"> - ",e);
				throw new Exception("Error creando el localizador de " + idOde,e);
			}
		}

	
	/**
	 * Conecta con el nodo Agrega identificado por el parametro <em>url</em>
	 * para subir a la carpeta personal del usuario los objetos contenidos en la
	 * carpeta <em>path</em>.
	 * 
	 * @param identificadores
	 * @param url
	 * @param usuario
	 * @param passwd
	 * @return ResultadoUploaderVO[] VO que contiene los parámetros titulo,mensajesError y subido
	 * @throws AutenticacionException
	 *             Si la autenticacion con los usuario y password proporcionados
	 *             falla.
	 * @throws ConexionException
	 *             Si se produce un fallo de conexion con el nodo Agrega.
	 * @throws AutorizacionException
	 *             Si el usuario no tiene los roles necesarios para subir a carpeta personal(docente o administrador).
	 */

		protected ResultadoUploaderVO[] handleSubirObjetosACarpetaPersonal(String[] identificadores,
				 String url, String usuario, String passwd) throws Exception {

			ResultadoUploaderVO[] resultadoUploader=new ResultadoUploaderVO[identificadores.length];
			SrvDRIService servicio=this.obtenerConexion(url);
			Locale locale=new Locale(LdapUserDetailsUtils.getIdioma());
			ResourceBundle i18n = ResourceBundle.getBundle(FILE_NAME_OFFLINE,locale);
			if(identificadores!=null && identificadores.length>0){
				for(int i=0;i<identificadores.length ;i++){
					ResultadoUploaderVO resultado=new ResultadoUploaderVO();
					try{
						PaquetePifDriVO paquete = this.getSrvEntregarService().generarPaquetePIF(identificadores[i]);
						Ode ode =null;
						try{
							ode = this.getOdeDao().obtenerOde(identificadores[i]);
						}catch(Exception et){
							logger.error("Error al obtener ode con identificador <" +identificadores[i]+"> - ",et);
							throw new Exception ("Error al obtener ode con identificador " +identificadores[i], et);
						}
						resultado.setTitulo(ode.getTitulo());
						
						Integer vuelta=servicio.crear(usuario, passwd, paquete.getPaquetePIF());
						logger.debug("Al hacer la llamada a DRI devuelve "+vuelta.toString());
						resultado.setNombrePaquete(paquete.getPaquetePIF().getName());
						
						String[] mensajes=new String[1];
						
						if(vuelta.equals(new Integer(0))){
							
							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);//En los mensajes devuelvo null si se ha subido bien
							resultado.setSubido(true);
						}else if(vuelta.equals(new Integer(1)) ){
								
//							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
//							resultado.setMensajesError(mensajes);
//							resultado.setSubido(false);
							logger.warn("Estamos en el servicio lanzando el error de  AutenticacionException <"+i18n.getString("DRI."+vuelta.toString())+">");
							throw new AutenticacionException(i18n.getString("DRI."+vuelta.toString()));
						}
						else if(vuelta.equals(new Integer(2)) ){
							
//							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
//							resultado.setMensajesError(mensajes);
//							resultado.setSubido(false);
							logger.warn("Estamos en el servicio lanzando el error de  AutorizacionException <"+i18n.getString("DRI."+vuelta.toString())+">");
							throw new AutorizacionException(i18n.getString("DRI."+vuelta.toString()));
						}
						else if(vuelta.equals(new Integer(4)) ){
							logger.warn("El ode tiene erroes de validación");
							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);
							resultado.setSubido(false);
						}else{

							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);
							resultado.setSubido(false);
						}
	
					}
					catch (AutenticacionException ex) {
						logger.error("El usuario no está dado de alta en nuestra plataforma ", ex);
						throw ex;
					}
					catch (AutorizacionException et) {
						logger.error("El usuario no tiene los roles necesarios para poder subir los odes a la plataforma, a su carpeta personal ",et);
						throw et;
					}
					catch (ConexionException er) {
						logger.error("La url no es correcta ",er);
						throw er;
					}
					catch(AxisFault e){
						if((e.getCause()!=null && (e.getCause() instanceof java.net.ConnectException)) || e.getFaultString().contains("404")) {
							logger.error("Error al intentar subir objetos al catalogador <" + e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
							throw new ConexionException ("Error al intentar subir objetos al catalogador, con identificador "+ identificadores[i], e);
						}
						else{	
							logger.error("Error de validacion del ODE <"+ e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
							resultado.setMensajesError(new String[]{i18n.getString("DRI.6")});
							resultado.setSubido(false);
							}
					}
					catch(IllegalArgumentException e){
						logger.error("Error al intentar subir objetos a la carpeta personal, IllegalArgumentException (" + e.getClass() + ", causa: " +  (e.getCause()==null?"null":e.getCause().getClass()) + "), con identificador "+ identificadores[i],e);
						throw new Exception ("Error al intentar subir objetos a la carpeta personal, con identificador "+ identificadores[i], e);
					}
					catch(Exception e){
						logger.error("Error al intentar subir objetos a la carpeta personal <" + e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
						throw new Exception ("Error al intentar subir objetos a la carpeta personal, con identificador "+ identificadores[i], e);
					}
					resultadoUploader[i]=resultado;
				}
			}
			return resultadoUploader;
		}

		

		/**
		 * Conecta con el nodo Agrega identificado por el parametro <em>url</em>
		 * para subir al repositorio los objetos contenidos en la
		 * carpeta <em>path</em>.
		 * 
		 * @param identificadores
		 * @param url
		 * @param usuario
		 * @param passwd
		 * @return ResultadoUploaderVO[] VO que contiene los parámetros titulo,mensajesError y subido
		 * @throws AutenticacionException
		 *             Si la autenticacion con los usuario y password proporcionados
		 *             falla.
		 * @throws ConexionException
		 *             Si se produce un fallo de conexion con el nodo Agrega.
		 * @throws AutorizacionException
		 *             Si el usuario no tiene los roles necesarios para subir a catalogar(docente, administrador).
		 */

		protected ResultadoUploaderVO[] handleSubirObjetosACatalogador(
				String[] identificadores,  String url, String usuario,
				String passwd) throws Exception {
			ResultadoUploaderVO[] resultadoUploader=new ResultadoUploaderVO[identificadores.length];
			SrvDRIService servicio=this.obtenerConexion(url);
			if(identificadores!=null && identificadores.length>0){
				for(int i=0;i<identificadores.length ;i++){
					ResultadoUploaderVO resultado=new ResultadoUploaderVO();
					Locale locale=new Locale(LdapUserDetailsUtils.getIdioma());
					ResourceBundle i18n = ResourceBundle.getBundle(FILE_NAME_OFFLINE,locale);
					try{
						PaquetePifDriVO paquete = this.getSrvEntregarService().generarPaquetePIF(identificadores[i]);
						
						Ode ode =null;
						try{
							ode = this.getOdeDao().obtenerOde(identificadores[i]);
						}catch(Exception et){
							logger.error("Error al obtener ode con identificador <" +identificadores[i]+"> - ",et);
							throw new Exception ("Error al obtener ode con identificador " +identificadores[i], et);
						}
						resultado.setTitulo(ode.getTitulo());
						
						
						Integer vuelta=servicio.presentarCatalogar(usuario, passwd, paquete.getPaquetePIF());
						logger.debug("Al hacer la llamada a DRI devuelve "+vuelta.toString());
						resultado.setNombrePaquete(paquete.getPaquetePIF().getName());
						
						String[] mensajes=new String[1];
						
						if(vuelta.equals(new Integer(0))){
							
							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);//En los mensajes devuelvo null si se ha subido bien
							resultado.setSubido(true);
						}else if(vuelta.equals(new Integer(1)) ){
								
							logger.warn("Estamos en el servicio lanzando el error de  AutenticacionException <"+i18n.getString("DRI."+vuelta.toString())+">");
							throw new AutenticacionException(i18n.getString("DRI."+vuelta.toString()));
						}
						else if(vuelta.equals(new Integer(2)) ){
							
							logger.warn("Estamos en el servicio lanzando el error de  AutorizacionException <"+i18n.getString("DRI."+vuelta.toString())+">");
							throw new AutorizacionException(i18n.getString("DRI."+vuelta.toString()));
						}
						else if(vuelta.equals(new Integer(4)) ){
							logger.warn("El ode tiene erroes de validación");
							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);
							resultado.setSubido(false);
						}else{

							mensajes[0]=i18n.getString("DRI."+vuelta.toString());
							resultado.setMensajesError(mensajes);
							resultado.setSubido(false);
						}
	
					}
					catch (AutenticacionException ex) {
						logger.error("El usuario no está dado de alta en nuestra plataforma ", ex);
						throw ex;
					}
					catch (AutorizacionException et) {
						logger.error("El usuario no tiene los roles necesarios para poder subir los odes a la plataforma, a catalogador ",et);
						throw et;
					}
					catch (ConexionException er) {
						logger.error("La url no es correcta ",er);
						throw er;
					}
					catch(AxisFault e){
						if(e.getCause()!=null && (e.getCause() instanceof java.net.ConnectException)) {
							logger.error("Error al intentar subir objetos al catalogador <" + e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i],e);
							throw new ConexionException ("Error al intentar subir objetos al catalogador, con identificador "+ identificadores[i]+"> - ", e);
						}
						else{	
							logger.error("Error de validacion del ODE <"+ e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
							resultado.setMensajesError(new String[]{i18n.getString("DRI.6")});
							resultado.setSubido(false);
							}
					}
					catch(IllegalArgumentException e){
						logger.error("Error al intentar subir objetos a la carpeta personal, IllegalArgumentException <" + e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
						throw new Exception ("Error al intentar subir objetos a la carpeta personal, con identificador "+ identificadores[i], e);
					}
					catch(Exception e){
						logger.error("Error al intentar subir objetos a catalogador <" + e.getClass() + ">, causa: <" +  (e.getCause()==null?"null":e.getCause().getClass()) + ">, con identificador <"+ identificadores[i]+"> - ",e);
						throw new Exception ("Error al intentar subir objetos a catalogaodr, con identificador "+ identificadores[i], e);
					}
					resultadoUploader[i]=resultado;
	
				}
			}
			return resultadoUploader;
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
		private void cambiaUUIDxMEC(String mec, String localizador, Manifest manifest, String catalogo) {
			if (logger.isDebugEnabled())
				logger.debug("Entrando en cambiaUUIDxMEC con mec <" + mec + "> y localizador <" + localizador+">");
			try {

				if (logger.isDebugEnabled())
					logger.debug("MEC = " + mec + "; Identifier = " + manifest.getIdentifier());

				// Cambiar el mec si no es igual
				if (!mec.equals(manifest.getIdentifier())) {
					if(logger.isDebugEnabled()) logger.debug("Cambiando el Manifest.getIdentifier");
					manifest.setIdentifier(mec);
				}

//				// 
//				if(logger.isDebugEnabled())
//				logger.debug("Buscando LOM-ES principal del manifiesto");
//				ManifestAgrega manAgrega = new ManifestAgrega(manifest);
//				// Lom lom = manAgrega.obtenerLom(mec, null);
//				Lom lom = manAgrega.obtenerLom(manifest.getIdentifier(), null);
//				if (lom != null) {
//					if(logger.isDebugEnabled())
//					logger.debug("Manipulando lom");
//					LomAgrega lomAgrega = new LomAgrega(lom);
//
//					// hay que comprobar que es distinto el agrega del mec, si es
//					// así lo recubrimos.
//					if (lomAgrega.getGeneralAgrega() != null
//							&& !lomAgrega.getGeneralAgrega().getPrimerIdentificador().equals(mec)) {
//						lomAgrega.getGeneralAgrega().setIdentificadorMEC(catalogo, mec);
//
//						// Campo 3.1 - Identificador del LOM-ES con sufijo meta
//						lomAgrega.getMetaMetadataAgrega().setIdentificadorMEC(catalogo, mec + "-meta");
//						if(logger.isDebugEnabled())
//						logger.debug("MEC introducido en el LOM-ES del manifiesto");
//						lom.setMetaMetadata(lomAgrega.getMetaMetadataAgrega().getMetaMetadata());
//						if(logger.isDebugEnabled())
//						logger.debug("Re-introduciendo Lom en Manifest");
//						// manAgrega.setLom(mec, null, lom);
//						manAgrega.setLom(manifest.getIdentifier(), null, lom);
//						manifest = manAgrega.getManifest();
//						if(logger.isDebugEnabled())
//						logger.debug("Lom modificado en objeto Manifest");
//					} else {
//						logger.warn("El Lom del manifest [" + mec + "] no tiene objeto general o el primer identificador"
//								+ " que contiene es igual al mec");
//					}
//				}
//				if(logger.isDebugEnabled())
//				logger.debug("Escribiendo Manifest modificado");
				File prueba = new File(localizador, MANIFEST_NAME);
				this.getScormDao().escribirODE(manifest, prueba);

				if (logger.isDebugEnabled())
					logger.debug("Manifest modificado con exito. Manifest.id = " + manifest.getIdentifier());

			} catch (Exception e) {
				logger.error("Error durante la modificacion del manifest publicado. No se ha podido introducir el MEC. - ", e);
			}
		}
		
		private String comprobarVocabulariosYFechas(Manifest manifest) throws Exception{
//			logger.debug("Recogiendo vocabularios controlados...");
			String vocabControlados = Utilidades.getPropertyValue("vocabulariosControlados");
//			String vocabControlados = "1.3,1.7,1.8,2.2,2.3.1,3.2.1,3.4,4.1,4.4.1.1,4.4.1.2,5.1,5.2,5.3,5.4,5.5,5.6,5.8,5.11,5.12,6.1,6.2,6.4.1,7.1,9.1";
			String[] vocabularios = vocabControlados.split(",");
			if (logger.isDebugEnabled())logger.debug("Vamos a obtener los valores de los vocabularios");
			
			SrvVocabulariosControladosService vocabSrv= this.getSrvVocabulariosControladosService();
			VocabularioVO[] vocabVO = vocabSrv.obtenerCombos(vocabularios, "en");
			
			/********vocabularios de general********/
			VocabularioVO idiomaV = vocabVO[0];//1.3
			ArrayList aIdiomaV = this.obtenValoresVocabulario(idiomaV);
			
			VocabularioVO estructuraV = vocabVO[1];//1.7
			ArrayList aEstructuraV = this.obtenValoresVocabulario(estructuraV);
			
			VocabularioVO nivelAgregacionV = vocabVO[2];//1.8
			ArrayList aNivelAgregacionV = this.obtenValoresVocabulario(nivelAgregacionV);
			
			/********vocabularios de ciclo de vida********/
			VocabularioVO estadoV = vocabVO[3];//2.2
			ArrayList aEstadoV = this.obtenValoresVocabulario(estadoV);
			
			VocabularioVO tipoContribucionV = vocabVO[4];//2.3.1
			ArrayList aTipoContribucionV = this.obtenValoresVocabulario(tipoContribucionV);
			
			/********vocabularios de meta-metadatos********/
			VocabularioVO tipoContribucionMetaV = vocabVO[5];//3.2.1
			ArrayList aTipoContribucionMetaV = this.obtenValoresVocabulario(tipoContribucionMetaV);
			
			VocabularioVO idiomaCatalogacionV = vocabVO[6];//3.4
			ArrayList aIdiomaCatalogacionV = this.obtenValoresVocabulario(idiomaCatalogacionV);
			
			/********vocabularios de tecnica********/
			VocabularioVO formatoV = vocabVO[7];//4.1
			ArrayList aFormatoV = this.obtenValoresVocabulario(formatoV);
			
			VocabularioVO tipoRequisitoV = vocabVO[8];//4.4.1.1
			ArrayList aTipoRequisitoV = this.obtenValoresVocabulario(tipoRequisitoV);
			
			VocabularioVO nombreRequisitoV = vocabVO[9];//4.4.1.2
			ArrayList aNombreRequisitosV = this.obtenValoresVocabulario(nombreRequisitoV);
			
			/********vocabularios de uso educativo********/
			VocabularioVO tipoInteractividadV = vocabVO[10];//5.1
			ArrayList aTipoInteractividadV = this.obtenValoresVocabulario(tipoInteractividadV);
			
			VocabularioVO tipoRecursoV = vocabVO[11];//5.2
			ArrayList aTipoRecursoV = this.obtenValoresVocabulario(tipoRecursoV);
			
			VocabularioVO nivelInteractividadV = vocabVO[12];//5.3
			ArrayList aNivelInteractividadV = this.obtenValoresVocabulario(nivelInteractividadV);
			
			VocabularioVO densidadSemanticaV = vocabVO[13];//5.4
			ArrayList aDensidadSemanticaV = this.obtenValoresVocabulario(densidadSemanticaV);
			
			VocabularioVO destinatarioV = vocabVO[14];//5.5
			ArrayList aDestinatarioV = this.obtenValoresVocabulario(destinatarioV);
			
			VocabularioVO contextoV = vocabVO[15];//5.6
			ArrayList aContextoV = this.obtenValoresVocabulario(contextoV);
			
			VocabularioVO dificultadV = vocabVO[16];//5.8
			ArrayList aDificultadV = this.obtenValoresVocabulario(dificultadV);
			
			VocabularioVO idiomaDestinatarioV = vocabVO[17];//5.11
			ArrayList aIdiomaDestinatarioV = this.obtenValoresVocabulario(idiomaDestinatarioV);
			
			VocabularioVO procesoCognitivoV = vocabVO[18];//5.12
			ArrayList aProcesoCognitivoV = this.obtenValoresVocabulario(procesoCognitivoV);
			
			/********vocabularios de derechos********/
			VocabularioVO costeV = vocabVO[19];//6.1
			ArrayList aCosteV = this.obtenValoresVocabulario(costeV);
			
			VocabularioVO derechosAutorV = vocabVO[20];//6.2
			ArrayList aDerechosAutorV = this.obtenValoresVocabulario(derechosAutorV);
			
			VocabularioVO tipoAccesoV = vocabVO[21];//6.4.1
			ArrayList aTipoAccesoV = this.obtenValoresVocabulario(tipoAccesoV);
			
			/********vocabularios de relacion********/
			VocabularioVO tipoRelacionV = vocabVO[22];//7.1
			ArrayList aTipoRelacionV = this.obtenValoresVocabulario(tipoRelacionV);
			
			/********vocabularios de clasificacion********/
			VocabularioVO propositoV = vocabVO[23];//9.1
			ArrayList aPropositoV = this.obtenValoresVocabulario(propositoV);
			
			StringBuffer resultado = new StringBuffer("");
			
			
			ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);

			HashMap lomesMap = manifestAgrega.recuperarLomesMap();
			
			Set padreLomes = lomesMap.keySet();
			Object[] padresLomesArray= padreLomes.toArray();
			
			for(int i=0; i< padresLomesArray.length;i++){
				boolean haCambiado = false;
				String idPadre = (String) padresLomesArray[i];
				Lom lomes = (Lom) lomesMap.get(padresLomesArray[i]);
				LomAgrega lomAgrega = new LomAgrega(lomes);
				
//				/**********************General**********************/
				if (logger.isDebugEnabled())logger.debug("Comprobando campos de la categoría General");
				GeneralAgrega generalAgrega = lomAgrega.getGeneralAgrega();
				List idiomas = generalAgrega.getIdiomasAv();

				boolean cambioIdiomas = false;
				ArrayList new_Idiomas= new ArrayList();
				for(int k = 0; k < idiomas.size();k++){
					String idioma = (String) idiomas.get(k);
					if(idioma !=null && !aIdiomaV.contains(idioma)){
//					if(idioma !=null){
						logger.debug("Valor erroneo de idioma: " + idioma + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 .append(getPropertyValueI18n("vocabularios.idioma"))
								 .append(idioma).append(SPLITTER);
						haCambiado = true;
						cambioIdiomas = true;
					}
					else{
						new_Idiomas.add(idioma);
					}
				}
				if(cambioIdiomas)
					generalAgrega.setIdiomasAv(new_Idiomas);
				
				String estructura = generalAgrega.getEstructuraAv();
				if(estructura !=null && !aEstructuraV.contains(estructura)){
//				if(estructura !=null){
					if (logger.isDebugEnabled())	logger.debug("Valor erroneo de estructura: " + estructura +". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 .append(getPropertyValueI18n("vocabularios.estructura"))
							 .append(estructura).append(SPLITTER);

					generalAgrega.setEstructuraAv(null);
					haCambiado = true;			
					
				}
				
				String nivelAgregacion = generalAgrega.getNivelDeAgregacionAv();
				if(nivelAgregacion !=null && !aNivelAgregacionV.contains(nivelAgregacion)){
//				if(nivelAgregacion !=null){
					if (logger.isDebugEnabled())	logger.debug("Valor erroneo de nivel de agregacion: " + nivelAgregacion +". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 .append(getPropertyValueI18n("vocabularios.nivelAgregacion"))
							 .append(nivelAgregacion).append(SPLITTER);
					generalAgrega.setNivelDeAgregacionAv(null);
					haCambiado = true;
				}
				
//				/**********************Ciclo de vida**********************/
				logger.debug("Comprobando campos de la categoría Ciclo de vida");
				LifeCycleAgrega cicloAgrega = lomAgrega.getLifeCycleAgrega();
				
				String estado = cicloAgrega.getEstatusAv();
				if(estado !=null && !aEstadoV.contains(estado)){
//				if(estado !=null){
					if (logger.isDebugEnabled())	logger.debug("Valor erroneo de estado: " + estado +". se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
					 		 .append(getPropertyValueI18n("vocabularios.estado"))
					 		 .append(estado).append(SPLITTER);
					cicloAgrega.setEstatusAv(null);
					haCambiado = true;
				}
				
				ArrayList<ContribucionAgrega> contribuciones = cicloAgrega.getContribucionesAv();
				ArrayList<ContribucionAgrega> new_contribuciones= new ArrayList<ContribucionAgrega>();
				boolean cambioContrib = false;
				for(int j = 0; j < contribuciones.size(); j++){
					ContribucionAgrega contribucion = contribuciones.get(j);
					String tipo = contribucion.getTipo();
//					if(tipo !=null){
					if(tipo !=null && !aTipoContribucionV.contains(tipo)){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de contribucion: " + tipo + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
						 		 .append(getPropertyValueI18n("vocabularios.tipoContribCiclo"))
						 		 .append(tipo).append(SPLITTER);
						contribucion.setTipo(null);
//						contribuciones.set(j, contribucion);
						haCambiado = true;
						cambioContrib = true;
					}
					
					FechaAgrega fecha = contribucion.getFecha();
					if(fecha!=null && fecha.getFecha()!=null){
						String sFecha = fecha.getFecha();
						boolean correcta = this.comprobarFecha(sFecha);

						if(!correcta){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de fecha: " + sFecha + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
					 		 		 .append(getPropertyValueI18n("vocabularios.fechaCiclo"))
					 		 		 .append(sFecha).append(SPLITTER);
							fecha.setFecha(null);
							contribucion.setTipo(null);
//							contribuciones.set(j, contribucion);
							haCambiado = true;
							cambioContrib = true;
						}
						
					}
					
					if((contribucion.getEntidades()!=null && contribucion.getEntidades().size()>0)||
						(contribucion.getTipo()!=null && !contribucion.getTipo().equals("")) ||
						(contribucion.getFecha()!=null && contribucion.getFecha().getFecha()!=null && !contribucion.getFecha().getFecha().equals("")) ||
						(contribucion.getFecha()!=null && contribucion.getFecha().getDescripciones()!=null && contribucion.getFecha().getDescripciones().size()>0)){
						new_contribuciones.add(contribucion);
					}
				}
				if(cambioContrib){
					cicloAgrega.setContribucionesAv(new_contribuciones);
				}
				
//				/**********************Meta-metadatos**********************/
				logger.debug("Comprobando campos de la categoría Meta-metadatos");
				MetaMetadataAgrega metadatosAgrega =lomAgrega.getMetaMetadataAgrega();
				String idiomaCatalogacion = metadatosAgrega.getIdiomaAv();
//				if(idiomaCatalogacion !=null){
				if(idiomaCatalogacion !=null && !aIdiomaCatalogacionV.contains(idiomaCatalogacion)){
					if (logger.isDebugEnabled()) logger.debug("Valor erroneo de idioma de catalogacion: " + idiomaCatalogacion + ". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 .append(getPropertyValueI18n("vocabularios.idiomaCatalogacion"))
			 		 		 .append(idiomaCatalogacion).append(SPLITTER);
					metadatosAgrega.setIdiomasAv(null);
					haCambiado = true;
				}
				
				ArrayList<ContribucionAgrega> contribucionesMeta = metadatosAgrega.getContribucionesAv();
				ArrayList<ContribucionAgrega> new_contribucionesMeta= new ArrayList<ContribucionAgrega>();
				boolean cambioContribMeta = false;
				for(int j = 0; j < contribucionesMeta.size(); j++){
					ContribucionAgrega contribucionMeta = contribucionesMeta.get(j);
					
					String tipo = contribucionMeta.getTipo();
//					if(tipo !=null){
					if(tipo !=null && !aTipoContribucionMetaV.contains(tipo)){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de contribucion: " + tipo + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
				 		 		 .append(getPropertyValueI18n("vocabularios.tipoContribMeta"))
				 		 		 .append(tipo).append(SPLITTER);
						contribucionMeta.setTipo(null);
//						contribucionesMeta.set(j, contribucionMeta);
						haCambiado = true;
						cambioContribMeta = true;
					}
					
					FechaAgrega fecha = contribucionMeta.getFecha();
					if(fecha!=null && fecha.getFecha()!=null){
						String sFecha = fecha.getFecha();
						boolean correcta = this.comprobarFecha(sFecha);

						if(!correcta){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de fecha: " + sFecha + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 		 .append(getPropertyValueI18n("vocabularios.fechaMeta"))
			 		 		 		 .append(sFecha).append(SPLITTER);
							fecha.setFecha(null);
							contribucionMeta.setTipo(null);
//							contribucionesMeta.set(j, contribucionMeta);
							haCambiado = true;
							cambioContribMeta = true;
						}
						
					}
					if((contribucionMeta.getEntidades()!=null && contribucionMeta.getEntidades().size()>0)||
							(contribucionMeta.getTipo()!=null && !contribucionMeta.getTipo().equals("")) ||
							(contribucionMeta.getFecha()!=null && contribucionMeta.getFecha().getFecha()!=null && !contribucionMeta.getFecha().getFecha().equals("")) ||
							(contribucionMeta.getFecha()!=null && contribucionMeta.getFecha().getDescripciones()!=null && contribucionMeta.getFecha().getDescripciones().size()>0)){
							new_contribucionesMeta.add(contribucionMeta);
						}
					}
					if(cambioContribMeta){
						cicloAgrega.setContribucionesAv(new_contribucionesMeta);
					}			
			
				/**********************Tecnica**********************/
				logger.debug("Comprobando campos de la categoría Tecnica");
				TechnicalAgrega tecnicaAgrega = lomAgrega.getTechnicalAgrega();
				
				ArrayList  formatos = tecnicaAgrega.getFormatosAv();
				boolean cambioFormatos = false;
				ArrayList new_formatos = new ArrayList();
				for(int j = 0; j < formatos.size();j++){
					String formato = (String) formatos.get(j);
					if(formato !=null && !aFormatoV.contains(formato)){
//					if(formato !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de formato: " + formato + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 .append(getPropertyValueI18n("vocabularios.formato"))
								 .append(formato).append(SPLITTER);
						haCambiado = true;
						cambioFormatos = true;
					}
					else{
						new_formatos.add(formato);
					}
				}
				if(cambioFormatos)
					tecnicaAgrega.setFormatosAv(new_formatos);
					
				ArrayList requisitos = tecnicaAgrega.getRequisitosAv();
				boolean cambioRequisitos = false;
				for(int j = 0; j < requisitos.size();j++){
					ArrayList<RequisitoAgrega> orComposites = (ArrayList<RequisitoAgrega>) requisitos.get(j);
					boolean cambioOrComposites = true;
					for(int k = 0; k < orComposites.size(); k++){
						RequisitoAgrega requisito = orComposites.get(k);
						String tipo = requisito.getTipo();
						boolean cambioRequisito =false;
						if(tipo !=null && !aTipoRequisitoV.contains(tipo)){
//						if(tipo !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo requisito: " + tipo + ". se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 		 .append(getPropertyValueI18n("vocabularios.tipoRequisito"))
			 		 		 		 .append(tipo).append(SPLITTER);
							requisito.setTipo(null);
							haCambiado = true;
							cambioRequisitos = true;
							cambioOrComposites =true;
							cambioRequisito= true;
						}
						
						String nombre = requisito.getNombre();
						if(nombre !=null && !aNombreRequisitosV.contains(nombre)){
//						if(nombre !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de nombre de requisito: " + nombre + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 		 .append(getPropertyValueI18n("vocabularios.nombreRequisito"))
			 		 		 		 .append(nombre).append(SPLITTER);
							requisito.setNombre(null);
							haCambiado = true;
							cambioRequisitos = true;
							cambioOrComposites = true;
							cambioRequisito = true;
						}
						if(cambioRequisito)
							orComposites.set(k, requisito);
							
						
					}
					if(cambioOrComposites)
						requisitos.set(j, orComposites);
				}
				if(cambioRequisitos)
					tecnicaAgrega.setRequisitosAv(requisitos);
				
				/**********************Uso educativo**********************/
				logger.debug("Comprobando campos de la categoría Uso educativo");
				ArrayList<EducationalAgrega> usosEduAgrega = lomAgrega.getEducationalsAgrega();
				ArrayList<EducationalAgrega> new_usosEducativos= new ArrayList<EducationalAgrega>();
				boolean cambioUsosEdu = false;
				for(int j = 0; j < usosEduAgrega.size();j++){
					EducationalAgrega usoEducativo = usosEduAgrega.get(j);
					//***********tipoInteractividad***********
					String tipoInteractividad = usoEducativo.getTipoDeInteractividadAv();
					if(tipoInteractividad !=null && !aTipoInteractividadV.contains(tipoInteractividad)){
//					if(tipoInteractividad !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de interactividad: " + tipoInteractividad + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 .append(getPropertyValueI18n("vocabularios.tipoInteractividad"))
								 .append(tipoInteractividad).append(SPLITTER);
						usoEducativo.setTipoDeInteractividadAv(null);
						haCambiado = true;
						cambioUsosEdu=true;
					}
					//***********tipoRecurso***********							
					ArrayList<String> tipoRecurso = usoEducativo.getTiposDeRecursoAv();
					boolean cambioTipoRecurso = false;
					ArrayList new_tipoRecurso = new ArrayList();
					for(int k = 0; k < tipoRecurso.size();k++){
						String recurso = tipoRecurso.get(k);
						if(recurso !=null && !aTipoRecursoV.contains(recurso)){
//						if(recurso !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de recurso: " + recurso + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 		 .append(getPropertyValueI18n("vocabularios.tipoRecurso"))
							 		 .append(recurso).append(SPLITTER);
							haCambiado = true;
							cambioTipoRecurso = true;
							cambioUsosEdu=true;
						}
						else{
							new_tipoRecurso.add(recurso);
						}
					}
					if(cambioTipoRecurso)
						usoEducativo.setTiposDeRecursoAv(new_tipoRecurso);
					//***********nivelInteractividad***********
					String nivelInteractividad = usoEducativo.getNivelDeInteractividadAv();
					if(nivelInteractividad !=null && !aNivelInteractividadV.contains(nivelInteractividad)){
//					if(nivelInteractividad !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de nivel de interactividad: " + nivelInteractividad + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
				 		 		 .append(getPropertyValueI18n("vocabularios.nivelInteractividad"))
				 		 		 .append(nivelInteractividad).append(SPLITTER);
						usoEducativo.setNivelDeInteractividadAv(null);
						haCambiado = true;
						cambioUsosEdu=true;
					}
					//***********densidadSemantica***********
					String densidadSemantica = usoEducativo.getDensidadSemanticaAv();
					if(densidadSemantica !=null && !aDensidadSemanticaV.contains(densidadSemantica)){
//					if(densidadSemantica !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de densidad semantica: " + densidadSemantica + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 .append(getPropertyValueI18n("vocabularios.densidadSemantica"))
								 .append(densidadSemantica).append(SPLITTER);
						usoEducativo.setDensidadSemanticaAv(null);
						haCambiado = true;
						cambioUsosEdu=true;
					}
					//***********destinatarios***********
					ArrayList<String> destinatarios = usoEducativo.getDestinatariosAv();
					boolean cambioDestinatarios = false;
					ArrayList new_Destinatarios = new ArrayList();
					for(int k = 0; k < destinatarios.size();k++){
						String destinatario = destinatarios.get(k);
						if(destinatario !=null && !aDestinatarioV.contains(destinatario)){
//						if(destinatario !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de destinatario: " + destinatario + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
									 .append(getPropertyValueI18n("vocabularios.destinatario"))
									 .append(destinatario).append(SPLITTER);
							haCambiado = true;
							cambioDestinatarios = true;
							cambioUsosEdu=true;
						}
						else{
							new_Destinatarios.add(destinatario);
						}
					}
					if(cambioDestinatarios)
						usoEducativo.setDestinatariosAv(new_Destinatarios);
					//***********contextos***********
					ArrayList<String> contextos = usoEducativo.getContextosAv();
					boolean cambioContextos = false;
					ArrayList new_Contextos = new ArrayList();
					for(int k = 0; k < contextos.size();k++){
						String contexto = contextos.get(k);
						if(contexto !=null && !aContextoV.contains(contexto)){
//						if(contexto !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de contexto: " + contexto + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 		 .append(getPropertyValueI18n("vocabularios.contexto"))
							 		 .append(contexto).append(SPLITTER);
							haCambiado = true;
							cambioContextos = true;
							cambioUsosEdu=true;
						}
						else{
							new_Contextos.add(contexto);
						}
					}
					if(cambioContextos)
						usoEducativo.setContextosAv(new_Contextos);
					
					//***********dificultad***********
					String dificultad = usoEducativo.getDificultadAv();
					if(dificultad !=null && !aDificultadV.contains(dificultad)){
//					if(dificultad !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de dificultad: " + dificultad + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
				 		 		 .append(getPropertyValueI18n("vocabularios.dificultad"))
				 		 		 .append(dificultad).append(SPLITTER);
						usoEducativo.setDificultadAv(null);
						haCambiado = true;
						cambioUsosEdu=true;
					}
					
					//***********Tiempo de aprendizaje***********				
					DuracionAgrega duracionAgrega = usoEducativo.getTiempoTipicoAprendizajeAv();
					if(duracionAgrega!=null){
						String duracion =duracionAgrega.getDuracion();
						if(duracion!=null){
							Pattern mask=Pattern.compile("^P([0-9]+Y){0,1}([0-9]+M){0,1}([0-9]+D){0,1}(T([0-9]+H){0,1}([0-9]+M){0,1}([0-9]+(.[0-9]+){0,1}S){0,1}){0,1}$"); //ejm ee-zz
							Matcher matcher = mask.matcher(duracion);
							boolean correcto = matcher.matches();
							if(!correcto){
								if (logger.isDebugEnabled()) logger.debug("Valor erroneo de duracion: " + duracion + ". Se borra valor.");
								resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 		 .append(getPropertyValueI18n("vocabularios.duracion"))
								 		 .append(duracion).append(SPLITTER);
								duracionAgrega.setDuracion(null);
								usoEducativo.setTiempoTipicoAprendizajeAv(duracionAgrega);
								haCambiado = true;
								cambioUsosEdu=true;
							}
						}
					}
					
					
					//***********idiomasDestinatarios***********
					ArrayList<String> idiomasDestinatarios = usoEducativo.getIdiomasDestinatarioAv();
					boolean cambioIdiomasDest = false;
					ArrayList new_IdiomasDest = new ArrayList();
					for(int k = 0; k < idiomasDestinatarios.size();k++){
						String idioma = idiomasDestinatarios.get(k);
						if(idioma !=null && !aIdiomaDestinatarioV.contains(idioma)){
//						if(idioma !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de idioma destinario: " + idioma + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
					 		 		 .append(getPropertyValueI18n("vocabularios.idiomaDestinatario"))
					 		 		 .append(idioma).append(SPLITTER);
							haCambiado = true;
							cambioIdiomasDest = true;
							cambioUsosEdu=true;
						}
						else{
							new_IdiomasDest.add(idioma);
						}
					}
					if(cambioIdiomasDest)
						usoEducativo.setIdiomasDestinatarioAv(new_IdiomasDest);
					//***********procesosCognitivos***********				
					ArrayList<String> procesosCognitivos = usoEducativo.getProcesosCognitivosAv();
					boolean cambioProcesosCog = false;
					ArrayList new_ProcesosCog = new ArrayList();
					for(int k = 0; k < procesosCognitivos.size();k++){
						String proceso = procesosCognitivos.get(k);
						if(proceso !=null && !aProcesoCognitivoV.contains(proceso)){
//						if(proceso !=null){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de proceso cognitivo: " + proceso + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 		 .append(getPropertyValueI18n("vocabularios.proceso"))
			 		 		 		 .append(proceso).append(SPLITTER);
							haCambiado = true;
							cambioProcesosCog = true;
							cambioUsosEdu=true;
						}
						else{
							new_ProcesosCog.add(proceso);
						}
					}
					if(cambioProcesosCog)
						usoEducativo.setProcesosCognitivosAv(new_ProcesosCog);

					
					if((usoEducativo.getTipoDeInteractividadAv()!=null && !usoEducativo.getTipoDeInteractividadAv().equals("")) ||
						(usoEducativo.getTiposDeRecursoAv()!=null && usoEducativo.getTiposDeRecursoAv().size()>0) ||
						(usoEducativo.getNivelDeInteractividadAv()!=null && !usoEducativo.getNivelDeInteractividadAv().equals("")) ||
						(usoEducativo.getDensidadSemanticaAv()!=null && !usoEducativo.getDensidadSemanticaAv().equals("")) ||
						(usoEducativo.getDestinatariosAv()!=null && usoEducativo.getDestinatarios().size()>0) ||
						(usoEducativo.getContextosAv()!=null && usoEducativo.getContextosAv().size()>0) ||
						(usoEducativo.getRangosEdadAv()!=null && usoEducativo.getRangosEdadAv().size()>0) ||
						(usoEducativo.getDificultadAv()!=null && !usoEducativo.getDificultadAv().equals("")) ||
						(usoEducativo.getTiempoTipicoAprendizajeAv()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDuracion()!=null && !usoEducativo.getTiempoTipicoAprendizajeAv().getDuracion().equals("")) ||
						(usoEducativo.getTiempoTipicoAprendizajeAv()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDescripciones()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDescripciones().size()>0) ||
						(usoEducativo.getDescripcionesAv()!=null && usoEducativo.getDescripcionesAv().size()>0) ||
						(usoEducativo.getIdiomasDestinatarioAv()!=null && usoEducativo.getIdiomasDestinatarioAv().size()>0) ||
						(usoEducativo.getProcesosCognitivosAv()!=null && usoEducativo.getProcesosCognitivosAv().size()>0)){
						new_usosEducativos.add(usoEducativo);
					}
					
				}
				if(cambioUsosEdu){
					EducationalAgrega[] aUsosEducativos= new_usosEducativos.toArray(new EducationalAgrega[new_usosEducativos.size()]);
					lomAgrega.setEducationalsAgrega(aUsosEducativos);
				}
				/**********************Derechos**********************/
				logger.debug("Comprobando campos de la categoría Derechos");
				RightsAgrega derechosAgrega = lomAgrega.getRightsAgrega();
				
				String coste = derechosAgrega.getCosteAv();
				if(coste !=null && !aCosteV.contains(coste)){
//				if(coste !=null){
					if (logger.isDebugEnabled()) logger.debug("Valor erroneo de coste: " + coste + ". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 .append(getPropertyValueI18n("vocabularios.coste"))
							 .append(coste).append(SPLITTER);
					derechosAgrega.setCosteAv(null);
					haCambiado = true;
				}
				
				String derechosAutor = derechosAgrega.getDerechosDeAutorAv();
				if(derechosAutor !=null && !aDerechosAutorV.contains(derechosAutor)){
//				if(derechosAutor !=null){
					logger.debug("Valor erroneo de derechos de autor: " + derechosAutor + ". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
					 		 .append(getPropertyValueI18n("vocabularios.derechosAutor"))
					 		 .append(derechosAutor).append(SPLITTER);
					derechosAgrega.setDerechosDeAutorAv(null);
					haCambiado = true;
				}
				
				AccesoAgrega acceso = derechosAgrega.getAccesoAv();
				String tipoAcceso = acceso.getTipoAcceso();
				if(tipoAcceso !=null && !aTipoAccesoV.contains(tipoAcceso)){
//				if(tipoAcceso !=null){
					if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de acceso: " + tipoAcceso + ". Se borra valor.");
					resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
			 		 		 .append(getPropertyValueI18n("vocabularios.tipoAcceso"))
			 		 		 .append(tipoAcceso).append(SPLITTER);
					acceso.setTipoAcceso(null);
					derechosAgrega.setAccesoAv(acceso);
					haCambiado = true;
				}
				
				/**********************Relación**********************/
				logger.debug("Comprobando campos de la categoría Relacion");
				ArrayList<RelationAgrega> relacionesAgrega = lomAgrega.getRelationsAgrega();
				ArrayList<RelationAgrega> new_relaciones= new ArrayList<RelationAgrega>();
				boolean cambioRelacion = false;
				for(int j = 0;j < relacionesAgrega.size(); j++){
					RelationAgrega relacionAgrega = relacionesAgrega.get(j);
					String tipo = relacionAgrega.getTipoAv();
					if(tipo !=null && !aTipoRelacionV.contains(tipo)){
//					if(tipo !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de tipo de relacion: " + tipo + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
								 .append(getPropertyValueI18n("vocabularios.tipoRelacion"))
								 .append(tipo).append(SPLITTER);
						relacionAgrega.setTipoAv(null);
						haCambiado = true;
						cambioRelacion=true;
					}
					if((relacionAgrega.getTipoAv()!=null && !relacionAgrega.getTipoAv().equals("")) ||
						(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getDescripciones()!=null && 
								relacionAgrega.getRecursoAv().getDescripciones().size()>0) ||
						(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getIdentificador()!=null && 
								relacionAgrega.getRecursoAv().getIdentificador().getCatalogo()!=null && 
								!relacionAgrega.getRecursoAv().getIdentificador().getCatalogo().equals("")) ||
						(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getIdentificador()!=null && 
								relacionAgrega.getRecursoAv().getIdentificador().getEntrada()!=null && 
								!relacionAgrega.getRecursoAv().getIdentificador().getEntrada().equals(""))){
						new_relaciones.add(relacionAgrega);
					}
				}
				if(cambioRelacion){
					RelationAgrega[] aRelaciones= new_relaciones.toArray(new RelationAgrega[new_relaciones.size()]);
					lomAgrega.setRelationsAgrega(aRelaciones);
				}
				/**********************Anotacion**********************/
				logger.debug("Comprobando campos de la categoría Anotacion");
				ArrayList<AnnotationAgrega> anotacionesAgrega = lomAgrega.getAnnotationsAgrega();
				ArrayList<AnnotationAgrega> new_anotaciones= new ArrayList<AnnotationAgrega>();
				boolean cambioAnotacion = false;
				for(int j = 0;j < anotacionesAgrega.size(); j++){
					AnnotationAgrega anotacionAgrega = anotacionesAgrega.get(j);
					FechaAgrega fecha = anotacionAgrega.getFechaAv();
					if(fecha!=null && fecha.getFecha()!=null){
						String sFecha = fecha.getFecha();
						boolean correcta = this.comprobarFecha(sFecha);

						if(!correcta){
							if (logger.isDebugEnabled()) logger.debug("Valor erroneo de fecha: " + sFecha + ". Se borra valor.");
							resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
							 		 .append(getPropertyValueI18n("vocabularios.fechaAnotacion"))
							 		 .append(sFecha).append(SPLITTER);
							fecha.setFecha(null);
							anotacionAgrega.setFechaAv(fecha);
							haCambiado = true;
							cambioAnotacion=true;
						}
						if(fecha.getFecha() != null || (fecha.getDescripciones()!=null && fecha.getDescripciones().size()>0)){
							new_anotaciones.add(anotacionAgrega);
						}
						
					}								
				}
				if(cambioAnotacion){
					AnnotationAgrega[] aAnotaciones= new_anotaciones.toArray(new AnnotationAgrega[new_anotaciones.size()]);
					lomAgrega.setAnnotationsAgrega(aAnotaciones);
				}
				/**********************Clasificacion**********************/
				logger.debug("Comprobando campos de la categoría Clasificacion");
				ArrayList<ClassificationAgrega> clasificacionesAgrega = lomAgrega.getClassificationsAgrega();
				ArrayList<ClassificationAgrega> new_clasificaciones= new ArrayList<ClassificationAgrega>();
				boolean cambioClasificacion = false;
				for(int j = 0; j < clasificacionesAgrega.size(); j++){
					ClassificationAgrega clasificacionAgrega = clasificacionesAgrega.get(j);
					String proposito = clasificacionAgrega.getPropositoAv();
					if(proposito !=null && !aPropositoV.contains(proposito)){
//					if(proposito !=null){
						if (logger.isDebugEnabled()) logger.debug("Valor erroneo de proposito: " + proposito + ". Se borra valor.");
						resultado.append(getPropertyValueI18n("vocabularios.texto.borrado"))
				 		 		 .append(getPropertyValueI18n("vocabularios.proposito"))
				 		 		 .append(proposito).append(SPLITTER);
						clasificacionAgrega.setPropostitoAv(null);
						haCambiado = true;
						cambioClasificacion=true;
					}
					if((clasificacionAgrega.getPropositoAv()!=null && !clasificacionAgrega.getPropositoAv().equals("")) ||
						(clasificacionAgrega.getRutaTaxonomicasAv()!=null && clasificacionAgrega.getRutaTaxonomicasAv().size() >0) ||
						(clasificacionAgrega.getPalabrasClaveAv()!=null && clasificacionAgrega.getPalabrasClaveAv().size()>0) ||
						(clasificacionAgrega.getDescripcionesAv()!=null && clasificacionAgrega.getDescripcionesAv().size()>0)){
						new_clasificaciones.add(clasificacionAgrega);
					}
				}
				if(cambioClasificacion){
					ClassificationAgrega[] aClasificaciones= new_clasificaciones.toArray(new ClassificationAgrega[new_clasificaciones.size()]);
					lomAgrega.setClassificationsAgrega(aClasificaciones);
				}
				
				
				//obtenemos la localización del lomes, por si estuviera referenciado por location
				Object elemento = manifestAgrega.obtenerElemento(idPadre);
				String location = null;
				if (elemento instanceof Manifest) {
					Manifest new_elemento = (Manifest) elemento;
					location = this.obtenLocation(new_elemento.getMetadata());				
				}else if (elemento instanceof Organization){
					Organization new_elemento = (Organization) elemento;
					location = this.obtenLocation(new_elemento.getMetadata());
				}else if (elemento instanceof Item){
					Item new_elemento = (Item) elemento;
					location = this.obtenLocation(new_elemento.getMetadata());
				}else if (elemento instanceof Resource){
					Resource new_elemento = (Resource) elemento;
					location = this.obtenLocation(new_elemento.getMetadata());
				}
				
				if (haCambiado)//si el lomes ha cambiado se inserta de nuevo el manifest o se sobreescribe 
							   //el fichero si estuviera referenciado mediante location
					manifestAgrega.setLom(idPadre, location, lomes);
			}

			return resultado.toString();
		}
		
		private String obtenLocation(Metadata metadata){
			String location = null;
			if (metadata != null && metadata.getGrp_any() != null
					&& metadata.getGrp_any().getAnyObject() != null) {
				
				Object[] array = metadata.getGrp_any().getAnyObject();
				for (int k = 0;  k < array.length; k++) {
					if (array[k] instanceof Location) {
						location = ((Location)array[k]).getContent();
					
					}
				}
			}
			return location;
		}
		
		private ArrayList obtenValoresVocabulario(VocabularioVO vocabulario){
			ArrayList resultado = new ArrayList();
			
			TerminoVO[] valores = vocabulario.getTerminos();
			for(int i = 0; i< valores.length;i++){
				resultado.add(valores[i].getNomTermino());
			}
			
			return resultado;
		}
		
		private boolean comprobarFecha(String fecha){
			boolean correcto = true;
			
			String expresion = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})(\\-(0[1-9]|1[0-2])(\\-(0[1-9]|[1-2][0-9]|3[0-1])(T([0-1][0-9]|2[0-3])(:[0-5][0-9](:[0-5][0-9](\\.[0-9]{1,}(Z|((\\+|\\-)([0-1][0-9]|2[0-3]):[0-5][0-9]))?)?)?)?)?)?)?$";
			
			Pattern mask=Pattern.compile(expresion);
			Matcher matcher = mask.matcher(fecha);
			correcto = matcher.matches();
			
			return correcto;
		}

		private SrvDRIService obtenerConexion(String url) throws MalformedURLException, ConexionException{
			SrvDRIService servicio =null;
			URL urlDri=null;
			try{
				SrvDRIServiceServiceLocator locator = new SrvDRIServiceServiceLocator();
				
				if(url.toLowerCase().startsWith(HTTP.toLowerCase())){
					urlDri=new URL(url+BARRA+DRI);
				}else{
					urlDri=new URL(HTTP+url+BARRA+DRI);
				}
				logger.debug("Generado la URL del nodo <"+urlDri+">");
				servicio= locator.getSrvDRIService(urlDri);
			}catch(MalformedURLException e){
				logger.error("La url está mal formada <" +urlDri+"> - ",e);
				throw new MalformedURLException ("La url está mal formada" +urlDri);
			}catch(ServiceException ex){
				logger.error("Error al llamar al servicio DRI con url <" +url+"> - ",ex);
				throw new ConexionException ("Error al generar el servicio DRI con url" +urlDri, ex);
			}
			return servicio;
			
		}

		@Override
		protected DescompriveYvalidaVO handleImportarOdeConId(
				DataHandler archivo, String titulo, String idOde)
				throws Exception {
			return importarOde(archivo, titulo, idOde);
		}
		

}