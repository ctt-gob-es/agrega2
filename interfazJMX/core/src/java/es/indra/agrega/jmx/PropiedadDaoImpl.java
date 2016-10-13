// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.indra.agrega.jmx;

/**
 * @see es.indra.agrega.jmx.Propiedad
 */
public class PropiedadDaoImpl
    extends es.indra.agrega.jmx.PropiedadDaoBase
{
    /**
     * @see es.indra.agrega.jmx.PropiedadDao#toPropiedadListItem(es.indra.agrega.jmx.Propiedad)
     */
    public es.indra.agrega.jmx.PropiedadVO toPropiedadVO(final es.indra.agrega.jmx.Propiedad entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.indra.agrega.jmx.PropiedadVO) 
                  this.getBeanMapper().map(entity, es.indra.agrega.jmx.PropiedadVO.class, DEF_MAPPING_PROPIEDAD_PROPIEDADVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.indra.agrega.jmx.PropiedadDao#fromPropiedadListItem(es.indra.agrega.jmx.PropiedadListItem)
	 */
    public es.indra.agrega.jmx.Propiedad fromPropiedadListItem(final es.indra.agrega.jmx.PropiedadVO vo) {
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
    public void fromPropiedadListItem(es.indra.agrega.jmx.PropiedadVO vo, es.indra.agrega.jmx.Propiedad entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPropiedadVO(vo, entity);
    }
}