// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.gestorCorreo.negocio.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.gestorCorreo.negocio.comun.MailSender;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.url.ImagenesAgrega;




/**
 * @see es.pode.gestorCorreo.negocio.servicios.SrvCorreo
 */

public class SrvCorreoImpl
    extends es.pode.gestorCorreo.negocio.servicios.SrvCorreoBase
{
	private static Logger log = Logger.getLogger(SrvCorreoImpl.class);
	private static String DEFAULT_BUNDLE = "correos-resources"; 
	
	private static String CORREO_BAJA_USUARIO="bajaUsuario.html";
	private static String CORREO_BAJA_USUARIO_NODO_TALLER="bajaUsuarioNodoTaller.html";
	private static String CORREO_BAJA_USUARIO_LDAP="bajaUsuarioLdap.html";
	private static String CORREO_ALTA_USUARIO_NODO_TALLER="altaUsuarioNodoTaller.html";
	private static String CORREO_ALTA_USUARIO="altaUsuario.html";
	private static String CORREO_ALTA_USUARIO_LDAP="altaUsuarioLdap.html";
	private static String CORREO_NUEVA_CLAVE="nuevaClave.html";
	private static String CORREO_NUEVA_CLAVE_LDAP="nuevaClaveLdap.html";
	private static String CORREO_ENVIAR_AMIGO="enviarAmigo.html";
	private static String CORREO_CONTENIDO_INAPROPIADO="contenidoInapropiado.html";
	private static String CORREO_CONTENIDO_INAPROPIADO_RECHAZAR="contenidoInapropiadoRechazar.html";
	private static String CORREO_SOLICITUD_BAJA_USUARIO="solicitudBajaUsuario.html";
	private static String CORREO_SOLICITUD_BAJA_USUARIO_LDAP="solicitudBajaUsuarioLdap.html";
	private static String CORREO_DESACTIVAR_USUARIO="desactivarUsuario.html";
	private static String CORREO_BAJA_GRUPO="bajaGrupo.html";
	private static String CORREO_COMENTARIO_ODE="comentarioODE.html";
	private static String CORREO_ENVIO_ODE_GRUPO="envioODEGrupo.html";
	private static String CORREO_PUBLICACION_ODE="publicacionODE.html";
	private static String CORREO_RECHAZO_ODE="rechazoODE.html";
	private static String CORREO_CONTACTO_PUBLICA_ODE="contactoPublicaODE.html";
	private static String CORREO_CONTACTO_AUTOPUBLICA_ODE="contactoAutopublicaODE.html";
	private static String CORREO_ENVIAR_AMIGO_SIN_COMENTARIO="enviarAmigoSinComentario.html"; //El correo de enviar a un amigo puede aparecer sin comentario.
	private static String CORREO_ALARMA_ZIP_OBSOLETO="enviarAlarmaZipObsoleto.html";
	private static String CORREO_ALARMA_FICHERO_OBSOLETO="enviarAlarmaFicheroObsoleto.html";
	private static String CORREO_ALARMA_ACTIVIDAD_NULA="enviarAlarmaActividadNula.html";
	private static String CORREO_ALARMA_DATO_ESTADISTICO_INCORRECTO="enviarAlarmaDatosEstadisticosIncorrectos.html";
	private static String CORREO_ALARMA_ACTIVIDAD_NEGATIVA="enviarAlarmaActividadNegativa.html";
	private static String CORREO_ALARMA_TAREA_PLANIFICADA="enviarAlarmaTareaPlanificada.html";
	private static String CORREO_ALARMA_UNIFICACION_METADATOS="enviarAlarmaUnificacionMetadatos.html";
	private static String CORREO_ALARMA_UNIFICACION_DESPUBLICADOS="enviarAlarmaUnificacionDespublicados.html";

	private static String INCIDENCIA_ZIP_OBSOLETO="INCIDENCIA_ZIP_OBSOLETO";
	private static String INCIDENCIA_FICHERO_OBSOLETO="INCIDENCIAFICHERO_OBSOLETO";
	private static String INCIDENCIA_ACTIVIDAD_NULA="INCIDENCIA_ACTIVIDAD_NULA";
	private static String INCIDENCIA_DATO_ESTADISTICO_INCORRECTO="INCIDENCIA_DATO_ESTADISTICO_INCORRECTO";
	private static String INCIDENCIA_ACTIVIDAD_NEGATIVA="INCIDENCIA_ACTIVIDAD_NEGATIVA";
	private static String INCIDENCIA_METADATOS_ODES_FEDERADOS="INCIDENCIA_METADATOS_ODES_FEDERADOS";
	private static String INCIDENCIA_DESPUBLICADOS_FEDERADOS="INCIDENCIA_DESPUBLICADOS_FEDERADOS";

	private SrvPropiedadService prop = null;
	/**
	 * Envia un correo recomendado el ODE a un amigo
	 * @param CorreoODEVO correoODEVO VO con datos específicos del ODE y con los datos de los correo
	 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
	 * @throws Exception
	 */
    protected es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO handleCorreoEnviarAmigo(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO)
        throws java.lang.Exception
        //Campos a rellenar
        //to: emails de los destinatarios del correo
		//from: quién envía el correo, este valor se recogerá del fichero agrega.properties 
		//idiomaCorreo:idioma en el que se envia el correo. Si no se rellena el correo se envío en el idioma por defecto de la plataforma
		//hrefLogo: enlace del logo
		//urlImagenLogo: url de la imagen del logo
		//urlFicha: url de la imagen de la ficha
		//tituloOde: el titulo del ODE
		//urlImagen: enlace de la imagen:
		//NombreDestinatario: nombre de los destinatarios separados por punto y coma. Este valor tiene que coincidir con los email de los destinatarios.
		//NombreRemitente: nombre del remitente que envía el correo
		//emailRemitente: email del que envia el correo
		//comentario: comentario del correo. Opcional si el usuario no ha introducido comentario este campo aparecerá vacío.
    {	 
    	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
        try{
        	
//        	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
        	if (correoOdeVO.getIdiomaCorreo()==null || correoOdeVO.getIdiomaCorreo().length()==0){
        		correoOdeVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
        	}
        	
    		ResourceBundle bundle = null;
    		StringBuffer texto=new StringBuffer();
    		
	    		log.debug("Correo recomendar a un amigo");
	    		//Plantilla que se va a utilizar
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		if((correoOdeVO.getComentario() == null) || (correoOdeVO.getComentario().length() == 0) || (correoOdeVO.getComentario() == " ") || (correoOdeVO.getComentario() == ""))
	    		{
	    			htmlFile.append(CORREO_ENVIAR_AMIGO_SIN_COMENTARIO);
	    			log.debug("Utilizo la plantilla sin comentario");
	    		}else
	    		{
	    			htmlFile.append(CORREO_ENVIAR_AMIGO);
	    			log.debug("Utilizo la plantilla con comentario");
	    		}
	    		

	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOdeVO.getIdiomaCorreo()));
	    		//obtenemos el asunto del correo
	    	 	String asunto = bundle.getString("texto.subject");
	    		//obtengo el texto de la plantilla
	    		
	    		String[] nombresDestinatariosArray = correoOdeVO.getNombreDestinatario().split(";");        	
	        	//Para cada destinatario se le envía un correo
	    		if (nombresDestinatariosArray.length == correoOdeVO.getTo().length){
		    		for(int i=0; i<correoOdeVO.getTo().length; i++ ){        		
		        		
		        		correoOdeVO.setNombreDestinatario(nombresDestinatariosArray[i]);
		        		//Obtenemos el texto del html
		        		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOdeVO.getIdiomaCorreo()),null,null,correoOdeVO,null);
		        		//la clase MailSender.java que será la encargada de enviar el correo
				    	MailSender mailSender = new MailSender();
			    		StringBuffer body= new StringBuffer(texto);
		        		if(mailSender.send(correoOdeVO.getTo()[i], asunto, body, correoOdeVO.getFrom(), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), 
		        				AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)));
		        			if (logger.isDebugEnabled())logger.debug("Envio correcto del email para el destinatario ["+correoOdeVO.getTo()[i]+"]");
		        		else
							if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente al destinatario ["+correoOdeVO.getTo()[i]+"]");
		        	}
		    		log.info("Se ha enviado el correo"); 
	    	
		    	resultadoEnvioCorreoVO.setResultado("OK");
		    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de enviar a un amigo se ha enviado correctamente");
		    	return resultadoEnvioCorreoVO;
	    		}
	    		
				log.warn("La cantidad de nombres de los destinatario y los correos han de ser iguales");
				resultadoEnvioCorreoVO.setResultado("KO");
				resultadoEnvioCorreoVO.setResultadoTexto("El correo de enviar a un amigo no se ha enviado correctamente");
				return resultadoEnvioCorreoVO;
				
	    }catch (Exception e){
	    	resultadoEnvioCorreoVO.setResultado("KO");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de enviar a un amigo no se ha enviado correctamente");
			log.error("El correo de enviar a un amigo no se ha enviado correctamente - " + e);
			
			return resultadoEnvioCorreoVO;
		}
    }

	/**
	 * Envia un correo avisando del contenido inapropiado de un ODE
	 * @param CorreoODEVO correoODEVO VO con datos específicos del ODE y con los datos de los correo
	 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
	 * @throws Exception
	 */
    protected es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO handleCorreoContenidoInapropiado(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO)
        throws java.lang.Exception
        //Campos a rellenar
		//to: destinatarios del correo
		//from: quién envía el correo se recogerá del agrega.properties (cau.agrega@indra.es) 
		//idiomaCorreo:idioma en el que se envia el correo.Si no se rellena se mandará en el idioma configurado por defecto en la plataforma.
		//hrefLogo: enlace del logo
		//urlImagenLogo: url de la imagen del logo
		//urlFicha: url de la imagen de la ficha
		//tituloOde: el titulo del ODE
		//urlImagen: enlace de la imagen:
		//nombreRemitente nombre de la persona que marca el contenido como inapropiado.
		//emailRemitente: email de la persona que marcado el contenido como inapropiado
		//comentario: comentario añadido al marcar el contenido como contenido inapropiado
		//valoracion: Valoración del ode, si no se pone ningún valor en el correo aparecerá la frase Sin valorar
		//comunidad: Comunidad en la que se marca el contenido inapropiado, si no se pone nada se mandará desde la comunidad configurada en los ficheros properties.
    {  	
    	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
    	try{
//        	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
        	if (correoODEVO.getIdiomaCorreo()==null || correoODEVO.getIdiomaCorreo().length()==0){
        		correoODEVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
        	}
    		ResourceBundle bundle = null;
    		StringBuffer texto=new StringBuffer();
	    		log.debug("Correo contenido inapropiado");
	    		//Plantilla que se va a utilizar
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_CONTENIDO_INAPROPIADO);
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoODEVO.getIdiomaCorreo()));
	    		//obtenemos el asunto del correo
	    	 	String asunto = bundle.getString("mandar.email.contenido.inapropiado.subject");
	    		//obtengo el texto de la plantilla   
	    	 	
	    	 	//Chequeamos el valor de la valoracion, si es null o vacio se pondrá "Sin valorar"
	    	 	
	    	 	if( (correoODEVO.getValoracion() == null) || (correoODEVO.getValoracion().length() == 0) || (correoODEVO.getValoracion() == "") || (correoODEVO.getValoracion() == " "))
	    	 	{
	    	 		log.debug("El solicitante no ha introducido valoracion le ponemos la etiqueta por defecto");
	    	 		correoODEVO.setValoracion("sinValorar");
	    	 	}
	    	 	
	         	texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoODEVO.getIdiomaCorreo()),null,null,correoODEVO,null);
	        	//la clase MailSender.java que será la encargada de enviar el correo
			   	MailSender mailSender = new MailSender();
		    	StringBuffer body= new StringBuffer(texto);
		    	//en mi caso los administradores son los k me pasen de adminusuarios
	        	if(mailSender.send(correoODEVO.getTo(), asunto, body, correoODEVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)))
	        		if (logger.isDebugEnabled())logger.debug("Envio correcto del email para todos los administradores");
	        	else
					if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente para todos los administradores");
		    	
	    	
	    	resultadoEnvioCorreoVO.setResultado("OK");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contenido inapropiado se ha enviado correctamente");
	    	return resultadoEnvioCorreoVO;
	    }catch (Exception e){
	    	resultadoEnvioCorreoVO.setResultado("KO");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contenido inapropiado no se ha enviado correctamente");
			log.error("El correo de contenido inapropiado no se ha enviado correctamente - " + e);
			//throw e;
			return resultadoEnvioCorreoVO;
		}
    }

	/**
	 * Envia un correo de Alta de un usuario
	 * @param CorreoUsuarioVO correoUsuarioVO VO con datos específicos del usuario y con los datos de los correo
	 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
	 * @throws Exception
	 */
    protected es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO handleAltaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO)
        throws java.lang.Exception
    {
	     //Este método tendrá que comprobar si se trata de un nodo taller o de un nodo con  ldap externo (ver los atributos correspondientes del VO CorreoUsuarioVO) según esto se cogerá una plantilla u otra.
		 //Una vez seleccione la plantilla tendrá que sustituir las cadenas por los códigos de los ficheros de internacionalización y las variables que cambian de un correo a otro como es el usuario
		 //Una vez se tenga la plantilla con todos los tokens sustituidos llamamos a la clase MailSender.java que será la encargada de enviar el correo, esta clase habrá que modificarla para que devuelva un VO con el resultado del envío (ResultadoEnvioCorreoVO).
		 //Hay que tener en cuenta las posibles excepciones que se pueden devolver en la clase MailSender, habrá que tenerlo todo entre un try catch.
		 //El Servicio SrvAdminUsuarios.handleAltaUsuario al llamar al servicio SrvCorreo tendrá que invocarlo entre un try catch.
		 
		 //Más tarde habrá que plantear llamar a este método desde SrvAdminUsuarios desde un hilo independiente para que no se quede esperando.
    	
        //Campos a rellenar
//      to: destinatarios del correo
//    	from: quién envía el correo cau.agrega@indra.es 
//    	idiomaCorreo:idioma en el que se envia el correo.
//      usuario: usuario que se da de alta
//      password: pasword de ese usuario
//      nodoTaller: si es nodoTaller
//      ldapExterno: si es Ldapexterno

    	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
//    	Si ldapexterno=nulo se pone un false
    	if (correoUsuarioVO.getLdapExterno()==null){
    		correoUsuarioVO.setLdapExterno(false);
    	}
//    	Si nodotaller=nulo se pone un false
    	if (correoUsuarioVO.getNodoTaller()==null){
    		correoUsuarioVO.setNodoTaller(false);
    	}
    	//Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoUsuarioVO.getIdiomaCorreo()==null || correoUsuarioVO.getIdiomaCorreo().length()==0){
    		correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
    	try{

    		ResourceBundle bundle = null;
	    	StringBuffer texto=null;
	    	//Si no es nodo taller
	    	if (Boolean.FALSE.equals(correoUsuarioVO.getNodoTaller()) && Boolean.FALSE.equals(correoUsuarioVO.getLdapExterno())){
	    		log.debug("Alta de ususario que no es nodo taller");
	    		//Se obtiene la plantilla
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_ALTA_USUARIO);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    		String asunto = bundle.getString("correo.altaUsuario.asunto");
	    	 	//obtengo el texto de la plantilla
	    		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
	    		texto=new StringBuffer();
				while (lenguaje.hasMoreTokens()){
					String idioma=lenguaje.nextToken();
					//Aqui construyo el texto de la plantilla en cada idioma
					texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
					texto.append("<br/><br/><br/><br/>");
				}
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
	    		log.info("Se ha enviado el correo de alta de usuario"); 
	    	}else if(Boolean.TRUE.equals(correoUsuarioVO.getNodoTaller()) && Boolean.FALSE.equals(correoUsuarioVO.getLdapExterno())) {
//	    		Si  es nodo taller
	    		log.debug("Alta de usuario que es nodo taller");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_ALTA_USUARIO_NODO_TALLER);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    		String asunto = bundle.getString("correoTaller.altaUsuario.asunto");
	    		//obtengo el texto de la plantilla
	    		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
	    		texto=new StringBuffer();
	    		while (lenguaje.hasMoreTokens()){
					String idioma=lenguaje.nextToken();
					//Aqui construyo el texto de la plantilla en cada idioma
					texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
					texto.append("<br/><br/><br/><br/>");
				}
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
	    		log.info("Se ha enviado el correo de alta de usuario");
	    	}
	    	//Si es Ldap
	    	if (Boolean.TRUE.equals(correoUsuarioVO.getLdapExterno())){
	    		log.debug("Alta en un servidor ldap externo");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_ALTA_USUARIO_LDAP);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    	 	String asunto=null;
	    		if (correoUsuarioVO.getNodoTaller() == false){
	    			asunto = bundle.getString("correo.altaUsuario.asunto");
	    		}else
	    			asunto = bundle.getString("correoTaller.altaUsuario.asunto");
	    		//obtengo el texto de la plantilla
	    		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoUsuarioVO.getIdiomaCorreo()),correoUsuarioVO,null,null,null);
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body,correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));	
	    		log.info("Se ha enviado el correo de alta de usuario");
	    	}
	    	resultadoEnvioCorreoVO.setResultado("OK");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de alta de usuario se ha mandado correctamente");
	    	return resultadoEnvioCorreoVO;
        }catch (Exception e){
        	resultadoEnvioCorreoVO.setResultado("KO");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de alta de usuario no se ha enviado correctamente");
			log.error("El correo de alta de usuario no se ha enviado correctamente - " + e);
			//throw e;
			return resultadoEnvioCorreoVO;
    	}
    }

	/**
	 * Envia un correo de Baja de un usuario
	 * @param CorreoUsuarioVO correoUsuarioVO VO con datos específicos del usuario y con los datos de los correo
	 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
	 * @throws Exception
	 */
    protected es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO handleBajaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO)
        throws java.lang.Exception
    {
        //Campos a rellenar
//      to: destinatarios del correo
//    	from: quién envía el correo cau.agrega@indra.es 
//    	idiomaCorreo:idioma en el que se envia el correo.
//      usuario: usuario que se da de baja
//      password: pasword de ese usuario
//      nodoTaller: si es nodoTaller
//      ldapExterno: si es Ldapexterno
    	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
    	  	try{
    	  		
    	  		
    		
    		ResourceBundle bundle = null;
	    	StringBuffer texto=null;
	    	//Si ldapexterno=nulo se pone un false
	    	if (correoUsuarioVO.getLdapExterno()==null){
	    		correoUsuarioVO.setLdapExterno(false);
	    	}
//	    	Si nodotaller=nulo se pone un false
	    	if (correoUsuarioVO.getNodoTaller()==null){
	    		correoUsuarioVO.setNodoTaller(false);
	    	}
//	    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
	    	if (correoUsuarioVO.getIdiomaCorreo()==null || correoUsuarioVO.getIdiomaCorreo().length()==0){
	    		correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
	    	}
//	    	Si no es nodo taller
	    	if (Boolean.FALSE.equals(correoUsuarioVO.getNodoTaller()) && Boolean.FALSE.equals(correoUsuarioVO.getLdapExterno())){
	    		log.debug("Baja de ususario que no es nodo taller");
//	    		AgregaProperties agregaProperties = AgregaPropertiesImpl.getInstance();
	    		
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_BAJA_USUARIO);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    		String asunto = bundle.getString("correo.bajaUsuario.asunto");
	    	 	//obtengo el texto de la plantilla
	    		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
	    		texto=new StringBuffer();
				while (lenguaje.hasMoreTokens()){
					String idioma=lenguaje.nextToken();
					//Aqui construyo el texto de la plantilla en cada idioma
					texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
					texto.append("<br/><br/><br/><br/>");
				}
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
	    		log.info("Se ha enviado el correo de baja de usuario"); 
	    	}else if (Boolean.TRUE.equals(correoUsuarioVO.getNodoTaller()) && Boolean.FALSE.equals(correoUsuarioVO.getLdapExterno())){
	    		//Si es nodo taller
	    		log.debug("Baja de ususario que es nodo taller");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_BAJA_USUARIO_NODO_TALLER);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    		String asunto = bundle.getString("correoTaller.bajaUsuario.asunto");
	    		//obtengo el texto de la plantilla
	    		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
	    		texto=new StringBuffer();
				while (lenguaje.hasMoreTokens()){
					String idioma=lenguaje.nextToken();
					//Aqui construyo el texto de la plantilla en cada idioma
					texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
					texto.append("<br/><br/><br/><br/>");
				}
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
	    		log.info("Se ha enviado el correo de baja de usuario"); 
	    	}
	    	//Si es Ldap
	    	if (Boolean.TRUE.equals(correoUsuarioVO.getLdapExterno())){
	    		log.debug("Baja en un servidor ldap externo");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_BAJA_USUARIO_LDAP);
//	    		obtenemos el asunto del correo
	    	 	String bundleFile = DEFAULT_BUNDLE;
	    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
	    		String asunto = bundle.getString("correo.solicitudBaja.asunto");
	    		//obtengo el texto de la plantilla
	    		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoUsuarioVO.getIdiomaCorreo()),correoUsuarioVO,null,null,null);
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
		    	//Enviamos un correo al administrador del ldap externo con
				// todos los usuarios que han sido realmente eliminados
		    	correoUsuarioVO.setEmailUsuario(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.ADMINLDAPEXTERNO));
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuarioVO.getTo(), asunto, body,correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));	
	    		log.info("Se ha enviado el correo de baja de usuario"); 
	    	}

	    	
	    	resultadoEnvioCorreoVO.setResultado("OK");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de baja de usuario se ha mandado correctamente");
	    	return resultadoEnvioCorreoVO;
    	}catch (Exception e){
    		resultadoEnvioCorreoVO.setResultado("KO");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de baja de usuario no se ha enviado correctamente");
			log.error("El correo de baja de usuario no se ha enviado correctamente " + e);
			//throw e;
			return resultadoEnvioCorreoVO;
    	}
    }

	/**
	 * Envia un correo de recuerdo de la contraseña de un usuario
	 * @param CorreoUsuarioVO correoUsuarioVO VO con datos específicos del usuario y con los datos de los correo
	 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
	 * @throws Exception
	 */
    protected es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO handleNuevaClave(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuario)
        throws java.lang.Exception
    {
        //Campos a rellenar
//      to: destinatarios del correo
//    	from: quién envía el correo cau.agrega@indra.es 
//    	idiomaCorreo:idioma en el que se envia el correo.
//      usuario: usuario que se da de alta
//      password: pasword de ese usuario
//    	nuevaClave: la nueva clave del usuario
//      ldapExterno: si es Ldapexterno
    	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
//    	Si ldapexterno=nulo se pone un false
    	if (correoUsuario.getLdapExterno()==null){
    		correoUsuario.setLdapExterno(false);
    	}
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoUsuario.getIdiomaCorreo()==null || correoUsuario.getIdiomaCorreo().length()==0){
    		correoUsuario.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
    	try{
    		ResourceBundle bundle = null;
	    	StringBuffer texto=null;
    	 	String bundleFile = DEFAULT_BUNDLE;
    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuario.getIdiomaCorreo()));
    		String asunto = bundle.getString("correo.nuevaClave.ldapExterno.asunto");
	    	//Si no es Ldap
    		if (correoUsuario.getLdapExterno() == false){
    			texto=new StringBuffer ();
	    		log.info("Cambio de contraseña en un servidor que no es ldap externo");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_NUEVA_CLAVE);

	    	 	//obtengo el texto de la plantilla
	    		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoUsuario.getIdiomaCorreo()), correoUsuario,null,null,null);
	    		
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuario.getTo(), asunto, body, correoUsuario.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
	    		log.info("Se ha enviado el correo de recuerdo de contraseña"); 
	    	}else{
	    		//Si es Ldap
	    		texto=new StringBuffer ();
	    		log.info("Cambio de contraseña en un servidor ldap externo");
	    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
	    		htmlFile.append(CORREO_NUEVA_CLAVE_LDAP);
	    		//obtengo el texto de la plantilla
	    		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoUsuario.getIdiomaCorreo()),correoUsuario,null,null,null);
//		    	la clase MailSender.java que será la encargada de enviar el correo
		    	MailSender mailSender = new MailSender();
	    		StringBuffer body= new StringBuffer(texto);
	    		mailSender.send(correoUsuario.getTo(), asunto, body,correoUsuario.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));	
	    		log.info("Se ha enviado el correo de recuerdo de contraseña"); 
	    	}
	    	resultadoEnvioCorreoVO.setResultado("OK");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de recuerdo de contraseña se ha mandado correctamente");
	    	return resultadoEnvioCorreoVO;
        }catch (Exception e){
    		resultadoEnvioCorreoVO.setResultado("KO");
	    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de recuerdo de contraseña no se ha enviado correctamente");
			log.error("El correo de recuerdo de contraseña no se ha enviado correctamente - " , e);
			//throw e;
			return resultadoEnvioCorreoVO;
    	}
    

    }
  
/**
 * Obtiene el texto de la plantilla HTML
 * @param htmlFile
 * @param locale
 * @param correoUsuarioVO
 * @param bundle
 * @param correoOdeVO
 * @return un String con el HTML ya construído
 * @throws Exception
 */  
private StringBuffer obtenerTextoHTML(
		String htmlFile, 
		Locale locale, 
		CorreoUsuarioVO correoUsuarioVO, 
		ResourceBundle bundle, 
		CorreoOdeVO correoOdeVO, 
		CorreoGrupoVO correoGrupoVO) 
throws Exception 
{

	String htmlKey ="_" + locale.getLanguage();
	if(logger.isDebugEnabled()) logger.debug("Locale obtenido -> <" + locale+">");
	
	if (locale==null){
		locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
	}
	

		StringBuffer result = null;

		String bundleFile = DEFAULT_BUNDLE+htmlKey;

			if(logger.isDebugEnabled()){ 
				logger.debug("HTML no cacheado para. Leo el fichero y lo proceso.");
			}

			if(locale!=null) {
				bundle = ResourceBundle.getBundle(bundleFile, locale);
			} else {
				bundle = ResourceBundle.getBundle(bundleFile);
			}
			
			java.io.File fHtmlFile = new java.io.File(htmlFile);
			if(logger.isDebugEnabled()) logger.debug("Fichero <" + htmlFile + "> exists= <" + fHtmlFile.exists() + ">; isFile = <" + fHtmlFile.isFile()+">");
			if(fHtmlFile.isFile()) {
				
				String rawHtmlCode = leeFichero(fHtmlFile);
				if(rawHtmlCode!=null) {
					if(bundle==null) {
						logger.warn("No se ha podido leer el bundle de traducciones <" + bundleFile+">");
					} else {
						// Obtengo todas la claves i18n del fichero de internacionalizacion de logos
						Enumeration<String> keys = bundle.getKeys();
						if(logger.isDebugEnabled()) logger.debug("Keys obtenidas de <" + bundleFile + ">: <" + keys+">");
						
						while(keys.hasMoreElements()) {
							String key = keys.nextElement();
							String value = bundle.getString(key);
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${" + key + "}> por <" + value+">");
											
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${"+key+"}\\E", value);
						}
					}
//					propiedades url
					StringBuffer url = new StringBuffer();
					url.append("http://").append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST)).append(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO));//respectivametne host y subdominio
//					 Comprobamos si url es nulo o vacio.
					if (url!=null && url.toString().length()>0){
						if(logger.isDebugEnabled()) logger.debug("Reemplazando ${url} por " + url.toString());
						rawHtmlCode = rawHtmlCode.replaceAll("\\Q${url}\\E", url.toString());
					}
					
					if (correoUsuarioVO!=null){
//					 propiedades usuario
						String usuario=null;
						StringBuffer html=new StringBuffer();
						usuario = correoUsuarioVO.getUsuario();
						
						String urlImagenLogo="";
						urlImagenLogo=correoUsuarioVO.getUrlImagenLogo();
	//					 Comprobamos si nombreDestinatario es nulo o vacio.
						if (!(urlImagenLogo == null) && urlImagenLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlImagenLogo}> por <" + urlImagenLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}else{
							urlImagenLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}
						
//						propiedades hrefLogo
						String hrefLogo="";
						hrefLogo=correoUsuarioVO.getHrefLogo();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(hrefLogo == null) && hrefLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${hrefLogo}> por <" + hrefLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}else{
							hrefLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}
						
						// Comprobamos si usuario es nulo o vacio.
						if (usuario.indexOf(",")!=-1){
							if (!(usuario == null) & usuario.length()>0){
								StringTokenizer usuarios= new StringTokenizer (usuario,",");
								while (usuarios.hasMoreTokens()){
									String token=usuarios.nextToken();
									//Aqui construyo la parte de html en la que irán los usuarios
									html.append(token).append("<br>");
								}
								if(logger.isDebugEnabled()) logger.debug("Reemplazando <${usuario}> por <" + html.toString()+">");
								rawHtmlCode = rawHtmlCode.replaceAll("\\Q${usuario}\\E", html.toString());
							}
						}else{
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${usuario}> por <" + usuario+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${usuario}\\E", usuario);
						}
							
						
//						propiedades clave original
						String claveOriginal="";
						claveOriginal=correoUsuarioVO.getPassword();
						if(logger.isDebugEnabled())logger.debug("Nuestra nueva original es: <" +claveOriginal+">");
	//					 Comprobamos si clave es nulo o vacio.
						if (!(claveOriginal == null) && claveOriginal.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${claveOriginal}> por <" + claveOriginal+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${claveOriginal}\\E", claveOriginal);
						}else{
							claveOriginal="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${claveOriginal}\\E", claveOriginal);
						}
						
						if(logger.isDebugEnabled())logger.debug("Nuestro valor del usuario es <"+usuario+">");
	//					propiedades clave nueva
						String clave="";
						clave=correoUsuarioVO.getNuevaClave();
						if(logger.isDebugEnabled())logger.debug("Nuestra nueva clave es: <" +clave+">");
	//					 Comprobamos si clave es nulo o vacio.
						if (!(clave == null) && clave.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${clave}> por <" + clave+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${clave}\\E", clave);
						}else{
							clave="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${clave}\\E", clave);
						}
						
	//					propiedades email
						String email="";
						email=correoUsuarioVO.getEmailUsuario();
						if(logger.isDebugEnabled())logger.debug("El mail es <"+email+">");
	//					 Comprobamos si email es nulo o vacio.
						if (!(email == null) && email.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${email}> por <" + email+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${email}\\E", email);
						}else{
							email="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${email}\\E", email);
						}
					}
					
					if (correoOdeVO!=null){
	//					propiedades urlImagenLogo
						String urlImagenLogo="";
						urlImagenLogo=correoOdeVO.getUrlImagenLogo();
	//					 Comprobamos si nombreDestinatario es nulo o vacio.
						if (!(urlImagenLogo == null) && urlImagenLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlImagenLogo}> por <" + urlImagenLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}else{
							urlImagenLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}
	//					propiedades nombreDestinatario
						String nombreDestinatario="";
						nombreDestinatario=correoOdeVO.getNombreDestinatario();
	//					 Comprobamos si nombreDestinatario es nulo o vacio.
						if (!(nombreDestinatario == null) && nombreDestinatario.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${nombreDestinatario}> por <" + nombreDestinatario+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreDestinatario}\\E", nombreDestinatario);
						}else{
							nombreDestinatario="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreDestinatario}\\E", nombreDestinatario);
						}
						
	//					propiedades nombreDestinatario
						String nombreRemitente="";
						nombreRemitente=correoOdeVO.getNombreRemitente();
						
	//					 Comprobamos si nombreRemitente es nulo o vacio.
						if (!(nombreRemitente == null) && nombreRemitente.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${nombreRemitente}> por <" + nombreRemitente+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreRemitente}\\E", nombreRemitente);
						}else{
							nombreRemitente="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreRemitente}\\E", nombreRemitente);
						}
					
	//					propiedades emailRemitente
						String emailRemitente="";
						emailRemitente=correoOdeVO.getEmailRemitente();
	//					 Comprobamos si emailRemitente es nulo o vacio.
						if (!(emailRemitente == null) && emailRemitente.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${emailRemitente}> por <" + emailRemitente+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${emailRemitente}\\E", emailRemitente);
						}else{
							emailRemitente="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${emailRemitente}\\E", emailRemitente);
						}
						

	//					propiedades urlImagen
						String urlImagen="";
						urlImagen=correoOdeVO.getUrlImagen();
	//					 Comprobamos si urlImagen es nulo o vacio.
						if (!(urlImagen == null) && urlImagen.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlImagen}> por <" + urlImagen+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagen}\\E", urlImagen);
						}else{
							urlImagen="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagen}\\E", urlImagen);
						}
						
	//					propiedades tituloODE
						String tituloODE="";
						tituloODE=correoOdeVO.getTituloOde();		
	//					 Comprobamos si tituloODE es nulo o vacio.
						if (!(tituloODE == null) && tituloODE.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${tituloODE}> por <" + tituloODE+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${tituloODE}\\E", tituloODE);
						}else{
							tituloODE="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${tituloODE}\\E", tituloODE);
						}
						
	//					propiedades urlFicha
						String urlFicha="";
						urlFicha=correoOdeVO.getUrlFicha();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(urlFicha == null) && urlFicha.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlFicha}> por <" + urlFicha+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlFicha}\\E", urlFicha);
						}else{
							urlFicha="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlFicha}\\E", urlFicha);
						}
						
	//					propiedades hrefLogo
						String hrefLogo="";
						hrefLogo=correoOdeVO.getHrefLogo();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(hrefLogo == null) && hrefLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${hrefLogo}> por <" + hrefLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}else{
							hrefLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}
						
	//					propiedades resumen
						String resumen="";
						resumen=correoOdeVO.getComentario();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(resumen == null) && resumen.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${resumen}> por <" + resumen+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${resumen}\\E", resumen);
						}else{
							resumen="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${resumen}\\E", resumen);
						}
						
						//					propiedades valoración
						String valoracion="";
						valoracion=correoOdeVO.getValoracion();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(valoracion == null) && valoracion.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${valoracion}> por <" + valoracion+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${valoracion}\\E", valoracion);
						}else{
							resumen="Sin valorar";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${valoracion}\\E", valoracion);
						}
						
//						propiedades comunidad
						StringBuffer comunidad = new StringBuffer();
						comunidad.append(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NODO));//nodo
//						 Comprobamos si url es nulo o vacio.
						if (!(comunidad == null) && comunidad.toString().length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${comunidad}> por <" + comunidad.toString()+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${comunidad}\\E", comunidad.toString());
						}
						
//						propiedades usuario
						String usuario="";
						usuario=correoOdeVO.getUsuario();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if (!(usuario == null) && usuario.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${usuario}> por <" + usuario+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${usuario}\\E", usuario);
						}else{
							usuario="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${usuario}\\E", usuario);
						}
						
						String nombreCompleto = correoOdeVO.getNombreRemitente();
						//					 Comprobamos si nombreCompleto es nulo o vacio.
						if (!(nombreCompleto == null) && nombreCompleto.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${nombreCompleto}> por <" + nombreCompleto+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreCompleto}\\E", nombreCompleto);
						}else{
							nombreCompleto="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreCompleto}\\E", nombreCompleto);
						}
						
						String getMail=correoOdeVO.getEmailRemitente();
//						 Comprobamos si nombreCompleto es nulo o vacio.
						if (!(getMail == null) && getMail.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${getMail}> por <" + getMail+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${getMail}\\E", getMail);
						}else{
							getMail="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${getMail}\\E", getMail);
						}
					}
//					propiedades nodo
					StringBuffer nodo = new StringBuffer();
					nodo.append(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.NODO));
//					 Comprobamos si url es nulo o vacio.
					if ((nodo != null) && nodo.toString().length()>0){
						if(logger.isDebugEnabled()) logger.debug("Reemplazando <${nodo}> por <" + nodo.toString()+">");
						rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nodo}\\E", nodo.toString());
					}
					
					if (correoGrupoVO!=null){
//						propiedades nombreGrupo
						String nombreGrupo="";
						nombreGrupo=correoGrupoVO.getNombreGrupo();		
	//					 Comprobamos si tituloODE es nulo o vacio.
						if (!(nombreGrupo == null) && nombreGrupo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${nombreGrupo}> por <" + nombreGrupo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreGrupo}\\E", nombreGrupo);
						}else{
							nombreGrupo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${nombreGrupo}\\E", nombreGrupo);
						}
						
						
	//					propiedades urlImagenLogo
						String urlImagenLogo="";
						urlImagenLogo=correoGrupoVO.getUrlImagenLogo();
	//					 Comprobamos si nombreDestinatario es nulo o vacio.
						if ((!(urlImagenLogo == null)) && urlImagenLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlImagenLogo}> por <" + urlImagenLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}else{
							urlImagenLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagenLogo}\\E", urlImagenLogo);
						}
						
						//					propiedades urlImagen
						String urlImagen="";
						urlImagen=correoGrupoVO.getUrlImagen();
	//					 Comprobamos si urlImagen es nulo o vacio.
						if ((!(urlImagen == null)) && urlImagen.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlImagen}> por <" + urlImagen+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagen}\\E", urlImagen);
						}else{
							urlImagen="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlImagen}\\E", urlImagen);
						}
						
	//					propiedades tituloODE
						String tituloODE="";
						tituloODE=correoGrupoVO.getTituloOde();		
	//					 Comprobamos si tituloODE es nulo o vacio.
						if ((!(tituloODE == null)) && tituloODE.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${tituloODE}> por <" + tituloODE+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${tituloODE}\\E", tituloODE);
						}else{
							tituloODE="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${tituloODE}\\E", tituloODE);
						}
						
	//					propiedades urlFicha
						String urlFicha="";
						urlFicha=correoGrupoVO.getUrlFicha();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if ((!(urlFicha == null)) && urlFicha.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${urlFicha}> por <" + urlFicha+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlFicha}\\E", urlFicha);
						}else{
							urlFicha="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${urlFicha}\\E", urlFicha);
						}
						
	//					propiedades hrefLogo
						String hrefLogo="";
						hrefLogo=correoGrupoVO.getHrefLogo();
	//					 Comprobamos si urlFicha es nulo o vacio.
						if ((!(hrefLogo == null)) && hrefLogo.length()>0){
							if(logger.isDebugEnabled()) logger.debug("Reemplazando <${hrefLogo}> por <" + hrefLogo+">");
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}else{
							hrefLogo="";
							rawHtmlCode = rawHtmlCode.replaceAll("\\Q${hrefLogo}\\E", hrefLogo);
						}
						
						

					}

					result = new StringBuffer(rawHtmlCode);
					logger.debug("Resultado de procesar HTML de correos:\n"+result);
				} else { 
					logger.warn("El fichero " + htmlFile + " no existe o no ha podido ser leido");
				}
				
			} else {
				logger.warn("No se ha podido encontrar el HTML con los textos correos: " + htmlFile);
			}
		return result;
	}

/**
 * Se encarga de leer el fichero que le pasamos
 * @param fichero
 * @return String
 * @throws Exception
 */
private String leeFichero(java.io.File fichero) throws Exception {
	String result = null;
	StringBuffer buffer = new StringBuffer();
	BufferedReader reader = new BufferedReader(new FileReader(fichero));
	if(log.isDebugEnabled()) log.debug("Valor de reader: <" + reader+">");
	if(reader!=null) {
		String line = reader.readLine();
		while(line!=null) {
			buffer.append(line).append("\n");
			line = reader.readLine();
		}
		if(buffer.length()>0) result = buffer.toString();
	}
	
	try {
		reader.close();
	} catch (Exception e) {
		if(log.isDebugEnabled())log.debug("No se ha podido cerrar el Stream");
	}
	
	return result;
}

/**
 * Envia un correo de solicitud de baja de un usuario
 * @param CorreoUsuarioVO correoUsuarioVO VO con datos específicos del usuario y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleSolicitudBajaUsuario(CorreoUsuarioVO correoUsuarioVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
        //Campos a rellenar
//      to: destinatarios del correo
//    	from: quién envía el correo cau.agrega@indra.es 
//    	idiomaCorreo:idioma en el que se envia el correo.
//      usuario: usuario que se da de baja
//      ldapExterno: si es Ldapexterno
		
//		Si ldapexterno=nulo se pone un false
    	if (correoUsuarioVO.getLdapExterno()==null){
    		correoUsuarioVO.setLdapExterno(false);
    	}
//    	Si nodotaller=nulo se pone un false
    	if (correoUsuarioVO.getNodoTaller()==null){
    		correoUsuarioVO.setNodoTaller(false);
    	}
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoUsuarioVO.getIdiomaCorreo()==null || correoUsuarioVO.getIdiomaCorreo().length()==0){
    		correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
    	
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Solicitud de baja de ususario que no es nodo taller");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_SOLICITUD_BAJA_USUARIO);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.bajaUsuario.asunto");
//	 	obtengo el texto de la plantilla
		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
		texto=new StringBuffer();
		while (lenguaje.hasMoreTokens()){
			String idioma=lenguaje.nextToken();
			//Aqui construyo el texto de la plantilla en cada idioma
			texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
			texto.append("<br/><br/><br/><br/>");
		}
		
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de solicitud de baja de usuario"); 
		if (Boolean.TRUE.equals(correoUsuarioVO.getLdapExterno())){
    		log.debug("Solicitud de baja de ususario que no es nodo taller");
    		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
    		htmlFile.append(CORREO_SOLICITUD_BAJA_USUARIO_LDAP);
//    		obtenemos el asunto del correo
    	 	bundleFile = DEFAULT_BUNDLE;
    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
    		asunto = bundle.getString("correo.solicitudBaja.asunto");
    	 	//obtengo el texto de la plantilla
    		correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoUsuarioVO.getIdiomaCorreo()), correoUsuarioVO,null,null,null);
//	    	la clase MailSender.java que será la encargada de enviar el correo
	    	mailSender = new MailSender();
	    	body= new StringBuffer(texto);
	    	correoUsuarioVO.setEmailUsuario((ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.ADMINLDAPEXTERNO)));
    		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
    		log.info("Se ha enviado el correo de baja de usuario"); 
   	}
	    	
    	
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de solicitud de baja se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de solicitud de baja no se ha enviado correctamente");
		log.error("El correo de solicitud de baja no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo de desactivacion de un usuario
 * @param CorreoUsuarioVO correoUsuarioVO VO con datos específicos del usuario y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleDesactivarUsuario(CorreoUsuarioVO correoUsuarioVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
        //Campos a rellenar
//      to: destinatarios del correo
//    	from: quién envía el correo cau.agrega@indra.es 
//    	idiomaCorreo:idioma en el que se envia el correo.
//      usuario: usuario que se da de baja

		
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoUsuarioVO.getIdiomaCorreo()==null || correoUsuarioVO.getIdiomaCorreo().length()==0){
    		correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Desactivación de un usuario");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_DESACTIVAR_USUARIO);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoUsuarioVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.desactivacionUsuario.asunto");
//	 	obtengo el texto de la plantilla
		StringTokenizer lenguaje= new StringTokenizer (correoUsuarioVO.getIdiomaCorreo(),",");
		texto=new StringBuffer();
		while (lenguaje.hasMoreTokens()){
			String idioma=lenguaje.nextToken();
			//Aqui construyo el texto de la plantilla en cada idioma
			texto.append(obtenerTextoHTML(htmlFile.toString(), new Locale(idioma), correoUsuarioVO,null,null,null));
			texto.append("<br/><br/><br/><br/>");
		}
		
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoUsuarioVO.getTo(), asunto, body, correoUsuarioVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de desactivacion de un usuario"); 
		
	    	
    	
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de desactivacion de un usuario se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de desactivacion de un usuario no se ha enviado correctamente");
		log.error("El correo de desactivacion de un usuiario no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo de baja  de un grupo
 * @param CorreoGrupoVO correoGrupoVO VO con datos específicos del grupo y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleBajaGrupo(CorreoGrupoVO correoGrupoVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
        //Campos a rellenar
		//to: destinatarios del correo
		//from: quién envía el correo cau.agrega@indra.es 
		//idiomaCorreo:idioma en el que se envia el correo.
		//nombreGrupo: el nombre del grupo
		

//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoGrupoVO.getIdiomaCorreo()==null || correoGrupoVO.getIdiomaCorreo().length()==0){
    		correoGrupoVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Email de baja de un grupo");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_BAJA_GRUPO);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoGrupoVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.bajaGrupo.asunto");
//	 	obtengo el texto de la plantilla
		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoGrupoVO.getIdiomaCorreo()), null,null,null,correoGrupoVO);
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoGrupoVO.getTo(), asunto, body, correoGrupoVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de eliminacion de grupo"); 
	  	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de baja de un usuario de un grupo se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de baja de un usuario de un grupo no se ha enviado correctamente");
		log.error("El correo de baja de un usuario de un grupo no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso de comentario de un ODE
 * @param CorreoOdeVO correoOdeVO VO con datos específicos del ODE y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleComentarioODE(CorreoOdeVO correoOdeVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
		 //Campos a rellenar
		//hrefLogo  Url a la que enlazará el logo de Agrega
		//urlImagenLogo  Url donde se encuentra el logo de Agrega
		//tituloOde  Título del ode sobre el que se envía el correo
		//urlFicha  Url de la ficha del ode sobre el que se envía el correo
		//tituloODe: Es el titulo del ODE
		//nombreDestinatario  Nombre del destinatario del correo.
		//urlImagen  Url de la imagen representativa del ode
		//comentario  Comentario sobre el ode.
		//to  destinatarios, email con los destinatarios del correo.
		//from  Persona a la que se envia el correo
		//idiomaCorreo  idioma en el que se traducirá el correo, si no se introduce ninguno se internacionalizará en el idioma por defecto de la plataforma.
		//usuario  Usuario que comenta el ODE
		
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoOdeVO.getIdiomaCorreo()==null || correoOdeVO.getIdiomaCorreo().length()==0){
    		correoOdeVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Comentario de ODE");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_COMENTARIO_ODE);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOdeVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.comentarioODE.asunto");
//	 	obtengo el texto de la plantilla
		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOdeVO.getIdiomaCorreo()), null,null,correoOdeVO,null);
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoOdeVO.getTo(), asunto, body, correoOdeVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de comentario de ODE"); 
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de comentario de ODE se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de comentario de ODE no se ha enviado correctamente");
		log.error("El correo de comentario de ODE no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso del envio de un ODE a un grupo
 * @param CorreoGrupoVO correoGrupoVO VO con datos específicos del grupo y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleEnvioODEGrupo(CorreoGrupoVO correoGrupoVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
		  //Campos a rellenar
		//to: destinatarios del correo, serán los emails de los destinatarios
		//from: quién envía el correo cau.agrega@indra.es 
		//idiomaCorreo:idioma en el que se envia el correo.
		//hrefLogo: enlace del logo
		//urlImagenLogo: url de la imagen del logo
		//urlFicha: url de la imagen de la ficha
		//tituloOde: el titulo del ODE
		//urlImagen: enlace de la imagen:
		//nombreGrupo: el nombre del grupo
		
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoGrupoVO.getIdiomaCorreo()==null || correoGrupoVO.getIdiomaCorreo().length()==0){
    		correoGrupoVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Envío de un ODE a un grupo");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_ENVIO_ODE_GRUPO);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoGrupoVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.envioGrupo.asunto");
//	 	obtengo el texto de la plantilla
		//Para cada destinatario se le envía un correo.
		if (correoGrupoVO.getTo().length>0){
			for(int i=0; i<correoGrupoVO.getTo().length; i++ ){
				logger.debug("Estoy enviando el correo a: <"+ correoGrupoVO.getTo().length+">");
//				Obtenemos el texto del html
				texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoGrupoVO.getIdiomaCorreo()), null,null,null,correoGrupoVO);
//    			La clase MailSender.java que será la encargada de enviar el correo
				mailSender = new MailSender();
				body= new StringBuffer(texto);
				if(mailSender.send(correoGrupoVO.getTo()[i], asunto, body, correoGrupoVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)))
					if (logger.isDebugEnabled())logger.debug("Envio correcto del email para el destinatario ["+correoGrupoVO.getTo()[i]+"]");
	        	else
					if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente al destinatario ["+correoGrupoVO.getTo()[i]+"]");
	        	}
	    	}
		log.info("Se ha enviado el correo de contacto envia un ODE a un grupo, a ["+ correoGrupoVO.getTo().length+"] destinatarios");
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de envio de un ODE a un grupo se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de envio de un ODE a un grupo no se ha enviado correctamente");
		log.error("El correo de envio de un ODE a un grupo no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso de la publicación de un ODE
 * @param correoOdeVO correoOdeVO VO con datos específicos del ODE que publica y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handlePublicacionODE(CorreoOdeVO correoODEVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
	//hrefLogo  Url a la que enlazará el logo de Agrega
	//urlImagenLogo  Url donde se encuentra el logo de Agrega
	//tituloOde  Título del ode sobre el que se envía el correo
	//urlFicha  Url de la ficha del ode sobre el que se envía el correo
	//urlImagen  Url de la imagen representativa del ode
	//to  destinatarios
	//from  Persona a la que se envia el correo
	//idiomaCorreo  idioma en el que irá el correo, si no se introduce ningún valor se enviará en el idioma por defecto de la plataforma
		
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoODEVO.getIdiomaCorreo()==null || correoODEVO.getIdiomaCorreo().length()==0){
    		correoODEVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Publicación de un ODE");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_PUBLICACION_ODE);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoODEVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.publicarODE.asunto");
//	 	obtengo el texto de la plantilla
		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoODEVO.getIdiomaCorreo()), null,null,correoODEVO,null);
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoODEVO.getTo(), asunto, body, correoODEVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de publicación de un ODE"); 
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo  de publicación de un ODE se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de publicación de un ODE  no se ha enviado correctamente");
		log.error("El correo de publicación de un ODE  no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso del rechazo de un ODE
 * @param correoOdeVO correoODEVO VO con datos específicos del ODE que publica y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleRechazoODE(CorreoOdeVO correoODEVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
		//hrefLogo  Url a la que enlazará el logo de Agrega
		//urlImagenLogo  Url donde se encuentra el logo de Agrega
		//tituloOde  Título del ode sobre el que se envía el correo
		//comentario  Comentario introducido al rechazar el ode.
		//to  destinatarios
		//from  Persona a la que se envia el correo
		//idiomaCorreo  idioma en el que irá el correo, si no se introduce ningún valor se enviará en el idioma por defecto de la plataforma
		
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoODEVO.getIdiomaCorreo()==null || correoODEVO.getIdiomaCorreo().length()==0){
    		correoODEVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		MailSender mailSender=null;
		StringBuffer body=null;
		
		log.debug("Email de rechazo de un ODE a un grupo");
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_RECHAZO_ODE);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoODEVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.rechazoODE.asunto");
//	 	obtengo el texto de la plantilla
		texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoODEVO.getIdiomaCorreo()), null,null,correoODEVO,null);
//    	la clase MailSender.java que será la encargada de enviar el correo
    	mailSender = new MailSender();
		body= new StringBuffer(texto);
		mailSender.send(correoODEVO.getTo(), asunto, body, correoODEVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD));
		log.info("Se ha enviado el correo de rechazo de ODE a un grupo"); 
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de rechazo de un ODE se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de rechazo de un ODE a un grupo no se ha enviado correctamente");
		log.error("El correo de rechazo de un ODE no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso de que un contacto publique un ODE
 * @param correoOdeVO correoODEVO VO con datos específicos del ODE que publica y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
protected ResultadoEnvioCorreoVO handleContactoPublicaODE(CorreoOdeVO correoOdeVO) throws Exception {
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
		//hrefLogo  Url a la que enlazará el logo de Agrega
		//urlImagenLogo  Url donde se encuentra el logo de Agrega
		//tituloOde  Título del ode sobre el que se envía el correo
		//urlFicha  Url de la ficha del ode sobre el que se envía el correo
		//urlImagen  Url de la imagen representativa del ode
		//to  destinatarios del correo.
		//from  Persona que envía el correo
		//idiomaCorreo  idioma en el que irá el correo
		//usuario  Contacto que publica el ODE
	
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoOdeVO.getIdiomaCorreo()==null || correoOdeVO.getIdiomaCorreo().length()==0){
    		correoOdeVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		StringBuffer htmlFile=null;
		String bundleFile=null;
		String asunto=null;
		 
		
		/*
		 * TRAZAS DE CONTACTO PUBLICA ODE
		 */
		
		log.debug("correoOdeVO <"+correoOdeVO.getComentario()+">");
		log.debug("correoOdeVO.getHrefLogo() <"+correoOdeVO.getHrefLogo()+">");
		log.debug("correoOdeVO.getEmailRemitente() <"+correoOdeVO.getEmailRemitente()+">");
		log.debug("correoOdeVO.getTituloOde() <"+correoOdeVO.getTituloOde()+">");
		log.debug("correoOdeVO.getFrom() <"+correoOdeVO.getFrom()+">");
		log.debug("Email de contacto publica un ODE");
		
		htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
		htmlFile.append(CORREO_CONTACTO_PUBLICA_ODE);
//		obtenemos el asunto del correo
	 	bundleFile = DEFAULT_BUNDLE;
	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOdeVO.getIdiomaCorreo()));
		asunto = bundle.getString("correo.contactoPublicaODE.asunto");
//	 	obtengo el texto de la plantilla
		//Para cada destinatario se le envía un correo
		if (correoOdeVO.getTo().length>0){
			for(int i=0; i<correoOdeVO.getTo().length; i++ ){        		
				logger.debug("Estoy enviando el correo a: ["+ correoOdeVO.getTo().length+"]");
		        //Obtenemos el texto del html
				texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOdeVO.getIdiomaCorreo()),null,null,correoOdeVO,null);
//				La clase MailSender.java que será la encargada de enviar el correo
				MailSender mailSender = new MailSender();
				StringBuffer body= new StringBuffer(texto);
				if(mailSender.send(correoOdeVO.getTo()[i], asunto, body, correoOdeVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)))
        			if (logger.isDebugEnabled())logger.debug("Envio correcto del email para el destinatario ["+correoOdeVO.getTo()[i]+"]");
        		else
					if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente al destinatario ["+correoOdeVO.getTo()[i]+"]");
        	}
    	}
		log.info("Se ha enviado el correo de contacto publica un ODE a ["+ (correoOdeVO.getTo()!=null?correoOdeVO.getTo().length:0)+"] destinatarios"); 
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contacto publica un ODE se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contacto publica un ODE no se ha enviado correctamente");
		log.error("El correo de contacto publica un ODE no se ha enviado correctamente - ", e);
//		throw e;
		return resultadoEnvioCorreoVO;
	}
}






@Override
	protected ResultadoEnvioCorreoVO handleCorreoRechazoContenidoInapropiado(
		CorreoOdeVO correoOdeVO) 
	throws Exception 
    //Campos a rellenar
	//to: destinatarios del correo
	//from: quién envía el correo se recogerá del agrega.properties (cau.agrega@indra.es) 
	//idiomaCorreo:idioma en el que se envia el correo.Si no se rellena se mandará en el idioma configurado por defecto en la plataforma.
	//hrefLogo: enlace del logo
	//urlImagenLogo: url de la imagen del logo
	//urlFicha: url de la imagen de la ficha
	//tituloOde: el titulo del ODE
	//urlImagen: enlace de la imagen:
	//nombreRemitente nombre de la persona que marca el contenido como inapropiado.
	//emailRemitente: email de la persona que marcado el contenido como inapropiado
	//comentario: comentario añadido al marcar el contenido como contenido inapropiado
	//valoracion: Valoración del ode, si no se pone ningún valor en el correo aparecerá la frase Sin valorar
	//comunidad: Comunidad en la que se marca el contenido inapropiado, si no se pone nada se mandará desde la comunidad configurada en los ficheros properties.
{  	
	ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
	try{
//    	Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
    	if (correoOdeVO.getIdiomaCorreo()==null || correoOdeVO.getIdiomaCorreo().length()==0){
    		correoOdeVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    	}
		ResourceBundle bundle = null;
		StringBuffer texto=new StringBuffer();
		
    		log.debug("Correo contenido inapropiado");
    		//Plantilla que se va a utilizar
    		StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
    		htmlFile.append(CORREO_CONTENIDO_INAPROPIADO_RECHAZAR);
    	 	String bundleFile = DEFAULT_BUNDLE;
    	 	bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOdeVO.getIdiomaCorreo()));
    		//obtenemos el asunto del correo
    	 	String asunto = bundle.getString("mandar.email.contenido.inapropiado.rechazar.subject");
    		//obtengo el texto de la plantilla   
    	 	
    	 	//Chequeamos el valor de la valoracion, si es null o vacio se pondrá "Sin valorar"
    	 	
    	 	if( (correoOdeVO.getValoracion() == null) || (correoOdeVO.getValoracion().length() == 0) || (correoOdeVO.getValoracion() == "") || (correoOdeVO.getValoracion() == " "))
    	 	{
    	 		log.debug("El solicitante no ha introducido valoracion le ponemos la etiqueta por defecto");
    	 		correoOdeVO.setValoracion("sinValorar");
    	 	}

         	texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOdeVO.getIdiomaCorreo()),null,null,correoOdeVO,null);

	    	//la clase MailSender.java que será la encargada de enviar el correo
		   	MailSender mailSender = new MailSender();
	    	
	    	//en mi caso los administradores son los k me pasen de adminusuarios
        	if(mailSender.send(correoOdeVO.getTo(), asunto, texto , correoOdeVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)))
        		if (logger.isDebugEnabled())logger.debug("Envio correcto del email para todos los administradores");
        	else
				if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente para todos los administradores");
	    	
    	
    	resultadoEnvioCorreoVO.setResultado("OK");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contenido inapropiado se ha enviado correctamente");
    	return resultadoEnvioCorreoVO;
    }catch (Exception e){
    	resultadoEnvioCorreoVO.setResultado("KO");
    	resultadoEnvioCorreoVO.setResultadoTexto("El correo de contenido inapropiado no se ha enviado correctamente");
		log.error("El correo de contenido inapropiado no se ha enviado correctamente - ", e);
		//throw e;
		return resultadoEnvioCorreoVO;
	}
}

/**
 * Envia un correo en el caso de que un contacto autopublique un ODE
 * @param correoOdeVO correoODEVO VO con datos específicos del ODE que se autopublica y con los datos de los correo
 * @return ResultadoEnvioCorreoVO con los datos de como ha sido el envío
 * @throws Exception
 */
	protected ResultadoEnvioCorreoVO handleContactoAutopublicaODE(CorreoOdeVO correoOdeVO) throws Exception {
//		Similar al contacoPublicaODE --> variamos la plantilla
		
		ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
		try{
			//hrefLogo  Url a la que enlazará el logo de Agrega
			//urlImagenLogo  Url donde se encuentra el logo de Agrega
			//tituloOde  Título del ode sobre el que se envía el correo
			//urlFicha  Url de la ficha del ode sobre el que se envía el correo
			//urlImagen  Url de la imagen representativa del ode
			//to  destinatarios del correo.
			//from  Persona que envía el correo
			//idiomaCorreo  idioma en el que irá el correo
			//usuario  Contacto que publica el ODE
		
			//  Si el idioma es nulo, cogemos el Idioma por defecto de la plataforma
	    	if (correoOdeVO.getIdiomaCorreo()==null || correoOdeVO.getIdiomaCorreo().length()==0){
	    		correoOdeVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
	    	}
			ResourceBundle bundle = null;
			StringBuffer texto=new StringBuffer();
			StringBuffer htmlFile=null;
			String bundleFile=null;
			String asunto=null;
					
			/*
			 * TRAZAS DE CONTACTO PUBLICA ODE
			 */
			log.debug("correoOdeVO <"+correoOdeVO.getComentario()+">");
			log.debug("correoOdeVO.getHrefLogo() <"+correoOdeVO.getHrefLogo()+">");
			log.debug("correoOdeVO.getEmailRemitente() <"+correoOdeVO.getEmailRemitente()+">");
			log.debug("correoOdeVO.getTituloOde() <"+correoOdeVO.getTituloOde()+">");
			log.debug("correoOdeVO.getFrom() <"+correoOdeVO.getFrom()+">");
			log.debug("correoOdeVO.getTo()[0] <"+(correoOdeVO.getTo())[0]+">");
			
			
			log.debug("Email de contacto autopublica un ODE");
			htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));
			htmlFile.append(CORREO_CONTACTO_AUTOPUBLICA_ODE);
			//		obtenemos el asunto del correo
			bundleFile = DEFAULT_BUNDLE;
			bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOdeVO.getIdiomaCorreo()));
			asunto = bundle.getString("correo.contactoPublicaODE.asunto");
			//	 	obtengo el texto de la plantilla
			//Para cada destinatario se le envía un correo
			if (correoOdeVO.getTo().length>0){
				for(int i=0; i<correoOdeVO.getTo().length; i++ ){        		
					logger.debug("Estoy enviando el correo de ode autopublicado a: ["+ correoOdeVO.getTo().length+"]");
					//Obtenemos el texto del html
					texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOdeVO.getIdiomaCorreo()),null,null,correoOdeVO,null);
					//				La clase MailSender.java que será la encargada de enviar el correo
					MailSender mailSender = new MailSender();
					StringBuffer body= new StringBuffer(texto);
					if(mailSender.send(correoOdeVO.getTo()[i], asunto, body, correoOdeVO.getFrom(), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)))
						if (logger.isDebugEnabled())logger.debug("Envio correcto del email para el destinatario ["+correoOdeVO.getTo()[i]+"]");
						else
							if (logger.isDebugEnabled())logger.debug("El email no se ha enviado correctamente al destinatario ["+correoOdeVO.getTo()[i]+"]");
				}
			}
			log.info("Se ha enviado el correo de contacto autopublica un ODE a ["+ correoOdeVO.getTo().length+"] destinatarios"); 
			resultadoEnvioCorreoVO.setResultado("OK");
			resultadoEnvioCorreoVO.setResultadoTexto("El correo de contacto autopublica un ODE se ha enviado correctamente");
			return resultadoEnvioCorreoVO;
		}catch (Exception e){
			resultadoEnvioCorreoVO.setResultado("KO");
			resultadoEnvioCorreoVO.setResultadoTexto("El correo de contacto autopublica un ODE no se ha enviado correctamente");
			log.error("El correo de contacto autopublica un ODE no se ha enviado correctamente - ", e);
			return resultadoEnvioCorreoVO;
		}
	}

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}

	
	protected ResultadoEnvioCorreoVO handleEnviarCorreoIncidencia(String listaDestinatarios, String listaEnCopia, String tipoIncidencia) throws Exception {
		
		ResultadoEnvioCorreoVO resultadoEnvioCorreoVO = new ResultadoEnvioCorreoVO();
		CorreoOdeVO correoOde = new CorreoOdeVO();
		try {

			if (log.isDebugEnabled())
				log.debug("Correo alarma de incidencia : " + tipoIncidencia + " a : " + listaDestinatarios + " en copia : " + listaEnCopia);

			correoOde.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			
			// Cargamos el logo de Agrega para mostrarlo en el correo
			String imagenLogoAgrega = ImagenesAgrega.urlImagenLogoAgrega();
			correoOde.setUrlImagenLogo(imagenLogoAgrega);

			ResourceBundle bundle = null;
			StringBuffer texto = new StringBuffer();

			// Plantilla que se va a utilizar
			StringBuffer htmlFile = new StringBuffer(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_PLANTILLAS_CORREO));

			if (tipoIncidencia.equals(INCIDENCIA_ZIP_OBSOLETO)) 		
				htmlFile.append(CORREO_ALARMA_ZIP_OBSOLETO);
			else if (tipoIncidencia.equals(INCIDENCIA_FICHERO_OBSOLETO))
				htmlFile.append(CORREO_ALARMA_FICHERO_OBSOLETO);
			else if (tipoIncidencia.equals(INCIDENCIA_ACTIVIDAD_NULA))
				htmlFile.append(CORREO_ALARMA_ACTIVIDAD_NULA);
			else if (tipoIncidencia.equals(INCIDENCIA_DATO_ESTADISTICO_INCORRECTO))
				htmlFile.append(CORREO_ALARMA_DATO_ESTADISTICO_INCORRECTO);
			else if (tipoIncidencia.equals(INCIDENCIA_ACTIVIDAD_NEGATIVA))
				htmlFile.append(CORREO_ALARMA_ACTIVIDAD_NEGATIVA);
			else if (tipoIncidencia.equals(INCIDENCIA_METADATOS_ODES_FEDERADOS)) 		
				htmlFile.append(CORREO_ALARMA_UNIFICACION_METADATOS);
			else if (tipoIncidencia.equals(INCIDENCIA_DESPUBLICADOS_FEDERADOS))
				htmlFile.append(CORREO_ALARMA_UNIFICACION_DESPUBLICADOS);	
			else
				htmlFile.append(CORREO_ALARMA_TAREA_PLANIFICADA);
			
			if (log.isDebugEnabled())
				log.debug("Utilizo la plantilla de incidencia :" + htmlFile);

			String bundleFile = DEFAULT_BUNDLE;
			bundle = ResourceBundle.getBundle(bundleFile, new Locale(correoOde.getIdiomaCorreo()));
			// obtenemos el asunto del correo
			String asunto = bundle.getString("correo.alarma.subject");
			
			String descNodo = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_CCAA);
			
			asunto=asunto + " " + tipoIncidencia + " en " + descNodo;
			String[] correoDestinatariosArray = null;
			String[] correoDestinatariosCCArray = null;
			
			if (listaDestinatarios!=null && !listaDestinatarios.equals(""))
				correoDestinatariosArray = listaDestinatarios.split(";");						
			
			if (listaEnCopia!=null && !listaEnCopia.equals(""))
				correoDestinatariosCCArray = listaEnCopia.split(";");							
					
			// Obtenemos el texto del html
			texto = obtenerTextoHTML(htmlFile.toString(), new Locale(correoOde.getIdiomaCorreo()), null, null, correoOde, null);

			// la clase MailSender.java que será la encargada de enviar el correo
			MailSender mailSender = new MailSender();
			StringBuffer body = new StringBuffer(texto);
			
			if (mailSender.send(correoDestinatariosArray,correoDestinatariosCCArray, asunto, body,
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER), 
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER),
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_HOST), 
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_AUTENTICATION),
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_DEBUG), 
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_USER),
					AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SMTP_PASSWD)));
			
			resultadoEnvioCorreoVO.setResultado("OK");
			resultadoEnvioCorreoVO.setResultadoTexto("El correo de alarma de incidencia se ha enviado correctamente");
			
			return resultadoEnvioCorreoVO;

		} catch (Exception e) {
			resultadoEnvioCorreoVO.setResultado("KO");
			resultadoEnvioCorreoVO.setResultadoTexto("El correo de alarma de incidencia  no se ha enviado correctamente");
			log.error("El correo de alarma de incidencia  no se ha enviado correctamente - "+ e);

			return resultadoEnvioCorreoVO;
		}
	}
	
	
}