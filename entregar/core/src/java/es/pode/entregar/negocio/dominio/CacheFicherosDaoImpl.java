/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.entregar.negocio.dominio;

/**
 * @see es.pode.entregar.negocio.dominio.CacheFicheros
 */
public class CacheFicherosDaoImpl
    extends es.pode.entregar.negocio.dominio.CacheFicherosDaoBase
{
    /**
     * @see es.pode.entregar.negocio.dominio.CacheFicherosDao#toFicheroVO(es.pode.entregar.negocio.dominio.CacheFicheros)
     */
    public es.pode.entregar.negocio.dominio.FicheroVO toFicheroVO(final es.pode.entregar.negocio.dominio.CacheFicheros entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.entregar.negocio.dominio.FicheroVO) 
                  this.getBeanMapper().map(entity, es.pode.entregar.negocio.dominio.FicheroVO.class, DEF_MAPPING_CACHEFICHEROS_FICHEROVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.entregar.negocio.dominio.CacheFicherosDao#fromFicheroVO(es.pode.entregar.negocio.dominio.FicheroVO)
	 */
    public es.pode.entregar.negocio.dominio.CacheFicheros fromFicheroVO(final es.pode.entregar.negocio.dominio.FicheroVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromFicheroVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.entregar.negocio.dominio.CacheFicherosDao#fromFicheroVO(es.pode.entregar.negocio.dominio.FicheroVO ,es.pode.entregar.negocio.dominio.CacheFicheros)	 
	 */
    public void fromFicheroVO(es.pode.entregar.negocio.dominio.FicheroVO vo, es.pode.entregar.negocio.dominio.CacheFicheros entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromFicheroVO(vo, entity);
    }
}