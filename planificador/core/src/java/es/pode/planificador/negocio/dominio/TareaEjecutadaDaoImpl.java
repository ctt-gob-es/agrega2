/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.dominio;

import es.pode.planificador.negocio.servicios.TareaEjecutadaExplotacionVO;

/**
 * @see es.pode.planificador.negocio.dominio.TareaEjecutada
 */
public class TareaEjecutadaDaoImpl
    extends es.pode.planificador.negocio.dominio.TareaEjecutadaDaoBase
{
    /**
     * @see es.pode.planificador.negocio.dominio.TareaEjecutadaDao#toTareaEjecutadaVO(es.pode.planificador.negocio.dominio.TareaEjecutada)
     */
    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO toTareaEjecutadaVO(final es.pode.planificador.negocio.dominio.TareaEjecutada entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.planificador.negocio.servicios.TareaEjecutadaVO) 
                  this.getBeanMapper().map(entity, es.pode.planificador.negocio.servicios.TareaEjecutadaVO.class, DEF_MAPPING_TAREAEJECUTADA_TAREAEJECUTADAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.planificador.negocio.dominio.TareaEjecutadaDao#fromTareaEjecutadaVO(es.pode.planificador.negocio.servicios.TareaEjecutadaVO)
	 */
    public es.pode.planificador.negocio.dominio.TareaEjecutada fromTareaEjecutadaVO(final es.pode.planificador.negocio.servicios.TareaEjecutadaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromTareaEjecutadaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.planificador.negocio.dominio.TareaEjecutadaDao#fromTareaEjecutadaVO(es.pode.planificador.negocio.servicios.TareaEjecutadaVO ,es.pode.planificador.negocio.dominio.TareaEjecutada)	 
	 */
    public void fromTareaEjecutadaVO(es.pode.planificador.negocio.servicios.TareaEjecutadaVO vo, es.pode.planificador.negocio.dominio.TareaEjecutada entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromTareaEjecutadaVO(vo, entity);
    }


	public TareaEjecutadaExplotacionVO toTareaEjecutadaExplotacionVO(
			TareaEjecutada entity) {
		// TODO Auto-generated method stub
		return null;
	}
}