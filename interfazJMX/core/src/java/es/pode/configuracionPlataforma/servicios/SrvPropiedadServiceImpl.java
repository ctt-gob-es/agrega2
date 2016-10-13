// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.servicios;

import es.indra.agrega.jmx.Propiedad;
import es.indra.agrega.jmx.PropiedadDao;
import es.indra.agrega.jmx.PropiedadVO;
import es.indra.agrega.jmx.Propiedades;


/**
 * @see es.pode.configuracionPlataforma.servicios.SrvPropiedadService
 */

public class SrvPropiedadServiceImpl
    extends es.pode.configuracionPlataforma.servicios.SrvPropiedadServiceBase
{


    /**
     * @see es.indra.agrega.jmx.PropiedadService#getAllProperties()
     */
    protected java.util.List handleGetAllProperties()
        throws java.lang.Exception
    {
        //@todo implement protected java.util.List handleGetAllProperties()
        return (java.util.List)this.getPropiedadDao().loadAll();  
    }

    /**
     * @see es.indra.agrega.jmx.PropiedadService#getPropiedadByName(java.lang.String)
     */
    protected es.indra.agrega.jmx.PropiedadVO handleGetPropiedadByName(java.lang.String Nombre)
        throws java.lang.Exception
    {
        //@todo implement protected es.indra.agrega.jmx.PropiedadListItem handleGetPropiedadByName(java.lang.String Nombre)
        return (es.indra.agrega.jmx.PropiedadVO)this.getPropiedadDao().load(this.getPropiedadDao().TRANSFORM_PROPIEDADVO,Nombre);      
    }

    /**
     * @see es.indra.agrega.jmx.PropiedadService#updatePropiedad(es.indra.agrega.jmx.Propiedad)
     */
    protected void handleUpdatePropiedad(es.indra.agrega.jmx.Propiedad Propiedad)
        throws java.lang.Exception
    {
    	this.getPropiedadDao().update(Propiedad);
    	
        //@todo implement protected void handleUpdatePropiedad(es.indra.agrega.jmx.Propiedad Propiedad)
        throw new java.lang.UnsupportedOperationException("es.indra.agrega.jmx.PropiedadService.handleUpdatePropiedad(es.indra.agrega.jmx.Propiedad Propiedad) Not implemented!");
    }

	@Override
	protected PropiedadVO handleGetPropiedad(String Nombre) throws Exception {
        //@todo implement protected es.indra.agrega.jmx.PropiedadListItem handleGetPropiedadByName(java.lang.String Nombre)
        return (es.indra.agrega.jmx.PropiedadVO)this.getPropiedadDao().load(this.getPropiedadDao().TRANSFORM_PROPIEDADVO,Nombre);      
    }

	@Override
	protected String handleGet(String property) throws Exception {
		logger.debug("Solicitada variable "+property);
		Propiedades prop;
		try {
			prop=Propiedades.getPropiedad(property);
			logger.debug("Recuperada propiedad");
			if(prop.getValor()==null||prop.getValor().equals("")) {
				logger.debug("No tenia valor cargado, consultamos base de datos");
				PropiedadVO salida=(PropiedadVO)this.getPropiedadDao().load(PropiedadDao.TRANSFORM_PROPIEDADVO,prop.getNombreConPrefijo());
				if (salida!=null) {
					prop.setValor(salida.getValor());
				}
			}
			return prop.getValor();
		} catch (Exception e) {
			logger.debug(e);
		}
		
		
//    	logger.debug("Buscamos "+Nombre);
        return "";      
    
		
//		return getPropiedadBBDDService().getPropiedadByName(property).getValor();
	}

	@Override
	protected boolean handleSet(String nombre, String valor) throws Exception {
		Propiedades prop;
		logger.debug("Buscando "+nombre+" para ponerlo a "+valor);
		try {
			prop = Propiedades.getPropiedad(nombre);
			logger.debug("Es JMX?"+(prop.getJmx()?"SI":"NO"));
			if(prop.getJmx()) {
				return false;
			}
			logger.debug("Recuperamos propiedad");
			prop.setValor(valor);
			logger.debug("Asignamos valor en enum");
			PropiedadVO propiedad = new PropiedadVO();
			propiedad.setNombre(nombre);
			propiedad.setValor(valor);
			//		getPropiedadBBDDService().updatePropiedad(propiedad);
			logger.debug("Buscamos " + propiedad.getNombre()
					+ " para ponerlo a " + propiedad.getValor() + " en BBDD");
			Propiedad propiedadEntity = getPropiedadDao().load(
					prop.getNombreConPrefijo());
			logger.debug("Cargamos propiedad");
			propiedadEntity.setValor(propiedad.getValor());
			getPropiedadDao().update(propiedadEntity);
			logger.debug("Actualizamos");
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}

	@Override
	protected String handleGetUrlNodo() throws Exception {
		String propertyValue = null;
		
		String host = handleGet(Propiedades.host.getNombre());
		String subdominio = handleGet(Propiedades.subdominio.getNombre());
		
		propertyValue = (subdominio == null || "".equals(subdominio))?"http://"+host:"http://"+host+subdominio;
		if(logger.isDebugEnabled()) logger.debug("getUrlNodo() devuelve : " + propertyValue);
		return propertyValue;
	}

}