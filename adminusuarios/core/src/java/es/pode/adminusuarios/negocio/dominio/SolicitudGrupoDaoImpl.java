/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.dominio;

/**
 * @see es.pode.adminusuarios.negocio.dominio.SolicitudGrupo
 */
public class SolicitudGrupoDaoImpl
    extends es.pode.adminusuarios.negocio.dominio.SolicitudGrupoDaoBase
{
    /**
     * @see es.pode.adminusuarios.negocio.dominio.SolicitudGrupoDao#toSolicitudGrupoVO(es.pode.adminusuarios.negocio.dominio.SolicitudGrupo)
     */
    public es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO toSolicitudGrupoVO(final es.pode.adminusuarios.negocio.dominio.SolicitudGrupo entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO) 
                  this.getBeanMapper().map(entity, es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO.class, DEF_MAPPING_SOLICITUDGRUPO_SOLICITUDGRUPOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.adminusuarios.negocio.dominio.SolicitudGrupoDao#fromSolicitudGrupoVO(es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO)
	 */
    public es.pode.adminusuarios.negocio.dominio.SolicitudGrupo fromSolicitudGrupoVO(final es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromSolicitudGrupoVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.adminusuarios.negocio.dominio.SolicitudGrupoDao#fromSolicitudGrupoVO(es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO ,es.pode.adminusuarios.negocio.dominio.SolicitudGrupo)	 
	 */
    public void fromSolicitudGrupoVO(es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO vo, es.pode.adminusuarios.negocio.dominio.SolicitudGrupo entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromSolicitudGrupoVO(vo, entity);
    }
}