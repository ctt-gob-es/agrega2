// license-header java merge-point

package es.pode.planificador.negocio.servicios.jms;

import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.SimpleMessageConverter;

/**
 * @see es.pode.planificador.negocio.servicios.jms.SrvTareaModificadorService
 *      Generated file:
 *      es/pode/planificador/negocio/servicios/jms/SrvTareaModificadorService
 */
public class SrvTareaModificadorServiceImpl

implements
		es.pode.planificador.negocio.servicios.jms.SrvTareaModificadorService

{

	private static final Logger logger = Logger
			.getLogger(SrvTareaModificadorServiceImpl.class);

	private JmsTemplate template;

	private Destination reply;

	// --------- Default Constructor ----------

	public SrvTareaModificadorServiceImpl() {
		super();
	}

	/**
	 * @param message
	 */
	public void sendMessage(final String message) {
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage(message);
				msg.setJMSReplyTo(getReply());
				return msg;

			}
		});

	}

	/**
	 * @param obj
	 */
	public void sendMessage(final Object obj) {
		template.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				Message message = new SimpleMessageConverter().toMessage(obj,
						session);
				message.setJMSReplyTo(getReply());
				return message;

			}
		});

	}
	/**
	 * @param template
	 */
	public void setJmsTemplate(JmsTemplate template) {

		this.template = template;
	}
	/**
	 * @param reply
	 */
	public void setReply(Destination reply) {

		this.reply = reply;
	}
	/**
	 * @return Destination
	 */
	public Destination getReply() {
		return this.reply;
	}

	// -------- User Defined Methods --------------

	/**
	 * Llamar al método sendMessage que envia a una cola jms la tarea de modificación
	 * @param idModificacion de la tarea de modificación
	 * @return ejecutarTareaModificacion Boolean
	 * @see es.pode.planificador.negocio.servicios.jms.SrvTareaModificadorService#ejecutarTareaModificacion(java.lang.Long)
	 */
	public java.lang.Boolean ejecutarTareaModificacion(java.lang.Long idModificacion)
    {
    	logger.info("Enviando mensaje para la ejecuacion de la tarea de modificacion " + idModificacion);
    	Boolean result = Boolean.TRUE;
    	try {
    	sendMessage(idModificacion);
    	} catch (Exception e) {
    		result = Boolean.FALSE;
    		logger.error("No se ha podido enviar el mensaje para ejecutar la tarea de modificacion " + idModificacion, e);
    		if(logger.isDebugEnabled()) logger.debug(e);
		}
        
        return result;
    }

	/**
	 * @param map
	 */
	public void sendMessage(Map map) {
		// TODO Auto-generated method stub
		
	}

}