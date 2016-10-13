/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.descargas.dominio;

/**
 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc
 */
public class CategoriaDescargaDescDaoImpl
    extends es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDescDaoBase
{
    /**
     * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDescDao#toCategoriaDescargaDescVO(es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc)
     */
    public es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO toCategoriaDescargaDescVO(final es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO) 
                  this.getBeanMapper().map(entity, es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO.class, DEF_MAPPING_CATEGORIADESCARGADESC_CATEGORIADESCARGADESCVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDescDao#fromCategoriaDescargaDescVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO)
	 */
    public es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc fromCategoriaDescargaDescVO(final es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromCategoriaDescargaDescVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDescDao#fromCategoriaDescargaDescVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO ,es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc)	 
	 */
    public void fromCategoriaDescargaDescVO(es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaDescVO vo, es.pode.contenidos.negocio.descargas.dominio.CategoriaDescargaDesc entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromCategoriaDescargaDescVO(vo, entity);
    }
}