// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.auditoria.negocio.servicios.jms;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.log4j.Logger;
import javax.jms.MessageListener;
import es.pode.auditoria.ServiceLocator;
import es.pode.auditoria.negocio.servicios.BusquedaVO;
import es.pode.auditoria.negocio.servicios.OperacionVO;
import es.pode.auditoria.negocio.servicios.PeticionFeedVO;

public class SrvAuditoriaRecibirServicioImpl implements es.pode.auditoria.negocio.servicios.jms.SrvAuditoriaRecibirServicio, MessageListener
{

	protected static Logger logger = Logger.getLogger(SrvAuditoriaRecibirServicioImpl.class);
	private es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio srvAuditoriaServicio;

	/**
	 * Saca de la cola jms los mensajes almacenados en la misms
	 * @param msg Mensage almacenado en la cola
	 * @param session
	 * @throws Exception
	 */

	public void onMessage(Message msg, Session session) throws Exception
	{

		try
		{
			// Implementation
			if (msg instanceof ObjectMessage)
			{
//				logger.info("msd instanceof ObjectMessage");

				ObjectMessage obj = (ObjectMessage) msg;
//				if (logger.isDebugEnabled())logger.debug("Mensaje transformado: <" + obj.getObject()+">");
				BusquedaVO busqueda = (BusquedaVO) obj.getObject();
				logger.info("busqueda vale <" + busqueda+">");
				if (logger.isDebugEnabled())
					logger.debug("El termino buscado es  [" + busqueda.getTerminoBuscado());
				//Si todo ha ido bien hago la petición RMI pasando el Objeto
				this.registrarBusqueda(busqueda);

			} else
			{
				if (logger.isDebugEnabled())
					logger.debug("No hay mas mensajes que tratar");
			}
			//session.close();

		} catch (Exception ex)
		{
			logger.error("Se ha producido un error al leer el mensaje [" + msg + "]", ex);
		}

	}

	/**
	 * Registra en base de datos la operación de búsqueda 
	 * @param busqueda Value Object con los datos de la búsqueda
	 */
	public void registrarBusqueda(es.pode.auditoria.negocio.servicios.BusquedaVO busqueda)
	{
//		logger.info("registrarBusqueda busqueda vale " + busqueda);
		if (logger.isDebugEnabled())
			logger.debug("La busqueda: <" + busqueda+"> y el servicio: <" + this.getSrvAuditoriaServicio()+">");
		ServiceLocator.instance().getSrvAuditoriaServicio().almacenarBusquedaBD(busqueda);
		//this.getSrvAuditoriaServicio().almacenarBusquedaBD(busqueda);
		if (logger.isDebugEnabled())
			logger.debug("Almacenada la busqueda en bbdd");

	}

	/**
	 * Registra en base de datos la operación de búsqueda 
	 * @param srvAuditoriaServicio Value Object con los datos de la búsqueda
	 */
	public void setSrvAuditoriaServicio(es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio srvAuditoriaServicio)
	{
		this.srvAuditoriaServicio = srvAuditoriaServicio;
	}

	/**
	 * Obtiene la referencia al objeto SrvAuditoriaServicio
	 * @return SrvAuditoriaServicio
	 */
	public es.pode.auditoria.negocio.servicios.SrvAuditoriaServicio getSrvAuditoriaServicio()
	{
		return this.srvAuditoriaServicio;
	}

	/**
	 * Registra en base de datos la operación realizada 
	 * @param operacion Value Object con los datos de la operación
	 */
	public void registrarOperacion(OperacionVO operacion)
	{
		if (logger.isDebugEnabled())
			logger.debug("La operacion: <" + operacion+"> y el servicio es: <"+ this.getSrvAuditoriaServicio()+">");
		ServiceLocator.instance().getSrvAuditoriaServicio().almacenarOperacionBD(operacion);
		//this.getSrvAuditoriaServicio().almacenarBusquedaBD(busqueda);
		if (logger.isDebugEnabled())
			logger.debug("Almacenada la busqueda en bbdd");

	}

	/**
	 * Saca de la cola jms los mensajes almacenados en la misms
	 * @param msg
	 */
	public void onMessage(Message msg)
	{
		try
		{
			// Implementation
			if (msg instanceof ObjectMessage)
			{
//				if(logger.isDebugEnabled())logger.debug("msg instanceof ObjectMessage " + msg);
			
				ObjectMessage obj = (ObjectMessage) msg;
//				if(logger.isDebugEnabled())logger.debug("El mensaje transformado vale <" + obj.getObject()+">");
				if (obj.getObject() instanceof BusquedaVO)
				{
					//if(logger.isDebugEnabled())logger.debug("obj.getObject().getClass() vale " + obj.getObject().getClass());
					BusquedaVO busqueda = (BusquedaVO) obj.getObject();
					//logger.debug("busqueda vale " + busqueda+" antes de registrarBusqueda");
					this.registrarBusqueda(busqueda);
				} else if (obj.getObject() instanceof OperacionVO) {
						//if(logger.isDebugEnabled())logger.debug("obj.getObject().getClass() vale " + obj.getObject().getClass());
						OperacionVO operacion = (OperacionVO) obj.getObject();
						//if(logger.isDebugEnabled())logger.debug("busqueda vale <" + operacion+"> antes de registrarBusqueda");
						this.registrarOperacion(operacion);
				} else if (obj.getObject() instanceof PeticionFeedVO) {
					//if(logger.isDebugEnabled()) logger.debug("obj.getObject().getClass() vale " + obj.getObject().getClass());
					PeticionFeedVO peticion = (PeticionFeedVO)obj.getObject();
					registrarPeticionFeed(peticion);
				} else {
					logger.warn("El mensaje recibido en el servicio de auditoria no es de ningun tipo esperado: <" + obj.getObject().getClass()+">");
				}
				if (logger.isDebugEnabled())
					logger.debug("El termino se almacena en bbdd");
				//Si todo ha ido bien hago la petición RMI pasando el Objeto

			} else
			{
				if (logger.isDebugEnabled())
					logger.debug("No hay mas mensajes que tratar");
			}
			//session.close();

		} catch (Exception ex)
		{
			logger.error("Se ha producido un error al leer el mensaje [" + msg + "]", ex);
		}

	}

	/**
	 * Registra en la base de datos de auditoria la intercepción de una peticion
	 * a un feed RSS/Atom.
	 * 
	 * @param VO con los datos de la intercepcion.
	 */
	public void registrarPeticionFeed(PeticionFeedVO peticionFeed) {
//		if(logger.isDebugEnabled()) logger.debug("Llamando a SrvAuditoriaServicio:almacenarPeticionFeedBD(:peticionFeed)");
		ServiceLocator.instance().getSrvAuditoriaServicio().almacenarPeticionFeedBD(peticionFeed);
	}

}