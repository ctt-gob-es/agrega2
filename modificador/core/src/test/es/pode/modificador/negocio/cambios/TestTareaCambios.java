package es.pode.modificador.negocio.cambios;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import junit.framework.TestCase;
import net.sf.dozer.util.mapping.MapperIF;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pode.modificador.negocio.cambios.configuracion.ConfiguracionDao;
import es.pode.modificador.negocio.cambios.configuracion.castor.ModificationTask;
import es.pode.modificador.negocio.servicio.vo.ConfiguracionTarea;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;

public class TestTareaCambios extends TestCase {

	private MapperIF mapper = null;
	private static ClassPathXmlApplicationContext context = null;
	private ConfiguracionDao dao = null;
	private FactoriaCambios factoria = null;
	private static final String basePath = "test/modificador/";
	
	public TestTareaCambios(String name) {
		super(name);
		if(context==null) {
			context = new ClassPathXmlApplicationContext(new String[]{"testContext.xml","user-applicationContext.xml"});
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
		File test = new File(basePath);
		if(!test.mkdirs() && !test.exists()) fail("No se ha podido crear la carpeta de tests");
		File odes = new File("odes");
		if(!odes.mkdirs() && !odes.exists()) fail("No se ha podido crear la carpeta de tests");
		URL folder = this.getClass().getResource("/odes");
		if(folder==null) fail("No se ha podido leer la carpeta de odes en test-resources");
		UtilesFicheros.copiar(new File(folder.getFile()), odes);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		UtilesFicheros.eliminar(new File("odes"));
		UtilesFicheros.eliminar(new File(basePath));
	}

	public void testejecutarNoSimulada() {
		// Recupera XML configuracion
		InputStream is = this.getClass().getResourceAsStream("/xmls/test-ejecucion-tarea.xml");
		if(is==null) fail("No se pudo abrir el XML de tarea");
		try {
			ModificationTask task = dao.leerConfiguracion(is);
			ConfiguracionTarea config = (ConfiguracionTarea)mapper.map(task, ConfiguracionTarea.class);
			TareaCambios tc = factoria.generarTareaCambios(config, basePath);
			long a = System.currentTimeMillis();
			tc.ejecutarTarea();
			long b = System.currentTimeMillis();
			long c = System.currentTimeMillis();
			long time = (b-a)-(c-b);
			
			System.out.println("Tarea terminada. Tiempo = " + time/1000f);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error inesperado : " + e.getMessage());
		}
	}
	
	public void testejecutarSimulada() 
	{
//		 Recupera XML configuracion
		InputStream is = this.getClass().getResourceAsStream("/xmls/test-ejecucion-tarea.xml");
		if(is==null) fail("No se pudo abrir el XML de tarea");
		try {
			ModificationTask task = dao.leerConfiguracion(is);
			ConfiguracionTarea config = (ConfiguracionTarea)mapper.map(task, ConfiguracionTarea.class);
			TareaCambios tc = factoria.generarTareaCambios(config, basePath);
			long a = System.currentTimeMillis();
			tc.ejecutarTarea(true);
			long b = System.currentTimeMillis();
			long c = System.currentTimeMillis();
			long time = (b-a)-(c-b);
			
			System.out.println("Tarea terminada. Tiempo = " + time/1000f);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error inesperado : " + e.getMessage());
		}
	}
}
