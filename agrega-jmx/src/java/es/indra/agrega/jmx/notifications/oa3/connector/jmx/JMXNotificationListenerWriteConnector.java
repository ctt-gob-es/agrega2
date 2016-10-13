/**
 * 
 */
package es.indra.agrega.jmx.notifications.oa3.connector.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

import org.apache.log4j.Logger;
import org.oa3.connector.IExternalWriteConnector;
import org.oa3.control.OAException;
import org.oa3.transaction.ITransactionSpec;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author jlhuertas
 *
 */
public class JMXNotificationListenerWriteConnector implements IExternalWriteConnector, InitializingBean {

	static Logger logger = Logger.getLogger(JMXNotificationListenerWriteConnector.class);
	
	private NotificationListener notificationListener;
	
	public Object deliver(Object[] payloads) throws OAException {
		
		int size=payloads.length;
        for (int i=0;i<size;i++) { //Un-batch the individual payloads.
            Object payload= payloads[i];
            if ( (payload != null) && (payload instanceof Notification)){
            	logger.info("Sending [" + payload + "] by email.");
            	notificationListener.handleNotification((Notification) payload, null);
            } else {
            	logger.warn("Undeliverable payload. Is null or not a JMX notification");
            }
        }
		return null;
	}

	public void connect() {

	}

	public void disconnect() {

	}

	public boolean isConnected() {
		return true;
	}

	public ITransactionSpec getTransactionResource() {
		return null;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(notificationListener, "The field 'emailNotificationListener' is mandatory.");
	}

	public NotificationListener getNotificationListener() {
		return notificationListener;
	}

	public void setNotificationListener(
			NotificationListener notificationListener) {
		this.notificationListener = notificationListener;
	}

}
