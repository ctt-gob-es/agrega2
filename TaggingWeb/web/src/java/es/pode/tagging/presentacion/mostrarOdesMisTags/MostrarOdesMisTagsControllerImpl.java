// license-header java merge-point
package es.pode.tagging.presentacion.mostrarOdesMisTags;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ODEPublico;
import es.pode.tagging.negocio.servicios.TaggingVO;
import es.pode.tagging.presentacion.TaggingSession;
import es.pode.tagging.presentacion.UrlOde;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.mostrarOdesMisTags.MostrarOdesMisTagsController
 */
public class MostrarOdesMisTagsControllerImpl extends MostrarOdesMisTagsController
{


	protected static Logger logger = Logger.getLogger(MostrarOdesMisTagsControllerImpl.class); 



    /**
     * @see es.pode.tagging.presentacion.mostrarOdesMisTags.MostrarOdesMisTagsController#obtenerOdesTag(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.mostrarOdesMisTags.ObtenerOdesTagForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOdesTag(ActionMapping mapping, es.pode.tagging.presentacion.mostrarOdesMisTags.ObtenerOdesTagForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	TaggingSession taggingSession =  this.getTaggingSession(request);
    	if(taggingSession!=null){
	        String usuario = taggingSession.getUsuario();
	        String tag = form.getTag();
	
    		if(usuario == null){		
    			//Comprobamos si seguimos autenticados si seguimos autenticados
    			//recuperamos el usuario de ldap y lo metemos en sesion
    			logger.debug("OBTENIENDO USUARIO: " +usuario);
    			try{
    				usuario = LdapUserDetailsUtils.getUsuario();
    				logger.debug("USUARIO CONOCIDO: " + usuario);
    			}
    			catch(Exception ad){
    				logger.debug("USUARIO ANONIMO");
    			}
    			taggingSession.setUsuario(usuario);
    			//*************************************************************
    		}
	        
	        
	        TaggingVO[] odes =null;
    		if(tag !=null && !tag.equals(""))
    		{
    			taggingSession.setTagPaginacion(tag);
    		}
    		Collection<UrlOde> listaUrlFicha= new ArrayList<UrlOde>();
    		if(usuario != null && !usuario.equals("") && taggingSession.getTagPaginacion()!=null && !taggingSession.getTagPaginacion().equals(""))
    		{
	        	logger.debug("Obteniendo los ODES del usuario: " + usuario + " con TAG:" + taggingSession.getTagPaginacion());
	        	odes = this.getSrvTaggingServer().obtenerOdesDeTagYUsuario(taggingSession.getTagPaginacion(), usuario);
    			for (int i = 0; i < odes.length; i++) {
    				UrlOde ode = new UrlOde();
    				// Si no tiene nodo (es el local) o la url es vacia o igual al local redirigimos a la ficha privada 
    				if ((odes[i].getNodo()==null) || (odes[i].getNodo().equals("")) || (odes[i].getNodo().equals(AgregaPropertiesImpl.getInstance().getUrlNodo())))
    					ode.setUrl(ODEPublico.urlFichaPrivadaODEPublicado(odes[i].getIdOde(), odes[i].getIdiomaCat()));
    				else
    					ode.setUrl(ODEPublico.urlFichaODEPublicado(odes[i].getIdOde(), odes[i].getIdiomaCat(), odes[i].getNodo()));
    				ode.setTitulo(odes[i].getTitulo());
    				listaUrlFicha.add( ode ) ;
    			}
    			//cuando paginamos cogemos el tag de sesion
    			if(tag == null || tag.equals(""))
    				form.setTag(taggingSession.getTagPaginacion());
    		}

	        
	        form.setOdes(listaUrlFicha);
    	}
     }









}