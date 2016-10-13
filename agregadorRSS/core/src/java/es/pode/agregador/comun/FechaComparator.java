/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.agregador.comun;

import java.util.Comparator;

import com.sun.syndication.feed.synd.SyndEntryImpl;

public class FechaComparator implements Comparator{

	public int compare(Object o1, Object o2) {
		try{
			SyndEntryImpl obj1 = (SyndEntryImpl) o1;
			SyndEntryImpl obj2 = (SyndEntryImpl) o2;
			
			int resultado = obj1.getPublishedDate().compareTo(obj2.getPublishedDate());
			int resul=0;
			if (resultado<0){
				resul=1;
			}else if (resultado>0){
				resul=-1;
			}else if (resultado==0){
				resul=resultado;
			}
			
			return resul;
		}catch (Exception e){
			return 0;
		}
	}

}
