/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.soporte;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.publicacion.negocio.servicios.OdeVO;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.utiles.imagenes.UtilesImagenes;

public class TratamientoImagenes {

	private static Logger logger = Logger.getLogger(TratamientoImagenes.class);
	protected final static String FILE_SEPARATOR = "/";
	protected final static String FILE_ICON_SIZE = "_icon_63_100";
	
	//Constantes relacionadas con la creacion de imagenes.
	//Estas imagenes son escalas de determinado tamaño de la imagen del ODE.
	public final static int LITTLE_WIDTH = 100;
	public final static int LITTLE_HEIGHT = 100;
	public final static int MEDIUM_WIDTH = 200;
	public final static int MEDIUM_HEIGHT = 125;
	public final static int BIG_WIDTH = 800;
	public final static int BIG_HEIGHT = 600;
		
	 /**
     * obtiene la localización del primer recurso válido para luego obtener la imagen representativa del ode
     * @param manifestAgrega
     * @param sMec
     * @param sLocalizador
     * @param properties
     * @throws Exception
     */
	//TODO ESte método pasará a ser muy simple, dará la imagen incluida o nada
	public static String localPathGenerate(ManifestAgrega manifestAgrega, String sMec, String sLocalizador,
			AgregaPropertiesImpl properties) throws Exception {

		StringBuffer sbLocalPath = new StringBuffer();
		try {
//			String vuelta ="";
//			//primero consultamos el recurso de la primera organización-> primer item. Si se trata de un recurso valido, lo escogemos
//			if(manifestAgrega.getManifest().getOrganizations()!=null && manifestAgrega.getManifest().getOrganizations().getOrganizationCount()>0 &&
//					manifestAgrega.getManifest().getOrganizations().getOrganization(0)!=null && 
//					manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem()!=null &&
//					manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItemCount()>0 &&
//					manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem(0)!=null ){
//				String identiref=manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem(0).getIdentifierref();
//				if(logger.isDebugEnabled())
//				logger.debug("Estamos en el organization, con el identifierref:["+identiref+"]");
//				if(identiref!=null && !identiref.equals("")){
//					Resource recurso=manifestAgrega.obtenerResource(identiref);
//					if(recurso!=null && recurso.getHref()!=null && !recurso.getHref().equals("")){
//						vuelta =generacionHref(manifestAgrega,recurso.getHref(),properties,sLocalizador);
//						if(logger.isDebugEnabled())
//						logger.debug("Se mira el recurso del manifest:["+recurso.getHref()+"] Y la generacion devuelve:["+vuelta+"]");
//						if(vuelta!=null){
//							return vuelta;
//						}
//					}
//				}
//				
//			}if(vuelta==null||vuelta.equals("")){ // si no hemos tenido suerte con el recurso de la primera organizacion-> primer item, miramos en el cajon de los
//				// recursos y el primero que nos valga, lo escogemos.
//				Resources recursos = manifestAgrega.getManifest().getResources();
//				if(recursos!=null && recursos.getResourceCount()>0){
//					if(logger.isDebugEnabled())
//						logger.debug("Se miran los recursos directamente");
//					//Recorremos todos los recursos
//					for (int i = 0; i < recursos.getResourceCount(); i++) {//Si hay recursos y no hemos encontrado todavía ninguno válido
//						Resource recurso = recursos.getResource(i);
//						if(recurso!=null && recurso.getHref()!=null && !recurso.getHref().equals("")){
//							vuelta =generacionHref(manifestAgrega,recurso.getHref(),properties,sLocalizador);
//							if(logger.isDebugEnabled())
//								logger.debug("Se mira el recurso directamente:["+recurso.getHref()+"] Y la generacion devuelve:["+vuelta+"]");
//							if(vuelta!=null){
//								return vuelta;
//							}
//						}
//					}
//				}
//			}
			
			//Mirar si existe vistaPreviaAgrega.png en raíz de Ode
			File vistaPrevia = new File(sLocalizador+"/"+properties.getProperty(AgregaProperties.VISTA_PREVIA_AGREGA));
			logger.debug("Vamos a ver si existe "+vistaPrevia.getAbsolutePath());
			if(vistaPrevia.exists()) {
				logger.debug("Existe imagen de vista previa en ODE");
				sbLocalPath.append(properties.getProperty(AgregaProperties.SERVER_PATH));
				sbLocalPath.append(FILE_SEPARATOR);
				sbLocalPath.append(sLocalizador);
				sbLocalPath.append(FILE_SEPARATOR);
				sbLocalPath.append(properties.getProperty(AgregaProperties.VISTA_PREVIA_AGREGA));
				logger.debug("El local path que va a devolver el método localPathGenerate es [ "+sbLocalPath.toString());
				return sbLocalPath.toString();
			}
			
			// Si llego aqui, es que no hay forma de encontrar un recurso valido ni en la primera organizacion ni entre los recursos.
//			Imagen por defecto, puesto que no hay ningún recurso en el ODE válido o fotografiable
			logger.error("No existen recurso válidos para el mec["
					+ sMec + "] con localizador[" + sLocalizador + "]");
//			sbLocalPath.append(properties.getProperty(AgregaProperties.SERVER_PATH));
//			sbLocalPath.append(FILE_SEPARATOR);
//			sbLocalPath.append(properties.getProperty(AgregaProperties.PROPERTY_IMAGE_DEFECTO));

//			if (logger.isDebugEnabled())
//				logger.debug("La ruta del objeto en el servidor es [" + sbLocalPath + "], con imagen por defecto");

		} catch (Exception ex) {
			logger.error("Se ha producido un error en localPathGenerate", ex);
			throw ex;
		}
		logger.debug(" El local path que va a devolver el método localPathGenerate es [ "+sbLocalPath.toString());
		return sbLocalPath.toString();
	}
	
	/**
	 * 
	 * @param manifestAgrega
	 * @param href
	 * @param properties
	 * @param sLocalizador
	 * @return
	 * @throws URISyntaxException
	 */
//	public static String generacionHref(ManifestAgrega manifestAgrega,String href,AgregaPropertiesImpl properties,String sLocalizador) throws URISyntaxException{
//		String sufijosArray=properties.getProperty(AgregaProperties.PROPERTY_IMAGE_COMMON_EXTENSION).toUpperCase();//Para asegurarnos que la comparacion se haga en mayúsculas
//		sufijosArray=sufijosArray.replace(" ", "");//Para asegurarnos que los sufijos no tengan ningun espacio en blanco
//		String[] sufijos=sufijosArray.split(",");
//		//Las extensiones fotografiables, las que hay que fotografiar
//		String sufijosFotoArray=properties.getProperty(AgregaProperties.PROPERTY_IMAGE_COMMON_EXTENSION_FOTOGRAFIABLE).toUpperCase();//Para asegurarnos que la comparacion se haga en mayúsculas
//		sufijosArray=sufijosFotoArray.replace(" ", "");//Para asegurarnos que los sufijos no tengan ningun espacio en blanco
//		String[] sufijosFoto=sufijosFotoArray.split(",");
//		
//		StringBuffer sbLocalPath = new StringBuffer();
//		String hrefCon=href.replace(" ", "_");//Para que cuando tengo espacios vacíos no nos de errores par generar la URI, pero sólo lo utilizamos
//		//para validar si es válido ( es decir si es opaco, y si tiene esquema; par generar la imagen fotografiable le pasamos su href con espacios vacíos
//		if(recursoValido(hrefCon)){
//			if(sufijos!=null && sufijos.length>0 ){
//				URI urlAux=new URI(hrefCon);
//				if(urlAux.getScheme()!=null && !urlAux.getScheme().equals("")){//Si tiene esquema es de tipo http://, https:// o ftp://
////						Imagen por defecto, no podemos dejar que se abra un navegador por lo que pueda contener.
//					//Por ahora le ponemos la imagen por defecto, hasta tener el icono
//					if(logger.isDebugEnabled())
//						logger.debug("Es de tipo http, https, ftp, ftps");
//					sbLocalPath.append(properties.getProperty(AgregaProperties.SERVER_PATH));
//					sbLocalPath.append(FILE_SEPARATOR);
//					sbLocalPath.append(properties.getProperty(AgregaProperties.PROPERTY_IMAGE_DEFECTO));
//					if (logger.isDebugEnabled())
//						logger.debug("La ruta del objeto en el servidor es [" + sbLocalPath + "], siendo el href del recurso ["+ href+"]");
//					return sbLocalPath.toString();
//				}
//				if(sufijosFoto !=null && sufijosFoto.length>0){
//					ArrayList listaSufFoto=new ArrayList(Arrays.asList(sufijosFoto));
//					boolean contenerSufFoto=listaSufFoto.contains((href.substring(href.lastIndexOf('.') + 1)).toUpperCase().trim());
//					logger.info("Esta en los fotografiables?"+contenerSufFoto);
//					ArrayList listaSuf=new ArrayList(Arrays.asList(sufijos));
//					boolean contenerIcono=listaSuf.contains((href.substring(href.lastIndexOf('.') + 1)).toUpperCase().trim());
//					logger.info("Esta en los iconos?"+contenerIcono);
//					if(contenerSufFoto || contenerIcono){
//						if(logger.isDebugEnabled())
//							logger.debug("Es fotografiable con extension ["+(href.substring(href.lastIndexOf('.') + 1)).toUpperCase().trim()+"]");
//						if(contenerIcono){//Si tiene una extension a la que hay que meter un icono por defecto
//							if(logger.isDebugEnabled())
//								logger.debug("Lleva la imagen por defecto con extension ["+(href.substring(href.lastIndexOf('.') + 1)).toUpperCase().trim()+"]");
//							sbLocalPath.append(properties.getProperty(AgregaProperties.SERVER_PATH));
//							sbLocalPath.append(FILE_SEPARATOR);
//							sbLocalPath.append(properties.getProperty(AgregaProperties.PROPERTY_IMAGE_COMMON_LOCAL_PATH));
//							sbLocalPath.append(FILE_SEPARATOR);
//							sbLocalPath.append((href.substring(href.lastIndexOf('.') + 1)).toLowerCase());
//							sbLocalPath.append(FILE_ICON_SIZE);
//							sbLocalPath.append(properties.getProperty(AgregaProperties.PROPERTY_IMAGE_EXTENSION));
//							logger.info("LocalPath generado para las extensiones con iconos [" + sbLocalPath+ "] ");
//							return sbLocalPath.toString();
//						}
//						//Las extensiones a las que hay que sacar foto
//						if(logger.isDebugEnabled())
//							logger.debug("Es un recurso a la que hay que sacar la foto, extension ["+(href.substring(href.lastIndexOf('.') + 1)).toUpperCase().trim()+"]");
//						sbLocalPath.append(properties.getProperty(AgregaProperties.SERVER_PATH));
//						if (logger.isDebugEnabled())
//							logger.debug("El path es ["+properties.getProperty(AgregaProperties.SERVER_PATH)+"]");
//						sbLocalPath.append(FILE_SEPARATOR);
//						sbLocalPath.append(sLocalizador);
//						if (logger.isDebugEnabled())
//							logger.debug("El sLocalizador es ["+sLocalizador+"]");	
//						sbLocalPath.append(FILE_SEPARATOR);
//						sbLocalPath.append(href);
//						if (logger.isDebugEnabled())
//						logger.debug("La ruta del objeto en el servidor es [" + sbLocalPath + "], siendo el href del recurso ["+ href+"]");
//						return sbLocalPath.toString();
//					}
//				}
//			}
//		}
//		return null;
//	}
	
	
	//TODO Método donde se escala imágen dada o la por defecto
	public static void createImage4Odes(OdeVO[] odeArray) throws Exception {
		try {
			if(logger.isDebugEnabled())
			logger.debug("Begin:createImage4Odes, el array de odes que nos llega es de longitud "+ odeArray.length);
			// Chequeo que el Archivo principal y el identificador vengan
			// rellenos
			// Si no es así lanzo una excepción y ya está
			for(int i=0;i<odeArray.length;i++){
				OdeVO ode=odeArray[i];
				if (ode.getIdentificadorMEC() != null && !ode.getIdentificadorMEC().equals("") && ode.getMainFile() != null
						&& !ode.getMainFile().equals("") && ode.getServerOn() != null && !ode.getServerOn().equals("")) {
//					if (logger.isDebugEnabled())logger.debug("Antes de enviar el mensaje id[" + ode.getIdentificadorMEC() + "] MainFile ["+ ode.getMainFile() + "] serverOn [" + ode.getServerOn() + "]");
//					publicacion.sendMessage(ode);
					logger.debug("Origen es "+ode.getMainFile());
					logger.debug("Destino es "+pathImagen(ode.getIdentificadorMEC(), LITTLE_WIDTH, LITTLE_HEIGHT, "", false).toString());
					File destino =new File(pathImagen(ode.getIdentificadorMEC(), LITTLE_WIDTH, LITTLE_HEIGHT, "", false).toString());
					//hay que crear la carpeta...
					logger.debug("Creamos carpeta padre "+destino.getParentFile().getPath());
					destino.getParentFile().mkdirs();
					
//					destino.createNewFile();
					UtilesImagenes.escala(new File(ode.getMainFile()), destino, LITTLE_WIDTH, LITTLE_HEIGHT, "png");
					logger.debug("Destino es "+pathImagen(ode.getIdentificadorMEC(), MEDIUM_WIDTH, MEDIUM_HEIGHT, "", false).toString());
					destino=new File(pathImagen(ode.getIdentificadorMEC(), MEDIUM_WIDTH, MEDIUM_HEIGHT, "", false).toString());
//					destino.createNewFile();
					UtilesImagenes.escala(new File(ode.getMainFile()), destino, MEDIUM_WIDTH, MEDIUM_HEIGHT, "jpg");
					logger.debug("Destino es "+pathImagen(ode.getIdentificadorMEC(), BIG_WIDTH, BIG_HEIGHT, "", false).toString());
					destino=new File(pathImagen(ode.getIdentificadorMEC(), BIG_WIDTH, BIG_HEIGHT, "", false).toString());
//					destino.createNewFile();
					UtilesImagenes.escala(new File(ode.getMainFile()), destino, BIG_WIDTH, BIG_HEIGHT, "jpg");
//					if(logger.isDebugEnabled())logger.debug("El mensaje se ha enviado correctamente para el ode "+ode.getIdentificadorMEC());
				} //else
					//throw new RuntimeException("EL archivo principal no va relleno");
			}
			// Si todo es correcto envio el VO en un mensaje
			if(logger.isDebugEnabled())logger.debug("End:createImage4Odes");
		} catch (Exception ex) {
			logger.error("Se ha producido un error en createImage4Odes", ex);
		}
	}

	
	
	/**
	 * Comprueba si un recurso es válido o no
	 * @param href enlace del recurso
	 * @return boolean si es válido o no
	 * @throws URISyntaxException
	 */
	public static boolean recursoValido(String href) throws URISyntaxException{
		URI uri=new URI(href);	
		if(uri!=null && !uri.getPath().equals("")){
			if(!uri.isOpaque()){//Para quitar las URL-s de tipo mailto:java-net@java.sun.com
				if (logger.isDebugEnabled()) logger.debug("El recurso con href ["+href+"] es valido");
				return true;
			}
			if (logger.isDebugEnabled()) logger.debug("El recurso con href ["+href+"] NO es valido");
			return false;
		}
		return false;
	}
	
	/*
	 * Este método, incluido anteriormente en el imagePathGenerate, contiene la parte de construcción del path, y la separa de la parte 
	 * de generación de la imagen, ya que la parte de reindexacion no debe regenerar las imagenes.*/
	public static OdeVO pathGenerate (ManifestAgrega manifestAgrega, String sMec, String sLocalizador) {
		AgregaPropertiesImpl properties = (AgregaPropertiesImpl) AgregaPropertiesImpl.getInstance();
		String serverId = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
		String sLocalPath = "";
		try {
			sLocalPath = localPathGenerate(manifestAgrega, sMec, sLocalizador, properties);
			logger.info("Vamos a generar el path para el objeto cuya ruta en el servidor es [" + sLocalPath + "]");
		} catch (Exception ex) {
			logger.error("Se ha producido un error en la llamada a localPathGenerate", ex);
		}
		// Ahora debo saber en qué servidor estoy.
		if (logger.isDebugEnabled())
			logger.debug("El servidor es serverId[" + serverId + "]");

		if (logger.isDebugEnabled())
			logger.debug("Genero el objeto de intercambio Mec [" + sMec + "] localPath [" + sLocalPath+ "] serverId [" + serverId + "]");
		// genero el Objeto de intercambio
		OdeVO ode = new OdeVO(sMec, sLocalPath, serverId);
		
		//if (logger.isDebugEnabled()) logger.debug("La imagen a indexar es [" + pathImagen(sMec) + "]");
		return ode;
	}
	
	
	//TODO DANIEL: Este es el que da la ruta de imagen de ODE que acaba en BBDD
	public static StringBuffer pathImagen(String sMec, int ancho, int alto, String localizador, boolean isUrl){
		StringBuffer imagePathReturn = new StringBuffer();

		AgregaProperties agregaProperties = AgregaPropertiesImpl.getInstance();
		//Mirar si existe vistaPreviaAgrega.png en raíz de Ode
		File vistaPrevia=null;
		if(!localizador.equals("")) {
			vistaPrevia = new File(localizador+"/"+agregaProperties.getProperty(AgregaProperties.VISTA_PREVIA_AGREGA));
		}
		if(localizador.equals("")||vistaPrevia.exists()) {
			if(!isUrl) {
				imagePathReturn.append(agregaProperties.getProperty(AgregaProperties.PATH_GALERIA_IMG));
			} else {
				// genero la URL de la imagen
				imagePathReturn.append(agregaProperties.getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH));
				imagePathReturn.append(FILE_SEPARATOR);
			}
			if (!isUrl) {
				// propiedad server Id
				imagePathReturn.append(agregaProperties
						.getProperty(AgregaProperties.SERVER_ID));
				imagePathReturn.append(FILE_SEPARATOR);
			}
			//		Tenemos que añadir un codigo MD5 para impedir que en un mismo directorio haya mas de 32000 subdirectorios. El sistema de ficheros no lo 
	//		soporta. Lo hacemos por MEC, para valancear la carga entre los codigos que salgan.
			String md5 = EncriptacionUtiles.md5String(sMec).substring(0, 2);
			imagePathReturn.append(md5);
			imagePathReturn.append(FILE_SEPARATOR);
			imagePathReturn.append(sMec);
			imagePathReturn.append(FILE_SEPARATOR);
			imagePathReturn.append(sMec);
	//		imagePathReturn.append(agregaProperties.getProperty(AgregaProperties.PROPERTY_IMAGE_EXTENSION));
		
			if (ancho==BIG_WIDTH && alto==BIG_HEIGHT)
				imagePathReturn.append("_captured.jpg");
			else if (ancho==MEDIUM_WIDTH && alto==MEDIUM_HEIGHT)
				imagePathReturn.append("_medium.jpg");
			else if (ancho==LITTLE_WIDTH && alto==LITTLE_HEIGHT)
				imagePathReturn.append(".png");
			else { 
				logger.error("TratamientoImagener.java - pathImagen - El ancho y el alto de la imagen no es correcto para poder calcular su path.");
				return new StringBuffer("");
			}	
		} else {
			//Imagen por defecto
			imagePathReturn.append(FILE_SEPARATOR);
			if (ancho==BIG_WIDTH && alto==BIG_HEIGHT)
				imagePathReturn.append(getUrl(agregaProperties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO)));
			else if (ancho==MEDIUM_WIDTH && alto==MEDIUM_HEIGHT)
				imagePathReturn.append(getUrl(agregaProperties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO_MEDIA)));
			else if (ancho==LITTLE_WIDTH && alto==LITTLE_HEIGHT)
				imagePathReturn.append(getUrl(agregaProperties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO_PEQUE)));
		}
		if (logger.isDebugEnabled()) logger.debug("La imagen a indexar es [" + imagePathReturn + "]");
		return imagePathReturn;
	}
	
	private static String getUrl(String url) {
		return url.substring(url.indexOf("/"));
	}
}
