// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.configuracionPortal.dominio;

/**
 * @see es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal
 */
public class ConfiguracionPortalDaoImpl
    extends es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortalDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortalDao#toConfiguracionPortalVO(es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal)
     */
    public es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO toConfiguracionPortalVO(final es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO.class, DEF_MAPPING_CONFIGURACIONPORTAL_CONFIGURACIONPORTALVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortalDao#fromConfiguracionPortalVO(es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO)
	 */
    public es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal fromConfiguracionPortalVO(final es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromConfiguracionPortalVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortalDao#fromConfiguracionPortalVO(es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO ,es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal)	 
	 */
    public void fromConfiguracionPortalVO(es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO vo, es.pode.contenidos.negocio.configuracionPortal.dominio.ConfiguracionPortal entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromConfiguracionPortalVO(vo, entity);
    }
}