/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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


