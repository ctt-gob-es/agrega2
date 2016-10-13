/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.buscar.negocio.buscar.dominio;

import java.util.Collection;
import java.util.Date;

/**
 * @see es.pode.buscar.negocio.buscar.dominio.Estadistica
 */
public class EstadisticaDaoImpl
    extends es.pode.buscar.negocio.buscar.dominio.EstadisticaDaoBase
{
    /**
     * @see es.pode.buscar.negocio.buscar.dominio.EstadisticaDao#toEstadisticaVO(es.pode.buscar.negocio.buscar.dominio.Estadistica)
     */
    public es.pode.buscar.negocio.buscar.servicios.EstadisticaVO toEstadisticaVO(final es.pode.buscar.negocio.buscar.dominio.Estadistica entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.buscar.negocio.buscar.servicios.EstadisticaVO) 
                  this.getBeanMapper().map(entity, es.pode.buscar.negocio.buscar.servicios.EstadisticaVO.class, DEF_MAPPING_ESTADISTICA_ESTADISTICAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.buscar.negocio.buscar.dominio.EstadisticaDao#fromEstadisticaVO(es.pode.buscar.negocio.buscar.servicios.EstadisticaVO)
	 */
    public es.pode.buscar.negocio.buscar.dominio.Estadistica fromEstadisticaVO(final es.pode.buscar.negocio.buscar.servicios.EstadisticaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromEstadisticaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.buscar.negocio.buscar.dominio.EstadisticaDao#fromEstadisticaVO(es.pode.buscar.negocio.buscar.servicios.EstadisticaVO ,es.pode.buscar.negocio.buscar.dominio.Estadistica)	 
	 */
    public void fromEstadisticaVO(es.pode.buscar.negocio.buscar.servicios.EstadisticaVO vo, es.pode.buscar.negocio.buscar.dominio.Estadistica entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromEstadisticaVO(vo, entity);
    }
    
    
    public java.util.Collection obtenerEstadisticasAgrupadasPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
  
    public java.util.Collection obtenerEstadisticasAgrupadasPorFechasYNodo(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta, final java.lang.String nodo)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
			queryObject.setParameter("nodo", nodo);
			queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.Collection obtenerEstadisticasTerminosPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
  
    public java.util.Collection obtenerEstadisticasTerminosPorFechasYNodo(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta, final java.lang.String nodo) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            queryObject.setParameter("nodo", nodo);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.Collection obtenerComunidadesTerminosMasBuscados(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta, final java.lang.String termino)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            queryObject.setParameter("termino", termino);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    public java.util.Collection obtenerEstadisticasActividadPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.Collection obtenerEstadisticasCoberturaCurricularPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.Collection obtenerEstadisticasLicenciasPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
    public java.util.Collection obtenerEstadisticasOdesPorFechas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    public java.util.Collection obtenerNodosConEstadisticas(final int transform, final java.lang.String queryString, final java.util.Date fechaDesde, final java.util.Date fechaHasta) {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("fechaDesde", fechaDesde);
            queryObject.setParameter("fechaHasta", fechaHasta);
            java.util.List results = queryObject.list();
            return results;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
    
}