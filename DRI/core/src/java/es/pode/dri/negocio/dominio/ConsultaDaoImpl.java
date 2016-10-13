/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.dominio;

/**
 * @see es.pode.dri.negocio.dominio.Consulta
 */
public class ConsultaDaoImpl
    extends es.pode.dri.negocio.dominio.ConsultaDaoBase
{
    /**
     * @see es.pode.dri.negocio.dominio.ConsultaDao#toConsultaVO(es.pode.dri.negocio.dominio.Consulta)
     */
    public es.pode.dri.negocio.servicios.ConsultaVO toConsultaVO(final es.pode.dri.negocio.dominio.Consulta entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.dri.negocio.servicios.ConsultaVO) 
                  this.getBeanMapper().map(entity, es.pode.dri.negocio.servicios.ConsultaVO.class, DEF_MAPPING_CONSULTA_CONSULTAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.dri.negocio.dominio.ConsultaDao#fromConsultaVO(es.pode.dri.negocio.servicios.ConsultaVO)
	 */
    public es.pode.dri.negocio.dominio.Consulta fromConsultaVO(final es.pode.dri.negocio.servicios.ConsultaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromConsultaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.dri.negocio.dominio.ConsultaDao#fromConsultaVO(es.pode.dri.negocio.servicios.ConsultaVO ,es.pode.dri.negocio.dominio.Consulta)	 
	 */
    public void fromConsultaVO(es.pode.dri.negocio.servicios.ConsultaVO vo, es.pode.dri.negocio.dominio.Consulta entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromConsultaVO(vo, entity);
    }
}