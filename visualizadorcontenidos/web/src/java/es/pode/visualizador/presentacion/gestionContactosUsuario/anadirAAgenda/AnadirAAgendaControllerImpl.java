// license-header java merge-point
package es.pode.visualizador.presentacion.gestionContactosUsuario.anadirAAgenda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.ContactoVO;
import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;



/**
 * @see es.pode.visualizador.presentacion.gestionContactosUsuario.anadirAAgenda.AnadirAAgendaController
 */
public class AnadirAAgendaControllerImpl extends AnadirAAgendaController
{


	private static Logger logger = Logger.getLogger(AnadirAAgendaControllerImpl.class);



    /**
     * @see es.pode.visualizador.presentacion.gestionContactosUsuario.anadirAAgenda.AnadirAAgendaController#anadirAAgenda(org.apache.struts.action.ActionMapping, es.pode.visualizador.presentacion.gestionContactosUsuario.anadirAAgenda.AnadirAAgendaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void anadirAAgenda(ActionMapping mapping, es.pode.visualizador.presentacion.gestionContactosUsuario.anadirAAgenda.AnadirAAgendaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	String usuarioContacto=form.getUsuarioContacto();
    	
//    	  if(form.getIdsAsArray()!=null && form.getIdsAsArray().length>0){
//			   Object[] arrayIds= form.getIdsAsArray();
//			   if(logger.isDebugEnabled())
//				   logger.debug("los nombres de los usuarios que se quieren asociar al ode son " + arrayIds);
//	    		Long[] ident=new Long[arrayIds.length];
//	    		String lista="";
//	    		for(int i=0;i<arrayIds.length;i++){
//	    			ident[i]= new Long((String) arrayIds[i]);
//	    			lista=lista+ ident[i].toString() + ",";
//	    		}
//	    		Collection id=form.getIdCollection();
//		        String usuarioContacto=form.getUsuarioContacto();
//		        logger.info("Se a�ade el usuario"+id);
//		        ContactoVO contacto=new ContactoVO();
//		        contacto.setUsuarioContacto(usuarioContacto);
    			ContactoVO contacto=new ContactoVO();
    			contacto.setUsuarioContacto(usuarioContacto);
		       ResultadoOperacionVO vuelta = this.getSrvPerfilPublico().anadirContactoAAgenda(contacto, LdapUserDetailsUtils.getUsuario());
		       if(vuelta!=null){
		 	       	
		 	       	Boolean texto=vuelta.getResultado();
		 	       	if(texto.equals(Boolean.TRUE)){
		 					form.setResultado("OK");
		 				}
		 				else{
		 					form.setResultado("FALLO");
		 				}
		 	       }  
//	  	    
//		   }else{
//			   throw new ValidatorException("{errors.asociarOde.idNulo}");
//		   }
    	
    	
      		 		
      
    	
    	
    	
    	
    	
    	
//    	try{
//	    	Collection id=form.getIdCollection();
//	        String usuarioContacto=form.getUsuarioContacto();
//	        logger.info("Se a�ade el usuario"+id);
//	        ContactoVO contacto=new ContactoVO();
//	        contacto.setUsuarioContacto(usuarioContacto);
//	       ResultadoOperacionVO vuelta = this.getSrvPerfilPublico().anadirContactoAAgenda(contacto, LdapUserDetailsUtils.getUsuario());;
//	        
//	        if(vuelta.equals(Boolean.TRUE)){
//				form.setResultado("OK");
//			}
//			else{
//				form.setResultado("FALLO");
//			}
//    	}catch(Exception e){
//    		logger.error("Error al aceptar la solicitud");
//    	}
     }









}