package es.pode.reputacion.mail;

import java.io.IOException;
import java.io.InputStream;
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

public class MailSender {

	private java.util.Properties pSpringProperties = null;
	
	private static Logger logger = Logger.getLogger(MailSender.class);

	public MailSender() {

	}

	public boolean send(String toAddress, String subject, StringBuffer body,
			String from) {

		logger.debug("Enviar email");
		logger.debug("Enviar email a la direccion ["+toAddress+"]");		
		boolean resultado = false;
		MimeMultipart multipart = new MimeMultipart();
		Properties properties = new Properties();
		String hostSmtp = null;
		Session session = null;
		try {
			
			//Cuando el contenido es inapropiado se envia un correo al administrador
			String senderAddress = null;
			if (from == null) {
				senderAddress = this.getPropertyValue("fromSender");
			} else {
				logger.debug("from es distinto de null");			
				senderAddress = from;
			}

			hostSmtp = this.getPropertyValue("hostSmtp");
			properties.put("mail.smtp.host", hostSmtp);
			////////////AUTENTICACION ///////////////
			
			if(new Boolean(this.getPropertyValue("autentication")).booleanValue())
			{
			logger.debug("El envio de correo necesita autenticacion");				
			properties.setProperty("mail.smtp.auth", "true"); 
			}
			
			session = Session.getDefaultInstance(properties, null);
			session.setDebug(new Boolean(this.getPropertyValue("debug")).booleanValue());
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
			if(new Boolean(this.getPropertyValue("autentication")).booleanValue())
			{
			Transport t = session.getTransport("smtp");
			logger.debug("antes de enviar");
			String usuario = this.getPropertyValue("userSmtp");
			String password =  this.getPropertyValue("passwordSmtp");
			t.connect(usuario,password);			
			t.sendMessage(msg,msg.getAllRecipients());
			logger.debug("despues de enviar el mensaje");			
			}else
			{
				logger.debug("se env�a el mensaje sin autenticacion");
			    Transport.send(msg);
			}
			resultado = true;

		} catch (Exception mex) {
			logger.error("MailSender.send() error ["+mex+"]");			
		}
		return resultado;
	}

	private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/agrega.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}	
	
	
}