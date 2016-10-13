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
