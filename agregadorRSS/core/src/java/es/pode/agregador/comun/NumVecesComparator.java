package es.pode.agregador.comun;

import java.util.Comparator;

import com.sun.syndication.feed.synd.SyndEntryImpl;

public class NumVecesComparator implements Comparator{

	
	public int compare(Object o1, Object o2) {
		try{
			SyndEntryImpl obj1 = (SyndEntryImpl) o1;
			SyndEntryImpl obj2 = (SyndEntryImpl) o2;
			Integer uno = new Integer(obj1.getUri());
			Integer dos = new Integer(obj2.getUri());
			
			int resultado = uno.intValue() - dos.intValue();
			int resul=0;
			if (resultado<0){
				resul=1;
			}else if (resultado>0){
				resul=-1;
			}else if (resultado==0){
				resul=resultado;
			}
			
			return  resul;
		}catch (Exception e){
			return 0;
		}
	}

}
