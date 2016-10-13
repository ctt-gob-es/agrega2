/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
package es.pode.presentacion.comentarios;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ODEPublico;
import es.pode.valoracion.negocio.servicios.ComentarioVO;



/**
 * @see es.pode.presentacion.comentarios.ComentariosController
 */
public class ComentariosControllerImpl extends ComentariosController
{

		private static final long serialVersionUID = -244265893890694131L;
		private static Logger logger = Logger.getLogger(ComentariosControllerImpl.class);
		private final static String BARRA = "/";
    
	/**
     * @see es.pode.presentacion.comentarios.ComentariosController#listarComentarios(org.apache.struts.action.ActionMapping, es.pode.presentacion.comentarios.ListarComentariosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void listarComentarios(
    		ActionMapping mapping, 
    		ListarComentariosForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response)
    throws Exception
    {
    	
    	VisualizadorSession sesion = this.getVisualizadorSession(request);
    	
        String identificador =form.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	ComentariosSession comentariosSesion = getComentariosSession(request);
    	comentariosSesion.setIdentificador(identificador);
    	setComentariosSession(request, comentariosSesion);
    	
    	if(	!sesion.isVerComentarios())
    	{
    		logger.debug("El ODE no se puede visualizar porque el usuario no está logado ");
			request.setAttribute("codigo_error", "noautenticado"); 
    		throw new NoAutenticadoException("Usuario No Autenticado");
    	}
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////RETORNO			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	odeSesion.setRetorno("Comentarios");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////  COMENTARIOS			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	try {
//			String idODE= sesion.getIdentificador();
			
			ComentarioVO[] comentarios= this.getSrvValoracionService().obtenerComentarios(identificador);
			
			form.setComentariosAsArray(comentarios );
			form.setComentario("");
			
			if(comentarios!=null)
				logger.debug("cantidad de comentarios... " + comentarios.length );
			
			
		} catch (Exception e) {
			logger.error("Error listando comentarios con idODE["+ identificador+"]",e);
			form.setComentarios(null);
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////// DESTACADO		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        if(sesion.isVerFavorito())
        {
			boolean esFavorito= false;
			try{
				esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(identificador, sesion.getUsuario());
			}catch (Exception e) {
				logger.debug("error al acceder al servicio PerfilPublico ", e);
			}
			sesion.setFavorito(esFavorito);
        }
		

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		
		if(sesion.isVerValorar())
		{
			String usuario="";
			if(sesion.isIdentidadFederada())
			{
	    		URL urlServer= new URL(sesion.getNodoOrigen());
				usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
			}else
			{
				usuario= sesion.getUsuario();
			}
			
			try{
				odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usuario, identificador, odeSesion.getIdiomaCat()));
				odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(identificador));
			}catch (Exception e) {
				logger.debug("error al acceder al servicio de valoración " , e);
				odeSesion.setValoracion("0.0");
				odeSesion.setValorado(true);
			}
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		CARGAR VALORES EN EL FORMULARIO PARA MOSTRAR EL MENU
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		form.setDatosSalida(odeSesion.getOrganizaciones());
		form.setLocalizacion(odeSesion.getLocalizadorCont());
		form.setIdSeleccionado(odeSesion.getIdSeleccionado());
		form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
		form.setUsuario(sesion.getUsuario());
		form.setIdentificador(identificador);
		form.setTituloOde(odeSesion.getTituloOde());
		form.setSecuencia(odeSesion.isSecuencia());
		form.setIdiomaCat(odeSesion.getIdiomaCat());
		form.setRetorno(odeSesion.getRetorno());
    }




    /**
     * @see es.pode.presentacion.comentarios.ComentariosController#insertarComentario(org.apache.struts.action.ActionMapping, es.pode.presentacion.comentarios.InsertarComentarioForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void insertarComentario(
    		ActionMapping mapping, 
    		InsertarComentarioForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
    	VisualizadorSession sesion = this.getVisualizadorSession(request);
    	String identificador=getComentariosSession(request).getIdentificador();
    	OdeSession odeSesion =OdeSessionUtils.getOdeSession(sesion, identificador);
    	
		String idioma= odeSesion.getIdiomaCat();
		String usuario= "";
		if(sesion.isIdentidadFederada())
		{
    		URL urlServer= new URL(sesion.getNodoOrigen());
			usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
		}else
		{
			usuario = LdapUserDetailsUtils.getLogin();
		}
		
		if(form.getComentario()==null || form.getComentario().equals(""))
		{
			throw new ValidatorException("{comentarios.nuevo.error.vacio}");
		}
		sesion.setListaComentariosEliminar(null);
		try {
			
			this.getSrvValoracionService().introducirComentario(identificador, form.getComentario(),idioma , usuario);
			logger.debug("se añadieron los comentarios con exito");
			this.saveSuccessMessage(request, "comentarios.nuevo.mensaje.exito");
			
		} catch (Exception e) {
			logger.error("Error insertando comentario con idODE["+identificador+"] comentarios["+form.getComentario()+"] idiomaODE["+ idioma +"]",e);
		}
		
		StringBuilder nombres = new StringBuilder("");
		StringBuilder emails= new StringBuilder("");

		try{
			String[] ids = {identificador};
			String[] usuarios= this.getSrvPublicacionService().obtenerUsuariosCreacionPublicadosIdentificadores(ids);
			for (int i = 0; usuarios!=null && i < usuarios.length; i++) {
				logger.debug("id: " + identificador + "  autor: " + usuarios[i]);
				if(usuarios[i]!=null && !usuarios[i].equals(""))
				{
					UsuarioVO autor = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuarios[i]);
					nombres.append(autor.getNombre());
					emails.append(autor.getEmail());
					if(i!=usuarios.length-1)
					{
						nombres.append(";");
						emails.append(";");
					}
				}
			}
		}catch (Exception e) {
			logger.debug("Error al obtener el autor del ODE.", e);
		}
		
		
		
		if(! emails.toString().equals("") )
		{
			CorreoOdeVO correo= new CorreoOdeVO();
			logger.debug("nombres:  " + nombres.toString());
			logger.debug("emails:  " +  emails.toString());

	    	String servidor= AgregaPropertiesImpl.getInstance().getUrlNodo();
	    	String nombre="";
	    	String email="";
	    	
	    	if( LdapUserDetailsUtils.getNombreCompleto()!=null)
	    		nombre=LdapUserDetailsUtils.getNombreCompleto();
	    	if(LdapUserDetailsUtils.getMail()!=null)
	    		email=LdapUserDetailsUtils.getMail();
	    	
			//Formamos la url de la imagen
	    	String hrefLogo = servidor ;
	    	String imagenLogo = servidor + BARRA + AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LOGO_AGREGA) ;
	    	
	    	String urlFicha = ODEPublico.urlFichaODEPublicado(identificador, odeSesion.getIdiomaCat());
			
    		correo.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));

    		correo.setComentario(form.getComentario());
    		correo.setEmailRemitente(email);
    		correo.setNombreRemitente(nombre);
			correo.setTo(emails.toString().split(";"));
			correo.setNombreDestinatario(nombres.toString());
			
    		correo.setHrefLogo(hrefLogo);
    		correo.setUrlImagenLogo(imagenLogo);
    		
    		correo.setUrlImagen(odeSesion.getUrlImagen());
    		correo.setUrlFicha(urlFicha);
    		correo.setTituloOde(odeSesion.getTituloOde());
    		
    		try{
			    ResultadoEnvioCorreoVO resultado = this.getSrvCorreo().comentarioODE(correo);
			    
			    if("OK".equals(resultado.getResultado()) )
			    	logger.debug("El Correo de contenido inapropiado se envió con éxito " );
			    else
			    	logger.error("El Correo NO se ha enviado correctamente.");
    		}catch (Exception e) {
		    	logger.error("El Correo NO se ha enviado correctamente.", e);
			}
		}

     }

	@Override
	public void eliminarComentarios(
			ActionMapping mapping,
			EliminarComentariosForm form, 
			HttpServletRequest request,
			HttpServletResponse response) 
	throws Exception 
	{
		try{
			VisualizadorSession sesion= this.getVisualizadorSession(request);
	    	String identificador=getComentariosSession(request).getIdentificador();
	    	OdeSession odeSesion =OdeSessionUtils.getOdeSession(sesion, identificador);
			
			String[] ids_string =(String[]) odeSesion.getListaComentariosEliminar().toArray(new String[0]);
			Long[] ids= new Long[ids_string.length];
			
			for(int i=0; i<ids.length;i++)
			{
				ids[i]= Long.valueOf(ids_string[i]);
			}
			
			this.getSrvValoracionService().eliminarComentarios(ids);
			
			logger.debug("se eliminaron los comentarios con exito");
			this.saveSuccessMessage(request, "comentarios.eliminar.mensaje.exito");
		}catch(Exception e)
		{
			logger.error("error al eliminar Comentarios",e);
			this.saveErrorMessage(request, "comentarios.eliminar.mensaje.error");
		}
		
	}

	@Override
	public void recuperarSeleccion(
			ActionMapping mapping,
			RecuperarSeleccionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{
		logger.debug("metodo recuperar seleccion");
    	VisualizadorSession sesion = this.getVisualizadorSession(request);
    	String identificador=getComentariosSession(request).getIdentificador();
    	OdeSession odeSesion =OdeSessionUtils.getOdeSession(sesion, identificador);
    	
		if(form.getSeleccionadoRowSelection()==null ||  form.getSeleccionadoRowSelection().isEmpty())
		{
			odeSesion.setListaComentariosEliminar(null);
			throw new ValidatorException("{comentarios.eliminar.mensaje.vacio}");
		}
		logger.debug("comentarios seleccionados: " + form.getSeleccionadoRowSelection().size());
		odeSesion.setListaComentariosEliminar(form.getSeleccionadoRowSelection());
    	
	}



	public String submit(
			ActionMapping mapping, 
			SubmitForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
		String resultado="";
		String action= form.getAction();
    	ResourceBundle datosResources = I18n.getInstance().getResource("application-resources", (Locale) request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	
		if(datosResources.getString("comentarios.aceptar").equals(action))
		{
			resultado= "ACEPTAR";
		}else{
			resultado= "CANCELAR";
		}
		return resultado;
	}




	@Override
	public void borrarSesion(ActionMapping mapping, BorrarSesionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		removeComentariosSession(request);
		
	}

}