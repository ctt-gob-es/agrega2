// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

import es.pode.publicacion.negocio.servicios.TransicionAutorVO;

/**
 * @see es.pode.publicacion.negocio.dominio.Transicion
 */
public class TransicionDaoImpl
    extends es.pode.publicacion.negocio.dominio.TransicionDaoBase
{
    /**
     * @see es.pode.publicacion.negocio.dominio.TransicionDao#toTransicionVO(es.pode.publicacion.negocio.dominio.Transicion)
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO toTransicionVO(final es.pode.publicacion.negocio.dominio.Transicion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.TransicionVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.TransicionVO.class, DEF_MAPPING_TRANSICION_TRANSICIONVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.TransicionDao#fromTransicionVO(es.pode.publicacion.negocio.servicios.TransicionVO)
	 */
    public es.pode.publicacion.negocio.dominio.Transicion fromTransicionVO(final es.pode.publicacion.negocio.servicios.TransicionVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromTransicionVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.TransicionDao#fromTransicionVO(es.pode.publicacion.negocio.servicios.TransicionVO ,es.pode.publicacion.negocio.dominio.Transicion)	 
	 */
    public void fromTransicionVO(es.pode.publicacion.negocio.servicios.TransicionVO vo, es.pode.publicacion.negocio.dominio.Transicion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromTransicionVO(vo, entity);
    }
    
    /**
     * @see es.pode.publicacion.negocio.dominio.TransicionDao#toIdUsuarioVO(es.pode.publicacion.negocio.dominio.Transicion)
     */
    public es.pode.publicacion.negocio.servicios.IdUsuarioVO toIdUsuarioVO(final es.pode.publicacion.negocio.dominio.Transicion entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.IdUsuarioVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.IdUsuarioVO.class, DEF_MAPPING_TRANSICION_IDUSUARIOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.TransicionDao#fromIdUsuarioVO(es.pode.publicacion.negocio.servicios.IdUsuarioVO)
	 */
    public es.pode.publicacion.negocio.dominio.Transicion fromIdUsuarioVO(final es.pode.publicacion.negocio.servicios.IdUsuarioVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromIdUsuarioVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.TransicionDao#fromIdUsuarioVO(es.pode.publicacion.negocio.servicios.IdUsuarioVO ,es.pode.publicacion.negocio.dominio.Transicion)	 
	 */
    public void fromIdUsuarioVO(es.pode.publicacion.negocio.servicios.IdUsuarioVO vo, es.pode.publicacion.negocio.dominio.Transicion entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromIdUsuarioVO(vo, entity);
    }


	public TransicionAutorVO toTransicionAutorVO(Transicion entity) {
		// TODO Auto-generated method stub
		  return (es.pode.publicacion.negocio.servicios.TransicionAutorVO) 
          this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.TransicionAutorVO.class, DEF_MAPPING_TRANSICION_TRANSICIONAUTORVO);
	}
	
	 public es.pode.publicacion.negocio.dominio.Transicion fromTransicionAutorVO(final es.pode.publicacion.negocio.servicios.TransicionAutorVO vo) {
	        //default mapping between VO and entity
	        //@todo verify or customize behaviour of this mapping
	        return super.fromTransicionAutorVO(vo);
	    }

		/**
		 * Copy a VO to an existing entity.
		 * @param vo The source bean (VO)
		 * @param entity The destination bean (an existing entity)
		 * @see es.pode.publicacion.negocio.dominio.TransicionDao#fromTransicionVO(es.pode.publicacion.negocio.servicios.TransicionVO ,es.pode.publicacion.negocio.dominio.Transicion)	 
		 */
	    public void fromTransicionAutorVO(es.pode.publicacion.negocio.servicios.TransicionAutorVO vo, es.pode.publicacion.negocio.dominio.Transicion entity) {
	        //default mapping between VO and entity
	        //@todo verify or customize behaviour of this mapping
	        super.fromTransicionAutorVO(vo, entity);
	    }
}