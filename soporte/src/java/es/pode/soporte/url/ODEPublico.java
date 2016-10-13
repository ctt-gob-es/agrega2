/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.url;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class ODEPublico {
	
	protected final static String URL_SEPARATOR = "/";
	protected final static String FICHA_URL_PREFIX = "ODE2";
	protected final static String PREVISUALIZADOR_URL_PREFIX = "visualizar";
	
	/**
	 * Construye la url de la ficha de un ODE público.
	 * @param idODE Identificador del ODE público.
	 * @param idioma Código del idioma en el que se quiere mostrar la previsualización (es, en, ca ..).
	 * @return URL que permite acceder a la ficha del ODE publicado.
	 */
	public static String urlFichaODEPublicado(String idODE, String idioma)
	{
		return urlFichaODEPublicado(idODE, idioma , null);
	}

	/**
	 * Construye la url de la ficha de un ODE público.
	 * Si el parámetro nodo es vacío, concatena la url del nodo local, 
	 * si el parámetro nodo tiene valor, lo utiliza para formar la url de la ficha
	 * @param idODE Identificador del ODE público.
	 * @param idioma Código del idioma en el que se quiere mostrar la previsualización (es, en, ca ..).
	 * @param nodo url de un nodo federado.
	 * @return URL que permite acceder a la ficha del ODE publicado.
	 */
	public static String urlFichaODEPublicado(String idODE, String idioma , String nodo)
	{
		if(nodo==null || nodo.equals(""))
			nodo=AgregaPropertiesImpl.getInstance().getUrlNodo();
		
		StringBuffer sb = new StringBuffer();
		sb.append(nodo);
		sb.append(URL_SEPARATOR);
		sb.append(FICHA_URL_PREFIX);
		sb.append(URL_SEPARATOR);
		sb.append(idioma);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		return sb.toString();
	}
	
	/**
	 * Construye la url que permite previsualizar el ODE público autónomo.
	 * @param idODE Identificador del ODE publicado autonomamente.
	 * @param idioma Código del idioma en el que se quiere mostrar la previsualización (es, en, ca ..).
	 * @return URL que permite la previsualización del ODE publicado autónomo.
	 */
	public static String urlPrevisualizaODEPublicadoAutonomo(String idODE, String idioma)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(URL_SEPARATOR);
		sb.append(PREVISUALIZADOR_URL_PREFIX);
		sb.append(URL_SEPARATOR);
		sb.append(idioma);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(URL_SEPARATOR);
		sb.append("true"); // secuencia
		return sb.toString();
	}
	
	/**
	 * Construye la url que permite previsualizar el ODE público.
	 * @param idODE Identificador del ODE publicado.
	 * @param idioma Código del idioma en el que se quiere mostrar la previsualización (es, en, ca ..).
	 * @return URL que permite la previsualización del ODE publicado.
	 */
	public static String urlPrevisualizaODEPublicado(String idODE, String idioma)
	{
		return urlPrevisualizaODEPublicado(idODE, idioma, null);
	}
	
	
	/**
	 * Construye la url que permite previsualizar el ODE público.
	 * Si el parámetro nodo es vacío, concatena la url del nodo local, 
	 * si el parámetro nodo tiene valor, lo utiliza para formar la url de la ficha
	 * @param idODE Identificador del ODE publicado.
	 * @param idioma Código del idioma en el que se quiere mostrar la previsualización (es, en, ca ..).
	 * @param nodo url de un nodo federado.
	 * @return URL que permite la previsualización del ODE publicado.
	 */
	public static String urlPrevisualizaODEPublicado(String idODE, String idioma, String nodo)
	{
		if(nodo==null || nodo.equals(""))
			nodo=AgregaPropertiesImpl.getInstance().getUrlNodo();
		
		StringBuffer sb = new StringBuffer();
		sb.append(nodo);
		sb.append(URL_SEPARATOR);
		sb.append(PREVISUALIZADOR_URL_PREFIX);
		sb.append(URL_SEPARATOR);
		sb.append(idioma);
		sb.append(URL_SEPARATOR);
		sb.append(idODE);
		sb.append(URL_SEPARATOR);
		sb.append("true");//secuencia
		return sb.toString();
	}
}
