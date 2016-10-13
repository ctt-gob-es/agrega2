/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import javax.management.Notification;

import es.indra.agrega.jmx.JmxUtils;

/**
 * @author jlhuertas
 *
 */
public class SLANotification extends Notification {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3626673244486049485L;

	public SLANotification(String type, Object source, long sequenceNumber, long timeStamp, String message){
		super(type, source, sequenceNumber, timeStamp, message);
	}
	
	/*String observedAttribute;
	
	public SLANotification(String type, Object source, long sequenceNumber, long timeStamp, String message, String obsAtt){
		super(type, source, sequenceNumber, timeStamp, message);
		observedAttribute = obsAtt;
	}

	public String getObservedAttribute() {
		return observedAttribute;
	}

	public void setObservedAttribute(String observedAttribute) {
		this.observedAttribute = observedAttribute;
	}*/
	
    /**
     * Returns a String representation of this SLANotification.
     *
     * @return  A a String representation of this EventObject.
     */
    public String toString() {
    	return JmxUtils.notificationToString(this);
    }


}
