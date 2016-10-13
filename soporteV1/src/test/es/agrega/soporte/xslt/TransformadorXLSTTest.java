package es.agrega.soporte.xslt;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TransformadorXLSTTest extends TestCase {
	private static Log logger = LogFactory.getLog(TransformadorXLSTTest.class);
	private static Properties props = null;

	private static String carpetaXsltTest="";
	private static String resultado="";
	
	public TransformadorXLSTTest(String arg0) {
		super(arg0);
		// Cargar propiedades del test
		if (props == null) {
			props = new Properties();
			
			InputStream is = this.getClass().getResourceAsStream("/test.properties");
			if (is == null) {
				fail("No se han podido cargar las propiedades del test");
			} else {
				try {
					props.load(is);
					carpetaXsltTest = props.getProperty("xslt.test.folder");
					logger.info("Carpeta de test recuperada : " + carpetaXsltTest);
					resultado= carpetaXsltTest + props.getProperty("xslt.test.resultado");
					logger.info("fichero resultado recuperado : " + resultado);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		// Crear carpeta de tests:
		if(carpetaXsltTest == null ) fail("No se han cargado las propiedades del test");
		java.io.File carpeta = new java.io.File(carpetaXsltTest);
		logger.debug("Creando carpetas de tests en : " + carpeta.getCanonicalPath());
		if (!carpeta.exists()) {
			boolean resultado = carpeta.mkdirs();
			if (!resultado)
				fail("No se pudo crear la carpeta de test");
		}
		
		File fileResultado = new File( resultado);
		if(fileResultado.exists())
			fileResultado.delete();
		
		
		// Copia los test-resources a la carpeta de test creada
//		java.net.URL url = this.getClass().getResource("/carpeta-test");
//		if (url == null)
//			fail("No se encuentran los recursos de src/test-resources");
//		String path = url.getPath();
//		UtilesFicheros.copiar(new java.io.File(path), carpeta);
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		File fileResultado = new File( resultado);
		if(fileResultado.exists())
			fileResultado.delete();

	}

	
	public void testTransformarVacio() throws Exception {
		Transformador tr= new TransformadorSaxonImpl();
		
		boolean resultado= tr.transformar("", "", "");
		
		assertFalse(resultado);
		logger.debug("test completado!!! ");
	}
	
	public void testTransformarSimple() throws Exception {
		String origen= carpetaXsltTest + props.getProperty("xslt.test.origen");
		String xslt= carpetaXsltTest + props.getProperty("xslt.test.xslt");
		
		Transformador tr= new TransformadorSaxonImpl();
		boolean res= tr.transformar(xslt, origen, resultado);
		
		assertTrue(res);
		
		File resultadoFile= new File(resultado);
		assertTrue(resultadoFile.exists());
		logger.debug("test completado!!! el fichero existe");
		
	}
	
	
	
}
