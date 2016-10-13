// license-header java merge-point
package es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.ResultadoOperacionAlbumVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;



/**
 * @see es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.EliminarAlbumController
 */
public class EliminarAlbumControllerImpl extends EliminarAlbumController
{

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(EliminarAlbumController.class);
	private final String OBJETOS_PERSONALES = "objetosPersonales";
	private final String ODES_POR_ALBUM = "odesPorAlbum";

	public final static String SIN_ERRORES = "0.0";


    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.EliminarAlbumController#eliminarAlbum(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.EliminarAlbumForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final ResultadoOperacionAlbumVO eliminarAlbum(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.EliminarAlbumForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {	
    	if(logger.isDebugEnabled())	logger.debug("Eliminando el album["+ form.getIdAlbum()+"]");
    	ResultadoOperacionAlbumVO resultadoAlbum = new ResultadoOperacionAlbumVO();
    	try{
    		//Si el álbum que vamos a eliminar tiene ODEs, desasociamos esos ODEs del album.
    		TransicionVO[] odes = this.getSrvAlbumService().obtenOdesPorAlbum(form.getIdAlbum());
    		if(odes!= null && odes.length>0)    		{
    			for (int i = 0; i < odes.length; i++) {
    				this.getSrvAlbumService().desasociarODEAlbum(odes[i].getIdODE());
				}
    		}
    		//El álbum no tiene odes, lo podemos eliminar.
    		resultadoAlbum= this.getSrvAlbumService().bajaAlbum(form.getIdAlbum());
    		if(SIN_ERRORES.equals(resultadoAlbum.getIdResultado())){
    			if(logger.isDebugEnabled()) logger.debug("Se ha dado de baja el album: ["+form.getIdAlbum()+"]");
    			form.setExitoFracaso(true);
				form.setTexto(resultadoAlbum.getDescripcion());
    		}
    		else{
//    			 Si la eliminación del álbum no ha ido bien, entendemos que ha habido un error
				logger.error("Error eliminando el álbum: [" + resultadoAlbum.getAlbum().getId() + "] de titulo: [" + resultadoAlbum.getAlbum().getTitulo()+ "].");
				form.setExitoFracaso(false);
				form.setTexto(resultadoAlbum.getDescripcion());
    		}
    	}catch (Exception ex) {
    		logger.error("ERROR (excepción) eliminando el álbum con identificador[" + form.getIdAlbum()+ "] ", ex);
    		form.setExitoFracaso(false);
    		if(!(resultadoAlbum.getDescripcion().equals(SIN_ERRORES))){
    			form.setTexto(resultadoAlbum.getDescripcion());
    		}
		}
      return new ResultadoOperacionAlbumVO();  	
     }



    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.EliminarAlbumController#comprobarOrigen(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.ComprobarOrigenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String comprobarOrigen(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.eliminarAlbum.ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String retorno="";
		logger.debug("El origen es ["+form.getRetorno()+"]");
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_PERSONALES)){
			retorno = "objetosPersonales";
		}
		if(form.getRetorno()!= null && form.getRetorno().equals(this.ODES_POR_ALBUM)){
			retorno = "odesPorAlbum";
		}
		logger.debug("Modificamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
    }
    
}


