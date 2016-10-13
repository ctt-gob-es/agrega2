/**
 * 
 */
package es.pode.oaiore.negocio.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author dgonzalezd
 *
 */
public enum OAIOREProperties {
	//Lista de propiedades, copiadas y pegadas del fichero properties
	nivelCurricularExcluido,
	entradasPorXML,
	rutaInicial,
	rutaNivelAgregacion,
	rutaTesauro,
	rutaArbolCurricular,
	tipoTodos,
	tipoNivelAgregacion,
	tipoAreaCurricular,
	tipoTesauro,
	resultadosPorConsulta,
	creador,
	licencia,
	nombreFichero;
	
	private static final String oaiConfigFile = "/oaiOre.properties";
	private static Properties props;
	private static Logger logger = Logger.getLogger(OAIOREProperties.class);
	
	static
	{
//		Inicializamos el fichero properties
		try {
				InputStream fIsSpringProperties = OAIOREProperties.class.getResourceAsStream(oaiConfigFile);
				props = new java.util.Properties();
				props.load(fIsSpringProperties);
		} catch (IOException e) {
			logger.error("Excepcion inicializando clase OAIOREProperties."+ e);
		}
	}
	
//	public static String getPropertyValue(OAIOREProperties sKey) 
//	{
//		String sReturn=new String();
////		try {
////			if (props == null)
////			{
////				InputStream fIsSpringProperties = OAIOREProperties.class.getResourceAsStream(oaiConfigFile);
////				props = new java.util.Properties();
////				props.load(fIsSpringProperties);
////			}
//			sReturn = props.getProperty(sKey.name());
////		} catch (IOException e) {
////			logger.warn("Excepcion intentando obtener propiedad ["+sKey+"] del fichero de propiedades del i18n["+e.getMessage()+"]");
////		}		
//		// devolvemos la propiedad
//		return sReturn;
//	}
	
	public String toString() {
		return props.getProperty(this.name())!=null?props.getProperty(this.name()):"";
	}
}
