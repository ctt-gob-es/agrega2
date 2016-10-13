// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.faqs.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq
 */
public class CategoriaFaqDaoImpl
    extends es.pode.contenidos.negocio.faqs.dominio.CategoriaFaqDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaFaqDao#toCategoriaFaqVO(es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq)
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO toCategoriaFaqVO(final es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO.class, DEF_MAPPING_CATEGORIAFAQ_CATEGORIAFAQVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaFaqDao#fromCategoriaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO)
	 */
    public es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq fromCategoriaFaqVO(final es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        //return super.fromCategoriaFaqVO(vo);
    	
    	CategoriaFaq resultado = super.fromCategoriaFaqVO(vo);
    	Collection colec = resultado.getCategoriaIdioma();
    	if (!(colec == null))
    	{
    		if(!(colec instanceof Set))
    		{
    			Set set = new HashSet();
    			set.addAll(colec);
    			resultado.setCategoriaIdioma(set);    			
    		}
    	}
    	return resultado;

    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.faqs.dominio.CategoriaFaqDao#fromCategoriaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO ,es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq)	 
	 */
    public void fromCategoriaFaqVO(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO vo, es.pode.contenidos.negocio.faqs.dominio.CategoriaFaq entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaFaqVO(vo, entity);
    }
}