// license-header java merge-point
package es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.EliminarContactoController
 */
public class EliminarContactoControllerImpl extends EliminarContactoController
{



	private static Logger logger = Logger.getLogger(EliminarContactoControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.EliminarContactoController#obtenerContacto(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.ObtenerContactoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerContacto(ActionMapping mapping, es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.ObtenerContactoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
            Long id=form.getId();
            String vuelta=form.getVuelta();
            String usuarioContacto=form.getUsuarioContacto();
            logger.info("El identificador y el usuario que hemos recibido son "+id+", "+usuarioContacto);
        	}catch(Exception e){
        		logger.error("Error al obtener el contacto");
        	}
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.EliminarContactoController#eliminarContacto(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.EliminarContactoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarContacto(ActionMapping mapping, es.pode.visualizador.presentacion.gestionContactosUsuario.eliminarContactos.EliminarContactoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
 	       Long id=form.getId();
 	       String usuarioContacto=form.getUsuarioContacto();
 	       String usuario=LdapUserDetailsUtils.getUsuario();
 	       Long[] idContactos=new Long[1];
 	       String recibirVuelta=form.getVuelta();
 	       idContactos[0]=id;
 	       ResultadoOperacionVO[] vuelta = this.getSrvPerfilPublico().eliminarContactoDeAgenda(idContactos, usuario);
 	       if(vuelta!=null && vuelta.length>0){
 	       	ResultadoOperacionVO resultado=vuelta[0];
 	       	Boolean texto=resultado.getResultado();
 	       	if(texto.equals(Boolean.TRUE)){
 					form.setResultado("OK");
 				}
 				else{
 					form.setResultado("FALLO");
 				}
 	       }else{
 	    	  form.setResultado("FALLO");
 	       }
     	}catch(Exception e){
     		logger.error("Error al eliminar el contacto");
     	}
     }






	public String obtenerVuelta(ActionMapping mapping, ObtenerVueltaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vuelta=form.getVuelta();
		String volver="Gestion";
		if(vuelta!=null && !vuelta.equals("") && vuelta.equals("Usuario")){
			volver="Usuario";
		}
		return volver;
	}







	@Override
	public String obtenerAccion(ActionMapping mapping, ObtenerAccionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accion="Cancelar";
	   	 try{
		     	// nothing to be done for this operation, there are no properties that can be set
		     	String actionAceptar=form.getAccionAceptar();
		     	String actionCancelar=form.getAccionCancelar();
		     	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);

	            ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);

		     	if((actionAceptar!=null && !actionAceptar.equals("") && actionAceptar.equals(i18n.getString("usuarios.aceptar")))){
		     		accion="Aceptar";
		     	}else if((actionCancelar!=null && !actionCancelar.equals("")&& actionCancelar.equals(i18n.getString("usuarios.cancelar")) )){
		     		accion="Cancelar";
		     	}
		     	logger.info("La accion que recibimos es "+accion);
	   	 }catch(Exception e){
	   		 logger.error("Error al obtener la accion "+accion);
	   	 }
	        return accion;
	}









}