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

public class RptdesignFile {
	
	/**
	 * Array con la lista de las variables que estaban contenidas en los ficheros de *rptdesign y que se modificaban en la instalacion.
	 */
	public static String[] variables = new String[]{
		InstalacionVariables.PORT_JBOSS,
		InstalacionVariables.SUBDOMAIN };
	
	/**
	 * Lista con las variables que estaban contenidas en lso ficheros de *rptdesign y que se modificaban en la instalacion.
	 */
	public static List<String> listaVariables = (List<String>) Arrays.asList(variables);
	
	/**
	 * Fichero/s destino de las variables de configuracion
	 */
	//public static String ficheroDestino = "/informes/plantillasInformes/";
	public static String ficheroDestinoDefault 		= "server"+File.separator+"default"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoAlternativo 	= "server"+File.separator+"alternativo"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode1 		= "server"+File.separator+"node1"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode2 		= "server"+File.separator+"node2"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode3 		= "server"+File.separator+"node3"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode4 		= "server"+File.separator+"node4"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode5 		= "server"+File.separator+"node5"+File.separator+"informes/plantillasInformes/";
	public static String ficheroDestinoNode6 		= "server"+File.separator+"node6"+File.separator+"informes/plantillasInformes/";
	
	public static String[] ficherosPlantilla = new String[]{
		"/coberturaCurricular.rptdesign",
		"/coberturaCurricularFederada.rptdesign",
		"/estadoOdes.rptdesign",
		"/informeCarga.rptdesign",
		"/masDescargado.rptdesign",
		"/masMostrado.rptdesign",
		"/masPrevisualizado.rptdesign",
		"/masValorado.rptdesign",
		"/nivelAgregacion.rptdesign",
		"/nivelAgregacionFederada.rptdesign",
		"/odesCargados.rptdesign",
		"/odesIdiomaFederada.rptdesign",
		"/odesLicencias.rptdesign",
		"/odesPublicadosFederada.rptdesign",
		"/odesUsuario.rptdesign",
		"/operacionesRealizadas.rptdesign",
		"/procesosPlanificados.rptdesign",
		"/repositorio.rptdesign",
		"/tamanio.rptdesign",
		"/terminosBusqueda.rptdesign",
		"/usuarios.rptdesign"};
	/**
	 * Metodo que indica si la variable que se le pasa es de las contenidas anteriormente en los ficheros de *rptdesign
	 * o no.
	 * @param variable Valor de la variable que se quiere averiguar si estaba reflejada en los ficheros *rptdesign en la instalacion.
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
	 * Este metodo modifica el valor de las variables que le pasan en la plantilla del fichero *.rptdesign
	 * Supone que el par variable-valor no es nulo ni vacio y que cubren todas las variables que se encuentran en el fichero.
	 * @param prop Propiedad con el nombre de la variable y el valor.
	 * @param homeFile Directorio raiz donde se encuentra el fichero destino de la sustitucion.
	 */
	public static void modificaVariables(PropiedadVO[] prop, String homeFile, String typeJboss)throws Exception
	{
		/*
		checkVariablesNecesarias(prop);
		PropiedadVO[] propiedades = InstalacionVariables.eliminarPrefijoTipoJboss(prop, typeJboss);
		Sed.crearDirConPermisos(homeFile + ficheroDestino);
//		Por cada fichero, me voy a haer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			String ficheroDestinoDisco = homeFile + ficheroDestino + ficherosPlantilla[i];
			InputStream ipstrm = RptdesignFile.class.getResourceAsStream(ficherosPlantilla[i]);
			Sed.sustituir(ipstrm, ficheroDestinoDisco, propiedades);
			ipstrm.close();
		}
		*/

		String ficheroDestinoDisco = "";
		PropiedadVO[] propiedades = InstalacionVariables.eliminarPrefijoTipoJboss(prop, typeJboss);
		checkVariablesNecesarias(propiedades);
		
		if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_DEF)) { 
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoDefault);			
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_ALT)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoAlternativo);			
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE1)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode1);		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE2)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode2);		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE3)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode3);		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE4)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode4);		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE5)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode5);		
		} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE6)) {
			Sed.crearDirConPermisos(homeFile + File.separator + ficheroDestinoNode6);
		}	

		//Por cada fichero, me voy a hacer su sustitucion.
		for (int i = 0; i < ficherosPlantilla.length; i++) {
			if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_DEF)) { 
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoDefault + File.separator + ficherosPlantilla[i];				
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_ALT)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoAlternativo + File.separator + ficherosPlantilla[i];				
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE1)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode1 + File.separator + ficherosPlantilla[i];			
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE2)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode2 + File.separator + ficherosPlantilla[i];			
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE3)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode3 + File.separator + ficherosPlantilla[i];			
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE4)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode4 + File.separator + ficherosPlantilla[i];			
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE5)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode5 + File.separator + ficherosPlantilla[i];			
			} else if (typeJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE6)) {
				ficheroDestinoDisco = homeFile + File.separator + ficheroDestinoNode6 + File.separator + ficherosPlantilla[i];
			}	
			InputStream ipstrm = RptdesignFile.class.getResourceAsStream(ficherosPlantilla[i]);
			Sed.sustituir(ipstrm, ficheroDestinoDisco, propiedades);
			ipstrm.close();
		}
	}

	/**
	 * Metodo que chequea que las variables que se le pasan contienen todas las variables necesarias para rellenar con exito el fichero *.rptdesign
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
			throw new Exception("Imposible modificar fichero *.rptdesign. El numero de propiedades suministradas no es correcto. Faltan por rellenar["+copiaVariables.size()+"] valores de variables de ["+variables.length+"]totales. Faltan: "+faltan);
		}
	}
}
