// license-header java merge-point
package es.pode.presentacion.inicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.agregaProperties.DecisorOffline;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaService;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceServiceLocator;
import es.pode.entregar.negocio.servicios.ArgObtOrganizacionesVO;
import es.pode.entregar.negocio.servicios.ManifestVO;
import es.pode.entregar.negocio.servicios.OrganizacionVO;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.exceptions.OdeNoEncontradoException;
import es.pode.presentacion.utils.OdeSessionUtils;
import es.pode.publicacion.negocio.servicios.ContenidoInapropiadoVO;
import es.pode.publicacion.negocio.servicios.EstadoVO;
import es.pode.publicacion.negocio.servicios.TransicionAutorVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.presentacion.inicio.VisualizarController
 */
@SuppressWarnings("serial")
public class VisualizarControllerImpl extends VisualizarController
{
	private static Logger logger = Logger.getLogger(VisualizarControllerImpl.class);
	
	private static final String LICENCIA="/licencia.txt";

	private static final String VISUALIZADOR_ADL="ADL";
	private static final String VISUALIZADOR_AGREGA="AGREGA";
	private static final String CON_SECUENCIA="ConSecuencia";
	private static final String SIN_SECUENCIA="SinSecuencia";

	
	
	private Properties properties;

	private String getPropertyValue(
			String sKey) 
	throws IOException 
{
	if (this.properties == null) {
		InputStream fIsProperties = this.getClass().getResourceAsStream("/visualizador.properties");
		properties = new java.util.Properties();
		properties.load(fIsProperties);
		fIsProperties.close();
	}
	return properties.getProperty(sKey);
}





    /**
     * @see es.pode.presentacion.inicio.VisualizarController#inicializar(org.apache.struts.action.ActionMapping, es.pode.presentacion.inicio.InicializarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void inicializar(
    		ActionMapping mapping, 
    		InicializarForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {

    	boolean secuencia = form.isSecuencia();
    	String identificador = form.getIdentificador(); 
    	
    	InicioSession inicioSesion= getInicioSession(request);
    	inicioSesion.setIdentificador(identificador);
    	setInicioSession(request, inicioSesion);
    	
    	String idioma= form.getIdioma();
    	String idiomaTit="";
    		
    	String usuario = ""; 
    	boolean visualizadorADL= false;
    	
    	if(LdapUserDetailsUtils.estaAutenticado()){
			idiomaTit=LdapUserDetailsUtils.getIdioma();
			usuario = LdapUserDetailsUtils.getUsuario();
			
			if(VISUALIZADOR_ADL.toLowerCase().equals(LdapUserDetailsUtils.getVisualizador()))
				visualizadorADL=true;
    	}
    	else{
    		idiomaTit = request.getLocale().getLanguage();
    		usuario ="user";
    	}
    	if(idiomaTit==null || idiomaTit.equals("")){
    		idiomaTit=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}
    	if(idioma==null || idioma.equals("")){
    		idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
    	}
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////		CONTROLES DE SEGURIDAD!!!   				////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	EstadoVO estado;
    	if(!DecisorOffline.esOffline())
    	{
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
//			recupero el ambito del ODE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
	    	boolean ambitoUniversal= false;
	    	boolean nodoEnAmbito=false;
	    	String[] ambito=null;
	    	DocVO30 doc = null;
	    	try{
	    		//Si pasamos el idioma vacio buscara en todos los idomas.
	    		//Esto es necesario por si el ODE esta en un idoma diferente al de navegacion o al por defecto de la plataforma
	    		doc =  this.getSrvBuscadorService().busquedaMEC(identificador, "");
	    	} catch (Exception e) {
	    		logger.error("error al llamar al servicio Buscador del indexador para el ODE con id "+identificador+": ",e );
			}

//   		 	if (doc==null)
//   		 		throw new Exception("El ODE con id "+identificador+" no ha podido ser encontrado");
	    		    	
	    	String serverId = "";
	    	if(LdapUserDetailsUtils.estaAutenticado())
	    	{
	    		serverId = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
	    	}else if(LdapUserDetailsUtils.tieneIdentidadFederada(request))
	    	{
//	    		formo la url del nodo
		        String nodo= "" ;
		        if(!form.getNodoOrigen().toLowerCase().startsWith("http://") )
		        	nodo="http://" +  form.getNodoOrigen();
		        else
		        	nodo= form.getNodoOrigen();
//		        llamo al nodo origen para conocer su identificador
	        	URL url= new URL(nodo + "/buscar-3/services/SrvBuscarFederadaService");
	        	SrvBuscarFederadaService server= new SrvBuscarFederadaServiceServiceLocator().getSrvBuscarFederadaService(url);
	        	serverId = server.obtenerIdentificadorNodo();
	    	}
	    	//Un usuario no logado no debe poder ver ODEs con ámbito limitado
//	    	if ((!LdapUserDetailsUtils.estaAutenticado())&&(!LdapUserDetailsUtils.tieneIdentidadFederada(request))){
//	    		serverId = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
//	    	}
	    	
	    	if(doc!=null && doc.getAmbito()!=null)
	    	{	
	    		ambito = doc.getAmbito();
	    		for (int i = 0; i < ambito.length && !ambitoUniversal; i++) {
					if("universal".equals(ambito[i]))
					{
						ambitoUniversal= true;
						nodoEnAmbito = true;
					}
					if(!serverId.equals("") && serverId.equals(ambito[i]))
					{
						nodoEnAmbito = true;
					}
//					if ((!LdapUserDetailsUtils.estaAutenticado())&&(!LdapUserDetailsUtils.tieneIdentidadFederada(request)) && (!"universal".equals(ambito[i]))){
//						nodoEnAmbito = false;
//					}
				}
	    	}
	    	
	    	
    		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
//			recupero el estado del ODE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
    		
	    	try{
	    		 estado = this.getSrvPublicacionService().obtenEstadoPorIdODE( identificador , "es");
	    		 if (estado==null)
	    			 throw new Exception("El estado del ODE con id "+identificador+" no ha podido ser encontrado");
	    	}catch (Exception e) {
	    		logger.error("Error obteniendo el estado del ODE con id "+identificador+": " , e);
	    		request.setAttribute("codigo_error", "odenoencontrado");
	    		throw new OdeNoEncontradoException("ODE no encontrado");
			}
	    	
	    	
	    	logger.debug("\n\nambito Universal: " + ambitoUniversal + "\n\n");
    		if(	AgregaProperties.PUBLICADO.equals(estado.getClave()) &&
    			!ambitoUniversal &&  //no es universal 
    			 !nodoEnAmbito ) // el nodo del usuario no está entre los ambitos del ODE
    		{
    			logger.debug("\n\nambito NOUniversa y usuario no logado.. lo envío al login \n\n");

    			logger.info("El ODE "+identificador+" no se puede visualizar porque el ODE no es universal y no es visible en este nodo");
				request.setAttribute("codigo_error", "noautenticado"); 
	    		throw new NoAutenticadoException("Usuario No Autenticado");
    			
    		}
	    	
	    	
	    	
	    	if( (  !AgregaProperties.PUBLICADO.equals(estado.getClave()) 
	    		&& !AgregaProperties.PUBLICADO_AUTONOMO.equals(estado.getClave()) )
	    		&& !LdapUserDetailsUtils.estaAutenticado() )
	    	{
	    		logger.info("El ODE '"+identificador+"' no se puede visualizar porque el usuario '"+usuario+"' no esta logado ");
				request.setAttribute("codigo_error", "noautenticado"); 
	    		throw new NoAutenticadoException("Usuario No Autenticado");
	    	}
	    	else if( AgregaProperties.CREACION.equals(estado.getClave() ) ||  
	    			AgregaProperties.RECHAZADO.equals(estado.getClave() ))
	    	{
	    		String[] ids= {identificador};
	    		String[] usuarios ;
	    		
	    		usuarios =  this.getSrvPublicacionService().obtenerUsuariosCreacionDeIdentificadores(ids);
	    		
	    		if(	usuarios==null || 
	    			usuarios.length != 1 )
	    		{
		    		logger.info("El ODE '"+identificador+"' no se puede visualizar porque el usuario '"+usuario+"' no es el creador");
					request.setAttribute("codigo_error", "noautenticado"); 
		    		throw new NoAutenticadoException("Usuario No autorizado");
	    		}
	    		else if( !usuario.equals( usuarios[0] ))
	    		{
	    			
	    			TransicionAutorVO[] compartidos = this.getSrvPublicacionService().obtenODEsCompartidosPorUsuarios(usuarios) ; 
	    			boolean compartido= false;
	    			
	    			for (int i = 0; i < compartidos.length && !compartido; i++) {
						if(compartidos[i].getIdODE().equals(identificador))
						{
							compartido= true;
						}
					}
	    			
	    			if(!compartido)
	    			{
			    		logger.info("El ODE '"+identificador+"' no se puede visualizar porque el usuario '"+usuario+"' no es el creador");
						request.setAttribute("codigo_error", "noautenticado"); 
			    		throw new NoAutenticadoException("Usuario No autorizado");
	    			}
	    		}
	    	}
	    	
    	}else
		{
    		estado= new EstadoVO();
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    	if(!visualizadorADL)
    	{
	    	
	    	// llamamos al servicio entregar para obtener las organizaciones.. 
	    	ArgObtOrganizacionesVO ode= new ArgObtOrganizacionesVO();
	    	ode.setIdentificador(identificador);
	    	ode.setIdioma(idioma);
	    	ode.setUsuario(usuario);
	    	ode.setIdiomaTitulo(idiomaTit);
	    	ManifestVO manifest=null;
	    	
	    	//Tendremos en cuenta que puede ser un ODE versionado y que la url desde la que se intenta
	    	//acceder sea la correspondiente a cuando el ODE estaba en su version anterior.
	    	//Probaremos a buscar todas las nuevas combinaciones de ids que puede tener el ODE.
	    	//El id de MEC solo puede haber cambiado en las posiciones que determinan en nivel de agregacion
	    	//y el idioma
	    	try {
	    		manifest = this.getSrvEntregarService().obtenerOrganizaciones(ode);
	    	
	    	} catch(Exception e1) {
	    		
		    	ArrayList<String> listaIdsMecPosibles=listarPosiblesIdsMecDeOdeVersionado(identificador);
		    	//Eliminamos de la lista el ID que ya se ha probado obtener
		    	listaIdsMecPosibles.remove(listaIdsMecPosibles.lastIndexOf(ode.getIdentificador()));
		    	
		    	boolean odeEncontrado=false;
		    	for(int i=0; i<listaIdsMecPosibles.size(); i++) {
		    		
		    		ode.setIdentificador(listaIdsMecPosibles.get(i));
			    	try {
			    		manifest = this.getSrvEntregarService().obtenerOrganizaciones(ode);
			    	} catch(Exception e2) {
			    		//No hacemos nada y seguimos intentando con otro ID de la lista
			    	}
			    	if (manifest!=null) {
			    		odeEncontrado=true;
			    		break;
			    	}
		    	}
		    	
		    	if(!odeEncontrado) {
					logger.error("no se pudo obtener el metadato correspondiente por un error en el servicio SrvEntregarService." , e1);
					request.setAttribute("codigo_error", "odenoencontrado");
		    		throw new OdeNoEncontradoException("ODE no encontrado");
		    	}
	    	}
	    	
			if( logger.isDebugEnabled() ){
	            logger.debug("VISUALIZADOR, CARGARTIPOVIS, VALOR DE SECUENCIA "+  secuencia);
			}
			
			//cargamos los datos en sesion
//			VisualizadorSession sesion=new VisualizadorSession();
//			this.setVisualizadorSession(request, sesion);
			VisualizadorSession sesion=initVisualizadorSession(request);
			
//			sesion.setSecuencia(form.isSecuencia());
			sesion.setUsuarioAdministrador(LdapUserDetailsUtils.esAdministrador());
	    	sesion.setUsuario(usuario);
	    	sesion.setIdentificador(identificador);
//			sesion.setIdiomaCat(idioma);
			sesion.setTituloOde(manifest.getTitulo());
	        sesion.setLocalizadorCont(manifest.getLocalizacionURL());
			OrganizacionVO[] orgs= manifest.getOrganizaciones();
			sesion.setOrganizaciones(Arrays.asList(orgs) );
//	    	sesion.setMenuDesplegado(true);
//	        if(orgs.length==1 && orgs[0].getItems()!=null && orgs[0].getItems().length==1)
//	        {
//	        	sesion.setMenuDesplegado(false);
//	        }
    		String urlImagen = ImagenODE.urlImagenODE(identificador);
			sesion.setUrlImagen(urlImagen);

	        
			sesion.setVerComentarios(false);
			sesion.setVerComentariosAlta(false);
			sesion.setVerComentariosBaja(false);
			sesion.setVerContenidoInapropiado(false);
			sesion.setVerEmbebido(false);
			sesion.setVerEstadisticas(false);
			sesion.setVerExportar(false);
			sesion.setVerFavorito(false);
			sesion.setVerItinerarios(false);
			sesion.setVerRecomendar(false);
			sesion.setVerSecuencia(form.isSecuencia());
			sesion.setVerShare(false);
			sesion.setVerTagging(false);
			sesion.setVerValorar(false);
			
	    	//Nueva sesión de ode
			OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, sesion.getIdentificador());
	    	
	    	odeSesion.setSecuencia(form.isSecuencia());
	    	odeSesion.setUsuarioAdministrador(LdapUserDetailsUtils.esAdministrador());
	    	odeSesion.setIdiomaCat(idioma);
	    	odeSesion.setTituloOde(manifest.getTitulo());
	    	odeSesion.setLocalizadorCont(manifest.getLocalizacionURL());
	    	odeSesion.setOrganizaciones(Arrays.asList(orgs) );
	    	odeSesion.setMenuDesplegado(true);
// 29012013 El menú se mostrará siempre desplegado	    	
//	        if(orgs.length==1 && orgs[0].getItems()!=null && orgs[0].getItems().length==1)
//	        {
//	        	odeSesion.setMenuDesplegado(false);
//	        }
	        odeSesion.setUrlImagen(urlImagen);
	        
//	    	odeSesion.setIdSeleccionado(sesion.getIdSeleccionado());
//	    	odeSesion.setRutaSeleccionado(sesion.getRutaSeleccionado());

	    	odeSesion.sethOrganizaciones(null);
	    	
	    	odeSesion.setVerComentarios(false);
	    	odeSesion.setVerComentariosAlta(false);
	    	odeSesion.setVerComentariosBaja(false);
	    	odeSesion.setVerContenidoInapropiado(false);
	    	odeSesion.setVerEmbebido(false);
	    	odeSesion.setVerEstadisticas(false);
	    	odeSesion.setVerRecomendar(false);
	    	odeSesion.setVerSecuencia(form.isSecuencia());
	    	odeSesion.setVerTagging(false);
	    	odeSesion.setVerValorar(false);
	        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    	//	usuario autenticado
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			if(LdapUserDetailsUtils.estaAutenticado()||this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SECUENCIA_SIN_LOGAR).equals("true"))
			{
				//Inicializamos ahora aqui los botones y el contador
//				sesion.setBtnDerecho(Boolean.FALSE);
//				sesion.setBtnIzquierdo(Boolean.FALSE);
//				sesion.setContador(-1); 
//				sesion.setIdSeleccionado("");
				sesion.setDatosSalida(null);
				sesion.setItemsFlow(null);
				
				odeSesion.setBtnDerecho(false);
				odeSesion.setBtnIzquierdo(false);
				odeSesion.setContador(-1); 
				odeSesion.setIdSeleccionado("");
				odeSesion.setDatosSalida(null);
				odeSesion.setItemsFlow(null);
				
				/*
				 * fjespino: Parametro urlCerrar para que la herramienta offline pueda abrir el visualizador en la misma pantalla.
				 */
				sesion.setUrlCerrar(request.getParameter("urlCerrar"));
				odeSesion.setUrlCerrar(request.getParameter("urlCerrar"));
				if( logger.isDebugEnabled() ){
					logger.debug("VISUALIZADOR, USUARIO AUTENTICADO " + sesion.getUsuario() );
				}
			}
			
			
			if(!DecisorOffline.esOffline())
			{
				if(LdapUserDetailsUtils.estaAutenticado())
				{
					sesion.setPerfilPublico(LdapUserDetailsUtils.tienePerfilPublico());	
				}
				
				
				sesion.setIdentidadFederada(LdapUserDetailsUtils.tieneIdentidadFederada(request));
				logger.debug("identidad federada " + sesion.isIdentidadFederada());
		    	if(sesion.isIdentidadFederada())
		    	{
			        sesion.setUsuarioOrigen(LdapUserDetailsUtils.getLogin());
					logger.debug("identidad federada: usuario " + sesion.getUsuarioOrigen());
					
			        String nodo= "" ;
			        if(!form.getNodoOrigen().toLowerCase().startsWith("http://") )
			        	nodo="http://" +  form.getNodoOrigen();
			        else
			        	nodo= form.getNodoOrigen();
			        sesion.setNodoOrigen(nodo);
			        logger.debug("identidad federada: nodo " + sesion.getNodoOrigen());
		    	}
				
				
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    	//	usuario autenticado y ODE PUBLICADO
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		    	if( AgregaProperties.PUBLICADO.equals(estado.getClave()))
		    	{
					sesion.setVerComentarios(true);
					sesion.setVerEmbebido(true);
					sesion.setVerEstadisticas(true);
					sesion.setVerRecomendar(true);
					sesion.setVerSecuencia(form.isSecuencia());
					sesion.setVerShare(true);
					
					odeSesion.setVerComentarios(true);
					odeSesion.setVerEmbebido(true);
					odeSesion.setVerEstadisticas(true);
					odeSesion.setVerRecomendar(true);
					odeSesion.setVerSecuencia(form.isSecuencia());
					odeSesion.setVerShare(true);
					
		    		if( LdapUserDetailsUtils.estaAutenticado() ||  sesion.isIdentidadFederada()) 
			        {
			    		logger.debug("Usuario autenticado y Ode Publicado...");
		
						sesion.setVerComentariosAlta(true);
						odeSesion.setVerComentariosAlta(true);
						if(LdapUserDetailsUtils.esAdministrador()) {
							sesion.setVerComentariosBaja(true);
							odeSesion.setVerComentariosBaja(true);
						}
						
						if(!sesion.isIdentidadFederada()) {
							sesion.setVerContenidoInapropiado(true);
							odeSesion.setVerContenidoInapropiado(true);
							
					    	//Incidencia 0001117
					    	//Si el usuario previsualiza un ode para el que ya ha realizado un comentario inapropiado,
					    	//se deshabilitar la opcion de vovler a enviar otro.
					    	ContenidoInapropiadoVO[] contenidoInapropiados = this.getSrvContenidoInapropiadoService().obtenerContenidosInapropiados();
					    	odeSesion.setVerContenidoInapropiado(puedeUsuarioCrearCI(contenidoInapropiados, usuario, identificador));
					    	sesion.setVerContenidoInapropiado(odeSesion.isVerContenidoInapropiado());
						}
						sesion.setVerExportar(true);
						sesion.setVerTagging(true);
						sesion.setVerValorar(true);
						
						odeSesion.setVerExportar(true);
						odeSesion.setVerTagging(true);
						odeSesion.setVerValorar(true);
			    		
						if(sesion.isPerfilPublico())
						{
							sesion.setVerFavorito(true);
							sesion.setVerItinerarios(true);
							odeSesion.setVerFavorito(true);
						}
			        }
		    	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    	//	usuario autenticado y ODE AUTO PUBLICADO 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		    	if( AgregaProperties.PUBLICADO_AUTONOMO.equals(estado.getClave())
			        	&& LdapUserDetailsUtils.estaAutenticado() ) 
		        {
		    		logger.debug("Usuario autenticado y Ode AUTO PUBLICADO...");
					sesion.setVerExportar(true);
					sesion.setVerSecuencia(form.isSecuencia());
					sesion.setVerShare(true);
					
					odeSesion.setVerExportar(true);
					odeSesion.setVerSecuencia(form.isSecuencia());
					odeSesion.setVerShare(true);
		        }
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			if(sesion.isVerExportar()||odeSesion.isVerExportar())
			{
				sesion.setTextoLicencia(cargarTextoLicencia(identificador));
				odeSesion.setTextoLicencia(cargarTextoLicencia(identificador));
		    	String[] formatos ={getPropertyValue("visualizador.descargas.formato.contenidos"),
		    			getPropertyValue("visualizador.descargas.formato.html"),
		    			getPropertyValue("visualizador.descargas.formato.metadatosPDF"),
		    			getPropertyValue("visualizador.descargas.formato.scorm2004ss"),
		    			getPropertyValue("visualizador.descargas.formato.scorm2004"),
		    			getPropertyValue("visualizador.descargas.formato.scorm12"),
		    			getPropertyValue("visualizador.descargas.formato.imscp")};
		
				sesion.setFormatosExportacion(formatos);				
			}	
			
			if(sesion.isVerEmbebido()||odeSesion.isVerEmbebido())
			{
				sesion.setContenidoEmbebido(generarUrlFicha(request,odeSesion));
				odeSesion.setContenidoEmbebido(generarUrlFicha(request,odeSesion));
			}
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//			valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			if(sesion.isVerValorar()||odeSesion.isVerValorar())
			{
				String usr="";
				if(sesion.isIdentidadFederada())
				{
		    		URL urlServer= new URL(sesion.getNodoOrigen());
					usr = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
				}else
				{
					usr= sesion.getUsuario();
				}
				
				try{
//					sesion.setValorado(this.getSrvValoracionService().tieneValoracion(usr, sesion.getIdentificador(), odeSesion.getIdiomaCat()));
//					sesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(sesion.getIdentificador()));
					
					odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usr, sesion.getIdentificador(), odeSesion.getIdiomaCat()));
					odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(sesion.getIdentificador()));
				}catch (Exception e) {
					logger.error("error al acceder al servicio de valoración " , e);
//					sesion.setValoracion("0.0");
//					sesion.setValorado(true);
					
					odeSesion.setValoracion("0.0");
					odeSesion.setValorado(true);
				}
				
				//Escribo datos en form
				form.setIdentificador(identificador);
				form.setSecuencia(odeSesion.isSecuencia());
			}
			
    	}
    	
		/*
		 * Session Timeout: Para evitar que caduque la sesion del empaquetador
		 * mientras se trabaja con el catalogador variamos el time-out de la
		 * sesion. En el punto de retorno se restaura al valor normal.
		 */
		request.getSession().setAttribute("timeout.visualizador", request.getSession().getMaxInactiveInterval());
		logger.debug("Timeout previo al cambio = " + request.getSession().getMaxInactiveInterval() + "s");
		String timeoutExtendido = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_EXTENDIDO);
		logger.debug("Modificando el timeout del visualizador a " + timeoutExtendido);
		request.getSession().setMaxInactiveInterval(Integer.parseInt(timeoutExtendido));
		//TODO Crear punto de retorno,para deshacer esto del timeout
    }









	private String generarUrlFicha(
			HttpServletRequest request, OdeSession odeSesion)
	{
		String textoAlt="";
		String urlFicha="";
		VisualizadorSession sesion= this.getVisualizadorSession(request);
		
		textoAlt = sesion.getTituloOde();

		urlFicha = ODEPublico.urlFichaODEPublicado(sesion.getIdentificador(), odeSesion.getIdiomaCat());
		StringBuffer resultado= new StringBuffer();
		resultado.append("<a href='"+ urlFicha+ "/embed' target='_blank'>");
		resultado.append("<img src='" + sesion.getUrlImagen() + "' alt='" + textoAlt + "' title='" + sesion.getIdentificador() + "'>");
		resultado.append("</a>");
		return resultado.toString();
	}


	
		private String cargarTextoLicencia(
				String identificdorODE)
		{
			try{
				LocalizadorVO loc= this.getSrvLocalizadorService().consultaLocalizador(identificdorODE);
				
				StringBuilder sb = new StringBuilder();
				File fileLicencia=new File(loc.getPath() + LICENCIA);
				String lineSep="\n";
				
				if(fileLicencia.exists()){
					logger.debug("Existe el fichero de texto de la licencia "+fileLicencia.getPath());
					BufferedReader br = new BufferedReader(new FileReader(fileLicencia));
					String texto="";
					while ((texto=br.readLine()) != null) {
						sb.append(texto).append(lineSep);
					 }
			 		br.close();
			 		return sb.toString().trim();
				}
					return null;
				
			}catch (Exception e) {
				logger.error("error al obtener la licencia.",e);
				return null;
			}
		}



		@Override
		public String seleccionarSecuencia(
				ActionMapping mapping,
				SeleccionarSecuenciaForm form,
				HttpServletRequest request,
				HttpServletResponse response) 
		throws Exception
		{
//			VisualizadorSession sesion= this.getVisualizadorSession(request);
			
//			if( sesion.isSecuencia())
//			if(OdeSessionUtils.getOdeSession(sesion, getInicioSession(request).getIdentificador()).isSecuencia())
			if(form.isSecuencia())
				return CON_SECUENCIA;
			
			return SIN_SECUENCIA;
		}



	@Override
	public String seleccionarTipoVisualizador(
			ActionMapping mapping,
			SeleccionarTipoVisualizadorForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{
        if(LdapUserDetailsUtils.estaAutenticado()) {
        	if(VISUALIZADOR_ADL.toLowerCase().equals(LdapUserDetailsUtils.getVisualizador())) 
        		return VISUALIZADOR_ADL;

        	return VISUALIZADOR_AGREGA;
        }
        return VISUALIZADOR_AGREGA;
	}


	private VisualizadorSession initVisualizadorSession(HttpServletRequest request) {
		VisualizadorSession sesion= getVisualizadorSession(request);
		if(sesion==null) {
			sesion = new VisualizadorSession();
			setVisualizadorSession(request,sesion);
		}
		return sesion;
	}
	

    private boolean puedeUsuarioCrearCI(ContenidoInapropiadoVO[] contenidoInapropiados, String usuario, String IdODE) {
		String logStr = "El usuario " + usuario + " tiene la propiedad de poder añadir Comen. Inapro.  sobre el ODE " + IdODE + " a ";	
    	for (ContenidoInapropiadoVO conInapr : contenidoInapropiados) {	
    		if (conInapr.getUsuario().equals(usuario) && conInapr.getIdOde().equals(IdODE)) {
    			logger.error(logStr.concat("false"));
    			return false;
    		}
		}
		logger.debug(logStr.concat("true"));
		return true;
	}
	    
    
    /**
     * Dado un ID de MEC de un ODE cualquiera, este metodo devuleve una lista los posibles 
     * IDs de MEC que puede tener la version de dicho ODE. En la lista se incluye el id MEC 
     * que se recibe como parametro.
	 * Este metodo esta hecho segun la implementacion de handleGeneraMEC de SrvPublicacionImpl
     * @param idMec
     * @return
     */
    private ArrayList<String> listarPosiblesIdsMecDeOdeVersionado(String idMec) {

    	String[] nivelesAgregacion={"1","2","3","4"};
    	//es=1,	ca=2, eu=3, gl=4, va=5, en=6, fr=7
    	String[] idiomas={"1","2","3","4","5","6","7"};
    	
    	ArrayList<String> listaIdsMecPosibles=new ArrayList<String>();
    	StringBuffer nuevoIdMec = new StringBuffer(idMec);
		
		//Analizamos el formato del ID de MEC de entrada para conocer las posiciones
		//del nivel de agregacion y del idioma

		//Posicion en el string de entrada donde esta especificado el nivel de agregacion
		//y el idioma. Lo inicializamos en 2 ya que las 2 primeras posiciones es siempre
		//el codigo 'es'
		int posicionAmodificar=2;
				
		//Vemos si el id Mec antiguo tiene la comunidad
		if(idMec.contains("-")) {
			posicionAmodificar+=3;	
		}
		
		String separador="_";
		String formatoFecha="yyyyMMdd";

		//Actualizamos posiciones ya que ahora vendria un separador 
		//y la fecha en formato yyyyMMdd
		posicionAmodificar+=separador.length()+formatoFecha.length();
		
		//Debemos devolver el las primeras posiciones del ArrayList auqellos ID MEC que 
		//tengan el mismo idioma que el que recibimos como argumento
		String idiomaIdMecEntrada=idMec.substring(posicionAmodificar, posicionAmodificar+1);
    			
		//Movemos el idioma del ID de MEC de entrada a la primera posicion de idiomas a probar
		ArrayList<String> listaIdiomas=new ArrayList<String>(Arrays.asList(idiomas));
		listaIdiomas.add(0,idiomaIdMecEntrada);
		listaIdiomas.remove(listaIdiomas.lastIndexOf(idiomaIdMecEntrada));
				
		String idiomaYnivelAgregacion="";
		for(int i=0; i<listaIdiomas.size(); i++) {
			for(int n=0; n<nivelesAgregacion.length; n++) {
				idiomaYnivelAgregacion=listaIdiomas.get(i)+nivelesAgregacion[n];
				nuevoIdMec=nuevoIdMec.replace(posicionAmodificar, posicionAmodificar+idiomaYnivelAgregacion.length(), idiomaYnivelAgregacion);
				listaIdsMecPosibles.add(nuevoIdMec.toString());
			}
		}
    	return listaIdsMecPosibles;
    }
	  	    
}