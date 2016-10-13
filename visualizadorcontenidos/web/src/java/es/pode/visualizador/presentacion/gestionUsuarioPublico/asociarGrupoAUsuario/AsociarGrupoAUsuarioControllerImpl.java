// license-header java merge-point
package es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarGrupoAUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.date.DateManager;



/**
 * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarGrupoAUsuario.AsociarGrupoAUsuarioController
 */
public class AsociarGrupoAUsuarioControllerImpl extends AsociarGrupoAUsuarioController
{




	private static Logger logger = Logger.getLogger(AsociarGrupoAUsuarioControllerImpl.class);

    /**
     * @see es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarGrupoAUsuario.AsociarGrupoAUsuarioController#asociarGrupoAUsuario(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarGrupoAUsuario.AsociarGrupoAUsuarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void asociarGrupoAUsuario(ActionMapping mapping, es.pode.visualizador.presentacion.gestionUsuarioPublico.asociarGrupoAUsuario.AsociarGrupoAUsuarioForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	Long identificador=form.getId();
//    	try{
//	    	if(form.getIdCollectionAsArray()!=null && form.getIdCollectionAsArray().length>0){
//				   Object[] arrayIds= form.getIdCollectionAsArray();
//				   if(logger.isDebugEnabled())
//					   logger.debug("los ids de los itinerarios que se quieren asociar son " + arrayIds);
//		    		Long[] ident=new Long[arrayIds.length];
//		    		String lista="";
//		    		for(int i=0;i<arrayIds.length;i++){
//		    			ident[i]= new Long((String) arrayIds[i]);
//		    			lista=lista+ ident[i].toString() + ",";
//		    		}
//		    		
//		  	     form.setListaId(lista);
//
//			   }else{
//				   throw new ValidatorException("{errors.asociarGrupos.idError}");
//			   }
//    	}catch (Exception e) {
//	      	    logger.error("Se ha producido un error al recuperar los itinerarios: " + e);
//  	      	    throw new ValidatorException("{errors.asociarOde.recuperar}");
//  	     }  
//
//    	
//    		if(form.getListaId()!=null && !form.getListaId().equals("")){
//    			String[]listIDsStrings=form.getListaId().split(",");
//    			if(logger.isDebugEnabled())
//    				logger.debug("La lista de identificadores:"+form.getListaId());
//				Long[] listIDs =new Long[listIDsStrings.length];
				Long[] identificadores=new Long[1];
				Collection<SolicitudGrupoVO> gruposAsociar=new ArrayList();
//				for(int i=0; i<listIDsStrings.length;i++)
//				{
				try{
					identificadores[0]=identificador;
//				
					SolicitudGrupoVO solicitud=new SolicitudGrupoVO();
					GrupoPublicoVO[] grupos = this.getSrvPerfilPublico().obtenerGruposPublicosPorIdentificador(identificadores);
					GrupoPublicoVO grupo=new GrupoPublicoVO();
					if(grupos!=null && grupos.length>0){
						grupo=grupos[0];
					}
					Boolean existe=this.getSrvPerfilPublico().existeSolicitudGrupoIdentico(grupo.getNombre(), LdapUserDetailsUtils.getUsuario());
					if(!existe.booleanValue()){
						solicitud.setGrupo(grupo.getNombre());
						solicitud.setId(identificadores[0]);
						solicitud.setUsuarioSolicitante(LdapUserDetailsUtils.getUsuario());
						solicitud.setUsuarioAdministrador(grupo.getAdministrador());
						Date fecha=new Date(System.currentTimeMillis());
						solicitud.setFechaSolicitud(DateManager.dateToCalendar(fecha));
						Long hecho = this.getSrvPerfilPublico().hacerSolicitudParaGrupo(solicitud);
						logger.info("Se ha creado correctamente la solicitud "+hecho);
						if(hecho!=null && hecho>0){
							gruposAsociar.add(solicitud);
						}
					}else{
						 throw new ValidatorException("{errors.asociarGrupos.existe}");
					}
				
			
	    		form.setGruposAsociar(gruposAsociar);
	    		}catch (Exception e){
	    		logger.error("Se ha producido un error al asociar el itinerario: " + e);
	    		throw new ValidatorException("{errors.asociar.gruposPublicos}");
	    	}
//	    		
//    		}else{
//    			throw new ValidatorException("{errors.bajanodosSQI.idNulo}");
//    		}
//    		 		
//    	
     }









}