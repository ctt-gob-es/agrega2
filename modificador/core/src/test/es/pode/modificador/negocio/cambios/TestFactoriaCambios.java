package es.pode.modificador.negocio.cambios;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pode.modificador.negocio.cambios.configuracion.ConfiguracionDao;
import es.pode.modificador.negocio.cambios.configuracion.castor.ModificationTask;
import es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

import net.sf.dozer.util.mapping.MapperIF;
import junit.framework.TestCase;

public class TestFactoriaCambios extends TestCase {
	private static final String TEST_XML = "/xmls/test-configuracion-odes.xml";
	private static final String TEST_XML2 = "/xmls/test-configuracion-tareas.xml";
	private MapperIF mapper = null;
	private static ClassPathXmlApplicationContext context = null;
	private ConfiguracionDao dao = null;
	private FactoriaCambios factoria = null;
	
	public TestFactoriaCambios() {
		super();
		if(context==null) {
			try {
			context = new ClassPathXmlApplicationContext(new String[]{"testContext.xml","test-user-applicationContext.xml"});
			} catch (Exception e) {
				Logger.getLogger(this.getClass()).error("Excepcion en la inicialización de Spring",e);
			}
		}
		if(mapper==null) {
			mapper = (MapperIF)context.getBean("beanMapper");
		}
		if(dao==null) {
			dao = (ConfiguracionDao)context.getBean("configuracionDao");
		}
		if(factoria==null) {
			factoria = (FactoriaCambios)context.getBean("factoriaCambios");
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		java.net.URL carpetaOdes = this.getClass().getResource("/odes");
		if(carpetaOdes==null) {
			fail("No se encuentra la carpeta de odes");
		}
		java.io.File carpetaTest=new java.io.File("odes");
		if(!carpetaTest.isDirectory()) assertTrue(carpetaTest.mkdirs());
		UtilesFicheros.copiar(new java.io.File(carpetaOdes.getPath()),carpetaTest);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		java.io.File carpetaTest=new java.io.File("odes");
		if(carpetaTest.exists()) UtilesFicheros.eliminar(carpetaTest);
	}

	public void testGenerarTareaCambios() 
	{
		InputStream is = this.getClass().getResourceAsStream(TEST_XML2);
		if(is== null) {
			fail("No se ha podido leer el xml de configuracion de tareas " + TEST_XML2);
		}
		ModificationTask task=null;
		try {
			task = dao.leerConfiguracion(is);
			ConfiguracionTarea vo = (ConfiguracionTarea)mapper.map(task, ConfiguracionTarea.class);
			factoria.generarTareaCambios(vo, "tareaTest/");
			assertNotNull("El resultado de obtenerODEs ha sido null",vo);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Fallo en la lectura de " + TEST_XML);
		}
		
	}

	public void testObtenerODEs() {
		// Leo la configuracion de tareas para el test
		InputStream is = this.getClass().getResourceAsStream(TEST_XML);
		if(is== null) {
			fail("No se ha podido leer el xml de configuracion de tareas " + TEST_XML);
		}
		ModificationTask task=null;
		try {
			task = dao.leerConfiguracion(is);
			ConfiguracionTarea vo = (ConfiguracionTarea)mapper.map(task, ConfiguracionTarea.class);
			ODE[] odes = factoria.obtenerODEs(vo.getObjetos(), "tareaTest/", false);
			assertNotNull("El resultado de obtenerODEs ha sido null",odes);
			assertEquals("El numero de odes no es el esperado",4, odes.length);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Fallo en la lectura de " + TEST_XML);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				// No hacer nada
			}
		}
		
	}

}
