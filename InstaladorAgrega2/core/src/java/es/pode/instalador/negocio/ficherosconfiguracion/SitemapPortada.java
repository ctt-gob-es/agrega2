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

public class SitemapPortada {

	/**
	 * Array con la lista de las variables que estaban contenidas en los ficheros de sitemapPortada.xml y que se modificaban en la instalacion.
	 */
	public static String[] variables = new String[]{
		InstalacionVariables.NODO      ,
		InstalacionVariables.SUBDOMAIN };
	
	/**
	 * Lista con las variables que estaban contenidas en los ficheros de sitemapPortada.xml y que se modificaban en la instalacion.
	 */
	public static List<String> listaVariables = (List<String>) Arrays.asList(variables);
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String directorioDestino = "sitemaps" + File.separator + "estatico";
	public static String ficheroDestino = "sitemapPortada.xml";
	
	public static String ficheroPlantilla = "/sitemapPortada.xml";
	/**
	 * Metodo que indica si la variable que se le pasa es de las contenidas anteriormente en los ficheros de sitemapPortada.xml
	 * o no.
	 * @param variable Valor de la variable que se quiere averiguar si estaba reflejada en los ficheros sitemapPortada.xml en la instalacion.
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
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero sitemapPortada.xml
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile, String typeJboss)throws Exception
	{
		checkVariablesNecesarias(prop);
		PropiedadVO[] propiedades = InstalacionVariables.eliminarPrefijoTipoJboss(prop, typeJboss);
		Sed.crearDirConPermisos(homeFile + File.separator + directorioDestino);
		String ficheroDestinoDisco = homeFile + File.separator + directorioDestino + File.separator + ficheroDestino;
		InputStream ipstrm = SitemapPortada.class.getResourceAsStream(ficheroPlantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,propiedades);
		return;
	}

	/**
	 * Metodo que chequea que las variables que se le pasan contienen todas las variables necesarias para rellenar con exito el fichero sitemapPortada.xml
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
			throw new Exception("Imposible modificar fichero sitemapPortada.xml. El numero de propiedades suministradas no es correcto. Faltan por rellenar["+copiaVariables.size()+"] valores de variables de ["+variables.length+"]totales. Faltan: "+faltan);
		}
	}
}
