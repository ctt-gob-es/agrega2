/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.validador;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import es.pode.soporte.constantes.ConstantesAgrega;

public class TipoOde {
	
    private  Logger logger = Logger.getLogger(this.getClass());
    
    private String tipo= null;
    
    
    public void obtieneNameSpaceYEsquema (String rutaManifest, NamespaceYEsquema nye) throws Exception{
		
//		String namespace= null;
		
//		String imsManifest = new StringBuffer(rutaManifest).append("/imsmanifest.xml").toString();
//		//cambiamos las barras de directorio
//		imsManifest = imsManifest.replace( '\\', '/');
		
		File docFile = new File(rutaManifest);
    	Document doc = null;
    	try{
      		SAXBuilder builder = new SAXBuilder(false);
    		doc = builder.build(docFile);
    		Element manifest = doc.getRootElement();
    		Namespace nameSpace = manifest.getNamespace();
    		nye.setNamespace(nameSpace.getURI());
    		
    		Element metadata=manifest.getChild("metadata", nameSpace);
    		if (metadata != null){
    			nye.setEsquema(metadata.getChildText("schema", nameSpace));
    		}
    		else{
    			nye.setEsquema("");
    		}
    	}
    	catch (Exception e){
    		
    	   String mensaje = "ERROR en la construcción Jdom para el manifest - " + e;
 		   logger.error(mensaje);

    	}
    }
    	
   public void obtenerTipoOde (String rutaManifest){
	   
	   String tipoOde= null;
	   NamespaceYEsquema nye= new NamespaceYEsquema();
	   
	   try{
		   obtieneNameSpaceYEsquema (rutaManifest, nye);
		   
		   String ficheroProperties = "/validador_soporte.properties";
		   InputStream is= null;
		   is= this.getClass().getResourceAsStream(ficheroProperties);
		   Properties fproperties = new Properties();
		   fproperties.load(is);
		   String ns_scorm2004_prop= fproperties.getProperty("url_imscp_v1p1");
		   String ns_scorm12_prop= fproperties.getProperty("url_imscp_rootv1p1p2");
		   String eq_scorm2004_prop= fproperties.getProperty("ADL_SCORM");
//		   String eq_IMSCP_prop= fproperties.getProperty("IMS_Content");
		   
		   if ((ns_scorm2004_prop.equals(nye.namespace)) && (eq_scorm2004_prop.equals(nye.esquema))){
			   tipoOde= ConstantesAgrega.SCORM_04; 
		   }
		   else{
				if (ns_scorm12_prop.equals(nye.namespace)){
					tipoOde= ConstantesAgrega.SCORM_12;
				}
				else{
					if ((ns_scorm2004_prop.equals(nye.namespace))){
						tipoOde= ConstantesAgrega.IMS_CP;
					}
				}
		   }
	   }
	   catch (Exception e){
		   logger.error("Error recuperando namespace y esquema del fichero imsmanifest en la ruta: <" + rutaManifest+"> - ",e);
	   }
   
	   logger.info("Tipo del ODE es: "+ tipoOde);
	   this.setTipo(tipoOde);
	   
   }

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}
    
////////////////////////////////
public boolean esRCP (String rutaManifest, String tipoOde) {
	boolean rcp=true;
	//solo scorm12 e imscp	
	try {
	if ((tipoOde!=null) && (!tipoOde.equals(""))) {
		Document doc = null;
		if ((tipoOde.equals(ConstantesAgrega.SCORM_12)) || (tipoOde.equals(ConstantesAgrega.IMS_CP))){
			File fichManifest = new File (rutaManifest);
			SAXBuilder builder = new SAXBuilder(false);
    		doc = builder.build(fichManifest);
    		Element manifest = doc.getRootElement();
    		Namespace nameSpace = manifest.getNamespace();
    		Element organizations=manifest.getChild("organizations", nameSpace);
    		if (organizations!=null) {
    			Element organization=organizations.getChild("organization", nameSpace);
    			if (organization!=null) {
    				rcp=false;
    			}
    		}
		}
		
	}
	}catch (Exception e) {
		logger.error("Se ha producido un error " + e);
	}
	return rcp;
}

////////////////////////////////


}
