package es.pode.modificador.negocio.cambios;

import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pode.parseadorXML.LomESDao;
import es.pode.parseadorXML.ParseadorException;
import es.pode.parseadorXML.SCORM2004Dao;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;

public class TestCambioLomes extends TestCase
{
	private static ClassPathXmlApplicationContext context = null;
	private SCORM2004Dao dao = null;
	private LomESDao lomesDao=null;
	private FactoriaCambios factoria = null;
	private static final String TEST_XML2 = "/odes/es_20070727_2_0130101/imsmanifest.xml";
	private Logger logger = Logger.getLogger(this.getClass());
	public TestCambioLomes(String arg0)
	{
		super(arg0);
		if(context==null) 
		{
			context = new ClassPathXmlApplicationContext(new String[]{"testContext.xml","user-applicationContext.xml"});
		}	
		if(dao==null) 
		{
			dao = (SCORM2004Dao)context.getBean("scorm2004Dao");
		}
		if(factoria==null) {
			factoria = (FactoriaCambios)context.getBean("factoriaCambios");
		}
		if(lomesDao==null) 
		{
			lomesDao = (LomESDao)context.getBean("lomesDao");
			
		}
		
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	//PROBADO
	public void testTransformarLom2Element() throws ParseadorException
	{
		try
		{
			String rutaManifest = this.getClass().getResource(TEST_XML2).getFile();
			File fOde= new File(rutaManifest);
			Manifest manifest= null;
			if (fOde.exists())
			{
				manifest = dao.parsearODELazy(fOde);
				ManifestAgrega manifestA=new ManifestAgrega(manifest);
				try
				{
				 Lom lom = manifestA.obtenerLom(manifest.getIdentifier(), null);
				 if (lom!=null)
					{
						ModificacionValor mod= new ModificacionValor();
						mod.setLomesDao(lomesDao);
						assertNotNull(mod.transformarLom2Element(lom));		
					}
				}
				catch (Exception e) {
					logger.error("no se ha podido obtener el lom");
				}
				
			}
		}
		catch (Exception e) 
		{
		
			logger.error("No se ha encontrado la ruta del imsmanifest" +e);
		}
	}
	
	
	//PROBADO
	public void testTransformarElement2Lom() throws Exception
	{
		String rutaManifest = this.getClass().getResource(TEST_XML2).getFile();
		File fOde= new File(rutaManifest);
		Manifest manifest= null;
		if (fOde.exists())
		{
			manifest = dao.parsearODELazy(fOde);
			ManifestAgrega manifestA=new ManifestAgrega(manifest);
			try
			{
			 Lom lom = manifestA.obtenerLom(manifest.getIdentifier(), null);
			 if (lom!=null)
				{
					ModificacionValor mod= new ModificacionValor();
					mod.setLomesDao(lomesDao);
					Element elem=mod.transformarLom2Element(lom);		
					assertNotNull(mod.transformarElement2Lom(elem));
				}
			}
			catch (Exception e) {
				logger.error("no se ha podido obtener el lom");
			}
			
		}
	}
	
	public void testobtenerTerminoLomes() throws ParseadorException
	{
		String rutaManifest = this.getClass().getResource(TEST_XML2).getFile();
		File fOde= new File(rutaManifest);
		Manifest manifest= null;
		if (fOde.exists())
		{
			manifest = dao.parsearODELazy(fOde);
			ManifestAgrega manifestA=new ManifestAgrega(manifest);
			try
			{
			 Lom lom = manifestA.obtenerLom(manifest.getIdentifier(), null);
			 if (lom!=null)
				{
					ModificacionValor mod= new ModificacionValor();
					mod.setLomesDao(lomesDao);
					//prueba1
					
//					 mod.setTerminoLom("lom.technical.format");
					
					
					//prueba2
					
//					 mod.setTerminoLom("lom.general.keyword.string");
//					 mod.setRegExp(true);
//					 mod.setValor("Pol�gonos|Tecnolog�a");
					
					
//					//prueba3
//					mod.setTerminoLom("lom.general.coverage.string");
//					mod.setLenguaje("es");
//					mod.setValor(".*");
//					mod.setRegExp(true);
					
//					//prueba4
					mod.setTerminoLom("lom.technical.format");
					mod.setValor("text/html");
					mod.setRegExp(false);
					
					Element elem=mod.transformarLom2Element(lom);		
					assertNotNull(mod.obtenerTerminoLomes(elem));
				}
			}
			catch (Exception e) {
				logger.error("no se ha podido obtener el lom");
			}
			
		}
	}
	

	/**
	 * @return the dao
	 */
	public SCORM2004Dao getDao()
	{
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(SCORM2004Dao dao)
	{
		this.dao = dao;
	}

	/**
	 * @return the factoria
	 */
	public FactoriaCambios getFactoria()
	{
		return factoria;
	}

	/**
	 * @param factoria the factoria to set
	 */
	public void setFactoria(FactoriaCambios factoria)
	{
		this.factoria = factoria;
	}

	/**
	 * @return the context
	 */
	public static ClassPathXmlApplicationContext getContext()
	{
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public static void setContext(ClassPathXmlApplicationContext context)
	{
		TestCambioLomes.context = context;
	}

}
