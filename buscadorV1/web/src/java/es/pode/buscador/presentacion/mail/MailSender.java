/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.buscador.presentacion.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

public class MailSender {

	private java.util.Properties pSpringProperties = null;
	
	private static Logger logger = Logger.getLogger(MailSender.class);

	public MailSender() {

	}

//	public boolean send(String toAddress, String subject, StringBuffer body,
//			String from) {
//
//		log("Enviar email");
//		log("Enviar email a la direccion ["+toAddress+"]");		
//		boolean resultado = false;
//		MimeMultipart multipart = new MimeMultipart();
//		Properties properties = new Properties();
//		String hostSmtp = null;
//		Session session = null;
//		
//		// Recupero las propiedades de configuracion de correo
//		String fromSender = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
//		String smtpHost = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST);
//		String smtpDebug = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG);
//		String smtpAutenticacion = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION);
//		String smtpUser = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER);
//		String smtpPassword = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD);
//		
//		
//		try {			
//			
//			String senderAddress = null;
//			if (from == null || from.equals("")) {
//				senderAddress = fromSender;
//			} else {
//				log("from es distinto de null");			
//				senderAddress = from;
//			}
//
//			hostSmtp =smtpHost;
//			properties.put("mail.smtp.host", hostSmtp);
//			////////////AUTENTICACION ///////////////
//			
//			if(smtpAutenticacion!=null && new Boolean(smtpAutenticacion).booleanValue())
//			{
//				log("El envio de correo necesita autenticacion");				
//				properties.setProperty("mail.smtp.auth", "true"); 
//			}
//			
//			session = Session.getDefaultInstance(properties, null);
//			session.setDebug(new Boolean(smtpDebug).booleanValue());
//			MimeMessage msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress(senderAddress));			
//			msg.setRecipients(Message.RecipientType.TO, toAddress);
//			msg.setSubject(subject);
//			msg.setSentDate(new Date());
//			// Cuerpo del mensaje
//			MimeBodyPart mbp = new MimeBodyPart();			
//			mbp.setContent(body.toString(), "text/html");
//			multipart.addBodyPart(mbp);
//			msg.setContent(multipart);
//			if(smtpAutenticacion!=null && new Boolean(smtpAutenticacion).booleanValue())
//			{
//			Transport t = session.getTransport("smtp");
//			log("antes de enviar");
//			String usuario = smtpUser;
//			String password = smtpPassword;
//			t.connect(usuario,password);			
//			t.sendMessage(msg,msg.getAllRecipients());
//			log("despues de enviar el mensaje");			
//			}else
//			{
//				log("se envía el mensaje sin autenticacion");
//			    Transport.send(msg);
//			}
//			resultado = true;
//
//		} catch (Exception mex) {
//			logger.error("MailSender.send() error ["+mex+"]");			
//		}
//		return resultado;
//	}
	
	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}