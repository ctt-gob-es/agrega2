/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.instalador.negocio.dominio;

/**
 * @see es.pode.instalador.negocio.dominio.Propiedad
 */
public class PropiedadDaoImpl
    extends es.pode.instalador.negocio.dominio.PropiedadDaoBase
{
    /**
     * @see es.pode.instalador.negocio.dominio.PropiedadDao#toPropiedadVO(es.pode.instalador.negocio.dominio.Propiedad)
     */
    public es.pode.instalador.negocio.servicios.PropiedadVO toPropiedadVO(final es.pode.instalador.negocio.dominio.Propiedad entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.instalador.negocio.servicios.PropiedadVO) 
                  this.getBeanMapper().map(entity, es.pode.instalador.negocio.servicios.PropiedadVO.class, DEF_MAPPING_PROPIEDAD_PROPIEDADVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.instalador.negocio.dominio.PropiedadDao#fromPropiedadVO(es.pode.instalador.negocio.servicios.PropiedadVO)
	 */
    public es.pode.instalador.negocio.dominio.Propiedad fromPropiedadVO(final es.pode.instalador.negocio.servicios.PropiedadVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromPropiedadVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.instalador.negocio.dominio.PropiedadDao#fromPropiedadVO(es.pode.instalador.negocio.servicios.PropiedadVO ,es.pode.instalador.negocio.dominio.Propiedad)	 
	 */
    public void fromPropiedadVO(es.pode.instalador.negocio.servicios.PropiedadVO vo, es.pode.instalador.negocio.dominio.Propiedad entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromPropiedadVO(vo, entity);
    }
}