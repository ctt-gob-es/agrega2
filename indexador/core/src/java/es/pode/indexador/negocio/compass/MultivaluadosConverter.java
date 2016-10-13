/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.compass;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.compass.core.Resource;
import org.compass.core.accessor.Getter;
import org.compass.core.accessor.Setter;
import org.compass.core.converter.ConversionException;
import org.compass.core.mapping.Mapping;
import org.compass.core.mapping.osem.ClassMapping;
import org.compass.core.mapping.osem.ClassPropertyMapping;
import org.compass.core.mapping.osem.ClassPropertyMetaDataMapping;
import org.compass.core.marshall.MarshallingContext;
import org.compass.core.spi.InternalResource;

public class MultivaluadosConverter extends org.compass.core.converter.mapping.osem.ClassMappingConverter {
	private static Logger logger = Logger.getLogger(MultivaluadosConverter.class);
	
	public static Properties props = null;
	private static String multivaluados=null;
	private static String separadorNombreCamposMulti=null;
	private static String separadorMultiUnmarshall=null;
	private static String separadorMultiMarshall=null;
	public MultivaluadosConverter(){
		super();
		if(props==null){
			try {
				Properties properties = new Properties();
				properties.load(this.getClass().getResourceAsStream(
						"/indexador.properties"));
				multivaluados = properties.getProperty("campos_multivaluados");
				separadorNombreCamposMulti = properties.getProperty("separadorNombreCamposMulti");
				separadorMultiMarshall = properties.getProperty("separadorCamposMultivaluadosMarshall");
				separadorMultiUnmarshall = properties.getProperty("separadorCamposMultivaluados");
				props = properties;
			} catch (IOException e) {
				logger.error("ERROR: fichero no encontrado: indexador.properties",
						e);
				throw new RuntimeException(e);
			}
		}
	}
	
	
	public boolean marshall(Resource resource, Object root, Mapping mapping, MarshallingContext context) throws ConversionException {
		logger.debug("***************   MARSHALL   ***************");
		if (logger.isDebugEnabled())logger.debug("Resource["+resource+"] root["+root+"] mapping["+mapping+"] context["+context+"]");
    	ClassMapping cm = (ClassMapping) mapping;
    	for (Iterator it = cm.mappingsIt();it.hasNext();){
    		ClassPropertyMapping cpMapping =  (ClassPropertyMapping) it.next();
    		if (logger.isDebugEnabled())logger.debug("Mapeando objeto["+cpMapping.getName()+"] del raiz.");
    		Getter getter = cpMapping.getGetter();
    		Object value = getter.get(root);
    		if (logger.isDebugEnabled())logger.debug("Mapeando objeto["+cpMapping.getName()+"] del raiz con valor["+value+"].");
    		String nameProperty = cpMapping.getName();
    		ArrayList l = null;
    		if (logger.isDebugEnabled())logger.debug("Contiene la lista de multivaluados este campo?["+multivaluados.contains(separadorNombreCamposMulti+nameProperty+separadorNombreCamposMulti)+"].");
    		if(multivaluados.contains(separadorNombreCamposMulti+nameProperty+separadorNombreCamposMulti)){
    			String sValue = (String) value;
    			if (logger.isInfoEnabled())logger.info("Campo multivaluado[" + nameProperty+"] valor["+sValue+"]");
    			String[] aValues = (sValue==null?new String[0]:sValue.split(separadorMultiMarshall));
    			Object valor = null;
    			for(int i =0; i< aValues.length; i++){
    				valor = aValues[i];
//    				logger.info("Marshall añadimos valor (" + valor + ") para -->" + nameProperty);
    				cpMapping.getConverter().marshall(resource, valor, cpMapping, context);
    			}
    			
    		}else{
//    			logger.info("Marshall añadimos valor (" + value + ") para -->" + nameProperty);
    			if (logger.isDebugEnabled())logger.debug("No esta contenido en la lista de multivaluados, marshalleamos por defecto con resource["+resource+"] value["+value+"] cpMapping["+cpMapping+" context["+context+"]].");
    			cpMapping.getConverter().marshall(resource, value, cpMapping, context);
    			}
    	}
    	if (logger.isDebugEnabled())logger.debug("Terminamos el mapeo de Resource["+resource+"] root["+root+"] mapping["+mapping+"] context["+context+"] con ["+cm.mappingsSize()+"]campos");    	
    	((InternalResource) resource).addUID();
		
    	logger.debug("******************FIN MARSHALL**********************");
		return true;
	}

	public Object unmarshall(Resource resource, Mapping mapping, MarshallingContext context) throws ConversionException {
    	   	
    	ClassMapping cm = (ClassMapping) mapping;
    	
    	Object resultado = null;
		try {
			resultado = cm.getConstructor().newInstance();
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}

    	for (Iterator it = cm.mappingsIt();it.hasNext();){
    		ClassPropertyMapping cpMapping =  (ClassPropertyMapping) it.next();
    		Setter setter = cpMapping.getSetter();
    		String nameProperty = cpMapping.getName();
    		Object value= null;
    		if(multivaluados.contains(separadorNombreCamposMulti+nameProperty+separadorNombreCamposMulti)){
    			String[] valores = resource.getValues(nameProperty);
    			StringBuilder sValores=new StringBuilder("");
    			for(int i = 0; i< valores.length;i++){
    				sValores.append(valores[i] +separadorMultiUnmarshall);
    			}
    			value = sValores.toString();
    		}else{
    			ClassPropertyMetaDataMapping cpmMapping = (ClassPropertyMetaDataMapping) cpMapping.getMapping(nameProperty);
    			value = cpmMapping.getConverter().unmarshall(resource,cpmMapping,context);
    		}
    		if (logger.isInfoEnabled())logger.info("Unmarshall valor ("+value+") para campo " + setter.getName());
    		setter.set(resultado,value);
    	}
    	
    	return resultado;
	}

}
