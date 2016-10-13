// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.negocio.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.pode.configuracionPlataforma.negocio.dominio.Ficheros;
import es.pode.configuracionPlataforma.negocio.dominio.Propiedad;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;
import es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO;



/**
 * @see es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService
 */

public class SrvGestorConfiguracionServiceImpl
    extends es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionServiceBase
{


    /**
     * @see es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService#modificarValorPropiedadLocal(java.lang.String, java.lang.String)
     */
    protected es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO handleModificarValorPropiedadLocal(java.lang.String nombrePropiedad, java.lang.String valor)
        throws java.lang.Exception
    {
        //@todo implement protected es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO handleModificarValorPropiedadLocal(java.lang.String nombrePropiedad, java.lang.String valor)
        return null;
    }

    /**
     * @see es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService#modificarValorPropiedad(java.lang.String, java.lang.String, java.lang.String)
     */
    protected es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO handleModificarValorPropiedad(java.lang.String nombrePropiedad, java.lang.String valor, java.lang.String instanciaJboss)
        throws java.lang.Exception
    {
		ResultadoOperacionVO resultado = null;
		
		SrvPropiedadService srvPropiedad = getSrvPropiedadService();			
		
		if (logger.isDebugEnabled())
			logger.debug("Va a localizar la propiedad : " + nombrePropiedad + " de " + instanciaJboss);
		
		Propiedad propBBDD = srvPropiedad.getPropiedadJboss(nombrePropiedad, instanciaJboss);
		
		if (propBBDD!=null)
		{
			if (!propBBDD.getValor().equals(valor))
			{
				if (logger.isDebugEnabled())
					logger.debug("La variable " + nombrePropiedad + " SI ha cambiado de " + propBBDD.getValor() + " a " + valor);
				
				srvPropiedad.setValorPropiedadJboss(propBBDD.getNombre(), valor, instanciaJboss);				
				resultado = gestionarActualizacionFicheros(propBBDD.getFicherosAfectados());				
			}
			else
			{
				if (logger.isDebugEnabled())
					logger.debug("La variable " + nombrePropiedad + " NO ha cambiado");
				
				resultado = new ResultadoOperacionVO();				
				resultado.setCodigoResultado(0);
				resultado.setMensaje("La variable " + nombrePropiedad + " no ha cambiado de valor");				
			}
		}else
		{
			if (logger.isDebugEnabled())
				logger.debug("La variable " + nombrePropiedad + " no se encuentra en base de datos");			
			resultado = new ResultadoOperacionVO();				
			resultado.setCodigoResultado(0);
			resultado.setMensaje("La variable " + nombrePropiedad + " no se encuentra en base de datos");
		}			
		return resultado;
    }

    /**
     * @see es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService#modificarPropiedadesLocal(es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[])
     */
    protected es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO handleModificarPropiedadesLocal(es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[] propiedades)
        throws java.lang.Exception
    {
        
        return handleModificarPropiedades(propiedades, obtenerInstanciaJbossLocal());
    }

    /**
     * @see es.pode.configuracionPlataforma.negocio.servicios.SrvGestorConfiguracionService#modificarPropiedades(es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[], java.lang.String)
     */
    protected es.pode.configuracionPlataforma.negocio.dominio.ResultadoOperacionVO handleModificarPropiedades(es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[] propiedades, java.lang.String instanciaJboss)
        throws java.lang.Exception
    {
		ResultadoOperacionVO resultado = null;
			
		SrvPropiedadService srvPropiedad = getSrvPropiedadService();
		
		boolean guardarAgrega = false;
		boolean guardarAuthbackend = false;
		boolean guardarInformes = false;
		boolean guardarOpml = false;
		boolean guardarRobots = false;
		boolean guardarSearch = false;
		boolean guardarSitemap = false;
		boolean guardarSpringldap = false;
		
		List<PropiedadVO> lPropOriginal = new ArrayList<PropiedadVO>();
		PropiedadVO propOriginal = null;
				
		for (int i = 0; i < propiedades.length; i++) {
			PropiedadVO propPantalla = propiedades[i];
			Propiedad propBBDD = srvPropiedad.getPropiedadJboss(propPantalla.getNombre(), propPantalla.getInstanciaJboss());
			
			propOriginal = new PropiedadVO();
			propOriginal.setNombre(propBBDD.getNombre());
			propOriginal.setValor(propBBDD.getValor());
			propOriginal.setInstanciaJboss(propBBDD.getInstanciaJboss());
			
			lPropOriginal.add(propOriginal);
				
			srvPropiedad.setValorPropiedadJboss(propBBDD.getNombre(), propPantalla.getValor(), propPantalla.getInstanciaJboss());

			String listaFicherosAfectados = propBBDD.getFicherosAfectados();
			// Almacenamos los ficheros que debemos modificar porque se ha cambiado el valor de alguna de sus propiedades
			if (listaFicherosAfectados.toUpperCase().contains("AGREGA"))
			{				    	
				guardarAgrega = true;					
			}
			if (listaFicherosAfectados.toUpperCase().contains("AUTHBACKEND"))
			{				    			    	
				guardarAuthbackend=true;						
			}		
			if (listaFicherosAfectados.toUpperCase().contains("RPTDESIGN"))
			{				    	
				guardarInformes = true;	
			}		
			if (listaFicherosAfectados.toUpperCase().contains("OPML"))
			{				    		    		    	
				guardarOpml = true;
			}
			if (listaFicherosAfectados.toUpperCase().contains("ROBOTS"))
			{				    	
				guardarRobots = true;		
			}				
			if (listaFicherosAfectados.toUpperCase().contains("SEARCH"))
			{				    	
				guardarSearch = true;
			}		
			if (listaFicherosAfectados.toUpperCase().contains("SITEMAP"))
			{				    	
				guardarSitemap = true;		
			}
			if (listaFicherosAfectados.toUpperCase().contains("SPRINGLDAP"))
			{				    	
				guardarSpringldap = true;		
			}
				
		}		

		// Si hay que tocar ficheros hacemos un backup previo
		if (guardarAgrega || guardarAuthbackend || guardarInformes || guardarOpml || guardarRobots 
				|| guardarSearch || guardarSitemap || guardarSpringldap)
		{
			// Creamos un backup de los ficheros de configuracion
			getSrvFicherosConfiguracionService().crearBackupFicherosInstanciaJboss(obtenerRutaJbossLocal(), instanciaJboss);
		}
		
		if (guardarAgrega)
		{			
			resultado = actualizarFichero(Ficheros.AGREGA_PROPERTIES.getFilename());			
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarAuthbackend)
		{				    							
			resultado = actualizarFichero(Ficheros.AUTHBACKEND_PROPERTIES.getFilename());
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarInformes)
		{				    		
			resultado = actualizarFichero("rptdesign");
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarOpml)
		{				    							
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarRobots)
		{				    							
			resultado = actualizarFichero(Ficheros.ROBOTS_TXT.getFilename());
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarSearch)
		{				    							
			resultado = actualizarFichero(Ficheros.SEARCHPLUGIN_XML.getFilename());
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarSitemap)
		{				    							
			resultado = actualizarFichero(Ficheros.SITEMAP.getFilename());
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
		if (guardarSpringldap)
		{				    							
			resultado = actualizarFichero(Ficheros.SPRINGLDAP_XML.getFilename());
			if (resultado==null || resultado.getCodigoResultado()!=1)
			{
				restaurarCambiosPropiedades(lPropOriginal, obtenerRutaJbossLocal(), instanciaJboss);
				return resultado;
			}
		}
	
		return resultado;
    }

    /**
     * Método que se encarga de restaurar los cambios realizados en base de datos y en ficheros.
     * @param lPropOriginales	Propiedades de base de datos originales
     * @param homeInstanciaJboss	Ruta física de la instancia de jboss 
     * @param instanciaJboss	Instancia de jboss
     * @return
     */
    private boolean restaurarCambiosPropiedades(List<PropiedadVO> lPropOriginales, String homeInstanciaJboss, String instanciaJboss)
    {
    	boolean resultado = false;
      	if (logger.isDebugEnabled())
			logger.debug("Se ha producido un error y hay que restaurar los cambios en bbdd y ficheros");
  
		SrvPropiedadService srvPropiedad = getSrvPropiedadService();
		
		for (Iterator<PropiedadVO> iterator = lPropOriginales.iterator(); iterator.hasNext();) {
			PropiedadVO propiedad = (PropiedadVO) iterator.next();			
			srvPropiedad.setValorPropiedadJboss(propiedad.getNombre(), propiedad.getValor(), propiedad.getInstanciaJboss());						
		}

		if (logger.isDebugEnabled())
			logger.debug("Se han restaurado los cambios en bbdd");

      	
		resultado = getSrvFicherosConfiguracionService().restaurarBackupFicherosInstanciaJboss(homeInstanciaJboss, instanciaJboss);
    	
      	if (logger.isDebugEnabled())
			logger.debug("Se han restaurar los cambios en ficheros. Resultado :" + resultado);
  
      	return resultado;
    }
    
    /**
     * Método encargado de invocar a la actualización de ficheros de propiedades 
     * @param listaFicherosAfectados
     * @return
     */
    private ResultadoOperacionVO gestionarActualizacionFicheros(String listaFicherosAfectados)
    {
    	ResultadoOperacionVO resultado = null;   
    	
    	if (logger.isDebugEnabled())
			logger.debug("Lista de ficheros afectados por el cambio de variable : "+ listaFicherosAfectados);
    	
		if (listaFicherosAfectados.toUpperCase().contains("AGREGA"))
		{				    	
			resultado = actualizarFichero(Ficheros.AGREGA_PROPERTIES.getFilename());					
		}
		if (listaFicherosAfectados.toUpperCase().contains("AUTHBACKEND"))
		{				    			    	
			resultado = actualizarFichero(Ficheros.AUTHBACKEND_PROPERTIES.getFilename());						
		}		
		if (listaFicherosAfectados.toUpperCase().contains("RPTDESIGN"))
		{				    	
			resultado = actualizarFichero("rptdesign");		
		}		
		if (listaFicherosAfectados.toUpperCase().contains("OPML"))
		{				    		    		    	
			resultado = actualizarFichero("opml");
		}
		if (listaFicherosAfectados.toUpperCase().contains("ROBOTS"))
		{				    	
			resultado = actualizarFichero(Ficheros.ROBOTS_TXT.getFilename());		
		}				
		if (listaFicherosAfectados.toUpperCase().contains("SEARCH"))
		{				    	
			resultado = actualizarFichero(Ficheros.SEARCHPLUGIN_XML.getFilename());		
		}		
		if (listaFicherosAfectados.toUpperCase().contains("SITEMAP"))
		{				    	
			resultado = actualizarFichero(Ficheros.SITEMAP.getFilename());		
		}
		if (listaFicherosAfectados.toUpperCase().contains("SPRINGLDAP"))
		{				    	
			resultado = actualizarFichero(Ficheros.SPRINGLDAP_XML.getFilename());		
		}		
			
		return resultado;
    }
    /**
     * Método encargado de gestionar la actualización del fichero de propiedades.
     * Si el nodo pertenece a un cluster y está configurado invoca a la actualización del mismo fichero en el resto de nodos del cluster
     * @param nombreFichero
     * @return
     */
    private ResultadoOperacionVO actualizarFichero(String nombreFichero)
    {
    	if (logger.isDebugEnabled())
			logger.debug("Se debe regenerar el fichero :" + nombreFichero);								    		    		    
	
    	ResultadoOperacionVO resultado = null;
    	
    	SrvPropiedadService srvPropiedad = getSrvPropiedadService();	
		
				    	
	    PropiedadVO[] propiedades = srvPropiedad.getAllProperties();
		
	    PropiedadVO[] propiedadesLocal = srvPropiedad.getAllProperties();
		
    	boolean bCorrecto = getSrvFicherosConfiguracionService().escribirFicheroLocal(nombreFichero, propiedadesLocal);
		
		if (bCorrecto)
		{
			resultado = new ResultadoOperacionVO();				
			resultado.setCodigoResultado(1);
			resultado.setMensaje("Variable modificada correctamente en base de datos y fichero " + nombreFichero);
			
			try
			{
				SrvFicherosConfiguracionService srvFicheroConfRemote=null;
				boolean bHayNodos=true;
				int nNodo=0;
				while (bHayNodos) {
					srvFicheroConfRemote = this.getSrvFicherosConfiguracionService(nNodo);
					if (srvFicheroConfRemote!=null)
					{
						logger.info("Va a invocar a otro nodo para actualizar el fichero :" + nombreFichero);
						boolean bCorrectoEx = srvFicheroConfRemote.escribirFicheroLocal(nombreFichero,propiedades);
					}
					else
					{
						bHayNodos=false;
					}
					nNodo++;
				}							 
			}catch (Exception e) {
				logger.error("Hubo un problema al invocar a otro nodo :" + e.getMessage());
			}
			
			
		}else
		{
			resultado = new ResultadoOperacionVO();				
			resultado.setCodigoResultado(0);
			resultado.setMensaje("Hubo problemas en la actualización de la variable en el fichero " + nombreFichero);				
		}
		
		return resultado;
    }
    
	/**
	 * Devuelve el nombre de la carpeta en la que esta la instancia de JBoss donde se
	 * ejecuta el codigo
	 */
	private String obtenerInstanciaJbossLocal() throws Exception {
		String homeInstancia = "";
		try {
			homeInstancia = System.getProperty("jboss.server.name");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		return homeInstancia;
	}
	
	/**
	 * Método que obtiene la ruta física de la instancia actual de jboss
	 * @return	Ruta de la instancia del jboss
	 * @throws Exception
	 */
	private String obtenerRutaJbossLocal() throws Exception {
		String homeJboss = "";
		try {
			homeJboss = System.getProperty("jboss.server.home.dir");			
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		return homeJboss;
	}	
	
	/**
	 * Método que invoca al servicio SrvFicherosConfiguracionService del nodo del cluster que se pasa como parámetro. 
	 * Obtiene la dirección configurada en el importedServices.properties 
	 * @param nNodo	Ordinal del nodo del cluster
	 * @return	Remote del servicio  del nodo externo.
	 * @throws java.lang.Exception
	 */
	protected final SrvFicherosConfiguracionService getSrvFicherosConfiguracionService(
			int nNodo) throws java.lang.Exception {
		String srvFicherosConfiguracionServiceFile = "importedServices.properties";
		java.io.InputStream srvIndexadorServiceInputStream = SrvGestorConfiguracionServiceBase.class
				.getClassLoader().getResourceAsStream(
						srvFicherosConfiguracionServiceFile);
		java.util.Properties srvFicherosConfiguracionServiceProperties = new java.util.Properties();
		srvFicherosConfiguracionServiceProperties
				.load(srvIndexadorServiceInputStream);
		String srvFicherosConfiguracionClusterServiceEndPointAddress = "";
		srvFicherosConfiguracionClusterServiceEndPointAddress = (String) srvFicherosConfiguracionServiceProperties
				.get("srvFicherosConfiguracionServiceClusterPort" + nNodo);
		logger
				.debug("srvFicherosConfiguracionClusterServiceEndPointAddress del fichero --> "
						+ srvFicherosConfiguracionClusterServiceEndPointAddress);
		if (srvFicherosConfiguracionClusterServiceEndPointAddress == null)
			return null;
		SrvFicherosConfiguracionServiceService srvFicherosConfiguracionService = new SrvFicherosConfiguracionServiceServiceLocator();
		if (srvFicherosConfiguracionClusterServiceEndPointAddress.length() > 0)
			((SrvFicherosConfiguracionServiceServiceLocator) srvFicherosConfiguracionService)
					.setSrvFicherosConfiguracionServiceEndpointAddress(srvFicherosConfiguracionClusterServiceEndPointAddress);
		SrvFicherosConfiguracionService port = srvFicherosConfiguracionService
				.getSrvFicherosConfiguracionService();
		return port;
	}
}