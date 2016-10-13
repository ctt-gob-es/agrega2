package es.pode.buscar.negocio.sqi.lenguajes;


import org.apache.log4j.Logger;

import es.pode.buscar.negocio.buscar.servicios.ParamBusquedaSQIVO;

public class VSQLWrapper {
	
	public final static String DEF_MAPPING_LOMES_LOMESVO = "DEF_MAPPING_LOMES_LOMESVO";
	private static final String INICIO_SIMPLE_QUERY = "<simpleQuery>";
	private static final String FIN_SIMPLE_QUERY = "</simpleQuery>";
	private static final String INICIO_TERM = "<term>";
	private static final String FIN_TERM = "</term>";
	
	protected static Logger logger = Logger.getLogger(VSQLWrapper.class);
	
	public static String map(Object parametroConsulta) throws Exception
	{
		if (parametroConsulta instanceof ParamBusquedaSQIVO )
				return map((ParamBusquedaSQIVO)parametroConsulta);
		else
			throw new Exception("Error mapeando parametros de consulta de clase["+parametroConsulta.getClass()+"] a lenguaje VSQL. El mapeo desde esta clase no esta implementado.");
	}
	/*
	 * Este metodo recibe un string con uno o varios documentos lomes concatenados y devuelve un
	 * array de VO's, resultado del parseo de los textos y mapeo de los textos al VO.
	 * */
	public static String map(final ParamBusquedaSQIVO parametrosBusqueda)
	{
//		TODO: hay que implementar la creacion de la consulta en VSQL a partir de los datos de cada VO
		String palabrasClave=parametrosBusqueda.getPalabrasClave();
		if(logger.isDebugEnabled())
			logger.debug("Las palabras clave obtenidas  son["+palabrasClave+"]");
//		String[] palabras=palabrasClave.split(" ");
		String query=INICIO_SIMPLE_QUERY+INICIO_TERM+palabrasClave+FIN_TERM+FIN_SIMPLE_QUERY;//Para que sea una sola cadena, no un y de cadenas
//		for(int i=0;i<palabras.length;i++){
//			query=query+INICIO_TERM+palabras[i]+FIN_TERM;
//		}
//		query=query+FIN_SIMPLE_QUERY;
		if(logger.isDebugEnabled())
			logger.debug("La consulta que lanzamos es la siguiente ["+query+"]");
		return query;
	}
}
