package es.pode.oaipmh.negocio.resumptionToken;

import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import es.pode.oaipmh.negocio.servicios.ResumptionTokenCacheHit;
import es.pode.oaipmh.soporte.OAIPMHProperties;
import es.pode.soporte.utiles.date.DateManager;

public class ResumptionTokenManager {
	
	private Properties props = null;
	private CacheManager cacheManager;
	private Cache resumptionToken;	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static final String FROM = "From";
	public static final String TO = "to";	
	public static final String PAG = "pag";	
	
	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public Cache getResumptionToken() {
		return resumptionToken;
	}

	public void setResumptionToken(Cache resumptionToken) {
		this.resumptionToken = resumptionToken;
	}	
	
	public ResumptionTokenManager()
	{
			
		InputStream is = null;
		try {
	//		Configuramos la clase de propiedades
			is = this.getClass().getResourceAsStream("/oaiPmh.properties");
			props = new Properties();
			props.load(is);
	//		Configuramos la cache de resumption token
			cacheManager= CacheManager.create();
			resumptionToken = new Cache("resumptionToken", //name
					100, //maxElementsInMemory
					false, //overflowToDisk
					false, //eternal
					new Long(props.getProperty("segundosCaducidad")).longValue(),//tiempo de vida de los elementos en la cache
					new Long(props.getProperty("segundosCaducidad")).longValue());//tiempo de vida de los elementos desde su ultimo acceso o su ultima modificacion
			cacheManager.addCache(resumptionToken);			
			
		} catch (IOException e) {
			logger.error("ERROR: Accediendo a la cache manager",e);
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
	
	public Element devuelveElementoResumptionToken(String identificador) throws Exception	{
		
		Element elemento = null;
		if (logger.isDebugEnabled())logger.debug("comprobarIdResumptionToken-- Buscando en cache con identificador["+identificador+"]");
		elemento = this.getResumptionToken().get(identificador);		
		return elemento;
		
	}
	
	public String devuelveClaveResumptionToken() throws Exception
	{		
		return(String.valueOf(System.currentTimeMillis()));		
    	
	}
	
	public ResumptionTokenCacheHit generateFirstResumptionToken (String idquery, String idConsulta, Integer numResultadosTotales) throws Exception
	{
//		No habia resumption token anterior	
		
		//Declaracion de variables
		//Numero maximo de resultados por pagina
		Integer numMaxPaginaInteger = new Integer(OAIPMHProperties.getPropertyValue(OAIPMHProperties.NUM_RESULT_PAG));				
		int numMaxPaginaInt = numMaxPaginaInteger.intValue();		
		ResumptionTokenCacheHit tokenCacheHit = new ResumptionTokenCacheHit();
		Date fechaActual = new Date();			
		long tiempoExpiracion = new Long(props.getProperty("tiempoExpiracion")).longValue();
		
//		Se genera un identificador de resumption token porque hay una nueva pagina
		String claveToken = devuelveClaveResumptionToken();		

		//Se rellena el vo
		tokenCacheHit.setDesde(new Integer(numMaxPaginaInt));
		tokenCacheHit.setHasta(new Integer(numMaxPaginaInt+(numMaxPaginaInt-1)));			
		tokenCacheHit.setFechaExpiracion(DateManager.dateToCalendar(new Date(fechaActual.getTime() + tiempoExpiracion)));
		tokenCacheHit.setIdConsulta(idConsulta);
		tokenCacheHit.setIdQuery(idquery);
		tokenCacheHit.setIdResumptionToken(claveToken);	
		
		if (logger.isDebugEnabled()) logger.debug("generateFirstResumptionToken-- El vo de resumptionTodenCacheHit tiene los siguientes valores:" +
				"IdQuery: ["+tokenCacheHit.getIdQuery()+"], " +
				"Desde: ["+tokenCacheHit.getDesde()+"], " +
				"Hasta: ["+tokenCacheHit.getHasta()+"], " +
				"FechaExpiracion: ["+tokenCacheHit.getFechaExpiracion()+"], " +
				"IdConsulta: ["+tokenCacheHit.getIdConsulta()+"], " +
				"IdResumptionToken: ["+tokenCacheHit.getIdResumptionToken()+"]");	
		this.getResumptionToken().put(new Element(claveToken,tokenCacheHit));		
		
		return tokenCacheHit;
	}
	
	public ResumptionTokenCacheHit generateNextResumptionToken(ResumptionTokenCacheHit resumptionTokenOld, Integer numResultadosTotales) throws Exception
	{
		//Declaracion de variables
		ResumptionTokenCacheHit tokenCacheHit = new ResumptionTokenCacheHit();
		Date fechaActual = new Date();			
		long tiempoExpiracion = new Long(props.getProperty("tiempoExpiracion")).longValue();		
		
		//Numero maximo de resultados por pagina		
		Integer numMaxPaginaInteger = new Integer(OAIPMHProperties.getPropertyValue(OAIPMHProperties.NUM_RESULT_PAG));				
		int numMaxPaginaInt = numMaxPaginaInteger.intValue();	
		
//			Se calcula el hasta
		Integer hasta = new Integer(resumptionTokenOld.getHasta().intValue() + numMaxPaginaInt);
			
			if(hasta.intValue() <= (numResultadosTotales.intValue()-1))
			{
//				Se genera un identificador de resumption token porque hay una nueva pagina
				String claveToken = devuelveClaveResumptionToken();

				if (logger.isDebugEnabled()) logger.debug("generateNextResumptionToken-- El valor de la claveToken es ["+claveToken+"]");
				//Se construye el vo de resumptionTokenCacheHit		
				tokenCacheHit.setDesde(new Integer(resumptionTokenOld.getHasta().intValue() + 1));
				tokenCacheHit.setHasta(hasta);			
				tokenCacheHit.setFechaExpiracion(DateManager.dateToCalendar(new Date(fechaActual.getTime() + tiempoExpiracion)));
				tokenCacheHit.setIdConsulta(resumptionTokenOld.getIdConsulta());
				tokenCacheHit.setIdQuery(resumptionTokenOld.getIdQuery());
				tokenCacheHit.setIdResumptionToken(claveToken);				
				
				if (logger.isDebugEnabled()) logger.debug("generateNextResumptionToken-- El vo de resumptionTodenCacheHit tiene los siguientes valores:" +
															"IdQuery: ["+tokenCacheHit.getIdQuery()+"], " +
															"Desde: ["+tokenCacheHit.getDesde()+"], " +
															"Hasta: ["+tokenCacheHit.getHasta()+"], " +
															"FechaExpiracion: ["+tokenCacheHit.getFechaExpiracion()+"], " +
															"IdConsulta: ["+tokenCacheHit.getIdConsulta()+"], " +
															"IdResumptionToken: ["+tokenCacheHit.getIdResumptionToken()+"]");	
				this.getResumptionToken().put(new Element(claveToken,tokenCacheHit));
			}
			else
				tokenCacheHit = null;
		
		
		//Se retorna el vo del resumptionToken
		return tokenCacheHit;
	}
	
	public ResumptionTokenCacheHit generateResumptionToken(Integer desde, Integer hasta, String idResumptionToken)
    throws Exception
{
    ResumptionTokenCacheHit tokenCacheHit = new ResumptionTokenCacheHit();
    Date fechaActual = new Date();
    long tiempoExpiracion = (new Long(props.getProperty("tiempoExpiracion"))).longValue();
    tokenCacheHit.setFechaExpiracion(DateManager.dateToCalendar(new Date(fechaActual.getTime() + tiempoExpiracion)));
    tokenCacheHit.setDesde(desde);
    tokenCacheHit.setHasta(hasta);
    tokenCacheHit.setIdResumptionToken(idResumptionToken);
    if(logger.isDebugEnabled())
    {
        logger.debug((new StringBuilder()).append("generateFirstResumptionToken-- El vo de resumptionTodenCacheHit tiene los siguie" +
"ntes valores:IdQuery: ["
).append(tokenCacheHit.getIdQuery()).append("], ").append("Desde: [").append(tokenCacheHit.getDesde()).append("], ").append("Hasta: [").append(tokenCacheHit.getHasta()).append("], ").append("FechaExpiracion: [").append(tokenCacheHit.getFechaExpiracion()).append("], ").append("IdConsulta: [").append(tokenCacheHit.getIdConsulta()).append("], ").append("IdResumptionToken: [").append(tokenCacheHit.getIdResumptionToken()).append("]").toString());
    }
    getResumptionToken().put(new Element(idResumptionToken, tokenCacheHit));
    return tokenCacheHit;
}
	
	public void almacenarResumptionToken(ResumptionTokenCacheHit resumptionToken)
    {
        String idResumptionToken = resumptionToken.getIdResumptionToken();
        getResumptionToken().put(new Element(idResumptionToken, resumptionToken));
    }	
	

}
