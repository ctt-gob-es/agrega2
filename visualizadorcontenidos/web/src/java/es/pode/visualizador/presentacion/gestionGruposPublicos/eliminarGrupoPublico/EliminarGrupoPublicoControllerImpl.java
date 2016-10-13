// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGrupoPublico;

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
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGrupoPublico.EliminarGrupoPublicoController
 */
public class EliminarGrupoPublicoControllerImpl extends EliminarGrupoPublicoController
{






	private static Logger logger = Logger.getLogger(EliminarGrupoPublicoControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGrupoPublico.EliminarGrupoPublicoController#obtenerGrupoPublico(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGrupoPublico.ObtenerGrupoPublicoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerGrupoPublico(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGrupoPublico.ObtenerGrupoPublicoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	       Long id=form.getId();
	       String nombre=form.getNombre();
	       logger.info("El id que recibimos es "+id+" y el nombre del grupo "+nombre);
    	}catch(Exception e){
    		logger.error("Error al obtener el grupo publico");
    	}
     }


    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGrupoPublico.EliminarGrupoPublicoController#eliminarGrupo(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.eliminarGrupoPublico.EliminarGrupoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarGrupo(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.eliminarGrupoPublico.EliminarGrupoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	       Long id=form.getId();
	       Long[] identificadores=new Long[1];
	       identificadores[0]=id;
	       String nombre=form.getNombre();
	       String usuario=LdapUserDetailsUtils.getUsuario();
	       ResultadoOperacionVO[] resultado = this.getSrvPerfilPublico().eliminarGrupoPublicoPorUsuario(identificadores, usuario);
	       if(resultado!=null && resultado.length>0){
	       	ResultadoOperacionVO vuelta=resultado[0];
	       	Boolean texto=vuelta.getResultado();
	       	if(texto.equals(Boolean.TRUE)){
					form.setResultado("OK");
				}
				else{
					form.setResultado("FALLO");
				}
	       }
    	}catch(Exception e){
    		logger.error("Error al eliminar el grupo publico");
    	}
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


	@Override
	public String obtenerVuelta(ActionMapping mapping, ObtenerVueltaForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String vuelta=form.getVuelta();
		String volver="Lista";
		if(vuelta!=null && !vuelta.equals("") && vuelta.equals("Itinerario")){
			volver="Itinerario";
		}
		return volver;
	}









}