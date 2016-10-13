// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.negocio.dominio;

/**
 * @see es.indra.agrega.jmx.Propiedad
 */
public class PropiedadDaoImpl
    extends es.pode.configuracionPlataforma.negocio.dominio.PropiedadDaoBase
{
    /**
     * @see es.indra.agrega.jmx.PropiedadDao#toPropiedadListItem(es.indra.agrega.jmx.Propiedad)
     */
    public es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO toPropiedadVO(final es.pode.configuracionPlataforma.negocio.dominio.Propiedad entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO) 
                  this.getBeanMapper().map(entity, es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO.class, DEF_MAPPING_PROPIEDAD_PROPIEDADVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.indra.agrega.jmx.PropiedadDao#fromPropiedadListItem(es.indra.agrega.jmx.PropiedadListItem)
	 */
    public es.pode.configuracionPlataforma.negocio.dominio.Propiedad fromPropiedadListItem(final es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromPropiedadVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.indra.agrega.jmx.PropiedadDao#fromPropiedadListItem(es.indra.agrega.jmx.PropiedadListItem ,es.indra.agrega.jmx.Propiedad)	 
	 */
    public void fromPropiedadListItem(es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO vo, es.pode.configuracionPlataforma.negocio.dominio.Propiedad entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPropiedadVO(vo, entity);
    }
}