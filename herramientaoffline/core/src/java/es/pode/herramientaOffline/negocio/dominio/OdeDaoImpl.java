// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.herramientaOffline.negocio.dominio;

/**
 * @see es.pode.herramientaOffline.negocio.dominio.Ode
 */
public class OdeDaoImpl
    extends es.pode.herramientaOffline.negocio.dominio.OdeDaoBase
{
    /**
     * @see es.pode.herramientaOffline.negocio.dominio.OdeDao#toOdeVO(es.pode.herramientaOffline.negocio.dominio.Ode)
     */
    public es.pode.herramientaOffline.negocio.soporte.OdeVO toOdeVO(final es.pode.herramientaOffline.negocio.dominio.Ode entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.herramientaOffline.negocio.soporte.OdeVO) 
                  this.getBeanMapper().map(entity, es.pode.herramientaOffline.negocio.soporte.OdeVO.class, DEF_MAPPING_ODE_ODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.herramientaOffline.negocio.dominio.OdeDao#fromOdeVO(es.pode.herramientaOffline.negocio.soporte.OdeVO)
	 */
    public es.pode.herramientaOffline.negocio.dominio.Ode fromOdeVO(final es.pode.herramientaOffline.negocio.soporte.OdeVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromOdeVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.herramientaOffline.negocio.dominio.OdeDao#fromOdeVO(es.pode.herramientaOffline.negocio.soporte.OdeVO ,es.pode.herramientaOffline.negocio.dominio.Ode)	 
	 */
    public void fromOdeVO(es.pode.herramientaOffline.negocio.soporte.OdeVO vo, es.pode.herramientaOffline.negocio.dominio.Ode entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromOdeVO(vo, entity);
    }
}