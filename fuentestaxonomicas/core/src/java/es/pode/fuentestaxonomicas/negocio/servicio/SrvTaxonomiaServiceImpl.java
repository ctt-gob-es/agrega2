/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.fuentestaxonomicas.negocio.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.fuentestaxonomicas.negocio.soporte.EstructuraVdex;
import es.pode.fuentestaxonomicas.negocio.soporte.Taxon;
import es.pode.soporte.constantes.ConstantesAgrega;

public class SrvTaxonomiaServiceImpl
    extends es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaServiceBase
{ 
	private static Properties properties;
	
	private String getPropertyValue(
			String sKey) 
	throws IOException 
	{
		InputStream fIsProperties = this.getClass().getResourceAsStream("/fuentestaxonomicas.properties");
		if (SrvTaxonomiaServiceImpl.properties == null) {
			properties = new java.util.Properties();
			properties.load(fIsProperties);
		}
		fIsProperties.close();
		if (logger.isDebugEnabled())
			logger.debug("Propiedad recuperada: <" + sKey + "> - <"+ properties.getProperty(sKey)+">");
//		 devolvemos la propiedad
		return properties.getProperty(sKey);
	}


	/**
	 * Obtiene los hijos del nodo que tenga el identificador id de la Taxonomia de nombre 
	 * nomTaxonomia con el idioma que le pasamos por parámetro
	 * @param id Identificador del nodo
	 * @param nomTaxonomia Nombre de la Taxonomia
	 * @param idioma Idioma de la Taxonomia
	 * @return TaxonVO[] Array de TaxonVO que contiene los hijos del nodo
	 * @throws Exception
	 */
    protected TaxonVO[] handleObtenerNodos(
    		String id, 
    		String nomTaxonomia, 
    		String idioma)
        throws Exception
 {

		Collection<TaxonVO> taxones = new ArrayList<TaxonVO>();
		String ambitoProp = null;

		try {
			// String nodo=ConstantesAgrega.AMBITO_NODO;
			String ambito = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NODO);//server.id

			ambitoProp = ConstantesAgrega.AMBITO_GENERAL;
			if (ambito == null) {
				ambito = ambitoProp;
			}

			HashMap<?, ?> hasmap = new HashMap<Object, Object>();
			EstructuraVdex estructura = this.getEstructurasDao()
					.obtenerTaxonomia(nomTaxonomia, idioma);

			hasmap = estructura.getHashMap();
			String vocabName = estructura.getVocabName();

			if (hasmap != null) {
				Taxon tax = (Taxon) hasmap.get(id.trim());

				if (tax != null && tax.getHijos() != null && tax.getHijos().size() > 0) {
					ArrayList<?> arr = tax.getHijos();

					for (Iterator<?> iter = arr.iterator(); iter.hasNext();) {
						boolean bandera = false;
						String idHijo = (String) iter.next();
						Taxon taxAux = (Taxon) hasmap.get(idHijo);
						String amb = taxAux.getAmbito();

						if (!(amb.equals(""))) {

							StringTokenizer token = new StringTokenizer(amb, ",");
							while (token.hasMoreElements() && (!bandera)) {
								String ambLeido = token.nextElement().toString().trim();
								if (ambito.equalsIgnoreCase(ambLeido)) {
									bandera = true;
									amb = ambLeido;
								}
							}
							if (ambito.equals(ambitoProp)) {
								TaxonVO taxVO = new TaxonVO();
								taxVO.setId(taxAux.getId().trim());
								taxVO.setValorTax(taxAux.getValorTax().trim());
								taxVO.setEsHoja(taxAux.getHijos().isEmpty());
								taxVO.setVocabName(vocabName);
								taxones.add(taxVO);
							}
							if (((amb.equals(ambito)) || (amb.equals(ambitoProp))) && (!ambito.equals(ambitoProp))) {

								TaxonVO taxVO = new TaxonVO();
								taxVO.setId(taxAux.getId().trim());
								taxVO.setValorTax(taxAux.getValorTax().trim());
								//taxVO.setAmbito(taxAux.getAmbito().trim());

						// ********************código nuevo***************************

								taxVO.setEsHoja(taxAux.getHijos().isEmpty());
								taxVO.setVocabName(vocabName);
						// ********************términa código nuevo*******************

								taxones.add(taxVO);
							}
						} else {
							TaxonVO taxVO = new TaxonVO();
							taxVO.setId(taxAux.getId().trim());
							taxVO.setValorTax(taxAux.getValorTax().trim());
							// taxVO.setAmbito(taxAux.getAmbito().trim());

					// ********************código nuevo***************************

							taxVO.setEsHoja(taxAux.getHijos().isEmpty());
							taxVO.setVocabName(vocabName);
					// ********************términa código nuevo*******************

							taxones.add(taxVO);
						}
					}

				}
			}

		} catch (Exception e) {
			logger.error("error en SrvTaxonomiaService:obtenerNodos ", e);
			throw e;
		}
		return taxones.toArray(new TaxonVO[0]);
	}

   /**
    * Obtiene los nodos padre de la taxonomia
    * @param nomTaxonomia Nombre de la Taxonomia
    * @param idioma Idioma de la Taxonomia
    * @return TaxonVO[] Un array de TaxonVO que contiene los hijos del nodo vdex
    * @throws Exception
    */
    protected TaxonVO[] handleObtenerTaxonomia(
    		String nomTaxonomia, 
    		String idioma)
        throws Exception
    {
    	return handleObtenerNodos("vdex", nomTaxonomia, idioma);
    }


    /**
     * Obtiene el taxonPath
     * @param id Identificador del Taxon en la hashMap
     * @param nomTaxonomia Nombre de la Taxonomia
     * @param idioma Idioma de la Taxonomia
     * @return TaxonVO[] Array de TaxonVO que contiene todos los taxones del taxonPath
     * @throws Exception
     */

	protected TaxonVO[] handleObtenerTaxonPath(
			String id, 
			String nomTaxonomia, 
			String idioma) 
	throws java.lang.Exception 
	{

		Collection<TaxonVO> taxones = new ArrayList<TaxonVO>();
    	HashMap<?, ?> hasmap = new HashMap<Object, Object>();
		try{
	    	EstructuraVdex estructura= this.getEstructurasDao().obtenerTaxonomia(nomTaxonomia, idioma);
	    	//Controlamos que si estructura viene null, devuelva un TaxonVO[0]
	    	if (estructura!=null) {
		    	hasmap = estructura.getHashMap();
				String vocabName=estructura.getVocabName();
				if (hasmap!=null){
					boolean flag=true;
					while(flag){
						if(id ==null){
							flag=false;
						}else{
							Taxon tax = (Taxon)hasmap.get(id);
							if(tax!=null){
								TaxonVO taxonAux=new TaxonVO();
								taxonAux.setId(tax.getId().trim());
								taxonAux.setVocabName(vocabName);
								if (!tax.getAmbito().equals("")) { //lleva Mec o comunidades ()
									String taxo=tax.getValorTax().trim();
									int num=taxo.lastIndexOf("(");
									if(num>0){
										taxo=taxo.substring(0, num).trim();
									}
									taxonAux.setValorTax(taxo);
								}else {
									taxonAux.setValorTax(tax.getValorTax()); //toda la cadena sin quitar parentesis 
								}
								taxonAux.setEsHoja(tax.getHijos().isEmpty());
								taxones.add(taxonAux);
								id=tax.getPadre();
								
								if(id!=null)
									id=id.trim();
							}else
								id=null;
						}
					}
				}
	    	}//fin comprobacion estructura!=null
		}catch(Exception e){
			logger.error("error en SrvTaxonomiaService:ObtenerTaxonPath", e);
			throw e;
		}
		return taxones.toArray(new TaxonVO[taxones.size()]);
		
	}
	
	
	protected TaxonPathVO[] handleObtenerVariosTaxonPaths(
			String[] ides, 
			String nomTaxonomia, 
			String idioma) 
	throws Exception 
	{
		logger.debug("\nnomtaxonomia recibido: " + nomTaxonomia);
		logger.debug("\nidioma recibido: " + idioma);
		
		List<TaxonPathVO> array=new ArrayList<TaxonPathVO>();
		TaxonPathVO[] path=null;
		try{
			for(int i=0;i<ides.length;i++){
				TaxonVO[] taxonesVuelta=this.obtenerTaxonPath(ides[i], nomTaxonomia, idioma);
				
				if(taxonesVuelta!=null && taxonesVuelta.length>0){	
					TaxonPathVO taxonPath=new TaxonPathVO();
					taxonPath.setDatos(taxonesVuelta);
					array.add(taxonPath);
				}
		}
		path=array.toArray(new TaxonPathVO[array.size()]);
		}catch(Exception e){
			logger.error("error en SrvTaxonomiaService:ObtenerVariosTaxonPaths", e);
			throw e;
		}
		return  path;
	}



	protected TaxonConRutaVO[] handleObtenerTaxonomiaCompletaPreorden(
			String nomTaxonomia, 
			String idioma)
	throws Exception
	{
		TaxonConRutaVO[] taxPreorden=null;

		try{
			EstructuraVdex ev= this.getEstructurasDao().obtenerTaxonomia(nomTaxonomia, idioma);
	
			logger.debug("ASC- RECOGEMOS ESTRUCTURAVDEX DE LA HASHMAP EL FICHERO ES " + nomTaxonomia+"_"+idioma+".xml" );
			
			if (ev!=null) {
				HashMap<?, ?> hashTaxonomia = ev.getHashMap();
				ArrayList<TaxonConRutaVO> taxonomia =new ArrayList<TaxonConRutaVO>();
				if (hashTaxonomia != null){
					Taxon taxonRaiz = (Taxon) hashTaxonomia.get("vdex");
					taxonomiaPreordenRec(nomTaxonomia,idioma,taxonRaiz,taxonomia);
				}
				taxPreorden= taxonomia.toArray(new TaxonConRutaVO[taxonomia.size()]);
			}
			logger.debug("ASC- RESULTADO A DEVOLVER DE TAXPREORDEN " + Arrays.toString(taxPreorden));
		}catch (Exception e) {
			logger.error("error en SrvTaxonomiaService:ObtenerTaxonomiaCompletaPreorden - ", e);
			throw e;
		}
		return taxPreorden;
	}
	
	private void taxonomiaPreordenRec(
			String nomTaxonomia, 
			String idioma,
			Taxon taxon,
			ArrayList<TaxonConRutaVO> taxonomia) 
	throws Exception
	{

		EstructuraVdex ev= this.getEstructurasDao().obtenerTaxonomia(nomTaxonomia, idioma);
		
		HashMap<?, ?> hashTaxonomia =null; 
		if (ev!=null) { 
			hashTaxonomia = ev.getHashMap();
		}
		if (hashTaxonomia != null){
			ArrayList<?> hijos = taxon.getHijos();
			Iterator<?> i = hijos.iterator();
			TaxonConRutaVO taxonRuta = new TaxonConRutaVO();
			taxonRuta.setId(taxon.getId());
			taxonRuta.setValorTax(taxon.getValorTax());
			taxonRuta.setTaxonPath(this.handleObtenerTaxonPath(taxon.getId(), nomTaxonomia, idioma));
			taxonomia.add(taxonRuta);
			if (hijos.size()>0){
				while (i.hasNext()){
					String hijo = (String) i.next();
					Taxon taxonHijo = (Taxon) hashTaxonomia.get(hijo);
			
					taxonomiaPreordenRec(nomTaxonomia,idioma,taxonHijo,taxonomia);
				}
			}
		}
	}
	
	protected String handleObtenerVocabName(
			String nomTaxonomia, 
			String idioma)
	throws Exception 
	{
		String vocabName=null;
		try{
			EstructuraVdex estructuraTaxonomia = this.getEstructurasDao().obtenerTaxonomia(nomTaxonomia, idioma);
			if(estructuraTaxonomia!=null){
				vocabName=estructuraTaxonomia.getVocabName();
			}
			
			if(vocabName==null)
				vocabName="";
		}catch (Exception e) {
			logger.error("error en SrvTaxonomiaService:ObtenerVocabName - ", e);
			throw e;
		}
		return vocabName;
	}
	
	protected String[] handleObtenerVocabNames(
			String[] nomTaxonomias,
			String idioma)
	throws Exception {
		String[] vueltaVocabs=null;
		try{
			vueltaVocabs=new String[nomTaxonomias.length];
			for(int i=0;i<nomTaxonomias.length;i++){
				String vocabName=this.obtenerVocabName(nomTaxonomias[i], idioma);
				vueltaVocabs[i]=vocabName;
			}
		}catch (Exception e) {
			logger.error("Error en SrvTaxonomiaService:ObtenerVocabNames - " , e);
			throw e;
		}
		return vueltaVocabs;
	}

	/**
	 * 
	 */
	protected String[] handleObtenerIdsConVocabIdentifier(
			String[] ids)
	throws Exception 
	{
		try{
			return this.getEstructurasDao().traducirVocabNames(ids);
		}catch (Exception e) {
			logger.error("Error en SrvTaxonomiaService:ObtenerIdsConVocabIdentifier - " , e);
			throw e;
		}
	}

	

	/**
	 * Este método permite obtener un listado de EstructurasVdexVO, del idioma solicitado.
	 * 
	 * @param idioma idioma solicitado
	 * @return array de objetos del tipo EstructuraVdexVO
	 * @throws Exception 
	 */
	
	protected EstructuraVdexVO[] handleObtenerTaxonomias(
			String idioma)
	throws Exception 
	{
		try{
			if(idioma==null || idioma.equals("") || idioma.toLowerCase().equals("todo"))
			{
				return this.getEstructurasDao().obtenerTaxonomias();
			}
			return this.getEstructurasDao().obtenerTaxonomias(idioma);
		}catch (Exception e) {
			logger.error("Error en SrvTaxonomiaService:ObtenerTaxonomias - " , e);
			throw e;
		}
	}

	
	protected EstructuraVdexVO handleObtenerArbolVigente()
	throws Exception 
	{
		EstructuraVdexVO resultado=null;
		String idioma= "es";
		
		try{
			EstructuraVdex estructura=this.getEstructurasDao().obtenerTaxonomia(getPropertyValue("arbolCurricular"), idioma);
			if(estructura!=null)
			{
				resultado= new EstructuraVdexVO();
				resultado.setVocabName(estructura.getVocabName());
				resultado.setVocabIdentifier(estructura.getVocabIdentifier());
			}//if
		}catch (Exception e) {
			logger.error("error en SrvTaxonomiaService:ObtenerArbolVigente - " , e);
			throw e;
		}
		return resultado;
	}


	protected Boolean handleChequearIdiomaTaxonomia(
			String identificador,
			String idioma) 
	throws Exception 
	{
		return (this.getEstructurasDao().obtenerTaxonomia(identificador, idioma)!=null);
	}
	
	
	
}
