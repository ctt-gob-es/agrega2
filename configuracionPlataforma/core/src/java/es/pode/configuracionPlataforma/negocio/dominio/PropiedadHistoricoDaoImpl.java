// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.negocio.dominio;

/**
 * @see es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico
 */
public class PropiedadHistoricoDaoImpl
    extends es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoDaoBase
{
    /**
     * @see es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoDao#toPropiedadHistoricoVO(es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico)
     */
    public es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO toPropiedadHistoricoVO(final es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO) 
                  this.getBeanMapper().map(entity, es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO.class, DEF_MAPPING_PROPIEDADHISTORICO_PROPIEDADHISTORICOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoDao#fromPropiedadHistoricoVO(es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO)
	 */
    public es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico fromPropiedadHistoricoVO(final es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromPropiedadHistoricoVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoDao#fromPropiedadHistoricoVO(es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO ,es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico)	 
	 */
    public void fromPropiedadHistoricoVO(es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistoricoVO vo, es.pode.configuracionPlataforma.negocio.dominio.PropiedadHistorico entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPropiedadHistoricoVO(vo, entity);
    }
}