// license-header java merge-point

package es.pode.soporte.auditoria.registrar;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/** 
 * Clase que se encarga de la intercepción de los servicios web, métodos y argumentos que nos interesa registrar
 * 
 * Inicialmente sólo se trata el escenario clase servidor web compuesta de uno o varios métodos 
 * y estos métodos están compuestos por uno o varios Objetos VO (argumentos) 
 * y estos Objetos tienen uno o varios atributos que son los que se extrae su valor. 
 * Los atributos que registramos deben ser de tipo String.    
 * 
 * @author jsimon
 */

public class ServiceRegistrarInterceptor implements MethodInterceptor 
{

	private static Logger log = Logger.getLogger(ServiceRegistrarInterceptor.class);
	static java.util.Properties pSpringProperties = null;
	static final String AUDITORIA = ServiceRegistrarInterceptor.getPropertyValue("auditoria");
	
	/** 
	 * Método de invocación que se ejecuta cuando se llama a un servicio web 
	 * 
	 * @param invocation Información de la clase que se está ejecutando y que se va a interceptar
	 * @return returnObject Objeto Devuelta: Joinpoint
	 */
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
    	if (RegistroCtes.AUDITORIA_NO.equals(AUDITORIA)) {
    		log.info("!!!!! Opción sin auditoría. No se capturan datos");
    		return invocation.proceed();
    	}
    
    	try {
    		/* Se interceptan los datos */
    		DatosVO datosInterceptados = Interceptor.getDatosInterceptados(invocation);
    	
	    	if (datosInterceptados != null) {
	    		HashMap valores = datosInterceptados.getValores();  
	    	
	    		if (valores != null && valores.size() > 0)     			 
	    			Registrar.operacion(datosInterceptados);
	    		else { 
	    			if (log.isDebugEnabled())log.debug("No se han interceptados valores");    		
	    		}
	    	}
    	}
	    catch (Throwable t) {
	    	log.error("Error: No se han interceptados los valores"); 
	    	log.error(t);
	    }
    	
    	Object returnObject = invocation.proceed();   
            	
        return returnObject;         
    }
  
	/** 
	 * Recuperado de un valor de un fichero de properties 
	 *   
	 * @param sKey El nombre a buscar en el fichero 
	 * @return sReturn El valor buscado
	 */
	private static String getPropertyValue(String sKey) 
	{
		String sReturn = null;
		try 
		{
			if (pSpringProperties == null)
			{
				InputStream fIsSpringProperties = ServiceRegistrarInterceptor.class.getResourceAsStream(RegistroCtes.FILE_NAME_PROPERTIES);
				pSpringProperties = new java.util.Properties();
				pSpringProperties.load(fIsSpringProperties);
			}
			sReturn = pSpringProperties.getProperty(sKey); 
		} 
		catch (IOException e) {
			log.error(e);
		}
		
		return sReturn;
	}
}