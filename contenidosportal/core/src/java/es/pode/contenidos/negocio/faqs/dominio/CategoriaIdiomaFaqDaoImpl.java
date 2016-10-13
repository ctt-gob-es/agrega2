// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.faqs.dominio;

/**
 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq
 */
public class CategoriaIdiomaFaqDaoImpl
    extends es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaqDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaqDao#toCategoriaIdiomaFaqVO(es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq)
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO toCategoriaIdiomaFaqVO(final es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO.class, DEF_MAPPING_CATEGORIAIDIOMAFAQ_CATEGORIAIDIOMAFAQVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaqDao#fromCategoriaIdiomaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO)
	 */
    public es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq fromCategoriaIdiomaFaqVO(final es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromCategoriaIdiomaFaqVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaqDao#fromCategoriaIdiomaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO ,es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq)	 
	 */
    public void fromCategoriaIdiomaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaIdiomaFaqVO vo, es.pode.contenidos.negocio.faqs.dominio.CategoriaIdiomaFaq entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaIdiomaFaqVO(vo, entity);
    }
}