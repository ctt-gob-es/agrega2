// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.planificador.negocio.dominio;

import java.util.List;

/**
 * @see es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada
 */
public class RegistroTareaCargaEjecutadaDaoImpl
    extends es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDaoBase
{
    /**
     * @see es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDao#toRegistroTareaCargaEjecutadaVO(es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada)
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO toRegistroTareaCargaEjecutadaVO(final es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO) 
                  this.getBeanMapper().map(entity, es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO.class, DEF_MAPPING_REGISTROTAREACARGAEJECUTADA_REGISTROTAREACARGAEJECUTADAVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDao#fromRegistroTareaCargaEjecutadaVO(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO)
	 */
    public es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada fromRegistroTareaCargaEjecutadaVO(final es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromRegistroTareaCargaEjecutadaVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutadaDao#fromRegistroTareaCargaEjecutadaVO(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO ,es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada)	 
	 */
    public void fromRegistroTareaCargaEjecutadaVO(es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO vo, es.pode.planificador.negocio.dominio.RegistroTareaCargaEjecutada entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromRegistroTareaCargaEjecutadaVO(vo, entity);
    }
	
	public Object buscarRegistrosConRuta(int transform, String queryString, Long id)
    {
        try
        {
            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
            queryObject.setParameter("id", id);
            List results = queryObject.list();
            Object result = null;
            if (results != null)
            {
                if (results.size() > 1)
                {
                    throw new org.springframework.dao.InvalidDataAccessResourceUsageException(
                        "More than one instance of 'java.lang.Long"
                            + "' was found when executing query --> '" + queryString + "'");
                }
                else if (results.size() == 1)
                {
                    result = results.iterator().next();
                }
            }
            return result;
        }
        catch (org.hibernate.HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
        
    }
}