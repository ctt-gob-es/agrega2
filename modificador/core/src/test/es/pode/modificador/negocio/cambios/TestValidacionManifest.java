package es.pode.modificador.negocio.cambios;

import junit.framework.TestCase;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import es.pode.soporte.utiles.ficheros.UtilesFicheros;

public class TestValidacionManifest extends TestCase {
	
	private static Logger logger = Logger.getLogger(TestValidacionManifest.class);
	private static final String TEST_FOLDER = "tests";
	private static final String TEST_ODE1= "/odes/folder/es_20070518_3_0041101";
	private static final String TEST_ODE_ERRONEO= "/odes/es_20070727_2_0130101_erroneo";
	
	public TestValidacionManifest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// Creo una carpeta para los tests
		java.io.File test = new java.io.File(TEST_FOLDER);
		if(!test.exists()) {
			if(!test.mkdirs()) fail("No se ha podido crear la carpeta de test " + test.getPath());
		}
		// Copio el ode de tests a la carpeta de tests
		copiarTestOde(test, TEST_ODE1);
		copiarTestOde(test, TEST_ODE_ERRONEO);
	}

	private void copiarTestOde(java.io.File test, String odePath) throws Exception {
		java.io.File destino = new java.io.File(test,odePath);
		if(!destino.mkdirs()) fail("No se ha podido copiar el ode de test a la carpeta de test");
		java.net.URL ode = this.getClass().getResource(odePath);
		if(ode==null) fail("No se ha encontrado el ode de test " + odePath);
		UtilesFicheros.copiar(new java.io.File(ode.getFile()), destino);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		java.io.File test = new java.io.File(TEST_FOLDER);
		if(test.exists()) UtilesFicheros.eliminar(test);
	}

	public void testejecutar() {
		ValidacionManifest validacion = new ValidacionManifest();
		try {
			Layout layout = new PatternLayout("%p - %m%n");
			FileAppender app = new FileAppender(layout,TEST_FOLDER+"/report.log");

			app.setThreshold(Level.INFO);
			app.setImmediateFlush(true);
			app.setAppend(false);
			java.io.File folder = new java.io.File(TEST_FOLDER + TEST_ODE1);
			if(!folder.isDirectory()) fail("No existe la carpeta del ODE");
			boolean resultado = validacion.ejecutar(null, app, folder.getCanonicalPath());
			assertTrue("Validacion erronea", resultado);
			
			FileAppender app2 = new FileAppender(layout,TEST_FOLDER+"/report_error.log");

			app2.setThreshold(Level.INFO);
			app2.setImmediateFlush(true);
			app2.setAppend(false);
			folder = new java.io.File(TEST_FOLDER + TEST_ODE_ERRONEO);
			if(!folder.isDirectory()) fail("No existe la carpeta del ODE");
			resultado = validacion.ejecutar(null, app2, folder.getCanonicalPath());
			assertFalse("Validacion erronea", resultado);
		} catch (Exception e) {
			logger.error(e);
			fail("Error inesperado");
		}
	}
}
