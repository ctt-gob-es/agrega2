/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.agrega.jmx.notifications.oa3.connector.jmx;


import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.UnboundedFifoBuffer;
import org.apache.log4j.Logger;
import org.oa3.connector.AbstractReadConnector;
import org.oa3.control.OAException;

import javax.management.Notification;
import javax.management.NotificationListener;


public class JMXReadConnector extends AbstractReadConnector implements NotificationListener {
    
	static Logger log = Logger.getLogger(JMXReadConnector.class.getName());

	/**
	 * Queue to store the JMX notifications as they arrive.
	 */
	private Buffer queue = BufferUtils.synchronizedBuffer(new UnboundedFifoBuffer());

    /**
     * Receive next Message. 
     *
     * @return Object[] containing the next batch of payloads from this connector.
     */
	
	public Object[] nextRecord() throws OAException {
		Object[] result = null;
		if (!queue.isEmpty()){
			synchronized(queue){				
				result = queue.toArray();
				queue.clear();
			}
		}
		return result;
	}


   /**
	* Invoked when a JMX notification occurs.
	* The implementation of this method should return as soon as possible, to avoid
	* blocking its notification broadcaster.
	*
	* @param notification The notification.    
	* @param handback An opaque object which helps the listener to associate information
	* regarding the MBean emitter. This object is passed to the MBean during the
	* addListener call and resent, without modification, to the listener. The MBean object 
	* should not use or modify the object. 
	*
	*/
	public void handleNotification(Notification notification, Object handback) {
		synchronized(queue){
			queue.add(notification);	
		}
	}

}

