package es.pode.gestorCorreo.negocio.comun;

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

public class MailSenderThread extends Thread {

	private static Logger logger = Logger.getLogger(MailSenderThread.class);

	private String toAddress = null;

	private String subject = null;

	private StringBuffer body = null;

	private String from = null;

	private boolean isDone = false;
	private String frSender = null;
	private String smHost= null;
	private String smAutenticacion= null;
	private String smDebug= null;
	private String smUser= null;
	private String smPassword= null;
	
	public MailSenderThread(String to, String sub, StringBuffer bodyEmail, String fromEmail, String fromSender, String smtpHost, String smtpAutenticacion, String smtpDebug, String smtpUser, String smtpPassword ) {
		toAddress = to;
		subject = sub;
		body = bodyEmail;
		from = fromEmail;
		frSender=fromSender;
		smHost=smtpHost;
		smAutenticacion=smtpAutenticacion;
		smDebug=smtpDebug;
		smUser=smtpUser;
		smPassword=smtpPassword;
	}

	public void send(String toAddress, String subject, StringBuffer body, String from, String fromSender, String smtpHost, String smtpAutenticacion, String smtpDebug, String smtpUser, String smtpPassword)
	{

		if(logger.isDebugEnabled())logger.debug("Send email a la direccion <" + toAddress+">");

		MimeMultipart multipart = new MimeMultipart();
		Properties properties = new Properties();
		String hostSmtp = null;
		Session session = null;
		
		// Recupero las propiedades de configuracion de correo
//		String fromSender = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
//		String smtpHost = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST);
//		String smtpDebug = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG);
//		String smtpAutenticacion = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION);
//		String smtpUser = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER);
//		String smtpPassword = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD);
		
		try {
			//Cuando se de de alta un usuario el correo que envia el administrador tendrá el from del administrador que le ha dado
			//de alta, para ello habrá que coger de la sesión el usuario y obtener su email ¿Cómo sacamos de la sesión el usuario?
			String senderAddress = null;
			if (from == null) {
				senderAddress = fromSender;
			} else {
				if(logger.isDebugEnabled())logger.debug("from es distinto a null");
				senderAddress = from;
			}

			hostSmtp = smtpHost;
			properties.put("mail.smtp.host", hostSmtp);
			
			if(smtpAutenticacion!=null && (new Boolean(smtpAutenticacion).booleanValue()))
			{
				if(logger.isDebugEnabled())logger.debug("El envio de correo necesita autenticacion");
				properties.setProperty("mail.smtp.auth", "true"); 
			}
			session = Session.getDefaultInstance(properties, null);
			session.setDebug(new Boolean(smtpDebug).booleanValue());
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
			if(smtpAutenticacion!=null && (Boolean.parseBoolean(smtpAutenticacion)))
			{
				Transport t = session.getTransport("smtp");
				//if(logger.isDebugEnabled())logger.debug("antes de enviar");
				String usuario = smtpUser;
				String password =  smtpPassword;
				t.connect(usuario,password);
				//if(logger.isDebugEnabled())logger.debug("despues de conectarnos");
				t.sendMessage(msg,msg.getAllRecipients());
				//if(logger.isDebugEnabled())logger.debug("despues de enviar el mensage");
			}else
			{
			    if(logger.isDebugEnabled())logger.debug("Se envia el mensaje sin autenticacion");
			    Transport.send(msg);
			}
			

		} catch (Exception mex) {
			logger.error("MailSender.send() error " + mex);
			//throw mex;
		} finally {
			isDone = true;
			if(logger.isDebugEnabled())logger.debug("finaliza la ejecucion del hilo de envio de correo");
		}

	}

	public void run() { // Sobrecargando al metodo run

		while (!isDone) {

			this.send(toAddress, subject, body, from, frSender, smHost, smAutenticacion, smDebug, smUser, smPassword);

		}
	}

}