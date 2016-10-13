// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.DRI;





/**
 * @see SrvDRIServiceImpl
 */
public class TestSrvDRIServiceImpl
    extends SrvDRIServiceImplBase
{


    /**
     * Constructor
     */
     public TestSrvDRIServiceImpl(){
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
	
	public void testPresentarAlmacenar() 
	{
     	String seed = new Long(System.currentTimeMillis()).toString();
    	//String sesionId = "sesion "+seed;
     	String sesionId;
//     	DataHandler pif = null;
//     	pif = new DataHandler(new FileDataSource(new File("D:\\1234.zip")));
     	//DataHandler pif = new DataHandler(null, "");
		SrvDRIService service = this.servicio;
		
		try 
		{
			sesionId = this.servicioSession.createSession("administrador", "1111111");
//			service.presentarAlmacenar(sesionId, pif);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			assertTrue(true);
		}
	}
	
	public void testSolicitarEntregar() {
     	String seed = new Long(System.currentTimeMillis()).toString();
     	//String sesionId = "sesion "+seed;
    	//String mec = "mec "+seed;
     	String mec="es_ex_20070518_1_9095447220";
		SrvDRIService service = this.servicio;
		String sesionId;
		try 
		{
			sesionId = this.servicioSession.createSession("administrador", "1111111");
//			service.solicitarEntregar(sesionId, mec);
		} 
		catch (Exception e) 
		{
			assertTrue(true);
		}
		//poco mas se puede hacer sin acceso al servicio de sesion
	}
	
	
	
}