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
