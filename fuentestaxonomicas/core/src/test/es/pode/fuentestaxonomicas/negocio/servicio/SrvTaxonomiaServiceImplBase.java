// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.fuentestaxonomicas.negocio.servicio;

import org.apache.log4j.Logger;
import org.dbunit.database.IDatabaseConnection;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Clase base para los test del los servicios. 
 *
 * @see AbstractTransactionalDataSourceSpringContextTests
 *
 * @see es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService
 */
public class SrvTaxonomiaServiceImplBase
    extends AbstractDependencyInjectionSpringContextTests
{

protected static Logger logger = Logger.getLogger(SrvTaxonomiaServiceImplBase.class);
    
   /** Aqui se deben de inyectar los beans a utilizar dentro del test
    *  Inyeccion del bean del Servicio es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService
    */
    
    protected es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService servicio;
	public void setServicio(es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService servicio) {
		this.servicio = servicio;
	}
	/**
	 * Nombre de archivo que contiene el dataset de pruebas
	 */
	private String datasetFile = "SrvDataEmptyDataSet.xls";
	IDatabaseConnection connection = null;
	
  
    
    protected String[] getConfigLocations() {
        return new String[] { "testContext.xml" };
    }

}
