/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.instalador.negocio.ficherosconfiguracion;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.pode.instalador.negocio.buscarysustituir.Sed;
import es.pode.instalador.negocio.servicios.PropiedadVO;

public class OpmlFile {

	/**
	 * Array con la lista de las variables que estaban contenidas en los ficheros de *opml y que se modificaban en la instalacion.
	 */
	public static String[] variables = new String[]{
		InstalacionVariables.NODO      ,
		InstalacionVariables.SUBDOMAIN };
	
	/**
	 * Lista con las variables que estaban contenidas en los ficheros de *opml y que se modificaban en la instalacion.
	 */
	public static List<String> listaVariables = (List<String>) Arrays.asList(variables);
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String ficheroDestino = "/rss/";
	
	public static String[] ficherosPlantilla = new String[]{
		"/agrega_atom_ca.opml",
		"/agrega_atom_en.opml",
		"/agrega_atom_es.opml",
		"/agrega_atom_eu.opml",
		"/agrega_atom_gl.opml",
		"/agrega_atom_va.opml",
		"/agrega_rss_ca.opml",
		"/agrega_rss_en.opml",
		"/agrega_rss_es.opml",
		"/agrega_rss_eu.opml",
		"/agrega_rss_gl.opml",
		"/agrega_rss_va.opml"};
	/**
	 * Metodo que indica si la variable que se le pasa es de las contenidas anteriormente en los ficheros de *opml
	 * o no.
	 * @param variable Valor de la variable que se quiere averiguar si estaba reflejada en los ficheros *opml en la instalacion.
	 * Si el valor es vacio o nulo, devuelve falso.
	 * @return Devuelve cierto o falso en funcion de que la variable estuviera o no.
	 */
	public static boolean existeVariable(String variable)
	{
		if (variable == null || variable.equals(""))
			return false;
		return listaVariables.contains(variable);
	}
	
	/**
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero *.opml
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile, String typeJboss)throws Exception
	{
		checkVariablesNecesarias(prop);
		PropiedadVO[] propiedades = InstalacionVariables.eliminarPrefijoTipoJboss(prop, typeJboss);
		Sed.crearDirConPermisos(homeFile + ficheroDestino);
//		Por cada fichero, me voy a haer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			String ficheroDestinoDisco = homeFile + ficheroDestino + ficherosPlantilla[i];
			InputStream ipstrm = OpmlFile.class.getResourceAsStream(ficherosPlantilla[i]);
			Sed.sustituir(ipstrm, ficheroDestinoDisco, propiedades);
		}
		return;
	}

	/**
	 * Metodo que chequea que las variables que se le pasan contienen todas las variables necesarias para rellenar con exito el fichero *.opml
	 * @param prop
	 */
	private static void checkVariablesNecesarias(PropiedadVO[] prop) throws Exception{
//		Me copio las variables que hacen falta en el fichero
		ArrayList<String> copiaVariables = new ArrayList<String>();
		for (int i = 0; i < variables.length; i++) {
			copiaVariables.add(""+ variables[i]); 
		}
		
		for (int i = 0; i < prop.length; i++) {
			if (copiaVariables.contains(prop[i].getNombre()))
				copiaVariables.remove(prop[i].getNombre());
		}
		if (copiaVariables.size() > 0) // si hemos consumido todas las variables del array de copia, es que estan todas en el array
		{	
			String faltan = "";
			String[] faltanArray = copiaVariables.toArray(new String[0]);
			for (int i = 0; i < faltanArray.length; i++) {
				faltan+= "["+faltanArray[i]+"]";
			}
			throw new Exception("Imposible modificar los ficheros *opml. El numero de propiedades suministradas no es correcto. Faltan por rellenar["+copiaVariables.size()+"] valores de variables de ["+variables.length+"]totales. Faltan: "+faltan);
		}
	}
}
