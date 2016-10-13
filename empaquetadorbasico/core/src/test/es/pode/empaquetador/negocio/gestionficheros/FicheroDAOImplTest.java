/**
 * 
 */
package es.pode.empaquetador.negocio.gestionficheros;

import java.io.File;

import junit.framework.TestCase;

/**
 * @author fjespino
 *
 */
public class FicheroDAOImplTest extends TestCase {
	private FicheroDaoImpl dao = new FicheroDaoImpl();
	
	/**
	 * @param arg0
	 */
	public FicheroDAOImplTest(String arg0) {
		super(arg0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#cargar(es.pode.empaquetador.negocio.gestionficheros.Fichero)}.
	 */
	public void testCargar() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#cargar(es.pode.empaquetador.negocio.gestionficheros.Fichero)}.
	 */
	public void testCrearCarpeta() {
		String arg1 = "c:/windows/temp";
		String arg2 = "";
		try {
			dao.crearCarpeta(arg1, arg2);
		} catch (FicheroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File prueba = new File (arg1 + "/" + arg2);
		assertTrue(prueba.exists());
		
		//nota: esto crear� carpetas en c:/windows/temp 
	}

	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#almacenar(es.pode.empaquetador.negocio.gestionficheros.Fichero)}.
	 */
	public void testAlmacenar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#eliminar(es.pode.empaquetador.negocio.gestionficheros.Fichero)}.
	 */
	public void testEliminar() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#toFicheroVO()}.
	 */
	public void testToFicheroVO() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.pode.empaquetador.negocio.gestionficheros.FicheroDAOImpl#fromFicheroVO(es.pode.empaquetador.negocio.servicio.FicheroVO)}.
	 */
	public void testFromFicheroVO() {
		fail("Not yet implemented");
	}

	public FicheroDaoImpl getDao() {
		return dao;
	}

	public void setDao(FicheroDaoImpl dao) {
		this.dao = dao;
	}

}
