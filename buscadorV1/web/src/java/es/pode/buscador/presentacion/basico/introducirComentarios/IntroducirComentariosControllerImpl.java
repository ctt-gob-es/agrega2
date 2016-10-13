// license-header java merge-point
package es.pode.buscador.presentacion.basico.introducirComentarios;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.url.ODEPublico;
import es.pode.valoracion.negocio.servicios.ComentarioVO;



/**
 * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController
 */
public class IntroducirComentariosControllerImpl extends IntroducirComentariosController
{

	private static final long serialVersionUID = 6786491288970950374L;
	
	private static Logger logger = Logger.getLogger(IntroducirComentariosControllerImpl.class);	
	
	private final static int MAX_COMENTARIO = 1100;
	private final static String SI = "SI";
	private final static String NO = "NO";		
	private final static String COMENTARIOS = "identificadoresComentarios";	

	private final static String ERROR_OBTENIENDO_COMENTARIOS = "introducir.comentarios.ode.errorObteniendoComentarios";	
	private final static String ERROR_INSERTANDO_COMENTARIO = "introducir.comentarios.ode.errorInsertandoComentario";
	private final static String ERROR_RECUPERANDO_IDS = "introducir.comentarios.ode.errorRecuperandoIdsOdesEliminar";
	private final static String ERROR_ELIMINANDO_COMENTARIO = "introducir.comentarios.ode.errorEliminandoComentario";
	private final static String ERROR_COMENTARIO_VACIO = "introducir.comentarios.ode.errorComentarioVacio";	
	private final static String ERROR_MAX_CARACTERES_COMENTARIO = "introducir.comentarios.ode.errorMaxCaracteresComentario";
	private final static String ERROR_PULSAR_ELIMINAR = "introducir.comentarios.ode.errorPulsarEliminar";



    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#recuperarComentarios(org.apache.struts.action.ActionMapping, RecuperarComentariosODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarComentariosODE(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.RecuperarComentariosODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//		Tenemos que recuperar todos los comentarios que haya asociados a este ODE.
//    	Llamamos al servicio de valoracion para esta tarea.
    	try{
	    	log("IntroducirComentariosControllerImpl - recuperarComentariosODE.");
	    	form.setListaVacia(new Boolean(true));
	        String idODE = form.getIdODE();       
	        Comentario_VO_date[] comentariosDate= new Comentario_VO_date[0];
	        ComentarioVO[] comentarios = null;
	        try {
	        	comentarios= this.getSrvValoracionService().obtenerComentarios(idODE);
	        	
	        	//Se comprueba el numero de comentarios
	        	if(comentarios!=null && comentarios.length>0)
	        		form.setListaVacia(new Boolean(false));
	        	if(comentarios != null && comentarios.length >0){
	        		comentariosDate = new Comentario_VO_date[comentarios.length];
	        	}
	        	for (int i = 0; comentarios != null && i < comentarios.length ; i++) {
	        		comentariosDate[i]= map_comentario(comentarios[i]);
				}
			} catch (Exception e) {
				logger.error("Error obteniendo comentarios del ODE con id["+idODE+"].["+ e.getCause()+"]");
				saveErrorMessage(request, ERROR_OBTENIENDO_COMENTARIOS);
			}
			
			form.setComentarios(java.util.Arrays.asList(comentariosDate));
			
			//Cargamos el locale
			Locale locale = null;
			try {
				locale = devuelveLocale(request);
			} catch (Exception e) {
				logger.error("Error recogiendo el locale",e);			
			}			
	        //Compruebo si es administrador o no
	        form.setUsuarioAdministrador(new Boolean(LdapUserDetailsUtils.esAdministrador()));
    	} catch (Exception e) {
			logger.error("IntroducirComentariosControllerImpl - recuperarComentariosODE:",e);			
		}
    }


    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#analizaPulsacion(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.introducirComentarios.AnalizaPulsacionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String analizaPulsacion(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.AnalizaPulsacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	return form.getAction();
    }

    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#insertarComentarioODE(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.introducirComentarios.InsertarComentarioODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void insertarComentarioODE(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.InsertarComentarioODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
    	try {
	    	log("IntroducirComentariosControllerImpl - insertarComentarioODE.");
			if (!validaFormularioInsercion(form, request)){
				return;
			}
			String idiomaCorreo=null;
			Locale locale = null;
			try {
				locale = devuelveLocale(request);
				idiomaCorreo=locale.getLanguage();
			} catch (Exception e) {
				logger.error("Error recuperando el locale", e);			
			}
	
			String hrefLogo = ImagenesAgrega.urlHrefLogo();
        	String imagenLogo = ImagenesAgrega.urlImagenLogoAgrega();
        	String urlFicha = ODEPublico.urlFichaODEPublicado(form.getIdODE(), form.getIdiomaODE());
        	String imagen = form.getImagen();

	    	log("Valoracion igual ["+form.getValoracion()+"]");
	    	//Analizamos la valoracion
	    	String valoracion = form.getValoracion();
	    	if (valoracion != null && !("").equals(valoracion) && ("0.0".equals(valoracion) || "-1.0".equals(valoracion)))    		
				valoracion = "Sin valorar";
	    	
	    	log("El valor de la valoracion que se envia al correo es ["+valoracion+"]");
	    
	    	
	    	
			
			try {
				String usuario=LdapUserDetailsUtils.getUsuario();
				if(form.getTieneIdentidadFederada()!=null && form.getTieneIdentidadFederada().booleanValue()){//tiene identidad federada
				  	usuario= usuario + "@" + form.getNodoOrigen();
		        }
				
				this.getSrvValoracionService().introducirComentario(form.getIdODE(), form.getComentario(),form.getIdiomaODE(), usuario);
				
				enviarCorreoComentario(form.getComentario(),hrefLogo,imagenLogo,imagen,urlFicha,form.getTituloODE(),valoracion,idiomaCorreo,form.getIdODE());

//				En el caso de ir todo bien, se reinicia el formulario para una nueva insercion
				reiniciaFormularioInsercion(form, locale);
			} catch (Exception e) {
				logger.error("Error insertando comentario con idODE["+form.getIdODE()+"] comentarios["+form.getComentario()+"] idiomaODE["+form.getIdiomaODE()+"]",e);
				saveErrorMessage(request, ERROR_INSERTANDO_COMENTARIO);
			}
    	} catch (Exception e) {
			logger.error("insertarComentarioODE: Error en la inserción comentario con idODE["+form.getIdODE()+"] comentarios["+form.getComentario()+"] idiomaODE["+form.getIdiomaODE()+"]",e);
			saveErrorMessage(request, ERROR_INSERTANDO_COMENTARIO);
		}
     }

    private void enviarCorreoComentario(String comentario,String hrefLogo,String imagenLogo,String imagen,String urlFicha,String tituloODE,String valoracion,String idiomaCorreo,String identificadorODE){
		try{			
			String nombre="";
			String email="";
			
			if( LdapUserDetailsUtils.getNombreCompleto()!=null)
				nombre=LdapUserDetailsUtils.getNombreCompleto();
			if(LdapUserDetailsUtils.getMail()!=null)
				email=LdapUserDetailsUtils.getMail();

	    	String[] idOde = new String[]{identificadorODE};
	    	String[] usuarios= this.getSrvPublicacionService().obtenerUsuariosCreacionPublicadosIdentificadores(idOde);
	    	if(usuarios !=null && usuarios.length>0){
		    	String creador = usuarios[0];
		    	UsuarioVO datosCreador = this.getSrvAdminUsuariosService().obtenerDatosUsuario(creador);
		    	if(datosCreador!=null){
					CorreoOdeVO correo = new CorreoOdeVO();
					correo.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));		
					correo.setComentario(comentario);
					correo.setEmailRemitente(email);
					correo.setNombreRemitente(nombre);
					correo.setTo(new String[]{datosCreador.getEmail()});			
					correo.setHrefLogo(hrefLogo);
					correo.setUrlImagenLogo(imagenLogo);
					correo.setIdiomaCorreo(idiomaCorreo);
					correo.setUrlImagen(imagen);
					correo.setUrlFicha(urlFicha);
					correo.setTituloOde(tituloODE);
					correo.setValoracion(valoracion);
		
				    ResultadoEnvioCorreoVO resultado = this.getSrvCorreo().comentarioODE(correo);
				    if("OK".equals(resultado.getResultado()) )
				    	logger.debug("IntroducirComentariosControllerImpl - enviarCorreoComentario: El Correo de contenido inapropiado se envió con éxito " );
				    else
				    	logger.error("IntroducirComentariosControllerImpl - enviarCorreoComentario: El Correo NO se ha enviado correctamente.");	
			    }else{
			    	logger.error("IntroducirComentariosControllerImpl - enviarCorreoComentario: No se ha podido obtener los datos de usuario ["+creador+"]");
			    }
		    }else{
		    	logger.error("IntroducirComentariosControllerImpl - enviarCorreoComentario: No se ha podido obtener el usuario de creación del ODE ["+identificadorODE+"].");
		    }
		}
		catch (Exception e) {
			logger.error("IntroducirComentariosControllerImpl - enviarCorreoComentario: " , e);
		}
    	
    }

    
    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#eliminarComentarios(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.introducirComentarios.EliminarComentariosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarComentarios(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.EliminarComentariosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	log("IntroducirComentariosControllerImpl - eliminarComentarios.");
    	 try {	
	        	Long[] identificadoresLong = null;
	    		String[] identificadoresString = (String[])request.getSession().getAttribute(COMENTARIOS);
	    		if(identificadoresString!=null)
	    			identificadoresLong = new Long[identificadoresString.length];
	    		//Se transforma el array de string a long
	    		for(int i=0; identificadoresString!=null && i<identificadoresString.length; i++){
	    			for(int j=i; j<=i; j++){
	    			identificadoresLong[j] = Long.valueOf(identificadoresString[i]);
	    			}
	    		}	
	    	    this.getSrvValoracionService().eliminarComentarios(identificadoresLong);	        	
			} catch (Exception e) {
				logger.error("Error eliminando los comentarios",e);
				saveErrorMessage(request, ERROR_ELIMINANDO_COMENTARIO);
			}		
     }

    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#recuperarSeleccionIds(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.introducirComentarios.RecuperarSeleccionIdsForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarSeleccionIds(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.RecuperarSeleccionIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	log("IntroducirComentariosControllerImpl - recuperarSeleccionIds.");
    	String[] idString = null;		
        try {
        	if(form.getIdRowSelectionAsArray()!=null)
        	{
        		idString = new String[form.getIdRowSelectionAsArray().length];        		
        		idString=form.getIdRowSelectionAsArray();
        	}
    		request.getSession().setAttribute(COMENTARIOS, idString);   
    			    		
		} catch (Exception e) {
			//logger.error("Error obteniendo el array de los identificadores de los odes a eliminar["+form.getIdRowSelectionAsArray()+"].");
			saveErrorMessage(request, ERROR_RECUPERANDO_IDS);				
			return; 
		}
     }

    /**
     * @see es.pode.buscador.presentacion.basico.introducirComentarios.IntroducirComentariosController#validaBotonEliminar(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.introducirComentarios.ValidaBotonEliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String validaBotonEliminar(ActionMapping mapping, es.pode.buscador.presentacion.basico.introducirComentarios.ValidaBotonEliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//    	Comprueba si al pulsar el boton de eliminar comentario habia seleccionado alguno o no.
		String[] ids = (String[])request.getSession().getAttribute(COMENTARIOS);
		if(ids==null){
			logger.error("Error al pulsar el boton de eliminar.");
			saveErrorMessage(request, ERROR_PULSAR_ELIMINAR);	
			return NO;
		}else
			return SI;	
    }
    
    private Comentario_VO_date map_comentario (ComentarioVO tr)
    {
    	Comentario_VO_date tr_date = new Comentario_VO_date();
    	
    	tr_date.setComentario(tr.getComentario());  
    	tr_date.setUsuario(tr.getUsuario());
    	tr_date.setFecha(tr.getFecha().getTime());
    	tr_date.setIdODE(tr.getIdODE());    	 	
    	tr_date.setId(tr.getId());
    	return tr_date;
    	
    }
	
    
    public class Comentario_VO_date {
    	
    	  /* Identificador del ODE */
        private java.lang.String idODE;
        
        /* Usuario que ha creado el comentario*/
        private java.lang.String usuario;

        /* El comentario de la valoracion. */
        private java.lang.String comentario;

        /* La fecha de la valoracion. */
        private java.util.Date fecha;          
        
        /* Id del comentario*/
        private java.lang.Long id;
        
		public java.lang.String getComentario() {
			return comentario;
		}

		public void setComentario(java.lang.String comentario) {
			this.comentario = comentario;
		}

		public java.util.Date getFecha() {
			return fecha;
		}

		public void setFecha(java.util.Date fecha) {
			this.fecha = fecha;
		}

		public java.lang.String getIdODE() {
			return idODE;
		}

		public void setIdODE(java.lang.String idODE) {
			this.idODE = idODE;
		}				

		public java.lang.Long getId() {
			return id;
		}

		public void setId(java.lang.Long id) {
			this.id = id;
		}
		
		public java.lang.String getUsuario() 
		{
			return usuario;
		}
		public void setUsuario(java.lang.String usuario)
		{
			this.usuario = usuario;
		}
    }
    
	private boolean validaFormularioInsercion(es.pode.buscador.presentacion.basico.introducirComentarios.InsertarComentarioODEForm form, HttpServletRequest request) throws Exception
	{
		boolean valido=true;			
		if (form.getComentario() == null || form.getComentario().trim().equals(""))
		{
			logger.error("Error insertando comentario con texto vacio.");
			saveErrorMessage(request,ERROR_COMENTARIO_VACIO);
			valido=false;			
		}		
		if (form.getComentario()!=null && !("").equals(form.getComentario().trim()) && form.getComentario().length()>MAX_COMENTARIO)
		{
			logger.error("Error insertando comentario excediendo el numero de caracteres permitido para el texto de comentario");
			saveErrorMessage(request,ERROR_MAX_CARACTERES_COMENTARIO);
			valido=false;			
		}	
		
		return valido;
	}
	
	private void reiniciaFormularioInsercion(es.pode.buscador.presentacion.basico.introducirComentarios.InsertarComentarioODEForm form, Locale locale) throws Exception
	{
		form.setComentario("");		
	}	

	private Locale devuelveLocale(HttpServletRequest request) throws Exception
	{	
		
		Locale locale=null;
//		if (request instanceof HttpServletRequest) {
			locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
//		}
//		else{ 	    					
//			try {
//				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
//			} catch (Exception e) {								
//				logger.error("DevuelveLocale-- Error recuperando el locale del request", e);
//			}
//		}
		return locale;
	}
	
	private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}
}