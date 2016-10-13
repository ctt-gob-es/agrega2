package es.pode.soporte.buscador;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.pode.soporte.constantes.ConstantesAgrega;

public class TraduccionesBuscador {

	
	private static HashMap mapaIdiomas = new HashMap(); // mapa que alberga los bunlde para cada idioma.
										// se accede al property por idioma
	private static Logger logger = Logger.getLogger(TraduccionesBuscador.class);
	private static final String ficheroProps = "/traduccionesBuscador.properties";
	private static Properties props = null;
	private static TraduccionesBuscador buscador = null;
	/*
	 * Obtiene un resoruce bundle para el idioma dado. Si no esta cargado lo carga. Si no existe devuelve null;
	 * */
	private ResourceBundle getBundleFile(String idioma)
	{
		ResourceBundle fichero = null;
		if ((fichero = (ResourceBundle)mapaIdiomas.get(idioma)) == null)  // si no existe, lo intentamos cargar
			fichero = addBundleFile(idioma);
		return fichero;
	}
	
	/*
	 * Añade un bundle para ese idioma a la coleccion de bundles y lo devuelve
	 * */
	private ResourceBundle addBundleFile(String idioma)
	{
		// Chequeamos que exista el fichero de bundle para el idioma dado.
		// Si no existe el fichero de properties para el idioma, no lo añadimos
		ResourceBundle fichero = null;
		try {
			fichero = ResourceBundle.getBundle("traduccionesBuscador", new Locale(idioma));
		} catch (RuntimeException e) {
			// No existe un resource bundle para un idioma dado
			logger.warn("Excepcion intentando buscar el fichero de bundle para el idioma["+idioma+"]["+e.getCause()+"]");
			return null;
		}
		if (fichero == null)
			return fichero;
		
		mapaIdiomas.put(idioma, fichero);
		return fichero;
	}
	/**
	 * Este metodo traduce la etiqueta que le pasan al idioma que se le suministra
	 * @param etiqueta tag que se quiere traducir
	 * @param idioma codigo de idioma ISO-639 en el que se quiere obtener la traduccion
	 * @return String
	 */
	public String traduceEtiqueta(String etiqueta, String idioma)
	{
		
		ResourceBundle fichero = getBundleFile(idioma);
		if (fichero != null)
			return fichero.getString(etiqueta);
		return "";
	}
	
	private static String getPropertyValue(String sKey) 
	{
		String sReturn="";
		try {
			if (props == null)
			{
				InputStream fIsSpringProperties = TraduccionesBuscador.class.getResourceAsStream(ficheroProps);
				props = new java.util.Properties();
				props.load(fIsSpringProperties);
			}
			sReturn = props.getProperty(sKey);
		} catch (IOException e) {
			logger.warn("Excepcion intentando obtener propiedad ["+sKey+"] del fichero de propiedades del buscador["+e.getMessage()+"]");
		}		
		// devolvemos la propiedad
		return sReturn;
	}
	
	public String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        ResourceBundle theResourceBundle = getResource(baseName,locale);
        try{
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        }
        return recurso;
    }
	
	public ResourceBundle getResource(String baseName, Locale locale){
        try{
        	return ResourceBundle.getBundle(baseName,locale);
        }catch (MissingResourceException mre){
        	try{
            	return ResourceBundle.getBundle(baseName,new Locale(getPropertyValue(ConstantesAgrega.IDIOMA_DEFECTO_PLATAFORMA)));
                
            }catch (MissingResourceException mr){
        		locale = new Locale("","");
        		return ResourceBundle.getBundle(baseName,locale);
            }
             
        }
    }
	
	public static TraduccionesBuscador getInstance()
	{
		if (buscador == null)
			buscador = new TraduccionesBuscador();
		return buscador;
	}
	
}
