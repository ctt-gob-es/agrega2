// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.rest.servicios;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import es.agrega.soporte.agregaProperties.AgregaProperties;

import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO;
import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonPathVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.parseadorXML.rest.Ode;
import es.pode.parseadorXML.rest.Odes;
import es.pode.parseadorXML.rest.RSP;
import es.pode.parseadorXML.rest.TaxonPath;
import es.pode.publicacion.negocio.servicios.OdePublicadoVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.url.ODEPublico;
import es.pode.tagging.negocio.servicios.TaggingVO;

/**
 * @see es.pode.rest.servicios.SrvRestService
 */

public class SrvRestServiceImpl extends
		es.pode.rest.servicios.SrvRestServiceBase {

	private SrvPropiedadService prop = null;
	private static final String SLASH = "/";
	private static final String REPLACE_REGEXP = "\\{0\\}";
	public final static Short UNEXPECTED_ERROR_CODE = 0;
	public final static String UNEXPECTED_ERROR_MSG = "Unexpected error";
	public final static Short AC_NODE_ERROR_CODE = 1;
	public final static String AC_NODE_ERROR_MSG = "The requested curricular area does not exist";
	public final static Short NO_SUCH_METHOD_ERROR_CODE = 2;
	public final static String NO_SUCH_METHOD_ERROR_MSG = "The requested method does not exist";
	public final static Short NO_SUCH_USER_ERROR_CODE = 3;
	public final static String NO_SUCH_USER_ERROR_MSG = "The request user does not exist";
	public final static Short WRONG_AGREGATION_VALUE_ERROR_CODE = 4;
	public final static String WRONG_AGREGATION_VALUE_ERROR_MSG = "The aggregation value does not exist";
	public final static Short LANGUAGE_NOT_SUPPORTED_ERROR_CODE = 5;
	public final static String LANGUAGE_NOT_SUPPORTED_ERROR_MSG = "The requested language is not supported by the repository";
	public final static Short PAGE_OVERFLOW_ERROR_CODE = 6;
	public final static String PAGE_OVERFLOW_ERROR_MSG = "The requested page does not exist";
	public final static Short MISSING_PARAMETER_ERROR_CODE = 7;
	public final static String MISSING_PARAMETER_ERROR_MSG = "The parameter {0} is required";
	public final static Short PARAM_FORMAT_ERROR_CODE = 8;
	public final static String PARAM_FORMAT_ERROR_MSG = "The parameter {0} has a wrong format";
	
	public final static Short WRONG_MAX_MIN_ERROR_CODE = 9;
	public final static String WRONG_MAX_MIN_VALUE = "The parameter {0} must be between 0 and 5";
	
	

	private static String arbolCurricularVigente = null;
	private static Long fechaArbolCurricular = 0l;

	private static I18n i18n = new I18n();

	  /**
	   * <p>
	   * Lanza una busqueda de ODEs en el nodo filtrando por area
	   * curricular, nivel de agregacion y/o valoracion. En caso de que
	   * el nodo no exista en el arbol curricular vigente, el metodo
	   * devuelve un error informando de ello. La busqueda por arbol
	   * curricular se realiza en profundidad, es decir: si se busca por
	   * el nodo 1.1, se devuelven aquellos ODEs que tienen como area
	   * curricular 1/1.1 y todas las que estan por debajo (1/1.1/1.1.1,
	   * 1/1.1/1.1.2, ...). La busqueda por valoracion se realiza por
	   * intervalo minimo-maximo, siendo el maximo opcional (si se omite,
	   * se considera maximo, con valor 5.0). La respuesta es un string
	   * con el XML a utilizar como respuesta.
	   * </p>
	      * @param node  Nodo del arbol curricular en el que se desea hacer la busqueda. Se debe proporcionar el nodo mas profundo de la rama en que se desea buscar (por ejemplo, si se desea buscar en 1/1.1, se debe proporcionar el valor 1.1).
	         * @param page  Pagina de resultados que se desea obtener. Si no se proporciona ningun valor, se devuelve la primera pagina.
	         * @param language  Idioma en el que se desean buscar los ODEs. En caso de no proporcionar ninguno, se busca en el idioma por defecto de la plataforma.
	         * @param agregationLevel  Nivel de agregacion. Su valor debe estar entre 1 y 4 (incluidos).
	         * @param minValue  Valor minimo de valoracion de los ODEs buscados
	         * @param maxValue  Valor maximo de valoracion de los ODEs buscados
	      * @return java.lang.String
	 * @throws java.lang.Exception 
	   */
	protected java.lang.String handleSearch(
			java.lang.String node, java.lang.Integer page,
			java.lang.String language, java.lang.Short agregationLevel, Float minValue, Float maxValue) throws java.lang.Exception {
		RSP result = null;
		TaxonVO[] taxon=null;
		try {
			/*
			 * Lanzo la busqueda contra el nodo (getSrvBuscarService)
			 */
			//ParametrosBusquedaAvanzadaVO busqueda = new ParametrosBusquedaAvanzadaVO();
			ParametrosBusquedaAvanzadaVO30 busqueda = new ParametrosBusquedaAvanzadaVO30();
			logger
					.info("Peticion REST de busqueda por arbol curricular: node = "
							+ node
							+ "; language = "
							+ language
							+ "; page = "
							+ page);
			/*
			 * Chequeo que el nodo del arbol curricular solicitado existe
			 * (SrvTaxonomiaService). Si el nodo no existe o el idioma no esta
			 * soportado, devuelve null
			 */
			checkCurrentCurricularArea();
			if (!chequearStringVacio(node)){
					taxon = getSrvTaxonomiaService().obtenerTaxonPath(node,
							arbolCurricularVigente, i18n.obtenerIdiomaDefectoPlataforma());
				
				
				if (logger.isDebugEnabled())
					logger
							.debug("Respuesta de obtenerTaxonPath en comprobacion de node -> "
									+ taxon);
				if (taxon == null || taxon.length == 0) {
					result = new RSP(AC_NODE_ERROR_CODE, AC_NODE_ERROR_MSG);
					logger.info("Respuesta REST: nodo de arbol curricular " + node
							+ " no existe en arbol curricular "
							+ arbolCurricularVigente);
					return writeRestResponse(result);
				}
				busqueda.setTaxonomia(new String[] { arbolCurricularVigente + SLASH
						+ node });
			}
			if (agregationLevel!=null && agregationLevel.SIZE>0){
				if (agregationLevel<1 || agregationLevel>4){
					result = new RSP(WRONG_AGREGATION_VALUE_ERROR_CODE, WRONG_AGREGATION_VALUE_ERROR_MSG);
					logger
					.info("Respuesta REST: idioma "
							+ agregationLevel
							+ " no existe como nivel de agregacion en Agrega");
					return writeRestResponse(result);
				}
			}

			String idiomaBusqueda = null;
			String idiomaNavegacion = null;

			/*
			 * Chequeo de idioma: si el idioma solicitado es buscable, se buscan
			 * resultados en ese idioma. Se usa como idioma de navegacion si
			 * existe como idioma soportadom sino, se traducen resultados a
			 * idioma por defecto de la plataforma
			 */
			if (chequearStringVacio(language)){
				language= i18n.obtenerIdiomaDefectoPlataforma();
			}
			IdiomaBean bean = obtenerIdiomas(language);
			if(bean==null) {
				// Generar respuesta de error
				result = new RSP(LANGUAGE_NOT_SUPPORTED_ERROR_CODE, LANGUAGE_NOT_SUPPORTED_ERROR_MSG);
				logger
						.info("Respuesta REST: idioma "
								+ language
								+ " no existe como idioma de busqueda soportado en Agrega");
				return writeRestResponse(result);
			} else {
				idiomaBusqueda = bean.getIdiomaBusqueda();
				idiomaNavegacion = bean.getIdiomaNavegacion();
			}

			


			
			StringBuilder valoracion= null;
			if (minValue!=null && minValue.SIZE>0){
				if (minValue>5 || minValue<0){
					logger.error("minValue debe ser menor que 5 o mayor que 0");
					return writeRestResponse(new RSP(WRONG_MAX_MIN_ERROR_CODE,
							WRONG_MAX_MIN_VALUE.replaceAll(REPLACE_REGEXP,
									RestParamNames.MIN_VALUE_PARAM.getValue())));
				}
				if (maxValue!=null && maxValue.SIZE>0){
					if (maxValue>5 || maxValue<0){
						logger.error("maxValue debe ser menor que 5 o mayor que 0");
						return writeRestResponse(new RSP(WRONG_MAX_MIN_ERROR_CODE,
								WRONG_MAX_MIN_VALUE.replaceAll(REPLACE_REGEXP,
										RestParamNames.MAX_VALUE_PARAM.getValue())));
					}
					valoracion = new StringBuilder();
					valoracion.append(minValue).append(" ").append(maxValue);
				}else{
					valoracion = new StringBuilder();
					valoracion.append(minValue).append(" ").append("5.0");
				}
			}
			
			if (agregationLevel!=null && agregationLevel.SIZE>0){
				String[] nivelAgregacion = new String[1];
				nivelAgregacion[0]=String.valueOf(agregationLevel);
				busqueda.setNivelAgregacion(nivelAgregacion);
			}
			if (valoracion!=null){
				busqueda.setValoracion(valoracion.toString());
			}
			
			busqueda.setIdiomaBusqueda(idiomaBusqueda);
			busqueda.setIdiomaNavegacion(idiomaNavegacion);
			// TODO Revisar - Devuelve todos los resultados, dejando la paginacion a la aplicacion llamante
			busqueda.setNumeroResultados(-1);
			busqueda.setResultadosPorPagina(-1);
			
			//PAra probar
			busqueda.setBusquedaSimpleAvanzada("");

			ResultadoBusquedaVO resultados = getSrvBuscarService().buscarAvanzado(busqueda);

			result = obtenerRespuestaRest(resultados, page, idiomaBusqueda,
					idiomaNavegacion, true);

		} catch (Exception e) {
			logger.error("Error inesperado durante la peticion REST - ", e);
			result = new RSP(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG);
		}
		/*
		 * Escribo el XML-REST resultado
		 */
		String response = null;

		response = writeRestResponse(result);
		
		// Modificamos el encoding del xml devuelto
		response = response.replace("UTF-8", "ISO-8859-1");


		return response;
	}


	  /**
	   * <p>
	   * Realiza una busqueda de objetos en el nodo por etiqueta social
	   * (tag). La respuesta es un string con el XML a utilizar como
	   * respuesta.
	   * </p>
	   * @param tag 
	   * @param page  Pagina de resultados que se desea obtener. Si no se proporciona ningun valor, se devuelve la primera pagina.
	   * @return java.lang.String
	 * @throws java.lang.Exception 
	   */
	protected java.lang.String handleSearchByTag(java.lang.String tag,
			java.lang.Integer page) throws java.lang.Exception {

		RSP result = null;
		try {
			logger
					.info("Peticion REST de busqueda de ODEs por tag: tag = "
							+ tag
							+ "; page = "
							+ page);
			

			
			 TaggingVO[] resultados = getSrvTaggingServer().obtenerOdesDeTag(tag);


			
			result = obtenerRespuestaRestByTag(resultados, page);

		} catch (Exception e) {
			logger.error("Error inesperado durante la peticion REST - ", e);
			result = new RSP(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG);
		}
		/*
		 * Escribo el XML-REST resultado
		 */
		String response = null;

		response = writeRestResponse(result);

		return response;
	

	}


	  /**
	   * <p>
	   * La respuesta es un string con el XML a utilizar como respuesta.
	   * </p>
	      * @param page  Pagina de resultados que se desea obtener. Si no se proporciona ningun valor, se devuelve la primera pagina.
	         * @param userId  Nombre corto del usuario cuyos ODEs se desean recuperar.
	      * @return java.lang.String
	 * @throws java.lang.Exception 
	   */
	protected java.lang.String handleBrowseUserOwnedPublicODEs(
			java.lang.Integer page, java.lang.String userId)
			throws java.lang.Exception {
		RSP result = null;
		try {
			logger.info("Peticion REST de ODEs publicados por usuario userId = " + userId);
			

			
			  TransicionVO[] resultados = getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(userId);


			result = obtenerRespuestaRestBrowseUserOwnedPublicODEs(resultados, page);

		} catch (Exception e) {
			logger.error("Error inesperado durante la peticion REST - ", e);
			result = new RSP(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG);
		}
		/*
		 * Escribo el XML-REST resultado
		 */
		String response = null;

		response = writeRestResponse(result);

		return response;
	}

	  /**
	   * <p>
	   * La respuesta es un string con el XML a utilizar como respuesta.
	   * </p>
	      * @param page  Pagina de resultados que se desea obtener. Si no se proporciona ningun valor, se devuelve la primera pagina.
	         * @param userId  Nombre corto del usuario cuyos ODEs se desean recuperar.
	      * @return java.lang.String
	 * @throws java.lang.Exception 
	   */
	protected java.lang.String handleBrowseUserPublicODEs(
			java.lang.Integer page, java.lang.String userId)
			throws java.lang.Exception {
		RSP result = null;
		try {
			logger.info("Peticion REST de ODEs publicados por usuario userId = " + userId);
			

			
			   OdePublicadoVO[] resultados = getSrvPublicacionService().obtenODEsPublicadoUsuario(userId);

			
			result = obtenerRespuestaRestBrowseUserPublicODEs(resultados, page);

		} catch (Exception e) {
			logger.error("Error inesperado durante la peticion REST - ", e);
			result = new RSP(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MSG);
		}
		/*
		 * Escribo el XML-REST resultado
		 */
		String response = null;

		response = writeRestResponse(result);

		return response;
	}

	/**
	   * <p>
	   * Procesa la peticion REST validando los parametros de la peticion
	   * y escribiendo la respuesta en el formato XML-REST.
	   * </p>
	 * @param request 
	      * @return java.lang.String
	 * @throws java.lang.Exception 
	   */

	protected java.lang.String handleProcessRestRequest(
			es.pode.rest.servicios.RestRequestVO request)
			throws java.lang.Exception {

		String result = null;
		try {
			logger.info("Peticion REST recibida. Parametros: method = "
					+ request.getMethod() + "; node = " + request.getNode()
					+ "; page = " + request.getPage() + "; language = "
					+ request.getLanguage() + "; tag = " + request.getTag()
					+ "; userId = " + request.getUserId() + "; minValue = "
					+ request.getMinValue() + "; maxValue = "
					+ request.getMaxValue() + "; aggregationLevel = "
					+ request.getAgregationLevel());
			
			Integer page = null;
			Float minValue = null;
			Float maxValue = null;
			Short aggregationLevel = null;
			
			if (chequearStringVacio(request.getMethod())) {
				return writeRestResponse(new RSP(MISSING_PARAMETER_ERROR_CODE,
						MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
								RestParamNames.METHOD_PARAM.getValue())));
			}

			/*
			 * Integracion con ESB: para facilitar esta integracion, considero
			 * en la validacion de parametros que cadena vacia y null son lo
			 * mismo. Transformo todas las cadenas vacias a null.
			 * 
			 * @fjespino
			 */
			
			// Validacion de parametros aggregationLevel
			if(!chequearStringVacio(request.getAgregationLevel())) {
				if(!chequearInteger(request.getAgregationLevel())) {
					return writeRestResponse(new RSP(PARAM_FORMAT_ERROR_CODE,
							PARAM_FORMAT_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.AGGREGATION_LEVEL_PARAM.getValue())));
				} else {
					aggregationLevel = Short.parseShort(request.getAgregationLevel());
				}
			}
			
			
//			 Validacion de parametros page
			if(!chequearStringVacio(request.getPage())) {
				if(!chequearShort(request.getPage())) {
					return writeRestResponse(new RSP(PARAM_FORMAT_ERROR_CODE,
							PARAM_FORMAT_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.PAGE_PARAM.getValue())));
				} else {
					page = Integer.parseInt(request.getPage());
				}
			}
//			 Validacion de parametro MinValue
			if(!chequearStringVacio(request.getMinValue())) {
				if(!chequearFloat(request.getMinValue())) {
					return writeRestResponse(new RSP(PARAM_FORMAT_ERROR_CODE,
							PARAM_FORMAT_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.MIN_VALUE_PARAM.getValue())));
				} else {
					minValue = Float.parseFloat(request.getMinValue());
				}
			}
			
//			 Validacion de parametro maxValue
			if(!chequearStringVacio(request.getMaxValue())) {
				if(!chequearFloat(request.getMaxValue())) {
					return writeRestResponse(new RSP(PARAM_FORMAT_ERROR_CODE,
							PARAM_FORMAT_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.MAX_VALUE_PARAM.getValue())));
				} else {
					maxValue = Float.parseFloat(request.getMaxValue());
				}
			}
			
			if (RestMethods.SEARCH.getValue().equals(request.getMethod())) {
				/*
				 * Peticion REST de una busqueda. Valido los parametros node,
				 * agregationLevel, minValue, maxValue, language y page. Al
				 * menos uno de los parametros node, agregationLevel o minValue
				 * son obligatorios.
				 */
				if (chequearStringVacio(request.getNode()) && chequearStringVacio(request.getAgregationLevel()) && chequearStringVacio(request.getMinValue())){
					StringBuilder builder = new StringBuilder();
					builder.append(RestParamNames.NODE_PARAM.getValue()).append(", ").append(RestParamNames.AGGREGATION_LEVEL_PARAM.getValue())
					.append(" or ").append(RestParamNames.MIN_VALUE_PARAM.getValue());
						result = writeRestResponse(new RSP(
								MISSING_PARAMETER_ERROR_CODE,
								MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
										builder.toString())));
					
//					if (request.getAgregationLevel() == null)
//						result = writeRestResponse(new RSP(
//								MISSING_PARAMETER_ERROR_CODE,
//								MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
//										RestParamNames.AGGREGATION_LEVEL_PARAM.getValue())));
//					
//					if (request.getMinValue() == null)
//						result = writeRestResponse(new RSP(
//								MISSING_PARAMETER_ERROR_CODE,
//								MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
//										RestParamNames.MIN_VALUE_PARAM.getValue())));
					
				}
				else {
					
					result = handleSearch(request.getNode(),
							page, request
									.getLanguage(), aggregationLevel, minValue, maxValue);
					
				}
			}else if (RestMethods.BROWSE_USER_OWNED_PUBLIC_ODES.getValue().equals(request.getMethod())) {
				if (chequearStringVacio(request.getUserId()))
					result = writeRestResponse(new RSP(
							MISSING_PARAMETER_ERROR_CODE,
							MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.USER_PARAM.getValue())));
				else {
					
					result = handleBrowseUserOwnedPublicODEs(page, request.getUserId());
				}
			}else if (RestMethods.BROWSE_USER_PUBLIC_ODES.getValue().equals(request.getMethod())) {
				if (chequearStringVacio(request.getUserId()))
					result = writeRestResponse(new RSP(
							MISSING_PARAMETER_ERROR_CODE,
							MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.USER_PARAM.getValue())));
				else {
					
					result = handleBrowseUserPublicODEs(page, request.getUserId());
				}
			}else if (RestMethods.SEARCH_BY_TAG.getValue().equals(request.getMethod())) {
				if (chequearStringVacio(request.getNode()))
					result = writeRestResponse(new RSP(
							MISSING_PARAMETER_ERROR_CODE,
							MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.NODE_PARAM.getValue())));
				if (chequearStringVacio(request.getTag()))
					result = writeRestResponse(new RSP(
							MISSING_PARAMETER_ERROR_CODE,
							MISSING_PARAMETER_ERROR_MSG.replaceAll(REPLACE_REGEXP,
									RestParamNames.TAG_PARAM.getValue())));
				else {
					result = handleSearchByTag(request.getTag(), page);
				}
			} else {
				/*
				 * Metodo no existente
				 */
				result = writeRestResponse(new RSP(NO_SUCH_METHOD_ERROR_CODE,
						NO_SUCH_METHOD_ERROR_MSG));
			}
			
			
			
			
		} catch (Exception e) {
			logger.error("Excepcion inesperada durante el proceso de una peticion REST",e);
			result = writeRestResponse(new RSP(UNEXPECTED_ERROR_CODE,UNEXPECTED_ERROR_MSG));
		}

		return result;
	}

	private final Boolean chequearStringVacio(String parametro) {
		return (parametro==null||"".equals(parametro));
	}
	
	private final Boolean chequearInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private final Boolean chequearFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private final Boolean chequearShort(String value) {
		try {
			Short.parseShort(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
     * 
     */
	private java.lang.String writeRestResponse(
			es.pode.parseadorXML.rest.RSP response) throws java.lang.Exception {
		StringWriter writer = new StringWriter();
		if (logger.isDebugEnabled())
			logger.debug("Escribiendo repuesta REST...");
		getRestDao().escribirRespuestaRest(response, writer);
		String result = writer.toString();
		try {
			writer.close();
		} catch (Exception e) {
			logger.debug("writer.close lanzo una excepcion", e);
		}

		return result;
	}

	/**
	 * Una vez por dia recupera el arbol curricular vigente de fuentes
	 * taxonomicas.
	 * 
	 * @throws Exception
	 */
	private void checkCurrentCurricularArea() throws Exception {
		Long today = System.currentTimeMillis();
		Long day2millis = 86400000l;
		try {
			if (SrvRestServiceImpl.fechaArbolCurricular == null
					|| (today - SrvRestServiceImpl.fechaArbolCurricular) > day2millis) {

					if (logger.isDebugEnabled())
						logger
								.debug("Renovando arbol curricular vigente en servicio REST");
					SrvRestServiceImpl.arbolCurricularVigente = getSrvTaxonomiaService()
							.obtenerArbolVigente().getVocabIdentifier();
					SrvRestServiceImpl.fechaArbolCurricular = today;
					if (logger.isDebugEnabled())
						logger.debug("Arbol curricular vigente "
								+ arbolCurricularVigente);
			}
		} catch (Exception e) {
			logger.error("Error en la renovacion del arbol curricular vigente",
					e);
			throw e;
		}
	}

	private Boolean checkLanguage(String language, String[] supported) {
		return Arrays.asList(supported).contains(language);
	}

	private IdiomaBean obtenerIdiomas(String language) {
		/*
		 * Chequeo de idioma: si el idioma solicitado es buscable, se buscan
		 * resultados en ese idioma. Se usa como idioma de navegacion si
		 * existe como idioma soportadom sino, se traducen resultados a
		 * idioma por defecto de la plataforma
		 */
		IdiomaBean result = new IdiomaBean();
		try {
			if (language == null) {
				result.setIdiomaBusqueda(i18n.obtenerIdiomaDefectoPlataforma());
				result.setIdiomaNavegacion(i18n.obtenerIdiomaDefectoPlataforma());
			} else if (checkLanguage(language, i18n.obtenerIdiomasBuscables())) {
				result.setIdiomaBusqueda(language);
				result.setIdiomaNavegacion(checkLanguage(language, i18n
						.obtenerIdiomasPlataforma()) ? language : i18n
						.obtenerIdiomaDefectoPlataforma());
			} else {
				result=null;
			}
		} catch (Exception e) {
			logger.error("Error en la obtencion de idiomas",e);
			result=null;
		}
		return result;
	}
	
	private RSP obtenerRespuestaRest(ResultadoBusquedaVO resultados,
			Integer page, String idiomaBusqueda, String idiomaNavegacion,
			Boolean recuperarAC) throws Exception {
		/*
		 * Pagino los resultado y recupero los de la pagina solicitada
		 * (propiedad en agrega.properties)
		 */
		RSP respuesta = new RSP();
		int k=0;
		if (resultados == null || resultados.getNumeroResultados() == 0) {
			if (logger.isDebugEnabled())
				logger
						.debug("Respuesta REST: buscar devuelve resultados = null; escribo respuesta REST");
			Odes odes = new Odes();
			odes.setTotal(0);
			odes.setPage(0);
			odes.setPages(0);
			odes.setOdes(new Ode[] {});
			respuesta.setStat(RSP.STAT_OK);
			respuesta.setOdes(odes);

		} else {
			if (page == null)
				page = 1;
			Integer numResultados = resultados.getNumeroResultados();
			Integer resultadosPorPagina = new Integer(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.REST_RESULTADOS_POR_PAGINA));
			Integer paginas = numResultados / (resultadosPorPagina);
			if ((numResultados%resultadosPorPagina)!=0){
				paginas = paginas + 1;
			}
			if (page > paginas || page < 1) {
				logger
						.info("Respuesta REST: la pagina solicitada no existe en el resultado obtenido");
				respuesta.setStat(RSP.STAT_ERROR);
				respuesta.setErrorCode(PAGE_OVERFLOW_ERROR_CODE);
				respuesta.setErrorMsg(PAGE_OVERFLOW_ERROR_MSG);
			} else {
				// Conjunto de areas curriculares para su traduccion
				HashSet<String> areasCurriculares = new HashSet<String>();
				
				respuesta.setStat(RSP.STAT_OK);
				ValoresBusquedaVO[] valores = resultados.getResultadoBusqueda();
				int init = ((page - 1) * resultadosPorPagina);
				int end = (init + (resultadosPorPagina-1))+1;
				if (end > valores.length)
					end = valores.length;
				if (logger.isDebugEnabled())
					logger.debug("Paginando resultados: Pagina = " + page
							+ "; init = " + init + "; end = " + end
							+ "; length = " + valores.length);
				Ode[] odes = new Ode[end - init];
				for (int i = init; i < end; i++) {
					Ode ode = new Ode();
					ode.setIdentifier(valores[i].getId());
					ode.setTitle(valores[i].getTitulo());
					ode.setUrl(ODEPublico.urlFichaODEPublicado(valores[i]
							.getId(), idiomaBusqueda));
					ode.setUrlPreview(ODEPublico.urlPrevisualizaODEPublicado(
							valores[i].getId(), idiomaNavegacion));
					ode.setAgregationLevel(Short.valueOf(valores[i].getNivelAgregacion().toString()));
					if(valores[i].getValoracion()!=null && valores[i].getValoracion()!= -1.0f) ode.setEvaluation(valores[i].getValoracion());
					if (recuperarAC && valores[i].getAreaCurricular()!=null && valores[i].getAreaCurricular().length>0) {
						if(logger.isDebugEnabled()) logger.debug("Recuperando taxonomias de los resultados");
						TaxonPath[] taxonPath = new TaxonPath[valores[i].getAreaCurricular().length];
						String[] areaCurricular = valores[i].getAreaCurricular();

						for(int j=0;j<areaCurricular.length;j++) {
							// Buscar devuelve las taxonomias del modo 1/1.1/1.1.2/ - Necesito solo el último nodo
							String[] taxones = areaCurricular[j].split(SLASH);
							taxonPath[j]=new TaxonPath();
							taxonPath[j].setTaxonId(taxones[taxones.length-1]);
							areasCurriculares.add(taxonPath[j].getTaxonId());
							
						}
						ode.setTaxonPath(taxonPath);
					}
					odes[k]=ode;
					k++;
				}
				/*
				 * Completo la informacion sobre taxonomias (traduccion en
				 * SrvTaxonomiaService). Esto solo se realiza en la busqueda por
				 * arbol curricular
				 */
				if (recuperarAC) {
					// Realizo la traduccion de las taxonomias de los resultados
					if(areasCurriculares.size()>0) {
						TaxonPathVO[] traducciones = getSrvTaxonomiaService()
								.obtenerVariosTaxonPaths(
										areasCurriculares
												.toArray(new String[areasCurriculares
														.size()]),
										arbolCurricularVigente,
										idiomaNavegacion);
						HashMap<String, String> mapa = new HashMap<String, String>();
						for (TaxonPathVO taxonPathVO : traducciones) {
							String id= taxonPathVO.getDatos()[0].getId();
							StringBuilder traduccion = new StringBuilder();
							for (TaxonVO taxon : taxonPathVO.getDatos()) {
								if(traduccion.length()>0) traduccion.insert(0, SLASH);
								traduccion.insert(0, taxon.getValorTax());
							}
							mapa.put(id, traduccion.toString());
						}
						/*
						 * Recorrer el Array de Odes para insertar las traducciones
						 */
						for (Ode ode : odes) {
							if(ode.getTaxonPath()!=null) {
								for (TaxonPath taxonPath : ode.getTaxonPath()) {
									taxonPath.setDescription(mapa.get(taxonPath.getTaxonId()));
								}
							}
						}
					}
				}
				
				Odes restResults = new Odes();
				restResults.setPage(page);
				restResults.setPages(paginas);
				restResults.setTotal(numResultados);
				restResults.setOdes(odes);
				respuesta.setStat(RSP.STAT_OK);
				respuesta.setOdes(restResults);
				
			}
		}
		return respuesta;
	}
	
	private class IdiomaBean {
		private String idiomaBusqueda=null;
		/**
		 * @return the idiomaBusqueda
		 */
		public String getIdiomaBusqueda() {
			return idiomaBusqueda;
		}
		/**
		 * @param idiomaBusqueda the idiomaBusqueda to set
		 */
		public void setIdiomaBusqueda(String idiomaBusqueda) {
			this.idiomaBusqueda = idiomaBusqueda;
		}
		/**
		 * @return the idiomaNavegacion
		 */
		public String getIdiomaNavegacion() {
			return idiomaNavegacion;
		}
		/**
		 * @param idiomaNavegacion the idiomaNavegacion to set
		 */
		public void setIdiomaNavegacion(String idiomaNavegacion) {
			this.idiomaNavegacion = idiomaNavegacion;
		}
		private String idiomaNavegacion=null;
	}
	
	
	private RSP obtenerRespuestaRestByTag(TaggingVO[] resultados,
			Integer page) throws Exception {
		/*
		 * Pagino los resultado y recupero los de la pagina solicitada
		 * (propiedad en agrega.properties)
		 */
		RSP respuesta = new RSP();
		int j=0;
		if (resultados == null || resultados.length == 0) {
			if (logger.isDebugEnabled())
				logger
						.debug("Respuesta REST: buscar devuelve resultados = null; escribo respuesta REST");
			Odes odes = new Odes();
			odes.setTotal(0);
			odes.setPage(0);
			odes.setPages(0);
			odes.setOdes(new Ode[] {});
			respuesta.setStat(RSP.STAT_OK);
			respuesta.setOdes(odes);

		} else {
			if (page == null)
				page = 1;
			Integer numResultados = resultados.length;
			Integer resultadosPorPagina = new Integer(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.REST_RESULTADOS_POR_PAGINA));
			Integer paginas = numResultados / (resultadosPorPagina);
			if ((numResultados%resultadosPorPagina)!=0){
				paginas = paginas + 1;
			}
			if (page > paginas || page < 1) {
				logger
						.info("Respuesta REST: la pagina solicitada no existe en el resultado obtenido");
				respuesta.setStat(RSP.STAT_ERROR);
				respuesta.setErrorCode(PAGE_OVERFLOW_ERROR_CODE);
				respuesta.setErrorMsg(PAGE_OVERFLOW_ERROR_MSG);
			} else {
				// Conjunto de areas curriculares para su traduccion
				HashSet<String> searchByTag = new HashSet<String>();
				
				respuesta.setStat(RSP.STAT_OK);
				int init = ((page - 1) * resultadosPorPagina);
				int end = (init + (resultadosPorPagina-1))+1;
				if (end > resultados.length)
					end = resultados.length;
				if (logger.isDebugEnabled())
					logger.debug("Paginando resultados: Pagina = " + page
							+ "; init = " + init + "; end = " + end
							+ "; length = " + resultados.length);
				Ode[] odes = new Ode[end - init];
							
				for (int i = init; i < end; i++) {
					
					Ode ode = new Ode();
					ode.setIdentifier(resultados[i].getIdOde());
					ode.setTitle(resultados[i].getTitulo());
					ode.setUrl(ODEPublico.urlFichaODEPublicado(resultados[i].getIdOde(), resultados[i].getIdiomaCat()));
					ode.setUrlPreview(ODEPublico.urlPrevisualizaODEPublicado(resultados[i].getIdOde(), resultados[i].getIdiomaCat()));

					odes[j]=ode;
					j++;
				}
				
				
				Odes restResults = new Odes();
				restResults.setPage(page);
				restResults.setPages(paginas);
				restResults.setTotal(numResultados);
				restResults.setOdes(odes);
				respuesta.setStat(RSP.STAT_OK);
				respuesta.setOdes(restResults);
				
			}
		}
		return respuesta;
	}
	
	
	private RSP obtenerRespuestaRestBrowseUserPublicODEs(OdePublicadoVO[] resultados,
			Integer page) throws Exception {
		//Este método es utilizado por handleBrowseUserOwnedPublicODEs y por handleBrowseUserPublicODEs 
		/*
		 * Pagino los resultado y recupero los de la pagina solicitada
		 * (propiedad en agrega.properties)
		 */
		RSP respuesta = new RSP();
		int j=0;
		if (resultados == null || resultados.length == 0) {
			if (logger.isDebugEnabled())
				logger
						.debug("Respuesta REST: buscar devuelve resultados = null; escribo respuesta REST");
			Odes odes = new Odes();
			odes.setTotal(0);
			odes.setPage(0);
			odes.setPages(0);
			odes.setOdes(new Ode[] {});
			respuesta.setStat(RSP.STAT_OK);
			respuesta.setOdes(odes);

		} else {
			if (page == null)
				page = 1;
			Integer numResultados = resultados.length;
			Integer resultadosPorPagina = new Integer(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.REST_RESULTADOS_POR_PAGINA));
			Integer paginas = numResultados / (resultadosPorPagina);
			if ((numResultados%resultadosPorPagina)!=0){
				paginas = paginas + 1;
			}
			if (page > paginas || page < 1) {
				logger
						.info("Respuesta REST: la pagina solicitada no existe en el resultado obtenido");
				respuesta.setStat(RSP.STAT_ERROR);
				respuesta.setErrorCode(PAGE_OVERFLOW_ERROR_CODE);
				respuesta.setErrorMsg(PAGE_OVERFLOW_ERROR_MSG);
			} else {
				
				
				respuesta.setStat(RSP.STAT_OK);
				int init = ((page - 1) * resultadosPorPagina);
				int end = (init + (resultadosPorPagina-1))+1;
				if (end > resultados.length)
					end = resultados.length;
				if (logger.isDebugEnabled())
					logger.debug("Paginando resultados: Pagina = " + page
							+ "; init = " + init + "; end = " + end
							+ "; length = " + resultados.length);
				Ode[] odes = new Ode[end - init];
							
				for (int i = init; i < end; i++) {
					Ode ode = new Ode();
					ode.setIdentifier(resultados[i].getIdODE());
					ode.setTitle(resultados[i].getTitulo());
					//TODO Deberán ir las URls
					ode.setUrl(ODEPublico.urlFichaODEPublicado(resultados[i].getIdODE(), resultados[i].getIdioma()));
					ode.setUrlPreview(ODEPublico.urlPrevisualizaODEPublicado(resultados[i].getIdODE(), resultados[i].getIdioma()));

					odes[j]=ode;
					j++;
				}
				
				
				Odes restResults = new Odes();
				restResults.setPage(page);
				restResults.setPages(paginas);
				restResults.setTotal(numResultados);
				restResults.setOdes(odes);
				respuesta.setStat(RSP.STAT_OK);
				respuesta.setOdes(restResults);
				
			}
		}
		return respuesta;
	}
	
	private RSP obtenerRespuestaRestBrowseUserOwnedPublicODEs(TransicionVO[] resultados,
			Integer page) throws Exception {
		//Este método es utilizado por handleBrowseUserOwnedPublicODEs y por handleBrowseUserPublicODEs 
		/*
		 * Pagino los resultado y recupero los de la pagina solicitada
		 * (propiedad en agrega.properties)
		 */
		RSP respuesta = new RSP();
		int j=0;
		if (resultados == null || resultados.length == 0) {
			if (logger.isDebugEnabled())
				logger
						.debug("Respuesta REST: buscar devuelve resultados = null; escribo respuesta REST");
			Odes odes = new Odes();
			odes.setTotal(0);
			odes.setPage(0);
			odes.setPages(0);
			odes.setOdes(new Ode[] {});
			respuesta.setStat(RSP.STAT_OK);
			respuesta.setOdes(odes);

		} else {
			if (page == null)
				page = 1;
			Integer numResultados = resultados.length;
			Integer resultadosPorPagina = new Integer(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.REST_RESULTADOS_POR_PAGINA));
			Integer paginas = numResultados / (resultadosPorPagina);
			if ((numResultados%resultadosPorPagina)!=0){
				paginas = paginas + 1;
			}
			if (page > paginas || page < 1) {
				logger
						.info("Respuesta REST: la pagina solicitada no existe en el resultado obtenido");
				respuesta.setStat(RSP.STAT_ERROR);
				respuesta.setErrorCode(PAGE_OVERFLOW_ERROR_CODE);
				respuesta.setErrorMsg(PAGE_OVERFLOW_ERROR_MSG);
			} else {
				
				
				respuesta.setStat(RSP.STAT_OK);
				int init = ((page - 1) * resultadosPorPagina);
				int end = (init + (resultadosPorPagina-1))+1;
				if (end > resultados.length)
					end = resultados.length;
				if (logger.isDebugEnabled())
					logger.debug("Paginando resultados: Pagina = " + page
							+ "; init = " + init + "; end = " + end
							+ "; length = " + resultados.length);
				Ode[] odes = new Ode[end - init];
							
				for (int i = init; i < end; i++) {
					Ode ode = new Ode();
					ode.setIdentifier(resultados[i].getIdODE());
					ode.setTitle(resultados[i].getTitulo());
					ode.setUrl(ODEPublico.urlPrevisualizaODEPublicadoAutonomo(resultados[i].getIdODE(), I18n.getInstance().obtenerIdiomaDefectoPlataforma()));
					ode.setUrlPreview(ODEPublico.urlPrevisualizaODEPublicadoAutonomo(resultados[i].getIdODE(), I18n.getInstance().obtenerIdiomaDefectoPlataforma()));

					odes[j]=ode;
					j++;
				}
				
				
				Odes restResults = new Odes();
				restResults.setPage(page);
				restResults.setPages(paginas);
				restResults.setTotal(numResultados);
				restResults.setOdes(odes);
				respuesta.setStat(RSP.STAT_OK);
				respuesta.setOdes(restResults);
			}
		}
		return respuesta;
	}
	
	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}
	
}