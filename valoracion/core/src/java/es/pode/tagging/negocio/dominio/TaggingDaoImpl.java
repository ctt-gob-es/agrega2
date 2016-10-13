// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.tagging.negocio.dominio;



import java.util.List;

import org.hibernate.Query;

import es.pode.tagging.negocio.servicios.TagVO;

/**
 * @see es.pode.tagging.negocio.dominio.Tagging
 */
public class TaggingDaoImpl
    extends es.pode.tagging.negocio.dominio.TaggingDaoBase
{
    @Override
	protected TagVO toTagVO(Object[] row) {
		// TODO Auto-generated method stub
    	TagVO tag = new TagVO();
    	tag.setNombre((String)row[0]);
    	tag.setNumOdes((Long)row[1]);
		return tag;
	}


	/**
     * @see es.pode.tagging.negocio.dominio.TaggingDao#toTaggingVO(es.pode.tagging.negocio.dominio.Tagging)
     */
    public es.pode.tagging.negocio.servicios.TaggingVO toTaggingVO(final es.pode.tagging.negocio.dominio.Tagging entity)
    {
        //default mapping between entity and VO
        //@todo verify or customize behaviour of this mapping
        return (es.pode.tagging.negocio.servicios.TaggingVO) 
                  this.getBeanMapper().map(entity, es.pode.tagging.negocio.servicios.TaggingVO.class, DEF_MAPPING_TAGGING_TAGGINGVO);
    }
    

	/**
	 * Copy a VO to a new entity.
	 * @param vo The source bean (VO)
	 * @return A new entity created with the values extracted from the vo.
	 * @see es.pode.tagging.negocio.dominio.TaggingDao#fromTaggingVO(es.pode.tagging.negocio.servicios.TaggingVO)
	 */
    public es.pode.tagging.negocio.dominio.Tagging fromTaggingVO(final es.pode.tagging.negocio.servicios.TaggingVO vo) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        return super.fromTaggingVO(vo);
    }

	/**
	 * Copy a VO to an existing entity.
	 * @param vo The source bean (VO)
	 * @param entity The destination bean (an existing entity)
	 * @see es.pode.tagging.negocio.dominio.TaggingDao#fromTaggingVO(es.pode.tagging.negocio.servicios.TaggingVO ,es.pode.tagging.negocio.dominio.Tagging)	 
	 */
    public void fromTaggingVO(es.pode.tagging.negocio.servicios.TaggingVO vo, es.pode.tagging.negocio.dominio.Tagging entity) {
        //default mapping between VO and entity
        //@todo verify or customize behaviour of this mapping
        super.fromTaggingVO(vo, entity);
    }
    
    
	public TagVO toTagVO(Tagging entity) {
		// TODO Auto-generated method stub
		 return null;
	}

	
	protected List handleObtenerTagsconLetra(String letra) throws Exception {
		// TODO Auto-generated method stub
		//select tagging.tag,count(tagging.tag) from es.pode.tagging.negocio.dominio.Tagging as tagging group by tagging.tag order by col_1_0_ desc
		List resultadoquery=null;
		
    	StringBuffer sb = new StringBuffer("SELECT tagging FROM TaggingImpl as tagging ");
    	
    	if (letra!=null && !letra.equals("")) {
    		sb.append(" WHERE tagging.tag LIKE :letra ");
    	}
    	sb.append(" ORDER BY tagging.tag ");
    	
    	Query query = this.getSession().createQuery(sb.toString());
    	
    	if (letra!=null && !letra.equals("")) {
    		query.setParameter("letra", "%" + letra + "%");
    	}    	
    	resultadoquery=query.list();
    	toTaggingVOCollection(resultadoquery);
    	
    	return resultadoquery;
		
		
	}
    
}