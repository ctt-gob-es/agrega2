/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.validador;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CodigoIdioma {

	private  Logger logger = Logger.getLogger(this.getClass());
	
	private static final String ficheroProp = "/validador_soporte.properties";
	private static Properties prop = null;
	InputStream in = null;
    
    public String obtieneCodigoIdioma (String idiomaISO) throws Exception{
    	
    	String codigo="";
    	
    	Properties prop = new Properties();
		in = this.getClass().getResourceAsStream(ficheroProp);
		prop.load(in);
    	
		codigo= prop.getProperty(idiomaISO);
		if (codigo==null){
			codigo= "";
		}
    	
    	return codigo;
    }
	
}
