package es.indra.agrega.jmx.notifications;

import java.io.IOException;


import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jmx.export.MBeanExportException;
import org.springframework.jmx.export.NotificationListenerBean;
import org.springframework.jmx.support.ObjectNameManager;

public class RemoteNotificationListenerRegisterer implements InitializingBean{

	private Logger logger = Logger.getLogger(RemoteNotificationListenerRegisterer.class);
	
	protected MBeanServerConnection defaultServer;

	/**
	 * The {@link javax.management.NotificationListener NotificationListeners} to register
	 * for the MBeans registered by this <code>MBeanExporter</code>.
	 */
	private ExtendedNotificationListenerBean[] notificationListeners = new ExtendedNotificationListenerBean[0];
	
	public MBeanServerConnection getDefaultServer() {
		return defaultServer;
	}

	public void setDefaultServer(MBeanServerConnection server) {
		this.defaultServer = server;
	}

	/**
	 * Set the {@link NotificationListenerBean NotificationListenerBeans} containing the
	 * {@link javax.management.NotificationListener NotificationListeners} that will be registered
	 * with the {@link MBeanServer}.
	 * @see #setNotificationListenerMappings(java.util.Map)
	 * @see NotificationListenerBean
	 */
	public void setNotificationListeners(ExtendedNotificationListenerBean[] notificationListeners) {
		this.notificationListeners = notificationListeners;
	}
	
	
	public void afterPropertiesSet() throws Exception {
		//Assert.notNull(server, "Property 'server' must not be null");
		registerNotificationListeners();
	}
	
	/**
	 * Register the configured {@link NotificationListener NotificationListeners}
	 * with the {@link MBeanServer}.
	 */
	private void registerNotificationListeners() throws MBeanExportException {
		for (int i = 0; i < this.notificationListeners.length; i++) {
			ExtendedNotificationListenerBean bean = this.notificationListeners[i];
			NotificationListener listener = bean.getNotificationListener();
			NotificationFilter filter = bean.getNotificationFilter();
			Object handback = bean.getHandback();
			ObjectName[] namesToRegisterWith = getObjectNamesForNotificationListener(bean);
			for (int j = 0; j < namesToRegisterWith.length; j++) {
				ObjectName objectName = namesToRegisterWith[j];
				//this.server.addNotificationListener(objectName, listener, filter, handback);
				MBeanServerConnection[] servers = bean.getServers();
				if (servers == null){
					if (defaultServer == null){
						servers = new MBeanServerConnection[0];
					}else{
						servers = new MBeanServerConnection[1];
						servers[0] = defaultServer;
					}
				}


				for (int k = 0; k < servers.length; k++){
					try {
						servers[k].addNotificationListener(objectName, listener, filter, handback);
						
					} catch (InstanceNotFoundException ex) {
						logger.info("Unable to register NotificationListener for MBean [" +
								objectName + "] in the server " + servers[k] + "because that MBean instance does not exist");
					} catch (IOException ex) {
						logger.info("Unable to register remote NotificationListener for MBean [" +
								objectName + "] in the server " + servers[k] + "because an IOException happened.", ex);
					}
				}
			}
		}
	}

	/**
	 * Retrieve the {@link javax.management.ObjectName ObjectNames} for which a
	 * {@link NotificationListener} should be registered.
	 */
	private ObjectName[] getObjectNamesForNotificationListener(NotificationListenerBean bean)
			throws MBeanExportException {
		String[] mappedObjectNames = bean.getMappedObjectNames();
		if (mappedObjectNames == null){
			mappedObjectNames = new String[0];
		}
		ObjectName[] objectNames = new ObjectName[mappedObjectNames.length];
		for (int i = 0; i < mappedObjectNames.length; i++) {
			String mappedName = mappedObjectNames[i];
			try {
				objectNames[i] = ObjectNameManager.getInstance(mappedName);
			}
			catch (MalformedObjectNameException ex) {
				throw new MBeanExportException(
						"Invalid ObjectName [" + mappedName + "] specified for NotificationListener [" +
						bean.getNotificationListener() + "]", ex);
			}
		}
		return objectNames;
	}

	
	
}
