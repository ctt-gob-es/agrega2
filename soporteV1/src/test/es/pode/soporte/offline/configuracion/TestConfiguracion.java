package es.pode.soporte.offline.configuracion;

import junit.framework.TestCase;

public class TestConfiguracion extends TestCase {

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestConfiguracion.class);
	
	private final String uuid = "test_user_id";
//	private final String mec = "test_user_mec";
//	private final String usuario = "test_user_usuario";

	public void testConfiguracion() {

		//TODO esto se puede hacer?	es.pode.soporte.offline.configuracion.ConfiguracionDao configuracionDao = new ConfiguracionDao();
		es.pode.soporte.offline.configuracion.ConfiguracionDao configuracionDao = ConfiguracionDao.instance();
		try {
			// Creamos una prop de prueba
			logger.debug("Creamos una propety");
			configuracionDao.setProperty(configuracionDao.USER, uuid);
			configuracionDao.save();

			// Comprobamos que exista
			assertTrue(configuracionDao.getProperty(configuracionDao.USER).equalsIgnoreCase(this.uuid));
			
			logger.debug("Borramos localizador np");
			// lo borramos
			configuracionDao.removeProperty(configuracionDao.USER);
			
			// Comprobamos que no exista
			assertNull(configuracionDao.getProperty(configuracionDao.USER));			
			configuracionDao.save();
			
		} catch (Exception e) {
			logger.error("Excepcion: ",e);
			fail();			
		}
	}

}
