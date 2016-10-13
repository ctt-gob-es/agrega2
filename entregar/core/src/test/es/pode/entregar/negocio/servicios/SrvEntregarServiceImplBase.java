// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.entregar.negocio.servicios;

import org.apache.log4j.Logger;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Clase base para los test del los servicios. 
 *
 * @see AbstractTransactionalDataSourceSpringContextTests
 *
 * @see es.pode.entregar.negocio.servicios.SrvEntregarService
 */
public class SrvEntregarServiceImplBase
    extends AbstractDependencyInjectionSpringContextTests
{

protected static Logger logger = Logger.getLogger(SrvEntregarServiceImplBase.class);
    
   /** Aqui se deben de inyectar los beans a utilizar dentro del test
    *  Inyeccion del bean del Servicio es.pode.entregar.negocio.servicios.SrvEntregarService
    */
    
    protected es.pode.entregar.negocio.servicios.SrvEntregarService servicio;
    
  	public void setServicio(es.pode.entregar.negocio.servicios.SrvEntregarService servicio) {
		this.servicio = servicio;
	}
	/**
	 * Nombre de archivo que contiene el dataset de pruebas
	 */
	private String datasetFile = "SrvDataEmptyDataSet.xls";
	IDatabaseConnection connection = null;
	
    
    protected String[] getConfigLocations() {
        return new String[] { "testContext_entregar.xml" ,"applicationContext-external_entregar.xml"};

    }
}