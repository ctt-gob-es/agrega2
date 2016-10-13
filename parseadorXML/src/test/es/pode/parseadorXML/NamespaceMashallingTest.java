/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.parseadorXML;

import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.castor.Manifest;

public class NamespaceMashallingTest extends TestCase {

	protected  final File folder = new File("src/test");
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void testScormAndLomMarshal()  {
		
		try {
			Manifest mf = getScormDao().parsearODEEager( new File( folder, "scorm_lom_mnf.xml") );
			
			File out = new File(folder, "scorm_lom_mnf_out.xml");
			getScormDao().escribirODE(mf, out );
			
//			Manifest mfLoaded = getScormDao().parsearODEEager( out );
		
		} catch ( Exception e ) {
			super.fail(e.getLocalizedMessage());
			logger.error(e);
		}
		
	}
	
	protected SCORM2004Dao getScormDao() {
		return null;//SpringBootstrapContextLoader.getScormDao();
	}
}
