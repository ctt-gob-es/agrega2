/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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