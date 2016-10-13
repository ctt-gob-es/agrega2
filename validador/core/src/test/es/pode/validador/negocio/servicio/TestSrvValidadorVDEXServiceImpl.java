// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.validador.negocio.servicio;

import org.apache.log4j.Logger;


/**
 * @see SrvValidadorVDEXServiceImpl
 */
public class TestSrvValidadorVDEXServiceImpl
    extends SrvValidadorVDEXServiceImplBase
{

	private static Logger logger = Logger.getLogger(TestSrvValidadorVDEXServiceImpl.class);
    /**
     * Constructor
     */
     public TestSrvValidadorVDEXServiceImpl(){
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
     */	
     public void testInicial(){

	}
	
	
    /**
     *   TEST obtenerValidacionTesauro
     *   
     */
     public void testObtenerValidacionTesauro01() 
     throws Exception 
     {
 		String tesauroBad="/xmls/tesauros/prueba_ok_es.xml";
	   	 java.net.URL fichero=null;
	   	 try 
	   	 {		
	   		 fichero = this.getClass().getResource(tesauroBad);
	   	 } 
	   	 catch (Exception e1)  
	   	 {
			logger.error(e1);
	   	 }

	   	 SrvValidadorVDEXService servicio= this.servicio;
		ValidaVdexVO valida= new ValidaVdexVO();
		valida= servicio.obtenerValidacionTesauro(fichero.getPath());

		assertEquals( valida.getValido().booleanValue(), true);
    }

     public void testObtenerValidadorTesauro02() 
     throws Exception 
     {
  		String tesauroBad="/xmls/tesauros/prueba_bad_es.xml";
	   	 java.net.URL fichero=null;
	   	 try 
	   	 {		
	   		fichero = this.getClass().getResource(tesauroBad);
	   	 } 
	   	 catch (Exception e1)  
	   	 {

	   		 logger.error(e1);
	   	 }

		SrvValidadorVDEXService servicio= this.servicio;
		ValidaVdexVO valida= new ValidaVdexVO();
		valida= servicio.obtenerValidacionTesauro(fichero.getPath());

		assertEquals( valida.getValido().booleanValue(), false);
		
	}
	
	public void testObtenerValidacionTaxonomias01()
	throws Exception 
	{
  		String taxonomia="/xmls/taxonomias/prueba_ok_es.xml";
	   	 java.net.URL fichero=null;
	   	 try 
	   	 {		
	   		fichero = this.getClass().getResource(taxonomia);
	   	 } 
	   	 catch (Exception e1)  
	   	 {

	   		 logger.error(e1);
	   	 }
		
		SrvValidadorVDEXService servicio= this.servicio;
		ValidaVdexVO valida= new ValidaVdexVO();
		valida= servicio.obtenerValidacionTaxonomia(fichero.getPath());
		
		assertEquals( valida.getValido().booleanValue(), true);		
	}
	
	public void testObtenerValidacionTaxonomias02()
	throws Exception 
	{
  		String taxonomia="/xmls/taxonomias/prueba_bad_es.xml";
	   	 java.net.URL fichero=null;
	   	 try 
	   	 {		
	   		fichero = this.getClass().getResource(taxonomia);
	   	 } 
	   	 catch (Exception e1)  
	   	 {

	   		 logger.error(e1);
	   	 }
		SrvValidadorVDEXService servicio= this.servicio;
		ValidaVdexVO valida= new ValidaVdexVO();
		valida= servicio.obtenerValidacionTaxonomia(fichero.getPath());
		
		assertEquals( valida.getValido().booleanValue(), false);		
	}
}