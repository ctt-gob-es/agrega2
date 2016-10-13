// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.noticias.dominio;

/**
 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia
 */
public class CategoriaIdiomaNoticiaDaoImpl
    extends es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticiaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticiaDao#toCategoriaIdiomaNoticiaVO(es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia)
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO toCategoriaIdiomaNoticiaVO(final es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO.class, DEF_MAPPING_CATEGORIAIDIOMANOTICIA_CATEGORIAIDIOMANOTICIAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticiaDao#fromCategoriaIdiomaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO)
	 */
    public es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia fromCategoriaIdiomaNoticiaVO(final es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromCategoriaIdiomaNoticiaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticiaDao#fromCategoriaIdiomaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO ,es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia)	 
	 */
    public void fromCategoriaIdiomaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaIdiomaNoticiaVO vo, es.pode.contenidos.negocio.noticias.dominio.CategoriaIdiomaNoticia entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaIdiomaNoticiaVO(vo, entity);
    }
}