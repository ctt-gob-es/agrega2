package es.pode.migracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.pode.negocio.SRVLocalizador;
import es.pode.negocio.SRVOdePublicado;
import es.pode.util.CalculoMD5;
import es.pode.util.Propiedades;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	private static final SRVOdePublicado servicioOdePub = new SRVOdePublicado();
	private static final SRVLocalizador srvLocalizador = new SRVLocalizador();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		Recogemos los parametros de entrada para la configuracion
		String ficheroConfiguracion = System.getProperty("configuracion");
		Propiedades.setPropertyFile(ficheroConfiguracion);

//		Sacamos la lista de los mecs de los ODES que tenemos que arreglar (los que no tienen MD5, que se supone que son todos, pero por si alguno se cuela, solo los
//		que no tienen)
		List<String> listaSinMD5 = servicioOdePub.listarTodosSinMD5();
		
//		Obtenemos la lista de los path que no tienen md5 en formato (idODE/path).
		Hashtable<String, String> listaODEsPAth = srvLocalizador.obtenerPathODES(listaSinMD5);
		
//		Tenemos que recorrer la lista de (idODE/path) y calcular los MD5 para cada ODE sin md5
		Hashtable<String, String> listaODEsMD5 = new Hashtable<String, String>();
		/*OJO: Es de suponer que la lista de los ODEs que no tienen md5 y la lista de los ODES publicados mide lo mismo*/
		String listaIds[] = listaODEsPAth.keySet().toArray(new String[0]); // sacamos las claves de la lista de (idODE/path) => ids
		String pathODE= "";
		String md5 = "";
		String pathBaseODEs = Propiedades.getPropertyValue(Propiedades.PATH_BASE_ODES);
		Hashtable<String, Long> tiempos = new Hashtable<String, Long>();
		long antes = 0;
		long despues = 0;
		long total = 0;
		for (int i = 0; i < listaIds.length; i++) {
//			Sacamos el path y lo concatenamos a la url basica para tener el path completo.
			pathODE = listaODEsPAth.get(listaIds[i]);
//			Calculamos el md5 para el ODE
			antes = System.currentTimeMillis();
			md5 = CalculoMD5.calculaMD5DePath(pathBaseODEs+pathODE);
			despues = System.currentTimeMillis();
			logger.debug("Calculado md5 para id["+listaIds[i]+"],path["+pathODE+"]=>md5["+md5+"]");
//			Metemos el id del ODE y su md5 en una tabla.
			listaODEsMD5.put(listaIds[i], md5);
			tiempos.put(listaIds[i], despues - antes);
			total+=(despues-antes);
		}
//		Llamamos al servicio de gestion de ODES publicados para que haga el update del md5 en los ODEs adecuados.
		int totalesModificados = servicioOdePub.modificaODEsMD5(listaODEsMD5);
		logger.info("Modificados finales (actualizados en BD)["+totalesModificados+"]");
		pintarODESMD5(listaODEsMD5,tiempos);
//		pruebaSinCalculoMD5();
		
//		pruebaConCaclculoMD5();
	}
	
	private static void pruebaSinCalculoMD5()
	{
		List<String> listaDeMD5 = Arrays.asList("454af3d9cd6a992a6963657831551424,e312dea52eff6ff6e4f73d5b20073300,b51694fff35e32343c38820f870fbaf,85052e14d883eb9d550e0ed5d7fc86d2,51095f11dad8eb87d263cf1d76c90950,b51694fff35e32343c38820f870fbaf,9f9c6c3e42b097f9d14557dd633758ea,153f0274a3e1881a5c77fb27a6ea334e,7c674cbd64d662ae214c0137cc39578e,30d6aee8fc4ab3f82318e8a83d354547,e312dea52eff6ff6e4f73d5b20073300,8a95d0fe8995567573e1caa0d6e6cb95,8bfa8b6825998f3f7ccb1a661f06fd87".split(","));
		List<String> listaSinMD5 =Arrays.asList("es_2009102612_9101533,es_2009101313_9160352,es_20091023_1_9124047,es_2009100911_9092718,es_2009100911_9130624,es_20091023_1_9125322,es_2009102611_9170200,es_20091026_1_9170942,es_2009102614_9180005,es_20091105_1_9174519,es_2009110513_9175035,es_2009110661_9105140,es_2009111111_9134732".split(","));
		String[] arrayMD5 = listaDeMD5.toArray(new String[0]);
		String[] arraySinMD5 = listaSinMD5.toArray(new String[0]);
		Hashtable<String, String> tablaLimpieza = new Hashtable<String, String>();
		for (int i = 0; i < arraySinMD5.length; i++) {
			tablaLimpieza.put(arraySinMD5[i],"");
		}
		servicioOdePub.modificaODEsMD5(tablaLimpieza);
		Hashtable<String, String> tablaRestauracion = new Hashtable<String, String>();
		for (int i = 0; i < arraySinMD5.length; i++) {
			tablaRestauracion.put(arraySinMD5[i],arrayMD5[i]);
		}
		servicioOdePub.modificaODEsMD5(tablaRestauracion);
	}
	
	private static void pruebaConCaclculoMD5()
	{
		
//		Recorremos una lista de ODEs y calculamos su md5 sacandolo por pantalla
		List<String> listaSinMD5 =Arrays.asList("es_2009102612_9101533,es_2009101313_9160352,es_20091023_1_9124047,es_2009100911_9092718,es_2009100911_9130624,es_20091023_1_9125322,es_2009102611_9170200,es_20091026_1_9170942,es_2009102614_9180005,es_20091105_1_9174519,es_2009110513_9175035,es_2009110661_9105140,es_2009111111_9134732".split(","));
//		Obtenemos la lista de los path que no tienen md5 en formato (idODE/path).
		Hashtable<String, String> listaODEsPAth = srvLocalizador.obtenerPathODES(listaSinMD5);
//		Tenemos que recorrer la lista de (idODE/path) y calcular los MD5 para cada ODE sin md5
		Hashtable<String, String> listaODEsMD5 = new Hashtable<String, String>();
		/*OJO: Es de suponer que la lista de los ODEs que no tienen md5 y la lista de los ODES publicados mide lo mismo*/
		String listaIds[] = listaODEsPAth.keySet().toArray(new String[0]); // sacamos las claves de la lista de (idODE/path) => ids
		String pathODE= "";
		String md5 = "";
		String pathBaseODEs = Propiedades.getPropertyValue(Propiedades.PATH_BASE_ODES);
		Hashtable<String, Long> tiempos = new Hashtable<String, Long>();
		long antes = 0;
		long despues = 0;
		long total = 0;
		for (int i = 0; i < listaIds.length; i++) {
//			Sacamos el path y lo concatenamos a la url basica para tener el path completo.
			pathODE = listaODEsPAth.get(listaIds[i]);
//			Calculamos el md5 para el ODE
			antes = System.currentTimeMillis();
			md5 = CalculoMD5.calculaMD5DePath(pathBaseODEs+pathODE);
			despues = System.currentTimeMillis();
			logger.debug("Calculado md5 para id["+listaIds[i]+"],path["+pathODE+"]=>md5["+md5+"]");
//			Metemos el id del ODE y su md5 en una tabla.
			listaODEsMD5.put(listaIds[i], md5);
			tiempos.put(listaIds[i], despues - antes);
			total+=(despues-antes);
		}
		pintarODESMD5(listaODEsMD5, tiempos);
		logger.info("El tiempo total del algoritmo de md5 ha sido de ["+total+"] milisegundos");
	}
	
	private static void pintarODESMD5(Hashtable listaODEsMD5, Hashtable tiempos)
	{
		Enumeration<String> ids = listaODEsMD5.keys();
		logger.info("Listado de identificadores y valor de MD5 calculado");
		long tiempoTotal = 0;
		while (ids.hasMoreElements()) {
			String idODE = (String) ids.nextElement();
			logger.info("Calculado idODE["+idODE+"] md5]["+listaODEsMD5.get(idODE)+"] en ["+tiempos.get(idODE)+"] milisegundos");
			tiempoTotal+=((Long)tiempos.get(idODE)).longValue();
		}
		logger.info("Tiempo total de calculo de md5:["+tiempoTotal/1000+"]segundos");
	}
}
