// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

/**
 * @see es.pode.publicacion.negocio.dominio.Estado
 */
public class EstadoDaoImpl
    extends es.pode.publicacion.negocio.dominio.EstadoDaoBase
{
    /**
     * @see es.pode.publicacion.negocio.dominio.EstadoDao#toEstadoVO(es.pode.publicacion.negocio.dominio.Estado)
     */
    public es.pode.publicacion.negocio.servicios.EstadoVO toEstadoVO(final es.pode.publicacion.negocio.dominio.Estado entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.EstadoVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.EstadoVO.class, DEF_MAPPING_ESTADO_ESTADOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.EstadoDao#fromEstadoVO(es.pode.publicacion.negocio.servicios.EstadoVO)
	 */
    public es.pode.publicacion.negocio.dominio.Estado fromEstadoVO(final es.pode.publicacion.negocio.servicios.EstadoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromEstadoVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.EstadoDao#fromEstadoVO(es.pode.publicacion.negocio.servicios.EstadoVO ,es.pode.publicacion.negocio.dominio.Estado)	 
	 */
    public void fromEstadoVO(es.pode.publicacion.negocio.servicios.EstadoVO vo, es.pode.publicacion.negocio.dominio.Estado entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromEstadoVO(vo, entity);
    }
}