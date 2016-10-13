// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.noticias.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia
 */
public class CategoriaNoticiaDaoImpl
    extends es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticiaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticiaDao#toCategoriaNoticiaVO(es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia)
     */
    public es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO toCategoriaNoticiaVO(final es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO.class, DEF_MAPPING_CATEGORIANOTICIA_CATEGORIANOTICIAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticiaDao#fromCategoriaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO)
	 */
    public es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia fromCategoriaNoticiaVO(final es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
    	CategoriaNoticia resultado = super.fromCategoriaNoticiaVO(vo);
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
	 * @see es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticiaDao#fromCategoriaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO ,es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia)	 
	 */
    public void fromCategoriaNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.CategoriaNoticiaVO vo, es.pode.contenidos.negocio.noticias.dominio.CategoriaNoticia entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaNoticiaVO(vo, entity);
    }
}