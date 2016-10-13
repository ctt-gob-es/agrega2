// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.descargas.dominio;

/**
 * @see es.pode.contenidos.negocio.descargas.dominio.Descarga
 */
public class DescargaDaoImpl
    extends es.pode.contenidos.negocio.descargas.dominio.DescargaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.descargas.dominio.DescargaDao#toDescargaVO(es.pode.contenidos.negocio.descargas.dominio.Descarga)
     */
    public es.pode.contenidos.negocio.descargas.servicio.DescargaVO toDescargaVO(final es.pode.contenidos.negocio.descargas.dominio.Descarga entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.descargas.servicio.DescargaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.descargas.servicio.DescargaVO.class, DEF_MAPPING_DESCARGA_DESCARGAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.descargas.dominio.DescargaDao#fromDescargaVO(es.pode.contenidos.negocio.descargas.servicio.DescargaVO)
	 */
    public es.pode.contenidos.negocio.descargas.dominio.Descarga fromDescargaVO(final es.pode.contenidos.negocio.descargas.servicio.DescargaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromDescargaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.descargas.dominio.DescargaDao#fromDescargaVO(es.pode.contenidos.negocio.descargas.servicio.DescargaVO ,es.pode.contenidos.negocio.descargas.dominio.Descarga)	 
	 */
    public void fromDescargaVO(es.pode.contenidos.negocio.descargas.servicio.DescargaVO vo, es.pode.contenidos.negocio.descargas.dominio.Descarga entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromDescargaVO(vo, entity);
    }
}