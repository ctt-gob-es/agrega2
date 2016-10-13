/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.modificador.negocio.dominio;

/**
 * @see es.pode.modificador.negocio.dominio.Modificacion
 */
public class ModificacionDaoImpl
    extends es.pode.modificador.negocio.dominio.ModificacionDaoBase
{
    /**
     * @see es.pode.modificador.negocio.dominio.ModificacionDao#toModificacionVO(es.pode.modificador.negocio.dominio.Modificacion)
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO toModificacionVO(final es.pode.modificador.negocio.dominio.Modificacion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.modificador.negocio.servicio.ModificacionVO) 
                  this.getBeanMapper().map(entity, es.pode.modificador.negocio.servicio.ModificacionVO.class, DEF_MAPPING_MODIFICACION_MODIFICACIONVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.modificador.negocio.dominio.ModificacionDao#fromModificacionVO(es.pode.modificador.negocio.servicio.ModificacionVO)
	 */
    public es.pode.modificador.negocio.dominio.Modificacion fromModificacionVO(final es.pode.modificador.negocio.servicio.ModificacionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromModificacionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.modificador.negocio.dominio.ModificacionDao#fromModificacionVO(es.pode.modificador.negocio.servicio.ModificacionVO ,es.pode.modificador.negocio.dominio.Modificacion)	 
	 */
    public void fromModificacionVO(es.pode.modificador.negocio.servicio.ModificacionVO vo, es.pode.modificador.negocio.dominio.Modificacion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromModificacionVO(vo, entity);
    }
}