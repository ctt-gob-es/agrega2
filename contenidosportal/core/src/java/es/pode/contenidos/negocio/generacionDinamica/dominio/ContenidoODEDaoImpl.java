// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.generacionDinamica.dominio;

/**
 * @see es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE
 */
public class ContenidoODEDaoImpl
    extends es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODEDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODEDao#toContenidoODEVO(es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE)
     */
    public es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO toContenidoODEVO(final es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO.class, DEF_MAPPING_CONTENIDOODE_CONTENIDOODEVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODEDao#fromContenidoODEVO(es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO)
	 */
    public es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE fromContenidoODEVO(final es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromContenidoODEVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODEDao#fromContenidoODEVO(es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO ,es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE)	 
	 */
    public void fromContenidoODEVO(es.pode.contenidos.negocio.generacionDinamica.servicio.ContenidoODEVO vo, es.pode.contenidos.negocio.generacionDinamica.dominio.ContenidoODE entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromContenidoODEVO(vo, entity);
    }
}