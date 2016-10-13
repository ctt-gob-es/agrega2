// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.contenidos.negocio.faqs.servicio;

import java.util.Date;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;




/**
 * @see SrvFaqServiceImpl TODO: To execute test on services that invoke
 *      webservices which implement security, you must include the next param to
 *      the java virtual machine: -Daxis.ClientConfigFile=client-config.wsdd
 */
public class TestSrvFaqServiceImpl extends SrvFaqServiceImplBase 
{

	
	
	
	
	
	private static final String ESPA�OL = "es";

	/**
	 * Constructor
	 */
	public TestSrvFaqServiceImpl() 
	{
		super();
	}

	/**
	 * onSetUpInTransaction
	 */

	protected void onSetUpInTransaction() throws Exception 
	{
		/**
		 * Implementar si no desea usar DataSets
		 */
		// Inicializamos la conexion a base de datos
		connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
		
		//Solucion para problema con tipo Boolean en base de datos Hypersonic
		// Esta solucion esta propuesta en el foro de I-Builder
		//DatabaseConfig config = connection.getConfig();
		
		// Inicializamos el dataset
		IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource("SrvDataEmptyDataSet.xls").getFile());
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}

	/**
	 * onTearDownInTransaction
	 */

	protected void onTearDownInTransaction() 
	{
		/**
		 * Implementar si no desea usar DataSets
		 */
		try 
		{
			// Inicializamos la conexion a base de datos
			connection = new DatabaseConnection(this.jdbcTemplate.getDataSource().getConnection());
			// Inicializamos el dataset
			IDataSet dataSet = new XlsDataSet(this.applicationContext.getResource("SrvDataEmptyDataSet.xls").getFile());
			DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
		} 
		catch (Throwable th) 
		{
			th.printStackTrace();
		}
	}

	/**
	 * testInicial TODO: Agregar las pruebas unitarias que correspondan al
	 * servicio, siguiendo un patron de nombrado como public void test<nombreTest>().
	 * En el caso de tener que el metodo ${operation.name} acceda a un
	 * webservice el cu�l implementa seguridad (se necesita la autenticaci�n de
	 * usuario), antes de invocarlo en el test, se debe establecer un usuario
	 * con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testInicial() 
	{
		String prueba = null;
		this.initAthenticationUser("usuario", "ibuilder");
		assertNull(prueba);
	}

	/**
	 * TEST obtenerFaqs TODO: Implementar el test para el metodo obtenerFaqs del
	 * servicio SrvFaqService. En el caso de tener que el metodo obtenerFaqs
	 * acceda a un webservice el cu�l implementa seguridad (se necesita la
	 * autenticaci�n de usuario), antes de invocarlo en el test, se debe
	 * establecer un usuario con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testobtenerFaqs() 
	{
		FaqVO[] faqVOs = servicio.obtenerFaqs();
		assertNotNull("La lista no es nula", faqVOs);
		assertEquals("Se obtienen 5 resultados", faqVOs.length, 5);
		assertEquals("El primer resultado es la ID 3", 3, faqVOs[0].getId().longValue());
	}

	/**
	 * TEST crearFaq TODO: Implementar el test para el metodo crearFaq del
	 * servicio SrvFaqService. En el caso de tener que el metodo crearFaq acceda
	 * a un webservice el cu�l implementa seguridad (se necesita la
	 * autenticaci�n de usuario), antes de invocarlo en el test, se debe
	 * establecer un usuario con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testcrearFaq() 
	{
		FaqVO faqVO = new FaqVO();
		
		DescripcionFaqVO[] descripcionFaq = new DescripcionFaqVO[1];
		descripcionFaq[0] = new DescripcionFaqVO();
		descripcionFaq[0].setPregunta("Pregunta de test3");
		descripcionFaq[0].setRespuesta("Respuesta a la pregunta de test3");
		descripcionFaq[0].setPosicion(2);
		descripcionFaq[0].setIdioma(ESPA�OL);
		//creamos para la ocasi�n una nueva categoria
		CategoriaFaqVO categoria = new CategoriaFaqVO();
		categoria.setNombre("CategoriaFaqTest");
		categoria.setId(new Long(1));
		CategoriaIdiomaFaqVO[] categoriaIdioma = new CategoriaIdiomaFaqVO[1];
		categoriaIdioma[0] = new CategoriaIdiomaFaqVO();
		categoriaIdioma[0].setNombre("CategoriaFaqTest");
		categoriaIdioma[0].setIdioma(ESPA�OL);
		categoriaIdioma[0].setId(Long.valueOf(1));
		categoria.setCategoriaIdioma(categoriaIdioma);
		
		//this.servicio.crearCategoria(categoria);

		faqVO.setCategoria(categoria);
		faqVO.setDescripcionFaq(descripcionFaq);
		faqVO.setFechaBaja(new Date());
		faqVO.setFechaModificacion(new Date());
		faqVO.setFechaPublicacion(new Date());
		
		try {
			FaqVO faqresult = this.servicio.crearFaq(faqVO);	
			FaqVO[] faqs = this.servicio.obtenerFaqs();
			System.out.print("hola");
			
			Long id = Long.valueOf(8);
			faqresult = this.servicio.consultaFaq(id);
			faqresult.setFechaBaja(null);
			faqresult = this.servicio.modificarFaq(faqresult);
			faqs = this.servicio.obtenerFaqs();
			System.out.print("hola");
			
//			boolean encontrado =false;
//			for (int i =0;i<faqs.length&&!encontrado;i++)
//			{
//				encontrado=faqs[i].getCategoriaFaq().getNombre().equalsIgnoreCase(faqresult.getCategoriaFaq().getNombre());
//			}
//			if(encontrado)				
//				assertTrue(categoria.getNombre().equalsIgnoreCase(faqresult.getCategoriaFaq().getNombre()));
//			else
//				fail();
		} catch (RuntimeException e) {			
			e.printStackTrace();
			fail();
		}
		
	}

	/**
	 * TEST modificarFaq TODO: Implementar el test para el metodo modificarFaq
	 * del servicio SrvFaqService. En el caso de tener que el metodo
	 * modificarFaq acceda a un webservice el cu�l implementa seguridad (se
	 * necesita la autenticaci�n de usuario), antes de invocarlo en el test, se
	 * debe establecer un usuario con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testmodificarFaq() 
	{
//		ModificaFaqVO faqModificado=new ModificaFaqVO();
//		FaqVO faqVO = servicio.consultaFaq(new Long(1));
//		faqModificado.setId(faqVO.getId());
//		faqModificado.setIdioma(faqVO.getIdioma());
//		faqModificado.setPosicion(faqVO.getPosicion());
//		faqModificado.setPregunta(faqVO.getPregunta());
//		faqModificado.setRespuesta(faqVO.getRespuesta() + " modificado");
//		
//		CategoriaFaqVO catFaqVO = faqVO.getCategoriaFaq();
//		catFaqVO.setNombre(catFaqVO.getNombre()+" modificado");		
//		catFaqVO.setNueva(Boolean.TRUE);
//		faqModificado.setCategoriaFaq(catFaqVO);
//		
//		System.out.println(faqModificado.getRespuesta());
//		try {
//			this.servicio.modificarFaq(faqModificado);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			fail();
//		}
//		
//		assertFalse(faqVO.equals(faqModificado));
		
	}

	/**
	 * TEST eliminarFaq TODO: Implementar el test para el metodo eliminarFaq del
	 * servicio SrvFaqService. En el caso de tener que el metodo eliminarFaq
	 * acceda a un webservice el cu�l implementa seguridad (se necesita la
	 * autenticaci�n de usuario), antes de invocarlo en el test, se debe
	 * establecer un usuario con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testeliminarFaq() 
	{
		Long[] faqsABorrar = new Long[] { Long.valueOf(1), Long.valueOf(2), Long.valueOf(3) };
		servicio.eliminarFaq(faqsABorrar[0]);
		FaqVO[] faqs = null;
		try {
			faqs = servicio.obtenerFaqs();
		} catch (RuntimeException e) {			
			e.printStackTrace();
			fail();
		}
		assertEquals(2, faqs.length);
	}

	/**
	 * TEST consultaFaq TODO: Implementar el test para el metodo consultaFaq del
	 * servicio SrvFaqService. En el caso de tener que el metodo consultaFaq
	 * acceda a un webservice el cu�l implementa seguridad (se necesita la
	 * autenticaci�n de usuario), antes de invocarlo en el test, se debe
	 * establecer un usuario con derechos de acceso utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testconsultaFaq() 
	{
//		FaqVO faqVO = servicio.consultaFaq(new Long(1));
//		assertEquals("Pregunta? ", "Pregunta 1", faqVO.getPregunta());
//		assertEquals("Pregunta? ", "Respuesta 1", faqVO.getRespuesta());
	}

	/**
	 * TEST obtenerFaqsPorIdioma TODO: Implementar el test para el metodo
	 * obtenerFaqsPorIdioma del servicio SrvFaqService. En el caso de tener que
	 * el metodo obtenerFaqsPorIdioma acceda a un webservice el cu�l implementa
	 * seguridad (se necesita la autenticaci�n de usuario), antes de invocarlo
	 * en el test, se debe establecer un usuario con derechos de acceso
	 * utilizando el m�todo
	 * {@link es.indra.servicios.SrvFaqServiceImplBase#initAthenticationUser(String, String) 
	 * initAuthenticationUser(authenticationUser, authenticationPassword)}
	 */
	public void testobtenerFaqsPorIdioma() 
	{
//		FaqVO[] faqVOs = servicio.obtenerFaqsPorIdioma("ca");
//		assertNotNull("La lista no es nula", faqVOs);
//		assertEquals("Se obtienen 1 resultado", 1, faqVOs.length);
//		assertEquals("El primer resultado es la ID 3", 3, faqVOs[0].getId().longValue());
	}

}