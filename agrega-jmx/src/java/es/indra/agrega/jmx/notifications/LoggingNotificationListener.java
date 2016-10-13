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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import es.indra.agrega.jmx.JmxUtils;

/**
 * This listener class listen for JMX notifications sent when a service
 * or operation breaks a stablished SLA.
 * 
 * When a notification is received it is logged with the Log4J framework.
 * 
 * @author jlhuertas
 *
 */
public class LoggingNotificationListener implements NotificationListener,
		NotificationFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3840008966810627444L;

	/**
	 * Default log level used unless a different one is provided.
	 */
	public static final Level DEFAULT_LOG_LEVEL = Level.WARN;
	
	private Level logLevel = DEFAULT_LOG_LEVEL;
	
	private static Logger logger = Logger.getLogger(LoggingNotificationListener.class);
	
	/**
 	 * Invoked when a JMX notification occurs.
	 * The implementation of this method should return as soon as possible, to avoid
	 * blocking its notification broadcaster.
	 * 
	 * This implementation just log the JMX notification using the Log4j logging framework.
	 *
	 * @param notification The notification.    
	 * @param handback An opaque object which helps the listener to associate information
	 * regarding the MBean emitter. This object is passed to the MBean during the
	 * addListener call and resent, without modification, to the listener. The MBean object 
	 * should not use or modify the object. 
	 *
	 */
	public void handleNotification(Notification notification, Object handback) {
		logger.log(logLevel, JmxUtils.notificationToString(notification));
	}

    /**
     * Invoked before sending the specified notification to the listener.
     *   
     * @param notification The notification to be sent.
     * @return <CODE>true</CODE> if the notification has to be sent to the listener, <CODE>false</CODE> otherwise.
     */  	
	public boolean isNotificationEnabled(Notification notification) {
		return Notification.class.isAssignableFrom(notification.getClass());
	}

	/**
	 * Change the logging level used to log the JMX notifications. If the string
	 * cannot be parsed the level applied is the default level
	 * {@link #DEFAULT_LOG_LEVEL}
	 * 
	 * @param levelStr
	 *            Possible values are
	 *            <code>"DEBUG", "INFO", "WARN", "ERROR", "FATAL"</code> (case
	 *            insensitive).
	 * 
	 */
	public void setNotificationLevel(String levelStr) {
		logLevel = Level.toLevel(levelStr, DEFAULT_LOG_LEVEL);
	}
}
