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
 * @see es.pode.adminusuarios.negocio.dominio.UsuarioPublico
 */
public class UsuarioPublicoDaoImpl
    extends es.pode.adminusuarios.negocio.dominio.UsuarioPublicoDaoBase
{
    /**
     * @see es.pode.adminusuarios.negocio.dominio.UsuarioPublicoDao#toUsuarioPublicoVO(es.pode.adminusuarios.negocio.dominio.UsuarioPublico)
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO toUsuarioPublicoVO(final es.pode.adminusuarios.negocio.dominio.UsuarioPublico entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO) 
                  this.getBeanMapper().map(entity, es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO.class, DEF_MAPPING_USUARIOPUBLICO_USUARIOPUBLICOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.adminusuarios.negocio.dominio.UsuarioPublicoDao#fromUsuarioPublicoVO(es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO)
	 */
    public es.pode.adminusuarios.negocio.dominio.UsuarioPublico fromUsuarioPublicoVO(final es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping

    	UsuarioPublico resultado = super.fromUsuarioPublicoVO(vo);

    	Collection col = resultado.getGrupoPublico();
    	if (col == null) {

    	} else {
    	    if (!(col instanceof Set)) {
    		Set set = new HashSet();
    		set.addAll(col);
    		resultado.setGrupoPublico(set);
    	    }
    	}
    	
    	Collection colContacto = resultado.getContacto();
    	if (col == null) {

    	} else {
    	    if (!(colContacto instanceof Set)) {
    		Set set = new HashSet();
    		set.addAll(colContacto);
    		resultado.setContacto(set);
    	    }
    	}
    	
    	Collection colFavorito = resultado.getFavoritoUsuario();
    	if (col == null) {

    	} else {
    	    if (!(colFavorito instanceof Set)) {
    		Set set = new HashSet();
    		set.addAll(colFavorito);
    		resultado.setFavoritoUsuario(set);
    	    }
    	}

    	return resultado;
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.adminusuarios.negocio.dominio.UsuarioPublicoDao#fromUsuarioPublicoVO(es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO ,es.pode.adminusuarios.negocio.dominio.UsuarioPublico)	 
	 */
    public void fromUsuarioPublicoVO(es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO vo, es.pode.adminusuarios.negocio.dominio.UsuarioPublico entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromUsuarioPublicoVO(vo, entity);
    }
    
    public void update(es.pode.adminusuarios.negocio.dominio.UsuarioPublico usuarioPublico) {
    	if (usuarioPublico == null) {
    	    throw new IllegalArgumentException("usuarioPublico.update - 'usuarioPublico' can not be null");
    	}
    	if (usuarioPublico.getGrupoPublico() == null) {

    	} else {
    	    if (!(usuarioPublico.getGrupoPublico() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(usuarioPublico.getGrupoPublico());
    		usuarioPublico.setGrupoPublico(set);
    	    }
    	}
    	if (usuarioPublico.getFavoritoUsuario() == null) {

    	} else {
    	    if (!(usuarioPublico.getFavoritoUsuario() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(usuarioPublico.getFavoritoUsuario());
    		usuarioPublico.setFavoritoUsuario(set);
    	    }
    	}
    	if (usuarioPublico.getContacto() == null) {

    	} else {
    	    if (!(usuarioPublico.getContacto() instanceof Set)) {

    		Set set = new HashSet();
    		set.addAll(usuarioPublico.getContacto());
    		usuarioPublico.setContacto(set);
    	    }
    	}
    	this.getHibernateTemplate().update(usuarioPublico);
    }
}