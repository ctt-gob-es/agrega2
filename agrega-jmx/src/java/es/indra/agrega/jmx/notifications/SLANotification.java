/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
