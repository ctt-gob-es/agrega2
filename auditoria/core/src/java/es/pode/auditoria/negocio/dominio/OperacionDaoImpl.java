// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.dominio;

import java.util.Calendar;
import java.util.List;

/**
 * @see es.pode.auditoria.negocio.dominio.Operacion
 */
public class OperacionDaoImpl
    extends es.pode.auditoria.negocio.dominio.OperacionDaoBase
{
    /**
     * @see es.pode.auditoria.negocio.dominio.OperacionDao#toOperacionVO(es.pode.auditoria.negocio.dominio.Operacion)
     */
    public es.pode.auditoria.negocio.servicios.OperacionVO toOperacionVO(final es.pode.auditoria.negocio.dominio.Operacion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.auditoria.negocio.servicios.OperacionVO) 
                  this.getBeanMapper().map(entity, es.pode.auditoria.negocio.servicios.OperacionVO.class, DEF_MAPPING_OPERACION_OPERACIONVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.auditoria.negocio.dominio.OperacionDao#fromOperacionVO(es.pode.auditoria.negocio.servicios.OperacionVO)
	 */
    public es.pode.auditoria.negocio.dominio.Operacion fromOperacionVO(final es.pode.auditoria.negocio.servicios.OperacionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromOperacionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.auditoria.negocio.dominio.OperacionDao#fromOperacionVO(es.pode.auditoria.negocio.servicios.OperacionVO ,es.pode.auditoria.negocio.dominio.Operacion)	 
	 */
    public void fromOperacionVO(es.pode.auditoria.negocio.servicios.OperacionVO vo, es.pode.auditoria.negocio.dominio.Operacion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromOperacionVO(vo, entity);
    }
    
    public Object obtenerNumOperacionesDesdeHasta(final int transform, final java.lang.String queryString, final Calendar fechaDesde, final Calendar fechaHasta, final java.lang.String operacion)
    {
//    	logger.info("obtenerNumOperacionesDesdeHasta");
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
		    queryObject.setParameter("fechaDesde", fechaDesde);
			queryObject.setParameter("fechaHasta", fechaHasta);
			queryObject.setParameter("operacion", operacion);  
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
//                	logger.debug("tamanio 1");
                    result = results.iterator().next();
                    
                }
            }
           
            result = transformEntity(transform, (Long)result);
            return result;
                       
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    
  
    
    public java.util.List obtenerOperacionesDesdeHasta(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
			queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            transformEntities(transform, results);
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    /**
     * Obtiene la lista con los identificadores de los ods sobre los que se ha realizado una determinada operacion.
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param  operacion identificador de la operacion
	 * @param transform
	 * @param queryString
	 * @return Lista con los identificadores
     * @see es.pode.auditoria.negocio.dominio.Operacion#obtenerIdOdesOperacionDesdeHasta(int, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
     */
    public java.util.List obtenerIdOdesOperacionDesdeHasta(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta, final java.lang.String operacion)
    {
//    	logger.debug("obtenerIdOdesOperacionDesdeHasta");
        try
        {
        	org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("operacion", operacion);  
			queryObject.setParameter("fechaDesde", fechaDesde);
			queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            
            transformEntities(transform, results);
           
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    /**
     * Obtiene el número de vecesla lista con los identificadores de los ods sobre los que se ha realizado una determinada operacion.
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param transform
	 * @param queryString
	 * @param operacion El identificador de la operacion
	 * @return Lista con los identificadores
     * @see es.pode.auditoria.negocio.dominio.Operacion#obtenerNumIdOdesOperacion(int, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
     */
    public Object  obtenerNumIdOdesOperacion(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta, final java.lang.String idOde, final java.lang.String operacion)
    {
        try
        {
//        	logger.debug("[obtenerNumIdOdesOperacion]");
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("idOde", idOde); 
			queryObject.setParameter("fechaDesde", fechaDesde);
			queryObject.setParameter("fechaHasta", fechaHasta);
			queryObject.setParameter("operacion", operacion);
			logger.debug("queryObject "+queryObject);
            List results = queryObject.list();
            
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                	logger.debug("results.size() > 1");
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Integer"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
//                	logger.debug("tamanio 1");
                    result = results.iterator().next();
                }
            }
           
          //  result = transformEntity(transform, (Integer)result);
            return result;
         
            
            //return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.List  obtenerIdOdeOperUsuario(final int transform, final java.lang.String queryString, final java.util.Calendar fechaDesde, final java.util.Calendar fechaHasta, final java.lang.String usuario, final java.lang.String operacion)
    {
//    	logger.debug("obtenerIdOdeOperUsuario");
    	try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
			queryObject.setParameter("fechaHasta", fechaHasta);
			queryObject.setParameter("usuario", usuario);
			queryObject.setParameter("operacion", operacion);
			java.util.List results = queryObject.list();
            transformEntities(transform, results);
            return results;
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
            case es.pode.auditoria.negocio.dominio.OperacionDao.TRANSFORM_OPERACIONVO :
                toOperacionVOCollection(entities);
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
    
    /**
     * Obtiene el número de veces que sea producido esa operacion con ese identificador que se pasan como parametros.
	 * @param  idOde el identificador del ode 
	 * @param operacion el identificador de la operacion
	 * @param transform
	 * @param queryString
	 * @return Integer con el numero
     */
    public Object  obtenerNumeroOperacion(final int transform, final java.lang.String queryString, final java.lang.String idOde, final java.lang.String operacion)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("idOde", idOde); 
			queryObject.setParameter("operacion", operacion);
			logger.debug("queryObject "+queryObject);
            List results = queryObject.list();
            
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                	logger.debug("results.size() > 1");
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Integer"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
//               	logger.debug("tamanio 1");
                    result = results.iterator().next();
                }
            }
            
          //  result = transformEntity(transform, (Integer)result);
            return result;
         
            
            //return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
}