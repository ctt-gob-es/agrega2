// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.dominio;


/**
 * @see es.pode.adminusuarios.negocio.dominio.OdeGrupo
 */
public class OdeGrupoDaoImpl
    extends es.pode.adminusuarios.negocio.dominio.OdeGrupoDaoBase
{
    /**
     * @see es.pode.adminusuarios.negocio.dominio.OdeGrupoDao#toOdeGrupoVO(es.pode.adminusuarios.negocio.dominio.OdeGrupo)
     */
    public es.pode.adminusuarios.negocio.servicios.OdeGrupoVO toOdeGrupoVO(final es.pode.adminusuarios.negocio.dominio.OdeGrupo entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.adminusuarios.negocio.servicios.OdeGrupoVO) 
                  this.getBeanMapper().map(entity, es.pode.adminusuarios.negocio.servicios.OdeGrupoVO.class, DEF_MAPPING_ODEGRUPO_ODEGRUPOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.adminusuarios.negocio.dominio.OdeGrupoDao#fromOdeGrupoVO(es.pode.adminusuarios.negocio.servicios.OdeGrupoVO)
	 */
    public es.pode.adminusuarios.negocio.dominio.OdeGrupo fromOdeGrupoVO(final es.pode.adminusuarios.negocio.servicios.OdeGrupoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromOdeGrupoVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.adminusuarios.negocio.dominio.OdeGrupoDao#fromOdeGrupoVO(es.pode.adminusuarios.negocio.servicios.OdeGrupoVO ,es.pode.adminusuarios.negocio.dominio.OdeGrupo)	 
	 */
    public void fromOdeGrupoVO(es.pode.adminusuarios.negocio.servicios.OdeGrupoVO vo, es.pode.adminusuarios.negocio.dominio.OdeGrupo entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromOdeGrupoVO(vo, entity);
    }
    
    
}