// license-header java merge-point

package es.pode.soporte.auditoria.registrar;

import java.beans.PropertyDescriptor;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanDescripcion 
{
	private static Logger logger = Logger.getLogger(BeanDescripcion.class);
	
	/** 
	 * M�todo para recuperar informaci�n de un objeto a trav�s de reflexi�n 
	 *   
	 * @param objeto Objeto al cual se le realiza la reflexi�n 
	 * @param atributo Valor que se recupera del objeto
	 * @return valor Se devuelve el valor del atributo buscado
	 */
    public static String describe (Object objeto, String atributo) {
    	
        if(objeto == null)
            return null;

    	Object valor = null;
    	        
        Class clase = objeto.getClass();
        if(clase.isArray() || java.util.Collection.class.isAssignableFrom(clase)) 
            logger.warn("El atributo es un array y deber�a ser un String");
        else {
        	if(logger.isDebugEnabled())logger.debug("Reflexi�n del objeto: " + objeto);
        	
            BeanWrapper wrapper = new BeanWrapperImpl(objeto);
            PropertyDescriptor descriptors[] = wrapper.getPropertyDescriptors();
                        
            for(int i = 0; i < descriptors.length; i++) {
                PropertyDescriptor pd = descriptors[i];
                          
                if(pd.getReadMethod() != null && pd.getWriteMethod() != null) {
                    String name = pd.getName();
                
                	/* Capturamos el valor del atributo que nos interesa */
                    if (name.equals(atributo)) {
                    	if(logger.isDebugEnabled())logger.debug("Nombre atributo: " + name);
                    	valor = wrapper.getPropertyValue(name);
                    	
                    	/* Si el valor es nulo registramos un "" */
                    	if(valor == null) {
                    		if(logger.isDebugEnabled())logger.debug("El valor del atributo interceptado es nulo");
                    		return null;
                    	}
						return valor.toString();
                    }                   	
                }
            }
        }
        
        return null;
    }

}
