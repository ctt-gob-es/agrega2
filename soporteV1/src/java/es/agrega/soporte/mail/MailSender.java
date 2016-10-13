/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.agrega.soporte.mail;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public boolean send(String toAddress, String subject, StringBuffer body,
			String from) {

		logger.debug("Enviar email a la direccion ["+toAddress+"]");		
		boolean resultado = false;
		MimeMultipart multipart = new MimeMultipart();
		Properties properties = new Properties();
		String hostSmtp = null;
		Session session = null;
		try {			
			
			String senderAddress = null;
			if (from == null || from.equals("")) {
				senderAddress = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
			} else {
				logger.debug("'from' no es null");			
				senderAddress = from;
			}

			hostSmtp = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST);
			properties.put("mail.smtp.host", hostSmtp);
			////////////AUTENTICACION ///////////////
			
			if(new Boolean(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION)).booleanValue())
			{
			logger.debug("El envio de correo necesita autenticacion");				
			properties.setProperty("mail.smtp.auth", "true"); 
			}
			
			session = Session.getDefaultInstance(properties, null);
			session.setDebug(Boolean.parseBoolean(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG)));
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress));			
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// Cuerpo del mensaje
			MimeBodyPart mbp = new MimeBodyPart();			
			mbp.setContent(body.toString(), "text/html");
			multipart.addBodyPart(mbp);
			msg.setContent(multipart);
			if(Boolean.valueOf(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION)))
			{
			Transport t = session.getTransport("smtp");

			String usuario = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER);
			String password =  AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD);
			t.connect(usuario,password);			
			t.sendMessage(msg,msg.getAllRecipients());
		
			}else
			{
				logger.debug("se envía el mensaje sin autenticacion");
			    Transport.send(msg);
			}
			resultado = true;

		} catch (Exception mex) {
			logger.error("MailSender.send() error <"+mex+">");			
		}
		return resultado;
	}

	
    public static Boolean validaEmail(String email) 
    throws Exception{
		Boolean resultado = Boolean.TRUE;
		
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]([A-Za-z0-9.-_])*@[A-Za-z0-9]([A-Za-z0-9\\.\\-\\_])*.([A-Za-z0-9])+$");
		Matcher matcher = pattern.matcher(email);			
		
		if (matcher.find()) {
			resultado= Boolean.TRUE;
		} else {
			resultado = Boolean.FALSE;
		}
		return resultado;
		
    }	  	   
	
    private String changeAccents(String s){
		try {
			s = s.replaceAll( "Á", "&Aacute;" );
			s = s.replaceAll( "É", "&Eacute;" );
			s = s.replaceAll( "Í", "&Iacute;" );
			s = s.replaceAll( "Ó", "&Oacute;" );
			s = s.replaceAll( "Ú", "&Uacute;" );
			s = s.replaceAll( "á", "&aacute;" );
			s = s.replaceAll( "é", "&eacute;" );
			s = s.replaceAll( "í", "&iacute;" );
			s = s.replaceAll( "ó", "&oacute;" );
			s = s.replaceAll( "ú", "&uacute;" );
			
			s = s.replaceAll( "Â", "&Acirc;" );
			s = s.replaceAll( "Ê", "&Ecirc;" );
			s = s.replaceAll( "Î", "&Icirc;" );
			s = s.replaceAll( "Ô", "&Ocirc;" );
			s = s.replaceAll( "Û", "&Ucirc;" );
			s = s.replaceAll( "â", "&acirc;" );
			s = s.replaceAll( "ê", "&ecirc;" );
			s = s.replaceAll( "î", "&icirc;" );
			s = s.replaceAll( "ô", "&ocirc;" );
			s = s.replaceAll( "û", "&ucirc;" );
			
			s = s.replaceAll( "À", "&Agrave;" );
			s = s.replaceAll( "È", "&Egrave;" );
			s = s.replaceAll( "Ì", "&Igrave;" );
			s = s.replaceAll( "Ò", "&Ograve;" );
			s = s.replaceAll( "Ù", "&Ugrave;" );
			s = s.replaceAll( "à", "&agrave;" );
			s = s.replaceAll( "è", "&egrave;" );
			s = s.replaceAll( "ì", "&igrave;" );
			s = s.replaceAll( "ò", "&ograve;" );
			s = s.replaceAll( "ù", "&ugrave;" );
			
			s = s.replaceAll( "Ç", "&Ccedil;" );
			s = s.replaceAll( "ç", "&ccedil;" ); 
			
			return s;
		} catch ( Exception ex ) {
			logger.error("MailSender: Error cambiando acentos para cadena="+s, ex);
			return "";
		}
		
		
	}
    
	public boolean sendHtml(
			String toAddress, 
			String subject, 
			StringBuffer body,
			String from)
	throws Exception
	{
		return send(toAddress, subject, new StringBuffer(changeAccents(body.toString())), from);
	}

	
}
