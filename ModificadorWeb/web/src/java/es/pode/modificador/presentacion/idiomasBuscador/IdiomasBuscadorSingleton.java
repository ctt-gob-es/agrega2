package es.pode.modificador.presentacion.idiomasBuscador;

//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

//import es.pode.indexador.negocio.servicios.busqueda.LocalizacionIdiomaVO;

public class IdiomasBuscadorSingleton {

	private static IdiomasBuscadorSingleton instance = null;
	private static HashMap mapaIdiomas = new HashMap(6); 
	private static final Logger logger = Logger.getLogger(IdiomasBuscadorSingleton.class);
	
	protected IdiomasBuscadorSingleton() {
		super();
	}
	
	public static IdiomasBuscadorSingleton getInstance() {
		if(instance == null ) {
			instance = new IdiomasBuscadorSingleton();
		}
		return instance;
	}
	
	public Collection obtenerIdiomas(String idioma) {
//		Collection idiomas = null;
//		if(logger.isDebugEnabled()) logger.debug("Recuperando combo de idiomas para sesion en idioma " + idioma);
//		idiomas = (Collection)mapaIdiomas.get(idioma);
//		if(idiomas == null) {
//			if(logger.isDebugEnabled()) logger.debug("El combo de idiomas en " + idioma + " no estaba cacheado");
//			try {
//				LocalizacionIdiomaVO[] idiomasArray = getSrvBuscadorService().obtenerIdiomasLocalizados(idioma);
//				if(idiomasArray==null) {
//					logger.error("No se han podido obtener los idiomas localizados a " + idioma);
//					idiomas = new ArrayList();
//				} else {
//					if(logger.isDebugEnabled()) logger.debug("Idiomas recuperados en " + idioma);
//					idiomas = Arrays.asList(idiomasArray);
//					mapaIdiomas.put(idioma, idiomas);
//				}
//			} catch (Exception e) {
//				logger.error("No se han podido obtener los idiomas localizados a " + idioma + " : " + e.getMessage());
//				if(logger.isDebugEnabled()) logger.debug(e);
//			}
//		}
		return null;
	}
	
	protected final es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService getSrvBuscadorService()
    throws java.lang.Exception
    {
	    String srvBuscadorServiceFile="importedServices.properties";	    
	    java.io.InputStream srvBuscadorServiceInputStream=IdiomasBuscadorSingleton.class.getClassLoader().getResourceAsStream(srvBuscadorServiceFile);
	    java.util.Properties srvBuscadorServiceProperties = new java.util.Properties();
	    srvBuscadorServiceProperties.load(srvBuscadorServiceInputStream);
	    String srvBuscadorServiceEndPointAddress="";
	    srvBuscadorServiceEndPointAddress=(String) srvBuscadorServiceProperties.get("srvBuscadorServicePort");
	    System.out.println("srvBuscadorServiceEndPointAddress del fichero --> " + srvBuscadorServiceEndPointAddress);
			es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceService srvBuscadorService = new es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceServiceLocator();                                                                                                                                                                                                                                                    
	    if (srvBuscadorServiceEndPointAddress.length()>0) 
			((es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceServiceLocator)srvBuscadorService).setSrvBuscadorServiceEndpointAddress(srvBuscadorServiceEndPointAddress);				
		es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorService port = srvBuscadorService.getSrvBuscadorService();	    
	    return port;
    }
	
}
