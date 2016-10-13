// license-header java merge-point
package es.pode.gestorFlujo.presentacion.albumes.envioAlbum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.publicacion.negocio.servicios.AlbumVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.gestorFlujo.presentacion.albumes.envioAlbum.EnvioAlbumController
 */
public class EnvioAlbumControllerImpl extends EnvioAlbumController
{

	private static final long serialVersionUID = 171018911063606239L;

	private static Logger logger = Logger.getLogger(EnvioAlbumController.class);
	public final static String SIN_ALBUM ="Sin álbum";
	public final static String ID_SINALBUM ="-1";
    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.envioAlbum.EnvioAlbumController#cargaAlbumes(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.envioAlbum.CargaAlbumesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargaAlbumes(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.envioAlbum.CargaAlbumesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {logger.info("Cargando los albumes del usuario: ["+LdapUserDetailsUtils.getUsuario() + "]");
	try{ 
		//Recupero todos los albumes del usuario
		AlbumVO[] albumes = this.getSrvAlbumService().obtenAlbumesPorUsuario(LdapUserDetailsUtils.getUsuario());
		logger.info("Hay un número de albumes: " + ((albumes==null)?null:albumes.length));
		if(albumes != null && albumes.length>0) {
			//Si el usuario tiene albumes, se los paso al albumCheck, (para chequear el album al que pertenezca)
			AlbumCheckVO[] albumesCheck = new AlbumCheckVO[albumes.length+1];
			
			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
        	sesion.setIdODE(form.getIdODE());
			
        	//Paso la primera fila SIN_ALBUM, 
			albumesCheck[0] = new AlbumCheckVO();
			albumesCheck[0].setDescripcion(SIN_ALBUM);
			albumesCheck[0].setIdAlbum(Long.parseLong(ID_SINALBUM));
			albumesCheck[0].setTitulo(SIN_ALBUM);
			albumesCheck[0].setUsuario(LdapUserDetailsUtils.getUsuario());
			albumesCheck[0].setCheck(false);
			for (int i = 0; i < albumes.length; i++) {
				albumesCheck[i+1] = new AlbumCheckVO();
//				Recorro todos los albumes, los mapeo al VO y obtengo los odes de cada uno de ellos para ver si el ode está en alguno.
				albumesCheck[i+1].setDescripcion(albumes[i].getDescripcion());
				albumesCheck[i+1].setFechaCreacion(albumes[i].getFechaCreacion());
				albumesCheck[i+1].setIdAlbum(albumes[i].getId());
				albumesCheck[i+1].setTitulo(albumes[i].getTitulo());
				albumesCheck[i+1].setUsuario(albumes[i].getUsuario());
				TransicionVO[] transicion = this.getSrvAlbumService().obtenOdesPorAlbum(albumes[i].getId());
				if(transicion == null || !(transicion.length>0) ){
					//Si no hay odes en el album, relleno el check del SIN_ALBUM
					albumesCheck[0].setCheck(true);
					albumesCheck[i+1].setCheck(false);
				}else{ // Hay odes en el album
					for (int j = 0; j < transicion.length; j++) {
						//Recorro la lista de odes por identificador y miro si coincide con el que estoy asociando. 
						if (form.getIdODE().equals(transicion[j].getIdODE())) {
							albumesCheck[i+1].setCheck(true);
						}
						else{
							albumesCheck[i+1].setCheck(false);
						}
						//logger.debug("En el album: ["+ albumes[i].getTitulo()+"] esta el ode con id: ["+ form.getIdODE()+"]? ["+ albumesCheck[i+1].getCheck()+"]");
					}
				}
			}
			form.setListaAlbumesAsArray(albumesCheck);
			logger.debug("Se han cargado ["+ albumesCheck.length+ "] albumes del usuario["+ LdapUserDetailsUtils.getUsuario()+"]");
		}
	} catch (Exception ex) {
		logger.error("Error inesperado cargando los albumes del usuario: " + LdapUserDetailsUtils.getUsuario(), ex);
		throw new ValidatorException("{gestorFlujo.error.inesperado}");
	}
   }






    /**
     * @see es.pode.gestorFlujo.presentacion.albumes.envioAlbum.EnvioAlbumController#moverAAlbum(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.albumes.envioAlbum.MoverAAlbumForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void moverAAlbum(ActionMapping mapping, es.pode.gestorFlujo.presentacion.albumes.envioAlbum.MoverAAlbumForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
		String idODE = sesion.getIdODE();
		if(logger.isDebugEnabled())logger.debug("El ode: ["+idODE+"] lo voy a asociar/desasociar a un album");
//		String retorno = sesion.getRetorno();
		//		miro si la lista que me pasan tiene mas de un elemento en ese caso, devuelvo un validatorException.
		String[] idAlbum = form.getIdAlbumRowSelectionAsArray();
		
		if(idAlbum == null || idAlbum.length != 1){
			logger.error("Error asignando el ode: ["+idODE+"] a ninguno o a mas de un album");
			throw new ValidatorException("{gestorFlujo.envioAlbum.noAlbum}");
		}
		logger.info("Envio al album de identificador: ["+ idAlbum[0]+"] de la lista, el ode con identificador: " + idODE);
		 try { 
			 Long idAlbumAsociar = Long.parseLong(idAlbum[0]);
			 //Si el identificador del álbum es -1, es porque quiero desasocirlo del álbum, y dejarlo sin album
			 //Si es distinto, quiero asociarlo a algún álbum, y ademas debo desasociarlo en el caso de que este asociado a alguno.
			 this.getSrvAlbumService().desasociarODEAlbum(idODE);
			 if(idAlbumAsociar != null){
				 if(idAlbumAsociar.equals(Long.parseLong(ID_SINALBUM))){
					logger.debug("El ode: ["+ idODE+"] se ha enviado al album: SIN_ALBUM ");
					form.setTexto("gestorFlujo.envioAlbum.exito");
					form.setExitoFracaso(true);
				 }else{
				 	TransicionVO transicion = this.getSrvAlbumService().asociarODEAlbum(idODE, LdapUserDetailsUtils.getUsuario(), idAlbumAsociar);
					logger.debug("El ode: ["+ idODE+"] se ha asociado al album: ["+ transicion.getTitulo()+ "] ");
					form.setTexto("gestorFlujo.envioAlbum.exito");
					form.setExitoFracaso(true);
				 }
			 }
		} catch (Exception e) {
			logger.error("Problemas asociando el ode: ["+ idODE+"] al album de identificador ["+ idAlbum[0] +"]");
			form.setTexto("gestorFlujo.envioAlbum.fracaso");
			form.setExitoFracaso(false);
		}
	
}



	public String comprobarOrigen(ActionMapping mapping, ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
		Long idAlbum = sesion.getIdAlbum();
		String retorno = sesion.getRetorno();
		logger.debug("El origen es ["+ retorno +"], y el idAlbum ["+ idAlbum +"]");
		if(retorno != null && retorno.equals(ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES)){
			retorno = ObjetosPersonalesControllerImpl.OBJETOS_PERSONALES;
		}
		if(retorno!= null && retorno.equals(ListarAlbumControllerImpl.ODES_POR_ALBUM)){
			retorno = ListarAlbumControllerImpl.ODES_POR_ALBUM;
		}
		logger.debug("Creamos el album, y volvemos a: ["+ retorno + "]");
		return retorno;
	}
}