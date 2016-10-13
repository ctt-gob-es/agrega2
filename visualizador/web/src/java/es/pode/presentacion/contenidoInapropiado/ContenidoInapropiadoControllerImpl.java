// license-header java merge-point
package es.pode.presentacion.contenidoInapropiado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ODEPublico;



public class ContenidoInapropiadoControllerImpl extends ContenidoInapropiadoController
{

	private static Logger logger = Logger.getLogger(ContenidoInapropiadoControllerImpl.class);
	
	
    public final void enviarMensaje(
    		ActionMapping mapping, 
    		EnviarMensajeForm form,
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	try{
	    	String servidor= AgregaPropertiesImpl.getInstance().getUrlNodo();
	    	String nombre="";
	    	String email="";
	    	
	    	if( LdapUserDetailsUtils.getNombreCompleto()!=null)
	    		nombre=LdapUserDetailsUtils.getNombreCompleto();
	    	if(LdapUserDetailsUtils.getMail()!=null)
	    		email=LdapUserDetailsUtils.getMail();
	    	
			VisualizadorSession sesion= this.getVisualizadorSession(request);
			String identificador=form.getIdentificador();
			OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
			
			
		   boolean contenidoCreado= this.getSrvContenidoInapropiadoService().crearContenidoInapropiado(
	    			identificador,
	    			odeSesion.getTituloOde(),
	    			Calendar.getInstance(),
	    			sesion.getUsuario(),
	    			form.getComentario(),
	    			AgregaProperties.PUBLICADO,
	    			odeSesion.getIdiomaCat());
			
			
			//Si el contenido inapropiado ha sido creado se manda el correo sino dev false y no se manda el correo
			if (contenidoCreado){
		    	String imagen= odeSesion.getUrlImagen() ;
				//Formamos la url de la imagen
		    	String hrefLogo = servidor ;
		    	String imagenLogo = servidor + "/" + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA);
		    	
		    	String urlFicha =  ODEPublico.urlFichaODEPublicado(identificador, odeSesion.getIdiomaCat());
			
		    	String[] publicadores =this.getSrvAdminUsuariosService().getEmailPublicadores();
		    	String[] administradores = this.getSrvAdminUsuariosService().getEmailAdmin();
		    	List<String> destinatarios= new ArrayList<String>();
		    	destinatarios.addAll( Arrays.asList( publicadores) );
		    	destinatarios.addAll( Arrays.asList( administradores));
		    	
		    	
	    		CorreoOdeVO correo = new CorreoOdeVO();
	    		correo.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
	    		
	    		correo.setComentario(form.getComentario());
	    		correo.setEmailRemitente(email);
	    		correo.setNombreRemitente(nombre);
	    		correo.setTo(destinatarios.toArray(new String[0]));
	    		
	    		correo.setHrefLogo(hrefLogo);
	    		correo.setUrlImagenLogo(imagenLogo);
	    		
	    		correo.setUrlImagen(imagen);
	    		correo.setUrlFicha(urlFicha);
	    		correo.setTituloOde(odeSesion.getTituloOde());
	    		
	    		
			    ResultadoEnvioCorreoVO resultado = this.getSrvCorreo().correoContenidoInapropiado(correo);
			    if("OK".equals(resultado.getResultado()) )
			    	logger.debug("El Correo de contenido inapropiado se envió con éxito " );
			    else
			    	logger.error("El Correo NO se ha enviado correctamente.");	
			    
			} else {
				logger.error("No se ha creado el contenido inapropiado. ");
			}
    	}
    	catch (Exception e) {
    		logger.error("error en COntenidoInapropiadoControllerImpl.enviarMensaje: " , e);
		}
    	
     }



}
