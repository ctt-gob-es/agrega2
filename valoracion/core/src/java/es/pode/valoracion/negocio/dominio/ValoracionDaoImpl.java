// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.valoracion.negocio.dominio;

import java.util.Date;
import java.util.List;

/**
 * @see es.pode.valoracion.negocio.dominio.Valoracion
 */
public class ValoracionDaoImpl
    extends es.pode.valoracion.negocio.dominio.ValoracionDaoBase
{
    /**
     * @see es.pode.valoracion.negocio.dominio.ValoracionDao#toValoracionVO(es.pode.valoracion.negocio.dominio.Valoracion)
     */
    public es.pode.valoracion.negocio.servicios.ValoracionVO toValoracionVO(final es.pode.valoracion.negocio.dominio.Valoracion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.valoracion.negocio.servicios.ValoracionVO) 
                  this.getBeanMapper().map(entity, es.pode.valoracion.negocio.servicios.ValoracionVO.class, DEF_MAPPING_VALORACION_VALORACIONVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.valoracion.negocio.dominio.ValoracionDao#fromValoracionVO(es.pode.valoracion.negocio.servicios.ValoracionVO)
	 */
    public es.pode.valoracion.negocio.dominio.Valoracion fromValoracionVO(final es.pode.valoracion.negocio.servicios.ValoracionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromValoracionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.valoracion.negocio.dominio.ValoracionDao#fromValoracionVO(es.pode.valoracion.negocio.servicios.ValoracionVO ,es.pode.valoracion.negocio.dominio.Valoracion)	 
	 */
    public void fromValoracionVO(es.pode.valoracion.negocio.servicios.ValoracionVO vo, es.pode.valoracion.negocio.dominio.Valoracion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromValoracionVO(vo, entity);
    }
    /**
     * @see es.pode.valoracion.negocio.dominio.ValoracionDao#toValoracionIdODEVO(es.pode.valoracion.negocio.dominio.Valoracion)
     */
    public es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO toValoracionIdODEVO(final es.pode.valoracion.negocio.dominio.Valoracion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO) 
                  this.getBeanMapper().map(entity, es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO.class, DEF_MAPPING_VALORACION_VALORACIONIDODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.valoracion.negocio.dominio.ValoracionDao#fromValoracionIdODEVO(es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO)
	 */
    public es.pode.valoracion.negocio.dominio.Valoracion fromValoracionIdODEVO(final es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromValoracionIdODEVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.valoracion.negocio.dominio.ValoracionDao#fromValoracionIdODEVO(es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO ,es.pode.valoracion.negocio.dominio.Valoracion)	 
	 */
    public void fromValoracionIdODEVO(es.pode.valoracion.negocio.servicios.auditoria.ValoracionIdODEVO vo, es.pode.valoracion.negocio.dominio.Valoracion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromValoracionIdODEVO(vo, entity);
    }


	public List listarODEsValoradosDesdeHasta(int transform, String queryString, Date fechaDesde, Date fechaHasta)
	{
		try
		{
		
			org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);		
			queryObject.setParameter("fechaHasta", fechaHasta); 		
			queryObject.setParameter("fechaDesde", fechaDesde); 		
			logger.debug("listarODEsValoradosDesdeHasta["+queryObject.getQueryString()+"]");		
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
            case es.pode.valoracion.negocio.dominio.ValoracionDao.TRANSFORM_VALORACIONVO :
                toValoracionVOCollection(entities);
                break;
            case es.pode.valoracion.negocio.dominio.ValoracionDao.TRANSFORM_VALORACIONIDODEVO :
                toValoracionIdODEVOCollection(entities);
                break;
            case TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }

}