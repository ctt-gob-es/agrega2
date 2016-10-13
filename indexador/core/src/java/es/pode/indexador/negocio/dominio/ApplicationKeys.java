/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.dominio;

import java.io.Serializable;
import java.util.HashMap;

public class ApplicationKeys implements Serializable {

	private static HashMap hashIdiomas=initIdiomas();
	private static HashMap initIdiomas()
	{
		HashMap idiomas=new HashMap();
		idiomas.put("0","es");
		//idiomas.put("1","en_GB");
		return idiomas;
	}
	
	public static HashMap valuesOfIdiomas()
	{
		return hashIdiomas;
	}

	
}
