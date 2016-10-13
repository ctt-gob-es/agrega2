/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.configuracionPortal;

import java.util.Calendar;

import org.apache.log4j.Logger;

import es.pode.soporte.configuracionPortal.servicio.ConfiguracionPortalVO;
import es.pode.soporte.configuracionPortal.servicio.SrvConfigPortal;

public class ConfiguracionPortalImpl implements ConfiguracionPortal{
	
	private static final Integer NUM_ODES_GALERIA_DEFAULT = Integer.valueOf("10");
	private static final String ID_RSS_GALERIA_DEFAULT = "1.1";
	
	private ConfiguracionPortalVO config;
	private static ConfiguracionPortalImpl configPortal= null;

	
	private static Logger log = Logger.getLogger(ConfiguracionPortalImpl.class);
	
	private ConfiguracionPortalImpl(){
		try {
		
			SrvConfigPortal servicio = SrvConfigPortalService();
			config = servicio.obtenerConfiguracion();
			log.debug("config="+config);
		} catch (Exception e) {
			log.error("Se ha lanzado una excepcion: " ,e);
		}
		finally
		{
			if(config==null)
			{
				config=new ConfiguracionPortalVO();
				config.setActiva(1);
				config.setAgregaWeb(1);
				config.setDescargas(1);
				config.setEstadisticas(1);
				config.setEtiquetas(1);
				config.setFecha(config.getFecha());
				config.setImagen("");
				config.setInformes(1);
				config.setNoticias(1);
				config.setOpenId(1);
				config.setPlugginBusqueda(1);
				config.setRegistrese(1);
				config.setRss(1);
				config.setTagging(1);
				config.setEnlacePassword(1);
				config.setGoogleAnalytic(0);
				config.setCodGa("");
				config.setIdRssGaleriaPortada(ID_RSS_GALERIA_DEFAULT);
				config.setNumOdesGaleria(NUM_ODES_GALERIA_DEFAULT);
				config.setItinerariosAprendizaje(1);
			}
		}
	}
	
	public static ConfiguracionPortal getInstance()
    {
/*		
		if(configPortal!=null)
		{
		ConfiguracionPortalImpl.configPortal.reset();
		}
*/
		if (ConfiguracionPortalImpl.configPortal== null)
		{
			ConfiguracionPortalImpl.configPortal= new ConfiguracionPortalImpl();
		}
		return ConfiguracionPortalImpl.configPortal;
    }

	public Integer getActiva() {
		return config.getActiva();
	}
	public Integer getAgregaWeb() {
		return config.getAgregaWeb();
	}
	public Integer getDescargas() {
		return config.getDescargas();
	}
	public Integer getEstadisticas() {
		return config.getEstadisticas();
	}
	public Integer getEtiquetas() {
		return config.getEtiquetas();
	}
	public Calendar getFecha() {
		return config.getFecha();
	}
	public String getImagen() {
		return config.getImagen();
	}
	public Integer getInformes() {
		return config.getInformes();
	}
	public Integer getNoticias() {
		return config.getNoticias();
	}
	public Integer getOpenId() {
		return config.getOpenId();
	}
	public Integer getPlugginBusqueda() {
		return config.getPlugginBusqueda();
	}
	public Integer getRegistrese() {
		return config.getRegistrese();
	}
	public Integer getRss() {
		return config.getRss();
	}
	public Integer getTagging() {
		return config.getTagging();
	}
	public Integer getEnlacePassword() {
		return config.getEnlacePassword();
	}
	public Integer getGoogleAnalytic() {
		return config.getGoogleAnalytic();
	}
	public String getCodGa() {
		return config.getCodGa();
	}
	public String getIdRssGaleriaPortada() {
		return config.getIdRssGaleriaPortada();
	}

	public Integer getNumOdesGaleria() {
		return config.getNumOdesGaleria();
	}
	public Integer getItinerariosAprendizaje() {
		return config.getItinerariosAprendizaje();
	}
	public synchronized void reset() {
		configPortal=null;
	}
    /**
     * Returns a reference to the SrvConfigPortalService imported service.
     * @return es.pode.soporte.configuracionPortal.servicio.SrvConfigPortal 
     * @throws java.lang.Exception Exception
     */
    protected  final static es.pode.soporte.configuracionPortal.servicio.SrvConfigPortal SrvConfigPortalService()
        throws java.lang.Exception
    {
        String srvConfigPortalServiceFile="importedServices.properties";	    
        java.io.InputStream srvConfigPortalServiceInputStream=ConfiguracionPortalImpl.class.getClassLoader().getResourceAsStream(srvConfigPortalServiceFile);
        java.util.Properties srvConfigPortalServiceProperties = new java.util.Properties();
        srvConfigPortalServiceProperties.load(srvConfigPortalServiceInputStream);
        String srvConfigPortalServiceEndPointAddress="";
        srvConfigPortalServiceEndPointAddress=(String) srvConfigPortalServiceProperties.get("srvConfigPortalPort");
        log.debug("srvConfigPortalServiceEndPointAddress del fichero --> " + srvConfigPortalServiceEndPointAddress);
        es.pode.soporte.configuracionPortal.servicio.SrvConfigPortalService srvConfigPortalService = new es.pode.soporte.configuracionPortal.servicio.SrvConfigPortalServiceLocator();                                                                                                                                                                                                                                                    
        if (srvConfigPortalServiceEndPointAddress.length()>0) 
			((es.pode.soporte.configuracionPortal.servicio.SrvConfigPortalServiceLocator)srvConfigPortalService).setSrvConfigPortalEndpointAddress(srvConfigPortalServiceEndPointAddress);				
        es.pode.soporte.configuracionPortal.servicio.SrvConfigPortal port = srvConfigPortalService.getSrvConfigPortal();   
        return port;
    }


}
