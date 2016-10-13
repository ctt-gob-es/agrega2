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
 * @see es.pode.adminusuarios.negocio.dominio.Grupo
 */
public class GrupoDaoImpl extends es.pode.adminusuarios.negocio.dominio.GrupoDaoBase {

    /**
         * @see es.pode.adminusuarios.negocio.dominio.GrupoDao#toGrupoVO(es.pode.adminusuarios.negocio.dominio.Grupo)
         */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO toGrupoVO(
	    final es.pode.adminusuarios.negocio.dominio.Grupo entity) {
	// default mapping between entity and VO
	// @todo verify or customize behaviour of this mapping
	return (es.pode.adminusuarios.negocio.servicios.GrupoVO) this.getBeanMapper().map(entity,
		es.pode.adminusuarios.negocio.servicios.GrupoVO.class, DEF_MAPPING_GRUPO_GRUPOVO);
    }

    /**
         * Copy a VO to a new entity.
         * @param vo the source bean (VO)
         * @return A new entity created with the values extracted from the vo.
         * @see es.pode.adminusuarios.negocio.dominio.GrupoDao#fromGrupoVO(es.pode.adminusuarios.negocio.servicios.GrupoVO)
         */
    public es.pode.adminusuarios.negocio.dominio.Grupo fromGrupoVO(
	    final es.pode.adminusuarios.negocio.servicios.GrupoVO vo) {
	// default mapping between VO and entity
	// @todo verify or customize behaviour of this mapping
	Grupo resultado = super.fromGrupoVO(vo);
	Collection col = resultado.getRols();
	if (!(col instanceof Set)) {
	    Set set = new HashSet();
	    set.addAll(col);
	    resultado.setRols(set);
	}
	return resultado;
	// return super.fromGrupoVO(vo);
    }

    /**
         * Copy a VO to an existing entity.
         * @param vo the source bean (VO)
         * @param entity the destination bean (an existing entity)
         * @see es.pode.adminusuarios.negocio.dominio.GrupoDao#fromGrupoVO(es.pode.adminusuarios.negocio.servicios.GrupoVO
         *      ,es.pode.adminusuarios.negocio.dominio.Grupo)
         */
    public void fromGrupoVO(es.pode.adminusuarios.negocio.servicios.GrupoVO vo,
	    es.pode.adminusuarios.negocio.dominio.Grupo entity) {
	// default mapping between VO and entity
	// @todo verify or customize behaviour of this mapping
	super.fromGrupoVO(vo, entity);
    }

    /**
         * @see es.pode.adminusuarios.negocio.dominio.GrupoDao#update(es.pode.adminusuarios.negocio.dominio.Grupo)
         */
    public void update(es.pode.adminusuarios.negocio.dominio.Grupo grupo) {
	if (grupo == null) {
	    throw new IllegalArgumentException("Grupo.update - 'grupo' can not be null");
	}
	if (!(grupo.getRols() instanceof Set)) {

	    Set set = new HashSet();
	    set.addAll(grupo.getRols());
	    grupo.setRols(set);
	}
	this.getHibernateTemplate().update(grupo);
    }
}