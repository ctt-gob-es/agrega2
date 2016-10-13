/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
