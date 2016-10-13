/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.configuracionPortal.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal;
import es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortalDao;
import es.pode.contenidos.negocio.noticias.servicio.ImagenVO;


/**
 * @see es.pode.contenidos.negocio.configuracionPortal.servicio.SrvConfigPortal
 */

public class SrvConfigPortalImpl
    extends es.pode.contenidos.negocio.configuracionPortal.servicio.SrvConfigPortalBase
{

	private static Logger log = Logger.getLogger(SrvConfigPortalImpl.class);

	/**
	 * Obtiene la configuración 
	 * @return ConfiguracionPortalVO: Obtiene la configuración del portal
	 * @throws Exception
	 */
    protected es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO handleObtenerConfiguracion()
        throws java.lang.Exception
    {
    	try
    	{
    	

    		ConfiguracionPortalVO configuracionPortal = (ConfiguracionPortalVO) this.getConfiguracionPortalDao().obtenerConfiguracion(ConfiguracionPortalDao.TRANSFORM_CONFIGURACIONPORTALVO);
    		log.debug("Recuperada la configuracion del portal");
    		return configuracionPortal;
    	}
    	catch(Exception e)
    	{
    		log.error("Excepcion en obtenerConfiguracion - ",e);
    		throw new Exception();
    	}

    }

    /**
	 * Crea la configuración del portal
	 * @param  configuracion ConfiguracionPortalVO: Datos de la configuración actual
	 * @return Long: Devuelve el id creado
	 * @throws Exception
	 */
    protected java.lang.Long handleCrearConfiguracion(es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO configuracion)
        throws java.lang.Exception
    {
    	try
    	{
	   	ConfiguracionPortal configuracionActual = (ConfiguracionPortal) this.getConfiguracionPortalDao().obtenerConfiguracion(ConfiguracionPortalDao.TRANSFORM_NONE);
	   	if(configuracionActual!=null){
	   		configuracionActual.setActiva(0);
	   		ConfiguracionPortal configPortalEntityActual = this.getConfiguracionPortalDao().load(configuracionActual.getId());
	   		this.getConfiguracionPortalDao().update(configPortalEntityActual);
	   	}
		
		ConfiguracionPortal configPortalEntityNew = this.getConfiguracionPortalDao().fromConfiguracionPortalVO(configuracion);
		
		this.getConfiguracionPortalDao().create(configPortalEntityNew);
				
		return Long.valueOf(1);
    }
		catch(Exception e)
    	{
    		log.error("Excepcion en crearConfiguracion - ",e);
    		throw new Exception();
    	}

    }

    /**
	 * Crea los ficheros de imagen y los almacena en el path correspondiente
	 * @param  imagen ImagenVO: Recibe las imagenes 
	 * @throws Exception
	 */
    protected void handleAlmacenarImagen(es.pode.contenidos.negocio.noticias.servicio.ImagenVO imagen)
        throws java.lang.Exception
    {
    	try
    	{
    	// Guarda en memoria la memoria y devuelve una ruta para acceder a ella
		// Obtenemos las propiedades
    	
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/spring.properties");
		java.util.Properties pSpringProperties;
		try {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
			// guardamos la imagen
			if(!imagen.getNombre().equalsIgnoreCase("noFile"))
			{
			imagen.setNombre(pSpringProperties.getProperty("config.imagenes.imagen"));
			File fImagen = new File(pSpringProperties.getProperty(
					"config.imagenes.path").toString()
					+ imagen.getNombre());
			fImagen.createNewFile();
			FileOutputStream fOsImagen = new FileOutputStream(fImagen);

			// fOsImagen.write(imagen.getDatos());
			
			try {
				imagen.getDatos().writeTo(fOsImagen);
			} catch (Exception e) {
				log.error(e);
				throw e;
			} finally {
				// liberamos recursos
				fOsImagen.close();
			}
			
			}
		} catch (Exception e) {
			log.error(e);
			throw e;
		} finally {
			fIsSpringProperties.close();
		}
    	
				
		
//		// guardamos la imagenPortada
//		if(!imagenPortada.getNombre().equalsIgnoreCase("noFile"))
//		{
//		imagenPortada.setNombre(pSpringProperties.getProperty("config.imagenes.imagenPortada"));
//		File fImagen_p = new File(pSpringProperties.getProperty(
//				"config.imagenes.path").toString()
//				+ imagenPortada.getNombre());
//		fImagen_p.createNewFile();
//		FileOutputStream fOsImagenP = new FileOutputStream(fImagen_p);
//
//		// fOsImagen.write(imagen.getDatos());
//		
//		imagenPortada.getDatos().writeTo(fOsImagenP);
//		
//
//		// liberamos recursos
//		fOsImagenP.close();
//		}

    }
    	catch(Exception e)
    	{
    		log.error("Excepcion en almacenarImagen - ",e);
    		throw new Exception();
    	}
    }

	@Override
	protected void handleAlmacenarImagenPorDefecto(ImagenVO imagen) throws Exception {
    	try
    	{
    	// Guarda en memoria la memoria y devuelve una ruta para acceder a ella
		// Obtenemos las propiedades
    	
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/spring.properties");
		java.util.Properties pSpringProperties;
		try {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
			// guardamos la imagen
			if(!imagen.getNombre().equalsIgnoreCase("noFile"))
			{
			imagen.setNombre(this.getSrvPropiedadService().get(AgregaProperties.VISTA_PREVIA_AGREGA));
			File fImagen = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_COMMON_PATH).toString()+ imagen.getNombre());
			fImagen.createNewFile();
			FileOutputStream fOsImagen = new FileOutputStream(fImagen);

			// fOsImagen.write(imagen.getDatos());
			
			try {
				imagen.getDatos().writeTo(fOsImagen);
			} catch (Exception e) {
				log.error(e);
				throw e;
			} finally {
				// liberamos recursos
				fOsImagen.close();
			}
			
			}
		} catch (Exception e) {
			log.error(e);
			throw e;
		} finally {
			fIsSpringProperties.close();
		}

    }
    	catch(Exception e)
    	{
    		log.error("Excepcion en almacenarImagenPorDefecto - ",e);
    		throw new Exception();
    	}
    }

	@Override
	protected String handleObtenerImagenPorDefecto() throws Exception {
		String imagen="";
//		ConfiguracionPortalVO configuracion=handleObtenerConfiguracion();
//		if (configuracion!=null&& configuracion.getImagenPorDefecto()!=null) {
//			imagen = configuracion.getImagenPorDefecto();
//		}
		return imagen;
	}

}