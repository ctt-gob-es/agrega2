package es.pode.informes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.IPlatformContext;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformServletContext;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.ReportEngine;


public class BirtEngine {

private static ReportEngine birtEngine = null;

private static Properties configProps = new Properties();

private final static String configFile = "BirtConfig.properties";

private static Logger logger=Logger.getLogger(BirtEngine.class);

public static synchronized void initBirtConfig() {
 loadEngineProps();
}

public static synchronized ReportEngine getBirtEngine(ServletContext sc) {
	System.out.println("getBirtEngine "+birtEngine);
 if (birtEngine == null) 
 {
	 System.out.println("birtEngine es null");
  EngineConfig config = new EngineConfig();
  
  if( configProps != null){
	  System.out.println("configProps es distinto de null");
   String logLevel = configProps.getProperty("logLevel");
   Level level = Level.OFF;
   if ("SEVERE".equalsIgnoreCase(logLevel)) 
   {
    level = Level.SEVERE;
   } else if ("WARNING".equalsIgnoreCase(logLevel))
   {
    level = Level.WARNING;
   } else if ("INFO".equalsIgnoreCase(logLevel)) 
   {
    level = Level.INFO;
   } else if ("CONFIG".equalsIgnoreCase(logLevel))
   {
    level = Level.CONFIG;
   } else if ("FINE".equalsIgnoreCase(logLevel)) 
   {
    level = Level.FINE;
   } else if ("FINER".equalsIgnoreCase(logLevel)) 
   {
    level = Level.FINER;
   } else if ("FINEST".equalsIgnoreCase(logLevel)) 
   {
    level = Level.FINEST;
   } else if ("OFF".equalsIgnoreCase(logLevel)) 
   {
    level = Level.OFF;
   }

   config.setLogConfig(configProps.getProperty("logDirectory"), level);
  }
  
  
  config.setEngineHome("");
  IPlatformContext context = new PlatformServletContext(sc);
  config.setPlatformContext( context );

  try
  { 
   Platform.startup( config );
   System.out.println("despues de startup");
  }
  catch ( BirtException e )
  {
  System.out.println("Se produce la siguiente excepcion "+e);
   e.printStackTrace( );
  }
  birtEngine = new ReportEngine(config);
  //IReportEngineFactory factory = (IReportEngineFactory) Platform
  //.createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
  System.out.println("birtEngine vale "+birtEngine);

 }
 return birtEngine;
}

public static synchronized void destroyBirtEngine() {
// if (birtEngine == null) {
 // return;
 //}  
 //birtEngine.destroy();
 Platform.shutdown();
 System.out.println("despues de Platform.shutdown()");
 birtEngine = null;
}
 
public Object clone() throws CloneNotSupportedException {
 throw new CloneNotSupportedException();
}

private static void loadEngineProps() {
 try {
  
  ClassLoader cl = Thread.currentThread ().getContextClassLoader();
  InputStream in = null;
  in = cl.getResourceAsStream (configFile);
  configProps.load(in);
  in.close();


 } catch (IOException e) {
	 logger.error(e);
 }

}

/*

public static synchronized IRunAndRenderTask starUpBirt(ServletContext sc) throws EngineException {

    
	loadEngineProps();
	
    System.out.println(" starUpBirt cargo las propiedades");
    EngineConfig config = null;
  //  IReportEngine engine = null;
    ReportEngine engine = null;
    IRunAndRenderTask task = null;
    try { 
		
		//Configure the Engine and start the Platform
		config = new EngineConfig( );
		     config.setEngineHome("c:/birtruntime/ReportEngine");
     System.out.println("Despues de config.setEngineHome "+config.getResourcePath());
     //IPlatformContext context = new PlatformServletContext(sc);
     //System.out.println("despues de sacar el contexto");
     //config.setPlatformContext(context);
      System.out.println("birtHome "+config.getBIRTHome());
      Platform.startup(config);
      System.out.println("Despues de Platform.startup "+Platform.getPlatformType());
      engine = new ReportEngine(config); 
     // IReportEngineFactory factory = (IReportEngineFactory) Platform
    //  .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
        System.out.println("despues de createReportEngine engine es "+engine);   
        System.out.println("engine.getClass "+engine.getClass());
    //   engine.changeLogLevel(Level.WARNING);
       System.out.println("despues de engine.changeLogLevel");
              
                IReportRunnable design = null;
                // Open the report design
                if(engine == null)
                {
                	System.out.println("el engine es null");
                }else
                {
                	ClassLoader loader = BirtEngine.class.getClassLoader();
System.out.println("Despues de sacar el loader "+loader);
                    InputStream in = loader.getResourceAsStream("C:\\birtruntime\\ReportEngine\\samples\\hello_world.rptdesign");
                    System.out.println("---------------");
                    System.out.println("in.available "+in.available());
                	System.out.println("**************");
                	//design = engine.openReportDesign("test.rptdesign");
                	//System.out.println("DESIGN VALE (obtengo el test) "+design);
                System.out.println("engine ES "+engine);
                	design = engine.openReportDesign(in);
                	System.out.println("despues de sacar design "+design);
                	System.out.println("+++++++++++++++++++");
                //design = engine.openReportDesign("C:/MDA/jboss-4.0.2/server/default/deploy/WebReport/Reports/adminUsuarios.rptdesign");
                }
System.out.println("design es "+design);
                // Create task to run and render the report,

                task = engine.createRunAndRenderTask(design);
                System.out.println("task vale "+task);
                // Set Render context to handle url and image locataions
                PDFRenderContext renderContext = new PDFRenderContext();
                renderContext.setSupportedImageFormats("JPG;PNG;BMP;SVG");
                HashMap contextMap = new HashMap();
                contextMap.put(EngineConstants.APPCONTEXT_PDF_RENDER_CONTEXT,
                                      renderContext);
                task.setAppContext(contextMap);
System.out.println(" *** SstartBirt: END");

                

               
    } catch (Exception ex) {
    	System.out.println("Se produce la siguiente excepcion!!! "+ex);
    	Platform.shutdown();
    	System.out.println("despues de tirar la plataforma");
    	                           logger.error(ex);

    	                }
    return task;

    }

*/

}
