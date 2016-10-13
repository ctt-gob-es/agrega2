// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.dominio;

/**
 * @see es.pode.dri.negocio.dominio.Sesion
 */
public class SesionDaoImpl
    extends es.pode.dri.negocio.dominio.SesionDaoBase
{
    /**
     * @see es.pode.dri.negocio.dominio.SesionDao#toSesionVO(es.pode.dri.negocio.dominio.Sesion)
     */
    public es.pode.dri.negocio.servicios.SesionVO toSesionVO(final es.pode.dri.negocio.dominio.Sesion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.dri.negocio.servicios.SesionVO) 
                  this.getBeanMapper().map(entity, es.pode.dri.negocio.servicios.SesionVO.class, DEF_MAPPING_SESION_SESIONVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.dri.negocio.dominio.SesionDao#fromSesionVO(es.pode.dri.negocio.servicios.SesionVO)
	 */
    public es.pode.dri.negocio.dominio.Sesion fromSesionVO(final es.pode.dri.negocio.servicios.SesionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromSesionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.dri.negocio.dominio.SesionDao#fromSesionVO(es.pode.dri.negocio.servicios.SesionVO ,es.pode.dri.negocio.dominio.Sesion)	 
	 */
    public void fromSesionVO(es.pode.dri.negocio.servicios.SesionVO vo, es.pode.dri.negocio.dominio.Sesion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromSesionVO(vo, entity);
    }
}