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
