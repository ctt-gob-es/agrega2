// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.faqs.dominio;

/**
 * @see es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq
 */
public class DescripcionFaqDaoImpl
    extends es.pode.contenidos.negocio.faqs.dominio.DescripcionFaqDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.faqs.dominio.DescripcionFaqDao#toDescripcionFaqVO(es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq)
     */
    public es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO toDescripcionFaqVO(final es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO.class, DEF_MAPPING_DESCRIPCIONFAQ_DESCRIPCIONFAQVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.faqs.dominio.DescripcionFaqDao#fromDescripcionFaqVO(es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO)
	 */
    public es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq fromDescripcionFaqVO(final es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromDescripcionFaqVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.faqs.dominio.DescripcionFaqDao#fromDescripcionFaqVO(es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO ,es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq)	 
	 */
    public void fromDescripcionFaqVO(es.pode.contenidos.negocio.faqs.servicio.DescripcionFaqVO vo, es.pode.contenidos.negocio.faqs.dominio.DescripcionFaq entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromDescripcionFaqVO(vo, entity);
    }
}