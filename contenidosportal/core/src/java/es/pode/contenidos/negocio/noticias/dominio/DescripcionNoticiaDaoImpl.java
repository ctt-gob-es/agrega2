/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.noticias.dominio;

/**
 * @see es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia
 */
public class DescripcionNoticiaDaoImpl
    extends es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticiaDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticiaDao#toDescripcionNoticiaVO(es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia)
     */
    public es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO toDescripcionNoticiaVO(final es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO.class, DEF_MAPPING_DESCRIPCIONNOTICIA_DESCRIPCIONNOTICIAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticiaDao#fromDescripcionNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO)
	 */
    public es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia fromDescripcionNoticiaVO(final es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromDescripcionNoticiaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticiaDao#fromDescripcionNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO ,es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia)	 
	 */
    public void fromDescripcionNoticiaVO(es.pode.contenidos.negocio.noticias.servicio.DescripcionNoticiaVO vo, es.pode.contenidos.negocio.noticias.dominio.DescripcionNoticia entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromDescripcionNoticiaVO(vo, entity);
    }
}