/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.soporte;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.pode.publicacion.negocio.servicios.SrvPublicacionServiceImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;

public class I18nModuloPublicacion {

	private static Logger logger = Logger.getLogger(I18nModuloPublicacion.class);
	private static HashMap<String, ResourceBundle> propsI18n = null;
	
	/**Este metodo recoge los properties internacionalizados
	 * 
	 * @param sKey
	 * 			La clave de la propiedad
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public static String getPropertyValueI18n(String sKey) {
		String idiomaUser = null;
		String retorno = null;
		try {
			idiomaUser = LdapUserDetailsUtils.getIdioma();
			retorno = getPropertyValueI18n(sKey, idiomaUser);
		} catch (Exception e) {
			logger.warn("Excepcion intentando obtener propiedad [" + sKey + "] del fichero de propiedades i18n["+ idiomaUser + "] del publicador[" + e.getCause() + "]", e);
		}
		return retorno;
	}


	/**Este metodo recoge los properties internacionalizados, en el idioma que le indicamos
	 * 
	 * @param sKey
	 * 			La clave de la propiedad
	 * @param idioma
	 * 			Idioma en el que queremos internacionalizar
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public static String getPropertyValueI18n(String sKey, String idioma) {
		String idiomaUser = null;
		ResourceBundle fichero = null;
		String retorno = null;
		try {
			idiomaUser = idioma;
			// No tengo mapa con los bundles de los idiomas o no tengo cargado el del idioma, lo cargo e inicializo
			if (propsI18n == null || (fichero = propsI18n.get(idiomaUser)) == null) {
				if (propsI18n == null) // inicializo la variable
					propsI18n = new HashMap();
				if ((fichero =propsI18n.get(idiomaUser)) == null) 
					// no hay fichero de bundle para este idioma, lo cargo
					addBundleFile(idiomaUser);
			}
			if ((fichero = propsI18n.get(idiomaUser)) == null)
				throw new Exception("Error intentando acceder al fichero de propiedades["
						+ SrvPublicacionServiceImpl.FILE_NAME_I18N + "] i18n del modulo publicador con idioma[" + idiomaUser+ "]. El fichero no existe.");
			retorno = fichero.getString(sKey);
			if (logger.isDebugEnabled()) logger.debug("propiedad internacionalizada obtenida[" + sKey + "]->[" + retorno + "]");		
		} catch (Exception e) {
			logger.warn("Excepcion intentando obtener propiedad [" + sKey + "] del fichero de propiedades i18n[" + idiomaUser + "] del modulo publicador[" + e.getCause() + "]", e);
		}
		// devolvemos la propiedad
		return retorno;
	}
	
	/**
	 * Añade un bundle para ese idioma a la coleccion de bundles y lo devuelve
	 */
	private static ResourceBundle addBundleFile(String idioma) {
		// Chequeamos que exista el fichero de bundle para el idioma dado.
		// Si no existe el fichero de properties para el idioma, no lo añadimos
		ResourceBundle fichero = null;
		try {
			fichero = ResourceBundle.getBundle(SrvPublicacionServiceImpl.FILE_NAME_I18N, new Locale(idioma));
		} catch (RuntimeException e) {
			// No existe un resource bundle para un idioma dado
			logger.warn("Excepcion intentando buscar el fichero de bundle para el idioma[" + idioma + "][" + e.getCause() + "]");
			return null;
		}
		if (fichero == null)
			return fichero;
		propsI18n.put(idioma, fichero);
		return fichero;
	}
}
