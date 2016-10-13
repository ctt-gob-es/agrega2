// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.gestorFlujo.presentacion.ObjetosPersonalesSession;
import es.pode.gestorFlujo.presentacion.albumes.listarAlbum.ListarAlbumControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.AlbumConTamainoVO;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesController;
import es.pode.gestorFlujo.presentacion.objetosPersonales.ObjetosPersonalesControllerImpl;
import es.pode.gestorFlujo.presentacion.objetosPersonales.TransicionConTamainoVO;
import es.pode.gestorFlujo.presentacion.utilidades.TransicionConTamainoVOComparator;
import es.pode.gestorFlujo.presentacion.utilidades.UtilesCuota;
import es.pode.parseadorXML.castor.Lom;
import es.pode.publicacion.negocio.servicios.AlbumVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.publicacion.negocio.servicios.TransicionVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.cuota.Cuota;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.serializar.SerializadorObjetos;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController
 */
public class BuscarPersonalesControllerImpl extends BuscarPersonalesController
{

	private Logger logger = Logger.getLogger(ObjetosPersonalesController.class);

	public final static String SIN_ERRORES = "0.0";
	private static final String HTTP ="http://";
	private static final String BARRA ="/";
	private static final String PUNTOS_SUSPENSIVOS="..";
	public final static String OBJETOS_PERSONALES = "objetosPersonales";
	public final static String SIN_ALBUM ="Sin álbum";
	protected final static String URL_IMAGEN ="static/img/azul/";
	protected final static String URL_IMAGEN_ODE_VERSIONADO ="static/img/azul/";
	public final static String ESTADO_PUBLICADO_VERSIONANDOSE = "PUBLICADO_VERSIONANDOSE";


	//Este metodo debe ser identico al ObjetosPersonalesController.cargarODESPersonales
	//A excepcion de que la llamada que haga al publicador para obtener la lista de ODEs
	//debe ser un nuevo metodo del publicador que recupere los ODEs con estado PERSONALES por titulo
    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#buscarOdesPorTitulo(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarOdesPorTituloForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void buscarOdesPorTitulo(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarOdesPorTituloForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Primero me traigo todos los albumes del usuario, sin ellos, no pinto la pantalla.
		AlbumVO[] albumes = null;
		try{
			albumes = this.getSrvAlbumService().obtenAlbumesPorUsuario(LdapUserDetailsUtils.getUsuario());
			  if(albumes != null && albumes.length>0) {
				  logger.debug("Se han cargado ["+ albumes.length+ "] albumes del usuario["+ LdapUserDetailsUtils.getUsuario()+"]");
				  AlbumConTamainoVO[] albumesConTamaino = new AlbumConTamainoVO[albumes.length];
				  for (int i = 0; i < albumesConTamaino.length; i++) {
					  albumesConTamaino[i] = new AlbumConTamainoVO();
					  albumesConTamaino[i].setDescripcion(albumes[i].getDescripcion());
					  albumesConTamaino[i].setFechaCreacion(albumes[i].getFechaCreacion());
					  albumesConTamaino[i].setId(albumes[i].getId());
					  albumesConTamaino[i].setNumeroOdes(albumes[i].getOdes().length);
					  if(albumes[i].getTitulo().length()>20){
						  String tituloCorto =albumes[i].getTitulo().substring(0, 18)+PUNTOS_SUSPENSIVOS;
					  	  albumesConTamaino[i].setTitulo(tituloCorto);
					  }
					  else{
						  albumesConTamaino[i].setTitulo(albumes[i].getTitulo());
					  }
					  albumesConTamaino[i].setUsuario(albumes[i].getUsuario());
					}
				
				 form.setListaAlbumesAsArray(albumesConTamaino);
				 form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
	  		}
		} catch (Exception ex) {
			logger.error("Error inesperado cargando los albumes del usuario: " + LdapUserDetailsUtils.getUsuario(), ex);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
		

		//Llegados a este punto, ya tengo mapeados todos los albumes del usuario.
		//Ahora listo los ODEs creados por el usuario y adecuo lo que voy a pintar en pantalla para cada ODE dependiendo del album en el que este
		try{
			logger.debug("Cargando objetos personales");
			//Cargamos la sesion, para pasar el retorno y luego saber donde debemos volver.
			ObjetosPersonalesSession sesion = this.getObjetosPersonalesSession(request);
	    	sesion.setRetorno(OBJETOS_PERSONALES);
	    	sesion.setIdAlbum(null);
	    	//Me traigo todos los ODEs personales 
			TransicionVO[] odesPersonales = this.getSrvPublicacionService().obtenODEsCreadosPorUsuarioPorTitulo(LdapUserDetailsUtils.getUsuario(), form.getTextoBusqueda()); 
			
			//Obtenemos los odes publicados autonomamente asociados al mismo usuario que tiene estos odes Personales. 
			TransicionVO[] odesPublicadosAutonomos=this.getSrvPublicacionService().obtenODEsPublicadosAutonomoPorUsuario(LdapUserDetailsUtils.getUsuario());
			
			//Calculo el peso de los ODEs de la carpeta personal, que lo componen los ODEs creados, rechazados y autopublicados
			String[] idODESPublicadosAutonomos = new String[odesPublicadosAutonomos.length];
			for (int i = 0; i < odesPublicadosAutonomos.length; i++) 
				idODESPublicadosAutonomos[i] = odesPublicadosAutonomos[i].getIdODE();
			
			//Calculamos lo que pesan los odes publicados Autonomos.
			Long[] tamanioOdesPublicadosAutonomos= this.getSrvLocalizadorService().consultaEspacioLocalizadores(idODESPublicadosAutonomos);
			
			//Recorro la lista de los ODEs personales (creados y rechazados) para ver con que datos mapeo cada pastilla
			if(odesPersonales !=null && odesPersonales.length>0){
				TransicionConTamainoVO[] odesTamaino=new TransicionConTamainoVO[odesPersonales.length];
	
				String[] identificadoresOdesPersonales=new String[odesPersonales.length];
				logger.debug("Entramos en el mapeo de ["+odesPersonales.length+"] ODES");
				for(int i=0;i<odesPersonales.length;i++){
					odesTamaino[i]=new TransicionConTamainoVO();
					odesTamaino[i].setComentarios(odesPersonales[i].getComentarios());
					odesTamaino[i].setFecha(odesPersonales[i].getFecha());
					odesTamaino[i].setIdODE(odesPersonales[i].getIdODE());
					odesTamaino[i].setIdUsuario(odesPersonales[i].getIdUsuario());
					odesTamaino[i].setTitulo(odesPersonales[i].getTitulo());
					odesTamaino[i].setCompartir(odesPersonales[i].getCompartido());
					//Miro si el ode esta en un album, para mostrar en la pastilla, el album, o la opcion de asociarse a uno de ellos.
					odesTamaino[i].setEstaEnAlbum(odesPersonales[i].getAlbum()==null?false:true);
					odesTamaino[i].setAlbum(odesPersonales[i].getAlbum()==null?"":odesPersonales[i].getAlbum().getDescripcion());
					odesTamaino[i].setEstadoActual(odesPersonales[i].getEstadoActual().getClave());
					/*
					if (odesPersonales[i].getEstadoActual().getClave().equals(ESTADO_PUBLICADO_VERSIONANDOSE))
						odesTamaino[i].setEstaVersionandose(true);
					else
						odesTamaino[i].setEstaVersionandose(false);
					*/	
					odesTamaino[i].setEstaVersionandose(this.getSrvPublicacionService().haEstadoVersionado(odesPersonales[i].getIdODE()));
					odesTamaino[i].setUrlImagen(crearUrlImagen(odesTamaino[i].getEstaVersionandose()));		
					logger.debug("El identificador del ODE:"+odesTamaino[i].getIdODE());
					identificadoresOdesPersonales[i]=odesPersonales[i].getIdODE();//Los guardamos para hacer la consulta al localizador
				}
				Long[] tamainoOdes=new Long[identificadoresOdesPersonales.length];
				if(identificadoresOdesPersonales!=null && identificadoresOdesPersonales.length>0){
					tamainoOdes=this.getSrvLocalizadorService().consultaEspacioLocalizadores(identificadoresOdesPersonales);
				}
				
				for(int i=0;i<odesTamaino.length;i++){
					if(tamainoOdes[i]!=null){
						String output = UtilesCuota.parseaCuota(tamainoOdes[i]);
						odesTamaino[i].setTamaino(output);
						logger.debug("Tamaino del ODE con id:"+odesTamaino[i].getIdODE()+ "] y su tamaino es:"+output);
					}else{
						odesTamaino[i].setTamaino("0");
						logger.debug("Tamaino del ODE con id:"+odesTamaino[i].getIdODE()+ "  su tamaino es: 0");
					}
				}
				Arrays.sort(odesTamaino, new TransicionConTamainoVOComparator());
	//			UtilesODEs.quicksort(odesTamaino, 0, odesTamaino.length-1); //Los ordeno, para pasarlos a la jsp ordenados por fecha
	//			form.setListaODESAsArray(odesTamaino);
				//form.setListaODES(Arrays.asList(odesTamaino));
				form.setListadoOdesPersonalesTitulo(Arrays.asList(odesTamaino));
				form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
				//Sumamos el peso de los personales y de los publicados Autonomos
				form.setTotalSuma(UtilesCuota.suma(tamainoOdes, tamanioOdesPublicadosAutonomos));
	//			form.setTotalSuma(Cuota.totalTamainoOdes(tamainoOdes));
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L; //Lo tendremos que recoger de soporte
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
				//La cuota irá en MB, no en bytes
				long divCuota = cuota/(1024*1024);
				form.setCuotaUsuario(divCuota);
				form.setEspacioLibre(Cuota.restanteTamaino(form.getTotalSuma(),cuota));
				//Añado a la sesion el espacioLibre que me queda.
				sesion.setEspacioLibre(form.getEspacioLibre());
				logger.debug("Insertamos usuario: ["+ form.getIdUsuario()+"], el total de la suma es: ["+form.getTotalSuma()+"] y la cuota de soporte: ["+cuota+"]");
			}else{
				//form.setListaODESAsArray(odesPersonales);
				form.setListadoOdesPersonalesTituloAsArray(odesPersonales);
				form.setIdUsuario(LdapUserDetailsUtils.getUsuario());
				form.setTotalSuma(Cuota.totalTamainoOdes(tamanioOdesPublicadosAutonomos));
				long cuota=LdapUserDetailsUtils.getCuota()!=null?LdapUserDetailsUtils.getCuota():0L;
				form.setPorcentajeMemoriaCubierta(Cuota.porcentajeCubierto(form.getTotalSuma(),cuota));
				//La cuota debe ir en MB
				long divCuota = (LdapUserDetailsUtils.getCuota()/(1024*1024));
				form.setCuotaUsuario(divCuota);
				form.setEspacioLibre(LdapUserDetailsUtils.getCuota());
			}
		}catch(Exception e){
			logger.error("Error al obtener los objetos personales: " + e);
			throw new ValidatorException("{gestor.flujo.error.obtener.personales}");
		}
    }

    
    /**
     * Este metodo debe ser identico al que hay en ObjetosPersonalesControllerImpl
     * 
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#compartirDescompartirODE(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.CompartirDescompartirODEForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void compartirDescompartirODE(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.CompartirDescompartirODEForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		ResultadoOperacionVO resultado;
		logger.debug("La variable que nos llega es:"+form.getIdODE()+" "+form.getCompartidoSN());
		if(!(Boolean.valueOf(form.getCompartidoSN()).booleanValue())){
			logger.debug("Vamos a COMPARTIRLO");
			resultado=this.getSrvPublicacionService().compartirODE(form.getIdODE());
		}else{
			resultado=this.getSrvPublicacionService().descompartirODE(form.getIdODE());
			logger.debug("Esta compartido y lo DESCOMPARTIREMOS");
		}
    }


    /**
     * Este metodo debe ser identico al que hay en ObjetosPersonalesControllerImpl
     * 
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#comprobarOrigen(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.ComprobarOrigenForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String comprobarOrigen(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
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


    /**
     * Este metodo debe ser identico al que hay en ObjetosPersonalesControllerImpl
     * 
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#selectAction(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		logger.debug("El action que llega es "+actionSubmit);
		logger.debug("Espacio libre "+form.getEspacioLibre());

		if (actionSubmit.equals("Crear Metadato")){
			result = "Crear Metadato";
		} else {
			String url = HTTP + LdapUserDetailsUtils.getHost();
			String returnUrl=BARRA + request.getSession().getServletContext().getInitParameter("url_objetos");
			
			if(LdapUserDetailsUtils.getSubdominio()!=null && 
			   !LdapUserDetailsUtils.getSubdominio().equals(""))
			{
				returnUrl = LdapUserDetailsUtils.getSubdominio() + returnUrl;
			}
			url= url + returnUrl;
			String urlCompleto=url+"?espacioLibre="+form.getEspacioLibre()+"&espacioODE=0";
			response.sendRedirect(urlCompleto);
			logger.debug("Lo reenviamos a "+urlCompleto);
			result = "Crear";
		} 
		return result;
    }

    
    /**
     * Este metodo debe ser identico al que hay en ObjetosPersonalesControllerImpl
     * 
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#crearMetadato(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.CrearMetadatoForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crearMetadato(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.CrearMetadatoForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		try {
			//Para crear Metadatos desde la carpeta personal, debemos generar un identificador ficticio que lo identifique
			logger.info("ASC - Otra vez el usuario es " + LdapUserDetailsUtils.getUsuario());
			//String idFicticio1=  es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
			String idFicticio = es.pode.soporte.uuid.PodeUUIDGenerator.getUUID(String.valueOf(System.currentTimeMillis()));
			//String idFicticio2 = podeUUIDGen.getUUID(String.valueOf(System.currentTimeMillis()));
			logger.info("ASC - el idFicticio que estamos generando es  --> " + idFicticio);
			//vemos el tipo de Perfil que tiene el usuario
			String idioma= ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
			String tipoCatalogador="";
			//Creamos un Lom y lo serializamos		
			Lom lomInicial = new Lom();
			SerializadorObjetos lomSerializado = new SerializadorObjetos();
			DataHandler lomHandler= lomSerializado.serializarObjeto(lomInicial);
			
			if ("avanzado".equals(LdapUserDetailsUtils.getCatalogador())){
				logger.info("Vamos a trabajar con el Catalogador Avanzado");
				tipoCatalogador="Avanzado";
				this.getSrvCatalogacionAvanzadaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE AVANZADO CON IDENTIFICADOR " + idFicticio);
			}else if ("basico".equals(LdapUserDetailsUtils.getCatalogador())){
				logger.info("Vamos a trabajar con el tipo de Catalogador Básico");
				tipoCatalogador="Basico";
				this.getSrvCatalogacionBasicaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE BASICO  CON IDENTIFICADOR " + idFicticio);
			}else {
				logger.info("Vamos a trabajar por defecto con el Catalogador Avanzado");
				tipoCatalogador="Avanzado";
				this.getSrvCatalogacionAvanzadaService().cargarObjLom(idFicticio, LdapUserDetailsUtils.getUsuario(), lomHandler);
				logger.info("ASC - HEMOS METIDO EL LOM EN LA HASH DE AVANZADO POR DEFECTO CON IDENTIFICADOR " + idFicticio);
			}
		
			//REDIRIGIMOS AL CATALOGADOR WEB, CON OTRO PARAMETRO EN LA LLAMADA...
			// EL PARAMETRO ES CREARMETADATO
			//URLs tipo host/agrega
			if (!tipoCatalogador.equals("")){
				String url = request.getSession().getServletContext().getInitParameter("url_catalogador");
		    	String returnUrl= request.getSession().getServletContext().getInitParameter("url_gestorFlujo"); //gestorFlujo/ObjetosPersonalesCU/ObjetosPersonalesCU.do
		    	
		    	url = ( url + "?idioma=" + idioma + "&usuario=" + LdapUserDetailsUtils.getUsuario()
				+ "&identificador=" + idFicticio + "&returnURL=" + returnUrl +"&crearMetadato=CM");
				//Tenemos que añadirle 2 parámetros más; 1º el que indicará al catalogador que es una catalogacion directa
		    
				url = "http://"+LdapUserDetailsUtils.getHost()+LdapUserDetailsUtils.getSubdominio()+"/"+url;
				logger.info("ASC - LA URL ES " + url);
				
				if(logger.isDebugEnabled()) logger.debug("Redirigiendo a url " + url);
				response.sendRedirect(url);		
				//tendremos que controlar las vueltas ahora desde el catalogadrWeb y ponerle otro parámetro
			}
		} catch (Exception e) {
			logger.error("Se ha producido un Error " + e);
			throw new ValidatorException("{gestor.flujo.error.obtener.personales}");
		}
    }


    /**
     * Este metodo debe ser identico al que hay en ObjetosPersonalesControllerImpl
     * 
     * @see es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.BuscarPersonalesController#submit(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPersonales.buscarPersonales.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		logger.debug("Estamos en submit, con action "+form.getAction());
		logger.debug("Espacio libre "+form.getEspacioLibre());
    	String accion = form.getAction();
    	if (accion.equals(i18n.getString("gestorFlujo.mostrarOdes.eliminar"))) 		
    	{
    		try{
    			List lista = (form).getIdODERowSelection();
    			logger.debug("Obtenemos la lista de odes, el primer elemento es  "+lista);
    			if (lista  != null)
    			{
    				form.setOrigen("personales");
    				String[] lOdes = new String[lista.size()];
    				lista.toArray(lOdes);
    				//form.setListODEs(lOdes);
    				form.setListadoOdesPersonalesTitulo(lOdes);
    				Logger.getLogger(this.getClass()).info("Obtenidos los odes de longitud "+lOdes.length);
    			}
    		} catch (Exception e){
        		logger.error("Se ha producido un error al intentar recuperar los ids de los odes a borrar: " +e);
        		throw e;
        	}
    	}
    }
	
	
	private String crearUrlImagen(Boolean estaVersionandose) throws RemoteException, Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(AgregaPropertiesImpl.getInstance().getUrlNodo());
		sb.append(BARRA);
		if(!estaVersionandose) {
			sb.append(URL_IMAGEN);
			sb.append("caja_personal.jpg");
		} else {
			sb.append(URL_IMAGEN_ODE_VERSIONADO);
			sb.append("caja_compartido.jpg");				
		}
		return sb.toString();
	}

}