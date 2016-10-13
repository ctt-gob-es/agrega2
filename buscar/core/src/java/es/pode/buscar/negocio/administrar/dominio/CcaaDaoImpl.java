/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.administrar.dominio;

/**
 * @see es.pode.buscar.negocio.administrar.dominio.Ccaa
 */
public class CcaaDaoImpl
    extends es.pode.buscar.negocio.administrar.dominio.CcaaDaoBase
{
    /**
     * @see es.pode.buscar.negocio.administrar.dominio.CcaaDao#toCcaaVO(es.pode.buscar.negocio.administrar.dominio.Ccaa)
     */
    public es.pode.buscar.negocio.administrar.servicio.CcaaVO toCcaaVO(final es.pode.buscar.negocio.administrar.dominio.Ccaa entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.buscar.negocio.administrar.servicio.CcaaVO) 
                  this.getBeanMapper().map(entity, es.pode.buscar.negocio.administrar.servicio.CcaaVO.class, DEF_MAPPING_CCAA_CCAAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.buscar.negocio.administrar.dominio.CcaaDao#fromCcaaVO(es.pode.buscar.negocio.administrar.servicio.CcaaVO)
	 */
    public es.pode.buscar.negocio.administrar.dominio.Ccaa fromCcaaVO(final es.pode.buscar.negocio.administrar.servicio.CcaaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromCcaaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.buscar.negocio.administrar.dominio.CcaaDao#fromCcaaVO(es.pode.buscar.negocio.administrar.servicio.CcaaVO ,es.pode.buscar.negocio.administrar.dominio.Ccaa)	 
	 */
    public void fromCcaaVO(es.pode.buscar.negocio.administrar.servicio.CcaaVO vo, es.pode.buscar.negocio.administrar.dominio.Ccaa entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCcaaVO(vo, entity);
    }
}