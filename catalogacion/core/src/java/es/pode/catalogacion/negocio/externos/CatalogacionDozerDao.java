package es.pode.catalogacion.negocio.externos;

import net.sf.dozer.util.mapping.MapperIF;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import es.pode.catalogacion.negocio.servicios.CBTaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.parseadorXML.LomESDao;
import es.pode.soporte.auditoria.registrar.Registrar;

public class CatalogacionDozerDao {
	public CatalogacionDozerDao() 
	{
		super();
	
	}

	private static Logger log = Logger.getLogger(CatalogacionDozerDao.class);

	private MapperIF beanMapperAux = null;
	
	private LomESDao lomes = null;

	public MapperIF getBeanMapperAux() 
	{
		return beanMapperAux;
	}

	public void setBeanMapperAux(MapperIF beanMapperAux) 
	{
		this.beanMapperAux = beanMapperAux;
	}
	
	
	public TaxonVO[] transformarCBTaxonVO2TaxonVO(CBTaxonVO[] cBTaxonList){
		
		TaxonVO[] taxonList=new TaxonVO[cBTaxonList.length];
		
		for(int i=0;i<cBTaxonList.length;i++){
			
			TaxonVO taxonVO = new TaxonVO();
			taxonVO.setId(cBTaxonList[i].getId());
			taxonVO.setValorTax(cBTaxonList[i].getValorTax() );
			taxonVO.setEsHoja(cBTaxonList[i].getEsHoja());
			taxonList[i] = taxonVO;

		}
		
		return taxonList;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public LomESDao getLomes() {
		return lomes;
	}

	public void setLomes(LomESDao lomes) {
		this.lomes = lomes;
	}


}

