// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.descargas.dominio;

/**
 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga
 */
public class CategoriaDescargaDaoImpl
    extends es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDao#toCategoriaDescargaVO(es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga)
     */
    public es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO toCategoriaDescargaVO(final es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO.class, DEF_MAPPING_CATEGORIADESCARGA_CATEGORIADESCARGAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDao#fromCategoriaDescargaVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO)
	 */
    public es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga fromCategoriaDescargaVO(final es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromCategoriaDescargaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDao#fromCategoriaDescargaVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO ,es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga)	 
	 */
    public void fromCategoriaDescargaVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO vo, es.pode.contenidos.negocio.descargas.dominio.CategoriaDescarga entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaDescargaVO(vo, entity);
    }
}