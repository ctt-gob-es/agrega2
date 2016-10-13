// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.dominio;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @see es.pode.adminusuarios.negocio.dominio.GrupoPublico
 */
public class GrupoPublicoDaoImpl
    extends es.pode.adminusuarios.negocio.dominio.GrupoPublicoDaoBase
{
    /**
     * @see es.pode.adminusuarios.negocio.dominio.GrupoPublicoDao#toGrupoPublicoVO(es.pode.adminusuarios.negocio.dominio.GrupoPublico)
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO toGrupoPublicoVO(final es.pode.adminusuarios.negocio.dominio.GrupoPublico entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO) 
                  this.getBeanMapper().map(entity, es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO.class, DEF_MAPPING_GRUPOPUBLICO_GRUPOPUBLICOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.adminusuarios.negocio.dominio.GrupoPublicoDao#fromGrupoPublicoVO(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO)
	 */
    public es.pode.adminusuarios.negocio.dominio.GrupoPublico fromGrupoPublicoVO(final es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO vo) {
    	// default mapping between VO and entity
    	// @todo verify or customize behaviour of this mapping
    	// return super.fromUsuarioVO(vo);
    	GrupoPublico resultado = super.fromGrupoPublicoVO(vo);

    	Collection col = resultado.getOdeGrupo();
    	if (col == null) {

    	} else {
    	    if (!(col instanceof Set)) {
    		Set set = new HashSet();
    		set.addAll(col);
    		resultado.setOdeGrupo(set);
    	    }
    	}

    	return resultado;
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.adminusuarios.negocio.dominio.GrupoPublicoDao#fromGrupoPublicoVO(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO ,es.pode.adminusuarios.negocio.dominio.GrupoPublico)	 
	 */
    public void fromGrupoPublicoVO(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO vo, es.pode.adminusuarios.negocio.dominio.GrupoPublico entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromGrupoPublicoVO(vo, entity);
    }
    
    public GrupoPublico create(final es.pode.adminusuarios.negocio.dominio.GrupoPublico grupoPublico)
    {
        if (grupoPublico == null)
        {
            throw new IllegalArgumentException(
                "GrupoPublico.create - 'grupoPublico' can not be null");
        }
        /*
        if (grupoPublico.getUsuarioPublico() == null) {

    	} else {
    	    if (!(grupoPublico.getUsuarioPublico() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(grupoPublico.getUsuarioPublico());
    		grupoPublico.setUsuarioPublico(set);
    	    }
    	}
    	*/
        if (grupoPublico.getOdeGrupo() == null) {

    	} else {
    	    if (!(grupoPublico.getOdeGrupo() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(grupoPublico.getOdeGrupo());
    		grupoPublico.setOdeGrupo(set);
    	    }
    	}
        Object identifier = this.getHibernateTemplate().save(grupoPublico);
        grupoPublico.setId((java.lang.Long)identifier);
        return grupoPublico;
    }
    
    public void update(es.pode.adminusuarios.negocio.dominio.GrupoPublico grupoPublico) {
    	if (grupoPublico == null) {
    	    throw new IllegalArgumentException("grupoPublico.update - 'grupoPublico' can not be null");
    	}
    	if (grupoPublico.getOdeGrupo() == null) {

    	} else {
    	    if (!(grupoPublico.getOdeGrupo() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(grupoPublico.getOdeGrupo());
    		grupoPublico.setOdeGrupo(set);
    	    }
    	}
    	/*
    	if (grupoPublico.getUsuarioPublico() == null) {

    	} else {
    	    if (!(grupoPublico.getUsuarioPublico() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(grupoPublico.getUsuarioPublico());
    		grupoPublico.setUsuarioPublico(set);
    	    }
    	}
    	*/
    	this.getHibernateTemplate().update(grupoPublico);
    }
    
    
    
}