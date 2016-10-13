/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.instalador.negocio.ficherosconfiguracion;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import es.pode.instalador.negocio.buscarysustituir.Sed;
import es.pode.instalador.negocio.servicios.PropiedadVO;

public class AgregaPropertiesFile {

	
	/**
	 * Array con la lista de las variables que estaban contenidas en el fichero de agrega.properties y que se modificaban en la instalacion.
	 */
	public static String[] variables = new String[]{
		InstalacionVariables.CCAA,
		InstalacionVariables.MAILCONTACTO,
		InstalacionVariables.TELEFONOCONTACTO,
//		InstalacionVariables.IMGSERVERHOST,
		InstalacionVariables.GALERIAIMGPATH,
//		InstalacionVariables.IMGSERVERPORT,
		InstalacionVariables.NODO,
		InstalacionVariables.NODO_JBOSS,
		InstalacionVariables.LDAPEXTERNAL,
		InstalacionVariables.LDAPADMIN,
		InstalacionVariables.PREFIJO_NODO,
		InstalacionVariables.RUTALOGS,
		InstalacionVariables.PROXY,
		InstalacionVariables.PROXYHOST,
		InstalacionVariables.PROXYPASSWD,
		InstalacionVariables.PROXYPORT,
		InstalacionVariables.PROXYUSER,
		InstalacionVariables.PORT,
		InstalacionVariables.PORT_JBOSS,
		InstalacionVariables.IDNODO,
		InstalacionVariables.SERVERON,
		InstalacionVariables.SMTPAUTHENTICATION,
		InstalacionVariables.SMTPHOST,
		InstalacionVariables.SMTPPASSWD,
		InstalacionVariables.SMTPSENDER,
		InstalacionVariables.SMTPUSER,
		InstalacionVariables.SUBDOMAIN,
		InstalacionVariables.SUBDOMAIN_JBOSS,
		InstalacionVariables.URLCONSEJERIA};
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	public static String directorioDestinoDefault 		= "server"+File.separator+"default"+File.separator+"conf";
	public static String directorioDestinoAlternativo 	= "server"+File.separator+"alternativo"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode1 		= "server"+File.separator+"node1"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode2 		= "server"+File.separator+"node2"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode3 		= "server"+File.separator+"node3"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode4 		= "server"+File.separator+"node4"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode5 		= "server"+File.separator+"node5"+File.separator+"conf"+File.separator;
	public static String directorioDestinoNode6 		= "server"+File.separator+"node6"+File.separator+"conf"+File.separator;
	public static String ficheroDestino 				= "agrega.properties";
	
	public static String ficheroPlantilla = "/agrega.properties";
	
	/**
	 * Lista con las variables que estaban contenidas en el fichero de agrega.properties y que se modificaban en la instalacion.
	 */
	public static List<String> listaVariables = (List<String>) Arrays.asList(variables);
	
	/**
	 * Metodo que indica si la variable que se le pasa es de las contenidas anteriormente en el fichero de agrega.properties
	 * o no.
	 * @param variable Valor de la variable que se quiere averiguar si estaba reflejada en el fichero agrega.properties en la instalacion.
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
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero agrega.properties
	 * Supone que el par variable-valor no es nulo y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param typeJboss Tipo de jboss.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile, String typeJboss)throws Exception
	{
		String ficheroDestinoDisco = "";
		PropiedadVO[] propiedades = InstalacionVariables.eliminarPrefijoTipoJboss(prop, typeJboss);
		checkVariablesNecesarias(propiedades);
		
		if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_DEF)) { 
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoDefault);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoDefault + File.separator + ficheroDestino;
			
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_ALT)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoAlternativo);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoAlternativo + File.separator + ficheroDestino;
			
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE1)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode1);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode1 + File.separator + ficheroDestino;
		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE2)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode2);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode2 + File.separator + ficheroDestino;
		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE3)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode3);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode3 + File.separator + ficheroDestino;
		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE4)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode4);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode4 + File.separator + ficheroDestino;
		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE5)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode5);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode5 + File.separator + ficheroDestino;
		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE6)) {
			Sed.crearDirConPermisos(homeFile + File.separator + directorioDestinoNode6);
			ficheroDestinoDisco = homeFile + File.separator + directorioDestinoNode6 + File.separator + ficheroDestino;
		}	
		
		InputStream ipstrm = AgregaPropertiesFile.class.getResourceAsStream(ficheroPlantilla);
		Sed.sustituir(ipstrm, ficheroDestinoDisco,propiedades);
		return;
	}
	
	
	/**
	 * Metodo que chequea que las variables que se le pasan contienen todas las variables necesarias para rellenar con exito el fichero agrega.properties
	 * @param prop
	 */
	public static void checkVariablesNecesarias(PropiedadVO[] prop) throws Exception{
//		Me copio las variables que hacen falta en el fichero
		ArrayList<String> copiaVariables = new ArrayList<String>();
		for (int i = 0; i < variables.length; i++) 
			copiaVariables.add(""+ variables[i]); 
		
		for (int i = 0; i < InstalacionVariables.variables_comunes_con_default_value.length; i++) 
			copiaVariables.add(""+ InstalacionVariables.variables_comunes_con_default_value[i]); 
				
		for (int i = 0; i < prop.length; i++) 
			if (copiaVariables.contains(prop[i].getNombre()))
				copiaVariables.remove(prop[i].getNombre());
		
		if (copiaVariables.size() > 0) // si hemos consumido todas las variables del array de copia, es que estan todas en el array
		{	
			String faltan = "";
			String[] faltanArray = copiaVariables.toArray(new String[0]);
			for (int i = 0; i < faltanArray.length; i++) {
				faltan+= "["+faltanArray[i]+"]";
			}
			throw new Exception("Imposible modificar fichero agrega.properties. El numero de propiedades suministradas no es correcto. Faltan por rellenar ["+copiaVariables.size()+"] valores de variables de ["+variables.length+"] totales. Faltan: "+faltan);
		}
	}
}
