// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.SQI;

/**
 * @see SrvSQISincronoServiceImpl
 */
public class TestSrvSQISincronoServiceImpl
    extends SrvSQISincronoServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvSQISincronoServiceImpl(){
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
		assertNull(prueba);
	}
	
	
    /**
     *   TEST synchronousQuery
     *   TODO: Implementar el test para el metodo synchronousQuery del servicio SrvSQISincronoService.
     */
     public void testsynchronousQuery() {
		String prueba = null; 
		assertNull(prueba);

    }

	
	
	
}