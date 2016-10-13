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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.pode.fuentestaxonomicas.negocio.soporte.EstructuraVdex;
import es.pode.fuentestaxonomicas.negocio.soporte.Relaciones;
/**
 * @see es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServices
 */

public class SrvTesaurosServicesImpl
    extends es.pode.fuentestaxonomicas.negocio.servicio.SrvTesaurosServicesBase
{
	private static Properties properties; 
	
	private String getPropertyValue(
			String sKey) 
	throws IOException 
	{
		InputStream fIsProperties = this.getClass().getResourceAsStream("/fuentestaxonomicas.properties");
		if (SrvTesaurosServicesImpl.properties == null) {
			properties = new java.util.Properties();
			properties.load(fIsProperties);
		}
		fIsProperties.close();
		if (logger.isDebugEnabled())logger.debug("getPropertyValue: Propiedad recuperada: <" + sKey + "> : <"+ properties.getProperty(sKey)+">");
//		 devolvemos la propiedad
		return properties.getProperty(sKey);
	}
	

    /**
     * Obtiene los terminos que están relacionados pasándole como parámetro el identificador de un termino
     * @param id Identificador del término
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return relacionadosId Array de TaxonVO que contienen el identificador y el valor del taxon
     * @throws Exception
     */
    protected TaxonVO[] handleObtenerTerminosRelacionadosPorId(
    		String id, 
    		String nomTesauro, 
    		String idioma)
        throws Exception
    {
    	TaxonVO[] relacionadosId = null;
    	
    	try{
	      	//para el id indicado se obtienen las relaciones y sus padres
	    	TaxonVO[] relacionesAsoc = obtenerTerminosRelacionAsociativa(id, nomTesauro, idioma);
	    	TaxonVO[] relacionesJerar = obtenerTerminosRelacionJerarquica(id, nomTesauro, idioma);
	    	TaxonVO[] padres = obtenerPadres(id, idioma, nomTesauro);
	    	int tamañoJerar = relacionesJerar.length;
	    	int tamañoAsoc = relacionesAsoc.length;
	    	int tamañoPadres = padres.length;
	    	int tamaño = tamañoJerar + tamañoAsoc + tamañoPadres;
	    	int tamañoPadresJerar= tamañoJerar + tamañoPadres;
	    	
	    	//lo juntamos todo en un array de TaxonVO
	    	relacionadosId = new TaxonVO[tamaño];
	    	for( int j=0;j<tamañoPadres;j++){
	       	   TaxonVO taxon = new TaxonVO(); 
	       	   taxon.setId(padres[j].getId());
	       	   taxon.setValorTax(padres[j].getValorTax());
	       	   taxon.setEsHoja(new Boolean(false));
	       	   taxon.setVocabName(padres[j].getVocabName());
	       	   taxon.setTipoRelacion("TG");
	       	   relacionadosId[j] = taxon;
	          }
	    	for( int j=tamañoPadres;j<(tamañoJerar+tamañoPadres);j++){
	      	   TaxonVO taxon = new TaxonVO(); 
	      	   int m = j-tamañoPadres;
	      	   taxon.setId(relacionesJerar[m].getId());
	      	   taxon.setValorTax(relacionesJerar[m].getValorTax());
	      	   taxon.setEsHoja(new Boolean(false));
	      	   taxon.setVocabName(relacionesJerar[m].getVocabName());
	      	   taxon.setTipoRelacion("TE");
	      	   relacionadosId[j] = taxon;
	         }
	         
	     	for( int j=tamañoPadresJerar;j<tamaño;j++){
	      	   TaxonVO taxon = new TaxonVO(); 
	      	   int k = j-tamañoPadresJerar;
	      	   taxon.setId(relacionesAsoc[k].getId());
	      	   taxon.setValorTax(relacionesAsoc[k].getValorTax());
	      	   taxon.setEsHoja(false);
	      	   taxon.setVocabName(relacionesAsoc[k].getVocabName());
	      	   taxon.setTipoRelacion("TR");
	      	   relacionadosId[j] = taxon;
	         }
    	}catch (Exception e) {
			logger.error("Error en SrvTesaurosServices:ObtenerTerminosRelacionadosPorId - " ,e );
			throw e;
		}
    	return relacionadosId;
    }

    /**
     * Obtiene la jerarquia de un termino
     * @param id Identificador del termino
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return JerarquiaVO[] Array de JerarquiaVO, donde JerarquiaVO es un array de TaxonVO
     * @throws Exception
     */
    protected  JerarquiaVO[] handleObtenerJerarquia(
    		String id, 
    		String nomTesauro, 
    		String idioma)
        throws Exception
    {	
    	JerarquiaVO[] rutaTextoAux=null;
    	try{
	    	//obtenemos todas las rutas o jerarquias que le corresponde al identificador indicado
	    	List<List<String>> lRutaI = obtenerRutas(id,nomTesauro, idioma,null,null);
	    	List<TaxonVO[]> lRutaTexto = obtenerTextoRutas(idioma,nomTesauro,lRutaI);
	    	ArrayList<JerarquiaVO> listaTax=new ArrayList<JerarquiaVO>();
	    	for(int i=0;i<lRutaTexto.size();i++){
	    		JerarquiaVO jerarquia=new JerarquiaVO();
	    		TaxonVO[] taxones = lRutaTexto.get(i);
	    		jerarquia.setJerarquia(taxones);
	    		listaTax.add(jerarquia);
	    	}
	    	rutaTextoAux =listaTax.toArray(new JerarquiaVO[listaTax.size()]);
    	}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerJerarquia - " ,e );
			throw e;
		}
       return rutaTextoAux;
    }

    /**
     * Obtiene el primer nivel del Tesauro, es decir aquellos terminos que no tengan padre
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return primerNivelOrd Array de TaxonVO que contenga todos los taxones que no tengan identificador del padre
     * @throws Exception
     */
    protected TaxonVO[] handleObtenerPrimerNivelTesauro(
    		String nomTesauro, 
    		String idioma)
    throws java.lang.Exception
    {
    	TaxonVO[] primerNivelOrd =null;
    	try{
	    	List<String> lIdPrimerNivel = new ArrayList<String>();
	    	EstructuraVdex cEstIdes = this.getEstructurasDao().obtenerTerminoTesauro(nomTesauro, idioma);
	    	 
	    	if(cEstIdes!=null){
		    	Collection<?> cIdes=cEstIdes.getHashMap().values();
		        Iterator<?> i = cIdes.iterator();
		       
		        //obtenemos los ides de los terminos que no tenga padre (termino general)
		    	while(i.hasNext()){
		    		String j = i.next().toString();
		    		EstructuraVdex estructura= this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma); 
		    		HashMap<?, ?> hash = estructura.getHashMap() ;
//		    		String vocabName=estructura.getVocabName();
		        	Relaciones relacion =(Relaciones)(hash.get(j));
		        	int idPadre = relacion.getIdPadres().size();
		
		        	if ((idPadre ==0)){
		        		lIdPrimerNivel.add(j);
		        	}
		        }
	    	
		    	//obtenemos el texto para cada uno de los ides y ordenamos el array de TaxonVO
		    	TaxonVO[] primerNivel = obtenerTexto(lIdPrimerNivel, idioma, nomTesauro);
		    	primerNivelOrd = ordenarAlfabeticamente(primerNivel);
	    	}
    	}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerPrimerNivelTesauro - " ,e );
			throw e;
		}
    	return primerNivelOrd;
    }

    /**
     * Obtiene los terminos que están relacionados pasándole como parámetro el texto de un termino
     * @param textoTesauro Texto del término
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return relacionadosTexto Array de TaxonVO que contienen el identificador y el valor del taxon
     * @throws Exception
     */
    protected TaxonVO[] handleObtenerTerminosRelacionadosPorTexto(
    		String textoTesauro, 
    		String nomTesauro, 
    		String idioma)
    throws java.lang.Exception
    {
    	TaxonVO[] relacionadosTexto=null;
    	try{
	    	//obtenemos los identificadores del texto pasado por parámetro
	    	List<String> lIds = obtenerIdentificador(textoTesauro, idioma, nomTesauro);
	    	if(lIds.size() >0){//el texto coincide con algun término del tesauro
	    		List<String> relacionesJerar=new ArrayList<String>();
	    		List<String> relacionesAsoc =new ArrayList<String>();
	    		List<String> padres=new ArrayList<String>();
	    	
		    	//para cada id que se corresponda con el texto se obtienen las relaciones y sus padres
		    	for (int i=0;i<lIds.size();i++){
		    		EstructuraVdex estructura= this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma);
		    			//(EstructuraVdex)hashMapRelaciones.get(nomTesauro);
		    		
		    		HashMap<?, ?> hash=estructura.getHashMap();
		    		List<String> padresAux = ((Relaciones)hash.get(lIds.get(i).toString())).getIdPadres();
		    		for(int j=0; j<padresAux.size();j++){
			        	if(!padres.contains(padresAux.get(j)))
			        		padres.add(padresAux.get(j));
			        }	
		    		List<String> relacionesJerarAux = obtenerRelacionJerarquica(lIds.get(i).toString(), nomTesauro , idioma); 
			        for(int j=0; j<relacionesJerarAux.size();j++){
			        	if(!relacionesJerar.contains(relacionesJerarAux.get(j)))
			        		relacionesJerar.add(relacionesJerarAux.get(j));
			        }
			        List<String> relacionesAsocAux = obtenerRelacionAsociativa(lIds.get(i).toString(), nomTesauro , idioma);
			        for(int j=0; j<relacionesAsocAux.size();j++){
			        	if(!relacionesAsoc.contains(relacionesAsocAux.get(j)))
			        		relacionesAsoc.add(relacionesAsocAux.get(j));
			        }
		    	}
		    	if(relacionesJerar.size()>0 || relacionesAsoc.size()>0){//termino tiene relaciones jerarquicas o asociativas
		    	
			    	//obtenemos el texto y ordenamos alfabeticamente las relaciones y los padres obtenidos
			    	TaxonVO[] rAsociativas = obtenerTexto(relacionesAsoc, idioma, nomTesauro);
			    	TaxonVO[] rAsociaOrdenadas = ordenarAlfabeticamente(rAsociativas);
			    	TaxonVO[] rJerar = obtenerTexto(relacionesJerar, idioma, nomTesauro);
			    	TaxonVO[] rJerarOrdenadas = ordenarAlfabeticamente(rJerar);
			    	TaxonVO[] tPadres = obtenerTexto(padres, idioma, nomTesauro);
			    	TaxonVO[] tPadresOrdenados = ordenarAlfabeticamente(tPadres);
			    	int tamañoJerar = rJerarOrdenadas.length;
			    	int tamañoAsoc = rAsociaOrdenadas.length;
			    	int tamañoPadres = tPadresOrdenados.length;
			    	int tamaño = tamañoJerar + tamañoAsoc + tamañoPadres;
			    	int tamañoPadresJerar= tamañoJerar + tamañoPadres;
		    	
			    	//lo juntamos todo en un array de TaxonVO
			    	relacionadosTexto = new TaxonVO[tamaño];
			    	
			    	for( int j=0;j<tamañoPadres;j++){
			       	   TaxonVO taxon = new TaxonVO(); 
			       	   taxon.setId(tPadresOrdenados[j].getId());
			       	   taxon.setValorTax(tPadresOrdenados[j].getValorTax());
			       	   taxon.setEsHoja(new Boolean(false));
			       	   taxon.setVocabName(tPadresOrdenados[j].getVocabName());
			       	   taxon.setTipoRelacion("TG");
			       	   relacionadosTexto[j] = taxon;
			          }
			    	for( int j=tamañoPadres;j<(tamañoPadresJerar);j++){
			      	   TaxonVO taxon = new TaxonVO(); 
			      	   int m = j-tamañoPadres;
			      	   taxon.setId(rJerarOrdenadas[m].getId());
			      	   taxon.setValorTax(rJerarOrdenadas[m].getValorTax());
			      	   taxon.setEsHoja(new Boolean(false));
			      	   taxon.setVocabName(rJerarOrdenadas[m].getVocabName());
			      	   taxon.setTipoRelacion("TE");
			      	   relacionadosTexto[j] = taxon;
			         }
			         
			     	for( int j=tamañoPadresJerar;j<tamaño;j++){
			      	   TaxonVO taxon = new TaxonVO(); 
			      	   int k = j-tamañoPadresJerar;
			      	   taxon.setId(rAsociaOrdenadas[k].getId());
			      	   taxon.setValorTax(rAsociaOrdenadas[k].getValorTax());
			      	   taxon.setEsHoja(false);
			      	   taxon.setVocabName(rAsociaOrdenadas[k].getVocabName());
			      	   taxon.setTipoRelacion("TR");
			      	   relacionadosTexto[j] = taxon;
			     	}
		     	}
		    	else{//coincide pero no tiene relaciones ni jerarquicas ni asociativas (es hoja)
		    		//Buscamos los terminos que contengan el texto
		    		
		    		relacionadosTexto=this.obtenerTerminosTexto(textoTesauro, nomTesauro, idioma);
		    		if(relacionadosTexto.length == 1){//solo un termino contiene el texto buscado por tanto mostramos las relaciones
		    								 //de dicho termino
	    				TaxonVO taxon= relacionadosTexto[0];
	    				
	    				relacionadosTexto=this.handleObtenerTerminosRelacionadosPorId(taxon.getId(), nomTesauro, idioma);
		    			
		    		}
		    	}
	    	}
	    	else{//el texto no coincide con ningún término
	//    		Buscamos los terminos que contengan el texto
	
	    		relacionadosTexto=this.obtenerTerminosTexto(textoTesauro, nomTesauro, idioma);
	    		if(relacionadosTexto.length == 1){//solo un termino contiene el texto buscado por tanto mostramos las relaciones
	    								 //de dicho termino
					TaxonVO taxon = relacionadosTexto[0];
				
					relacionadosTexto=this.handleObtenerTerminosRelacionadosPorId(taxon.getId(), nomTesauro, idioma);
	    			
	    		}
	    	}
    	}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerTerminosRelacionadosPorTexto - " ,e );
			throw e;
    	}
    	return relacionadosTexto;
    }
    


    /**
     * Obtiene los terminos relacionados asociativamente con el identificador que le pasamos como parámetro
     * @param id Identificador del termino
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return rAsociaOrdenadas Array de TaxonVO que contiene los términos relacionados asociativamente ordenadas alfabeticamente
     * @throws Exception
     */
    protected TaxonVO[] handleObtenerTerminosRelacionAsociativa(
    		String id, 
    		String nomTesauro, 
    		String idioma)
     throws java.lang.Exception
    {
    	TaxonVO[] rAsociaOrdenadas=null;
    	try{
	    	//obtenemos la relaciones asociativas del id indicado, obtenemos su texto y lo ordenamos alfabeticamente
	    	List<String> relacionesAsoc = obtenerRelacionAsociativa(id,nomTesauro,idioma);
	    	TaxonVO[] rAsociativas = obtenerTexto(relacionesAsoc, idioma, nomTesauro);
	    	rAsociaOrdenadas = ordenarAlfabeticamente(rAsociativas);
    	}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerTerminosRelacionAsociativa - " ,e );
			throw e;
		}
        return     	rAsociaOrdenadas;
    }

    /**
     * Obtiene los terminos relacionados jerarquicamente con el identificador que le pasamos como parámetro
     * @param id Identificador del termino
     * @param nomTesauro Nombre del Tesauro
     * @param idioma Idioma del Tesauro
     * @return rJerarOrdenadas Array de TaxonVO que contiene los términos relacionados jerarquicamente ordenadas alfabeticamente
     * @throws Exception
     */
    protected TaxonVO[] handleObtenerTerminosRelacionJerarquica(
    		String id, 
    		String nomTesauro, 
    		String idioma)
    throws Exception
    {
    	TaxonVO[] rJerarOrdenadas=null;
    	try{
    	//obtenemos la relaciones jerarquicas del id indicado, obtenemos su texto y lo ordenamos alfabeticamente
    	List<String> relacionesJerar = obtenerRelacionJerarquica(id,nomTesauro, idioma);
    	TaxonVO[] rJerarquicas = obtenerTexto(relacionesJerar, idioma, nomTesauro);
        rJerarOrdenadas = ordenarAlfabeticamente(rJerarquicas);
    	}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerTerminosRelacionJerarquica - " ,e );
			throw e;
		}
    	return rJerarOrdenadas;
    }

    
    
    
	protected String handleObtenerVocabName(
			String nomTesauro, 
			String idioma) 
	throws Exception 
	{
		String vocabName=null;
		try{
			EstructuraVdex estructuraTesauro = this.getEstructurasDao().obtenerTerminoTesauro(nomTesauro, idioma);
			if(estructuraTesauro!=null)
				vocabName=estructuraTesauro.getVocabName().toString();	
			if(vocabName==null)
				vocabName="";
		}catch (Exception e) {
    		logger.error("Error en SrvTesaurosServices:ObtenerVocabName - " ,e );
			throw e;
		}
		return vocabName;
	}

	protected String[] handleObtenerVocabNames(
			String[] nomTesauros, 
			String idioma) 
	throws Exception 
	{
		
		String[] vueltaVocabs=null;
		try{
			vueltaVocabs=new String[nomTesauros.length];
			for(int i=0;i<nomTesauros.length;i++){
				String vocabName=this.obtenerVocabName(nomTesauros[i], idioma);
				vueltaVocabs[i]=vocabName;
			}
		}catch (Exception e) {
			logger.error("Error en SrvTesaurosServices:ObtenerVocabNames - " ,e );
			throw e;
		}
		return vueltaVocabs;
	}

	
	
	protected JerarquiaVO[] handleObtenerTerminos(
			String texto, 
			String nomTesauro, 
			String idioma)
	throws Exception 
	{
		JerarquiaVO[] jerarquias=null;
		try{
			ArrayList<JerarquiaVO> terminosEnc = new ArrayList<JerarquiaVO>();
			//Eliminamos los espacio que tenga el texto tanto al principio como al final
			texto = texto.trim();
			//Pasamos texto a minuscula y sustituimos la vocales con tildes con vocales sin tilde
			String textoBuscar = quitarTildes(texto.toLowerCase());
	
			//comprobamos que esta bien formada
			Pattern mask=Pattern.compile("^([0-9][a-zñ.\\'ç\\-0-9\\s/]*)|[a-zñ.\\'ç\\-/][a-zñ0-9.\\'ç\\-/][a-zñ0-9.\\'ç\\-\\s/]*$");//
			Matcher matcher = mask.matcher(textoBuscar);
			boolean correcto = matcher.matches();
			
			if(!texto.equals("") && correcto){
				//elimininamos espacio en blanco ejem:"historia de   la    filosofia" --> "historia de la filosofia"
				String[] aux2 =textoBuscar.split("  ");
				StringBuilder textoTemp=new StringBuilder();
				for(int i=0;i<aux2.length;i++){
					if(!aux2[i].trim().equals("")){
						textoTemp.append(" " + aux2[i]);
					}
				}
				textoBuscar = textoTemp.toString().trim();
	
		      	EstructuraVdex estruc=  this.getEstructurasDao().obtenerIdTesauro(nomTesauro, idioma);
	
				if(estruc!=null){
					HashMap<?, ?> auxHashId = estruc.getHashMap();//hashMap con id como key y terminos como value
					EstructuraVdex estructura = this.getEstructurasDao().obtenerTerminoTesauro(nomTesauro, idioma);
					if(estructura!=null){
						//terminos del tesauro
						HashMap<?, ?> auxHashTerm = estructura.getHashMap();//hashMap con termino con key y id como value
						Collection<?> cTerminos = auxHashTerm.values();//identificadores
						Iterator<?> iTerminos=cTerminos.iterator();
						while(iTerminos.hasNext()){//para cada identificador 
							String id= (String) iTerminos.next();//identificador
							String term = (String) auxHashId.get(id);//termino correspondiente a id
							//eliminamos los espacios,pasamos a minusculas y eliminamos la tildes del termino
							term = term.trim();
							String termAux = quitarTildes(term.toLowerCase());
									
							String[] aux =termAux.split(textoBuscar);
							//si el vector obtenido en el split es mayor que uno, el texto que estamos buscando esta contenido en el termino
							//si el vector es menor o igual a uno, puede ser que el texto a buscar coincida con el termino o
							// que el texto a buscar se la parte final del termino donde estamos buscando.
							//En todos estos casos se ha encontrado resultado por lo tanto se obtienen las jerarquias correspondientes 
							//al identificador del terminos
							if(aux.length>1 || termAux.equals(textoBuscar) || termAux.endsWith(textoBuscar)) {			
								JerarquiaVO[] jeraq = this.handleObtenerJerarquia(id, nomTesauro, idioma);
								for(int i=0;i<jeraq.length;i++){
									terminosEnc.add(jeraq[i]);
								}					
							}
						}
					}
				}
			}
			jerarquias = terminosEnc.toArray(new JerarquiaVO[terminosEnc.size()]);
		}catch (Exception e) {
			logger.error("Error en SrvTesaurosServices:ObtenerTerminos - " ,e );
			throw e;
		}
		return jerarquias;
	}
    
	
	protected boolean handleChequearExistenciaIdioma(
			String idioma) 
	throws Exception 
	{
		boolean resultado = false;
		try{
			String ficheroProperties = "/fuentestaxonomicas.properties";
			InputStream iss=null;
			iss= this.getClass().getResourceAsStream(ficheroProperties);
			Properties fprop = new Properties();
			fprop.load(iss);
	
			if(this.getEstructurasDao().obtenerTerminoTesauro(fprop.getProperty("tesauroETB"), idioma) !=null )
				resultado= true;
		}catch (Exception e) {
			logger.error("Error en SrvTesaurosServices:ChequearExistenciaIdioma - " ,e );
			throw e;
		}
		return resultado;
	}


	protected TaxonVO[] handleObtenerTextosDeIds(
			String[] ids, 
			String idioma, 
			String nomTesauro)
	throws Exception 
	{
		TaxonVO[] taxones=null;
		try{
			List<String> listaIds= Arrays.asList(ids);
			taxones= obtenerTexto(listaIds, idioma, nomTesauro);
		}catch (Exception e) {
			logger.error("Error en SrvTesaurosServices:ObtenerTextosDeIds - " ,e );
			throw e;
		}
		return taxones;
	}


	protected EstructuraVdexVO[] handleObtenerTesauros(
			String idioma)
	throws Exception 
	{
		try{
			if(idioma==null || idioma.equals("") || idioma.toLowerCase().equals("todo"))
			{
				return this.getEstructurasDao().obtenerTesauros();
			}
			return this.getEstructurasDao().obtenerTesauros(idioma);
		}catch (Exception e) {
			logger.error("error en SrvTesaurosServices:obtenerTesauros - " , e);
			throw e;
		}
	}
	
	
	protected EstructuraVdexVO handleObtenerTesauroVigente()
	throws Exception 
	{
		EstructuraVdexVO resultado=null;
		String idioma= "es";
		try{
			EstructuraVdex estructura=this.getEstructurasDao().obtenerTerminoTesauro(getPropertyValue("tesauroETB"), idioma);
			if(estructura!=null)
			{
				resultado= new EstructuraVdexVO();
				resultado.setVocabName(estructura.getVocabName());
				resultado.setVocabIdentifier(estructura.getVocabIdentifier());
			}//if
		}catch (Exception e) {
			logger.error("error en SrvTesaurosServices:ObtenerVigente - " , e);
			throw e;
		}
		return resultado;
	}

    
    /**
     * Recupera el texto de los identificadores que le pasamos en el idioma indicado
     * @param ides Lista de identificadores
     * @param idioma Idioma del Tesauro
     * @param nomTesauro Nombre del Tesauro
     * @return taxones Array de TaxonVO que contiene los identificadores y el valor del taxon
     */
    private TaxonVO[] obtenerTexto(
    		List<String> ides, 
    		String idioma,
    		String nomTesauro)
    throws Exception
    {
    	//Entra un array de identificadores y nos devuelve el texto de cada uno de ellos en 
    	//en el idioma que le indiquemos en array de TaxonVO
    	TaxonVO[] taxones=new TaxonVO[ides.size()];
    	try{
	    	EstructuraVdex estructura= this.getEstructurasDao().obtenerIdTesauro(nomTesauro, idioma);
	    	HashMap<?, ?> has=estructura.getHashMap();
	    	String vocabName=estructura.getVocabName();
	    	for( int i=0;i<ides.size();i++){
	    		String id=ides.get(i).toString();
	    		String texto=has.get(id).toString();
	    		TaxonVO taxon = new TaxonVO();
	    		taxon.setId(id);
	    		taxon.setValorTax(texto);
	    		taxon.setEsHoja(false);
	    		taxon.setVocabName(vocabName);
	    		taxones[i]=taxon;   		
	    	}
    	
    	}catch (Exception e) {
    		logger.error("error en obtenerTexto - " , e);
    		throw e;
		}
		return taxones;
    }
    /**
     * Recupera el identificador de los textos que le pasamos en el idioma indicado
     * @param texto El texto
     * @param idioma Idioma del Tesauro
     * @param nomTesauro Nombre del Tesauro
     * @return lIdes Lista de identificadores que tengan el texto que le pasamos como parámetro
     */
  private List<String> obtenerIdentificador(
		  String texto,
		  String idioma,
		  String nomTesauro)
  throws Exception
  {
	  //Para el texto que se recibe se devuelve una lista de identificadores que le corresponden
	  List<String> lIdes = new ArrayList<String>();
	  try{
		  //Eliminamos los espacio que tenga el texto tanto al principio como al final
		  texto = texto.trim();
		  //Pasamos texto a minuscula y sustituimos la vocales con tildes con vocales sin tilde
		  texto = quitarTildes(texto.toLowerCase());
	
		  EstructuraVdex estruc = this.getEstructurasDao().obtenerIdTesauro(nomTesauro, idioma) ;
		  if(estruc!=null){
			  HashMap<?, ?> auxHashId = estruc.getHashMap();//hashMap con id con key y terminos como value
			  EstructuraVdex estructura = this.getEstructurasDao().obtenerTerminoTesauro(nomTesauro, idioma);
			  if(estructura!=null){
				  //terminos del tesauro
				  HashMap<?, ?> auxHashTerm = estructura.getHashMap();//hashMap con termino con key y id como value
				  Collection<?> cTerminos = auxHashTerm.values();//identificadores
				  Iterator<?> iTerminos=cTerminos.iterator();
				  while(iTerminos.hasNext()){//para cada identificador 
					  String id= (String) iTerminos.next();//identificador
					  String term = (String) auxHashId.get(id);//termino correspondiente a id
					  //eliminamos los espacios,pasamos a minusculas y eliminamos la tildes del termino
					  term = term.trim();
					  String termAux =  quitarTildes(term.toLowerCase());
						
					  //comparamos el texto con el termino si son iguales añadimos el id
					  if(termAux.equals(texto)) {
						  lIdes.add(id);
					  }
					  //añadimos los terminos cuya traducción al ingles es igual 
					  //ejem: attitude(actitud) --> 118 y attitude(actitudes) --> 1654
					  //añadimos los dos ids si la palabra buscada es attitude
					  boolean fin = false;
					  int x =0;
					  while(!fin){
						  x++;
						  if(termAux.equals("agrega_"+ x +"_"+texto)){
							  lIdes.add(id);
						  }
						  else fin = true;
					  }
				  }
			  }
		  }
	  
	  }catch (Exception e) {
		  logger.error("error en obtenerIdentificador - " , e);
		  throw e;
	}
	  return lIdes;
  }	
    
    
    /**
     * Obtiene todas las relaciones asociativas del identificador que le pasamos
     * @param id Identificador del término
     * @param nomTesauro Nombre del Tesauro
     * @return lAsocia Lista de identificadores relacionados asociativamente con el identificador que le pasamos como parámetro
     */
    private List<String> obtenerRelacionAsociativa(
    		String id,
    		String nomTesauro,
    		String idioma)
    throws Exception
    {
    	//Relaciones asociativas del identificador indicado (tanto las que el identificador
    	//sea fuente, como los casos en el que sea destino)
    	List<String> lAsocia=new ArrayList<String>();
    	try{
	    	EstructuraVdex estructura=this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma) ;
	    	HashMap<?, ?> hash = estructura.getHashMap() ;
	    	if (hash.containsKey(id))
	    		lAsocia = ((Relaciones)hash.get(id)).getAsociativas();
    	}catch (Exception e) {
    		logger.debug("error en obtenerRelacionAsociativa - " , e);
    		throw e;
		}
    	return lAsocia;
    }

    /**
     * Obtiene todas las relaciones jerarquicas del identificador que le pasamos
     * @param id Identificador del término
     * @param nomTesauro Nombre del Tesauro
     * @return lAsocia Lista de identificadores relacionados jerarquicamente con el identificador que le pasamos como parámetro
     */
    
    private  List<String> obtenerRelacionJerarquica(
    		String id,
    		String nomTesauro,
    		String idioma)
    throws Exception 
    {
    	//Relaciones jerarquicas del identificador indicado
    	List<String> lJerar=new ArrayList<String>();
    	try{
	    	EstructuraVdex estructura= this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma); 
	    	HashMap<?, ?> hash = estructura.getHashMap() ;
	    	if (hash.containsKey(id))
	    		lJerar=((Relaciones)hash.get(id)).getJerarquicas();
    	}catch (Exception e) {
    		logger.debug("error en obtenerRelacionJerarquica - " , e);
    		throw e;
		}
    	return lJerar;
    }
    
   
   /**
    * Ordena alfabéticamente el array de TaxonVO que le pasamos como parámetro
    * @param taxones array de TaxonVO que contiene el identificador y el texto de los taxones
    * @return taxones array de TaxonVO que le pasamos como parámetro ordenado alfabéticamente
    */ 
    private  TaxonVO[] ordenarAlfabeticamente(
    		TaxonVO[] taxones)
    throws Exception
    {
    	try{
	    	TaxonVO[] taxonesSinTildes = new TaxonVO[taxones.length];
	    	
	    	//eliminamos las tildes para poder hacer la comparación correctamente
	    	for (int k=0;k<taxones.length;k++){
	    		TaxonVO taxonSinTildes = new TaxonVO();
	    		String valorTax = quitarTildes(taxones[k].getValorTax());
				
	    		taxonSinTildes.setValorTax(valorTax);
	    		taxonSinTildes.setId(taxones[k].getId());
	    		taxonSinTildes.setVocabName(taxones[k].getVocabName());
	    		taxonSinTildes.setTipoRelacion(taxones[k].getTipoRelacion());
	    		taxonesSinTildes[k]= taxonSinTildes;
	    	}
	    	
	    	for( int i=0;i<taxones.length-1;i++){                 
	    		for (int j=i+1;j<taxones.length;j++){
	    			String aux=null;
	    			String auxId=null;
	    			String aux2=null;
	    			String auxId2=null;
	    			String auxVocab=null;
	    			String auxVocab2=null;
	    			String auxTipo=null;
	    			String auxTipo2=null;
	    			String valorTaxI = taxonesSinTildes[i].getValorTax();
	    			String valorTaxJ = taxonesSinTildes[j].getValorTax();
	    			
	    			//comparamos los texto sin tildes y ordenamos a la vez tanto el array con los taxones sin tildes
	    			//como el array con los taxones originales que sera el que vamos devolver
	    			if(valorTaxI.compareToIgnoreCase(valorTaxJ)>0){
	    				aux=taxonesSinTildes[j].getValorTax();
	    				taxonesSinTildes[j].setValorTax(taxonesSinTildes[i].getValorTax());
	    				taxonesSinTildes[i].setValorTax(aux);
	    				auxId=taxonesSinTildes[j].getId();
	    				taxonesSinTildes[j].setId(taxonesSinTildes[i].getId());
	    				taxonesSinTildes[i].setId(auxId);
	    				auxVocab=taxonesSinTildes[j].getVocabName();
	    				taxonesSinTildes[j].setVocabName(taxonesSinTildes[i].getVocabName());
	    				taxonesSinTildes[i].setVocabName(auxVocab);
	    				auxTipo=taxonesSinTildes[j].getTipoRelacion();
	    				taxonesSinTildes[j].setTipoRelacion(taxonesSinTildes[i].getTipoRelacion());
	    				taxonesSinTildes[i].setTipoRelacion(auxTipo);
	    				
	    				aux2=taxones[j].getValorTax();
	    				taxones[j].setValorTax(taxones[i].getValorTax());
	    				taxones[i].setValorTax(aux2);
	    				auxId2=taxones[j].getId();
	    				taxones[j].setId(taxones[i].getId());
	    				taxones[i].setId(auxId2);
	    				auxVocab2=taxones[j].getVocabName();
	    				taxones[j].setVocabName(taxones[i].getVocabName());
	    				taxones[i].setVocabName(auxVocab2);
	    				auxTipo2=taxones[j].getTipoRelacion();
	    				taxones[j].setTipoRelacion(taxones[i].getTipoRelacion());
	    				taxones[i].setTipoRelacion(auxTipo2);
	    			}	
	    		}
	    	}
    	}catch (Exception e) {
    		logger.debug("error en ordenarAlfabeticamente - " , e);
    		throw e;
		}
    	return taxones;
    }
    
    private List<List<String>> obtenerRutas(
    		String id,
    		String nomTesauro,
    		String idioma,
    		List<String> rutaActual,
    		List<List<String>> rutas)
    throws Exception
    {
    	
    	List<String> ruta = new ArrayList<String>();
    	String nuevoDest=id;
    	try{
	    	EstructuraVdex estructura=  this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma);
	    	HashMap<?, ?> hash = estructura.getHashMap() ;
			if (rutas == null)
				rutas = new ArrayList<List<String>>();
	    	if (hash.containsKey(nuevoDest)){
	    		if (rutaActual == null)
	    			rutaActual = new ArrayList<String>();
	    		rutaActual.add(nuevoDest);
	    		Relaciones r = (Relaciones)hash.get(nuevoDest);
	    		List<String> padres = r.getIdPadres();
	    		if(padres.size()== 0){
	    			//componemos la ruta
	    			for(int i= 0;i<rutaActual.size();i++){
	    				ruta.add(rutaActual.get(i));
	    			}
	    			//añadimos la ruta con las demas
	    			rutas.add(ruta);	
	    		}
		    	else {
		    		for(int i = 0;i<padres.size();i++){
		    			nuevoDest=r.getIdPadres().get(i).toString();
		    			obtenerRutas(nuevoDest,nomTesauro,idioma,rutaActual,rutas);
		    			rutaActual.remove(nuevoDest);
	    			}
		    	}	
	    	} 	
    	}catch (Exception e) {
    		logger.error("error en obtenerRutas - " ,e);
    		throw e;
		}
		return rutas;
    }
    
    private List<TaxonVO[]> obtenerTextoRutas(
    		String idioma,
    		String nomTesauro,
    		List<List<String>> rutas)
    	throws Exception
    {
    	//obtiene el texto de las rutas que se le pasa por parámetro y devuelve una lista de arrays de TaxonVO
    	//y ademas ordena correctamente cada ruta
    	List<TaxonVO[]> rutasTexto = new ArrayList<TaxonVO[]>();
    	try{
	    	Iterator<List<String>> i = rutas.iterator();
	    	List<String> sigRuta = null;
	    	while (i.hasNext()){
	    		//para cada ruta obtenemos su texto
	    		sigRuta = i.next();
	    		TaxonVO[] sigRutaTexto = obtenerTexto(sigRuta, idioma, nomTesauro);
	    	    
	    		int tamaño = sigRutaTexto.length;   
	    	    TaxonVO[] rutaOrdenada = new TaxonVO[tamaño];
		       
	    	    //ordenamos correctamente la ruta
	    	    for( int j=tamaño;j>0;j--){
		    	   TaxonVO taxon = new TaxonVO(); 
		    	   taxon.setId(sigRutaTexto[j-1].getId());
		    	   taxon.setValorTax(sigRutaTexto[j-1].getValorTax());
		    	   taxon.setEsHoja(sigRutaTexto[j-1].getEsHoja());
		    	   taxon.setVocabName(sigRutaTexto[j-1].getVocabName());
		    	   taxon.setTipoRelacion(sigRutaTexto[j-1].getTipoRelacion());
		    	   rutaOrdenada[tamaño-j] = taxon;
	    	    }
	    		rutasTexto.add(rutaOrdenada);
	    	}
    	}catch (Exception e) {
    		logger.error("error en obtenerTextoRutas - " ,e);
    		throw e;
    	}
    	return rutasTexto;
    }
    
    private TaxonVO[] obtenerPadres(
    		String id,
    		String idioma,
    		String nomTesauro)
    throws Exception
    {
    	TaxonVO[] padresOrdenados=null;
    	//se obtiene los padres del id pasado por parametros en orden alfabético
    	try{
        	List<String> lPadres=new ArrayList<String>();
	    	EstructuraVdex estructura= this.getEstructurasDao().obtenerRelacionesTesauro(nomTesauro, idioma);
	    	HashMap<?, ?> hash = estructura.getHashMap() ;
	    	//obtenemos los padre del id
	    	if (hash.containsKey(id))
	    		lPadres = ((Relaciones)hash.get(id)).getIdPadres();
	    	TaxonVO[] padres = obtenerTexto(lPadres, idioma, nomTesauro);
	        padresOrdenados = ordenarAlfabeticamente(padres);
    	}catch (Exception e) {
    		logger.error("error en obtenerPadres - ",e);
    		throw e;
		}
        return padresOrdenados;
    }

	

	
	private String quitarTildes(
			String entrada)
	throws Exception
	{
		String resultado =null; 
	
		try{
			resultado= entrada; 
			resultado = resultado.replace('á', 'a');
			resultado = resultado.replace('é', 'e');
			resultado = resultado.replace('í', 'i');
			resultado = resultado.replace('ó', 'o');
			resultado = resultado.replace('ú', 'u');
			resultado = resultado.replace('Á', 'A');
			resultado = resultado.replace('É', 'E');
			resultado = resultado.replace('Í', 'I');
			resultado = resultado.replace('Ó', 'O');
			resultado = resultado.replace('Ú', 'U');
			
			resultado = resultado.replace('à', 'a');
			resultado = resultado.replace('è', 'e');
			resultado = resultado.replace('ì', 'i');
			resultado = resultado.replace('ò', 'o');
			resultado = resultado.replace('ù', 'u');
			resultado = resultado.replace('À', 'A');
			resultado = resultado.replace('È', 'A');
			resultado = resultado.replace('Ì', 'I');
			resultado = resultado.replace('Ò', 'O');
			resultado = resultado.replace('Ù', 'U');
			
			resultado = resultado.replace('ä', 'a');
			resultado = resultado.replace('ë', 'e');
			resultado = resultado.replace('ï', 'i');
			resultado = resultado.replace('ö', 'o');
			resultado = resultado.replace('ü', 'u');
			resultado = resultado.replace('Ä', 'A');
			resultado = resultado.replace('Ë', 'E');
			resultado = resultado.replace('Ï', 'i');
			resultado = resultado.replace('Ö', 'O');
			resultado = resultado.replace('Ü', 'U');
		}catch (Exception e) {
			logger.error("error en quitarTildes",e);
    		throw e;
		}
		return resultado;

	}



	private TaxonVO[] obtenerTerminosTexto(
			String texto, 
			String nomTesauro, 
			String idioma)
	throws Exception 
	{	
		TaxonVO[] terminos=null;
		try{
			ArrayList<TaxonVO> terminosEnc = new ArrayList<TaxonVO>();
			//Eliminamos los espacio que tenga el texto tanto al principio como al final
			texto = texto.trim();
			//Pasamos texto a minuscula y sustituimos la vocales con tildes con vocales sin tilde
			String textoBuscar= quitarTildes(texto.toLowerCase()); 
	
			//comprobamos que esta bien formada
			Pattern mask=Pattern.compile("^([0-9][a-zñ.\\'ç\\-0-9\\s/]*)|[a-zñ.\\'ç\\-/][a-zñ0-9.\\'ç\\-/][a-zñ0-9.\\'ç\\-\\s/]*$");//
			Matcher matcher = mask.matcher(textoBuscar);
			boolean correcto = matcher.matches();
			
			if(!texto.equals("") && correcto){
				//elimininamos espacio en blanco ejem:"historia de   la    filosofia" --> "historia de la filosofia"
				String[] aux2 =textoBuscar.split("  ");
				StringBuilder textoTemp=new StringBuilder();
				for(int i=0;i<aux2.length;i++){
					if(!aux2[i].trim().equals("")){
						textoTemp.append(" " + aux2[i]);
					}
				}
				textoBuscar = textoTemp.toString().trim();
	
				EstructuraVdex estruc = this.getEstructurasDao().obtenerIdTesauro(nomTesauro, idioma); 
				if(estruc!=null){
					HashMap<?, ?> auxHashId = estruc.getHashMap();//hashMap con id con key y terminos como value
					EstructuraVdex estructura = this.getEstructurasDao().obtenerTerminoTesauro(nomTesauro, idioma); 
					if(estructura!=null){
						//terminos del tesauro
						HashMap<?, ?> auxHashTerm = estructura.getHashMap();//hashMap con termino con key y id como value
						Collection<?> cTerminos = auxHashTerm.values();//identificadores
						Iterator<?> iTerminos=cTerminos.iterator();
						while(iTerminos.hasNext()){//para cada identificador 
							String id= (String) iTerminos.next();//identificador
							String term = (String) auxHashId.get(id);//termino correspondiente a id
							//eliminamos los espacios,pasamos a minusculas y eliminamos la tildes del termino
							term = term.trim();
							term = term.toLowerCase();
							String termAux = quitarTildes(term); 
	
							if(termAux.equals(textoBuscar) || termAux.endsWith(" " + textoBuscar ) 
									|| termAux.startsWith(textoBuscar + " " ) 
									|| termAux.indexOf(" " + textoBuscar + " ") > -1) {
								TaxonVO taxon = new TaxonVO();
						      	   taxon.setId(id);
						      	   taxon.setValorTax(term);
						      	   taxon.setEsHoja(false);
						      	   taxon.setVocabName(estructura.getVocabName());	
						      	   terminosEnc.add(taxon);
							}
						}
					}
				}
			}
			terminos = terminosEnc.toArray(new TaxonVO[terminosEnc.size()]);
		}catch (Exception e) {
			logger.error("error en obtenerTerminosTexto - ",e);
    		throw e;
		}
		return terminos;
	}


	protected Boolean handleChequearIdiomaTesauro(
			String identificador,
			String idioma) 
	throws Exception 
	{
		return (this.getEstructurasDao().obtenerIdTesauro(identificador, idioma)!=null);
	}


}