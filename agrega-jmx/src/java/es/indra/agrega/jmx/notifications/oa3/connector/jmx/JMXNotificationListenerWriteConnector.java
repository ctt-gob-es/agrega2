/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
