// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.dominio;

/**
 * @see es.pode.auditoria.negocio.dominio.PeticionFeed
 */
public class PeticionFeedDaoImpl
    extends es.pode.auditoria.negocio.dominio.PeticionFeedDaoBase
{
    /**
     * @see es.pode.auditoria.negocio.dominio.PeticionFeedDao#toPeticionFeedVO(es.pode.auditoria.negocio.dominio.PeticionFeed)
     */
    public es.pode.auditoria.negocio.servicios.PeticionFeedVO toPeticionFeedVO(final es.pode.auditoria.negocio.dominio.PeticionFeed entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.auditoria.negocio.servicios.PeticionFeedVO) 
                  this.getBeanMapper().map(entity, es.pode.auditoria.negocio.servicios.PeticionFeedVO.class, DEF_MAPPING_PETICIONFEED_PETICIONFEEDVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.auditoria.negocio.dominio.PeticionFeedDao#fromPeticionFeedVO(es.pode.auditoria.negocio.servicios.PeticionFeedVO)
	 */
    public es.pode.auditoria.negocio.dominio.PeticionFeed fromPeticionFeedVO(final es.pode.auditoria.negocio.servicios.PeticionFeedVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromPeticionFeedVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.auditoria.negocio.dominio.PeticionFeedDao#fromPeticionFeedVO(es.pode.auditoria.negocio.servicios.PeticionFeedVO ,es.pode.auditoria.negocio.dominio.PeticionFeed)	 
	 */
    public void fromPeticionFeedVO(es.pode.auditoria.negocio.servicios.PeticionFeedVO vo, es.pode.auditoria.negocio.dominio.PeticionFeed entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPeticionFeedVO(vo, entity);
    }
}