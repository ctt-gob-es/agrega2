package es.pode.parseadorXML.scorm2004.agrega;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.ControlMode;
import es.pode.parseadorXML.castor.Sequencing;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Dependency;
import es.pode.parseadorXML.castor.Item;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.Organization;
import es.pode.parseadorXML.castor.Resource;

public class ManifestAgregaTest extends TestCase {

	private SCORM2004Dao dao = null;

	private Properties properties = null;

	private ClassPathXmlApplicationContext context = null;

	private Logger logger = Logger.getLogger(this.getClass());

	public ManifestAgregaTest(String arg0) {
		super(arg0);
		this.context = new ClassPathXmlApplicationContext(
				"parseadorXML-applicationContext.xml");
		this.dao = (SCORM2004Dao) this.context.getBean("scorm2004Dao",
				SCORM2004Dao.class);
		this.properties = new Properties();

		try {
			InputStream is = this.getClass().getResourceAsStream(
					"/parseadorXML.properties");
			properties.load(is);
		} catch (IOException e) {
			logger.error("No se pudo abrir el fichero de propiedades; ", e);
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		logger.info("Paso por setup");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testTieneSecuencia() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				
				boolean resultado = manifestAgrega.tieneSecuencias();
				assertTrue("El ode tiene secuencia : ",resultado);
				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	public void testObtenerItem() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);

				Item item = manifestAgrega
						.obtenerItem("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699");
				assertNotNull("Item distinto de null?", item);
				// Pruebo a modificarlo
				item.setTitle("Titulo modificado");
				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	public void testSetLom() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);

				Lom lomAInsertar = new Lom();
				// Insertamos un Lom vacio en tres elementos (uno con Lom
				// previo, otro con metadata sin Lom y otro sin metadata
				manifestAgrega.setLom("MANIFEST-A09C57BFD9E2313044F1D6AC99DD31C8",null, lomAInsertar);
				manifestAgrega.setLom("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699",null, lomAInsertar);
				manifestAgrega.setLom("ITEM-F7E7F37D-5427-58DC-E205-19D75FF4DA35",null, lomAInsertar);
				manifestAgrega.setLom("RES-1937E17748FA68C8270AA65D7691BB67","contenido/actividad.html", lomAInsertar);

				// Comprobamos que estos elementos tienen un Lom vacio
				/*Lom extraido = manifestAgrega.obtenerLom("MANIFEST-A09C57BFD9E2313044F1D6AC99DD31C8",null);
				assertTrue("Con Lom previo: ", extraido instanceof Lom && extraido.getLomItem().length==0);
				extraido = manifestAgrega.obtenerLom("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699",null);
				assertTrue("Con Metadata previo sin Lom: ", extraido instanceof Lom && extraido.getLomItem().length==0);
				extraido = manifestAgrega.obtenerLom("ITEM-F7E7F37D-5427-58DC-E205-19D75FF4DA35",null);
				assertTrue("Sin Metadata previo: ", extraido instanceof Lom && extraido.getLomItem().length==0);
				extraido = manifestAgrega.obtenerLom("RES-1937E17748FA68C8270AA65D7691BB67","contenido/actividad.html");
				assertTrue("Sin Metadata previo: ", extraido instanceof Lom && extraido.getLomItem().length==0);*/
				
				dao.escribirODE(manifest, new File("D:/resultado_test.xml"));
				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	public void testObtenerLom() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);

				Lom lom1 = manifestAgrega
						.obtenerLom("MANIFEST-A09C57BFD9E2313044F1D6AC99DD31C8", null);
				Lom lom2 = manifestAgrega
						.obtenerLom("RES-1937E17748FA68C8270AA65D7691BB67", null);
				Lom lom3 = manifestAgrega
						.obtenerLom("MANIFEST-2B3052FE-91ED-61FC-F5BF-2E7ED32F1BE5", null);
				Lom lom4 = manifestAgrega
				.obtenerLom("RES-1937E17748FA68C8270AA65D7691BB67","contenido/actividad.html");
				Lom lomNull = manifestAgrega
						.obtenerLom("ORG-6B2AEB07-8EA1-B15D-C129-B6D8A5F66956", null);

//				assertTrue("lom1: ", lom1 instanceof Lom);
//				assertTrue("lom2: ", lom2 instanceof Lom);
//				assertTrue("lom3: ", lom3 instanceof Lom);
//				assertTrue("lom3: ", lom4 instanceof Lom);
				assertNull("lomNull: ", lomNull);

				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}

	public void testObtenerSequencing() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				Sequencing seq1 = manifestAgrega
						.obtenerSequencing("ORG-1407CA53DF6A0791B2C82466F69C4BB1");
				Sequencing seq2 = manifestAgrega
						.obtenerSequencing("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699");
				assertNotNull(seq1);
				assertNotNull(seq2);
				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			logger.error(e);
		}
	}

	public void testSetSequencing() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				
				// Crear secuencia
				ControlMode cm = new ControlMode();
				cm.setChoice(false);
				cm.setChoiceExit(true);
				cm.setFlow(false);
				cm.setForwardOnly(true);
				Sequencing seq = new Sequencing();
				seq.setControlMode(cm);
				
				manifestAgrega.setSequencing("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699", seq);
				// Asserts
				Sequencing retrieved = manifestAgrega.obtenerSequencing("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699");
				assertNotNull("La secuencia existe: ", seq);
				ControlMode cm2 = retrieved.getControlMode();
				assertFalse("choice : ",cm2.getChoice());
				assertTrue("choiceExit : ",cm2.getChoiceExit());
				assertFalse("flow : ",cm2.getFlow());
				assertTrue("forwardOnly : ",cm2.getForwardOnly());
				
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			logger.error(e);
		}
	}
	
	public void testEliminarRecursos(){
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				
				//PRIMERO: hago la prueba eliminando las dependencias de los recursos
				
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				Resource rec,rec2=new Resource();
				Dependency depen=new Dependency();
				rec=manifestAgrega.getManifest().getResources().getResource(0);
				String id_nuevo_rec="RES-1937E17748FA68C8270AA65D7691BB68";
				rec2.setIdentifier(id_nuevo_rec);
				depen.setIdentifierref(id_nuevo_rec);
				rec.addDependency(0, depen);
				manifestAgrega.getManifest().getResources().addResource(rec2);
				
				assertTrue(manifestAgrega.getManifest().getResources().getResource().length==2);
				assertTrue(manifestAgrega.getManifest().getResources().getResource(0).getDependency().length==1);
				assertEquals(manifestAgrega.getManifest().getResources().getResource(0).getDependency(0).getIdentifierref(),id_nuevo_rec);
				
				manifestAgrega.eliminarRecursos(id_nuevo_rec);
					
				assertTrue(manifestAgrega.getManifest().getResources().getResource().length==2);
				assertTrue(manifestAgrega.getManifest().getResources().getResource(0).getDependency().length==0);
		 						
				//AHORA: hago la prueba eliminando las dependencias de los items
				
				Item item=new Item();
				String id_nuevo_item="ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD698";
				item.setIdentifier(id_nuevo_item);
				item.setIdentifierref(id_nuevo_rec);
				manifestAgrega.getManifest().getOrganizations().getOrganization(0).addItem(item);
				
				assertTrue(manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem().length==5);
				assertTrue(manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem(4).getIdentifier().equals(id_nuevo_item));
				assertTrue(manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem(4).getIdentifierref().equals(id_nuevo_rec));
				
				
				manifestAgrega.eliminarRecursos(id_nuevo_rec);
				
				assertTrue(manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem().length==5);
				assertNull(manifestAgrega.getManifest().getOrganizations().getOrganization(0).getItem(4).getIdentifierref());

				
				}
			} catch (ParseadorException e) {
				logger.error(e);
			}
		}		
	
	
	
	public void testEliminarItem() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				String itemABorrar = "ITEM-F7E7F37D-5427-58DC-E205-19D75FF4DA35";
				assertNotNull("El item existe previamente ", manifestAgrega
						.obtenerItem(itemABorrar));

				manifestAgrega.eliminarItem(itemABorrar);
				assertNull("Comprueba que el item ha sido borrado ",
						manifestAgrega.obtenerItem(itemABorrar));

				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			logger.error(e);
		}
	}

	public void testBuscarPadre() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
				Object padre1 = manifestAgrega
						.buscarPadre("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699");
				Object padre2 = manifestAgrega
						.buscarPadre("ITEM-F7E7F37D-5427-58DC-E205-19D75FF4DA35");
				assertTrue("Assert padre 1", padre1 instanceof Organization);
				assertTrue(
						"Assert padre 2",
						(padre2 instanceof Item)
								&& (((Item) padre2).getIdentifier()
										.equals("ITEM-27DC3291-8067-D5DB-480A-A328E3FD89DD")));
				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			logger.error(e);
		}
	}

	public void testObtenerElemento() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);

				Object manifestExtraido = manifestAgrega
						.obtenerElemento("MANIFEST-A09C57BFD9E2313044F1D6AC99DD31C8");
				Object item = manifestAgrega
						.obtenerElemento("ITEM-C0B1ECC9ADE44FDDBB72F0E4871DD699");
				Object organizacion = manifestAgrega
						.obtenerElemento("ORG-1407CA53DF6A0791B2C82466F69C4BB1");
				Object recurso = manifestAgrega
						.obtenerElemento("RES-1937E17748FA68C8270AA65D7691BB67");
				Object elementoSubmanifiesto = manifestAgrega
						.obtenerElemento("RES-D23D11AE-B507-CC76-D57C-2E801BCA2199");
				Object elementoFile = manifestAgrega
				.obtenerElemento("RES-1937E17748FA68C8270AA65D7691BB67","contenido/actividad.html");
				// Assert de los elementos y sus clases
				assertTrue("elemento Manifest: ",
						manifestExtraido instanceof Manifest);
				assertTrue("elemento Item: ", item instanceof Item);
				assertTrue("elemento Organization: ",
						organizacion instanceof Organization);
				assertTrue("elemento Resource: ", recurso instanceof Resource);
				assertTrue("elemento Resource en Submanifiesto: ",
						elementoSubmanifiesto instanceof Resource);
				assertTrue("elemento File en Resource: ",
						elementoFile instanceof es.pode.parseadorXML.castor.File);

				System.out.println("OK");
			} else
				fail("Recurso de test "
						+ properties.getProperty("test.manifest")
						+ " no encontrado");

		} catch (ParseadorException e) {
			logger.error(e);
		}
	}
	
	
	public void testResetearId() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				// Parseo dos veces el manifiesto para conservar el objeto original. Lo usare para los assert
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				Manifest manifestReseteado = dao.parsearODELazy(new File(url.getFile()));
				
				ManifestAgrega manifestAgrega = new ManifestAgrega(manifestReseteado);
				
				logger.info("Comienzo el reseteo del manifest...");
				manifestAgrega.resetearIds();
				logger.info("Manifiesto reseteado: comienzo los asserts.");
				assertNotSame("El ID del submanifiesto es distinto ", manifest.getManifest(0).getIdentifier(), manifestReseteado.getManifest(0));
				assertNotSame("El ID del recurso en submanifiesto es distinto ",manifest.getManifest(0).getResources().getResource(0).getIdentifier(),manifestReseteado.getManifest(0).getResources().getResource(0).getIdentifier());
				assertEquals("La organizacion principal es la misma: ",manifestReseteado.getOrganizations().getDefault(), manifestReseteado.getOrganizations().getOrganization(0).getIdentifier());
				assertEquals("La organizacion principal del submanifiesto es la misma: ",manifestReseteado.getManifest(0).getOrganizations().getDefault(), manifestReseteado.getManifest(0).getOrganizations().getOrganization(0).getIdentifier());
				assertEquals("La referencia item-recurso en el mismo ode es correcta",manifestReseteado.getOrganizations().getOrganization(0).getItem(0).getIdentifierref(), manifestReseteado.getResources().getResource(0).getIdentifier());
				assertEquals("La referencia item-recurso en submanifiesto es correcta",manifestReseteado.getOrganizations().getOrganization(0).getItem(3).getIdentifierref(), manifestReseteado.getManifest(0).getResources().getResource(0).getIdentifier());
				assertEquals("La referencia item-submanifiesto es correcta",manifestReseteado.getOrganizations().getOrganization(0).getItem(2).getIdentifierref(), manifestReseteado.getManifest(0).getIdentifier());
				logger.info("Manifiesto reseteado: comienzo los asserts.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
			fail("Error durante la ejecucion del test : "+ e.getMessage());
		}
	}
	
	public void testRecuperarLomes() {
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				// Parseo dos veces el manifiesto para conservar el objeto original. Lo usare para los assert
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				
				logger.info("Probando recuperarLomes()");
				ManifestAgrega ma = new ManifestAgrega(manifest);
				Collection lomesCol = ma.recuperarLomes();
				assertEquals("El numero de Lomes recuperados es el esperado.", 4, lomesCol.size());
				logger.info("Probando recuperarLomesAsArray()");
				Lom[] lomes = ma.recuperarLomesAsArray();
				assertEquals("El numero de Lomes recuperados es el esperado.", 4, lomes.length);
			}
		}catch (Exception e) {

			logger.error(e.getMessage(),e);
			fail("Error durante la ejecucion del test : "+ e.getMessage());
		}
	}
	
	public void testObtenerFicherosODE() {
		
		final int ASSERT_PATHS_SIZE = 77;
		
		final String[] arrayOrdenado = new String[] {
				"contenido/actividad.html", "contenido/actividad/2.swf",
				"contenido/css/radio_estilo.css",
				"contenido/imagenes/fondo_amarillo.gif",
				"contenido/imagenes/index_17.gif",
				"submanifest1/contenido/autoevaluador.html",
				"submanifest1/contenido/bibliogra.html",
				"submanifest1/contenido/css/radio_estilo.css",
				"submanifest1/contenido/emergentes/emep10_1.html",
				"submanifest1/contenido/emergentes/emep10_1b.html",
				"submanifest1/contenido/emergentes/emep10_1c.html",
				"submanifest1/contenido/emergentes/emep1_1.html",
				"submanifest1/contenido/emergentes/emep1_1b.html",
				"submanifest1/contenido/emergentes/emep1_1c.html",
				"submanifest1/contenido/emergentes/emep2_1.html",
				"submanifest1/contenido/emergentes/emep2_1b.html",
				"submanifest1/contenido/emergentes/emep2_1c.html",
				"submanifest1/contenido/emergentes/emep2_1d.html",
				"submanifest1/contenido/emergentes/emep3_1.html",
				"submanifest1/contenido/emergentes/emep3_1b.html",
				"submanifest1/contenido/emergentes/emep4_1.html",
				"submanifest1/contenido/emergentes/emep4_1b.html",
				"submanifest1/contenido/emergentes/emep4_1c.html",
				"submanifest1/contenido/emergentes/emep4_1d.html",
				"submanifest1/contenido/emergentes/emep5_1.html",
				"submanifest1/contenido/emergentes/emep5_1b.html",
				"submanifest1/contenido/emergentes/emep5_1c.html",
				"submanifest1/contenido/emergentes/emep5_1d.html",
				"submanifest1/contenido/emergentes/emep6_1.html",
				"submanifest1/contenido/emergentes/emep6_1b.html",
				"submanifest1/contenido/emergentes/emep6_1c.html",
				"submanifest1/contenido/emergentes/emep6_1d.html",
				"submanifest1/contenido/emergentes/emep6_1e.html",
				"submanifest1/contenido/emergentes/emep6_1f.html",
				"submanifest1/contenido/emergentes/emep7_1.html",
				"submanifest1/contenido/emergentes/emep7_1b.html",
				"submanifest1/contenido/emergentes/emep8_1.html",
				"submanifest1/contenido/emergentes/emep8_1b.html",
				"submanifest1/contenido/emergentes/emep8_1c.html",
				"submanifest1/contenido/emergentes/emep8_1d.html",
				"submanifest1/contenido/emergentes/emep8_1e.html",
				"submanifest1/contenido/emergentes/emep9_1.html",
				"submanifest1/contenido/emergentes/emep9_1b.html",
				"submanifest1/contenido/emergentes/emep9_1c.html",
				"submanifest1/contenido/emergentes/emep9_1d.html",
				"submanifest1/contenido/enlaces.html",
				"submanifest1/contenido/imagenes/espacio.gif",
				"submanifest1/contenido/imagenes/fondo_amarillo.gif",
				"submanifest1/contenido/imagenes/index_17.gif",
				"submanifest1/contenido/imagenes/mascota_1_ok.gif",
				"submanifest1/contenido/imagenes/mascota_2_ok.gif",
				"submanifest1/contenido/imagenes/mascota_3_ok.gif",
				"submanifest1/contenido/imagenes/mascota_4_ok.gif",
				"submanifest1/contenido/imagenes/mascota_5_ok.gif",
				"submanifest1/contenido/imagenes/navegador_02.gif",
				"submanifest1/contenido/imagenes/navegador_04.gif",
				"submanifest1/contenido/imagenes/navegador_05.gif",
				"submanifest1/contenido/imagenes/navegador_06.gif",
				"submanifest1/contenido/imagenes/navegador_roll_02.gif",
				"submanifest1/contenido/imagenes/navegador_roll_04.gif",
				"submanifest1/contenido/imagenes/navegador_roll_06.gif",
				"submanifest1/contenido/imagenes/pest_cerrada.gif",
				"submanifest1/contenido/imagenes/pestania_abierta_01.gif",
				"submanifest1/contenido/imagenes/pestania_abierta_02.gif",
				"submanifest1/contenido/imagenes/pestania_abierta_03.gif",
				"submanifest1/contenido/imagenes/pestania_abierta_05.gif",
				"submanifest1/contenido/index.html",
				"submanifest1/contenido/pag1.html",
				"submanifest1/contenido/pag10.html",
				"submanifest1/contenido/pag2.html",
				"submanifest1/contenido/pag3.html",
				"submanifest1/contenido/pag4.html",
				"submanifest1/contenido/pag5.html",
				"submanifest1/contenido/pag6.html",
				"submanifest1/contenido/pag7.html",
				"submanifest1/contenido/pag8.html",
				"submanifest1/contenido/pag9.html" };
		
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest"));
			if (url != null) {
				// Parseo dos veces el manifiesto para conservar el objeto original. Lo usare para los assert
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				
				logger.info("Probando recuperarLomes()");
				ManifestAgrega ma = new ManifestAgrega(manifest);
				
				logger.info("Obteniendo paths desordenados ...");
				String[] pathsDesordenados = ma.obtenerFicherosODE(false);
				logger.info("Obteniendo paths ordenados ...");
				String[] pathsOrdenados = ma.obtenerFicherosODE(true);
				
				// Asserts de longitud de array
				assertEquals("Longitud del path desordenado", ASSERT_PATHS_SIZE, pathsDesordenados.length);
				assertEquals("Longitud del path ordenado", ASSERT_PATHS_SIZE, pathsOrdenados.length);
				// Asserts de contenidos
				assertTrue("Orden y objetos del array ordenado",Arrays.equals(arrayOrdenado, pathsOrdenados));
			}
		} catch (Exception e) {
			logger.error(e);
			fail(e.getMessage());
		}
	}
	
	
	public void testObtenerURLsODE() {
		
		final int ASSERT_PATHS_SIZE = 2;
		
		final String[] arrayOrdenado = new String[] {
				"http://ccaa.agrega.indra.es",
				"http://www.google.com" };
		
		try {
			URL url = this.getClass().getResource(
					"/" + properties.getProperty("test.manifest2"));
			if (url != null) {
				// Parseo dos veces el manifiesto para conservar el objeto original. Lo usare para los assert
				Manifest manifest = dao.parsearODELazy(new File(url.getFile()));
				
				logger.info("Probando recuperarLomes()");
				ManifestAgrega ma = new ManifestAgrega(manifest);
				
				logger.info("Obteniendo paths desordenados ...");
				String[] pathsDesordenados = ma.obtenerURLsODE(false);
				logger.info("Obteniendo paths ordenados ...");
				String[] pathsOrdenados = ma.obtenerURLsODE(true);
				
				// Asserts de longitud de array
				assertEquals("Longitud del path desordenado", ASSERT_PATHS_SIZE, pathsDesordenados.length);
				assertEquals("Longitud del path ordenado", ASSERT_PATHS_SIZE, pathsOrdenados.length);
				// Asserts de contenidos
				assertTrue("Orden y objetos del array ordenado",Arrays.equals(arrayOrdenado, pathsOrdenados));
			}
		} catch (Exception e) {
			logger.error(e);
			fail(e.getMessage());
		}
	}
}
