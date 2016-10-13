// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.localizador.negocio.dominio;

/**
 * @see es.pode.localizador.negocio.dominio.Localizador
 */
public class LocalizadorDaoImpl
    extends es.pode.localizador.negocio.dominio.LocalizadorDaoBase
{
    /**
     * @see es.pode.localizador.negocio.dominio.LocalizadorDao#toLocalizadorVO(es.pode.localizador.negocio.dominio.Localizador)
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO toLocalizadorVO(final es.pode.localizador.negocio.dominio.Localizador entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.localizador.negocio.servicios.LocalizadorVO) 
                  this.getBeanMapper().map(entity, es.pode.localizador.negocio.servicios.LocalizadorVO.class, DEF_MAPPING_LOCALIZADOR_LOCALIZADORVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.localizador.negocio.dominio.LocalizadorDao#fromLocalizadorVO(es.pode.localizador.negocio.servicios.LocalizadorVO)
	 */
    public es.pode.localizador.negocio.dominio.Localizador fromLocalizadorVO(final es.pode.localizador.negocio.servicios.LocalizadorVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromLocalizadorVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.localizador.negocio.dominio.LocalizadorDao#fromLocalizadorVO(es.pode.localizador.negocio.servicios.LocalizadorVO ,es.pode.localizador.negocio.dominio.Localizador)	 
	 */
    public void fromLocalizadorVO(es.pode.localizador.negocio.servicios.LocalizadorVO vo, es.pode.localizador.negocio.dominio.Localizador entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromLocalizadorVO(vo, entity);
    }
}