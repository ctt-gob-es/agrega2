/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.contenidos.negocio.comun;

import java.io.Serializable;
import java.util.Comparator;

import es.pode.contenidos.negocio.noticias.servicio.NoticiaVO;

public class NoticiasDateComparator implements Comparator, Serializable {
		
		public int compare(Object o1, Object o2) {
			if (o2==null && o1==null) return 0;
			if (o2==null) return 1;
			if (o1==null) return -1;
			//TODO En la vida un date va a ser ""
			if(((NoticiaVO) o2).getFechaPublicacion()==null || ((NoticiaVO) o2).getFechaPublicacion().equals(""))return 1;
			if(((NoticiaVO) o1).getFechaPublicacion()==null || ((NoticiaVO) o1).getFechaPublicacion().equals(""))return -1;
			int results = (((NoticiaVO) o1).getFechaPublicacion().compareTo(((NoticiaVO) o2).getFechaPublicacion()));
			if(results > 0) return -1;
			if (results < 0)return 1;
			return 0;
		}
}
