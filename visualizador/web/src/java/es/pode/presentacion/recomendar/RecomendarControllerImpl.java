// license-header java merge-point
package es.pode.presentacion.recomendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.auditoria.registrar.RegistroExterno;
import es.pode.soporte.seguridad.captcha.CaptchaServiceSingleton;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.presentacion.recomendar.RecomendarController
 */
public class RecomendarControllerImpl extends RecomendarController
{



	private static Logger logger = Logger.getLogger(RecomendarControllerImpl.class);
	public final static String ERROR_REGISTRAR_BBDD = "enviar.email.amigo.error.registrar.baseDatos";

    /**
     * @see es.pode.presentacion.enviarMail.EnviarMailController#enviarMensaje(org.apache.struts.action.ActionMapping, es.pode.presentacion.enviarMail.EnviarMensajeForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	public final void enviarMensaje(
    		ActionMapping mapping, 
    		EnviarMensajeForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {

    	try{
        	boolean validCaptcha =false;
        	//Validamos captcha
        	try {
    			validCaptcha = CaptchaServiceSingleton.getInstance().validateResponseForID(request.getSession().getId(), form.getCaptchaInput());
    		} catch (CaptchaServiceException e) {
    			//should not happen, may be thrown if the id is not valid
    			logger.warn("Excepcion en comprobacion de captcha: ", e);
    			validCaptcha=false;
    		}
        	if(!validCaptcha) {
		    	logger.error("El Correo NO se ha enviado por que el captcha introducido es erroneo.");	
				throw new Exception("NO SE HA ENVIADO");
        	}
    		
	    	String servidor= AgregaPropertiesImpl.getInstance().getUrlNodo();
	    	String nombre= form.getNombreRemitente();
	    	String email= form.getDireccionRemitente();
	    	
			VisualizadorSession sesion= this.getVisualizadorSession(request);
			String identificador = form.getIdentificador();
			//TODO
			logger.debug("****************Identificador es "+identificador);
			OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);

	    	String imagen= odeSesion.getUrlImagen() ;
			//Formamos la url de la imagen
	    	String hrefLogo = servidor ;
	    	String imagenLogo = servidor + "/" + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA);
	    	
	    	String urlFicha = ODEPublico.urlFichaODEPublicado(identificador, odeSesion.getIdiomaCat());

	    	String[] destinatariosArray = form.getDireccionDestinatario().split(";");
	    	
    		CorreoOdeVO correo = new CorreoOdeVO();
    		correo.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
    		
    		correo.setComentario(form.getMensaje());
    		correo.setEmailRemitente(email);
    		correo.setNombreRemitente(nombre);
    		correo.setTo(destinatariosArray);
    		correo.setNombreDestinatario(form.getNombreDestinatario());
    		
    		correo.setHrefLogo(hrefLogo);
    		correo.setUrlImagenLogo(imagenLogo);
    		
    		correo.setUrlImagen(imagen);
    		correo.setUrlFicha(urlFicha);
    		correo.setTituloOde(odeSesion.getTituloOde());

    		
		    ResultadoEnvioCorreoVO resultado = this.getSrvCorreo().correoEnviarAmigo(correo);
		    if("OK".equals(resultado.getResultado()) )
		    	logger.debug("El Correo de contenido inapropiado se envió con éxito " );
			else {
		    	logger.error("El Correo NO se ha enviado correctamente.");	
				throw new Exception("NO SE HA ENVIADO");
			}
		    
	        //Se almacena en la base de datos el envio para que este accesible en auditoria. Los odes mas enviados.
	        logger.debug("Se almacena en la BBDD el envio del ode con identificador en el idioma["+odeSesion.getIdiomaCat()+"]");
	       
	        if (!RegistroExterno.operacionEnviarCorreo(identificador, odeSesion.getIdiomaCat()))
	        	saveErrorMessage(request, ERROR_REGISTRAR_BBDD);
	        else
	        	logger.debug("El envio del correo se ha registrado correctamente en la base de datos");

    	}
    	catch (Exception e) {
    		logger.error(e);
    		throw e;
		}
    	
    	
    }


}