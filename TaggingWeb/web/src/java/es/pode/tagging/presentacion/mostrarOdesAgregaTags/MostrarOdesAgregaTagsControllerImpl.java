// license-header java merge-point
package es.pode.tagging.presentacion.mostrarOdesAgregaTags;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ODEPublico;
import es.pode.tagging.negocio.servicios.TaggingVO;
import es.pode.tagging.presentacion.TaggingSession;
import es.pode.tagging.presentacion.UrlOde;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.tagging.presentacion.mostrarOdesAgregaTags.MostrarOdesAgregaTagsController
 */
public class MostrarOdesAgregaTagsControllerImpl extends MostrarOdesAgregaTagsController
{

	public static final String NEUTRO = "neutro";
	public static final String LAYOUT_NEUTRO = "NEUTRO";
	public static final String TRUE = "true";

	protected static Logger logger = Logger.getLogger(MostrarOdesAgregaTagsControllerImpl.class); 



    /**
     * @see es.pode.tagging.presentacion.mostrarOdesAgregaTags.MostrarOdesAgregaTagsController#obtenerOdesTag(org.apache.struts.action.ActionMapping, es.pode.tagging.presentacion.mostrarOdesAgregaTags.ObtenerOdesTagForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerOdesTag(ActionMapping mapping, es.pode.tagging.presentacion.mostrarOdesAgregaTags.ObtenerOdesTagForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		TaggingSession taggingSession =  this.getTaggingSession(request);
    	if(taggingSession!=null){
    		String usuario = taggingSession.getUsuario();
    		if (usuario!=null) 
    			form.setEsAnonimo(false);// usuario conocido (esta autenticado)
    		else {
    			
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
    			
    			if(usuario!=null)
    				form.setEsAnonimo(false);// usuario conocido (esta autenticado)
    			else
    				form.setEsAnonimo(true);// usuario anonimo
    		}
    				
    		String tag = form.getTag();

    		TaggingVO[] odes =null;
    		if(tag !=null && !tag.equals(""))
    		{
    			taggingSession.setTagPaginacion(tag);
    		}
    		Collection<UrlOde> listaUrlFicha= new ArrayList<UrlOde>();
    		if(taggingSession.getTagPaginacion()!=null && !taggingSession.getTagPaginacion().equals(""))
    		{
    			logger.info("Obteniendo ODES con TAG:" + taggingSession.getTagPaginacion());
    			odes = this.getSrvTaggingServer().obtenerOdesDeTag(taggingSession.getTagPaginacion());
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
    	else 
    		form.setEsAnonimo(true);
    	
    	//si la peticion viene del nodo neutro mostrarmos el layout-sinlateral(layout del nodo neutro)
    	if(this.TRUE.equals(AgregaPropertiesImpl.getInstance().getProperty(this.NEUTRO)))
    		form.setTipoLayoutBuscador(this.LAYOUT_NEUTRO);
    	
     }









}