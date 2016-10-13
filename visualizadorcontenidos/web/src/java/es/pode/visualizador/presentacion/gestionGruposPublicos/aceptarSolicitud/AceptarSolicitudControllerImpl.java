// license-header java merge-point
package es.pode.visualizador.presentacion.gestionGruposPublicos.aceptarSolicitud;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;



/**
 * @see es.pode.visualizador.presentacion.gestionGruposPublicos.aceptarSolicitud.AceptarSolicitudController
 */
public class AceptarSolicitudControllerImpl extends AceptarSolicitudController
{






	private static Logger logger = Logger.getLogger(AceptarSolicitudControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.aceptarSolicitud.AceptarSolicitudController#aceptarSolicitud(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.aceptarSolicitud.AceptarSolicitudForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void aceptarSolicitud(ActionMapping mapping, es.pode.visualizador.presentacion.gestionGruposPublicos.aceptarSolicitud.AceptarSolicitudForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try{
	    	Long id=form.getId();
	        String usuarioSolicitante=form.getUsuarioSolicitante();
	        logger.info("Se acepta la solicitud "+id+" del usuario "+usuarioSolicitante);
	        Boolean vuelta = this.getSrvPerfilPublico().aceptarSolicitudGrupo(id);
	        logger.info("Se ha aceptado"+vuelta.booleanValue());
	        if(vuelta.equals(Boolean.TRUE)){
				form.setResultado("OK");
			}
			else{
				form.setResultado("FALLO");
			}
    	}catch(Exception e){
    		logger.error("Error al aceptar la solicitud");
    	}
     }









}