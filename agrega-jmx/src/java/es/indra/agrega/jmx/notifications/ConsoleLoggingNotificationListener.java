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
