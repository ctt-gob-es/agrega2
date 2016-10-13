/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.soporte;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO;

public class NumTermsArbol {
	private static Logger logger = Logger.getLogger(NumTermsArbol.class);
	
	public static ResultadosCountVO obtenerNumeroNodos(String[] identificadores, String indexPath) throws Exception {
//		if(logger.isDebugEnabled())logger.debug("NumTermsArbol - obtenerNumeroNodos");
//		try {
//			Directory directory = FSDirectory.getDirectory(indexPath);
//			IndexReader reader = IndexReader.open(directory);
//
//			TermEnum terms = reader.terms();
//			ResultadosCountVO resultado = new ResultadosCountVO();
//			resultado.setConteo(new Integer[identificadores.length]);
//			int minFreq = 0;
//			while (terms.next()) {
//				if (terms.docFreq() >= minFreq && terms.term().field().equals(campo)) {
//					for(int i = 0 ; i < identificadores.length; i++){
//						if(identificadores[i]!=null && identificadores[i].equals(new TermInfo(terms.term(), terms.docFreq()).term.text().toUpperCase())){
//							if(logger.isDebugEnabled())logger.debug("NumTermsArbol - obtenerNumeroNodos: terms.docFreq()="+terms.docFreq()+"   new TermInfo(terms.term(), terms.docFreq()).term.text()="+new TermInfo(terms.term(), terms.docFreq()).term.text());
//							resultado.getConteo()[i]=terms.docFreq();
//							continue;
//						}
//					}
//				}
//			}
//			int suma = 0;
//			for(int i = 0 ; i < resultado.getConteo().length; i++){
//				if(resultado.getConteo()[i]==null) resultado.getConteo()[i]=0;
//				else suma = suma + resultado.getConteo()[i].intValue();
//			}
//			resultado.setDocumentosCount(suma);
//			reader.close();
//			directory.close();
//			return resultado;
//		} catch (Exception e) {
//			Exception exc = new Exception(" caught a " + e.getClass() + "\n with message: ",e);
//			logger.error(exc);
//			throw exc;
//		}
		
		IndexSearcher searcher=null;
		
		try {
			searcher = new IndexSearcher(indexPath);

			ResultadosCountVO resultado = new ResultadosCountVO();
			resultado.setConteo(new Integer[identificadores.length]);
			int suma = 0;
			
			for (int i = 0; i < identificadores.length; i++) {
				String string = identificadores[i];
				DisjunctionMaxQuery consulta = new DisjunctionMaxQuery(0.01f);
				consulta.add(getTermQuery("taxonomia", identificadores[i].toLowerCase()));
				Hits hits = searcher.search(consulta);
				
//				logger.debug("Antes de quitar duplicados tenemos "+hits.length());	
				
				//Elimino duplicados
				HashMap<String, Document> map = new HashMap<String, Document>();
				for (int j = 0; j < hits.length(); j++) {
					map.put(hits.doc(j).get("identificador"),hits.doc(j));
				}
				logger.debug("Despues de quitar duplicados tenemos "+map.size());
				
				resultado.getConteo()[i]=map.size();
				suma = suma + map.size();
			}

			resultado.setDocumentosCount(suma);
			searcher.close();
			return resultado;
		} catch (Exception e) {
			Exception exc = new Exception(" caught a " + e.getClass() + "\n with message: ",e);
			logger.error(exc);
			throw exc;
		} finally {
			if(searcher!=null) {
				searcher.close();
			}
		}
	}
	
	private static Query getTermQuery(String f, String t) {
		return new TermQuery(new Term(f, t));
	}
	
	public static Hits[] obtenerHitsNodos(String[] identificadores, IndexSearcher searcher) throws Exception {
		
		Hits[] hitsNodos=new Hits[identificadores.length];
		
		try {
			ResultadosCountVO resultado = new ResultadosCountVO();
			resultado.setConteo(new Integer[identificadores.length]);
			for (int i = 0; i < identificadores.length; i++) {
				String string = identificadores[i];
				DisjunctionMaxQuery consulta = new DisjunctionMaxQuery(0.01f);
				consulta.add(getTermQuery("taxonomia", identificadores[i].toLowerCase()));
				hitsNodos[i] = searcher.search(consulta);
//				logger.debug("NUMTERMARBOL: Para termino <"+i+">, obtengo <"+hitsNodos[i].length()+"> elementos.");
			}
			logger.debug("hitsNodos <"+hitsNodos.toString()+"> con longitud <"+hitsNodos.length+">");

		} catch (Exception e) {
			Exception exc = new Exception(" caught a " + e.getClass() + "\n with message: "+e.getMessage(),e);
			logger.error(exc);
			throw exc;
		}
		return hitsNodos;
	}
}

