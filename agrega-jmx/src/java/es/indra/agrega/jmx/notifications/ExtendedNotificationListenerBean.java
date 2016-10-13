/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import javax.management.MBeanServerConnection;
import javax.management.NotificationListener;

import org.springframework.jmx.export.NotificationListenerBean;

/**
 * @author jlhuertas
 *
 */
public class ExtendedNotificationListenerBean extends NotificationListenerBean {

	/**
	 * List of servers that we are going to listen for.
	 */
	private MBeanServerConnection[] servers;

	/**
	 * Create a new instance of the {@link NotificationListenerBean} class.
	 * @param notificationListener the encapsulated listener
	 */
	public ExtendedNotificationListenerBean(NotificationListener notificationListener) {
		super(notificationListener);
	}

	public MBeanServerConnection[] getServers() {
		return servers;
	}

	public void setServers(MBeanServerConnection[] servers) {
		this.servers = servers;
	}
	
}
