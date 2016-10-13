package es.pode.soporte.auditoria.registrar;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class ValidadorMECTest extends TestCase {

	public static Logger log = Logger.getLogger(ValidadorMECTest.class);

	public ValidadorMECTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testValidadorMEC() {
		
		try {
			String idODE = "es-na_20080301_1_9120034";
			boolean validado = Registrar.comprobarODE(idODE);
						
			assertTrue(validado);			
		}
		catch (Exception ex) {
			log.error("Se ha producido una excepcion en ValidadorMECTest", ex);
			ex.printStackTrace();
		}
	}
}


