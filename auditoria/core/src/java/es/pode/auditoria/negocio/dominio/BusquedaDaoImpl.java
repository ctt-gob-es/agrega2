// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.dominio;

/**
 * @see es.pode.auditoria.negocio.dominio.Busqueda
 */
public class BusquedaDaoImpl
    extends es.pode.auditoria.negocio.dominio.BusquedaDaoBase
{
    /**
     * @see es.pode.auditoria.negocio.dominio.BusquedaDao#toBusquedaVO(es.pode.auditoria.negocio.dominio.Busqueda)
     */
    public es.pode.auditoria.negocio.servicios.BusquedaVO toBusquedaVO(final es.pode.auditoria.negocio.dominio.Busqueda entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.auditoria.negocio.servicios.BusquedaVO) 
                  this.getBeanMapper().map(entity, es.pode.auditoria.negocio.servicios.BusquedaVO.class, DEF_MAPPING_BUSQUEDA_BUSQUEDAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.auditoria.negocio.dominio.BusquedaDao#fromBusquedaVO(es.pode.auditoria.negocio.servicios.BusquedaVO)
	 */
    public es.pode.auditoria.negocio.dominio.Busqueda fromBusquedaVO(final es.pode.auditoria.negocio.servicios.BusquedaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromBusquedaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.auditoria.negocio.dominio.BusquedaDao#fromBusquedaVO(es.pode.auditoria.negocio.servicios.BusquedaVO ,es.pode.auditoria.negocio.dominio.Busqueda)	 
	 */
    public void fromBusquedaVO(es.pode.auditoria.negocio.servicios.BusquedaVO vo, es.pode.auditoria.negocio.dominio.Busqueda entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromBusquedaVO(vo, entity);
    }
    
    public java.util.List listarTerminosBuscadosDesdeHasta(final int transform, final java.lang.String queryString,java.util.Calendar fechaDesde, java.util.Calendar fechaHasta)
    {
    	try
        {
    		logger.debug("BUSQUEDADAOIMPL listarTerminosBuscadosDesdeHasta");
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            logger.debug("queryObject "+queryObject);
			queryObject.setParameter("fechaHasta", fechaHasta);            	
			queryObject.setParameter("fechaDesde", fechaDesde);            	
			logger.debug("listarTerminosBuscadosDesdeHasta["+queryObject.getQueryString()+"]");
            java.util.List results = queryObject.list();
            transformEntities(transform, results);
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.List listarAmbitoConsultaDesdeHasta(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaHasta", fechaHasta);            	
			queryObject.setParameter("fechaDesde", fechaDesde);    
            java.util.List results = queryObject.list();
            transformEntities(transform, results);
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public Object listarNumBusquedasDesdeHasta(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta, final java.lang.String ambito)
    {
//    	logger.debug("listarNumBusquedasDesdeHasta ");
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaHasta", fechaHasta);            	
			queryObject.setParameter("fechaDesde", fechaDesde); 
			queryObject.setParameter("ambito_busqueda", ambito);            	
            java.util.List results = queryObject.list();
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Integer"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
                    result = results.iterator().next();
                }
            }
            result = transformEntity(transform, (Long)result);
            return result;
        }
        catch (org.hibernate.HibernateException ex)
        {
        	logger.error("Excepcion en la operacion listarNumBusquedasDesdeHasta "+ex);
        	throw ex;
            //throw super.convertHibernateAccessException(ex);
        }
    }
    
    /**
     * @see es.pode.auditoria.negocio.dominio.Busqueda#obtenerNumTerminosBuscados(int, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
     */
    public Long obtenerNumTerminosBuscados(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta, final java.lang.String terminoBuscado)
    {
//    	logger.debug("obtenerNumTerminosBuscados");
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            logger.debug("queryObject "+queryObject);
            queryObject.setParameter("fechaHasta", fechaHasta);            	
			queryObject.setParameter("fechaDesde", fechaDesde); 
			queryObject.setParameter("terminoBuscado", terminoBuscado);            	
            java.util.List results = queryObject.list();
            Long result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Integer"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
                    result = (Long)results.iterator().next();
                }
            }
            /////result = transformEntity(transform, (Long)result);
            logger.debug("result "+result);
            return result;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    private final void transformEntities(final int transform, final java.util.Collection entities)
    {
        switch (transform)
        {
            case es.pode.auditoria.negocio.dominio.BusquedaDao.TRANSFORM_BUSQUEDAVO :
                toBusquedaVOCollection(entities);
                break;
            case TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }
    
    private final Object transformEntity(final int transform, final Long entity)
    {
        Object target = null;
        if (entity != null)
        {
            switch (transform)
            {
                case TRANSFORM_NONE : // fall-through
                default:
                    target = entity;
            }
        }
        return target;
    }

}