/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import javax.management.Notification;

/**
 * @author jlhuertas
 *
 */
public interface Deliverer {

	public boolean deliver(Notification notification);
}
