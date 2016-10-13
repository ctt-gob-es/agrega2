package es.pode.soporte.offline.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;

public class ConfiguracionDao {

	private final String HEADER = "configuaracionDao properties";
	
	public static final String USER = "login";

	public static final String NOMBRE = "nombre";

	public static final String APELLIDOS = "apellidos";

	public static final String EMAIL = "email";

	public static final String EMPAQUETADOR = "empaquetador";
	
	public static final String CATALOGADOR = "catalogador";

	public static final String IDIOMA = "idioma";

	public static final String VISUALIZADOR = "visualizador";
	
	public static final String FILENAME = "configuracion.properties";

	private long fechaModificacion = 0;
	
	// los private
	private static java.util.Properties props = null;

	private Logger logger = Logger.getLogger(ConfiguracionDao.class);

	private static ConfiguracionDao dao;

	private ConfiguracionDao() {
		
		init();
	}

	private void init() {
		// TODO Mejorar el control de fichero modificado.
		props=null;
		java.io.File file = new java.io.File(FILENAME);
		if (!file.exists()) {
			try {
				if(file.getParentFile()!=null)
						file.getParentFile().mkdirs();			
				file.createNewFile();
				fechaModificacion = file.lastModified();
			} catch (Exception e) {
				logger.error("No se ha podido crear el fichero " + FILENAME);
				if (logger.isDebugEnabled())
					logger.debug(e);
			}
		}
		if (props == null) {
			if(logger.isDebugEnabled()) logger.debug("Recargando el fichero de propiedades");
			props = new java.util.Properties();
			if (file.exists()) {
				try {
					FileInputStream fis = new java.io.FileInputStream(file); 
					props.load(fis);
					fis.close();
				} catch (Exception e) {
					logger.error("No se ha podido cargar el fichero " + FILENAME,e);
					
					if (logger.isDebugEnabled())
						logger.debug(e);
				}
			} else {
				logger.warn("No se ha creado el fichero <" + FILENAME + ">");
			}
		}
		logger.info("Configuracion Dao cargada correctamente");
	}
	
	public static synchronized ConfiguracionDao instance() {
		if (dao == null) {
			dao = new ConfiguracionDao();
		}
		return dao;
	}

	// obtiene una propiedad
	public String getProperty(String key) {
		String result = null;
		
		// TODO Mejorar la comprobacion de fichero modificado. De momento cojo la opcion lenta.
		if(logger.isDebugEnabled()) logger.debug("getProperty("+key+")");
		init();
		if (props != null) {
			result = props.getProperty(key);
		}
		return result;
	}
	
	// guarda una propiedad	
	public void setProperty(String key, String value) throws Exception {
		// TODO mejorar la comprobacion de fichero modificado.
		if(logger.isDebugEnabled()) logger.debug("setProperty<"+key+"><"+value+">");
		init();
		java.io.File fichero = new java.io.File(FILENAME);
//		if(fechaModificacion < (fichero).lastModified()) {
//			// Hago merge de las escritas en el fichero con las actuales
//			java.util.Properties tmp = new java.util.Properties();
//			FileInputStream ios = new FileInputStream(fichero);
//			tmp.load(ios);
//			tmp.putAll(props);
//			props = tmp;
//			ios.close();
//			fechaModificacion = fichero.lastModified();
//		}
		if (props != null){
			props.setProperty(key, value);
			save();
		}
		else
			logger.info("No se puede hacer setProperty");
			throw new Exception("No se puede hacer set property");
	
	}
	
	// elimina una propiedad
	public void removeProperty(String key) throws Exception {
	if (props != null)
		props.remove(key);
	else
		throw new Exception("No se puede eliminar la key " + key);
	}

	// almacena el props
	public void save() throws Exception {
		// TODO el metodo save esta deprecated, utilizo store
		try {
			File fichero = new File(FILENAME);
			if(logger.isDebugEnabled()) logger.debug("Ruta de fichero de configuracion = <" + fichero.getCanonicalPath()+">");
			if(fichero.exists()) logger.debug("Borrando fichero <" + fichero.delete()">");
			logger.debug("Creando fichero <" + fichero.createNewFile()+">");
			FileOutputStream fos = new FileOutputStream(fichero);
			if(logger.isDebugEnabled()) logger.debug("Guardando propiedades en <" + FILENAME+">");
			if (props != null) {
				props.store(fos, HEADER);
				fos.flush();
				fos.close();
			} 
			else {
				fos.close();
				logger.info("No se puede hacer setProperty");
				throw new Exception("No se puede hacer set property");				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw e;
		}		
	}
	

//	public String getProperty(String sKey) {
//
//		String resultado = null;
//		try {
//
//			InputStream fIsConfigProperties = this.getClass().getResourceAsStream(this.FILENAME);
//			if (this.pConfigProperties == null) {
//				pConfigProperties = new java.util.Properties();
//				pConfigProperties.load(fIsConfigProperties);
//
//			}
//			fIsConfigProperties.close();
//			resultado = pConfigProperties.getProperty(sKey);
//
//		} catch (Exception e) {
//			logger.error("no se ha podido recuperar la propiedad: " + sKey + ";", e);
//		}
//		// devolvemos la propiedad si todo ha ido bien
//		return resultado;
//
//	}
	
//	public boolean setProperty(String key, String value) {
//	try {
//
//		InputStream fIsConfigProperties = this.getClass().getResourceAsStream(this.FILENAME);
//		if (this.pConfigProperties == null) {
//			pConfigProperties = new java.util.Properties();
//			pConfigProperties.load(fIsConfigProperties);
//
//		}
//		fIsConfigProperties.close();
//		pConfigProperties.setProperty(key, value);
//
//	} catch (Exception e) {
//		logger.error("no se ha podido escrbir el valor: " + value + " en la propiedad: " + key + ";", e);
//		return false;
//	}
//	return true;
//
//}
}