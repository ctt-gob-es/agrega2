// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.dominio;

/**
 * @see es.pode.adminusuarios.negocio.dominio.Rol
 */
public class RolDaoImpl extends es.pode.adminusuarios.negocio.dominio.RolDaoBase {
    /**
     * @see es.pode.adminusuarios.negocio.dominio.RolDao#toRolVO(es.pode.adminusuarios.negocio.dominio.Rol)
     */
    public es.pode.adminusuarios.negocio.servicios.RolVO toRolVO(final es.pode.adminusuarios.negocio.dominio.Rol entity) {
	// default mapping between entity and VO
	// @todo verify or customize behaviour of this mapping
	return (es.pode.adminusuarios.negocio.servicios.RolVO) this.getBeanMapper().map(entity,
		es.pode.adminusuarios.negocio.servicios.RolVO.class, DEF_MAPPING_ROL_ROLVO);
    }

    /**
     * Copy a VO to a new entity.
     * @param vo the source bean (VO)
     * @return A new entity created with the values extracted from the vo.
     * @see es.pode.adminusuarios.negocio.dominio.RolDao#fromRolVO(es.pode.adminusuarios.negocio.servicios.RolVO)
     */
    public es.pode.adminusuarios.negocio.dominio.Rol fromRolVO(final es.pode.adminusuarios.negocio.servicios.RolVO vo) {
	// default mapping between VO and entity
	// @todo verify or customize behaviour of this mapping
	return super.fromRolVO(vo);
    }

    /**
     * Copy a VO to an existing entity.
     * @param vo the source bean (VO)
     * @param entity the destination bean (an existing entity)
     * @see es.pode.adminusuarios.negocio.dominio.RolDao#fromRolVO(es.pode.adminusuarios.negocio.servicios.RolVO
     *      ,es.pode.adminusuarios.negocio.dominio.Rol)
     */
    public void fromRolVO(es.pode.adminusuarios.negocio.servicios.RolVO vo,
	    es.pode.adminusuarios.negocio.dominio.Rol entity) {
	// default mapping between VO and entity
	// @todo verify or customize behaviour of this mapping
	super.fromRolVO(vo, entity);
    }
}