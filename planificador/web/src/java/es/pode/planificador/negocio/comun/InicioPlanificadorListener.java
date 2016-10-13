package es.pode.planificador.negocio.comun;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import es.pode.ServiceLocator;

public class InicioPlanificadorListener implements ServletContextListener {

	private static Logger log = Logger.getLogger(InicioPlanificadorListener.class);
	
	public void contextInitialized(ServletContextEvent arg0) 
	{
		try 
		{
			log.info("Inicialización del planificador en el contextInitialized");
			if (!Planificador.getAgenda().isStarted()) 
			{
				Planificador.getAgenda().start();
				log.info("Planificador iniciado");
			}
				
			
			/* Los trabajos que se quedaron a media ejecución al parar el servidor se ponen a interrumpidos */
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoInterrumpido();
		}
		catch (Exception e)
		{
			log.error("Error en la inicialización del planificador " + e);
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) 
	{
		try 
		{
			log.info("Parada del planificador en el contextDestroyed");
			if (Planificador.getAgenda().isStarted()) 
			{
				Planificador.getAgenda().shutdown();
				log.info("Planificador parado");
			}
			
			/* Los trabajos que se quedaron a media ejecución al parar el servidor se ponen a interrumpidos */
			ServiceLocator.instance().getSrvRegistroPlanificadorService().registrarTrabajoInterrumpido();
		}
		catch (Exception e)
		{
			log.error("Error en la parada del planificador en la destrucción del contexto " + e);
		}
	}
}
