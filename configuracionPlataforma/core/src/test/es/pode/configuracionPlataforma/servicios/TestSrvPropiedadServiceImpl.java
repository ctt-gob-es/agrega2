// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.servicios;

/**
 * @see SrvPropiedadServiceImpl
 */
public class TestSrvPropiedadServiceImpl
    extends SrvPropiedadServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvPropiedadServiceImpl(){
		super();
	}

    /**
     * onSetUpInTransaction
     */

     protected void onSetUpInTransaction() throws Exception {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
		super.onSetUpInTransaction();
	}

    /**
     * onTearDownInTransaction
     */

	 protected void onTearDownInTransaction() {

     	/**
     	 * Implementar si no desea usar DataSets
     	 */
	 	
    	 super.onTearDownInTransaction();
     }
	

    /**
     * testInicial
     *   TODO: Agregar las pruebas unitarias que correspondan al servicio, siguiendo un patron de nombrado como
     *     public void test<nombreTest>().
     */	
     public void testInicial(){
		String prueba = null;
		assertNotNull(prueba);
	}
	
	
    /**
     *   TEST get
     *   TODO: Implementar el test para el metodo get del servicio SrvPropiedadService.
     */
     public void testget() {
		String prueba = null; 
		assertNotNull(prueba);

    }

    /**
     *   TEST set
     *   TODO: Implementar el test para el metodo set del servicio SrvPropiedadService.
     */
     public void testset() {
		String prueba = null; 
		assertNotNull(prueba);

    }

	
	
	
}