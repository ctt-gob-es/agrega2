package es.pode.indexador.negocio.soporte;

import org.apache.log4j.Logger;
import org.apache.lucene.util.PriorityQueue;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;

import es.pode.indexador.negocio.servicios.busqueda.PalabraClaveVO;
import es.pode.indexador.negocio.servicios.busqueda.ParamPalabrasClave;
import es.pode.indexador.negocio.servicios.busqueda.PrioridadPalabrasClaveVO;
import es.pode.indexador.negocio.servicios.busqueda.SrvBuscadorServiceImpl;

public class HighFreqTerms {
	private static Logger logger = Logger.getLogger(HighFreqTerms.class);
	
	public static PrioridadPalabrasClaveVO obtenerPalabrasClave(ParamPalabrasClave paramBusq, String indexPath) throws Exception {
		if(logger.isDebugEnabled())logger.debug("HighFreqTerms - obtenerPalabrasClave");
		PrioridadPalabrasClaveVO resultado = new PrioridadPalabrasClaveVO();
		try {
			Directory directory = FSDirectory.getDirectory(indexPath);
			IndexReader reader = IndexReader.open(directory);

			TermInfoQueue tiq = new TermInfoQueue(paramBusq.getNumeroResultados().intValue()+1);
			TermEnum terms = reader.terms();

			int minFreq = 0;
			int tamanio = 0;
			while (terms.next()) {
				if (terms.docFreq() >= minFreq && terms.term().field().equals("keyword") && !stopWord(new TermInfo(terms.term(), terms.docFreq()).term.text().trim().toLowerCase(),SrvBuscadorServiceImpl.props.getProperty("hightFreqTerms.stopWords." + paramBusq.getIdiomaBusqueda()).trim().split(","))) {
					tiq.put(new TermInfo(terms.term(), terms.docFreq()));
					if (tiq.size() == paramBusq.getNumeroResultados().intValue()+1) {		  // if tiq overfull
						tiq.pop();				  // remove lowest in tiq
						minFreq = ((TermInfo)tiq.top()).docFreq; // reset minFreq
					}else tamanio++;
				}
			}
			PalabraClaveVO[] palabrasClave = new PalabraClaveVO[(tamanio>=paramBusq.getNumeroResultados().intValue())?paramBusq.getNumeroResultados().intValue():tamanio];
			for(int i = (tamanio>=paramBusq.getNumeroResultados().intValue())?paramBusq.getNumeroResultados().intValue()-1:tamanio-1; i >= 0; i--) {
				PalabraClaveVO palabraClave = new PalabraClaveVO();
				TermInfo termInfo = (TermInfo)tiq.pop();
				palabraClave.setPalabraClave(termInfo.term.text());
				palabraClave.setRanking(termInfo.docFreq);
				palabrasClave[i]=palabraClave;
				if(logger.isDebugEnabled())logger.debug(termInfo.term + " " + termInfo.docFreq);
			}
			resultado.setPalabrasClave(palabrasClave);
			reader.close();
			directory.close();
			return resultado;
		} catch (Exception e) {
			Exception exc = new Exception(" caught a " + e.getClass() + "\n with message: ",e);
			logger.error(exc);
			throw exc;
		}
	}
	
	private static boolean stopWord(String campo, String[] stopWords) {
		for(int i = 0 ; i < stopWords.length ; i++) if(stopWords[i].equals(campo)) return true;
		return false;
	}
}

final class TermInfo {
	TermInfo(Term t, int df) {
		term = t;
		docFreq = df;
	}
	int docFreq;
	Term term;
}

final class TermInfoQueue extends PriorityQueue {
	TermInfoQueue(int size) {
	    initialize(size);
	}
	
	protected final boolean lessThan(Object a, Object b) {
		TermInfo termInfoA = (TermInfo)a;
		TermInfo termInfoB = (TermInfo)b;
		return termInfoA.docFreq < termInfoB.docFreq;
	}
}
