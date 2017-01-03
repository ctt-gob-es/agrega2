// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.oaipmh.negocio.servicios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.indexador.negocio.servicios.busqueda.ParamPeriodoRepositorioVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.oaipmh.negocio.resumptionToken.ResumptionTokenManager;
import es.pode.oaipmh.soporte.OAIPMHProperties;
import es.pode.parseadorXML.LomESDao;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.CopyrightAndOtherRestrictions;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.soporte.elastic.BuscadorMetadatosFederados;
import es.pode.soporte.elastic.Source;
import es.pode.soporte.utiles.string.UtilesString;
import es.pode.soporte.validador.ValidadorMEC;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;


/**
 * @see es.pode.oaipmh.negocio.servicios.SrvOaiPmhService
 */

public class SrvOaiPmhServiceImpl
    extends es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceBase
{

	private SrvPropiedadService prop = null;
	private static Logger logger = Logger.getLogger(SrvOaiPmhServiceImpl.class);

	private static final String RUTA_METADATOS_ODES_FEDERADOS = "uploads"+File.separator+"metadatosLomesOdesFederados";
	
	private static final String PROTOCOLO_HTTP = "protocoloHttp";
	private static final String BUSCADOR_FICHA = "buscadorFicha";
	private static final String INTERROGACION = "interrogacion";
	private static final String IGUAL = "igual";
	private static final String AMPERSAND = "ampersand";
	private static final String PARAMETRO_IDIOMA = "parametroIdioma";	
	private static final String PARAMETRO_ID_ODE = "parametroIdentificadorODE";
	
	public static final String FROM = "From";
	public static final String TO = "to";
	public static final String GUION = "-";
	public static final String DOS_PUNTOS = ":";
	
	public static final String BARRA = "barra";
	
	public static final int numMaxPaginaInt = OAIPMHProperties.numMaxPaginaInt;	
	
	
	private Properties props = null;
	private CacheManager cacheManager;
	private Cache cacheHeaders;	
	private Cache cacheRecords;	
	
	private static ResumptionTokenManager resumptionToken = null;
	
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public Cache getCacheHeaders() {
		return cacheHeaders;
	}

	public void setCacheHeaders(Cache cacheHeaders) {
		this.cacheHeaders = cacheHeaders;
	}
	
	public Cache getCacheRecords() {
		return cacheRecords;
	}

	public void setCacheRecords(Cache cacheRecords) {
		this.cacheRecords = cacheRecords;
	}
	
	public SrvOaiPmhServiceImpl()
	{
		InputStream is = null;
		
		if (resumptionToken == null) resumptionToken = new ResumptionTokenManager();
		
		try {
	//		Configuramos la clase de propiedades
			is = this.getClass().getResourceAsStream("/oaiPmh.properties");
			props = new Properties();
			props.load(is);
	//		Configuramos la cache de headers
			cacheManager= CacheManager.create();
			cacheHeaders = new Cache("cacheHeaders", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(props.getProperty("segundosCaducidad")).longValue(),//tiempo de vida de los elementos en la cache
					new Long(props.getProperty("segundosCaducidad")).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(cacheHeaders);
			
//			Configuramos la cache de records
			cacheManager= CacheManager.create();
			cacheRecords = new Cache("cacheRecords", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(props.getProperty("segundosCaducidad")).longValue(),//tiempo de vida de los elementos en la cache
					new Long(props.getProperty("segundosCaducidad")).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(cacheRecords);
			
		} catch (IOException e) {
			logger.error("ERROR: Accediendo a la cache manager - ",e);
			throw new RuntimeException(e);
		} finally {
			if( is != null ) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}		
	
	/**
     * Obtiene información sobre el repositorio      
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de identify
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleIdentify()
        throws java.lang.Exception
    {    	

    	/**
    	 * -------------------------------------------------------------------------
    	 * --------------------DECLARACION DE VARIABLES-----------------------------
    	 * -------------------------------------------------------------------------
    	 * */    			
		IdentifyVO identify = new IdentifyVO();
		DescripcionOaiIdentifierVO descripcionOaiIdentifier = new DescripcionOaiIdentifierVO(); 
    	
    	try{
	    		    	
	    	/**
	    	 * -------------------------------------------------------------------------
	    	 * -------------------- SE RELLENA IDENTIFYVO ------------------------------
	    	 * -------------------------------------------------------------------------
	    	 * */
    			logger.debug("Se rellena el vo de identify");
    			
				//Nombre del repositorio
				identify.setNombreRepositorio(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NOMBRE_REPOSITORIO));
				if (logger.isDebugEnabled()) logger.debug("El valor del nombre del repositorio introducido es ["+identify.getNombreRepositorio()+"]");
				
				//Url del repositorio
				identify.setUrlRepositorio("http://"+ AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST) + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_REPOSITORIO_AGREGA));
				if (logger.isDebugEnabled()) logger.debug("El valor de la url del repositorio introducido es ["+identify.getUrlRepositorio()+"]");
				
				//Version del protocolo
				identify.setVersionProtocolo(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_VERSION_PROTOCOLO));
				if (logger.isDebugEnabled()) logger.debug("El valor de la version del repositorio introducido es ["+identify.getVersionProtocolo()+"]");
				
//				Email admin
				identify.setEmailAdmin(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.EMAIL_ADMIN_REPOSITORIO));
					
				if (logger.isDebugEnabled()) logger.debug("El valor del emailAdmin del repositorio introducido es ["+identify.getEmailAdmin()+"]");
				
				//Fecha inicio del repositorio
				Calendar fechaInicioRepo = null; 
				try
				{
					if (logger.isDebugEnabled())logger.debug("Se llama al indexador para obtener la fecha inicio del repositorio");
					fechaInicioRepo = this.getSrvBuscadorService().fechaInicioRepositorio();
					
				} catch (Exception e)
				{					
					logger.error("Error recuperando la fecha de inicio del repositorio del indexador - ",e);	
					//Se rellena el resultadoOaiRequestVO
					return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
												null, null, OAIPMHProperties.VERB_IDENTIFY);
				}
				identify.setFechaInicioRepositorio(devuelveFechaStringFromCalendar(fechaInicioRepo));				
				if (logger.isDebugEnabled()) logger.debug("El valor de la fecha de inicio del repositorio introducido es ["+identify.getFechaInicioRepositorio()+"]");
				
				//Politica de borrado
				identify.setPoliticaBorrado(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_POLITICA_BORRADO));
				if (logger.isDebugEnabled()) logger.debug("El valor de la politica de borrado del repositorio introducido es ["+identify.getPoliticaBorrado()+"]");
				
								
				//Temporalidad
				identify.setTemporalidad(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_GRANULARIDAD));
				if (logger.isDebugEnabled()) logger.debug("El valor de la temporalidad del repositorio introducido es ["+identify.getTemporalidad()+"]");
				
				//Descripcion del identificador OAI
				descripcionOaiIdentifier.setEsquema(OAIPMHProperties.eschemaIdentifier());
				if (logger.isDebugEnabled()) logger.debug("Descripcion OAI-identifier. El valor del esquema del identificador OAI es ["+descripcionOaiIdentifier.getEsquema()+"]");				
				descripcionOaiIdentifier.setIdentificadorRepositorio(OAIPMHProperties.namespaceIdentifier());
				if (logger.isDebugEnabled()) logger.debug("Descripcion OAI-identifier. El valor del identificador del repositorio del identificador OAI es ["+descripcionOaiIdentifier.getIdentificadorRepositorio()+"]");				
				descripcionOaiIdentifier.setDelimitador(this.DOS_PUNTOS);
				if (logger.isDebugEnabled()) logger.debug("Descripcion OAI-identifier. El valor del delimitador del identificador OAI es ["+descripcionOaiIdentifier.getDelimitador()+"]");
				descripcionOaiIdentifier.setEjemploIdentificador(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_EJEMPLO_IDENTIFIER_OAI));
				if (logger.isDebugEnabled()) logger.debug("Descripcion OAI-identifier. El valor del ejemplo del identificador OAI es ["+descripcionOaiIdentifier.getEjemploIdentificador()+"]");
				identify.setDescripcionOaiIdentifier(descripcionOaiIdentifier);
				
				/**
		    	 * -------------------------------------------------------------------------
		    	 * -------------------- SE RELLENA EL VO DE RETORNO ------------------------
		    	 * -------------------------- Y SE DEVUELVE --------------------------------
		    	 * */
				if (logger.isDebugEnabled()) logger.debug("Se rellena el vo de retorno con los valores: IdentifyVO:["+identify+"], " +
						"codigo de error ["+null+"] en el verbo ["+OAIPMHProperties.VERB_IDENTIFY+"]");				
				return devuelveResultadoOaiRequest(null, null, 
						identify, null, OAIPMHProperties.VERB_IDENTIFY);			
    	} catch (Exception e)
    	{    		
			logger.error("Error recuperando los datos del repositorio - ",e);
//			Se rellena el resultadoOaiRequestVO
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
										null, null, OAIPMHProperties.VERB_IDENTIFY);
    	}		
    }

    /**
     * Obtiene el listado de los headers que existen en el repositorio
     * @param  parametroLlamada ParametrosOaiPmhVO: vo que contiene los parametros de busqueda  
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de listIdentfiers
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleListIdentifiers(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada)
        throws java.lang.Exception
    {
   	
    	/**
    	 * -------------------------------------------------------------------------
    	 * --------------------DECLARACION DE VARIABLES-----------------------------
    	 * -------------------------------------------------------------------------
    	 * */    		
		ParamPeriodoRepositorioVO paramBusq = new ParamPeriodoRepositorioVO();	
		es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] listIdentifiers = null;
		es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] resultadosHeaderRetorno = null;
		ArrayList resultadoHeaderAux = new ArrayList();		
		ReanudacionTokenVO token = new ReanudacionTokenVO();
		ResumptionTokenCacheHit tokenCacheHit = new ResumptionTokenCacheHit();
        int numInicio= 0;
        es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] resultadoHeader= null;
        String claveToken = "";
        int numFin = 0;
        Element elemento = null;
        Element elementoIndice = null;
        ResumptionTokenCacheHit siguienteCacheToken = new ResumptionTokenCacheHit();

       // es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO listIdentifiers[]
        
		
		/**
    	 * -------------------------------------------------------------------------
    	 * ------ COMPROBAMOS SI EL REPOSITORIO ADMITE ESE METADATAPREFIX ----------
    	 * ----------------- PARAMETRO REQUERIDO POR EL PROTOCOLO-------------------
    	 * -------------------------------------------------------------------------
    	 * */
		logger.debug("parametroLlamada.getCodigoPaginacion() <"+parametroLlamada.getCodigoPaginacion()+">");
		if((parametroLlamada.getCodigoPaginacion() == null)||(parametroLlamada.getCodigoPaginacion() == ""))
		{
			if (!OAIPMHProperties.esFormatoMetadatoAdmitido(parametroLlamada.getPrefijoMetadato()))
			{
				if (logger.isDebugEnabled()) logger.debug("El metadataPrefix ["+parametroLlamada.getPrefijoMetadato()+"] no esta admitido en el repositorio");
				//			Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT, OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT_DES, 
										null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
			}
		}
		
		/**
    	 * -------------------------------------------------------------------------
    	 * --------------- COMPROBAMOS SI EL REPOSITORIO ADMITE SET ----------------
    	 * -------------------------------------------------------------------------
    	 * */
		String set = parametroLlamada.getIdentificadorConjunto();
		boolean filtrarMetadatosFederados = false;
		
		if(set!=null && !set.isEmpty())				
		{			
			if(!OAIPMHProperties.repositorioAdmiteSets()) {
	    		logger.debug("Sets no estan admitidos en el repositorio");	
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_SET_HIERARCHY, OAIPMHProperties.ERR_NO_SET_HIERARCHY_DES, 
	    									null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);				
			}
			
	    	if(!esSetValido(set)) {
	    		logger.debug("Se ha recibido un set no valido");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_SET_DES, 
	    				                           null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);					
	    	}
	    	
    		// Se trata de una consulta a metadatos federeados. Debe filtrarse por fechas y un máximo de un mes
    		if (esSetMetadatosFederados(set) && 
    				!validacionFechasMetadatosFederados(parametroLlamada.getFechaDesde(), parametroLlamada.getFechaHasta()))
    		{
    			logger.error("Las fechas proporcionadas no son correctas");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_ARGUMENT_DES, 
                           null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
    		}
	    	
    		filtrarMetadatosFederados = true;
    	}    	
		
		/**
    	 * -------------------------------------------------------------------------
    	 * -------------TRATAMIENTO DE LA FECHADESDE Y FECHAHASTA-------------------
    	 * ---------------------- PARAMETROS OPCIONALES ----------------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
		tratamientoFechaDesdeHasta(parametroLlamada.getFechaDesde(), (parametroLlamada.getFechaHasta()), paramBusq);    	
    	
    	/**
    	 * -------------------------------------------------------------------------
    	 * ------------------- COMPROBAMOS EL RESUMPTION TOKEN ---------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
    	String idResumptionToken = parametroLlamada.getCodigoPaginacion();
    	try
    	{
    	if(idResumptionToken != null && !"".equals(idResumptionToken))
    	{    		
//    		 Vamos a comprobar a la cache si esta el identificador 
    		elemento = resumptionToken.devuelveElementoResumptionToken(idResumptionToken);
    		   		
    		if(elemento != null)
    		{
    			//El identificador se encontro en la cache    			
    			//Buscamos en la cache de resumptionToken el vo de resumptioTokenCacheHit    			
    			ResumptionTokenCacheHit vueltaCacheToken = (ResumptionTokenCacheHit)elemento.getObjectValue();
    			numInicio = vueltaCacheToken.getDesde().intValue();
                numFin = vueltaCacheToken.getHasta().intValue();
                paramBusq.setRegistroFinal(vueltaCacheToken.getHasta());
                paramBusq.setRegistroInicial(vueltaCacheToken.getDesde());
                logger.debug((new StringBuilder()).append("vueltaCacheToken.getDesde() ").append(vueltaCacheToken.getDesde()).toString());
                logger.debug((new StringBuilder()).append("vueltaCacheToken.getHasta() ").append(vueltaCacheToken.getHasta()).toString());
                
                if(filtrarMetadatosFederados) {

            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
        	    			parametroLlamada.getFechaDesde(), 
        	    			parametroLlamada.getFechaHasta(), 
        	    			parametroLlamada.getIdentificadorConjunto(), 
        	    			null,
        	    			vueltaCacheToken.getDesde());
            		
            		resultadoHeader = source2IndexadorResultadoHeaderVO(listaIdsOdes);
                } else {
                	resultadoHeader = getSrvBuscadorService().busquedaHeadersRepositorio(paramBusq);
                }
                
                logger.debug((new StringBuilder()).append("El numero de resultados del buscador es resultadoHeader ").append(resultadoHeader.length).toString());
                elementoIndice = resumptionToken.devuelveElementoResumptionToken("numElementosIndice");
                if(vueltaCacheToken.getSiguienteResumptionToken() != null)
                {
                    logger.debug("No es necesario crear otro resumptionToken recojo el identificador");
                    elemento = resumptionToken.devuelveElementoResumptionToken(vueltaCacheToken.getSiguienteResumptionToken());
                    siguienteCacheToken = (ResumptionTokenCacheHit)elemento.getObjectValue();
                    token = devuelveResumptionToken(siguienteCacheToken, siguienteCacheToken.getDesde(), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                } else
                {
                	logger.debug("Genero otro resumptionToken y lo guardo en la cache de resumptionToken");
                    numInicio = numFin;
                    numFin = numInicio + numMaxPaginaInt;
                    logger.debug((new StringBuilder()).append("numInicio ").append(numInicio).toString());
                    logger.debug((new StringBuilder()).append("numFin ").append(numFin).toString());
                    if(numInicio < ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue())
                    {
                        if(numFin > ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue())
                        {
                            logger.debug("Nos quedamos hasta el tamanio maximo del repositorio");
                            numFin = ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue();
                        }
                        logger.debug("Necesitar\351 generar otro resumptionToken");
                        claveToken = devuelveClaveResumptionToken();
                        siguienteCacheToken = resumptionToken.generateResumptionToken(Integer.valueOf(numInicio), Integer.valueOf(numFin), claveToken);
                        vueltaCacheToken.setSiguienteResumptionToken(claveToken);
                        resumptionToken.almacenarResumptionToken(vueltaCacheToken);
                        token = devuelveResumptionToken(siguienteCacheToken, Integer.valueOf(numInicio), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                    } else
                    {
                        logger.debug("Es la ultima pagina");
                        token = devuelveResumptionToken(null, vueltaCacheToken.getDesde(), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                    }
                    
                }
    		 } else
             {
                 logger.debug("El identificador del resumptionToken es incorrecto");
                 return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_RESUMPTION_TOK, OAIPMHProperties.ERR_BAD_RESUMPTION_TOK_DES, 
						   null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
             }
                
    	}else
    	{
    		logger.debug("Es la primera vez que se ejecuta esa query");
            Integer numElementosIndice = null;

            if(filtrarMetadatosFederados) {
            	
        		int nElementosTotales = obtenerNumTotalMetadatosFederados(
    	    			parametroLlamada.getFechaDesde(), 
    	    			parametroLlamada.getFechaHasta(), 
    	    			parametroLlamada.getIdentificadorConjunto(), 
    	    			null);

                numElementosIndice = new Integer(nElementosTotales);
            } else {
            	numElementosIndice = getSrvBuscadorService().obtenerTotalesRepositorioFechas(paramBusq).getDocumentosCount();
            }
            
            if(numElementosIndice == null || numElementosIndice.intValue() == 0)
            {
            	logger.debug((new StringBuilder()).append("No se ha podido encontrar ningun header con fechaDesde [").append(paramBusq.getDesde()).append("] y fechaHasta [").append(paramBusq.getHasta()).append("]").toString());
            	return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
					       null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
            }
            
            logger.debug((new StringBuilder()).append("El numero de elementos del indice ").append(numElementosIndice).toString());
            tokenCacheHit = resumptionToken.generateResumptionToken(numElementosIndice, numElementosIndice, "numElementosIndice");
            logger.debug("Se guarda en la cache un VO con el numero de elementos del indice");
           
            if(numElementosIndice.intValue() <= numMaxPaginaInt)
            {
                logger.debug("No hay paginacion");
                paramBusq.setRegistroFinal(numElementosIndice);
                paramBusq.setRegistroInicial(new Integer(0));
                logger.debug("Se llama al indexador para obtener toda la informaci\363n del indice");
                

                if(filtrarMetadatosFederados) {

            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
        	    			parametroLlamada.getFechaDesde(), 
        	    			parametroLlamada.getFechaHasta(),  
        	    			parametroLlamada.getIdentificadorConjunto(), 
        	    			null,
        	    			paramBusq.getRegistroInicial());
            		
            		resultadoHeader = source2IndexadorResultadoHeaderVO(listaIdsOdes);
                } else {                
                	resultadoHeader = getSrvBuscadorService().busquedaHeadersRepositorio(paramBusq);
                }
            } else
            {
                logger.debug("Hay paginacion");
                numFin = numMaxPaginaInt;
                if(logger.isDebugEnabled())
                {
                    logger.debug((new StringBuilder()).append("Llamamos al indexador con los parametros: desde [").append(paramBusq.getDesde()).append("] y hasta [").append(paramBusq.getHasta()).append("]").toString());
                }
                paramBusq.setRegistroFinal(new Integer(numFin));
                paramBusq.setRegistroInicial(new Integer(numInicio));

                if(filtrarMetadatosFederados) {

            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
        	    			parametroLlamada.getFechaDesde(), 
        	    			parametroLlamada.getFechaHasta(), 
        	    			parametroLlamada.getIdentificadorConjunto(), 
        	    			null,
        	    			paramBusq.getRegistroInicial());
            		
            		resultadoHeader = source2IndexadorResultadoHeaderVO(listaIdsOdes);
                } else {
                	resultadoHeader = getSrvBuscadorService().busquedaHeadersRepositorio(paramBusq);
                }
                logger.debug((new StringBuilder()).append("El numero de resultados del buscador es resultadoHeader ").append(resultadoHeader.length).toString());
                if(logger.isDebugEnabled())
                {
                    logger.debug((new StringBuilder()).append("El tamanio del array resultadoHeader es [").append(resultadoHeader.length).append("]").toString());
                }
                logger.debug((new StringBuilder()).append("resultadoHeader[0].getIdentificador() ").append(resultadoHeader[0].getIdentificador()).toString());
                logger.debug((new StringBuilder()).append("resultadoHeader[1].getIdentificador() ").append(resultadoHeader[1].getIdentificador()).toString());
                logger.debug((new StringBuilder()).append("resultadoHeader[2].getIdentificador() ").append(resultadoHeader[2].getIdentificador()).toString());
                claveToken = devuelveClaveResumptionToken();
                numInicio = numFin;
                numFin = numInicio + numMaxPaginaInt;
                tokenCacheHit = resumptionToken.generateResumptionToken(new Integer(numInicio), new Integer(numFin), claveToken);
                
            }
            if(resultadoHeader == null || resultadoHeader.length == 0)
            {
            	logger.debug((new StringBuilder()).append("No se ha podido encontrar ningun header con fechaDesde [").append(paramBusq.getDesde()).append("] y fechaHasta [").append(paramBusq.getHasta()).append("]").toString());
            	return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
					       null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
            }
            
            token = devuelveResumptionToken(tokenCacheHit, null, numElementosIndice.intValue(), false);
    	}
    	}catch(Exception e)
		{
			pintarTrazasListIdentifiers(resultadoHeader);				
			logger.error("Error recuperando el vo del doc ["+resultadoHeader+"] del servicio de indexacion con tamanho ["+resultadoHeader.length+"] - ",e);
			//Se rellena el resultadoOaiRequestVO
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
											   null, null, OAIPMHProperties.VERB_LIST_IDENTIFIERS);				
		}
    	pintarTrazasListIdentifiers(resultadoHeader);

    	listIdentifiers = mapeoHeaderVO(resultadoHeader);
    	
		if (logger.isDebugEnabled()) 
			logger.debug((new StringBuilder()).append("listIdentifiers [")
					.append(listIdentifiers).append("] con tamanho [")
					.append(listIdentifiers.length).append("]").toString());
		
        return devuelveResultadoOaiRequest(null, null, listIdentifiers, token, OAIPMHProperties.VERB_LIST_IDENTIFIERS);
    }
    

    /**
     * Obtiene el listado de los formatos de metadatos que existen en el repositorio
     * @param parametrosLlamada ParametrosOaiPmhVO: vo que contiene los parametros de busqueda      
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de listMetadataFormat
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleListMetadataFormat(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada)
        throws java.lang.Exception
    {    	
    	/**
    	 * -------------------------------------------------------------------------
    	 * ------ COMPROBAMOS SI EL REPOSITORIO ADMITE ESE IDENTIFICADOR -----------
    	 * ------------------------ PARAMETRO OPCIONAL -----------------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
    	if(parametrosLlamada.getIdentificador() != null && !parametrosLlamada.getIdentificador().equals(""))
    	{
			if (!validateIdentifier(parametrosLlamada.getIdentificador()))
			{
				if (logger.isDebugEnabled()) logger.debug("El identificador ["+parametrosLlamada.getIdentificador()+"] no esta admitido en repositorio");
//				Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ID_DOES_NOT_EXIST, OAIPMHProperties.ERR_ID_DOES_NOT_EXIST_DES, 
								                   null, null, OAIPMHProperties.VERB_LIST_METADATA_FORMATS);				
			} 
    	}
		
		/**
    	 * -------------------------------------------------------------------------
    	 * -----------OBTENEMOS LA LISTA DE FORMATOS DEL REPOSITORIO----------------
    	 * -------------------------------------------------------------------------
    	 * */ 
        try{
	        
        	//Obtenemos el numero de metatada format que existen en el repositorio
	        ListMetadataFormatVO[] listMetadataFormat = new ListMetadataFormatVO[OAIPMHProperties.devuelveNumMetadataFormat()];        	
	        if (logger.isDebugEnabled()) logger.debug("El tamanho del array lostMetadataFormat es ["+listMetadataFormat.length+"]");
	        
	        String[] metadataFormatos = OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_METADATOS).split(",");
	        String[] metadataEsquemas = OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_ESQUEMAS_METADATOS).split(",");
	        String[] metadataNamespaces = OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_NAMESPACE_METADATOS).split(",");
	        
			for (int j = 0; j < listMetadataFormat.length; j++)
	        {
	        	ListMetadataFormatVO metadataFormat = new ListMetadataFormatVO();
		        	metadataFormat.setNombreMetadato( metadataFormatos[j]);
		        	metadataFormat.setEspacioNombres(metadataEsquemas[j]);
		        	metadataFormat.setEsquema(metadataNamespaces[j]);
		        listMetadataFormat[j] = metadataFormat;
	        	
	        }
	        
	        //Trazas del listMetadataFormat
	        pintarTrazasListMetadataFormat(listMetadataFormat);
	        
	        /**
	    	 * -------------------------------------------------------------------------
	    	 * -----------INTRODUCIMOS LA INFORMACION EN EL VO DE VUELTA----------------
	    	 * -------------------------------------------------------------------------
	    	 * */ 	    	
	        return devuelveResultadoOaiRequest(null, null, 
	        		                           listMetadataFormat, null, OAIPMHProperties.VERB_LIST_METADATA_FORMATS);       	
       	
        }catch (Exception e)
        {        	
			logger.error("Error recuperando la lista de formatos del repositorio - ",e);
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
					                           null, null, OAIPMHProperties.VERB_LIST_METADATA_FORMATS);			
        }        

    }
    
    
    private boolean esSetMetadatosFederados (String set) {
    	if(set.contentEquals(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_METADATOS_FEDERADOS)))
    		return true;
    	return false;
    }
    
    
    private Set<String> obtenerIdsNodosPermitidosEnSet() {
    	
    	String idsNodosPermitidos = OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_ID_NODOS_SETS_PERMITIDOS);   
    	
    	if(idsNodosPermitidos==null || idsNodosPermitidos.isEmpty())
    		logger.warn("La lista de nodos permitidos en los sets esta vacia");
    	
    	String[] listaIdsNodosPermitidos = idsNodosPermitidos.split(",");
    	
    	Set<String> idsNodos = new HashSet<String>();

    	for(int i=0; i<listaIdsNodosPermitidos.length; i++)
    		if(!idsNodos.contains(listaIdsNodosPermitidos[i]))
    			idsNodos.add(listaIdsNodosPermitidos[i]);
    	
    	return idsNodos;
    }
	
    
	private boolean esSetValido(String set) {
		
    	if(esSetMetadatosFederados(set))
    		return true;
    	
    	//Obtenemos la lista de los ids de los nodos que pueden ser un set 
    	Set<String> idsNodosPermitidos = obtenerIdsNodosPermitidosEnSet();
    	
    	NodoVO[] listaNodos = null;
		try {
			listaNodos = this.getSrvNodoService().listarNodos();
		} catch (Exception e) {
			logger.error("Error al obtener los nodos registrados ",e);
			return false;
		}
    	for(int i=0; i<listaNodos.length; i++) {
    		if(idsNodosPermitidos.contains(listaNodos[i].getIdNodo())) {
	    		String nodo = UtilesString.removeHtmlAccents(listaNodos[i].getNodo());
	    		nodo = UtilesString.removeAccents(nodo);
	    		if(set.equalsIgnoreCase(nodo)) 
	    			return true;
    		}
    	}
    	logger.warn("El set "+set+" no coincide con el nombre de ningun nodo registrado");
    	return false;
	}
    
    
    private List<String> listarSets() {
    	
    	List<String> listaSets = new ArrayList<String>();
    	
    	//Aniadimos a la lista de sets el de metadatos federados
    	listaSets.add(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_METADATOS_FEDERADOS));
    	
    	//Obtenemos la lista de los ids de los nodos que pueden ser un set 
    	Set<String> idsNodosPermitidos = obtenerIdsNodosPermitidosEnSet();
    	
    	NodoVO[] listaNodos = null;
		try {
			listaNodos = this.getSrvNodoService().listarNodos();
		} catch (Exception e) {
			logger.error("Error al obtener los nodos registrados ",e);
			return listaSets;
		}
    	for(int i=0; i<listaNodos.length; i++) {
    		if(idsNodosPermitidos.contains(listaNodos[i].getIdNodo())) {
	    		String nodo = UtilesString.removeHtmlAccents(listaNodos[i].getNodo());
	    		nodo = UtilesString.removeAccents(nodo);
	    		listaSets.add(nodo);
    		}
    	}
    	return listaSets;
    }
    
    
    /**
     * Metodo que dado un set de una CCAA lo traduce 
     * @return
     */
    private String set2IdNodo(String set) {
    	
    	if(set.contentEquals(OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_METADATOS_FEDERADOS)))
    		return "";
    	
    	NodoVO[] listaNodos = null;
		try {
			listaNodos = this.getSrvNodoService().listarNodos();
		} catch (Exception e) {
			logger.error("Error al obtener los nodos registrados ",e);
			return null;
		}
    	for(int i=0; i<listaNodos.length; i++) {
    		String nodo = UtilesString.removeHtmlAccents(listaNodos[i].getNodo());
    		nodo = UtilesString.removeAccents(nodo);
    		if(set.equalsIgnoreCase(nodo)) 
    			return listaNodos[i].getIdNodo();
    	}
    	logger.warn("El set "+set+" no coincide con el nombre de ningun nodo registrado");
    	return null;
    }
    
    
    /**
    * Metodo que realiza la búsqueda contra el índice de ElasticSearch. 
    * En función del tipo de petición ListIdentifiers o GetRecord se devuelve una lista de identificadores o la ruta del fichero si existe en  
    * el conjunto indicado.
    * @throws Exception
    */
	private List<Source> filtrarMetadatosFederados(
			Calendar fechaDesde, 
			Calendar fechaHasta, 
			String set, 
			String identificador,
			int inicioPagina) throws Exception
	{      
		logger.info("filtrarMetadatosFederados");
		String url= this.getSrvPropiedadService().getValorPropiedad("url_es");

		String idNodo = set2IdNodo(set);
		String fechaInicio = null;
		String fechaFin = null;
		
		if(fechaDesde!=null) {
			fechaInicio = calendar2string(fechaDesde, "yyyy-MM-dd");
			logger.info("Se va a buscar en el nodo '"+idNodo+"' desde la fecha "+fechaInicio);	
		}
		
		if(fechaHasta!=null) {
			fechaFin = calendar2string(fechaHasta, "yyyy-MM-dd");
			logger.info("Se va a buscar en el nodo '"+idNodo+"' hasta la fecha "+fechaFin);
		}
		List<Source> resulBusqueda = BuscadorMetadatosFederados.buscarMetadatosFederados(
				url, 
				identificador, 
				idNodo, 
				fechaInicio, 
				fechaFin, 
				inicioPagina, 
				numMaxPaginaInt);		
				
		return resulBusqueda;      
	}
	
    /**
	* Metodo que obtiene el número total de resultados de un filtro contra el índice de ElasticSearch. 
	* @throws Exception
	*/
	private int obtenerNumTotalMetadatosFederados(
			Calendar fechaDesde, 
			Calendar fechaHasta, 
			String set, 
			String identificador) throws Exception
	{      
		logger.info("obtenerNumTotalMetadatosFederados");
		//List<String> lRetorno = new ArrayList<String>();
		String url= this.getSrvPropiedadService().getValorPropiedad("url_es");
		
		String idNodo = set2IdNodo(set);
		String fechaInicio = null;
		String fechaFin = null;
		
		if(fechaDesde!=null) {
			fechaInicio = calendar2string(fechaDesde, "yyyy-MM-dd");
			logger.info("Se va a buscar en el nodo '"+idNodo+"' desde la fecha "+fechaInicio);	
		}
		
		if(fechaHasta!=null) {
			fechaFin = calendar2string(fechaHasta, "yyyy-MM-dd");
			logger.info("Se va a buscar en el nodo '"+idNodo+"' hasta la fecha "+fechaFin);
		}
	
		int numResultadosTotales = BuscadorMetadatosFederados.obtenerTotalMetadatosFederados(
				url, 
				identificador, 
				idNodo, 
				fechaInicio, 
				fechaFin, 
				0, 1);		
		return numResultadosTotales;
	}

	
	public static String calendar2string (Calendar c, String formatoFecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		String s = sdf.format(c.getTime());
		return s;
	}
		
		
	private Date string2date (String fecha, String formatoFecha) {
		if(fecha==null || fecha.isEmpty()) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		Date d = null;
		try {
			d = sdf.parse(fecha);
		} catch (ParseException e) {
			logger.error("Hubo un problema al parsear el string '"+fecha+"' como date usando el formato '"+formatoFecha+"': "+e);
		}	
		return d;		
	}
	

	private Calendar string2calendar (String fecha, String formatoFecha) {
		Date d = string2date(fecha, formatoFecha);
		if(d==null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	

    private es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] source2OaiResultadoHeaderVO(List<Source> resulBusqueda) {
    	
    	if(resulBusqueda==null)
    		return new ResultadoHeaderVO[0];
    	
    	es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] datosResultHeaders = new ResultadoHeaderVO[resulBusqueda.size()];
    	
    	for(int i=0; i<resulBusqueda.size(); i++) {
    		ResultadoHeaderVO datosResultHeader = new ResultadoHeaderVO();
    		datosResultHeader.setFecha(resulBusqueda.get(i).getFechaPublicacion());
    		datosResultHeader.setIdentificador(resulBusqueda.get(i).getIdODE());
    		datosResultHeaders[i] = datosResultHeader;
    	}
    	
    	return datosResultHeaders;
    }
	

    private es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] source2IndexadorResultadoHeaderVO(List<Source> resulBusqueda) {
    	
    	if(resulBusqueda==null)
    		return new es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[0];
    	
    	es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] datosResultHeaders = new es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[resulBusqueda.size()];
    	
    	for(int i=0; i<resulBusqueda.size(); i++) {
    		es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO datosResultHeader = new es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO();
    		datosResultHeader.setIdentificador(resulBusqueda.get(i).getIdODE());
    		    		
    		//datosResultHeader.setFecha(string2calendar(resulBusqueda.get(i).getFechaPublicacion(), "yyyy-MM-dd'T'HH:mm:ss"));
    		if(resulBusqueda.get(i).getFechaPublicacion()!=null && !resulBusqueda.get(i).getFechaPublicacion().isEmpty()) {
    			datosResultHeader.setFecha(string2calendar(resulBusqueda.get(i).getFechaPublicacion(), "yyyy-MM-dd"));
    			logger.info("La fecha de publicacion es "+resulBusqueda.get(i).getFechaPublicacion());
    		} else {
    			datosResultHeader.setFecha(string2calendar(resulBusqueda.get(i).getFechaDespublicacion(), "yyyy-MM-dd"));
    			logger.info("La fecha de despublicacion es "+resulBusqueda.get(i).getFechaDespublicacion());
    		}
    		
    		datosResultHeaders[i] = datosResultHeader;
    	}
    	
    	return datosResultHeaders;
    }
	

    /*
    private es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO source2IndexadorResultadoRecordVO(List<Source> resulBusqueda) {
    	
    	if(resulBusqueda==null || resulBusqueda.isEmpty())
    		return null;
    	
    	es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO datosResultadoRecord = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO();
    	    	
    	datosResultadoRecord.setAmbito(null);
    	datosResultadoRecord.setAutores(null);
    	datosResultadoRecord.setContribuidor(null);
    	datosResultadoRecord.setDerechos(null);
    	datosResultadoRecord.setDescripcion(null);
    	datosResultadoRecord.setFecha(resulBusqueda.get(0).getFechaPublicacion());
    	datosResultadoRecord.setFormatos(null);
    	datosResultadoRecord.setFuente(null);
    	datosResultadoRecord.setIdentificador(resulBusqueda.get(0).getIdODE());
    	datosResultadoRecord.setIdioma(null);
    	datosResultadoRecord.setPublicador(null);
    	datosResultadoRecord.setRelacion(null);
    	datosResultadoRecord.setTema(null);
    	datosResultadoRecord.setTipo(null);
    	datosResultadoRecord.setTitulo(resulBusqueda.get(0).getTitulo());
    	
    	return datosResultadoRecord;
    }
    */


    /**
     * Obtiene el listado de los sets que existen en el repositorio. El repositorio puede aceptarlos o no. (En nuestro caso no se aceptan)
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de listSets
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleListSets()
        throws java.lang.Exception
    {    	
    	/**
    	 * -------------------------------------------------------------------------
    	 * --------------------DECLARACION DE VARIABLES-----------------------------
    	 * -------------------------------------------------------------------------
    	 * */       	
    	ResultadoOAIRequest resultadoOAIRequest = new ResultadoOAIRequest();
    	
    	/**
    	 * -------------------------------------------------------------------------
    	 * --------------- COMPROBAMOS SI EL REPOSITORIO ADMITE SET ----------------
    	 * -------------------------------------------------------------------------
    	 * */  

    	try
    	{
	    	if(!OAIPMHProperties.repositorioAdmiteSets()) {
	    		logger.debug("Sets no estan admitidos en el repositorio");		
	    		resultadoOAIRequest = devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_SET_HIERARCHY, OAIPMHProperties.ERR_NO_SET_HIERARCHY_DES, 
	    				                           null, null, OAIPMHProperties.VERB_LIST_SETS);	
		    	return resultadoOAIRequest;
	    	}
	    	
	    	List<String> listaSets = listarSets();
	    	
    		logger.debug("Sets estan admitidos en el repositorio");
    		List<SetVO> setList = new ArrayList<SetVO>();
    		
    		for(int i=0; i<listaSets.size(); i++) {
        		SetVO list = new SetVO();
        		list.setIdentificador(listaSets.get(i));
        		setList.add(list);
    		}
    		
    		resultadoOAIRequest = devuelveResultadoOaiRequest(null, null, setList.toArray(new SetVO[setList.size()]), null, OAIPMHProperties.VERB_LIST_SETS);	    	
	    	return resultadoOAIRequest;
	    	
    	} catch (Exception e) {    		
			logger.error("Error recuperando si el repositorio admite set o no - ",e);	
//			Se rellena el resultadoOaiRequestVO
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
					                           null, null, OAIPMHProperties.VERB_LIST_SETS);			
    	}
    }

    /**
     * Obtiene el listado de los records que existen en el repositorio
     * @param parametrosLlamada ParametrosOaiPmhVO: vo que contiene los parametros de busqueda      
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de listRecords
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleListRecords(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametrosLlamada)
        throws java.lang.Exception
    {    	
    	/**
    	 * -------------------------------------------------------------------------
    	 * --------------------DECLARACION DE VARIABLES-----------------------------
    	 * -------------------------------------------------------------------------
    	 * */    	           
          
        //ParamPeriodoRepositorioVO paramBusq = new ParamPeriodoRepositorioVO();
        //es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] resultadosRecordRetorno = null;
		//ArrayList resultadoRecordAux = new ArrayList();	
    	//ReanudacionTokenVO token = new ReanudacionTokenVO();
		
		
		ParamPeriodoRepositorioVO paramBusq = new ParamPeriodoRepositorioVO();
        ReanudacionTokenVO token = new ReanudacionTokenVO();
        ResumptionTokenCacheHit tokenCacheHit = new ResumptionTokenCacheHit();
        int numInicio;
        es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] resultadoRecord;
        es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] listRecords = null;
        es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] resultadosRecordRetorno = null;
        ArrayList resultadoRecordAux = new ArrayList();
        String claveToken = "";
        numInicio = 0;
        int numFin = 0;
        resultadoRecord = null;
        Element elemento = null;
        Element elementoIndice = null;
        ResumptionTokenCacheHit siguienteCacheToken = new ResumptionTokenCacheHit();
        
        /**
    	 * -------------------------------------------------------------------------
    	 * ------ COMPROBAMOS SI EL REPOSITORIO ADMITE ESE METADATAPREFIX ----------
    	 * -------------------------- PARAMETRO REQUERIDO --------------------------
    	 * -------------------------------------------------------------------------
    	 * */ 	
		if((parametrosLlamada.getCodigoPaginacion() == null)||(parametrosLlamada.getCodigoPaginacion() == ""))
		{
			if (!OAIPMHProperties.esFormatoMetadatoAdmitido(parametrosLlamada.getPrefijoMetadato()))
			{
				if (logger.isDebugEnabled()) logger.debug("El metadataPrefix ["+parametrosLlamada.getPrefijoMetadato()+"] no esta admitido en repositorio");
				//Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT, OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT_DES, 
						                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);				
			}   
		}
		
		/**
		* -------------------------------------------------------------------------
		* --------------- COMPROBAMOS SI EL REPOSITORIO ADMITE SET ----------------
		* -------------------------- PARAMETRO OPCIONAL ---------------------------
		* -------------------------------------------------------------------------
		* */
		
		// Verificamos si el SET que nos pasan es el que se ha definido con Agrega Semántico para obtener los metadatos federados 
		// Si es así lo marcamos para modificar las búsquedas posteriores
    	boolean bConsultarRepositorioMetadatosFederados=false;    	
		boolean filtrarMetadatosFederados = false;
		
		String set = parametrosLlamada.getIdentificadorConjunto();
		
	    if(set!=null && !set.isEmpty())				
		{
			if(!OAIPMHProperties.repositorioAdmiteSets()) {
	    		logger.debug("Sets no estan admitidos en el repositorio");	
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_SET_HIERARCHY, OAIPMHProperties.ERR_NO_SET_HIERARCHY_DES, 
	    									null, null, OAIPMHProperties.VERB_LIST_RECORDS);				
			}
			
	    	if(!esSetValido(parametrosLlamada.getIdentificadorConjunto())) {
	    		logger.debug("Se ha recibido un set no valido");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_SET_DES, 
	    				                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);					
	    	}

	    	/*
	    	if (esSetMetadatosFederados(set) && 
	    			!validacionFechasMetadatosFederados(parametrosLlamada.getFechaDesde(), parametrosLlamada.getFechaHasta()))
    		{
    			logger.error("Las fechas proporcionadas no son correctas");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_ARGUMENT_DES, 
                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);
    		}
    		*/
    		filtrarMetadatosFederados = true;
	    	
	    	if(esSetMetadatosFederados(set))
	    		bConsultarRepositorioMetadatosFederados=true;
		}
    	
		/**
    	 * -------------------------------------------------------------------------
    	 * -------------TRATAMIENTO DE LA FECHADESDE Y FECHAHASTA-------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
    	tratamientoFechaDesdeHasta(parametrosLlamada.getFechaDesde(), parametrosLlamada.getFechaHasta(), paramBusq);
		
		/**
    	 * -------------------------------------------------------------------------
    	 * ------------------- COMPROBAMOS EL RESUMPTION TOKEN ---------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
    	String idResumptionToken = parametrosLlamada.getCodigoPaginacion();
    	try
    	{
	    	if(idResumptionToken != null && !"".equals(idResumptionToken))
	    	{    
	    		//logger.debug("Se hace una llamada con codigo de paginacion");
	    		//Vamos a comprobar a la cache si esta el identificador 
	    		elemento = resumptionToken.devuelveElementoResumptionToken(idResumptionToken);

	    		if(elemento == null) {
	                logger.debug("El identificador del resumptionToken es incorrecto");
	                return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_RESUMPTION_TOK, OAIPMHProperties.ERR_BAD_RESUMPTION_TOK_DES, 
	                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);	    			
	    		}
	    			
    			//El identificador se encontro en la cache    			
    			//Buscamos en la cache de resumptionToken el vo de resumptioTokenCacheHit    			
    			ResumptionTokenCacheHit vueltaCacheToken = (ResumptionTokenCacheHit)elemento.getObjectValue();
    			numInicio = vueltaCacheToken.getDesde().intValue();
                numFin = vueltaCacheToken.getHasta().intValue();
                paramBusq.setRegistroFinal(vueltaCacheToken.getHasta());
                paramBusq.setRegistroInicial(vueltaCacheToken.getDesde());
                logger.debug((new StringBuilder()).append("vueltaCacheToken.getDesde() ").append(vueltaCacheToken.getDesde()).toString());
                logger.debug((new StringBuilder()).append("vueltaCacheToken.getHasta() ").append(vueltaCacheToken.getHasta()).toString());
                
                // Si es consulta contra repositorio metadatos federados se obtiene de manera diferente
                /*
	    	    if (bConsultarRepositorioMetadatosFederados) {
	    	    	Calendar cIni = Calendar.getInstance();
	    	    	Calendar cFin = Calendar.getInstance();
	    	    	
	    	    	cIni.setTimeInMillis(parametrosLlamada.getFechaDesde().getTimeInMillis());
	    	    	cFin.setTimeInMillis(parametrosLlamada.getFechaHasta().getTimeInMillis());
	    	    	resultadoRecord = obtenerMetadatosFederados(cIni,cFin,vueltaCacheToken.getDesde(),false);
	    	    	
	    	    } else*/ 
	    	    if (filtrarMetadatosFederados) {
	    	    	
	    	    	//TODO
            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
        	    			parametrosLlamada.getFechaDesde(), 
        	    			parametrosLlamada.getFechaHasta(), 
        	    			parametrosLlamada.getIdentificadorConjunto(), 
        	    			null,
        	    			vueltaCacheToken.getDesde());
        			
            		if(listaIdsOdes==null || listaIdsOdes.isEmpty()) {
        				logger.debug("No se ha podido encontrar ningun record");
        				//Se rellena el resultadoOaiRequestVO
        				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
        						                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);	
        			}
            		
            		resultadoRecord = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[listaIdsOdes.size()];
            		
            		for(int i=0; i<listaIdsOdes.size(); i++) {
            			es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO tmp = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO();
            			tmp.setTitulo(listaIdsOdes.get(i).getRuta());
            			tmp.setDescripcion(listaIdsOdes.get(i).getEstado());
            			tmp.setIdentificador(listaIdsOdes.get(i).getIdODE());
            			
            			if(listaIdsOdes.get(i).getEstado().contentEquals("PUBLICADO"))
            				tmp.setFecha(listaIdsOdes.get(i).getFechaPublicacion());
            			else 
            				tmp.setFecha(listaIdsOdes.get(i).getFechaDespublicacion());
            			
            			resultadoRecord[i] = tmp;
            		}
	    	    	
	    	    } else {
	    	    	resultadoRecord = this.getSrvBuscadorService().busquedaRepositorio(paramBusq);
	    	    }
	    	    
                elementoIndice = resumptionToken.devuelveElementoResumptionToken("numElementosIndice");
                if(vueltaCacheToken.getSiguienteResumptionToken() != null)
                {
                	logger.debug("No es necesario crear otro resumptionToken recojo el identificador.");
                    elemento = resumptionToken.devuelveElementoResumptionToken(vueltaCacheToken.getSiguienteResumptionToken());
                    siguienteCacheToken = (ResumptionTokenCacheHit)elemento.getObjectValue();
                    token = devuelveResumptionToken(siguienteCacheToken, siguienteCacheToken.getDesde(), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                }else
                {
                	logger.debug("Genero otro resumptionToken y lo guardo en la cache de resumptionToken.");
                    numInicio = numFin;
                    numFin = numInicio + numMaxPaginaInt;
                    if(numInicio < ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue())
                    {
                    	if(numFin > ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue())
                        {
                            logger.debug("Nos quedamos hasta el tamanio maximo del repositorio");
                            numFin = ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue();
                        }
                        logger.debug("Necesitamos generar otro resumptionToken");
                        claveToken = devuelveClaveResumptionToken();
                        siguienteCacheToken = resumptionToken.generateResumptionToken(Integer.valueOf(numInicio), Integer.valueOf(numFin), claveToken);
                        vueltaCacheToken.setSiguienteResumptionToken(claveToken);
                        resumptionToken.almacenarResumptionToken(vueltaCacheToken);
                        token = devuelveResumptionToken(siguienteCacheToken, Integer.valueOf(numInicio), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                    }else
                    {
                        logger.debug("Es la ultima pagina");
                        token = devuelveResumptionToken(null, vueltaCacheToken.getDesde(), ((ResumptionTokenCacheHit)elementoIndice.getObjectValue()).getDesde().intValue(), true);
                    }
                }
	    		             
	    	}else
	    	{
	    		logger.debug("Es la primera vez que se ejecuta esa query");
	    	    Integer numElementosIndice = null;
	    	   
	    	    // Si es consulta contra repositorio metadatos federados se obtiene de manera diferente
	    	    /*if (bConsultarRepositorioMetadatosFederados) {
	    	    	numElementosIndice =  obtenerMetadatosFederados(parametrosLlamada.getFechaDesde(), parametrosLlamada.getFechaHasta(),0,true).length;
	    	    } else*/ 
	    	    if (filtrarMetadatosFederados) { 
    	    	
	    	    	//TODO
	    	    	numElementosIndice = obtenerNumTotalMetadatosFederados(
	    	    			parametrosLlamada.getFechaDesde(), 
	    	    			parametrosLlamada.getFechaHasta(), 
	    	    			set, 
	    	    			null);
    	    	
	    	    } else {
	    	    	numElementosIndice = getSrvBuscadorService().obtenerTotalesRepositorioFechas(paramBusq).getDocumentosCount();
	    	    }
	    	    
	    	    logger.debug("numElementosIndice <"+numElementosIndice+">");
	    	    
	    	    if(numElementosIndice == null || numElementosIndice.intValue() == 0)
	    	    {
	    	    	logger.debug("No se ha podido encontrar ningun record con fechaDesde ["+paramBusq.getDesde()+"] y fechaHasta ["+paramBusq.getHasta()+"]");
	    	    	//Se rellena el resultadoOaiRequestVO
					return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
							                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);  
	    	    }
	    	    
				logger.debug((new StringBuilder()).append("El numero de elementos del indice ").append(numElementosIndice).toString());
				tokenCacheHit = resumptionToken.generateResumptionToken(numElementosIndice, numElementosIndice, "numElementosIndice");
				logger.debug("Se guarda en la cache un VO con el numero de elementos del indice");
				
				if(numElementosIndice.intValue() <= numMaxPaginaInt)
				{
					logger.debug("No hay paginacion");
					paramBusq.setRegistroFinal(numElementosIndice);
					paramBusq.setRegistroInicial(new Integer(0));
					logger.debug("Se llama al indexador para obtener toda la informaci\363n del indice");
					
		    	    // Si es consulta contra repositorio metadatos federados se obtiene de manera diferente
		    	    /*if (bConsultarRepositorioMetadatosFederados) {
		    	    	resultadoRecord = obtenerMetadatosFederados(parametrosLlamada.getFechaDesde(), parametrosLlamada.getFechaHasta(),0,false);
		    	    } else*/ 
		    	    if (filtrarMetadatosFederados) {
		    	    	
		    	    	//TODO
	            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
	        	    			parametrosLlamada.getFechaDesde(), 
	        	    			parametrosLlamada.getFechaHasta(), 
	        	    			parametrosLlamada.getIdentificadorConjunto(), 
	        	    			null,
	        	    			paramBusq.getRegistroInicial());
	        			
	            		if(listaIdsOdes==null || listaIdsOdes.isEmpty()) {
	        				logger.debug("No se ha podido encontrar ningun record");
	        				//Se rellena el resultadoOaiRequestVO
	        				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
	        						                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);	
	        			}
	            		
	            		resultadoRecord = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[listaIdsOdes.size()];
	            		
	            		for(int i=0; i<listaIdsOdes.size(); i++) {
	            			es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO tmp = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO();
	            			tmp.setTitulo(listaIdsOdes.get(i).getRuta());
	            			tmp.setDescripcion(listaIdsOdes.get(i).getEstado());
	            			tmp.setIdentificador(listaIdsOdes.get(i).getIdODE());
	            			
	            			if(listaIdsOdes.get(i).getEstado().contentEquals("PUBLICADO"))
	            				tmp.setFecha(listaIdsOdes.get(i).getFechaPublicacion());
	            			else 
	            				tmp.setFecha(listaIdsOdes.get(i).getFechaDespublicacion());
	            			
	            			resultadoRecord[i] = tmp;
	            		}
	            		
		    	    } else {
		    	    	resultadoRecord = getSrvBuscadorService().busquedaRepositorio(paramBusq);
		    	    }
		    	    
				} else
				{
					logger.debug("Hay paginacion");
					numFin = numMaxPaginaInt;
					if(logger.isDebugEnabled())
					logger.debug((new StringBuilder())
							.append("Llamamos al indexador con los parametros: desde [")
							.append(paramBusq.getDesde())
							.append("] y hasta [")
							.append(paramBusq.getHasta()).append("]")
							.toString());
					paramBusq.setRegistroFinal(new Integer(numFin));
					paramBusq.setRegistroInicial(new Integer(numInicio));
					
					// Si es consulta contra repositorio metadatos federados se obtiene de manera diferente
		    	    /*if (bConsultarRepositorioMetadatosFederados) {
		    	    	resultadoRecord = obtenerMetadatosFederados(parametrosLlamada.getFechaDesde(), parametrosLlamada.getFechaHasta(), 0,false);
		    	    } else*/
		    	    if (filtrarMetadatosFederados) { 	
		    	    
		    	    	//TODO
	            		List<Source> listaIdsOdes = filtrarMetadatosFederados(
	        	    			parametrosLlamada.getFechaDesde(), 
	        	    			parametrosLlamada.getFechaHasta(), 
	        	    			parametrosLlamada.getIdentificadorConjunto(), 
	        	    			null,
	        	    			paramBusq.getRegistroInicial());
	        			
	            		if(listaIdsOdes==null || listaIdsOdes.isEmpty()) {
	        				logger.debug("No se ha podido encontrar ningun record");
	        				//Se rellena el resultadoOaiRequestVO
	        				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
	        						                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);	
	        			}
	            		
	            		resultadoRecord = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[listaIdsOdes.size()];
	            		
	            		for(int i=0; i<listaIdsOdes.size(); i++) {
	            			es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO tmp = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO();
	            			tmp.setTitulo(listaIdsOdes.get(i).getRuta());
	            			tmp.setDescripcion(listaIdsOdes.get(i).getEstado());
	            			tmp.setIdentificador(listaIdsOdes.get(i).getIdODE());
	            			
	            			if(listaIdsOdes.get(i).getEstado().contentEquals("PUBLICADO"))
	            				tmp.setFecha(listaIdsOdes.get(i).getFechaPublicacion());
	            			else 
	            				tmp.setFecha(listaIdsOdes.get(i).getFechaDespublicacion());
	            			
	            			resultadoRecord[i] = tmp;
	            		}
	            		
		    	    } else { 
		    	    	resultadoRecord = getSrvBuscadorService().busquedaRepositorio(paramBusq);
		    	    }
					
					if (logger.isDebugEnabled()) {
						logger.debug((new StringBuilder())
								.append("El tamanho del array resultadoRecord es [")
								.append(resultadoRecord.length).append("]")
								.toString());	
						logger.debug((new StringBuilder())
								.append("resultadoRecord[0].getIdentificador() ")
								.append(resultadoRecord[0]
										.getIdentificador()).toString());
						logger.debug((new StringBuilder())
								.append("resultadoRecord[1].getIdentificador() ")
								.append(resultadoRecord[1]
										.getIdentificador()).toString());
						logger.debug((new StringBuilder())
								.append("resultadoRecord[2].getIdentificador() ")
								.append(resultadoRecord[2]
										.getIdentificador()).toString());
					}
					claveToken = devuelveClaveResumptionToken();
					numInicio = numFin;
					numFin = numInicio + numMaxPaginaInt;
					tokenCacheHit = resumptionToken.generateResumptionToken(new Integer(numInicio), new Integer(numFin), claveToken);
				}
				if(resultadoRecord == null || resultadoRecord.length == 0)
				{
					logger.debug("No se ha podido encontrar ningun record con fechaDesde ["+paramBusq.getDesde()+"] y fechaHasta ["+paramBusq.getHasta()+"]");
					return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
							                           null, null, OAIPMHProperties.VERB_LIST_RECORDS);    						
				}
				token = devuelveResumptionToken(tokenCacheHit, null, numElementosIndice.intValue(), false); 
	    	}
    	}
	    catch(Exception e)
        {
	    	//pintarTrazasListRecords(resultadoRecord);    					
			logger.error("Error recuperando el vo del doc ["+resultadoRecord+"] del servicio de indexacion con tamanho ["+resultadoRecord.length+"] - ",e);
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
					                           null, null, OAIPMHProperties.VERB_LIST_RECORDS); 
        }	

	    pintarTrazasListRecords(resultadoRecord);
		
	    // Si es consulta contra repositorio metadatos federados se obtiene de manera diferente
	    if (bConsultarRepositorioMetadatosFederados || filtrarMetadatosFederados){
	    	Object[] o = new Object[resultadoRecord.length]; 
	    	
	    	for(int i=0; i<resultadoRecord.length; i++) {
	    		
	    		String rutaXml = resultadoRecord[i].getTitulo();
	    		String estado = resultadoRecord[i].getDescripcion();
	    		String idOde = resultadoRecord[i].getIdentificador();	    		
				
				logger.info("La ruta del xml del ODE "+idOde+" es "+rutaXml);
				    	
				File manifest = new File(rutaXml);
				if(!manifest.exists()) 
					logger.error("El fichero "+manifest.getAbsolutePath()+" no existe");
	    		
	    		LomESDao lomDao = new LomESDao();
	    		Lom lom = lomDao.parsearLom(manifest);
	    		o[i] = lom;
				
				if(estado.contentEquals("DESPUBLICADO")) {			
					//Poner el status del lifecycle del lomAgrega al estado correcto
					LomAgrega lomAgrega = new LomAgrega(lom);
					lomAgrega.getLifeCycleAgrega().setEstatusAv("DESPUBLICADO");
					lomAgrega.getGeneralAgrega().setNivelDeAgregacion(resultadoRecord[i].getFecha());
					lom = lomAgrega.getLom();
				}		
	    	}
	    	return devuelveResultadoOaiRequest(null, null, o, token, OAIPMHProperties.VERB_LIST_RECORDS);	
	    	
    	}
	    
		if(parametrosLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
				parametrosLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE)) {
			
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			Object[] o = new Object[resultadoRecord.length];  
							
			for(int i=0; i<resultadoRecord.length; i++) {
				String idMEC = resultadoRecord[i].getIdentificador();
				String pathODE = localizadorService.consultaLocalizador(idMEC).getPath();
				Manifest manifest = this.parsearManifiesto(pathODE + File.separator + "imsmanifest.xml");						
				ManifestAgrega manAgrega = new ManifestAgrega(manifest);
				String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
								
				//En caso de ser Lom IEEE pongo a null los campos que deben omitirse
				if(parametrosLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
					lom = lomes2LomIEEE(lom);
				
				o[i] = lom;
			}
			return devuelveResultadoOaiRequest(null, null, o, token, OAIPMHProperties.VERB_LIST_RECORDS);	
		}
	    
		/**
    	 * -----------------------------------------------------------------------------------
    	 * - REALIZAMOS EL MAPEO DE RESULTADORECORDVO (INDEXADOR) A RESULTADORECORD VO (OAI)--
    	 * -----------------------------------------------------------------------------------
    	 * */
		listRecords = mapeoListRecordVO(resultadoRecord);	
		if (logger.isDebugEnabled()) logger.debug("listRecords ["+listRecords+"] con tamanho ["+listRecords.length+"]");
    	
		return devuelveResultadoOaiRequest(null, null, 
              listRecords, token, OAIPMHProperties.VERB_LIST_RECORDS);
    }

    			
    /*
     * Metodo que modifica los campos de de un objeto lom con datos LOM-ES
     * a un objeto lom con datos LOM-IEEE (o LOM)
     */
    private Lom lomes2LomIEEE (Lom lom) throws Exception {

		//Lom-ES tiene varios campos mas que Lom-IEEE; proceso cognitivo y acceso.
    	//Estos campos extra de LOM-ES los ponemos a null para que no aparezcan en el XML.
		LomAgrega lomAgrega = new LomAgrega(lom);
		for (int i=0; i<lomAgrega.getEducationalsAgrega().size();i++) 
			lomAgrega.getEducationalAgrega(i).setProcesosCognitivosAv(null);
		lom = lomAgrega.getLom();
		lom.getRights().getGroupRightsRights().setAccess(null);
		
		//Ponemos a null otros campos que no tienen traduccion directa a LOM ya 
		//que LOM es mas restrictivo en los valores que pueden tomar algunos campos
		for (int i=0; i<lom.getTechnical().getGroupTechnicalTechnical().getRequirement().length; i++)
			lom.getTechnical().getGroupTechnicalTechnical().getRequirement(i).setGroupRequirementRequirement(null);	

		for (int i=0; i<lom.getEducational().length; i++) {
			if(lom.getEducational(i).getGroupEducationalEducational()!=null) {

				for (int j=0; j<lom.getEducational(i).getGroupEducationalEducational().getLearningResourceType().length; j++) 
					lom.getEducational(i).getGroupEducationalEducational().setLearningResourceType(j, null);

				for (int j=0; j<lom.getEducational(i).getGroupEducationalEducational().getIntendedEndUserRole().length; j++) 
					lom.getEducational(i).getGroupEducationalEducational().setIntendedEndUserRole(j, null);		

				for (int j=0; j<lom.getEducational(i).getGroupEducationalEducational().getContext().length; j++) 
					lom.getEducational(i).getGroupEducationalEducational().setContext(j, null);				
			}
		}

		CopyrightAndOtherRestrictions c = lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions();
		String licencia = c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getContent();
		
		//Traducimos la licencia al diminio de lom
		if(licencia.equalsIgnoreCase("public domain") || licencia.equalsIgnoreCase("not appropriate")) {
			c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().setContent("no");
		} else {
			c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().setContent("yes");
		}
		
		//TODO
		/*
		 * Cambiar loca campos *VocabSource().getContent() donde se esta obteniendo LOM-ESv1.0 por LOMv1.0
		 * Ver codigo de abajo:		 
		
		String s = null;
		CopyrightAndOtherRestrictions c = lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions();
		s = c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabSource().getUniqueElementName();
		logger.error("LUIS source unique :" +s);
		s = c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabSource().getContent();
		logger.error("LUIS source content :" +s);
		s = c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getUniqueElementName();
		logger.error("LUIS value unique :" +s);
		s = c.getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getContent();
		logger.error("LUIS value content :" +s);
		
		SALIDA QUE GENERA EL CODIGO DE ARRIBA
		 
		2013-11-18 18:59:27,260 ERROR [es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceImpl] (ajp-0.0.0.0-8009-1) LUIS source unique :source
		2013-11-18 18:59:27,260 ERROR [es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceImpl] (ajp-0.0.0.0-8009-1) LUIS source content :LOM-ESv1.0
		2013-11-18 18:59:27,260 ERROR [es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceImpl] (ajp-0.0.0.0-8009-1) LUIS value unique :value
		2013-11-18 18:59:27,261 ERROR [es.pode.oaipmh.negocio.servicios.SrvOaiPmhServiceImpl] (ajp-0.0.0.0-8009-1) LUIS value content :creative commons: attribution - non commercial - share alike
		 */

		return lom;
    }
    
    			
    /**
     * Obtiene información de un registro del repositorio  
     * @param parametroLlamada ParametrosOaiPmhVO: vo que contiene los parametros de busqueda   
     * @return ResultadoOaiRequestVo: vo de retorno que es este caso contendra relleno el vo de getRecord
     * @throws Exception
     * 
     */
    protected es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest handleGetRecord(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada)
        throws java.lang.Exception
    {    	        
        /**
    	 * -------------------------------------------------------------------------
    	 * ------ COMPROBAMOS SI EL REPOSITORIO ADMITE ESE METADATAPREFIX ----------
    	 * ---------------------- PARAMETRO REQUERIDO ------------------------------
    	 * -------------------------------------------------------------------------
    	 * */ 
		if (!OAIPMHProperties.esFormatoMetadatoAdmitido(parametroLlamada.getPrefijoMetadato()))
		{
			if (logger.isDebugEnabled()) logger.debug("El metadataPrefix ["+parametroLlamada.getPrefijoMetadato()+"] no esta admitido en repositorio");
			//Se rellena el resultadoOaiRequestVO
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT, OAIPMHProperties.ERR_CAN_NOT_DISEMINATE_FORMAT_DES, 
					                           null, null, OAIPMHProperties.VERB_GET_RECORD);			
		}   
		
		/**
		* -------------------------------------------------------------------------
		* --------------- COMPROBAMOS SI EL REPOSITORIO ADMITE SET ----------------
		* -------------------------- PARAMETRO OPCIONAL ---------------------------
		* -------------------------------------------------------------------------
		* */		
		String set = parametroLlamada.getIdentificadorConjunto();
		boolean filtrarMetadatosFederados = false;
		
		if(set!=null && !set.isEmpty())				
		{			
			if(!OAIPMHProperties.repositorioAdmiteSets()) {
	    		logger.debug("Sets no estan admitidos en el repositorio");	
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_SET_HIERARCHY, OAIPMHProperties.ERR_NO_SET_HIERARCHY_DES, 
	    									null, null, OAIPMHProperties.VERB_GET_RECORD);				
			}
			
	    	if(!esSetValido(set)) {
	    		logger.debug("Se ha recibido un set no valido");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_SET_DES, 
	    				                           null, null, OAIPMHProperties.VERB_GET_RECORD);					
	    	}
	    	
    		// Se trata de una consulta a metadatos federeados. Debe filtrarse por fechas y un máximo de un mes
    		/*
	    	if (esSetMetadatosFederados(set) && 
    				!validacionFechasMetadatosFederados(parametroLlamada.getFechaDesde(), parametroLlamada.getFechaHasta()))
    		{
    			logger.error("Las fechas proporcionadas no son correctas");
	    		return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_BAD_ARGUMENT, OAIPMHProperties.ERR_BAD_ARGUMENT_DES, 
                           null, null, OAIPMHProperties.VERB_GET_RECORD);
    		}
	    	*/
    		filtrarMetadatosFederados = true;
    	}    	
        		
		/**
		* -------------------------------------------------------------------------
		* ------ COMPROBAMOS SI EL REPOSITORIO ADMITE ESE IDENTIFICADOR -----------
		* ----------------------- PARAMETRO REQUERIDO -----------------------------
		* -------------------------------------------------------------------------
		* */ 
		String idMEC = "";
		
		if (!validateIdentifier(parametroLlamada.getIdentificador()))
		{
			if (logger.isDebugEnabled()) logger.debug("El identificador ["+parametroLlamada.getIdentificador()+"] no esta admitido en repositorio");
			//Se rellena el resultadoOaiRequestVO
			return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ID_DOES_NOT_EXIST, OAIPMHProperties.ERR_ID_DOES_NOT_EXIST_DES, 
					                           null, null, OAIPMHProperties.VERB_GET_RECORD);			
		}   

		idMEC = devuelveidMec(parametroLlamada.getIdentificador());
				
		/**
    	 * -------------------------------------------------------------------------
    	 * --------SE LLAMA AL INDEXADOR PARA OBTENER EL VO DE LOS RECORDS----------
    	 * -------------------------------------------------------------------------
    	 * */		
		es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO recordVO = null;		 
				
		if (filtrarMetadatosFederados) {

			List<Source> listaIdsOdes = filtrarMetadatosFederados(
					null, 
					null, 
					set, 
					idMEC,
					0);
			
			if(listaIdsOdes==null || listaIdsOdes.isEmpty()) {
				logger.debug("No se ha podido encontrar ningun record");
				//Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
						                           null, null, OAIPMHProperties.VERB_GET_RECORD);	
			}
			
			//Cogemos el primer ODE de la lista ya que sera el ultimo estado del ODE buscado
			String rutaXml = listaIdsOdes.get(0).getRuta();
			
			boolean estaDespublicado = false;
			
			//Comprobamos si es un ODE borrado 
			if(listaIdsOdes.get(0).getEstado().contentEquals("DESPUBLICADO")) {
				
				estaDespublicado = true;
				
				//Buscamos en los resultados el registro de publicacion para coger la ruta del lomes
				for(int i=0; i<listaIdsOdes.size(); i++) 
					if(listaIdsOdes.get(i).getEstado().contentEquals("PUBLICADO"))					
						rutaXml = listaIdsOdes.get(i).getRuta();
			}
			logger.info("Esta despublicado? "+estaDespublicado);
			
			if(rutaXml==null || rutaXml.isEmpty()) 
				logger.error("No se ha podido obtener la ruta del ode "+idMEC);
				
			logger.info("La ruta del xml del ODE es "+rutaXml);
			    	
			File manifest = new File(rutaXml);
			if(!manifest.exists()) 
				logger.error("El fichero "+manifest.getAbsolutePath()+" no existe");
			
			LomESDao lomDao = new LomESDao();
    		Lom lom = lomDao.parsearLom(manifest);
			
			if(estaDespublicado) {			
				//Poner el status del lifecycle del lomAgrega al estado correcto
				LomAgrega lomAgrega = new LomAgrega(lom);
				lomAgrega.getLifeCycleAgrega().setEstatusAv("DESPUBLICADO");
				lomAgrega.getGeneralAgrega().setNivelDeAgregacion(listaIdsOdes.get(0).getFechaDespublicacion());
				lom = lomAgrega.getLom();
			}			

			if(parametroLlamada.getPrefijoMetadato()!=null && 
					parametroLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE)) 
				lom = lomes2LomIEEE(lom);
						
			return devuelveResultadoOaiRequest(null, null, lom, null, OAIPMHProperties.VERB_GET_RECORD);
			
		} else {
		
			logger.debug("Antes de llamar al indexador");
			try
			{
				logger.debug("Se llama al servicio de indexacion para obtener el vo del record con identificador ["+parametroLlamada.getIdentificador()+"]");
				recordVO = this.getSrvBuscadorService().busquedaMECRepositorio(idMEC);
			}
			catch (Exception e)
			{
				//Pintamos los valores del record        		
				pintarTrazasGetRecord(recordVO);			
				logger.error("Error recuperando el vo del servicio de indexacion",e);
				//Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_ERROR_GENERICO, OAIPMHProperties.ERR_ERROR_GENERICO_DES, 
						                           null, null, OAIPMHProperties.VERB_GET_RECORD);			
			}
			
			//Se comprueba que la vuelta del indexador es correcta
			if(recordVO == null)
			{
				logger.debug("No se ha podido encontrar ningun record");
				//Se rellena el resultadoOaiRequestVO
				return devuelveResultadoOaiRequest(OAIPMHProperties.ERR_NO_RECORDS_MATCH, OAIPMHProperties.ERR_NO_RECORDS_MATCH_DES, 
						                           null, null, OAIPMHProperties.VERB_GET_RECORD);				
			}
		    	
			//Pintamos los valores del recordVO
			pintarTrazasGetRecord(recordVO);
			
			/**
	    	 * -----------------------------------------------------------------------------------
	    	 * ---------- REALIZAMOS EL MAPEO DE RECORDVO (INDEXADOR) A RECORDVO (OAI)------------
	    	 * -----------------------------------------------------------------------------------
	    	 * */	
			es.pode.oaipmh.negocio.servicios.ResultadoRecordVO recordVOOai = null;
			
			if(parametroLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
					parametroLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE)) {
				
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				String pathODE = localizadorService.consultaLocalizador(idMEC).getPath();
				Manifest manifest = this.parsearManifiesto(pathODE + File.separator + "imsmanifest.xml");						
				ManifestAgrega manAgrega = new ManifestAgrega(manifest);
				String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);		
	
				if(parametroLlamada.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE)) 
					lom = lomes2LomIEEE(lom);
							
				return devuelveResultadoOaiRequest(null, null, lom, null, OAIPMHProperties.VERB_GET_RECORD);
			}
			
			recordVOOai  = mapeoRecordVO(recordVO);	
			return devuelveResultadoOaiRequest(null, null, recordVOOai, null, OAIPMHProperties.VERB_GET_RECORD);
		}
    }
    
    
	private Manifest parsearManifiesto(String localizador) throws Exception {
		Manifest manifest = null;	
		//manifest = this.getScormDao().parsearODELazy(new File(localizador));
		SCORM2004Dao s = new SCORM2004Dao();
		File f = new File(localizador);
		if(!f.exists()) {
			logger.error("El fichero "+localizador+" NO existe");
			return null;
		}
		logger.error("El fichero "+localizador+" SI existe");
		try {
			manifest = s.parsearODELazy(f);
		} catch (ParseadorException e) {
			logger.error("No se puede parsear el fichero; localizador: "+localizador);
			throw e;
		}
		return manifest;
	}
	
    
    /**
     * Metodo que devuelve el vo de reanudaciontoken en los metodos listIdentifiers y listRecords
     */  
    private ReanudacionTokenVO devuelveReanudacionToken(ResumptionTokenCacheHit tokenCacheHitNext, String clave, Integer desdeToken, int numResultados, boolean encontradoCache) throws Exception
    {
    	ReanudacionTokenVO token = new ReanudacionTokenVO();
    	if (encontradoCache)
    	{
    		logger.debug("Se encontro el elemento en la cache de resultados");
    		//Se encontro el elemento en la cache
    		if(tokenCacheHitNext == null)
			{
    			logger.debug("La pagina entregada es la ultima. No hay nuevo resumptionToken");
				//No hay nuevo resumption token la pagina entregada es la ultima
				token.setCursor(desdeToken);
				token.setTamanio(new Integer(numResultados));
			}
			else
			{
				//Hay nuevo resumption token
//				Se rellena el vo de reanudacionToken 
				logger.debug("Hay nuevo resumptionToken");
				token.setCursor(desdeToken);
				token.setFechaExpiracion(tokenCacheHitNext.getFechaExpiracion());
				token.setIdentificador(tokenCacheHitNext.getIdResumptionToken());
				token.setTamanio(new Integer(numResultados));
			}    	
    	}
    	else
    	{			
			// Se calcula el numero de paginas
			int numPaginas = (numResultados/numMaxPaginaInt)+1;				
			if (logger.isDebugEnabled()) logger.debug("El numero de paginas es  ["+numPaginas+"]");
			
			//Si existe mas de una pagina se genera la clave para el resumption token y se guarda en la cache de resumption token.
			if(numPaginas > 1)
			{
				//Se genera el vo del resumptionToken y se introduce en la cache de resumption token
				logger.debug("Hay mas de una pagina. Se genera el resumptionToken");
				ResumptionTokenCacheHit tokenCacheHit = resumptionToken.generateFirstResumptionToken(clave, OAIPMHProperties.VERB_LIST_RECORDS, new Integer(numResultados));
				//Se rellena el vo de reanudacionToken 
				token.setCursor(new Integer(0));
				token.setFechaExpiracion(tokenCacheHit.getFechaExpiracion());
				token.setIdentificador(tokenCacheHit.getIdResumptionToken());
				token.setTamanio(new Integer(numResultados));
			}
			else
			{
				//Solo hay una pagina. El vo de reanudacionToken se rellena con null
				logger.debug("Solo hay una pagina. No hay resumptionToken");
				token.setCursor(new Integer(0));
				token.setTamanio(new Integer(numResultados));
			}
    	}
    	return token;
    }
    
    
	/**
	 * Nos devuelve el idOAI del id-MEC
	 */  
    private String devuelveidOai(String idMEC) throws Exception
    {
//    	El identificador debe cumplir el siguiente formato: oai-identifier = scheme ":" namespace-identifier ":" local-identifier
    	String idOAI = "";
    	if(idMEC != null && !idMEC.equals(""))
    		idOAI = OAIPMHProperties.eschemaIdentifier() + this.DOS_PUNTOS + OAIPMHProperties.namespaceIdentifier() + this.DOS_PUNTOS +  idMEC;
    	return idOAI;
    }
    
    
    /**
     * Nos devuelve el idMec del identificador-OAIPMH
     * @throws Exception
     */
    private String devuelveidMec (String identifierOai) throws Exception
    {	    	
    	String[] identifierOaiSplit = identifierOai.split(this.DOS_PUNTOS);
    	return identifierOaiSplit[2];
    }
    
    
    /**
     * Valida si el identificador cumple el patron establecido
     * @throws Exception
     */
	private boolean validateIdentifier(String identifier) throws Exception 
	{
		boolean valid = false;		
		//El identificador debe cumplir el siguiente formato: oai-identifier = scheme ":" namespace-identifier ":" local-identifier
		if(identifier != null && !identifier.equals(""))
		{
			String[] identifierSplit = identifier.split(this.DOS_PUNTOS);			
			if(identifierSplit != null && identifierSplit.length > 0)
			{
				/*
		    	 * -------------------------------------------------------------------------
		    	 * Se comprueba 4 cosas:
		    	 * 	1. El tamaño del array sea 3
		    	 * 	2. La posicion primera cumple el patron de scheme
		    	 * 	3. La posicion segunda cumpla el patron de namespace-identifier
		    	 *  4. La posicion tercera cumpla el patron del mec
		    	 * -------------------------------------------------------------------------
		    	 * */
				
				ValidadorMEC validaMec = new ValidadorMEC();
				if(identifierSplit.length == 3 && OAIPMHProperties.eschemaIdentifier().equals(identifierSplit[0]) && OAIPMHProperties.namespaceIdentifier().equals(identifierSplit[1]) && validaMec.validar(identifierSplit[2]))				
					valid = true;				
			}
		}		
		return valid;
	}
  
	/**
	 * Metodo que mapeo un vo de tipo indexador a uno de tipo oaiPmh
	 * @throws Exception
	 */
    private es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] mapeoHeaderVO(es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] headerIndexador) throws Exception
    {
    	if (headerIndexador != null && headerIndexador.length > 0){
	    	es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[] headerOaiPmh = new es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[headerIndexador.length]; 
	    	for (int i = 0; headerIndexador != null && i < headerIndexador.length; i++)
	    	{	    	
	    		es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO headerOaiPmhAux = new es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO();
	    		if(!(headerIndexador[i] == null))
	    		{
	    			headerOaiPmhAux.setFecha(devuelveFechaStringFromCalendar(headerIndexador[i].getFecha()));
		    		headerOaiPmhAux.setIdentificador(devuelveidOai(headerIndexador[i].getIdentificador()));
	    		}
	    		headerOaiPmh[i] = headerOaiPmhAux;	    		
	    	}
	    	return headerOaiPmh;
    	}
    	else
    	{
    		return new es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO[]{};
    	}    	
    }
    
    
    /**
     * Devuelve la fecha como string de un calendar
     * @throws Exception
     */
    private String devuelveFechaStringFromCalendar(Calendar calendar) throws Exception
    {
    	String fechaSt = "";

    	if(calendar != null && !"".equals(calendar))
    	{
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	fechaSt = simpleDateFormat.format(calendar.getTime());
    	}
    	
    	return fechaSt;
    }
    
    
    /**
     * mapear de un VO a otro
     * @param recordIndexador
     * @return mapeoRecordVO
     * @throws Exception
     */
    private es.pode.oaipmh.negocio.servicios.ResultadoRecordVO mapeoRecordVO (es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO recordIndexador) throws Exception
    {
    	es.pode.oaipmh.negocio.servicios.ResultadoRecordVO recordOaiPmh = new es.pode.oaipmh.negocio.servicios.ResultadoRecordVO();
    	
		recordOaiPmh.setAmbito(recordIndexador.getAmbito());
		recordOaiPmh.setAutores(recordIndexador.getAutores());
		recordOaiPmh.setContribuidor(recordIndexador.getContribuidor());
		recordOaiPmh.setDerechos(recordIndexador.getDerechos());
		recordOaiPmh.setDescripcion(recordIndexador.getDescripcion()); 		
		recordOaiPmh.setFecha(devuelveFechaString(recordIndexador.getFecha()));		
		recordOaiPmh.setFormatos(recordIndexador.getFormatos());
		recordOaiPmh.setFuente(recordIndexador.getFuente());
		/** Identificadores 
		 * 1. En la primera posicion contiene el mec
		 * 2. En la segunda posicion contiene la url de la ficha del ode
		 * */
		String[] identificadores = new String[2];
			identificadores[0] = recordIndexador.getIdentificador();
			identificadores[1] = devuelveUrlFicha(recordIndexador);	
		recordOaiPmh.setIdentificador(identificadores);
		recordOaiPmh.setIdRepositorio(devuelveidOai(recordIndexador.getIdentificador()));
		recordOaiPmh.setIdioma(recordIndexador.getIdioma());
		recordOaiPmh.setPublicador(recordIndexador.getPublicador());
		recordOaiPmh.setRelacion(recordIndexador.getRelacion());
		recordOaiPmh.setTema(recordIndexador.getTema());
		recordOaiPmh.setTipo(recordIndexador.getTipo());
		recordOaiPmh.setTitulo(recordIndexador.getTitulo());
		
		return recordOaiPmh;
    }
    
    
    /**
     * Metodo que devuelve la fecha como string en formato valido para OAI-PMH
     * @throws Exception
     */
    private String devuelveFechaString (String var) throws Exception {
    	return(var.substring(0,4) + this.GUION + var.substring(4,6) + this.GUION + var.substring(6,8));    	
    }
    
    
    /**
     * Metodo que devuelve la url de la ficha del Ode
     * @throws Exception
     */
    private String devuelveUrlFicha(es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO recordIndexador) throws Exception
    {
    	//La url sera del tipo: http://desarrollo.agrega.indra.es/buscador/BuscarAvanzadoCU/MostrarResultadosAvanzadoPrepararRetornoDetalle.do?idioma=es&identificadorODE=es_20080429_2_9121621    	
    	String hostNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);   	 	
    	String url = props.getProperty("protocoloHttp") + hostNodo + props.getProperty(BUSCADOR_FICHA) +  recordIndexador.getIdioma() + props.getProperty(BARRA) + recordIndexador.getIdentificador();
    	return url;    	
    }
    
    
    /**
     * Metodo que mapea un vo de tipo indexador a uno de tipo oaiPmh
     * @throws Exception
     */
    private es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] mapeoListRecordVO(es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] recordIndexador) throws Exception
    {
    	if (recordIndexador != null && recordIndexador.length > 0){
	    	es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[] recordOaiPmhArray = new es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[recordIndexador.length]; 
	    	for (int i = 0; recordIndexador != null && i < recordIndexador.length; i++)
	    	{ 
	    		es.pode.oaipmh.negocio.servicios.ResultadoRecordVO recordOaiPmh = mapeoRecordVO(recordIndexador[i]);
	    		recordOaiPmhArray[i] = recordOaiPmh;
	    	}
	    	return recordOaiPmhArray;
    	}
    	else
    	{
    		return new es.pode.oaipmh.negocio.servicios.ResultadoRecordVO[]{};
    	}
    	
    }
    
    
	 /**
	  * Metodo para pintar los valores devueltos por el indexador del headerVO
	  * @param headerVO
	  * @throws Exception
	  */
    private void pintarTrazasListIdentifiers (es.pode.indexador.negocio.servicios.busqueda.ResultadoHeaderVO[] headerVO) throws Exception
    {
    	if(headerVO == null) {
    		logger.warn("El array devuelto por el indexador HeaderVO es nulo");
    		return;
    	}
		if(headerVO.length == 0) {
			logger.debug("El tamanho del array headerVO devuelto por el indexador es cero");
    		return;
		}
		//Pintamos los valores de el/los headers/s    			
		for (int i = 0; i < headerVO.length; i++)
			if (logger.isDebugEnabled()) logger.debug("Headernumero ["+i+"]");
    }
    
    
    /**
     * Metodo para pintar la lista de los formatos de metadatos que hay en el repositorio
     * @param listMetadataFormat
     * @throws Exception
     */
    private void pintarTrazasListMetadataFormat (ListMetadataFormatVO[] listMetadataFormat) throws Exception
    {
    	if(listMetadataFormat == null) {
    		logger.error("El array listMetadataFormatVO es nulo");
    		return;
    	}
		if(listMetadataFormat.length == 0) {
			logger.debug("El tamanho del array listMetadataFormat devuelto es cero");
			return;
		}
		// Pintamos los valores de el/los formatos del metadata  			
		for (int i = 0; i < listMetadataFormat.length; i++) {
			if (logger.isDebugEnabled()) logger.debug("Formato de metadato numero ["+i+"]");
			if (logger.isDebugEnabled()) logger.debug("El valor del espacioNombres en el formato de metadato numero ["+i+"] es ["+listMetadataFormat[i].getEspacioNombres()+"]");
			if (logger.isDebugEnabled()) logger.debug("El valor del esquema en el formato de metadato numero ["+i+"] es ["+listMetadataFormat[i].getEsquema()+"]");
			if (logger.isDebugEnabled()) logger.debug("El valor del nombre del metadato en el formato de metadato numero ["+i+"] es ["+listMetadataFormat[i].getNombreMetadato()+"]");        				        				        			
		}
    }
    
    
    private void pintarTrazasGetRecord (es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO recordVO) throws Exception
    {
		if (recordVO != null) {
			logger.warn("El vo esta vacio");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("El valor de el/los ambitos en el record  es ["+recordVO.getAmbito()+"]");
			logger.debug("El valor de el/los autores en el record es ["+recordVO.getAutores()+"]");
			logger.debug("El valor de el/los derechos en el record es ["+recordVO.getDerechos()+"]");
			logger.debug("El valor de la/las descripciones en el record es ["+recordVO.getDescripcion()+"]");
			logger.debug("El valor de la fecha en el record es ["+recordVO.getFecha()!=null?recordVO.getFecha().toString():""+"]");
			logger.debug("El valor de el/los formatos en el record es ["+recordVO.getFormatos()+"]");
			logger.debug("El valor del identificador en el record es ["+recordVO.getIdentificador()+"]");
			logger.debug("El valor del idioma en el record es ["+recordVO.getIdioma()+"]");
			logger.debug("El valor de el/los temas en el record es ["+recordVO.getTema()+"]");
			logger.debug("El valor de el/los tipos en el record es ["+recordVO.getTipo()+"]");
			logger.debug("El valor del titulo en el record es ["+recordVO.getTitulo()+"]");
			logger.debug("El valor de la/las relaciones en el record es ["+recordVO.getRelacion()+"]");
			logger.debug("El valor de el/los publicadores en el record es ["+recordVO.getPublicador()+"]");	
			logger.debug("El valor de la/las fuentes en el record es ["+recordVO.getFuente()+"]");
			logger.debug("El valor de el/los contribuidores en el record es ["+recordVO.getContribuidor()+"]");	        			
		}
	}
    
    /**
     * Metodo para pintar los valores devueltos por el indexador del recordVO
     * @param recordVO
     * @throws Exception
     */
    private void pintarTrazasListRecords (es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] recordVO) throws Exception
    {
    	if(recordVO == null) {
    		logger.warn("El array devuelto por el indexador RecordVO es nulo");
			return;
    	}
		if(recordVO.length == 0) {
			logger.debug("El tamanho del array recordVO devuelto por el indexador es cero");
			return;
		}
		//Pintamos los valores de el/los record/s    			
		for (int i = 0; i < recordVO.length; i++) {
			if (logger.isDebugEnabled()) logger.debug("Record numero ["+i+"]");
			//pintarTrazasGetRecord(recordVO[i]);        				        			
		}
    }
    
    
    /**
     * Genera la clave a partir de la fechaDesde y fechaHasta
     * @param parametroLlamada
     * @return String clave
     * @throws Exception
     */
    private String generaClaveDatos(es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO parametroLlamada) throws Exception
    {
    	//Genero la clave a partir de la fechaDesde y fechaHasta
    	String desde, hasta, clave = "";
    	if(parametroLlamada.getFechaDesde() == null || ("").equals(parametroLlamada.getFechaDesde()))	desde = this.FROM;
    	else desde = parametroLlamada.getFechaDesde().toString();
    	
    	if(parametroLlamada.getFechaHasta() == null || ("").equals(parametroLlamada.getFechaHasta()))	hasta = this.TO;
    	else hasta = parametroLlamada.getFechaHasta().toString();
    	
    	clave = desde + hasta;
    	if (logger.isDebugEnabled()) logger.debug("El valor de la clave creada es ["+clave+"]");
    	
    	return clave;
    } 
    
    
	/**
	 * Metodo que realiza el tratamiento de la fechaDesde y fechaHasta. En el caso de que venga relleno, 
	 * se realiza la transformacion a Calendar y se introduce en el vo de parametros de busqueda
	 * @throws Exception
	 */
    private void tratamientoFechaDesdeHasta(Calendar fechaDesde, Calendar fechaHasta, ParamPeriodoRepositorioVO paramBusq) throws Exception
    {    	
//    	Fecha Desde
    	if(fechaDesde != null)
		{
    		Calendar fechaDesdeCalendar = Calendar.getInstance();
    		fechaDesdeCalendar.setTime(fechaDesde.getTime());
    		paramBusq.setDesde(fechaDesdeCalendar);
    		if (logger.isDebugEnabled()) logger.debug("El valor de la fecha desde es ["+paramBusq.getDesde()+"]");
	
		}     	
//    	Fecha hasta
    	if(fechaHasta !=null)
    	{
			Calendar fechaHastaCalendar = Calendar.getInstance();
			fechaHastaCalendar.setTime(fechaHasta.getTime());
			paramBusq.setHasta(fechaHastaCalendar);
			if (logger.isDebugEnabled()) logger.debug("El valor de la fecha hasta es ["+paramBusq.getHasta()+"]");
    	}
    	
    }    
        
    
    /**
     * Devuelve  el vo de resultadoOaiRequest
     * @throws Exception
     */
    private ResultadoOAIRequest devuelveResultadoOaiRequest(String errorCode, String errorDescripcion, Object obj, ReanudacionTokenVO reanudacionToken, String verb) throws Exception
    {
    	ResultadoOAIRequest resultadoOAIRequest = new ResultadoOAIRequest();
		//Se comprueba cual es el verbo
		if (OAIPMHProperties.VERB_GET_RECORD.equals(verb)) {
			if (obj instanceof ResultadoRecordVO)
				resultadoOAIRequest.setGetRecord((ResultadoRecordVO)obj);
			else
				resultadoOAIRequest.setGetRecordLomes(obj);
			
		} else if (OAIPMHProperties.VERB_LIST_RECORDS.equals(verb))	{
			if (obj instanceof ResultadoRecordVO[])
				resultadoOAIRequest.setListRecords((ResultadoRecordVO[])obj);
			else
				resultadoOAIRequest.setListRecordsLomes((Object[])obj);
			
		} else if (OAIPMHProperties.VERB_IDENTIFY.equals(verb))	resultadoOAIRequest.setIdentifyVO((IdentifyVO)obj);    		
		else if (OAIPMHProperties.VERB_LIST_IDENTIFIERS.equals(verb)) resultadoOAIRequest.setListIdentifiers((ResultadoHeaderVO[])obj);    		
		else if (OAIPMHProperties.VERB_LIST_METADATA_FORMATS.equals(verb)) resultadoOAIRequest.setListMetadataFormat((ListMetadataFormatVO[])obj);    		   		
		else if (OAIPMHProperties.VERB_LIST_SETS.equals(verb)) resultadoOAIRequest.setListSets((SetVO[])obj);
		
		//Se rellena el errorcode, errorDescripcion, reanudacionToken y verb
		resultadoOAIRequest.setErrorCode(errorCode);
		resultadoOAIRequest.setErrorDescripcion(errorDescripcion);
		resultadoOAIRequest.setReanudacionToken(reanudacionToken);
		resultadoOAIRequest.setVerb(verb);     	
	
		return resultadoOAIRequest;
    }

    
    /** Para saber si el servicio está operativo
	 * 
	 * @return Boolean
	 * @throws Exception
	 * */
	protected Boolean handleEstasActivo() throws Exception {
		return new Boolean(true);
	} 
	
	
	 private ReanudacionTokenVO devuelveResumptionToken(ResumptionTokenCacheHit tokenCacheHitNext, Integer desdeToken, int numResultados, boolean encontradoCache)
     throws Exception
 {
     ReanudacionTokenVO token = new ReanudacionTokenVO();
     if(encontradoCache)
     {
         logger.debug("Se encontro el elemento en la cache de resultados");
         if(tokenCacheHitNext == null)
         {
             logger.debug("La pagina entregada es la ultima. No hay nuevo resumptionToken");
             token.setCursor(desdeToken);
             token.setTamanio(new Integer(numResultados));
         } else
         {
             logger.debug("Hay nuevo resumptionToken");
             token.setCursor(Integer.valueOf(desdeToken.intValue() - numMaxPaginaInt));
             token.setFechaExpiracion(tokenCacheHitNext.getFechaExpiracion());
             token.setIdentificador(tokenCacheHitNext.getIdResumptionToken());
             token.setTamanio(new Integer(numResultados));
         }
     } else
     {
         int numPaginas = numResultados / numMaxPaginaInt + 1;
         if(logger.isDebugEnabled())
         {
             logger.debug((new StringBuilder()).append("El numero de paginas es  [").append(numPaginas).append("]").toString());
         }
         if(numPaginas > 1)
         {
             logger.debug("Hay mas de una pagina. Se genera el resumptionToken");
             token.setCursor(new Integer(0));
             token.setFechaExpiracion(tokenCacheHitNext.getFechaExpiracion());
             token.setIdentificador(tokenCacheHitNext.getIdResumptionToken());
             token.setTamanio(new Integer(numResultados));
         } else
         {
             logger.debug("Solo hay una pagina. No hay resumptionToken");
             token.setCursor(new Integer(0));
             token.setTamanio(new Integer(numResultados));
         }
     }
     return token;
 }
	 
	 
	private String devuelveClaveResumptionToken() throws Exception	{
		return String.valueOf(System.currentTimeMillis());
	}

	  
	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
			prop = this.getSrvPropiedadService();
		return prop;
	}
	
	
    /** Método que obtiene una lista de ficheros de metadatos de los ODEs del repositorio entre dos fechas dadas, y controlando la paginación de los resultados.
	 * 
	 * @param Calendar 	Fecha a partir de la cual se desea consultar los metadatos.
	 * @param Calendar	Fecha hasta la cual se desea consultar los metadatos.
	 * @param int		Contador de inicio de resultados para la paginación.	
	 * @return ResultadoRecordVO[] Lista de resultados. En cada objeto de la lista, en el campo título se almacena la ruta física del fichero para posteriomente poder parsearlo.
	 * @throws Exception
	 * */
	private es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[] obtenerMetadatosFederados(Calendar fechaDesde, Calendar fechaHasta, int numInicio, boolean bModoTotalesRepositorio) throws Exception {
								
		if (logger.isDebugEnabled())
		{
			logger.debug("Va a obtener metadatos del repositorio :" + RUTA_METADATOS_ODES_FEDERADOS);
			logger.debug("Modo totales repositorio :" + bModoTotalesRepositorio);
			logger.debug("Fecha Desde :" + fechaDesde.getTime());
			logger.debug("Fecha Hasta :" + fechaHasta.getTime());
			logger.debug("Empieza a devolver registro número :" + numInicio);
			logger.debug("Registros por página :" + numMaxPaginaInt);
		}
		
		List<es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO> listaMetadatos = new ArrayList<es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO>(); 
		
		File dirInicial = new File(RUTA_METADATOS_ODES_FEDERADOS);
		
		// Sumamos un dia a las fechas porque el proceso de recopilación en el repositorio los crea con un día más.
   
		Calendar fecDesdeActualizada = Calendar.getInstance();
		fecDesdeActualizada.setTime(fechaDesde.getTime());
		fecDesdeActualizada.add(Calendar.DAY_OF_MONTH, 1);
		Calendar fechaHastaActualizada = Calendar.getInstance();
		fechaHastaActualizada.setTime(fechaHasta.getTime());
		fechaHastaActualizada.add(Calendar.DAY_OF_MONTH, 1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fecInicio = sdf.format(fecDesdeActualizada.getTime());			
		String fecFin = sdf.format(fechaHastaActualizada.getTime());
    
//  	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String fecInicio = sdf.format(fechaDesde.getTime());			
//		String fecFin = sdf.format(fechaHasta.getTime());
                
		int nNumOrdenFichero = 1;
		for (int i = 0; i < dirInicial.listFiles().length; i++) {
			String nombreDirFecha = (dirInicial.listFiles()[i]).getName();
			
			if ((nombreDirFecha.compareTo(fecInicio)>=0 )&& (nombreDirFecha.compareTo(fecFin)<=0))
			{					        		
				nNumOrdenFichero = listarDirectorio(dirInicial.listFiles()[i], listaMetadatos, numInicio,bModoTotalesRepositorio, nNumOrdenFichero);						
			}
			
			// Si no estamos en modo totalRepositorio debemos comprobar que no hayamos llenado ya la página
			if (!bModoTotalesRepositorio && listaMetadatos.size()>= numMaxPaginaInt)
			{				
				if (logger.isDebugEnabled())
					logger.debug("Ya se ha llenado la página de resultados");
				break;				
			}	
		}
		return listaMetadatos.toArray(new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO[listaMetadatos.size()] );		
	}
	

	/**
	 * Metodo que recorre un directorio de manera recursiva y va almacenando en la lista que se pasa como parámetro los ficheros xml. 
	 * Si se le pasa que está en modo total metadatos del repositorio no tiene en cuenta la paginación y devuelve todos los resultados para 
	 * que se devuelva el número de metadatos y poder paginar posteriormente.
	 */	
	public int listarDirectorio(File directorio, List<es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO> listaResultados, int numIni, boolean bModoTotalesRepositorio, int nNumOrdenFichero) {
 
		File[] ficheros = directorio.listFiles();

		for (int x = 0; x < ficheros.length; x++) {
			
			// Si no estamos en modo totalRepositorio debemos comprobar que no hayamos llenado ya la página
			if (!bModoTotalesRepositorio && listaResultados.size()>= numMaxPaginaInt)
			{		
				if (logger.isDebugEnabled())
					logger.debug("Ya se ha llenado la página de resultados");		
				return nNumOrdenFichero;			
			}		
			
			// Si es directorio lo recorremos recursivamente
			if (ficheros[x].isDirectory()) {				
				nNumOrdenFichero = listarDirectorio(ficheros[x],listaResultados, numIni, bModoTotalesRepositorio, nNumOrdenFichero);
			}
			// Si el fichero es un xml y el nombre es mayor de 4 letras (para prevenir error de fichero con nombre '.xml')
			else if ((ficheros[x].getName().endsWith(".xml")) && ((ficheros[x].getName().length()> 4)))
			{
				// Creamos un objeto ResultadoRecordVO para devolver el nombre del fichero
				// Se hace de esta manera para mantener el mismo tipo de vuelta
				// Dentro del objeto, en el campo título introducimos la ruta del fichero para que después se obtenga el fichero y se parsee
				
				// Solo se inserta si el el número de orden del fichero es mayor que el número de inicio
				// Esto se hace para soportar la paginación, si el número de orden es menor que el de inicio quiere decir que es un metadato
				// que ya se ha devuelto en una página anterior				
				if (nNumOrdenFichero >numIni)
				{
					es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO res = new es.pode.indexador.negocio.servicios.busqueda.ResultadoRecordVO();				
					res.setTitulo(ficheros[x].getAbsolutePath());
					listaResultados.add(res);	
					
				}				
				
				nNumOrdenFichero++;
			}
				
		}
	
		return nNumOrdenFichero;
	}
    	
	
	/**
	 * Metodo que realiza la validación de la fechaDesde y fechaHasta para el set de metadatos federados. 
	 * Las fechas deben ir rellenas y no deben diferir más de 30 días
	 * @throws Exception
	 */
    private boolean validacionFechasMetadatosFederados(Calendar fechaDesde, Calendar fechaHasta) throws Exception
    {    	
    	boolean bCorrecto=true;
    	
    	if(fechaDesde == null || (fechaHasta==null) || fechaHasta.before(fechaDesde)) { 
    		bCorrecto=false;
    	} else {
    		Calendar cHastaMesMenos = Calendar.getInstance();    
    		cHastaMesMenos.setTime(fechaHasta.getTime());
    		cHastaMesMenos.add(Calendar.DAY_OF_YEAR, -30);

    		if (cHastaMesMenos.after(fechaDesde))
    			bCorrecto=false;
    	}
    	return bCorrecto;
    }    	
    
}