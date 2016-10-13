/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.compass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassQuery.SortDirection;
import org.compass.core.CompassQuery.SortPropertyType;
import org.compass.core.CompassSession;
import org.compass.core.CompassTermFreq;
import org.compass.core.CompassTermFreqsBuilder;
import org.compass.core.CompassTransaction;
import org.compass.core.engine.SearchEngineOptimizer;
import org.compass.core.engine.spellcheck.SearchEngineSpellCheckManager;
import org.compass.core.engine.spellcheck.SearchEngineSpellCheckSuggestBuilder;
import org.compass.core.engine.spellcheck.SearchEngineSpellSuggestions;
import org.compass.core.lucene.engine.LuceneSearchEngineInternalSearch;
import org.compass.core.lucene.util.LuceneHelper;
import org.compass.gps.CompassGps;
import org.compass.gps.CompassGpsException;

import es.pode.indexador.negocio.servicios.busqueda.PalabraClaveVO;
import es.pode.indexador.negocio.servicios.busqueda.PrioridadPalabrasClaveVO;
import es.pode.soporte.i18n.I18n;

public class GestorCompass {
	
	
	private static Logger logger = Logger.getLogger(GestorCompass.class);
	private Compass compass;
	private CompassGps gps;
	private static Properties props;
	private static String prefijo_subindex;
	private final static String FICHERO_PROPIEDADES = "/indexador.properties";
	private final static String PREFIJO_SUBINDICE = "prefijo_subindex";
	private static final String STOP_WORDS ="hightFreqTerms.stopWords.";
	
	
	public GestorCompass(){
		if (props==null) {
			try {
				Properties properties = new Properties();
				properties.load(this.getClass().getResourceAsStream(
						FICHERO_PROPIEDADES));
				
				prefijo_subindex = properties.getProperty(PREFIJO_SUBINDICE);
				props = properties;
			} catch (IOException e) {
				logger.error("ERROR: fichero de propiedades <"+ FICHERO_PROPIEDADES + "> no encontrado. - ", e);
				throw new RuntimeException("ERROR: fichero de propiedades <"+ FICHERO_PROPIEDADES + "> no encontrado.", e);
			}
		}
	}
	
	
//	Metodos publicos para la injección de dependencias. Ver user-applicationContext_indexador.xml
	public Compass getCompass() {
		return compass;
	}
	
	
	public void setCompass(Compass compass) {
		this.compass = compass;
	}
	
	
	public CompassGps getGps() {
		return gps;
	}
	
	
	public void setGps(CompassGps gps) {
		this.gps = gps;
	}
	
	
	public String[] obtenerSugerencias(String palabra,String idioma, int numeroSugerencias) throws Exception{
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto: <"+idioma +"> no es un idioma de busqueda");
		
		String[] sugerencias=null;

		if (palabra==null || palabra.equals(""))
		{
			logger.warn("No hay sugerencias posibles para la palabra <"+palabra+">");
			return sugerencias;
		}
		if (logger.isDebugEnabled())
			logger.debug("Obtenemos motor de sugerencias para palabra <"+ palabra+">");
		SearchEngineSpellCheckSuggestBuilder suggestBuilder = compass.getSpellCheckManager().suggestBuilder(palabra);
//		Añadimos el subindice(spellcheck) en el queremos buscar sugerencias
		if(idioma !=null){
			String subIndexSug = prefijo_subindex + idioma;
			String[] subindex = {subIndexSug};
			suggestBuilder.subIndexes(subindex);
		}
//		if (logger.isDebugEnabled())logger.debug("Buscamos sugerencias en los subindices["+(idioma==null?"todos":idioma)+"].");
		
//		Indicamos el número de sugerencias que queremos que nos devuelva
//		if (logger.isDebugEnabled())logger.debug("Maximo numero de sugerencias["+numeroSugerencias+"].");
		suggestBuilder.numberOfSuggestions(numeroSugerencias);
		//obtenemos sugerencias
		SearchEngineSpellSuggestions sug = suggestBuilder.suggest();
		if (logger.isDebugEnabled())
			logger.debug("Obtenemos <"+sug.getSuggestions().length+"> sugerencias para palabra <"+palabra+"> y subindices <"+(idioma==null?"todos":idioma)+">");
		if(sug.getSuggestions().length > 0){
			//obtenemos el array de sugerencias
			sugerencias= sug.getSuggestions();

			  //if (logger.isDebugEnabled()){ for (int i = 0; i <
			  //sugerencias.length; i++)
			  //logger.debug("Sugerencia para palabra <" + palabra + "> y subindices <" +
			  //(idioma == null ? "todos" : idioma) + "> <" + sugerencias[i] + ">");}
			  
		} else{
			if (logger.isDebugEnabled())
				logger.debug("No hay sugerencias para la palabra <"+palabra+"> y subindices <"+(idioma==null?"todos":idioma)+">");
		}
		return sugerencias;
	}

	
	public Hits busqueda(Query consulta, String idioma, String campoOrdenacion) throws Exception {
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto:"+idioma +" no es un idioma de busqueda");
		
		Hits hitsLucene = null;

		if (consulta != null) {
			CompassSession session = compass.openSession();
			CompassTransaction tr = session.beginTransaction();

			CompassQuery compassQuery = LuceneHelper.createCompassQuery(session, consulta);
			
			// Añadimos el subindice en el que queremos buscar
			String[] subindex = null;
			if (idioma != null) {					
				String subIndexBusq = prefijo_subindex + idioma;
				subindex = new String[1];
				subindex[0] = subIndexBusq;
				compassQuery.setSubIndexes(subindex);
			} 

			//Añadimos ordenacion de resultados (nivelAgregacion,fechaPublicacion)
			if(campoOrdenacion!=null)
				compassQuery.addSort(campoOrdenacion,SortPropertyType.STRING,SortDirection.REVERSE);
//			logger.debug("Buscando en subindice <"+(idioma==null?"todos":idioma)+"> con consulta <" + consulta+">");
			
			//Obtenemos hits de compass
			CompassHits hitsCompass = compassQuery.hits();
//			logger.debug("Obtenidos <"+(hitsCompass!=null?hitsCompass.getLength():0)+">");
			
			//Transformamos los hits de compass en hits de lucene
			if(hitsCompass!=null && hitsCompass.length()>0)
				hitsLucene = LuceneHelper.getLuceneSearchEngineHits(hitsCompass).getHits();
			else
				logger.debug("hits de compass=null");
				
			tr.commit();
			session.close();
		} else {
			logger.debug("La consulta es null");			
		}
		return hitsLucene;
	}
	
	
	private Query getTermQuery(String f, String t) {
		return new TermQuery(new Term(f, t));
	}
	
	
	public Integer[] numTermsArbolQuery(String[] identificadores, String idioma,String campo) throws Exception {
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto:"+idioma +" no es un idioma de busqueda");
		Integer[] resultados=new Integer[identificadores.length];
		Hits hitsLucene = null;

		for (int j = 0; j < resultados.length; j++) {
			DisjunctionMaxQuery consulta = new DisjunctionMaxQuery(0.01f);
			consulta.add(getTermQuery("taxonomia", identificadores[j].toLowerCase()));
			
			if (consulta != null) {
				CompassSession session = compass.openSession();
				CompassTransaction tr = session.beginTransaction();

				logger.debug("Buscando en subindice <"+ (idioma == null ? "todos" : idioma)
							+ "> con consulta <" + consulta + ">");
				
				CompassQuery compassQuery = LuceneHelper.createCompassQuery(session, consulta);
				
				// Añadimos el subindice en el queremos buscar
				String[] subindex = null;
				if (idioma != null) {
					String subIndexBusq = prefijo_subindex + idioma;
					subindex = new String[1];
					subindex[0] = subIndexBusq;
					compassQuery.setSubIndexes(subindex);
				} 
				
				//Obtenemos hits de compass
				CompassHits hitsCompass = compassQuery.hits();
				logger.debug("busqueda - Obtenidos <" + (hitsCompass != null ? hitsCompass.getLength(): 0) + ">");
				//Transformamos los hits de compass en hits de lucene
				if (hitsCompass != null && hitsCompass.length() > 0) {
					hitsLucene = LuceneHelper.getLuceneSearchEngineHits(hitsCompass).getHits();
					
//					logger.debug("Antes de quitar duplicados tenemos "+hitsLucene.length());	
					
					//Elimino duplicados
					HashMap<String, Document> map = new HashMap<String, Document>();
					for (int i = 0; i < hitsLucene.length(); i++) {
						map.put(hitsLucene.doc(i).get("identificador"),hitsLucene.doc(i));
					}
					logger.debug("Despues de quitar duplicados tenemos <"+map.size()+">");
						resultados[j] = map.size();
				} else {
					logger.debug("numTermsArbolQuery: hits de compass <"+j+">=null");	
				}				
				tr.commit();
				session.close();
			} 
		}
		return resultados;
	}
	
	
	public Hits[] hitsArbolQuery(String[] identificadores, String idioma,String campo) throws Exception {
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto: <"+idioma +"> no es un idioma a buscar");
//		Integer[] resultados=new Integer[identificadores.length];
		
		Hits[] hitsLucene = new Hits[identificadores.length];

		for (int j = 0; j < identificadores.length; j++) {
			DisjunctionMaxQuery consulta = new DisjunctionMaxQuery(0.01f);
			consulta.add(getTermQuery("taxonomia", identificadores[j].toLowerCase()));
			
			if (consulta != null) {
				CompassSession session = compass.openSession();
				CompassTransaction tr = session.beginTransaction();

					logger.debug("busqueda - Buscando en subindice <" + (idioma == null ? "todos" : idioma)
							+ "> con consulta <" + consulta + ">");
				CompassQuery compassQuery = LuceneHelper.createCompassQuery(session, consulta);
				
				// Añadimos el subindice en el queremos buscar
				String[] subindex = null;
				if (idioma != null) {
					String subIndexBusq = prefijo_subindex + idioma;
					subindex = new String[1];
					subindex[0] = subIndexBusq;
					compassQuery.setSubIndexes(subindex);
				}
				
				//Obtenemos hits de compass
				CompassHits hitsCompass = compassQuery.hits();
				logger.debug("hits de compass obtenidos <" + (hitsCompass != null ? hitsCompass.getLength(): 0) + ">");
				//Transformamos los hits de compass en hits de lucene
				if (hitsCompass != null && hitsCompass.length() > 0) {
					hitsLucene[j] = LuceneHelper.getLuceneSearchEngineHits(hitsCompass).getHits();
					
//					logger.debug("Antes de quitar duplicados tenemos "+hitsLucene.length());	
//					
//					//Elimino duplicados
//					HashMap<String, Document> map = new HashMap<String, Document>();
//					for (int i = 0; i < hitsLucene.length(); i++) {
//						map.put(hitsLucene.doc(i).get("identificador"),hitsLucene.doc(i));
//					}
//					logger.debug("Despues de quitar duplicados tenemos "+map.size());
//						resultados[j] = map.size();
				} else {
					logger.debug("hitsArbolQuery: hits de compass <"+j+">=null");	
				}		
				tr.commit();
				session.close();
			}
		}
		return hitsLucene;
	}
	
	
	public Integer numDocs(String idioma) throws Exception {
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto:"+idioma +" no es un idioma de busqueda");
		
		Integer nDocs = 0;

		CompassSession session = compass.openSession();
		CompassTransaction tr = session.beginTransaction();

		String[] subindex = null;
		if (idioma != null) {
			String subIndexBusq = prefijo_subindex + idioma;
			subindex = new String[1];
			subindex[0] = subIndexBusq;
		}
					
		IndexReader reader = null;
		try{
			LuceneSearchEngineInternalSearch internalSearch = LuceneHelper.getLuceneInternalSearch(session, subindex, null);
			if(internalSearch !=null)
				reader = internalSearch.getReader();
		}catch(Exception e){
			logger.error("numDocs - Error obteniendo el IndexReader de los subindices <"+(idioma==null?"todos":idioma)+"> - ",e);
		}
				
		if (reader != null)
			nDocs = Integer.valueOf(reader.numDocs());
		logger.debug("Numero de documentos de los subindices <"+(idioma==null?"todos":idioma)+"> <" + nDocs+">");
		tr.commit();
		session.close();
		return nDocs;
	}

	public Document obtenerDocumento(int docSelec) {
		Document doc = null;

		CompassSession session = compass.openSession();
		CompassTransaction tr = session.beginTransaction();;
		
		IndexReader reader = null;
		try{
			LuceneSearchEngineInternalSearch internalSearch = LuceneHelper.getLuceneInternalSearch(session, null, null);
			if(internalSearch !=null)
				reader = internalSearch.getReader();
		}catch(Exception e){
			logger.error("Error obteniendo el IndexReader de los todos subindices - ",e);
		}
		
		if (reader != null){
			logger.debug("Obtenemos el documento <"+ docSelec+">");
			try {
				doc = reader.document(docSelec);
			} catch (Exception e) {
				logger.error("Error al obtener el documento <" + docSelec+"> del IndexReader. - ",e);
			}
		}else{
			logger.warn("No podemos obtener el documento <" + docSelec + ">, indice vacio.");
		}
		tr.commit();
		session.close();
		return doc;
	}

	public Integer[] numTermsArbol(String[] identificadores, String idioma,String campo) throws Exception {
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto:"+idioma +" no es un idioma de busqueda");
		
		TermEnum terms=null;
		Integer[] conteo = new Integer[identificadores.length];
		try {

			terms= this.obtenerTerminos(idioma);
			
			for(int i = 0;i<conteo.length;i++)conteo[i]=0;
			
			int minFreq = 0;					
			if(terms!=null){
				while (terms.next()) {
					if (terms.docFreq() >= minFreq && terms.term().field().equals(campo)) {
						for(int i = 0 ; i < identificadores.length; i++){
							if(identificadores[i]!=null && identificadores[i].equals(terms.term().text().toUpperCase())){
								logger.debug("frecuencia= <"+terms.docFreq()+"> termino= <"+terms.term().text()+">");
								conteo[i]=terms.docFreq();
								continue;
							} 
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("numTermsArbol - Error obteniendo los terminos del indice. - ",e);
		}
		return conteo;
	}

	public Hashtable<String, String> obtenerFechasRepositorio(String campo) {
		Hashtable<String, String> fechas= new Hashtable<String, String>();
		TermEnum terms = null;
		try{
			terms = this.obtenerTerminos(null);
			if(terms !=null){
				while (terms.next()){
//					Si el termino que tratamos es el de fecha, cojemos su valor
					if (campo.equals(terms.term().field()))
						fechas.put(terms.term().text(), "");
				}
			}
		
		} catch (Exception e) {
			logger.error("Error obteniendo los terminos del indice. - ",e);
		}
		return fechas;
	}
	
	
	public PrioridadPalabrasClaveVO highFreqTerms(int numResultados, String idioma,String campo) throws Exception{
		if(!validarIdiomaBusqueda(idioma))
			throw new Exception("Idioma incorrecto:"+idioma +" no es un idioma de busqueda");
		
		CompassSession session = compass.openSession();
		CompassTransaction tr = session.beginTransaction();

		String[] subindex = null;
		
		String[] aStopWords = new String[0];
		String stopWords = props.getProperty(STOP_WORDS + idioma);
		
		if(stopWords!=null)
			aStopWords = stopWords.trim().split(",");
		logger.debug("Obtenidos las stopWord: <" + Arrays.toString(aStopWords)+">");
		
		if(idioma !=null){
			String subIndexBusq = prefijo_subindex + idioma;
			subindex = new String[1];
			subindex[0] = subIndexBusq;
		}
		logger.debug("Obtenemos <"+ numResultados +"> terminos mas frecuentes de subindices <"+(idioma==null?"todos":idioma)+">");
		
		CompassTermFreqsBuilder termFreqsBuilder = session.termFreqsBuilder(new String[]{campo});
		int numTerms = numResultados + aStopWords.length;
		termFreqsBuilder.setSize(numTerms);
		termFreqsBuilder.setSort(CompassTermFreqsBuilder.Sort.FREQ);
		termFreqsBuilder.setSubIndexes(subindex);
		CompassTermFreq[] terms = termFreqsBuilder.toTermFreqs();
		logger.debug("Numero de terminos <"+ terms.length+"> de subindices <"+(idioma==null?"todos":idioma)+">");
		
		PrioridadPalabrasClaveVO resultado = new PrioridadPalabrasClaveVO();
		int tamaño = terms.length > numResultados? numResultados:terms.length;
		int j=0;
		ArrayList<PalabraClaveVO> aPalabrasClave = new ArrayList<PalabraClaveVO>();
		for(int i = 0;i< terms.length && j<tamaño; i++) {
			if(!esStopWord(terms[i].getTerm().trim().toLowerCase(),aStopWords)){
				PalabraClaveVO palabraClave = new PalabraClaveVO();
				palabraClave.setPalabraClave(terms[i].getTerm());
				palabraClave.setRanking((int) terms[i].getFreq());
				aPalabrasClave.add(palabraClave);
				j++;
			}
		}
		PalabraClaveVO[] palabrasClave = aPalabrasClave.toArray(new PalabraClaveVO[0]);
		resultado.setPalabrasClave(palabrasClave);
//		session.close();
		tr.commit();
		return resultado;
	}

	
	public boolean sincronizar() {
		try	{
			if (!gps.isPerformingIndexOperation()) {
				boolean isRunning = gps.isRunning();
				if (!isRunning) {
					gps.start();
				}
				// gps.index();

				gps.index();
				if (!isRunning) {
					gps.stop();
				}
				return true;
			} else {
				logger.debug("Ya se esta realizando una operacion de volvado de datos de la bbdd al indice.");
			}
		} catch (CompassGpsException ex) {
			logger.error("Compass Gps Device exception - ", ex);
		} catch (IllegalStateException ex) {
			logger.error("Illegal State Exception - ", ex);
		} catch (Exception ex) {
			logger.error("Exception - ", ex);
		} 				
		return false;
	}

	
	public void sincronizarSpellCheck() {
		logger.info("**********************sincronizarSpellCheck**********************");
		SearchEngineSpellCheckManager spellCheckManager = compass.getSpellCheckManager();
		SearchEngineOptimizer optimizer = compass.getSearchEngineOptimizer();
		optimizer.optimize();
		if (spellCheckManager.isRebuildNeeded()) {
			boolean resultado = spellCheckManager.rebuild();
			if (resultado)
				if (logger.isInfoEnabled()) logger.info("REALIZADO REBUILD SPELLCHECK CORRECTAMENTE");
			else
				if (logger.isInfoEnabled()) logger.info("NO REALIZADO REBUILD SPELLCHECK CORRECTAMENTE");
		}
		else
			if (logger.isInfoEnabled()) logger.info("NO HAY NECESIDAD DE HACER REBUILD SPELLCKECK");
		if (logger.isInfoEnabled()) logger.info("********************** fin sincronizarSpellCheck**********************");
	}

	private TermEnum obtenerTerminos(String idioma) throws Exception{
		CompassSession session = compass.openSession();
		CompassTransaction tr = session.beginTransaction();
		
		String[] subindex = null;
		if(idioma !=null){
			String subIndexBusq = prefijo_subindex + idioma;
			subindex = new String[1];
			subindex[0] = subIndexBusq;
		}
//		if (logger.isDebugEnabled())logger.debug("Obtenemos los términos de los subindices <"+(idioma==null?"todos":idioma)+">");
		IndexReader reader = null;
		try{
			LuceneSearchEngineInternalSearch internalSearch = LuceneHelper.getLuceneInternalSearch(session, subindex, null);
			if(internalSearch !=null)
				reader = internalSearch.getReader();
		}catch(Exception e){
			logger.error("Error obteniendo el IndexReader de los subindices <"+(idioma==null?"todos":idioma)+"> - ",e);
		}
		
		TermEnum terms = null;
		
		if (reader != null){
			terms = reader.terms();
		}
		else  
			logger.debug("No se han obtenidos terminos de los subindices. Indice vacio <"+(idioma==null?"todos":idioma)+">");
			
//		session.close();
		tr.commit();
		return terms;
	}
	
	private static boolean esStopWord(String campo, String[] stopWords) {
		for(int i = 0 ; i < stopWords.length ; i++) if(stopWords[i].equals(campo)) return true;
		return false;
	}
	
	private boolean validarIdiomaBusqueda(String idioma){
		if(idioma == null)
			return true;
		
		String[] idiomas;
		try {
			idiomas = I18n.getInstance().obtenerIdiomasBuscables();
			if(idiomas!=null){
				for(int i = 0; i < idiomas.length;i++){
					if(idiomas[i].equals(idioma))
						return true;
				}
			}
		} catch (Exception e) {
			logger.error("ERROR obteniendo idiomas a buscar - ", e);
		}
		return false;
	}
	
	public void optimizarBorrados(String idiomaSubindex)throws Exception{
		if(!validarIdiomaBusqueda(idiomaSubindex))
			throw new Exception("Idioma incorrecto: <"+idiomaSubindex +"> no es un idioma de busqueda");		
		
		CompassSession session = compass.openSession();
		CompassTransaction tr = session.beginTransaction();
		String[] subindex = null;
		if(idiomaSubindex !=null){
			String subIndexBusq = prefijo_subindex + idiomaSubindex;
			subindex = new String[1];
			subindex[0] = subIndexBusq;
		}
			
		LuceneSearchEngineInternalSearch internalSearch = LuceneHelper.getLuceneInternalSearch(session, subindex, null);
		IndexReader reader;
		if(internalSearch !=null){
			reader = internalSearch.getReader();	
			
			if(reader!=null && reader.hasDeletions()){
				logger.info("MaxDoc = <"+reader.maxDoc()+"> NumDeletedDocs = <"+reader.numDeletedDocs()+"> NumDoc = <"+reader.numDocs()+">");
				SearchEngineOptimizer optimizer = compass.getSearchEngineOptimizer();
				if (logger.isInfoEnabled()) logger.info("Existen documentos borrados en el indice, optimizamos...");
				long inicio = System.currentTimeMillis();
				if(idiomaSubindex!=null)
					optimizer.optimize(subindex[0],1);//optimizamos el indice que se nos indica
				else 
					optimizer.optimize(1);// optimizamos todos los indices
				long fin = System.currentTimeMillis();
				logger.debug("Finalizamos optimizacion en ---> <" + (fin-inicio)/1000 +"> segundos");
			}
		}
		tr.commit();
	}
}
