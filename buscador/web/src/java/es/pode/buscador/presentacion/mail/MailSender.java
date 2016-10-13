package es.pode.buscador.presentacion.mail;

//import org.apache.log4j.Logger;

public class MailSender {

//	private java.util.Properties pSpringProperties = null;
	
//	private static Logger logger = Logger.getLogger(MailSender.class);

	public MailSender() {

	}

//	public boolean send(String toAddress, String subject, StringBuffer body,
//			String from) {
//
//		if(logger.isDebugEnabled())logger.debug("Enviar email a la direccion ["+toAddress+"]");		
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
//				//if(logger.isDebugEnabled())logger.debug("from no null ni vacio");			
//				senderAddress = from;
//			}
//
//			hostSmtp =smtpHost;
//			properties.put("mail.smtp.host", hostSmtp);
//			////////////AUTENTICACION ///////////////
//			
//			if(smtpAutenticacion!=null && new Boolean(smtpAutenticacion).booleanValue())
//			{
//				if(logger.isDebugEnabled())logger.debug("El envio de correo necesita autenticacion");				
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
//			//if(logger.isDebugEnabled())logger.debug("antes de enviar");
//			String usuario = smtpUser;
//			String password = smtpPassword;
//			t.connect(usuario,password);			
//			t.sendMessage(msg,msg.getAllRecipients());
//			//if(logger.isDebugEnabled())logger.debug("despues de enviar el mensaje");			
//			}else
//			{
//				if(logger.isDebugEnabled())logger.debug("se envía el mensaje sin autenticacion");
//			    Transport.send(msg);
//			}
//			resultado = true;
//
//		} catch (Exception mex) {
//			logger.error("MailSender.send() error ["+mex+"]");			
//		}
//		return resultado;
//	}

}