package es.pode.gestorCorreo.negocio.comun;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;

public class MailSender {

	private static Logger log = Logger.getLogger(MailSenderThread.class);

	public MailSender() {

	}
	//En el caso de que el correo de destino (toAdress) solo sea uno va por aqui
	public boolean send(String toAddress, String subject, StringBuffer body,	
			String from, String fromSender, String smtpHost, String smtpAut, String smtpDebug, String smtpUser, String smtpPswd) throws Exception {
		return send(new String[]{toAddress},null,subject,body,from, fromSender,smtpHost, smtpAut, smtpDebug, smtpUser, smtpPswd);
	}

	//En el caso de que el correo de destino (toAdress) sea mas de uno va por aqui
	public boolean send(String[] toAddress, String subject, StringBuffer body,
			String from, String fromSender, String smtpHost, String smtpAut, String smtpDebug, String smtpUser, String smtpPswd) throws Exception {
		return send(toAddress,null,subject,body,from, fromSender,smtpHost, smtpAut, smtpDebug, smtpUser, smtpPswd);
	}		

		//En el caso de que en el correo se quiera incluir destinatarios en copia

	public boolean send(String[] toAddress,String[] ccAddress, String subject, StringBuffer body,
			String from, String fromSender, String smtpHost, String smtpAut, String smtpDebug, String smtpUser, String smtpPswd) throws Exception {

		if(log.isDebugEnabled())log.debug("Enviar email a la direccion <" + Arrays.toString(toAddress)+">");
		boolean resultado = false;
		MimeMultipart multipart = new MimeMultipart();
		Properties properties = new Properties();
		String hostSmtp = null;
		Session session = null;
		try {
			//Cuando se de de alta un usuario el correo que envia el administrador tendrá el from del administrador que le ha dado
			//de alta, para ello habrá que coger de la sesión el usuario y obtener su email ¿Cómo sacamos de la sesión el usuario?
			String senderAddress = null;
			if (from == null) {
//				senderAddress = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER);
				senderAddress = fromSender;
			} else {
				log.debug("from no es null");
				senderAddress = from;
			}

//			hostSmtp = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST);
			hostSmtp = smtpHost;
			properties.put("mail.smtp.host", hostSmtp);
			////////////AUTENTICACION ///////////////
			
//			String autenticacion = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION);
			String autenticacion = smtpAut;
			if(autenticacion != null && (new Boolean(autenticacion).booleanValue()))
			{
				log.debug("El envio de correo necesita autenticacion");
				properties.setProperty("mail.smtp.auth", "true"); 
			}
			
			session = Session.getDefaultInstance(properties, null);
//			session.setDebug(new Boolean(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG)).booleanValue());
			session.setDebug(new Boolean(smtpDebug).booleanValue());
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(senderAddress));
			//msg.setRecipients(Message.RecipientType.TO, toAddress);
			Address[] address = new InternetAddress[toAddress.length];
			for (int i=0;i<toAddress.length; i++){
				address[i]= new InternetAddress(toAddress[i]);
			}
			
			// Si tiene direcciones en copia se procesan y se incluyen en el mensaje
			Address[] addressCC = null;			
			
			if((ccAddress!=null) && (ccAddress.length>0))
			{
				addressCC = new InternetAddress[ccAddress.length];
				for (int i=0;i<ccAddress.length; i++){
					addressCC[i]= new InternetAddress(ccAddress[i]);
				}
				
				msg.setRecipients(Message.RecipientType.CC, addressCC);
			}
				
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// Cuerpo del mensaje
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(body.toString(), "text/html");
			multipart.addBodyPart(mbp);
			msg.setContent(multipart);
			if(Boolean.parseBoolean(autenticacion))
			{
				Transport t = session.getTransport("smtp");
				//log("antes de enviar");
//				String usuario = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER);
				String usuario = smtpUser;
//				String password = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD);
				String password = smtpPswd;
				t.connect(usuario,password);
				//log("despues de conectarnos");
				t.sendMessage(msg,msg.getAllRecipients());
				//log("despues de enviar el mensage");
			} else
			{
				if(log.isDebugEnabled())log.debug("Se envia el mensaje sin autenticacion");
			    Transport.send(msg);
			}
			resultado = true;

		} catch (Exception mex) {
			log.error("MailSender.send() error " + mex);
			throw mex;
		}
		return resultado;
	}

	
	
}