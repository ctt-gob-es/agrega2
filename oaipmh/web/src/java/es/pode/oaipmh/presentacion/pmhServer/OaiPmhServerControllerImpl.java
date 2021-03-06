// license-header java merge-point
package es.pode.oaipmh.presentacion.pmhServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.io.FileUtils;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.sitemapProperties.SitemapProperties;
import es.agrega.soporte.sitemapProperties.SitemapPropertiesImpl;
import es.pode.oaipmh.negocio.servicios.DescripcionOaiIdentifierVO;
import es.pode.oaipmh.negocio.servicios.IdentifyVO;
import es.pode.oaipmh.negocio.servicios.ListMetadataFormatVO;
import es.pode.oaipmh.negocio.servicios.ParametrosOaiPmhVO;
import es.pode.oaipmh.negocio.servicios.ReanudacionTokenVO;
import es.pode.oaipmh.negocio.servicios.ResultadoHeaderVO;
import es.pode.oaipmh.negocio.servicios.ResultadoOAIRequest;
import es.pode.oaipmh.negocio.servicios.ResultadoRecordVO;
import es.pode.oaipmh.negocio.servicios.SetVO;
import es.pode.oaipmh.soporte.OAIPMHProperties;
import es.pode.parseadorXML.OaiPmhDao;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.oai_dc.Dc;
import es.pode.parseadorXML.oai_dc.DcContributor;
import es.pode.parseadorXML.oai_dc.DcCoverage;
import es.pode.parseadorXML.oai_dc.DcCreator;
import es.pode.parseadorXML.oai_dc.DcDate;
import es.pode.parseadorXML.oai_dc.DcDescription;
import es.pode.parseadorXML.oai_dc.DcFormat;
import es.pode.parseadorXML.oai_dc.DcIdentifier;
import es.pode.parseadorXML.oai_dc.DcLanguage;
import es.pode.parseadorXML.oai_dc.DcPublisher;
import es.pode.parseadorXML.oai_dc.DcRelation;
import es.pode.parseadorXML.oai_dc.DcRights;
import es.pode.parseadorXML.oai_dc.DcSource;
import es.pode.parseadorXML.oai_dc.DcSubject;
import es.pode.parseadorXML.oai_dc.DcTitle;
import es.pode.parseadorXML.oai_dc.DcType;
import es.pode.parseadorXML.oai_dc.Oai_dcTypeItem;
import es.pode.parseadorXML.oai_pmh.Description;
import es.pode.parseadorXML.oai_pmh.Error;
import es.pode.parseadorXML.oai_pmh.Identify;
import es.pode.parseadorXML.oai_pmh.ListIdentifiers;
import es.pode.parseadorXML.oai_pmh.ListIdentifiersTypeHeader;
import es.pode.parseadorXML.oai_pmh.ListIdentifiersTypeResumptionToken;
import es.pode.parseadorXML.oai_pmh.ListMetadataFormats;
import es.pode.parseadorXML.oai_pmh.ListRecords;
import es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord;
import es.pode.parseadorXML.oai_pmh.ListRecordsTypeResumptionToken;
import es.pode.parseadorXML.oai_pmh.ListSets;
import es.pode.parseadorXML.oai_pmh.Metadata;
import es.pode.parseadorXML.oai_pmh.MetadataFormat;
import es.pode.parseadorXML.oai_pmh.RecordTypeHeader;
import es.pode.parseadorXML.oai_pmh.Request;
import es.pode.parseadorXML.oai_pmh.Set;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.ListRecordsAgrega;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.OAIPMHAgrega;
import es.pode.parseadorXML.oai_pmh.oai_pmhAgrega.RecordAgrega;
import es.pode.parseadorXML.oai_pmh.types.DeletedRecordType;
import es.pode.parseadorXML.oai_pmh.types.GranularityType;
import es.pode.parseadorXML.oai_pmh.types.OAIPMHerrorcodeType;
import es.pode.parseadorXML.oai_pmh.types.ProtocolVersionType;
import es.pode.parseadorXML.oai_pmh.types.StatusType;
import es.pode.parseadorXML.oai_pmh.types.VerbType;
import es.pode.parseadorXML.oai_pmh_id.OaiIdentifier;
import es.pode.soporte.utiles.date.DateManager;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;



/**
 * @see es.pode.oaipmh.presentacion.pmhServer.OaiPmhServerController
 */

public class OaiPmhServerControllerImpl extends OaiPmhServerController
{

	public final static String DOS_PUNTOS = ":";
	public static final String GUION = "-";


	private static Logger logger = Logger.getLogger(OaiPmhServerControllerImpl.class);
	private java.util.Properties pOaiPmhProperties = null;
	private OaiPmhDao dao = null;


  

	 /**
	 * Genera la llamada Identify del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void identify(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.IdentifyForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		ResultadoOAIRequest resultadoOAIRequest = this.getSrvOaiPmhService().identify();
    		form.setResultadoOAIRequest(resultadoOAIRequest);
    		if(logger.isDebugEnabled())logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
    	}catch(Exception e)
    	{
    		logger.error("Exception en el m�todo identify - "+e);
    		response.sendError(503, "Service unavailable"); 
    		
    	}
       
    }


    /**
	 * Genera la llamada ListIdentifiers del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void listIdentifiers(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ListIdentifiersForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
        {
            ParametrosOaiPmhVO parametroLlamada = new ParametrosOaiPmhVO();
            ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
            parametroLlamada.setCodigoPaginacion(parametrosRequestVO.getCodigoPaginacion());
            if(parametrosRequestVO.getFechaDesde() == null)
            {
                parametroLlamada.setFechaDesde(null);
            } else
            {
                parametroLlamada.setFechaDesde(DateManager.dateToCalendar(getFecha(parametrosRequestVO.getFechaDesde())));
            }
            if(parametrosRequestVO.getFechaHasta() == null)
            {
                parametroLlamada.setFechaHasta(null);
            } else
            {
                parametroLlamada.setFechaHasta(DateManager.dateToCalendar(getFecha(parametrosRequestVO.getFechaHasta())));
            }
            parametroLlamada.setIdentificador(parametrosRequestVO.getIdentificador());
            parametroLlamada.setIdentificadorConjunto(parametrosRequestVO.getIdentificadorConjunto());
            parametroLlamada.setPrefijoMetadato(parametrosRequestVO.getPrefijoMetadato());
            ResultadoOAIRequest resultadoOAIRequest = getSrvOaiPmhService().listIdentifiers(parametroLlamada);
            if(logger.isDebugEnabled())
            {
				logger.debug("Resultado de la request: <"
						+ (new StringBuilder()).append("resultadoOAIRequest ").append(resultadoOAIRequest).toString()
						+ "> Codigos de error de la request: <"
						+ (new StringBuilder()).append("resultadoOAIRequest.getErrorCode() ").append(resultadoOAIRequest.getErrorCode()).toString()
						+ "> Lista de identificadores de la request: <"
						+ (new StringBuilder()).append("resultadoOAIRequest.getListIdentifiers() ").append(resultadoOAIRequest.getListIdentifiers()).toString()
						+ ">");
            }
            form.setResultadoOAIRequest(resultadoOAIRequest);
            if(logger.isDebugEnabled())
                logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
        }
        catch(Exception e)
        {
            logger.error((new StringBuilder()).append("Exception en el m\351todo ListIdentifiers ").append(e).toString()+" - ",e);
            response.sendError(503, "Service unavailable");
        }
        
    }



    /**
	 * Genera la llamada ListMetadataFormat del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void listMetadataFormat(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ListMetadataFormatForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		ParametrosOaiPmhVO parametroLlamada = new ParametrosOaiPmhVO();
    		ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
    		this.getBeanMapper().map(parametrosRequestVO, parametroLlamada);
    		ResultadoOAIRequest resultadoOAIRequest = this.getSrvOaiPmhService().listMetadataFormat(parametroLlamada);
    		form.setResultadoOAIRequest(resultadoOAIRequest);
    		if(logger.isDebugEnabled())logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
    		form.setParametrosRequestVO(parametrosRequestVO);
    	}catch(Exception e)
    	{
    		logger.error("Exception en el m�todo ListMetadataFormat - "+e);
    		response.sendError(503, "Service unavailable"); 
    		
    	}
       
    }


    /**
	 * Genera la llamada ListSets del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void listSets(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ListSetsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
    		ResultadoOAIRequest resultadoOAIRequest = this.getSrvOaiPmhService().listSets();
    		form.setResultadoOAIRequest(resultadoOAIRequest);
    		if(logger.isDebugEnabled())logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
    		form.setParametrosRequestVO(parametrosRequestVO);
    	}catch(Exception e)
    	{
    		logger.error("Exception en el m�todo ListSets - "+e);
    		response.sendError(503, "Service unavailable"); 
    		
    	}
         
    }


    /**
	 * Genera la llamada ListRecords del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void listRecords(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ListRecordsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		ParametrosOaiPmhVO parametroLlamada = new ParametrosOaiPmhVO();
    		ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
//    	Mapeo ParametrosRequestVO - ParametrosOaiPmhVO
    		parametroLlamada.setCodigoPaginacion(parametrosRequestVO.getCodigoPaginacion());
    	//fecha Desde
    		if(parametrosRequestVO.getFechaDesde() == null)
    		{
    			parametroLlamada.setFechaDesde(null);
    		}else
    		{
    			parametroLlamada.setFechaDesde(DateManager.dateToCalendar(getFecha(parametrosRequestVO.getFechaDesde())));
    		}
    	//fechaHasta
    		if(parametrosRequestVO.getFechaHasta() == null)
    		{
    			parametroLlamada.setFechaHasta(null);
    		}else
    		{
    			parametroLlamada.setFechaHasta(DateManager.dateToCalendar(getFecha(parametrosRequestVO.getFechaHasta())));
    		}
    		
    	//Identificador
    		parametroLlamada.setIdentificador(parametrosRequestVO.getIdentificador());
    	//identificador conjunto
    		parametroLlamada.setIdentificadorConjunto(parametrosRequestVO.getIdentificadorConjunto());
    	//tipo de metadato
    		parametroLlamada.setPrefijoMetadato(parametrosRequestVO.getPrefijoMetadato());
    	//this.getBeanMapper().map(parametrosRequestVO, parametroLlamada);
    		ResultadoOAIRequest resultadoOAIRequest = this.getSrvOaiPmhService().listRecords(parametroLlamada);
    		form.setResultadoOAIRequest(resultadoOAIRequest);
    		if(logger.isDebugEnabled())logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
    		form.setParametrosRequestVO(parametrosRequestVO); 
    	}catch(Exception e)
    	{
    		logger.error("Exception en el m�todo ListRecords - "+e);
    		response.sendError(503, "Service unavailable"); 
    		
    	}
         
    }


    /**
	 * Genera la llamada GetRecord del protocolo Oai-pmh
	 * @throws Exception
	 */
    public final void getRecord(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.GetRecordForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try
    	{
    		ParametrosOaiPmhVO parametroLlamada = new ParametrosOaiPmhVO();
    		ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
    		if(logger.isDebugEnabled()){
    			logger.debug("parametrosRequestVO <"+parametrosRequestVO+">");
    			logger.debug("parametrosRequestVO.getIdentificador <"+parametrosRequestVO.getIdentificador()+">");
    			logger.debug("parametrosRequestVO.getPrefijoMetadato <"+parametrosRequestVO.getPrefijoMetadato()+">");
    		}
    		this.getBeanMapper().map(parametrosRequestVO, parametroLlamada);
    		ResultadoOAIRequest resultadoOAIRequest = this.getSrvOaiPmhService().getRecord(parametroLlamada);
    		form.setResultadoOAIRequest(resultadoOAIRequest);
    		if(logger.isDebugEnabled()) logger.debug("Almaceno en el formulario el objeto resultadoOAIRequest");
    		form.setParametrosRequestVO(parametrosRequestVO); 
    	}catch(Exception e)
    	{
    		logger.error("Exception en el m�todo GetRecord - "+e);
    		response.sendError(503, "Service unavailable"); 
    		
    	}
       
    }


    /**
	 * Genera el VO con el error que se produce en la llamada al m�todo del protocolo oai
	 * @throws Exception
	 */
    public final void obtenerResultadoOAIRequest(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ObtenerResultadoOAIRequestForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
      	ResultadoOAIRequest resultadoOAIRequest = new ResultadoOAIRequest();
        ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
        this.getBeanMapper().map(parametrosRequestVO, resultadoOAIRequest);
        //Recorremos el VO ResultadoOAIRequest
        if(logger.isDebugEnabled()) {
        	logger.debug("resultadoOAIRequest.getCodigoError <"+resultadoOAIRequest.getErrorCode()+">");
        	logger.debug("resultadoOAIRequest.getDescripcionError <"+resultadoOAIRequest.getErrorDescripcion()+">");
        	logger.debug("resultadoOAIRequest.getVerb() <"+resultadoOAIRequest.getVerb()+">");
        }
        ///
    	form.setResultadoOAIRequest(resultadoOAIRequest);
     }


    /**
     * Recibe el VO con los par�metros de la petici�n para validar los par�metros 
     * return String devuelve una cadena con el tipo de m�todo del protoco oai-pmh
     */
    public final java.lang.String analizaPeticion(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.AnalizaPeticionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String resultado = "";
        ParametrosRequestVO parametrosRequest = form.getParametrosRequestVO();
        try
        { 
	        if(parametrosRequest == null)
	        {
	        	if(logger.isDebugEnabled())logger.debug("no se recibe ningun parametro");
	        	resultado = "badVerb";
	        	parametrosRequest = new ParametrosRequestVO();
	        	parametrosRequest.setErrorCode("badVerb");
	        	parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_VERB_DES);
	        	
	        }else if(parametrosRequest.getErrorCode() != null)
        	{
        		if(logger.isInfoEnabled())logger.info("Existe un error en los par�metros, hay mas atributos del mismo tipo en la request");
        		resultado = parametrosRequest.getErrorCode();
        		
        	}else if(parametrosRequest.getVerb() == null)
    		{
    			logger.warn("No se recibe el parametro verb necesario");
    			resultado = "badVerb";
            	parametrosRequest = new ParametrosRequestVO();
            	parametrosRequest.setErrorCode("badVerb");
            	parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_VERB_DES);
            	
    		}else if(parametrosRequest.getVerb().equalsIgnoreCase("GetRecord"))
        	{
        		//Validamos si aparece el parametro identifier
        		String identificador = parametrosRequest.getIdentificador();
        		String metadatoFormat = parametrosRequest.getPrefijoMetadato();
        		if(((validaParametros(parametrosRequest, parametrosRequest.getVerb())).equalsIgnoreCase("badArgument")))
        		{
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		
        		}else if((identificador == null) || (identificador == "") || (identificador == " ") || (metadatoFormat == null) || (metadatoFormat == "") || (metadatoFormat == " "))
        		{
        			if(logger.isDebugEnabled())logger.debug("El identificador o el tipo de metadato no es valido");
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		}else
        		{
        			resultado = "GetRecord";
        		}
        		
        	}else if((parametrosRequest.getVerb().equalsIgnoreCase("ListRecords")) || (parametrosRequest.getVerb().equalsIgnoreCase("ListIdentifiers")))
    		{
    			String metadatoFormat = parametrosRequest.getPrefijoMetadato();
    			if(logger.isDebugEnabled())logger.debug("codigo paginacion <"+parametrosRequest.getCodigoPaginacion()+">");
    			if(((validaParametros(parametrosRequest, parametrosRequest.getVerb())).equalsIgnoreCase("badArgument")))
        		{
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		}else
        		{
        			//Validamos si recibimos el parametro metadataPrefix siempre y cuando no recibamos el par�metro resumptionToken
        			if(logger.isDebugEnabled())logger.debug("metadatoFormat <"+metadatoFormat+">");
        			if(((metadatoFormat == null) || (metadatoFormat == "") || (metadatoFormat == " "))&&((parametrosRequest.getCodigoPaginacion() == null)))
        			{
        				if(logger.isDebugEnabled())logger.debug("El tipo de metadato no es valido");
        				resultado = "badArgument";
        				parametrosRequest.setErrorCode(resultado);
            			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        			
        			}else if((!(parametrosRequest.getFechaDesde() == null)) && !(this.validaFecha((parametrosRequest.getFechaDesde()))))
    				{
    					if(logger.isDebugEnabled())logger.debug("El atributo from no es correcto");
    					resultado = "badArgument";
    					parametrosRequest.setErrorCode(resultado);
    					parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
    				
    				}else if((!(parametrosRequest.getFechaHasta() == null)) && !(this.validaFecha(parametrosRequest.getFechaHasta())))
					{
						if(logger.isDebugEnabled())logger.debug("El atributo until no es correcto");
						resultado = "badArgument";
						parametrosRequest.setErrorCode(resultado);
						parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
					
					}else if(!(comparacionFechas(parametrosRequest.getFechaDesde(), parametrosRequest.getFechaHasta())))
					{
						if(logger.isDebugEnabled())logger.debug("Las fechas no son correctas");
						resultado = "badArgument";
						parametrosRequest.setErrorCode(resultado);
						parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
					
					}else if(parametrosRequest.getVerb().equalsIgnoreCase("ListRecords"))
					{
						resultado = "ListRecords";
					}else
					{
						resultado = "ListIdentifiers";
					}
        		}
    		}else if(parametrosRequest.getVerb().equalsIgnoreCase("ListSets"))
			{
				if(((validaParametros(parametrosRequest, parametrosRequest.getVerb())).equalsIgnoreCase("badArgument")))
        		{
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		}else
        		{
					resultado = "ListSets";
				
        		}
			}else if(parametrosRequest.getVerb().equalsIgnoreCase("Identify"))
			{
				if(((validaParametros(parametrosRequest, parametrosRequest.getVerb())).equalsIgnoreCase("badArgument")))
        		{
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		}else
        		{
        			resultado = "Identify";
        		}
			}else if(parametrosRequest.getVerb().equalsIgnoreCase("ListMetadataFormats"))
			{
				if(((validaParametros(parametrosRequest, parametrosRequest.getVerb())).equalsIgnoreCase("badArgument")))
        		{
        			resultado = "badArgument";
        			parametrosRequest.setErrorCode(resultado);
        			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_ARGUMENT_DES);
        		}else
        		{
        			resultado = "ListMetadataFormat";
        		}
			}else
			{
				if(logger.isDebugEnabled())logger.debug("No es ninguno de los m�todos reconocidos");
				resultado = "badVerb";
				parametrosRequest.setErrorCode(resultado);
    			parametrosRequest.setErrorDescripcion(OAIPMHProperties.ERR_BAD_VERB_DES);
			}
			    
        }catch(Exception e)
        {
        	logger.error("[analizaPeticion] Se produce la siguiente excepcion - "+e);
        	resultado = "Error";  
        }
        form.setParametrosRequestVO(parametrosRequest);
        return resultado;
    }


    /**
     * Recoge los par�metros de la request y genera un VO con los datos de la misma
     */
    public final void obtenerParametros(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ObtenerParametrosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ParametrosRequestVO parametrosRequestVO = null;
    	Enumeration nombreParametros = request.getParameterNames();
    	if(request.getParameterNames() == null)
    	{
    		if(logger.isDebugEnabled())logger.debug("No tengo parametros");
        	form.setParametrosRequestVO(null);
        	return;
    	}
		parametrosRequestVO = new ParametrosRequestVO();
		List listaParametros = new ArrayList();
		
		while(nombreParametros.hasMoreElements())
		{
			String param = (String)nombreParametros.nextElement();
			if(logger.isDebugEnabled())logger.debug("param vale <"+param+">");
			if(param.equals("verb"))
			{
				if(request.getParameterValues("verb").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo verb");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_BAD_VERB_DES);
					parametrosRequestVO.setErrorCode("badVerb");
				}else
				{
					if(logger.isDebugEnabled())logger.debug("tipo de operacion <"+request.getParameter(param)+">");
					parametrosRequestVO.setVerb(request.getParameter(param));
				}
			}else if(param.equals("identifier"))
			{
				if(request.getParameterValues("identifier").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo identifier");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_IDENTIFIER_DES);
				}else
				{
//        					if(logger.isDebugEnabled())logger.debug("identificador");
					parametrosRequestVO.setIdentificador(request.getParameter(param));
				}
			}else if(param.equals("metadataPrefix"))
			{
				if(request.getParameterValues("metadataPrefix").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo metadataPrefix");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_METADATAPREFIX_DES);
				}else
				{
					parametrosRequestVO.setPrefijoMetadato(request.getParameter(param));
				}
			}else if(param.equals("from"))
			{
				if(request.getParameterValues("from").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo from");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_FROM_DES);
				}else
				{
					parametrosRequestVO.setFechaDesde(request.getParameter(param));
				}
			}else if(param.equals("until"))
			{
				if(request.getParameterValues("until").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo until");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_UNTIL_DES);
				}else
				{
					parametrosRequestVO.setFechaHasta(request.getParameter(param));
				}
			}else if(param.equals("set"))
			{
				if(request.getParameterValues("set").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo set");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_SET_DES);
				}else
				{
					parametrosRequestVO.setIdentificadorConjunto(request.getParameter(param));
				}
			}else if(param.equals("resumptionToken"))
			{
//    										if(logger.isDebugEnabled())logger.debug("resumptionToken");
				if(request.getParameterValues("resumptionToken").length > 1)
				{
					if(logger.isDebugEnabled())logger.debug("En la peticion hay mas de un atributo resumptionToken");
					parametrosRequestVO.setErrorCode("badArgument");
					parametrosRequestVO.setErrorDescripcion(OAIPMHProperties.ERR_SEVERAL_RESUMPTIONTOKEN_DES);
				}else
				{
					parametrosRequestVO.setCodigoPaginacion(request.getParameter(param));
					if(logger.isDebugEnabled())logger.debug("parametrosRequestVO.getCodigoPaginacion() <"+parametrosRequestVO.getCodigoPaginacion()+">");
				}
			}else
			{
				//Se a�ade el parametro a la lista de parametros
				listaParametros.add(param);
			}
		}
		
		if(!(listaParametros == null) || !(listaParametros.size() == 0))
			parametrosRequestVO.setParametros(listaParametros);

    	form.setParametrosRequestVO(parametrosRequestVO);
    }


    /**
     * Metodo que devuelve la fecha como string en formato valido para OAI-PMH
     * @throws Exception
     */
    private String devuelveFechaString (String var) throws Exception {
    	return(var.substring(0,10));    	
    }
    

	/**
	 * Nos devuelve el idOAI del id-MEC
	 */  
    private String devuelveidOai(String idMEC) throws Exception  {
    	//El identificador debe cumplir el siguiente formato: oai-identifier = scheme ":" namespace-identifier ":" local-identifier
    	String idOAI = "";
    	if(idMEC != null && !idMEC.equals(""))
    		idOAI = OAIPMHProperties.eschemaIdentifier() + DOS_PUNTOS + OAIPMHProperties.namespaceIdentifier() + DOS_PUNTOS +  idMEC;
    	return idOAI;
    }
    
    
    /**
     * @see es.pode.oaipmh.presentacion.pmhServer.OaiPmhServerController#obtenerRespuestaProtocolo(org.apache.struts.action.ActionMapping, es.pode.oaipmh.presentacion.pmhServer.ObtenerRespuestaProtocoloForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerRespuestaProtocolo(ActionMapping mapping, es.pode.oaipmh.presentacion.pmhServer.ObtenerRespuestaProtocoloForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
    	
	    	int tipoProtocolo = -1;
	        ResultadoOAIRequest resultadoOAIRequest = form.getResultadoOAIRequest();
	        ParametrosRequestVO parametrosRequestVO = form.getParametrosRequestVO();
	        dao = new OaiPmhDao();
	        OAIPMHAgrega oaipmh = new OAIPMHAgrega();
	        oaipmh.setResponseDate(new Date());
	        //Modificamos la fecha para que tenga granularidad diaria
	        if(logger.isDebugEnabled())logger.debug("oaipmh.getResponseDate() <"+oaipmh.getResponseDate()+">");
			Request requestOai = new Request();
			requestOai.setContent("http://"+request.getServerName()+request.getRequestURI());
			
			if(resultadoOAIRequest.getErrorCode() == null)
			{
				requestOai.setVerb(VerbType.valueOf(resultadoOAIRequest.getVerb()));
				if(logger.isDebugEnabled())logger.debug("La respuesta es correcta");
				
				if(resultadoOAIRequest.getVerb().equalsIgnoreCase("GetRecord"))
				{							
					//Atributos de request
					requestOai.setIdentifier(parametrosRequestVO.getIdentificador());
					requestOai.setMetadataPrefix(parametrosRequestVO.getPrefijoMetadato());
					oaipmh.setRequest(requestOai);
					
					RecordAgrega record = new RecordAgrega();
					RecordTypeHeader header = new RecordTypeHeader();
					// Si se ha pedido metadatos LOMES, LOM o es un set estamos trabajando con objetos LomAgrega 
					if((parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
	    					parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
	    					|| (parametrosRequestVO.getIdentificadorConjunto() != null))
	    					{	    				
						Lom lom = (Lom)resultadoOAIRequest.getGetRecordLomes();
						LomAgrega lomAgrega = new LomAgrega(lom);		
						header.setIdentifier(parametrosRequestVO.getIdentificador());
						if(logger.isDebugEnabled())logger.debug("header.getIdentifier() <"+header.getIdentifier()+">");
						
						if(lomAgrega.getLifeCycleAgrega()!=null &&
								lomAgrega.getLifeCycleAgrega().getEstatusAv()!=null &&
								lomAgrega.getLifeCycleAgrega().getEstatusAv().contentEquals("DESPUBLICADO")) {

							header.setDatestamp(lomAgrega.getGeneralAgrega().getNivelDeAgregacion());
							header.setStatus(StatusType.DELETED);
							
						} else {					
						
							// Si tenemos que devolver lomes o lom se devuelve directamente
							if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
			    					parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))	
	    					{
								header.setDatestamp(devuelveFechaString(lomAgrega.getLifeCycleAgrega().getFechaPublicacion()));
								record.setMetadata(lom);
	    					}else
	    					{
	    						// Tenemos que parsear de lomes a dc
								header.setDatestamp(devuelveFechaString(lomAgrega.getLifeCycleAgrega().getFechaPublicacion()));
	    						record.setMetadata(transformadorLomes2DC(parametrosRequestVO.getPrefijoMetadato(),lom));
	    					}
						}
						
						if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES))
							tipoProtocolo = OaiPmhDao.GETRECORD_LOMES;
						else if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
							tipoProtocolo = OaiPmhDao.GETRECORD_LOM_IEEE;
						else
							tipoProtocolo = OaiPmhDao.GETRECORD_DC;
							
					} else {	
						//if(logger.isDebugEnabled())logger.debug("getRecord");
						ResultadoRecordVO resultadoRecordVO = resultadoOAIRequest.getGetRecord();
						if(logger.isDebugEnabled())logger.debug("resultadoRecordVO <"+resultadoRecordVO+">");
						header.setIdentifier(resultadoRecordVO.getIdRepositorio());    		
						if(logger.isDebugEnabled())logger.debug("header.getIdentifier() <"+header.getIdentifier()+">");
						header.setDatestamp(resultadoRecordVO.getFecha());
						record.setMetadata(obtenerMetadato(parametrosRequestVO.getPrefijoMetadato(),resultadoRecordVO));
						tipoProtocolo = OaiPmhDao.GETRECORD_DC;
					}
					
					record.setHeader(header);						
					oaipmh.setGetRecord(record);
					
		        }else if(resultadoOAIRequest.getVerb().equalsIgnoreCase("Identify"))
	        	{
	            	IdentifyVO identifyVO = resultadoOAIRequest.getIdentifyVO();
	            	oaipmh.setRequest(requestOai);
	            	Identify identify = new Identify();
	        		identify.setRepositoryName(identifyVO.getNombreRepositorio());
	        		if(logger.isDebugEnabled())logger.debug("identify.getRepositoryName() <"+identify.getRepositoryName()+">");
	        		identify.setBaseURL(identifyVO.getUrlRepositorio());
	        		if(logger.isDebugEnabled())logger.debug("oaipmh.getBaseURL() "+identify.getBaseURL()+">");
	        		identify.setProtocolVersion(ProtocolVersionType.VALUE_0);
	        		if(logger.isDebugEnabled())logger.debug("identify.getProtocolVersion() <"+identify.getProtocolVersion()+">");
	        		identify.addAdminEmail(identifyVO.getEmailAdmin());
	        		if(logger.isDebugEnabled())logger.debug("oaipmh.getAdminEmailCount() <"+identify.getAdminEmailCount()+">");
	        		identify.setEarliestDatestamp(identifyVO.getFechaInicioRepositorio());
	        		if(logger.isDebugEnabled())logger.debug("oaipmh.getEarliestDatestamp() <"+identify.getEarliestDatestamp()+">");
	        		//No vamos a tener pol�tica de borrado
	        		if(logger.isDebugEnabled())logger.debug("identifyVO.getPoliticaBorrado() <"+identifyVO.getPoliticaBorrado()+">");
	        		identify.setDeletedRecord(DeletedRecordType.valueOf(identifyVO.getPoliticaBorrado()));
	        		if(logger.isDebugEnabled())logger.debug("identifyVO.getTemporalidad() <"+identifyVO.getTemporalidad()+">");
	        		identify.setGranularity(GranularityType.valueOf(identifyVO.getTemporalidad()));
	        		DescripcionOaiIdentifierVO descripcionOaiIdentifierVO = identifyVO.getDescripcionOaiIdentifier();
	        		OaiIdentifier oaiIdentifierType = new OaiIdentifier();
	        		oaiIdentifierType.setDelimiter(descripcionOaiIdentifierVO.getDelimitador());
	        		oaiIdentifierType.setRepositoryIdentifier(descripcionOaiIdentifierVO.getIdentificadorRepositorio());
	        		oaiIdentifierType.setSampleIdentifier(descripcionOaiIdentifierVO.getEjemploIdentificador());
	        		oaiIdentifierType.setScheme(descripcionOaiIdentifierVO.getEsquema());
	        		Description desc = new Description();
	        		desc.setAnyObject(oaiIdentifierType);
	        		identify.addDescription(desc);
	        		oaipmh.setIdentify(identify);	
	        		tipoProtocolo = OaiPmhDao.IDENTIFY;
	        		
	        	}else if(resultadoOAIRequest.getVerb().equalsIgnoreCase("ListIdentifiers"))
	    		{
	//	        			if(logger.isDebugEnabled())logger.debug("ListIdentifiers");
	    			ResultadoHeaderVO[] resultadoHeaderVO = resultadoOAIRequest.getListIdentifiers();
	    			requestOai.setMetadataPrefix(parametrosRequestVO.getPrefijoMetadato());
	    			if(!(parametrosRequestVO.getFechaDesde() == null))
	    				requestOai.setFrom(parametrosRequestVO.getFechaDesde());
	    			
	    			if(!(parametrosRequestVO.getFechaHasta() == null))
	    				requestOai.setUntil(parametrosRequestVO.getFechaHasta());
	    			
	    			if(!(parametrosRequestVO.getIdentificadorConjunto() == null))
	    				requestOai.setSet(parametrosRequestVO.getIdentificadorConjunto());
	    			
	    			if(!(parametrosRequestVO.getCodigoPaginacion() == null))
	    				requestOai.setResumptionToken(parametrosRequestVO.getCodigoPaginacion());
	    			
	    			oaipmh.setRequest(requestOai);
	    			ListIdentifiers listIdentifiers = new ListIdentifiers();
	    			for(int i=0;i<resultadoHeaderVO.length;i++)
	    			{
	    				ListIdentifiersTypeHeader header = new ListIdentifiersTypeHeader();
	    				if(!(resultadoHeaderVO[i] == null))
	    					header.setIdentifier(resultadoHeaderVO[i].getIdentificador());
	    				
	    				if(!(resultadoHeaderVO[i] == null) &&(!(resultadoHeaderVO[i].getFecha() == null)))
	    					header.setDatestamp((resultadoHeaderVO[i].getFecha()).toString());
	    				
	    				if(!(resultadoHeaderVO[i].getIdentificadorConjunto() == null))
	    					header.setSetSpec(resultadoHeaderVO[i].getIdentificadorConjunto());
	    				
	    				listIdentifiers.addListIdentifiersTypeHeader(header);
	    			}
	    			if(!(resultadoOAIRequest.getReanudacionToken() == null))
	    			{
	    				ReanudacionTokenVO reanudacionToken = resultadoOAIRequest.getReanudacionToken();
	    				ListIdentifiersTypeResumptionToken listIdentifiersTypeResumptionToken = new ListIdentifiersTypeResumptionToken();
						if(reanudacionToken.getFechaExpiracion() != null)
						{
							listIdentifiersTypeResumptionToken.setExpirationDate(reanudacionToken.getFechaExpiracion().getTime() );
						}
						if(reanudacionToken.getTamanio() != null)
						{
							listIdentifiersTypeResumptionToken.setCompleteListSize((new Long((reanudacionToken.getTamanio()).toString())).longValue());
						}
	    				listIdentifiersTypeResumptionToken.setContent(reanudacionToken.getIdentificador());
	    				if(reanudacionToken.getCursor() != null)
	                    {
	    					listIdentifiersTypeResumptionToken.setCursor((new Long((reanudacionToken.getCursor()).toString())).longValue());
	                    }
	    				listIdentifiers.setListIdentifiersTypeResumptionToken(listIdentifiersTypeResumptionToken);
	    			}
	    			oaipmh.setListIdentifiers(listIdentifiers);
	    			tipoProtocolo = OaiPmhDao.LISTIDENTIFIERS;
	    			
	    		}else if(resultadoOAIRequest.getVerb().equalsIgnoreCase("ListMetadataFormats"))
				{
	//	        				if(logger.isDebugEnabled())logger.debug("ListMetadataFormats");
					ListMetadataFormatVO[] listMetadataFormat = resultadoOAIRequest.getListMetadataFormat();
					if(!(parametrosRequestVO.getIdentificador() == null))
	    			{
	    				requestOai.setIdentifier(parametrosRequestVO.getIdentificador());
	    				if(logger.isDebugEnabled())logger.debug("requestOai.getIdentifier() <"+requestOai.getIdentifier()+">");
	    			}
	    			
	        		oaipmh.setRequest(requestOai);
	        		ListMetadataFormats listMetadataFormats= new ListMetadataFormats();
	        		for(int i=0;i<listMetadataFormat.length;i++)
	        		{
	        			MetadataFormat meta = new MetadataFormat();
	        			meta.setMetadataPrefix(listMetadataFormat[i].getNombreMetadato());
	        			meta.setSchema(listMetadataFormat[i].getEsquema());
	        			meta.setMetadataNamespace(listMetadataFormat[i].getEspacioNombres());
	        			listMetadataFormats.addMetadataFormat(meta);
	        		}
	        		oaipmh.setListMetadataFormats(listMetadataFormats);
	        		tipoProtocolo = OaiPmhDao.LISTMETADATAFORMATS;
	        		
				}else if(resultadoOAIRequest.getVerb().equalsIgnoreCase("ListRecords"))
				{
					Object[] o = null;
					ResultadoRecordVO[] resultadoRecordVO = null;
					ListRecordsTypeRecord[] aRecord = null;
					int numResultados = 0;
					
					// Si se ha pedido metadatos LOMES, LOM o es un set estamos trabajando con objetos LomAgrega 
	    			if((parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
	    					parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
	    					|| (parametrosRequestVO.getIdentificadorConjunto() != null))
	    					{
	    				
	    				o = resultadoOAIRequest.getListRecordsLomes();
	    				numResultados = o.length;
	    				
						if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES))
							tipoProtocolo = OaiPmhDao.GETRECORD_LOMES;
						else if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
							tipoProtocolo = OaiPmhDao.GETRECORD_LOM_IEEE;
						else
							tipoProtocolo = OaiPmhDao.GETRECORD_DC;
	    				
					} else {
	    				resultadoRecordVO = resultadoOAIRequest.getListRecords();
	    				numResultados = resultadoRecordVO.length;
	            		tipoProtocolo = OaiPmhDao.LISTRECORDS_DC;
	    		    }
	        		aRecord = new ListRecordsTypeRecord[numResultados];
					
	    			
					if(parametrosRequestVO.getPrefijoMetadato() == null)
						requestOai.setMetadataPrefix(OAIPMHProperties.VALUE_METADATO_DC);
					else
						requestOai.setMetadataPrefix(parametrosRequestVO.getPrefijoMetadato());
					
	    			if(!(parametrosRequestVO.getFechaDesde() == null))
	    				requestOai.setFrom(parametrosRequestVO.getFechaDesde());
	    			
	    			if(!(parametrosRequestVO.getFechaHasta() == null))
	    				requestOai.setUntil(parametrosRequestVO.getFechaHasta());
	    			
	    			if(!(parametrosRequestVO.getIdentificadorConjunto() == null))
	    				requestOai.setSet(parametrosRequestVO.getIdentificadorConjunto());
	    			
	    			if(!(parametrosRequestVO.getCodigoPaginacion() == null))
	    				requestOai.setResumptionToken(parametrosRequestVO.getCodigoPaginacion());
	    			
	        		oaipmh.setRequest(requestOai);
	        		ListRecordsAgrega listRecordsAgrega = new ListRecordsAgrega();
	        		ListRecords listRe = new ListRecords();
					ListRecordsTypeResumptionToken listRecordssTypeResumptionToken = new ListRecordsTypeResumptionToken();
					
	        		for(int i=0; i<numResultados; i++)
	        		{
	        			aRecord[i] = new ListRecordsTypeRecord();
	        			RecordTypeHeader header = new RecordTypeHeader();
	        			Metadata meta=new Metadata();
	        			
	        			// Si se ha pedido metadatos LOMES, LOM o es un set estamos trabajando con objetos LomAgrega 
	        			if((parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
		    					parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))
		    					|| (parametrosRequestVO.getIdentificadorConjunto() != null))
		    					{
	        				// TODO 20161111 ejfente. Aqu� hay que distinguir...si viene con set o no 
	        				// Si viene con set tenemos siempre lomes y en funci�n del metadato solicitado se debe
	        				// transformar a dc o trabajar con lom. Hay que hacer un transformador como el "obtenerMetadato"
		    					        				
	        				LomAgrega lomAgrega = new LomAgrega((Lom)o[i]);
	        				
							if(lomAgrega.getLifeCycleAgrega()!=null &&
									lomAgrega.getLifeCycleAgrega().getEstatusAv()!=null &&
									lomAgrega.getLifeCycleAgrega().getEstatusAv().contentEquals("DESPUBLICADO")) {

								header.setDatestamp(lomAgrega.getGeneralAgrega().getNivelDeAgregacion());
								header.setStatus(StatusType.DELETED);
	        				
							} else {
								
								String catalogo = this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.CATALOGO_MEC);
								header.setIdentifier(devuelveidOai(lomAgrega.getGeneralAgrega().obtenerIdentificadorFormatoMEC(catalogo)));		
								header.setDatestamp(devuelveFechaString(lomAgrega.getLifeCycleAgrega().getFechaPublicacion()));
								
								// Si tenemos que devolver lomes o lom se devuelve directamente
								if(parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOMES) ||
				    					parametrosRequestVO.getPrefijoMetadato().equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_LOM_IEEE))	
		    					{										
									meta.setAnyObject((Lom)o[i]);
		    					}else
		    					{
		    						// Tenemos que parsear de lomes a dc
									meta.setAnyObject(transformadorLomes2DC(parametrosRequestVO.getPrefijoMetadato(),(Lom)o[i]));
		    					}
							}
							
	        			} else {
	            			//if(logger.isDebugEnabled())logger.debug("El resultadoRecordVO numero "+i);
	            			header.setIdentifier(resultadoRecordVO[i].getIdRepositorio());
	            			//if(logger.isDebugEnabled())logger.debug("El identifier es resultadoRecordVO numero "+header.getIdentifier());
	            			header.setDatestamp((resultadoRecordVO[i].getFecha()).toString());
	            			meta.setAnyObject(obtenerMetadato(parametrosRequestVO.getPrefijoMetadato(),resultadoRecordVO[i]));
	        			}
	        			aRecord[i].setRecordTypeHeader(header);
	        			aRecord[i].setMetadata(meta);
	        		}
	        		
	        		listRe.setListRecordsTypeRecord(aRecord);
	        		listRecordsAgrega.setListRecords(listRe);
	        		if(logger.isDebugEnabled())logger.debug("El listRecordsAgrega tiene ["+listRecordsAgrega.getRecords().length+ "] elementos");
	        		
	        		//ResumptionToken
	        		if(!(resultadoOAIRequest.getReanudacionToken() == null))
	    			{
	    				ReanudacionTokenVO reanudacionToken = resultadoOAIRequest.getReanudacionToken();
	    				if(logger.isDebugEnabled())logger.debug("El reanudacionToken es ["+reanudacionToken+ "]");
	    				
	    				if(reanudacionToken.getFechaExpiracion()!=null){
	    					listRecordssTypeResumptionToken.setExpirationDate(reanudacionToken.getFechaExpiracion().getTime() );
	    					if(logger.isDebugEnabled())logger.debug("La fecha de expiracion ["+listRecordssTypeResumptionToken.getExpirationDate()+ "]");
	    				}
	    				if(reanudacionToken.getTamanio()!=null){
	        				listRecordssTypeResumptionToken.setCompleteListSize((new Long((reanudacionToken.getTamanio()).toString())).longValue());
	        				if(logger.isDebugEnabled())logger.debug("El tamaino ["+listRecordssTypeResumptionToken.getCompleteListSize()+ "]");
	    				}
	    				if(reanudacionToken.getIdentificador()!=null){
	        				listRecordssTypeResumptionToken.setContent(reanudacionToken.getIdentificador());
	        				if(logger.isDebugEnabled())logger.debug("El identificador ["+listRecordssTypeResumptionToken.getContent()+ "]");
	    				}
	    				if(reanudacionToken.getCursor()!=null){
	        				listRecordssTypeResumptionToken.setCursor((new Long((reanudacionToken.getCursor()).toString())).longValue());
	        				if(logger.isDebugEnabled())logger.debug("El cursor ["+listRecordssTypeResumptionToken.getCursor()+ "]");
	    				}
	    				
	    				listRecordsAgrega.setResumptionToken(listRecordssTypeResumptionToken);
	    				if(logger.isDebugEnabled())logger.debug("Lo introducimos en el listRecordsAgrega ["+listRecordsAgrega.getResumptionToken()+"]");
	    			}
	        		oaipmh.setListRecords(listRecordsAgrega);
	        		
				}else if(resultadoOAIRequest.getVerb().equalsIgnoreCase("ListSets"))
				{
					//if(logger.isDebugEnabled())logger.debug("ListSets");
					requestOai.setVerb(VerbType.LISTSETS);
	        		ListSets listSets= new ListSets();
					SetVO[] setVO = resultadoOAIRequest.getListSets();
					for(int i=0;i<setVO.length;i++)
					{
						Set set = new Set();
						set.setSetName(setVO[i].getNombre());
						set.setSetSpec(setVO[i].getIdentificador());	
						listSets.addSet(set);
					}
					oaipmh.setListSets(listSets);
					tipoProtocolo = OaiPmhDao.LISTSETS;
				}
	        
			}else
			{
				if(!(resultadoOAIRequest.getErrorCode().equalsIgnoreCase(OAIPMHProperties.ERR_BAD_VERB)))
				{
					if(logger.isDebugEnabled())logger.debug("Se a�ade el tipo de verb a la request cuando el error no sea del tipo badVerb");
					requestOai.setVerb(VerbType.valueOf(resultadoOAIRequest.getVerb()));
					//A�ado los par�metros de la petici�n a la request
					if(!(parametrosRequestVO.getFechaDesde() == null))
						requestOai.setFrom(parametrosRequestVO.getFechaDesde());
					
					if(!(parametrosRequestVO.getFechaHasta() == null))
						requestOai.setUntil(parametrosRequestVO.getFechaHasta());
					
					if(!(parametrosRequestVO.getIdentificador() == null))
						requestOai.setIdentifier(parametrosRequestVO.getIdentificador());
					
					if(!(parametrosRequestVO.getPrefijoMetadato() == null))
						requestOai.setMetadataPrefix(parametrosRequestVO.getPrefijoMetadato());
					
					if(!(parametrosRequestVO.getCodigoPaginacion() == null))
						requestOai.setResumptionToken(parametrosRequestVO.getCodigoPaginacion());
					
					if(!(parametrosRequestVO.getIdentificadorConjunto() == null))
						requestOai.setResumptionToken(parametrosRequestVO.getIdentificadorConjunto());
				}
				if(logger.isDebugEnabled())logger.debug("la respuesta del protoco es erronea");
				oaipmh.setRequest(requestOai);
				if(logger.isDebugEnabled())logger.debug("oaipmh.getRequest() <"+oaipmh.getRequest()+">");
	    		Error error = new Error();
	    		error.setCode(OAIPMHerrorcodeType.valueOf(resultadoOAIRequest.getErrorCode()));
	    		if(logger.isDebugEnabled())logger.debug("resultadoOAIRequest.getErrorDescripcion() <"+resultadoOAIRequest.getErrorDescripcion()+">");
	    		error.setContent(resultadoOAIRequest.getErrorDescripcion());	
	    		oaipmh.addError(error);
	    		tipoProtocolo = OaiPmhDao.ERROR;
			}
	         
			//Obtenemos el xml codificado en oai-pmh
			try {
				response.setContentType("text/xml");
				response.setCharacterEncoding("ISO-8859-1");
				String ficheroTemporal = getTempFileName();
				if(logger.isDebugEnabled())logger.debug("Se escribe el xml en la response:/l TipoProtocolo <"+tipoProtocolo+">");
				//	dao.escribirOAIPMH(oaipmh, response.getOutputStream(), tipoProtocolo);
				//El xml de la respuesta se escribir� primero en un fichero para poder modificar el valor de la etiqueta <responseDate>
				if(logger.isDebugEnabled())logger.debug("FICHERO TEMPORAL "+ficheroTemporal);
				dao.escribirOAIPMH(oaipmh, new File(ficheroTemporal), tipoProtocolo, "ISO-8859-1");
				if(logger.isDebugEnabled())logger.debug("Se ha escrito el xml en el fichero");
				(response.getOutputStream()).print(obtenerFechaResponseEnXML(ficheroTemporal));
				//El fichero con el xml ser� eliminado
				UtilesFicheros.eliminar(new File(ficheroTemporal));
				if(logger.isDebugEnabled())logger.debug("Se ha eliminado correctamente el fichero xml");
			} catch (ParseadorException e1) {
				logger.error("Se ha producido una excepcion - ",e1);
			}
		}catch( Exception e){
			logger.error("ERROR en el metodo obtenerRespuestaProtocolo - ",e);
			throw e;
		}
     
	}


    /**
     * Metodo que crea nombres de ficheros temporales usando 
     * numeraciones que evitan que varias peticiones oaipmh usen el 
     * mismo fichero temporal
     */
    private String getTempFileName() {
    	Random generator = new Random();
    	//Random number between 0 and 10000
    	int randomNumber=generator.nextInt(10000);
    	String number = Integer.toString(randomNumber); 
    	return "/tmp/AGREGA_OAIPMH_tempfile."+new GregorianCalendar().getTimeInMillis()+"."+number;
    }
    
    
    /**
	 * Recoge un literal del archivo properties referenciando el codigo de
	 * dicho literal
	 * 
	 * @param sKey
	 *                codigo del literal
	 * @return String String con el literal
	 * @throws IOException
	 */
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/oaiPmh.properties");
		if (this.pOaiPmhProperties == null)	{
			pOaiPmhProperties = new java.util.Properties();
			pOaiPmhProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		return pOaiPmhProperties.getProperty(sKey);
	}

	
	/**
	 * Valida la fecha que recibe como par�metro
	 * @param sKey fecha a validar
	 * @return Boolean
	 * @throws IOException
	 */
	private String obtenerFechaValida(Date fecha) throws IOException{
		if(logger.isDebugEnabled()){
			logger.debug("granularidad <"+OAIPMHProperties.getPropertyValue(OAIPMHProperties.VALUE_GRANULARIDAD_YYYYMMDD)+">");
		}
		//SimpleDateFormat formatter = new SimpleDateFormat(OAIPMHProperties.getPropertyValue(OAIPMHProperties.VALUE_GRANULARIDAD_YYYYMMDD));
		String resultado = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			sdf.applyPattern("yyyy-MM-dd'T'hh:mm:ss'Z'");
			resultado = sdf.format(new Date());
			if(logger.isDebugEnabled())logger.debug("la fecha ahora vale <"+resultado+">");
		}
		catch (Exception pe) {
			logger.error("excepcion en la validacion de la fecha - ",pe);
		}
		return resultado;
	}
	 
	 
	/**
	* Valida la fecha que recibe como par�metro
	* @param sKey fecha a validar
	* @return Boolean
	* @throws IOException
	*/
	 private boolean validaFecha(String str) throws IOException {
		String granularidad = null;
		granularidad = OAIPMHProperties.getPropertyValue(OAIPMHProperties.KEY_GRANULARIDAD_FORMATER);
		if(logger.isDebugEnabled())logger.debug("granularidad <"+granularidad+">");
		SimpleDateFormat formatter = new SimpleDateFormat(granularidad);
		if(logger.isDebugEnabled())logger.debug("La fecha a validar es <"+str+">");
		
		//Validamos la longitud de la fecha para ver si concuerda con el formato
		if(!(str.length() == granularidad.length())) {
			if(logger.isDebugEnabled())logger.debug("Tienen distinta longitud no se corresponde con la granularidad");
			return false;
		}
		try {
			Date fechaValidada = formatter.parse(str);
			if(logger.isDebugEnabled())logger.debug("fechaValida <"+fechaValidada+">");
		} catch (ParseException pe) {
			logger.error("excepcion en la validacion de la fecha - ",pe);
			return false;
		}
		return true;
	}
			 
	 
	/**
	* Valida si cada llamada del protocolo recibe los parametros que necesita
	* @param  parametros VO con los datos que se pasan por par�metro
	* @param  tipoPeticion m�todo del protocolo Oai-Pmh
	* @return String
	*/
	 private String validaParametros(ParametrosRequestVO parametros, String tipoPeticion)
	 {
		if(parametros.getParametros().size() > 0)
		{
			if(logger.isDebugEnabled())logger.debug("En la peticion nos han pasado parametros no reconocidos por el repositorio");
			return "badArgument";
		}
		if(tipoPeticion.equalsIgnoreCase("GetRecord"))
		{
			if(!(parametros.getCodigoPaginacion() == null) || !(parametros.getFechaDesde() == null) || !(parametros.getFechaHasta() == null))
			{
				if(logger.isDebugEnabled())logger.debug("Se pasan al metodo GetRecord mas parametros de los necesarios");
				return "badArgument";
			}
		}
		if((tipoPeticion.equalsIgnoreCase("ListRecords")) || (tipoPeticion.equalsIgnoreCase("ListIdentifiers")))
		{
			 if(parametros.getIdentificador() != null)
			 {
				 if(logger.isDebugEnabled())logger.debug("Se pasan al metodo ListRecords o ListIdentifiers mas parametros de los necesarios");
				 return "badArgument";
			 }
		}
		if(tipoPeticion.equalsIgnoreCase("ListSets"))
		{
			if(!(parametros.getFechaDesde() == null) || !(parametros.getFechaHasta() == null) || !(parametros.getIdentificador() == null) || !(parametros.getIdentificadorConjunto() == null) || !(parametros.getPrefijoMetadato() == null))
			{
				if(logger.isDebugEnabled())logger.debug("Se pasan al metodo ListSets mas parametros de los necesarios");
				return "badArgument";
			}
		}
		if(tipoPeticion.equalsIgnoreCase("Identify"))
		{
			if(!(parametros.getCodigoPaginacion() ==null) || !(parametros.getFechaDesde() ==null) || !(parametros.getFechaHasta() ==null) || !(parametros.getIdentificador() ==null) || !(parametros.getIdentificadorConjunto() ==null) || !(parametros.getPrefijoMetadato() ==null))
			{
				if(logger.isDebugEnabled())logger.debug("Se pasan al metodo Identify mas parametros de los necesarios");
				return "badArgument";
			}
		}
		if(tipoPeticion.equalsIgnoreCase("ListMetadataFormats"))
		{
			if(!(parametros.getCodigoPaginacion() ==null) || !(parametros.getFechaDesde() ==null) || !(parametros.getFechaHasta() ==null) || !(parametros.getIdentificadorConjunto() ==null) || !(parametros.getPrefijoMetadato() ==null))
			{
				if(logger.isDebugEnabled())logger.debug("Se pasan al metodo ListMetadataFormats mas parametros de los necesarios");
				return "badArgument";
			}
		}
		return "ok";
	 }
	 
	 
	 private String obtenerCampoFnDeVcard(String vcard) {
		 if(vcard==null)
			 return null;
		 if(vcard.isEmpty())
			 return "";
		 
		 String[] tmp = vcard.split("FN:");
		 tmp = tmp[1].split("EMAIL;TYPE=INTERNET:");
		 return tmp[0];
	 }
	 		
		
	 /**
	  * Mapea un objeto lomes a dublin core
	  * @param lenguage
	  * @param lom
	  * @return
	  */	 
	private Object transformadorLomes2DC(String lenguage, Lom lom)
	{
		Object resultado = null;	 
		Dc dublincore = new Dc();
		LomAgrega lomAgrega = new LomAgrega(lom);
		
		try{
			
			String idioma = null;

	       	//language
       	 	if(lomAgrega.getMetaMetadataAgrega()!=null && lomAgrega.getMetaMetadataAgrega().getIdioma()!=null)
       	 	{
       	 		idioma = lomAgrega.getMetaMetadataAgrega().getIdioma();
       	 		
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcLanguage language = new DcLanguage();
       	 		language.setContent(idioma);
       	 		logger.debug("a�adir el idioma ["+language.getContent()+"]");
       	 		item.setDcLanguage(language);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	 	
       	 	if(idioma==null)
       	 		idioma="es";
			
			//title
			if(lomAgrega.getGeneralAgrega().getTitulo(idioma)!=null)
			{
				Oai_dcTypeItem item = new Oai_dcTypeItem();
				DcTitle titulo =new DcTitle();
				titulo.setContent(lomAgrega.getGeneralAgrega().getTitulo(idioma));
				logger.debug("Se a�ade el titulo <"+titulo.getContent()+">");
				item.setDcTitle(titulo);
				dublincore.addOai_dcTypeItem(item);
			}
			
			//creator
			if(lom.getLifeCycle()!=null && lom.getLifeCycle().getGroupLifeCycleLifeCycle()!=null)
			{
       	 		for(int j=0;j<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContributeCount();j++)//Todos los autores en uno
       	 		{
       	 			String rol = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent();
       	 			logger.debug("LUIS rol "+rol);
       	 			
       	 			if(!rol.contentEquals("author"))
       	 				continue;
       	 			
       	 			for(int k=0; k<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnboundedCount(); k++) {
       	 				String autor = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnbounded(k).getGroupEntityUnboundedEntity().getContent();
       	 				autor = obtenerCampoFnDeVcard(autor);
       	 				if(autor!=null && !autor.isEmpty()) {
       	 					Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	       	 		DcCreator creador = new DcCreator();
	       		 			creador.setContent(autor);
	       		 			item.setDcCreator(creador);
	       		 			dublincore.addOai_dcTypeItem(item);
	       	 				logger.debug("El autor numero "+j+"-"+k+" es: ["+autor+"]");
       	 				}
       	 			}
       	 		}
			}
			
       	 	//subject
       	 	if(lom.getGeneral().getGroupGeneralGeneral().getKeywordCount()>0 && lom.getGeneral().getGroupGeneralGeneral().getKeyword()!=null)
       	 	{
	       	 	logger.debug("a�adir el subject");
       	 		for(int j=0; j<lom.getGeneral().getGroupGeneralGeneral().getKeywordCount(); j++)
       	 		{
	       	 		Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
	       	 		DcSubject subject = new DcSubject();
	       	 		String s = lom.getGeneral().getGroupGeneralGeneral().getKeyword()[j].getGroupKeywordKeyword().getLanguageStringItem(0).getString().getContent();
	       	 		if(s!=null && !s.isEmpty()) {
		       	 		subject.setContent(s);
		       	 		logger.debug("El tema numero "+j+" es ["+subject.getContent()+"]");
		       	 		itemAut.setDcSubject(subject);
		       	 		dublincore.addOai_dcTypeItem(itemAut);
	       	 		}
       	 		}
	       	}
       	 	
       	 	//coverage (1.6)
       	 	if(lom.getGeneral().getGroupGeneralGeneral().getCoverage() != null)
       	 	{
	       	 	for(int j=0;j<lom.getGeneral().getGroupGeneralGeneral().getCoverageCount();j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcCoverage coverage = new DcCoverage();
	       	 		coverage.setContent(lom.getGeneral().getGroupGeneralGeneral().getCoverage(j).getGroupCoverageCoverage().getLanguageStringItem(0).getString().getContent());
	       	 		logger.debug("El ambito numero "+j+" es ["+coverage.getContent()+"]");
	       	 		item.setDcCoverage(coverage);
	       	 		dublincore.addOai_dcTypeItem(item);
	       	 	}
       	 	
       	 	}
			
       	 	//contributor
			if(lom.getLifeCycle()!=null && lom.getLifeCycle().getGroupLifeCycleLifeCycle()!=null)
			{
       	 		for(int j=0;j<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContributeCount();j++)//Todos los autores en uno
       	 		{
       	 			String rol = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent();
       	 			
       	 			if(!rol.contentEquals("contributor"))
       	 				continue;
       	 			
       	 			for(int k=0; k<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnboundedCount(); k++) {
       	 				String contributor = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnbounded(k).getGroupEntityUnboundedEntity().getContent();
       	 				contributor = obtenerCampoFnDeVcard(contributor);
       	 				if(contributor!=null && !contributor.isEmpty()) {
       	 					Oai_dcTypeItem item = new Oai_dcTypeItem();
       		       	 		DcContributor contrib = new DcContributor();
       		       	 		contrib.setContent(contributor);
	       		 			item.setDcContributor(contrib);
	       		 			dublincore.addOai_dcTypeItem(item);
	       	 				logger.debug("El contribuidor numero "+j+"-"+k+" es: ["+contributor+"]");
       	 				}
       	 			}
       	 		}
			}
       	 	
       	 	//rights
       	 	if(lom.getRights()!=null &&
       	 		lom.getRights().getGroupRightsRights()!=null &&
       	 			lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions()!=null &&
       	 				lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions().getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions()!=null &&
       	 					lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions().getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue()!=null) {
       	 			
       	 		String derechos = lom.getRights().getGroupRightsRights().getCopyrightAndOtherRestrictions().getGroupCopyrightAndOtherRestrictionsCopyrightAndOtherRestrictions().getComplexTypeCopyrightAndOtherRestrictionsVocabValue().getContent();
       	 	
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcRights rights = new DcRights();
       	 		rights.setContent(derechos);
       	 		item.setDcRights(rights);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	 	
			//description
       	 	if(lomAgrega.getGeneralAgrega().getDescripcionesIdioma(idioma)!=null)
       	 	{
       	 		logger.debug("a�adir la description");
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcDescription descripcion = new DcDescription();
       	 		descripcion.setContent(lomAgrega.getGeneralAgrega().getDescripcion(0, idioma));
       	 		logger.debug(" descripcion.getContent() "+descripcion.getContent());
       	 		item.setDcDescription(descripcion);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	 	
       	 	//date (fecha de ultima publicacion)
       	 	/*
       	 	if(!(resultadoRecordVO.getFecha() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir el date");
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcDate date = new DcDate();
       	 		date.setContent((resultadoRecordVO.getFecha()).toString());
       	 		if(logger.isDebugEnabled())logger.debug(" date.getContent() "+date.getContent());
       	 		item.setDcDate(date);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	 	*/
       	 	
       	 	//format
       	 	if(lom.getTechnical()!=null && lom.getTechnical().getGroupTechnicalTechnical()!=null)
       	 	{
	       	 	for(int j=0;j<lom.getTechnical().getGroupTechnicalTechnical().getFormatCount();j++)
	       	 	{	       	 		
	       	 		String formato = lom.getTechnical().getGroupTechnicalTechnical().getFormat(j).getGroupFormatFormat().getContent();
	       	 		Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
	       	 		DcFormat format = new DcFormat();
	       	 		format.setContent(formato);
	       	 		logger.debug("El formato numero "+j+" es ["+formato+"]");
	       	 		itemAut.setDcFormat(format);
	       	 		dublincore.addOai_dcTypeItem(itemAut);
	       	 	}
       	 	}
       	 	/*
       	//source
       	 	if(!(resultadoRecordVO.getFuente() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los sources");
	       	 	for(int j=0;j<resultadoRecordVO.getFuente().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcSource source = new DcSource();
	       	 		source.setContent((resultadoRecordVO.getFuente()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("La fuente "+j+" es ["+source.getContent()+"]");
	       	 		item.setDcSource(source);
	       	 		dublincore.addOai_dcTypeItem(item);
	       	 	}
       	 	
       	 	}
       	 	*/
       	 	
       	 	//publisher
			if(lom.getLifeCycle()!=null && lom.getLifeCycle().getGroupLifeCycleLifeCycle()!=null)
			{
       	 		for(int j=0;j<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContributeCount();j++)//Todos los autores en uno
       	 		{
       	 			String rol = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getRole().getGroupRoleRole().getComplexTypeRoleVocabValue().getContent();
       	 			
       	 			if(!rol.contentEquals("publisher"))
       	 				continue;
       	 			
       	 			for(int k=0; k<lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnboundedCount(); k++) {
       	 				String publicador = lom.getLifeCycle().getGroupLifeCycleLifeCycle().getContribute(j).getGroupContributeContribute().getEntityUnbounded(k).getGroupEntityUnboundedEntity().getContent();
       	 				publicador = obtenerCampoFnDeVcard(publicador);
       	 				if(publicador!=null && !publicador.isEmpty()) {
       	 					Oai_dcTypeItem item = new Oai_dcTypeItem();
							DcPublisher publisher = new DcPublisher();
							publisher.setContent(publicador);
							item.setDcPublisher(publisher);
							dublincore.addOai_dcTypeItem(item);
	       	 				logger.debug("El publicador numero "+j+"-"+k+" es: ["+publicador+"]");
       	 				}
       	 			}
       	 		}
			}

       		//relation
       	 	if(lom.getRelationCount()>0 && lom.getRelation()!=null)
       	 	{
       	 		logger.debug("a�adir los relation");
	       	 	for(int j=0; j<lom.getRelationCount(); j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcRelation relation = new DcRelation();
	       	 		relation.setContent(lom.getRelation()[j].getGroupRelationRelation().getResource().getGroupResourceResource().getIdentifier().getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent());
	       	 		logger.debug("La relacion "+j+" es ["+relation.getContent()+"]");
	       	 		item.setDcRelation(relation);
	       	 		dublincore.addOai_dcTypeItem(item);
	        	}
       	 	}
       	
       		//type
       	 	if(lom.getEducationalCount()>0 && lom.getEducational()!=null)
       	 	{
	       	 	for(int j=0; j<lom.getEducationalCount(); j++)
	       	 	{
		       	 	for(int k=0; k<lom.getEducational()[j].getGroupEducationalEducational().getLearningResourceTypeCount(); k++)
		       	 	{
		       	 		String tipoRecursoEducativo = lom.getEducational()[j].getGroupEducationalEducational().getLearningResourceType(k).getGroupLearningResourceTypeLearningResourceType().getComplexTypeLearningResourceTypeVocabValue().getContent();
			       	 	Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
			       	 	DcType type = new DcType();
		    	 		type.setContent(tipoRecursoEducativo);
		    	 		logger.debug("El tipo "+j+"-"+k+" es ["+tipoRecursoEducativo+"]");
			       	 	itemAut.setDcType(type);
			   	 		dublincore.addOai_dcTypeItem(itemAut);
		       	 	}
	        	}
       	 	}
       	 	
       	 	//identifier
   	 		logger.debug("a�adir los identifier");
   	 		for(int j=0;j<lom.getGeneral().getGroupGeneralGeneral().getIdentifierCount();j++)
   	 		{
   	 			Oai_dcTypeItem item = new Oai_dcTypeItem();
   	 			DcIdentifier identifier = new DcIdentifier();
   	 			String id = lom.getGeneral().getGroupGeneralGeneral().getIdentifier(j).getGroupIdentifierIdentifier().getEntry().getGroupEntryEntry().getContent();
   	 			if(id!=null && !id.isEmpty()) {
	   	 			identifier.setContent(id);
	   	 			logger.debug("El identificador "+j+" es ["+identifier.getContent()+"]");
	   	 			item.setDcIdentifier(identifier);
	   	 			dublincore.addOai_dcTypeItem(item);
   	 			}
   	 		}
       	 	
       	 	resultado = dublincore;
		} catch (Exception e) {
			logger.error("ERROR ", e);
		}
		return resultado;
	}
	 
	 
	 /**
		* Devuelve el tipo de metadato codificado en el lenguage que se pase por par�metro: dublincore, perseus,...
		* @param  resultadoRecordVO VO con la informaci�n del registro
		* @param  lenguage String con el lenguage en el que se codificar� la etiqueta metadato
		* @return Object 
		*/
	 private Object obtenerMetadato(String lenguage,ResultadoRecordVO resultadoRecordVO)
	 {
		
		 Object resultado = null;
		 if((lenguage == null)||(lenguage.equalsIgnoreCase(OAIPMHProperties.VALUE_METADATO_DC))) //El lenguage puede ser null en aquellas peticiones que nos pasen el resumptionToken
		 {
			 
			 if(logger.isDebugEnabled())logger.debug("METADATOS EN DUBLINCORE"); 
		 //METADATOS EN DUBLIN CORE
			Dc dublincore = new Dc();
			
			//title
			if(!(resultadoRecordVO.getTitulo() == null))
			{
//				if(logger.isDebugEnabled())logger.debug("a�adir el titulo");
				Oai_dcTypeItem item = new Oai_dcTypeItem();
				DcTitle titulo =new DcTitle();
				titulo.setContent(resultadoRecordVO.getTitulo());
				if(logger.isDebugEnabled())logger.debug("Se a�ade el titulo <"+titulo.getContent()+">");
				item.setDcTitle(titulo);
				dublincore.addOai_dcTypeItem(item);
			}
			
			//creator
			if(!(resultadoRecordVO.getAutores() == null))
			{
				Oai_dcTypeItem item = new Oai_dcTypeItem();
//				if(logger.isDebugEnabled())logger.debug("a�adir el creator");
				String autores="";
       	 		for(int j=0;j<resultadoRecordVO.getAutores().length;j++)//Todos los autores en uno
       	 		{
       	 			autores=autores+resultadoRecordVO.getAutores()[j]+" ";	
       	 			if(logger.isDebugEnabled())logger.debug("El autor numero "+j+" es: ["+resultadoRecordVO.getAutores()[j]+"]");
       	 		}
       	 		
       	 		DcCreator creador = new DcCreator();
	 			creador.setContent(autores);
	 			item.setDcCreator(creador);
	 			dublincore.addOai_dcTypeItem(item);
//				for(int j=0;j<resultadoRecordVO.getAutores().length;j++)//Los autores uno a uno
//       	 		{
//					Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
//	       	 		DcCreator creator = new DcCreator();
//	       	 		creator.setContent((resultadoRecordVO.getAutores()[j]));
//	       	 		itemAut.setDcCreator(creator);
//	       	 		dublincore.addOai_dcTypeItem(itemAut);
//       	 		}
			}
			
       	 	//subject
       	 	if(!(resultadoRecordVO.getTema() == null))
       	 	{
	       	 	if(logger.isDebugEnabled())logger.debug("a�adir el subject");
       	 		for(int j=0;j<resultadoRecordVO.getTema().length;j++)
       	 		{
	       	 		Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
	       	 		DcSubject subject = new DcSubject();
	       	 		subject.setContent((resultadoRecordVO.getTema()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("El tema numero "+j+" es ["+subject.getContent()+"]");
	       	 		itemAut.setDcSubject(subject);
	       	 		dublincore.addOai_dcTypeItem(itemAut);
    		
       	 		}
	       	 }
       	 	
       	 	//coverage
       	 	if(!(resultadoRecordVO.getAmbito() == null))
       	 	{
	       	 	if(logger.isDebugEnabled())logger.debug("a�adir el coverage");
	       	 	for(int j=0;j<resultadoRecordVO.getAmbito().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcCoverage coverage = new DcCoverage();
	       	 		coverage.setContent((resultadoRecordVO.getAmbito()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("El ambito numero "+j+" es ["+coverage.getContent()+"]");
	       	 		item.setDcCoverage(coverage);
	       	 		dublincore.addOai_dcTypeItem(item);
	       	 	}
       	 	
       	 	}
       	 //contributor
       	 	if(!(resultadoRecordVO.getContribuidor() == null))
       	 	{
	       	 	if(logger.isDebugEnabled())logger.debug("a�adir el contributor");
	       	 	for(int j=0;j<resultadoRecordVO.getContribuidor().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcContributor contributor = new DcContributor();
	       	 		contributor.setContent((resultadoRecordVO.getContribuidor()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("La contribucion numero "+j+" es ["+contributor.getContent()+"]");
	       	 		item.setDcContributor(contributor);
	       	 		dublincore.addOai_dcTypeItem(item);
	       	 	}
       	 	
       	 	}
       	 //rights
       	 	if(!(resultadoRecordVO.getDerechos() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los derechos, (rights)");
	       	 	for(int j=0;j<resultadoRecordVO.getDerechos().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcRights rights = new DcRights();
	       	 		rights.setContent((resultadoRecordVO.getDerechos()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("El derecho numero "+j+" es ["+rights.getContent()+"]");
	       	 		item.setDcRights(rights);
	       	 		dublincore.addOai_dcTypeItem(item);
	  		
	       	 	}
       	 	
       	 	}
       	//description
       	 	if(!(resultadoRecordVO.getDescripcion() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir la description");
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcDescription descripcion = new DcDescription();
       	 		descripcion.setContent(resultadoRecordVO.getDescripcion());
       	 		if(logger.isDebugEnabled())logger.debug(" descripcion.getContent() "+descripcion.getContent());
       	 		item.setDcDescription(descripcion);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	//date
       	 	if(!(resultadoRecordVO.getFecha() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir el date");
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcDate date = new DcDate();
       	 		date.setContent((resultadoRecordVO.getFecha()).toString());
       	 		if(logger.isDebugEnabled())logger.debug(" date.getContent() "+date.getContent());
       	 		item.setDcDate(date);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	//format
       	 	if(!(resultadoRecordVO.getFormatos() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los format");
	       	 	for(int j=0;j<resultadoRecordVO.getFormatos().length;j++)
	       	 	{
	       	 		
	       	 		Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
	       	 		DcFormat format = new DcFormat();
	       	 		format.setContent((resultadoRecordVO.getFormatos()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("El formato numero "+j+" es ["+format.getContent()+"]");
	       	 		itemAut.setDcFormat(format);
	       	 		dublincore.addOai_dcTypeItem(itemAut);
	       	 	}
       	 	}
       	//source
       	 	if(!(resultadoRecordVO.getFuente() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los sources");
	       	 	for(int j=0;j<resultadoRecordVO.getFuente().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcSource source = new DcSource();
	       	 		source.setContent((resultadoRecordVO.getFuente()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("La fuente "+j+" es ["+source.getContent()+"]");
	       	 		item.setDcSource(source);
	       	 		dublincore.addOai_dcTypeItem(item);
	       	 	}
       	 	
       	 	}
       	//publisher
       	 	if(!(resultadoRecordVO.getPublicador() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los publisher");
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		String publicadores="";
	       	 	for(int j=0;j<resultadoRecordVO.getPublicador().length;j++)
	       	 	{
	       	 		publicadores=publicadores+resultadoRecordVO.getPublicador()[j]+" ";//Todos los publicadores a la vez
	       	 		if(logger.isDebugEnabled())logger.debug("El publicador numero "+j+" es:["+resultadoRecordVO.getPublicador()[j]+"]");
//		       	 	Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
//		       	 	DcPublisher publisher = new DcPublisher();
//		       	 	publisher.setContent((resultadoRecordVO.getPublicador()[j]));
//		       	 	itemAut.setDcPublisher(publisher);
//		   	 		dublincore.addOai_dcTypeItem(itemAut);
	        	}
	       	 DcPublisher publisher = new DcPublisher();
	       	 publisher.setContent(publicadores);
	       	 item.setDcPublisher(publisher);
	       	 dublincore.addOai_dcTypeItem(item);
       	 	}
       	//relation
       	 	if(!(resultadoRecordVO.getRelacion() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los relation");
	       	 	for(int j=0;j<resultadoRecordVO.getRelacion().length;j++)
	       	 	{
	       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
	       	 		DcRelation relation = new DcRelation();
	       	 		relation.setContent((resultadoRecordVO.getRelacion()[j]));
	       	 		if(logger.isDebugEnabled())logger.debug("La relacion "+j+" es ["+relation.getContent()+"]");
	       	 		item.setDcRelation(relation);
	       	 		dublincore.addOai_dcTypeItem(item);
	        	}
       	 	
       	 	}
       	
       	//type
       	 	if(!(resultadoRecordVO.getTipo() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los type");
	       	 	for(int j=0;j<resultadoRecordVO.getTipo().length;j++)
	       	 	{
		       	 	Oai_dcTypeItem itemAut = new Oai_dcTypeItem();
		       	 	DcType type = new DcType();
	    	 		type.setContent((resultadoRecordVO.getTipo()[j]));
	    	 		if(logger.isDebugEnabled())logger.debug("El tipo "+j+" es ["+type.getContent()+"]");
		       	 	itemAut.setDcType(type);
		   	 		dublincore.addOai_dcTypeItem(itemAut);
	        	}
       	 	}
       	//identifier
       	 	if(!(resultadoRecordVO.getIdentificador() == null))
       	 	{
       	 		if(logger.isDebugEnabled())logger.debug("a�adir los identifier");
       	 		for(int j=0;j<resultadoRecordVO.getIdentificador().length;j++)
       	 		{
       	 			Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 			DcIdentifier identifier = new DcIdentifier();
       	 			identifier.setContent(resultadoRecordVO.getIdentificador()[j]);
       	 			if(logger.isDebugEnabled())logger.debug("El identificador "+j+" es ["+identifier.getContent()+"]");
       	 			item.setDcIdentifier(identifier);
       	 			dublincore.addOai_dcTypeItem(item);
       	 		}
       	 		
       	 	}
       	//language
       	 	if(!(resultadoRecordVO.getIdioma() == null))
       	 	{
       	 		Oai_dcTypeItem item = new Oai_dcTypeItem();
       	 		DcLanguage language = new DcLanguage();
       	 		language.setContent(resultadoRecordVO.getIdioma());
       	 		if(logger.isDebugEnabled())logger.debug("a�adir el idioma ["+language.getContent()+"]");
       	 		item.setDcLanguage(language);
       	 		dublincore.addOai_dcTypeItem(item);
       	 	}
       	 	
       	 	resultado = dublincore;
		 }
       	
       	return resultado;
	 }
	 
	 
	/**
	* Obtiene el tipo Date asociado a la cadena que se manda por par�metro
	* @param  String con la fecha
	* @return Date 
	*/
	private Date getFecha(String fecha) {
		Date resultado = null;
		if(!(fecha == null)) {
			int anio = new Integer(fecha.substring(0,4)).intValue();
			int mes = new Integer(fecha.substring(5,7)).intValue() -1;
			int dia = new Integer(fecha.substring(8,10)).intValue();
			resultado = (new GregorianCalendar(anio,mes,dia)).getTime();
		}
		if(logger.isDebugEnabled())logger.debug("la fecha resultado: <"+resultado+">");
		return resultado;
	}
 
	 
	private boolean comparacionFechas(String fDesde, String fHasta) {
		Date fechaDesde = null;
		Date fechaHasta = null;
				
		if(!(fDesde == null)) {
			fechaDesde = this.getFecha(fDesde);
			if(logger.isDebugEnabled())logger.debug("fecha Desde :" + fechaDesde.toString());
		}
		if(!(fHasta == null)) {
			fechaHasta = this.getFecha(fHasta);
			if(logger.isDebugEnabled())logger.debug("fecha Hasta :" + fechaHasta.toString());
		}
		if(fechaDesde == null || fechaHasta == null || fechaDesde.before(fechaHasta) || fechaDesde.equals(fechaHasta))
			return true;
		return false;
	}
	 
	 
	private String obtenerFechaResponseEnXML(String ficheroTemporal) throws IOException {
		String fecha = obtenerFechaValida(new Date());
		String str = FileUtils.readFileToString(new File(ficheroTemporal));
		return str.replaceFirst("<responseDate>.*</responseDate>", "<responseDate>"+fecha+"</responseDate>");
	}

}