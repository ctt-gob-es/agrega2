/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.dominio;

import java.util.Collection;

import org.hibernate.Query;

import es.pode.publicacion.negocio.servicios.CriteriaCIIdOdeVO;

/**
 * @see es.pode.publicacion.negocio.dominio.ContenidoInapropiado
 */
public class ContenidoInapropiadoDaoImpl
    extends es.pode.publicacion.negocio.dominio.ContenidoInapropiadoDaoBase
{
    /**
     * @see es.pode.publicacion.negocio.dominio.ContenidoInapropiadoDao#toContenidoInapropiadoVO(es.pode.publicacion.negocio.dominio.ContenidoInapropiado)
     */
    public es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO toContenidoInapropiadoVO(final es.pode.publicacion.negocio.dominio.ContenidoInapropiado entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO) 
                  this.getBeanMapper().map(entity, es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO.class, DEF_MAPPING_CONTENIDOINAPROPIADO_CONTENIDOINAPROPIADOVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.publicacion.negocio.dominio.ContenidoInapropiadoDao#fromContenidoInapropiadoVO(es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO)
	 */
    public es.pode.publicacion.negocio.dominio.ContenidoInapropiado fromContenidoInapropiadoVO(final es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromContenidoInapropiadoVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.publicacion.negocio.dominio.ContenidoInapropiadoDao#fromContenidoInapropiadoVO(es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO ,es.pode.publicacion.negocio.dominio.ContenidoInapropiado)	 
	 */
    public void fromContenidoInapropiadoVO(es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO vo, es.pode.publicacion.negocio.dominio.ContenidoInapropiado entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromContenidoInapropiadoVO(vo, entity);
    }
    
    public java.util.Collection obtenerTodosCIXOdeHQL(es.pode.publicacion.negocio.servicios.CriteriaContenidoInapropiadoVO criteriavo)
    {
    	Collection<?> resultadoquery =null;

    	//seleccionamos todos los ci, que cumplan: SELECT * FROM conte_inap c where c.ID_ODE='es_20081207_2_9145250' group by c.ID_ODE, c.ESTADO, c.ESTADO_CI, c.FECHA_INACTIVIDAD ;
    	StringBuffer sb = new StringBuffer("SELECT CI FROM ContenidoInapropiadoImpl as CI "); 
    	// 26092012 Se quita el group by porque falla en oracle
    	// sb.append(" GROUP BY CI.idOde, CI.titulo, CI.fecha, CI.usuario, CI.comentario, CI.estado, CI.estado_ci, CI.fecha_inactividad, CI.idioma_cat ");
    	    	    	
    	Query query = this.getSession().createQuery(sb.toString());
    	
    	resultadoquery= query.list();
    	
    	toContenidoInapropiadoVOCollection(resultadoquery);
    	
        return resultadoquery;
    }
    
    
    
    public java.util.Collection obtenerCIdeUsuarioOdeHQL(es.pode.publicacion.negocio.servicios.CriteriaCIUsuarioOdeVO criteriavo)
    {
        
    	Collection<?> resultadoquery=null;
    	StringBuffer sb = new StringBuffer("SELECT CI FROM ContenidoInapropiadoImpl as CI ");
    	
    	if (criteriavo.getIdOde()!=null && !criteriavo.getIdOde().equals("")) {
    		sb.append(" WHERE CI.idOde = :idOde ");
    	}
    	if (criteriavo.getUsuario()!=null && !criteriavo.getUsuario().equals("")) {
    		sb.append(" AND CI.usuario = :usuario ");
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")){
    		sb.append(" AND CI.estado = :estado ");
    	}
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		sb.append(" AND CI.idioma_cat = :idioma_cat ");
    	}
    	Query query = this.getSession().createQuery(sb.toString());
    	
    	if (criteriavo.getIdOde()!=null && !criteriavo.getIdOde().equals("")) {
    		query.setParameter("idOde", criteriavo.getIdOde());
    	}
    	if (criteriavo.getUsuario()!=null && !criteriavo.getUsuario().equals("")) {
    		query.setParameter("usuario", criteriavo.getUsuario());
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")) {
    		query.setParameter("estado", criteriavo.getEstado());
    	}
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		query.setParameter("idioma_cat", criteriavo.getIdioma_cat());
    	}
    	
    	resultadoquery=query.list();
    	toContenidoInapropiadoVOCollection(resultadoquery);
    	
    	return resultadoquery;
    }
    
    
    public java.util.Collection obtenerDetalleCIdeOdeHQL(es.pode.publicacion.negocio.servicios.CriteriaCIDetalleOdeVO criteriavo)
    {
    	Collection<?> resultadoquery= null;
    	//	Necesitamos saber el detalle de un reporte en concreto, por ejm:
    	//SELECT * FROM conte_inap c where c.ID_ODE='es_20081207_2_9145250' AND c.ESTADO_CI=0 
    	//AND c.FECHA_INACTIVIDAD='2009-11-18 11:00:31' group by c.ID_ODE, c.USUARIO ;
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT CI FROM ContenidoInapropiadoImpl as CI ");
    	
    	if (criteriavo.getIdOde()!= null && !criteriavo.getIdOde().equals("")){
    		sb.append(" WHERE CI.idOde = :idOde ");
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")){
    		sb.append(" AND CI.estado = :estado ");
    	}
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		sb.append(" AND CI.idioma_cat = :idioma_cat ");
    	}
    	//filtro por estado_ci
    	sb.append(" AND CI.estado_ci = :estado_ci ");
    	//filtro por fecha inactividad
    	if (criteriavo.getFecha_inactividad()!=null){
    		sb.append(" AND CI.fecha_inactividad = :fecha_inactividad ");
    	}
    	
    	// 201301114 Se comenta el group by porque en entornos Oracle falla porque el Select no es una expresión group by
    	//			 Además, después de analizarlo no tiene sentido que se haga el group by, ya que nunca va a agrupar nada 
    	//			 porque un usuario nunca puede reportar más de una vez un ODE como contenido inapropiado.
    	
    	//sb.append(" GROUP BY CI.idOde, CI.usuario ");
    	
    	Query query = this.getSession().createQuery(sb.toString());
    	
    	// parametros
    	if (criteriavo.getIdOde()!= null && !criteriavo.getIdOde().equals("")){
    		query.setParameter("idOde", criteriavo.getIdOde());
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")){
    		query.setParameter("estado", criteriavo.getEstado());
    	}    	
    	query.setParameter("estado_ci", criteriavo.isEstado_ci());
    	if (criteriavo.getFecha_inactividad()!=null){
    		query.setParameter("fecha_inactividad", criteriavo.getFecha_inactividad());
    	}
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		query.setParameter("idioma_cat", criteriavo.getIdioma_cat());
    	}
    	resultadoquery=query.list();
    	toContenidoInapropiadoVOCollection(resultadoquery);
    	
        return resultadoquery;
    }
    
    public java.util.Collection obtenerCIIdOde(es.pode.publicacion.negocio.servicios.CriteriaCIIdOdeVO criteriavo)
    {	
    	Collection<?> resultadoquery=null;
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT CI FROM ContenidoInapropiadoImpl as CI ");
    	
    	if (criteriavo.getIdOde()!= null && !criteriavo.getIdOde().equals("")){
    		sb.append(" WHERE CI.idOde = :idOde ");
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")){
    		sb.append(" AND CI.estado = :estado ");
    	}
    	//filtro por estado_ci
    	sb.append(" AND CI.estado_ci = :estado_ci ");
    	
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		sb.append(" AND CI.idioma_cat = :idioma_cat ");
    	}
    	//filtro por fecha inactividad
    	if (criteriavo.getFecha_inactividad()!=null){
    		sb.append(" AND CI.fecha_inactividad = :fecha_inactividad");
    	}
    	
    	// 201301114 Se comenta el group by porque en entornos Oracle falla porque el Select no es una expresión group by
    	//			 Además, después de analizarlo no tiene sentido que se haga el group by, ya que nunca va a agrupar nada 
    	//			 porque un usuario nunca puede reportar más de una vez un ODE como contenido inapropiado.

    	sb.append(" GROUP BY CI.idOde, CI.usuario ");
    	
    	Query query = this.getSession().createQuery(sb.toString());
    	
    	// parametros
    	if (criteriavo.getIdOde()!= null && !criteriavo.getIdOde().equals("")){
    		query.setParameter("idOde", criteriavo.getIdOde());
    	}
    	if (criteriavo.getEstado()!=null && !criteriavo.getEstado().equals("")){
    		query.setParameter("estado", criteriavo.getEstado());
    	}    	
    	query.setParameter("estado_ci", criteriavo.isEstado_ci());
    	if (criteriavo.getFecha_inactividad()!=null){
    		query.setParameter("fecha_inactividad", criteriavo.getFecha_inactividad());
    	}
    	
    	if (criteriavo.getIdioma_cat()!=null && !criteriavo.getIdioma_cat().equals("")) {
    		query.setParameter("idioma_cat", criteriavo.getIdioma_cat());
    	}
    	
    	resultadoquery=query.list();
    	//toContenidoInapropiadoVOCollection(resultadoquery);
    	
        return resultadoquery;
    }
      

	@Override
	protected void handleEliminarCIPoridOde(CriteriaCIIdOdeVO criteriavo) throws Exception {
		if (criteriavo!=null && !criteriavo.getIdOde().equals("")){
			//Collection datosIdOde=this.obtenerDetalleCIdeOdeHQL(criteriavo);
			Collection<?> datosIdOde=this.obtenerCIIdOde(criteriavo);
			if (datosIdOde!=null) {
				this.remove(datosIdOde);
			}
		}
		
	}
	
}