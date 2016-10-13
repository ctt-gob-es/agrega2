// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.CancelarSolicitudController
 */
public class CancelarSolicitudControllerImpl extends CancelarSolicitudController
{



	private static Logger logger = Logger.getLogger(CancelarSolicitudControllerImpl.class);


    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.CancelarSolicitudController#obtenerSolicitud(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.ObtenerSolicitudForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerSolicitud(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.ObtenerSolicitudForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	 Long id=form.getId();
	         String grupo=form.getGrupo();
	         String usuarioSolicitante=form.getUsuarioSolicitante();
	         logger.info("El id que recibimos es "+id+" y el nombre del grupo "+grupo+" para eliminar la solicitud del usuario "+usuarioSolicitante);
    	}catch(Exception e){
    		logger.error("Error al obtener la solicitud");
    	}
       
     }







    /**
     * @see es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.CancelarSolicitudController#cancelarSolicitud(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.CancelarSolicitudForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cancelarSolicitud(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.cancelarSolicitud.CancelarSolicitudForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	Long id=form.getId();
		    String usuarioSolicitante=form.getUsuarioSolicitante();
		    logger.info("Se acepta la solicitud "+id+" del usuario "+usuarioSolicitante);
		    Boolean vuelta = this.getSrvPerfilPublico().cancelarSolicitudGrupo(id);
		    logger.info("Se ha aceptado"+vuelta.booleanValue());
			if(vuelta.equals(Boolean.TRUE)){
				form.setResultado("OK");
			}
			else{
				form.setResultado("FALLO");
			}
    	}catch(Exception e){
    		logger.error("Error al cancelar la solicitud");
    	}
     }









}