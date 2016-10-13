/**
 * 
 */
package es.pode.parseadorXML;

import java.awt.image.SampleModel;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import org.xml.sax.InputSource;

import es.pode.parseadorXML.rest.Ode;
import es.pode.parseadorXML.rest.Odes;
import es.pode.parseadorXML.rest.RSP;
import es.pode.parseadorXML.rest.TaxonPath;

import junit.framework.TestCase;

/**
 * @author fjespino
 *
 */
public class RestDaoTest extends TestCase {

	
	private URL restSample = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		restSample = this.getClass().getResource("/restSample.xml");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link es.pode.parseadorXML.RestDao#leerRespuestaRest(org.xml.sax.InputSource)}.
	 */
	public void testLeerRespuestaRest() {
		try {
			RestDao dao = new RestDao();
			InputSource in = new InputSource(restSample.openStream());
			RSP rsp = dao.leerRespuestaRest(in);
			assertNotNull(rsp);
			assertNotNull(rsp.getOdes());
			assertNotNull(rsp.getOdes().getOdes());
			assertEquals(2, rsp.getOdes().getOdes().length);
			assertNotNull(rsp.getOdes().getOdes()[0].getTaxonPath());
			assertEquals(2,rsp.getOdes().getOdes()[0].getTaxonPath().length);
			assertEquals(RSP.STAT_OK, rsp.getStat());
			assertEquals(new Integer(1), rsp.getOdes().getPage());
			assertEquals(new Integer(1), rsp.getOdes().getPages());
			assertEquals(new Integer(2), rsp.getOdes().getTotal());
			assertEquals("ode1", rsp.getOdes().getOdes()[0].getIdentifier());
			assertEquals(Short.valueOf("3"), rsp.getOdes().getOdes()[0].getAgregationLevel());
			assertEquals(Float.valueOf("2.5"), rsp.getOdes().getOdes()[0].getEvaluation());
			assertEquals("Título de un ODE: La historia de España", rsp.getOdes().getOdes()[0].getTitle());
			assertEquals("http://pruebas.agrega.indra.es/agrega/ODE/es/ode1", rsp.getOdes().getOdes()[0].getUrl());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link es.pode.parseadorXML.RestDao#escribirRespuestaRest(es.pode.parseadorXML.rest.RSP, java.io.Writer)}.
	 */
	public void testEscribirRespuestaRestError() {
		try {
			StringWriter writer = new StringWriter();
			RestDao dao = new RestDao();
			dao.escribirRespuestaRest(new RSP(Short.valueOf("0"),"Unexpected error"), writer);
			System.out.println("Resultado REST-XML\n\n" + writer.toString());
			
			// Usamos leerRespuestaRest para hacer asserts
			InputSource is = new InputSource(new StringReader(writer.toString()));
			RSP response = dao.leerRespuestaRest(is);
			assertNotNull(response);
			assertNull(response.getOdes());
			assertEquals(RSP.STAT_ERROR, response.getStat());
			assertEquals(Short.valueOf("0"), response.getErrorCode());
			assertEquals("Unexpected error", response.getErrorMsg());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link es.pode.parseadorXML.RestDao#escribirRespuestaRest(es.pode.parseadorXML.rest.RSP, java.io.Writer)}.
	 */
	public void testEscribirRespuestaRestOK() {
		try {
			StringWriter writer = new StringWriter();
			RestDao dao = new RestDao();
			RSP test = new RSP();
			test.setStat(RSP.STAT_OK);
			test.setOdes(new Odes());
			test.getOdes().setPage(1);
			test.getOdes().setPages(1);			
			test.getOdes().setTotal(2);
			test.getOdes().setOdes(new Ode[]{new Ode(), new Ode()});
			test.getOdes().getOdes()[0].setIdentifier("ode1");
			test.getOdes().getOdes()[0].setUrl("http://www.google.es");
			test.getOdes().getOdes()[0].setUrlPreview("http://www.google.es");
			test.getOdes().getOdes()[0].setTitle("Título de un ODE: La historia de España");
			test.getOdes().getOdes()[0].setAgregationLevel(Short.valueOf("3"));
			test.getOdes().getOdes()[0].setEvaluation(3.5f);
			test.getOdes().getOdes()[1].setIdentifier("ode2");
			test.getOdes().getOdes()[1].setUrl("http://www.google.es");
			test.getOdes().getOdes()[1].setUrlPreview("http://www.google.es");
			test.getOdes().getOdes()[1].setTitle("Título de un ODE: La historia de España con taxones");
			test.getOdes().getOdes()[1].setTaxonPath(new TaxonPath[]{new TaxonPath(), new TaxonPath()});
			test.getOdes().getOdes()[1].getTaxonPath()[0].setTaxonId("1.1");
			test.getOdes().getOdes()[1].getTaxonPath()[0].setDescription("Educacion Primaria");
			test.getOdes().getOdes()[1].getTaxonPath()[1].setTaxonId("1.2");
			test.getOdes().getOdes()[1].getTaxonPath()[1].setDescription("No existo");
			
			dao.escribirRespuestaRest(test, writer);
			System.out.println("Resultado REST-XML\n\n" + writer.toString());
			
			// Usamos leerRespuestaRest para hacer asserts
			InputSource is = new InputSource(new StringReader(writer.toString()));
			RSP response = dao.leerRespuestaRest(is);
			assertNotNull(response);
			assertNotNull(response.getOdes());
			assertNotNull(response.getOdes().getOdes());
			assertEquals(2, response.getOdes().getOdes().length);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
}
