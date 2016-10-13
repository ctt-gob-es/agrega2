// license-header java merge-point
package es.pode.reputacion.presentacion.insertar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.reputacion.mail.MailSender;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsAgrega;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.valoracion.negocio.servicios.ComentarioVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.reputacion.presentacion.insertar.InsertarComentarioController
 */
public class InsertarComentarioControllerImpl extends InsertarComentarioController
{

	private static final long serialVersionUID = 6786491288970950374L;

	private static Logger logger = Logger.getLogger(InsertarComentarioControllerImpl.class);		
	
	
	private java.util.Properties reputacionProperties = null;	
	
	private final static String SUBJECT_EMAIL = "mandar.email.contenido.inapropiado.subject";
	private final static String BODY_EMAIL_HOLA = "mandar.email.contenido.inapropiado.body.hola";
	private final static String BODY_EMAIL = "mandar.email.contenido.inapropiado.body";
	private final static String BODY_EMAIL2 = "mandar.email.contenido.inapropiado.body2";
	private final static String BODY_EMAIL_TITULO = "mandar.email.contenido.inapropiado.body.titulo.comentario";
	private final static String BODY_EMAIL_COMENTARIO = "mandar.email.contenido.inapropiado.body.comentario.comentario"; 
	private final static String BODY_EMAIL_VALORACION = "mandar.email.contenido.inapropiado.body.comentario.valoracion";
	private final static String BODY_EMAIL_COMUNIDAD = "mandar.email.contenido.inapropiado.body.comentario.comunidad";
	private final static String BODY_EMAIL_COMENTARIO_DESPEDIDA1 = "mandar.email.contenido.inapropiado.body.comentario.despedida1"; 
	private final static String BODY_EMAIL_COMENTARIO_DESPEDIDA2 = "mandar.email.contenido.inapropiado.body.comentario.despedida2";
	private final static String BODY_EMAIL_COMENTARIO_FINAL = "mandar.email.contenido.inapropiado.body.comentario.final";	
	
	private final static int MAX_TITULO = 255;
	private final static int MAX_COMENTARIO = 400;
	private final static String SI = "SI";
	private final static String NO = "NO";	
	private final static String FICHERO="application-resources";		
	private final static String ARG1 = "arg1";
	private final static String COMENTARIOS = "identificadoresComentarios";	

	private final static String ERROR_OBTENIENDO_COMENTARIOS = "introducir.comentarios.ode.errorObteniendoComentarios";
	private final static String ERROR_OBTENIENDO_VALORACION = "introducir.comentarios.ode.errorObteniendoValoracion";
	private final static String ERROR_INSERTANDO_COMENTARIO = "introducir.comentarios.ode.errorInsertandoComentario";
	private final static String ERROR_RECUPERANDO_IDS = "introducir.comentarios.ode.errorRecuperandoIdsOdesEliminar";
	private final static String ERROR_ELIMINANDO_COMENTARIO = "introducir.comentarios.ode.errorEliminandoComentario";
	private final static String ERROR_COMENTARIO_VACIO = "introducir.comentarios.ode.errorComentarioVacio";
	private final static String ERROR_TITULO_VACIO = "introducir.comentarios.ode.errorTituloVacio";	
	private final static String ERROR_MAX_CARACTERES_TITULO = "introducir.comentarios.ode.errorMaxCaracteresTitulo";
	private final static String ERROR_MAX_CARACTERES_COMENTARIO = "introducir.comentarios.ode.errorMaxCaracteresComentario";
	private final static String ERROR_PULSAR_ELIMINAR = "introducir.comentarios.ode.errorPulsarEliminar";
	
    /**
     * @see es.pode.reputacion.presentacion.insertar.InsertarComentarioController#recuperarComentariosODE(org.apache.struts.action.ActionMapping, es.pode.reputacion.presentacion.insertar.RecuperarComentariosODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarComentariosODE(ActionMapping mapping, es.pode.reputacion.presentacion.insertar.RecuperarComentariosODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
//    	Se comprueba si el usuario esta autenticado     	
    	form.setUsuarioLogado(LdapUserDetailsUtils.estaAutenticado());    	
    	
//		Tenemos que recuperar todos los comentarios que haya asociados a este ODE.
//    	Llamamos al servicio de valoracion para esta tarea.   	   		
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
//        Cargamos el select de valoraciones
		
		//Cargamos el locale
		Locale locale = null;
		try {
			locale = devuelveLocale(request);
		} catch (Exception e) {
			logger.error(e);
		}
		form.setValoracionLabelList(cargarValoracionLabel(locale));
		form.setValoracionValueList(cargarValoracionValue());   
		
        //Compruebo si es administrador o no
        form.setUsuarioAdministrador(new Boolean(LdapUserDetailsUtils.esAdministrador())); 	 
       
     }

    /**
     * @see es.pode.reputacion.presentacion.insertar.InsertarComentarioController#insertarComentarioODE(org.apache.struts.action.ActionMapping, es.pode.reputacion.presentacion.insertar.InsertarComentarioODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void insertarComentarioODE(ActionMapping mapping, es.pode.reputacion.presentacion.insertar.InsertarComentarioODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       	
    	
//    	Obtenemos los datos del comentario insertado.
        Float valoracion;        
        try {
        	valoracion = new Float(form.getValoracion());
		} catch (Exception e) {
			logger.error("Error convirtiendo valoracion["+form.getValoracion()+"] a float.",e);
			saveErrorMessage(request, ERROR_OBTENIENDO_VALORACION);
			return; // no progresamos la insercion del comentario.
		}
//		Validamos el formulario. En el caso de no validar, salvamos el motivo en la request
		if (!validaFormularioInsercion(form, request)){
			return;
		}	
		Locale locale = null;
		try {
			locale = devuelveLocale(request);
		} catch (Exception e) {
			logger.error("Error recuperando el locale", e);			
		}
//		Hay que tener en cuenta si el contenido es inapropiado o no, y si es asi, hay que mandar un correo electornico al administrador
//		con la informacion necesaria que se puede obtener del propio formulario.		
		String[] administradores = getSrvAdminUsuariosService().getEmailAdmin();
		if(form.getConInaprop()!=null && form.getConInaprop().booleanValue()){	
			for(int i=0; administradores!=null && i<administradores.length; i++){
				if(enviarCorreo(administradores[i], this.getResource(SUBJECT_EMAIL, FICHERO, locale), construirBodyEmail(form.getTituloODE(),form.getTitulo(), form.getComentario(), form.getValoracion(), request.getServerName(), request), null))			
					logger.debug("Envio correcto del email para el administrador ["+administradores[i]+"]");
				else
					logger.debug("El email no se ha enviado correctamente al administrador ["+administradores[i]+"]");
			}			
		}
		
		//Introducimos la seguridad para este metodo			
		
		
		try {
			this.getSrvValoracionService().introducirComentario(form.getIdODE(), form.getTitulo(), form.getComentario(), valoracion, form.getIdiomaODE());
//			En el caso de ir todo bien, se reinicia el formulario para una nueva insercion
			reiniciaFormularioInsercion(form, locale);
		} catch (Exception e) {
			logger.error("Error insertando comentario con idODE["+form.getIdODE()+"] titulo["+form.getTitulo()+"] valoracion["+valoracion+"] comentarios["+form.getComentario()+"] idiomaODE["+form.getIdiomaODE()+"]",e);
			saveErrorMessage(request, ERROR_INSERTANDO_COMENTARIO);
		}
     }
    

	public void eliminarComentarios(ActionMapping mapping, EliminarComentariosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
							
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
	
	public String analizaPulsacion(ActionMapping mapping, AnalizaPulsacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return form.getAction();		
	}
	
	public void recuperarSeleccionIds(ActionMapping mapping, RecuperarSeleccionIdsForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	
	public String validaBotonEliminar(ActionMapping mapping, ValidaBotonEliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Comprueba si al pulsar el boton de eliminar comentario habia seleccionado alguno o no.
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
    	tr_date.setFecha(tr.getFecha().getTime());
    	tr_date.setIdODE(tr.getIdODE());
    	tr_date.setTitulo(tr.getTitulo());
    	tr_date.setValoracion(tr.getValoracion());
    	tr_date.setId(tr.getId());
    	return tr_date;
    	
    }
	
    
    public class Comentario_VO_date {
    	
    	  /* Identificador del ODE al que hace referencia la valoracion. */
        private java.lang.String idODE;

        /* El comentario de la valoracion. */
        private java.lang.String comentario;

        /* La fecha de la valoracion. */
        private java.util.Date fecha;

        /* Titulo del comentario, en caso de tenerlo. */
        private java.lang.String titulo;
        
        /* Valoracion */
        private java.lang.Float valoracion;

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

		public java.lang.String getTitulo() {
			return titulo;
		}

		public void setTitulo(java.lang.String titulo) {
			this.titulo = titulo;
		}

		public java.lang.Float getValoracion() {
			return valoracion;
		}

		public void setValoracion(java.lang.Float valoracion) {
			this.valoracion = valoracion;
		}

		public java.lang.Long getId() {
			return id;
		}

		public void setId(java.lang.Long id) {
			this.id = id;
		}
    }
    
	private boolean validaFormularioInsercion(es.pode.reputacion.presentacion.insertar.InsertarComentarioODEForm form, HttpServletRequest request) throws Exception
	{
		boolean valido=true;		
		if (form.getTitulo() == null || form.getTitulo().trim().equals(""))
		{
			logger.error("Error insertando comentario con titulo vacio.");
			saveErrorMessage(request,ERROR_TITULO_VACIO);
			valido=false;			
		}
		if (form.getComentario() == null || form.getComentario().trim().equals(""))
		{
			logger.error("Error insertando comentario con texto vacio.");
			saveErrorMessage(request,ERROR_COMENTARIO_VACIO);
			valido=false;			
		}						
		if (form.getTitulo()!=null && !("").equals(form.getTitulo().trim()) && form.getTitulo().length()>MAX_TITULO)
		{
			logger.error("Error insertando comentario excediendo el numero de caracteres permitido para el titulo");
			saveErrorMessage(request,ERROR_MAX_CARACTERES_TITULO);
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
	
	private void reiniciaFormularioInsercion(es.pode.reputacion.presentacion.insertar.InsertarComentarioODEForm form, Locale locale) throws Exception
	{
		form.setComentario("");
		form.setTitulo("");
				
		String[] valoracionesValor = cargarValoracionValue();		

		form.setValoracion(valoracionesValor[0]);
		form.setValoracionLabelList(cargarValoracionLabel(locale));
        form.setValoracionValueList(valoracionesValor);
	}
	
	private String[] cargarValoracionLabel(Locale locale){
		ResourceBundle datosResources = I18n.getInstance().getResource(FICHERO, locale);
		if (logger.isDebugEnabled())logger.debug("cargarValoracionLabel: cargando labels de valoracion localizados en["+locale.getDisplayLanguage()+"]");
		String[] valoracionLabel = {datosResources.getString("introducir.comentarios.ode.valoracion.menos.uno"),
									datosResources.getString("introducir.comentarios.ode.valoracion.cero"),
									datosResources.getString("introducir.comentarios.ode.valoracion.uno"),
									datosResources.getString("introducir.comentarios.ode.valoracion.dos"),
									datosResources.getString("introducir.comentarios.ode.valoracion.tres"),
									datosResources.getString("introducir.comentarios.ode.valoracion.cuatro"),
									datosResources.getString("introducir.comentarios.ode.valoracion.cinco")};
		return valoracionLabel;
	}
	
	private String[] cargarValoracionValue(){
		if (logger.isDebugEnabled())logger.debug("cargarValoracionValue: cargando valores de valoracion");
		String[] valoracionArray = null;
		try {
			valoracionArray = this.getPropertyValue("valoraciones.valor").split(",");
		} catch (IOException e) {
			logger.error(e);
		}
		return valoracionArray;
	}
	
	private boolean enviarCorreo(String to, String asunto, StringBuffer body, String from) throws Exception {
		// Envio de correo necesitamos conocer el
		// resultado del envio para mostrarselo al usuario	
			boolean flag = true;
			logger.debug("Envio de correo");		
			try {
			    MailSender mailSender = new MailSender();
			    mailSender.send(to, asunto, body, from);
			} catch (Exception e) {
			   logger.debug("error al enviar el correo",e);
			   flag = false;
			}
			return flag;
	    }
	
	private StringBuffer construirBodyEmail(String tituloODE, String tituloComentario, String comentario, String valoracion, String comunidad, HttpServletRequest request) throws IOException{
		
		StringBuffer contenido = new StringBuffer();
		
		Locale locale = null;
		try {
			locale = devuelveLocale(request);
		} catch (Exception e) {
			logger.error(e);
		}
		
//		REALIZAMOS EL TRATAMIENTO DE LOS LITERALES PARA PODER INTRODUCIRLOS EN EL CÓDIGO HTML		
		String bodyEmailHola = this.getResource(BODY_EMAIL_HOLA, FICHERO, locale);
		String bodyEmail = this.getResource(BODY_EMAIL, FICHERO, locale);
		String bodyEmail2 = this.getResource(BODY_EMAIL2, FICHERO, locale);
		String bodyEmailTitulo = this.getResource(BODY_EMAIL_TITULO, FICHERO, locale);
		String bodyEmailComentario = this.getResource(BODY_EMAIL_COMENTARIO, FICHERO, locale);	
		String bodyEmailValoracion = this.getResource(BODY_EMAIL_VALORACION, FICHERO, locale);	
		String bodyEmailComunidad = this.getResource(BODY_EMAIL_COMUNIDAD, FICHERO, locale);	
		String bodyEmailComentarioDespedida1 = this.getResource(BODY_EMAIL_COMENTARIO_DESPEDIDA1, FICHERO, locale);	
		String bodyEmailComentarioDespedida2 = this.getResource(BODY_EMAIL_COMENTARIO_DESPEDIDA2, FICHERO, locale);
		String bodyEmailComentarioFinal = this.getResource(BODY_EMAIL_COMENTARIO_FINAL, FICHERO, locale);		
			
		
		//Formo parte del body del mensaje
		String textoBodyReturn = "";
		String[] textoBodyArray = bodyEmail.split(" ");
		for(int i=0; textoBodyArray!=null && i<textoBodyArray.length; i++){
			if(textoBodyArray[i].equals(ARG1))
				textoBodyArray[i]= "\"" + tituloODE + "\"";	
			textoBodyReturn = textoBodyReturn + " " + textoBodyArray[i];
			
		}	
		textoBodyReturn.trim();
		
		//Formo parte del body del mensaje
		bodyEmailTitulo = bodyEmailTitulo + " " + tituloComentario;
		bodyEmailComentario = bodyEmailComentario + " " + comentario;	
		bodyEmailValoracion = bodyEmailValoracion + " " + valoracion;	
		bodyEmailComunidad = bodyEmailComunidad + " " + comunidad;	
		
		//Codigo html
		contenido.append("<html>");				
		contenido.append(bodyEmailHola);	
		contenido.append("<br>");	
		contenido.append("<br>");	
		contenido.append(textoBodyReturn);
		contenido.append("<br>");
		contenido.append("<br>");
		contenido.append(bodyEmail2);
		contenido.append("<br>");		
		contenido.append(bodyEmailTitulo);
		contenido.append("<br>");		
		contenido.append(bodyEmailComentario);
		contenido.append("<br>");		
		contenido.append(bodyEmailValoracion);
		contenido.append("<br>");		
		contenido.append(bodyEmailComunidad);
		contenido.append("<br>");	
		contenido.append("<br>");
		contenido.append("<br>");
		contenido.append(bodyEmailComentarioDespedida1);
		contenido.append("<br>");
		contenido.append(bodyEmailComentarioDespedida2);
		contenido.append("<br>");		
		contenido.append("<br>");
		contenido.append("<br>");
		contenido.append(bodyEmailComentarioFinal);
		contenido.append("</br>");
		contenido.append("</html>");
		
		return contenido;		
	
	}	
	
    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream(
				"/reputacion.properties");
		if (this.reputacionProperties == null) {
			reputacionProperties = new java.util.Properties();
			reputacionProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		this.logger.debug("Propiedad recuperada: " + sKey + " : "
				+ reputacionProperties.getProperty(sKey));
		// devolvemos la propiedad
		return reputacionProperties.getProperty(sKey);
	}
    
//	MÉTODOS PARA PINTAR LOS MENSAJES INTERNACIONALIZADOS
	public static String getResource(String key, String baseName, Locale locale){
        String recurso = "";
        ResourceBundle theResourceBundle = getResource(baseName,locale);
        try{
        	if (theResourceBundle!=null){
               recurso = theResourceBundle.getString(key);
           }
        }catch (MissingResourceException mre){
        	recurso = key;
        }
        return recurso;
    }
	
	public static ResourceBundle getResource(String baseName, Locale locale){
        try{
        	return ResourceBundle.getBundle(baseName,locale);
            
        }catch (MissingResourceException mre){
        		locale = new Locale("","");
        		return ResourceBundle.getBundle(baseName,locale);
             
        }
    }
	private Locale devuelveLocale(HttpServletRequest request) throws Exception
	{	
		
		Locale locale=null;
		if (request instanceof HttpServletRequest) {
			locale = (Locale)((HttpServletRequest)request).getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
			
		}
		else{ 	    					
			try {
				locale=new Locale(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			} catch (Exception e) {								
				logger.error("DevuelveLocale-- Error recuperando el locale del request", e);
			}
		}
		return locale;
	}

	
}