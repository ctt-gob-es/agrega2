/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

/**
 * @author jlhuertas
 *
 */
public class ConsoleLoggingNotificationListener	implements NotificationListener, NotificationFilter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978201386905631060L;

	public void handleNotification(Notification notification, Object handback) {
		System.out.println(notification);
		System.out.println(handback);
	}
	
	public boolean isNotificationEnabled(Notification notification) {
		return Notification.class.isAssignableFrom(notification.getClass());
	}
}
