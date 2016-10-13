// license-header java merge-point
package es.pode.buscador.presentacion.basico.detallar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.serverProperties.DependentServerProperties;
import es.pode.auditoria.negocio.servicios.NumeroOperacionesVO;
import es.pode.buscador.presentacion.BuscarSession;
import es.pode.buscador.presentacion.avanzado.buscarAvanzado.BuscarAvanzadoControllerImpl;
import es.pode.buscador.presentacion.basico.descargar.DescargarControllerImpl;
import es.pode.buscador.presentacion.error.NoExisteOdeException;
import es.pode.buscador.presentacion.error.NoTienePermisosException;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.buscar.negocio.buscar.servicios.ContribucionOdeVO;
import es.pode.buscar.negocio.buscar.servicios.MetadatoBasicoVO;
import es.pode.buscar.negocio.buscar.servicios.MetadatoOiaOreVO;
import es.pode.buscar.negocio.buscar.servicios.ParametroMetadatoVO;
import es.pode.buscar.negocio.buscar.servicios.ParametrosBusquedaAvanzadaVO30;
import es.pode.buscar.negocio.buscar.servicios.ResultadoBusquedaVO;
import es.pode.buscar.negocio.buscar.servicios.SrvBuscarService;
import es.pode.buscar.negocio.buscar.servicios.ValoresBusquedaVO;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaService;
import es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceServiceLocator;
import es.pode.catalogacion.licencias.servicio.GruposLicenciasVO;
import es.pode.empaquetador.negocio.servicio.SrvFachadaAgregarService;
import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.estadisticas.EstadisticasProperties;
import es.pode.soporte.estadisticas.EstadisticasPropertiesImpl;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.url.Proxy;
import es.pode.valoracion.negocio.servicios.ComentarioVO;


/**
 * @see es.pode.buscador.presentacion.basico.detallar.DetallarController
 */
public class DetallarControllerImpl extends DetallarController{
	
   private static final long serialVersionUID = -8190956967834990490L;

	private java.util.Properties pSpringProperties = null;
	public static final String LAYOUT_BUSCADOR = "BUSCADOR";
	public static final String LAYOUT_EMPAQUETADOR = "EMPAQUETADOR";
	public static final String LAYOUT_FEDERADO = "FEDERADO";
	public static final String LAYOUT_NEUTRO = "NEUTRO";
	public static final String BUSCAR = "BUSCAR";
	public static final String BUSCARRSS = "BUSCARRSS";
	public static final String BUSCAR_SIMPLE = "BUSCAR_SIMPLE";
	public static final String BUSQUEDA_FEDERADA = "BUSQUEDA_FEDERADA";
	public static final String BUSQUEDA_ARBOL = "BUSQUEDA_ARBOL";
	public static final String BUSQUEDA_TESAURO = "BUSQUEDA_TESAURO";
	public static final String BUSQUEDA_DETALLE = "BUSQUEDA_DETALLE";
	public static final String DETALLE_FEDERADO="DETALLE_FEDERADO";
	private static final String BUSCARRELACIONADOSAC="BUSCARRELACIONADOSAC";//BUSQUEDA DE ODES RELACIONADOS POR ARBOL CURRICULAR
	public static final String SEPARADOR_PALABRAS = " ";
	public static final String AMBITO_UNIVERSAL = "universal";
	public static final String IDENTIFICADOR_NODO = "server.id";
	private final static String POSICIONADO_DETALLE = "POSICIONADO_DETALLE";
	private final static String POSICIONADO_DETALLE_EMBED = "POSICIONADO_DETALLEEMBED";
	private final static String DETALLE_SIN_POSICIONAMIENTO = "DETALLE_SIN_POSICIONAMIENTO";
	private final static String ODE_LOCAL = "ODE_LOCAL";
	private final static String ODE_FEDERADO = "ODE_FEDERADO";
	public static final String JAVA_QUOTES = "\"";
	public final static String DOT = "\\.";
	public final static String COMA = "\\,";
	public final static String SLASH = "\\/";
	public final static String DOUBLE_DOT = "\\:";
	public final static String OPEN_QUESTIONMARK = "\\¿";
	public final static String CLOSE_QUESTIONMARK = "\\?";
	public final static String OPEN_EXCLAMATIONMARK = "\\¡";
	public final static String CLOSE_EXCLAMATIONMARK = "\\!";
	public final static String OPEN_PARENTHESES = "\\(";
	public final static String CLOSE_PARENTHESES = "\\)";
	private final static String MENSAJE_ERROR_DETALLES = "listar.odecu.mostrar.resultados.detalles.errorObteniendoDetalles";
	public final static String MENSAJE_TRADUCCION_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorTraduccionBusqueda";
	public final static String MENSAJE_OBTENCION_VALORACION = "listar.odecu.mostrar.resultados.consulta.cabecera.errorObtencionValoracion";
	public final static String MENSAJE_OBTENCION_ESTADISTICA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorObtencionEstadistica";
	public final static String MENSAJE_AGREGACION_ODE = "listar.odecu.mostrar.resultados.consulta.detalles.errorAgregacionOde";
	public final static String MENSAJE_IDIOMAS_BUSQUEDA = "listar.odecu.mostrar.resultados.consulta.cabecera.errorIdiomasBusqueda";
	public final static String MENSAJE_OBTENCION_TAGS = "listar.odecu.mostrar.resultados.consulta.cabecera.errorObtencionTag";
	public final static String MENSAJE_OBTENCION_ODESRELACIONADOS = "listar.odecu.mostrar.resultados.consulta.cabecera.errorObtencionOde";
	public final static String MENSAJE_OBTENCION_MASDATOS = "listar.odecu.mostrar.resultados.consulta.cabecera.errorObtencionMasDatos";
	public final static String ERROR_OBTENIENDO_VALORACION = "introducir.comentarios.ode.errorObteniendoValoracion";
	private final static String ERROR_INSERTANDO_VALORACION = "introducir.comentarios.ode.errorInsertandoValoracion";
	private final static int NUM_ODES = 4;
	
	private static Logger logger = Logger.getLogger(DetallarControllerImpl.class);
	
	/**
     * @see es.pode.buscador.presentacion.basico.detallar.DetallarController#buscarDetalleODE(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.detallar.BuscarDetalleODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscarDetalleODE(ActionMapping mapping, es.pode.buscador.presentacion.basico.detallar.BuscarDetalleODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
    		List<String> errorMessages = new ArrayList<String>();
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	        BuscarSession sesion = this.getBuscarSession(request);
			if (sesion == null) {
				sesion = new BuscarSession();
				this.setBuscarSession(request, sesion);
				logger.debug("DetallarControllerImpl - buscarDetalleODE: Sesion de busqueda creada en busqueda avanzada. La sesion estaba vacia.");
			}
			if(form.getMostrarVuelta()==null || form.getMostrarVuelta().equals(""))form.setMostrarVuelta(mostrarVuelta(form));
			if(sesion.getExisteSesion()==null || sesion.getExisteSesion()){
				form.setExisteSesion(existeSesion(sesion) || (form.getNodoOrigen()!=null && !form.getNodoOrigen().equals("")));
				sesion.setExisteSesion(form.getExisteSesion());
			}
			
			if(form.getNodoOrigen()!=null && !form.getNodoOrigen().equals("") && form.getTipoLayoutBuscador()!=null && !form.getTipoLayoutBuscador().equals(LAYOUT_EMPAQUETADOR)
					&& !form.getTipoLayoutBuscador().equals(LAYOUT_FEDERADO)){
				form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_FEDERADO);
				sesion.setPosicionamientoAnterior(form.getPosicionamientoAnterior());
				sesion.setPosicionamientoSiguiente(form.getPosicionamientoSiguiente());
				sesion.setNodoOrigen(form.getNodoOrigen());
			}else if(form.getPosicionamientoAnterior()==null && form.getPosicionamientoSiguiente()==null){
				form.setPosicionamientoAnterior(sesion.getPosicionamientoAnterior());
				form.setPosicionamientoSiguiente(sesion.getPosicionamientoSiguiente());
			}
			
			if(form.getTipoLayoutBuscador()==null){
	        	try{
			    	if("true".equals(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.NEUTRO))) form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_NEUTRO);
			    	else form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);
	        	}catch(Exception e){
	        		logger.debug("No existe la propiedad neutro del agregaProperties");
	        		form.setTipoLayoutBuscador(DetallarControllerImpl.LAYOUT_BUSCADOR);	        		
	        	}
		    	
	        }
	        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Detallar ODE con identificadorODE ["+form.getIdentificadorODE()+"] e idioma de busqueda["+form.getIdioma()+"]");
	        ParametroMetadatoVO parametroMetadato=null;
	        
	        //Recogemos de plantillas el valor de origen_embed
	        String origen=request.getSession().getServletContext().getInitParameter("origen_embed");
		   	
	        if(form.getOrigen()!=null&&form.getOrigen().equalsIgnoreCase(origen)) {
	        	parametroMetadato = new ParametroMetadatoVO(form.getIdentificadorODE(),form.getIdioma(),POSICIONADO_DETALLE_EMBED);
	        } else {
	        	parametroMetadato = new ParametroMetadatoVO(form.getIdentificadorODE(),form.getIdioma(),POSICIONADO_DETALLE);
	        }
	        
	        MetadatoBasicoVO metadatos =null;
	        try{
	        	metadatos = this.getSrvBuscarService().solicitarMetadato(parametroMetadato);
	        }catch (Exception ex){
	    		logger.info("No se encontraron metadatos para el ODE con identificadorODE["+form.getIdentificadorODE()+"]");
	    		request.setAttribute("error_codigo", "odeNoExiste");
	    		request.setAttribute("nodoOrigen","http://"+LdapUserDetailsUtils.getHost() +LdapUserDetailsUtils.getSubdominio() + "/visualizadorcontenidos/Portada/Portada.do");
	    		throw new NoExisteOdeException();
	    	}	        
	        
	        if(!metadatos.getAmbito()[0].equals("universal")){//Si el ODE tiene ambito universal mostramos la ficha. 
	        	//Si el ODE tiene ambito no universal comprobamos si el usuario esta logado en el nodo o si tiene identidadFederada.
	        	//   -Si esta logado mostramos la ficha.
	        	//   -Si tiene identidad federada pedimos el idNodo del nodo origen de la petición y si pertenece al ambito del ODE mostramos 
	        	//    la ficha, en caso contrario mostramos pantalla de acceso restringido.
	        	//   -Si no esta logado y no tiene identidad federada mostramos pantalla de acceso restringido. 
	        	if(LdapUserDetailsUtils.estaAutenticado() || LdapUserDetailsUtils.tieneIdentidadFederada(request)){
	        		if(form.getNodoOrigen()!=null && !form.getNodoOrigen().equals("")){
		        		if(logger.isDebugEnabled())logger.debug("Comprobamos que el nodo origen pertenece al ambito del nodo");
		        		if(logger.isDebugEnabled())logger.debug("Obtenemos identificador del nodo origen["+form.getNodoOrigen()+"]");

		        		String idNodo="";
		        		String address = "http://"+form.getNodoOrigen()+"/buscar-1/services/SrvBuscarFederadaService";
		        		
		   	         	try {
		   	         		es.pode.buscar.negocio.federada.servicios.SrvBuscarFederadaServiceService srvBuscarFederadaService = new SrvBuscarFederadaServiceServiceLocator();
			   	         	((SrvBuscarFederadaServiceServiceLocator)srvBuscarFederadaService).setSrvBuscarFederadaServiceEndpointAddress(address);
			   	         	SrvBuscarFederadaService port =  srvBuscarFederadaService.getSrvBuscarFederadaService();
		  
		   	         		if(logger.isDebugEnabled())logger.debug(form.getNodoOrigen()+" #### srvBuscarFederadaEndPointAddress del fichero --> " + srvBuscarFederadaService.getSrvBuscarFederadaServiceAddress());	   	         	
		   	         		idNodo = port.obtenerIdentificadorNodo();
		   	         	} catch (Exception e) {	        	 
		   	         		logger.error("No se ha podido obtener el idNodo del nodo["+address+"]",e);
		   	         	}
		   	         	//comprobamos si el identificador devuelto pertenece al ambito del ode
		   	         	if(idNodo!=null && !idNodo.equals("")){
		   	         		boolean encontrado=false;
		   	         		SrvNodoService srvNodos = this.getSrvNodoService();
		   	         		NodoVO[] nodos = srvNodos.listarNodos();
		   	         		
		   	         		for(int i=0;i<metadatos.getAmbito().length && !encontrado;i++){
		   	         			if(metadatos.getAmbito()[i].equals(idNodo)){
		   	         				encontrado=true;
		   	         			}else{
		   	         				if(nodos!=null && srvNodos.existeNombreNodo(metadatos.getAmbito()[i]) ){
		   	         					String ambitoId="";
		   	         					for(int j=0;j < nodos.length && ("").equals(ambitoId);j++){
		   	         						if(nodos[j].getNodo().equals(metadatos.getAmbito()[i])){
		   	         							ambitoId=nodos[j].getIdNodo();
		   	         						}
		   	         					}
		   	         					if(idNodo.equals(ambitoId)){
		   	         						encontrado = true;
		   	         					}
		   	         				}
		   	         			}
		   	         		}
		   	         		if(!encontrado){
		   	         			if(logger.isDebugEnabled())logger.debug("No tiene permisos para mostrar el ode");
		   		        	 	request.setAttribute("error_codigo", "noTienePermisos");
		   		        	 	request.setAttribute("nodoOrigen","http://"+form.getNodoOrigen() + "/visualizadorcontenidos/Portada/Portada.do");
		   		        		throw new NoTienePermisosException();
		   	         		}
		   	         	}else {
	   	         			if(logger.isDebugEnabled())logger.debug("No tiene permisos para mostrar el ode");
	   		        	 	request.setAttribute("error_codigo", "noTienePermisos");
	   		        	 	request.setAttribute("nodoOrigen","http://"+form.getNodoOrigen() + "/visualizadorcontenidos/Portada/Portada.do");
	   		        		throw new NoTienePermisosException();
		   	         	} 	
	        		}
	        	}
	        	else{
//	        		response.sendRedirect("http://"+form.getNodoOrigen()+"/visualizadorcontenidos/Acceso/Acceso.do");
	        		if(logger.isDebugEnabled())logger.debug("No tiene permisos para mostrar el ode");
	        		request.setAttribute("error_codigo", "noTienePermisos");
	        		if(form.getNodoOrigen()!=null && !form.getNodoOrigen().equals(""))
	        			request.setAttribute("nodoOrigen","http://"+form.getNodoOrigen() + "/visualizadorcontenidos/Portada/Portada.do");
	        		else
	        			request.setAttribute("nodoOrigen","http://"+LdapUserDetailsUtils.getHost() +LdapUserDetailsUtils.getSubdominio() + "/visualizadorcontenidos/Portada/Portada.do");
	        		throw new NoTienePermisosException();
	        	}
	        }
	        
	        
	    	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Se han solicitado metadatos, comienza la traduccion");
    		String terminosTraducidos = traducirTerminos(new String []{metadatos.getIdioma()},getPropertyValue("idioma"),getPropertyValue("idioma.terminos"),idiomaNavegacion);
    		if(terminosTraducidos!=null)
    			form.setIdiomaODE(I18n.getInstance().obtenerIdiomaTraducido(terminosTraducidos,idiomaNavegacion));
    		else{
    			form.setIdioma("");
    			logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener idioma =["+metadatos.getIdioma()+"]");
        		errorMessages.add(MENSAJE_TRADUCCION_BUSQUEDA);
        	}

    		//Licencias
    		terminosTraducidos = traducirTerminos(metadatos.getLicencias(),getPropertyValue("licencias"),getPropertyValue("idioma.terminos"),idiomaNavegacion);
    		if(terminosTraducidos!=null)
    			form.setLicencias(terminosTraducidos);
    		else{
    			logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener licencias =["+Arrays.toString(metadatos.getLicencias())+"]");
    			if(metadatos.getLicencias()!=null)form.setLicencias(concatenaArrayString(metadatos.getLicencias()));
    			else form.setLicencias("");
        		errorMessages.add(MENSAJE_TRADUCCION_BUSQUEDA);
        	}

			String IdLicencia = getIdTermino(metadatos.getLicencias()[0],getPropertyValue("licencias"),getPropertyValue("idioma.terminos"));
	        GruposLicenciasVO[] gruposLicencias=getSrvGruposLicencias().obtieneGrupoLicencias(IdLicencia);
	        form.setIdLicencia(IdLicencia);

	        if (gruposLicencias.length>0) {
		        String[] grpsLicencias=new String[gruposLicencias.length];
		        for (int i=0; i<gruposLicencias.length; i++) 
		        	grpsLicencias[i]=gruposLicencias[i].getGrupoLicencias();
				form.setGruposLicencia(grpsLicencias);
	        } else {
				form.setGruposLicencia(null);
	        }	          

	        // 11042012 Se añade validación para que si no tiene destinatarios no intente traducirlos, ya que lo consideraría como un error
	        // 			cuando sólo es error cuando tiene destinatarios y no puede traducirlos.
	        if (metadatos.getDestinatarios()==null || metadatos.getDestinatarios().length==0)
	        {
    			form.setDestinatarios("");
	        }else
	        {
	    		terminosTraducidos = traducirTerminos(metadatos.getDestinatarios(),getPropertyValue("destinatarios"),getPropertyValue("idioma.terminos"),idiomaNavegacion);
	    		if(terminosTraducidos!=null)
	    			form.setDestinatarios(terminosTraducidos);
	    		else{
	    			logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener licencias =["+Arrays.toString(metadatos.getDestinatarios())+"]");
	    			form.setDestinatarios("");
	        		errorMessages.add(MENSAJE_TRADUCCION_BUSQUEDA);
	        	}
	        }

    		form.setFormato(concatenaArrayString(metadatos.getFormato()));
    		form.setTitulo(metadatos.getTitulo());
    		form.setAutor(concatenaArrayString(metadatos.getAutor()));
			//Introducimos el valor del titulo en la sesion
			if(form.getTipoLayoutBuscador()!=null && form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_EMPAQUETADOR))logger.debug("DetallarControllerImpl - agregarOdes: Se agrega ode federado al empaquetador con  IdOde:" +sesion.getEmpIdOde()+" IdDestino:"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador());
			if(form.getBusquedaSimpleAvanzada()!=null && !form.getBusquedaSimpleAvanzada().equals(""))sesion.setBusquedaSimpleAvanzada(form.getBusquedaSimpleAvanzada());
			else if(sesion.getBusquedaSimpleAvanzada()!=null && !sesion.getBusquedaSimpleAvanzada().equals(""))form.setBusquedaSimpleAvanzada(sesion.getBusquedaSimpleAvanzada());
			if(form.getNodoOrigen()!=null && !form.getNodoOrigen().equals(""))sesion.setNodoOrigen(form.getNodoOrigen());
			else if(sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().equals(""))form.setNodoOrigen(sesion.getNodoOrigen());
			if(form.getAreaCurricularBusqueda()!=null && !form.getAreaCurricularBusqueda().equals(""))sesion.setAreaCurricularBusqueda(form.getAreaCurricularBusqueda());
			if(form.getTesauroBusqueda()!=null && !form.getTesauroBusqueda().equals(""))sesion.setTesauroBusqueda(form.getTesauroBusqueda());
			if(form.getTipoNavegacion()!=null && !form.getTipoNavegacion().equals(""))sesion.setTipoNavegacion(form.getTipoNavegacion());
			
			sesion.setTitulo(metadatos.getTitulo());
			form.setAgregarFederado(false);
			form.setValoracion(metadatos.getValoracion().toString());		
			
	        form.setDescripcion(metadatos.getDescripcion());
	        form.setLocalizadorODE(metadatos.getLocalizadorODE());
	        form.setAmbito(concatenaArrayString(metadatos.getAmbito()));
	        form.setIdentificadorODE(metadatos.getIdentificadorODE());
	        form.setTamanio(convertirMB(metadatos.getTamanio()));
	        form.setImagen("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+comprobarImagen(LdapUserDetailsUtils.getHost(), metadatos.getImagen()));
	        form.setImagenAmpliada("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+ampliarImagen(comprobarImagen(LdapUserDetailsUtils.getHost(), metadatos.getImagen())));
	        	        
	        if (metadatos.getFechaPublicacion()!=null && !metadatos.getFechaPublicacion().equals("")){
	        	String fechaPublicacion=metadatos.getFechaPublicacion().substring(6,8) + "/" + metadatos.getFechaPublicacion().substring(4,6) + "/" + metadatos.getFechaPublicacion().substring(0,4);
	        	if(metadatos.getHoraPublicacion()!=null && !metadatos.getHoraPublicacion().equals("")) fechaPublicacion=fechaPublicacion+SEPARADOR_PALABRAS+metadatos.getHoraPublicacion().substring(0,2)+":"+metadatos.getHoraPublicacion().substring(2,4)+":"+metadatos.getHoraPublicacion().substring(4,6);
	        	form.setFechaPublicacion(fechaPublicacion);
	        }
	        //ESTADISTICAS DEL ODE
	        try{
		        NumeroOperacionesVO[] operaciones = this.getSrvAuditoriaServicio().obtenNumeroOperaciones(form.getIdentificadorODE());
		        String[] descargas = EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_DESCARGA).split(",");
		        int numDescargas=0;
		        for (int i=0;i<operaciones.length;i++){
		        	for(int j=0;j<descargas.length;j++){
		        		if (operaciones[i].getOperacion().equals(descargas[j])){
		        			numDescargas = numDescargas + operaciones[i].getNumeroOperaciones();
		        			if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: La estadística de "+descargas[j]+" es "+operaciones[i].getNumeroOperaciones());
		        		}
		        	}
		        	if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_VISTO))){
		        		Integer veces= operaciones[i].getNumeroOperaciones();
		        		form.setNumVecesVisto(veces.toString());
		        	}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_PREVISUALIZADO))){
		        		Integer veces= operaciones[i].getNumeroOperaciones();
		        		form.setNumVecesPrevisualizado(veces.toString());
		        	}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_ENVIO))){
		        		Integer veces= operaciones[i].getNumeroOperaciones();
		        		form.setNumVecesEnviado(veces.toString());
		        	}else if (operaciones[i].getOperacion().equals(EstadisticasPropertiesImpl.getInstance().getProperty(EstadisticasProperties.PROPERTY_OPERACIONES_EMBED))){
	        			Integer veces= operaciones[i].getNumeroOperaciones();
		        		form.setNumVecesEmbed(veces.toString());
		        	}
			        	
		        }
		        form.setNumVecesDescargado(String.valueOf(numDescargas));
		        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE Estadísticas: Descargado ["+numDescargas+"] Visto ["+form.getNumVecesVisto()+"] Previsualizado ["+form.getNumVecesPrevisualizado()+"] Enviado ["+form.getNumVecesEnviado()+"]");
	        }catch(Exception ex){
	        	form.setNumVecesPrevisualizado("");
	        	form.setNumVecesEnviado("");
	        	form.setNumVecesVisto("");
	        	form.setNumVecesDescargado("");
        		logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener la estadística del ODE para el identificador de ode=["+form.getIdentificadorODE()+"]",ex);
        		errorMessages.add(MENSAJE_OBTENCION_ESTADISTICA);
        	}
	        
	        try{
	        	String[] etiquetas =this.getSrvTaggingServer().obtenerTagsDeOde(form.getIdentificadorODE());
	        	form.setEtiquetas(etiquetas);
//	        	if (etiquetas!=null && etiquetas.length>0){
//	        		form.setExistenEtiquetas(true);
//	        	}else{
//	        		form.setExistenEtiquetas(false);
//	        	}
	        	form.setExistenEtiquetas(etiquetas!=null && etiquetas.length>0);
	        	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Existen ["+etiquetas.length+"] etiquetas para el ODE con identificador ["+form.getIdentificadorODE()+"]");
	        }catch(Exception ex){
	        	logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener las etiquetas del ODE para el identificador de ode=["+form.getIdentificadorODE()+"]",ex);
        		errorMessages.add(MENSAJE_OBTENCION_TAGS);
        		form.setExistenEtiquetas(false);
	        }
	        //BUSQUEDA DE ODE's RELACIONADOS
	        try{
	        	if(metadatos.getAreaCurricular()!=null && metadatos.getAreaCurricular().length>0){
	        	
	        		ParametrosBusquedaAvanzadaVO30 parametros=new ParametrosBusquedaAvanzadaVO30();
	        	
	        		parametros.setIdiomaBusqueda(form.getIdioma());
	        		parametros.setNumeroResultados(NUM_ODES);
	        		parametros.setIdiomaNavegacion(idiomaNavegacion);
	        		String arbolVigente=this.getSrvTaxonomiaService().obtenerArbolVigente().getVocabIdentifier();
	        		String[] areaCurricular=new String[metadatos.getAreaCurricular().length];
	        	
	        		parametros.setBusquedaSimpleAvanzada(BUSCARRELACIONADOSAC);
	        	
	        		for (int j = 0; metadatos.getAreaCurricular()!=null && j < metadatos.getAreaCurricular().length; j++) {
	        			String area = sortAreaCurricularDescendente(metadatos.getAreaCurricular()[j].split("/"),metadatos.getAreaCurricular()[j].split("/").length);
	        			areaCurricular[j]=arbolVigente+"/"+area;
	        		}
	        		parametros.setTaxonomia(areaCurricular);
	        	
	        		ValoresBusquedaVO[] resultadosBusqueda=this.getSrvBuscarService().buscarAvanzado(parametros).getResultadoBusqueda();
	        
	        		logger.debug("DetallarControllerImpl - ODE RELACIONADO:"+resultadosBusqueda.length+"]");
	        	
	        		if(resultadosBusqueda!=null && resultadosBusqueda.length>0){
	        			//Vamos a coger todos los ode's Relacionados quitando el que hemos seleccionado
	        			int numOdes=resultadosBusqueda.length;
//	        			ValoresBusquedaVO[] odesRelacionados = new ValoresBusquedaVO[NUM_ODES-1]; 
	        			int j=0;
	        			ArrayList<ValoresBusquedaVO> aOdesRelacionados = new ArrayList<ValoresBusquedaVO>();
	        			for(int i=0;i<numOdes;i++){
	        				if( (aOdesRelacionados.size() < (NUM_ODES-1)) && !form.getIdentificadorODE().equalsIgnoreCase(resultadosBusqueda[i].getId())){
	        					// 14112012
	        					// Verificamos si exite la imagen sino ponemos la de por defecto
	        					resultadosBusqueda[i].setUrlImagen("http://" + LdapUserDetailsUtils.getHost() + LdapUserDetailsUtils.getSubdominio() + comprobarImagen(LdapUserDetailsUtils.getHost(), resultadosBusqueda[i].getUrlImagen()));
	        					aOdesRelacionados.add(resultadosBusqueda[i]);
//	        					odesRelacionados[j]=resultadosBusqueda[i];
	        					j++;
	        					form.setExistenOdesRelacionados(true);
	        				}
	        			}
	        			if(aOdesRelacionados.size()>0){
	        				ValoresBusquedaVO[] odesRelacionados = aOdesRelacionados.toArray(new ValoresBusquedaVO[0]);
	        				form.setOdesRelacionados(odesRelacionados);
	        			}
	        		}
	        	}	
	        }catch(Exception ex){
	        	logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener los Odes Relacionados para el identificador de ode=["+form.getIdentificadorODE()+"]",ex);
        		errorMessages.add(MENSAJE_OBTENCION_ODESRELACIONADOS);
        		form.setExistenEtiquetas(false);
	        }
	        //Orientacion didáctica,palabras clave y tipo de recurso
	        	try{
	        	if(metadatos.getOrientacionDidactica()!=null && metadatos.getOrientacionDidactica().length>0 &&!metadatos.getOrientacionDidactica()[0].equalsIgnoreCase(""))
	        	{
	        	form.setOrientacionDidactica(metadatos.getOrientacionDidactica());
	        	}
	        	if(metadatos.getPalabrasClave()!=null&&!metadatos.getPalabrasClave()[0].equalsIgnoreCase(""))
	        	{
	        	form.setPalabrasClave(metadatos.getPalabrasClave());
	        	}
	        	if(metadatos.getTipoRecurso()!=null&&!metadatos.getTipoRecurso()[0].equalsIgnoreCase(""))
	        	{
	        	String tipoRecurso = traducirTerminos(metadatos.getTipoRecurso(),getPropertyValue("tipoRecurso"),getPropertyValue("idioma.terminos"),idiomaNavegacion);
	            	
	        	form.setTipoRecursos(tipoRecurso);
	        	}
	        
	        //Contribuciones/Entidad
	        	
	        	ContribucionOdeVO[] contribuciones=metadatos.getContribuciones();
	        	if(contribuciones!=null)
	        	{
	        	form.setContribucion(contribuciones);
	        	
	        	for(int i=0;i<contribuciones.length;i++)
	        	{
	        	 if (contribuciones[i].getFecha()!=null && !contribuciones[i].getFecha().equals("")){
	  	        	String fechaContribucion=contribuciones[i].getFecha().substring(8,10) + "/" + contribuciones[i].getFecha().substring(5,7) + "/" + contribuciones[i].getFecha().substring(0,4)+SEPARADOR_PALABRAS;
	  	        	if(contribuciones[i].getFecha().length()>10)fechaContribucion=fechaContribucion+contribuciones[i].getFecha().substring(11,19);
	  	        	form.getContribucion()[i].setFecha(fechaContribucion);
	  	        	String tipo = traducirTerminos(new String[]{contribuciones[i].getTipoContribucion()},getPropertyValue("tipoContribucion"),getPropertyValue("idioma.terminos"),idiomaNavegacion);
	  	        	form.getContribucion()[i].setTipoContribucion(tipo);
	  		    
	  	        }
	        	}
	        	}
	        	
	
	        }catch(Exception ex){
	        	logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener las etiquetas del ODE para el identificador de ode=["+form.getIdentificadorODE()+"]",ex);
        		errorMessages.add(MENSAJE_OBTENCION_MASDATOS);
        		form.setExistenEtiquetas(false);
	        }
	        
	        //METADATOS OAI-ORE
	        try{ 
	        	ParametroMetadatoVO parametros = new ParametroMetadatoVO();
	        	parametros.setIdentificadorODE(form.getIdentificadorODE()); 
	        	parametros.setIdioma(form.getIdioma());
	        	MetadatoOiaOreVO metadatosOaiOre = this.getSrvBuscarService().solicitarMetadatosOaiOre(parametros );
	        	form.setDatosOaiOre(metadatosOaiOre);
	        }catch(Exception ex){
	        	logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener los metadatos para OAIORE del identificador de ode=["+form.getIdentificadorODE()+"]",ex);
	        }
	        
	        if(form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_BUSCADOR)){
	        	try{
	        		form.setUsuarioLogado(LdapUserDetailsUtils.estaAutenticado());
	        		if(LdapUserDetailsUtils.esAdministrador()||LdapUserDetailsUtils.esDocente())
	        			form.setUsuarioValido(true);
	        		else
	        			form.setUsuarioValido(false);
	        		
	        		form.setTieneIdentidadFederada(false);
		        	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: UsuarioLogado: "+form.getUsuarioLogado());
		        }catch (Exception ex){
	        		logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error obteniendo autenticacion del usuario.",ex);
	        		form.setUsuarioLogado(false);
	        		form.setTieneIdentidadFederada(false);
	        		errorMessages.add(MENSAJE_ERROR_DETALLES);
	        	}
			}else if (form.getTipoLayoutBuscador().equals(DetallarControllerImpl.LAYOUT_FEDERADO)){
				if(form.getTieneIdentidadFederada() ==null || !form.getTieneIdentidadFederada()){
					if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Comprobamos si tiene identidad federada.");
					form.setTieneIdentidadFederada(LdapUserDetailsUtils.tieneIdentidadFederada(request));
					if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: ¿Tiene identidad federada?  ---> " + form.getTieneIdentidadFederada());
				}
				form.setUsuarioLogado(LdapUserDetailsUtils.estaAutenticado());
			}
			else {
				form.setUsuarioLogado(false);
				form.setTieneIdentidadFederada(false);
			}

	        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Se ha realizado la traducción, comienza la obtención de número de comentarios");
	        try{
	        	ComentarioVO[] comentarios = this.getSrvValoracionService().obtenerComentarios(form.getIdentificadorODE());
		        if (comentarios!=null){
		        	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Existen ["+comentarios.length+"] comentarios");
		        	form.setNrComentarios(String.valueOf(comentarios.length));
		        }else{
		        	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: No existen comentarios");
		        	form.setNrComentarios("0");
		        }
		        if( form.getUsuarioLogado()){
		        	form.setEstaValorado(this.getSrvValoracionService().tieneValoracion(LdapUserDetailsUtils.getUsuario(), form.getIdentificadorODE(), form.getIdioma()));
		        }else if(form.getTieneIdentidadFederada()){
		        	form.setEstaValorado(this.getSrvValoracionService().tieneValoracion(LdapUserDetailsUtils.getUsuario() + "@" + form.getNodoOrigen(), form.getIdentificadorODE(), form.getIdioma()));
		        }
	        }catch (Exception ex){
	        	form.setNrComentarios("0");
        		logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error al obtener la valoración del ODE para el identificador de ode=["+form.getIdentificadorODE()+"]",ex);
        		errorMessages.add(MENSAJE_OBTENCION_VALORACION);
        	}
	        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Detalle del ODE  identificadorODE ["+form.getIdentificadorODE()+"]:idioma["+metadatos.getIdioma()+"] ambito["+concatenaArrayString(metadatos.getAmbito())+"] licencias["+concatenaArrayString(metadatos.getLicencias())+"] titulo["+metadatos.getTitulo()+"] valoracion["+metadatos.getValoracion()+"] destinatarios["+concatenaArrayString(metadatos.getDestinatarios())+"] descripcion["+metadatos.getDescripcion()+"] localizadorODE["+metadatos.getLocalizadorODE()+"] formato["+Arrays.toString(metadatos.getFormato())+"]");
	        
	        //Se analiza la secuencia
	        if(metadatos.getConSinSecuencia() == null)metadatos.setConSinSecuencia(false);
	        form.setSeleccionarSecuencia(metadatos.getConSinSecuencia());
	        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: NivelAgregacion="+metadatos.getNivelAgregacion()+" SeleccionarSecuencia="+form.getSeleccionarSecuencia());

			if(errorMessages!=null && errorMessages.size()>0){
	        	if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: Existen errores en la traduccion de terminos para el detalle="+errorMessages.toString());
				saveErrorMessage(request, errorMessages);
			}
	        if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - buscarDetalleODE: BuscadorEmpaquetador:"+form.getTipoLayoutBuscador());
//        Nos falta por rellenar los parametros que nos pueden hacer falta para el boton de atras.
	        rellenaFormularioDesdeSesion(request, form);  

	        

    	}catch (java.net.ConnectException cEx){
    		logger.error("DetallarControllerImpl - buscarDetalleODE ERROR: Error en la conexión a servicios con metadatos para ODE con identificadorODE["+form.getIdentificadorODE()+"]",cEx);
    		saveErrorMessage(request,MENSAJE_ERROR_DETALLES);
    	}
     }
    
    private static String sortAreaCurricularDescendente(String [] array, int len){
		int a,b;
		String temp;
		int sortTheStrings = len - 1;
		for (a = 0; a < sortTheStrings; ++a)
		for (b = 0; b < sortTheStrings; ++b)
		if(array[b].compareTo(array[b + 1]) < 0){
			temp = array[b];
			array[b] = array[b + 1];
			array[b + 1] = temp;
		}
		return array[0];
	}
    
    public void valorarODE(ActionMapping mapping, ValorarODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	logger.debug("DetallarControllerImpl - valorarODE: identificadorODE="+form.getIdentificadorODE()+" idioma="+form.getIdioma()+" valoracion="+form.getValoracion());
        Float valoracion;        
        try {
        	valoracion = Float.parseFloat(form.getValoracion());
		} catch (Exception e) {
			logger.error("Error convirtiendo valoracion["+form.getValoracion()+"] a float.",e);
			saveErrorMessage(request, DetallarControllerImpl.ERROR_OBTENIENDO_VALORACION);
			return; // no progresamos la insercion del comentario.
		}
		try {

			String usuario= LdapUserDetailsUtils.getUsuario();
			if(form.getTieneIdentidadFederada()!=null && form.getTieneIdentidadFederada()){
	        	usuario= usuario + "@" + form.getNodoOrigen();
	        }
			this.getSrvValoracionService().introducirValoracion(valoracion, form.getIdentificadorODE(), usuario, form.getIdioma());			
		} catch (Exception e) {
			logger.error("Error insertando valoracion con idODE["+form.getIdentificadorODE()+"] idiomaODE["+form.getIdioma()+"] y autor["+LdapUserDetailsUtils.getLogin()+"]",e);
			saveErrorMessage(request, DetallarControllerImpl.ERROR_INSERTANDO_VALORACION);
		}		
	}
    
	public void prepararQueEsEsto(ActionMapping mapping, PrepararQueEsEstoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - prepararQueEsEsto");
	}
    
    /*
	 * Este metodo extrae de la sesion los parametros de busqueda que se han utilizado para llegar hasta esta pantalla
	 * de detalles de busqueda para poder realizar la misma busqueda y llegar a la misma pagina de resultados en el
	 * caso de que se pulse el boton de atras.
	 * */
	public String determinaAtras(ActionMapping mapping, DeterminaAtrasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Tenemos que acceder al parametro de la sesion que nos indica la ultima busqueda que se hizo,
//		desde donde se hizo (avanzada o basica) y dependiendo del resultado, rellenamos el formulario con los parametros
//		de la busqueda avanzada o basica dependiendo de a donde tengamos que ir.
		try{
			//Consultamos la sesion
			BuscarSession sesion = this.getBuscarSession(request);
			if (sesion == null || sesion.getBusquedaSimpleAvanzada()==null){
	//			Si no hay sesion, pinto la traza e invoco una busqueda simple a secas sin parametros. Esto desenvocara
	//			en una pagina de sugerencias vacia.
				logger.error("Error obteniendo la sesion al pulsar atras en los detalles de Avanzada/Basica["+form.getBusquedaSimpleAvanzada()+"]buscadorContenido["+form.getBuscadorContenido()+"]idioma["+form.getIdiomaBuscador()+"], enviamos al usuario a la busqueda simple sin argumentos.");
				form.setBuscadorContenido("");
				form.setIdiomaBuscador("");
				form.setPagina("1");
				return BUSCAR;
	//			 Si la búsqueda fue simple  o avanzada
			}else if (sesion.getBusquedaSimpleAvanzada().equals(BUSCAR)){
	//			 Si además fué federada
				if(sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().trim().equals("")){
					if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - determinaAtras: atras a listado busqueda federado:"+sesion.getNodoOrigen());
					response.sendRedirect("http://"+sesion.getNodoOrigen()+"/"+request.getSession().getServletContext().getInitParameter("url_buscadorAvanzado"));	
					return BUSQUEDA_FEDERADA;
				}
				if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - determinaAtras: atras a listado busqueda: Tipo busqueda="+sesion.getTipoBusqueda()+" busquedaSimpleAvanzada="+sesion.getBusquedaSimpleAvanzada());
				form.setTipoBusqueda(sesion.getTipoBusqueda());
				form.setBusquedaSimpleAvanzada(sesion.getBusquedaSimpleAvanzada());
				
				if(sesion.getEnlaceTaxSelec()!=null&&!sesion.getEnlaceTaxSelec().equalsIgnoreCase(""))
				{
	
					form.setEnlaceTaxSelec(sesion.getEnlaceTaxSelec());
				}
				if(sesion.getIdTesauro()!=null &&!sesion.getIdTesauro().equalsIgnoreCase(""))
				{
					form.setIdTesauro(sesion.getIdTesauro());
				}
				return BUSCAR;
			}
		}catch (Exception ex){
    		logger.error("DetallarControllerImpl - determinaAtras ERROR: Error al determinar la vuelta.",ex);
    	}
    	return BUSCAR;
	}
	
	public void agregarODEFederado(ActionMapping mapping, AgregarODEFederadoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			BuscarSession sesion = this.getBuscarSession(request);
			String literalFormato = getPropertyValue(DescargarControllerImpl.FORMATO_POR_DEFECTO);
			if (sesion == null) {
				logger.error("DetallarControllerImpl - agregarODEFederado: Sesion perdida desde comienzo agregación");
				form.setAgregacionCorrecta(false);
				saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
			}else{
				try{
					if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - agregarODEFederado: Descargar ODE con identificadorODE ["+form.getIdentificadorODEFederado()+"] y formato["+literalFormato+"] desde["+form.getNodoDestino()+"] y url="+form.getUrlEntregar());
					try{
						if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - agregarODEFederado: Se agrega ode federado al empaquetador con  IdOde:" +sesion.getEmpIdOde()+" IdDestino:"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador()+" y DataHandler del ODE");
						SrvFachadaAgregarService agregarService = this.getSrvFachadaAgregarService();
						agregarService.agregarFederado(sesion.getEmpIdOde(), sesion.getEmpIdDestino(), sesion.getEmpTipoEmpaquetador(), "http://"+form.getNodoDestino()+form.getUrlEntregar());
				    	form.setAgregacionCorrecta(true);
			    	} catch (Exception ex){
						logger.error("DetallarControllerImpl - agregarODEFederado: Error agregando al empaquetador DataHandler del ODE con identificadorODE["+form.getIdentificadorODEFederado()+"] y formato["+literalFormato+"]",ex);
						form.setAgregacionCorrecta(false);
					}
					
				} catch (Exception ex){
	    			logger.error("DetallarControllerImpl - agregarODEFederado: Error en la obtencion del fichero ZIP del ODE con identificadorODE["+form.getIdentificadorODEFederado()+"] y formato["+literalFormato+"]",ex);
	    			saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
	    			form.setAgregacionCorrecta(false);
	    		}
			}
		}catch (Exception ex){
			logger.error("DetallarControllerImpl - agregarODEFederado:Error agregando ode federado, Error en la obtencion del fichero ZIP del ODE con identificadorODE["+form.getIdentificadorODEFederado()+"] y formato por defecto ["+DescargarControllerImpl.FORMATO_POR_DEFECTO+"]",ex);
			saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
		}
	}

	public String analizaEntrada(ActionMapping mapping, AnalizaEntradaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(form.getIdentificadorODEFederado()!=null && !form.getIdentificadorODEFederado().equals("")){
				if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - analizaEntrada: NODO DESTINO="+form.getNodoDestino()+" e identificador ODE federado="+form.getIdentificadorODEFederado());
				return DETALLE_FEDERADO;
			}else if(form.getPosicionamiento()!=null && !form.getPosicionamiento().equals("")){
				return POSICIONADO_DETALLE;
			}else if(!existeSesion(this.getBuscarSession(request))){
				return DETALLE_SIN_POSICIONAMIENTO;
			}
		}catch (Exception ex){
			logger.error("DetallarControllerImpl - analizaEntrada: Error entrada caso de uso",ex);
			saveErrorMessage(request, MENSAJE_ERROR_DETALLES);
		}
		return "DETALLE";
	}
	
	public void agregarOdes(ActionMapping mapping, AgregarOdesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			BuscarSession sesion = this.getBuscarSession(request);
			if (sesion == null) {
				if (logger.isDebugEnabled())logger.error("DetallarControllerImpl - agregarOdes: Sesion perdida desde comienzo agregación");
				form.setAgregacionCorrecta(false);
				saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
			}else{
	//			 ODE federado, devuelvo DataHandler al empaquetador
				try {
					if(sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().trim().equals("")){
						SrvEntregarService entregarService = this.getSrvEntregarService();
						PaquetePifVO resultadoEntregar = entregarService.generarPaquetePIFTipoPIF(new TipoPifVO(form.getIdentificadorODE(), getPropertyValue("descargar.formatos.SCORM_2004_VALUE"), form.getIdioma()));
						if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - agregarOdes: Se prepara la redireción al nodoOrigen["+sesion.getNodoOrigen()+"] desde nodo destino ["+LdapUserDetailsUtils.getHost()+"]");
						form.setTipoAgregacion("AGREGACION_FEDERADA");
						response.sendRedirect("http://"+sesion.getNodoOrigen()+"/"+request.getSession().getServletContext().getInitParameter("url_buscadorDetalle")+"?identificadorODEFederado="+form.getIdentificadorODE()+"&tipoLayoutBuscador="+LAYOUT_EMPAQUETADOR+"&nodoDestino="+LdapUserDetailsUtils.getHost()+"&urlEntregar="+resultadoEntregar.getHref());
					}else{
						if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - agregarOdes: Se agrega ode local al empaquetador con  IdOde:" +sesion.getEmpIdOde()+" IdDestino:"+sesion.getEmpIdDestino()+" TipoEmpaquetador:"+ sesion.getEmpTipoEmpaquetador()+" e IdentificadorODE()a agregar: "+form.getIdentificadorODE());
						SrvFachadaAgregarService agregarService = this.getSrvFachadaAgregarService();
						agregarService.agregar(sesion.getEmpIdOde(), sesion.getEmpIdDestino(), sesion.getEmpTipoEmpaquetador(), form.getIdentificadorODE());
						form.setAgregacionCorrecta(true);
						form.setTipoAgregacion("AGREGACION_LOCAL");
						if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - agregarOdes: ODE agregado");
					}
				} catch (Exception ex){
	    			logger.error("DetallarControllerImpl - agregarOdes: Error agregando al empaquetador local identificadorODE["+form.getIdentificadorODE()+"]",ex);
	    			saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
	    			form.setAgregacionCorrecta(false);
	    		}
			}
		}catch (Exception ex){
			logger.error("DetallarControllerImpl - agregarOdes ERROR: Agregando ode local desde buscador empaquetador",ex);
			saveErrorMessage(request, MENSAJE_AGREGACION_ODE);
		}
	}
	
	public String analizaAgregacion(ActionMapping mapping, AnalizaAgregacionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(form.getTipoAgregacion()!=null && form.getTipoAgregacion().equals("AGREGACION_FEDERADA")) return form.getTipoAgregacion();
		}catch (Exception ex){
			logger.error("DetallarControllerImpl - analizaAgregacion ERROR: Analizando tipo agregación buscador empaquetador",ex);
		}
		return "AGREGACION_LOCAL";
	}
	
	public void cargarMetadatosCatalogador(ActionMapping mapping, CargarMetadatosCatalogadorForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(LdapUserDetailsUtils.estaAutenticado()){
				if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - cargarMetadatosCatalogador: Se cargan metadatos para IdentificadorODE="+form.getIdentificadorODE());
				this.getSrvFachadaAgregarService().prepararMetadatos(form.getIdentificadorODE());
				if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - cargarMetadatosCatalogador: Se redirige a el catalogador con IdentificadorODE="+form.getIdentificadorODE()+", idioma="+LdapUserDetailsUtils.getIdioma()+" y usuario="+LdapUserDetailsUtils.getUsuario());
				response.sendRedirect("http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/catalogadorWeb/VerMetadatos/VerMetadatos.do?identificador="+form.getIdentificadorODE()+"&idioma="+LdapUserDetailsUtils.getIdioma()+"&usuario="+LdapUserDetailsUtils.getUsuario());
			}	
		} catch (Exception ex) {
			logger.error("DetallarControllerImpl - cargarMetadatosCatalogador ERROR: Error en la carga de metadatos en el empaquetador para IdentificadorODE="+form.getIdentificadorODE(),ex);
		}
	}
	
	public void gestionarBotonera(ActionMapping mapping, GestionarBotoneraForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			BuscarSession sesion = this.getBuscarSession(request);
			if(sesion.getPagina()!=null && form.getResultados().getResultadoBusqueda()!=null && form.getResultados().getResultadoBusqueda().length > 0){
		        for(int i = 0; i < form.getResultados().getResultadoBusqueda().length; i++){
		        	if(form.getResultados().getResultadoBusqueda()[i].getId()!=null && form.getResultados().getResultadoBusqueda()[i].getId().equals(form.getIdentificadorODE())){
		        		form.setNodoDestino(form.getResultados().getResultadoBusqueda()[i].getNodo());
		        		if(i==0 && sesion.getPagina().intValue()==1 && form.getResultados().getNumeroResultados().intValue()==1){
		        			form.setPosicionamientoAnterior("");
		        			form.setPosicionamientoSiguiente("");
		        			break;
		        		}else if(i==0 && sesion.getPagina().intValue()==1 && form.getResultados().getNumeroResultados().intValue()>1){
		        			form.setPosicionamientoAnterior("");
		        			form.setPosicionamientoSiguiente("SIGUIENTE");
		        			break;
		        		}else if(i==form.getResultados().getResultadoBusqueda().length-1 && (form.getResultados().getNumeroResultados().intValue()/sesion.getPagina().intValue())<=Integer.parseInt(request.getSession().getServletContext().getInitParameter("max_res_pagina"))){
		        			form.setPosicionamientoAnterior("ANTERIOR");
		        	        form.setPosicionamientoSiguiente("");
		        	        break;
		        		}else{
		        			form.setPosicionamientoAnterior("ANTERIOR");
		        	        form.setPosicionamientoSiguiente("SIGUIENTE");
		        	        break;
		        		}
		        	}
		        }
			}
		} catch (Exception ex) {
			logger.error("DetallarControllerImpl - gestionarBotonera ERROR: Error gestionando la botonera de posicionado",ex);
		}
	}
	
	
	//Esta funcion es una apaño para poder compatibilizar aquellos nodos que tiene instalados un Agrega2 con dos JBoss (4.X.X), 
	//con los nodos que tengan un solo JBoss 5.1.0. Esto es debido a que en la implementacion con un solo JBOss la diferencia entre el buscador publico y el privado
	//se da en la url poniendo una referencia a buscador2 o buscador respectivamente. Los nodos con dos JBoss no entienden URLs que 
	//contengan la referencia a buscador2.
	private String comprobarEnlaceFichaOde(String url){		
		try{
			new InputStreamReader(Proxy.getInputStream(new URL(url), 2000));
		}catch(Exception ex){	
			if(url.contains("buscador2"))
				return url.replace("buscador2", "buscador");
		}
		return url;
	}
	

	public String analizaPosicionamiento(ActionMapping mapping, AnalizaPosicionamientoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(form.getNodoDestino() != null && !form.getNodoDestino().trim().equals("")){
				BuscarSession sesion = this.getBuscarSession(request);
				//"http://"+form.getNodoDestino()+"/"
				String destino = "http://"+form.getNodoDestino()+"/"+request.getSession().getServletContext().getInitParameter("url_buscadorDetalle")+
					"?idioma="+form.getIdioma()+
					"&identificadorODE="+((form.getIdentificadorODE()!=null)?form.getIdentificadorODE():"")+
					"&tipoLayoutBuscador="+((sesion.getTipoLayoutBuscador()!=null)?sesion.getTipoLayoutBuscador():DetallarControllerImpl.LAYOUT_BUSCADOR)+
					"&nodoOrigen="+((LdapUserDetailsUtils.getHost()!=null)?LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio():"")+
					"&posicionamientoAnterior="+form.getPosicionamientoAnterior()+
					"&posicionamientoSiguiente="+form.getPosicionamientoSiguiente()+
					"&busquedaSimpleAvanzada="+sesion.getBusquedaSimpleAvanzada();
				
				//Llamada obsoleta; todas las instalaciones de Agrega ya tienen el JBoss5 con una
				//version de agrega que diferencia entre buscador2 y buscador
				//destino = comprobarEnlaceFichaOde(destino);
				
				//identifidad federada
				if(LdapUserDetailsUtils.estaAutenticado()){
					String usuarioEncriptado = EncriptacionUtiles.encriptar(LdapUserDetailsUtils.getUsuario());
					destino = destino + "&federado="+usuarioEncriptado;
				}
				response.sendRedirect(destino);
				return ODE_FEDERADO;
			}
		} catch (Exception ex) {
			logger.error("DetallarControllerImpl - analizaPosicionamiento ERROR: Error analizando el posicionamiento entre local o federado",ex);
		}
		return ODE_LOCAL;
	}
	
	public void gestionarBusqueda(ActionMapping mapping, GestionarBusquedaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			SrvBuscarService buscarService = this.getSrvBuscarService();
			form.setResultados(buscarService.buscarAvanzado(cargarParametros(request,this.getBuscarSession(request).getPagina(),form.getIdioma(),this.getBuscarSession(request).getNumeroResultados(),BUSQUEDA_DETALLE)));
		}catch (Exception ex) {
			logger.error("DetallarControllerImpl - gestionarBusqueda ERROR: Error lanzando búsqueda de posicionamiento",ex);
		}
	}

	public String analizaFlujoPosicionamiento(ActionMapping mapping, AnalizaFlujoPosicionamientoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			if(form.getNodoOrigen()!=null && !form.getNodoOrigen().equals("")){
				BuscarSession sesion = this.getBuscarSession(request);
				if(sesion.getBusquedaSimpleAvanzada()!=null && sesion.getBusquedaSimpleAvanzada().equals(BUSQUEDA_ARBOL)) response.sendRedirect("http://"+form.getNodoOrigen()+"/"+request.getSession().getServletContext().getInitParameter("url_buscadorDetalle")+"?idioma="+form.getIdioma()+"&identificadorODE="+form.getIdentificadorODE()+"&posicionamiento="+form.getPosicionamiento()+"&tipoBusquedaArbol=");
				else response.sendRedirect("http://"+form.getNodoOrigen()+"/"+request.getSession().getServletContext().getInitParameter("url_buscadorDetalle")+"?idioma="+form.getIdioma()+"&identificadorODE="+form.getIdentificadorODE()+"&posicionamiento="+form.getPosicionamiento());
				return ODE_FEDERADO;
			}
		} catch (Exception ex) {
			logger.error("DetallarControllerImpl - analizaPosicionamiento ERROR: Error analizando el posicionamiento entre local o federado",ex);
		}
		return ODE_LOCAL;
	}

	public void gestionarPosicionamiento(ActionMapping mapping, GestionarPosicionamientoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			BuscarSession sesion = this.getBuscarSession(request);
			boolean posicionado = false;
			SrvBuscarService buscarService = this.getSrvBuscarService();
			ResultadoBusquedaVO resultados = buscarService.buscarAvanzado(cargarParametros(request,sesion.getPagina(), form.getIdioma(), sesion.getNumeroResultados(),BUSQUEDA_DETALLE));
			for(int i=0; i < resultados.getResultadoBusqueda().length && !posicionado; i++){
				if(form.getIdentificadorODE().equals(resultados.getResultadoBusqueda()[i].getId())){
					if(form.getPosicionamiento().equals("ANTERIOR")){
						if(i>0){
							form.setNodoDestino(resultados.getResultadoBusqueda()[i-1].getNodo());
							form.setIdentificadorODE(resultados.getResultadoBusqueda()[i-1].getId());
						}else{
							sesion.setPagina(sesion.getPagina()-1);
							resultados = buscarService.buscarAvanzado(cargarParametros(request,sesion.getPagina(),form.getIdioma(),sesion.getNumeroResultados(),BUSQUEDA_DETALLE));
							form.setNodoDestino(resultados.getResultadoBusqueda()[resultados.getResultadoBusqueda().length-1].getNodo());
							form.setIdentificadorODE(resultados.getResultadoBusqueda()[resultados.getResultadoBusqueda().length-1].getId());
						}
						posicionado = true;
					}else if(form.getPosicionamiento().equals("SIGUIENTE")){
						if(i<resultados.getResultadoBusqueda().length-1){
							form.setNodoDestino(resultados.getResultadoBusqueda()[i+1].getNodo());
							form.setIdentificadorODE(resultados.getResultadoBusqueda()[i+1].getId());
						}else{
							sesion.setPagina(sesion.getPagina()+1);
							resultados = buscarService.buscarAvanzado(cargarParametros(request,sesion.getPagina(),form.getIdioma(),sesion.getNumeroResultados(),BUSQUEDA_DETALLE));
							form.setNodoDestino(resultados.getResultadoBusqueda()[0].getNodo());
							form.setIdentificadorODE(resultados.getResultadoBusqueda()[0].getId());
						}
						posicionado = true;
					}
				}
			}
			form.setResultados(resultados);
		} catch (Exception ex) {
			logger.error("DetallarControllerImpl - gestionarPosicionamiento ERROR: En el posicionado dentro de la búsqueda del ode con ambito correcto que sigue o antecede.",ex);
		}
	}
	
//	Método que comprueba que en los campos de fecha son valores son correctos
	private void saveErrorMessage(HttpServletRequest request, List<String> messages) throws Exception{
		if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - saveErrorMessage: Formulario incorrecto------------.");
		boolean encontrado;		
		if(request!=null && messages!=null && messages.size()>0){
			//Pintamos la posición cero de la lista
			saveErrorMessage(request, messages.get(0).toString());
			//Recorremos el vector comprobando si el mensaje de error ha sido pintado anteriormente
			for(int i = 1; messages.size() > i ; i++){ 
				encontrado = false;
				for(int j=i; j>0 ;j--){
					if(messages.get(j-1).equals(messages.get(i)))
						encontrado = true;
				}
				if(!encontrado)
					saveErrorMessage(request, messages.get(i).toString());
			}
		}
	}
    
	private void rellenaFormularioDesdeSesion(HttpServletRequest request, BuscarDetalleODEForm form){
//		Si no hay sesion introducimos valores de busqueda por defecto
		BuscarSession sesion = this.getBuscarSession(request);
		if (sesion == null) {
			sesion = new BuscarSession();
			sesion.setPagina(1);
			sesion.setBuscadorContenido("");
			sesion.setIdioma("");
			form.setBusquedaSimpleAvanzada(BUSCAR);
		}else if (sesion.getBusquedaSimpleAvanzada()==null || sesion.getBusquedaSimpleAvanzada().equals(BUSCAR)){
			form.setBusquedaSimpleAvanzada(BUSCAR);
			if(logger.isDebugEnabled())logger.debug("Hemos detectado el boton atras de detalles seleccionado desde busqueda AVANZADA");
		}else if (sesion.getBusquedaSimpleAvanzada().equals(BUSQUEDA_ARBOL)){
			form.setBusquedaSimpleAvanzada(BUSQUEDA_ARBOL);
			if(logger.isDebugEnabled())logger.debug("Hemos detectado el boton atras de detalles seleccionado desde BUSQUEDA_ARBOL");
		}
	}
	
	private String convertirMB(String string){
		try{
			BigDecimal tamanio=new BigDecimal(string);
	        BigDecimal pasarMB=new BigDecimal(1048576);
	  
	        BigDecimal tamanioMB=tamanio.divide(pasarMB,2,2);
	    	return String.valueOf(tamanioMB) ;
		}catch(Exception ex){
			if (logger.isDebugEnabled())logger.error("El formato del tamaño del ODE no es correcto");
			return "0.00";
		}
    }
	
    private String concatenaArrayString(String[] string){
    	String salida = "";
    	if (string!=null && string.length >0) {
    		String[] array = string;
	    	for (int i = 0; i < array.length; i++) {
	    		if (salida.equals("")) salida = array[0];
	    		else salida=salida + ", " + array[i];	    			
			}
    	}
    	return salida;
    }

    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/spring_buscador2.properties");
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		logger.debug("Propiedad recuperada: " + sKey + " : "
				+ pSpringProperties.getProperty(sKey));
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}
    
    
    /*
     * Devuelve el ID del temino a traducir
     */
    private String getIdTermino(String termino, String identificadorVocabulario, String idiomaTerminos){

		TerminoYPadreVO terminoTraducido=null;
		if(termino!=null && !termino.trim().equals("")) {
			try{
				TerminoYPadreVO terminoVO = new TerminoYPadreVO(identificadorVocabulario, termino, idiomaTerminos,"");
				TerminoYPadreVO[] terminoArray = this.getSrvVocabulariosControladosService().obtenerIdTermino(new TerminoYPadreVO[]{terminoVO});
				if(terminoArray!=null && terminoArray.length>0) terminoTraducido=terminoArray[0];
			}catch (RemoteException e) {
	    		logger.error("DetallarControllerImpl - getIdTermino ERROR: Error en la invocacion al servicio de vocabularios controlados obteniendo el identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",e);
			} catch (Exception ex) {
				logger.error("DetallarControllerImpl - getIdTermino ERROR: Error en la obtencion de identificador del termino["+termino+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",ex);
			}
			if (terminoTraducido != null && terminoTraducido.getIdTermino() != null)
				return terminoTraducido.getIdTermino();
		}
		return "";
    }
    
    
//    Tenemos terminos en ingles que queremos traducir al castellano. Cada termino pertenece a un vocabulario y le
//    corresponde un identificador dentro de ese vocabulario que tambien tiene un identificador.
//    Recibe un String con los destinatarios separados por ":" y los devuelve traducidos separados por ","
    private String traducirTerminos(String[] terminos, String identificadorVocabulario, String idiomaTerminos,String idiomaNavegacion) throws Exception{
    	String salida = null;
		if (terminos!=null) {
			TerminoYPadreVO terminoTraducido=null;
			for (int i = 0; i < terminos.length; i++) {
//	    			Ahora tenemos que recuperar de los vocabularios controlados el valor del identificador del termino que
//	    			hemos encontrado en los destinatarios y con ese identificador obtener la traduccion del termino
//	    			Primero averiguamos el identificador del termino y luego obtenemos la traduccion del mismo
				if(!terminos[i].trim().equals("")){
					try{
						TerminoYPadreVO terminoVO = new TerminoYPadreVO(identificadorVocabulario, terminos[i],idiomaTerminos,"");
						TerminoYPadreVO[] terminoArray = this.getSrvVocabulariosControladosService().obtenerIdTermino(new TerminoYPadreVO[]{terminoVO});
						if(terminoArray!=null && terminoArray.length>0) terminoTraducido=terminoArray[0];
						if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - traducirTerminos: Obtenido identificador["+terminoTraducido.getIdTermino()!=null?terminoTraducido.getIdTermino():"null"+"] para vocabulario["+identificadorVocabulario+"] con termino["+terminos[i]+"] escrito en idioma["+idiomaTerminos+"]");
					}catch (RemoteException e) {
			    		logger.error("DetallarControllerImpl - traducirTerminos ERROR: Error en la invocacion al servicio de vocabularios controlados obteniendo el identificador del termino["+terminos[i]+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",e);
					} catch (Exception ex) {
						logger.error("DetallarControllerImpl - traducirTerminos ERROR: Error en la obtencion de identificador del termino["+terminos[i]+"] idioma en LOM-ES["+idiomaTerminos+"] identificador vocabulario["+identificadorVocabulario+"]",ex);
					}
					if (terminoTraducido != null && terminoTraducido.getIdTermino() != null){
//			    		Aqui habria que plantear la obtencion del idioma al que queremos que se traduzcan las palabras.
//			    		Se puede obtener del idioma de navegacion y tener en cuenta algun idioma por defecto.
//			    		Ahora ponemos castellano
				    	try {
//				    		Tenemos un identificador de vocabulario y un termino que pertenece al mismo (en formato texto) y el idioma
//				    		en el que esta escrito el termino. Queremos averiguar el identificador de dicho termino.
				    		String[] idTerminos = new String[]{terminoTraducido.getIdTermino()};

							TerminoVO[] terminoArray = this.getSrvVocabulariosControladosService().crearTraduccionAIdioma(idTerminos,idiomaNavegacion);
							if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - traducirTerminos: Obtenida traduccion["+ terminoArray[0].getNomTermino()+"] de termino["+terminoTraducido.getIdTermino()+"] para idioma["+idiomaNavegacion+"]");
							String traduccion = terminoArray[0].getNomTermino();
							if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - traducirTerminos: Traduciendo destinatario["+terminos[i]+"]->["+traduccion+"]");
							if (i == 0)	salida = traduccion;
							else salida= salida + ", " + traduccion;
				    	}
				    	catch (RemoteException e) {
				    		logger.error("DetallarControllerImpl - traducirTerminos ERROR: Error en la invocacion al servicio de vocabularios controlados obteniendo la traduccion del termino["+terminoTraducido.getIdTermino()+"] con idioma a traducir["+idiomaTerminos+"]",e);
						} catch (Exception ex) {
							logger.error("DetallarControllerImpl - traducirTerminos ERROR: Error en la traduccion de identificador de termino["+terminoTraducido.getIdTermino()+"] idioma a traducir["+idiomaTerminos+"]",ex);
						}
					}
				}else salida = "";
			}
		}
    	return salida;
    }
    
    private boolean existeSesion(BuscarSession sesion){
    	if(sesion.getAnyoPublicacion()==null && sesion.getEnlaceTaxSelec()==null && sesion.getAreaCurricularBusqueda()==null && sesion.getTesauroBusqueda()==null 
    	&& sesion.getAutor()==null && sesion.getBuscadorContenido()==null && sesion.getTipoLayoutBuscador()==null && sesion.getTipoLayoutBuscador()==null
    	&& sesion.getBusquedaSimpleAvanzada()==null && sesion.getContexto()==null && sesion.getDescripcion()==null && sesion.getDiaPublicacion()==null
    	&& sesion.getEdad()==null && sesion.getEmpIdDestino()==null && sesion.getEmpIdOde()==null && sesion.getEmpTipoEmpaquetador()==null && sesion.getEnlaceComunidadesSeleccionadas()==null
     	&& sesion.getIdentificadorODE()==null
    	&& sesion.getFormato()==null && sesion.getIdentificadorODE()==null && sesion.getIdioma()==null 
    	&& sesion.getIdTesauro()==null && sesion.getJerarquias()==null && sesion.getMesPublicacion()==null && sesion.getNodoOrigen()==null
    	&& sesion.getPagina()==null && sesion.getProcesoCognitivo()==null && sesion.getRecurso()==null && sesion.getSecuencia()==null && sesion.getTipoBusqueda()==null && sesion.getTipoNavegacion()==null)return false;
   // 	&& sesion.getTipoBusquedaArbol()==null && sesion.getTipoVisualizacion()==null && sesion.getTitulo()==null && sesion.getValoracion()==null && sesion.getNivelAgregacion()==null && sesion.getDestinatarios()==null) return false;
    	return true;
    }
    
    private boolean mostrarVuelta(es.pode.buscador.presentacion.basico.detallar.BuscarDetalleODEForm form){
    	if(form.getAreaCurricularBusqueda()==null && form.getBuscadorContenido()==null && form.getTipoLayoutBuscador()==null && form.getTipoLayoutBuscador()==null && form.getPagina()==null && form.getTipoBusqueda()==null
    	&& form.getBusquedaSimpleAvanzada()==null && form.getDescripcion()==null && form.getFormato()==null && form.getIdentificadorODE()!=null && form.getIdioma()!=null && form.getNodoOrigen()==null
    	&& form.getTitulo()==null && form.getValoracion()==null && form.getDestinatarios()==null && (form.getPosicionamientoAnterior()==null || form.getPosicionamientoAnterior().equals(""))
    	&& (form.getPosicionamientoSiguiente()==null || form.getPosicionamientoSiguiente().equals(""))) return false;
    	return true;
    }
    
    // Esta funcion devuelve el path de la imagen ampliada de una imagen de un ODE
    private String ampliarImagen (String url){
    	try{
    		if(url!=null && url.equals("/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_100x100))){
    			return "/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_GRANDE);
    		}else if(url!=null && !("").equals(url)){
	    		StringBuilder urlImagenReturn = new StringBuilder("");
	    		String[] urlImagenArray = url.split(DOT);
				for(int i=0; urlImagenArray!=null && i<urlImagenArray.length; i++){
					if((this.getPropertyValue("imagen.ampliada.png")).equals(urlImagenArray[i]))
						urlImagenArray[i] = this.getPropertyValue("imagen.ampliada.captured"); 
					urlImagenReturn.append(urlImagenArray[i]);
				}
				return(urlImagenReturn.toString().trim());
	    	}
    	}catch(IOException e){
    		logger.error("DetallarControllerImpl - ampliarImagen: Error al ampliar imagen con url="+url, e);
    	} catch (Exception e) {
			logger.error("Se ha producido un error - "+ e.getCause());
		}
    	return "";
    }
	
	private ParametrosBusquedaAvanzadaVO30 cargarParametros(HttpServletRequest request,Integer pagina,String idioma,String numeroResultados, String busquedaSimpleAvanzada) throws Exception {
		if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - cargarParametros");
		BuscarSession sesion = this.getBuscarSession(request);
		ParametrosBusquedaAvanzadaVO30 param = new ParametrosBusquedaAvanzadaVO30();
		param.setIdiomaNavegacion(((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage());
		param.setIdiomaBusqueda(idioma);
		param.setOrigenPagina(pagina);
		param.setPalabrasClave(prepararTextoBusqueda(sesion.getBuscadorContenido()));
		
		param.setFormato(sesion.getFormato()!=null?sesion.getFormato().split("-"):new String[0]);
		param.setRecurso(sesion.getRecurso());
		param.setNivelAgregacion(sesion.getNivelAgregacion()!=null?sesion.getNivelAgregacion().split("-"):new String[0]);
		param.setDestinatarios(sesion.getDestinatarios());
		param.setProcesoCognitivo(sesion.getProcesoCognitivo());
		param.setFechaPublicacion(construirFecha(sesion.getAnyoPublicacion(),sesion.getMesPublicacion(),sesion.getDiaPublicacion()));
		param.setContexto(sesion.getContexto());
		param.setEdad(prepararTextoBusqueda(sesion.getEdad()));
		param.setBusquedaSimpleAvanzada(busquedaSimpleAvanzada);
		//Es el IdOde inicial,no el del detalle.Esto influia a la hora de encontrar el elemento anterior y siguiente
		param.setIdentificador(sesion.getIdODE());
		param.setKeyword(sesion.getKeyword());
		//param.setIdTesauro((sesion.getIdTesauro()!=null && !sesion.getIdTesauro().equals(""))?sesion.getIdTesauro():sesion.getTesauroBusqueda());
		param.setAutor(prepararTextoBusqueda(sesion.getAutor()));
		param.setSecuencia((sesion.getSecuencia()!=null && !sesion.getSecuencia().equals(""))?Boolean.valueOf(sesion.getSecuencia()):null);
		param.setValoracion(sesion.getValoracion()!=null && !sesion.getValoracion().equals("")?sesion.getValoracion().replaceAll("-", SEPARADOR_PALABRAS):null);
		
		String[] taxTesSelec=null;
		String tesauroBusqueda = sesion.getTesauroBusqueda();
		String taxonomíaBusqueda = sesion.getAreaCurricularBusqueda();
		String tipoNavegacion = sesion.getTipoNavegacion();
		
		String busquedaTax = "";
		if(tesauroBusqueda!=null && !tesauroBusqueda.trim().equals("")){
			busquedaTax = tipoNavegacion + "/" + tesauroBusqueda;
		}else if (taxonomíaBusqueda!=null && !taxonomíaBusqueda.trim().equals("")){
			busquedaTax = tipoNavegacion + "/" + taxonomíaBusqueda;
		}
		
		String taxonomiasSelec = sesion.getEnlaceTaxSelec();
		String idTesauro = sesion.getIdTesauro();
			
		if(taxonomiasSelec!=null && !taxonomiasSelec.trim().equals("")){
			String[] taxSelec = taxonomiasSelec.split(BuscarAvanzadoControllerImpl.TAX_ASTERISCO);
			int tamaño = taxSelec.length;
			tamaño = tamaño + ((busquedaTax.equals(""))? 0 : 1);
			if(idTesauro !=null && !idTesauro.trim().equals("")){
				EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
				String vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
			
				tamaño = tamaño +1;
				taxTesSelec=new String[tamaño];
				if(busquedaTax.trim().equals(""))
					taxTesSelec[tamaño-1]= vocabIdentifierTesauro + "/" + idTesauro;
				else
					taxTesSelec[tamaño-2]= vocabIdentifierTesauro + "/" + idTesauro;
			}else{
				taxTesSelec=new String[tamaño];
			}
			
			if(!busquedaTax.trim().equals(""))
				taxTesSelec[tamaño-1]=busquedaTax;
			
			for (int i=0;i<taxSelec.length;i++){
				taxTesSelec[i] = taxSelec[i].replace("\\$","/");
			}
		}else if(idTesauro !=null && !idTesauro.trim().equals("")){
			String vocabIdentifierTesauro="";
			if(sesion.getTipoBusqueda().equalsIgnoreCase("07")||sesion.getTipoBusqueda().equalsIgnoreCase("08"))
			{
				//Va a almacenar el IdTesauro y el código del tesauro seleccionado (en circunstancias normales se recoge el tesauro vigente)
    			String[] tesauro = new String[2];
    			tesauro=idTesauro.replace('$', '/').split("/");
    			idTesauro=tesauro[1];
    			
    			//Recogemos el tesauro seleccionado previamente al RSS
    			vocabIdentifierTesauro=tesauro[0];
    			
			}
			else
			{
		
			EstructuraVdexVO tesauroVigente = this.getSrvTesaurosServices().obtenerTesauroVigente();
			vocabIdentifierTesauro = tesauroVigente.getVocabIdentifier();
			}
			
			if(!busquedaTax.trim().equals("")){
				taxTesSelec = new String[2];
				taxTesSelec[0] = vocabIdentifierTesauro + "/" + idTesauro;
				taxTesSelec[1] = busquedaTax;
			}else{
				taxTesSelec = new String[1];
				taxTesSelec[0] = vocabIdentifierTesauro + "/" + idTesauro;
			}	
		}else if (!busquedaTax.trim().equals("")){
			taxTesSelec = new String[1];
			taxTesSelec[0] = busquedaTax;	
			
		}
	
					
		param.setTaxonomia(taxTesSelec);
		
		param.setAmbito(AMBITO_UNIVERSAL+SEPARADOR_PALABRAS+DependentServerProperties.getInstance().getProperty(IDENTIFICADOR_NODO));
		//param.setAreaCurricular((sesion.getAreaCurricularBusqueda()!=null && !sesion.getAreaCurricularBusqueda().equals(""))?sesion.getAreaCurricularBusqueda():sesion.getAreaCurricular());
		param.setComunidadPeticion((sesion.getNodoOrigen()!=null && !sesion.getNodoOrigen().equals(""))?sesion.getNodoOrigen():LdapUserDetailsUtils.getHost());
		if(sesion.getTipoBusqueda()!=null && sesion.getTipoBusqueda().equals("01") )param.setComunidadesSeleccionadas(obtenerComunidades());
		else if(sesion.getEnlaceComunidadesSeleccionadas()!=null && !sesion.getEnlaceComunidadesSeleccionadas().equals(""))
			param.setComunidadesSeleccionadas(sesion.getEnlaceComunidadesSeleccionadas().split("-"));//param.setComunidadesSeleccionadas(obtenerComunidades(sesion.getEnlaceComunidadesSeleccionadas().split("-")));
	/*OJOOOOOOOOOOOOOOOOOOOOOOOO	else if(sesion.getTipoBusquedaArbol()!=null && sesion.getTipoBusquedaArbol().equals("01")){
			param.setComunidadesSeleccionadas(obtenerComunidades());
		}*/
		//si búsqueda federada entonces se selecciona TODAS
		String maxRes = request.getSession().getServletContext().getInitParameter("max_res_totales");
		if (maxRes == null || maxRes.equals("")) maxRes = "1000";
		param.setNumeroResultados(new Integer(maxRes));
		if (numeroResultados == null || numeroResultados.equals("")) numeroResultados = "10";
		param.setResultadosPorPagina(new Integer(numeroResultados));
		return param;
	}
	
	private String[] obtenerComunidades() throws RemoteException, Exception{		
		NodoVO[] nodoLista = this.getSrvNodoService().listarNodos();
   	 	String[]comuni = new String[nodoLista.length];
   	 	for(int j=0; j<nodoLista.length;j++) comuni[j]=nodoLista[j].getId().toString();
		return comuni;
	}
    
    public String construirFecha(String anyoFechaPublicacion,String mesFechaPublicacion,String diaFechaPublicacion){
		if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - construyeFecha");
		if(!comprobarAtributoConValor(anyoFechaPublicacion) && !comprobarAtributoConValor(mesFechaPublicacion) &&	!comprobarAtributoConValor(diaFechaPublicacion)) return "";
		if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - construyeFecha: fecha a enviar=["+traducirFecha(anyoFechaPublicacion) + traducirFecha(mesFechaPublicacion) + traducirFecha(diaFechaPublicacion)+"]");
		return traducirFecha(anyoFechaPublicacion) + traducirFecha(mesFechaPublicacion) + traducirFecha(diaFechaPublicacion);
		
	}
	
	private String traducirFecha(String campo){
		if(campo!=null && !campo.trim().equals(""))return campo;
		return "*";
	}
	
	private boolean comprobarAtributoConValor(String variable){
		//Compruebo que el existe el campo analizado
		return(variable!=null && !variable.equals(""));
	}
	
	
	// Esta funcion comprueba si existe la imagen y en caso de no ser asi devuelve la url de la de por defecto
	private String comprobarImagen(String servidor, String imagen) throws Exception {
		try{
			new InputStreamReader(Proxy.getInputStream(new URL("http://"+servidor+LdapUserDetailsUtils.getSubdominio()+imagen)));
			return imagen;
		}catch(Exception ex){
			if(logger.isDebugEnabled())logger.debug("DetallarControllerImpl - comprobarImagen=http://"+servidor+LdapUserDetailsUtils.getSubdominio()+imagen);
			return LdapUserDetailsUtils.getSubdominio()+"/"+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_IMAGEN_DEFECTO_100x100);
		}
	}

	private String prepararTextoBusqueda(String texto){
		if(texto!=null && !texto.equals(""))return texto.replaceAll("&quot;", DetallarControllerImpl.JAVA_QUOTES).replaceAll(BuscarAvanzadoControllerImpl.HTML_PLUS, BuscarAvanzadoControllerImpl.JAVA_PLUS).replaceAll(DetallarControllerImpl.DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.DOUBLE_DOT, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_QUESTIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_PARENTHESES, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.OPEN_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.CLOSE_EXCLAMATIONMARK, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.COMA, DetallarControllerImpl.SEPARADOR_PALABRAS).replaceAll(DetallarControllerImpl.SLASH, DetallarControllerImpl.SEPARADOR_PALABRAS);
		return "";
	}

}