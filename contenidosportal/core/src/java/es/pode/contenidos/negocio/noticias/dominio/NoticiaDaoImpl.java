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
 * @see es.pode.contenidos.negocio.noticias.dominio.Noticia
 */
public class NoticiaDaoImpl
    extends es.pode.contenidos.negocio.noticias.dominio.NoticiaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.noticias.dominio.NoticiaDao#toNoticiaVO(es.pode.contenidos.negocio.noticias.dominio.Noticia)
     */
    public es.pode.contenidos.negocio.noticias.servicio.NoticiaVO toNoticiaVO(final es.pode.contenidos.negocio.noticias.dominio.Noticia entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.noticias.servicio.NoticiaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.noticias.servicio.NoticiaVO.class, DEF_MAPPING_NOTICIA_NOTICIAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.noticias.dominio.NoticiaDao#fromNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO)
	 */
    public es.pode.contenidos.negocio.noticias.dominio.Noticia fromNoticiaVO(final es.pode.contenidos.negocio.noticias.servicio.NoticiaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
    	Noticia resultado = super.fromNoticiaVO(vo);
    	Collection colec = resultado.getDescripcionNoticia();
    	if (!(colec == null))
    	{
    		if(!(colec instanceof Set))
    		{
    			Set set = new HashSet();
    			set.addAll(colec);
    			resultado.setDescripcionNoticia(set);    			
    		}
    	}
    	return resultado;
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.noticias.dominio.NoticiaDao#fromNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO ,es.pode.contenidos.negocio.noticias.dominio.Noticia)	 
	 */
    public void fromNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.NoticiaVO vo, es.pode.contenidos.negocio.noticias.dominio.Noticia entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromNoticiaVO(vo, entity);
    }
}