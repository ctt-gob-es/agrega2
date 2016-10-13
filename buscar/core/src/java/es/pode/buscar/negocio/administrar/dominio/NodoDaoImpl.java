// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.administrar.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @see es.pode.buscar.negocio.administrar.dominio.Nodo
 */
public class NodoDaoImpl
    extends es.pode.buscar.negocio.administrar.dominio.NodoDaoBase
{
    /**
     * @see es.pode.buscar.negocio.administrar.dominio.NodoDao#toNodoVO(es.pode.buscar.negocio.administrar.dominio.Nodo)
     */
    public es.pode.buscar.negocio.administrar.servicio.NodoVO toNodoVO(final es.pode.buscar.negocio.administrar.dominio.Nodo entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.buscar.negocio.administrar.servicio.NodoVO) 
                  this.getBeanMapper().map(entity, es.pode.buscar.negocio.administrar.servicio.NodoVO.class, DEF_MAPPING_NODO_NODOVO);
    }


	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.buscar.negocio.administrar.dominio.NodoDao#fromNodoVO(es.pode.buscar.negocio.administrar.servicio.NodoVO)
	 */
    public es.pode.buscar.negocio.administrar.dominio.Nodo fromNodoVO(final es.pode.buscar.negocio.administrar.servicio.NodoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        Nodo resultado = super.fromNodoVO(vo);
        Collection col = resultado.getServicio();
        if (!(col == null)){
        	if (!(col instanceof Set)){
        		Set set = new HashSet();
        		set.addAll(col);
        		resultado.setServicio(set);
        	}
        }
        return resultado;
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.buscar.negocio.administrar.dominio.NodoDao#fromNodoVO(es.pode.buscar.negocio.administrar.servicio.NodoVO ,es.pode.buscar.negocio.administrar.dominio.Nodo)	 
	 */
    public void fromNodoVO(es.pode.buscar.negocio.administrar.servicio.NodoVO vo, es.pode.buscar.negocio.administrar.dominio.Nodo entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromNodoVO(vo, entity);
    }
}