// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @see es.pode.publicacion.negocio.dominio.ODEPublicado
 */
public class OdePublicadoDaoImpl
    extends OdePublicadoDaoBase
{
	
	private Logger logger = Logger.getLogger(this.getClass());
    /**
     * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#eliminaODEsPublicadosPorId(java.lang.String)
     */
    protected void handleEliminaODEsPublicadosPorId(java.lang.String idODE) throws Exception
    {
		if (idODE == null || idODE.equals(""))
		{
			logger.error("Imposible eliminar ODE publicado sin identificador.");
			throw new Exception("Imposible eliminar ODE publicado sin identificador.");
		}
		
		IdODEPublicadoCriteria criterio = new IdODEPublicadoCriteria(idODE);
		List resultados = this.obtenODEsPublicadosPorID(criterio);
		if (resultados== null || resultados.size() == 0)
		{
			logger.warn("No hay ODEs que borrar para el identificador["+idODE+"]");
			return;
		}
		this.remove(resultados);
    }

    /**
     * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#eliminaODEsPublicadosPorId(java.lang.String[])
     */
	protected void handleEliminaODEsPublicadosPorId(String[] idODE) throws Exception {
		if (idODE == null || idODE.length ==0)
		{
			logger.error("Imposible eliminar de ODEs publicados una lista vacia.");
			throw new Exception("Imposible eliminar de ODEs publicados una lista vacia.");
		}
		
		IdODEsCriteria criterio = new IdODEsCriteria(idODE);
		List resultados = this.obtenODEsPublicadosDeConjunto(criterio);
		if (resultados== null || resultados.size() == 0)
		{
			logger.warn("No hay ODEs que borrar para la lista de["+idODE.length+"] identificadores");
			return;
		}
		this.remove(resultados);
	}

	/**
     * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#toPesoODEVO(es.pode.publicacion.negocio.dominio.ODEPublicado)
     */
    public es.pode.publicacion.negocio.servicios.PesoODEVO toPesoODEVO(final es.pode.publicacion.negocio.dominio.OdePublicado entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.PesoODEVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.PesoODEVO.class, DEF_MAPPING_ODEPUBLICADO_PESOODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#fromPesoODEVO(es.pode.publicacion.negocio.servicios.PesoODEVO)
	 */
    public es.pode.publicacion.negocio.dominio.OdePublicado fromPesoODEVO(final es.pode.publicacion.negocio.servicios.PesoODEVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromPesoODEVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#fromPesoODEVO(es.pode.publicacion.negocio.servicios.PesoODEVO ,es.pode.publicacion.negocio.dominio.ODEPublicado)	 
	 */
    public void fromPesoODEVO(es.pode.publicacion.negocio.servicios.PesoODEVO vo, es.pode.publicacion.negocio.dominio.OdePublicado entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPesoODEVO(vo, entity);
    }
    /**
     * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#toDetallePublicadoODEVO(es.pode.publicacion.negocio.dominio.ODEPublicado)
     */
    public es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO toDetallePublicadoOdeVO(final es.pode.publicacion.negocio.dominio.OdePublicado entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO.class, DEF_MAPPING_ODEPUBLICADO_DETALLEPUBLICADOODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#fromDetallePublicadoODEVO(es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO)
	 */
    public es.pode.publicacion.negocio.dominio.OdePublicado fromDetallePublicadOdePublicadoVO(final es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromDetallePublicadoODEVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#fromDetallePublicadoODEVO(es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO ,es.pode.publicacion.negocio.dominio.ODEPublicado)	 
	 */
    public void fromDetallePublicadoODEVO(es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO vo, es.pode.publicacion.negocio.dominio.OdePublicado entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromDetallePublicadoODEVO(vo, entity);
    }
    /**
     * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#toDetallePublicadoODEVO(es.pode.publicacion.negocio.dominio.ODEPublicado)
     */
    public es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO toDetallePublicadoODEVO(final es.pode.publicacion.negocio.dominio.OdePublicado entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO.class, DEF_MAPPING_ODEPUBLICADO_DETALLEPUBLICADOODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.ODEPublicadoDao#fromDetallePublicadoODEVO(es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO)
	 */
    public es.pode.publicacion.negocio.dominio.OdePublicado fromDetallePublicadoODEVO(final es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromDetallePublicadoODEVO(vo);
    }

	public es.pode.publicacion.negocio.servicios.OdePublicadoVO toOdePublicadoVO(OdePublicado entity) {
		
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.OdePublicadoVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.OdePublicadoVO.class, DEF_MAPPING_ODEPUBLICADO_ODEPUBLICADOVO);
	}

	/**
     * @see es.pode.publicacion.negocio.dominio.OdePublicado#obtenNumODEsPublicadosPorIdioma(int, java.lang.String, java.lang.String)
     */
    public Object obtenNumODEsPublicadosPorIdioma(final int transform, final java.lang.String queryString, final java.lang.String idioma)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("idioma", idioma);
            java.util.List results = queryObject.list();
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Long"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
                    result = results.iterator().next();
                }
            }
            return result;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * @see es.pode.publicacion.negocio.dominio.OdePublicado#obtenNumODEsPublicados(int, java.lang.String)
     */
    public Object obtenNumODEsPublicados(final int transform, final java.lang.String queryString)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            java.util.List results = queryObject.list();
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Long"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
                    result = results.iterator().next();
                }
            }
            return result;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

	}