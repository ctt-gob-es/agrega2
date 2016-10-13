package es.pode.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



public class Propiedades {

	public static final String LOC_URL			= "localizador.db.conexion.url";
	public static final String LOC_USERNAME		= "localizador.db.conexion.usuario";
	public static final String LOC_PWD			= "localizador.db.conexion.clave";
	public static final String LOC_BD_DRIVER	= "localizador.db.conexion.driver";
	public static final String LOC_BD_DIALECT	= "localizador.db.conexion.dialecto";
	public static final String ODE_URL			= "odePublicado.db.conexion.url";
	public static final String ODE_USERNAME		= "odePublicado.db.conexion.usuario";
	public static final String ODE_PWD			= "odePublicado.db.conexion.clave";
	public static final String ODE_BD_DRIVER	= "odePublicado.db.conexion.driver";
	public static final String ODE_BD_DIALECT	= "odePublicado.db.conexion.dialecto";
	
	public static final String BD_DRIVER_ORACLE	= "Oracle";
	public static final String BD_DRIVER_MYSQL	= "MySQL";
	public static final String BD_DIALECT_ORACLE= "Oracle";
	public static final String BD_DIALECT_MYSQL	= "MySQL";
	
	public static final String PATH_BASE_ODES = "path.base.odes";

	private static String FILE_NAME_PROPERTIES = "";
	private static String FILE_NAME_PROPERTIES_DEFAULT = "/build/arreglatorMD5.properties";
	
	private static Properties props = null;
	private static Logger logger = Logger.getLogger(Propiedades.class);
	
	public static String getPropertyValue(String sKey) {
		String sReturn = new String();
		try {
			if (props == null) {
				InputStream fIsSpringProperties = null;
				if (FILE_NAME_PROPERTIES != null && !FILE_NAME_PROPERTIES.equals(""))
				{
					logger.debug("Estamos instanciando fichero de propiedades con fichero ["+FILE_NAME_PROPERTIES+"]");
					fIsSpringProperties = new FileInputStream(FILE_NAME_PROPERTIES);
				}
				else // probamos con el fichero por defecto
				{
					logger.debug("Estamos instanciando fichero de propiedades con fichero ["+FILE_NAME_PROPERTIES_DEFAULT+"]");
					fIsSpringProperties = Propiedades.class.getResourceAsStream(FILE_NAME_PROPERTIES_DEFAULT); // lo cargamos del classpath
				}
				props = new java.util.Properties();
				props.load(fIsSpringProperties);
				logger.debug("Se ha cargado correctamente propiedades con fichero ["+FILE_NAME_PROPERTIES+"]");
			}
			sReturn = props.getProperty(sKey);
			logger.info("Se devuelve propiedad["+sKey+"]=["+sReturn+"]");
		} catch (IOException e) {
			logger.warn("Excepcion intentando obtener propiedad [" + sKey+ "] del fichero de propiedades["+FILE_NAME_PROPERTIES+"]"+ e.getCause());
		}
		// devolvemos la propiedad
		return sReturn;
	}
	
	public static void setPropertyFile(String ficheroPropiedades)
	{
		FILE_NAME_PROPERTIES = ficheroPropiedades;
	}
}
