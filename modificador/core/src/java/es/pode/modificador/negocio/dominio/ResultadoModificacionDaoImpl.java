// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.modificador.negocio.dominio;

import org.apache.commons.lang.StringUtils;

import es.pode.modificador.negocio.servicio.ResultadoModificacionVO;

/**
 * @see es.pode.modificador.negocio.dominio.ResultadoModificacion
 */
public class ResultadoModificacionDaoImpl
    extends es.pode.modificador.negocio.dominio.ResultadoModificacionDaoBase
{
    /**
     * @see es.pode.modificador.negocio.dominio.ResultadoModificacionDao#toResultadoModificacionVO(es.pode.modificador.negocio.dominio.ResultadoModificacion)
     */
    public es.pode.modificador.negocio.servicio.ResultadoModificacionVO toResultadoModificacionVO(final es.pode.modificador.negocio.dominio.ResultadoModificacion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
    	ResultadoModificacionVO vo =(es.pode.modificador.negocio.servicio.ResultadoModificacionVO)this.getBeanMapper().map(entity, es.pode.modificador.negocio.servicio.ResultadoModificacionVO.class, DEF_MAPPING_RESULTADOMODIFICACION_RESULTADOMODIFICACIONVO);
    	
    	// Correccion de problemas de Oracle. El tratamiento incorrecto de
		// pathBackup lleva a que se registren cadenas vacias para esta columna.
		// Para disminuir el impacto de los Portales llamantes a este servicio,
		// sigo enviando VOs con cadena vacia.
    	if(StringUtils.isEmpty(vo.getPathBackup())) vo.setPathBackup("");
    	
        return vo;
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.modificador.negocio.dominio.ResultadoModificacionDao#fromResultadoModificacionVO(es.pode.modificador.negocio.servicio.ResultadoModificacionVO)
	 */
    public es.pode.modificador.negocio.dominio.ResultadoModificacion fromResultadoModificacionVO(final es.pode.modificador.negocio.servicio.ResultadoModificacionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromResultadoModificacionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.modificador.negocio.dominio.ResultadoModificacionDao#fromResultadoModificacionVO(es.pode.modificador.negocio.servicio.ResultadoModificacionVO ,es.pode.modificador.negocio.dominio.ResultadoModificacion)	 
	 */
    public void fromResultadoModificacionVO(es.pode.modificador.negocio.servicio.ResultadoModificacionVO vo, es.pode.modificador.negocio.dominio.ResultadoModificacion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromResultadoModificacionVO(vo, entity);
    }
}