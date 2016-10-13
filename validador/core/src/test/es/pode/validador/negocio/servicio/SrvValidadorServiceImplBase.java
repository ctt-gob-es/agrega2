// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.validador.negocio.servicio;

import org.apache.log4j.Logger;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Clase base para los test del los servicios. 
 *
 * @see AbstractTransactionalDataSourceSpringContextTests
 *
 * @see es.pode.validador.negocio.servicio.SrvValidadorService
 */
public class SrvValidadorServiceImplBase
    extends AbstractDependencyInjectionSpringContextTests
{

protected static Logger logger = Logger.getLogger(SrvValidadorServiceImplBase.class);
    
   /** Aqui se deben de inyectar los beans a utilizar dentro del test
    *  Inyeccion del bean del Servicio es.pode.validador.negocio.servicio.SrvValidadorService
    */
    
    protected es.pode.validador.negocio.servicio.SrvValidadorService servicio;
	public void setServicio(es.pode.validador.negocio.servicio.SrvValidadorService servicio) {
		this.servicio = servicio;
	}
	/**
	 * Nombre de archivo que contiene el dataset de pruebas
	 */
	private String datasetFile = "SrvDataEmptyDataSet.xls";
	IDatabaseConnection connection = null;
	
   
    
      
    protected String[] getConfigLocations() {
        return new String[] { "testContext.xml","user-applicationContext.xml","applicationContext-external_validador.xml" };
    }

}