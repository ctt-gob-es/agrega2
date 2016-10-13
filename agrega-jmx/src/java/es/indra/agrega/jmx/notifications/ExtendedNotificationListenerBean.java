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
