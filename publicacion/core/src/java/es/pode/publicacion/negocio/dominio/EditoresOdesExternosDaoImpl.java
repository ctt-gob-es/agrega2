// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

/**
 * @see es.pode.publicacion.negocio.dominio.EditoresOdesExternos
 */
public class EditoresOdesExternosDaoImpl
    extends es.pode.publicacion.negocio.dominio.EditoresOdesExternosDaoBase
{
    /**
     * @see es.pode.publicacion.negocio.dominio.EditoresOdesExternosDao#toEditoresOdesExternosVO(es.pode.publicacion.negocio.dominio.EditoresOdesExternos)
     */
    public es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO toEditoresOdesExternosVO(final es.pode.publicacion.negocio.dominio.EditoresOdesExternos entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO.class, DEF_MAPPING_EDITORESODESEXTERNOS_EDITORESODESEXTERNOSVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.EditoresOdesExternosDao#fromEditoresOdesExternosVO(es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO)
	 */
    public es.pode.publicacion.negocio.dominio.EditoresOdesExternos fromEditoresOdesExternosVO(final es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromEditoresOdesExternosVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.EditoresOdesExternosDao#fromEditoresOdesExternosVO(es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO ,es.pode.publicacion.negocio.dominio.EditoresOdesExternos)	 
	 */
    public void fromEditoresOdesExternosVO(es.pode.publicacion.negocio.servicios.EditoresOdesExternosVO vo, es.pode.publicacion.negocio.dominio.EditoresOdesExternos entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromEditoresOdesExternosVO(vo, entity);
    }
}