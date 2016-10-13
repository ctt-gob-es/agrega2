// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.negocio.servicios;

import java.util.ArrayList;

import es.pode.configuracionPlataforma.ficheros.AgregaPropertiesFile;
import es.pode.configuracionPlataforma.ficheros.AuthBackendFile;
import es.pode.configuracionPlataforma.ficheros.OpmlFile;
import es.pode.configuracionPlataforma.ficheros.RobotsFile;
import es.pode.configuracionPlataforma.ficheros.RptdesignFile;
import es.pode.configuracionPlataforma.ficheros.SearchPluginFile;
import es.pode.configuracionPlataforma.ficheros.SitemapPortada;
import es.pode.configuracionPlataforma.ficheros.SpringLdapFile;
import es.pode.configuracionPlataforma.negocio.dominio.Ficheros;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;
import es.pode.configuracionPlataforma.negocio.dominio.Propiedades;


/**
 * @see es.pode.configuracionPlataforma.negocio.servicios.SrvFicherosConfiguracionService
 */

public class SrvFicherosConfiguracionServiceImpl
    extends es.pode.configuracionPlataforma.negocio.servicios.SrvFicherosConfiguracionServiceBase
{

	/* Instancia a la que pertenecen las propiedades globales; aplicables en todas las instancias de Jboss */
	private String INSTANCIA_GLOBAL="global";

    /**
     * @see es.pode.configuracionPlataforma.negocio.servicios.SrvFicherosConfiguracionService#escribirFicheroLocal(java.lang.String, es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[])
     */
    protected boolean handleEscribirFicheroLocal(java.lang.String nombreFichero, es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[] propiedadesCompletas)
        throws java.lang.Exception
    {
       	boolean resultado = false;
		try{
			if (logger.isDebugEnabled())
				logger.debug("Inicio actualización fichero :" + nombreFichero);
	    		
			SrvPropiedadService srvPropiedad = getSrvPropiedadService();	
			
			if (propiedadesCompletas==null)
			{		    	
		    	propiedadesCompletas = srvPropiedad.getPropiedadesPorFichero(nombreFichero);
			}
			
			PropiedadVO[] propiedades = eliminarPropiedadesDeOtrasInstancias(propiedadesCompletas);
		   
	    	if (nombreFichero.equals(Ficheros.AGREGA_PROPERTIES.getFilename()))
	    	{	    	
		    	AgregaPropertiesFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}	    	
	    	if (nombreFichero.equals(Ficheros.AUTHBACKEND_PROPERTIES.getFilename()))
	    	{	    	
		    	AuthBackendFile.modificaVariables(propiedades, obtenerRutaJbossLocal());		    
	    	}	    	
	    	if (nombreFichero.equals("rptdesign"))
	    	{	    	
		    	RptdesignFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}	    	
	    	if (nombreFichero.equals("opml"))
	    	{	    	
		    	OpmlFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}
	    	if (nombreFichero.equals(Ficheros.ROBOTS_TXT.getFilename()))
	    	{	    	
		    	RobotsFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}
	    	if (nombreFichero.equals(Ficheros.SEARCHPLUGIN_XML.getFilename()))
	    	{	    			    
		    	SearchPluginFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}
	    	if (nombreFichero.equals(Ficheros.SITEMAP.getFilename()))
	    	{	    	
		    	SitemapPortada.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}
	    	if (nombreFichero.equals(Ficheros.SPRINGLDAP_XML.getFilename()))
	    	{	    	
		    	SpringLdapFile.modificaVariables(propiedades, obtenerRutaJbossLocal());
	    	}

	    	srvPropiedad.resetCachePropiedadesLocales();

	    	
	    	resultado=true;
	    }catch (Exception e) {
	    	logger.error("Error al actualizar el fichero " + e.getMessage());
		}	    	
	    return resultado;
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

	@Override
	protected boolean handleCrearBackupFicherosInstanciaJboss(
			String homeInstanciaJboss, String instanciaJboss) throws Exception {
		AgregaPropertiesFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		AuthBackendFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		OpmlFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		RobotsFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		RptdesignFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		SearchPluginFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		SitemapPortada.crearBackup(homeInstanciaJboss, instanciaJboss);
		SpringLdapFile.crearBackup(homeInstanciaJboss, instanciaJboss);
		
		return true;
	}

	@Override
	protected boolean handleRestaurarBackupFicherosInstanciaJboss(
			String homeInstanciaJboss, String instanciaJboss) throws Exception {
		AgregaPropertiesFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		AuthBackendFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		OpmlFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		RobotsFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		RptdesignFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		SearchPluginFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		SitemapPortada.restaurarBackup(homeInstanciaJboss, instanciaJboss);
		SpringLdapFile.restaurarBackup(homeInstanciaJboss, instanciaJboss);

		return true;
	}
	
	/**
	 * Metodo que dada una lista de propiedades elimina las que pertenecen a otras instancias JBoss
	 */
	private PropiedadVO[] eliminarPropiedadesDeOtrasInstancias(PropiedadVO[] propiedades) throws Exception {
		
		String instanciaLocal=obtenerInstanciaJbossLocal();
		
		ArrayList<PropiedadVO> propCorrectas = new ArrayList<PropiedadVO>();
		
    	for(int i=0; i<propiedades.length; i++) {
    		
    		String instanciaPropiedad=propiedades[i].getInstanciaJboss();
    	
    		if(instanciaPropiedad.contentEquals(instanciaLocal) || 
    				instanciaPropiedad.contentEquals(INSTANCIA_GLOBAL)) {
        		propCorrectas.add(propiedades[i]);       
        
        	}
    	}
    	return  propCorrectas.toArray(new PropiedadVO[0]);
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
}