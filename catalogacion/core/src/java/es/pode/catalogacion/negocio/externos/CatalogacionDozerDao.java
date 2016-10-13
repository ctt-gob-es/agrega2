/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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

