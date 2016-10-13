package es.pode.catalogacion.soporte;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.pode.catalogacion.negocio.servicios.IdentificadorVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.validador.CodigoIdioma;

public class ValidarMec implements Serializable {

	//Este método 
	public boolean esValidoMEC(IdentificadorVO[] identificadores, String nivelAgregacion, String idiomaMeta, List mensajes, String idiomaMens) throws Exception {
		InputStream in = null;
		boolean mecValido=true;
		int cuentaMec=0;
		String desglosamec="";
		
		Properties prop = new Properties();
		in = this.getClass().getResourceAsStream("/catalogadorAvanzado.properties");
		prop.load(in);
		in.close();
		ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", new Locale(idiomaMens));
		
		if ((identificadores != null) && (identificadores.length>0)){
			
			String catalogoMec=prop.getProperty("mec_catalog");
			//recorremos la estructura de Identificadores
			for (int indi=0;indi<identificadores.length;indi++) {
				if ((identificadores[indi].getCatalogo()!=null) && (identificadores[indi].getCatalogo().equals(catalogoMec)) 
				   && (identificadores[indi].getEntrada()!=null) && (!identificadores[indi].getEntrada().equals(""))) {
					//miramos que el entry sea valido debe ser de la forma
					 //es-ex_20061017_2_1234567 siendo -ex opcional
					 Pattern mask=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)\\_([0-9]{1})\\_([0-9]{7}$)"); //ejm ee-zz
					 Matcher matcher = mask.matcher(identificadores[indi].getEntrada());
					 
					 //o de la forma es-ex_2006101722_1300009 siendo _ex opcional
					 Pattern mask2=Pattern.compile("^([A-Za-z]{2})(\\-[A-Za-z][A-Za-z])?\\_((?:19|20)\\d\\d)(?:0\\d|1[0-2])(3[01]|[012]\\d)([0-9]{1})([0-9]{1})\\_([0-9]{7}$)");
					 Matcher matcher2 = mask2.matcher(identificadores[indi].getEntrada());
					 // si uno de los dos es válido
					 mecValido = (mecValido && matcher.matches()) || (mecValido && matcher2.matches()); //true--> ok uno de los matches
					 if (mecValido) {
						 cuentaMec++; //lo hemos encontrado
						 desglosamec=identificadores[indi].getEntrada();
					 }
			    }
			}
		}
	    if ((mecValido) && (cuentaMec<=1)) {
	    	//hacems splitt
	    	if (!desglosamec.equals("")){
	    		//Vemos si tiene comunidad autonoma -ex...
	    		String[] guiones=desglosamec.split("\\_");
	    		if (guiones.length>1) { 
	    			String nivelAg="";
	    			String idiometa="";
	    			// Si la longitud es 3 es el MEC nuevo sino, el antiguo
	    			if (guiones.length==3){
	    				int posi=guiones.length-2;
	    				String digitos =guiones[posi];
	    				//cogemos las dos ultimas posiciones penultima es idioma y ult nivelAgregacion 20061017 2 2
	    				int longidigi =digitos.length();
	    				nivelAg=digitos.substring(longidigi-1, longidigi); //nivel agregacion
	    				//si se llama desde validarMetadatos, no viene con 1.8.X sino con 1,2,3...	    				
	    				String nivelAgeProp="";
	    				if (nivelAgregacion.contains("."))
	    					nivelAgeProp=prop.getProperty(nivelAgregacion)!=null?prop.getProperty(nivelAgregacion):"";
	    				else
	    					nivelAgeProp=nivelAgregacion;
	    				
	    				idiometa=digitos.substring(longidigi-2, longidigi-1); // idioma metametadatos
//	    				String idiometaProp = prop.getProperty(idiomaMeta)!=null?prop.getProperty(idiomaMeta):"";
	    				CodigoIdioma cod = new CodigoIdioma();
	    		   		String idiometaProp = cod.obtieneCodigoIdioma(idiomaMeta);
	    				if (!nivelAgeProp.equals("") && (!nivelAgeProp.equals(nivelAg))) {
	    					//error
	    					mecValido = mecValido && false;
	    					mensajes.add(datosResources.getString("CAV.1.11"));
	    				}
	    				if ((!idiometaProp.equals("")) && (!idiometaProp.equals(idiometa))) {
//	    					error
	    					mecValido = mecValido && false;
	    					mensajes.add(datosResources.getString("CAV.1.12"));
	    				}
	    			} else {
	    				//en esa posicion tenemos un solo elemento
	    				int posi=guiones.length-2;
	    				nivelAg=guiones[posi];
	    				String nivelAgeProp="";
	    				if (nivelAgregacion.contains("."))
	    					nivelAgeProp=prop.getProperty(nivelAgregacion)!=null?prop.getProperty(nivelAgregacion):"";
	    				else
	    					nivelAgeProp=nivelAgregacion;
	    				
	    				idiometa="";//nos da igual
	    				if (!nivelAgeProp.equals("") && (!nivelAgeProp.equals(nivelAg))) {
	    					//error
	    					mecValido = mecValido && false;
	    					mensajes.add(datosResources.getString("CAV.1.11"));
	    				}
	    			}
	    			
	    		} 
	    	}
	    	return mecValido;
	    } else {
	    	if ((mecValido) && (cuentaMec>1)) {
	    		//El mec está dupicado
	    		mensajes.add(datosResources.getString("CAV.1.13"));
	    	}else {
	    		//El mec no cumple el patrón CAV.1.10
	    		mensajes.add(datosResources.getString("CAV.1.10"));
	    	}
	    	return false;
	    }
	}

}
