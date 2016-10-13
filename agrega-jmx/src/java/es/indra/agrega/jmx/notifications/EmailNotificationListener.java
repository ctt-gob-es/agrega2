/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * 
 */
package es.indra.agrega.jmx.notifications;

import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import es.indra.agrega.jmx.JmxUtils;

/**
 * This listener class listen for JMX notifications sent when a service
 * or operation breaks a stablished SLA.
 * 
 * When a notification is received it is sent by email to the system administrator.
 * 
 * @author jlhuertas
 *
 */
public class EmailNotificationListener implements NotificationListener,	NotificationFilter, InitializingBean, Deliverer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8639435668979521206L;

	private boolean ready = false;
	
	private static Logger logger = Logger.getLogger(EmailNotificationListener.class);
	
	/**
	 * SMTP server used to send the emails.
	 */
	private String mailhost;
	
	/**
	 * username with rights to connect to the SMTP server and send emails.
	 */
	private String user;
	
	/**
	 * Password of the user used to connect to the SMTP server.
	 */
	private String password;
	
	/**
	 * Email address from which the emails are going to be sent.
	 */
	private String from;
	
	/**
	 * Email addresses to send the emails. You can specify a list of email addresses separated by semicolons.
	 */
	private String to;
	
	/**
	 * Persistent queue to store the notifications until they are delivered.
	 */
	private PersistedNotificationQueue deliveryQueue;
	

	public void handleNotification(Notification notification, Object handback) {
		//add the notification to the delivery queue
		deliveryQueue.add(notification);
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

	
	private void sendMail(String to, String subject, String content) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.user", user );
        properties.put("mail.host", mailhost);
        properties.put("mail.from", from);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", Boolean.toString(password != null));
        //properties.put("mail.debug", "true");
        Session session = Session.getInstance(properties);
        
        MimeMessage message = new MimeMessage(session);
        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setText(content);
        message.setSentDate(new Date());
        message.saveChanges();
        
        Transport t = session.getTransport("smtp");
       	t.connect(mailhost, user, password);
        t.sendMessage(message, message.getAllRecipients());
        t.close();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMailhost() {
		return mailhost;
	}

	public void setMailhost(String mailhost) {
		this.mailhost = mailhost;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(deliveryQueue, "The field 'deliveryQueue' is mandatory.");
		Assert.hasText(from, "The field 'from' is mandatory.");
		Assert.hasText(mailhost, "The field 'mailhost' is mandatory.");
		Assert.hasText(user, "The field 'user' is mandatory.");
		Assert.hasText(to, "The field 'to' is mandatory.");
		ready = true;
	}

	public boolean deliver(Notification notification) {
		if (!ready){
			return false;
		}
		boolean result = true;
		try {
			//Construct the subject and content of the message
			String subject = "SLA broken. MBean:" + notification.getSource();
			String content = JmxUtils.notificationToString(notification); 
			//send the message
			this.sendMail(to, subject, content);
		} catch (MessagingException e) {
			result = false;
			logger.warn("Error sending notification EMail.", e);
		}	
		return result;
	}

	public PersistedNotificationQueue getDeliveryQueue() {
		return deliveryQueue;
	}

	public void setDeliveryQueue(PersistedNotificationQueue deliveryQueue) {
		this.deliveryQueue = deliveryQueue;
	}
}
