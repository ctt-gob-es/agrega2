/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.pode.administracion.presentacion;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * @author dgonzalezd
 *
 */
public class Utils {
	
	private static Logger log = Logger.getLogger(Utils.class);
	static public String tidyEntities(String texto) {
		String LT="&lt;";
		String GT="&gt;";
		texto = searchEntity(texto, LT);
		texto = searchEntity(texto, GT);
		return texto;
	}

	static public String searchEntity(String texto, String entity) {
		int i=0;
		while (i!=-1&&i<texto.length()) {
			int index=texto.indexOf(entity, i);
			if (index!=-1) {
				String prev=texto.substring(0, index);
				String post=texto.substring(index+4);
				String wrapped = wrapEntity(texto, index, entity.length());
				texto=prev+wrapped+post;
				i = index + wrapped.length();
			} else {
				i=index;
			}
		}
		return texto;
	}
	
	static public String wrapEntity(String texto,int index, int entitylength) {
		String CDATA_init="<![CDATA[";
		String CDATA_end="]]>";

		String entity=texto.substring(index, index+entitylength);
		texto=CDATA_init+entity+CDATA_end;
		return texto;
	}
	
	static public String ids2String (Iterator iter, String separator) {
	    StringBuilder listaId = new StringBuilder();
	    while (iter.hasNext()) {
    	//Previamente se realizaba estas dos acciones: pasar de String a Long y luego de Long a String y agregar separador.
	    	//Long id = Long.parseLong((String) iter.next());
	    	//listaId.append(id.toString() + separator);
    	//Ahora solo se añade separador realizando un cast a String.
	    	listaId.append((String)iter.next() + separator);
	    } 
	    return listaId.toString();
	}
	
	static public IdsBean ids2StringLong (Iterator iter, String separator) {
		    int i = 0;
 		    StringBuilder listaId = new StringBuilder();
 		    ArrayList<Long> IDs = new ArrayList<Long>();
 		    while (iter.hasNext()) {	
 		    	Long id = Long.parseLong((String) iter.next());
 		    	IDs.add(id);
 		    	listaId.append(id.toString() + separator);				
 		    	i = i + 1;
 		    } 
 		    
 		    IdsBean idsBean = new IdsBean();
 		    idsBean.setIDs(IDs);
 		    idsBean.setIds(listaId.toString());
 		    return idsBean;
	}
	
	public static class IdsBean {
		String ids;
		ArrayList<Long> IDs;
		/**
		 * @return the ids
		 */
		public String getIds() {
			return ids;
		}
		/**
		 * @param ids the ids to set
		 */
		public void setIds(String ids) {
			this.ids = ids;
		}
		/**
		 * @return the iDs
		 */
		public ArrayList<Long> getIDs() {
			return IDs;
		}
		/**
		 * @param iDs the iDs to set
		 */
		public void setIDs(ArrayList<Long> iDs) {
			IDs = iDs;
		}
	}
}
